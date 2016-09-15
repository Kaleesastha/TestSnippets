package test;
import com.adventnet.nms.startnms.*;
import com.adventnet.nms.util.*;
public class QueueBE implements SocketServerConnectionBE , SocketSessionConnectionBE , RunProcessInterface
{
    private MainSocketSessionBE mss=null;
    
    public void callMain(String[] args)
    {
        try
        {
            PureServerUtilsBE.serverSocketBE.registerForResponses(this);
            while(mss==null) Thread.sleep(100);
            Thread.sleep(2*60000);//Wait For FE to connect to BE
            mss.send("QUEUE","FE_BUSY",new byte[0]);//No Internationalization
        }
        catch(Exception e)
        {
            System.err.println("Exception In Test Program - QueueBE");//No Internationalization
            e.printStackTrace();
        }
    }
    public boolean isInitialized()
    {
        return true;
    }
    public void shutDown()
    {
        return;
    }
    public void init(MainSocketSessionBE mss)
    {
        this.mss=mss;       
    }

    public void receive(String uid, byte[] data)
    {
        
    }
    
    public void close()
    {
    }


    
}
