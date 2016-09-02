/*
   $Id: ConnectionBreakHandlerImpl.java,v 1.14.8.6 2015/07/31 10:06:18 ayush Exp $
 */
package test;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.*;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;
import java.net.Socket;
import java.sql.PreparedStatement;


import org.hibernate.JDBCException;

import com.adventnet.management.log.Log;
import com.adventnet.management.log.SystemUtil;
import com.adventnet.management.scheduler.Scheduler;
import com.adventnet.management.transaction.ConnectionBreakHandler;
import com.adventnet.management.transaction.ConnectionPool;
import com.adventnet.nms.admin.ShutDownAPIImpl;
import com.adventnet.nms.fe.common.BEFailOverEvent;
import com.adventnet.nms.fe.common.BEFailOverListener;
import com.adventnet.nms.startnms.MainSocketSessionBE;
import com.adventnet.nms.startnms.NmsMainFE;
import com.adventnet.nms.startnms.UpdateMaintainer;
import com.adventnet.nms.store.relational.RelationalAPI;
import com.adventnet.nms.util.DBParamsParser;
import com.adventnet.nms.util.NmsLogMgr;
import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.util.PureServerUtils;
import com.adventnet.nms.util.PureServerUtilsFE;
import com.adventnet.nms.util.PureUtils;
import com.adventnet.nms.util.hbn.HibernateUtil;
import com.adventnet.nms.store.relational.RelationalAPI;


public class ConnectionBreakHandlerImpl implements ConnectionBreakHandler,BEFailOverListener
{
    private static boolean isDBFailureSentToFEs = false;
    private boolean shutcalled=false;
    private int dbreconnection_timeout = 20000; 
    private String dbreconnection="false"; //No Internationalization
    private String dbreplication="false"; //No Internationalization
    private static final int DB_RECONNECT = 432;
    private int acquireRetryAttempts = 10;
    private static String unique_id = "0"; // No Internationalization
    private boolean refresh=true;
    private boolean closeOldConnections=true;
	private String serverType="BE";
    String hbnFile = "classes/hbnlib/hibernate.cfg.xml";
    String db = null;
    private boolean isOracle;
    private boolean isMSSQL;
	private boolean isPostgresql;
	private boolean isMySQL;

    public ConnectionBreakHandlerImpl()
    {
    	db = PureServerUtils.getDatabaseName().toLowerCase();
    	if(db.equals("oracle")) isOracle = true;
    	else if(db.contains("microsoft sql server")) isMSSQL = true;
    	else if(db.contains("postgresql")) isPostgresql = true;
    	else if(db.contains("mysql")) isMySQL = true;
    	
    	serverType=NmsUtil.getServerType();
    	if(!serverType.equalsIgnoreCase("BE") && !serverType.equalsIgnoreCase("REMOTE_POLLER"))
    	{
    		FailOverListenerThread failover= new FailOverListenerThread(this);
        }
    }
    private void initParams()
    {
        try
        {
            String dbreconn = NmsUtil.getParameter("ENABLE_DB_RECONNECTION"); //No Internationalization
            if(dbreconn != null)
            {
                dbreconnection = dbreconn;
            }
            System.out.println(" [ConnectionBreakHandler] Value set for ENABLE_DB_RECONNECTION :: " + dbreconnection); //No Internationalization
            NmsLogMgr.HAUSER.log(" [ConnectionBreakHandler] Value set for ENABLE_DB_RECONNECTION :: " + dbreconnection,Log.SUMMARY); //No Internationalization
            if(!dbreconnection.equalsIgnoreCase("true"))
            {
            	return;
            }
            String dbreconn_timeout = HibernateUtil.getHbnProperty("hibernate.c3p0.acquireRetryDelay"); //No Internationalization
//          If c3p0 is used as underlying database, hibernate.c3p0.acquireRetryDelay is mandatory. hence WebNMS parameter which was used for same purpose is discarded.   
//          String dbreconn_timeout = NmsUtil.getParameter("DB_RECONNECTION_TIMEOUT"); //No Internationalization
            if(dbreconn_timeout != null)
            {
                dbreconnection_timeout = (Integer.parseInt(dbreconn_timeout)); //No Internationalization
            }
            else
            {
            	System.err.println("WARNING : The parameter 'hibernate.c3p0.acquireRetryDelay' is not configured. If C3P0 is used as underlying connection pool, this parameter mandatory.");
		NmsLogMgr.HAERR.log("WARNING : The parameter 'hibernate.c3p0.acquireRetryDelay' is not configured. If C3P0 is used as underlying connection pool, this parameter mandatory.",Log.SUMMARY);
            }
            System.out.println(NmsUtil.GetString(" [ConnectionBreakHandler] Value set for DB_RECONNECTION_TIMEOUT :: ") + dbreconnection_timeout); //No Internationalization
	    NmsLogMgr.HAUSER.log(NmsUtil.GetString(" [ConnectionBreakHandler] Value set for DB_RECONNECTION_TIMEOUT :: ") + dbreconnection_timeout,Log.SUMMARY); //No Internationalization
            String dbrep= NmsUtil.getParameter("DB_REPLICATION"); //No Internationalization
            if(dbrep!=null)
            {
                dbreplication =dbrep;
            }
        }
        catch(Exception exx)
        {
          System.err.println("EXCEPTION WHILE READING PARAMETERS FOR DBRECONNECTION.GOING BY DEFAULT");//No I18N
	  NmsLogMgr.HAERR.log("EXCEPTION WHILE READING PARAMETERS FOR DBRECONNECTION.GOING BY DEFAULT",1);//No I18N
		}
	}

