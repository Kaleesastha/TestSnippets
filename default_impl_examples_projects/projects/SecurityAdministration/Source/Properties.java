
//$Id: Properties.java,v 1.1 2006/08/29 13:57:02 build Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.security.ui;




import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.tree.*;
import java.util.*;


public class Properties extends JDialog 
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "SecurityAdministrationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JButton closeButton = null;
	javax.swing.JTabbedPane JTabbedPane1 = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JLabel JLabel2 = null;
	javax.swing.JTextField UserText = null;
	javax.swing.JTextField DescrText = null;
	javax.swing.JLabel JLabel3 = null;
	javax.swing.JLabel JLabel4 = null;
	javax.swing.JTextField GroupText = null;
	javax.swing.JTextField ViewText = null;
	javax.swing.JPanel JPanel6 = null;
	javax.swing.JTextField NumericTextField1 = null;
	javax.swing.JLabel JLabel9 = null;
	javax.swing.JPanel JPanel61 = null;
	javax.swing.JTextField NumericTextField11 = null;
	javax.swing.JLabel JLabel91 = null;
	javax.swing.JLabel JLabel6 = null;
	javax.swing.JLabel JLabel10 = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	javax.swing.JTree JTree1 = null;
	javax.swing.JPanel JPanel4 = null;
	javax.swing.JLabel JLabel5 = null;
	javax.swing.JPanel JPanel5 = null;
	javax.swing.JLabel JLabel7 = null;
	javax.swing.JLabel JLabel8 = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>


  
  
  
   


  public Properties()
  {
    //<Begin_Properties>
    pack();
  
    //<End_Properties>
  }

  public Properties(java.applet.Applet applet)
  {
    //<Begin_Properties_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_Properties_java.applet.Applet>
  }
 public Properties(Frame owner,java.applet.Applet applet)
  {
   super(owner); 
    this.applet = applet;
    pack();
    this.setTitle("Properties");
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
  }	

 
    public void setUpProperties()
  { 

  //<Begin_setUpProperties> 

//<UserCode_Begin_Bean_Top>

setResizable(false);
//<UserCode_End_Bean_Top>

          try
          {
            closeButton.setText(resourceBundle.getString("Close"));
            closeButton.setFont(new Font("Dialog",0,12));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+closeButton,ex); 
          }

//<UserCode_Begin_Bean_closeButton>

//<UserCode_End_Bean_closeButton>

          try
          {
            JLabel1.setText(resourceBundle.getString("User Status"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel1,ex); 
          }

//<UserCode_Begin_Bean_JLabel1>

//<UserCode_End_Bean_JLabel1>

          try
          {
            JLabel2.setText(resourceBundle.getString("User name"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel2,ex); 
          }

//<UserCode_Begin_Bean_JLabel2>

//<UserCode_End_Bean_JLabel2>

          try
          {
            UserText.setEditable(false);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+UserText,ex); 
          }

//<UserCode_Begin_Bean_UserText>

//<UserCode_End_Bean_UserText>

          try
          {
            DescrText.setEditable(false);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+DescrText,ex); 
          }

//<UserCode_Begin_Bean_DescrText>

//<UserCode_End_Bean_DescrText>

          try
          {
            JLabel3.setText(resourceBundle.getString("Groups for the user"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel3,ex); 
          }

//<UserCode_Begin_Bean_JLabel3>

//<UserCode_End_Bean_JLabel3>

          try
          {
            JLabel4.setText(resourceBundle.getString("Views for the user"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel4,ex); 
          }

//<UserCode_Begin_Bean_JLabel4>

//<UserCode_End_Bean_JLabel4>

          try
          {
            GroupText.setEditable(false);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+GroupText,ex); 
          }

//<UserCode_Begin_Bean_GroupText>

//<UserCode_End_Bean_GroupText>

          try
          {
            ViewText.setEditable(false);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+ViewText,ex); 
          }

//<UserCode_Begin_Bean_ViewText>

//<UserCode_End_Bean_ViewText>

          try
          {
            NumericTextField1.setText(resourceBundle.getString("No Expiry"));
            NumericTextField1.setEnabled(true);
            NumericTextField1.setEditable(false);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+NumericTextField1,ex); 
          }

//<UserCode_Begin_Bean_NumericTextField1>

