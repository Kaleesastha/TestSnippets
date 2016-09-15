package com.adventnet.nms.topodb;

import java.util.*;

import com.adventnet.nms.topodb.*;
import com.adventnet.nms.mapdb.*;
import com.adventnet.nms.util.*;


public class MyDiscFilter implements FoundFilter
{
    

    
    public ManagedObject filterObject(ManagedObject obj, TopoAPI api)
    {
        // If the object is null, return null
        if (obj == null) 
            return null;                  
        if(!(obj instanceof Node)) return obj;

        System.out.println(" ObjName--> " + obj);

        String name = obj.getName();
        String mapname = name + "_map";
        Properties mapProp = new Properties();
        mapProp.put("name", mapname);
        mapProp.put("label", mapname);
        mapProp.put("autoPlacement", String.valueOf(true));
        mapProp.put("treeIconFileName","images/ipnet_small" + NmsUtil.getImageType());
        mapProp.put("topology","grid(width=5;height=7;gapX=13;gapY=15;scroll=vertical;isPercentage=true),star,ring,flow");
        mapProp.put("currentTopology", "grid");

        
        MapAPI mapi = (MapAPI)NmsUtil.getAPI("MapAPI");
        try
        {
        if(! mapi.doesTheMapExist(mapname))
        {

            System.out.println(" name--> " +name);
            mapi.addMap(mapname, mapProp);
            
        }
        else
        {
            System.out.println("doesTheMapExist() returns false while addoing the node -- " + obj.getName());
        }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        // return the node with new properties. 
        return obj;                                    
    }
    
}






