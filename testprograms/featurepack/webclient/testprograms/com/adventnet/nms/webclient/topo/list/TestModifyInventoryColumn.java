//$Id: TestModifyInventoryColumn.java,v 1.2 2003/10/20 15:54:51 srikrishnan Exp $

package com.adventnet.nms.webclient.topo.list;


//java imports
//import java.util.*;

//cactus-test imports
import servletunit.struts.CactusStrutsTestCase;

//junit imports
//import junit.framework.*;


public class TestModifyInventoryColumn extends CactusStrutsTestCase
{


	//Constructor called
	public TestModifyInventoryColumn (String name)
	{
		super(name);
	}



	// Initialization of the ActionClass
	public void setUp() throws Exception
	{
		super.setUp();	
		setConfigFile("/webclient/topo/conf/topo-struts-config.xml");
		setRequestPathInfo("/ModifyTopoCV.do");
		addRequestParameter("selitems","name type status");
		addRequestParameter("viewId","Network Database");

	}


	/* NOTE :
	 *  Automated testcases id starts here from WC-TOPO-ILV-119
	 *  <strong> Please refer to the wc_topo_inv_list_tc.html for the testcases pertaining 
	 *  to the inventory list view
	 *  Testcase id started here is the continuation of the testcase id mentioned in the 
	 *  TestTopoColumnCustomiszer.java 
	 */


	/**	
	 * @test test_case_id="WC-TOPO-ILV-119"
	 * test_case_description="To test the verifyForward of updated.jsp page"
	 * test_case_procedure=""
	 * expected_result="Action should be forwarded to the updated.jsp"
	 * severity="ShowStopper"
	 * comments=""
	 * category="AT"
	 * ctc=""   
	 */

	public void testModifyInventoryColumnFwd()
	{
		actionPerform();
		verifyForward("updated");
		verifyForwardPath("/webclient/common/jsp/updated.jsp");
	}

	/**	
	 * @test test_case_id="WC-TOPO-ILV-120"
	 * test_case_description="To test the selected items list from the request"
	 * test_case_procedure="By setting some of the selected items value "name,type,status"
	 * and check the same is set in the selected items as parameters in the request."
	 * expected_result="Selected items entries found in the request parameter"
	 * severity="ShowStopper"
	 * comments=""
	 * category="AT"
	 * ctc=""   
	 */


	public void testGetSelectedItems()
	{
		actionPerform();
		assertEquals("Checking the Selected Columns to View","name type status",request.getParameter("selitems"));
	}

	/*
	 * @valid
	 * @test_case_id="WC-TOPO-ILV-"
	 * description="This method is to check the CustomView has been Modified"
	 *
	 */

	/*
	   public void testCreateTopoCustomView()
	   {
	   String viewId = request.getParameter("viewId");
	   String userName = request.getParameter("userName");

	   StringTokenizer tokens = new StringTokenizer (request.getParameter("selitems")," ",false);

	   int numtokens = tokens.countTokens();
	   String[] column = new String[numtokens];
	   int i = 0;

	   while(tokens.hasMoreTokens())
	   {
	   column[i] = tokens.nextToken();
	   i++;
	   }

	   TopoCustomViewFetcher tcf = new TopoCustomViewFetcher();

	   boolean status = tcf.modifyCV(viewId,userName,column);

	   actionPerform();

	   assertEquals("Checking CustomView Success status",true,status);
	   }
	 */


	/*
	 * Invoking the test suite
	 */

	public static junit.framework.Test suite()
	{
		return new junit.framework.TestSuite(TestModifyInventoryColumn.class);
	}



	/*
	 * Call Main method.
	 */

	public static void main(String args[])
	{
		junit.textui.TestRunner.run(TestModifyInventoryColumn.class);
	}


}
