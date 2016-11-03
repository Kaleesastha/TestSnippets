//$Id: AssignProp.java,v 1.4 2008/10/18 12:27:18 javeed Exp $

// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.security.ui;




import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Vector;
import java.util.Hashtable;
import java.util.Enumeration;



public class AssignProp extends JDialog 
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "SecurityAdministrationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	javax.swing.JList JList3 = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JButton JButton2 = null;
	javax.swing.JButton JButton1 = null;
	javax.swing.JScrollPane JScrollPane2 = null;
	javax.swing.JList JList1 = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JButton JButton3 = null;
	javax.swing.JButton JButton4 = null;
	javax.swing.DefaultListModel AllUser = null;
	javax.swing.DefaultListModel Usersforgroup = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>

	protected String user = "";
	protected static String what = "";
	String groupName = "";
	Vector viewsForGroup = null;

  	 

  


  


 

  

    public void init()
  {
        //<Begin_init> 
	if(getParameter("RESOURCE_PROPERTIES" ) != null)
	{
		localePropertiesFileName = getParameter("RESOURCE_PROPERTIES");
	}
	resourceBundle = com.adventnet.apiutils.Utility.getBundle(localePropertiesFileName,getParameter("RESOURCE_LOCALE"),applet);
        if (initialized) return; 
        this.setSize(getPreferredSize().width+553,getPreferredSize().height+378); 
          setTitle(resourceBundle.getString("AssignProp"));
        Container container = getContentPane();
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
        // let us set the initialized variable to true so  
        // we dont initialize again even if init is called 
        initialized = true; 

  
         //<End_init>
	 Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	double wid = screenSize.getWidth();
 	double hgt = screenSize.getHeight();
	setLocation((int)wid/2,( (int)hgt/2 - (int)hgt/4));	
	//customizeTree();	
	setTitle(resourceBundle.getString("Select ")+what);
	if(what.equals("Groups"))
	{
		setTitle(resourceBundle.getString("javaui.security.common.select")+resourceBundle.getString("javaui.securityadmin.settinggroup.group"));//No I18N
	}
	else if(what.equals("Users"))
	{
		setTitle(resourceBundle.getString("javaui.security.common.select")+resourceBundle.getString("javaui.securityadmin.settingusers.users"));//No I18N
	}
	else if(what.equals("CustomViewScopes"))
	{
		setTitle(resourceBundle.getString("javaui.security.common.select")+resourceBundle.getString("javaui.security.customviewscopes"));//No I18N
	}
	else if(what.equals("AuthorizedScopes"))
	{
		setTitle(resourceBundle.getString("javaui.security.common.select")+resourceBundle.getString("javaui.security.settingauthorizedscopes.authorizedscopes"));//No I18N
	}
	//setIconImage(AuthMain.getBuilderUiIfInstance().getFrameIcon());	
	AuthMain.getBuilderUiIfInstance().centerWindow(this);
	
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
            if (input.equals("MS_MODE")) value = "3"; 
            if (input.equals("RESOURCE_PROPERTIES")) value = "SecurityAdministrationResources"; 
            }
        return value;

  
           //<End_getParameter_String>
  } 
  public void setUpProperties()
  {
  //<Begin_setUpProperties> 

          try
          {
            JPanel1.setBorder(new javax.swing.border.EtchedBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel1,ex); 
          }

//<UserCode_Begin_Bean_JPanel1>

//<UserCode_End_Bean_JPanel1>

          try
          {
            JScrollPane1.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("")));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JScrollPane1,ex); 
          }

//<UserCode_Begin_Bean_JScrollPane1>

//<UserCode_End_Bean_JScrollPane1>

          try
          {
            JList3.setModel(AllUser);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JList3,ex); 
          }

//<UserCode_Begin_Bean_JList3>

