/**
 * $Id: TL1CustomViewsMgr.java,v 1.2 2008/07/14 11:22:02 swaminathap Exp $
 */

package com.adventnet.nms.topodb.tl1;

import com.adventnet.nms.db.util.DBXmlUpdate;
import com.adventnet.nms.db.util.DBXmlUtility;
import com.adventnet.nms.fe.alert.AlertFE;
import com.adventnet.nms.fe.alert.AlertSessionBean;
import com.adventnet.nms.fe.common.CustomViewException;
import com.adventnet.nms.fe.common.ModuleNotInitializedException;
import com.adventnet.nms.fe.common.TableColumn;
import com.adventnet.nms.fe.event.EventFE;
import com.adventnet.nms.fe.event.EventSessionBean;
import com.adventnet.nms.fe.perf.PollFE;
import com.adventnet.nms.fe.perf.PerfSessionBean;
import com.adventnet.nms.fe.topo.TopoSessionBean;
import com.adventnet.nms.mapdb.MapAPI;
import com.adventnet.nms.store.NmsStorageException;
import com.adventnet.nms.topodb.IpAddress;
import com.adventnet.nms.topodb.ManagedObject;
import com.adventnet.nms.topodb.Node;
import com.adventnet.nms.topodb.TopoAPI;
import com.adventnet.nms.topodb.TopoObject;
import com.adventnet.nms.fe.topo.TopoFE;
import com.adventnet.nms.topodb.TopoActionListener;
import com.adventnet.nms.topodb.TopoNotificationEvent;
import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.util.RunProcessInterface;
import com.adventnet.nms.util.NmsLogMgr;
import com.adventnet.management.log.Log;
import java.rmi.RemoteException;
import java.util.Properties;
import java.util.Vector;


import com.adventnet.management.transaction.UserTransactionException; 


public class TL1CustomViewsMgr implements RunProcessInterface, TopoActionListener
{
    private boolean initialized = false;


    private Boolean isAdded = new Boolean((Boolean.FALSE).booleanValue());

    private int tl1NodeCounter = 0;
    private int tl1InterfaceCounter = 0;

    //----------------------------------------------------------------------------

    public TL1CustomViewsMgr()
    { }

    //----------------------------------------------------------------------------

    public void update(TopoNotificationEvent evt) throws RemoteException
    {
		String type=evt.getUpdateType();

       if (type.equals("Manage") || type.equals("Status") || type.equals("Discover") || type.equals("Property Update")) //no i18n
       
	   {
		   return;
	   }

		ManagedObject obj=evt.getNewManagedObject();
        try 
		{
	    if (type.equals("Added") || type.equals("Deleted"))//no i18n

	    {

		synchronized(isAdded)
                {
        
                    if (type.equals("Added")) //no i18n

                    {
                      
						if ( !(obj instanceof TL1Node) && !(obj instanceof TL1Interface) )
						{
						return;
						}
						
						if (obj instanceof TL1Node)
                            tl1NodeCounter++;        
                        else if (obj instanceof TL1Interface)
                            tl1InterfaceCounter++;        
                        
                        if ( ! isAdded.booleanValue()) 
                        { 
                            //To Populate Data in Database from Tree.xml for new user
                            try 
                            {
                                DBXmlUpdate dbxmlupdate = new DBXmlUpdate(NmsUtil.relapi.getConnection());
                                if (dbxmlupdate.updateDB("root","Tree.xml")) //no i18n

                                {
                                    NmsLogMgr.MISCUSER.log (NmsUtil.GetString("TL1: Custom View : Default Tree Nodes copied to the database from users/root/Tree.xml successfully"), Log.INTERMEDIATE_DETAIL); //no i18n
                      
                                } 
                                else 
                                {
                                    NmsLogMgr.MISCUSER.log (NmsUtil.GetString("TL1: Custom View : Default Tree Nodes of users/root/Tree.xml ALREADY present in the database"), Log.INTERMEDIATE_DETAIL);//no i18n

                                }
                            } 
                            catch (NmsStorageException nsex) 
                            {
                                NmsLogMgr.MISCERR.fail(NmsUtil.GetString("TL1: Custom View : Error copying Default Tree Nodes of users/root/Tree.xml to the database"), nsex);//no i18n

                            }

                            addTL1Views();
                            isAdded = new Boolean(Boolean.TRUE.booleanValue());
                            
                        }
                    } 
                    else if (type.equals("Deleted")) //no i18n

                    {
						obj=evt.getOldManagedObject();
						if ( !(obj instanceof TL1Node) && !(obj instanceof TL1Interface) )
						{
							return;
						}

                        if (obj instanceof TL1Node)
                            tl1NodeCounter--;        
                        else if (obj instanceof TL1Interface)
                            tl1InterfaceCounter--;
                        
                        if ((tl1NodeCounter == 0) && (tl1InterfaceCounter == 0)) 
                        {
                            removeTL1Views();
                            isAdded = new Boolean(Boolean.FALSE.booleanValue());
                        }
                    }
                }
	    }
	}
	catch(Exception ex)
	{
	    NmsLogMgr.MISCERR.fail(NmsUtil.GetString("TL1: Custom View : Exception during ManagedObject update of type " + type + " for " + obj), ex);//no i18n

	}
    }

