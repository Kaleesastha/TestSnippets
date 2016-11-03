/*$Id: SwitchDiscoveryFilter.java,v 1.13.4.1 2013/08/10 09:58:58 wesley Exp $*/

package test;

import java.util.Vector;
import java.util.Properties;
import java.text.MessageFormat;
import java.rmi.RemoteException;

import com.adventnet.snmp.snmp2.SnmpOID;
import com.adventnet.snmp.snmp2.SnmpVar;

import com.adventnet.snmp.beans.SnmpTarget;

import com.adventnet.nms.topodb.FoundFilter;
import com.adventnet.nms.topodb.TopoAPI;
import com.adventnet.nms.topodb.ManagedObject;
import com.adventnet.nms.topodb.TopoObject;
import com.adventnet.nms.topodb.TopoDB;
import com.adventnet.nms.topodb.TopoNotificationEvent;
import com.adventnet.nms.topodb.TopoActionListener;
import com.adventnet.nms.topodb.Node;
import com.adventnet.nms.topodb.SnmpNode;
import com.adventnet.nms.topodb.DBServer;

import com.adventnet.nms.severity.SeverityInfo;
import com.adventnet.nms.severity.SeverityIterator;

import com.adventnet.nms.util.NmsLogMgr;
import com.adventnet.nms.util.LockableObject;
import com.adventnet.nms.util.NmsUtil;
import com.adventnet.management.log.Log;


import com.adventnet.nms.store.NmsStorageException;
import com.adventnet.management.transaction.UserTransactionException; 


/**  
 *   SwitchDiscoveryFilter is a discoveryFilter that identifies a switch element from
 *   other elements and adds objects to the database as required by the SwitchMapExample.
 *   Basically it adds SwitchObject corresponding to the identified object and Port objects
 *   corresponding the ports in the Switch. It also sets properties of PortObjects by 
 *   getting values from the switch ports by Snmp queries.
**/
public class SwitchDiscoveryFilter implements FoundFilter, TopoActionListener {

    /**  Used to query the object's snmp agent for its port details. **/
    TopoAPI topoapi = null;
    
    public SwitchDiscoveryFilter() 
    {
        getTopoAPI();
        if ( topoapi != null ) {
            try {
                topoapi.addTopoBulkListener(new SwitchTopoListener());
                NmsLogMgr.TOPOUSER.log(NmsUtil.GetString("Successfully added SwitchTopoListener for bulk delete notifications"), Log.VERBOSE);
            } catch( Exception e1 ) {
                NmsLogMgr.TOPOERR.fail(NmsUtil.GetString("Exception adding SwitchTopoListener :"), e1);
            }

            try {
                topoapi.register(this);
                NmsLogMgr.TOPOUSER.log(NmsUtil.GetString("Successfully registered SwitchDiscoveryFilter as TopoActionListener"), Log.VERBOSE);//no i18n
            } catch ( Exception e2 ) {
                NmsLogMgr.TOPOERR.fail(NmsUtil.GetString("Exception registering SwitchDiscoveryFilter as TopoActionListener "), e2);//no i18n
            }
        }
        else
            System.err.println(NmsUtil.GetString("SwitchDiscoveryFilter unable to get TopoAPI handle during instantiation"));
    }

    // FoundFilter interface method.

    public ManagedObject filterObject(ManagedObject obj, TopoAPI tapi) 
        throws NmsStorageException, UserTransactionException 



