/*
 * $Id: TableCustomizationFilter.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
 * Copyright (c) 2001 Adventnet, Inc. All Rights Reserved.
 * Please read the associated COPYRIGHTS file for more details.
 *
 * ADVENTNET,INC. MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY
 * OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
 * NON-INFRINGEMENT. ADVENTNET, INC. SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS
 * SOFTWARE OR ITS DERIVATIVES.
 *
 */
 
package test;

import com.adventnet.nms.topodb.*;
import com.adventnet.nms.poll.*;
import com.adventnet.nms.util.*;

import java.util.*;

/**
 * TableCustomizationFilter implements the PollFilter interface to support
 * the table customization feature. Users can create their own table 
 * based on their requirements using this feature. 
 * In this example, a schema is created to store the value collected 
 * for three dataIdentifiers from a device for a particular time in a 
 * single row of a table. 
 * Using this schema, a table called 'WinNTtable' is created using
 * PollAPI. Then, this table is associated with all PolledData objects
 * whose policyName equals to 'WindowsNT'.
 *
 * An entry of this class should be given in Polling.filters of WebNMS/conf
 * directory as follows:
 *
 *   <FILTER
 *      className="test.TableCustomizationFilter" />
 *
 * @author Rajagopal N
 * @version $Revision: 1.1.1.1 $
 * @since WebNMS 2.3 + SP2 
 */ 
public class TableCustomizationFilter implements PollFilter
{
	public static PollAPI api = null;
	boolean flag = true;

	/**
	 * PollAPI's handle is obtained using the NmsUtil's getAPI() method. 
     * A schema to store the data collected for the three dataIdentifieres 
     * namely IFINOCTETS,IFOUTOCTETS,IFERRORS  of an agent for a 
     * particular time in a single row of a table
	 * called 'WinNTtable' is created and added to the database using
	 * the PollAPI. Then the table 'WintNTtable' is created corresponding
	 * to this schema using the PollAPI's createTable() method.
	 * Finally this table is asscociated with all PolledData whose
	 * policyName equals 'WindowsNT'
	 */
	public Vector applyPollFilter(ManagedObject obj,Vector pdatasVect)
	{
		Vector pvector = pdatasVect;
		PolledData pd = null;

		if(flag)
		{
			try
			{
				// PollAPI's handle is obtained using NmsUtil's
				// getAPI() method.
				api = (PollAPI)NmsUtil.getAPI("PollAPI");
			}
			catch(Exception e)
			{
				System.out.println("Error occurred while getting PollAPI handle: "+e);
				e.printStackTrace();
			}

			try
			{
				// form the schema and create the WinNTtable.
                // Note: This schema is created for mysql database. User should
                // modify this schema depending upon the database.
				String schema = "create table <> (AGENT varchar(100),IFINOCTETS BIGINT, IFOUTOCTETS BIGINT, IFERRORS BIGINT,TIMEOFCOL varchar(100))";
				String tablename = "WinNTtable";
				api.addCreateSchema(tablename, schema);
				api.createTable("WinNTtable");
			}
			catch (Exception e)
			{
				System.out.println("Exception in creating Schema and adding table");
				e.printStackTrace();
			}
            // flag is set to false since table is created
			flag = false;
		}

		// The vector of PolledData objects is enumerated and checked for the
		// policyName. If it equals to 'WindowsNT, then the statsDataTableName
		// of the PolledData is set to "WinNTtable" so that all the data
		// collected for this PolledData would be stored in this table.

		for(Enumeration e = pvector.elements(); e.hasMoreElements();)
		{
			pd = (PolledData)e.nextElement();
			if(pd.getPolicyName().equals("WindowsNT"))
			{
				pd.setStatsDataTableName("WinNTtable");
			}	
		}
        // finally return the PolledData vector after setting the table name
        // to all PolledData whose policyName equals "WindowsNT".
		return pvector;
	}
}














