/* $Id: FilterActionBaseImpl.java,v 1.2 2010/10/29 13:45:49 swaminathap Exp $ */

package com.adventnet.nms.eventdb;
//java imports
import java.util.*;
import java.lang.Integer;
import java.util.Enumeration;
import java.sql.*;
import org.json.JSONObject;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;

//Nms imports
import com.adventnet.nms.store.NmsStorageException;
import com.adventnet.nms.util.NmsLogMgr;
import com.adventnet.nms.util.NmsUtil;
import com.adventnet.management.log.Log;
import com.adventnet.management.transaction.ConnectionPool;
import com.adventnet.management.transaction.PreparedStatementWrapper;
import com.adventnet.management.transaction.UserTransactionException;
import com.adventnet.nms.alertdb.Alert;
import com.adventnet.nms.store.relational.RelationalUtil;
/**
 * This is base class for FilterActionInterface.Once we convert the actions object from the event/alert ,we will put into the In Queue.
 * The Processing thread will take  objects from input queue and it will execute the corresponding filter action.We have a parameter 
 * which will check if the InQ has reached maximum size ,we will copy all actions from InQ to Database table.while processing the actions will be taken 
 * from database first and then it will be processed from the queue. 
 */

public abstract class FilterActionBaseImpl implements FilterActionInterface
{
	 private int psIDToInsert;
	 private int psIDToSelect;
	 private int psIDToVerify;
	 private int psIDToDelete;
	 private int psIDToMax;		
	
	 //adding events/alerts to queue		
	 private Vector inQForFilter = new Vector();
	 private Vector outQForFilter = new Vector();
	
	 // sync variable
	 private Integer syncInQ= new Integer(1);	
	
	//Batch update variable
	private boolean isBatchUpdate=false;
	private boolean isBatchUpdateSupportedForDB = false;   //if database support the batch update
	private int batchSize=60; //default batch size	
	private int processBatchSize=500;
	
	//variable for buffer mechanism
	private int startId=0;
	
	private boolean isDataAvailable=true;
	private boolean isFirstTime=true;
	private static int stId=0;
	private static int enId=0;
	
	protected String tableName=null;  //we can store table name in this variable ,this can be used while log the error message ,we can put the table name aslo
	protected String type=null;	
	private FilterAction filterActionObj=null;
	
	abstract public void addAlertToInQ(Alert alt);
	abstract public void addEventToInQ(Event event);
	abstract public void schedule() ;	
	abstract public void initialize();	
	
	/* initialize() By using this method ,one can initialise the start up variable here.
	 * 
	 */
	
	/* In case you are using our buffer mechanism ,you need to initialize startup variable ,for that the extended classes should call this
	 * method by passing the tableName and columnName. We have used two column for the filter action table.One is identifier and other one is 
	 * will have action object.the ID column name will be come for the all the table.you can specify the other column which ever you want.  
	 */
	public void initializeFilterAction(String className,String type,String tableName,String columnName)
	{
		String psIDToInsertProps="insert into "+tableName+" values(?,?)"; //No I18N
		String psIDToSelectProps="select "+RelationalUtil.getAlias(columnName)+" from "+tableName+" where ( ID BETWEEN "+"?"+" AND "+"?"+") order by ID"; //No I18N
		String psIDToVerifyTable="select count(*) from "+tableName+"" ;//No I18N
		String psIDToDeleteProps="delete from "+tableName+" where ( ID  BETWEEN "+"?"+" AND "+"?"+")"; //No I18N
		String psIDToMaxid="select MAX(ID) from "+tableName+""; //No I18N
		ConnectionPool cp=ConnectionPool.getInstance();
		psIDToInsert=cp.getPreparedStatementID(psIDToInsertProps);
		psIDToSelect=cp.getPreparedStatementID(psIDToSelectProps);
		psIDToVerify=cp.getPreparedStatementID(psIDToVerifyTable);
		psIDToDelete=cp.getPreparedStatementID(psIDToDeleteProps);
		psIDToMax=cp.getPreparedStatementID(psIDToMaxid);
		this.type=type;	
		this.tableName=tableName;
		//Batch details	
		batchSize=EventMgr.emgr.batchSize;	
		processBatchSize=EventMgr.emgr.MAX_FILTER_ACTION_DATA_QUEUE;
		
		isBatchUpdateSupportedForDB=NmsUtil.isBatchUpdateSupported();
		isBatchUpdate=EventMgr.emgr.isBatchUpdate();
		
		// while starting the server (warm start),we need to read the database whether any action are available in the database,if its there,we need to find 
		//the maximum value of ID and set it to startId. 		
		startId=getMaxID();		
		//to get the filteraction  object
		try
		{
			
			filterActionObj = (FilterAction)Class.forName(className).newInstance();
 
		}
		catch(Exception es)
		{
			System.err.println("FilterActionBaseImpl: Error loading a class"+className ); //No I18N
		}	
		
	}
	
