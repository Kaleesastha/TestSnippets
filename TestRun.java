package test;
import com.adventnet.nms.commonfe.GenericFEAPIImpl;
import com.adventnet.nms.topodb.TopoAPI;
import com.adventnet.nms.util.NmsUtil;
import java.util.*;
public class TestRun implements Runnable
{
	TopoAPI topo = null;
	Vector activeUsers;
	List actualUsers;
	public void run()
	{
                while (topo == null)
                {
                        try{
                                topo = (TopoAPI)NmsUtil.getAPI("TopoAPI");
                                System.err.println("Got TopoAPI handle"); //Will log them in stderr
                                Thread.sleep(5000);
                        }
                        catch(Exception e){e.printStackTrace();}
                }
		try{
			System.err.println("Number of Managed Objects are::  "+topo.getNumObjects());
		}
		catch(Exception e){e.printStackTrace();}
	    try
	    {
		    activeUsers = GenericFEAPIImpl.getAPI().getActiveUsers();
		    actualUsers = GenericFEAPIImpl.getAPI().getActualUsers();
	    }
	    catch(Exception re)
	    {
		    re.printStackTrace();
	    }
	    if(activeUsers !=null)
	    {
		    System.err.println("Active users are:");
		    System.err.println("***********************************");
		    for(Iterator iter = activeUsers.iterator(); iter.hasNext();)
		    {
			    System.err.println("==>"+iter.toString());
		    }
		    System.err.println("***********************************");
		    System.err.println("No. of active users are:"+activeUsers.size());
	    }
	    if(actualUsers !=null)
	    {
		    System.err.println("Actual users are:");
		    System.err.println("***********************************");
		    for(Iterator iter = actualUsers.iterator(); iter.hasNext();)
		    {
			    System.err.println("==>"+iter.toString());
		    }
		    System.err.println("***********************************");
		    System.err.println("No. of actual users are:"+activeUsers.size());
	    }
	}
}
