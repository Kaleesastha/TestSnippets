//$Id: PollingObjectPropsPanel.java,v 1.1.4.22 2013/08/31 11:48:36 vijayalakshmiv Exp $

package com.adventnet.nms.perfui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;
import java.awt.HeadlessException;
import javax.swing.BorderFactory;
import javax.swing.CellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import com.adventnet.nms.xmlui.DataTableModel;
import com.adventnet.nms.xmlui.NmsComboItem;
import com.adventnet.nms.xmlui.NmsPropertiesPanel;
import com.adventnet.nms.xmlui.NmsWizardPanel;
import com.adventnet.nms.perfui.MibUIWrapper;
import com.adventnet.nms.perfui.PollObjOidList;
import com.adventnet.nms.perfui.PollingObjectUI;
import com.adventnet.nms.poll.Compare;
import com.adventnet.nms.poll.NumericCompare;
import com.adventnet.nms.poll.StringCompare;
import com.adventnet.nms.pollui.PerfUIData;
//import com.adventnet.nms.pollui.StatsAdminPanel;
import com.adventnet.nms.util.NmsClientUtil;

/**
 * The NmsWizardPanel class for Polling Objects
 * 
 * @author vijayalakshmiv
 *
 */
public class PollingObjectPropsPanel extends NmsWizardPanel implements ActionListener,ItemListener,KeyListener{

	public PollObjOidList list;
	public Properties pollobjProps;
	//private Vector DcVector;
	//private Vector oids= new Vector();
	private int selindex=0;
	private JButton AddDid;
	private JButton ModifyDid;
	private JButton DeleteDid;
	public String operation="";
	private PollingObjectUI pobj ;
	private int oidindex=0;
	private Vector commonprops;
	private String pollname="";
	private Vector numericcond=new Vector();
	private Vector stringcond=new Vector();
	private JTextField pdname = null;
	
	
	public void preInit()
	{
		pobj = new PollingObjectUI(this);
		PerfUIData.getInstance().registerPanel(pobj.getId(),pobj);
		getWizardCard().add(pobj,"defaultcard");//No I18N
				
	}
	
	public void init()
	{
		super.init();
	}
	
	public void postInit()
	{
		pdname = (JTextField) getComponentWithProp("name","PollObjPage2");
		getWizardCard().setBorder(BorderFactory.createLineBorder(new Color(90,135,226),1));
		addListPanel();
		separateStringandNumeric();
		addListeners();
		changeWizardControls(false);
		setCommonPropsVector();
		keyeventchecking();
	}

	private void keyeventchecking() throws HeadlessException {
   	   
	    	KeyListener HandleSymbolskeyListener = new KeyListener() {
	    	      public void keyPressed(KeyEvent keyEvent) {
	    	      	  JTextField textField = (JTextField) keyEvent.getSource();
	    	    	  char c = keyEvent.getKeyChar();
	    	       	  if(((c >= 32) && (c<=45)) || (c==47) || ((c>=58) && (c<=64)) || ((c>=91) && (c<=94)) || (c==96) || ((c>=123) && (c<=126)))
	    	    	  {
	    	    		  textField.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(new Character(c), 0), "none");
	    	    	  }
	    	      }
	    	      public void keyReleased(KeyEvent keyEvent) {
	    	      }
	              public void keyTyped(KeyEvent keyEvent) {
	    	      }
	        };
	        
