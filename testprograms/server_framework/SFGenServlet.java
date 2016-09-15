
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
import com.adventnet.nms.startnms.NmsCorbaMain;
import com.adventnet.snmp.mibs.*;

public class SFGenServlet extends HttpServlet
{
    HttpServletRequest req = null;
	PrintWriter out = null;
    public void doPost(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException 
    {
        doGet(req, res);
    }
    
    public synchronized void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		res.setContentType("text/html");
		out = res.getWriter();
		executeTestCase();
	}

	private void executeTestCase()
	{
		try
		{
			testCase019();
			testCase019_1();
				
		}catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void testCase019()
	{
		try
		{
			out.println("<b>TESTCASE 19 STARTED</b>");
			XMLDataReader re = new XMLDataReader("C:/NMS4 SP1/AdventNet/WebNMS/users/root/Tree.xml");
			Vector v = new Vector();
			v = re.getRootChildNodes();

			for (Enumeration e = v.elements() ; e.hasMoreElements() ;) 
			{
				XMLNode node1 = re.searchNodeForId("Configuration",(XMLNode)e.nextElement());
				out.println("<br>searchNodeForId : " + node1);
			}
 	
		}catch (Exception e)
		{
			e.printStackTrace();
		}
	}


	private void testCase019_1()
	{
		try
		{
			out.println("<br><br><b>TESTCASE 19.1 STARTED</b>");
			XMLDataReader xm = new XMLDataReader("C:/NMS4 SP1/AdventNet/WebNMS/users/root/Tree.xml",true,true,true);		
			String enc = xm.getEncoding();
			out.println("<br>ENCODING When set to TRUE  : " + enc);
		
			XMLDataReader xm1 = new XMLDataReader("C:/NMS4 SP1/AdventNet/WebNMS/users/root/Tree.xml",true,true,false);		
			String enc1 = xm1.getEncoding();
			out.println("<br>ENCODING When set to FALSE : " + enc1);
		}catch (Exception e)
		{
			e.printStackTrace();
		}
	}


}
