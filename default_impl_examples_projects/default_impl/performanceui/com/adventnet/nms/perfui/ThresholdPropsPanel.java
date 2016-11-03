//$Id: ThresholdPropsPanel.java,v 1.1.4.16 2013/08/13 07:34:30 vijayalakshmiv Exp $

package com.adventnet.nms.perfui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;

import com.adventnet.nms.xmlui.NmsComboItem;
import com.adventnet.nms.xmlui.NmsPropertiesPanel;
import com.adventnet.nms.pollui.PerfUIData;
import com.adventnet.nms.util.NmsClientUtil;

public class ThresholdPropsPanel extends NmsPropertiesPanel implements ActionListener{


private String operation="Add";//No I18N
private ThresholdUI thresholdui;
private Vector severity;
private PerfUIData perfdata;

public void preInit()
{
	
}

private void setDefaults()
{
    thresholdui = new ThresholdUI(this);
    getPropsPanel().add(thresholdui,BorderLayout.LINE_START);
    perfdata=PerfUIData.getInstance();
    perfdata.registerPanel(thresholdui.getId(),thresholdui);
}

public void postInit()
{
	setDefaults();
	getPropsPanel().setBorder(BorderFactory.createLineBorder(new Color(90,135,226),1));
	addListeners();
	JLabel resetlabel = getGroupLabel("ThresholdResetProps");
	if(resetlabel != null)
		resetlabel.setIcon(NmsClientUtil.getImageIcon(NmsClientUtil.applet.getDocumentBase()+"../images/help_contextual1.png"));//No I18N
	keyeventchecking();
}

private void keyeventchecking() throws HeadlessException 
{

	KeyListener HandleSymbolskeyListener = new KeyListener()
	{
		public void keyPressed(KeyEvent keyEvent)
		{
			JTextField textField = (JTextField) keyEvent.getSource();
			char c = keyEvent.getKeyChar();
			if(((c >= 32) && (c<=47)) || ((c>=58) && (c<=64)) || ((c>=91) && (c<=94)) || (c==96) || ((c>=123) && (c<=126)))
			{
				textField.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(new Character(c), 0), "none");
			}
		}
		public void keyReleased(KeyEvent keyEvent) 
		{
		}
		public void keyTyped(KeyEvent keyEvent) 
		{
		}
	};

