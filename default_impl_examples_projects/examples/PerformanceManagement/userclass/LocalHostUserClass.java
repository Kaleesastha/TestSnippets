/*
 * $Id: LocalHostUserClass.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
 * Copyright (c) 2002 Adventnet, Inc. All Rights Reserved.
 * Please read the associated COPYRIGHTS file for more details 
 * 
 * ADVENTNET, INC. MAKES NO REPRESENTATIONS OR WARRANTIES  ABOUT THE SUITABILITY
 * OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
 * NON-INFRINGEMENT. ADVENTNET, INC. SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS
 * SOFTWARE OR ITS DERIVATIVES.
 */
package test;

import com.adventnet.nms.poll.CheckMOProperties;
import com.adventnet.nms.topodb.ManagedObject;
import com.adventnet.nms.topodb.TopoObject;


/**
 * User Class implementation of CheckMOProperties interface. Here our aim is to
 * create a set of PolledData objects for the machine in which WebNMS is running
 * and for entities in localhost which are modelled as ManagedObjects. This
 * class will form a part of a Polling Object's match criteria. 
 * To include this class as part of match criteria , compile this class under
 * <Web NMS Home> /classes directory and give entry in Polling.conf (
 * <Web NMS Home> / conf directory) .
 * @version 1.0
 * @since 1.0
 * @see CheckMOProperties
 */


//Sample Entry in Polling.conf,

//  <POLLING_OBJECT name="localhost">
//  <MATCH_CRITERIA>
//  <USER_CLASS>
//  <CLASS_NAME class="test.LocalHostUserClass" />
// </USER_CLASS>
//  </MATCH_CRITERIA>
//  -----------
//  -----------
//  </POLLING_OBJECT>
// Give the entry as the first entry in Polling.conf . This is because as and
// when a ManagedObject is discovered , it will be passed through the set of
// Polling Objects defined in Polling.conf . Once this ManagedObject matches
// match criteria specified in a PollingObject , ManagedObject will not be
// compared against other PollingObjects. PollingObject match criteria
// comparision is done in the order given in Polling.conf

public class LocalHostUserClass implements CheckMOProperties
{
    String localhost=null;
    
    /**
     * Constructor. This class is instantiated at startup when PollingObjects
     * are created by reading Polling.conf.
     *
     */

    public LocalHostUserClass ()
    {
        try
        {
            //get the localhost name . 
            localhost =
                (String)java.net.InetAddress.getLocalHost().getHostName().toLowerCase();
        }
        catch(Exception e)
        {
            System.err.println(" Exception in user class "+e);
        }
        
    }

    /**
     * Whenever a ManagedObject is added to NMS , the Poll Engine will be
     * intimated and the Poll Engine will pass this ManagedObject through the
     * available set of PollingObjects to check if the ManagedObject matches any
     * PollingObject's match criteria. When this user class is encountered , the
     * ManagedObject is passed as a parameter to this method. Here we check
     * whether the ManagedObject name contains the localhost's name. If so
     * return true else return false. 
     */       
    public boolean checkProperties(ManagedObject mo)
    {
        if(localhost==null) return false;
        String name=mo.getName().toLowerCase();

        //check if name of ManagedObject contains localhost's name .
        if(name.indexOf(localhost) > -1)
        {
            return true;
        }
        else
        {
            return false;
        }
        
           
    }    
}// LocalHostUserClass







