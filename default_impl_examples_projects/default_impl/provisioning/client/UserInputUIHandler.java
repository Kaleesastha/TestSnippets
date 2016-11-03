//$Id: UserInputUIHandler.java,v 1.2 2007/02/27 06:03:05 srajeswari Exp $

package com.adventnet.nms.provisioning.ui;

import javax.swing.*;
import java.text.MessageFormat;
import java.awt.*;
import java.lang.*;
import java.util.*;
import java.io.*;
import java.net.URL;

import com.adventnet.nms.provisioning.xml.*;
import com.adventnet.management.config.xml.*;
//import com.adventnet.apiutils.StringUtil;
import com.adventnet.nms.provisioning.ui.ProvClientUtils;
import com.adventnet.nms.provisioning.ui.uielements.XmlUIElement;
import com.adventnet.nms.provisioning.ui.uielements.CustomProvisioningTable;
import com.adventnet.nms.provisioning.ui.uielements.AbstractXmlUIElement;
import com.adventnet.nms.util.NmsClientUtil;

public class UserInputUIHandler
{
	//private Applet appl;
	private ArrayList propKeyList = null;
	private ArrayList propValueList = null;
	private ArrayList uiList = new ArrayList();
	private ArrayList uiElementsList = new ArrayList();

    private int numberOfRequiredFields = 0;

    // To show the panel in TemplateUIPanel whose form is incomplete, the following variables are used.
    // This hashtable will contain XmlUIElements and the Panels in which they are present.
    private Hashtable elementsAndPanels = new Hashtable();

    // The panel that contains the XmlUIElement whose value should not be empty.
    private JPanel incompletePanel;

    // The error message that should be displayed in the JOptionPane. This message will be displayed by the
    // TemplateUIPanel.
    private String errorMessage = "";

	private static Properties typeToClassMappingProp;

	private JTextArea ta;	 	
	private static String mappingTxt = "TypeToUIElementMapping.txt";	



	public JPanel getPanelForProperties(Properties propsArg)
	{
		//clear();
		propKeyList = new ArrayList();
		propValueList = new ArrayList();
		for(Enumeration e = propsArg.propertyNames() ;e.hasMoreElements();)
		{
			String name = (String)e.nextElement();
			String value = (String)propsArg.getProperty(name);
			XmlUIElement el = getXmlUIElementFor("textfield");
			if(el != null)
			{
				propKeyList.add(name);
				el.setValue(value);
				if (name.endsWith("#"))
				{
					el.setLabelName(name.substring(0,name.length()-1));
				}
				else
				{
					el.setLabelName(name);
				}
				propValueList.add(el);
			}
		}
		return getPanelFor(propValueList);
	}


	public JPanel getPanelFor(Form f) throws InvalidTemplateException
	{
		//clear();by jai
		//uiList = new ArrayList();
		//uiElementsList = new ArrayList();
        ArrayList list = getXmlUIElements(f);
		Vector tables = f.getTables();
		if(tables.size() == 0)
		{
			return getPanelFor(list);
		}
		else
		{
			Table table = (Table)tables.elementAt(0);
			return getPanelWithTable(list,table);
		}
	}

    public ArrayList getXmlUIElements(Form f) throws InvalidTemplateException
    {	
        Vector v = f.getUserInputs();
		Vector userInputVecArg = new Vector();
        int size = v.size();
        for(int i = 0 ; i < size ; i++)
        {
            UserInput ui = (UserInput)v.elementAt(i);
            String satisfied = ui.getAttribute("satisfied");
            if(satisfied == null || (!satisfied.equals("false")))
            {
                userInputVecArg.addElement(ui);
            }
        }
		ArrayList list = new ArrayList(userInputVecArg.size());		
		for(int i = 0, j = userInputVecArg.size(); i < j; i++)
		{
			UserInput ui = (UserInput)userInputVecArg.elementAt(i);
			uiList.add(ui);
			XmlUIElement el = getXmlElement(ui);
			uiElementsList.add(el);
			list.add(el);
		}
        return list;
    }

    
	private JPanel getPanelWithTable(ArrayList list,Table element) throws InvalidTemplateException
	{
        JTable table = getTable(list,element);
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(new JScrollPane(table));
        for(int j = 0 ; j < list.size() ; j++)
        {
            elementsAndPanels.put(list.get(j),panel);
        }
		return panel;
	}