	//to find the max id from the database,
	/* while starting the server (warm start),we need to read the database whether any action are available in the database,if its there,we need to find 
	the maximum value of ID and set it to startId. 	*/	
	private int getMaxID()
	{
		int id=0;
		ResultSet rs = null;
		PreparedStatementWrapper ps = null;
		ConnectionPool cp=null;
		try
		{	
			cp=ConnectionPool.getInstance();			
			ps = cp.fetchPreparedStatement(psIDToMax);
			PreparedStatement selectToMaxID = ps.getPreparedStatement();
			if (selectToMaxID!=null)
			{
				rs = cp.executeQuery(selectToMaxID);
				while (rs.next())
				{
					id=rs.getInt(1);

				}                
			}        

		}		
		catch( NmsStorageException nse )
		{
			NmsLogMgr.MISCERR.fail(NmsUtil.GetString("NmsStorageException while fetching the maximum id from the "+tableName+" Table - getMaxId()"),nse);  //No I18n			
		}
		catch(Exception ex)
		{
			NmsLogMgr.MISCERR.fail(NmsUtil.GetString("Exception while fetching the max id from the "+tableName+" Table-getMaxId() "), ex); //No I18N
		}
		finally
		{
			try 
			{
				if(rs!=null)
				{
					rs.close ();
				}
				if ( ps != null)
				{
					cp.returnPreparedStatement(ps);
				}
				
			}
			catch (Exception e) 
			{ 
				// no need to print the exception here				
			}
		}

		return id;
	}
	/* To get total of row in the database table
	 */
	
	private int getNumberOfRows()
	{
		ResultSet rs = null;
		PreparedStatementWrapper ps = null;
		int totalRow=0;
		ConnectionPool cp=null;
		try
		{		
				cp=ConnectionPool.getInstance();	
				ps = cp.fetchPreparedStatement(psIDToVerify);            
				PreparedStatement selectPropToVerify = ps.getPreparedStatement();
            
				if (selectPropToVerify!=null)
				{		            	          
					rs = cp.executeQuery(selectPropToVerify);
            	 
            	  	while (rs.next())
	                {
            		  totalRow=rs.getInt(1);
            	
	                }                
				}					            
            
        }			
	    catch( NmsStorageException nse )
	    {
	    	 NmsLogMgr.MISCERR.fail(NmsUtil.GetString("NmsStorageException while getting total actions from the "+tableName+" Table  -moveToOutQForFilter)"),nse); //No I18N
        }
        catch(Exception ex)
	    {
        	 NmsLogMgr.MISCERR.fail(NmsUtil.GetString("Exception while getting total email actions from the "+tableName+" Table -moveToOutQForFilter "), ex);//No I18N
	        
        }
        finally
	      {
	            try 
	            {
	            	if(rs!=null)
	            	{
	            		rs.close();
	            	}
	                if ( ps != null)
	                    cp.returnPreparedStatement(ps);
	               
	            }
	            catch (Exception exe) 
	            {
	            	//need not print exceptions here
	            }
	      } 
        return totalRow;
       
	} 
		
