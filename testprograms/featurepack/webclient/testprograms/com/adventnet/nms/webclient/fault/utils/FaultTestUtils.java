//$Id: FaultTestUtils.java,v 1.3 2003/10/22 15:19:46 rameshkumarp Exp $

/**
 * @(#)FaultTestUtils.java
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

package com.adventnet.nms.webclient.fault.utils;

import java.util.*;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.net.MalformedURLException;

import com.adventnet.nms.eventdb.EventAPI;
import com.adventnet.nms.eventdb.Event;

import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.util.TimeoutException;
import com.adventnet.nms.util.AccessDeniedException;

import com.adventnet.nms.store.NmsStorageException;
import com.adventnet.nms.fault.FaultException;
import com.adventnet.nms.alertdb.Alert;
import com.adventnet.nms.alertdb.AlertAPI;
import com.adventnet.nms.alertdb.AlertAnnotation;

import com.adventnet.nms.fe.event.EventSessionBean;
import com.adventnet.nms.fe.event.EventFE;
import com.adventnet.nms.fe.common.ModuleNotInitializedException;
import com.adventnet.nms.fe.common.NmsTreeAPI;
import com.adventnet.nms.fe.common.CustomViewException;
import com.adventnet.nms.fe.alert.AlertSessionBean;
import com.adventnet.nms.fe.alert.AlertFE;

import com.adventnet.management.transaction.UserTransactionException;

public class FaultTestUtils
{

    private String hostName = "localhost";

    /**
     * To get the EventSessionBean handle from EventFE module
     * @return EventSessionBean handle
     */
    public EventSessionBean getEventSessionBeanHandle()
    {
        EventFE eventFE = new EventFE();
        EventSessionBean eventSessionBean = null;
        if ( eventFE.isInitialized() ) {
            try {
                eventSessionBean = EventFE.getEventSession();
            } catch ( ModuleNotInitializedException e ) {
                System.out.println( "EventFE module is not initialised" );
                e.printStackTrace();
                return null;
            }
        }
        return eventSessionBean;
    }

    /**
     * To get the EventSessionBean handle from EventFE module
     * @return EventSessionBean handle
     */
    public AlertSessionBean getAlertSessionBeanHandle()
    {
        AlertFE alertFE = new AlertFE();
        AlertSessionBean alertSessionBean = null;
        if ( alertFE.isInitialized() ) {
            try {
                alertSessionBean = AlertFE.getAlertSession();
            } catch ( ModuleNotInitializedException e ) {
                System.out.println( "EventFE module is not initialised" );
                e.printStackTrace();
                return null;
            }
        }
        return alertSessionBean;
    }

    /**
     * To get NmsTreeAPI handle
     * @return NmsTreeAPI handle
     */
    public NmsTreeAPI getNmsTreeAPI()
    {
        NmsTreeAPI treeAPI = ( NmsTreeAPI ) NmsUtil.getAPI( "TreeAPI" );
        if ( treeAPI != null ) {
            return treeAPI;
        } else {
            String apiString = "//" + hostName + "/TreeAPI";
            try {
                treeAPI = ( NmsTreeAPI ) Naming.lookup( apiString );
            } catch ( NotBoundException e ) {
                e.printStackTrace();
            } catch ( MalformedURLException e ) {
                e.printStackTrace();
            } catch ( RemoteException e ) {
                e.printStackTrace();
            }
        }
        return treeAPI;
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

    public Alert getAlertForEvent( Event event )
    {
        AlertAPI alertAPI;
        Alert alert = null;
        String entity;

        alertAPI = this.getAlertAPI();
        entity = event.getEntity();
        try {
            alert = alertAPI.checkOutIfAvailable( entity );
            alertAPI.unlock( alert );
        } catch ( RemoteException e ) {
            e.printStackTrace();
        } catch ( TimeoutException e ) {
            e.printStackTrace();
        } catch ( FaultException e ) {
            e.printStackTrace();
        }

        return alert;
    }

    /**
     * Adds the annotation message to the alert
     * @param alert to which the annotation has to be added
     * @param annotationMessage the annotation message
     */
    public void addAnnotation( Alert alert , String annotationMessage )
    {
        AlertAPI alertAPI;
        AlertAnnotation alertAnnotation;

        alertAPI = this.getAlertAPI();
        alertAnnotation = new AlertAnnotation();
        alertAnnotation.setEntity( alert.getEntity() );
        String who = alert.getWho();
        if ( who == null ) {
            System.out.println( "who is null so setting to root." );
            who = "root";
        }
        alertAnnotation.setWho( who );
        alertAnnotation.setNotes( annotationMessage );


        try {
            alertAPI.addAnnotation( alertAnnotation );
        } catch ( RemoteException e ) {
            e.printStackTrace();
        } catch ( UserTransactionException e ) {
            e.printStackTrace();
        } catch ( FaultException e ) {
            e.printStackTrace();
        }
    }

    /**
     * Adds an event with incoming properties and returns the added property.
     * @param eventProp Properties of the event to be added
     * @return event added to the DB
     */
    public Event addAndReturnEvent( Properties eventProp )
    {
        System.out.println( "Before Adding " );

        EventAPI eventAPI;
        Vector eventVector;
        Event event;
        Properties eventPropClone;

        eventPropClone = ( Properties ) eventProp.clone();
        eventAPI = this.getEventAPI();
        eventVector = null;
        event = new Event();
        event.setProperties( eventProp );
        try {
            eventAPI.addEvent( event );
        } catch ( RemoteException e ) {
            System.out.println( "Event is not added due to the following problem :" );
            e.printStackTrace();
        } catch ( NmsStorageException e ) {
            System.out.println( "Event is not added due to the following problem :" );
            e.printStackTrace();
        } catch ( FaultException e ) {
            System.out.println( "Event is not added due to the following problem :" );
            e.printStackTrace();
        }
        try {
            try {
                Thread.sleep( 1000 );
            } catch ( InterruptedException e ) {
                e.printStackTrace();
            }

            eventVector = eventAPI.getObjects( "Event" , eventPropClone );
        } catch ( RemoteException e ) {
            System.out.println( "Events are not fetched from the DB due to the following problem" );
            e.printStackTrace();
        } catch ( FaultException e ) {
            System.out.println( "Events are not fetched from the DB due to the following problem" );
            e.printStackTrace();
        }

        return ( Event ) eventVector.firstElement();
    }

    public boolean addAnnotation( String entity , String userName , String annotation )
    {
        AlertAPI alertAPI;
        Alert alert;

        alertAPI = this.getAlertAPI();
        try {
            alert = alertAPI.checkOutIfAvailable( entity );
            if ( alert == null ) {
                System.out.println( "Alert with given entity is not found" );
                return false;
            }
            alertAPI.updateNotes( alert , userName , annotation , true );
            alertAPI.unlock( alert );
        } catch ( RemoteException e ) {
            e.printStackTrace();
            return false;
        } catch ( TimeoutException e ) {
            e.printStackTrace();
            return false;
        } catch ( FaultException e ) {
            e.printStackTrace();
            return false;
        } catch ( AccessDeniedException e ) {
            e.printStackTrace();
            return false;
        } catch ( UserTransactionException e ) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    public boolean loginHtmlClient( String userName , String password )
    {
        return true;
    }

    public boolean isCVPresent( String userName , String viewId )
    {
        EventSessionBean eventSessionBean = getEventSessionBeanHandle();
        Hashtable viewHashtable = null;
        try {
            viewHashtable = eventSessionBean.getAllViewIDsAndProps( userName );
            if ( viewHashtable.containsKey( viewId ) ) {
                return true;
            }
        } catch ( RemoteException e ) {
            e.printStackTrace();
        } catch ( CustomViewException e ) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isAlarmCVPresent( String userName , String viewId )
    {
        AlertSessionBean alerttSessionBean = getAlertSessionBeanHandle();
        Hashtable viewHashtable = null;
        try {
            viewHashtable = alerttSessionBean.getAllViewIDsAndProps( userName );
            if ( viewHashtable.containsKey( viewId ) ) {
                return true;
            }
        } catch ( RemoteException e ) {
            e.printStackTrace();
        } catch ( CustomViewException e ) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean removeCustomView( String userName , String viewId )
    {
        boolean result = false;
        if ( !isCVPresent( userName , viewId ) ) {
            System.out.println( "Custom View " + viewId + " is already not present" );
            return true;
        }
        EventSessionBean eventSessionBean = getEventSessionBeanHandle();
        try {
            result = eventSessionBean.removeCustomView( userName , viewId );
        } catch ( RemoteException e ) {
            e.printStackTrace();
        } catch ( CustomViewException e ) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean removeAlarmCustomView( String userName , String viewId )
    {
        boolean result = false;
        if ( !isAlarmCVPresent( userName , viewId ) ) {
            System.out.println( "Custom View " + viewId + " is already not present" );
            return true;
        }
        AlertSessionBean alertSessionBean = getAlertSessionBeanHandle();
        try {
            result = alertSessionBean.removeCustomView( userName , viewId );
        } catch ( RemoteException e ) {
            e.printStackTrace();
        } catch ( CustomViewException e ) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main( String[] args )
    {
        FaultTestUtils faultTestUtils;
        faultTestUtils = new FaultTestUtils();
        Properties eventProp = new Properties();
        eventProp.setProperty( "severity" , "4" );
        eventProp.setProperty( "entity" , "myentity" );
        eventProp.setProperty( "source" , "rameshkumarp" );
        eventProp.setProperty( "category" , "mycategory" );
        eventProp.setProperty( "text" , "mytext" );
        String currentTime = new String( new Long( System.currentTimeMillis() ).toString() );
        eventProp.setProperty( "time" , currentTime );
        eventProp.setProperty( "domain" , "mydomain" );
        eventProp.setProperty( "network" , "mynetwork" );
        eventProp.setProperty( "node" , "mynode" );
        eventProp.setProperty( "helpURL" , "myhelpURL" );
        eventProp.setProperty( "groupName" , "mygroupName" );
        Event event = faultTestUtils.addAndReturnEvent( eventProp );
        System.out.println( "Event = " + event.getEntity() );
        System.out.println( " event ==null" + ( event == null ) );
        Alert alert = faultTestUtils.getAlertForEvent( event );
        System.out.println( "alert = = null " + ( alert == null ) );
        faultTestUtils.addAnnotation( alert , "abcdefghiklmnopqrstuvwxyz" );
        //System.out.println( "Alert  Details = " + alert.getId() );*/

/*        for ( int i = 0 ; i < args.length ; i++ ) {
            String s = args[i];
            System.out.println( "arg["+i+"] = "+s );
        }*/

    }
}
