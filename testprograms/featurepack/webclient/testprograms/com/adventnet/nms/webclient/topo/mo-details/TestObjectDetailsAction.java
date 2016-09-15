//$Id: TestObjectDetailsAction.java,v 1.3 2003/12/22 18:17:26 srikrishnan Exp $

/**
 * @(#)TestObjectDetailsAction.java
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
//import org.apache.struts.taglib.tiles.ComponentConstants;
//import  org.apache.struts.tiles.ComponentContext;
//import  org.apache.struts.tiles.beans.MenuItem;

//JWebUnit imports
//import net.sourceforge.jwebunit.WebTestCase;

//junit imports
//import junit.framework.*;

// adventnet imports
import com.adventnet.nms.webclient.topo.utils.TopoTestUtils;


public class TestObjectDetailsAction extends CactusStrutsTestCase {


	public TestObjectDetailsAction(String name){

		super(name);

	}

	public void setUp() throws Exception 
	{
		super.setUp();	
		setConfigFile("/webclient/topo/conf/topo-struts-config.xml");
	}

	/**	
	  @test test_case_id="WC-TOPO-OBJ-DTLS-113"
	  test_case_description="To test the proper forward it to the appropriate type definition of MO"
	  test_case_procedure=""
	  expected_result="Action should be forwarded to the corresponding type definition of MO. "
	  severity="ShowStopper"
	  comments=""
	  category="AT"
	  ctc=""   
	 */

	public void testObjectDetailsActionFwdForMO()
	{

		Properties prop = TopoTestUtils.getPropertiesForMOType("ManagedObject");
		String className = prop.getProperty("classname");
		String objName = prop.getProperty("name");
		
		request.setAttribute("businessData",prop);

		setRequestPathInfo("/"+className+"Action.do");

		request.setAttribute("formName","/"+className+"Handler");

		actionPerform();
		//verifyTilesForward("details",className+"Def");
		//verifyTilesForward("details",className+"Page"); // since the fwd name
		//is case sensitive in the struts-config.xml file, hard coding is done.
		verifyTilesForward("details","managedObjectPage");

	}

	/**	
	  @test test_case_id="WC-TOPO-OBJ-DTLS-114"
	  test_case_description="To test the proper forward it to the appropriate type definition of PortObject MO"
	  test_case_procedure=""
	  expected_result="Action should be forwarded to the corresponding type definition of PortObject MO. "
	  severity="ShowStopper"
	  comments=""
	  category="AT"
	  ctc=""   
	 */

	public void testObjectDetailsActionFwdForPortObject()
	{
				
		Properties prop = TopoTestUtils.getPropertiesForMOType("PortObject");
		String className = prop.getProperty("classname");
		String objName = prop.getProperty("name");
		
		request.setAttribute("businessData",prop);

		setRequestPathInfo("/"+className+"Action.do");

		request.setAttribute("formName","/"+className+"Handler");

		actionPerform();
		//verifyTilesForward("details",className+"Def");
		//verifyTilesForward("details",className+"Page");
		verifyTilesForward("details","portObjectPage");

	}

	/**	
	  @test test_case_id="WC-TOPO-OBJ-DTLS-115"
	  test_case_description="To test the proper forward it to the appropriate type definition of TopoObject MO"
	  test_case_procedure=""
	  expected_result="Action should be forwarded to the corresponding type definition of TopoObject MO. "
	  severity="ShowStopper"
	  comments=""
	  category="AT"
	  ctc=""   
	 */

	public void testObjectDetailsActionFwdForTopoObject()
	{
				
		Properties prop = TopoTestUtils.getPropertiesForMOType("TopoObject");
		String className = prop.getProperty("classname");
		String objName = prop.getProperty("name");
		
		request.setAttribute("businessData",prop);

		setRequestPathInfo("/"+className+"Action.do");

		request.setAttribute("formName","/"+className+"Handler");

		actionPerform();
		//verifyTilesForward("details",className+"Def");
		//verifyTilesForward("details",className+"Page");
		verifyTilesForward("details","topoObjectPage");

	}


	/**	
	  @test test_case_id="WC-TOPO-OBJ-DTLS-116"
	  test_case_description="To test the proper forward it to the appropriate type definition of Network MO"
	  test_case_procedure=""
	  expected_result="Action should be forwarded to the corresponding type definition of Network MO. "
	  severity="ShowStopper"
	  comments=""
	  category="AT"
	  ctc=""   
	 */


	public void testObjectDetailsActionFwdForNetwork()
	{
				
		Properties prop = TopoTestUtils.getPropertiesForMOType("Network");
		String className = prop.getProperty("classname");
		String objName = prop.getProperty("name");
		
		request.setAttribute("businessData",prop);

		setRequestPathInfo("/"+className+"Action.do");

		request.setAttribute("formName","/"+className+"Handler");

		actionPerform();
		//verifyTilesForward("details",className+"Def");
		//verifyTilesForward("details",className+"Page");
		verifyTilesForward("details","networkPage");

	}

