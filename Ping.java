/* 
   $Id: Ping.java,v 1.10.6.10.4.9 2009/07/06 13:42:18 sankaralingam Exp $
 */
/**

 * Ping.java

 */

package com.adventnet.nms.util;



import java.lang.*;

import java.io.*;
import java.net.*;

import java.util.*;

import com.adventnet.util.NativePing;
import com.adventnet.nms.util.IPV6Util;
import com.adventnet.nms.util.InetAddressWrapper;
import com.adventnet.management.log.LogUser;
import com.adventnet.management.log.LogMgr;
import com.adventnet.management.log.Log;

/**
 * This Utility class is used to ping a machine to check if it 
 * is alive.This class is used by the NMS Discovery engine if ICMP
 * discovery is enabled.It also contains other utility methods to 
 * determine the interfaces of local host and the ipAddress contained
 * in the ARP and Routing table of the local host.
 */


public class Ping {



    private static String osname = System.getProperty("os.name");//No Internationalisation
    private static String localip = SocketUtil.getServerHost();
    static String PING_CMD="ping -w 0 -n 1"; // the ping command//No Internationalisation

    static String ARP_CMD="arp -a"; // the arp command//No Internationalisation

    static String NETSTAT_CMD="netstat -rn"; // the arp command//No Internationalisation

    static int num_try = 1;//No Internationalisation

    /**
     * The NativePing instance to be used if NativePing is enabled 
     * in NMS.The instance will be null if it is not enabled.
     */

    public static NativePing nPing;

    static LogUser PingErr=LogMgr.getLogUser("PINGERR");//No I18N

     /** 
     * This method is used to do a ping to the machine to check its
     * aliveness.The host to be pinged is passed as an argument
     * to the method and the retries takes the default value 0.
     * @param host ipAddress of the machine which is to be pinged
     * @return true if the ping was successful,false otherwise
     */
    public static boolean ping (String host) 
    {
        return ping (host, 0);
    }
   
    private static boolean execPing(String host)
    {
        String cmd;
        if( osname.startsWith("SunOS") || osname.startsWith("Solaris")) {//No Internationalisation
            if (localip == null)
            {
                PING_CMD="/usr/sbin/ping ";//No Internationalisation
            }
            else
            {
                PING_CMD="/usr/sbin/ping -i" + " " + localip;//No Internationalisation
            }
            cmd = new String(PING_CMD + " " + host + " " + num_try);//No Internationalisation
        }
        else if( osname.startsWith("Linux") ) {//No Internationalisationi
            if (localip == null)
            {
                PING_CMD="/bin/ping -c 1 -w" + " " + num_try;//No Internationalisation
            }
            else
            {
                PING_CMD="/bin/ping -c 1 -w" + " " + num_try + " " + "-I" + " " + localip;//No Internationalisation
            }
            cmd = new String(PING_CMD + " " + host);
        }
        else if(osname.startsWith("FreeBSD") ) 
            {
                PING_CMD="/sbin/ping -c 1";//No Internationalisation
                cmd = new String(PING_CMD + " " + host);//No Internationalisation
            }
        else if(osname.startsWith("Windows") ) 
            {
                PING_CMD="ping -n 1 -w";//No Internationalisation
                cmd = new String(PING_CMD + " " + num_try*1000 + " " + host);

            }
        else if(!osname.startsWith("Windows") ) {
            PING_CMD="ping -n 1";//No Internationalisation
            cmd = new String(PING_CMD + " " + host);
        }
        else {
            cmd = new String(PING_CMD + " " + host);
        }

        return execCmd(cmd);  
    }      

    // New method to execute the ping cmd  
    private static boolean execCmd(String cmd)
    {

        RunCmd runc = new RunCmd(cmd);

		long sysPingTimeout=0;//Need to check whether this is right way of initializing...Testing with respect to functionality is essential... 
		String temp = null;

		try{

			temp = NmsUtil.getParameter("SYSPING_TIMEOUT"); //No I18N      
			if(temp == null)
			{            
				sysPingTimeout = 5000;
			}
			else
			{
				sysPingTimeout = Integer.parseInt(temp)*1000;
			}

		}
		catch(Exception e){          

			e.printStackTrace();

		}


		runc.start();
		long startTime = System.currentTimeMillis();
		long currTime;
		do try {
			currTime = System.currentTimeMillis();
			if ((currTime - startTime) >= sysPingTimeout)                      
			{
				runc.stopCommand();
				if(PingErr!=null)
				{
					PingErr.log("[THREAD:: "+Thread.currentThread().getName()+"] "+NmsUtil.GetString("Timed out when executing the following cmd by RunCmd::")+ cmd,Log.VERBOSE); //No I18N 
				}   
				return false;
			} 
            Thread.sleep(25); } catch (Exception e) {}
        //while (runc.isAlive());
        while (!runc.finished);
        String reply;
        if( osname.startsWith("Windows") ) 
            {
                if ( (runc.result == false) || (runc.exitValue != 0) )
                    return false;
                else if ((reply=runc.stdout.toString()).indexOf("Reply from") != -1)  
                    if (reply.indexOf("bytes=") != -1) return true;
                return false;
            }  
        else if( osname.startsWith("Linux") || (osname.startsWith("FreeBSD")) ) 
            {
                if ( (runc.result == false) || (runc.exitValue != 0) )
                    return false;
                else if ( (reply=runc.stdout.toString()).indexOf("64 bytes from") != -1)
                    return true;
                return false;
            }
        else 
            {
                if ( (runc.result == false) || (runc.exitValue != 0) ) {
                    return false;
                }
                else 
                    {
                        return true;
                    }
            }
    }
    
