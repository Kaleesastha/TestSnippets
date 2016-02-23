

/**
 * DiscoveryTest.java
 *
 *
 * Created: Thu May 02 18:56:06 2002
 *
 * @author <a href="mailto: "</a>
 * @version
 */
package test;

import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.util.RunProcessInterface;
import com.adventnet.nms.topodb.TopoAPI;
import com.adventnet.nms.topodb.ManagedObject;
import com.adventnet.nms.topodb.IpAddress;
import com.adventnet.nms.topodb.TopoObserver;

public class DiscoveryTest_Time implements TopoObserver, RunProcessInterface
{
    static int count = 0;
    static int time =0;
    static long cumulativeTime = 0;
    TopoAPI api = null;
    long startTime = 0;
    long endTime = 0;
    public DiscoveryTest_Time ()
    {
        
    }

    ////////RunProcessInterface
    public void callMain(String[] args)
    {
        System.out.println(" Entered ");
	while (api == null)
        {
            api = (TopoAPI)NmsUtil.getAPI("TopoAPI");
            if (api != null)
            {
                break;
            }
            try
            {
                Thread.sleep(5000);
		    startTime = System.currentTimeMillis();

            }
            catch (Exception e)
            {
            }
        }
        register();
    }

    private void register()
    {
        try
        {
            api.register(this);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public boolean isInitialized ()
    {
        return true;
    } 

    public void shutDown()
    {
    
    }

    ////////TopoObserver methods
    public void update (String type, ManagedObject obj)
    {
        if (type == null)
        {
            return;
        }
        if (type.equalsIgnoreCase("Added"))
        {
            if (obj instanceof IpAddress)
            {
			endTime = System.currentTimeMillis();
			count++;
			
		}
		long diff = endTime-startTime;
		if ( diff >=30000) {
			time+=30;
			cumulativeTime = (long)(cumulativeTime+diff);
			System.out.println(" ********************************************* ");
			System.out.println(" count is " + count);
			System.out.println(" diff in millisec "+diff);
			System.out.println(" Discovery rate after "+time+" Seconds : " + Math.ceil((double)(count*60*1000)/cumulativeTime)+ " Nodes per Minute");
			System.out.println(" ********************************************* ");
         		startTime = System.currentTimeMillis();  
			
		}
	  }
	 	
    }

    public void update (String type, String name)
    {
        System.err.println(" dummy calle");
    }
}// DiscoveryTest

            
