//$Id: RequestHandler.java,v 1.3.4.1 2012/04/05 09:51:48 wesley Exp $

package com.adventnet.nms.webclient.topo.MOproperty;

import java.util.*;
import java.io.*;
import java.sql.Statement;
import java.sql.ResultSet;
import java.net.InetAddress;
import java.text.MessageFormat;

import org.w3c.dom.*;

import com.adventnet.nms.topodb.TopoAPI;
//import com.adventnet.nms.topodb.TopoDB;
//import com.adventnet.nms.topodb.AnalyzeUser;
//import com.adventnet.nms.mapdb.MapAPI;
//import com.adventnet.nms.mapdb.MapDB;
//import com.adventnet.nms.mapdb.MapSymbol;
import com.adventnet.nms.alertdb.AlertAPI;
//import com.adventnet.nms.eventdb.EventAPI;
import com.adventnet.nms.poll.PollAPI;
//import com.adventnet.nms.authentication.UserConfigAPI;
//import com.adventnet.nms.alertdb.AlertFilterAPI;
//import com.adventnet.management.policydb.NmsPolicyAPI;
//import com.adventnet.nms.util.WatchUtil;
import com.adventnet.nms.alertdb.Alert;
//import com.adventnet.nms.alertdb.FaultMgr;
//import com.adventnet.nms.alertdb.AlertFilter;
//import com.adventnet.nms.eventdb.EventFilterAPI;
//import com.adventnet.nms.alertdb.AlertAnnotation;
//import com.adventnet.nms.eventdb.SendEmail;
//import com.adventnet.nms.eventdb.EventMgr;
//import com.adventnet.nms.server.FilterObject;
//import com.adventnet.nms.eventdb.FilterAction;
//import com.adventnet.nms.eventdb.FilterCommand;
import com.adventnet.nms.topodb.ManagedObject;
//import com.adventnet.nms.topodb.ManagedGroupObject;
import com.adventnet.nms.topodb.TopoObject;
//import com.adventnet.nms.topodb.IpAddress;
//import com.adventnet.nms.topodb.Network;
import com.adventnet.nms.topodb.SnmpNode;
//import com.adventnet.nms.topodb.SnmpInterface;
import com.adventnet.nms.poll.PolledData;
import com.adventnet.nms.poll.CollectedData;
import com.adventnet.nms.poll.MultiplePolledData;
import com.adventnet.nms.poll.PollingObject;
import com.adventnet.nms.severity.SeverityInfo;
//import com.adventnet.security.authorization.AuthorizationAdmin;
//import com.adventnet.nms.topodb.Node;
//import com.adventnet.management.policydb.PolicyObject;
//import com.adventnet.management.policydb.PolicyEvent;
//import com.adventnet.nms.applnfw.discovery.server.model.InetService;
//import com.adventnet.nms.extranet.utils.server.NmsWrapperUtil;

import com.adventnet.nms.util.NmsUtil;
import com.adventnet.management.transaction.ConnectionPool;

//import com.adventnet.nms.fe.common.PanelTreeNode;
//import com.adventnet.nms.fe.common.NmsTreeAPI;
//import com.adventnet.nms.util.Ping;

import com.adventnet.snmp.beans.SnmpTarget;
import com.adventnet.snmp.beans.SnmpTable;
import com.adventnet.snmp.snmp2.SnmpVar;
import com.adventnet.snmp.snmp2.SnmpOID;
import com.adventnet.snmp.mibs.MibOperations;
import test.SwitchObject;

public class RequestHandler 
{
    //private static AlertFilterAPI alertFilterAPI = null;
    //private static EventFilterAPI eventFilterAPI = null;
    private static AlertAPI alertAPI = null;
    //private static EventAPI eventAPI = null;
    private static TopoAPI topoAPI = null;
    private static PollAPI pollAPI = null;
    //private static NmsPolicyAPI policyAPI = null;
    //private static MapAPI mapAPI = null;
    //private static UserConfigAPI userConfigAPI = null;
    //private AuthorizationAdmin authAdmin = null;

    //private static ServiceXML servXML = null;

    //private static final String SEND_EMAIL = "Send Email";
    //private static final String RUN_SYS_COMMAND = "Run System Command";
    //private static final String RUN_PROGRAM = "Run Program";
    //private static final String SEND_SMS = "Send SMS";
    public static final int GET_SNMP_TABLE = 228;
    public static final int GET_SNAPSHOT_DATA = 216;

    //private static final String BUSINESSVIEW_MAPNAME="BusinessView.netmap";

    private int clearSeverity;

    public RequestHandler()
    {
        try
        {
            /*
            if ( servXML == null )
            {
                servXML = ServiceXML.getInstance() ;
            }
            */
            if ( topoAPI == null )
            {
                topoAPI = (TopoAPI) NmsUtil.getAPI("TopoAPI");  //No Internationalization
            }
            /*
            if ( mapAPI == null )
            {
                mapAPI = (MapAPI) NmsUtil.getAPI("MapAPI"); //No Internationalization
            }
            */
            if ( pollAPI == null )
            {
                pollAPI = (PollAPI) NmsUtil.getAPI("PollAPI"); //No Internationalization
            }
            /*
            if ( alertFilterAPI == null )
            {
                alertFilterAPI = (AlertFilterAPI) NmsUtil.getAPI("AlertFilterAPI"); //No Internationalization
            }
            */
            if ( alertAPI == null )
            {
                alertAPI = (AlertAPI) NmsUtil.getAPI("AlertAPI"); //No Internationalization
            }
            /*
            if ( eventAPI == null )
            {
                eventAPI = (EventAPI) NmsUtil.getAPI("EventAPI"); //No Internationalization
            }
            
            if ( policyAPI == null )
            {
                policyAPI = (NmsPolicyAPI) NmsUtil.getAPI("NmsPolicyAPI") ; //No Internationalization
            }
            
            if ( userConfigAPI == null )
            {
                userConfigAPI = (UserConfigAPI) NmsUtil.getAPI("UserConfigAPI") ; //No Internationalization
            }
            */
        }
        catch(Exception rmiEx )
        {
            //printLogMessage( OpManagerLog.MAJOR, "Exception while looking up server APIs ", rmiEx );
            rmiEx.printStackTrace();
        }

    clearSeverity = SeverityInfo.getInstance().getClear();
    }
    /*
    private static void printLogMessage( int level, Object message, Throwable exp)
    {
        //OpManagerLogger.printErr( level, message, exp);
    }

    private static void printLogMessage( int level, Object message )
    {
        //OpManagerLogger.printLog( level, message.toString() );
    }

    */


