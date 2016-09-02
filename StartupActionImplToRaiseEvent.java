/*
In conf/nmsInterfaces.conf, configure StartUpAction="com.adventnet.nms.admin.StartupActionImplToRaiseEvent" as the last entry.
(After changing nmsInterfaces.conf, save this and open in a browser OR execute xmllint command for ensuring proper syntax )

5. Now start the PRIMARY & STANDBY servers.

The result of the configuration is as follows:
After a BE failover, an event is raised with severity critical.
*/

package com.adventnet.nms.admin;
import com.adventnet.nms.util.*;
import com.adventnet.nms.eventdb.*;
import java.net.*;
public class StartupActionImplToRaiseEvent implements  StartUpAction
{
	public int handleStartUp(java.util.Map map)
	{
		/*CONTINUE = 101;
		  ABORT = 102;*/
		System.err.println("Map is::"+map);
		System.err.println("Map values is::"+map.values());
		if(map.get("primaryrole").equals("false"))
		{
			try{
				System.err.println("Intentionally waiting here in handleStartUp just to check whether STANDBY server waits only after we allow it to take over");
				Thread.sleep(450);
				System.err.println("Wait over! Now it can take over!");
				Thread eventThread = new Thread()
				{   
					public void run()
					{   
						try 
						{   
							System.err.println("Before EventAPI");
							EventAPI eAPI = null;
							while(eAPI == null){
								eAPI = (EventAPI)NmsUtil.getAPI("EventAPI");
								Thread.sleep(2000);
							}
							System.err.println("After EventAPI");
							Event e = new Event();
							e.setCategory("FailOver");
							e.setSeverity(1);
							String address = null;
							System.err.println("Before address");
							try{
								address=SocketUtil.getServerHost();
							}
							catch(Exception ex){ex.printStackTrace();}
							if(address==null)
							{
								address = InetAddress.getLocalHost().getHostAddress();
							}
							System.err.println("After address");
							e.setSource(address);
							e.setEntity(address);
							e.setTime(System.currentTimeMillis());
							eAPI.addEvent(e);
						}   
						catch (Exception exp){exp.printStackTrace();}   
					}   
				};  
				eventThread.start();
			}
			catch(Exception exp) {exp.printStackTrace();}
		}
		return 101;
	}
}
