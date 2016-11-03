//$Id: PropertyFormCustomizer.java,v 1.8.10.1 2012/01/25 05:12:46 karen.r Exp $
package com.adventnet.nms.mapui;

//java imports

//swing imports
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import javax.swing.text.*;
import javax.swing.JDialog;

import java.awt.*;
import java.awt.event.*;
import java.awt.GridLayout;
import java.util.*;
import java.net.URL;

//WebNMS imports
import com.adventnet.nms.util.*;
import com.adventnet.nms.severity.*;
import com.adventnet.nms.startclient.*;

//XML import
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;
import javax.xml.parsers.*;


public class PropertyFormCustomizer extends GroupedDataFormCustomizer implements ActionListener
{
    // Vector of form objects sent from nms for display
    Vector formObjects  = null;
    Vector mapCriteria=null;
    Hashtable groupHash=null;
    JFrame parentFrame  = null;
    JDialog dialog      = null;
    private Document doc=null;
     private static int count = 0;
    public PropertyFormCustomizer()
    {
    }

    //DataFormCustomizer
    public Vector getFormProperties()
    {
	return this.formObjects;
    }

    public void setFormProperties(Vector formObjects)
    {
	this.formObjects = formObjects;
    }
    
    //Grouped DataForm customizer Only for Map properties
    public Hashtable getGroupFormProperties(){
    	return groupHash;
    }
    public void setGroupFormProperties(Hashtable hash){
         this.groupHash=hash;
    }
    
    public Window getCustomizer()
    {
		return init();
    }

    private Window init()
    {
    	parentFrame = getParentFrame();
    	String propertyForm=getFormName();
    	NmsPanel panel=getParentPanel();

    	String xmlFile = getPropertyXmlFile(propertyForm,panel);

    	dialog = initializePropertyDialog(formObjects,xmlFile);

    	return dialog;
    }
    
    /*
     * The method will retrieve the property file name based on which the properties 
     * will be shown in the property dialog.
     */
    public String getPropertyXmlFile(String propertyForm,NmsPanel panel)
    {
    	String xmlFile=null;
    	if(propertyForm.equals("MANAGED-OBJECT-FORM")){
    		xmlFile="conf/MOPropertyForm.xml";
    	}
    	else if(panel instanceof MapApplet){
    		MapApplet applet=(MapApplet)panel;
    		int selectedSymbols=applet.getSelectedSymbols().size();
    		if(selectedSymbols>0){
    			//Form invoked from a MapSymbolComponent;   
    			MapSymbolComponent comp=(MapSymbolComponent)applet.getSelectedSymbols().elementAt(count++);

    			if(selectedSymbols==count){
    				//Reset the count.
    				count=0;
    			}
    			if(comp instanceof MapLinkComponent){
    				xmlFile="conf/LinkPropertyForm.xml";
    			}
    			else if(comp instanceof MapContainerComponent){
    				xmlFile="conf/ContainerPropertyForm.xml";
    			}
    			else{
    				xmlFile="conf/SymbolPropertyForm.xml";
    			}
    		}

    		if (this.formObjects == null){
    			this.formObjects=(Vector)groupHash.get("MAP_PROPERTIES");
    			this.mapCriteria=(Vector)groupHash.get("MAP_CRITERIA_PROPERTIES");
    			//Form invoked from Map
    			xmlFile="conf/MapPropertyForm.xml";
    		}
    	}
    	return xmlFile;
    }
    
    /*
     * The method is used to initialize the property dialog.
     */
    public JDialog initializePropertyDialog(Vector formObjects,String xmlFile)
    {
    	if(this.formObjects == null)
    	{
    		dialog = getMessageDialog("No data available");//No Internationalisation
    	}
    	else
    	{
    		doc=constructXML(xmlFile); 
    		if(doc==null){
    			System.err.println("Error while parsing XML file "+xmlFile);
    			return null;
    		} 
    		dialog = new PropertyDialog(parentFrame,(ActionListener)this,doc);
    	}
    	return dialog;
    }

    public Document constructXML(String xmlFile){
	if(xmlFile==null){
		return null;
	}
         Document doc=parsePropertyXML(xmlFile);
         if(doc==null){
              return null;
         }
         
         Properties objProperties=getObjectProperties(formObjects);
         
         
         //Set the current value of the properties.
         Element root = getRowFromPropertyFile(doc,objProperties);
         
         //add the extra properties under other properties
         //Remove SNMP group for non snmp devices.
         //Add criteria under MapCriteria group.
         
         updateOtherTabProperties(root,objProperties);

         return doc;
    }
    
