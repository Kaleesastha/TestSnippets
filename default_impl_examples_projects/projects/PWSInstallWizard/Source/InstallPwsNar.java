/**
 * $Id: InstallPwsNar.java,v 1.5 2010/10/29 13:46:40 swaminathap Exp $
 * InstallPwsNar.java
 *
 *
 * Created: Wed Apr 17 10:49:10 2002
 *
 * @author <a href="mailto:hari@HARI"></a>
 * @version
 */

package com.adventnet.studio.framework.installer;

import java.util.*;
import java.util.zip.*;
import java.io.*;
import java.net.*;
import java.lang.reflect.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

import org.w3c.dom.*;

import com.adventnet.nms.tools.utils.XmlUtil;
import com.adventnet.nms.tools.utils.FileUtil;
import com.adventnet.nms.tools.utils.JarUtil;
import com.adventnet.nms.tools.nar.CommandLineNarInstaller;
import com.adventnet.tools.update.UpdateManagerUtil;
import com.adventnet.tools.update.installer.UpdateManager;
import com.adventnet.builder.utils.general.GeneralUtility;
import com.adventnet.nms.tools.confchanges.WrapperConfUtil;


public class InstallPwsNar {

    private ZipFile pwsnar;

    private Document narDoc;

    private Document uninstallDoc;

    private ZipOutputStream backupZipStream;

    private String uninstallFile ;

    private String startNmsFile;

    private String startApplicationClientFile;//<ccs>

    //private String launcherFile;//<ccs>--launcher file updation is now done during packaging the nar, in StudioPackager.java

    //private ArrayList customNarClassPathList;//<ccs>, replaced by the foll. two distinctive arrays list

    private ArrayList customNarServerClassPathList;//<ccs>

    private ArrayList customNarClientClassPathList;//<ccs>

    private String osExt = ".sh";

    private String backupZipFile ;
    
    private ArrayList backupEntryList = null; // to validate entries in the backup zip.



    private int lengthOfTask;
    private int current = 1;
    private String statMessage;

    boolean cancelled = false;

    private String installPath;

    ArrayList dbs;
    NodeList nars;

    //String applicationDatabase;
    Element unEl;

    private CommandLineNarInstaller installNar=null;//For installing imported nars in commandline
    private ArrayList narList=null;//Will have list installed nars

    private ArrayList jfeList = new ArrayList();

    public InstallPwsNar (String narFile,String installPathArg)
    throws IOException,ZipException {
        uninstallFile = installPathArg + File.separator
            + "NetMonitor" + File.separator + "build" + File.separator + "uninstall.xml";

        backupZipFile = installPathArg +  File.separator
            + "NetMonitor" + File.separator + "build" + File.separator + "PwsBackup.zip";

        pwsnar = new ZipFile(narFile);

        // reading the nar xml file, throwing exception if the nar xml entry
        // is not present or corrupted.
        ZipEntry narxml = pwsnar.getEntry(narFile.substring(narFile.lastIndexOf(File.separator) + 1,narFile.lastIndexOf('.')) + ".xml");
        if(narxml == null) {
            throw new RuntimeException("Nar format not proper please create nar again using Packager");
        }

        // checking the installtion location by seeing if the netmonitor , build
        // directory is present.
        if( ! (new File(installPathArg + File.separator + "NetMonitor"
                + File.separator + "build")).isDirectory() ) {
            throw new RuntimeException("Check nar installation path");
        }


        // parsing the nar xml and creating xml document
        InputStream narxmlstream = pwsnar.getInputStream(narxml);
        narDoc = XmlUtil.parseAndCreateDocument(narxmlstream);


        // checking the version of webnms is same as that from which
        // the project work space was created
        NodeList nl = narDoc.getElementsByTagName("WEBNMS");

        if(nl == null || nl.getLength() == 0) {
            throw new RuntimeException("Nar format not proper please create nar again using Packager");
        }

        UpdateManagerUtil.setHomeDirectory(System.getProperty("user.dir"));
        String version = UpdateManager.getProductVersion("conf");
        String  currentContext= UpdateManager.getSubProductName("conf");//No Internationalization

        String servicePack = UpdateManager.getServicePackVersionAlone("conf");
        //We have to trim all the subpatch versions.
        int index=-1;
        if(servicePack!=null&&(index=servicePack.indexOf("."))!=-1){
            servicePack=servicePack.substring(0,index);
        }
        //If servicepack is null we consider as 0 SP
        if(servicePack==null){
            System.out.println("No ServicePack Installed");
            servicePack="0";
        }
        servicePack="SP-"+servicePack;
        Element webnmsInfo = (Element)nl.item(0);
        String productVersion = webnmsInfo.getAttribute("version");
        String productSp = webnmsInfo.getAttribute("sp");
        String narContext = webnmsInfo.getAttribute("productContext");
	if(!narContext.trim().equals("") && !checkProductContext(currentContext,narContext)){
            throw new RuntimeException("Please install this NAR in Web NMS Professional Edition");//No Internationalization
	}

        if(!checkNarCompatibility(version+"+"+servicePack,productVersion+"+"+productSp))
        {
             System.out.println("Product Version :: " + version);
            System.out.println("Producte Service Pack :: " + servicePack);
            throw new RuntimeException("Nar is not compatible with the target WebNMS. Check from which NMS the project was created and try installing");

        }



        // reading the list of databases for which the application has been
        // packaged
        Element narroot = narDoc.getDocumentElement();
        nl = narroot.getElementsByTagName("APPDATABASES");

        Element appDbs = (Element)nl.item(0);
        NodeList appnl = appDbs.getElementsByTagName("DB");
        dbs = new ArrayList();
        int applnLength = appnl.getLength();
        for(int i = 0 ; i < applnLength; i++) {
            Node n = appnl.item(i);
            if(n.getNodeType() != n.ELEMENT_NODE) {
            continue;
            }
            dbs.add(((Element)n).getAttribute("name"));
        }


        // reading the list of client nars that have been packaged along
        // with this nar
        nl = narroot.getElementsByTagName("CLIENT_NARS");
        if(nl != null && nl.getLength() > 0) {
            Element clientnar = (Element)nl.item(0);
            nars = clientnar.getElementsByTagName("NAR");
        }

        // reading the list of jar file entries that have been packaged along
        // with this nar
        nl = narroot.getElementsByTagName("JARFILE");
        int length = nl.getLength();
        for (int i = 0 ; i < length ; i++) {
            jfeList.add(nl.item(i));
        }

        //<ccs>
        //reading the list of  jars from <applicationname>.xml / nar xml file available in the NAR
        //based on types 'server' or 'client' or 'both' that have been packaged along
        // with this nar , and populating the ArrayList based on the type
        customNarServerClassPathList = new ArrayList();
        customNarClientClassPathList = new ArrayList();
        //narroot refers to <applicationname>.xml / nar xml
        nl = narroot.getElementsByTagName("NMS_JARS");
        if(nl != null && nl.getLength() > 0) {

            Element nmsjars = (Element)nl.item(0);
            NodeList jars = nmsjars.getElementsByTagName("JAR");
            for (int i = 0; i < jars.getLength() ; i++) {
                Element jar = (Element)jars.item(i);
                if(jar.getAttribute("type").equals("server")){
                     customNarServerClassPathList.add(jar.getAttribute("targetDir")+File.separator+jar.getAttribute("name"));
                }
                else if(jar.getAttribute("type").equals("client")){
                    customNarClientClassPathList.add(jar.getAttribute("targetDir")+File.separator+jar.getAttribute("name"));
                }
                else if(jar.getAttribute("type").equals("both")){
                    // if the jar needs to be updated in both the client and the server
                    // then both the Server List and Client List needs to be updated
                    customNarServerClassPathList.add(jar.getAttribute("targetDir")+File.separator+jar.getAttribute("name"));
                    customNarClientClassPathList.add(jar.getAttribute("targetDir")+File.separator+jar.getAttribute("name"));
                }
            }

        }
        //<ccs>


        // parsing the uninstall xml file
        uninstallDoc = XmlUtil.parseAndCreateDocument(uninstallFile);
        if(uninstallDoc == null) {
            uninstallDoc = XmlUtil.createNewDocument();
            Element root = uninstallDoc.createElement("UNINSTALL");
            uninstallDoc.appendChild(root);
        }


        startNmsFile =  installPathArg +File.separator+ "bin" + File.separator + "startnms";//Extension will be added while editing
        //<ccs> startApplicationClient.bat/.sh
        startApplicationClientFile = installPathArg +File.separator+ "bin" + File.separator + "startApplicationClient";//Extension will be added while editing
        //launcherFile =  installPathArg +File.separator+ "conf" + File.separator + "launcher_conf.txt";//<ccs> This
        //(launcherFile) functinality is now implemented in StudioPackager.xml
        //customNarClassPathList = new ArrayList();//<ccs>
        narList=new ArrayList();
        narList.add(narFile.substring(narFile.lastIndexOf(File.separator) + 1,narFile.lastIndexOf(".")));
        installPath = installPathArg;
    }
    boolean checkProductContext(String currentContext,String narContext){
	if(!currentContext.equals("STD_BE")){
		//If the product is not Standard Edition there is no need for this check.
		return true;
	}
	else if(currentContext.equals(narContext)){
		// In Standard Edition, only Standard edition NARs are allowed.
		return true;
	}
	return false;
    }
    boolean checkNarCompatibility(String nmsVersion,String narVersion)
    {
        if(nmsVersion.equals(narVersion)){
            return true;
        }
        if(nmsVersion.equals("4.5+SP-1")&&narVersion.equals("4.7+SP-0")){
	    return true;
	}
        if(nmsVersion.equals("4.7+SP-0")&&narVersion.equals("4.5+SP-1")){
	    return true;
        }
        return false;
    }
    // method for setting the user selected database for which
    // he wants his application to work with
    /*void setApplicationDatabase(String applicationDatabaseArg)
    {
        applicationDatabase = applicationDatabaseArg;
    }*/