     /** 
     * This method is used to ping to the machine with the given properties. 
     * @param Properties values to be sent to the host to exec Ping.
     * The following values should be set to the Properties object.<br>
     *
     * Example </br>
     * <br>Properties p = new Properties();</br>
     * <br>p.setProperty("hostName", "test");</br>
     * <br>p.setProperty("pingTimeout", "<time in seconds>");</br>
     * <br>p.setProperty("pingRetries", "<retries>");</br>
     * should be passed as properties to this method
     * to the method and the retries takes the default value 0.
     * @param host ipAddress of the machine which is to be pinged
     * @return true if the ping was successful,false otherwise
     */


    public static boolean ping(Properties p)
    {
            
        String hostname = p.getProperty("hostName");
         if (hostname == null)
             {
                return false;
             }   

        int pingRetries =0;
        int pingTimeout = 5;
        String temp;
        temp = p.getProperty("pingTimeout"); 
        try 
            {
                if(temp != null) 
                    {
                        pingTimeout = Integer.parseInt(temp);
                        if(pingTimeout <= 0)
                            {
                                if((osname.startsWith("Linux")) || (osname.startsWith("Solaris")))
                                    {
                                        pingTimeout = 5;
                                      
                                    }
                            }    
                        
                    }
            }
        catch(NumberFormatException nfe) 
            {
                pingTimeout = 5;
            }
        temp = p.getProperty("pingRetries"); 
        try 
            {
                if(temp != null) 
                    {
                        pingRetries = Integer.parseInt(temp);
                    }
            }
        catch(NumberFormatException nfe) 
            {
                pingRetries = 0;
            }

		//Impl added for IPV6 - chinnaraj
		//TODO: need to take care of ping timeout for IPV6 nodes.
		if(hostname.indexOf(":") != -1)
		{
			for (int i = 0; i < pingRetries + 1; i++)
			{
				if (execPing6(hostname))
				{
					return true;
				}
			}
            if(PingErr!=null)
            {
                PingErr.log("[THREAD:: "+Thread.currentThread().getName()+"] "+NmsUtil.GetString("IPV6 Ping failed for device ")+ hostname+NmsUtil.GetString(".Ping Properties ")+p,Log.VERBOSE); //No I18N
            }
			return false;
		}

        if (NmsUtil.nativePing) 
            {
                if (nPing == null) 
                    {
                        nPing = new NativePing();
                    }

                try
                    {
                        /* 
                         * Here + 1 is given because NativePing treates the second parameter
                         * as a count of the pings and not as retries parameter.
                         */

                        int result = nPing.pingHost (p);
                                
                        if (result == 0) 
                            {
                                return true;
                            }
                        else 
                            {
                                if(PingErr!=null)
                                {
                                    PingErr.log("[THREAD:: "+Thread.currentThread().getName()+"] "+NmsUtil.GetString("Native Ping failed for device ")+hostname+NmsUtil.GetString(".Ping Properties ")+p,Log.VERBOSE); //No I18N
                                }
                                return false;
                            }
                    }
                catch (AccessDeniedException ade)
                    {
                        NmsUtil.nativePing = false;
                        nPing = null;
                        NmsLogMgr.MISCERR.fail (NmsUtil.GetString("User does not have permission to open RAW socket and hence Native Ping cannot be used. Defaulting to System Ping."), null);
                    }
            }

   
        String pingCmd;
        if( osname.startsWith("SunOS") || osname.startsWith("Solaris")) 
            {
                PING_CMD="/usr/sbin/ping ";//No Internationalisation
                pingCmd = new String(PING_CMD + " " + hostname + " " + pingTimeout);
            }
        else if( osname.startsWith("Linux") ) 
            {
                PING_CMD="/bin/ping -c 1 -w";//No Internationalisation
                pingCmd = new String(PING_CMD + " " + pingTimeout + " " + hostname);

            }
        else if(osname.startsWith("FreeBSD") ) 
            {
                
                PING_CMD="/sbin/ping -c 1";//No Internationalisation
                pingCmd = new String(PING_CMD + " " + hostname);
            }
        else if(osname.startsWith("Windows") ) 
            {
                PING_CMD="ping -n 1 -w";//No Internationalisation
                pingCmd = new String(PING_CMD + " " + pingTimeout*1000 + " " + hostname);

            }
        else if(!osname.startsWith("Windows") ) 
            {
                PING_CMD="ping -n 1";//No Internationalisation
                pingCmd = new String(PING_CMD + " " + hostname);
            }

        else 
            {
                pingCmd = new String(PING_CMD + " " + hostname);
            }

        for (int i = 0; i < pingRetries + 1; i++)
            {
                boolean exec = execCmd(pingCmd);
                if(exec)
                    {
                        return true;
                    }
               
            }
        if(PingErr!=null)
        {
            PingErr.log("[THREAD:: "+Thread.currentThread().getName()+"] "+NmsUtil.GetString("ICMP Ping failed for device ")+ hostname+NmsUtil.GetString(".Ping Properties ")+p,Log.VERBOSE); //No I18N
        }
        return false;
    }


