//$Id: WebNMSFEResourceMonitoring.java,v 1.13 2010/10/29 13:45:52 swaminathap Exp $
package com.adventnet.nms.management;

import com.adventnet.nms.util.*;
import com.adventnet.nms.startnms.*;

import java.util.*;

import com.adventnet.nms.poll.*;
import com.adventnet.management.log.Log;

public class WebNMSFEResourceMonitoring
{
	private PollAPI pollAPI = null;
	private int jvmAgentPort = 1600;
	private String feHostAddr = null;
	private String feJVMMgmtPData = null;
	
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
	public Hashtable feVsJVMAgentDetails = new Hashtable();
	private Hashtable threadParam = null;
	private Hashtable memoryParam = null;
	private Hashtable cpuParam = null;
	private String tblSchema = "";//No I18N
	private MainSocketSessionBE mssb = null;
	private NmsMonitoringXMLParser mxp = new NmsMonitoringXMLParser();
	public WebNMSFEResourceMonitoring(MainSocketSessionBE mss)
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
		
		mssb = mss;
		generateTableSchema();
		getPollAPIInstance();
		getHostName();	     
	} 

	public void addResourcePolledData()
	{

		if(pollAPI != null)
		{
			threadParam = mxp.getResourceDetails("FEResourceMonitoring",null,null,"ThreadCount");//No I18N
			memoryParam = mxp.getResourceDetails("FEResourceMonitoring",null,null,"MemoryUsage");//No I18N
			cpuParam = mxp.getResourceDetails("FEResourceMonitoring",null,null,"CPUUsage");//No I18N

			if(getMonitor("ThreadCount"))//No I18N
			{
				addThreadPolledData();
			}
			if(getMonitor("MemoryUsage"))//No I18N
			{
				addMemoryPolledData();
			}
			if(getMonitor("CPUUsage"))//No I18N
			{
				addCPUPolledData();
			}
		}			    
	}			

	public void addThreadPolledData()
	{
		feJVMMgmtPData = "JVMPD_FE_"+jvmAgentPort+"_MonitorThread\tFE_"+feHostAddr+"\t"+threadOID;//No I18N
		try
		{
			if(Collector.pollmgr.alreadyContains(feJVMMgmtPData))
			{
				setThreadPolledDataStatus(true);

			}
			else
			{
				PolledData threadPolledData =new PolledData();
				threadPolledData.setName("JVMPD_FE_"+jvmAgentPort+"_MonitorThread");//No I18N
				threadPolledData.setOid(threadOID);//No I18N
				threadPolledData.setSave(true);
				threadPolledData.setAgent("FE_"+feHostAddr);//No I18N
				threadPolledData.setDnsName(feHostAddr);//No I18N
				threadPolledData.setPeriod(getMonitoringInterval("ThreadCount"));//No I18N
				threadPolledData.setPort(jvmAgentPort);//No I18N
				threadPolledData.setFailureThreshold(2);
				Collector.pollmgr.addCreateSchema("NMS_STATUS_MONITOR%",tblSchema);//No I18N
				Collector.pollmgr.createTable("NMS_STATUS_MONITOR%");//No I18N
				threadPolledData.setStatsDataTableName("NMS_STATUS_MONITOR%");//No I18N
				threadPolledData.setSnmpVersion("v2");//No I18N
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
						NmsLogMgr.MISCUSER.log(NmsUtil.GetString("Threshold configured for FEResourceMonitoring ThreadCount is not available in the Threshold.conf"),Log.SUMMARY);//No I18N
					}
				}
				try
				{
					pollAPI.addPoll(threadPolledData);
				}
				catch(Exception e1)
				{
					System.err.println(" Exception in getting Poll API handle "+e1);//No I18N
					e1.printStackTrace();
				}
			}
		}
		catch(Exception e2)
		{
			e2.printStackTrace();
		}
	}

	public void addMemoryPolledData()
	{
		feJVMMgmtPData = "JVMPD_FE_"+jvmAgentPort+"_MonitorMemory\tFE_"+feHostAddr+"\t"+heapMemoryUsed+"+"+nonHeapMemoryUsed;//No I18N
		try
		{
			if(Collector.pollmgr.alreadyContains(feJVMMgmtPData))
			{
				setMemoryPolledDataStatus(true);
			}
			else
			{
				PolledData memoryPolledData =new PolledData();
				memoryPolledData.setName("JVMPD_FE_"+jvmAgentPort+"_MonitorMemory");//No I18N
				memoryPolledData.setOid(memoryFormula);
				memoryPolledData.setSave(true);
				memoryPolledData.setAgent("FE_"+feHostAddr);//No I18N
				memoryPolledData.setDnsName(feHostAddr);//No I18N
				memoryPolledData.setPeriod(getMonitoringInterval("MemoryUsage"));//No I18N
				memoryPolledData.setPort(jvmAgentPort);//No I18N
				memoryPolledData.setSnmpVersion("v2");//No I18N
				memoryPolledData.setSaveAbsolutes(true);  
				memoryPolledData.setFailureThreshold(2);
				Collector.pollmgr.addCreateSchema("NMS_STATUS_MONITOR%",tblSchema);//No I18N
				Collector.pollmgr.createTable("NMS_STATUS_MONITOR%");//No I18N
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
						NmsLogMgr.MISCUSER.log(NmsUtil.GetString("Threshold configured for FEResourceMonitoring MemoryUsage is not available in the Threshold.conf"),Log.SUMMARY);//No I18N
					}
				}

				try
				{
					pollAPI.addPoll(memoryPolledData);
				}
				catch(Exception e1)
				{
					System.err.println(" Exception in getting Poll API handle "+e1);//No I18N
					e1.printStackTrace();
				}
			}
		}
		catch(Exception e2)
		{
			e2.printStackTrace();
		}
	}

	public void addCPUPolledData()
	{
		String osname = "";
		Properties prop = (Properties)UpdateMaintainer.feJVMDetails.get(mssb.getSessionId());
		if (prop != null)
		{
			osname = prop.getProperty("OSName");//No I18N
		}
		if (osname.startsWith("Win"))
		{
			feJVMMgmtPData = "JVMPD_FE_"+jvmAgentPort+"_MonitorCPU\tFE_"+feHostAddr+"\t"+winOID; // No I18N
		}
		else if (osname.startsWith("Linux"))
		{
			feJVMMgmtPData = "JVMPD_FE_"+jvmAgentPort+"_MonitorCPU\tFE_"+feHostAddr+"\t"+linuxOID; // No I18N
		}
		else if (osname.startsWith("Sun"))
		{
			feJVMMgmtPData = "JVMPD_FE_"+jvmAgentPort+"_MonitorCPU\tFE_"+feHostAddr+"\t"+solarisOID; // No I18N
		}
		try
		{
			if(Collector.pollmgr.alreadyContains(feJVMMgmtPData))
			{
				setCPUPolledDataStatus(true);
			}
			else
			{
				PolledData cpuPolledData = new PolledData();
				cpuPolledData.setName("JVMPD_FE_"+jvmAgentPort+"_MonitorCPU"); // No I18N
				cpuPolledData.setSave(true);
				cpuPolledData.setPeriod(getMonitoringInterval("CPUUsage"));//No I18N
				cpuPolledData.setAgent("FE_"+feHostAddr); // No I18N
				cpuPolledData.setDnsName(feHostAddr); // No I18
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
						NmsLogMgr.MISCUSER.log(NmsUtil.GetString("Threshold configured for FEResourceMonitoring CPUUsage is not available in the Threshold.conf"),Log.SUMMARY);//No I18N
					}
				}

				try
				{
					pollAPI.addPoll(cpuPolledData);
				}
				catch(Exception e1)
				{
					System.err.println(" Exception in getting Poll API handle "+e1);//No I18N
					e1.printStackTrace();
				}
			}
		}
		catch(Exception ee)
		{
			ee.printStackTrace();
		}
	}

	public void setThreadPolledDataStatus(boolean flag)
	{
		feJVMMgmtPData = "JVMPD_FE_"+jvmAgentPort+"_MonitorThread\tFE_"+feHostAddr+"\t"+threadOID;//No I18N
		try
		{
			PolledData tempPolledData = Collector.pollmgr.getPolledData(feJVMMgmtPData);
			if(tempPolledData != null)
			{
				if(flag)
				{
					tempPolledData.setPeriod(getMonitoringInterval("ThreadCount"));//No I18N
					Vector threshNames = new Vector();
					String thresholdName = getThresholdName("ThreadCount");// No I18N
					if(thresholdName != null)
					{
						threshNames.addElement(thresholdName);
						tempPolledData.setThreshold(true);
						if(checkthresholdValidity(thresholdName))
						{
							tempPolledData.setThresholdNames(threshNames);
						}
						else
						{
							NmsLogMgr.MISCUSER.log(NmsUtil.GetString("Threshold configured for FEResourceMonitoring ThreadCount is not available in the Threshold.conf"),Log.SUMMARY);//No I18N
						}
					}
				}
				tempPolledData.setActive(flag);
				pollAPI.modifyPoll(tempPolledData);
				tempPolledData = null;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void setMemoryPolledDataStatus(boolean flag)
	{
		feJVMMgmtPData = "JVMPD_FE_"+jvmAgentPort+"_MonitorMemory\tFE_"+feHostAddr+"\t"+heapMemoryUsed+"+"+nonHeapMemoryUsed;//No I18N
		try
		{
			PolledData tempPolledData = Collector.pollmgr.getPolledData(feJVMMgmtPData);
			if(tempPolledData != null)
			{
				if(flag)
				{
					tempPolledData.setPeriod(getMonitoringInterval("MemoryUsage"));//No I18N
					Vector threshNames = new Vector();
					String thresholdName = getThresholdName("MemoryUsage");// No I18N
					if(thresholdName != null)
					{
						threshNames.addElement(thresholdName);
						tempPolledData.setThreshold(true);
						if(checkthresholdValidity(thresholdName))
						{
							tempPolledData.setThresholdNames(threshNames);
						}
						else
						{
							NmsLogMgr.MISCUSER.log(NmsUtil.GetString("Threshold configured for FEResourceMonitoring MemoryUsage is not available in the Threshold.conf"),Log.SUMMARY);//No I18N
						}						
					}
				}
				tempPolledData.setActive(flag);
				pollAPI.modifyPoll(tempPolledData);
				tempPolledData = null;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void setCPUPolledDataStatus(boolean flag)
	{
		String osname = "";
		Properties prop = (Properties)UpdateMaintainer.feJVMDetails.get(mssb.getSessionId());
		if (prop != null)
		{
			osname = prop.getProperty("OSName");//No I18N
		}
		if (osname.startsWith("Win"))
		{
			feJVMMgmtPData = "JVMPD_FE_"+jvmAgentPort+"_MonitorCPU\tFE_"+feHostAddr+"\t"+winOID; // No I18N
		}
		else if (osname.startsWith("Linux"))
		{
			feJVMMgmtPData = "JVMPD_FE_"+jvmAgentPort+"_MonitorCPU\tFE_"+feHostAddr+"\t"+linuxOID; // No I18N
		}
		else if (osname.startsWith("Sun"))
		{
			feJVMMgmtPData = "JVMPD_FE_"+jvmAgentPort+"_MonitorCPU\tFE_"+feHostAddr+"\t"+solarisOID; // No I18N
		}

		try
		{
			PolledData tempPolledData = Collector.pollmgr.getPolledData(feJVMMgmtPData);
			if(tempPolledData != null)
			{
				if(flag)
				{
					tempPolledData.setPeriod(getMonitoringInterval("CPUUsage"));//No I18N
					Vector threshNames = new Vector();
					String thresholdName = getThresholdName("CPUUsage");// No I18N
					if(thresholdName != null)
					{
						threshNames.addElement(thresholdName);
						tempPolledData.setThreshold(true);
						if(checkthresholdValidity(thresholdName))
						{
							tempPolledData.setThresholdNames(threshNames);
						}
						else
						{
							NmsLogMgr.MISCUSER.log(NmsUtil.GetString("Threshold configured for FEResourceMonitoring CPUUsage is not available in the Threshold.conf"),Log.SUMMARY);//No I18N
						}						
					}
				}
				tempPolledData.setActive(flag);
				pollAPI.modifyPoll(tempPolledData);
				tempPolledData = null;
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
				NmsLogMgr.MISCUSER.log(NmsUtil.GetString("FE JVM Thread Polled Data is not monitored since Monitor parameter is set to false."),Log.DEBUG);//No I18N
				return false;
			}
			else
			{
				NmsLogMgr.MISCUSER.log(NmsUtil.GetString("FEResourceMonitoring ThreadCount Monitor parameter should have values either true or false, hence setting as true."),Log.SUMMARY);//No I18N
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
				NmsLogMgr.MISCUSER.log(NmsUtil.GetString("FE JVM Memory Polled Data is not monitored since Monitor parameter is set to false."),Log.DEBUG);//No I18N
				return false;
			}
			else
			{
				NmsLogMgr.MISCUSER.log(NmsUtil.GetString("FEResourceMonitoring MemoryUsage Monitor parameter should have values either true or false, hence setting as true."),Log.SUMMARY);//No I18N
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
				NmsLogMgr.MISCUSER.log(NmsUtil.GetString("FE JVM CPU Polled Data is not monitored since Monitor parameter is set to false."),Log.DEBUG);//No I18N
				return false;
			}
			else
			{
				NmsLogMgr.MISCUSER.log(NmsUtil.GetString("FEResourceMonitoring CPUUsage Monitor parameter should have values either true or false, hence setting as true."),Log.SUMMARY);//No I18N
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
					NmsLogMgr.MISCUSER.log(NmsUtil.GetString("FEResourceMonitoring ThreadCount MonitoringInterval is less than zero. Setting default value 60."),Log.SUMMARY);//No I18N
					interval = 60;
				}
			}
			catch(Exception e)
			{
				NmsLogMgr.MISCERR.fail(NmsUtil.GetString("Exception while getting FEResourceMonitoring ThreadCount MonitoringInterval. Setting default value 60."),e);//No I18N
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
					NmsLogMgr.MISCUSER.log(NmsUtil.GetString("FEResourceMonitoring MemoryUsage MonitoringInterval is less than zero. Setting default value 60."),Log.SUMMARY);//No I18N
					interval = 60;
				}
			}
			catch(Exception e)
			{
				NmsLogMgr.MISCERR.fail(NmsUtil.GetString("Exception while getting FEResourceMonitoring MemoryUsage MonitoringInterval. Setting default value 60."),e);//No I18N
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
					NmsLogMgr.MISCUSER.log(NmsUtil.GetString("FEResourceMonitoring CPUUsage MonitoringInterval is less than zero. Setting default value 60."),Log.SUMMARY);//No I18N
					interval = 60;
				}
			}
			catch(Exception e)
			{
				NmsLogMgr.MISCERR.fail(NmsUtil.GetString("Exception while getting FEResourceMonitoring CPUUsage MonitoringInterval. Setting default value 60."),e);//No I18N
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
				NmsLogMgr.MISCUSER.log(NmsUtil.GetString("FEResourceMonitoring ThreadCount is not associated with any threshold."),Log.SUMMARY);//No I18N
			}
			return threshNames;
		}
		else if(resourceName.equals("MemoryUsage"))
		{
			threshNames = (String)memoryParam.get("ThresholdName");// No I18N
			if(threshNames == null)
			{
				NmsLogMgr.MISCUSER.log(NmsUtil.GetString("FEResourceMonitoring MemoryUsage is not associated with any threshold."),Log.SUMMARY);//No I18N
			}
			return threshNames;
		}
		else if(resourceName.equals("CPUUsage"))
		{
			threshNames = (String)cpuParam.get("ThresholdName");// No I18N
			if(threshNames == null)
			{
				NmsLogMgr.MISCUSER.log(NmsUtil.GetString("FEResourceMonitoring CPUUsage is not associated with any threshold."),Log.SUMMARY);//No I18N
			}
			return threshNames;
		}
		return threshNames;
	}

	public void disablePolledDatas()
	{
		setThreadPolledDataStatus(false);
		setMemoryPolledDataStatus(false);
		setCPUPolledDataStatus(false);
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

	/* Utility method for getting the PollAPI handle. The Poll API handle will be used for Polled Data addition & Modification purpose */

	private void getPollAPIInstance()
	{
		int retries = 1;
		pollAPI = (PollAPI) NmsUtil.getAPI("PollAPI");//No I18N
		while(pollAPI == null && retries<=25)
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
				System.err.println(" Exception in getting Poll API handle "+e1);//No I18N
				e1.printStackTrace();
			}
			if(NmsUtil.webNMSModulesStarted)
			{
				break;
			}
			retries++;
		}

		if(pollAPI == null)
		{
			NmsLogMgr.MISCERR.log(" Problem in getting PollAPI handle. No JVM Monitoring Polled data will be added. ",Log.SUMMARY);//No I18N
		}

	}

	/* Utility method for finding the FE Server HostAddress. */

	private void getHostName()
	{
		if(feHostAddr == null)
		{
			try
			{
				Properties prop = (Properties)UpdateMaintainer.feJVMDetails.get(mssb.getSessionId());
				if (prop != null)
				{
                                	feHostAddr = prop.getProperty("HOSTADDRESS");//No I18N
				}
			}
			catch(Exception e2)
			{
				System.err.println(" Exception while getting the HostAddress "+e2);//No I18N
				e2.printStackTrace();
			}
		}
	}

	public void setJVMAgentPort(int jvmAgentPort)
	{
		this.jvmAgentPort = jvmAgentPort;
	}
}
