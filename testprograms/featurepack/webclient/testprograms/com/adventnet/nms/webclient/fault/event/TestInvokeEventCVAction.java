//$Id: TestInvokeEventCVAction.java,v 1.2 2003/10/22 12:45:27 rameshkumarp Exp $

/**
 * @(#)TestInvokeEventCVAction.java
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

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 @test suite="Event Custom View - InvokeEventCVAction"
 owner="rameshkumarp"
 version="1.0"
 date="Oct 10, 2003"

 Introduction=""
 Acronymn=""
 PriorKnowledge=""
 Resource=""
 Links=""
 Preamble=""
 */
public class TestInvokeEventCVAction extends servletunit.struts.CactusStrutsTestCase
{
    private String authorizedEventUser = "AlarmAuthorizedUser";   //TODO user name hard coded
    public TestInvokeEventCVAction( String theName )
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
        setRequestPathInfo( "/invokeEventCV" );
        session.setAttribute( "userName" , authorizedEventUser );
        addRequestParameter("viewId" , "Events");
    }

    /**
     @valid 
     @test test_case_id="WC-FLT-ECV-100"
     description="VerifyTilesForward and verifynoactionerrors"
     */
    public void testAddCvFwd()
    {
        actionPerform();

        verifyTilesForward("eventCV" , "eventCVForm");
        verifyNoActionErrors();
    }

    public static Test suite()
    {
        return new TestSuite( TestInvokeEventCVAction.class );
    }

    public static void main( String[] args )
    {
        TestRunner.run( TestInvokeEventCVAction.class );
    }

}
