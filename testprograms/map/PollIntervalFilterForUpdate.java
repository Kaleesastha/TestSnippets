import com.adventnet.nms.topodb.*;

public class PollIntervalFilterForUpdate implements FoundFilter
{
    public ManagedObject filterObject(ManagedObject obj, TopoAPI tapi) 
    {
        //Properties p = new Properties();
        if(obj.getKey().equals("ramanr.india.adventnet.com"))
        {
            obj.setPollInterval(1);
            obj.setTester("usertest");
            obj.setUClass("UserTestForUpdate");
        }
        return obj;
    }
}// PollIntervalFilter
