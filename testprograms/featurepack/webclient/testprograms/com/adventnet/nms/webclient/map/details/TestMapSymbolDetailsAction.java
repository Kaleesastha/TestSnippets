//$Id: TestMapSymbolDetailsAction.java,v 1.1 2003/10/29 06:46:36 srikrishnan Exp $
package com.adventnet.nms.webclient.map.view;

import java.util.Properties;

import servletunit.struts.CactusStrutsTestCase;


import com.adventnet.nms.webclient.topo.utils.TopoTestUtils;
//import junit.framework.*;


public class TestMapSymbolDetailsAction extends CactusStrutsTestCase
{

	public TestMapSymbolDetailsAction(String name){

		super(name);

	}

	public void setUp() throws Exception 
	{
		super.setUp();	
		setConfigFile("/webclient/map/conf/map-struts-config.xml");

		System.out.println("View ID" +request.getParameter("viewId")); 
		System.out.println("UserName" +request.getParameter("userName")); 
	}



	/**	
	  @test test_case_id="WC-MAP-SYMB-DETAILS-501"
	  test_case_description="To test the proper actionforward when MapSymbol
	  details is invoked"
	  test_case_procedure=""
	  expected_result="Action gets forwarded to the corresponding Definition file"
	  severity="ShowStopper"
	  comments=""
	  category="AT"
	  ctc=""   
	 */

	public void testMapSymbolDetails()
	{
		setRequestPathInfo("/mapSymbolDetails.do");
		Properties prp = TopoTestUtils.getPropertiesForMOType("Node");
		request.setAttribute("businessdata",prp);
		actionPerform();
		verifyTilesForward("details","MapSymbolDetailsDef");
	}

	/**	
	  @test test_case_id="WC-MAP-SYMB-DETAILS-502"
	  test_case_description="To test the proper actionforward when 
	  MapSymboldetailshandler is invoked"
	  test_case_procedure=""
	  expected_result="Action gets forwarded to the corresponding Tiles
	  definition"
	  severity="ShowStopper"
	  comments=""
	  category="AT"
	  ctc=""   
	 */

	public void testMapSymbolDetailsHandlerPage()
	{
		setRequestPathInfo("/mapSymbolDetailsHandler.do");
		actionPerform();
		verifyTilesForward("details","MapSymbolDetailsDef");
	}


	/*
	 * Invoking the test suite
	 */

	public static junit.framework.Test suite()
	{
		return new junit.framework.TestSuite(TestMapSymbolDetailsAction.class);
	}



	/*
	 * Call Main method.
	 */

	public static void main(String args[])
	{
		junit.textui.TestRunner.run(TestMapSymbolDetailsAction.class);

	}

}
