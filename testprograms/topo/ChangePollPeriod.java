/* $Id: ChangePollPeriod.java,v 1.2 2003/06/09 12:04:39 priya Exp $
 *
 * File Name      : ChangePollPeriod.java
 * Description    : To modify the poll interval other than switch
 * Other Info     : Compile this program and place it inside <WebNMS_HOME>/class directory and 
 *                  give the entry inside the discovery_filters in the conf directory and start the server
 *
 * USAGE          : Compile this program and place it inside <WebNMS_HOME>/class directory and 
 *                  give the entry inside the discovery_filters in the conf directory and start the server
 * Parameter Desc :                 
 *
 * Owner Name     : Priya
 * Change History(Author Date(dd-mm-yyy) and Description of methods added/modifed/deleted):
 */
package test;
import java.util.*;
import com.adventnet.nms.topodb.*;
import com.adventnet.nms.util.*;
public class ChangePollPeriod implements FoundFilter
{
    String object=null;  
    public ManagedObject filterObject(ManagedObject obj,TopoAPI api)
    {
        if(obj.getType().equals("Switch"))
        {
            return null;
        }
       else
        {
            obj.setPollInterval(1);
	    obj.setStatusPollEnabled(true);
	    return obj;
    }
    
}

