//$Id: TestRemoveUserAction.java,v 1.3 2003/12/17 06:30:10 sasikumar Exp $

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

public class TestRemoveUserAction extends CactusStrutsTestCase
{
	public TestRemoveUserAction(String testname)
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
        //setRequestPathInfo("/InvokeRemoveUserForm");
        setRequestPathInfo("/RemoveUserAction");
	}

    /**
	@valid
	@test test_case_id=""
	description="To Check whether action class forwards the request to ForwardPath"
	*/
					 
	
	public void testRemoveUser()
	{
        try{
             UserConfigAPI userConfig = (UserConfigAPI)NmsUtil.getAPI("UserConfigAPI");
             userConfig.createNewAccount("sas","kumar","sasi");
        actionPerform();
        verifyForwardPath("message");
        //verifyForwardPath("removeuser");
	//	verifyForwardPath("/webclient/common/jsp/rightLayout.jsp");
		assertEquals("sas",request.getParameter("userName"));
        userConfig.isUserNamePresent("sasi","sas");
        assertEquals("user is  present",false,userConfig.isUserNamePresent("sasi","sas"));
       // user is added so that test can be run next time
        userConfig.createNewAccount("sas","kumar","sasi");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

	public static junit.framework.Test suite()
	{
		return new junit.framework.TestSuite( TestRemoveUserAction.class );
	}
	
   
    public static void main(String arg[])
	{
   	junit.textui.TestRunner.run(TestRemoveUserAction.class);
	}
}

