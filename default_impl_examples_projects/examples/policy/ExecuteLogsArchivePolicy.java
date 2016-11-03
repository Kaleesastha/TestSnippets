package test;
import com.adventnet.nms.policies.*;
import com.adventnet.management.policydb.*;
import com.adventnet.nms.util.*;
import java.util.*;

/**
* This class is an example for merging the Policy object with the
* Policy condition and Policy Action.
*/
public class ExecuteLogsArchivePolicy
{
/**
* This constructor takes the argument host name and the port through
* which the NmsPolicyAPI is looked up. To this Policy Object, the PolicyAction
* and PolicyCondition is merged.
* @param host Hostname
* @param portOption Port number
*/
	public ExecuteLogsArchivePolicy(String host,String portOption)
	{
		try
		{
                    NmsPolicyAPI api = (NmsPolicyAPI)java.rmi.Naming.lookup("//"+host+portOption+"/NmsPolicyAPI");	
                    PeriodicPolicyObject archObj = new LogsArchivePolicy();
                    PolicyAction policyAct=new LogsArchiveAction();
                    PolicyCondition policyCond=new LogsArchiveCondition();
                    archObj.setName("ArchivePolicy");
                    archObj.setGroupName("TestArchiveGroup");
                    // for adding a policy
                    api.addPolicy(archObj);
                    Thread.sleep(2000);
                    api.addPolicyActionAndCondition("ArchivePolicy",policyAct,policyCond);
                    Thread.sleep(2000);
                    PolicyEvent pe = new PolicyEvent(this);
                    pe.addPolicyNamesToTrigger("ArchivePolicy");
                    Thread.sleep(2000);
                    // For policy execution
                    api.executePolicy(pe);
                    System.out.println(" Policy executed successfully ");
		}catch (Exception e)
		{
			System.err.println(" Exception occured : ");
			e.printStackTrace();
		}
	}
	
	public static void main(String args[])
	{
		String host = "localhost";
                String portOption = ":1099";
                for(int i = 0; i < args.length;i++)
                {
                        if(args[i].equals("-h"))
                        {
                                host = args[i+1];
                                i++;
                        }
                        else if(args[i].equals("-p"))
                        {
                                portOption = ":" + args[i+1];
                                i++;
                        }
                }
		new ExecuteLogsArchivePolicy(host,portOption);
	}
}
