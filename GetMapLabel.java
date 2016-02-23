package test;
import com.adventnet.nms.mapdb.*;
import com.adventnet.nms.util.*;
import java.util.*;

public class GetMapLabel implements RunProcessInterface
{
	public void callMain(String args[])
	{
		try{
			Thread.sleep(20000);
			System.err.println("After 20 seconds!!!");
			MapAPI api = (MapAPI)NmsUtil.getAPI("MapAPI");
			if(api!=null)
			{
				Vector vec = api.getDefaultMapNames();
				for(Iterator iter = vec.iterator(); iter.hasNext();)
				{
					String mapName = iter.next().toString();
					System.err.println("map name is::"+mapName);
					MapDB mapdb = api.getMap(mapName);
					System.err.println("label for "+mapName+" is::"+mapdb.getLabel());
				}
			}
		}
		catch(Exception e)
		{
			System.err.println("this test is Fail");
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
}
