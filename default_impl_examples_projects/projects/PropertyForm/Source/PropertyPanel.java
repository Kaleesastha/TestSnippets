//$Id: PropertyPanel.java,v 1.2 2008/05/07 13:03:58 sureshm Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// Kindly refer to tcats_edit_source_files.html file under help/source_generation directory for details




//<Begin_Version>
//version$12
//<End_Version>
package com.adventnet.nms.mapui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import org.w3c.dom.*;
import com.adventnet.nms.util.NmsClientUtil;
import com.adventnet.nms.util.BrowseComponent;
import java.util.*;
public class PropertyPanel extends JPanel  implements ActionListener,com.adventnet.apiutils.ParameterChangeListener 
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	javax.swing.JPanel parentPanel = null;
	javax.swing.JPanel editPanel = null;
	javax.swing.JButton editButton = null;
	private com.adventnet.apiutils.ParameterObject po= null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>
	Hashtable propertyVsComponent=null;
	Element group=null;

  

  


  


 

  

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
        if (initialized) return; 
        setPreferredSize(new Dimension(getPreferredSize().width+381,getPreferredSize().height+319)); 
        setSize(getPreferredSize()); 
        Container container = this;
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

            if(!group.getAttribute("type").equalsIgnoreCase("criteria")){
		 editPanel.setVisible(false);
             
           } 
  } 
  public String getParameter(String input)
  {
           //<Begin_getParameter_String> 
	if (po != null) 
	 { 
	   if(po.getParameter(input) != null) 
	     {
	       return (String)po.getParameter(input); 
	     }
	 }
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
  //<Begin_setUpProperties> 

          try
          {
            editButton.setFont(new Font("SansSerif",0,12));
            editButton.setHorizontalTextPosition(4);
            editButton.setText("Edit");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+editButton,ex); 
          }

//<UserCode_Begin_Bean_editButton>
editButton.setActionCommand("edit");
editButton.addActionListener(this);
editButton.setText(NmsClientUtil.GetString("javaclient.propform.map.editbutton"));
//<UserCode_End_Bean_editButton>

  
          //<End_setUpProperties>
  } 
  public void initVariables()
  {
        //<Begin_initVariables> 
	 if(po == null)
	{
	po = new com.adventnet.apiutils.ParameterObject();
	}
        Top= new javax.swing.JPanel();
        JScrollPane1= new javax.swing.JScrollPane();
        parentPanel= new javax.swing.JPanel();
        editPanel= new javax.swing.JPanel();
        editButton= new javax.swing.JButton();
        initializeParameters(); 

  
          //<End_initVariables>
  } 
  public void setUpGUI(Container container)
  {
//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));
Top.add(JScrollPane1,BorderLayout.CENTER);
JScrollPane1.getViewport().add(parentPanel);
parentPanel.setLayout(new GridBagLayout());
Top.add(editPanel,BorderLayout.SOUTH);
editPanel.setLayout(new FlowLayout(2,0,5));
editPanel.add(editButton);

  
//<End_setUpGUI_Container>


  } 
  public void setUpConnections()
  {
  //<Begin_setUpConnections> 

  
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
     ex.printStackTrace();
     //<End_showStatus_String_Exception>
  }





  

   
  public void setProperties(java.util.Properties props)
  {
         //<Begin_setProperties_java.util.Properties> 
	if(po != null)
	{
	po.setParameters(props);
	}
  
         //<End_setProperties_java.util.Properties>
  } 
  private void initializeParameters()
  {
          //<Begin_initializeParameters> 
	 if(po != null) 
	   {
	    po.addParameterChangeListener(this);
	   }

  
          //<End_initializeParameters>
  } 
  public void destroy()
  {
         //<Begin_destroy> 
	if(po != null)
	{
	 po.removeParameterChangeListener(this);
	}
  
         //<End_destroy>
  } 
  public void setParameterObject(com.adventnet.apiutils.ParameterObject paramObj)
  {
         //<Begin_setParameterObject_com.adventnet.apiutils.ParameterObject> 
	this.po=paramObj;
	initializeParameters();
  
         //<End_setParameterObject_com.adventnet.apiutils.ParameterObject>
  } 
  public void parameterChanged(com.adventnet.apiutils.ParameterObject paramObj)
  {
  //<Begin_parameterChanged_com.adventnet.apiutils.ParameterObject> 

  
  //<End_parameterChanged_com.adventnet.apiutils.ParameterObject>
  }


