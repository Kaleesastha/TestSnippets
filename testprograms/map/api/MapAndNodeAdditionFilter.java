/*******************************************************************************************
 *                                                                                         *
 *            MapAndNodeAdditionFilter.java            balaji.r.               27.03.2002  *
 *                                                                                         *
 *******************************************************************************************/

package com.adventnet.nms.topodb;

import java.util.*;

import com.adventnet.nms.topodb.*;
import com.adventnet.nms.mapdb.*;
import com.adventnet.nms.util.*;
import com.adventnet.nms.fe.common.NmsTreeAPI;
import com.adventnet.nms.fe.common.PanelTreeNode;

public class MapAndNodeAdditionFilter implements FoundFilter 
{ 
    int levels=3;
    boolean hierarchical=true;

    public ManagedObject filterObject(ManagedObject obj, TopoAPI api) 
    { 
        // If the object is null, return null 
        if (obj == null) 
            return null; 
        NmsTreeAPI trapi=null;        
        trapi = (NmsTreeAPI) NmsUtil.getAPI("TreeAPI");
        System.out.println(" ******%%%%%%%%%%%%y**Maps Test - Discovery Filter - ObjName-- " + obj); 
        String objName = obj.getName();        
      
        String type = obj.getType(); 
        String mapname = type + "_map"; 
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


        String parent= mapname + ".netmap";
        for(int k=1;k<=levels;k++)
        {
            if(hierarchical)
            {
                parent= mapname + ".netmap";
            }
            String submapname = objName + "_"+k; 
            System.out.println(" submapname :" + submapname);
            mapProp = new Properties(); 
            mapProp.put("name", submapname ); 
            mapProp.put("label", submapname ); 
            mapProp.put("autoPlacement", String.valueOf(true)); 
            mapProp.put("treeIconFileName","images/ipnet_small" + NmsUtil.getImageType()); 
            mapProp.put("topology","grid(width=5;height=7;gapX=13;gapY=15;scroll=vertical;isPercentage=true),star,ring,flow"); 
            mapProp.put("currentTopology", "grid"); 
            mapProp.put("parentMapKey", parent); 
            
            try 
            { 
                if( mapi.doesTheMapExist(parent)) 
                { 
                    Properties customProp = new Properties(); 
                    customProp.put("type", "win*"); 
                    //  customProp.put("mySys", "balajir"); 
                    mapi.addCustomMap(submapname , mapProp, customProp); 
                
                } 
            }
            catch(Exception e)
            {
                System.out.println(" Exception while adding map " + submapname);
                e.printStackTrace();
            }
            mapname = submapname;


            //treenode addition .. balaji.r.

            PanelTreeNode panelNode = new PanelTreeNode(submapname+"_B"+k);
            String nodeType="DEVICE";
            //String parent="Alerts";
            String userName="All";
            panelNode.setUserName(userName);
            panelNode.setNodeType(nodeType);
            panelNode.setParent(parent);
            panelNode.setModuleName("Alerts");
            //Properties to be set for Tree or Properties that are stored in PanelProps table
            Properties p = new Properties();
            p.put("ID",submapname+"_B"+k);
            p.put("Client","All");
            p.put("TREE-NAME",submapname+"_B"+k);
            p.put("ICON-FILE","images/mac.png");
            p.put("PANEL-KEY","AlertApplet");
            p.put("MENU-FILE-NAME","alertsmenu.xml");
            p.put("MODULE-NAME","Alerts");
            p.put("URL","jsp/GetAlerts.jsp");
            p.put("TARGET","center");
            panelNode.setPanelProperties(p);
            //Properties to be set for CustomView tables
            
            Properties v = new Properties();
            v.put("<VIEWNAME>",submapname+"_B"+k);
            v.put("<MODULE>","Alerts");
            panelNode.setViewProperties(v); 
            try
            {
                trapi.addNode(panelNode);
            }
            catch(Exception e)
            {

                System.out.println(" >>>>>>>>>>>>>>>..>>>>>>>>>   "+e);
            }

            //end tree node addition
        }
       
            // return the node with new properties. 
        return obj; 
    } 
    
} 
