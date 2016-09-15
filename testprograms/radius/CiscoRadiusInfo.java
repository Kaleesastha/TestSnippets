package test.radius;


/**
 * CiscoRadiusInfo.java
 *
 *
 * Created: Thu Mar 18 09:25:17 2004
 *
 * @author <a href="mailto:vasus@.adventnet.com"</a>
 * @version
 */

import com.adventnet.nms.util.PureUtils;
import com.adventnet.snmp.beans.*;
import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.File;

//wifi import
import com.adventnet.wifi.topo.server.*;

public class CiscoRadiusInfo 
{
public static String aphost="Not Available";
public static String rserverName=null;
public static String rserverPort=null;
public static String rserverSharedSecret = null;
public static String rserverTimeOut = null;
public static String rretryValue=null;
public static String rserverEAPAuth=null;
public static String rserverMacAddr=null;
public static  String rserverAdminAuth = null;
public static  String rserverMipAuth = null;
public static String priority=null;
public static int OIDValue=1;
public static String vendorName = null;

public static SnmpTarget target = new SnmpTarget();


public static Element ciscoEle;
public Element root;

public CiscoRadiusInfo()
{
	
}

public void getInputFile()
{
	String confHome =  PureUtils.usersDir + "conf";
        String fileName = PureUtils.rootDir + System.getProperty("file.separator") + "conf" + System.getProperty("file.separator") + "UpdateRadiusInfo.xml" ;
        //String fileName = "." + System.getProperty("file.separator") + "UpdateRadiusInfo.xml" ;
	System.out.println(fileName);
        File radiusFile = new File ( fileName );
        if ( !radiusFile.exists() )
        {
	    System.out.println("file Not Found");
        }

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try
        {
            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(radiusFile);

            Element root = doc.getDocumentElement();

	    
            Element detailsEle =(Element) (root.getElementsByTagName("Details")).item(0);

	    ciscoEle =(Element) (root.getElementsByTagName("server")).item(0);	    	
            //serverEle =(Element) (root.getElementsByTagName("server")).item(0);
	
        }
        catch (Exception e )
        {
            e.printStackTrace();
        }
 }


    
    
public void init(String aphost)
{
	try{
		getInputFile();

		AccessPoint ap = new AccessPoint();
		vendorName = ap.getVendor();
        	target.setTargetHost(aphost);
		ciscoOID();
                	
	    }catch(Exception e)
	    {
		System.err.println("Invalid AP ");
		e.printStackTrace();
	     }

}

public void getAuthType(String serverPriority)
{
	
		try{		
			if(serverPriority.equals("RadiusServerOne"))
			{
	  		OIDValue = 1;
			}else if(serverPriority.equals("RadiusServerTwo"))
			{
	  		OIDValue = 2;
			}else if(serverPriority.equals("RadiusServerThree"))
			{
	                OIDValue= 3;
			}else if(serverPriority.equals("RadiusSecondaryFour"))
			{
	 		OIDValue= 4;
			}else
			{
			System.out.println("Invalid Value   " + OIDValue);
			}
	}catch(Exception e)
	{
	e.printStackTrace();
	}

}

public static void ciscoOID()
{
	if(vendorName.equals(ciscoEle.getAttribute("vendorName")))
	{
	rserverName = ciscoEle.getAttribute("servername");
	System.out.println(rserverName);
	rserverPort = ciscoEle.getAttribute("serverport");
	System.out.println(rserverPort);
	rserverTimeOut = ciscoEle.getAttribute("serverTimeOut");
	System.out.println(rserverTimeOut);
	rserverSharedSecret = ciscoEle.getAttribute("serverSharedSecret");
	System.out.println(rserverSharedSecret);
	rserverEAPAuth = ciscoEle.getAttribute("serverEAPAuth");
	System.out.println(rserverEAPAuth);
	rserverMacAddr = ciscoEle.getAttribute("serverMacAddr");
	System.out.println(rserverMacAddr);
	rserverAdminAuth = ciscoEle.getAttribute("serverAdminAuth");
	rserverMipAuth = ciscoEle.getAttribute("serverMipAuth");
	}else
	{ System.err.println("Invalid vendor ");
	}
	
}


public void updateRadiusInfoForCiscoAP(String priority,String serverName,int serverPort,int serverTimeOut,int retryValue,String serverSharedSecret,int serverEAPAuth,int serverMacAddr,int serverAdminAuth,int serverMipAuth) throws Exception
{
	 try{
		init(aphost);
		getAuthType(priority);
	    }catch(Exception e)
	    {
		System.err.println("Invalid AP / init failed");
            }

	try{

		if(serverName !=null)
		  {
			System.out.println("oid value " + OIDValue);
			try{
            		   rserverName = rserverName + OIDValue;
            		   target.setObjectID(rserverName); 
			   System.out.println(rserverName);
            		   String s = target.snmpSet(serverName);
			   System.out.println("##########   " + s);
           		   }catch(Exception de)
			   {de.printStackTrace();}
		  }
		 	try{
                           rserverPort = rserverPort + OIDValue;
                           target.setObjectID(rserverPort); // or 1.1.0 with standard prefix
                           target.snmpSet(Integer.toString(serverPort));
                           }catch(Exception de)
                        {de.printStackTrace();}
		
			try{rserverTimeOut = rserverTimeOut + OIDValue;
			    target.setObjectID(rserverTimeOut);
			    target.snmpSet(Integer.toString(serverTimeOut));	
			    }catch(Exception e)
			    {e.printStackTrace();}

			try{rserverSharedSecret = rserverSharedSecret + OIDValue;
			    target.setObjectID(rserverSharedSecret);
                            target.snmpSet(serverSharedSecret);
                            }catch(Exception e)
                            {e.printStackTrace();}

			try{rserverEAPAuth = rserverEAPAuth + OIDValue;
                            target.setObjectID(rserverEAPAuth);
                            target.snmpSet(Integer.toString(serverEAPAuth));
                            }catch(Exception e)
                            {e.printStackTrace();}
			try{rserverMacAddr = rserverMacAddr + OIDValue;
			    target.setObjectID(rserverMacAddr);
                            target.snmpSet(Integer.toString(serverMacAddr));
                            }catch(Exception e)
                            {e.printStackTrace();}

			 try{rserverAdminAuth = rserverAdminAuth + OIDValue;
			    target.setObjectID(rserverAdminAuth);
                            target.snmpSet(Integer.toString(serverAdminAuth));
                            }catch(Exception e)
                            {e.printStackTrace();}
			
			 try{rserverMipAuth = rserverMipAuth + OIDValue;
			     target.setObjectID(rserverMipAuth);
			     target.snmpSet(Integer.toString(serverMipAuth));
			    }catch(Exception e)
			    {e.printStackTrace();}

	//updateServerProtocol(serverProtocol);
	}catch(Exception de)
	{
	System.err.println("Exception while updating the values to the radius parameters");
	de.printStackTrace();
	}
}

/*public void updateServerProtocol(String serverProtocol) throws Exception
{
	try{
	    String rserverName = serverEle.getAttribute("servername");
	    rserverName = rserverName + OIDValue;
	    System.out.println("rserverName         " + rserverName);
	    target.setObjectID(rserverName); // or 1.1.0 with standard prefix
            String s = target.snmpSet(serverProtocol);
	    System.out.println( s  + "Updated the server status to " + serverProtocol);
           }catch(Exception de)
           {
           System.err.println("Error while setting server status");
           de.printStackTrace();
           }

}*/

}
