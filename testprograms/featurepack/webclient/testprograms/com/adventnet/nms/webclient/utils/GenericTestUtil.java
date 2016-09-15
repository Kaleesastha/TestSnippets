//$Id: GenericTestUtil.java,v 1.5 2003/10/20 17:35:58 srikrishnan Exp $

/**
 * @(#)GenericTestUtil.java
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

package com.adventnet.nms.webclient.utils;

import java.text.SimpleDateFormat;

import java.util.Properties;
import java.util.Vector;
import java.util.TimeZone;
import java.util.Date;

import java.rmi.RemoteException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.net.MalformedURLException;

import com.adventnet.nms.eventdb.EventAPI;
import com.adventnet.nms.eventdb.Event;
import com.adventnet.nms.alertdb.AlertAPI;
import com.adventnet.nms.severity.SeverityInfo;
import com.adventnet.nms.store.NmsStorageException;
import com.adventnet.nms.fault.FaultException;
import com.adventnet.nms.util.NmsUtil;

import com.adventnet.security.authorization.AuthorizationAdmin;
import com.adventnet.security.authorization.AuthorizationException;

public class GenericTestUtil
{
    String hostName;
    private EventAPI eventAPI;
    private AlertAPI alertAPI;
    private AuthorizationAdmin authAdmin;

    public GenericTestUtil( )
    {
        this.hostName = "localhost";
        this.eventAPI = getEventAPI();
        this.alertAPI = getAlertAPI();
        this.authAdmin = getAuthAdmin();
    }

    /**
     * Get the AlertAPI handle
     * @return handle to AlertAPI
     */
    public AlertAPI getAlertAPI()
    {
        AlertAPI alertAPI;

        alertAPI = ( AlertAPI ) NmsUtil.getAPI( "AlertAPI" );
        if ( alertAPI != null ) {
            return alertAPI;
        } else {
            String apiString = "//" + hostName + "/AlertAPI";
            System.out.println( " API String = " + apiString );
            try {
                alertAPI = ( AlertAPI ) Naming.lookup( apiString );
            } catch ( NotBoundException e ) {
                e.printStackTrace();
            } catch ( MalformedURLException e ) {
                e.printStackTrace();
            } catch ( RemoteException e ) {
                e.printStackTrace();
            }
            return alertAPI;
        }
    }

    /**
     * Get the EventAPI handle
     * @return handle to EventAPI
     */
    public EventAPI getEventAPI()
    {
        EventAPI eventAPI;
        eventAPI = ( EventAPI ) NmsUtil.getAPI( "EventAPI" );
        if ( eventAPI == null ) {
            String apiString = "//" + hostName + "/EventAPI";
            System.out.println( " API String = "  + apiString );
            try {
                eventAPI = ( EventAPI ) Naming.lookup( apiString );
            } catch ( NotBoundException e ) {
                e.printStackTrace();
            } catch ( MalformedURLException e ) {
                e.printStackTrace();
            } catch ( RemoteException e ) {
                e.printStackTrace();
            }
        }
        return eventAPI;
    }

    /**
     * To NmsAuthAdminAPI handle via AuthorizationAdmin
     * @return AuthorizationAdmin handle
     */
    public AuthorizationAdmin getAuthAdmin()
    {
        AuthorizationAdmin authorizationAdmin;
        authorizationAdmin = (AuthorizationAdmin)NmsUtil.getAPI("NmsAuthAdminAPI");
        if(authorizationAdmin == null) {
            String apiString = "//" + hostName + "/NmsAuthAdminAPI";
            System.out.println( " API String = " + apiString );
            try {
                authorizationAdmin = ( AuthorizationAdmin ) Naming.lookup( apiString );
            } catch ( NotBoundException e ) {
                e.printStackTrace();
            } catch ( MalformedURLException e ) {
                e.printStackTrace();
            } catch ( RemoteException e ) {
                e.printStackTrace();
            }
            if(authorizationAdmin == null) {
            System.out.println( "authorizationAdmin is null" );
            }
        }
        return authorizationAdmin;
    }

    /**
     * Formate a date (String) in to date/time string using TimeZone of the current host
     * @param longDateValue  String representation of long date value
     * @param dateFormat  Formate in which the date has to be returned
     * @return the formatted date/time string
     */
    public String getDate( String longDateValue , String dateFormat )
    {
        SimpleDateFormat simpleDateFormat;
        TimeZone timeZone;

        simpleDateFormat = new SimpleDateFormat( dateFormat );
        timeZone = TimeZone.getDefault();
        simpleDateFormat.setTimeZone( timeZone );
        String formattedDate = simpleDateFormat.format( new Date( Long.parseLong( longDateValue ) ) );

        return formattedDate;
    }

    private Properties getEventProperties()
    {
        Properties eventProp;
        eventProp = new Properties();
        eventProp.setProperty( "category" , "mycategory" );
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
     * First a critical event will be added will be added in to the database for the given entity and hence one alarm will be created for that event. Again one clear event will be added for that event which will change earlier critical alarm to clearm alarm
     * @param entity entity name for which the clear event will be added to the database and hence alarm
     *
     */
    public void generateClearAlerts(String entity)
    {
        SeverityInfo severityInfo = SeverityInfo.getInstance();
        Properties eventProp = getEventProperties();
        eventProp = getEventProperties();
        Event event = new Event();
        eventProp.put( "entity" , entity + "_" + severityInfo.getName( 1 ) + "_Event" );
        eventProp.put( "source" , entity );
        eventProp.put( "severity" , Integer.toString( 1 ) );
        eventProp.setProperty( "text" , "Event with severity " + severityInfo.getName( 1 ) + " added" );
        event.setProperties( eventProp );
        try {
            eventAPI.addEvent( event );
            eventProp.put( "entity" , entity + "_" + severityInfo.getName( 1 ) + "_Event" );
            eventProp.put( "source" , entity );
            eventProp.put( "severity" , Integer.toString( severityInfo.getClear() ) );
            eventProp.setProperty( "text" , "Event with clear severity added to generate a clear alert" );
            event.setProperties( eventProp );
            eventAPI.addEvent( event );
        } catch ( RemoteException e ) {
            e.printStackTrace();
        } catch ( NmsStorageException e ) {
            e.printStackTrace();
        } catch ( FaultException e ) {
            e.printStackTrace();
        }
    }

    /**
     * To generate events with all severities mentioned in the SeverityInfo.conf file for the given entity
     * @param entity entity for which the events to be generated
     */
    public void generateAllSeverityEvents( String entity )
    {
        SeverityInfo severityInfo = SeverityInfo.getInstance();
        Vector severityVector = severityInfo.getNames();
        int severityVectorLength = severityVector.size();
        for ( int i = 0 ; i < severityVectorLength ; i++ ) {
            String severity = ( String ) severityVector.elementAt( i );
            Properties eventProp = getEventProperties();
            eventProp.put( "entity" , entity + "_" + severity + "_Event" );
            eventProp.put( "source" , entity );
            eventProp.put( "severity" , Integer.toString( i + 1 ) );
            eventProp.setProperty( "text" , "Event with severity " + severity + " added" );
            Event event = new Event();
            event.setProperties( eventProp );
            try {
                eventAPI.addEvent( event );
            } catch ( RemoteException e ) {
                e.printStackTrace();
            } catch ( NmsStorageException e ) {
                e.printStackTrace();
            } catch ( FaultException e ) {
                e.printStackTrace();
            }
        }

    }

    public boolean createAdminGroup(String groupName)
    {
        try {
            Vector allGroupName = authAdmin.getAllGroups();
            if(allGroupName.contains(groupName))
            {
                System.out.println( "group already present" );
                return false;
            }
            Vector adminGroupView = authAdmin.getAllViewNames("Admin");
            int adminViewNameCount = adminGroupView.size();
            System.out.println( "Vector size = " +adminViewNameCount );
            for ( int i = 0 ; i < adminViewNameCount ; i++ ) {
                String viewName = ( String ) adminGroupView.elementAt( i );
                authAdmin.assignViewToGroup(groupName , viewName);
            }
        } catch ( RemoteException e ) {
            e.printStackTrace();
        } catch ( AuthorizationException e ) {
            e.printStackTrace();
        }
        return true;
    }

     public void createUser(String userName , String groupName )
     {
         try {
             authAdmin.createUser(userName , groupName);
         } catch ( RemoteException e ) {
             e.printStackTrace();
         } catch ( AuthorizationException e ) {
             e.printStackTrace();
         }
    }

    public void removeViewFromGroup( String groupName , String viewName  )
    {
        try {
            authAdmin.removeViewFromGroup(groupName , viewName);
        } catch ( RemoteException e ) {
            e.printStackTrace();
        } catch ( AuthorizationException e ) {
            e.printStackTrace();
        }
    }

    public static void main( String[] args )
    {
        GenericTestUtil genericTestUtil;
        genericTestUtil = new GenericTestUtil();

        //TODO Method Name and its corresponding number
        if ( args.length == 0 ) {
            System.out.println( "1 . getDate" );
            System.out.println( "2 . generateAllSeverityEvents" );
            System.out.println( "3 . generateAllSeverityAlarms" );
        }

        if ( args[0].equals( "getDate" ) ) {
            if ( args.length > 3 ) {
                System.out.println( "USAGE : java GenericTestUtils getDate <arg1: long value of the date> <arg2: Date Format default: MMM dd,yyyy hh:mm:ss a>" );
            }
            if ( args.length == 2 ) {
                System.out.println( "Date Value = " + genericTestUtil.getDate( args[1] , "MMM dd,yyyy hh:mm:ss a" ) );
            }
            if ( args.length == 3 ) {
                System.out.println( "Date Value = " + genericTestUtil.getDate( args[1] , args[2] ) );
            }
        }

        if(args[0].equals("generateAllSeverityEvents")) {
            if ( args.length != 2) {
                System.out.println( "USAGE : java GenericTestUtils  generateAllSeverityEvents <entity name>" );
            }
            genericTestUtil.generateAllSeverityEvents(args[1]);
        }
        if ( args[0].equals( "generateAllSeverityAlarms" ) ) {
            if ( args.length != 3 ) {
                System.out.println( "USAGE : java GenericTestUtils  generateAllSeverityEvents <entity name 1> <entity name 2>" );
            }
            genericTestUtil.generateAllSeverityEvents( args[1] );
            genericTestUtil.generateClearAlerts(args[2]);
        }
    }
}
