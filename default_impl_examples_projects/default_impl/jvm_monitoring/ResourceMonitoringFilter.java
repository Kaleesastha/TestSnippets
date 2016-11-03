//$Id: ResourceMonitoringFilter.java,v 1.16 2010/10/29 13:45:51 swaminathap Exp $
package com.adventnet.nms.management;

import java.util.Properties;
import java.util.StringTokenizer;

import com.adventnet.nms.alertdb.Alert;
import com.adventnet.nms.eventdb.Event;
import com.adventnet.nms.eventdb.FilterClient;
import com.adventnet.nms.smtp.SmtpMailer;
import com.adventnet.nms.util.MailConfigParser;
import com.adventnet.nms.util.NmsUtil;

public class ResourceMonitoringFilter implements FilterClient
{
	/**
	 * Default Serial Version UID
	 */
	private static final long serialVersionUID = 1L;
	private Properties mailServerDetails = new Properties();
	private static NmsMonitoringXMLParser mxp = null;
	private String smtpAccountName = "Default"; //No I18N
	// JVM Monitoring Related PD Names
	private String[] jvmMonitoringRelatedPDNames = {"MonitorCPU","MonitorThread","MonitorMemory"};	// No i18N

	public void init()
	{
		mxp = new NmsMonitoringXMLParser();
		mailServerDetails = MailConfigParser.getInstance().getMailServerDetails(smtpAccountName);
	}

	public Event filter(Event e)
	{
		String text = e.getText();// No I18N
		if (isAnJVMMonitoringEvent(text)) {
			try {

				String currentValue = null;
				String pdName = null;
				String pdType = null;
				String specificPDName = null;
				String pdAgent = null;
				String serverType = null;
				String hostIp = null;
				String thresholdValue = null;
				String severity = null;
				String rearmValue = null;
				int currentValueIndex = text.indexOf(NmsUtil.GetString("webclient.fault.eventdetails.value"));
				if (currentValueIndex != -1) {
					currentValue = text.substring(currentValueIndex);	// Sample Value ::: Value: 16.0, Data: JVMPD_BE_16500_MonitorMemory : BE_127.0.0.1 : ((.1.3.6.1.4.1.42.2.145.3.163.1.1.2.11.0+.1.3.6.1.4.1.42.2.145.3.163.1.1.2.21.0)*100)/(.1.3.6.1.4.1.42.2.145.3.163.1.1.2.13.0+.1.3.6.1.4.1.42.2.145.3.163.1.1.2.23.0), Threshold Type: max Critical Threshold: 15.0 Critical Rearm Value: 14.0
					int endIndex = currentValue.indexOf(",");
					if (endIndex != -1) {
						currentValue = currentValue.substring(0, endIndex);	// Sample Value :::  Value: 16.0
						String[] currentValueSplit = currentValue.split(":");
						if (currentValueSplit.length == 2) {
							currentValue = currentValueSplit[1].trim();	// Sample Value :::  16.0
						}
					}
				}
				int pdDetailsIndex = text.indexOf(NmsUtil.GetString("webclient.fault.eventdetails.data"));
				if (pdDetailsIndex != -1) {
					String pdDetails = text.substring(pdDetailsIndex);	// Sample Value :::  Data: JVMPD_BE_16500_MonitorMemory : BE_127.0.0.1 : ((.1.3.6.1.4.1.42.2.145.3.163.1.1.2.11.0+.1.3.6.1.4.1.42.2.145.3.163.1.1.2.21.0)*100)/(.1.3.6.1.4.1.42.2.145.3.163.1.1.2.13.0+.1.3.6.1.4.1.42.2.145.3.163.1.1.2.23.0), Threshold Type: max Critical Threshold: 15.0 Critical Rearm Value: 14.0
					String[] pdDetailsSplit = pdDetails.split(":");
					if (pdDetailsSplit.length >= 3) {
						pdName = pdDetailsSplit[1].trim();	// Sample Value :::  JVMPD_BE_16500_MonitorMemory
						String[] pdNameSplit = pdName.split("_");
						if (pdNameSplit.length >= 1) {
							pdType = pdNameSplit[0].trim();	// Sample Value :::  JVMPD
						}
						if (pdNameSplit.length >= 4) {
							specificPDName = pdNameSplit[3].trim();	// Sample Value :::  MonitorMemory
						}
						pdAgent = pdDetailsSplit[2].trim();	// Sample Value :::  BE_127.0.0.1
						String[] pdAgentSplit = pdAgent.split("_");
						if (pdAgentSplit.length >= 2) {
							serverType = pdAgentSplit[0].trim();	// Sample Value :::  BE
							hostIp = pdAgentSplit[1].trim();	// Sample Value :::  127.0.0.1
						}
					}
				}

				int thresholdDetailsIndex = text.indexOf(NmsUtil.GetString("webclient.fault.eventdetails.threshold") + ": ");
				if (thresholdDetailsIndex != -1) {
					String thresholdDetails = text.substring(thresholdDetailsIndex);	// Threshold: 15.0 Critical Rearm Value: 14.0
					String[] thresholdDetailsSplit = thresholdDetails.split(" ");
					if (thresholdDetailsSplit.length >= 3) {
						thresholdValue = thresholdDetailsSplit[1].trim();	// Sample Value :::  15.0
						severity = thresholdDetailsSplit[2].trim();	// Sample Value :::  Critical
					}
				}

				int rearmIndex = text.indexOf(NmsUtil.GetString("webclient.fault.eventdetails.rearm"));
				if (rearmIndex != -1) {
					String rearmDetails = text.substring(rearmIndex);	// Sample Value :::  Rearm Value: 14.0
					String[] rearmDetailsSplit = rearmDetails.split(":");
					if (rearmDetailsSplit.length >= 1) {
						rearmValue = rearmDetailsSplit[1].trim();	// Sample Value :::  14.0
					}
				}
				
				if(isAnJVMMonitoringEvent(pdName)){
					// Handling Reset Event
					if (text.startsWith("Threshold reset")) {
						Event event = handleResetEvent(e, pdName, pdType, hostIp, "", currentValue, serverType, specificPDName);
						return event;
					}
	
					// Handling Threshold Event
					Event event = handleThresholdEvent(e, pdName, pdType, hostIp, thresholdValue, currentValue, serverType, specificPDName);
					return event;
				}
				return e;
			} catch (Exception ee) {
				ee.printStackTrace();
				// Returning Original Event on exception
				return e;
			}
		}else{
			return e;
		}
	}
	
