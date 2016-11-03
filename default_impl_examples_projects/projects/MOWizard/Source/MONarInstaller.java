//$Id: MONarInstaller.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $
package com.adventnet.nms.tools.mowizard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.event.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import java.util.jar.*;
import java.util.*;
import javax.xml.parsers.*;
import java.util.zip.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;
import com.adventnet.nms.tools.objtorel.ClassAnalyser;
import com.adventnet.apiutils.BuilderResourceBundle;
import com.adventnet.nms.tools.utils.ToolsUtil;

public class MONarInstaller extends JPanel implements ListSelectionListener,ActionListener 
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JLabel labelDesc = null;
	javax.swing.JScrollPane confFileScroller = null;
	javax.swing.JList confFileList = null;
	javax.swing.JScrollPane previewFileScroller = null;
	javax.swing.JTextArea textPreviewFile = null;
	javax.swing.JPanel panelInfo = null;
	javax.swing.JLabel labelInfo = null;
	javax.swing.JButton buttonUpdate = null;
	javax.swing.JPanel panelSysOID = null;
	javax.swing.JCheckBox chb_SysOID = null;
	javax.swing.JLabel labelSysOID = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>
	DefaultListModel confFileListModel=null;
	String startNmsJDBC,launcherConf,mapIcon,listIcon,DBSchema,DBAlias,relClass;
	Document xmlDoc=null;
	Document launchDoc=null;
	Element rootNode=null;
	Element deviceNode=null;
	Element listIconNode=null;
	Element mapIconNode=null;
	Element oidTypeNode=null;
	Element unInstallNode=null;

	Element oldOIDTypeData=null;
	Element oldMapIconNode=null;
	Element oldListIconNode=null;
	String narFileName=null;
	String moJarName=null;
	String managedObjectName=null;
	String rootPath="NetMonitor"+File.separator+"build"+File.separator;
	String depArchList[]=null;
	boolean isRelationalJavaDefined=false;
	boolean isdeviceModelled=false;
	boolean addOnJars=false;
	boolean isUserTesterDefined=false;
	boolean isDiscoveryFilterDefined=false;
	boolean isTransparent=false;
	boolean updateStatus=false;
	int lineNo=-1;

	String beforeVar="";
	String afterVar="";
	String beforeChange=null;
	String afterChange=null;

	String startnmsbeforeChange="";
	String startnmsAfterChange="";

	String launcherConfBeforeChange="";
	String launcherConfAfterChange="";

	String listIconbeforeChange="";
	String listIconAfterChange="";
	String imageNames[]=null;

	String mapIconbeforeChange="";
	String mapIconAfterChange="";

	String oid=null;
	String type=null;
	String pollInt=null;
	String statusPoller=null;
	String discFilter=null;

	String mapIconName=null;
	String classpathList=null;

	String severityNames[]=null;
	ClassAnalyser cnr=new ClassAnalyser();
	boolean isWindows=false;
	private BuilderResourceBundle resourceBundle=ToolsUtil.getBundle();
	public MONarInstaller()
	{
		//<Begin_MONarInstaller>
		this.init();

		//<End_MONarInstaller>
	}

	public MONarInstaller(java.applet.Applet applet)
	{
		//<Begin_MONarInstaller_java.applet.Applet>
		this.applet = applet;
		this.init();

		//<End_MONarInstaller_java.applet.Applet>
	}

	public void start()
	{ 
		//<Begin_start> 
		if(running)
			return;
		running=true;


		//<End_start>
	} 

	public void initVariables()
	{ 
		//<Begin_initVariables> 
		Top= new javax.swing.JPanel();
		labelDesc= new javax.swing.JLabel();
		confFileScroller= new javax.swing.JScrollPane();
		confFileList= new javax.swing.JList();
		previewFileScroller= new javax.swing.JScrollPane();
		textPreviewFile= new javax.swing.JTextArea();
		panelInfo= new javax.swing.JPanel();
		labelInfo= new javax.swing.JLabel();
		buttonUpdate= new javax.swing.JButton();
		panelSysOID= new javax.swing.JPanel();
		chb_SysOID= new javax.swing.JCheckBox();
		labelSysOID= new javax.swing.JLabel();


		//<End_initVariables>
		confFileListModel=new DefaultListModel();
		startNmsJDBC=launcherConf=mapIcon=listIcon=DBSchema=DBAlias=relClass=null;
	} 

	public void setConstraints(int x,int y,int width,int height,double wtX,double wtY,int anchor,int fill,Insets inset,int padX,int padY )
	{ 
		//<Begin_setConstraints_int_int_int_int_double_double_int_int_Insets_int_int> 
		cons.gridx = x;
		cons.gridy = y;
		cons.gridwidth = width;
		cons.gridheight = height;
		cons.weightx = wtX;
		cons.weighty = wtY;
		cons.anchor = anchor;
		cons.fill = fill;
		cons.insets = inset;
		cons.ipadx = padX;
		cons.ipady = padY;
		//<End_setConstraints_int_int_int_int_double_double_int_int_Insets_int_int>
	} 



	public void setUpGUI(Container container)
	{ 
		//<Begin_setUpGUI_Container> 
		container.add(Top,BorderLayout.CENTER);
		Top.setLayout(new GridBagLayout());
		inset = new Insets(5,5,5,5);
		setConstraints(1,1,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
		Top.add(labelDesc,cons);
		inset = new Insets(5,5,5,5);
		setConstraints(0,1,1,1,0.1,0.1,cons.WEST,cons.BOTH,inset,0,0);
		Top.add(confFileScroller,cons);
		confFileScroller.getViewport().add(confFileList);
		inset = new Insets(5,5,5,5);
		setConstraints(0,2,2,1,0.1,0.7,cons.WEST,cons.BOTH,inset,0,0);
		Top.add(previewFileScroller,cons);
		previewFileScroller.getViewport().add(textPreviewFile);
		inset = new Insets(5,5,5,5);
		setConstraints(0,3,0,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
		Top.add(panelInfo,cons);
		panelInfo.setLayout(new GridBagLayout());
		inset = new Insets(5,5,5,5);
		setConstraints(0,0,1,2,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
		panelInfo.add(labelInfo,cons);
		inset = new Insets(5,5,5,5);
		setConstraints(1,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
		panelInfo.add(buttonUpdate,cons);
		inset = new Insets(0,0,0,0);
		setConstraints(0,0,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
		Top.add(panelSysOID,cons);
		panelSysOID.setLayout(new GridBagLayout());
		inset = new Insets(5,5,5,5);
		setConstraints(0,1,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
		panelSysOID.add(chb_SysOID,cons);
		inset = new Insets(5,5,5,5);
		setConstraints(0,0,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
		panelSysOID.add(labelSysOID,cons);


		//<End_setUpGUI_Container>
	} 

	public void setUpProperties()
	{ 

		//<Begin_setUpProperties> 

		try
		{
			labelDesc.setFont(new Font("Dialog",0,12));
			labelDesc.setForeground(new Color(-16764109));
			labelDesc.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.LineBorder(new Color(-16777216),1),resourceBundle.getString("Description"),1,2,new Font("Dialog",0,12),new Color(-16764109)));
		}
		catch(Exception ex)
		{
			showStatus(resourceBundle.getString("Exception while setting properties for bean ")+labelDesc,ex); 
		}

		try
		{
			confFileScroller.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.LineBorder(new Color(-16777216),1),resourceBundle.getString("Configuration Files"),0,0,new Font("Dialog",0,12),new Color(-13434829)));
		}
		catch(Exception ex)
		{
			showStatus(resourceBundle.getString("Exception while setting properties for bean ")+confFileScroller,ex); 
		}

		try
		{
			previewFileScroller.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.LineBorder(new Color(-16777216),1),resourceBundle.getString("Preview Changes"),0,0,new Font("Dialog",0,12),new Color(-13434829)));
		}
		catch(Exception ex)
		{
			showStatus(resourceBundle.getString("Exception while setting properties for bean ")+previewFileScroller,ex); 
		}

		try
		{
			labelInfo.setText(resourceBundle.getString("<HTML> <FONT face=Times color=#000000 >Please click on the Apply button to update all the configuration files. </FONT></HTML>"));
		}
		catch(Exception ex)
		{
			showStatus(resourceBundle.getString("Exception while setting properties for bean ")+labelInfo,ex); 
		}

		try
		{
			buttonUpdate.setFont(new Font("Dialog",0,12));
			buttonUpdate.setText(resourceBundle.getString(" Apply"));
			buttonUpdate.setMnemonic(97);
		}
		catch(Exception ex)
		{
			showStatus(resourceBundle.getString("Exception while setting properties for bean ")+buttonUpdate,ex); 
		}

		try
		{
			chb_SysOID.setFont(new Font("Dialog",0,12));
			chb_SysOID.setText(resourceBundle.getString(" Update SysOID"));
		}
		catch(Exception ex)
		{
			showStatus(resourceBundle.getString("Exception while setting properties for bean ")+chb_SysOID,ex); 
		}

		try
		{
			labelSysOID.setFont(new Font("SansSerif",0,12));
			labelSysOID.setForeground(new Color(-16764109));
			labelSysOID.setText(resourceBundle.getString("<HTML> <P><FONT face=Serif size=-1 color=black>Please check the update SysOID check box if you want the Object Identifier of your device to be included into configuration files</FONT></P></HTML>"));
		}
		catch(Exception ex)
		{
			showStatus(resourceBundle.getString("Exception while setting properties for bean ")+labelSysOID,ex); 
		}


		//<End_setUpProperties>
		confFileList.addListSelectionListener(this);
		confFileList.setModel(confFileListModel);

		if(System.getProperty("os.name").toLowerCase().trim().startsWith("win")) {
			isWindows=true;
		}
		textPreviewFile.setWrapStyleWord(true);
		textPreviewFile.setLineWrap(true);
		buttonUpdate.addActionListener(this);
	} 

	public void setUpConnections()
	{ 
		//<Begin_setUpConnections> 


		//<End_setUpConnections>
	} 

	public void stop()
	{ 
		//<Begin_stop> 
		if(!running)
			return;
		running=false;


		//<End_stop>
	} 





	public String getParameter(String input)
	{ 
		//<Begin_getParameter_String> 
		String value = null;
		if ( applet != null)
		{    
			value = applet.getParameter(input);
		}    
		else
		{    
			value = (String)com.adventnet.apiutils.Utility.getParameter(input);
		}    
		if(value == null)
		{
			if (input.equals("HOST")) value = "localhost"; 
			if (input.equals("MS_MODE")) value = "3"; 
			if (input.equals("PORT")) value = "161"; 
		}
		return value;


		//<End_getParameter_String>
	} 

	public void init()
	{ 

		//<Begin_init> 
		if (initialized == true) return; 
		setPreferredSize(new Dimension(getPreferredSize().width+714,getPreferredSize().height+625)); 
		setSize(getPreferredSize()); 
		Container container = this;
		container.setLayout(new BorderLayout()); 
		try 
		{ 
			initVariables(); 
			setUpGUI(container); 
			setUpProperties(); 
			setUpConnections(); 
		} 
		catch(Exception ex) 
		{ 
			showStatus(resourceBundle.getString("Error in init method"),ex); 
		} 
		// let us set the initialzed variable to true so  
		// we dont initialize again even if init is called 
		initialized = true; 


		//<End_init>
	} 

	public void showStatus(String message)
	{
		//<Begin_showStatus_String>
		System.out.println(resourceBundle.getString("Internal Error :")+ message);
		//<End_showStatus_String>
	}

	public void showStatus(String message,Exception ex)
	{
		//<Begin_showStatus_String_Exception>
		System.out.println(resourceBundle.getString("Internal Error :")+ message);
		ex.printStackTrace();
		//<End_showStatus_String_Exception>
	}

	public void setNarFile(String narFile) {
		narFileName=narFile;
	}

	public boolean getUpdateStatus() {
		return updateStatus;
	}

	public void checkFilesForWritePermission(String fileName) throws FileNotFoundException {
		// This check is done since before we proceed with installtion
		// we check all files for write permisson and then start with 
		// the installation process.
		File f=new File(fileName);
		if(!f.canWrite()) {
			throw new FileNotFoundException(resourceBundle.getString("Write Permission Denied : ")+f.toString());
		}
	}



	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==buttonUpdate) {
			try {
				checkFilesForWritePermission("."+File.separator+"bin"+File.separator+"startnms."+((isWindows)?"bat":"sh"));
				checkFilesForWritePermission("."+File.separator+"conf"+File.separator+"launcher_conf.txt");
				if(isRelationalJavaDefined) {
					checkFilesForWritePermission("."+File.separator+"conf"+File.separator+"DatabaseSchema.conf");
					checkFilesForWritePermission("."+File.separator+"conf"+File.separator+"DatabaseAliases.conf");
					checkFilesForWritePermission("."+File.separator+"conf"+File.separator+"relationalclasses.conf");
				}
				if(isdeviceModelled) {
					checkFilesForWritePermission("."+File.separator+"conf"+File.separator+"mapIcon.data");
					if(chb_SysOID.isSelected()) {
						checkFilesForWritePermission("."+File.separator+"conf"+File.separator+"OIDType.data");
					}	
					checkFilesForWritePermission("."+File.separator+"conf"+File.separator+"listIcon.data");
				}
				if(isDiscoveryFilterDefined) {
					checkFilesForWritePermission("."+File.separator+"conf"+File.separator+"discovery.filters");
				}
			}
			catch(Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null,e.getMessage(),resourceBundle.getString("Installtion Aborted"),JOptionPane.ERROR_MESSAGE);
				return;
			}
			updateStatus=true;
			updateStatus= updateStatus && updateStartNMS();
			if(updateStatus) {
				updateStatus= updateStatus && updateLauncherConf();
			}
			if(isdeviceModelled) {
				if(updateStatus) {
					updateStatus= updateStatus && updateMapIcon();
				}
				if(updateStatus) {
					updateStatus= updateStatus && updateListIcon();
				}
				if(chb_SysOID.isSelected()) {
					updateOIDType();
				}
			}
			else
			{
				if(isDiscoveryFilterDefined)
				{
					updateDiscoveryFilter();
				}
			}
			if(isRelationalJavaDefined) {
				try {
					// Following 3 functions if Exceptions occur then it is considered as it returning 
					// a false;
					updateDatabaseRelationalClasses();
					updateDatabaseAlias();
					updateDatabaseSchema();
					// We are doing a logically anding with "true" since if all the three functions
					// return properly then it means all 3 functions have returned true.
					updateStatus = updateStatus && true;
				}
				catch(Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null,resourceBundle.getString("Nar Installation Process Failed : ")+e.getMessage(),resourceBundle.getString("Error"),JOptionPane.ERROR_MESSAGE);
					updateStatus=false;
				}	
			}
			if(updateStatus) {
				updateStatus = updateStatus && installArchives();
			}
			updateStatus = updateStatus && writeUninstallXML();
			if(updateStatus==true) {
				JOptionPane.showMessageDialog(null,resourceBundle.getString("ManagedObject Installed Successfully"),resourceBundle.getString("Info"),JOptionPane.INFORMATION_MESSAGE);
				((JDialog)getParentContainer(this)).dispose();
			}
		}
	}

	public void findDependentArchives() {
		try {
			int noOfDependentArchives=0;
			JarFile jf=new JarFile(narFileName);
			String str=null;
			for(Enumeration en=jf.entries();en.hasMoreElements();) {
				str=en.nextElement().toString();
				if(str.startsWith("NetMonitor") && (str.toLowerCase().endsWith(".zip") || str.toLowerCase().endsWith(".jar"))) {
					noOfDependentArchives+=1;
				}
			}
			depArchList=new String[noOfDependentArchives];
			int i=0;
			for(Enumeration en=jf.entries();en.hasMoreElements();) {
				str=en.nextElement().toString();
				if(str.startsWith("NetMonitor") && (str.toLowerCase().endsWith(".zip") || str.toLowerCase().endsWith(".jar"))) {
					str="."+File.separator+"NetMonitor"+File.separator+"build"+File.separator+(new File(str)).getName();
					depArchList[i++]=str;
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();

		}
	}

	public void disAssembleNar() {
		if(narFileName==null) {
			return;
		}
		// First read the xml file and populate corresponding properties.
		try {
			File f=new File(narFileName);
			JarFile jf=new JarFile(narFileName);
			String xmlFileName=f.getName();
			//ManagedObject Name
			managedObjectName=xmlFileName.substring(0,xmlFileName.lastIndexOf("."));
			//XML File Name
			xmlFileName=managedObjectName+".xml";
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			DocumentBuilder db=dbf.newDocumentBuilder();
			xmlDoc=db.parse(jf.getInputStream(jf.getEntry(xmlFileName)));
		}

		catch(Exception e) {
			e.printStackTrace();
			return;
		}

		String moJarName=rootPath+managedObjectName+".jar";
		rootNode=(Element)xmlDoc.getElementsByTagName("MANAGED-OBJECT").item(0);

		if(rootNode.getAttribute("relJava")!=null && rootNode.getAttribute("relJava").equals("true")) {
			isRelationalJavaDefined=true;
		}
		else {
			isRelationalJavaDefined=false;
		}
		if(rootNode.getAttribute("addOnJars")!=null && rootNode.getAttribute("addOnJars").trim().length()!=0) {
			addOnJars=true;
		}
		else {
			addOnJars=false;
		}
		if(rootNode.getAttribute("userTester")!=null && rootNode.getAttribute("userTester").equals("true")) {
			isUserTesterDefined=true;
		}
		else {
			isUserTesterDefined=false;
		}
		if(rootNode.getAttribute("discFilter")!=null && rootNode.getAttribute("discFilter").equals("true")) {
			isDiscoveryFilterDefined=true;
		}
		else {
			isDiscoveryFilterDefined=false;
		}
		deviceNode=(Element)xmlDoc.getElementsByTagName("DEVICE_DETAILS").item(0);
		if(deviceNode==null) {
			isdeviceModelled=false;
			isTransparent=false;
		}
		else {
			isdeviceModelled=true;
			if(deviceNode.getAttribute("transparentImage")!=null && deviceNode.getAttribute("transparentImage").equals("true")) {
				isTransparent=true;
			}
			else {
				isTransparent=false;
			}
		}
		if(isdeviceModelled==false && isDiscoveryFilterDefined) {
			confFileListModel.addElement("discovery.filters");
		}
	}	

	public void valueChanged(ListSelectionEvent lse) {
		// Since there is only one list is assumed to be the conFileList.
		if(lse.getValueIsAdjusting()) {
			return;
		}
		int row=confFileList.getSelectedIndex();
		if(row==-1) {
			return;
		}
		String elementName=(String)confFileList.getModel().getElementAt(row);
		handleSelectedFile(elementName);
	}

	public void prepareForUpdation() {
		prepareforUpdateStartNmsJDBC();
		prepareforUpdateLauncher();
		if(isdeviceModelled) {
			prepareforUpdateOIDTypeData();
			prepareforUpdateMapIconData();
			prepareforUpdateListIconData();
		}
		if(isRelationalJavaDefined) {
			prepareforUpdateDatabaseAlias();
			prepareforUpdateDatabaseSchema();
			prepareforUpdateRelationalClasses();
		}
	}

	private boolean prepareforUpdateOIDTypeData() {
		confFileListModel.addElement("OIDType.data");
		oidTypeNode=xmlDoc.createElement("DATA");
		oidTypeNode.setAttribute("OID",deviceNode.getAttribute("OID"));
		oidTypeNode.setAttribute("Type",deviceNode.getAttribute("Type"));
		oidTypeNode.setAttribute("pollInt",deviceNode.getAttribute("pollInt"));
		if(isDiscoveryFilterDefined) {
			discFilter=rootNode.getAttribute("discFilterClass");
			oidTypeNode.setAttribute("DISC_FILTER",discFilter);
		}
		if(isUserTesterDefined) {
			statusPoller=rootNode.getAttribute("userTesterClass");
			oidTypeNode.setAttribute("USER_TESTER",statusPoller);
		}
		return true;
	}

	private boolean prepareforUpdateMapIconData() {
		confFileListModel.addElement("mapIcon.data");

		Element devDetails=(Element)xmlDoc.getElementsByTagName("DEVICE_DETAILS").item(0);
		mapIconName=devDetails.getAttribute("MAPICONNAME");
		File f=new File(mapIconName);
		mapIconNode=xmlDoc.createElement("DATA");
		mapIconNode.setAttribute("TYPE",devDetails.getAttribute("Type"));
		// Menu Name is hardCoded as "snmpmenu"
		mapIconNode.setAttribute("menuName","snmpmenu");
		mapIconNode.setAttribute("iconName",f.getName());
		return true;
	}

	private boolean prepareforUpdateListIconData() {
		confFileListModel.addElement("listIcon.data");
		Element devDetails=(Element)xmlDoc.getElementsByTagName("DEVICE_DETAILS").item(0);
		if(isTransparent) {
			imageNames=new String[1];
			// Leave it for now
			StringTokenizer stk=new StringTokenizer(devDetails.getAttribute("SEVERITY_IMAGES"),",|");
			stk.nextToken();
			imageNames[0]=stk.nextToken();
			imageNames[0]=(new File(imageNames[0])).getName();
		}
		else {
			int severityLevels=Integer.parseInt(devDetails.getAttribute("SEVERITY_LEVELS"));
			imageNames=new String[severityLevels];
			severityNames=new  String[severityLevels];
			listIconNode=xmlDoc.createElement("DATA");
			StringTokenizer stk=new StringTokenizer(devDetails.getAttribute("SEVERITY_IMAGES"),"|,");
			String str=null;
			for(int i=0;i<severityLevels;i++) {
				severityNames[i]=stk.nextToken();
				imageNames[i]=stk.nextToken();
				imageNames[i]=(new File(imageNames[i])).getName();
			}
		}
		return true;
	}

	private boolean prepareforUpdateDatabaseSchema() {
		confFileListModel.addElement("DatabaseSchema.conf");
		try {
			JarFile jf=new JarFile(narFileName);
			ZipEntry fileEntry=jf.getEntry("./conf/"+managedObjectName+"Schema.conf");
			int length=(int)fileEntry.getSize();
			byte fileContent[]=new byte[length];
			InputStream fin=jf.getInputStream(fileEntry);
			//fin.read(fileContent,0,length);
			BufferedInputStream bin = new BufferedInputStream(fin);
			bin.read(fileContent,0,length);
			DBSchema=new String(fileContent);
			jf.close();
			fin.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private boolean prepareforUpdateDatabaseAlias() {

		confFileListModel.addElement("DatabaseAliases.conf");
		try {
			JarFile jf=new JarFile(narFileName);
			ZipEntry fileEntry=jf.getEntry("./conf/"+managedObjectName+"Alias.conf");
			int length=(int)fileEntry.getSize();
			byte fileContent[]=new byte[length];
			InputStream fin=jf.getInputStream(fileEntry);
			//fin.read(fileContent,0,length);
			BufferedInputStream bin = new BufferedInputStream(fin);
			bin.read(fileContent,0,length);
			DBAlias=new String(fileContent);
			jf.close();
			fin.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private boolean prepareforUpdateRelationalClasses() {

		confFileListModel.addElement("relationalclasses.conf");
		try {
			JarFile jf=new JarFile(narFileName);
			ZipEntry fileEntry=jf.getEntry("./conf/"+managedObjectName+"relationalclasses.conf");
			int length=(int)fileEntry.getSize();
			byte fileContent[]=new byte[length];
			InputStream fin=jf.getInputStream(fileEntry);
			//fin.read(fileContent,0,length);
			BufferedInputStream bin = new BufferedInputStream(fin);
			bin.read(fileContent,0,length);
			relClass=new String(fileContent);
			jf.close();
			fin.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private boolean prepareforUpdateStartNmsJDBC() {
		confFileListModel.addElement("startnms.sh");
		// 1. Open the existing file.  
		// 2. Add changes 
		// 3. Show Changes in different color.
		String moPathLine="";
		String currentLine="";
		boolean varFound=false;
		boolean beforeVarFound=true;
		// Show Changes like the following ...
		/*
		   Line Number : <Line No.> of <fileName>
#############################
has chanaged to 
#############################
		 */   
		String fileName="startnms";
		if(isWindows) {
			fileName+=".bat";
		}
		else  {
			fileName+=".sh";
		}
		File f=new File("."+File.separator+"bin"+File.separator+fileName);
		BufferedReader bfr=null;
		try {
			bfr=new BufferedReader(new FileReader(f.toString()));
			// This function assumes that the file startnms.sh/bat will 
			// contain only one line that startwith "MOPATH=" or "set MOPATH="
			// will surely contain one MOPATH=
			while( (currentLine=bfr.readLine())!=null) {
				lineNo++;
				varFound=false;
				varFound=isMOPathFound(currentLine);
				if(varFound) {
					beforeChange=currentLine;
					afterChange=substituteMOJarValues(currentLine);
					beforeVarFound=false;
				}	
				else {
					if(beforeVarFound) {
						beforeVar+=currentLine+ "\n";
					}
					else {
						afterVar+=currentLine+"\n";
					}
				}	
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private boolean prepareforUpdateLauncher() {

		confFileListModel.addElement("launcher_conf.txt");
		try {
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			DocumentBuilder db=dbf.newDocumentBuilder();
			launchDoc=db.parse("."+File.separator+"conf"+File.separator+"launcher_conf.txt");
			NodeList applicationList=launchDoc.getElementsByTagName("application");
			for(int i=0;i<applicationList.getLength();i++) {
				Element appNode=(Element)applicationList.item(i);
				if(isClassPathFound(appNode)) {
					processNodeForLauncher(appNode);
					break;
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean isClassPathFound(Element appNode) {
		NodeList propList=appNode.getElementsByTagName("property");
		for(int i=0;i<propList.getLength();i++) {
			Element propNode=(Element)propList.item(i);
			if(propNode.getAttribute("name").equals("AppName") && propNode.getAttribute("value").equals("Start Web NMS Server")) {
				return true;
			}
		}
		return false;
	}

	private void processNodeForLauncher(Element appNode) {
		String classpath=null;
		NodeList propList=appNode.getElementsByTagName("property");
		String str=null;
		for(int i=0;i<propList.getLength();i++) {
			Element propNode=(Element)propList.item(i);
			if(propNode.getAttribute("name").equals("Classpath")) {
				classpath=propNode.getAttribute("value");
				launcherConfBeforeChange=appNode.toString();
				// Add all jars to classpath list;
				try {
					JarFile jf=new JarFile(narFileName);
					for(Enumeration en=jf.entries();en.hasMoreElements();) {
						str=en.nextElement().toString();
						if(str.startsWith("NetMonitor") && ( str.endsWith(".jar") || str.endsWith(".zip"))) {
							classpath+=";"+"./"+str;
						}	
					}
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				propNode.removeAttribute("value");
				propNode.setAttribute("value",classpath);
				launcherConfAfterChange=appNode.toString();
			}
		}
	}

	private void handleSelectedFile(String fileName) {
		// startnmsjdbc,launcher_conf.txt,listIcon.data,mapIcon.data,DatabaseSchema.conf,DatabaseAliases.conf
		// relationalclasses.conf
		if(fileName==null || fileName.trim().length()==0) {
			return;
		}
		if(fileName.trim().toLowerCase().startsWith("startnms")) {
			showPreviewStartNmsJdbc();
		}
		else if(fileName.trim().toLowerCase().equals("launcher_conf.txt")) {
			showPreviewLauncherConf();
		}
		else if(fileName.trim().toLowerCase().equals("listicon.data")) {
			showPreviewListIcon();
		}
		else if(fileName.trim().toLowerCase().equals("mapicon.data")) {
			showPreviewMapIcon();
		}
		else if(fileName.trim().toLowerCase().equals("databaseschema.conf")) {
			showPreviewDatabaseSchema();
		}
		else if(fileName.trim().toLowerCase().equals("databasealiases.conf")) {
			showPreviewDatabaseAliases();
		}
		else if(fileName.trim().toLowerCase().equals("relationalclasses.conf")) {
			showPreviewRelationalClasses();
		}
		else if(fileName.trim().toLowerCase().equals("oidtype.data")) {
			showPreviewOIDType();
		}
		return;
	}

	private void showPreviewOIDType() {
		textPreviewFile.setText(resourceBundle.getString("The following lines will be added to your <WebNMS-Home>/conf/OIDType.data")+"\n\n"+oidTypeNode.toString());
	}

	private void showPreviewRelationalClasses() {
		textPreviewFile.setText(java.text.MessageFormat.format(resourceBundle.getString("The following line will be added to {0}"),new String[]{File.separator+"conf"+File.separator+"relationalclasses.conf\n\n"}));
		textPreviewFile.append(relClass);
	}

	private void showPreviewDatabaseAliases() { 
		textPreviewFile.setText(DBAlias);
	}

	private void showPreviewDatabaseSchema() {
		textPreviewFile.setText(DBSchema);
	}

	private void showPreviewListIcon() {
		String lSep=System.getProperty("line.separator");
		String previewTxt="";

		previewTxt="Image Type = : "+((isTransparent)?"Transparent":"Non Transparent")+lSep+lSep;
		if(isTransparent) {	
			previewTxt="<DATA DEFAULT_TRANSPARENT_IMG=\""+imageNames[0]+"\"/>";
			textPreviewFile.setText(previewTxt);
			return;
		}
		previewTxt="<DATA ";
		for(int i=0;i<imageNames.length;i++) {
			previewTxt+=severityNames[i]+"_IMG=\""+imageNames[i]+"\""+System.getProperty("line.separator");
		}
		previewTxt+=" />";
		textPreviewFile.setText(previewTxt);
	}

	private void showPreviewMapIcon() {
		String previewTxt=mapIconNode.toString();
		textPreviewFile.setText(previewTxt);
	}	

	private void showPreviewStartNmsJdbc() {
		String lSep=System.getProperty("line.separator");
		String previewTxt=" Line Number : "+lineNo+" of startnms"+((isWindows)?".bat":".sh")+lSep+lSep+beforeChange+lSep+"has changed to "+lSep+afterChange; 
		textPreviewFile.setText(previewTxt);
	}

	private void showPreviewLauncherConf() {
		String lSep=System.getProperty("line.separator");
		String previewTxt="Class path entry before Change"+lSep+launcherConfBeforeChange+lSep+" has Changed to ";
		previewTxt+=lSep+lSep+launcherConfAfterChange;
		textPreviewFile.setText(previewTxt);
	}

	private String substituteMOJarValues(String line) {
		StringTokenizer stk=new StringTokenizer(line,"=");

		stk.nextToken();
		// Next Token will not exist if this is the first Nar that is 
		// installed.  
		String actualValue="."+File.separator+"NetMonitor"+File.separator+"build"+File.separator+managedObjectName+".jar";

		if(stk.hasMoreTokens()) {
			actualValue=stk.nextToken();
			for(int i=0;i<depArchList.length;i++) {
				actualValue+=File.pathSeparator+depArchList[i];

			}
		}
		if(isWindows) {
			return "set MOPATH="+actualValue;
		}
		else {
			return "MOPATH="+actualValue;
		}
	}			

	private boolean isMOPathFound(String line) {
		if(isWindows) {
			if(line.trim().toLowerCase().startsWith("set")) {
				StringTokenizer stk=new StringTokenizer(line.trim());
				stk.nextToken();
				if(stk.hasMoreTokens() && stk.nextToken().trim().startsWith("MOPATH=")) {
					return true;
				}
			}
		}
		else {
			// We assume that this is a UNIX based OS
			if(line.trim().startsWith("MOPATH=")) {
				return true;
			}
		}
		return false;
	}

	public boolean updateLauncherConf() {
		Document oldLaunch=null;
		try {
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			DocumentBuilder db=dbf.newDocumentBuilder();
			oldLaunch=db.parse(new File("."+File.separator+"conf"+File.separator+"launcher_conf.txt"));

			TransformerFactory tff=TransformerFactory.newInstance();
			Transformer tf=tff.newTransformer();
			DOMSource dSource=new DOMSource(oldLaunch.getDocumentElement());
			StreamResult sResult=new StreamResult("."+File.separator+"conf"+File.separator+managedObjectName+"launcher_conf.txt");
			tf.transform(dSource,sResult);

			DOMSource newSource=new DOMSource(launchDoc.getDocumentElement());
			StreamResult newResult=new StreamResult("."+File.separator+"conf"+File.separator+"launcher_conf.txt");
			tf.transform(newSource,newResult);
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null,resourceBundle.getString("Nar Installation Process Failed : ")+e.getMessage(),resourceBundle.getString("Error"),JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean updateStartNMS() {
		File f=null;
		if(isWindows) {
			f=new File("."+File.separator+"bin"+File.separator+"startnms.bat");
		}
		else {
			f=new File("."+File.separator+"bin"+File.separator+"startnms.sh");
		}
		try
		{
			File backup=new File(f.toString()+"_"+managedObjectName);
			FileInputStream fis=new FileInputStream(f);
			byte[] read=new byte[(int)f.length()];
			fis.read(read);
			fis.close();
			FileOutputStream fos=new FileOutputStream(backup);
			fos.write(read);
			fos.flush();
			fos.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,resourceBundle.getString("Nar Installation Process Failed : Could not update startnms"),resourceBundle.getString("Error"),JOptionPane.ERROR_MESSAGE);
			return false;
		}
		try {
			java.io.FileWriter fWrite=new java.io.FileWriter("."+File.separator+"bin"+File.separator+"startnms"+((isWindows)?".bat":".sh"));
			String fileContent=beforeVar+afterChange+"\n\n"+afterVar;

			fWrite.write(fileContent,0,fileContent.length());
			fWrite.close();
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null,resourceBundle.getString("Nar Installation Process Failed : ")+e.getMessage(),resourceBundle.getString("Error"),JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean updateListIcon() {
		try {
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			DocumentBuilder db=dbf.newDocumentBuilder();
			Document listDoc=db.parse("."+File.separator+"conf"+File.separator+"listIcon.data");
			// Write a back up of the listIcon.data file

			// Now start processing the node.

			NodeList nodes=listDoc.getElementsByTagName("DATA");
			int i=0;
			for(;i<nodes.getLength();i++) {
				Element tempNode=(Element)nodes.item(i);
				if(oidTypeNode.getAttribute("Type").equals(tempNode.getAttribute("TYPE"))) {
					oldListIconNode=(Element)tempNode.cloneNode(true);
					NamedNodeMap nnm=tempNode.getAttributes();
					int length=nnm.getLength();
					for(int j=length-1;j>=0;j--) {
						Attr att=(Attr)nnm.item(j);
						if(!att.getName().equals("MENU")) {
							tempNode.removeAttribute(att.getName());
						}
					}
					tempNode.setAttribute("TYPE",oidTypeNode.getAttribute("Type"));
					if(isTransparent) {
						tempNode.setAttribute("DEFAULT_TRANSPARENT_IMG",imageNames[0]);		
					}
					else {
						for(int j=0;j<severityNames.length;j++) {
							tempNode.setAttribute(severityNames[j].toUpperCase().trim()+"_IMG",(new File(imageNames[j])).getName());
						}
					}
					break;
				}
			}
			if(i==nodes.getLength()) {
				// TYpe is not found therefore add a new node   
				Element newNode=listDoc.createElement("DATA");
				newNode.setAttribute("TYPE",oidTypeNode.getAttribute("Type"));
				if(isTransparent) {
					newNode.setAttribute("DEFAULT_TRANSPARENT_IMG",imageNames[0]);
					newNode.setAttribute("MENU","snmpmenu");
				}
				else {
					newNode.setAttribute("MENU","snmpmenu");
					for(int j=0;j<severityNames.length;j++) {
						newNode.setAttribute(severityNames[j].toUpperCase().trim()+"_IMG",(new File(imageNames[j])).getName());
					}
				}
				listDoc.getDocumentElement().appendChild(newNode);
			}
			TransformerFactory tff=TransformerFactory.newInstance();
			Transformer tf=tff.newTransformer();
			DOMSource dSource=new DOMSource(listDoc.getDocumentElement());
			StreamResult sResult=new StreamResult(new File("."+File.separator+"conf"+File.separator+"listIcon.data"));
			tf.transform(dSource,sResult);
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null,resourceBundle.getString("Nar Installation Process Failed : ")+e.getMessage(),resourceBundle.getString("Error"),JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return false;
		}
		return true;
	}	

	public boolean updateMapIcon() {
		try {
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			DocumentBuilder db=dbf.newDocumentBuilder();
			Document listDoc=db.parse("."+File.separator+"conf"+File.separator+"mapIcon.data");

			// Now start processing the node.

			NodeList nodes=listDoc.getElementsByTagName("DATA");
			int i=0;
			for(;i<nodes.getLength();i++) {
				Element tempNode=(Element)nodes.item(i);
				if(oidTypeNode.getAttribute("Type").equals(tempNode.getAttribute("TYPE"))) {
					oldMapIconNode=(Element)tempNode.cloneNode(true);
					// Add our Node values
					tempNode.setAttribute("iconName",(new File(deviceNode.getAttribute("MAPICONNAME"))).getName());
					break;
				}
			}
			if(i==nodes.getLength()) {
				// TYpe is not found therefore add a new node   
				Element newNode=listDoc.createElement("DATA");
				newNode.setAttribute("TYPE",oidTypeNode.getAttribute("Type"));
				newNode.setAttribute("MENU","snmpmenu");
				newNode.setAttribute("iconName",(new File(deviceNode.getAttribute("MAPICONNAME"))).getName());
				listDoc.getDocumentElement().appendChild(newNode);
			}
			TransformerFactory tff=TransformerFactory.newInstance();
			Transformer tf=tff.newTransformer();
			DOMSource dSource=new DOMSource(listDoc.getDocumentElement());
			StreamResult sResult=new StreamResult(new File("."+File.separator+"conf"+File.separator+"mapIcon.data"));
			tf.transform(dSource,sResult);
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null,resourceBundle.getString("Nar Installation Process Failed : ")+e.getMessage(),resourceBundle.getString("Error"),JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return false;
		}
		return true;
	}	


	public boolean updateDatabaseSchema() throws FileNotFoundException,IOException {
		return cnr.updateDatabaseSchema(DBSchema,".");
	}

	public boolean updateDatabaseAlias() throws FileNotFoundException,IOException {
		return cnr.updateDatabaseAliases(DBAlias,".");
	}

	public boolean updateDatabaseRelationalClasses() throws FileNotFoundException,IOException {
		return cnr.updaterelationalclasses(relClass,".");
	}

	//////

	public String getNmsHome() {
		return "."+File.separator;
	}

	public void updateOIDType() {
		try {
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			DocumentBuilder db=dbf.newDocumentBuilder();
			Document doc=db.parse(getNmsHome()+"conf"+File.separator+"OIDType.data");


			NodeList dataList=doc.getElementsByTagName("DATA");
			int result=-1;
			boolean updateNeeded=false;
			boolean avoidWrite=false;
			Element data=null;
			boolean foundInFile=false;
			for(int i=0;i<dataList.getLength();i++) {
				data=(Element)dataList.item(i);
				result=compareElements(data,oidTypeNode);
				if(result==1) {
					break;
				}
			}
			if(result==1) {
				// Take backup of old data
				oldOIDTypeData=(Element)data.cloneNode(true);

				// Setting the new Values to OIDType.data
				data.setAttribute("OID",oidTypeNode.getAttribute("OID"));
				data.setAttribute("TYPE",oidTypeNode.getAttribute("Type"));
				data.setAttribute("POLL_INTERVAL",oidTypeNode.getAttribute("pollInt"));
				if(oidTypeNode.hasAttribute("USER_TESTER")) {
					data.setAttribute("USER_TESTER",oidTypeNode.getAttribute("USER_TESTER"));
				}
				if(oidTypeNode.hasAttribute("DISC_FILTER")) {
					data.setAttribute("DISC_FILTER",oidTypeNode.getAttribute("DISC_FILTER"));
				}
			}
			else {
				Element newNode=doc.createElement("DATA");
				newNode.setAttribute("OID",oidTypeNode.getAttribute("OID"));
				newNode.setAttribute("TYPE",oidTypeNode.getAttribute("Type"));
				newNode.setAttribute("POLL_INTERVAL",oidTypeNode.getAttribute("pollInt"));
				if(oidTypeNode.hasAttribute("USER_TESTER")) {
					newNode.setAttribute("USER_TESTER",oidTypeNode.getAttribute("USER_TESTER"));
				}
				if(oidTypeNode.hasAttribute("DISC_FILTER")) {
					newNode.setAttribute("DISC_FILTER",oidTypeNode.getAttribute("DISC_FILTER"));
				}
				doc.getDocumentElement().appendChild(newNode);
			}
			// write OIDType.data and add an entry into it.  
			writeXMLFile(doc,getNmsHome()+"conf"+File.separator+"OIDType.data","ISO-8859-1","OIDType.data.dtd");
		}
		catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,resourceBundle.getString("Exception :")+e.getMessage(),resourceBundle.getString("Error"),JOptionPane.ERROR_MESSAGE);
			return;
		}
	}

	private int compareElements(Element nodeFromOIDType,Element nodeFromScr) {
		// Return type 
		// -1 : if two nodes are not equal.
		//  1 : when it has compared and found OID

		if(nodeFromOIDType.getAttribute("OID").trim().equals(nodeFromScr.getAttribute("OID").trim())) {
			// If OID is different further checking need not be done.  
			return 1;
		}
		else {
			return -1;
		}
	}

	private void writeXMLFile(Document doc,String filepath,String encoding,String dtdFileName) throws Exception {
		TransformerFactory tff=TransformerFactory.newInstance();
		Transformer tf=tff.newTransformer();
		tf.setOutputProperty("doctype-system",dtdFileName);
		tf.setOutputProperty("encoding",encoding);
		DOMSource dSource=new DOMSource(doc.getDocumentElement());
		StreamResult sResult=new StreamResult(filepath);
		tf.transform(dSource,sResult);
	}

	public Container getParentContainer(Container con)  {
		while(!(con instanceof JDialog)){
			con=con.getParent();
		}
		return con;
	}

	public boolean installArchives() {
		// All jar/zip files will be stored in the NetMonitor/build directory.  
		try {
			JarFile jf=new JarFile(narFileName);
			String entryName="";
			for(Enumeration en=jf.entries();en.hasMoreElements();) {
				entryName=en.nextElement().toString();
				if(entryName.toLowerCase().endsWith(".zip") || entryName.toLowerCase().endsWith(".jar")) {
					// Copy them to NetMonitor/build directory.  
					if(!copyFile(jf,entryName)) {
						return false;
					}
				}	
			}	
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean copyFile(JarFile jf,String entryName) {
		ZipEntry arcEntry=null;
		arcEntry=jf.getEntry(entryName);
		int fileSize=(int)arcEntry.getSize();
		byte fileContents[]= new byte[fileSize];
		File targetFile=new File(entryName);
		targetFile=new File(System.getProperty("user.dir")+File.separator+"NetMonitor"+File.separator+"build"+File.separator+targetFile.getName());
		if(targetFile.exists()) {
			int returnVal=-1;
			returnVal=JOptionPane.showConfirmDialog(null,java.text.MessageFormat.format(resourceBundle.getString("{0} is already found. Do you want to replace??"),new String[]{entryName}),resourceBundle.getString("Confirm"),JOptionPane.YES_NO_OPTION);
			if(returnVal==JOptionPane.NO_OPTION) {
				return true;
			}
		}
		try {

			FileOutputStream zout=new FileOutputStream(targetFile.toString());
			BufferedInputStream zin=new BufferedInputStream(jf.getInputStream(arcEntry));
			zin.read(fileContents,0,fileSize);
			zin.close();
			zout.write(fileContents,0,fileSize);
			zout.close();
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,resourceBundle.getString(" File Copy process could not proceed ")+e.getMessage(),resourceBundle.getString("Error"),JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	public void updateDiscoveryFilter()
	{
		File f=new File("."+File.separator+"conf"+File.separator+"discovery.filters");
		if(f.exists())
		{
			try
			{
				DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
				DocumentBuilder db=dbf.newDocumentBuilder();
				Document doc=db.parse(f);
				Element element=doc.createElement("FILTER");
				element.setAttribute("className",rootNode.getAttribute("discFilterClass"));

				doc.getDocumentElement().appendChild(element);
				DOMSource dsource=new DOMSource(doc.getDocumentElement());
				StreamResult result=new StreamResult(f);

				TransformerFactory transformerfactory=TransformerFactory.newInstance();
				Transformer transformer=transformerfactory.newTransformer();

				transformer.transform(dsource,result);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				JOptionPane.showMessageDialog(null,resourceBundle.getString("discovery.filters updation failed"),resourceBundle.getString("Error"),JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private boolean writeUninstallXML() {
		// Find whether this node info is already available.
		// If it is available overwrite it. 
		File f=new File("."+File.separator+"NetMonitor"+File.separator+"build"+File.separator+"uninstall.xml");
		Document doc=null;
		Element  narNode=null;
		if(!f.exists()) {
			try {
				DocumentBuilderFactory tdbf=DocumentBuilderFactory.newInstance();
				DocumentBuilder tdb=tdbf.newDocumentBuilder();
				Document tdoc=tdb.newDocument();

				Element rootElement=tdoc.createElement("UNINSTALL");
				tdoc.appendChild(rootElement);

				TransformerFactory tff=TransformerFactory.newInstance();
				Transformer tf=tff.newTransformer();
				DOMSource dSource=new DOMSource(tdoc.getDocumentElement());
				StreamResult sResult=new StreamResult(f);
				tf.transform(dSource,sResult);
			}
			catch(Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null,resourceBundle.getString(" Installation process could not proceed : Error while writing Uninstall Information"),resourceBundle.getString("Error"),JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		try {
			DocumentBuilderFactory tdbf=DocumentBuilderFactory.newInstance();
			DocumentBuilder tdb=tdbf.newDocumentBuilder();
			Document tdoc=tdb.parse(f);

			unInstallNode=tdoc.createElement("INSTALLED_NARS");
			unInstallNode.setAttribute("NAME",managedObjectName);
			unInstallNode.setAttribute("TYPE","MANAGED-OBJECT");
			Element propertiesNode=tdoc.createElement("PROPERTIES");
			Element mapNode=tdoc.createElement("MAPICON");
			Element listNode=tdoc.createElement("LISTICON");	
			Element oidNode=tdoc.createElement("DEVICE_PARAMS");
			Element discovery=tdoc.createElement("DISCOVERY_FILTER");

			unInstallNode.appendChild(propertiesNode); 
			propertiesNode.setAttribute("ARCHIVE",managedObjectName+".jar");
			if(isRelationalJavaDefined) {
				propertiesNode.setAttribute("UPDATED-CONF","YES"); 
			}
			else {
				propertiesNode.setAttribute("UPDATED-CONF","NO");
			}
			if(isdeviceModelled)
			{
				propertiesNode.setAttribute("DEVICE_MODELED","YES");
			}
			else
			{
				propertiesNode.setAttribute("DEVICE_MODELED","NO");   
			}
			String str="";
			if(depArchList.length!=0) {
				for(int j=0;j<depArchList.length;j++) {
					str+=(new File(depArchList[j])).getName()+",";
				}
				propertiesNode.setAttribute("DEPENDENT-ARCHIVES",str);
			}
			if(isdeviceModelled)
			{
				mapNode.setAttribute("TYPE",oidTypeNode.getAttribute("Type"));
				mapNode.setAttribute("MENU","snmpmenu");
				mapNode.setAttribute("MODIFY","NO");
				mapNode.setAttribute("IMAGE",mapIconName);

				if(oldMapIconNode!=null) {
					mapNode.setAttribute("OLD_MENU",oldMapIconNode.getAttribute("menuName"));
					mapNode.setAttribute("OLD_TYPE",oldMapIconNode.getAttribute("TYPE"));
					mapNode.setAttribute("OLD_IMAGE",oldMapIconNode.getAttribute("iconName"));
					if(oldMapIconNode.hasAttribute("MAP_FILTER")) {
						mapNode.setAttribute("OLD_MAP_FILTER",oldMapIconNode.getAttribute("MAP_FILTER"));
					}
				}
				else {
					mapNode.setAttribute("NEWNODE","true");
				}
				// Mandatory attributes
				listNode.setAttribute("TYPE",oidTypeNode.getAttribute("Type"));
				listNode.setAttribute("MENU","snmpmenu");
				listNode.setAttribute("MODIFY","NO");
				
				// save Old Node Contents
				
				if(oldListIconNode!=null) {
					listNode.setAttribute("OLD_MENU",oldListIconNode.getAttribute("MENU"));
					if(oldListIconNode.hasAttribute("DEFAULT_TRANSPARENT_IMG")) {
						listNode.setAttribute("OLD_DEFAULT_TRANSPARENT_IMG",oldListIconNode.getAttribute("DEFAULT_TRANSPARENT_IMG"));
					}
					else {
						// This means old node has Multilevel severity 
						listNode.setAttribute("OLD_MULTILEVEL_SEVERITY","true");
						NamedNodeMap nnm=oldListIconNode.getAttributes();
						for(int i=0;i<nnm.getLength();i++) {
							Attr att=(Attr)nnm.item(i);
							if(att.getName().endsWith("IMG")) {
								listNode.setAttribute("OLD_"+att.getName(),att.getValue());
							}
						}
					}
				}
				else {
					listNode.setAttribute("NEWNODE","true");
				}

				if(isTransparent) {
					listNode.setAttribute("TRANSPARENT","YES");
					listNode.setAttribute("DEFAULT_TRANSPARENT_IMG",imageNames[0]);
				}
				else {
					// old Node is a multilevel image 
					// so record changes accordingly.
					listNode.setAttribute("TRANSPARENT","NO");
					Element severityNodeList[]=new Element[imageNames.length];
					for(int k=0;k<imageNames.length;k++) {
						severityNodeList[k]=tdoc.createElement("SEVERITY");
						severityNodeList[k].setAttribute("LEVEL",severityNames[k]+"_IMG");
						severityNodeList[k].setAttribute("NAME",(new File(imageNames[k])).getName());
						listNode.appendChild(severityNodeList[k]);
					}
				}
				propertiesNode.appendChild(listNode);
				propertiesNode.appendChild(mapNode);

				if(oldOIDTypeData!=null) {
					oidNode.setAttribute("OLD_OID",oldOIDTypeData.getAttribute("OID"));
					oidNode.setAttribute("OLD_TYPE",oldOIDTypeData.getAttribute("TYPE"));
					oidNode.setAttribute("OLD_POLL_INTERVAL",oldOIDTypeData.getAttribute("POLL_INTERVAL"));
				}

				oidNode.setAttribute("OID",oidTypeNode.getAttribute("OID"));
				oidNode.setAttribute("TYPE",oidTypeNode.getAttribute("Type"));
				oidNode.setAttribute("POLL_INTERVAL",oidTypeNode.getAttribute("pollInt"));
				oidNode.setAttribute("MODIFY","NO");
				if(oidTypeNode.hasAttribute("USER_TESTER")) {
					if(oldOIDTypeData!=null && oldOIDTypeData.hasAttribute("USER_TESTER")) {
						oidNode.setAttribute("OLD_USER_TESTER",oldOIDTypeData.getAttribute("USER_TESTER"));
					}
					oidNode.setAttribute("USER_TESTER",oidTypeNode.getAttribute("USER_TESTER"));
				}
			}
			if(oidTypeNode!=null && oidTypeNode.hasAttribute("DISC_FILTER")) {
				if(oldOIDTypeData!=null && oldOIDTypeData.hasAttribute("DISC_FILTER")) {
					oidNode.setAttribute("OLD_DISC_FILTER",oldOIDTypeData.getAttribute("DISC_FILTER"));
				}
				oidNode.setAttribute("DISC_FILTER",oidTypeNode.getAttribute("DISC_FILTER"));
			}
			else if(oldOIDTypeData!=null && oldOIDTypeData.hasAttribute("DISC_FILTER")) {
				oidNode.setAttribute("OLD_DISC_FILTER",oldOIDTypeData.getAttribute("DISC_FILTER"));
			}
			else {
				if(isDiscoveryFilterDefined)
				{
					discovery.setAttribute("className",rootNode.getAttribute("discFilterClass"));
					propertiesNode.appendChild(discovery);
				}
			}
			propertiesNode.appendChild(oidNode);
			tdoc.getDocumentElement().appendChild(unInstallNode);
			DOMSource dSource=new DOMSource(tdoc.getDocumentElement());
			StreamResult sResult=new StreamResult(f);
			TransformerFactory tff=TransformerFactory.newInstance();
			Transformer tf=tff.newTransformer();
			tf.transform(dSource,sResult);
		}
		catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,resourceBundle.getString(" Installation process could not proceed : Error while writing Uninstall Information"),resourceBundle.getString("Error"),JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}	

	public static void main(String[] args) {
		MONarInstaller mni=new MONarInstaller();
		mni.setNarFile("./projects/Testing2/TestClass2/TestClass2.nar");
		mni.disAssembleNar();
		mni.findDependentArchives();
		mni.prepareForUpdation();

		JDialog dlg=new JDialog();
		dlg.getContentPane().add(mni,BorderLayout.CENTER);
		dlg.setSize(600,400);
		dlg.setVisible(true);
	}	
}





