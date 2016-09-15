package test;
import com.adventnet.nms.topodb.*;
import java.util.*;

public class OwnerNameSetter implements FoundFilter
{
    public ManagedObject filterObject(ManagedObject obj,TopoAPI api)
    {
	System.out.println("owner name Setter called for "+obj);
	if(obj == null) return null;
	
//	obj.setOwnerName("AAA");

	if(obj instanceof Node)
	{
	    boolean belongs = false;
	    Node node = (Node) obj;
	    Vector v = node.getParentNets();
	    for (Enumeration e = v.elements();e.hasMoreElements();)
	    {
		if((e.nextElement()).equals("192.168.1.0"))
		{
		    belongs = true;
		    break;
		}
	    }
	    
	    if(belongs)
	    {
		node.setOwnerName("kashyap");
		return node;
	    }
	    
	}
	
	else if(obj instanceof IpAddress)
	{
	    IpAddress ip = (IpAddress)obj;
	    if( (ip.getParentNet() != null) && (ip.getParentNet().equals("192.168.1.0")) )
		ip.setOwnerName("kashyap");
	    return ip;
	}
	
/*	else if(obj instanceof Network)
	{
	    if( (((Network)obj).getName()).equals("192.168.1.0"))
		obj.setOwnerName("kashyap");
	}
*/	
	return obj;
    }
}












