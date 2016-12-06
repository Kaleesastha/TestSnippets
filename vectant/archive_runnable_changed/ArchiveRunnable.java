//$Id: ArchiveRunnable.java,v 1.5.6.6 2010/03/20 06:30:49 bselva Exp $

/* Copyright (c) 1996 - 2002 Adventnet, Inc. All Rights Reserved.
 * Please read the associated COPYRIGHTS file for more details.
 * ADVENTNET, INC. MAKES NO REPRESENTATIONS OR WARRANTIES  ABOUT THE
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE, OR NON-INFRINGEMENT.  ADVENTNET, INC. SHALL
 * NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF
 * USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE  OR ITS DERIVATIVES.
 */

/**
 * ArchiveRunnable.java
 * Copyright 2001. AdventNet, Inc. All Rights Reserved.
 * Author : Alexander Lobo
 */

package com.adventnet.nms.server.dataarchiver;

import java.util.Properties;
import java.util.Date;
import java.util.Calendar;

import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.util.NmsLogMgr;
import com.adventnet.management.log.Log;

public class ArchiveRunnable implements Runnable
{
	private Properties[] archiveProps=null;
	private long archiveInterval=-1;
	private String name="defaultArcRun";//No I18N
	private String className=null;//No I18N
	private DataArchiveIfc archiveClass=null;
	private String value=null;//No I18N

	private boolean initialReport = true;

	public void initialize(Properties[] arcProps)
	{
		try
		{
			archiveProps=arcProps;

			long scheduleTime = -1;
			long initialDelayTime = -1;
			//all the props contains common props. so just we get first props
			Properties commonProps=archiveProps[0];
			if (( value=commonProps.getProperty("id")) !=  null)//No I18N
				name=value;

			className=commonProps.getProperty("className").trim();//No I18N
			try
			{
				archiveInterval=Long.parseLong(commonProps.getProperty("executeInterval").trim());//No I18N
			}
			catch(Exception ex)
			{
				NmsLogMgr.MISCERR.fail(NmsUtil.GetString("Invalid executeInterval.Setting default value 24 hrs"), null);//No I18N
				archiveInterval=24;
			}

			String unit=commonProps.getProperty("executeIntervalUnit");//No I18N

			if(unit.equals("HOURS"))//No I18N
			{
				archiveInterval=archiveInterval*1000*60*60;
			}
			else if(unit.equals("DAYS"))//No I18N
			{
				archiveInterval=archiveInterval*1000*60*60*24;
			}
			else if(unit.equals("MINUTES"))//No I18N
			{
				archiveInterval=archiveInterval*1000*60;
			}
			else
			{
				NmsLogMgr.MISCERR.fail(NmsUtil.GetString("Invalid Unit for executingInterval. Allowed values are HOURS, DAYS. Setting default to HOURS"), null);//No I18N
				archiveInterval=archiveInterval*1000*60*60;
			}
			if(unit.equals("HOURS"))//No I18N
			{
				Date thisHour = new Date();
				thisHour.setMinutes(59);
				thisHour.setSeconds(59);
				scheduleTime = thisHour.getTime();
				DataMgmtHandler.scheduleTask(this, scheduleTime);
				initialReport = false;
				NmsLogMgr.MISCUSER.log("Archiving scheduled at "+thisHour+" for "+unit,Log.DEBUG);//No I18N
			}
			else if(unit.equals("DAYS"))//No I18N
			{
				/*Date today = new Date();
				today.setHours(23);
				today.setMinutes(59);
				today.setSeconds(59);
				DataMgmtHandler.scheduleTask(this, today.getTime());
				NmsLogMgr.MISCUSER.log("Archiving scheduled at "+today+" for "+unit,Log.DEBUG);//No I18N
				*/
				Calendar cal=Calendar.getInstance();
				cal.set(Calendar.HOUR_OF_DAY,00);
				cal.set(Calendar.MINUTE,00);
				cal.set(Calendar.SECOND,00);
				//to schedule the daily report at 12.05
				scheduleTime = cal.getTimeInMillis()+archiveInterval;
				initialReport = false;
				Date nextDay=new Date(scheduleTime);
				DataMgmtHandler.scheduleTask(this,scheduleTime);
				NmsLogMgr.MISCUSER.log("Archiving scheduled at "+nextDay+" for "+unit,Log.DEBUG);//No I18N
			}
			else
			{
				scheduleTime = System.currentTimeMillis()+archiveInterval;
				DataMgmtHandler.scheduleTask(this, scheduleTime);
				NmsLogMgr.MISCUSER.log("Archiving scheduled at "+new Date(System.currentTimeMillis()+archiveInterval)+" for "+unit,Log.DEBUG);//No I18N
			}
			initialDelayTime = System.currentTimeMillis() + (DataMgmtRPI.INITIAL_REPORT_DELAY*1000);
			boolean runOnInit = Boolean.parseBoolean(commonProps.getProperty("runOnInitialization"));
			if((initialDelayTime < scheduleTime) && runOnInit)
			{
				DataMgmtHandler.scheduleTask(this,initialDelayTime);
				NmsLogMgr.MISCUSER.log("Archiving scheduled at "+new Date(initialDelayTime),Log.DEBUG);//No I18N
				initialReport = true;

			}
			
		}
		catch(Exception e)
		{

			NmsLogMgr.MISCERR.fail(NmsUtil.GetString("Error occured during the initialization of archive. Properties ") + archiveProps, e);//No I18N
		}
	}

	public void run()
	{
		long scheduledTime=System.currentTimeMillis();
		try
		{
			if(archiveClass==null)
			{
				archiveClass=(DataArchiveIfc)Class.forName(className).newInstance();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		boolean result=false;
		try
		{
			result=archiveClass.doArchive(archiveProps);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			result=false;
		}
		if(!result)
		{
			NmsLogMgr.MISCERR.fail(NmsUtil.GetString("Problem occured in Archive."), null );//No I18N
		}
		
		if(initialReport)
		{
			initialReport = false;
		}
		else
		{
			DataMgmtHandler.scheduleTask(this,scheduledTime+archiveInterval);
			NmsLogMgr.MISCUSER.log("Archiving scheduled at "+new Date(scheduledTime+archiveInterval),Log.DEBUG);//No I18N
		}
	}
}


