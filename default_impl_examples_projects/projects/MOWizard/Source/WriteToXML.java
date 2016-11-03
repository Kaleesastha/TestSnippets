//$Id: WriteToXML.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $
package com.adventnet.nms.tools.mowizard;

import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import javax.swing.JOptionPane;
import java.io.File;

public class WriteToXML
{
	String FS=File.separator;
	String PS=System.getProperty("path.separator");
	public WriteToXML()
	{}
	
	public void populate()
	{
		//populate all the default objects of the nms in a file MOObjects.xml
		String[] moobjects={"com.adventnet.nms.topodb.ManagedObject","com.adventnet.nms.topodb.SnmpNode","com.adventnet.nms.topodb.Node","com.adventnet.nms.topodb.IpAddress","com.adventnet.nms.topodb.TopoObject","com.adventnet.nms.topodb.Network","com.adventnet.nms.topodb.SnmpInterface"};
		File f=new File("."+FS+"projects"+FS+"MOObjects.xml");
		try{
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			DocumentBuilder db=dbf.newDocumentBuilder();
			Document doc=db.newDocument();
			Element node=doc.createElement("MO_OBJECTS");
			doc.appendChild(node);
			for(int i=0;i<moobjects.length;i++){
				Element child=doc.createElement("CLASS");
				child.setAttribute("name",moobjects[i]);
				child.setAttribute("classpath",PS+"."+FS+"classes");
				doc.getDocumentElement().appendChild(child);
			}
			updateXML(doc,f);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public void updateXML(Document doc,File f)
	{
		try{
			TransformerFactory tfff=TransformerFactory.newInstance();
			Transformer tff=tfff.newTransformer();
			DOMSource doms=new DOMSource(doc);
			StreamResult stream=new StreamResult(f);
			tff.transform(doms,stream);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
