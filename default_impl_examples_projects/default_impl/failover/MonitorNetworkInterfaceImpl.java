/* $Id: MonitorNetworkInterfaceImpl.java,v 1.1.6.1 2013/05/13 13:04:02 venkatramanan Exp $ */
package test;


import java.net.InetAddress;
import java.net.NetworkInterface;

import com.adventnet.management.log.Log;
import com.adventnet.nms.util.NmsLogMgr;
import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.admin.MonitorNetworkInterface;

//This class will determine whether the this box is in network. Will check for the NetworkInterface availability using the networkInterface class provided by Java

public class MonitorNetworkInterfaceImpl implements MonitorNetworkInterface
{

	private static final String SERVER_IP = NmsUtil.getServerIPAndHostName().getProperty("SERVER_IP"); //No I18N

	public boolean isInNetwork()throws Exception
	{
		InetAddress addr = InetAddress.getByName(SERVER_IP);
		NetworkInterface netIF = NetworkInterface.getByInetAddress(addr);
		if(netIF==null)
		{
			return false;
		}
		boolean isUP = netIF.isUp();
		return isUP;
	}
}
