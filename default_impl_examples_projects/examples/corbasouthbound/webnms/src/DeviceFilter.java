/* $Id: DeviceFilter.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $ */
package com.adventnet.nms.example.southboundcorba;
import com.adventnet.nms.example.southboundcorba.device.*; // The package containing our stubs.
import org.omg.CosNaming.*;  // HelloClient will use the naming service.
import org.omg.CORBA.*;      // All CORBA applications need these classes.
import org.omg.CosNaming.NamingContextPackage.*;


import java.util.*;
import java.rmi.*;
import com.adventnet.nms.topodb.*;
import com.adventnet.nms.util.*;
import com.adventnet.management.policydb.*;
import com.adventnet.management.log.Log;

import com.adventnet.nms.util.*;

public class DeviceFilter implements FoundFilter
{
    private static final boolean debug = true;	

    public ManagedObject filterObject(ManagedObject obj,TopoAPI api)
    {
        StringTokenizer st = new StringTokenizer(obj.getName(),".");
            //String hostName = obj.getName();
            //        StringTokenizer st = new StringTokenizer(hostName,".")
        String hostName = st.nextToken();

 
        if(debug)
        {
        System.out.println("*****DeviceFilter: HostName is - " + hostName);
        }
        obj.setUserProperty("Is_CORBA_Agent", "false");
        if (SCORBAAPI.api.getInfo(hostName,"user.name")!=null)

        //        if (hostName.equals("192.168.4.32")||hostName.equals("srinivask.india.adventnet.com")||hostName.equals("jagannath.india.adventnet.com"))
        {
            //System.out.println("*****CORBA******" + hostName);
            obj.setPollInterval(30);
            obj.setTester("usertest");
            obj.setUClass("com.adventnet.nms.example.southboundcorba.DeviceStatus");
            try 
            {
                String name = SCORBAAPI.api.getInfo(hostName,"user.name");
                String type = SCORBAAPI.api.getInfo(hostName,"os.name");
                String osArch = SCORBAAPI.api.getInfo(hostName,"os.arch");
                System.out.println("******DeviceFilter : "+ name);
                System.out.println("******DeviceType : " + type);
                System.out.println("******Device_OS_Arch : " + osArch);
                //obj.setDisplayName(name);
                obj.setUserProperty("Is_CORBA_Agent", "true");
                obj.setUserProperty("CORBA_DEVICE_UserName", name);
                //obj.setType(type);
                obj.setUserProperty("CORBA_Agent_Devicetype", type);
                obj.setUserProperty("CORBA_Agent_OS_Arch", osArch);
                
            } 
            catch (Exception e) 
            {
		System.out.println("Non-CORBA Node -- CORBA Exception: " + e.toString());
                e.printStackTrace(System.out);
		//return null;
            }
        }
        return obj;
    }
}

