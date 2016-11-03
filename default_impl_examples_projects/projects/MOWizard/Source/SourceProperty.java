//$Id: SourceProperty.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $
package com.adventnet.nms.tools.mowizard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.tree.*;
import com.adventnet.nms.tools.objtorel.TransverseContainer;
import org.w3c.dom.*;
import org.xml.sax.*;
import com.adventnet.apiutils.BuilderResourceBundle;
import com.adventnet.nms.tools.utils.ToolsUtil;

/**
 *  This screen will is used to show the required properties when the
 *  user selects a node from the ProjectTree from the MainScreen;
 *  When the serviceType is set to -1 transCon will contain the 
 *  the wizard for modification; Similary other services are given to 
 *  the user.  
 */

public class SourceProperty extends JPanel implements ActionListener 
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	javax.swing.JPanel  Top = null;
	javax.swing.JTabbedPane  sourcePropTab = null;
	javax.swing.JPanel  SourcePanel = null;
	javax.swing.JPanel  PropertyPanel = null;
	javax.swing.JPanel  labelModifyPanel = null;
	javax.swing.JLabel  propInfoLabel = null;
	javax.swing.JButton  propModifyButton = null;
	//<End_Variable_Declarations>
	MOEditor editor=null;
	TransverseContainer  transCon=null;
	int serviceType=-1;
	MainScreen mainScr=null;
	ClassProperties propTab=null;
	StatusPollerProp statPollProp= null;
	DiscFilterProp discFilterProp=null;
	RelJavaProp relJava=null;
	DeployScr  depScr=null;
	Document serviceHelper=null;
	ProjProperty projInfo=null;
	String projName=null;
	DefaultMutableTreeNode depNode=null;
	private BuilderResourceBundle resourceBundle=ToolsUtil.getBundle();	

		public SourceProperty()
  {
			//<Begin_SourceProperty>
    this.init();
  
    //<End_SourceProperty>
		}

	public SourceProperty(MOEditor moe,MainScreen msc) {
		editor=moe;
		mainScr=msc;
		this.init();
	}

	public void setEditor(MOEditor moe) {
		editor=moe;
	}

	public MOEditor getEditor() {
		return editor;
	}

	public void addTransverseComponent(TransverseContainer tCon) {
		transCon=tCon;
	}

	public TransverseContainer getTransverseComponent() {
		return transCon;
	}

	public SourceProperty(java.applet.Applet applet)
  {
		//<Begin_SourceProperty_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_SourceProperty_java.applet.Applet>
	}

	public int getServiceType() {
		return serviceType;
	}

	public void setServiceType(int sType) {
		/**
		 * 1001 - User Tester
		 * 1002 - Discovery Filter
		 * -1   - Class Node
		 * 1003 - Relational Java
		 * 1004 - Deployment Node.
		 */
		serviceType=sType;
	}

	private void modifyRelationalJava(ActionEvent ae) {
		if(mainScr.ProjectTree.getLastSelectedPathComponent()==null) {
			return;
		}
		DefaultMutableTreeNode selectedNode=(DefaultMutableTreeNode)mainScr.ProjectTree.getLastSelectedPathComponent();
		if(selectedNode.getUserObject() instanceof java.lang.String) {	
			return;
		}
		mainScr.generateRelationalJava((DefaultMutableTreeNode)selectedNode.getParent());
	}

	private void modifyDeploymentWizard(ActionEvent ae) {
		MOPackager mpk=new MOPackager();
		mpk.setDeployParams(serviceHelper,depNode,projName);
		mpk.startDeployment();
	}

	public void setDeployInfo(Document document,DefaultMutableTreeNode dNode,String projectName) {
		serviceHelper=document;
		depNode=dNode;
		projName=projectName;
	}

	public void setUpProperties()throws Exception
  { 
		//<Begin_setUpProperties>

          try
          {
            sourcePropTab.setTabPlacement(3);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+sourcePropTab,ex); 
          }

          try
          {
            propInfoLabel.setText(resourceBundle.getString("Click to modify properties of ManagedObject"));
            propInfoLabel.setForeground(new Color(-16764109));
            propInfoLabel.setFont(new Font("Dialog",0,16));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+propInfoLabel,ex); 
          }

          try
          {
            propModifyButton.setText(resourceBundle.getString("Modify"));
            propModifyButton.setBorder(new javax.swing.border.BevelBorder(0));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+propModifyButton,ex); 
          }
		propModifyButton.setPreferredSize(new Dimension(propModifyButton.getPreferredSize().width+36,propModifyButton.getPreferredSize().height+6));
		labelModifyPanel.setPreferredSize(new Dimension(labelModifyPanel.getPreferredSize().width+10,labelModifyPanel.getPreferredSize().height+10));

  //<End_setUpProperties>
		propModifyButton.addActionListener(this);	

	} 


	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == propModifyButton) {
			propModifyButton_Clicked(ae);
		}	
	}

	public void setServiceHelper(Document doc) {
		serviceHelper=doc;
	}

	
	

	public void setAdditionalInfo(String projectName) {
		projName=projectName;
	}



	public void setPropTabService(int serviceType) {
		// Here check which service of ManagedObject is available to be modified.
			DefaultMutableTreeNode node=(DefaultMutableTreeNode)mainScr.ProjectTree.getLastSelectedPathComponent();
		
			switch(getServiceType()) {
				case 1:
					projInfo=new ProjProperty();
					PropertyPanel.remove(1);
					PropertyPanel.add(projInfo,BorderLayout.CENTER);
					projInfo.showProjectProperty(projName);
					PropertyPanel.updateUI();
					labelModifyPanel.setVisible(false);
					break;
				case -1:
					// Show TransversePanel Wizard.
					propTab=new ClassProperties();
					PropertyPanel.remove(1);
					PropertyPanel.add(propTab,BorderLayout.CENTER);
					propTab.setClassProperties(serviceHelper);
					testToCreateOrModify(node,"Class");

					propInfoLabel.setText(resourceBundle.getString("Click to modify properties of Class"));
					propModifyButton.setText(resourceBundle.getString("Modify"));
					PropertyPanel.updateUI();
					labelModifyPanel.setVisible(true);
					break;
				case 1001:
					// Show UserTester Wizard.
					statPollProp=new StatusPollerProp();
					// We are removing any previous Property Panel showing in PropertyPanel and then 
					// adding our new Property Sheet "statPollProp"
					PropertyPanel.remove(1);
					PropertyPanel.add(statPollProp,BorderLayout.CENTER);
					statPollProp.setStatusPollerProperties(serviceHelper);
					testToCreateOrModify(node,"Status Poller");

					propInfoLabel.setText(resourceBundle.getString("Click to modify properties of Status Poller"));
					PropertyPanel.updateUI();
					labelModifyPanel.setVisible(true);
					break;
				case 1002:
					// Show Discovery Filter
					discFilterProp=new DiscFilterProp();
					PropertyPanel.remove(1);
					discFilterProp.setDiscoveryFilterProperties(serviceHelper);
					PropertyPanel.add(discFilterProp,BorderLayout.CENTER);	
					testToCreateOrModify(node,"Discovery Filter");

					propInfoLabel.setText(resourceBundle.getString("Click to modify properties of Discovery Filter"));
					PropertyPanel.updateUI();
					labelModifyPanel.setVisible(true);
					break;
				case 1003:
					relJava=new RelJavaProp();
					PropertyPanel.remove(1);
					relJava.setRelationalJavaProperties(serviceHelper);
					PropertyPanel.add(relJava,BorderLayout.CENTER);	
					testToCreateOrModify(node,"Relational Java");

					propInfoLabel.setText(resourceBundle.getString("Click to modify properties of Relational Java"));

					PropertyPanel.updateUI();
					labelModifyPanel.setVisible(true);
					// Show Relational Java
					break;
				case 1004:
					// Show DeploymentWizard. 
					depScr=new DeployScr();
					PropertyPanel.remove(1);
					depScr.init();
					PropertyPanel.add(depScr,BorderLayout.CENTER);
					propModifyButton.setText(resourceBundle.getString("Deploy"));
					propInfoLabel.setText(resourceBundle.getString("Click on Deploy button to package this ManagedObject"));
					PropertyPanel.updateUI();
					labelModifyPanel.setVisible(true);
					break;
		}
	}

	private void testToCreateOrModify(DefaultMutableTreeNode node,String str)
	{
		if(!mainScr.findNodeinTree((DefaultMutableTreeNode)node.getParent(),str))
		{
			return;
		}
		DefaultMutableTreeNode tempNode=mainScr.getNamedChild((DefaultMutableTreeNode)node.getParent(),str);
		if(tempNode==null)
		{
			return;
		}	
		ManagedObjectModel tmpMom=(ManagedObjectModel)tempNode.getUserObject();
		if(tmpMom.getFileName()!=null && tmpMom.getFileName().toString().trim().length()!=0)
		{
			propModifyButton.setText(resourceBundle.getString("Modify"));		
		}
		else
		{
			propModifyButton.setText(resourceBundle.getString("Create"));
		}
	}

	private void propModifyButton_Clicked(ActionEvent ae) {
		// Here check which service of ManagedObject is available to be modified.
		switch(getServiceType()) {
			case -1:
				// Show TransversePanel Wizard.
				modifyClassDefinition(ae);
				break;
			case 1000:
				// Since this is also related to class show the same
				// TransversePanel wizard.
				modifyClassDefinition(ae);
				break;
			case 1001:
				// Show UserTester Wizard.
				modifyUserTester(ae);
				break;
			case 1002:
				// Show Discovery Filter
				modifyDiscoveryFilter(ae);
				break;
			case 1003:
				// Show Relational Java
				modifyRelationalJava(ae);
				break;
			case 1004:
				// Show DeploymentWizard. 
				modifyDeploymentWizard(ae);
				break;
		}		
	}

	private void modifyClassDefinition(ActionEvent ae) {
		if(serviceHelper==null) {
			return;
		}
		mainScr.loadClassFromModel(true);
	}		

	private void modifyUserTester(ActionEvent ae) {
		if(mainScr.ProjectTree.getLastSelectedPathComponent()==null) {
			return;
		}
		DefaultMutableTreeNode usrTstNode=(DefaultMutableTreeNode)mainScr.ProjectTree.getLastSelectedPathComponent();
		if(usrTstNode.getUserObject() instanceof java.lang.String) {
			return;
		}
		mainScr.callUserTester((DefaultMutableTreeNode)usrTstNode.getParent());
	}

	private void modifyDiscoveryFilter(ActionEvent ae) {
		if(mainScr.ProjectTree.getLastSelectedPathComponent()==null) {
			return;
		}
		DefaultMutableTreeNode discFilNode=(DefaultMutableTreeNode)mainScr.ProjectTree.getLastSelectedPathComponent();
		if(discFilNode.getUserObject() instanceof java.lang.String) {
			return;
		}
		mainScr.callDiscoveryFilter((DefaultMutableTreeNode)discFilNode.getParent());
	}	

	public void init()
  { 

		//<Begin_init>
        if (initialized == true) return; 
        setPreferredSize(new Dimension(getPreferredSize().width+692,getPreferredSize().height+555)); 
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

	public void setUpConnections()throws Exception
  { 
		//<Begin_setUpConnections>

  //<End_setUpConnections>
	}

	public void initVariables()throws Exception
  { 
		//<Begin_initVariables>
        Top= new javax.swing.JPanel();
        sourcePropTab= new javax.swing.JTabbedPane();
        SourcePanel= new javax.swing.JPanel();
        PropertyPanel= new javax.swing.JPanel();
        labelModifyPanel= new javax.swing.JPanel();
        propInfoLabel= new javax.swing.JLabel();
        propModifyButton= new javax.swing.JButton();

  //<End_initVariables>
		propTab=new ClassProperties();
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
	Top.add(sourcePropTab,BorderLayout.CENTER);
	sourcePropTab.addTab(resourceBundle.getString("Source"),null,SourcePanel,resourceBundle.getString("Click to view Source"));
	SourcePanel.setLayout(new BorderLayout(5,5));
	sourcePropTab.addTab(resourceBundle.getString("Property"),null,PropertyPanel,resourceBundle.getString("Click to view/modify properties"));
	PropertyPanel.setLayout(new BorderLayout(5,5));
	PropertyPanel.add(labelModifyPanel,BorderLayout.SOUTH);
	labelModifyPanel.setLayout(new BorderLayout(5,5));
	labelModifyPanel.add(propInfoLabel,BorderLayout.CENTER);
	labelModifyPanel.add(propModifyButton,BorderLayout.EAST);
	
  //<End_setUpGUI_Container>
		SourcePanel.add(editor,BorderLayout.CENTER);
		PropertyPanel.add(propTab,BorderLayout.CENTER);
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

	void setActiveIndex(int tabIndex) {
		sourcePropTab.setSelectedIndex(tabIndex);
	}

	public void setSourceTabComponent(Component comp) {
		if(comp==null) {
			return;
		}
		// Since we have added only one component we use the value "0" to remove
		// that component from the SourcePanel
		SourcePanel.remove(0);
		SourcePanel.add(comp,BorderLayout.CENTER);
	}
}
