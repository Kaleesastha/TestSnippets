//A program for testing NmsTreeAPI

import java.io.*;
import java.util.*;
import java.util.Vector;
import java.util.Properties;
import java.rmi.RemoteException;
import java.rmi.*;
import java.util.*;
import com.adventnet.nms.fe.common.NmsTreeAPI;
import com.adventnet.nms.fe.common.PanelTreeNode;
import com.adventnet.nms.fe.common.ViewData;
import com.adventnet.nms.fe.common.TableColumn;
import com.adventnet.nms.util.*;
class TestNmsTreeAPI{
public static void main(String[] args){
    NmsTreeAPI t=null;
    try{
        //RMI Lookup is done here
        String name="//localhost/TreeAPI";
        t = (NmsTreeAPI) Naming.lookup(name);
        // panelTreeNode is a datastructure that holds data related to a Node in the Tree
        PanelTreeNode panelNode = new PanelTreeNode("Vinayaka1");
        String nodeType="DEVICE";
        String parent="Alerts";
        String userName="root";
        panelNode.setUserName(userName);
        panelNode.setNodeType(nodeType);
        panelNode.setParent(parent);
        panelNode.setModuleName("Alerts");
        //Properties to be set for Tree or Properties that are stored in PanelProps table
        Properties p = new Properties();
        p.put("TREE-NAME","test");
        p.put("ID","Vinayaka1");
        p.put("Client","All");
        p.put("TREE-NAME","test");
        p.put("ICON-FILE","images/maps.png");
        p.put("PANEL-KEY","MapApplet");
        p.put("MENU-FILE-NAME","mapmenu.xml");
        p.put("MODULE-NAME","Alerts");
        p.put("URL","jsp/GetAlerts.jsp");
        p.put("TARGET","center");
        panelNode.setPanelProperties(p);
        //Properties to be set for CustomView tables
       
        Properties v = new Properties();
        v.put("<VIEWNAME>","test");
        v.put("<MODULE>","Alerts");
        panelNode.setViewProperties(v); 

        System.out.println("addNode result :"+t.addNode(panelNode));
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }
}
    
}



           











