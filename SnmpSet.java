import com.adventnet.snmp.snmp2.*;
import com.adventnet.snmp.beans.*;

public class SnmpSet {

	public static void main(String[] args) {

		SnmpTarget target = new SnmpTarget();
		target.setDebug(true);
		try {
			target.loadMibs("/home/local/ZOHOCORP/venkat-0773/webnms/1_integration_branch_v3/mibs/RFC1213-MIB");
		} catch(Exception exc) {
			System.out.println("Error loading Mibs :: " + exc.getMessage());
		}

		target.setSnmpVersion(SnmpServer.VERSION3);
		//target.setTargetHost("192.168.57.44");
		target.setTargetHost("venkat-0773.csez.zohocorpin.com");
		target.setTargetPort(8001);

		String[] oidList = {"1.4.0", "1.5.0"};
		String[] varList = {"tempContact", "tempName"};
		SnmpVar[] variableList = new SnmpVar[varList.length];
		for(int i=0; i<varList.length; i++) {
			try {
				variableList[i] = SnmpVar.createVariable(varList[i], SnmpAPI.STRING);
			} catch(Exception exc) {
				System.out.println("Error creating variable :: " + exc.getMessage());
			}
		}

		target.setPrincipal("privUser");
		target.setContextName("priv");
		target.setAuthProtocol(SnmpServer.MD5_AUTH);
		target.setAuthPassword("authUser");
		target.setPrivProtocol(SnmpServer.CBC_DES);
		target.setPrivPassword("privUser");

		try {
			target.create_v3_tables();
		} catch(Exception exc) {
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
