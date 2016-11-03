package test;
import com.adventnet.nms.policies.*;
import com.adventnet.management.policydb.*;
import com.adventnet.nms.util.*;
import java.util.*;

/**
 * This class is an example for getting notification while the user tries to 
 * add,delete,update,execute and schedule a Policy.
 */
public class ExecutePolicyObserverExample
{

   /**
	* This constructor takes the argument host name and the port through
	* which the NmsPolicyAPI is looked up. And the PolicyObserver is 
	* registered in the NmsPolicyAPI.
	* @param host Hostname
	* @param portOption Port number
	*/
	public ExecutePolicyObserverExample(String host,String portOption)
	{
		try
		{
			NmsPolicyAPI api = (NmsPolicyAPI)java.rmi.Naming.lookup("//"+host+portOption+"/NmsPolicyAPI");	
        	PolicyObserver obs = new test.PolicyObserverExample();
			System.out.println(" Register PolicyObserver "+api.register(obs));
		}
		catch (Exception e)
		{
			System.err.println(" Exception occured : "+e);
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
		new ExecutePolicyObserverExample(host,portOption);
	}
}