	public void handleConnectionFail(Connection con, Exception e)
	{
		/*
		 * TODO : need to sync the methods handleConnectionFail and handleConnectionFail(2 args)?
		 */
		boolean dbLost = false;
		
		if(con==null || e==null)
		{
			NmsLogMgr.MISCERR.fail("[ConnectionBreakHandler] : Connection is null, Hence calling handleReconnection directly."+Thread.currentThread(), e); //No I18N
			handleReConnection();
			return;
		}           
		if(e instanceof JDBCException)
		{
			e = ((JDBCException)e).getSQLException();
		}
		if(e instanceof SQLException)
		{
			SQLException sqle = (SQLException)e;
			/*Additional error codes viz. 00942 01012 01033 01034 01089 03113 12203 12500 12505 12571 17002 17401 17412 17442 are added for MRV's Oracle replication. Refer http://bit.ly/vZsMeg for more details*/

			if((isOracle&&(sqle.getErrorCode() == 942 || sqle.getErrorCode() == 1012 || sqle.getErrorCode() == 1033 || sqle.getErrorCode() == 1034 || sqle.getErrorCode() == 1089 || sqle.getErrorCode() == 03113 || sqle.getErrorCode() == 12203 || sqle.getErrorCode() == 12500 || sqle.getErrorCode() == 12505 || sqle.getErrorCode() == 12528 || sqle.getErrorCode() == 12571 || sqle.getErrorCode() == 17002 || sqle.getErrorCode() == 17401 || sqle.getErrorCode() == 17412 || sqle.getErrorCode() == 17442 )) || (isOracle&&(sqle.getMessage().indexOf("No more data to read from socket") != -1))|| (sqle.getMessage().indexOf("An attempt by a client to checkout a Connection has timed out") != -1) || (isMSSQL && ("08S01".equals(sqle.getSQLState()))) || (isPostgresql && (sqle.getSQLState().equals("57P01") || sqle.getSQLState().equals("57P02") || sqle.getSQLState().equals("57P03"))) )
			{
				NmsLogMgr.MISCERR.fail("[ConnectionBreakHandler] : SQLException occurred in Connection on Thread : "+Thread.currentThread(), sqle); //No I18N
				NmsLogMgr.HAERR.fail("[ConnectionBreakHandler] : SQLException occurred in Connection on Thread : "+Thread.currentThread(), sqle); //No I18N
				dbLost = true;
			}
//			else if(isMySQL && sqle != null && sqle.getSQLState().startsWith("08"))
			else if(isMySQL && sqle != null && sqle.getSQLState() != null && sqle.getSQLState().startsWith("08"))
			{
				NmsLogMgr.MISCERR.fail("[ConnectionBreakHandler] : SQLException occurred in Connection on Thread : "+Thread.currentThread(), sqle); //No I18N
				NmsLogMgr.HAERR.fail("[ConnectionBreakHandler] : SQLException occurred in Connection on Thread : "+Thread.currentThread(), sqle); //No I18N
				dbLost = true;				
			}				
		} 
	/*	else if(con==null || e==null)
		{
			NmsLogMgr.HAERR.fail("[ConnectionBreakHandler] : Connection is null, Hence calling handleReconnection directly."+Thread.currentThread(), e); //No I18N
			handleReConnection();
			return;
		}*/
		else
		{
			try 
			{
				if(con.isClosed())
				{
					NmsLogMgr.MISCERR.fail( "[ConnectionBreakHandler] will refresh connections as connections are closed with message. "+Thread.currentThread(), e); //No I18N
				NmsLogMgr.HAERR.fail("[ConnectionBreakHandler] : SQLException occurred in Connection on Thread : "+Thread.currentThread(), e); //No I18N
					dbLost = true;
				}				
			} 
			catch (Exception e2) 
			{
				dbLost = true;
				NmsLogMgr.MISCERR.fail( "[ConnectionBreakHandler] will refresh connections due to Exception : "+Thread.currentThread(), e2); //No I18N
				NmsLogMgr.HAERR.fail("[ConnectionBreakHandler] : SQLException occurred in Connection on Thread : "+Thread.currentThread(), e); //No I18N
			}
		}
		if(!dbLost)
		{
			/*
			 *  Why to use stray query to check for database connection loss :
			 *  Based on error codes used above, db connection loss is checked. 
			 *  However the best way is to use a stray query is used. So that there is no dependencies on
			 *  driver, it works across database and more importantly the database is actually hit. 
			 *  
			 *  Why to spawn new thread in this process :
			 *  If there happens to be an SQLException due to an incorrect db operation on a transaction 
			 *  connection, none of the above error codes will not match. In such a scenario, 
			 *  if handleConnectionFail calls getConnection, from the same thread(transaction thread), 
			 *  the connection returned will already have some operation which is to be aborted and that is 
			 *  about to be rolled back. Hence a thread is spawned instead of using the same same connection 
			 *  to check for db connection loss. 
			 */
			
			Thread checkConnection = new Thread()
			{
				public void run()
				{
					handleConnectionFail();
				}
			};checkConnection.start();
		}
		else
		{
			handleReConnection();
		}
	}


