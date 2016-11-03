//$Id: UninstallManagedObject.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $

package com.adventnet.nms.tools.mowizard;

import org.w3c.dom.*;
import javax.swing.JOptionPane;
import javax.xml.parsers.*;
import java.io.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;
import java.util.*;
import com.adventnet.apiutils.BuilderResourceBundle;
import com.adventnet.nms.tools.utils.ToolsUtil;

public class UninstallManagedObject 
{
	String nodename=null;
	String xmlpath=null;
	String curDir=".";
	String FS=File.separator;
	String PS=System.getProperty("path.separator");
	DocumentBuilderFactory dbf=null;
	DocumentBuilder db=null;
	Document doc=null;
	boolean status=false;
	Vector depArchives=new Vector();
	private String lineSep=System.getProperty("line.separator");
	private BuilderResourceBundle resourceBundle=ToolsUtil.getBundle();
	public UninstallManagedObject()
	{
	}

	public boolean unInstall(String delobject)
	{
		nodename=delobject;
		File f=null;
		xmlpath="."+FS+"NetMonitor"+FS+"build"+FS+"uninstall.xml";
		try{
			dbf=DocumentBuilderFactory.newInstance();
			db=dbf.newDocumentBuilder();
			f=new File(xmlpath);
			if(!f.exists()){
				JOptionPane.showMessageDialog(null,java.text.MessageFormat.format(resourceBundle.getString("The file {0} does not exist"),new String[]{f.getName()}),resourceBundle.getString("Error"),JOptionPane.OK_OPTION);
				return false;
			}
			doc=db.parse(f);
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return UninstallMO(f);
	}

	public boolean UninstallMO(File f)
	{
		//Determines which node to be deleted
		Element delNode=findWhichNode(doc);
		if(delNode == null){
			JOptionPane.showMessageDialog(null,java.text.MessageFormat.format(resourceBundle.getString("The required node to be deleted does not exist in {0}"),new String[]{f.getName()}),resourceBundle.getString("Error"),JOptionPane.OK_OPTION);
			return false;
		}
		populateVector();
		if(!startUninstall(delNode,f)){
			return false;
		}
		//doc.getDocumentElement().removeChild(inst_props);
		return updateXML(doc,f);
	}

	public Element findWhichNode(Document doc)
    {
		//returns the node to be uninstalled
		NodeList tags=doc.getElementsByTagName("INSTALLED_NARS");
		for(int i=0;i<tags.getLength();i++)
		{
			Element reqnode=(Element)tags.item(i);
			if(reqnode.getAttribute("NAME").equals(nodename) && reqnode.getAttribute("TYPE").equals("MANAGED-OBJECT")){
				return reqnode;
			}
		}
		return null;
	}

	public boolean startUninstall(Element delNode,File f)
	{
		String[] archives=null;
		Element propNode=(Element)delNode.getElementsByTagName("PROPERTIES").item(0);
		File delFile=null;
		String archive=propNode.getAttribute("ARCHIVE");
        String isdeviceModelled=propNode.getAttribute("DEVICE_MODELED");
		if(archive.equals("")){
			JOptionPane.showMessageDialog(null,java.text.MessageFormat.format(resourceBundle.getString("The archive {0} does not exist"),new String[]{archive}),resourceBundle.getString("Error"),JOptionPane.OK_OPTION);
			return false;
		}
		if(depArchives!=null){
			archives=parseDepArchives(propNode,archive);
			for(int i=0;i<archives.length;i++)
			{
				deleteFile(archives[i]);
			}
		}
		String confStat=propNode.getAttribute("UPDATED-CONF");
		if(confStat.equals("YES")){
				
			if(!revertConfFiles(nodename)){
				JOptionPane.showMessageDialog(null,resourceBundle.getString("Error occured while reverting conf files"),resourceBundle.getString("Error"),JOptionPane.OK_OPTION);
				return false;
			}
		}
		Element mapelement=(Element)propNode.getElementsByTagName("MAPICON").item(0);
		if(mapelement!=null && !mapelement.getAttribute("TYPE").equals("")){
			if(!revertMapIcon(mapelement)){
				return false;
			}
		}
		Element listelement=(Element)propNode.getElementsByTagName("LISTICON").item(0);
		if(listelement!=null && !listelement.getAttribute("TYPE").equals("")){
			if(!revertListIcon(listelement)){
				return false;
			}
		}
		Element oidtype=(Element)propNode.getElementsByTagName("DEVICE_PARAMS").item(0);
		if(oidtype!=null && !oidtype.getAttribute("OID").equals("")){
			if(!revertOIDType(oidtype)){
				return false;
			}
		}
                if(isdeviceModelled!=null&&isdeviceModelled.equals("NO"))
                {
                    Element filter=(Element)propNode.getElementsByTagName("DISCOVERY_FILTER").item(0);
    
                    if(filter!=null)
                    {
                        if(!revertDiscoveryFilter(filter.getAttribute("className")))
                        {
                            return false;
                        }
                    }
                }
                    
		if(!revertLauncherConf(archives)){
			return false;
		}
		if(!revertStartNMSAPP(archives,"startnms")){
			return false;
		}
		/*if(!revertStartNMSAPP(archives,"startApplicationClient")){
			return false;
		}*/
		doc.getDocumentElement().removeChild(delNode);
		return true;
	}

	public void deleteFile(String file)
	{
		//deletes the file from the resp. location
		if(file!=null){
			File delFile=new File(curDir+FS+"NetMonitor"+FS+"build"+FS+file);
			if(delFile.exists()){
				delFile.delete();
			}
		}
	}
    public boolean revertDiscoveryFilter(String discoveryClass)
    {
        File f=new File("."+File.separator+"conf"+File.separator+"discovery.filters");
        if(f.exists())
        {
            try
            {
                DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
                DocumentBuilder db=dbf.newDocumentBuilder();
                Document doc=db.parse(f);
                NodeList nodes=doc.getElementsByTagName("FILTER");
                for(int i=0;i<nodes.getLength();i++)
                {
                    Element element=(Element)nodes.item(i);
                    if(element.getAttribute("className").equals(discoveryClass))
                    {
                        doc.getDocumentElement().removeChild(element);
                        return updateXML(doc,f);
                    }
                }
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null,resourceBundle.getString("Uninstallation failed for discovery.filters"),resourceBundle.getString("Error"),JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
        }
        return false;
    }
	public boolean revertConfFiles(String objname)
	{
		try{
			if(objname != null){
                updateDatabaseSchema(objname);
				updateDatabaseAliases(objname);
				updaterelationalclasses(objname);
			}
		}
		catch(Exception e){
			return false;
		}
		return true;
	}

	public void updateDatabaseSchema(String objname) throws Exception
	{
			//Updating DatabaseSchema.conf
			BufferedReader bf = new BufferedReader(new FileReader(curDir+FS+"conf"+FS+"DatabaseSchema.conf"));
			String str = new String();
			String newfile = new String();
			String strtemp = new String();
			while( (str = bf.readLine())!=null) {
				if( str.startsWith("BeginCreateSchema")) {
					strtemp = bf.readLine();
					/* If the conf file has selected Object Name as
					   Index then leave all subsequent lines until 
					   another BeginCreateSchema / EndModules are 
					   reached */
					if(strtemp.indexOf(" "+objname+" ") != -1) {
						while( (str = bf.readLine()) != null) { 
							if(str.startsWith("BeginCreateSchema") || str.startsWith("EndModules")) {
								newfile = newfile + str + lineSep;
								break;
							}
						}
					}
					else {
						newfile = newfile + str + lineSep + strtemp+ lineSep;
					}	
				}
				else{
					newfile = newfile + str + lineSep;
				}
			}	
			bf.close();

			FileOutputStream fout = new FileOutputStream(curDir+FS+"conf"+FS+"DatabaseSchema.conf");
			fout.write(newfile.getBytes());
			fout.close();
	}

	public void updateDatabaseAliases(String objname) throws Exception
	{
			BufferedReader bf = new BufferedReader(new FileReader(curDir+FS+"conf"+FS+"DatabaseAliases.conf"));
			String newfile = "";
			String strtemp = "";
			String str="";
			while( (str = bf.readLine()) != null) {
				if(str.startsWith("#")) {
					str=str.trim();
					if(str.endsWith(objname)) {
						while( (str = bf.readLine())!=null) {
							if(str.indexOf("#")!=-1) {
								newfile = newfile+str+lineSep;
								break;
							}
						}	
					}
					else {
						newfile = newfile+str+lineSep;
					}	
				}
				else {
					newfile = newfile+str+lineSep;
				}
			}
			bf.close();
			FileOutputStream fout = new FileOutputStream(curDir+FS+"conf"+FS+"DatabaseAliases.conf");
			fout.write(newfile.getBytes());
			fout.close();
	}

	public void updaterelationalclasses(String objname) throws Exception
	{
			String newfile = "";
			String str="";
			BufferedReader bf = new BufferedReader(new FileReader(curDir+FS+"conf"+FS+"relationalclasses.conf"));
			while( (str = bf.readLine())!=null) {
				str=str.trim();
				if(!str.endsWith(objname)){
					newfile = newfile+str+lineSep;
				}
			}		
			bf.close();
			FileOutputStream fout = new FileOutputStream(curDir+FS+"conf"+FS+"relationalclasses.conf");
			fout.write(newfile.getBytes());
			fout.close();
	}

	public boolean revertMapIcon(Element mapicon)
	{
		File f=new File("."+FS+"conf"+FS+"mapIcon.data");
		Document doc1=null;
		if(!f.exists()){
			JOptionPane.showMessageDialog(null,java.text.MessageFormat.format(resourceBundle.getString("The file {0} does not exist"),new String[]{f.toString()}),resourceBundle.getString("Error"),JOptionPane.OK_OPTION);
			return false;
		}
		try{
			doc1=db.parse(f);
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null,java.text.MessageFormat.format(resourceBundle.getString("Error while parsing {0}"),new String[]{f.toString()}),resourceBundle.getString("Error"),JOptionPane.OK_OPTION);
			return false;
		}
		NodeList maplist=doc1.getElementsByTagName("DATA");
		for(int i=0;i<maplist.getLength();i++)
		{
			Element nodedata=(Element)maplist.item(i);
			if(nodedata.getAttribute("TYPE").equals(mapicon.getAttribute("TYPE"))){
				//search for modify status
				if(mapicon.hasAttribute("NEWNODE")) {
					doc1.getDocumentElement().removeChild(nodedata);
					return updateXML(doc1,f);
				}
				if(mapicon.getAttribute("MODIFY").equals("NO")){
					// if old Data is present set is and write into file
					if(mapicon.hasAttribute("OLD_MENU")) {
						// Revert back to previous values
						nodedata.setAttribute("menuName",mapicon.getAttribute("OLD_MENU"));
						nodedata.setAttribute("iconName",mapicon.getAttribute("OLD_IMAGE"));
						if(mapicon.hasAttribute("OLD_MAP_FILTER")) {
							nodedata.setAttribute("MAP_FILTER",mapicon.getAttribute("OLD_MAP_FILTER"));
						}
					}
					else {
						// remove Node contents
						doc1.getDocumentElement().removeChild(nodedata);
					}
				}
				return updateXML(doc1,f);
			}
		}
		return true;
	}

	public boolean revertListIcon(Element listelement)
	{
		File f=new File("."+FS+"conf"+FS+"listIcon.data");
		Document doc1=null;
		if(!f.exists()){
			JOptionPane.showMessageDialog(null,java.text.MessageFormat.format(resourceBundle.getString("The file {0} does not exist"),new String[]{f.toString()}),resourceBundle.getString("Error"),JOptionPane.OK_OPTION);
			return false;
		}
		try{
			doc1=db.parse(f);
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null,java.text.MessageFormat.format(resourceBundle.getString("Error while parsing {0}"),new String[]{f.toString()}),resourceBundle.getString("Error"),JOptionPane.OK_OPTION);
			return false;
		}
		NodeList icontags=doc1.getElementsByTagName("DATA");
		Element newnode=null;
		for(int i=0;i<icontags.getLength();i++){
			Element listnode=(Element)icontags.item(i);
			if(listnode.getAttribute("TYPE").equals(listelement.getAttribute("TYPE"))){
				// Here we proceed with the assumption that while uninstallation the type will be 
				// present in the file.  
				if(listelement.hasAttribute("NEWNODE")) {
					doc1.getDocumentElement().removeChild(listnode);
					return updateXML(doc1,f);
				}
				NamedNodeMap rnnm=listnode.getAttributes();
				int length=rnnm.getLength();
				for(int j=length-1;j>=0;j--) {
					Attr ratt=(Attr)rnnm.item(j);
					listnode.removeAttribute(ratt.getName());
				}
				listnode.setAttribute("TYPE",listelement.getAttribute("TYPE"));
				listnode.setAttribute("MENU",listelement.getAttribute("OLD_MENU"));
				if(listelement.hasAttribute("OLD_MULTILEVEL_SEVERITY") || (listelement.hasAttribute("TRANSPARENT") && listelement.getAttribute("TRANSPARENT").equals("NO"))) {
					NodeList nodeList=listelement.getElementsByTagName("SEVERITY");

					NamedNodeMap nnm=listelement.getAttributes();
					length=nnm.getLength()-1;
					for(int j=length;j>=0;j--) {
						Attr att=(Attr)nnm.item(j);
						if(att.getName().startsWith("OLD") && att.getName().endsWith("IMG")) {
							// Here 4 is used because it will be like OLD_SEVERITY_IMG
							listnode.setAttribute(att.getName().substring(4),att.getValue());
						}
					}
				}
				else {
					// old one is a transparent image so replace it with TRANSPARENTIMAGE 
					listnode.setAttribute("DEFAULT_TRANSPARENT_IMG",listelement.getAttribute("OLD_DEFAULT_TRANSPARENT_IMG"));
				}
				return updateXML(doc1,f);
			}
		}
		return updateXML(doc1,f);
	}	

