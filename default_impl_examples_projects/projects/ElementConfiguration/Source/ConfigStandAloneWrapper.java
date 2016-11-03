package com.adventnet.nms.config;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import java.util.*;
import java.net.*;

public class ConfigStandAloneWrapper
{
	ConfigPanel configPanel = null;

	public ConfigStandAloneWrapper(String args[])
	{
		JFrame jf = new JFrame("Configuration Management");
		jf.setSize(700, 300);
		
		JApplet applet = getStandAloneApplet();
		if(applet == null)
		{
			System.out.println("Applet no intialized");
			System.exit(0);
		}
		ConfigPanel cp = new ConfigPanel("com.adventnet.nms.config.StandAloneClientUtils", args[0]);
		cp.isStandAlone = true;
		cp.init(applet);
		configPanel = ConfigPanel.getInstance();
		jf.getContentPane().add(cp);
		
		Test tt = new Test();
		
		JMenuBar jmb = new JMenuBar();
		JMenu testMenu = new JMenu ("Operations");
		JMenuItem item;
		testMenu.add (item = new JMenuItem ("New Task"));
		item.addActionListener (tt);
		testMenu.add (item = new JMenuItem ("Modify Task"));
		item.addActionListener (tt);
		testMenu.add (item = new JMenuItem ("Delete Task"));
		item.addActionListener (tt);
		testMenu.add (item = new JMenuItem ("Save DeviceList"));
		item.addActionListener (tt);
		testMenu.add (item = new JMenuItem ("Save DataSource"));
		item.addActionListener (tt);
		
		//testMenu.add (item = new JMenuItem ("Delete Task"));
		//item.addActionListener (tt);
		//testMenu.add (item = new JMenuItem ("Upload"));
		//item.addActionListener (tt);
		//testMenu.add (item = new JMenuItem ("Execute"));
		//item.addActionListener (tt);
		
		testMenu.add (item = new JMenuItem ("Exit"));
		item.addActionListener (tt);
		
		jmb.add(testMenu);
		jf.setJMenuBar (jmb);

		jf.addWindowListener(new WindowAdapter() 
			{ 
				public void windowClosing(WindowEvent e)
				{ 
					System.exit(0);
				} 
			});

		jf.setVisible(true);
	}


	private JApplet getStandAloneApplet()
	{
		try
		{
			StandAloneApplet saa = new StandAloneApplet();
			saa.setDocumentBase(new URL("file://" + System.getProperty("user.dir") + "/" ));
			saa.setCodeBase(new URL("file://" + System.getProperty("user.dir") + "/" ));
			Properties props = new Properties();
			props.put("INITIAL_MIBS","RFC1213-MIB"); 
			props.put("MIBS_DIR","mibs"); 
			saa.setAppletProperties(props);
			return saa;
		}
		catch(Exception ex)
		{
		}
		return null;
	}
	
	public static void main(String args[])
	{
		ConfigStandAloneWrapper csaw = new ConfigStandAloneWrapper(args);
	}

	
	class Test implements ActionListener 
	{
		public void actionPerformed(ActionEvent ae)
		{
			String command = ae.getActionCommand();
			
			if(command.equals("New Task"))
			{
				AddNewTask addTask = new AddNewTask(configPanel);
				addTask.setVisible(true);
			}
			else if (command.equals("Modify Task"))
			{
				Object[] selectedTaskNames = configPanel.getConfigMainUI().getSelectedTaskNames();
				if(selectedTaskNames != null && selectedTaskNames.length > 0)
				{
					AddNewTask  addTask = new AddNewTask(configPanel, configPanel.getConfigMainUI().getTaskDetails((String)selectedTaskNames[0]));
					configPanel.configClientUtils.centerWindow(addTask);
					addTask.setVisible(true);
				}
				else
				{
					configPanel.configClientUtils.showErrorDialog(configPanel, "No task selected", "Info", "info");
				}
			}
			else if (command.equals("Delete Task"))
			{
				configPanel.getConfigMainUI().removeTaskActionPerformed();
			}	
			else if (command.equals("Save DeviceList"))
			{
				Object[] taskNameArray = configPanel.getConfigMainUI().getSelectedTaskNames();
				if(taskNameArray != null && taskNameArray.length > 0)
				{
					String taskName = (String) taskNameArray[0];
					DeviceListOperation list = new DeviceListOperation(configPanel, taskName);
					list.setVisible(true);
				}
				else
				{
					configPanel.configClientUtils.showErrorDialog(configPanel, "Kindly select a task before associating devicelist", "Error", "error");
				}
			}
			
			else if(command.equals("Save DataSource"))
			{
				configPanel.getConfigMainUI().dataSourceOperationsActionPerformed();
/*				AddModifyDataSource amDataSource = new AddModifyDataSource(configPanel);
				amDataSource.setVisible(true);*/
			}
			
			else if(command.equals("Exit"))
			{
				System.exit(1);
			}
		}
	}
}
