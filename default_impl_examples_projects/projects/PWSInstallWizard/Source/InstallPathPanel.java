
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// Kindly refer to tcats_edit_source_files.html file under help/source_generation directory for details

/*$Id: InstallPathPanel.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $*/
 
//<Begin_Version>
//version$10
//<End_Version>

package com.adventnet.studio.framework.installer;




import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import com.adventnet.builder.utils.guilogic.Browse;
import com.adventnet.builder.utils.file.BuilderFileFilter;



public class InstallPathPanel extends JPanel
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JLabel pathLabel = null;
	javax.swing.JTextField pathField = null;
	javax.swing.JButton pathBtn = null;
	javax.swing.JLabel narFileLabel = null;
	javax.swing.JTextField narFileField = null;
	javax.swing.JButton narPathButton = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>

	Browse narFileBrowse;
	Browse installPathBrowse;

  public InstallPathPanel()
  {
    //<Begin_InstallPathPanel>
    this.init();
  
    //<End_InstallPathPanel>
  }

  public InstallPathPanel(java.applet.Applet applet)
  {
    //<Begin_InstallPathPanel_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_InstallPathPanel_java.applet.Applet>
  }

  

    public void init()
  {
        //<Begin_init> 
        if (initialized == true) return; 
        setPreferredSize(new Dimension(getPreferredSize().width+614,getPreferredSize().height+387)); 
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
        // let us set the initialized variable to true so  
        // we dont initialize again even if init is called 
        initialized = true; 

  
        //<End_init>
	
	narFileBrowse.setBrowseBttn(narPathButton);
	narFileBrowse.setFileTxtFld(narFileField);
	narFileBrowse.setTitle("Select NAR file to install");
	narFileBrowse.setFileFilters(new BuilderFileFilter[]{new BuilderFileFilter("nar","NMS Archive Files(*.nar)")});
	installPathBrowse.setBrowseBttn(pathBtn);
	installPathBrowse.setFileTxtFld(pathField);
	installPathBrowse.setTitle("Select installation path");
	installPathBrowse.setSelectionMode(JFileChooser.DIRECTORIES_ONLY);

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
  public void stop()
  {
       //<Begin_stop> 
       if(!running)
 return;
 running=false;

  
       //<End_stop>
  } 
  public void start()
  {
       //<Begin_start> 
       if(running)
 return;
 running=true;

  
       //<End_start>
  } 
  public void setProperties(java.util.Properties props)
  {
         //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  } 
   
  public void setUpProperties()
  {
  //<Begin_setUpProperties> 

          try
          {
            pathLabel.setHorizontalAlignment(2);
            pathLabel.setFont(new Font("SansSerif",0,12));
            pathLabel.setForeground(new Color(-16777216));
            pathLabel.setHorizontalTextPosition(4);
            pathLabel.setText("Select installation location");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+pathLabel,ex); 
          }

//<UserCode_Begin_Bean_pathLabel>

//<UserCode_End_Bean_pathLabel>

          try
          {
            pathField.setHorizontalAlignment(2);
            pathField.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+pathField,ex); 
          }

//<UserCode_Begin_Bean_pathField>

//<UserCode_End_Bean_pathField>

          try
          {
            pathBtn.setFont(new Font("SansSerif",0,12));
            pathBtn.setHorizontalTextPosition(4);
            pathBtn.setText("Browse");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+pathBtn,ex); 
          }

//<UserCode_Begin_Bean_pathBtn>

//<UserCode_End_Bean_pathBtn>

          try
          {
            narFileLabel.setHorizontalAlignment(2);
            narFileLabel.setFont(new Font("SansSerif",0,12));
            narFileLabel.setForeground(new Color(-16777216));
            narFileLabel.setHorizontalTextPosition(4);
            narFileLabel.setText("Select NAR ");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+narFileLabel,ex); 
          }

//<UserCode_Begin_Bean_narFileLabel>

//<UserCode_End_Bean_narFileLabel>

          try
          {
            narFileField.setHorizontalAlignment(2);
            narFileField.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+narFileField,ex); 
          }

//<UserCode_Begin_Bean_narFileField>

//<UserCode_End_Bean_narFileField>

          try
          {
            narPathButton.setFont(new Font("SansSerif",0,12));
            narPathButton.setHorizontalTextPosition(4);
            narPathButton.setText("Browse");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+narPathButton,ex); 
          }

//<UserCode_Begin_Bean_narPathButton>

//<UserCode_End_Bean_narPathButton>

  
          //<End_setUpProperties>
  } 
  public void initVariables()
  {
        //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        pathLabel= new javax.swing.JLabel();
        pathField= new javax.swing.JTextField();
        pathBtn= new javax.swing.JButton();
        narFileLabel= new javax.swing.JLabel();
        narFileField= new javax.swing.JTextField();
        narPathButton= new javax.swing.JButton();

  
        //<End_initVariables>
	narFileBrowse = new Browse("NarFile");
	installPathBrowse = new Browse("InstallPath");
  } 
   
   
   
   
  public void setUpGUI(Container container)
  {
//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new GridBagLayout());
inset = new Insets(0,5,5,0);
setConstraints(0,2,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
Top.add(pathLabel,cons);
inset = new Insets(0,5,0,0);
setConstraints(0,3,1,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
Top.add(pathField,cons);
inset = new Insets(0,5,0,5);
setConstraints(1,3,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
Top.add(pathBtn,cons);
inset = new Insets(0,5,5,0);
setConstraints(0,0,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
Top.add(narFileLabel,cons);
inset = new Insets(0,5,5,0);
setConstraints(0,1,1,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
Top.add(narFileField,cons);
inset = new Insets(0,5,5,5);
setConstraints(1,1,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
Top.add(narPathButton,cons);

  
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
  
  String getNarFile()
  {
       return narFileField.getText();
  }
  
  String getInstallPath()
  {
       return pathField.getText();
  }
}








