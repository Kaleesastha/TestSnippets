//$Id: ExampleFrame.java,v 1.2 2007/02/22 15:02:55 srajeswari Exp $
package com.adventnet.nms.example.befe;

// NMS imports
import com.adventnet.nms.severity.*;
import com.adventnet.nms.startclient.NmsFrame;
import com.adventnet.nms.mapui.DefaultMapModel;
import com.adventnet.nms.mapui.MapClientAPI;
import com.adventnet.nms.mapui.MapSymbolComponent;
import com.adventnet.nms.util.*;
//import com.adventnet.nms.befeexample.*;

// java imports
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.border.*;

/* This class shows a JFrame which contains the Number of Events and 
Alerts for the selected ManagedObject.*/

public class ExampleFrame implements NmsFrame,ActionListener,ItemListener
{
	ExampleClient ec;
	JLabel lblName;
	JComboBox mObject;
	JLabel lblEvents;
	JTextField events;
	JLabel lblAlerts;
	JTextField alerts;
	JFrame frame;
	
	JLabel lblSeverity;
	JLabel lblSalerts;
	JLabel lblSevents;
	
	GridBagLayout gbl;
	GridBagConstraints gbc;
	Container cp;
	JPanel panel;
	JButton close;
	
	SeverityInfo severityInfo;
	Vector severityNames;
	JLabel sNames[];
	JTextField eValues[];
	JTextField aValues[];
	Hashtable Severitytable=new Hashtable(10);
	String objName = null;
	boolean standAlone = false;
	
