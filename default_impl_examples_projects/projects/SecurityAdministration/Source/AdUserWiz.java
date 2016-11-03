
//$Id: AdUserWiz.java,v 1.9.12.1 2013/08/06 06:41:09 wesley Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

//<Begin_Version>
//version$8
//<End_Version>
package com.adventnet.security.ui;




import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import com.adventnet.nms.util.NmsClientUtil;
import com.adventnet.nms.client.ValidateCredentials;
import java.text.MessageFormat;


public class AdUserWiz extends JDialog 
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "SecurityAdministrationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JButton backButton = null;
	javax.swing.JButton nextButton = null;
	javax.swing.JButton cancelButton = null;
	com.adventnet.beans.panels.CardPanel CardPanel1 = null;
	//<End_Variable_Declarations>

//	public static boolean state = false;
  
     Vector userGroupVec = null;
  

     public boolean STATE = true;

     AgeingPanel age = null;

   


  

  

  public AdUserWiz(  Frame owner, java.applet.Applet applet)
  {

    super(owner);		
    this.applet = applet;
    pack();
       //this.setTitle(resourceBundle.getString("AdUserWiz"));
   this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

  }	
 
    public void setUpProperties()
  { 

  //<Begin_setUpProperties> 

          try
          {
            backButton.setFont(new Font("Dialog",0,13));
            backButton.setText(resourceBundle.getString("Back"));
            backButton.setMaximumSize(new Dimension(95,29));
            backButton.setMinimumSize(new Dimension(95,29));
            backButton.setPreferredSize(new Dimension(95,29));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+backButton,ex); 
          }

//<UserCode_Begin_Bean_backButton>
backButton.setMnemonic('B');
//<UserCode_End_Bean_backButton>

          try
          {
            nextButton.setText(resourceBundle.getString("Next"));
            nextButton.setFont(new Font("Dialog",0,13));
            nextButton.setMaximumSize(new Dimension(95,29));
            nextButton.setMinimumSize(new Dimension(95,29));
            nextButton.setPreferredSize(new Dimension(95,29));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+nextButton,ex); 
          }

//<UserCode_Begin_Bean_nextButton>
nextButton.setMnemonic('N');
//<UserCode_End_Bean_nextButton>

          try
          {
            cancelButton.setText(resourceBundle.getString("Cancel"));
            cancelButton.setFont(new Font("Dialog",0,13));
            cancelButton.setMaximumSize(new Dimension(96,29));
            cancelButton.setMinimumSize(new Dimension(96,29));
            cancelButton.setPreferredSize(new Dimension(96,29));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+cancelButton,ex); 
          }

//<UserCode_Begin_Bean_cancelButton>
cancelButton.setMnemonic('C');
//<UserCode_End_Bean_cancelButton>

          try
          {
            CardPanel1.setBorder(new javax.swing.border.SoftBevelBorder(0));
            java.lang.String[]  CardPanel1cardAndClassNames_array = new java.lang.String[ 3 ]; 
            CardPanel1cardAndClassNames_array[ 0 ] = resourceBundle.getString("user=com.adventnet.security.ui.AddUsrpanel");
            CardPanel1cardAndClassNames_array[ 1 ] = resourceBundle.getString("age=com.adventnet.security.ui.AgeingPanel");
            CardPanel1cardAndClassNames_array[ 2 ] = resourceBundle.getString("group=com.adventnet.security.ui.Group");
            CardPanel1.setCardAndClassNames(CardPanel1cardAndClassNames_array);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+CardPanel1,ex); 
          }

//<UserCode_Begin_Bean_CardPanel1>