    public JTable getTable(ArrayList list,Table element) throws InvalidTemplateException
    {	
        int rows = element.getRowCount();
        int columns = element.getColumnCount();
        int[] widths = element.getColumnWidths();
        int size = element.getUserInputs().size();
        if(list.size() == 0) return new JTable();

        CustomProvisioningTable tableElement = new CustomProvisioningTable();
        JTable table = tableElement.getTable(list,rows,columns,widths);
        return table;
    }


	public void clear()
	{
		uiList.clear();
		uiElementsList.clear();
        elementsAndPanels.clear();
        numberOfRequiredFields = 0;
	}

    public int getNumberOfRequiredFields()
    {
        return numberOfRequiredFields;
    }

	public Properties getUserInputValues()
	{
		Properties prop = new Properties();
		for(int i = 0; i < uiList.size();i++)
		{
			UserInput ui = (UserInput)uiList.get(i);
			XmlUIElement el = (XmlUIElement)uiElementsList.get(i);
			ui.setValue(el.getValue());
			prop.put("$UserInput$" + ui.getID(),el.getValue());
		}   
		return prop;
	}

    public boolean areInputValuesValid()
    {
        for(int i = 0; i < uiList.size();i++)
        {
            XmlUIElement el = (XmlUIElement)uiElementsList.get(i);
            try
            {
                el.checkConstraints();
            }
            catch(Exception e)
            {
                errorMessage = e.getMessage();
                incompletePanel = (JPanel) elementsAndPanels.get(el);
                return false;
            }
            if ((el.isRequired())&&(el.isValueNull()))
            {
                errorMessage = MessageFormat.format(ProvClientUtils.getString("{0} field cannot be empty"), new Object[]{el.getLabelName()});
                incompletePanel = (JPanel) elementsAndPanels.get(el);
                return false;
            }
        }
        return true;
    }

    private void showErrorMessage(String message)
    {
        JOptionPane.showMessageDialog(null,message,ProvClientUtils.getString("Error"),JOptionPane.ERROR_MESSAGE);
    }
    
    public String getErrorMessage()
    {
        return errorMessage;
    }
    
    // The method is called by TemplateUIPanel to find out the Form that is incomplete.
    public JPanel getIncompletePanel()
    {
        return incompletePanel;
    }
  public Properties getPropertiesValue()
	{
		Properties props = new Properties();
		for(int i = 0; i < propKeyList.size();i++)
		{
			String propName = (String)propKeyList.get(i);
			//Since $Template$Params is being appended (before replacement)in the server side latest code 
			//it is removed here using substring(15). 
			//If not removed, this results in $Template$Params$Template$ParamsSource as the key to be replaced by the user specified value.
			//Check to be introduced in server side.
			//propName = propName.trim().substring(15);
			XmlUIElement el = (XmlUIElement)propValueList.get(i);
			props.put(propName,el.getValue());
		}   
		return props;        
	}



	public XmlUIElement getXmlElement(UserInput uiArg)
	{
		String type = "textfield";
		Qualifier qual = uiArg.getQualifier();
		String enumNames[] = null;
		String enumValues[] = null;
		if(qual != null)
		{
			type = qual.getType();
			Vector enumVec = qual.getEnums();
			if(enumVec.size() > 0)
			{
				enumNames = new String[enumVec.size()];
				enumValues = new String[enumVec.size()];
				for (int i = 0; i < enumNames.length; i++) 
				{
					com.adventnet.management.config.xml.Enum e = (com.adventnet.management.config.xml.Enum)enumVec.elementAt(i);
					enumNames[i] = e.getName();
					enumValues[i] = e.getValue();
					if(enumNames[i] == null)
					{
						enumNames[i] = enumValues[i];
					}
				} 
			}
		}
		XmlUIElement el = getXmlUIElementFor(type);
		if(el == null)
		{
			return null;
		}
		el.setDescription(uiArg.getDescription());
		if(enumNames != null)
		{
			el.setEnumeratedValues(enumNames,enumValues);
		}
		if((qual!=null)&&(qual.getRange()!=null)&&(!(qual.getRange().equals(""))))
		{
		  el.setRange(qual.getRange());
		}
		if(uiArg.getDefaultValue()!=null)
		{
		  el.setValue(uiArg.getDefaultValue());
		}	
		String isEditable = uiArg.getAttribute("editable");
		if(isEditable != null )
		{
			if(isEditable.trim().equals("false"))
			{
				el.setEditable(false);	
			}
			else
			{
				el.setEditable(true);	
			}
		}
		String required = uiArg.getAttribute("required");
		{
		  if (required.trim().equals("true"))
			{
			  el.setRequired(true);
				numberOfRequiredFields++;
			}
			else
			{
			  el.setRequired(false);
			}	
		}
		el.setLabelName(uiArg.getLabel());
		return el;
	}


