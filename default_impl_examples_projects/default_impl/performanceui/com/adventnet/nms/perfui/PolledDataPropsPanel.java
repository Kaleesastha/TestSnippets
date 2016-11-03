//$Id: PolledDataPropsPanel.java,v 1.1.4.17 2013/08/31 12:49:20 vijayalakshmiv Exp $
package com.adventnet.nms.perfui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Enumeration;
import java.util.Vector;
import java.util.Hashtable;
import java.util.Properties;
import javax.swing.KeyStroke;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.util.StringTokenizer;


import com.adventnet.nms.xmlui.NmsComboItem;
import com.adventnet.nms.xmlui.NmsWizardPanel;
//import com.adventnet.nms.pollui.StatsAdminPanel;
import com.adventnet.nms.pollui.PerfUIData;
import com.adventnet.nms.util.NmsClientUtil;
/**
 *
 * @author vijayalakshmiv
 */
public class PolledDataPropsPanel extends NmsWizardPanel implements KeyListener,ItemListener,ActionListener{

    
    public Hashtable polldata=new Hashtable();
    private  static PolledDataUI pdui;
    public JTextField agentfield;
    public String agentvalue;
    private JComboBox combo;
    private PerfUIData perfdata;
    Properties actualprops;
    PolledDataAgentSearch agentsearch = null;
    private String operation="Add";
   
   
    public void preInit()
    {
    	perfdata=PerfUIData.getInstance();
    	getPermissions();
    	setDefaults();
    	
    }
    
    public void init()
    {
    	super.init();
    	
    }
    
    public void postInit()
    {
    	addListeners();
    	String agent=perfdata.getSelectedAgent();
    	if(agent!=null)
    	{
    		agentvalue=agent;
    		pdui.setAgent(agent);
    		fetchPolledDataforAgent(agent);
    	}
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
		JTextField dnsNameTextField=(JTextField) getComponentWithProp("dnsName");
		dnsNameTextField.addKeyListener(HandleSymbolskeyListener);
		JTextField parentObjTextField=(JTextField) getComponentWithProp("parentObj");
		parentObjTextField.addKeyListener(HandleSymbolskeyListener);
		JTextField logFileTextField=(JTextField) getComponentWithProp("logFile");
		logFileTextField.addKeyListener(HandleSymbolskeyListener);
		JTextField periodTextField=(JTextField) getComponentWithProp("period");
		periodTextField.addKeyListener(HandleNonNumerickeyListener);
		JTextField failureThresholdTextField=(JTextField) getComponentWithProp("failureThreshold");
		failureThresholdTextField.addKeyListener(HandleNonNumerickeyListener);
		JTextField portTextField=(JTextField) getComponentWithProp("port");
		portTextField.addKeyListener(HandleNonNumerickeyListener);
		JTextField communityTextField=(JTextField) getComponentWithProp("community");
		communityTextField.addKeyListener(HandleNumbersAndSymbolskeyListener);
		JTextField thresholdListTextField=(JTextField) getComponentWithProp("thresholdList");
		thresholdListTextField.addKeyListener(HandleSymbolskeyListener);
		JTextField userNameTextField=(JTextField) getComponentWithProp("userName");
		userNameTextField.addKeyListener(HandleSymbolskeyListener);
		JTextField contextNameTextField=(JTextField) getComponentWithProp("contextName");
		contextNameTextField.addKeyListener(HandleSymbolskeyListener);
	}

    
    public Vector getPermissions()
    {
    	Vector permissions=perfdata.getUserPermissions();
    	return permissions;
    	
    }
 
    private void setDefaults()
    {
        pdui = new PolledDataUI(this);
        perfdata=PerfUIData.getInstance();
        perfdata.registerPanel(pdui.getId(),pdui);
        add(pdui,BorderLayout.LINE_START);
        setBorder(BorderFactory.createLineBorder(new Color(90,135,226),1));
    }
    

    private void addListeners()
    {
    	agentfield=(JTextField) getComponentWithProp("agent");//NO I18N
    	if(agentfield != null)
    	
    	agentfield.addKeyListener(this);
    	
		
    	JCheckBox active = (JCheckBox)getComponentWithProp("active");//NO I18N
    	if(active != null)
    	
    	active.addItemListener(this); 
            
			
    	JCheckBox save = (JCheckBox)getComponentWithProp("save");//NO I18N
    	if(save != null)
    		
    	save.addItemListener(this);
            
		JCheckBox threshold = (JCheckBox)getComponentWithProp("threshold","Pdpage2");//NO I18N
    	if(threshold != null)
    	threshold.addItemListener(this); 
    	
    	combo = (JComboBox)getComponentWithProp("snmpVersion");//NO I18N
    	if(combo != null)
    		
    	combo.addActionListener(this);
 
    	
    	JButton browsemib = getControl("browsemib");//NO I18N
    	if(browsemib != null)
    		
    	browsemib.addActionListener(this);
    	
    	  
    	JButton pollsearch = getControl("browseagent");//No I18N
    	if(pollsearch != null)
    	
    	pollsearch.addActionListener(this);
    		

    	JButton button = (JButton)getControl("PdThresholdbrowse");//NO I18N
    	if(button != null)
    		button.addActionListener(this);
    	
    	JCheckBox logdirectly = (JCheckBox)getComponentWithProp("logDirectly","Pdpage2");//NO I18N
    	if(logdirectly!=null)
    		logdirectly.addItemListener(this);
    }    
    
