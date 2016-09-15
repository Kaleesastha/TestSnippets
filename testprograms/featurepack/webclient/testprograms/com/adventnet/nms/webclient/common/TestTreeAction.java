//$Id: TestTreeAction.java,v 1.5 2003/09/26 12:19:52 sasikumar Exp $

package com.adventnet.nms.webclient.common;

import java.util.*;


//needed for getting instance of XmlTreeModel
import com.adventnet.webclient.components.tree.*;

import servletunit.struts.CactusStrutsTestCase;
import org.apache.cactus.*;

    /** 
    @test suite="Test Suite for Navigation Component"
    owner="sasikumar"
    version="1.0"
    date="Sep 10th 2003"
    */

public class TestTreeAction extends CactusStrutsTestCase
{
	public TestTreeAction(String testname)
	{
		super(testname);
    }
	public void setUp() throws Exception
   	{

	/**
    * In SetUp method Struts-Config.xml file and RequestPathinfo is referred.
	  Request Parameter is added here.
	*/
 		super.setUp();
        setConfigFile("/webclient/common/conf/struts-config.xml");
		System.out.println("Inside setUp method");	
        addRequestParameter("selectedNode","Fault");
        addRequestParameter("selectedTab","Fault");
		setRequestPathInfo("/Tree");
    }

	/**
    @valid
	@test test_case_id="WC-FMW-CV-NC-002"
	test_case_description="Setting required Atrribute and asserting from request,
	verfying wheater request is forwarded to tree,forward path is correct"
			    */
	public void testSelectedNode()
	{
        actionPerform();
		verifyForward("tree");
        verifyForwardPath("/webclient/common/jsp/NavigationComponent.jsp");
		System.out.println("Request Value is:----- " + 
							getRequest().getAttribute("selectedNode"));
		//assertEquals("Fault",getRequest().getAttribute("selectedNode"));
        //assertEquals("Fault",getRequest().getAttribute("selectedTab"));
		
		//HttpServletRequest
		assertEquals("Fault",request.getAttribute("selectedNode"));
        assertEquals("Fault",request.getAttribute("selectedTab"));  
    }
   

    /**
	@valid
	@test test_case_id="WC-FMW-CV-NC-003"
	test_case_description="To test wheater TreeModel and Path is not Null"
	*/
	public void testTree()
    {
	   actionPerform();
       verifyForward("tree");
       Object treeModelObj;
       Object pathObj;
       treeModelObj = request.getAttribute("TreeModel");
       pathObj = request.getAttribute("path");
   	   assertNotNull("TreeModelObj is Null",treeModelObj);
	   assertEquals("path object is not an instance of ArrayList",true, 
	               (treeModelObj instanceof XmlTreeModel));
       assertNotNull("PathObject is Null",pathObj);
	   assertEquals("path object is not an instance of ArrayList",true, 
	               (pathObj instanceof ArrayList));
	   
   }

   /**
   @valid
   @test test_case_id="WC-FMW-CV-NC-005"
   test_case_description="To test wheater selectedIds is not Null"
   */
   public void testsetSelectedIdInSession()
   {
       actionPerform();
	   Object selectedIdsObj;
	   //selectedIds=request.getSession().getAttribute("selectedIds"); 		   

	   //HttpSession object
	   selectedIdsObj = session.getAttribute("selectedIds");		   
	   assertNotNull("selectedIds is Null",selectedIdsObj);
	   assertEquals(" is not an instance of Properties",true,
	               (selectedIdsObj instanceof Properties));

   }
  

  /**
  @valid
  @test test_case_id="WC-FMW-CV-NC-006"
  test_case_description="To test wheater userParams is not Null"
  */
  public void testsetUserParamsInSession()
  {
	  actionPerform(); 
	  Object userParamsObj;
	  //userParamsObj=request.getSession().getAttribute("userParams");
	  userParamsObj = session.getAttribute("userParams");
	  assertNotNull("userParams is Null",userParamsObj);
	  assertEquals("userParamsObj is not an instance of Properties",true,
	              (userParamsObj instanceof Properties));
  }
  public static junit.framework.Test suite()
  {
      return new junit.framework.TestSuite( TestTreeAction.class );
  }
	
  public static void main(String arg[])
  {
  junit.textui.TestRunner.run(TestTreeAction.class);
  }
}
