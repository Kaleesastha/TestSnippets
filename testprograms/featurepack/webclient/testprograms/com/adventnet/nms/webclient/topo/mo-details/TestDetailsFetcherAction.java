//$Id: TestDetailsFetcherAction.java,v 1.3 2003/12/23 04:57:51 srikrishnan Exp $

/**
 * @(#)TestDetailsFetcherAction.java
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

package com.adventnet.nms.webclient.topo.details;

// Java imports
import java.util.Properties;

// struts imports
import servletunit.struts.CactusStrutsTestCase;

//JWebUnit imports
//import net.sourceforge.jwebunit.WebTestCase;

//junit imports
//import junit.framework.*;

// adventnet imports
import com.adventnet.nms.webclient.topo.utils.TopoTestUtils;


public class TestDetailsFetcherAction extends CactusStrutsTestCase {


	public TestDetailsFetcherAction(String name){

		super(name);

	}

	public void setUp() throws Exception 
	{
		super.setUp();	
		setConfigFile("/webclient/topo/conf/topo-struts-config.xml");
		setRequestPathInfo("/objectdetails.do");
	}


	/**	
	  @test test_case_id="WC-TOPO-OBJ-DTLS-100"
	  test_case_description="To test the proper actionforward it to the appropriate type of MO"
	  test_case_procedure=""
	  expected_result="Action should be forwarded to the corresponding Action to the approrpriate MO. "
	  severity="ShowStopper"
	  comments=""
	  category="AT"
	  ctc=""   
	 */

	public void testDetailsFetcherActionFwdForMO()
	{
		TopoTestUtils.addObjectFromUtil("ManagedObject");
		String objName = TopoTestUtils.getObjectNameForMOType("ManagedObject");

		addRequestParameter("name",objName);


		System.out.println("->> Inside Action-Fwd-For-MO  >> "+ request.getParameter("name")); 

		actionPerform();


		verifyForward("ManagedObject");
		verifyForwardPath("/ManagedObjectAction.do");

	}

	/**	
	  @test test_case_id="WC-TOPO-OBJ-DTLS-102"
	  test_case_description="To test the proper actionforward it to the appropriate type of PortObject MO "
	  test_case_procedure=""
	  expected_result="Action should be forwarded to the corresponding Action to the approrpriate PortObject MO. "
	  severity="ShowStopper"
	  comments=""
	  category="AT"
	  ctc=""   
	 */

	public void testDetailsFetcherActionFwdForPortObject()
	{

		String objName = TopoTestUtils.getObjectNameForMOType("PortObject");

		addRequestParameter("name",objName);


		System.out.println("->>  Inside Action-Fwd-For-PortObject  >> "+ request.getParameter("name")); 

		actionPerform();

		verifyForward("PortObject");
		verifyForwardPath("/PortObjectAction.do");

	}

	/**	
	  @test test_case_id="WC-TOPO-OBJ-DTLS-103"
	  test_case_description="To test the proper actionforward it to the appropriate type of TopoObject MO "
	  test_case_procedure=""
	  expected_result="Action should be forwarded to the corresponding Action to the approrpriate TopoObject MO. "
	  severity="ShowStopper"
	  comments=""
	  category="AT"
	  ctc=""   
	 */

	public void testDetailsFetcherActionFwdForTopoObject()
	{

		TopoTestUtils.addObjectFromUtil("TopoObject");
		String objName = TopoTestUtils.getObjectNameForMOType("TopoObject");

		addRequestParameter("name",objName);


		System.out.println("->>  Inside Action-Fwd-For-TopoObject  >> "+ request.getParameter("name")); 

		actionPerform();

		verifyForward("TopoObject");
		verifyForwardPath("/TopoObjectAction.do");

	}


	/**	
	  @test test_case_id="WC-TOPO-OBJ-DTLS-104"
	  test_case_description="To test the proper actionforward it to the appropriate type of NetworkObject MO "
	  test_case_procedure=""
	  expected_result="Action should be forwarded to the corresponding Action to the approrpriate NetworkObject MO. "
	  severity="ShowStopper"
	  comments=""
	  category="AT"
	  ctc=""   
	 */

	public void testDetailsFetcherActionFwdForNetwork()
	{

		Properties prp = TopoTestUtils.getPropertiesForMOType("Network");

		String objName = prp.getProperty("name");

		request.setAttribute("businessData",prp);

		addRequestParameter("name",objName);


		System.out.println("->>  Inside Action-Fwd-For-Network  >> "+ request.getParameter("name")); 

		actionPerform();

		verifyForward("Network");
		verifyForwardPath("/NetworkAction.do");

	}


	/**	
	  @test test_case_id="WC-TOPO-OBJ-DTLS-105"
	  test_case_description="To test the proper actionforward it to the appropriate type of NodeObject MO "
	  test_case_procedure=""
	  expected_result="Action should be forwarded to the corresponding Action to the approrpriate NodeObject MO. "
	  severity="ShowStopper"
	  comments=""
	  category="AT"
	  ctc=""   
	 */

	public void testDetailsFetcherActionFwdForNode()
	{

		String objName = TopoTestUtils.getObjectNameForMOType("Node");

		addRequestParameter("name",objName);

		System.out.println("->>  Inside Action-Fwd-For-Node  >> "+ request.getParameter("name")); 

		actionPerform();

		verifyForward("Node");
		verifyForwardPath("/NodeAction.do");

	}

	/**	
	  @test test_case_id="WC-TOPO-OBJ-DTLS-106"
	  test_case_description="To test the proper actionforward it to the appropriate type of IpAddress MO "
	  test_case_procedure=""
	  expected_result="Action should be forwarded to the corresponding Action to the approrpriate IpAddress Object MO. "
	  severity="ShowStopper"
	  comments=""
	  category="AT"
	  ctc=""   
	 */

	public void testDetailsFetcherActionFwdForIpAddress()
	{

		String objName = TopoTestUtils.getObjectNameForMOType("IpAddress");

		addRequestParameter("name",objName);

		System.out.println("->>  Inside Action-Fwd-For-IpAddress  >> "+ request.getParameter("name")); 

		actionPerform();

		verifyForward("IpAddress");
		verifyForwardPath("/IpAddressAction.do");

	}

	/**	
	  @test test_case_id="WC-TOPO-OBJ-DTLS-107"
	  test_case_description="To test the proper actionforward it to the appropriate type of SnmpNode MO "
	  test_case_procedure=""
	  expected_result="Action should be forwarded to the corresponding Action to the approrpriate SnmpNode Object MO. "
	  severity="ShowStopper"
	  comments=""
	  category="AT"
	  ctc=""   
	 */

	public void testDetailsFetcherActionFwdForSnmpNode()
	{

		String objName = TopoTestUtils.getObjectNameForMOType("SnmpNode");

		addRequestParameter("name",objName);

		System.out.println("->>  Inside Action-Fwd-For-SnmpNode  >> "+ request.getParameter("name")); 

		actionPerform();

		verifyForward("SnmpNode");
		verifyForwardPath("/SnmpNodeAction.do");

	}


	/**	
	  @test test_case_id="WC-TOPO-OBJ-DTLS-108"
	  test_case_description="To test the proper actionforward it to the appropriate type of SnmpInterface MO "
	  test_case_procedure=""
	  expected_result="Action should be forwarded to the corresponding Action to the approrpriate SnmpInterface Object MO. "
	  severity="ShowStopper"
	  comments=""
	  category="AT"
	  ctc=""   
	 */

	public void testDetailsFetcherActionFwdForSnmpInterface()
	{

		String objName = TopoTestUtils.getObjectNameForMOType("SnmpInterface");

		addRequestParameter("name",objName);

		System.out.println("->>  Inside Action-Fwd-For-SnmpInterface  >> "+ request.getParameter("name")); 

		actionPerform();

		verifyForward("SnmpInterface");
		verifyForwardPath("/SnmpInterfaceAction.do");

	}

	/**	
	  @test test_case_id="WC-TOPO-OBJ-DTLS-109"
	  test_case_description="To test the proper actionforward it to the appropriate type of SwitchObject MO "
	  test_case_procedure=""
	  expected_result="Action should be forwarded to the corresponding Action to the approrpriate Switch Object MO. "
	  severity="ShowStopper"
	  comments=""
	  category="AT"
	  ctc=""   
	 */

	public void testDetailsFetcherActionFwdForSwitchObject()
	{

		String objName = TopoTestUtils.getObjectNameForMOType("SwitchObject");

		addRequestParameter("name",objName);

		System.out.println("->>  Inside Action-Fwd-For-SwitchObject  >> "+ request.getParameter("name")); 

		actionPerform();

		verifyForward("SwitchObject");
		verifyForwardPath("/SwitchObjectAction.do");

	}

	/**	
	  @test test_case_id="WC-TOPO-OBJ-DTLS-110"
	  test_case_description="To test the proper actionforward it to the appropriate type of Printer Object MO "
	  test_case_procedure=""
	  expected_result="Action should be forwarded to the corresponding Action to the approrpriate Printer Object MO. "
	  severity="ShowStopper"
	  comments=""
	  category="AT"
	  ctc=""   
	 */

	public void testDetailsFetcherActionFwdForPrinter()
	{

		String objName = TopoTestUtils.getObjectNameForMOType("Printer");

		addRequestParameter("name",objName);

		System.out.println("->>  Inside Action-Fwd-For-Printer  >> "+ request.getParameter("name")); 

		actionPerform();

		verifyForward("Printer");
		verifyForwardPath("/PrinterAction.do");

	}

	/**	
	  @test test_case_id="WC-TOPO-OBJ-DTLS-111"
	  test_case_description="To test the proper invocation of the superclass actions if there is no Action mpping for the subclass Action "
	  test_case_procedure=" Remove the SnmpInterface Action entry from the module-struts-config.xml, and check whether the Action has been forwarded to its immediate super class/or its order towards up. In this case, we are checking that, it should check for its immediate superclass IpAddress."
	  expected_result="Action should be forwarded to immediate super class. "
	  severity="ShowStopper"
	  comments=""
	  category="AT"
	  ctc=""   
	 */

	public void testDetailsFetcherActionFwdForSnmpInterfacetoParentObj()
	{

		String objName = TopoTestUtils.getObjectNameForMOType("SnmpInterface");

		addRequestParameter("name",objName);

		//Check for the following print in the nmserr.txt in this case "Unable to find the details page 
		//configured for "+className+" hence forwarding the action to the configuration of it's super class 
		// : (SuperClassName) "

		System.out.println("->>  Inside Action-Fwd-For-SnmpInterface-to-ParentObj  >> "+ request.getParameter("name")); 

		actionPerform();

		verifyForward("IpAddress");
		verifyForwardPath("/IpAddressAction.do");

	}


	/**	
	  @test test_case_id="WC-TOPO-OBJ-DTLS-112"
	  test_case_description="To test the proper actionforward it to the appropriate type of ErrorMessage "
	  test_case_procedure=""
	  expected_result="Action should be forwarded to the corresponding Action to the approrpriate ErrorMessage"
	  severity="ShowStopper"
	  comments=""
	  category="AT"
	  ctc=""   
	 */


	public void testDetailsFetcherActionFwdForErrorPage()
	{

		String objName = TopoTestUtils.getObjectNameForMOType("ErrorPage");

		addRequestParameter("name",objName);

		System.out.println("->>  Inside Action-Fwd-For-ErrorPage  >> "+ request.getParameter("name")); 

		actionPerform();

		verifyForward("onerror");
		verifyForwardPath("/webclient/topo/jsp/detailsError.jsp");

	}

	/*
	   public void testDetailsFetcherActionFwdForMONull()
	   {

	   String objName = null;

	   addRequestParameter("name",objName);

	   System.out.println("->>  Inside Action-Fwd-For-Mo-Null  >> "+ request.getParameter("name")); 

	   actionPerform();

	   verifyForward("Printer");
	   verifyForwardPath("/PrinterAction.do");
	   }
	 */

	/*
	 * Invoking the test suite
	 */

	public static junit.framework.Test suite()
	{
		return new junit.framework.TestSuite(TestDetailsFetcherAction.class);
	}



	/*
	 * Call Main method.
	 */

	public static void main(String args[])
	{
		junit.textui.TestRunner.run(TestDetailsFetcherAction.class);

	}

}