	/**
	 * This function helps in filtering only jvm monitoring related events. JVM Monitoring related PD names are listed in array jvmMonitoringRelatedPDNames 
	 * and this function will check whether the given text contains any of those listed pds. Returns true when it matches and false otherwise
	 *  
	 * @param eventText the text to look for pd name matches or the pdname itself
	 * @return true if matches and false otherwise
	 */
	private boolean isAnJVMMonitoringEvent(String eventText){
		boolean result = false;
		for (String jvmMonitoringRelatedPD : jvmMonitoringRelatedPDNames) {
			if(eventText.contains(jvmMonitoringRelatedPD)){
				result = true;
				break;
			}
		}
		return result;
	}
	
	private void sendMailNotification(String serverName,String resourceName,String value,String unit, String currentValue)
	{
		try
		{
			String smtpSSL = mailServerDetails.getProperty("SMTP_SSL"); //No I18N
			SmtpMailer mailer = null;
			String subject = java.text.MessageFormat.format(NmsUtil.GetString(mxp.getResourceDetails("ResourceManagement","MailNotification","Subject")),new String[]{serverName,resourceName,value,unit});//No I18N
			String fromAddr = mxp.getResourceDetails("ResourceManagement","MailNotification","FromAddress"); //No I18N
			String toAddr = mxp.getResourceDetails("ResourceManagement","MailNotification","ToAddress"); //No I18N
			String[] toAddresses = getToAddresses(toAddr);
			if(toAddresses!=null){
				for (String toAddress : toAddresses) {
					if(fromAddr != null && !fromAddr.trim().equals("") && toAddress != null && !toAddress.trim().equals("")) //No I18N
					{
						mailer = new SmtpMailer (mailServerDetails.getProperty("SMTP_SERVER"), fromAddr, new String[]{toAddress},subject,null,mailServerDetails.getProperty("USER_NAME"),mailServerDetails.getProperty("PASSWORD"),Boolean.parseBoolean(smtpSSL));//No I18N 
					}
					else
					{
						mailer = new SmtpMailer(smtpAccountName,subject,null);
					}
					
					String body = java.text.MessageFormat.format(NmsUtil.GetString(mxp.getResourceDetails("ResourceManagement", "MailNotification", "Message")), new String[] { serverName, resourceName, resourceName, currentValue, unit });//No I18N
		
					mailer.sendMessage(body);
					mailer.close();
				}
			}
		}
		catch (Exception ee)
		{
			ee.printStackTrace();
		}

	}
	
