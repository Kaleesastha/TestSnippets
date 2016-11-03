
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
public class FrameMenu extends JPanel implements ItemListener,ActionListener
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
	javax.swing.JButton browse = null;
	javax.swing.JTextField menuNameField = null;
	javax.swing.JTextField itemNameField = null;
	javax.swing.JCheckBox checkFrame = null;
	javax.swing.JLabel configureFrame = null;
	javax.swing.JLabel menuName = null;
	javax.swing.JLabel itemName = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JButton JButton1 = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JTextArea JTextArea1 = null;
	javax.swing.JLabel JLabel2 = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>
    Hashtable frameInformation;
    OperationTagScreen operation=null;
    public static boolean newUser;
  
  














   


  public FrameMenu()
  {
    //<Begin_FrameMenu>
    this.init();
  
    //<End_FrameMenu>
  }

  public FrameMenu(java.applet.Applet applet)
  {
    //<Begin_FrameMenu_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_FrameMenu_java.applet.Applet>
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
        browse= new javax.swing.JButton();
        menuNameField= new javax.swing.JTextField();
        itemNameField= new javax.swing.JTextField();
        checkFrame= new javax.swing.JCheckBox();
        configureFrame= new javax.swing.JLabel();
        menuName= new javax.swing.JLabel();
        itemName= new javax.swing.JLabel();
        JLabel1= new javax.swing.JLabel();
        JButton1= new javax.swing.JButton();
        JPanel3= new javax.swing.JPanel();
        JTextArea1= new javax.swing.JTextArea();
        JLabel2= new javax.swing.JLabel();

  
        //<End_initVariables>
        browse.addActionListener(this);
        JButton1.addActionListener(this);
        browse.setActionCommand("select");
        JButton1.setActionCommand("advanced");
		JButton1.setMnemonic('a');
		browse.setMnemonic('s');
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
setConstraints(2,1,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel1.add(browse,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,1,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel1.add(menuNameField,cons);
inset = new Insets(5,5,5,0);
setConstraints(1,2,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel1.add(itemNameField,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel1.add(checkFrame,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel1.add(configureFrame,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel1.add(menuName,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel1.add(itemName,cons);
inset = new Insets(0,0,0,0);
setConstraints(0,3,0,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel1.add(JLabel1,cons);
inset = new Insets(0,5,20,10);
setConstraints(2,4,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel1.add(JButton1,cons);
JPanel2.add(JPanel3,BorderLayout.NORTH);
JPanel3.setLayout(new BorderLayout(5,5));
JPanel3.add(JTextArea1,BorderLayout.NORTH);
Top.add(JLabel2,BorderLayout.WEST);

  
//<End_setUpGUI_Container>
	checkFrame.addItemListener(this);
  } 
  public void setUpProperties()
  { 

  //<Begin_setUpProperties> 

          try
          {
            Top.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.LineBorder(new Color(-16777216),1),resourceBundle.getString("Frame Menu Information"),2,0,new Font("Dialog",0,12),new Color(-16777216)));
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
            browse.setText(resourceBundle.getString("Select"));
            browse.setFont(new Font("Dialog",0,12));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+browse,ex); 
          }

//<UserCode_Begin_Bean_browse>

browse.setText(ToolsUtil.getMenuName(resourceBundle.getString("Select"),'S'));
//<UserCode_End_Bean_browse>

          try
          {
            checkFrame.setSelected(false);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+checkFrame,ex); 
          }

//<UserCode_Begin_Bean_checkFrame>

//<UserCode_End_Bean_checkFrame>

          try
          {
            configureFrame.setText(resourceBundle.getString("Configure Frame Specific Menu"));
            configureFrame.setFont(new Font("Dialog",0,12));
            configureFrame.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+configureFrame,ex); 
          }

//<UserCode_Begin_Bean_configureFrame>

//<UserCode_End_Bean_configureFrame>

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
            JTextArea1.setText(resourceBundle.getString("Please select an existing menu or give a new one."));
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
		JLabel2.setPreferredSize(new Dimension(JLabel2.getPreferredSize().width+165,JLabel2.getPreferredSize().height+421));
		JPanel3.setPreferredSize(new Dimension(JPanel3.getPreferredSize().width+10,JPanel3.getPreferredSize().height+10));
		JPanel1.setPreferredSize(new Dimension(JPanel1.getPreferredSize().width+115,JPanel1.getPreferredSize().height+157));
		JPanel2.setPreferredSize(new Dimension(JPanel2.getPreferredSize().width+10,JPanel2.getPreferredSize().height+65));

  
          //<End_setUpProperties>

		JLabel2.setPreferredSize(new Dimension(JLabel1.getPreferredSize().width+168,JLabel1.getPreferredSize().height+388));
                URL url=getClass().getResource("images"+File.separator+"framemenu.jpg");
                JLabel2.setIcon(new ImageIcon(ImgConv.getImage(url.toString())));
                JTextArea1.setText("\n"+resourceBundle.getString("  Select an existing menu or give a new one"));

  } 
	public void itemStateChanged(ItemEvent ie)
	{
		if(checkFrame.isSelected())
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
		browse.setEnabled(true);
       JButton1.setEnabled(true);
	}
	public void disableAll()
	{
		menuNameField.setEnabled(false);
		itemNameField.setEnabled(false);
		browse.setEnabled(false);
                JButton1.setEnabled(false);
	}
	public void setFrameMenuInformations()
	{
		frameInformation=new Hashtable();

		if(checkFrame.isSelected())
		{
			frameInformation.put("menuname",menuNameField.getText());
			frameInformation.put("menuitem",itemNameField.getText());
			if(operation!=null)
			{
				if(newUser&&operation.chBoxSecure.isSelected())
				{
					if(!operation.getPrivelege().equals(""))
					{
						frameInformation.put("privilege",operation.getPrivelege());
					}
					frameInformation.put("operation",operation.getOperationName());
				}

				if(!operation.getAccKey().equals("")&&!operation.getAccKey().equals("none"))
				{
					frameInformation.put("acceleratorkey",operation.getAccKey());

					if(!operation.getAccModifier().equals(""))
					{
						frameInformation.put("acceleratormodifier",operation.getAccModifier());
					}
				}
				if(!operation.getShortKey().equals("")&&!operation.getShortKey().equals("none"))
				{
					frameInformation.put("shortcutkey",operation.getShortKey());
				}
			}
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

    public Hashtable getFrameMenuInformations()
    {
        return frameInformation;
    }
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getActionCommand().equals("select"))
        {
            
            MenuViewer menu=new MenuViewer();
            menu.showTree();
            String name=menu.gettreename();
            menuNameField.setText(name);
        }
        if(ae.getActionCommand().equals("advanced"))
        {
						operation.setVisible(true);
        }
    }
    public boolean validateFrame()
    {
        if(checkFrame.isSelected())
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
        setPreferredSize(new Dimension(getPreferredSize().width+863,getPreferredSize().height+530)); 
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



