//$Id: standardPackComp.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $
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
import com.adventnet.apiutils.BuilderResourceBundle;
import com.adventnet.nms.tools.utils.ToolsUtil;

public class standardPackComp extends AbstractTransversePanel implements TransverseListener,ActionListener
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	javax.swing.JPanel Top = null;
	javax.swing.JLabel infoLabelBot = null;
	javax.swing.JPanel panelMOList = null;
	javax.swing.JScrollPane moListScroller = null;
	javax.swing.JList moContentsList = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JLabel labelNarDir = null;
	javax.swing.JTextField textNarDir = null;
	javax.swing.JButton buttonNarDir = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>
	TransverseContainer transCon=null;
	Document doc=null;
	DefaultMutableTreeNode depNode=null;
	String projectName=null;
	DefaultListModel moConListModel=null;
	String fs=File.separator;
	JFileChooser jf=null;
	private BuilderResourceBundle resourceBundle=ToolsUtil.getBundle();
	public void start()
  { 

		//<Begin_start> 

  
  //<End_start>
	} 
	public void initVariables()
  { 

		//<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        infoLabelBot= new javax.swing.JLabel();
        panelMOList= new javax.swing.JPanel();
        moListScroller= new javax.swing.JScrollPane();
        moContentsList= new javax.swing.JList();
        JPanel1= new javax.swing.JPanel();
        labelNarDir= new javax.swing.JLabel();
        textNarDir= new javax.swing.JTextField();
        buttonNarDir= new javax.swing.JButton();
        //<End_initVariables>
  		moConListModel=new DefaultListModel();
	} 
	 
	public void setUpGUI(Container container)
  { 

		//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
	Top.setLayout(new BorderLayout(5,5));
	Top.add(infoLabelBot,BorderLayout.SOUTH);
	Top.add(panelMOList,BorderLayout.CENTER);
	panelMOList.setLayout(new BorderLayout(5,5));
	panelMOList.add(moListScroller,BorderLayout.CENTER);
	moListScroller.getViewport().add(moContentsList);
	Top.add(JPanel1,BorderLayout.NORTH);
	JPanel1.setLayout(new GridBagLayout());
	inset = new Insets(10,10,10,0);
	setConstraints(0,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
	JPanel1.add(labelNarDir,cons);
	inset = new Insets(10,10,10,10);
	setConstraints(1,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
	JPanel1.add(textNarDir,cons);
	inset = new Insets(10,0,10,10);
	setConstraints(2,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
	JPanel1.add(buttonNarDir,cons);
	
  
//<End_setUpGUI_Container>
	} 
	public void setUpProperties()
  { 

		//<Begin_setUpProperties> 

          try
          {
            infoLabelBot.setHorizontalAlignment(2);
            infoLabelBot.setVerticalTextPosition(1);
            infoLabelBot.setHorizontalTextPosition(2);
            infoLabelBot.setVerticalAlignment(1);
            infoLabelBot.setFont(new Font("Dialog",0,12));
            infoLabelBot.setForeground(new Color(-16764109));
            infoLabelBot.setText(resourceBundle.getString("<HTML><UL><LI><FONT face=Times size=-1 color=#000000 >The standard components of a ManagedObect are ManagedObject Class,Status Poller, Discovery Filter, RelationalManagedObject, Database Schema, Database Alias, relationalclasses.conf and other dependent classes. <BR><LI> <FONT face=Times size=-1 color=#0000000 >Please Click the Next Button to add dependent classes. </FONT></UL></HTML> "));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+infoLabelBot,ex); 
          }

          try
          {
            panelMOList.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.LineBorder(new Color(-16777216),1),resourceBundle.getString("Components of ManagedObject"),1,2,new Font("Dialog",0,12),new Color(-16764109)));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+panelMOList,ex); 
          }

          try
          {
            JPanel1.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.LineBorder(new Color(-16777216),1),resourceBundle.getString("Nar Destination Directory"),1,2,new Font("Dialog",0,12),new Color(-16764109)));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel1,ex); 
          }

          try
          {
            labelNarDir.setText(resourceBundle.getString("Nar Directory"));
            labelNarDir.setFont(new Font("Dialog",0,12));
            labelNarDir.setForeground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+labelNarDir,ex); 
          }

          try
          {
            buttonNarDir.setText(resourceBundle.getString("Browse"));
            buttonNarDir.setFont(new Font("Dialog",0,12));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+buttonNarDir,ex); 
          }
		moListScroller.setPreferredSize(new Dimension(moListScroller.getPreferredSize().width+52,moListScroller.getPreferredSize().height+2));
		panelMOList.setPreferredSize(new Dimension(panelMOList.getPreferredSize().width+302,panelMOList.getPreferredSize().height+209));
		infoLabelBot.setPreferredSize(new Dimension(infoLabelBot.getPreferredSize().width+0,infoLabelBot.getPreferredSize().height+42));

  
          //<End_setUpProperties>
  		moContentsList.setModel(moConListModel);
		buttonNarDir.addActionListener(this);
	} 
	public void setUpConnections()
  { 

		//<Begin_setUpConnections> 

  
  //<End_setUpConnections>
	} 
	public void stop()
  { 

		//<Begin_stop> 

  
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
            if (input.equals("PORT")) value = "161"; 
            }
        return value;

  
           //<End_getParameter_String>
	} 
	public void init()
  { 

		//<Begin_init> 
        if (initialized == true) return; 
		/*
        setPreferredSize(new Dimension(getPreferredSize().width+447,getPreferredSize().height+489)); 
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

	public standardPackComp()
  {
		//<Begin_standardPackComp>
    this.init();
  
    //<End_standardPackComp>
	}

	public standardPackComp(java.applet.Applet applet)
  {
		//<Begin_standardPackComp_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_standardPackComp_java.applet.Applet>
	}
	private String changePackToPath(String pack) {
		if(pack.trim().length()==0) {
			return "";
		}
		while(pack.indexOf(".")!=-1) {
			StringBuffer tempBuf=new StringBuffer(pack);
			tempBuf=tempBuf.replace(pack.indexOf("."),pack.indexOf(".")+1,fs);
			pack=tempBuf.toString().trim();
		}	
		return pack;
	}	

	public void loadScreenData() {
		// A document is present with this key name 
		doc=(Document)transCon.getTransverseComponent("XMLMODEL");
		depNode=(DefaultMutableTreeNode)transCon.getTransverseComponent("deployNode");
		projectName=(String)transCon.getTransverseComponent("projectName");
		// Clear the List
		moConListModel.clear();

		// First set the class Node
		Element classNode=(Element)doc.getElementsByTagName("CLASS").item(0);
		// Set the class Name
		transCon.addTransverseComponent(classNode.getAttribute("name")+".class","."+fs+"projects"+depNode.getParent()+fs+"classes"+fs+changePackToPath(classNode.getAttribute("package"))+fs+classNode.getAttribute("name")+".class");
		moConListModel.addElement(classNode.getAttribute("name")+".class");
		Element userTester=(Element)doc.getElementsByTagName("USER_TESTER").item(0);
		if(userTester.getAttribute("fileName").trim().length()!=0) {
			String className=userTester.getAttribute("className");
			if(className.lastIndexOf(".")!=-1) {
				className=className.substring(className.lastIndexOf(".")+1);
			}
			moConListModel.addElement(className+".class");
			transCon.addTransverseComponent(className+".class","."+fs+"projects"+fs+depNode.getParent().getParent().toString()+fs+depNode.getParent().toString()+fs+"classes"+fs+changePackToPath(userTester.getAttribute("className"))+".class");
		}
		Element discFilter=(Element)doc.getElementsByTagName("DISC_FILTER").item(0);
		if(discFilter.getAttribute("fileName").trim().length()!=0) {
			String className=discFilter.getAttribute("className");
			if(className.lastIndexOf(".")!=-1) {
				className=className.substring(className.lastIndexOf(".")+1);
			}
			moConListModel.addElement(className+".class");
			transCon.addTransverseComponent(className+".class","."+fs+"projects"+fs+projectName+fs+depNode.getParent().toString()+fs+"classes"+fs+changePackToPath(discFilter.getAttribute("className"))+".class");
		}
		Element relJava=(Element)doc.getElementsByTagName("RELATIONAL_JAVA").item(0);
		if(relJava.getAttribute("fileName").trim().length()!=0) {
			String className=relJava.getAttribute("className");
			if(className.lastIndexOf(".")!=-1) {
				className=className.substring(className.lastIndexOf(".")+1);
			}
			moConListModel.addElement(className+".class");
			transCon.addTransverseComponent(className+".class","."+fs+"projects"+fs+projectName+fs+depNode.getParent().toString()+fs+"classes"+fs+changePackToPath(relJava.getAttribute("className"))+".class");
			moConListModel.addElement(classNode.getAttribute("name")+"Schema.conf");
			Element aliasSchemaNode=(Element)doc.getElementsByTagName("ALIAS_SCHEMA").item(0);
			transCon.addTransverseComponent(classNode.getAttribute("name")+"Schema.conf",aliasSchemaNode.getAttribute("schemaFilePath"));
			moConListModel.addElement(classNode.getAttribute("name")+"Alias.conf");
			transCon.addTransverseComponent(classNode.getAttribute("name")+"Alias.conf",aliasSchemaNode.getAttribute("aliasFilePath"));
			transCon.addTransverseComponent("relclassesPath",aliasSchemaNode.getAttribute("relclassesPath"));
			//Add Code for relationalclasses.conf
		}	
	}

	public void actionPerformed(ActionEvent ae) {
		if(jf==null) {
			jf=new JFileChooser();
			jf.setCurrentDirectory(new File("."));
			jf.setDialogTitle(resourceBundle.getString("Nar File Destination Directory"));
			jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		}
		if(jf.showOpenDialog(this)==JFileChooser.APPROVE_OPTION) {
			textNarDir.setText(jf.getSelectedFile().toString());
		}
	}
	
	public int nextActionPerformed(String str)
	{
		if(textNarDir.getText().length()==0) {
			JOptionPane.showMessageDialog(null,resourceBundle.getString("Please enter the Destination Path for the Nar"),resourceBundle.getString("Error"),JOptionPane.ERROR_MESSAGE);
			return -1;
		}
		if((new File(textNarDir.getText().trim())).exists()==false) {
			JOptionPane.showMessageDialog(null,java.text.MessageFormat.format(resourceBundle.getString("Directory : {0} does Not exist"),new String[]{textNarDir.getText()}),resourceBundle.getString("Error"),JOptionPane.ERROR_MESSAGE);
			return -1;
		}
		if(transCon.containsKey("NARPATH")) {
			transCon.removeTransverseComponent("NARPATH");
		}
		transCon.addTransverseComponent("NARPATH",textNarDir.getText().trim());
		return 1;
	}
	public int previousActionPerformed(String str)
	{
		return 0;
	}

	public boolean finishActionPerformed()
	{
		return false;
	}

	public String getNmsHome() {
		return "."+File.separator;
	}

	public void cancelActionPerformed(String str)
	{
		if(str.trim().equals("Screen1")) {
			int returnVal=JOptionPane.showConfirmDialog(null,resourceBundle.getString("ManagedObject deployment is not complete. Do you want to cancel ???"),resourceBundle.getString("Confirm"),JOptionPane.YES_NO_OPTION);
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

	public void addTransverseContainer(TransverseContainer tCon) {
		transCon=tCon;
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
}