	// 	start the thread
	/* This method will be used to process the filter action.In this is method we will copy the action from input queue to output queue and then 
	 * action will be executed by iterating the output queue.Since this is abstract class ,User can extend this only when they want to use our buffer
	 * mechanism.They should pass args as filteraction and type of the object whether its event or alert. 
	 */
	public void runAction()
	{	
		try
		{	
				moveToOutQForFilter();  //  copy the actions from inQ to outQ				
				processAndExecuteAction();					
			
		}	
		catch(Exception anye)
		{
			NmsLogMgr.MISCERR.fail(NmsUtil.GetString("Exception in "+tableName+".run() method Thread ")+anye,anye) ;// No I18N
		}
					
	}
	
	/*	Add the  events/alert to input filter queue if the queue is reached maximum size ,we will copy the actions from input queue to database table */	
	public Properties addObjectToInQ(Properties prop)
	{						
		synchronized(syncInQ)
		{
			int queueSize=inQForFilter.size();	 // finding inQ size
			
			if(queueSize>=EventMgr.emgr.MAX_FILTER_ACTION_DATA_QUEUE)
			{
				 //adding current action object to the queue					
			
				copyInQToDB();					
				isDataAvailable=true;					
				inQForFilter.addElement(prop);  
			}
			else
			{
				inQForFilter.addElement(prop);

			}
		}		
		return prop;

	}
	