    {
        if (obj == null)
            return null; 

    	if( !(obj instanceof TopoObject) ) return obj;
	
        if( !( (TopoObject) obj ).getIsNode() )
            return obj; 

        if( !( (TopoObject) obj ).getIsSNMP() )
            return obj; 

       SnmpTarget target = new SnmpTarget();
	   SwitchObject switchObj =null;
	   TopoObject tobj = null;
	   try
	   {
	   	tobj = (TopoObject)obj;
	   }
	   catch (Exception ex)
	   {
		 NmsLogMgr.TOPOERR.fail(NmsUtil.GetString("Class Cast Exception while type casting to TopoObject ") + obj.getName(), null); // no i18n	   
	   }
	   if (tobj != null)
	   {
		   String version = tobj.getVersion();
		   if (version.equalsIgnoreCase("v3"))
		   {
			   target.setSnmpVersion(target.VERSION3);
			   target.setPrincipal(tobj.getUserName());
			   target.setContextName(tobj.getContextName());
		   }
	   }
	   
	   try
	   {

        synchronized ( target ) 
        {
            String switchName = obj.getName();

            if ( !getIsSwitch ( (TopoObject)obj,target) ) {
                return obj;
            }

            switchObj = new SwitchObject();

            Properties objProp = ((SnmpNode) obj).getProperties();
            objProp.remove("name");
            objProp.remove("classname");
            objProp.remove("type");
            switchObj.setName( switchName );

            switchObj.setProperties( objProp );

            if ( !queryAndSetPropertiesForSwitch( switchObj,target) ) {
                NmsLogMgr.TOPOERR.fail(NmsUtil.GetString("SwitchDiscoveryFilter not adding SwitchObject for ") + switchName, null);
                return obj;
            }

            boolean added = false;
            try {
                if ( !tapi.isManagedObjectPresent( switchObj.getKey() ) ) {
					//Switch not present in DB, hence adding.
                    added = tapi.addObject( switchObj );
	                //port addition when a new switch is added to the DB
					if ( added ) {
						addPortObjectsForSwitch( switchObj, tapi,target);
					}                    
                }
				//handling the case of rediscovery when switch is already present in DB. Query and set switch properties as these may have changed during rediscovery.
                else
                {
                    switchObj=(SwitchObject)tapi.getByName(switchObj.getName());
                    queryAndSetPropertiesForSwitch( switchObj,target);                                       
                    addPortObjectsForSwitch( switchObj, tapi,target);                    
                }				

            } catch(NmsStorageException nmse) {
                throw nmse;
            } catch(UserTransactionException ute ) {
                throw ute;

            } catch( Exception ex ) {
				Object[] args={switchName};
                NmsLogMgr.TOPOERR.fail(MessageFormat.format(NmsUtil.GetString("Exception while trying to add switch object for {0} in SwitchDiscoveryFilter "),args), ex);
                return obj;
            }

             //If status propogation for containers is enabled, then the switch status would have got 
            //altered through propogation by now. Hence returning the object with proper status as the
            //object that is returned from here would be updated in DB if already present.
            if (DBServer.enableSPForRelations)
            {
                try 
                {
                    ManagedObject tempSwitch = (SwitchObject)tapi.checkOut( switchObj.getKey(),0,false,true);
                    if(tempSwitch != null)
                    {
                    	switchObj.setStatus(tempSwitch.getStatus());
                    }
                }
                catch( Exception ee )
                {
					Object[] args={switchObj};
                    NmsLogMgr.TOPOERR.fail(MessageFormat.format(NmsUtil.GetString("Exception getting switch object {0} from database "),args),ee);
                }
            }
			}
	   }
	   finally
	   {
		   target.releaseResources();

	   }
				
	   return null;
    }
    
    /**
     * The TopoActionListener interface implementation. This method handles the SwitchObject deletion
     *
     */
    public void update(TopoNotificationEvent evt ) throws RemoteException
	{

		String type=evt.getUpdateType();

        if ( !type.startsWith("Deleted") )
            return;

		ManagedObject obj=evt.getOldManagedObject();
        if ( !( obj instanceof SwitchObject ) )
            return;

        getTopoAPI();

        if ( topoapi == null ) {
			Object[] args={obj};
            NmsLogMgr.TOPOERR.fail(MessageFormat.format(NmsUtil.GetString("SwitchDiscoveryFilter not able to delete the ports of {0} after receving the delete notification as TopoActionListener : Unable to get TopoAPI handle"),args), null);//no i18n
            return;
        }

        String[] ports = obj.getChildrenKeys();

        for ( int i = 0; i < ports.length; i++ ) {
            String name = ports[i];
            ManagedObject portObj = null;
	    try {
                portObj = topoapi.checkOut(name,0,true,true);
                if( !( portObj instanceof PortObject ) )
                    continue;
                topoapi.deleteObject( portObj,false,true );
            } catch ( Exception ee ) {
                NmsLogMgr.TOPOERR.fail(NmsUtil.GetString("In SwitchDiscoveryFilter : Exception deleting port ") + name, ee);
            }
	    finally
	    {
		try
		    {
		    	if (portObj != null)
			{
			    topoapi.unlock(portObj);
			}
		    }
		    catch (Exception ei)
		    {
			    NmsLogMgr.TOPOERR.fail(MessageFormat.format(NmsUtil.GetString("Exception while unlocking port object "),portObj), ei); // no i18n
		    }
	    }
        }
    }

    private void getTopoAPI() {
        if( topoapi != null )
            return;
        topoapi = (TopoAPI) NmsUtil.getAPI("TopoAPI");
    }

