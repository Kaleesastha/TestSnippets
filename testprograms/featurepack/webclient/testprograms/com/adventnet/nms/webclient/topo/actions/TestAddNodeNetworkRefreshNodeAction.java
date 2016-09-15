//$Id: TestAddNodeNetworkRefreshNodeAction.java,v 1.5 2003/12/17 07:35:58 srikrishnan Exp $

/**
 * @(#)TestAddNodeNetworkRefreshNodeAction.java
 *
 *  Copyright (c) 2003 AdventNet, Inc. All Rights Reserved.
 * Please read the associated COPYRIGHTS file for more details.
 *
 * ADVENTNET, INC. MAKES NO REPRESENTATIONS OR WARRANTIES
 * ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE, OR NON-INFRINGEMENT.  ADVENTNET,
 * INC. SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
 * LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE
 * OR ITS DERIVATIVES.
 */

package com.adventnet.nms.webclient.topo.actions;

// Java imports
//import java.util.*;

// struts imports
import servletunit.struts.CactusStrutsTestCase;

//JWebUnit imports
//import net.sourceforge.jwebunit.WebTestCase;

//junit imports
//import junit.framework.Test;
//import junit.textui.TestRunner;

// adventnet imports
//import com.adventnet.nms.webclient.topo.utils.*;


public class TestAddNodeNetworkRefreshNodeAction extends CactusStrutsTestCase {


	public TestAddNodeNetworkRefreshNodeAction(String name){

		super(name);

	}

	public void setUp() throws Exception 
	{
		super.setUp();	
		setConfigFile("/webclient/topo/conf/topo-struts-config.xml");

	}


	/**	
	  @test test_case_id="WC-TOPO-ACT-FORM-101"
	  test_case_description="To test the proper actionforward when AddNode form 
	  is invoked"
	  test_case_procedure=""
	  expected_result="Action gets forwarded to the corresponding addNodePageDef
	  file"
	  severity="ShowStopper"
	  comments=""
	  category="AT"
	  ctc=""   
	 */
/* This cannot be mapped since this will not be a part of parameter values set
 * in topo-struts-config.xml 

	public void testAddNodePageFwd()
	{
		setRequestPathInfo("/addNode");
		actionPerform();
		verifyTilesForward("addNodePageDef","/webclient/topo/jsp/addNode.jsp");
	}
*/

/*
	/**	
	  @test test_case_id="WC-TOPO-ACT-FORM-102"
	  test_case_description="To test the response page when AddNode form 
	  is invoked"
	  test_case_procedure=""
	  expected_result="Action gets forwarded to the corresponding
	  addNodeResponsePageDef"
	  severity="ShowStopper"
	  comments=""
	  category="AT"
	  ctc=""   
	 */

	public void testAddNodeResponsePageFwd()
	{

		setRequestPathInfo("/AddNodeAction");
		//setInitParameter( "definitions-config" , "/webclient/topo/conf/topo-tiles-defn.xml" );
        //setInitParameter( "definitions-debug" , "0" );
		actionPerform();
		verifyTilesForward("response","addNodeResponsePageDef");

	}


	/**	
	  @test test_case_id="WC-TOPO-ACT-FORM-103"
	  test_case_description="To test the proper actionforward when AddNode form is
	  invoked and a Node is added using the form"
	  test_case_procedure=""
	  expected_result="Action gets forwarded to the corresponding 
	  rightLayout.jsp file"
	  severity="ShowStopper"
	  comments=""
	  category="AT"
	  ctc=""   
	 */


	public void testAddNodeActionFwdWithInputValue()
	{

		setRequestPathInfo("/AddNodeAction.do");

		addRequestParameter("ipAddress","192.168.4.140");

		actionPerform();

		verifyForward("response");
		verifyForwardPath("/webclient/common/jsp/rightLayout.jsp"); 
		// It will forward it to the rightLayout.jsp page.

	}

