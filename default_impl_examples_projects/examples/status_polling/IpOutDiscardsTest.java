/* $Id: IpOutDiscardsTest.java,v 1.4 2008/07/30 11:30:11 barathv Exp $ */
/**
 * IpOutDiscardsTest.java
*/
package test;

import java.util.Properties;

import com.adventnet.nms.util.*;
import com.adventnet.nms.severity.*;
import com.adventnet.nms.netwatch.UserTester;
import com.adventnet.nms.topodb.*;
import com.adventnet.snmp.beans.*;
import com.adventnet.snmp.snmp2.*;

/**
 * This is an example of doing custom checks for status of a network element.
 * This is useful if ping or snmpping is not enough to test the status of the 
 * network element, and you need to ensure something else is working. The tester 
 * can be configured to be invoked for a ManagedObject from the OIDType.data file,
 * or by modifying the usertest and uClass fields of a ManagedObject from user written
 * discovery filter or any other user written class.
 * This example checks for IP output discards (ipOutDiscards 4.11.0)on a router, and 
 * then returns the severity based on whether any output IP datagrams are discarded.
**/

public class IpOutDiscardsTest implements UserTester {

  /**
   * Hashtable to store previous counter value of the ifOutDiscards
  */
  static java.util.Hashtable myCounters = new java.util.Hashtable();

  /**
   * Method from UserTester interface.
   * The ManagedObject is passed along with the TopoAPI
   * and status is returned from this method.
  */
  public int test(String moname, Properties prop, TopoAPI api) {
	  SeverityInfo sevInfo = SeverityInfo.getInstance();	
	  SeverityIterator iterator = sevInfo.getIterator();
          ManagedObject manobj = null;
         try
         {
              manobj = api.checkOut(moname,0,false,true);
         }
         catch( Exception ex) {
            NmsLogMgr.TOPOERR.fail("Exception in getting the object  " + manobj.getName(),ex); //no i18n
        }
      TopoObject obj;
      if(!(manobj instanceof TopoObject))
          return sevInfo.getSpecialPurposeSeverity(); //ignored
          //return -1; //ignored
      else
          obj = (TopoObject)manobj;
      
    try {

      if (obj == null) 
          return sevInfo.getSpecialPurposeSeverity(); //ignored
	  	//return -1; //ignored

      if (!obj.getIsSNMP()) { // Problem with SNMP?
		iterator.moveToHighest();
		return iterator.getPreviousCriticality(3);
		//return TopoAPI.WARNING;  // warning
      }

      if (!obj.getIsRouter()) { // This Status Test is for routers
		iterator.moveToHighest();
		return iterator.getPreviousCriticality(3);
	    //return TopoAPI.WARNING;  // warning
      }

      //Get the value of 4.11.0 from the agent.
      SnmpTarget target = new SnmpTarget();
      target.setTargetHost(obj.getIpAddress());
      target.setCommunity(obj.getCommunity());
      target.setSnmpOID(new SnmpOID("4.11.0"));
      target.setTargetPort(obj.getSnmpport());
      SnmpVar var = target.snmpGetVariable();
	  cleanUp(target);

      if (var == null) {// SNMP get failed - timeout
		iterator.moveToHighest();
		return iterator.getPreviousCriticality(3);
	    //return TopoAPI.WARNING;  // warning
      }


      	// for counters we need to get deltas - so need previous value
      Long lastValue = (Long) myCounters.get(obj.getName()+"4.11.0");

	// Save this one for later
      myCounters.put(obj.getName()+"4.11.0", var.toValue());

      if (lastValue == null) 
        return sevInfo.getSpecialPurposeSeverity(); //ignored
	  	//return -1; // ignored
     
      int drops = ((Long)var.toValue()).intValue() - lastValue.intValue();
    
      if (drops > 0) 
	  {
		iterator.moveToHighest();
		return iterator.getPreviousCriticality(2);
		//return TopoAPI.MINOR; // minor
	  }	
      return sevInfo.getClear(); // all clear
      //return TopoAPI.CLEAR; // all clear

    } catch (Exception e) {
      System.err.println("AStatus Test Error: "+e);
      e.printStackTrace();
      return sevInfo.getSpecialPurposeSeverity(); //ignored
      //return -1; //ignored
    }
  }
	public void cleanUp(SnmpTarget target)
	{
		target.releaseResources();	
	}

}
