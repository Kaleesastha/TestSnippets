/* $Id: RMIClientDemo_Scalar.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $ */

package com.adventnet.nms.example.jmxagent;

import com.adventnet.adaptors.clients.rmi.*;
import javax.management.*;
import java.util.*;
import javax.jmx.openmbean.*;
import java.io.*;
/*
 * This is an example program to access NMS RMI-Agent. This program demonstrates the GET and SET operation
 * on scalars by invoking the corresponding Operations. Here, to demonstrate the SET, the user property
 * names for the moUserPropNames attribute, which are input by the user, are set.The GET on scalars is 
 * demonstrated by getting the values of the WebNMSTopoMIB which includes the moUserPropName
 */
public class RMIClientDemo_Scalar
{
    static String hostName = "localhost";
    static String portNo = "1099";

    ObjectName objectName = null;

    RMIClient rmiClient = null;
    MBeanOperationInfo[] opeInfo = null;
    MBeanAttributeInfo[] attInfo = null;

    public static void main(String args[])
    {
	for (int i = 0 ; i < args.length; i+=2)
	{
	    if(args[i].trim().equals("-HOST"))
		hostName = args[i+1];
	    if(args[i].trim().equals("-PORT"))
		portNo = args[i+1];
	}
	
	RMIClientDemo_Scalar rmiclientdemo = new RMIClientDemo_Scalar();
	rmiclientdemo.connect();
	rmiclientdemo.setVal();
	rmiclientdemo.getVal();
    }

    /*
     * In this method, we establish a connection with the agent.The details of the attributes and operations
     * of the WebNMSTopoMibInstrument are obtained by passing the OBJECTNAME.  
     */
    private void connect()
    {
 	try
	{
	    MBeanServer server = MBeanServerFactory.createMBeanServer();

	    rmiClient = new RMIClient();
	    rmiClient.connect(hostName,Integer.parseInt(portNo),null);

	    objectName = new ObjectName("AdventNet_WebNMS_MIB_JMX:type=WebNMSTopoMibInstrument");

	    MBeanInfo mBeanInfo = rmiClient.getMBeanInfo(objectName);

	    opeInfo = mBeanInfo.getOperations();
	    attInfo = mBeanInfo.getAttributes();

	}
	catch(Exception e)
	{
	    System.out.println("Exception = " + e);
	}
    }

    /* 
     * The user is prompted to enter some user property names for the moUserProperties attribute of the
     * WebNMSTopoMibInstrument.The values entered by the user are set to the Agent by invoking the SET operation 
     */

    void setVal()
    {
 	try
	{
	    System.out.println(" \n\nEnter user property names as comma separated String, ");
	    System.out.println(" the values of which will be displayed in the moUserProperties ");
	    System.out.println(" attribute of the MoTable in the AdventNet-WebNMS-MIB");
	    System.out.print(" (The user needs to input values in the given format : \"name,ownerName\") ===>");
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    String propNames = br.readLine();
	    String values[] = {propNames};
	    String types[] = {"java.lang.String"};
	    
	    for(int i = 0; i < opeInfo.length; i++)
	    {
		if( opeInfo[i].getImpact() == opeInfo[i].ACTION)
		{
		    Object result = rmiClient.invoke(objectName,opeInfo[i].getName(),values,types);
		    System.out.println("\n The user property names have been stored in");
		    System.out.println(" moUserPropName attribute present in WebNMSTopo Group");
		}
	    }
	}
	catch(Exception e)
	{
	    System.out.println("Exception = " + e);
	}
    }

    /*
     * The details regarding the WebNMSTopo Group are displayed.The group also includes the values
     * of the moUserPropName specified by the user.
     */
    void getVal()
    {
 	try
	{
	    System.out.println(" Displaying the WebNMSTopo Group details which includes moUserPropNames\n"); 
	    System.out.println(" To check whether the value for moUserPropName has been set, ");
	    System.out.println(" *********** WebNMS Topology Group details ****************");
	    for(int i = 0; i < opeInfo.length; i++)
	    {
		if( opeInfo[i].getImpact() == opeInfo[i].INFO)
		{
		    Object result = rmiClient.invoke(objectName,opeInfo[i].getName(),new Object[]{},new String[]{});
		    System.out.println(attInfo[i].getName() +" = " + result.toString());
		}
	    }
	}
	catch(Exception e)
	{
	    System.out.println("Exception = " + e);
	}
    }
}
