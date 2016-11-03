/*
 $Id: PolicyObserverExample.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
 */


package test;
import com.adventnet.management.policydb.*;
import com.adventnet.management.policydb.PolicyObserver;
import java.util.Properties;

public class PolicyObserverExample extends java.rmi.server.UnicastRemoteObject implements PolicyObserver
{

	public PolicyObserverExample() throws java.rmi.RemoteException
	{
		super();
	}
	
	public void policyStatusUpdate(Properties prop)
	{
		if(prop == null || prop.size() == 0 )
		{
			System.out.println("Not enough arguement ");	
			return;
		}
		
		int action = Integer.parseInt((String)prop.get("action"));
		String policyName = (String)prop.get("name");
		String policyGroupName = (String)prop.get("groupName");
		int currentStatus = Integer.parseInt((String)prop.get("currentStatus"));
		
		switch (action)
		{
		case NmsPolicyAPI.POLICY_ADDED:
			System.out.println(" Policy -"+policyName+" with the group Name - "+policyGroupName+" added ");
			break;

		case NmsPolicyAPI.POLICY_UPDATED:
			System.out.println(" Policy -"+policyName+" with the group Name - "+policyGroupName+" updated ");
			break;
			
		case NmsPolicyAPI.POLICY_DELETED:
			System.out.println(" Policy -"+policyName+" with the group Name - "+policyGroupName+" deleted ");
			break;
			
		case NmsPolicyAPI.POLICY_STOPPED:
			System.out.println(" Policy -"+policyName+" with the group Name - "+policyGroupName+" stopped ");
			break;
			
		case NmsPolicyAPI.POLICY_EXECUTED:
			System.out.println(" Policy -"+policyName+" with the group Name - "+policyGroupName+" executed ");
			break;

		default :
			System.out.println("Default no matching found");
		}

		
		// It's current status is 		
		switch(currentStatus)
		{
		case NmsPolicyAPI.POLICY_DISABLED:
			System.out.println(" It is currently  disabled ");
			break;
			
		case NmsPolicyAPI.POLICY_STOPPED:
			System.out.println(" It is now stopped ");
			break;
			
		case NmsPolicyAPI.POLICY_SCHEDULED:
			System.out.println(" It is scheduled to run at some time");
			break;
			
		case NmsPolicyAPI.POLICY_EXECUTED:
			System.out.println(" It is executed successfully ");
			break;
			
		default :
			System.out.println(" Not a valid status ");
		}
		
	}
	
}		

