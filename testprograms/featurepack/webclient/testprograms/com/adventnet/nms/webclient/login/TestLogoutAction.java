//$Id,$
package com.adventnet.nms.webclient.login;

import servletunit.struts.CactusStrutsTestCase;
import org.apache.cactus.*;

/**
@test suite="Test Suite for Authentication"
owner="sasikumar"
version="1.0"
date="Oct 14th 2003"
*/

public class TestLogoutAction extends CactusStrutsTestCase
{

	public TestLogoutAction(String testname)
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
		setRequestPathInfo("/Logout");
        
    }
	public void testLogout()
	{
	 /**
     @valid
	 @test test_case_id="WC-FMW-AUTH-020"
	 test_case_description="To Check wheater Action class forwards request to Logout
	 */
        try
        {
        //added to test wheater session is invalidated
        session.setAttribute("count","10");
        actionPerform();
		verifyForward("success");
        verifyForwardPath("/webclient/common/jsp/Logout.jsp");
        //In stderr.txt java.lang.IllegalStateException :getAttribute :
        //session already invalidated will be thrown
        session.getAttribute("count");
        }catch ( Exception e ) {
			e.printStackTrace();
		}
    }
       
	public static junit.framework.Test suite()
	{
	    return new junit.framework.TestSuite( TestLogoutAction.class );
	}
	
 	public static void main(String arg[])
     {
	     junit.textui.TestRunner.run(TestLogoutAction.class);
     }
}
