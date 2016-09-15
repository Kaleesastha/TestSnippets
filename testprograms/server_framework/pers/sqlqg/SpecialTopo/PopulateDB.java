/*
 * $Id: PopulateDB.java,v 1.1 2003/06/06 10:09:55 kasturirangan Exp $
 */

package com.adventnet.testtools.sqg;

import java.sql.*;
import java.util.*;
import java.io.*;
import com.adventnet.nms.topodb.*;
import com.adventnet.nms.extend.*;
import com.adventnet.nms.util.*;

/**
 * This class is used to populate the database for testing
 * SQLQueryGenerator
 */
public class PopulateDB implements RunProcessInterface
{
    private boolean initialized = false;
	public void shutDown()
	{
	}
	public void callMain(String args[])
	{
        doTheWork();
	}
	
	public boolean isInitialized()
	{
		return initialized;
	}


    public void doTheWork()
    {
        populateTopoDB();
        initialized = true;
    } 

    private void populateTopoDB()
    {
        System.out.println("populateTopoDB");
        TopoAPI topoAPI = null;
        do
        {
            try{
                Thread.sleep(1000);
            }catch(Exception e){}
            topoAPI = (TopoAPI)NmsUtil.getAPI("TopoAPI");
            if(topoAPI == null)
            {
                System.out.println("TopoAPI handle is not yet got for populating the database");
            }
        } while(topoAPI == null);
        System.out.println("TopoAPI handle got " + topoAPI);
        {
            String[] name = {"111","112","113","114","115","116"};
            String type = "specialMO1";
            String[] specialName = {"11","12","13","14","15","16"};
            int[] specialStatus = {1,2,3,1,2,1};
            String[] operStatus = {"UP", "DOWN", "UP", "DOWN", "UP","UP"};
            String[] adminStatus = {"DOWN", "DOWN", "DOWN", "UP", "UP","UP"};
            for(int i = 0; i < specialName.length; i++)
            {
                addSpecialMO1(topoAPI, name[i], type, specialName[i], specialStatus[i], operStatus[i], adminStatus[i]);
                System.out.println("Added specialMO1:" + name[i]);
            }
        }
        try{
        System.out.println("Topoapi.getByName=" + topoAPI.getByName("111"));
        }catch(Exception e){e.printStackTrace();}
        {
            String[] name = {"21","22","23","24","25","26"};
            String type = "specialMO2";
            String[] specialName = {"21","22","23","24","25","26"};
            int[] specialStatus = {1,2,3,1,2,1};
            String[] operStatus = {"UP", "DOWN", "UP", "DOWN", "UP","UP"};
            String[] adminStatus = {"DOWN", "DOWN", "DOWN", "UP", "UP","UP"};
            String[] mo2 = {"1","2","3","2","1","2"};
            for(int i = 0; i < specialName.length; i++)
            {
                addSpecialMO2(topoAPI, name[i], type, specialName[i], specialStatus[i], operStatus[i], adminStatus[i], mo2[i]);
                System.out.println("Added specialMO2:" + name[i]);
            }
        }
        {
            String[] name = {"31","32","33","34","35","36"};
            String type = "specialMO3";
            String[] specialName = {"31","32","33","34","35","36"};
            int[] specialStatus = {1,2,3,1,2,1};
            String[] operStatus = {"UP", "DOWN", "UP", "DOWN", "UP","UP"};
            String[] adminStatus = {"DOWN", "DOWN", "DOWN", "UP", "UP","UP"};
            String[] mo3 = {"1","2","3","2","1","2"};
            for(int i = 0; i < specialName.length; i++)
            {
                addSpecialMO3(topoAPI, name[i], type, specialName[i], specialStatus[i], operStatus[i], adminStatus[i], mo3[i]);
                System.out.println("Added specialMO3:" + name[i]);
            }
        }
        {
            String[] name = {"41","42","43","44","45","46"};
            String type = "specialMO4";
            String[] specialName = {"41","42","43","44","45","46"};
            int[] specialStatus = {1,2,3,1,2,1};
            String[] operStatus = {"UP", "DOWN", "UP", "DOWN", "UP","UP"};
            String[] adminStatus = {"DOWN", "DOWN", "DOWN", "UP", "UP","UP"};
            String[] mo4 = {"1","2","3","2","1","2"};
            for(int i = 0; i < specialName.length; i++)
            {
                addSpecialMO4(topoAPI, name[i], type, specialName[i], specialStatus[i], operStatus[i], adminStatus[i], mo4[i]);
                System.out.println("Added specialMO4:" + name[i]);
            }
        }
    }

    private static void addSpecialMO1(TopoAPI topoAPI, String name, String type, String specialName, int specialStatus, String operStatus, String adminStatus)
    {
        try{
        specialMO1 mo = new specialMO1();
        mo.setName(name);
        mo.setType(type);
        mo.setSpecialName(specialName);
        mo.setSpecialStatus(specialStatus);
        mo.setOperStatus(operStatus);
        mo.setAdminStatus(adminStatus);
        topoAPI.addObject(mo, false, false);
        }catch(Exception e){e.printStackTrace();}
    }        

    private static void addSpecialMO2(TopoAPI topoAPI, String name, String type, String specialName, int specialStatus, String operStatus, String adminStatus, String mo2)
    {
        try{
        specialMO2 mo = new specialMO2();
        mo.setName(name);
        mo.setType(type);
        mo.setSpecialName(specialName);
        mo.setSpecialStatus(specialStatus);
        mo.setOperStatus(operStatus);
        mo.setAdminStatus(adminStatus);
        mo.setMo2(mo2);
        topoAPI.addObject(mo, false, false);
        }catch(Exception e){e.printStackTrace();}
    }        

    private static void addSpecialMO3(TopoAPI topoAPI, String name, String type, String specialName, int specialStatus, String operStatus, String adminStatus, String mo3)
    {
        try{
        specialMO3 mo = new specialMO3();
        mo.setName(name);
        mo.setType(type);
        mo.setSpecialName(specialName);
        mo.setSpecialStatus(specialStatus);
        mo.setOperStatus(operStatus);
        mo.setAdminStatus(adminStatus);
        mo.setMo3(mo3);
        topoAPI.addObject(mo, false, false);
        }catch(Exception e){e.printStackTrace();}
    }        

    private static void addSpecialMO4(TopoAPI topoAPI, String name, String type, String specialName, int specialStatus, String operStatus, String adminStatus, String mo4)
    {
        try{
        specialMO4 mo = new specialMO4();
        mo.setName(name);
        mo.setType(type);
        mo.setSpecialName(specialName);
        mo.setSpecialStatus(specialStatus);
        mo.setOperStatus(operStatus);
        mo.setAdminStatus(adminStatus);
        mo.setMo4(mo4);
        topoAPI.addObject(mo, false, false);
        }catch(Exception e){e.printStackTrace();}
    }        

    
}
