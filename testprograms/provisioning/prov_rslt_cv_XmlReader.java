//$Id: AF_14_XmlReader.java, v 1.3 2005/12/22 17:58:38 sasidhar Exp
package test;

import java.io.*;
import org.xml.sax.*;
import org.w3c.dom.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import java.util.*;

public class prov_rslt_cv_XmlReader
{	
	public static String inputfilepath=null;
	public prov_rslt_cv_XmlReader(String filepath) 
	{
	  inputfilepath=filepath;		
	}
	public static Element docBuild()
	{	Element root1=null;
		InputStream inpStream=null;
		try
		{
			inpStream = new FileInputStream(inputfilepath);
			InputSource source = new InputSource(inpStream);

			DocumentBuilderFactory fac = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = fac.newDocumentBuilder();
			Document doc = docBuilder.parse(source);
			DocumentType dt = doc.getDoctype();
			root1 = doc.getDocumentElement();
			
		}
		catch(Exception ex)
		{

		}
		finally
		{
			try
			{
			if(inpStream != null) inpStream.close();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			
			}

		}
	return root1;	

	}
	 
	public Properties getprops(String name,String elname)
	{
		Element rt=this.docBuild();
		Properties prop=new Properties();
		
		NodeList nlist = rt.getElementsByTagName(name);//gets the elemnt with tag of test case number
		Element	n1=(Element)nlist.item(0);
		Node n2=null;
		n2=(n1.getElementsByTagName(elname)).item(0);
		if(n2==null)
		{
		return null;
		}
		NamedNodeMap nnm=n2.getAttributes();
		for(int i=0;i<nnm.getLength() ;i++)
		{
			prop.setProperty( ((Attr)(nnm.item(i))).getName(),((Attr)(nnm.item(i))).getValue() );
		}
		return prop;

	}
	
	
}
