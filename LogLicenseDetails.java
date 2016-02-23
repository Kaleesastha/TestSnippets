package com.adventnet.nms.topodb;
import com.adventnet.nms.topodb.AnalyzeUser;
import com.adventnet.nms.topodb.DBServer;
import com.adventnet.nms.util.RunProcessInterface;

public class LogLicenseDetails implements RunProcessInterface
{
	public void callMain(String args[])
	{
		try{
			Thread.sleep(20000);
			AnalyzeUser au = new AnalyzeUser(DBServer.topodb.comapi);
			au.checkLicense();
			while(DBServer.comapi!=null && DBServer.topodb!=null)
			{
				System.err.println("***********************************");
				if (au.isLicenseInitialised())
				{
					if (au.isDeviceBased())
					{
						System.err.println("no. of MOs"+ au.refreshDeviceCounter());
						System.err.println("maxLicenseLimit::"+au.getMaxDeviceCount());
					}
					else
					{
						System.err.println("no. of MOs"+DBServer.topodb.getCurrentMOCount());
						System.err.println("Maximum no. of MOs"+au.getMaxMOCount());
						System.err.println("maxLicenseLimit::"+DBServer.topodb.getMaxLicenseLimit());
						System.err.println("getCurrentMOCount::"+DBServer.topodb.getCurrentDeviceCount());
						System.err.println("getCompleteList"+DBServer.topodb.getCompleteList().size());
					}
				}
				System.err.println("***********************************");
			}
				/*System.err.println("***********************************");
				System.err.println("getLicenseType ::"+DBServer.topodb.getLicenseType());
				System.err.println("getCurrentMOCount"+DBServer.topodb.getCurrentMOCount());
				System.err.println("getMaxLicenseLimit ::"+DBServer.topodb.getMaxLicenseLimit());
				System.err.println("***********************************");*/
		}
		catch(Exception e)
		{
			System.out.println("this test is Fail");
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
