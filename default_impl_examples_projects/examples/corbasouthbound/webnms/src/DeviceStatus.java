/* $Id: DeviceStatus.java,v 1.5 2008/07/30 11:27:41 barathv Exp $ */
package com.adventnet.nms.example.southboundcorba;
import com.adventnet.nms.example.southboundcorba.device.*;
import com.adventnet.nms.util.NmsLogMgr;
import java.util.*;

import com.adventnet.nms.severity.*;
import com.adventnet.nms.netwatch.UserTester;
import com.adventnet.nms.topodb.*;

public class DeviceStatus implements UserTester 
{    String hostName;
    
    public int test(String moname,Properties prop, TopoAPI api) 
    {
        ManagedObject obj = null;
        try
         {
             obj = api.checkOut(moname,0,false,true);
             
         }
         catch( Exception ex) {
            NmsLogMgr.TOPOERR.fail("Exception in getting the object  " + obj.getName(),ex); // no i18n
        }
        StringTokenizer st = new StringTokenizer(obj.getName(),".");
        hostName = st.nextToken();

        System.out.println("****DeviceStatus - trying to check " + hostName);
        SeverityInfo sevInfo = SeverityInfo.getInstance();
        int ignore = sevInfo.getSpecialPurposeSeverity(); 


        String isCorbaAgent = (String)obj.getUserProperty("Is_CORBA_Agent"); //No I18N


        if (!isCorbaAgent.equals("true"))
        {
            System.out.println("****DeviceStatus " + hostName + " returning ignore");
            return ignore;
        }
        
        SeverityIterator iterator = sevInfo.getIterator();
        iterator.moveToHighest(); // iterator state set to critical

        String status = SCORBAAPI.api.getInfo(hostName,"status");
        System.out.println("*****DeviceStatus - " + status);
        if((status !=null)&&(status.equals("clear")))
        {
            System.out.println("*****DeviceStatus - " + status);
            return sevInfo.getClear();
        }
        else
        {
        System.out.println("*****DeviceStatus - " + status);
        return iterator.getPreviousCriticality();
        }
    }
}



