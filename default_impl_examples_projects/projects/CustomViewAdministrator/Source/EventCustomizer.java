
//$Id: EventCustomizer.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $
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

public class EventCustomizer extends JPanel implements ItemListener,ActionListener
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JLabel lfilterViewName = null;
	javax.swing.JLabel lSeverity = null;
	javax.swing.JComboBox cSeverity = null;
	javax.swing.JLabel lMessage = null;
	javax.swing.JLabel lCategory = null;
	javax.swing.JTextField tCategory = null;
	javax.swing.JLabel lDomain = null;
	javax.swing.JLabel lNetwork = null;
	javax.swing.JLabel lNode = null;
	javax.swing.JTextField tNode = null;
	javax.swing.JLabel lFailedObject = null;
	javax.swing.JTextField tFailedObject = null;
	javax.swing.JLabel lSource = null;
	javax.swing.JLabel lFromDate = null;
	javax.swing.JLabel lToDate = null;
	javax.swing.JTextField tNetwork = null;
	javax.swing.JTextField tfilterViewName = null;
	javax.swing.JTextField tMessage = null;
	javax.swing.JTextField tDomain = null;
	com.adventnet.nms.util.DateTimeComponent tToDate = null;
	javax.swing.JTextField tSource = null;
	javax.swing.JLabel lEventAge = null;
	javax.swing.JComboBox cEventAge = null;
	javax.swing.JComboBox cEventPeriod = null;
	javax.swing.JTextField tEventPeriod = null;
	javax.swing.JLabel lRefreshPeriod = null;
	javax.swing.JTextField tRefreshPeriod = null;
	com.adventnet.nms.util.DateTimeComponent tFromDate = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JButton bSelectProps = null;
	javax.swing.JButton bAdditionalProps = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>
	ShowEventProperties viewProps=null;
	InitialProperties initialProps=null;
	AdditionalCriteria criteria=null;
	Properties selectedColumns=null;
	Properties tableColumnProperties=null;
	Vector selectedProps=null;	
	Properties additionalCriteria=null;
	
  public EventCustomizer()
  {
    //<Begin_EventCustomizer>
    this.init();
  
    //<End_EventCustomizer>
  }

  public EventCustomizer(java.applet.Applet applet)
  {
    //<Begin_EventCustomizer_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_EventCustomizer_java.applet.Applet>
  }


  


 

  

    public void init()
  {
        //<Begin_init> 
        if (initialized == true) return; 
        setPreferredSize(new Dimension(getPreferredSize().width+501,getPreferredSize().height+569)); 
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
            tCategory.setHorizontalAlignment(2);
            tCategory.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tCategory,ex); 
          }

          try
          {
            lDomain.setHorizontalAlignment(2);
            lDomain.setFont(new Font("SansSerif",0,12));
            lDomain.setForeground(new Color(-16777216));
            lDomain.setHorizontalTextPosition(4);
            lDomain.setText("Domain");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+lDomain,ex); 
          }

          try
          {
            lNetwork.setHorizontalAlignment(2);
            lNetwork.setFont(new Font("SansSerif",0,12));
            lNetwork.setForeground(new Color(-16777216));
            lNetwork.setHorizontalTextPosition(4);
            lNetwork.setText("Network");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+lNetwork,ex); 
          }

          try
          {
            lNode.setHorizontalAlignment(2);
            lNode.setFont(new Font("SansSerif",0,12));
            lNode.setForeground(new Color(-16777216));
            lNode.setHorizontalTextPosition(4);
            lNode.setText("Node");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+lNode,ex); 
          }

          try
          {
            tNode.setHorizontalAlignment(2);
            tNode.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tNode,ex); 
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
            lFromDate.setText("From Date/Time");
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
            lToDate.setText("To Date/Time");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+lToDate,ex); 
          }

          try
          {
            tNetwork.setHorizontalAlignment(2);
            tNetwork.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tNetwork,ex); 
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
            tMessage.setHorizontalAlignment(2);
            tMessage.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tMessage,ex); 
          }

          try
          {
            tDomain.setHorizontalAlignment(2);
            tDomain.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tDomain,ex); 
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
            cEventAge.setFont(new Font("SansSerif",0,12));
            cEventAge.setMaximumSize(new Dimension(161,22));
            cEventAge.setMinimumSize(new Dimension(161,22));
            cEventAge.setPreferredSize(new Dimension(161,22));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+cEventAge,ex); 
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
            tEventPeriod.setHorizontalAlignment(2);
            tEventPeriod.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tEventPeriod,ex); 
          }

          try
          {
            lRefreshPeriod.setHorizontalAlignment(2);
            lRefreshPeriod.setFont(new Font("SansSerif",0,12));
            lRefreshPeriod.setForeground(new Color(-16777216));
            lRefreshPeriod.setHorizontalTextPosition(4);
            lRefreshPeriod.setText("Refresh Period in minutes");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+lRefreshPeriod,ex); 
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
            tFromDate.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tFromDate,ex); 
          }

          try
          {
            bSelectProps.setFont(new Font("SansSerif",0,12));
            bSelectProps.setHorizontalTextPosition(4);
            bSelectProps.setBorder(new javax.swing.border.BevelBorder(0));
            bSelectProps.setPreferredSize(new Dimension(130,25));
            bSelectProps.setMaximumSize(new Dimension(130,25));
            bSelectProps.setMinimumSize(new Dimension(130,25));
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
            bAdditionalProps.setMaximumSize(new Dimension(130,25));
            bAdditionalProps.setMinimumSize(new Dimension(130,25));
            bAdditionalProps.setPreferredSize(new Dimension(130,25));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+bAdditionalProps,ex); 
          }
		bAdditionalProps.setPreferredSize(new Dimension(bAdditionalProps.getPreferredSize().width+23,bAdditionalProps.getPreferredSize().height+2));
		bSelectProps.setPreferredSize(new Dimension(bSelectProps.getPreferredSize().width+23,bSelectProps.getPreferredSize().height+2));

  
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
        lMessage= new javax.swing.JLabel();
        lCategory= new javax.swing.JLabel();
        tCategory= new javax.swing.JTextField();
        lDomain= new javax.swing.JLabel();
        lNetwork= new javax.swing.JLabel();
        lNode= new javax.swing.JLabel();
        tNode= new javax.swing.JTextField();
        lFailedObject= new javax.swing.JLabel();
        tFailedObject= new javax.swing.JTextField();
        lSource= new javax.swing.JLabel();
        lFromDate= new javax.swing.JLabel();
        lToDate= new javax.swing.JLabel();
        tNetwork= new javax.swing.JTextField();
        tfilterViewName= new javax.swing.JTextField();
        tMessage= new javax.swing.JTextField();
        tDomain= new javax.swing.JTextField();
        tToDate= new com.adventnet.nms.util.DateTimeComponent();
        tSource= new javax.swing.JTextField();
        lEventAge= new javax.swing.JLabel();
        cEventAge= new javax.swing.JComboBox();
        cEventPeriod= new javax.swing.JComboBox();
        tEventPeriod= new javax.swing.JTextField();
        lRefreshPeriod= new javax.swing.JLabel();
        tRefreshPeriod= new javax.swing.JTextField();
        tFromDate= new com.adventnet.nms.util.DateTimeComponent();
        JPanel2= new javax.swing.JPanel();
        bSelectProps= new javax.swing.JButton();
        bAdditionalProps= new javax.swing.JButton();

  
        //<End_initVariables>
	
	
	cEventAge.addItemListener(this);
	cEventPeriod.addItemListener(this);
	bSelectProps.addActionListener(this);
	bAdditionalProps.addActionListener(this);


	Vector v=getSeverity();
	cSeverity.addItem("all");
	cSeverity.addItemListener(this);
	for(int i=0;i<v.size();i++)
	{
		cSeverity.addItem(v.elementAt(i).toString());
	}
	tRefreshPeriod.setVisible(false);
	lRefreshPeriod.setVisible(false);
	addItems(cEventAge);	
	cEventPeriod.addItem(">");
	cEventPeriod.addItem(">=");
	cEventPeriod.addItem("<");
	cEventPeriod.addItem("<=");
	cEventPeriod.setEnabled(false);
	
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
setConstraints(1,1,0,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel1.add(cSeverity,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel1.add(lMessage,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,3,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel1.add(lCategory,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,3,0,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel1.add(tCategory,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,4,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel1.add(lDomain,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,5,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel1.add(lNetwork,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,6,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel1.add(lNode,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,6,0,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel1.add(tNode,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,7,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel1.add(lFailedObject,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,7,0,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
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
setConstraints(1,5,0,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel1.add(tNetwork,cons);
inset = new Insets(15,5,5,5);
setConstraints(1,0,0,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel1.add(tfilterViewName,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,2,0,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel1.add(tMessage,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,4,0,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel1.add(tDomain,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,10,0,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel1.add(tToDate,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,8,0,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel1.add(tSource,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,11,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel1.add(lEventAge,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,11,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel1.add(cEventAge,cons);
inset = new Insets(5,5,5,5);
setConstraints(2,11,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel1.add(cEventPeriod,cons);
inset = new Insets(5,5,5,5);
setConstraints(3,11,0,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel1.add(tEventPeriod,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,12,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel1.add(lRefreshPeriod,cons);
inset = new Insets(5,5,5,5);
setConstraints(3,12,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
JPanel1.add(tRefreshPeriod,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,9,0,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel1.add(tFromDate,cons);
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
	tNode.setEditable(false);
	cSeverity.setEnabled(false);
	tCategory.setEditable(false); 
	tFailedObject.setEditable(false); 
	tNetwork.setEditable(false);
	tMessage.setEditable(false);
	tDomain.setEditable(false); 
	tFromDate.setEnabled(false);
	tToDate.setEnabled(false); 
	tSource.setEditable(false);
	cEventAge.setEnabled(false);
	cEventPeriod.setEnabled(false);
	bSelectProps.setEnabled(false);
	bAdditionalProps.setEnabled(false);
	tEventPeriod.setEditable(false);
	tRefreshPeriod.setEditable(false);
}
public void enableAllComponents()
{
	tfilterViewName.setEditable(true);
	tNode.setEditable(true);
	cSeverity.setEditable(true);
	tCategory.setEditable(true); 
	tFailedObject.setEditable(true); 
	tNetwork.setEditable(true);
	tMessage.setEditable(true);
	tDomain.setEditable(true); 
	tFromDate.setEnabled(true);
	tToDate.setEnabled(true); 
	tSource.setEditable(true);
	cEventAge.setEditable(true);
	cEventPeriod.setEditable(true);
}
	
public String getViewName()
{
	return tfilterViewName.getText();
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
	
	if(prop.get("node")!=null)
	{
		tNode.setText(prop.get("node").toString());	
		vec.add("node");
	}
	if(prop.get("stringseverity")!=null)
	{
		cSeverity.setSelectedItem(prop.get("stringseverity"));
		vec.add("stringseverity");
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
	if(prop.get("network")!=null)
	{
		tNetwork.setText(prop.get("network").toString());
		vec.add("network");
	}
	if(prop.get("text")!=null)
	{
		tMessage.setText(prop.get("text").toString());
		vec.add("text");
	}
	if(prop.get("domain")!=null)
	{
		tDomain.setText(prop.get("domain").toString()); 
		vec.add("domain");
	}
	if(prop.get("time")!=null)
	{
		String time[]=NmsClientUtil.splitDate(prop.get("time").toString());
		tFromDate.setDateTime(time[0]);	
		tToDate.setDateTime(time[1]); 		
		/*StringTokenizer buf=new StringTokenizer(prop.get("time").toString());
		buf.nextToken();
		String from=null;
		String to=null;
		if((from=buf.nextToken())!=null)
		{
			//tFromDate.setText(from);	
		}
		if(buf.nextToken()!=null)
		{
			if((to=buf.nextToken())!=null)
			{
				//tToDate.setText(to); 		
			}	
		}*/
		vec.add("time");
	}
	if(prop.get("source")!=null)
	{	
		tSource.setText(prop.get("source").toString());
		vec.add("source");
	}
	if(prop.get("eventAge")!=null)
	{
		setEventAge(prop.get("eventAge").toString());
		vec.add("eventAge");
	}
	}
	if(props.containsKey("tablecolumns"))
	{
		tableColumnProperties=(Properties)props.get("tablecolumns");
	}
	additionalCriteria=prop;
	selectedProps=vec;	
}
private void setEventAge(String str)
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
public String getEventAge()
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
	p.put("source","Source");
	p.put("time","Date");
	p.put("text","Message");
	return p;
}

public Properties getProperties()
{
	Properties props = new Properties();
	if(!tNode.getText().trim().equals(""))
	{
		props.put("node",tNode.getText());
	}
	if(!cSeverity.getSelectedItem().equals("Any"))
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
	if(!tNetwork.getText().trim().equals(""))
	{
		props.put("network",tNetwork.getText());
	}
	if(!tMessage.getText().trim().equals(""))
	{
		props.put("text",tMessage.getText());
	}
	if(!tDomain.getText().trim().equals(""))
	{
		props.put("domain",tDomain.getText()); 
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
		props.put("time","<between> "+time1+" and "+time2);
	}
	if(!tSource.getText().trim().equals(""))
	{
		props.put("source",tSource.getText());
	}
	if(!cEventAge.getSelectedItem().equals("Any"))
	{
		props.put("eventAge",getEventAge());
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
	  tEventPeriod.setText("Time");
	  tEventPeriod.setEnabled(false);
	  cEventPeriod.setEnabled(false);
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
	         viewProps=new ShowEventProperties();
	         isNull=true;
	}
	
	if(initialProps==null)
	{
		initialProps=new InitialProperties();
	}
	viewProps.setDisplayNames(initialProps.getIdVsDisplayNamesForEvents());
	if(tableColumnProperties==null && isNull)
	{
		viewProps.setSelection(getDefaultColumns());
	}
	if(tableColumnProperties!=null && isNull) 
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





