//<UserCode_End_Bean_CardPanel1>
		JPanel1.setPreferredSize(new Dimension(JPanel1.getPreferredSize().width+209,JPanel1.getPreferredSize().height+0));

  
          //<End_setUpProperties>
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
        this.setSize(getPreferredSize().width+620,getPreferredSize().height+427); 
          setTitle(resourceBundle.getString("AdUserWiz"));
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
	          setTitle(resourceBundle.getString("User Administration"));		
		CardPanel1.showCard("user");
          	           backButton.setEnabled(false);
 		age = new AgeingPanel();
		
		//AuthMain.getBuilderUiIfInstance().ciWindow(this);
		
		addWindowListener(new WindowAdapter()
					{
						public void windowClosing(WindowEvent we)
							{
								close();
							}
					}
				   );
	
	((com.adventnet.security.ui.Group)CardPanel1.getCard("group")).setParent(this);
	AuthMain.getBuilderUiIfInstance().centerWindow(this);
	//oldPassword.requestFocus();
	//setTitle("Reset Password");		
		

  } 
  public void setUpConnections()
  { 

  //<Begin_setUpConnections> 

      backButton_backButton_conn1 backButton_backButton_conn11 =  new backButton_backButton_conn1();
      backButton.addActionListener(backButton_backButton_conn11);
      nextButton_nextButton_conn1 nextButton_nextButton_conn11 =  new nextButton_nextButton_conn1();
      nextButton.addActionListener(nextButton_nextButton_conn11);
      cancelButton_cancelButton_conn1 cancelButton_cancelButton_conn11 =  new cancelButton_cancelButton_conn1();
      cancelButton.addActionListener(cancelButton_cancelButton_conn11);
  
      //<End_setUpConnections>
  } 
  public void initVariables()
  { 

  //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        JPanel1= new javax.swing.JPanel();
        backButton= new javax.swing.JButton();
        nextButton= new javax.swing.JButton();
        cancelButton= new javax.swing.JButton();
        CardPanel1= new com.adventnet.beans.panels.CardPanel(applet);

  
        //<End_initVariables>
	
	userGroupVec = new Vector();	
  } 
   
  public void setUpGUI(Container container)
  { 

  //<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));
Top.add(JPanel1,BorderLayout.SOUTH);
JPanel1.setLayout(new FlowLayout(2,5,5));
JPanel1.add(backButton);
JPanel1.add(nextButton);
JPanel1.add(cancelButton);
Top.add(CardPanel1,BorderLayout.CENTER);

  
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

 public void setUser()
	{
            
	   if(userGroupVec.elementAt(2) == null)	
     {

AuthMain.model.setUserData(userGroupVec.elementAt(0).toString(),null,(Vector)userGroupVec.elementAt(3),(Hashtable)userGroupVec.elementAt(4),(Hashtable)userGroupVec.elementAt(5),userGroupVec.elementAt(1).toString());
     }	
	   else
	   { 	
AuthMain.model.setUserData(userGroupVec.elementAt(0).toString(),userGroupVec.elementAt(2).toString(),(Vector)userGroupVec.elementAt(3),
					(Hashtable)userGroupVec.elementAt(4),(Hashtable)userGroupVec.elementAt(5),userGroupVec.elementAt(1).toString());
              }
	   userGroupVec.clear();		
	}
 
 public void editUser()
	{
            if(userGroupVec.elementAt(1) == null)	
               {
model.modifyUserData(userGroupVec.elementAt(0).toString(),null,(Vector)userGroupVec.elementAt(3),
					(Hashtable)userGroupVec.elementAt(4),(Hashtable)userGroupVec.elementAt(5),userGroupVec.elementAt(1).toString());
	   }	
	   else
        {
model.modifyUserData(userGroupVec.elementAt(0).toString(),userGroupVec.elementAt(2).toString(),(Vector)userGroupVec.elementAt(3),
					(Hashtable)userGroupVec.elementAt(4),(Hashtable)userGroupVec.elementAt(5),userGroupVec.elementAt(1).toString());

        }
	  userGroupVec.clear();			
	}

 	

  	
    public void editUserPwd()
	{
		
	}
    
    public Vector getAllGroups()
	{
        /*Vector allGroupVec = new Vector();
        Vector uservec = model.getAllUserNames();
        for(int i=0;i < uservec.size();i++)
        {
            String uname = (String)uservec.elementAt(i);
            Vector grpvec = model.getGroupsForUser(uname);
            for(int j=0;j<grpvec.size();j++)
            {
                if(!allGroupVec.contains(grpvec.elementAt(j).toString()))							
                {
                    allGroupVec.add(grpvec.elementAt(j));
                }
            }
        }
        Vector grps = model.getAllGroupNames();
        for(int k=0;k < grps.size();k++)
        {
            if(!allGroupVec.contains(grps.elementAt(k).toString()))							
            {
                allGroupVec.add(grps.elementAt(k));	
            }
        }
        return allGroupVec;*/

        Vector getGroupVec = model.getGroups();
        Vector groupNames = new Vector();

        for (int i = 0; i < getGroupVec.size(); i=i+2)
        {
            groupNames.addElement(getGroupVec.elementAt(i));
        }
        Collections.sort(groupNames);
        return groupNames;
	}

   com.adventnet.security.ui.AbstractSecurityModel model = null;
	
 public void setSecurityModel(com.adventnet.security.ui.AbstractSecurityModel model)	
	{
		this.model = model;
	  ((com.adventnet.security.ui.Group)CardPanel1.getCard("group")).setSecurityModel(model);	
	}	
	
	public void setBuilderUiIf(com.adventnet.nms.util.CommonBuilderUIInterface  uiImpl)
	{
	((com.adventnet.security.ui.Group)CardPanel1.getCard("group")).setBuilderUiIf(uiImpl);
	((com.adventnet.security.ui.AddUsrpanel)CardPanel1.getCard("user")).setBuilderUiIf(uiImpl);
//	((com.adventnet.security.ui.PwdPanel)CardPanel1.getCard("pass")).setBuilderUiIf(uiImpl);
		
	}	 

public void setState(boolean state)
	{
 		// STATE is true for add , false for edit .
	 	STATE = state;
	}		
	
public void setValues(String uname,Vector groups)
	{
		
	  ((com.adventnet.security.ui.Group)CardPanel1.getCard("group")).setUserGroups(groups,getAllGroups());
	  ((com.adventnet.security.ui.Group)CardPanel1.getCard("group")).setSecurityModel(model);
	  ((com.adventnet.security.ui.AddUsrpanel)CardPanel1.getCard("user")).setUserName(uname);
	    ((com.adventnet.security.ui.Group)CardPanel1.getCard("group")).setUserName(uname);
		
	  if(!STATE)	
	 	{
	 ((com.adventnet.security.ui.AddUsrpanel)CardPanel1.getCard("user")).setPassword();
		}	
	Hashtable useratb = model.getUserAttributes(uname);
	int userage =0;
	int pwdage =0;	
		if(useratb.get("userexpirytime") != null)
	userage = new Integer(useratb.get("userexpirytime").toString()).intValue();
		if(useratb.get("passwdexpirytime") != null)
	pwdage =  new Integer(useratb.get("passwdexpirytime").toString()).intValue();
	((com.adventnet.security.ui.AgeingPanel)CardPanel1.getCard("age")).setUserAttributes(userage, pwdage);
		
	}	
	
public void close()
	{
		//AuthMain.enableButtons();
 		dispose();  
	}	
	
	
public void setScreen(boolean bool)
	{
		if(bool)
		System.out.println(";kejhwflje");
		else
		System.out.println("u9284382");
	}
	
	
	//<Begin__class_backButton_backButton_conn1>

 class backButton_backButton_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_backButton_backButton_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  
     if(nextButton.getActionCommand().equals(resourceBundle.getString("Finish")))
   {
   nextButton.setText(resourceBundle.getString("Next"));
   nextButton.setActionCommand(resourceBundle.getString("Next")); 
   }  
  CardPanel1.showCard( CardPanel1.getPreviousCardName());   
  if(CardPanel1.getSelectedCardName().equals("user")) 
   {
    backButton.setEnabled(false);
   } 
  else
   {
    backButton.setEnabled(true);
   }  
     }
