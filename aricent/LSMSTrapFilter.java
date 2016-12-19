/*****************************************************************************
* FILE NAME: LSMSTrapFilter.java
*
* DESCRIPTION: This class implements a trap filter for handling LSMS
* 			   traps.
*
* DATE      		NAME      DESCRIPTION
* ------------------------------------------------
* 20-Jan-2014		myasir10	Created for PR 223120
* 31-May-2014		Myasir10	Updated for PR 238882 and 239450
* 02 Dec 2014        Puneeta       Updated for BUG 19118683 to resolve UAM/UIM and deviceTimeStamp issue.
*                                  Device time stamp value was obtained as Long not Date Object by correlator during events
*                                  to alert creation or updation which was creating discrepancy in custom views.
*					UAM Number in event object chipps off leading 0s and the new event
*                                  obtained contains leading 0s in it which was creating discrepancy in custom views.
* 
* 12 Jan 2015		Shailesh		Updated for Bug 19104567
* 27 June 2016		Yogesh 			Updated for BUG 23021762  
* Copyright 2015, Oracle
*****************************************************************************/

package com.tekelec.ems.lsms.trap;

import java.net.InetAddress;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.io.IOException;
import com.adventnet.management.log.Log;
import com.adventnet.nms.alertdb.Alert;
import com.adventnet.nms.alertdb.AlertAPI;
import com.adventnet.nms.eventdb.Event;
import com.adventnet.nms.eventdb.TrapFilter;
import com.adventnet.nms.fault.FaultException;
import com.adventnet.nms.util.NmsUtil;
import com.adventnet.snmp.snmp2.SnmpAPI;
import com.adventnet.snmp.snmp2.SnmpPDU;
import com.adventnet.snmp.snmp2.SnmpVarBind;
import com.adventnet.snmp.snmp2.UDPProtocolOptions;
import com.tekelec.ems.fault.FaultDeviceObserver;
import com.tekelec.ems.fault.FaultUtil;
import com.tekelec.ems.fault.SouthboundResyncReference;
import com.tekelec.ems.log.E5MSLogger;
import com.tekelec.ems.log.LogParameters;
import com.tekelec.ems.log.LogParameters.MODULE_NAMES;
import com.tekelec.ems.lsms.topo.Tek_inventory_lsmsnode;
import com.tekelec.ems.lsms.util.LsmsConstants;
import com.tekelec.ems.lsms.util.LsmsStatusUtil;
import com.tekelec.ems.lsms.util.LsmsTrapXmlReader;
import com.tekelec.ems.util.CircularQueue;
import com.tekelec.ems.util.PropertyUtil;
import com.tekelec.ems.util.system.AlmService;
import java.lang.instrument.Instrumentation;

public class LSMSTrapFilter implements TrapFilter {

	/**
	 * Generated serial Version UID
	 */
	private static final long serialVersionUID = -8934862241736113746L;
	
	private final int SwitchOverCompletedTrapOid = 12;
	
	private static FaultUtil fUtil;
	
	//Logger parameter.
	private LogParameters logParam;
	//LSMS EMS logger object.
	private E5MSLogger emsLogger;
	
	public static final String HAS_CLEAR_PROPERTY = "hasClear";
	
	public static final String ZERO = "0";
	
	private static final int CLEAR_SEVERITY_VALUE = 6;

	private static ConcurrentHashMap<String, LsmsTrap> lsmsTrapsDefinedInXml;
	
	private static Properties prop = null ;
	
