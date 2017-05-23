package test;
import com.adventnet.nms.topodb.*;

/*Discovery Filter to set the name of the node and ip address when the name is set as IP address*/
public class TestFilter implements FoundFilter
{
	private static int count = 0 ;

	public ManagedObject filterObject (ManagedObject obj, TopoAPI api) 
	{
		if (obj == null)
		{
			return null;
		}
		System.err.println("obj name : "+obj.getName());
		if(obj.getName().indexOf("192.168.138.40") != -1){
			String type = obj.getType().toLowerCase();
			if(type.indexOf("node") != -1){
				obj.setName("webnms-support");
			}
			else if(type.indexOf("interface") != -1){
				obj.setName("IF-webnms-support");
			}
			System.err.println("obj properties : "+obj.getProperties());
		}
		return obj;
	}
}

/*192.168.138.0   
172.24.10.0         
192.168.138.40  
172.24.10.104   
IF-192.168.138.40
IF-172.24.10.104
*/
