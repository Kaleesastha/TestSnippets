//$Id: RouterMapFilter.java,v 1.3.8.4 2013/07/08 11:51:09 shajahan Exp $

/**

 * RouterMapFilter.java

 */

package test;



import com.adventnet.nms.topodb.*;
import com.adventnet.nms.mapdb.*;
import com.adventnet.nms.util.*;
import com.adventnet.nms.severity.*;
import com.adventnet.nms.util.NmsLogMgr;
import com.adventnet.management.log.Log;
import java.util.*;
import java.rmi.RemoteException;
import com.adventnet.nms.store.NmsStorageException;
import com.adventnet.management.transaction.UserTransactionException; 


public class RouterMapFilter implements MapFilter 

{
    String routermap = "RouterMap.netmap";
    TopoAPI topoapi = null;
 
    /* Here we will add the routermap */
    private void init(MapAPI mapapi) throws NmsStorageException, UserTransactionException
    {        
       try 
        {
           if (!mapapi.doesTheMapExist(routermap))
            {
                Properties p = new Properties();
                p.put("name",routermap);
                p.put("label","Routers");
                p.put("autoPlacement",String.valueOf(true));
                p.put("treeIconFileName","images/routermap"+NmsUtil.getImageType());
                p.put("webTreeIcon","routers.png");
                p.put("topology","$router");
                p.put("currentTopology","router");
                p.put("mapSymbolRenderer", "{\"html\"=\"routerbridge\",\"java\"=\"com.adventnet.nms.mapui.RouterMapRenderer\"}");
                p.put("mapLinkRenderer", "{\"html\"=\"arrow\",\"java\"=\"com.adventnet.nms.mapui.MapLinkRendererImpl\"}");
                p.put("backgroundColor","255,255,255");
                p.put("helpDoc", "default_impl/map_filters/README.html");
	        p.put("anchored", String.valueOf( true ));
            p.put("HTML-TABLE","Infrastructure View"); 
                if (mapapi.addMap(routermap,p,1))
		{
                    NmsLogMgr.MAPUSER.log("In RouterMapFilter ."+routermap+" created successfully ", Log.SUMMARY);
		}
                else
		{
                    NmsLogMgr.MAPERR.fail("In RouterMapFilter ."+routermap+" could not be created. " , null);
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
        catch (Exception someexc) 
        {
            NmsLogMgr.MAPERR.fail("In RouterMapFilter ."+routermap+" could not be created or already exists "+someexc , someexc);
        }
    }


   /** 

   * The Vector of MapSymbols,ManagedObject, and MapAPI are passed as arguments.
   * The returned Vector of map symbols/links is added to the map
   * Here in this example we will create a map of routers and LinkObjects
   * called  "RouterMap.netmap" . This map will show routers 
   * This filter is declared in the configuration file conf/map.filters
   * and so every ManagedObject this will be invoked 
   */

    public Vector filterMapSymbols(Vector mapSymbs, ManagedObject obj, MapAPI api) throws NmsStorageException, UserTransactionException
    {
        if ((obj instanceof Node) && ((Node)obj).getIsRouter()) 
	{
	    init(api);
            try
	    {
		   processRouter( (Node)obj, api, mapSymbs );
	    }
            catch (Exception ex)

            {
                NmsLogMgr.MAPERR.fail("Exception when trying to create MapSymbols in RouterMapFilter : " + ex , ex);
            }
        }

        return mapSymbs;
    }


       private void processRouter ( Node obj, MapAPI api,Vector mapSymbs ) throws Exception
        {
                        addRouterContainer( obj, api ,mapSymbs );
                        addRouterIfSymbols( obj, api ,mapSymbs );
        }

        private void  addRouterContainer ( Node obj, MapAPI api,Vector mapSymbs ) throws Exception
        {
                Properties containerProp = new Properties();
                containerProp.put("name",obj.getName());
                //props.setProperty("label", moDispName+"&"+ipAddress);
                containerProp.put("label",obj.getDisplayName()+"&"+obj.getIpAddress());
                containerProp.put("objName",obj.getName());
//                containerProp.put("menuName","routermenu");
                containerProp.put("topology","$router");
                containerProp.put("currentTopology","router");
                containerProp.put("iconName","map_router.png");
                containerProp.put("mapName",routermap);
                MapContainer routerMapContainer = new MapContainer();
                routerMapContainer.setProperties(containerProp);
	        if(obj.getWebNMS()!=null)
        	{
	            routerMapContainer.setWebNMS(obj.getWebNMS());
        	}

        	if ( obj.getManaged() ) 
		{
	            routerMapContainer.setParameter("status", String.valueOf( obj.getStatus() ));//No Internationalization
        	    routerMapContainer.setParameter("managed", "true");//No Internationalization
        	}
	        else 
		{	
        	    int unknown = SeverityInfo.getInstance().getUnknown();
	            routerMapContainer.setParameter("status", String.valueOf( unknown ));//No Internationalization
        	    routerMapContainer.setParameter("managed", "false");//No Internationalization
	        }

                mapSymbs.addElement(routerMapContainer);
        }

        private void addRouterIfSymbols ( Node obj, MapAPI api ,Vector mapSymbs ) throws Exception
        {
                if ( topoapi == null )
		{
                        getTopoAPI();
		}
                Properties matchCriteria = new Properties();
                matchCriteria.put("parentNode",obj.getName());
                try
                {
                        Vector interfaceObjects = topoapi.getObjects("SnmpInterface",matchCriteria);//No I18N
                        for ( Enumeration en = interfaceObjects.elements(); en.hasMoreElements(); )
                        {
//                                String ipaddr = (String) en.nextElement();
                                //Finding the interface name
                                String label = "";
                                String iconName = "";
				//HBNTODO : getObjectNamesWithProps replaced by getObjects as the former method returns id.
                                SnmpInterface infObj = (SnmpInterface)en.nextElement();//topoapi.getByName(ipaddr);
				String ipaddr = infObj.getName();
                                if ( infObj != null){
                                        label = infObj.getIfDescr();
				}
                                label = label.trim();
                                if ( label.startsWith("Serial") || label.startsWith("serial") ){
                                        iconName = "router_db9.png";//No Internationalization

				}
                                else{
                                        iconName = "router_rj45.png";//No Internationalization

				}
                                Properties p = new Properties();
                                p.put("name",ipaddr);
                                p.put("label",label);
                                p.put("objName",ipaddr);
                                p.put("objType", String.valueOf(1) );
                                p.put("parentName",obj.getName());
                                p.put("iconName",iconName);
                                //Router interface specific menu
                                p.put("ifIndex",String.valueOf(infObj.getIfIndex()));
                                p.put("mapName",routermap);
                                MapSymbol interfaceMapSymbol = new MapSymbol();
                                interfaceMapSymbol.setProperties(p);
			        if(obj.getWebNMS()!=null)
		        	{
	        		    interfaceMapSymbol.setWebNMS(obj.getWebNMS());
		        	}
	
       			 	if ( obj.getManaged() ) 
				{
	        		    interfaceMapSymbol.setParameter("status", String.valueOf( obj.getStatus() ));//No Internationalization

		        	    interfaceMapSymbol.setParameter("managed", "true");//No Internationalization

        			}
			        else 
				{	
		        	    int unknown = SeverityInfo.getInstance().getUnknown();
	        		    interfaceMapSymbol.setParameter("status", String.valueOf( unknown ));//No Internationalization

		        	    interfaceMapSymbol.setParameter("managed", "false");//No Internationalization

	        		}

                                mapSymbs.addElement(interfaceMapSymbol);
                        }

                }
                catch(Exception e)
                {
			NmsLogMgr.MAPERR.fail("In RouterMapFilter.. Exception while adding interface symbols: ", e);//no i18n        
                }

        }

       private void getTopoAPI()
        {
                try
                {
			topoapi = (TopoAPI) NmsUtil.getAPI("TopoAPI");//No I18N
                        while( topoapi == null)
                        {
				try
				{
                                Thread.sleep(200);
				}
				catch(Exception e)
				{}
				topoapi = (TopoAPI) NmsUtil.getAPI("TopoAPI");//No I18N
                        }
                }
                catch ( Exception e)
                {
                       System.err.println("Exception while trying to get TopoAPI in RouterMapFilter"+e);//No Internationalization

                }
        }
    

}
