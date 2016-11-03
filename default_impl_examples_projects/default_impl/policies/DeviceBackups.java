
/* $Id: DeviceBackups.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $ */

/*
 *  DeviceBackups.java
 */
package com.adventnet.nms.policies;

import java.util.Properties;
import java.util.Vector;
import java.util.Enumeration;
import java.util.Date;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import com.adventnet.management.policydb.PolicyEvent;
import com.adventnet.management.policydb.PeriodicPolicyObject;
import com.adventnet.nms.util.NmsLogMgr;
import com.adventnet.management.log.Log;
import com.adventnet.nms.util.NmsUtil ;
import com.adventnet.nms.topodb.TopoAPI ;
import com.adventnet.nms.topodb.SnmpNode ;
import com.adventnet.nms.topodb.Node ;
import com.adventnet.snmp.snmp2.SnmpOID;
import com.adventnet.snmp.beans.SnmpTarget;
import com.adventnet.nms.util.PureUtils;

/**
 * This policy backs up SNMP devices and creates backup
 * data for the device.  This data can later be used
 * to restore a device, whose configuration has been lost.
 * 
 * The backup is for a MIB sub-tree on the device.  For
 * example interfaces group on a device.
 *
 * Variations of this policy can backup more specific MIB data
 * for specific devices, i.e. more knowledge of the domain
 * can be coded in the policy.  So a backup of routers may
 * include backup of interfaces table, routing table, etc.
 */

public class DeviceBackups extends PeriodicPolicyObject
{

    static final long serialVersionUID = -2765663103228179775L;
    // The MIBs to be loaded, so can use names, and saved data would show names
    //    String mibs = "none";  
    //    String subtree = "Enter MIB subtree to backup"; // The MIB sub-tree to backup

    //   String type = "Enter prefix of device";  // the device type to backup

    //    String directory = "DEVICE_BACKUPS";  // the directory to backup
    //    int maxdata = 1000; // max number of variables to get


    /** Set the period to one day in the constructor. **/
    public DeviceBackups() 
    { 
	super();
	period = 3600*24;
        setUserProperty("MIBs to be Loaded" , "none");
        setUserProperty("MIB Sub-Tree toBackup" , "Enter MIB subtree to backup");
        setUserProperty("Device Type" , "Enter prefix of device");
        setUserProperty("Backups Directory" , "DEVICE_BACKUPS" );
        setUserProperty("Maximum Data (variables)" , "1000");
    }

    /**
     * Sets the properties for this policy object
     * @param p the property to be set.
     */

    public void setProperties(Properties p)
    {

	String temp;
	temp = (String)p.remove("MIBs to be Loaded");
	if(temp != null)
	{
            setUserProperty("MIBs to be Loaded" , temp.trim());
	}
	
	temp = (String)p.remove("MIB Sub-Tree toBackup");
	if(temp != null)
	{
            setUserProperty("MIB Sub-Tree toBackup" , temp.trim());
	}

	temp = (String)p.remove("Device Type");
	if(temp != null)
	{
            setUserProperty("Device Type" , temp.trim());
	}
	
	temp = (String)p.remove("Backups Directory");
	if(temp != null)
	{
            setUserProperty("Backups Directory" , temp.trim() );
	}
	
	temp = (String)p.remove("Maximum Data (variables)");
        if(temp != null)
	{
            setUserProperty("Maximum Data (variables)" , temp.trim());
        }
	super.setBaseProperties(p);
    }
    /**
     * Returns the properties of this policy object
     * @return "DeviceBackups properties"
     */

    public Properties getProperties()
    {
	Properties p = super.getBaseProperties();
	String temp;
	temp = (String)getUserProperty("MIBs to be Loaded");
	if(temp != null)
	{
                p.put("MIBs to be Loaded" , temp);
	}
	
	temp = (String)getUserProperty("MIB Sub-Tree toBackup");
	if(temp != null)
	{
                p.put("MIB Sub-Tree toBackup" , temp);
	}
	
	temp = (String)getUserProperty("Device Type");
	if(temp != null)
	{
                p.put("Device Type" , temp);
	}
	
	temp = (String)getUserProperty("Backups Directory");
	if(temp != null)
	{
                p.put("Backups Directory" , temp );
	}
	
	temp = (String)getUserProperty("Maximum Data (variables)");
	if(temp != null)
	{
                p.put("Maximum Data (variables)" , temp);
        }

	return p;
    }
    
