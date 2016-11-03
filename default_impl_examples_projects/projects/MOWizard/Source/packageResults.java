//$Id: packageResults.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $
package com.adventnet.nms.tools.mowizard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import com.adventnet.nms.tools.objtorel.TransverseListener;
import com.adventnet.nms.tools.objtorel.TransverseContainer;
import org.w3c.dom.*;
import org.xml.sax.*;
import javax.swing.tree.*;
import javax.xml.parsers.*;
import java.util.StringTokenizer;
import java.util.*;
import java.util.zip.*;
import java.util.jar.*;
import com.adventnet.apiutils.BuilderResourceBundle;
import com.adventnet.nms.tools.utils.ToolsUtil;

public class packageResults extends AbstractTransversePanel implements TransverseListener {
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	javax.swing.JPanel  Top = null;
	javax.swing.JScrollPane  JScrollPane1 = null;
	javax.swing.JLabel  labelPackResults = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>
	TransverseContainer transCon=null;
	Document moDoc=null;
	DefaultMutableTreeNode depNode=null;
	String projectName=null;
	Element moClassNode=null;
    Element userTester =null;
    Element discFilter =null;
    Element deviceNode =null;
	Element relJava    =null;
	Element aliasSchema=null;
	Document doc=null;
	private BuilderResourceBundle resourceBundle=ToolsUtil.getBundle();
	public packageResults()
  {
		//<Begin_packageResults>
    this.init();
  
    //<End_packageResults>
	}

	public packageResults(java.applet.Applet applet)
  {
		//<Begin_packageResults_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_packageResults_java.applet.Applet>
	}

	public void start()
  { 
		//<Begin_start>

  //<End_start>
	} 

	public void initVariables()throws Exception
  { 
		//<Begin_initVariables>
        Top= new javax.swing.JPanel();
        JScrollPane1= new javax.swing.JScrollPane();
        labelPackResults= new javax.swing.JLabel();

  //<End_initVariables>
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
	public void setUpToolBar()
  { 

		//<Begin_setUpToolBar>

  //<End_setUpToolBar>
	} 
	public void setUpGUI(Container container)throws Exception
  { 

		//<Begin_setUpGUI_Container>
container.add(Top,BorderLayout.CENTER);
	Top.setLayout(new GridBagLayout());
	inset = new Insets(10,10,10,10);
	setConstraints(0,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
	Top.add(JScrollPane1,cons);
	JScrollPane1.getViewport().add(labelPackResults);
	
  //<End_setUpGUI_Container>
	} 
	public void setUpProperties()throws Exception
  { 

		//<Begin_setUpProperties>

          try
          {
            labelPackResults.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.LineBorder(new Color(-16777216),1),resourceBundle.getString("Package Results"),1,2,new Font("Dialog",0,12),new Color(-16764109)));
            labelPackResults.setVerticalAlignment(1);
            labelPackResults.setVerticalTextPosition(1);
            labelPackResults.setHorizontalTextPosition(2);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+labelPackResults,ex); 
          }