	/**
	 * This function applies the trap filter on the incoming data.
	 * @param pdu
	 * @return pdu
	 */
	public Object applyTrapFilter(SnmpPDU pdu) {
		//initialize traps map
		if(lsmsTrapsDefinedInXml == null){
			lsmsTrapsDefinedInXml = new LsmsTrapXmlReader().getHashMap();
		}
		//initialize fault util
		if(fUtil == null){
			fUtil = new FaultUtil();
		}
		//Initialize logger
		logParam = new LogParameters();
		emsLogger  = E5MSLogger.getInstance(MODULE_NAMES.FAULTPARSING);
		logParam.setSubModuleName("LSMSTrapFilter");
		emsLogger.logMessage(logParam, "SNMP v3 Traps Recieved... ", Log.DEBUG);
		/**
		 * Check and authenticate SNMP v3 Trap
		 */
		if(pdu.getVersion() == SnmpAPI.SNMP_VERSION_3 &&
				 FaultDeviceObserver.resyncDeviceMap != null)
		{
			Event eventObj = new Event();
			
			
			try{
				System.out.println(pdu.getSecurityModel());
				String str1 = new String(pdu.getUserName());
				System.out.println(str1);
				if(pdu.getEngineID()!=null){
					String str2 = new String(pdu.getEngineID());
					System.out.println(str2);
				}
				if(pdu.getContextID()!= null){
					String str3 = new String(pdu.getContextID());
					System.out.println(str3);
					
				}
				if(pdu.getContextName()!= null){

					String str4 = new String(pdu.getContextName());
					System.out.println(str4);	
				}
				emsLogger.logMessage(logParam, "SNMP v3 Traps check passed", Log.DEBUG);
				UDPProtocolOptions opt = (UDPProtocolOptions)pdu.getProtocolOptions(); 
				String SessionID = opt.getSessionId();
				InetAddress address = opt.getRemoteAddress();
				String sourceIpAddress = address.toString().substring(1);
				emsLogger.logMessage(logParam, "source Ip is "+sourceIpAddress, Log.DEBUG);
				if(FaultDeviceObserver.resyncDeviceMap.containsKey(sourceIpAddress)){
				int specificType = pdu.getSpecificType();
				Vector<SnmpVarBind> varbindsVector = pdu.getVariableBindings();
				eventObj.setCategory(LsmsConstants.CATEGORY);
				//Set Source
				String source = null;
				SouthboundResyncReference sbReference = FaultDeviceObserver
						.resyncDeviceMap.get(sourceIpAddress);
				if (sbReference != null) {
					Tek_inventory_lsmsnode node = (Tek_inventory_lsmsnode) sbReference.getDeviceBean();
					source = node.getLsmsName();
					// source = node.getLsmsIp();
					eventObj.setSource(source);
					// Set event number
					String eventNumber = "";
					for(SnmpVarBind varbind : varbindsVector){
						String oid = varbind.getObjectID().toString();
						if(oid.equalsIgnoreCase(".1.3.6.1.4.1.323.5.3.4.1.1.1.0")){
							
							eventNumber = varbind.getVariable().toString();
							break;
						}
					}
					/**
 					 * Removing leading zeros.  
                     */
					eventNumber  = eventNumber.replaceFirst("^0+(?!$)", "");
					eventObj.setUserProperty("eventid", eventNumber);
					LsmsTrap lsmsObj = lsmsTrapsDefinedInXml.get(eventNumber);
					emsLogger.logMessage(logParam, "Trap recieved with following "
							+ "details:\nSource: "+source+"\nIP: "
							+sourceIpAddress+"\nEvt No.: "+eventNumber, Log.DEBUG);
					//Set description and severity
					if(lsmsObj != null){
						//Set entity
						eventObj.setEntity(node.getLsmsName()+"_"+lsmsObj.getEntity());
						if(lsmsObj.isFormatDescription()){
							int n = varbindsVector.size() - 1;
							Object args [] = new String[n];
							for(int i = 0; i < n; i++){
								args[i] = varbindsVector.get(i+1)
												.getVariable().toString();
								emsLogger.logMessage(logParam, "Varbind value"
										+ " found: "+args[i], Log.DEBUG);
							}
							eventObj.setText(String.format
									(lsmsObj.getDescription(), args));
							/**
							 * BUG 23021762
							 * If event no. is 4100,4200,4300 then refer to 
							 * LsmsPlatformAlarmDesc.properties file for 
							 */
							if(eventNumber.trim().contains("4100") ||eventNumber.trim().contains("4200") ||eventNumber.trim().contains("4300")){
								
									if(prop == null){
										try {
											prop = PropertyUtil.getLsmsPlatformAlarmDesc();		
										    }  catch (Exception e) {
												e.printStackTrace();
										    }
										}
									if(prop == null){
									emsLogger.logMessage(logParam, "[LSMSTrapFilter.java][applyfilter()prop == null", Log.DEBUG);

									}
									String desc = prop.getProperty(args[0].toString());
									
									if(desc != null && desc != ""){
										eventObj.setText(desc);
										}		 
							}
							
						} else {
							eventObj.setText(lsmsObj.getDescription());
						}
						//Set sub-resource
						eventObj.setUserProperty("subresource", lsmsObj.getSubResource());
						//Set device time
						Date date = new Date();
						String timeStamp = DateFormat.getDateTimeInstance().format(date);
						eventObj.setUserProperty("deviceTimeStamp", String.valueOf(date.getTime()));
						//Set count
						eventObj.setUserProperty("count", "1");
						//Set protocol
						eventObj.setUserProperty("protocol", LsmsConstants.PROTOCOL);
						//Set time
						eventObj.setTime(date.getTime());
						int severity = FaultDeviceObserver.eventSeverity
									.get(lsmsObj.getSeverity().toUpperCase());
						eventObj.setSeverity(severity);
						eventObj.setUserProperty(HAS_CLEAR_PROPERTY, lsmsObj.getPairEventNumber()); 
  						if(severity == CLEAR_SEVERITY_VALUE && (! ZERO.equals(lsmsObj.getPairEventNumber()))) {
							eventObj.setEntity(node.getLsmsName()+"_"+lsmsObj.getPairEventNumber());
						} 
						addEventToQueue(eventObj, sourceIpAddress, sbReference,
								node);
					}else {
						emsLogger.logMessage(logParam, "This trap is not " +
								"defined in lsms trap file. OID: "+specificType
								+" Evt No: "+eventNumber, Log.DEBUG);
						return null;
					}
					if(specificType == SwitchOverCompletedTrapOid){
						new LsmsStatusUtil().updateStatusOfSingleLsmsPair(node, sbReference);
					}
				}
				}else {
					emsLogger.logMessage(logParam, "key '"+sourceIpAddress+"' not "
							+ "present in resync map for this trap from "
							+sourceIpAddress, Log.DEBUG);
				}
				
				
			}catch(Exception e) {
				emsLogger.logMessage(logParam, e, Log.INTERMEDIATE_DETAIL);
			}
			
		}
		
		/*
		 * Check if SNMP version of trap is v1. Process only if trap is v1 
		 * else do nothing. 
		 */
		else if(pdu.getVersion() == SnmpAPI.SNMP_VERSION_1 &&
				 FaultDeviceObserver.resyncDeviceMap != null) {
			//Create an Event Object
			Event eventObj = new Event();
			try {
				//fetch IP from the LSMS TRAP pdu.
				UDPProtocolOptions opt = (UDPProtocolOptions)pdu.getProtocolOptions(); 
				InetAddress address = opt.getRemoteAddress();
				String sourceIpAddress = address.toString().substring(1);
				//If IP present in resync map then only process trap.
				if(FaultDeviceObserver.resyncDeviceMap.containsKey(sourceIpAddress)){
					int specificType = pdu.getSpecificType();
					 
					Vector<SnmpVarBind> varbindsVector = pdu.getVariableBindings();
					//Set Category
					eventObj.setCategory(LsmsConstants.CATEGORY);
					//Set Source
					String source = null;
					SouthboundResyncReference sbReference = FaultDeviceObserver
									.resyncDeviceMap.get(sourceIpAddress);
					Tek_inventory_lsmsnode node = (Tek_inventory_lsmsnode) 
													sbReference.getDeviceBean();
					source = node.getLsmsName();
					//source = node.getLsmsIp();
					eventObj.setSource(source);
					//Set event number
					String eventNumber = varbindsVector.get(0).getVariable().toString();
					/**
 					 * Removing leading zeros.  
                                    */
					eventNumber  = eventNumber.replaceFirst("^0+(?!$)", "");
					eventObj.setUserProperty("eventid", eventNumber);
					LsmsTrap lsmsObj = lsmsTrapsDefinedInXml.get(eventNumber);
					emsLogger.logMessage(logParam, "Trap recieved with following "
							+ "details:\nSource: "+source+"\nIP: "
							+sourceIpAddress+"\nEvt No.: "+eventNumber, Log.DEBUG);
					//Set description and severity
					if(lsmsObj != null){
						//Set entity
						eventObj.setEntity(node.getLsmsName()+"_"+lsmsObj.getEntity());
						if(lsmsObj.isFormatDescription()){
							int n = varbindsVector.size() - 1;
							Object args [] = new String[n];
							for(int i = 0; i < n; i++){
								args[i] = varbindsVector.get(i+1)
												.getVariable().toString();
								emsLogger.logMessage(logParam, "Varbind value"
										+ " found: "+args[i], Log.DEBUG);
							}
							eventObj.setText(String.format
									(lsmsObj.getDescription(), args));
							/**
							 * BUG 23021762
							 * If event no. is 4100,4200,4300 then refer to 
							 * LsmsPlatformAlarmDesc.properties file for 
							 */
							if(eventNumber.trim().contains("4100") ||eventNumber.trim().contains("4200") ||eventNumber.trim().contains("4300")){
								
									if(prop == null){
										try {
											prop = PropertyUtil.getLsmsPlatformAlarmDesc();		
										    }  catch (Exception e) {
												e.printStackTrace();
										    }
										}
									if(prop == null){
									emsLogger.logMessage(logParam, "[LSMSTrapFilter.java][applyfilter()prop == null", Log.DEBUG);

									}
									String desc = prop.getProperty(args[0].toString());
									
									if(desc != null && desc != ""){
										eventObj.setText(desc);
										}		 
							}
							
						} else {
							eventObj.setText(lsmsObj.getDescription());
						}
						//Set sub-resource
						eventObj.setUserProperty("subresource", lsmsObj.getSubResource());
						//Set device time
						Date date = new Date();
						String timeStamp = DateFormat.getDateTimeInstance().format(date);
						eventObj.setUserProperty("deviceTimeStamp", String.valueOf(date.getTime()));
						//Set count
						eventObj.setUserProperty("count", "1");
						//Set protocol
						eventObj.setUserProperty("protocol", LsmsConstants.PROTOCOL);
						//Set time
						eventObj.setTime(date.getTime());
						int severity = FaultDeviceObserver.eventSeverity
									.get(lsmsObj.getSeverity().toUpperCase());
						eventObj.setSeverity(severity);
						eventObj.setUserProperty(HAS_CLEAR_PROPERTY, lsmsObj.getPairEventNumber()); 
  						if(severity == CLEAR_SEVERITY_VALUE && (! ZERO.equals(lsmsObj.getPairEventNumber()))) {
							eventObj.setEntity(node.getLsmsName()+"_"+lsmsObj.getPairEventNumber());
						} 
						addEventToQueue(eventObj, sourceIpAddress, sbReference,
								node);
					} else {
						emsLogger.logMessage(logParam, "This trap is not " +
								"defined in lsms trap file. OID: "+specificType
								+" Evt No: "+eventNumber, Log.DEBUG);
						return null;
					}
					if(specificType == SwitchOverCompletedTrapOid){
						new LsmsStatusUtil().updateStatusOfSingleLsmsPair(node, sbReference);
					}

				} else {
					emsLogger.logMessage(logParam, "key '"+sourceIpAddress+"' not "
							+ "present in resync map for this trap from "
							+sourceIpAddress, Log.DEBUG);
				}
			} catch(Exception e) {
				emsLogger.logMessage(logParam, e, Log.INTERMEDIATE_DETAIL);
			}
		}
		return null;
	}
	
