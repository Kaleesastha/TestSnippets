


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
import java.util.*;
import com.adventnet.nms.tools.utils.FilePreviewer;
import java.net.URL;
import com.adventnet.nms.tools.utils.ImgConv;
import com.adventnet.nms.tools.utils.ToolsUtil;
public class MapMenu extends JPanel implements ItemListener,ActionListener
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "DeploymentWizard";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JLabel configureMap = null;
	javax.swing.JCheckBox checkMap = null;
	javax.swing.JLabel menuName = null;
	javax.swing.JTextField menuNameField = null;
	javax.swing.JTextField itemNameField = null;
	javax.swing.JLabel itemName = null;
	javax.swing.JTextField mapIconField = null;
	javax.swing.JLabel mapIcon = null;
	javax.swing.JButton browse = null;
	javax.swing.JLabel JLabel6 = null;
	javax.swing.JLabel JLabel1 = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>

        Vector mapInformation;
  
  
  














   


  public MapMenu()
  {
    //<Begin_MapMenu>
    this.init();
  
    //<End_MapMenu>
  }

  public MapMenu(java.applet.Applet applet)
  {
    //<Begin_MapMenu_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_MapMenu_java.applet.Applet>
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
        JPanel1= new javax.swing.JPanel();
        configureMap= new javax.swing.JLabel();
        checkMap= new javax.swing.JCheckBox();
        menuName= new javax.swing.JLabel();
        menuNameField= new javax.swing.JTextField();
        itemNameField= new javax.swing.JTextField();
        itemName= new javax.swing.JLabel();
        mapIconField= new javax.swing.JTextField();
        mapIcon= new javax.swing.JLabel();
        browse= new javax.swing.JButton();
        JLabel6= new javax.swing.JLabel();
        JLabel1= new javax.swing.JLabel();

  
        //<End_initVariables>
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
Top.add(JPanel1,BorderLayout.CENTER);
JPanel1.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel1.add(configureMap,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel1.add(checkMap,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel1.add(menuName,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,1,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel1.add(menuNameField,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,2,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel1.add(itemNameField,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel1.add(itemName,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,3,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel1.add(mapIconField,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,3,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel1.add(mapIcon,cons);
inset = new Insets(5,5,5,5);
setConstraints(2,3,1,1,0.0,0.0,cons.EAST,cons.NONE,inset,0,0);
JPanel1.add(browse,cons);
inset = new Insets(0,0,0,0);
setConstraints(0,4,0,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel1.add(JLabel6,cons);
Top.add(JLabel1,BorderLayout.WEST);

  
//<End_setUpGUI_Container>
		checkMap.addItemListener(this);
                browse.addActionListener(this);
                browse.setActionCommand("browse");
  } 
  public void setUpProperties()
  { 

  //<Begin_setUpProperties> 

          try
          {
            Top.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.LineBorder(new Color(-16777216),1),resourceBundle.getString("Map Menu Information"),2,0,new Font("Dialog",0,12),new Color(-16777216)));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+Top,ex); 
          }

//<UserCode_Begin_Bean_components>

//<UserCode_End_Bean_components>

          try
          {
            JPanel1.setBorder(new javax.swing.border.BevelBorder(0));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel1,ex); 
          }

//<UserCode_Begin_Bean_JPanel1>

//<UserCode_End_Bean_JPanel1>

          try
          {
            configureMap.setText(resourceBundle.getString("Configure MapMenu"));
            configureMap.setFont(new Font("Dialog",0,12));
            configureMap.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+configureMap,ex); 
          }

//<UserCode_Begin_Bean_configureMap>

//<UserCode_End_Bean_configureMap>

          try
          {
            checkMap.setEnabled(true);
            checkMap.setSelected(false);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+checkMap,ex); 
          }

//<UserCode_Begin_Bean_checkMap>

//<UserCode_End_Bean_checkMap>

          try
          {
            menuName.setText(resourceBundle.getString("Menu Name"));
            menuName.setFont(new Font("Dialog",0,12));
            menuName.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+menuName,ex); 
          }

//<UserCode_Begin_Bean_menuName>

//<UserCode_End_Bean_menuName>

          try
          {
            itemName.setText(resourceBundle.getString("Menu Item Name"));
            itemName.setFont(new Font("Dialog",0,12));
            itemName.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+itemName,ex); 
          }

//<UserCode_Begin_Bean_itemName>

//<UserCode_End_Bean_itemName>

          try
          {
            mapIcon.setFont(new Font("Dialog",0,12));
            mapIcon.setText(resourceBundle.getString("Image For Map"));
            mapIcon.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+mapIcon,ex); 
          }

