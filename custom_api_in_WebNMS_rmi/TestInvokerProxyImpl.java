package com.adventnet.nms.test;

import java.io.*;
import java.rmi.RemoteException;
import java.util.*;

import com.adventnet.management.log.Log;
import com.adventnet.nms.fe.common.*;
import com.adventnet.nms.startnms.*;
import com.adventnet.nms.util.*;

public class TestInvokerProxyImpl implements TestInvoker,BEFailOverListener,CommonModuleAPI {
    private TestInvoker test = null;    

	public TestInvokerProxyImpl () {
		// TODO Auto-generated constructor stub
		if(!NmsMainFE.singleJVM) {
			try {
				test = (TestInvoker) PureServerUtilsFE.lookupBEAPI("TestInvoker"); //No i18N
			} catch (Exception e) {
				// TODO Auto-generated catch block
				NmsLogMgr.MISCERR.fail("Error occurred while obtaining TestInvoker from BE", e); //No i18N
			}
			PureServerUtilsFE.clientSocketFE.registerBEFailOverListener(this);
		}
	}
	
	public void postBEFailOverNotification(BEFailOverEvent event) {
		// TODO Auto-generated method stub
		String beHost = event.getNewBEHost();
		try {
			NmsLogMgr.MISCUSER.log("Trying to reconnect to new BE "+beHost+" for TestInvoker", Log.DEBUG);
			test = (TestInvoker) PureServerUtilsFE.lookupBEAPI("TestInvoker"); //No i18N
			NmsLogMgr.MISCUSER.log("Successfully got TestInvoker from new BE : "+beHost, Log.DEBUG);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			NmsLogMgr.MISCERR.fail("Error occurred while obtaining TestInvoker from new BE : "+beHost+" after failover.", e); //No i18N
		}
	}

	public void preBEFailOverNotification(BEFailOverEvent event) {
		// TODO Auto-generated method stub
		NmsLogMgr.MISCUSER.log("Pre Failover called in Test Proxy API ", Log.DEBUG);
	}

	public void testInvoke() throws RemoteException
	{
		try
		{       
			System.out.println("Test Invoked ");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
