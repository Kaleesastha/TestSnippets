import com.adventnet.nms.topodb.*;
import com.adventnet.nms.netwatch.UserTester;

public class UserTest implements UserTester
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
        //System.out.println("-- returning " + i);
        return statusVals[val];
    }
}// UserTest