    // Method for just printing the appliction name and version
    void printApplicationNameAndVersion()
    {
        Element root = narDoc.getDocumentElement();
        System.out.println("Application Name :: " + root.getAttribute("applicationname"));
        System.out.println("Application Version :: " + root.getAttribute("version"));
    }

    // method for getting the application name and version
    String getApplicationNameVersion()
    {
        Element root = narDoc.getDocumentElement();
        return root.getAttribute("applicationname") + root.getAttribute("version");
    }

    URL getReadMeURL() {
        try {
            Element narroot = narDoc.getDocumentElement();
            String readmeName = "README.txt";
            if(narroot.hasAttribute("readMe")) {
                readmeName = narroot.getAttribute("readMe");
            }
            URLClassLoader clo = new URLClassLoader(new URL[]{new File(pwsnar.getName()).toURL()});
            return clo.findResource(readmeName);
        }
        catch(MalformedURLException e) {
            System.err.println("Readme file entry does not exist :: " + e.getMessage());
            return null;
        }
    }

    // Method reads the readme entry from the nar and returns contents
    // as string
    String getReadMeForApplication() {
        try {
            StringBuffer readmeBuffer = new StringBuffer();
            // getting read me file name from nar xml
            Element narroot = narDoc.getDocumentElement();
            String readmeName = "README.txt";
            if(narroot.hasAttribute("readMe")) {
            readmeName = narroot.getAttribute("readMe");
            }
            ZipEntry readmeEntry = pwsnar.getEntry(readmeName);
            if(readmeEntry == null) {
            System.err.println("No readme file entry present in nar file");
            return "";
            }

            InputStream entryStream = pwsnar.getInputStream(readmeEntry);
            BufferedReader inbuff = new BufferedReader(new InputStreamReader(entryStream));

            String c;
            while((c = inbuff.readLine()) != null) {
            readmeBuffer.append(c + "\n");
            }
            inbuff.close();
            return readmeBuffer.toString();
        }
        catch(IOException e) {
            System.err.println("Readme file entry may be corrupted :: " + e.getMessage());
            return "";
        }
    }

    // reads the readme file from nar and returns stream of the readme
    InputStream getReadMeStream() {
        try {
            StringBuffer readmeBuffer = new StringBuffer();
            // getting read me file name from nar xml
            Element narroot = narDoc.getDocumentElement();
            String readmeName = "README.txt";
            if(narroot.hasAttribute("readMe")) {
            readmeName = narroot.getAttribute("readMe");
            }
            ZipEntry readmeEntry = pwsnar.getEntry(readmeName);
            if(readmeEntry == null) {
            System.err.println("No readme file entry present in nar file");
            return null;
            }

            return pwsnar.getInputStream(readmeEntry);
        }
        catch(IOException e) {
            System.err.println("Readme file entry may be corrupted :: " + e.getMessage());
            return null;
        }
    }