  //<End_setUpProperties>
	} 
	public void setUpConnections()throws Exception
  { 

		//<Begin_setUpConnections>

  //<End_setUpConnections>
	} 
	public void stop()
  { 

		//<Begin_stop>

  //<End_stop>
	} 
	public void setUpMenus()
  { 

		//<Begin_setUpMenus>

  //<End_setUpMenus>
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
            if (input.equals("PORT")) value = "161"; 
            }
        return value;

  //<End_getParameter_String>
	} 
	public void init()
  { 

		//<Begin_init>
        if (initialized == true) return; 
        //setPreferredSize(new Dimension(getPreferredSize().width+693,getPreferredSize().height+484)); 
        //setSize(getPreferredSize()); 
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
      setUpMenus();
      setUpToolBar();
        

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

	public void loadScreenData() {
		labelPackResults.setText("");
		String results="<HTML>";

		Document doc=(Document)transCon.getTransverseComponent("XMLMODEL");
		moClassNode=(Element)doc.getElementsByTagName("CLASS").item(0);
		userTester=(Element)doc.getElementsByTagName("USER_TESTER").item(0);
		discFilter=(Element)doc.getElementsByTagName("DISC_FILTER").item(0);
		relJava=(Element)doc.getElementsByTagName("RELATIONAL_JAVA").item(0);
		aliasSchema=(Element)doc.getElementsByTagName("ALIAS_SCHEMA").item(0);

		results+="<TABLE><TR><TD><FONT face=Times color=#000000 size=-1>"+resourceBundle.getString("Class Name")+"</FONT></TD><TD><FONT face=Times color=#000000 size=-1>:"+moClassNode.getAttribute("package")+"."+moClassNode.getAttribute("name")+"</FONT></TD></TR>";
		if(userTester!=null) {
			results+="<TR><TD><FONT face=Times COLOR=#000000 size=-1>"+resourceBundle.getString("Status Poller")+"</FONT></TD><TD><FONT face=Times color=#000000 size=-1>:"+userTester.getAttribute("className")+"</FONT></TD></TR>";
		}
		if(discFilter!=null) {
			results+="<TR><TD><FONT face=Times COLOR=#000000 size=-1>"+resourceBundle.getString("Discovery Filter")+"</FONT></TD><TD><FONT face=Times color=#000000 size=-1>:"+discFilter.getAttribute("className")+"</FONT></TD></FONT></TR>";
		}
		if(relJava!=null) {
			results+="<TR><TD><FONT face=Times COLOR=#000000 size=-1>"+resourceBundle.getString("Relational Class")+"</FONT></TD><TD><FONT face=Times color=#000000 size=-1>:"+relJava.getAttribute("className")+"</FONT></TD></FONT></TR>";
		}
		if(aliasSchema!=null) {
			results+="<TR><TD><FONT face=Times COLOR=#000000 size=-1>"+resourceBundle.getString("Database Schema")+"</FONT></TD><TD><FONT face=Times color=#000000 size=-1>:"+moClassNode.getAttribute("name")+"Schema.conf</FONT></TD></FONT></TR>";
			results+="<TR><TD><FONT face=Times COLOR=#000000 size=-1>"+resourceBundle.getString("Database Alias")+"</FONT></TD><TD><FONT face=Times color=#000000 size=-1>:"+moClassNode.getAttribute("name")+"Alias.conf</FONT></TD></FONT></TR>";
		}
		results+="</TABLE></HTML>";
		labelPackResults.setText(results);
	}

	public void addTransverseContainer(TransverseContainer tCon) {
		transCon=tCon;
	}

	public int nextActionPerformed(String str)
	{
		return 1;
	}
	public int previousActionPerformed(String str)
	{
		return 0;
	}

	public boolean createXMLFile() {
		moDoc=(Document)transCon.getTransverseComponent("XMLMODEL");
		depNode=(DefaultMutableTreeNode)transCon.getTransverseComponent("deployNode");
		projectName=(String)transCon.getTransverseComponent("projName");

		moClassNode=(Element)moDoc.getElementsByTagName("CLASS").item(0);
		userTester =(Element)moDoc.getElementsByTagName("USER_TESTER").item(0);
		discFilter =(Element)moDoc.getElementsByTagName("DISC_FILTER").item(0);
		deviceNode =(Element)moDoc.getElementsByTagName("DEVICE_PARAMS").item(0);
		relJava=(Element)moDoc.getElementsByTagName("RELATIONAL_JAVA").item(0);
		aliasSchema=(Element)moDoc.getElementsByTagName("ALIAS_SCHEMA").item(0);
		String relJavaFileName=System.getProperty("user.dir")+File.separator+"projects"+File.separator+projectName+File.separator+"classes"+File.separator+changePackToPath(relJava.getAttribute("className"))+".class";
		try {
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			DocumentBuilder db=dbf.newDocumentBuilder();
			doc=db.newDocument();
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		Element rootNode=doc.createElement("MANAGED-OBJECT");
		doc.appendChild(rootNode);
		if(userTester.getAttribute("fileName")!=null && userTester.getAttribute("fileName").trim().length()!=0) {
			rootNode.setAttribute("userTester","true");
			rootNode.setAttribute("userTesterClass",userTester.getAttribute("className"));
		}
		else {
			rootNode.setAttribute("userTester","false");
		}
		if(discFilter.getAttribute("fileName")!=null && discFilter.getAttribute("fileName").trim().length()!=0) {
			rootNode.setAttribute("discFilter","true");
			rootNode.setAttribute("discFilterClass",discFilter.getAttribute("className"));
		}
		else {
			rootNode.setAttribute("discFilter","false");
		}
		if(relJava.getAttribute("fileName")!=null && relJava.getAttribute("fileName").trim().length()!=0 && (new File(relJavaFileName)).exists()) {
			rootNode.setAttribute("relJava","true");
			if(aliasSchema!=null) {
				rootNode.setAttribute("aliasSchema","true");
			}
			else {
				rootNode.setAttribute("aliasSchema","false");
			}
		}
		else {
			rootNode.setAttribute("relJava","false");
		}
		if(transCon.containsKey("addOnArchives")==true) {
			rootNode.setAttribute("addOnArchives",transCon.getTransverseComponent("addOnArchives").toString());
		}
		if(deviceNode!=null) {
			Element deviceDetails=doc.createElement("DEVICE_DETAILS");
			deviceDetails.setAttribute("OID",deviceNode.getAttribute("eoid"));
			deviceDetails.setAttribute("Type",deviceNode.getAttribute("devType"));
			deviceDetails.setAttribute("pollInt",deviceNode.getAttribute("pollInt"));
			 deviceDetails.setAttribute("transparentImage",transCon.getTransverseComponent("transparentImage").toString());
			 deviceDetails.setAttribute("SEVERITY_LEVELS",transCon.getTransverseComponent("SEVERITY_LEVELS").toString());
			 
			 if(transCon.containsKey("SEVERITY_LEVELS")) {
				 int noOfLevels=Integer.parseInt((String)transCon.getTransverseComponent("SEVERITY_LEVELS"));
				 String severityImages="";
				 String tempSeverityImages[]=(String [])transCon.getTransverseComponent("SEVERITY_IMAGES");
				 String tempSeverityNames[]=(String [])transCon.getTransverseComponent("SEVERITY_NAMES");
				 for(int i=0;i<noOfLevels;i++) {
					severityImages=severityImages+tempSeverityNames[i].toUpperCase()+","+tempSeverityImages[i]+"|";	 
				 }
				 deviceDetails.setAttribute("SEVERITY_IMAGES",severityImages);
			}	 
			 deviceDetails.setAttribute("MAPICONNAME",transCon.getTransverseComponent("MAPICONNAME").toString());
			rootNode.appendChild(deviceDetails);
		}

		WriteToXML wtx=new WriteToXML();
		wtx.updateXML(doc,new File(System.getProperty("user.dir")+File.separator+"projects"+File.separator+projectName+File.separator+depNode.getParent()+File.separator+depNode.getParent()+".xml"));
		return true;
	}

	private String changePackToPath(String pack) {
		if(pack.trim().length()==0) {
			return "";
		}
		while(pack.indexOf(".")!=-1) {
			StringBuffer tempBuf=new StringBuffer(pack);
			tempBuf=tempBuf.replace(pack.indexOf("."),pack.indexOf(".")+1,File.separator);
			pack=tempBuf.toString().trim();
		}	
		return pack;
	}	

	private String changePackToZipPath(String pack) {
		if(pack.trim().length()==0) {
			return "";
		}
		while(pack.indexOf(".")!=-1) {
			StringBuffer tempBuf=new StringBuffer(pack);
			tempBuf=tempBuf.replace(pack.indexOf("."),pack.indexOf(".")+1,"/");
			pack=tempBuf.toString().trim();
		}	
		return pack;
	}

	public void doCleanUp() {
	}

	public boolean finishActionPerformed()
	{
		if(!createXMLFile()) {
			return false;
		}
		if(!createNar()) {
			return false;
		}
		((JDialog)getParentContainer(this)).dispose();
		return true;
	}
	
	private void addToZipFile(ZipOutputStream zout,String fileName,String entryName) throws IOException {
		// zout     - is the output stream. ie. stream in which we can write to the target zip file.
		// fileName - This is the filename from which we read the actual file for copying into the 
		//            zip file.
		// entryName  this is the name which is shown when we do a unzip -l <zip/jar file name> or
		//            jar -tvf <zip/jar file name>
		
	
		ZipEntry ze=new ZipEntry(entryName);
		File f=new File(fileName);
		int length=(int)f.length();
		byte fileContents[]=new byte[length];
		FileInputStream fin=new FileInputStream(fileName);
		fin.read(fileContents,0,length);
		zout.putNextEntry(new ZipEntry(entryName));
		zout.write(fileContents,0,length);
		zout.closeEntry();
		fin.close();
	}

	public boolean createNar() {
		//  This method creates a nar that has to be packaged.
		
		String rootName=System.getProperty("user.dir")+File.separator+"projects"+File.separator;
		String baseName=rootName+projectName+File.separator+"classes"+File.separator;
		String moClassName=baseName+changePackToPath(moClassNode.getAttribute("package")+"."+moClassNode.getAttribute("name"))+".class";
		String userTesterFileName=baseName+changePackToPath(userTester.getAttribute("className"))+".class";
		String discFilterFileName=baseName+changePackToPath(discFilter.getAttribute("className"))+".class";
		String relJavaFileName=baseName+changePackToPath(relJava.getAttribute("className"))+".class";
		/*
		System.out.println(" MOClassName = "+(new File(moClassName)).exists());
		System.out.println(" UserTester = "+(new File(userTesterFileName)).exists());
		System.out.println(" Discovery Filter "+(new File(discFilterFileName)).exists());
		System.out.println(" Relational Java Filename = "+(new File(relJavaFileName)).exists());
		*/

		String fileName=System.getProperty("user.dir")+File.separator+"projects"+File.separator+projectName+File.separator+depNode.getParent().toString()+File.separator+depNode.getParent().toString();
		
		try {
		
			FileOutputStream fout=new FileOutputStream(fileName+".jar");
			ZipOutputStream  zout=new ZipOutputStream(fout);
			zout.setMethod(ZipOutputStream.DEFLATED);
			zout.setLevel(9);
			
			addToZipFile(zout,moClassName,changePackToZipPath(moClassNode.getAttribute("package"))+"/"+(new File(moClassName)).getName());
			//zipFile.addFile(moClassName,changePackToPath(moClassNode.getAttribute("package")));
			// Add a UserTester only if it is defined 
			if(userTester.getAttribute("fileName")!=null && userTester.getAttribute("fileName").trim().length()!=0) {
				addToZipFile(zout,userTesterFileName,changePackToZipPath(userTester.getAttribute("className"))+".class");
			}
			// Add a Discovery Filter only if it is defined 
			if(discFilter.getAttribute("fileName")!=null && discFilter.getAttribute("fileName").trim().length()!=0) {
				
				addToZipFile(zout,discFilterFileName,changePackToZipPath(discFilter.getAttribute("className"))+".class");
			}
			// Add a Relational Java only if it is defined 
			if(relJava.getAttribute("fileName")!=null && relJava.getAttribute("fileName").trim().length()!=0 && (new File(relJavaFileName)).exists()) {
				addToZipFile(zout,relJavaFileName,changePackToZipPath("com.adventnet.nms.store.relational.Relational")+depNode.getParent()+".class");	
			}
			// Close the jar file which has the will be copied into the NetMonitor/build Directory
			zout.close();

			// Create the nar file which contains the jar and then add it to the newly
			// created nar. file.  

			if(transCon.getTransverseComponent("NARPATH")==null) {
				JOptionPane.showMessageDialog(null,resourceBundle.getString("Nar File name is missing; Please try it again"),resourceBundle.getString("Fatal Error"),JOptionPane.ERROR_MESSAGE);
				return false;
			}
			String narPath=(String)transCon.getTransverseComponent("NARPATH");
			fout=new FileOutputStream(narPath+File.separator+moClassNode.getAttribute("name")+".nar");
			zout=new ZipOutputStream(fout);
			
			addToZipFile(zout,fileName+".jar","NetMonitor/build/"+(new File(fileName+".jar")).getName());
			addToZipFile(zout,"."+File.separator+"projects"+File.separator+projectName+File.separator+depNode.getParent()+File.separator+depNode.getParent()+".xml",depNode.getParent()+".xml");
			if(relJava.getAttribute("fileName")!=null && relJava.getAttribute("fileName").trim().length()!=0 && (new File(relJavaFileName)).exists()) {
				addToZipFile(zout,rootName+projectName+File.separator+depNode.getParent()+File.separator+depNode.getParent()+"Schema.conf","./conf/"+depNode.getParent()+"Schema.conf");
				addToZipFile(zout,rootName+projectName+File.separator+depNode.getParent()+File.separator+depNode.getParent()+"Alias.conf","./conf/"+depNode.getParent()+"Alias.conf");
				addToZipFile(zout,rootName+projectName+File.separator+depNode.getParent()+File.separator+depNode.getParent()+"relationalclasses.conf","./conf/"+depNode.getParent()+"relationalclasses.conf");
			}

			// Add Custom Jars & zips given by the user in Second Screen. 
			String dependentArchives=null;
			String tempFileName=null;
			if(transCon.containsKey("addOnArchives")) {
				dependentArchives=transCon.getTransverseComponent("addOnArchives").toString();
				StringTokenizer stk=new StringTokenizer(dependentArchives,File.pathSeparator);
				while(stk.hasMoreTokens()) {
					tempFileName=stk.nextToken();
					if(tempFileName.trim().length()==0) {
						// This situation arises only when the user as clicked Add On 
						// the Add On Archives and then does a cancel we get the token 
						// as a blank space.
						continue;
					}
					addToZipFile(zout,tempFileName,"NetMonitor/build/"+(new File(tempFileName)).getName());
				}
			}
			if(transCon.containsKey("MAPICONNAME") && transCon.getTransverseComponent("MAPICONNAME")!=null) {
				addToZipFile(zout,(String)transCon.getTransverseComponent("MAPICONNAME"),"./images/"+(new File((String)transCon.getTransverseComponent("MAPICONNAME"))).getName());
			}
			if(transCon.containsKey("SEVERITY_LEVELS")) {
				int noOfLevels=Integer.parseInt((String)transCon.getTransverseComponent("SEVERITY_LEVELS"));
				String[] imageNames=(String [])transCon.getTransverseComponent("SEVERITY_IMAGES");

				String[] severityLevels=(String [])transCon.getTransverseComponent("SEVERITY_NAMES");
				for(int i=0;i<noOfLevels;i++) {
					addToZipFile(zout,imageNames[i],"./icons/"+(new File(imageNames[i])).getName());
				}
			}
			zout.close();
			JOptionPane.showMessageDialog(null,resourceBundle.getString("Nar Successfully created in :")+narPath+File.separator+moClassNode.getAttribute("name")+".nar",resourceBundle.getString("Nar Packager"),JOptionPane.INFORMATION_MESSAGE);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public String getNmsHome() {
		return "."+File.separator;
	}

	public void cancelActionPerformed(String str)
	{
		if(str.trim().equals("Screen4")) {
			int returnVal=JOptionPane.showConfirmDialog(null,resourceBundle.getString("MO Generation is not complete. Do you want to cancel ???"),resourceBundle.getString("Confirm"),JOptionPane.YES_NO_OPTION);
			if(returnVal==JOptionPane.YES_OPTION) {
				((JDialog)getParentContainer(this)).dispose();
			}
		}
	}

	public void closeActionPerformed()
	{ }

	public Container getParentContainer(Container con){
		while(!(con instanceof JDialog)){
			con=con.getParent();
		}
		return con;
	}
}