//<UserCode_End_Bean_JList3>

          try
          {
            JButton2.setFont(new Font("SansSerif",0,12));
            JButton2.setHorizontalTextPosition(4);
            JButton2.setText(resourceBundle.getString(">"));
            JButton2.setToolTipText(resourceBundle.getString("Add"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton2,ex); 
          }

//<UserCode_Begin_Bean_JButton2>

//<UserCode_End_Bean_JButton2>

          try
          {
            JButton1.setFont(new Font("SansSerif",0,12));
            JButton1.setHorizontalTextPosition(4);
            JButton1.setText(resourceBundle.getString("<"));
            JButton1.setToolTipText(resourceBundle.getString("Remove"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton1,ex); 
          }

//<UserCode_Begin_Bean_JButton1>

//<UserCode_End_Bean_JButton1>

          try
          {
            JScrollPane2.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("")));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JScrollPane2,ex); 
          }

//<UserCode_Begin_Bean_JScrollPane2>

//<UserCode_End_Bean_JScrollPane2>

          try
          {
            JList1.setModel(Usersforgroup);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JList1,ex); 
          }

//<UserCode_Begin_Bean_JList1>

//<UserCode_End_Bean_JList1>

          try
          {
            JLabel1.setFont(new Font("SansSerif",0,12));
            JLabel1.setHorizontalTextPosition(4);
            JLabel1.setIconTextGap(6);
            JLabel1.setHorizontalAlignment(2);
            JLabel1.setForeground(new Color(-16764109));
            JLabel1.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("")));
            JLabel1.setText(resourceBundle.getString(""));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel1,ex); 
          }

//<UserCode_Begin_Bean_JLabel1>

//<UserCode_End_Bean_JLabel1>

          try
          {
            JButton3.setHorizontalTextPosition(4);
            JButton3.setText(resourceBundle.getString("  Ok  "));
            JButton3.setFont(new Font("Dialog",0,13));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton3,ex); 
          }

//<UserCode_Begin_Bean_JButton3>
JButton3.setMnemonic('O');
//<UserCode_End_Bean_JButton3>

          try
          {
            JButton4.setHorizontalTextPosition(4);
            JButton4.setText(resourceBundle.getString("Cancel"));
            JButton4.setFont(new Font("Dialog",0,13));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton4,ex); 
          }