    // Method returns the list of databases that the appliction
    // can work with
    /*String[] getDBsForAppliction()
    {
        String[] appDbs = (String[])dbs.toArray(new String[]{});

        // Crude fix , as Packager is returing names that
        // don't match the database directory names.
        // Possible hole in the code.
        for (int i = 0 ; i < appDbs.length ; i++) {
            appDbs[i]=getApplicationDBDir(appDbs[i]);
        }
        return appDbs;
    }*/

    //Returns the directory name which is there under
    //conf directory for each RDBMS
    /*private String getApplicationDBDir(String dbName){
        if(dbName.equalsIgnoreCase("mssql")) {
            return "MSSQL";
        }
        else if(dbName.equalsIgnoreCase("mysql")) {
            return "MySQL";
        }
        else if(dbName.equalsIgnoreCase("oracle")) {
            return "Oracle";
        }
        else if(dbName.equalsIgnoreCase("solid")) {
            return "solid";
        }
        else if(dbName.equalsIgnoreCase("sybase")) {
            return "sybase";
        }
        else if(dbName.equalsIgnoreCase("timesten")) {
            return "TimesTen";
        }
	else if(dbName.equalsIgnoreCase("informix")) { //No Internationalization
		return "Informix"; //No Internationalization
	}
	else if(dbName.equalsIgnoreCase("firebird")) { //No Internationalization
		return "Firebird"; //No Internationalization
	}
	else if(dbName.equalsIgnoreCase("postgresql")) { //No Internationalization
		return "postgresql"; //No Internationalization
	}
        return null;
    }*/

    // Checks if  some application created from studio has been
    // already installed in the target webnms
    boolean checkIfSomeApplicationInstalled()
    {
        Element unroot = uninstallDoc.getDocumentElement();
        NodeList nl = unroot.getElementsByTagName("INSTALLED-NARS");
        for(int i = 0 ; i < nl.getLength() ; i++)
            {
            Node n = nl.item(i);
            if(n.getNodeType() != n.ELEMENT_NODE)
                {
                continue;
                }
            Element el = (Element)n;
            String appName = el.getAttribute("TYPE");
            if(appName != null && appName.equals("PWSAPPLICATION"))
                {
                return true;
                }
            }
        return false;
    }

    //Checks whether some other NAR(client,MO,Application) is
    //installed with this name
    boolean checkForNarName(String narName)
    {
        Element unroot = uninstallDoc.getDocumentElement();
        NodeList nl = unroot.getElementsByTagName("INSTALLED-NARS");
        for(int i = 0 ; i < nl.getLength() ; i++){
            Node n = nl.item(i);
            if(n.getNodeType() != n.ELEMENT_NODE){
            continue;
            }
            Element el = (Element)n;
            String appName = el.getAttribute("NAME");
            if(appName != null && appName.equals(narName)){
            return true;
            }
        }
        return false;
    }

    // method currently not used
    boolean checkIfPreviousVersionInstalled()
    {
        Element narroot = narDoc.getDocumentElement();
        String applicationName = narroot.getAttribute("applicationname");
        Element unroot = uninstallDoc.getDocumentElement();
        NodeList nl = unroot.getElementsByTagName("INSTALLED-NARS");
        for(int i = 0 ; i < nl.getLength() ; i++)
            {
            Node n = nl.item(i);
            if(n.getNodeType() != n.ELEMENT_NODE)
                {
                continue;
                }
            Element el = (Element)n;
            String appName = el.getAttribute("applicationname");

            System.out.println("checkIfPreviousVersionInstalled applicationName :: " + applicationName);
            System.out.println("checkIfPreviousVersionInstalled appName :: " + appName);
            if(applicationName.equals(appName))
                {
                return true;
                }
            }
        return false;
    }

    // method currently not used
    boolean checkIfSameVersionInstalled()
    {
        Element narroot = narDoc.getDocumentElement();
        String applicationName = narroot.getAttribute("applicationname");
        String applicationVersion = narroot.getAttribute("version");
        Element unroot = uninstallDoc.getDocumentElement();
        NodeList nl = unroot.getElementsByTagName("INSTALLED-NARS");
        for(int i = 0 ; i < nl.getLength() ; i++)
            {
            Node n = nl.item(i);
            if(n.getNodeType() != n.ELEMENT_NODE)
                {
                continue;
                }
            Element el = (Element)n;
            String appName = el.getAttribute("applicationname");
            String appVersion = el.getAttribute("version");
            if(appName.equals(applicationName) && appVersion.equals(applicationVersion))
                {
                return true;
                }
            }
        return false;
    }

    // the main method which extracts the entries
    // takes back up of the files if they are going to be overwritten -
    // - the extracted entries
    // sets the jar or zip entries that were packaged with the nar -
    // - to be put in the classpath of the startnms.bat or sh file
    void extractNar()
    throws IOException {

        Enumeration entries = pwsnar.entries();
        while(entries.hasMoreElements()) {
            ZipEntry entry = (ZipEntry)entries.nextElement();

            String entryName = entry.getName();

            statMessage = "Installing entry :: " + entryName;

            System.out.println(statMessage);

            File entryFile = new File(installPath + File.separator + entryName);

            if(entry.isDirectory()) {
            entryFile.mkdir();
            Element unFileEl = uninstallDoc.createElement("FILE");
            unFileEl.setAttribute("name",entryName);
            unEl.appendChild(unFileEl);
            current ++;
            continue;
            }
            InputStream entryStream = pwsnar.getInputStream(entry);
            BufferedInputStream inbuff = new BufferedInputStream(entryStream);

            if(entryFile.exists()) {
            appendToBackUpZip(entryName);
            }
            else {
            createParentDirs(entryFile.getParentFile());
            // creating parent directories
            // that may not exist for the entry file
            Element unFileEl = uninstallDoc.createElement("FILE");
            unFileEl.setAttribute("name",entryName);
            unEl.appendChild(unFileEl);
            }

            BufferedOutputStream outbuff = new BufferedOutputStream(new FileOutputStream(installPath + File.separator + entryName));

            int c;
            while((c = inbuff.read()) != -1) {
            outbuff.write(c);
            }
            inbuff.close();
            outbuff.close();

            current ++;
        }
	//update the classpath entry in the clientparameters.conf,launcher_conf.txt,WebNMS.jnlp files.

	updateTheLauncher();
	clientParametersJnlpUpdater();

        try{
            fetchFilesForClasspathUpdating();
        }
        catch(Exception e)
        { 
            System.out.println("Exception : "+e.getMessage());
        }

        jarEntryUpdate();

	//update the wrapper.conf file which is added for the starting webnms as a service in WebNMS5
	updateWrapperConf();
    }

