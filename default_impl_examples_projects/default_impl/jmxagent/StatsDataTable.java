 //$Id: StatsDataTable.java,v 1.3 2007/10/17 09:29:01 barathv Exp $
/* Copyright (c)  1996 - 2004  Adventnet, Inc. All Rights Reserved.
 * PLEASE READ THE ASSOCIATED COPYRIGHTS FILE FOR MORE DETAILS.
 * ADVENTNET, INC. MAKES NO REPRESENTATIONS OR WARRANTIES  ABOUT THE
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE, OR NON-INFRINGEMENT.
 * ADVENTNET, INC. SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE
 * AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE
 * OR ITS DERIVATIVES.
 */

/**
 * @Version :  6.0.0 Wed Feb 14 00:34:12 IST 2007
 * @Author  :  AdventNet Agent Toolkit Java Edition
 */

// Any changes made to this file will be lost, if regenerated.
// User code should be added within user tags for code merging support, if regenerated.



// Package Name (Dont Delete this comment)
package com.adventnet.nms.jmxagent ;

import javax.management.*;
import javax.management.modelmbean.*;
import javax.jmx.openmbean.*;

import com.adventnet.utils.jmx.*;
import com.adventnet.utilities.common.*;
import com.adventnet.utils.agent.utils;

import java.io.*;
import java.util.*;
import java.lang.reflect.*;

	// User code starts here
import com.adventnet.nms.topodb.*;
import java.sql.*;
import com.adventnet.nms.util.*;
import com.adventnet.nms.store.*;
import com.adventnet.management.transaction.PreparedStatementWrapper;
	// User code ends here


/**
 * Handles all requests under
 * statsDataTable group
 */


public class StatsDataTable implements JmxTableModelListener{
       // User code starts here
    String[] names = {"PollID","Time","Oidindex","Value"};//No I18N
    Object[] values = null;
    CompositeData[] comps = null;
    // User code ends here

	private AdventNet_WebNMS_MIB_JMX agentName = null;

	/**
	 * Sets the Agent Reference
	 */
	public void setAgentReference(AdventNet_WebNMS_MIB_JMX agentRef){
		agentName = agentRef;
	}


	/**
	 * Gets the Agent Reference
	 */
	public AdventNet_WebNMS_MIB_JMX getAgentReference(){
		return agentName;
	}

	private StatsDataEntry entry = null;
	private String instrClassName = "com.adventnet.nms.jmxagent.StatsDataEntry";//No I18N
	private String[] indexNames = new String[]{"PollID","Time","Oidindex"};//No I18N

	/**
	 * To get the index column names of the table
	 * @retrun An array of String containing the Index names
	 */
	public String[] getIndexNames()
	{
		return indexNames;
	}
	MBeanServer server = null;
	private boolean[] externalIndexFlags = new boolean[]{false, false, false};

	private Hashtable table = null;
	private Vector vec = null;


	/**
	 * Sets the Hashtable containing the Table entries
	 * @param vec The Hashtable containing the Table entries
	 */
	public void setHashtable(Hashtable table)
	{
		this.table = table;
		vec = null;
	}

	/**
	 * Returns the Hashtable containing the Table entries
	 * @return Hashtable containing the Table entries
	 */
	public Hashtable getHashtable()
	{
		return table;
	}

	/**
	 * Sets the Vector containing the Table entries
	 * @param vec The Vector containing the Table entries
	 */
 	public void setTableVector(Vector  vec)
	{
		this.vec = vec;
		table = null;
	}

	/**
	 * Returns the Vector containing the Table entries
		* @return Vector containing the Table entries
	 */
	public Vector getTableVector()
	{
		return vec;
	}



	/**
	 * Initialises the StatsDataTable
	 */
	public StatsDataTable()
	{
		table = new Hashtable();
	}
	public StatsDataTable(AdventNet_WebNMS_MIB_JMX agentRef)
	{
		agentName = agentRef;
		table = new Hashtable();
	}





