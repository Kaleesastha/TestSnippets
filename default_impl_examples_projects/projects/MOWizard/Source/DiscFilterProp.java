//$Id: DiscFilterProp.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $
package com.adventnet.nms.tools.mowizard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import com.adventnet.apiutils.BuilderResourceBundle;
import com.adventnet.nms.tools.utils.ToolsUtil;


public class DiscFilterProp extends JPanel 
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	javax.swing.JPanel  Top = null;
	javax.swing.JLabel  labelDesc = null;
	javax.swing.JPanel  panelDiscFilter = null;
	javax.swing.JLabel  labelClsName = null;
	javax.swing.JLabel  labelClsNameVal = null;
	javax.swing.JLabel  labelMOName = null;
	javax.swing.JLabel  labelMONameVal = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>
	private BuilderResourceBundle resourceBundle=ToolsUtil.getBundle();
	public DiscFilterProp()
  {
		//<Begin_DiscFilterProp>
    this.init();
  
    //<End_DiscFilterProp>
	}

	public DiscFilterProp(java.applet.Applet applet)
  {
		//<Begin_DiscFilterProp_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_DiscFilterProp_java.applet.Applet>
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
        labelDesc= new javax.swing.JLabel();
        panelDiscFilter= new javax.swing.JPanel();
        labelClsName= new javax.swing.JLabel();
        labelClsNameVal= new javax.swing.JLabel();
        labelMOName= new javax.swing.JLabel();
        labelMONameVal= new javax.swing.JLabel();

  //<End_initVariables>
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
	inset = new Insets(0,10,10,10);
	setConstraints(0,1,0,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
	Top.add(labelDesc,cons);
	inset = new Insets(10,10,0,10);
	setConstraints(0,0,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
	Top.add(panelDiscFilter,cons);
	panelDiscFilter.setLayout(new GridBagLayout());
	inset = new Insets(10,10,0,10);
	setConstraints(0,0,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
	panelDiscFilter.add(labelClsName,cons);
	inset = new Insets(10,10,0,10);
	setConstraints(1,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
	panelDiscFilter.add(labelClsNameVal,cons);
	inset = new Insets(0,10,10,10);
	setConstraints(0,1,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
	panelDiscFilter.add(labelMOName,cons);
	inset = new Insets(0,10,10,10);
	setConstraints(1,1,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
	panelDiscFilter.add(labelMONameVal,cons);
	
  //<End_setUpGUI_Container>
	}

	public void setUpProperties()throws Exception
  { 

		//<Begin_setUpProperties>

          try
          {
            labelDesc.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.LineBorder(new Color(-16777216),1),resourceBundle.getString("Description"),0,0,new Font("Dialog",0,12),new Color(-16764109)));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+labelDesc,ex); 
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
            labelMOName.setText(resourceBundle.getString("ManagedObject Name"));
            labelMOName.setFont(new Font("Dialog",0,12));
            labelMOName.setForeground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+labelMOName,ex); 
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
        setPreferredSize(new Dimension(getPreferredSize().width+685,getPreferredSize().height+480)); 
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
	
	public void setDiscoveryFilterProperties(Document doc) {
		if(doc==null) {
			return;
		}
		Element tempNode=(Element)doc.getElementsByTagName("DISC_FILTER").item(0);
		if(tempNode!=null && tempNode.getAttribute("fileName")!=null && tempNode.getAttribute("fileName").trim().length()!=0) {
			labelClsNameVal.setText(tempNode.getAttribute("className"));
			tempNode=(Element)doc.getElementsByTagName("CLASS").item(0);
			labelMONameVal.setText(tempNode.getAttribute("package")+"."+tempNode.getAttribute("name"));
		}
		else {
			labelClsNameVal.setText(resourceBundle.getString(":  Not Defined"));
			labelMONameVal.setText(resourceBundle.getString(":  Not Defined"));
		}
	}

}