    //----------------------------------------------------------------------------

    //----------------------------------------------------------------------------


    private void addTL1Views() throws NmsStorageException, UserTransactionException



    {
        if (NmsUtil.relapi == null) return;  // Serialized Mode not allowed

        // Add TL1-Panels Node
        Properties panelProp = new Properties();
        panelProp.put("Client", "All");//no i18n

        panelProp.put("TREE-NAME", "TL1-Panels");//no i18n

        panelProp.put("ICON-FILE", "images/tl1.png");//no i18n

        panelProp.put("TARGET", "center");//no i18n

        panelProp.put("URL", "html/Center_Spread.html");//no i18n

        panelProp.put("PANEL-NAME", "com.adventnet.nms.tl1ui.TL1BaseScreen");//no i18n

        DBXmlUtility xmlutility = DBXmlUtility.getInstance(NmsUtil.relapi.getConnection());
        if (xmlutility != null) 
        {
            try 
            {
                xmlutility.addNode("TL1-Panels", "DEVICE", "root", "WebNMS-Panels", "Default", panelProp, null);//no i18n

            } 
            catch (NmsStorageException nsex) 
            {
                NmsLogMgr.MISCERR.fail(NmsUtil.GetString("TL1: Custom View : Error Adding TL1-Panels Tree Node"), nsex);//no i18n

            }
        } 
        else 
        {
            NmsLogMgr.MISCERR.log(NmsUtil.GetString("TL1: Custom View : Error Adding TL1-Panels Tree Node : Can't get DBXmlUtility handle"), Log.INTERMEDIATE_DETAIL);   //no i18n
         
        }

        // Add TL1-Map Custom Map
        try 
        {
            MapAPI mapi = (MapAPI) NmsUtil.getAPI("MapAPI");//no i18n


	    if(mapi != null)
	    {
            if (mapi.doesTheMapExist("TL1-Map.netmap")) return;//no i18n


            Properties mapProp = new Properties();
            mapProp.put("label", "TL1-Map");//no i18n

            mapProp.put("autoPlacement", String.valueOf(true));//no i18n

            mapProp.put("imageName", "world.png");//no i18n

            mapProp.put("treeIconFileName", "images/maps.png");//no i18n

            mapProp.put("parentNode" , "TL1-Panels");//no i18n

            mapProp.put("topology", "grid,star,ring,flow");//no i18n

            mapProp.put("currentTopology", "grid");//no i18n

            mapProp.put("mapSymbolRenderer", "com.adventnet.nms.mapui.MapSymbolRendererImpl");//no i18n

            mapProp.put("mapLinkRenderer", "com.adventnet.nms.mapui.MapLinkRendererImpl");//no i18n

            mapProp.put("menuName", "mapmenu.xml");//no i18n


            Properties filterProp = new Properties();
            // Wildcard *, comma separation, etc. are supported
            filterProp.put("classname", "TL1Node,AcmeMSUNode");//no i18n

            if ( ! mapi.addCustomMap("TL1-Map", mapProp, filterProp)) //no i18n

            {
                NmsLogMgr.MISCERR.log(NmsUtil.GetString("TL1: Custom View : Error Adding TL1-Map Custom Map for TL1 Devices"), Log.INTERMEDIATE_DETAIL);                //no i18n

            }

            // Add TL1-Topo-Map Custom Map
	    if (mapi.doesTheMapExist("TL1-Topo-Map.netmap")) return;//no i18n

            
            mapProp.remove("name");//no i18n

            mapProp.put("label","TL1-Topo-Map");//no i18n

            mapProp.put("parentNode","TL1-Panels");//no i18n

            
            //Properties filterProp1 = new Properties();
            
            if ( ! mapi.addMap("TL1-Topo-Map", mapProp)) //no i18n

            {
                NmsLogMgr.MISCERR.log(NmsUtil.GetString("TL1: Custom View : Error Adding TL1-Topo-Map Custom Map for TL1 Devices"), Log.INTERMEDIATE_DETAIL);                //no i18n

	    }
	    }
        } 

        catch(NmsStorageException nmse) 
        {
            throw nmse;
        } 
        catch(UserTransactionException ute ) 
        {
            throw ute;
        }

        catch (RemoteException rex) 
        {
            NmsLogMgr.MISCERR.fail(NmsUtil.GetString("TL1: Custom View : Error Adding TL1-Map Custom Map for TL1 Devices"), rex);            //no i18n

        }

        // Add TL1-Events Custom View
        TableColumn[] eventTable = new TableColumn[4];
        eventTable[0] = new TableColumn("severity","Class",55);//no i18n

        eventTable[1] = new TableColumn("source","Source",135);//no i18n

        eventTable[2] = new TableColumn("time","Date",155);//no i18n

        eventTable[3] = new TableColumn("text","Message",275);//no i18n

        Properties eventProp = new Properties();
        eventProp.put("Client", "All");//no i18n

        eventProp.put("DEVICE-REF", "TL1-Panels");//no i18n

        eventProp.put("parent", "TL1-Panels");//no i18n

        eventProp.put("ICON-FILE", "images/tl1event.png");//no i18n

        eventProp.put("SHOW-TREENODE-PROPERTIES", "true");//no i18n

        eventProp.put("TARGET", "center");//no i18n

        eventProp.put("TREE-POPUP-MENU", "Custom Views,frameoptions.xml");//no i18n

        eventProp.put("URL", "jsp/GetEvents.jsp");//no i18n

        eventProp.put("PANEL-NAME", "com.adventnet.nms.eventui.EventBrowser");//no i18n

        eventProp.put("MODULE-NAME", "Events");//no i18n

        eventProp.put("TABLE-POPUP-MENU", "View");//no i18n

        eventProp.put("MENU-FILE-NAME", "eventsmenu.xml");//no i18n

        Properties eventMatch = new Properties();
        eventMatch.put("agentProtocol","TL1");//no i18n

        try 
        {
            EventSessionBean evSession = EventFE.getEventSession();
            if (evSession != null) 
            {
                try 
                {
                    evSession.createCustomView("root","TL1-Events","TL1-Events","LEVEL-1",eventTable,eventProp, eventMatch);//no i18n

                } 
                catch (CustomViewException cvex) 
                {
                    NmsLogMgr.MISCERR.fail(NmsUtil.GetString("TL1: Custom View : Error Adding TL1-Events Custom View"),cvex);//no i18n

                } 
                catch (RemoteException rex) 
                {
                    NmsLogMgr.MISCERR.fail(NmsUtil.GetString("TL1: Custom View : Error Adding TL1 Events Custom View"), rex);//no i18n

                }
            } 
            else 
            {
                NmsLogMgr.MISCERR.log(NmsUtil.GetString("TL1: Custom View : Error Adding TL1-Events Custom View : Can't get EventSessionBean handle"), Log.INTERMEDIATE_DETAIL);                //no i18n

            }
        } 
        catch (ModuleNotInitializedException mniex) 
        {
            NmsLogMgr.MISCERR.fail(NmsUtil.GetString("TL1: Custom View : Error Adding TL1-Events Custom View : Can't get EventSessionBean handle"), mniex);            //no i18n

        }

        // Add TL1-Alerts Custom View
        TableColumn[] alertTable = new TableColumn[6];
        alertTable[0] = new TableColumn("severity","Class",55);//no i18n

        alertTable[1] = new TableColumn("entity","Failure Object",135);//no i18n

        alertTable[2] = new TableColumn("groupName","Alert Group",150);//no i18n

        alertTable[3] = new TableColumn("who","Owner",60);//no i18n

        alertTable[4] = new TableColumn("modTime","Date/Time",155);//no i18n

        alertTable[5] = new TableColumn("message","Alert Message",275);//no i18n

        Properties alertProp = new Properties();
        alertProp.put("Client", "All");//no i18n

        alertProp.put("DEVICE-REF", "TL1-Panels");//no i18n

        alertProp.put("parent", "TL1-Panels");//no i18n

        alertProp.put("ICON-FILE", "images/tl1alert.png");//no i18n

        alertProp.put("SHOW-TREENODE-PROPERTIES", "true");//no i18n

        alertProp.put("TARGET", "center");//no i18n

        alertProp.put("TREE-POPUP-MENU", "Custom Views,frameoptions.xml");//no i18n

        alertProp.put("URL", "jsp/GetAlerts.jsp");//no i18n

        alertProp.put("PANEL-NAME", "com.adventnet.nms.alertui.AlertApplet");//no i18n

        alertProp.put("MODULE-NAME", "Alerts");//no i18n

        alertProp.put("TABLE-POPUP-MENU", "View");//no i18n

        alertProp.put("MENU-FILE-NAME", "alertsmenu.xml");//no i18n

        Properties alertMatch = new Properties();
        alertMatch.put("agentProtocol","TL1");//no i18n

        try 
        {
            AlertSessionBean altSession = AlertFE.getAlertSession();
            if (altSession != null)
            { 
                try 
                {
                    altSession.createCustomView("root", "TL1-Alerts","TL1-Alarms","LEVEL-1",alertTable,alertProp,alertMatch);//no i18n

                } 
                catch (CustomViewException cvex) 
                {
                    NmsLogMgr.MISCERR.fail(NmsUtil.GetString("TL1: Custom View : Error Adding TL1-Alerts Custom View"),cvex);//no i18n

                } 
                catch (RemoteException rex) 
                {
                    NmsLogMgr.MISCERR.fail(NmsUtil.GetString("TL1: Custom View : Error Adding TL1-Alerts Custom View"), rex);//no i18n

                }
            } 
            else 
            {
                NmsLogMgr.MISCERR.log(NmsUtil.GetString("TL1: Custom View : Error Adding TL1-Alerts Custom View : Can't get AlertSessionBean handle"), Log.INTERMEDIATE_DETAIL);//no i18n

            }
        } 
        catch (ModuleNotInitializedException mniex) 
        {
            NmsLogMgr.MISCERR.fail(NmsUtil.GetString("TL1: Custom View : Error Adding TL1-Alerts Custom View : Can't get AlertSessionBean handle"), mniex);//no i18n

        }

        // Add TL1-Nodes Custom View
        TableColumn[] nodeTable = new TableColumn[6];
        nodeTable[0] = new TableColumn("name","Name",100);//no i18n

        nodeTable[1] = new TableColumn("type","Type",100);//no i18n

        nodeTable[2] = new TableColumn("isSNMP","IsSnmp",100);//no i18n

        nodeTable[3] = new TableColumn("ipAddress","IPAddress",100);//no i18n

        nodeTable[4] = new TableColumn("netmask","NetMask",100);//no i18n

        nodeTable[5] = new TableColumn("status","Status",55);//no i18n

        Properties nodeProp = new Properties();
        nodeProp.put("VIEW-LENGTH", "25");//no i18n

        nodeProp.put("Client", "All");//no i18n

        nodeProp.put("DEVICE-REF", "TL1-Panels");//no i18n

        nodeProp.put("parent", "TL1-Panels");//no i18n

        nodeProp.put("ICON-FILE", "images/tl1node.png");//no i18n

        nodeProp.put("SHOW-TREENODE-PROPERTIES", "true");//no i18n

        nodeProp.put("TARGET", "center");//no i18n

        nodeProp.put("TREE-POPUP-MENU", "dbmenu.xml,frameoptions.xml");//no i18n

        nodeProp.put("URL", "jsp/NetTable.jsp");//no i18n

        nodeProp.put("PANEL-NAME", "com.adventnet.nms.mapui.NmsListView");//no i18n

        nodeProp.put("MODULE-NAME", "Network Database");//no i18n

        nodeProp.put("MENU-FILE-NAME", "dbmenu.xml");//no i18n

        Properties nodeMatch = new Properties();
        nodeMatch.put("classname","TL1Node,AcmeMSUNode");//no i18n

        TopoSessionBean topoSession = null;
        try 
        {
            topoSession = TopoFE.getTopoSession();
            if (topoSession != null) 
            {
                try 
                {
                    topoSession.createCustomView("root", "TL1-Nodes","TL1-Nodes", "LEVEL-1", nodeTable, nodeProp, nodeMatch);//no i18n

                } 
                catch (CustomViewException cvex) 
                {
                    NmsLogMgr.MISCERR.fail(NmsUtil.GetString("TL1: Custom View : Error Adding TL1-Nodes Custom View"), cvex);//no i18n

                }
                catch (RemoteException rex) 
                {
                    NmsLogMgr.MISCERR.fail(NmsUtil.GetString("TL1: Custom View : Error Adding TL1-Nodes Custom View"), rex);//no i18n

                }
            } 
            else 
            {
                NmsLogMgr.MISCERR.log(NmsUtil.GetString("TL1: Custom View : Error Adding TL1-Nodes Custom View : Can't get TopoSessionBean handle"), Log.INTERMEDIATE_DETAIL);//no i18n

            }
        } 
        catch (ModuleNotInitializedException mniex) 
        {
            NmsLogMgr.MISCERR.fail(NmsUtil.GetString("TL1: Custom View : Error Adding TL1-Nodes Custom View : Can't get TopoSessionBean handle"), mniex);//no i18n

            topoSession = null;
        }

        // Add TL1-Gateway-Nodes Custom View
        TableColumn[] nodeTable1 = new TableColumn[6];
        nodeTable1[0] = new TableColumn("name","Name",100);//no i18n

        nodeTable1[1] = new TableColumn("type","Type",100);//no i18n

        nodeTable1[2] = new TableColumn("isSNMP","IsSnmp",100);//no i18n

        nodeTable1[3] = new TableColumn("ipAddress","IPAddress",100);//no i18n

        nodeTable1[4] = new TableColumn("netmask","NetMask",100);//no i18n

        nodeTable1[5] = new TableColumn("status","Status",55);//no i18n

        Properties nodeProp1 = new Properties();
        nodeProp1.put("VIEW-LENGTH", "25");//no i18n

        nodeProp1.put("Client", "All");//no i18n

        nodeProp1.put("DEVICE-REF", "TL1-Panels");//no i18n

        nodeProp1.put("parent", "TL1-Panels");//no i18n

        nodeProp1.put("ICON-FILE", "images/gatewaynode.png");//no i18n

        nodeProp1.put("SHOW-TREENODE-PROPERTIES", "true");//no i18n

        nodeProp1.put("TARGET", "center");//no i18n

        nodeProp1.put("TREE-POPUP-MENU", "dbmenu.xml,frameoptions.xml");//no i18n

        nodeProp1.put("URL", "jsp/NetTable.jsp");//no i18n

        nodeProp1.put("PANEL-NAME", "com.adventnet.nms.mapui.NmsListView");//no i18n

        nodeProp1.put("MODULE-NAME", "Network Database");//no i18n

        nodeProp1.put("MENU-FILE-NAME", "dbmenu.xml");//no i18n

        Properties nodeMatch1 = new Properties();
        nodeMatch1.put("classname","TL1GatewayNode");//no i18n

        //TopoSessionBean topoSession = null;
        try 
        {
            topoSession = TopoFE.getTopoSession();
            if (topoSession != null) 
            {
                try 
                {
                    topoSession.createCustomView("root", "TL1-Gateway-Nodes","TL1-Gateway-Nodes", "LEVEL-1", nodeTable1, nodeProp1, nodeMatch1);//no i18n

                } 
                catch (CustomViewException cvex) 
                {
                    NmsLogMgr.MISCERR.fail(NmsUtil.GetString("TL1: Custom View : Error Adding TL1-Gateway-Nodes Custom View"), cvex);//no i18n

                }
                catch (RemoteException rex) 
                {
                    NmsLogMgr.MISCERR.fail(NmsUtil.GetString("TL1: Custom View : Error Adding TL1-Gateway-Nodes Custom View"), rex);//no i18n

                }
            } 
            else 
            {
                NmsLogMgr.MISCERR.log(NmsUtil.GetString("TL1: Custom View : Error Adding TL1-Gateway-Nodes Custom View : Can't get TopoSessionBean handle"), Log.INTERMEDIATE_DETAIL);//no i18n

            }
        } 
        catch (ModuleNotInitializedException mniex) 
        {
            NmsLogMgr.MISCERR.fail(NmsUtil.GetString("TL1: Custom View : Error Adding TL1-Gateway-Nodes Custom View : Can't get TopoSessionBean handle"), mniex);//no i18n

            topoSession = null;
        }

        // Add TL1-GatewayAccess Custom View
        TableColumn[] nodeTable2 = new TableColumn[6];
        nodeTable2[0] = new TableColumn("name","Name",100);//no i18n

        nodeTable2[1] = new TableColumn("type","Type",100);//no i18n

        nodeTable2[2] = new TableColumn("isSNMP","IsSnmp",100);//no i18n

        nodeTable2[3] = new TableColumn("ipAddress","IPAddress",100);//no i18n

        nodeTable2[4] = new TableColumn("netmask","NetMask",100);//no i18n

        nodeTable2[5] = new TableColumn("status","Status",55);//no i18n

        Properties nodeProp2 = new Properties();
        nodeProp2.put("VIEW-LENGTH", "25");//no i18n

        nodeProp2.put("Client", "All");//no i18n

        nodeProp2.put("DEVICE-REF", "TL1-Panels");//no i18n

        nodeProp2.put("parent", "TL1-Panels");//no i18n

        nodeProp2.put("ICON-FILE", "images/gatewayacc.png");//no i18n

        nodeProp2.put("SHOW-TREENODE-PROPERTIES", "true");//no i18n

        nodeProp2.put("TARGET", "center");//no i18n

        nodeProp2.put("TREE-POPUP-MENU", "dbmenu.xml,frameoptions.xml");//no i18n

        nodeProp2.put("URL", "jsp/NetTable.jsp");//no i18n

        nodeProp2.put("PANEL-NAME", "com.adventnet.nms.mapui.NmsListView");//no i18n

        nodeProp2.put("MODULE-NAME", "Network Database");//no i18n

        nodeProp2.put("MENU-FILE-NAME", "dbmenu.xml");//no i18n

        Properties nodeMatch2 = new Properties();
        nodeMatch2.put("classname","TL1GatewayAccessSession");//no i18n

        //TopoSessionBean topoSession = null;
        try 
        {
            topoSession = TopoFE.getTopoSession();
            if (topoSession != null) 
            {
                try 
                {
                    topoSession.createCustomView("root", "TL1-GatewayAccess","TL1-GatewayAccess", "LEVEL-1", nodeTable2, nodeProp2, nodeMatch2);//no i18n

                } 
                catch (CustomViewException cvex) 
                {
                    NmsLogMgr.MISCERR.fail(NmsUtil.GetString("TL1: Custom View : Error Adding TL1-GatewayAccess Custom View"), cvex);//no i18n

                }
                catch (RemoteException rex) 
                {
                    NmsLogMgr.MISCERR.fail(NmsUtil.GetString("TL1: Custom View : Error Adding TL1-GatewayAccess Custom View"), rex);//no i18n

                }
            } 
            else 
            {
                NmsLogMgr.MISCERR.log(NmsUtil.GetString("TL1: Custom View : Error Adding TL1-GatewayAccess Custom View : Can't get TopoSessionBean handle"), Log.INTERMEDIATE_DETAIL);//no i18n

            }
        } 
        catch (ModuleNotInitializedException mniex) 
        {
            NmsLogMgr.MISCERR.fail(NmsUtil.GetString("TL1: Custom View : Error Adding TL1-GatewayAccess Custom View : Can't get TopoSessionBean handle"), mniex);//no i18n

            topoSession = null;
        }

        // Add TL1-Interfaces Custom View
        TableColumn[] intfTable = new TableColumn[6];
        intfTable[0] = new TableColumn("name","Name",100);//no i18n

        intfTable[1] = new TableColumn("type","Type",100);//no i18n

        intfTable[2] = new TableColumn("isSNMP","IsSnmp",100);//no i18n

        intfTable[3] = new TableColumn("ipAddress","IPAddress",100);//no i18n

        intfTable[4] = new TableColumn("netmask","NetMask",100);//no i18n

        intfTable[5] = new TableColumn("status","Status",55);//no i18n

        Properties intfProp = new Properties();
        intfProp.put("VIEW-LENGTH", "25");//no i18n

        intfProp.put("Client", "All");//no i18n

        intfProp.put("DEVICE-REF", "TL1-Panels");//no i18n

        intfProp.put("parent", "TL1-Panels");//no i18n

        intfProp.put("ICON-FILE", "images/tl1interface.png");//no i18n

        intfProp.put("SHOW-TREENODE-PROPERTIES", "true");//no i18n

        intfProp.put("TARGET", "center");//no i18n

        intfProp.put("TREE-POPUP-MENU", "dbmenu.xml,frameoptions.xml");//no i18n

        intfProp.put("URL", "jsp/NetTable.jsp");//no i18n

        intfProp.put("PANEL-NAME", "com.adventnet.nms.mapui.NmsListView");//no i18n

        intfProp.put("MODULE-NAME", "Network Database");//no i18n

        intfProp.put("MENU-FILE-NAME", "dbmenu.xml");//no i18n

        Properties intfMatch = new Properties();
        intfMatch.put("classname","TL1Interface");//no i18n

        if (topoSession != null) 
        {
            try 
            {
                topoSession.createCustomView("root","TL1-Interfaces","TL1-Interfaces","LEVEL-1",intfTable,intfProp,intfMatch);//no i18n

            } 
            catch (CustomViewException cvex) 
            {
                NmsLogMgr.MISCERR.fail(NmsUtil.GetString("TL1: Custom View : Error Adding TL1-Interfaces Custom View"),cvex);//no i18n

            } 
            catch (RemoteException rex) 
            {
                NmsLogMgr.MISCERR.fail(NmsUtil.GetString("TL1: Custom View : Error Adding TL1-Interfaces Custom View"), rex);//no i18n

            }
        } 
        else 
        {
            NmsLogMgr.MISCERR.log(NmsUtil.GetString("TL1: Custom View : Error Adding TL1-Interfaces Custom View : Can't get TopoSessionBean handle"), Log.INTERMEDIATE_DETAIL);//no i18n

        }

        // Add TL1-Performance-Views Custom View
        TableColumn[] statsTable = new TableColumn[9];
        statsTable[0] = new TableColumn("name","Statistic Name",160);//no i18n

        statsTable[1] = new TableColumn("id","Poll Id",40);//no i18n

        statsTable[2] = new TableColumn("dnsName","DNS Name",160);//no i18n

        // Extra column added for TL1-Performance-Views Custom View
        statsTable[3] = new TableColumn("parentObj","Parent Object",160);//no i18n

        statsTable[4] = new TableColumn("oid","Object Id",100);//no i18n

        statsTable[5] = new TableColumn("community","Community",75);//no i18n

        statsTable[6] = new TableColumn("period","Interval",60);//no i18n

        statsTable[7] = new TableColumn("active","Active",30);//no i18n

        statsTable[8] = new TableColumn("isMultiplePolledData","Multiple",30);//no i18n

        Properties statsProp = new Properties();
        statsProp.put("Client", "All");//no i18n

        statsProp.put("DEVICE-REF", "TL1-Panels");//no i18n

        statsProp.put("parent", "TL1-Panels");//no i18n

        statsProp.put("ICON-FILE", "images/stats.png");//no i18n

        statsProp.put("SHOW-TREENODE-PROPERTIES", "true");//no i18n

        statsProp.put("TARGET", "center");//no i18n

        statsProp.put("TREE-POPUP-MENU", "Custom Views,frameoptions.xml,TreeOperations.xml");//no i18n

        statsProp.put("URL", "jsp/Report.jsp");//no i18n

        statsProp.put("PANEL-NAME", "com.adventnet.nms.pollui.StatsAdminPanel");//no i18n

        statsProp.put("MODULE-NAME", "Stats Admin");//no i18n

        statsProp.put("TABLE-POPUP-MENU", "View");//no i18n

        statsProp.put("MENU-FILE-NAME", "pollmenu.xml");//no i18n

        Properties statsMatch = new Properties();
        statsMatch.put("protocol","TL1");//no i18n

        try 
        {
            PerfSessionBean peSession = PollFE.getPerfSession();
            if (peSession != null) 
            {
                try 
                {
                    peSession.createCustomView("root","TL1-Stats-Data","TL1-Performance-Views","LEVEL-1",statsTable,statsProp, statsMatch);//no i18n

                } 
                catch (CustomViewException cvex) 
                {
                    NmsLogMgr.MISCERR.fail(NmsUtil.GetString("TL1: Custom View : Error Adding TL1-Performance-Views Custom View"), cvex);//no i18n

                }
                catch (RemoteException rex) 
                {
                    NmsLogMgr.MISCERR.fail(NmsUtil.GetString("TL1: Custom View : Error Adding TL1-Performance-Views Custom View"), rex);//no i18n

                }
            } 
            else 
            {
                NmsLogMgr.MISCERR.log(NmsUtil.GetString("TL1: Custom View : Error Adding TL1-Performance-Views Custom View : Can't get PerfSessionBean handle"), Log.INTERMEDIATE_DETAIL);//no i18n

            }
        } 
        catch (ModuleNotInitializedException mniex) 
        {
            NmsLogMgr.MISCERR.fail(NmsUtil.GetString("TL1: Custom View : Error Adding TL1-Performance-Views Custom View : Can't get PerfSessionBean handle"), mniex);//no i18n

        }
    }

