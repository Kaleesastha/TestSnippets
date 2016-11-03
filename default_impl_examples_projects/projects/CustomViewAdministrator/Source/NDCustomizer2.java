//$Id: NDCustomizer2.java,v 1.2 2007/02/22 15:03:04 srajeswari Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory




package com.adventnet.nms.tools.CustomView;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;




public class NDCustomizer2 extends JPanel implements ActionListener
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JLabel inuse = null;
	javax.swing.JLabel isrouter = null;
	javax.swing.JLabel sysoid = null;
	javax.swing.JLabel sysname = null;
	javax.swing.JLabel sysdescr = null;
	javax.swing.JLabel community = null;
	javax.swing.JLabel isinterface = null;
	javax.swing.JLabel isnode = null;
	javax.swing.JLabel isnetwork = null;
	javax.swing.JTextField sysOIDField = null;
	javax.swing.JTextField sysDescrField = null;
	javax.swing.JTextField sysNameField = null;
	javax.swing.JTextField communityField = null;
	javax.swing.JComboBox inUseCombo = null;
	javax.swing.JComboBox isRouterCombo = null;
	javax.swing.JComboBox isInterfaceCombo = null;
	javax.swing.JComboBox isNodeCombo = null;
	javax.swing.JComboBox isNetworkCombo = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JButton selectprops = null;
	javax.swing.JButton additionalcriteria = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>
	SelectPropsToView viewProps=null;
	AdditionalCriteria criteria=null;
	Properties customProperty2=null;
	InitialProperties initialProps=null;
	Properties selectedColumns=null;
	Properties tableColumnProperties=null;
	Vector selectedProps=null;
	Properties selectedCriteria=null;
  public NDCustomizer2()
  {
    //<Begin_NDCustomizer2>
    this.init();
  
    //<End_NDCustomizer2>
  }

  public NDCustomizer2(java.applet.Applet applet)
  {
    //<Begin_NDCustomizer2_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_NDCustomizer2_java.applet.Applet>
  }


  


 

  

    public void init()
  {
        //<Begin_init> 
        if (initialized == true) return; 
        setPreferredSize(new Dimension(getPreferredSize().width+481,getPreferredSize().height+493)); 
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
            inuse.setHorizontalAlignment(2);
            inuse.setFont(new Font("SansSerif",0,12));
            inuse.setForeground(new Color(-16777216));
            inuse.setHorizontalTextPosition(4);
            inuse.setText("inUse");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+inuse,ex); 
          }

          try
          {
            isrouter.setHorizontalAlignment(2);
            isrouter.setFont(new Font("SansSerif",0,12));
            isrouter.setForeground(new Color(-16777216));
            isrouter.setHorizontalTextPosition(4);
            isrouter.setText("isRouter");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+isrouter,ex); 
          }

          try
          {
            sysoid.setHorizontalAlignment(2);
            sysoid.setFont(new Font("SansSerif",0,12));
            sysoid.setForeground(new Color(-16777216));
            sysoid.setHorizontalTextPosition(4);
            sysoid.setText("sysOID");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+sysoid,ex); 
          }

          try
          {
            sysname.setHorizontalAlignment(2);
            sysname.setFont(new Font("SansSerif",0,12));
            sysname.setForeground(new Color(-16777216));
            sysname.setHorizontalTextPosition(4);
            sysname.setText("sysName");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+sysname,ex); 
          }

          try
          {
            sysdescr.setHorizontalAlignment(2);
            sysdescr.setFont(new Font("SansSerif",0,12));
            sysdescr.setForeground(new Color(-16777216));
            sysdescr.setHorizontalTextPosition(4);
            sysdescr.setText("sysDescr");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+sysdescr,ex); 
          }

          try
          {
            community.setHorizontalAlignment(2);
            community.setFont(new Font("SansSerif",0,12));
            community.setForeground(new Color(-16777216));
            community.setHorizontalTextPosition(4);
            community.setText("Community");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+community,ex); 
          }

          try
          {
            isinterface.setHorizontalAlignment(2);
            isinterface.setFont(new Font("SansSerif",0,12));
            isinterface.setForeground(new Color(-16777216));
            isinterface.setHorizontalTextPosition(4);
            isinterface.setText("isInterface");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+isinterface,ex); 
          }

          try
          {
            isnode.setHorizontalAlignment(2);
            isnode.setFont(new Font("SansSerif",0,12));
            isnode.setForeground(new Color(-16777216));
            isnode.setHorizontalTextPosition(4);
            isnode.setText("isNode");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+isnode,ex); 
          }

          try
          {
            isnetwork.setHorizontalAlignment(2);
            isnetwork.setFont(new Font("SansSerif",0,12));
            isnetwork.setForeground(new Color(-16777216));
            isnetwork.setHorizontalTextPosition(4);
            isnetwork.setText("isNetwork");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+isnetwork,ex); 
          }

          try
          {
            sysOIDField.setHorizontalAlignment(2);
            sysOIDField.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+sysOIDField,ex); 
          }

          try
          {
            sysDescrField.setHorizontalAlignment(2);
            sysDescrField.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+sysDescrField,ex); 
          }

          try
          {
            sysNameField.setHorizontalAlignment(2);
            sysNameField.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+sysNameField,ex); 
          }

          try
          {
            communityField.setHorizontalAlignment(2);
            communityField.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+communityField,ex); 
          }

          try
          {
            inUseCombo.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+inUseCombo,ex); 
          }

          try
          {
            isRouterCombo.setFont(new Font("SansSerif",0,12));
            isRouterCombo.setEditable(false);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+isRouterCombo,ex); 
          }

          try
          {
            isInterfaceCombo.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+isInterfaceCombo,ex); 
          }

          try
          {
            isNodeCombo.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+isNodeCombo,ex); 
          }

          try
          {
            isNetworkCombo.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+isNetworkCombo,ex); 
          }

          try
          {
            selectprops.setFont(new Font("SansSerif",0,12));
            selectprops.setHorizontalTextPosition(4);
            selectprops.setBorder(new javax.swing.border.BevelBorder(0));
            selectprops.setLabel("Select Properties  To View");
            selectprops.setText("Select Properties To View");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+selectprops,ex); 
          }

          try
          {
            additionalcriteria.setFont(new Font("SansSerif",0,12));
            additionalcriteria.setHorizontalTextPosition(4);
            additionalcriteria.setText("Additional Criteria");
            additionalcriteria.setMaximumSize(new Dimension(151,27));
            additionalcriteria.setMinimumSize(new Dimension(151,27));
            additionalcriteria.setPreferredSize(new Dimension(151,27));
            additionalcriteria.setBorder(new javax.swing.border.BevelBorder(0));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+additionalcriteria,ex); 
          }
		selectprops.setPreferredSize(new Dimension(selectprops.getPreferredSize().width+6,selectprops.getPreferredSize().height+6));
		JPanel1.setPreferredSize(new Dimension(JPanel1.getPreferredSize().width+150,JPanel1.getPreferredSize().height+0));

  
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
        JPanel1= new javax.swing.JPanel();
        inuse= new javax.swing.JLabel();
        isrouter= new javax.swing.JLabel();
        sysoid= new javax.swing.JLabel();
        sysname= new javax.swing.JLabel();
        sysdescr= new javax.swing.JLabel();
        community= new javax.swing.JLabel();
        isinterface= new javax.swing.JLabel();
        isnode= new javax.swing.JLabel();
        isnetwork= new javax.swing.JLabel();
        sysOIDField= new javax.swing.JTextField();
        sysDescrField= new javax.swing.JTextField();
        sysNameField= new javax.swing.JTextField();
        communityField= new javax.swing.JTextField();
        inUseCombo= new javax.swing.JComboBox();
        isRouterCombo= new javax.swing.JComboBox();
        isInterfaceCombo= new javax.swing.JComboBox();
        isNodeCombo= new javax.swing.JComboBox();
        isNetworkCombo= new javax.swing.JComboBox();
        JPanel2= new javax.swing.JPanel();
        selectprops= new javax.swing.JButton();
        additionalcriteria= new javax.swing.JButton();

  
        //<End_initVariables>
	addItems(inUseCombo);
	addItems(isRouterCombo);
	addItems(isNetworkCombo);
	addItems(isInterfaceCombo);
	addItems(isNodeCombo);
	selectprops.addActionListener(this);
	additionalcriteria.addActionListener(this);
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
Top.add(JPanel1,BorderLayout.NORTH);
JPanel1.setLayout(new GridBagLayout());
inset = new Insets(15,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel1.add(inuse,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel1.add(isrouter,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel1.add(sysoid,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,3,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel1.add(sysname,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,4,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel1.add(sysdescr,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,5,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel1.add(community,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,6,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel1.add(isinterface,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,7,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel1.add(isnode,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,8,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel1.add(isnetwork,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,2,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel1.add(sysOIDField,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,4,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel1.add(sysDescrField,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,3,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel1.add(sysNameField,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,5,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel1.add(communityField,cons);
inset = new Insets(15,5,5,5);
setConstraints(1,0,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel1.add(inUseCombo,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,1,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel1.add(isRouterCombo,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,6,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel1.add(isInterfaceCombo,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,7,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel1.add(isNodeCombo,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,8,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel1.add(isNetworkCombo,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,9,0,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel1.add(JPanel2,cons);
JPanel2.setLayout(new FlowLayout(1,5,5));
JPanel2.add(selectprops);
JPanel2.add(additionalcriteria);

  
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
public void actionPerformed(ActionEvent e)
{
	if(e.getSource().equals(selectprops))
	{
		boolean isNull=false;	
		if(viewProps==null)
		{
			viewProps=new SelectPropsToView();
			isNull=true;	
		}
		
		if(initialProps==null)
		{
			initialProps=new InitialProperties();
		}
		viewProps.setDisplayNames(initialProps.getIdVsDisplayNamesForTopo());
		if(tableColumnProperties==null && isNull)
		{
			viewProps.setSelection(getDefaultColumns());
		}
		
		else if(tableColumnProperties!=null && isNull) 
		{
			viewProps.setSelection(tableColumnProperties);
			viewProps.setDisplayNames(tableColumnProperties);
		}
		else
		{
			Properties selectProps=viewProps.getColumns();
			viewProps.setSelection(selectProps);
			viewProps.setDisplayNames(selectProps);	
		}
		viewProps.setVisible(true);
		
	}
	if(e.getSource().equals(additionalcriteria))
	{
		
		if(criteria==null)
		{
				
			criteria=new AdditionalCriteria("Additional Criteria",selectedCriteria,selectedProps);
		}
		else
		{
			Properties prop=criteria.getProperties();	
			criteria=new AdditionalCriteria("Additional Criteria",prop,null);
		}
		criteria.setVisible(true);
	}
}
	public Properties getProperties()
	{
		customProperty2=new Properties();
		if(!sysOIDField.getText().trim().equals(""))
		{
			customProperty2.put("sysOID",sysOIDField.getText());
		}
		if(!sysDescrField.getText().trim().equals(""))
		{
			customProperty2.put("sysDescr",sysDescrField.getText());
		}
		if(!sysNameField.getText().trim().equals(""))
		{
			customProperty2.put("sysName",sysNameField.getText());
		}
		if(!communityField.getText().trim().equals(""))
		{
			customProperty2.put("community",communityField.getText());
		}
		if(!inUseCombo.getSelectedItem().toString().equals("all"))
		{
			customProperty2.put("inUse",inUseCombo.getSelectedItem());
		}
		if(!isRouterCombo.getSelectedItem().toString().equals("all"))
		{
			customProperty2.put("isRouter",isRouterCombo.getSelectedItem());
		}
		if(!isInterfaceCombo.getSelectedItem().toString().equals("all"))
		{
			customProperty2.put("isInterface",isInterfaceCombo.getSelectedItem());
		}
		if(!isNodeCombo.getSelectedItem().toString().equals("all"))
		{
			customProperty2.put("isNode",isNodeCombo.getSelectedItem());
		}
		if(!isNetworkCombo.getSelectedItem().toString().equals("all"))
		{
			customProperty2.put("isNetwork",isNetworkCombo.getSelectedItem());
		}
		if(criteria!=null && criteria.getProps().size()>0)
		{
	        		Properties properties=criteria.getProps();
	        		for(Enumeration e=properties.propertyNames();e.hasMoreElements();)
			{
		   		String str=(String)e.nextElement();
		         	           customProperty2.put(str,properties.get(str));
	        		}
		}		
		
		return customProperty2;
	}
public void setProperties(Properties props,Vector vec)
{
	if(props==null)
	{
		return;
	}
	Properties prop=null;
	if(props.containsKey("viewcriteria"))
	{

	
	 prop=(Properties)props.get("viewcriteria");
	for(Enumeration enumerate=prop.propertyNames();enumerate.hasMoreElements();)
	{
		selectedCriteria=prop;	
		String key=(String)enumerate.nextElement();
		if(key.equals("sysOID"))
		{
		   	sysOIDField.setText(prop.getProperty(key));
			vec.add("sysOID");	
		}
		else if(key.equals("sysDescr"))
		{
			sysDescrField.setText(prop.getProperty(key));
			vec.add("sysDescr");
		}
		else if(key.equals("sysName"))
		{
			sysNameField.setText(prop.getProperty(key));
			vec.add("sysName");	
		}
		else if(key.equals("community"))
		{
			communityField.setText(prop.getProperty(key));
			vec.add("community");	
		}
		else if(key.equals("inUse"))
		{
			inUseCombo.setSelectedItem(prop.getProperty(key));
			vec.add("inUse");	
		}
		else if(key.equals("isRouter"))
		{
			isRouterCombo.setSelectedItem(prop.getProperty(key));
			vec.add("isRouter");	
		}
		else if(key.equals("isNetwork"))
		{
			isNetworkCombo.setSelectedItem(prop.getProperty(key));
			vec.add("isNetwork");
		}
		else if(key.equals("isInterface"))
		{
			isInterfaceCombo.setSelectedItem(prop.getProperty(key));
			vec.add("isInterface");	
		}
		else if(key.equals("isNode"))
		{
			isNodeCombo.setSelectedItem(prop.getProperty(key));
			vec.add("isNode");	
		}
	}
	}
	selectedProps=vec;	
	if(props.containsKey("tablecolumns"))
	{
		tableColumnProperties=(Properties)props.get("tablecolumns");
	}
		
      }
   public Properties getTableColumns()
	{
		if(viewProps!=null)
		{
			selectedColumns=viewProps.getSelectedColumns();
		}
		//This is done when the user doesn't click the "selectpropstoview" button
		else
		{
			selectedColumns=getDefaultColumns();
		}
		return selectedColumns;
	}
	public Properties getDefaultColumns()
	{
		Properties p=new Properties();
		p.put("name","Name");
		p.put("status","Status");
		p.put("isSNMP","isSnmp");
		p.put("ipAddress","IPAddress");
		p.put("type","Type");
		p.put("netmask","NetMask");
		return p;
	}
	public void disableAll()
	{
		sysOIDField.setEditable(false);
		sysDescrField.setEditable(false);
		sysNameField.setEditable(false);
		communityField.setEditable(false);
		inUseCombo.setEnabled(false);
		isRouterCombo.setEnabled(false);
		isInterfaceCombo.setEnabled(false);
		isNodeCombo.setEnabled(false);
		isNetworkCombo.setEnabled(false);
		selectprops.setEnabled(false);
		additionalcriteria.setEnabled(false);
	}
}





























