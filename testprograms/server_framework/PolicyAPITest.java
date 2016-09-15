
import com.adventnet.nms.policies.*;
import com.adventnet.management.policydb.*;
import com.adventnet.nms.util.*;
import java.util.*;
public class PolicyAPITest
{
	public PolicyAPITest()
	{
		try
		{

//		NmsPolicyAPI api = NmsUtil.getPolicyAPI();

		NmsPolicyAPI api = (NmsPolicyAPI)java.rmi.Naming.lookup("//subramani/NmsPolicyAPI");	
		NmsPolicyAPI api = (NmsPolicyAPI)java.rmi.Naming.lookup("//subramani/NmsPolicyAPI");	
	
/*		
		api.addAction("mng",action);
		api.addCondition("ksf",condition);
		api.setPolicyStatus("jhf",NmsPolicyAPI.POLICY_DISABLED);
		{
			Properties prop = new Properties();
			prop.put("jfsf","skhasf");
			api.updatePolicy("sfsaf",prop);
			api.updatePolicy("StatusTestPolicy5",prop);
		}
		
*/
		PolicyObject obj1 = new TesterPolicy();
		obj1.setName("testpolicy3");
		obj1.setGroupName("TesterPolicyGroup");
		
		// for adding a policy
		api.addPolicy(obj1);
/*		
		PolicyCondition condition = new PolicyConditionImpl();
		PolicyAction action = new PolicyActionImpl();

		// for adding condition
		api.addCondition("testpolicy3",condition);
		
		// for adding action
		api.addAction("testpolicy3",action);
		
		// for getpolicy
		obj1 = (PolicyObject)api.getPolicy("testpolicy3");
		
		// Cross check on policy condition and policy action
//		System.out.println(" condition keys :"+obj1.getConditionKeys());
//		System.out.println(" action keys :"+obj1.getActionKeys());

*/		
//		obj1 = (PolicyObject)api.getPolicy("testpolicy3");

		PolicyEvent pe = new PolicyEvent(this);
		pe.addPolicyNamesToTrigger("testpolicy3");
		System.out.println(" before executing Policy ");
		Thread.sleep(2000);
		// For policy execution
		
		api.executePolicy(pe);
		System.out.println(" Policy executed successfully ");

		// to check Policy names
		System.out.println("Policy Names are "+api.getPolicyNames());

		System.out.println(" current Status of policy testpolicy3 "+obj1.getStatus());
		
		api.setPolicyStatus("testpolicy3",NmsPolicyAPI.POLICY_DISABLED);

		System.out.println(" now Status of policy testpolicy3 is "+obj1.getStatus());

		System.out.println(" Is Policy Mgr initialized "+api.isInitialized());

		

		System.out.println(" Properties of policy object testpolicy3"+obj1.getProperties());
		
		Properties prop = new Properties();
		prop.put("tester","tester1");
		prop.put("type","winNt");
		prop.put("parentNet","192.168.3.0");
		prop.put("community","public");
		
		System.out.println(" new properties of Policy object testpolicy3"+prop);
		
		System.out.println(" Result of update policy "+api.updatePolicy("testpolicy3" , prop));
		Thread.sleep(2000);
	
		// getting instance again
		obj1 = (PolicyObject)api.getPolicy("testpolicy3");

		System.out.println(" After updation properties of policy object testpolicy3 is "+obj1.getProperties());

		System.out.println(" Result of deleting policy Object testpolicy3 is "+api.deletePolicy("testpolicy3"));

		
		PolicyObserver obs = new test.PolicyObserverExample();
		System.out.println(" Register PolicyObserver "+api.register(obs));
		System.out.println(" Register PolicyObserver "+api.register(obs));
		System.out.println(" remove PolicyObserver "+api.removeObserver(obs));
		api.removeAllObservers();
	
		/*
		
		
		
		
		System.out.println(" Register PolicyObserver "+api.register(new test.PolicyObserver1()));
		
		PolicyObserver obs = new test.PolicyObserver1();
		System.out.println(" Register PolicyObserver "+api.register(obs));
		System.out.println(" Register PolicyObserver "+api.register(obs));
		System.out.println(" remove PolicyObserver "+api.removeObserver(obs));
		api.removeAllObservers();
		
		api.setPolicyStatus("testpolicy3" , NmsPolicyAPI.POLICY_DISABLED);
		if((api.getPolicy("testpolicy3")).getStatus() == NmsPolicyAPI.POLICY_DISABLED)
		{
			System.out.println(" setting policy status method working well");	
		}
//		api.setPolicyStatus("testpolicy3" , NmsPolicyAPI.POLICY_ENABLED);

		Properties prop = new Properties();
		prop.put("tester","tester1");
		prop.put("type","winNt");
		prop.put("parentNet","192.168.3.0");
		prop.put("community","public");
		api.updatePolicy("StatusTestPolicy5" , prop);
		
		
		
		{
		// in oppurtune test cases			
		System.out.println("Deleting Policy with non existing name"+api.deletePolicy("4124124"));
		PolicyEvent pe = new PolicyEvent(this);
		pe.addPolicyNamesToTrigger("StatusTestPolicy5whrfqwhrf");
		api.executePolicy(pe);
		PolicyObject obj = api.getPolicy("jhrf,af");
		System.out.println(" Result of inoppurtune test "+obj);

		}
			
		api.deletePolicy("testpolicy3");

		System.out.println(obj1.getActions("TestKey"));
		System.out.println(obj1.getConditions("TestKey"));
		System.out.println(obj1.getConditionKeys());

		
		PolicyEvent pe = new PolicyEvent(this);
		pe.addPolicyNamesToTrigger("StatusTestPolicy5");
		api.executePolicy(pe);
		System.out.println("444");

		
		PolicyObserver obs = new test.PolicyObserver1();
		System.out.println(" Register PolicyObserver "+api.register(obs));
		System.out.println(" Register PolicyObserver "+api.register(obs));
		System.out.println(" remove PolicyObserver "+api.removeObserver(obs));
		api.removeAllObservers();

		api.setPolicyStatus("StatusTestPolicy5" , NmsPolicyAPI.POLICY_DISABLED);
		api.setPolicyStatus("StatusTestPolicy5" , NmsPolicyAPI.POLICY_ENABLED);

		Properties prop = new Properties();
		prop.put("tester","3333333");
		api.updatePolicy("StatusTestPolicy5" , prop);
*/
		
		}catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void callMain(String args[])
	{
		System.out.println(" Inside main ");
		new PolicyAPITest();
	}
	public boolean isInitialized()
	{
		return true;
	}
	public void shutDown()
	{

	}
}