    // method for creating parent directories for entry file ,
    // if they do not exist
    boolean createParentDirs(File dir) {
        if (dir.exists()) {
            return false;
        }
        if (dir.mkdir()) {
            Element unFileEl = uninstallDoc.createElement("FILE");
            String dirName = dir.toString();
            dirName = dirName.substring(installPath.length() + 1);
            dirName = dirName.replace('\\','/');
            unFileEl.setAttribute("name",dirName);
            unEl.appendChild(unFileEl);
             return true;
         }
        String parent = dir.getParent();
        return (parent != null) && (createParentDirs(new File(parent)) && dir.mkdir());
    }

    //Added for the updating the launcher_conf.txt
    void updateTheLauncher()
    {
	    File file = new File(installPath+File.separator+"conf"+File.separator+"launcher_conf.txt");//No I18N
	    if(!file.exists())
	    {
		return;	
	    }
	    try
	    {
		    appendToBackUpZip("conf"+File.separator+file.getName());//No I18N
	    }
	    catch(Exception exp)
	    {
		    System.err.println("Exception during backup :"+exp.getMessage());//No I18N
	    }
	    Document launcherDoc = processTheFile(file);
		    FileOutputStream fout=null;
	    try
	    {
		    fout = new FileOutputStream(file);
		    updateTheModification(fout,launcherDoc);
	    }
	    catch(Exception fe)
	    {
		    System.err.println("Exception in updateTheLauncher:"+fe.getMessage());//No I18N
	    }
	    finally
	    {
		    try
		    {
			    fout.close();
		    }
		    catch(Exception e){}
	    }
	  	
    }

    //Added for the updating the launcher_conf.txt
    Document processTheFile(File newFile)
    {
	    boolean elementFound= false;
	    Document doc = XmlUtil.parseAndCreateDocument(newFile.getAbsolutePath());
	    NodeList appList=doc.getElementsByTagName("application");//No I18N
	    int appListLength = appList.getLength();
	    for(int i=0;i<appListLength;i++)
	    {
		    if(elementFound)
		    {
			    break;
		    }
		    if(appList.item(i).getNodeType()==Node.ELEMENT_NODE)
		    {
			    Element application=(Element)appList.item(i);
			    NodeList propertyList=application.getElementsByTagName("property");//No I18n
			    int serverpropListLength = propertyList.getLength();
			    for(int j=0;j<serverpropListLength;j++)
			    {
				    if(propertyList.item(j).getNodeType()==Node.ELEMENT_NODE)
				    {
					    Element property=(Element)propertyList.item(j);
					    if(property.getAttribute("name").equals("InterfaceFile") && property.getAttribute("value").equals("com.adventnet.launcher.nms.RDBMS_ModeCustomizer"))//No I18n
					    {
						    elementFound=true;
						    updateElementWithClassPath("server",propertyList);//No I18n
						    break;
					    }
				    }
			    }
		    }
	    }
	    elementFound=false;
	    for(int k=0;k<appListLength;k++)
	    {
		    if(elementFound)
		    {
			    break;
		    }
		    if(appList.item(k).getNodeType()==Node.ELEMENT_NODE)
		    {
			    Element application=(Element)appList.item(k);
			    NodeList propertyList=application.getElementsByTagName("property");//No I18N
			    int clientpropListLength = propertyList.getLength();
			    for(int l=0;l<clientpropListLength;l++)
			    {
				    if(propertyList.item(l).getNodeType()==Node.ELEMENT_NODE)
				    {
					    Element property=(Element)propertyList.item(l);
					    if(property.getAttribute("name").equals("Classname") && property.getAttribute("value").equals("com.adventnet.nms.startclient.WebNMSClient"))//No I18N
					    {
						    elementFound=true;
						    updateElementWithClassPath("client",propertyList);//No I18N
						    break;
					    }
				    }
			    }
		    }
	    }
	    elementFound=false;
	    for(int m=0;m<appListLength;m++)
	    {
		    if(elementFound)
		    {
			    break;
		    }
		    if(appList.item(m).getNodeType()==Node.ELEMENT_NODE)
		    {
			    Element application=(Element)appList.item(m);
			    NodeList propertyList=application.getElementsByTagName("property");//No I18N
			    int clientpropListLength = propertyList.getLength();
			    for(int l=0;l<clientpropListLength;l++)
			    {
				    if(propertyList.item(l).getNodeType()==Node.ELEMENT_NODE)
				    {
					    Element property=(Element)propertyList.item(l);
					    if(property.getAttribute("name").equals("Classname") && property.getAttribute("value").equals("com.adventnet.launcher.nms.ReinitializeAll"))//No I18N
					    {
						    elementFound=true;
						    updateElementWithClassPath("server",propertyList);//No I18N
						    break;
					    }
				    }
			    }
		    }
	    }

	    return doc;
    }

    //Added for the updating the launcher_conf.txt
    void updateElementWithClassPath(String type,NodeList propertyList)
    {

        String separator=":";//No I18N
	if(osExt.equals(".sh"))//No I18N
	{
		separator=";";//No I18N
	}
	    for(int i=0;i<propertyList.getLength();i++)
	    {

		    if(propertyList.item(i).getNodeType()==Node.ELEMENT_NODE)
		    {
			    Element property=(Element)propertyList.item(i);

			    if(property.getAttribute("name").equals("Classpath"))//No I18N
			    {
				    String classpath= property.getAttribute("value");//No I18N

				    if(type.equals("server"))//No I18N
				    {
					    String line ="";//No I18N
					    int length = customNarServerClassPathList.size();
					    for(int j = 0 ; j < length; j++)
					    {
						    String jarOrZipFile = (String)customNarServerClassPathList.get(j);
						    jarOrZipFile=jarOrZipFile.replace('\\','/');//<ccs>//No I18N
						    line = line + jarOrZipFile;
						    if((j+1)<length) //This check is added to avoid path separator at the end
						    {
							    line = line + separator;
						    }
					    }

					    //adding project specific server side jars to the classpath
					    classpath = classpath+";"+line;//No I18N
				    }
				    else if(type.equals("client"))//No I18N
				    {
					    String line ="";//No I18N
					    for(int k = 0 ; k < customNarClientClassPathList.size() ; k++)
					    {
						    String jarOrZipFile = (String)customNarClientClassPathList.get(k);
						    jarOrZipFile=jarOrZipFile.replace('\\','/');//No I18N
						    if(!jarOrZipFile.startsWith("./"))//No I18N
						    {
							    jarOrZipFile="./"+jarOrZipFile;//No I18N
						    }
						    line=line+separator+jarOrZipFile;
					    }
					    //adding project specific client side jars to the classpath
					    classpath = classpath+line;
				    }
				    property.setAttribute("value",classpath);//No I18N
				    break;
			    }
		    }
	    }
    }


