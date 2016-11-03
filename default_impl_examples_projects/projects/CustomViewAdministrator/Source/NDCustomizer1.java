//$Id: NDCustomizer1.java,v 1.2 2007/02/22 15:03:04 srajeswari Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory




package com.adventnet.nms.tools.CustomView;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import com.adventnet.nms.util.*;
import java.util.*;





public class NDCustomizer1 extends JPanel implements ItemListener
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel Prop1 = null;
	javax.swing.JLabel filtername = null;
	javax.swing.JLabel name = null;
	javax.swing.JLabel ipaddress = null;
	javax.swing.JLabel status = null;
	javax.swing.JLabel type = null;
	javax.swing.JLabel issnmp = null;
	javax.swing.JLabel netmask = null;
	javax.swing.JLabel classname = null;
	javax.swing.JLabel managed = null;
	javax.swing.JLabel pollintervel = null;
	javax.swing.JLabel statusupdatetime = null;
	javax.swing.JLabel statuschangetime = null;
	javax.swing.JLabel tester = null;
	javax.swing.JLabel uclass = null;
	javax.swing.JTextField filterNameField = null;
	javax.swing.JTextField nameField = null;
	javax.swing.JTextField ipAddressField = null;
	javax.swing.JTextField typeField = null;
	javax.swing.JTextField netMaskField = null;
	javax.swing.JTextField classNameField = null;
	javax.swing.JTextField pollIntervalField = null;
	javax.swing.JTextField statusUpdateTimeField = null;
	javax.swing.JTextField statusChangeTimeField = null;
	javax.swing.JTextField testerField = null;
	javax.swing.JTextField uclassField = null;
	javax.swing.JComboBox statusCombo = null;
	javax.swing.JComboBox isSnmpCombo = null;
	javax.swing.JComboBox isManageCombo = null;
	javax.swing.JLabel JLabel1 = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>
	Properties customProperty1=null;


  

  


  


 

  

    public void init()
  {
        //<Begin_init> 
        if (initialized == true) return; 
        setPreferredSize(new Dimension(getPreferredSize().width+450,getPreferredSize().height+529)); 
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
        // let us set the initialzed variable to true so  
        // we dont initialize again even if init is called 
        initialized = true; 


  
        //<End_init>
  } 
  public String getParameter(String input)
  {
           //<Begin_getParameter_String> 
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
            Top.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.LineBorder(new Color(-16777216),1),"MO Properties",1,0,new Font("Dialog",0,12),new Color(-16764109)));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+Top,ex); 
          }

          try
          {
            filtername.setHorizontalAlignment(2);
            filtername.setFont(new Font("SansSerif",0,12));
            filtername.setForeground(new Color(-16777216));
            filtername.setHorizontalTextPosition(4);
            filtername.setText("Custom View Name");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+filtername,ex); 
          }

          try
          {
            name.setHorizontalAlignment(2);
            name.setFont(new Font("SansSerif",0,12));
            name.setForeground(new Color(-16777216));
            name.setHorizontalTextPosition(4);
            name.setText("Name");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+name,ex); 
          }

          try
          {
            ipaddress.setHorizontalAlignment(2);
            ipaddress.setFont(new Font("SansSerif",0,12));
            ipaddress.setForeground(new Color(-16777216));
            ipaddress.setHorizontalTextPosition(4);
            ipaddress.setText("IPAddress");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+ipaddress,ex); 
          }

          try
          {
            status.setHorizontalAlignment(2);
            status.setFont(new Font("SansSerif",0,12));
            status.setForeground(new Color(-16777216));
            status.setHorizontalTextPosition(4);
            status.setText("Status");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+status,ex); 
          }

          try
          {
            type.setHorizontalAlignment(2);
            type.setFont(new Font("SansSerif",0,12));
            type.setForeground(new Color(-16777216));
            type.setHorizontalTextPosition(4);
            type.setText("Type");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+type,ex); 
          }

          try
          {
            issnmp.setHorizontalAlignment(2);
            issnmp.setFont(new Font("SansSerif",0,12));
            issnmp.setForeground(new Color(-16777216));
            issnmp.setHorizontalTextPosition(4);
            issnmp.setText("isSNMP");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+issnmp,ex); 
          }

          try
          {
            netmask.setHorizontalAlignment(2);
            netmask.setFont(new Font("SansSerif",0,12));
            netmask.setForeground(new Color(-16777216));
            netmask.setHorizontalTextPosition(4);
            netmask.setText("Netmask");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+netmask,ex); 
          }

          try
          {
            classname.setHorizontalAlignment(2);
            classname.setFont(new Font("SansSerif",0,12));
            classname.setForeground(new Color(-16777216));
            classname.setHorizontalTextPosition(4);
            classname.setText("Classname");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+classname,ex); 
          }

          try
          {
            managed.setHorizontalAlignment(2);
            managed.setFont(new Font("SansSerif",0,12));
            managed.setForeground(new Color(-16777216));
            managed.setHorizontalTextPosition(4);
            managed.setText("Managed");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+managed,ex); 
          }

          try
          {
            pollintervel.setHorizontalAlignment(2);
            pollintervel.setFont(new Font("SansSerif",0,12));
            pollintervel.setForeground(new Color(-16777216));
            pollintervel.setHorizontalTextPosition(4);
            pollintervel.setText("Poll Interval");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+pollintervel,ex); 
          }

          try
          {
            statusupdatetime.setHorizontalAlignment(2);
            statusupdatetime.setFont(new Font("SansSerif",0,12));
            statusupdatetime.setForeground(new Color(-16777216));
            statusupdatetime.setHorizontalTextPosition(4);
            statusupdatetime.setText("StatusUpdateTime");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+statusupdatetime,ex); 
          }

          try
          {
            statuschangetime.setHorizontalAlignment(2);
            statuschangetime.setFont(new Font("SansSerif",0,12));
            statuschangetime.setForeground(new Color(-16777216));
            statuschangetime.setHorizontalTextPosition(4);
            statuschangetime.setText("StatusChangeTime");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+statuschangetime,ex); 
          }

          try
          {
            tester.setHorizontalAlignment(2);
            tester.setFont(new Font("SansSerif",0,12));
            tester.setForeground(new Color(-16777216));
            tester.setHorizontalTextPosition(4);
            tester.setText("Tester");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tester,ex); 
          }

          try
          {
            uclass.setHorizontalAlignment(2);
            uclass.setFont(new Font("SansSerif",0,12));
            uclass.setForeground(new Color(-16777216));
            uclass.setHorizontalTextPosition(4);
            uclass.setText("uclass");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+uclass,ex); 
          }

          try
          {
            filterNameField.setHorizontalAlignment(2);
            filterNameField.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+filterNameField,ex); 
          }

          try
          {
            nameField.setHorizontalAlignment(2);
            nameField.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+nameField,ex); 
          }

          try
          {
            ipAddressField.setHorizontalAlignment(2);
            ipAddressField.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+ipAddressField,ex); 
          }

          try
          {
            typeField.setHorizontalAlignment(2);
            typeField.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+typeField,ex); 
          }

          try
          {
            netMaskField.setHorizontalAlignment(2);
            netMaskField.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+netMaskField,ex); 
          }

          try
          {
            classNameField.setHorizontalAlignment(2);
            classNameField.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+classNameField,ex); 
          }

          try
          {
            pollIntervalField.setHorizontalAlignment(2);
            pollIntervalField.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+pollIntervalField,ex); 
          }

          try
          {
            statusUpdateTimeField.setHorizontalAlignment(2);
            statusUpdateTimeField.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+statusUpdateTimeField,ex); 
          }

          try
          {
            statusChangeTimeField.setHorizontalAlignment(2);
            statusChangeTimeField.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+statusChangeTimeField,ex); 
          }

          try
          {
            testerField.setHorizontalAlignment(2);
            testerField.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+testerField,ex); 
          }

          try
          {
            uclassField.setHorizontalAlignment(2);
            uclassField.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+uclassField,ex); 
          }

          try
          {
            statusCombo.setFont(new Font("SansSerif",0,12));
            statusCombo.setEditable(true);
            statusCombo.setAutoscrolls(false);
            statusCombo.setDoubleBuffered(false);
            statusCombo.setMaximumRowCount(8);
            statusCombo.setSelectedIndex(0);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+statusCombo,ex); 
          }

          try
          {
            isSnmpCombo.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+isSnmpCombo,ex); 
          }

          try
          {
            isManageCombo.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+isManageCombo,ex); 
          }

          try
          {
            JLabel1.setHorizontalAlignment(2);
            JLabel1.setFont(new Font("SansSerif",0,12));
            JLabel1.setForeground(new Color(-16777216));
            JLabel1.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+JLabel1,ex); 
          }

  
          //<End_setUpProperties>
  } 
  public void start()
  {
  //<Begin_start> 

  
  //<End_start>
  } 
  public void stop()
  {
  //<Begin_stop> 

  
  //<End_stop>
  } 
  public void initVariables()
  {
        //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        Prop1= new javax.swing.JPanel();
        filtername= new javax.swing.JLabel();
        name= new javax.swing.JLabel();
        ipaddress= new javax.swing.JLabel();
        status= new javax.swing.JLabel();
        type= new javax.swing.JLabel();
        issnmp= new javax.swing.JLabel();
        netmask= new javax.swing.JLabel();
        classname= new javax.swing.JLabel();
        managed= new javax.swing.JLabel();
        pollintervel= new javax.swing.JLabel();
        statusupdatetime= new javax.swing.JLabel();
        statuschangetime= new javax.swing.JLabel();
        tester= new javax.swing.JLabel();
        uclass= new javax.swing.JLabel();
        filterNameField= new javax.swing.JTextField();
        nameField= new javax.swing.JTextField();
        ipAddressField= new javax.swing.JTextField();
        typeField= new javax.swing.JTextField();
        netMaskField= new javax.swing.JTextField();
        classNameField= new javax.swing.JTextField();
        pollIntervalField= new javax.swing.JTextField();
        statusUpdateTimeField= new javax.swing.JTextField();
        statusChangeTimeField= new javax.swing.JTextField();
        testerField= new javax.swing.JTextField();
        uclassField= new javax.swing.JTextField();
        statusCombo= new javax.swing.JComboBox();
        isSnmpCombo= new javax.swing.JComboBox();
        isManageCombo= new javax.swing.JComboBox();
        JLabel1= new javax.swing.JLabel();

  
        //<End_initVariables>
	Vector v=getSeverity();
	statusCombo.addItem("all");
	statusCombo.addItemListener(this);
	for(int i=0;i<v.size();i++)
	{
		statusCombo.addItem(v.elementAt(i).toString());
	}
	addItems(isSnmpCombo);
	addItems(isManageCombo);
  } 
