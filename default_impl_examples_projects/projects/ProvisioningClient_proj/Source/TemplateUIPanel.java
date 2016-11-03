//$Id: TemplateUIPanel.java,v 1.4.6.1 2013/12/04 10:09:47 venkatramanan Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.provisioning.ui;    

import java.util.TimeZone;
import java.text.MessageFormat;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import javax.swing.tree.*;
import javax.swing.text.*;
import java.awt.*;
import java.lang.*;
import java.util.*;
import java.io.*;
import java.awt.event.*;
import java.applet.*;
import java.net.*;
import java.rmi.RemoteException;    

import com.adventnet.nms.provisioning.NoSuchOperationException;
import com.adventnet.nms.provisioning.ProvisioningConstants;
import com.adventnet.nms.provisioning.TemplateNotFoundException;
import com.adventnet.nms.provisioning.TemplateInitializationException;
import com.adventnet.nms.provisioning.OperationFailedException;
import com.adventnet.nms.util.DateTimeComponent;
import com.adventnet.nms.provisioning.xml.*;
import com.adventnet.nms.provisioning.ui.ProvClientUtils;
import com.adventnet.nms.failover.*;
import com.adventnet.nms.util.NmsClientUtil;
import com.adventnet.nms.util.CommonUtilCarrier;
import com.adventnet.nms.util.NmsCommonUtilClientImpl;
import com.adventnet.management.config.xml.*;
import com.adventnet.management.i18n.AdventNetResourceBundle;

import javax.xml.transform.*;
import java.io.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;