    Properties getResultForRequest(Properties reqProps, int requestID)
    {
        Properties resProps = new Properties();
        // Alex code - BEGIN
        if ( requestID == GET_SNAPSHOT_DATA )
        {
            if ( reqProps != null )
            {
                try
                {
                    String objName = reqProps.getProperty("objName");
                    //OpManagerLogger.printLog(OpManagerLog.DEBUG, "objName in OpManagerRequestHandler = "+objName);
                    if ( topoAPI != null && topoAPI.isManagedObjectPresent( objName ) )
                    {
                        //Hashtable servStatus = OpManagerServerUtil.getDiscoveredServiceStatus( objName );
                        //resProps.put("DEVICERESPONSETIME", "" + ServiceResponseTime.getDeviceResponseTime( objName ) );
                        //System.out.println("The service status is "+servStatus); 
                        //resProps.put("SERVICE_STATUS", servStatus );
                        /*
                        Properties moProps = topoAPI.getPropertiesOfObject( objName );
                        //resProps.put("MO_PROPERTIES", moProps );
                        System.out.println(" The MO properties fetched from DB is "+moProps); //No Internationalization
                        String category = (String)moProps.getProperty("category");
                        if(category == null)
                        {
                            String motype = (String)moProps.getProperty("type");
                            if(motype != null & motype.equals("Switch"))
                            {
                                moProps.setProperty("category",motype);
                            }
                            else 
                            {
                                boolean isrouter = (new Boolean(moProps.getProperty("isRouter"))).booleanValue();
                                if(isrouter)
                                {
                                    moProps.setProperty("category","Router");
                                }
                                else
                                {
                                    moProps.setProperty("category","Server");
                                }
                            }
                            

                        }
                        */
                        
                        ManagedObject obj = (ManagedObject) topoAPI.getByName( objName );
                        // resProps.put("MO_PROPERTIES", moProps );
                        SnmpTarget target = new SnmpTarget();
                        boolean snmpAlive = true;
                        boolean isCiscoRouter = false;
                        boolean ramUsageCalculated = false;
                        boolean cpuUsageCalculated = false;
                        boolean diskUsageCalculated = false;
                        
                        String isDeviceQuery = reqProps.getProperty("isDeviceQuery");
                        
                        if(isDeviceQuery != null && !isDeviceQuery.equals("true")) //No Internationalization
                        {
                            getDetailsFromDB(objName, resProps); 
                        }
                        else 
                        {

                        if ((obj instanceof SnmpNode))
                        {
                            
                            TopoObject tobj = (TopoObject)obj;
                            String version = tobj.getVersion().trim();
                            target.setCommunity( tobj.getCommunity());
                            int ver = 0;
                            if(version!=null)
                            {
                                if(version.equals("v2")) //No Internationalization
                                {
                                    ver = 1;
                                } else if(version.equals("v3")) //No Internationalization
                                {
                                    ver = 3;
                                }
                            }
                            target.setSnmpVersion(ver);

                            target.setTargetHost(tobj.getName());
                            target.setTargetPort(tobj.getSnmpport());
                            if(ver==3)
                            {
                                target.setPrincipal(tobj.getUserName());
                                target.setContextName(tobj.getContextName());
                            }
                            else
                            {
                                target.setCommunity(tobj.getCommunity());
                            }
                            target.setTimeout(2);
                            //target.setObjectID(".1.3.6.1.2.1.1.1.0");
                            String reslt = null;
                            SnmpVar snmpvar = target.snmpGet(new SnmpOID(".1.3.6.1.2.1.1.1.0")); //No Internationalization
                            if (snmpvar != null)
                            {
                                if(snmpvar.toString().trim().equalsIgnoreCase("NULL")) //No Internationalization
                                {
                                    snmpAlive = false;
                                }
                                try
                                {
                                    reslt = new String(snmpvar.toBytes(),"UTF-8"); //No Internationalization
                                }
                                catch(java.io.UnsupportedEncodingException uee)
                                {
                                    reslt = new String(snmpvar.toBytes(),NmsUtil.getParameter("CHAR_ENCODING")); //No Internationalization
                                }
                            }
                            if ( reslt == null )
                            {
                                snmpAlive = false;
                            }
                            
                            if ( snmpAlive )
                            {
                               
                                target.setTimeout( 6 );
                                isCiscoRouter = (tobj.getIsRouter() || tobj instanceof SwitchObject); //No Internationalization

                                HostResourceCollection hrinfo = new HostResourceCollection(target);
                                //target.releaseResources();
                                boolean opmObjChanged = false;
                                if(isCiscoRouter)
                                {
                                    long cpuU = hrinfo.getRouterCPU();
                                    if(cpuU != -1)
                                    {
                                        resProps.setProperty("CPU_USAGE", ""+cpuU ); //No Internationalization
                                    }
                                    else
                                    {
                                        resProps.setProperty("CPU_USAGE", "NODATA" ); //No Internationalization
                                    }

                                    long ramU = hrinfo.getRouterMemUsage();
                                    if(ramU != -1)
                                    {
                                        resProps.setProperty("RAM_USAGE", ""+ramU ); //No Internationalization
                                    }
                                    else
                                    {
                                        resProps.setProperty("RAM_USAGE", "NODATA" ); //No Internationalization
                                    }
                                    resProps.setProperty("DISK_USAGE", "NODATA" ); //No Internationalization
                                }
                                else
                                {
                                    HostDetail hrInfo = null;
                                    long[] usage = hrinfo.getUsage();
                                    if( usage[0] > 0 )
                                    {
                                        long hdSize = -1; //= obj.getHardDiskSize();
                                        if(hdSize <= 0) // If No hard disk size present in DB.
                                        {
                                            //OpManagerLogger.printLog(OpManagerLog.DEBUG, "Hard disk usage  available. But total hd size not in OpManager Obj for "+obj.getName());
                                            hrInfo = new HostDetail();
                                            hrInfo.getHostDetail(obj);
                                            hdSize = hrInfo.getDiskSize();
                                            //OpManagerLogger.printLog(OpManagerLog.DEBUG, "Hard disk size get from the device is "+hdSize);
                                            //obj.setHardDiskSize((int)hdSize);
                                            opmObjChanged = true;
                                        }

                                        //OpManagerLogger.printLog(OpManagerLog.DEBUG, "hdSize---"+hdSize);
                                        long totalBytes = (long) hdSize *( 1024 * 1024 * 1024 ) ;
                                        long percentage = 0;
                                        if(hdSize > 0)
                                        {
                                            double freeBytes = ( double )( totalBytes - usage[0] ) ;
                                            double usedBytes = ( double )( totalBytes - freeBytes ) ;

                                            double freeDbl = (double) ( freeBytes / ( 1024 * 1024 * 1024 )) ;
                                            double usedDbl = (double) ( usedBytes /  ( 1024 * 1024 * 1024 )) ;
                                            double totalDbl = (double) ( totalBytes /  ( 1024 * 1024 * 1024 )) ;

                                            resProps.setProperty("FREE_DISK", "" + roundOff( freeDbl ) ); // .substring( 0,5) );
                                            resProps.setProperty("USED_DISK", "" + roundOff( usedDbl ) ); //.substring( 0,5)  );
                                            resProps.setProperty("TOTAL_DISK", "" + totalDbl );
                                            //OpManagerLogger.printLog(OpManagerLog.DEBUG, "total bytes : " + totalDbl + " USAGE " + usage[0] + " TOTAL :"+ hdSize + " USED :" + usedDbl +" FREE :"+ freeDbl );
                                            percentage = (usage[0]*100)/(hdSize*(1024*1024*1024));

                                            if(percentage >100)
                                            {
                                                percentage = 100;
                                            }
                                            resProps.setProperty("DISK_USAGE", "" + percentage );
                                            diskUsageCalculated = true;
                                        } 
                                    }
                                    else
                                    {
                                        resProps.setProperty("DISK_USAGE", "NODATA" );
                                    }
                                    if(usage[1] > 0)
                                    { 
                                        long ramSizeInMB = -1; ;//= (long)obj.getRamSize();
                                        if(ramSizeInMB <= 0)
                                        {
                                            //OpManagerLogger.printLog(OpManagerLog.DEBUG, "Ram disk usage available. But total ram size not in OpManager Obj for "+obj.getName());
                                            if(hrInfo == null)
                                            {
                                                hrInfo = new HostDetail();
                                                hrInfo.getHostDetail(obj);
                                                opmObjChanged = true;
                                            }
                                            ramSizeInMB = hrInfo.getRAMSize();
                                            //OpManagerLogger.printLog(OpManagerLog.DEBUG, "RAM size get from the device is "+ramSizeInMB);
                                            //obj.setRamSize((int)ramSizeInMB);
                                        }

                                        long ramSize = ramSizeInMB*1024*1024;
                                        long percentage = 0;
                                        /*if(ramSize >0)
                                        {
                                            percentage = (usage[1]*100)/ramSize;

                                            if(percentage > 100)
                                            {
                                                percentage = 100;
                                            }
                                            resProps.setProperty("RAM_USAGE", ""+percentage );
                                            ramUsageCalculated = true;
                                        }*/ 
                                        if(ramSize >0)
                                        {
                                            //percentage = (usage[1]*100)/ramSize;
						/** ############## **/
						String OSName=target.snmpGet(new SnmpOID(".1.3.6.1.2.1.1.1.0")).toString().trim();
						System.err.println("OSName is::"+OSName);
						if(OSName.indexOf("inux") != -1)
						{
							try{
								target.setObjectIDList(null);
								SnmpOID[] oid = new SnmpOID[4];
								oid[0]	=  new SnmpOID(".1.3.6.1.4.1.2021.4.5.0"); //Total
								oid[1]	=  new SnmpOID(".1.3.6.1.4.1.2021.4.6.0"); //free Memory
								oid[2]	=  new SnmpOID(".1.3.6.1.4.1.2021.4.14.0"); //Buffer
								oid[3]	=  new SnmpOID(".1.3.6.1.4.1.2021.4.15.0"); // Cached
								target.setSnmpOIDList(oid);
								String[] test = target.snmpGetList();
								if(test != null)
								{
									try
									{
										long Total = Long.parseLong(test[0]);
										long reallyAvail = Long.parseLong(test[1]);
										long buffer=Long.parseLong(test[2]);
										long cached=Long.parseLong(test[3]);
										long freeMemory = (Total-reallyAvail-buffer-cached);
										percentage = freeMemory*100/Total;
										/***/
										System.err.println("Total, reallyAvail, buffer, cached, freeMemory is:"+Total+", "+reallyAvail+", "+buffer+", "+cached+", "+freeMemory);
										System.err.println("plain maths calculation::"+ freeMemory*100/Total);
										System.err.println("freeMemory percentage:"+ percentage);
										/***/
										resProps.setProperty("RAM_USAGE", Long.toString(percentage));
									}
									catch(NumberFormatException e)
									{
									// TODO. handle percentage here	
									}

								}
							}
							catch(Exception e)
							{
								e.printStackTrace();
									// TODO. handle percentage here	
							}
						}
						else{
							/*try
							{
								target.setObjectIDList(null);
								SnmpOID[] oid = new SnmpOID[2];
								oid[0] = new SnmpOID(".1.3.6.1.4.1.9.9.48.1.1.1.5.1");  //No Internationalization
								oid[1] = new SnmpOID(".1.3.6.1.4.1.9.9.48.1.1.1.6.1");  //No Internationalization
								target.setSnmpOIDList(oid);
								String[] test = target.snmpGetList();
								if(test != null)
								{
									try
									{
										long used = Long.parseLong(test[0]);
										long free = Long.parseLong(test[1]);
										percentage = (used*100)/(used+free);
										resProps.setProperty("RAM_USAGE", Long.toString(percentage));
										System.err.println("used, free::"+used+", "+free);
									}
									catch(NumberFormatException e)
									{
									// TODO. handle percentage here	
									}
								}
							}
							catch(Exception e)
							{
								e.printStackTrace();
								//TODO: handle percentage here
							}*/
							percentage = (usage[1]*100)/ramSize;
							System.err.println("percentage in non-Linux Memory is::"+percentage);
						}
						/** ############## **/

                                            if(percentage > 100)
                                            {
                                                percentage = 100;
                                            }
                                            resProps.setProperty("RAM_USAGE", ""+percentage );
                                            ramUsageCalculated = true;
                                        } 
                                    }
                                    else
                                    {
                                        resProps.setProperty("RAM_USAGE", "NODATA" );
                                    }

                                    long cpuUsage = hrinfo.getCpuUsage();
                                    if(cpuUsage>=0)
                                    {
                                        resProps.setProperty("CPU_USAGE", ""+hrinfo.getCpuUsage() );
                                        cpuUsageCalculated = true;
                                    }
                                }
                                if(opmObjChanged)
                                {
                                    topoAPI.updateObject(obj, false, false);
                                }
                                /*
                                  IF-Table data is being fetched from database instead of direct query.
                                  IF direct query is required the below code can be reused.
                                 */
                               
                                String className = reqProps.getProperty("classname"); // No Internationalization
                                if(className.equals("SwitchObject")) { // No Internationalization
                                
                                SnmpTarget snmpTarget1 = new SnmpTarget();
                                snmpTarget1.setTargetHost(tobj.getIpAddress());
                                snmpTarget1.setTargetPort(tobj.getSnmpport());
                                snmpTarget1.setCommunity(tobj.getCommunity());
				snmpTarget1.setSnmpVersion(ver);
                                if(ver==3)
                                {
                                   snmpTarget1.setPrincipal(tobj.getUserName());
                                   snmpTarget1.setContextName(tobj.getContextName());
                                }
                                snmpTarget1.setTimeout(2);
                                //oids[] = IfIndex, IfDescr, IfSpeed, IfOperStatus, Mac Address
                                String oids[] ={"2.2.1.1","2.2.1.2","2.2.1.5","2.2.1.8","2.2.1.6"};  //No Internationalization
                                snmpTarget1.setObjectIDList(oids); 
                                Vector dataVector = new Vector();
                                String result[][] = snmpTarget1.snmpGetAllList();
                                if(result != null)
                                {
                                for (int i = 0; i < result.length; i++)
                                {
                                     Vector row = new Vector();
                                    for (int j=0; j < result[i].length; j++)
                                    {
                                        String cellValue = result[i][j];
                                                                                    
                                        if ( oids[j].equals("2.2.1.5") ) // result == 9 & 15 ) // No Internationalization 
                                        {
                                            if ( cellValue != null)
                                            {
                                                double result1 = roundOff( cellValue, true );
                                                cellValue = "" + result1 ; // No Internationalization
                                            }
                                            else
                                            {
                                                cellValue = ""; //No Internationalization
                                            }
                                        }

                                        if ( oids[j].equals("2.2.1.8")) // No Internationalization
                                        {
                                            if ( cellValue.trim().equals("up(1)") ) //No Internationalization
                                            {
                                                cellValue ="Up" ; //No Internationalization
                                            }
                                            if ( cellValue.trim().equals("down(2)") ) //No Internationalization
                                            {
                                                cellValue ="Down" ; //No Internationalization
                                            }
                                            if ( cellValue.trim().equals("testing(3)") ) //No Internationalization
                                            {
                                                cellValue = "Testing" ; //No Internationalization
                                            }
                                        }
                                        if(cellValue == null && cellValue.equals("")) //No Internationalization
                                        {
                                            cellValue  = "N/A";  //No Internationalization
                                        }
                                        row.addElement( cellValue );
                                    }
                                    dataVector.addElement( row );
                                }
                                }
                                else {
                                    resProps.put("IF_DATA", new Vector() );
                                }
                                snmpTarget1.releaseResources();
                               
                                
                                /*SnmpTable snmpTable = new SnmpTable();
                                snmpTable.setTargetHost(tobj.getIpAddress());
                                snmpTable.setTargetPort(tobj.getSnmpport());
                                snmpTable.setCommunity(tobj.getCommunity());
                                snmpTable.setTimeout(1);
                                snmpTable.setTableOID(".1.3.6.1.2.1.2.2"); //No Internationalization

                                Vector dataVector = new Vector();
                                int[] indices = { 0, 1, 4, 7, 5 }; // IfIndex, IfDescr, IfSpeed, IfOperStatus, Mac Address

                                for ( int i=0; i < indices.length; i++)
                                {
                                    String[] colValues = snmpTable.getColumn( indices[i] ) ;
                                    if ( colValues != null )
                                    {
                                        for ( int k=0; k < colValues.length; k++ )
                                        {
                                            String cellValue = colValues[ k ];

                                            //OpManagerLogger.printLog(OpManagerLog.DEBUG, " Before Processing : " + cellValue + " index : " + indices[i] + " Name : " + snmpTable.getColumnName( indices[i] )  ) ;
                                            if ( indices[i] == 4 ) // || indices[i] == 9 || indices[i] == 15 ) // IfInOctets & IfOutOctets
                                            {
                                                double result = roundOff( cellValue, true );
                                                cellValue = "" + result ;
                                            }

                                            if ( indices[i] == 7 )
                                            {
                                                if ( cellValue.trim().equals("up(1)") ) //No Internationalization
                                                {
                                                    cellValue ="Up" ; //No Internationalization
                                                }
                                                if ( cellValue.trim().equals("down(2)") ) //No Internationalization
                                                {
                                                    cellValue ="Down" ; //No Internationalization
                                                }
                                                if ( cellValue.trim().equals("testing(3)") ) //No Internationalization
                                                {
                                                    cellValue = "Testing" ; //No Internationalization
                                                }
                                            }
                                            //OpManagerLogger.printLog(OpManagerLog.DEBUG, " After Processing : " + cellValue ) ;
                                            Vector rowVect = null;
                                            if ( k < dataVector.size() )
                                            {
                                                rowVect = ( Vector ) dataVector.elementAt( k ) ;
                                            }
                                            if ( rowVect == null )
                                            {
                                                Vector row = new Vector();
                                                if(i > 0)
                                                {
                                                    for(int myI=i; myI>0; myI--)
                                                    {
                                                        row.addElement("N/A"); //No Internationalization
                                                    }
                                                }
                                                row.addElement( cellValue );
                                                dataVector.addElement( row );
                                            }
                                            else
                                            {
                                                int sizeHere = rowVect.size();
                                                if(i > sizeHere)
                                                {
                                                    for(int myI=i; myI>sizeHere; myI--)
                                                    {
                                                        rowVect.addElement("N/A"); //No Internationalization
                                                    }
                                                }
                                                rowVect.addElement( cellValue );
                                            }
                                        }
                                    }
                                }
                                snmpTable.releaseResources();*/
                                //OpManagerLogger.printLog(OpManagerLog.DEBUG, " After populating values : IFTABLE : " + dataVector );
                                resProps.put("IF_DATA", dataVector );
                            }
                            }
                            else
                            {
                                //resProps.put("IF_DATA", new Vector() );
                                resProps.setProperty("CPU_USAGE", "NODATA" );
                                resProps.setProperty("DISK_USAGE", "NODATA" );
                                resProps.setProperty("RAM_USAGE", "NODATA" );
                            }
                        }
                        
                        }
                        /*
            if( (obj instanceof SnmpNode) && (!snmpAlive || (isDeviceQuery != null && !isDeviceQuery.equals("true"))))
            {
                resProps.put("IF_DATA", getIfDataFromDB(obj));
            }
                        */
            if((!isCiscoRouter) && (!diskUsageCalculated || !ramUsageCalculated || !cpuUsageCalculated))
            {/*
                HRDetails hrdetails = HostResourceAPI.getInstance().getObject(obj.getName(), obj.getType());
                if(hrdetails != null)
                {
                if(!diskUsageCalculated)
                {
                    Properties diskUsageProps = HostResourceAPI.getInstance().getResultProperties(hrdetails, "Disk Space in MB", obj.getType()); //No Internationalization
                    //System.err.println("diskUsageProps "+diskUsageProps);
                    if(diskUsageProps != null)
                    {
                    double freeBytes = roundOff(Double.parseDouble(diskUsageProps.getProperty("FreeSpace"))/(1024));
                    //double usedBytes = Double.parseDouble(diskUsageProps.getProperty("UsedSpace"))/(1024);
                    double totalBytes = roundOff(Double.parseDouble(diskUsageProps.getProperty("TotalSpace"))/(1024));
                    double usedBytes = roundOff(totalBytes-freeBytes);
                    resProps.setProperty("FREE_DISK", "" + freeBytes);
                    resProps.setProperty("USED_DISK", "" + usedBytes);
                    resProps.setProperty("TOTAL_DISK", "" + totalBytes);
                    resProps.setProperty("DISK_USAGE", ""+ ((int)(roundOff((usedBytes/totalBytes)) * 100)));
                    }
                }

                if(!ramUsageCalculated)
                {
                    Properties ramUsageProps = HostResourceAPI.getInstance().getResultProperties(hrdetails, "Memory Utilization", obj.getType()); //No Internationalization
                    if(ramUsageProps != null)
                    {
                    String memUsage = ramUsageProps.getProperty("PhysicalMemUtilization");
                    if(memUsage == null)
                    {
                        memUsage = ramUsageProps.getProperty("SwapMemUtilization");
                    }

                    if(memUsage != null)
                    {
                        resProps.setProperty("RAM_USAGE", ""+Integer.parseInt(memUsage));
                    }
                    else
                    {
                        resProps.setProperty("RAM_USAGE", "NODATA" );
                    }
                    }
                    else
                    {
                    resProps.setProperty("RAM_USAGE", "NODATA" );
                    }
                }

                if(!cpuUsageCalculated)
                {
                    Properties cpuUsageProps = HostResourceAPI.getInstance().getResultProperties(hrdetails, "CPU Utilization", obj.getType()); //No Internationalization
                    if(cpuUsageProps != null)
                    {
                    String cpuStr = cpuUsageProps.getProperty("CPUUtilization");
                    if(cpuStr != null)
                    {
                        int cpu = Integer.parseInt(cpuStr);
                        if(cpu > 100)
                        {
                        cpu = 100;
                        }
                        resProps.setProperty("CPU_USAGE", ""+cpu);
                    }
                    else if(cpuUsageProps.size() > 0)
                    {
                        int cpu = 0;
                        for(Enumeration en = cpuUsageProps.elements(); en.hasMoreElements();)
                        {
                        cpu += Integer.parseInt((String)en.nextElement());
                        }

                        if(cpu > 100)
                        {
                        cpu = 100;
                        }
                        resProps.setProperty("CPU_USAGE", ""+cpu);
                    }
                    else
                    {
                        resProps.setProperty("CPU_USAGE", "NODATA" );
                    }
                    }
                }
                }*/
            }

                    }
                    else
                    {
                        resProps.setProperty("ERROR", "Device not yet discovered. Use the Add Device option to discover the device" );
                    }
                    /*
                    Properties lastAlarmProps = getLastAlarm( objName );
                    if ( lastAlarmProps != null )
                    {
                        resProps.setProperty("ALARM_SEVERITY", lastAlarmProps.getProperty("ALARM_SEVERITY") );
                        resProps.setProperty("ALARM_MESSAGE", lastAlarmProps.getProperty("ALARM_MESSAGE") );
                    }
                    else
                    {
                        resProps.setProperty("ALARM_SEVERITY", "0");
                        resProps.setProperty("ALARM_MESSAGE", "No Alarms for this Device" );
                    }
                    */
                }catch(Exception x )
                {
                    //printLogMessage( OpManagerLog.MAJOR, "Exception while getting snapshot data " + x );
                    x.printStackTrace();
                    //OpManagerServerUtil.printException( x );
                    x.printStackTrace();
                }
            }
        }
         else if ( requestID == GET_SNMP_TABLE )
        {
            try
            {
                if ( reqProps != null )
                {
                    String hostName = reqProps.getProperty("objName");
                    String columns = reqProps.getProperty("columns");
                    boolean isSNMP = false;
                    int port = 161;
                    String commString = "public"; //No Internationalization

                    String isSNMPStr = reqProps.getProperty("isSNMP");
                    if(isSNMPStr == null)
                    {
                        TopoObject mo = (TopoObject) topoAPI.getByName( hostName );
                        isSNMP = mo.getIsSNMP();
                        port = mo.getSnmpport();
                        commString = mo.getCommunity();
                    }
                    else
                    {
                        isSNMP = isSNMPStr.equals("true"); //No Internationalization
                        port = Integer.parseInt(reqProps.getProperty("SnmpPort"));
                        commString = reqProps.getProperty("CommunityString");
                    }
                    String tableName = reqProps.getProperty("tableName","");
                    //If the table name is not specified then the default handling of SNMP table is sufficient for processing the data.

                    if ( isSNMP )
                    {
                        if ( tableName.equals("RoutingTable") ) //No Internationalization
                        {
                            /* Special handling is required for the routing table as the ifIndex needs to be changed to the ifDesc */
                            Vector tableData = getRoutingTable( hostName, columns );
                            //OpManagerLogger.printLog(OpManagerLog.DEBUG, "Routing table ::  DATA :: " + tableData );
                            resProps.put("SNMPData", tableData );

                        }
                        else if ( tableName.equals("IpAddressTable")) //No Internationalization
                        {
                            /* Special handling is required for the IP Address table as the ifIndex needs to be changed to the ifDesc */
                            Vector tableData = getRoutingTable( hostName, columns );
                            //OpManagerLogger.printLog(OpManagerLog.DEBUG, "IpAddress table ::  DATA :: " + tableData );
                            resProps.put("SNMPData", tableData );
                        }

                        else
                        {
                            Vector tableData = getSNMPTable( hostName, port, commString, columns );
                            if(tableData != null)
                            {
                                //OpManagerLogger.printLog(OpManagerLog.DEBUG, " SNMP DATA: " + tableData );
                                resProps.put("SNMPData", tableData );
                            }
                            else
                            {
                                resProps.put("SNMPData",new Vector());
                            }
                        }
                    }
                    else
                    {
                        resProps.put("SNMPData", new Vector());
                    }
                }
            }
            catch(Exception e)
            {
                //printLogMessage( OpManagerLog.MAJOR, "Exception while getting SNMP Table.. ", e );
            }
        }
        // Alex code - END
        return resProps;
    }
    // Alex Methods - BEGIN

    
    public void getDetailsFromDB(String aName, Properties resProps)
    {
        String agentName = "";
        try {

        Vector agentVec = pollAPI.getPollsForAgent(aName);
        agentVec = setPolledDataForDeviceType(agentVec);
        if(agentVec != null)
        {
            for(Enumeration en = agentVec.elements(); en.hasMoreElements(); )
            {
                agentName = (String)en.nextElement();
                //System.out.println("The agent name is "+agentName);
                PolledData pData = pollAPI.getPolledData(agentName);
                long lastCTime = pData.getLastTimeValue();
                if(lastCTime == 0)
                {
                    continue;
                }
                //System.out.println("The last data collected time is "+lastCTime);
                long dcTime=System.currentTimeMillis();


                Vector vecInstances = null;
                if (pData.getIsMultiplePolledData())
                {
                    Vector vecAllInstances = getAllInstances(pData);
                    if (vecAllInstances != null && vecAllInstances.size() == 1)
                    {
                        vecInstances = vecAllInstances;
                    }
                }

                Map cdMap =  getCollectedData(pData, vecInstances,lastCTime  ,dcTime );
                //String moName = pData.getAgent();
                String pdName = pData.getName();
                Set set = cdMap.keySet();
                Iterator iterator = set.iterator();
                while(iterator.hasNext())
                {
                    String cdKey = (String) iterator.next();
                    CollectedData cData = (CollectedData) cdMap.get(cdKey);
                    if (cData == null)
                    {
                        if(pdName.indexOf("CPUUtilization") > 0) //No Internationalization
                        {
                            resProps.setProperty("CPU_USAGE", "NODATA" ); //No Internationalization
                        }
                        else if(pdName.indexOf("MemoryUtilization") > 0)
                        {
                            resProps.setProperty("RAM_USAGE", "NODATA" ); //No Internationalization
                        }    
                        else
                        {
                            resProps.setProperty(pdName, "NODATA" ); //No Internationalization
                        }
                        //continue;
                    }
                    else
                    {
                        Long[] times = cData.getTimes();
                        //Long[] values = (Long[])cData.getValues();
                        Long[] values = null;
                        Double[] dvalue = null;
                        Object[] obj = cData.getValues();
                        if(obj instanceof Long[])
                        {
                        	values = (Long[])obj;
                        }
                        else if(obj instanceof Double[])
                        {
                        	dvalue = (Double[])obj;
                        } 
                        /*
                        for(int i = 0 ; i<times.length; i++) {
                        //System.out.println("The Time in long value "+times[i]);
                        //System.out.println("The Value in long value "+values[i]);
                        }
                        */
                        Long maxval = new Long(100);
                        if(pdName.indexOf("CPUUtilization") != -1) //No Internationalization
                        {
                        	if(values != null)
                        	{
                            if(values[0].compareTo( maxval) > 0)
                            {
                                values[0] = maxval; 
                            }
                            resProps.setProperty("CPU_USAGE", ""+values[0]); //No Internationalization
                        	}
                        	else if(dvalue != null)
                        	{
                        		if(dvalue[0].compareTo( maxval.doubleValue()) > 0)
                                {
                                    dvalue[0] = maxval.doubleValue(); 
                                }
                                resProps.setProperty("CPU_USAGE", ""+dvalue[0]); //No Internationalization
                        	}
                        }
                        else if(pdName.indexOf("MemoryUtilization") != -1)
                        {
                        	if(values != null)
                        	{
                             if(values[0].compareTo( maxval) > 0)
                             {
                                 values[0] = maxval;
                             }
                            resProps.setProperty("RAM_USAGE", ""+ values[0]  ); //No Internationalization
                        	}
                        	else if(dvalue != null)
                        	{
                        		if(dvalue[0].compareTo( maxval.doubleValue()) > 0)
                                {
                                    dvalue[0] = maxval.doubleValue();
                                }
                               resProps.setProperty("RAM_USAGE", ""+ dvalue[0]  ); //No Internationalization
                        	}
                        }    
                        else
                        {
                        	if(values != null)
                        	{
                        	resProps.setProperty(pdName, ""+ values[0] ); //No Internationalization
                        	}
                        	else if(dvalue != null)
                        	{
                        		resProps.setProperty(pdName, ""+ dvalue[0] ); //No Internationalization
                        	}
                        }
                    }
                }
            }
        }
        }catch(Exception e)
            {
                e.printStackTrace();
            }
    }

