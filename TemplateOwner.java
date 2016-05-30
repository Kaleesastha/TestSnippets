package test;

import com.adventnet.nms.util.*;
import com.adventnet.nms.provisioning.xml.*;
import com.adventnet.nms.provisioning.*;
public class TemplateOwner implements RunProcessInterface {
	public void callMain(String args[]) {
		try{
			ProvisioningAPI api = null;
			while (api == null){
				api = (ProvisioningAPI) NmsUtil.getAPI("ProvisioningAPI");
				Thread.sleep(2000);
			}
			System.err.println("after sleep!!! : "+api);

			for (int k=0;k<args.length;k++){
				String file = api.getTemplate(args[k]);
				//System.err.println("Template : "+file);
				try{
					Template t = new Template(file);
					System.err.println("Owner is: "+t.getOwner());
				} catch(Exception exp){exp.printStackTrace();}
			} 		
		}
		catch(Exception e) {e.printStackTrace();}
	}
	public boolean isInitialized() { return true;}
	public void shutDown() {}
}

//PROCESS test.TemplateOwner
//ARGS DelTrapTarget AddNode AddTrapTarget AddV3Credentials
//For Add*.xml, we changed the owner as master
//For DelTrapTarget, the owner was left as root itself