    public void handleConnectionFail()
    {
		ResultSet rs = null;
		Connection con = null;
		try
		{
			con = ConnectionPool.getInstance().getConnection();
		}
		catch(Exception e)
		{
			// if there is any exception from getConnection... but the chances of this scenario is very very unlikely ...
			handleReConnection();
		}
		PreparedStatement stmt = null;
		try
		{
			String testQuery = HibernateUtil.getHbnProperty("testQuery");
    		if(testQuery == null)
    		{
    			testQuery = "select HOSTADDRESS from BEFailOver";// No I18N
    		}			
		/*Changed to fix Open cursor issue in Oracle*/
		stmt = con.prepareStatement(testQuery);
		stmt.execute();
		}
		catch(SQLException sqle)
		{
			NmsLogMgr.MISCERR.fail("[ConnectionBreakHandler] : Exception occurred in Connection on Thread : "+Thread.currentThread(), sqle); //No I18N
			NmsLogMgr.HAERR.fail("[ConnectionBreakHandler] : SQLException occurred in Connection on Thread : "+Thread.currentThread(), sqle); //No I18N
			handleReConnection();
		}
		finally
		{	
			if(stmt!=null)
			{
				try 
				{
					stmt.close();
				}
				catch (SQLException e1){}
			}
		}    	
    }
    
