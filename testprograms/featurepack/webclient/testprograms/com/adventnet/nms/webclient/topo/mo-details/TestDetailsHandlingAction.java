//$Id: TestDetailsHandlingAction.java,v 1.3 2003/12/23 04:47:33 srikrishnan Exp $ 

/**
 * @(#)TestDetailsHandlingAction.java
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


public class TestDetailsHandlingAction extends CactusStrutsTestCase {


	public TestDetailsHandlingAction(String name){

		super(name);

	}

	public void setUp() throws Exception 
	{
		super.setUp();	
		setConfigFile("/webclient/topo/conf/topo-struts-config.xml");
	}

	/**	
	  @test test_case_id="WC-TOPO-OBJ-DTLS-122"
	  test_case_description="To test the proper forward it to the appropriate type definition of MO"
	  test_case_procedure=""
	  expected_result="Action should be forwarded to the corresponding type definition of MO. "
	  severity="ShowStopper"
	  comments=""
	  category="AT"
	  ctc=""   
	 */

	public void testDetailsHandlingActionFwdForMO()
	{

		TopoTestUtils.addObjectFromUtil("ManagedObject");		
		Properties prop = TopoTestUtils.getPropertiesForMOType("ManagedObject");
		String className = prop.getProperty("classname");
		String objName = prop.getProperty("name");
		
		request.setAttribute("businessData",prop);

		setRequestPathInfo("/"+className+"Handler.do");

		request.setAttribute("formName","/"+className+"Handler");

		actionPerform();
		//verifyTilesForward("details",className+"Def");
		//verifyTilesForward("details",className+"Page"); // since the fwd name
		//is case sensitive in the struts-config.xml file, hard coding is done.
		verifyTilesForward("details","managedObjectPage");

	}

	/**	
	  @test test_case_id="WC-TOPO-OBJ-DTLS-123"
	  test_case_description="To test the proper forward it to the appropriate type definition of PortObject MO"
	  test_case_procedure=""
	  expected_result="Action should be forwarded to the corresponding type definition of PortObject MO. "
	  severity="ShowStopper"
	  comments=""
	  category="AT"
	  ctc=""   
	 */

	public void testDetailsHandlingActionFwdForPortObject()
	{
				
		//TopoTestUtils.addObjectFromUtil("PortObject");		
		Properties prop = TopoTestUtils.getPropertiesForMOType("PortObject");
		String className = prop.getProperty("classname");
		String objName = prop.getProperty("name");
		
		request.setAttribute("businessData",prop);

		setRequestPathInfo("/"+className+"Handler.do");

		request.setAttribute("formName","/"+className+"Handler");

		actionPerform();
		//verifyTilesForward("details",className+"Def");
		//verifyTilesForward("details",className+"Page");
		verifyTilesForward("details","portObjectPage");

	}

	/**	
	  @test test_case_id="WC-TOPO-OBJ-DTLS-124"
	  test_case_description="To test the proper forward it to the appropriate type definition of TopoObject MO"
	  test_case_procedure=""
	  expected_result="Action should be forwarded to the corresponding type definition of TopoObject MO. "
	  severity="ShowStopper"
	  comments=""
	  category="AT"
	  ctc=""   
	 */

	public void testDetailsHandlingActionFwdForTopoObject()
	{
		TopoTestUtils.addObjectFromUtil("TopoObject");		
		Properties prop = TopoTestUtils.getPropertiesForMOType("TopoObject");
		String className = prop.getProperty("classname");
		String objName = prop.getProperty("name");
		
		request.setAttribute("businessData",prop);

		setRequestPathInfo("/"+className+"Handler.do");

		request.setAttribute("formName","/"+className+"Handler");

		actionPerform();
		//verifyTilesForward("details",className+"Def");
		//verifyTilesForward("details",className+"Page");
		verifyTilesForward("details","topoObjectPage");

	}

	/**	
	  @test test_case_id="WC-TOPO-OBJ-DTLS-125"
	  test_case_description="To test the proper forward it to the appropriate type definition of Network MO"
	  test_case_procedure=""
	  expected_result="Action should be forwarded to the corresponding type definition of Network MO. "
	  severity="ShowStopper"
	  comments=""
	  category="AT"
	  ctc=""   
	 */

	public void testDetailsHandlingActionFwdForNetwork()
	{
				
		//TopoTestUtils.addObjectFromUtil("Network");		
		Properties prop = TopoTestUtils.getPropertiesForMOType("Network");
		String className = prop.getProperty("classname");
		String objName = prop.getProperty("name");
		
		request.setAttribute("businessData",prop);

		setRequestPathInfo("/"+className+"Handler.do");

		request.setAttribute("formName","/"+className+"Handler");

		System.out.println("______________________________________________________________________"); 
		System.out.println("TABLIST DETAILS ::: >>> "+request.getAttributeNames()); 
		for (java.util.Enumeration en = request.getAttributeNames();en.hasMoreElements();)
		{
			System.out.println("Attribute Names " +en.nextElement()); 
		}
		System.out.println("______________________________________________________________________"); 

		actionPerform();
		//verifyTilesForward("details",className+"Def");
		//verifyTilesForward("details",className+"Page");
		verifyTilesForward("details","networkPage");

	}


	/**	
	  @test test_case_id="WC-TOPO-OBJ-DTLS-126"
	  test_case_description="To test the proper forward it to the appropriate type definition of IpAddress MO"
	  test_case_procedure=""
	  expected_result="Action should be forwarded to the corresponding type definition of IpAddress MO. "
	  severity="ShowStopper"
	  comments=""
	  category="AT"
	  ctc=""   
	 */

	public void testDetailsHandlingActionFwdForIpAddress()
	{
				
		//TopoTestUtils.addObjectFromUtil("IpAddress");		
		Properties prop = TopoTestUtils.getPropertiesForMOType("IpAddress");
		String className = prop.getProperty("classname");
		String objName = prop.getProperty("name");
		
		request.setAttribute("businessData",prop);

		setRequestPathInfo("/"+className+"Handler.do");

		request.setAttribute("formName","/"+className+"Handler");

		actionPerform();
		//verifyTilesForward("details",className+"Def");
		//verifyTilesForward("details",className+"Page");
		verifyTilesForward("details","ipAddressPage");

	}

	/**	
	  @test test_case_id="WC-TOPO-OBJ-DTLS-127"
	  test_case_description="To test the proper forward it to the appropriate type definition of SnmpNode MO"
	  test_case_procedure=""
	  expected_result="Action should be forwarded to the corresponding type definition of SnmpNode MO. "
	  severity="ShowStopper"
	  comments=""
	  category="AT"
	  ctc=""   
	 */

	public void testDetailsHandlingActionFwdForSnmpNode()
	{
				
		//TopoTestUtils.addObjectFromUtil("SnmpNode");		
		Properties prop = TopoTestUtils.getPropertiesForMOType("SnmpNode");
		String className = prop.getProperty("classname");
		String objName = prop.getProperty("name");
		
		request.setAttribute("businessData",prop);

		setRequestPathInfo("/"+className+"Handler.do");

		request.setAttribute("formName","/"+className+"Handler");

		actionPerform();
		//verifyTilesForward("details",className+"Def");
		//verifyTilesForward("details",className+"Page");
		verifyTilesForward("details","snmpNodePage");

	}

	/**	
	  @test test_case_id="WC-TOPO-OBJ-DTLS-128"
	  test_case_description="To test the proper forward it to the appropriate type definition of SnmpInterface MO"
	  test_case_procedure=""
	  expected_result="Action should be forwarded to the corresponding type definition of SnmpInterface MO. "
	  severity="ShowStopper"
	  comments=""
	  category="AT"
	  ctc=""   
	 */

	public void testDetailsHandlingActionFwdForSnmpInterface()
	{
				
		//TopoTestUtils.addObjectFromUtil("SnmpInterface");		
		Properties prop = TopoTestUtils.getPropertiesForMOType("SnmpInterface");
		String className = prop.getProperty("classname");
		String objName = prop.getProperty("name");
		
		request.setAttribute("businessData",prop);

		setRequestPathInfo("/"+className+"Handler.do");

		request.setAttribute("formName","/"+className+"Handler");

		actionPerform();
		//verifyTilesForward("details",className+"Def");
		//verifyTilesForward("details",className+"Page");
		verifyTilesForward("details","snmpInterfacePage");

	}
	
	/**	
	  @test test_case_id="WC-TOPO-OBJ-DTLS-129"
	  test_case_description="To test the proper forward it to the appropriate type definition of SwitchObject MO"
	  test_case_procedure=""
	  expected_result="Action should be forwarded to the corresponding type definition of SwitchObject MO. "
	  severity="ShowStopper"
	  comments=""
	  category="AT"
	  ctc=""   
	 */

	public void testDetailsHandlingActionFwdForSwitchObject()
	{
				
		//TopoTestUtils.addObjectFromUtil("SwitchObject");		
		Properties prop = TopoTestUtils.getPropertiesForMOType("SwitchObject");
		String className = prop.getProperty("classname");
		String objName = prop.getProperty("name");
		
		request.setAttribute("businessData",prop);

		setRequestPathInfo("/"+className+"Handler.do");

		request.setAttribute("formName","/"+className+"Handler");

		actionPerform();
		//verifyTilesForward("details",className+"Def");
		//verifyTilesForward("details",className+"Page");
		verifyTilesForward("details","switchObjectPage");

	}
	
	/**	
	  @test test_case_id="WC-TOPO-OBJ-DTLS-130"
	  test_case_description="To test the proper forward it to the appropriate type definition of Printer MO"
	  test_case_procedure=""
	  expected_result="Action should be forwarded to the corresponding type definition of Printer MO. "
	  severity="ShowStopper"
	  comments=""
	  category="AT"
	  ctc=""   
	 */

	public void testDetailsHandlingActionFwdForPrinter()
	{
				
		//TopoTestUtils.addObjectFromUtil("Printer");		
		Properties prop = TopoTestUtils.getPropertiesForMOType("Printer");
		String className = prop.getProperty("classname");
		String objName = prop.getProperty("name");
		
		request.setAttribute("businessData",prop);

		setRequestPathInfo("/"+className+"Handler.do");

		request.setAttribute("formName","/"+className+"Handler");

		actionPerform();
		//verifyTilesForward("details",className+"Def");
		//verifyTilesForward("details",className+"Page");
		verifyTilesForward("details","printerPage");

	}


	/*
	 * Invoking the test suite
	 */

	public static junit.framework.Test suite()
	{
		return new junit.framework.TestSuite(TestDetailsHandlingAction.class);
	}



	/*
	 * Call Main method.
	 */

	public static void main(String args[])
	{
		junit.textui.TestRunner.run(TestDetailsHandlingAction.class);

	}
}
