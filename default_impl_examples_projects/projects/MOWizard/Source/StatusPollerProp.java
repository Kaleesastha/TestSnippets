//SId: $
package com.adventnet.nms.tools.mowizard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import com.adventnet.apiutils.BuilderResourceBundle;
import com.adventnet.nms.tools.utils.ToolsUtil;

public class StatusPollerProp extends JPanel 
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	javax.swing.JPanel  Top = null;
	javax.swing.JPanel  panelStatPoll = null;
	javax.swing.JLabel  labelClsNameVal = null;
	javax.swing.JLabel  labelMOName = null;
	javax.swing.JLabel  labelClsName = null;
	javax.swing.JLabel  labelMONameVal = null;
	javax.swing.JLabel  labelStatusPollDesc = null;
	javax.swing.JPanel  panelDeviceDetail = null;
	javax.swing.JLabel  labelOID = null;
	javax.swing.JLabel  labelOIDVal = null;
	javax.swing.JLabel  labelType = null;
	javax.swing.JLabel  labelTypeVal = null;
	javax.swing.JLabel  labelPollInt = null;
	javax.swing.JLabel  labelPollIntVal = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>
	private BuilderResourceBundle resourceBundle=ToolsUtil.getBundle();
	public StatusPollerProp()
  {
		//<Begin_StatusPollerProp>
    this.init();
  
    //<End_StatusPollerProp>
	}

	public StatusPollerProp(java.applet.Applet applet)
  {
		//<Begin_StatusPollerProp_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_StatusPollerProp_java.applet.Applet>
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
        panelStatPoll= new javax.swing.JPanel();
        labelClsNameVal= new javax.swing.JLabel();
        labelMOName= new javax.swing.JLabel();
        labelClsName= new javax.swing.JLabel();
        labelMONameVal= new javax.swing.JLabel();
        labelStatusPollDesc= new javax.swing.JLabel();
        panelDeviceDetail= new javax.swing.JPanel();
        labelOID= new javax.swing.JLabel();
        labelOIDVal= new javax.swing.JLabel();
        labelType= new javax.swing.JLabel();
        labelTypeVal= new javax.swing.JLabel();
        labelPollInt= new javax.swing.JLabel();
        labelPollIntVal= new javax.swing.JLabel();

  //<End_initVariables>
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
	inset = new Insets(10,10,0,10);
	setConstraints(0,0,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
	Top.add(panelStatPoll,cons);
	panelStatPoll.setLayout(new GridBagLayout());
	inset = new Insets(10,10,0,10);
	setConstraints(1,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
	panelStatPoll.add(labelClsNameVal,cons);
	inset = new Insets(0,10,10,10);
	setConstraints(0,1,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
	panelStatPoll.add(labelMOName,cons);
	inset = new Insets(10,10,0,10);
	setConstraints(0,0,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
	panelStatPoll.add(labelClsName,cons);
	inset = new Insets(0,10,10,10);
	setConstraints(1,1,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
	panelStatPoll.add(labelMONameVal,cons);
	inset = new Insets(0,10,10,10);
	setConstraints(0,2,0,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
	Top.add(labelStatusPollDesc,cons);
	inset = new Insets(0,10,0,10);
	setConstraints(0,1,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
	Top.add(panelDeviceDetail,cons);
	panelDeviceDetail.setLayout(new GridBagLayout());
	inset = new Insets(10,10,0,10);
	setConstraints(0,0,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
	panelDeviceDetail.add(labelOID,cons);
	inset = new Insets(10,10,0,10);
	setConstraints(1,0,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
	panelDeviceDetail.add(labelOIDVal,cons);
	inset = new Insets(0,10,0,10);
	setConstraints(0,1,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
	panelDeviceDetail.add(labelType,cons);
	inset = new Insets(0,10,0,10);
	setConstraints(1,1,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
	panelDeviceDetail.add(labelTypeVal,cons);
	inset = new Insets(0,10,10,10);
	setConstraints(0,2,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
	panelDeviceDetail.add(labelPollInt,cons);
	inset = new Insets(0,10,10,10);
	setConstraints(1,2,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
	panelDeviceDetail.add(labelPollIntVal,cons);
	
  //<End_setUpGUI_Container>
	} 
	public void setUpProperties()throws Exception
  { 

		//<Begin_setUpProperties>

          try
          {
            panelStatPoll.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.LineBorder(new Color(-16777216),1),resourceBundle.getString("Status Poller Details"),1,2,new Font("Dialog",0,12),new Color(-16764109)));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+panelStatPoll,ex); 
          }

          try
          {
            labelMOName.setFont(new Font("Dialog",0,12));
            labelMOName.setForeground(new Color(-16764109));
            labelMOName.setText(resourceBundle.getString("ManagedObject Name"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+labelMOName,ex); 
          }

          try
          {
            labelClsName.setText(resourceBundle.getString("Class Name"));
            labelClsName.setFont(new Font("Dialog",0,12));
            labelClsName.setForeground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+labelClsName,ex); 
          }

          try
          {
            labelStatusPollDesc.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.LineBorder(new Color(-16777216),1),resourceBundle.getString("Description"),1,2,new Font("Dialog",0,12),new Color(-16764109)));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+labelStatusPollDesc,ex); 
          }

          try
          {
            panelDeviceDetail.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.LineBorder(new Color(-16777216),1),resourceBundle.getString("Device Details Associated to Status Poller"),1,2,new Font("Dialog",0,12),new Color(-16764109)));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+panelDeviceDetail,ex); 
          }

          try
          {
            labelOID.setText(resourceBundle.getString("OID "));
            labelOID.setFont(new Font("Dialog",0,12));
            labelOID.setForeground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+labelOID,ex); 
          }

          try
          {
            labelType.setText(resourceBundle.getString("Type"));
            labelType.setFont(new Font("Dialog",0,12));
            labelType.setForeground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+labelType,ex); 
          }

          try
          {
            labelPollInt.setText(resourceBundle.getString("Poll Interval"));
            labelPollInt.setFont(new Font("Dialog",0,12));
            labelPollInt.setForeground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+labelPollInt,ex); 
          }

  //<End_setUpProperties>
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
        setPreferredSize(new Dimension(getPreferredSize().width+696,getPreferredSize().height+489)); 
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

	public void setStatusPollerProperties(Document doc) {
		if(doc==null) {
			return;
		}
		// Write code for processing Status Poller Information.
		Element tempNode=(Element)doc.getElementsByTagName("USER_TESTER").item(0);
		Element deviceNode=(Element)doc.getElementsByTagName("DEVICE_PARAMS").item(0);
		if(deviceNode!=null) {
			labelOIDVal.setText(":  "+deviceNode.getAttribute("eoid"));
			labelTypeVal.setText(":  "+deviceNode.getAttribute("devType"));
			labelPollIntVal.setText(":  "+deviceNode.getAttribute("pollInt"));
		}
		else {
			labelOIDVal.setText(resourceBundle.getString(":  Not Defined"));
			labelTypeVal.setText(resourceBundle.getString(":  Not Defined"));
			labelPollIntVal.setText(resourceBundle.getString(":  Not Defined"));
		}
		if(tempNode.getAttribute("fileName")!=null && tempNode.getAttribute("fileName").trim().length()!=0) {
			labelClsNameVal.setText(":  "+tempNode.getAttribute("className"));
			tempNode=(Element)doc.getElementsByTagName("CLASS").item(0);
			labelMONameVal.setText(tempNode.getAttribute("package")+"."+tempNode.getAttribute("name"));

			if(deviceNode==null) {
				labelOIDVal.setText(resourceBundle.getString(":  Not Defined"));
				labelTypeVal.setText(resourceBundle.getString(":  Not Defined"));
				labelPollIntVal.setText(resourceBundle.getString(":  Not Defined"));
			}
			else {
							}
			
		}
		else {
			labelClsNameVal.setText(resourceBundle.getString(":  Not Defined"));
		}
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
}