    //----------------------------------------------------------------------------

    private void removeTL1Views() throws NmsStorageException, UserTransactionException



    {
	MapAPI mapi = (MapAPI) NmsUtil.getAPI("MapAPI");//no i18n


	if (NmsUtil.relapi == null) return;  // Serialized Mode not allowed

        try 
        {
            if ((mapi != null) && mapi.doesTheMapExist("TL1-Map.netmap")) //no i18n

            {
                if ( ! mapi.deleteMap("TL1-Map.netmap")) //no i18n

                {
                    NmsLogMgr.MISCERR.log(NmsUtil.GetString("TL1: Custom View : Error Deleting TL1-Map Custom Map for TL1 Devices"), Log.INTERMEDIATE_DETAIL);//no i18n

                }
            }
        } 

        catch(NmsStorageException nmse) 
        {
            throw nmse;
        } 
        catch(UserTransactionException ute ) 
        {
            throw ute;
        }

        catch (RemoteException rex) 
        {
            NmsLogMgr.MISCERR.fail(NmsUtil.GetString("TL1: Custom View : Error Deleting TL1-Map Custom Map for TL1 Devices"), rex);//no i18n

        }

        try 
        {
            if ((mapi != null) && mapi.doesTheMapExist("TL1-Topo-Map.netmap")) //no i18n

            {
                if ( ! mapi.deleteMap("TL1-Topo-Map.netmap")) //no i18n

                {
                    NmsLogMgr.MISCERR.log(NmsUtil.GetString("TL1: Custom View : Error Deleting TL1-Topo-Map Custom Map for TL1 Devices"), Log.INTERMEDIATE_DETAIL);//no i18n

                }
            }
        } 

        catch(NmsStorageException nmse) 
        {
            throw nmse;
        } 
        catch(UserTransactionException ute ) 
        {
            throw ute;
        }

        catch (RemoteException rex) 
        {
            NmsLogMgr.MISCERR.fail(NmsUtil.GetString("TL1: Custom View : Error Deleting TL1-Topo--Map Custom Map for TL1 Devices"), rex);//no i18n

        }

        DBXmlUtility xmlutility = DBXmlUtility.getInstance(NmsUtil.relapi.getConnection());
        if (xmlutility != null) 
        {
            try 
            {
                xmlutility.removeNode("TL1-Panels", "root", "Default", true);//no i18n

            }
            catch (NmsStorageException nsex) 
            {
                NmsLogMgr.MISCERR.fail(NmsUtil.GetString("TL1: Custom View : Error Deleting TL1-Panels Tree Node and Child Nodes"), nsex);//no i18n

            }
        } 
        else 
        {
            NmsLogMgr.MISCERR.log(NmsUtil.GetString("TL1: Custom View : Error Deleting TL1-Panels Tree Node and Child Nodes : Can't get DBXmlUtility handle"), Log.INTERMEDIATE_DETAIL);//no i18n

        }
    }

