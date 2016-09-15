//$Id: TestRemoveEventCV.java,v 1.3 2003/10/22 15:22:25 rameshkumarp Exp $

/**
 * @(#)TestRemoveEventCV.java
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
 date="Oct 14, 2003"

 Introduction=""
 Acronymn=""
 PriorKnowledge=""
 Resource=""
 Links=""
 Preamble=""
 */
public class TestRemoveEventCV extends servletunit.struts.CactusStrutsTestCase
{

    private String authorizedEventUser = "AlarmAuthorizedUser";

    public TestRemoveEventCV( String theName )
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
        setRequestPathInfo( "/removeEventCV" );

        session.setAttribute( "userName" , authorizedEventUser );
        request.setAttribute( "viewId" , "Events" );
    }

    private String createCustomView( String viewName , String parentViewID , String userName ) throws RemoteException , CustomViewException
    {
        EventSessionBean eventSessionBean = new FaultTestUtils().getEventSessionBeanHandle();
        String createdCvVIewID = "";
        CustomViewProperties customViewProperties = eventSessionBean.getViewProperties( userName , parentViewID );
        Properties panelProperties = customViewProperties.getPanelProperties();
        panelProperties.setProperty( "parent" , "Events" );
        Properties criteriaProperties = customViewProperties.getCriteriaProperties();
        createdCvVIewID = eventSessionBean.createCustomView( userName , viewName , "" , null , panelProperties , criteriaProperties );
        return createdCvVIewID;
    }

    /**
     @valid 
     @test test_case_id="WC-FLT-ECV-105"
     description="Checking Forward"
     */
    public void testcvResultFwd() throws RemoteException , CustomViewException
    {
        String viewName = this.getName();
        String parentViewID = "Events";
        String viewID = createCustomView( viewName , parentViewID , authorizedEventUser );
        addRequestParameter( "viewId" , viewID );

        actionPerform();
        verifyForward( "cvResult" );      //TODO Tiles Forward
        verifyNoActionErrors();
    }

    /**
     @valid
     @test test_case_id="WC-FLT-ECV-106"
     description="Checking attribute set in the request"
     */
    public void testSetAttribute() throws RemoteException , CustomViewException
    {
        String viewName = this.getName();
        String parentViewID = "Events";
        String viewID = createCustomView( viewName , parentViewID , authorizedEventUser );
        addRequestParameter( "viewId" , viewID );

        actionPerform();

        assertEquals( "viewId attribute is not set correctly" , parentViewID , ( String ) request.getAttribute( "viewId" ) );
        assertEquals( "action attribute is not set correctly" , "/fault/NetworkEvent.do" , ( String ) request.getAttribute( "action" ) );
    }

    /**
     @valid
     @test test_case_id="WC-FLT-ECV-107"
     description="checking the removal usign framework api"
     */
    public void testCvRemoval() throws RemoteException , CustomViewException
    {
        String viewName = this.getName();
        String parentViewID = "Events";
        String viewID = createCustomView( viewName , parentViewID , authorizedEventUser );
        addRequestParameter( "viewId" , viewID );

        actionPerform();

        EventSessionBean eventSessionBean = new FaultTestUtils().getEventSessionBeanHandle();

        Hashtable viewIdsAndProps = eventSessionBean.getAllViewIDsAndProps( authorizedEventUser );
        assertEquals( "Custom View is not removed" , false , viewIdsAndProps.containsKey( viewID ) );

    }

    public static Test suite()
    {
        return new TestSuite( TestRemoveEventCV.class );
    }

    public static void main( String[] args )
    {
        TestRunner.run( TestRemoveEventCV.class );
    }

}