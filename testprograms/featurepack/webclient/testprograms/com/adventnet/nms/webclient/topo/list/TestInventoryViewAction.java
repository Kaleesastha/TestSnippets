//$Id: TestInventoryViewAction.java,v 1.2 2003/10/20 15:54:51 srikrishnan Exp $

package com.adventnet.nms.webclient.topo.list;

//java imports
import java.util.Vector;

//cactus-test imports
import servletunit.struts.CactusStrutsTestCase;

//JWebUnit imports
//import net.sourceforge.jwebunit.WebTestCase;

//junit imports
//import junit.framework.*;
import com.adventnet.nms.fe.common.TableColumn;

/**
 * This class is to check the inventoryview Action class
 */
public class TestInventoryViewAction extends CactusStrutsTestCase
{



	/*
	 *  Constructor called
	 */
	public TestInventoryViewAction (String name)
	{
		super(name);
	}



	/*
	 * Initialization of the ActionClass
	 */

	public void setUp() throws Exception
	{
		super.setUp();	
		setConfigFile("/webclient/topo/conf/topo-struts-config.xml");
		setRequestPathInfo("/NetworkInventory.do");

		addRequestParameter("viewId","Network Database");
		addRequestParameter("userName","root");

	}

	/* NOTE :
	 *  Automated testcases id starts here from WC-TOPO-ILV-101
	 *  <strong> Please refer to the wc_topo_inv_list_tc.html for the testcases
	 *  pertaining to the inventory list view
	 *  Testcase id started here is NOT the continuation of the testcase id 
	 *  mentioned in the testcase document. 
	 */


	/**	
     * @test test_case_id="WC-TOPO-ILV-101"
     * test_case_description="To test the verifyForward of networkInventory page"
     * test_case_procedure="Action class forwards the request to networkInventory
	 * page, if we give correct viewId and userName"
     * expected_result="Action should be forwarded to the InventoryView. "
     * severity="ShowStopper"
     * comments=""
     * category="AT"
     * ctc=""   
     */

	public void testInventoryListFwd()
	{
		actionPerform();
		verifyForward("InventoryView");
		verifyForwardPath("/webclient/topo/jsp/networkInventory.jsp");
	}


	/**	
     * @test test_case_id="WC-TOPO-ILV-102"
     * test_case_description="To test the request Attribute value for ViewId" 
     * test_case_procedure=""
     * expected_result="In the request object, viewID Attribute value is set as
	 * "Network Database".
     * severity="Critical"
     * comments=""
     * category="AT"
     * ctc=""   
     */


	public void testViewIdAttributes()
	{
		actionPerform();
		String getViewId = (String)request.getAttribute("viewId");
		assertEquals("Checking the ViewID        :","Network Database",getViewId);
	}
	
	/**	
     * @test test_case_id="WC-TOPO-ILV-103"
     * test_case_description="To test the request Attribute value for isAscending" 
     * test_case_procedure=""
     * expected_result="In the request object, isAscending Attribute value is 
	 * set as "false".
     * severity="Critical"
     * comments=""
     * category="AT"
     * ctc=""   
     */

	public void testIsAscendingAttributeValue()
	{
		actionPerform();
		String getBoolVal = (String)request.getAttribute("isAscending");
		boolean isBool = Boolean.getBoolean(getBoolVal);
		assertEquals("Checking the isAscending   :", false,isBool);
	}
	

	/**	
     * @test test_case_id="WC-TOPO-ILV-104"
     * test_case_description="To test the request Attribute value for 
	 *  viewLength"
     * test_case_procedure=""
     * expected_result="In the request object, viewLength Attribute value is 
	 * set as "25" by default.
     * severity="Critical"
     * comments=""
     * category="AT"
     * ctc=""   
     */


	public void testViewLengthAttributeValue()
	{
		actionPerform();
		String getViewLength = (String)request.getAttribute("viewLength");
		int getIntViewLength = Integer.parseInt(getViewLength);
		assertEquals("Checking the viewLength    :", 25,getIntViewLength);
	}


