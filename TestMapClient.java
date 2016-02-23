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
			System.err.println("setProperties called! "+p);
			MapClientAPI mcapi = new MapClientAPI();
			AddGroup();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	void AddGroup()
	{
	
		try
		{
			System.err.println("Group method called..");
			//DefaultMapModel mapModel = MapClientAPI.getInstance().getMapModel("172.18.84.0.netmap");
			DefaultMapModel mapModel = MapClientAPI.getInstance().loadMap("172.18.84.0.netmap");
			//DefaultMapModel mapModel = MapClientAPI.getInstance().loadMap("testMap.netmap");
			MapApplet applet = new MapApplet(mapModel);
			Properties pr = mapModel.getMapCriteriaProperties();
			System.err.println("Map Criteria Properties "+pr);
			MapGroupComponent grp1 = new MapGroupComponent();
			MapGroupComponent grp2 = new MapGroupComponent();

			MapSymbolComponent major = mapModel.getSymbolByName("jayasingh-0134.csez.zohocorpin.com");
			System.err.println("major is : "+major.getProperties());
			MapSymbolComponent s2 = mapModel.getSymbolByName("arun-temp-w8.csez.zohocorpin.com");
			System.err.println("s2 is : "+s2.getProperties());
			MapSymbolComponent s3 = mapModel.getSymbolByName("avx50211c.csez.zohocorpin.com");
			System.err.println("s3 is : "+s3.getProperties());

			MapSymbolComponent major2 = mapModel.getSymbolByName("balaji-1489.csez.zohocorpin.com");
			MapSymbolComponent major3 = mapModel.getSymbolByName("iyanar-1689.csez.zohocorpin.com");
			MapSymbolComponent major4 = mapModel.getSymbolByName("brajendran-0782.csez.zohocorpin.com");

			grp1.setName("group1");
			grp1.setMapName("172.18.84.0.netmap");
			//grp1.setMapName("testMap.netmap");
			grp1.addSymbol(major);
			grp1.addSymbol(s2);
			grp1.addSymbol(s3);
			mapModel.addGroup(grp1,true);

			grp2.setName("group2");
			grp2.setMapName("172.18.84.0.netmap");
			//grp2.setMapName("testMap.netmap");
			grp2.addSymbol(major2);
			grp2.addSymbol(major3);
			grp2.addSymbol(major4);
			mapModel.addGroup(grp2,true);
		}
		catch(Exception e)
		{
			System.err.println("exception occurred in Add Group :( ");
			e.printStackTrace();
		}
	}
}
