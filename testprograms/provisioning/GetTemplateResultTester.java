//$ld GetTemplateResultTester.java, v 1.0 2006/2/17 08:18:25 p.m sasidhar Exp $
import java.rmi.*;
import java.util.*;
import com.adventnet.nms.provisioning.*;
import com.adventnet.nms.provisioning.xml.*;

public class GetTemplateResultTester 
{
	public static void main(String[] args)throws Exception
	{
		try
		{
                    if(args.length < 1)
                        {
                            System.out.println("Usage: java GetTemplateResultTester <PRId> ");
                            return;
                        }
			ProvisioningAPI provApi = (ProvisioningAPI)Naming.lookup ("rmi://localhost:1099/ProvisioningAPI");
			System.out.println ("Successfully got the handle for ProvisioningAPI");
			//long prid = new Long(args[0]).longValue();
                        String operationId = args[0];
                        OperationIdentifier oi = new OperationIdentifier();
                        oi.setAttribute("operationIdentifier", operationId+"");
			String templateresult = provApi.getResult(oi.toString());
			
                        System.out.println(templateresult);
                //}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println ( "Error in getting the handle for ProvisioningAPI");
		}					
	}
}
