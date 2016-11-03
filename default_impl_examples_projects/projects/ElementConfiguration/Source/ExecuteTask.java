
//$Id: ExecuteTask.java,v 1.1 2006/08/29 13:56:51 build Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.config;



import java.net.URL;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import com.adventnet.management.config.*;

public class ExecuteTask extends JDialog implements ConfigResponseListener, HelpInterface
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "ElementConfigurationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JLabel JLabel2 = null;
	javax.swing.JPanel JPanel4 = null;
	javax.swing.JPanel cardPanel = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JTextField JTextField1 = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JButton backButton = null;
	javax.swing.JButton nextButton = null;
	javax.swing.JButton cancelButton = null;
	javax.swing.JPanel JPanel5 = null;
	javax.swing.JPanel JPanel6 = null;
	javax.swing.JLabel JLabel3 = null;
	javax.swing.JPanel JPanel7 = null;
	javax.swing.JButton helpButton = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>

	private DataSourceForExecution dataSourceForExecution = null;
	private DeviceListsForExecution deviceListPanel = null;
	private AssociateDevicesDuringExecution associateDevices = null;
	private FinalScreenForExecution finalScreen= null;

	private CombinedTaskExecute combinedTaskExecute = null;
	private ConfigPanel configPanel = null;
	private Vector deviceInfo = new Vector();
	private String taskName = null;
	private Vector devices = new Vector();	
	private Vector deviceListName = new Vector();
	private boolean isCombined = false;
	private boolean isTemplate = false;
	private String protocol = "";
	private int cardIndex = 0;
	private int totalCards = 0;

	private HelpDialog helpDialog = null;

	public ExecuteTask(ConfigPanel configPanel, String taskName)
	{
		super(configPanel.configClientUtils.getParentFrame(configPanel));

		this.configPanel = configPanel;
		this.taskName = taskName;

		applet = configPanel.applet;

		init();
		configInit();
	}

	public ExecuteTask(ConfigPanel configPanel, String taskName, boolean isTemplate)
	{
		super(configPanel.configClientUtils.getParentFrame(configPanel));

		this.configPanel = configPanel;
		this.taskName = taskName;
		this.isTemplate = isTemplate;

		applet = configPanel.applet;

		init();
		configInit();
	}


	public ExecuteTask()
  {
		//<Begin_ExecuteTask>
    pack();
  
    //<End_ExecuteTask>
	}

	public ExecuteTask(java.applet.Applet applet)
  {
		//<Begin_ExecuteTask_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_ExecuteTask_java.applet.Applet>
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



	public void setUpProperties()
  { 

		//<Begin_setUpProperties> 

          try
          {
            JLabel2.setBorder(new javax.swing.border.SoftBevelBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel2,ex); 
          }

//<UserCode_Begin_Bean_JLabel2>

//<UserCode_End_Bean_JLabel2>

          try
          {
            JPanel4.setBorder(new javax.swing.border.SoftBevelBorder(0));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel4,ex); 
          }

//<UserCode_Begin_Bean_JPanel4>

//<UserCode_End_Bean_JPanel4>

          try
          {
            JPanel1.setBorder(new javax.swing.border.EtchedBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel1,ex); 
          }

//<UserCode_Begin_Bean_JPanel1>

//<UserCode_End_Bean_JPanel1>

          try
          {
            JLabel1.setForeground(new Color(-16764109));
            JLabel1.setText(resourceBundle.getString("Task Name    "));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel1,ex); 
          }

//<UserCode_Begin_Bean_JLabel1>

//<UserCode_End_Bean_JLabel1>

          try
          {
            JTextField1.setEditable(false);
            JTextField1.setText(resourceBundle.getString(""));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JTextField1,ex); 
          }

//<UserCode_Begin_Bean_JTextField1>

//<UserCode_End_Bean_JTextField1>

          try
          {
            JPanel3.setBorder(new javax.swing.border.EtchedBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel3,ex); 
          }

//<UserCode_Begin_Bean_JPanel3>

//<UserCode_End_Bean_JPanel3>

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
            JLabel3.setText(resourceBundle.getString("Execute Task"));
            JLabel3.setForeground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel3,ex); 
          }

//<UserCode_Begin_Bean_JLabel3>

