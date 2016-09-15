/**
 * IpOutDiscardsTest.java
*/
package test;

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

public class TestUserTester implements UserTester {

  /**
   * Hashtable to store previous counter value of the ifOutDiscards
  */
  static java.util.Hashtable myCounters = new java.util.Hashtable();

  /**
   * Method from UserTester interface.
   * The ManagedObject is passed along with the TopoAPI
   * and status is returned from this method.
  */
  public int test(ManagedObject manobj, TopoAPI api) {
	  SeverityInfo sevInfo = SeverityInfo.getInstance();	
	  SeverityIterator iterator = sevInfo.getIterator();
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

      Thread.currentThread().wait(20000);
      

    } catch (Exception e) {
      System.err.println("AStatus Test Error: "+e);
      e.printStackTrace();
      return sevInfo.getSpecialPurposeSeverity(); //ignored
      //return -1; //ignored
    }
  }

}
