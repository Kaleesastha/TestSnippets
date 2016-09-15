//$Id: TestModifyAlarmViewColumns.java,v 1.2 2003/09/23 13:17:13 rameshkumarp Exp $

/**
 * @(#)TestModifyAlarmViewColumns.java
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

/**
 @test suite="Alart View Test Program"
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
public class TestModifyAlarmViewColumns extends servletunit.struts.CactusStrutsTestCase
{

    private String userName = null;

    public TestModifyAlarmViewColumns( String theName )
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
        setRequestPathInfo( "/ModifyAlarmCV" );
        userName = "root";
        session.setAttribute( "userName" , userName );
        addRequestParameter( "viewId" , "Alerts" );
        addRequestParameter( "selitems" , "id entity status" );

    }

    /**
     @valid 
     @test test_case_id="WC-FLT-ALV-119"
     description=""
     */
    public void testUpdateFwd()
    {
        actionPerform();
        verifyForward( "updated" );
    }

    public static junit.framework.Test suite()
    {
        return new junit.framework.TestSuite( TestModifyAlarmViewColumns.class );
    }

    public static void main( String[] args )
    {
        junit.textui.TestRunner.run( TestModifyAlarmViewColumns.class );
    }

}