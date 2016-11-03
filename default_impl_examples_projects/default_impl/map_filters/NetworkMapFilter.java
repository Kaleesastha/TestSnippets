// $Id: NetworkMapFilter.java,v 1.10.4.8 2013/08/21 10:24:56 wesley Exp $

/**
 * NetworkMapFilter.java
 */

package test;

import java.util.Vector;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Enumeration;

import java.rmi.RemoteException;

import org.json.JSONException;
import org.json.JSONObject;

import test.Printer;
import test.SwitchObject;

import com.adventnet.nms.mapdb.ExtendedMapFilter;
import com.adventnet.nms.mapdb.MapFilterList;
import com.adventnet.nms.mapdb.MapAPI;
import com.adventnet.nms.mapdb.MapDB;
import com.adventnet.nms.mapdb.MapSymbol;
import com.adventnet.nms.mapdb.MapLink;

import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.util.NmsLogMgr;

// NetworkSegmentation
import com.adventnet.nms.util.NetWatchUtil;

import com.adventnet.nms.severity.SeverityInfo;

import com.adventnet.management.log.Log;

import com.adventnet.nms.topodb.TopoAPI;
import com.adventnet.nms.topodb.SnmpNode;
import com.adventnet.nms.topodb.ManagedObject;
import com.adventnet.nms.topodb.Node;
import com.adventnet.nms.topodb.TopoObject;
import com.adventnet.nms.topodb.Network;
import com.adventnet.nms.topodb.IpAddress;
import com.adventnet.nms.topodb.SnmpInterface;


import com.adventnet.nms.store.NmsStorageException;
import com.adventnet.management.transaction.UserTransactionException; 


/** 
 *  This filter class is responsible for the Network maps that come up for every 
 *  managed network. This class adds one map for every discovered network that 
 *  is managed and displays all the nodes and interfaces of the network. Also 
 *  it creates another map under the network map to show all the failed systems
 *  of the network. The network map is a default map whereas the failed systems
 *  map is a custom map. The network map is made default because of the limitaiton
 *  in custom maps that only symbols are added to represent the MO mathcing the 
 *  map criteria. Hence the filter itself adds and deletes symbols and links to and 
 *  from the netwok map.
 */
public class NetworkMapFilter implements ExtendedMapFilter {

    TopoAPI tapi = null;
    Hashtable netTopologies = new Hashtable(10);

    /**
     *  Interface method that will be invoked for any ManagedObject addition.
     *  The MO added and a vector of the symbols that are created for the MO based on the 
     *  existing custom maps are passed along with a MapAPI handle. Here the vector is not
     *  to be altered but the MapAPI methods are used to add map or symbol or link 
     *  depending on the type of MO added.
     */

    public Vector filterMapSymbols ( Vector mapSymbs, ManagedObject obj, MapAPI mapi ) throws NmsStorageException, UserTransactionException{




        if ( obj == null )
            return mapSymbs;

        if ( !(obj instanceof TopoObject) )
            return mapSymbs;

        if ( obj instanceof Network ) {
            if ( ((Network)obj).getManaged() )
                addMapsForNetwork( ( Network ) obj, mapi );
            return mapSymbs;
        }

        if ( obj instanceof Node ) {
            addSymbolsForNode( (Node) obj, mapSymbs, mapi );
        }
        
        if ( obj instanceof IpAddress ) {
            handleInterface( (IpAddress) obj, mapSymbs, mapi );
        }

        return mapSymbs;
    } // filterMapSymbols()
    