    public void setData(Properties props)
    {
    	if(props.remove("confirm") != null)
		{
			boolean success = (Boolean) props.remove("success");
			if(success)
			{	
				//String operation=PolledDataUI.operation;
				String op =  (String) props.remove("operation");
				if((op != null) && (op != ""))
				{
				 operation = op;
				}
				if(operation.equalsIgnoreCase("Add"))
				{
				JOptionPane.showMessageDialog(null,NmsClientUtil.GetString("javaui.perfui.perfdata.polleddata.add"),NmsClientUtil.GetString("javaui.perfui.perfdata.polleddata.dialogtitle.add"),JOptionPane.INFORMATION_MESSAGE);
				}
				else if(operation.equalsIgnoreCase("Modify"))
				{
				JOptionPane.showMessageDialog(null,NmsClientUtil.GetString("javaui.perfui.perfdata.polleddata.modify"),NmsClientUtil.GetString("javaui.perfui.perfdata.polleddata.dialogtitle.modify"),JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
				JOptionPane.showMessageDialog(null,NmsClientUtil.GetString("javaui.perfui.perfdata.polleddata.delete"),NmsClientUtil.GetString("javaui.perfui.perfdata.polleddata.dialogtitle.delete"),JOptionPane.INFORMATION_MESSAGE);
				}
			}	
			
			else{
				
				showErrorDialog("Unable to "+ operation +" Polled Data."+"Refer server side logs for further details");//NO I18N
				pdui.handleError();
				NmsClientUtil.normalCursor(this);
			}	
		}
		
 
    	else if(props.containsKey("agents"))
    	{
    		Vector agents = (Vector) props.get("agents");
    		int total = (Integer) props.get("total");
    		agentsearch.setAgentsVector(agents,total);
    	}
    	else
    		super.setData(props);
    }
    
    public void showErrorDialog(String message)
	{
		JOptionPane.showMessageDialog(this,message,NmsClientUtil.GetString("Error Message"),JOptionPane.ERROR_MESSAGE);//No I18N
		NmsClientUtil.normalCursor(this);
	}
    
    private void actionToperform(String action,ActionEvent evt)
    {
    	if(action.equalsIgnoreCase("browsemib"))//NO I18N
    	{
    		MibUIWrapper browse = new MibUIWrapper(this);
    		browse.setVisible(true);
    	}
    	else if (action.equalsIgnoreCase("browseagent"))//NO I18N
    	{
    		agentsearch = new PolledDataAgentSearch(this);	
    		agentsearch.setVisible(true);
    	}
    	else if(action.equalsIgnoreCase("snmpVersion"))
		{	
		
    	if(evt.getSource() instanceof JComboBox)
		{	
    	try{
    		JComboBox cmb = (JComboBox) evt.getSource();
		JPanel group=getGroupPanel("snmpV3");//NO I18N
		NmsComboItem item = (NmsComboItem) cmb.getSelectedItem();
		   	if(item.getValue().equalsIgnoreCase("v3")){
	    		group.setVisible(true);
		
	    	}
	    	else
	    	{
	    	group.setVisible(false);
	    	}
		group.validate();
		group.repaint();
		}
		
    	catch(Exception e)
    	{
    		
    	}
		}
		}
    	else if(action.equalsIgnoreCase("PdThresholdbrowse"))//NO I18N
    	{
    		JButton threshold = (JButton) evt.getSource();
    		ThresholdDialog dlg = new ThresholdDialog(this);
    		dlg.setVisible(true);
    	}
    	else if(action.equalsIgnoreCase("threshold"))//NO I18N
    	{
    		
    	}
    }
    private void checkBoxItemstateChange(ItemEvent e,String[] tochange,String pageId)
    {
    	{
    		boolean change = false;
    		if(e.getStateChange()==e.SELECTED) {
		           change=true;
		    }
    		disableEnableComponents(tochange,change,pageId);
    	}
    }

    private void fetchPolledDataforAgent(String agent)
    {
    	pdui.sendReqForPolledData(agent);
    	
    }
    
    
        
    public void setAgent(String agent)
    {
    	clearAll();
    	Component comp=getComponentWithProp("agent");
    	if(comp != null && comp instanceof JTextField)
    		((JTextField)comp).setText(agent);
    	agentvalue=agent;
    	pdui.setAgent(agent);
		fetchPolledDataforAgent(agentvalue);
		
    }
    
   public void doFinish(Properties props)
    {
    	NmsClientUtil.busyCursor(this);
    	String operation=PolledDataUI.operation;
    	props=addextraProps(props);
    	handleNumericType(props);
    	Properties props1 = (Properties) props.clone();
    	for(Enumeration en = props1.keys();en.hasMoreElements();)
    	{
    		String key = en.nextElement().toString();
    		Object obj = props1.get(key);
    		if(obj == null)
    			props.remove(key);
    		else if(obj instanceof String && obj.toString().equals(""))
    			props.remove(key);
    	}
    	if(operation.equalsIgnoreCase("add"))//NO I18N
    	{
    	  addPolledData(props);
    	  super.doFinish(props);
      	pdui.operation="Add";
    	}
    	else if(operation.equalsIgnoreCase("modify"))//NO I18N
    	{
    	   
    		modifyPolledData(props);
    	}
    	
    }
    
    
    private void handleNumericType(Properties props) {
	// TODO Auto-generated method stub
    	
    	String val = (String) props.get("numerictype");
    			
    	if(val.equalsIgnoreCase("long"))//No Internationalisation
    	{
    		val = "1";//No Internationalisation
    	}
    	else if(val.equalsIgnoreCase("string"))//No Internationalisation
    	{
    		val = "2";//No Internationalisation
    	}
    	else if(val.equalsIgnoreCase("decimal"))//No Internationalisation
    	{
    		val = "3";//No Internationalisation
    	}
    	else
    	{
    		val = "-1";//No Internationalisation
    	}
	   props.put("numericType", val);
	   
}

	private Properties addextraProps(Properties props)
    {
    	for(Enumeration en = actualprops.keys();en.hasMoreElements();)
    	{
    		String key = (String) en.nextElement();
    		if(!props.containsKey(key))
    			props.put(key, actualprops.getProperty(key));
    	}
    	return props;
    }
    
    private void modifyPolledData(Properties props)
    {
    	try{
    
        pdui.modifyPolledData(props);
        	{
           NmsClientUtil.normalCursor(this);
           }
       	showPage("PDpage1");
        }
         catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    
    
    public boolean  beforeNext()
    {
    	Properties props = getData();
    	String name = (String) props.get("name");//NO I18N
    	if(pdui.operation.equalsIgnoreCase("add") && polldata.containsKey(name))//NO I18N
        {
            JOptionPane.showMessageDialog(this,NmsClientUtil.GetString("javaui.perfgui.polleddata.alreadypresent.error "),NmsClientUtil.GetString("Error Message"),JOptionPane.ERROR_MESSAGE);
            
            return false;
        }
    	
        String agent =(String)props.get("agent");//NO I18N
        String oid =(String)props.get("oid");//NO I18N
        if (oid.indexOf("..")>=0)
        {
        JOptionPane.showMessageDialog(this,NmsClientUtil.GetString("javaui.perfgui.polleddata.illformedoid.error"),NmsClientUtil.GetString("Error Message"),JOptionPane.ERROR_MESSAGE);
        return false;
         }
        String period = (String)props.get("period");//NO I18N
        try{
        	int n = Integer.parseInt(period);
            }
        catch (NumberFormatException e)
        {
		JOptionPane.showMessageDialog(this,NmsClientUtil.GetString("javaui.perfgui.polleddata.numericperiod.error"),NmsClientUtil.GetString("Error Message"),JOptionPane.ERROR_MESSAGE);
		return false;
		}
        
        String[] keyfields ={name,agent,oid,period};
        if(!validateKeyField(keyfields))
        	return false;
        
        return true;
    }
    
    private boolean validateKeyField(String values[])
    {
       for(int i=0;i<=values.length-1;i++)
       {	   
    	String value = values[i];
    	   if(value.equals("") || value== null)//NO I18N
            {
                JOptionPane.showMessageDialog (this, NmsClientUtil.GetString("javaui.perfgui.polleddata.keyfieldsnull.error"), NmsClientUtil.GetString("Error Message"), JOptionPane.ERROR_MESSAGE);
                return false;
             }
       }
       
       return true;
    }
    private void addPolledData(Properties prop)
    {
           try{
            
        	   pdui.addPolledData(prop);
               String pdname=(String)prop.get("name");//NO I18N
               String pdkey=pdname+"\t"+prop.get("agent")+"\t"+prop.get("oid");//NO I18N
               polldata.put(pdname,pdkey);
               setValue("agent",(String) prop.get("agent"));//NO I18N
               NmsClientUtil.normalCursor(this);
               }
           catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()== e.VK_ENTER)
		{
			agentvalue=agentfield.getText();
			fetchPolledDataforAgent(agentvalue);
			agentfield.setText(agentvalue);
		}
	}
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() instanceof JCheckBox)
		{	
			JCheckBox c = (JCheckBox) e.getSource();
			if(c.getActionCommand().equalsIgnoreCase("active"))//No I18N
			{	
				JCheckBox active = (JCheckBox)getComponentWithProp("active");
				JCheckBox save = (JCheckBox)getComponentWithProp("save");
				if(save != null && active != null)
				{
					String[] tochange ={"save"};//No I18N
		   		        checkBoxItemstateChange(e,tochange,"Pdpage2");
		   		        if(save.isSelected() && active.isSelected())
		   			 {
		    				boolean change=true;
		    				changeField(change,"statsDataTableName");
						changeField(change,"savePollCount");
						changeField(change,"saveAbsolutes");
						changeField(change,"timeAvg");
						changeField(change,"logDirectly");
					}
		  		       else
		 		        {
		    				boolean change=false;
		    				changeField(change,"statsDataTableName");
						changeField(change,"savePollCount");
						changeField(change,"saveAbsolutes");
						changeField(change,"timeAvg");
						changeField(change,"logDirectly");
		   			 }
				}
			}
			else if(c.getActionCommand().equalsIgnoreCase("save"))//No I18N
			{
				String[] tochange ={"statsDataTableName","savePollCount","saveAbsolutes","timeAvg","logDirectly"};//No I18N
				checkBoxItemstateChange(e,tochange,"Pdpage2");
			}
			else if(c.getActionCommand().equalsIgnoreCase("threshold"))//No I18N
			{
				String[] tochange ={"thresholdList","failureThreshold","saveOnThreshold"};//No I18N
				checkBoxItemstateChange(e,tochange,"Pdpage2");
				JButton bt = getControl("PdThresholdbrowse");
				if(e.getStateChange()==e.SELECTED)
				{
					bt.setEnabled(true);
				}
				else
					bt.setEnabled(false);
			}
			else if(c.getActionCommand().equalsIgnoreCase("logDirectly"))//No I18N
			{
				boolean change=false;
				if(e.getStateChange()==e.SELECTED)
					change=true;
				changeField(change,"logFile","Pdpage2");
			}
		
		}
	}
			
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		actionToperform(e.getActionCommand(),e);
	}
	
	public void doCancel()
	{
		//pdui.clearTable();
		super.doCancel();
		pdui.resetAction();
	}
	
	public void setThresholdField(String threshold)
	 {
		Vector<String> newThresList = new Vector<String>();
		StringTokenizer st = new StringTokenizer(threshold,",");
		while(st.hasMoreTokens())
   	 	{
			newThresList.addElement(st.nextToken());
   	 	}
		
		JTextField th = (JTextField) getComponentWithProp("thresholdList","Pdpage2");//No I18N
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
	
	public boolean canFinish(Properties props)
	{
		try{
			String failurethreshold = props.getProperty("failureThreshold");//No I18N
			if(!failurethreshold.equals(""))
			{
				int thresh = Integer.parseInt(failurethreshold);
			}	
		
		}
		catch(NumberFormatException nfe)
		{
			
			JOptionPane.showMessageDialog (this, NmsClientUtil.GetString("javaui.perfgui.polleddata.failurethreshold.error"), NmsClientUtil.GetString("Error Message"), JOptionPane.ERROR_MESSAGE);//No I18N
			Component comp = getComponentWithProp("failureThreshold","Pdpage2");
			comp.requestFocus();
            return false;
		}
		
		String logdirectly = props.getProperty("logDirectly");//No I18N
		String logfile = props.getProperty("logFile");//No I18N
		if(logdirectly != null && logdirectly.equalsIgnoreCase("true") && logfile != null && logfile.equals("") )
		{
			JOptionPane.showMessageDialog (this, NmsClientUtil.GetString("javaui.perfgui.polleddata.logfile.error"), NmsClientUtil.GetString("Error Message"), JOptionPane.ERROR_MESSAGE);//No I18N
			Component comp = getComponentWithProp("logFile","Pdpage2");
			comp.requestFocus();
            return false;
		}
		
		return true;
	}
	
	public void selectPolledData(String pdname,Properties props)
	{
		pdui.setData(props);
	}
	
	public PolledDataUI getPolledDataUI()
	{
		return pdui;
	}
    
	//overriding this method here to allow to set focus on the name property.
	public void clearAll()
	{
		super.clearAll();
		getComponentWithProp("name").requestFocus();//No I18N
		/*pdui.resetTableSelection();
		*/
	}
}

