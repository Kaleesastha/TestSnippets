//$Id: MOChooser.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $
package com.adventnet.nms.tools.mowizard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.filechooser.FileFilter;
import com.adventnet.nms.tools.utils.ToolsFileFilter;
import com.adventnet.nms.tools.objtorel.ClassAnalyser;
import com.adventnet.nms.tools.objtorel.EditableList;
import java.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.net.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;
import com.adventnet.apiutils.BuilderResourceBundle;
import com.adventnet.nms.tools.utils.ToolsUtil;

public class MOChooser extends JDialog implements ActionListener
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	javax.swing.JPanel  Top = null;
	javax.swing.JPanel  listPanel = null;
	javax.swing.JButton  okButton = null;
	javax.swing.JButton  cancelButton = null;
	javax.swing.JPanel  okCancelPanel = null;
	javax.swing.JLabel  InfoLabel = null;
	javax.swing.JButton  browseButton = null;
	javax.swing.JScrollPane  listPane = null;
	javax.swing.JList  objectList = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>
	String fileName=null;
	DefaultListModel dlm=null;
	JDialog dlg=null;
	JButton ed_okButton=null;
	JTextField classname=null;
	EditableList ed1=null;
	WriteToXML xmlwrite=null;
	String classpath=System.getProperty("path.separator")+"."+File.separator+"classes";
	ClassAnalyser cnr=null;
	private BuilderResourceBundle resourceBundle=ToolsUtil.getBundle();
	public MOChooser()
	{
		//<Begin_MOChooser>
		pack();
		this.setTitle(resourceBundle.getString("MOChooser"));
		//<End_MOChooser>
	}

	public MOChooser(java.applet.Applet applet)
	{
		//<Begin_MOChooser_java.applet.Applet>
		this.applet = applet;
		pack();
		this.setTitle(resourceBundle.getString("MOChooser"));
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		//<End_MOChooser_java.applet.Applet>
	}

	public void setVisible(boolean bl)
	{
		//<Begin_setVisible_boolean>
		if(bl)
		{
			init();
			start();
		}
		else
		{
			stop();
		}
		super.setVisible(bl);
		//<End_setVisible_boolean>
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
		listPanel= new javax.swing.JPanel();
		okButton= new javax.swing.JButton();
		cancelButton= new javax.swing.JButton();
		okCancelPanel= new javax.swing.JPanel();
		InfoLabel= new javax.swing.JLabel();
		browseButton= new javax.swing.JButton();
		listPane= new javax.swing.JScrollPane();
		objectList= new javax.swing.JList();
		//<End_initVariables>
		dlm=new DefaultListModel();
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
		Top.add(listPanel,BorderLayout.SOUTH);
		listPanel.setLayout(new FlowLayout(2,10,5));
		listPanel.add(okButton);
		listPanel.add(cancelButton);
		Top.add(okCancelPanel,BorderLayout.CENTER);
		okCancelPanel.setLayout(new GridBagLayout());
		inset = new Insets(5,5,5,5);
		setConstraints(0,2,-1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
		okCancelPanel.add(InfoLabel,cons);
		inset = new Insets(5,5,5,5);
		setConstraints(2,2,0,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
		okCancelPanel.add(browseButton,cons);
		inset = new Insets(5,5,5,5);
		setConstraints(0,1,0,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
		okCancelPanel.add(listPane,cons);
		listPane.getViewport().add(objectList);
		//<End_setUpGUI_Container>
	} 
	public void setUpProperties()throws Exception
	{ 
		//<Begin_setUpProperties>
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

		try
		{
			okCancelPanel.setBorder(new javax.swing.border.LineBorder(new Color(-16764109),2));
		}
		catch(Exception ex)
		{
			showStatus(resourceBundle.getString("Exception while setting properties for bean ")+okCancelPanel,ex); 
		}
		try
		{
			InfoLabel.setText(resourceBundle.getString("<html><Font FACE=Times size=3 color=black><li>Click Browse if your ManagedObject is not listed above</li></Font></html>"));
		}
		catch(Exception ex)
		{
			showStatus(resourceBundle.getString("Exception while setting properties for bean ")+InfoLabel,ex); 
		}
		try
		{
			browseButton.setText(resourceBundle.getString("Browse"));
		}
		catch(Exception ex)
		{
			showStatus(resourceBundle.getString("Exception while setting properties for bean ")+browseButton,ex); 
		}
		try
		{
			objectList.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.LineBorder(new Color(-16777216),2),resourceBundle.getString("Existing Managed Objects"),0,0,new Font("Dialog",0,12),new Color(-16764109)));
		}
		catch(Exception ex)
		{
			showStatus(resourceBundle.getString("Exception while setting properties for bean ")+objectList,ex); 
		}
		okCancelPanel.setPreferredSize(new Dimension(okCancelPanel.getPreferredSize().width+0,okCancelPanel.getPreferredSize().height+10));
		cancelButton.setPreferredSize(new Dimension(cancelButton.getPreferredSize().width+0,cancelButton.getPreferredSize().height+1));
		okButton.setPreferredSize(new Dimension(okButton.getPreferredSize().width+32,okButton.getPreferredSize().height+0));
		//<End_setUpProperties>
		objectList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		browseButton.addActionListener(this);
		okButton.addActionListener(this);
		cancelButton.addActionListener(this);
		objectList.setModel(dlm);
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
		this.setSize(getPreferredSize().width+693,getPreferredSize().height+474); 
		setTitle("new_screen1");
		Container container = getContentPane();
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
		setTitle(resourceBundle.getString("Select a ManagedObject"));
		setLocation(getScrLoc(this));
		callXMLWrite();	
		readFromXML();
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
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==browseButton){
			browseButton_Clicked(ae);
		}
		else if(ae.getSource()==okButton){
			okButton_Clicked(ae);
		}
		else if(ae.getSource()==cancelButton){
			cancelButton_Clicked(ae);
		}
		else if(ae.getSource()==ed_okButton){
			edokButton_Clicked(ae);
		}
	}

	public void edokButton_Clicked(ActionEvent ae)
	{
		if(classname.getText().trim().length()==0) {
			JOptionPane.showMessageDialog(null,resourceBundle.getString("Enter the classname(packgename+classname)"),resourceBundle.getString("Information"),JOptionPane.OK_OPTION);
			classname.requestFocus();
			return;
		}	
		Vector vec=ed1.getList();
		URL urlArray[] = new URL[vec.size()];
		int i=0;
		String str=null;
		String temptoken=System.getProperty("user.dir");
		File f=null;
		try {
			for(i=0;i<vec.size();i++) {
				str=(String)vec.elementAt(i)+File.separator;
				if(str.startsWith(".")){
					str=str.substring(str.indexOf(".")+1);
					str=temptoken+str;
				}
				str=(new File(str)).toURL().toString();
				urlArray[i]=new URL(str);
			}
			fileName=cnr.getFullClassName(classname.getText().trim(),urlArray);
		}
		catch(MalformedURLException cnfe) {
			JOptionPane.showMessageDialog(null,resourceBundle.getString("CLASSPATH is still not complete. Please correct CLASSPATH entries"),resourceBundle.getString("Error"),JOptionPane.OK_OPTION);
			return;
		}
		catch(ClassNotFoundException cnfe) {
			JOptionPane.showMessageDialog(null,resourceBundle.getString("CLASSPATH is still not complete. Please update CLASSPATH entries"),resourceBundle.getString("Error"),JOptionPane.OK_OPTION);
			return;
		}
		catch(NoClassDefFoundError ndf) {
			JOptionPane.showMessageDialog(null,resourceBundle.getString("CLASSPATH is still not complete. Please update CLASSPATH entries"),resourceBundle.getString("Error"),JOptionPane.OK_OPTION);
			return;
		}
		catch(LinkageError le) {
			JOptionPane.showMessageDialog(null,resourceBundle.getString("CLASSPATH is still not complete. Please update CLASSPATH entries"),resourceBundle.getString("Error"),JOptionPane.OK_OPTION);
			return;
		}
		classpath=ed1.getClassPath();
		dlg.setVisible(false);
	}


	public void browseButton_Clicked(ActionEvent ae)
	{
		JFileChooser fileChoose=new JFileChooser();
		fileChoose.setCurrentDirectory(new File(System.getProperty("user.dir")));
		fileChoose.setDialogTitle("Select");
		ToolsFileFilter tff=new ToolsFileFilter();
		tff.addExtension("class");
		fileChoose.setFileFilter(tff);
		int returnVal=fileChoose.showOpenDialog(this);
		if(returnVal==JFileChooser.APPROVE_OPTION){
			fileName=fileChoose.getSelectedFile().getAbsolutePath();
			try{
				//Analyse the given class
				cnr=new ClassAnalyser();
				fileName=cnr.getFullClassName(fileName);
			}
			catch(ClassNotFoundException cnfe){
				//what if the requested class is not in the classpath.Bring up an Editable List
				StringTokenizer stk=new StringTokenizer(cnfe.getMessage(),"=");
				String title=null,mesg=null;
				if(stk.countTokens()==1) {
					JOptionPane.showMessageDialog(null,cnfe.getMessage(),resourceBundle.getString("Error Loading class"),JOptionPane.OK_OPTION);
				}
				// Shows a JDialog with an OK CANCEL Button.
				callEditableList();
			}
		}
		dlm=(DefaultListModel)objectList.getModel();
		if(dlm.indexOf(fileName)==-1){
			dlm.addElement(fileName);
		}
	}

	public void callXMLWrite()
	{
		File f=new File(System.getProperty("user.dir")+File.separator+"projects"+File.separator+"MOObjects.xml");
		if(!f.exists()){
			xmlwrite=new WriteToXML();
			xmlwrite.populate();
		}
		return;
	}

	public void okButton_Clicked(ActionEvent ae)
	{
		if(objectList.getSelectedIndex()==-1){
			JOptionPane.showMessageDialog(null,resourceBundle.getString("Please select any one of the classes in the list"),resourceBundle.getString("Error"),JOptionPane.OK_OPTION);
			return;
		}
		fileName=(String)(objectList.getSelectedValue());
		writeToXML();
		this.dispose();
	}
	public void cancelButton_Clicked(ActionEvent ae)
	{
		this.dispose();
	}

	public String getClassName()
	{
		return fileName;
	}
	public void readFromXML()
	{
		try{
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			DocumentBuilder db=dbf.newDocumentBuilder();
			Document doc=null;
			File f=new File(System.getProperty("user.dir")+File.separator+"projects"+File.separator+"MOObjects.xml");
			doc=db.parse(f);
			Element rootNode=doc.getDocumentElement();
			NodeList childNodes=rootNode.getChildNodes();
			NamedNodeMap nnm=null;
			Node child=null;

			for(int i=0;i<childNodes.getLength();i++)
			{
				if(!(childNodes.item(i).getNodeName().equals("#text"))){
					child=(Element)childNodes.item(i);
					nnm=child.getAttributes();

					Node childAttrib=nnm.item(0);
					String nodeValue=childAttrib.getNodeValue();
					dlm=(DefaultListModel)objectList.getModel();
					dlm.addElement(nodeValue);
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public Point getScrLoc(Component com)
	{
		Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
		int width=(int)(d.getWidth()-com.getSize().getWidth())/2;
		int height=(int)(d.getHeight()-com.getSize().getHeight())/2;
		return new Point(width,height);
	}

	public void callEditableList()
	{
		ed1=new EditableList("List Of ClassPath Entries");
		ToolsFileFilter tff=new ToolsFileFilter();
		tff.addExtension("jar");
		tff.addExtension("zip");
		ed1.setFileFilter(tff);
		ed1.setClasspathEditable();
		dlg=new JDialog(this,resourceBundle.getString("Update ClassPath Entries"),true);
		dlg.getContentPane().add(ed1,BorderLayout.CENTER);
		JPanel textPanel=new JPanel();
		GridBagLayout grid=new GridBagLayout();
		GridBagConstraints cons=new GridBagConstraints();
		textPanel.setLayout(grid);
		dlg.getContentPane().add(textPanel,BorderLayout.NORTH);
		classname=new JTextField();
		JLabel label=new JLabel(resourceBundle.getString("Full ClassName"));
		cons.gridx=0;
		cons.gridy=0;
		cons.fill=GridBagConstraints.NONE;
		cons.weightx=0;
		cons.insets=new Insets(2,2,2,2);
		grid.setConstraints(label,cons);
		textPanel.add(classname);

		cons.gridx=1;
		cons.gridwidth=GridBagConstraints.REMAINDER;
		cons.fill=GridBagConstraints.HORIZONTAL;
		cons.weightx=0.1;
		cons.insets=new Insets(2,2,2,2);
		grid.setConstraints(classname,cons);
		textPanel.add(label);
		
		ed_okButton=new JButton(resourceBundle.getString("OK"));
		ed_okButton.addActionListener(this);
		ed_okButton.setActionCommand("OK");
		JPanel okPanel=new JPanel();
		okPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		okPanel.add(ed_okButton);
		dlg.getContentPane().add(okPanel,BorderLayout.SOUTH);
		dlg.pack();
		dlg.setLocation(getScrLoc(dlg));
		dlg.setVisible(true);
	}

	public void writeToXML()
	{
		File f=new File(System.getProperty("user.dir")+File.separator+"projects"+File.separator+"MOObjects.xml");
		if(!f.exists()){
			return;
		}
		try{
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			DocumentBuilder db=dbf.newDocumentBuilder();
			Document doc=db.parse(f);
			if(findInFile(doc,fileName)){
				return;
			}
			Element node=doc.createElement("CLASS");
			node.setAttribute("name",fileName);
			node.setAttribute("classpath",classpath);
			doc.getDocumentElement().appendChild(node);
			updateXML(doc,f);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public boolean findInFile(Document doc,String fileName)
	{
		boolean isFound=false;
		NodeList list=doc.getDocumentElement().getElementsByTagName("CLASS");
		for(int i=0;i<list.getLength();i++)
		{
			Element child=(Element)list.item(i);
			if(child.getAttribute("name").equals(fileName)){
				isFound=true;
			}
		}
		return isFound;
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