    void updateWrapperConf()
    {

	    try
	    {
		    appendToBackUpZip("conf"+File.separator+"wrapper.conf");//No I18N
	    }
	    catch(Exception e)
	    {
		    System.err.println("Exception while doing backup of wrapper.conf file:"+e.getMessage());//No I18N
	    }

	    WrapperConfUtil wrapperUpdater = new WrapperConfUtil();

	    int length = customNarServerClassPathList.size();
	    String [] classes=new String[length];
	    for(int i = 0 ; i < length; i++)
	    {
		    String jarOrZipFile = (String)customNarServerClassPathList.get(i);
		    jarOrZipFile=jarOrZipFile.replace('\\','/');//<ccs> //No I18N
		    classes[i]= jarOrZipFile;
	    }


	    wrapperUpdater.updateWrapperFile("wrapper.java.classpath",classes);//No I18N

    }

    void clientParametersJnlpUpdater()
    {
	    File file = new File(installPath+File.separator+"conf"+File.separator+"clientparameters.conf");//No I18N
	    if(!file.exists())
	    {
		    return;	
	    }
	    try
	    {
		    appendToBackUpZip("conf"+File.separator+file.getName());//No I18N
	    }
	    catch(Exception exp)
	    {
		    System.err.println("Exception while doing backup of clientparameters file:"+exp.getMessage());//No I18N
	    }
	    Document clientDoc =clientParametersConfUpdater(file);
	    FileOutputStream fout=null;
	    try
	    {
		    fout = new FileOutputStream(file);
		    updateTheModification(fout,clientDoc);
	    }
	    catch(Exception fe)
	    {
		    System.err.println("Exception while writing to file"+fe.getMessage());//No I18N
	    }
	    finally
	    {
		    try
		    {
			    fout.close();
		    }
		    catch(Exception e){}
	    }
	    File jnlpFile = new File(installPath+File.separator+"conf"+File.separator+"WebNMS.jnlp");//No I18N
	    if(!jnlpFile.exists())
	    {
		    return;	
	    }
	    try
	    {
		    appendToBackUpZip("conf"+File.separator+jnlpFile.getName());//No I18N
	    }
	    catch(Exception exp)
	    {
		    System.err.println("Exception while doing backup of jnlp file:"+exp.getMessage());//No I18N
	    }
	    Document jnlpDoc =webNMSJnlpUpdater(jnlpFile);
	    FileOutputStream fstream=null;
	    try
	    {
		    fstream = new FileOutputStream(jnlpFile);
		    updateTheModification(fstream,jnlpDoc);
	    }
	    catch(Exception fe)
	    {
		    System.err.println("Exception while writing to file"+fe.getMessage());//No I18N
	    }
	    finally
	    {
		    try
		    {
			    fstream.close();
		    }
		    catch(Exception e){}
	    }

    }

   Document clientParametersConfUpdater(File file)
   {
	   Document clientparametersDoc=XmlUtil.parseAndCreateDocument(file.getAbsolutePath());
	   NodeList parameters=(NodeList)clientparametersDoc.getElementsByTagName("PARAMETERS");//No I18N
	   Element e = (Element) parameters.item(0);
	   String classpath = e.getAttribute("ARCHIVE");//No I18N
	   int clientClassPathListSize = customNarClientClassPathList.size();
	   for(int j= 0 ; j< clientClassPathListSize;j++)
	   {
		   String jarOrZipFile = (String)customNarClientClassPathList.get(j);
		   jarOrZipFile=jarOrZipFile.replace('\\','/');//No I18N
		   if(!jarOrZipFile.startsWith("../"))//No I18N
		   {
			   if(jarOrZipFile.startsWith("./"))//No I18N
			   {
				   jarOrZipFile=jarOrZipFile.substring(2,jarOrZipFile.length());
			   }
			   jarOrZipFile="../"+jarOrZipFile;//No I18N
		   }
		   classpath = classpath +","+jarOrZipFile;//No I18N
	   }
		   //adding project specific client side jars to the classpath
		   e.setAttribute("ARCHIVE",classpath);//No I18N
	   return clientparametersDoc;
   }

   Document webNMSJnlpUpdater(File file)
   {
	   Document webnmsjnlpDoc=XmlUtil.parseAndCreateDocument(file.getAbsolutePath());
	   NodeList resources=(NodeList)webnmsjnlpDoc.getElementsByTagName("resources");//No I18N
	   Element resourceElement = (Element) resources.item(0);
	   int clientClassPathListSize = customNarClientClassPathList.size();
	   for (int j = 0; j<clientClassPathListSize; j++)
	   {
		   String jarOrZipFile = (String)customNarClientClassPathList.get(j);
		   jarOrZipFile=jarOrZipFile.replace('\\','/');//No I18N
		   
		   Element jarsFrmPackagerXML = webnmsjnlpDoc.createElement("jar");//No I18N
		   jarsFrmPackagerXML.setAttribute("href", jarOrZipFile);//No I18N
		   resourceElement.appendChild(jarsFrmPackagerXML);
	   }
	   return webnmsjnlpDoc;
   }

     public void updateTheModification(FileOutputStream fout,Document xmlDoc) throws IOException,FileNotFoundException
     {
	     try
	     {
		     TransformerFactory tff=TransformerFactory.newInstance();
		     Transformer tf=tff.newTransformer();
		     DOMSource dSource=new DOMSource(xmlDoc.getDocumentElement());
		     StreamResult sResult=new StreamResult(fout);
		     tf.transform(dSource,sResult);
		     fout.close();
	     }
	     catch(TransformerException tfe)
	     {
		     tfe.printStackTrace();
		     throw new IOException ("Package Process Failed : "+tfe.getMessage());//No I18N
	     }
     }


