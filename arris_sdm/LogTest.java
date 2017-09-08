/*Configuration in NmsProcessesBE.conf

PROCESS test.LogTest
ARGS NULL

*/
package test;

import com.adventnet.nms.util.*;
import com.adventnet.management.log.Log;

public class LogTest implements RunProcessInterface
{
	public void callMain(String args[])
	{
			try{
				NmsLogMgr.KPIUSER.log("#### KPIUSER - SUMMARY ", Log.SUMMARY);
				NmsLogMgr.POLLUSER.log("#### POLLUSER SUMMARY ", Log.SUMMARY);
				NmsLogMgr.POLICYUSER.log("#### POLICYUSER SUMMARY ", Log.SUMMARY);
				NmsLogMgr.TOPOUSER.log("#### TOPOUSER SUMMARY ", Log.SUMMARY);
				NmsLogMgr.EVENTUSER.log("#### EVENTUSER SUMMARY ", Log.SUMMARY);
				NmsLogMgr.ALERTUSER.log("#### ALERTUSER SUMMARY ", Log.SUMMARY);
				NmsLogMgr.MAPUSER.log("#### MAPUSER SUMMARY ", Log.SUMMARY);
				NmsLogMgr.MISCUSER.log("#### MISCUSER SUMMARY ", Log.SUMMARY);
				NmsLogMgr.REPORTUSER.log("#### REPORTUSER SUMMARY ", Log.SUMMARY);
				NmsLogMgr.DISCOVERYUSER.log("#### DISCOVERYUSER SUMMARY ", Log.SUMMARY);
				NmsLogMgr.ALERT_AUDITUSER.log("#### AUDITUSER SUMMARY ", Log.SUMMARY);
				System.out.println("#### SYSTEM OUT SUMMARY ");
				System.err.println("#### SYSTEM ERR SUMMARY ");
				//----------//
                NmsLogMgr.KPIERR.log("#### KPIERR - INTERMEDIATE_DETAIL", Log.INTERMEDIATE_DETAIL);
                NmsLogMgr.POLLERR.log("#### POLLERR - INTERMEDIATE_DETAIL", Log.INTERMEDIATE_DETAIL);
                NmsLogMgr.POLICYERR.log("#### POLICYERR - INTERMEDIATE_DETAIL ", Log.INTERMEDIATE_DETAIL);
                NmsLogMgr.TOPOERR.log("#### TOPOERR - INTERMEDIATE_DETAIL ", Log.INTERMEDIATE_DETAIL);
                NmsLogMgr.EVENTERR.log("#### EVENTERR - INTERMEDIATE_DETAIL ", Log.INTERMEDIATE_DETAIL);
                NmsLogMgr.MAPERR.log("#### MAPERR - INTERMEDIATE_DETAIL ", Log.INTERMEDIATE_DETAIL);
                NmsLogMgr.REPORTERR.log("#### REPORTERR - INTERMEDIATE_DETAIL ", Log.INTERMEDIATE_DETAIL);
                NmsLogMgr.ALERTERR.log("#### ALERTERR INTERMEDIATE_DETAIL ", Log.INTERMEDIATE_DETAIL);
                NmsLogMgr.MISCERR.log("#### MISCERR INTERMEDIATE_DETAIL ", Log.INTERMEDIATE_DETAIL);
                NmsLogMgr.REPORTERR.log("#### REPORTERR INTERMEDIATE_DETAIL ", Log.INTERMEDIATE_DETAIL);
			}
			catch(Exception e)
			{
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
