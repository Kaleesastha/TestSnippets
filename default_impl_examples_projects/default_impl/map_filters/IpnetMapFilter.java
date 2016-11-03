//$Id: IpnetMapFilter.java,v 1.8.4.6 2013/08/21 10:24:34 wesley Exp $
/**
 * IpnetMapFilter.java
 */
package test;

import com.adventnet.nms.topodb.*;
import com.adventnet.nms.mapdb.*;
import com.adventnet.nms.severity.*;
import com.adventnet.nms.util.*;
import com.adventnet.nms.util.NmsLogMgr;
import com.adventnet.management.log.Log;


import com.adventnet.nms.store.NmsStorageException;
import com.adventnet.management.transaction.UserTransactionException; 

import java.rmi.RemoteException;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

/**
 * This is a map filter for creating a map which has the Network map symbols and
 * the Router map symbols. This also creates and adds a link between a router and
 * all the networks it is attached to.
 **/
public class IpnetMapFilter implements ExtendedMapFilter {
	TopoAPI topoapi = null;
	boolean isInitialized = false;
	String ipnetmap = "ipnet.netmap";

	/**
	 * The Vector of MapSymbols,ManagedObject, and MapAPI are passed as arguments.
	 * The returned Vector of map symbols/links is added to the map
	 * Here in this example we will create a map of networks and routers
	 * called  "ipnet.netmap" . This map will show networks and routers
	 * and links between them
	 * This filter is declared in the configuration file conf/map.filters
	 * and so for every ManagedObject this will be invoked 
	 */

	public Vector filterMapSymbols(Vector mapSymbs, ManagedObject obj, MapAPI api) throws NmsStorageException, UserTransactionException{      



		boolean toBeProcessed = false;
		if (obj instanceof Network) toBeProcessed=true;
		else if (obj instanceof Node) {
			if (((Node)obj).getIsRouter()) toBeProcessed=true;
		}
		// If the object is neither a router nor a network just leave it as it is
		// Do not fiddle with it
		if (toBeProcessed) {
			// Initialize if already not done and add the map
			if (!isInitialized)
			{
				init(api);
				getTopoAPI();
			}
			MapSymbol newsymb = createMapObj(obj);
			newsymb.setMapName(ipnetmap);
			mapSymbs.addElement(newsymb);
			addLinksIfRouter(mapSymbs,obj);
		}
		return mapSymbs;
	}

	/*
	 * Here we will get the TopoAPI reference and also add the ipnet map
	 */

	private synchronized void init(MapAPI mapapi) throws NmsStorageException, UserTransactionException {
		try {
			if (!mapapi.doesTheMapExist(ipnetmap)) {
				Properties p = new Properties();
				p.put("name",ipnetmap);
				p.put("label","ipnet");
				p.put("autoPlacement",String.valueOf(true));
				p.put("imageName","worldmap_2.png");
				p.put("treeIconFileName","images/ipnet_small"+NmsUtil.getImageType());
				p.put("webTreeIcon","ipnet.png");
				p.put("topology","grid,star,ring,flow,3Dimension(linkOverSymbol=true)");
				p.put("currentTopology","3Dimension");
                p.put("helpDoc", "default_impl/map_filters/README.html");
                p.put("mapSymbolRenderer", "{\"html\":\"symbol_label\",\"java\":\"com.adventnet.nms.mapui.MapSymbolRendererImpl_5\"}");
                p.put("mapLinkRenderer", "{\"html\":\"curved\",\"java\":\"com.adventnet.nms.mapui.MapLinkRendererImpl_3\"}");
                p.put("HTML-TABLE","Infrastructure View");
              //User prop for link and symbol highlight
     	         p.put("highlightLinks","true");
		
		//For TabView
		Properties tabprop=new Properties();

		tabprop.put("tabPanels", "Events,Alerts");
		Properties properties1 = new Properties();

		properties1.put("FieldsWanted", "Status=severity=55;Source=source=135;Date=time=155;");
		properties1.put("source","*");
		tabprop.put("EVENT-PROPS", properties1);

		Properties properties2 = new Properties();
		properties2.put("FieldsWanted", "Status=severity=55;Failure Object=entity=135;Alarm Group=groupName=150;Date/Time=modTime=155;Alarm Message=message=275;");
		tabprop.put("ALERT-PROPS", properties2);
		properties2.put("entity","*");
		///Tab view props ends
                
				if (mapapi.addMapWithTabs(ipnetmap,p,0,tabprop))
				{
				//if (mapapi.addMap(ipnetmap,p,0))
					NmsLogMgr.MAPUSER.log("In IpnetMapFilter ."+ipnetmap+" created successfully ", Log.SUMMARY);
				}
				else
				{
					NmsLogMgr.MAPERR.fail("In IpnetMapFilter ."+ipnetmap+" could not be created " , null);
				}
			}

		} catch(NmsStorageException nmse) {
		    throw nmse;
		} catch(UserTransactionException ute ) {
		    throw ute;

		} catch (Exception someexc) {
			NmsLogMgr.MAPERR.fail("In IpnetMapFilter ."+ipnetmap+" could not be created or already exists "+someexc , someexc);
		}
		isInitialized=true;
	}

	/**
	 * This method creates and adds a link between the Router MapSymbol and all the Network
	 * MapSymbol to which this router is attached to. The link object created is just added
	 * to the Vector of map symbols passed.
	 */

