//$ld PersistUserInput.java, v 1.0 2006/2/17 08:18:25 p.m sasidhar Exp $
import java.rmi.*;
import java.util.*;
import com.adventnet.nms.provisioning.*;
import com.adventnet.nms.provisioning.xml.*;

public class PersistUserInput 
{
	public static void main(String[] args)throws Exception
	{
		try
		{
                    if(args.length < 2)
                        {
                            System.out.println("Usage: java PersistUserInput <PRId> <TemplateName>");
                            return;
                        }
			ProvisioningAPI provApi = (ProvisioningAPI)Naming.lookup ("rmi://localhost:1099/ProvisioningAPI");
			System.out.println ("Successfully got the handle for ProvisioningAPI");
			long prId = new Long(args[0]).longValue();
                        String templateName = args[1];
                        Properties props =new Properties();
                        props.setProperty("user_prop_key_1_ "+templateName,"user_prop_val_1");
                        props.setProperty("user_prop_key_2_ "+templateName,"user_prop_val_2");
			provApi.setUserInputs(prId,templateName,props);
                                       
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println ( "Error in getting the handle for ProvisioningAPI");
		}					
	}
}
