/**
 * $Id: PWSUninstallWizard.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $
 * PWSUninstallWizard.java
 *
 *
 * Created: Fri Apr 19 14:21:53 2002
 *
 * @author <a href="mailto:hari@HARI"></a>
 * @version
 */

package com.adventnet.studio.framework.installer;

import java.util.*;
import java.util.zip.*;
import java.io.*;
import java.lang.reflect.*;

import org.w3c.dom.*;

import com.adventnet.nms.tools.utils.XmlUtil;
import com.adventnet.nms.tools.nar.CommandLineNarUnInstaller;
public class PWSUninstallWizard {

    private String backupZipFile;

    private String uninstallFile;

    private Document uninstallDoc;

    private ZipFile backupZip; 

    private Element appUnEl;

    private int lengthOfTask;
    private int current = 1;
    private String statMessage;

    private String uninstallLocation;

    private CommandLineNarUnInstaller uninstallNar=null;//For uninstalling imported NARS in commandline
    private ArrayList narList=null;//Uninstalled NAR list

    public PWSUninstallWizard (String application,String uninstallLocationArg)
	throws IOException {
	backupZipFile = uninstallLocationArg +  File.separator 
	    + "NetMonitor" + File.separator + "build" + File.separator + "PwsBackup.zip";

	uninstallFile = uninstallLocationArg + File.separator 
	    + "NetMonitor" + File.separator + "build" + File.separator + "uninstall.xml";
	
	// parsing the uninstall xml file
	uninstallDoc = XmlUtil.parseAndCreateDocument(uninstallFile);	
	backupZip = new ZipFile(backupZipFile);

	// finding the studio nar uninstall information
	// if not found throwing exception
	Element unroot = uninstallDoc.getDocumentElement();

	NodeList nl = unroot.getElementsByTagName("INSTALLED-NARS");
	for(int i = 0 ; i < nl.getLength() ; i++) {
	    Node n = nl.item(i);
	    if(n.getNodeType() != n.ELEMENT_NODE) {
		continue;
	    }
	    Element el = (Element)n;
	    String app = el.getAttribute("NAME");
	    String type=el.getAttribute("TYPE");
	    if(application.equals(app)&&type.equals("PWSAPPLICATION")) {
		appUnEl = el;
		break;
	    }
	}

	if(appUnEl == null) {
	    throw new RuntimeException("Studio application by the name  " + application 
				+ " is not installed at the location " + uninstallLocationArg);
	}
	narList=new ArrayList();
	narList.add(application);
	uninstallLocation = uninstallLocationArg;
    }

