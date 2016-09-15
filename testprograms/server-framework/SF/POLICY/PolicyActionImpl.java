package test;
import com.adventnet.management.policydb.*;

public class PolicyActionImpl  implements PolicyAction
{
	public String getKey()
	{
		return "TestKey";
	}
	
	public int getPriority()
	{
		return 5;
	}
	public void executeAction(PolicyEvent pe)
	{
		System.out.println(" This is from PolicyConditionImpl to confirm working ");
	}
}
