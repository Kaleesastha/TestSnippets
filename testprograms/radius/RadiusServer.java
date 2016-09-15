package test.radius;

//java imports
import java.sql.*;

//import com.adventnet.nms.util.NmsLogMgr;

//nms imports
import com.adventnet.management.log.Log;
import com.adventnet.nms.topodb.Node;

//wifi imports
import com.adventnet.wifi.utils.server.WLANLogManager;

public  class RadiusServer extends Node
{
	private int authport;
	private int acctport;
	private String sharedsecret = "";
	private String ipAddress = "";

  
 public void setAuthport(int authportArg)
  {
	 authport = authportArg;
  
  } 
  
 public int getAuthport()
  {
	return authport;
  
  } 
  
 public void setAcctport(int acctportArg)
  {
 	acctport = acctportArg;
  
  } 
  
 public int getAcctport()
  {
	return acctport;
  
  } 
  
 public void setSharedsecret(String sharedsecretArg)
  {
 if(sharedsecretArg != null)
 {
	sharedsecret = sharedsecretArg;
 }
  
  } 
  
 public String getSharedsecret()
  {
	return sharedsecret;
  
  } 

public void setIpAddress(String ipAddressArg)
  {

if(ipAddressArg != null)
 {
         ipAddress = ipAddressArg;
 }
}


 
public String getIpAddress()
{
	return ipAddress;
}

 
  public java.util.Properties getProperties()
  {
	java.util.Properties mosource_prop;
	mosource_prop = super.getProperties();
	
	mosource_prop.put("authport",String.valueOf(getAuthport()));
	
	mosource_prop.put("acctport",String.valueOf(getAcctport()));
	
	if(getSharedsecret()!=null)
	{
		mosource_prop.put("sharedsecret",getSharedsecret());
	}
	if(getIpAddress()!=null)
	{
		mosource_prop.put("ipAddress",getIpAddress());
	}

	//NmsLogMgr.TOPOUSER.log("Properties returned in getProperties method of MO radiusserver are "+mosource_prop.toString(),Log.DEBUG);
	WLANLogManager.DISCOUT.log("Properties returned in getProperties method of MO radiusserver are"+ mosource_prop.toString(),Log.DEBUG);
	return mosource_prop;
  
  } 
  
  public void setProperties(java.util.Properties p)
  {


	//NmsLogMgr.TOPOUSER.log("Properties received in setProperties method of MO radiusserver are "+p.toString(),Log.DEBUG);
	WLANLogManager.DISCOUT.log("Properties received in setProperties method of MO radiusserver are "+p.toString(),Log.DEBUG);
	

	String ipAddressValue=p.getProperty("ipAddress");
	if(ipAddressValue!=null)
        {
                ipAddress=ipAddressValue;
		p.remove("ipAddress");
        }

	String authportvalue=p.getProperty("authport");
	
	if(authportvalue!=null)
	{ 
		authport=Integer.parseInt(authportvalue);
		p.remove("authport");
	} 

	String acctportvalue=p.getProperty("acctport");
	if(acctportvalue!=null)
	{ 
		acctport=Integer.parseInt(acctportvalue);
		p.remove("acctport");
	} 

	String sharedsecretvalue=p.getProperty("sharedsecret");
	if(sharedsecretvalue!=null)
	{ 
		sharedsecret=sharedsecretvalue;
		p.remove("sharedsecret");
	} 
	super.setProperties(p);

  
  } 
  
  public Object clone()
  {
	return super.clone();
  
  } 
  
  public int checkStatus() throws java.rmi.RemoteException
  {
	return super.checkStatus();
  
  } 

} 