    private void handleReConnection()
    {
    	final String msg1 = NmsUtil.GetString("test.ConnectionBreakHandler.db_connection_status_lost");//"Connection between the Database server and WebNMS_BE Server is lost, trying to re-establish the connection. Please do not perform any read/write operation."; //No Internationalization
    	final String msg2 = NmsUtil.GetString("test.ConnectionBreakHandler.db_connection_status_resumed");//"Connection between the Database server and WebNMS_BE Server is resumed, so you can perform any read/write operation."; //No Internationalization
    	final String mess3 = "Connection to the Database is lost. Trying to reconnect to the Database";//No Internationalization
    	final String mess4 = "Please Wait ..."; //No Internationalization
    	final String mess5 = "Could not reconnect to database. Server is being Shut Down"; //No Internationalization
    	final String mess6 = "##########################################################"; //No Internationalization
    	final String mess7 = "Reconnected to the database";//No Internationalization

    	if(!shutcalled)
    	{
    		shutcalled=true; 
    		initParams(); 
    		try{

    			if(dbreconnection.equalsIgnoreCase("TRUE")) //No Internationalization
    			{
				try{
					acquireRetryAttempts=Integer.parseInt(HibernateUtil.getHbnProperty("hibernate.c3p0.acquireRetryAttempts"));
				}
				catch(NumberFormatException ne)
				{
					ne.printStackTrace();
				}
    				Thread DBReconnectionThread=new Thread("DBReconnection Thread") //NO I18N
    				{
    					public void run()
    					{
    						try
    						{        
    							System.out.println(mess6); 
    							SystemUtil.cout.println(mess6); 
							NmsLogMgr.HAUSER.log(mess6,Log.SUMMARY); 
    							System.err.println(mess3);  
    							System.out.println(mess3); 
							NmsLogMgr.HAUSER.log(mess3,Log.SUMMARY); 
    							SystemUtil.cout.println(mess3); 
    							SystemUtil.cout.println(mess4); 
    							System.out.println(mess4); 
							NmsLogMgr.HAUSER.log(mess4,Log.SUMMARY); 

    							NmsLogMgr.MISCERR.log(mess6,1); 
							NmsLogMgr.HAERR.log(mess6,Log.SUMMARY); 
    							NmsLogMgr.MISCERR.log(mess3,1);
							NmsLogMgr.HAERR.log(mess3,Log.SUMMARY); 

    							// Informing all the FEs about the DB Connection lost, if there is a standby server.
    							if(hasStandBy() && !isDBFailureSentToFEs)
    							{
    								broadcastMsgToFE(msg1,"SUSPEND_REQUESTS"); //No Internationalization
    								isDBFailureSentToFEs = true;
    							}

    							suspendNMS(); // suspends all the scheduler threads
    							// Additional retry is introduces as buffer for C3P0 to initialize and reconnect. 
							int i=0;
							while(true)
    							{
    								//Changed this for DBReplication support.In FE JVM,first it will try to refresh the connections with
    								//existing hibernate.cfg.xml .If an exception is thrown,(meaning the Master Database is not available)
    								//and hibernate.cfg.xml of the standby server will be copied on to classes/hbnlib directory and then refresh 
    								//connections will be done.
    								try
    								{
									if(!isDBreadyForReconnection())
									{
										closeOldConnections = false;
										i = acquireRetryAttempts+1;
										throw new Exception("Database not ready for reconnection!");
									}
    									ConnectionPool.getInstance().createSecondaryConnectionPool(PureUtils.rootDir+hbnFile); // refresh connections
									/*String url = PureServerUtils.url;
									String driverName       =       PureServerUtils.driverName;
									String username         =       PureServerUtils.userName;
									String password         =       PureServerUtils.password;
									NmsUtil.relapi          =	    new RelationalAPI(url,username,password,driverName,true);*/
									com.adventnet.nms.util.PureServerUtils.initSnmpV3Params(); //Called to re-create SNMP V3 connections
    									break;
    								}
    								catch(Exception ex)
    								{
    									if(acquireRetryAttempts <= 0 || i<acquireRetryAttempts+1)
									{
										i++;
										continue;
									}
    									try
    									{
    										if(serverType.equalsIgnoreCase("BE") || dbreplication.equalsIgnoreCase("FALSE"))
    										{
    											throw ex;
    										}
    										else
    										{
											ex.printStackTrace();
    											boolean copied=copySecondaryParams();
    											if(copied)
    											{
    												File file= new File(PureUtils.rootDir+hbnFile);
    												DBParamsParser parser= DBParamsParser.purgeInstance(file);
    												PureServerUtils.getDatabaseParams();
    												String url = PureServerUtils.url;
												NmsUtil.relapi.getConnectionPool().url=url;
												/*ConnectionPool.getInstance().url=url;*/
												com.adventnet.nms.util.PureServerUtils.initSnmpV3Params(); //Called to re-create SNMP V3 connections
    											}
    											//ConnectionPool.getInstance().createSecondaryConnectionPool(); // refresh connections
    											refresh=false;
    										}
    									}
    									catch(Exception exp)
    									{
    										throw exp;
    									}
    								}
    							}
    							checkAndResumeNMS(); // To resume Web NMS after refreshing the connections
    							shutcalled=false; 

    							// Informing all the FEs about the DB Connection re-establishment, if there is a standby server
    							if(hasStandBy() && isDBFailureSentToFEs)
    							{
    								broadcastMsgToFE(msg2,"RESUME_REQUESTS"); //No Internationalization
    								isDBFailureSentToFEs = false;
    							}
    							System.out.println(mess7); 
    							SystemUtil.cout.println(mess7);  
							NmsLogMgr.HAUSER.log(mess7,Log.SUMMARY); 
    							System.out.println(mess6);  
    							SystemUtil.cout.println(mess6);  
							NmsLogMgr.HAUSER.log(mess6,Log.SUMMARY); 
    						}
    						catch(Exception e)
    						{
    							SystemUtil.cout.println(mess5); 
    							System.out.println(mess5); 
    							NmsLogMgr.MISCERR.log(mess5,1); 
							NmsLogMgr.HAERR.log(mess5,Log.SUMMARY); 
    							PureServerUtils.backUpInProcess = false;
    							checkAndResumeNMS();
    							// Informing all the FEs about the DB Connection re-establishment, if there is a standby server
    							if(hasStandBy() && isDBFailureSentToFEs)
    							{
    								broadcastMsgToFE(msg2,"DO_FAILOVER"); //No Internationalization
    								isDBFailureSentToFEs  = false;
    							}
    							try
    							{	       
								Thread DBReconnectionThread=new Thread("DBReconnection Thread") //NO I18N
								{
									public void run()
									{
										try{
											ShutDownAPIImpl.getInstance().shutDownNMSServer(true); // On ENABLE_DBRECONNECTION set to false or null
										}
										catch(Exception e)   
										{
											e.printStackTrace();
										}
									}
								};
								DBReconnectionThread.start();
    							}
    							catch(Exception se)
    							{
    								System.err.println("Exception in Shuting Down the server   " +  se); //No Internationalization
								NmsLogMgr.HAERR.log("Exception in Shuting Down the server   " +  se,1); //No Internationalization
    							}
    						}
    					}
    				};
    				DBReconnectionThread.start();

    			}
    			else
    			{
    				displayShutDownMessages();
    				initiateShutDown();
    			}
    		}
    		catch(NumberFormatException nfe)
    		{
    			displayShutDownMessages();
    			initiateShutDown();
    		}
    		catch(Exception e)	
    		{
    			System.err.println("Exception in Shuting Down the server   " +  e); //No Internationalization
    			NmsLogMgr.HAERR.log("Exception in Shuting Down the server   " +  e,1); //No Internationalization
    		}
    	}
    }

