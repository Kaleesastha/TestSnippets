//$Id: TestAlarmViewDispatchAction.java,v 1.1 2003/09/23 13:16:19 rameshkumarp Exp $

/**
 * @(#)TestAlarmViewDispatchAction.java
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

import com.adventnet.nms.eventdb.Event;
import com.adventnet.nms.webclient.fault.utils.FaultTestUtils;
import com.adventnet.nms.alertdb.Alert;

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
public class TestAlarmViewDispatchAction extends servletunit.struts.CactusStrutsTestCase
{

    private String userName = null;
    private Event event = null;
    private FaultTestUtils faultTestUtils = new FaultTestUtils();

    public TestAlarmViewDispatchAction( String theName )
    {
        super( theName );
    }

    private Properties getEventProperties()
    {
        Properties eventProp = new Properties();
        eventProp.put( "entity" , this.getName() );
        eventProp.put( "source" , this.getName() );
        eventProp.put( "severity" , "1" );
        eventProp.setProperty( "category" , "mycategory" );
        eventProp.setProperty( "text" , "mytext" );
        String currentTime = new String( ( new Long( System.currentTimeMillis() ) ).toString() );
        eventProp.setProperty( "time" , currentTime );
        eventProp.setProperty( "domain" , "mydomain" );
        eventProp.setProperty( "network" , "mynetwork" );
        eventProp.setProperty( "node" , "mynode" );
        eventProp.setProperty( "helpURL" , "myhelpURL" );
        eventProp.setProperty( "groupName" , "mygroupName" );
        return eventProp;
    }

    public void setUp()
    {
        try {
            super.setUp();
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        setConfigFile( "/webclient/fault/conf/fault-struts-config.xml" );
        setRequestPathInfo( "/AlarmOperations" );
        userName = "root";
        event = faultTestUtils.addAndReturnEvent( getEventProperties() );
        session.setAttribute( "userName" , userName );
        addRequestParameter( "selectedEntity" , event.getEntity() );
        addRequestParameter( "viewId" , "Alerts" );
    }

    /**
     @valid
     @test test_case_id="WC-FLT-ALV-113"
     description=""
     */
    public void testClearAlarmFwd()
    {
        addRequestParameter( "methodCall" , "clearAlarm" );
        actionPerform();

        verifyForward( "viewAlarm" );

        Alert alert = faultTestUtils.getAlertForEvent( event );
        int severity = alert.getSeverity();

        assertEquals( "Severity of the cleared Alert is not 5 (clear)" , 5 , severity );
    }

    /**
     @valid
     @test test_case_id="WC-FLT-ALV-114"
     description=""
     */
    public void testClearAlarmViewIdAttr()
    {
        addRequestParameter( "methodCall" , "clearAlarm" );
        addRequestParameter( "viewId" , "Alerts" );
        addRequestParameter( "selectedEntity" , event.getEntity() );

        actionPerform();

        Object viewId = request.getAttribute( "viewId" );

        assertNotNull( "selectedEntity attribute is null in request" , viewId );

        assertEquals( "selectedEntity is not instance of String" , true , viewId instanceof String );

        assertEquals( "viewId attribute value is not same as that of the value set in the request parameter" , "Alerts" , request.getAttribute( "viewId" ) );
    }

    /**
     @valid
     @test test_case_id="WC-FLT-ALV-115"
     description=""
     */
    public void testClearMoreThanOneAlarm()
    {
        Properties eventTwoProp = getEventProperties();
        String secondEntity = getName() + "_Second";
        eventTwoProp.setProperty( "entity" , secondEntity );
        eventTwoProp.setProperty( "source" , secondEntity );
        Event eventTwo = faultTestUtils.addAndReturnEvent( eventTwoProp );
        String selectedEntity = getName() + "," + secondEntity;
        addRequestParameter( "methodCall" , "clearAlarm" );
        addRequestParameter( "selectedEntity" , selectedEntity );

        actionPerform();

        Alert alertOne = faultTestUtils.getAlertForEvent( event );
        Alert alertTwo = faultTestUtils.getAlertForEvent( eventTwo );

        assertEquals( "Severity of the first Alert (of Two Alerts) Cleared is not equal to 5 ( clear ) " , 5 , alertOne.getSeverity() );

        assertEquals( "Severity of the second Alert (of Two Alerts) Cleared is not equal to 5 ( clear ) " , 5 , alertTwo.getSeverity() );
    }

    /**
     @valid
     @test test_case_id="WC-FLT-ALV-116"
     description=""
     */
    public void testdeleteAlarmFwd()
    {
        addRequestParameter( "methodCall" , "deleteAlarm" );
        actionPerform();

        verifyForward( "viewAlarm" );

        Alert alert = faultTestUtils.getAlertForEvent( event );

        assertNull( "alert is not null , meaning alert is not deleted" , alert );
    }

    /**
     @valid
     @test test_case_id="WC-FLT-ALV-117"
     description=""
     */
    public void testDeleteAlarmViewIdAttr()
    {
        addRequestParameter( "methodCall" , "deleteAlarm" );
        addRequestParameter( "viewId" , "Alerts" );
        addRequestParameter( "selectedEntity" , event.getEntity() );

        actionPerform();

        Object viewId = request.getAttribute( "viewId" );

        assertNotNull( "selectedEntity attribute is null in request" , viewId );

        assertEquals( "selectedEntity is not instance of String" , true , viewId instanceof String );

        assertEquals( "viewId attribute value is not same as that of the value set in the request parameter" , "Alerts" , request.getAttribute( "viewId" ) );
    }

    /**
     @valid
     @test test_case_id="WC-FLT-ALV-118"
     description=""
     */
    public void testDeleteMoreThanOneAlarm()
    {
        Properties eventTwoProp = getEventProperties();
        String secondEntity = getName() + "_Second";
        eventTwoProp.setProperty( "entity" , secondEntity );
        eventTwoProp.setProperty( "source" , secondEntity );
        Event eventTwo = faultTestUtils.addAndReturnEvent( eventTwoProp );
        String selectedEntity = getName() + "," + secondEntity;
        addRequestParameter( "methodCall" , "deleteAlarm" );
        addRequestParameter( "selectedEntity" , selectedEntity );

        actionPerform();

        Alert alertOne = faultTestUtils.getAlertForEvent( event );
        Alert alertTwo = faultTestUtils.getAlertForEvent( eventTwo );

        assertNull( "alertOne is not null - alertone is not deleted while deleting 2 alerts (multiple selection" , alertOne );

        assertNull( "alertTwo is not null - alertTwo is not deleted while deleting 2 alerts (multiple selection" , alertTwo );
    }

    public static junit.framework.Test suite()
    {
        return new junit.framework.TestSuite( TestAlarmViewDispatchAction.class );
    }

    public static void main( String[] args )
    {
        junit.textui.TestRunner.run( TestAlarmViewDispatchAction.class );
    }

}