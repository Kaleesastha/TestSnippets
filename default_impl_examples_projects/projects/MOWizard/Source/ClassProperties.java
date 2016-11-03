//$Id: ClassProperties.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $
package com.adventnet.nms.tools.mowizard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import javax.swing.table.*;
import com.adventnet.nms.tools.objtorel.*;
import java.util.*;
import java.net.*;
import java.beans.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import com.adventnet.apiutils.BuilderResourceBundle;
import com.adventnet.nms.tools.utils.ToolsUtil;
public class ClassProperties extends JPanel implements ActionListener
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	javax.swing.JPanel  Top = null;
	javax.swing.JPanel  panelClassProp = null;
	javax.swing.JLabel  labelClassName = null;
	javax.swing.JLabel  labelPackName = null;
	javax.swing.JLabel  labelInterface = null;
	javax.swing.JLabel  labelClassVal = null;
	javax.swing.JLabel  labelPackVal = null;
	javax.swing.JLabel  labelInfaceVal = null;
	javax.swing.JPanel  panelDeviceProp = null;
	javax.swing.JLabel  labelOID = null;
	javax.swing.JLabel  labelOIDVal = null;
	javax.swing.JLabel  labelType = null;
	javax.swing.JLabel  labelTypeVal = null;
	javax.swing.JLabel  labelPoll = null;
	javax.swing.JLabel  labelPollVal = null;
	javax.swing.JLabel  labelUsrTst = null;
	javax.swing.JLabel  labelUstTstVal = null;
	javax.swing.JLabel  labelDiscFil = null;
	javax.swing.JLabel  labelDiscFilVal = null;
	javax.swing.JPanel  panelSuperProp = null;
	javax.swing.JLabel  labelSuper = null;
	javax.swing.JLabel  labelSuperVal = null;
	javax.swing.JButton  propButton = null;
	javax.swing.JPanel  panelCustomProps = null;
	javax.swing.JLabel  labelCustInfo = null;
	javax.swing.JScrollPane  custPropTableScroller = null;
	javax.swing.JTable  customPropTable = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>
	CustomPropTableModel cptm=null;
	ClassAnalyser cnr=null;
	JTable table=null;
	CustomPropTableModel cptm_prop=null;
	boolean isManagedObject=false;
	JScrollPane tableScroll=null;
	JDialog dlg=null;
	BuilderResourceBundle resourceBundle=ToolsUtil.getBundle();
	public ClassProperties()
  {
		//<Begin_ClassProperties>
    this.init();
  
    //<End_ClassProperties>
	}

	public ClassProperties(java.applet.Applet applet)
  {
		//<Begin_ClassProperties_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_ClassProperties_java.applet.Applet>
	}

	public void start()
  { 

		//<Begin_start>

  //<End_start>
	} 
	public void initVariables()throws Exception
  {

		//<Begin_initVariables>
        Top= new javax.swing.JPanel();
        panelClassProp= new javax.swing.JPanel();
        labelClassName= new javax.swing.JLabel();
        labelPackName= new javax.swing.JLabel();
        labelInterface= new javax.swing.JLabel();
        labelClassVal= new javax.swing.JLabel();
        labelPackVal= new javax.swing.JLabel();
        labelInfaceVal= new javax.swing.JLabel();
        panelDeviceProp= new javax.swing.JPanel();
        labelOID= new javax.swing.JLabel();
        labelOIDVal= new javax.swing.JLabel();
        labelType= new javax.swing.JLabel();
        labelTypeVal= new javax.swing.JLabel();
        labelPoll= new javax.swing.JLabel();
        labelPollVal= new javax.swing.JLabel();
        labelUsrTst= new javax.swing.JLabel();
        labelUstTstVal= new javax.swing.JLabel();
        labelDiscFil= new javax.swing.JLabel();
        labelDiscFilVal= new javax.swing.JLabel();
        panelSuperProp= new javax.swing.JPanel();
        labelSuper= new javax.swing.JLabel();
        labelSuperVal= new javax.swing.JLabel();
        propButton= new javax.swing.JButton();
        panelCustomProps= new javax.swing.JPanel();
        labelCustInfo= new javax.swing.JLabel();
        custPropTableScroller= new javax.swing.JScrollPane();
        customPropTable= new javax.swing.JTable();

  //<End_initVariables>
		Object tableHdr[]={ resourceBundle.getString("Serial Number"),resourceBundle.getString("DataType"),resourceBundle.getString("Property Name"),resourceBundle.getString("Default Value"),resourceBundle.getString("AccessModifiers")};
		Object prop[]={ "Serial Number","Property Name","DataType"};
		cptm_prop=new CustomPropTableModel(prop,0);
		cptm=new CustomPropTableModel(tableHdr,0);
		customPropTable.setModel(cptm);
		table=new JTable();
		table.setModel(cptm_prop);
		tableScroll=new JScrollPane();
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
	public void setUpToolBar()
  { 

		//<Begin_setUpToolBar>

  //<End_setUpToolBar>
	} 
	public void setUpGUI(Container container)throws Exception
  { 

		//<Begin_setUpGUI_Container>
container.add(Top,BorderLayout.CENTER);
	Top.setLayout(new GridBagLayout());
	inset = new Insets(0,0,0,0);
	setConstraints(0,0,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
	Top.add(panelClassProp,cons);
	panelClassProp.setLayout(new GridBagLayout());
	inset = new Insets(10,10,10,10);
	setConstraints(0,0,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
	panelClassProp.add(labelClassName,cons);
	inset = new Insets(10,10,10,10);
	setConstraints(0,1,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
	panelClassProp.add(labelPackName,cons);
	inset = new Insets(10,10,10,10);
	setConstraints(0,2,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
	panelClassProp.add(labelInterface,cons);
	inset = new Insets(10,10,10,10);
	setConstraints(1,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
	panelClassProp.add(labelClassVal,cons);
	inset = new Insets(10,10,10,10);
	setConstraints(1,1,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
	panelClassProp.add(labelPackVal,cons);
	inset = new Insets(10,10,10,10);
	setConstraints(1,2,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
	panelClassProp.add(labelInfaceVal,cons);
	inset = new Insets(0,0,0,0);
	setConstraints(0,1,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
	Top.add(panelDeviceProp,cons);
	panelDeviceProp.setLayout(new GridBagLayout());
	inset = new Insets(10,10,0,10);
	setConstraints(0,0,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
	panelDeviceProp.add(labelOID,cons);
	inset = new Insets(10,10,0,10);
	setConstraints(1,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
	panelDeviceProp.add(labelOIDVal,cons);
	inset = new Insets(0,10,0,10);
	setConstraints(0,1,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
	panelDeviceProp.add(labelType,cons);
	inset = new Insets(0,10,0,10);
	setConstraints(1,1,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
	panelDeviceProp.add(labelTypeVal,cons);
	inset = new Insets(0,10,0,10);
	setConstraints(0,2,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
	panelDeviceProp.add(labelPoll,cons);
	inset = new Insets(0,10,0,10);
	setConstraints(1,2,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
	panelDeviceProp.add(labelPollVal,cons);
	inset = new Insets(0,10,0,10);
	setConstraints(0,3,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
	panelDeviceProp.add(labelUsrTst,cons);
	inset = new Insets(0,10,0,10);
	setConstraints(1,3,1,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
	panelDeviceProp.add(labelUstTstVal,cons);
	inset = new Insets(0,10,10,10);
	setConstraints(0,4,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
	panelDeviceProp.add(labelDiscFil,cons);
	inset = new Insets(0,10,0,10);
	setConstraints(1,4,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
	panelDeviceProp.add(labelDiscFilVal,cons);
	inset = new Insets(0,0,0,0);
	setConstraints(0,2,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
	Top.add(panelSuperProp,cons);
	panelSuperProp.setLayout(new GridBagLayout());
	inset = new Insets(10,10,10,10);
	setConstraints(0,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
	panelSuperProp.add(labelSuper,cons);
	inset = new Insets(0,0,0,0);
	setConstraints(1,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
	panelSuperProp.add(labelSuperVal,cons);
	inset = new Insets(0,0,0,0);
	setConstraints(2,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
	panelSuperProp.add(propButton,cons);
	inset = new Insets(0,0,0,0);
	setConstraints(0,3,0,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
	Top.add(panelCustomProps,cons);
	panelCustomProps.setLayout(new GridBagLayout());
	inset = new Insets(10,10,0,10);
	setConstraints(0,0,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
	panelCustomProps.add(labelCustInfo,cons);
	inset = new Insets(0,10,10,10);
	setConstraints(0,1,0,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
	panelCustomProps.add(custPropTableScroller,cons);
	custPropTableScroller.getViewport().add(customPropTable);
	
  //<End_setUpGUI_Container>
  tableScroll.getViewport().add(table);
	} 
	public void setUpProperties()throws Exception
  { 

		//<Begin_setUpProperties>

          try
          {
            Top.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.LineBorder(new Color(-16777216),1),resourceBundle.getString("ManagedObject Properties"),1,2,new Font("Dialog",0,12),new Color(-16764109)));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+Top,ex); 
          }

          try
          {
            panelClassProp.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.LineBorder(new Color(-16777216),1),resourceBundle.getString("Class Properties"),1,2,new Font("Dialog",0,12),new Color(-16764109)));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+panelClassProp,ex); 
          }

          try
          {
            labelClassName.setText(resourceBundle.getString("Class Name"));
            labelClassName.setFont(new Font("Dialog",0,12));
            labelClassName.setForeground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+labelClassName,ex); 
          }

          try
          {
            labelPackName.setText(resourceBundle.getString("Package Name"));
            labelPackName.setFont(new Font("Dialog",0,12));
            labelPackName.setForeground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+labelPackName,ex); 
          }

          try
          {
            labelInterface.setText(resourceBundle.getString("Interfaces"));
            labelInterface.setFont(new Font("Dialog",0,12));
            labelInterface.setForeground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+labelInterface,ex); 
          }

          try
          {
            panelDeviceProp.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.LineBorder(new Color(-16777216),1),resourceBundle.getString("Device Based Properties"),1,2,new Font("Dialog",0,12),new Color(-16764109)));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+panelDeviceProp,ex); 
          }

          try
          {
            labelOID.setText(resourceBundle.getString("Object Identifier"));
            labelOID.setFont(new Font("Dialog",0,12));
            labelOID.setForeground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+labelOID,ex); 
          }

          try
          {
            labelType.setFont(new Font("Dialog",0,12));
            labelType.setForeground(new Color(-16764109));
            labelType.setText(resourceBundle.getString("Type"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+labelType,ex); 
          }

          try
          {
            labelPoll.setText(resourceBundle.getString("Poll Interval"));
            labelPoll.setFont(new Font("Dialog",0,12));
            labelPoll.setForeground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+labelPoll,ex); 
          }

          try
          {
            labelUsrTst.setFont(new Font("Dialog",0,12));
            labelUsrTst.setForeground(new Color(-16764109));
            labelUsrTst.setText(resourceBundle.getString("Status Poller"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+labelUsrTst,ex); 
          }

          try
          {
            labelDiscFil.setText(resourceBundle.getString("Discovery Filter"));
            labelDiscFil.setFont(new Font("Dialog",0,12));
            labelDiscFil.setForeground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+labelDiscFil,ex); 
          }

          try
          {
            panelSuperProp.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.LineBorder(new Color(-16777216),1),resourceBundle.getString("Super Class Properties"),1,2,new Font("Dialog",0,12),new Color(-16764109)));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+panelSuperProp,ex); 
          }

          try
          {
            labelSuper.setText(resourceBundle.getString("Class Name"));
            labelSuper.setFont(new Font("Dialog",0,12));
            labelSuper.setForeground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+labelSuper,ex); 
          }

          try
          {
            labelSuperVal.setForeground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+labelSuperVal,ex); 
          }

          try
          {
            propButton.setText(resourceBundle.getString("Properties"));
            propButton.setFont(new Font("Dialog",0,12));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+propButton,ex); 
          }

          try
          {
            panelCustomProps.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.LineBorder(new Color(-16777216),1),resourceBundle.getString("Custom Properties"),1,2,new Font("Dialog",0,12),new Color(-16764109)));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+panelCustomProps,ex); 
          }

  //<End_setUpProperties>
		propButton.setEnabled(false);
		propButton.addActionListener(this);
	} 
	public void setUpConnections()throws Exception
  { 

		//<Begin_setUpConnections>

  //<End_setUpConnections>
	} 
	public void stop()
  { 

		//<Begin_stop>

  //<End_stop>
	} 
	public void setUpMenus()
  { 

		//<Begin_setUpMenus>

  //<End_setUpMenus>
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
            if (input.equals("HOST")) value = "localhost"; 
            if (input.equals("PORT")) value = "161"; 
            }
        return value;

  //<End_getParameter_String>
	} 
	public void init()
  { 

		//<Begin_init>
        if (initialized == true) return; 
        setPreferredSize(new Dimension(getPreferredSize().width+788,getPreferredSize().height+597)); 
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
          showStatus(resourceBundle.getString("Error in init method"),ex); 
        } 
        // let us set the initialzed variable to true so  
        // we dont initialize again even if init is called 
        initialized = true; 
      setUpMenus();
      setUpToolBar();
        

  //<End_init>
	} 


	public void showStatus(String message)
  {
		//<Begin_showStatus_String>
     System.out.println(resourceBundle.getString("Internal Error :")+ message);
     //<End_showStatus_String>
	}
	
	public void showStatus(String message,Exception ex)
  {
		//<Begin_showStatus_String_Exception>
     System.out.println(resourceBundle.getString("Internal Error :")+ message);
     ex.printStackTrace();
     //<End_showStatus_String_Exception>
	}

	public void setClassProperties(Document doc) {
		if(doc==null) {
			return;
		}
		// Write Code for processing Class Information.
		Element tempNode=(Element)doc.getElementsByTagName("CLASS").item(0);
		labelClassVal.setText(tempNode.getAttribute("name"));
		if(tempNode.getAttribute("package")!=null) {
			labelPackVal.setText(":  "+tempNode.getAttribute("package"));
		}
		else {
			labelPackVal.setText(resourceBundle.getString(":  Not Defined"));
		}	
		if(tempNode.getAttribute("implements")!=null) {
			labelInfaceVal.setText(":  "+tempNode.getAttribute("implements"));
		}
		else {
			labelInfaceVal.setText(resourceBundle.getString(":  Not Defined"));
		}	
		if(tempNode.getAttribute("extends")!=null) {
			labelSuperVal.setText(":  "+tempNode.getAttribute("extends"));
			propButton.setEnabled(true);
		}
		else {
			labelSuperVal.setText(resourceBundle.getString(":  Not Defined"));
			propButton.setEnabled(false);
		}

		// Write Code for processing Device Details
		tempNode=(Element)doc.getElementsByTagName("DEVICE_PARAMS").item(0);
		if(tempNode!=null) {
			labelOIDVal.setText(":  "+tempNode.getAttribute("eoid"));
			labelTypeVal.setText(":  "+tempNode.getAttribute("devType"));
			labelPollVal.setText(":  "+tempNode.getAttribute("pollInt"));
		}
		else {
			labelOIDVal.setText(resourceBundle.getString(":  Not Defined"));
			labelTypeVal.setText(resourceBundle.getString(":  Not Defined"));
			labelPollVal.setText(resourceBundle.getString(":  Not Defined"));
		}
		tempNode=(Element)doc.getElementsByTagName("USER_TESTER").item(0);
		if(tempNode!=null) {
			if(tempNode.getAttribute("className")!=null && tempNode.getAttribute("className").trim().length()!=0) {
				labelUstTstVal.setText(":  "+tempNode.getAttribute("className"));
			}
			else {
				labelUstTstVal.setText(resourceBundle.getString(":  Not Defined"));
			}	
		}
		else {
			labelUstTstVal.setText(resourceBundle.getString(":  Not Defined"));
		}
		tempNode=(Element)doc.getElementsByTagName("DISC_FILTER").item(0);
		if(tempNode!=null) {
			if(tempNode.getAttribute("className")!=null && tempNode.getAttribute("className").trim().length()!=0) {
				labelDiscFilVal.setText(":  "+tempNode.getAttribute("className"));
			}
			else  {
				labelDiscFilVal.setText(resourceBundle.getString(":  Not Defined"));
			}	
		}
		else {	
			labelDiscFilVal.setText(resourceBundle.getString(":  Not Defined"));
		}	
			
		// Remove all Rows if already some rows are present.  This is done to represent the 
		// the latest class Properties.

		for(int i=customPropTable.getRowCount()-1;i>=0;i--) {
            ((DefaultTableModel)customPropTable.getModel()).removeRow(i);
        }
	
		NodeList moProp=doc.getElementsByTagName("MOPROPERTY");
        for(int i=0;i<moProp.getLength();i++) {
            Element node=(Element)moProp.item(i);
            Object[] data= { new Integer(i+1),
                        ((node.getAttribute("dataType")==null)? " ":node.getAttribute("dataType")) ,
                        ((node.getAttribute("name")==null) ? "  ":node.getAttribute("name")),
                        ((node.getAttribute("defaultValue")==null) ? "  ":node.getAttribute("defaultValue")),
                        ((node.getAttribute("accessModifier")==null) ? "  ":node.getAttribute("accessModifier")),
                        //((node.getAttribute("fieldModifier")==null) ? "  ":node.getAttribute("fieldModifier")) 
			};
            ((DefaultTableModel)customPropTable.getModel()).addRow(data);
        }
	}		

	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==propButton){
			propButton_Clicked(ae);
		}
	}

	public void propButton_Clicked(ActionEvent ae)
	{
		String classname=labelSuperVal.getText().trim();
		String classpath=null;
		classname=classname.substring(classname.indexOf(":")+1).trim();
		File f=new File("."+File.separator+"projects"+File.separator+"MOObjects.xml");
		if(!f.exists()){
			return;
		}
		try{
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			DocumentBuilder db=dbf.newDocumentBuilder();
			Document doc=db.parse(f);
			NodeList tags=doc.getElementsByTagName("CLASS");
			for(int i=0;i<tags.getLength();i++){
				Element node=(Element)tags.item(i);
				if(node.getAttribute("name").equals(classname)){
					classpath=node.getAttribute("classpath");
					break;
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		if(classpath!=null){
			for(int i=table.getModel().getRowCount();i>0;i--) {
				((DefaultTableModel)table.getModel()).removeRow(i-1);
			}
			String className=null;
			try {
				URL[] urlarray=parseFile(classpath);
				cnr=new ClassAnalyser();
				String fullclassName=cnr.getFullClassName(classname,urlarray);
			}
			catch(ClassNotFoundException cnfe) 
			{
				JOptionPane.showMessageDialog(null,resourceBundle.getString("Error in Loading the class"),resourceBundle.getString("Error"),JOptionPane.OK_OPTION);
				return;
			}
			populateTable();
		}
	}    

	public void populateTable() {
		String superClassNames[]=null;
		String className=null;
		try {
			superClassNames=cnr.getSuperClassNames(className);
		}catch(ClassNotFoundException cnfe) {
			JOptionPane.showMessageDialog(null,resourceBundle.getString("The selected class is not found"),resourceBundle.getString("Error"),JOptionPane.OK_OPTION);
			return;
		}     
		if(superClassNames==null) {
			return;
		}
		for(int i=0;i<superClassNames.length;i++) {
			if(superClassNames[i].trim().equals("com.adventnet.nms.topodb.ManagedObject")) {
				isManagedObject=true;
				break;
			}    
		}
		addDataTables(className);
	}

	Object[] createTableRow(String PropertyName,int rowNumber,boolean isBase,Class analyze) {

		Object data[]=null;
		PropertyDescriptor pd=null;
		try {
			pd=new PropertyDescriptor(PropertyName,analyze);
			if(isBase) {
				data =new Object[3];
				data[0]=new String(Integer.toString(rowNumber+1));
				data[1]=new String(PropertyName);
				data[2]=new String((pd.getPropertyType().toString().indexOf("String")==-1)? pd.getPropertyType().toString():"String");
				//data[3]=new String(pd.getWriteMethod().getName());
				//data[4]=new String(pd.getReadMethod().getName());
			}
			return data;
		}catch(IntrospectionException ie) {
			return null;
		}    
	}    

	void createSelectionTable(String[] PropertyNames, String ClassName) 			{
		Object data[]=new Object[3];
		PropertyDescriptor pd=null;
		Class analyze=null;

		try {
			analyze=cnr.getAnalysedClass();
		}
		catch(NullPointerException cnfe) {
			System.out.println(resourceBundle.getString("Please update CLASSPATH variable to include : ")+ClassName);
			return;
		}

		if(isManagedObject) { 
			int j=0;
			for(int i=0;i< PropertyNames.length;i++) {
				if((data=createTableRow(PropertyNames[i],j,true,analyze)) != null) {
					((javax.swing.table.DefaultTableModel)table.getModel()).addRow(data);                
					j++;
				}
			}
		}
		else {
			int j=0;
			for(int i=0;i< PropertyNames.length;i++) {
				if((data=createTableRow(PropertyNames[i],j,false,analyze)) != null) {
					j++;
					((javax.swing.table.DefaultTableModel)table.getModel()).addRow(data);
				}
			}
		}    
		dlg=new JDialog();
		dlg.getContentPane().setLayout(new BorderLayout());
		dlg.setSize(400,400);
		dlg.setTitle(java.text.MessageFormat.format(
        resourceBundle.getString("Superclass Properties for {0}"), new String[]{""+labelClassVal.getText().trim()}));

		dlg.setResizable(false);
		dlg.setLocation(getScrLoc(dlg));
		JButton okbutton=new JButton(resourceBundle.getString("OK"));
		JPanel panel=new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panel.add(okbutton);
		okbutton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
				dlg.dispose();
				}
				});
		dlg.getContentPane().add(panel,BorderLayout.SOUTH);
		dlg.getContentPane().add(tableScroll,BorderLayout.CENTER);
		dlg.setModal(true);
		dlg.setVisible(true);
	}

	private Point getScrLoc(Component comp)
	{
		Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
		int width=(int)(d.getWidth()-comp.getSize().getWidth())/2;
		int height=(int)(d.getHeight()-comp.getSize().getHeight())/2;
		return new Point(width,height);
	}

	public void addDataTables(String className) {
		String [] Props;
		try {
			cnr.analyseClass(cnr.getAnalysedClass(),null,false);
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null,resourceBundle.getString("Error in Loading the class"),resourceBundle.getString("Error"),JOptionPane.OK_OPTION);
			return;
		}
		Props=cnr.getProperties();
		createSelectionTable(Props,className);
	}

	public URL[] parseFile(String classpath)  
	{
		URL[] urlarray=null;
		String token=null;
		int i=0;
		StringTokenizer stk=new StringTokenizer(classpath,System.getProperty("path.separator"));
		try{
			urlarray=new URL[stk.countTokens()];
			while(stk.hasMoreTokens()){
				if((token=stk.nextToken()).startsWith(".")){
					String temptoken=token.substring(token.indexOf('.')+1);
					token=System.getProperty("user.dir")+temptoken;
				}
				urlarray[i++]=new File(token).toURL();
			}
		}
		catch(MalformedURLException mue){
			mue.printStackTrace();
		}
		return urlarray;
	}
}	