    /** 
     * This method is used to do a  ping to the machine to check its
     * aliveness.
     * @param host ipAddress of the machine which is to be pinged
     * @param retries no of retries when doing a ping to the host 
     * @return true if the ping was successful,false otherwise
     */
  
    public static boolean ping (String host, int retries) 
    {
	 	//Added by chinnaraj.Check whether the Address is IPV4 or IPV6 and call the corresponding execPing method.
		 String type = "V4";  //No Internationalisation
		 if(host.indexOf(":") != -1)
		 	type = "V6";   //No Internationalisation
		 
        //Check if native ping is to be used
        if (NmsUtil.nativePing) 
            {
		 		if(type.equals("V6"))
		 		{
					for (int i = 0; i < retries + 1; i++)
	 				{
					 	if (execPing6(host))
					 	{
							 return true;
			 			}
					}
                    if(PingErr!=null)
                    {
                        PingErr.log("[THREAD:: "+Thread.currentThread().getName()+"] "+NmsUtil.GetString("IPV6 Ping failed for device ")+ host,Log.VERBOSE); //No I18N
                    }
					return false;
		 		}
                if (nPing == null) 
                    {
                        nPing = new NativePing();
                    }

                try
                    {
                        /* 
                         * Here + 1 is given because NativePing treates the second parameter
                         * as a count of the pings and not as retries parameter.
                         */
                        int result = nPing.pingHost (host, retries + 1);
           
                        if (result == 0) 
                            {
                                return true;
                            }
                        else 
                            {
                                if(PingErr!=null)
                                {
                                    PingErr.log("[THREAD:: "+Thread.currentThread().getName()+"] "+NmsUtil.GetString("Native Ping failed for device ")+ host,Log.VERBOSE); //No I18N
                                }
                                return false;
                            }
                    }
                catch (AccessDeniedException ade)
                    {
                        NmsUtil.nativePing = false;
                        nPing = null;
                        NmsLogMgr.MISCERR.fail (NmsUtil.GetString("User does not have permission to open RAW socket and hence Native Ping cannot be used. Defaulting to System Ping."), null);
                    }
            }

        for (int i = 0; i < retries + 1; i++)
            {
		 		if(type.equals("V6"))
		 		{
			 		if (execPing6(host))
			 		{
				 		return true;
			 		}
		 		}
				else
				{
                	boolean exec = execPing (host);
	                if (exec)
    	                {
        	                return true;
            	        }
				}
            }
        if(PingErr!=null)
        {
            PingErr.log("[THREAD:: "+Thread.currentThread().getName()+"] "+NmsUtil.GetString("ICMP Ping failed for device ")+ host,Log.VERBOSE); //No I18N
        }
        return false;
    }

     /** 
     * This method is used to set the Ping timeout value. 
     * @param timeout time in seconds for the server to wait to get the response from host.
     * default value will be "1".
     */

  
    public static void setPingTimeout(int timeout)
    {
         if(timeout <= 0)
            {
                if((osname.startsWith("Linux")) || (osname.startsWith("Solaris")))
                {
                    num_try = 1;
                }    
            }
           else
                {
 
                    num_try=timeout;
                } 

    }

    
     /** 
     * This method is used to get the Ping timeout value 
     */


