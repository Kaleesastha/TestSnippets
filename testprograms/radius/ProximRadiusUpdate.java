package test.radius;


/**
 * ProximRadiusUpdate.java
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

public class ProximRadiusUpdate 
{

public static String aphost=null;
public static String rserverName=null;
public static String rserverPort=null;
public static String rserverSharedSecret = null;
public static String rserverTimeOut = null;
public static String rserverretryValue=null;
public static String rserverStatus=null;
public static String rserverformat=null;
public static String priority=null;
public static int OIDValue=1;
public static String vendorName = null;

public static SnmpTarget target = new SnmpTarget();


public static Element proximEle;
public Element root;

public ProximRadiusUpdate()
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

	    proximEle =(Element) (root.getElementsByTagName("server")).item(0);	    	
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
		proximOID();
                	
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

public static void proximOID()
{
	if(vendorName.equals(proximEle.getAttribute("vendorName")))
	{
	rserverName = proximEle.getAttribute("servername");
	System.out.println(rserverName);
	rserverStatus = proximEle.getAttribute("serverstatus");
	System.out.println(rserverName);
	rserverPort = proximEle.getAttribute("serverport");
	System.out.println(rserverPort);
	rserverSharedSecret = proximEle.getAttribute("sharedSecret");
	System.out.println(rserverSharedSecret);
	rserverTimeOut = proximEle.getAttribute("responseTime");
	System.out.println(rserverTimeOut);
	rserverretryValue = proximEle.getAttribute("retryValue");
	System.out.println(rserverretryValue);
	rserverformat = proximEle.getAttribute("format");
	System.out.println(rserverformat);
	}else
	{ System.err.println("Invalid vendor ");
	}
	
}


public void updateRadiusInfoForCiscoAP(String priority,String serverName,String status,int serverPort,String secret,int serverTimeOut,int retryValue,String format) throws Exception
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
            		   target.setObjectID(rserverName); // or 1.1.0 with standard prefix
            		   String s = target.snmpSet(serverName);
           		   }catch(Exception de)
			   {de.printStackTrace();}
		  }
			try{
                           rserverStatus = rserverStatus + OIDValue;
                           target.setObjectID(rserverStatus); // or 1.1.0 with standard prefix
                           String s = target.snmpSet(status);
                           }catch(Exception de)
                           {de.printStackTrace();}
		
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
                            target.snmpSet(secret);
                            }catch(Exception e)
                            {e.printStackTrace();}

			try{rserverretryValue = rserverretryValue + OIDValue;
                            target.setObjectID(rserverretryValue);
                            target.snmpSet(Integer.toString(retryValue));
                            }catch(Exception e)
                            {e.printStackTrace();}
			try{rserverformat = rserverformat + OIDValue;
                            target.setObjectID(rserverformat);
                            target.snmpSet(format);
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
