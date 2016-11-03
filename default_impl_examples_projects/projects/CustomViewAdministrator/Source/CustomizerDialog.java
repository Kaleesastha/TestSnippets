
//$Id: CustomizerDialog.java,v 1.2 2007/02/22 15:03:04 srajeswari Exp $

// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory




package com.adventnet.nms.tools.CustomView;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import com.adventnet.nms.tools.packager.WizardPanelListener;
import java.util.*;
import com.adventnet.nms.fe.common.NmsTreeAPI;
import java.rmi.*;
import com.adventnet.nms.topoui.ClientTopoAPIImpl;

public class CustomizerDialog  extends CustomMain implements WizardPanelListener
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	javax.swing.JPanel Top = null;
	com.adventnet.nms.tools.packager.WizardPanel WizardPanel1 = null;
	//<End_Variable_Declarations>

 //	PropertyWizard propWizard=null;
	NDCustomizer1 propScreen1=null;
	NDCustomizer2 propScreen2=null;
	TreeProperties treeProps=null;
	UserSelection userSelect=null;
	ParentSelection parentSelect=null;
	AlertCustomizer alertProps=null;
	EventCustomizer eventProps=null;
	PerfCustomizer1 perfProps1=null;
	PerfCustomizer2 perfProps2=null;
	PerfCustomizer3 perfProps3=null;
	ApplyModification applyModification=null;
	JDialog dialog=null;
	ClientTopoAPIImpl api=null;
	public String customType="";
	public String createOrModify="";
	Vector createdCustomViewInfo=null;
	Properties customViewProperties=null;
	String viewId="";
	String listOfUsers="";
	String customViewName="";
  public CustomizerDialog()
  {
    //<Begin_CustomizerDialog>
  
  //<End_CustomizerDialog>
  }

  public CustomizerDialog(java.applet.Applet applet)
  {
    //<Begin_CustomizerDialog_java.applet.Applet>
    this.applet = applet;
  
    //<End_CustomizerDialog_java.applet.Applet>
  }
public CustomizerDialog(String type,String s,Properties props)
{
	customType=type;
	createOrModify=s;
	customViewProperties=props;
	if(props!=null)
	{
		
		viewId=props.getProperty("viewid");
		
	}
}