//<UserCode_Begin_Bean_JButton4>
JButton4.setMnemonic('C');
//<UserCode_End_Bean_JButton4>
		JButton4.setPreferredSize(new Dimension(JButton4.getPreferredSize().width+4,JButton4.getPreferredSize().height+1));
		JButton3.setPreferredSize(new Dimension(JButton3.getPreferredSize().width+10,JButton3.getPreferredSize().height+1));

  
          //<End_setUpProperties>
	if(what.equals("Groups")){
		JScrollPane1.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("All Groups")));
		JScrollPane2.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("Selected Groups")));
		JLabel1.setText(resourceBundle.getString("<html><body><face font=\"sansserif,SansSerif\"></font size>Select the groups from the list for which the user needs to be assigned.</body></html>"));
	}
	if(what.equals("Users")){
		JScrollPane1.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("All Users")));
		JScrollPane2.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("Selected Users")));
		JLabel1.setText(resourceBundle.getString("<html><body><face font=\"sansserif,SansSerif\"></font size>Select the users from the list for which the group needs to be assigned.</body></html>"));
	}
	if(what.equals(resourceBundle.getString("CustomViewScopes"))){
		JScrollPane1.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("All CustomViewScopes")));
		JScrollPane2.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("Selected CustomViewScopes")));
		JLabel1.setText(resourceBundle.getString("<html><body><face font=\"sansserif,SansSerif\"></font size>Select the CustomViewScopes from the list for which the user needs to be assigned.</body></html>"));
	}
	if(what.equals(resourceBundle.getString("AuthorizedScopes"))){
		JScrollPane1.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("All AuthorizedScopes")));
		JScrollPane2.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("Selected AuthorizedScopes")));
		JLabel1.setText(resourceBundle.getString("<html><body><face font=\"sansserif,SansSerif\"></font size>Select the AuthorizedScopes from the list for which the CustomView needs to be assigned.</body></html>"));
	}
	//setResizable(false);
} 
  public void start()
  {
  //<Begin_start> 
       if(running)
 return;
 running=true;

  
       //<End_start>
  } 
  public void stop()
  {
  //<Begin_stop> 
       if(!running)
 return;
 running=false;

  
       //<End_stop>
  } 
  public void initVariables()
  {
        //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        JPanel1= new javax.swing.JPanel();
        JScrollPane1= new javax.swing.JScrollPane();
        JList3= new javax.swing.JList();
        JPanel2= new javax.swing.JPanel();
        JButton2= new javax.swing.JButton();
        JButton1= new javax.swing.JButton();
        JScrollPane2= new javax.swing.JScrollPane();
        JList1= new javax.swing.JList();
        JLabel1= new javax.swing.JLabel();
        JPanel3= new javax.swing.JPanel();
        JButton3= new javax.swing.JButton();
        JButton4= new javax.swing.JButton();
        AllUser= new javax.swing.DefaultListModel();
        Usersforgroup= new javax.swing.DefaultListModel();

  
        //<End_initVariables>
  } 
  public void setUpGUI(Container container)
  {
//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,2,1,0.01,0.01,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(JPanel1,cons);
JPanel1.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.02,0.015,cons.NORTH,cons.BOTH,inset,0,0);
JPanel1.add(JScrollPane1,cons);
JScrollPane1.getViewport().add(JList3);
inset = new Insets(5,5,5,6);
setConstraints(1,1,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel1.add(JPanel2,cons);
JPanel2.setLayout(new GridLayout(2,0,5,5));
JPanel2.add(JButton2);
JPanel2.add(JButton1);
inset = new Insets(5,5,5,5);
setConstraints(2,1,1,1,0.02,0.015,cons.SOUTH,cons.BOTH,inset,0,0);
JPanel1.add(JScrollPane2,cons);
JScrollPane2.getViewport().add(JList1);
inset = new Insets(0,0,5,0);
setConstraints(0,0,3,1,0.01,0.0010,cons.NORTH,cons.BOTH,inset,0,0);
JPanel1.add(JLabel1,cons);
inset = new Insets(0,5,5,5);
setConstraints(0,1,2,1,0.0,0.0015,cons.SOUTH,cons.BOTH,inset,0,0);
Top.add(JPanel3,cons);
JPanel3.setLayout(new FlowLayout(2,5,5));
JPanel3.add(JButton3);
JPanel3.add(JButton4);

  
//<End_setUpGUI_Container>
  } 
  public void setUpConnections()
  {
  //<Begin_setUpConnections> 

  
  //<End_setUpConnections>
  
	FireData fire = new FireData();
	JButton3.addActionListener(fire);
	JButton4.addActionListener(fire);
	JButton1.addActionListener(fire);
	JButton2.addActionListener(fire);
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





  public AssignProp()
  {
    //<Begin_AssignProp>
    pack();
  
    //<End_AssignProp>
  }

  public AssignProp(java.applet.Applet applet)
  {
    //<Begin_AssignProp_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_AssignProp_java.applet.Applet>
  }

 public AssignProp(Frame owner,java.applet.Applet applet)
  {
    super(owner);
    this.applet = applet;
    pack();
    this.setTitle("AssignProp");
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
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

 






  public void setVisible(boolean bl)
  {
        //<Begin_setVisible_boolean>
       	if(bl)
       	{
       	  init();
          start();
        }
        else
        {
          stop();
        }
        super.setVisible(bl);
  
                 //<End_setVisible_boolean>
  }



	
	
	//Utility methods.

	public void setData(java.util.Vector data,java.util.Vector olddata,String name){
		user = name;
		int size = data.size();
		for(int i=0;i<size;i++){
			AllUser.addElement(data.elementAt(i));
		}
		if(olddata == null)
		{
			olddata = new Vector();
		}
		int siz = olddata.size();
		for(int j=0;j<siz;j++){
			Usersforgroup.addElement(olddata.elementAt(j));
		}
	}

 
	public void setGroupDataForCustom(String gName,Vector viewsVec)
	{
		groupName = gName;
		viewsForGroup = viewsVec;
		//System.out.println("Group Name in AssignProp "+groupName);
	}





	//Class for connections.
	
	Vector group = null;
	
	class FireData implements java.awt.event.ActionListener,java.io.Serializable
	{
		public void actionPerformed(java.awt.event.ActionEvent action)
		{
			if(action.getActionCommand().equals(resourceBundle.getString(">"))){
			         if(JList3.getSelectedValue() != null && !Usersforgroup.contains( JList3.getSelectedValue()))
				         Usersforgroup.addElement(JList3.getSelectedValue());
			}
			else if(action.getActionCommand().equals(resourceBundle.getString("<"))){
				Usersforgroup.removeElement(JList1.getSelectedValue());			
			}
			else if(action.getActionCommand().equals(resourceBundle.getString("  Ok  "))){
				//System.out.println("INside the Ok "+Usersforgroup);
				if(what.equals("Groups")){
					group = new Vector();
					int size = Usersforgroup.size();
					for (int i=0;i<size;i++){
						group.addElement(Usersforgroup.elementAt(i));	
					}
					//System.out.println("For Users "+group+"   LIST   "+Usersforgroup);
					AuthMain.main.disableButtons();
					AuthMain.model.modifyUserData(user,null,group,null,null,null);

				}	
				else if(what.equals("Users")){
					group = new Vector();
					int size = Usersforgroup.size();
					for (int i=0;i<size;i++){
						group.addElement(Usersforgroup.elementAt(i));	
					}
					//System.out.println("For Groups "+group);
					AuthMain.main.disableButtons();
					AuthMain.model.modifyUserGroupData(user,group);
				}
				else if(what.equals(resourceBundle.getString("AuthorizedScopes")))
				{	
					Vector allViews = new Vector();
					group = new Vector();
					int size = Usersforgroup.size();
					for (int i=0;i<size;i++){
						group.addElement(Usersforgroup.elementAt(i));	
					}
					int allSize = AllUser.size();
					for(int i=0;i<allSize;i++)
					{
						allViews.addElement(AllUser.elementAt(i)); 
					}
					AuthMain.main.disableButtons();
					if(group.size() == 0)
					{
						group = new Vector();
					}
					
					for (int i=0;i<size;i++)
					{
						String viewName = (String)Usersforgroup.elementAt(i);
						if(!viewsForGroup.contains(viewName))
						{
							viewsForGroup.addElement(viewName);
						}
					}
					
					
					AuthMain.model.modViewProp(groupName,false,group,user);
					//AuthMain.model.modifyGroupData(groupName, viewsForGroup);
				}
				else if(what.equals(resourceBundle.getString("CustomViewScopes")))
				{
					//System.out.println("FOR THE CUSTOM VIEW");
					group = new Vector();
					int size = Usersforgroup.size();
					for (int i=0;i<size;i++)
					{
						group.addElement(Usersforgroup.elementAt(i));	
					}
					AuthMain.main.disableButtons();
					
				}				

				dispose();

			}
			else if(action.getActionCommand().equals(resourceBundle.getString("Cancel"))){
				dispose();
			}
		}
	}
 







 




 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
  public void setButtonStatus(String user)
  {
	Hashtable useroper = AuthMain.model.getOperationsForUser(user);

	if (useroper!=null)
	{
		for(Enumeration eu = useroper.keys();eu.hasMoreElements();)
		{
			String opername = (String)eu.nextElement();
			String opertype = (String)useroper.get(opername);


			if (opername.equals("Assign User To Group"))
			{
				if(opertype.equals("0"))
				{
					JButton2.setEnabled(false);
				}else
				{
					JButton2.setEnabled(true);
				}
			}

			else if (opername.equals("Remove User From Group"))
			{
				if(opertype.equals("0"))
				{
					JButton1.setEnabled(false);
				}else
				{
					JButton1.setEnabled(true);
				}
			}
		}
	}
	Hashtable groupoper = AuthMain.model.getOperationsForGroup(AuthMain.model.getGroupsForUser(user));
	if (groupoper!=null)
	{
		for(Enumeration eu = groupoper.keys();eu.hasMoreElements();)
		{
			String opername = (String)eu.nextElement();
			String opertype = (String)groupoper.get(opername);


			if (opername.equals("Assign User To Group"))
			{
				if(opertype.equals("0"))
				{
					JButton2.setEnabled(false);
				}else
				{
					JButton2.setEnabled(true);
				}
			}

			else if (opername.equals("Remove User From Group"))
			{
				if(opertype.equals("0"))
				{
					JButton1.setEnabled(false);
				}else
				{
					JButton1.setEnabled(true);
				}
			}
		}
	}
   }
}




































