//$Id: RelJavaProp.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $
package com.adventnet.nms.tools.mowizard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.table.*;
import javax.swing.tree.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import com.adventnet.editor.text.*;
import com.adventnet.apiutils.BuilderResourceBundle;
import com.adventnet.nms.tools.utils.ToolsUtil;

public class RelJavaProp extends JPanel implements ActionListener
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	javax.swing.JPanel  Top = null;
	javax.swing.JPanel  panelClsMO = null;
	javax.swing.JLabel  labelMOName = null;
	javax.swing.JLabel  labelRelClsName = null;
	javax.swing.JLabel  labelRelClsNameVal = null;
	javax.swing.JLabel  labelMONameVal = null;
	javax.swing.JScrollPane  propertyScroller = null;
	javax.swing.JTable  propertyTable = null;
	javax.swing.JPanel  panelSchema = null;
	javax.swing.JLabel  labelSchema = null;
	javax.swing.JButton  butSchemaView = null;
	javax.swing.JPanel  panelAlias = null;
	javax.swing.JLabel  labelAlias = null;
	javax.swing.JButton  labelAliasView = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>
	private BuilderResourceBundle resourceBundle=ToolsUtil.getBundle();
	Object tableHdr[] = {resourceBundle.getString("Serial Number"),resourceBundle.getString("Property Name"),resourceBundle.getString("Alias Name"),resourceBundle.getString("Storage Type"),resourceBundle.getString("Storage Size"),resourceBundle.getString("Primary Key" )};
	DefaultMutableTreeNode relJavaNode=null;
	String schemaFilePath=null;
	String aliasFilePath=null;
	JDialog fShowDialog=null;
	JavaTextPane fileShowText=null;
	JButton   okButton=null;
	JScrollPane textScroller=null;
	JPanel      okPanel=null;




	public RelJavaProp()
	{
		//<Begin_RelJavaProp>
		this.init();

		//<End_RelJavaProp>
	}

	public RelJavaProp(java.applet.Applet applet)
	{
		//<Begin_RelJavaProp_java.applet.Applet>
		this.applet = applet;
		this.init();

		//<End_RelJavaProp_java.applet.Applet>
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
		panelClsMO= new javax.swing.JPanel();
		labelMOName= new javax.swing.JLabel();
		labelRelClsName= new javax.swing.JLabel();
		labelRelClsNameVal= new javax.swing.JLabel();
		labelMONameVal= new javax.swing.JLabel();
		propertyScroller= new javax.swing.JScrollPane();
		propertyTable= new javax.swing.JTable();
		panelSchema= new javax.swing.JPanel();
		labelSchema= new javax.swing.JLabel();
		butSchemaView= new javax.swing.JButton();
		panelAlias= new javax.swing.JPanel();
		labelAlias= new javax.swing.JLabel();
		labelAliasView= new javax.swing.JButton();

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
		inset = new Insets(10,10,0,10);
		setConstraints(0,0,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
		Top.add(panelClsMO,cons);
		panelClsMO.setLayout(new GridBagLayout());
		inset = new Insets(0,10,10,10);
		setConstraints(0,1,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
		panelClsMO.add(labelMOName,cons);
		inset = new Insets(10,10,0,10);
		setConstraints(0,0,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
		panelClsMO.add(labelRelClsName,cons);
		inset = new Insets(10,10,0,10);
		setConstraints(1,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
		panelClsMO.add(labelRelClsNameVal,cons);
		inset = new Insets(0,10,10,10);
		setConstraints(1,1,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
		panelClsMO.add(labelMONameVal,cons);
		inset = new Insets(0,10,0,10);
		setConstraints(0,1,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
		Top.add(propertyScroller,cons);
		propertyScroller.getViewport().add(propertyTable);
		inset = new Insets(0,10,0,10);
		setConstraints(0,2,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
		Top.add(panelSchema,cons);
		panelSchema.setLayout(new GridBagLayout());
		inset = new Insets(10,10,10,10);
		setConstraints(0,0,1,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
		panelSchema.add(labelSchema,cons);
		inset = new Insets(10,10,10,10);
		setConstraints(1,0,1,1,0.0,0.0,cons.SOUTH,cons.NONE,inset,0,0);
		panelSchema.add(butSchemaView,cons);
		inset = new Insets(0,10,10,10);
		setConstraints(0,3,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
		Top.add(panelAlias,cons);
		panelAlias.setLayout(new GridBagLayout());
		inset = new Insets(10,10,10,10);
		setConstraints(0,0,1,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
		panelAlias.add(labelAlias,cons);
		inset = new Insets(10,10,10,10);
		setConstraints(1,0,1,1,0.0,0.0,cons.SOUTH,cons.NONE,inset,0,0);
		panelAlias.add(labelAliasView,cons);

		//<End_setUpGUI_Container>
	} 
	public void setUpProperties()throws Exception
	{ 

		//<Begin_setUpProperties>

		try
		{
			panelClsMO.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.LineBorder(new Color(-16777216),1),resourceBundle.getString("RelationalObject Properties"),1,2,new Font("Dialog",0,12),new Color(-16764109)));
		}
		catch(Exception ex)
		{
			showStatus(resourceBundle.getString("Exception while setting properties for bean ")+panelClsMO,ex); 
		}

		try
		{
			labelMOName.setFont(new Font("Dialog",0,12));
			labelMOName.setForeground(new Color(-16764109));
			labelMOName.setText(resourceBundle.getString("ManagedObject Name"));
		}
		catch(Exception ex)
		{
			showStatus(resourceBundle.getString("Exception while setting properties for bean ")+labelMOName,ex); 
		}

		try
		{
			labelRelClsName.setText(resourceBundle.getString("Class name"));
			labelRelClsName.setFont(new Font("Dialog",0,12));
			labelRelClsName.setForeground(new Color(-16764109));
		}
		catch(Exception ex)
		{
			showStatus(resourceBundle.getString("Exception while setting properties for bean ")+labelRelClsName,ex); 
		}

		try
		{
			panelSchema.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.LineBorder(new Color(-16777216),1),resourceBundle.getString("Database Schema details"),1,2,new Font("Dialog",0,12),new Color(-16764109)));
		}
		catch(Exception ex)
		{
			showStatus(resourceBundle.getString("Exception while setting properties for bean ")+panelSchema,ex); 
		}

		try
		{
			labelSchema.setText(resourceBundle.getString("Please click the View button for Database Schema"));
			labelSchema.setFont(new Font("Dialog",0,12));
			labelSchema.setForeground(new Color(-16764109));
		}
		catch(Exception ex)
		{
			showStatus(resourceBundle.getString("Exception while setting properties for bean ")+labelSchema,ex); 
		}

		try
		{
			butSchemaView.setFont(new Font("Dialog",0,12));
			butSchemaView.setText(resourceBundle.getString("View"));
		}
		catch(Exception ex)
		{
			showStatus(resourceBundle.getString("Exception while setting properties for bean ")+butSchemaView,ex); 
		}

		try
		{
			panelAlias.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.LineBorder(new Color(-16777216),1),resourceBundle.getString("Database Alias details"),1,2,new Font("Dialog",0,12),new Color(-16764109)));
		}
		catch(Exception ex)
		{
			showStatus(resourceBundle.getString("Exception while setting properties for bean ")+panelAlias,ex); 
		}

		try
		{
			labelAlias.setFont(new Font("Dialog",0,12));
			labelAlias.setForeground(new Color(-16764109));
			labelAlias.setText(resourceBundle.getString("Please click the View button for Database Aliases"));
		}
		catch(Exception ex)
		{
			showStatus(resourceBundle.getString("Exception while setting properties for bean ")+labelAlias,ex); 
		}

		try
		{
			labelAliasView.setText(resourceBundle.getString("View"));
			labelAliasView.setFont(new Font("Dialog",0,12));
		}
		catch(Exception ex)
		{
			showStatus(resourceBundle.getString("Exception while setting properties for bean ")+labelAliasView,ex); 
		}

		//<End_setUpProperties>
		CustomPropTableModel cptm=new CustomPropTableModel(tableHdr,0);
		propertyTable.setModel(cptm);
		butSchemaView.addActionListener(this);
		labelAliasView.addActionListener(this);
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
		setPreferredSize(new Dimension(getPreferredSize().width+700,getPreferredSize().height+493)); 
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

	public void setRelationalJavaProperties(Document doc) {
		if(doc==null) {
			return;
		}
		Element tempNode=(Element)doc.getElementsByTagName("RELATIONAL_JAVA").item(0);
		if(tempNode!=null) {
			labelRelClsNameVal.setText(":  "+tempNode.getAttribute("className"));
			tempNode=(Element)doc.getElementsByTagName("CLASS").item(0);
			labelMONameVal.setText(":  "+tempNode.getAttribute("package")+"."+tempNode.getAttribute("name"));
		}
		else {
			labelRelClsNameVal.setText(resourceBundle.getString(":  Not Defined"));
			labelMONameVal.setText(resourceBundle.getString(":  Not Defined"));
		}
		// Remove the already existing nodes 
		for(int i=propertyTable.getRowCount()-1;i>0;i--) {
			((DefaultTableModel)propertyTable.getModel()).removeRow(i);
		}
		NodeList propList=doc.getElementsByTagName("PROPS");
		for(int i=0;i<propList.getLength();i++) {
			tempNode=(Element)propList.item(i);
			Object data[] = { new Integer(i+1),
				((tempNode.getAttribute("name")==null)? " ":tempNode.getAttribute("name")),
				((tempNode.getAttribute("aliasName")==null)? " ":tempNode.getAttribute("aliasName")),
				((tempNode.getAttribute("sType")==null)? " ":tempNode.getAttribute("sType")),
				((tempNode.getAttribute("sSize")==null)? " ":tempNode.getAttribute("sSize")),
				((tempNode.getAttribute("key")==null)? " ":tempNode.getAttribute("key")) };
				((CustomPropTableModel)propertyTable.getModel()).addRow(data);
		}
		// Set the appropriate properties for show Schema
		tempNode=(Element)doc.getElementsByTagName("ALIAS_SCHEMA").item(0);
		if(tempNode==null) {
			return;
		}
		schemaFilePath=tempNode.getAttribute("schemaFilePath");
		aliasFilePath=tempNode.getAttribute("aliasFilePath");
		// Set a property as aliasFilePath  schemaFilePath
		// set the appropriate properties for showing Aliases

	}

	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==labelAliasView) {
			butAliasView_Clicked(ae);
		}
		else if(ae.getSource()==butSchemaView) {
			butSchemaView_Clicked(ae);
		}
	}

	void butAliasView_Clicked(ActionEvent ae) {
		if(aliasFilePath==null || aliasFilePath.trim().length()==0) {
			return;
		}
		File f=new File(aliasFilePath);
		if(!f.exists()) {
			JOptionPane.showMessageDialog(null,resourceBundle.getString("Alias not defined"),resourceBundle.getString("Message"),JOptionPane.OK_OPTION);
			// By some reason if the file is not present
			return;
		}
		int fileLength=(int)f.length();
		char aliasBuff[]=new char[fileLength];
		try {
			FileReader fRead=new FileReader(f.toString());
			fRead.read(aliasBuff,0,fileLength);
		}catch(Exception e) {e.printStackTrace(); }	
		fileShowText=new JavaTextPane();
		fileShowText.setEditable(false);
		fileShowText.setText(new String(aliasBuff));
		textScroller=new JScrollPane(fileShowText);
		fShowDialog=new JDialog();
		fShowDialog.setTitle(java.text.MessageFormat.format(resourceBundle.getString("Database Alias for {0}"),new String[]{labelMONameVal.getText()}));
		okButton=new JButton(resourceBundle.getString("OK"));
		okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
				fShowDialog.dispose();
				}
				});
		fShowDialog.getContentPane().add(textScroller,BorderLayout.CENTER);
		okPanel=new JPanel();
		okPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		okPanel.add(okButton);
		fShowDialog.getContentPane().add(okPanel,BorderLayout.SOUTH);
		fShowDialog.setSize(600,400);
		fShowDialog.setLocation(getScrLoc(fShowDialog));
		fShowDialog.setVisible(true);
	}


	void butSchemaView_Clicked(ActionEvent ae) {
		if(schemaFilePath==null || schemaFilePath.trim().length()==0) {
			return;
		}
		File f=new File(schemaFilePath);
		if(!f.exists()) {
			JOptionPane.showMessageDialog(null,resourceBundle.getString("Schema not defined"),resourceBundle.getString("Message"),JOptionPane.OK_OPTION);
			// By some reason if the file is not present
			return;
		}
		int fileLength=(int)f.length();
		char schemaBuff[]=new char[fileLength];
		try {
			FileReader fRead=new FileReader(f);
			fRead.read(schemaBuff,0,fileLength);
		}catch(Exception e) { e.printStackTrace(); }
		fileShowText=new JavaTextPane();
		fileShowText.setEditable(false);
		fileShowText.setText(new String(schemaBuff));
		textScroller=new JScrollPane(fileShowText);
		fShowDialog=new JDialog();
		fShowDialog.setTitle(java.text.MessageFormat.format(resourceBundle.getString("Database Schema for {0}"),new String[]{labelMONameVal.getText()}));
		okButton=new JButton(resourceBundle.getString("OK"));
		okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
				fShowDialog.dispose();
				}
				});
		fShowDialog.getContentPane().add(textScroller,BorderLayout.CENTER);
		okPanel=new JPanel();
		okPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		okPanel.add(okButton);
		fShowDialog.getContentPane().add(okPanel,BorderLayout.SOUTH);
		fShowDialog.setSize(600,400);
		fShowDialog.setLocation(getScrLoc(fShowDialog));
		fShowDialog.setVisible(true);
	}

	public Point getScrLoc(Component comp) {
		// This method will return the x,y that will place a container in the center 
		// of the screen. 
		Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
		int width=(int)(d.getWidth()-comp.getSize().getWidth())/2;
		int height=(int)(d.getHeight()-comp.getSize().getHeight())/2;
		return new Point(width,height);
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

