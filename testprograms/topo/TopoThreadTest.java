import com.adventnet.nms.topodb.*;
import java.rmi.*;
import java.util.*;
import test.*;
import com.adventnet.nms.severity.*;
import javax.transaction.RollbackException;
import com.adventnet.nms.util.*;
import com.adventnet.management.transaction.*;

public class TopoThreadTest
{

    public TopoAPI getHandle( String str )
    {
        TopoAPI topo = null;
        try
        {
            while(topo == null)
            {
                System.out.println("--trying to connect host : " + str);
                topo = (TopoAPI)Naming.lookup("rmi://" + java.net.InetAddress.getByName(str).getHostName() + "/TopoAPI");
                Thread.sleep(1000);
            }
        }
        catch(Exception e)
        {
            System.out.println("Error in getting handle. Quitting...");
            e.printStackTrace();
            System.exit(0);
        }
        return topo;
    }

    public static  void main(String[] args)
    {
        TopoThreadTest cct = new TopoThreadTest();
        TopoAPI topo=cct.getHandle("ms-1test1");
        System.out.println("Got the handle");
        
        /*
        try
        {
            Node no = new Node();
            no.setName(name);
            System.out.println("-- Adding node : " + topo.addObject(no));
        }
        catch(Exception ee)
        {
            System.out.println("Exception adding node : " + ee);
            ee.printStackTrace();
        }
        */

        for ( int i = 1; i <= 3; i++ )
            new TestThread("Thread"+i,topo).start();

//          new TestThread(topo).start();
        
    }
}

class TestThread extends Thread
{
    TopoAPI topo=null;
    String name = null;

    public TestThread(String name, TopoAPI api)
    {
        super(name);
        topo=api;
        this.name = name;
    }
    
    public void run()
    {
        for ( int i = 1; i <= 5; i++ )
        {
            try
            {
                /*
                  Node node = new Node();
                  node.setIpAddress("192.168.9.151");
                  node.setNetmask("255.255.255.0");
                  System.out.println("addNode : " + name + " : " + topo.addNode(node));
                */
                ManagedObject mo = topo.checkOut("AAAA");
                mo.setDisplayName(name+i);
                System.out.println(name + " updating " + mo + " : " + topo.updateObject(mo,false,true));
            
            } catch( Exception e )
            {
                System.out.println("== Exception while performing : " + name + " : " + i);
                e.printStackTrace();
            }
        }
    }
}
