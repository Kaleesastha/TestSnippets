package test;

import java.io.*;
import java.util.*;
import com.adventnet.nms.policies.*;
import com.adventnet.management.policydb.*;
import com.adventnet.nms.util.*;
import java.util.*; 
import java.rmi.*;


import test.PolicyObserverExample;
import test.PolicyActionImpl;
import test.PolicyConditionImpl;
import com.adventnet.nms.policies.ExampleStopPolicy;


//TESTCASE==NOS -10 11 12 15 22 23 24 25 
public class SFPLCYNORM 
{
            
    public static void main(String args[])
    {
        String host = "localhost";
        
        if(args.length == 1)
            {
                host=args[0];
            }
        try{
            
            NmsPolicyAPI napi = (NmsPolicyAPI)Naming.lookup("rmi://"+host+":1099/NmsPolicyAPI");             
                          
                        
            
            //register policy observer
            PolicyObserver obs = new PolicyObserverExample();
            System.out.println("SF-PLCY-NORM-010==PASSED if RESULT=true  "+napi.register(obs));
            System.out.println("SF-PLCY-NORM-011==PASSED if RESULT=true "+napi.removeObserver(obs));
              
            napi.register(obs);
            napi.register(obs);
            napi.removeAllObservers();
            System.out.println("SF-PLCY-NORM-012:PASSED ===if result is false!!! "+napi.removeObserver(obs));
            
              
            
            //stop policy :
            /* here we use a examplestoppolicy so as to run this compile the policy Example policy in to WebNMS/classes and add a entry in relationalclasses.conf as "test.policy.policies.ExampleStopPolicy      policydb        RelationalPolicyObject  PolicyObject" and then start the server */
           
            PolicyObject obj15 = new ExampleStopPolicy();
            obj15.setName("examplestoppolicy");
            obj15.setGroupName("group1");
            napi.addPolicy(obj15);
            PolicyEvent pe1 = new PolicyEvent(napi);
            pe1.addPolicyNamesToTrigger("examplestoppolicy");
            System.out.println("before executing Policy ");
            napi.executePolicy(pe1);
            System.out.println("SF-PLCY-NORM-015==PASSED if RESULT=true "+napi.stopPolicy("examplestoppolicy"));
	        
            
            PolicyObject obj23 = new WebNMSBackUpPolicy();
            obj23.setName("testpolicy-23");
            napi.addPolicy(obj23);
              
            PolicyAction pa=new PolicyActionImpl();
            PolicyCondition pc=new PolicyConditionImpl();
            
            System.out.println("SF=PLCY-NORM-23==PASSED if RESULT=true  "+napi.addPolicyActionAndCondition("testpolicy-23",pa,pc));
            PolicyObject po = napi.getPolicy("testpolicy-23");  
            Vector a = po.getActionKeys();
            Vector c = po.getConditionKeys();  
            System.out.println("SF=PLCY-NORM-22==check the values"+a+c);
            
        
        
        
        }
        catch(Exception e)
            {
                e.printStackTrace();
            }
    }
}


