import java.io.*;
import java.rmi.*;
import java.util.*;
import org.w3c.dom.Element.*;
import com.adventnet.nms.provisioning.*;
import com.adventnet.management.config.xml.*;
import com.adventnet.nms.provisioning.xml .*;

public class prov_sch_th_pri_Tester
{
	public static void main(String[] args)
	{
		try
		{
                    ProvisioningAPI provAPI = (ProvisioningAPI) Naming.lookup("ProvisioningAPI");
                    if(provAPI != null)
			{
                            System.out.println("ProvisioningAPI is successfully accessed" );
			}
                    
			
                    long when = System.currentTimeMillis()+5000;
                    
                    /*    ##########This Part of the code is to reset the rearmTime */
                    
                    Properties prop = new Properties();
                    prop.put("When",Long.toString(when));
                    prop.put("Host","sasidhar");
                    
                    TemplateParams templateParams = new TemplateParams();
                    templateParams.setProperties(prop);
                    String params = templateParams.toString();
                    System.out.println(params);
                    String t = provAPI.getTemplate("prov_sch_th_pri_Template" , params);
                    provAPI.provision(t);
                    
                    prop.put("When",Long.toString(when+10));
                    templateParams.setProperties(prop);
                    params = templateParams.toString();
                    t = provAPI.getTemplate(".","prov_sch_th_pri_Template" , params);
                    provAPI.provision(t);
                    
                    // Template0 is given as input to the Provision Object	
                    prop.put("When",Long.toString(when+6000));
                    templateParams.setProperties(prop);
                    params = templateParams.toString();
                    t = provAPI.getTemplate(".","prov_sch_th_pri_Template0" , params);
                    provAPI.provision(t);
                    
                    // Template1 is given as input to the Provision Object
                    prop.put("When",Long.toString(when+6010));
                    templateParams.setProperties(prop);
                    params = templateParams.toString();
                    t = provAPI.getTemplate(".","prov_sch_th_pri_Template1" , params);
                    provAPI.provision(t);
                    
                    // Template2 is given as input to the Provision Object
                    prop.put("When",Long.toString(when+6000));
                    templateParams.setProperties(prop);
                    params = templateParams.toString();
                    t =provAPI.getTemplate(".","prov_sch_th_pri_Template2" , params);
                    provAPI.provision(t);
                    
                    // Template3 is given as input to the Provision Object
                    prop.put("When",Long.toString(when+6000));
                    templateParams.setProperties(prop);
                    params = templateParams.toString();
                    t =provAPI.getTemplate(".","prov_sch_th_pri_Template3" , params);
                    provAPI.provision(t);
                    
                    // Template4 is given as input to the Provision Object
                    prop.put("When",Long.toString(when+6000));
                    templateParams.setProperties(prop);
                    params = templateParams.toString();
                    t = provAPI.getTemplate(".","prov_sch_th_pri_Template4" , params);
                    provAPI.provision(t);
                    
                    // Template5 is given as input to the Provision Object
                    prop.put("When",Long.toString(when+16000));
                    templateParams.setProperties(prop);
                    params = templateParams.toString();
                    t = provAPI.getTemplate(".","prov_sch_th_pri_Template5" , params);
                    provAPI.provision(t);
                    
                    // Template6 is given as input to the Provision Object
                    prop.put("When",Long.toString(when+16000));
                     templateParams.setProperties(prop);
                    params = templateParams.toString();
                    t =provAPI.getTemplate(".","prov_sch_th_pri_Template6" , params);
                    String id=provAPI.provision(t);
                    
                    System.out.println("Id of the  Provision Result :"+id);
                    
		}
		catch (Exception e)
                    {
			System.out.println("Exception in accesssing the handle to provisioningAPI");
			e.printStackTrace();
                    }
	}
}
