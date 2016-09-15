import com.adventnet.nms.topodb.*;
import com.adventnet.nms.netwatch.UserTester;

public class UserTestForUpdate implements UserTester
{
    static String[] types = {"type1","type2","type3","type4","type5"};
    static int[] statusVals = {1,2,3,4,5};
    static int i = 0;

    public int test(ManagedObject obj, TopoAPI api)
    {
        //System.out.println("-- Usertest for " + obj);
        i++;
        if(i >= 5)
            i = 0;
        int val = i;
        obj.setType(types[i]);
        try
        {
            api.updateObject(obj);
        }
        catch(Exception ee)
        {
            System.err.println("Exception in Usertest ");
            ee.printStackTrace();
        }
        if(obj.getKey().equals("ramanr.india.adventnet.com"))
        {
            new MyThread(obj, api).start();
        }
        //System.out.println("-- returning " + i);
        return statusVals[val];
    }
}// UserTest

class MyThread extends Thread
{
    ManagedObject obj = null;
    TopoAPI api = null;

    public MyThread(ManagedObject obj, TopoAPI api)
    {
        this.obj = obj;
        this.api = api;
    }
    public void run()
    {
        int i = obj.getStatus();
        if (i>=5) i =0;
        i = i+1;
        obj.setStatus(i);
        try
        {
            api.updateObject(obj);
        }
        catch(Exception ee)
        {
            System.err.println("Exception in Usertest_Thread ");
            ee.printStackTrace();
        }
    }
}
