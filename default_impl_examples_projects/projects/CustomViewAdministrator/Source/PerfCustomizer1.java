
//$Id: PerfCustomizer1.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.tools.CustomView;




import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;



public class PerfCustomizer1 extends JPanel 
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	javax.swing.JPanel Top = null;
	javax.swing.JLabel lFilterView = null;
	javax.swing.JTextField tFilterView = null;
	javax.swing.JLabel lName = null;
	javax.swing.JTextField tName = null;
	javax.swing.JLabel lId = null;
	javax.swing.JTextField tId = null;
	javax.swing.JLabel ldnsName = null;
	javax.swing.JTextField tdnsName = null;
	javax.swing.JLabel lOid = null;
	javax.swing.JTextField tOid = null;
	javax.swing.JLabel lCommunity = null;
	javax.swing.JTextField tCommunity = null;
	javax.swing.JLabel lPeriod = null;
	javax.swing.JTextField tPeriod = null;
	javax.swing.JLabel lActive = null;
	javax.swing.JComboBox cActive = null;
	javax.swing.JLabel lIsMultiple = null;
	javax.swing.JComboBox cIsMultiple = null;
	javax.swing.JLabel lSnmpVersion = null;
	javax.swing.JTextField tSnmpVersion = null;
	javax.swing.JLabel lAgent = null;
	javax.swing.JTextField tAgent = null;
	javax.swing.JLabel lPort = null;
	javax.swing.JTextField tPort = null;
	javax.swing.JLabel lThreshold = null;
	javax.swing.JComboBox cThreshold = null;
	javax.swing.JLabel lCounterValue = null;
	javax.swing.JTextField tCounterValue = null;
	javax.swing.JLabel JLabel1 = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>



  public PerfCustomizer1()
  {
    //<Begin_PerfCustomizer1>
    this.init();
  
    //<End_PerfCustomizer1>
  }

  public PerfCustomizer1(java.applet.Applet applet)
  {
    //<Begin_PerfCustomizer1_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_PerfCustomizer1_java.applet.Applet>
  }


  


 

  

    public void init()
  {
        //<Begin_init> 
        if (initialized == true) return; 
        setPreferredSize(new Dimension(getPreferredSize().width+522,getPreferredSize().height+445)); 
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
            lFilterView.setHorizontalAlignment(2);
            lFilterView.setFont(new Font("SansSerif",0,12));
            lFilterView.setForeground(new Color(-16777216));
            lFilterView.setHorizontalTextPosition(4);
            lFilterView.setText("Custom View Name");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+lFilterView,ex); 
          }

          try
          {
            tFilterView.setHorizontalAlignment(2);
            tFilterView.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tFilterView,ex); 
          }

          try
          {
            lName.setHorizontalAlignment(2);
            lName.setFont(new Font("SansSerif",0,12));
            lName.setForeground(new Color(-16777216));
            lName.setHorizontalTextPosition(4);
            lName.setText("Name");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+lName,ex); 
          }

          try
          {
            tName.setHorizontalAlignment(2);
            tName.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tName,ex); 
          }

          try
          {
            lId.setHorizontalAlignment(2);
            lId.setFont(new Font("SansSerif",0,12));
            lId.setForeground(new Color(-16777216));
            lId.setHorizontalTextPosition(4);
            lId.setText("ID");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+lId,ex); 
          }

          try
          {
            tId.setHorizontalAlignment(2);
            tId.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tId,ex); 
          }

          try
          {
            ldnsName.setHorizontalAlignment(2);
            ldnsName.setFont(new Font("SansSerif",0,12));
            ldnsName.setForeground(new Color(-16777216));
            ldnsName.setHorizontalTextPosition(4);
            ldnsName.setText("DNS Name");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+ldnsName,ex); 
          }

          try
          {
            tdnsName.setHorizontalAlignment(2);
            tdnsName.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tdnsName,ex); 
          }

          try
          {
            lOid.setHorizontalAlignment(2);
            lOid.setFont(new Font("SansSerif",0,12));
            lOid.setForeground(new Color(-16777216));
            lOid.setHorizontalTextPosition(4);
            lOid.setText("OID");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+lOid,ex); 
          }

          try
          {
            tOid.setHorizontalAlignment(2);
            tOid.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tOid,ex); 
          }

          try
          {
            lCommunity.setHorizontalAlignment(2);
            lCommunity.setFont(new Font("SansSerif",0,12));
            lCommunity.setForeground(new Color(-16777216));
            lCommunity.setHorizontalTextPosition(4);
            lCommunity.setText("Community");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+lCommunity,ex); 
          }

          try
          {
            tCommunity.setHorizontalAlignment(2);
            tCommunity.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tCommunity,ex); 
          }

          try
          {
            lPeriod.setHorizontalAlignment(2);
            lPeriod.setFont(new Font("SansSerif",0,12));
            lPeriod.setForeground(new Color(-16777216));
            lPeriod.setHorizontalTextPosition(4);
            lPeriod.setText("Period");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+lPeriod,ex); 
          }

          try
          {
            tPeriod.setHorizontalAlignment(2);
            tPeriod.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tPeriod,ex); 
          }

          try
          {
            lActive.setHorizontalAlignment(2);
            lActive.setFont(new Font("SansSerif",0,12));
            lActive.setForeground(new Color(-16777216));
            lActive.setHorizontalTextPosition(4);
            lActive.setText("Active");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+lActive,ex); 
          }

          try
          {
            cActive.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+cActive,ex); 
          }

          try
          {
            lIsMultiple.setHorizontalAlignment(2);
            lIsMultiple.setFont(new Font("SansSerif",0,12));
            lIsMultiple.setForeground(new Color(-16777216));
            lIsMultiple.setHorizontalTextPosition(4);
            lIsMultiple.setText("IsMutiplePolledData");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+lIsMultiple,ex); 
          }

          try
          {
            cIsMultiple.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+cIsMultiple,ex); 
          }

          try
          {
            lSnmpVersion.setHorizontalAlignment(2);
            lSnmpVersion.setFont(new Font("SansSerif",0,12));
            lSnmpVersion.setForeground(new Color(-16777216));
            lSnmpVersion.setHorizontalTextPosition(4);
            lSnmpVersion.setText("SnmpVersion");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+lSnmpVersion,ex); 
          }

          try
          {
            tSnmpVersion.setHorizontalAlignment(2);
            tSnmpVersion.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tSnmpVersion,ex); 
          }

          try
          {
            lAgent.setHorizontalAlignment(2);
            lAgent.setFont(new Font("SansSerif",0,12));
            lAgent.setForeground(new Color(-16777216));
            lAgent.setHorizontalTextPosition(4);
            lAgent.setText("Agent");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+lAgent,ex); 
          }

          try
          {
            tAgent.setHorizontalAlignment(2);
            tAgent.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tAgent,ex); 
          }

          try
          {
            lPort.setHorizontalAlignment(2);
            lPort.setFont(new Font("SansSerif",0,12));
            lPort.setForeground(new Color(-16777216));
            lPort.setHorizontalTextPosition(4);
            lPort.setText("Port");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+lPort,ex); 
          }

          try
          {
            tPort.setHorizontalAlignment(2);
            tPort.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tPort,ex); 
          }

          try
          {
            lThreshold.setHorizontalAlignment(2);
            lThreshold.setFont(new Font("SansSerif",0,12));
            lThreshold.setForeground(new Color(-16777216));
            lThreshold.setHorizontalTextPosition(4);
            lThreshold.setText("Threshold");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+lThreshold,ex); 
          }

          try
          {
            cThreshold.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+cThreshold,ex); 
          }

          try
          {
            lCounterValue.setHorizontalAlignment(2);
            lCounterValue.setFont(new Font("SansSerif",0,12));
            lCounterValue.setForeground(new Color(-16777216));
            lCounterValue.setHorizontalTextPosition(4);
            lCounterValue.setText("lastCounterValue");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+lCounterValue,ex); 
          }

          try
          {
            tCounterValue.setHorizontalAlignment(2);
            tCounterValue.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tCounterValue,ex); 
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
        lFilterView= new javax.swing.JLabel();
        tFilterView= new javax.swing.JTextField();
        lName= new javax.swing.JLabel();
        tName= new javax.swing.JTextField();
        lId= new javax.swing.JLabel();
        tId= new javax.swing.JTextField();
        ldnsName= new javax.swing.JLabel();
        tdnsName= new javax.swing.JTextField();
        lOid= new javax.swing.JLabel();
        tOid= new javax.swing.JTextField();
        lCommunity= new javax.swing.JLabel();
        tCommunity= new javax.swing.JTextField();
        lPeriod= new javax.swing.JLabel();
        tPeriod= new javax.swing.JTextField();
        lActive= new javax.swing.JLabel();
        cActive= new javax.swing.JComboBox();
        lIsMultiple= new javax.swing.JLabel();
        cIsMultiple= new javax.swing.JComboBox();
        lSnmpVersion= new javax.swing.JLabel();
        tSnmpVersion= new javax.swing.JTextField();
        lAgent= new javax.swing.JLabel();
        tAgent= new javax.swing.JTextField();
        lPort= new javax.swing.JLabel();
        tPort= new javax.swing.JTextField();
        lThreshold= new javax.swing.JLabel();
        cThreshold= new javax.swing.JComboBox();
        lCounterValue= new javax.swing.JLabel();
        tCounterValue= new javax.swing.JTextField();
        JLabel1= new javax.swing.JLabel();

  
        //<End_initVariables>
        addItems(cActive);
        addItems(cIsMultiple);
        addItems(cThreshold);	
        tId.addKeyListener(new KeyAdapter(){
	public void keyTyped(KeyEvent ke){
	if((ke.getKeyChar() >= '0' && ke.getKeyChar() <='9') || ke.getKeyChar()==ke.VK_BACK_SPACE) 
	{
		//DO Nothing	
	}
	else {
		ke.consume();
		Toolkit.getDefaultToolkit().beep();
		}
		}
	});

	tOid.addKeyListener(new KeyAdapter(){
	public void keyTyped(KeyEvent ke){
	if((ke.getKeyChar() >= '0' && ke.getKeyChar() <='9') || ke.getKeyChar()=='.' || ke.getKeyChar()==ke.VK_BACK_SPACE) {
		//DO Nothing	
	}
	else {
		ke.consume();
		Toolkit.getDefaultToolkit().beep();
		}
		}
	});

	tPeriod.addKeyListener(new KeyAdapter(){
	public void keyTyped(KeyEvent ke){
	if((ke.getKeyChar() >= '0' && ke.getKeyChar() <='9') || ke.getKeyChar()==ke.VK_BACK_SPACE) 
	{
		//DO Nothing	
	}
	else {
		ke.consume();
		Toolkit.getDefaultToolkit().beep();
		}
		}
	});

	tPort.addKeyListener(new KeyAdapter(){
	public void keyTyped(KeyEvent ke){
	if((ke.getKeyChar() >= '0' && ke.getKeyChar() <='9') || ke.getKeyChar()==ke.VK_BACK_SPACE) 
	{
		//DO Nothing	
	}
	else {
		ke.consume();
		Toolkit.getDefaultToolkit().beep();
		}
		}
	});

	tCounterValue.addKeyListener(new KeyAdapter(){
	public void keyTyped(KeyEvent ke){
	if((ke.getKeyChar() >= '0' && ke.getKeyChar() <='9') || ke.getKeyChar()==ke.VK_BACK_SPACE) 
	{
		//DO Nothing	
	}
	else {
		ke.consume();
		Toolkit.getDefaultToolkit().beep();
		}
		}
	});
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
Top.setLayout(new GridBagLayout());
inset = new Insets(15,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
Top.add(lFilterView,cons);
inset = new Insets(15,5,5,5);
setConstraints(1,0,1,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(tFilterView,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
Top.add(lName,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,1,1,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(tName,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
Top.add(lId,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,2,1,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(tId,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,3,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
Top.add(ldnsName,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,3,1,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(tdnsName,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,4,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
Top.add(lOid,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,4,1,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(tOid,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,5,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
Top.add(lCommunity,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,5,1,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(tCommunity,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,6,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
Top.add(lPeriod,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,6,1,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(tPeriod,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,7,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
Top.add(lActive,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,7,1,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(cActive,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,8,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
Top.add(lIsMultiple,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,8,1,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(cIsMultiple,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,9,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
Top.add(lSnmpVersion,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,9,1,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(tSnmpVersion,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,10,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
Top.add(lAgent,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,10,1,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(tAgent,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,11,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
Top.add(lPort,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,11,1,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(tPort,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,12,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
Top.add(lThreshold,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,12,1,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(cThreshold,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,13,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
Top.add(lCounterValue,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,13,1,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(tCounterValue,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,14,0,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(JLabel1,cons);

  
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
	tFilterView.setEditable(false);
	tName.setEditable(false);
	tId.setEditable(false);
	tdnsName.setEditable(false); 
	tCommunity.setEditable(false); 
	tPeriod.setEditable(false);
	cActive.setEnabled(false);
	cIsMultiple.setEnabled(false); 
	tSnmpVersion.setEditable(false);
	tAgent.setEditable(false); 
	tPort.setEditable(false);
	cThreshold.setEnabled(false);
	tCounterValue.setEditable(false);
	tOid.setEditable(false);
}
public String getViewName()
{
	return tFilterView.getText();
}
public void setProperties(Properties props,Vector vec)
{
	if(props==null)
	{
		return;
	}
	
	if(props.get("TREE-NAME")!=null)
	{
		tFilterView.setText(props.getProperty("TREE-NAME"));
	}	
	if(!props.containsKey("viewcriteria"))
	{
		return;	
	}
	Properties prop=(Properties)props.get("viewcriteria");
	
	if(prop.get("name")!=null)
	{
		tName.setText(prop.get("name").toString());
		vec.add("name");
	}
	if(prop.get("oid")!=null)
	{
		tOid.setText(prop.get("oid").toString());
		vec.add("oid");
	}

	if(prop.get("id")!=null)
	{
		tId.setText(prop.get("id").toString());
		vec.add("id");
	}
	if(prop.get("dnsName")!=null)
	{
		tdnsName.setText(prop.get("dnsName").toString()); 
		vec.add("dnsName");
	}
	if(prop.get("community")!=null)
	{
		tCommunity.setText(prop.get("community").toString()); 	
		vec.add("community");
	}
	if(prop.get("period")!=null)
	{
		tPeriod.setText(prop.get("period").toString());
		vec.add("period");
	}
	if(prop.get("active")!=null)
	{
		cActive.setSelectedItem(prop.get("active").toString());
		vec.add("active");
	}
	if(prop.get("isMultiplePolledData")!=null)
	{
		cIsMultiple.setSelectedItem(prop.get("isMultiplePolledData").toString()); 
		vec.add("isMultiplePolledData");
	}
	if(prop.get("snmpVersion")!=null)
	{
		tSnmpVersion.setText(prop.get("snmpVersion").toString());
		vec.add("snmpVersion");
	}
	if(prop.get("agent")!=null)
	{
		tAgent.setText(prop.get("agent").toString()); 
		vec.add("agent");
	}
	if(prop.get("port")!=null)
	{
		tPort.setText(prop.get("port").toString());
		vec.add("port");
	}
	if(prop.get("threshold")!=null)
	{
		cThreshold.setSelectedItem(prop.get("threshold"));
		vec.add("threshold");
	}
	if(prop.get("lastCounterValue")!=null)
	{
		tCounterValue.setText(prop.get("lastCounterValue").toString());           		
		vec.add("lastCounterValue");
	}	
}
public Properties getProperties()
{
	Properties props = new Properties();
	if(!tName.getText().trim().equals(""))
	{	
		props.put("name",tName.getText());
	}
	if(!tId.getText().trim().equals(""))
	{
		props.put("id",tId.getText());
	}
	if(!tdnsName.getText().trim().equals(""))
	{
		props.put("dnsName",tdnsName.getText()); 
	}
	if(!tCommunity.getText().trim().equals(""))
	{
		props.put("community",tCommunity.getText()); 
	}
	if(!tPeriod.getText().trim().equals(""))
	{
		props.put("period",tPeriod.getText());
	}
	if(!cActive.getSelectedItem().equals("all"))
	{
		props.put("active",cActive.getSelectedItem());
	}
	if(!cIsMultiple.getSelectedItem().equals("all"))
	{
		props.put("isMultiplePolledData",cIsMultiple.getSelectedItem()); 
	}
	if(!tSnmpVersion.getText().trim().equals(""))
	{
		props.put("snmpVersion",tSnmpVersion.getText());
	}
	if(!tAgent.getText().trim().equals(""))
	{
		props.put("agent",tAgent.getText()); 
	}
	if(!tPort.getText().trim().equals(""))
	{
		props.put("port",tPort.getText());
	}
	if(!cThreshold.getSelectedItem().equals("all"))
	{
		props.put("threshold",cThreshold.getSelectedItem());
	}
	if(!tCounterValue.getText().trim().equals(""))
	{
		props.put("lastCounterValue",tCounterValue.getText());  
	}
	if(!tOid.getText().trim().equals(""))
	{
		props.put("oid",tOid.getText());  
	}

	return props; 	
}		

}

















