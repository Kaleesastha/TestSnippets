//$Id: WebNMSAgentApp.java,v 1.2.6.1 2012/01/25 05:12:45 karen.r Exp $

/* WebNMSAgentApp.java
 * Copyright (c) 1998 Adventnet, Inc. All Rights Reserved.
 * Please read the associated COPYRIGHTS file for more details.
 *
 * ADVENTNET, INC. MAKES NO REPRESENTATIONS OR WARRANTIES  ABOUT THE
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE, OR NON-INFRINGEMENT.  ADVENTNET, INC. SHALL
 * NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF
 * USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE  OR ITS DERIVATIVES.
 */

/**
 * this application starts the SnmpAgent
 * for the specified options.
 */

package com.adventnet.nms.jmxagent;


// For SNMP communication
import java.util.Calendar;
import java.util.Date;

import com.adventnet.management.log.Log;
import com.adventnet.nms.util.NmsLogMgr;
import com.adventnet.nms.util.NmsUtil;
import com.adventnet.security.authorization.Coding;
/*
 * This is the main class from which the NMS JMX-Agent is getting started.
 */
public class WebNMSAgentApp implements com.adventnet.nms.util.RunProcessInterface
{

    private static AdventNet_WebNMS_MIB_JMX agent = null;
    public static long startTimeMillis;
    public static byte[] startTime = new byte[8];
    static boolean initialized = false;

    public WebNMSAgentApp()
    {
    }

    public WebNMSAgentApp(String args[])
    {
	agent =  new AdventNet_WebNMS_MIB_JMX(args);
    }
    //method added by rameshj
    private void decryptCommunity(String args[])
    {
        try{
            int argc = args.length;
            for (int argn = 0; argn < argc ; ++argn )
            {
                if ( args[argn].equals( "-readCommunity" ) && argn + 1 < argc && args[argn+1].startsWith("0x"))//No Internationalisation
                {
                    args[argn+1]=Coding.convertFromBase(args[argn+1].substring(2));
                }
                else if( args[argn].equals( "-writeCommunity" ) && argn + 1 < argc && args[argn+1].startsWith("0x"))//No Internationalisation
                {
                    args[argn+1]=Coding.convertFromBase(args[argn+1].substring(2));
                }
            }
        }catch(Exception e)
        {
            System.err.println(NmsUtil.GetString("server.jmxagent.decrypt"));
            e.printStackTrace();
        }

    }

    /*
     * This method gets called by the NMS to start start the JMX-Agent
     */
    public void callMain(String args[])
    {
	//started rameshj
        if(NmsUtil.isStoreEncryptedFormat())
        {
            decryptCommunity(args);
        }
	//Ended rameshj

	agent =  new AdventNet_WebNMS_MIB_JMX(args);
	//WebNMSAgentApp app = new WebNMSAgentApp(args);
	System.out.println(" WebNMSAgentApp: NMS Agent Started");//No I18N
	NmsLogMgr.MISCUSER.log(NmsUtil.GetString("WebNmsAgentApp  module Initialized"),Log.SUMMARY); //No I18N
	//initialized = true;
	startTimeMillis = System.currentTimeMillis();
	initStartTime();
    }

    /*
     * To know whether the JMX-Agent got initialized or not
     */
    public boolean isInitialized()
    {
	return initialized;
    }

    /*
     * To stop the JMX-Agent
     */
    public void shutDown()
    {
	if(agent != null)
	    agent.stopAgent();
    }

    public void init()
    {
    }

    /*
     * Getting the time at which the NMS JMX-Agent started
     */
    void initStartTime()
    {
	Date date = new Date(startTimeMillis);
	Calendar cal = Calendar.getInstance();
	cal.setTime(date);

	int year = cal.get(cal.YEAR);
	startTime[0] = (byte)(year >> 8);
	startTime[1] = (byte)year;
	startTime[2] = (byte)(cal.get(cal.MONTH) + 1);
	startTime[3] = (byte)cal.get(cal.DAY_OF_MONTH);
	startTime[4] = (byte)cal.get(cal.HOUR);
	startTime[5] = (byte)cal.get(cal.MINUTE);
	startTime[6] = (byte)cal.get(cal.SECOND);
	startTime[7] = (byte)(cal.get(cal.MILLISECOND) % 10);
	//startTime[8] = (byte)'+';
        //startTime[9] = (byte)5;
	//startTime[10] = (byte)30;
    }

    /*
     * To return the JMX-Agent SNMP port
     */
    public static int getSnmpPort()
    {
	return agent.snmpPort;
    }

    /*
     * To return the JMX-Agent RMI port
     */
    public static int getRmiPort()
    {
	return agent.rmiPort;
    }

    /*
     * To return the JMX-Agent CORBA port
     */
    public static int getCorbaPort()
    {
	return agent.corbaPort;
    }

    /*
     * To return the JMX-Agent HTTP port
     */
    public static int getHttpPort()
    {
	return agent.httpPort;
    }

    /*
     * To return the JMX-Agent HTML port
     */
    public static int getHtmlPort()
    {
	return agent.htmlPort;
    }
}