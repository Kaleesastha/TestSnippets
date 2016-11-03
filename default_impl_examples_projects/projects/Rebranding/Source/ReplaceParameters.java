//$Id: ReplaceParameters.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $
package com.adventnet.studio.tools.rebranding;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Element;
import java.util.Hashtable;
import java.util.Enumeration;
import com.adventnet.nms.tools.utils.XmlUtil;
public class ReplaceParameters
{
	private Element root=null;
	Hashtable paramVsValue=null;	
	public ReplaceParameters(String xmlFile,Hashtable paramVsValue){
		Document doc=XmlUtil.parseAndCreateDocument(xmlFile);
		if(doc!=null){
			root=doc.getDocumentElement();
		}
		this.paramVsValue=paramVsValue;
	}
	public ReplaceParameters(Document doc,Hashtable paramVsValue){
		if(doc!=null){
			root=doc.getDocumentElement();
		}
		this.paramVsValue=paramVsValue;
	}
	public boolean replace(){
		if(root==null){
			System.out.println("Unable to replace Parameters the root Element is null");
			return false;
		}

		parseXML(root);
		return true;	
	}

	//Recursively parse the Node and change the $VARIABLE present in
	// the value
	private void parseXML(Node root){
		
		if(root.getNodeType()==Node.ELEMENT_NODE){
			NamedNodeMap nodeMap=root.getAttributes();
			int nodes=nodeMap.getLength();
			for(int i=0;i<nodes;i++){
				Node attribute=nodeMap.item(i);
				String value=attribute.getNodeValue();
				if(value.indexOf("$")!=-1){
					attribute.setNodeValue(updateValue(value));	
				}
			}
			NodeList childs=root.getChildNodes();
			int childSize=childs.getLength();
			for(int j=0;j<childSize;j++){
				parseXML(childs.item(j));
			}
		}
		// There are few text nodes in WebNMS.jnlp
		else if(root.getNodeType()==Node.TEXT_NODE){
			String value=root.getNodeValue();
			if(value.indexOf("$")!=-1){
				root.setNodeValue(updateValue(value));	
			}
		}
	}
	//Replace the $VARIABLE with appropriate value.
	private String updateValue(String value){
		for(Enumeration e=paramVsValue.keys();e.hasMoreElements();){
			String parameter=e.nextElement().toString();
			int index=value.indexOf(parameter);
			if(index!=-1){
				value=value.substring(0,index)+paramVsValue.get(parameter).toString()
					+value.substring(index+parameter.length());
			}
		}
		return value;
	}

	public static void main(String args[]){
		Hashtable table=new Hashtable();

		table.put("$LANGUAGE","tamil");
		table.put("$COUNTRY","India");
		table.put("$PROJECTHOME","./projects");
		table.put("$PRODUCT","Web NMS");
		table.put("$NEWPRODUCT","ABC EMS");
		table.put("$COMPANY","AdventNet");
		table.put("$NEWCOMPANY","XYZ");
		table.put("$PRODUCT1","WebNMS");
		table.put("$URL","www.adventnet.com");
		table.put("$NEWURL","www.yahoo.com");
		table.put("$SPLASH","./images/splash.jpg");
		table.put("$NEWSPLASH","./images/NewSplash.jpg");
		table.put("$VERSION","4");
		table.put("$NEWVERSION","1.0");
		table.put("$COPYRIGHT","i1999-10001");
		table.put("$NEWCOPYRIGHT","New i1999-10001");
		table.put("$MOTTO","Excellance Matters");
		table.put("$NEWMOTTO","New Motto");
		table.put("$NEWLOGO","./images/New Logo");
		table.put("$NEWFRAMEICON","./images/New Frame Icon");

		ReplaceParameters replace=new ReplaceParameters(args[0],table);
		System.out.println("The parameter replace result"+replace.replace());
	}
}