	KeyListener HandleNumbersAndSymbolskeyListener = new KeyListener() 
	{
		public void keyPressed(KeyEvent keyEvent) 
		{
			JTextField textField = (JTextField) keyEvent.getSource();
			char c = keyEvent.getKeyChar();
			if(((c >= 32) && (c<=64)) || ((c>=91) && (c<=96)) || ((c>=123) && (c<=126)))
			{
				textField.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(new Character(c), 0), "none");
			}
		}
		public void keyReleased(KeyEvent keyEvent)
		{
		}
		public void keyTyped(KeyEvent keyEvent)
		{
		}
	};

	KeyListener HandleNonNumerickeyListener = new KeyListener() 
	{
		public void keyPressed(KeyEvent keyEvent) 
		{
			JTextField textField = (JTextField) keyEvent.getSource();
			char c = keyEvent.getKeyChar();
			if(((c >= 32) && (c<=47)) || ((c>=58) && (c<=126)))
			{
				textField.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(new Character(c), 0), "none");
			}
		}
		public void keyReleased(KeyEvent keyEvent)
		{
		}
		public void keyTyped(KeyEvent keyEvent)
		{
		}
	};
	
	KeyListener HandleNonDotNumerickeyListener = new KeyListener() 
	{
		public void keyPressed(KeyEvent keyEvent) 
		{
			JTextField textField = (JTextField) keyEvent.getSource();
			char c = keyEvent.getKeyChar();
			if(((c >= 32) && (c<=45)) || (c==47) || ((c>=58) && (c<=126)))
			{
				textField.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(new Character(c), 0), "none");
			}
		}
		public void keyReleased(KeyEvent keyEvent)
		{
		}
		public void keyTyped(KeyEvent keyEvent)
		{
		}
	};

    JTextField nameTextField=(JTextField) getComponentWithProp("name");
    nameTextField.addKeyListener(HandleSymbolskeyListener);
    JTextField categorylongTextField=(JTextField) getCardComponentWithProp("category","longcard");
    categorylongTextField.addKeyListener(HandleSymbolskeyListener);
    JTextField categorydecimalTextField=(JTextField) getCardComponentWithProp("category","decimalcard");
    categorydecimalTextField.addKeyListener(HandleSymbolskeyListener);
    JTextField categoryrangeTextField=(JTextField) getCardComponentWithProp("category","rangecard");
    categoryrangeTextField.addKeyListener(HandleSymbolskeyListener);
    JTextField categorypercentageTextField=(JTextField) getCardComponentWithProp("category","percentagecard");
    categorypercentageTextField.addKeyListener(HandleSymbolskeyListener);
    JTextField categorystringTextField=(JTextField) getCardComponentWithProp("category","stringcard");
    categorystringTextField.addKeyListener(HandleSymbolskeyListener);
    JTextField thresholdValuelongTextField=(JTextField) getCardComponentWithProp("thresholdValue","longcard");
    thresholdValuelongTextField.addKeyListener(HandleNonNumerickeyListener);
    JTextField thresholdValuedecimalTextField=(JTextField) getCardComponentWithProp("thresholdValue","decimalcard");
    thresholdValuedecimalTextField.addKeyListener(HandleNonDotNumerickeyListener);
    JTextField thresholdValuerangeTextField=(JTextField) getCardComponentWithProp("thresholdValue","rangecard");
    thresholdValuerangeTextField.addKeyListener(HandleNonDotNumerickeyListener);
    JTextField thresholdValuepercentageTextField=(JTextField) getCardComponentWithProp("thresholdValue","percentagecard");
    thresholdValuepercentageTextField.addKeyListener(HandleNonNumerickeyListener);
    JTextField rearmValuelongTextField=(JTextField) getCardComponentWithProp("rearmValue","longcard");
    rearmValuelongTextField.addKeyListener(HandleNonNumerickeyListener);
    JTextField rearmValuedecimalTextField=(JTextField) getCardComponentWithProp("rearmValue","decimalcard");
    rearmValuedecimalTextField.addKeyListener(HandleNonDotNumerickeyListener);
    JTextField rearmValuerangeTextField=(JTextField) getCardComponentWithProp("rearmValue","rangecard");
    rearmValuerangeTextField.addKeyListener(HandleNonDotNumerickeyListener);
    JTextField rearmValuepercentageTextField=(JTextField) getCardComponentWithProp("rearmValue","percentagecard");
    rearmValuepercentageTextField.addKeyListener(HandleNonNumerickeyListener);
    JTextField allowedTextField=(JTextField) getComponentWithProp("allowed");
    allowedTextField.addKeyListener(HandleSymbolskeyListener);
    JTextField disAllowedTextField=(JTextField) getComponentWithProp("disAllowed");
    disAllowedTextField.addKeyListener(HandleSymbolskeyListener);
}

  
private void addListeners()
{
	JComboBox type=(JComboBox) getComponentWithProp("kind");//No I18N
	type.addActionListener(this);
	
	JButton browsemib = getControl("browsemib");//No I18N
	if(browsemib != null)
	{	
	browsemib.addActionListener(this);
		
	}
	
}

private void browseMib()
{
	MibUIWrapper browse = new MibUIWrapper(this);
	browse.setVisible(true);
}

public void showTypeCard(String kind)
{
if(kind.equalsIgnoreCase("long"))//No I18N
{
	showCard("longcard");//No I18N
}
else if(kind.equalsIgnoreCase("string"))//No I18N
{
	showCard("stringcard");//No I18N
}
else if(kind.equalsIgnoreCase("percentage"))//No I18N
{
	showCard("percentagecard");//No I18N
	//JButton browsemib = getControl("browsemib");//No I18N
	//if(browsemib != null)
	//{	
	//browsemib.addActionListener(this);
	//}
}
else if(kind.equalsIgnoreCase("range"))//No I18N
{
	showCard("rangecard");//No I18N
}
else if(kind.equalsIgnoreCase("decimal"))
{
	showCard("decimalcard");//No I18N
}
}
public void disableComponents(String[] comps)
{
	for(int i=0;i<=comps.length-1;i++)
	{
		changeField(false,comps[i]);
	}
}

