//$Id: TestWebFrameworkUtil.java,v 1.3 2003/12/16 11:11:18 sasikumar Exp $


package com.adventnet.nms.webclient.common;

import java.util.*;
import javax.servlet.http.HttpSession;

import junit.textui.TestRunner;
import junit.framework.*;

import servletunit.struts.CactusStrutsTestCase;
import org.apache.cactus.*;


public class TestWebFrameworkUtil extends CactusStrutsTestCase
{
    public TestWebFrameworkUtil(String testname)
	{
		super(testname);
	}
   public void setUp() 
    {
    }   
    public void testgetSubRootNode()
    {
     /*System.out.println("root value is --->"+WebFrameworkUtil.getInstance().getRootNode("root").getAttribute("ID")); 
     System.out.println("parent node is --->"+WebFrameworkUtil.getInstance().getRootNode("root").getParentNode());
     System.out.println("root node is --->"+WebFrameworkUtil.getInstance().getRootNode("root"));
     System.out.println("subroot node is --->"+WebFrameworkUtil.getInstance().
                                 getSubRootNode(WebFrameworkUtil.getInstance().
                                 getRootNode("root"), "Events").getAttribute("ID"));
      */
    assertEquals("Events",WebFrameworkUtil.getInstance().getSubRootNode(WebFrameworkUtil.getInstance().getRootNode("root"), "Events").getAttribute("ID"));
    
    
    
    }

	public void testgetPathProperties()
    {
   /*  System.out.println("path value is --->"+WebFrameworkUtil.
                    getInstance().getPathProperties(WebFrameworkUtil.getInstance().
                        getRootNode("root"),"Fault","Major"));    
      */
     String Fault="Fault";
     String Alerts="Alerts";
     String Major="Major";
     
     Properties PathProperty =new Properties();
     PathProperty.put(Fault,"Fault Management");                
     //System.out.println("PathProperty value is"+PathProperty);
     assertEquals(PathProperty,WebFrameworkUtil.getInstance().
                      getPathProperties(WebFrameworkUtil.getInstance().
                       getRootNode("root"),"Fault","Alarms"));
    }
	
	public void testgetTreePathList()
    {
       	/**System.out.println("treepath list value is ----"+WebFrameworkUtil.
        getInstance().getTreePathList(WebFrameworkUtil.getInstance().getRootNode("root"),"Fault","Alarms");*/
         
        String Fault="Fault";
        String Alerts="Alerts";
        String Major="Major";
        ArrayList expectedpath=new ArrayList();
        
        expectedpath.add(Fault="Fault");
        //System.out.println("expectedpath"+expectedpath);
           
        assertEquals(expectedpath,WebFrameworkUtil.
                               getInstance().getTreePathList(WebFrameworkUtil.
                               getInstance().getRootNode("root"),"Fault","Alarms"));
    }
	
    public void testgetPathURL()
    {
      /*System.out.println("path url is"+WebFrameworkUtil.getInstance().
	  						getPathURL(WebFrameworkUtil.getInstance().
       						getRootNode("root"),"Fault","Major"));
       */
       Properties PathUrl=new Properties();
       String Fault="Fault";
       String Alerts="Alerts";
       String Major="Major";
     
       PathUrl.put(Fault,"jsp/ShowImage.jsp?imageName=../images/faultmain.png");                
	   assertEquals(PathUrl,WebFrameworkUtil.getInstance().
	  						getPathURL(WebFrameworkUtil.getInstance().
       						getRootNode("root"),"Fault","Major"));
    }
   /** 
	public void testgetTreePath()
    {
       System.out.println("Tree path is"+WebFrameworkUtil.getInstance().
                                    getTreePath(WebFrameworkUtil.getInstance().
                                    getRootNode("root"),"Fault","Major").size());
     
    }
    */
  
    public void testparentTab()
    {
        //System.out.println("Parent tab is"+WebFrameworkUtil.getInstance().getParentTab("Events","root"));
        assertEquals("Fault",WebFrameworkUtil.getInstance().getParentTab("Events","root"));
    }
   
	 public static junit.framework.Test suite()
     {
        return new junit.framework.TestSuite( TestWebFrameworkUtil.class );
     }
        public static void main(String arg[])
     {
	     junit.textui.TestRunner.run(TestWebFrameworkUtil.class);
     }
    
}
