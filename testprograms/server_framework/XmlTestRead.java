/* $Id: XmlTestRead.java,v 1.1.1.1 2001/01/10 15:20:13 nms Exp $ */




import java.io.*;

import java.util.*;

import java.net.*;



//from xml.jar

import org.w3c.dom.*;

import org.xml.sax.*;

import com.sun.xml.tree.*;



// AdventNet Parser

/**

 * This class uses Sun's JAVA Project X xml-parser 

 */

public class XmlTestRead 

{


	public XmlTestRead(String fileNameAbsPath)

	{

		InputStream is = null;
		is = getInputStream(fileNameAbsPath);
		readXmlFile(is);
		try
		{
			is.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		
	}
	
	public InputStream getInputStream(String fileNameAbsPath)
	{
		InputStream ias = null;
		try 

		{
			if ( fileNameAbsPath.startsWith("http://"))
			{		

				URL xmlfile=new URL("http",null,fileNameAbsPath);
				ias=xmlfile.openStream();								
			}
			else
			{				
				ias = new FileInputStream(fileNameAbsPath);								
			}

		}
		catch (Exception ex) 
		{	
			System.out.println(ex);			
			return null;
		}	
		return ias;
		
	}
	

	private void readXmlFile(InputStream in)

	{





		XmlDocument	doc;





		try {						



			XmlDocumentBuilder	builder;

			Parser parser;




			builder = new XmlDocumentBuilder ();

			parser = new com.sun.xml.parser.Parser ();

			parser.setDocumentHandler (builder);

			builder.setParser (parser);

			builder.setDisableNamespaces (false);

			parser.parse (new InputSource(in));

			doc = builder.getDocument ();


		} catch (SAXParseException err) {

			System.out.println ("** Parsing error" 

					+ ", line " + err.getLineNumber ()

					+ ", uri " + err.getSystemId ());

			System.out.println("   " + err.getMessage ());



			Exception	x = err;

			if (err.getException () != null)

				x = err.getException ();

			x.printStackTrace ();





		} catch (SAXException e) {

			Exception	x = e;

			if (e.getException () != null)

				x = e.getException ();

			x.printStackTrace ();



		} catch (Throwable t) {

			t.printStackTrace ();

		}







	}



	public static void main(String args[])
	{

		if(args.length == 0)

		{

			System.out.println("Usage: XmlTestRead file");

			System.exit(1);

		}


		XmlTestRead xmlp = new XmlTestRead(args[0]);

	}
	
}

