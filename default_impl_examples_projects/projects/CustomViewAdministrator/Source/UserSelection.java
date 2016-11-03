//$Id: UserSelection.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory



package com.adventnet.nms.tools.CustomView;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.event.*;



public class UserSelection extends JPanel implements ActionListener
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	javax.swing.JList addedUsersList = null;
	javax.swing.JScrollPane JScrollPane11 = null;
	javax.swing.JList existingUsersList = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JButton add = null;
	javax.swing.JButton remove = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JTextArea JTextArea1 = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>
	Vector getusers=null;
	Vector groups=null;
        	Object[] user_sel;
        	Object[] add_sel;
	Vector v=null;
    	Vector addedUsers=null;
	UserGroupInformation userGroupInfo=null;
	Vector users=null;
	Hashtable groupVsUsers=null;
	
  

  


  


 

  

    public void init()
  {

	//<Begin_init> 
        if (initialized == true) return; 
        setPreferredSize(new Dimension(getPreferredSize().width+587,getPreferredSize().height+467)); 
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
            Top.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.LineBorder(new Color(-16777216),1),"User Group List",1,0,new Font("Dialog",0,12),new Color(-16764109)));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+Top,ex); 
          }

          try
          {
            JPanel1.setBorder(new javax.swing.border.EtchedBorder(0));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+JPanel1,ex); 
          }

          try
          {
            addedUsersList.setFixedCellWidth(2);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+addedUsersList,ex); 
          }

          try
          {
            existingUsersList.setFixedCellWidth(2);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+existingUsersList,ex); 
          }

          try
          {
            add.setFont(new Font("SansSerif",0,12));
            add.setHorizontalTextPosition(4);
            add.setText(">>");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+add,ex); 
          }

          try
          {
            remove.setFont(new Font("SansSerif",0,12));
            remove.setHorizontalTextPosition(4);
            remove.setText("<<");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+remove,ex); 
          }

          try
          {
            JTextArea1.setWrapStyleWord(true);
            JTextArea1.setBackground(new Color(-3355444));
            JTextArea1.setEditable(false);
            JTextArea1.setLineWrap(true);
            JTextArea1.setFont(new Font("Dialog",1,13));
            JTextArea1.setText(" Select user groups  from the below list for applying this customView");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+JTextArea1,ex); 
          }
		JTextArea1.setPreferredSize(new Dimension(JTextArea1.getPreferredSize().width+0,JTextArea1.getPreferredSize().height+2));
		JPanel3.setPreferredSize(new Dimension(JPanel3.getPreferredSize().width+10,JPanel3.getPreferredSize().height+10));

  
          //<End_setUpProperties>
		            JTextArea1.setText(" \nSelect user groups  from the below list for applying this customView");

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
        JPanel1= new javax.swing.JPanel();
        JScrollPane1= new javax.swing.JScrollPane();
        addedUsersList= new javax.swing.JList();
        JScrollPane11= new javax.swing.JScrollPane();
        existingUsersList= new javax.swing.JList();
        JPanel2= new javax.swing.JPanel();
        add= new javax.swing.JButton();
        remove= new javax.swing.JButton();
        JPanel3= new javax.swing.JPanel();
        JTextArea1= new javax.swing.JTextArea();

  
        //<End_initVariables>
	v=new Vector();

  } 
  public void setUpGUI(Container container)
  {


//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));
Top.add(JPanel1,BorderLayout.CENTER);
JPanel1.setLayout(new GridBagLayout());
inset = new Insets(5,5,15,5);
setConstraints(2,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel1.add(JScrollPane1,cons);
JScrollPane1.getViewport().add(addedUsersList);
inset = new Insets(5,5,15,5);
setConstraints(0,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel1.add(JScrollPane11,cons);
JScrollPane11.getViewport().add(existingUsersList);
inset = new Insets(5,5,15,5);
setConstraints(1,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel1.add(JPanel2,cons);
JPanel2.setLayout(new GridLayout(2,0,20,20));
JPanel2.add(add);
JPanel2.add(remove);
Top.add(JPanel3,BorderLayout.NORTH);
JPanel3.setLayout(new BorderLayout(5,5));
JPanel3.add(JTextArea1,BorderLayout.NORTH);

  
//<End_setUpGUI_Container>

		add.addActionListener(this);
        add.setActionCommand("add");

        
        remove.addActionListener(this);
        remove.setActionCommand("remove");
         existingUsersList.addListSelectionListener( new ListSelectionListener()
            {public void valueChanged(ListSelectionEvent lse)
                {
                    if ( lse.getSource().equals(existingUsersList) )
			user_sel = existingUsersList.getSelectedValues();
                }
            });

		
        addedUsersList.addListSelectionListener( new ListSelectionListener()
            { public void valueChanged(ListSelectionEvent lse)
                {
                    if ( lse.getSource().equals(addedUsersList) )
			add_sel =addedUsersList.getSelectedValues();
                }
            });
addUsersToList();
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
   public void actionPerformed(ActionEvent ae)
    {
        if(ae.getActionCommand().equals("add"))
        {
            if ( user_sel != null)
            {
                for(int i=0;i<user_sel.length;i++)
                {
                    boolean test=false;
                    for(int j=0;j<v.size();j++)
                    {
                        if( v.elementAt(j).equals(user_sel[i]))
                        {
                            test=true;
                        }                            
                    }
                    if(!test)
                        v.add(user_sel[i]);
                }
                addedUsersList.setListData(v.toArray());
              
              
			                
            }
        }
        if(ae.getActionCommand().equals("remove"))
        {
            int len = addedUsersList.getModel().getSize();
            if ( len != 0 && add_sel !=null)
            {
                for( int i=0;i<add_sel.length;++i)
                {
                    for( int j=0;j<v.size();++j)
                    {
                        if ( add_sel[i].equals(v.elementAt(j) ))
                            v.remove(j);
                    }
                }
                
                addedUsersList.setListData(v.toArray());
           		   
                
            }
        }
    }
             
	
    public void addUsers()
    {
	Vector v=new Vector();
	 if(userGroupInfo==null)
            {
                userGroupInfo=new UserGroupInformation();
            }
           groupVsUsers=new Hashtable();
	  groups=userGroupInfo.getAllGroups();
	
	 getusers=userGroupInfo.getAllUsers();	
	for(int i=0;i<groups.size();i++)
	{
		groupVsUsers.put(groups.elementAt(i).toString(),getusers.elementAt(i));
	}

	
	 existingUsersList.setListData(groups);
	

    }
    public Vector getAddedUsersList()
    {
       addedUsers=new Vector();
      
        if(addedUsersList!=null)
        {
            int size=addedUsersList.getModel().getSize();
            for(int i=0;i<size;i++)
            {
                	String group=addedUsersList.getModel().getElementAt(i).toString();
		Vector users=(Vector)groupVsUsers.get(group);	
		for(int j=0;j<users.size();j++)
		{
			if(!addedUsers.contains(users.elementAt(j)))
			{
				addedUsers.add(users.elementAt(j));
			}
		}
		               
            }
        }
	
	return addedUsers;
    }
    
public void addUsersToList()
{
	
		new UserThread().start();
}




  public UserSelection()
  {
    //<Begin_UserSelection>
    this.init();
  
    //<End_UserSelection>

  }

  public UserSelection(java.applet.Applet applet)
  {
    //<Begin_UserSelection_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_UserSelection_java.applet.Applet>
  }
	 public class UserThread extends Thread
    {
        public void run()
        {
                  
           
			addUsers();

          

        }
    }
public boolean validateUsers()
{
	if(addedUsersList.getModel().getSize()>0)
	{
		return true;
	}
	return false;
}		

}
