       public Map getCollectedData(PolledData pData,
						Vector mInstances,
						 long startDateInMillis,
						 long endDateInMillis)
						 throws Exception
    {
        //String moName = pData.getAgent();
        //String pdName = pData.getName();
	   boolean isMultiplePD = pData.getIsMultiplePolledData();
       Map hMap = new HashMap();
	   if (isMultiplePD)
	   {
		Vector mpdInstances = mInstances;
		if (mpdInstances == null || mpdInstances.size() == 0)
		{
			mpdInstances = getAllInstances(pData);
		}
        
		Enumeration enumerate = mpdInstances.elements();
		while (enumerate.hasMoreElements())
		{
            
            //System.out.println("The key is "+pData.getKey());
			String inst = (String) enumerate.nextElement();
            //System.out.println("the instance name is "+inst);
            CollectedData cdata = pollAPI.getCollectedData(inst,
                                                           pData.getKey(),
                                                           startDateInMillis,
                                                           endDateInMillis);
            if(cdata !=null)
            {
                hMap.put(inst,cdata);
                //return null;
            }
           
		}	
	   }
       else
       {
           //System.out.println("The key is "+pData.getKey());
           CollectedData cdata = pollAPI.getCollectedValues(pData.getKey(),
                                                            startDateInMillis,
                                                            endDateInMillis);
           if(cdata !=null)
           {
               hMap.put(pData.getName(),cdata );
               //return null;
           }
       }
       //System.out.println("Returning HASHMAP "+hMap);
	   return hMap;

    }