	public boolean revertOIDType(Element oidtype)
	{
		//This method reverts the Type of the device for the particular OID...If the OID entry is new one it is simply removed ...if its a modified one....then it is updated....
		File f=new File("."+FS+"conf"+FS+"OIDType.data");
		Document doc1=null;
		if(!f.exists()){
			JOptionPane.showMessageDialog(null,java.text.MessageFormat.format(resourceBundle.getString("The file {0} does not exist"),new String[]{f.toString()}),resourceBundle.getString("Error"),JOptionPane.OK_OPTION);
			return false;
		}
		try{
			doc1=db.parse(f);
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null,java.text.MessageFormat.format(resourceBundle.getString("Error while parsing {0}"),new String[]{f.toString()}),resourceBundle.getString("Error"),JOptionPane.OK_OPTION);
			return false;
		}
		NodeList oidtags=doc1.getElementsByTagName("DATA");
		for(int i=0;i<oidtags.getLength();i++){
			Element oidelement=(Element)oidtags.item(i);
			if(oidelement.getAttribute("OID").equals(oidtype.getAttribute("OID"))){
				NamedNodeMap nnm=oidelement.getAttributes();
				for(int j=nnm.getLength()-1;j>=0;j--) {
					Attr att=(Attr)nnm.item(j);
					oidelement.removeAttribute(att.getName());
				}
				nnm=oidtype.getAttributes();
				for(int j=0;j<nnm.getLength();j++) {
					Attr att=(Attr)nnm.item(j);
					if(att.getName().startsWith("OLD")) {
						oidelement.setAttribute(att.getName().substring(4),att.getValue());
					}
				}
				return updateXML(doc1,f);
			}
		}
		return true;
	}

	public void populateVector()
	{
		//Populate all the dependent archives in a vector except the one to be uninstalled
		StringTokenizer stk=null;
		String token=null;
		NodeList tags=doc.getElementsByTagName("INSTALLED_NARS");
		for(int i=0;i<tags.getLength();i++){
			Element nodes=(Element)tags.item(i);
			if(!nodes.getAttribute("NAME").equals(nodename)){
				Element props=(Element)nodes.getElementsByTagName("PROPERTIES").item(0);
				String archive=props.getAttribute("DEPENDENT-ARCHIVES");
				if(props!=null && archive!=null){
					stk=new StringTokenizer(archive,",");
					while(stk.hasMoreTokens()){
						token=stk.nextToken();
						if(!depArchives.contains(token)){
							depArchives.add(token);
						}
					}
				}
			}
		}
	}

	public String[] parseDepArchives(Element propNode,String archive)
	{
		//returns an array of dependent-archives to be deleted....
		String[] archiveList=null;
		int i=0;
		String list=propNode.getAttribute("DEPENDENT-ARCHIVES");
		String strtoken=null;
		StringTokenizer stk=new StringTokenizer(list,",");
		archiveList=new String[stk.countTokens()+1];
		archiveList[i++]=archive;
		while(stk.hasMoreTokens()){
			strtoken=stk.nextToken();
			if(!depArchives.contains(strtoken)){
				archiveList[i++]=strtoken;
			}
		}
		return archiveList;
	}

	public boolean revertLauncherConf(String[] archives)
	{
		//parse the launcherconf file and using the archivelist update the classpath entry list
		File f=new File(curDir+FS+"conf"+FS+"launcher_conf.txt");
		if(!f.exists()){
			JOptionPane.showMessageDialog(null,java.text.MessageFormat.format(resourceBundle.getString("The file {0} does not exist"),new String[]{f.toString()}),resourceBundle.getString("Error"),JOptionPane.OK_OPTION);
			return false;
		}
		Document doc1=null;
		try{
			doc1=db.parse(f);
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null,java.text.MessageFormat.format(resourceBundle.getString("Error while parsing {0}"),new String[]{f.toString()}),resourceBundle.getString("Error"),JOptionPane.OK_OPTION);
			return false;
		}
		NodeList classpathlist=doc1.getElementsByTagName("application");
		for(int i=0;i<classpathlist.getLength();i++){
			Element classnode=(Element)classpathlist.item(i);
			NodeList classlist=classnode.getElementsByTagName("property");
			for(int j=0;j<classlist.getLength();j++){
				Element reqelement=(Element)classlist.item(j);
				if(reqelement.getAttribute("value").trim().equals("Start Web NMS Server")){
					revertClasspath(archives,classlist,doc1,f);
				}
			}
		}
		return updateXML(doc1,f);
	}

	public void revertClasspath(String[] archives,NodeList classlist,Document doc1,File f)
	{
		String classpath=null;
		String delpath="";
		Element reqnode=null;
		for(int k=0;k<classlist.getLength();k++){
			reqnode=(Element)classlist.item(k);
			if(reqnode.getAttribute("name").equals("Classpath")){
				classpath=reqnode.getAttribute("value");
				break;
			}
		}
		for(int i=0;i<archives.length;i++){
			delpath=";./NetMonitor/build/"+archives[i];
			if(classpath.indexOf(delpath)!=-1){
				classpath=classpath.substring(0,classpath.indexOf(delpath))+classpath.substring(classpath.indexOf(delpath)+delpath.length(),classpath.length());
			}
		}
		reqnode.setAttribute("value",classpath);
	}

	public boolean revertStartNMSAPP(String[] archives,String argstr)
	{
		//A bit confusing function this is....Here the occurence of MOPATH= is searched stored in a variable str...and the string after MOPATH= is parsed,compared with the archives string array and finally updated in the file...
		String nmsstr="."+FS+"bin"+FS+argstr;
		String templine=null;
		String str=null;
		String line="";
		String tempstr="."+FS+"NetMonitor"+FS+"build"+FS;
		File f=null;
		if(System.getProperty("os.name").toLowerCase().startsWith("win")){
			f=new File(nmsstr+".bat");
		}
		else{
			f=new File(nmsstr+".sh");
		}
		try{
			BufferedReader bufread=new BufferedReader(new FileReader(f));
			StringBuffer strbuf=new StringBuffer();
			while((line=bufread.readLine())!=null){
				if(line.indexOf("MOPATH=")!=-1){
					str=line.substring(0,line.indexOf("MOPATH=")+7);
					for(int i=0;i<archives.length;i++)
					{
						if(line.substring(line.indexOf("MOPATH=")+7).indexOf(tempstr+archives[i])!=-1){
							int index=line.indexOf(tempstr+archives[i]);
							int length=(tempstr+archives[i]).length();
							if(line.substring(index-1).startsWith(PS)){
								index=index-1;
								length=length+1;
							}
							templine=line.substring((line.indexOf("MOPATH=")+7),index)+line.substring(index+length,line.length());
							line=str+templine;
						}
					}
					strbuf.append(line);
					strbuf.append(lineSep);
				}
				else{
					strbuf.append(line);
					strbuf.append(lineSep);
				}
			}
			FileOutputStream fout=new FileOutputStream(f);
			fout.write(strbuf.toString().getBytes());
			fout.close();
		}
		catch(Exception e){
			return false;
		}
		return true;
	}

	public boolean updateXML(Document doc,File f)
	{
		//All the XML documents updations into the file are carried out by this method
		try{
			TransformerFactory tfff=TransformerFactory.newInstance();
			Transformer tff=tfff.newTransformer();
			DOMSource doms=new DOMSource(doc);
			StreamResult stream=new StreamResult(f);
			tff.transform(doms,stream);
			return true;
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null,java.text.MessageFormat.format(resourceBundle.getString("Error while writing into {0}"),new String[]{f.toString()}),resourceBundle.getString("Error"),JOptionPane.OK_OPTION);
			return false;
		}
	}

	public static void main(String[] ar)
	{
		UninstallManagedObject umo=new UninstallManagedObject();
		umo.unInstall("Example");
	}
}
