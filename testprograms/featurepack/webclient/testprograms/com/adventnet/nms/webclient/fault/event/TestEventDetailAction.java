//$Id: TestEventDetailAction.java,v 1.2 2003/10/22 16:11:44 rameshkumarp Exp $

/**
 * @(#)TestEventDetailAction.java
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

import com.adventnet.nms.webclient.fault.utils.FaultTestUtils;

import com.adventnet.nms.eventdb.Event;

/**
 * @test suite="Event Details View"
 owner="rameshkumarp"
 version="1.0"
 date="Aug 14, 2003"
 */
public class TestEventDetailAction extends servletunit.struts.CactusStrutsTestCase
{

    private FaultTestUtils faultTestUtils = new FaultTestUtils();
    private String userName = null;
    private String id = null;
    private Event event;

    public TestEventDetailAction( String theName )
    {
        super( theName );
    }

    private Properties getEventProperties()
    {
        Properties eventProp;
        eventProp = new Properties();
        eventProp.put( "entity" , this.getName() );
        eventProp.put( "source" , this.getName() );
        eventProp.put( "severity" , "1" );
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
     * setUp method sets the "userName" in the session and "id" in the request paramater and also struts config file to be referred. Since the request pathinfo is common for all the test cases in this testsuite, it has been mentioned in the setUp method itself.
     */
    public void setUp()
    {
        try {
            super.setUp();
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        setConfigFile( "/webclient/fault/conf/fault-struts-config.xml" );
        setRequestPathInfo( "/EventDetails.do" );
        this.userName = "root"; //TODO user name should not be hard coded. it has to be got by someway
        event = faultTestUtils.addAndReturnEvent( getEventProperties() );
        this.id = ( new Integer( event.getId() ) ).toString();
        session.setAttribute( "userName" , userName );
        addRequestParameter( "id" , id );

    }

    /**
     @valid
     @test test_case_id="WC-FLT-EDV-011"
     description="Call the actionPerform and check the verify forward"
     */
    public void testEventDetailsForward()
    {
        actionPerform();
        verifyTilesForward( "eventDetails" , "eventdetailsPage" );
    }

    /**
     @valid
     @test test_case_id="WC-FLT-EDV-012"
     description="get the "eventProp" from request and check whether it is null or not  and also whether it is instance of Properties object"
     */
    public void testReqAttr()
    {
        actionPerform();
        Object obj = request.getAttribute( "eventProp" );

        assertNotNull( "data attribute is null" , obj );

        assertTrue( "eventProp object is not an instance of Properties" , ( obj instanceof Properties ) );
    }

    /**
     * This method first call the actionPerform method and return the value of the key in the "data" property object
     * @param key - key value whose value has to be got from the "data" property object
     * @return value of the key in the "data" property object
     */
    private String getPropValFromData( String key )
    {
        actionPerform();
        Properties dataProp = ( Properties ) request.getAttribute( "eventProp" );
        if ( !( dataProp.containsKey( key ) ) ) {
            return null;
        }
        return dataProp.getProperty( key );
    }

    /**
     @valid
     @test test_case_id="WC-FLT-EDV-013"
     description="using getPropValFromData method get the id value of the eventProp set in the request and check whether it is same as the id of the event whose detail is requested"
     */
    public void testDataID()
    {
        String actualID = getPropValFromData( "id" );
        assertEquals( "input ID and ID present in data properties are not same" , actualID , id );
    }

    public static junit.framework.Test suite()
    {
        return new junit.framework.TestSuite( TestEventDetailAction.class );
    }

    public static void main( String[] args )
    {
        junit.textui.TestRunner.run( TestEventDetailAction.class );
    }

}
