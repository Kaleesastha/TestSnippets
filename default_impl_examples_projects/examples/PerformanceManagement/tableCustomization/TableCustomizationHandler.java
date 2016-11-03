/*
 * $Id: TableCustomizationHandler.java,v 1.2 2008/12/16 07:42:13 swaminathap Exp $
 * Copyright (c) 2001 Adventnet, Inc. All Rights Reserved.
 * Please read the associated COPYRIGHTS file for more details.
 *
 * ADVENTNET,INC. MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY
 * OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
 * NON-INFRINGEMENT. ADVENTNET, INC. SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS
 * SOFTWARE OR ITS DERIVATIVES.
 */
 
package test;

import java.sql.*;
import java.util.*;
import com.adventnet.nms.store.relational.*;
import com.adventnet.nms.util.*;
import com.adventnet.nms.poll.*;

/**
 * TableCustomizationHandler class implements the DataRequestHandler interface.
 * It is mainly written to fetch the values from the customized table 
 * (other than the default STATSDATA or STRINGDATA table) created by the user.
 *
 * In the canHandleRequest method of this class, PolledData is checked
 * for the policyName and if it is equal to 'WindowsNT', true is returned.
 * If this method returns true then the getCollectedData method of PollAPI calls
 * this class to fetch collected values for PolledData otherwise data will be
 * fetched from default STATSDATA or STRINGDATA table for the PolledData.
 *
 * In the getCollectedData method of this class,  the PolledData is checked
 * for the name and an sql query is executed to fetch the values and time of
 * data collection  for this PolledData from the database. Then this values
 * are put it into the CollectedData and the same is returned. User can
 * retrieve the collected values of this PolledData which is stored in user
 * defined customized table, by using the getCollectedData or
 * getCollectedValues method of PollAPI.
 *
 * An entry of this class should be given in nmsInterfaces.conf in WebNms/conf
 * directory under the Interfaces tag as follows:
 *       dataHandler= "test.TableCustomizationHandler";
 *
 * @author Rajagopal N
 * @version $Revision: 1.2 $
 * @since WebNMS 2.3 + SP2
 */
public class TableCustomizationHandler implements DataRequestHandler
{
	Connection con = null;
	RelationalAPI relapi = null;
    
	/**
	 * PolledData object is checked for policyName,
	 * and if it matches 'WindowsNT', then true is returned.
	 * If this method returns true, then the getCollectedData or
	 * getCollectedValues of PollAPI calls this class to get the
	 * the collected values for this PolledData, otherwise it will
	 * fetch data from the default STATSDATA or STRINGDATA table.
	 */
	public boolean canHandleRequest(PolledData pd)
	{
		if(pd.getPolicyName().equals("WindowsNT"))
		{
			return true;
		}
		return false;
	}