	/**
	 * This method will add the given object to E5-MS database
	 */
	private void addEventToQueue(Event eventObj, String sourceIpAddress,
			SouthboundResyncReference sbReference, Tek_inventory_lsmsnode node) {
		//Add event to queue
		CircularQueue queue = sbReference.getCircularQueue();
		if(!sbReference.isBufferOverFlow()
				&& (queue.size() >= FaultDeviceObserver
						.getLsmsQueueMaxSize())){
			sbReference.setBufferOverFlow(true);
			try {
				AlertAPI alertApi = (AlertAPI) NmsUtil.getAPI("AlertAPI");     
				Properties bufferAlmProp = new Properties();
				bufferAlmProp.put("subresource",
						"AlarmMemory_"+node.getLsmsName());

				Vector<Alert> bufferAlertData;

				bufferAlertData = alertApi.getObjects("Alert"
						, bufferAlmProp);
				if (bufferAlertData == null) {
					emsLogger.logMessage(logParam, "Generate" +
							" Buffer Full Event if not exist..."
							, Log.DEBUG);
					fUtil.addBufferFullEventToDB(sourceIpAddress);
				} else {
					emsLogger.logMessage(logParam, "Buffer Full" +
							" Event already exist..."
							, Log.DEBUG);
				}
			} catch (RemoteException e) {
				emsLogger.logMessage(logParam, e, Log.INTERMEDIATE_DETAIL);
			} catch (FaultException e) {
				emsLogger.logMessage(logParam, e, Log.INTERMEDIATE_DETAIL);
			}
		} 
		emsLogger.logMessage(logParam, "Add event to queue "
				+ "Using Snmp Interface..", Log.DEBUG);
		//add event to circular queue.
		queue.add(eventObj);
		synchronized (sbReference.uamLock) {
			sbReference.uamLock.notifyAll();
		}
		
		// Queuing event for aprintd client sessions
		AlmService.populateAlarm(eventObj, false, logParam);
		
		emsLogger.logMessage(logParam, "circular queue "
				+ "updated for source "
				+   sourceIpAddress, Log.DEBUG);
		emsLogger.logMessage(logParam, "circular queue"
				+ " size : "
				+   queue.size(), Log.DEBUG);
	}
	
	
}