    private MapSymbol setSymbolUserProp(MapSymbol mobj,ManagedObject mo,MapAPI mapi,boolean update)
	{
		try{
			JSONObject tooltip=null;
			Properties userprops = mobj.getUserProperties();
			String obj=(String) userprops.get("tooltip");
			if(obj!=null)
			tooltip = new JSONObject(obj);
			else
				tooltip=new JSONObject();
			
			if(mo instanceof Node)
			{
				mo=(Node)mo;
				tooltip.put("isRouter",((Node) mo).getIsRouter());
			}
			if(mo instanceof SnmpNode)
			{
				mo=(SnmpNode)mo;
				tooltip.put("sysOID",((SnmpNode) mo).getSysOID());
				tooltip.put("sysName",((SnmpNode) mo).getSysName());
				//mobj.setIconName("server-Vista-icon128.png");
			}
			
			if(mo instanceof IpAddress)
			{
				mo=(IpAddress)mo;
				tooltip.put("parentNet",((IpAddress) mo).getParentNet());
				tooltip.put("parentNode",((IpAddress) mo).getParentNode());
			}
			if(mo instanceof SwitchObject)
			{
				mo=(SwitchObject)mo;
				Integer numport = new Integer(((SwitchObject) mo).getNumPorts());
				tooltip.put("numPorts",numport.toString());
				//mobj.setIconName("hp-switch-device-md.png");
			}
			if(mo instanceof Printer)
			{
				mo=(Printer)mo;
				Integer ps = new Integer(((Printer) mo).getPrinterStatus());
				tooltip.put("printerStatus",ps.toString());
			}
			tooltip.put("status",mobj.getStatus());
			Properties toolprops = new Properties();
			toolprops.put("name",mobj.getName());
			toolprops.put("tooltip",tooltip.toString());
			mobj.setProperties(toolprops);
			if(update)
			mapi.updateSymbol(mobj.getMapName(),toolprops);
			}
			catch(JSONException json)
			{
				
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NmsStorageException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UserTransactionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return mobj;
		
	}
    /**
     * This is one another interface method that will be invoked whenever there is a update
     * in the topology database. This method handles the Network updates particularly network 
     * deletion and network getting managed.
     */

    public void update ( String type, ManagedObject obj, MapAPI mapi ) throws NmsStorageException, UserTransactionException{



        if ( obj == null || !(obj instanceof Network) )
            return;

		String netmapname = obj.getName() + ".netmap";
		if ( type.startsWith("Deleted") ) {
			try {
				if ( mapi.doesTheMapExist(netmapname) ) {
					mapi.deleteMap(netmapname);
				}

			} catch(NmsStorageException nmse) {
			    throw nmse;
			} catch(UserTransactionException ute ) {
			    throw ute;

			} catch( RemoteException ex ) {
                NmsLogMgr.MAPERR.fail("Exception in NetworkMapFilter update while deleting map " + netmapname + " : ", ex);
			}
		}
		else if (type.startsWith("Manage")) {
			if ( obj.getManaged() ) {
				try	{
					if ( !mapi.doesTheMapExist(netmapname) ) {
						addMapsForNetwork( (Network)obj, mapi );
                        addSymbolsForTheNetwork( (Network)obj, mapi );
					}

				} catch(NmsStorageException nmse) {
				    throw nmse;
				} catch(UserTransactionException ute ) {
				    throw ute;

				} catch( RemoteException ex ) {
                    NmsLogMgr.MAPERR.fail("Exception in NetworkMapFilter update " + netmapname + " : ", ex);
				}
			}
		}
		if(!type.equalsIgnoreCase("Added")&&!type.equalsIgnoreCase("Deleted"))
	    {	
	    try {
			
			Vector symbolKeys = mapi.getSymbolNamesAssociatedWithObject("symbol",obj.getName());
			if(symbolKeys != null && symbolKeys.size()>0)
			{
				for(int i=0;i<=symbolKeys.size()-1;i++)
				{
					MapSymbol sym=(MapSymbol)mapi.getObject((String)symbolKeys.elementAt(0));
					if(sym != null)
					{
						setSymbolUserProp(sym,obj,mapi,true);
					}

				}
			}
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	    }
		    try {
			    if ( mapi.doesTheMapExist(netmapname) )
			    {
				    Properties p = new Properties();
				    p.put("discoveryStatus",getDiscoveryStatusValue(((Network)obj).getDiscoveryStatus()));
				    mapi.updateMap(netmapname,p);
			    }
		    } catch(NmsStorageException nmse) {
			    NmsLogMgr.MAPERR.fail("NmsStorageException in NetworkMapFilter update while updating map " + netmapname + " : ", nmse);//No Internationalization
			    throw nmse;
		    } catch(UserTransactionException ute ) {
			    NmsLogMgr.MAPERR.fail("UserTransactionException in NetworkMapFilter update while updating map " + netmapname + " : ", ute);//No Internationalization

			    throw ute;
		    } catch( RemoteException ex ) {
			    NmsLogMgr.MAPERR.fail("Exception in NetworkMapFilter update while updating map " + netmapname + " : ", ex);//No Internationalization

		    }
	       

    } // update()


    private void addMapsForNetwork ( Network net, MapAPI mapi ) throws NmsStorageException, UserTransactionException{




        String netname = net.getName();
        String netmapname = netname + ".netmap";

        // the properties of the map which we want to create
        Properties mapprop = new Properties();
        mapprop.put("label",netname);
        mapprop.put("objName", "null");
        mapprop.put("menuName", "null");
        mapprop.put("imageName", "null");
        mapprop.put("treeIconFileName","images/ipn" + NmsUtil.getImageType());
        mapprop.put("webTreeIcon","ip-address.png");
        mapprop.put("mapSymbolRenderer", "{\"html\":\"symbol_label\",\"java\":\"com.adventnet.nms.mapui.EthernetMapSymbolRenderer\"}");
        mapprop.put("mapLinkRenderer", "{\"html\":\"line\",\"java\":\"com.adventnet.nms.mapui.EthernetMapLinkRenderer\"}");
        mapprop.put("helpDoc", "default_impl/map_filters/README.html");
        mapprop.put("autoPlacement", String.valueOf(true));
        mapprop.put("topology","$grid(width=55;height=55;gapX=120;gapY=130;scroll=vertical)");
        mapprop.put("currentTopology","grid");
        mapprop.put("backgroundColor","255,255,255");
        mapprop.put("discoveryStatus",getDiscoveryStatusValue(net.getDiscoveryStatus()));
        mapprop.put("flash","true");
        mapprop.put("HTML-TABLE","Network Maps");
        mapprop.put("MO-NAME",netname);

	//For TabView
	Properties tabprop=new Properties();

	tabprop.put("tabPanels", "Events,Alerts");
	Properties properties1 = new Properties();

	properties1.put("FieldsWanted", "Status=severity;Source=source;Date/Time=time;Message=text;Category=category;Node=node;Entity=entity;");
	properties1.put("source",net.getName());
	tabprop.put("EVENT-PROPS", properties1);

	Properties properties2 = new Properties();
	properties2.put("FieldsWanted", "Status=severity=55;Failure Object=entity=135;Alarm Group=groupName=150;Date/Time=modTime=155;Alarm Message=message=275;");
	tabprop.put("ALERT-PROPS", properties2);
	///Tab view props ends
                
                
		
        // add the map
        try {
		 if(getDiscoveryStatusValue(net.getDiscoveryStatus()).equals("Yet to begin"))
        	{
	            try
        	    {
                	Thread.sleep(500);
	            }
        	    catch(InterruptedException e)
	            {
	            }
        	    if(tapi == null)
	            {
        	        getTopoAPI();
        	    }
	            Network network = (Network)tapi.checkOut(netname,0,false,true);
	            mapprop.put("discoveryStatus",getDiscoveryStatusValue(network.getDiscoveryStatus()));
        	}


		if ( mapi.addMapWithTabs (netmapname, mapprop,tabprop) )
		{
					//if ( mapi.addMap (netmapname, mapprop) ){
          		NmsLogMgr.MAPUSER.log("Created a map for network "+netmapname, Log.SUMMARY);
                	netTopologies.put(netmapname,"grid");
          		addFailedSystemsMapForNetwork ( netmapname, netname, mapi );
      		}

	} catch(NmsStorageException nmse) {
	    throw nmse;
	} catch(UserTransactionException ute ) {
	    throw ute;

        } catch (Exception anye) {
      		NmsLogMgr.MAPERR.fail("Exception in NetworkMapFilter while adding network map for network " + net, anye);
        }
    } // addMapsForNetwork()
    
    private void addFailedSystemsMapForNetwork ( String networkMapKey, String netname, MapAPI api ) throws Exception {

        Properties mapprop = new Properties();
	Properties tabprop = new Properties();
        mapprop.put("label","Failed Systems");
        mapprop.put("parentMapKey" , networkMapKey);
        //mapprop.put("parentNode" , networkMapKey);
        mapprop.put("menuName", "failedobjectsmenu");
        mapprop.put("treeIconFileName","images/burst"+NmsUtil.getImageType());
        mapprop.put("webTreeIcon","failed-systems.png");
        mapprop.put("helpDoc", "help/user_guide/app_ui/maps/map_details/appui_map_details.html");
        mapprop.put("topology","grid(width=60;height=60;gapX=140;gapY=140),star,ring,flow");
        mapprop.put("currentTopology","grid");
        mapprop.put("mapSymbolRenderer", "{\"html\":\"symbol_label\",\"java\":\"com.adventnet.nms.mapui.MapSymbolRendererImpl_4\"}");
        mapprop.put("backgroundColor","255,255,255");

        // A node can belong to multiple nets and so have many nets as parent
        // wildcard * and comma separation etc supported in this filter props
        Properties filterProp = new Properties();
        filterProp.put("parentNet","*"+netname+"*");
        filterProp.put("stringstatus", "critical,major");

	tabprop.put("tabPanels", "Events,Alerts");
	Properties properties1 = new Properties();
	properties1.put("FieldsWanted", "Status=severity;Source=source;HelpURL=helpURL;Date/Time=time;Message=text;Category=category;Network=network;Node=node;Entity=entity;");
	properties1.put("source","*");
	tabprop.put("EVENT-PROPS", properties1);
	Properties properties2 = new Properties();
	properties2.put("FieldsWanted", "Status=severity=55;Failure Object=entity=135;Alarm Group=groupName=150;");
	tabprop.put("ALERT-PROPS", properties2);

        // add the custom map
        if ( api.addCustomMapWithTabs( netname+"Failed_Objects_Map", mapprop, filterProp,tabprop ) ) {
//        if ( api.addCustomMap( netname+"Failed_Objects_Map", mapprop, filterProp ) ) {
            NmsLogMgr.MAPUSER.log("Created the map "+netname+"Failed_Objects_Map", Log.SUMMARY);
        }
    } // addFailedSystemsMapForNetwork()


    private void addSymbolsForTheNetwork( Network net, MapAPI mapi ) throws NmsStorageException, UserTransactionException{



        if ( tapi == null )
            getTopoAPI();
      
        String netname = net.getName();
        String netmapname = netname + ".netmap";
        try {
            Vector nodes = tapi.getNodesOfNetwork( netname );
            Vector interfaces = tapi.getInterfacesOfNetwork( netname );
            
            if ( nodes != null ) {
                for ( Enumeration en = nodes.elements(); en.hasMoreElements(); ) {
                    String name = ( String ) en.nextElement();
                    Node nodeObj = ( Node ) tapi.getByName( name );
                    if ( nodeObj == null )
                        continue;
                    MapSymbol symb = createMapSymbolObj( nodeObj );
                    mapi.addSymbol( netmapname, symb.getProperties() );
                }
            }
            
            String topology = getTopologyOfMap( netmapname, mapi );

            if ( interfaces == null ) 
                return;

            for ( Enumeration en = interfaces.elements(); en.hasMoreElements(); ) {
                String name = ( String ) en.nextElement();
                IpAddress interfaceObj = ( IpAddress ) tapi.getByName( name );
                if ( interfaceObj == null ) 
                    continue;
                if ( topology.equals("ethernet") ) {
                    MapLink link = createMapLinkObj( interfaceObj );
                    mapi.addLink( netmapname, link.getProperties() );
                    continue;
                } 
                
                if ( interfaceObj instanceof SnmpInterface ) {
                    if ( checkIfEthernet( (SnmpInterface)interfaceObj ) ) {
                        if ( updateMapTopology( netmapname, mapi ) ) {
                            topology = "ethernet";
                            MapLink link = createMapLinkObj( interfaceObj );
                            mapi.addLink( netmapname, link.getProperties() );
                            continue;
                        }
                    }
                }
//                  MapSymbol symb = createMapSymbolObj( interfaceObj );
//                  mapi.addSymbol( netmapname, symb.getProperties() );
//                  continue;
            }

		} catch(NmsStorageException nmse) {
		    throw nmse;
		} catch(UserTransactionException ute ) {
		    throw ute;

        } catch ( RemoteException ee ) {
            NmsLogMgr.MAPERR.fail("Exception in NetworkMapFilter while adding map objects for Network " + netname + " : ",ee);
        }
    } // addSymbolsForTheNetwork()


    private boolean updateMapTopology ( String mapName, MapAPI mapi ) throws NmsStorageException, UserTransactionException{




        boolean result = false;
        try {
            Properties p = new Properties();
            p.put("topology","$ethernet(width=55;height=55;gapX=120;gapY=130;scroll=vertical;fixedSymbolSize=true;)");
            p.put("currentTopology","ethernet");
            result = mapi.updateMap( mapName, p );

	} catch(NmsStorageException nmse) {
	    throw nmse;
	} catch(UserTransactionException ute ) {
	    throw ute;

        } catch ( RemoteException rem ) {
            NmsLogMgr.MAPERR.fail( rem + " caught in NetworkMapFilter while trying to update map topology for " + mapName, null);
            return false;
        }
        if ( result )
            netTopologies.put(mapName, "ethernet");
        return result;
    } // updateMapTopology()


    private String getTopologyOfMap ( String netmapname, MapAPI mapi ) throws NmsStorageException, UserTransactionException{




        String topology = ( String ) netTopologies.get( netmapname);
        if ( topology != null )
            return topology;
        try {
            MapDB map = mapi.getMap( netmapname );
            if ( map == null )
                return null;
            topology = map.getCurrentTopology();
            netTopologies.put(netmapname,topology);

	} catch(NmsStorageException nmse) {
	    throw nmse;
	} catch(UserTransactionException ute ) {
	    throw ute;

        } catch ( RemoteException ee ) {
            NmsLogMgr.MAPERR.fail( ee + " caught in NetworkMapFilter while trying get map topology for " + netmapname, null);
        }
        return topology;
    } // getTopologyOfMap()


    private void createLinksForInterfaceSymbolsInMap( String mapName, MapAPI mapi ) throws NmsStorageException, UserTransactionException {




        try {
            Vector mapObjects = mapi.getSymbolNames( mapName );
            if ( mapObjects == null )
                return;

            if ( tapi == null )
                getTopoAPI();

            String parentNet = mapName.substring(0,mapName.indexOf(".netmap"));

            for ( Enumeration en = mapObjects.elements(); en.hasMoreElements(); ) {
                String name = ( String ) en.nextElement();
                MapSymbol symb = ( MapSymbol ) mapi.getObject( name );
                if ( symb == null )
                    continue;
                ManagedObject mo = tapi.getByName( symb.getObjName() );
                if ( mo == null || !(mo instanceof Node) )
                    continue;
                
                ManagedObject interfaceObj = null;
                Vector ipaddrs = ((Node)mo).getIpaddrs();
                for (int j=0;j<ipaddrs.size();j++)
                {
                    String ipaddr = (String)ipaddrs.elementAt(j);
                    IpAddress ipAddr = tapi.getInterface(ipaddr);
                    if (ipAddr != null && ipAddr.getParentNet() != null)
                    {
                        if (parentNet.indexOf(ipAddr.getParentNet()) != -1)
                        {
                            interfaceObj = ipAddr;
                        }
                    }
                }
                
                if ( interfaceObj != null ) {
                    MapLink link = createMapLinkObj( interfaceObj );
                    link.setMapName( mapName );
                    mapi.addLink( mapName, link.getProperties() );
                }
            }

	} catch(NmsStorageException nmse) {
	    throw nmse;
	} catch(UserTransactionException ute ) {
	    throw ute;

        } catch ( RemoteException ee ) {
            NmsLogMgr.MAPERR.fail("NetworkMapFilter exception while creating links after changing the map " + mapName + " to ethernet : ",ee);
        }
    } // createLinksForInterfaceSymbolsInMap()

    /** Create symbols for the node in the corresponding net wrok map */

    private void addSymbolsForNode ( Node node, Vector mapSymbs, MapAPI mapi ) throws NmsStorageException, UserTransactionException{




        Vector parentNets = node.getParentNets();
        if ( parentNets == null )
            return;
        MapSymbol symb = createMapSymbolObj( node );

        for ( Enumeration en = parentNets.elements(); en.hasMoreElements(); ) {
            String net = ( String ) en.nextElement();
            String mapName = net + ".netmap";

            try {
                // As there won't be maps for unmanaged Networks/also if maps are deleted
                if ( !mapi.doesTheMapExist( mapName ) )
                    continue;

	    } catch(NmsStorageException nmse) {
		throw nmse;
	    } catch(UserTransactionException ute ) {
		throw ute;

            } catch( Exception exx ) {}

            MapSymbol symbol = (MapSymbol) symb.clone();
            symbol.setMapName( net + ".netmap" );
            mapSymbs.addElement( symbol );
        }
    } // addSymbolsForNode

    /** Create symbols for the node in the corresponding network map */

    private void handleInterface ( IpAddress interfaceObj, Vector mapSymbs, MapAPI mapi ) throws NmsStorageException, UserTransactionException{




        String parentNet = interfaceObj.getParentNet();
        String mapName = parentNet + ".netmap";

        try {
            // As there won't be maps for unmanaged Networks/also if maps are deleted        
            if ( !mapi.doesTheMapExist( mapName ) )
                return;

	} catch(NmsStorageException nmse) {
	    throw nmse;
	} catch(UserTransactionException ute ) {
	    throw ute;

        } catch( Exception exx ) {}

        String topology = getTopologyOfMap( mapName, mapi );

        if ( topology == null ) {
            return;
        }
        if ( topology.equals("ethernet") ) {
            MapLink link = createMapLinkObj( interfaceObj );
            link.setMapName( mapName );
            mapSymbs.addElement( link );
            return;
        }
        else {
            if ( interfaceObj instanceof SnmpInterface ) {
                if ( checkIfEthernet( (SnmpInterface)interfaceObj ) ) {
                    if ( updateMapTopology( mapName, mapi ) ) {
                        createLinksForInterfaceSymbolsInMap( mapName, mapi );
                        MapLink link = createMapLinkObj( interfaceObj );
                        link.setMapName( mapName );
                        mapSymbs.addElement( link );
                        return;
                    }
                }
            }
        }
//          MapSymbol symbol = createMapSymbolObj( interfaceObj );
//          symbol.setMapName( mapName );
//          mapSymbs.addElement( symbol );
    } // handleInterface

    private boolean checkIfEthernet ( SnmpInterface obj ) {
        int ifType = 0;
        try {
            ifType = Integer.parseInt( obj.getPhysMedia() );
        } catch ( NumberFormatException ex ) {
            return false;
        }
        return ( ifType == 6 || ifType == 26 ); // 6 -- ethernet-csmacd && 26 -- ethernet-3Mbit
    } // checkIfEthernet()

	/** Create a map object based on a topology object **/
	private MapSymbol createMapSymbolObj ( ManagedObject obj ) {
		MapSymbol mobj = new MapSymbol();
		String objname = obj.getName();
        // WebNMS Field added.
        if(obj.getWebNMS()!=null)
        {
            mobj.setWebNMS(obj.getWebNMS());
        }

		mobj.setName(objname);
		mobj.setObjName(objname);
		mobj.setLabel(getShortie(obj.getDisplayName(),obj));
		mobj.setType(obj.getType());
		
		if (obj.getManaged()) {
			mobj.setParameter("status", String.valueOf(obj.getStatus()));
			mobj.setParameter("managed","true");
		} else {
			int unknown = SeverityInfo.getInstance().getUnknown();
			mobj.setParameter("status", String.valueOf(unknown));
			mobj.setParameter("managed","false");
		}
		int objtype = 1;
		String menuName = "objectmenu";
		String iconName = "";
		if ( obj instanceof Network ) {
			if (((Network)obj).getDiscover()) {
				mobj.setParameter("discover","true");
			} else {
				mobj.setParameter("discover","false");
			}
			objtype = 2;
			menuName = "netmenu";
			iconName="ip" + NmsUtil.getImageType();
		} else if (obj instanceof IpAddress) {
			objtype=4;
			menuName="interfacemenu";
			iconName="if" + NmsUtil.getImageType();
		} else if  (obj instanceof Node) {
			if(!((Node)obj).getIsRouter()) {
				objtype=1;
				menuName="nodemenu";
			} else {
				objtype=1;
				menuName="routermenu";
				iconName= "router" + NmsUtil.getImageType();
			}
		}

        Properties mapIconProperties = (Properties)MapFilterList.propertiesTable.get( obj.getType() );

		if( mapIconProperties != null )	{
			for( Enumeration keys=mapIconProperties.keys();keys.hasMoreElements(); ) {
				String key = (String)keys.nextElement();
				String symbolValue = mobj.getProperty( key );

				String value = (String)mapIconProperties.get(key);
				if( value.indexOf( "MO(" ) >= 0 ) { 
                    /* Assign the MO Property */
					Properties moProps = obj.getProperties();
					String moKey = value.substring( value.indexOf("(")+1,value.indexOf(")") );
					if( moProps != null ) {
						String valueInMO = moProps.getProperty(moKey);
						if( valueInMO != null )	{
							mobj.setParameter(key, valueInMO);
						}
					}
				}
			}
		}
		return setSymbolUserProp(mobj,obj,(MapAPI)NmsUtil.getAPI("MapAPI"),false);
	} // End createMapSymbolObj

	/** Create a map object based on a topology object **/
	private MapLink createMapLinkObj ( ManagedObject obj ) {
		if ( !(obj instanceof IpAddress) ) return null;
		MapLink mobj = new MapLink();
		String objname = obj.getName();
		mobj.setName(objname);
		mobj.setObjName(objname);
        // WebNMS Field added.
        if(obj.getWebNMS()!=null)
        {
            mobj.setWebNMS(obj.getWebNMS());
        }
		mobj.setType(obj.getType());
		mobj.setOwnerName( obj.getOwnerName() );
		mobj.setSource(((IpAddress)obj).getParentNode());

		if ( obj.getManaged() ) {
			mobj.setParameter("status", String.valueOf(obj.getStatus()));
			mobj.setParameter("managed","true");
		} else {
			int unknown = SeverityInfo.getInstance().getUnknown();
			mobj.setParameter("status", String.valueOf(unknown));
			mobj.setParameter("managed","false");
		}
		return mobj;
	} // createMapLinkObj

    private void getTopoAPI() {
		try
		{   
			tapi = (TopoAPI) NmsUtil.getAPI("TopoAPI");//No I18N
			while( tapi == null)
			{
				try
				{
				Thread.sleep(200);
				}
				catch(Exception e)
				{}
				tapi = (TopoAPI) NmsUtil.getAPI("TopoAPI");//No I18N
			}
		}
		catch ( Exception e)
		{
			e.printStackTrace();
		}
    } // getTopoAPI()

    //This method will truncate the string after the first dot if the label is DNS. 
    //This will return the same if the label contains IP. If the label contains 3 
    //contigeous dots seperated by integer values, it will be treated as IP.
    private String getShortie( String label ) {

        if ( label == null )
            return "";
        
        int oldIndex = 0;
        int newIndex = 0;
        int dotCount = 0;
        
        while((newIndex = label.indexOf(".",oldIndex)) != -1) {
            try {
                Integer.parseInt(label.substring(oldIndex,newIndex));
            } catch (NumberFormatException ne) {
                dotCount=0;
                oldIndex = newIndex+1;
                continue;
            }
            oldIndex = newIndex+1;
            dotCount++;	
        }
        
        //Only this condition need to be changed if IpAddr contains more than 3 dots.
        if (dotCount != 3) {
            int index = label.indexOf(".");
            if (index != -1)
                label = label.substring(0,index);
        }
        return label;
    } // getShortie()

	private String getShortie(String label, ManagedObject obj)
	{
		if (label == null)
			return "";

        if(obj!=null && obj instanceof TopoObject)
        {
            label = NetWatchUtil.truncateDomainName(label, ((TopoObject)obj).getDomainName()); 
        }
        else
        {
            getShortie(label);
        }
		return label;
	}

    //Returns the status of the NetworkDiscovery
    private String getDiscoveryStatusValue( int val )
    {
        if ( val == 1 )
        {
            return "Yet to begin";//No Internationalization

        }
        else if ( val == 2 )
        {
            return "In progress";//No Internationalization

        }
        else if ( val == 3 )
        {
            return "Finished";//No Internationalization

        }
        else 
        {
            return "Discovery disabled";//No Internationalization

        }
    }

} // NetworkMapFilter
