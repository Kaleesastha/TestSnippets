import com.adventnet.nms.topodb.*;
import com.adventnet.nms.store.relational.*;
import com.adventnet.nms.util.*;

import java.rmi.*;
import java.rmi.server.*;
import java.io.*;

public class TestTopoListener implements TopoListener, Serializable
{
    static String[] arg = null;
	static int count = 0;
    XMLDataWriter writer = null;
    TopoAPI api = null;
    
    public void start() 
    {
	try{
	    api = (TopoAPI)Naming.lookup("rmi://"+arg[0]+"/TopoAPI");
	}
	catch(Exception e)
	{
	    System.out.println("CONNECTION PROBLEM "+e);
	    System.exit(1);
	}
	System.out.println(" CONNECTION SUCCESSFUL");
	
	try 
	{
	    api.addTopoListener(this);
		if(arg[1].equalsIgnoreCase("withowner"))
		{
			System.out.println("Attemptingg to delete "+arg[2]+"\t"+arg[3]+" and all its sub elements" + api.deleteObjectAndSubElements(arg[2]+"\t"+arg[3]));
		}
		else
		{
		System.out.println("Attempting to delete "+arg[1]+" and all its sub elements" + api.deleteObjectAndSubElements(arg[1]));
		}
		
	}
	catch(Exception e2)
	{
	    System.out.println(" ldsjgjl"+e2);
		e2.printStackTrace();
	}
    }

    public boolean deleteObject(XMLNode data) throws NmsException
    {
	count++;
	System.out.println("+++++++++++++ Deleteobject CALLED+++++++++ \n");
	writer = new XMLDataWriter("test.file"+count,data);
	//throw new NmsException();
	return true;
    }

    public static void main(String[] args)
    {
	arg = args;
	if(args.length < 2) 
	{
	    System.out.println(" Usage: TestTopoListener hostname objectnameToDelete");
	    System.exit(1);
	}
	if(args[1].equalsIgnoreCase("withowner"))
	{
		if(args.length < 4)
		{
			System.out.println(" Usage: TestTopoListener hostname withowner objectnameToDelete ownername");
			System.exit(1);
		}
	}
	
	new TestTopoListener().start();
    }
}