    private void updateOtherTabProperties(Element root,Properties objProperties)
    {
    	String isSNMP=(String)objProperties.get("isSNMP");
    	NodeList groupList=root.getElementsByTagName("GROUP");//No I18N
        int groupCount=groupList.getLength();
        for(int j=groupCount-1;j>=0;j--){
             Element group=(Element)groupList.item(j);
             String groupName=group.getAttribute("name");
             if(groupName.equalsIgnoreCase("javaclient.propform.othertab")){ //This key corresponds to Others tab.
                  //Remove stringstatus as we already added status
                  objProperties.remove("stringstatus");//No I18N
                  updateProperties(group,objProperties);
             }
             else if(groupName.equalsIgnoreCase("javaclient.propform.mo.tab4")){ //This key corresponds to SNMP tab.
			if(isSNMP==null || isSNMP.equalsIgnoreCase("false")){
	                   root.removeChild(group);
			}
             }
             else if(groupName.equalsIgnoreCase("javaclient.propform.map.criteriatab")){//This key corresponds to criteria tab
                  if(mapCriteria==null || mapCriteria.size()==0){
                       //If no MapCriteria then remove the group
                       root.removeChild(group);
                  }
                  else{
                        Properties criteriaProperties=getObjectProperties(mapCriteria);
                        updateProperties(group,criteriaProperties);
                        //Special case to handle criteria property
                        //If this group is a criteria group then Edit button
                        //will be enabled in the PropertyPanel
                        group.setAttribute("type","criteria");
                  }
             }
        }
    }
    
    public Element getRowFromPropertyFile(Document doc,Properties objProperties)
    {
    	Element root=doc.getDocumentElement();
    	NodeList propertyList=root.getElementsByTagName("ROW");
    	int propertyLength=propertyList.getLength();
    	for(int i=0;i<propertyLength;i++){
    		Element property=(Element)propertyList.item(i);
    		if(property.getAttribute("type").equalsIgnoreCase("property")){

    			String propertyName=property.getAttribute("name");
    			String currentValue=(String)objProperties.remove(propertyName);

    			//ObjectType of mapsymbol will come as integer which needs to be converted to string
    			if(propertyName.equalsIgnoreCase("objtype")){
    				try{
    					currentValue=MapSymbolComponent.getStrObjType(Integer.parseInt(currentValue));
    				}
    				catch(NumberFormatException nfe){
    					System.err.println("Error while parsing the value "+nfe.getMessage());
    				}

    				String possibleValues = getObjTypeValue(propertyName,property,objProperties);

    				property.setAttribute("possibleValues",possibleValues);
    			}

    			//Status will be integer which will be converted to String
    			if(propertyName.equalsIgnoreCase("status")){
    				try{
    					currentValue=NmsClientUtil.severityInfo.getName(Integer.parseInt(currentValue));
    				}
    				catch(NumberFormatException nfe){
    					//System.err.println("Error while parsing the value "+nfe.getMessage());
    				}
    			}

    			//For current topology we need to list all the possible topologies in the combo
    			if(propertyName.equalsIgnoreCase("currenttopology")){
    				String possibleValues=(String)objProperties.remove("topology");	
    				if(possibleValues!=null){
    					possibleValues = getCurrentTopologyValue(possibleValues);
    				}
    				else{
    					possibleValues = getAllTopologyValue(possibleValues);  
    				}
    				property.setAttribute("possibleValues",possibleValues);
    			}

    			if(currentValue!=null){
    				property.setAttribute("currentValue",currentValue);
    			}
    		}
    	}
    	return root;
    }
    
    public String getObjTypeValue(String propertyName,Element property,Properties objProperties)
    {
    	String possibleValues="";
    	for(Enumeration en = MapSymbolComponent.objTypes.keys();en.hasMoreElements();)
    	{
    		String objType = (String)en.nextElement();
    		possibleValues=possibleValues+objType+",";//No I18N
    	}
    	return possibleValues;
    }
    
    public String getCurrentTopologyValue(String possibleValues)
    {
    	int beginIndex = possibleValues.indexOf("(");
		int endIndex = possibleValues.indexOf(")");
		int length = possibleValues.length();
            
        while (beginIndex > -1 &&  endIndex > -1) {
        	String firstPart = possibleValues.substring(0,beginIndex);
        	String secondPart =  possibleValues.substring(endIndex + 1, length);
            possibleValues = firstPart + secondPart;
			beginIndex = possibleValues.indexOf("(");
			endIndex = possibleValues.indexOf(")");
			length = possibleValues.length();
        }
        
		int dollarIndex = possibleValues.indexOf("$");
		length = possibleValues.length();
		while (dollarIndex > -1) {
			possibleValues = possibleValues.substring(0,dollarIndex) + possibleValues.substring(dollarIndex+1,length);
			length = possibleValues.length();
			dollarIndex = possibleValues.indexOf("$");
		}
		return possibleValues;
    }
    
