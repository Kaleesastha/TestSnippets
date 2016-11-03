//$Id: EscalationFilterAction.java,v 1.1.6.2.2.1 2014/02/26 12:01:12 venkatramanan Exp $
package com.adventnet.nms.eventui;

import java.awt.Frame;
import java.awt.Image;
import java.util.Hashtable;
import java.util.Vector;
import java.util.Properties;
import java.util.*;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;

import com.adventnet.nms.alertui.EscalationPolicyAdmin;
import com.adventnet.nms.eventdb.FilterAction;
import com.adventnet.nms.eventui.DefaultNmsFilterFrame;
import com.adventnet.nms.policyui.NmsPolicyPanel;
import com.adventnet.nms.util.NmsClientUtil;
import com.adventnet.nms.util.NmsUiAPI;
import com.webnms.nms.eventui.AbstractFilterActionPanel;
import com.webnms.nms.eventui.AddFilterAction;

public class EscalationFilterAction extends AddFilterAction
{

	JTable filterTable = null;
	public DefaultNmsFilterFrame frm = null;
	DefaultMutableTreeNode selectedNode = null;
	
	/** Creates new form EscalationFilterAction */
	public EscalationFilterAction(Frame parent, boolean modal,String policyFilter) 
	{
		super(parent, modal,policyFilter);
		/*Image img;
        if((img=NmsClientUtil.getFrameIcon())!=null)
        {
            setIconImage(img);
        }*/
        filterTable = EscalationPolicyAdmin.filterTable;
	}
	
	public void addButtonActionPerformed(java.awt.event.ActionEvent evt)
	{
		int index = tabPanel.getSelectedIndex();
		String selectedAction = tabPanelList.get(index).getSelectedAction();
		if(selectedAction != null && !selectedAction.trim().equals(""))
		{
			DefaultTableModel model = (DefaultTableModel)filterTable.getModel();
			
			int size  = model.getRowCount();

			for(int i = 0; i < size; i ++)
			{
				String key = (String)model.getValueAt(i, 0);
				if(selectedAction.equals(key))
				{	
					JOptionPane.showMessageDialog(this  ,NmsClientUtil.GetString("javaui.filter.alreadexist"), NmsClientUtil.GetString("javaui.filter.errormessage"), JOptionPane.ERROR_MESSAGE);//No i18n
					return;
				}
			}

			Vector<String> vec = new Vector<String>();
			vec.addElement(selectedAction);
			model.addRow(vec);
		}
		checkAndSaveNotifiers();
		setVisible(false);
		dispose();
	}
	
	public void checkAndSaveNotifiers()
	{

		boolean changed = false;
		notifiers.clear();		
		int size = tabPanelList.size();
		for(int i = 0 ;i<size;i++)
		{
			AbstractFilterActionPanel panel = tabPanelList.elementAt(i);
			if(panel.hasActionsChanged())
			{
				changed = true;
			}
			notifiers.putAll(panel.getActionList());			
		}

		if(changed)
		{
			NmsPolicyPanel policyPanel = (NmsPolicyPanel)NmsUiAPI.getNmsPanel("Policy");//No i18n
			NmsPolicyPanel.notifiers = notifiers;
			NmsPolicyPanel.dbpolicynotifiers = notifiers;
			policyPanel.getPolicyClient().saveNotifiers();
		}
	}
	
	public void populateDetails()
	{   

		notifiers = NmsPolicyPanel.notifiers;
		Hashtable<String,FilterAction> dbpolicyactions = NmsPolicyPanel.dbpolicynotifiers;
		Enumeration enm = NmsPolicyPanel.dbpolicynotifiers.keys();
		while(enm.hasMoreElements())
		{
			String keyval = (String)enm.nextElement();
			FilterAction act = (FilterAction)dbpolicyactions.get(keyval);
			notifiers.put(keyval,act);
		}
		Hashtable<Integer,Hashtable<String,FilterAction>> sepList = separateFilterActions();
		int size = tabPanelList.size();
		for(int i= 0;i<size;i++)
		{
			AbstractFilterActionPanel panel = tabPanelList.elementAt(i);
			panel.setActionList(sepList.get(i));
		}

		pack();
	}

	public Hashtable<String,Properties> getSmtpAccountList()
	{
		Hashtable<String,Properties> smtpAccountList = NmsPolicyPanel.smtpList; 
		return smtpAccountList;
	}

	public boolean saveSmtpAccountList(Hashtable<String,Properties> smtpAccountList)
	{
		NmsPolicyPanel policyPanel = (NmsPolicyPanel)NmsUiAPI.getNmsPanel("Policy");//No i18n
		policyPanel.getPolicyClient().saveMailSettings(smtpAccountList);
		return true;
	}

}