    public static int getPingTimeout()
    {
        return num_try;
    }


    /** 
     * This method is used to get the interfaces of the localhost.
     * @return Hashtable containing the interfaces of the localhost
     * and also the corresponding netmask 
     */
	
   	
    public static Hashtable getInterfaces() {

        String osName = null;

        if( osname.startsWith("SunOS") || osname.startsWith("Solaris")) {

            NETSTAT_CMD="/usr/bin/netstat -rn";

        } else if( osname.startsWith("Linux") ) {

            NETSTAT_CMD="/bin/netstat -rn";

        }else if(osname.startsWith("FreeBSD") ) {
            //System.out.println("In Ping for OS "+osname);
            //System.out.println("netstat command does not work for FreeBSD Systems pick it up from seed file");
            //h is {192.168.1.21=192.168.1.21, NetMask:192.168.1.21=255.255.255.0}          
            Hashtable h=getFromSeedFile();
            //System.out.println("@#@#@#@#@#@#@#@# h is "+h);   
            return h;
        

        }else if( !osname.startsWith("Windows") ) {

            //		 NETSTAT_CMD="netstat -rn";
            NETSTAT_CMD="ipconfig";

        }  
      
        else if( osname.startsWith("Windows") ) {
            //Hashtable h=getFromSeedFile();
            //return h;   
            //NETSTAT_CMD="netstat -rn";

        }

        String cmd = new String(NETSTAT_CMD);

        RunCmd runc = new RunCmd(cmd);

        runc.start();

        do try { Thread.sleep(1000); } catch (Exception e) {}

        //while (runc.isAlive());

        while (!runc.finished);



        if( osname.startsWith("Windows") ) 
            {
                StringTokenizer st = new StringTokenizer(runc.stdout.toString());

                Hashtable h = new Hashtable();
              
                try 
                    {
                        File file = new File(PureUtils.rootDir +"/ipconfig.txt");
                        BufferedReader stream = new BufferedReader(new InputStreamReader(CommonUtil.openFile(file)));
                        String temp;
                        String ipAddr = null;
                        String netmask = null;
                        while (( temp = stream.readLine()) != null) 
                            {
                                if (temp.indexOf(":") == -1)
                                    {
                                        continue;
                                    }
                                st = new StringTokenizer(temp, ":");
                                while (st.hasMoreTokens())
                                    {
                                        String firstToken = st.nextToken();

					//for supporting different locales
                                        if ( ( (osname.startsWith("Windows Vista")) && (firstToken.indexOf(NmsUtil.GetString("localnet.discovery.windowsvista.IPV4Address")) != -1)) || firstToken.indexOf(NmsUtil.GetString("localnet.discovery.IP Address")) != -1)
                                            {
                                                if (st.hasMoreTokens())
                                                    {
                                                        ipAddr = st.nextToken().trim();
                                                        h.put(ipAddr, ipAddr);
                                                    }
                                            }
                                        else if (firstToken.indexOf(NmsUtil.GetString("localnet.discovery.Subnet Mask")) != -1)
                                            {
                                                if (st.hasMoreTokens())
                                                    {
                                                        netmask = st.nextToken().trim();
                                                        h.put("NetMask:"+ipAddr, netmask);
                                                    }
                                            }
                                    }
                            }
                    } 
                catch (Exception e) 
                    {
                        NmsLogMgr.MISCERR.fail(NmsUtil.GetString("Cannot read netstat file in WinNT ") + e, e);
                        return null;
                    }

                /*              int i=6;
                                String netmask = "255.255.255.0";
                                while (st.hasMoreTokens()) {
                                String s = st.nextToken();
                                if (s.equals("Metric")) i = 1;
                                else if (s.equals("Active")) i=6;
                                if (i==3)netmask = s;
                                if (i==5) { 
                                if (h==null) h = new Hashtable();
                                if ( (!s.equals("127.0.0.1")) && (!s.equals("0.0.0.0")) ) {
                                h.put(s,s); 
                                if ( (!netmask.equals("255.255.255.255")) && (!netmask.equals("0.0.0.0")) && (!netmask.equals("224.0.0.0"))) 
                                h.put("NetMask:"+s,netmask);
                                }
                                i = 0; 
                                }
                                i++;
                                }*/

                return h;



            }  else if( osname.startsWith("SunOS") || osname.startsWith("Solaris")) {

                try {

                    BufferedReader st = new BufferedReader(

                                                           new StringReader(runc.stdout.toString() + "\nEND\n"));



                    for (int i=0;i<4;i++) st.readLine();

                    Hashtable h = new Hashtable();	

                    while (true) {

                        if (!st.ready()) break;

                        String str = st.readLine();

                        StringTokenizer strTok = new StringTokenizer(str);

                        int numTokens = strTok.countTokens();

                        if (numTokens < 5) break;

                        String dest = null;

                        dest = strTok.nextToken();

                        for (int i=0;i<numTokens-2;i++) strTok.nextToken();

                        h.put(strTok.nextToken(), dest);

                    }

                    Hashtable newh = new Hashtable();

                    for (Enumeration e = h.keys();e.hasMoreElements();) {

                        String key = (String) e.nextElement(); 

                        String intf[] = getSolarisIf(key);

                        if ( (intf!=null) && (intf[0] != null) 

                             && (intf[1] != null) ) 

                            if ( (!intf[0].equals("127.0.0.1")) 

                                 && (!intf[0].equals("0.0.0.0")) ) {

                                newh.put(intf[0],intf[0]);

                                newh.put("NetMask:"+intf[0],intf[1]);

                            }

                    }

                    return newh;

                }

                catch (Exception e) 

                    {

			NmsLogMgr.MISCERR.fail(e.toString(), e);
                    }

            }

        else if( osname.startsWith("Linux") ) {

            try {

                BufferedReader st = new BufferedReader(

                                                       new StringReader(runc.stdout.toString() + "\nEND\n"));



                for (int i=0;i<2;i++) st.readLine();

                Hashtable h = new Hashtable();	

                while (true) {

                    if (!st.ready()) break;

                    String str = st.readLine();

                    StringTokenizer strTok = new StringTokenizer(str);

                    int numTokens = strTok.countTokens();

                    if (numTokens < 8) break;

                    String dest = null;

                    dest = strTok.nextToken();

                    for (int i=0;i<numTokens-2;i++) strTok.nextToken();

                    h.put(strTok.nextToken(), dest);

                }

                Hashtable newh = new Hashtable();

                for (Enumeration e = h.keys();e.hasMoreElements();) {

                    String key = (String) e.nextElement(); 

                    String intf[] = getLinuxIf(key);

                    if ( (intf!=null) && (intf[0] != null) 

                         && (intf[1] != null) ) 

                        if ( (!intf[0].equals("127.0.0.1")) 

                             && (!intf[0].equals("0.0.0.0")) ) {

                            newh.put(intf[0],intf[0]);

                            newh.put("NetMask:"+intf[0],intf[1]);

                        }

                }

                return newh;

            }

            catch (Exception e) 

                {

                    NmsLogMgr.MISCERR.fail(e.toString(), e);
                }

        }

        return null;

    }