    public String getAllTopologyValue(String possibleValues)
    {
    	for(Enumeration en = MapClientInitializer.mapLayouts.keys();en.hasMoreElements();)
    	{
    		String layout = (String)en.nextElement();
    		possibleValues=possibleValues+layout+",";
    	}
    	return possibleValues;
    }
    
    /*
     * The properties are added as a single row in the others tab.
     */
    public void updateProperties(Element group,Properties objProperties){
    	
    	ArrayList<String> array = handleSorting(objProperties);
    	Document ownerDoc=group.getOwnerDocument();
    	Element row = null;
    	int size = array.size();
    	for(int i=0;i<size;i++)
    	{
              String key=array.get(i);
              String value=(String)objProperties.get(key);     
              row = getObjectRow(key,value,ownerDoc);
              group.appendChild(row);
         }
    }
    
    /*
     * The handleSorting method needs to be overriden to perform sorting of the properties that are shown in the other tab of the form.
     * The keys of the object properties needs to be sorted and returned in the form of an ArrayList.
    */
    public ArrayList<String> handleSorting(Properties objProperties)
    {
    	int index = 0;
    	ArrayList<String> array = new ArrayList<String>();
    	Enumeration enm = objProperties.keys();
    	while(enm.hasMoreElements())
    	{
    		String val = (String)enm.nextElement();
    		array.add(val);
    	}
    	return array;
    }
    
    /*
     * The getObjectRow method is used to set the attributes for each property. In case the user wants to set the rows as 
     * editable or if the user needs to add label or text field this method can be overriden.
     */
    public Element getObjectRow(String key,String value,Document ownerDoc)
    {
    	
    	Element row=ownerDoc.createElement("ROW");
        row.setAttribute("type","property");
        row.setAttribute("name",key);
        //Meaningful string for discoveryStatus. Patch integration 26986
        if (key.equalsIgnoreCase("discoveryStatus"))//No internationalization
        {
      	  int intValue = Integer.parseInt(value);
      	  switch (intValue)
      	  {
      	  case 1: value = NmsClientUtil.GetString("javaui.moprops.discstatus.yettobegin");break;
      	  case 2: value = NmsClientUtil.GetString("javaui.moprops.discstatus.inprogress");break;
      	  case 3: value = NmsClientUtil.GetString("javaui.moprops.discstatus.finished");break;
      	  case 4: value = NmsClientUtil.GetString("javaui.moprops.discstatus.disabled");break;
      	  }
        }         
        row.setAttribute("displayName",key);
        row.setAttribute("currentValue",value);
        row.setAttribute("isEditable","true");
        row.setAttribute("component","textfield");
        if(key.equalsIgnoreCase("nodelist")){
               row.setAttribute("isEditable","false");
        row.setAttribute("component","list");
        }
        return row;
    }
    
    private JDialog getMessageDialog(String message)
    {
		JOptionPane optionPane = new JOptionPane(message, JOptionPane.INFORMATION_MESSAGE);
		return optionPane.createDialog(parentFrame, "Message");//No Internationalisation
    }

