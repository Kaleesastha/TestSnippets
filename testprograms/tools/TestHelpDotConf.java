import org.w3c.dom.*;
import javax.xml.parsers.*;
import org.xml.sax.*;


import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;

import java.io.*;



public class TestHelpDotConf
{
	void test(String nmshome)
	{
		try
		{
			String hc = nmshome + File.separator + "conf" + File.separator 
				+ "help.conf";
			DocumentBuilderFactory docBuilderFactory =
				DocumentBuilderFactory.newInstance();
			docBuilderFactory.setIgnoringElementContentWhitespace(true);
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(hc);

			Element root = doc.getDocumentElement();
			//System.out.println(root);
			NodeList nl = root.getElementsByTagName("HELP");
			for(int i = 0 , j = nl.getLength() ; i < j ; i++)
			{
				Element n = (Element)nl.item(i);
				//System.out.println("Attribute value " + n.getAttribute("value"));
				String v = n.getAttribute("value");
				if(v.equals(""))
					{
						System.out.println("Necessary dummy files are not created as per the entries in the help.conf file");	
						System.exit(1);
					}
					if(v.indexOf('#') != -1)
					{
						v = v.substring(0,v.lastIndexOf('#'));
					}
				String helpFile = nmshome + File.separator + v;
				if(!(new File(helpFile)).exists())
				{
						System.out.println("Necessary dummy files are not created as per the entries in the help.conf file");	
						System.exit(1);

				}
			}
			System.out.println("Dummy files are created as per the entries in the help.conf");	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String args[])
	{
		TestHelpDotConf h = new TestHelpDotConf();
		if(args.length == 0)
		{
			System.out.println("Usage :: java TestHelpDotConf <WEBNMSHOME>");
			System.exit(1);
		}
		h.test(args[0]);
	}
}


