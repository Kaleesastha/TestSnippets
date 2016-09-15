//$Id: TestLoginAction.java,v 1.5 2003/12/12 09:08:18 sasikumar Exp $

package com.adventnet.nms.webclient.login;

import servletunit.struts.CactusStrutsTestCase;
import org.apache.cactus.*;

import java.net.InetAddress;
/**
@test suite="Test Suite for Authentication"
owner="sasikumar"
version="1.0"
date="Oct 14th 2003"
*/

public class TestLoginAction extends CactusStrutsTestCase
{

	public TestLoginAction(String testname)
	{
		super(testname);
	}
	public void setUp() throws Exception
	{
	/**
	 * In SetUp method Struts-Config.xml file and RequestPathinfo is referred.
	 *  Request Parameter is added here.
	*/
	   	super.setUp();
		setConfigFile("/webclient/common/conf/struts-config.xml");
		setRequestPathInfo("/jsp/Login");
        
    }
	public void testUserParams()
	{
	 /**
     @valid
	 @test test_case_id="WC-FMW-Auth-017"
	 test_case_description="To Check wheater Action class forwards request to MainLayout
	 */
        addRequestParameter("userName","root");
        addRequestParameter("password","public");
        addRequestParameter("clienttype","html");
        actionPerform();
		verifyForward("login");
        //verifyForwardPath("/webclient/common/jsp/MainLayout.jsp");
        assertEquals("root",session.getAttribute("userName"));
        assertEquals("public",session.getAttribute("password"));
        assertEquals("html",request.getParameter("clienttype"));
    }
    /**
     @valid
	 @test test_case_id="WC-FMW-Auth-018"
	 test_case_description="To Check wheater URLString is constructed
	 */

	 
    public void testconstrutURLString()
    {
       try{
//        String hostName = InetAddress.getLocalHost().getHostName();
        addRequestParameter("userName","root");
        addRequestParameter("password","public");
        addRequestParameter("requestPath","http://localhost:9090/fault/AlarmDetails.do");
        addRequestParameter("clienttype","html");
        addRequestParameter("method","alertpickUp");
        addRequestParameter("entity","IF-192.168.4.212");
        actionPerform();
		System.out.println("---url string--"+request.getAttribute("urlString"));	
        String result="http://localhost:9090/fault/AlarmDetails.do?entity=IF-192.168.4.212&method=alertpickUp&Cactus_Service=CALL_TEST&Cactus_TestMethod=testconstrutURLString&Cactus_AutomaticSession=true&Cactus_TestClass=com.adventnet.nms.webclient.login.TestLoginAction&";
        assertEquals(result,request.getAttribute("urlString"));
     }catch (Exception e) 
                {
                     e.printStackTrace();
                }
       }
       
	public static junit.framework.Test suite()
	{
	    return new junit.framework.TestSuite( TestLoginAction.class );
	}
	
 	public static void main(String arg[])
     {
	     junit.textui.TestRunner.run(TestLoginAction.class);
     }
}