public void showDialog()
{
	

	if(dialog==null)
	{
		dialog=new JDialog(CustomViewTypes.topFrame);
		if(createOrModify.equals("create"))
		{
			dialog.setTitle("CREATE "+customType);
		}
		else if(createOrModify.equals("modify"))
		{
			dialog.setTitle("MODIFY "+customType);				
		}
		this.init();
		setUpGUI(dialog.getContentPane());
		dialog.getContentPane().add(Top);
	}
		dialog.setModal(true);
		Toolkit kit=Toolkit.getDefaultToolkit();
		Dimension dim=kit.getScreenSize();
		dialog.setSize(500,600);
		dialog.setLocation((dim.width-500)/2,(dim.height-600)/2);
		dialog.setVisible(true);
		
		
		
}

  


 

  

    public void init()
  {
        //<Begin_init> 
        if (initialized == true) return; 
        try 
        { 
          initVariables(); 
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

	if(customType.equals("TOPO CUSTOMVIEW"))
	{
		propScreen1=new NDCustomizer1();
		propScreen2=new NDCustomizer2();
		//For modify screens we have to set the perivious values
		if(createOrModify.equals("modify")&&customViewProperties!=null)
		{
			Vector v=new Vector();
			propScreen1.setProperties(customViewProperties,v);
			propScreen2.setProperties(customViewProperties,v);
		}
		
		WizardPanel1.addComponents("Property1",propScreen1);	
		WizardPanel1.addComponents("Property2",propScreen2);		

	}
	else if(customType.equals("ALERT CUSTOMVIEW"))
	{
		alertProps=new AlertCustomizer();
			
		if(createOrModify.equals("modify")&&customViewProperties!=null)
		{
			alertProps.setProperties(customViewProperties);
		}
		WizardPanel1.addComponents("alert",alertProps);	
	}
	else if(customType.equals("EVENT CUSTOMVIEW"))
	{
		eventProps=new EventCustomizer();
		if(createOrModify.equals("modify")&&customViewProperties!=null)
		{
			eventProps.setProperties(customViewProperties);
		}
		WizardPanel1.addComponents("event",eventProps);	
	}
	else if(customType.equals("PERFORMANCE CUSTOMVIEW"))
	{
		perfProps1=new PerfCustomizer1();
		perfProps2=new PerfCustomizer2();
		perfProps3=new PerfCustomizer3();
		if(createOrModify.equals("modify")&&customViewProperties!=null)
		{
			Vector v=new Vector();
			perfProps1.setProperties(customViewProperties,v);
			perfProps2.setProperties(customViewProperties,v);
			perfProps3.setProperties(customViewProperties,v);
		}
		WizardPanel1.addComponents("perf1",perfProps1);	
		WizardPanel1.addComponents("perf2",perfProps2);	
		WizardPanel1.addComponents("perf3",perfProps3);	
	}
	
	// In creation only this screen is needed for modification we can't allow this user group selection
	if(createOrModify.equals("create"))
	{
		userSelect=new UserSelection();
		WizardPanel1.addComponents("UserSelection",userSelect);
	}
		parentSelect=new ParentSelection();		
		treeProps=new TreeProperties(customType);
	if(createOrModify.equals("modify")&&customViewProperties!=null)
	{

		parentSelect.setProperties(customViewProperties,"modify");
		
		treeProps.setProperties(customViewProperties);
	}
	WizardPanel1.addComponents("ParentSelection", parentSelect);		
	WizardPanel1.addComponents("TreeProperties",treeProps);
	if(createOrModify.equals("modify")&&customViewProperties!=null)
	{
		applyModification=new ApplyModification();
		applyModification.setProperties(customViewProperties);
		WizardPanel1.addComponents("apply",applyModification);
	}
	WizardPanel1.addTransverseListener(this);
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
        WizardPanel1= new com.adventnet.nms.tools.packager.WizardPanel();

  
        //<End_initVariables>
// propWizard=new PropertyWizard();

		

  } 
  public void setUpGUI(Container container)
  {
//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));
Top.add(WizardPanel1,BorderLayout.CENTER);

  
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
				public boolean nextActionPerformed(String identifier)
				{
					Component comp=WizardPanel1.getDisplayComponent();
					if(comp==propScreen1)
					{
						if(propScreen1.getViewName().trim().equals(""))
						{
							JOptionPane.showMessageDialog(null,"Please give a customview name","Message",JOptionPane.INFORMATION_MESSAGE);							
							return false;
						}
					}
					if(comp==alertProps)
					{
						if(alertProps.getViewName().trim().equals(""))
						{
							JOptionPane.showMessageDialog(null,"Please give a customview name","Message",JOptionPane.INFORMATION_MESSAGE);							
							return false;
						}
					}
					if(comp==eventProps)
					{
						if(eventProps.getViewName().trim().equals(""))
						{
							JOptionPane.showMessageDialog(null,"Please give a customview name","Message",JOptionPane.INFORMATION_MESSAGE);							
							return false;
						}
					}
					if(comp==perfProps1)
					{
						if(perfProps1.getViewName().trim().equals(""))
						{
							JOptionPane.showMessageDialog(null,"Please give a customview name","Message",JOptionPane.INFORMATION_MESSAGE);							
							return false;
						}
					}
					if(comp==userSelect)
					{
						if(!userSelect.validateUsers())
						{
							JOptionPane.showMessageDialog(null,"Please select user group for applying this customview","Message",JOptionPane.INFORMATION_MESSAGE);
							return false;
						}
						//this screen will come only for creating time.while modification "modify" and parent name is passed as argument 
						 dialog.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
						parentSelect.setUsers(userSelect.getAddedUsersList(),"create","");
						dialog.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
						
					}
					if(comp==parentSelect)
					{
						if(!parentSelect.validateParent())
						{
							return false;
						}						
					}
					return true;
				}

				public boolean previousActionPerformed(String identifier)
				{
					return true;
				}

				public boolean finishActionPerformed()
				{
					Component comp=WizardPanel1.getDisplayComponent();
					if(comp==applyModification)
					{
						if(!applyModification.validateSelection())
						{
							JOptionPane.showMessageDialog(null,"Please select a group for applying this modification","Message",JOptionPane.INFORMATION_MESSAGE);
							return false;
						}
					}
					dialog.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));		
					createCustomView();
					closeDialog();
					dialog.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					return true;
				}

				public void cancelActionPerformed(String identifier)
				{
					closeDialog();
				}
				public void closeActionPerformed()
				{
					closeDialog();
				}


				public void helpActionPerformed()
				{
				}

				private void closeDialog()
				{
					dialog.setVisible(false);
				}



