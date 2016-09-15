//$Id: TestAddUserAction.java,v 1.2 2003/12/09 06:29:30 sasikumar Exp $

package com.adventnet.nms.webclient.useradmin;

import com.adventnet.nms.authentication.UserConfigAPI;
import com.adventnet.nms.util.NmsUtil;

import org.apache.cactus.*;
import servletunit.struts.CactusStrutsTestCase;

	/** 
 	@test suite="Test Suite for Navigation Component"
 	owner="sasikumar"
 	version="1.0"
 	date="Sep 10th 2003"
 	*/

public class TestAddUserAction extends CactusStrutsTestCase
{
	public TestAddUserAction(String testname)
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
        addRequestParameter("password","kumar");
        addRequestParameter("newGroup","sasi");
        addRequestParameter("exsistingGroups","Users");
        addRequestParameter("passwordExpiryTime","10");
        addRequestParameter("accountExpiryTime","10");
        setRequestPathInfo("/AddUserAction");
	}

    /**
	@valid
	@test test_case_id=""
	description="To Check whether action class forwards the request to ForwardPath"
	*/
					 
	
	public void testAddUser()
	{
        try{
             UserConfigAPI userConfig = (UserConfigAPI)NmsUtil.getAPI("UserConfigAPI");
             userConfig.removeUser("sas");
        actionPerform();
        verifyForward("messagePage");
		//verifyForwardPath(  "/webclient/common/jsp/MessagePage.jsp");
		assertEquals("sas",request.getParameter("userName"));
        assertEquals("kumar",request.getParameter("password"));
        assertEquals("sasi",request.getParameter("newGroup"));
        assertEquals("Users",request.getParameter("exsistingGroups"));  
        assertEquals("10",request.getParameter("passwordExpiryTime"));
        assertEquals("10",request.getParameter("accountExpiryTime"));
       
        userConfig.isUserNamePresent("sasi","sas");
        assertEquals("user is not present",true,userConfig.isUserNamePresent("sasi","sas"));
        //user is removed so that test can be run next time
        userConfig.removeUser("sas");
         }catch(Exception e){
            e.printStackTrace();
        }
    }

	public static junit.framework.Test suite()
	{
		return new junit.framework.TestSuite( TestAddUserAction.class );
	}
	
   
    public static void main(String arg[])
	{
   	junit.textui.TestRunner.run(TestAddUserAction.class);
	}
}