    /** 
     * This method is used to get the ipAddresses defined in the ARP
     * table of the localhost.It is used during
     * the initial discovery process to determine the known IP's
     * before doing a broadcast
     * @return Hashtable with the key as ipAddress and the value
     * as the corresponding MAC Address as defined in the ARP table   
     */
	
    public static Hashtable getArpAddrs() {



        if( osname.startsWith("SunOS") || osname.startsWith("Solaris")) {

            ARP_CMD="/usr/sbin/arp -a";

        } else if( osname.startsWith("Linux") ) {

            ARP_CMD="/sbin/arp -a";

        } else if( !osname.startsWith("Windows") ) {

            ARP_CMD="arp -a";

        }



        String cmd = new String(ARP_CMD);

        RunCmd runc = new RunCmd(cmd);

        runc.start();

        do try { Thread.sleep(1000); } catch (Exception e) {}

        //while (runc.isAlive());

        while (!runc.finished);





        if( osname.startsWith("Windows") ) {

            if (runc.stdout == null) return null;

            StringTokenizer st = new StringTokenizer(runc.stdout.toString());



            Hashtable h = null;



            /*int i=10;

            while (st.hasMoreTokens()) {

            String s = st.nextToken();

            if (s.equals("Interface:")) i = 1;



            if (i==8) { 

            if (h==null) h = new Hashtable();

            if (!s.equals("Internet")) if (st.hasMoreTokens())

            h.put(s,st.nextToken()); 

            i = 6; 

            }

            i++;

            }

            return h;



            } */

            String ipAdd=null;

            String hardAdd=null;

            String type=null;



            while (st.hasMoreTokens()) {

                String s = st.nextToken();

                if (s.equals("Type")) {

                    break;

                }

            }



            while(st.hasMoreTokens()) {

                ipAdd = st.nextToken();

                if(st.hasMoreTokens()) {

                    hardAdd = st.nextToken();

                    if(st.hasMoreTokens())

                        type = st.nextToken();

                }

                if((type != null)&&(type.equals("invalid")))continue;



                if (h==null) h = new Hashtable();

                if((ipAdd != null)&&(hardAdd != null)&&(!hardAdd.equals("00:00:00:00:00:00")))

                    h.put(ipAdd,hardAdd);

            }

            return h;



        }

        else if( osname.startsWith("SunOS") || osname.startsWith("Solaris")) {

            try {

                BufferedReader st = new BufferedReader(

                                                       new StringReader(runc.stdout.toString()));



                for (int i=0;i<3;i++) st.readLine();

                Hashtable h = new Hashtable();	

                while (true) {

                    if (!st.ready()) break;

                    String str = st.readLine();

                    if ( (str == null) || str.trim().equals("")) break;

                    StringTokenizer strTok = new StringTokenizer(str);

                    int numTokens = strTok.countTokens();

                    if (numTokens < 3) break;

                    strTok.nextToken();

                    String str1 = strTok.nextToken();

                    str1 = WatchUtil.getIP(str1);

                    for (int i=0;i<numTokens-3;i++) strTok.nextToken();

                    String str2 = strTok.nextToken();

                    if(!str2.equals("00:00:00:00:00:00"))

                        h.put(str1, str2);

                }

                return h;

            } catch (Exception e) {

                NmsLogMgr.MISCERR.fail(e.toString(), e);

            }

        } 

        else if( osname.startsWith("Linux") ) {

            try {

                BufferedReader st = new BufferedReader(

                                                       new StringReader(runc.stdout.toString()));



                for (int i=0;i<1;i++) st.readLine();

                Hashtable h = new Hashtable();	

                while (true) {

                    if (!st.ready()) break;

                    String str = st.readLine();

                    if ( (str == null) || str.trim().equals("")) break;

                    StringTokenizer strTok = new StringTokenizer(str);

                    int numTokens = strTok.countTokens();

                    if (numTokens < 3) break;

                    String str1 = strTok.nextToken();

                    str1 = WatchUtil.getIP(str1);

                    for (int i=0;i<2;i++) strTok.nextToken();

                    String str2 = strTok.nextToken();

                    if(!str2.equals("00:00:00:00:00:00"))

                        h.put(str1, str2);

                }

                return h;

            } catch (Exception e) {

                NmsLogMgr.MISCERR.fail(e.toString(), e);

            }

        }

        return null;

    }