    public Vector setPolledDataForDeviceType(Vector agentVec )
    {
        Vector sPData = new Vector();
        for(Enumeration en= agentVec.elements(); en.hasMoreElements(); )
        {
            String pName = (String)en.nextElement();
            if( pName.indexOf("CPUUtilization") != -1 || pName.indexOf("MemoryUtilization") != -1) //No Internationalization
            {
                //System.out.println("The polledData to be removed is @@@@@@@@ "+pName);
                sPData.add(pName);
            }
        }
        return sPData;
    }

    public Vector getAllInstances(PolledData pData) throws Exception
	{
		if (pData.getIsMultiplePolledData())
		{
                	MultiplePolledData mpd = new MultiplePolledData();
	                mpd.setId(pData.getId());
        	        mpd.setProperties(pData.getProperties());
			return pollAPI.getInstances(mpd);
		}
		return null;
	}

    public Vector getSNMPTable( String host, int port, String commString, String columns ) throws Exception
    {
        Vector columnsToFetch = new Vector();
        Vector displayColumns = new Vector();
        if ( columns != null )
        {
            StringTokenizer tokenizer = new StringTokenizer( columns,";"); //No Internationalization
            while( tokenizer.hasMoreTokens() )
            {
                String colm = tokenizer.nextToken();
                if ( colm != null )
                {
                    int index = colm.indexOf("="); //No Internationalization
                    if ( index != -1 )
                    {
                        String fetchCol = colm.substring(0, index);
                        String toDisp = colm.substring( ++index );
                        //OpManagerLogger.printLog(OpManagerLog.DEBUG, " FETCH : " + fetchCol + " DISP : " + toDisp );
                        columnsToFetch.addElement( fetchCol );
                        displayColumns.addElement( toDisp );
                    }
                }
            }
        }
        //OpManagerLogger.printLog(OpManagerLog.DEBUG, "FETCH COLS:  " + columnsToFetch) ;
        //OpManagerLogger.printLog(OpManagerLog.DEBUG, "DISP COLS : " + displayColumns );

        //String[] strColumns = { "hrSWInstalledName", "hrSWInstalledType", "hrSWInstalledDate" };
        int numCols = columnsToFetch.size();
        //OpManagerLogger.printLog(OpManagerLog.DEBUG, " Size to Fetch: " + numCols );
        String colArray[] = new String[numCols] ;

        int j=0;
        for ( Enumeration enums = columnsToFetch.elements(); enums.hasMoreElements(); )
        {
            String colm = (String) enums.nextElement();
            colArray[j] = new String();
            colArray[j] = colm;
            //OpManagerLogger.printLog(OpManagerLog.DEBUG, " Column ADDDDDD : "  + colm );
            j++;
        }

        SnmpTarget snmpTarget = new SnmpTarget();
        snmpTarget.setTargetHost(host);
        snmpTarget.setTargetPort(port);
        snmpTarget.setCommunity(commString);
        snmpTarget.setObjectIDList(colArray);
        snmpTarget.setTimeout(5);
        String tableValues[][] = snmpTarget.snmpGetAllList();

        Vector dataVector = new Vector();
        int colCount = colArray.length ;
        dataVector.addElement( displayColumns );
        if(tableValues == null)
        {
            return dataVector;
        }
        int rowCount = tableValues.length ;
        //OpManagerLogger.printLog(OpManagerLog.DEBUG, "Column count :: "+colCount);
        //OpManagerLogger.printLog(OpManagerLog.DEBUG, "Row count :: "+rowCount);

        for ( int r=0; r<rowCount; r++)
        {
            Vector row = new Vector();
            //OpManagerLogger.printLog(OpManagerLog.DEBUG, "-----Row-----"+r);
            for ( int c=0; c<colCount; c++)
            {
                Object objVal = tableValues[r][c];
                //OpManagerLogger.printLog(OpManagerLog.DEBUG, "Values --->"+objVal);
                row.addElement( objVal );
            }
            dataVector.addElement( row );
        }
        snmpTarget.releaseResources();
        return dataVector;
    }

