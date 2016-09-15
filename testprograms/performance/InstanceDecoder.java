/*
   $Id: InstanceDecoder.java,v 1.1 2001/09/10 08:33:41 sudhal Exp $
 */

/**
 * DataDecoder.java
 */
package test;
import java.util.Enumeration;
import java.util.Vector;
import java.util.Hashtable;
import com.adventnet.nms.poll.PolledData;
import com.adventnet.nms.poll.DataDecoder;
import com.adventnet.nms.poll.CollectedInfo;
    



/**
 * This is an example for data deocder .
 * The collected data from an agent could be in any encoded form and the user
 * would like to decode the data before it is stored.In such cases , the
 * DataDecoder interface has to be implemented by the user.As soon as data
 * get collected, it will be passed through this user class , where the
 * user can decode/ modify data and return the modified data for storage.
 * And the name of the implementing class has to be specified in
 * <b> nmsinterfaces.conf</b>
 * as <B> dataDecoder = "MyDecoderClass"</B> under INTERFACES  tag.

 * @author <a href="mailto:Administrator@HUPREMA"></a>
 * @version 1.0
 * @since 1.0
 
 */
public class InstanceDecoder  implements DataDecoder{
     
    /**
     * decodes collected data.  In this method , collected data is
     * passed to the user for decoding , the user decodes it and
     * returns data in its modified form.In this example, collected
     * data for any PolledData whose name starts with "INTERFACE_in"
     * is converted to a string value if it is of type long  and stored
     * in STRINGDATA% table instead of STATSDATA%.
     * 
     * @param colinfo a <code>CollectedInfo</code> object which
     * contains collected data.
     * @return a <code>CollectedInfo</code> object after necessary
     * modifications from which data will be retrived and stored.  */

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
                // if the oid name starts with "INTERFACE_in"
                // we convert the collected long value into string
                // and store it in STRINGDATA%.
                //This is done for all the instances of the PolledData.
//                if(pd.getName().startsWith("INTERFACE_in"))
                   if(pd.getIsMultiplePolledData())
             {
                    long  id=pd.getId();
                    //colinfo.setTableName(id ,"STRINGDATA%");
                    //colinfo.setType(id,2);
                    Hashtable ht=colinfo.getInstanceValuePair(key);

                    Hashtable hasht=new Hashtable();
                    
                    if(ht==null) return colinfo;

                    int i=1;
                    for(Enumeration e1=ht.elements();e1.hasMoreElements();)
                    {
                        Object val=(Long)e1.nextElement();

                        hasht.put("x"+i,val);
                        i++;
                    }

                    colinfo.setInstanceValuePair(key,hasht);
                }

            }
        }
        
        return colinfo;
    }
    
}