//<UserCode_Begin_Bean_mapIcon>

//<UserCode_End_Bean_mapIcon>

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
		JLabel1.setPreferredSize(new Dimension(JLabel1.getPreferredSize().width+168,JLabel1.getPreferredSize().height+388));

  
          //<End_setUpProperties>

           URL url=getClass().getResource("images"+File.separator+"mapmenu.jpg");
           JLabel1.setIcon(new ImageIcon(ImgConv.getImage(url.toString())));
  } 
	public void itemStateChanged(ItemEvent ie)
	{
		if(checkMap.isSelected())
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
		menuNameField.setEnabled(true);
		itemNameField.setEnabled(true);
		mapIconField.setEnabled(true);
		browse.setEnabled(true);
	}
	public void disableAll()
	{
		menuNameField.setEnabled(false);
		itemNameField.setEnabled(false);
		mapIconField.setEnabled(false);
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
            ff.setDescription(resourceBundle.getString("Image files" ));
            int ret;
            jfc = new JFileChooser(System.getProperty("user.dir")+File.separator+"images");   // new
            FilePreviewer previewer = new FilePreviewer(jfc);
            jfc.setAccessory(previewer);
            jfc.setDialogType(JFileChooser.CUSTOM_DIALOG);
            jfc.setDialogTitle(resourceBundle.getString("Select the Icon File"));
            jfc.setApproveButtonMnemonic('s');
            jfc.setFileFilter(ff);
            ret = jfc.showDialog(null,ToolsUtil.getMenuName(resourceBundle.getString("Select"),'s'));
            if ( ret == 0)
            {
                File f_icon=jfc.getSelectedFile();
                mapIconField.setText(f_icon.toString() );
            }
            
        }
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
        setPreferredSize(new Dimension(getPreferredSize().width+677,getPreferredSize().height+434)); 
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

    public void setMapMenuInformations()
    {
        mapInformation=new Vector();
        if(checkMap.isSelected())
        {
            mapInformation.addElement(menuNameField.getText());
            mapInformation.addElement(itemNameField.getText());
            mapInformation.addElement(mapIconField.getText());
        }
    }
    public Vector getMapMenuInformations()
    {
        return mapInformation;
    }
    public boolean validateMap()
    {
        if(checkMap.isSelected())
        {
            
            
            if(menuNameField.getText()==null||menuNameField.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null,resourceBundle.getString("Enter a menu name"),resourceBundle.getString("Message"),JOptionPane.INFORMATION_MESSAGE);
                return false;
                                         
            }
            else if(itemNameField.getText()==null||itemNameField.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null,resourceBundle.getString("Enter a menu item "),resourceBundle.getString("Message"),JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
            else if(mapIconField.getText()==null||mapIconField.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null,resourceBundle.getString("Select an icon for map "),resourceBundle.getString("Message"),JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
            File f=new File(mapIconField.getText());
            if(!f.exists())
            {
                JOptionPane.showMessageDialog(null,resourceBundle.getString("Select an Existing icon"),resourceBundle.getString("Message"),JOptionPane.INFORMATION_MESSAGE);
                return false;
            }

        }
        return true;
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


