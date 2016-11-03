//$Id: InvokePerfUI.java,v 1.1.4.10 2013/08/29 11:38:35 wesley Exp $
package com.adventnet.nms.perfui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;


import com.adventnet.nms.perfui.MainPerfPanel;
import com.adventnet.nms.pollui.StatsAdminPanel;
import com.adventnet.nms.util.CustomClassInterface;
import com.adventnet.nms.util.NmsClientUtil;
import com.adventnet.nms.xmlui.NmsPropertiesPanel;
import com.adventnet.nms.xmlui.NmsPropsFactory;
import com.adventnet.nms.xmlui.NmsTreePanel;

public class InvokePerfUI implements CustomClassInterface,ActionListener,TreeSelectionListener {

	String filename="PerformanceConfigUI.xml";//No I18N
	private static NmsTreePanel panel;
	

	public void setProperties(Properties[] props) {
		
		try{
		Properties prop = props[0];
		String action = (String) prop.get("ACTION");//No I18N
		panel=(NmsTreePanel) NmsPropsFactory.parsePropertyXML(filename);
		if(panel == null)
			return;
		addTreeNodeListeners();
		boolean show=performAuthCheck(action);
		if(show)
		{	
		panel.selectNode(action);
		String viewId = (String) prop.get("VIEWID");//No I18N
		if(viewId != null && viewId.equals("KPI"))
		{
			panel.enableDisabledTreeNode(false,"PolledData");//No I18N
			panel.enableDisabledTreeNode(false,"PollingObject");//No I18N
		}
		MainPerfPanel frame = new MainPerfPanel();
		JButton help = frame.getHelpButton();
		help.addActionListener(this);
		frame.getContentPane().add(panel,BorderLayout.CENTER);
		frame.setPreferredSize(new Dimension(800,655));
		frame.validate();
		
		//frame.setModal(true);
		//frame.setModalityType(Dialog.ModalityType.DOCUMENT_MODAL);
		frame.pack();
		frame.setVisible(true);
		}
		else{
			String msg = "";
			if(action.equals("PollingObject"))
				msg = "javaui.poll.error.userNotAuthorizedToViewPollingObjects";
			else if(action.equals("PolledData"))
				msg = "javaui.poll.error.userNotAuthorizedtoviewpd";
			else if(action.equals("Threshold"))
				msg = "javaui.poll.error.userNotAuthorizedToVieThreshold";
			JOptionPane.showMessageDialog(panel,NmsClientUtil.GetString(msg),NmsClientUtil.GetString("Error Message"),JOptionPane.ERROR_MESSAGE);
		}
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	private void addTreeNodeListeners()
	{
		Vector nodes = panel.getTreeNodeComponents();
		for(int i=0;i<=nodes.size()-1;i++)
		{
			Component comp = (Component) nodes.elementAt(i);
			if(comp instanceof JButton)
			{
				JButton but = (JButton)comp;
				but.addActionListener(this);
			}
			else 
			{
				panel.getjtree().addTreeSelectionListener(this);
			
			}
		}
		
	}

	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("help"))
		{
			String selection = panel.getSelectedTreeNodeId();
			String key = "Poll_Details";
			if(selection.equals("PolledData"))
				key="Poll_Object_Details";
			else if(selection.equals("Threshold"))
				key="Poll_Threshold_Details";
			NmsClientUtil.showHelpBasedOnKey(key);
		}
		else
		{
		JButton button = (JButton) e.getSource();
		Vector nodes = panel.getTreeNodeComponents();
		for(int i=0;i<=nodes.size()-1;i++)
		{
			Component comp = (Component) nodes.elementAt(i);
			if(comp instanceof JButton)
			{
				if(comp == button)
				{
					comp.setBackground(new Color(90,135,226));
					comp.setForeground(Color.white);
					comp.setFont(new Font(NmsClientUtil.getFont().getName(),1,12));
				}
				else{
					comp.setBackground(new Color(223,223,223));
					comp.setForeground(Color.black);
					comp.setFont(new Font(NmsClientUtil.getFont().getName(),0,12));
				}
			}
		}	
		}
	}

	public void valueChanged(TreeSelectionEvent e) {
				
		
	}
	
	//Used for invoking threshold UI from PolledData/PollingObject threshold dialogs
	public static void invokeThresholdUI()
	{
		panel.selectNode("Threshold");//No I18N
	}
	
		
	/*
	 * This method checks if the user has permissions to view PollingObjects/PolledData/Thresholds. If not, the
	 * corresponding tree node will be disabled.
	 */
	public boolean performAuthCheck(String action)
	{
		Vector permissions = StatsAdminPanel.pollclient.getUserPermissions();
		boolean flag=true;
		if(permissions !=null)
		{
			if(!permissions.contains("Get Polling Objects"))//No I18N
			{
			    panel.enableDisabledTreeNode(false,"PollingObject");//No I18N
			    if(action.equalsIgnoreCase("PollingObject"))//No I18N
			    	flag=false;
			}
			if(!permissions.contains("Get Polling Unit"))//No I18N
			{
				panel.enableDisabledTreeNode(false,"PolledData");//No I18N
				if(action.equalsIgnoreCase("PolledData"))//No I18N
					flag=false;
			}	
			if(!permissions.contains("Get Threshold Objects"))//No I18N
			{	
				panel.enableDisabledTreeNode(false,"Threshold");//No I18N
				if(action.equalsIgnoreCase("Threshold"))//No I18N
					flag=false;
			}
						
		}
		
		return flag;
	}
	
	public NmsPropertiesPanel getPanel()
	{
		return panel;
	}
}
