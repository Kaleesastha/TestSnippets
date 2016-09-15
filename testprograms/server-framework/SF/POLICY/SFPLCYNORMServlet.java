package test;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.adventnet.nms.policies.*;
import com.adventnet.management.policydb.*;
import com.adventnet.nms.util.*;
import java.util.*; 
import java.rmi.*;


//import test.policy.policies.ExamplePolicy;

public class SFPLCYNORMServlet extends HttpServlet
{
    PrintWriter out=null;
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
    {
        doGet(req,res);
        
    }
    
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
    {
		
        out=res.getWriter();
        
        
        String host = "localhost";
        
		try{
            NmsPolicyAPI napi = (NmsPolicyAPI)NmsUtil.getAPI("NmsPolicyAPI");             
            
            out.println("initial"+napi.deletePolicy("testpolicy3"));
            out.println("intial"+napi.deletePolicy("testpolicy4"));
            
            PolicyObject obj1 = new WebNMSBackUpPolicy();
            obj1.setName("testpolicy3");
            obj1.setGroupName("TesterPolicyGroup");
 
            // for adding a policy
            out.println("SF-PLCY-NORM-003==PASSED if RESULT=true-->  "+napi.addPolicy(obj1));
            out.println("SF-PLCY-NORM-001==PASSED if RESULT=complete list of polices  "+napi.getCompleteList());
            out.println("SF-PLCY-NORM-002==PASSED if RESULT= properties of WebNMSBackUpPolicy    "+napi.getPropertiesOfObject("testpolicy3"));
            
            
            //for delete policy
            out.println("SF-PLCY-NORM-004==PASSED if RESULT = true "+napi.deletePolicy("testpolicy3"));
            out.println("SF-PLCY-NORM-004"+napi.getCompleteList());
            
            napi.addPolicy(obj1);
            PolicyEvent pe = new PolicyEvent(napi);
            pe.addPolicyNamesToTrigger("testpolicy3");
            out.println(" before executing Policy ");
            Thread.sleep(2000);
            // For policy execution
            napi.executePolicy(pe);
            out.println(" SF-PLCY-NORM-005:check the backup whether the backup has taken");
            
            
            // get policy
            obj1 = (PolicyObject)napi.getPolicy("testpolicy3",true);
            out.println("SF-PLCY-NORM-006==PASSED if RESULT=valid object  "+obj1);  
            
            obj1 = (PolicyObject)napi.getPolicy("testpolicy3");
            out.println("SF-PLCY-NORM-007==PASSED if RESULT=valid object   "+obj1);
            
            out.println("SF-PLCY-NORM-008==PASSED if RESULT=List of Policies"+napi.getPolicyNames());
            
            out.println("SF-PLCY-NORM-009==PASSED if RESULT=true "+napi.isInitialized());
            
            
            //setPolicyStatus
            obj1 = (PolicyObject)napi.getPolicy("testpolicy3",true);
            napi.setPolicyStatus("testpolicy3",NmsPolicyAPI.POLICY_DISABLED);
            if((napi.getPolicy("testpolicy3" )).getStatus() == NmsPolicyAPI.POLICY_DISABLED)
            {
                out.println(" SF-PLCY-NORM-013: Passed");
            }
            else{
                out.println(" SF-PLCY-NORM-013: Failed");
            } 
            
            //update policy
            Properties prop = new Properties();
            prop.put("tester","tester1");
            prop.put("type","winNt");
            prop.put("parentNet","192.168.3.0");
            prop.put("community","public");
            napi.updatePolicy("testpolicy3",prop);
            Thread.sleep(5000);
            out.println("SF-PLCY-NORM-014==PASSED if RESULT = properties of WebNMSBackUpPolicy     "+napi.getPropertiesOfObject("testpolicy3"));
                        
            
            // getObjectnameWithProps
            PolicyObject obj2 = new WebNMSBackUpPolicy();
            obj2.setName("testpolicy4");
            obj2.setGroupName("TesterPolicyGroup");
            napi.addPolicy(obj2);
            
            Properties p1 = new Properties();
            p1.put("name","test");
            out.println("SF-PLCY-NORM-016==PASSED if RESULT=list of policies whose names starts with 'test'    "+napi.getObjectNamesWithProps(p1));
            
            Properties p2 = new Properties();
            p2.put("groupName","TesterPolicyGroup");
            out.println("SF-PLCY-NORM-017==PASSED if RESULT=list of policies belong to TesterPolicyGroup    "+napi.getObjectNamesWithProps(p2));
            
            Properties p3 = new Properties(); 
            p3.put("status","" +NmsPolicyAPI.POLICY_ENABLED);
            out.println("SF-PLCY-NORM-018==PASSED if RESULT=list of enabled policies    "+napi.getObjectNamesWithProps(p3));
            
            Properties p4 = new Properties(); 
            p4.put("status","" +NmsPolicyAPI.POLICY_DISABLED);
            out.println("SF-PLCY-NORM-019==PASSED if RESULT=list of disabled polices   "+napi.getObjectNamesWithProps(p4));
            
            Properties p5 = new Properties(); 
            p5.put("name","test");
            p5.put("status","" +NmsPolicyAPI.POLICY_DISABLED);
            out.println("SF-PLCY-NORM-020==PASSED if RESULT=DISABLED&&name starts with 'test'    "+napi.getObjectNamesWithProps(p5));
            
            Properties p6 = new Properties(); 
            p6.put("groupName","TesterPolicyGroup");
            p6.put("status","" +NmsPolicyAPI.POLICY_ENABLED);
            out.println("SF-PLCY-NORM-021==PASSED if RESULT=ENABLED&&group=TesterPolicyGroup   "+napi.getObjectNamesWithProps(p6));
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}



