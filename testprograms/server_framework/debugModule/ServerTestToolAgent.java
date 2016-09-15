/* $Id: ServerTestToolAgent.java,v 1.1.1.1 2001/01/10 15:20:13 nms Exp $ */

package com.adventnet.nms.server.testtool;

import java.util.Vector;
import java.util.Properties;
import java.rmi.RemoteException;
import java.rmi.Naming;

import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.util.PureServerUtilsBE;
import com.adventnet.nms.util.PureServerUtils;
import com.adventnet.nms.util.RunProcessInterface;
import com.adventnet.management.policydb.*;

public class ServerTestToolAgent  implements RunProcessInterface 
{
	private ServerTestToolAPIImpl api = null;
	public ServerTestToolAgent() 
	{
	}

	public boolean isInitialized()
	{
		System.out.println(" Process ServerTestTool Started ");
		return true;
	}
	public void shutDown()
	{
			try
			{
				String url = PureServerUtils.getRMIURL("NmsPolicyAPI");
				if (url != null)
				{
					Naming.unbind(url);
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
	}

	public void startProcess()
	{
		try
		{
			api = new ServerTestToolAPIImpl();
			NmsUtil.bindObjectInRegistry(api,"ServerTestToolAPI",true);
		}catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void callMain(String arg[])
	{
		System.out.println(" Process ServerTestTool Started ");
		try
		{
			startProcess();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}
	public static void main(String[] args)
	{
		ServerTestToolAgent mgr = new ServerTestToolAgent();
		mgr.callMain(args);
	}

}


