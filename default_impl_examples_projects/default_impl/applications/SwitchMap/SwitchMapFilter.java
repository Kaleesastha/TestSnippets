/* $Id: SwitchMapFilter.java,v 1.3.8.3 2013/07/05 11:15:20 aravinds Exp $ */

package test;

import java.util.Properties;
import java.util.Vector;

import com.adventnet.nms.topodb.TopoAPI;
import com.adventnet.nms.topodb.ManagedObject;
import com.adventnet.nms.topodb.Network;
import com.adventnet.nms.topodb.Node;
import com.adventnet.nms.topodb.SnmpNode;
import com.adventnet.nms.mapdb.MapAPI;
import com.adventnet.nms.mapdb.MapFilter;
import com.adventnet.nms.mapdb.MapSymbol;
import com.adventnet.nms.mapdb.MapLink;
import com.adventnet.nms.mapdb.MapGroup;
import com.adventnet.nms.mapdb.MapContainer;

import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.severity.SeverityInfo;

import com.adventnet.nms.util.NmsLogMgr;
import com.adventnet.management.log.Log;


import com.adventnet.nms.store.NmsStorageException;
import com.adventnet.management.transaction.UserTransactionException; 


public class SwitchMapFilter implements MapFilter {

    TopoAPI topoapi = null;

    String switchMap = "Switches.netmap";


    public Vector filterMapSymbols( Vector mapSymbs, ManagedObject obj, MapAPI api ) throws NmsStorageException, UserTransactionException {



        if ( obj instanceof Network )
            return mapSymbs;

        if ( obj instanceof SwitchObject ) {
            try {
                Vector mapNames = createMaps( (Node) obj, api );
                for( int z = 0; z < mapNames.size(); z++ ) {
                    String mapName = (String) mapNames.elementAt(z);
                    String switchName = obj.getKey();
                    MapContainer mapobject = (MapContainer) createMapObject( obj, "Container" );
                    mapobject.setMapName( mapName );
                    mapobject.setObjType( 1 );
                    mapobject.setTopology("$switch");
                    mapobject.setCurrentTopology("switch");
                    String sysDescr = ((SnmpNode)obj).getSysDescr();
					mapobject.setParameter("label",switchName + " : " + sysDescr);
					mapobject.setParameter("iconName","switch2.png");
					
                    mapSymbs.addElement( mapobject );
                }

	    } catch(NmsStorageException nmse) {
		throw nmse;
	    } catch(UserTransactionException ute ) {
		throw ute;

            } catch ( Exception ee ) {
                NmsLogMgr.MAPERR.fail("Exception in SwitchMapFilter while processing notification for Switch " + obj, ee);
            }
        }

        else if ( obj instanceof PortObject ) {

            if ( topoapi == null )
                getTopoAPI();

            try {
                String switchName = obj.getParentKey();

                SwitchObject parentSwitch = (SwitchObject) topoapi.checkOut( switchName,0,false,true );
                if ( parentSwitch == null ) {
                    NmsLogMgr.MAPERR.fail("SwitchMapFilter aborting symbol addition for Port " + obj.getName() + ". Parent " + switchName + " doesn't exist.", null);
                    return mapSymbs;
                }

                Vector parentNets = parentSwitch.getParentNets();
                if ( parentNets == null )
                    return mapSymbs;
                    
                parentNets.addElement( switchMap );
                for ( int z = 0; z < parentNets.size(); z++ ) {
                    String mapName = (String) parentNets.elementAt(z);
                    if ( !mapName.endsWith( switchMap ) )
                        mapName = mapName + switchMap;

                    if ( api.doesTheMapExist( mapName ) ) {
                        Vector symbols = api.getSymbolNames( mapName );
                        if ( symbols != null && symbols.contains( switchName + "\t" + mapName ) ) {
                            MapSymbol mapobject = createMapObject( obj, "Symbol" );
                            mapobject.setLabel( ((PortObject)obj).getPortIfDescr() );
                            mapobject.setMapName( mapName );
                            mapobject.setParentName( parentSwitch.getName() );
                            mapobject.setObjType( 1 );
                            mapobject.setParameter( "ifIndex", String.valueOf( ((PortObject)obj).getPortIfIndex() ) );
                            
                            mapSymbs.addElement( mapobject );
                        }
                    }
                } 

	    } catch(NmsStorageException nmse) {
		throw nmse;
	    } catch(UserTransactionException ute ) {
		throw ute;

            } catch ( Exception ex ) {
                    NmsLogMgr.MAPERR.fail("Exception while adding symbol for port object "+ obj.getKey() , ex);
            }
        }
        return mapSymbs;
    }

    /* Here we will add the SwitchMap */