    /* This method has been introduced to address DMS issue. Since they dont have
       startnms, startApplicationClient and instead they will be having startdms.
       To address those issue, ClasspathUpdatingFiles.xml has been introduced.
       This files will contains all the file entry that needs classpath changes. */
    void fetchFilesForClasspathUpdating() throws Exception
    {
        String fSep = File.separator;
        InputStream classpathFiles = this.getClass().getResourceAsStream("ClasspathUpdatingFiles.xml");
        Document doc= XmlUtil.parseAndCreateDocument(classpathFiles);
        Element root=doc.getDocumentElement();
        NodeList fileNode = root.getElementsByTagName("FILE");
        int length = fileNode.getLength() ;
        for(int i=0; i< length ;i++)
            {
                Element subNode  = (Element)fileNode.item(i);
                String fileName = subNode.getAttribute("SOURCE");
                String fileType = subNode.getAttribute("TYPE");
                //if(fileType.equals("server"))
                if((fileName.contains("startnms") == true) || (fileName.contains("startdms") == true)) //No Internationalization 
                {
                    setClassPathInScriptOrBatch(fileName);
                    //setClassPathInReinitializeScriptOrBatch(fileName);
                }
                //else if(fileType.equals("client"))
                else if(fileName.contains("startApplicationClient") == true)  //No Internationalization
                {
                    setClientClassPathInScriptOrBatch(fileName);  
                }
                else if(fileName.contains("reinitialize_nms")==true) //No Internationalization
                {
                	setClassPathInReinitializeScriptOrBatch(fileName);
                }
                
                else if(fileType.equals("both"))
                {
                    setClassPathInScriptOrBatch(fileName);
                    setClassPathInReinitializeScriptOrBatch(fileName);
                    setClientClassPathInScriptOrBatch(fileName);  
                }
            }
        // incrementing the work unit by one for updating classpath
        current++;
    }

    // Method which put the jar or zip in classpath of the nms server
    void setClassPathInScriptOrBatch(String fileName)
    throws IOException
    {
        File f = new File(installPath + File.separator + fileName);
        if(!f.exists())
        {
            return;
        }
        statMessage = "Setting CLASSPATH variable in " + f.getName() +" file ....";
        System.out.println(statMessage);
        osExt = f.getName().substring(f.getName().indexOf('.'));
        
        appendToBackUpZip(fileName);
        BufferedReader reader = new BufferedReader(new FileReader(f));
        String line = null;

        StringBuffer startNmsBuffer = new StringBuffer();
        String separator=null;
        while((line = reader.readLine()) != null)
            {
                if(line.indexOf("MOPATH=")!= -1){
                    if(osExt.equals(".sh")){
                            line = "MOPATH=";
                            separator=":";
                    }
                    else{
                            line = "set MOPATH=";
                            separator=";";
                    }
                    //<ccs>--changed 'customNarClassPathList' to 'customNarServerClassPathList'
                    int length = customNarServerClassPathList.size();
                    for(int i = 0 ; i < length; i++){
                            String jarOrZipFile = (String)customNarServerClassPathList.get(i);
                            jarOrZipFile=jarOrZipFile.replace('\\','/');//<ccs>
                            line = line + jarOrZipFile;
                            if((i+1)<length) //This check is added to avoid path separator at the end
                            {
                                line = line + separator;
                            }
                    }
                }
                startNmsBuffer.append(line);
                startNmsBuffer.append("\n");
            }

        reader.close();

        BufferedWriter writer = new BufferedWriter(new FileWriter(f));
        writer.write(startNmsBuffer.toString());
        writer.flush();
        writer.close();
    }

    //<ccs>-- new method,Setting the NARPATH variable in startApplicationClient.bat/,sh
    //with the entries from customNarClientClassPathList
    void setClientClassPathInScriptOrBatch(String fileName) throws IOException{

        File f = new File(installPath + File.separator + fileName);
        if(!f.exists())
        {
            return;
        }
        statMessage = "Setting CLASSPATH variable in " + f.getName() +" file ....";
        System.out.println(statMessage);
        osExt = f.getName().substring(f.getName().indexOf('.'));
        
        appendToBackUpZip(fileName);
        BufferedReader reader = new BufferedReader(new FileReader(f));
        String line = null;
        String NARPATH = new String();
        StringBuffer startApplicationClientBuffer = new StringBuffer();
        String separator=null;
        while((line = reader.readLine()) != null){
            if(line.indexOf("NARPATH=")!= -1){
                String narString = null;
                NARPATH = line.trim();
                int narIndex;
                if(osExt.equals(".sh")){
                     narString = "NARPATH=";
                     narIndex = narString.length();
                     separator=":";
                }
                else{
                    narString = "set NARPATH=";
                    narIndex = narString.length();
                    separator=";";
                }
                NARPATH=NARPATH.substring(narIndex);
                for(int i = 0 ; i < customNarClientClassPathList.size() ; i++){
                    String jarOrZipFile = (String)customNarClientClassPathList.get(i);
                    jarOrZipFile=jarOrZipFile.replace('\\','/');
                    if(!jarOrZipFile.startsWith("./")){
                       jarOrZipFile="./"+jarOrZipFile;
                    }
                    NARPATH=NARPATH+separator+jarOrZipFile;
                }
                line=narString+NARPATH;
            }
            startApplicationClientBuffer.append(line);
            startApplicationClientBuffer.append("\n");
        }

        reader.close();
        BufferedWriter writer = new BufferedWriter(new FileWriter(f));
        writer.write(startApplicationClientBuffer.toString());
        writer.flush();
        writer.close();
    }
    //<ccs>