//<UserCode_End_Bean_NumericTextField1>

          try
          {
            JLabel9.setHorizontalAlignment(2);
            JLabel9.setFont(new Font("SansSerif",0,12));
            JLabel9.setForeground(new Color(-16777216));
            JLabel9.setHorizontalTextPosition(4);
            JLabel9.setText(resourceBundle.getString("Days remaining "));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel9,ex); 
          }

//<UserCode_Begin_Bean_JLabel9>

//<UserCode_End_Bean_JLabel9>

          try
          {
            NumericTextField11.setText(resourceBundle.getString("No Expiry"));
            NumericTextField11.setEnabled(true);
            NumericTextField11.setEditable(false);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+NumericTextField11,ex); 
          }

//<UserCode_Begin_Bean_NumericTextField11>

//<UserCode_End_Bean_NumericTextField11>

          try
          {
            JLabel91.setHorizontalAlignment(2);
            JLabel91.setFont(new Font("SansSerif",0,12));
            JLabel91.setForeground(new Color(-16777216));
            JLabel91.setHorizontalTextPosition(4);
            JLabel91.setText(resourceBundle.getString("Days remaining "));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel91,ex); 
          }

//<UserCode_Begin_Bean_JLabel91>

//<UserCode_End_Bean_JLabel91>

          try
          {
            JLabel6.setHorizontalAlignment(2);
            JLabel6.setFont(new Font("SansSerif",0,12));
            JLabel6.setForeground(new Color(-16777216));
            JLabel6.setHorizontalTextPosition(4);
            JLabel6.setText(resourceBundle.getString("User Expiry "));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel6,ex); 
          }

//<UserCode_Begin_Bean_JLabel6>

//<UserCode_End_Bean_JLabel6>

          try
          {
            JLabel10.setHorizontalAlignment(2);
            JLabel10.setFont(new Font("SansSerif",0,12));
            JLabel10.setForeground(new Color(-16777216));
            JLabel10.setHorizontalTextPosition(4);
            JLabel10.setText(resourceBundle.getString("Password Expiry "));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel10,ex); 
          }

//<UserCode_Begin_Bean_JLabel10>

//<UserCode_End_Bean_JLabel10>

          try
          {
            JTree1.setShowsRootHandles(false);
            JTree1.setBackground(new Color(-1));
            JTree1.setRootVisible(false);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JTree1,ex); 
          }

//<UserCode_Begin_Bean_JTree1>

//<UserCode_End_Bean_JTree1>

          try
          {
            JLabel5.setFont(new Font("Dialog",0,12));
            JLabel5.setForeground(new Color(-16764109));
            JLabel5.setText(resourceBundle.getString(""));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel5,ex); 
          }

//<UserCode_Begin_Bean_JLabel5>

//<UserCode_End_Bean_JLabel5>

          try
          {
            JLabel7.setForeground(new Color(-16764109));
            JLabel7.setFont(new Font("Dialog",0,12));
            JLabel7.setText(resourceBundle.getString(" Allowed Operations"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel7,ex); 
          }

//<UserCode_Begin_Bean_JLabel7>

//<UserCode_End_Bean_JLabel7>

          try
          {
            JLabel8.setFont(new Font("Dialog",0,12));
            JLabel8.setForeground(new Color(-16764109));
            JLabel8.setText(resourceBundle.getString("Disallowed operations."));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel8,ex); 
          }

//<UserCode_Begin_Bean_JLabel8>

//<UserCode_End_Bean_JLabel8>
		JPanel5.setPreferredSize(new Dimension(JPanel5.getPreferredSize().width+40,JPanel5.getPreferredSize().height+10));
		JPanel4.setPreferredSize(new Dimension(JPanel4.getPreferredSize().width+441,JPanel4.getPreferredSize().height+24));
		JScrollPane1.setPreferredSize(new Dimension(JScrollPane1.getPreferredSize().width+40,JScrollPane1.getPreferredSize().height+184));
		NumericTextField11.setPreferredSize(new Dimension(NumericTextField11.getPreferredSize().width+103,NumericTextField11.getPreferredSize().height+1));
		NumericTextField1.setPreferredSize(new Dimension(NumericTextField1.getPreferredSize().width+103,NumericTextField1.getPreferredSize().height+1));
		closeButton.setPreferredSize(new Dimension(closeButton.getPreferredSize().width+22,closeButton.getPreferredSize().height+0));
		JPanel2.setPreferredSize(new Dimension(JPanel2.getPreferredSize().width+385,JPanel2.getPreferredSize().height+0));

  
