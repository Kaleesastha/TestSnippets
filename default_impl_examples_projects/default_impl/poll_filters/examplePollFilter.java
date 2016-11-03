/*
$Id: examplePollFilter.java,v 1.2 2008/09/02 09:44:19 tinku Exp $
*/

/** This class is an example which implements the PollFilter
 * interface. This example just adds/deletes some oid's pertaining to nodes.
 * This filter will remove a poll configured through polling.conf
 * This filter will also add a couple of polls namely IpInDiscards
 * and IpOutDiscards . In these two polls threshold events will also be
 * configured .
 * This filter will affect these changes only in case of Windows95 machines
 */

package test;
import com.adventnet.nms.topodb.*;
import com.adventnet.nms.poll.*;
import java.util.*;
import com.adventnet.nms.util.*;


public class examplePollFilter implements PollFilter {

    String oid="",agent="" ;
    String deloid="1.2.0";
    String win95oid = ".1.3.6.1.4.1.311.1.1.3.2";
  /**
   * This is the method that does the changes .The arguments passed to it
   * are the ManagedObject and Vector of polldatas for that ManagedObject
   * Remember the ManagedObjects passed are not necessarily TopoObjects
   * and if TopoObject not necessarily SNMP.
   * This is to facilitate the user performing other tests/polls on the ManagedObjects
   * or starting a poll for an Object for which SNMP agent may be started in
   * the future .If the User wants he can write his own classes for spawning
   * a different kind of polling.
   * He can do this from the Discovery filters too .
   * So it is advisable to test forTopoObject and  SNMP
   * The method returns a vector of polldata for which polling should be
   * started after making his own changes.Remember this method gets called
   * just before the polls are about to be initiated and hence from here
   * he can filter what has been specified in the Polling.conf file.
   */

    public Vector applyPollFilter(ManagedObject obj ,Vector pdatasVect) {
      Vector pvector = pdatasVect;
      try {
	if ( !(obj instanceof TopoObject)) return pvector;   
        if (((TopoObject)obj).getIsSNMP()) {
		if (obj instanceof SnmpNode) {
			String sysOID=((SnmpNode)obj).getSysOID();
			if(sysOID==null)
			{
				return pvector;
			}
			if (sysOID.equals(win95oid)) {
				for ( Enumeration e=pvector.elements();e.hasMoreElements();) {
					PolledData pd = (PolledData) e.nextElement();
					if ((pd.getOid()).equals(deloid)) {
		  pvector.removeElement(pd);
                }
	      }
	      PolledData pd1=new PolledData();
	      pd1.setName("IpInDiscards");
	      pd1.setCommunity(((TopoObject)obj).getCommunity());
	      // An important point to note here when setting the oid is to 
	      // take care of specifying it properly
	      // if the OID does not start with a dot then it is assumed to 
	      // belong to MIB-II and the standard Mib-II prefix .1.3.6.1.2.1.
	      // is prepended to it .
	      // if it belongs to some other mib then it is better you start
	      // the oid with a dot and specify it completely say like
	      // .1.3.6.1.2.1.43.8.2.1.8.1.1 
	      pd1.setOid("4.11.0");
	      pd1.setSave(true);
          pd1.setAgent(obj.getName());

          // For collecting the values from the agent there are 2 important
          // variables in the PolledData
          // a)  agent  
          // b)  dnsName

          // For a NON DHCP node the agent and dns name are same.
          // For a      DHCP node the agent will be the Mac address and the
          // dns name would be the currently assigned IP address

          // If the obj is a non DHCP node  the user need not do anyting 
          // special for PolledData.
          // It would suffice if setAgent method is invoked. 

		  // if the obj is a DHCP node the obj.getName will return the Mac
          //  addres
          // Then the agent of the pd1  will be set to mac address because of
          //  the pd1.setAgent(obj.getName()) call.
          // But the PolledData uses the dnsName for polling. 
		  // So  setDnsName will have to be invoked  separately with 
		  // obj.getIpAddress as parameter i.e
          // pd1.setDnsName(obj.getIpAddress()); 

          // Please note that when setAgent is invoked intenrally it calls
          // setDnsName also. 
          // So for a Non DHCP node the setAgent call would suffice as it
          // would set the dnsName.
          // For DHCP nodes the setAgent should  be followed by the setDnsName

	      // set the polling period to 600 seconds
	      pd1.setPeriod(600);
	      pvector.addElement(pd1);
	      //Now we will have to add for IpOutDiscards . Since we want most of
	      // the properties same for that too .instead of coding all that we
	      // will use a shortcut
	      PolledData pd2= (PolledData) pd1.clone();
	      pd2.setName("IpOutDiscards");
	      pd2.setOid("4.8.0");
	      pvector.addElement(pd2);
	    }  // if obj.sysOID is winOID
	  }  // if obj instance of SnmpNode
        } // if obj.isSNMP
      } catch(Exception e ){
	System.err.println("Exception in examplePollFilter: "+ e);
	return pvector;
      }
      return pvector;
    }

// Important if it is required that you want to start a poll for
// multiple instances of a variable , just set the property
// isMultiplePolledData to true of the polledData object . The rest
// remain the same.


// This part is for finding the index of a particular interface
// If the object is not an interface or the interface index
// cannot be found it returns a string "exception";
// The idea of returning string is to make it easy to add to the oid
// of polleddata. just add a "." and the returned value . But take care to check
// if the returned value is an exception or not




  String getIndex(TopoObject obj)  {

    String index = "exception" ;
    if (obj instanceof SnmpInterface) {
      try {
        SnmpInterface interf = (SnmpInterface) obj;
        int ind=0;
        if ((ind=interf.getIfIndex())!=0) return String.valueOf(ind);
      } catch (Exception e) {}
    }
    return index;
  }




}



