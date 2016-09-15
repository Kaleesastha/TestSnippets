package com.adventnet.nms.topodb;

import java.util.*;

import com.adventnet.nms.topodb.*;
import com.adventnet.nms.mapdb.*;
import com.adventnet.nms.util.*;

public class MapCreationFilter implements FoundFilter 
{ 
    public ManagedObject filterObject(ManagedObject obj, TopoAPI api) 
    { 
        // If the object is null, return null 
        if (obj == null) 
            return null; 
        
        System.out.println(" Maps Test - Discovery Filter - ObjName-- " + obj); 
        String objName = obj.getName();        
      
        String type = obj.getType(); 
        String mapname = type + "_map"; 
        Properties mapProp = new Properties(); 
        mapProp.put("name", mapname); 
        mapProp.put("label", mapname); 
        mapProp.put("autoPlacement", String.valueOf(true)); 
        mapProp.put("treeIconFileName","images/ipnet_small" + NmsUtil.getImageType()); 
        mapProp.put("imageName","world3" + NmsUtil.getImageType()); 
        mapProp.put("topology","grid(width=5;height=7;gapX=13;gapY=15;scroll=vertical;isPercentage=true),star,ring,flow"); 
        mapProp.put("currentTopology", "grid"); 
  
        MapAPI mapi = (MapAPI)NmsUtil.getAPI("MapAPI"); 
        try 
        { 
            if(! mapi.doesTheMapExist(mapname + ".netmap")) 
            { 
                System.out.println(" type-- " +type); 
                Properties customProp = new Properties(); 
                customProp.put("type", type); 
                customProp.put("mySys", "balajir"); 
                mapi.addCustomMap(mapname, mapProp, customProp); 
  
            } 
            else 
            { 
                System.out.println("doesTheMapExist() returns true while adding the node -- " + obj.getName()); 
            } 
        } 
        catch(Exception e) 
        { 
            e.printStackTrace(); 
        } 
  
        // << repeat above, but as a map underneath the other maps  
        String submapname1 = objName + "_1"; 
        System.out.println(" submapname1 :" + submapname1);
        mapProp = new Properties(); 
        mapProp.put("name", submapname1 ); 
        mapProp.put("label", submapname1 ); 
        mapProp.put("autoPlacement", String.valueOf(true)); 
        mapProp.put("imageName","world3" + NmsUtil.getImageType()); 
        mapProp.put("treeIconFileName","images/ipnet_small" + NmsUtil.getImageType()); 
        mapProp.put("topology","grid(width=5;height=7;gapX=13;gapY=15;scroll=vertical;isPercentage=true),star,ring,flow"); 
        mapProp.put("currentTopology", "grid"); 
        mapProp.put("parentMapKey", mapname + ".netmap"); 
  
        try 
        { 
            if( mapi.doesTheMapExist(mapname + ".netmap")) 
            { 
                  Properties customProp = new Properties(); 
                customProp.put("type", type); 
                customProp.put("mySys", "ramanr"); 
                mapi.addCustomMap(submapname1 , mapProp, customProp); 
                
            } 
        }
        catch(Exception e)
        {
            System.out.println(" Exception while adding map " + submapname1);
            e.printStackTrace();
        }
            // << repeat above, but as a map underneath the other maps  
        String submapname2 = objName + "_2"; 
        System.out.println(" submapname2 :" + submapname2);
        mapProp = new Properties(); 
        mapProp.put("name", submapname2 ); 
        mapProp.put("label", submapname2 ); 
        mapProp.put("autoPlacement", String.valueOf(true)); 
        mapProp.put("treeIconFileName","images/ipnet_small" + NmsUtil.getImageType()); 
        mapProp.put("topology","grid(width=5;height=7;gapX=13;gapY=15;scroll=vertical;isPercentage=true),star,ring,flow"); 
        mapProp.put("imageName","world3" + NmsUtil.getImageType()); 
        mapProp.put("currentTopology", "grid"); 
        mapProp.put("parentMapKey", submapname1 + ".netmap"); 
        
        try 
        { 
            if( mapi.doesTheMapExist(submapname1 + ".netmap")) 
            { 
                Properties customProp = new Properties(); 
                customProp.put("type", type); 
                customProp.put("mySys", "balajir"); 
                mapi.addCustomMap(submapname2, mapProp, customProp); 
                
            } 
        }
        catch(Exception e)
        {
            System.out.println(" Exception while adding map " + submapname2);
            e.printStackTrace();
        }  
            // return the node with new properties. 
        return obj; 
    } 
    
} 