//<UserCode_End_Connection_backButton_backButton_conn1>
 }//<End__class_backButton_backButton_conn1>
//<Begin__class_nextButton_nextButton_conn1>

 class nextButton_nextButton_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_nextButton_nextButton_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
    addUser(arg0);
     }   
//<UserCode_End_Connection_nextButton_nextButton_conn1>
 }//<End__class_nextButton_nextButton_conn1>



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
		((com.adventnet.security.ui.AddUsrpanel)CardPanel1.getCard("user")).setFocus();
  }


//<Begin__class_cancelButton_cancelButton_conn1>

 class cancelButton_cancelButton_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_cancelButton_cancelButton_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
   
close();
     }
//<UserCode_End_Connection_cancelButton_cancelButton_conn1>
 }//<End__class_cancelButton_cancelButton_conn1>




  

  



	
  public AdUserWiz()
  {
    //<Begin_AdUserWiz>
    pack();
  
    //<End_AdUserWiz>
  }

		
  public AdUserWiz(java.applet.Applet applet)
  {
    //<Begin_AdUserWiz_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_AdUserWiz_java.applet.Applet>
  }
	
	
  public boolean isValid(String text)
  {
	int length = text.length();

	for(int i=0;i<length;i++)
	{
	  int asciis = text.charAt(i);
	  if(!(asciis>=33 && asciis<=126))
	  {
		return false;
	   }
	}	
	return true;
  }


		
	public void addUser(java.awt.event.ActionEvent arg0)
	{
		if(arg0.getActionCommand().equals(resourceBundle.getString("Finish")))
     		{
			nextButton.setEnabled(false);
      			if(((com.adventnet.security.ui.Group)CardPanel1.getCard("group")).getGroups().isEmpty())
       			{
				/*
          				if(((com.adventnet.security.ui.Group)CardPanel1.getCard("group")).getAddOperations().size() == 0) 
        				{   
            				Utilities.errorMessage(resourceBundle.getString("Select groups or permissions for the user"));      
            				return ;  
        				}
				*/
       			}
     			userGroupVec.add(3,((com.adventnet.security.ui.Group)CardPanel1.getCard("group")).getGroups());//NO I18N
   			userGroupVec.add(4,((com.adventnet.security.ui.Group)CardPanel1.getCard("group")).getAddOperations());//NO I18N
                        
   			userGroupVec.add(5,null);
                        
                        AuthMain.main.disableButtons();
                        for (int i=0;i<10;i++)
                        {
                            if(AuthMain.main.canAddUser)
                            {
                                break;
                            }
                            else 
                            {
                                try
                                {
                                    Thread.sleep(100);
                                }
                                catch(Exception e)
                                {}
                            }
                        }
                        
                        Vector userlist=AuthMain.model.getAllUsers();
                        AuthMain.main.canAddUser=false; 
                        
                        String userToBeAdded=(String)userGroupVec.firstElement();
                            
                            if(userlist.contains(userToBeAdded))
                            {
                                
                                String errorString=java.text.MessageFormat.format(resourceBundle.getString("javaclient.security.userexistmessage"),new String[]{userToBeAdded});
                                Utilities.errorMessage(errorString);
                                setVisible(false); 
                                
                                AuthMain.main.fireDataChanged();
                                return;
                            }
                        
                       
                        Vector grouplist = model.getGroups();
                                     
                    	if(grouplist.contains(userToBeAdded))
                    	{
                    		String errorString=java.text.MessageFormat.format(resourceBundle.getString("javaui.securityadmin.createuser.invalidName"),new String[]{userToBeAdded});
                    		Utilities.errorMessage(errorString);
                            setVisible(false); 
                            AuthMain.main.fireDataChanged();
                            return;
                    	
                    	}
                        
                        setUser();   //for adding new user..

                        
    			if(((com.adventnet.security.ui.AgeingPanel)CardPanel1.getCard("age")).isAgeSet())
      			{
       				Hashtable hash =  ((com.adventnet.security.ui.AgeingPanel)CardPanel1.getCard("age")).getUserAttributes();
      				Integer uage;
      				Integer page;
			         	if(hash.get("uage") == null)
      				{
        					uage = new Integer(0);   
     				}
     				else
     				{
        					uage =  new Integer(hash.get("uage").toString());  
     				}    
     				if(hash.get("pwage") == null)
     				{
        					page = new Integer(0);   
      				}
     				else
      				{
        					page = new Integer(hash.get("pwage").toString());
      				}

     				String str = ((com.adventnet.security.ui.AddUsrpanel)CardPanel1.getCard("user")).getUserName();
				String descName = ((com.adventnet.security.ui.AddUsrpanel)CardPanel1.getCard("user")).getDescName();//NO I18N
 				AuthMain.main.disableButtons();
      				AuthMain.model.setUserAttributes(str, null, uage , page ,descName);
      			} 
      			setVisible(false);
			
    			return;
 		}  
		if(arg0.getActionCommand().equals(resourceBundle.getString("Next"))) 
  		{  
  			if(CardPanel1.getSelectedCardName().equals("user"))
  			{
     				if(((com.adventnet.security.ui.AddUsrpanel)CardPanel1.getCard("user")).getUserName().equals(""))
     				{
      					Utilities.errorMessage(resourceBundle.getString("You must type a user name"));    
       					return;
    				}
     				if(((com.adventnet.security.ui.AddUsrpanel)CardPanel1.getCard("user")).getUserName().trim().length() > 50)
     				{
      					Utilities.errorMessage(resourceBundle.getString("User name exceeds 50 characters"));    
       					return;
    				}
 				String str = null;
				str = ((com.adventnet.security.ui.AddUsrpanel)CardPanel1.getCard("user")).getUserName();      
                                /*
                                  Vector uNames = AuthMain.model.getAllUserNames();
                                  if(uNames.contains(str))
                                  {
                                  Utilities.errorMessage(resourceBundle.getString("User name already exists"));//No I18N 
                                  ((com.adventnet.security.ui.AddUsrpanel)CardPanel1.getCard("user")).setFocus();//No I18N 
                                  
                                  return;
                                  }
                                */
 	
                if(! isValid(str))
                {
                    String message = NmsClientUtil.GetString("javaui.securityadmin.username.special.notallowed");//No I18N  
                    String title = NmsClientUtil.GetString("javaui.common.changepassword.errortitle");//No I18N  
                    JOptionPane.showMessageDialog(this,message,title,JOptionPane.ERROR_MESSAGE);           
                    return;
                }
  				 if(!(com.adventnet.security.AuthUtil.isValid(str)))
   					{
						//Utilities.errorMessage(resourceBundle.getString("Illegal character in user name \nCharacters '/', '\', ':' '*', '?', '<', '>', '|', '&' ")+"\n"+resourceBundle.getString("and (space) are not allowed"));
						Utilities.errorMessage(resourceBundle.getString("Illegal character in user name \nCharacters ' '/', '\', ':' '*', '?', '<', '>', '|', '&' \n and (space) are not allowed"));
   						return ; 
   					}
                                 AuthMain.model.fetchUserDetails();
   				    
      				userGroupVec.add(0,str);
                                //Desc Name
                                String descName = ((com.adventnet.security.ui.AddUsrpanel)CardPanel1.getCard("user")).getDescName();//NO I18N
                                userGroupVec.add(1,descName);
                                String passw = ((com.adventnet.security.ui.AddUsrpanel)CardPanel1.getCard("user")).getPassword();
                                for(int i=0;i<passw.length();i++)
                                {
                                        if(passw.charAt(i) == ' ')
                                        {
                                                Utilities.errorMessage(resourceBundle.getString("Illegal character in password space is not allowed"));
                                                return ;
                                        }
                                }
                                
                                if(((com.adventnet.security.ui.AddUsrpanel)CardPanel1.getCard("user")).getPassword().equals("error"))
     				{
        					Utilities.errorMessage(resourceBundle.getString("Both passwords must be same\nPlease retype the password"));    
        					return;
     				}
                                if(NmsClientUtil.getMinPasswordLength() !=-1 && passw.length() < NmsClientUtil.getMinPasswordLength())
                                {
                                    Object[] msgargs={new Integer(NmsClientUtil.getMinPasswordLength())};
                                    MessageFormat msgform=new MessageFormat(NmsClientUtil.GetString("javaui.common.changepassword.minlength.reached"));//No I18N
                                    String message = msgform.format(msgargs);
                                    String title = NmsClientUtil.GetString("javaui.common.changepassword.title");//No I18N
                                    JOptionPane.showMessageDialog(this,message,title,JOptionPane.ERROR_MESSAGE);
                                    return;
                                }
  				if(passw.equals(" "))
   				{
                                    userGroupVec.add(2, "null");//NO I18N   
  				}
				else
 				{
                                    //Custom Validation
                                    ValidateCredentials validator = NmsClientUtil.getValidator();
                                    if(validator!=null)
                                    {
					    java.util.Properties serverProps = new java.util.Properties();
					    serverProps = NmsClientUtil.appendServerProperties(serverProps);
					    boolean done =validator.validate(str,null,passw,serverProps);
                                        if(!done)
                                        {
                                            return;
                                        }
                                        
                                    }
                                    else
                    	            {
                    	                  if(!isValid(passw))
                    	                  {
                    	                	  String title = NmsClientUtil.GetString("javaui.common.changepassword.errortitle");//No I18N  
                    	                      JOptionPane.showMessageDialog(this,resourceBundle.getString("Illegal character in password"),title,JOptionPane.ERROR_MESSAGE); //No I18N           
                    	                      return ;
                    	                  }
                    	            }
                                    userGroupVec.add(2,passw);  
                                    //userGroupVec.add(1,((com.adventnet.security.ui.AddUsrpanel)CardPanel1.getCard("user")).getPassword());    
				}    
   
  			} 
			//System.out.println("Vector my vect "+userGroupVec);
    			backButton.setEnabled(true); 
 
           			CardPanel1.showCard(CardPanel1.getNextCardName()); 
    			if(CardPanel1.getSelectedCardName().equals("group"))
      			{
      				((com.adventnet.security.ui.Group)CardPanel1.getCard("group")).setSecurityModel(model); 

				((com.adventnet.security.ui.Group)CardPanel1.getCard("group")).setGroups(getAllGroups(),null); 

     				nextButton.setActionCommand(resourceBundle.getString("Finish"));  
     				nextButton.setText(resourceBundle.getString("Finish"));   
      			}
		}
	
			
	}
 

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}
