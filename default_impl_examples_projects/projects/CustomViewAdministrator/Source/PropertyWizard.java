//$Id :$
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory




package com.adventnet.nms.tools.CustomView;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;



public class PropertyWizard extends JPanel implements ActionListener
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JButton previous = null;
	javax.swing.JButton next = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JPanel CardPanel = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>
	CardLayout card=null;
	NDCustomizer1 cust1=null;
	NDCustomizer2 cust2=null;
	TreeProperties treeProps=null;
	AlertCustomizer alertProps=null;
	EventCustomizer eventProps=null;
	PerfCustomizer1 perfProps1=null;
	PerfCustomizer2 perfProps2=null;
	PerfCustomizer3 perfProps3=null;
	ParentSelection parent=null;
	String customType="";
	int noOfScreens=0;
	int count=0;
	Properties customProps=null;
  public PropertyWizard()
  {
    //<Begin_PropertyWizard>
    this.init();
  
    //<End_PropertyWizard>
	
  }

  public PropertyWizard(java.applet.Applet applet)
  {
    //<Begin_PropertyWizard_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_PropertyWizard_java.applet.Applet>
  }
public PropertyWizard(String type,Properties props)
{
	customType=type;
	customProps=props;
	this.init();
}
	

  


 

  

    public void init()
  {
        //<Begin_init> 
        if (initialized == true) return; 
        setPreferredSize(new Dimension(getPreferredSize().width+491,getPreferredSize().height+528)); 
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
            previous.setFont(new Font("SansSerif",0,12));
            previous.setHorizontalTextPosition(4);
            previous.setText("<<Previous");
            previous.setBorder(new javax.swing.border.BevelBorder(0));
            previous.setMaximumSize(new Dimension(85,27));
            previous.setMinimumSize(new Dimension(85,27));
            previous.setPreferredSize(new Dimension(85,27));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+previous,ex); 
          }

          try
          {
            next.setFont(new Font("SansSerif",0,12));
            next.setHorizontalTextPosition(4);
            next.setText("Next>>");
            next.setBorder(new javax.swing.border.BevelBorder(0));
            next.setMaximumSize(new Dimension(85,27));
            next.setMinimumSize(new Dimension(85,27));
            next.setPreferredSize(new Dimension(85,27));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+next,ex); 
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
        JPanel2= new javax.swing.JPanel();
        previous= new javax.swing.JButton();
        next= new javax.swing.JButton();
        JLabel1= new javax.swing.JLabel();
        CardPanel= new javax.swing.JPanel();

  
        //<End_initVariables>
	card=new CardLayout();
	previous.addActionListener(this);
	next.addActionListener(this);
	previous.setEnabled(false);
  } 
  public void setUpGUI(Container container)
  {
//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,10));
Top.add(JPanel2,BorderLayout.SOUTH);
JPanel2.setLayout(new GridBagLayout());
inset = new Insets(0,0,20,10);
setConstraints(1,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel2.add(previous,cons);
inset = new Insets(0,10,20,10);
setConstraints(2,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel2.add(next,cons);
inset = new Insets(0,0,0,0);
setConstraints(0,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel2.add(JLabel1,cons);
Top.add(CardPanel,BorderLayout.CENTER);
CardPanel.setLayout(new FlowLayout(1,5,5));

  
//<End_setUpGUI_Container>
CardPanel.setLayout(card);

if(customType.equals("TOPO CUSTOMVIEW"))
{
	
	cust1=new NDCustomizer1();
	cust1.disableAll();
	Vector v=new Vector();
	cust1.setProperties(customProps,v);
	addCard("prop1",cust1);
	cust2=new NDCustomizer2();	
	cust2.disableAll();
	cust2.setProperties(customProps,v);
	addCard("prop2",cust2);
}
else if(customType.equals("ALERT CUSTOMVIEW"))
{
	alertProps=new AlertCustomizer();
	alertProps.disableAll();
	alertProps.setProperties(customProps);
	addCard("alert",alertProps);
	
}
else if(customType.equals("EVENT CUSTOMVIEW"))
{
	eventProps=new EventCustomizer();
	eventProps.disableAll();
	eventProps.setProperties(customProps);
	addCard("event",eventProps);

}
else if(customType.equals("PERFORMANCE CUSTOMVIEW"))
{
	perfProps1=new PerfCustomizer1();
	Vector v=new Vector();
	perfProps1.disableAll();
	perfProps1.setProperties(customProps,v);
	addCard("perf1",perfProps1);
	
	perfProps2=new PerfCustomizer2();
	perfProps2.disableAll();	
	perfProps2.setProperties(customProps,v);
	addCard("perf2",perfProps2);
	perfProps3=new PerfCustomizer3();
	perfProps3.disableAll();	
	perfProps3.setProperties(customProps,v);
	addCard("perf3",perfProps3);
}		
		


parent=new ParentSelection();
parent.disableAll();
//for showing alone we don't need any combo for only modification alone we have to show the combo
parent.setProperties(customProps,"show");
addCard("parent",parent);
treeProps=new TreeProperties();
treeProps.disableAll();
treeProps.setProperties(customProps);
addCard("tree",treeProps);			
  } 
public void addCard(String s,JPanel panel)
{
	CardPanel.add(s,panel);	
	noOfScreens++;

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
	if(e.getSource().equals(next))
	{
		card.next(CardPanel);
		previous.setEnabled(true);
		count++;
		if(count==noOfScreens-1)
		{
			next.setEnabled(false);	
		}
	}
	if(e.getSource().equals(previous))
	{
		card.previous(CardPanel);
		next.setEnabled(true);
		count--;
		if(count==0)
		{
			previous.setEnabled(false);	
		}
		
	}
}

}




