    /**
     * Returns the help url of this policy object. As, the default customizer associated
     * with this policy uses the help url directly from the help.conf, this method
     * returns null.
     */
    public String getHelpURL()
    {
        return null;
    }

    /** 
     * Returns the properties of this policy object 
     * @return "Policy customizer name" 
     */ 
    public String getPolicyObjectCustomizer() 
    {
	return policyObjectCustomizer;
    }


    /**
     * executes action of this policy object.
     * @param policyEvt PolicyEvent
     */

    public void executeAction(PolicyEvent policyEvt)
    {
	String mibs  = (String)getUserProperty("MIBs to be Loaded");
	String subtree = (String)getUserProperty("MIB Sub-Tree toBackup");
	String type = (String)getUserProperty("Device Type");
	String directory  = (String)getUserProperty("Backups Directory");
	int maxdata  = Integer.parseInt((String)getUserProperty("Maximum Data (variables)"));

	SnmpTarget target = new SnmpTarget();
	if ( (mibs != null) && !mibs.equals("") && !mibs.equals("none") ) 
        {
            try 
            {
                target.loadMibs(mibs);
            } catch (Exception ex) 
            { 
                System.err.println(NmsUtil.GetString("DeviceBackups error loading mibs:") +
                                   mibs+" : "+ex); 
            }
        }

	Date now = new Date();
	SimpleDateFormat formatter
	    = new SimpleDateFormat ("hhmmss_MM-dd-yyyy");
	String timestamp = formatter.format(now);	

	try {  // We'll check each node in the database

	    File dir = new File(PureUtils.rootDir+directory);
	    if (!dir.exists()) dir.mkdirs();

	    TopoAPI api = (TopoAPI)NmsUtil.getAPI("TopoAPI");
	    if (api == null)
            { 
                NmsLogMgr.POLICYERR.log(NmsUtil.GetString("TopoAPI in null in Device Backup Policy. Can not execute Policy "),Log.SUMMARY);
                return;
            }

	    Vector nodes = api.getNodes();

	    for (Enumeration en = nodes.elements();en.hasMoreElements();) {
		Node node = api.getNode((String) en.nextElement());

		// first check if type of node is correct
		if (!node.getIsSNMP()) continue;

		SnmpNode snode = (SnmpNode) node;

		if (!node.getType().startsWith(type)) continue;

		// set the agent hostname
		target.setTargetHost( snode.getName() );  
		target.setCommunity( snode.getCommunity() );
		target.setObjectID(subtree);

		FileOutputStream fileout = 
		    new FileOutputStream(directory+"/"+snode.getName()+
					 "_"+timestamp);

		PrintWriter out = new PrintWriter(fileout);

		out.println("Backup for node: "+node.getIpAddress());
		//  collect the data
		SnmpOID rootoid = target.getSnmpOIDList()[0];
		int maxtry = 0;
		while ( maxtry++ < maxdata) {  // limit getnexts to maxdata

		    String result = target.snmpGetNext();
		    if (result == null) { // problem with data received ?
			out.println("Error in data from agent: "+ 
				    target.getErrorString());
			break;
		    }

		    if ( !SnmpTarget.isInSubTree(rootoid,target.getSnmpOIDList()[0]) )
			break;  // check first column

		    out.println(target.getObjectID() + ": "+result);
		}

		if (maxtry == 1) out.println("No data in sub-tree: "+ subtree);
		if (maxtry >= maxdata) out.println("Max data exceeded: "+
						   maxdata);
		out.flush();
		fileout.close();
	    }

	} catch (Exception ex) {
	    NmsLogMgr.POLICYERR.fail(NmsUtil.GetString("Error executing DeviceBackups Policy"), ex); 
	}
    }
}