	public CompositeData getFirstEntry()
	{
		// User code starts here
		/*
		return null
		*/
		if(!agentName.initPoll())
	    return null;
        String ttime = "";//No I18N
	//ResultSet rs1=null, rs2 = null, rs3= null, rs4=null;
        long pollid = 0;

        String oidIndexval = "";//No I18N

        String val = "";//No I18N

        String tableName = "";//No I18N
        Statement stmt= null ;
        if(agentName.statstableName.indexOf("%") != -1)//No I18N
	{
            tableName = exactName(agentName.statstableName);
	}
        else
	{
            tableName = agentName.statstableName;
	}

	    //select min time
	    /** select the least time in the statsdata table
	     */
	    ResultSet rs1=null;
	    PreparedStatementWrapper pstatForMinTime= null;
	    PreparedStatement pstatementForMinTime = null;
	    try
	    {
		    pstatForMinTime = agentName.rlAPI.fetchPreparedStatement(agentName.rlAPI.getPreparedStatementID("select min(ttime) from "+tableName));//No I18N
		    pstatementForMinTime = pstatForMinTime.getPreparedStatement();
		    rs1 =  agentName.rlAPI.executeQuery(pstatementForMinTime);
		    rs1.next();
		    ttime = rs1.getString(1);
	    }
	    catch (Exception e)
	    {
		    agentName.agentErr.fail("Exception in executing query ", e); // no i18n  
	    }
	    finally
	    {
		    try
		    {
			    rs1.close();
		    }
		    catch  (Exception ex){}
		    agentName.rlAPI.returnPreparedStatement(pstatForMinTime);
	    }

	    //select min oid corr mintime
	    /** select the least pollid from the statsdata table given the ttime
	     */
	    ResultSet rs2=null;
	    PreparedStatementWrapper pstatForMin= null;
	    PreparedStatement pstatementForMin = null;
	    try
	    {
		    pstatForMin = agentName.rlAPI.fetchPreparedStatement(agentName.rlAPI.getPreparedStatementID("select min(pollid) from "+tableName+" where ttime ="+ttime));//No I18N
		    pstatementForMin = pstatForMin.getPreparedStatement();
		    rs2 = agentName.rlAPI.executeQuery(pstatementForMin);
		    rs2.next();
		    if(PureServerUtils.getDatabaseName().equalsIgnoreCase("timesten"))//No I18N
			    pollid = Long.parseLong(rs2.getString(1));
		    else
			    pollid = rs2.getLong(1);

	    }
	    catch (Exception e)
	    {
		    agentName.agentErr.fail("Exception in executing query ", e);	    // no i18n   
	    }
	    finally
	    {
		    try
		    {
			    rs2.close();
		    }
		    catch  (Exception ex){}
		    agentName.rlAPI.returnPreparedStatement(pstatForMin);
	    }
	    //select oidindex corr above two
	    /** select the least instance value from the statsdata table given the ttime,pollid
	     */
	    ResultSet rs3=null;
	    PreparedStatementWrapper pstatForMinInst= null;
	    PreparedStatement pstatementForMinInst = null;
	    String oid1 = "";
	    try
	    {
	    pstatForMinInst = agentName.rlAPI.fetchPreparedStatement(agentName.rlAPI.getPreparedStatementID("select min(instance) from "+tableName+" where ttime="+ttime+" and pollid="+pollid));//No I18N
	    pstatementForMinInst = pstatForMinInst.getPreparedStatement();
	    rs3 = agentName.rlAPI.executeQuery(pstatementForMinInst);
	    rs3.next();
	    oid1 = rs3.getString(1);
	    }
	     catch (Exception e)
	    {
		    agentName.agentErr.fail("Exception in executing query ", e);	    // no i18n   
	    }
	    finally
	    {
		    try
		    {
			    rs3.close();
		    }
		    catch  (Exception ex){}
		    agentName.rlAPI.returnPreparedStatement(pstatForMinInst);
	    }
		
	    //select corr value for the above three
	    /** select the value from the statsdata table given the ttime and the pollid and the instance values
	     */
	    ResultSet rs4=null;
	    PreparedStatementWrapper pstatForVal= null;
	    PreparedStatement pstatementForVal = null;
	    try
	    {
	    pstatForVal = agentName.rlAPI.fetchPreparedStatement(agentName.rlAPI.getPreparedStatementID("select val from "+tableName+" where ttime="+ttime+" and pollid="+pollid+" and instance='"+oid1+"'"));//No I18N
	    pstatementForVal = pstatForVal.getPreparedStatement();
	    rs4 =  agentName.rlAPI.executeQuery(pstatementForVal);
	    oidIndexval = oid1;
	    rs4.next();
	    val = rs4.getString(1);
	    }
	     catch (Exception e)
	    {
		    agentName.agentErr.fail("Exception in executing query ", e);  // no i18n   
	    }
	    finally
	    {
		    try
		    {
			    rs4.close();
		    }
		    catch  (Exception ex){}
		    agentName.rlAPI.returnPreparedStatement(pstatForVal);
	    }

	    CompositeData c = makeComData(pollid,ttime,oidIndexval,val);
        return c;
		// User code ends here
	}
	public CompositeData getEntry(Object[] indexObjects)
	{
	// User code starts here
	/*
	return null;
	*/
	 if(!agentName.initPoll())
	    return null;
        String tableName = "";//No I18N
        long id1 =0;
        String oidIndex1 ="";//No I18N
        String ttime1 = "";//No I18N
        String val1 = "";//No I18N

        id1 = ((Long)indexObjects[0]).longValue() ;
        ttime1 = indexObjects[1].toString();
       	oidIndex1 = indexObjects[2].toString();

        ResultSet rs = null;
        PreparedStatementWrapper pstatForVal= null;
	PreparedStatement pstatementForVal = null;
	if(agentName.statstableName.indexOf("%") != -1)//No I18N
            tableName = exactName(agentName.statstableName);
        else
            tableName = agentName.statstableName;

        try
            {
                /** selecting the 'value' field froms the statsdata table given the id and ttime and the instance value
                 */
                 pstatForVal = agentName.rlAPI.fetchPreparedStatement(agentName.rlAPI.getPreparedStatementID("select val from "+tableName+" where pollid="+id1+" and ttime="+ttime1+" and instance='"+oidIndex1+"'"));//No I18N
		 pstatementForVal = pstatForVal.getPreparedStatement();
                rs =   agentName.rlAPI.executeQuery(pstatementForVal);
                if(!rs.next())
                    return null;
                else
                    val1 = rs.getString(1);

            }
            catch (Exception e)
 	    {
		    agentName.agentErr.fail("Exception in executing query ", e);  // no i18n   
	    }
	    finally
	    {
		    try
		    {
			    rs.close();
		    }
		    catch  (Exception ex){}
		    agentName.rlAPI.returnPreparedStatement(pstatForVal);
	    }
        return makeComData(id1,ttime1,oidIndex1,val1);
	// User code ends here
	}
	public CompositeData getNextEntry(Object[] indexObjects)
	{
		// User code starts here
		/*
		return null;
		*/
		        if(!agentName.initPoll())
	    return null;
        //getting the id,time,oid as indices
        String tableName = "";//No I18N
        long id = 0;
	long id1 =0;
        String oidindex1 ="";//No I18N
        String ttime1 = "";//No I18N
        String ttime = "";//No I18N
        String val1 = "";//No I18N

        id1 = ((Long)indexObjects[0]).longValue() ;
        ttime1 = indexObjects[1].toString();

        oidindex1 = indexObjects[2].toString();

	// ResultSet rs = null,rs1 = null;
        //Statement stmt = null ;
        if(agentName.statstableName.indexOf("%") != -1)//No I18N
            tableName = exactName(agentName.statstableName);
        else
            tableName = agentName.statstableName;
                /** selecting the least instance value from the statsdata table greater than the given instance value
                 */
		ResultSet rs = null;
                PreparedStatementWrapper pstatForMinInst= null;
	        PreparedStatement pstatementForMinInst = null;
		String oidndx = null;
		try
		{
			pstatForMinInst = agentName.rlAPI.fetchPreparedStatement(agentName.rlAPI.getPreparedStatementID("select min(instance) from "+tableName+" where ttime="+ttime1+" and pollid="+id1+" and instance>'"+oidindex1+"'"));  //No I18N
			pstatementForMinInst = pstatForMinInst.getPreparedStatement();
			rs = agentName.rlAPI.executeQuery(pstatementForMinInst);
			rs.next();
			oidndx = rs.getString(1);
		}
		catch (Exception e)
		{
			agentName.agentErr.fail("Exception in executing query ", e); // no i18n   
		}
		finally
		{
			try
			{
				rs.close();
			}
			catch  (Exception ex){}
			agentName.rlAPI.returnPreparedStatement(pstatForMinInst);
		}	

                //if no values exist ,get inside the following loop
                //if(!rs.next())
		if(oidndx == null)
                    {
                        long id2 = 0 ;
                        /** selecting the least pollid from statsdata where the pollid is greater than the given and given the time
                         */
			ResultSet rs1 = null;
			PreparedStatementWrapper pstatForMinID= null;
	                PreparedStatement pstatementForMinID = null;
			try
			{
				pstatForMinID = agentName.rlAPI.fetchPreparedStatement(agentName.rlAPI.getPreparedStatementID("select min(pollid) from "+tableName+" where pollid>"+id1+" and ttime="+ttime1));//No I18N
				pstatementForMinID = pstatForMinID.getPreparedStatement();
				rs1 = agentName.rlAPI.executeQuery(pstatementForMinID);
				rs1.next();
				if(PureServerUtils.getDatabaseName().equalsIgnoreCase("timesten"))//No I18N
					id2 = Long.parseLong(rs1.getString(1));
				else
					id2 = rs1.getLong(1);

			}
			catch(Exception e)
			{
				agentName.agentErr.fail("Exception in executing query ", e); // no i18n  
			}
			finally
			{
				try
				{
					rs1.close();
				}
				catch  (Exception ex){}
				agentName.rlAPI.returnPreparedStatement(pstatForMinID);	
			}
                        /** selecting the least instance value from the statsdata table given the pollid and ttime
                         */
                        
			ResultSet rs3 = null;
			PreparedStatementWrapper pstatForInd= null;
			 PreparedStatement pstatementForInd = null;
			 String x = null;
			 try
			 {
				 pstatForInd = agentName.rlAPI.fetchPreparedStatement(agentName.rlAPI.getPreparedStatementID("select min(instance) from "+tableName+" where pollid="+id2+" and ttime="+ttime1));//No I18N
				 pstatementForInd = pstatForInd.getPreparedStatement();
				 rs3 = agentName.rlAPI.executeQuery(pstatementForInd);
				 rs3.next();
				 x = rs3.getString(1);
			 }
			 catch(Exception e)
			 {
				 agentName.agentErr.fail("Exception in executing query ", e);  // no i18n  
			 }
			 finally
			 {
				 try
				 {
					 rs3.close();
				 }
				 catch  (Exception ex){}
				 agentName.rlAPI.returnPreparedStatement(pstatForInd);	
			 }
                        //rs.close();
                        //if no values exist ,get inside the following loop
                        //if(!rs.next())
			 if(x == null)
                            //   if(!rs.next())
                            {
                                long p1 = 0 ;
                                /** selecting the least ttime from the statsdata table greater than the given time
                                 */
                                ResultSet rs4 = null;
				PreparedStatementWrapper pstatForT= null;
			        PreparedStatement pstatementForT = null;
				String t1 = null;
				try
				{
					pstatForT = agentName.rlAPI.fetchPreparedStatement(agentName.rlAPI.getPreparedStatementID("select min(ttime) from "+tableName+" where ttime>"+ttime1));//No I18N
					pstatementForT = pstatForT.getPreparedStatement();
					rs4 = agentName.rlAPI.executeQuery(pstatementForT);
					rs4.next();
					t1 = rs4.getString(1);
				}
				catch(Exception e)
				{
					agentName.agentErr.fail("Exception in executing query ", e);  // no i18n  
				}
				finally
				{
					try
					{
						rs4.close();
					}
					catch  (Exception ex){}
					agentName.rlAPI.returnPreparedStatement(pstatForT);	
				}
				/** selecting the least pollid given the ttime
                                 */
				ResultSet rs5 = null;
				PreparedStatementWrapper pstatForI= null;
			        PreparedStatement pstatementForI = null;
				try
				{
					pstatForI = agentName.rlAPI.fetchPreparedStatement(agentName.rlAPI.getPreparedStatementID("select min(pollid) from "+tableName+" where ttime ="+t1));//No I18N
					pstatementForI = pstatForI.getPreparedStatement();
					rs5 = agentName.rlAPI.executeQuery(pstatementForI);
					rs5.next();
					if(PureServerUtils.getDatabaseName().equalsIgnoreCase("timesten"))//No I18N
						p1 = Long.parseLong(rs5.getString(1));
					else
						p1 = rs5.getLong(1);

						
				
				}
				catch(Exception e)
				{
					agentName.agentErr.fail("Exception in executing query ", e);  // no i18n   
				}
				finally
				{
					try
					{
						rs5.close();
					}
					catch  (Exception ex){}
					agentName.rlAPI.returnPreparedStatement(pstatForI);    
				}
                                /** selecting the least instance value from the statsdata table given the time and the pollid
                                 */
                                ResultSet rs6 = null;
				PreparedStatementWrapper pstatForIT= null;
			        PreparedStatement pstatementForIT = null;	
				String oidndxxx = null;
				try
				{
					pstatForIT = agentName.rlAPI.fetchPreparedStatement(agentName.rlAPI.getPreparedStatementID("select min(instance) from "+tableName+" where ttime="+t1+" and pollid="+p1));//No I18N
					pstatementForIT = pstatForIT.getPreparedStatement();
					rs6 = agentName.rlAPI.executeQuery(pstatementForIT);
					rs6.next();
					oidndxxx = rs6.getString(1);
				}
				catch(Exception e)
				{
					agentName.agentErr.fail("Exception in executing query ", e);  // no i18n   
				}
				finally
				{
					try
					{
						rs6.close();
					}
					catch  (Exception ex){}
					agentName.rlAPI.returnPreparedStatement(pstatForIT);    
				}
                                /** selecting the value from the statsdata table given the ttime,pollid and instance values
                                 */
                                ResultSet rs7 = null;
				PreparedStatementWrapper pstatForTable= null;
			        PreparedStatement pstatementForTable = null;
				try
				{
					pstatForTable = agentName.rlAPI.fetchPreparedStatement(agentName.rlAPI.getPreparedStatementID("select val from "+tableName+" where ttime="+t1+" and pollid="+p1+" and instance='"+oidndxxx+"'"));//No I18N
					pstatementForTable = pstatForTable.getPreparedStatement();
					rs7 = agentName.rlAPI.executeQuery(pstatementForTable);
					id = p1;
					ttime = t1;
					oidindex1 = oidndxxx;
					rs7.next();
					long res = rs7.getLong(1);
					val1 = (new Long(res)).toString();
				}
				catch(Exception e)
				{
				}
				finally
				{
					try
					{
						rs7.close();
					}
					catch  (Exception ex){}
					agentName.rlAPI.returnPreparedStatement(pstatForTable);    
				}
			    }
                        else
                            {
                                String oidndxx = x;
                                /** selecting the value from the statsdata table given the ttime and the pollid and the instance
                                 */
                                ResultSet rs8 = null;
				PreparedStatementWrapper pstatForTimeID= null;
			        PreparedStatement pstatementForTimeID = null;
				try
				{

					pstatForTimeID = agentName.rlAPI.fetchPreparedStatement(agentName.rlAPI.getPreparedStatementID("select val from "+tableName+" where ttime="+ttime1+" and pollid="+id2+" and instance='"+oidndxx+"'"));//No I18N
					pstatementForTimeID = pstatForTimeID.getPreparedStatement();
					rs8 =  agentName.rlAPI.executeQuery(pstatementForTimeID);
					rs8.next();
					id = id2;
					ttime = ttime1;
					oidindex1 = oidndxx;
					val1 = rs8.getString(1);
				}
				catch(Exception e)
				{
					agentName.agentErr.fail("Exception in executing query ", e);  // no i18n   
				}
				finally
				{
					try
					{
						rs8.close();
					}
					catch  (Exception ex){}
					agentName.rlAPI.returnPreparedStatement(pstatForTimeID);    
				}
                            }
                    }
                else
                    {
                        //  String oidndx = rs.getString(1);
                        /** selecting the value from the statsdata table given the ttime,pollid and the instance values
                         */
                        ResultSet rs9 = null;
			PreparedStatementWrapper pstatForTimeIDs= null;
			PreparedStatement pstatementForTimeIDs = null;
			try
			{
				pstatForTimeIDs = agentName.rlAPI.fetchPreparedStatement(agentName.rlAPI.getPreparedStatementID("select val from "+tableName+" where ttime="+ttime1+" and pollid="+id1+" and instance='"+oidndx+"'"));//No I18N
				pstatementForTimeIDs = pstatForTimeIDs.getPreparedStatement();
				rs9 = agentName.rlAPI.executeQuery(pstatementForTimeIDs);
				rs9.next();
				id = id1;
				ttime = ttime1;
				oidindex1 = oidndx;
				val1 = rs9.getString(1);
			}
			catch(Exception e)
			{
				agentName.agentErr.fail("Exception in executing query ", e);  // no i18n   
			}
			finally
			{
				try
				{
					rs9.close();
				}
				catch  (Exception ex){}
				agentName.rlAPI.returnPreparedStatement(pstatForTimeIDs);    
			}
                    }
        return makeComData(id,ttime,oidindex1,val1);

		// User code ends here
	}
	public int totalRows()
	{
	     		// User code starts here
	     		/*
	     		return -1;
	     		*/
	     		 if(!agentName.initPoll())
	    return 0;
        int sizeOfTable = 0 ;
        String tableName = "";//No I18N
	ResultSet rsj=null;
        PreparedStatementWrapper pstatForRow= null;
        PreparedStatement pstatementForRow = null;
	try
            {
                if(agentName.statstableName.indexOf("%") != -1)//No I18N
tableName = exactName(agentName.statstableName);
                else
                    tableName = agentName.statstableName;
                /** select the number of rows in the statsdata table
                 */
                pstatForRow = agentName.rlAPI.fetchPreparedStatement(agentName.rlAPI.getPreparedStatementID("select count(*) from "+tableName));//No I18N
		pstatementForRow = pstatForRow.getPreparedStatement();
                rsj = agentName.rlAPI.executeQuery(pstatementForRow);
                rsj.next();
                sizeOfTable = rsj.getInt(1);
            }
        catch(Exception we)
            {
		    
            }
        finally
            {
                try
                    {
                        rsj.close();
                    }
                catch(Exception aa)
                    {
                    }
            }
        return sizeOfTable;
	     		// User code ends here
	}
	public List getEntries(int startIndex, int endIndex)
	{
		// User code starts here
		/*
		return null;
		*/
		if(!agentName.initPoll())
	    return null;
        ArrayList arrayList = new ArrayList();
        String tableName = "";//No I18N
        String ttime = "";//No I18N
	ResultSet rs1=null,rs2=null,rs3=null,rs4=null,rsa=null,rsb=null,rsc=null,rsd=null,rse=null,rsf=null,rsg=null,rsh=null,rsi=null,rsj=null;
        long pollid = 0;
        String oidIndexval = "";//No I18N
        String val = "";//No I18N
        String[] names={"","",""};//No I18N
        if(agentName.statstableName.indexOf("%") != -1)//No I18N
            tableName = exactName(agentName.statstableName);
        else
            tableName = agentName.statstableName;
        boolean firstEntry = true;
        Statement stmt = null ;
        for(int i = 0 ;i< totalRows();i++)
            {
                if(firstEntry)
                    {
                        try
                            {
                                //select min time
                                stmt = agentName.rlAPI.query("select min(ttime) from "+tableName,true);//No I18N
                                rs1 = stmt.getResultSet();
                                rs1.next();
                                ttime = rs1.getString(1);
                                rs1.close();
                                stmt.close();

				try{
                                //select min oid corr mintime
                                stmt = agentName.rlAPI.query("select min(pollid) from "+tableName+" where ttime ="+ttime,true);//No I18N
                                rs2 = stmt.getResultSet();
                                rs2.next();
                                if(PureServerUtils.getDatabaseName().equalsIgnoreCase("timesten"))//No I18N
                                    pollid = Long.parseLong(rs2.getString(1));
                                else
                                    pollid = rs2.getLong(1);
			    }
                                catch(Exception e)
                                    {
                                    }
                                finally
                                    {
                                        rs2.close();
                                        stmt.close();
                                    }

                                //select oidindex corr above two
                                stmt = agentName.rlAPI.query("select min(instance) from "+tableName+" where ttime="+ttime+" and pollid="+pollid,true);//No I18N
                                rs3 = stmt.getResultSet();
                                rs3.next();
                                String oid1 = rs3.getString(1);
                                rs3.close();
                                stmt.close();

                                //select corr value for the above three
                                stmt = agentName.rlAPI.query("select val from "+tableName+" where ttime="+ttime+" and pollid="+pollid+" and instance='"+oid1+"'",true); //No I18N
                                rs4 = stmt.getResultSet();
                                oidIndexval = oid1;
                                rs4.next();
                                val = rs4.getString(1);
                                firstEntry = false;

                            }
                        catch(Exception e)
                            {
                                return null;
                            }
                        finally
                            {
                                try
                                    {
					rs4.close();
                                        rs1.close();
                                        rs2.close();
                                        rs3.close();
					// THEME-II Start
					stmt.close();
					// THEME-II End
                                    }
                                catch(Exception x4)
                                    {
                                        //x4.printStackTrace();
                                    }
                            }
                    }
                else
                    {

                        //ResultSet rs = null;
                        try
                            {


                                stmt = agentName.rlAPI.query("select min(instance) from "+tableName+" where ttime="+ttime+" and pollid="+pollid+" and instance>'"+oidIndexval+"'",true);  //No I18N
                                rsa = stmt.getResultSet();
                                rsa.next();
                                String oidndx = rsa.getString(1);
                                rsa.close();
                                stmt.close();
                                //if no oidndx values exist ,get inside the following loop
                                //if(!rs.next())
                                if(oidndx == null)
                                    {
					long id2 = 0 ;
					try{
					stmt = agentName.rlAPI.query("select min(pollid) from "+tableName+" where pollid>"+pollid+" and ttime="+ttime,true);//No I18N
                                        rsb = stmt.getResultSet();
                                        rsb.next();
                                        if(PureServerUtils.getDatabaseName().equalsIgnoreCase("timesten"))//No I18N
                                          id2 = Long.parseLong(rsb.getString(1));
                                        else
                                          id2 = rsb.getLong(1);
                                        }
                                        catch(Exception e)
                                            {
                                            }
                                        finally
                                            {
                                        rsb.close();
                                        stmt.close();
                                            }
                                        stmt = agentName.rlAPI.query("select min(instance) from "+tableName+" where pollid="+id2+" and ttime="+ttime,true);        //No I18N
                                        rsc = stmt.getResultSet();
                                        rsc.next();
                                        String x = rsc.getString(1);
                                        //stmt.close();

                                        //if no oidindex values exist ,get inside the following loop
                                        //if(!rs.next())
                                        if(x == null)
                                            //   if(!rs.next())
                                            {
						long p1 = 0 ;
                        rsc.close();
                        stmt.close();
                                                stmt = agentName.rlAPI.query("select min(ttime) from "+tableName+" where ttime>"+ttime,true);//No I18N
                                                rsd = stmt.getResultSet();
                                                rsd.next();
                                                String t1 = rsd.getString(1);
                                                rsd.close();
                                                stmt.close();
						try{
                                                stmt = agentName.rlAPI.query("select min(pollid) from "+tableName+" where ttime ="+t1,true);//No I18N
                                                rse = stmt.getResultSet();
                                                rse.next();
                                                if(PureServerUtils.getDatabaseName().equalsIgnoreCase("timesten"))//No I18N
                                                     p1 = Long.parseLong(rse.getString(1));
                                                else
                                                    p1 = rse.getLong(1);
						}
                                                catch(Exception e)
                                                    {
                                                    }
                                                finally
                                                    {
                                                rse.close();
                                                stmt.close();
                                                    }
                                                stmt = agentName.rlAPI.query("select min(instance) from "+tableName+" where ttime="+t1+" and pollid="+p1,true);//No I18N
                                                rsf = stmt.getResultSet();
                                                rsf.next();
                                                String oidndxxx = rsf.getString(1);
                                                rsf.close();
                                                stmt.close();
                                                stmt = agentName.rlAPI.query("select val from "+tableName+" where ttime="+t1+" and pollid="+p1+" and instance='"+oidndxxx+"'",true);//No I18N
                                                rsg = stmt.getResultSet();
                                                pollid = p1;
                                                ttime = t1;
                                                oidIndexval = oidndxxx;
                                                rsg.next();
                                                val = rsg.getString(1);
                                                rsg.close();
                                                stmt.close();
                                            }
                                        else
                                            {
                                                String oidndxx = rsc.getString(1);
                                                rsc.close();
                                                stmt.close();
                                                stmt = agentName.rlAPI.query("select val from "+tableName+" where ttime="+ttime+" and pollid="+id2+" and instance='"+oidndxx+"'",true);//No I18N
                                                rsh = stmt.getResultSet();
                                                rsh.next();
                                                pollid = id2;
                                                ttime = ttime;
                                                oidIndexval = oidndxx;
                                                val = rsh.getString(1);
                                                rsh.close();
                                                stmt.close();
                                            }
                                    }
                                else
                                    {
                                //  String oidndx = rs.getString(1);
                                        stmt = agentName.rlAPI.query("select val from "+tableName+" where ttime="+ttime+" and pollid="+pollid+" and instance='"+oidndx+"'",true); //No I18N
                                        rsi = stmt.getResultSet();
                                        rsi.next();
                                        pollid = pollid;
                                        ttime = ttime;
                                        oidIndexval = oidndx;
                                        val = rsi.getString(1);
                                        rsi.close();
                                        stmt.close();
                                    }
                            }
                        catch(Exception ex)
                            {
                                return null;
                            }
                        finally
                            {
                                try
                                    {
                                        rsa.close();
                                        rsb.close();
                                        rsc.close();
                                        rsd.close();
                                        rse.close();
                                        rsf.close();
                                        rsg.close();
                                        rsh.close();
                                        rsi.close();
					// THEME-II Start
                                        stmt.close();
					// THEME-II End
                                    }
                                catch(Exception x5)
                                    {
                                        //x5.printStackTrace();
                                    }
                            }
                    }
                //checking if the data obtained is in the range,then put in object array
                if((i+1 >= startIndex) && (i+1 <= endIndex))
                    {
                        Object[] indx = new Object[]{new Long(pollid),ttime,oidIndexval};
                        arrayList.add(indx);
                        /** checking if the upper limit is exceeded or not,if exceeded,then break
                         */
                        if(i+1 == endIndex) break;
                    }
            }//end of for
        return arrayList;
		// User code ends here
	}
	public void addRow(Object[] indexObjects, CompositeData entry) throws Exception
	{	}
	public void modifyRow(Object[] indexObjects, CompositeData entry) throws Exception
	{	}
	public void deleteRow(Object[] indexObjects) throws Exception
	{	}