	//Moves the  Actions Object from input Q to output Q 
	private  void moveToOutQForFilter() 
	{	
		
		//Vector inQCopyForFilter=new Vector();
		int totalRow=0;		 //to find total row in the database.
		String value=null;		
		int copystId=0;
		int copyenId=0;			
		boolean isCopied=false;
			 if(isDataAvailable)   //We will query the SendEmailEventAction table only when there is any actions available in the table
			 {						 
				totalRow=getNumberOfRows();
			
				if(isFirstTime && totalRow!=0)
				{													
					int iden=getMaxID();
					int startid=iden-totalRow;
					
					if(totalRow>=processBatchSize)
					{
						stId=startid+1;
						
						enId=startid+processBatchSize;
					}
					if(totalRow<processBatchSize)
					{
						stId=startid+1;
						enId=startid+totalRow;
						
					}
					isFirstTime=false;
				}
				else if(totalRow!=0)
				{				
					if(totalRow>=processBatchSize)
					{
						stId=enId+1;
						enId=enId+processBatchSize;
					}
					if(totalRow<processBatchSize)
					{
						stId=enId;
						enId=enId+totalRow;
						
					}
				}
				copystId=stId;
				copyenId=enId;
				
			} 
			/* First  we have to check if any actions available in the database ,we have copy the actions from SendEmailEventAction table to outQ		 
			 */	
			 if(totalRow>0)        
			 {							
					NmsLogMgr.MISCERR.log("Started copying the action objects from the "+tableName+" table to cache",Log.DEBUG); //No Internationalisation
				
					/* Fetch the actions from the database and copying to the outQ*/
					ResultSet rs = null;
				    PreparedStatementWrapper ps = null;
				    ConnectionPool cp=null;	 
							try
							{
									
								cp=ConnectionPool.getInstance();	
								ps = cp.fetchPreparedStatement(psIDToSelect);						 
								PreparedStatement getProps = ps.getPreparedStatement();   							
								if (getProps!=null)
								{
									getProps.setInt (1,stId);
									getProps.setInt(2,enId);				                
									rs = cp.executeQuery(getProps); 				               
									Properties p1=null;					               
									while (rs.next())
									{
									value=rs.getString(1); 						               
					                p1=getPropsFromJSON(value);		 			                				 			   	     
						 		    outQForFilter.addElement(p1);				   
									}       				
								}
							  
								isCopied=true;	   
							}							
							catch( NmsStorageException nse )
							 {		    	
					    	   NmsLogMgr.MISCERR.fail(NmsUtil.GetString("NmsStorageException while copying the filter actions from "+tableName+" table to outQ -moveToOutQForFilter)"),nse);  //No I18N
					    	   rollback(NmsUtil.GetString("Database Exception occured while adding actions to the "+tableName+" Table ")+ nse.getMessage());//No I18N
					    	   stId=copystId;
					    	   enId=copyenId;
					    			
							 }
						     catch( UserTransactionException ute )
					    	 {
						    	 NmsLogMgr.MISCERR.fail(NmsUtil.GetString("Transaction Exception while copying the filter actions from "+tableName+" Table to outQ -moveToOutQForFilter "),ute);//No I18N
						         stId=copystId;
				    			 enId=copyenId;
					    	 }
						     catch(Exception ex)
							 {				
								 NmsLogMgr.MISCERR.fail(NmsUtil.GetString("Exception while copying the filter actions from "+tableName+" Table to outQ -moveToOutQForFilter "), ex);//No I18N
								 rollback(NmsUtil.GetString("Exception occured while adding actions to the "+tableName+" Table ") + ex.getMessage());//No I18N
								 stId=copystId;
					    		 enId=copyenId;
							 }
						     
							finally
							{
								 try 
								 {
								     if(rs!=null)
								     {          	
								        rs.close ();
								     }
								     if ( ps != null)
								     {
								      cp.returnPreparedStatement(ps);
								     }          
								  }
								  catch (Exception exe) 
								  {
								     //	System.err.println("Exception in finally block in moveToOutQForFilter method"+exe); //No I18N
								  }
							} 		
						 /*Once we copy the actions from the database ,we are deleting the same actions from the database*/	
					if(isCopied)
					{
						try
						{
						   begin(-1);			
						   ps = cp.fetchPreparedStatement(psIDToDelete);							 
						   PreparedStatement deleteProps = ps.getPreparedStatement();   
						   if (deleteProps!=null)
					       {
					           deleteProps.setInt (1,stId);
					           deleteProps.setInt(2,enId);					                
					           deleteProps.executeUpdate();
					        }						 	
											              
		                  commit();  
		                  NmsLogMgr.MISCERR.log("Completed copying the action objects from the "+tableName+" table to cache",Log.DEBUG); //No Internationalisation

		            
			         }  			  		    			  
		    	
				    catch(Exception ex)
				    {				
					 NmsLogMgr.MISCERR.fail(NmsUtil.GetString("Exception while copying the filter actions from "+tableName+" Table to outQ -moveToOutQForFilter "), ex);//No I18N
					 rollback(NmsUtil.GetString("Exception occured while adding actions to the "+tableName+" Table ") + ex.getMessage());//No I18N
					 stId=copystId;
		    		 enId=copyenId;
				    }
				   finally
				   {
					 try 
					 {
					     if(rs!=null)
					     {          	
					        rs.close ();
					     }
					     if ( ps != null)
					     {
					      cp.returnPreparedStatement(ps);
					     }          
					  }
					  catch (Exception exe) 
					  {
					     //	System.err.println("Exception in finally block in moveToOutQForFilter method"+exe); //No I18N
					  }
				 } 		
			}
		         //once we copy the action from the database ,we need to copy the actions from InQ			
			}
	
			else
			{
			 synchronized(syncInQ)
		     {
				 // we will copy the actions from inQ to outQ and emptying the inQ					
				isDataAvailable=false;
				outQForFilter = inQForFilter;
				inQForFilter = new Vector();		
				
		     }		 
			}   
	}
	/* Incase if you want to clean filter action table ,you can use this method
	 */
	public void  cleanFilterActionTable()
	{
		
		int totalRow=0;		 //to find total row in the database.
		int startId=0;
		int endId=0;
	
		PreparedStatementWrapper ps = null;
		ConnectionPool cp=null;	 	
	    if(isDataAvailable)   //We will query the Filter Action table only when there is any actions available in the table
		{		
		  totalRow=getNumberOfRows();				
		  int iden=getMaxID();		 				
		  int beginId=iden-totalRow;				 
          int iter=0;
          int remain=0;
          cp=ConnectionPool.getInstance();	
          iter = totalRow /batchSize;
          remain = totalRow %batchSize;

          try
          {
             begin(-1);                
                 
             for(int i=1;i<=iter;i++)
             {
               startId=beginId+1;
               endId=beginId+batchSize;
               beginId=endId;           
               ps = cp.fetchPreparedStatement(psIDToDelete);						 
			   PreparedStatement deleteProps = ps.getPreparedStatement();   
			   if (deleteProps!=null)
			   {
				   deleteProps.setInt (1,startId);
				   deleteProps.setInt(2,endId);					                
				   deleteProps.executeUpdate();
			   }                 
				
             }
             if(remain!=0)
             {
               	  startId=beginId+1;
                  endId=beginId+remain;
                  ps = cp.fetchPreparedStatement(psIDToDelete);					 
				  PreparedStatement deleteProps = ps.getPreparedStatement();   
				  if (deleteProps!=null)
			      {
			         deleteProps.setInt (1,startId);
			         deleteProps.setInt(2,endId);					                
			         deleteProps.executeUpdate();
			             
			      }		 
				 	
             }
             commit();  
           
		            
          } 		    			  
		    		
		  catch(Exception ex)
		  {
						
			NmsLogMgr.MISCERR.fail(NmsUtil.GetString("Exception while copying the filter actions from "+tableName+" Table to outQ -moveToOutQForFilter "), ex);//No I18N
		    rollback(NmsUtil.GetString("Exception occured while adding actions to the "+tableName+" Table ") + ex.getMessage());//No I18N
					    
		  }
		  finally
		  {
			try 
			{
			
		       if ( ps != null)
		       {
		    	 cp.returnPreparedStatement(ps);
		       }            
			}
			catch (Exception exe) 
			{
					            //	System.err.println("Exception in finally block in moveToOutQForFilter method"+exe); //No I18N
			}
			
		  }	
		} 		
		
	}
	
	
	private void copyInQToDB()
	{	
				int queueSize=inQForFilter.size();	 // finding inQ size						
				JSONObject js = null;	
				Properties actionProp;			
				int count = 0;
				PreparedStatementWrapper ps = null;
				ConnectionPool cp=null;	
				int copyId=startId;					
				begin(-1);
				NmsLogMgr.MISCERR.log("Started copying the action objects from the cache to the "+tableName+"Table",Log.DEBUG); //No Internationalisation
				try
				{
					
					cp=ConnectionPool.getInstance();	
					ps = cp.fetchPreparedStatement(psIDToInsert); 
					PreparedStatement insertPropToTable=null;
					for(int i=0;i<queueSize;i++)
					{
						startId++;
						actionProp=(Properties)inQForFilter.elementAt(i);
						js=new JSONObject(actionProp);  //constructing JSON object from actions properties.					
						insertPropToTable = ps.getPreparedStatement();
						insertPropToTable.setInt (1,startId);
						insertPropToTable.setString(2,js.toString());    
						actionProp=null;
						if(isBatchUpdateSupportedForDB && isBatchUpdate)
						{
							count++;
							if(count == batchSize)
							{
								insertPropToTable.addBatch();
								insertPropToTable.executeBatch();								
								count = 0;
							}
							else
							{
								insertPropToTable.addBatch();
							}	
						}
						else
						{
							insertPropToTable.execute();
						}
						js=null;
					}

					if ( (isBatchUpdateSupportedForDB && isBatchUpdate) && (count != 0))
					{
						cp.executeBatch(insertPropToTable);
					}

					commit();  
				}							    
				catch( NmsStorageException nse )
				{
					NmsLogMgr.MISCERR.fail(NmsUtil.GetString("NmsStorageException while adding filter actions into "+tableName+" Table - CopyInQToDB()"),nse); //No I18N
					rollback(NmsUtil.GetString("Database Exception occured while adding actions to the "+tableName+" Table ") + nse.getMessage());//No I18N
					startId=copyId;
				}
				catch( UserTransactionException ute )
				{
					NmsLogMgr.MISCERR.fail(NmsUtil.GetString("Transaction Exception while adding actions into the database - CopyInQToDB()"),ute);//No I18N
					startId=copyId;
				}
				catch(Exception ex)
				{
					NmsLogMgr.MISCERR.fail(NmsUtil.GetString("Exception while adding actions into the "+tableName+" Table -CopyInQToDB()"),ex);//No I18N
					rollback(NmsUtil.GetString("Exception occured while adding actions to the "+tableName+" Table ") + ex.getMessage());//No I18N
					startId=copyId;
				}
				finally
				{
					try 
					{
						if ( ps != null)
						cp.returnPreparedStatement(ps);						
					}
					catch (Exception exe) 
					{ 
					//need not print the exception here.
					}
				}			 
				inQForFilter.removeAllElements();		//we copied all the actions from inQForFilter to database ,we can remove the elements in inQForFilter 
			  NmsLogMgr.MISCERR.log("Completed copying the action objects from the cache to the "+tableName+" Table",Log.DEBUG); //No Internationalisation

		}		
		
	
	
