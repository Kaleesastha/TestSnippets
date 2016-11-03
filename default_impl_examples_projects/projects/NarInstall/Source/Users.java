
/*
  $Id: Users.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $
 */

// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory


//<Begin_Version>
//version$1
//<End_Version>
package com.adventnet.nms.tools.nar;



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.event.*;
import java.net.URL;
import com.adventnet.nms.tools.utils.ImgConv;


public class Users extends JPanel implements ActionListener
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "DeploymentWizard";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	javax.swing.JList addedUsersList = null;
	javax.swing.JScrollPane JScrollPane2 = null;
	javax.swing.JList allUsersList = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JButton add = null;
	javax.swing.JButton remove = null;
	javax.swing.JTextArea textArea = null;
	javax.swing.JPanel JPanel4 = null;
	javax.swing.JCheckBox oidUpdate = null;
	javax.swing.JTextArea JTextArea1 = null;
	javax.swing.JLabel JLabel1 = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>
	Vector getusers;
	Vector groups;
        Object[] user_sel;
        Object[] add_sel;
        Vector v=null;
    NarExtracter extracter;
    Vector allExistingUsers=new Vector();
    Vector addedUsers;
    private static UsersList list=null;

    javax.swing.JTextArea  JTextArea2 = null;











   


  

  

 
    public void start()
  { 

  //<Begin_start> 
       if(running)
 return;
 running=true;

  
       //<End_start>
/*      if(extracter.deviceSpecific)
      {
          //JPanel4.setVisible(true);
         
         JPanel4.add(JTextArea1,BorderLayout.NORTH);
         JTextArea1.setVisible(true);
          oidUpdate.setVisible(true);
          textArea.setVisible(true);
          JTextArea2.setVisible(false);
      }
      else
      {
          //JPanel4.setVisible(false);
          JTextArea1.setVisible(false);
          textArea.setVisible(false);
          JPanel4.add(JTextArea2,BorderLayout.NORTH);
          JTextArea2.setVisible(true);
          oidUpdate.setVisible(false);
      }*/

          JTextArea1.setVisible(false);
          oidUpdate.setVisible(false);
  } 
	
  public void initVariables()
  { 

  //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        JPanel3= new javax.swing.JPanel();
        JPanel2= new javax.swing.JPanel();
        JScrollPane1= new javax.swing.JScrollPane();
        addedUsersList= new javax.swing.JList();
        JScrollPane2= new javax.swing.JScrollPane();
        allUsersList= new javax.swing.JList();
        JPanel1= new javax.swing.JPanel();
        add= new javax.swing.JButton();
        remove= new javax.swing.JButton();
        textArea= new javax.swing.JTextArea();
        JPanel4= new javax.swing.JPanel();
        oidUpdate= new javax.swing.JCheckBox();
        JTextArea1= new javax.swing.JTextArea();
        JLabel1= new javax.swing.JLabel();

  
        //<End_initVariables>
        v=new Vector();
        JTextArea2= new javax.swing.JTextArea();
        
       
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
   
  public void setUpGUI(Container container)
  { 

  //<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));
Top.add(JPanel3,BorderLayout.CENTER);
JPanel3.setLayout(new BorderLayout(5,5));
JPanel3.add(JPanel2,BorderLayout.CENTER);
JPanel2.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(2,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel2.add(JScrollPane1,cons);
JScrollPane1.getViewport().add(addedUsersList);
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel2.add(JScrollPane2,cons);
JScrollPane2.getViewport().add(allUsersList);
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel2.add(JPanel1,cons);
JPanel1.setLayout(new GridLayout(2,0,5,20));
JPanel1.add(add);
JPanel1.add(remove);
JPanel3.add(textArea,BorderLayout.NORTH);
JPanel3.add(JPanel4,BorderLayout.SOUTH);
JPanel4.setLayout(new BorderLayout(5,5));
JPanel4.add(oidUpdate,BorderLayout.CENTER);
JPanel4.add(JTextArea1,BorderLayout.NORTH);
Top.add(JLabel1,BorderLayout.WEST);

  
//<End_setUpGUI_Container>
        add.addActionListener(this);
        add.setActionCommand("add");
        JTextArea2.setVisible(false);
        
        remove.addActionListener(this);
        remove.setActionCommand("remove");
         allUsersList.addListSelectionListener( new ListSelectionListener()
            {public void valueChanged(ListSelectionEvent lse)
                {
                    if ( lse.getSource().equals(allUsersList) )
			user_sel = allUsersList.getSelectedValues();
                }
            });

		
        addedUsersList.addListSelectionListener( new ListSelectionListener()
            { public void valueChanged(ListSelectionEvent lse)
                {
                    if ( lse.getSource().equals(addedUsersList) )
			add_sel =addedUsersList.getSelectedValues();
                }
            });
         addedUsersList.setPreferredSize(new Dimension(175,150));
         allUsersList.setPreferredSize(new Dimension(175,150));
  } 
  public void setUpProperties()
  { 

  //<Begin_setUpProperties> 

          try
          {
            Top.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.LineBorder(new Color(-16777216),1),resourceBundle.getString("Users Information"),2,0,new Font("Dialog",0,12),new Color(-16777216)));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+Top,ex); 
          }

