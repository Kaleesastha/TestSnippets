package test;
import com.adventnet.nms.util.*;
import java.util.*;
import com.adventnet.nms.topodb.*;
import java.io.*;
import java.rmi.Naming;
import com.adventnet.management.log.SystemUtil;
public class TopoAPITest {
	public static void main(String args[])
	{
		TopoAPI topo = null;
		try{
			topo = (TopoAPI)Naming.lookup("//172.24.14.34/TopoAPI");
			//topo = (TopoAPI)Naming.lookup("rmi://172.24.14.34/TopoAPI");
			//topo = (TopoAPI)Naming.lookup("rmi://localhost/TopoAPI");
			//topo = (TopoAPI)Naming.lookup("//localhost/TopoAPI");
			System.err.println("Got TopoAPI handle"); //Will log them in stderr
		}
		catch(Exception e){}
		try {
			ManagedObject mobj = topo.getByName(args[0]);
			System.err.println("Properties: "+mobj.getProperties());
			Properties criteria = new Properties();
			Vector MOVector  =  topo.getObjects("ChassisDevice",criteria);
			ListIterator iter = MOVector.listIterator();
			while (iter.hasNext()) {
				ManagedObject obj = (ManagedObject)iter.next();
				System.err.println("==>"+obj.getProperties());
			}   
		}
		catch(Exception e) {
			System.err.println("Exception  : " + e);
		}
	}
}
