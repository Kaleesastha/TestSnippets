/* $Id: LinkMapFilter.java,v 1.2 2008/12/13 10:39:44 sudharshan Exp $ */
/**
 * LinkMapFilter.java
 */
package test;

import com.adventnet.nms.topodb.*;
import com.adventnet.nms.mapdb.*;
import com.adventnet.nms.severity.*;
import com.adventnet.nms.util.NmsUtil;
import java.util.*;

/** 	This is the class that demonstrates the adding of links
    between objects. The MapLink is identified by its source object,
    destination object and the map those objects belong to.
    It also allows for modifying symbols before entering topology database.
**/
public class LinkMapFilter implements MapFilter {

  private Vector snmpMapObjects;

  private boolean inited = false;

  private String router = null;

  /**
   * The Vector of MapSymbols,ManagedObject, and MapAPI are passed as arguments.
   * The returned Vector of map symbol is added to the map
   * Note that if the class returns a null or an empty vector then there will
   * be no symbols added for the ManagedObject
   * As of version 2.0 Interface objects (that is objects of class IpAddress ) are
   * not passed to the filters .
   * Here in this example if the ManagedObject is SNMP then we set some
   * additional parameters and change the menu to snmpmenu
   * One could have added additional MapSymbols or deleted the ones passed to this
   * This filter is declared in the configuration file conf/map.filters
   * and so every ManagedObject this will be invoked.
   *
   * Note : For this to work you should have the SnmpNodes.netmap created
   *        To create it just add the following lines to conf/maps.conf
   *        <map>
   *        <param name="mapname" value="SnmpNodes.netmap" >
   *        <param name="label" value="Snmp Nodes MAP" >
   *        <param name="imageName" value="world.gif" >
   *        <param name="topology" value="circle" >
   *        <param name="autoPlacement" value="true" >
   *
   *	           <param name="classname" value="Snmp*" >
   *        </map>
   */
  public Vector filterMapSymbols(Vector mapSymbs, ManagedObject obj, MapAPI api)
  {
    // return if not TopoObject
    if (! (obj instanceof TopoObject) ) return mapSymbs;
    // return if not SNMP
    if (!((TopoObject)obj).getIsSNMP()) return mapSymbs;
    if (!inited)
    {
      snmpMapObjects  =new Vector();
      inited=true;
    }
    Vector retvect = new Vector();
    for (int i=0;i<mapSymbs.size();i++) // for each MapSymbol
    {
        MapSymbol mobj = (MapSymbol) mapSymbs.elementAt(i);
        String mapname = mobj.getMapName();
//      See to that SnmpNodes.netmap is created already in maps.conf
        if (mapname.equals("SnmpNodes.netmap"))
        {
            String destin = mobj.getName();
        	addLinkWithRouter(destin,retvect);
        }
        // add custom properties to be displayed in the property form.
        mobj.setParameter("INITIAL_MIBS", "..!mibs!rfc1213-MIB") ;
        mobj.setParameter("community", ((TopoObject)obj).getCommunity()) ;//NO I18N
        // set the menu name of the MapSymbol.
        String menuname = mobj.getMenuName();
        if ( (menuname == null) || (menuname.equals("nodemenu") ) )
        	mobj.setParameter("menuName", "snmpmenu"); // the menu can even be any user menu.
        retvect.addElement(mobj);
    }
    if ( (obj instanceof Node) && ( ((Node)obj).getIsRouter()) )
        addRouterAndLinks(obj,retvect);
    return retvect;
  }


/**
*    Creates a MapSymbol for the router and then adds links
*    from that router to each other object discovered before than
*    that of the router.
*/
  void addRouterAndLinks(ManagedObject obj,Vector retvect)
  {
    if (router != null) return;
    String objname = obj.getKey();
    router = objname;
    MapSymbol routersymb = createRouterObj(obj); // creates Router mapSymbol
    // set the router map symbol's mapname to the custom map.
    routersymb.setMapName("SnmpNodes.netmap");
    retvect.addElement(routersymb);
    for (int i=0;i<snmpMapObjects.size();i++) // for each snmp map object
    {                                  // discovered before than that of router
        addLinkWithRouter((String)snmpMapObjects.elementAt(i),retvect);
        // adds link between the router and the object
    }
  }

/** Create a map object based on a topology object
*/
  MapSymbol createRouterObj (ManagedObject obj)
  {
    MapSymbol mobj = new MapSymbol();
    String objname = obj.getKey();
    mobj.setName(objname);
    mobj.setObjName(objname);
    String shortie = new String(objname);
    int indx = -1;
    if (objname != null) indx = objname.indexOf(".");
    if (indx != -1)
        try
        {
          Integer.parseInt(objname.substring(0,indx));
        }
        catch (NumberFormatException ne)
        {
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
    String menuName = "snmpmenu";
    String iconName = "switch"+NmsUtil.getImageType();
    mobj.setMenuName(menuName);
    mobj.setIconName(iconName);
    return mobj;
  }

/**
*    adds link between the router(source) if any discovered and the destin object.
*    If no router is discovered yet, just adds destin to a vector to be used later
*    when the first router is discovered.
*/
  void addLinkWithRouter(String destin,Vector retvect)
  {
    if (router==null)
    {
        snmpMapObjects.addElement(destin);
        return;
    }
    MapLink link = new MapLink();
    Properties p = new Properties();
    String mapname = "SnmpNodes.netmap";
    p.put("source",router);
    p.put("mapName",mapname);
    p.put("menuName","linkmenu");
    p.put("dest",destin);
    p.put("label",router+"<--->"+destin);
    p.put("name",router+"\t"+destin);
    p.put("objName",router);
    link.setProperties(p);
    retvect.addElement(link);
  }

}