	/**
	 * Splits the given address list with the delimeter ',' and returs an address array. Values are trimmed and added into the return array
	 * 
	 * @param toAddr comma seperated address list. Ex vivekjustthink@gmail.com, vivekjustthink@ymail.com,etc..
	 * @return An array of addresses
	 */
	private String[] getToAddresses(String toAddr) {
		String[] toAddresses = null;
		if (toAddr != null && toAddr.contains(",")) {
			toAddresses = toAddr.split(",");
			for (int i = 0; i < toAddresses.length; i++) {
				toAddresses[i] = toAddresses[i].trim();
			}
		} else if (toAddr != null) {
			toAddresses = new String[1];
			toAddresses[0] = toAddr;
		}
		return toAddresses;
	}

	private Event handleThresholdEvent(Event e, String pdName,String pdType, String host,String thresholdValue, String curValue, String serverType, String specificPdName)
	{
		if (pdType.equals("JVMPD"))// No I18N
		{
			e.setCategory("NMSManagement");// No I18N
			e.setSource("Resource Monitoring");// No I18N
				
			if (serverType.equals("BE"))
			{
				String jvm = "Back-End Server";// No I18N
				if(specificPdName.equals("MonitorMemory"))// No I18N
				{
					e.setEntity("BE_Memory_Monitoring_"+host);// No I18N
					setText(e,"Memory Utilization",curValue,thresholdValue,jvm,host);// No I18N
					String triggerAction = mxp.getResourceDetails("BEResourceMonitoring","MemoryUsage","TriggerAction");// No I18N

					StringTokenizer strToken = new StringTokenizer(triggerAction,",");// No I18N
					int tokenCount = strToken.countTokens();
					for(int j=0;j<tokenCount;j++)
					{
						String action = strToken.nextToken();
						if(action.equals("MailNotification"))// No I18N
						{
							sendMailNotification(jvm,"Memory Consumption",thresholdValue,"MB", curValue);// No I18N
						}
						else
						{
							System.err.println("The Actions specified for BE JVM MemoryUsage does not include "+action);// No I18N
						}
					}
				}
				else if(specificPdName.equals("MonitorCPU"))// No I18N
				{
					e.setEntity("BE_CPU_Monitoring_"+host);// No I18N
					setText(e,"CPU Utilization",curValue,thresholdValue,jvm,host);// No I18N
					String triggerAction = mxp.getResourceDetails("BEResourceMonitoring","CPUUsage","TriggerAction");// No I18N
					StringTokenizer strToken = new StringTokenizer(triggerAction,",");// No I18N
					int tokenCount = strToken.countTokens();
					for(int j=0;j<tokenCount;j++)
					{
						String action = strToken.nextToken();
						if(action.equals("MailNotification"))// No I18N
						{
							sendMailNotification(jvm,"CPU Utilization",thresholdValue,"Percent", curValue);// No I18N
						}
						else 
						{
							System.err.println("The Actions specified for BE JVM CPUUsage does not include "+action);// No I18N
						}
					}

				}
				else if(specificPdName.equals("MonitorThread"))// No I18N
				{
					e.setEntity("BE_Thread_Monitoring_"+host);// No I18N
					setText(e,"Thread Count",curValue,thresholdValue,jvm,host);// No I18N
					String triggerAction = mxp.getResourceDetails("BEResourceMonitoring","ThreadCount","TriggerAction");// No I18N
					StringTokenizer strToken = new StringTokenizer(triggerAction,",");// No I18N
					int tokenCount = strToken.countTokens();
					for(int j=0;j<tokenCount;j++)
					{
						String action = strToken.nextToken();
						if(action.equals("MailNotification"))// No I18N
						{
							sendMailNotification(jvm,"Thread Count",thresholdValue,"", curValue);// No I18N
						}
						else
						{
							System.err.println("The Actions specified for BE JVM ThreadCount does not include "+action);// No I18N
						}
					}					
				}
			}
			else if (serverType.equals("FE"))// No I18N
			{
				String jvm = "Front-End Server";// No I18N
				
				if(specificPdName.equals("MonitorMemory"))// No I18N
				{
					e.setEntity("FE_Memory_Monitoring_"+host);// No I18N
					setText(e,"Memory Utilization",curValue,thresholdValue,jvm,host);// No I18N
					String triggerAction = mxp.getResourceDetails("FEResourceMonitoring",null,null,"MemoryUsage","TriggerAction");// No I18N
                                	StringTokenizer strToken = new StringTokenizer(triggerAction,",");// No I18N
					int tokenCount = strToken.countTokens();
					for(int j=0;j<tokenCount;j++)
					{
						String action = strToken.nextToken();
						if(action.equals("MailNotification"))// No I18N
						{
							sendMailNotification(jvm,"Memory Consumption",thresholdValue,"MB", curValue);// No I18N
						}
						else
						{
							System.err.println("The Actions specified for FE JVM MemoryUsage does not include "+action);// No I18N
						}
					}					
				}
				else if(specificPdName.equals("MonitorCPU"))// No I18N
				{
					e.setEntity("FE_CPU_Monitoring_"+host);// No I18N
					setText(e,"CPU Utilization",curValue,thresholdValue,jvm,host);// No I18N
					String triggerAction = mxp.getResourceDetails("FEResourceMonitoring",null,null,"CPUUsage","TriggerAction");// No I18N
                                	StringTokenizer strToken = new StringTokenizer(triggerAction,",");// No I18N
					int tokenCount = strToken.countTokens();
					for(int j=0;j<tokenCount;j++)
					{
						String action = strToken.nextToken();
						if(action.equals("MailNotification"))// No I18N
						{
							sendMailNotification(jvm,"CPU Utilization",thresholdValue,"Percent", curValue);// No I18N
						}
						else
						{
							System.err.println("The Actions specified for FE JVM CPUUsage does not include "+action);// No I18N
						}
					}					
				}
				else if(specificPdName.equals("MonitorThread"))// No I18N
				{
					e.setEntity("FE_Thread_Monitoring_"+host);// No I18N
					setText(e,"Thread Count",curValue,thresholdValue,jvm,host);// No I18N
					String triggerAction = mxp.getResourceDetails("FEResourceMonitoring",null,null,"ThreadCount","TriggerAction");// No I18N
					StringTokenizer strToken = new StringTokenizer(triggerAction,",");// No I18N
					int tokenCount = strToken.countTokens();
					for(int j=0;j<tokenCount;j++)
					{		
						String action = strToken.nextToken();
						if(action.equals("MailNotification"))// No I18N
						{
							sendMailNotification(jvm,"Thread Count",thresholdValue,"", curValue);// No I18N						
						}
						else
						{
							System.err.println("The Actions specified for FE JVM ThreadCount does not include "+action);// No I18N
						}
					}					
				}
			}
			else if (serverType.equalsIgnoreCase("CLIENT"))// No I18N
			{
				String jvm = "Client";// No I18N
				
				if(specificPdName.equals("MonitorMemory"))// No I18N
				{
					e.setEntity("Client_Memory_Monitoring_"+host);// No I18N
					setText(e,"Memory Utilization",curValue,thresholdValue,jvm,host);// No I18N
					String triggerAction = mxp.getResourceDetails("ClientResourceMonitoring",null,null,"MemoryUsage","TriggerAction");// No I18N
                                	StringTokenizer strToken = new StringTokenizer(triggerAction,",");// No I18N
					int tokenCount = strToken.countTokens();
					for(int j=0;j<tokenCount;j++)
					{
						String action = strToken.nextToken();
						if(action.equals("MailNotification"))// No I18N
						{
							sendMailNotification(jvm,"Memory Consumption",thresholdValue,"MB", curValue);// No I18N
						}
						else
						{
							System.err.println("The Actions specified for Client JVM MemoryUsage does not include "+action);// No I18N
						}
					}					
				}
				else if(specificPdName.equals("MonitorCPU"))// No I18N
				{
					e.setEntity("Client_CPU_Monitoring_"+host);// No I18N
					setText(e,"CPU Utilization",curValue,thresholdValue,jvm,host);// No I18N
					String triggerAction = mxp.getResourceDetails("ClientResourceMonitoring",null,null,"CPUUsage","TriggerAction");// No I18N
                                	StringTokenizer strToken = new StringTokenizer(triggerAction,",");// No I18N
					int tokenCount = strToken.countTokens();
					for(int j=0;j<tokenCount;j++)
					{
						String action = strToken.nextToken();
						if(action.equals("MailNotification"))// No I18N
						{
							sendMailNotification(jvm,"CPU Utilization",thresholdValue,"Percent", curValue);// No I18N
						}
						else
						{
							System.err.println("The Actions specified for Client JVM CPUUsage does not include "+action);// No I18N
						}
					}					
				}
				else if(specificPdName.equals("MonitorThread"))// No I18N
				{
					e.setEntity("Client_Thread_Monitoring_"+host);// No I18N
					setText(e,"Thread Count",curValue,thresholdValue,jvm,host);// No I18N
					String triggerAction = mxp.getResourceDetails("ClientResourceMonitoring",null,null,"ThreadCount","TriggerAction");// No I18N
					StringTokenizer strToken = new StringTokenizer(triggerAction,",");// No I18N
					int tokenCount = strToken.countTokens();
					for(int j=0;j<tokenCount;j++)
					{
						String action = strToken.nextToken();
						if(action.equals("MailNotification"))// No I18N
						{	
							sendMailNotification(jvm,"Thread Count",thresholdValue,"", curValue);// No I18N						
						}
						else
						{
							System.err.println("The Actions specified for Client JVM ThreadCount does not include "+action);// No I18N
						}
					}					
				}
			}
		}
		else if(pdName.equals("StatusPollingRate"))// No I18N
		{
			e.setEntity("StatusPollingRate_Monitoring_"+host);// No I18N
			setText(e,pdName,curValue,thresholdValue,"",host);// No I18N
			String triggerAction = mxp.getResourceDetails("BEResourceMonitoring","StatusPollingRate","TriggerAction");// No I18N
			StringTokenizer strToken = new StringTokenizer(triggerAction,",");// No I18N
			int tokenCount = strToken.countTokens();
			for(int j=0;j<tokenCount;j++)
			{
				String action = strToken.nextToken();
				if(action.equals("MailNotification"))// No I18N
				{	
					sendMailNotification("","StatusPollingRate",thresholdValue,"Per Second", curValue);// No I18N					
				}
				else
				{
					System.err.println("The Actions specified for StatusPollingRate does not include "+action);// No I18N
				}
			}					
		}
		else if(pdName.equals("TrapRate"))// No I18N
		{
			e.setEntity("TrapRate_Monitoring_"+host);// No I18N
			setText(e,pdName,curValue,thresholdValue,"",host);// No I18N
			String triggerAction = mxp.getResourceDetails("BEResourceMonitoring","TrapProcessingRate","TriggerAction");// No I18N
			StringTokenizer strToken = new StringTokenizer(triggerAction,",");// No I18N
			int tokenCount = strToken.countTokens();
			for(int j=0;j<tokenCount;j++)
			{
				String action = strToken.nextToken();
				if(action.equals("MailNotification"))// No I18N
				{		
					sendMailNotification("","TrapProcessingRate",thresholdValue,"Per Second", curValue);// No I18N	
				}
				else
				{
					System.err.println("The Actions specified for TrapProcessingRate does not include "+action);// No I18N
				}
			}					
		}
		else if(pdName.equals("EventRate"))// No I18N
		{
			e.setEntity("EventRate_Monitoring_"+host);// No I18N
			setText(e,pdName,curValue,thresholdValue,"",host);// No I18N
			String triggerAction = mxp.getResourceDetails("BEResourceMonitoring","EventProcessingRate","TriggerAction");// No I18N
			StringTokenizer strToken = new StringTokenizer(triggerAction,",");// No I18N
			int tokenCount = strToken.countTokens();
			for(int j=0;j<tokenCount;j++)
			{
				String action = strToken.nextToken();
				if(action.equals("MailNotification"))// No I18N
				{	
					sendMailNotification("","EventProcessingRate",thresholdValue,"Per Second", curValue);// No I18N	
				}
				else
				{
					System.err.println("The Actions specified for EventProcessingRate does not include "+action);// No I18N
				}
			}					
		}
		else if(pdName.equals("AlertRate"))// No I18N
		{
			e.setEntity("AlertRate_Monitoring_"+host);// No I18N
			setText(e,pdName,curValue,thresholdValue,"",host);// No I18N
			String triggerAction = mxp.getResourceDetails("BEResourceMonitoring","AlertProcessingRate","TriggerAction");// No I18N
			StringTokenizer strToken = new StringTokenizer(triggerAction,",");// No I18N
			int tokenCount = strToken.countTokens();
			for(int j=0;j<tokenCount;j++)
			{
				String action = strToken.nextToken();
				if(action.equals("MailNotification"))// No I18N
				{
					sendMailNotification("","AlertProcessingRate",thresholdValue,"Per Second", curValue);// No I18N	
				}
				else
				{
					System.err.println("The Actions specified for AlertProcessingRate does not include "+action);// No I18N
				}
			}					
		}
		else if(pdName.equals("DataCollectionRate"))// No I18N
		{
			e.setEntity("DataCollectionRate_Monitoring_"+host);// No I18N
			setText(e,pdName,curValue,thresholdValue,"",host);// No I18N
			String triggerAction = mxp.getResourceDetails("BEResourceMonitoring","DataCollectionRate","TriggerAction");// No I18N
			StringTokenizer strToken = new StringTokenizer(triggerAction,",");// No I18N
			int tokenCount = strToken.countTokens();
			for(int j=0;j<tokenCount;j++)
			{
				String action = strToken.nextToken();
				if(action.equals("MailNotification"))// No I18N
				{	
					sendMailNotification("","DataCollectionRate",thresholdValue,"Per Second", curValue);// No I18N	
				}
				else
				{
					System.err.println("The Actions specified for DataCollectionRate does not include "+action);// No I18N
				}
			}					
		}
		return e;
	}

