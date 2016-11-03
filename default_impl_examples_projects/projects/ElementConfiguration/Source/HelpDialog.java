
//$Id: HelpDialog.java,v 1.1 2006/08/29 13:56:51 build Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.config;



import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class HelpDialog extends JDialog 
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "ElementConfigurationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JLabel titleLabel = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JPanel JPanel5 = null;
	javax.swing.JLabel subTitleLabel = null;
	javax.swing.JTextArea helpArea = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JPanel JPanel6 = null;
	javax.swing.JPanel JPanel4 = null;
	javax.swing.JButton JButton2 = null;
	javax.swing.JButton JButton1 = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>

	private ConfigPanel configPanel = null;

	private HelpInterface parent = null;

	private RelatedTopics relatedTopics = null;

	private Vector topicsVector = null;

	private String title = null;

	public HelpDialog(ConfigPanel configPanel, HelpInterface parent)
	{
		super((JDialog)parent);

		this.configPanel = configPanel;
		this.parent = parent;

		applet = configPanel.applet;

		init();
		configInit();
	}

	public void setRelatedTopics(String[] relatedTopics)
	{
		if(relatedTopics != null && relatedTopics.length > 0)
		{
			topicsVector = new Vector();

			topicsVector.addElement(title);

			for(int i=0; i<relatedTopics.length; i++)
			{
				topicsVector.addElement(relatedTopics[i]);
			}

			JButton2.setVisible(true);
		}
	}

	public HelpDialog()
  {
		//<Begin_HelpDialog>
    pack();
  
    //<End_HelpDialog>
	}

	public HelpDialog(java.applet.Applet applet)
  {
		//<Begin_HelpDialog_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_HelpDialog_java.applet.Applet>
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


	public void init()
  {
		//<Begin_init> 
	if(getParameter("RESOURCE_PROPERTIES" ) != null)
	{
		localePropertiesFileName = getParameter("RESOURCE_PROPERTIES");
	}
	resourceBundle = com.adventnet.apiutils.Utility.getBundle(localePropertiesFileName,getParameter("RESOURCE_LOCALE"),applet);
        if (initialized) return; 
        this.setSize(getPreferredSize().width+451,getPreferredSize().height+360); 
          setTitle(resourceBundle.getString("HelpDialog"));
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
        // let us set the initialized variable to true so  
        // we dont initialize again even if init is called 
        initialized = true; 

  
         //<End_init>
		
		this.setTitle(resourceBundle.getString("Help"));

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
            if (input.equals("RESOURCE_PROPERTIES")) value = "ElementConfigurationResources"; 
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
            Top.setBackground(new Color(-1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+Top,ex); 
          }

//<UserCode_Begin_Bean_Top>

//<UserCode_End_Bean_Top>

          try
          {
            JPanel1.setBackground(new Color(-1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel1,ex); 
          }

//<UserCode_Begin_Bean_JPanel1>

//<UserCode_End_Bean_JPanel1>

          try
          {
            titleLabel.setBackground(new Color(-1));
            titleLabel.setOpaque(true);
            titleLabel.setText(resourceBundle.getString("TASK CONFIGURATION"));
            titleLabel.setHorizontalAlignment(0);
            titleLabel.setIconTextGap(10);
            titleLabel.setForeground(new Color(-52480));
            titleLabel.setFont(new Font("Dialog",1,20));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+titleLabel,ex); 
          }

//<UserCode_Begin_Bean_titleLabel>

//<UserCode_End_Bean_titleLabel>

          try
          {
            JPanel2.setBackground(new Color(-1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel2,ex); 
          }

//<UserCode_Begin_Bean_JPanel2>

//<UserCode_End_Bean_JPanel2>

          try
          {
            JPanel5.setBackground(new Color(-1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel5,ex); 
          }

//<UserCode_Begin_Bean_JPanel5>

//<UserCode_End_Bean_JPanel5>

          try
          {
            subTitleLabel.setForeground(new Color(-13434625));
            subTitleLabel.setFont(new Font("Dialog",1,20));
            subTitleLabel.setBackground(new Color(-1));
            subTitleLabel.setOpaque(true);
            subTitleLabel.setIconTextGap(7);
            subTitleLabel.setText(resourceBundle.getString("Sub - Title"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+subTitleLabel,ex); 
          }

//<UserCode_Begin_Bean_subTitleLabel>

//<UserCode_End_Bean_subTitleLabel>

          try
          {
            helpArea.setEditable(false);
            helpArea.setWrapStyleWord(true);
            helpArea.setLineWrap(true);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+helpArea,ex); 
          }

//<UserCode_Begin_Bean_helpArea>

//<UserCode_End_Bean_helpArea>

          try
          {
            JPanel3.setBackground(new Color(-1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel3,ex); 
          }

//<UserCode_Begin_Bean_JPanel3>

//<UserCode_End_Bean_JPanel3>

          try
          {
            JPanel6.setBackground(new Color(-1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel6,ex); 
          }

//<UserCode_Begin_Bean_JPanel6>

//<UserCode_End_Bean_JPanel6>

          try
          {
            JPanel4.setBackground(new Color(-1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel4,ex); 
          }

//<UserCode_Begin_Bean_JPanel4>

//<UserCode_End_Bean_JPanel4>

          try
          {
            JButton2.setForeground(new Color(-10027162));
            JButton2.setFocusPainted(false);
            JButton2.setText(resourceBundle.getString("Related Topics >>"));
            JButton2.setBackground(new Color(-16777165));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton2,ex); 
          }

//<UserCode_Begin_Bean_JButton2>

//<UserCode_End_Bean_JButton2>

          try
          {
            JButton1.setText(resourceBundle.getString("Done"));
            JButton1.setForeground(new Color(-6684775));
            JButton1.setFocusPainted(false);
            JButton1.setBackground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton1,ex); 
          }

//<UserCode_Begin_Bean_JButton1>

//<UserCode_End_Bean_JButton1>
		titleLabel.setPreferredSize(new Dimension(titleLabel.getPreferredSize().width+111,titleLabel.getPreferredSize().height+13));

  
          //<End_setUpProperties>
	} 
	public void start()
  {
		//<Begin_start> 
       if(running)
 return;
 running=true;

  
       //<End_start>
	} 
	public void stop()
  {
		//<Begin_stop> 
       if(!running)
 return;
 running=false;

  
       //<End_stop>
	} 
	public void initVariables()
  {
		//<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        JPanel1= new javax.swing.JPanel();
        titleLabel= new javax.swing.JLabel();
        JPanel2= new javax.swing.JPanel();
        JPanel5= new javax.swing.JPanel();
        subTitleLabel= new javax.swing.JLabel();
        helpArea= new javax.swing.JTextArea();
        JPanel3= new javax.swing.JPanel();
        JPanel6= new javax.swing.JPanel();
        JPanel4= new javax.swing.JPanel();
        JButton2= new javax.swing.JButton();
        JButton1= new javax.swing.JButton();

  
        //<End_initVariables>
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


	public void doneButtonActionPerformed()
	{
		setVisible(false);
	}
	public void topicsButtonMouseClicked(MouseEvent me)
	{
		Point p = JButton2.getLocationOnScreen();
		if(topicsVector != null)
		{
			if(relatedTopics == null)
			{
				relatedTopics = new RelatedTopics(this,p,139,100,topicsVector);
			}
			else
			{
				relatedTopics.setLocation((int)p.getX(), ((int)p.getY())+JButton2.getHeight());
				relatedTopics.setVisible(true);
			}
		}
	}

	public void showHelpFor(String key)
	{
		if(key.equals(title))
		{
			subTitleLabel.setText(resourceBundle.getString("Overview"));
		}
		else
		{
			subTitleLabel.setText(resourceBundle.getString(key));
		}

		helpArea.setText(parent.getHelpFor(key));
	}

	private void configInit()
	{

		ImageIcon icon = configPanel.configClientUtils.getImage(configPanel.applet.getDocumentBase()+"../images/devicesearch.png");
		titleLabel.setIcon(icon);

		ImageIcon icon1 = configPanel.configClientUtils.getImage(configPanel.applet.getDocumentBase()+"../images/i18n.jpg");
		subTitleLabel.setIcon(icon1);

		JButton2.setVisible(false);

		title = parent.getTitle();

		titleLabel.setText(resourceBundle.getString(title));

		showHelpFor(title);

		configPanel.configClientUtils.centerWindow(this);
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
Top.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel1,cons);
JPanel1.setLayout(new FlowLayout(1,5,5));
JPanel1.add(titleLabel);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(JPanel2,cons);
JPanel2.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel2.add(JPanel5,cons);
JPanel5.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel5.add(subTitleLabel,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel5.add(helpArea,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel3,cons);
JPanel3.setLayout(new BorderLayout(5,5));
JPanel3.add(JPanel6,BorderLayout.CENTER);
JPanel6.setLayout(new FlowLayout(0,5,5));
JPanel3.add(JPanel4,BorderLayout.EAST);
JPanel4.setLayout(new FlowLayout(1,5,5));
JPanel4.add(JButton2);
JPanel4.add(JButton1);

  
//<End_setUpGUI_Container>
	} 
	public void setUpConnections()
  {
		//<Begin_setUpConnections> 

      JButton2_JButton2_conn1 JButton2_JButton2_conn11 =  new JButton2_JButton2_conn1();
      JButton2.addMouseListener(JButton2_JButton2_conn11);
      JButton1_JButton1_conn1 JButton1_JButton1_conn11 =  new JButton1_JButton1_conn1();
      JButton1.addActionListener(JButton1_JButton1_conn11);
  
      //<End_setUpConnections>
	} 


	//<Begin__class_JButton2_JButton2_conn1>

 class JButton2_JButton2_conn1 extends java.awt.event.MouseAdapter implements java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton2_JButton2_conn1>



     //Listener Interface Methods Are Added Below 


     public void mouseClicked( java.awt.event.MouseEvent arg0)
     {
  topicsButtonMouseClicked(arg0);
     }
//<UserCode_End_Connection_JButton2_JButton2_conn1>
 }//<End__class_JButton2_JButton2_conn1>
	//<Begin__class_JButton1_JButton1_conn1>

 class JButton1_JButton1_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton1_JButton1_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  doneButtonActionPerformed();
     }
//<UserCode_End_Connection_JButton1_JButton1_conn1>
 }//<End__class_JButton1_JButton1_conn1>

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}





