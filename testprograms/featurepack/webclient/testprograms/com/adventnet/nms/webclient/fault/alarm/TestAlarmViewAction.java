//$Id: TestAlarmViewAction.java,v 1.3 2003/12/02 18:34:32 rameshkumarp Exp $

/**
 * @(#)TestAlarmViewAction.java
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

package com.adventnet.nms.webclient.fault.alarm;

import java.util.*;

/**
 @test suite="Alarm List View Test Program"
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
public class TestAlarmViewAction extends servletunit.struts.CactusStrutsTestCase
{

    private String userName = null;

    public TestAlarmViewAction( String theName )
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
        setRequestPathInfo( "/AlarmView" );
        userName = "root";
        session.setAttribute( "userName" , userName );
        addRequestParameter( "viewId" , "Alerts" );

    }

    /**
     @valid
     @test test_case_id="WC-FLT-ALV-100"
     description=""
     */

    public void testAlarmListViewFwd()
    {
        actionPerform();

        verifyTilesForward("alarmView" , "NetworkAlarmPage");
    }

    /**
     @valid
     @test test_case_id="WC-FLT-ALV-101"
     description=""
     */
    public void testViewIdAttr()
    {
        actionPerform();
        String viewId = ( String ) request.getAttribute( "viewId" );
        String message = "value of viewId attribute is not equal to Alerts";
        assertNotNull( "viewId attribute is null " , viewId );
        assertEquals( message , "Alerts" , viewId );
    }

    /**
     @valid
     @test test_case_id="WC-FLT-ALV-102"
     description=""
     */
    public void testIsAscendingAttr()
    {
        actionPerform();
        Object isAscending = request.getAttribute( "isAscending" );
        assertNotNull( "isAscending is null" , isAscending );
        assertEquals( "isAscending value is not true" , false , ( new Boolean( ( String ) isAscending ) ).booleanValue() );
    }

    /**
     @valid
     @test test_case_id="WC-FLT-ALV-103"
     description=""
     */
    public void testViewLengthAttr()
    {
        actionPerform();
        assertEquals( "Checking the viewLength    :" , 25 , ( ( Integer ) request.getAttribute( "viewLength" ) ).intValue() );
    }

    /**
     @valid
     @test test_case_id="WC-FLT-ALV-104"
     description=""
     */
    public void testStartIndexAttr()
    {
        actionPerform();
        Object startIndex = request.getAttribute( "startIndex" );
        assertNotNull( "FROM_INDEX attribute is null " , startIndex );
        assertEquals( "Checking the FROM_INDEX    :" , 0 , Integer.parseInt( ( String ) startIndex ) );
    }

    /**
     @valid
     @test test_case_id="WC-FLT-ALV-105"
     description=""
     */
    public void testCustomizeColumnActionAttr()
    {
        actionPerform();
        assertEquals( "Checking the Customize_column_action     :" , "AlarmColumnCustomizer.do" , ( String ) request.getAttribute( "CUSTOMIZE_COLUMNS_ACTION" ) );
    }

    /**
     @valid
     @test test_case_id="WC-FLT-ALV-106"
     description=""
     */
    public void testActionAttr()
    {
        actionPerform();
        assertEquals( "Checking the value of action attribute     :" , "AlarmView.do" , ( String ) request.getAttribute( "action" ) );
    }

    /**
     @valid
     @test test_case_id="WC-FLT-ALV-107"
     description=""
     */
    public void testPageLengthAttr()
    {
        actionPerform();
        Object pageLength = request.getAttribute( "PAGE_LENGTHS" );
        assertNotNull( "PAGE_LENGTH vector is null" , pageLength );
        assertEquals( "PAGE_LENGTH attribute is not an instance of Vector" , true , pageLength instanceof Vector );
    }

    /**
     @valid
     @test test_case_id="WC-FLT-ALV-108"
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
     @test test_case_id="WC-FLT-ALV-109"
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
     @test test_case_id="WC-FLT-ALV-110"
     description=""
     */
    public void testOrderByColumnAttr()
    {
        actionPerform();
        assertEquals( "Checking the orderByColumn    :" , null , request.getAttribute( "orderByColumn" ) );
    }

    /**
     @valid
     @test test_case_id="WC-FLT-ALV-111"
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
     @test test_case_id="WC-FLT-ALV-112"
     description=""
     */
    public void testData2View()
    {
        actionPerform();
        assertEquals( "Check for viewLength" , 25 , ( ( Vector ) request.getAttribute( "viewData" ) ).size() );
    }



    public static junit.framework.Test suite()
    {
        return new junit.framework.TestSuite( TestAlarmViewAction.class );
    }

    public static void main( String[] args )
    {
        junit.textui.TestRunner.run( TestAlarmViewAction.class );
    }

}