//<UserCode_End_Bean_JLabel3>

          try
          {
            helpButton.setPreferredSize(new Dimension(35,23));
            helpButton.setMaximumSize(new Dimension(35,23));
            helpButton.setMinimumSize(new Dimension(35,23));
            helpButton.setBackground(new Color(-1));
            helpButton.setFocusPainted(false);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+helpButton,ex); 
          }

//<UserCode_Begin_Bean_helpButton>

//<UserCode_End_Bean_helpButton>
		cancelButton.setPreferredSize(new Dimension(cancelButton.getPreferredSize().width+19,cancelButton.getPreferredSize().height+0));
		nextButton.setPreferredSize(new Dimension(nextButton.getPreferredSize().width+31,nextButton.getPreferredSize().height+0));
		backButton.setPreferredSize(new Dimension(backButton.getPreferredSize().width+29,backButton.getPreferredSize().height+0));

  
          //<End_setUpProperties>
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
        this.setSize(getPreferredSize().width+742,getPreferredSize().height+502); 
          setTitle(resourceBundle.getString("ExecuteTask"));
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

	public void initVariables()
  { 

		//<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        JPanel2= new javax.swing.JPanel();
        JLabel2= new javax.swing.JLabel();
        JPanel4= new javax.swing.JPanel();
        cardPanel= new javax.swing.JPanel();
        JPanel1= new javax.swing.JPanel();
        JLabel1= new javax.swing.JLabel();
        JTextField1= new javax.swing.JTextField();
        JPanel3= new javax.swing.JPanel();
        backButton= new javax.swing.JButton();
        nextButton= new javax.swing.JButton();
        cancelButton= new javax.swing.JButton();
        JPanel5= new javax.swing.JPanel();
        JPanel6= new javax.swing.JPanel();
        JLabel3= new javax.swing.JLabel();
        JPanel7= new javax.swing.JPanel();
        helpButton= new javax.swing.JButton();

  
        //<End_initVariables>
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

	public void nextButtonActionPerformed()
	{
		if(cardIndex == totalCards)
		{
			finishActionPerformed();
		}
		else if(cardIndex == (totalCards - 2))
		{
			if(deviceListPanel.isExtraDevicesNeededForExecute())	
			{
				cardIndex++;
			}
			else if(deviceListPanel.getSelectedDeviceList().size() > 0)
			{
				cardIndex = cardIndex + 2;
			}
			else
			{
				configPanel.configClientUtils.showErrorDialog(this, resourceBundle.getString("Please select device or device list to execute"), resourceBundle.getString("Info"),"info");
				return;
			}
		}
		else
		{
			if((cardIndex == 1) && isTemplate)
			{
				if(dataSourceForExecution.getSelectedDataSource() == null)
				{
					configPanel.configClientUtils.showErrorDialog(this, resourceBundle.getString("Please select a Data Source"), resourceBundle.getString("Info"),"info");
					return;
				}
			}
			else if(cardIndex == totalCards - 1)
			{
				if(!(deviceListPanel.getSelectedDeviceList().size() > 0) && !(associateDevices.getSelectedDevices().size() > 0))
				{
					configPanel.configClientUtils.showErrorDialog(this, resourceBundle.getString("Please select device or device list to execute"), resourceBundle.getString("Info"),"info");
					return;
				}
			}
			cardIndex++;
		}

		CardLayout card = (CardLayout)cardPanel.getLayout();
		card.show(cardPanel, String.valueOf(cardIndex));

		if(! isCombined)
		{
			if(cardIndex == totalCards)
			{
				deviceListName = deviceListPanel.getSelectedDeviceList();
				if(deviceListName.size() > 0)
				{
					for(int j = 0; j < deviceListName.size(); j++)
					{
						Object[] params = {deviceListName.elementAt(j)};
						configPanel.configResponseHandler.sendRequest(NmsConfigConstants.GET_DEVICELIST, params, this);
					}
				}
				else if (deviceListPanel.isExtraDevicesNeededForExecute())
				{
					setDevicesForFinalScreen();
				}
				else
				{
					configPanel.configClientUtils.showErrorDialog(this, resourceBundle.getString("Please select device or device list to execute"), resourceBundle.getString("Info"),"info");
				}

				nextButton.setText(resourceBundle.getString("Finish"));
			}
		}

		if(cardIndex > 1)
		{
			backButton.setEnabled(true);
		}
	}

	public void backButtonActionPerformed()
	{
		if(cardIndex == totalCards)
		{
			if(deviceListPanel.isExtraDevicesNeededForExecute())	
			{
				cardIndex--;
			}
			else
			{
				cardIndex = cardIndex - 2;
			}

			deviceInfo.removeAllElements();
		}
		else
		{
			cardIndex--;
		}

		CardLayout card = (CardLayout)cardPanel.getLayout();
		card.show(cardPanel, String.valueOf(cardIndex));

		if(cardIndex == 1)
		{
			backButton.setEnabled(false);
		}

		if(cardIndex < totalCards)
		{
			nextButton.setText(resourceBundle.getString("Next"));
		}
	}








	private void finishActionPerformed()
	{
		ExecuteXMLGenerator executeXML = new ExecuteXMLGenerator();

		String executeXMLStr = null;

		if(!isCombined)
		{
			if(devices.size() > 0 || deviceListName.size() > 0 )
			{
				if(isTemplate)
				{
					executeXML.setDataSourceName(dataSourceForExecution.getSelectedDataSource());
				}

				executeXML.setTaskName(taskName);
				executeXML.setProtocol(protocol);
				executeXML.setDevices(devices);
				executeXML.setDeviceListNames(deviceListName);

				executeXMLStr = executeXML.getExecuteXML();
			}
			else
			{
				configPanel.configClientUtils.showErrorDialog(this, resourceBundle.getString("Please select device or device list to execute"), resourceBundle.getString("Info"),"info");
				return;
			}
		}
		else
		{
			Vector subTaskNames = configPanel.configMainUI.getSubTaskNames(taskName);

			Hashtable taskVsDLs = combinedTaskExecute.getSelectedDeviceListForSubTasks();

			Vector deviceListVector = new Vector();

			if(subTaskNames != null && taskVsDLs != null)
			{
				for(int i=0; i<subTaskNames.size(); i++)
				{
					Vector deviceLists = (Vector)taskVsDLs.get(subTaskNames.elementAt(i));

					if(deviceLists != null)
					{
						String[] dls = new String[deviceLists.size()];

						for(int j=0; j<deviceLists.size(); j++)
						{
							dls[j] = (String)deviceLists.elementAt(j);
						}

						deviceListVector.addElement(dls);
					}
				}
			}

			executeXML.setTaskName(taskName);
			executeXML.setDeviceListNames(subTaskNames, deviceListVector);

			executeXMLStr = executeXML.getExecuteXML();
		}

		if(isTemplate)
		{
			Object[] params = {executeXMLStr, dataSourceForExecution.getDataSourceParams()};
			configPanel.configResponseHandler.sendRequest(NmsConfigConstants.EXECUTE_TASK_WITH_DATASOURCE, params, this);
		}
		else
		{
			Object[] params = {executeXMLStr};
			configPanel.configResponseHandler.sendRequest(NmsConfigConstants.EXECUTE, params, this);
		}

		configPanel.configMainUI.setTaskInExecutingState(taskName);	

		setVisible(false);
	}

	private void configInit()
	{
		JLabel2.setIcon(configPanel.configClientUtils.getImage(applet.getDocumentBase()+"../images/execute.png"));

		protocol = configPanel.configMainUI.getProtocol(taskName);

		isCombined = configPanel.configMainUI.isCombined(taskName);

		JTextField1.setText(taskName);

		cardPanel.setLayout(new CardLayout());

		helpButton.setIcon(configPanel.configClientUtils.getImage(applet.getDocumentBase()+"../images/contextualhelp.png"));

		if(isCombined)
		{
			combinedTaskExecute = new CombinedTaskExecute(configPanel, taskName);

			cardIndex++;
			cardPanel.add(String.valueOf(cardIndex), combinedTaskExecute);
		}
		else
		{
			if(isTemplate)
			{
				dataSourceForExecution = new DataSourceForExecution(configPanel, taskName);

				cardIndex++;
				cardPanel.add(String.valueOf(cardIndex), dataSourceForExecution);
			}

			deviceListPanel = new DeviceListsForExecution(configPanel, taskName);

			cardIndex++;
			cardPanel.add(String.valueOf(cardIndex), deviceListPanel);

			associateDevices = new AssociateDevicesDuringExecution(configPanel, taskName);

			cardIndex++;
			cardPanel.add(String.valueOf(cardIndex), associateDevices);

			finalScreen = new FinalScreenForExecution(configPanel);

			cardIndex++;
			cardPanel.add(String.valueOf(cardIndex), finalScreen);
		}

		totalCards = cardIndex;

		cardIndex = 1;
		backButton.setEnabled(false);

		if(cardIndex == totalCards)
		{
			nextButton.setText(resourceBundle.getString("Finish"));
		}

		configPanel.configClientUtils.centerWindow(this); 
	}

	private void setDevicesForFinalScreen()
	{
		devices = associateDevices.getSelectedDevices();
		if(devices.size() > 0)
		{
			for(int i = 0; i < devices.size(); i++)
			{
				deviceInfo.addElement(devices.elementAt(i));
			}
		}
		if(deviceInfo.size() > 0)
		{
			finalScreen.setDeviceInfo(deviceInfo);
		}
		else
		{
			configPanel.configClientUtils.showErrorDialog(this, resourceBundle.getString("Please select device or device list to execute"), resourceBundle.getString("Info"),"info");
		}
	}

	public void response(ConfigResultEvent cre)
	{
		int workId = cre.getWorkID();
		int errorCode = cre.getErrorCode();
		String uniqueId = cre.getRequestID();

		if(workId == NmsConfigConstants.GET_DEVICELIST)
		{
			if(errorCode == NmsConfigConstants.NO_ERROR)
			{
				Vector device = new Vector();
				String deviceList = cre.getDeviceList();
				DeviceListReader dlr = new DeviceListReader(deviceList);
				Vector devicesVector = dlr.getDevices();
				for(int i = 0; i < devicesVector.size(); i++)
				{
					deviceInfo.addElement(devicesVector.elementAt(i));
				} 
			}
			setDevicesForFinalScreen();
		}
		else if(workId == NmsConfigConstants.EXECUTE || workId == NmsConfigConstants.EXECUTE_TASK_WITH_DATASOURCE)
		{
			configPanel.configMainUI.updateTaskLastExecutedTime(taskName);

			if(errorCode == NmsConfigConstants.NO_ERROR)
			{
				if(configPanel.isConfigPanelVisible())
				{
					ShowDetails sd = new ShowDetails(configPanel, taskName, cre);

					sd.setVisible(true);
				}
			}
			else
			{
				configPanel.configClientUtils.showErrorDialog(this, resourceBundle.getString(cre.getErrorString()), resourceBundle.getString("Info"),"info");
			}		

			dispose();
		}
	}

	public String getHelpFor(String key)
	{
		String helpContent = "";
		
		if(key.equals(resourceBundle.getString("Execute Task")))
		{
			helpContent = "\t" + resourceBundle.getString("Task execution is the process of setting the values that are defined for the Attributes on a specific set of devices. When the Task is defined as a Template, a Data Source has to be selected for the execution. This is because, the values that are to be substitued for the Attributes which has Place Holders defined, are specified only in the Data Sources. (NOTE: Updates are not sent to clients connected to the same Server when a Task gets executed)");
		}
		else if(key.equals(resourceBundle.getString("Device Lists for Execution")))
		{
			helpContent = "\t" + resourceBundle.getString("Tasks are set to be executed on any selected set of devices. These devices can be from a Device List which has already been configured for this task or can be selected individually for execution purpose. The concept of selecting individual devices for execution does not hold good for Combined Tasks. Combined Tasks can be executed only on Device Lists which are configured for the sub-tasks or on Device Lists that are defined for the protocol under which the that sub-task is defined."); 
		}
		else if(key.equals(resourceBundle.getString("Associating More Devices")))
		{
			helpContent = "\t" + resourceBundle.getString("Tasks need not necessarily have Device Lists associated with it for execution unless and otherwise it is defined as a Combined Task. Devices can be selected at Runtime by using the option provided in the User Interface. The attributes of the devices that are selected at runtime are also configurable. Combined Tasks can only be executed on Device Lists that are configured for the sub-tasks.");
		}
		else if(key.equals(resourceBundle.getString("Data Source for Templates")))
		{
			helpContent = "\t" + resourceBundle.getString("Data can be specified directly for an attribute during general task configuration. But when a task is defined as a template then data is required to be specified in either of the three input types namely Network Element Input, Inventory Input or User Input. For every input type a Place Holder or identifier is required to be specified during task configuration. For every Place Holder a value can be specified that either corresponds to a specific data or is the specified data itself. A Task defined as Template can have many Data Sources defined under it. At any point of time, the user can select one among the several Data Sources that might be present for the Template for execution purpose. After selecting the Data Source, the users can select the Device List and/or devices on which it has to be executed.");
		}

		return helpContent;
	}

	public String getTitle()
	{
		return resourceBundle.getString("Execute Task");
	}

	public void helpButtonActionPerformed()
	{
		if(helpDialog == null)
		{
			helpDialog = new HelpDialog(configPanel, this);
			helpDialog.setRelatedTopics(new String[] {resourceBundle.getString("Device Lists for Execution"), resourceBundle.getString("Associating More Devices"), resourceBundle.getString("Data Source for Templates")});
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
setConstraints(0,1,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(JPanel2,cons);
JPanel2.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.1,cons.CENTER,cons.VERTICAL,inset,0,0);
JPanel2.add(JLabel2,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel2.add(JPanel4,cons);
JPanel4.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel4.add(cardPanel,cons);
cardPanel.setLayout(new FlowLayout(1,5,5));
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel4.add(JPanel1,cons);
JPanel1.setLayout(new GridBagLayout());
inset = new Insets(5,10,5,0);
setConstraints(0,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel1.add(JLabel1,cons);
inset = new Insets(5,10,5,20);
setConstraints(1,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel1.add(JTextField1,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel3,cons);
JPanel3.setLayout(new FlowLayout(2,5,5));
JPanel3.add(backButton);
JPanel3.add(nextButton);
JPanel3.add(cancelButton);
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel5,cons);
JPanel5.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel5.add(JPanel6,cons);
JPanel6.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(-1,-1,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel6.add(JLabel3,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel5.add(JPanel7,cons);
JPanel7.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(-1,-1,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel7.add(helpButton,cons);

  
//<End_setUpGUI_Container>
	} 
	public void setUpConnections()
  {
		//<Begin_setUpConnections> 

      helpButton_helpButton_conn1 helpButton_helpButton_conn11 =  new helpButton_helpButton_conn1();
      helpButton.addActionListener(helpButton_helpButton_conn11);
      JButton2_JButton2_conn1 JButton2_JButton2_conn11 =  new JButton2_JButton2_conn1();
      nextButton.addActionListener(JButton2_JButton2_conn11);
      JButton3_JButton3_conn1 JButton3_JButton3_conn11 =  new JButton3_JButton3_conn1();
      cancelButton.addActionListener(JButton3_JButton3_conn11);
      JButton1_JButton1_conn1 JButton1_JButton1_conn11 =  new JButton1_JButton1_conn1();
      backButton.addActionListener(JButton1_JButton1_conn11);
  
      //<End_setUpConnections>
	} 


	//<Begin__class_helpButton_helpButton_conn1>

 class helpButton_helpButton_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_helpButton_helpButton_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  helpButtonActionPerformed();
     }
//<UserCode_End_Connection_helpButton_helpButton_conn1>
 }//<End__class_helpButton_helpButton_conn1>
	//<Begin__class_JButton2_JButton2_conn1>

 class JButton2_JButton2_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton2_JButton2_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  nextButtonActionPerformed();
     }
//<UserCode_End_Connection_JButton2_JButton2_conn1>
 }//<End__class_JButton2_JButton2_conn1>
	//<Begin__class_JButton3_JButton3_conn1>

 class JButton3_JButton3_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton3_JButton3_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  ExecuteTask.this.setVisible(false);
  ExecuteTask.this.dispose(); 
     }
//<UserCode_End_Connection_JButton3_JButton3_conn1>
 }//<End__class_JButton3_JButton3_conn1>
	//<Begin__class_JButton1_JButton1_conn1>

 class JButton1_JButton1_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton1_JButton1_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  backButtonActionPerformed();
     }
//<UserCode_End_Connection_JButton1_JButton1_conn1>
 }//<End__class_JButton1_JButton1_conn1>

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}






