//$Id: WebNMSBEResourceMonitoring.java,v 1.15.6.2 2014/03/20 09:30:05 venkatramanan Exp $
package com.adventnet.nms.management;

import com.adventnet.nms.util.*;
import com.adventnet.nms.startnms.*;
import com.adventnet.nms.poll.*;
import com.adventnet.management.log.Log;
import java.util.*;

public class WebNMSBEResourceMonitoring
{
	String hostAddr = null;//NO I18N
	private PollAPI pollAPI = null;
	private int jvmAgentPort;
   	private String tblSchema = "";//No I18N	

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
	private NmsMonitoringXMLParser mxp = new NmsMonitoringXMLParser();	
	private Hashtable threadParam = null;
	private Hashtable memoryParam = null;
	private Hashtable cpuParam = null;

	public WebNMSBEResourceMonitoring()
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

	}
	public void addResourcePolledData()
	{
		getPollAPIInstance();
        	getHostName();
		generateTableSchema();
		if(pollAPI != null)
        	{
			try
			{
				jvmAgentPort = Integer.parseInt(System.getProperty("com.sun.management.snmp.port"));//No I18N
        		
            			try
            			{
                			waitForPollMgrToInitialize();
            			}
            			catch(NullPointerException e)
            			{
                			return;
            			}
				threadParam = mxp.getResourceDetails("BEResourceMonitoring","ThreadCount");//No I18N
				memoryParam = mxp.getResourceDetails("BEResourceMonitoring","MemoryUsage");//No I18N
				cpuParam = mxp.getResourceDetails("BEResourceMonitoring","CPUUsage");//No I18N
				
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
			catch(NumberFormatException nfe)
			{
				NmsLogMgr.MISCUSER.log(NmsUtil.GetString("Exception in getting SNMP agent port for BE JVM"),Log.SUMMARY);//No I18N				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}	

		}
	}
	
	public void addThreadPolledData()
	{
		try
        	{
            		String pdKey ="JVMPD_BE_"+jvmAgentPort+"_MonitorThread\tBE_"+hostAddr+"\t"+threadOID;//No I18N
            		if(pollAPI.getPolledData(pdKey) == null)
            		{
                		PolledData threadPolledData = new PolledData();
                		threadPolledData.setName("JVMPD_BE_"+jvmAgentPort+"_MonitorThread");//No I18N
                		threadPolledData.setSave(true);
				threadPolledData.setPeriod(getMonitoringInterval("ThreadCount"));//No I18N
				threadPolledData.setOid(threadOID); // No I18N
                		threadPolledData.setAgent("BE_"+hostAddr); //No I18N
                		threadPolledData.setSnmpVersion("v2"); // No I18N
                		threadPolledData.setDnsName(hostAddr); // No I18N
				threadPolledData.setFailureThreshold(2);
                		pollAPI.addCreateSchema("NMS_STATUS_MONITOR%",tblSchema); // No I18N
                		pollAPI.createTable("NMS_STATUS_MONITOR%"); //No I18N
                		threadPolledData.setStatsDataTableName("NMS_STATUS_MONITOR%"); // No I18N
                		threadPolledData.setPort((new Integer(jvmAgentPort)).intValue()); // No I18N
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
						NmsLogMgr.MISCUSER.log(NmsUtil.GetString("Threshold configured for BEResourceMonitoring ThreadCount is not available in the Threshold.conf"),Log.SUMMARY);//No I18N
					}
				}
	            		pollAPI.addPoll(threadPolledData);
            		}
            		else
            		{
				setThreadPolledDataStatus(true);
				
            		}
        	}
        	catch(Exception e)
        	{
            		e.printStackTrace();
        	}
	}
	
	public void addMemoryPolledData()
	{
		try
        	{
				
            		String pdKey = "JVMPD_BE_"+jvmAgentPort+"_MonitorMemory\tBE_"+hostAddr+"\t" + memoryFormula; // No I18N
            		if(pollAPI.getPolledData(pdKey) == null)
            		{
                		PolledData memoryPolledData = new PolledData();
                		memoryPolledData.setName("JVMPD_BE_"+jvmAgentPort+"_MonitorMemory"); // No I18N
                		memoryPolledData.setSave(true);
                		memoryPolledData.setPeriod(getMonitoringInterval("MemoryUsage"));// No I18N
                		memoryPolledData.setAgent("BE_"+hostAddr); // No I18N
                		memoryPolledData.setDnsName(hostAddr); // No I18N                       
                		memoryPolledData.setSnmpVersion("v2"); // No I18N
				memoryPolledData.setFailureThreshold(2);
                		pollAPI.addCreateSchema("NMS_STATUS_MONITOR%",tblSchema); // No I18N
                		pollAPI.createTable("NMS_STATUS_MONITOR%"); //No I18N
                		memoryPolledData.setStatsDataTableName("NMS_STATUS_MONITOR%"); // No I18N
                		//memoryPolledData.setOid((heapMemoryUsed+"+"+nonHeapMemoryUsed)); // No I18N
				//memoryPolledData.setOid("("+"("+(heapMemoryUsed+"+"+nonHeapMemoryUsed)+")"+"*"+"100"+")"+"/"+"("+(maxHeapMemorySize+"+"+maxNonHeapMemorySize)+")");//No I18N
				memoryPolledData.setOid(memoryFormula);
                		memoryPolledData.setSaveAbsolutes(true);                        
                		memoryPolledData.setPort((new Integer(jvmAgentPort)).intValue());//No I18N
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
						NmsLogMgr.MISCUSER.log(NmsUtil.GetString("Threshold configured for BEResourceMonitoring MemoryUsage is not available in the Threshold.conf"),Log.SUMMARY);//No I18N
					}
				}

                		pollAPI.addPoll(memoryPolledData);
            		}
            		else
            		{
				setMemoryPolledDataStatus(true);
			}
        	}
        	catch(Exception ee)
        	{
            		ee.printStackTrace();
        	}
	}

	public void addCPUPolledData()
	{
		try
        	{
			String osname = System.getProperty("os.name");// No I18N
			String pdKey =null;
			if(osname.startsWith("Win"))// No I18N
			{
				pdKey = "JVMPD_BE_"+jvmAgentPort+"_MonitorCPU\tBE_"+hostAddr+"\t"+winOID; // No I18N
			}
			else if(osname.startsWith("Linux"))
			{
				pdKey = "JVMPD_BE_"+jvmAgentPort+"_MonitorCPU\tBE_"+hostAddr+"\t"+linuxOID; // No I18N
			}
			else if (osname.startsWith("Sun"))
			{
				pdKey = "JVMPD_BE_"+jvmAgentPort+"_MonitorCPU\tBE_"+hostAddr+"\t"+solarisOID; // No I18N
			}
			if(pollAPI.getPolledData(pdKey) == null)
            		{
                		PolledData cpuPolledData = new PolledData();
                		cpuPolledData.setName("JVMPD_BE_"+jvmAgentPort+"_MonitorCPU"); // No I18N
                		cpuPolledData.setSave(true);
                		cpuPolledData.setPeriod(getMonitoringInterval("CPUUsage"));// No I18N
                		cpuPolledData.setAgent("BE_"+hostAddr); // No I18N
                		cpuPolledData.setDnsName(hostAddr); // No I18
				cpuPolledData.setSnmpVersion("v2"); // No I18N
				cpuPolledData.setFailureThreshold(2);
                		pollAPI.addCreateSchema("NMS_STATUS_MONITOR%",tblSchema); // No I18N
                		pollAPI.createTable("NMS_STATUS_MONITOR%"); //No I18N
                		cpuPolledData.setStatsDataTableName("NMS_STATUS_MONITOR%"); // No I18N
				if (osname.startsWith("Win"))// No I18N
				{
					cpuPolledData.setOid(winOID); // No I18N
				}
				else if (osname.startsWith("Linux"))// No I18N
				{
					cpuPolledData.setOid(linuxOID); // No I18N
				}
				else if (osname.startsWith("Sun"))// No I18N
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
						NmsLogMgr.MISCUSER.log(NmsUtil.GetString("Threshold configured for BEResourceMonitoring CPUUsage is not available in the Threshold.conf"),Log.SUMMARY);//No I18N
					}
				}

				pollAPI.addPoll(cpuPolledData);
            		}
            		else
            		{
				setCPUPolledDataStatus(true);
			}
        	}
        	catch(Exception ee)
        	{
            		ee.printStackTrace();
        	}
	}

	public void setThreadPolledDataStatus(boolean flag)
    	{
		String threadPDataKey = "JVMPD_BE_"+jvmAgentPort+"_MonitorThread\tBE_"+hostAddr+"\t"+threadOID; // No I18N
		try
        	{
            		if(pollAPI != null)
            		{
				PolledData threadPData = pollAPI.getPolledData(threadPDataKey);
				if(threadPData != null)
            			{
					if(flag)
					{
						threadPData.setPeriod(getMonitoringInterval("ThreadCount"));// No I18N
						Vector threshNames = new Vector();
						String thresholdName = getThresholdName("ThreadCount");// No I18N
						if(thresholdName != null)
						{
							threshNames.addElement(thresholdName);
							threadPData.setThreshold(true);
							if(checkthresholdValidity(thresholdName))
							{
								threadPData.setThresholdNames(threshNames);
							}
							else
							{
								NmsLogMgr.MISCUSER.log(NmsUtil.GetString("Threshold configured for BEResourceMonitoring ThreadCount is not available in the Threshold.conf"),Log.SUMMARY);//No I18N
							}
							
						}
					}
					threadPData.setActive(flag);
      					pollAPI.modifyPoll(threadPData);
            			}

			}
		}
		catch(com.adventnet.management.transaction.UserTransactionException e1)
		{
			e1.printStackTrace();
		}
		catch(Exception e2)
		{
			e2.printStackTrace();
		}

	}

	public void setMemoryPolledDataStatus(boolean flag)
    	{
		String memoryPDataKey = "JVMPD_BE_"+jvmAgentPort+"_MonitorMemory\tBE_"+hostAddr+"\t"+heapMemoryUsed+"+"+nonHeapMemoryUsed; // No I18N
		try
        	{
            		if(pollAPI != null)
            		{
				PolledData memoryPData = pollAPI.getPolledData(memoryPDataKey);
				if(memoryPData != null)
            			{
					if(flag)
					{
						memoryPData.setPeriod(getMonitoringInterval("MemoryUsage"));// No I18N
						Vector threshNames = new Vector();
						String thresholdName = getThresholdName("MemoryUsage");// No I18N
						if(thresholdName != null)
						{
							threshNames.addElement(thresholdName);
							memoryPData.setThreshold(true);
							if(checkthresholdValidity(thresholdName))
							{
								memoryPData.setThresholdNames(threshNames);
							}
							else
							{
								NmsLogMgr.MISCUSER.log(NmsUtil.GetString("Threshold configured for BEResourceMonitoring MemoryUsage is not available in the Threshold.conf"),Log.SUMMARY);//No I18N
							}

						}
					}
					memoryPData.setActive(flag);
                			pollAPI.modifyPoll(memoryPData);
            			}

			}
		}
		catch(com.adventnet.management.transaction.UserTransactionException e1)
		{
			e1.printStackTrace();
		}
		catch(Exception e2)
		{
			e2.printStackTrace();
		}


	}

	public void setCPUPolledDataStatus(boolean flag)
    	{
		String osname = System.getProperty("os.name");
		String cpuPDataKey = null;
		if(osname.startsWith("Win"))
		{
			cpuPDataKey = "JVMPD_BE_"+jvmAgentPort+"_MonitorCPU\tBE_"+hostAddr+"\t"+winOID; // No I18N
		}
		else if(osname.startsWith("Linux"))
		{
			cpuPDataKey = "JVMPD_BE_"+jvmAgentPort+"_MonitorCPU\tBE_"+hostAddr+"\t"+linuxOID; // No I18N
		}
		else if (osname.startsWith("Sun"))
		{
			cpuPDataKey = "JVMPD_BE_"+jvmAgentPort+"_MonitorCPU\tBE_"+hostAddr+"\t"+solarisOID; // No I18N
		}


		try
		{
			if(pollAPI != null)
			{
				PolledData cpuPData = pollAPI.getPolledData(cpuPDataKey);
				if (cpuPData != null)
				{
					if(flag)
					{
						cpuPData.setPeriod(getMonitoringInterval("CPUUsage"));// No I18N
						Vector threshNames = new Vector();
						String thresholdName = getThresholdName("CPUUsage");// No I18N
						if(thresholdName != null)
						{
							threshNames.addElement(thresholdName);
							cpuPData.setThreshold(true);
							if(checkthresholdValidity(thresholdName))
							{
								cpuPData.setThresholdNames(threshNames);
							}
							else
							{
								NmsLogMgr.MISCUSER.log(NmsUtil.GetString("Threshold configured for BEResourceMonitoring CPUUsage is not available in the Threshold.conf"),Log.SUMMARY);//No I18N
							}
							
						}
					}
					cpuPData.setActive(flag);
					pollAPI.modifyPoll(cpuPData);
				}

			}
		}
		catch(com.adventnet.management.transaction.UserTransactionException e1)
		{
			e1.printStackTrace();
		}
		catch(Exception e2)
		{
			e2.printStackTrace();
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
				NmsLogMgr.MISCUSER.log(NmsUtil.GetString("BE JVM Thread Polled Data is not monitored since Monitor parameter is set to false."),Log.DEBUG);//No I18N
				return false;
			}
			else
			{
				NmsLogMgr.MISCUSER.log(NmsUtil.GetString("BEResourceMonitoring ThreadCount Monitor parameter should have values either true or false, hence setting as true."),Log.SUMMARY);//No I18N
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
				NmsLogMgr.MISCUSER.log(NmsUtil.GetString("BE JVM Memory Polled Data is not monitored since Monitor parameter is set to false."),Log.DEBUG);//No I18N
				return false;
			}
			else
			{
				NmsLogMgr.MISCUSER.log(NmsUtil.GetString("BEResourceMonitoring MemoryUsage Monitor parameter should have values either true or false, hence setting as true."),Log.SUMMARY);//No I18N
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
				NmsLogMgr.MISCUSER.log(NmsUtil.GetString("BE JVM CPU Polled Data is not monitored since Monitor parameter is set to false."),Log.DEBUG);//No I18N
				return false;
			}
			else
			{
				NmsLogMgr.MISCUSER.log(NmsUtil.GetString("BEResourceMonitoring CPUUsage Monitor parameter should have values either true or false, hence setting as true."),Log.SUMMARY);//No I18N
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
					NmsLogMgr.MISCUSER.log(NmsUtil.GetString("BEResourceMonitoring ThreadCount MonitoringInterval is less than zero. Setting default value 60."),Log.SUMMARY);//No I18N
					interval = 60;
				}
			}
			catch(Exception e)
			{
				NmsLogMgr.MISCERR.fail(NmsUtil.GetString("Exception while getting BEResourceMonitoring ThreadCount MonitoringInterval. Setting default value 60."),e);//No I18N
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
					NmsLogMgr.MISCUSER.log(NmsUtil.GetString("BEResourceMonitoring MemoryUsage MonitoringInterval is less than zero. Setting default value 60."),Log.SUMMARY);//No I18N
					interval = 60;
				}
			}
			catch(Exception e)
			{
				NmsLogMgr.MISCERR.fail(NmsUtil.GetString("Exception while getting BEResourceMonitoring MemoryUsage MonitoringInterval. Setting default value 60."),e);//No I18N
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
					NmsLogMgr.MISCUSER.log(NmsUtil.GetString("BEResourceMonitoring CPUUsage MonitoringInterval is less than zero. Setting default value 60."),Log.SUMMARY);//No I18N
					interval = 60;
				}
			}
			catch(Exception e)
			{
				NmsLogMgr.MISCERR.fail(NmsUtil.GetString("Exception while getting BEResourceMonitoring CPUUsage MonitoringInterval. Setting default value 60."),e);//No I18N
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
				NmsLogMgr.MISCUSER.log(NmsUtil.GetString("BEResourceMonitoring ThreadCount is not associated with any threshold."),Log.SUMMARY);//No I18N
			}
			return threshNames;
		}
		else if(resourceName.equals("MemoryUsage"))
		{
			threshNames = (String)memoryParam.get("ThresholdName");// No I18N
			if(threshNames == null)
			{
				NmsLogMgr.MISCUSER.log(NmsUtil.GetString("BEResourceMonitoring MemoryUsage is not associated with any threshold."),Log.SUMMARY);//No I18N
			}
			return threshNames;
		}
		else if(resourceName.equals("CPUUsage"))
		{
			threshNames = (String)cpuParam.get("ThresholdName");// No I18N
			if(threshNames == null)
			{
				NmsLogMgr.MISCUSER.log(NmsUtil.GetString("BEResourceMonitoring CPUUsage is not associated with any threshold."),Log.SUMMARY);//No I18N
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

	/* Utility method for getting the PollAPI handle. The Poll API handle will be used for Polled Data addition & Modification purpose */
    
    	private void getPollAPIInstance()
    	{
		pollAPI = (PollAPI) NmsUtil.getAPI("PollAPI");//No I18N
        	while(pollAPI == null)
        	{
            		try
            		{
				try
				{
                		Thread.sleep(200);
				}
				catch(Exception e){}
				pollAPI = (PollAPI) NmsUtil.getAPI("PollAPI");//No I18N
            		}
            		catch(Exception e1)
            		{
                		System.out.println(" Exception in getting Poll API handle "+e1);//No I18N
                		e1.printStackTrace();
            		}
            		if(NmsUtil.webNMSModulesStarted && NmsMainFE.isStarted)
            		{
                		break;
            		}
        	}

        	if(pollAPI == null)
        	{
            		System.err.println(" Problem in getting PollAPI handle from the WebNMSMgmtBEProcess. No JVM Monitoring Polled data will be added. ");//No I18N
        	}

    	}


    
    	/* Utility method for finding the BE Server HostAddress. */

    	private void getHostName()
    	{
        	hostAddr = System.getProperty("nms.server.host");//No I18N
        	if(hostAddr == null)
        	{
            		try
            		{
                		hostAddr = java.net.InetAddress.getLocalHost().getHostAddress();//No I18N
            		}
            		catch(java.net.UnknownHostException e)
            		{
                		e.printStackTrace();
            		}
        	}
    	}

    	/* Utility method for checking the PollMgr initialization
     	*/
    
    	private void waitForPollMgrToInitialize()
    	{
        	int retries = 1;
        	while(!Collector.pollmgr.isInitialized() || retries <= 25)
        	{            
            		try
            		{
                		Thread.sleep(1000);
            		}
            		catch(Exception e)
            		{
            		}
            		retries++;
        	}
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
}