    /** 
     * This method is used to get the ipAddresses of the machines
     * defined in the Routing table of the localhost.It is used during
     * the initial discovery process to determine the known IP's
     * before doing a broadcast
     * @return Hashtable 
     */


    public static Hashtable getRTAddrs() {



        if( osname.startsWith("SunOS") || osname.startsWith("Solaris")) {

            NETSTAT_CMD="/usr/bin/netstat -rn";

        }else if( osname.startsWith("Linux") ) {

            NETSTAT_CMD="/bin/netstat -rn";

        }else if( !osname.startsWith("Windows") ) {

            NETSTAT_CMD="netstat -rn";

        }



        String cmd = new String(NETSTAT_CMD);

        RunCmd runc = new RunCmd(cmd);

        runc.start();

        //while (runc.isAlive()) {

        while (!runc.finished) {

            try { Thread.sleep(100); } catch (Exception e) {}

        }





        if( osname.startsWith("Windows") ) {

            StringTokenizer st = new StringTokenizer(runc.stdout.toString());



            Hashtable h = null;



            int i=6;

            while (st.hasMoreTokens()) {

                String s = st.nextToken();

                if (s.equals("Metric")) i = 1;

                else if (s.equals("Active")) i=6;



                if (i==4) { 

                    if (h==null) h = new Hashtable();

                    if ( (!s.equals("127.0.0.1")) && (!s.equals("0.0.0.0")) )

                        h.put(s,s); 

                    i = -1; 

                }

                i++;

            }

            return h;



        }  else if( osname.startsWith("SunOS") || osname.startsWith("Solaris")) {

            try {

                BufferedReader st = new BufferedReader(

                                                       new StringReader(runc.stdout.toString()));



                for (int i=0;i<4;i++) st.readLine();

                Hashtable h = new Hashtable();	

                while (true) {

                    if (!st.ready()) break;

                    String str = st.readLine();

                    if ( (str == null) || str.trim().equals("")) break;

                    StringTokenizer strTok = new StringTokenizer(str);

                    int numTokens = strTok.countTokens();

                    if (numTokens < 5) break;

                    String str1 = strTok.nextToken();

                    str1 = WatchUtil.getIP(str1);

                    String str2 = strTok.nextToken();

                    str2 = WatchUtil.getIP(str2);

                    h.put(str1, str2);

                }

                return h;

            } catch (Exception e) {

                NmsLogMgr.MISCERR.fail(e.toString(), e);

            }

        }

        else if( osname.startsWith("Linux") ) {

            try {

                BufferedReader st = new BufferedReader(

                                                       new StringReader(runc.stdout.toString()));



                for (int i=0;i<4;i++) st.readLine();

                Hashtable h = new Hashtable();	

                while (true) {

                    if (!st.ready()) break;

                    String str = st.readLine();

                    if ( (str == null) || str.trim().equals("")) break;

                    StringTokenizer strTok = new StringTokenizer(str);

                    int numTokens = strTok.countTokens();

                    if (numTokens < 5) break;

                    String str1 = strTok.nextToken();

                    str1 = WatchUtil.getIP(str1);

                    String str2 = strTok.nextToken();

                    str2 = WatchUtil.getIP(str2);

                    h.put(str1, str2);

                }

                return h;

            } catch (Exception e) {

                NmsLogMgr.MISCERR.fail(e.toString(), e);

            }

        }

        return null;

    }