	/**
	 * Returns the TabularData
	 * @return TabularData containing the rows corresponding the StatsDataTable
	 */
	public TabularData getStatsDataTable()
	{
	     // User code starts here
                if(true)
                    {
                        return getTable();
                    }
                // User code ends here
		try{
			if(table != null)
				return  Utilities.getTabularData(this, indexNames, table, instrClassName, null);
			else if(vec != null)
				return  Utilities.getTabularData(this, indexNames, vec, instrClassName, null);
		}catch(Exception ex){
			ex.printStackTrace();
		}

		return null;
	}



	/**
	 * Sets the TabularData to the AaplicationTable
	 * @param data The TabularData to be set to the AaplicationTable
	 */
	public void setStatsDataTable(TabularData data) throws Exception
	{
		AgentException ae = null;

		for(Enumeration e = data.enumerate();e.hasMoreElements();)
		{
			Object[] index = (Object[])e.nextElement();
			CompositeData comp = data.getRow(index);

			if(table != null)
				entry = (StatsDataEntry)Utilities.getEntryFromCompositeData(table, comp, indexNames, instrClassName);
			else if(vec != null)
				entry = (StatsDataEntry)Utilities.getEntryFromCompositeData(vec, comp, indexNames, instrClassName);

			if(comp.getOperationType().equals(CompositeData.CREATED))
			{//create new entry

			if(entry != null)
				throw new AgentException("Row already exist",CommonUtils.ROWCREATIONFAILED);//No I18N
				entry = new StatsDataEntry();

				if(table != null)
					table.put(index, entry);
				else if(vec != null)
				vec.addElement(entry);
				for(Enumeration ce = comp.enumerate();ce.hasMoreElements();)
				{
					String key = (String)ce.nextElement();
					try{
						Utilities.setField(entry, instrClassName, key, comp.getDataItem(key));
					}catch(AgentException aexp){
						ae = aexp;
					}
				}
			}

			else if(comp.getOperationType().equals(CompositeData.DELETED))
			{

				if(table != null)
				{
					for(Enumeration en = table.keys();en.hasMoreElements();)
					{
						Object keyObject = en.nextElement();
						if(entry.equals(table.get(keyObject)))
							table.remove(keyObject);
					}
				}
				else if(vec != null)
					if(!vec.removeElement(entry))
						throw new AgentException("Invalid Index", CommonUtils.INVALIDINDEX);//No I18N
				data.deleteRow(index);
			}

			else if(comp.getOperationType().equals(CompositeData.MODIFIED))
			{

				for(Enumeration ce = comp.enumerate();ce.hasMoreElements();)
				{
					String key = (String)ce.nextElement();
					if(!comp.isModified(key))
						continue;
					try{

						Utilities.setField(entry, instrClassName, key, comp.getDataItem(key));
					}catch(AgentException aexp){
						ae = aexp;
					}
				}
			}

			comp.setOperationType(CompositeData.NOCHANGES);
		}

		if(ae != null)
			throw ae;

	}