    private Vector createMaps( Node mobj, MapAPI mapapi ) throws NmsStorageException, UserTransactionException {




        Vector mapNames = new Vector();
        String mapName = null;

        try {

            if ( !mapapi.doesTheMapExist( switchMap ) ) {
                int index = 1;
                if ( mapapi.doesTheMapExist("RouterMap.netmap") )
                    index = 2;
                if ( addSwitchMap( switchMap, null, index, mapapi ) )
                    mapNames.addElement( switchMap );
            }
            else
                mapNames.addElement( switchMap );
            
            Vector parentnets = mobj.getParentNets();
            for ( int i = 0; i < parentnets.size(); i++ ) {
                String parentname = (String) parentnets.elementAt(i);
                if ( !mapapi.doesTheMapExist( parentname + ".netmap" ) ) 
                    continue;
                mapName = parentname + switchMap;
                if ( mapapi.doesTheMapExist( mapName ) ) {
                    mapNames.addElement( mapName );
                    continue;
                }
                if ( addSwitchMap( mapName, parentname + ".netmap", -1, mapapi ) )
                    mapNames.addElement( mapName );
            }

	} catch(NmsStorageException nmse) {
	    throw nmse;
	} catch(UserTransactionException ute ) {
	    throw ute;

        }
        catch ( Exception someexc ) {
            NmsLogMgr.MAPERR.fail("Exception in SwitchMapFilter while creating maps : ", someexc);
            return null;
        }
        return mapNames;
    }
    

    private boolean addSwitchMap ( String mapname, String parent, int index, MapAPI mapapi ) throws NmsStorageException, UserTransactionException {



        if ( ( mapname == null ) || ( mapapi == null ) )
            return false;

        Properties p = new Properties();
        p.put("name", mapname);
        p.put("label", "Switches");
        if ( parent != null && !parent.equalsIgnoreCase("null") )
            p.put("parentMapKey", parent);
        p.put("autoPlacement", String.valueOf( true ));
        p.put("anchored", String.valueOf( true ));
        p.put("treeIconFileName", "images/switch1" + NmsUtil.getImageType());
        p.put("webTreeIcon","switches.png");
        p.put("topology","$switch");
        p.put("currentTopology","switch");
        p.put("backgroundColor","255,255,255");
        p.put("mapSymbolRenderer", "{\"html\"=\"switchports\",\"java\"=\"com.adventnet.nms.mapui.SwitchMapRenderer\"}");
        p.put("mapLinkRenderer", "{\"html\"=\"no_link\",\"java\"=\"com.adventnet.nms.mapui.MapLinkRendererImpl\"}");
        p.put("helpDoc", "default_impl/applications/help/SwitchMap.html");
        p.put("HTML-TABLE","Infrastructure View");

        boolean added = false;

        try {

            if ( index == -1 )
                added = mapapi.addMap( mapname, p );
            else
                added = mapapi.addMap( mapname, p, index );

            if ( added )
                NmsLogMgr.MAPUSER.log("In SwitchMapFilter ." + mapname + " created successfully ", Log.SUMMARY);
            else
                NmsLogMgr.MAPERR.fail("In SwitchMapFilter ." + mapname + " could not be created. " , null);

	} catch(NmsStorageException nmse) {
	    throw nmse;
	} catch(UserTransactionException ute ) {
	    throw ute;

        }
        catch ( Exception e ) {
            NmsLogMgr.MAPERR.fail("Exception in SwitchMapFilter while trying to add " + mapname, e );
        }
        return added;
    }

    private MapSymbol createMapObject ( ManagedObject obj, String type ) {
        MapSymbol mobj;
        if ( type.equals("Group") )
            mobj = new MapGroup();
        else if ( type.equals( "Container" ) )
            mobj = new MapContainer();
        else
            mobj = new MapSymbol();

        String objname = obj.getKey();
        mobj.setName( objname );
        mobj.setObjName( objname );
        mobj.setLabel( objname );

        // WebNMS Field added.
        if(obj.getWebNMS()!=null)
        {
            mobj.setWebNMS(obj.getWebNMS());
        }



        if ( obj.getManaged() ) {
            mobj.setParameter("status", String.valueOf( obj.getStatus() ));
            mobj.setParameter("managed", "true");
        }
        else {
            int unknown = SeverityInfo.getInstance().getUnknown();
            mobj.setParameter("status", String.valueOf( unknown ));
            mobj.setParameter("managed", "false");
        }
        return mobj;
    }

    private void getTopoAPI() {
        try {
		topoapi= (TopoAPI) NmsUtil.getAPI("TopoAPI");//No I18N
            while( topoapi == null) {
		    try
		    {
			    Thread.sleep(200);
		    }
		    catch(Exception e){}
                topoapi = (TopoAPI) NmsUtil.getAPI("TopoAPI");//No I18N
            }
        } catch ( Exception e) {
            NmsLogMgr.MAPERR.fail("Exception while trying to get TopoAPI in SwitchMapFilter", e);
        }
    }
}