//<UserCode_Begin_Bean_components>

//<UserCode_End_Bean_components>

          try
          {
            JPanel3.setBorder(new javax.swing.border.BevelBorder(0));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel3,ex); 
          }

//<UserCode_Begin_Bean_JPanel3>

//<UserCode_End_Bean_JPanel3>

          try
          {
            JPanel2.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(0),resourceBundle.getString(""),2,0,new Font("Dialog",0,12),new Color(-10066279)));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel2,ex); 
          }

//<UserCode_Begin_Bean_JPanel2>

//<UserCode_End_Bean_JPanel2>

          try
          {
            addedUsersList.setPreferredSize(new Dimension(175,150));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+addedUsersList,ex); 
          }

//<UserCode_Begin_Bean_addedUsersList>

//<UserCode_End_Bean_addedUsersList>

          try
          {
            allUsersList.setPreferredSize(new Dimension(175,150));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+allUsersList,ex); 
          }

//<UserCode_Begin_Bean_allUsersList>

//<UserCode_End_Bean_allUsersList>

          try
          {
            add.setFont(new Font("Dialog",0,12));
            add.setText(resourceBundle.getString(">>"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+add,ex); 
          }

//<UserCode_Begin_Bean_add>

//<UserCode_End_Bean_add>

          try
          {
            remove.setFont(new Font("Dialog",0,12));
            remove.setText(resourceBundle.getString("<<"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+remove,ex); 
          }

//<UserCode_Begin_Bean_remove>

//<UserCode_End_Bean_remove>

          try
          {
            textArea.setBackground(new Color(-3355444));
            textArea.setWrapStyleWord(true);
            textArea.setLineWrap(true);
            textArea.setFont(new Font("Dialog",1,13));
            textArea.setEditable(true);
            textArea.setText(resourceBundle.getString("Select the users for whom the Nar has to be Installed."));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+textArea,ex); 
          }

//<UserCode_Begin_Bean_textArea>

//<UserCode_End_Bean_textArea>

          try
          {
            JPanel4.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(0),resourceBundle.getString(""),0,0,new Font("Dialog",0,12),new Color(-10066279)));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel4,ex); 
          }

//<UserCode_Begin_Bean_JPanel4>

//<UserCode_End_Bean_JPanel4>

          try
          {
            oidUpdate.setText(resourceBundle.getString("SYSObjectID Updation"));
            oidUpdate.setFont(new Font("Dialog",0,12));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+oidUpdate,ex); 
          }

//<UserCode_Begin_Bean_oidUpdate>

//<UserCode_End_Bean_oidUpdate>

          try
          {
            JTextArea1.setFont(new Font("Dialog",1,13));
            JTextArea1.setWrapStyleWord(true);
            JTextArea1.setLineWrap(true);
            JTextArea1.setBackground(new Color(-3355444));
            JTextArea1.setText(resourceBundle.getString("When SysObjectID Update option is checked, the existing SysObjectType of the device will be over written by the user specified type given while packaging."));
            JTextArea1.setEditable(false);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JTextArea1,ex); 
          }

//<UserCode_Begin_Bean_JTextArea1>

