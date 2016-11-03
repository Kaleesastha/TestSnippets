/* $Id: ServiceMapFilter.java,v 1.3 2010/10/29 13:46:03 swaminathap Exp $   */
package test;

import com.adventnet.nms.topodb.*;
import com.adventnet.nms.mapdb.*;
import com.adventnet.nms.util.*;
import com.adventnet.management.log.Log;
import com.adventnet.nms.mapui.*;
import com.adventnet.nms.severity.*;

import java.util.*;


import com.adventnet.nms.store.NmsStorageException;
import com.adventnet.management.transaction.UserTransactionException; 


/**
 * This is a map filter to create a map link named Services under Maps 
 * tree to view all the services available & the objects belonging to 
 * those services.All the services are created as a sub-link under 
 * Services. By clicking these sub-links we can view objects 
 * corresponding to those services.
 **/

public class ServiceMapFilter implements MapFilter 
{
    String serviceMap = "services.netmap";
    TopoAPI topoApi = null;
    boolean toBeProcessed = false;
    boolean isInitialized = false;

    /**
     * Method which creates the Services link & the sub-links for all the
     * available services.
     **/

    public Vector filterMapSymbols(Vector mapSymbs, ManagedObject obj, MapAPI mapApi) throws NmsStorageException, UserTransactionException



    {
	if(obj instanceof GroupInterface) 
	{
	    toBeProcessed = true;
	    
	}
	
	if (toBeProcessed) 
	{
	    if (! isInitialized) 
	    {
		init(mapApi);
		isInitialized = true;
	    }
	    addMapsUnderServices(serviceMap, mapApi);
	    MapSymbol newsymb = createMapObj(obj);
	    newsymb.setMapName(serviceMap);
	    mapSymbs.addElement(newsymb);
	    toBeProcessed = false;
	}
	return mapSymbs;
    }
    /**
     * Method to create the Services link under maps tree.
     **/

    private void init(MapAPI mapApi) throws NmsStorageException, UserTransactionException



    {
	getTopoAPI();
	try 
	{
	    if (! mapApi.doesTheMapExist(serviceMap)) 
	    {
		Properties p = new Properties();
		p.put("name",serviceMap);
		p.put("label","services");
		p.put("autoPlacement",String.valueOf(true));
		p.put("imageName","world3"+NmsUtil.getImageType());
		p.put("treeIconFileName","images/ipnet_small"+NmsUtil.getImageType());
		p.put("mapSymbolRenderer", "com.adventnet.nms.mapui.MapSymbolRendererImpl_3");
                p.put("topology","grid(width=55;height=55;gapX=120;gapY=130;scroll=vertical)");
                p.put("currentTopology","$grid");
                p.put("backgroundColor","255,255,255");
		p.put("helpDoc","help/developer_guide/using_examples/maps/map_filters/mapfilter_eg.html");
		
		if (mapApi.addMap(serviceMap,p,0))
		{
		    NmsLogMgr.MAPUSER.log("In ServiceMapFilter ."+serviceMap+" created successfully ", Log.SUMMARY);					
		}
		else 
		{
		    NmsLogMgr.MAPERR.fail("In IpnetMapFilter ."+serviceMap+" could not be created " , null);
		}
	    }
	}

	catch(NmsStorageException nse)
	{
		throw nse;
     	}
	catch(UserTransactionException ute)
	{
		throw ute;
	}

	catch (Exception e) 
	{
	    NmsLogMgr.MAPERR.fail("Exception in ServiceMapFilter "+e , e);
	}
    }
    /**
     * Method to get the TopoAPI handle 
     **/
    private void getTopoAPI() 
    {
	try 
	{
		topoApi = (TopoAPI) NmsUtil.getAPI("TopoAPI");//No I18N
	    while (topoApi == null) 
	    {
		    try
		    {
			    Thread.sleep(200);
		    }
		    catch(Exception e){}

		topoApi = (TopoAPI) NmsUtil.getAPI("TopoAPI");//No I18N
	    }
	}
	catch(Exception e) 
	{
	    System.err.println("Exception getTopoApi of NmsUtil " + e);
	}
    }
    
    /**
     * Create a map symbol based on the Topology Object
     **/
    private MapSymbol createMapObj (ManagedObject obj) 
    {
	MapSymbol mobj = new MapSymbol();
	String objname = obj.getKey();
	mobj.setName(objname); 
	mobj.setObjName(objname);
	mobj.setLabel(objname);
	if (obj.getManaged()) 
	{
	    mobj.setParameter("status", String.valueOf(obj.getStatus()));
	    mobj.setParameter("isManaged","true");
	} 
	else 
	{
	    int unknown = SeverityInfo.getInstance().getUnknown();
	    mobj.setParameter("status",String.valueOf(unknown));
	    mobj.setParameter("isManaged","false");
	}
	String iconName = "";
	iconName="ip"+NmsUtil.getImageType();
	if (!iconName.equals("") )
	    mobj.setIconName(iconName);
	return mobj;
    }
    
    /**
     * Creates the sub-links for available services under Service link
    **/

    private void addMapsUnderServices(String serviceMap, MapAPI mapApi) throws NmsStorageException, UserTransactionException{



	try {
	    Vector groups = topoApi.getGroupNames();
	    Enumeration enumerate = groups.elements();
	    while (enumerate.hasMoreElements()) 
	    {
		String mapName = enumerate.nextElement().toString();
		if(! mapApi.doesTheMapExist(mapName)) 
		{
		    Properties mapProp = new Properties();
		    mapProp.put("label", mapName);
		    mapProp.put("parentNode", serviceMap);
		    mapProp.put("treeIconFileName","images/windows"+NmsUtil.getImageType());
                    mapProp.put("imageName","world3"+NmsUtil.getImageType());
                    mapProp.put("mapSymbolRenderer", "com.adventnet.nms.mapui.MapSymbolRendererImpl_3");
                    mapProp.put("topology","grid(width=55;height=55;gapX=120;gapY=130;scroll=vertical)");
                    mapProp.put("currentTopology","$grid");
                    mapProp.put("backgroundColor","255,255,255");
                    mapProp.put("helpDoc","help/developer_guide/using_examples/maps/map_filters/mapfilter_eg.html");
		    
		    Properties filterProp = new Properties();
		    filterProp.put(mapName, "yes");
		    System.out.println("mapName" + mapName);
		    if (mapApi.addCustomMap(mapName, mapProp, filterProp)) {
			NmsLogMgr.MAPUSER.log("Created the map " + mapName, Log.SUMMARY);
		    }
		    else 
		    {
			NmsLogMgr.MAPUSER.log("Error while Creating the map " + mapName, Log.SUMMARY);
		    }
		}
	    }
	}

	catch(NmsStorageException nse)
	{
		throw nse;
     	}
	catch(UserTransactionException ute)
	{
		throw ute;
	}

	catch(Exception e) 
	{
	    System.out.println("Exception while adding maps under services");
	    e.printStackTrace();
	}
    }
}



















