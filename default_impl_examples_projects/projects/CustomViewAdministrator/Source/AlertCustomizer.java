
//$Id: AlertCustomizer.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.tools.CustomView;




import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.event.*;

import com.adventnet.nms.util.XMLNode;
import com.adventnet.nms.util.XMLDataReader;
import com.adventnet.nms.util.NmsClientUtil;
import com.adventnet.nms.eventui.DEvent;


public class AlertCustomizer extends JPanel implements ItemListener,ActionListener
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JLabel lfilterViewName = null;
	javax.swing.JLabel lSeverity = null;
	javax.swing.JComboBox cSeverity = null;
	javax.swing.JLabel lPrevSev = null;
	javax.swing.JLabel lOwner = null;
	javax.swing.JTextField tOwner = null;
	javax.swing.JLabel lCategory = null;
	javax.swing.JLabel lGroup = null;
	javax.swing.JLabel lMessage = null;
	javax.swing.JTextField tMessage = null;
	javax.swing.JLabel lFailedObject = null;
	javax.swing.JTextField tFailedObject = null;
	javax.swing.JLabel lSource = null;
	javax.swing.JLabel lFromDate = null;
	javax.swing.JLabel lToDate = null;
	javax.swing.JTextField tGroup = null;
	javax.swing.JTextField tfilterViewName = null;
	javax.swing.JComboBox cPrevSev = null;
	javax.swing.JTextField tCategory = null;
	com.adventnet.nms.util.DateTimeComponent tFromDate = null;
	com.adventnet.nms.util.DateTimeComponent tToDate = null;
	javax.swing.JTextField tSource = null;
	javax.swing.JLabel lCreFromDate = null;
	javax.swing.JLabel lCreToDate = null;
	com.adventnet.nms.util.DateTimeComponent tCreFromDate = null;
	com.adventnet.nms.util.DateTimeComponent tCreToDate = null;
	javax.swing.JLabel lEventAge = null;
	javax.swing.JComboBox cEventPeriod = null;
	javax.swing.JComboBox cEventAge = null;
	javax.swing.JTextField tEventPeriod = null;
	javax.swing.JTextField tRefreshPeriod = null;
	javax.swing.JLabel lRefreshPeriod = null;
	javax.swing.JLabel lGroupViewMode = null;
	javax.swing.JComboBox cGroupViewMode = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JButton bSelectProps = null;
	javax.swing.JButton bAdditionalProps = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>
	ShowAlertProperties viewProps=null;	
	InitialProperties initialProps=null;
 	AdditionalCriteria criteria=null;
	Properties selectedColumns=null;
	Properties tableColumnProperties=null;
	Properties additionalCriteria=null;
	Vector selectedProps=null;
  public AlertCustomizer()
  {
    //<Begin_AlertCustomizer>
    this.init();
  
    //<End_AlertCustomizer>
  }

  public AlertCustomizer(java.applet.Applet applet)
  {
    //<Begin_AlertCustomizer_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_AlertCustomizer_java.applet.Applet>
  }


  


 

  

    public void init()
  {
        //<Begin_init> 
        if (initialized == true) return; 
        setPreferredSize(new Dimension(getPreferredSize().width+595,getPreferredSize().height+626)); 
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
            lfilterViewName.setHorizontalAlignment(2);
            lfilterViewName.setFont(new Font("SansSerif",0,12));
            lfilterViewName.setForeground(new Color(-16777216));
            lfilterViewName.setHorizontalTextPosition(4);
            lfilterViewName.setText("Custom View Name");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+lfilterViewName,ex); 
          }

          try
          {
            lSeverity.setHorizontalAlignment(2);
            lSeverity.setFont(new Font("SansSerif",0,12));
            lSeverity.setForeground(new Color(-16777216));
            lSeverity.setHorizontalTextPosition(4);
            lSeverity.setText("Severity");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+lSeverity,ex); 
          }

          try
          {
            cSeverity.setFont(new Font("SansSerif",0,12));
            cSeverity.setEditable(true);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+cSeverity,ex); 
          }

          try
          {
            lPrevSev.setHorizontalAlignment(2);
            lPrevSev.setFont(new Font("SansSerif",0,12));
            lPrevSev.setForeground(new Color(-16777216));
            lPrevSev.setHorizontalTextPosition(4);
            lPrevSev.setText("Previous Severity");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+lPrevSev,ex); 
          }

          try
          {
            lOwner.setHorizontalAlignment(2);
            lOwner.setFont(new Font("SansSerif",0,12));
            lOwner.setForeground(new Color(-16777216));
            lOwner.setHorizontalTextPosition(4);
            lOwner.setText("Owner");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+lOwner,ex); 
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
            lCategory.setHorizontalAlignment(2);
            lCategory.setFont(new Font("SansSerif",0,12));
            lCategory.setForeground(new Color(-16777216));
            lCategory.setHorizontalTextPosition(4);
            lCategory.setText("Category");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+lCategory,ex); 
          }

          try
          {
            lGroup.setHorizontalAlignment(2);
            lGroup.setFont(new Font("SansSerif",0,12));
            lGroup.setForeground(new Color(-16777216));
            lGroup.setHorizontalTextPosition(4);
            lGroup.setText("Group");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+lGroup,ex); 
          }

          try
          {
            lMessage.setHorizontalAlignment(2);
            lMessage.setFont(new Font("SansSerif",0,12));
            lMessage.setForeground(new Color(-16777216));
            lMessage.setHorizontalTextPosition(4);
            lMessage.setText("Message");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+lMessage,ex); 
          }

          try
          {
            tMessage.setHorizontalAlignment(2);
            tMessage.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tMessage,ex); 
          }

          try
          {
            lFailedObject.setHorizontalAlignment(2);
            lFailedObject.setFont(new Font("SansSerif",0,12));
            lFailedObject.setForeground(new Color(-16777216));
            lFailedObject.setHorizontalTextPosition(4);
            lFailedObject.setText("Failed Object");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+lFailedObject,ex); 
          }

          try
          {
            tFailedObject.setHorizontalAlignment(2);
            tFailedObject.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tFailedObject,ex); 
          }

          try
          {
            lSource.setHorizontalAlignment(2);
            lSource.setFont(new Font("SansSerif",0,12));
            lSource.setForeground(new Color(-16777216));
            lSource.setHorizontalTextPosition(4);
            lSource.setText("Source");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+lSource,ex); 
          }

          try
          {
            lFromDate.setHorizontalAlignment(2);
            lFromDate.setFont(new Font("SansSerif",0,12));
            lFromDate.setForeground(new Color(-16777216));
            lFromDate.setHorizontalTextPosition(4);
            lFromDate.setText("From Date/Time(modified)");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+lFromDate,ex); 
          }

          try
          {
            lToDate.setHorizontalAlignment(2);
            lToDate.setFont(new Font("SansSerif",0,12));
            lToDate.setForeground(new Color(-16777216));
            lToDate.setHorizontalTextPosition(4);
            lToDate.setText("To Date/Time(modified)");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+lToDate,ex); 
          }

          try
          {
            tGroup.setHorizontalAlignment(2);
            tGroup.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tGroup,ex); 
          }

          try
          {
            tfilterViewName.setHorizontalAlignment(2);
            tfilterViewName.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tfilterViewName,ex); 
          }

          try
          {
            cPrevSev.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+cPrevSev,ex); 
          }

          try
          {
            tCategory.setHorizontalAlignment(2);
            tCategory.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tCategory,ex); 
          }

          try
          {
            tFromDate.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tFromDate,ex); 
          }

          try
          {
            tToDate.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tToDate,ex); 
          }

          try
          {
            tSource.setHorizontalAlignment(2);
            tSource.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tSource,ex); 
          }

          try
          {
            lCreFromDate.setHorizontalAlignment(2);
            lCreFromDate.setFont(new Font("SansSerif",0,12));
            lCreFromDate.setForeground(new Color(-16777216));
            lCreFromDate.setHorizontalTextPosition(4);
            lCreFromDate.setText("From Date/Time(created)");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+lCreFromDate,ex); 
          }

          try
          {
            lCreToDate.setHorizontalAlignment(2);
            lCreToDate.setFont(new Font("SansSerif",0,12));
            lCreToDate.setForeground(new Color(-16777216));
            lCreToDate.setHorizontalTextPosition(4);
            lCreToDate.setText("To Date/Time(created)");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+lCreToDate,ex); 
          }

          try
          {
            tCreFromDate.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tCreFromDate,ex); 
          }

          try
          {
            tCreToDate.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tCreToDate,ex); 
          }

          try
          {
            lEventAge.setHorizontalAlignment(2);
            lEventAge.setFont(new Font("SansSerif",0,12));
            lEventAge.setForeground(new Color(-16777216));
            lEventAge.setHorizontalTextPosition(4);
            lEventAge.setText("Event Age");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+lEventAge,ex); 
          }

          try
          {
            cEventPeriod.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+cEventPeriod,ex); 
          }

          try
          {
            cEventAge.setFont(new Font("SansSerif",0,12));
            cEventAge.setPreferredSize(new Dimension(161,22));
            cEventAge.setMinimumSize(new Dimension(161,22));
            cEventAge.setMaximumSize(new Dimension(161,22));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+cEventAge,ex); 
          }

          try
          {
            tEventPeriod.setHorizontalAlignment(2);
            tEventPeriod.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tEventPeriod,ex); 
          }

          try
          {
            tRefreshPeriod.setHorizontalAlignment(2);
            tRefreshPeriod.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tRefreshPeriod,ex); 
          }

          try
          {
            lRefreshPeriod.setHorizontalAlignment(2);
            lRefreshPeriod.setFont(new Font("SansSerif",0,12));
            lRefreshPeriod.setForeground(new Color(-16777216));
            lRefreshPeriod.setHorizontalTextPosition(4);
            lRefreshPeriod.setText("Refresh Period");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+lRefreshPeriod,ex); 
          }

          try
          {
            lGroupViewMode.setHorizontalAlignment(2);
            lGroupViewMode.setFont(new Font("SansSerif",0,12));
            lGroupViewMode.setForeground(new Color(-16777216));
            lGroupViewMode.setHorizontalTextPosition(4);
            lGroupViewMode.setText("Group View Mode");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+lGroupViewMode,ex); 
          }

          try
          {
            cGroupViewMode.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+cGroupViewMode,ex); 
          }

          try
          {
            bSelectProps.setFont(new Font("SansSerif",0,12));
            bSelectProps.setHorizontalTextPosition(4);
            bSelectProps.setBorder(new javax.swing.border.BevelBorder(0));
            bSelectProps.setMaximumSize(new Dimension(120,25));
            bSelectProps.setMinimumSize(new Dimension(120,25));
            bSelectProps.setPreferredSize(new Dimension(120,25));
            bSelectProps.setText("Select Properties To View");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+bSelectProps,ex); 
          }

          try
          {
            bAdditionalProps.setFont(new Font("SansSerif",0,12));
            bAdditionalProps.setHorizontalTextPosition(4);
            bAdditionalProps.setText("Additonal Criteria");
            bAdditionalProps.setBorder(new javax.swing.border.BevelBorder(0));
            bAdditionalProps.setMinimumSize(new Dimension(130,25));
            bAdditionalProps.setMaximumSize(new Dimension(130,25));
            bAdditionalProps.setPreferredSize(new Dimension(130,25));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+bAdditionalProps,ex); 
          }
		bAdditionalProps.setPreferredSize(new Dimension(bAdditionalProps.getPreferredSize().width+23,bAdditionalProps.getPreferredSize().height+2));
		bSelectProps.setPreferredSize(new Dimension(bSelectProps.getPreferredSize().width+33,bSelectProps.getPreferredSize().height+5));

  
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
        lfilterViewName= new javax.swing.JLabel();
        lSeverity= new javax.swing.JLabel();
        cSeverity= new javax.swing.JComboBox();
        lPrevSev= new javax.swing.JLabel();
        lOwner= new javax.swing.JLabel();
        tOwner= new javax.swing.JTextField();
        lCategory= new javax.swing.JLabel();
        lGroup= new javax.swing.JLabel();
        lMessage= new javax.swing.JLabel();
        tMessage= new javax.swing.JTextField();
        lFailedObject= new javax.swing.JLabel();
        tFailedObject= new javax.swing.JTextField();
        lSource= new javax.swing.JLabel();
        lFromDate= new javax.swing.JLabel();
        lToDate= new javax.swing.JLabel();
        tGroup= new javax.swing.JTextField();
        tfilterViewName= new javax.swing.JTextField();
        cPrevSev= new javax.swing.JComboBox();
        tCategory= new javax.swing.JTextField();
        tFromDate= new com.adventnet.nms.util.DateTimeComponent();
        tToDate= new com.adventnet.nms.util.DateTimeComponent();
        tSource= new javax.swing.JTextField();
        lCreFromDate= new javax.swing.JLabel();
        lCreToDate= new javax.swing.JLabel();
        tCreFromDate= new com.adventnet.nms.util.DateTimeComponent();
        tCreToDate= new com.adventnet.nms.util.DateTimeComponent();
        lEventAge= new javax.swing.JLabel();
        cEventPeriod= new javax.swing.JComboBox();
        cEventAge= new javax.swing.JComboBox();
        tEventPeriod= new javax.swing.JTextField();
        tRefreshPeriod= new javax.swing.JTextField();
        lRefreshPeriod= new javax.swing.JLabel();
        lGroupViewMode= new javax.swing.JLabel();
        cGroupViewMode= new javax.swing.JComboBox();
        JPanel2= new javax.swing.JPanel();
        bSelectProps= new javax.swing.JButton();
        bAdditionalProps= new javax.swing.JButton();

  
        //<End_initVariables>
	
	cEventAge.addItemListener(this);
	cEventPeriod.addItemListener(this);
	cSeverity.addItemListener(this);
	cPrevSev.addItemListener(this);
	bSelectProps.addActionListener(this);
	bAdditionalProps.addActionListener(this);

	
	Vector v=getSeverity();
	cSeverity.addItem("all");
	cPrevSev.addItem("all");
	
	tRefreshPeriod.setVisible(false);
	lRefreshPeriod.setVisible(false);
	for(int i=0;i<v.size();i++)
	{
		cSeverity.addItem(v.elementAt(i).toString());
		cPrevSev.addItem(v.elementAt(i).toString());
	}
	addItems(cEventAge);	
	cEventPeriod.addItem(">");
	cEventPeriod.addItem(">=");
	cEventPeriod.addItem("<");
	cEventPeriod.addItem("<=");
	cEventPeriod.setEnabled(false);
	cGroupViewMode.addItem("none");
	cGroupViewMode.addItem("max");
	cGroupViewMode.addItem("latest");
  } 
