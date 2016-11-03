
//$Id: ConfigureDevices.java,v 1.2 2007/02/22 15:03:04 srajeswari Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.config;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import com.adventnet.management.config.*;

public class ConfigureDevices extends JDialog implements ActionListener, ConfigResponseListener, HelpInterface, WindowListener
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "ElementConfigurationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel11 = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JButton JButton1 = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JLabel JLabel11 = null;
	javax.swing.JPanel JPanel41 = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JButton backButton = null;
	javax.swing.JButton nextButton = null;
	javax.swing.JButton cancelButton = null;
	javax.swing.JPanel JPanel31 = null;
	javax.swing.JPanel cardPanel = null;
	javax.swing.JLabel JLabel1 = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>

	private ConfigPanel configPanel = null;

	private SelectProtocolPanel selectProtocolPanel = null;

	private	SelectTasksPanel selectTasksPanel = null;

	private	TemplatesConfiguration templatesConfiguration = null;

	private SetPropertiesForDevices setPropertiesForDevices = null;

	private Hashtable uniqueIDVsTask = new Hashtable();

	private Vector devices = null;

	private HelpDialog helpDialog = null;

	private int cardIndex = 0;

	public ConfigureDevices(ConfigPanel configPanel, Vector devices)
	{
		super(configPanel.configClientUtils.getParentFrame(configPanel));

		this.configPanel = configPanel;
		this.devices = devices;

		applet = configPanel.applet;

		init();
		configInit();
		// Added by saravanasp as part of Operation Theme.
		addWindowListener(this);
	}


    public ConfigureDevices()
    {
	//<Begin_ConfigureDevices>
	pack();
	
	//<End_ConfigureDevices>
    }
    
    public ConfigureDevices(java.applet.Applet applet)
    {
	//<Begin_ConfigureDevices_java.applet.Applet>
	this.applet = applet;
	pack();
	// Commented to handle the Window Closing Events as part of Operation Theme
	//this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	
	//<End_ConfigureDevices_java.applet.Applet>
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
        this.setSize(getPreferredSize().width+738,getPreferredSize().height+461); 
          setTitle(resourceBundle.getString("ConfigureDevices"));
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
		setTitle(resourceBundle.getString("Configure Devices"));

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
            JPanel11.setBorder(new javax.swing.border.EtchedBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel11,ex); 
          }

//<UserCode_Begin_Bean_JPanel11>

