package test;

import java.util.Properties;
import java.util.Vector;

import com.adventnet.nms.eventdb.V3CredentialsValidator;
import com.adventnet.nms.netwatch.DiscoveryAPI;
import com.adventnet.nms.netwatch.NetWatcher;
import com.adventnet.nms.util.SnmpPing;
import com.adventnet.nms.util.WatchUtil;
import com.adventnet.snmp.beans.SnmpTarget;
import com.adventnet.snmp.sas.ProtocolDataUnit;

public class V3CredentialsValidatorImpl implements V3CredentialsValidator
{
	public V3CredentialsValidatorImpl ()
	{

	}     

	@Override
	public int validate(ProtocolDataUnit ppdu) {
		// TODO Auto-generated method stub
        int result = 0;
		String network = null;
		String netmask = null;
		String ipAddress = ppdu.getHostAddress();

		DiscoveryAPI discAPI = DiscoveryAPI.getInstance(); 

		for (int i = 0; i < discAPI.getInNets().size(); i++) {
			String tempNet = (String) discAPI.getInNets().get(i);
			String tempMask = (String) discAPI.getNetmasks().get(i);
			// Check whether node IP is in network or it is network itself.
			if (WatchUtil.inNet(ipAddress, tempNet, tempMask) || ipAddress.equals(tempNet)) {
				network = tempNet;
				netmask = tempMask;
				break;
			}
		}

//		NetWatcher nw = (NetWatcher) discAPI.getNetWatcher(network); 
		SnmpPing sping = new SnmpPing();
		//sping.snmpping(ppdu.getHostAddress());
		Vector v3Vect = sping.getV3CredentialsForTheHost(ipAddress,network,true);

		//Vector v3Vect = nw.sping.getV3CredentilsForTheHost(ipAddress,network,true);

		if(v3Vect == null || v3Vect.isEmpty())
		{
			return result;
		}
		for(int i=0;i<v3Vect.size();i++)
		{
			Properties v3Prop = (Properties)v3Vect.elementAt(i);
			String authProtocol = v3Prop.getProperty("AUTHPROTOCOL");
			String authPassword = v3Prop.getProperty("AUTHPASSWORD");
			String privProtocol = v3Prop.getProperty("PRIVPROTOCOL");
			String privPassword = v3Prop.getProperty("PRIVPASSWORD");
			SnmpTarget target = new SnmpTarget();
			target.setTargetHost(ppdu.getHostAddress());
			target.setTargetPort(ppdu.getRemotePort());	
			target.setPrivPassword(privPassword);
			target.setAuthProtocol(Integer.parseInt(authProtocol));
			target.setPrivProtocol(Integer.parseInt(privProtocol));
			target.setAuthPassword(authPassword);
			target.setSnmpVersion(target.VERSION3);
			result=target.create_v3_tables();		
		}
		return result;	
	}
}// PreStartupValidatorImpl