public void addItems(JComboBox combo)
{
	combo.addItem("Any");
	combo.addItem("Age in min");
	combo.addItem("Age in hrs");
	combo.addItem("Age in days");
	combo.addItem("Today");
	combo.addItem("Yesterday");	
}
  
  public void setUpGUI(Container container)
  {
//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));
Top.add(JPanel1,BorderLayout.CENTER);
JPanel1.setLayout(new GridBagLayout());
inset = new Insets(15,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel1.add(lfilterViewName,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel1.add(lSeverity,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,1,0,1,0.1,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel1.add(cSeverity,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel1.add(lPrevSev,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,3,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel1.add(lOwner,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,3,0,1,0.1,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel1.add(tOwner,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,4,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel1.add(lCategory,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,5,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel1.add(lGroup,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,6,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel1.add(lMessage,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,6,0,1,0.1,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel1.add(tMessage,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,7,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel1.add(lFailedObject,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,7,0,1,0.1,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel1.add(tFailedObject,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,8,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel1.add(lSource,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,9,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel1.add(lFromDate,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,10,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel1.add(lToDate,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,5,0,1,0.1,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel1.add(tGroup,cons);
inset = new Insets(15,5,5,5);
setConstraints(1,0,0,1,0.1,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel1.add(tfilterViewName,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,2,0,1,0.1,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel1.add(cPrevSev,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,4,0,1,0.1,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel1.add(tCategory,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,9,0,1,0.1,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel1.add(tFromDate,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,10,0,1,0.1,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel1.add(tToDate,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,8,0,1,0.1,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel1.add(tSource,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,11,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel1.add(lCreFromDate,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,12,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel1.add(lCreToDate,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,11,0,1,0.1,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel1.add(tCreFromDate,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,12,0,1,0.1,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel1.add(tCreToDate,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,14,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel1.add(lEventAge,cons);
inset = new Insets(5,5,5,5);
setConstraints(2,14,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel1.add(cEventPeriod,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,14,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel1.add(cEventAge,cons);
inset = new Insets(5,5,5,5);
setConstraints(3,14,0,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel1.add(tEventPeriod,cons);
inset = new Insets(5,5,5,5);
setConstraints(3,15,0,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel1.add(tRefreshPeriod,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,15,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel1.add(lRefreshPeriod,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,13,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel1.add(lGroupViewMode,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,13,0,1,0.1,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel1.add(cGroupViewMode,cons);
Top.add(JPanel2,BorderLayout.SOUTH);
JPanel2.setLayout(new FlowLayout(1,5,5));
JPanel2.add(bSelectProps);
JPanel2.add(bAdditionalProps);

  
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

public void disableAll()
{
	tfilterViewName.setEditable(false);
	tOwner.setEditable(false);
	cSeverity.setEditable(false);
	cPrevSev.setEditable(false);
	tCategory.setEditable(false); 
	tFailedObject.setEditable(false); 
	tMessage.setEditable(false); 
	tFromDate.setEnabled(false);
	
	tToDate.setEnabled(false); 
	tCreFromDate.setEnabled(false);
	tCreToDate.setEnabled(false); 
	tSource.setEnabled(false);
	cEventAge.setEnabled(false);
	cEventPeriod.setEnabled(false);
	tGroup.setEditable(false);
	cSeverity.setEnabled(false);
	cPrevSev.setEnabled(false);
	cEventAge.setEnabled(false);
	tEventPeriod.setEditable(false);
	bSelectProps.setEnabled(false);
	bAdditionalProps.setEnabled(false);
	tRefreshPeriod.setEnabled(false);
	cGroupViewMode.setEnabled(false);
}
public void setProperties(Properties props)
{
	Vector vec=new Vector();	
	if(props==null)
	{
		return;
	}
	
	if(props.get("TREE-NAME")!=null)
	{
		tfilterViewName.setText(props.getProperty("TREE-NAME"));
	}
	Properties prop=null;
	if(props.containsKey("viewcriteria"))
	{
		
	
	 prop=(Properties)props.get("viewcriteria");
	
	if(prop.get("stringseverity")!=null)
	{
		cSeverity.setSelectedItem(prop.get("stringseverity"));
		vec.add("stringseverity");
	}
	if(prop.get("stringpreviousseverity")!=null)
	{
		cPrevSev.setSelectedItem(prop.get("stringpreviousseverity"));
		vec.add("stringpreviousseverity");
	}
	if(prop.get("category")!=null)
	{
		tCategory.setText(prop.get("category").toString()); 
		vec.add("category");
	}
	if(prop.get("entity")!=null)
	{
		tFailedObject.setText(prop.get("entity").toString()); 
		vec.add("entity");
	}
	if(prop.get("message")!=null)
	{
		tMessage.setText(prop.get("message").toString()); 
		vec.add("message");
	}
	if(prop.get("source")!=null)
	{
		tSource.setText(prop.get("source").toString());
		vec.add("source");
	}	
	if(prop.get("AlertAge")!=null)
	{
		setAlertAge(prop.get("AlertAge").toString());
		vec.add("AlertAge");
	}	
	if(prop.get("groupName")!=null)
	{
		tGroup.setText(prop.get("groupName").toString());
		vec.add("groupName");
	}
	if(prop.get("modTime")!=null)
	{
		String time[]=NmsClientUtil.splitDate(prop.get("modTime").toString());
		tFromDate.setDateTime(time[0]);	
		tToDate.setDateTime(time[1]); 	
		vec.add("modTime");
	}
	if(prop.get("createTime")!=null)
	{
		String time[]=NmsClientUtil.splitDate(prop.get("createTime").toString());
		tCreFromDate.setDateTime(time[0]);	
		tCreToDate.setDateTime(time[1]); 	
		vec.add("createTime");
	}
	if(prop.get("who")!=null)
	{
		tOwner.setText(prop.get("who").toString());
		vec.add("who");
	}
	if(prop.get("groupViewMode")!=null)
	{
		cGroupViewMode.setSelectedItem(prop.get("groupViewMode"));
		vec.add("groupViewMode");
	}
	}
	selectedProps=vec;
	if(props.containsKey("tablecolumns"))
	{
		tableColumnProperties=(Properties)props.get("tablecolumns");
	}
	
	additionalCriteria=prop;
	
}
private void setAlertAge(String str)
{
	StringTokenizer str1=new StringTokenizer(str);
	String age=str1.nextToken();
	String type=str1.nextToken();
	String period=str1.nextToken();
	String rPeriod=str1.nextToken();
	if(age.trim().equals("RHr"))
	{
		cEventAge.setSelectedItem("Age in hrs");
		cEventPeriod.setSelectedItem(type);  	
		tEventPeriod.setText(period);
	}
	if(age.trim().equals("RMi"))
	{
		cEventAge.setSelectedItem("Age in min");
		cEventPeriod.setSelectedItem(type);  	
		tEventPeriod.setText(period);
	}
	if(age.trim().equals("RDy"))
	{
		cEventAge.setSelectedItem("Age in days");
		cEventPeriod.setSelectedItem(type);  	
		tEventPeriod.setText(period);
          }
	if(age.trim().equals("ADy") && period.trim().equals("2"))
	{
		cEventAge.setSelectedItem("Yesterday");
	}
	else if(age.trim().equals("ADy") && period.trim().equals("1"))
	{
		cEventAge.setSelectedItem("Today");
	}
	tRefreshPeriod.setText(rPeriod);
}
public String getAlertAge()
{
      StringBuffer alertAge=new StringBuffer();
      if(cEventAge.getSelectedItem().equals("Age in hrs"))
      {
	alertAge.append("RHr"+" ");
	alertAge.append(cEventPeriod.getSelectedItem().toString()+" ");
	alertAge.append(tEventPeriod.getText()+" "+tRefreshPeriod.getText());
      }
      else if(cEventAge.getSelectedItem().equals("Age in min"))
      {
	alertAge.append("RMi"+" ");
	alertAge.append(cEventPeriod.getSelectedItem().toString()+" ");
	alertAge.append(tEventPeriod.getText()+" "+tRefreshPeriod.getText());
      }	
      else if(cEventAge.getSelectedItem().equals("Age in days"))
      {
	alertAge.append("RDy"+" ");
	alertAge.append(cEventPeriod.getSelectedItem().toString()+" ");
	alertAge.append(tEventPeriod.getText()+" "+tRefreshPeriod.getText());
      } 
      else if(cEventAge.getSelectedItem().equals("Today"))
      {
	alertAge.append("ADy"+" ");
	alertAge.append("==  ");
	alertAge.append(" 1  "+tRefreshPeriod.getText());
      }
      else if(cEventAge.getSelectedItem().equals("Age in hrs"))
      {
	alertAge.append("ADy"+" ");
	alertAge.append("== ");
	alertAge.append(" 2 "+tRefreshPeriod.getText());
      }
      return alertAge.toString();	
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
	p.put("severity","Status");
	p.put("who","Owner");
	p.put("message","Alarm Message");
	p.put("entity","Failure Object");
	p.put("modTime","Date/Time");
	p.put("groupName","Alarm Group");
	return p;
}

public String getViewName()
{
	return tfilterViewName.getText();	
}


public Properties getProperties()
{
	Properties props = new Properties();
	if(!cSeverity.getSelectedItem().equals("all"))
	{
		props.put("stringseverity",cSeverity.getSelectedItem());
	}
	if(!tCategory.getText().trim().equals(""))
	{
		props.put("category",tCategory.getText()); 
	}
	if(!tFailedObject.getText().trim().equals(""))
	{
		props.put("entity",tFailedObject.getText()); 
	}
	if(!tMessage.getText().trim().equals(""))
	{
		props.put("message",tMessage.getText());
	}
	if(!tSource.getText().trim().equals(""))
	{
		props.put("source",tSource.getText());
	}
	
	if(!cPrevSev.getSelectedItem().equals("all"))
	{
		props.put("stringpreviousseverity",cPrevSev.getSelectedItem());
	}
	if(!tOwner.getText().trim().equals(""))
	{
		props.put("who",tOwner.getText());
	}
	if(!tGroup.getText().trim().equals(""))
	{
		props.put("groupName",tGroup.getText());
	}
	if(!cGroupViewMode.getSelectedItem().toString().equals(""))
	{
		props.put("groupViewMode",cGroupViewMode.getSelectedItem());
	}
	String from=tFromDate.getDateTime();
	String to=tToDate.getDateTime();
	Date dt;
	String time1=null;
	String time2=null;	
	if(from!=null && !from.trim().equals(""))
	{
		try
		{
			dt=NmsClientUtil.parseDate(from);
			time1=String.valueOf(DEvent.getParsedTime(dt));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	else
	     time1="";
	if(to!=null && !to.trim().equals(""))
	{
		try
		{
			dt=NmsClientUtil.parseDate(to);
			time2=String.valueOf(DEvent.getParsedTime(dt));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	else
		time2="";	
	if(!time1.equals("") || !time2.equals(""))
	{
		props.put("modTime","<between> "+time1+" and "+time2);
	}
	from=tCreFromDate.getDateTime();
	to=tCreToDate.getDateTime();
	if(from!=null && !from.trim().equals(""))
	{
		try
		{
			dt=NmsClientUtil.parseDate(from);
			time1=String.valueOf(DEvent.getParsedTime(dt));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	else
	     time1="";
	if(to!=null && !to.trim().equals(""))
	{
		try
		{
			dt=NmsClientUtil.parseDate(to);
			time2=String.valueOf(DEvent.getParsedTime(dt));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	else
		time2="";	
	if(!time1.equals("") || !time2.equals(""))
	{
		props.put("createTime","<between> "+time1+" and "+time2);
			
	}
	if(!cEventAge.getSelectedItem().toString().equals("Any"))
	{
		props.put("AlertAge",getAlertAge());
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
	
 public void itemStateChanged(ItemEvent ie)
 {
    if(ie.getSource().equals(cEventAge))
     {
	Object item=cEventAge.getSelectedItem();
	if(!item.toString().equals("Any"))
	{
	  cEventPeriod.setEnabled(true);
	  tRefreshPeriod.setVisible(true);
	  lRefreshPeriod.setVisible(true);
	  tRefreshPeriod.setText("1");
	  tEventPeriod.setText("1");	
	  tEventPeriod.setEnabled(true);	
	}
	else 
	{
	  cEventPeriod.setEnabled(false);
	  tEventPeriod.setText("Time");
	  tEventPeriod.setEnabled(false);
	  tRefreshPeriod.setVisible(false);		
	  lRefreshPeriod.setVisible(false);
	}
	if(item.toString().equals("Today") || item.toString().equals("Yesterday"))
	{
	  cEventPeriod.setEnabled(false);
	  tEventPeriod.setText("Time");	
	tEventPeriod.setEnabled(false);		
	}
     }
     else if(ie.getSource().equals(cSeverity))
     {
           DefaultComboBoxModel model=(DefaultComboBoxModel)cSeverity.getModel();
	Object item=cSeverity.getSelectedItem();
	if(model.getIndexOf(item) == -1)
	 {
		  model.addElement(item);
	 }
     }
 }
 public void actionPerformed(ActionEvent aevt)
 {
    if(aevt.getSource().equals(bSelectProps))
      {
	boolean isNull=false;
	if(viewProps==null)
	{
	         viewProps=new ShowAlertProperties();
		isNull=true;
	}
	
	if(initialProps==null)
	{
		initialProps=new InitialProperties();
	}
	viewProps.setDisplayNames(initialProps.getIdVsDisplayNamesForAlerts());
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
		criteria=new AdditionalCriteria("Additonal Criteria",additionalCriteria,selectedProps);
		
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




















































