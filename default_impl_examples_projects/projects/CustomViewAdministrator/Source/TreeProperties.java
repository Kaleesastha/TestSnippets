//$Id: TreeProperties.java,v 1.2 2007/02/22 15:03:04 srajeswari Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory




package com.adventnet.nms.tools.CustomView;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;



public class TreeProperties extends JPanel 
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel TreeProps = null;
	javax.swing.JLabel frameTitle = null;
	javax.swing.JLabel menuFile = null;
	javax.swing.JLabel iconFile = null;
	javax.swing.JLabel treePopup = null;
	javax.swing.JLabel nodeIndex = null;
	javax.swing.JTextField frameTitleField = null;
	javax.swing.JTextField menuFileField = null;
	javax.swing.JTextField iconFileField = null;
	javax.swing.JTextField treePopupField = null;
	javax.swing.JTextField nodeIndexField = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JLabel tablePopUP = null;
	javax.swing.JTextField tablePopupField = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>
	Properties treeProps=null;
	String customType="";

  public TreeProperties()
  {
    //<Begin_TreeProperties>
    this.init();
  
    //<End_TreeProperties>
  }

  public TreeProperties(java.applet.Applet applet)
  {
    //<Begin_TreeProperties_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_TreeProperties_java.applet.Applet>
  }