	public void init(JApplet app)
	{
		// Getting the instance of severityInfo
		
		standAlone = false;
		try
		{
		severityInfo=getSeverityInfo(app.getDocumentBase().getHost());
		objName = (String) app.getParameter("NODE");//No Internationalisation
		initGUI();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
			frame = null;
		}
	}
	public void passTheInputs(Properties prop)
	{
		// Getting the instance of severityInfo
		try
		{
		severityInfo=getSeverityInfo(prop.getProperty("host"));
		objName = (String) prop.getProperty("NODE");//No Internationalisation
		initGUI();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	private void initGUI() throws Exception
	{
		ExampleFrame ef = new ExampleFrame();
		
		// Getting the Severity Names in to a vector
		severityNames = severityInfo.getNames();
		
		// Adding the SeverityNames in to a Vector, removing "Unknown" severity//No Internationalisation
		for (int i=0;i<severityNames.size();++i)
			if(((String)severityNames.elementAt(i)).equalsIgnoreCase("unknown") )//No Internationalisation
				severityNames.remove(i);
				
		// Creating controls objName is the name of the object
		init(severityNames.size(), objName);
		// To Layout the controls
		setLayout();
		frame.setTitle(NmsClientUtil.GetString("Events and Alerts For ")+ objName);//No Internationalisation
		// To get the instance of the Client
		ec=ExampleClient.getInstance();
		if (mObject.getItemAt(0) != null)
		{	
			String node = (String) mObject.getItemAt(0); 
			ec.registerForGettingUpdates(this,node);
		}
		else
		{
			throw new NullPointerException("No Object Selected");
		}
		

	}
	/* This method will create the necessary controls.
	The argument "num" will have the number of severities configured in Severity.conf file. */
	private void init(int num, String objName)
	{
		frame=new JFrame();
		cp = frame.getContentPane();
		
		lblName = new JLabel(NmsClientUtil.GetString("Selected Object is"));

		Vector objNames = new Vector () ;
		 DefaultMapModel mapModel; 
	 	
         
		 if( objName.indexOf("netmap")> 0 )//No Internationalisation
		 {
			 MapClientAPI mapClient=MapClientAPI.getInstance();
			 mapModel = mapClient.getMapModel(objName);
			 Vector allSymbols = mapModel.getSymbols();
			 if(allSymbols !=null)
			 {
				 for(int i=0;i<allSymbols.size();i++) 
				 {
					 objNames.addElement(((MapSymbolComponent)allSymbols.elementAt(i)).getObjName()); 
				 }
			 }
		 }
		 else
		 {
			 objNames.addElement(objName);
			 //mObject.setEnabled(false);
		 }
		
		mObject = new JComboBox(objNames );
		mObject.addItemListener(this);
		
		mObject.setBorder(new BevelBorder(BevelBorder.RAISED));
		mObject.setEditable(false);
		
		lblEvents=new JLabel(NmsClientUtil.GetString(" Total Number of  Events  "));
		
		events=new JTextField(6);
		events.setEditable(false);
		events.setBorder(new EmptyBorder(0,0,0,0));
		
		lblAlerts=new JLabel(NmsClientUtil.GetString(" Total Number of  Alerts  "));
		
		alerts=new JTextField(6);
		alerts.setEditable(false);
		alerts.setBorder(new EmptyBorder(0,0,0,0));
		
		lblSeverity = new JLabel(NmsClientUtil.GetString("Severity"));
		lblSevents = new JLabel(NmsClientUtil.GetString("Events"));
		lblSalerts=new JLabel(NmsClientUtil.GetString("Alerts"));
		
		sNames = new JLabel[num];
		eValues =new JTextField[num];
		aValues =new JTextField[num];
		for ( int i=0;i< sNames.length;++i)
		{
                    String tempSeverityNames = (String) severityNames.elementAt(i);
                    sNames[i]=new JLabel(NmsClientUtil.GetString(tempSeverityNames));
                    
			eValues[i] = new JTextField(6);
			eValues[i].setEditable(false);
			eValues[i].setText("0");//No Internationalisation
			aValues[i] = new JTextField(6);
			aValues[i].setEditable(false);
			aValues[i].setText("0");//No Internationalisation
                        Severitytable.put(tempSeverityNames,new Integer(i));
		}
		
		panel = new JPanel();
		close = new JButton(NmsClientUtil.GetString("Close"));
		close.addActionListener(this);
		panel.add(close);
		
		gbl=new GridBagLayout();
		gbc = new GridBagConstraints();
		cp.setLayout(gbl);
		
		
		frame.setSize(500,230);
		
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
		frame.addWindowListener(new WindowAdapter()
			{
			public void windowClosing(WindowEvent we)
			{
				if ( we.getSource().equals(frame) )
				{
					ec.removeNameFromServer(ExampleFrame.this);
					frame.dispose();
					if (standAlone)
						System.exit(0);
				}
			}});
		
	}
	
	public void itemStateChanged(ItemEvent me)
	{
		
		String node =(String) mObject.getSelectedItem();
		ec.removeNameFromServer(ExampleFrame.this);
   		ec.registerForGettingUpdates(this,node);

	}
	public void mouseExited(MouseEvent me)
	{
	}
	public void mouseEntered(MouseEvent me)
	{
	}
	public void mouseReleased(MouseEvent me)
	{
	}
	public void mousePressed(MouseEvent me)
	{
	}

	public void actionPerformed(ActionEvent ae)
	{
		if ( ae.getSource().equals(close) )
		{
			ec.removeNameFromServer(ExampleFrame.this);
			frame.dispose();
			if (standAlone)
				System.exit(0);
		}
		
	}
	/* This Method sets the corresponding colors for JLabels and JTextFields
	according to their severity. */
	private void setColors()
	{
		
		for ( int i=0;i < severityNames.size();++i)
		{
			String name=(String)severityNames.elementAt(i);
			Enumeration enumerate = Severitytable.keys();
				while ( enumerate.hasMoreElements() )
				{
                                    String severityName = (String) enumerate.nextElement();
					if ( name.trim().equals(severityName) )
					{
						int index = ((Integer)Severitytable.get(severityName)).intValue();
						eValues[index].setOpaque(true);
						aValues[index].setOpaque(true);
						eValues[index].setBackground(severityInfo.getColor(name.trim()) );
						aValues[index].setBackground(severityInfo.getColor(name.trim()) );
					}
				}
				
		}
	}
	// For layout of controls
	private void setLayout()
	{
		gbc.insets =new Insets(10,0,0,0);
		
		gbc.fill=GridBagConstraints.BOTH;
		gbc.weightx=0.0;
		gbc.gridwidth=GridBagConstraints.REMAINDER;
		gbl.setConstraints(lblName,gbc);
		cp.add(lblName);
		
		gbc.insets=new Insets(0,0,0,0);
		gbc.fill=GridBagConstraints.BOTH;
		gbc.weightx=1.0;
		gbc.gridwidth=GridBagConstraints.REMAINDER;
		gbl.setConstraints(mObject,gbc);
		cp.add(mObject);
		
		
		gbc.fill=GridBagConstraints.BOTH;
		gbc.weightx=1.0;
		gbc.gridwidth=2;
		gbl.setConstraints(lblEvents,gbc);
		cp.add(lblEvents);
		
		
		gbc.fill=GridBagConstraints.BOTH;
		gbc.weightx=0.0;
		gbc.gridwidth=1;
		gbl.setConstraints(events,gbc);
		cp.add(events);
		
		
		gbc.fill=GridBagConstraints.BOTH;
		gbc.weightx=1.0;
		gbc.gridwidth=GridBagConstraints.RELATIVE;
		gbl.setConstraints(lblAlerts,gbc);
		cp.add(lblAlerts);
		
		gbc.fill=GridBagConstraints.BOTH;
		gbc.weightx=0.0;
		
		gbc.gridwidth=GridBagConstraints.REMAINDER;
		gbl.setConstraints(alerts,gbc);
		cp.add(alerts);
		
		gbc.insets= new Insets(10,0,0,0);
		gbc.weightx=1.0;
		gbc.gridwidth=1;
		gbl.setConstraints(lblSeverity,gbc);
		cp.add(lblSeverity);
		for ( int i=0;i<sNames.length;++i)
		{
			gbc.gridwidth=1;
			if ( i == sNames.length-1)
			{
				gbc.gridwidth=GridBagConstraints.REMAINDER;
			}
			if ( i == sNames.length-2)
			{
				
				gbc.gridwidth=GridBagConstraints.RELATIVE;
			}
			
			gbl.setConstraints(sNames[i],gbc);
			cp.add(sNames[i]);
		}
		gbc.insets= new Insets(0,0,0,0);
		gbc.gridwidth=1;
		gbl.setConstraints(lblSevents,gbc);
		cp.add(lblSevents);
		for ( int i=0;i<eValues.length;++i)
		{
			gbc.gridwidth=1;
			if ( i == eValues.length-1)
			{
				gbc.gridwidth=GridBagConstraints.REMAINDER;
			}
			if ( i == eValues.length-2)
			{
				gbc.gridwidth=GridBagConstraints.RELATIVE;
			}
			
			gbl.setConstraints(eValues[i],gbc);
			cp.add(eValues[i]);
		}
		
		gbc.gridwidth=1;
		gbl.setConstraints(lblSalerts,gbc);
		cp.add(lblSalerts);
		for ( int i=0;i<aValues.length;++i)
		{
			gbc.gridwidth=1;
			if ( i == aValues.length-1)
				gbc.gridwidth=GridBagConstraints.REMAINDER;
			if ( i == aValues.length-2)
				gbc.gridwidth=GridBagConstraints.RELATIVE;
			
			gbl.setConstraints(aValues[i],gbc);
			cp.add(aValues[i]);
		}
		
		gbc.fill=GridBagConstraints.BOTH;
		gbc.weightx=1.0;
		gbc.weighty=1.0;
		gbc.gridwidth=GridBagConstraints.REMAINDER;
		gbl.setConstraints(panel,gbc);
		cp.add(panel);
		//frame.setVisible(true);
		setColors();
	}
	// To get the instance of SeverityInfo	
	private SeverityInfo getSeverityInfo(String name)
	{
		return  SeverityInfo.getInstanceFromHost(name);
	}
	// Method in NmsFrame Interface.
	public void setVisible(boolean visible)
	{
		if(frame != null)
		{
			frame.setVisible(visible);
            if(visible)
            {
                frame.setIconImage(NmsClientUtil.getFrameIcon());   
            }
		}
		
	}
	
	/* This method displays the number of events and alerts for the
	selected Object */
	public void showEventsAndAlerts(Hashtable eventsHash, Hashtable alertsHash)
	{
		int total =0;
		Enumeration enumerate = eventsHash.keys();
		while( enumerate.hasMoreElements() )
		{
			String sname =(String)enumerate.nextElement();
			int value =((Integer)eventsHash.get(sname)).intValue();
			total += value;
			events.setText(String.valueOf(total));			
			Enumeration labels = Severitytable.keys();
			
			while( labels.hasMoreElements() )
			{
                            String severityName = (String) labels.nextElement();
				if ( severityName.equals(sname) )
				{
					int index =((Integer)(Severitytable.get(severityName))).intValue();
					eValues[index].setText(String.valueOf(value));
				}
			}
			
			
		}
		
		Enumeration en=alertsHash.keys();
        total = 0;
		while( en.hasMoreElements() )
		{
			String sname =(String)en.nextElement();
			int value =((Integer)alertsHash.get(sname)).intValue();
			total += value;
			alerts.setText(String.valueOf(total));			
			Enumeration labels = Severitytable.keys();
			
			while( labels.hasMoreElements() )
			{
                            String severityName = (String) labels.nextElement();
				if ( severityName.equals(sname) )
				{
					int index =((Integer)(Severitytable.get(severityName))).intValue();
					aValues[index].setText(String.valueOf(value));
				}
			}
		}
	}

	/* This method will be called for showing the Updated Event*/
	public void showEvents(String severity,int eventsCount)
	{
		Enumeration enumerate = Severitytable.keys();
		while( enumerate.hasMoreElements() )
		{
                    String severityName = (String) enumerate.nextElement();
			if ( severityName.equals(severity) )
			{
				int index = ((Integer)Severitytable.get(severityName)).intValue();
				if ( eValues[index].getText().equals("") )//No Internationalisation
				{
					eValues[index].setText(String.valueOf(eventsCount) );
				}
				else
				{
					int prev = (Integer.parseInt(eValues[index].getText()));
					eValues[index].setText(String.valueOf( prev+eventsCount) );
				}
			}
			
		}
		
		String str=events.getText().trim();
		int i=0;
		if ( str == "" )//No Internationalisation
		{
			str="0";//No Internationalisation
		}
		else
		{
			
			try
			{
				i=Integer.parseInt(str);
				
				
			}
			catch(Exception e)
			{
				i=0;
			}
		}
		i += eventsCount;
		events.setText(String.valueOf(i));
	}

	/* This method will be called for showing the Updated Alert*/
	public void showAlerts(String severity,int alertsCount)
	{
		Enumeration enumerate = Severitytable.keys();
		while( enumerate.hasMoreElements() )
		{
                    String severityName = (String) enumerate.nextElement();
			if ( severityName.equals(severity) )
			{
				int index = ((Integer)Severitytable.get(severityName)).intValue();
				if ( aValues[index].getText().equals("") )//No Internationalisation
				{
					aValues[index].setText(String.valueOf(alertsCount) );
				}
				else
				{
					int prev = (Integer.parseInt(aValues[index].getText()));
					aValues[index].setText(String.valueOf( prev+alertsCount) );
				}
			}
			
		}
		
		String str=alerts.getText().trim();
		int i=0;
		if ( str == "" )//No Internationalisation
		{
			str="0";//No Internationalisation
		}
		else
		{
			
			try
			{
				i=Integer.parseInt(str);
				
				
			}
			catch(Exception e)
			{
				i=0;
			}
		}
		i += alertsCount;
		alerts.setText(String.valueOf(i));
	}
}

