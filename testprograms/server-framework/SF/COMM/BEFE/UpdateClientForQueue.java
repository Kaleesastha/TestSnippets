package test;
import com.adventnet.nms.startnms.*;
import com.adventnet.nms.util.*;
public class UpdateClientForQueue implements UpdateClient, RunProcessInterface
{
    
    public void callMain(String[] args)
    {
        try
        {
            Thread.sleep(5000);
            PureServerUtilsFE.clientSocketFE.registerForUpdates("FE_BUSY",this);//No Internationalization
        }
        catch(Exception e)
        {
            System.err.println("Exception In Test Program - UpdateClient ");//No Internationalization
            e.printStackTrace();
        }
    }

    public boolean isInitialized()
    {
        return true;
    }
    
    public void shutDown()
    {
    }
    
    public void send(String req, String uid , byte[] data)
    {
        try
        {
            System.err.println("Making FE Busy..........");//No Internationalization
            Thread.sleep(7*60000);//For Refreshing- //Send Traps by this time
            //Thread.sleep(12*60000);//For Cleanup - //Send Traps by this time
            System.err.println("Overrr.........");//No Internationalization
            return;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}
