/**
 * NetworkMapFilter.java
 */
package test;

import com.adventnet.nms.topodb.*;
import com.adventnet.nms.mapdb.*;
import com.adventnet.nms.util.NmsLogMgr;
import com.adventnet.nms.util.NmsUtil;
import com.adventnet.management.log.Log;

import java.util.*;

/** 	This is the interface for filtering map symbols, and
	allows for modifying symbols before entering topology database.
	And also this will get notification when changes made (update/delete)
	in the topology database.
**/
public class OwnerNetworkMapFilter implements ExtendedMapFilter {
 /** 
   * The Vector of MapSymbols,ManagedObject, and MapAPI are passed as arguments.
   * The returned Vector of map symbol is added to the map
   * Note that if the class returns a null or an empty vector then there will
   * be no symbols added for the ManagedObject
   * As of version 2.0 Interface objects (that is objects of class IpAddress ) are 
   * not passed to the filters .
   * Here in this example if the ManagedObject is a Network then we add a
   * map for the Network using the MapAPI methods . The map for the network
   * will basically show all the nodes belonging to the network
   * One could have added additional MapSymbols or deleted the ones passed too
   * This filter is declared in the configuration file conf/mapIcon.data against
   * the type Network . Hence this will be invoked only for Network objects
   */

  public Vector filterMapSymbols(Vector mapSymbs, ManagedObject obj, MapAPI api) {
    if (! (obj instanceof Network) ) return mapSymbs;
    Network netw = (Network) obj ;
    String netname = netw.getName();
    String netowner = netw.getOwnerName();
    if ( netowner == null )
        netowner = "NULL";
    String netmapname = netname+".netmap";
    String mapkey = netmapname;
    if ( netowner != null && !netowner.equalsIgnoreCase("null") )
        mapkey = netmapname + "\t" + netowner;
    try {
      	// map already exists
      	if (api.doesTheMapExist(mapkey)) return mapSymbs;
	  }
	  catch(Exception ex) {
      		NmsLogMgr.MAPERR.fail("Exception in NetworkMapFilter "+ex , ex);
	  }
		
	  if (!obj.getManaged()) return mapSymbs;

	  createCustomMap(netmapname,netname,netowner,api);

    return mapSymbs;     
  }
  
  /** This method will be invoked for update/delete operations
  * of topology database. Refer respective javadocs for more
  * information. For available values of type argument, see the
  * update() method of com.adventnet.nms.topodb.TopoObserver class.
  */
  public void update(String type, ManagedObject obj, MapAPI api)
  {
		if (!(obj instanceof Network))
		{
			return;
		}
		
		String netmapname = obj.getName() + ".netmap";
		if (type.startsWith("Deleted"))
		{
			try
			{
				if (api.doesTheMapExist(netmapname))
				{
					api.deleteMap(netmapname);
				}
			}
			catch(Exception ex)
			{
      				NmsLogMgr.MAPERR.fail("Exception in NetworkMapFilter "+ex , ex);
			}
		}
		if (type.startsWith("Manage"))
		{
			if (obj.getManaged())
			{
				try
				{
					if (! api.doesTheMapExist(netmapname))
					{
						createCustomMap(netmapname,obj.getName(),obj.getOwnerName(),api);
					}
				}
				catch(Exception ex)
				{
      				NmsLogMgr.MAPERR.fail("Exception in NetworkMapFilter "+ex , ex);
				}
			}
		}
  }

  private void createCustomMap(String netmapname, String netname, String netowner, MapAPI api)
  {
      if ( netowner == null )
          netowner = "NULL";
      // the properties of the map which we want to create
	  Properties mapprop = new Properties();
      mapprop.put("label",netname);
      mapprop.put("ownerName",netowner);
      mapprop.put("objName", "null");
	  mapprop.put("menuName", "null");
	  mapprop.put("imageName", "null");
	  mapprop.put("treeIconFileName","images/ipn"+NmsUtil.getImageType());
	  mapprop.put("mapSymbolRenderer", "com.adventnet.nms.mapui.MapSymbolRendererImpl");
          mapprop.put("helpDoc", "help/using_examples/maps/map_filters/mapfilter_eg.html");
	  mapprop.put("autoPlacement", String.valueOf(true));
	  mapprop.put("topology","grid,star,ring,flow");
	  mapprop.put("currentTopology","grid");
	  // The properties of the ManagedObject which should be displayed in this map
	  Properties filterProp = new Properties();
	  // A node can belong to multiple nets and so have many nets as parent
	  // wildcard * and comma separation etc supported in this filter props
	  filterProp.put("parentNet","*"+netname+"*");
      // add the custom map
	  try
	  {
      		if (api.addCustomMap(netmapname,mapprop,filterProp))
      		{
          		NmsLogMgr.MAPUSER.log("Created a map for network "+netmapname, Log.SUMMARY);
          		addMapsUnderNetwork(netmapname, netowner, api);
      		}
   	  } 
	  catch (Exception anye) 
	  {
      		NmsLogMgr.MAPERR.fail("Exception in NetworkMapFilter "+anye , anye);
      }
  }

  private void addMapsUnderNetwork(String networkMapKey, String netowner, MapAPI api) throws Exception
  {
      String network = networkMapKey.substring(0, networkMapKey.indexOf(".netmap"));
      Properties mapprop = new Properties();
      mapprop.put("label","Windows Systems");
      mapprop.put("ownerName",netowner);
      mapprop.put("parentMapKey" , networkMapKey);
      mapprop.put("treeIconFileName","images/windows"+NmsUtil.getImageType());
          mapprop.put("helpDoc", "help/using_examples/maps/map_filters/mapfilter_eg.html");
	  mapprop.put("topology","grid(width=60;height=60;gapX=140;gapY=140),star,ring,flow");
	  mapprop.put("currentTopology","grid");
          mapprop.put("mapSymbolRenderer", "com.adventnet.nms.mapui.MapSymbolRendererImpl_3");
          mapprop.put("backgroundColor","255,255,255");
      // The properties of the ManagedObject which should be displayed in this map
      Properties filterProp = new Properties();
      // A node can belong to multiple nets and so have many nets as parent
      // wildcard * and comma separation etc supported in this filter props
      filterProp.put("parentNet","*"+ network +"*");
      filterProp.put("type", "win*");
      // add the custom map
      if (api.addCustomMap(network+"Windows_Systems_Map",mapprop,filterProp))
      {
          NmsLogMgr.MAPUSER.log("Created the map "+network+"Windows_Systems_Map", Log.SUMMARY);
      }
      mapprop = new Properties();
      mapprop.put("label","Failed Systems");
      mapprop.put("ownerName",netowner);
      mapprop.put("parentMapKey" , networkMapKey);
	  mapprop.put("menuName", "failedobjectsmenu");
      mapprop.put("treeIconFileName","images/burst"+NmsUtil.getImageType());
          mapprop.put("helpDoc", "help/using_examples/maps/map_filters/mapfilter_eg.html");
	  mapprop.put("topology","grid,star,ring,flow");
	  mapprop.put("currentTopology","grid");

      // A node can belong to multiple nets and so have many nets as parent
      // wildcard * and comma separation etc supported in this filter props
      filterProp = new Properties();
      filterProp.put("parentNet","*"+network+"*");
      filterProp.put("stringstatus", "critical,major");
      // add the custom map
      if (api.addCustomMap(network+"Failed_Objects_Map",mapprop,filterProp))
      {
          NmsLogMgr.MAPUSER.log("Created the map "+network+"Failed_Objects_Map", Log.SUMMARY);
      }
  }

}