	// User code starts here
/**
     * getting the statsdata from the Statsdata table.Using the statsdata,forming an array of CompositeData
     * Using the array of CompositeData forming the TabularData.
     * returning the TabularData
     */
    TabularData getTable()
    {
        if(!agentName.initPoll())
	    return null;
        TabularData td = null;
	ResultSet rs1 = null,rs2 = null;
        OpenMBeanParameterInfo[] parameterInfo = new OpenMBeanParameterInfo[names.length];
        String returnType = null;
        Statement stmt1 = null;
        try
            {
                Class entryClassName = Class.forName(instrClassName);

                for(int i = 0; i < names.length; i++)
                    {
                        String methodName = "get" + names[i];//No I18N

                        Method method = entryClassName.getMethod(methodName,null);

                        returnType = method.getReturnType().getName();

                        parameterInfo[i] = new OpenMBeanParameterInfo(names[i],returnType,null,null,null);
                    }
            }
        catch(Exception e)
            {
                agentName.agentErr.fail(" exception in stats ", e);//No I18N
                return null;
            }

        if(agentName.statstableName.indexOf("%") !=-1)//No I18N
            {
                agentName.statstableName =  exactName(agentName.statstableName);
            }

        try
            {
                /** getting the total number of rows in the statsdata table
                 */
                stmt1 = agentName.rlAPI.query("select count(*) from "+agentName.statstableName,true);//No I18N
                rs1 = stmt1.getResultSet();
                rs1.next();
                int sizeOfTable = rs1.getInt(1);
                rs1.close();
                comps = new CompositeData[sizeOfTable];
                stmt1.close();
                /** getting entire data of the statsdata table
                 */

                stmt1 = agentName.rlAPI.query("select * from "+agentName.statstableName,true);//No I18N
		rs2 = stmt1.getResultSet();

                int i=0;

                while(rs2.next())
                    {

			long id1 =0;
                        if(PureServerUtils.getDatabaseName().equalsIgnoreCase("timesten"))//No I18N
                           id1 = Long.parseLong(rs2.getString(1));
                        else
                           id1 = rs2.getLong(1);

                        String oid1= rs2.getString(2);

                        String t1 = rs2.getString(3);

                        String va1 =  rs2.getString(4);

                        comps[i++] = makeComData(id1,t1,va1,oid1);

                    }
            }
        catch(Exception ent)
            {
                return null;
            }
        finally
            {
                try
                    {
                        rs1.close();
                        rs2.close();

			// THEME-II Start
                        stmt1.close();
			// THEME-II End
                    }
                catch(Exception x1)
                    {
                        //x1.printStackTrace();
                    }
            }
        TabularParameterInfo tinfo = new TabularParameterInfo(null,null,null,null,null,parameterInfo,indexNames);
        try
            {
                td =  new TabularData(tinfo, comps);
            }
        catch(Exception  exe)
            {
		agentName.agentErr.fail(" exception in stats is  ",exe);//No I18N
                return null;
	    }
        return td;
    }
    /** setting the correct date for the statsdata table
     */
    public String exactName(String name)
    {
        StringBuffer sbf = new StringBuffer(agentName.statstableName);
        int t = (agentName.statstableName).indexOf('%');
        String todayDate = currentDate();
        sbf.replace(t,t+1,todayDate);
        agentName.statstableName = sbf.toString();
	return agentName.statstableName;

    }
    //getting the current date for the statsdata table
    String currentDate()
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new java.util.Date());
        String tmp = (cal.get(cal.MONTH) + 1) + "_" + cal.get(cal.DAY_OF_MONTH) + "_" + cal.get(cal.YEAR);//No I18N
        return tmp;
    }
    /**
     * forming a statsdata using the index.Forming the CompositeData for the statsdata
     * Returning the CompositeData
     */
     private CompositeData makeComData(long p,String t,String oidInd,String v)
    {

        values = new Object[names.length];

        values[0] = new Long(p);
        values[1] = t;
        values[2] = oidInd;
        values[3] = v;
        try
            {
                CompositeData c = new CompositeData(null,names,values);
		return c;
            }
        catch(Exception w)
            {
                return null;
            }
    }
	// User code ends here
}





