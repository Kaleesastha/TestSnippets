import com.adventnet.nms.topodb.*;

public class PollIntervalFilter implements FoundFilter
{
    public ManagedObject filterObject(ManagedObject obj, TopoAPI tapi) 
    {
        //Properties p = new Properties();
        obj.setPollInterval(10);
        obj.setTester("usertest");
        obj.setUClass("UserTest");
        return obj;
    }
}// PollIntervalFilter
