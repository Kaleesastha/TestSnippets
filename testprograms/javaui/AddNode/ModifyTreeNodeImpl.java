package com.adventnet.nms.util;
import  com.adventnet.nms.util.*;
import com.adventnet.nms.startclient.*;
import java.util.*;
//This class extends ModifyTreeNode.java.The method getClientsSupported has been overridden so as to display only "Java UI Only" option in the "Include Tree Node In"(UI displayed when you click on"Add Node") combo box in the client. 
public class ModifyTreeNodeImpl extends ModifyTreeNode
{
    Vector clients = null;
    public ModifyTreeNodeImpl()
    {
        super();
	
        
    }

 public  Vector getClientsSupported()
    {
        
        clients=new Vector();
        clients.addElement("javaonly");
        return clients;
    }
}
