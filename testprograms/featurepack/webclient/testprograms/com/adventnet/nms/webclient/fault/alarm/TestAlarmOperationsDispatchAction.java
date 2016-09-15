//$Id: TestAlarmOperationsDispatchAction.java,v 1.4 2003/12/03 04:41:08 rameshkumarp Exp $

/**
 * @(#)TestAlertDetailAction.java
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
import com.adventnet.nms.alertdb.Alert;
import com.adventnet.nms.webclient.fault.utils.FaultTestUtils;

/**

 @test suite=""
 owner="rameshkumarp"
 version="1.0"
 date="Aug 21, 2003"
 */
public class TestAlarmOperationsDispatchAction extends servletunit.struts.CactusStrutsTestCase
{

    private FaultTestUtils faultTestUtils = new FaultTestUtils();
    private String userName = null;
    private String unAuthorizedUser = "AlarmUnAuthorizedUser"; //TODO before running the test suite, we have to create the user who does not have permission
    private String authorizedUser = "AlarmAuthorizedUser"; //TODO before running the test suite, we have to create the user who does not have permission . And also this should not be hardcoded here.
    private String entity = null;
    private Properties eventProperties = null;
    private Event event = null;
    private Alert alert = null;

    public TestAlarmOperationsDispatchAction( String theName )
    {
        super( theName );
    }
    private Properties getEventProperties()
    {
        Properties eventProp;
        eventProp = new Properties();
        eventProp.put("entity" , this.getName());
        eventProp.put("source" , this.getName());
        eventProp.put("severity" ,"1");
        eventProp.setProperty( "category" , "mycategory" );
        eventProp.setProperty( "text" , "mytext" );
        String currentTime = new String( new Long( System.currentTimeMillis() ).toString() );
        eventProp.setProperty( "time" , currentTime );
        eventProp.setProperty( "domain" , "mydomain" );
        eventProp.setProperty( "network" , "mynetwork" );
        eventProp.setProperty( "node" , "mynode" );
        eventProp.setProperty( "helpURL" , "myhelpURL" );
        eventProp.setProperty( "groupName" , "mygroupName" );
        return eventProp;
    }