    // Method which will uninstall the nar
    void uninstall()
	throws ClassNotFoundException,NoSuchMethodException,
	       InstantiationException,IllegalAccessException,
	       IOException,InvocationTargetException
    {
	NodeList nl = appUnEl.getElementsByTagName("NAR");

	// uninstalling any client nar inst allted and removing the 
	// entry from uninstall.xml
	if(nl != null && nl.getLength() != 0) {

	    // Do a for loop and uninstall all the client nars
	    for (int  j = 0, k = nl.getLength(); j < k ; j++) 
		{
		    Element nar = (Element)nl.item(j);
		    statMessage  = "Uninstalling client nar :: " + nar.getAttribute("name");

		    System.out.println(statMessage);
		    uninstallNar=new CommandLineNarUnInstaller(nar.getAttribute("name"));
		    if(uninstallNar.processUnInstallation()){
			narList.add(nar.getAttribute("name"));
		    }
		    

		    //Though this is done by CommandLineUninstaller
		    //We have to do this since we are maintaining our
		    //own uninstallDoc.
		    Element unroot = uninstallDoc.getDocumentElement();

		    NodeList inars = unroot.getElementsByTagName("INSTALLED-NARS");
		    for(int i = 0 ; i < inars.getLength() ; i++) {
			Node n = inars.item(i);
			if(n.getNodeType() != n.ELEMENT_NODE) {
			    continue;
			}
			Element el = (Element)n;
			String app = el.getAttribute("NAME");
			if(nar.getAttribute("name").equals(app)) {
			    uninstallDoc.getDocumentElement().removeChild(el);
			    break;
			}
		    }
		    current ++; // incremented as one client nar uninstall work is done.

		} // end of for (int  = 0;  < ; ++)
	}

	// Reads the file entries that were created by the nar
	// deletes the file from disk
	nl = appUnEl.getElementsByTagName("FILE");
	for(int i = (nl.getLength() -1) ; i >= 0  ; i--) {
	    Node n = nl.item(i);
	    if(n.getNodeType() != n.ELEMENT_NODE) {
		continue;
	    }
	    Element el = (Element)n;
	    String delFileName = el.getAttribute("name");
	    statMessage  = "Removing file :: " + delFileName;
	    System.out.println(statMessage);
	    delFileName  = uninstallLocation + File.separator + delFileName ;
	    File delFile = new File(delFileName);
	    delFile.delete();
	    
	    current ++;
	}

	// removing the uninstall information of nar from document
	uninstallDoc.getDocumentElement().removeChild(appUnEl);

	// restoring the files that were backed up using the backup
	// zip created during installation
	Enumeration entries = backupZip.entries();
	while(entries.hasMoreElements()) {
	    ZipEntry entry = (ZipEntry)entries.nextElement();
	    String entryName = entry.getName();

	    statMessage = "Restoring File " + entryName;
	    System.out.println(statMessage);

	    File entryFile = new File(uninstallLocation + File.separator + entryName);
	    if(entry.isDirectory()) {
		entryFile.mkdir();
		continue;
	    }

	    InputStream entryStream = backupZip.getInputStream(entry);
	    BufferedInputStream inbuff = new BufferedInputStream(entryStream);
	    BufferedOutputStream outbuff = new BufferedOutputStream(new FileOutputStream(uninstallLocation + File.separator + entryName));

	    int c;
	    while((c = inbuff.read()) != -1) {
		outbuff.write(c);
	    }
	    inbuff.close();
	    outbuff.flush();
	    outbuff.close();
	    current++;
	}
	XmlUtil.writeToXML(uninstallDoc,uninstallFile);
    }

    // returns true if the uninstall operation is completed
    boolean done() {
        if (current >= lengthOfTask) {
	    return true;
	}
	else {
	    return false;
	}
    }

    
    // return a integer representing the amount of work that 
    // needs to be done for uninstallation
    int getLengthOfTask() {
	NodeList nl = appUnEl.getElementsByTagName("FILE");
	lengthOfTask = nl.getLength();
	nl = appUnEl.getElementsByTagName("NAR");
	if(nl != null) {
	    lengthOfTask += nl.getLength();
	}
	lengthOfTask += backupZip.size();
        return lengthOfTask;
    }

    // returns a integer representing the current uninstall 
    // work that is going on
    int getCurrent() {
        return current;
    }
    
    // returns a string representing the work that is going on
    String getMessage() 
    {
        return statMessage;
    }


    // main method which can be used for command line uninstallation
    public static void main(String args[]) {
	if(args.length!=1){
	    System.out.println("USAGE CommandLineStudioNarUnInstaller narName");	
	    return;
	}
	String narName=args[0];
	if(narName.endsWith(".nar")){
	    narName=narName.substring(0,narName.length()-4);
	}
	try {
	    PWSUninstallWizard uninstaller = new PWSUninstallWizard(narName,System.getProperty("user.dir"));
	    uninstaller.uninstall();
	    System.out.println(narName+".nar uninstalled successfully");
	}
	catch(Exception e) {
	    System.out.println("Uninstalltion failed for nar "+narName);
	    e.printStackTrace();
	}
    }
    public String[] getUnInstalledNarsList(){
	String[] list=new String[narList.size()];
	for(int i=0;i<narList.size();i++){
	    list[i]=narList.get(i).toString();
	}
	return list;
    }
    
}// PWSUninstallWizard
