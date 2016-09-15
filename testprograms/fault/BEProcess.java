package test;

import com.adventnet.nms.eventdb.*;
import com.adventnet.nms.alertdb.*;
import com.adventnet.nms.util.*;

public class BEProcess implements RunProcessInterface
{
    AlertAPI alertAPI = null;
    EventAPI eventAPI = null;
    TrapAPI trapAPI = null;
    EventParserAPI eparser = null;

    public void callMain(String[] a)
    {
        while(!NmsUtil.webNMSModulesStarted)
        {
            try
            {
                Thread.sleep(60000);
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }
        }

        while(eventAPI == null || trapAPI == null || alertAPI == null)
        {
            eventAPI = (EventAPI)NmsUtil.getAPI("EventAPI");
            trapAPI = (TrapAPI)NmsUtil.getAPI("TrapAPI");
            alertAPI = (AlertAPI)NmsUtil.getAPI("AlertAPI");
            try
            {
                Thread.sleep(200);
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }
        }
        /*
        try
        {
            eventAPI = null;
            eventAPI = com.adventnet.nms.eventdb.EventMgr.eventapi;
            System.out.println("Event API from static variable com.adventnet.nms.eventdb.EventMgr.eventapi -->"+eventAPI);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        try
        {
            trapAPI = null;
            trapAPI = com.adventnet.nms.eventdb.EventMgr.trapAPI;
            System.out.println("Trap API from static variable com.adventnet.nms.eventdb.EventMgr.trapAPI -->"+trapAPI);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        try
		{
			alertAPI = null;
            alertAPI = com.adventnet.nms.alertdb.FaultMgr.alertapi;
            System.out.println("Alert API from static variable com.adventnet.nms.alertdb.FaultMgr.alertapi -->"+alertAPI);
		}
		catch(Exception e)
		{
			e.printStackTrace();
        }
        */
        try
        {
            eparser=com.adventnet.nms.eventdb.EventMgr.eventParserAPI ;
            System.out.println("EventParser API from static variable com.adventnet.nms.eventdb.EventMgr.eventParserAPI -->"+eparser);
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
