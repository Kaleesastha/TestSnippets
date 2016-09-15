package test.provision;

import java.rmi.*;
import java.util.*;
import com.adventnet.nms.provisioning.*;
import com.adventnet.nms.provisioning.ui.*;

class ProvisioningTest extends Thread
{
    Properties props=new Properties();
    ProvisioningAPI provAPI=null;

    public static void main(String args[])
    {
		if(args.length!=2)
		{
			System.out.println("USAGE: java test.provision.ProvisioningTest hostName portNumber");
			System.exit(0);
		}
try
    {
        Properties prop= new Properties();
       
        prop.setProperty("USERNAME","root");
        prop.setProperty("PASSWORD","public");
        prop.setProperty("SERVERNAME",args[0]);
        prop.setProperty("SERVERPORT",args[1]);
        prop.setProperty("MODE","session");

        ProvisioningAPI prov=ProvClientUtils.getProvisioningAPI(prop);
   
        ProvisioningTest prime= new ProvisioningTest(prop,prov);
        prime.start();
        prime= new ProvisioningTest(prop, prov);
        prime.start();
        prime=  new ProvisioningTest(prop,prov);
        prime.start();
        prime= new ProvisioningTest(prop,prov);
        prime.start();
prime= new ProvisioningTest(prop, prov);
        prime.start();
        prime=  new ProvisioningTest(prop,prov);
        prime.start();
        prime= new ProvisioningTest(prop,prov);
        prime.start();
        prime= new ProvisioningTest(prop, prov);
        prime.start();
        prime=  new ProvisioningTest(prop,prov);
        prime.start();
        prime= new ProvisioningTest(prop,prov);
        prime.start();
 }
catch(Exception e)
    {
        e.printStackTrace();
    }
    }
    ProvisioningTest(Properties prop,ProvisioningAPI prov)
    {
        this.props=prop;
        this.provAPI=prov;
    }
    

    public void run()
    {
        try
            {
               
                for(int i=0;i<100;i++)
                    {
                        //        ProvisioningAPI provAPI = (ProvisioningAPI)Naming.lookup("rmi://"+args[0]+":1099/ProvisioningAPI");
                       
                        String template = provAPI.getTemplate("Test");
                        String result = provAPI.provision(template);
                        System.out.println("The result of "+i+" "+result);
                    }
            }
        catch(Exception e)
            {
                e.printStackTrace();
            }
    }

}


