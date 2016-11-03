
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
import java.net.URL;
import com.adventnet.nms.tools.utils.ImgConv;
import com.adventnet.nms.tools.utils.ToolsUtil;

public class PanelMenu extends JPanel implements ItemListener,ActionListener
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
	javax.swing.JLabel configurePanel = null;
	javax.swing.JCheckBox checkPanel = null;
	javax.swing.JTextField menuFileNameField = null;
	javax.swing.JTextField menuNameField = null;
	javax.swing.JTextField itemNameField = null;
	javax.swing.JLabel menuFileName = null;
	javax.swing.JLabel menuName = null;
	javax.swing.JLabel itemName = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JPanel JPanel4 = null;
	javax.swing.JButton JButton1 = null;
	javax.swing.JLabel JLabel3 = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JTextArea JTextArea1 = null;
	javax.swing.JLabel JLabel2 = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>

    Hashtable panelInformation;
    OperationTagScreen operation=null;
    public static boolean newUser;
  















   


  public PanelMenu()
  {
    //<Begin_PanelMenu>
    this.init();
  
    //<End_PanelMenu>
  }

  public PanelMenu(java.applet.Applet applet)
  {
    //<Begin_PanelMenu_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_PanelMenu_java.applet.Applet>
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
        configurePanel= new javax.swing.JLabel();
        checkPanel= new javax.swing.JCheckBox();
        menuFileNameField= new javax.swing.JTextField();
        menuNameField= new javax.swing.JTextField();
        itemNameField= new javax.swing.JTextField();
        menuFileName= new javax.swing.JLabel();
        menuName= new javax.swing.JLabel();
        itemName= new javax.swing.JLabel();
        JLabel1= new javax.swing.JLabel();
        JPanel4= new javax.swing.JPanel();
        JButton1= new javax.swing.JButton();
        JLabel3= new javax.swing.JLabel();
        JPanel3= new javax.swing.JPanel();
        JTextArea1= new javax.swing.JTextArea();
        JLabel2= new javax.swing.JLabel();

  
        //<End_initVariables>
        JButton1.addActionListener(this);
        JButton1.setActionCommand("advanced");
		JButton1.setMnemonic('a');
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
setConstraints(0,0,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel1.add(configurePanel,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel1.add(checkPanel,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,1,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel1.add(menuFileNameField,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,2,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel1.add(menuNameField,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,3,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel1.add(itemNameField,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel1.add(menuFileName,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel1.add(menuName,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,3,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel1.add(itemName,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,4,0,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel1.add(JLabel1,cons);
inset = new Insets(0,0,0,0);
setConstraints(0,5,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel1.add(JPanel4,cons);
JPanel4.setLayout(new GridBagLayout());
inset = new Insets(0,5,20,10);
setConstraints(1,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel4.add(JButton1,cons);
inset = new Insets(0,5,20,5);
setConstraints(0,0,-1,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
JPanel4.add(JLabel3,cons);
JPanel2.add(JPanel3,BorderLayout.NORTH);
JPanel3.setLayout(new BorderLayout(5,5));
JPanel3.add(JTextArea1,BorderLayout.NORTH);
Top.add(JLabel2,BorderLayout.WEST);

  
//<End_setUpGUI_Container>
	checkPanel.addItemListener(this);
  } 
  public void setUpProperties()
  { 

  //<Begin_setUpProperties> 

          try
          {
            Top.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.LineBorder(new Color(-16777216),1),resourceBundle.getString("Panel Menu Information"),2,0,new Font("Dialog",0,12),new Color(-16777216)));
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
            configurePanel.setFont(new Font("Dialog",0,12));
            configurePanel.setText(resourceBundle.getString("Configure Panel Specific  Menu"));
            configurePanel.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+configurePanel,ex); 
          }

//<UserCode_Begin_Bean_configurePanel>

//<UserCode_End_Bean_configurePanel>

          try
          {
            checkPanel.setSelected(false);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+checkPanel,ex); 
          }

//<UserCode_Begin_Bean_checkPanel>

//<UserCode_End_Bean_checkPanel>

          try
          {
            menuFileName.setText(resourceBundle.getString("Menu File Name"));
            menuFileName.setFont(new Font("Dialog",0,12));
            menuFileName.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+menuFileName,ex); 
          }

//<UserCode_Begin_Bean_menuFileName>

//<UserCode_End_Bean_menuFileName>

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
            JButton1.setFont(new Font("SansSerif",0,12));
            JButton1.setHorizontalTextPosition(0);
            JButton1.setLabel(resourceBundle.getString("Advanced"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton1,ex); 
          }

//<UserCode_Begin_Bean_JButton1>
JButton1.setLabel(ToolsUtil.getMenuName(resourceBundle.getString("Advanced"),'A'));

//<UserCode_End_Bean_JButton1>

          try
          {
            JLabel3.setHorizontalTextPosition(2);
            JLabel3.setHorizontalAlignment(2);
            JLabel3.setFont(new Font("SansSerif",0,12));
            JLabel3.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel3,ex); 
          }

//<UserCode_Begin_Bean_JLabel3>

//<UserCode_End_Bean_JLabel3>

          try
          {
            JTextArea1.setText(resourceBundle.getString("Please make sure the given menu is associated with any existing panel"));
            JTextArea1.setFont(new Font("Dialog",1,13));
            JTextArea1.setWrapStyleWord(true);
            JTextArea1.setLineWrap(true);
            JTextArea1.setEditable(false);
            JTextArea1.setBackground(new Color(-3355444));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JTextArea1,ex); 
          }

//<UserCode_Begin_Bean_JTextArea1>

//<UserCode_End_Bean_JTextArea1>
		JLabel2.setPreferredSize(new Dimension(JLabel2.getPreferredSize().width+168,JLabel2.getPreferredSize().height+388));
		JTextArea1.setPreferredSize(new Dimension(JTextArea1.getPreferredSize().width+426,JTextArea1.getPreferredSize().height+18));
		JPanel3.setPreferredSize(new Dimension(JPanel3.getPreferredSize().width+10,JPanel3.getPreferredSize().height+10));
		JPanel2.setPreferredSize(new Dimension(JPanel2.getPreferredSize().width+10,JPanel2.getPreferredSize().height+10));

  
          //<End_setUpProperties>

                 URL url=getClass().getResource("images"+File.separator+"panelspecificmenu.jpg");
                 JLabel2.setIcon(new ImageIcon(ImgConv.getImage(url.toString())));
                 JTextArea1.setText("\n"+resourceBundle.getString("Please make sure the given menu is associated with any existing panel"));
 
  }
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getActionCommand().equals("advanced"))
        {
			operation.setVisible(true);
        }
    }
	public void itemStateChanged(ItemEvent ie)
	{
		if(checkPanel.isSelected())
		{
			enableAll();
		}
		else
		{
			disableAll();
		}
	}
	public void instantiateOperation()
	{
		if(operation==null)
		{
			operation=new OperationTagScreen();
		}
		operation.newuser=newUser;
	}


	public void enableAll()
	{
		menuFileNameField.setEnabled(true);
		menuNameField.setEnabled(true);
		itemNameField.setEnabled(true);
                    JButton1.setEnabled(true);
	}
	public void disableAll()
	{
		menuFileNameField.setEnabled(false);
		menuNameField.setEnabled(false);
		itemNameField.setEnabled(false);
                JButton1.setEnabled(false);
	}

	public void setPanelMenuInformations()
	{
		panelInformation=new Hashtable();
		if(checkPanel.isSelected())
		{
			panelInformation.put("menufilename",menuFileNameField.getText());
			panelInformation.put("menuname",menuNameField.getText());
			panelInformation.put("menuitem",itemNameField.getText());
			if(operation!=null)
			{
				if(newUser&&operation.chBoxSecure.isSelected())
				{
					if(!operation.getPrivelege().equals(""))
					{
						panelInformation.put("privilege",operation.getPrivelege());
					}
			
					panelInformation.put("operation",operation.getOperationName());
				}

				if(!operation.getAccKey().equals("")&&!operation.getAccKey().equals("none"))
				{
					panelInformation.put("acceleratorkey",operation.getAccKey());
					if(!operation.getAccModifier().equals(""))
					{
						panelInformation.put("acceleratormodifier",operation.getAccModifier());
					}
				}
				if(!operation.getShortKey().equals("")&&!operation.getShortKey().equals("none"))
				{
					panelInformation.put("shortcutkey",operation.getShortKey());
				}
			}
		}
	}
	public Hashtable getPanelMenuInformations()
    {
        return panelInformation;
    }
    public boolean validatePanel()
    {
        if(checkPanel.isSelected())
        {
            if(menuFileNameField.getText()==null||menuFileNameField.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null,resourceBundle.getString("Enter a panel specific Menu file"),resourceBundle.getString("Message"),JOptionPane.INFORMATION_MESSAGE);
                return false;
                                         
            }
            
            else if(menuNameField.getText()==null||menuNameField.getText().equals(""))
            {
                                         
                JOptionPane.showMessageDialog(null,resourceBundle.getString("Enter a menu name"),resourceBundle.getString("Message"),JOptionPane.INFORMATION_MESSAGE);
                return false;
                                         
            }
            else if(itemNameField.getText()==null||itemNameField.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null,resourceBundle.getString("Enter a menu item "),resourceBundle.getString("Message"),JOptionPane.INFORMATION_MESSAGE);
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
        setPreferredSize(new Dimension(getPreferredSize().width+697,getPreferredSize().height+436)); 
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



