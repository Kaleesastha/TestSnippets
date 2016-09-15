//$Id: TestInvokeAddUserAction.java,v 1.2 2003/12/09 06:29:30 sasikumar Exp $
package com.adventnet.nms.webclient.useradmin;

import java.util.Vector;

import org.apache.cactus.*;
import servletunit.struts.CactusStrutsTestCase;

	/** 
 	@test suite="Test Suite for Navigation Component"
 	owner="sasikumar"
 	version="1.0"
 	date="Sep 10th 2003"
 	*/

public class TestInvokeAddUserAction extends CactusStrutsTestCase
{
    Vector Groups=new  Vector();

	public TestInvokeAddUserAction(String testname)
	{
		super(testname);
    }

	/** 
	* In SetUp method Struts-Config.xml file and RequestPathinfo is referred.
	* Request Parameter is added here. 
	*/
	
	public void setUp() throws Exception
   	{
        super.setUp();
        setConfigFile("/webclient/admin/conf/admin-struts-config.xml");
//        Groups.add(new String("Admin"));
//        Groups.add(new String("User"));
		addRequestParameter("ExistingGroups","Users");
        setRequestPathInfo("/InvokeAddUserForm");
	}

    /**
	@valid
	@test test_case_id=""
	description="To Check whether action class forwards the request to ForwardPath"
	*/
					 
	
	public void testSelectedTab()
	{
        actionPerform();
        verifyForward("addUser");
//		verifyForwardPath("/webclient/admin/jsp/AddUser.jsp");
		assertEquals("Users",request.getParameter("ExistingGroups"));

    }

	public static junit.framework.Test suite()
	{
		return new junit.framework.TestSuite( TestInvokeAddUserAction.class );
	}
	
   
    public static void main(String arg[])
	{
   	junit.textui.TestRunner.run(TestInvokeAddUserAction.class);
	}
}

