package test;
import com.adventnet.management.policydb.*;

public class PolicyConditionImpl  implements PolicyCondition
{
	public boolean isConditionSatisfied(PolicyEvent pe)
	{
		return true;
	}
	
	public String getKey()
	{
		return "TestKey";
	}
}
