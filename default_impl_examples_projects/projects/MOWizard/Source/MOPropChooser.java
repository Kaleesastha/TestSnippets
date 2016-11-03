//$Id: MOPropChooser.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $
package com.adventnet.nms.tools.mowizard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import com.adventnet.nms.tools.objtorel.TransverseContainer;
import com.adventnet.nms.tools.objtorel.TransverseListener;
import org.w3c.dom.*;
import org.xml.sax.*;
import javax.xml.parsers.*;
import java.util.StringTokenizer;
import com.adventnet.apiutils.BuilderResourceBundle;
import com.adventnet.nms.tools.utils.ToolsUtil;

public class MOPropChooser extends AbstractTransversePanel implements TransverseListener 
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel panel_Container = null;
	javax.swing.JPanel panel_Modifiers = null;
	javax.swing.JPanel panel_ClsDetails = null;
	javax.swing.JLabel label_Interface = null;
	javax.swing.JLabel label_ClassName = null;
	javax.swing.JTextField text_ClsName = null;
	javax.swing.JTextField text_Interface = null;
	javax.swing.JLabel label_PackageName = null;
	javax.swing.JTextField text_PackageName = null;
	javax.swing.JPanel panel_MOPropTypeAdd = null;
	javax.swing.JCheckBox chb_AdFrMangd = null;
	javax.swing.JCheckBox chb_AdFrmDev = null;
	javax.swing.JLabel infoTxt = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>
	// Custom Variable Declerations.  
	ButtonGroup grp_accMod=null;
	TransverseContainer transCon;
	String projectName=null;
	private BuilderResourceBundle resourceBundle=ToolsUtil.getBundle();

	public MOPropChooser()
	{
		//<Begin_MOPropChooser>
		this.init();

		//<End_MOPropChooser>
	}

	public MOPropChooser(java.applet.Applet applet)
	{
		//<Begin_MOPropChooser_java.applet.Applet>
		this.applet = applet;
		this.init();

		//<End_MOPropChooser_java.applet.Applet>
	}


	public void setUpProperties()
	{ 

		//<Begin_setUpProperties> 

		try
		{
			panel_Modifiers.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.LineBorder(new Color(-16777216),1),"",1,2,new Font("Dialog",0,12),new Color(-10066279)));
		}
		catch(Exception ex)
		{
			showStatus(resourceBundle.getString("Exception while setting properties for bean ")+panel_Modifiers,ex); 
		}

		try
		{
			panel_ClsDetails.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.LineBorder(new Color(-16777216),1),resourceBundle.getString("Class Details"),1,2,new Font("Dialog",0,12),new Color(-16764109)));
		}
		catch(Exception ex)
		{
			showStatus(resourceBundle.getString("Exception while setting properties for bean ")+panel_ClsDetails,ex); 
		}

		try
		{
			label_Interface.setText(resourceBundle.getString("Interfaces"));
			label_Interface.setForeground(new Color(-16764109));
		}
		catch(Exception ex)
		{
			showStatus(resourceBundle.getString("Exception while setting properties for bean ")+label_Interface,ex); 
		}

		try
		{
			label_ClassName.setText(resourceBundle.getString("Class Name"));
			label_ClassName.setForeground(new Color(-16764109));
		}
		catch(Exception ex)
		{
			showStatus(resourceBundle.getString("Exception while setting properties for bean ")+label_ClassName,ex); 
		}

		try
		{
			label_PackageName.setText(resourceBundle.getString("Package Name"));
			label_PackageName.setForeground(new Color(-16764109));
		}
		catch(Exception ex)
		{
			showStatus(resourceBundle.getString("Exception while setting properties for bean ")+label_PackageName,ex); 
		}

		try
		{
			panel_MOPropTypeAdd.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.LineBorder(new Color(-16777216),1),resourceBundle.getString("Properties"),1,2,new Font("Dialog",0,12),new Color(-16764109)));
		}
		catch(Exception ex)
		{
			showStatus(resourceBundle.getString("Exception while setting properties for bean ")+panel_MOPropTypeAdd,ex); 
		}

		try
		{
			chb_AdFrMangd.setText(resourceBundle.getString("Add from Existing ManagedObject"));
			chb_AdFrMangd.setLabel(resourceBundle.getString("Extend an existing ManagedObject"));
		}
		catch(Exception ex)
		{
			showStatus(resourceBundle.getString("Exception while setting properties for bean ")+chb_AdFrMangd,ex); 
		}

		try
		{
			chb_AdFrmDev.setText(resourceBundle.getString("Add From Device"));
			chb_AdFrmDev.setLabel(resourceBundle.getString("Model a Device"));
		}
		catch(Exception ex)
		{
			showStatus(resourceBundle.getString("Exception while setting properties for bean ")+chb_AdFrmDev,ex); 
		}


		//<End_setUpProperties>
		text_ClsName.addKeyListener(new KeyAdapter(){
				public void keyTyped(KeyEvent ke){
				if((ke.getKeyChar() >= 'A' && ke.getKeyChar() <= 'Z') || (ke.getKeyChar() >='a' && ke.getKeyChar() <= 'z') || (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') || ke.getKeyChar()== ke.VK_BACK_SPACE){
				}	
				else{
				Toolkit.getDefaultToolkit().beep();
				ke.consume();
				}
				}
				});

		text_Interface.addKeyListener(new KeyAdapter() { 
				public void keyTyped(KeyEvent ke){
				if((ke.getKeyChar() >= 'A' && ke.getKeyChar() <= 'Z') || (ke.getKeyChar() >='a' && ke.getKeyChar() <= 'z') || (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') || ke.getKeyChar() == '.' || ke.getKeyChar()== ke.VK_BACK_SPACE || ke.getKeyChar()==ke.VK_COMMA){
				}
				else{
				Toolkit.getDefaultToolkit().beep();
				ke.consume();
				}
				}
				});


		// Start showing padam.
		infoTxt.setVerticalAlignment(JLabel.TOP);
		infoTxt.setForeground(Color.black);
		infoTxt.setFont(new Font("SansSerif",14,Font.BOLD));
		infoTxt.setHorizontalAlignment(JLabel.LEFT);
		infoTxt.setText(resourceBundle.getString("<HTML><FONT FACE=Times size=+1>Properties can be added<UL><LI> By extending an Existing ManagedObject.</LI> <LI> By Modelling a Device</FONT></LI>"));
	} 

	public void init()
	{ 
		if(false) {
			//<Begin_init> 
			if (initialized == true) return; 
			/*
			   setPreferredSize(new Dimension(getPreferredSize().width+839,getPreferredSize().height+605)); 
			   setSize(getPreferredSize()); 
			 */
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
		///setSize(573,507); 
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
	}

	public void setUpConnections()
	{ 

		//<Begin_setUpConnections> 


		//<End_setUpConnections>
	} 
	public void initVariables()
	{ 

		//<Begin_initVariables> 
		Top= new javax.swing.JPanel();
		panel_Container= new javax.swing.JPanel();
		panel_Modifiers= new javax.swing.JPanel();
		panel_ClsDetails= new javax.swing.JPanel();
		label_Interface= new javax.swing.JLabel();
		label_ClassName= new javax.swing.JLabel();
		text_ClsName= new javax.swing.JTextField();
		text_Interface= new javax.swing.JTextField();
		label_PackageName= new javax.swing.JLabel();
		text_PackageName= new javax.swing.JTextField();
		panel_MOPropTypeAdd= new javax.swing.JPanel();
		chb_AdFrMangd= new javax.swing.JCheckBox();
		chb_AdFrmDev= new javax.swing.JCheckBox();
		infoTxt= new javax.swing.JLabel();


		//<End_initVariables>


	} 


	public void setProjectName(String projName) {
		projectName=projName;
	}


	public void setUpGUI(Container container)
	{ 

		//<Begin_setUpGUI_Container> 
		container.add(Top,BorderLayout.CENTER);
		Top.setLayout(new GridBagLayout());
		inset = new Insets(10,10,10,10);
		setConstraints(0,1,1,1,0.1,0.5,cons.CENTER,cons.BOTH,inset,0,0);
		Top.add(panel_Container,cons);
		panel_Container.setLayout(new GridBagLayout());
		inset = new Insets(10,10,10,10);
		setConstraints(0,0,0,1,0.1,0.1,cons.WEST,cons.BOTH,inset,0,0);
		panel_Container.add(panel_Modifiers,cons);
		panel_Modifiers.setLayout(new GridBagLayout());
		inset = new Insets(10,10,10,10);
		setConstraints(0,0,0,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
		panel_Modifiers.add(panel_ClsDetails,cons);
		panel_ClsDetails.setLayout(new GridBagLayout());
		inset = new Insets(10,10,10,10);
		setConstraints(0,2,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
		panel_ClsDetails.add(label_Interface,cons);
		inset = new Insets(10,10,10,10);
		setConstraints(0,1,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
		panel_ClsDetails.add(label_ClassName,cons);
		inset = new Insets(10,10,10,10);
		setConstraints(1,1,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
		panel_ClsDetails.add(text_ClsName,cons);
		inset = new Insets(10,10,10,10);
		setConstraints(1,2,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
		panel_ClsDetails.add(text_Interface,cons);
		inset = new Insets(10,10,10,10);
		setConstraints(0,0,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
		panel_ClsDetails.add(label_PackageName,cons);
		inset = new Insets(10,10,10,10);
		setConstraints(1,0,0,1,0.1,0.1,cons.CENTER,cons.HORIZONTAL,inset,0,0);
		panel_ClsDetails.add(text_PackageName,cons);
		inset = new Insets(10,10,10,10);
		setConstraints(0,1,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
		panel_Modifiers.add(panel_MOPropTypeAdd,cons);
		panel_MOPropTypeAdd.setLayout(new GridBagLayout());
		inset = new Insets(10,10,10,10);
		setConstraints(0,1,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
		panel_MOPropTypeAdd.add(chb_AdFrMangd,cons);
		inset = new Insets(10,10,10,10);
		setConstraints(0,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
		panel_MOPropTypeAdd.add(chb_AdFrmDev,cons);
		inset = new Insets(10,10,10,10);
		setConstraints(0,2,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
		panel_Modifiers.add(infoTxt,cons);


		//<End_setUpGUI_Container>
	} 

	public void stop()
	{ 

		//<Begin_stop> 


		//<End_stop>
	} 
	public void start()
	{ 

		//<Begin_start> 


		//<End_start>
	} 
	public String getParameter(String input)
	{ 

		if(false) {

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
		return null;
	} 

	public void addTransverseContainer(TransverseContainer tCon) {
		transCon=tCon;
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


	public int nextActionPerformed(String str) {

		// First check whether a class with this name already exists.
		if((new File(System.getProperty("user.dir")+File.separator+projectName+File.separator+text_ClsName.getText().trim())).exists()) {
			JOptionPane.showMessageDialog(null,resourceBundle.getString("A class with this name is already found Please enter another name"),resourceBundle.getString("Error"),JOptionPane.ERROR_MESSAGE);
			return -1;
		}
		// With the Given data form XML Data. 
		Document doc=null;
		Element clsNode=null;
		if(transCon.getTransverseComponent("XMLMODEL")==null) {
			try {
				DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
				DocumentBuilder db=dbf.newDocumentBuilder();
				doc=db.newDocument();
				Element rootNode=doc.createElement("CLASS_INFO");
				clsNode=doc.createElement("CLASS");
				doc.appendChild(rootNode);
				rootNode.appendChild(clsNode);
				transCon.addTransverseComponent("XMLMODEL",doc);
			}
			catch(ParserConfigurationException pce) {
				pce.printStackTrace();
			}
		}
		else {
			doc=(Document)transCon.getTransverseComponent("XMLMODEL");
			clsNode=(Element)doc.getDocumentElement().getElementsByTagName("CLASS").item(0);
		}

		// Find the options that are enabled for screen invocation 
		// and  deny / grant such screens. 
		if(text_ClsName.getText().trim().length()==0) {
			JOptionPane.showMessageDialog(null,resourceBundle.getString("Please enter the class Name"),resourceBundle.getString("Error"),JOptionPane.ERROR_MESSAGE);
			return -1;
		}
		if(text_PackageName.getText().length()!=0) {
			clsNode.setAttribute("package",text_PackageName.getText().trim());
		}
		clsNode.setAttribute("name",text_ClsName.getText().trim());
		if(text_Interface.getText().trim().length()!=0) {
			clsNode.setAttribute("implements",text_Interface.getText().trim());
		}
		clsNode.setAttribute("accessModifier","public");
		clsNode.setAttribute("extends","com.adventnet.nms.topodb.ManagedObject");

		// Since Add CUstom properties is made mandatory make Screen4 mandatory.  
		AbstractTransversePanel atp=null;
		atp=(AbstractTransversePanel)transCon.getTransverseComponent("Screen4");
		atp.setShowing(true);
		clsNode.setAttribute("addCustProps","true");

		if(!chb_AdFrmDev.isSelected()) {
			atp=null;
			atp=(AbstractTransversePanel)transCon.getTransverseComponent("Screen2");
			atp.setShowing(false);
			clsNode.setAttribute("addFromDevice","false");

			Element deviceNode=(Element)doc.getElementsByTagName("DEVICE_PARAMS").item(0);
			if(deviceNode!=null) {
				doc.getDocumentElement().removeChild(deviceNode);
			}
		}
		else {
			atp=null;
			atp=(AbstractTransversePanel)transCon.getTransverseComponent("Screen2");
			atp.setShowing(true);
			clsNode.setAttribute("addFromDevice","true");
		}

		if(!chb_AdFrMangd.isSelected()) {
			atp=null;
			atp=(AbstractTransversePanel)transCon.getTransverseComponent("Screen3");
			atp.setShowing(false);
			clsNode.setAttribute("addFromManaged","false");
		}
		else {
			atp=null;
			atp=(AbstractTransversePanel)transCon.getTransverseComponent("Screen3");
			atp.setShowing(true);
			clsNode.setAttribute("addFromManaged","true");
		}

		return 1;
	}

	public int previousActionPerformed(String str) {
		return 1;
	}

	public boolean finishActionPerformed() {
		return false;
	}

	private Window getParentContainer() {
		Container con=getParent();
		for(;!(con instanceof Window);con=con.getParent());
		return (Window)con;
	}

	public void cancelActionPerformed(String str) {
		if(str.trim().equals("Screen1")) {
			int returnVal=JOptionPane.showConfirmDialog(null,resourceBundle.getString("MO Generation is not complete. Do you want to cancel ???"),resourceBundle.getString("Confirm"),JOptionPane.YES_NO_OPTION);
			if(returnVal==JOptionPane.YES_OPTION) {
				getParentContainer().dispose();
			}
		}
	}
	public void closeActionPerformed() {
	}

	public TransverseContainer getTransverseContainer() {
		return transCon;
	}

	public void disableClassNameField() {
		text_ClsName.setEnabled(false);
	}

	public void loadScreenData() {
		if(transCon.getTransverseComponent("XMLMODEL")==null) {
			return;
		}
		Document doc=(Document)transCon.getTransverseComponent("XMLMODEL");
		Element rootNode=doc.getDocumentElement();
		NodeList classNode=rootNode.getElementsByTagName("CLASS");
		rootNode=(Element)classNode.item(0);
		if(rootNode.getAttribute("name")!=null) {
			text_ClsName.setText(rootNode.getAttribute("name"));
		}
		if(rootNode.getAttribute("interface")!=null) {
			text_Interface.setText(rootNode.getAttribute("implements"));
		}

		if(rootNode.getAttribute("addFromDevice")!=null) {
			chb_AdFrmDev.setSelected(Boolean.valueOf(rootNode.getAttribute("addFromDevice")).booleanValue());
		}
		if(rootNode.getAttribute("addFromManaged")!=null) {
			chb_AdFrMangd.setSelected(Boolean.valueOf(rootNode.getAttribute("addFromManaged")).booleanValue());
		}

		/*
		   if(rootNode.getAttribute("addCustProps")!=null) {
		   chb_AdCusProp.setSelected(Boolean.valueOf(rootNode.getAttribute("addCustProps")).booleanValue());
		   } */
	}
}











