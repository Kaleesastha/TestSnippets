//$Id: TestMapViewAction.java,v 1.4 2003/12/17 07:30:01 srikrishnan Exp $
package com.adventnet.nms.webclient.map.view;

import java.util.Properties;

import servletunit.struts.CactusStrutsTestCase;


import com.adventnet.nms.webclient.topo.utils.TopoTestUtils;
//import junit.framework.*;


public class TestMapViewAction extends CactusStrutsTestCase
{

	public TestMapViewAction(String name){

		super(name);

	}

	public void setUp() throws Exception 
	{
		super.setUp();	
		setConfigFile("/webclient/map/conf/map-struts-config.xml");

		System.out.println("View ID : " +request.getParameter("viewId"));
		System.out.println("UserName :" +request.getParameter("userName")); 
	}


	/**	
	  @test test_case_id="WC-MAP-VIEW-101"
	  test_case_description="To test the proper actionforward when MapView is invoked"
	  test_case_procedure=""
	  expected_result="Action gets forwarded to the corresponding Tiles forward"
	  severity="ShowStopper"
	  comments="" DEPRECATED since there is no tiles definition to forward
	  category="AT"
	  ctc=""   
	 */
/*
	public void testMapViewFwd()
	{
		setRequestPathInfo("/MapView.do");
		actionPerform();
		verifyTilesForward("MapView","MapViewPage");
		
	}
*/
	/**	
	  @test test_case_id="WC-MAP-VIEW-102"
	  test_case_description="To test the proper actionforward when MapView 
	  is invoked"
	  test_case_procedure=""
	  expected_result="Action gets forwarded to the corresponding 
	  rightLayout.jsp file"
	  severity="ShowStopper"
	  comments=""
	  category="AT"
	  ctc=""   
	 */

	public void testMapViewMsgPage()
	{
		setRequestPathInfo("/MapView");
		request.setAttribute("viewId","ipnet.netmap");

		actionPerform();
		
		verifyForward("MapView");
		verifyForwardPath("MapViewPage");
	}


	/*
	 * Invoking the test suite
	 */

	public static junit.framework.Test suite()
	{
		return new junit.framework.TestSuite(TestMapViewAction.class);
	}


	/*
	 * Call Main method.
	 */

	public static void main(String args[])
	{
		junit.textui.TestRunner.run(TestMapViewAction.class);

	}

}
