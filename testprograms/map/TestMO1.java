//Can be used for testing TopoAPI related test cases in map_api_utc_tp.html
import com.adventnet.nms.topodb.*;

import java.rmi.Naming;
import java.util.*;

public class TestMO1
{
	public static void main(String args[]) throws Exception
	{
		//AddObject();
		//AddObjects();
		//AddNode();
	    //UpdateObject();
		//AddNodeToTopoDB();
		//DeleteMO();
		DeleteMOs();
//		TopoAPI api = (TopoAPI)Naming.lookup("//localhost/TopoAPI");
//		api.deleteObjectAndSubElements("archana.india.adventnet.com");
//		api.removeNodeFromTopoDB("192.168.112.64");
	
	}
	static void AddObject() throws Exception
	{
		TopoAPI api = (TopoAPI)Naming.lookup("//localhost/TopoAPI");
		ManagedObject mo1 = new ManagedObject();
		mo1.setName("NE1");
		mo1.setStatus(3);
		mo1.setType("node");
		System.out.println("Result of addObject : "+api.addObject(mo1,true,false));	
	}
	
	static void AddObjects() throws Exception
	{
		TopoAPI api = (TopoAPI)Naming.lookup("//localhost/TopoAPI");
		Vector MoVect = new Vector();

		ManagedObject mo1 = new ManagedObject();
		mo1.setName("NE2");
		mo1.setStatus(3);
		mo1.setType("node");
		MoVect.addElement(mo1);
		
		ManagedObject mo2 = new ManagedObject();
		mo2.setName("NE3");
		mo2.setStatus(3);
		mo2.setType("node");
		MoVect.addElement(mo2);
		
		ManagedObject mo3 = new ManagedObject();
		mo3.setName("NE4");
		mo3.setStatus(3);
		mo3.setType("node");
		MoVect.addElement(mo3);

		api.addObjects("ManagedObject",MoVect);
	}

	static void AddNode() throws Exception
	{
		TopoAPI api = (TopoAPI)Naming.lookup("//localhost/TopoAPI");
		Node mo1 = new Node("192.168.110.164","255.255.255.0");
		mo1.setStatus(3);
		System.out.println("Result of addNode : "+api.addNode(mo1,true,true,true));
	}
	
	static void AddNodeToTopoDB() throws Exception
	{
		TopoAPI api = (TopoAPI)Naming.lookup("//localhost/TopoAPI");
		System.out.println("Result of addNodeToTopoDB : "+api.addNodeToTopoDB("192.168.110.162","255.255.255.0"));
	}

	
	static void UpdateObject() throws Exception
	{
		TopoAPI api = (TopoAPI)Naming.lookup("//localhost/TopoAPI");
		ManagedObject mo1 = api.getByName("abbas.india.adventnet.com");
		mo1.setStatus(4);
		//mo1.setUserProperty("prop1","group1");
		//mo1.setType("node");
		System.out.println("Result of UpdateObject() : "+api.updateObject(mo1,true,false));
	}
	
	static void DeleteMO() throws Exception
	{
		TopoAPI api = (TopoAPI)Naming.lookup("//localhost/TopoAPI");
		ManagedObject mo1 = api.getByName("192.168.9.0");
		System.out.println("Result of deleteObject : "+api.deleteObject(mo1,true,false));
	}

	static void DeleteMOs() throws Exception
	{
		TopoAPI api = (TopoAPI)Naming.lookup("//localhost/TopoAPI");
		Properties prop=new Properties();
		prop.put("name","*");
		api.deleteObjects(null,prop);
	}

}