	private void processAndExecuteAction()
	{		
	
		try
		{
			for	(Enumeration en = outQForFilter.elements();en.hasMoreElements();) 
			{
				Properties prop = (Properties)en.nextElement();				
				
					try
					{			
						filterActionObj.setProperties(prop);
						filterActionObj.runAction(type); //No I18N  //pass event/alert as argument,coz SendEmail has runAction(String) which is used to run both event and alert action									
					
					}
					catch(Exception anye)
					{
						NmsLogMgr.MISCERR.fail(NmsUtil.GetString("Exception in "+tableName+" method while executing filter action from output queue")+anye,anye) ; // No I18N

					}							
			
			
			}
		outQForFilter.removeAllElements();		
		
		}	
		catch(Exception anye)
		{
			NmsLogMgr.MISCERR.fail(NmsUtil.GetString("Exception in "+tableName+" in method Thread ")+anye,anye) ;// No I18N
		}
			
		
	}


	
// Need to check before the shutdown the server ,whether we need to execute the filter action available in output queue based on parameter gracefulShutDownFilterAction 	
	
	public void shutDown()
	{		
		 while(EventMgr.emgr.gracefulShutDownFilterAction  && (inQForFilter.size() > 0 || outQForFilter.size()>0))
         {          
		     NmsLogMgr.MISCERR.log("Before shutdown the server,we are executing filter action",Log.VERBOSE); //No Internationalisation
		     	synchronized(syncInQ)
				{
		    	 copyInQToDB();		   
				}
		     processAndExecuteAction();  //executing the email action only if there is action in the outQForFilter		
			 try
			 {
				 Thread.sleep(500);
			 }
			 catch(Exception e) 
			 {
				 NmsLogMgr.MISCERR.fail(NmsUtil.GetString("Exception  while  shut down the  Server while executing the filter action in separate thread  "), e); //No I18N

			 }
        }
	}
	