    private void displayShutDownMessages()
    {
    	final String mess1 = "Connection to the Database is lost. Server is being Shut Down";//No Internationalization
        final String mess2 = "Please Restart the server"; //No Internationalization
        final String mess3 = NmsUtil.GetString("Connection to the Database is lost. Distributed Poller is being Shut Down"); //No I18N
        final String mess4 = NmsUtil.GetString("Please Restart the distributed poller"); //No I18N
        final String mess5 = "##########################################################"; //No Internationalization
        
    	SystemUtil.cout.println(""); //No I18N
        SystemUtil.cout.println(mess5); 
        if(NmsUtil.getServerType().equalsIgnoreCase("REMOTE_POLLER"))
        {
        	SystemUtil.cout.println(mess3); 
        	SystemUtil.cout.println(mess4);
		NmsLogMgr.HAUSER.log(mess5,Log.SUMMARY); 
		NmsLogMgr.HAUSER.log(mess3,Log.SUMMARY); 
		NmsLogMgr.HAUSER.log(mess4,Log.SUMMARY); 
        }
        else
        {
        	SystemUtil.cout.println(mess1); 
        	SystemUtil.cout.println(mess2);
		NmsLogMgr.HAUSER.log(mess5,Log.SUMMARY); 
		NmsLogMgr.HAUSER.log(mess1,Log.SUMMARY); 
		NmsLogMgr.HAUSER.log(mess2,Log.SUMMARY); 
        }
        SystemUtil.cout.println(mess5);
	NmsLogMgr.HAUSER.log(mess5,Log.SUMMARY); 

        NmsLogMgr.MISCERR.log(mess5,1);
        if(NmsUtil.getServerType().equalsIgnoreCase("REMOTE_POLLER"))
        {
        	NmsLogMgr.MISCERR.log(mess3,1);
        	NmsLogMgr.MISCERR.log(mess4,1);
		NmsLogMgr.HAERR.log(mess3,Log.SUMMARY); 
		NmsLogMgr.HAERR.log(mess4,Log.SUMMARY); 
        }
        else
        {
        	NmsLogMgr.MISCERR.log(mess1,1);
        	NmsLogMgr.MISCERR.log(mess2,1);
		NmsLogMgr.HAERR.log(mess1,Log.SUMMARY); 
		NmsLogMgr.HAERR.log(mess2,Log.SUMMARY); 
        }
        NmsLogMgr.MISCERR.log(mess5,1);
	NmsLogMgr.HAERR.log(mess5,Log.SUMMARY); 
    }
   