public void enableComponents(String[] comps)
{
	for(int i=0;i<=comps.length-1;i++)
	{
		changeField(true,comps[i]);
	}
}

public void setOperation(String operation)
{
	this.operation=operation;
}

public boolean canFinish(Properties props)
{
	//Properties props = getData();
	if(operation.equalsIgnoreCase("add"))//No I18N
	{
		String name = (String) props.get("name");//No I18N
		if(name==null || name.equals(""))//No Internationalisation//No Internationalisation
		{
			 JOptionPane.showMessageDialog(this,NmsClientUtil.GetString("javaui.perfgui.threshold.nameempty.error"),NmsClientUtil.GetString("Error Message"),JOptionPane.ERROR_MESSAGE);
	                 getComponentWithProp("name").requestFocus();//No I18N
	                 return false;
	    }
		
		if(!thresholdui.checkForName(name))
		{	
			JOptionPane.showMessageDialog(this,NmsClientUtil.GetString("javaui.perfgui.thresholdalreadyexists.error"),NmsClientUtil.GetString("Error Message"),JOptionPane.ERROR_MESSAGE);
			getComponentWithProp("name").requestFocus();//No I18N
			((JTextField)getComponentWithProp("name")).selectAll();//No I18N
            return false;
		}	
	}
	
	String s="";
	s=(String) props.get("message");//No I18N
    if(s==null || s.trim().equals(""))//No Internationalisation//No Internationalisation
    {
    	props.put("message","Threshold exceeded");//No Internationalisation//No Internationalisation
    }
    s=(String)props.get("category");//No I18N
    if(s==null || s.trim().equals(""))
    {
         props.put("category","Threshold");//No I18N
    }
    s=(String)props.get("clrMessage");//No I18N
    if(s==null || s.trim().equals(""))//No Internationalisation//No Internationalisation
    {
    	props.put("clrMessage","Threshold reset:");//No I18N
    }
	
	String type = (String) props.get("kind");//No I18N
	if(type == null || type.trim().equals(""))//No I18N
	{
		JOptionPane.showMessageDialog(this,NmsClientUtil.GetString("javaui.perfgui.threshold.typeempty.error"),NmsClientUtil.GetString("Error Message"),JOptionPane.ERROR_MESSAGE);
        getComponentWithProp("kind").requestFocus();//No I18N
        return false;
	}
	
	if(type.equalsIgnoreCase("long")||type.equalsIgnoreCase("percentage"))//No I18N
	{	
		if(validateLongPercent(props,type))
			
			return true;
	}
	else if(type.equalsIgnoreCase("decimal") ||type.equalsIgnoreCase("range"))
	{
		if(validateDecimal(props))
			return true;
	}
	else if(type.equalsIgnoreCase("string"))//No I18N
	{
		if(validateString(props))
			return true;
	}
	return false;
}

private boolean validateString(Properties props)
{
	String s="";
	s=(String)props.get("allowed");//No I18N
    if(s== null || s.trim().equals(""))
    {
        JOptionPane.showMessageDialog(this,NmsClientUtil.GetString("javaui.perfgui.threshold.allowedvalues.empty"),NmsClientUtil.GetString("Error Message"),JOptionPane.ERROR_MESSAGE);
			return false;
    }
    s=(String)props.get("disAllowed");//No I18N
    if(s==null||s.trim().equals(""))
    {
        JOptionPane.showMessageDialog(this,NmsClientUtil.GetString("javaui.perfgui.threshold.disallowedvalues.empty"),NmsClientUtil.GetString("Error Message"),JOptionPane.ERROR_MESSAGE);
			return false;
    }
     
    s=(String)props.get("triggerSeverity");//No I18N
    
    if(s== null)
    {
       JOptionPane.showMessageDialog(this,NmsClientUtil.GetString("javaui.perfgui.threshold.severitynull.error"),NmsClientUtil.GetString("Error Message"),JOptionPane.ERROR_MESSAGE);
       return false;
    }
    s= getSeverityValue(s);
    props.put("triggerSeverity",s);//No I18N
    
    s=(String)props.get("resetSeverity");//No I18N
    if(s==null || s.trim().equals(""))
    {
      JOptionPane.showMessageDialog(this,NmsClientUtil.GetString("Reset Severity cannot be empty"),NmsClientUtil.GetString("Error Message"),JOptionPane.ERROR_MESSAGE);//No I18N
      
	 return false;
    }
    
    s = getSeverityValue(s);
    props.put("resetSeverity",s);//No I18N
    
    return true;
}