/*
		System.out.println("______________________________________________________________________"); 
		System.out.println("TABLIST DETAILS ::: >>> "+request.getAttributeNames()); 
		for (java.util.Enumeration en = request.getAttributeNames();en.hasMoreElements();)
		{
			String name = (String) en.nextElement();
			System.out.println("Attribute Names " +name);
			System.out.println("Attribute Names " +name +" Values " +request.getAttribute(name)); 
		}
		System.out.println("______________________________________________________________________"); 

		actionPerform();
		verifyTilesForward("details",className+"Def");
		ComponentContext cc = (ComponentContext)request.getAttribute(ComponentConstants.COMPONENT_CONTEXT);
		//System.out.println("CONTEXT " +cc.getAttribute("tabList"));
		Object o = cc.getAttribute("tabList");
		System.out.println("tabList of " + className+"Def"); 
		if(o instanceof List)
		{
			Iterator i = ((List)o).iterator();
			while(i.hasNext())
			{
				MenuItem item = (MenuItem) i.next();
				System.out.println("Value : " + item.getValue()); 
				System.out.println("ToolTip : " + item.getTooltip()); 
				System.out.println("Icon : " + item.getIcon()); 
				System.out.println("Link : " + item.getLink()); 
				System.out.println(""); 
				System.out.println(""); 
			}
		}
*/

	/**	
	  @test test_case_id="WC-TOPO-OBJ-DTLS-117"
	  test_case_description="To test the proper forward it to the appropriate type definition of IpAddress MO"
	  test_case_procedure=""
	  expected_result="Action should be forwarded to the corresponding type definition of IpAddress MO. "
	  severity="ShowStopper"
	  comments=""
	  category="AT"
	  ctc=""   
	 */


	public void testObjectDetailsActionFwdForIpAddress()
	{
				
		Properties prop = TopoTestUtils.getPropertiesForMOType("IpAddress");
		String className = prop.getProperty("classname");
		String objName = prop.getProperty("name");
		
		request.setAttribute("businessData",prop);

		setRequestPathInfo("/"+className+"Action.do");

		request.setAttribute("formName","/"+className+"Handler");

		actionPerform();
		//verifyTilesForward("details",className+"Def");
		//verifyTilesForward("details",className+"Page");
		verifyTilesForward("details","ipAddressPage");

	}

	/**	
	  @test test_case_id="WC-TOPO-OBJ-DTLS-118"
	  test_case_description="To test the proper forward it to the appropriate type definition of SnmpNode MO"
	  test_case_procedure=""
	  expected_result="Action should be forwarded to the corresponding type definition of SnmpNode MO. "
	  severity="ShowStopper"
	  comments=""
	  category="AT"
	  ctc=""   
	 */

	public void testObjectDetailsActionFwdForSnmpNode()
	{
				
		Properties prop = TopoTestUtils.getPropertiesForMOType("SnmpNode");
		String className = prop.getProperty("classname");
		String objName = prop.getProperty("name");
		
		request.setAttribute("businessData",prop);

		setRequestPathInfo("/"+className+"Action.do");

		request.setAttribute("formName","/"+className+"Handler");

		actionPerform();
		//verifyTilesForward("details",className+"Def");
		//verifyTilesForward("details",className+"Page");
		verifyTilesForward("details","snmpNodePage");

	}

	/**	
	  @test test_case_id="WC-TOPO-OBJ-DTLS-119"
	  test_case_description="To test the proper forward it to the appropriate type definition of SnmpInterface MO"
	  test_case_procedure=""
	  expected_result="Action should be forwarded to the corresponding type definition of SnmpInterface MO. "
	  severity="ShowStopper"
	  comments=""
	  category="AT"
	  ctc=""   
	 */

	public void testObjectDetailsActionFwdForSnmpInterface()
	{
				
		Properties prop = TopoTestUtils.getPropertiesForMOType("SnmpInterface");
		String className = prop.getProperty("classname");
		String objName = prop.getProperty("name");
		
		request.setAttribute("businessData",prop);

		setRequestPathInfo("/"+className+"Action.do");

		request.setAttribute("formName","/"+className+"Handler");

		actionPerform();
		//verifyTilesForward("details",className+"Def");
		//verifyTilesForward("details",className+"Page");
		verifyTilesForward("details","snmpInterfacePage");

	}
	
	/**	
	  @test test_case_id="WC-TOPO-OBJ-DTLS-120"
	  test_case_description="To test the proper forward it to the appropriate type definition of SwitchObject MO"
	  test_case_procedure=""
	  expected_result="Action should be forwarded to the corresponding type definition of SwitchObject MO. "
	  severity="ShowStopper"
	  comments=""
	  category="AT"
	  ctc=""   
	 */

	public void testObjectDetailsActionFwdForSwitchObject()
	{
				
		Properties prop = TopoTestUtils.getPropertiesForMOType("SwitchObject");
		String className = prop.getProperty("classname");
		String objName = prop.getProperty("name");
		
		request.setAttribute("businessData",prop);

		setRequestPathInfo("/"+className+"Action.do");

		request.setAttribute("formName","/"+className+"Handler");

		actionPerform();
		//verifyTilesForward("details",className+"Def");
		//verifyTilesForward("details",className+"Page");
		verifyTilesForward("details","switchObjectPage");

	}
	
	/**	
	  @test test_case_id="WC-TOPO-OBJ-DTLS-121"
	  test_case_description="To test the proper forward it to the appropriate type definition of Printer MO"
	  test_case_procedure=""
	  expected_result="Action should be forwarded to the corresponding type definition of Printer MO. "
	  severity="ShowStopper"
	  comments=""
	  category="AT"
	  ctc=""   
	 */

	public void testObjectDetailsActionFwdForPrinter()
	{
				
		Properties prop = TopoTestUtils.getPropertiesForMOType("Printer");
		String className = prop.getProperty("classname");
		String objName = prop.getProperty("name");
		
		request.setAttribute("businessData",prop);

		setRequestPathInfo("/"+className+"Action.do");

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
		return new junit.framework.TestSuite(TestObjectDetailsAction.class);
	}



	/*
	 * Call Main method.
	 */

	public static void main(String args[])
	{
		junit.textui.TestRunner.run(TestObjectDetailsAction.class);

	}
}
