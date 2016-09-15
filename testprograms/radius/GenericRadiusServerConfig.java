package test.radius;


/**
 * GenericRadiusServerConfig.java
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
import java.util.*;

import com.adventnet.wifi.topo.server.*;

public class  GenericRadiusServerConfig
{

private static String vendorName=null;
private static SnmpTarget target = new SnmpTarget();

private static Element d; 
private Element proximEle;
private Element root;
Properties p = new Properties();

public GenericRadiusServerConfig()
{
       //init(aphostName); 
	
}

/*
public static void main(String args[])
{
	try{
	GenericRadiusServerConfig cri = new GenericRadiusServerConfig();
	//cri.getValuesFromXml();
	}catch(Exception e)
	{
	e.printStackTrace();
	}
}
*/	

public boolean  genericApUpdate(String aphost,String vendorName,String servername,int authport,String sharedsecret,int responsetime,int retryvalue)
{

NamedNodeMap nnm;

	System.out.println("INTO GENERIC AP UPDATE         ");
        String fileName = "." + System.getProperty("file.separator") + "UpdateRadiusInfo.xml" ;
        System.out.println(fileName);
        File radiusFile = new File ( fileName );
        if ( !radiusFile.exists() )
        {
            System.out.println("file Not Found");
        }

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try
        {   
	    target.setTargetHost(aphost);
	    //target.loadMibs("/home/vasus/AWCVX-MIB.my");	   
	    //target.loadMibs("/home/vasus/BRIDGE-MIB");	   
            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(radiusFile);

            Element root = doc.getDocumentElement();

            NodeList detailsEle = (root.getElementsByTagName("server"));

	    for (int i=0;i<detailsEle.getLength();i++)
            {
              d =(Element) (root.getElementsByTagName("server")).item(i);
              nnm = d.getAttributes();
	
		
	    if(vendorName.equals(d.getAttribute("vendorname")))
	    {
		
	     for(int k=0;k<nnm.getLength();k++)
	     {
		     Node n = nnm.item(k);
		     p = new Properties();
		     p.put(n.getNodeName(),n.getNodeValue());
		     System.out.println(p);
		     for ( Enumeration e = p.keys(); e.hasMoreElements(); )
		     {
			     String key = (String) e.nextElement();
			     String value = (String) p.get(key);
			     
			     try{
			     if(key.equals("serverName"))
			     {	
			     	target.setObjectID(value); // 
			     //System.out.println("oid   " + key );
            		     	String s = target.snmpSet(servername);
			     	System.out.println(s + "  ^^^^^^^^^^^^          " +  target.snmpGet());
			     }else if(key.equals("authPort"))
			     {
			     target.setObjectID(value);
                             //System.out.println("oid   " + key );
                             String s = target.snmpSet(Integer.toString(authport));
			     System.out.println(target.snmpGet());
			     }else if(key.equals("secret"))
			     {
			      target.setObjectID(value);
                             //System.out.println("oid   " + key );
                             String s = target.snmpSet(sharedsecret);
			     System.out.println(target.snmpGet());
			     }else if(key.equals("responseTime"))
			     {	
			      target.setObjectID(value); 
                             System.out.println("oid   " + key );
                             String s = target.snmpSet(Integer.toString(responsetime));
			     }else if(key.equals("retryValue"))
			     {
			      target.setObjectID(value);
			      String s = target.snmpSet(Integer.toString(retryvalue));
			     }else
				{System.out.println("Cannot update configuration : Invalid Values");}
	
				
		   		  
				}catch(Exception error)
				{System.err.println("Exception in updateing the object");
				 return false;
				}

		     }
		}
	    }else
	    {
		return false;
	    }	
		     /*for(Enumeration e = p.elements() ; e.hasMoreElements() ;)
		       {
		       sj = (String)e.nextElement();
		       System.out.println("My Values   "  +  sj);
		       }*/

	     }
        }catch (Exception e )
        {
            e.printStackTrace();
        }
	return true;	
}


}

   