    private boolean getIsSwitch( TopoObject switchObj,SnmpTarget target) {

	    String switchName = switchObj.getName();
	    //Query object for the number of ports.
	    target.setTargetHost( switchObj.getIpAddress() );
	    target.setTargetPort( switchObj.getSnmpport() );	//Getting the Port Value
	    target.setCommunity(switchObj.getCommunity());
	    target.setAttemptPartial(true);

	    target.setSnmpOID( new SnmpOID("17.1.2.0") );
	    String numPortsVal = target.snmpGet();

	    if ( numPortsVal == null || numPortsVal.equalsIgnoreCase("null") )
		    return false;
	    else
		    return true;
	    //an managedobject to be discovered as switch it need not implement root port and root cost OID.

	    /*target.setSnmpOID(new SnmpOID("17.2.7.0")); //Spt root port
	      String splRootPort = target.snmpGet();
	      return ( (splRootPort != null) && (!splRootPort.equalsIgnoreCase("null")) ); */ //no i18n

    }

    private boolean queryAndSetPropertiesForSwitch( SwitchObject switchObj,SnmpTarget target) {

	    Properties p = new Properties();
	    SnmpOID numPorts = new SnmpOID(".1.3.6.1.2.1.17.1.2.0");
	    SnmpOID rootPort = new SnmpOID(".1.3.6.1.2.1.17.2.7.0");
	    SnmpOID rootCost = new SnmpOID(".1.3.6.1.2.1.17.2.6.0");
	    SnmpOID ipforwarding = new SnmpOID(".1.3.6.1.2.1.4.1.0"); //no i18n
	    String switchName = switchObj.getName();        
	    String val = null;
	    try {
		    //we check whether incoming object is router or not. If router we return the object
		    SnmpVar var = target.snmpGet(ipforwarding);
		    if(var != null)
		    {
			    val=var.toString();
			    if (val.equals("1") )
			    {
				    return false;
			    }
		    }
		    target.setSnmpOID( numPorts );
		    val = target.snmpGet();
		    if ( val == null ) {
			    Object[] args={switchName,target.getErrorString()};
			    NmsLogMgr.TOPOERR.fail(MessageFormat.format(NmsUtil.GetString("Error setting numPorts for {0} in SwitchDiscoveryFilter. Snmp value got is null : {1}"),args), null);
			    return false;
		    }
		    p.put("numPorts", val);

		    target.setSnmpOID( rootPort );
		    val = target.snmpGet();
		    if ( val == null ) {
			    Object[] args={switchName,target.getErrorString()};
			    NmsLogMgr.TOPOERR.fail(MessageFormat.format(NmsUtil.GetString("Error setting rootPort for {0} in SwitchDiscoveryFilter. Snmp value got is null : {1}"),args), null);
			    //return false;
			    val="null"; //No I18N
			    //since root port is not implemented we store null in the hash table
		    }
		    p.put("rootPort", val);

		    target.setSnmpOID( rootCost );
		    val = target.snmpGet();
		    if ( val == null ) {
			    Object[] args={switchName,target.getErrorString()};
			    NmsLogMgr.TOPOERR.fail(MessageFormat.format(NmsUtil.GetString("Error setting rootCost for {0} in SwitchDiscoveryFilter. Snmp value got is null : {1}"),args) , null);
			    //return false;
			    val="null"; //No I18N
			    //since rootcost is not implemented we store null in the hash table
		    }
		    p.put("rootCost", val);

		    switchObj.setProperties( p ); 
	    } 
	    catch ( Exception ee ) {
		    Object[] args={switchObj.getName()};
		    NmsLogMgr.TOPOERR.fail(MessageFormat.format(NmsUtil.GetString("Exception while setting properties for switch {0} in SwitchDiscoveryFilter "),args), ee);
		    return false;
	    }
	    return true;
    }


    private void addPortObjectsForSwitch( SwitchObject switchObj, TopoAPI tapi,SnmpTarget target)  
        throws NmsStorageException, UserTransactionException 