private boolean validateDecimal(Properties props)
{
String s="";
	
	s=(String) props.get("thresholdValue");//No I18N
	
    if(s==null || s.equals(""))
    {
        JOptionPane.showMessageDialog(this,NmsClientUtil.GetString("javaui.perfgui.thresholdvalue.empty.error"),NmsClientUtil.GetString("Error Message"),JOptionPane.ERROR_MESSAGE);
        getComponentWithProp("thresholdValue").requestFocus();//No I18N
        return false;
    }
    
    try{
    	double val = Double.parseDouble(s);
    }
    catch(NumberFormatException nfe)
    {
    	JOptionPane.showMessageDialog(this,NmsClientUtil.GetString("javaui.perfgui.threshold.longvalue.error"),NmsClientUtil.GetString("Error Message"),JOptionPane.ERROR_MESSAGE);
        getComponentWithProp("thresholdValue").requestFocus();//No I18N
        return false;
    }
    s=(String)props.get("severity");//No I18N
    
    if(s== null)
    {
            JOptionPane.showMessageDialog(this,NmsClientUtil.GetString("javaui.perfgui.threshold.severitynull.error"),NmsClientUtil.GetString("Error Message"),JOptionPane.ERROR_MESSAGE);
            getComponentWithProp("severity").requestFocus();//No I18N
           return false;
    }
    s = getSeverityValue(s);
    props.put("severity",s);//No I18N
    s=(String)props.get("rearmValue");//No I18N
    if(s==null || s.equals(""))
    {
    JOptionPane.showMessageDialog(this,NmsClientUtil.GetString("javaui.perfgui.threshold.resetvaluenull.error"),NmsClientUtil.GetString("Error Message"),JOptionPane.ERROR_MESSAGE);
    getComponentWithProp("rearmValue").requestFocus();//No I18N
    return false;
    }
    s=(String)props.get("rearmValue");//No I18N
    try{
        double val = Double.parseDouble(s);
        }
        catch(NumberFormatException ne)
        {
            JOptionPane.showMessageDialog(this,NmsClientUtil.GetString("javaui.perfgui.threshold.invalidresetvalue.error"),NmsClientUtil.GetString("Error Message"),JOptionPane.ERROR_MESSAGE);
            getComponentWithProp("rearmValue").requestFocus();//No I18N
            return false;
        }
	return true;
}