    //----------------------------------------------------------------------------
    public void callMain (String[] args)
    {
        NmsLogMgr.MISCUSER.log(NmsUtil.GetString("TL1: Custom View : TL1CustomViewsMgr started as a seperate Web NMS process"), Log.INTERMEDIATE_DETAIL);//no i18n

        initialized = true;

        try 
        {
            // You shouldn't try to getAPI("TopoAPI") before DBServer is
            // initialized. This safeguards from that happening under some
            // rare race conditions, resulting in some exceptions.
            RunProcessInterface rpi = (RunProcessInterface) NmsUtil.runProcessModules.get("com.adventnet.nms.topodb.DBServer");//no i18n

            while ((rpi == null) || ( ! rpi.isInitialized())) 
            {
                try 
                {
                    Thread.sleep(1000);
                } catch (InterruptedException iex) { }
                rpi =  (RunProcessInterface) NmsUtil.runProcessModules.get("com.adventnet.nms.topodb.DBServer");//no i18n

            }

            // Check if Topo Module is initialized.
            TopoAPI tapi = (TopoAPI) NmsUtil.getAPI("TopoAPI");//no i18n

            while ( (tapi == null) || ( ! tapi.isInitialized()) ) 
            {
                try 
                {
                    Thread.sleep(2000);
                } catch (InterruptedException iex) { }
                tapi = (TopoAPI) NmsUtil.getAPI("TopoAPI");//no i18n

            }

            if (tapi.register(this)) 
            {
                NmsLogMgr.MISCUSER.log(NmsUtil.GetString("TL1: Custom View : Registering TL1CustomViewsMgr with TopoAPI successful"), Log.INTERMEDIATE_DETAIL);//no i18n

            }
            else 
            {
                NmsLogMgr.MISCERR.log(NmsUtil.GetString("TL1: Custom View : Error - Registering TL1CustomViewsMgr with TopoAPI fails"), Log.INTERMEDIATE_DETAIL);//no i18n

            }
            warmStartCheckAndInit(tapi);
        } 
        catch (RemoteException ex) 
        {
            NmsLogMgr.MISCERR.fail(NmsUtil.GetString("TL1: Custom View : Error - Registering TL1CustomViewsMgr with TopoAPI fails"), ex);//no i18n

        }

        catch (Exception ex1) 
        {
             NmsLogMgr.MISCERR.fail(NmsUtil.GetString("TL1: Custom View : Error - Registering TL1CustomViewsMgr with TopoAPI fails"), ex1);//no i18n

        }

    }