        {
            String switchName = switchObj.getName();
        int portNos = switchObj.getNumPorts();

        SnmpOID basePort = new SnmpOID(".1.3.6.1.2.1.17.1.4.1");
        SnmpOID basePortIndex = null;

        int[] portArr = new int[ portNos ];

        String portArrVal = null;
        target.setSnmpOID( basePort );
        
        for ( int i = 0; i < portNos; i++ ) {
            portArrVal= target.snmpGetNext(); 
            if ( portArrVal == null || portArrVal.equalsIgnoreCase("null") ) continue;
            try {
                portArr[ i ] = Integer.parseInt( portArrVal );
            }catch(NumberFormatException nfe){
				Object[] args={target.getTargetHost(),new Integer(i),portArrVal};
                NmsLogMgr.TOPOERR.fail(MessageFormat.format(NmsUtil.GetString("Exception getting value from mib for  {0} at port {1} value got is: {2}"),args), null);
            }
        }
        //PORT STATE FIX START
        int[] indexArr = new int[ portNos ];
        String index = null;
        target.setSnmpOID(new SnmpOID(".1.3.6.1.2.1.17.1.4.1.1")); //no i18n

        for ( int i = 0; i < portNos; i++ )
        {
            index= target.snmpGetNext();
            if ( index== null || index.equalsIgnoreCase("null") ) continue; //no i18n
            try
            {
                indexArr[ i ] = Integer.parseInt(index );
            }catch(NumberFormatException nfe)
            {
                Object[] args={target.getTargetHost(),new Integer(i),portArrVal};
                NmsLogMgr.TOPOERR.fail(MessageFormat.format(NmsUtil.GetString("Exception getting value from mib for  {0} at port {1} value got is: {2}"),args), null); //no i18n
            }
        }
        //PORT STATE FIX END
        int[] portIndexArr = new int[ portArr.length ];

        String[] childrenKeys = new String[ 1 ];
 
        for ( int i = 0; i < portArr.length; i++ ) {
            String oid = ".1.3.6.1.2.1.17.1.4.1.2." + portArr[i];
            basePortIndex = new SnmpOID( oid );
            target.setSnmpOID( basePortIndex );
            String portIndexVal = target.snmpGet();
            if ( portIndexVal == null || portIndexVal.equalsIgnoreCase("null") ) continue;
            try{
                portIndexArr[ i ] = Integer.parseInt( portIndexVal );
            }catch(NumberFormatException nfe){
				Object[] args={switchName,new Integer(i),portIndexVal};
                NmsLogMgr.TOPOERR.fail(MessageFormat.format(NmsUtil.GetString("Exception getting value from mib for (0) at port {1} value got is: {2}"),args), null); 
            }
        }

        for ( int i = 0; i < portIndexArr.length; i++ ) {
            String name = switchName + "_Port" + portIndexArr[i];
            PortObject port = null;
	    try {
		port = (PortObject)tapi.checkOut( name,0,true,true );
		if ( port != null ) {
                    if ( !port.getManaged() ) {
                        port.setManaged( true );
		//	tapi.lock(port,LockableObject.WRITE_LOCK,((TopoDB)tapi).getLockTimeout());
                        tapi.updateObject( port,false,true );
                    }
		    continue;
                }
	} catch(NmsStorageException nmse) {
	    throw nmse;
	} catch(UserTransactionException ute ) {
	    throw ute;

            } catch( Exception ex ) {
				Object[] args={name};
                NmsLogMgr.TOPOERR.fail(MessageFormat.format(NmsUtil.GetString("Unable to check if port object {0} already exists."),args), ex);
            }
	    finally
	    {
		    try
		    {
		    	if (port != null)
			{
			    tapi.unlock(port);
			}
		    }
		    catch (Exception ei)
		    {
			    NmsLogMgr.TOPOERR.fail(MessageFormat.format(NmsUtil.GetString("Exception while unlocking port object "),port), ei); // no i18n
		    }
	    }
            childrenKeys[0] = name;
            try {
                PortObject portObj = new PortObject();
                portObj.setName( name );
                portObj.setParentKey( switchName );

                // WebNMS Field added.
                if(switchObj.getWebNMS()!=null)
                {
                    portObj.setWebNMS(switchObj.getWebNMS());
                }

                portObj.setTester("usertest");
                portObj.setUClass("test.PortStatusPoller");
                portObj.setPortIfIndex( portIndexArr[i] );

                if (!queryAndSetPropertiesForPort( portObj, String.valueOf( portIndexArr[i] ),target,String.valueOf(indexArr[i]))) {
					Object[] args={name};
                    NmsLogMgr.TOPOERR.fail(MessageFormat.format(NmsUtil.GetString("Error while adding {0} in SwitchDiscoveryFilter. Aborting port additions."),args), null);
                    //return;
    		    continue; 		// when a port fails no other port gets discovered
                }

                if ( tapi.addObject( portObj ) ) {

                    switchObj.addChildrenKeys( childrenKeys );
                    SeverityIterator sevIter = SeverityInfo.getInstance().getIterator();
                    String portstatus = portObj.getPortState();
                    
                    if ( portstatus.equals("broken") ) {
                        if( sevIter.moveToHighest() ) {
                            portObj.setStatus( sevIter.getCurrent() );
                        }
                    }
                    else if ( portstatus.equals("disabled") || portstatus.equals("blocking") ) {
                        if( sevIter.moveToHighest() ) {
                            portObj.setStatus( sevIter.getPreviousCriticality() );
                        }
                    }
                    
                    if ( portObj.getStatus() != SeverityInfo.getInstance().getClear() )
                        ((TopoDB)tapi).genEvent("Port added in " + portstatus + " state",portObj);
                }

	} catch(NmsStorageException nmse) {
	    throw nmse;
	} catch(UserTransactionException ute ) {
	    throw ute;

            }
            catch ( Exception ex ) {
                NmsLogMgr.TOPOERR.fail(NmsUtil.GetString("Exception in SwitchDiscoveryFilter while adding port : ") + name, ex);
                return;
            }
        }
    }

