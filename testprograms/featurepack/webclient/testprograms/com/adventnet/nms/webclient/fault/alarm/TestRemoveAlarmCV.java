//$Id: TestRemoveAlarmCV.java,v 1.2 2003/10/22 15:19:11 rameshkumarp Exp $

/**
 * @(#)TestRemoveAlarmCV.java
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

import com.adventnet.nms.fe.alert.AlertSessionBean;
import com.adventnet.nms.fe.common.CustomViewException;
import com.adventnet.nms.fe.common.CustomViewProperties;

import com.adventnet.nms.webclient.fault.utils.FaultTestUtils;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

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
public class TestRemoveAlarmCV extends servletunit.struts.CactusStrutsTestCase
{

    private String authorizedAlarmUser = "AlarmAuthorizedUser";

    public TestRemoveAlarmCV( String theName )
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
        setRequestPathInfo( "/removeAlarmCV" );

        session.setAttribute( "userName" , authorizedAlarmUser );
        addRequestParameter( "viewId" , "Alerts" );
    }

    private String createCustomView( String viewName , String parentViewID , String userName ) throws CustomViewException , RemoteException
    {
        AlertSessionBean alertSessionBean = new FaultTestUtils().getAlertSessionBeanHandle();
        String createdCvVIewID = "";

        CustomViewProperties customViewProperties = alertSessionBean.getViewProperties( userName , parentViewID );
        Properties panelProperties = customViewProperties.getPanelProperties();
        panelProperties.setProperty( "parent" , "Alerts" );
        Properties criteriaProperties = customViewProperties.getCriteriaProperties();
        createdCvVIewID = alertSessionBean.createCustomView( userName , viewName , "" , null , panelProperties , criteriaProperties );

        return createdCvVIewID;
    }

    /**
     @valid
     @test test_case_id=""
     description=""
     */
    public void testcvResultFwd() throws CustomViewException , RemoteException
    {
        String viewName = this.getName();
        String parentViewID = "Alerts";
        String viewID = createCustomView( viewName , parentViewID , authorizedAlarmUser );
        addRequestParameter( "viewId" , viewID );

        actionPerform();

        verifyForward( "cvResult" );
        verifyNoActionErrors();
    }

    /**
     @valid
     @test test_case_id=""
     description=""
     */
    public void testSetAttribute() throws CustomViewException , RemoteException
    {
        String viewName = this.getName();
        String parentViewID = "Alerts";
        String viewID = createCustomView( viewName , parentViewID , authorizedAlarmUser );
        addRequestParameter( "viewId" , viewID );

        actionPerform();

        assertEquals( "viewId attribute is not set correctly" , parentViewID , ( String ) request.getAttribute( "viewId" ) );
        assertEquals( "action attribute is not set correctly" , "/fault/AlarmView.do" , ( String ) request.getAttribute( "action" ) );
    }

    /**
     @valid
     @test test_case_id=""
     description=""
     */
    public void testCvRemoval() throws CustomViewException , RemoteException
    {
        String viewName = this.getName();
        String parentViewID = "Events";
        String viewID = createCustomView( viewName , parentViewID , authorizedAlarmUser );
        addRequestParameter( "viewId" , viewID );

        actionPerform();

        AlertSessionBean alertSessionBean = new FaultTestUtils().getAlertSessionBeanHandle();
        Hashtable viewIdsAndProps = alertSessionBean.getAllViewIDsAndProps( authorizedAlarmUser );
        assertEquals( "Custom View is not removed" , false , viewIdsAndProps.containsKey( viewID ) );

    }


    public static Test suite()
    {
        return new TestSuite( TestRemoveAlarmCV.class );
    }

    public static void main( String[] args )
    {
        TestRunner.run( TestRemoveAlarmCV.class );
    }

}