import com.adventnet.nms.topodb.*;
import java.rmi.Naming;

public class TopoOperations
{
    
    TopoAPI topoAPI= null;
    String hostName = null;

    public TopoOperations(String hostName)
    {
        this.hostName=hostName;
        getAPI();
        new Thread(new AddRunnable()).start();
        new Thread(new DeleteRunnable()).start();
        new Thread(new UpdateRunnable()).start();
    }
    
    private void getAPI()
    {
        try
        {
            topoAPI = (TopoAPI)Naming.lookup("//"+hostName+"/TopoAPI");
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
    }

    public static void main(String[] a)
    {
        if(a.length != 1)
        {
            System.out.println(" java TopoOperations hostName");
            return;
        }
        new TopoOperations(a[0]);
    }

    class AddRunnable implements Runnable
    {
        public void run()
        {
            System.out.println(" TOPO ADD RUNNABLE STARTED ==================");
            for(int i=26;i<=50;i++)
            {
                for(int j=1;j<=255;j++)
                {
                    ManagedObject object = new ManagedObject();
                    String name = "192.168."+i+"."+j;
                    object.setName(name);
                    object.setDisplayName(name);
                    try
                    {
                        if(topoAPI.getByName(name)==null)
                        {
                            topoAPI.addObject(object);
                            System.out.println(" Added ManagedObject =="+name);
                        }
                    }
                    catch(Exception ee)
                    {
                        System.out.println(" Exception while adding =="+name+" "+ee);
                        ee.printStackTrace();
                    }
                }
            }
            System.out.println(" TOPO ADD RUNNABLE COMPLETED ==================");
        }
    }

    class DeleteRunnable implements Runnable
    {
        public void run()
        {
            System.out.println(" TOPO DELETE RUNNABLE STARTED ==================");
            for(int i=50;i>=26;i--)
            {
                for(int j=1;j<=255;j++)
                {
                    String name = "192.168."+i+"."+j;
                    ManagedObject obj = null;
                    try
                    {
                        obj = topoAPI.checkOut(name,5);
                        if(obj == null)
                        {
                            continue;
                        }
                        topoAPI.deleteObject(obj,true,true);
                        System.out.println(" deleted ManagedObject =="+name);
                        topoAPI.unlock(obj);
                    }
                    catch(Exception ee)
                    {
                        System.out.println(" Exception while deleting =="+name+" "+ee);
                        ee.printStackTrace();
                        try
                        {
                            topoAPI.unlock(obj);
                        }
                        catch(Exception e)
                        {
                            ee.printStackTrace();
                        }
                    }
                }
            }
            System.out.println(" TOPO DELETE RUNNABLE COMPLETED ==================");
        }
    }

    class UpdateRunnable implements Runnable
    {
        public void run()
        {
            System.out.println(" TOPO UPDATE RUNNABLE STARTED ==================");
            for(int i=26;i<=50;i++)
            {
                for(int j=1;j<=255;j++)
                {
                    String name = "192.168."+i+"."+j;
                    ManagedObject obj = null;
                    try
                    {
                        obj = topoAPI.checkOut(name,5);
                        if(obj == null)
                        {
                            continue;
                        }
                        obj.setWebNMS(String.valueOf(System.currentTimeMillis()));
                        topoAPI.updateObject(obj,true,true);
                        System.out.println(" updated ManagedObject =="+name);
                        topoAPI.unlock(obj);
                    }
                    catch(Exception ee)
                    {
                        System.out.println(" Exception while updating =="+name+" "+ee);
                        ee.printStackTrace();
                        try
                        {
                            topoAPI.unlock(obj);
                        }
                        catch(Exception e)
                        {
                            ee.printStackTrace();
                        }
                    }
                }
            }
            System.out.println(" TOPO UPDATE RUNNABLE COMPLETED ==================");
        }
    }
}


