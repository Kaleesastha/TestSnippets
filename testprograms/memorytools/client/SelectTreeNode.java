
import java.util.Enumeration;
import java.util.Properties;

import javax.swing.*;
import javax.swing.tree.*;
import java.util.*;

//WebNMS imports.
import com.adventnet.nms.util.*;

public class SelectTreeNode implements com.adventnet.nms.util.CustomClassInterface,Runnable
{
	
    int timeWait=2;
    
   public void setProperties(Properties p[])
   {
       Properties prop = p[0];
       timeWait=Integer.parseInt(prop.getProperty("timeWait"));
       System.out.println("timeWati =="+timeWait);
       new Thread(new SelectTreeNode()).start();
   }

    public void run()
    {
        while(true)
        {
            JTree tree = NmsUiAPI.getNmsTree();
            TreeModel model = tree.getModel();
            XMLNode node = (XMLNode)model.getRoot();
            selectTreeNodes(node); 
            try
            {
                Thread.sleep(2*timeWait);
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }
        }
    }

    private void selectTreeNodes(XMLNode root)
    {
        try
        {
            Thread.sleep(timeWait*1000);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
        
        String key = (String)root.getAttribute("ID");
        
        if(key != null)
        {
            NmsUiAPI.selectTreeNode(key,true);
            System.out.println(" tree node selected =="+key);
        }
        Vector childNodes = root.getChildNodes();
        for(int i=0;i<childNodes.size();i++)
        {
            XMLNode child = (XMLNode)childNodes.elementAt(i);
            selectTreeNodes(child);
        }
    }
}

 