	        KeyListener HandleNumbersAndSymbolskeyListener = new KeyListener() {
	    	      public void keyPressed(KeyEvent keyEvent) {
	    	      	  JTextField textField = (JTextField) keyEvent.getSource();
	    	    	  char c = keyEvent.getKeyChar();
	    	       	  if(((c >= 32) && (c<=64)) || ((c>=91) && (c<=96)) || ((c>=123) && (c<=126)))
	    	    	  {
	    	    		  textField.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(new Character(c), 0), "none");
	    	    	  }
	    	      }
	    	      public void keyReleased(KeyEvent keyEvent) {
	    	      }
	            public void keyTyped(KeyEvent keyEvent) {
	    	      }
	        };
	        
	        KeyListener HandleNonNumerickeyListener = new KeyListener() {
	    	      public void keyPressed(KeyEvent keyEvent) {
	    	      	  JTextField textField = (JTextField) keyEvent.getSource();
	    	    	  char c = keyEvent.getKeyChar();
	    	       	  if(((c >= 32) && (c<=47)) || ((c>=58) && (c<=126)))
	    	    	  {
	    	    		  textField.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(new Character(c), 0), "none");
	    	    	  }
	    	      }
	    	      public void keyReleased(KeyEvent keyEvent) {
	    	      }
	          public void keyTyped(KeyEvent keyEvent) {
	    	      }
	       };
	        JTextField nameTextField=(JTextField) getComponentWithProp("name");
	        nameTextField.addKeyListener(HandleSymbolskeyListener);
	        JTextField page2nameTextField=(JTextField) getComponentWithProp("name","PollObjPage2");
	        page2nameTextField.addKeyListener(HandleSymbolskeyListener);
	        JTextField pollingPeriodTextField=(JTextField) getComponentWithProp("pollingPeriod");
	        pollingPeriodTextField.addKeyListener(HandleNonNumerickeyListener);
	        JTextField ClassTextField=(JTextField) getComponentWithProp("Class");
	        ClassTextField.addKeyListener(HandleSymbolskeyListener);
	        JTextField intervalTextField=(JTextField) getComponentWithProp("interval");
	        intervalTextField.addKeyListener(HandleNonNumerickeyListener);
	        JTextField prefixTextField=(JTextField) getComponentWithProp("prefix");
	        prefixTextField.addKeyListener(HandleSymbolskeyListener);
	        JTextField thresholdListTextField=(JTextField) getComponentWithProp("thresholdList");
	        thresholdListTextField.addKeyListener(HandleSymbolskeyListener);
	        JTextField failureThresholdTextField=(JTextField) getComponentWithProp("failureThreshold");
	        failureThresholdTextField.addKeyListener(HandleNonNumerickeyListener);
	}
	
	private void separateStringandNumeric()
	{
		Component condition=getTableColumnComponent("condition");//No I18N
		String[] num={"eq","neq","gt","lt","ge","le"};//No I18N
		String[] str={"equals","notequals","contains","doesNotContain","startsWith","doesNotStartWith","endsWith","doesNotEndWith"};//No I18N
		 
		if(condition instanceof JComboBox)
		{
			JComboBox cb = (JComboBox) condition;
			for(int i=0;i<=cb.getItemCount()-1;i++)
			{
				boolean flag=false;
				NmsComboItem item = (NmsComboItem) cb.getItemAt(i);
				String cd = item.getLabel();
				for(int j=0;j<=str.length-1;j++)
				{
				String toadd = NmsClientUtil.GetString("javaui.xmlui.combobox.label."+str[j]);
				if(cd.equals(toadd))
					{
					stringcond.add(str[j]);
					flag=true;
					break;
					}
				}
				if(flag)
					continue;
				for(int k=0;k<=num.length-1;k++)
				{
					String toadd = NmsClientUtil.GetString("javaui.xmlui.combobox.label."+num[k]);
					if(cd.equals(toadd))
				 {
					numericcond.add(num[k]);
					break;
				 }	
				}
					
			}
		}
		
	}
		
	
	private void setCommonPropsVector()
	{
		commonprops = new Vector();
		commonprops.add("status");//No I18N
		commonprops.add("updateMOs");//No I18N
		commonprops.add("Compare");//No I18N
		//commonprops.add("pollingPeriod");
		commonprops.add("Class");//No I18N
	}
	//Hides the wizard controls for the first Polling Object list page
	public void changeWizardControls(boolean flag)
	{
		//getControlPanel().setVisible(flag);
		Component[] comps = getControlPanel().getComponents();
		for(int i=0;i<=comps.length-1;i++)
		{
			if(comps[i] instanceof JButton)
			{
				JButton b = (JButton) comps[i];
				b.setVisible(flag);
			}
		}
	}
	private void addListPanel()
	{
		NmsPropertiesPanel panel=getPage("PollObjPage2");//No I18N
		list = new PollObjOidList(this);
		panel.add(list,BorderLayout.LINE_START);
		panel.updateUI();
		panel.validate();
		panel.repaint();
	}
	private void addListeners()
	{
		JCheckBox active = (JCheckBox)getComponentWithProp("active");//No I18N
		if(active != null)
			
    	active.addItemListener(this);
    	
    	JCheckBox save = (JCheckBox)getComponentWithProp("storeData");//No I18N
    	if(save != null)
    	save.addItemListener(this);
    	
    	
    	JCheckBox threshold = (JCheckBox)getComponentWithProp("threshold");//No I18N
    	if(threshold != null)
    	threshold.addItemListener(this); 
    	
    	JButton browsemib = getControl("browsemib");//No I18N
    	if(browsemib != null)
    		
    	browsemib.addActionListener(this);
    	
    	AddDid = getControl("AddDid");//No I18N
    	if(AddDid != null)
    	{
   		AddDid.addActionListener(this);
    	}
    	if(pdname != null)
		pdname.addKeyListener(this);	
    	
		
   	
    	ModifyDid = getControl("ModifyDid");//No I18N
    	if( ModifyDid!= null)
    	
    		ModifyDid.addActionListener(this);
    	
    	DeleteDid = getControl("DeleteDid");//No I18N
    	if(DeleteDid != null)
    	
    		DeleteDid.addActionListener(this);
    	
    	JButton addrow = getControl("addrow");//No I18N
    	if(addrow != null)
    	{
    		addrow.addActionListener(this);
    	}
		
    	
    	JButton deleterow = getControl("deleterow");//No I18N
    	if(deleterow != null)
    	{	
    	deleterow.addActionListener(this);
    	}
    	
    	Component type=getTableColumnComponent("type");//No I18N
    
    	if(type != null){
    		((JComboBox)type).addActionListener(this);
    	}
    	
    	JButton bt = getControl("Thresholdbrowse");//No I18N
    	if(bt != null)
    		bt.addActionListener(this);
    	JComboBox oiddata = (JComboBox) this.getComponentWithProp("oiddatatype","PollObjPage2");
    	if(oiddata != null)
    		oiddata.addActionListener(this);
	}
	
	
	 public void setData(Properties props)
	    {
	    	if(props.remove("confirm") != null)
			{
				boolean success = (Boolean) props.remove("success");
				if(success)
				{	
					//String operation=PollingObjectUI.operation;
					String op =  (String) props.remove("operation");
					if((op != null) && (op != ""))
					{
					 operation = op;
					}
					if(operation.equalsIgnoreCase("Add"))
					{
					JOptionPane.showMessageDialog(null,NmsClientUtil.GetString("javaui.perfui.perfdata.pollingobject.add"),NmsClientUtil.GetString("javaui.perfui.perfdata.pollingobject.dialogtitle.add"),JOptionPane.INFORMATION_MESSAGE);
					}
					else if(operation.equalsIgnoreCase("Modify"))
					{
					JOptionPane.showMessageDialog(null,NmsClientUtil.GetString("javaui.perfui.perfdata.pollingobject.modify"),NmsClientUtil.GetString("javaui.perfui.perfdata.pollingobject.dialogtitle.modify"),JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{
					JOptionPane.showMessageDialog(null,NmsClientUtil.GetString("javaui.perfui.perfdata.pollingobject.delete"),NmsClientUtil.GetString("javaui.perfui.perfdata.pollingobject.dialogtitle.delete"),JOptionPane.INFORMATION_MESSAGE);
					}
				}	
				
				else{
					
					showErrorDialog("Unable to "+ operation +" Polling object."+"Refer server side logs for further details");//NO I18N
					pobj.handleError();
					NmsClientUtil.normalCursor(this);
				}	
			}
		 	else{
	    		super.setData(props);
	    		pobj.oldprops=props;
		 	}
	    }
	
	 public void showErrorDialog(String message)
		{
			JOptionPane.showMessageDialog(this,message,NmsClientUtil.GetString("Error Message"),JOptionPane.ERROR_MESSAGE);//No I18N
			NmsClientUtil.normalCursor(this);
		}
	
	 private void actionToperform(String action,Object source)
	    {
	    	if(action.equalsIgnoreCase("browsemib"))//No I18N
	    	{
	    		MibUIWrapper browse = new MibUIWrapper(this);
	    		browse.setVisible(true);
	    	}
	    	if(action.equalsIgnoreCase("AddDid"))//No I18N
	    	{
	    		addDataId();
	    	}
	    	if(action.equalsIgnoreCase("ModifyDid"))//No I18N
	    	{
	    		modifyDataId();
	    	}
	    	if(action.equalsIgnoreCase("DeleteDid"))//No I18N
	    	{
	    		deleteDataId();
	    	}
	    	if(action.equalsIgnoreCase("addrow"))//No I18N
	    	{
	    		JTable table=(JTable)getComponentWithProp("Compare");//No I18N
	    		if(table != null)
	    		{
	    		  DataTableModel model = (DataTableModel) table.getModel();
	    		  model.addRow(new Vector());
	    		}

			JComboBox prop = (JComboBox) getTableColumnComponent("property");
			prop.getEditor().getEditorComponent().addKeyListener(new KeyListener()
			//prop.addKeyListener(new KeyListener()
			{

				public void keyTyped(KeyEvent e) {
					// TODO Auto-generated method stub
	         	}

				public void keyPressed(KeyEvent keyEvent) {
					JTextField textField = (JTextField) keyEvent.getSource();
					char c = keyEvent.getKeyChar();
					if(((c >= 32) && (c<=45)) || (c==47) || ((c>=58) && (c<=64)) || ((c>=91) && (c<=94)) || (c==96) || ((c>=123) && (c<=126)))
					{
						textField.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(new Character(c), 0), "none");
					}
				}

				public void keyReleased(KeyEvent e) {
					// TODO Auto-generated method stub
				}

			});
	    	}
	    	if(action.equalsIgnoreCase("deleterow"))//No I18N
	    	{
	    		JTable table=(JTable)getComponentWithProp("Compare");//No I18N
	    		if(table != null)
	    		{
	    		  DataTableModel model = (DataTableModel) table.getModel();
	    		  int index=table.getSelectedRow();
	    		  if(index<0)
	    		  {
	    			  JOptionPane.showMessageDialog(this,NmsClientUtil.GetString("javaui.perfgui.matchcriteria.deleteerror"),NmsClientUtil.GetString("Error Message"),JOptionPane.ERROR_MESSAGE);
	    			  return;
	    		  }
	    		  int res=JOptionPane.showConfirmDialog(this,NmsClientUtil.GetString("javaui.perfgui.matchcriteria.deleteconfirmation"),NmsClientUtil.GetString("javaui.audioui.confirmDialog"),JOptionPane.OK_CANCEL_OPTION);
	    		  if(res==JOptionPane.OK_OPTION)
	    		  {	  
	    			  Runnable run = new Runnable()
	    			  {
	    				  public void run() {
	    					  JTable table=(JTable)getComponentWithProp("Compare");//No I18N
	    					  DataTableModel model = (DataTableModel) table.getModel();
	    					  int index=table.getSelectedRow();
	    					  model.removeRow(index);
	    					  table.updateUI();
	    					  table.clearSelection();
	    					  CellEditor editor =table.getCellEditor();
	    					  if(editor!=null)
	    					  {
	    						  editor.cancelCellEditing();//fix for WEBNMS7825
	    					  }
	    				  }
	    			  };
	                   SwingUtilities.invokeLater(run);
	    		  }
	    		}
	    	}	
	    	if(action.equalsIgnoreCase("type"))//No I18N
	    	{
	 
	    		JComboBox cbox=(JComboBox)source;
	    		NmsComboItem item = (NmsComboItem) cbox.getSelectedItem();
	    		if(item != null)
	    		{	
	    		String sel = item.getValue();
	    		    		
	    		removeNumericString(sel);
	    		}
	    	}
	    	
	    	if(action.equalsIgnoreCase("Thresholdbrowse"))
	    	{
	    		ThresholdDialog dlg = new ThresholdDialog(this);
	    		dlg.setVisible(true);
	    	}
	    	
	    	if(action.equalsIgnoreCase("oiddatatype"))
	    	{
	    		JComboBox cmb = (JComboBox) source;
	    		NmsComboItem item = (NmsComboItem) cmb.getSelectedItem();
	    		String oiddatatype = item.getValue();
	    		JCheckBox active = (JCheckBox)getComponentWithProp("active");//No I18N
	    		JCheckBox save = (JCheckBox)getComponentWithProp("storeData");//No I18N
	       		if(active != null && save !=null)
	    		{
	    		if(oiddatatype.equalsIgnoreCase("long") && active.isSelected() && save.isSelected())
	    		{
	    			changeField(true,"statsDataTableName","PollObjPage2");
	    			changeField(false,"decimalDataTableName","PollObjPage2");
	    			changeField(false,"stringDataTableName","PollObjPage2");
	    			setValue("statsDataTableName","STATSDATA%");
	    			setValue("decimalDataTableName","");
	    			setValue("stringDataTableName","");
	    		}
	    		else if(oiddatatype.equalsIgnoreCase("string") && active.isSelected() && save.isSelected())
	    		{
	    			changeField(false,"decimalDataTableName","PollObjPage2");
	    			changeField(false,"statsDataTableName","PollObjPage2");
	    			changeField(true,"stringDataTableName","PollObjPage2");
	    			setValue("stringDataTableName","STRINGDATA%");
	    			setValue("statsDataTableName","");
	    			setValue("decimalDataTableName","");
	    		}
	    		else if(oiddatatype.equalsIgnoreCase("decimal") && active.isSelected() && save.isSelected())
	    		{
	    			changeField(true,"decimalDataTableName","PollObjPage2");
	    			changeField(false,"statsDataTableName","PollObjPage2");
	    			changeField(false,"stringDataTableName","PollObjPage2");
	    			setValue("decimalDataTableName","DECIMALDATA%");
	    			setValue("statsDataTableName","");
	    			setValue("stringDataTableName","");
	    		}
	    		else
	    		{
	    			changeField(false,"decimalDataTableName","PollObjPage2");
	    			changeField(false,"statsDataTableName","PollObjPage2");
	    			changeField(false,"stringDataTableName","PollObjPage2");
	    		}
	    		}
	    	}
	    	}	
	
	    	
	    	
	 
	private void removeNumericString(String sel)
	 {
		 JComboBox condition = (JComboBox) getTableColumnComponent("condition");//No I18N
		 condition.removeAllItems();
		 Vector toadd=null;
		 if(sel.equalsIgnoreCase("string"))//No I18N
		 {	 
			 toadd=stringcond;
		 }
		 else
		 {
			 toadd=numericcond;
		 }
		 
		 for(int i=0;i<=toadd.size()-1;i++)
		 {
			 NmsComboItem item = new NmsComboItem(toadd.elementAt(i).toString());
			 condition.addItem(item);
		 }
		 
	 }
	 private void addDataId()
	 {
		 NmsClientUtil.busyCursor(this);
		 list.operation="add";//No I18N
		 
		 Properties props = getData();
		 
		 for(int i=0;i<=commonprops.size()-1;i++)
		 {
			 String rem = (String) commonprops.elementAt(i);
			 props.remove(rem);
			 
		 }
		// props=getDataCollectionData(props);
		 if(validateProps(props,true)){
			 String oid = (String)props.get("oid");//No I18N
             //oids.add(oid);
             list.addOid(oid);
             addToVector(-1,props);
             clearPage("PollObjPage2");//No I18N
             clearNextPages();
		 	}
		 
		 changeDataIdControls(false);
		 list.operation="";//No I18N
		 NmsClientUtil.normalCursor(this);
	}
	 
	/* private Properties getDataCollectionData(Properties props)
	 {
		 //Properties props1 = getData();
		 String oidname=(String) props1.get("name");//No I18N
		 props.remove("name");//No I18N
		 props.remove("status");//No I18N
		 props.remove("updateMOs");//No I18N
		 props.remove("Compare");//No I18N
		 props.remove("Class");//No I18N
		 props.put("name",oidname);//No I18N
		 
		 return props;
		 
	 }*/
	 private void addToVector(int index,Properties props)
	 {
		String t=(String)props.get("thresholdList");//No I18N
		if(t!= null && !t.equalsIgnoreCase(""))
		{	
		int k = 1;
		StringTokenizer st= new StringTokenizer(t,",");//No Internationalisation //No Internationalisation
		while(st.hasMoreTokens())
		{
			props.put("ThresholdName"+k,st.nextToken());//No Internationalisation //No Internationalisation
			k++;
		}
		}
		//props.put("CommonCriteria",(String)props.get("pollingPeriod"));//No I18N
		
		list.addToVector(props, index);
		// check regarding prefix and interval
		
		/*if(index == -1)
		DcVector.add(props);
		else
			DcVector.add(index,props);*/
		
	 }
	 private void modifyDataId(){
		 NmsClientUtil.busyCursor(this);
		 Properties props = getData();
		 
		 
		 for(Enumeration en=props.keys();en.hasMoreElements();)
		 {
			 String key = (String) en.nextElement();
			 if(commonprops.contains(key))
				 props.remove(key);
		 }
		 props.remove("pollingPeriod");//No I18N
		 //props=getDataCollectionData(props);
		 
		 if(validateProps(props,false)){
			 
			 list.removeFromVector(oidindex);
			 list.addToVector(props,oidindex);
			 
			 list.modifyOid();
			 addToVector(oidindex,props);
			 restoreDefaults();
		 }
		 else
		 {
			 list.modifyOid();
			 restoreDefaults();
		 }
		 NmsClientUtil.normalCursor(this);
	 
	 }
	 
	 private void restoreDefaults()
	 {
		 clearPage(getCurrentPageId());
		 clearNextPages();
		 changeField(true,"name");
		 changeField(true,"oid");
		 changeField(true,"type");
		 changeDataIdControls(false);
	 }
	 
	 private void deleteDataId(){
		 int re = JOptionPane.showConfirmDialog (this,NmsClientUtil.GetString("javaui.perfgui.pollingobject.deleteoid.confirmation"),NmsClientUtil.GetString("Confirm Deletion"),JOptionPane.ERROR_MESSAGE);

		 NmsClientUtil.busyCursor(this);
		 if (selindex != -1 && re ==0)
			{
				list.removeFromVector(selindex);
				list.deleteOid();
				//clearPage("PollObjPage2");//No I18N
				restoreDefaults();
			}
		 else
		 {
			 list.modifyOid();
			restoreDefaults();
			//changeDataIdControls(true);
		 }
			/*if(DcVector.size()<=0)
			{
				ModifyDid.setEnabled(false);
				DeleteDid.setEnabled(false);
			}*/
		 NmsClientUtil.normalCursor(this);
	 }
	 
	 private boolean validateProps(Properties props,boolean add)
	 {
		 String s="";
		if(add)
		{	
		 s=(String) props.get("oid");//No I18N
		 if ((s==null) || (s.equals(""))) {//No Internationalisation //No Internationalisation
				JOptionPane.showMessageDialog(this,NmsClientUtil.GetString("webclient.perf.pollingobj.oid.error"),NmsClientUtil.GetString("Error Message"),JOptionPane.ERROR_MESSAGE);
				getComponentWithProp("oid").requestFocus();//No I18N
				return false;
			}
		 if(s.indexOf("..")>=0) 
		 {//No Internationalisation //No Internationalisation
				JOptionPane.showMessageDialog(this,NmsClientUtil.GetString("javaui.perfgui.polleddata.illformedoid.error"),NmsClientUtil.GetString("Error Message"),JOptionPane.ERROR_MESSAGE);
				getComponentWithProp("oid").requestFocus();//No I18N
				return false;
			}            
		 if(list.listmodel.contains(s))
				{
					JOptionPane.showMessageDialog(this,NmsClientUtil.GetString("javaui.perfgui.oidalreadypresent.error"),NmsClientUtil.GetString("Error Message"),JOptionPane.ERROR_MESSAGE);
					getComponentWithProp("oid").requestFocus();//No I18N
					return false;
				}
			
			s = (String)props.get("name");
			if ((s==null) || (s.equals(""))) {//No Internationalisation //No Internationalisation
				JOptionPane.showMessageDialog(this,NmsClientUtil.GetString("javaui.perfgui.entervalidstringerror"),NmsClientUtil.GetString("Error Message"),JOptionPane.ERROR_MESSAGE);
				getComponentWithProp("name").requestFocus();//No I18N
				return false;
			}
		}	
			s = (String)props.get("interval");//No I18N
			if((s != null) && (s.length() > 0))
			{
				try{
					int n = Integer.parseInt(s);
				}catch(NumberFormatException e)
				{
					JOptionPane.showMessageDialog(this,NmsClientUtil.GetString("javaui.perfgui.entervalidnumericerror"),NmsClientUtil.GetString("Error Message"),JOptionPane.ERROR_MESSAGE);
					getComponentWithProp("interval").requestFocus();//No I18N
					return false;
				}
			}
			s = (String)props.get("failureThreshold");//No I18N
			
			if((s != null) && (s.length() > 0))
			{
				try{
					int n = Integer.parseInt(s);
				}catch(NumberFormatException e)
				{
					JOptionPane.showMessageDialog(this,NmsClientUtil.GetString("javaui.perfgui.entervalidsintegererror"),NmsClientUtil.GetString("Error Message"),JOptionPane.ERROR_MESSAGE);
					getComponentWithProp("failureThreshold").requestFocus();//No I18N

					return false;
				}
			}
			if(!add)
			{
				
			}
			return true;
	 }
	 
	 public boolean canFinish()
	 {
		return true; 
	 }
	 
	 public void doFinish(Properties props)
	 {
		 try{
			 
	    props=setMatchCriteria(props);
		 
		 
		 
		 
		// String[] tohave={"status","updateMOs","Compare","pollingPeriod","Class"};//No I18N
		 //Note all other properties (including user props will be taken as collection criteria only.
		 
		 for(Enumeration en =props.keys();en.hasMoreElements();)
		 {
			 boolean flag=false;
			 String key = (String) en.nextElement();
			 for(int i=0;i<=commonprops.size()-1;i++)
			 {
				String s= (String) commonprops.elementAt(i);
				 if(key.equalsIgnoreCase(s)){
					 {
						 flag=true;
						 break;
					 }
					
				 }
			 }
			 Object obj = props.get(key);
			 if(obj == null)
				 flag=false;
			 else if(obj instanceof String && obj.toString().equals(""))
			 {
				 flag=false;
			 }
			 if(!flag)
				 props.remove(key);
		 }
		//Maybe can think of some other way to do this in future.Assuming that, name will be present only in the first page, we do this.
		 
		 props.put("name",pollname);//No I18N
		 
//		 String period =getValue("pollingPeriod");//No I18N
//		 props.put("pollingPeriod",period);//No I18N
		 Vector dcVector=list.getDcVector();
		 
		 if(dcVector != null)
		 {
		 removeThresholdList(dcVector);
		 try
		 {
	
	 	 if(operation.equalsIgnoreCase("add"))
	 	 {
	 		 props =populateCommonCriteria(props,dcVector);
	 		 props.put("CollectionCriteria",list.getDcVector());//No I18N
			 addPollObj(props);
	 	 }
	 	    
	 	 else if(operation.equalsIgnoreCase("modify"))
		 {
	 		 removePollingPeriod(dcVector);
	 		 props = populateCommonCriteria(props,dcVector);
			 props.put("CollectionCriteria",list.getDcVector());//No I18N 
	                 modifyPollObj(props);
		 }
		 list.getDcVector().clear();
		 changeFirstPage(true);
		 super.doFinish(props);
		 changeWizardControls(false);
		 NmsClientUtil.normalCursor(this);
		 }
		 catch(Exception e)
		 {
			 JOptionPane.showMessageDialog(null,NmsClientUtil.GetString("javaui.perfgui.pollingobject.dataidentifiernull.error"),NmsClientUtil.GetString("javaui.perfui.perfdata.pollingobject.dialogtitle.error"),JOptionPane.INFORMATION_MESSAGE);
		 }
		 }
		 else
		 {
			 JOptionPane.showMessageDialog(null,NmsClientUtil.GetString("javaui.perfgui.pollingobject.dataidentifiernull.error"),NmsClientUtil.GetString("javaui.perfui.perfdata.pollingobject.dialogtitle.error"),JOptionPane.INFORMATION_MESSAGE);
		 }
		 }
		 catch(Exception e)
		 {
			 System.err.println("Exception while performing "+operation);//No I18N
			 e.printStackTrace();
		 }
	 }
	 
	 /*
	  * Note: for associating a key for threshold list field, we had to use the key thresholdList
	  * However,while sending it to the server, we need to have props as thresholdName#1,thresholdName#2 etc.
	  * Hence, this dummy key thresholdList has to be removed from the dcVector
	  */
	 
	 private void removeThresholdList(Vector dcVector)
	 {
		 for(int i=0;i<=dcVector.size()-1;i++)
		 {
			 Properties props = (Properties) dcVector.elementAt(i);
			 if(props.containsKey("thresholdList"))
				 props.remove("thresholdList");
		 }
	 }
	 
	 private void removePollingPeriod(Vector dcVector)
	 {
		 for(int i=0;i<=dcVector.size()-1;i++)
		 {
			 Properties props = (Properties) dcVector.elementAt(i);
			 if(props.containsKey("pollingPeriod"))
				 props.remove("pollingPeriod");
		 }
	 }
	 
	 private Properties populateCommonCriteria(Properties props,Vector dcVector)
	 {
		 Properties common  = new Properties();
		 String period = getValue("pollingPeriod");//No I18N
		 common.put("pollingPeriod",period);//No I18N
		 //Note: Prefix will be common for all OIDs in a particular PollingObject
		 Properties oidprops = (Properties)dcVector.elementAt(0);
		 String prefix=(String) oidprops.get("prefix");//No I18N
		 if(prefix != null)
		 common.put("prefix",prefix);//No I18N
		 
		 props.put("CommonCriteria",common);//No I18N
		 return props;
	 }
	 private void modifyPollObj(Properties props)
	 {
		 try
	     {
	       String result="";
	       String ex="";
	       NmsClientUtil.busyCursor(this);
	       //StatsAdminPanel.pollclient.sendReqToModifyPollingObject(props);
           
	       //if(result.equalsIgnoreCase("success"))//No I18N
	         {
	         pobj.modifyPollTable(props, selindex);
	        
	          }
	         //else{
	         /*StringTokenizer str = new StringTokenizer(result,",");//No I18N
	                 while(str.hasMoreElements())
	                 {
	                  str.nextToken();
	                  ex=str.nextToken();
	                  JOptionPane.showMessageDialog(this,"Exception while modifying PolledData  "+ex,"Error",JOptionPane.ERROR);//No I18N
	                 }
	                 return;
	           //   }*/
	     }
	       catch(Exception ex){
	            ex.printStackTrace();
	        }
	       NmsClientUtil.normalCursor(this); 
	 }
		 
	 private Properties setMatchCriteria(Properties props)
	 {
		 Vector mc = (Vector) props.get("Compare");//No I18N
		 Vector McVector = new Vector();
		 for(int i=0;i<=mc.size()-1;i++)
			{
			Properties p = (Properties) mc.elementAt(i);	
			 String type=(String)p.get("type");//No I18N
	         String prop =(String)p.get("property");//No I18N
	         String cond = (String)p.get("condition");//No I18N
	         String val = (String)p.get("value");//No I18N
				if(type.equalsIgnoreCase("string"))//No I18N
	               {
	                 StringCompare comp=new StringCompare(prop,cond,val);
	                McVector.addElement(comp);
	               }
	             else
	                {
	                  long val1 = Long.parseLong(val);
	                  NumericCompare comp = new NumericCompare(prop,cond,val1);
	                  McVector.addElement(comp);
	                 }
			}
		 props.put("Compare",McVector);//No I18N
		 Vector uclass = new Vector();
		 uclass.add(props.get("Class"));//No I18N
		 props.put("Class",uclass);//No I18N
		 return props;
	 }
	 private void addPollObj(Properties props)
	    {
	       try{

	           String result= "";
	           //StatsAdminPanel.pollclient.sendReqToAddPollingObject(props);
	           NmsClientUtil.busyCursor(this);
	           //String ex="";
	           //if(result.equalsIgnoreCase("success"))//No I18N
	           {
	            
	            String name=(String) props.get("name");//No I18N
	            String period = (String) props.get("pollingPeriod");//No I18N
	            String status = (String) props.get("status");//No I18N
	            String[] rowdata = {name,status,period};
	            pobj.addPollObject(rowdata,props);
	            JPanel WizardPanel = getWizardCard();
	            CardLayout cl=(CardLayout) WizardPanel.getLayout();
	            cl.show(WizardPanel,"defaultcard");//No I18N
	            
	            
	            }
	            //else
	            /*{
	            
	                StringTokenizer str = new StringTokenizer(result,",");//No I18N
	                while(str.hasMoreElements())
	                {
	                 str.nextToken();
	                 ex=str.nextToken();
	                 JOptionPane.showMessageDialog(this,"Exception while adding Polling Object  "+ex,"Error",JOptionPane.ERROR_MESSAGE);//No I18N

	                }
	                return;
	             }*/
	           
	           
	        }
	        catch(Exception ex){
	            ex.printStackTrace();
	        }
	        NmsClientUtil.normalCursor(this);    
	        

	        
	    }
	    private void checkBoxItemstateChange(ItemEvent e,String[] tochange)
	    {
	    	{
	    		// TODO Auto-generated method stub
	    		if(e.getStateChange()==e.DESELECTED) {
			       disableComponents(tochange);
			    } else if(e.getStateChange()==e.SELECTED) {
			            enableComponents(tochange);
			    }
	    	}
	    }
	public void disableComponents(String[] todisable)
	{
		
		for(int i=0;i<=todisable.length-1;i++)
		{
			changeField(false,todisable[i]);
		}
	}
	
	public void enableComponents(String[] toenable)
    {
    	for(int i=0;i<=toenable.length-1;i++)
    	{	
    	changeField(true,toenable[i]);
    	}
    }
	
	public void populateOIDList(Vector dcvector,String prefix)
	{
	    Vector oids = new Vector();
		for(int i=0;i<=dcvector.size()-1;i++)
		{
			Properties props = (Properties)dcvector.elementAt(i);
			Enumeration keys = props.keys();
			String thresholdList = new String();
			while(keys.hasMoreElements())
			{
				String propkey = (String)keys.nextElement();
				if(propkey.startsWith("ThresholdName"))
				{
					String value = props.getProperty(propkey);
					if(thresholdList.length() == 0)
					{
						thresholdList = value;
					}
					else
					{
						thresholdList = thresholdList + "," + value;
					}
				}
			}
			props.put("thresholdList", thresholdList);
			String oid = (String) props.get("oid");//No I18N
			oids.add(oid);
			if(prefix != null)
			props.put("prefix",prefix);
			list.addToVector(props,i);
		}
		
		list.populateList(oids);
		
	}
	
	private Properties populateDefaults(Properties props)
	{
		Vector v = getKeys();
		
		
		for(Enumeration en = v.elements();en.hasMoreElements();)
		{
			Properties pagekeys = (Properties) en.nextElement();
			for(Enumeration e = pagekeys.keys();e.hasMoreElements();)
			{
				String key = (String) e.nextElement();
				if(!props.containsKey(key)&&!commonprops.contains(key))
				{
				  props.put(key,pagekeys.get(key));	
				}
			}
		}
		return props;
	}
	public boolean beforeNext()
	{
			String name=getValue("name");//No I18N
			if(name.equals("")||name==null)//No I18N
			{
				JOptionPane.showMessageDialog(this,NmsClientUtil.GetString("javaui.perfgui.pollingobject.namenull.error"));
				return false;
			}
			
			if(operation.equals("add") && pobj.getNamesVector().contains(getValue("name")))//No I18N
			{
				JOptionPane.showMessageDialog(this,NmsClientUtil.GetString("javaui.perfgui.pollingobject.nameexists.error"));
				return false;
			}

			try
			{
			Vector vect = getTableValue("Compare");
			JTable tables = (JTable)getComponentWithProp("Compare");
			DataTableModel models = (DataTableModel) tables.getModel();
			int rec = models.getRowCount();
		        if (rec > 0)
			{
		        	if (tables.isEditing())
		        	{
		        		tables.getCellEditor().stopCellEditing();
		        	}
			}
			}
			catch(Exception e)
			{
			JTable tables = (JTable)getComponentWithProp("Compare");
			DataTableModel models = (DataTableModel) tables.getModel();
			int rec = models.getRowCount();
		        if (rec > 0)
			{
		        	if (tables.isEditing())
		        	{
		        		tables.getCellEditor().stopCellEditing();
		        	}
			}
			}

                        try
                        {
			if((getTableValue("Compare")==null||getTableValue("Compare").isEmpty()) && (getValue("Class")==null || getValue("Class").equals("")))//No I18N
			{
				JOptionPane.showMessageDialog(this,NmsClientUtil.GetString("javaui.perfgui.pollingobject.matchciterianull.error"));
				return false;
			}
                        }
                        catch(Exception e)
			{
				JOptionPane.showMessageDialog(this,NmsClientUtil.GetString("javaui.perfgui.pollingobject.matchciterianull.error"));
				return false;
			}
			
			String period = getValue("pollingPeriod");
			if(period != null && !period.equals(""))
			{
				try{
					int n = Integer.parseInt(period);
				}catch(NumberFormatException e)
				{
					JOptionPane.showMessageDialog(this,NmsClientUtil.GetString("javaui.perfgui.entervalidnumericerror"),NmsClientUtil.GetString("Error Message"),JOptionPane.ERROR_MESSAGE);
					getComponentWithProp("pollingPeriod").requestFocus();//No I18N
					return false;
				}
			}
			
				if(!validateMatchCriteria(getTableValue("Compare")))
				{
					return false;
				}
				else
				{
					if(checkHasDuplicates(getTableValue("Compare")))
					{
						return false;
					}
				}
			
			
			setPollName(name);
			
			return true;
	}
	
	private boolean checkHasDuplicates(Vector mc){
		
		Vector pvec = new Vector();
		for(int i=0;i<=mc.size()-1;i++)
		{
			Properties props = (Properties) mc.elementAt(i);
			String pty = (String)props.get("property");
			if(!pvec.contains(pty))
			{
				pvec.add(pty);
			}
			else
			{
				JOptionPane.showMessageDialog(this,NmsClientUtil.GetString("javaui.perfgui.matchcriteria.criteriaduplicateerror"),NmsClientUtil.GetString("Error Message"),JOptionPane.ERROR_MESSAGE);
				return true;
			}
		}
		
		
		return false;
	}
	private boolean validateMatchCriteria(Vector mc)
	{
	
		for(int i=0;i<=mc.size()-1;i++)
		{
			Properties props = (Properties) mc.elementAt(i);
			String type=(String) props.get("type");
			if(type == null || type.equals(""))
			{
				JOptionPane.showMessageDialog(this,NmsClientUtil.GetString("javaui.perfgui.matchcriteria.typenullerror"),NmsClientUtil.GetString("Error Message"),JOptionPane.ERROR_MESSAGE);
				return false;
			}
			String pr = (String)props.get("property");
			if(pr == null || pr.equals(""))
			{
				JOptionPane.showMessageDialog(this,NmsClientUtil.GetString("javaui.perfgui.matchcriteria.criterianullerror"),NmsClientUtil.GetString("Error Message"),JOptionPane.ERROR_MESSAGE);
				return false;
			}
			String condition=(String)props.get("condition");
			if(condition==null||condition.equals(""))
			{
				JOptionPane.showMessageDialog(this,NmsClientUtil.GetString("javaui.perfgui.matchcriteria.conditionnullerror"),NmsClientUtil.GetString("Error Message"),JOptionPane.ERROR_MESSAGE);
				return false;
				
			}
			String value=(String) props.get("value");
			if(value==null||value.equals(""))
			{
				JOptionPane.showMessageDialog(this,NmsClientUtil.GetString("javaui.perfgui.matchcriteria.valuenullerror"),NmsClientUtil.GetString("Error Message"),JOptionPane.ERROR_MESSAGE);
				return false;
				
			}
			if(type.equalsIgnoreCase("numeric"))
			{
				
				
				try{
					int n = Integer.parseInt(value);
				}catch(NumberFormatException e)
				{
					JOptionPane.showMessageDialog(this,NmsClientUtil.GetString("javaui.perfgui.matchcriteria.numericerror"),NmsClientUtil.GetString("Error Message"),JOptionPane.ERROR_MESSAGE);
					return false;
				}
				
			}
		}
		
		return true;
	}
	public void populateDcPropsPanel(Properties props,int index)
	{
		int i = 1;
		selindex=index;
		String oiddatatype = (String) props.get("oiddatatype");
		String[] todisable = {"decimalDataTableName","statsDataTableName","stringDataTableName","name","oid","type"};//NO I18N
		disableEnableComponents(todisable,false,"PollObjPage2");
		JButton browse = getControl("browsemib");
		if(browse != null)
		{
			browse.setEnabled(false);
		}
		String threshold="";
		while(props.get("thresholdName"+i) != null)//No I18N
			{
		String pr = (String)props.get("thresholdName"+i);
		if(threshold.equals(""))
		{
			threshold=pr;
		}
		else{
		threshold=threshold+","+pr;//No I18N

		}
		i++;
		
		}
		props.put("SelectedThresholds",threshold);//No I18N
		setData(props);
		
		JTextField namefield=(JTextField)getComponentWithProp("name","PollObjPage1");
		if(namefield!= null)
		{	
		namefield.setText(pollname);
		namefield.setEnabled(true);
		}
		oidindex=index;
		
	}
	
	
	public void setPollObjProps(Properties props)
	{
	  this.pollobjProps=props;
	  
	  String name = (String) props.get("name");//No I18N
	  setPollName(name);
	  Vector Compare = (Vector) props.get("Compare");//No I18N
      //Note: Table Model can set values only if a Vector of Properties is provided.
      Vector Mcvector=convertMcProps(Compare);
      props.put("Compare",Mcvector);
      Vector uclass =(Vector)props.get("Class");//No I18N
      //Note: Assuming that only one user class can exist.
      if(uclass != null)
      props.put("Class",uclass.elementAt(0));
      
      
      String prefix="";//No I18N
      if(props.get("CommonCriteria") != null)
      {
    	  Properties common = (Properties) props.get("CommonCriteria");
    	  String period = (String) common.get("pollingPeriod");
    	  if(period != null)
    	  props.put("pollingPeriod",period);//No I18N
    	  prefix = (String)common.get("prefix");//No I18N
      }
      Vector DcVector = (Vector) props.get("CollectionCriteria");//No I18N
      setDataforPage("PollObjPage1", props);//No I18N
      
      if(DcVector!= null)
      populateOIDList(DcVector,prefix);
      changeFirstPage(false);
      

	}
	
	private void changeFirstPage(boolean flag)
	{
		Component nme=getComponentWithProp("name","PollObjPage1");
	      if(nme != null && nme instanceof JTextField)
	      {	  
	    	  JTextField n=(JTextField)nme;
	    	  n.setEnabled(flag);
	      }
	    Component uclass = getComponentWithProp("Class");
	    if(uclass != null && uclass instanceof JTextField)
	    {
	    	JTextField n=(JTextField)uclass;
	    	  n.setEnabled(flag);
	    	
	    }
	    Component mc = getComponentWithProp("Compare");
	    if(mc != null && mc instanceof JTable)
	    {
	    	JTable n = (JTable)mc;
	    	n.setEnabled(flag);
	    }
	    JButton add = getControl("addrow");
	    if(add != null)
	    	add.setEnabled(flag);
	    JButton delete = getControl("deleterow");
	    	delete.setEnabled(flag);
	    Component comp = getComponentWithProp("status");
	    Vector permissions = pobj.permissions;
	    if(comp != null)
	    {	
	    	if(permissions.contains("Change Polling Object Status"))
	    		comp.setEnabled(true);
	    	else
	    		comp.setEnabled(false);
	    }
	    	    
	}
	public void addPollObj()
	{
		clearAll();
		String[] enable ={"name","Compare","Class"};//No I18N
	    enableComponents(enable);
		showPage("PollObjPage1");//No I18N
		list.listmodel.removeAllElements();
	}
	
	public void modifyPollObj(int row)
	{
		showPage("PollObjPage1");//No I18N
		String[] todisable ={"Compare","Class"};//No I18N
	    disableComponents(todisable);
	    selindex=row;
	    
	}
	
	private void setPollName(String name)
	{
		pollname=name;
	}
	
	private Vector convertMcProps(Vector compare)
    {
     Vector v = new Vector();
     for(Enumeration en =compare.elements();en.hasMoreElements();)
     { 
       Properties props = new Properties();
       Compare obj=(Compare)en.nextElement();
 	   String type=(String)obj.getType();
 	   props.put("type",type);//No I18N
 	   String property =(String)obj.getProperty();
 	   props.put("property",property);//No I18N
 	   String condition =(String)obj.getCondition();
 	   props.put("condition",condition);//No I18N
 	   String value =(String)obj.getValue();
 	   props.put("value",value);//No I18N
 	   
 	   v.add(props);
     }
     
     return v;
    }
	
	public void setSelectedIndex(int selindex)
	{
		this.selindex=selindex;
	}
	
	private GridBagConstraints getConstraints()
	{
		GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
		gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
		gridBagConstraints.gridx=0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 0.0;
		gridBagConstraints.weighty = 0.0;
		gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
		return gridBagConstraints;
	}
	
	public boolean beforePrevious()
	{
		//Note: first page should not have the wizard controls
		if(getCurrentPageIndex()==1){
			changeWizardControls(false);
		}
		
		return true;
	}
	
	public void doCancel()
	{
		int res=JOptionPane.showConfirmDialog(this,NmsClientUtil.GetString("javaui.perfgui.pollingobject.cancelaction"),NmsClientUtil.GetString("javaui.audioui.confirmDialog"),JOptionPane.YES_NO_OPTION);
		if(res==JOptionPane.OK_OPTION)
		{	
		changeWizardControls(false);
		Vector v = list.getDcVector();
		if(v!= null)
		v.clear();
		super.doCancel();
		}
	}
	
	public void changeDataIdControls(boolean flag)
	{
		ModifyDid.setEnabled(flag);//No I18N
		DeleteDid.setEnabled(flag);//No I18N
		
		AddDid.setEnabled(!flag);
	}
	
	public void setThresholdField(String threshold)
	 {
		Vector<String> newThresList = new Vector<String>();
		StringTokenizer st = new StringTokenizer(threshold,",");
		while(st.hasMoreTokens())
   	 	{
			newThresList.addElement(st.nextToken());
   	 	}
		
		JTextField th = (JTextField) getComponentWithProp("thresholdList");//No I18N
		if(th!=null)
		 {
			 String thresholds = th.getText();
			 Vector<String> availthresList = new Vector<String>();
		     if(thresholds!=null && !thresholds.equals(""))
		     {
		    	 st = new StringTokenizer(thresholds,",");
		    	 while(st.hasMoreTokens())
		    	 {
		    		 availthresList.addElement(st.nextToken());
		    	 }
		     }
		     int length=newThresList.size();
		     
		     for(int i=0;i<length;i++)
		     {
		    	 String value = (String)newThresList.elementAt(i);
		    	 if(!availthresList.contains(value))
		    	 {
		    		 if(thresholds.length() == 0)
		    		 {
		    			 thresholds = value;
		    		 }
		    		 else
		    		 {
		    			 thresholds = thresholds+","+value;
		    		 }
		    	 }
		     }
			 
		 th.setText(thresholds);
		}
	 }

	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		actionToperform(e.getActionCommand(),e.getSource());
	}

	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() instanceof JCheckBox)
		{	
		JCheckBox c = (JCheckBox) e.getSource();
		if(c.getActionCommand().equalsIgnoreCase("active"))//No I18N
		{	
			JCheckBox active = (JCheckBox)getComponentWithProp("active");
			JCheckBox save = (JCheckBox)getComponentWithProp("storeData");
			if(save != null && active != null)
			{
				String[] tochange ={"storeData","dataType","savePollCount","saveAbsolutes","timeAvg","logFile"};//No I18N
				checkBoxItemstateChange(e,tochange);
				if(save.isSelected() && active.isSelected())
				{
					JComboBox oiddata = (JComboBox) this.getComponentWithProp("oiddatatype","PollObjPage2");
					NmsComboItem item = (NmsComboItem) oiddata.getSelectedItem();
			    	if(oiddata != null)
			    	{
			    		if(item.getValue().equalsIgnoreCase("long"))
			    		{
			    			changeField(true,"statsDataTableName","PollObjPage2");
			    			changeField(false,"decimalDataTableName","PollObjPage2");
			    			changeField(false,"stringDataTableName","PollObjPage2");	
			    		}
			    		else if(item.getValue().equalsIgnoreCase("string"))
			    		{
			    			changeField(false,"decimalDataTableName","PollObjPage2");
			    			changeField(false,"statsDataTableName","PollObjPage2");
			    			changeField(true,"stringDataTableName","PollObjPage2");
			    		}
			    		else if(item.getValue().equalsIgnoreCase("decimal"))
			    		{
			    			changeField(true,"decimalDataTableName","PollObjPage2");
			    			changeField(false,"statsDataTableName","PollObjPage2");
			    			changeField(false,"stringDataTableName","PollObjPage2");
			    		}
			    		else
			    		{
			    			changeField(false,"decimalDataTableName","PollObjPage2");
			    			changeField(false,"statsDataTableName","PollObjPage2");
			    			changeField(false,"stringDataTableName","PollObjPage2");
			    		}
			    	}
				}
				else
				{
					boolean change=false;
					//changeField(change,"storeData");
					changeField(change,"dataType");
					changeField(change,"statsDataTableName");
					changeField(change,"savePollCount");
					changeField(change,"saveAbsolutes");
					changeField(change,"timeAvg");
					changeField(change,"logFile");
					changeField(change,"stringDataTableName");
					changeField(change,"decimalDataTableName");
				}
			}
			
		//	JCheckBox storeData = (JCheckBox)getComponentWithProp("storeData");
			
		
		}
		else if(c.getActionCommand().equalsIgnoreCase("storeData"))//No I18N
		{
			JCheckBox save = (JCheckBox)getComponentWithProp("storeData");
			if(save != null)
			{
				if(save.isSelected())
				{
			JComboBox oiddata = (JComboBox) this.getComponentWithProp("oiddatatype","PollObjPage2");
	    	if(oiddata != null)
	    	{
	    		String[] tochange ={"dataType","savePollCount","saveAbsolutes","timeAvg","logFile"};//No I18N
				checkBoxItemstateChange(e,tochange);
				NmsComboItem item = (NmsComboItem) oiddata.getSelectedItem();
	    		if(item.getValue().equalsIgnoreCase("long"))
	    		{
	    			changeField(true,"statsDataTableName","PollObjPage2");
	    			changeField(false,"decimalDataTableName","PollObjPage2");
	    			changeField(false,"stringDataTableName","PollObjPage2");
	    		}
	    		else if(item.getValue().equalsIgnoreCase("string"))
	    		{
	    			changeField(false,"decimalDataTableName","PollObjPage2");
	    			changeField(false,"statsDataTableName","PollObjPage2");
	    			changeField(true,"stringDataTableName","PollObjPage2");
	    		}
	    		else if(item.getValue().equalsIgnoreCase("decimal"))
	    		{
	    			changeField(true,"decimalDataTableName","PollObjPage2");
	    			changeField(false,"statsDataTableName","PollObjPage2");
	    			changeField(false,"stringDataTableName","PollObjPage2");
	    		}
	    		else
	    		{
	    			changeField(false,"decimalDataTableName","PollObjPage2");
	    			changeField(false,"statsDataTableName","PollObjPage2");
	    			changeField(false,"stringDataTableName","PollObjPage2");
	    		}
	    	}
				}
				else
				{
					String[] tochange ={"dataType","statsDataTableName","savePollCount","saveAbsolutes","timeAvg","logFile","stringDataTableName","decimalDataTableName"};//No I18N
					checkBoxItemstateChange(e,tochange);
				}
			}
			
			
			
			
			
			
			
		}
		else if(c.getActionCommand().equalsIgnoreCase("threshold"))//No I18N
		{
//			String[] tochange ={"thresholdList","failureThreshold","saveOnThreshold"};//No I18N
			boolean change = true;
			if(e.getStateChange()==e.DESELECTED)
				change=false;
			changeField(change,"thresholdList");
			changeField(change,"failureThreshold");
			changeField(change,"saveOnThreshold");
//			checkBoxItemstateChange(tochange,change);
			
			JButton bt = getControl("Thresholdbrowse");
			bt.setEnabled(change);	
			
		}
		
	}
	}		

	private void checkBoxItemstateChange(String[] tochange,boolean enable) {
		
		for(int i=0;i<=tochange.length-1;i++)
		{
			Component comp = getComponentWithProp(tochange[i],"PollObjPage2");
			comp.setEnabled(enable);
			
		}
		
	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==pdname)
		AddDid.setEnabled(true);
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}