public PropertyPanel(Element group){
     this.group=group;
     this.init();
     constructGUI();
    
}


  public PropertyPanel()
  {
    //<Begin_PropertyPanel>
    this.init();
  
    //<End_PropertyPanel>
  }

  public PropertyPanel(java.applet.Applet applet)
  {
    //<Begin_PropertyPanel_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_PropertyPanel_java.applet.Applet>
  }

 
  
private void constructGUI(){
	propertyVsComponent=new Hashtable();
	NodeList rowList=group.getElementsByTagName("ROW");
	int rowCount=rowList.getLength();
	for(int i=0;i<rowCount;i++){
	     Element row=(Element)rowList.item(i);
	     String type=row.getAttribute("type");
	     String displayName=row.getAttribute("displayName");
	     String component=row.getAttribute("component");
	     String isEditable=row.getAttribute("isEditable");
	     String currentValue=row.getAttribute("currentValue");
	     String propName=row.getAttribute("name");
	     boolean editable=isEditable.equalsIgnoreCase("true")?true:false;
	     //First Column Start 
	     JLabel label=new JLabel();
	     label.setText(NmsClientUtil.GetString(displayName));
	     if(type.equals("divider")){
	          label.setFont(new Font((NmsClientUtil.getFont()).getName(),Font.BOLD,(NmsClientUtil.getFont()).getSize()));
          	          inset = new Insets(10,5,5,5);
	     }
	     else{
	          label.setFont(new Font((NmsClientUtil.getFont()).getName(),Font.PLAIN,(NmsClientUtil.getFont()).getSize()));	          
                     inset = new Insets(5,5,5,5);
	     }

	     setConstraints(0,i,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
	     parentPanel.add(label,cons);
	     //First Column End 
	     
	     //Second Column Start
                inset = new Insets(5,5,5,5);
	     JComponent rightComponent=null;
       	     if(type.equals("divider")){
       	          JSeparator separator=new JSeparator();
       	          separator.setForeground(Color.black);
	          rightComponent=separator;
          	          inset = new Insets(10,5,5,5);
	     }
	     else if(component.equalsIgnoreCase("label")){
	          JLabel lab=new JLabel();
	          lab.setFont(new Font((NmsClientUtil.getFont()).getName(),Font.PLAIN,(NmsClientUtil.getFont()).getSize()));	              lab.setText(currentValue);
	          rightComponent=lab;
	     }
	     else if(component.equalsIgnoreCase("textfield")){
	          JTextField textField=new JTextField();
	          textField.setEditable(editable);
	          textField.setText(currentValue);
	          rightComponent=textField;
	     }
	     else if(component.equalsIgnoreCase("passwordfield")){
		  JPasswordField passField=new JPasswordField();
		  passField.setEditable(editable);
		  passField.setText(currentValue);
		  rightComponent=passField;		
	     }
	     else if(component.equalsIgnoreCase("checkbox")){	
	          JCheckBox checkBox=new JCheckBox();
	          checkBox.setEnabled(editable);
	          boolean selection=currentValue.equalsIgnoreCase("true")?true:false;
   	          checkBox.setSelected(selection);	          
	          rightComponent=checkBox;

	     }
	     else if(component.equalsIgnoreCase("combobox")){
	          JComboBox combo=new JComboBox();
	          combo.setEditable(editable);
	          String possibleValues=row.getAttribute("possibleValues");
	          Vector v=getValsFromList(possibleValues,",");
	          for(int j=0;j<v.size();j++){
	               combo.addItem(v.elementAt(j));
	          }
	          combo.setSelectedItem(currentValue);
	          if(possibleValues.trim().equals("")){
	               combo.addItem(currentValue);
	          }
	          combo.setFont(new Font((NmsClientUtil.getFont()).getName(),Font.PLAIN,(NmsClientUtil.getFont()).getSize()));	  
	          rightComponent=combo;
	     }
	     else if(component.equalsIgnoreCase("list")){
	               JScrollPane scroll =new JScrollPane();
                          JList list= new JList();
                          scroll.getViewport().add(list);
                          list.setVisibleRowCount(3);
                          Vector v=getValsFromList(currentValue," ");
                	    list.setFont(new Font((NmsClientUtil.getFont()).getName(),Font.PLAIN,(NmsClientUtil.getFont()).getSize()));	  
                          list.setListData(v);
                          rightComponent=scroll;
              }
              else if(component.equalsIgnoreCase("browsecomponent")){
                  String baseDir = row.getAttribute("BaseDirectory"); 
                  Properties p = new Properties(); 
                  p.setProperty("tfText", currentValue); 
                  if (baseDir != null) { 
                      p.setProperty("BaseDirectory", baseDir ); 
                  } 
                  
                  rightComponent =new BrowseComponent   (NmsClientUtil.applet, p); 

              }
	     //Issue ID:3003948 First letter not visible in the text fileds in the mapproperties ui.
	     //setConstraints(1,i,1,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
	     setConstraints(1,i,1,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,1,0);
	     if(rightComponent!=null){
	                
		     parentPanel.add(rightComponent,cons);
	     	     propertyVsComponent.put(propName,rightComponent);		
	     }
	     //Second Column End  
	     
	  
	  
             
	}
	inset = new Insets(5,5,5,5);
	setConstraints(0,rowCount,0,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
	parentPanel.add(new JLabel(),cons);
}

     private Vector getValsFromList(String listString, String delimiter)
    {
        StringTokenizer strTokens = new StringTokenizer(listString, delimiter);
        int count = strTokens.countTokens();
        Vector arra = new Vector(count);
	for (int i = 0; i < count; i++)
        {
            arra.add(strTokens.nextToken());
        }
	return arra;
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
  
  //Updates the group XML with new values
  void updateXML(){
       NodeList rowList=group.getElementsByTagName("ROW");
       int rowLength=rowList.getLength();
       for(int i=0;i<rowLength;i++){
            Element row=(Element)rowList.item(i);
            if(!row.getAttribute("type").equalsIgnoreCase("property")){
		continue;		       
	  }
	  String propName=row.getAttribute("name");
	  String newValue=null;
	  JComponent component=(JComponent)propertyVsComponent.get(propName);
	  if(component instanceof JTextField){
	       newValue=((JTextField)component).getText();
	  }
	  else if(component instanceof JPasswordField){
		newValue=((JPasswordField)component).getText();
	  }
	  else if(component instanceof JCheckBox){
	       newValue = (new Boolean(((JCheckBox)component).isSelected())).toString();
	  }
	  else if(component instanceof JComboBox){
	       newValue=(String)((JComboBox)component).getSelectedItem();
	  }
	  else if(component instanceof BrowseComponent){
	       newValue=((BrowseComponent)component).getValue();
	  }
	    	
  	if(newValue!=null){
  	     row.setAttribute("currentValue",newValue);
  	}
       }
  }
  
  public void actionPerformed(ActionEvent ae){
       if(ae.getActionCommand().equals("edit")){
            AdditionalCriteria criteria=new AdditionalCriteria(group,(JDialog)editButton.getTopLevelAncestor());
            criteria.setVisible(true);
            if(criteria.isModified()){
                 parentPanel.removeAll();
                 constructGUI();
                 parentPanel.updateUI();
            }
       }
  }
}
























