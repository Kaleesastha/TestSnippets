/*
  $Id: LongToStringDecoder.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
 * Copyright (c) 2002 Adventnet, Inc. All Rights Reserved.
 * Please read the associated COPYRIGHTS file for more details 
 * 
 * ADVENTNET, INC. MAKES NO REPRESENTATIONS OR WARRANTIES  ABOUT THE SUITABILITY
 * OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
 * NON-INFRINGEMENT. ADVENTNET, INC. SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS
 * SOFTWARE OR ITS DERIVATIVES.  
*/

package test;
import java.util.Enumeration;
import java.util.Vector;
import java.util.Hashtable;
import com.adventnet.nms.poll.PolledData;
import com.adventnet.nms.poll.DataDecoder;
import com.adventnet.nms.poll.CollectedInfo;


/**
 * This is an example for data decoder.
 * The collected data from an agent could be in any encoded form which is 
 * hard to read and understand and the user would like to decode the data
 * before it is stored. In such cases, the DataDecoder interface has to be
 * implemented by the user. As soon as data get collected, it will be passed
 * through this user class, where the user can decode/ modify data and return
 * the modified data for storage.
 * And the name of the implementing class has to be specified in
 * <b> nmsinterfaces.conf</b>
 * as <B> dataDecoder = "LongToStringDecoder"</B> under INTERFACES  tag.
 * In this example, collected data for any PolledData whose name starts with
 * "INTERFACE_in" is converted to a string value if it is of type long 
 * and stored in STRINGDATA% table instead of STATSDATA%. 
 */

public class LongToStringDecoder  implements DataDecoder
{
    
    /**
     * This method decodes / modifies  collected data.  In this method ,
     * collected data is 
     * passed to the user for decoding / modification and returned CollectedInfo
     * object will have data in its modified form. In this example, collected
     * data for any PolledData whose name starts with "INTERFACE_in"
     * is converted to a string value if it is of type long  and stored
     * in STRINGDATA% table instead of STATSDATA%.
     * 
     * @param colinfo a <code>CollectedInfo</code> object which
     * contains collected data and the corresponding PolledData objects.
     * @return a <code>CollectedInfo</code> object after necessary
     * modifications from which data will be retrieved and stored.
     */
    
     public CollectedInfo decode(CollectedInfo colinfo)
     {

         //data collection is done for a group of PolledData objects
         // at a time. Grouping of PolledData objects is done based on Agent
         // name , protocol used and the period of data collection.
         //Thus PolledData with the same agent, period and protocol will be
         // polled together and the collecteddata will be put in a single
         // CollectedInfo object.

         //get the agent for which data is collected.
        String agent=colinfo.getAgent();

        Vector keys = colinfo.getKeys();

        if(keys == null)
            return colinfo;
        else
        {
            /**
	     * Vector of keys of PolledData are enumerated to check
	     * for particular key of PolledData whose name starts with
             * "INTERFACE_in".
	     */

            for(Enumeration e=keys.elements();e.hasMoreElements();)
            {

                String key =(String)e.nextElement();

                if(key==null)
                {
                    continue;
                }
                
                PolledData pd = colinfo.getPolledData(key);
				
                /**
		 * If the name of the PolledData starts with "INTERFACE_in"
                 * we convert the collected long value into string
		 * value and store it in STRINGDATA%. This is done for all
		 * the instances of the PolledData.
		 */

                if(pd.getName().startsWith("INTERFACE_in"))
                {
                    long  id=pd.getId();

                    /*
                     * Change the table name to STRINGDATA%.
                     * Note that associating a table name with a PolledData will
                     * not create the table. The table should be created either
                     * at run time 
                     * through API (using PollAPI methods addCreateSchema and
                     * createTable ) or at startup by giving an entry in
                     * DatabaseSchema.conf . In this case , STRINGDATA% is the
                     * default table which will be created by NMS at startup.
                     */
                    colinfo.setTableName(id ,"STRINGDATA%");

                    /*
                     * Change the type of collected data to 'string'.
                     * 1 denotes long type of data and a value of 2 denotes
                     * string.
                     */
                    colinfo.setType(id,2);

                    /*
                     * collected value for any PolledData is put in a hash table.
                     * The format of the hash table is as follows.
                     * [instance-1  value-1]
                     * [instance-2  value-2]
                     * ......
                     * .......
                     * [instance-n  value-n]
                     *
                     * for PolledData objects there will be only one instance
                     * "-1" . For multiplePolledData objects , more than one
                     * instance may be present. The instance-n is a string and
                     * the value-n is either a Long or String object. 
                     */
                     
                    Hashtable ht=colinfo.getInstanceValuePair(key);

                    if(ht==null) return colinfo;

                  
                    for(Enumeration en=ht.keys();en.hasMoreElements();)
                    {
                        String instance = (String)en.nextElement();
                        Object obj =(Object)ht.get(instance);
                        
                        String stringVal=null;
                        //The value could be either String or Long. If it is a
                        // Long convert it into a String and put the modified
                        // value (long value is converted into a string) in the
                        // CollectedInfo object.
                        if(obj instanceof Long)
                            stringVal = ((Long)obj).toString();

                        if(stringVal!=null)
                        colinfo.setValue(pd ,stringVal ,instance);
                        break;
                    }
                }

            }
        }
        
        return colinfo;
    }
    
}
