	/**	
	  @test test_case_id="WC-TOPO-ACT-FORM-104"
	  test_case_description="To test the proper actionforward when AddNetwork form
	  is invoked"
	  test_case_procedure=""
	  expected_result="Action gets forwarded to the corresponding
	  addNetworkPageDef file"
	  severity="ShowStopper"
	  comments=""
	  category="AT"
	  ctc=""   
	 */

	public void testAddNetworkPageFwd()
	{

		setRequestPathInfo("/addNetwork.do");
		actionPerform();
		verifyTilesForward("addNetworkPage","addNetworkPageDef");

	}

	/**	
	  @test test_case_id="WC-TOPO-ACT-FORM-105"
	  test_case_description="To test the proper actionforward when AddNetwork form
	  is invoked"
	  test_case_procedure=""
	  expected_result="Action gets forwarded to the corresponding
	  addNetworkResponsePageDef file"
	  severity="ShowStopper"
	  comments=""
	  category="AT"
	  ctc=""   
	 */

	public void testAddNetworkResponsePageFwd()
	{
		setRequestPathInfo("/AddNetworkAction");
		actionPerform();
		verifyTilesForward("response","addNetworkResponsePageDef");
	}

	/**	
	  @test test_case_id="WC-TOPO-ACT-FORM-106"
	  test_case_description="To test the proper actionforward when AddNode form
	  is invoked and a Netowrk is added using the form" 
	  test_case_procedure=""
	  expected_result="Action gets forwarded to the corresponding
	  rightLayout.jsp file" 
	  severity="ShowStopper" 
	  comments=""
	  category="AT" 
	  ctc=""   
	 */


	public void testAddNetworkActionFwdWithNoInputValue() {

		setRequestPathInfo("/AddNetworkAction.do");

		addRequestParameter("ipAddress","192.168.4.0"); 
		// IpAddress should be a Network 

		actionPerform(); 
		verifyForward("response");
		verifyForwardPath("/webclient/common/jsp/rightLayout.jsp");

	}

	/**	
	  @test test_case_id="WC-TOPO-ACT-FORM-107" 
	  test_case_description="To test
	  the proper actionforward when RefreshNode form is invoked"
	  test_case_procedure="" 
	  expected_result="Action gets forwarded to the
	  corresponding refreshNodePageDef file" 
	  severity="ShowStopper" 
	  comments=""
	  category="AT" ctc=""   
	 */

	public void testRefreshNodeFwd() {

		setRequestPathInfo("/refreshNode.do"); 
		actionPerform();
		verifyTilesForward("refreshNodePage","refreshNodePageDef");

	}

	/**	
	  @test test_case_id="WC-TOPO-ACT-FORM-108"
	  test_case_description="To test the proper actionforward when AddNetwork form
	  is invoked"
	  test_case_procedure=""
	  expected_result="Action gets forwarded to the corresponding
	  refreshNodeResponsePageDef file"
	  severity="ShowStopper"
	  comments=""
	  category="AT"
	  ctc=""   
	 */

	
	public void testRefreshNodeResponsePageFwd()
	{
		setRequestPathInfo("/refreshNodeAction");
		actionPerform();
		verifyTilesForward("response","refreshNodeResponsePageDef");
	}

	/**	
	  @test test_case_id="WC-TOPO-ACT-FORM-109" 
	  test_case_description="To test
	  the proper actionforward when RefreshNode form is invoked and a Node is
	  rediscovered/refreshed using the form" 
	  test_case_procedure=""
	  expected_result="Action gets forwarded to the corresponding
	  rightLayout.jsp file" 
	  severity="ShowStopper" 
	  comments=""
	  category="AT" 
	  ctc=""   
	 */


	public void testRefreshNodeActionFwdWithNoInputValue() {

		setRequestPathInfo("/refreshNodeAction.do");

		addRequestParameter("name","zia"); 
		// It should take name as a parameter.

		actionPerform(); verifyForward("response");
		verifyForwardPath("/webclient/common/jsp/rightLayout.jsp");

	}


/*
* This is to test the ManageUnmanage operation.For the following scenerios,
* like ManageSuccess,UnManageSuccess,ManageUnManage Unauthorized,Operation Not
* initialized,Insufficient Inputs will lead to
* listviewpage. In all the above cases, we have changes only w.r.t to the
* message beeing set as an attribute .
*/
	/**	
	  @test test_case_id="WC-TOPO-ACT-FORM-110"
	  test_case_description="To test the proper actionforward when
	  manageunmanage action is invoked"
	  test_case_procedure=""
	  expected_result="Action gets forwarded to the corresponding listviewpage"
	  severity="ShowStopper"
	  comments=""
	  category="AT"
	  ctc=""   
	 */

