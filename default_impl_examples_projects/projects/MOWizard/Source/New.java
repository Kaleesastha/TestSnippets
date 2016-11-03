//$Id: New.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $
package com.adventnet.nms.tools.mowizard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.w3c.dom.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import com.adventnet.apiutils.BuilderResourceBundle;
import com.adventnet.nms.tools.utils.ToolsUtil;

public class New extends JPanel implements ActionListener,FileFilter  
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	javax.swing.JPanel  Top = null;
	javax.swing.JPanel  panel_projVar = null;
	javax.swing.JTextField  text_projName = null;
	javax.swing.JLabel  label_projName = null;
	javax.swing.JLabel  label_package = null;
	javax.swing.JTextField  text_packageName = null;
	javax.swing.JPanel  panelOKCancel = null;
	javax.swing.JButton  okButton = null;
	javax.swing.JButton  cancelButton = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>
	// Custom Variable Declerations . . . . .
	//NewProj newProjType=null;	
	private boolean projTypeAdded=false;
	javax.swing.JTextArea projDesc=null;
	javax.swing.JScrollPane projDescScroller=null;
	private BuilderResourceBundle resourceBundle=ToolsUtil.getBundle();
	public New()
	{
		//<Begin_New>
		this.init();

		//<End_New>
	}

	public New(java.applet.Applet applet)
	{
		//<Begin_New_java.applet.Applet>
		this.applet = applet;
		this.init();

		//<End_New_java.applet.Applet>
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
		panel_projVar= new javax.swing.JPanel();
		text_projName= new javax.swing.JTextField();
		label_projName= new javax.swing.JLabel();
		label_package= new javax.swing.JLabel();
		text_packageName= new javax.swing.JTextField();
		panelOKCancel= new javax.swing.JPanel();
		okButton= new javax.swing.JButton();
		cancelButton= new javax.swing.JButton();

		//<End_initVariables>
		//newProjType=new NewProj();
		projDesc=new javax.swing.JTextArea();
		projDescScroller=new javax.swing.JScrollPane(projDesc);
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
		Top.setLayout(new BorderLayout(5,5));
		Top.add(panel_projVar,BorderLayout.CENTER);
		panel_projVar.setLayout(new GridBagLayout());
		inset = new Insets(10,10,10,10);
		setConstraints(1,0,2,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
		panel_projVar.add(text_projName,cons);
		inset = new Insets(10,10,10,10);
		setConstraints(0,0,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
		panel_projVar.add(label_projName,cons);
		inset = new Insets(10,10,10,10);
		setConstraints(0,1,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
		panel_projVar.add(label_package,cons);
		inset = new Insets(10,10,10,10);
		setConstraints(1,1,2,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
		panel_projVar.add(text_packageName,cons);
		
		Top.add(panelOKCancel,BorderLayout.SOUTH);
		panelOKCancel.setLayout(new FlowLayout(2,5,5));
		panelOKCancel.add(okButton);
		panelOKCancel.add(cancelButton);

		//<End_setUpGUI_Container>
		//	panel_ProjType.add(newProjType,BorderLayout.CENTER);
		inset=new Insets(5,5,5,5);
		setConstraints(0,2,3,2,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
		panel_projVar.add(projDescScroller,cons);

	} 
	public void setUpProperties()throws Exception
	{ 

		//<Begin_setUpProperties>

		try
		{
			panel_projVar.setBorder(new javax.swing.border.EtchedBorder(1));
		}
		catch(Exception ex)
		{
			showStatus(resourceBundle.getString("Exception while setting properties for bean ")+panel_projVar,ex); 
		}

		try
		{
			label_projName.setText(resourceBundle.getString("Project Name"));
			label_projName.setForeground(new Color(-16764109));
		}
		catch(Exception ex)
		{
			showStatus(resourceBundle.getString("Exception while setting properties for bean ")+label_projName,ex); 
		}

		try
		{
			label_package.setName(resourceBundle.getString("Package Name"));
			label_package.setText(resourceBundle.getString("PackageName"));
			label_package.setForeground(new Color(-16764109));
		}
		catch(Exception ex)
		{
			showStatus(resourceBundle.getString("Exception while setting properties for bean ")+label_package,ex); 
		}

		try
		{
			okButton.setText(resourceBundle.getString("OK"));
		}
		catch(Exception ex)
		{
			showStatus(resourceBundle.getString("Exception while setting properties for bean ")+okButton,ex); 
		}

		try
		{
			cancelButton.setText(resourceBundle.getString("Cancel"));
		}
		catch(Exception ex)
		{
			showStatus(resourceBundle.getString("Exception while setting properties for bean ")+cancelButton,ex); 
		}
		cancelButton.setPreferredSize(new Dimension(cancelButton.getPreferredSize().width+6,cancelButton.getPreferredSize().height+0));
		okButton.setPreferredSize(new Dimension(okButton.getPreferredSize().width+32,okButton.getPreferredSize().height+0));

		//<End_setUpProperties>
		okButton.addActionListener(this);
		cancelButton.addActionListener(this);
		//	newProjType.addProjectComponents();	

		text_projName.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent ke) {
				if((ke.getKeyChar() >='a' && ke.getKeyChar() <='z') || (ke.getKeyChar() >= '0' && ke.getKeyChar() <='9') || (ke.getKeyChar() >='A' && ke.getKeyChar()<='Z') || (ke.getKeyChar()==ke.VK_BACK_SPACE)) {
				//DO Nothing	
				}
				else {
				ke.consume();
				Toolkit.getDefaultToolkit().beep();
				}
				}
				});

		text_packageName.addKeyListener(new KeyAdapter(){
				public void keyTyped(KeyEvent ke){
				if((ke.getKeyChar() >='a' && ke.getKeyChar() <='z') || (ke.getKeyChar() >= '0' && ke.getKeyChar() <='9') || (ke.getKeyChar() >='A' && ke.getKeyChar()<='Z') || ke.getKeyChar()=='.' || ke.getKeyChar()==ke.VK_BACK_SPACE) {
				//DO Nothing	
				}
				else {
				ke.consume();
				Toolkit.getDefaultToolkit().beep();
				}
				}
				});

			
            projDescScroller.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.LineBorder(new Color(-16777216),1),resourceBundle.getString("Project Description"),1,2,new Font("Dialog",0,12),new Color(-16764109)));
		
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
	public void init()
	{ 
		if(false) {
			//<Begin_init>
			if (initialized == true) return; 
			setPreferredSize(new Dimension(getPreferredSize().width+788,getPreferredSize().height+602)); 
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
			setUpMenus();
			setUpToolBar();


			//<End_init>
		}
		if (initialized == true) return; 

		setSize(400,100); 
		Container container = this;
		container.setLayout(new BorderLayout()); 
		try 
		{
			initVariables(); 
			setUpGUI(container); 
			setUpProperties(); 
			setUpConnections(); 
			//Top.remove(panel_ProjType);
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
	} 


	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==okButton) {
			okButton_Clicked(ae);
		}
		else if(ae.getSource()==cancelButton) {
			cancelButton_Clicked(ae);
		}
	}

	private String getNmsHome() {
		return "."+File.separator;
	}

	private boolean validateProjInfo() {
		// Validate the project Name such that it is unique.
		/*
		   Step 1: Read the projects Directory. getNmsHome()+"projects"
		   Step 2: Find the number of Projects from projInfo File.
		 */
		File projDir=new File(getNmsHome()+File.separator+"projects"+File.separator);
		File projects[]=projDir.listFiles(this);
		for(int i=0;i<projects.length;i++) {
			if(projects[i].getName().equals(text_projName.getText().trim())) {
				JOptionPane.showMessageDialog(null,java.text.MessageFormat.format(resourceBundle.getString("A Project with name : {0} already exists"),new String[]{projects[i].getName()}),resourceBundle.getString("Error"),JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		return true;
	}


	private boolean writeProjectInfo() {
		// This function writes information regarding new project Files.
		if(!validateProjInfo()) {
			return false;
		}
		/*if(newProjType.getSelectedIndex()==-1) {
		  JOptionPane.showMessageDialog(null,"Please select one of proejct Types","Error",JOptionPane.ERROR_MESSAGE);
		  return false;
		  }*/

		File f=new File(getNmsHome()+File.separator+"projects"+File.separator+text_projName.getText().trim());	
		f.mkdir();
		DocumentBuilderFactory dbf=null;
		DocumentBuilder db=null;
		Document doc=null;
		try {
			dbf=DocumentBuilderFactory.newInstance();
			dbf.setNamespaceAware(true);
			db=dbf.newDocumentBuilder();
			doc=db.newDocument();
		}
		catch(ParserConfigurationException pfe) {
			pfe.printStackTrace();
			return false;
		}
		Element node;
		node=doc.createElement("project");
		doc.appendChild(node);
		Element nameNode=doc.createElement("property");
		nameNode.setAttribute("name","projectName");
		nameNode.setAttribute("value",text_projName.getText().trim());
		node.appendChild(nameNode);

		Element packageNode=doc.createElement("property");
		packageNode.setAttribute("name","package");
		packageNode.setAttribute("value",text_packageName.getText().trim());
		node.appendChild(packageNode);

		Element projTypeNode=doc.createElement("property");
		projTypeNode.setAttribute("name","projectType");
		projTypeNode.setAttribute("value",String.valueOf(1));
		node.appendChild(projTypeNode);

		Element toolNode=doc.createElement("property");
		toolNode.setAttribute("name","ToolName");
		toolNode.setAttribute("value","MOWizard");
		node.appendChild(toolNode);
		
		Element projDescNode=doc.createElement("property");
		projDescNode.setAttribute("name","ProjDesc");
		projDescNode.setAttribute("value",((projDesc.getText().trim().length()==0)?"  ":projDesc.getText().trim()));
		node.appendChild(projDescNode);
		try {
			TransformerFactory tff=TransformerFactory.newInstance();
			Transformer tf=tff.newTransformer();
			DOMSource   dSource=new DOMSource(doc);
			StreamResult sResult=new StreamResult(new File(f.toString()+".proj"));
			tf.transform(dSource,sResult);
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private Window getParentContainer() {
		Container con=getParent();
		for(;!(con instanceof Window); con=con.getParent());
		return (Window)con;
	}

	private void okButton_Clicked(ActionEvent ae) {
		// get which project Type is selected from the JPanel which is added to the left Pane of the
		// vertical Splitter.
		if(text_projName.getText().trim().length()==0){
			JOptionPane.showMessageDialog(null,resourceBundle.getString("Please enter Project Name"),resourceBundle.getString("Error"),JOptionPane.ERROR_MESSAGE);
			return;
		}

		if(text_packageName.getText().trim().length()==0){
			JOptionPane.showMessageDialog(null,resourceBundle.getString("Please enter Package Name"),resourceBundle.getString("Error"),JOptionPane.ERROR_MESSAGE);
			return;
		}

		if(text_projName.getText().trim().indexOf(" ") != -1){
			JOptionPane.showMessageDialog(null,resourceBundle.getString("Sorry!!!Please do not enter a blank space in the Project Name"),resourceBundle.getString("Error"),JOptionPane.ERROR_MESSAGE);
			return;
		}

		if(text_packageName.getText().trim().startsWith(".")){
			JOptionPane.showMessageDialog(null,resourceBundle.getString("Please do not enter Package Name starting with a dot"),resourceBundle.getString("Error"),JOptionPane.ERROR_MESSAGE);
			return;
		}

		if(text_packageName.getText().trim().endsWith(".")){
			JOptionPane.showMessageDialog(null,resourceBundle.getString("Please do not enter Package Name ending in a dot"),resourceBundle.getString("Error"),JOptionPane.ERROR_MESSAGE);
			return;
		}

		if(!writeProjectInfo()) {
			projTypeAdded=false;
		}
		else {
			projTypeAdded=true;
			getParentContainer().dispose();

		}
	}

	private void cancelButton_Clicked(ActionEvent ae) {
		// Close the container of this screen by finding out the container of this screen.
		getParentContainer().dispose();
		projTypeAdded=false;
	}

	public String addedProjectTypeInfo() {
		if(projTypeAdded) {
			return text_projName.getText().trim();
		}
		return null;
	}

	public String getPackageName(){
		if(projTypeAdded){
			return text_packageName.getText().trim();
		}
		return null;
	}
	public boolean accept(File f) {
		return f.isDirectory();
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
}