    //method to set entries in reinitialize_nms.bat/,sh file.
    //with the entries from customNarServerClassPathList
    void setClassPathInReinitializeScriptOrBatch(String fileName)
    throws IOException
    {
    	File f = new File(installPath + File.separator + fileName);
        if(!f.exists())
        {
            return;
        }
        statMessage = "Setting CLASSPATH variable in " + f.getName() +" file ...."; //No Internationalization
        System.out.println(statMessage);
        osExt = f.getName().substring(f.getName().indexOf('.'));
        
        appendToBackUpZip(fileName);
        BufferedReader reader = new BufferedReader(new FileReader(f));
        String line = null;
        String NARPATH = new String();
        StringBuffer reinitializeNmsBuffer = new StringBuffer();
        String separator,endtag=null;
        int k = 0;
        while((line = reader.readLine()) != null){
	    if(line.indexOf("CLASSPATH=") != -1) //No Internationalization
	    {
	    	k = k + 1;
	    }
	    if(k == 1)
	    { 
	    	StringBuffer appendBuffer = new StringBuffer();
            if(osExt.equals(".sh")){ //No Internationalization
            	reinitializeNmsBuffer.append("\n"); //No Internationalization
                 appendBuffer = reinitializeNmsBuffer.append("OTHER_CLASSPATH="); //No Internationalization 
                 separator=":"; //No Internationalization
                 endtag="$OTHER_CLASSPATH"; //No Internationalization
            }
            else{
            	reinitializeNmsBuffer.append("\n"); //No Internationalization
                appendBuffer = reinitializeNmsBuffer.append("set OTHER_CLASSPATH=");  //No Internationalization
                separator=";"; //No Internationalization
                endtag="%OTHER_CLASSPATH%"; //No Internationalization
            }
            for(int i = 0 ; i < customNarServerClassPathList.size() ; i++){
                String jarOrZipFile = (String)customNarServerClassPathList.get(i);
                jarOrZipFile=jarOrZipFile.replace('\\','/'); 
                if(!jarOrZipFile.startsWith("./")){ //No Internationalization
                   jarOrZipFile="./"+jarOrZipFile; //No Internationalization
            }    
                appendBuffer.append(jarOrZipFile+separator);
            }
                appendBuffer.append(endtag);
                appendBuffer.append("\n"); //No Internationalization

            k++;
    	}
	    
            reinitializeNmsBuffer.append(line);
            reinitializeNmsBuffer.append("\n"); //No Internationalization
        }

        reader.close();
        BufferedWriter writer = new BufferedWriter(new FileWriter(f));
        writer.write(reinitializeNmsBuffer.toString());
        writer.flush();
        writer.close();
    }

    // Method which appends entries that need to be backed up for uninstallation
    void appendToBackUpZip (String  entry)
    throws IOException {

        entry = entry.replace('\\', '/'); // to be platform independant, zip entries should have forward slashes only
        
        String dotSlash = "./";           
        if (entry.startsWith(dotSlash)){  // dot slash prefix, is not required for the entries
            entry = entry.substring(dotSlash.length());
        } 

        if (entry.startsWith("/")) {      // slash prefix, is not required before the entry. hence it is removed here.
            entry = entry.substring(1);
        }

        if (backupEntryList == null){     // if the ArrayList 'backupEntryList' is null initialize it
            backupEntryList = new ArrayList();
        }

        if (backupEntryList.contains(entry)){
            return;                       // if this entry already added to the backupZip, it will not be added again
        } else {
            backupEntryList.add(entry);   // if this entry is not yet added to the backupZip, it will be added
        }
        

    ZipEntry ze = new ZipEntry(entry);

    if(backupZipStream == null) {
        backupZipStream =  new ZipOutputStream(new FileOutputStream(backupZipFile));
    }
    
    try {

        backupZipStream.putNextEntry(ze);
    } catch(ZipException exp) {
        backupZipStream.closeEntry();
        return;
    }

    BufferedInputStream entryStream = new BufferedInputStream(new FileInputStream(installPath + File.separator + entry));

    int data;
    while( (data = entryStream.read()) != -1) {
        backupZipStream.write(data);
    }

    entryStream.close();

    backupZipStream.closeEntry();
    }

    // this method copies the selected applications database specific
    // schema ,  alias files under the conf directory
    /*void setupApplicationDatabase()
    throws IOException
    {
    statMessage = "Setting up application database ...";
    System.out.println(statMessage);

    String aliasFile = installPath + File.separator + "conf"
        + File.separator + "DatabaseAliases.conf";
    if((new File(aliasFile).exists())) {
        appendToBackUpZip("conf/DatabaseAliases.conf");
    }
    String schemaFile = installPath + File.separator + "conf"
        + File.separator + "DatabaseSchema.conf";
    if((new File(schemaFile)).exists()) {
        appendToBackUpZip("conf/DatabaseSchema.conf");
    }
    String paramsFile = installPath + File.separator + "conf"
        + File.separator + "database_params.conf";
    if((new File(paramsFile)).exists()){
        appendToBackUpZip("conf/database_params.conf");
    }

    FileUtil.copyDirectory(new File(installPath + File.separator
                    + "conf" + File.separator + applicationDatabase),
                   new File(installPath + File.separator + "conf"));
    // accounting for one more unit of work done 
    // for setting up application database.
    current++;
    }*/

    // Method uses reflection api to install the client nar packaged with
    // studio nar , using CommandLineNarInstaller
    void installClientNars()
    throws ClassNotFoundException,NoSuchMethodException,
           InstantiationException,IllegalAccessException,
               InvocationTargetException {

    if(nars == null) {
        return;
    }
    for (int i = 0 ; i < nars.getLength() ; i++) {
        Element nar = (Element)nars.item(i);
        String narName=nar.getAttribute("name");
        statMessage = "Installing client nar :: " + narName;

        System.out.println(statMessage);
        installNar=new CommandLineNarInstaller(nar.getAttribute("path")
                           + "/" + nar.getAttribute("installxml"));
        if(installNar.processInstallation()){
        if(narName.indexOf(".")!=-1){
            narName=narName.substring(0,narName.lastIndexOf("."));
        }
        narList.add(narName);

        }
        // accounting for one unit of work done
        // for installing the client nars
        current ++;
    }
    }

    // method writes the information that will be used while uninstallion
    // into the uninstall.xml file
    void writeUninstallInfo()
    throws IOException
    {
    statMessage = "Writing uninstall information ...";

    System.out.println(statMessage);

    backupZipStream.finish();
    backupZipStream.close();

    if(nars != null){
        for (int i = 0 ; i < nars.getLength() ; i++) {
        Element nar = (Element)nars.item(i);
        Element unNarEl = uninstallDoc.createElement("NAR");
        unNarEl.setAttribute("name",nar.getAttribute("displayName"));
        unEl.appendChild(unNarEl);
        }
    }

    XmlUtil.writeToXML(uninstallDoc,uninstallFile);
    // accounting for one unit of work done 
    // for writing the studio nar uninstall information.
    current ++;
    }

