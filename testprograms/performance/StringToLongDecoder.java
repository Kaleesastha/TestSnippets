
package test;

import java.util.*;
import java.io.*;
import com.adventnet.nms.poll.DataDecoder;
import com.adventnet.nms.poll.CollectedInfo;
import com.adventnet.nms.poll.PolledData;
//import com.adventnet.snmp.mibs.MibOperations;

/**
 * StringToLongDecoder.java
 * This class performs the conversion AkaraCounter64 to long values before saving it to the database
 * Keep in mind that this class is only called if you have specified the following entry in the nmsInterfaces.conf
 * file in the <Web NMS Home/conf> directory: dataDecoder = test.StringToLongDecoder
 *
 * @version 1.0
 * @date August 15, 2001
 */

public class StringToLongDecoder implements DataDecoder {

/**
 * Debug Flag
 */
 private static boolean DEBUG = true;

  public StringToLongDecoder() {
		if (DEBUG) {
    		System.out.println("Inside the Data Decoder");
		}
  	}
     public CollectedInfo decode(CollectedInfo colinfo)
     {
        String agent=colinfo.getAgent();

        Vector keys = colinfo.getKeys();
        if(keys == null)
            return colinfo;
        else
        {
            // here the user can check for any particular
            // agent .

            for(Enumeration e=keys.elements();e.hasMoreElements();)
            {

                String key =(String)e.nextElement();
                if(key==null)
                {
                    continue;
                }

                // here check for a particular key , if necessary.
                PolledData pd = colinfo.getPolledData(key);
                
                // if the oid is of type Octect String 
                // we convert the collected string value into a long
                // and store it in STATSDATA%.
                //This is done for all the instances of the PolledData.

                //get the OID type
                int pdOidType = pd.getNumericType();

              if(pdOidType == 2)
              {
                  long  id=pd.getId();
                  colinfo.setTableName(id ,"STATSDATA%");
                  colinfo.setType(id,1);
                  System.out.println(" converting string to long for key "+pd.getKey());
                  Hashtable ht=colinfo.getInstanceValuePair(key);

                  for(Enumeration en=ht.keys();en.hasMoreElements();)
                  {
                      String instance = (String)en.nextElement();
                      String classType = ht.get(instance).getClass().getName();

                      Object obj = (Object)ht.get(instance);

                      long longVal = 0L;
                      /*
                        char[] chars = obj.toString().toCharArray();
                      
                      for (int i = 0; i < chars.length; i++) {

                        if (chars[i] == ' ') {
                            continue;
                        }

                        long oneByte = chars[i];
                        oneByte &= 255;

                        longVal = longVal << 4;
                        if ((oneByte >= '0') && (oneByte <= '9')) {
                             longVal += oneByte - '0';
                        } else if ((oneByte >= 'a')&&(oneByte <= 'f')){
                             longVal += oneByte - 'a' + 10;
                        } else if ((oneByte >= 'A')&&(oneByte <= 'F')){
                             longVal += oneByte - 'A' + 10;
                        }
                      }
                      */

                      try{
                          
                          longVal=Long.parseLong((String)obj);
                      }
                      catch(Exception e1)
                      {
                          System.out.println(" exception converting data "+obj.toString()+ ", setting default value of 1000 ");
                          longVal=1000;
                      }

                      colinfo.setValue(pd ,longVal, instance);
                    }
                }

            }
        }
     return colinfo;
    }
}