//<UserCode_End_Bean_JPanel11>

          try
          {
            JButton1.setPreferredSize(new Dimension(35,23));
            JButton1.setMaximumSize(new Dimension(35,23));
            JButton1.setMinimumSize(new Dimension(35,23));
            JButton1.setText(resourceBundle.getString(""));
            JButton1.setFocusPainted(false);
            JButton1.setBackground(new Color(-1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton1,ex); 
          }

//<UserCode_Begin_Bean_JButton1>

//<UserCode_End_Bean_JButton1>

          try
          {
            JLabel11.setForeground(new Color(-16764109));
            JLabel11.setText(resourceBundle.getString("Device Configuration"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel11,ex); 
          }

//<UserCode_Begin_Bean_JLabel11>

//<UserCode_End_Bean_JLabel11>

          try
          {
            JPanel41.setBorder(new javax.swing.border.EtchedBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel41,ex); 
          }

//<UserCode_Begin_Bean_JPanel41>

//<UserCode_End_Bean_JPanel41>

          try
          {
            backButton.setText(resourceBundle.getString("Back"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+backButton,ex); 
          }

//<UserCode_Begin_Bean_backButton>

//<UserCode_End_Bean_backButton>

          try
          {
            nextButton.setText(resourceBundle.getString("Next"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+nextButton,ex); 
          }

//<UserCode_Begin_Bean_nextButton>

//<UserCode_End_Bean_nextButton>

          try
          {
            cancelButton.setText(resourceBundle.getString("Cancel"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+cancelButton,ex); 
          }

//<UserCode_Begin_Bean_cancelButton>

//<UserCode_End_Bean_cancelButton>

          try
          {
            JPanel31.setBorder(new javax.swing.border.EtchedBorder(0));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel31,ex); 
          }

//<UserCode_Begin_Bean_JPanel31>

//<UserCode_End_Bean_JPanel31>

          try
          {
            cardPanel.setBorder(new javax.swing.border.SoftBevelBorder(0));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+cardPanel,ex); 
          }

//<UserCode_Begin_Bean_cardPanel>

//<UserCode_End_Bean_cardPanel>

          try
          {
            JLabel1.setBorder(new javax.swing.border.SoftBevelBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel1,ex); 
          }

//<UserCode_Begin_Bean_JLabel1>

//<UserCode_End_Bean_JLabel1>
		nextButton.setPreferredSize(new Dimension(nextButton.getPreferredSize().width+11,nextButton.getPreferredSize().height+0));
		backButton.setPreferredSize(new Dimension(backButton.getPreferredSize().width+9,backButton.getPreferredSize().height+0));

  
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
        JPanel11= new javax.swing.JPanel();
        JPanel2= new javax.swing.JPanel();
        JButton1= new javax.swing.JButton();
        JPanel3= new javax.swing.JPanel();
        JLabel11= new javax.swing.JLabel();
        JPanel41= new javax.swing.JPanel();
        JPanel1= new javax.swing.JPanel();
        backButton= new javax.swing.JButton();
        nextButton= new javax.swing.JButton();
        cancelButton= new javax.swing.JButton();
        JPanel31= new javax.swing.JPanel();
        cardPanel= new javax.swing.JPanel();
        JLabel1= new javax.swing.JLabel();

  
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







	public void nextButtonActionPerformed(String actionCommand)
	{
		if(actionCommand.equals(resourceBundle.getString("Finish")))
		{
			finishActionPerformed();
			this.dispose();
		}
		else
		{
			CardLayout card = (CardLayout)cardPanel.getLayout();

			if(cardIndex == 0)
			{
				backButton.setEnabled(true);

				selectTasksPanel.setProtocol(selectProtocolPanel.getSelectedProtocol());
			}
			else if(cardIndex == 1)
			{
				Vector selectedTemplates = selectTasksPanel.getSelectedTemplates();

				if(selectedTemplates != null && selectedTemplates.size() > 0)
				{
					templatesConfiguration.setTemplates(selectedTemplates);
				}
				else
				{
					cardIndex++;
					card.next(cardPanel);
				}
			}

			if(cardIndex == 2)
			{
				String protocol = selectProtocolPanel.getSelectedProtocol();

				setPropertiesForDevices.setProtocol(protocol);
				setPropertiesForDevices.setDevices(configPanel.configMainUI.getDevicesWithDefaultValues(devices, protocol));

				nextButton.setText(resourceBundle.getString("Finish"));
			}

			cardIndex++;
			card.next(cardPanel);
		}
	}






	public void backButtonActionPerformed()
	{
		CardLayout card = (CardLayout)cardPanel.getLayout();

		if(cardIndex == 1)
		{
			backButton.setEnabled(false);
		}
		else if(cardIndex == 3)
		{
			Vector selectedTemplates = selectTasksPanel.getSelectedTemplates();

			if(selectedTemplates == null || selectedTemplates.size() == 0)
			{
				cardIndex--;
				card.previous(cardPanel);
			}

			nextButton.setText(resourceBundle.getString("Next"));
		}

		cardIndex--;
		card.previous(cardPanel);
	}




	public void cancelButtonActionPerformed()
	{
		this.setVisible(false);
		this.dispose();
	}

	private void configInit()
	{
		JLabel1.setIcon(configPanel.configClientUtils.getImage(applet.getDocumentBase()+"../images/execute.png"));
		JButton1.setIcon(configPanel.configClientUtils.getImage(applet.getDocumentBase()+"../images/contextualhelp.png"));

		JButton1.addActionListener(this);
		
		cardPanel.setLayout(new CardLayout(5,5));

		selectProtocolPanel = new SelectProtocolPanel(configPanel);

		selectTasksPanel = new SelectTasksPanel(configPanel);

		templatesConfiguration = new TemplatesConfiguration(configPanel);

		setPropertiesForDevices = new SetPropertiesForDevices(configPanel);

		cardPanel.add("1", selectProtocolPanel);
		cardPanel.add("2", selectTasksPanel);
		cardPanel.add("3", templatesConfiguration);
		cardPanel.add("4", setPropertiesForDevices);

		backButton.setEnabled(false);

		configPanel.configClientUtils.centerWindow(this);
	}

	private void finishActionPerformed()
	{
		ExecuteXMLGenerator executeXML = new ExecuteXMLGenerator();

		String executeXMLStr = null;

		String uniqueID = null;

		executeXML.setProtocol(selectProtocolPanel.getSelectedProtocol());
		executeXML.setDevices(setPropertiesForDevices.getDevices());

		Vector selectedTasks = selectTasksPanel.getSelectedTasks();

		if(selectedTasks != null && selectedTasks.size() > 0)
		{
			for(int i=0; i<selectedTasks.size(); i++)
			{
				String taskName = (String)selectedTasks.elementAt(i);

				executeXML.setTaskName(taskName);

				Object[] params = {executeXML.getExecuteXML()};

				uniqueID = configPanel.configResponseHandler.sendRequest(NmsConfigConstants.EXECUTE, params, this);

				configPanel.configMainUI.setTaskInExecutingState(taskName);
				uniqueIDVsTask.put(uniqueID, taskName);
			}
		}

		Hashtable templatesWithDS = templatesConfiguration.getTemplatesWithdDataSource();

		if(templatesWithDS != null && templatesWithDS.size() > 0)
		{
			Hashtable dataSourceParamsTable = templatesConfiguration.getDataSourceParamsTable();

			for(Enumeration enumerate = templatesWithDS.keys(); enumerate.hasMoreElements();)
			{
				String template = (String)enumerate.nextElement();
				String dataSource = (String)templatesWithDS.get(template);

				executeXML.setTaskName(template);
				executeXML.setDataSourceName(dataSource);

				Object[] params = {executeXML.getExecuteXML(), getDataSourceParams(dataSourceParamsTable, template+dataSource)};
				uniqueID = configPanel.configResponseHandler.sendRequest(NmsConfigConstants.EXECUTE_TASK_WITH_DATASOURCE, params, this);

				configPanel.configMainUI.setTaskInExecutingState(template);
				uniqueIDVsTask.put(uniqueID, template);
			}
		}

		setVisible(false);
		//dispose();
	}

	private Properties getDataSourceParams(Hashtable dataSourceParamsTable, String templateWithDS)
	{
		Properties dataSourceParams = null;

		if(dataSourceParamsTable != null)
		{
			Properties prop = (Properties)dataSourceParamsTable.get(templateWithDS);

			if(prop != null)
			{
				dataSourceParams = new Properties();

				for(Enumeration enumerate = prop.keys(); enumerate.hasMoreElements();)
				{
					String key = (String)enumerate.nextElement();
					String value = (String)prop.get(key);

					if(value != null && !value.equals(""))
					{
						dataSourceParams.put("$DataSourceParam$"+key, value);
					}
				}
			}
		}

		return dataSourceParams;
	}

	public void response(ConfigResultEvent configResultEvent)
	{
		int workID = configResultEvent.getWorkID();
		int errorCode = configResultEvent.getErrorCode();

		String uniqueID = configResultEvent.getRequestID();

		String taskName = (String)uniqueIDVsTask.remove(uniqueID);

		if(workID == NmsConfigConstants.EXECUTE || workID == NmsConfigConstants.EXECUTE_TASK_WITH_DATASOURCE)
		{
			configPanel.configMainUI.updateTaskLastExecutedTime(taskName);	

			if(errorCode == NmsConfigConstants.NO_ERROR)
			{
				ShowDetails sd = new ShowDetails(configPanel, taskName, configResultEvent);

				sd.setVisible(true);
			}
			else
			{
				configPanel.configClientUtils.showErrorDialog(this, resourceBundle.getString(configResultEvent.getErrorString()), resourceBundle.getString("Info"),"info");
			}

			dispose();
		}
	}




	public void contextSensitiveHelpActionPerformed()
	{
		if(helpDialog == null)
		{
			helpDialog = new HelpDialog(configPanel, this);
			helpDialog.setRelatedTopics(new String[] {resourceBundle.getString("Task Lists for Execute"), resourceBundle.getString("Data Source for Templates"), resourceBundle.getString("Define Device Attributes")});
		}
		else
		{
			if(!helpDialog.isVisible())
			{
				configPanel.configClientUtils.centerWindow(helpDialog);
			}
		}

		helpDialog.setVisible(true);
	}

	public String  getHelpFor(String key)
	{
		String helpContent = "";

		if(key.equals(resourceBundle.getString("Device Configuration")))
		{
			helpContent = "\t" + resourceBundle.getString("Device Configuration is the process of setting the values that are defined for the Attributes of the selected tasks on a specific set of devices. User can select one or more tasks of the selected protocol and execute it on the devices which needs to be configured. User can select one or more devices for configuration. When the Task is defined as a Template, a Data Source has to be selected for the execution. This is because, the values that are to be substitued for the Attributes which have Place Holders defined, are specified only in the Data Sources. The attributes of the selected devices will be also listed and the user can manipulate the device attributes according to the requirements.");
		}
		else if(key.equals(resourceBundle.getString("Task Lists for Execute")))
		{
			helpContent = "\t" + resourceBundle.getString("All the existing tasks for the protocol selected will be listed from which the user can select one or more tasks for execution. The details of the selected task will also be listed. Check the check box for the task to be selected for execution."); 
		}
		else if(key.equals(resourceBundle.getString("Data Source for Templates")))
		{
			helpContent = "\t" + resourceBundle.getString("Data can be specified directly for an attribute during general task configuration. But when a task is defined as a template then data is required to be specified in either of the three input types namely Network Element Input, Inventory Input or User Input. For every input type a Place Holder or identifier is required to be specified during task configuration. For every Place Holder a value can be specified that either corresponds to a specific data or is the specified data itself. A Task defined as Template can have many Data Sources defined under it. At any point of time, the user can select one among the several Data Sources that might be present for the Template for execution purpose. After selecting the Data Source, the users can select the Device List and/or devices on which it has to be executed."); 
		}
		else if(key.equals(resourceBundle.getString("Define Device Attributes")))
		{
			helpContent = "\t" + resourceBundle.getString("Common device attributes like port, retries, timeout of the selected devices will be listed in the table. To view the complete device attributes, user can use the browse button provided in each row of the attributes table. User can also manipulate the device attributes to the requirements using the edit option."); 
		}

		return helpContent;
	}

	public String getTitle()
	{
		return resourceBundle.getString("Device Configuration");
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
Top.add(JPanel11,cons);
JPanel11.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,10);
setConstraints(1,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel11.add(JPanel2,cons);
JPanel2.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(-1,-1,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel2.add(JButton1,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel11.add(JPanel3,cons);
JPanel3.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(-1,-1,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel3.add(JLabel11,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel41,cons);
JPanel41.setLayout(new BorderLayout(5,5));
JPanel41.add(JPanel1,BorderLayout.EAST);
JPanel1.setLayout(new FlowLayout(1,5,5));
JPanel1.add(backButton);
JPanel1.add(nextButton);
JPanel1.add(cancelButton);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(JPanel31,cons);
JPanel31.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel31.add(cardPanel,cons);
cardPanel.setLayout(new CardLayout(5,5));
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.1,cons.CENTER,cons.VERTICAL,inset,0,0);
JPanel31.add(JLabel1,cons);

  
//<End_setUpGUI_Container>
	} 
	public void setUpConnections()
  {
		//<Begin_setUpConnections> 

      backButton_backButton_conn1 backButton_backButton_conn11 =  new backButton_backButton_conn1();
      backButton.addActionListener(backButton_backButton_conn11);
      nextButton_nextButton_conn1 nextButton_nextButton_conn11 =  new nextButton_nextButton_conn1();
      nextButton.addActionListener(nextButton_nextButton_conn11);
      cancelButton_cancelButton_conn1 cancelButton_cancelButton_conn11 =  new cancelButton_cancelButton_conn1();
      cancelButton.addActionListener(cancelButton_cancelButton_conn11);
  
      //<End_setUpConnections>
	} 


	//<Begin__class_backButton_backButton_conn1>

 class backButton_backButton_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_backButton_backButton_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  backButtonActionPerformed();
     }
//<UserCode_End_Connection_backButton_backButton_conn1>
 }//<End__class_backButton_backButton_conn1>
	//<Begin__class_nextButton_nextButton_conn1>

 class nextButton_nextButton_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_nextButton_nextButton_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  nextButtonActionPerformed(arg0.getActionCommand());
     }
//<UserCode_End_Connection_nextButton_nextButton_conn1>
 }//<End__class_nextButton_nextButton_conn1>
	//<Begin__class_cancelButton_cancelButton_conn1>

 class cancelButton_cancelButton_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_cancelButton_cancelButton_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  cancelButtonActionPerformed();
     }