    private void initiateShutDown()
    {
	    if(NmsUtil.getServerType().equalsIgnoreCase("REMOTE_POLLER"))
	    {
		    try
		    {
			    Class pollerUtil = Class.forName("com.adventnet.nms.poll.PollerUtil");
			    Method getPoller = pollerUtil.getMethod("getPoller",new Class[]{});
			    Object poller = getPoller.invoke(null,new Object[]{});
			    Method shutDown = poller.getClass().getMethod("shutDown",new Class[]{});
			    shutDown.invoke(poller,new Object[]{});
		    }
		    catch(Exception e)
		    {
			    e.printStackTrace();
		    }
	    }
	    else
	    {
		    try {
			    //RelationalAPI.disconnect();
			    ConnectionPool.getInstance().disconnectAll(closeOldConnections);
		    } catch (Exception e1) {
			    System.err.println("ConnectionBreakHandlerImpl:initiateShutDown: Error in ConnectionPool.disconnectAll()  "+e1);
			    NmsLogMgr.HAERR.log("ConnectionBreakHandlerImpl:initiateShutDown: Error in ConnectionPool.disconnectAll()  "+e1,1);
			    e1.printStackTrace();
		    }
		    try
		    {	       
			    Thread DBReconnectionThread=new Thread("DBReconnection Thread") //NO I18N
			    {
				    public void run()
				    {
					    try{
						    ShutDownAPIImpl.getInstance().shutDownNMSServer(true); // On ENABLE_DBRECONNECTION set to false or null
					    }
					    catch(Exception e)   
					    {
						    e.printStackTrace();
					    }
				    }
			    };
			    DBReconnectionThread.start();
		    }
		    catch(Exception se)
		    {
			    System.err.println("Exception in Shuting Down the server   " +  se); //No Internationalization
			    NmsLogMgr.HAERR.log("Exception in Shuting Down the server   " +  se,1); //No Internationalization
		    }
	    }
    }

    private void log(String logMsg)
    {
        SystemUtil.cout.println(logMsg); 
        NmsLogMgr.MISCERR.log(NmsUtil.GetString(" [ConnectionBreakHandler] :: ") +logMsg,1); //No Internationalization
        NmsLogMgr.HAERR.log(NmsUtil.GetString(" [ConnectionBreakHandler] :: ") +logMsg,1); //No Internationalization
    }



	private void suspendNMS()
    {
  		NmsLogMgr.MISCUSER.log(NmsUtil.GetString("Recreation of Database Connections in Web NMS is started. All the schedulers would be suspende during this operation."),Log.SUMMARY); 
  		NmsLogMgr.HAUSER.log(NmsUtil.GetString("Recreation of Database Connections in Web NMS is started. All the schedulers would be suspende during this operation."),Log.SUMMARY); 

        // Stop any write requests to sessions
        PureServerUtils.backUpInProcess_Session = true;

        // Suspend all the Schedulers.
        if (!Scheduler.suspendAll())
        {
            NmsLogMgr.MISCERR.fail(NmsUtil.GetString(" [ConnectionBreakHandler] : Problem in suspending Schedulers"),null); //No Internationalization
            NmsLogMgr.HAERR.fail(NmsUtil.GetString(" [ConnectionBreakHandler] : Problem in suspending Schedulers"),null); //No Internationalization
        }

        // Here sleep for a period of 1 secs to prevent
        // any inconsistancy after stopping Scheduler.
        /*
        try
        {
            Thread.sleep(1000);
        }
        catch(InterruptedException ie){}
        */
        //Turn on the variable "BackUpInProcess" for all the API's to true.
        PureServerUtils.backUpInProcess = true ;
    }


    public static boolean checkAndResumeNMS()
    {
        if(Scheduler.isSuspended())
        {
            try
            {
                //Set the variables back to false.
                PureServerUtils.backUpInProcess_Session = false;
                PureServerUtils.backUpInProcess = false;
                // Resume all the schedulers again.
                Scheduler.resumeAll();
                NmsLogMgr.MISCUSER.log(NmsUtil.GetString(" [ConnectionBreakHandler] : Database Connections are recreated in WebNMS. All the schedulers are resumed now."),Log.SUMMARY); //No Internationalization
                NmsLogMgr.HAUSER.log(NmsUtil.GetString(" [ConnectionBreakHandler] : Database Connections are recreated in WebNMS. All the schedulers are resumed now."),Log.SUMMARY); //No Internationalization
                return true;
            }
            catch(Throwable thr)
            {
                NmsLogMgr.MISCERR.fail(NmsUtil.GetString(" [ConnectionBreakHandler] : Problem in resuming the schedulers of Web NMS, during Database Connection recreation : "), thr); //No Internationalization
                NmsLogMgr.HAERR.fail(NmsUtil.GetString(" [ConnectionBreakHandler] : Problem in resuming the schedulers of Web NMS, during Database Connection recreation : "), thr); //No Internationalization
	    }
            return false;
        }
        NmsLogMgr.MISCUSER.log(NmsUtil.GetString(" [ConnectionBreakHandler] : Schedulers of Web NMS are not in suspended state"), Log.SUMMARY); //No Internationalization
        NmsLogMgr.HAUSER.log(NmsUtil.GetString(" [ConnectionBreakHandler] : Schedulers of Web NMS are not in suspended state"), Log.SUMMARY); //No Internationalization
        return true;
    }