    /** Get solaris interface details  **/

    static String[] getSolarisIf(String intf) {

        RunCmd runc = new RunCmd("/sbin/ifconfig "+intf);

        runc.start();

        //while (runc.isAlive()) 

        while (!runc.finished) {

            try { Thread.sleep(100); } catch (Exception e) {

                NmsLogMgr.MISCERR.fail(e.toString(), e);

            }

        }



        StringTokenizer st = new StringTokenizer(runc.stdout.toString());

        String sa[] = new String[2];

        while (st.hasMoreTokens()) {

            String s = st.nextToken();
	    
	    //for supporting different locales

            if (s.equals(NmsUtil.GetString("localnet.discovery.inet"))) sa[0] = st.nextToken();

            else if (s.equals(NmsUtil.GetString("localnet.discovery.netmask"))) sa[1] = getNetMask(st.nextToken());

        }

        return sa;

    }



    /** Get linux interface details  **/

    static String[] getLinuxIf(String intf) {

        RunCmd runc = new RunCmd("/sbin/ifconfig "+intf);

        runc.start();

        //while (runc.isAlive()) {

        while (!runc.finished) {

            try { Thread.sleep(100); } catch (Exception e) {

                NmsLogMgr.MISCERR.fail(e.toString(), e);

            }

        }



        StringTokenizer st = new StringTokenizer(runc.stdout.toString());

        String sa[] = new String[2];

        while (st.hasMoreTokens()) {

            String s = st.nextToken();
	    
	    //for supporting different locales
            if (s.equals(NmsUtil.GetString("localnet.discovery.inet"))) {

                String sss = st.nextToken();

                if(sss.startsWith(NmsUtil.GetString("localnet.discovery.addr")+":")) {

                    int index = sss.indexOf(":");

                    sa[0] = sss.substring(index+1,sss.length());

                }

            }

            else if (s.startsWith(NmsUtil.GetString("localnet.discovery.Mask") + ":")) {

                int index = s.indexOf(":");

                sa[1] = s.substring(index+1,s.length());

            }

        }

        return sa;

    }





    /** Get a netmask X.X... from an ffff style netmask **/

    static String getNetMask(String netmask) {

        try {

            Long nmlong = Long.valueOf(netmask,16);

            return WatchUtil.convertAddr(nmlong.longValue());

        } catch (Exception ex) { return null; }

    }


    static Hashtable getFromSeedFile()
    {
        Hashtable h = new Hashtable();
        String line = null;    
        BufferedReader is = null;
        String file = PureUtils.rootDir + "/conf/seed.file";
        try {
            is =  new BufferedReader(new InputStreamReader(CommonUtil.openFile(new File(file))));
            while ( (line = is.readLine()) != null) {		  
	    
                if (line.trim().equals("")) continue;

	        else if (line.startsWith("#")) continue;

	        StringTokenizer st = new StringTokenizer(line);            
                String s = st.nextToken();
                if ( s.startsWith("ip") ){       

	            s = st.nextToken();
	            int a[] = WatchUtil.getAddrArray(s);
	            String s1 = st.nextToken();
	            a = WatchUtil.getAddrArray(s1);

	            if (a == null) {System.out.println(NmsUtil.GetString("Invalid entry in seed file ")+line);return null;}
                    h.put(s,s);
                    h.put("Netmask:"+s,s1);       //No Internationalisation
                }
                else continue;
                
            }
        } catch (IOException e) {
            System.err.println(NmsUtil.GetString("Seed File I/O Error: ")+file); 
        }	
        //System.out.println("@#@#@#@#@#@#@#@# h is "+h);    //No Internationalisation
        return h;
    }

