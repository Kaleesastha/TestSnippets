package test;

import java.util.*;
import java.awt.Color;

import com.adventnet.nms.mapui.*;
import com.adventnet.nms.util.*;


public class TestMapClient implements CustomClassInterface
{

	public void setProperties(Properties p[])
	{
		try{
		
			MapClientAPI mcapi = new MapClientAPI();
			DefaultMapModel mapModel = MapClientAPI.getInstance().getMapModel("ipnet.netmap");
			AddSymbol();
			AddLink();
			AddGroup();
			UpdateLink();
			UpdateGroup();
			DeleteGroup();
			AddContainer();
			UpdateContainer();
			DeleteContainer();
			
			Properties pr = mapModel.getMapCriteriaProperties();
			System.out.println("Map Criteria Properties "+pr);
			
			mcapi.openMap("Failed_Objects_Map.netmap");
			mcapi.openMapInNewWindow("192.168.112.0Failed_Objects_Map.netmap");
			mcapi.openMap(null);
			mcapi.openMapInNewWindow(null);
			mcapi.openMap("192.168.110.0.netmap");

		    javax.swing.tree.DefaultTreeModel treemodel = null;
		    DefaultMapModel mapmodel = null;


			treemodel = mcapi.getMapNames();
			if (treemodel != null)
			{
				System.out.println("treemodel not null..");
				System.out.println(treemodel);
			}

			mapmodel = mcapi.loadMap("192.168.9.0.netmap");


			Properties prop = new Properties();
			prop.put("name","tree.netmap");
			prop.put("topology", "tree");
			prop.put("currentTopology", "tree");
			prop.put("mapSymbolRenderer", "com.adventnet.nms.mapui.TreeSymbolRenderer");

			DefaultMapModel	mapModel1 = new DefaultMapModel(prop);
			mapModel1.setMapName("tree.netmap");
			mapModel1.setBackground(new Color(255, 255, 190));
			mapModel1.setContainment(false);
			boolean retVal = mcapi.addMap(mapModel1);
			System.out.println("addMap returns "+retVal);
			MapClientAPI.getInstance().addMap(mapModel1);	

			DefaultMapModel mapmodel1 = mcapi.getMapModel("tree.netmap");

			if (mapmodel1 != null)
			{
				System.out.println("mapmodel1 not null..");
				System.out.println(mapmodel1);
				System.out.println("deleteMap returned " +mcapi.deleteMap("tree.netmap"));
			}
			else
			{
				System.out.println("mapmodel1 is null..");
			}
			
			System.out.println("saveMap returned " +mcapi.saveMap("ipnet.netmap"));

			EditableMap eMap = mcapi.getMapContainer("ipnet.netmap",true);
			System.out.println("getmapcontainer returned "+eMap);
			
			Properties prop1 = mcapi.getMapConfig("Linux");
			System.out.println(prop1);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}

	}
	
			MapSymbolComponent AddSymbol()
			{
				System.out.println("AddSymbol called..");
				DefaultMapModel mapModel = new DefaultMapModel();
				MapSymbolComponent symbol1 = new MapSymbolComponent();
				symbol1.setMapName("ipnet.netmap");
				symbol1.setName("one");
				symbol1.setLabel("priya");
				symbol1.setObjName("priya.india.adventnet.com");
				symbol1.setMenuName("snmpmenu");
				mapModel.addSymbol(symbol1,true);
				
				MapSymbolComponent symbol2 = new MapSymbolComponent();
				symbol2.setMapName("ipnet.netmap");
				symbol2.setName("two");
				symbol2.setLabel("ANU");
				symbol2.setObjName("shailesh.india.adventnet.com");
				symbol2.setMenuName("snmpmenu");
				mapModel.addSymbol(symbol2,true);
				return symbol2;
			}
			
			MapLinkComponent AddLink()
			{
			
				DefaultMapModel mapModel = new DefaultMapModel();
				MapLinkComponent link = new MapLinkComponent();
				try
				{
					link.setSource("one");
					link.setDest("two");
					link.setName("link_for_container");
					link.setMapName("ipnet.netmap");
					boolean linkretval = mapModel.addLink(link,true);
					System.out.println("addLink returns "+linkretval);
				}
				catch(Exception e)
				{
					System.out.println("exception occurred in AddLink :( ");
					e.printStackTrace();
				}
				return link;
			}
			
			void UpdateLink()
			{
				try
				{
					DefaultMapModel mapModel = MapClientAPI.getInstance().getMapModel("ipnet.netmap");
					MapLinkComponent link = mapModel.getLinkByName("link1");
					Properties prop = new Properties();
					prop.put("myProperty","valueeee");
					boolean retVal2= mapModel.updateLink(link.getKey(),prop,true);
					System.out.println("update link status "+retVal2);
					
				}
				catch(Exception e)
				{
					System.out.println("exception occurred in UpdateLink :( ");
					e.printStackTrace();
				}
			}
	
