import com.adventnet.snmp.snmp2.*;
import com.adventnet.snmp.beans.*;

public class SnmpSet {

	public static void main(String[] args) {
                System.err.println("Usage : java SnmpSet -Droot.dir=<NMS_HOME> <IPAddress_of_NMS_Server> <authuser / privuser / noauthuser> ");
		SnmpTarget target = new SnmpTarget();
		target.setDebug(true);
                try {
                        String nmsDir = System.getProperty("root.dir");
                        target.loadMibs(nmsDir+"/mibs/AdventNet-WebNMS-MIB"+ " "+nmsDir+"/mibs/RFC1213-MIB");
                } catch(Exception exc) {
                        System.out.println("Error loading Mibs :: " + exc.getMessage());
                }   

		target.setSnmpVersion(SnmpServer.VERSION3);
		//target.setTargetHost("192.168.57.44");
		target.setTargetHost(args[0]);
		target.setTargetPort(8001);

		/*String[] oidList = {"1.4.0", "1.5.0"};
		String[] varList = {"tempContact", "tempName"};*/
		String[] oidList = {".1.3.6.1.4.1.2162.4.1.8.0"};
		String[] varList = {"ddd,puiblic"};
		SnmpVar[] variableList = new SnmpVar[varList.length];
		for(int i=0; i<varList.length; i++) {
			try {
				variableList[i] = SnmpVar.createVariable(varList[i], SnmpAPI.STRING);
			} catch(Exception exc) {
				System.out.println("Error creating variable :: " + exc.getMessage());
			}
		}

		if(args[1].equalsIgnoreCase("noauthuser")){
			target.setPrincipal("noAuthUser");
			target.setContextName("noAuth");
		}

		if(args[1].equalsIgnoreCase("privuser")){
			target.setPrincipal("privUser");
			target.setContextName("priv");
			target.setAuthProtocol(SnmpServer.MD5_AUTH);
			target.setAuthPassword("authUser");
			target.setPrivProtocol(SnmpServer.CBC_DES);
			target.setPrivPassword("privUser");
		}

		if(args[1].equalsIgnoreCase("authuser")){
			target.setPrincipal("authUser");
			target.setContextName("auth");
			target.setAuthProtocol(SnmpServer.MD5_AUTH);
			target.setAuthPassword("authUser");
		}
		try {
			int res = target.create_v3_tables();
		System.err.println("result is ;" +res );
		} catch(Exception exc) {
			exc.printStackTrace();
			System.out.println("Error while creating v3 tables :: " + exc.getMessage());
		}

		target.setObjectIDList(oidList);
		SnmpVarBind[] result = null;
		try {
			result = target.snmpSetVariableList(variableList);
		} catch(Exception exc) {
			exc.printStackTrace();
			System.out.println("Error while performing SnmpSet :: " + exc.getMessage());
		}

		if (result != null) {
			for(int i=0; i<result.length; i++) {
				System.out.println(result[i]);
			}
		} else {
				System.out.println("Null response");
		}

		target.releaseResources();
	}
}
