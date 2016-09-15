//$Id: TestAlarmCvAction.java,v 1.3 2003/12/02 18:34:32 rameshkumarp Exp $

/**
 * @(#)TestAlarmCvAction.java
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

import java.rmi.RemoteException;
import java.util.*;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

import com.adventnet.nms.fe.common.CustomViewException;
import com.adventnet.nms.fe.common.CustomViewProperties;
import com.adventnet.nms.fe.alert.AlertSessionBean;

import com.adventnet.nms.webclient.fault.utils.FaultTestUtils;

/**
 @test suite=""
 owner="rameshkumarp"
 version="1.0"
 date="Oct 14, 2003"

 Introduction=""
 Acronymn=""
 PriorKnowledge=""
 Resource=""
 Links=""
 Preamble=""
 */
public class TestAlarmCvAction extends servletunit.struts.CactusStrutsTestCase
{

    private String authorizedAlarmUser = "AlarmAuthorizedUser";

    public TestAlarmCvAction( String theName )
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
        setRequestPathInfo( "/alarmCVAction" );

        session.setAttribute( "userName" , authorizedAlarmUser );
        addRequestParameter( "viewId" , "Alerts" );
        addRequestParameter( "actionToPerform" , "create" );

    }

    /**
     @valid
     @test test_case_id="WC-FLT-ACV-101"
     description="Forward checking"
     */
    public void testCvResultFwd()
    {
        addRequestParameter( "severity" , new String[]{"clear"} );
        addRequestParameter( "cvName" , this.getName() );
        addRequestParameter( "stringentity" , "sw*" );
        addRequestParameter( "alarmAgeCategory" , "any" );
        addRequestParameter( "previousseverity" , new String[]{"Major"} );

        actionPerform();

        verifyForward( "cvResult" );    //TODO TilesForward
        verifyNoActionErrors();

        String viewId = ( String ) request.getAttribute( "viewId" );
    }

    /**
     @valid
     @test test_case_id="WC-FLT-ACV-102"
     description="checking whether attributes are set in the request"
     */
    public void testSetAttributes()
    {
        addRequestParameter( "severity" , new String[]{"clear"} );
        addRequestParameter( "cvName" , this.getName() );
        addRequestParameter( "stringentity" , "sw*" );
        addRequestParameter( "alarmAgeCategory" , "any" );
        addRequestParameter( "previousseverity" , new String[]{"Major"} );

        actionPerform();

        assertEquals( "displayName is not same as the cvName " , this.getName() , ( String ) request.getAttribute( "displayName" ) );
        assertEquals( "action attribute is not set correctly" , "/fault/AlarmView.do" , ( String ) request.getAttribute( "action" ) );
    }

    /**
     @valid
     @test test_case_id="WC-FLT-ACV-103"
     description="Checking the creation of CV using framework API"
     */
    public void testCVCreate() throws RemoteException , CustomViewException
    {
        addRequestParameter( "severity" , new String[]{"Major"} );
        addRequestParameter( "cvName" , this.getName() );
        addRequestParameter( "stringentity" , "sw*" );
        addRequestParameter( "alarmAgeCategory" , "any" );

        actionPerform();

        String viewId_AddedCV = ( String ) request.getAttribute( "viewId" );

        AlertSessionBean alertSessionBean = new FaultTestUtils().getAlertSessionBeanHandle();
        CustomViewProperties customViewProperties;
        customViewProperties = alertSessionBean.getViewProperties( authorizedAlarmUser , viewId_AddedCV );
        Properties viewProperties = customViewProperties.getCriteriaProperties();
        String severity = viewProperties.getProperty( "stringseverity" );

        assertEquals( "CV added for severity (Info) but added is different" , "Major" , severity );
    }

    /**
     @valid
     @test test_case_id="WC-FLT-ACV-104"
     description="Checking the modification of CV using framework API"
     */

    public void testmodifyCV() throws CustomViewException , RemoteException
    {
        addRequestParameter( "severity" , new String[]{"Major"} );
        addRequestParameter( "cvName" , this.getName() );
        addRequestParameter( "stringentity" , "sw*" );
        addRequestParameter( "alarmAgeCategory" , "any" );
        addRequestParameter("previousseverity", new String[]{"Critical"});

        actionPerform();     //To add a new CV

        String viewId_AddedCV = ( String ) request.getAttribute( "viewId" );
        addRequestParameter( "viewId" , viewId_AddedCV );
        addRequestParameter( "severity" , new String[]{"Minor"} );
        addRequestParameter( "actionToPerform" , "modify" );

        actionPerform();  // To modify the above added CV

        String viewId_ModifiedCV = ( String ) request.getAttribute( "viewId" );
        AlertSessionBean alertSessionBean = new FaultTestUtils().getAlertSessionBeanHandle();
        CustomViewProperties customViewProperties;
        customViewProperties = alertSessionBean.getViewProperties( authorizedAlarmUser , viewId_ModifiedCV );
        Properties viewProperties = customViewProperties.getCriteriaProperties();
        String severity = viewProperties.getProperty( "stringseverity" );

        assertEquals( "CV modified for severity (Minor) but added is different" , "Minor" , severity );

    }

    /**
     * To remove the CV added in each test method after its excution is over.
     */
    public void tearDown()
    {
        FaultTestUtils faultTestUtils = new FaultTestUtils();
        String viewId = ( String ) request.getAttribute( "viewId" );
        faultTestUtils.removeAlarmCustomView( authorizedAlarmUser , viewId );
    }

    public static Test suite()
    {
        return new TestSuite( TestAlarmCvAction.class );
    }

    public static void main( String[] args )
    {
        TestRunner.run( TestAlarmCvAction.class );
    }

}