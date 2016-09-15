//$Id: TestEventColumnCustomizer.java,v 1.1 2003/09/23 12:05:44 rameshkumarp Exp $

/**
 * @(#)TestEventColumnCustomizer.java
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

package com.adventnet.nms.webclient.fault.event;

import java.util.*;

/**
 @test suite="Event List View Test Programs "
 owner="rameshkumarp"
 version="1.0"
 date="Sep 23, 2003"

 Introduction=""
 Acronymn=""
 PriorKnowledge=""
 Resource=""
 Links=""
 Preamble=""
 */
public class TestEventColumnCustomizer extends servletunit.struts.CactusStrutsTestCase
{
     private String userName = null;
    public TestEventColumnCustomizer( String theName )
    {
        super( theName );
    }

    public void setUp()
    {
        try {
            super.setUp();
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        setConfigFile( "/webclient/fault/conf/fault-struts-config.xml" );
        setRequestPathInfo( "/EventColumnCustomizer" );
        userName = "root";
        session.setAttribute( "userName" , userName );
        addRequestParameter( "viewId" , "Events" );

    }

    /**
     @valid
     @test test_case_id="WC-FLT-ELV-113"
     description=""
     */

    public void testShowColumnsFwd()
    {
        actionPerform();
        verifyForward( "ShowColumns" );
    }

    /**
     @valid
     @test test_case_id="WC-FLT-ELV-114"
     description=""
     */

    public void testActionAttr()
    {
        actionPerform();
        Object action = request.getAttribute( "action" );

        assertNotNull( "action parameter is null" , action );

        assertEquals( "action is not instance of String" , true , action instanceof String );

        assertEquals( "action is not ModifyEvent.do" , "/fault/ModifyEventCV.do" , ( String ) action );
    }

    /**
      @valid
      @test test_case_id="WC-FLT-ELV-115"
      description=""
      */

    public void testDefaultListAttr()
    {
        actionPerform();
        Object defaultList = request.getAttribute( "defaultList" );

        assertNotNull( "defaultList parameter is null" , defaultList );

        assertEquals( "defaultList is not instance of String" , true , defaultList instanceof Properties );
    }

    /**
     @valid
     @test test_case_id="WC-FLT-ELV-116"
     description=""
     */

    public void testViewListAttr()
    {
        actionPerform();
        Object viewList = request.getAttribute( "viewList" );

        assertNotNull( "viewList parameter is null" , viewList );

        assertEquals( "viewList is not instance of String" , true , viewList instanceof com.adventnet.nms.fe.common.TableColumn[] );
    }



    public static junit.framework.Test suite()
    {
        return new junit.framework.TestSuite( TestEventColumnCustomizer.class );
    }

    public static void main( String[] args )
    {
        junit.textui.TestRunner.run( TestEventColumnCustomizer.class );
    }

}