public void createCustomView()
{
	//the vector is initialised here because we have to create new instance for each type and it will not be created when we click cancel
	createdCustomViewInfo=new Vector();
	 listOfUsers="";
	customViewName="";
	try
	{
			String name="//localhost/TreeAPI";
		       	 treeAPI = (NmsTreeAPI) Naming.lookup(name);
	}
	catch(Exception e)
	{
			System.out.println("Exception while instantiating treeAPI");
			e.printStackTrace();
	}
			
	if(customType.equals("TOPO CUSTOMVIEW"))
	{
		
		createTopoCustomView();
	}
	else if(customType.equals("ALERT CUSTOMVIEW"))
	{
		createAlertCustomView();
	}
	else if(customType.equals("EVENT CUSTOMVIEW"))
	{	
 
		createEventCustomView();	
	}
	else if(customType.equals("PERFORMANCE CUSTOMVIEW"))
	{
		createPerformanceCustomView();
	}
	String message="";
	if(listOfUsers.trim().equals(""))
	{
		if(createOrModify.equals("create"))
		{
			message="CustomView Creation failed";
		}
		else if(createOrModify.equals("modify"))
		{
			message="CustomvView Modification failed";
		}
	}
	else
	{
		if(createOrModify.equals("create"))
		{
			message="CustomView : "+customViewName+" is created successfully for"+"\n User: "+listOfUsers;
		}
		else if(createOrModify.equals("modify"))
		{
			message="CustomView : "+customViewName+" is modified successfully for"+"\n User: "+listOfUsers;
		}
	}
	JOptionPane.showMessageDialog(null,message,"Result",JOptionPane.INFORMATION_MESSAGE);
		
}

