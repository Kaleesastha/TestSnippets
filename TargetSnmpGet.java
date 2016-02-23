import com.adventnet.snmp.beans.*;
import com.adventnet.snmp.snmp2.*;
import com.adventnet.snmp.mibs.MibException;
import java.io.IOException;

public class TargetSnmpGet {
	public static void main(String[] args) {
		for(int i=0; i<1; i++) {
			String name = "Thread--"+i;
			GetRequest t1 = new GetRequest(name);
		}
	}
}

class GetRequest implements Runnable {
	Thread t;
	String name;
	
	GetRequest(String name) {
		this.name = name;
		t = new Thread(this,name);
		t.start();
	}

	SnmpTarget target;
	
	public void run() {
		ProtocolOptions tcpOpt = new TcpProtocolOptionsImpl("localhost", 8001);
		target = new SnmpTarget(2, tcpOpt);
		target.setSnmpVersion(SnmpTarget.VERSION2C);
		target.setTargetHost("localhost");
		target.setTargetPort(8001);
		try {
			target.loadMibs("/home/local/ZOHOCORP/pradheep-1134/Product/SNMPAPI_4071/WebNMS/SNMPAPI/RFC1213-MIB");
		} catch(Exception exc) {
			System.out.println("Exception Message :: " + exc.getMessage());
		}
		
		if(target.getSnmpVersion() == SnmpTarget.VERSION3) {
			target.setPrincipal("privUser");
			target.setContextName("priv");
			target.setAuthProtocol(SnmpTarget.MD5_AUTH);
			target.setAuthPassword("privUser");
			target.setPrivProtocol(SnmpTarget.CBC_DES);
			target.setPrivPassword("privUser");
			target.create_v3_tables();
		}
		String[] list = { "1.1.0", "1.2.0", "1.3.0", "1.4.0", "1.5.0", "1.6.0" };
		target.setObjectIDList(list);
		SnmpVarBind[] result = target.snmpGetVariableBindings();
		if(result != null) {
			for(int i=0;i<result.length;i++) {
				System.out.print("VALUE of OID "+list[i]+":\t");
				System.out.println(result[i].toTagString());
			} 
		} else {
			System.out.println("ERROR_CODE :: "+target.getErrorCode());
			System.out.println("ERROR_STRING :: "+target.getErrorString());
		}
		target.releaseResources();
	}
}
