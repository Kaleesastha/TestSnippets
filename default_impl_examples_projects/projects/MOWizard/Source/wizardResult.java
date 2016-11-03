//$Id: wizardResult.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $
package com.adventnet.nms.tools.mowizard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import com.adventnet.nms.util.*;
import com.adventnet.nms.tools.objtorel.TransverseListener;
import com.adventnet.nms.tools.objtorel.TransverseContainer;
import java.util.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import javax.swing.border.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;
import com.adventnet.apiutils.BuilderResourceBundle;
import com.adventnet.nms.tools.utils.ToolsUtil;

public class wizardResult extends AbstractTransversePanel implements TransverseListener
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	javax.swing.JPanel  Top = null;
	javax.swing.JPanel  wizardholderPanel = null;
	javax.swing.JScrollPane  projInfoScroller = null;
	javax.swing.JTextArea  ProjInfo = null;
	//<End_Variable_Declarations>

	private BuilderResourceBundle resourceBundle=ToolsUtil.getBundle();
	TransverseContainer tcon=null;
	String filePath=null;

	public void setUpProperties()throws Exception
  { 

		//<Begin_setUpProperties>

          try
          {
            wizardholderPanel.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.LineBorder(new Color(-16777216),1),resourceBundle.getString("Properties of current ManagedObject"),1,2,new Font("Dialog",0,12),new Color(-16764109)));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+wizardholderPanel,ex); 
          }

  //<End_setUpProperties>
  	ProjInfo.setEditable(false);
	} 
	public void init()
  { 

		if(false) {
			//<Begin_init>
        if (initialized == true) return; 
        setPreferredSize(new Dimension(getPreferredSize().width+731,getPreferredSize().height+512)); 
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

		if (initialized == true) return; 
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
	} 
	public void setUpConnections()throws Exception
  { 

		//<Begin_setUpConnections>

  //<End_setUpConnections>
	} 
	public void initVariables()throws Exception
  { 

		//<Begin_initVariables>
        Top= new javax.swing.JPanel();
        wizardholderPanel= new javax.swing.JPanel();
        projInfoScroller= new javax.swing.JScrollPane();
        ProjInfo= new javax.swing.JTextArea();

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
	Top.setLayout(new BorderLayout(5,5));
	Top.add(wizardholderPanel,BorderLayout.CENTER);
	wizardholderPanel.setLayout(new BorderLayout(5,5));
	wizardholderPanel.add(projInfoScroller,BorderLayout.CENTER);
	projInfoScroller.getViewport().add(ProjInfo);
	
  //<End_setUpGUI_Container>		
	} 
	public void setUpMenus()
  { 

		//<Begin_setUpMenus>

  //<End_setUpMenus>
	} 
	public void stop()
  { 

		//<Begin_stop>

  //<End_stop>
	} 
	public void start()
  { 

		//<Begin_start>

  //<End_start>
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

	public wizardResult()
  {
		//<Begin_wizardResult>
    this.init();
  
    //<End_wizardResult>
	}

	public wizardResult(java.applet.Applet applet)
  {
		//<Begin_wizardResult_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_wizardResult_java.applet.Applet>
	}

	public boolean isShowing()
	{
		return showing;
	}

	public void setShowing(boolean makeVisible)
	{
		showing=makeVisible;
	}

	public void addTransverseContainer(TransverseContainer transCon)
	{
		tcon=transCon;
		return;
	}

	public void loadScreenData()
	{
		String cname=null;
		String cimp=null;
		String cextends=null;
		String datatype=null;
		String name=null;

		StringBuffer html=new StringBuffer("Class\t  : ");
		Document doc=(Document)tcon.getTransverseComponent("XMLMODEL");
		Element rootNode=doc.getDocumentElement();	
		NodeList classprop=rootNode.getElementsByTagName("CLASS");	
		NodeList moprop=rootNode.getElementsByTagName("MOPROPERTY");

		Element classnode=(Element)classprop.item(0);	

		cname=classnode.getAttribute("name");
		cimp=classnode.getAttribute("implements");
		cextends=classnode.getAttribute("extends");
		html.append(cname+ "\n");
		html.append("Extends\t  : "+cextends+"\n");
		if(cimp != "")
			html.append("Implements\t  : "+cimp+"\n");
		html.append("\n\n");
		html.append("Totally " + moprop.getLength()+" properties have been defined in the class\n\n");
		html.append("The PropertyNames and their respective DataTypes are listed under:\n\n");

		for(int i=0;i<moprop.getLength();i++)
		{
			Element props=(Element)moprop.item(i);
			datatype=props.getAttribute("dataType");	
			name=props.getAttribute("name");
			html.append("\t"+name+"\t"+datatype+"\n");
		}
		ProjInfo.setText(html.toString());
	}

	public int nextActionPerformed(String str)
	{
		return 1;
	}
	public int previousActionPerformed(String str)
	{
		return 0;
	}
	
	public boolean finishActionPerformed()
	{
		return false;
	}
	
	public String getNmsHome() {
		return "."+File.separator;
	}
	
	public void cancelActionPerformed(String str)
	{
		if(str.trim().equals("Screen5")) {
			int returnVal=JOptionPane.showConfirmDialog(null,resourceBundle.getString("MO Generation is not complete. Do you want to cancel ???"),resourceBundle.getString("Confirm"),JOptionPane.YES_NO_OPTION);
			if(returnVal==JOptionPane.YES_OPTION) {
				((JDialog)getParentContainer(this)).dispose();
			}
        }
	}
	public void closeActionPerformed()
	{ }

	public Container getParentContainer(Container con){
		while(!(con instanceof JDialog)){
			con=con.getParent();
		}
		return con;
	}
}