	private Event handleResetEvent(Event e,String pdName,String pdType, String host, String thresholdValue, String curValue, String serverType, String specificPdName)
	{
		if (pdType.equals("JVMPD"))// No I18N
		{
			e.setCategory("NMSManagement");// No I18N
			e.setSource("Resource Monitoring");// No I18N
			
			if (serverType.equals("BE"))
			{
				String jvm = "Back-End Server";// No I18N
				if(specificPdName.equals("MonitorMemory"))// No I18N
				{
					e.setEntity("BE_Memory_Monitoring_"+host);// No I18N
					setText(e,"Memory Utilization",curValue,thresholdValue,jvm,host);// No I18N
				}
				if(specificPdName.equals("MonitorCPU"))// No I18N
				{
					e.setEntity("BE_CPU_Monitoring_"+host);// No I18N
					setText(e,"CPU Utilization",curValue,thresholdValue,jvm,host);// No I18N
				}
				if(specificPdName.equals("MonitorThread"))// No I18N
				{
					e.setEntity("BE_Thread_Monitoring_"+host);// No I18N
					setText(e,"Thread Count",curValue,thresholdValue,jvm,host);// No I18N
				}
			}
			else if (serverType.equals("FE"))
			{
				String jvm = "Front-End Server";// No I18N
				if(specificPdName.equals("MonitorMemory"))// No I18N
				{
					e.setEntity("FE_Memory_Monitoring_"+host);// No I18N
					setText(e,"Memory Utilization",curValue,thresholdValue,jvm,host);// No I18N
				}
				if(specificPdName.equals("MonitorCPU"))// No I18N
				{
					e.setEntity("FE_CPU_Monitoring_"+host);// No I18N
					setText(e,"CPU Utilization",curValue,thresholdValue,jvm,host);// No I18N
				}
				if(specificPdName.equals("MonitorThread"))// No I18N
				{
					e.setEntity("FE_Thread_Monitoring_"+host);// No I18N
					setText(e,"Thread Count",curValue,thresholdValue,jvm,host);// No I18N
				}
			}
			else if (serverType.equalsIgnoreCase("CLIENT"))
			{
				String jvm = "Client";// No I18N
				if(specificPdName.equals("MonitorMemory"))// No I18N
				{
					e.setEntity("Client_Memory_Monitoring_"+host);// No I18N
					setText(e,"Memory Utilization",curValue,thresholdValue,jvm,host);// No I18N
				}
				if(specificPdName.equals("MonitorCPU"))// No I18N
				{
					e.setEntity("Client_CPU_Monitoring_"+host);// No I18N
					setText(e,"CPU Utilization",curValue,thresholdValue,jvm,host);// No I18N
				}
				if(specificPdName.equals("MonitorThread"))// No I18N
				{
					e.setEntity("Client_Thread_Monitoring_"+host);// No I18N
					setText(e,"Thread Count",curValue,thresholdValue,jvm,host);// No I18N
				}
			}
		}
			if(pdName.equals("StatusPollingRate"))// No I18N
			{
				e.setEntity("StatusPollingRate_Monitoring_"+host);// No I18N
				setText(e,pdName,curValue,thresholdValue,"",host);// No I18N
			}
			else if(pdName.equals("TrapRate"))// No I18N
			{
				e.setEntity("TrapRate_Monitoring_"+host);// No I18N
				setText(e,pdName,curValue,thresholdValue,"",host);// No I18N
			}
			else if(pdName.equals("EventRate"))// No I18N
			{
				e.setEntity("EventRate_Monitoring_"+host);// No I18N
				setText(e,pdName,curValue,thresholdValue,"",host);// No I18N
			}
			else if(pdName.equals("AlertRate"))// No I18N
			{
				e.setEntity("AlertRate_Monitoring_"+host);// No I18N
				setText(e,pdName,curValue,thresholdValue,"",host);// No I18N
			}
			else if(pdName.equals("DataCollectionRate"))// No I18N
			{
				e.setEntity("DataCollectionRate_Monitoring_"+host);// No I18N
				setText(e,pdName,curValue,thresholdValue,"",host);// No I18N
			}
		return e;
	}