public class TemplateUIPanel extends JPanel implements   FocusListener, ChangeListener, FailOverListener, TreeSelectionListener
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "ProvisioningClientResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel CenterPanel = null;
	javax.swing.JPanel LoginParametersPanel = null;
	javax.swing.JPanel RadioButtonPanel = null;
	javax.swing.JPanel TemplateParamsPanel = null;
	javax.swing.JPanel FormsPanel = null;
	javax.swing.JPanel ResultPanel = null;
	javax.swing.JPanel SouthPanel = null;
	javax.swing.JScrollPane statusScrollPane = null;
	javax.swing.JTextArea statusTextArea = null;
	javax.swing.JPanel ButtonPanel = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JButton backButton = null;
	javax.swing.JButton nextButton = null;
	javax.swing.JButton descriptionButton = null;
	javax.swing.JButton fetchButton = null;
	javax.swing.JButton closeButton = null;
	javax.swing.JButton helpButton = null;
	javax.swing.JCheckBox debugCheckbox = null;
	javax.swing.JLabel mandatoryLabel = null;
    //<End_Variable_Declarations>

    

	JTextArea descriptionTextArea = new JTextArea();
	JScrollPane descriptionScrollPane = new JScrollPane(descriptionTextArea);
	JDialog dialog;
	boolean firstTime = true;

	UserInputUIHandler uiHandler = new UserInputUIHandler();
	ProvisioningApiHandler apiHandler ;//= new ProvisioningApiHandler();
	ProvisioningResult ProvisioningResult1;
	LoginPanel loginPanel;


	JTabbedPane jtp =  new JTabbedPane();

	JRadioButton []   radioButtons;
	ButtonGroup bg =new ButtonGroup();
	private boolean radioButtonPanelMadeVisible = false;
	private boolean templateParametersPanelMadeVisible = false;
	private boolean scheduled;
	private int numberOfRequiredFields = 0;

	// Based on the  value of this variable, events will be handled.
	private int whichPanelShowing = 0;
	private Dimension southPanelDimension;
	private final int LOGIN_PARAMETERS_PANEL_SHOWING = 0;    
	private final int RADIOBUTTON_PANEL_SHOWING = 1;
	private final int TEMPLATE_PARAMS_PANEL_SHOWING = 2;
	private final int FORMS_PANEL_SHOWING = 3;
	private final int RESULT_PANEL_SHOWING = 4;



	Template template = null;	

	String title;
	String result;
	String helpUrl;
	String closeButtonName="Close";
	String aplorfinish="Apply";
	String titleBorderString = null;
	String templateName;
	Thread workerThread;
	public static TimeZone perfTimeZone = null;


	//The following declarations are for Tree.
	// The boolean variable to decide whether forms should be displayed in Tree format.
	private boolean treeNeeded = false;
	private JPanel rightPanel; 


	// The hashtable that stores the tree nodes and their corresponding panels.
	private Hashtable nodesAndPanelsTable = new Hashtable();

	// The panel that contains the tree, that appears on the left side of split pane.
	private JPanel treePanel = new JPanel(new BorderLayout());

	// The split pane that contains the tree and the panels.
	private JSplitPane splitPane;

	// The root node, which will be displayed as "Forms" in the Tree.
	private DefaultMutableTreeNode formsNode; 

	// The Tree that appears on the left side of split pane.
	private JTree tree;
	private StyleSheetManager manager;
	private boolean useStyleSheet;

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

	public void init()
	{
		//<Begin_init> 
		if(getParameter("RESOURCE_PROPERTIES" ) != null)
		{
			localePropertiesFileName = getParameter("RESOURCE_PROPERTIES");
		}
		resourceBundle = com.adventnet.apiutils.Utility.getBundle(localePropertiesFileName,getParameter("RESOURCE_LOCALE"),applet);
		if (initialized == true) return; 
		setPreferredSize(new Dimension(getPreferredSize().width+668,getPreferredSize().height+538)); 
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
			showStatus(ProvClientUtils.getString("Error in init method"),ex); 
		} 
		// let us set the initialzed variable to true so  
		// we dont initialize again even if init is called 
		initialized = true; 


		//<End_init>
		createDescriptionDialog();
		descriptionTextArea.setEditable(false);
		descriptionTextArea.setLineWrap(true);
		descriptionTextArea.setWrapStyleWord(true);

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
			if (input.equals("RESOURCE_PROPERTIES")) value = "ProvisioningClientResources"; 
		}
		return value;


		//<End_getParameter_String>
	} 
	public void setUpProperties()
	{
		//<Begin_setUpProperties> 

		try
		{
			CenterPanel.setPreferredSize(new Dimension(630,420));
		}
		catch(Exception ex)
		{
			showStatus(ProvClientUtils.getString("Exception while setting properties for bean"),ex); 
		}

		try
		{
			RadioButtonPanel.setPreferredSize(new Dimension(630,350));
		}
		catch(Exception ex)
		{
			showStatus(ProvClientUtils.getString("Exception while setting properties for bean"),ex); 
		}

		try
		{
			TemplateParamsPanel.setPreferredSize(new Dimension(630,350));
		}
		catch(Exception ex)
		{
			showStatus(ProvClientUtils.getString("Exception while setting properties for bean"),ex); 
		}

		try
		{
			FormsPanel.setPreferredSize(new Dimension(630,350));
		}
		catch(Exception ex)
		{
			showStatus(ProvClientUtils.getString("Exception while setting properties for bean"),ex); 
		}

		try
		{
			ResultPanel.setPreferredSize(new Dimension(630,350));
		}
		catch(Exception ex)
		{
			showStatus(ProvClientUtils.getString("Exception while setting properties for bean"),ex); 
		}

		try
		{
			SouthPanel.setPreferredSize(new Dimension(640,-50));
		}
		catch(Exception ex)
		{
			showStatus(ProvClientUtils.getString("Exception while setting properties for bean"),ex); 
		}

		try
		{
			statusScrollPane.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.LineBorder(new Color(-16777216),1),ProvClientUtils.getString("Status"),1,2,new Font("Dialog",1,12),new Color(-10066279)));
			statusScrollPane.setMaximumSize(new Dimension(32767,32767));
			statusScrollPane.setMinimumSize(new Dimension(29,45));
			statusScrollPane.setPreferredSize(new Dimension(600,25));
		}
		catch(Exception ex)
		{
			showStatus(ProvClientUtils.getString("Exception while setting properties for bean"),ex); 
		}

		try
		{
			statusTextArea.setWrapStyleWord(true);
			statusTextArea.setEnabled(false);
			statusTextArea.setLineWrap(true);
			statusTextArea.setOpaque(false);
			statusTextArea.setDisabledTextColor(new Color(-13421824));
		}
		catch(Exception ex)
		{
			showStatus(ProvClientUtils.getString("Exception while setting properties for bean"),ex); 
		}
		statusTextArea.addFocusListener(this);
		try
		{
			ButtonPanel.setPreferredSize(new Dimension(600,50));
		}
		catch(Exception ex)
		{
			showStatus(ProvClientUtils.getString("Exception while setting properties for bean"),ex); 
		}

		try
		{
			JPanel2.setPreferredSize(new Dimension(290,10));
		}
		catch(Exception ex)
		{
			showStatus(ProvClientUtils.getString("Exception while setting properties for bean"),ex); 
		}

		try
		{
			backButton.setHorizontalTextPosition(4);
			backButton.setText(ProvClientUtils.getString("Back"));
			backButton.setFont(new Font("Dialog",1,12));
		}
		catch(Exception ex)
		{
			showStatus(ProvClientUtils.getString("Exception while setting properties for bean"),ex); 
		}
		backButton.setVisible(false);
		try
		{
			nextButton.setHorizontalTextPosition(4);
			nextButton.setText(ProvClientUtils.getString("Next"));
			nextButton.setFont(new Font("Dialog",1,12));
		}
		catch(Exception ex)
		{
			showStatus(ProvClientUtils.getString("Exception while setting properties for bean"),ex); 
		}

		try
		{
			descriptionButton.setHorizontalTextPosition(4);
			descriptionButton.setFont(new Font("Dialog",1,12));
			descriptionButton.setText(ProvClientUtils.getString("Description"));
		}
		catch(Exception ex)
		{
			showStatus(ProvClientUtils.getString("Exception while setting properties for bean"),ex); 
		}
		descriptionButton.setVisible(false);
		try
		{
			fetchButton.setHorizontalTextPosition(4);
			fetchButton.setFont(new Font("Dialog",1,12));
			fetchButton.setText(ProvClientUtils.getString("Fetch Result"));
		}
		catch(Exception ex)
		{
			showStatus(ProvClientUtils.getString("Exception while setting properties for bean"),ex); 
		}
		fetchButton.setVisible(false);
		try
		{
			closeButton.setHorizontalTextPosition(4);
			closeButton.setText(ProvClientUtils.getString("Close"));
			closeButton.setFont(new Font("Dialog",1,12));
		}
		catch(Exception ex)
		{
			showStatus(ProvClientUtils.getString("Exception while setting properties for bean"),ex); 
		}

		try
		{
			helpButton.setHorizontalTextPosition(4);
			helpButton.setText(ProvClientUtils.getString("Help"));
			helpButton.setFont(new Font("Dialog",1,12));
		}
		catch(Exception ex)
		{
			showStatus(ProvClientUtils.getString("Exception while setting properties for bean"),ex); 
		}
		helpButton.setVisible(false);
		try
		{
			debugCheckbox.setText(ProvClientUtils.getString("Show Debug Information"));
			debugCheckbox.setFont(new Font("Dialog",0,12));
			debugCheckbox.setHorizontalAlignment(2);
		}
		catch(Exception ex)
		{
			showStatus(ProvClientUtils.getString("Exception while setting properties for bean"),ex); 
		}

		try
		{
			mandatoryLabel.setFont(new Font("Dialog",1,12));
			mandatoryLabel.setForeground(new Color(-13434676));
			mandatoryLabel.setHorizontalTextPosition(0);
			mandatoryLabel.setHorizontalAlignment(4);
			mandatoryLabel.setText(ProvClientUtils.getString("(*) indicates a mandatory field"));
		}
		catch(Exception ex)
		{
			showStatus(ProvClientUtils.getString("Exception while setting properties for bean"),ex); 
		}
		mandatoryLabel.setPreferredSize(new Dimension(mandatoryLabel.getPreferredSize().width+92,mandatoryLabel.getPreferredSize().height+13));
		debugCheckbox.setPreferredSize(new Dimension(debugCheckbox.getPreferredSize().width+21,debugCheckbox.getPreferredSize().height+0));
		helpButton.setPreferredSize(new Dimension(helpButton.getPreferredSize().width+2,helpButton.getPreferredSize().height+0));
		descriptionButton.setPreferredSize(new Dimension(descriptionButton.getPreferredSize().width+18,descriptionButton.getPreferredSize().height+0));
		JPanel2.setPreferredSize(new Dimension(JPanel2.getPreferredSize().width+316,JPanel2.getPreferredSize().height+35));
		ButtonPanel.setPreferredSize(new Dimension(ButtonPanel.getPreferredSize().width+24,ButtonPanel.getPreferredSize().height+62));
		statusScrollPane.setPreferredSize(new Dimension(statusScrollPane.getPreferredSize().width+24,statusScrollPane.getPreferredSize().height+30));
		SouthPanel.setPreferredSize(new Dimension(SouthPanel.getPreferredSize().width+10,SouthPanel.getPreferredSize().height+174));
		CenterPanel.setPreferredSize(new Dimension(CenterPanel.getPreferredSize().width+94,CenterPanel.getPreferredSize().height+0));


		//<End_setUpProperties>
	} 
	public void initVariables()
	{
		//<Begin_initVariables> 
		Top= new javax.swing.JPanel();
		CenterPanel= new javax.swing.JPanel();
		LoginParametersPanel= new javax.swing.JPanel();
		RadioButtonPanel= new javax.swing.JPanel();
		TemplateParamsPanel= new javax.swing.JPanel();
		FormsPanel= new javax.swing.JPanel();
		ResultPanel= new javax.swing.JPanel();
		SouthPanel= new javax.swing.JPanel();
		statusScrollPane= new javax.swing.JScrollPane();
		statusTextArea= new javax.swing.JTextArea();
		ButtonPanel= new javax.swing.JPanel();
		JPanel2= new javax.swing.JPanel();
		backButton= new javax.swing.JButton();
		nextButton= new javax.swing.JButton();
		descriptionButton= new javax.swing.JButton();
		fetchButton= new javax.swing.JButton();
		closeButton= new javax.swing.JButton();
		helpButton= new javax.swing.JButton();
		debugCheckbox= new javax.swing.JCheckBox();
		mandatoryLabel= new javax.swing.JLabel();


		//<End_initVariables>
	} 
	public void setUpGUI(Container container)
	{
		//<Begin_setUpGUI_Container> 
		container.add(Top,BorderLayout.CENTER);
		Top.setLayout(new BorderLayout(1,1));
		Top.add(CenterPanel,BorderLayout.CENTER);
		CenterPanel.setLayout(new CardLayout(2,2));
		CenterPanel.add(LoginParametersPanel,"LoginParametersPanel");
		LoginParametersPanel.setLayout(new BorderLayout(5,5));
		CenterPanel.add(RadioButtonPanel,"RadioButtonPanel");
		RadioButtonPanel.setLayout(new BorderLayout(0,0));
		CenterPanel.add(TemplateParamsPanel,"TemplateParamsPanel");
		TemplateParamsPanel.setLayout(new BorderLayout(0,0));
		CenterPanel.add(FormsPanel,"FormsPanel");
		FormsPanel.setLayout(new BorderLayout(0,0));
		CenterPanel.add(ResultPanel,"ResultPanel");
		ResultPanel.setLayout(new FlowLayout(1,0,0));
		Top.add(SouthPanel,BorderLayout.SOUTH);
		SouthPanel.setLayout(new BorderLayout(0,0));
		SouthPanel.add(statusScrollPane,BorderLayout.NORTH);
		statusScrollPane.getViewport().add(statusTextArea);
		SouthPanel.add(ButtonPanel,BorderLayout.CENTER);
		ButtonPanel.setLayout(new BorderLayout(0,0));
		ButtonPanel.add(JPanel2,BorderLayout.SOUTH);
		JPanel2.setLayout(new FlowLayout(1,5,0));
		JPanel2.add(backButton);
		JPanel2.add(nextButton);
		JPanel2.add(descriptionButton);
		JPanel2.add(fetchButton);
		JPanel2.add(closeButton);
		JPanel2.add(helpButton);
		ButtonPanel.add(debugCheckbox,BorderLayout.WEST);
		ButtonPanel.add(mandatoryLabel,BorderLayout.EAST);


		//<End_setUpGUI_Container>

	} 
	public void setUpConnections()
	{
		//<Begin_setUpConnections> 

		java.awt.event.ActionListener CloseButtonListener1 =  new java.awt.event.ActionListener()
		{

			//<TOP_PART>  - Please do not remove this comment or write any custom code above this

			//Listener Interface Methods Are Added Below 


			public void actionPerformed( java.awt.event.ActionEvent arg0)
			{
				confirmAndClose();
			}
		};
		closeButton.addActionListener(CloseButtonListener1);
		java.awt.event.ActionListener HelpButtonListener1 =  new java.awt.event.ActionListener()
		{

			//<TOP_PART>  - Please do not remove this comment or write any custom code above this

			//Listener Interface Methods Are Added Below 


			public void actionPerformed( java.awt.event.ActionEvent arg0)
			{
				helpAction();
			}
		};
		helpButton.addActionListener(HelpButtonListener1);
		NextButtonListener NextButtonListener1 =  new NextButtonListener();
		nextButton.addActionListener(NextButtonListener1);
		FetchButtonListener FetchButtonListener1 =  new FetchButtonListener();
		fetchButton.addActionListener(FetchButtonListener1);
		BackButtonListener BackButtonListener1 =  new BackButtonListener();
		backButton.addActionListener(BackButtonListener1);
		DescriptionButtonListener DescriptionButtonListener1 =  new DescriptionButtonListener();
		descriptionButton.addActionListener(DescriptionButtonListener1);

		//<End_setUpConnections>
	} 




	public void showStatus(String message)
	{
		//<Begin_showStatus_String>
		System.out.println(message);
		//<End_showStatus_String>
	}
	public void showStatus(String message,Exception ex)
	{
		//<Begin_showStatus_String_Exception>
		System.out.println(message);
		ex.printStackTrace();
		//<End_showStatus_String_Exception>
	}





	public TemplateUIPanel()
	{
		//<Begin_TemplateUIPanel>
		this.init();

		//<End_TemplateUIPanel>
		showLoginPanel();
		perfTimeZone = TimeZone.getDefault();
	}

	public TemplateUIPanel(java.applet.Applet applet)
	{
		//<Begin_TemplateUIPanel_java.applet.Applet>
		this.applet = applet;
		this.init();

		//<End_TemplateUIPanel_java.applet.Applet>
		showLoginPanel();
		setupTimeZone();
	}

	private void setupTimeZone()
	{
		if(perfTimeZone != null) return;
		String timeZonePreferrence=applet.getParameter("TIMEZONE_PREFERENCE");
		String timeZoneID = null;
		if (timeZonePreferrence!=null)
		{
			if(timeZonePreferrence.trim().equalsIgnoreCase("BE"))
			{
				timeZoneID=applet.getParameter("BE_TIMEZONE_ID");
			}
			else if (timeZonePreferrence.trim().equalsIgnoreCase("FE"))
			{
				timeZoneID=applet.getParameter("FE_TIMEZONE_ID");
			}
			else
				timeZoneID=TimeZone.getDefault().getID();
		}
		else
			timeZoneID = TimeZone.getDefault().getID();
		perfTimeZone = TimeZone.getTimeZone(timeZoneID);	
	}


	public TemplateUIPanel(java.applet.Applet applet,Properties prop)
	{
		this.applet = applet;
		this.init();
		setupTimeZone();
		try
		{
			apiHandler=new ProvisioningApiHandler(prop); 
			manager = new StyleSheetManager(uiHandler,prop,descriptionTextArea);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.err.println(ProvClientUtils.getString("Exception while creating ProvisioningApiHandler instance"));
		}
	}
	private void showLoginPanel()
	{
		backButton.setVisible(false);
		helpButton.setVisible(false);
		nextButton.setVisible(true);
		closeButton.setVisible(true);
		nextButton.setText(ProvClientUtils.getString("Next"));
		closeButton.setText(ProvClientUtils.getString("Close"));
		mandatoryLabel.setVisible(false);
		debugCheckbox.setVisible(false);
		statusScrollPane.setVisible(false);
		descriptionButton.setVisible(false);
		southPanelDimension=SouthPanel.getPreferredSize();
		SouthPanel.setPreferredSize(JPanel2.getPreferredSize());
		LoginParametersPanel.removeAll();
		loginPanel=new LoginPanel(applet);
		LoginParametersPanel.add(loginPanel,BorderLayout.CENTER);
		((CardLayout)CenterPanel.getLayout()).show(CenterPanel,"LoginParametersPanel");
	}	
	private void  createDescriptionDialog()
	{
		Container c = getTopLevelAncestor();
		dialog = new JDialog((JFrame)null,ProvClientUtils.getString("Description"));
		dialog.getContentPane().removeAll();
		dialog.setSize(500,120);
		dialog.getContentPane().add(descriptionScrollPane);
		dialog.addWindowListener(new WindowAdapter()
				{
					public void windowClosing(WindowEvent we)
					{
						dialog.dispose();
					}
				});
	}

	private void showDescriptionDialog()
	{
		if(firstTime)
		{
			Dimension dd= getSize();
			Dimension prd=dialog.getSize();
			Point pd= getLocationOnScreen();
			int x=(int)(pd.getX()+(dd.getWidth()-prd.getWidth())/2);
			int y=(int)(pd.getY()+(dd.getHeight()-prd.getHeight())/2);
			dialog.setLocation(x,y);
			firstTime = false;
		}
		dialog.show();
	}

	public void disposeDescriptionDialog()
	{ 
		if(dialog != null)
		{
			if(dialog.isVisible())
			{
				dialog.dispose();
			}
		}
	}

	/*public TemplateUIPanel(Applet applet, Container container)
	  {
	  this(applet);
	  }*/

	public Applet getApplet()
	{
		return applet;
	}


	public void focusGained(FocusEvent ev)
	{
		if(ev.getSource() == descriptionTextArea)
			debugCheckbox.requestFocus();
		else transferFocus();
	}

	public void focusLost(FocusEvent ev)
	{
	}

	public void valueChanged(TreeSelectionEvent event)
	{
		DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
		if(selectedNode != null)
		{
			JPanel panel = (JPanel) nodesAndPanelsTable.get(selectedNode);        
			if(panel != null) 
			{
				((CardLayout)rightPanel.getLayout()).show(rightPanel,selectedNode.getUserObject().toString());
				String description = panel.getName();
				if(description != null)
					descriptionTextArea.setText(description);
				setFocus(panel);
			}
		}
	}

	public void stateChanged(ChangeEvent ce)
	{
		setDescriptionAndEnablebuttons();
	}
	public void preFailOverNotification(FailOverEvent failoverevent)
	{
		apiHandler.setFailOverOccured(true);
	}

	public void postFailOverNotification(FailOverEvent failoverevent)
	{
		if (failoverevent.getResult())
		{
			Properties p=new Properties();
			p.putAll(failoverevent.getPresentFEInfo());
			p.put("HOSTNAME",failoverevent.getNewHost());
			//int port=1099;
			try
			{
				//port=Integer.parseInt(com.adventnet.nms.util.NmsClientUtil.applet.getParameter("RMI_REG_PORT"));
				//apiHandler.setRMIRegPort(port+"");
				//apiHandler.setRMIServerName(failoverevent.getNewHost());
				apiHandler.reInitializeAPI(p);
				apiHandler.setFailOverOccured(false);
			}
			/*catch (NumberFormatException n)
			  {
			  n.printStackTrace();
			  }*/
			catch(Exception e)
			{
				String message = MessageFormat.format(ProvClientUtils.getString("Exception occured during Post Fail Over Notification {0}"), new Object[]{e.getMessage()});
				System.err.println(message);
				JOptionPane.showMessageDialog(this, MessageFormat.format(ProvClientUtils.getString("ProvisioningAPI is not available in the RMI Registry of {0}"), new Object[]{failoverevent.getNewHost()}),ProvClientUtils.getString("Provisionig"),JOptionPane.INFORMATION_MESSAGE);
				closeAction();
			}
		}
		else
		{
			apiHandler.setExit(true);
			javax.swing.JOptionPane.showMessageDialog(TemplateUIPanel.this,ProvClientUtils.getString("Connection with FE lost"),ProvClientUtils.getString("Provisioning"),javax.swing.JOptionPane.INFORMATION_MESSAGE);
			closeAction();
		}
	}

	public void helpAction( )
	{
		if(helpUrl == null)
		{
			JOptionPane.showMessageDialog(this, MessageFormat.format(ProvClientUtils.getString("No help URL available in the template {0}"), new Object[]{titleBorderString}), ProvClientUtils.getString("Provisioning"), JOptionPane.ERROR_MESSAGE);
			return;
		}

		try
		{
			URL finalUrl = null;
			if(applet != null)
			{
				finalUrl = new URL(applet.getDocumentBase() +"../"+ helpUrl);
				applet.getAppletContext().showDocument(finalUrl, "_blank");
			}
			else
			{
				File f = new File(helpUrl);
				finalUrl = f.toURL();
				JEditorPane pane = new JEditorPane();
				pane.setPage(finalUrl);
				pane.setEditable(false);
				JScrollPane spane = new JScrollPane(pane);
				JFrame frame = new JFrame(ProvClientUtils.getString("Help"));
				frame.getContentPane().add(spane);
				frame.setSize(600, 500);
				frame.setVisible(true);
			}
		}
		catch(Exception exc)
		{
			if (exc.getMessage()!=null)
			{
				if (exc.getMessage().trim().length()>50)
				{
					JFrame parentF=null;
					if (TemplateUIPanel.this.getTopLevelAncestor() instanceof JFrame)
					{
						parentF=(JFrame)TemplateUIPanel.this.getTopLevelAncestor();
					}
					test.provisioning.MessageWindow.showMessage(parentF,ProvClientUtils.getString("Provisioning"),exc.getMessage());
				}
				else
				{
					JOptionPane.showMessageDialog(TemplateUIPanel.this,exc.getMessage(),ProvClientUtils.getString("Provisioning"),JOptionPane.ERROR_MESSAGE);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this, ProvClientUtils.getString("Exception while getting Help URL"), ProvClientUtils.getString("Provisioning"), JOptionPane.ERROR_MESSAGE);
			}
		}
	}



	class Worker implements Runnable
	{
		JPanel parent =null;

		public Worker(JPanel parent)
		{
			this.parent = parent;
		}
		public void run()
		{

			try
			{
				Properties props = uiHandler.getUserInputValues();
                NmsClientUtil.busyCursor(parent);
				String id = applyUserConfiguration(props);
                NmsClientUtil.normalCursor(parent);
				BaseElement be=ProvClientUtils.getBaseElement(id);
				
				Element docElement = be.getElement();
				StringWriter writer = new StringWriter();
				try
				{
					TransformerFactory fac = TransformerFactory.newInstance();
					Transformer trans = fac.newTransformer();
					trans.setOutputProperty("indent","yes");//No I18N
					trans.setOutputProperty("encoding","UTF-8");//No I18N
					DOMSource domSource = new DOMSource(docElement);
					StreamResult streamResult = new StreamResult(writer);
					trans.transform(domSource, streamResult);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}

				if (be instanceof TemplateResult)
				{
					showResultPanel(be.getAttribute("status"),writer.toString());
					//showResultPanel(be.getAttribute("status"),be.toString());
				}
				else if (be instanceof Template)
				{
					setTemplate((Template)be);
					appletApplicationCommon();
				}
				else if(getTopLevelAncestor() instanceof test.provisioning.TemplateNmsFrame)
				{
					JOptionPane.showMessageDialog(TemplateUIPanel.this,ProvClientUtils.getString("Provisioning activity is scheduled"),ProvClientUtils.getString("Provisioning"),JOptionPane.PLAIN_MESSAGE);
					closeAction();
				}
				/*     else if ((apiHandler.getStatus(id).equals("NOTSTARTED"))&&
					   (template.hasAttribute("when"))&&
					   (template.getAttribute("when").trim().length()!=0))*/
				else
				{
					fetchButton.setVisible(true);
					fetchButton.setActionCommand(id);
					debugCheckbox.setVisible(false);
					mandatoryLabel.setVisible(false);
					backButton.setVisible(false);
					closeButton.setText(ProvClientUtils.getString("Close"));
					closeButton.setVisible(false);
					nextButton.setVisible(false);
					helpButton.setVisible(false);
					statusTextArea.setText(MessageFormat.format(ProvClientUtils.getString("Provisioning activity is scheduled at {0}.Click Fetch Result button,after scheduled time to get result"), new Object[]{template.getAttribute("when").trim()}));    
				}/*
					else
					{
					disableAllComponentsInButtonPanel();
					Worker w=new Worker(TemplateUIPanel.this,id);
					workerThread = new Thread(w);
					workerThread.start(); 
					}*/
			}
			catch(Exception ex)
			{
				NmsClientUtil.normalCursor(parent);
				statusTextArea.setText(ex.getMessage());
                System.err.println(ProvClientUtils.getString("Error occured while configuring"));
				if (ex.getMessage()!=null)
				{
					if (ex.getMessage().trim().length()>50)
					{
						JFrame parentF=null;
						if (TemplateUIPanel.this.getTopLevelAncestor() instanceof JFrame)
						{
							parentF=(JFrame)TemplateUIPanel.this.getTopLevelAncestor();
						}
						test.provisioning.MessageWindow.showMessage(parentF,ProvClientUtils.getString("Provisioning"),ex.getMessage());
					}
					else
					{
						JOptionPane.showMessageDialog(TemplateUIPanel.this,ex.getMessage(),ProvClientUtils.getString("Provisioning"),JOptionPane.ERROR_MESSAGE);
					}
				}
				if(templateParametersPanelMadeVisible)
				{
					backButton.doClick();
				}
				else
				{
					closeAction();
				}
			}
		}
	}

	public void closeAction()
	{
		Container c=getTopLevelAncestor();
		c.setVisible(false);
		if (c instanceof JFrame)
		{
			((JFrame)c).dispose();
		}
		if(dialog != null)
		{
			if(dialog.isVisible())
			{
				dialog.dispose();
			}
		}
		if(applet == null)
		{
			System.exit(0);
		}
	}
	public void confirmAndClose()
	{
		String message=ProvClientUtils.getString("Do you want to close?");

		if ((whichPanelShowing==TEMPLATE_PARAMS_PANEL_SHOWING)||(whichPanelShowing==FORMS_PANEL_SHOWING))
		{
			message=ProvClientUtils.getString("Do you want to abort provisioning?");
		}
		int ok=JOptionPane.showConfirmDialog(this,message,ProvClientUtils.getString("Provisioning"),JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
		if (ok==JOptionPane.YES_OPTION)
		{
			try
			{
				if ((whichPanelShowing==FORMS_PANEL_SHOWING)&&(template!=null)&&(template.hasAttribute("operationId")))
				{
					apiHandler.stopRunningOperation(template.getAttribute("operationId"));
				}
			}
			catch(Exception exc)
			{
				if((exc.getMessage()!=null)&&(!(exc.getMessage().trim().equals(""))))
				{
					if (exc.getMessage().trim().length()>50)
					{
						test.provisioning.MessageWindow.showMessage(NmsClientUtil.getParentFrame(),ProvClientUtils.getString("Provisioning"),exc.getMessage());
					}
					else
					{
						JOptionPane.showMessageDialog(this,exc.getMessage(),ProvClientUtils.getString("Provisioning"),JOptionPane.ERROR_MESSAGE);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(this,MessageFormat.format(ProvClientUtils.getString("Error occured while stoping the activity with id {0}"), new Object[]{template.getAttribute("operationId")}),ProvClientUtils.getString("Provisioning"),JOptionPane.INFORMATION_MESSAGE);
				}
			}
			closeAction();
		}
	}
	/*public boolean isFormsPanelShowing()
	  {
	  return whichPanelShowing==FORMS_PANEL_SHOWING;
	  }
	  public boolean isSubmittedTemplate()
	  {
	  if(template==null)
	  {
	  return false;
	  }
	  else if (!template.hasAttribute("operationId"))
	  {
	  return false;
	  }
	  return true;
	  }
	  private String getOperationId()
	  {
	  if ((template!=null)||(template.hasAttribute("operationId")))
	  {
	  return template.getAttribute("operationId");
	  }
	  return null;
	  }*/

	private void showResultPanel(String status,String resultString)
	{
		try
		{
            nextButton.setVisible(true);
            if (debugCheckbox.isSelected())
			{
				System.out.println(resultString);
			}
			TemplateResult tr = new TemplateResult(resultString);
			if (!tr.hasAttribute("errorMessage"))
			{
				ResultPanel.removeAll();
				ResultPanel.setLayout(new BorderLayout());
				ProvisioningResult1 = new ProvisioningResult(applet,status,resultString);
				ResultPanel.add(ProvisioningResult1,BorderLayout.CENTER);
				whichPanelShowing = RESULT_PANEL_SHOWING;
				statusScrollPane.setVisible(false);
				debugCheckbox.setVisible(false);
				mandatoryLabel.setVisible(false);
				closeButton.setText(ProvClientUtils.getString("Close"));
				if (radioButtonPanelMadeVisible)
				{
					backButton.setVisible(true);
					backButton.setText(ProvClientUtils.getString("Templates"));
				}
				else
				{
					backButton.setVisible(false);
				}
				helpButton.setVisible(false);
				if (tr.getSubTagsByName("ConfigResult").size()>0)
				{
					nextButton.setText(ProvClientUtils.getString("Details"));
				}
				else nextButton.setVisible(false);
				if ((titleBorderString==null)||(titleBorderString.equals("")))
				{
					setBorder(new TitledBorder(new LineBorder(Color.gray,1),MessageFormat.format(ProvClientUtils.getString("Result for {0}"), new Object[]{tr.getAttribute("name")}),0,2));
				}
				else
				{
					setBorder(new TitledBorder(new LineBorder(Color.gray,1), MessageFormat.format(ProvClientUtils.getString("Result for {0}"), new Object[]{titleBorderString}),0,2));
				}
				((CardLayout)CenterPanel.getLayout()).show(CenterPanel,"ResultPanel");
				CenterPanel.revalidate();
				descriptionButton.setVisible(false);
				if(dialog != null)
				{
					if(dialog.isVisible())
					{
						dialog.dispose();
					}
				}
			}
			else 
			{
				JOptionPane.showMessageDialog(this,tr.getAttribute("errorMessage"),ProvClientUtils.getString("Provisioning Result"),JOptionPane.INFORMATION_MESSAGE);
			}
			enableAllComponentsInButtonPanel();
		}
		catch(Exception ex)
		{
			System.err.println(ProvClientUtils.getString("Error occured while constructing Result UI"));
			System.err.println(ex.getMessage());
        }
	}

	public void showZerothPanel()
	{
		whichPanelShowing = RADIOBUTTON_PANEL_SHOWING;
		template=null;
		radioButtonPanelMadeVisible = true;
		RadioButtonPanel.removeAll();

		backButton.setVisible(false);
		helpButton.setVisible(false);
		nextButton.setVisible(true);
		closeButton.setVisible(true);
		nextButton.setText(ProvClientUtils.getString("Next"));
		closeButton.setText(ProvClientUtils.getString("Close"));
		mandatoryLabel.setVisible(false);
		debugCheckbox.setVisible(false);
		statusScrollPane.setVisible(true);
		descriptionButton.setVisible(false);

		JPanel listPanel = new JPanel(new BorderLayout());
		JPanel upperListPanel = new JPanel ();
		JPanel centerListPanel = new JPanel ();
		centerListPanel.setLayout(new BoxLayout(centerListPanel, BoxLayout.X_AXIS));

		JLabel selectLabel = new JLabel(ProvClientUtils.getString("Select a Template for Provisioning"));
		upperListPanel.add(selectLabel);

		centerListPanel.add(Box.createHorizontalGlue());
		statusTextArea.setText(ProvClientUtils.getString("Template list loaded successfully"));
		centerListPanel.add(getRadioButtonPanel());
		centerListPanel.add(Box.createHorizontalGlue());

		listPanel.add(upperListPanel,BorderLayout.NORTH);
		listPanel.add(centerListPanel,BorderLayout.CENTER);

		RadioButtonPanel.setLayout(new BorderLayout());
		RadioButtonPanel.add(listPanel,BorderLayout.CENTER);
		if (bg.getButtonCount()>0)
		{
			radioButtons[0].setSelected(true);
		}
		setBorder(new TitledBorder(new LineBorder(Color.gray,1),ProvClientUtils.getString("Provisioning Options"),0,2));
		((CardLayout)CenterPanel.getLayout()).show(CenterPanel,"RadioButtonPanel");
		setFocus(centerListPanel);
		ProvisioningResult1 = null;
	}

	private Component getRadioButtonPanel()
	{
		JPanel p =new JPanel();	
		try
		{
			String []s =(this.getTemplateList());
			bg=new ButtonGroup();
			radioButtons =new JRadioButton[s.length];
			GridBagLayout gb=new GridBagLayout();
			GridBagConstraints gc= new GridBagConstraints();
			gc.insets=new Insets(2,2,2,2);
			gc.anchor=GridBagConstraints.WEST;
			p.setLayout(gb);
			for (int i=0;i<s.length;i++)
			{
				radioButtons[i]=new JRadioButton(s[i]);
				radioButtons[i].setActionCommand((s[i]));
				radioButtons[i].setLocation(radioButtons[i].getX()+10,radioButtons[i].getY());
				gc.gridy=i;
				gb.setConstraints(radioButtons[i],gc);
				bg.add(radioButtons[i]);
				p.add(radioButtons[i]);
			}
		}
		catch (Exception ex)
		{
			System.err.println(ProvClientUtils.getString("Error in getting list of Templates"));
			System.err.println(ex.getMessage());
        }
		JScrollPane sp = new JScrollPane(p);
		JViewport vp=new JViewport();
		JButton b=new JButton(ProvClientUtils.getString("Provisioning Templates"));
		b.setFocusPainted(false);
		b.setBorder(new BevelBorder(BevelBorder.RAISED));
		vp.setView(b);
		sp.setColumnHeader(vp);
		sp.setBorder(new BevelBorder(BevelBorder.LOWERED));
		return (sp);
	}

	public String[] getTemplateList() throws java.rmi.RemoteException
	{
		return apiHandler.getTemplateList();
	}
	public void showTemplateParametersPanel(String tempname)
	{
		constructInitialFormPanel(tempname,getInitialFormProperties(tempname));
	}
	private void constructInitialFormPanel(String tempname , Properties initialProperties)
	{
		templateName = tempname;
		if (initialProperties.size()==0)
		{
			uiHandler.getPanelForProperties(initialProperties);
			whichPanelShowing = TEMPLATE_PARAMS_PANEL_SHOWING;
			nextButton.doClick();
		}
		else
		{
			TemplateParamsPanel.removeAll();
			TemplateParamsPanel.setLayout(new BorderLayout());
			debugCheckbox.setVisible(false);
			mandatoryLabel.setVisible(false);
			if(radioButtonPanelMadeVisible)
			{
				backButton.setText(ProvClientUtils.getString("Back"));
				backButton.setVisible(true);
			}
			else
			{
				backButton.setVisible(false);
			}
			nextButton.setText(ProvClientUtils.getString("Next"));
			nextButton.setVisible(true);
			helpButton.setVisible(false);
			closeButton.setVisible(true);
			statusTextArea.setText(ProvClientUtils.getString("Template parameters loaded successfully"));
			JPanel tempparamspanel1=uiHandler.getPanelForProperties(initialProperties);

			TemplateParamsPanel.add(tempparamspanel1,BorderLayout.CENTER);
			TemplateParamsPanel.updateUI();

			titleBorderString = tempname;
			if(titleBorderString.equals(""))
			{
				setBorder(new TitledBorder(new LineBorder(Color.gray,1), MessageFormat.format(ProvClientUtils.getString("Template : {0}"), new Object[]{tempname}),0,2));
			}
			else
			{
				setBorder(new TitledBorder(new LineBorder(Color.gray,1), MessageFormat.format(ProvClientUtils.getString("Template : {0}"), new Object[]{titleBorderString}),0,2));
			}
			whichPanelShowing = TEMPLATE_PARAMS_PANEL_SHOWING;
			templateParametersPanelMadeVisible=true;
			((CardLayout)CenterPanel.getLayout()).show(CenterPanel,"TemplateParamsPanel");
			setFocus(TemplateParamsPanel);
		}	
	}

	private void showMessageInDialog( String message,String title,int type)
	{
		JOptionPane.showMessageDialog(this, message , title , type);
	}

	public void loadUsingProvisioningApi(String templateName, String params)
	{
		this.templateName=templateName;
		numberOfRequiredFields = 0;
		statusTextArea.setText(ProvClientUtils.getString("Template loaded successfully"));
		try
		{
			String s = apiHandler.getTemplate(templateName, params);
			setTemplate(new Template(s));
		}
		catch(Exception e)
		{
			statusTextArea.setText(e.getMessage());
			System.err.println(ProvClientUtils.getString("Error while getting the Template"));
			System.err.println(e.getMessage());
            nextButton.setVisible(false);
			template = null;
		}
		appletApplicationCommon();

	}
	public Properties getInitialFormProperties(String templateName)
	{
		this.templateName=templateName;
		String parameters=null;
		try
		{
			parameters =apiHandler.getTemplateParameters(templateName);
			if (parameters!=null)
			{
				return (new TemplateParams(parameters).getProperties());
			}
		}
		catch(Exception e)
		{
			statusTextArea.setText(e.getMessage());
			System.err.println(ProvClientUtils.getString("Error occured while getting Template parameters"));
			System.err.println(e.getMessage());
            nextButton.setVisible(false);
		}
		return new Properties();
	}
	public void loadTemplateParams(String templateName,Properties propsArg)
	{
		try
		{
			TemplateParams ttpp=new TemplateParams();
			ttpp.setProperties(propsArg);

			org.w3c.dom.Document document = ttpp.getElement().getOwnerDocument();
			StringWriter writer = new StringWriter();
			try
			{
				TransformerFactory fac = TransformerFactory.newInstance();
				Transformer trans = fac.newTransformer();
				trans.setOutputProperty("indent","yes");//No I18N
				trans.setOutputProperty("encoding","UTF-8");//No I18N
				DOMSource domSource = new DOMSource(document);
				StreamResult streamResult = new StreamResult(writer);
				trans.transform(domSource, streamResult);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			//loadUsingProvisioningApi(templateName, ttpp.toString());
			loadUsingProvisioningApi(templateName, writer.toString());
		}
		catch(Exception eee)
		{
			appendStatus(eee.getMessage());
			System.err.println(ProvClientUtils.getString("Error occured while loading Template Parameters"));
			System.err.println(eee.getMessage());
        }            
	} 
	public String applyUserConfiguration(Properties propsArg) throws RemoteException,TemplateInitializationException,InvalidTemplateException,OperationFailedException
	{ 
		if(template.hasAttribute("statusMessage"))
		{
			template.getElement().removeAttribute("statusMessage");
		}

		template=PopulateTemplateParams.substituteParams(template,propsArg);
		if((template.getAttribute("triggerHandler").trim().length()==0)&&(template.getAttribute("when").trim().length()==0)&&(scheduled))
		{
			DateTimeComponent d= new DateTimeComponent();
			java.util.Calendar calendar = java.util.Calendar.getInstance();
			d.setYear(calendar.get(Calendar.YEAR));
			d.setMonth(calendar.get(Calendar.MONTH)+1);
			d.setDay(calendar.get(Calendar.DATE));
			d.setHour(calendar.get(Calendar.HOUR));
			d.setMinute(calendar.get(Calendar.MINUTE)+5);
			d.setSecond(calendar.get(Calendar.SECOND));
			JPanel p=new JPanel(new GridLayout(2,1));
			p.add(new JLabel(ProvClientUtils.getString("Please specify schedule time")));
			p.add(d);
			int ok=JOptionPane.showConfirmDialog(this,p,ProvClientUtils.getString("Provisioning"),JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
			if (ok==JOptionPane.OK_OPTION)
			{
				String st=d.getDateTime();
				if (st!=null)
				{
					template.setAttribute("when",st);
				}
				else
				{
					JOptionPane.showMessageDialog(this,ProvClientUtils.getString("Invalid Time Specification, so submitted for synchronous execution"),ProvClientUtils.getString("Provisioning"),JOptionPane.PLAIN_MESSAGE);
				}
				//scheduled=false;
			}
		}

		if (isDebugEnabled())
		{
			System.out.println(ProvClientUtils.getString("Template submitted for provisioning")+" \n"+template);
		}
		String user=apiHandler.getProperties().getProperty("USERNAME");
		if(user!=null)
		{
			template.setAttribute("owner",user);
		}
		return apiHandler.provision(template);
	}
	public void getProvisionPanel(String tempname)
	{
		numberOfRequiredFields = 0;
		statusTextArea.setText(ProvClientUtils.getString("Template loaded successfully"));
		Properties props = getInitialFormProperties(tempname);
		Enumeration en = props.propertyNames();
		while(en.hasMoreElements())
		{
			String key = (String)en.nextElement();
			String value =applet.getParameter(key);
			if ((value==null)||(value.trim().length()==0))
			{
				constructInitialFormPanel(tempname , props );
				return;
			}  
			props.setProperty(key,value );
		}
		try
		{
			loadTemplateParams(tempname,props);
			//appletApplicationCommon();
		}
		catch(Exception eee)
		{
			System.err.println(ProvClientUtils.getString("Exception in getting the Provision Panel"));
			System.err.println(eee.getMessage());
        }            
	}    


	private void appletApplicationCommon()
	{
		FormsPanel.removeAll();
		whichPanelShowing = FORMS_PANEL_SHOWING;
		debugCheckbox.setVisible(true);
		nextButton.setText(aplorfinish);
		closeButton.setText(ProvClientUtils.getString(closeButtonName));
		FormsPanel.setLayout(new BorderLayout());
		JPanel upperPanel = new JPanel(new BorderLayout());
		FormsPanel.add(upperPanel);
		if(template != null)
		{
			if(template.getElement().hasAttribute("statusMessage"))
			{
				statusTextArea.setText(template.getAttribute("statusMessage"));
			}

			if(!useStyleSheet)
			{
				if(treeNeeded)
				{
					splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
					// Selecting the first leaf node and showing the first panel on start up.
					TreeNode[] nodeArray = formsNode.getFirstLeaf().getPath();
					if(nodeArray != null)
					{
						TreePath path = new TreePath(nodeArray);
						tree.setSelectionPath(path);
					}
					treePanel.setPreferredSize(new Dimension(175,550));
					splitPane.setContinuousLayout(true);
					splitPane.setLeftComponent(treePanel);
					splitPane.setRightComponent(rightPanel);
					upperPanel.add(splitPane,BorderLayout.CENTER);
				}
				else
				{
					upperPanel.add(jtp,BorderLayout.CENTER);
				}
				if(!treeNeeded)
				{
					if (jtp.getTabCount()==0)
					{
						if (statusTextArea.getText().equals(ProvClientUtils.getString("Template loaded successfully")))
						{
							descriptionTextArea.setText(ProvClientUtils.getString("No user input forms are required in this template. Please proceed to execute the Template"));
						}
						else
						{
							descriptionTextArea.setText("");
						}
					}
				}
			}
			else
			{
				try
				{
					JPanel panel = manager.getRuntimePanel(template,template.getAttribute("stylesheet"));
					upperPanel.add(new JScrollPane(panel));
					if(getTopLevelAncestor() instanceof  Window)
					{
						Dimension d = panel.getPreferredSize();
						int height = (int) d.getHeight();
						int width = (int)d.getWidth();
						Container c = getTopLevelAncestor();
						Window w = (Window)c;
						//setPreferredSize(new Dimension(width+40,height+140));
						w.setSize(width+50,height+190);
						if(c instanceof test.provisioning.TemplateNmsFrame)
						{
							centerWindow(w);
						}
						w.validate();
					}
				}
				catch(Exception e)
				{
					String message = ProvClientUtils.getString("Unable to use the stylesheet for the Template.Using Default layout");
					System.err.println(message);
					statusTextArea.setText(message + e.getMessage());
                    template.getElement().removeAttribute("stylesheet");
					setTemplate(template);
					appletApplicationCommon();
				}
			}
		}

		((CardLayout)CenterPanel.getLayout()).show(CenterPanel,"FormsPanel");
		if ((helpUrl != null)&&(!(helpUrl.equals(""))))
		{
			helpButton.setVisible(true);
		}
		descriptionButton.setVisible(true);
		setDescriptionAndEnablebuttons();
		if((templateParametersPanelMadeVisible)&&((template==null)||template.getAttribute("operationId").trim().length()==0))
		{
			backButton.setText(ProvClientUtils.getString("Back"));
			backButton.setVisible(true);
		}
		else
		{
			backButton.setVisible(false);
		}
		if(template == null)
		{
			nextButton.setVisible(false);
			descriptionButton.setVisible(false);
		}
		else
		{
			nextButton.setVisible(true);
        }
		if (uiHandler.getNumberOfRequiredFields() > 0)
		{
			mandatoryLabel.setVisible(true);
		}	
		else
		{
			mandatoryLabel.setVisible(false);
		}
		FormsPanel.updateUI();
		setFocus(FormsPanel);

	}

	private void centerWindow(Window w)
	{
		JFrame jf = NmsClientUtil.getParentFrame();
		Dimension dim = jf.getSize();
		int height = (int)dim.getHeight();
		int width = (int)dim.getWidth();
		int winheight = (int)w.getSize().getHeight();
		int winwidth = (int)w.getSize().getWidth();
		int y = (height-winheight)/2;
		int x = (width-winwidth)/2;
		if(x < 0 || y < 0) return;
		w.setLocation(x,y);
	}


	private void setDescriptionAndEnablebuttons()
	{
		if(jtp.getSelectedComponent() == null)
		{
			return;
		}
		Component c = ((JScrollPane) jtp.getSelectedComponent()).getViewport().getView();
		c.requestFocus();
		if(c.getName()!=null)
		{
			descriptionTextArea.setText(c.getName());
		}
		setFocus(((JScrollPane) jtp.getSelectedComponent()));
	}

	private void disableAllComponentsInButtonPanel()
	{
		Container container = nextButton.getParent();
		Component [] c = container.getComponents();
		for (int i=0;i<c.length;i++)
		{
			c[i].setEnabled(false);
		}
		debugCheckbox.setEnabled(false);
	}

	private void enableAllComponentsInButtonPanel()
	{
		Container container = nextButton.getParent();
		Component [] c = container.getComponents();
		for (int i=0;i<c.length;i++)
		{
			c[i].setEnabled(true);
		}
		debugCheckbox.setEnabled(true);
	}

	public boolean isDebugEnabled()
	{
		return debugCheckbox.isSelected();
	}

	public void setDebugEnabled(boolean b)
	{
		if ((debugCheckbox != null)&&(debugCheckbox.isVisible()))
		{
			debugCheckbox.setSelected(b);
		}
	}

	public String getResult()
	{
		return result;
	}

	/*public Properties getInitialFormProperties(String tempName)
	  {
	  return apiHandler.getInitialFormProperties(tempName);
	  } */	

	public void appendStatus(String status)
	{
		//statusTextArea.append("\n" + status);
		statusTextArea.setText(status);
	}	

	public void clearStatus()
	{
		statusTextArea.setText(""); 
	}

	private void validateTablesInForms(Vector forms) throws InvalidTemplateException
	{
		int size = forms.size();
		for(int i = 0 ; i < size ; i++)
		{
			Form form = (Form) forms.elementAt(i);
			Vector tables = form.getTables();
			int tablesize = tables.size();
			if(tablesize == 0) continue;
			validateTables(tables);
		}
	}

	private void validateTables(Vector tables) throws InvalidTemplateException
	{
		int size = tables.size();
		for(int i = 0 ; i < size ; i++)
		{
			Table table = (Table)tables.elementAt(i);
			int rows = table.getRowCount();
			int columns = table.getColumnCount();
			Vector tableUserInputs = table.getUserInputs();
			int userinputsize = tableUserInputs.size();
			if(rows*columns > userinputsize)
			{
				String message = ProvClientUtils.getString("The values for the table are not sufficient");
				throw new InvalidTemplateException(message);
			}
			if(userinputsize > rows*columns)
			{
				String message = ProvClientUtils.getString("Number of UserInputs is more than needed");
				throw new InvalidTemplateException(message);
			}
		}
	}

	public void setTemplate(Template templateArg)
	{
		Vector formsVec = templateArg.getForms(templateArg.getAttribute("formId"));
		try
		{
			validateTablesInForms(formsVec);
		}
		catch(Exception e)
		{
			String message = MessageFormat.format(ProvClientUtils.getString("Error in Table format {0}"), new Object[]{e.getMessage()});
			System.err.println(message);
			statusTextArea.setText(message);
			return;
		}
		statusTextArea.setText(ProvClientUtils.getString("Template loaded successfully"));
		template = templateArg;
		title=templateArg.getName();
		String label=templateArg.getAttribute("label");
		if ((label==null)||(label.equals("")))
		{
			titleBorderString =templateArg.getAttribute("name");
		}
		else
		{
			titleBorderString=label;
		}
		if((templateArg.getCloseButtonName()!=null)&&(!(templateArg.getCloseButtonName().equals(""))))
		{
			closeButtonName=ProvClientUtils.getString(templateArg.getCloseButtonName());
		}
		else
		{
			closeButtonName=ProvClientUtils.getString("Close");
		}
		if((templateArg.getFinishButtonName()!=null)&&(!(templateArg.getFinishButtonName().equals(""))))
		{
			aplorfinish=ProvClientUtils.getString(templateArg.getFinishButtonName());
		}
		else
		{
			aplorfinish=ProvClientUtils.getString("Apply"); 
		}
		if(templateArg.getHelpUrl()!=null)
		{
			helpUrl = templateArg.getHelpUrl(); 
		}
		else
		{
			helpUrl="";
		}

		if(titleBorderString.equals(""))
		{
			setBorder(new TitledBorder(new LineBorder(Color.gray,1), MessageFormat.format(ProvClientUtils.getString("Template : {0}"), new Object[]{title}),0,2));
		}
		else
		{
			setBorder(new TitledBorder(new LineBorder(Color.gray,1), MessageFormat.format(ProvClientUtils.getString("Template : {0}"), new Object[]{titleBorderString}),0,2));
		}

		// Checking if the Forms present in the Template should be displayed in a Tree,
		// and creating a tree if needed.
		treeNeeded = template.isTreeNeeded();
		String stylesheet = template.getAttribute("stylesheet");
		if(!(stylesheet == null) && !(stylesheet.trim().equals("")))
		{
			useStyleSheet = true;
			return;
		}
		useStyleSheet = false;
		if(treeNeeded)
		{
			treePanel.removeAll();
			rightPanel= new JPanel(new CardLayout());
			formsNode = new DefaultMutableTreeNode(ProvClientUtils.getString("Forms"));
			tree = new JTree(formsNode);
			tree.setShowsRootHandles(true);
			tree.putClientProperty("JTree.lineStyle", "Angled");
			treePanel.add(new JScrollPane(tree));
			tree.setRowHeight(20);
			nodesAndPanelsTable.clear();
			tree.addTreeSelectionListener(this);
		}
		setForms(formsVec);
	}	

	/** Set the forms to be rendered in the panel. **/
	public void setForms(Vector formsVecArg)
	{
		int index = 0;
		try
		{
			uiHandler.clear();
			if(jtp != null)
			{
				jtp.removeAll();
			}
			for(Enumeration enumerate = formsVecArg.elements();enumerate.hasMoreElements();)
			{
				Form f = ((Form)enumerate.nextElement());
				String script = f.getAttribute("script");
				JPanel jp = null;
				jp = uiHandler.getPanelFor(f);
				String description = ProvClientUtils.getString(f.getDescription());
				String title = ProvClientUtils.getString(f.getTitle());
				if(title.trim().equals(""))
				{
					title = "Form " + index;
				}
				if(!treeNeeded)
				{
					addToTabbedPane(jp , description , title);
				}
				else
				{
					addToTree(jp , description , title);
				}
				index++;
			}
			if(!treeNeeded)
			{
				jtp.addChangeListener(this);
			}
		}
		catch(Exception exc)
		{
			String message = MessageFormat.format(ProvClientUtils.getString("Error occured while adding Forms: {0}"), new Object[]{exc.getMessage()});
			statusTextArea.setText(message);
			System.err.println(message);
            template = null;
		}
	}

	private void addToTabbedPane(JPanel panel,String description,String title)
	{
		panel.setName(description);
		jtp.addTab(title,null,new JScrollPane(panel),null);
	}

	private void addToTree(JPanel panel,String description,String title)
	{
		panel.setName(description);
		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(title);
		formsNode.add(newNode);
		nodesAndPanelsTable.put(newNode,panel);
		rightPanel.add(new JScrollPane(panel),title);
	}

	public void incrementNumberOfRequiredFields()
	{
		numberOfRequiredFields++;
	}
	public void setScheduled(boolean b)
	{
		scheduled=b;
	}
	public boolean setFocus(Container container)
	{
		Component c[]=container.getComponents();
		for(int i=0;i<c.length;i++)
		{
			if ((c[i] instanceof Container)&&(((Container)c[i]).getComponents().length>0))
			{
				if (setFocus((Container)c[i]))
				{
					return true;
				}
			}
			else
			{
				if ((c[i].isEnabled())&&(c[i].isVisible())&&(c[i].isFocusTraversable()))
				{
					if ((!(c[i] instanceof JTextComponent))||(((JTextComponent)c[i]).isEditable()))
					{
						c[i].requestFocus();
						return true;
					}
				}
			}
		}
		return false;
	}
	private void nextButtonAction()
	{
		switch(whichPanelShowing)
		{
			case LOGIN_PARAMETERS_PANEL_SHOWING:
				try
				{
					if (loginPanel.areInputValuesValid())
					{
						Properties loginProps=loginPanel.getProperties();
						initializeResourceBundle(loginProps);
						debugCheckbox.setText(ProvClientUtils.getString("Show Debug Information"));
						mandatoryLabel.setText(ProvClientUtils.getString("(*) indicates a mandatory field"));
						fetchButton.setText(ProvClientUtils.getString("Fetch Result"));
						apiHandler=new ProvisioningApiHandler(loginProps);
						manager = new StyleSheetManager(uiHandler,loginProps,descriptionTextArea);
						if(southPanelDimension!=null)
						{
							SouthPanel.setPreferredSize(southPanelDimension);
						}
						showZerothPanel();
					}
					else
					{
						showMessageInDialog(ProvClientUtils.getString("Values cannot be null"),ProvClientUtils.getString("Provisioning"),JOptionPane.INFORMATION_MESSAGE);
					}
				}
				catch(Exception exc)
				{
					if((exc.getMessage()!=null)&&(!(exc.getMessage().trim().equals(""))))
					{
						if (exc.getMessage().trim().length()>50)
						{
							test.provisioning.MessageWindow.showMessage(NmsClientUtil.getParentFrame(),ProvClientUtils.getString("Provisioning"),exc.getMessage());
						}
						else
						{
							JOptionPane.showMessageDialog(this,exc.getMessage(),ProvClientUtils.getString("Provisioning"),JOptionPane.ERROR_MESSAGE);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(this, MessageFormat.format(ProvClientUtils.getString("Error occured while stoping the activity with id {0}"), new Object[]{template.getAttribute("operationId")}),ProvClientUtils.getString("Provisioning"),JOptionPane.INFORMATION_MESSAGE);
					}
				}
				break;
			case RADIOBUTTON_PANEL_SHOWING:
				Properties initialProps = getInitialFormProperties(bg.getSelection().getActionCommand());
				if (initialProps.size()==0)
				{ 
					uiHandler.getPanelForProperties(initialProps);
					whichPanelShowing = TEMPLATE_PARAMS_PANEL_SHOWING;
					templateParametersPanelMadeVisible=true;
					nextButton.doClick();
				}
				else
				{
					constructInitialFormPanel(bg.getSelection().getActionCommand() , initialProps );
				}  
				break;
			case TEMPLATE_PARAMS_PANEL_SHOWING:

				Properties props1 = uiHandler.getPropertiesValue();
				boolean canBeSubmitted = true;
				if (props1.size()>0)
				{
					for(Enumeration enumerate = props1.propertyNames();enumerate.hasMoreElements();)
					{
						if(props1.getProperty((String)(enumerate.nextElement())).equals(""))
						{
							canBeSubmitted = false;
						}
					}
				}
				if (canBeSubmitted)
				{
					jtp.removeChangeListener(TemplateUIPanel.this);
					jtp.removeAll();
					FormsPanel.removeAll();
					try
					{
						numberOfRequiredFields = 0;
						statusTextArea.setText(ProvClientUtils.getString("Template loaded successfully"));
						loadTemplateParams(templateName,props1);
						//appletApplicationCommon();
					}
					catch(Exception eee){eee.printStackTrace();}  
				}
				else
				{
					showMessageInDialog(ProvClientUtils.getString("Values cannot be null"),ProvClientUtils.getString("Provisioning"),JOptionPane.INFORMATION_MESSAGE);
				}
				break;

			case FORMS_PANEL_SHOWING:
				if (uiHandler.areInputValuesValid())
				{
                    backButton.setVisible(false);
                    nextButton.setVisible(false);
                    Worker w=new Worker(TemplateUIPanel.this);
					workerThread = new Thread(w);
                    workerThread.start(); 
				}
				/* If forms are displayed as tree, then the tree node corresponding to the panel
				   that contains the incomplete form should be selected. */
				else
				{
					if(treeNeeded)
					{
						JPanel incompletePanel = uiHandler.getIncompletePanel();
						Enumeration e = nodesAndPanelsTable.keys();
						while(e.hasMoreElements())
						{
							DefaultMutableTreeNode nodeToBeSelected = (DefaultMutableTreeNode)e.nextElement();
							JPanel jp = (JPanel) nodesAndPanelsTable.get(nodeToBeSelected);
							if(incompletePanel == jp)
							{
								TreeNode[] nodeArray = nodeToBeSelected.getFirstLeaf().getPath();
								if(nodeArray != null)
								{
									TreePath path = new TreePath(nodeArray);
									tree.setSelectionPath(path);
								}
								break;
							}
						}
					}
					String errorMessage = uiHandler.getErrorMessage();
					showMessageInDialog(errorMessage,ProvClientUtils.getString("Provisioning"),JOptionPane.INFORMATION_MESSAGE);
				}
				break;
			case RESULT_PANEL_SHOWING:
				if (ProvisioningResult1 != null)
				{
					ProvisioningResult1.tableRowSelectedEvent();
				}
				break;
		}
	}	

	//<Begin__class_NextButtonListener>

	class NextButtonListener implements java.awt.event.ActionListener, java.io.Serializable 
	{

		//<TOP_PART>  - Please do not remove this comment or write any custom code above this

		//Listener Interface Methods Are Added Below 


		public void actionPerformed( java.awt.event.ActionEvent arg0)
		{
			nextButtonAction();
		}
	}
	//<End__class_NextButtonListener>
	//<Begin__class_BackButtonListener>

	class BackButtonListener implements java.awt.event.ActionListener, java.io.Serializable 
	{

		//<TOP_PART>  - Please do not remove this comment or write any custom code above this

		//Listener Interface Methods Are Added Below 


		public void actionPerformed( java.awt.event.ActionEvent arg0)
		{
			backButtonAction();
		}
	}
	//<End__class_BackButtonListener>

	private void backButtonAction()
	{
		switch(whichPanelShowing)
		{
			case RADIOBUTTON_PANEL_SHOWING:
				break;
			case TEMPLATE_PARAMS_PANEL_SHOWING:
				int selectedIndex = 0; 
				while ((selectedIndex<radioButtons.length)&&(!(radioButtons[selectedIndex].isSelected()))) selectedIndex++;
				String selectedString =bg.getSelection().getActionCommand();
				showZerothPanel();
				if (bg.getButtonCount()>0)
				{
					if ((bg.getButtonCount()>selectedIndex)&&(radioButtons[selectedIndex].getActionCommand().equals(selectedString)))
					{
						radioButtons[selectedIndex].setSelected(true);
						radioButtons[selectedIndex].requestFocus();
					}
				}
				break;

			case FORMS_PANEL_SHOWING:
				if (getInitialFormProperties(templateName).size()==0)
				{
					whichPanelShowing = TEMPLATE_PARAMS_PANEL_SHOWING;
					disposeDescriptionDialog();
					backButton.doClick();
				}
				else
				{
					statusTextArea.setText(ProvClientUtils.getString("Template parameters loaded successfully"));
					setBorder(new TitledBorder(new LineBorder(Color.gray,1), MessageFormat.format(ProvClientUtils.getString("Template : {0}"), new Object[]{templateName}),0,2));
					debugCheckbox.setVisible(false);
					mandatoryLabel.setVisible(false);
					descriptionButton.setVisible(false);
					if(dialog != null)
					{
						if(dialog.isVisible())  dialog.dispose();
					}
					if (radioButtonPanelMadeVisible)
					{
						backButton.setVisible(true);
					}
					else
					{
						backButton.setVisible(false);
					}
					nextButton.setVisible(true);
					backButton.setText(ProvClientUtils.getString("Back"));
					closeButton.setText(ProvClientUtils.getString("Close"));
					nextButton.setText(ProvClientUtils.getString("Next"));
					helpButton.setVisible(false);
					whichPanelShowing = TEMPLATE_PARAMS_PANEL_SHOWING;    
					((CardLayout)CenterPanel.getLayout()).show(CenterPanel,"TemplateParamsPanel");
					setFocus(TemplateParamsPanel);
				} 
				break;

			case RESULT_PANEL_SHOWING:
				showZerothPanel();
				break;
		}


	}
	//<Begin__class_FetchButtonListener>

	class FetchButtonListener implements java.awt.event.ActionListener, java.io.Serializable 
	{

		//<TOP_PART> - Please do not remove this comment or write any custom code above this

		//Listener Interface Methods Are Added Below 


		public void actionPerformed( java.awt.event.ActionEvent arg0)
		{
			try
			{
				String id=fetchButton.getActionCommand();
				String status=apiHandler.getStatus(id);
				OperationStatus opStat=new OperationStatus(status);
				status=opStat.getAttribute("status"); 
				if (status.equals(ProvisioningConstants.NOTSTARTED))
				{
					JOptionPane.showMessageDialog(TemplateUIPanel.this, ProvClientUtils.getString("Provisioning activity is scheduled.Try again after scheduled time"),ProvClientUtils.getString("Provisioning"),JOptionPane.INFORMATION_MESSAGE);
				}
				else if (status.equals(ProvisioningConstants.INPROGRESS))
				{
					JOptionPane.showMessageDialog(TemplateUIPanel.this,ProvClientUtils.getString( "Provisioning activity is in progress.Try after sometime"),ProvClientUtils.getString("Provisioning"), JOptionPane.INFORMATION_MESSAGE);
				}
				else if (status.equals(ProvisioningConstants.FINISHED))
				{
					fetchButton.setVisible(false);
					closeButton.setVisible(true);
					nextButton.setVisible(true);    
					showResultPanel(status,apiHandler.getResultStatus(id));
				}
				else if (status.equals(ProvisioningConstants.FINISHED_RESCHEDULED))
				{
					fetchButton.setVisible(true);
					closeButton.setVisible(false);
					nextButton.setVisible(false);    
					showResultPanel(status,apiHandler.getResultStatus(id));
				}
			}
			catch(Exception ex)
			{
				statusTextArea.setText(ex.getMessage());
				System.err.println(MessageFormat.format(ProvClientUtils.getString("Error in getting provisioning result: {0}"), new Object[]{ex.getMessage()}));
            }
		}
	}
	//<End__class_FetchButtonListener>


	//<Begin__class_DescriptionButtonListener>

	class DescriptionButtonListener implements java.awt.event.ActionListener, java.io.Serializable 
	{

		//<TOP_PART> - Please do not remove this comment or write any custom code above this

		//Listener Interface Methods Are Added Below 


		public void actionPerformed( java.awt.event.ActionEvent arg0)
		{
			showDescriptionDialog();
		}
	}
	//<End__class_DescriptionButtonListener>

	private void initializeResourceBundle(Properties prop)
	{
		if(applet != null)
		{
			ProvClientUtils.englishToNative = new AdventNetResourceBundle(applet);
		}
		else
		{
			String coun = prop.getProperty("COUNTRY");
			String lan = prop.getProperty("LANGUAGE");
			initializeEnglishToNative(lan, coun);
		}
		CommonUtilCarrier.setInstance(new NmsCommonUtilClientImpl());
	}
	private void initializeEnglishToNative(String lang,String country)
	{
		FileInputStream fis =null;
		try
		{
			StringBuffer fileBuf=new StringBuffer("html/EnglishToNative");
			if((lang!=null)&&(country!=null))
			{
				fileBuf.append("_");
				fileBuf.append(lang);
				fileBuf.append("_");
				fileBuf.append(country);
			}
			fileBuf.append(".properties");
			if(!(new File(fileBuf.toString()).exists()))
			{
				fileBuf = new StringBuffer("html/EnglishToNative.properties");
			}
			fis = new FileInputStream(fileBuf.toString());
			ProvClientUtils.englishToNative=new AdventNetResourceBundle(fis);
		}
		catch(Exception e)
		{
			String errorMessage = e.getMessage() != null ? e.getMessage() : e.toString();
			System.err.println("Cannot instantiate resource bundle : "+errorMessage);
		}
		finally
		{
			if(fis !=null)
			{
				try
				{
					fis.close();
				}
				catch(Exception e)
				{
					System.err.println("Cannot close internationalization property file");
				}
			}
		}
	}


}