    /* This method is used to fetch the data for the Routing table from the ipRoutTable of the  RFC1213-MIB. The ifIndex has been replaced by the ifDesc from the interfaceTable */
    public Vector getRoutingTable(  String host, String columns )
    {
        Vector columnsToFetch = new Vector();
        Vector displayColumns = new Vector();
        if ( columns != null )
        {
            StringTokenizer tokenizer = new StringTokenizer( columns,";"); //No Internationalization
            while( tokenizer.hasMoreTokens() )
            {
                String colm = tokenizer.nextToken();
                if ( colm != null )
                {
                    int index = colm.indexOf("="); //No Internationalization
                    if ( index != -1 )
                    {
                        String fetchCol = colm.substring(0, index);
                        String toDisp = colm.substring( ++index );
                        //OpManagerLogger.printLog(OpManagerLog.DEBUG, " FETCH : " + fetchCol + " DISP : " + toDisp );
                        columnsToFetch.addElement( fetchCol );
                        displayColumns.addElement( toDisp );
                    }
                }
            }
        }
        //OpManagerLogger.printLog(OpManagerLog.DEBUG, "FETCH COLS:  " + columnsToFetch) ;
        //OpManagerLogger.printLog(OpManagerLog.DEBUG, "DISP COLS : " + displayColumns );

        int numCols = columnsToFetch.size();
        String colArray[] = new String[numCols] ;

        int j=0;
        for ( Enumeration enums = columnsToFetch.elements(); enums.hasMoreElements(); )
        {
            String colm = (String) enums.nextElement();
            colArray[j] = new String();
            colArray[j] = colm;
            //OpManagerLogger.printLog(OpManagerLog.DEBUG, " Column ADDDDDD : "  + colm );
            j++;
        }

        SnmpTarget snmpTarget = new SnmpTarget();
        snmpTarget.setTargetHost(host);
        snmpTarget.setObjectIDList(colArray);
        snmpTarget.setTimeout(5);
        String tableValues[][] = snmpTarget.snmpGetAllList();

        Vector dataVector = new Vector();
        int colCount = colArray.length;
        dataVector.addElement( displayColumns );

        //Fetch the ifDesc values corresponding to the ifIndex from the device
        //OpManagerLogger.printLog(OpManagerLog.DEBUG,"---------------------------------------");
        String infArray[] = { "ifIndex","ifDescr" }; //No Internationalization
        SnmpTarget target = new SnmpTarget();
        target.setTargetHost(host);
        target.setObjectIDList(infArray);
        String interfaceValues[][] = target.snmpGetAllList();

        Hashtable infIndexDetails = new Hashtable();
        int count = interfaceValues.length;
        for (int r=0; r<count; r++)
        {
            if ( interfaceValues[r][0]  != null && interfaceValues[r][1] != null )
            {
                infIndexDetails.put(interfaceValues[r][0],interfaceValues[r][1]);
            }
        }
        //OpManagerLogger.printLog(OpManagerLog.DEBUG,"INTERFACE DETAILS --->"+infIndexDetails);
        //OpManagerLogger.printLog(OpManagerLog.DEBUG,"---------------------------------------");

        int rowCount = tableValues.length;
        for ( int r=0; r<rowCount; r++)
        {
            Vector row = new Vector();
            for ( int c=0; c<colCount; c++)
            {
                Object objVal = null;
                if ( c == 1 ) //Router interface index needs to be replaced by router interface discription
                {
                    //OpManagerLogger.printLog(OpManagerLog.DEBUG,"Column count ::: "+colCount + "Value ::: "+tableValues[r][c]);
                    objVal = infIndexDetails.get(tableValues[r][c]);
                    if ( objVal == null )
                    {
                        objVal = "Unknown"; //No Internationalization
                    }
                }
                else
                {
                    objVal = tableValues[r][c];
                }
                row.addElement( objVal );
            }
            dataVector.addElement( row );
        }
        snmpTarget.releaseResources();
        target.releaseResources();
        return dataVector;
    }

