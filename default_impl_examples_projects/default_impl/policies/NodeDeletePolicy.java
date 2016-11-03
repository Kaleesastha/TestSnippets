
/* $Id: NodeDeletePolicy.java,v 1.2 2008/07/09 01:57:57 johnpaul Exp $ */

/**
 *  NodeDeletePolicy.java
 **/

package com.adventnet.nms.policies;

import java.util.Properties; 
import java.util.Vector;
import java.util.Enumeration;
import java.util.Date;
import com.adventnet.management.policydb.PolicyEvent; 
import com.adventnet.management.policydb.PeriodicPolicyObject; 
import com.adventnet.nms.util.NmsLogMgr; 
import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.topodb.TopoAPI; 
import com.adventnet.nms.topodb.Node; 
import com.adventnet.nms.severity.SeverityInfo;
import com.adventnet.nms.severity.SeverityIterator;
import com.adventnet.management.log.Log;

/** This policy is to delete failed nodes from the topology database
after they have been down for a while.  This allows cleaning up 
outdated information.  Admin controls when the failed node is 
deleted from database **/

public class NodeDeletePolicy extends PeriodicPolicyObject 
{ 
	 static final long serialVersionUID = -4460550312290818634L;
    /** Controls when the failed node is deleted from database **/

    public NodeDeletePolicy()
    {
        setUserProperty("Delete After (days)" , "7");//No Internationalisation
    }

    /** 
     * Sets the properties for this policy object 
     * @param p the property to be set. 
     */ 
    public void setProperties(Properties p) 
    { 
        String temp = (String)p.remove("Delete After (days)");//No Internationalisation
        if(temp != null)
        {
            setUserProperty("Delete After (days)" , temp.trim());//No Internationalisation
        }
        super.setBaseProperties(p); 	
    }  

    /** 
     * Returns the properties of this policy object 
     * @return "Policy properties" //No Internationalisation
     */ 

    public Properties getProperties() 
    { 
        Properties p = super.getBaseProperties(); 
        String temp = getUserProperty("Delete After (days)");//No Internationalisation
        if(temp != null)
        {
            p.put("Delete After (days)",temp); //No Internationalisation
        }
        return p;
    }


    /** 
     * Returns the customizer of this policy object.  Since there is no
	 * customizer this method returns null
     * @return "Policy customizer name" //No Internationalisation
     */ 
    public String getPolicyObjectCustomizer() 
    {
	return policyObjectCustomizer;
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

    /** This does the work **/
    public void executeAction(PolicyEvent policyEvt) 
    {
       int deleteAfter = Integer.parseInt(getUserProperty("Delete After (days)"));//No Internationalisation

	try {  // We'll check each node in the database

	    TopoAPI api = (TopoAPI)NmsUtil.getAPI("TopoAPI");//No Internationalisation
	    if (api == null)
		{
			return;
		}

	    Vector nodes = api.getNodes();
	    for (Enumeration en = nodes.elements();en.hasMoreElements();) 
            {
                String nodeName = (String) en.nextElement();
                Node node = api.getNode(nodeName);
                if(node == null)
                {
                    NmsLogMgr.POLICYERR.log(NmsUtil.GetString("Node with the name ")+ nodeName + NmsUtil.GetString(" is null.Can not delete it using  NodeDelete Policy ") , Log.SUMMARY);
                    continue;
                }
		// first check if status is major or critical
		int status = node.getStatus();
		SeverityIterator iterator = SeverityInfo.getInstance().getIterator();
		iterator.moveToHighest();
		int sev1 = iterator.getCurrent();
		int sev2 = iterator.getPreviousCriticality();
		if ((status != sev1) && (status != sev2))
		    continue;
		// next check if failed for more than defined time
		if ( node.getStatusChangeTime() >
		     ((new Date()).getTime() - (24*60*60*1000* deleteAfter)) )
		    continue;
		
		// now delete node if it has only one interface
		if ( node.getIpaddrs().size() <= 1) 
		{
			System.out.println(NmsUtil.GetString("Node deleted with the name ")+ node.getName());
			api.deleteObject(node, true,false);
		}

		// should check each interface for multiple interfaces
		if ( node.getIpaddrs().size() > 1) 
		{
			System.out.println(NmsUtil.GetString("As no of IP's is more than 1 node not  deleted with the name ")+ node.getName());
				

		}
	    }

	} catch (Exception ex) {
	    NmsLogMgr.POLICYERR.fail(NmsUtil.GetString("Error executing NodeDeletePolicy"), 
				     ex); 
	}

    }
}