    //----------------------------------------------------------------------------
    // Prerequisite : Event(Fault) Module should be initialized
    //                Topo Module should be initialized
    //                TL1DiscProcess should be initialized 
    public synchronized boolean isInitialized()
    {
        if (initialized)
            return true;
        else
            return false;
    }

    //----------------------------------------------------------------------------
    public void shutDown()
    {
        synchronized(isAdded)
            {
                NmsLogMgr.MISCUSER.log(NmsUtil.GetString("TL1: Custom View : TL1CustomViewsMgr ready for Shut Down"), Log.INTERMEDIATE_DETAIL);       //no i18n

            }
    }

    //----------------------------------------------------------------------------

    private void warmStartCheckAndInit(TopoAPI tapi) throws NmsStorageException, UserTransactionException



    {
        synchronized(isAdded)
        {
	    try 
	    {
		if ((tapi.getNumNodes() == 0) && (tapi.getNumInterfaces() == 0))
		    return;

		// Count the TL1Interfaces
		Properties pif = new Properties();
		pif.put("statpollCommand", "*");  // Only TL1Interface has this prop //no i18n

		pif.put("classname", "TL1Interface");//no i18n
		Vector interface_names = tapi.getObjectNamesWithProps(pif);
		if (interface_names != null) 
		    tl1InterfaceCounter = interface_names.size();

		// Count the TL1Nodes
		Properties pno = new Properties();
		pno.put("loginCommand", "*");  // Only TL1Node has this property //no i18n
		Vector node_names = tapi.getObjectNamesWithProps(pno);
		if (node_names != null)
		    tl1NodeCounter = node_names.size();

		if ((tl1NodeCounter > 0) || (tl1InterfaceCounter > 0)) {
		    isAdded = new Boolean((Boolean.TRUE).booleanValue()); 
		}
	    } 

	    catch(NmsStorageException nmse) 
	    {
		throw nmse;
	    } 
	    catch(UserTransactionException ute ) 
	    {
		throw ute;
	    }

	    catch (RemoteException rex) 
	    {
		NmsLogMgr.MISCERR.fail(NmsUtil.GetString("TL1: Custom View : Error - While Warm Start Initialization of TL1CustomViewsMgr"), rex);//no i18n

	    }
	}//end of synchronized
    }
    //----------------------------------------------------------------------------
}