	private void setText(Event e,String pdName,String currValue,String value,String jvm,String host)
	{
		String text = "";
		String unit = "";
		if(jvm.equals(""))
		{
			if(value.equals(""))
			{
				text = java.text.MessageFormat.format(NmsUtil.GetString("server.framework.jvmmonitor.rateevent.reset"),new String[]{pdName,currValue,host});
			}
			else
			{
				if((pdName.equals("StatusPollingRate"))||(pdName.equals("DataCollectionRate")))
				{
					text = java.text.MessageFormat.format(NmsUtil.GetString("server.framework.jvmmonitor.rateevent.threshold0"),new String[]{pdName,currValue,value,host});
				}
				else
				{
					text = java.text.MessageFormat.format(NmsUtil.GetString("server.framework.jvmmonitor.rateevent.threshold1"),new String[]{pdName,currValue,value,host});
				}
			}
		}
		else
		{
			if(pdName.contains("Memory"))
			{
				unit = "MB";// No I18N
			}
			else if(pdName.contains("CPU"))
			{
				unit = "%";// No I18N
			}
			if(value.equals(""))
			{
				text = java.text.MessageFormat.format(NmsUtil.GetString("server.framework.jvmmonitor.resourceevent.reset"),new String[]{pdName,currValue,unit,jvm,host});
			}
			else
			{
				text = java.text.MessageFormat.format(NmsUtil.GetString("server.framework.jvmmonitor.resourceevent.threshold"),new String[]{pdName,currValue,unit,value,unit,jvm,host});
			}
		}
		e.setText(text);
	}

	public Alert filter(Alert a)
	{
		return a;
	}

} 