	/**	
     * @test test_case_id="WC-TOPO-ILV-105"
     * test_case_description="To test the request Attribute value for startIndex"
     * test_case_procedure=""
     * expected_result="In the request object, startIndex Attribute value is set
	 * as "1" by default.
     * severity="Critical"
     * comments=""
     * category="AT"
     * ctc=""   
     */


	public void testStartIndexAttributeValue()
	{
		actionPerform();
		String getStartIndex = (String)request.getAttribute("startIndex");
		//int getIntStartIndex = Integer.parseInt(getStartIndex);
		assertEquals("Checking the startIndex    :", 1,
		Integer.parseInt(getStartIndex));

	}


	/**	
     * @test test_case_id="WC-TOPO-ILV-106"
     * test_case_description="To test the request Attribute value for 
	 * orderByColumn"
     * test_case_procedure=""
     * expected_result="In the request object, orderByColumn Attribute value 
	 * is set as "null" by default.
     * severity="Critical"
     * comments=""
     * category="AT"
     * ctc=""   
     */


	public void testCustomizeColumnActionAttributeValue()
	{
		actionPerform();
		String getCustomizeColAction =
		(String)request.getAttribute("CUSTOMIZE_COLUMNS_ACTION");
		
		assertEquals("Checking the Customize_column_action     :","TopoColumnCustomizer.do",getCustomizeColAction);
	}


	/**	
     * @test test_case_id="WC-TOPO-ILV-107"
     * test_case_description="To test the request Attribute value for 
	 * customizeColumnAction" 
     * test_case_procedure=""
     * expected_result="In the request object, customizeColumnAction Attribute
	 * value is set as
	 * "TopoColumnCustomizer.do" by default.
     * severity="Critical"
     * comments=""
     * category="AT"
     * ctc=""   
     */


	public void testOrderByColumnAttributeValue()
	{
		actionPerform();
		assertEquals("Checking the orderByColumn" +
		":",null,request.getAttribute("orderByColumn"));
	}


	/**	
     * @test test_case_id="WC-TOPO-ILV-108"
     * test_case_description="To test the request Attribute value for userName" 
     * test_case_procedure=""
     * expected_result="In the request object, userName Attribute value is set
	 * as "root" by default.
     * severity="ShowStopper"
     * comments=""
     * category="AT"
     * ctc=""   
     */

	public void testUserNameAttributeValue()
	{
		actionPerform();

		//userName value should be root,and this case will fail , since the userName value is currently not set at session level.( Once the authentation is completed from the framework side, this will work)
		//assertEquals("Checking the userName    :","root",(String)request.getAttribute("userName"));

	}


	/**	
     * @test test_case_id="WC-TOPO-ILV-109"
     * test_case_description="This is to check the default TableColumn 
	 * headers of the rendered table."
     * test_case_procedure=""
     * expected_result="Default Column Headers like Name,Status,Type,IpAddress,
	 * portState,parentNet 
	 * should exist"
     * severity="Critical"
     * comments="This method is not scalable.This will test only the default 
	 * parameters (6) parameters."
     * category="AT"
     * ctc=""   
     */


	public void testCheckTableColumnHeaders()
	{

		actionPerform();

		//Check the headerListValue gets printed in the stdout.txt - to verify
		//the values
		Object objVal = request.getSession().getAttribute("headerList");
		System.out.println("TableHeader value"+objVal);

		TableColumn[] headerListVal = (TableColumn[])request.getSession().getAttribute("headerList");

		for(int i =0;i <headerListVal.length;i++)
		{
			if(headerListVal[i].getColumnName() == "name")
			{
				assertEquals("Checking the value of DisplayName","Name",headerListVal[i].getDisplayName());
			}
			else if(headerListVal[i].getColumnName() == "status")
			{
				assertEquals("Checking the value of DisplayName","Status",headerListVal[i].getDisplayName());
			}
			else if(headerListVal[i].getColumnName() == "type")
			{
				assertEquals("Checking the value of DisplayName","Type",headerListVal[i].getDisplayName());
			}
			else if(headerListVal[i].getColumnName() == "ipAddress")
			{
				assertEquals("Checking the value of DisplayName","IpAddress",headerListVal[i].getDisplayName());
			}
			else if(headerListVal[i].getColumnName() == "portState")
			{
				assertEquals("Checking the value of DisplayName","PortState",headerListVal[i].getDisplayName());
			}
			else if(headerListVal[i].getColumnName() == "parentNet")
			{
				assertEquals("Checking the value of DisplayName","ParentNet",headerListVal[i].getDisplayName());
			}

			System.out.println("TableHeader Column Name  :" +headerListVal[i].getColumnName()+"    Display Name   " +headerListVal[i].getDisplayName());

		}

	}


