
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.tools.CustomView;




import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.event.*;
import java.util.*;


public class PerfCustomizer3 extends JPanel implements ActionListener
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	javax.swing.JPanel Top = null;
	javax.swing.JTextField tTimeAvg = null;
	javax.swing.JLabel lTimeAvg = null;
	javax.swing.JTextField tOwner = null;
	javax.swing.JLabel lOwner = null;
	javax.swing.JTextField tSaveThreshold = null;
	javax.swing.JLabel lSaveThreshold = null;
	javax.swing.JTextField tSuffix = null;
	javax.swing.JLabel lSuffix = null;
	javax.swing.JTextField tFailThreshold = null;
	javax.swing.JLabel lFailThreshold = null;
	javax.swing.JTextField tSavePollCount = null;
	javax.swing.JLabel lSavePollCount = null;
	javax.swing.JButton bAdditionalProps = null;
	javax.swing.JButton bSelectProps = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JLabel JLabel2 = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>
	 showPerfProperties viewProps=null;
	InitialProperties initialProps=null;
	AdditionalCriteria criteria=null;
	Properties selectedColumns=null;
	Properties tableColumnProperties=null;
	Vector selectedProps=null;
	Properties additionalCriteria=null;
  public PerfCustomizer3()
  {
    //<Begin_PerfCustomizer3>
    this.init();
  
    //<End_PerfCustomizer3>
  }

  public PerfCustomizer3(java.applet.Applet applet)
  {
    //<Begin_PerfCustomizer3_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_PerfCustomizer3_java.applet.Applet>
  }


  


 

  

    public void init()
  {
        //<Begin_init> 
        if (initialized == true) return; 
        setPreferredSize(new Dimension(getPreferredSize().width+470,getPreferredSize().height+471)); 
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
            Top.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.LineBorder(new Color(-16777216),1),"MO Properties",0,0,new Font("Dialog",0,12),new Color(-16764109)));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+Top,ex); 
          }

          try
          {
            tTimeAvg.setHorizontalAlignment(2);
            tTimeAvg.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tTimeAvg,ex); 
          }

          try
          {
            lTimeAvg.setHorizontalAlignment(2);
            lTimeAvg.setFont(new Font("SansSerif",0,12));
            lTimeAvg.setForeground(new Color(-16777216));
            lTimeAvg.setHorizontalTextPosition(4);
            lTimeAvg.setText("timeAvg");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+lTimeAvg,ex); 
          }

          try
          {
            tOwner.setHorizontalAlignment(2);
            tOwner.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tOwner,ex); 
          }

          try
          {
            lOwner.setHorizontalAlignment(2);
            lOwner.setFont(new Font("SansSerif",0,12));
            lOwner.setForeground(new Color(-16777216));
            lOwner.setHorizontalTextPosition(4);
            lOwner.setText("OwnerName");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+lOwner,ex); 
          }

          try
          {
            tSaveThreshold.setHorizontalAlignment(2);
            tSaveThreshold.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tSaveThreshold,ex); 
          }

          try
          {
            lSaveThreshold.setHorizontalAlignment(2);
            lSaveThreshold.setFont(new Font("SansSerif",0,12));
            lSaveThreshold.setForeground(new Color(-16777216));
            lSaveThreshold.setHorizontalTextPosition(4);
            lSaveThreshold.setText("SaveonThreshold");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+lSaveThreshold,ex); 
          }

          try
          {
            tSuffix.setHorizontalAlignment(2);
            tSuffix.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tSuffix,ex); 
          }

          try
          {
            lSuffix.setHorizontalAlignment(2);
            lSuffix.setFont(new Font("SansSerif",0,12));
            lSuffix.setForeground(new Color(-16777216));
            lSuffix.setHorizontalTextPosition(4);
            lSuffix.setText("Suffix");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+lSuffix,ex); 
          }

          try
          {
            tFailThreshold.setHorizontalAlignment(2);
            tFailThreshold.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tFailThreshold,ex); 
          }

          try
          {
            lFailThreshold.setHorizontalAlignment(2);
            lFailThreshold.setFont(new Font("SansSerif",0,12));
            lFailThreshold.setForeground(new Color(-16777216));
            lFailThreshold.setHorizontalTextPosition(4);
            lFailThreshold.setText("failureThreshold");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+lFailThreshold,ex); 
          }

          try
          {
            tSavePollCount.setHorizontalAlignment(2);
            tSavePollCount.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tSavePollCount,ex); 
          }

          try
          {
            lSavePollCount.setHorizontalAlignment(2);
            lSavePollCount.setFont(new Font("SansSerif",0,12));
            lSavePollCount.setForeground(new Color(-16777216));
            lSavePollCount.setHorizontalTextPosition(4);
            lSavePollCount.setText("SavePollCount");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+lSavePollCount,ex); 
          }

          try
          {
            bAdditionalProps.setFont(new Font("SansSerif",0,12));
            bAdditionalProps.setHorizontalTextPosition(4);
            bAdditionalProps.setText("Additonal Properties");
            bAdditionalProps.setBorder(new javax.swing.border.BevelBorder(0));
            bAdditionalProps.setPreferredSize(new Dimension(163,25));
            bAdditionalProps.setMinimumSize(new Dimension(163,25));
            bAdditionalProps.setMaximumSize(new Dimension(163,25));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+bAdditionalProps,ex); 
          }

          try
          {
            bSelectProps.setFont(new Font("SansSerif",0,12));
            bSelectProps.setHorizontalTextPosition(4);
            bSelectProps.setBorder(new javax.swing.border.BevelBorder(0));
            bSelectProps.setPreferredSize(new Dimension(163,25));
            bSelectProps.setMinimumSize(new Dimension(163,25));
            bSelectProps.setMaximumSize(new Dimension(163,25));
            bSelectProps.setText("Select Properties To View");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+bSelectProps,ex); 
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

          try
          {
            JLabel2.setHorizontalAlignment(2);
            JLabel2.setFont(new Font("SansSerif",0,12));
            JLabel2.setForeground(new Color(-16777216));
            JLabel2.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+JLabel2,ex); 
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
        tTimeAvg= new javax.swing.JTextField();
        lTimeAvg= new javax.swing.JLabel();
        tOwner= new javax.swing.JTextField();
        lOwner= new javax.swing.JLabel();
        tSaveThreshold= new javax.swing.JTextField();
        lSaveThreshold= new javax.swing.JLabel();
        tSuffix= new javax.swing.JTextField();
        lSuffix= new javax.swing.JLabel();
        tFailThreshold= new javax.swing.JTextField();
        lFailThreshold= new javax.swing.JLabel();
        tSavePollCount= new javax.swing.JTextField();
        lSavePollCount= new javax.swing.JLabel();
        bAdditionalProps= new javax.swing.JButton();
        bSelectProps= new javax.swing.JButton();
        JLabel1= new javax.swing.JLabel();
        JLabel2= new javax.swing.JLabel();

  
        //<End_initVariables>
        bSelectProps.addActionListener(this);
        bAdditionalProps.addActionListener(this);
} 
  public void setUpGUI(Container container)
  {
//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(1,5,0,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(tTimeAvg,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,5,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
Top.add(lTimeAvg,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,4,0,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(tOwner,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,4,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
Top.add(lOwner,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,3,0,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(tSaveThreshold,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,3,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
Top.add(lSaveThreshold,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,2,0,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(tSuffix,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
Top.add(lSuffix,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,1,0,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(tFailThreshold,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
Top.add(lFailThreshold,cons);
inset = new Insets(15,5,5,5);
setConstraints(1,0,0,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(tSavePollCount,cons);
inset = new Insets(15,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
Top.add(lSavePollCount,cons);
inset = new Insets(20,5,5,5);
setConstraints(2,6,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
Top.add(bAdditionalProps,cons);
inset = new Insets(20,5,5,5);
setConstraints(1,6,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
Top.add(bSelectProps,cons);
inset = new Insets(20,5,5,5);
setConstraints(0,6,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(JLabel1,cons);
inset = new Insets(0,0,0,0);
setConstraints(0,7,0,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(JLabel2,cons);

  
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
public void disableAll()
{
	tTimeAvg.setEditable(false);
	tOwner.setEditable(false);
	tSaveThreshold.setEditable(false);
	tSuffix.setEditable(false); 
	tFailThreshold.setEditable(false); 
	tSavePollCount.setEditable(false);
	bAdditionalProps.setEnabled(false);
	bSelectProps.setEnabled(false);
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
	if(prop.get("timeAvg")!=null)
	{
		tTimeAvg.setText(prop.get("timeAvg").toString());
		vec.add("timeAvg");
	}
	if(prop.get("ownerName")!=null)
	{
		tOwner.setText(prop.get("ownerName").toString());
		vec.add("ownerName");
	}
	if(prop.get("saveOnThreshold")!=null)
	{
		tSaveThreshold.setText(prop.get("saveOnThreshold").toString());
		vec.add("saveOnThreshold");
	}
	if(prop.get("suffix")!=null)
	{
		tSuffix.setText(prop.get("suffix").toString()); 
		vec.add("suffix");
	}
	if(prop.get("failureThreshold")!=null)
	{
		tFailThreshold.setText(prop.get("failureThreshold").toString()); 
		vec.add("failureThreshold");
	}
	if(prop.get("savePollCount")!=null)
	{
		tSavePollCount.setText(prop.get("savePollCount").toString());
		vec.add("savePollCount");
	}
	}
	if(props.containsKey("tablecolumns"))
	{
		tableColumnProperties=(Properties)props.get("tablecolumns");
	}
	selectedProps=vec;
	additionalCriteria=prop;
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
	p.put("name","Statistic Name");
	p.put("active","Active");
	p.put("period","Interval");
	p.put("community","Community");
	p.put("oid","Object Id");
	p.put("dnsName","DNS Name");
	p.put("id","Poll Id");
	return p;
}
	  		

public Properties getProperties()
{
	Properties props = new Properties();
	if(!tTimeAvg.getText().trim().equals(""))
	{
		props.put("timeAvg",tTimeAvg.getText());
	}
	if(!tOwner.getText().trim().equals(""))
	{
		props.put("ownerName",tOwner.getText());
	}
	if(!tSaveThreshold.getText().trim().equals(""))
	{	
		props.put("saveOnThreshold",tSaveThreshold.getText());
	}
	if(!tSuffix.getText().trim().equals(""))
	{
		props.put("suffix",tSuffix.getText()); 
	}
	if(!tFailThreshold.getText().trim().equals(""))
	{
		props.put("failureThreshold",tFailThreshold.getText()); 
	}
	if(!tSavePollCount.getText().trim().equals(""))
	{
		props.put("savePollCount",tSavePollCount.getText());
	}
	if(criteria!=null && criteria.getProps().size()>0)
	{
	        Properties properties=criteria.getProps();
	        for(Enumeration e=properties.propertyNames();e.hasMoreElements();)
	        {
		   String str=(String)e.nextElement();
		   props.put(str,properties.get(str));
	        }
	}
	return props; 	
}		
public void actionPerformed(ActionEvent aevt)
 {
	if(aevt.getSource().equals(bSelectProps))
     	{
	boolean isNull=false;
	if(viewProps==null)
	{
	         viewProps=new showPerfProperties();
		isNull=true;
	}

	if(initialProps==null)
	{
		initialProps=new InitialProperties();
	}
	viewProps.setDisplayNames(initialProps.getIdVsDisplayNamesForPerformance());
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
      else if(aevt.getSource().equals(bAdditionalProps))
      {
	if(criteria==null)
	{
		criteria=new AdditionalCriteria("Additional Criteria",additionalCriteria,selectedProps);
	}
	else
	{
		Properties prop=criteria.getProps();
		criteria=new AdditionalCriteria("Additonal Criteria",prop,null);
	}
	
	criteria.setVisible(true);
      }	
 }
}
























