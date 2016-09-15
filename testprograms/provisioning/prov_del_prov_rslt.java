import java.rmi.*;

import com.adventnet.nms.provisioning.*;
import com.adventnet.nms.provisioning.*;

public class prov_del_prov_rslt
{
    public static void main(String args[]) throws Exception
    {
        if(args.length < 2)
        {
            System.out.println("Host in which NMSServer is running and provision result id are not set USAGE:: java prov_del_prov_rslt <NMSServer> <PRId> [<forcefulDelete>]");
			return;
        }
        try
        {
            ProvisioningAPI provApi = (ProvisioningAPI)Naming.lookup("//"+ args[0] + "/ProvisioningAPI");
            long prId = Long.parseLong(args[1]);
            if(args.length > 2)
            {
                String forcefulDeleteStr = args[2];
                if(forcefulDeleteStr.equalsIgnoreCase("true"))
                {
                    provApi.deleteProvisionResult(prId, new Boolean(forcefulDeleteStr).booleanValue());
                }
                else if(forcefulDeleteStr.equalsIgnoreCase("false"))
                {
                    provApi.deleteProvisionResult(prId, new Boolean(forcefulDeleteStr).booleanValue());
                }
                else
                {
                    provApi.deleteProvisionResult(prId);
                }
            }
            else provApi.deleteProvisionResult(prId);
        }
        catch(InvalidProvisionResultException pre)
        {
            pre.printStackTrace();
        }
        catch(ProvisioningException pe)
        {
            pe.printStackTrace();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