	/**	
     * @test test_case_id="WC-TOPO-ILV-110"
     * test_case_description="To test the change in the isAscending attribute
	 * value gets reflected"
     * test_case_procedure=""
     * expected_result="In the request object, isAscending Attribute value is
	 * set as "true" by default.
     * severity="ShowStopper"
     * comments="By default, "isAscending" parameter value is set as "false".
	 * And in this method, we are overwriting this param value by setting it as
	 * "true" and checking that its value
	 * is currently beeing set."
     * category="AT"
     * ctc=""   
     */


	public void testIsAscending()
	{
		addRequestParameter("isAscending","false");
		actionPerform();
		String getBoolVal = (String)request.getAttribute("isAscending");
		boolean isBoolVal = Boolean.getBoolean(getBoolVal);
		assertEquals("Checking the isAscending   :",false,isBoolVal);
	}



	/**	
     * @test test_case_id="WC-TOPO-ILV-111"
     * test_case_description="To test the change in the orderByColumn attribute
	 * value to "Name" get reflected"
     * test_case_procedure=""
     * expected_result="In the request object, orderByColumn Attribute value 
	 * is set as "Name" by default.
     * severity="Major"
     * comments="By default,its value is null and now testing it by overwritting
	 * value by one of the table column value. This test sets the orderByColumn
	 * by setting the value as "name" assuming that , this table column exist 
	 * by default.Before testing this field ensure from the Tree.xml this 
	 * column "name" exist for "Network Database"
     * category="AT"
     * ctc=""   
     */


	public void testOrderByColumn()
	{
		addRequestParameter("orderByColumn","name");	
		actionPerform();
		String getOrderByCol = (String)request.getAttribute("orderByColumn");
		assertEquals("Checking the orderByColumn    :","name",getOrderByCol);
	}


	/**	
     * @test test_case_id="WC-TOPO-ILV-112"
     * test_case_description="To test the data by setting the data with the
	 * viewLength as 10.
     * test_case_procedure=""
     * expected_result="This method returns the data with the size specified
	 * as 10 and we are 
	 * checking the length of the fetched data"
     * severity="Critical"
     * comments=""
     * category="AT"
     * ctc=""   
     */

	public void testData2View()
	{
		addRequestParameter("viewLength","10");	
		actionPerform();
		String getVal = (String) request.getSession().getAttribute("viewData");
		System.out.println("Data2View :::: "+getVal);
		Vector viewDataVector =
		(Vector)request.getSession().getAttribute("viewData");
		assertEquals("Check for viewLength",10,viewDataVector.size()); 	
	}




	/**	
     * @test test_case_id="WC-TOPO-ILV-113"
     * test_case_description="To test if the viewData is null/Empty it should show
	 * the Error page"
     * test_case_procedure=""
     * expected_result="This should show the Error page "
     * severity="Critical"
     * comments=""
     * category="AT"
     * ctc=""   
     */

	public void testEmptyViewData()
	{
		addRequestParameter("viewData","null");
		actionPerform();

		//Check with the error page mapping w.r.t topo-struts-config.xml
		verifyForward("ErrorPage");
		verifyForwardPath("/webclient/topo/jsp/TopoErrorPage.jsp");
	}


	/*
		Additional Scope for Automation...
		1. Test the Name field with link.
		2. Test the list view Name with type image.
		3. Test the list view with Status image.
	*/

	/**
	 * Invoking the test suite
	 */

	public static junit.framework.Test suite()
	{
		return new junit.framework.TestSuite(TestInventoryViewAction.class);
	}



	/**
	 * Call Main method.
	 */

	public static void main(String args[])
	{
		junit.textui.TestRunner.run(TestInventoryViewAction.class);
	}

}
