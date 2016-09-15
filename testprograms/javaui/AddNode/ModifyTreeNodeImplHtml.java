package com.adventnet.nms.util;
import  com.adventnet.nms.util.*;
import com.adventnet.nms.startclient.*;
import java.util.*;
//This class extends ModifyTreeNode.java.The method getClientsSupported has been overridden so as to display only "HTML UI Only" option in the "Include Tree Node In"(UI displayed when you click on"Add Node") combo box in the client. 
public class ModifyTreeNodeImplHtml  extends ModifyTreeNode
{
    Vector clients = null;
    public ModifyTreeNodeImplHtml()
    {
        super();
	
        
    }

 public  Vector getClientsSupported()
    {
        
        clients=new Vector();
        clients.addElement("htmlonly");
        return clients;
    }
}
