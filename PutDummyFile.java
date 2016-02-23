//$Id: PutDummyFile.java,v 1.2.2.1 2005/07/29 10:17:23 venkatramanan Exp $

package com.adventnet.nms.tools.confchanges;

import java.util.Vector;
import java.io.*;

import org.w3c.dom.*;
import javax.xml.parsers.*;

public class PutDummyFile
{
		Vector locationDetails = null;
		
		private Document getHelpConfDocument(String nmsHome) throws Exception
		{
			String helpConfFile =	nmsHome + File.separator + "conf" + File.separator + "help.conf";
			File helpFile = new File(helpConfFile);
			if(!helpFile.exists())
			{
					return null;
			}
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc =db.parse(helpConfFile);
			return doc;
		}

		private String getHtmlFileName(String value)
		{
				int index = value.trim().indexOf(".html");
				String fName = value.trim().substring(0,index + 5);
				System.out.println("The name of the file is " + fName);
				fName = fName.replace('\\',File.separatorChar);
				fName = fName.replace('/',File.separatorChar);
				return fName;
		}

		private void copyFile(File sourceFile,File destFile) throws IOException
		{
				BufferedInputStream bis = new BufferedInputStream(new FileInputStream(sourceFile));
				int length = bis.available();
				byte arr[] = new byte[length];
				bis.read(arr,0,length);
				bis.close();
				File parent = new File(destFile.getParent());
				if(!parent.exists())
				{
						parent.mkdirs();
				}
				FileOutputStream fos = new FileOutputStream(destFile);
				fos.write(arr);
				fos.flush();
				fos.close();
		}

		public void getHelpUrl(String nmsHome) throws Exception
		{
				Document doc = getHelpConfDocument(nmsHome);
				if(doc == null)
				{
					return;
				}
				Element rootElement = doc.getDocumentElement();
				NodeList list = rootElement.getChildNodes();
				for(int i=0;i<list.getLength();i++)
				{
						Node helpListNode = list.item(i);
						NodeList keyNodes = helpListNode.getChildNodes();
						for(int j=0;j<keyNodes.getLength();j++)
						{
								Node helpNode = keyNodes.item(j);
								if(helpNode.getNodeType() != Node.ELEMENT_NODE)
								{
										continue;
								}
								Element helpElement = (Element)helpNode;
								String value = helpElement.getAttribute("value");
								String htmlFileName = nmsHome + File.separator + getHtmlFileName(value);
								File htmlFile = new File(htmlFileName);
								if(htmlFile.exists())
								{
										continue;
								}
								File dummyFile = new File(nmsHome + File.separator + "help" + File.separator +"quick_tour" + File.separator + "default_contexthelp.html");
	//Checking whether default_contexthelp.html is present in the required place thereby avoiding FileNotFoundException
								if(dummyFile.exists())
								{
								copyFile(dummyFile,htmlFile);
								}
						}
				}
		}

		

		public static void main(String args[])
		{
				PutDummyFile pdf = new PutDummyFile();
				try
				{
					pdf.getHelpUrl(args[0]);
				}
				catch(Exception e)
				{
						e.printStackTrace();
				}
		}
		
}