	/**
	 * Retrieves the collected values from the user defined table
     * for PolledData whose policyName equals to 'WindowsNT' 
     * between the time interval given.
	 * An sql query is executed to get the collected values and time of data 
	 * collection for a PolledData. This returns a ResultSet which contains the
	 * collected values. The ResultSet is manipulated to get the 
	 * collected values and time of data collection and the same are put into 
	 * the CollectedData object. Finally the CollectedData is returned.
	 */
	public CollectedData getCollectedData(PolledData pd,String index,long stime,long etime)
	{
        // get database connection using RelationalAPI.
        
		if (relapi == null) relapi = NmsUtil.relapi;
		try
		{	
			con = relapi.getConnection();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}

		if(pd.getPolicyName().equals("WindowsNT"))
		{

				ResultSet rs=null;
			try
			{
				Statement stm = con.createStatement();

				if(pd.getName().equals("IFINOCTETS"))
				{
                    // user can change this query based on their requirements
                    
					String sqlString = "select IFINOCTETS,TIMEOFCOL from WinNTtable where AGENT like '"+pd.getAgent()+"'";
					rs = stm.executeQuery(sqlString);

					if (rs == null) return null;

					Vector ifinoctets = new Vector();
					Vector timeofcol = new Vector(); 

                    // process the ResultSet and put the values and times
                    // in the vectors.

					while (rs.next()) 
					{
						ifinoctets.addElement(rs.getString(1));
						timeofcol.addElement(rs.getString(2));  
					}	

					long value[] = new long[ifinoctets.size()];
					long time[] = new long[timeofcol.size()];       

                    // fill the values and times in the arrays

					for (int i=0;i<time.length;i++) 
					{
						try 
						{	
							value[i] = Long.parseLong((String)ifinoctets.elementAt(i));
							time[i] = Long.parseLong((String)timeofcol.elementAt(i));
						}
						catch (NumberFormatException nfe)
						{						
							nfe.printStackTrace();
						}
					}

                    // add these arrays in the Vector

					Vector v = new Vector();
					v.addElement(time);
					v.addElement(value);

                    // form CollectedData object using this Vector

					CollectedData cd = new CollectedData(v);

                    // finally return the CollectedData object
					return cd;
				}	
				else if(pd.getName().equals("IFOUTOCTETS"))
				{
					String sqlString = "select IFOUTOCTETS,TIMEOFCOL from WinNTtable where AGENT like '" + pd.getAgent()+ "'";
					rs = stm.executeQuery(sqlString);

					if (rs == null) return null;

					Vector ifoutoctets = new Vector();
					Vector timeofcol = new Vector(); 

					while (rs.next()) 
					{
						ifoutoctets.addElement(rs.getString(1));
						timeofcol.addElement(rs.getString(2));  
					}

					long value[] = new long[ifoutoctets.size()];
					long time[] = new long[timeofcol.size()];       

					for (int i=0;i<time.length;i++) 
					{
						try 
						{
							value[i] = Long.parseLong((String)ifoutoctets.elementAt(i));
							time[i] = Long.parseLong((String)timeofcol.elementAt(i));
						}
						catch (NumberFormatException nfe)
						{	
							nfe.printStackTrace();
						}
					}

					Vector v = new Vector();
					v.addElement(time);
					v.addElement(value);

					CollectedData cd = new CollectedData(v);
					return cd;

				}
				else if(pd.getName().equals("IFERRORS"))
				{
					String sqlString = "select IFERRORS,TIMEOFCOL from WinNTtable where AGENT like '"+pd.getAgent()+"'";
					rs = stm.executeQuery(sqlString);

					if (rs == null) return null;

					Vector iferrors = new Vector();
					Vector timeofcol = new Vector(); 

					while (rs.next()) 
					{
						iferrors.addElement(rs.getString(1));
						timeofcol.addElement(rs.getString(2));  
					}	

					long value[] = new long[iferrors.size()];
					long time[] = new long[timeofcol.size()];       

					for (int i=0;i<time.length;i++) 
					{
						try 
						{		
							value[i] = Long.parseLong((String)iferrors.elementAt(i));
							time[i] = Long.parseLong((String)timeofcol.elementAt(i));
						}
						catch (NumberFormatException nfe)
						{			
							nfe.printStackTrace();
						}		
					}

					Vector v = new Vector();
					v.addElement(time);
					v.addElement(value);

					CollectedData cd = new CollectedData(v);
					return cd;
				}
			}
			catch(Exception e)
			{
				System.out.println("Exception while getting data");
				e.printStackTrace();
				return null;
			}
			finally
			{
				try
				{

				if(rs!=null)
				{
					rs.close();
				}
				}
				catch(Exception e){}
			}

		}
		return null;
	}

	/**
	 * Returns the instances of MultiplePolledData whose collected 
     * values are stored in the user defined table. User has to include 
     * his code to execute a sql string to get all the instances of 
     * MultiplePolledData.
	 */
	public Vector getInstances(PolledData pd)
	{
		return null;
	}
}





