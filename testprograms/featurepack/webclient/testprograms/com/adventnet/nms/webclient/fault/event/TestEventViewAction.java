//$Id: TestEventViewAction.java,v 1.2 2003/10/22 16:25:04 rameshkumarp Exp $

/**
 * @(#)TestEventViewAction.java
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
 @test suite="Event List View Test Program"
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
public class TestEventViewAction extends servletunit.struts.CactusStrutsTestCase
{

    private String userName = null;

    public TestEventViewAction( String theName )
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
        setRequestPathInfo( "/NetworkEvent" );
        userName = "root";
        session.setAttribute( "userName" , userName );
        addRequestParameter( "viewId" , "Events" );

    }

    /**
     @valid 
     @test test_case_id="WC-FLT-ELV-100"
     description="To Test Event List View Forward"
     */
    public void testEventListViewFwd()
    {
        actionPerform();
        verifyTilesForward( "eventView" , "NetworkEventPage" );
    }

    /**
     @valid
     @test test_case_id="WC-FLT-ELV-101"
     description=""
     */
    public void testViewIdAttr()
    {
        actionPerform();
        String viewId = ( String ) request.getAttribute( "viewId" );
        String message = "value of viewId attribute is not equal to Events";

        assertNotNull( "viewId attribute is null " , viewId );

        assertEquals( message , "Events" , viewId );
    }

    /**
     @valid
     @test test_case_id="WC-FLT-ELV-102"
     description=""
     */
    public void testIsAscendingAttr()
    {
        actionPerform();
        Object isAscending = request.getAttribute( "isAscending" );

        assertNotNull( "isAscending attribute is null" , isAscending );

        assertEquals( "isAscending value is not false" , false , ( new Boolean( ( String ) isAscending ) ).booleanValue() );
    }

    /**
     @valid
     @test test_case_id="WC-FLT-ELV-103"
     description=""
     */
    public void testViewLengthAttr()
    {
        actionPerform();

        assertEquals( "Checking the viewLength    :" , 50 , ( ( Integer ) request.getAttribute( "viewLength" ) ).intValue() );
    }

    /**
     @valid
     @test test_case_id="WC-FLT-ELV-104"
     description="DEPRECATED"
     */
    /* public void testStartIndexAttr()
     {
         addRequestParameter( "FROM_INDEX" , "0" );
         actionPerform();
         Object FROM_INDEX = request.getAttribute( "FROM_INDEX" );

         assertNotNull( "FROM_INDEX attribute is null " , FROM_INDEX );

         assertEquals( "Checking the FROM_INDEX    :" , 0 , Integer.parseInt( ( String ) FROM_INDEX ) );
     }*/

    /**
     @valid
     @test test_case_id="WC-FLT-ELV-105"
     description=""
     */
    public void testCustomizeColumnActionAttr()
    {
        actionPerform();

        assertEquals( "Checking the Customize_column_action     :" , "EventColumnCustomizer.do" , ( String ) request.getAttribute( "CUSTOMIZE_COLUMNS_ACTION" ) );
    }

    /**
     @valid
     @test test_case_id="WC-FLT-ELV-106"
     description=""
     */
    public void testActionAttr()
    {
        actionPerform();

        assertEquals( "Checking the value of action attribute     :" , "NetworkEvent.do" , ( String ) request.getAttribute( "action" ) );
    }

    /**
     @valid
     @test test_case_id="WC-FLT-ELV-107"
     description=""
     */
    public void testPageLengthAttr()
    {
        actionPerform();
        Object pageLength = request.getAttribute( "PAGE_LENGTHS" );

        assertNotNull( "PAGE_LENGTHS vector is null" , pageLength );

        assertEquals( "PAGE_LENGTHS attribute is not an instance of Vector" , true , pageLength instanceof Vector );
    }

    /**
     @valid
     @test test_case_id="WC-FLT-ELV-108"
     description=""
     */
    public void testHeaderListAttr()
    {
        actionPerform();
        Object headerList = request.getAttribute( "headerList" );

        assertNotNull( "headerList array is null" , headerList );

        assertEquals( "headerList attribute is not an instance of TableColumn" , true , headerList instanceof com.adventnet.nms.fe.common.TableColumn[] );
    }

    /**
     @valid
     @test test_case_id="WC-FLT-ELV-109"
     description=""
     */
    public void testRecordAttr()
    {
        actionPerform();
        Object record = request.getAttribute( "RECORDS" );

        assertNotNull( "RECORDS vector is null" , record );

        assertEquals( "RECORDS attribute is not an instance of Long" , true , record instanceof Long );
    }

    /**
     @valid
     @test test_case_id="WC-FLT-ELV-110"
     description=""
     */
    public void testOrderByColumnAttr()
    {
        actionPerform();

        assertEquals( "Checking the orderByColumn    :" , null , request.getAttribute( "orderByColumn" ) );
    }

    /**
     @valid
     @test test_case_id="WC-FLT-ELV-111"
     description=""
     */
    public void testOrderByColumn()
    {
        addRequestParameter( "orderByColumn" , "entity" );
        actionPerform();

        assertEquals( "Checking the orderByColumn    :" , "entity" , ( String ) request.getAttribute( "orderByColumn" ) );
    }

    /**
     @valid
     @test test_case_id="WC-FLT-ELV-112"
     description=""
     */
    public void testData2View()
    {
        actionPerform();

        assertEquals( "Check for viewLength" , 50 , ( ( Vector ) request.getAttribute( "viewData" ) ).size() );
    }

    public static junit.framework.Test suite()
    {
        return new junit.framework.TestSuite( TestEventViewAction.class );
    }

    public static void main( String[] args )
    {
        junit.textui.TestRunner.run( TestEventViewAction.class );
    }

}