//<End_setUpProperties>
		            JPanel1.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("General User Description")));
  } 
  public void init()
  { 

  //<Begin_init> 
	if(getParameter("RESOURCE_PROPERTIES" ) != null)
	{
		localePropertiesFileName = getParameter("RESOURCE_PROPERTIES");
	}
	resourceBundle = com.adventnet.apiutils.Utility.getBundle(localePropertiesFileName,getParameter("RESOURCE_LOCALE"),applet);
        if (initialized) return; 
        this.setSize(getPreferredSize().width+518,getPreferredSize().height+520); 
          setTitle(resourceBundle.getString("Properties"));
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
      setTitle(resourceBundle.getString("User properties"));
		if(!AuthMain.standalone)
		{
			JTree1.setRootVisible(false);
		}
		else
		{
			JTree1.setRootVisible(true);
		}
		 
		AuthMain.getBuilderUiIfInstance().centerWindow(this);
		JLabel5.setIcon(AuthMain.getBuilderUiIfInstance().getImage("treeconfig.png"));		
		JLabel7.setIcon(AuthMain.getBuilderUiIfInstance().getImage("checked.png"));		
		JLabel8.setIcon(AuthMain.getBuilderUiIfInstance().getImage("unchecked.png"));		
		JViewport viewp = new JViewport();
		JLabel lab = new JLabel(resourceBundle.getString("Permissions Tree"));
		lab.setHorizontalAlignment((int)JLabel.CENTER_ALIGNMENT);
		lab.setForeground(Color.black);
		viewp.setView(lab);
		JScrollPane1.setColumnHeader(viewp);
		
		
		for(int i=1;i<JTree1.getRowCount();i++)
				JTree1.expandRow(i);

	 addKeyListener( new KeyAdapter(){
					public void keyTyped(java.awt.event.KeyEvent arg0)
					{
						if(arg0.getKeyCode() == arg0.VK_ESCAPE)
							{
								close();
							}
					}
				} );
		
	
  } 
  public void setUpConnections()
  { 

  //<Begin_setUpConnections> 

      JTree1_JTree1_conn1 JTree1_JTree1_conn11 =  new JTree1_JTree1_conn1();
      JTree1.addMouseListener(JTree1_JTree1_conn11);
      JTabbedPane1_JTabbedPane1_conn1 JTabbedPane1_JTabbedPane1_conn11 =  new JTabbedPane1_JTabbedPane1_conn1();
      JTabbedPane1.addChangeListener(JTabbedPane1_JTabbedPane1_conn11);
      closeButton_closeButton_conn1 closeButton_closeButton_conn11 =  new closeButton_closeButton_conn1();
      closeButton.addActionListener(closeButton_closeButton_conn11);
  
      //<End_setUpConnections>
  } 
  public void initVariables()
  { 
if(false)
		{
  //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        JPanel2= new javax.swing.JPanel();
        closeButton= new javax.swing.JButton();
        JTabbedPane1= new javax.swing.JTabbedPane();
        JPanel1= new javax.swing.JPanel();
        JLabel1= new javax.swing.JLabel();
        JLabel2= new javax.swing.JLabel();
        UserText= new javax.swing.JTextField();
        DescrText= new javax.swing.JTextField();
        JLabel3= new javax.swing.JLabel();
        JLabel4= new javax.swing.JLabel();
        GroupText= new javax.swing.JTextField();
        ViewText= new javax.swing.JTextField();
        JPanel6= new javax.swing.JPanel();
        NumericTextField1= new javax.swing.JTextField();
        JLabel9= new javax.swing.JLabel();
        JPanel61= new javax.swing.JPanel();
        NumericTextField11= new javax.swing.JTextField();
        JLabel91= new javax.swing.JLabel();
        JLabel6= new javax.swing.JLabel();
        JLabel10= new javax.swing.JLabel();
        JPanel3= new javax.swing.JPanel();
        JScrollPane1= new javax.swing.JScrollPane();
        JTree1= new javax.swing.JTree();
        JPanel4= new javax.swing.JPanel();
        JLabel5= new javax.swing.JLabel();
        JPanel5= new javax.swing.JPanel();
        JLabel7= new javax.swing.JLabel();
        JLabel8= new javax.swing.JLabel();

  
        //<End_initVariables>
			}

        Top= new javax.swing.JPanel();
        JPanel2= new javax.swing.JPanel();
        closeButton= new javax.swing.JButton();
        JTabbedPane1= new javax.swing.JTabbedPane();
        JPanel1= new javax.swing.JPanel();
        JLabel1= new javax.swing.JLabel();
        JLabel2= new javax.swing.JLabel();
        UserText= new javax.swing.JTextField();
        DescrText= new javax.swing.JTextField();
        JLabel3= new javax.swing.JLabel();
        JLabel4= new javax.swing.JLabel();
        GroupText= new javax.swing.JTextField();
        ViewText= new javax.swing.JTextField();
        JPanel6= new javax.swing.JPanel();
        NumericTextField1= new javax.swing.JTextField();
        JLabel9= new javax.swing.JLabel();
        JPanel61= new javax.swing.JPanel();
        NumericTextField11= new javax.swing.JTextField();
        JLabel91= new javax.swing.JLabel();
        JLabel6= new javax.swing.JLabel();
        JLabel10= new javax.swing.JLabel();
        JPanel3= new javax.swing.JPanel();
        JScrollPane1= new javax.swing.JScrollPane();
        JPanel4= new javax.swing.JPanel();
        JLabel5= new javax.swing.JLabel();
        JPanel5= new javax.swing.JPanel();
        JLabel7= new javax.swing.JLabel();
        JLabel8= new javax.swing.JLabel();
       Object previousExpanded = UIManager.put("Tree.expandedIcon",AuthMain.getBuilderUiIfInstance().getImage("expand3.png"));
       Object previousCollapsed = UIManager.put("Tree.collapsedIcon", AuthMain.getBuilderUiIfInstance().getImage("collapse3.png"));
       JTree1= new javax.swing.JTree();
       UIManager.put("Tree.expandedIcon", previousExpanded);
       UIManager.put("Tree.collapsedIcon", previousCollapsed);	
       JTree1.putClientProperty("JTree.lineStyle", "Angled");		

  } 
   
  public void setUpGUI(Container container)
  { 

  //<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));
Top.add(JPanel2,BorderLayout.SOUTH);
JPanel2.setLayout(new FlowLayout(2,5,5));
JPanel2.add(closeButton);
Top.add(JTabbedPane1,BorderLayout.CENTER);
JTabbedPane1.addTab(resourceBundle.getString("General"),null,JPanel1,null);
JPanel1.setLayout(new GridBagLayout());
inset = new Insets(20,5,20,5);
setConstraints(0,1,1,1,0.0,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel1.add(JLabel1,cons);
inset = new Insets(20,5,20,5);
setConstraints(0,0,1,1,0.0,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel1.add(JLabel2,cons);
inset = new Insets(20,5,10,5);
setConstraints(1,0,1,1,0.0,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel1.add(UserText,cons);
inset = new Insets(20,5,10,5);
setConstraints(1,1,1,1,0.0,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel1.add(DescrText,cons);
inset = new Insets(20,5,20,5);
setConstraints(0,2,1,1,0.0,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel1.add(JLabel3,cons);
inset = new Insets(20,5,10,5);
setConstraints(0,3,1,1,0.0,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel1.add(JLabel4,cons);
inset = new Insets(20,5,10,5);
setConstraints(1,2,1,1,0.0,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel1.add(GroupText,cons);
inset = new Insets(20,5,10,5);
setConstraints(1,3,1,1,0.0,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel1.add(ViewText,cons);
inset = new Insets(20,0,10,5);
setConstraints(1,4,1,1,0.0,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel1.add(JPanel6,cons);
JPanel6.setLayout(new FlowLayout(0,5,5));
JPanel6.add(NumericTextField1);
JPanel6.add(JLabel9);
inset = new Insets(20,0,10,5);
setConstraints(1,5,1,1,0.1,0.1,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel1.add(JPanel61,cons);
JPanel61.setLayout(new FlowLayout(0,5,5));
JPanel61.add(NumericTextField11);
JPanel61.add(JLabel91);
inset = new Insets(20,5,10,5);
setConstraints(0,4,1,1,0.0,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel1.add(JLabel6,cons);
inset = new Insets(20,5,10,5);
setConstraints(0,5,1,1,0.0,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel1.add(JLabel10,cons);
JTabbedPane1.addTab(resourceBundle.getString("Permission Tree View"),null,JPanel3,null);
JPanel3.setLayout(new BorderLayout(5,5));
JPanel3.add(JScrollPane1,BorderLayout.CENTER);
JScrollPane1.getViewport().add(JTree1);
JPanel3.add(JPanel4,BorderLayout.NORTH);
JPanel4.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(0,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel4.add(JLabel5,cons);
JPanel3.add(JPanel5,BorderLayout.SOUTH);
JPanel5.setLayout(new GridLayout(2,1,5,5));
JPanel5.add(JLabel7);
JPanel5.add(JLabel8);

  
//<End_setUpGUI_Container>
  } 
   
  public void stop()
  { 

  //<Begin_stop> 
       if(!running)
 return;
 running=false;

  
       //<End_stop>
  } 
  public void start()
  { 

  //<Begin_start> 
       if(running)
 return;
 running=true;

  
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
            if (input.equals("MS_MODE")) value = "3"; 
            if (input.equals("RESOURCE_PROPERTIES")) value = "SecurityAdministrationResources"; 
            }
        return value;

  
           //<End_getParameter_String>
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

 com.adventnet.security.ui.AbstractSecurityModel model = null;	
	
 public void setSecurityModel(com.adventnet.security.ui.AbstractSecurityModel sModel)
	{
		model = sModel;
		JTree1.setModel(model.getTreeModel());
		for(int i=0;i<JTree1.getRowCount();i++)
		 {
 			JTree1.expandRow(i);
		 }
	}	
 
 public void setUserName(String username)
	{
		UserText.setText(username);
		setData();
	}
Vector viewsforuser = new Vector();	
public void setData()
	{
 		viewsforuser.clear();
		
 		

	    	String groupfield = "";
                      String viewfield = "";
		Vector usergroup = (Vector)model.getGroupsForUser(UserText.getText());
                      if(usergroup.size() > 0)
			{
				for(int i=0;i<usergroup.size();i++)
				{
				     groupfield  += usergroup.elementAt(i).toString() + " , ";
				}	
				GroupText.setText(groupfield.substring(0,groupfield.length() -2));
				GroupText.setToolTipText(groupfield.substring(0,groupfield.length() -2));

			Hashtable attb = model.getUserAttributes(UserText.getText());
               DescrText.setText(attb.get("status").toString());
	   	if((attb.get("passwdexpirytime") == null) || (attb.get("userexpirytime") == null))
		{
			NumericTextField1.setText(resourceBundle.getString("No Expiry"));
			NumericTextField11.setText(resourceBundle.getString("No Expiry"));
			return;
		}	
		if(!attb.get("passwdexpirytime").toString().equals("0"))
		{
	    NumericTextField11.setText(attb.get("passwdexpirytime").toString());
		}
		if(!attb.get("userexpirytime").toString().equals("0"))
		{
	    NumericTextField1.setText(attb.get("userexpirytime").toString());	
		}
				for(int i=0;i<usergroup.size();i++)
				{
				 Vector groupview = model.getViewsForGroup(usergroup.elementAt(i).toString());
				 if(groupview.isEmpty())
				{
	
					return;
				}
	     	
				for(int j=0;j<groupview.size();j++)
				{
				  viewsforuser.add(groupview.elementAt(j).toString());					
				  viewfield += groupview.elementAt(j).toString() + " , ";
				}
			
		                      }
				
               ViewText.setText(viewfield.substring(0,viewfield.length() -2));
	    ViewText.setToolTipText(viewfield.substring(0,viewfield.length() -2));
                                   }
	
	
		
	}	


 public boolean find(DefaultMutableTreeNode rootnode, Object obj)
	{
		
	  java.util.Vector vec = new java.util.Vector();
	  for(int i=0;i<rootnode.getChildCount();i++)
			     {
			 	   DefaultMutableTreeNode node1 = (DefaultMutableTreeNode)rootnode.getChildAt(i);
				   vec.add(node1.getUserObject());
		             find(node1,obj);
				 }
	
	  if(vec.contains(obj))	
	    {
		   return true;
	    }

	 return false;	
	
	}
              

public java.util.Hashtable useroperations = null;

DefaultTreeModel tempModel = null;
	
private void setTreeModel(DefaultTreeModel tempModel)
	{
      	      this.tempModel = tempModel;
	      Hashtable temphash1 = new Hashtable();
	      Hashtable temphash2 = new Hashtable();

	      for(int i=0;i< viewsforuser.size(); i++)	
		{
		    temphash2 = model.getViewOperations( (String)viewsforuser.elementAt(i));
		    for(Enumeration e = temphash2.keys(); e.hasMoreElements();)
			{
				Object key =e.nextElement();
				Object value = temphash2.get(key);
				if( temphash1.containsKey(key) && (temphash1.get(key).toString().equals("1")))
					{
  					       continue;
					}
				temphash1.put( key, value);
			}
		}	
 	   Hashtable defaulthash = model.getOperationsForUser( UserText.getText());
	   for(Enumeration enum1 = defaulthash.keys(); enum1.hasMoreElements();)
		{
			Object key =enum1.nextElement();
			Object value = defaulthash.get(key);
			if(temphash1.containsKey(key) && (temphash1.get(key).toString().equals("1")))
				{
				     continue;
				}
			temphash1.put( key, value);
		}
		
	   useroperations = temphash1;

              if(viewsforuser.size() == 1)
		{
	 		renderOpTree((DefaultMutableTreeNode)tempModel.getRoot());  
			return;			
		}
	else
		{
			constructUserOperationHash((DefaultMutableTreeNode)((DefaultTreeModel)JTree1.getModel()).getRoot());
		}
	 renderOpTree((DefaultMutableTreeNode)tempModel.getRoot()); 

  }

public void constructUserOperationHash(DefaultMutableTreeNode parentnode)
	{

 	  DefaultMutableTreeNode parent = parentnode ;
	  DefaultMutableTreeNode child = null;	
       	  for(int i=0;i<parent.getChildCount();i++)
		{
	  		child = (DefaultMutableTreeNode)tempModel.getChild(parent,i);
                                 String userob = ((TreeObject)child.getUserObject()).toString();
			if(useroperations.containsKey(userob))
			 {
                   		   if(useroperations.get(userob).equals("1"))
 			    	{
					if(!child.isLeaf())
					{
				  	 for(int j=0;j<child.getChildCount();j++)
				    		{
					DefaultMutableTreeNode childofchild = (DefaultMutableTreeNode)tempModel.getChild(child,j);
		 			String userob1 = ((TreeObject)childofchild.getUserObject()).toString();
					useroperations.put(userob1,"1");
				    		}
			         		}
			 	}
  		 				
		 	 }
 			constructUserOperationHash(child);
	 	  }
	}	

public void renderOpTree(DefaultMutableTreeNode parentnode)
	{

 	  DefaultMutableTreeNode parent = parentnode ;
	  DefaultMutableTreeNode child = null;	
       	  for(int i=0;i<parent.getChildCount();i++)
		{
	 		child = (DefaultMutableTreeNode)tempModel.getChild(parent,i);
                                 String userob = ((TreeObject)child.getUserObject()).toString();
			if(useroperations.containsKey(userob))
			 {
			   ((TreeObject)child.getUserObject()).setSelected(true);
                   		   if(useroperations.get(userob).equals("1"))
			    {
 				((TreeObject)child.getUserObject()).setIncluded(true);
				if(!child.isLeaf())
				{
				  for(int j=0;j<child.getChildCount();j++)
					{
				  DefaultMutableTreeNode childofchild = (DefaultMutableTreeNode)tempModel.getChild(child,j);
				  ((TreeObject)childofchild.getUserObject()).setSelected(true);									
	 			  String userob1 = ((TreeObject)childofchild.getUserObject()).toString();
				  ((TreeObject)childofchild.getUserObject()).setIncluded(true);

				  if(useroperations.containsKey(userob1))
		 		    {
				         if(useroperations.get(userob).equals("0"))
				         ((TreeObject)childofchild.getUserObject()).setIncluded(false);
				   }

				  else useroperations.put(userob1,"1");
			 	   	}
			    }
		  }

	     else if(useroperations.get(userob).equals("0"))
			   	{
				    ((TreeObject)child.getUserObject()).setIncluded(false);
				}
         		 				
		  }
      	else
 			{
				((TreeObject)child.getUserObject()).setSelected(false);	
			}	
			
 			renderOpTree(child);	
			
	 	}	
		
	 	
		
	}	
	
    
	public void close()
	{
 	   model = null;
	  JTree1 = null;
	  useroperations = null;   
	  removeAll(); 
	  dispose();  
	}	

public void recursivelySelectChildren(DefaultMutableTreeNode node, boolean selected)
	{
	       if(selected)
		{
		 for(int i=0;i<node.getChildCount();i++)
		   {
		           DefaultMutableTreeNode child = (DefaultMutableTreeNode)tempModel.getChild(node,i); 
			((TreeObject)child.getUserObject()).setSelected(false);     
			((TreeObject)child.getUserObject()).setIncluded(false);     		
		   } 

	          	}
		
	       else
		{
		  for(int i=0;i<node.getChildCount();i++)
		   {
		              DefaultMutableTreeNode child = (DefaultMutableTreeNode)tempModel.getChild(node,i); 
			   ((TreeObject)child.getUserObject()).setSelected(true);     
			   ((TreeObject)child.getUserObject()).setIncluded(true);     		
		   } 	


		}

	}


//<Begin__class_closeButton_closeButton_conn1>

 class closeButton_closeButton_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_closeButton_closeButton_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
close();
     }
//<UserCode_End_Connection_closeButton_closeButton_conn1>
 }//<End__class_closeButton_closeButton_conn1>
                        
 
//<Begin__class_JTabbedPane1_JTabbedPane1_conn1>

 class JTabbedPane1_JTabbedPane1_conn1 implements javax.swing.event.ChangeListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JTabbedPane1_JTabbedPane1_conn1>



     //Listener Interface Methods Are Added Below 


     public void stateChanged( javax.swing.event.ChangeEvent arg0)
     {
 if(JTabbedPane1.getSelectedIndex() == 1)
  {
    JTree1.setCellRenderer(new CustomTreeRenderer());  
    String str = resourceBundle.getString("The following tree displays allowed / disallowed  operations for the user  :  ")+ UserText.getText();   
    JLabel5.setText(str); 
    DefaultTreeModel tempModel =  (DefaultTreeModel)JTree1.getModel();
    setTreeModel(tempModel);   
    renderOpTree((DefaultMutableTreeNode)tempModel.getRoot());  
   JTree1.repaint(); 
  }  
     }
//<UserCode_End_Connection_JTabbedPane1_JTabbedPane1_conn1>
 }//<End__class_JTabbedPane1_JTabbedPane1_conn1>
                        
 

                        


                        
 


//<Begin__class_JTree1_JTree1_conn1>

 class JTree1_JTree1_conn1 extends java.awt.event.MouseAdapter implements java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JTree1_JTree1_conn1>



     //Listener Interface Methods Are Added Below 


     public void mousePressed( java.awt.event.MouseEvent arg0)
     {
 /* 
  int row = JTree1.getRowForLocation(arg0.getX(), arg0.getY()); 
 if(row == -1)
  return;
  TreePath  path =  JTree1.getPathForRow(row); 
  DefaultMutableTreeNode node =  (DefaultMutableTreeNode )path.getLastPathComponent();
 TreeObject tob = (TreeObject)node.getUserObject() ;
 if(tob.isSelected())
  {
   tob.setSelected(false);
   if(!node.isLeaf()) 
   {
   for(int i=0;i<node.getChildCount();i++)
   {
              DefaultMutableTreeNode child = (DefaultMutableTreeNode)tempModel.getChild(node,i); 
   ((TreeObject)child.getUserObject()).setSelected(false);     
   } 
   } 
  } 
 else  
  {
   tob.setSelected(true);
   if(!node.isLeaf()) 
   {
   for(int i=0;i<node.getChildCount();i++)
   {
              DefaultMutableTreeNode child = (DefaultMutableTreeNode)tempModel.getChild(node,i); 
   ((TreeObject)child.getUserObject()).setSelected(true);     
   } 
   } 
  } 
 if(tob.isIncluded())
  {
   tob.setIncluded(false);
      if(!node.isLeaf()) 
   {
   for(int i=0;i<node.getChildCount();i++)
   {
              DefaultMutableTreeNode child = (DefaultMutableTreeNode)tempModel.getChild(node,i); 
   ((TreeObject)child.getUserObject()).setIncluded(false);     
   } 
   } 
  } 
 else  
  {
   tob.setIncluded(true);
   if(!node.isLeaf()) 
   {
   for(int i=0;i<node.getChildCount();i++)
   {
              DefaultMutableTreeNode child = (DefaultMutableTreeNode)tempModel.getChild(node,i); 
   ((TreeObject)child.getUserObject()).setIncluded(true);     
   } 
   } 
  }  
  
  
  
 JTree1.revalidate(); 
 JTree1.repaint(); 
  */
     }
//<UserCode_End_Connection_JTree1_JTree1_conn1>
 }//<End__class_JTree1_JTree1_conn1>
                        
 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
                        }










































