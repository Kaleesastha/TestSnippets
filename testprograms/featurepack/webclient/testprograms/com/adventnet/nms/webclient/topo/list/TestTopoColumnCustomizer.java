//$Id: TestTopoColumnCustomizer.java,v 1.2 2003/10/20 15:54:51 srikrishnan Exp $

package com.adventnet.nms.webclient.topo.list;

//java imports
import java.util.Properties;
import java.util.Enumeration;

//cactus-test imports
import servletunit.struts.CactusStrutsTestCase;

//junit imports
//import junit.framework.*;


public class TestTopoColumnCustomizer extends CactusStrutsTestCase
{


	//Constructor called
	public TestTopoColumnCustomizer (String name)
	{
		super(name);
	}



	// Initialization of the ActionClass
	public void setUp() throws Exception
	{
		super.setUp();	
		setConfigFile("/webclient/topo/conf/topo-struts-config.xml");
		setRequestPathInfo("/TopoColumnCustomizer.do");

	}

	/* NOTE :
	 *  Testcase id in TestInventoryViewAction.java is WC-TOPO-ILV-101 to WC-TOPO-ILV-113
	 *  <strong> Please refer to the TestInventoryViewAction.java for the last testcase id.<strong>
	 *  Testcase id started here is continuation of the testcase id mentioned in the 
	 *  TestInventoryViewAction.java class.
	 */

	/**	
	 * @test test_case_id="WC-TOPO-ILV-114"
	 * test_case_description="To test the verifyForward of ShowColumns page"
	 * test_case_procedure=".
	 * expected_result="Action class forwards the request to ShowColumns page
	 * severity="ShowStopper"
	 * comments=""
	 * category="AT"
	 * ctc=""   
	 */


	public void testTopoColumnCustomizerFwd()
	{
		actionPerform();
		verifyForward("ShowColumns");
		verifyForwardPath("/webclient/common/jsp/ShowColumns.jsp");
	}


	/**	
	 * @test test_case_id="WC-TOPO-ILV-115"
	 * test_case_description="To test the viewList values of the TableColumn
	 * test_case_procedure=".
	 * expected_result="Will print the columnNames and DisplayNames in the stdout.txt"
	 * severity="Major"
	 * comments=""
	 * category="AT"
	 * ctc=""   
	 */

	public void testShowViewList()
	{
		addRequestParameter("viewId","Network Database");
		addRequestParameter("userName","root");
		actionPerform();

		com.adventnet.nms.fe.common.TableColumn[] viewHeaderListVal = (com.adventnet.nms.fe.common.TableColumn[])request.getAttribute("viewList");
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("View List Entities : (Column Name ),(Display Name)");
		for (int j=0;j<viewHeaderListVal.length;j++)
		{
			System.out.println("                   : "+viewHeaderListVal[j].getColumnName()+","+viewHeaderListVal[j].getDisplayName());   
		}
		System.out.println("--------------------------------------------------------------------------");
	}


	/**	
	 * @test test_case_id="WC-TOPO-ILV-116"
	 * test_case_description="This method is to test the DefaultList values of the TableColumn
	 * test_case_procedure=".
	 * expected_result="Will print the defaultList in the stdout.txt"
	 * severity="Major"
	 * comments=""
	 * category="AT"
	 * ctc=""   
	 */


	public void testShowDefaultList()
	{
		addRequestParameter("viewId","Network Database");
		addRequestParameter("userName","root");
		actionPerform();

		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		System.out.println("View Default List Entities : (Column Name ),(Display Name)");
		System.out.println("                   : "+request.getAttribute("defaultList"));
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
	}



	/**	
	 * @test test_case_id="WC-TOPO-ILV-117"
	 * test_case_description="This method is to compair the values of the ViewList and the DefaultList"
	 * test_case_procedure="".
	 * expected_result="Will print if any of the ViewList present in the DefaultList"
	 * severity="ShowStopper"
	 * comments=""
	 * category="AT"
	 * ctc=""   
	 */


	public void testCompairLists()
	{
		addRequestParameter("viewId","Network Database");
		addRequestParameter("userName","root");
		actionPerform();

		com.adventnet.nms.fe.common.TableColumn[] viewHeaderListVal = (com.adventnet.nms.fe.common.TableColumn[])request.getAttribute("viewList");

		System.out.println("----------------------------->" +viewHeaderListVal.length);

		Properties defaultList=(Properties)request.getAttribute("defaultList");
		Enumeration keys = defaultList.propertyNames();

		while(keys.hasMoreElements())
		{
			String defelement = defaultList.getProperty((String)keys.nextElement());
			for(int i=0;i<viewHeaderListVal.length;i++)
			{
				if((viewHeaderListVal[i].getColumnName()).equals(defelement))
				{
					System.out.println("View List ***********>>> " +viewHeaderListVal[i].getColumnName() +" Present in Default List");
				}
			}
		}


	}



	/**	
	 * @test test_case_id="WC-TOPO-ILV-118"
	 * test_case_description="This method is to test the action value set in the request".
	 * test_case_procedure="".
	 * expected_result="The Attribute value for the parameter "action" to be returned as ModifyTopoCV.do"
	 * severity="ShowStopper"
	 * comments=""
	 * category="AT"
	 * ctc=""   
	 */

	public void testActionValue()
	{
		actionPerform();
		assertEquals("Check the value of Action in the request ","ModifyTopoCV.do",(String)request.getAttribute("action"));
	}


	/*
	 * Invoking the test suite
	 */

	public static junit.framework.Test suite()
	{
		return new junit.framework.TestSuite(TestTopoColumnCustomizer.class);
	}



	/*
	 * Call Main method.
	 */

	public static void main(String args[])
	{
		junit.textui.TestRunner.run(TestTopoColumnCustomizer.class);
	}

}
