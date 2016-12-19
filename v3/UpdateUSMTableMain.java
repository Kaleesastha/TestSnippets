package test;
import com.adventnet.snmp.snmp2.*;
import com.adventnet.nms.util.*;
import java.util.*;
import com.adventnet.utils.*;
import com.adventnet.snmp.snmp2.usm.*;
import com.adventnet.snmp.beans.*;

public class UpdateUSMTableMain implements RunProcessInterface {
	public void callMain(String[] s){
		try
		{
			SnmpTarget target = PureServerUtils.getSnmpTarget();
			 String NO_AUTH	= "20";
			 String MD5_AUTH = "21";
			 String SHA_AUTH = "22";
			 String NO_PRIV	= "51";
			 String DES		= "50";
			 String AES         = "49"; //No I18N

			Properties prop = new Properties();
			/**/
			prop.put("SNMPAGENTPORT","8003"); 
			prop.put("SECURITYLEVEL","NoAuthNoPriv"); 
			prop.put("USERNAME","noAuthUser"); 
			prop.put("CONTEXT","noAuth");
			/*NoAuthNoPriv Ends*/

			/*AuthNoPriv*/
			prop.put("SECURITYLEVEL","AuthNoPriv"); 
			prop.put("AUTHPROTOCOL","MD5"); 
			prop.put("USERNAME","authUser"); 
			prop.put("AUTHPASSWORD","authUser"); 
			prop.put("CONTEXT","auth");
			/*AuthNoPriv Ends*/

			/*AuthPriv*/
			prop.put("SECURITYLEVEL","AuthPriv"); 
			prop.put("AUTHPROTOCOL","MD5"); 
			prop.put("PRIVPROTOCOL","CBC-DES"); 
			prop.put("USERNAME","privUser"); 
			prop.put("AUTHPASSWORD","authUser"); 
			prop.put("PRIVPASSWORD","privUser"); 
			prop.put("CONTEXT","priv");
			/*AuthPriv Ends*/
			System.err.println("props at last : "+prop);

			/**/
			String securityLevel = "NoAuthNoPriv";
			String port = prop.getProperty("SNMPAGENTPORT");
			String userName = prop.getProperty("USERNAME");
			String engineName = "null";
			String authProtocol = prop.getProperty("AUTHPROTOCOL");
			String authPassword = prop.getProperty("AUTHPASSWORD");
			String privProtocol = prop.getProperty("PRIVPROTOCOL");
			String privPassword = prop.getProperty("PRIVPASSWORD");
			securityLevel = prop.getProperty("SECURITYLEVEL");
			/**/
			if(securityLevel.equalsIgnoreCase("NoAuthNoPriv"))
			{
				authProtocol=NO_AUTH;
				privProtocol=NO_PRIV;
			}
			else if(securityLevel.equalsIgnoreCase("AuthNoPriv"))
			{
				if(authProtocol.equals("MD5"))
				{
					authProtocol=MD5_AUTH;
					privProtocol = NO_PRIV;
				}
				else if(authProtocol.equalsIgnoreCase("SHA"))
				{
					authProtocol=SHA_AUTH;
					privProtocol = NO_PRIV;
				}
			}
			else if(securityLevel.equalsIgnoreCase("AuthPriv"))
			{
				if(authProtocol.equals("MD5"))
				{
					authProtocol=MD5_AUTH;
				}
				else if(authProtocol.equals("SHA"))
				{
					authProtocol=SHA_AUTH;
				}
				if(privProtocol.equals("CBC-DES"))
				{
					privProtocol=DES;
				}
				else if(privProtocol.equals("CFB-AES-128"))
				{
					privProtocol=AES;
				}

			}
			/**/

			String ipString = s[0] ;

			UDPProtocolOptions targetOptions = PureServerUtils.getTargetOptions();
			targetOptions.setRemoteHost(ipString);
			targetOptions.setRemotePort(Integer.parseInt(port));
			int authProtocolValue=20;
			int privProtocolValue=51;
			if(authProtocol != null && !authProtocol.equals("")) //NO I18N
			{
				authProtocolValue = Integer.parseInt(authProtocol);
			}
			if(privProtocol != null && !privProtocol.equals("")) //NO I18N
			{
				privProtocolValue = Integer.parseInt(privProtocol);
			}
			USMUtils.updateUsmTable(targetOptions, userName, authProtocolValue, authPassword, privProtocolValue, privPassword, target.getSnmpEngineTable(), target.getUSMTable());
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	public void shutDown() {System.err.println("UpdateUSMTable shutdown");}
	public boolean isInitialized()
	{
		System.err.println("UpdateUSMTable initialised");
		return true;
	}
}