    // method currently not used
    void printEntries()
    {
    Enumeration entries = pwsnar.entries();
    while(entries.hasMoreElements())
        {
        ZipEntry entry = (ZipEntry)entries.nextElement();
        System.out.println("Entry :: " + entry);
        }
    }

    // Method which will be called from the UI part of the installer
    // to install the studio nar
    void go()
    throws IOException,ClassNotFoundException,NoSuchMethodException,
           InstantiationException,IllegalAccessException,InvocationTargetException {
    Element root = narDoc.getDocumentElement();
    unEl = uninstallDoc.createElement("INSTALLED-NARS");
    String narName = pwsnar.getName();
    narName = narName.substring(narName.lastIndexOf(File.separator) + 1,narName.lastIndexOf('.'));
    unEl.setAttribute("NAME",narName);
    // Logicall storing application name and version as name is the correct one
    // but since Deployment Wizard now stores nar name as the list
    // of installed nars , we will follow the same approach
    unEl.setAttribute("APP_VER",root.getAttribute("applicationname")
              + root.getAttribute("version"));
    unEl.setAttribute("TYPE","PWSAPPLICATION");
    uninstallDoc.getDocumentElement().appendChild(unEl);

    extractNar();
    //setupApplicationDatabase();
    writeUninstallInfo();
    // please do not move this line below command line install
    // as command line needs to put entry in uninstall.xml independently
    installClientNars();
    }

    // if any exception occurs this method will be called , to cancel
    // the installation process and start auto uninstall
    void cancelInstall() {
    cancelled = true;
    }

    boolean cancelled() {
    return cancelled;
    }

    // returns true if the installation process is completed
    // else returns false
    boolean done() {
        if (current >= lengthOfTask) {
        return true;
    }
    else {
        return false;
    }
    }

    // returns a integer representing the number of work to be done
    // used for setting the maximum value of install progress bar
    int getLengthOfTask() {

    lengthOfTask = pwsnar.size();

    // adding task for setting classpath in startnms batch
    // and writing uninstall information and setting up database
    lengthOfTask += 1;

    // adding one for accounting rebranding related updation
    lengthOfTask += 1;

    //adding task for setting up application database
    lengthOfTask += 1;

    // adding one for writing uninstall information
    lengthOfTask += 1;

    //adding task for Client nar installation
    if(nars != null) {
        lengthOfTask += nars.getLength();
    }
        return lengthOfTask;
    }

    // returns a integer represeting the currently running work
    int getCurrent()
    {
        return current;
    }

    // returns a string representing the current running work
    String getMessage()
    {
        return statMessage;
    }

    /* 
     * This method updates jars entries
     * Before updating take a back of the file in backup zip
     */
    private void jarEntryUpdate()
        throws IOException {
        
        int length = jfeList.size();
        for(int i = 0 ; i < length ; i++) {
            Element el = (Element) jfeList.get(i);
            String jar = el.getAttribute("JAR");
            String entry = el.getAttribute("SOURCE");
            statMessage = "updating entry " + entry + " in jar " + jar;
            System.out.println(statMessage);
            appendToBackUpZip(jar);
            JarUtil.addEntry(new File(installPath + File.separator + jar), new File(installPath + File.separator 
                                        + "jar_files" + File.separator + entry), entry);
        }

        FileUtil.deleteDirectory(new File(installPath + File.separator + "jar_files"));
        
        // accounting for one unit of work done
        // for installing the jar entries in the studio nar
        current++;
        //TODO :: i18n of installer messages not done
    }

    // main , can be used for command line installation
    public static void main(String args[]) {
    if(args.length!=1){
        //System.out.println("USAGE CommandLineStudioNarInstaller narFile applicationDB");
        System.out.println("USAGE CommandLineStudioNarInstaller narFile"); //No I18N
	return;
    }
    if(!(new File(args[0])).isFile()){
        System.out.println("File "+args[0]+" doesn't exist. Unable to proceed installation");
        return;
    }
    args[0]=args[0].replace('/',File.separatorChar);//SP1 Fix Mess Id : 129046
    String narName=args[0].substring(args[0].lastIndexOf(File.separator)+1,args[0].lastIndexOf("."));
    InstallPwsNar install=null;
    try {
        install = new InstallPwsNar(args[0],System.getProperty("user.dir"));
        /*String dbDir=install.getApplicationDBDir(args[1]);
        if(!(new File(System.getProperty("user.dir")+File.separator+"conf"+File.separator+dbDir)).exists()){
        System.out.println("Unable to proceed installation For the specified RDBMS");
        System.out.println("Please specify an RDBMS from the following list");
        String[] dbList=install.getDBsForAppliction();
        for(int i=0;i<dbList.length;i++){
            int j=i+1;
            System.out.println("  "+j+". "+dbList[i]);
        }
        return;
        }*/
        //install.setApplicationDatabase(dbDir);
        install.printApplicationNameAndVersion();
        //Check any studio NAR is installed
        if(install.checkIfSomeApplicationInstalled()){
        System.out.println("A  Studio application is already installed.Please uninstall it to proceed installation .");
        System.exit(0);
        }
        //Check whether any other NAR is installed with this NAR name
        if(install.checkForNarName(narName)){
        System.out.println("A nar with the name "+narName+" already installed.Please uninstall it to proceed installation");
        System.exit(0);
        }
        install.go();
        System.out.println(narName+".nar installed successfully");
    }
    catch(Exception exp) {
        System.out.println("Installation Failed"+exp.getMessage());
        if(install==null){
        exp.printStackTrace();
        return;
        }
        install.cancelInstall();
        System.out.println("Uninstalling installed informations");

        try{
        //To proceed installation we have to write all the
        //installed information to uninstall.xml.
        //So that uninstaller will read the xml and will proceed
        install.writeUninstallInfo();
        (new PWSUninstallWizard(install.getApplicationNameVersion(),System.getProperty("user.dir"))).uninstall();
        }
        catch(Exception e){
        e.printStackTrace();
        }
    }
    }

    public String[] getInstalledNarsList(){
    String[] list=new String[narList.size()];
    for(int i=0;i<narList.size();i++){
        list[i]=narList.get(i).toString();
    }
    return list;
    }

}// InstallPwsNar