private boolean validateLongPercent(Properties props,String type)
{
	String s="";
	
	s=(String) props.get("thresholdValue");//No I18N
	
    if(s==null || s.equals(""))
    {
        JOptionPane.showMessageDialog(this,NmsClientUtil.GetString("javaui.perfgui.thresholdvalue.empty.error"),NmsClientUtil.GetString("Error Message"),JOptionPane.ERROR_MESSAGE);
        getComponentWithProp("thresholdValue").requestFocus();//No I18N
        return false;
    }
    try{
    long val = Long.parseLong(s);
    }
    catch(NumberFormatException ne)
    {
        JOptionPane.showMessageDialog(this,NmsClientUtil.GetString("javaui.perfgui.threshold.longvalue.error"),NmsClientUtil.GetString("Error Message"),JOptionPane.ERROR_MESSAGE);
        getComponentWithProp("thresholdValue").requestFocus();//No I18N
        return false;
}
    
     s=(String)props.get("severity");//No I18N
     
     if(s== null)
     {
             JOptionPane.showMessageDialog(this,NmsClientUtil.GetString("javaui.perfgui.threshold.severitynull.error"),NmsClientUtil.GetString("Error Message"),JOptionPane.ERROR_MESSAGE);
             getComponentWithProp("severity").requestFocus();//No I18N
            return false;
     }
     s = getSeverityValue(s);
     props.put("severity",s);//No I18N
     
     s=(String)props.get("rearmValue");//No I18N
        if(s==null || s.equals(""))
        {
        JOptionPane.showMessageDialog(this,NmsClientUtil.GetString("javaui.perfgui.threshold.resetvaluenull.error"),NmsClientUtil.GetString("Error Message"),JOptionPane.ERROR_MESSAGE);
        getComponentWithProp("rearmValue").requestFocus();//No I18N
        return false;
        }
        try{
            long val = Long.parseLong(s);
            }
            catch(NumberFormatException ne)
            {
                JOptionPane.showMessageDialog(this,NmsClientUtil.GetString("javaui.perfgui.threshold.invalidresetvalue.error"),NmsClientUtil.GetString("Error Message"),JOptionPane.ERROR_MESSAGE);
                getComponentWithProp("rearmValue").requestFocus();//No I18N
                return false;
            }
      
      if(type.equalsIgnoreCase("percentage"))//No I18N
      {
    	  s=(String)props.get("oid");//No I18N
          if(s==null || s.equals(""))//No I18N
           {
               JOptionPane.showMessageDialog(this,NmsClientUtil.GetString("javaui.perfgui.threshold.oidvaluenull.error"),NmsClientUtil.GetString("Error Message"),JOptionPane.ERROR_MESSAGE);
               getComponentWithProp("oid").requestFocus();//No I18N
               return false;
           }
          if (s.indexOf("..")>=0)
          {
          JOptionPane.showMessageDialog(this,NmsClientUtil.GetString("javaui.perfgui.polleddata.illformedoid.error"),NmsClientUtil.GetString("Error Message"),JOptionPane.ERROR_MESSAGE);
          return false;
           }
      }
       
        return true;
}
public void setData(Properties props)
{
	if(props.remove("confirm") != null)
	{
		String op =  (String) props.remove("operation");
		if((op != null) && (op != ""))
		{
		 operation = op;
		}
		boolean success = (Boolean) props.remove("success");
		if(success)
		{	
			//String operation = getOperation();
			if(operation.equalsIgnoreCase("Add"))
			{
			addThreshold(props);
			JOptionPane.showMessageDialog(null,NmsClientUtil.GetString("javaui.perfui.perfdata.threshold.add"),NmsClientUtil.GetString("javaui.perfui.perfdata.threshold.dialogtitle.add"),JOptionPane.INFORMATION_MESSAGE);
			}
			else if(operation.equalsIgnoreCase("Modify"))
			{
			modifyThreshold(props);
			JOptionPane.showMessageDialog(null,NmsClientUtil.GetString("javaui.perfui.perfdata.threshold.modify"),NmsClientUtil.GetString("javaui.perfui.perfdata.threshold.dialogtitle.modify"),JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				deleteThreshold(props);
				JOptionPane.showMessageDialog(null,NmsClientUtil.GetString("javaui.perfui.perfdata.threshold.delete"),NmsClientUtil.GetString("javaui.perfui.perfdata.threshold.dialogtitle.delete"),JOptionPane.INFORMATION_MESSAGE);
			}
		}	
		
		else{
			
			showErrorDialog("Unable to "+ operation +" threshold object."+"Refer server side logs for further details");//NO I18N
			NmsClientUtil.normalCursor(this);
		}	
	}
	else{
		super.setData(props);
	}
}

private String getSeverityValue(String s)
{
	int severity=NmsClientUtil.severityInfo.getValue(s);
	return Integer.valueOf(severity).toString();
}

public void setSeverity(Properties props)
{
	String kind = (String) props.get("kind");//No I18N
	DefaultComboBoxModel cmodel = new DefaultComboBoxModel();
	JComboBox severity=null;
	String sev=null;
	
	
	if(kind.equalsIgnoreCase("long")||kind.equalsIgnoreCase("percentage")||kind.equalsIgnoreCase("range")||kind.equalsIgnoreCase("decimal"))//No I18N
	{
		severity=(JComboBox) getComponentWithProp("severity");//No I18N
		sev= getSeverityforCombo((String) props.get("severity"));//No I18N
		props.put("severity",sev);
		
	}
	else{
		severity=(JComboBox) getComponentWithProp("triggerSeverity");//No I18N
		sev= getSeverityforCombo((String) props.get("triggerSeverity"));//No I18N
		props.put("triggerSeverity",sev);
		JComboBox severity1=(JComboBox) getComponentWithProp("resetSeverity");//No I18N
		String sev1=getSeverityforCombo((String) props.get("resetSeverity"));//No I18N
		props.put("resetSeverity",sev1);
		severity1.setSelectedItem(sev1);
		
	}
	severity.setSelectedItem(sev);
	
	
}

private boolean hasSeverity(JComboBox cbox,String tocheck)
{
	for(int i=0;i<=cbox.getItemCount()-1;i++)
	{
		String sev=(String) cbox.getItemAt(i);
		if(sev.equalsIgnoreCase(tocheck))
			return true;
	}
	
	return false;
}
private String  getSeverityforCombo(String sev)
{
    
    try{
	int severityValue = Integer.parseInt(sev);	
	String severity=NmsClientUtil.severityInfo.getName(severityValue);
    return severity;
    }
    catch(NumberFormatException nfe)
    {
    	return sev;
    }
}

public void doFinish(Properties props)
{

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
	NmsClientUtil.busyCursor(this);
	if(!operation.equals("") && operation.equalsIgnoreCase("add"))//No I18N
	{	
		perfdata.sendDataToServer(props,"Add Threshold");
	
	}
	else if(!operation.equals("") && operation.equalsIgnoreCase("modify"))//No I18N
	{
		perfdata.sendDataToServer(props,"Modify Threshold");
	}
}

public void modifyThreshold(Properties props)
{
	thresholdui.modifyThreshold(props);	
	NmsClientUtil.normalCursor(this);
}

public void deleteThreshold(Properties props)
{
	thresholdui.deleteThreshold(props);
}
public void addThreshold(Properties props)
{
	
 		String name=(String)props.get("name");//No I18N
        thresholdui.addThreshold(name,props);
        NmsClientUtil.normalCursor(this);
 }

public void showErrorDialog(String message)
{
	JOptionPane.showMessageDialog(this,message,NmsClientUtil.GetString("Error Message"),JOptionPane.ERROR_MESSAGE);//No I18N
	NmsClientUtil.normalCursor(this);
}


public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	if(e.getActionCommand().equalsIgnoreCase("kind"))//No I18N
	{
	JComboBox typebox = (JComboBox) e.getSource();	
	NmsComboItem item = (NmsComboItem) typebox.getSelectedItem();
	String kind=item.getValue();
	showTypeCard(kind);
	}
	else if(e.getActionCommand().equalsIgnoreCase("browsemib"))//No I18N
	{
		browseMib();
	}
}

private boolean checkForKind(String kind)
{
	try{
	JComboBox cbox = (JComboBox) getComponentWithProp("kind");
	for(int i=0;i<=cbox.getItemCount()-1;i++)
	{
		NmsComboItem item = (NmsComboItem) cbox.getItemAt(i);
		String kind1 = item.getValue();
		if(kind.equalsIgnoreCase(kind1))
			return true;
	}
	}
	catch(Exception e)
	{
		return false;
	}
	return false;
}

public String getOperation()
{
	return operation;
}

public boolean canSetData(Properties props)
{
	setSeverity(props);
	String kind = (String) props.get("kind");
	if(kind==null||!checkForKind(kind))
	{
		JOptionPane.showMessageDialog(this,NmsClientUtil.GetString("javaui.perfgui.threshold.type.error"),NmsClientUtil.GetString("Error Message"),JOptionPane.ERROR_MESSAGE);
        return false;
         
	}
	
	return true;
}

//overriding this method here to allow to set focus on the name property.
public void clearAll()
{
	super.clearAll();
	getComponentWithProp("name").requestFocus();//No I18N
	
}

//overriding this for the cancel action
public void doCancel()
{
	thresholdui.resetAction();
}

}

