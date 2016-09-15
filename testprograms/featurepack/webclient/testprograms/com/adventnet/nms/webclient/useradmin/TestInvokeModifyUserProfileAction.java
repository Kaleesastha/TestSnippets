//$Id: TestInvokeModifyUserProfileAction.java,v 1.2 2003/12/09 06:29:30 sasikumar Exp $

package com.adventnet.nms.webclient.useradmin;

import com.adventnet.nms.authentication.UserConfigAPI;
import com.adventnet.nms.util.NmsUtil;

import org.apache.cactus.*;
import servletunit.struts.CactusStrutsTestCase;

	/** 
 	@test suite="Test Suite for Navigation Component"
 	owner="sasikumar"
 	version="1.0"
 	date="Oct 27th 2003"
 	*/

public class TestInvokeModifyUserProfileAction extends CactusStrutsTestCase
{
	public TestInvokeModifyUserProfileAction(String testname)
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
		addRequestParameter("userName","sas");
        addRequestParameter("userage","0");
        addRequestParameter("passage","0");
        addRequestParameter("AvailableGroups","Users");
        addRequestParameter("ExistingGroups","sasi");
        setRequestPathInfo("/InvokeModifyUserProfileForm");
//        setRequestPathInfo("/InvokeUserToModify");
	}

    /**
	@valid
	@test test_case_id=""
	description="To Check whether action class forwards the request to ForwardPath"
	*/
					 
	
	public void testInvokeModifyUserProfile()
	{
        try{
         UserConfigAPI userConfig = (UserConfigAPI)NmsUtil.getAPI("UserConfigAPI");
         userConfig.createNewAccount("sas","kumar","sasi");
         
        actionPerform();
        verifyForward("modifyUserProfile");
//        verifyForwardPath("/webclient/admin/jsp/ModifyUserProfile.jsp");
		assertEquals("sas",request.getParameter("userName"));
        assertEquals("0",request.getParameter("userage"));
        assertEquals("0",request.getParameter("passage"));
        assertEquals("Users",request.getParameter("AvailableGroups"));
        assertEquals("sasi",request.getParameter("ExistingGroups"));
         userConfig.removeUser("sas");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

	public static junit.framework.Test suite()
	{
		return new junit.framework.TestSuite( TestInvokeModifyUserProfileAction.class );
	}
	
   
    public static void main(String arg[])
	{
   	junit.textui.TestRunner.run(TestInvokeModifyUserProfileAction.class);
	}
}

