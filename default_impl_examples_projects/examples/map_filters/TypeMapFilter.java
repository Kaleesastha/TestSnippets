/**
 * TypeMapFilter.java
 */
package test;

import com.adventnet.nms.topodb.*;
import com.adventnet.nms.mapdb.*;
import com.adventnet.nms.severity.*;
import com.adventnet.nms.util.NmsLogMgr;
import com.adventnet.management.log.Log;

import java.util.*;


import com.adventnet.nms.store.NmsStorageException;
import com.adventnet.management.transaction.UserTransactionException; 


/** 	This is the class for filtering map symbols based on
*       their type, and allows for modifying symbols before entering
*       topology database.
*/
public class TypeMapFilter implements MapFilter {
 /**
   * The Vector of MapSymbols,ManagedObject, and MapAPI are passed as arguments.
   * The returned Vector of map symbol is added to the map
   * Note that if the class returns a null or an empty vector then there will
   * be no symbols added for the ManagedObject
   * As of version 2.0 Interface objects (that is objects of class IpAddress ) are
   * not passed to the filters .
   * Here in this example we create a map for the type it belongs to using the MapAPI methods .
   * The map for the type will basically show all the nodes belonging to that particular type
   * eg. Windows95, WindowsNT, CISCO , etc.
   * One could have added additional MapSymbols or deleted the ones passed too
   * Adding MapSymbols can be done by creating them through createMapObj mathod
   * and just adding the MapSymbol to the mapSymbs vector. Deleting MapSymbols
   * can be done by just removing it from the vector.
   * This filter should be declared in the configuration file conf/map.filters .
   * Then this will be invoked for all objects discovered.
   */


  public Vector filterMapSymbols(Vector mapSymbs, ManagedObject obj, MapAPI api) throws NmsStorageException, UserTransactionException



  {
    // get the type of the object eg. Windows95, WindowsNT, snmp-node, etc.
    if ( ! (obj instanceof TopoObject)) return mapSymbs;
    String typeName = ((TopoObject)obj).getType();
    String typeMapName = typeName+".netmap";
    try
    {
        // if map already exists do nothing just return mapSymbs
        if (api.doesTheMapExist(typeMapName)) return mapSymbs;
        // the properties of the map which we want to create
        Properties mapprop = new Properties();
        mapprop.put("label",typeName+" Map");
        mapprop.put("objName", "null");
        mapprop.put("menuName", "null");
        mapprop.put("imageName", "null");
        mapprop.put("autoPlacement", String.valueOf(true));
	    mapprop.put("topology","grid,star,ring,flow");
	    mapprop.put("currentTopology","grid");
	    mapprop.put("helpDoc", "help/developer_guide/customization/maps/using_examples/mapfilter_eg.html");
        // The properties of the ManagedObject which should be displayed in this map
        Properties filterProp = new Properties();
        // the filter criterias
        filterProp.put("type",typeName);
        // add the custom map
        if (api.addCustomMap(typeMapName,mapprop,filterProp))
        {
            NmsLogMgr.MAPUSER.log("Created a map for type "+ typeMapName, Log.SUMMARY);
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

    catch (Exception anye)
    {
        NmsLogMgr.MAPERR.fail("Exception in TypeMapFilter "+anye , anye);
    }
    return mapSymbs;
  }

/**
*    The createMapObj method is not actually being used in this MapFilter
*    example. It is just given to enable users to add their own MapSymbols.
*    What one has to do to make it possible is just creating MapSymbols through
*    this method and add it to the mapSymbs Vector and then return mapSymbs.
*/
  MapSymbol createMapObj (ManagedObject obj)
  {
    MapSymbol mobj = new MapSymbol();
    String objname = obj.getKey();
    mobj.setName(objname);
    mobj.setObjName(objname);
    String shortie = new String(objname);
    int indx = -1;
    if (objname != null) indx = objname.indexOf(".");
    if (indx != -1)
        try {
          Integer.parseInt(objname.substring(0,indx));
        } catch (NumberFormatException ne) {
          shortie=objname.substring(0,indx);  // only if not a number
        }
    mobj.setLabel(shortie);
    if (obj.getManaged())
    {
      mobj.setParameter("status", String.valueOf(obj.getStatus()));
      mobj.setParameter("isManaged","true");
    }
    else
    {
	  int unknown = SeverityInfo.getInstance().getUnknown();	
      mobj.setParameter("status", String.valueOf(unknown));
      //mobj.setParameter("status", "unknown");
      mobj.setParameter("isManaged","false");
    }
    return mobj;
  }

}


