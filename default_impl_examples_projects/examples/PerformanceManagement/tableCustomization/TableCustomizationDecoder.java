/*
 * $Id: TableCustomizationDecoder.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
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

import java.util.*;
import com.adventnet.nms.poll.*;

/**
 * TableCustomizationDecoder class implements the DataDecoder
 * interface. It enables the user to store the collected values 
 * of three dataIdentifiers from the device for a particular 
 * time in a single row of of the customized table created by the 
 * user. The decode method of this class explains about storing 
 * the values of more than one PolledData in a same row. 
 * In this example, the value collected from a particular device
 * for a particular time for the OIDs 2.2.1.16, 2.2.1.10 and 
 * 2.2.1.14 are stored in a same row of the table. 
 * Initially, table should be created using PollAPI.
 * And this table should be associated to the PolledData using 
 * PollFilter. In Polling.conf an 'insertString' corresponding to 
 * the user defined schema using which the table is created 
 * already is given for OID 2.2.1.10. 
 * Values collected from the device are inserted in CollectedInfo 
 * against the respective column names corresponding to the schema 
 * using the insertString.
 *
 * An entry of this class should be given in nmsInterfaces.conf 
 * under the Interfaces tag in the WebNMS/conf directory as follows:
 *
 *     dataDecoder ="test.TableCustomizationDecoder";
 *
 * @author Rajagopal N
 * @version $Revision: 1.1.1.1 $
 * @since WebNMS 2.3 + SP2
 */   
public class TableCustomizationDecoder implements DataDecoder
{
	/**
	 * Data collected from the device would be filled in the
     * CollectedInfo and passed to this method to make necessary
     * changes if any. Collected values are stored against the 
     * corresponding keys of PolledData in the CollectedInfo object.
	 * In this method, keys of PolledData are retrieved from the
     * CollectedInfo. Using these keys, the corresponding 
     * PolledData and the collected values are retrieved. 
     * Then the PolledData objects are checked for the policyName. 
     * If it matches "WindowsNT", then the values for the Oids 
     * 2.2.1.10,2.2.1.14 and 2.2.1.6 are inserted into the 
     * corresponding columns of CollectedInfo with the corresponding 
     * instances. Agent corresponding to the PolledData and the time 
     * of data collection are also inserted into the
	 * columns of CollectedInfo. Finally the collectedInfo is returned to
	 * store the collected values in the database after making
     * necessary changes.
	 */ 
	public CollectedInfo decode(CollectedInfo colinfo)
	{
        // retrieve keys of PolledData from CollectedInfo
		Vector v=colinfo.getKeys(); 
        
		String mainKey=null;

		Long timeofcol = new Long(colinfo.getTime());

		PolledData pd =null;
		long finalval=0;

		if(v!=null)
		{
			for(int i=0;i<v.size();i++)
			{
				if(((String)v.elementAt(i)).indexOf("2.2.1.10")>-1)
					mainKey=(String)v.elementAt(i);
				// Here mainKey refers to the PolledData key
				// to which an insertString is associated
				// in Polling.conf
			}

			for(int i=0;i<v.size();i++)
			{
				String key =(String)v.elementAt(i);
				Vector value =colinfo.getValue(key);
				pd=colinfo.getPolledData(key); 

				// check if any error message has been logged for
				// this PolledData. If no error message is
				// there , then proceed else leave this PolledData
				// and go to the next one.

				String errorString=colinfo.getErrorMessage(key);

				if(errorString!=null) continue;

				// check if the PolledData has been
				// formed under the PollingObject  "WindowsNT"
				
				if (pd.getPolicyName().equals("WindowsNT"))				
				{
					// if so , get all the instances 

					Vector inst =colinfo.getInstances(key);

					if(inst!=null)
					{

						// For each of these instances
						// we have to  explicitly fill the values
						// for columns IFINOCTETS , IFOUTOCTETS and
						// IFERRORS  with the collected values.
						// Use the method putValuesForColumn()
						// to insert values into these columns .


						for(int s=0;s<inst.size();s++)
						{
							String instance=(String)inst.elementAt(s);
							if(pd.getOid().indexOf("2.2.1.10")>-1)
							{

								colinfo.putValuesForColumn(mainKey,instance,"IFINOCTETS",colinfo.getValue(key,instance));
							}
							else if(pd.getOid().indexOf("2.2.1.14")>-1)
							{
								colinfo.putValuesForColumn(mainKey,instance,"IFERRORS",colinfo.getValue(key,instance));
							}
							else if(pd.getOid().indexOf("2.2.1.16")>-1)
							{
								colinfo.putValuesForColumn(mainKey,instance,"IFOUTOCTETS",colinfo.getValue(key,instance));
							}

							// Now the time of Collection and
							// agent has to be filled

							colinfo.putValuesForColumn(mainKey,instance,"TIMEOFCOL",timeofcol);
							colinfo.putValuesForColumn(mainKey,instance,"AGENT",pd.getAgent());
						}
					}
				}	
			}
		} 
        // finally return the CollectedInfo after making the changes
		return colinfo;    
	}
}