	private XmlUIElement getXmlUIElementFor(String typeArg)
	{
		if(typeToClassMappingProp == null)
		{
			setUpMappingsHM();
		}
		String clsName = (String)typeToClassMappingProp.get(typeArg);
		if((clsName != null) 
				&& !(clsName.equals("*NOTFOUND*"))
				&& !(clsName.equals("*EXCEPTION*")))
		{
			try
			{
				Class cls = Class.forName(clsName);
				return (XmlUIElement) cls.newInstance();
			}
			catch(Throwable th)
			{
				typeToClassMappingProp.put(typeArg,"*EXCEPTION*");	
				showErrorMessage(MessageFormat.format(ProvClientUtils.getString("{0} occurred when trying to get the XmlUIElement for type : {1}"), new Object[]{th.getClass().getName(),typeArg}));
				th.printStackTrace();
				return null;
			}
		}   
		else if(clsName == null)
		{
			typeToClassMappingProp.put(typeArg,"*NOTFOUND*");	
			showErrorMessage(MessageFormat.format(ProvClientUtils.getString("The type {0} does not have the corresponding XMLUIElement specified in the TypeToUIElementMapping.txt file"), new Object[]{typeArg}));
		}	
		return null;		     
	}

	private void setUpMappingsHM()
	{
		typeToClassMappingProp = new Properties();
		URL url = UserInputUIHandler.class.getClassLoader().getResource(mappingTxt);
		if(url == null)
		{	
			showErrorMessage(ProvClientUtils.getString("TypeToUIElementMapping.txt file is not found"));
			return;
		}
		try
		{	
			typeToClassMappingProp.load(url.openStream());
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
	}


	/** Get the panel for a given form.  **/
	public  JPanel getPanelFor(ArrayList elements)
	{
		JPanel p = new JPanel(new GridBagLayout());
		int maxCols = 1;
		int elementSize = elements.size();
		for (int i= 0;i<elementSize;i++)
		{ // count max number of cols
			//((XmlUIElement)elements.get(i)).setEditable(true);//by jai
			int cols = ((XmlUIElement)elements.get(i)).getNumberOfColumns();
			if(cols > maxCols)
			{
				maxCols = cols;
			}
		}
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH; c.weightx = 1.0;
		c.gridx = 0; c.gridy = 0;
		if (elementSize < 5 )
			c.insets = new Insets(8,8,8,14);
		else if (elementSize < 20 )
			c.insets = new Insets(4,4,4,10);
		else if ((elementSize > 40 && maxCols == 2) || (elementSize > 80 &&  maxCols == 4))
			c.insets = new Insets(1,1,1,8);
		else
			c.insets = new Insets(2,2,2,8);

		int rowsAdded = 0;
		for (int i=0;i<elementSize;i++) 
		{
            elementsAndPanels.put(elements.get(i),p);
			rowsAdded += ((XmlUIElement)elements.get(i)).addComponents(p,c,0,rowsAdded,maxCols);
		}
		return p;
	}


	/** This replaces the user input variables in the XML template **/
	public String replaceVariablesInString(String xmlStringArg)
	{
		String modXmlString = xmlStringArg;
		Properties prop = new Properties();
		for(int i = 0; i < uiList.size();i++)
		{
			UserInput ui = (UserInput)uiList.get(i);
			XmlUIElement el = (XmlUIElement)uiElementsList.get(i);
			ui.setValue(el.getValue());
			prop.put("$UserInput$"+ui.getID(), el.getValue());
			//modXmlString = StringUtil.replaceStringBySpecifiedString(modXmlString,"$UserInput$" +  ui.getID(),el.getValue());
		}
		Template template = null;
		try
		{
			template = new Template(xmlStringArg);
//			template = PopulateTemplateParams.substituteParams(template, prop, 3);
			template = PopulateTemplateParams.substituteParams(template, prop);
		}
		catch(Exception exc)
		{
			exc.printStackTrace();
		}
		//return modXmlString;
		return template.toString();
	}
}