    public static void main ( String[] args)
    {
        //String cmd = "ping "+args[0];
        
        String r = "192.168.1.";
        long start = System.currentTimeMillis();
        for ( int i = 1 ; i < 254 ; i ++)
            {

                r = "192.168.1."+Integer.toString(i);
                long ls = System.currentTimeMillis();
                System.out.println(" KKKK "+Ping.ping(r,0));
                long le = System.currentTimeMillis();
                System.out.println(" R "+r + " time "+(ls-le));
            
            }
        long end = System.currentTimeMillis();
        System.err.println(" Total Time "+(start-end));
        
        
    }

	//This below methods are added by chinnaraj for IPV6 support.

   private static boolean execPing6(String host)
   {
       	 boolean isLinkLocal = false;
	 try
	 {
	 	InetAddressWrapper inet = IPV6Util.getByName(host); 
		isLinkLocal = inet.isLinkLocalAddress();
	 }
	 catch(UnknownHostException e) {}
      String cmd="";
	  if(osname.startsWith("SunOS") || osname.startsWith("Solaris")) 
	  {
		 cmd ="/usr/sbin/ping "+host+" "+num_try;  //No Internationalisation
	  }
	  else if( osname.startsWith("Linux") ) 
	  {
              	 if(isLinkLocal)
		 {
			 cmd = "/usr/sbin/ping6 -c 1 -w 1 -I ";  //No Internationalisation
                         Vector names = com.adventnet.nms.netwatch.DiscoveryAPI.getInstance().getServerDetails().getNetworkInterfaceNames();
			 for(int i=0; i<names.size(); i++)
			 {
				 cmd = cmd + names.elementAt(i) +" "+ host;  //No Internationalisation
				 if(executeCommand(cmd))
					 return true;
			 }
             if(PingErr!=null)
             {
            PingErr.log("[THREAD:: "+Thread.currentThread().getName()+"] "+NmsUtil.GetString("IPV6 Ping failed for device ")+ host,Log.VERBOSE); //No I18N
             }
			 return false;
		 }
		 else
		 {
		 	cmd ="/usr/sbin/ping6 -c 1 -w 1 "+host;  //No Internationalisation
		 }
	  }
	  else if(osname.startsWith("Windows") || osname.startsWith("windows")) //No Internationalization
	  {
		 cmd = "ping -n 1 "+host; //No Internationalization
		 if(isLinkLocal)
		 {
			 //Currently the ScopeID for link local is assumed as 4. 
			 //TODO: Need to find the correct solution.
			 cmd = "ping -n 1 "+host+"%4"; //No Internationalization
		 }
	  }
	  /*else if(osname.startsWith("Windows") ) 
	  {
		 cmd="ping6 -n 1 "+host;  //No Internationalisation
	  }
	  else 
	  {
		 cmd="ping6 -n 1 "+host;  //No Internationalisation
	  }*/
	  System.err.println("cmd is::"+cmd);
          if(executeCommand(cmd))
      {
          return true;
      }
      if(PingErr!=null)
      {
          PingErr.log("[THREAD:: "+Thread.currentThread().getName()+"] "+NmsUtil.GetString("IPV6 Ping failed for device ")+ host,Log.VERBOSE);//No I18N 
      }
      return false;

   }
   
   private static boolean executeCommand(String cmd)
   {
	  RunCmd runc = new RunCmd(cmd);
	  runc.start();
	  do try { Thread.sleep(100); } catch (Exception e) {}
	  while (!runc.finished);

      String reply;
	  if(osname.equalsIgnoreCase("Windows 2003"))  //No Internationalization
	  {
		 if ( (runc.result == false) || (runc.exitValue != 0) )
			return false;
		 else if ( (reply=runc.stdout.toString()).indexOf("Reply from") != -1) //No Internationalization  
			return true;
		 return false;
	  }
	  else if( osname.startsWith("Windows") || osname.startsWith("windows")) //No Internationalization
	  {
		 if ( (runc.result == false) || (runc.exitValue != 0) )
			return false;
		 else if ( (reply=runc.stdout.toString()).indexOf("Reply from") != -1)  
			if (reply.indexOf("bytes=") != -1) return true;
		 return false;
	  }  
	  else if( osname.startsWith("Linux")) 
	  {
        if ( (runc.result == false) || (runc.exitValue != 0) )
			return false;
		 else if ( (reply=runc.stdout.toString()).indexOf("64 bytes from") != -1)
			return true;
		 return false;
	  }
	  else 
	  {
		 if ( (runc.result == false) || (runc.exitValue != 0) ) {
			return false;
		 }
		 else 
		 {
			return true;
		 }
	  }
   }

}
