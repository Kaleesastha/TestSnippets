//$Id: TestEventCvAction.java,v 1.3 2003/10/22 15:24:17 rameshkumarp Exp $

/**
 * @(#)TestEventCvAction.java
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
import java.rmi.RemoteException;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

import com.adventnet.nms.fe.common.CustomViewProperties;
import com.adventnet.nms.fe.common.CustomViewException;
import com.adventnet.nms.fe.event.EventSessionBean;

import com.adventnet.nms.webclient.fault.utils.FaultTestUtils;

/**
 @test suite=""
 owner="rameshkumarp"
 version="1.0"
 date="Oct 13, 2003"

 Introduction=""
 Acronymn=""
 PriorKnowledge=""
 Resource=""
 Links=""
 Preamble=""
 */
public class TestEventCvAction extends servletunit.struts.CactusStrutsTestCase
{

    private String authorizedEventUser = "AlarmAuthorizedUser";

    public TestEventCvAction( String theName )
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
        setRequestPathInfo( "/eventCVAction" );

        session.setAttribute( "userName" , authorizedEventUser );
        addRequestParameter( "viewId" , "Events" );
        addRequestParameter( "actionToPerform" , "create" );

    }

    /**
     @valid 
     @test test_case_id="WC-FLT-ECV-101"
     description="Forward checking"
     */
    public void testCvResultFwd()
    {
        addRequestParameter( "severity" , new String[]{"Info"} );
        addRequestParameter( "cvName" , this.getName() );
        addRequestParameter( "stringentity" , "sw*" );
        addRequestParameter( "eventAgeCategory" , "any" );

        actionPerform();

        verifyForward( "cvResult" );
        verifyNoActionErrors();
    }

    /**
     @valid
     @test test_case_id="WC-FLT-ECV-102"
     description="Attribute checking"
     */
    public void testSetAttributes()
    {

        addRequestParameter( "severity" , new String[]{"Info"} );
        addRequestParameter( "cvName" , this.getName() );
        addRequestParameter( "stringentity" , "sw*" );
        addRequestParameter( "eventAgeCategory" , "any" );

        actionPerform();

        assertEquals( "displayName is not same as the cvName " , this.getName() , ( String ) request.getAttribute( "displayName" ) );
        assertEquals( "action attribute is not set correctly" , "/fault/NetworkEvent.do" , ( String ) request.getAttribute( "action" ) );
    }

    /**
     @valid
     @test test_case_id="WC-FLT-ECV-103"
     description="Through Framework API check whether added CV is in the DB"
     */
    public void testCVCreate() throws RemoteException , CustomViewException
    {
        addRequestParameter( "severity" , new String[]{"Info"} );
        addRequestParameter( "cvName" , this.getName() );
        addRequestParameter( "stringentity" , "sw*" );
        addRequestParameter( "eventAgeCategory" , "any" );

        actionPerform();

        String viewId_AddedCV = ( String ) request.getAttribute( "viewId" );

        EventSessionBean eventSessionBean = new FaultTestUtils().getEventSessionBeanHandle();
        CustomViewProperties customViewProperties;
        customViewProperties = eventSessionBean.getViewProperties( authorizedEventUser , viewId_AddedCV );
        Properties viewProperties = customViewProperties.getCriteriaProperties();
        String severity = viewProperties.getProperty( "stringseverity" );

        assertEquals( "CV added for severity (Info) but added is different" , "Info" , severity );

    }

    /**
     @valid
     @test test_case_id="WC-FLT-ECV-104"
     description="Check whether modifyCV works by creating a new CV with info severity and modifying the CV with severity "Critical" and checking later on whether CV modified by getting the property of modified CV"
     */
    public void testmodifyCV() throws CustomViewException , RemoteException
    {
        addRequestParameter( "severity" , new String[]{"Info"} );
        addRequestParameter( "cvName" , this.getName() );
        addRequestParameter( "stringentity" , "sw*" );
        addRequestParameter( "eventAgeCategory" , "any" );

        actionPerform();     //To add a new CV

        String viewId_AddedCV = ( String ) request.getAttribute( "viewId" );

        addRequestParameter( "viewId" , viewId_AddedCV );
        addRequestParameter( "severity" , new String[]{"Critical"} );
        addRequestParameter( "actionToPerform" , "modify" );

        actionPerform();  // To modify the above added CV

        String viewId_ModifiedCV = ( String ) request.getAttribute( "viewId" );
        EventSessionBean eventSessionBean = new FaultTestUtils().getEventSessionBeanHandle();
        CustomViewProperties customViewProperties;
        customViewProperties = eventSessionBean.getViewProperties( authorizedEventUser , viewId_ModifiedCV );
        Properties viewProperties = customViewProperties.getCriteriaProperties();
        String severity = viewProperties.getProperty( "stringseverity" );

        assertEquals( "CV modified for severity (Critical) but added is different" , "Critical" , severity );
    }

    /**
     * To remove the CV added in each test method after its excution is over.
     */
    public void tearDown()
    {
        FaultTestUtils faultTestUtils = new FaultTestUtils();
        String viewId = ( String ) request.getAttribute( "viewId" );
        faultTestUtils.removeCustomView( authorizedEventUser , viewId );
    }

    public static Test suite()
    {
        return new TestSuite( TestEventCvAction.class );
    }

    public static void main( String[] args )
    {
        TestRunner.run( TestEventCvAction.class );
    }

}