public void createTopoCustomView()
{
	Properties viewProperties=new Properties();
	String viewName=propScreen1.getViewName();
	customViewName=viewName;
	String treeIcon="";
	Properties p1=propScreen1.getProperties();
	Properties p2=propScreen2.getProperties();
	for(Enumeration enumerate=p1.propertyNames();enumerate.hasMoreElements();)
	{
		Object element=enumerate.nextElement();
		viewProperties.put(element,p1.getProperty(element.toString()));
	}
	for(Enumeration enumerate=p2.propertyNames();enumerate.hasMoreElements();)
	{
		Object element=enumerate.nextElement();
		viewProperties.put(element,p2.getProperty(element.toString()));
	}
	Properties columns=propScreen2.getTableColumns();
	Properties treeProperties=treeProps.getProperties();
	if(treeProperties.containsKey("ICON-FILE"))
	{
		treeIcon=treeProperties.getProperty("ICON-FILE");
	}
	if(createOrModify.equalsIgnoreCase("create"))
	{
		callForCreate(viewName,"Network Database",columns,treeProperties,viewProperties,treeIcon);
	}
	else if(createOrModify.equalsIgnoreCase("modify"))
	{
		callForModify(viewName,"Network Database",columns,treeProperties,viewProperties,treeIcon);
	}


}
public void callForCreate(String viewName,String module,Properties columns,Properties treeProperties,Properties viewProperties,String treeIcon)
{
	boolean result=true;
	Properties userVsParent=parentSelect.getUserVsParent();
	if(userVsParent!=null&&userVsParent.size()>0)
	{
		for(Enumeration enumerate=userVsParent.propertyNames();enumerate.hasMoreElements();)
		{
			String user=enumerate.nextElement().toString();
			String parent=(String)userVsParent.getProperty(user);
				
			result=create(viewName,module,user,parent,columns,treeProperties,viewProperties);
			System.out.println("Custom view with name "+viewName +"is created for "+user+" Result ="+result);
			//If the result is true then store the username viewname and its id This vector object is called from main class and it is added to the tree                                                                                                                        
			if(result==true)
			{
				// customViewId is a super class variable
				String[] s={user,viewName,customViewId,treeIcon,parent};
				createdCustomViewInfo.add(s);	
				if(listOfUsers.equals(""))
				{
					listOfUsers=user;
				}
				else
				{
					listOfUsers=listOfUsers+","+user;
				}
			}
		}
	}

}
public void callForModify(String viewName,String module,Properties columns,Properties treeProperties,Properties viewProperties,String treeIcon)
{
	boolean result=true;
	String parent="";
	Properties userVsId=applyModification.getUserVsId();
		
	
	Properties userVsParent=parentSelect.getUserVsParent();
	for(Enumeration e=userVsParent.propertyNames();e.hasMoreElements();)
	{
		parent=userVsParent.getProperty(e.nextElement().toString());
	}
	
	if(!parent.trim().equals("")&&userVsId!=null&&userVsId.size()>0)
	{
		for(Enumeration enumerate=userVsId.propertyNames();enumerate.hasMoreElements();)
		{		
			String user=enumerate.nextElement().toString();
			String viewId=(String)userVsId.getProperty(user);
			result=modify(viewId,viewName,module,user,parent,columns,treeProperties,viewProperties);	
			System.out.println("Custom view with name "+viewName +"is modified for "+user+" Result ="+result);
			if(result==true)
			{
				// if the user modifies the viewname and treeicon we have to set them to the user object so that it will take effect.
				String[] s={user,viewName,treeIcon,viewId,parent};
				createdCustomViewInfo.add(s);	
				if(listOfUsers.equals(""))
				{
					listOfUsers=user;
				}
				else
				{
					listOfUsers=listOfUsers+","+user;
				}	
			}
		}
	}

}
public void createAlertCustomView()
{
	String viewName=alertProps.getViewName();
	customViewName=viewName;
	String treeIcon="";
	Properties viewProperties=alertProps.getProperties();
	Properties columns=alertProps.getTableColumns();
	Properties treeProperties=treeProps.getProperties();
	if(treeProperties.containsKey("ICON-FILE"))
	{
		treeIcon=treeProperties.getProperty("ICON-FILE");
	}
	if(createOrModify.equalsIgnoreCase("create"))
	{
		callForCreate(viewName,"Alerts",columns,treeProperties,viewProperties,treeIcon);
	}
	else if(createOrModify.equalsIgnoreCase("modify"))
	{
		callForModify(viewName,"Alerts",columns,treeProperties,viewProperties,treeIcon);
	}

}
public void createEventCustomView()
{
	String treeIcon="";
	String viewName=eventProps.getViewName();
	customViewName=viewName;
	Properties viewProperties=eventProps.getProperties();
	Properties columns=eventProps.getTableColumns();
	Properties treeProperties=treeProps.getProperties();
	if(treeProperties.containsKey("ICON-FILE"))
	{
		treeIcon=treeProperties.getProperty("ICON-FILE");
	}
	if(createOrModify.equalsIgnoreCase("create"))
	{
		callForCreate(viewName,"Events",columns,treeProperties,viewProperties,treeIcon);
	}
	else if(createOrModify.equalsIgnoreCase("modify"))
	{
		callForModify(viewName,"Events",columns,treeProperties,viewProperties,treeIcon);
	}
}
public void createPerformanceCustomView()
{
	Properties viewProperties=new Properties();
	String treeIcon="";
	String viewName=perfProps1.getViewName();
	customViewName=viewName;
	Properties p1=perfProps1.getProperties();
	Properties p2=perfProps2.getProperties();
	Properties p3=perfProps3.getProperties();
	for(Enumeration enumerate=p1.propertyNames();enumerate.hasMoreElements();)
	{
		Object element=enumerate.nextElement();
		viewProperties.put(element,p1.getProperty(element.toString()));
	}
	for(Enumeration enumerate=p2.propertyNames();enumerate.hasMoreElements();)
	{
		Object element=enumerate.nextElement();
		viewProperties.put(element,p2.getProperty(element.toString()));
	}
	for(Enumeration enumerate=p3.propertyNames();enumerate.hasMoreElements();)
	{
		Object element=enumerate.nextElement();
		viewProperties.put(element,p3.getProperty(element.toString()));
	}
	Properties columns=perfProps3.getTableColumns();
	Properties treeProperties=treeProps.getProperties();
	if(treeProperties.containsKey("ICON-FILE"))
	{
		treeIcon=treeProperties.getProperty("ICON-FILE");
	}
	if(createOrModify.equalsIgnoreCase("create"))
	{
		callForCreate(viewName,"Stats Admin",columns,treeProperties,viewProperties,treeIcon);
	}
	else if(createOrModify.equalsIgnoreCase("modify"))
	{
		callForModify(viewName,"Stats Admin",columns,treeProperties,viewProperties,treeIcon);
	}
}
  public static void main(String [] args)
  {
     //<Begin_main_String[]>
     CustomizerDialog consoleApplication = new CustomizerDialog();
     consoleApplication.init();
  
     //<End_main_String[]>
  }
public Vector getCreatedCustomViewInfo()
{
	return createdCustomViewInfo;
}





 

  
}















































