
import java.util.Enumeration;
import java.util.Properties;

import javax.swing.*;
import javax.swing.tree.*;
import java.util.*;

//WebNMS imports.
import com.adventnet.nms.util.*;

public class AddCustomView implements com.adventnet.nms.util.CustomClassInterface,Runnable
{
	
    String node = "Alerts";
    int childNumber =5;
    String[] viewNames = null;
    String[] viewIds = null;

    public void setProperties(Properties p[])
    {
        Properties prop = p[0];
        System.out.println(" prop =="+prop);
        node = prop.getProperty("parentKey");
        childNumber = Integer.parseInt(prop.getProperty("childNumber"));
        System.out.println("parenetKey=="+node+" childNumber ++"+childNumber);
        viewNames = new String[childNumber];
        viewIds = new String[childNumber];
        new Thread(this).start();
    }

    public void run()
    {
        //  while(true)
        {
            addCustomView();
            updateCustomView();
            removeCustomView();
            try
            {
                Thread.sleep(60000);
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }
        }
    }

    private void addCustomView()
    {
        for(int i=0;i<viewNames.length;i++)
        {
            String viewName = "king"+i;
            viewNames[i] = viewName;
            Properties criteria = new Properties();
            criteria.setProperty("FieldsWanted","Status=severity;Source=source;FailureObject=entity");
            criteria.setProperty("severity","2");
            
            Properties treeProp = new Properties();
            treeProp.setProperty("TREE-NAME",viewName);
            
            String result = NmsUiAPI.addCustomView(node,node,criteria,treeProp,true);
            NmsUiAPI.selectTreeNode(result,true);
            try
            {
                Thread.sleep(6000);
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }
            viewIds[i]=result;
            System.out.println(" Added Result for === "+viewName+"  "+result);
        }
    }

    private void updateCustomView()
    {
        for(int i=0;i<viewNames.length;i++)
        {
            String viewName = "MOD"+i;
            Properties criteria = new Properties();
            criteria.setProperty("FieldsWanted","Status=severity;Source=source;FailureObject=entity;Id=id");
            criteria.setProperty("severity","5");
            
            Properties treeProp = new Properties();
            treeProp.setProperty("TREE-NAME",viewName);
            
            boolean result = NmsUiAPI.modifyCustomView(node,viewIds[i],criteria,treeProp,true);
            NmsUiAPI.selectTreeNode(viewIds[i],true);
            try
            {
                Thread.sleep(6000);
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }
            System.out.println(" Update Result for === "+viewName+"  "+result);
        }
    }

    private void removeCustomView()
    {
        for(int i=0;i<viewNames.length;i++)
        {
            String viewName=viewNames[i];
            boolean result = NmsUiAPI.removeCustomView(node,viewIds[i],true);
            System.out.println(" Delete Result for === "+viewName+"  "+result);
        }
    }

}

 