	private void addLinksIfRouter(Vector mapSymbs,ManagedObject obj) throws NmsStorageException, UserTransactionException {



		if ( !(obj instanceof Node)) return;
		Node node = (Node) obj;
		if (!node.getIsRouter() ) return ;
		String objname = obj.getKey();
		Properties p = new Properties();
		p.put("source",objname);//No I18N
		p.put("mapName",ipnetmap);//No I18N
		p.put("status",String.valueOf(obj.getStatus()));//No I18N
		p.put("menuName","linkmenu");//No I18N
		Vector ipaddrs = node.getIpaddrs();

		// add a link cirresponding to each interface of the router
		for (int i=0;i<ipaddrs.size();i++)  {
			try {
				IpAddress ipaddr = topoapi.getInterface((String)ipaddrs.elementAt(i));	
                if ( ipaddr == null )
                    continue;
				// by setting the objName we facilitate the automatic update of the
				// link when the interface gets updated
				
		                p.put("objName",ipaddr.getName());//No I18N
				p.put("dest",ipaddr.getParentNet());//No I18N
				//p.put("label",objname+"<--->"+ipaddr.getParentNet());
				p.put("name",ipaddr.getIpAddress()+"---"+ipaddr.getParentNet());
				MapLink link = new MapLink();
                if(ipaddr.getWebNMS()!=null)
                {
                    link.setWebNMS(ipaddr.getWebNMS());
                }

				link.setProperties(p);
				
                if(node != null && node.getIsRouter())
                {
                	link.setLinkType(12);
                }
                  
				// Note we just have to add it to the vector passed to this IpnetMapFilter
				// It is not advisable to add directly using the mapapi methods
				mapSymbs.addElement(link);

			} catch(NmsStorageException nmse) {
			    throw nmse;
			} catch(UserTransactionException ute ) {
			    throw ute;

			}  catch(Exception exep)    { 
				NmsLogMgr.MAPERR.fail("IpnetMapFilter addLinkForRouter exception "+ exep , exep);
			}
		}
	}

	/**
	 * This returns the ToAPI reference
	 */
	private void getTopoAPI() {
		try
		{   
			topoapi = (TopoAPI)NmsUtil.getAPI("TopoAPI");//No I18N
			while( topoapi == null)
			{
				try
				{
				Thread.sleep(200);
				}
				catch(Exception e){}
				topoapi = (TopoAPI)NmsUtil.getAPI("TopoAPI");//No I18N
			}
		}
		catch ( Exception e)
		{
			e.printStackTrace();
		}
	}

	/** Create a map symbol object based on a topology object **/
	private MapSymbol createMapObj (ManagedObject obj) {
		MapSymbol mobj = new MapSymbol();
		String objname = obj.getKey();
		mobj.setName(objname); 
        if(obj.getWebNMS()!=null)
        {
            mobj.setWebNMS(obj.getWebNMS());
        }


		mobj.setObjName(objname);
		mobj.setLabel(objname);
		if (obj.getManaged()) {
			mobj.setParameter("status", String.valueOf(obj.getStatus()));
			mobj.setParameter("managed","true");
		} else {
			int unknown = SeverityInfo.getInstance().getUnknown();
			mobj.setParameter("status",String.valueOf(unknown));
			//mobj.setParameter("status", "unknown");
			mobj.setParameter("managed","false");
		}
		int objtype = 1;
		String iconName = "";
		if (obj instanceof Network) {
			if (((Network)obj).getDiscover()) {
				mobj.setParameter("discover","true");
			} else {
				mobj.setParameter("discover","false");
			}
			objtype = 2;
			iconName="ip"+NmsUtil.getImageType();
		} else if  (obj instanceof Node) {
				objtype=1;
				iconName= "router"+NmsUtil.getImageType();
		}
		mobj.setObjType(objtype);
		if (!iconName.equals("") )
			mobj.setIconName(iconName);
		return setSymbolUserProp(mobj,obj,(MapAPI)NmsUtil.getAPI("MapAPI"),false);
	}

		public void update(String type, ManagedObject obj, MapAPI api)
			throws NmsStorageException, UserTransactionException {
		// TODO Auto-generated method stub
		try {
			if(!type.equalsIgnoreCase("Added")&&!type.equalsIgnoreCase("Deleted"))
			{	
			Vector symbolKeys = api.getSymbolNamesAssociatedWithObject("symbol",obj.getName());
			if(symbolKeys != null && symbolKeys.size()>0)
			{
				for(int i=0;i<=symbolKeys.size()-1;i++)
				{
					MapSymbol sym=(MapSymbol)api.getObject((String)symbolKeys.elementAt(0));
					if(sym != null)
					{
						setSymbolUserProp(sym,obj,api,true);
					}

				}
			}
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private MapSymbol setSymbolUserProp(MapSymbol mobj,ManagedObject mo,MapAPI mapi,boolean update)
	{
		if(mo instanceof Network){
			
			try {
			JSONObject tooltip=null;
			Properties userprops = mobj.getUserProperties();
			String obj=(String) userprops.get("tooltip");
			if(obj!=null)
				tooltip=new JSONObject(obj);
			else
				tooltip = new JSONObject();
			
			Network net =(Network)mo;
			
			tooltip.put("ipAddress",net.getIpAddress());
			tooltip.put("netmask",net.getNetmask());
			tooltip.put("discoveryStatus",net.getDiscoveryStringStatus());
			
			
			Properties toolprops = new Properties();
			toolprops.put("tooltip",tooltip.toString());
			toolprops.put("name",mobj.getName());
			mobj.setProperties(toolprops);
			
			if(update)
			mapi.updateSymbol(mobj.getMapName(),toolprops);
			}
			catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		 	}
			catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NmsStorageException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UserTransactionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}

		return mobj;
	}
}
