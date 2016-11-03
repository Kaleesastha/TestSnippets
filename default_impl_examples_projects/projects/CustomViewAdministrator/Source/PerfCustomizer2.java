//$Id: PerfCustomizer2.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.tools.CustomView;




import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;



public class PerfCustomizer2 extends JPanel 
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	javax.swing.JPanel Top = null;
	javax.swing.JTextField tStatsTable = null;
	javax.swing.JLabel lStatsTable = null;
	javax.swing.JLabel lProtocol = null;
	javax.swing.JTextField tProtocol = null;
	javax.swing.JComboBox cSave = null;
	javax.swing.JLabel lSave = null;
	javax.swing.JTextField tPolicy = null;
	javax.swing.JTextField tCurSaveCount = null;
	javax.swing.JLabel lCurSaveCount = null;
	javax.swing.JTextField tFailCount = null;
	javax.swing.JLabel lFailCount = null;
	javax.swing.JTextField tThresholdList = null;
	javax.swing.JLabel lThresholdList = null;
	javax.swing.JTextField tParentObj = null;
	javax.swing.JLabel lParentObj = null;
	javax.swing.JLabel lLogFile = null;
	javax.swing.JComboBox cLogDir = null;
	javax.swing.JTextField tLogFile = null;
	javax.swing.JLabel lLogDir = null;
	javax.swing.JTextField tSaveAbsolute = null;
	javax.swing.JLabel lSaveAbsolute = null;
	javax.swing.JLabel lPolicy = null;
	javax.swing.JTextField tNumType = null;
	javax.swing.JLabel lNumType = null;
	javax.swing.JTextField tPrevSeverity = null;
	javax.swing.JLabel lPrevSeverity = null;
	javax.swing.JLabel lGroup = null;
	javax.swing.JTextField tGroup = null;
	javax.swing.JLabel lPollerName = null;
	javax.swing.JTextField tPollerName = null;
	javax.swing.JLabel JLabel1 = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>



  public PerfCustomizer2()
  {
    //<Begin_PerfCustomizer2>
    this.init();
  
    //<End_PerfCustomizer2>
  }

  public PerfCustomizer2(java.applet.Applet applet)
  {
    //<Begin_PerfCustomizer2_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_PerfCustomizer2_java.applet.Applet>
  }


  


 

  

    public void init()
  {
        //<Begin_init> 
        if (initialized == true) return; 
        setPreferredSize(new Dimension(getPreferredSize().width+468,getPreferredSize().height+472)); 
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
            tStatsTable.setHorizontalAlignment(2);
            tStatsTable.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tStatsTable,ex); 
          }

          try
          {
            lStatsTable.setHorizontalAlignment(2);
            lStatsTable.setFont(new Font("SansSerif",0,12));
            lStatsTable.setForeground(new Color(-16777216));
            lStatsTable.setHorizontalTextPosition(4);
            lStatsTable.setText("StatsDataTableName");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+lStatsTable,ex); 
          }

          try
          {
            lProtocol.setHorizontalAlignment(2);
            lProtocol.setFont(new Font("SansSerif",0,12));
            lProtocol.setForeground(new Color(-16777216));
            lProtocol.setHorizontalTextPosition(4);
            lProtocol.setText("Protocol");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+lProtocol,ex); 
          }

          try
          {
            tProtocol.setHorizontalAlignment(2);
            tProtocol.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tProtocol,ex); 
          }

          try
          {
            cSave.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+cSave,ex); 
          }

          try
          {
            lSave.setHorizontalAlignment(2);
            lSave.setFont(new Font("SansSerif",0,12));
            lSave.setForeground(new Color(-16777216));
            lSave.setHorizontalTextPosition(4);
            lSave.setText("Save");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+lSave,ex); 
          }

          try
          {
            tPolicy.setHorizontalAlignment(2);
            tPolicy.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tPolicy,ex); 
          }

          try
          {
            tCurSaveCount.setHorizontalAlignment(2);
            tCurSaveCount.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tCurSaveCount,ex); 
          }

          try
          {
            lCurSaveCount.setHorizontalAlignment(2);
            lCurSaveCount.setFont(new Font("SansSerif",0,12));
            lCurSaveCount.setForeground(new Color(-16777216));
            lCurSaveCount.setHorizontalTextPosition(4);
            lCurSaveCount.setText("Current Save Count");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+lCurSaveCount,ex); 
          }

          try
          {
            tFailCount.setHorizontalAlignment(2);
            tFailCount.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tFailCount,ex); 
          }

          try
          {
            lFailCount.setHorizontalAlignment(2);
            lFailCount.setFont(new Font("SansSerif",0,12));
            lFailCount.setForeground(new Color(-16777216));
            lFailCount.setHorizontalTextPosition(4);
            lFailCount.setText("failureCount");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+lFailCount,ex); 
          }

          try
          {
            tThresholdList.setHorizontalAlignment(2);
            tThresholdList.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tThresholdList,ex); 
          }

          try
          {
            lThresholdList.setHorizontalAlignment(2);
            lThresholdList.setFont(new Font("SansSerif",0,12));
            lThresholdList.setForeground(new Color(-16777216));
            lThresholdList.setHorizontalTextPosition(4);
            lThresholdList.setText("ThresholdList");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+lThresholdList,ex); 
          }

          try
          {
            tParentObj.setHorizontalAlignment(2);
            tParentObj.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tParentObj,ex); 
          }

          try
          {
            lParentObj.setHorizontalAlignment(2);
            lParentObj.setFont(new Font("SansSerif",0,12));
            lParentObj.setForeground(new Color(-16777216));
            lParentObj.setHorizontalTextPosition(4);
            lParentObj.setText("ParentObj");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+lParentObj,ex); 
          }

          try
          {
            lLogFile.setHorizontalAlignment(2);
            lLogFile.setFont(new Font("SansSerif",0,12));
            lLogFile.setForeground(new Color(-16777216));
            lLogFile.setHorizontalTextPosition(4);
            lLogFile.setText("logFile");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+lLogFile,ex); 
          }

          try
          {
            cLogDir.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+cLogDir,ex); 
          }

          try
          {
            tLogFile.setHorizontalAlignment(2);
            tLogFile.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tLogFile,ex); 
          }

          try
          {
            lLogDir.setHorizontalAlignment(2);
            lLogDir.setFont(new Font("SansSerif",0,12));
            lLogDir.setForeground(new Color(-16777216));
            lLogDir.setHorizontalTextPosition(4);
            lLogDir.setText("logDirectly");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+lLogDir,ex); 
          }

          try
          {
            tSaveAbsolute.setHorizontalAlignment(2);
            tSaveAbsolute.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tSaveAbsolute,ex); 
          }

          try
          {
            lSaveAbsolute.setHorizontalAlignment(2);
            lSaveAbsolute.setFont(new Font("SansSerif",0,12));
            lSaveAbsolute.setForeground(new Color(-16777216));
            lSaveAbsolute.setHorizontalTextPosition(4);
            lSaveAbsolute.setText("Saveabsolute");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+lSaveAbsolute,ex); 
          }

          try
          {
            lPolicy.setHorizontalAlignment(2);
            lPolicy.setFont(new Font("SansSerif",0,12));
            lPolicy.setForeground(new Color(-16777216));
            lPolicy.setHorizontalTextPosition(4);
            lPolicy.setText("Policy Name");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+lPolicy,ex); 
          }

          try
          {
            tNumType.setHorizontalAlignment(2);
            tNumType.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tNumType,ex); 
          }

          try
          {
            lNumType.setHorizontalAlignment(2);
            lNumType.setForeground(new Color(-16777216));
            lNumType.setHorizontalTextPosition(4);
            lNumType.setText("Numeric Type");
            lNumType.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+lNumType,ex); 
          }

          try
          {
            tPrevSeverity.setHorizontalAlignment(2);
            tPrevSeverity.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tPrevSeverity,ex); 
          }

          try
          {
            lPrevSeverity.setHorizontalAlignment(2);
            lPrevSeverity.setFont(new Font("SansSerif",0,12));
            lPrevSeverity.setForeground(new Color(-16777216));
            lPrevSeverity.setHorizontalTextPosition(4);
            lPrevSeverity.setText("Previous Severity");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+lPrevSeverity,ex); 
          }

          try
          {
            lGroup.setHorizontalAlignment(2);
            lGroup.setForeground(new Color(-16777216));
            lGroup.setHorizontalTextPosition(4);
            lGroup.setFont(new Font("SansSerif",0,12));
            lGroup.setText("Group Name");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+lGroup,ex); 
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
            lPollerName.setHorizontalAlignment(2);
            lPollerName.setFont(new Font("SansSerif",0,12));
            lPollerName.setForeground(new Color(-16777216));
            lPollerName.setHorizontalTextPosition(4);
            lPollerName.setText("PollerName");
            lPollerName.setMaximumSize(new Dimension(70,13));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+lPollerName,ex); 
          }

          try
          {
            tPollerName.setHorizontalAlignment(2);
            tPollerName.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+tPollerName,ex); 
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
        tStatsTable= new javax.swing.JTextField();
        lStatsTable= new javax.swing.JLabel();
        lProtocol= new javax.swing.JLabel();
        tProtocol= new javax.swing.JTextField();
        cSave= new javax.swing.JComboBox();
        lSave= new javax.swing.JLabel();
        tPolicy= new javax.swing.JTextField();
        tCurSaveCount= new javax.swing.JTextField();
        lCurSaveCount= new javax.swing.JLabel();
        tFailCount= new javax.swing.JTextField();
        lFailCount= new javax.swing.JLabel();
        tThresholdList= new javax.swing.JTextField();
        lThresholdList= new javax.swing.JLabel();
        tParentObj= new javax.swing.JTextField();
        lParentObj= new javax.swing.JLabel();
        lLogFile= new javax.swing.JLabel();
        cLogDir= new javax.swing.JComboBox();
        tLogFile= new javax.swing.JTextField();
        lLogDir= new javax.swing.JLabel();
        tSaveAbsolute= new javax.swing.JTextField();
        lSaveAbsolute= new javax.swing.JLabel();
        lPolicy= new javax.swing.JLabel();
        tNumType= new javax.swing.JTextField();
        lNumType= new javax.swing.JLabel();
        tPrevSeverity= new javax.swing.JTextField();
        lPrevSeverity= new javax.swing.JLabel();
        lGroup= new javax.swing.JLabel();
        tGroup= new javax.swing.JTextField();
        lPollerName= new javax.swing.JLabel();
        tPollerName= new javax.swing.JTextField();
        JLabel1= new javax.swing.JLabel();

  
        //<End_initVariables>
	addItems(cSave);
	addItems(cLogDir);
	tNumType.addKeyListener(new KeyAdapter(){
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
inset = new Insets(5,5,5,5);
setConstraints(1,14,1,1,0.1,0.0,cons.WEST,cons.BOTH,inset,0,0);
Top.add(tStatsTable,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,14,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
Top.add(lStatsTable,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,12,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
Top.add(lProtocol,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,12,1,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(tProtocol,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,4,1,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(cSave,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,4,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
Top.add(lSave,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,2,1,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(tPolicy,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,10,1,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(tCurSaveCount,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,10,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
Top.add(lCurSaveCount,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,11,1,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(tFailCount,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,11,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
Top.add(lFailCount,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,9,1,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(tThresholdList,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,9,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
Top.add(lThresholdList,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,8,1,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(tParentObj,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,8,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
Top.add(lParentObj,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,7,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
Top.add(lLogFile,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,6,1,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(cLogDir,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,7,1,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(tLogFile,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,6,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
Top.add(lLogDir,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,5,1,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(tSaveAbsolute,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,5,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
Top.add(lSaveAbsolute,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
Top.add(lPolicy,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,1,1,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(tNumType,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
Top.add(lNumType,cons);
inset = new Insets(15,5,5,5);
setConstraints(1,0,1,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(tPrevSeverity,cons);
inset = new Insets(15,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
Top.add(lPrevSeverity,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,3,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
Top.add(lGroup,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,3,1,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(tGroup,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,13,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
Top.add(lPollerName,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,13,1,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(tPollerName,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,15,0,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
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
	tStatsTable.setEditable(false);
	tProtocol.setEditable(false);
	cSave.setEnabled(false);
	tPolicy.setEditable(false); 
	tCurSaveCount.setEditable(false); 
	tFailCount.setEditable(false);
	tThresholdList.setEditable(false);
	tParentObj.setEditable(false); 
	tLogFile.setEditable(false);
	cLogDir.setEnabled(false); 
	tSaveAbsolute.setEditable(false);
	tNumType.setEditable(false);
	tPrevSeverity.setEditable(false);
	tGroup.setEditable(false);
	tPollerName.setEditable(false);
}

public void setProperties(Properties props,Vector vec)
{
	
	if(props==null)
	{
		return;
	}
	if(!props.containsKey("viewcriteria"))
	{
		return;	
	}
	Properties prop=(Properties)props.get("viewcriteria");
	if(prop.get("statsDataTableName")!=null)
	{
		tStatsTable.setText(prop.get("statsDataTableName").toString());
		vec.add("statsDataTableName");
	}
	if(prop.get("protocol")!=null)
	{
		tProtocol.setText(prop.get("protocol").toString());
		vec.add("protocol");
	}
	if(prop.get("save")!=null)
	{
		cSave.setSelectedItem(prop.get("save"));
		vec.add("save");
	}
	if(prop.get("policyName")!=null)
	{
		tPolicy.setText(prop.get("policyName").toString()); 
		vec.add("policyName");
	}
	if(prop.get("currentSaveCount")!=null)
	{
		tCurSaveCount.setText(prop.get("currentSaveCount").toString()); 
		vec.add("currentSaveCount");
	}
	if(prop.get("failureCount")!=null)
	{
		tFailCount.setText(prop.get("failureCount").toString());
		vec.add("failureCount");
	}
	if(prop.get("thresholdList")!=null)
	{
		tThresholdList.setText(prop.get("thresholdList").toString());
		vec.add("thresholdList");
	}
	if(prop.get("parentObj")!=null)
	{
		tParentObj.setText(prop.get("parentObj").toString()); 
		vec.add("parentObj");
	}
	if(prop.get("logFile")!=null)
	{
		tLogFile.setText(prop.get("logFile").toString());
		vec.add("logFile");
	}
	if(prop.get("logDir")!=null)
	{
		cLogDir.setSelectedItem(prop.get("logDir").toString()); 
		vec.add("logDir");
	}
	if(prop.get("saveAbsolute")!=null)
	{
		tSaveAbsolute.setText(prop.get("saveAbsolute").toString());
		vec.add("saveAbsolute");
	}
	if(prop.get("numericType")!=null)
	{
		tNumType.setText(prop.get("numericType").toString());
		vec.add("numericType");
	}
	if(prop.get("previousSeverity")!=null)
	{
		tPrevSeverity.setText(prop.get("previousSeverity").toString());           		
		vec.add("previousSeverity");
	}
	if(prop.get("groupName")!=null)
	{
		tGroup.setText(prop.get("groupName").toString());           		
		vec.add("groupName");
	}
	if(prop.get("pollerName")!=null)
	{
		tPollerName.setText(prop.get("pollerName").toString());           		
		vec.add("pollerName");
	}

}
public Properties getProperties()
{
	Properties props = new Properties();
	if(!tStatsTable.getText().trim().equals(""))
	{
		props.put("statsDataTableName",tStatsTable.getText());
	}
	if(!tProtocol.getText().trim().equals(""))
	{
		props.put("protocol",tProtocol.getText());
	}
	if(!cSave.getSelectedItem().equals("all"))
	{
		props.put("save",cSave.getSelectedItem());
	}
	if(!tPolicy.getText().trim().equals(""))
	{
		props.put("policyName",tPolicy.getText()); 
	}
	if(!tCurSaveCount.getText().trim().equals(""))
	{	
		props.put("currentSaveCount",tCurSaveCount.getText()); 
	}
	if(!tFailCount.getText().trim().equals(""))
	{
		props.put("failureCount",tFailCount.getText());
	}
	if(!tThresholdList.getText().trim().equals(""))
	{
		props.put("thresholdList",tThresholdList.getText());
	}
	if(!tParentObj.getText().trim().equals(""))
	{
		props.put("parentObj",tParentObj.getText()); 
	}
	if(!tLogFile.getText().trim().equals(""))
	{
		props.put("logFile",tLogFile.getText());
	}
	if(!cLogDir.getSelectedItem().equals("all"))
	{
		props.put("logDir",cLogDir.getSelectedItem()); 
	}
	if(!tSaveAbsolute.getText().trim().equals(""))
	{
		props.put("saveAbsolute",tSaveAbsolute.getText());
	}
	if(!tNumType.getText().trim().equals(""))
	{
		props.put("numericType",tNumType.getText());
	}
	if(!tPrevSeverity.getText().trim().equals(""))
	{
		props.put("previousSeverity",tPrevSeverity.getText());  
	}
	if(!tGroup.getText().trim().equals(""))
	{
		props.put("groupName",tGroup.getText());  
	}
	if(!tPollerName.getText().trim().equals(""))
	{
		props.put("pollerName",tPollerName.getText());  
	}

	return props; 	
}		
}
















