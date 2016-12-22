//$Id: WebNMSClientResourceMonitoring.java,v 1.11 2010/10/29 13:45:51 swaminathap Exp $
package com.adventnet.nms.management;

import com.adventnet.nms.util.*;
import com.adventnet.nms.startnms.*;

import java.util.*;

import com.adventnet.nms.poll.*;

import java.net.InetAddress;

import com.adventnet.nms.fe.utils.*;
import com.adventnet.management.log.Log;

public class WebNMSClientResourceMonitoring
{
	private PollAPI pollAPI = null;
	private PollMgr pollMgr = null;
	
	public static String threadOID = ".1.3.6.1.4.1.42.2.145.3.163.1.1.3.1.0";//No I18N
	public static String heapMemoryUsed = ".1.3.6.1.4.1.42.2.145.3.163.1.1.2.11.0"; //No I18N
	public static String nonHeapMemoryUsed = ".1.3.6.1.4.1.42.2.145.3.163.1.1.2.21.0"; //No I18N

	//FIX for memory graph//
	public static String maxHeapMemorySize = ".1.3.6.1.4.1.42.2.145.3.163.1.1.2.13.0"; //No I18N
	public static String maxNonHeapMemorySize = ".1.3.6.1.4.1.42.2.145.3.163.1.1.2.23.0"; //No I18N
	public static String memoryFormula = "("+"("+(heapMemoryUsed+"+"+nonHeapMemoryUsed)+")"+"*"+"100"+")"+"/"+"("+(maxHeapMemorySize+"+"+maxNonHeapMemorySize)+")";

	private String winOID = ".1.3.6.1.2.1.25.3.3.1.2"; //No I18N
	private String linuxOID = ".1.3.6.1.4.1.2021.10.1.5"; //No I18N
	private String solarisOID = ".1.3.6.1.4.1.2021.10.1.5"; //No I18N
	private String tblSchema = "";//No I18N
	private NmsMonitoringXMLParser mxp = new NmsMonitoringXMLParser();
	transient MainSocketSessionBE mssf;
	Hashtable localHt = null;
	String portNo = null;
	int jvmAgentPort = 0;
	String hostAddr = null;
	private Hashtable threadParam = null;
	private Hashtable memoryParam = null;
	private Hashtable cpuParam = null;

	public WebNMSClientResourceMonitoring(MainSocketSessionBE mss)
	{
		
		Hashtable mapping = NmsUtil.getDeviceOIDAndTypes();
		String value = (String)mapping.get("win_CPU_OID"); //No I18N
		if(value != null && !value.equals(""))//No I18N
		{
			winOID = value;
		}
		String linux_value = (String)mapping.get("linux_CPU_OID");//No I18N
		if(value != null && !value.equals(""))//No I18N
		{
			linuxOID = linux_value;
		}
		String heapMemory = (String)mapping.get("heap_memory_OID");//No I18N
		if(value != null && !value.equals(""))//No I18N
		{
			heapMemoryUsed = heapMemory;
		}
		String nonHeapMemory = (String)mapping.get("non_heap_memory_OID");//No I18N
		if(value != null && !value.equals(""))//No I18N
		{
			nonHeapMemoryUsed = nonHeapMemory;
		}
		String maxHeapMemory = (String)mapping.get("max_heap_memory_OID");//No I18N
		if(value != null && !value.equals(""))//No I18N
		{
			maxHeapMemorySize  = maxHeapMemory;
		}
		String maxNonHeapMemory = (String)mapping.get("max_non_heap_memory_OID");//No I18N
		if(value != null && !value.equals(""))//No I18N
		{
			maxNonHeapMemorySize = maxNonHeapMemory;
		}
		String thread  = (String)mapping.get("thread_OID");//No I18N
		if(value != null && !value.equals(""))//No I18N
		{
			threadOID = thread;
		}
		
		this.mssf = mss;
		generateTableSchema();
		getPollAPIInstance();
	}

