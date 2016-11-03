
//$Id: ConfigMainUI.java,v 1.3.2.1 2012/05/29 06:26:56 karen.r Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

//<Begin_Version>
//version$1
//<End_Version>
package com.adventnet.nms.config;


import javax.swing.text.*;
import java.net.URL;
import javax.swing.table.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

import com.adventnet.nms.util.*;
import com.adventnet.management.config.*;
import com.adventnet.dmp.model.table.SortColumn;
import com.adventnet.beans.probeans.ProListViewDefaultTableModel;
import com.adventnet.beans.probeans.ProTable;
import com.adventnet.beans.probeans.ProListView;

public class ConfigMainUI extends JPanel implements ConfigResponseListener
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "ElementConfigurationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JLabel JLabel5 = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JTextField taskCountText = null;
	javax.swing.JLabel JLabel2 = null;
	javax.swing.JTextField fromText = null;
	javax.swing.JLabel JLabel3 = null;
	javax.swing.JTextField toText = null;
	javax.swing.JLabel JLabel4 = null;
	javax.swing.JComboBox pageLengthCombo = null;
	javax.swing.JButton homeButton = null;
	javax.swing.JButton previousButton = null;
	javax.swing.JButton nextButton = null;
	javax.swing.JButton endButton = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	com.adventnet.beans.probeans.ProTable table = null;
	com.adventnet.beans.probeans.ProListViewDefaultTableModel tableModel = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>

	private ConfigPanel configPanel = null;
	
           private ProListView listView = null;

	private Hashtable lastExecIDVsTask = new Hashtable();

	public ConfigMainUI(ConfigPanel panel)
	{
		configPanel = panel;

		applet = configPanel.applet;

		init();
		configInit();
	}

	public Object [] columnObject = {ConfigPanel.getInstance().configClientUtils.getString("Attributes"),ConfigPanel.getInstance().configClientUtils.getString("Value")};

	public Object [][] snmpDeviceAttributes = {{"Device Name",""},{"Port","161"},{"Community","public"},{"Snmp Version","v1"},{"User Name",""},{"Context Name",""},{"Context ID",""},{"Max-Reptitions",""}, {"Non-Repeators",""},{"Auth-Protocol",""},{"Auth-Password",""},{"Previous-Password",""},{"Retries","3"},{"Timeout","5000"}};
	public Object [][] telnetDeviceAttributes = {{"Device Name",""},{"Port","23"},{"Login Name",""},{"Password",""},{"Login Prompt","login"},{"Password Prompt","Password"},{"Shell Prompt","$"},{"Retries","3"},{"Timeout","5000"}};
	public Object [][] tftpDeviceAttributes = {{"Device Name",""},{"Port","69"},{"Retries","3"},{"Timeout","5000"}};
	public Object [][] tl1DeviceAttributes = {{"Device Name",""},{"Port","9099"},{"Retries","3"},{"Timeout","5000"}};
	public Object [][] ftpDeviceAttributes = {{"Device Name",""},{"Port","21"},{"User Name",""},{"Password",""},{"Retries","3"},{"Timeout","5000"}};
	public Object [][] netconfDeviceAttributes = {{"Device Name",""},{"Port","2022"},{"User Name",""},{"Password",""},{"Retries","3"},{"Timeout","5000"}};//No I18N


	private Vector mainVectorForAllTasks = new Vector();
	private int selectedPageLength = 25;
	private Vector columnVector = new Vector();



	public void setUpProperties()
  { 

		//<Begin_setUpProperties> 

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
            JLabel5.setText(resourceBundle.getString("Batch Configuration"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel5,ex); 
          }

//<UserCode_Begin_Bean_JLabel5>

//<UserCode_End_Bean_JLabel5>

          try
          {
            JLabel1.setText(resourceBundle.getString("Total"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel1,ex); 
          }

//<UserCode_Begin_Bean_JLabel1>

//<UserCode_End_Bean_JLabel1>

          try
          {
            taskCountText.setEditable(false);
            taskCountText.setText(resourceBundle.getString(""));
            taskCountText.setPreferredSize(new Dimension(50,20));
            taskCountText.setMaximumSize(new Dimension(50,20));
            taskCountText.setMinimumSize(new Dimension(50,20));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+taskCountText,ex); 
          }

//<UserCode_Begin_Bean_taskCountText>

//<UserCode_End_Bean_taskCountText>

          try
          {
            JLabel2.setText(resourceBundle.getString("Displaying  "));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel2,ex); 
          }

//<UserCode_Begin_Bean_JLabel2>

//<UserCode_End_Bean_JLabel2>

          try
          {
            fromText.setHorizontalAlignment(0);
            fromText.setMaximumSize(new Dimension(50,20));
            fromText.setMinimumSize(new Dimension(50,20));
            fromText.setPreferredSize(new Dimension(50,20));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+fromText,ex); 
          }

//<UserCode_Begin_Bean_fromText>

//<UserCode_End_Bean_fromText>

          try
          {
            JLabel3.setText(resourceBundle.getString("    to    "));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel3,ex); 
          }

//<UserCode_Begin_Bean_JLabel3>

//<UserCode_End_Bean_JLabel3>

          try
          {
            toText.setHorizontalAlignment(0);
            toText.setPreferredSize(new Dimension(50,20));
            toText.setMaximumSize(new Dimension(50,20));
            toText.setMinimumSize(new Dimension(50,20));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+toText,ex); 
          }

//<UserCode_Begin_Bean_toText>

//<UserCode_End_Bean_toText>

          try
          {
            JLabel4.setText(resourceBundle.getString("Page Length   "));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel4,ex); 
          }

//<UserCode_Begin_Bean_JLabel4>

//<UserCode_End_Bean_JLabel4>

          try
          {
            pageLengthCombo.setPreferredSize(new Dimension(55,20));
            pageLengthCombo.setMinimumSize(new Dimension(55,20));
            pageLengthCombo.setMaximumSize(new Dimension(55,20));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+pageLengthCombo,ex); 
          }

//<UserCode_Begin_Bean_pageLengthCombo>

//<UserCode_End_Bean_pageLengthCombo>

          try
          {
            homeButton.setMaximumSize(new Dimension(30,20));
            homeButton.setMinimumSize(new Dimension(30,20));
            homeButton.setPreferredSize(new Dimension(30,20));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+homeButton,ex); 
          }

//<UserCode_Begin_Bean_homeButton>
	homeButton.setBorderPainted(false);
//<UserCode_End_Bean_homeButton>

          try
          {
            previousButton.setMaximumSize(new Dimension(30,20));
            previousButton.setMinimumSize(new Dimension(30,20));
            previousButton.setPreferredSize(new Dimension(30,20));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+previousButton,ex); 
          }

//<UserCode_Begin_Bean_previousButton>
	previousButton.setBorderPainted(false);
