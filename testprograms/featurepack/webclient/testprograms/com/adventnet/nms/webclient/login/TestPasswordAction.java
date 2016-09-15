//$Id: TestPasswordAction.java,v 1.4 2003/10/15 09:51:36 sasikumar Exp $
package com.adventnet.nms.webclient.login;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import java.net.MalformedURLException;

import servletunit.struts.CactusStrutsTestCase;
import org.apache.cactus.*;

import com.adventnet.security.authorization.AuthorizationAdmin;
import com.adventnet.security.authorization.AuthorizationException;
import com.adventnet.nms.util.NmsUtil;

/**
@test suite="Test Suite for Authentication"
owner="sasikumar"
version="1.0"
date="Oct 14th 2003"
*/

public class TestPasswordAction extends CactusStrutsTestCase
{
    String hostName;
    private AuthorizationAdmin authAdmin;   
    private AuthorizationAdmin authorizationAdmin;   
    
	//Util class To get handle of  NmsAuthAdminAPI which is used to create user
    public AuthorizationAdmin getAuthAdmin()
    {
       AuthorizationAdmin authorizationAdmin;
       authorizationAdmin = (AuthorizationAdmin)NmsUtil.getAPI("NmsAuthAdminAPI");
       if(authorizationAdmin == null) 
       {
        String apiString = "//" + hostName + "/NmsAuthAdminAPI";
        System.out.println( " API String = " + apiString );
        try
            {
                 authorizationAdmin = ( AuthorizationAdmin ) Naming.lookup( apiString );
            } catch ( NotBoundException e ) {
                e.printStackTrace();
            } catch ( MalformedURLException e ) {
                e.printStackTrace();
            } catch ( RemoteException e ) {
                e.printStackTrace();
            }
       if(authorizationAdmin == null) 
            {
            System.out.println( "authorizationAdmin is null" );
            }
         }
            return authorizationAdmin;
    }
    
	public TestPasswordAction(String testname)
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
		setRequestPathInfo("/PasswordExpiry");
        
    }
	public void testUserParams()
	{
	 /**
     @valid
	 @test test_case_id="WC-FMW-AUTH-018"
	 test_case_description="To test wheater changed password is set"
	 */
     try{
      
        //createUser("sasi","kumar","admin");
        this.hostName="localhost"; 
        this.authAdmin=getAuthAdmin();
        authAdmin.createUser("sasi","admin","kumar");
        
        addRequestParameter("userName","sasi");
        addRequestParameter("password","kumar");
        addRequestParameter("confirmpassword","kumar");
        addRequestParameter("pwdExpiryStatus","0");
        
        session.setAttribute("userName","sasi");
        session.setAttribute("password","s");
        actionPerform();
		verifyForward("success");
        
        verifyForwardPath("/webclient/common/jsp/MainLayout.jsp");
        assertEquals("sasi",session.getAttribute("userName"));
        assertEquals("kumar",session.getAttribute("password"));
        
        //change back the Password
        authAdmin.changePassword("sasi","s","kumar");
     }catch ( Exception e ) {
			e.printStackTrace();
		}
    }
       
	public static junit.framework.Test suite()
	{
	    return new junit.framework.TestSuite( TestPasswordAction.class );
	}
	
 	public static void main(String arg[])
     {
	     junit.textui.TestRunner.run(TestPasswordAction.class);
     }
}
