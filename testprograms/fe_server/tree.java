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
class tree
{

   

public static void main(String[] args){
    NmsTreeAPI t=null;
    try
    {
        //RMI Lookup is done here
        String name="//localhost/TreeAPI";
        t = (NmsTreeAPI) Naming.lookup(name);
        
        // t.updateUserDetail("guest");
         System.out.println("List of customviews for ****: " +t.getChildList("root","Alerts"));
        // System.out.println("Moving ofcustomviews for ***:" +t.moveNode("polo","All","Fault")); 
         //      System.out.println("removal of customviews for ****: " + t.removeNode("mopi","All","Alerts",false));   
        //        System.out.println("The Parent is: " +t.getParent("sdf","mail"));

        //        System.out.println("The number of childNodes: "+t.getChildCount("root","Network Database"));

        //  PanelTreeNode pt = t.getPanelTreeNode("root","Network Database");     
        //     System.out.println("node  " +pt.getPanelProperties());
        // XMLNode xt = t.getTree("root","mail");

        // System.out.println("The Tree :" +xt.getNodeType());
       
            
         
        //        System.out.println("The total number of nodes: "+t.getNodeCount("root") );
        //    System.out.println("Existence Of the node :" +t.nodeExist("root","mail")); 
 //    Thread.sleep(10000); 
       
       /* TableColumn[] columns =new TableColumn[4];
        columns[0] = new TableColumn("priority","PRIORITY222",75);
        columns[1] = new TableColumn("entity","ENTITY",75);    
        columns[2] = new TableColumn("severity","Sverity",75);
          columns[3] = new TableColumn("who","owner",75);
//        columns[4] = new TableColumn("network","Network",75);*/

        //  panelTreeNode is a datastructure that holds data related to a node
        
        PanelTreeNode panelNode = new PanelTreeNode("swam");
        // String nodeType="DEVICE";
        // String parent="Maps"; 
        //  String previousNode="Network Database"; 
            String parent="Network Database";
        String userName="root";
        panelNode.setUserName(userName);
        // panelNode.setNodeIndex(1);     
        // panelNode.setNodeType(nodeType);
        panelNode.setParent(parent);
                 // panelNode.setPreviousNode(previousNode); 
                //   panelNode.setModuleName("Alerts");
                 //Properties to be set for Tree or Properties that are stored in PanelProps table
                   Properties p = new Properties();
                   p.put("TREE-NAME","swam");
                   p.put("ID","swam");
                   p.put("Client","All");
                   p.put("ICON-FILE","images/pc.png");
 /*  p.put("PANEL-KEY","AlertApplet");
//   p.put("MENU-FILE-NAME","alertsmenu.xml");
   //  p.put("MODULE-NAME","Network Database");
   p.put("URL","jsp/GetAlerts.jsp");
   p.put("TREE-POPUP-MENU","Custom Views");
   p.put("TARGET","center");*/
   panelNode.setPanelProperties(p);
   //Properties to be set for CustomView tables
   Properties v = new Properties();
    v.put("<VIEWNAME>","swam");
   //  v.put("<MODULE>","Alerts");
 //  v.put("FieldsWanted",columns);
  panelNode.setViewProperties(v); 
  /*  for(int i=0; i<columns.length;i++)
   {
       System.out.println("*********"+columns[i]);
   }*/
 
     System.out.println("insertNode result :" +t.addNode(panelNode));
    //                            System.out.println("ModifyNode result :" + t.modifyNode(panelNode));
    }    
    catch(Exception e)
    {
        e.printStackTrace();
    }
}
   

}



           











