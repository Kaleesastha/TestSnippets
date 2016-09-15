//$Id: TestModifyEventViewColumns.java,v 1.2 2003/09/23 12:10:57 rameshkumarp Exp $

/**
 * @(#)TestModifyEventViewColumns.java
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

/**
 @test suite=""
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
public class TestModifyEventViewColumns extends servletunit.struts.CactusStrutsTestCase
{

    private String userName = null;

    public TestModifyEventViewColumns( String theName )
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
        setRequestPathInfo( "/ModifyEventCV" );
        userName = "root";
        session.setAttribute( "userName" , userName );
        addRequestParameter( "viewId" , "Events" );
        addRequestParameter( "selitems" , "id entity status" );

    }

    /**
     @valid 
     @test test_case_id="WC-FLT-ELV-117"
     description=""
     */

    public void testUpdateFwd()
    {
        actionPerform();
        verifyForward( "updated" );
    }

    public static junit.framework.Test suite()
    {
        return new junit.framework.TestSuite( TestModifyEventViewColumns.class );
    }

    public static void main( String[] args )
    {
        junit.textui.TestRunner.run( TestModifyEventViewColumns.class );
    }

}