public void addItems(JComboBox combo)
	{
	combo.addItem("all");
	combo.addItem("true");
	combo.addItem("false");
	}
  public void setUpGUI(Container container)
  {
//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));
Top.add(Prop1,BorderLayout.CENTER);
Prop1.setLayout(new GridBagLayout());
inset = new Insets(15,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
Prop1.add(filtername,cons);
inset = new Insets(5,6,6,6);
setConstraints(0,1,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
Prop1.add(name,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
Prop1.add(ipaddress,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,4,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
Prop1.add(status,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,5,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
Prop1.add(type,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,6,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
Prop1.add(issnmp,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,3,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
Prop1.add(netmask,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,11,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
Prop1.add(classname,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,7,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
Prop1.add(managed,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,8,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
Prop1.add(pollintervel,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,9,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
Prop1.add(statusupdatetime,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,10,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
Prop1.add(statuschangetime,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,12,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
Prop1.add(tester,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,13,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
Prop1.add(uclass,cons);
inset = new Insets(15,5,5,5);
setConstraints(1,0,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Prop1.add(filterNameField,cons);
inset = new Insets(6,6,6,6);
setConstraints(1,1,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Prop1.add(nameField,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,2,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Prop1.add(ipAddressField,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,5,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Prop1.add(typeField,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,3,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Prop1.add(netMaskField,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,11,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Prop1.add(classNameField,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,8,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Prop1.add(pollIntervalField,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,9,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Prop1.add(statusUpdateTimeField,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,10,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Prop1.add(statusChangeTimeField,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,12,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Prop1.add(testerField,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,13,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Prop1.add(uclassField,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,4,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Prop1.add(statusCombo,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,6,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Prop1.add(isSnmpCombo,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,7,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Prop1.add(isManageCombo,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,14,0,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
Prop1.add(JLabel1,cons);

  
//<End_setUpGUI_Container>
  } 
  public void setUpConnections()
  {
  //<Begin_setUpConnections> 

  
  //<End_setUpConnections>
  } 
public Vector getSeverity()
{
		Vector severity=new Vector();
		File f=new File(System.getProperty("user.dir")+File.separator+"conf"+File.separator+"SeverityInfo.conf");
		XMLDataReader reader=new XMLDataReader(f.toString(),false);
		XMLNode root=reader.getRootNode();
		Vector childs=root.getChildNodes();
		for(int i=0;i<childs.size();i++)
		{
			XMLNode child=(XMLNode)childs.elementAt(i);
			if(child!=null&&child.getNodeType()==XMLNode.ELEMENT)
			{
				if(child.getNodeName().equalsIgnoreCase("severity"))
				{
					severity.add(child.getAttribute("ID").toString());
				}
			}
		}
		return severity;
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




  public NDCustomizer1()
  {
    //<Begin_NDCustomizer1>
    this.init();
  
    //<End_NDCustomizer1>
  }

  public NDCustomizer1(java.applet.Applet applet)
  {
    //<Begin_NDCustomizer1_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_NDCustomizer1_java.applet.Applet>
  }
public void itemStateChanged(ItemEvent ie)
{
		if(ie.getSource().equals(statusCombo))
		{
			DefaultComboBoxModel model=(DefaultComboBoxModel)statusCombo.getModel();
			Object item=statusCombo.getSelectedItem();
			 if(model.getIndexOf(item) == -1)
			  {
				  model.addElement(item);
			  }
		}
}
public String getViewName()
{
	return filterNameField.getText();
}
public void serViewName(String s)
{
	filterNameField.setText(s);
}
public Properties getProperties()
{
	customProperty1=new Properties();
	if(!nameField.getText().trim().equals(""))
	{
		customProperty1.put("name",nameField.getText());
	}
	if(!ipAddressField.getText().trim().equals(""))
	{
		customProperty1.put("ipAddress",ipAddressField.getText());
	}
	if(!typeField.getText().trim().equals(""))
	{
		customProperty1.put("type",typeField.getText());
	}
	if(!netMaskField.getText().trim().equals(""))	
	{
		customProperty1.put("netmask",netMaskField.getText());
	}
	if(!classNameField.getText().trim().equals(""))
	{
		customProperty1.put("classname",classNameField.getText());
	}
	if(!pollIntervalField.getText().trim().equals(""))
	{
		customProperty1.put("pollInterval",pollIntervalField.getText());
	}
	if(!statusUpdateTimeField.getText().trim().equals(""))
	{
		customProperty1.put("statusUpdateTime",statusUpdateTimeField.getText());
	}
	if(!statusChangeTimeField.getText().trim().equals(""))
	{	
		customProperty1.put("statusChangeTime",statusChangeTimeField.getText());
	}
	if(!testerField.getText().trim().equals(""))
	{
		customProperty1.put("tester",testerField.getText());
	}
	if(!uclassField.getText().trim().equals(""))
	{	
		customProperty1.put("uClass",uclassField.getText());
	}
	if(!statusCombo.getSelectedItem().toString().equals("all"))
	{	
		customProperty1.put("stringstatus",statusCombo.getSelectedItem());
	}
	if(!isSnmpCombo.getSelectedItem().toString().equals("all"))
	{
		customProperty1.put("isSNMP",isSnmpCombo.getSelectedItem());
	}
	if(!isManageCombo.getSelectedItem().toString().equals("all"))
	{
		customProperty1.put("managed",isManageCombo.getSelectedItem());
	}
	return customProperty1;
}
	
public void setProperties(Properties props,Vector vec)
{
	if(props==null)
	{
		return;
	}
	
	if(props.get("TREE-NAME")!=null)
	{
		filterNameField.setText(props.getProperty("TREE-NAME"));
	}
	if(!props.containsKey("viewcriteria"))
	{
		return;	
	}
	Properties prop=(Properties)props.get("viewcriteria");
	for(Enumeration enumerate=prop.propertyNames();enumerate.hasMoreElements();)
	{
		String key=(String)enumerate.nextElement();
		
		if(key.equals("name"))
		{
			nameField.setText(prop.getProperty(key));
			vec.add("name");	
		}
		else if(key.equals("ipAddress"))
		{
			ipAddressField.setText(prop.getProperty(key));
			vec.add("ipAddress");		
		}
		else if(key.equals("type"))
		{
			typeField.setText(prop.getProperty(key));
			vec.add("type");	
		}
		else if(key.equals("netmask"))
		{
			netMaskField.setText(prop.getProperty(key));
			vec.add("netmask");	
		}
		else if(key.equals("classname"))
		{
			classNameField.setText(prop.getProperty(key));
			vec.add("classname");	
		}
		else if(key.equals("pollInterval"))
		{
			pollIntervalField.setText(prop.getProperty(key));
			vec.add("pollInterval");	
		}
		else if(key.equals("statusChangeTime"))
		{
			statusChangeTimeField.setText(prop.getProperty(key));
			vec.add("statusChangeTime");	
		}
		else if(key.equals("statusUpdateTime"))
		{
			statusUpdateTimeField.setText(prop.getProperty(key));
			vec.add("statusUpdateTime");	
		}
		else if(key.equals("tester"))
		{
			testerField.setText(prop.getProperty(key));
			vec.add("tester");	
		}
		else if(key.equals("uClass"))
		{
			uclassField.setText(prop.getProperty(key));
			vec.add("uClass");		
		}
		else if(key.equals("stringstatus"))
		{
			statusCombo.setSelectedItem(prop.getProperty(key));
			vec.add("stringstatus");		
		}
		else if(key.equals("isSNMP"))
		{
			isSnmpCombo.setSelectedItem(prop.getProperty(key));
			vec.add("isSNMP");	
		}
		else if(key.equals("managed"))
		{
			isManageCombo.setSelectedItem(prop.getProperty(key));
			vec.add("managed");	
		}
	}
}
public void disableAll()
{
	filterNameField.setEditable(false);
	nameField.setEditable(false);
	ipAddressField.setEditable(false);
	typeField.setEditable(false);
	netMaskField.setEditable(false);
	classNameField.setEditable(false);
	pollIntervalField.setEditable(false);
	statusUpdateTimeField.setEditable(false);
	statusChangeTimeField.setEditable(false);
	testerField.setEditable(false);
	uclassField.setEditable(false);
	statusCombo.setEnabled(false);
	isSnmpCombo.setEnabled(false);
	isManageCombo.setEnabled(false);
}
	
}


























