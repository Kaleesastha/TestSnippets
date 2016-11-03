//$Id: 
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory






package com.adventnet.nms.pollui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import com.adventnet.nms.util.NmsClientUtil;
import java.util.Vector;
import java.util.Date;
import java.util.Calendar;
import java.util.TimeZone;
import com.adventnet.nms.util.DateTimeComponent;
import javax.swing.table.*;

public class SnmpCollectedTable extends JFrame 
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel mainPanel = null;
	javax.swing.JPanel graphPanel = null;
	javax.swing.JLabel instLabel = null;
	javax.swing.JComboBox instCombo = null;
	javax.swing.JPanel timePanel = null;
	javax.swing.JLabel fromLabel = null;
	javax.swing.JLabel toLabel = null;
	javax.swing.JButton plotButton = null;
	javax.swing.JPanel buttonPanel = null;
	javax.swing.JButton helpButton = null;
	javax.swing.JButton closeButton = null;
	private JScrollPane tablePane = null;
	private JTable table = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>
	private String title= null;
	private boolean isMultiple = false;
	private StatisticsForTable handle =null;
	private long initialTime =0;
	private String instanceString ="-1"; 
	private CardLayout card= null;
	private  Vector instances = new Vector(); 
	private boolean initialize=false;
	private DateTimeComponent fromTime = null;
	private DateTimeComponent toTime = null;
	private String format="yyyy.MM.dd hh:mm:ss a ";
	private Vector columns= null;
	private Vector data= null;
	private Vector val =null;

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

	public SnmpCollectedTable(String title, boolean isMultiple, StatisticsForTable handle,long time1,long time2) 
	{

		this.title = title;
		this.isMultiple = isMultiple;
		this.handle = handle;
		init();
		fromLabel.setText(NmsClientUtil.GetString("From"));
		toLabel.setText(NmsClientUtil.GetString("To"));

		columns=new Vector();
		data=new Vector();
		columns.add("Time of Collection");
		columns.add("Value Collected");  
		setResizable(true);

		addTimeComp();
		Image img;
		if((img=NmsClientUtil.getFrameIcon())!= null)
			this.setIconImage(img);  
		fromTime.setTheFont(NmsClientUtil.getFont());
		fromTime.setDateTime(NmsClientUtil.formatDate(time1,"MMM dd,yyyy hh:mm:ss a"));

		toTime.setTheFont(NmsClientUtil.getFont());
		toTime.setDateTime(NmsClientUtil.formatDate(time2,"MMM dd,yyyy hh:mm:ss a"));

		if(isMultiple)
		{
			try
			{
				instances = handle.getInstances();
			}
			catch(Exception ex)
			{
			}	       
			if(instances!=null && instances.size() != 0)
			{
				for (int i =0;i<instances.size();i++)
					instCombo.addItem(instances.elementAt(i));
			}
			else
			{
				//NmsClientUtil.err(NmsClientUtil.GetString("No data collected for the selected Statistic"));
			}
		}
		else 
		{
			timePanel.remove(instLabel); 
			timePanel.remove(instCombo);          
		}    
		//this.setSize(800,500);

	}

	private void addTimeComp()
	{
		fromTime = new DateTimeComponent();
		toTime = new DateTimeComponent();       
		inset = new Insets(0,0,0,0);
		setConstraints(2,1,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
		timePanel.add(toTime,cons);
		inset = new Insets(5,0,5,0);
		setConstraints(2,0,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
		timePanel.add(fromTime,cons);
	}    

	public synchronized void showTable(long[] times, Object[]  vals)
	{
		Vector oldData=data;
		oldData=null;
		data = new Vector();
		for (int i=0; i <times.length; i++) 
		{
			val= new Vector();
			val.addElement(NmsClientUtil.formatDate(times[i]));
			val.addElement(vals[i].toString());
			data.add(val);
		}
		DefaultTableModel model= new DefaultTableModel(data,columns);
		table.setModel(model);
		NmsClientUtil.normalCursor(this);
	}

	public void setUIVisible()
	{
		NmsClientUtil.centerWindow(this);
		setVisible(true);
	}

	public void setHeaderText(String title)
	{
		try
		{
			if(title == null || title.length() == 0)
				setHeaderText(title);
		}
		catch(Exception exception)
		{
			System.out.println(exception.getMessage());
		}
	}

	private void getData()
	{
		if(isMultiple)
		{ 
			if(instCombo.getSelectedItem()!=null)
			{	       
				instanceString = ((String)instCombo.getSelectedItem()).trim();
			}
			else
			{
				int re = JOptionPane.showConfirmDialog(NmsClientUtil.getParentFrame(), NmsClientUtil.GetString("Enter a valid instance and try again "), NmsClientUtil.GetString("Confirm"),JOptionPane.ERROR_MESSAGE);	
				return;
			}		       
		}   
		NmsClientUtil.busyCursor(this);
		long startTime=0;
		long endTime = 0;
		try
		{
			java.util.Date date1 = NmsClientUtil.parseDate (fromTime.getDateTime());
			startTime= date1.getTime();
			java.util.Date date2 = NmsClientUtil.parseDate (toTime.getDateTime());
			endTime = date2.getTime();
		}
		catch(Exception e)
		{
			NmsClientUtil.err(NmsClientUtil.GetString("Select an date for which you like  to collect Polled Data."));
			NmsClientUtil.normalCursor(this);
			return;
		}
		handle.fetchData(instanceString,startTime,endTime);
		NmsClientUtil.normalCursor(this);
		//Thread th = new Thread(this,"Snmp Collected Statistics Event Handler");
		//th.start();  
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
	public void init()
	{
		//<Begin_init> 
		if (initialized == true) return; 
		this.setSize(700,450);
		setTitle("Collected Statistics");
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
			showStatus("Error in init method",ex); 
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
		}
		return value;


		//<End_getParameter_String>
	} 
	public void setUpProperties()
	{

		try
		{
			instLabel.setHorizontalAlignment(2);
			instLabel.setFont(new Font("Dialog",0,12));
			instLabel.setForeground(new Color(-16777216));
			instLabel.setHorizontalTextPosition(4);
			instLabel.setText("Instance");
		}
		catch(Exception ex)
		{
			showStatus("Exception while setting properties for bean "+instLabel,ex); 
		}

		//<UserCode_Begin_Bean_instLabel>

		//<UserCode_End_Bean_instLabel>

		try
		{
			instCombo.setFont(new Font("Dialog",0,12));
			instCombo.setEditable(true);
		}
		catch(Exception ex)
		{
			showStatus("Exception while setting properties for bean "+instCombo,ex); 
		}

		//<UserCode_Begin_Bean_instCombo>

		//<UserCode_End_Bean_instCombo>

		try
		{
			timePanel.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(0),"Date Range Setting",0,0,new Font("Dialog",0,12),new Color(-16777216)));
		}
		catch(Exception ex)
		{
			showStatus("Exception while setting properties for bean "+timePanel,ex); 
		}

		//<UserCode_Begin_Bean_timePanel>

		//<UserCode_End_Bean_timePanel>

		try
		{
			fromLabel.setHorizontalAlignment(2);
			fromLabel.setFont(new Font("Dialog",0,12));
			fromLabel.setForeground(new Color(-16777216));
			fromLabel.setHorizontalTextPosition(4);
			fromLabel.setText("From");
		}
		catch(Exception ex)
		{
			showStatus("Exception while setting properties for bean "+fromLabel,ex); 
		}

		//<UserCode_Begin_Bean_fromLabel>

		//<UserCode_End_Bean_fromLabel>

		try
		{
			toLabel.setHorizontalAlignment(2);
			toLabel.setFont(new Font("Dialog",0,12));
			toLabel.setForeground(new Color(-16777216));
			toLabel.setHorizontalTextPosition(4);
			toLabel.setText("To");
		}
		catch(Exception ex)
		{
			showStatus("Exception while setting properties for bean "+toLabel,ex); 
		}

		//<UserCode_Begin_Bean_toLabel>

		//<UserCode_End_Bean_toLabel>

		try
		{
			plotButton.setFont(new Font("Dialog",0,12));
			plotButton.setHorizontalTextPosition(4);
			plotButton.setText("Plot");
		}
		catch(Exception ex)
		{
			showStatus("Exception while setting properties for bean "+plotButton,ex); 
		}

		//<UserCode_Begin_Bean_plotButton>

		//<UserCode_End_Bean_plotButton>

		try
		{
			helpButton.setFont(new Font("Dialog",0,12));
			helpButton.setHorizontalTextPosition(4);
			helpButton.setText("Help");
		}
		catch(Exception ex)
		{
			showStatus("Exception while setting properties for bean "+helpButton,ex); 
		}

		//<UserCode_Begin_Bean_helpButton>

		//<UserCode_End_Bean_helpButton>

		try
		{
			closeButton.setFont(new Font("Dialog",0,12));
			closeButton.setHorizontalTextPosition(4);
			closeButton.setText("Close");
			tablePane.setBorder(new javax.swing.border.BevelBorder(1));

		}
		catch(Exception ex)
		{
			showStatus("Exception while setting properties for bean "+closeButton,ex); 
		}

		//<UserCode_Begin_Bean_closeButton>

		//<UserCode_End_Bean_closeButton>


		//<End_setUpProperties>
	} 
	public void initVariables()
	{
		//<Begin_initVariables> 
		Top= new javax.swing.JPanel();
		mainPanel= new javax.swing.JPanel();
		graphPanel= new javax.swing.JPanel();
		instLabel= new javax.swing.JLabel();
		instCombo= new javax.swing.JComboBox();
		timePanel= new javax.swing.JPanel();
		fromLabel= new javax.swing.JLabel();
		toLabel= new javax.swing.JLabel();
		plotButton= new javax.swing.JButton();
		buttonPanel= new javax.swing.JPanel();
		helpButton= new javax.swing.JButton();
		closeButton= new javax.swing.JButton();
		tablePane= new JScrollPane();
		table= new JTable();

		//<End_initVariables>
	} 
	public void setUpGUI(Container container)
	{
		//<Begin_setUpGUI_Container> 
		container.add(Top,BorderLayout.CENTER);
		Top.setLayout(new GridBagLayout());
		inset = new Insets(0,0,0,0);
		setConstraints(0,0,1,1,1.0,1.0,cons.CENTER,cons.BOTH,inset,0,0);
		Top.add(mainPanel,cons);
		mainPanel.setLayout(new BorderLayout(5,5));
		mainPanel.add(graphPanel,BorderLayout.CENTER);
		graphPanel.setLayout(new BorderLayout(5,5));
		inset = new Insets(0,0,0,0);
		setConstraints(0,0,0,1,1.0,1.0,cons.CENTER,cons.BOTH,inset,0,0);
		graphPanel.add(tablePane,BorderLayout.CENTER);
		tablePane.getViewport().add(table);
		inset = new Insets(0,0,0,0);
		setConstraints(0,0,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
		graphPanel.add(timePanel,BorderLayout.SOUTH);
		timePanel.setLayout(new GridBagLayout());
		inset = new Insets(2,5,2,5);
		setConstraints(0,0,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
		timePanel.add(fromLabel,cons);
		inset = new Insets(2,5,2,5);
		setConstraints(0,1,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
		timePanel.add(toLabel,cons);
		inset = new Insets(2,5,2,5);
		setConstraints(3,0,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
		timePanel.add(instLabel,cons);
		inset = new Insets(2,5,2,5);
		setConstraints(4,0,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
		timePanel.add(instCombo,cons);
		mainPanel.add(buttonPanel,BorderLayout.SOUTH);
		buttonPanel.setLayout(new GridBagLayout());
		inset = new Insets(5,10,5,10);
		setConstraints(0,0,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
		buttonPanel.add(plotButton,cons);
		inset = new Insets(5,10,5,10);
		setConstraints(1,0,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
		buttonPanel.add(closeButton,cons);

		//<End_setUpGUI_Container>
	} 
	public void setUpConnections()
	{
		//<Begin_setUpConnections> 

		helpButton_helpButton_conn helpButton_helpButton_conn1 =  new helpButton_helpButton_conn();
		helpButton.addActionListener(helpButton_helpButton_conn1);
		instCombo_instCombo_conn instCombo_instCombo_conn1 =  new instCombo_instCombo_conn();
		instCombo.addItemListener(instCombo_instCombo_conn1);
		plotButton_plotButton_conn plotButton_plotButton_conn1 =  new plotButton_plotButton_conn();
		plotButton.addActionListener(plotButton_plotButton_conn1);
		closeButton_closeButton_conn closeButton_closeButton_conn1 =  new closeButton_closeButton_conn();
		closeButton.addActionListener(closeButton_closeButton_conn1);

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
		//<End_showStatus_String_Exception>
	}





	public SnmpCollectedTable()
	{
		//<Begin_SnmpCollectedGraph>
		pack();

		//<End_SnmpCollectedGraph>
	}

	public SnmpCollectedTable(java.applet.Applet applet)
	{
		//<Begin_SnmpCollectedGraph_java.applet.Applet>
		this.applet = applet;
		pack();
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		//<End_SnmpCollectedGraph_java.applet.Applet>
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




	//<Begin__class_closeButton_closeButton_conn>

	class closeButton_closeButton_conn implements java.awt.event.ActionListener, java.io.Serializable 
	{

		//<TOP_PART>
		//<UserCode_Begin_Connection_closeButton_closeButton_conn>

		//Listener Interface Methods Are Added Below 


		public void actionPerformed( java.awt.event.ActionEvent arg0)
		{
			dispose();
		}
		//<UserCode_End_Connection_closeButton_closeButton_conn>
	}//<End__class_closeButton_closeButton_conn>




	//<Begin__class_helpButton_helpButton_conn>

	class helpButton_helpButton_conn implements java.awt.event.ActionListener, java.io.Serializable 
	{

		//<TOP_PART>
		//<UserCode_Begin_Connection_helpButton_helpButton_conn>

		//Listener Interface Methods Are Added Below 


		public void actionPerformed( java.awt.event.ActionEvent arg0)
		{
			NmsClientUtil.showHelpBasedOnKey("Poll_CollectedStatistics_Details");
		}
		//<UserCode_End_Connection_helpButton_helpButton_conn>
	}//<End__class_helpButton_helpButton_conn>






	//<Begin__class_graphCombo_graphCombo_conn>





	//<Begin__class_instCombo_instCombo_conn>

	class instCombo_instCombo_conn implements java.awt.event.ItemListener, java.io.Serializable 
	{

		//<TOP_PART>
		//<UserCode_Begin_Connection_instCombo_instCombo_conn>

		//Listener Interface Methods Are Added Below 


		public void itemStateChanged( java.awt.event.ItemEvent arg0)
		{
			if(initialize)
			{
				getData();
			}
			else
				initialize=true;
		}
		//<UserCode_End_Connection_instCombo_instCombo_conn>
	}//<End__class_instCombo_instCombo_conn>


	//<Begin__class_plotButton_plotButton_conn>

	class plotButton_plotButton_conn implements java.awt.event.ActionListener, java.io.Serializable 
	{

		//<TOP_PART>
		//<UserCode_Begin_Connection_plotButton_plotButton_conn>

		//Listener Interface Methods Are Added Below 


		public void actionPerformed( java.awt.event.ActionEvent arg0)
		{
			getData();
		}
		//<UserCode_End_Connection_plotButton_plotButton_conn>
	}//<End__class_plotButton_plotButton_conn>
}








