    /*
     * This method is used to send the Connection status between the NMS_BE_Server and the DB_Server. 
     * i.e if the connection is down, information will be sent to all the FEs, which inturn send them to all
     * the clients connected to it, also message will be sent to all the FE's if the connection is resumed,
     * else the failover info will be sent to all the FEs to failover to the STANDBY Server.
     */
    private void broadcastMsgToFE(String msg, String type)
    {
        try
            {
                ByteArrayOutputStream bo = new ByteArrayOutputStream();
                DataOutputStream dop = new DataOutputStream(bo);
                dop.writeInt(DB_RECONNECT);
                dop.writeUTF(msg);
                dop.writeUTF(type);
                
                byte data[] = bo.toByteArray();
                //UpdateMaintainer.send("BROADCAST_FROM_CLIENT",unique_id,data);
                Vector mssbes = UpdateMaintainer.getAllMssbe();
                for (Enumeration e = mssbes.elements(); e.hasMoreElements();)
                    {
                        ((MainSocketSessionBE)e.nextElement()).scheduleSend("BROADCAST_FROM_CLIENT", unique_id, data); //No Internationalization
                    }
            }catch(IOException ioe)
                {
                    System.err.println(" Exception while sending the DB Reconnection details to the FEs : "+ioe); //No Internationalization
                    NmsLogMgr.HAERR.log(" Exception while sending the DB Reconnection details to the FEs : "+ioe,1); //No Internationalization
                    ioe.printStackTrace();
                }
    }
    
    /*
      This method is to check whether the BE Server has any Standby server for Failover. If any of the BE Server refisters itself as a STANDBY server to the BE Primary server, then the FE_SERVER_TYPE of the the STANDBY Server will be "STANDBY". This is not be applicable to the STANDBY Server which does not have the FE Server within it, i.e in the STANDBY Server, if the FE Server part is disabled.
     */
    private boolean hasStandBy()
    {
        Vector feList = UpdateMaintainer.getFEList();
        int size = feList.size();
        for(int i = 0 ; i < size ; i++)
            {
                Properties p = (Properties)feList.elementAt(i);
                String type = p.getProperty("FE_SERVER_TYPE"); //No Internationalization
                if(type != null && type.equalsIgnoreCase("STANDBY")) //No Internationalization
                    {
                        return true;
                    }
            }
        return false;
    }
    public void preBEFailOverNotification(BEFailOverEvent beFailOverEvent)
    {
    }
    //This is primarily done to handle the scenario where the DB1 to which PrimaryServer is connected is up
    //However when the PrimaryServer gets killed,FE stills connects to DB1.The Stanby server which then
    //subsequently comes up will be connected to the replicated DB2.This can lead to inconsistency where
    //the FE and BE will be connected to differentDB.Hence FE needs to connect to DB2 when PrimaryServer
    //goes down.

    public void postBEFailOverNotification(BEFailOverEvent beFailOverEvent)
    { 
        if(!refresh)
        {
            return;
        }
        initParams();
	if(!isDBreadyForReconnection())
	{
		closeOldConnections = false;
	}
        if(dbreplication.equalsIgnoreCase("TRUE")&&(dbreconnection.equalsIgnoreCase("TRUE"))) //No Internationalization
        {            
            try
            {
                suspendNMS(); // suspends all the scheduler threads
                boolean copied=copySecondaryParams();
                if(copied)                                                                                                                  {
                    File file= new File(PureUtils.rootDir+hbnFile);//No Internationalization
                    DBParamsParser parser= DBParamsParser.purgeInstance(file);
                    PureServerUtils.getDatabaseParams();
                    String url = PureServerUtils.url;
                    NmsUtil.relapi.getConnectionPool().url=url;
		    /*String driverName       =       PureServerUtils.driverName;
		    String username         =       PureServerUtils.userName;
		    String password         =       PureServerUtils.password;
		    NmsUtil.relapi          =	    new RelationalAPI(url,username,password,driverName,true);*/
		    com.adventnet.nms.util.PureServerUtils.initSnmpV3Params(); //Called to re-create SNMP V3 connections
                }
                ConnectionPool.getInstance().createSecondaryConnectionPool(PureUtils.rootDir+hbnFile,closeOldConnections); // refresh connections
                refresh=true;
                checkAndResumeNMS(); // To resume Web NMS after refreshing the connections

            }catch(Exception e)
            {
                checkAndResumeNMS();
            }
        }
    }

//Added for DBReplication support.The hibernate.cfg.xml of the standby server will be copied into FE under <WebNMS Home>/classes/hbnlib/secondary directory.This method simply copies(or renames) the hibernate.cfg.xml  from the secondary folder to <WebNMS Home>/classes/hbnlib directory.