//<UserCode_End_Bean_previousButton>

          try
          {
            nextButton.setPreferredSize(new Dimension(30,20));
            nextButton.setMinimumSize(new Dimension(30,20));
            nextButton.setMaximumSize(new Dimension(30,20));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+nextButton,ex); 
          }

//<UserCode_Begin_Bean_nextButton>
	nextButton.setBorderPainted(false);
//<UserCode_End_Bean_nextButton>

          try
          {
            endButton.setMaximumSize(new Dimension(30,20));
            endButton.setMinimumSize(new Dimension(30,20));
            endButton.setPreferredSize(new Dimension(30,20));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+endButton,ex); 
          }

//<UserCode_Begin_Bean_endButton>
	endButton.setBorderPainted(false);
//<UserCode_End_Bean_endButton>

          try
          {
            table.setRowHeight(21);
            table.setShowVerticalLines(false);
            table.setShowHorizontalLines(false);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+table,ex); 
          }

//<UserCode_Begin_Bean_table>

//<UserCode_End_Bean_table>
		JScrollPane1.setPreferredSize(new Dimension(JScrollPane1.getPreferredSize().width+2,JScrollPane1.getPreferredSize().height+2));

  
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
        setPreferredSize(new Dimension(getPreferredSize().width+828,getPreferredSize().height+568)); 
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
	 tableModel.setDataVector(mainVectorForAllTasks);  
	} 

	public void initVariables()
  { 

		//<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        JPanel1= new javax.swing.JPanel();
        JLabel5= new javax.swing.JLabel();
        JLabel1= new javax.swing.JLabel();
        taskCountText= new javax.swing.JTextField();
        JLabel2= new javax.swing.JLabel();
        fromText= new javax.swing.JTextField();
        JLabel3= new javax.swing.JLabel();
        toText= new javax.swing.JTextField();
        JLabel4= new javax.swing.JLabel();
        pageLengthCombo= new javax.swing.JComboBox();
        homeButton= new javax.swing.JButton();
        previousButton= new javax.swing.JButton();
        nextButton= new javax.swing.JButton();
        endButton= new javax.swing.JButton();
        JPanel2= new javax.swing.JPanel();
        JScrollPane1= new javax.swing.JScrollPane();
        table= null;
        tableModel= null;

  
        //<End_initVariables>
	   columnVector.addElement(resourceBundle.getString("Task Name"));//No I18N
 	         columnVector.addElement(resourceBundle.getString("Task Description"));//No I18N
 	         columnVector.addElement(resourceBundle.getString("Protocol"));//No I18N
 	         columnVector.addElement(resourceBundle.getString("Template"));//No I18N
 	         columnVector.addElement(resourceBundle.getString("Rollback"));//No I18N
 	         columnVector.addElement(resourceBundle.getString("Last Executed Time"));//No I18N
 	         tableModel = new ProListViewDefaultTableModel(columnVector);
 	         listView = new ProListView();
 	         
 	         try
 	         {
 	                 listView.setListViewModel(tableModel);
 	                 table = listView.getProTable();
 	         }
 	         catch(Exception ex)
 	         {
 	                 System.err.println(ex.getMessage());    
 	         }
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
            if (input.equals("RESOURCE_PROPERTIES")) value = "ElementConfigurationResources"; 
            if (input.equals("HOST")) value = "localhost"; 
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









	public ConfigMainUI()
  {
		//<Begin_ConfigMainUI>
    this.init();
  
    //<End_ConfigMainUI>
	}

	public ConfigMainUI(java.applet.Applet applet)
  {
		//<Begin_ConfigMainUI_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_ConfigMainUI_java.applet.Applet>
	}

	public void menuActionPerformed(String actionCommand)
	{
		if(actionCommand.equals("add task"))
		{
			addTaskActionPerformed();
		}
		else if(actionCommand.equals("remove task"))
		{
			removeTaskActionPerformed();
		}
		else if(actionCommand.equals("task details"))
		{
			viewTaskDetailsActionPerformed();
		}
		else if(actionCommand.equals("upload task"))
		{
			uploadTaskActionPerformed();
		}
		else if(actionCommand.equals("associate devices"))
		{
			deviceListOperationsActionPerformed();
		}
		else if(actionCommand.equals("execute"))	
		{
			executeActionPerformed();	
		}
		else if(actionCommand.equals("modify task"))
		{
			modifyTaskActionPerformed();
		}
		else if(actionCommand.equals("data source operations"))
		{
			dataSourceOperationsActionPerformed();
		}
		else if(actionCommand.equals("task audit details"))
		{
			viewTaskHistoryActionPerformed();
		}
	}

	private void addTaskActionPerformed()
	{
		AddNewTask addTask = new AddNewTask(configPanel);
		configPanel.configClientUtils.centerWindow(addTask);
		addTask.setVisible(true);
	}

	private void uploadTaskActionPerformed()
	{
		Object[] selectedTask = getSelectedTaskNames();

		if(selectedTask != null && selectedTask.length > 0)
		{
			if(!isCombined((String)selectedTask[0]) && !isTemplate((String)selectedTask[0]))
			{
				TaskUpload upload = new TaskUpload(configPanel, (String)selectedTask[0], getProtocol((String)selectedTask[0]));
				upload.setVisible(true);	
			}
			else
			{
				configPanel.configClientUtils.showErrorDialog(configPanel.configClientUtils.getParentFrame(this), resourceBundle.getString("Upload cannot be done for Combined Tasks and Templates"), resourceBundle.getString("Info"), "info");
			}
		}
		else
		{
			configPanel.configClientUtils.showErrorDialog(configPanel.configClientUtils.getParentFrame(this), resourceBundle.getString("No task selected"), resourceBundle.getString("Info"), "info");
		}
	}

	private void deviceListOperationsActionPerformed()
	{
		Object[] selectedTask = getSelectedTaskNames();

		if(selectedTask != null && selectedTask.length > 0)
		{
			if(!isCombined((String)selectedTask[0]))
			{
				DeviceListOperation list = new DeviceListOperation(configPanel, (String)selectedTask[0]);
				list.setVisible(true);	
			}
			else
			{
				configPanel.configClientUtils.showErrorDialog(configPanel.configClientUtils.getParentFrame(this), resourceBundle.getString("Device list cannot be associated for Combined Tasks"), resourceBundle.getString("Info"), "info");
			}
		}
		else
		{
			configPanel.configClientUtils.showErrorDialog(configPanel.configClientUtils.getParentFrame(this), resourceBundle.getString("No task selected"), resourceBundle.getString("Info"), "info");
		}
	}

	private void viewTaskDetailsActionPerformed()
	{
		Object[] selectedTask = getSelectedTaskNames();

		if(selectedTask != null && selectedTask.length > 0)
		{
			TaskDetails details = new TaskDetails(configPanel, getTaskDetails((String)selectedTask[0]));
			configPanel.configClientUtils.centerWindow(details);
		}
		else
		{
			configPanel.configClientUtils.showErrorDialog(configPanel.configClientUtils.getParentFrame(this), resourceBundle.getString("No task selected"), resourceBundle.getString("Info"), "info");
		}
	}

	private void viewTaskHistoryActionPerformed()
	{
		Object[] selectedTask = getSelectedTaskNames();

		if(selectedTask != null && selectedTask.length > 0)
		{
			if(!isTaskAlreadyExecuted((String)selectedTask[0]))
			{
				configPanel.configClientUtils.showErrorDialog(configPanel.configClientUtils.getParentFrame(this), resourceBundle.getString("The selected task is not yet executed"), resourceBundle.getString("Info"), "info");
			}
			else if(isTaskCurrentlyExecuting((String)selectedTask[0]))
			{
				configPanel.configClientUtils.showErrorDialog(configPanel.configClientUtils.getParentFrame(this), resourceBundle.getString("The selected task is under execution"), resourceBundle.getString("Info"), "info");
			}
			else
			{
				ViewTaskHistory history = new ViewTaskHistory(configPanel, (String)selectedTask[0]);
				history.setVisible(true);				
			}
		}
		else
		{
			configPanel.configClientUtils.showErrorDialog(configPanel.configClientUtils.getParentFrame(this), resourceBundle.getString("No task selected"), resourceBundle.getString("Info"), "info");
		}
	}

/*
	public void setVector(Vector v)
	{
		tableModel.addRow(v);
	}
*/

	public void dataSourceOperationsActionPerformed()
	{
		Object[] selectedTaskNames = getSelectedTaskNames();

		if(selectedTaskNames != null && selectedTaskNames.length > 0)
		{
			if(isTemplate((String)selectedTaskNames[0]))
			{

				DataSourceForTemplate ds = new DataSourceForTemplate(configPanel, (String)selectedTaskNames[0]);
				ds.setVisible(true);
			}
			else
			{
				configPanel.configClientUtils.showErrorDialog(configPanel.configClientUtils.getParentFrame(this), resourceBundle.getString("Please select template"), resourceBundle.getString("Info"), "info");
			}
		}
		else
		{
			configPanel.configClientUtils.showErrorDialog(configPanel.configClientUtils.getParentFrame(this), resourceBundle.getString("No task selected"), resourceBundle.getString("Info"), "info");
		}
	}


	public void response(ConfigResultEvent configResultEvent)
	{
		int workID = configResultEvent.getWorkID();
		int errorCode = configResultEvent.getErrorCode();
		String uniqueID = configResultEvent.getRequestID();

		if(workID == NmsConfigConstants.GET_TASKS)
		{
			addExistingTasksToView(configResultEvent.getTasks());
		}
		else if(workID == NmsConfigConstants.GET_TASK)
		{
			extractTask(configResultEvent.getTask());
		}
		else if(workID == NmsConfigConstants.GET_LASTEXECUTED_TIME)
		{
			String taskName = (String)lastExecIDVsTask.remove(uniqueID);

			String[] str = configResultEvent.getLastExecutedTime();

			String formattedDate = "Not Executed";

			if(str != null && str.length == 1)
			{
				long date = Long.parseLong(str[0]);

				if(date != -1)
				{
					formattedDate = configPanel.configClientUtils.formatDate(date);
				}
			}

			if(taskName != null)
			{
				int row = getRow(taskName);	

				if(row != -1)
				{
					tableModel.setValueAt(formattedDate, row, 5);
				}
				else
				{
					Vector taskDetails = getTaskDetails(taskName);

					if(taskDetails != null)
					{
						taskDetails.setElementAt(formattedDate, 5);
					}
				}
			}
		}
	}

	private void addExistingTasksToView(Vector tasks)
	{
		if(tasks != null && tasks.size() > 0)
		{
			for(int i=0; i<tasks.size(); i++)
			{
				Object[] params = {tasks.elementAt(i)};
				configPanel.configResponseHandler.sendRequest(NmsConfigConstants.GET_TASK, params, this);
			}
		}
		else
		{
			fromText.setText("0");
			toText.setText("0");
			taskCountText.setText("0");
		}
	}

	private void extractTask(String task)	
	{
		if(task != null)
		{
			ConfigTaskReader reader = new ConfigTaskReader(configPanel, task);

			Vector taskData = new Vector();

			String taskName = reader.getTaskName();

			taskData.addElement(taskName);
			taskData.addElement(reader.getTaskDesc());

			if(reader.isCombinedTask())
			{
				Vector subTaskNames = reader.getTaskAttributes();

				String combined = "COMBINED(";

				if(subTaskNames != null)
				{
					for(int i=0; i<subTaskNames.size(); i++)
					{
						if(i < (subTaskNames.size()-1))
						{
							combined = combined + (String)subTaskNames.elementAt(i) + ",";
						}
						else
						{
							combined = combined + (String)subTaskNames.elementAt(i);
						}
					}
				}

				combined = combined + ")";

				taskData.addElement(combined);
			}
			else
			{	
				if(reader.getProtocol() != null)
				{
					taskData.addElement(reader.getProtocol().toUpperCase());	
				}
				else
				{
					taskData.addElement("");
				}	
			}

			taskData.addElement(String.valueOf(reader.isTemplate()));
			taskData.addElement(reader.getRollbackDocument());
			taskData.addElement("Not Executed");

			if(!configPanel.isStandAlone)
				updateTaskLastExecutedTime(taskName);

			addTaskToView(taskData);
		}

	}

	public void updateTaskLastExecutedTime(String taskName)	
	{
		String[] taskArr = {taskName};

		Object[] params = {taskArr};

		String uniqueID = configPanel.configResponseHandler.sendRequest(NmsConfigConstants.GET_LASTEXECUTED_TIME, params, this);
		lastExecIDVsTask.put(uniqueID, taskName);
	}

	public void setTaskInExecutingState(String taskName)	
	{
		int row = getRow(taskName);
		
		Vector taskDetails = getTaskDetails(taskName);

			if(taskDetails != null)
			{
				taskDetails.setElementAt("Executing ...", 5);
			}
		reload();
	}

	public boolean isTaskCurrentlyExecuting(String taskName)
	{
		boolean bool = false;

		Vector taskDetails = getTaskDetails(taskName);

		if(taskName != null)
		{
			String str = (String)taskDetails.elementAt(5);

			if(str.equals("Executing ..."))
			{
				bool = true;
			}
		}

		return bool;
	}

	private boolean firstTime = true;
 
	public void addTaskToView(Vector taskData)
	{

		mainVectorForAllTasks.addElement(taskData);

		if(firstTime)
		{
 	           	fromText.setText("1");//No I18N
 	                      firstTime =false;
 	           }

		if(mainVectorForAllTasks.size() > selectedPageLength)
		{
			nextButton.setEnabled(true);
			endButton.setEnabled(true);
			toText.setText(selectedPageLength + "");
		}
		else
		{
			toText.setText((mainVectorForAllTasks.size()) + "");
		}

		reload();
		taskCountText.setText(""+(mainVectorForAllTasks.size()));
	}

	private void reload()
	{
		int start = Integer.parseInt(fromText.getText());
		int end = Integer.parseInt(toText.getText());
		
		tableModel.setDataVector(mainVectorForAllTasks);

		int size = mainVectorForAllTasks.size();
		if (size != 0)
		{
			try
			{				
				int sortColumn = listView.getSortHeaderRenderer().getDataSortColumn();
		        if(sortColumn != -1)
		        {
		        	boolean sortOrder = listView.getSortHeaderRenderer().getDataSortOrder();
		        	sortColumn = table.convertColumnIndexToModel(sortColumn);
		        	SortColumn[] col = {new SortColumn(String.valueOf(sortColumn),sortOrder)};
		        	tableModel.sort(col);
				}
		        else
		        {
		        	tableModel.setRange(start,end);
		        }
			}
			catch (Exception ex)
			{
				System.err.println(ex.getMessage());
			}
		}
		else
		{
			try
			{
				tableModel.refresh();
			}
			catch(Exception exp){exp.printStackTrace();}
			fromText.setText("0");
			toText.setText("0");
			taskCountText.setText("0");
			firstTime = true;
		}
		
	}
	
	private int getVectIndex(String taskName)
	{
 		int start = -1;
 	                 
 	           for(int i=0;i< mainVectorForAllTasks.size();i++)
 	           {
 	           	Vector vect = (Vector) mainVectorForAllTasks.elementAt(i);
 	                      String val = (String) vect.elementAt(0);
 	                      if(taskName.equals(val))
 	                      {
 	                      	start = i;
 	                                 break;
			}
		}
 	                 
 	           return start;
	}
	
	public void updateTaskView(String taskName, Vector taskData)
	{
		int row = getRow(taskName);

		if(row != -1)
		{
			int index = getVectIndex(taskName);
			mainVectorForAllTasks.setElementAt(taskData,index);
			reload();
		}
	}

	private void removeTaskFromView(String taskName)
	{
		String from = fromText.getText().trim();
		String to = toText.getText();
		if( ! from.equals("") && !to.equals(""))
		{
			int start = Integer.parseInt(from);
			int toInt = Integer.parseInt(to);
			start = start+getRow(taskName)-1;
			start = getVectIndex(taskName);
			mainVectorForAllTasks.removeElementAt(start);

			taskCountText.setText(""+(mainVectorForAllTasks.size()));
			if(mainVectorForAllTasks.size() < selectedPageLength && (toInt >1))
 	                      {
			toText.setText(""+(toInt -1));
 	                      }
 	                     	reload();
		}	
	}

	private int getRow(String taskName)	
	{
		int rowsCount = tableModel.getRowCount();

		if(rowsCount > 0)
		{
			for(int i=0; i<rowsCount; i++)
			{
				if((tableModel.getValueAt(i, 0)).equals(taskName))
				{
					return i;
				}
			}
		}

		return -1;			
	}

	public boolean isTaskAlreadyPresent(String taskName)
	{
		if(getRow(taskName) != -1)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	private void modifyTaskActionPerformed()
	{
		Object[] selectedTaskNames = getSelectedTaskNames();

		if(selectedTaskNames != null && selectedTaskNames.length > 0)
		{
			AddNewTask  addTask = new AddNewTask(configPanel, getTaskDetails((String)selectedTaskNames[0]));
			configPanel.configClientUtils.centerWindow(addTask);
			addTask.setVisible(true);
		}
		else
		{
			configPanel.configClientUtils.showErrorDialog(configPanel.configClientUtils.getParentFrame(this), resourceBundle.getString("No task selected"), resourceBundle.getString("Info"), "info");
		}
	}

	public void removeTaskActionPerformed()
	{
		Object[] selectedTaskNames = getSelectedTaskNames();

		if(selectedTaskNames != null && selectedTaskNames.length > 0)
		{
			if(configPanel.configClientUtils.getConfirmation(this))
			{
				for(int i=0; i<selectedTaskNames.length; i++)
				{
					Object[] params = {selectedTaskNames[i]};
					configPanel.configResponseHandler.sendRequest(NmsConfigConstants.DELETE_TASK, params, this);
					removeTaskFromView(params[0].toString());
				}
			}
		}
		else
		{
			configPanel.configClientUtils.showErrorDialog(configPanel.configClientUtils.getParentFrame(this), resourceBundle.getString("No task selected"), resourceBundle.getString("Info"), "info");
		}
	}

	public Object[] getSelectedTaskNames()
	{
		Object[] selectedTaskNames = null;

		int[] selectedRows = table.getSelectedRows();
		int length = selectedRows.length;

		if(length > 0)
		{
			selectedTaskNames = new Object[length];

			for(int i=0; i < length; i++)
			{
				selectedTaskNames[i] = tableModel.getValueAt(selectedRows[i], 0);
			}
		}

		return selectedTaskNames;
	}

	private void executeActionPerformed()
	{
		Object[] selectedTask = getSelectedTaskNames();

		if(selectedTask != null && selectedTask.length > 0)
		{
			String selectedTaskName = selectedTask[0].toString();

			if(isCombined(selectedTaskName))
			{
				ExecuteTask exec = new ExecuteTask(configPanel, selectedTaskName);
				exec.setVisible(true);
			}
			else
			{
				ExecuteTask exec = new ExecuteTask(configPanel, selectedTaskName, isTemplate(selectedTaskName));
				exec.setVisible(true);
			}
		}
		else
		{
			configPanel.configClientUtils.showErrorDialog(configPanel.configClientUtils.getParentFrame(this), resourceBundle.getString("No task selected"), resourceBundle.getString("Info"), "info");
		}
	}

	public boolean isCombined(String taskName)
	{
		boolean bool = false;

		Vector taskDetails = getTaskDetails(taskName);

		if(taskDetails != null)
		{
			String str = (String)taskDetails.elementAt(2);

			if(str.startsWith("COMBINED"))
			{
				bool = true;
			}
		}

		return bool;
	}

	public boolean isTemplate(String taskName)
	{
		boolean bool = false;

		Vector taskDetails = getTaskDetails(taskName);

		if(taskDetails != null)
		{
			String str = (String)taskDetails.elementAt(3);

			if(str.equalsIgnoreCase("true"))
			{
				bool = true;
			}
		}

		return bool;
	}

	public Vector getSubTaskNames(String taskName)
	{
		Vector subTaskNames = null;

		if(isCombined(taskName))
		{
			Vector taskDetails = getTaskDetails(taskName);

			if(taskDetails != null)
			{
				String str = (String)taskDetails.elementAt(2);

				StringTokenizer tokenizer = new StringTokenizer(str.substring(str.indexOf('(')+1, str.lastIndexOf(')')), ",");

				subTaskNames = new Vector();

				while(tokenizer.hasMoreTokens())
				{
					subTaskNames.addElement(tokenizer.nextToken().trim());	
				}
			}
		}

		return subTaskNames;
	}

	public String getProtocol(String taskName)
	{
		String protocol = null;

		Vector taskDetails = getTaskDetails(taskName);

		if(taskDetails != null)
		{
			protocol = (String)taskDetails.elementAt(2);
		}

		return protocol;
	}

	public Vector getTasksForProtocol(String protocol)
	{
		Vector tasks = new Vector();

		int rowCount = tableModel.getRowCount();

		for(int i=0; i<mainVectorForAllTasks.size(); i++)
		{
			Vector element = (Vector)mainVectorForAllTasks.elementAt(i);

			if(((String)element.elementAt(2)).equalsIgnoreCase(protocol))
			{
				tasks.addElement(element.elementAt(0));
			}
		}

		return tasks;
	}

	public Vector getProtocolSpecificTasks(String protocol)
	{
		Vector tasks = null;

		for(int i=0; i<mainVectorForAllTasks.size(); i++)
		{
			Vector element = (Vector)mainVectorForAllTasks.elementAt(i);

			if(((String)element.elementAt(2)).equalsIgnoreCase(protocol) && !isTemplate(element.elementAt(0).toString()))
			{
				if(tasks == null)
					tasks = new Vector();
				tasks.addElement(element.elementAt(0));
			}
		}

		return tasks;
	}

	public Hashtable getAllTasks()
	{
		Hashtable allTasks = new Hashtable();

		allTasks.put("snmp", getTasksForProtocol("snmp"));
		allTasks.put("tl1", getTasksForProtocol("tl1"));
		allTasks.put("telnet", getTasksForProtocol("telnet"));
		allTasks.put("tftp", getTasksForProtocol("tftp"));
		allTasks.put("ftp", getTasksForProtocol("ftp"));
		allTasks.put("netconf", getTasksForProtocol("netconf"));//No I18N
		return allTasks;
	}

	public Vector getTaskDetails(String taskName)
	{
		Vector taskDetails = null;

		for(int i=0; i<mainVectorForAllTasks.size(); i++)
		{
			taskDetails = (Vector)mainVectorForAllTasks.elementAt(i);

			if((taskDetails.elementAt(0)).equals(taskName))
			{
				break;
			}
			else
			{
				taskDetails = null;
			}
		}

		return taskDetails;
	}










	public boolean isTaskAlreadyExecuted(String taskName)
	{
		boolean bool = true;

		Vector taskDetails = getTaskDetails(taskName);

		if(taskDetails != null)
		{
			String str = (String)taskDetails.elementAt(5);

			if(str.equals("Not Executed"))
			{
				bool = false;
			}
		}

		return bool;

	}

	private void configInit()
	{
		if(configPanel.isStandAlone)
		{
			JPanel1.setVisible(false);
		}
		
		try
		{
			fromText.setDocument(new CustomTextDocument());
			toText.setDocument(new CustomTextDocument());
			fromText.setText("0");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		pageLengthCombo.addItem("25");
		pageLengthCombo.addItem("50");
		pageLengthCombo.addItem("75");
		pageLengthCombo.addItem("100");		
		homeButton.setEnabled(false);
		nextButton.setEnabled(false);
		previousButton.setEnabled(false);
		endButton.setEnabled(false);
		table.setSelectionBackground(new Color(180,180,180));
		table.setDefaultRenderer(Object.class,new SimpleTableSelectionRenderer());
		table.setDefaultEditor(Object.class,null);
		TableColumnModel colModel=table.getColumnModel();
		int count=colModel.getColumnCount();
		for(int i=0;i<count;i++){
			colModel.getColumn(i).setCellRenderer(new ProtocolIconRenderer(configPanel));
		}
		URL docBase = configPanel.applet.getDocumentBase();
		String test = docBase+"../images/batchconfigtreeicon.png" ;
		ImageIcon satishBug = configPanel.configClientUtils.getImage(test);
		JLabel5.setIcon(satishBug);
		String test1 = docBase+"../images/first.png" ;
		ImageIcon satishBug1 = configPanel.configClientUtils.getImage(test1);
		homeButton.setIcon(satishBug1);
		String test2 = docBase+"../images/back1.png" ;
		ImageIcon satishBug2 = configPanel.configClientUtils.getImage(test2);
		previousButton.setIcon(satishBug2);
		String test3 = docBase+"../images/next.png" ;
		ImageIcon satishBug3 = configPanel.configClientUtils.getImage(test3);
		nextButton.setIcon(satishBug3);
		String test4 = docBase+"../images/last.png" ;
		ImageIcon satishBug4 = configPanel.configClientUtils.getImage(test4);
		endButton.setIcon(satishBug4);

		String image=docBase+"../images/first_mo.png" ;
		homeButton.setRolloverIcon(configPanel.configClientUtils.getImage(image));
		image=docBase+"../images/back_mo.png" ;
		previousButton.setRolloverIcon(configPanel.configClientUtils.getImage(image));
		image=docBase+"../images/next_mo.png" ;
		nextButton.setRolloverIcon(configPanel.configClientUtils.getImage(image));
		image=docBase+"../images/last_mo.png" ;
		endButton.setRolloverIcon(configPanel.configClientUtils.getImage(image));
		image=docBase+"../images/first_dis.png" ;
		homeButton.setDisabledIcon(configPanel.configClientUtils.getImage(image));
		image=docBase+"../images/back1_dis.png" ;
		previousButton.setDisabledIcon(configPanel.configClientUtils.getImage(image));
		image=docBase+"../images/next_dis.png" ;
		nextButton.setDisabledIcon(configPanel.configClientUtils.getImage(image));
		image=docBase+"../images/last_dis.png" ;
		endButton.setDisabledIcon(configPanel.configClientUtils.getImage(image));

		ImageIcon icon = configPanel.configClientUtils.getImage(docBase+"../images/devices.png") ;
		//taskCountText.setText(""+tableModel.getRowCount());
		table.addMouseListener(mouseListener);
		JTableHeader header = table.getTableHeader();

		header.setPreferredSize(new Dimension((int)(table.getPreferredSize()).getWidth(),23));
		header.setMinimumSize(new Dimension((int)(table.getMinimumSize()).getWidth(),23));		
		configPanel.configResponseHandler.sendRequest(NmsConfigConstants.GET_TASKS, null, this);
	}

	public void homeButtonActionPerformed()
	{
		String str = pageLengthCombo.getSelectedItem().toString();
		int number = Integer.parseInt(str);
		if( number < mainVectorForAllTasks.size())
		{
			fromText.setText(""+1);
			toText.setText(""+number);
			try
			{
			       tableModel.first();
			}
			catch(Exception ex)
			{
			           System.err.println(ex.getMessage());
			}
		
		}

		homeButton.setEnabled(false);
		previousButton.setEnabled(false);
		nextButton.setEnabled(true);
		endButton.setEnabled(true);
	}

	public void endButtonActionPerformed()
	{
		String str = pageLengthCombo.getSelectedItem().toString();
		int number = Integer.parseInt(str);
		if( number < mainVectorForAllTasks.size())
		{
			int destin = mainVectorForAllTasks.size();
			int start = destin - selectedPageLength;
			if(start <=0)
				start =1;

			if((start+1) == 1)
			{
				homeButton.setEnabled(false);
				previousButton.setEnabled(false);
			}
			else
			{
				homeButton.setEnabled(true);
				previousButton.setEnabled(true);
			}
			nextButton.setEnabled(false);
			endButton.setEnabled(false);

			fromText.setText(""+(start+1));
			toText.setText(""+destin);
			try
			{
			       tableModel.last();
			}
			catch(Exception ex)
			{
			           System.err.println(ex.getMessage());
			}
		}

	}	

	public void previousButtonActionPerformed()
	{
		nextButton.setEnabled(true);
		endButton.setEnabled(true);
		viewPrevious();
	}

	public void viewPrevious()
	{
		String from = fromText.getText();
		String to = toText.getText();

		int fromInt = 0;
		int toInt = 0;
		if( ! from.equals("") && !to.equals(""))
		{
			try
			{
				fromInt = Integer.parseInt(from);
				toInt = Integer.parseInt(to);
			}

			catch (NumberFormatException ne)
			{
				Toolkit.getDefaultToolkit().beep();
				return;
			}

			int destin = fromInt;

			int start = fromInt-selectedPageLength;
			if(start <= 1)
			{
				start = 1;
				if (selectedPageLength < mainVectorForAllTasks.size())
					destin = selectedPageLength+1;
				previousButton.setEnabled(false);
				homeButton.setEnabled(false);
			}

			else
			{
				previousButton.setEnabled(true);
				homeButton.setEnabled(true);
			}

			if(destin != 1)
				--destin;
			if(start ==0)
				start =1;
			fromText.setText(""+start);
			toText.setText(""+destin);
			try
 	                      {
 	                                 tableModel.previous();
 	                      }
 	                      catch (Exception ex)
			{
 	                      	System.err.println(ex.getMessage());
			}
		}		
	}

	public void nextButtonActionPerformed()
	{
		previousButton.setEnabled(true);
		homeButton.setEnabled(true);
		viewNext();
	}

	public void viewNext()
	{
		String from = fromText.getText();
		String to = toText.getText();
		int fromInt = 0;
		int toInt = 0;
		if( ! from.equals("") && ! toText.equals(""))
		{
			try
			{
				fromInt = Integer.parseInt(from);
				toInt = Integer.parseInt(to);
			}
			catch (NumberFormatException ne)
			{
				//Toolkit.getDefaultToolkit().beep();
				return;
			}

			int start = fromInt+selectedPageLength;
			start = toInt;
			int destin = start+selectedPageLength;
			if(destin >= mainVectorForAllTasks.size())
			{
				destin = mainVectorForAllTasks.size();
				nextButton.setEnabled(false);
				endButton.setEnabled(false);
			}

			else
			{
				nextButton.setEnabled(true);
				endButton.setEnabled(true);
			}


			++start;
			fromText.setText(""+start);
			toText.setText(""+(destin));
			                     
 	                         try
 	                         {
 	                                 tableModel.next();
 	                         }
 	                         catch (Exception ex)
 	                         {
 	                                 System.err.println(ex.getMessage());
 	                         }
		}		
	}





	MouseListener mouseListener = new MouseAdapter(){

		public void mouseClicked(MouseEvent me)
		{
			if(configPanel.isStandAlone)
 			{
 				return;
 			}

			Object source = me.getSource();

			if(me.getClickCount() == 2)
			{
				viewTaskDetailsActionPerformed();
			}
			if(source instanceof JTable)
			{
				if(me.isMetaDown())
				{
					JTable table = (JTable)source;

					int x_co = me.getX();
					int y_co = me.getY();

					int row = table.rowAtPoint(new Point(x_co, y_co));

					if(row != -1)
					{
						table.setRowSelectionInterval(row, row);

						JPopupMenu popupMenu = new JPopupMenu();

						String tablePopup = (String)configPanel.configNodeProperties.get("TABLE-POPUP-MENU");

						tablePopup = NmsClientUtil.GetString(tablePopup);

						JMenuBar panelMenuBar = configPanel.getPanelMenuBar();

						for(int i=0; i<panelMenuBar.getMenuCount(); i++)
						{
							JMenu menu = panelMenuBar.getMenu(i);

							if(NmsClientUtil.validateMenuText(menu.getText(), tablePopup))
							{
								JMenu clonedMenu = NmsClientUtil.cloneMenu(menu);
								
								if(clonedMenu != null)
								{
									int itemCount = clonedMenu.getItemCount();

									for(int j=0; j<itemCount; j++)
									{
										popupMenu.add(clonedMenu.getItem(0));
									}
								}
							}
						}

						popupMenu.show(me.getComponent(), x_co, y_co);
					}

				}
			}
		}

	};






	public void fromTextKeyPressedEvent(KeyEvent ke)
	{

		if(ke.getKeyCode() == KeyEvent.VK_ENTER)
		{
			String from = fromText.getText().trim();
			if(from.equals("0"))
				from = "1";
			String to = toText.getText();
			if(to.equals("0"))
				to = "1";
			int fromNumber = 0;
			int toNumber = 0;
			int difference = 0;
			if(! from.equals("") && ! to.equals(""))
			{
				try
				{
					fromNumber = Integer.parseInt(from);
					toNumber = Integer.parseInt(to);
					difference = toNumber - fromNumber;
				}	
				catch (NumberFormatException ne)
				{
					Toolkit.getDefaultToolkit().beep();
					return;
				}

				fromKeys = true;
				if(difference >= 0)
				{
					reformCombo(++difference);
					if(toNumber < mainVectorForAllTasks.size())
					{
						nextButton.setEnabled(true);
						endButton.setEnabled(true);
					}
					else
					{
						nextButton.setEnabled(false);
						endButton.setEnabled(false);
					}

					if(toNumber > selectedPageLength)
					{
						homeButton.setEnabled(true);
						previousButton.setEnabled(true);
					}
					else
					{
						homeButton.setEnabled(false);
						previousButton.setEnabled(false);						
					}
				}	
				else
					Toolkit.getDefaultToolkit().beep();
			}	
		}
	}

	public void reformCombo(int number)
	{
		boolean flag = false;
		int index1 = 0;
		for(int i=0;i<pageLengthCombo.getItemCount();i++)
		{
			String str = pageLengthCombo.getItemAt(i).toString();
			int temp = Integer.parseInt(str);
			if(temp == number)
			{
				index1 = i;
				flag = true;
				break;
			}
		}

		if(! flag)
		{
			pageLengthCombo.addItem(""+number);
			boolean bool = false;
			int index = 0;
			for(int i=0;i<pageLengthCombo.getItemCount();i++)
			{
				String str = pageLengthCombo.getItemAt(i).toString();
				int temp = Integer.parseInt(str);
				if(temp > number)
				{
					pageLengthCombo.insertItemAt(""+number,i);
					index = i;
					pageLengthCombo.insertItemAt(""+temp,++i);
					pageLengthCombo.removeItemAt(++i);
					bool = true;
					break;
				}
			}
			if(bool)
			{
				pageLengthCombo.removeItemAt(pageLengthCombo.getItemCount()-1);
				pageLengthCombo.setSelectedIndex(index);
			}	

			else
			{
				pageLengthCombo.setSelectedIndex(pageLengthCombo.getItemCount()-1);
			}
			pageLengthCombo.repaint();
		}

		else
		{
			pageLengthCombo.setSelectedIndex(index1);
			//changeTableView();
		}
	}

	public void toTextKeyPressedEvent(KeyEvent ke)
	{
		if(ke.getKeyCode() == KeyEvent.VK_ENTER)
		{
			String from = fromText.getText().trim();
			if(from.equals("0"))
				from = "1";
			String to = toText.getText();
			if(to.equals("0"))
				to = "1";
			fromKeys = true;
			int fromNumber = 0;
			int toNumber = 0;
			int difference = 0;
			if( ! from.equals("") && ! to.equals(""))
			{
				try
				{
					fromNumber = Integer.parseInt(from);
					toNumber = Integer.parseInt(to);
					difference = toNumber - fromNumber;
				}	
				catch (NumberFormatException ne)
				{
					Toolkit.getDefaultToolkit().beep();
					return;
				}

				if(difference >= 0)
				{
					reformCombo(++difference);
					if(toNumber < mainVectorForAllTasks.size())
					{
						nextButton.setEnabled(true);
						endButton.setEnabled(true);
					}
					else
					{
						nextButton.setEnabled(false);
						endButton.setEnabled(false);
					}
				}	
				else
					Toolkit.getDefaultToolkit().beep();		
			}	
		}
	}


	public void pageLengthComboActionPerformed()
	{
		String str = pageLengthCombo.getSelectedItem().toString();
		selectedPageLength = Integer.parseInt(str);
		try
		{
 	                         tableModel.setFetchSize(selectedPageLength);
 	           }
 	           catch(Exception ex)
 	           {
 	           	System.err.println(ex.getMessage());
		}
		if(! fromText.getText().equals("") && ! toText.getText().equals(""))
		{
			changeTableView();
		}	
	}

	boolean fromKeys = false;
	public void changeTableView()
	{
		//if(selectedPageLength <= mainVectorForAllTasks.size())
		//{
		String from = fromText.getText();
		String to = toText.getText();
		if(! from.equals("") && ! to.equals(""))
		{
			if(from.equals("0"))
				from = "1";
			if(to.equals("0"))
				to ="1";
			int fromInt = Integer.parseInt(from);
			int toInt = Integer.parseInt(to);

			int start = fromInt;
			int destin = 0;
			if(fromKeys)
			{
				if(toInt > mainVectorForAllTasks.size())
					destin = mainVectorForAllTasks.size();
				else
					destin = toInt;
				if(destin < mainVectorForAllTasks.size())
				{
					nextButton.setEnabled(true);
					endButton.setEnabled(true);
				}
				else
				{
					nextButton.setEnabled(false);
					endButton.setEnabled(false);
				}
			}
			else
			{
				destin = selectedPageLength;
				if (destin > mainVectorForAllTasks.size())
				{
					destin = mainVectorForAllTasks.size();
					nextButton.setEnabled(false);
					endButton.setEnabled(false);
				}

				else
				{
					//destin = selected;
					nextButton.setEnabled(true);
					endButton.setEnabled(true);
				}
				if(destin - start < selectedPageLength)
				{
					start = 1;
					previousButton.setEnabled(false);
					homeButton.setEnabled(false);
				}
				else
				{
					previousButton.setEnabled(true);
					homeButton.setEnabled(true);
				}
			}

			if(destin <= mainVectorForAllTasks.size())
			{
				try
				{
				}
				catch(Exception ex)
				{
					System.err.println(ex.getMessage());
				}

				int m = destin;

				fromText.setText(""+start);
				toText.setText(""+m);
			}	
		}	
		//}
		fromKeys = false;
	}


	class CustomTextDocument extends PlainDocument
	{
		String validValues = "0123456789";

		public CustomTextDocument()
		{
			super();
		}

		public CustomTextDocument(String str)
		{
			validValues = str;
		}

		public void insertString(int offset,String str,AttributeSet attrib)
			throws BadLocationException
			{
				if(str == null)
					return;

				if(getLength() == 0)
				{
					if(str.equals("0"))
					{
                                            //Beep sound produced while connecting the client to the server is fixed by checking the row count
                                                int rowCount = tableModel.getRowCount();
                                                if(rowCount > 0)
                                                {
                                                    Toolkit.getDefaultToolkit().beep();
                                                    return;
                                                }
					}
				}
				for(int i=0;i < str.length();i++)
				{
					if(validValues.indexOf(str.valueOf(str.charAt(i))) == -1)
					{
						Toolkit.getDefaultToolkit().beep();
						return;
					}
				}

				if(! fromText.getText().equals("") && !toText.getText().equals(""))
				{
					if(fromText.getDocument() == this)
					{
						String from = fromText.getText()+str;
						String to = toText.getText();
						if(Integer.parseInt(from) > Integer.parseInt(to))
						{
							Toolkit.getDefaultToolkit().beep();
							return;
						}
					}
				}					

				super.insertString(offset,str,attrib);
			}
	}	

	public String getRollbackDocument(String taskName)	
	{
		String rollbackDocument = null;

		Vector taskDetails = getTaskDetails(taskName);

		if(taskDetails != null)
		{
			rollbackDocument = (String)taskDetails.elementAt(4);
		}

		return rollbackDocument;
	}

	public String getLastExecutedTime(String taskName)	
	{
		String lastExecutedTime = null;

		Vector taskDetails = getTaskDetails(taskName);

		if(taskDetails != null)
		{
			lastExecutedTime = (String)taskDetails.elementAt(5);
		}

		return lastExecutedTime;
	}

	public Vector getDevicesWithDefaultValues(Vector devices, String protocol)
	{
		return getDevices(devices, getDeviceAttributes(protocol));
	}

	private Vector getDevices(Vector devices, Object[][] attributes)
	{
		Vector deviceAttributes = null;

		if(devices != null && attributes != null)
		{
			deviceAttributes = new Vector();

			for(int i=0; i<devices.size(); i++)
			{
				Vector device = new Vector();

				device.addElement(devices.elementAt(i));

				for(int j=1; j<attributes.length; j++)
				{
					device.addElement(attributes[j][1]);
				}

				deviceAttributes.addElement(device);
			}
		}

		return deviceAttributes;
	}

	public Vector getDeviceAttributeNames(String protocol)
	{
		return getDeviceAttributeNames(getDeviceAttributes(protocol));
	}

	public Vector getDeviceAttributeNames(Object[][] deviceAttributes)
	{
		return getAttributeNames(deviceAttributes);
	}

	public Object[][] getDeviceAttributes(String protocol)
	{
		Object[][] deviceAttributes = null;

		if(protocol.equalsIgnoreCase("SNMP"))
		{
			deviceAttributes = snmpDeviceAttributes;
		}
		else if(protocol.equalsIgnoreCase("Telnet"))
		{
			deviceAttributes = telnetDeviceAttributes;
		}
		else if(protocol.equalsIgnoreCase("TFTP"))
		{
			deviceAttributes = tftpDeviceAttributes;
		}
		else if(protocol.equalsIgnoreCase("FTP"))
		{
			deviceAttributes = ftpDeviceAttributes;
		}
		else if(protocol.equalsIgnoreCase("TL1"))
		{
			deviceAttributes = tl1DeviceAttributes;
		}
		else if(protocol.equalsIgnoreCase("Netconf"))//No I18N
		{
			deviceAttributes = netconfDeviceAttributes;
		}

		return deviceAttributes;
	}

	private Vector getAttributeNames(Object[][] attributes)
	{
		Vector attributeNames = null;

		if(attributes != null)
		{
			attributeNames = new Vector();

			for(int i=0; i<attributes.length; i++)
			{
				attributeNames.addElement(attributes[i][0]);
			}
		}

		return attributeNames;
	}

/*	private void removeAllRows()
	{
		int rowCount = tableModel.getRowCount();

		if(rowCount > 0)
		{
			for(int i=0; i<rowCount; i++)
			{
				tableModel.removeRow(0);
			}
		}
	}

*/
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
JPanel1.setLayout(new GridBagLayout());
inset = new Insets(5,0,5,10);
setConstraints(0,0,1,1,0.1,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel1.add(JLabel5,cons);
inset = new Insets(5,0,5,5);
setConstraints(1,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel1.add(JLabel1,cons);
inset = new Insets(5,0,5,10);
setConstraints(2,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel1.add(taskCountText,cons);
inset = new Insets(5,0,5,5);
setConstraints(3,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel1.add(JLabel2,cons);
inset = new Insets(5,0,5,0);
setConstraints(4,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel1.add(fromText,cons);
inset = new Insets(5,0,5,0);
setConstraints(5,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel1.add(JLabel3,cons);
inset = new Insets(5,0,5,5);
setConstraints(6,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel1.add(toText,cons);
inset = new Insets(5,0,5,5);
setConstraints(7,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel1.add(JLabel4,cons);
inset = new Insets(5,0,5,10);
setConstraints(8,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel1.add(pageLengthCombo,cons);
inset = new Insets(5,0,5,5);
setConstraints(9,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel1.add(homeButton,cons);
inset = new Insets(5,0,5,5);
setConstraints(10,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel1.add(previousButton,cons);
inset = new Insets(5,0,5,5);
setConstraints(11,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel1.add(nextButton,cons);
inset = new Insets(5,0,5,10);
setConstraints(12,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel1.add(endButton,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(JPanel2,cons);
JPanel2.setLayout(new BorderLayout(5,5));
JPanel2.add(JScrollPane1,BorderLayout.CENTER);
JScrollPane1.getViewport().add(table);

  
//<End_setUpGUI_Container>
	} 
	public void setUpConnections()
  {
		//<Begin_setUpConnections> 

      home_home_conn1 home_home_conn11 =  new home_home_conn1();
      homeButton.addActionListener(home_home_conn11);
      previous_previous_conn1 previous_previous_conn11 =  new previous_previous_conn1();
      previousButton.addActionListener(previous_previous_conn11);
      next_next_conn1 next_next_conn11 =  new next_next_conn1();
      nextButton.addActionListener(next_next_conn11);
      end_end_conn1 end_end_conn11 =  new end_end_conn1();
      endButton.addActionListener(end_end_conn11);
      toText_toText_conn1 toText_toText_conn11 =  new toText_toText_conn1();
      toText.addKeyListener(toText_toText_conn11);
      JComboBox1_JComboBox1_conn1 JComboBox1_JComboBox1_conn11 =  new JComboBox1_JComboBox1_conn1();
      pageLengthCombo.addActionListener(JComboBox1_JComboBox1_conn11);
      fromText_fromText_conn1 fromText_fromText_conn11 =  new fromText_fromText_conn1();
      fromText.addKeyListener(fromText_fromText_conn11);
  
      //<End_setUpConnections>
	} 


	//<Begin__class_home_home_conn1>

 class home_home_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_home_home_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  homeButtonActionPerformed();
     }
//<UserCode_End_Connection_home_home_conn1>
 }//<End__class_home_home_conn1>
	//<Begin__class_previous_previous_conn1>

 class previous_previous_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_previous_previous_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  previousButtonActionPerformed();
     }
//<UserCode_End_Connection_previous_previous_conn1>
 }//<End__class_previous_previous_conn1>
	//<Begin__class_next_next_conn1>

 class next_next_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_next_next_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  nextButtonActionPerformed();
     }
//<UserCode_End_Connection_next_next_conn1>
 }//<End__class_next_next_conn1>
	//<Begin__class_end_end_conn1>

 class end_end_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_end_end_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  endButtonActionPerformed();
     }
//<UserCode_End_Connection_end_end_conn1>
 }//<End__class_end_end_conn1>
	//<Begin__class_toText_toText_conn1>

 class toText_toText_conn1 extends java.awt.event.KeyAdapter implements java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_toText_toText_conn1>



     //Listener Interface Methods Are Added Below 


     public void keyReleased( java.awt.event.KeyEvent arg0)
     {
  toTextKeyPressedEvent(arg0);
     }
//<UserCode_End_Connection_toText_toText_conn1>
 }//<End__class_toText_toText_conn1>
	//<Begin__class_JComboBox1_JComboBox1_conn1>

 class JComboBox1_JComboBox1_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JComboBox1_JComboBox1_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  pageLengthComboActionPerformed();
     }
//<UserCode_End_Connection_JComboBox1_JComboBox1_conn1>
 }//<End__class_JComboBox1_JComboBox1_conn1>
	//<Begin__class_fromText_fromText_conn1>

 class fromText_fromText_conn1 extends java.awt.event.KeyAdapter implements java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_fromText_fromText_conn1>



     //Listener Interface Methods Are Added Below 


     public void keyReleased( java.awt.event.KeyEvent arg0)
     {
  fromTextKeyPressedEvent(arg0);  
     }
//<UserCode_End_Connection_fromText_fromText_conn1>
 }//<End__class_fromText_fromText_conn1>

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}











