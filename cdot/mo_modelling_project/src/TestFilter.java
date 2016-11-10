package test;
import com.adventnet.nms.topodb.*;
import java.sql.Timestamp;
import java.sql.Date;
import java.sql.Time;
import java.util.Vector;
import corechassis.ngems.devicemodel.XMLNode_Port;
import common.mo.model.ChassisDevice;

public class TestFilter implements FoundFilter
{
	private static int count = 0 ;

	public ManagedObject filterObject (ManagedObject obj, TopoAPI api) 
	{
		if (obj == null)
		{
			return null;
		}
		/*if (obj instanceof SnmpNode) 
		{
			XMLNode_Port  md = new XMLNode_Port();
			md.setName(obj.getName()+"_"+String.valueOf(count));
			md.setPurpose(obj.getName()+"_purpose");
			count++;

			// If an object is returned from this method, an existence check is done. 
			// If it is already present in the database, the object will not be added 
			// Else, the object is directly added to the database.
			try{
				api.addObject (md);
			}
			catch(Exception e){
				System.err.println("Error in adding TestMO1");
				e.printStackTrace();
			}                      

		}*/
		//else if (obj instanceof Node) 
		if (obj instanceof Node) 
		{
                        ChassisDevice cd = new ChassisDevice();
                        cd.setName(obj.getName()+"xx"+String.valueOf(count));
                        cd.setModelNumber(obj.getName()+"---"+String.valueOf(count));
                        count++;
                        String lastValue = String.valueOf(count);
                        String ipAddress = "192.168.100."+lastValue;
                        Vector v = new Vector();
                        v.add(ipAddress);
                        //cd.setIpaddrs(v);
                        System.err.println("properties: "+cd.getProperties());
                        try{
                                api.addObject (cd);
                        }   
                        catch(Exception e){ 
                                e.printStackTrace();
                        }    
		}
		return obj;
	}
}
