/* This is a test servlet */
package test;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;

import java.io.*;
import java.util.*;
import java.util.Vector;
import com.adventnet.nms.util.*;
import com.adventnet.management.log.Log;
import com.adventnet.nms.startnms.*;
import com.adventnet.snmp.mibs.*;
public class TestFEParameters extends HttpServlet
{
    HttpServletRequest req = null;
    public void doPost(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException 
    {
        doGet(req, res);
    }
    
    public synchronized void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		try
		{
		System.out.println("country is " + NmsUtil.country);
		System.out.println("language is " + NmsUtil.language);
		System.out.println("PureServerUtilsFE.be_host" + PureServerUtilsFE.be_host);
		System.out.println("PureServerUtilsFE.be_port" + PureServerUtilsFE.be_port);
		System.out.println("PureServerUtilsFE.be_registry_port" + PureServerUtilsFE.be_registry_port);
		System.out.println("NmsMainFE.proto" + NmsMainFE.proto);
		System.out.println("PureServerUtils.useJDBC"+PureServerUtils.useJDBC);
		System.out.println("PureUtils.usersDir"+PureUtils.usersDir);
		
		}catch (Exception e)
		{
			e.printStackTrace();
		}
/*		
		NmsFileUtil nfu = new NmsFileUtil("logs");
		System.out.println("NmsFileUtil******** ");
		System.out.println("getfilecount is " + nfu.getFileCount("nmsout"));
		System.out.println("getfileindex is  " + nfu.getFileIndex("nmsout12.txt"));
		System.out.println("getfilemax is " + nfu.getFileMax("nmsout.txt"));	
		System.out.println("getfilenext is " + nfu.getFileNext("nmsout.txt"));
		System.out.println("getfiletype is " + nfu.getFileType("nmsout66.txt"));
		System.out.println("NmsUtil***********");
		NmsUtil.setDEBUG(true);
		System.out.println("country is " + NmsUtil.country);
		System.out.println("current_keys is " + NmsUtil.current_keys);
		System.out.println("initial mibOps is " + NmsUtil.mibOps);
		NmsUtil.addMIBModule("Printer-MIB");
		System.out.println("mibops after loading mibmodule is " + NmsUtil.mibOps);
		System.out.println("checkonestring returns " + NmsUtil.checkOneString("srik*","srikanth"));
		System.out.println("checkstring returns " + NmsUtil.checkString("srikanth","sri*th"));
		System.out.println("datestring is " + NmsUtil.dateString(5857235));
		System.out.println("datestring is " + NmsUtil.dateString("967705857235"));
		/*for (int i = 0 ; i < 100; i++)
			System.out.println(i+"");*/
	/*	System.out.println("deletefile returned " + NmsUtil.deleteFile(".","NETMREG.DATA"));
		Properties p = new Properties();
		p.put("name","srikanth");
		byte[] barray = NmsUtil.serializeProperties(p);
		System.out.println("deserialized Properties output is " + NmsUtil.deSerializeProperties(barray));
		Vector v = new Vector();
		v.addElement("1");
		v.addElement("2");
		barray = NmsUtil.serializeVector(v);
		System.out.println("deserialized Vector output is " + NmsUtil.deSerializeVector(barray));
		String[] sarr = new String[2];
		sarr[0] = "raji";
		sarr[1] = "vidhya";
		System.out.println("deserialized String Array output is " + NmsUtil.deSerializeStringArray(barray));
		System.out.println( "topoapi is " + NmsUtil.getTopoAPI());
		System.out.println( "eventapi is " + NmsUtil.getEventAPI());
		System.out.println( "mapapi is " + NmsUtil.getMapAPI());
		System.out.println( "pollapi is " + NmsUtil.getPollAPI());
		NmsLogMgr.MISCUSER.log( "#######alertapi is " + NmsUtil.getAlertAPI(), Log.SUMMARY);
		System.out.println("getconfirmauth is " + NmsUtil.getConfirmAuth());
		System.out.println("getdebug " + NmsUtil.getDEBUG());
		System.out.println("getboolean prop " + NmsUtil.getBooleanProp("true"));
		System.out.println(" ");
		*/

	}
}