    private boolean copySecondaryParams()
    {
        File sFile=new File(PureUtils.rootDir+"classes/hbnlib/secondary/hibernate.cfg.xml");
        boolean copy=false;
        FileInputStream fis=null;
        FileOutputStream fos=null; 
        if(!sFile.exists())
        {
            return copy;
        }
        File dFile= new File(PureUtils.rootDir+hbnFile);
        try
        {
            int len;
            byte[] barr= new byte[1024];
            fis= new FileInputStream(sFile);
            fos = new FileOutputStream(dFile);
            while ((len = fis.read(barr)) > 0) 
            {
               fos.write(barr, 0, len);
            }
            fos.close();
            fis.close();
            copy=true;
            return copy;
        }
        catch(Exception e)
        {
            System.err.println("Exception in Copying the file hibernate.cfg.xml file of Standy Server"+e.getMessage());
            NmsLogMgr.HAERR.log("Exception in Copying the file hibernate.cfg.xml file of Standy Server"+e.getMessage(),1);
            e.printStackTrace();
            return copy;
        }
        finally
        {
            try
            {
                if(fis!=null)
                {
                    fis.close();
                }
                if(fos!=null)
                {
                    fos.close();
                }
            }catch(Exception exp){}


        }
    }
    private boolean isDBreadyForReconnection(){
	    String dbHost = "localhost"; //NO I18N
	    int dbPort =  3306;
	    Socket dbSocket = null;
	    int i=0;
	    try
	    {
		    dbHost = DBParamsParser.getInstance().getDBHost();
		   //########################################### CITRIX CHANGE FOR DBPORT PARSER ISSUE
		   // dbPort = Integer.parseInt(DBParamsParser.getInstance().getDBPort());
		    dbPort = 1521;
		   //########################################### CITRIX CHANGE FOR DBPORT PARSER ISSUE ENDS
		    while(i < acquireRetryAttempts)
		    {
			    try{
				    dbSocket = TimedSocket.getSocket(dbHost, dbPort, dbreconnection_timeout);
			    }
			    catch(Exception exp)
			    {
				    exp.printStackTrace();
				    //return false; //FALSE to be returned only after acquireRetryAttempts is finished
			    }
			    if(dbSocket != null)
			    {
				    try{dbSocket.close();}catch(Exception ex){}
				    return true;
			    }
			    i++;
			    Thread.sleep(dbreconnection_timeout);
		    }
		    return false;
	    }
	    catch(Exception ex)
	    {
		    ex.printStackTrace();
		    return false;
	    }
    }

  // This thread is to register the ConnectionBreakHandlerImpl as a BEFailOverListener after all
  // the FE modules have initialised.
  class FailOverListenerThread implements Runnable
  {
      ConnectionBreakHandlerImpl connectionBrkHan;
      FailOverListenerThread(ConnectionBreakHandlerImpl cb)
      {
          connectionBrkHan=cb;
          Thread failoverListenerThread = new Thread(this);
          failoverListenerThread.setName("FAILOVER-LISTENER");
          failoverListenerThread.start();

      }
      public void run ()
      {
          try
          {              
                  while(!NmsMainFE.isStarted)
                  {
                      try
                      {
                          Thread.sleep(100);
                      }catch(InterruptedException e){}
                  }
                  PureServerUtilsFE.clientSocketFE.registerBEFailOverListener(connectionBrkHan);

              
          }
          catch(Exception e)
          {
              System.err.println("Exception While Trying To Register ConnectionBreakHandler As BEFailoverListener"+e.getMessage());
              NmsLogMgr.HAERR.log("Exception While Trying To Register ConnectionBreakHandler As BEFailoverListener"+e.getMessage(),1);

          }
      }
  }

}