    public static Properties getLastAlarm( String source )
    {
        if ( alertAPI != null )
        {
            try
            {
                Properties matchingProperties = new Properties();
                matchingProperties.setProperty("source", source );
                Vector alarmsVector = alertAPI.getObjects( "Alert", matchingProperties ); //No Internationalization

                if ( alarmsVector != null && alarmsVector.size() > 0 )
                {
                    int severity = 0;
                    String message = ""; //No Internationalization

                    Alert latestAlarm = (Alert) alarmsVector.elementAt( 0 );
                    long modTime = latestAlarm.getModTime();
                    for(Enumeration alms = alarmsVector.elements(); alms.hasMoreElements() ; )
                    {
                        Alert alert = (Alert) alms.nextElement();
                        long nextModTime = alert.getModTime();
                        if ( nextModTime > modTime )
                        {
                            latestAlarm = alert;
                            modTime = nextModTime;
                        }
                    }

                    Properties almProps = new Properties();
                    almProps.setProperty("ALARM_SEVERITY", "" + latestAlarm.getSeverity() );
                    almProps.setProperty("ALARM_MESSAGE", latestAlarm.getMessage() );
                    return almProps;
                }
            }
            catch(Exception almEx )
            {
                //OpManagerLogger.printLog(OpManagerLog.DEBUG, "Exception while getting Last alarm for the device : " + source );
                almEx.printStackTrace();
            }
        }
        return null;
    }
   
