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
import com.adventnet.nms.startnms.NmsCorbaMain;
import com.adventnet.snmp.mibs.*;
public class TestXMLServlet extends HttpServlet
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
			FileInputStream fr = new FileInputStream("f:/webnms/conf/ldap.conf");
//			XMLDataReader re = new XMLDataReader("f:/webnms/conf/policy.conf",false);
			XMLDataReader re = new XMLDataReader("file:f:/webnms/users/root/paneldata.xml");
//			re.load("file:///f:/webnms/conf/policy.conf");
			System.out.println(re.getRootNode().getNodeName());
			XMLNode node = re.getNodeById("Network Database");
			System.out.println("getNodeById"+node.getNodeName());

			{
				Vector v = re.getRootChildNodes();
				for(Enumeration en = v.elements() ; en.hasMoreElements();)
				{
					XMLNode node1 = re.searchNodeForId("Network Database",(XMLNode)en.nextElement());
					System.out.println("getNodeById"+node1.getNodeName());
//					System.out.println(((XMLNode)en.nextElement()).getNodeName());
				}
			}
			
/*
			For testcase 15 :			
			XMLDataReader re = new XMLDataReader("file:f:/webnms/conf/policy.conf","com.adventnet.nms.util.XMLNode");

			for test case id :17
			XMLDataReader re = new XMLDataReader("file:f:/webnms/users/root/paneldata.xml");
			XMLNode node = re.getNodeById("Network Database");
			System.out.println("getNodeById"+node.getNodeName());
		
			for test case id :19
			XMLDataReader re = new XMLDataReader("file:f:/webnms/users/root/paneldata.xml");
			System.out.println(re.getRootNode().getNodeName());
			XMLNode node = re.getNodeById("Network Database");
			System.out.println("getNodeById"+node.getNodeName());
			{
				Vector v = re.getRootChildNodes();
				for(Enumeration en = v.elements() ; en.hasMoreElements();)
				{
				Vector v = re.getRootChildNodes();
				for(Enumeration en = v.elements() ; en.hasMoreElements();)
				{
					XMLNode node1 = re.searchNodeForId("Network Database",(XMLNode)en.nextElement());
					System.out.println("getNodeById"+node1.getNodeName());
				}
			}


			
			
			
			
			
			

					//InputStream is = new InputStream();
		//FileInputStream fis = new FileInputStream("g:/Program Files/sync39/WebNMS/listmenus/linkmenu.xml");
		//FileInputStream fis = new FileInputStream("g:/Program Files/sync39/WebNMS/conf/configprovider.xml");
		//XMLDataReader xm = new XMLDataReader(fis, "com.adventnet.nms.util.XMLNode");
		//XMLDataReader xm = new XMLDataReader("file:g:/Program Files/sync39/WebNMS/listmenus/linkmenu.xml", true, true);
		XMLDataReader xm = new XMLDataReader("file:g:/Program Files/sync39/WebNMS/users/root/PanelData.xml", true, true);		
		//xm.load("file:g:/Program Files/sync39/WebNMS/listmenus/linkmenu.xml");
		//xm.load("file:///g:/Program Files/sync39/WebNMS/conf/configprovider.xml");
		
		XMLNode rootnode = xm.getRootNode();
		System.out.println( "Root Node Name = " + rootnode.getNodeName() );
		System.out.println( "Root Node Value = " + rootnode.getNodeValue() );
		System.out.println( "Root Node Attribute list = " + rootnode.getAttributeList() );
		Vector vec = xm.getRootChildNodes();
		System.out.println("Child Vectors");
		XMLNode childnode = null;
		for( Enumeration en = vec.elements(); en.hasMoreElements(); )
		{
			childnode = (XMLNode)en.nextElement();
			System.out.println( "Child Node Name = " + childnode.getNodeName() );
			System.out.println( "Child Node Value = " + childnode.getNodeValue() );
			System.out.println( "Child Node Attribute list = " + childnode.getAttributeList() );
		}
		
		XMLNode node = xm.getNodeById("Network Database");
		System.out.println( "Node Attributes = " + node.getAttributeList() );
		
		XMLNode node1 = xm.searchNodeForId( "Network Database.Networks", childnode );
		System.out.println( "Node1 attributes = " + node1.getAttributeList() );
		
		//XMLDataReader xm1 = new XMLDataReader("file:///d|/sync39/webnms/listmenus/linkmenu.xml");

		//InputStream in =new InputStream();
		//in.read("file:///d|/sync39/webnms/listmenus/linkmenu.xml",b);
	
		//XMLDataReader xm1 = new XMLDataReader("file:///d|/sync39/webnms/listmenus/linkmenu.xml",false);
		
		//xm.load("file:///D:/Sync39/WebNMS/listmenus/linkmenu.xml");
		
		//XMLDataReader xm2 = new XMLDataReader("file:///d|/sync39/webnms/listmenus/linkmenu.xml"); 
	*/

			
			
			
		}catch (Exception e)
		{
			e.printStackTrace();
		}

	}
}
