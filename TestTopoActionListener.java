package test;

import java.rmi.RemoteException;

import com.adventnet.nms.topodb.ManagedObject;
import com.adventnet.nms.topodb.Network;
import com.adventnet.nms.topodb.Node;
import com.adventnet.nms.topodb.SnmpInterface;
import com.adventnet.nms.topodb.SnmpNode;
import com.adventnet.nms.topodb.TopoAPI;
import com.adventnet.nms.topodb.TopoActionListener;
import com.adventnet.nms.topodb.TopoNotificationEvent;
import com.adventnet.nms.util.LockableObject;
import com.adventnet.nms.util.NmsUtil;

public class TestTopoActionListener implements TopoActionListener  {
	TopoAPI api = null;
	public void update(TopoNotificationEvent event) throws RemoteException {
		api = (TopoAPI)NmsUtil.getAPI("TopoAPI");
		String type = event.getUpdateType().toLowerCase().trim();
		System.out.println(" The event is " + event + " The Type is "+ type);
		if(type.startsWith("Added".toLowerCase().trim()))
		{
			try
			{
				ManagedObject mo= event.getNewManagedObject();
				System.out.println("Managed Object Added" + mo.getName());
				
				if (mo instanceof Node)
				{
					api.lock(mo, LockableObject.WRITE_LOCK, 5);
					mo.setUserProperty("CUSTOM_PROPERTY_1", "VALUE_1");
					mo.setUserProperty("CUSTOM_PROPERTY_2", "VALUE_2");
					api.updateObject(mo, false, false);
					api.unlock(mo);
				}
				//if ((mo instanceof SnmpNode) || (mo instanceof SnmpInterface))
				//{
				
				//SampleThread st = new SampleThread(mo);
				//st.start();
				
					/*api.lock(mo, LockableObject.WRITE_LOCK, 5);
					mo.setUserProperty("CUSTOM_PROPERTY_1", "VALUE_1");
					mo.setUserProperty("CUSTOM_PROPERTY_2", "VALUE_2");
					api.updateObject(mo, false, false);
					api.unlock(mo);*/
				//}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
		else if(type.startsWith("Deleted".toLowerCase().trim()))
		{
		}
		else if(type.startsWith("Manage".toLowerCase().trim()))
		{
		}
		else if(type.startsWith("Status".toLowerCase().trim()))
		{			
		}
		else if(type.startsWith("StatusPollEnabled".toLowerCase().trim()))
		{
		}
		else if(type.startsWith("Discover".toLowerCase().trim()))
		{
		}
		else if(type.startsWith("Property".toLowerCase().trim()))
		{
		}
		else if(type.startsWith("IpAddresss".toLowerCase().trim()))
		{
		}
		else if(type.startsWith("License".toLowerCase().trim()))
		{
		}
	}
}