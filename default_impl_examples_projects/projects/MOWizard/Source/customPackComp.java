//$Id: customPackComp.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $
package com.adventnet.nms.tools.mowizard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import com.adventnet.nms.tools.objtorel.EditableList;
import com.adventnet.nms.tools.utils.ToolsFileFilter;
import com.adventnet.nms.tools.objtorel.TransverseContainer;
import com.adventnet.nms.tools.objtorel.TransverseListener;
import com.adventnet.apiutils.BuilderResourceBundle;
import com.adventnet.nms.tools.utils.ToolsUtil;


public class customPackComp extends AbstractTransversePanel implements TransverseListener 
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	javax.swing.JPanel  Top = null;
	javax.swing.JLabel  labelSpace = null;
	javax.swing.JPanel  panelEditabList = null;
	//<End_Variable_Declarations>
	EditableList edl=null;
	TransverseContainer transCon=null;
	private BuilderResourceBundle resourceBundle=ToolsUtil.getBundle();

	public customPackComp()
  {
		//<Begin_customPackComp>
    this.init();
  
    //<End_customPackComp>
	}

	public customPackComp(java.applet.Applet applet)
  {
		//<Begin_customPackComp_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_customPackComp_java.applet.Applet>
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
        labelSpace= new javax.swing.JLabel();
        panelEditabList= new javax.swing.JPanel();

  //<End_initVariables>
		edl=new EditableList(resourceBundle.getString("Select Dependent components"));
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
	Top.add(labelSpace,BorderLayout.SOUTH);
	Top.add(panelEditabList,BorderLayout.CENTER);
	panelEditabList.setLayout(new BorderLayout(5,5));
	
  //<End_setUpGUI_Container>
		panelEditabList.add(edl,BorderLayout.CENTER);
	} 
	public void setUpProperties()throws Exception
  { 
		//<Begin_setUpProperties>

          try
          {
            panelEditabList.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.LineBorder(new Color(-16777216),1),resourceBundle.getString("Additional Components"),1,2,new Font("Dialog",0,12),new Color(-16764109)));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+panelEditabList,ex); 
          }
		panelEditabList.setPreferredSize(new Dimension(panelEditabList.getPreferredSize().width+400,panelEditabList.getPreferredSize().height+335));
		labelSpace.setPreferredSize(new Dimension(labelSpace.getPreferredSize().width+659,labelSpace.getPreferredSize().height+80));

  //<End_setUpProperties>
		edl.setClasspathEditable();
		ToolsFileFilter tff=new ToolsFileFilter();
		tff.addExtension("jar");
		tff.addExtension("zip");
		edl.setFileFilter(tff);
		edl.setFileSelectionMode(JFileChooser.FILES_ONLY);
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
		/*
        setPreferredSize(new Dimension(getPreferredSize().width+476,getPreferredSize().height+489)); 
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
		
	}
	public int nextActionPerformed(String str)
	{
		// Just reset the support Archive entry with the latest entry.
		transCon.removeTransverseComponent("addOnArchives");
		if(edl.getClassPath().trim().length()!=0) {
			transCon.addTransverseComponent("addOnArchives",edl.getClassPath());
		}
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
		if(str.trim().equals("Screen2")) {
			int returnVal=JOptionPane.showConfirmDialog(null,resourceBundle.getString("ManagedObject Deployment is not complete. Do you want to cancel ???"),resourceBundle.getString("Confirm"),JOptionPane.YES_NO_OPTION);
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

}