    /**
     * setUp method sets the "userName" in the session. Add an event with entity value as the name of the testcase under run. Get the alert corresponding to the added evnet. Entity of this alert is set in the attribute. struts config file to be referred is set. Since the request pathinfo is common for all the test cases in this testsuite, it has been mentioned in the setUp method itself.
     */
    public void setUp()
    {
        try {
            super.setUp();
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        setConfigFile("/webclient/fault/conf/fault-struts-config.xml");
        setRequestPathInfo( "/AlarmDetails" );

        //TODO : user name and entity are common parameter for all the operation in alarm
        this.userName = authorizedUser;
        session.setAttribute( "userName" , userName );

        getEventProperties();
        eventProperties = getEventProperties();
        Event event = faultTestUtils.addAndReturnEvent(eventProperties);
        alert = faultTestUtils.getAlertForEvent(event);
        this.entity = alert.getEntity();

        addRequestParameter( "entity" , entity );
        addRequestParameter( "method" , "alertProperties" );
    }

    /**
     @valid
     @test test_case_id="WC-FLT-ADV-TP-048"
     description="To Check whether action class forwards the request to AlerttDetails page if we give correct eventID and user name"
     */

    public void testAlertDetailsForward()
    {
        actionPerform();

        verifyForward( "alarmProperties" );

        Object alertPropObj;
        alertPropObj = request.getAttribute( "alertProp" );

        assertNotNull( "alertProp attribute is null" , alertPropObj );

        assertTrue( "alertProp object is not an instance of Properties" , ( alertPropObj instanceof Properties ) );

        verifyNoActionErrors();
    }

    private void checkForAttr( String expectedVal , String attrName )
    {
        String message;
        message = "Value present in request for attrib: " + attrName + "is not same  as value expected : " + expectedVal;

        assertEquals( message , expectedVal , ( String ) request.getAttribute( attrName ) );
    }

    /**
     @valid
     @test test_case_id="WC-FLT-ADV-TP-049"
     description="Using checkForAttr method check whether entity value present in the request attribute and entity of the alert(used in this test) are same"
     */

    public void testEntityISCorrectInAlertProp()
    {
        actionPerform();
        checkForAttr( entity , "entity" );
    }
    /**
     @valid
     @test test_case_id="WC-FLT-ADV-TP-050"
     description="Set the necessary parameter in the request and check whether request is forwarded to "otherFailures""
     */
    public void testOtherFailturesForward()
    {
        addRequestParameter( "method" , "otherFailures" );
        String groupName = getEventProperties().getProperty("groupName");
        addRequestParameter( "groupName" , groupName ); //TODO group Name should not be hard coded
        actionPerform();

        verifyForward( "otherFailures" );

        Object otherFailurePropObj;
        otherFailurePropObj = request.getAttribute( "otherFailures" );

        assertNotNull( "alertProp attribute is null" , otherFailurePropObj );

        assertTrue( "otherFailures object is not an instance of Properties" , ( otherFailurePropObj instanceof Properties ) );

        verifyNoActionErrors();

    }

    private void alertPickUpPerform()
    {
        addRequestParameter( "method" , "alertPickUp" );
        actionPerform();
    }
    /**
     @valid
     @test test_case_id="WC-FLT-ADV-TP-051"
     description="Set the necessary parameter(method = "alertPickUp") in the request and check whether request is forwarded to "alarmDetail""
     */
    public void testPickUpForward()
    {
        alertPickUpPerform();

        verifyForward( "alarmDetails" );

        checkForAttr( entity , "entity" );

        verifyNoActionErrors();
    }
    private void myAssert(String property , String expectedValue , String actualValue)
    {
        String message ="Property Checked " + property  + " : "+expectedValue + " and " + actualValue + " are not same";
        assertEquals(message , expectedValue , actualValue);
    }
    /**
     @valid
     @test test_case_id="WC-FLT-ADV-TP-052"
     description="After pickUp whether necessary attributes are set in the request attribute "
     */
    public void testPickUpReqAttrib()
    {
        alertPickUpPerform();
        String ownerName = alert.getWho();
        String groupName = alert.getGroupName();
        Properties alertProp = alert.getProperties();
        String severity = alertProp.getProperty( "stringseverity" );

        myAssert( "ownerName" , authorizedUser , ( String ) request.getAttribute( "ownerName" ) );
        myAssert( "groupName" , groupName , ( String ) request.getAttribute( "groupName" ) );
        myAssert( "severity" , severity , ( String ) request.getAttribute( "severity" ) );
        assertEquals( "ShowTabOne's value is not true" , true , ( ( Boolean ) request.getAttribute( "showTabOne" ) ).booleanValue() );


    }

    /**
     @valid
     @test test_case_id="WC-FLT-ADV-TP-053"
     description="After pickUp whether boolean value is set for "pickUpStatus" request attribute"
     */
    public void testPickUpStatus()
    {
        alertPickUpPerform();

        Object pickUpStatus;
        pickUpStatus = (String) request.getAttribute( "pickUpStatus" );

        assertNotNull( "pickUpStatus attribute is null" , pickUpStatus );

        assertEquals("pickUpStatus is not equal to true" , "true" , pickUpStatus );
    }

    /**
     @valid
     @test test_case_id="WC-FLT-ADV-TP-054"
     description="pickUp operation for unuthorized user"
     */
    public void testPickUpStatusForNonAuthorizedUser()
    {
        session.setAttribute( "userName" , unAuthorizedUser );
        setInitParameter( "definitions-config" , "/webclient/fault/conf/fault-tiles-defn.xml" );
        setInitParameter( "definitions-debug" , "0" );
        alertPickUpPerform();

        //verifyForward( "messagePage" );
        verifyTilesForward("messagePage" , "message");

    }

    private void alertUnPickUpPerform()
    {
        addRequestParameter( "method" , "alertUnPick" );
        actionPerform();
    }
    /**
     @valid
     @test test_case_id="WC-FLT-ADV-TP-055"
     description="Set the necessary parameter(method = "alertUnPick") in the request and check whether request is forwarded to "alarmDetail""
     */
    public void testUnPickForward()
    {
        alertUnPickUpPerform();

        verifyForward( "alarmDetails" );

        checkForAttr( entity , "entity" );

        verifyNoActionErrors();
    }
    /**
     @valid
     @test test_case_id="WC-FLT-ADV-TP-056"
     description="After alertUnPick check whether necessary parameters are sent in the request attribute"
     */
    public void testUnPickUpReqAttrib()
    {
        alertUnPickUpPerform();

        String ownerName = alert.getWho();
        ownerName = (ownerName == null) ? "-" : ownerName ;
        String groupName = alert.getGroupName();
        Properties alertProp = alert.getProperties();
        String severity = alertProp.getProperty( "stringseverity" );

        myAssert( "ownerName",ownerName , ( String ) request.getAttribute( "ownerName" ) );
        myAssert( "groupName", groupName , ( String ) request.getAttribute( "groupName" ) );
        myAssert( "severity" ,  severity , ( String ) request.getAttribute( "severity" ) );
        assertEquals( "ShowTabOne's value is not true" , true , ( ( Boolean ) request.getAttribute( "showTabOne" ) ).booleanValue() );

    }
    /**
     @valid
     @test test_case_id="WC-FLT-ADV-TP-057"
     description="After unPick check whether "unPickUpStatus" request attribute set a boolean value "
     */
    public void testUnPickUpStatus()
    {
        alertUnPickUpPerform();

        Object unPickUpStatus;
        unPickUpStatus = request.getAttribute( "UnPickStatus" );

        assertNotNull( "unPickUpStatus attribute is null" , unPickUpStatus );

        assertEquals( "unPickUpStatus is not true" , "true" , unPickUpStatus );
    }
    /**
     @valid
     @test test_case_id="WC-FLT-ADV-TP-058"
     description="Alert Unpick for a unauthorized user"
     */
    public void testUnPickUpStatusForNonAuthorizedUser()
    {
        session.setAttribute( "userName" , unAuthorizedUser );
        alertUnPickUpPerform();

        verifyTilesForward( "messagePage" , "message" );
    }

    private void clearAlertPerform()
    {
        addRequestParameter( "method" , "clearAlert" );
        actionPerform();
        //TODO : entity - alarm which is not clear -should be set
    }
    /**
     @valid
     @test test_case_id="WC-FLT-ADV-TP-059"
     description="Set the necessary parameter(method = "clearAlert") in the request and check whether request is forwarded to "alarmDetail""
     */
    public void testClearAlertForward()
    {
        clearAlertPerform();

        verifyForward( "alarmDetails" );

        checkForAttr( entity , "entity" );

        verifyNoActionErrors();
    }
    /**
     @valid
     @test test_case_id="WC-FLT-ADV-TP-060"
     description="After alert clear, check whether necessary parameters are set in the request attribute"
     */
    public void testClearReqAttrib()
    {
        clearAlertPerform();

        String ownerName = alert.getWho();
        ownerName = ( ownerName == null ) ? "-" : ownerName;
        String groupName = alert.getGroupName();
        Properties alertProp = alert.getProperties();

        myAssert( "ownerName" , ownerName , ( String ) request.getAttribute( "ownerName" ) );
        myAssert( "groupName" , groupName , ( String ) request.getAttribute( "groupName" ) );
        myAssert( "severity" , "Clear" , ( String ) request.getAttribute( "severity" ) );
        assertEquals( "ShowTabOne's value is not true" , true , ( ( Boolean ) request.getAttribute( "showTabOne" ) ).booleanValue() );

    }
    /**
     @valid
     @test test_case_id="WC-FLT-ADV-TP-061"
     description="Clear Alert for authorized user"
     */
    public void testClearAlertForAuthorizedUser()
    {
        session.setAttribute( "userName" , userName ); //TODO : here we have to give user name who should  have authorization to perform this action.
        clearAlertPerform();
        String severity = ( String ) request.getAttribute( "severity" );

        assertEquals( "After clearing the alert, severity of the alert is not Clear", "Clear" , severity );
    }
    /**
     @valid
     @test test_case_id="WC-FLT-ADV-TP-062"
     description="Clear Alert for unauthorized user"
     */
    public void testClearAlertForNonAuthorizedUser()
    {
        session.setAttribute( "userName" , unAuthorizedUser );
        clearAlertPerform();

        verifyTilesForward( "messagePage" , "message" );
    }
    /**
     @valid
     @test test_case_id="WC-FLT-ADV-TP-063"
     description="Set the necessary parameter(method = "doAnnotate") in the request and check whether request is forwarded to "setAnnotation""
     */
    public void testDoAnnotateForward()
    {
        addRequestParameter( "method" , "doAnnotate" );
        actionPerform();

        verifyForward( "setAnnotation" );

        checkForAttr( entity , "entity" );

        verifyNoActionErrors();
    }
    /**
     @valid
     @test test_case_id="WC-FLT-ADV-TP-064"
     description="check for the "annotationWidnow" attribute"
     */
    /*public void testAnnotationWindowAttr()
    {
        addRequestParameter( "method" , "doAnnotate" );
        actionPerform();
        Boolean annotationWindow;
        annotationWindow = ( Boolean ) request.getAttribute( "annotationWindow" );

        assertEquals( true , annotationWindow.booleanValue() );
    }*/

    private void setAnnotationPerform()
    {
        addRequestParameter( "method" , "setAnnotation" );
        addRequestParameter( "text" , "annotationMessage" );
        actionPerform();
    }
    /**
     @valid
     @test test_case_id="WC-FLT-ADV-TP-065"
     description="Set the necessary parameter(method = "setAnnotation") in the request and check whether request is forwarded to "setAnnotation""
     */
    public void testSetAnnotationForward()
    {
        setAnnotationPerform();

        verifyForward( "annotationStatus" );

        checkForAttr( entity , "entity" );

        verifyNoActionErrors();
    }
    /**
     @valid
     @test test_case_id="WC-FLT-ADV-TP-066"
     description="Annotate action for Authorized User"
     */
    public void testAnnotateForAuthorizedUser()
    {
        session.setAttribute( "userName" , userName ); //TODO User name hard coding - Valid user
        setAnnotationPerform();
        Boolean isAlertAnnotated;
        isAlertAnnotated = ( Boolean ) request.getAttribute( "isAlertAnnotated" );

        assertEquals( true , isAlertAnnotated.booleanValue() );
    }
    /**
     @valid
     @test test_case_id="WC-FLT-ADV-TP-067"
     description="Annotate action for unauthorized User"
     */
    public void testAnnotateForNonAuthorizedUser()
    {
        session.setAttribute( "userName" , unAuthorizedUser );
        setAnnotationPerform();

        verifyTilesForward( "messagePage" , "message" );
    }

    private void viewAnnotationAndHistoryPerform()
    {
        addRequestParameter( "method" , "viewAnnotationAndHistory" );
        actionPerform();
    }
    /**
     @valid
     @test test_case_id="WC-FLT-ADV-TP-068"
     description="Set the necessary parameter(method = "viewAnnotationAndHistory") in the request and check whether request is forwarded to "annAndHistory""
     */
    public void testViewAlertAnnotationandHistoryForward()
    {
        viewAnnotationAndHistoryPerform();

        verifyForward( "annAndHistory" );

        checkForAttr( entity , "entity" );

        verifyNoActionErrors();
    }
    /**
     @valid
     @test test_case_id="WC-FLT-ADV-TP-069"
     description="ViewAnnotationAndHistory for AuthorizedUser"
     */
    public void testViewAnnotationAndHistoryForAuthorizedUser()
    {
        session.setAttribute( "userName" , userName ); //TODO User name hard coding - Valid user
        viewAnnotationAndHistoryPerform();
        Vector annotationValue;
        annotationValue = ( Vector ) request.getAttribute( "annotationValue" );

        assertNotNull( "annotationValue is null" , annotationValue );
    }
    /**
     @valid
     @test test_case_id="WC-FLT-ADV-TP-070"
     description="ViewAnnotationAndHistory for unauthorizedUser"
     */
    public void testViewAnnotationAndHistoryForNonAuthorizedUser()
    {
        session.setAttribute( "userName" , unAuthorizedUser );
        viewAnnotationAndHistoryPerform();

        verifyTilesForward( "messagePage" , "message" );
    }

    /* Test has to be added whether added Annotation Message is coming when we get the annotation messages
       public void testAnnotate()
       {
           addRequestParameter( "method" , "setAnnotation" );
           addRequestParameter( "annotationMessage" , "annotationMessage" );
           actionPerform();
       }*/

    /**
     @valid
     @test test_case_id="WC-FLT-ADV-TP-071"
     description="aftter viewAnnoationAndHistory action, check whether necessary parameters are set in the request attribute"
     */
    public void testViewAnnotationAndHistoryReqAttrib()
    {
        session.setAttribute( "userName" , userName ); //TODO User name hard coding - Valid user
        viewAnnotationAndHistoryPerform();
        Vector alertHistory;
        alertHistory = ( Vector ) request.getAttribute( "alertHistory" );

        assertNotNull( "alertHistory is null" , alertHistory );
    }
    /**
     @valid
     @test test_case_id="WC-FLT-ADV-TP-072"
     description=" View Alert History for unauthorized user"
     */
    public void testViewAlertHistoryForNonAuthorizedUser()
    {
        session.setAttribute( "userName" , unAuthorizedUser );
        viewAnnotationAndHistoryPerform();

        String hisFailure = (String) request.getAttribute("hisFailure");
        assertNotNull("hisFailure string is null" , hisFailure);
    }

    private void viewMergedAnnotationAndHistoryPerform()
    {
        addRequestParameter( "method" , "viewMergedAnnotationAndHistory" );
        actionPerform();
    }
    /**
     @valid
     @test test_case_id="WC-FLT-ADV-TP-073"
     description="Set the necessary parameter(method = "viewMergedAnnotationAndHistory") in the request and check whether request is forwarded to "viewMergedNotes""
     */
    public void testViewMergedAnnotationAndHistoryForward()
    {
        viewMergedAnnotationAndHistoryPerform();

        verifyForward( "viewMergedNotes" );

        checkForAttr( entity , "entity" );

        verifyNoActionErrors();
    }
    /**
     @valid
     @test test_case_id="WC-FLT-ADV-TP-074"
     description="Check MergedAnnotationAndHistory action for authorized user"
     */
    public void testViewMergedAnnotationAndHistoryForAuthorizedUser()
    {
        session.setAttribute( "userName" , userName ); //TODO User name hard coding - Valid user
        viewMergedAnnotationAndHistoryPerform();
        Vector mergedNotes;
        mergedNotes = ( Vector ) request.getAttribute( "mergedNotes" );

        assertNotNull( "alertHistory is null" , mergedNotes );
    }
    /**
     @valid
     @test test_case_id="WC-FLT-ADV-TP-075"
     description="Check MergedAnnotationAndHistory action for unauthorized user"
     */
    public void testViewMergedAnnotationAndHistoryForNonAuthorizedUser()
    {
        session.setAttribute( "userName" , unAuthorizedUser );
        viewMergedAnnotationAndHistoryPerform();

        verifyTilesForward( "messagePage" , "message" );
    }

    public static junit.framework.Test suite()
    {
        return new junit.framework.TestSuite( TestAlarmOperationsDispatchAction.class );
    }

    public static void main( String[] args )
    {
        junit.textui.TestRunner.run( TestAlarmOperationsDispatchAction.class );
    }

}