//<UserCode_End_Connection_cancelButton_cancelButton_conn1>
 }//<End__class_cancelButton_cancelButton_conn1>

	public void actionPerformed(ActionEvent ae)
	{
		contextSensitiveHelpActionPerformed();
	}

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }

    // The below methods till the WindowStateChanged() method are included as part of operation Theme to cleanup the registered Listeners.
    public void dispose()
    {
	super.dispose();

	ActionListener[] allbackListeners = (ActionListener[])backButton.getListeners(ActionListener.class);
	if(allbackListeners != null)
	    {
		for(int removeCounter=0; removeCounter<allbackListeners.length; removeCounter++)
		    {
			backButton.removeActionListener(allbackListeners[removeCounter]);
		    }
	    }

	ActionListener[] allnextListeners = (ActionListener[])nextButton.getListeners(ActionListener.class);
	if(allnextListeners != null)
	    {
		for(int removeCounter=0; removeCounter<allnextListeners.length; removeCounter++)
		    {
			nextButton.removeActionListener(allnextListeners[removeCounter]);
		    }
	    }

	ActionListener[] allcancelListeners = (ActionListener[])cancelButton.getListeners(ActionListener.class);
	if(allcancelListeners != null)
	    {
		for(int removeCounter=0; removeCounter<allcancelListeners.length; removeCounter++)
		    {
			cancelButton.removeActionListener(allcancelListeners[removeCounter]);
		    }
	    }

	removeWindowListener(this);
    }

    public void windowClosed(WindowEvent we)
    {}

    public void windowActivated(WindowEvent we)
    {}

    public void windowClosing(WindowEvent we)
    {
	setVisible(false);
	dispose();
    }

    public void windowDeactivated(WindowEvent we)
    {}

    public void windowDeiconified(WindowEvent we)
    {}

    public void windowGainedFocus(WindowEvent we)
    {}
    
    public void windowIconified(WindowEvent we)
    {}
    
    public void windowLostFocus(WindowEvent we)
    {}

    public void windowOpened(WindowEvent we)
    {}

    public void windowStateChanged(WindowEvent we)
    {}

}






