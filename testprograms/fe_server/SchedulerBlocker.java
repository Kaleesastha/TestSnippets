package test;

import com.adventnet.nms.util.*;

public class SchedulerBlocker implements RunProcessInterface, Runnable
{
    public void callMain(String[] args)
    {
        NmsUtil.scheduler.scheduleTask(this, System.currentTimeMillis() + 60000);
        NmsUtil.scheduler.scheduleTask(this, System.currentTimeMillis() + 60000);
        NmsUtil.scheduler.scheduleTask(this, System.currentTimeMillis() + 60000);
        NmsUtil.scheduler.scheduleTask(this, System.currentTimeMillis() + 60000);
        NmsUtil.scheduler.scheduleTask(this, System.currentTimeMillis() + 60000);
    }

    public void shutDown()
    {
    }

    public boolean isInitialized()
    {
        return true;
    }

    public void run()
    {
        while (true)
        {
            try
            {
                Thread.sleep(10000000);
            }
            catch (Exception e){}
        } 
