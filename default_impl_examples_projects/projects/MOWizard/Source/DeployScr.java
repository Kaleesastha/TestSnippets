//$Id: DeployScr.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $
package com.adventnet.nms.tools.mowizard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import com.adventnet.apiutils.BuilderResourceBundle;
import com.adventnet.nms.tools.utils.ToolsUtil;


public class DeployScr extends JPanel 
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	javax.swing.JPanel Top = null;
	javax.swing.JLabel JLabel1 = null;
	//<End_Variable_Declarations>
	private BuilderResourceBundle resourceBundle=ToolsUtil.getBundle();

	public void init()
	{
		//<Begin_init> 
		if (initialized == true) return; 
		setPreferredSize(new Dimension(getPreferredSize().width+725,getPreferredSize().height+487)); 
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
	public void setUpProperties()
	{
		//<Begin_setUpProperties> 

		try
		{
			JLabel1.setVerticalAlignment(1);
			JLabel1.setVerticalTextPosition(1);
			JLabel1.setHorizontalTextPosition(2);
			JLabel1.setText(resourceBundle.getString("<HTML><H3 align=left><font color=black>ManagedObject Deployment</font></H3><UL><LI><P><FONT face=courier color=black> When a MangagedObject is defined using MOWizard it has to be packaged as a nar(<B>N</B>ms <B>AR</B>chive).  If a Status Poller and a Discovery Filter are defined for this ManagedObject they have to packaged with this nar. Database support for a ManagedObject is provided by Relationalxxx object (where xxx is the ManagedObject).  When a Relationalxxx is generated and compiled we get the corresponding database schema,and other files (databasealias.conf and relationalclasses.conf) which are used by AdventNet WebNMS for providing database support.  These files will be integrated into NMS when we install a nar using Deployment Wizard.<BR> </font></p><LI><P><font face=courier color=black>Please click on the Deploy button to package this ManagedObject.</font></P></HTML>"));
		}
		catch(Exception ex)
		{
			showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel1,ex); 
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
		JLabel1= new javax.swing.JLabel();


		//<End_initVariables>
	} 
	public void setUpGUI(Container container)
	{
		//<Begin_setUpGUI_Container> 
		container.add(Top,BorderLayout.CENTER);
		Top.setLayout(new GridLayout(1,0,5,5));
		Top.add(JLabel1);


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






	public DeployScr()
	{
		//<Begin_DeployScr>
		this.init();

		//<End_DeployScr>
	}

	public DeployScr(java.applet.Applet applet)
	{
		//<Begin_DeployScr_java.applet.Applet>
		this.applet = applet;
		this.init();

		//<End_DeployScr_java.applet.Applet>
	}
}


