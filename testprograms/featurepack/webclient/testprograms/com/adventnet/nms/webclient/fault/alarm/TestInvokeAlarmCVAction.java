//$Id: TestInvokeAlarmCVAction.java,v 1.2 2003/10/22 15:17:28 rameshkumarp Exp $

/**
 * @(#)TestInvokeAlarmCVAction.java
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

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 @test suite="Alarm CV Form Invocation"
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
public class TestInvokeAlarmCVAction extends servletunit.struts.CactusStrutsTestCase
{
    private String authorizedAlarmUser = "AlarmAuthorizedUser"; //TODO user name hard coded
    public TestInvokeAlarmCVAction( String theName )
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
        setRequestPathInfo( "/invokeAlarmCV" );
        session.setAttribute( "userName" , authorizedAlarmUser );
        addRequestParameter( "viewId" , "Alerts" );
    }

    /**
     @valid
     @test test_case_id="WC-FLT-ACV-100"
     description="Forward checking"
     */
    public void testAddCvFwd()
    {
        actionPerform();

        verifyTilesForward( "alarmCV" , "alarmCVForm" );
        verifyNoActionErrors();
    }
    public static Test suite()
    {
        return new TestSuite( TestInvokeAlarmCVAction.class );
    }

    public static void main( String[] args )
    {
        TestRunner.run( TestInvokeAlarmCVAction.class );
    }

}