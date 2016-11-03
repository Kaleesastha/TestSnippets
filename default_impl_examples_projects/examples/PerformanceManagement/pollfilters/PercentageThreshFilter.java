/*
 * $Id: PercentageThreshFilter.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
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
import com.adventnet.nms.topodb.*;
import com.adventnet.nms.poll.*;
import com.adventnet.nms.poll.ThresholdObject;
import java.util.*;
import com.adventnet.nms.util.*;

/**
 * This is an example for checking the free memory available in the 
 * localhost. If the free memory level falls below 40%, a threshold event
 * of minor severity is generated to indicate the same. Similarly,if it
 * falls below 30% a threshold event of major severity will be generated.
 * If it is below 20% a threshold event of critical severity is generated.
 * This is achieved by the powerful feature of the PollFilter.
 * All the ManagedObjects and vector of PolledData created for the
 * corresponding ManagedObjects are passed through PollFilter before
 * they are stored in database. Hence, addition of PolledData or
 * modification of existing PolledData created for the
 * corresponding ManagedObjects can be done by the use of PollFilter. 
 * In this example,a PolledData is added to the localhost to check for
 * the Free Memory available for the localhost. A ThresholdObject of kind
 * percentage is associated with this PolledData and the ThresholdEvent
 * will be generated when the free Memory availability matches any one of
 * the above condition with corresponding severity.
 */
public class PercentageThreshFilter implements PollFilter
{

     public static PollAPI api = null; 
     static boolean flag = true;    

    // All the discovered ManagedObjects and vector of PolledData created
    // for the corresponding ManagedObjects are passed through this method.
     
    public Vector applyPollFilter(ManagedObject obj,Vector pdatasVect)
    {
	Vector pvector = pdatasVect;
 		
	try
	{
	    // If the object is not an instance of TopoObject the vector
	    // is returned.

	    if(!(obj instanceof TopoObject)) return pvector;
	    if(flag) 
		{
		    try
		    {
			// PollAPI's handle is obtained using NmsUtils'
			// getAPI() method.

			api = (PollAPI)NmsUtil.getAPI("PollAPI");
			
		    }
		    catch(Exception e)
		    {
			System.out.println("Error: "+e);
			e.printStackTrace();
		    }

		    // A ThresholdObject of kind 'percentage' and type 'min' is
                    // created .
		    // ThresholdValue is set to 40 so that if the percentage
		    // falls below 40 a ThresholdEvent of minor severity will
		    // be generated. The oid set in threshold object is
                    // .1.3.6.1.4.1.2162.4.1.4.0 which returns total memory 
		    // available. 

		    ThresholdObject th1 = new ThresholdObject("percentThreshold1"); 
                  
                    th1.setKind("percentage");   
                    th1.setSeverity(3);
                    th1.setThresholdType("min");   
                    th1.setThresholdValue(40L);  
                    th1.setRearmValue(40L);  
                    th1.setCategory("Threshold");  
		    th1.setOidValue(".1.3.6.1.4.1.2162.4.1.4.0");
                    th1.setOidType("node");
                    th1.setSendClearStatus(true);                    
                    boolean b1 = api.addThresholdObject(th1); 


                    // Similarly threshold object for generating major severity
                    // event is added . In this case threshold and rearm value
                    // is set to 30.

                    ThresholdObject th2 = new ThresholdObject("percentThreshold2");
                  
                    th2.setKind("percentage");
                    th2.setSeverity(2);
                    th2.setThresholdType("min");
                    th2.setThresholdValue(30L);
                    th2.setRearmValue(30L);
                    th2.setCategory("Threshold");
		    th2.setOidValue(".1.3.6.1.4.1.2162.4.1.4.0");
                    th2.setOidType("node");
                    th2.setSendClearStatus(true);                                        
                    boolean b2 = api.addThresholdObject(th2);


                    //The third and last threshold has critical severity and
                    //this event will be generated if percentage of free memory
                    // goes below 20.


		    ThresholdObject th3 = new ThresholdObject("percentThreshold3");
                  
                    th3.setKind("percentage");
                    th3.setSeverity(1);
                    th3.setThresholdType("min");
                    th3.setThresholdValue(20L);
                    th3.setRearmValue(20L);
                    th3.setCategory("Threshold");
		    th3.setOidValue(".1.3.6.1.4.1.2162.4.1.4.0");
                    th3.setOidType("node");
                    th3.setSendClearStatus(true);                    
                    boolean b3 = api.addThresholdObject(th3);


		    String localhost = (String)java.net.InetAddress.getLocalHost().getHostName();


		    // New PolledData object is created by instantiating
		    // PolledData class. This PolledData is set with the Oid 
		    // that corresponds to the free memeory availability.
		    // Then this PolledData is associated with these
		    // 3 ThresholdObjects. Necessary properties of the
		    // PolledData have been set to this PolledData before
		    // it is added to the vector. Finally the vector is
		    // returned.

		    PolledData pd = new PolledData();
		    pd.setName("webNMSFreeMemory");
		    pd.setOid(".1.3.6.1.4.1.2162.4.1.5.0");
		    pd.setAgent(localhost+"_FreeMemory");
                    pd.setDnsName(localhost);
		    pd.setThreshold(true);
		    Vector threshvector = new Vector();
		    threshvector.addElement("percentThreshold1");
		    threshvector.addElement("percentThreshold2");
		    threshvector.addElement("percentThreshold3");
		    pd.setThresholdNames(threshvector);
		    pd.setSaveAbsolutes(true);
		    pd.setPeriod(600);
		    pd.setPort(8001);
		    pvector.addElement(pd);
		    System.out.println("pd is added");
		    flag = false;

                    //Here the order of threshold event generation will be as
                    //follows.
                    // free memory < 40% --> minor event
                    // free memory < 30% --> major event
                    //free memory < 20%  --> critical event

                    
	        }
		
	    // Vector of PolledData is returned after carrying out all 
	    // modification or addition of PolledData.
	    return pvector;
	}
	catch(Exception e)
	{
	    System.out.println("Exception: "+e);
	    e.printStackTrace();
	    return pvector;
	}
    }
}
											
	
