    private Vector getIfDataFromDB(ManagedObject devObj)
    {
    Vector toReturn = new Vector();
    ResultSet rs = null;
    Statement stmt = null;
    boolean notSwitch = !(devObj instanceof SwitchObject);
    try
    {
        String query = getQuery(devObj);
        stmt = ConnectionPool.getInstance().getConnection().createStatement();
        rs = stmt.executeQuery(query);
        while(rs.next())
        {
        Vector rowData = new Vector();
        rowData.addElement(rs.getString(1));
        rowData.addElement(rs.getString(2));
        rowData.addElement(rs.getString(3));
        String status = (rs.getInt(4) == clearSeverity) ? "Up" : "Down"; //No Internationalization
        rowData.addElement(status);
        if(notSwitch)
        {
            rowData.addElement(rs.getString(5));
        }
        else
        {
            rowData.addElement("N/A"); //No Internationalization
        }

        toReturn.addElement(rowData);
        }
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }
    finally
    {
        try
        {
        if(rs != null)
        {
            rs.close();
        }
        }
        catch(Exception e){}
        try
        {
        if(stmt != null)
        {
            stmt.close();
        }
        }
        catch(Exception e){}
    }

    return toReturn;
    }

    private String getQuery(ManagedObject devObj)
    {
    if(devObj instanceof SwitchObject)
    {
        return "select PortObject.PORTIFINDEX,PortObject.PORTIFDESCR,PortObject.PORTIFSPEED/(1000*1000),ManagedObject.STATUS from ManagedObject,PortObject where ManagedObject.NAME=PortObject.NAME AND ManagedObject.NAME like '"+devObj.getName()+"_Port%' order by PortObject.PORTIFINDEX"; //No Internationalization
    }

    return "select SnmpInterface.IFINDEX,SnmpInterface.IFDESCR,SnmpInterface.IFSPEED/(1000*1000),ManagedObject.STATUS,SnmpInterface.PHYSADDR from ManagedObject,IpAddress,SnmpInterface where ManagedObject.NAME=IpAddress.NAME And IpAddress.NAME=SnmpInterface.NAME AND IpAddress.PARENTNODE='"+devObj.getName()+"' order by SnmpInterface.IFINDEX"; //No Internationalization
    }



    public  double roundOff( String dblStr, boolean byteVal )
    {
        double dblValue = new Double( dblStr ).doubleValue() ;
        return roundOff( dblValue, byteVal );
    }

    public  double roundOff( double dblValue )
    {
        //double val = (double) ( dblValue / 1024 /1024 ) ;
        int temp = (int)((dblValue * 100) + 0.5);
        double result = temp / 100.0;
        return result;
    }

    public  double roundOff( double dblValue, boolean byteVal )
    {
        double val = dblValue;
        if ( byteVal )
        {
            val = (double) ( dblValue / 1024 /1024 ) ;
        }
        int temp = (int)((val * 100) + 0.5);
        double result = temp / 100.0;
        return result;
    }


    
}