public TreeProperties(String type)
{
	
  customType=type;
this.init();
}


 

  

    public void init()
  {
        //<Begin_init> 
        if (initialized == true) return; 
        setPreferredSize(new Dimension(getPreferredSize().width+526,getPreferredSize().height+462)); 
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
          showStatus("Error in init method",ex); 
        } 
        // let us set the initialzed variable to true so  
        // we dont initialize again even if init is called 
        initialized = true; 


  
        //<End_init>
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
            }
        return value;

  
           //<End_getParameter_String>
  } 
  public void setUpProperties()
  {
  //<Begin_setUpProperties> 

          try
          {
            Top.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.LineBorder(new Color(-16777216),1),"Tree Properties",1,0,new Font("Dialog",0,12),new Color(-16764109)));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+Top,ex); 
          }

          try
          {
            frameTitle.setHorizontalAlignment(2);
            frameTitle.setFont(new Font("SansSerif",0,12));
            frameTitle.setForeground(new Color(-16777216));
            frameTitle.setHorizontalTextPosition(4);
            frameTitle.setText("Frame Title");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+frameTitle,ex); 
          }

          try
          {
            menuFile.setHorizontalAlignment(2);
            menuFile.setFont(new Font("SansSerif",0,12));
            menuFile.setForeground(new Color(-16777216));
            menuFile.setHorizontalTextPosition(4);
            menuFile.setText("Menu File Name");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+menuFile,ex); 
          }

          try
          {
            iconFile.setHorizontalAlignment(2);
            iconFile.setFont(new Font("SansSerif",0,12));
            iconFile.setForeground(new Color(-16777216));
            iconFile.setHorizontalTextPosition(4);
            iconFile.setText("IconFile");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+iconFile,ex); 
          }

          try
          {
            treePopup.setHorizontalAlignment(2);
            treePopup.setFont(new Font("SansSerif",0,12));
            treePopup.setForeground(new Color(-16777216));
            treePopup.setHorizontalTextPosition(4);
            treePopup.setText("Tree Popup Menu");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+treePopup,ex); 
          }

          try
          {
            nodeIndex.setHorizontalAlignment(2);
            nodeIndex.setFont(new Font("SansSerif",0,12));
            nodeIndex.setForeground(new Color(-16777216));
            nodeIndex.setHorizontalTextPosition(4);
            nodeIndex.setText("Node Index");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+nodeIndex,ex); 
          }

          try
          {
            frameTitleField.setHorizontalAlignment(2);
            frameTitleField.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+frameTitleField,ex); 
          }

          try
          {
            menuFileField.setHorizontalAlignment(2);
            menuFileField.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+menuFileField,ex); 
          }

          try
          {
            iconFileField.setHorizontalAlignment(2);
            iconFileField.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+iconFileField,ex); 
          }

          try
          {
            treePopupField.setHorizontalAlignment(2);
            treePopupField.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+treePopupField,ex); 
          }

          try
          {
            nodeIndexField.setHorizontalAlignment(2);
            nodeIndexField.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+nodeIndexField,ex); 
          }

          try
          {
            JLabel1.setHorizontalAlignment(2);
            JLabel1.setFont(new Font("SansSerif",0,12));
            JLabel1.setForeground(new Color(-16777216));
            JLabel1.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+JLabel1,ex); 
          }

          try
          {
            tablePopUP.setHorizontalAlignment(2);
            tablePopUP.setFont(new Font("SansSerif",0,12));
            tablePopUP.setForeground(new Color(-16777216));
            tablePopUP.setHorizontalTextPosition(4);
            tablePopUP.setText("Table Popup Menu");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tablePopUP,ex); 
          }

          try
          {
            tablePopupField.setHorizontalAlignment(2);
            tablePopupField.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tablePopupField,ex); 
          }

  
          //<End_setUpProperties>
  } 
  public void start()
  {
  //<Begin_start> 

  
  //<End_start>
  } 
  public void stop()
  {
  //<Begin_stop> 

  
  //<End_stop>
  } 
  public void initVariables()
  {
        //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        TreeProps= new javax.swing.JPanel();
        frameTitle= new javax.swing.JLabel();
        menuFile= new javax.swing.JLabel();
        iconFile= new javax.swing.JLabel();
        treePopup= new javax.swing.JLabel();
        nodeIndex= new javax.swing.JLabel();
        frameTitleField= new javax.swing.JTextField();
        menuFileField= new javax.swing.JTextField();
        iconFileField= new javax.swing.JTextField();
        treePopupField= new javax.swing.JTextField();
        nodeIndexField= new javax.swing.JTextField();
        JLabel1= new javax.swing.JLabel();
        tablePopUP= new javax.swing.JLabel();
        tablePopupField= new javax.swing.JTextField();

  
        //<End_initVariables>
	if(customType.equals("TOPO CUSTOMVIEW"))
	{
			menuFileField.setText("dbmenu.xml");
			iconFileField.setText("images/ntwkdbtreeicon.png");
			treePopupField.setText("Custom Views,frameoptions.xml,TreeOperations.xml");
			
	}
	else if(customType.equals("ALERT CUSTOMVIEW"))
	{
			menuFileField.setText("alertsmenu.xml");
			iconFileField.setText("images/alarm.png");
			treePopupField.setText("Custom Views,frameoptions.xml,TreeOperations.xml");
			tablePopupField.setText("View");
	}
	else if(customType.equals("EVENT CUSTOMVIEW"))
	{
			menuFileField.setText("eventsmenu.xml");
			iconFileField.setText("images/event.png");
			treePopupField.setText("Custom Views,frameoptions.xml,TreeOperations.xml");
			tablePopupField.setText("View");
	}
	else if(customType.equals("PERFORMANCE CUSTOMVIEW"))
	{
			menuFileField.setText("pollmenu.xml");
			iconFileField.setText("images/configcollecttreeicon.png");
			treePopupField.setText("Custom Views,frameoptions.xml,TreeOperations.xml");
			tablePopupField.setText("View");
	}
  } 
  public void setUpGUI(Container container)
  {
//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));
Top.add(TreeProps,BorderLayout.CENTER);
TreeProps.setLayout(new GridBagLayout());
inset = new Insets(15,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
TreeProps.add(frameTitle,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
TreeProps.add(menuFile,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
TreeProps.add(iconFile,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,3,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
TreeProps.add(treePopup,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,4,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
TreeProps.add(nodeIndex,cons);
inset = new Insets(15,5,5,5);
setConstraints(1,0,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
TreeProps.add(frameTitleField,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,1,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
TreeProps.add(menuFileField,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,2,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
TreeProps.add(iconFileField,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,3,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
TreeProps.add(treePopupField,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,4,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
TreeProps.add(nodeIndexField,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,6,0,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
TreeProps.add(JLabel1,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,5,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
TreeProps.add(tablePopUP,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,5,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
TreeProps.add(tablePopupField,cons);

  
//<End_setUpGUI_Container>
  } 
  public void setUpConnections()
  {
  //<Begin_setUpConnections> 

  
  //<End_setUpConnections>
  } 



  
  public void showStatus(String message)
  {
     //<Begin_showStatus_String>
     System.out.println("Internal Error :"+ message);
     //<End_showStatus_String>
  }
  public void showStatus(String message,Exception ex)
  {
     //<Begin_showStatus_String_Exception>
     System.out.println("Internal Error :"+ message);
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
public void disableAll()
{
	frameTitleField.setEditable(false);
	menuFileField.setEditable(false);
	iconFileField.setEditable(false);
	treePopupField.setEditable(false);
	nodeIndexField.setEditable(false);
	tablePopupField.setEditable(false);
	
}		
	
public Properties getProperties()
{
	treeProps=new Properties();
	if(!frameTitleField.getText().trim().equals(""))
	{
		treeProps.put("FRAME-TITLE",frameTitleField.getText());
	}
	if(!menuFileField.getText().trim().equals(""))
	{
		treeProps.put("MENU-FILE-NAME",menuFileField.getText());
	}
	if(!iconFileField.getText().trim().equals(""))
	{
		treeProps.put("ICON-FILE",iconFileField.getText());
	}
	if(!treePopupField.getText().trim().equals(""))
	{
		treeProps.put("TREE-POPUP-MENU",treePopupField.getText());
	}
	if(!nodeIndexField.getText().trim().equals(""))
	{
		treeProps.put("NODE-INDEX",nodeIndexField.getText());
	}
	if(!tablePopupField.getText().trim().equals(""))
	{
		treeProps.put("TABLE-POPUP-MENU",tablePopupField.getText());
	}
	return treeProps;
}	
public void setProperties(Properties props)
{
	if(props==null)
	{
		return;
	}
	for(Enumeration enumerate=props.propertyNames();enumerate.hasMoreElements();)
	{
		String key=(String)enumerate.nextElement();
		if(key.equals("FRAME-TITLE"))
		{
			frameTitleField.setText(props.getProperty(key));
		}
		else if(key.equals("MENU-FILE-NAME"))
		{
			menuFileField.setText(props.getProperty(key));
		}
		else if(key.equals("ICON-FILE"))
		{
			iconFileField.setText(props.getProperty(key));
		}
		else if(key.equals("TREE-POPUP-MENU"))
		{
			treePopupField.setText(props.getProperty(key));
		}
		else if(key.equals("NODE-INDEX"))
		{
			nodeIndexField.setText(props.getProperty(key));
		}
		else if(key.equals("TABLE-POPUP-MENU"))
		{
			tablePopupField.setText(props.getProperty(key));
		}
		
	}
}
}













