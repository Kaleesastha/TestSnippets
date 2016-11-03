//$Id: ProjProperty.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $
package com.adventnet.nms.tools.mowizard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import org.xml.sax.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import com.adventnet.apiutils.BuilderResourceBundle;
import com.adventnet.nms.tools.utils.ToolsUtil;



public class ProjProperty extends JPanel 
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	javax.swing.JPanel Top = null;
	javax.swing.JLabel labelDesc = null;
	javax.swing.JLabel labelClassesVal = null;
	javax.swing.JLabel labelClasses = null;
	javax.swing.JLabel labelPackageVal = null;
	javax.swing.JLabel labelPackage = null;
	javax.swing.JLabel labelProjNameVal = null;
	javax.swing.JLabel labelProjName = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>
	String fs=File.separator;
	private BuilderResourceBundle resourceBundle=ToolsUtil.getBundle();

	public ProjProperty()
  {
		//<Begin_ProjProperty>
    this.init();
  
    //<End_ProjProperty>
	}

	public ProjProperty(java.applet.Applet applet)
  {
		//<Begin_ProjProperty_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_ProjProperty_java.applet.Applet>
	}

	public void init()
  {
		//<Begin_init> 
        if (initialized == true) return; 
        setPreferredSize(new Dimension(getPreferredSize().width+726,getPreferredSize().height+500)); 
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
            if (input.equals("HOST")) value = "localhost"; 
            if (input.equals("PORT")) value = "161"; 
            }
        return value;

  
           //<End_getParameter_String>
	} 
	public void setUpProperties()
  {
		//<Begin_setUpProperties> 

          try
          {
            labelDesc.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.LineBorder(new Color(-16777216),1),resourceBundle.getString("Description"),1,2,new Font("Dialog",0,12),new Color(-16764109)));
            labelDesc.setVerticalAlignment(1);
            labelDesc.setVerticalTextPosition(1);
            labelDesc.setHorizontalTextPosition(2);
            labelDesc.setHorizontalAlignment(2);
            labelDesc.setFont(new Font("Dialog",0,12));
            labelDesc.setForeground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+labelDesc,ex); 
          }

          try
          {
            labelClassesVal.setFont(new Font("Dialog",0,12));
            labelClassesVal.setForeground(new Color(-16764109));
            labelClassesVal.setHorizontalAlignment(2);
            labelClassesVal.setVerticalTextPosition(1);
            labelClassesVal.setHorizontalTextPosition(2);
            labelClassesVal.setVerticalAlignment(1);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+labelClassesVal,ex); 
          }

          try
          {
            labelClasses.setText(resourceBundle.getString("Classes Defined"));
            labelClasses.setFont(new Font("Dialog",0,12));
            labelClasses.setForeground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+labelClasses,ex); 
          }

          try
          {
            labelPackageVal.setFont(new Font("Dialog",0,12));
            labelPackageVal.setForeground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+labelPackageVal,ex); 
          }

          try
          {
            labelPackage.setFont(new Font("Dialog",0,12));
            labelPackage.setForeground(new Color(-16764109));
            labelPackage.setText(resourceBundle.getString("Package Name"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+labelPackage,ex); 
          }

          try
          {
            labelProjNameVal.setFont(new Font("Dialog",0,12));
            labelProjNameVal.setForeground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+labelProjNameVal,ex); 
          }

          try
          {
            labelProjName.setText(resourceBundle.getString("Project Name"));
            labelProjName.setFont(new Font("Dialog",0,12));
            labelProjName.setForeground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+labelProjName,ex); 
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
        labelDesc= new javax.swing.JLabel();
        labelClassesVal= new javax.swing.JLabel();
        labelClasses= new javax.swing.JLabel();
        labelPackageVal= new javax.swing.JLabel();
        labelPackage= new javax.swing.JLabel();
        labelProjNameVal= new javax.swing.JLabel();
        labelProjName= new javax.swing.JLabel();

  
        //<End_initVariables>
	} 
	public void setUpGUI(Container container)
  {
		//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
	Top.setLayout(new GridBagLayout());
	inset = new Insets(10,10,10,10);
	setConstraints(0,3,0,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
	Top.add(labelDesc,cons);
	inset = new Insets(10,0,10,10);
	setConstraints(1,2,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
	Top.add(labelClassesVal,cons);
	inset = new Insets(10,10,10,10);
	setConstraints(0,2,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
	Top.add(labelClasses,cons);
	inset = new Insets(10,0,10,10);
	setConstraints(1,1,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
	Top.add(labelPackageVal,cons);
	inset = new Insets(10,10,10,10);
	setConstraints(0,1,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
	Top.add(labelPackage,cons);
	inset = new Insets(10,0,10,10);
	setConstraints(1,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
	Top.add(labelProjNameVal,cons);
	inset = new Insets(10,10,10,10);
	setConstraints(0,0,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
	Top.add(labelProjName,cons);
	
  
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

	public void showProjectProperty(String projectName) {
		// Read projectName.proj
		try {
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			DocumentBuilder db=dbf.newDocumentBuilder();
			Document doc=db.parse(new File("."+fs+"projects"+fs+projectName+".proj"));
			
			NodeList propList=doc.getElementsByTagName("property");
			Element tempNode=null;
			for(int i=0;i<propList.getLength();i++) {
				tempNode=(Element)propList.item(i);
				if(tempNode.getAttribute("name").equals("projectName")) {
					labelProjNameVal.setText(tempNode.getAttribute("value"));
					continue;
				}
				if(tempNode.getAttribute("name").equals("package")) {
					labelPackageVal.setText(tempNode.getAttribute("value"));
					continue;
				}
				if(tempNode.getAttribute("name").equals("ProjDesc")) {
					labelDesc.setText("<HTML><P>"+tempNode.getAttribute("value")+"</P></HTML>");
					continue;
				}
			}
			// Find the classes that are defined in a project. 
			// Open <WebNMS-Home>/projects/projectName/
			// Find Directories and Directory.mow files if both exist then 
			// then give each class. 
			File f=new File("."+fs+"projects"+fs+projectName);
			File classFiles[]=f.listFiles(new FileFilter() {
				public boolean accept(File f) {
					if(f.toString().trim().endsWith(".mow")) {
						return true;
					}
					else {
						return false;
					}
				}
			});
			if(classFiles.length==0) {
				labelClassesVal.setText("<HTML><P>Class Not Defined</P></HTML>");
				return;
			}
			String str="<HTML><OL>";
			String tempFileName=null;
			for(int i=0;i<classFiles.length;i++) {
				tempFileName=classFiles[i].getName();
				str+="<LI>"+labelPackageVal.getText().trim()+"."+tempFileName.substring(0,tempFileName.indexOf("."))+"</LI>";
			}
			str+="</OL></HTML>";
			labelClassesVal.setText(str);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}	
}







