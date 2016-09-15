/* $Id: testXmlChecker.java,v 1.1.1.1 2001/01/10 15:20:13 nms Exp $ */

import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;
import java.util.*;
import java.io.*;
import java.net.*;

import org.w3c.dom.*;
import org.xml.sax.*;
import com.sun.xml.tree.*;



public class testXmlChecker
{


	public testXmlChecker()
	{

	}

	public void readXmlMenuFile(String absFileName) 
	{

		FileInputStream is=null;
		try
		{
			File f=new File(absFileName);
			is=new FileInputStream(f);

		}
		catch(Exception e)
		{
			System.out.println("Exception caught : "+e);
		}

		XmlDocument xmlData = new XmlDocument();
		
		try 
		{						

				XmlDocumentBuilder	builder;
				Parser parser;
				builder = new XmlDocumentBuilder ();
				parser = new com.sun.xml.parser.Parser ();
				parser.setDocumentHandler (builder);
				builder.setParser (parser);
				builder.setDisableNamespaces (false);
				parser.parse (new InputSource(is));
				xmlData = builder.getDocument ();
			}
		 catch (SAXParseException err) 
			 {
             	System.out.println ("** Parsing error" 
					+ ", line " + err.getLineNumber ()
					+ ", uri " + err.getSystemId ());
				System.out.println("   " + err.getMessage ());
				Exception	x = err;
			
				if (err.getException () != null)
				{
					x = err.getException ();
				}

				x.printStackTrace ();

			} 
			catch (SAXException e) 
			{
				Exception	x = e;
				if (e.getException () != null)
				{
					x = e.getException ();
				}
				x.printStackTrace ();

			} 
			catch (Throwable t) 
			{
				t.printStackTrace ();
			}
	

		//rootNode = getXmlTreeNode(xmlData.getDocumentElement(), null);  
		
	}

/*
	private XmlTreeNode getXmlTreeNode(Node node, XmlTreeNode pNode) 
	{
		XmlTreeNode xmlNode = new XmlTreeNode();	  

		xmlNode.setParent(pNode);

		Vector attribute_vec=null;
		Vector childNodes=null;
		// attribute list 
		//xmlNode.setAttributeList(getAttributeList(idomNode));

		if(node.getNodeType()==Node.ELEMENT_NODE)
		{
			attribute_vec = getAttributeList(node);
		
			String node_name = node.getNodeName();
			XmlDetails m = new XmlDetails(node_name,attribute_vec);
			xmlNode.setUserObject(m);


			// child node list 
			//xmlNode.setChildNodes(getChildNodesV(idomNode,xmlNode));
			 childNodes = getChildNodesV(node,xmlNode);
		
			for(int i = 0;i < childNodes.size();i++)
			{			
				((XmlTreeNode)(childNodes.elementAt(i))).setParent(null);
				//xmlNode.removeAllChildren();
				xmlNode.add((XmlTreeNode)childNodes.elementAt(i));		  
			}
		
		}
		return xmlNode;

	}
*/


	public static void main(String args[])
	{



		if(args.length ==  0 )
		{
			System.out.println("Error: invalid number of arguments");
			System.exit(1);
		}

		testXmlChecker xmle = new testXmlChecker();
		for(int i = 0; i < args.length; i++)
		{
			xmle.readXmlMenuFile(args[i]);
		}
	}
}