	private void generateTableSchema()
	{
		String dbName = "mysql";//No I18N

		try
		{
			dbName = PureServerUtils.getDatabaseName();//No I18N
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		if(dbName.equalsIgnoreCase("mysql"))//No I18N
		{
			tblSchema = "create table <> (POLLID BIGINT,INSTANCE varchar(100),TTIME BIGINT,VAL BIGINT)";//No I18N
		}
		else if(dbName.equalsIgnoreCase("oracle"))//No I18N
		{
			tblSchema = "create table <> (POLLID NUMBER(19),INSTANCE varchar(100),TTIME NUMBER(19),VAL NUMBER(13))";//No I18N
		}
		else if(dbName.equalsIgnoreCase("firebird"))
		{
			tblSchema = "create table <> (POLLID Numeric(12,0),INSTANCE varchar(100),TTIME Numeric(12,0),VAL Numeric(12,0))";//No I18N
		}
		else if(dbName.equalsIgnoreCase("postgresql"))
		{
			tblSchema = "create table <> (POLLID Numeric(19),INSTANCE varchar(100),TTIME Numeric(19),VAL Numeric(13))";//No I18N
		}
		else
		{
			tblSchema = "create table <> (POLLID Numeric(19,0),INSTANCE varchar(100),TTIME Numeric(19,0),VAL Numeric(19,0))";//No I18N
		}
	}

	public void addResourcePolledData(String hostName,String portNo,String hostIP,String osname)
	{
		try{
			jvmAgentPort = (new Integer(portNo)).intValue();

			if(pollAPI != null)
			{
				threadParam = mxp.getResourceDetails("ClientResourceMonitoring",null,null,"ThreadCount");//No I18N
				memoryParam = mxp.getResourceDetails("ClientResourceMonitoring",null,null,"MemoryUsage");//No I18N
				cpuParam = mxp.getResourceDetails("ClientResourceMonitoring",null,null,"CPUUsage");//No I18N
				
				if(getMonitor("ThreadCount"))//No I18N
				{
					addThreadPolledData(hostIP,jvmAgentPort);
				}
				if(getMonitor("MemoryUsage"))//No I18N
				{
					addMemoryPolledData(hostIP,jvmAgentPort);
				}
				if(getMonitor("CPUUsage"))//No I18N
				{
					addCPUPolledData(hostIP,jvmAgentPort,osname);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void addThreadPolledData(String hostAddr,int jvmAgentPort)
	{
		try
		{	
			String clientJVMMgmtPData = "JVMPD_CLIENT_"+jvmAgentPort+"_MonitorThread\tClient_"+hostAddr+"\t"+threadOID;//No I18N
			try
			{
				PolledData tempPD = pollAPI.getPolledData(clientJVMMgmtPData);
				if(tempPD != null)
				{
					setThreadPolledDataStatus(true,jvmAgentPort,hostAddr);	
				}
				else
				{
					PolledData threadPolledData = new PolledData();
					threadPolledData.setName("JVMPD_CLIENT_"+jvmAgentPort+"_MonitorThread");//No I18N
					threadPolledData.setOid(threadOID);
					threadPolledData.setSave(true);
					threadPolledData.setAgent("Client_"+hostAddr);//No I18N
					threadPolledData.setDnsName(hostAddr);//No I18N
					threadPolledData.setPeriod(getMonitoringInterval("ThreadCount"));//No I18N
					threadPolledData.setPort(jvmAgentPort);
					threadPolledData.setSnmpVersion("v2");//No I18N
					threadPolledData.setFailureThreshold(2);
					pollAPI.addCreateSchema("NMS_STATUS_MONITOR%",tblSchema);//No I18N
					pollAPI.createTable("NMS_STATUS_MONITOR%");//No I18N
					threadPolledData.setStatsDataTableName("NMS_STATUS_MONITOR%");//No I18N
					Vector threshNames = new Vector();
					String thresholdName = getThresholdName("ThreadCount");//No I18N
					if(thresholdName != null)
					{
						threshNames.addElement(thresholdName);
						threadPolledData.setThreshold(true);
						if(checkthresholdValidity(thresholdName))
						{
							threadPolledData.setThresholdNames(threshNames);
						}
						else
						{
							NmsLogMgr.MISCUSER.log(NmsUtil.GetString("Threshold configured for ClientResourceMonitoring ThreadCount is not available in the Threshold.conf"),Log.SUMMARY);//No I18N
						}
					}

					try
					{
						pollAPI.addPoll(threadPolledData);
					}
					catch(Exception e)
					{
						System.err.println(" Exception in getting Poll API handle ");//No I18N
						e.printStackTrace();
					}
				}
			}
			catch(Exception e1)
			{
				System.err.println(" Exception while checking the availability of Thread PolledData ClientVM");//No I18N
				e1.printStackTrace();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	public void addMemoryPolledData(String hostAddr,int jvmAgentPort)
	{
		try
		{	
			String clientJVMMgmtPData = "JVMPD_CLIENT_"+jvmAgentPort+"_MonitorMemory\tClient_"+hostAddr+"\t"+heapMemoryUsed+"+"+nonHeapMemoryUsed;//No I18N
			try
			{
				PolledData tempPD = pollAPI.getPolledData(clientJVMMgmtPData);
				if(tempPD != null)
				{
					setMemoryPolledDataStatus(true,jvmAgentPort,hostAddr);                          
				}
				else
				{
					PolledData memoryPolledData = new PolledData();
					memoryPolledData.setName("JVMPD_CLIENT_"+jvmAgentPort+"_MonitorMemory");//No I18N
					memoryPolledData.setOid(memoryFormula);
					memoryPolledData.setSave(true);
					memoryPolledData.setAgent("Client_"+hostAddr);//No I18N
					memoryPolledData.setDnsName(hostAddr);//No I18N
					memoryPolledData.setPeriod(getMonitoringInterval("MemoryUsage"));//No I18N
					memoryPolledData.setPort(jvmAgentPort);//No I18N
					memoryPolledData.setSnmpVersion("v2");//No I18N
					memoryPolledData.setSaveAbsolutes(true);  
					memoryPolledData.setFailureThreshold(2);
					pollAPI.addCreateSchema("NMS_STATUS_MONITOR%",tblSchema);//No I18N
					pollAPI.createTable("NMS_STATUS_MONITOR%");//No I18N
					memoryPolledData.setStatsDataTableName("NMS_STATUS_MONITOR%");//No I18N
					Vector threshNames = new Vector();
					String thresholdName = getThresholdName("MemoryUsage");// No I18N
					if(thresholdName != null)
					{
						threshNames.addElement(thresholdName);
						memoryPolledData.setThreshold(true);
						if(checkthresholdValidity(thresholdName))
						{
							memoryPolledData.setThresholdNames(threshNames);
						}
						else
						{
							NmsLogMgr.MISCUSER.log(NmsUtil.GetString("Threshold configured for ClientResourceMonitoring MemoryUsage is not available in the Threshold.conf"),Log.SUMMARY);//No I18N
						}
					}

					try
					{
						pollAPI.addPoll(memoryPolledData);
					}
					catch(Exception e)
					{
						System.err.println(" Exception in getting Poll API handle ");//No I18N
						e.printStackTrace();
					}
				}

			}
			catch(Exception e1)
			{
				System.err.println(" Exception while checking the availability of Memory PolledData ClientVM");//No I18N
				e1.printStackTrace();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}	

	public void addCPUPolledData(String hostAddr,int jvmAgentPort,String osname)
	{
		try
		{
			String clientJVMMgmtPData = null;
			if (osname.startsWith("Win"))
			{
				clientJVMMgmtPData = "JVMPD_CLIENT_"+jvmAgentPort+"_MonitorCPU\tClient_"+hostAddr+"\t"+winOID;//No I18N
			}
			else if (osname.startsWith("Linux"))
			{
				clientJVMMgmtPData = "JVMPD_CLIENT_"+jvmAgentPort+"_MonitorCPU\tClient_"+hostAddr+"\t"+linuxOID;//No I18N
			}
			else if (osname.startsWith("Sun"))
			{
				clientJVMMgmtPData = "JVMPD_CLIENT_"+jvmAgentPort+"_MonitorCPU\tClient_"+hostAddr+"\t"+solarisOID;//No I18N
			}

			try
			{
				PolledData tempPD = pollAPI.getPolledData(clientJVMMgmtPData);
				if(tempPD != null)
				{
					setCPUPolledDataStatus(true,jvmAgentPort,hostAddr,osname);
				}
				else
				{
					PolledData cpuPolledData = new PolledData();
					cpuPolledData.setName("JVMPD_CLIENT_"+jvmAgentPort+"_MonitorCPU"); // No I18N
					cpuPolledData.setSave(true);
					cpuPolledData.setPeriod(getMonitoringInterval("CPUUsage"));//No I18N
					cpuPolledData.setAgent("Client_"+hostAddr); // No I18N
					cpuPolledData.setDnsName(hostAddr); // No I18
					cpuPolledData.setSnmpVersion("v2"); // No I18N
					cpuPolledData.setFailureThreshold(2);
					pollAPI.addCreateSchema("NMS_STATUS_MONITOR%",tblSchema); // No I18N
					pollAPI.createTable("NMS_STATUS_MONITOR%"); //No I18N
					cpuPolledData.setStatsDataTableName("NMS_STATUS_MONITOR%"); // No I18N
					if (osname.startsWith("Win"))
					{
						cpuPolledData.setOid(winOID); // No I18N
					}
					else if (osname.startsWith("Linux"))
					{
						cpuPolledData.setOid(linuxOID); // No I18N
					}
					else if (osname.startsWith("Sun"))
					{
						cpuPolledData.setOid(solarisOID); // No I18N
					}
					cpuPolledData.setSaveAbsolutes(true);                        
					cpuPolledData.setIsMultiplePolledData(true);                        
					cpuPolledData.setPort(161);//No I18N
					Vector threshNames = new Vector();
					String thresholdName = getThresholdName("CPUUsage");// No I18N
					if(thresholdName != null)
					{
						threshNames.addElement(thresholdName);
						cpuPolledData.setThreshold(true);
						if(checkthresholdValidity(thresholdName))
						{
							cpuPolledData.setThresholdNames(threshNames);
						}
						else
						{
							NmsLogMgr.MISCUSER.log(NmsUtil.GetString("Threshold configured for ClientResourceMonitoring CPUUsage is not available in the Threshold.conf"),Log.SUMMARY);//No I18N
						}
					}

					try
					{
						pollAPI.addPoll(cpuPolledData);
					}
					catch(Exception e)
					{
						System.err.println(" Exception in getting Poll API handle ");//No I18N
						e.printStackTrace();
					}
				}
			}
			catch(Exception e1)
			{
				System.err.println(" Exception while checking the availability of Memory PolledData ClientVM");//No I18N
				e1.printStackTrace();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}   

	public void setThreadPolledDataStatus(boolean flag,int jvmAgentPort,String hostAddr)
	{
		String clientJVMMgmtPData = "JVMPD_CLIENT_"+jvmAgentPort+"_MonitorThread\tClient_"+hostAddr+"\t"+threadOID;//No I18N 
		try
		{
			PolledData tempPD = pollAPI.getPolledData(clientJVMMgmtPData);
			if(tempPD != null)
			{
				if(flag)
				{
					tempPD.setPeriod(getMonitoringInterval("ThreadCount"));//No I18N
					Vector threshNames = new Vector();
					String thresholdName = getThresholdName("ThreadCount");// No I18N
					if(thresholdName != null)
					{
						threshNames.addElement(thresholdName);
						tempPD.setThreshold(true);
						if(checkthresholdValidity(thresholdName))
						{
							tempPD.setThresholdNames(threshNames);
						}
						else
						{
							NmsLogMgr.MISCUSER.log(NmsUtil.GetString("Threshold configured for ClientResourceMonitoring ThreadCount is not available in the Threshold.conf"),Log.SUMMARY);//No I18N
						}
					}
				}
				tempPD.setActive(flag);
				pollAPI.modifyPoll(tempPD);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void setMemoryPolledDataStatus(boolean flag,int jvmAgentPort,String hostAddr)
	{
		String clientJVMMgmtPData = "JVMPD_CLIENT_"+jvmAgentPort+"_MonitorMemory\tClient_"+hostAddr+"\t"+heapMemoryUsed+"+"+nonHeapMemoryUsed;//No I18N
		try
		{
			PolledData tempPD = pollAPI.getPolledData(clientJVMMgmtPData);
			if(tempPD != null)
			{
				if(flag)
				{
					tempPD.setPeriod(getMonitoringInterval("MemoryUsage"));//No I18N
					Vector threshNames = new Vector();
					String thresholdName = getThresholdName("MemoryUsage");// No I18N
					if(thresholdName != null)
					{
						threshNames.addElement(thresholdName);
						tempPD.setThreshold(true);
						if(checkthresholdValidity(thresholdName))
						{
							tempPD.setThresholdNames(threshNames);
						}
						else
						{
							NmsLogMgr.MISCUSER.log(NmsUtil.GetString("Threshold configured for ClientResourceMonitoring MemoryUsage is not available in the Threshold.conf"),Log.SUMMARY);//No I18N
						}
					}

				}
				tempPD.setActive(flag);
				pollAPI.modifyPoll(tempPD);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void setCPUPolledDataStatus(boolean flag,int jvmAgentPort,String hostAddr,String osname)
	{
		String clientJVMMgmtPData = null;
		if (osname.startsWith("Win"))
		{
			clientJVMMgmtPData = "JVMPD_CLIENT_"+jvmAgentPort+"_MonitorCPU\tClient_"+hostAddr+"\t"+winOID;//No I18N
		}
		else if (osname.startsWith("Linux"))
		{
			clientJVMMgmtPData = "JVMPD_CLIENT_"+jvmAgentPort+"_MonitorCPU\tClient_"+hostAddr+"\t"+linuxOID;//No I18N
		}
		else if (osname.startsWith("Sun"))
		{
			clientJVMMgmtPData = "JVMPD_CLIENT_"+jvmAgentPort+"_MonitorCPU\tClient_"+hostAddr+"\t"+solarisOID;//No I18N
		}
		try
		{
			PolledData tempPD = pollAPI.getPolledData(clientJVMMgmtPData);
			if(tempPD != null)
			{
				if(flag)
				{
					tempPD.setPeriod(getMonitoringInterval("CPUUsage"));//No I18N
					Vector threshNames = new Vector();
					String thresholdName = getThresholdName("CPUUsage");// No I18N
					if(thresholdName != null)
					{
						threshNames.addElement(thresholdName);
						tempPD.setThreshold(true);
						if(checkthresholdValidity(thresholdName))
						{
							tempPD.setThresholdNames(threshNames);
						}
						else
						{
							NmsLogMgr.MISCUSER.log(NmsUtil.GetString("Threshold configured for ClientResourceMonitoring CPUUsage is not available in the Threshold.conf"),Log.SUMMARY);//No I18N
						}
					}

				}
				tempPD.setActive(flag);
				pollAPI.modifyPoll(tempPD);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	private boolean getMonitor(String resourceName)
	{
		if(resourceName.equals("ThreadCount"))
		{
			if(threadParam.get("Monitor").equals("true"))//No I18N
			{
				return true;
			}
			else if(threadParam.get("Monitor").equals("false"))
			{
				NmsLogMgr.MISCUSER.log(NmsUtil.GetString("Client JVM Thread Polled Data is not monitored since Monitor parameter is set to false."),Log.DEBUG);//No I18N
				return false;
			}
			else
			{
				NmsLogMgr.MISCUSER.log(NmsUtil.GetString("ClientResourceMonitoring ThreadCount Monitor parameter should have values either true or false, hence setting as true."),Log.SUMMARY);//No I18N
				return true;
			}
		}

		else if(resourceName.equals("MemoryUsage"))
		{
			if(memoryParam.get("Monitor").equals("true"))//No I18N
			{
				return true;
			}
			else if(memoryParam.get("Monitor").equals("false"))
			{
				NmsLogMgr.MISCUSER.log(NmsUtil.GetString("Client JVM Memory Polled Data is not monitored since Monitor parameter is set to false."),Log.DEBUG);//No I18N
				return false;
			}
			else
			{
				NmsLogMgr.MISCUSER.log(NmsUtil.GetString("ClientResourceMonitoring MemoryUsage Monitor parameter should have values either true or false, hence setting as true."),Log.SUMMARY);//No I18N
				return true;
			}
		}

		else if(resourceName.equals("CPUUsage"))
		{
			if(cpuParam.get("Monitor").equals("true"))//No I18N
			{
				return true;
			}
			else if(cpuParam.get("Monitor").equals("false"))
			{
				NmsLogMgr.MISCUSER.log(NmsUtil.GetString("Client JVM CPU Polled Data is not monitored since Monitor parameter is set to false."),Log.DEBUG);//No I18N
				return false;
			}
			else
			{
				NmsLogMgr.MISCUSER.log(NmsUtil.GetString("ClientResourceMonitoring CPUUsage Monitor parameter should have values either true or false, hence setting as true."),Log.SUMMARY);//No I18N
				return true;
			}
		}
		return false;
	}

	private int getMonitoringInterval(String resourceName)
	{
		int interval = 60;
		if(resourceName.equals("ThreadCount"))
		{
			try
			{
				interval = Integer.parseInt((String)threadParam.get("MonitoringInterval"));
				if(interval<0)
				{
					NmsLogMgr.MISCUSER.log(NmsUtil.GetString("ClientResourceMonitoring ThreadCount MonitoringInterval is less than zero. Setting default value 60."),Log.SUMMARY);//No I18N
					interval = 60;
				}
			}
			catch(Exception e)
			{
				NmsLogMgr.MISCERR.fail(NmsUtil.GetString("Exception while getting ClientResourceMonitoring ThreadCount MonitoringInterval. Setting default value 60."),e);//No I18N
				interval = 60;
			}
			return interval;
		}
		else if(resourceName.equals("MemoryUsage"))
		{
			try
			{
				interval = Integer.parseInt((String)memoryParam.get("MonitoringInterval"));
				if(interval<0)
				{
					NmsLogMgr.MISCUSER.log(NmsUtil.GetString("ClientResourceMonitoring MemoryUsage MonitoringInterval is less than zero. Setting default value 60."),Log.SUMMARY);//No I18N
					interval = 60;
				}
			}
			catch(Exception e)
			{
				NmsLogMgr.MISCERR.fail(NmsUtil.GetString("Exception while getting ClientResourceMonitoring MemoryUsage MonitoringInterval. Setting default value 60."),e);//No I18N
				interval = 60;
			}
			return interval;

		}
		else if(resourceName.equals("CPUUsage"))
		{
			try
			{
				interval = Integer.parseInt((String)cpuParam.get("MonitoringInterval"));
				if(interval<0)
				{
					NmsLogMgr.MISCUSER.log(NmsUtil.GetString("ClientResourceMonitoring CPUUsage MonitoringInterval is less than zero. Setting default value 60."),Log.SUMMARY);//No I18N
					interval = 60;
				}
			}
			catch(Exception e)
			{
				NmsLogMgr.MISCERR.fail(NmsUtil.GetString("Exception while getting ClientResourceMonitoring CPUUsage MonitoringInterval. Setting default value 60."),e);//No I18N
				interval = 60;
			}
			return interval;

		}
		return interval;
	}

	private String getThresholdName(String resourceName)
	{
		String threshNames = null;
		if(resourceName.equals("ThreadCount"))
		{
			threshNames = (String)threadParam.get("ThresholdName");// No I18N
			if(threshNames == null)
			{
				NmsLogMgr.MISCUSER.log(NmsUtil.GetString("ClientResourceMonitoring ThreadCount is not associated with any threshold."),Log.SUMMARY);//No I18N
			}
			return threshNames;
		}
		else if(resourceName.equals("MemoryUsage"))
		{
			threshNames = (String)memoryParam.get("ThresholdName");// No I18N
			if(threshNames == null)
			{
				NmsLogMgr.MISCUSER.log(NmsUtil.GetString("ClientResourceMonitoring MemoryUsage is not associated with any threshold."),Log.SUMMARY);//No I18N
			}
			return threshNames;
		}
		else if(resourceName.equals("CPUUsage"))
		{
			threshNames = (String)cpuParam.get("ThresholdName");// No I18N
			if(threshNames == null)
			{
				NmsLogMgr.MISCUSER.log(NmsUtil.GetString("ClientResourceMonitoring CPUUsage is not associated with any threshold."),Log.SUMMARY);//No I18N
			}
			return threshNames;
		}
		return threshNames;
	}

	public void disablePolledDatas(String hostAddr,String portNo,String osname)
	{
		try
		{
			jvmAgentPort = (new Integer(portNo)).intValue();
			setThreadPolledDataStatus(false,jvmAgentPort,hostAddr);
			setMemoryPolledDataStatus(false,jvmAgentPort,hostAddr);
			setCPUPolledDataStatus(false,jvmAgentPort,hostAddr,osname);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	private boolean checkthresholdValidity(String thresholdName)
	{
		try
		{
			Vector threshObjects = pollAPI.getAllThresholdObjects();
			for (int i=0;i<threshObjects.size();i++)
			{
				ThresholdObject tobj = (ThresholdObject)threshObjects.elementAt(i);
				if (tobj.getName().equals(thresholdName))
				{
					if (tobj.getKind().equalsIgnoreCase("long"))
                			{
						return true;
					}

				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	private void getPollAPIInstance()
	{
		int retries = 1;
		pollAPI = (PollAPI) NmsUtil.getAPI("PollAPI");//No I18N
		while(pollAPI == null && retries <=25) 
		{
			try
			{
				try
				{
				Thread.sleep(1000);
				}
				catch(Exception e){}
				pollAPI = (PollAPI)NmsUtil.getAPI("PollAPI");//No I18N
			}
			catch(Exception e1)
			{
				System.out.println(" Exception in getting Poll API handle "+e1);//No I18N
				e1.printStackTrace();
			}
			if(NmsMainFE.isStarted)
			{
				break;
			}
			retries++;
		}

		if(pollAPI == null)
		{
			System.err.println(" Problem in getting PollAPI handle from the WebNMSMgmtFESession. No JVM Monitoring Polled data will be added. ");//No I18N
		}
	}


}