    public Properties getObjectProperties(Vector obj)
    {
	    Properties objProperties=new Properties();
	    int noOfProps = obj.size();
	    for(int i = 0;i < noOfProps; i++)
	    {
		    FormObject formObject = (FormObject)obj.elementAt(i);
		    String propName = formObject.getPropertyName().toString().trim();
		    String propVal = (String)formObject.getCurrentValue();
		    objProperties.put(propName,propVal);
	    }
	   return objProperties;
    }
    //ActionListener method
    public void actionPerformed(ActionEvent ae)
    {

        //Update the XML DOC with new values	
        ((PropertyDialog)dialog).updateXML();
    
        int noOfProps = formObjects.size();
        boolean isPropChanged = false;
        Properties currProp =getProperties("currentValue");//No I18N
	for(int i = noOfProps-1;i >=0; i--)
	{
		FormObject formObject = (FormObject)formObjects.elementAt(i);
		String propName = formObject.getPropertyName().toString();
		String currVal = currProp.getProperty(propName);

		//Convert the String objType to its int value.
		if(propName.equalsIgnoreCase("objtype")){
                  currVal=MapSymbolComponent.getIntObjType(currVal)+"";
             }
             String prevVal = (String)formObject.getCurrentValue();
             //Convert the String status to its int value.
             if(propName.equalsIgnoreCase("status")){

                  try{
                       //This is to confirm whether the original value is in int
                       //In NetworkDBVIew the status will come as string instead of int.
                       //In those casees NumberFormatException will occur and no need
                       //to convert the string to int.
                       int currStatus=Integer.parseInt(prevVal); 
                       currVal=NmsClientUtil.severityInfo.getValue(currVal)+"";
                  }
                  catch(Exception e){
                  }

             }
             
             if(propName.equalsIgnoreCase("discoveryStatus"))
             {
             	//here we need to check the discoveryStatus string and then convert it to corresponding integer.
             	if(currVal.equals(NmsClientUtil.GetString("javaui.moprops.discstatus.yettobegin")))
             	{
             		currVal="1";//No Internationalization
             	}else if(currVal.equals(NmsClientUtil.GetString("javaui.moprops.discstatus.inprogress")))
             	{
             		currVal="2";//No Internationalization
             	}else if(currVal.equals(NmsClientUtil.GetString("javaui.moprops.discstatus.finished")))
             	{
             		currVal="3";//No Internationalization
             	}else if(currVal.equals(NmsClientUtil.GetString("javaui.moprops.discstatus.disabled")))
             	{
             		currVal="4";//No Internationalization
             	}
		else
		{
			JOptionPane.showMessageDialog(null,NmsClientUtil.GetString("javaui.moprops.discstatus.invalidentry"),NmsClientUtil.GetString("javaui.moprops.dialog.invaliddiscstatus"),JOptionPane.ERROR_MESSAGE);
			return;
		}
             }

	   if(currVal !=null &&  !((currVal.trim()).equals(prevVal.trim())))
           {
                isPropChanged = true;
		//Before setting values in formobject make it editable
		formObject.setEditable(true);
		formObject.setCurrentValue(currVal);
		continue;
            }
	    //Remove the unmodified properties except name	
	    if(!propName.equals("name")){
     		formObjects.removeElementAt(i);
	    }
        }
        
        
        //Check if any Mapcriteria changed
        if(mapCriteria!=null && mapCriteria.size()>0){
             mapCriteria.removeAllElements();
             Properties criteriaProps=getCriteriaProperties();
             for(Enumeration e=criteriaProps.keys();e.hasMoreElements();){
                  String key=(String)e.nextElement();
                  String value=(String)criteriaProps.get(key);
                  FormObject obj=new FormObject(key,null,true,value);
                  mapCriteria.add(obj);
             }
   	     isPropChanged = true;
        }

        
//        if(isPropChanged)
        {
             //Add an extra formobject to make changes in server
             String isSave=doc.getDocumentElement().getAttribute("save");
             if(!isSave.trim().equals("")){
                  FormObject obj=new FormObject("Save Changes On Server",null,true,isSave);
                  formObjects.add(obj);
                  
             }
            
            firePropertyChange();
        }

	dialog.dispose();
    }


 private Properties getCriteriaProperties(){
      Element root=doc.getDocumentElement();
      NodeList groupList=root.getElementsByTagName("GROUP");
      int count=groupList.getLength();
      Properties criteria=new Properties();
      for(int i=0;i<count;i++){
           Element group=(Element)groupList.item(i);
           if(group.getAttribute("type").equalsIgnoreCase("criteria")){
                NodeList rows=group.getElementsByTagName("ROW");
                for(int j=0;j<rows.getLength();j++){
                     
                     Element row=(Element)rows.item(j);
                     if(!row.getAttribute("type").equals("property")){
                          continue;
                     }
                     criteria.put(row.getAttribute("name"),row.getAttribute("currentValue"));
                }
           }
      }
      return criteria;
 }

    private Document parsePropertyXML(String xmlFileName){
      try
        {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
	    int port = NmsClientUtil.applet.getCodeBase().getPort();
	    String host = NmsClientUtil.applet.getCodeBase().getHost();
 	    String  strProtocolName = NmsClientUtil.applet.getCodeBase().getProtocol();
	    URL url = new URL(strProtocolName + "://" + host + ":" + String.valueOf(port) +"/"+xmlFileName);
            Document doc = docBuilder.parse(url.openStream());
            return doc;
        }
        catch(Exception exp)
        {
            System.err.println(exp.toString());
        }
        return null;
  }
    private Properties getProperties(String attribute){
	    Properties prop=new Properties();
	    Element root=doc.getDocumentElement();
	    NodeList groupList=root.getElementsByTagName("GROUP");
	    int count=groupList.getLength();
	    for(int i=0;i<count;i++){
		    Element group=(Element)groupList.item(i);
		    //Avoid criteria props
		    if(group.getAttribute("type").equalsIgnoreCase("criteria")){
			    continue;
		    }
		    NodeList rowList=group.getElementsByTagName("ROW");
		    int rowLength=rowList.getLength();
		    for(int j=0;j<rowLength;j++){
			    Element row=(Element)rowList.item(j);
			    if(!row.getAttribute("type").equalsIgnoreCase("property")){
				    continue;
			    }
			    String key=row.getAttribute("name");
			    String value=row.getAttribute(attribute);
			    prop.put(key,value);            
		    }
	    }
	    return prop;
    }
}