    private boolean queryAndSetPropertiesForPort( PortObject portObj, String portIfIndexVal,SnmpTarget target,String portNumber ) {

        SnmpOID portState = null;
        SnmpOID portIfDescr = null;
        SnmpOID portIfSpeed = null;

        Properties p = new Properties();
        String portName = portObj.getName();
        String val = null;

        try {
            String descroid = ".1.3.6.1.2.1.2.2.1.2." + portIfIndexVal;
            portIfDescr = new SnmpOID( descroid );
            target.setSnmpOID( portIfDescr );
            val = target.snmpGet();
            if ( val == null ) {
				Object[] args={portName,target.getErrorString()};
                NmsLogMgr.TOPOERR.fail(MessageFormat.format(NmsUtil.GetString("Error setting portIfDescr for {0} in SwitchDiscoveryFilter. Snmp value got is null : {1}"),args), null);
                //return false;
		val="";		// If ifSpeed value for the Port is not got, Port will not get discovered.
            }
            p.put("portIfDescr", val);

            String speedoid = ".1.3.6.1.2.1.2.2.1.5." + portIfIndexVal;
            portIfSpeed = new SnmpOID( speedoid );
            target.setSnmpOID( portIfSpeed );
            val = target.snmpGet();
            if ( val == null ) {
				Object[] args={portName,target.getErrorString()};
                NmsLogMgr.TOPOERR.fail(MessageFormat.format(NmsUtil.GetString("Error setting portIfSpeed for {0} in SwitchDiscoveryFilter. Snmp value got is null : {1}"),args), null);
                //return false;
		val="0"; 	// If ifSpeed value for the Port is not got, Port will not get discovered.
            }
            p.put("portIfSpeed", val);

            String stateoid = ".1.3.6.1.2.1.17.2.15.1.3." + portNumber; //no i18n
            portState = new SnmpOID( stateoid );
            target.setSnmpOID( portState );
            val = target.snmpGet();
            if ( val == null || val.equalsIgnoreCase("null") ) {
				Object[] args={portName,target.getErrorString()};
                NmsLogMgr.TOPOERR.fail(MessageFormat.format(NmsUtil.GetString("Error setting portState for {0} in SwitchDiscoveryFilter. Snmp value got is null : {1}"),args), null);
                //return false;
		val="0";	// If ifSpeed value for the Port is not got, Port will not get discovered.
            }
            int portstatus = 0;
            try {
                portstatus = Integer.parseInt( val );
            }catch(NumberFormatException nfe){
				Object[] args={portName,val};
                NmsLogMgr.TOPOERR.fail(MessageFormat.format(NmsUtil.GetString("Exception setting portState for {0} in SwitchDiscoveryFilter. Snmp value got is : {1}"),args), null); 
            }

            if ( portstatus == 1 ) 
                p.put("portState","disabled");
            else if ( portstatus == 2 ) 
                p.put("portState","blocking");
            else if ( portstatus == 3 ) 
                p.put("portState","listening");
            else if ( portstatus == 4 ) 
                p.put("portState","learning");
            else if ( portstatus == 5 )
                p.put("portState","forwarding");
            else if ( portstatus == 6 )
                p.put("portState","broken");
            else
                p.put("portState","unknown");

            portObj.setProperties( p );
        }
        catch ( Exception ex ) {
			Object[] args={portIfIndexVal,portObj.getParentKey()};
            NmsLogMgr.TOPOERR.fail(MessageFormat.format(NmsUtil.GetString("Exception in SwitchDiscoveryFilter while getting snmp values and adding port of portIfIndex value : {0}  for switch  {1}"),args), ex);
            return false;
        }
        return true;
    }
    
}
