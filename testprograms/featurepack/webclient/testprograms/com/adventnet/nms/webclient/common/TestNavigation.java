//$Id: TestNavigation.java,v 1.3 2003/12/12 06:50:37 sasikumar Exp $

package com.adventnet.nms.webclient.common;

import java.net.InetAddress;

import com.meterware.httpunit.HttpUnitOptions;

import junit.textui.TestRunner;

import net.sourceforge.jwebunit.WebTestCase;

public class TestNavigation extends WebTestCase
{
    public TestNavigation(String name) 
    {
        super(name);
    }
    public void setUp()
    {
        getTestContext().setBaseUrl("http://localhost:9090/");
        getTestContext().setEncodingScheme("UTF-8");
		HttpUnitOptions.setDefaultContentType("text/html");
		HttpUnitOptions.setExceptionsThrownOnScriptError( false );
	    HttpUnitOptions.setLoggingHttpHeaders(false); 
    }
   
    public void testnavigation()
    {
try{
         
     //---------- To get HostName ----------
      //String hostName = InetAddress.getLocalHost().getHostName();
      //System.out.println(" Host Name = " + hostName );
      
      //---------- JWebunit Code ----------
    //Login has to be taken care  
    //beginAt("/");
      
        
     beginAt("/LoginPage.do");
     getDialog().setFormParameter("userName","root"); 
     getDialog().setFormParameter("password","public");
     setWorkingForm("loginForm");
     submit("login");
     gotoRootWindow();
     assertTitleEquals("Web NMS 4");
         
      gotoFrame("leftTop");
      //------Hardcoding the excepted result------
      String leftFrameurl ="http://localhost:9090/webclient/common/jsp/logo.jsp";
      assertEquals(leftFrameurl,getDialog().getResponse().getURL().toString());
           
      gotoFrame("leftFrame");
      //------Hardcoding the excepted result------
      String leftframeurl="http://localhost:9090/Tree.do?selectedTab=Maps&selectedNode=ipnet.netmap";
      assertEquals(leftframeurl,getDialog().getResponse().getURL().toString());
      gotoFrame("mainFrame");
      //------Hardcoding the excepted result------
	  String mainframeurl = "http://localhost:9090/map/MapView.do?selectedTab=Maps&selectedNode=ipnet.netmap&viewId=ipnet.netmap&null";

        assertEquals(mainframeurl,getDialog().getResponse().getURL().toString());
	}catch (Exception e) 
                {
                     e.printStackTrace();
                }
      
    }
    
 public void testLink()
    {
    try
    {
        beginAt("/LoginPage.do");
        getDialog().setFormParameter("userName","root"); 
        getDialog().setFormParameter("password","public");
        setWorkingForm("loginForm");
        submit("login");
        gotoRootWindow();
        assertTitleEquals("Web NMS 4");
        gotoFrame("leftFrame");
		assertLinkPresentWithText("IPnet");
        assertLinkPresentWithText("Routers");
        assertLinkPresentWithText("Switches");
        assertLinkPresentWithText("Printers");
		
        
      	gotoFrame("mainFrame");
        assertLinkPresentWithText("Fault Management");
        assertLinkPresentWithText("Performance");
        assertLinkPresentWithText("Network Database");
        assertLinkPresentWithText("SNMP Tools");
        assertLinkPresentWithText("Admin");
        assertLinkPresentWithText("Personalize");
      	assertLinkPresentWithText("Logout");
        
    }catch (Exception e) 
                {
                     e.printStackTrace();
                }
    
     }

public void testURLFault()
    {
    try
    {
        beginAt("/LoginPage.do");
        getDialog().setFormParameter("userName","root"); 
        getDialog().setFormParameter("password","public");
        setWorkingForm("loginForm");
        submit("login");
        gotoRootWindow();
        assertTitleEquals("Web NMS 4");
        
      	gotoFrame("mainFrame");
        clickLinkWithText("Fault Management");
	   	gotoFrame("leftFrame");
        assertLinkPresentWithText("Network Events");
        assertLinkPresentWithText("Alarms");

      	gotoFrame("mainFrame");
        clickLinkWithText("Performance");
	   	gotoFrame("leftFrame");
        assertLinkPresentWithText("Report Types");
    }catch (Exception e) 
                {
                     e.printStackTrace();
                }
    
     }

public void testURLPerformance()
    {
    try
    {
        beginAt("/LoginPage.do");
        getDialog().setFormParameter("userName","root"); 
        getDialog().setFormParameter("password","public");
        setWorkingForm("loginForm");
        submit("login");
        gotoRootWindow();
        assertTitleEquals("Web NMS 4");
        

      	gotoFrame("mainFrame");
        clickLinkWithText("Performance");
	   	gotoFrame("leftFrame");
        assertLinkPresentWithText("Report Types");
        assertLinkPresentWithText("Receive Traffic");
        assertLinkPresentWithText("Transmit Traffic");
        assertLinkPresentWithText("Rx Utilization");
        assertLinkPresentWithText("Tx Utilization");
        assertLinkPresentWithText("CPU Utilization");

    }catch (Exception e) 
                {
                     e.printStackTrace();
                }
    
     }

public void testURLTopo()
    {
    try
    {
        beginAt("/LoginPage.do");
        getDialog().setFormParameter("userName","root"); 
        getDialog().setFormParameter("password","public");
        setWorkingForm("loginForm");
        submit("login");
        gotoRootWindow();
        assertTitleEquals("Web NMS 4");
        

      	gotoFrame("mainFrame");
        clickLinkWithText("Network Database");
	   	gotoFrame("leftFrame");
        assertLinkPresentWithText("Complete View");
        assertLinkPresentWithText("Networks");
        assertLinkPresentWithText("Nodes");
        assertLinkPresentWithText("Interfaces");
        assertLinkPresentWithText("Switches");
        assertLinkPresentWithText("Routers");

    }catch (Exception e) 
                {
                     e.printStackTrace();
                }
    
     }


public void testURLSnmp()
    {
    try
    {
        beginAt("/LoginPage.do");
        getDialog().setFormParameter("userName","root"); 
        getDialog().setFormParameter("password","public");
        setWorkingForm("loginForm");
        submit("login");
        gotoRootWindow();
        assertTitleEquals("Web NMS 4");
        

      	gotoFrame("mainFrame");
        clickLinkWithText("SNMP Tools");
	   	gotoFrame("leftFrame");
        assertLinkPresentWithText("Mib Manager");

    }catch (Exception e) 
                {
                     e.printStackTrace();
                }
    
     }


public void testURLAdmin()
    {
    try
    {
        beginAt("/LoginPage.do");
        getDialog().setFormParameter("userName","root"); 
        getDialog().setFormParameter("password","public");
        setWorkingForm("loginForm");
        submit("login");
        gotoRootWindow();
        assertTitleEquals("Web NMS 4");
        

      	gotoFrame("mainFrame");
        clickLinkWithText("Admin");
	   	gotoFrame("leftFrame");
        assertLinkPresentWithText("User Admin");
        assertLinkPresentWithText("Add User");
        assertLinkPresentWithText("Modify User profile");
        assertLinkPresentWithText("Remove User");
        assertLinkPresentWithText("Network Admin");

    }catch (Exception e) 
                {
                     e.printStackTrace();
                }
    
     }



	 

    public static junit.framework.Test Suite()
    {
        return new junit.framework.TestSuite(TestNavigation.class);
    }
       public static void main(String args[])
       {
        TestRunner.run(TestNavigation.class);
        }
}