//<UserCode_End_Bean_JTextArea1>
		JLabel1.setPreferredSize(new Dimension(JLabel1.getPreferredSize().width+168,JLabel1.getPreferredSize().height+388));
		JTextArea1.setPreferredSize(new Dimension(JTextArea1.getPreferredSize().width+286,JTextArea1.getPreferredSize().height+97));
		oidUpdate.setPreferredSize(new Dimension(oidUpdate.getPreferredSize().width+162,oidUpdate.getPreferredSize().height+64));
		JPanel4.setPreferredSize(new Dimension(JPanel4.getPreferredSize().width+10,JPanel4.getPreferredSize().height+10));
		textArea.setPreferredSize(new Dimension(textArea.getPreferredSize().width+316,textArea.getPreferredSize().height+36));
		JPanel3.setPreferredSize(new Dimension(JPanel3.getPreferredSize().width+10,JPanel3.getPreferredSize().height+10));

  
          //<End_setUpProperties>
          
                oidUpdate.setText(resourceBundle.getString("SYSObjectID Update"));
             URL url=getClass().getResource("images"+File.separator+"usersinnms.jpg");
             JLabel1.setIcon(new ImageIcon(ImgConv.getImage(url.toString())));
             textArea.setText("\n"+resourceBundle.getString("NAR installation is based on user basis.Select the users for whom the Nar has to be Installed."));
             textArea.setEditable(false);
             
		textArea.setPreferredSize(new Dimension(textArea.getPreferredSize().width+0,textArea.getPreferredSize().height+18));
            JTextArea2.setFont(new Font("Dialog",1,13));
            JTextArea2.setWrapStyleWord(true);
            JTextArea2.setLineWrap(true);
            JTextArea2.setBackground(new Color(-3355444));
            JTextArea2.setText(resourceBundle.getString("NAR installation is based on user basis.Select the users for whom the nar has to be installed"));
            JTextArea2.setEditable(false);
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
        try
        {
            Vector grpUsers = new Vector();
			Vector users=new Vector();
		
            for(int i=0;i<groups.size();i++)
            {
            
                grpUsers = (Vector)getusers.elementAt(i);
               
                for(int j=0;j<grpUsers.size();j++)
                {
                    if(!users.contains(grpUsers.elementAt(j).toString()))
                        users.add(grpUsers.elementAt(j));
                    else
                        continue;
                }
            }
                        
            String[] add_user=new String[users.size()+2];
            add_user[0]="<All Existing Users>";
            add_user[1]="<Any NewUser>";
            for(int i =0,j= 2;i<users.size();i++,j++)
            {
                add_user[j] =(String)users.elementAt(i);
				
            }
            setAllExistingUsers(users);
            allUsersList.setListData(add_user);
        }
        catch(Exception e)
        {
           e.printStackTrace();
        }
    }
    public void setAddedUsersList()
    {
       addedUsers=new Vector();
      
        if(addedUsersList!=null)
        {
            int size=addedUsersList.getModel().getSize();
            for(int i=0;i<size;i++)
            {
                addedUsers.addElement(addedUsersList.getModel().getElementAt(i));

                
            }
        }
    }
    public Vector getAddedUsersList()
    {
        return addedUsers;
    }
    public void setAllExistingUsers(Vector v)
    {
        allExistingUsers=v;
    }
    public Vector getAllExistingUsers()
    {
        return allExistingUsers;
    }
  public void setUpConnections()
  { 

  //<Begin_setUpConnections> 

  
  //<End_setUpConnections>
  } 
  public void stop()
  { 

  //<Begin_stop> 
       if(!running)
 return;
 running=false;

  
       //<End_stop>
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
	if(getParameter("RESOURCE_PROPERTIES" ) != null)
	{
		localePropertiesFileName = getParameter("RESOURCE_PROPERTIES");
	}
	resourceBundle = com.adventnet.apiutils.Utility.getBundle(localePropertiesFileName,getParameter("RESOURCE_LOCALE"),applet);
        if (initialized) return; 
        setPreferredSize(new Dimension(getPreferredSize().width+569,getPreferredSize().height+393)); 
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
        // let us set the initialized variable to true so  
        // we dont initialize again even if init is called 
        initialized = true; 

  
         //<End_init>
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

    public boolean validateUsers()
    {

        if(getAddedUsersList().isEmpty())
        { 
            JOptionPane.showMessageDialog(null,resourceBundle.getString("Select users to whom Nar has to be installed"),resourceBundle.getString("Message"),JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        else
            return true;
    }


   


  public Users()
  {
    //<Begin_Users>
    this.init();
  
    //<End_Users>
  }
    public Users(NarExtracter extract)
    {
        extracter=extract;
        this.init();
        this.start();
        
    }
    public void addUsersToList()
    {
       
        new UserThread().start();
       
    }
    
        
  public Users(java.applet.Applet applet)
  {
    //<Begin_Users_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_Users_java.applet.Applet>
  }
    public class UserThread extends Thread
    {
        public void run()
        {
          
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            try
            {
                Thread.sleep(200);
            }
            catch(Exception e)
            {
                System.out.println(resourceBundle.getString(" exception in userThread"));
            }
            if(list==null)
            {
                list=new UsersList();
            }
            getusers=list.getAllUsers();
            groups=list.getAllGroups();
            addUsers();
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
          

        }
    }

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}





