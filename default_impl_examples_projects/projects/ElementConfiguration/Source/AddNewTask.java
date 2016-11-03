
//$Id: AddNewTask.java,v 1.2.6.1 2012/05/29 06:26:27 karen.r Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.config;



import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.applet.*;
import java.net.URL;

import com.adventnet.management.config.*;

public class AddNewTask extends JDialog implements ConfigResponseListener,HelpInterface
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
	javax.swing.JPanel JPanel31 = null;
	javax.swing.JPanel cardPanel = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JPanel JPanel41 = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JButton backButton = null;
	javax.swing.JButton nextButton = null;
	javax.swing.JButton cancelButton = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>


	public void init()
	{
		//<Begin_init> 
		if(getParameter("RESOURCE_PROPERTIES" ) != null)
		{
			localePropertiesFileName = getParameter("RESOURCE_PROPERTIES");
		}
		resourceBundle = com.adventnet.apiutils.Utility.getBundle(localePropertiesFileName,getParameter("RESOURCE_LOCALE"),applet);
		if (initialized) return; 
		this.setSize(getPreferredSize().width+812,getPreferredSize().height+575); 
		setTitle(resourceBundle.getString("AddNewTask"));
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
		this.setSize(getPreferredSize().width+700,getPreferredSize().height+450);
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
			JLabel11.setText(resourceBundle.getString("Task Configuration"));
		}
		catch(Exception ex)
		{
			showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel11,ex); 
		}

		//<UserCode_Begin_Bean_JLabel11>

		//<UserCode_End_Bean_JLabel11>

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
		JPanel31= new javax.swing.JPanel();
		cardPanel= new javax.swing.JPanel();
		JLabel1= new javax.swing.JLabel();
		JPanel41= new javax.swing.JPanel();
		JPanel1= new javax.swing.JPanel();
		backButton= new javax.swing.JButton();
		nextButton= new javax.swing.JButton();
		cancelButton= new javax.swing.JButton();


		//<End_initVariables>
	} 
	public void setUpGUI(Container container)
	{
		//<Begin_setUpGUI_Container> 
		container.add(Top,BorderLayout.CENTER);
		Top.setLayout(new GridBagLayout());
		inset = new Insets(5,5,5,5);
		setConstraints(0,1,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
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
		setConstraints(0,2,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
		Top.add(JPanel31,cons);
		JPanel31.setLayout(new GridBagLayout());
		inset = new Insets(5,5,5,5);
		setConstraints(1,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
		JPanel31.add(cardPanel,cons);
		cardPanel.setLayout(new CardLayout(5,5));
		inset = new Insets(5,5,5,5);
		setConstraints(0,0,1,1,0.0,0.1,cons.CENTER,cons.VERTICAL,inset,0,0);
		JPanel31.add(JLabel1,cons);
		inset = new Insets(5,5,5,5);
		setConstraints(0,3,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
		Top.add(JPanel41,cons);
		JPanel41.setLayout(new BorderLayout(5,5));
		JPanel41.add(JPanel1,BorderLayout.EAST);
		JPanel1.setLayout(new FlowLayout(1,5,5));
		JPanel1.add(backButton);
		JPanel1.add(nextButton);
		JPanel1.add(cancelButton);


		//<End_setUpGUI_Container>
	} 
	public void setUpConnections()
	{
		//<Begin_setUpConnections> 

		JButton31_JButton31_conn1 JButton31_JButton31_conn11 =  new JButton31_JButton31_conn1();
		cancelButton.addActionListener(JButton31_JButton31_conn11);
		backButton1_backButton1_conn1 backButton1_backButton1_conn11 =  new backButton1_backButton1_conn1();
		backButton.addActionListener(backButton1_backButton1_conn11);
		nextButton1_nextButton1_conn1 nextButton1_nextButton1_conn11 =  new nextButton1_nextButton1_conn1();
		nextButton.addActionListener(nextButton1_nextButton1_conn11);
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






	//<Begin__class_JButton31_JButton31_conn1>

	class JButton31_JButton31_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
	{

		//<TOP_PART>
		//<UserCode_Begin_Connection_JButton31_JButton31_conn1>



		//Listener Interface Methods Are Added Below 


		public void actionPerformed( java.awt.event.ActionEvent arg0)
		{
			cancelButtonActionPerformed();
		}
		//<UserCode_End_Connection_JButton31_JButton31_conn1>
	}//<End__class_JButton31_JButton31_conn1>
	//<Begin__class_backButton1_backButton1_conn1>

	class backButton1_backButton1_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
	{

		//<TOP_PART>
		//<UserCode_Begin_Connection_backButton1_backButton1_conn1>



		//Listener Interface Methods Are Added Below 


		public void actionPerformed( java.awt.event.ActionEvent arg0)
		{
			backButtonActionPerformed();
		}
		//<UserCode_End_Connection_backButton1_backButton1_conn1>
	}//<End__class_backButton1_backButton1_conn1>
	//<Begin__class_nextButton1_nextButton1_conn1>

	class nextButton1_nextButton1_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
	{

		//<TOP_PART>
		//<UserCode_Begin_Connection_nextButton1_nextButton1_conn1>



		//Listener Interface Methods Are Added Below 


		public void actionPerformed( java.awt.event.ActionEvent arg0)
		{
			nextButtonActionPerformed();
		}
		//<UserCode_End_Connection_nextButton1_nextButton1_conn1>
	}//<End__class_nextButton1_nextButton1_conn1>


	public AddNewTask()
	{
		//<Begin_AddNewTask>
		pack();

		//<End_AddNewTask>
	}

	public AddNewTask(java.applet.Applet applet)
	{
		//<Begin_AddNewTask_java.applet.Applet>
		this.applet = applet;
		pack();
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		//<End_AddNewTask_java.applet.Applet>
	}





	//<----------------------------------------------------------------------------------------------------------------->

	private ConfigPanel configPanel = null;

	private TaskNamePanel taskNamePanel = null;

	private SnmpScalarPanel snmpScalarPanel = null;
	private SnmpTablePanel snmpTablePanel = null;

	private TelnetPanel telnetPanel = null;

	private TftpPanel tftpPanel = null;

	private TftpPanel ftpPanel = null;

	private TL1Panel tl1Panel = null;

	private NetconfPanel netconfPanel = null;

	private CombinedTaskPanel combinedTaskPanel = null;

	private RollbackPanel rollbackPanel = null;

	private Hashtable taskDetails = new Hashtable();
	private Vector cardNames = new Vector();

	private int cardIndex = 0;
	private int totalCards = 0;

	private boolean isModify = false;

	private HelpDialog helpDialog = null;

	public AddNewTask(ConfigPanel configPanel)
	{
		super(configPanel.configClientUtils.getParentFrame(configPanel));

		this.configPanel = configPanel;

		applet = configPanel.applet;

		init();
		configInit(null);
	}

	public AddNewTask(ConfigPanel configPanel, Vector rowData)
	{
		super(configPanel.configClientUtils.getParentFrame(configPanel));

		this.configPanel = configPanel;
		isModify = true;

		applet = configPanel.applet;

		init();
		configInit(rowData);
	}

	private void configInit(Vector rowData)
	{
		ImageIcon icon = configPanel.configClientUtils.getImage(configPanel.applet.getDocumentBase()+"../images/addtask.png");

		JLabel1.setIcon(icon);
		ImageIcon icon1 = configPanel.configClientUtils.getImage(configPanel.applet.getDocumentBase()+"../images/contextualhelp.png");

		JButton1.setIcon(icon1);
		cardPanel.setLayout(new CardLayout());

		if(isModify)
		{
			Object[] params = {rowData.elementAt(0)};

			configPanel.configResponseHandler.sendRequest(NmsConfigConstants.GET_TASK, params, this);

			taskDetails.put("taskname", rowData.elementAt(0));
			if(rowData.elementAt(1) != null)
				taskDetails.put("description", rowData.elementAt(1));
			taskDetails.put("protocol", rowData.elementAt(2));
			taskDetails.put("istemplate", rowData.elementAt(3));
			if(rowData.elementAt(4) != null)
				taskDetails.put("rollback", rowData.elementAt(4));

			taskDetails.put("lastexecutedtime", rowData.elementAt(5));

			taskNamePanel = new TaskNamePanel(configPanel, taskDetails);
			//dummy check
			if(!configPanel.isStandAlone)
				nextButton.setEnabled(false);
		}
		else
		{
			taskNamePanel = new TaskNamePanel(configPanel);
		}

		taskDetails.put("protocol", "");	
		taskDetails.put("istemplate", "");

		cardPanel.add("maincard",taskNamePanel);

		totalCards = cardIndex++;
		cardNames.setSize(5);
		cardNames.setElementAt("maincard", cardIndex-1);

		backButton.setEnabled(false);
	}

	public void cancelButtonActionPerformed()
	{
		this.setVisible(false);
		this.dispose();
	}


	private void nextButtonActionPerformed()
	{

		CardLayout card = (CardLayout)cardPanel.getLayout();

		if(cardIndex == 1)
		{
			String taskName = taskNamePanel.getTaskName();

			if(taskName.equals(""))
			{
				configPanel.configClientUtils.showErrorDialog(this, resourceBundle.getString("Please specify the task name"), resourceBundle.getString("Info"), "info");
				return;
			}
			else if(configPanel.configMainUI.isTaskAlreadyPresent(taskName) && !isModify)
			{
				configPanel.configClientUtils.showErrorDialog(this, resourceBundle.getString("The given task name is already present"), resourceBundle.getString("Info"), "info");
				return;
			}

			String protocol = taskNamePanel.getSelectedProtocol();
			boolean isTemplate = taskNamePanel.isTemplate();

			if(!((String)taskDetails.get("protocol")).equalsIgnoreCase(protocol) || !((String)taskDetails.get("istemplate")).equals(String.valueOf(isTemplate)))
			{
				if(protocol.equalsIgnoreCase("snmp"))
				{
					snmpScalarPanel = new SnmpScalarPanel(configPanel, isTemplate);
					cardPanel.add("scalar", snmpScalarPanel);

					cardIndex++;
					cardNames.setElementAt("scalar", cardIndex-1);

					snmpTablePanel = new SnmpTablePanel(configPanel, isTemplate);
					cardPanel.add("table", snmpTablePanel);

					cardIndex++;
					cardNames.setElementAt("table", cardIndex-1);

					if(isModify)
					{
						Vector taskAttributes = (Vector)taskDetails.get("attributes");

						snmpScalarPanel.setValues((Vector)taskAttributes.elementAt(0));
						snmpTablePanel.setValues((Hashtable)taskAttributes.elementAt(1));
					}

				}
				else if(protocol.equalsIgnoreCase("telnet"))
				{
					telnetPanel = new TelnetPanel(configPanel, isTemplate);	
					cardPanel.add("telnet", telnetPanel);

					if(isModify)
					{
						telnetPanel.setValues((Vector)taskDetails.get("attributes"));
					}

					cardIndex++;
					cardNames.setElementAt("telnet", cardIndex-1);

				}
				else if(protocol.equalsIgnoreCase("tftp"))
				{
					tftpPanel = new TftpPanel(configPanel, isTemplate);
					cardPanel.add("tftp", tftpPanel);

					if(isModify)
					{
						tftpPanel.setValues((Vector)taskDetails.get("attributes"));
					}

					cardIndex++;
					cardNames.setElementAt("tftp", cardIndex-1);
				}
				else if(protocol.equalsIgnoreCase("ftp"))
				{
					ftpPanel = new TftpPanel(configPanel, isTemplate);
					ftpPanel.setScreenForFTP(true);

					cardPanel.add("ftp", ftpPanel);

					if(isModify)
					{
						ftpPanel.setValues((Vector)taskDetails.get("attributes"));
					}

					cardIndex++;
					cardNames.setElementAt("ftp", cardIndex-1);
				}
				else if(protocol.equalsIgnoreCase("tl1"))
				{
					tl1Panel = new TL1Panel(configPanel, isTemplate);
					cardPanel.add("tl1", tl1Panel);

					if(isModify)	
					{
						tl1Panel.setValues((Vector)taskDetails.get("attributes"));
					}

					cardIndex++;
					cardNames.setElementAt("tl1", cardIndex-1);
				}
				else if(protocol.equalsIgnoreCase("netconf"))//No I18N
				{
					netconfPanel = new NetconfPanel(configPanel, isTemplate);
					cardPanel.add("netconf", netconfPanel);//No I18N

					if(isModify)
					{
						netconfPanel.setValues((Vector)taskDetails.get("attributes"));//No I18N
					}

					cardIndex++;
					cardNames.setElementAt("netconf", cardIndex-1);//No I18N
				}
				else if(protocol.equalsIgnoreCase("combined"))
				{
					combinedTaskPanel = new CombinedTaskPanel(configPanel);	
					cardPanel.add("combined", combinedTaskPanel);
					if(isModify)
					{
						combinedTaskPanel.setValues((Vector)taskDetails.get("attributes"));
					}

					cardIndex++;
					cardNames.setElementAt("combined", cardIndex-1);
				}

				if(!protocol.equalsIgnoreCase("combined"))
				{
					if(rollbackPanel == null)
					{
						if(isModify)
						{
							rollbackPanel = new RollbackPanel(configPanel, protocol, (String)taskDetails.get("taskname"));
							rollbackPanel.setRollbackDocument((String)taskDetails.get("rollback"));
						}
						else	
						{
							rollbackPanel = new RollbackPanel(configPanel, protocol);
						}
						cardPanel.add("rollback", rollbackPanel);
					}
					else
					{
						if(!((String)taskDetails.get("protocol")).equalsIgnoreCase(protocol))	
						{
							rollbackPanel.setProtocol(protocol);
							rollbackPanel.refreshList();
						}
					}

					cardIndex++;
					cardNames.setElementAt("rollback", cardIndex-1);

					/*if(isTemplate)
					  {
					  if(dataSourcePanel == null)
					  {
					  dataSourcePanel = new DataSourcePanel(configPanel);
					  cardPanel.add("datasource", dataSourcePanel);
					  }

					  cardIndex++;
					  cardNames.setElementAt("datasource", cardIndex-1);
					  }*/
				}
				totalCards = cardIndex;

				taskDetails.put("protocol", protocol);	
				taskDetails.put("istemplate", String.valueOf(isTemplate));
			}

			cardIndex = 2;

			card.show(cardPanel, (String)cardNames.elementAt(cardIndex-1));

			backButton.setEnabled(true);

			if(cardIndex == totalCards)
			{
				nextButton.setText(resourceBundle.getString("Finish"));			
			}
		}
		else 
		{
			if(cardIndex == 2 || cardIndex == 3)
			{
				String protocol = (String)taskDetails.get("protocol");

				Vector taskAttributes = null;

				if(cardIndex == 2 && !protocol.equalsIgnoreCase("SNMP"))
				{
					if(protocol.equalsIgnoreCase("Telnet"))
					{
						taskAttributes = telnetPanel.getAllValues();
					}
					else if(protocol.equalsIgnoreCase("TFTP"))
					{
						taskAttributes = tftpPanel.getAllValues();
					}
					else if(protocol.equalsIgnoreCase("FTP"))
					{
						taskAttributes = ftpPanel.getAllValues();
					}
					else if(protocol.equalsIgnoreCase("TL1"))
					{	
						taskAttributes = tl1Panel.getAllValues();
					}
					else if(protocol.equalsIgnoreCase("Netconf"))//No I18N
					{
						taskAttributes = netconfPanel.getAllValues();
					}
					else if(protocol.equalsIgnoreCase("Combined"))
					{
						taskAttributes = combinedTaskPanel.getSelectedTasks();
					}

					if(taskAttributes == null || taskAttributes.size() == 0)
					{
						configPanel.configClientUtils.showErrorDialog(this, resourceBundle.getString("No attributes defined for the task"), resourceBundle.getString("Info"), "info");
						return;
					}
					else
					{
						taskDetails.put("attributes", taskAttributes);
					}
				}
				else if(cardIndex == 3 && protocol.equalsIgnoreCase("SNMP"))
				{
					Vector scalarAttributes = snmpScalarPanel.getAllValues();
					Hashtable tableAttributes = snmpTablePanel.getAllValues();

					if(tableAttributes != null && tableAttributes.size() > 0)
					{
						for(Enumeration enumerate = tableAttributes.keys(); enumerate.hasMoreElements();)
						{
							Vector vec1 = (Vector)tableAttributes.get(enumerate.nextElement());	

							Vector vec2 = (Vector)vec1.elementAt(1);

							if(vec2 == null || vec2.size() == 0)
							{
								configPanel.configClientUtils.showErrorDialog(this, resourceBundle.getString("Please add atleast one row for the selected tables"), resourceBundle.getString("Info"), "info");
								return;
							}
						}
					}

					if((scalarAttributes == null || scalarAttributes.size() == 0) && (tableAttributes == null || tableAttributes.size() == 0))
					{
						configPanel.configClientUtils.showErrorDialog(this, resourceBundle.getString("No attributes defined for the task"), resourceBundle.getString("Info"), "info");
						return;
					}
					else
					{
						taskAttributes = new Vector();

						taskAttributes.addElement(scalarAttributes);
						taskAttributes.addElement(tableAttributes);

						taskAttributes = configPanel.mibHandler.formatSNMPTaskAttributesForSave(taskAttributes);

						taskDetails.put("attributes", taskAttributes);
					}
				}
			}

			if(cardIndex == totalCards)
			{
				finishActionPerformed();
				return;
			}

			cardIndex++;

			card.show(cardPanel, (String)cardNames.elementAt(cardIndex-1));

			if(cardIndex == totalCards)
			{
				nextButton.setText(resourceBundle.getString("Finish"));
			}
		}
	}

	private void backButtonActionPerformed()
	{
		CardLayout card = (CardLayout)cardPanel.getLayout();

		cardIndex--;

		card.show(cardPanel, (String)cardNames.elementAt(cardIndex-1));

		if(cardIndex == totalCards-1)
		{
			nextButton.setText(resourceBundle.getString("Next"));
		}

		if(cardIndex == 1)
		{
			backButton.setEnabled(false);
		}
	}

	private void finishActionPerformed()
	{
		saveTask();
		setVisible(false);
	}

	private void saveTask()
	{
		taskDetails.put("taskname", taskNamePanel.getTaskName());

		taskDetails.put("description", taskNamePanel.getTaskDescription());

		if(rollbackPanel != null)
		{
			taskDetails.put("rollback", rollbackPanel.getRollbackDocument());
		}

		ConfigTaskGenerator taskGenerator = new ConfigTaskGenerator(configPanel);

		if(isModify)
		{
			taskGenerator.setOverwrite(true);
		}

		Object[] params = {taskGenerator.generateTask(taskDetails)};

		configPanel.configResponseHandler.sendRequest(NmsConfigConstants.SAVE_TASK, params, this);
	}

	private void extractTaskDetails(String task)
	{
		ConfigTaskReader reader = new ConfigTaskReader(configPanel, task);

		Vector taskAttributes = reader.getTaskAttributes();

		if(! reader.isCombinedTask())
		{
			if((reader.getProtocol()).equalsIgnoreCase("snmp"))
			{
				taskAttributes = configPanel.mibHandler.formatSNMPTaskAttributesForDisplay(taskAttributes);
			}
		}

		taskDetails.put("attributes", taskAttributes);
	}

	public void response(ConfigResultEvent configResultEvent)
	{
		int workID = configResultEvent.getWorkID();
		int errorCode = configResultEvent.getErrorCode();

		String uniqueID = configResultEvent.getRequestID();

		if(workID == NmsConfigConstants.GET_TASK)
		{
			if(errorCode == NmsConfigConstants.NO_ERROR)
			{
				extractTaskDetails(configResultEvent.getTask());		
				nextButton.setEnabled(true);
			}
		}
		else if(workID == NmsConfigConstants.SAVE_TASK)
		{
			if(errorCode == NmsConfigConstants.NO_ERROR)
			{
				updateTaskTable();
			}
			else 
			{
				configPanel.configClientUtils.showErrorDialog(this, resourceBundle.getString(configResultEvent.getErrorString()), resourceBundle.getString("Error"), "error");
			}

			dispose();
		}
	}

	private void updateTaskTable()
	{
		Vector taskData = new Vector();

		taskData.addElement(taskDetails.get("taskname"));
		taskData.addElement(taskDetails.get("description"));

		String protocol = (String)taskDetails.get("protocol");

		if(protocol.equalsIgnoreCase("Combined"))
		{
			Vector taskAttributes = (Vector)taskDetails.get("attributes");

			String combined = "COMBINED(";

			if(taskAttributes != null)
			{
				for(int i=0; i<taskAttributes.size(); i++)	
				{
					if(i < (taskAttributes.size()-1))
					{
						combined = combined + (String)taskAttributes.elementAt(i) + ",";
					}
					else
					{
						combined = combined + (String)taskAttributes.elementAt(i);
					}
				}
			}

			combined = combined + ")";

			taskData.addElement(combined);
		}
		else
		{
			taskData.addElement(protocol.toUpperCase());
		}

		taskData.addElement((String)taskDetails.get("istemplate"));

		if(taskDetails.get("rollback") != null)
		{
			taskData.addElement(taskDetails.get("rollback"));
		}
		else
		{
			taskData.addElement("");
		}

		if(isModify)
		{
			taskData.addElement(taskDetails.get("lastexecutedtime"));

			configPanel.configMainUI.updateTaskView((String)taskData.elementAt(0), taskData);
		}
		else
		{
			taskData.addElement("Not Executed");

			configPanel.configMainUI.addTaskToView(taskData);
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
			helpButtonActionPerformed();
		}
		//<UserCode_End_Connection_JButton1_JButton1_conn1>
	}//<End__class_JButton1_JButton1_conn1>

	public void helpButtonActionPerformed()
	{
		if(helpDialog == null)
		{
			helpDialog = new HelpDialog(configPanel, this);
			helpDialog.setRelatedTopics(new String[] {resourceBundle.getString("Task Vs Template"), resourceBundle.getString("Attributes For Task"), resourceBundle.getString("Rollback Configuration"), resourceBundle.getString("Save Task")});
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

	public String getHelpFor(String key)
	{
		String helpContent = "";

		if(key.equals(resourceBundle.getString("Task Configuration")))
		{
			helpContent = "\t" + resourceBundle.getString("A Task is an entity which is defined based on a definite protocol and which contains information related to the configuration that is to be done. We basically define the parameters that are to be configured as 'Attributes', and assign values to them in the process of configuration. Please type in a Task Name and select a protocol to proceed with the Task Configuration process."); 
		}
		else if(key.equals(resourceBundle.getString("Task Vs Template")))
		{
			helpContent = "\t" + resourceBundle.getString("Tasks can be defined as Templates in the sense that, the values for the task 'Attributes' need not be specified during the Task Configuration process. We define certain Place Holders (nothing but variables) for the Attributes which can be substituted with various values by defining different 'Data Sources'.");
		}
		else if(key.equals(resourceBundle.getString("Attributes For Task")))
		{
			helpContent = "\t" + resourceBundle.getString("Attributes are nothing but the parameters that are involved in the configuration process. Values are directly given for the Attributes if the Task is not defined as a Template. When the Task is defined as a Template, the Attributes are provided with Place Holders (Nothing but variables) whose values are defined in Data Sources.");
		}
		else if(key.equals(resourceBundle.getString("Rollback Configuration")))
		{
			helpContent = "\t" + resourceBundle.getString("When a task is being executed on a device, there are various possibilities due to which it may fail to succeed (For example, Device not reachable). Under these circumstances, user has the provision of specifying a Rollback Task which would be executed if the configuration fails. This rollback task can be any other task defined under the same protocol, or it could be set to the 'Current Configuration' (i.e. Configuration before the execution of this task).");
		}
		else if(key.equals(resourceBundle.getString("Save Task")))
		{
			helpContent = "\t" + resourceBundle.getString("Task configuration is said to be complete if a valid name is assigned to it and some Attributes defined. These two entries are mandatory for Task Configuration. It is upto the user's requirement to assign or skip Rollback Configuration. Once this is done we are set for saving the task. (NOTE: Saving a task does not update the clients connected to same Server about the new addition).");
		}

		return helpContent;
	}

	public String  getTitle()
	{
		return resourceBundle.getString("Task Configuration");
	}


	public void setProperties(java.util.Properties props)
	{
		//<Begin_setProperties_java.util.Properties> 


		//<End_setProperties_java.util.Properties>
	}
}