	public void testManageSuccess()
	{
		setRequestPathInfo("/manageUnmanage");
		request.setAttribute("success","true");
		actionPerform();
		verifyForward("reloadview");
	}
	
/*
* In the case of ManageUnmanage failure, it leads to the page responsePageDef
*/

	/**	
	  @test test_case_id="WC-TOPO-ACT-FORM-111"
	  test_case_description="To test the proper actionforward when
	  manageunmanage form is invoked and gets failed"
	  test_case_procedure=""
	  expected_result="Action gets forwarded to the corresponding
	  responsePageDef file"
	  severity="ShowStopper"
	  comments=""
	  category="AT"
	  ctc=""   
	 */

	public void testManageUnmanageFailure()
	{
		setRequestPathInfo("/manageUnmanage");
		request.setAttribute("notinitialized","Operation Not initialized");
		String msg ="Unable to manage the device/Unable to Unmanage the device";
	 	request.setAttribute("message",msg);
		actionPerform();
		verifyTilesForward("responsePage","responsePageDef");
	}

/*
*  It will be enough to test only for DeleteObject and not for all of the
*  following , deleteObject Processed, UnAuthorized, NotInitialized,
*  Insufficient inputs, failure . Only change in all the above is the message
*  string that will be passed as an Attribute value. Here we have tested for
*  deleteObject Processed.
*
*/

	/**	
	  @test test_case_id="WC-TOPO-ACT-FORM-112"
	  test_case_description="To test the proper actionforward when DeleteObject
	  is invoked"
	  test_case_procedure=""
	  expected_result="Action gets forwarded to the corresponding listviewpage"
	  severity="ShowStopper"
	  comments=""
	  category="AT"
	  ctc=""   
	 */

	public void testDeleteObjectProcessed()
	{
		setRequestPathInfo("/deleteObject");
		request.setAttribute("processed","delete objects,is beeing processed");
		actionPerform();
		verifyForward("reloadview");
	}

	/**	
	  @test test_case_id="WC-TOPO-ACT-FORM-113"
	  test_case_description="To test the proper actionforward when
	  StartStopDiscovery is invoked"
	  test_case_procedure=""
	  expected_result="Action gets forwarded to the corresponding listviewpage"
	  severity="ShowStopper"
	  comments=""
	  category="AT"
	  ctc=""   
	 */

	public void testStartStopDiscoverySuccess()
	{
		setRequestPathInfo("/startStopDiscovery");
		request.setAttribute("success","StopStop discovery Success");
		actionPerform();
		verifyForward("reloadview");
	}

	/**	
	  @test test_case_id="WC-TOPO-ACT-FORM-114"
	  test_case_description="To test the proper actionforward when
	  StartStopDiscovery form is invoked and gets failed"
	  test_case_procedure=""
	  expected_result="Action gets forwarded to the corresponding Failure
	  Definition"
	  severity="ShowStopper"
	  comments=""
	  category="AT"
	  ctc=""   
	 */

	public void testStartStopDiscoveryFailure()
	{
		setRequestPathInfo("/startStopDiscovery");
		String msg ="StartStop discovery Failed";
	 	request.setAttribute("failed",msg);
		actionPerform();
		verifyTilesForward("responsePage","responsePageDef");
	}

	/*
	 * Invoking the test suite
	 */

	public static junit.framework.Test suite() { return new
	junit.framework.TestSuite(TestAddNodeNetworkRefreshNodeAction.class); }



	/*
	 * Call Main method.
	 */

	public static void main(String args[]) {
		junit.textui.TestRunner.run(TestAddNodeNetworkRefreshNodeAction.class);

	}

}