	private void begin(int timeOut)
	{
		try 
		{	ConnectionPool cp=ConnectionPool.getInstance();		
			cp.getTransactionAPI().begin(-1);

		}
		catch(SystemException se)
		{
			NmsLogMgr.MISCERR.fail(NmsUtil.GetString("UserTransaction Exception when begin the transaction in "+tableName),se); //No I18N
			se.printStackTrace();			              
		}
		catch(NotSupportedException ntse) 
		{
		}
	}
	private void commit()
	{
		try {
			ConnectionPool cp=ConnectionPool.getInstance();
			cp.getTransactionAPI().commit();
		}catch(Exception e){			
		
		}
	}
	
	private void rollback(String str)
	{
		try {
			ConnectionPool cp=ConnectionPool.getInstance();
			if(str == null)
			{
				cp.getTransactionAPI().rollback();
			}
			else
			{
				cp.getTransactionAPI().rollback(str);
			}
		}catch(Exception e){}
	}
	
	private Properties getPropsFromJSON(String jsString)
	{
        Properties attribute = new Properties();
        if(jsString == null || jsString.equalsIgnoreCase("NULL"))
        {
           return attribute;
        }
        JSONObject js = null;
        try{
                js = new JSONObject(jsString);
                Iterator itn= js.keys();
                while(itn.hasNext())
                {
                        String key=(String)itn.next();
                        String value= js.getString(key);
                        if(value.equals("NULL")){

                                value = "";
                        }
                        attribute.put(key,value);
                }

        }catch(Exception ex)
        {
                ex.printStackTrace();
        }

        return attribute;
	}
	
	
}

