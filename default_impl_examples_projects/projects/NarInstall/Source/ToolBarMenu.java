


/**
 * $Id:
 */

// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory


//<Begin_Version>
//version$1
//<End_Version>
package com.adventnet.nms.tools.nar;



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import com.adventnet.nms.tools.utils.FilePreviewer;
import java.util.*;
import java.net.URL;
import com.adventnet.nms.tools.utils.ImgConv;


public class ToolBarMenu extends JPanel implements ItemListener,ActionListener
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "DeploymentWizard";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JLabel configureToolBar = null;
	javax.swing.JCheckBox checkToolBar = null;
	javax.swing.JLabel toolbarName = null;
	javax.swing.JLabel icon = null;
	javax.swing.JTextField toolbarNameField = null;
	javax.swing.JTextField iconField = null;
	javax.swing.JButton browse = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JLabel tooltip = null;
	javax.swing.JTextField tooltipField = null;
	javax.swing.JLabel JLabel2 = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>

    Vector toolbarInformation;
  
  
  














   


  public ToolBarMenu()
  {
    //<Begin_ToolBarMenu>
    this.init();
  
    //<End_ToolBarMenu>
  }

  public ToolBarMenu(java.applet.Applet applet)
  {
    //<Begin_ToolBarMenu_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_ToolBarMenu_java.applet.Applet>
  }

 
    public void start()
  { 

  //<Begin_start> 
       if(running)
 return;
 running=true;

  
       //<End_start>
  } 
  public void initVariables()
  { 

  //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        JPanel2= new javax.swing.JPanel();
        JPanel1= new javax.swing.JPanel();
        configureToolBar= new javax.swing.JLabel();
        checkToolBar= new javax.swing.JCheckBox();
        toolbarName= new javax.swing.JLabel();
        icon= new javax.swing.JLabel();
        toolbarNameField= new javax.swing.JTextField();
        iconField= new javax.swing.JTextField();
        browse= new javax.swing.JButton();
        JLabel1= new javax.swing.JLabel();
        tooltip= new javax.swing.JLabel();
        tooltipField= new javax.swing.JTextField();
        JLabel2= new javax.swing.JLabel();

  
        //<End_initVariables>
        browse.addActionListener(this);
        browse.setActionCommand("browse");
		disableAll();
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
   
  public void setUpGUI(Container container)
  { 

  //<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));
Top.add(JPanel2,BorderLayout.CENTER);
JPanel2.setLayout(new BorderLayout(5,5));
JPanel2.add(JPanel1,BorderLayout.CENTER);
JPanel1.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel1.add(configureToolBar,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel1.add(checkToolBar,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel1.add(toolbarName,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel1.add(icon,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,1,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel1.add(toolbarNameField,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,2,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel1.add(iconField,cons);
inset = new Insets(5,5,5,5);
setConstraints(2,2,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel1.add(browse,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,4,0,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel1.add(JLabel1,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,3,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel1.add(tooltip,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,3,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel1.add(tooltipField,cons);
Top.add(JLabel2,BorderLayout.WEST);

  
//<End_setUpGUI_Container>
		checkToolBar.addItemListener(this);
  } 
  public void setUpProperties()
  { 

  //<Begin_setUpProperties> 

          try
          {
            Top.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.LineBorder(new Color(-16777216),1),resourceBundle.getString("ToolBar Menu Information"),2,0,new Font("Dialog",0,12),new Color(-16777216)));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+Top,ex); 
          }

//<UserCode_Begin_Bean_components>

//<UserCode_End_Bean_components>

          try
          {
            JPanel2.setBorder(new javax.swing.border.BevelBorder(0));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel2,ex); 
          }

//<UserCode_Begin_Bean_JPanel2>

//<UserCode_End_Bean_JPanel2>

          try
          {
            configureToolBar.setText(resourceBundle.getString("Configure ToolBar Menu"));
            configureToolBar.setFont(new Font("Dialog",0,12));
            configureToolBar.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+configureToolBar,ex); 
          }

//<UserCode_Begin_Bean_configureToolBar>

//<UserCode_End_Bean_configureToolBar>

          try
          {
            toolbarName.setText(resourceBundle.getString("ToolBar Name"));
            toolbarName.setFont(new Font("Dialog",0,12));
            toolbarName.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+toolbarName,ex); 
          }

//<UserCode_Begin_Bean_toolbarName>

//<UserCode_End_Bean_toolbarName>

          try
          {
            icon.setText(resourceBundle.getString("ToolBar Icon"));
            icon.setFont(new Font("Dialog",0,12));
            icon.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+icon,ex); 
          }

//<UserCode_Begin_Bean_icon>

//<UserCode_End_Bean_icon>

          try
          {
            browse.setText(resourceBundle.getString("..."));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+browse,ex); 
          }

//<UserCode_Begin_Bean_browse>

//<UserCode_End_Bean_browse>

          try
          {
            tooltip.setText(resourceBundle.getString("Tooltip Text"));
            tooltip.setFont(new Font("Dialog",0,12));
            tooltip.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+tooltip,ex); 
          }

//<UserCode_Begin_Bean_tooltip>

//<UserCode_End_Bean_tooltip>
		JLabel2.setPreferredSize(new Dimension(JLabel2.getPreferredSize().width+169,JLabel2.getPreferredSize().height+406));
		JPanel2.setPreferredSize(new Dimension(JPanel2.getPreferredSize().width+198,JPanel2.getPreferredSize().height+250));

  
          //<End_setUpProperties>
               JLabel2.setPreferredSize(new Dimension(JLabel1.getPreferredSize().width+168,JLabel1.getPreferredSize().height+388));
                 URL url=getClass().getResource("images"+File.separator+"toolbardetails.jpg");
                 JLabel2.setIcon(new ImageIcon(ImgConv.getImage(url.toString())));
                 toolbarName.setText("Panel Name");
	  
  } 
	public void itemStateChanged(ItemEvent ie)
	{
		if(checkToolBar.isSelected())
		{
			enableAll();
		}
		else
		{
			disableAll();
		}
	}
	public void enableAll()
	{
		toolbarNameField.setEnabled(true);
		iconField.setEnabled(true);
		tooltipField.setEnabled(true);
		browse.setEnabled(true);

	}
	public void disableAll()
	{
		toolbarNameField.setEnabled(false);
		iconField.setEnabled(false);
		tooltipField.setEnabled(false);
		browse.setEnabled(false);
	}
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getActionCommand().equals("browse"))
        {
            JFileChooser jfc=null;
            NarFileFilter ff = new NarFileFilter();
            ff.addExtension("png");
            ff.addExtension("jpg");
            ff.setDescription("Image files" );
            int ret;
            jfc = new JFileChooser(System.getProperty("user.dir")+File.separator+"images");   // new
            FilePreviewer previewer = new FilePreviewer(jfc);
            jfc.setAccessory(previewer);
            jfc.setDialogType(JFileChooser.CUSTOM_DIALOG);
            jfc.setDialogTitle("Select the Icon File");
            jfc.setApproveButtonMnemonic('s');
            jfc.setFileFilter(ff);
            ret = jfc.showDialog(null,"Select");
            if ( ret == 0)
            {
                File f_icon=jfc.getSelectedFile();
                iconField.setText(f_icon.toString() );
            }
        }
        
        
    }
    public void setToolBarMenuInformations()
    {
        toolbarInformation=new Vector();
        if(checkToolBar.isSelected())
        {
            toolbarInformation.add(toolbarNameField.getText());
            toolbarInformation.add(iconField.getText());
            toolbarInformation.add(tooltipField.getText());
        }
    }
    public Vector getToolBarMenuInformations()
    {
        return toolbarInformation;
    }
    public boolean validateToolBar()
    {
        if(checkToolBar.isSelected())
        {
            if(toolbarNameField.getText()==null||toolbarNameField.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null,"Enter a panel name","Mesage",JOptionPane.INFORMATION_MESSAGE);
                return false;
                                         
            }
            
            else if(iconField.getText()==null||iconField.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null,"Enter an icon for toolbar","Mesage",JOptionPane.INFORMATION_MESSAGE);
                return false;
                                         
            }
            else if(tooltipField.getText()==null||tooltipField.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null,"Enter tooltip for toolbar ","Mesage",JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
        }
        return true;
    }
  public void setUpConnections()
  { 

  //<Begin_setUpConnections> 

  
  //<End_setUpConnections>
  } 
  public void stop()
  { 

  //<Begin_stop> 
       if(!running)
 return;
 running=false;

  
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
	if(getParameter("RESOURCE_PROPERTIES" ) != null)
	{
		localePropertiesFileName = getParameter("RESOURCE_PROPERTIES");
	}
	resourceBundle = com.adventnet.apiutils.Utility.getBundle(localePropertiesFileName,getParameter("RESOURCE_LOCALE"),applet);
        if (initialized) return; 
        setPreferredSize(new Dimension(getPreferredSize().width+595,getPreferredSize().height+402)); 
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
        // let us set the initialized variable to true so  
        // we dont initialize again even if init is called 
        initialized = true; 

  
         //<End_init>
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



 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}




