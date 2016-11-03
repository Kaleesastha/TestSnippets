
//$Id: UploadDetails.java,v 1.1 2006/08/29 13:56:51 build Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.config;




import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import com.adventnet.management.config.xml.*;
import com.adventnet.management.config.*;

public class UploadDetails extends JDialog implements ConfigResponseListener
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "ElementConfigurationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JButton JButton1 = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JLabel JLabel4 = null;
	javax.swing.JLabel JLabel3 = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JLabel JLabel2 = null;
	javax.swing.JPanel JPanel5 = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	javax.swing.JTable JTable1 = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JCheckBox saveCheckBox = null;
	javax.swing.table.DefaultTableModel DefaultTableModel1 = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>


	private ConfigPanel configPanel = null;

	private String uploadedTask = null;









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
		saveCheckBox.setSelected(true);
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
        this.setSize(getPreferredSize().width+506,getPreferredSize().height+277); 
          setTitle(resourceBundle.getString("UploadDetails"));
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
            JButton1.setLabel(resourceBundle.getString("Close"));
            JButton1.setFont(new Font("Dialog",0,12));
            JButton1.setText(resourceBundle.getString("O.K."));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton1,ex); 
          }

//<UserCode_Begin_Bean_JButton1>

//<UserCode_End_Bean_JButton1>

          try
          {
            JLabel1.setRequestFocusEnabled(false);
            JLabel1.setForeground(new Color(-16764109));
            JLabel1.setFont(new Font("Dialog",0,12));
            JLabel1.setText(resourceBundle.getString("Protocol :"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel1,ex); 
          }

//<UserCode_Begin_Bean_JLabel1>

//<UserCode_End_Bean_JLabel1>

          try
          {
            JLabel2.setFont(new Font("Dialog",0,12));
            JLabel2.setForeground(new Color(-16764109));
            JLabel2.setText(resourceBundle.getString("Device Name :"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel2,ex); 
          }

//<UserCode_Begin_Bean_JLabel2>

//<UserCode_End_Bean_JLabel2>

          try
          {
            JPanel5.setBorder(new javax.swing.border.EtchedBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel5,ex); 
          }

//<UserCode_Begin_Bean_JPanel5>

//<UserCode_End_Bean_JPanel5>

          try
          {
            saveCheckBox.setText(resourceBundle.getString("Save Upload Information to Task"));
            saveCheckBox.setFont(new Font("Dialog",0,12));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+saveCheckBox,ex); 
          }

//<UserCode_Begin_Bean_saveCheckBox>

//<UserCode_End_Bean_saveCheckBox>
		JPanel5.setPreferredSize(new Dimension(JPanel5.getPreferredSize().width+10,JPanel5.getPreferredSize().height+10));
		JPanel2.setPreferredSize(new Dimension(JPanel2.getPreferredSize().width+175,JPanel2.getPreferredSize().height+12));
		JPanel1.setPreferredSize(new Dimension(JPanel1.getPreferredSize().width+401,JPanel1.getPreferredSize().height+1));

  
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
        JButton1= new javax.swing.JButton();
        JPanel2= new javax.swing.JPanel();
        JLabel4= new javax.swing.JLabel();
        JLabel3= new javax.swing.JLabel();
        JLabel1= new javax.swing.JLabel();
        JLabel2= new javax.swing.JLabel();
        JPanel5= new javax.swing.JPanel();
        JScrollPane1= new javax.swing.JScrollPane();
        JTable1= new javax.swing.JTable();
        JPanel3= new javax.swing.JPanel();
        saveCheckBox= new javax.swing.JCheckBox();
        DefaultTableModel1= new javax.swing.table.DefaultTableModel();

  
        //<End_initVariables>
	} 
	public void setUpGUI(Container container)
  {
		//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));
Top.add(JPanel1,BorderLayout.SOUTH);
JPanel1.setLayout(new FlowLayout(1,5,5));
JPanel1.add(JButton1);
Top.add(JPanel2,BorderLayout.NORTH);
JPanel2.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel2.add(JLabel4,cons);
inset = new Insets(5,5,5,5);
setConstraints(3,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel2.add(JLabel3,cons);
inset = new Insets(5,5,5,0);
setConstraints(0,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel2.add(JLabel1,cons);
inset = new Insets(5,5,5,0);
setConstraints(2,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel2.add(JLabel2,cons);
Top.add(JPanel5,BorderLayout.CENTER);
JPanel5.setLayout(new BorderLayout(5,5));
JPanel5.add(JScrollPane1,BorderLayout.CENTER);
JScrollPane1.getViewport().add(JTable1);
JPanel5.add(JPanel3,BorderLayout.SOUTH);
JPanel3.setLayout(new FlowLayout(1,5,5));
JPanel3.add(saveCheckBox);

  
//<End_setUpGUI_Container>
	} 
	public void setUpConnections()
  {
		//<Begin_setUpConnections> 

      JButton1_JButton1_conn1 JButton1_JButton1_conn11 =  new JButton1_JButton1_conn1();
      JButton1.addActionListener(JButton1_JButton1_conn11);
  
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

	public UploadDetails(ConfigPanel configPanel, String uploadedTask, String protocolName, String deviceName, String[] columnHeader,String[][] values)
	{
		this.configPanel = configPanel;
		this.uploadedTask = uploadedTask;

		init();

		JTable1.setDefaultRenderer(Object.class, new SimpleTableSelectionRenderer());
		JTable1.setDefaultEditor(Object.class, null);	

		JButton1.setText("OK");

		JLabel4.setText(protocolName);
		JLabel3.setText(deviceName);

		DefaultTableModel1.setDataVector(values,columnHeader);
		JTable1.setModel(DefaultTableModel1);

		configPanel.configClientUtils.centerWindow(this);
	}


	public UploadDetails()
  {
		//<Begin_UploadDetails>
    pack();
  
    //<End_UploadDetails>
	}

	public UploadDetails(java.applet.Applet applet)
  {
		//<Begin_UploadDetails_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_UploadDetails_java.applet.Applet>
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

	public void okButtonActionPerformed()
	{
		if(saveCheckBox.isSelected())
		{
			try
			{
				ConfigTask task = new ConfigTask(uploadedTask);
				task.setAttribute("isOverwrite", "true");
				Object[] params = {task.toString()};
				configPanel.configResponseHandler.sendRequest(NmsConfigConstants.SAVE_TASK, params, this);
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}

		setVisible(false);
	}

	public void response(ConfigResultEvent configResultEvent)
	{
		int workID = configResultEvent.getWorkID();
		int errorCode = configResultEvent.getErrorCode();

		String uniqueID = configResultEvent.getRequestID();

		if(workID == NmsConfigConstants.SAVE_TASK)
		{
			if(errorCode != NmsConfigConstants.NO_ERROR)
			{
				configPanel.configClientUtils.showErrorDialog(this, resourceBundle.getString(configResultEvent.getErrorString()), resourceBundle.getString("Error"), "error");
			}

			dispose();
		}
	}




	//<Begin__class_JButton1_JButton1_conn1>

 class JButton1_JButton1_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton1_JButton1_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  okButtonActionPerformed();
     }
//<UserCode_End_Connection_JButton1_JButton1_conn1>
 }//<End__class_JButton1_JButton1_conn1>

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}