			void AddGroup()
			{
			
				try
				{
					System.out.println("Group method called..");
					DefaultMapModel mapModel = new DefaultMapModel();
					MapGroupComponent grp1 = new MapGroupComponent();


					MapSymbolComponent symbol = new MapSymbolComponent();
					symbol.setLabel("Brinda");
					symbol.setMapName("ipnet.netmap");
					symbol.setName("bmapsymbol");
					symbol.setObjName("brindad.india.adventnet.com");
					symbol.setGroupName("group1");
					mapModel.addSymbol(symbol,true);

					MapSymbolComponent symboll = new MapSymbolComponent();
					symboll.setLabel("priya");
					symboll.setMapName("ipnet.netmap");
					symboll.setName("psymbol");
					symboll.setGroupName("group1");
					symboll.setObjName("priya.india.adventnet.com");
					mapModel.addSymbol(symboll,true);

					grp1.setName("group1");
					grp1.addSymbol(symbol);
					grp1.addSymbol(symboll);
					mapModel.addGroup(grp1,true);

					MapGroupComponent grp2 = new MapGroupComponent();
					MapSymbolComponent symbol3 = new MapSymbolComponent();
					symbol3.setLabel("Symbol3");
					symbol3.setMapName("ipnet.netmap");
					symbol3.setObjName("dhcp");
					symbol3.setName("3");
					symbol3.setGroupName("group2");
					mapModel.addSymbol(symbol3,true);

					MapSymbolComponent symbol4 = new MapSymbolComponent();
					symbol4.setLabel("Symbol4");
					symbol4.setMapName("ipnet.netmap");
					symbol4.setName("4");
					symbol4.setGroupName("group2");
					symbol4.setObjName("renukak.india.adventnet.com");
					mapModel.addSymbol(symbol4,true);

					grp2.setName("group2");
					grp2.addSymbol(symbol3);
					grp2.addSymbol(symbol4);
				}
				catch(Exception e)
				{
					System.out.println("exception occurred in Add Group :( ");
					e.printStackTrace();
				}
			}
			
			void UpdateGroup()
			{
				try
				{
					DefaultMapModel mapModel = MapClientAPI.getInstance().getMapModel("ipnet.netmap");
					MapGroupComponent grp = mapModel.getGroupByName("group2");
					Properties prop = new Properties();
					prop.put("label","Star");
					prop.put("selected","true");
					boolean retVal= mapModel.updateGroup(grp.getKey(),prop,true);
					System.out.println("update group status "+retVal);
				}
				catch(Exception e)
				{
					System.out.println("exception in UpdateGroup :( ");
					e.printStackTrace();
				}
			}
			
			void DeleteGroup()
			{
				try
				{
					DefaultMapModel mapModel = MapClientAPI.getInstance().getMapModel("ipnet.netmap");
					MapGroupComponent grp = mapModel.getGroupByName("group1");
					boolean retVal= mapModel.deleteGroup(grp,true);
					System.out.println("delete group status "+retVal);
					
					
				}
				catch(Exception e)
				{
					System.out.println("exception in DeleteGroup :( ");
					e.printStackTrace();
				}
			}
			
			void AddContainer()
			{
				try
				{
					DefaultMapModel mapModel = MapClientAPI.getInstance().getMapModel("ipnet.netmap");
					MapContainerComponent container = new MapContainerComponent();
					MapSymbolComponent sym_for_container = AddSymbol();
					container.addSymbol(sym_for_container);
					MapLinkComponent link_for_container = AddLink();
					container.addLink(link_for_container);
					container.setTopology("ring");
					container.setName("Shreya");
					container.setMapName("ipnet.netmap");
					boolean cont_bool = mapModel.addContainer(container,true);
					System.out.println("addContainer returned "+cont_bool);
				}
				catch(Exception e)
				{
					System.out.println("Exception in AddContainer :( ");
					e.printStackTrace();
				}
				
			}
			
			void UpdateContainer()
			{
				try
				{
					DefaultMapModel mapModel = MapClientAPI.getInstance().getMapModel("ipnet.netmap");
					MapContainerComponent container = mapModel.getContainerByName("Shreya");
					Properties prop = new Properties();
					prop.put("label","Aditi");
					prop.put("objName","srividya.india.adventnet.com");
					boolean cont_bool = mapModel.updateContainer(container.getKey(),prop,true);
					System.out.println("updateContainer returned "+cont_bool);
				}
				catch(Exception e)
				{
					System.out.println("Exception in updateContainer :( ");
					e.printStackTrace();
				}
							
			}
			
			void DeleteContainer()
			{
				try
				{
					DefaultMapModel mapModel = MapClientAPI.getInstance().getMapModel("ipnet.netmap");
					MapContainerComponent container = mapModel.getContainerByName("Shreya");
					boolean cont_bool = mapModel.deleteContainer(container,true);
					System.out.println("deleteContainer returned "+cont_bool);
				}
				catch(Exception e)
				{
					System.out.println("Exception in deleteContainer :( ");
					e.printStackTrace();
				}
			}
}