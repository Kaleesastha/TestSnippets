

// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.runtimeconfig;



//$Id: MapIconAddModify.java,v 1.2.6.1 2012/01/25 05:12:46 karen.r Exp $




import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class MapIconAddModify extends JDialog 
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "RuntimeAdministrationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel1 = null;
	com.adventnet.beans.panels.CardPanel cardPanel = null;
	javax.swing.JLabel JLabel2 = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JButton backButton = null;
	javax.swing.JButton nextButton = null;
	javax.swing.JButton cancelButton = null;
	javax.swing.JPanel JPanel8 = null;
	javax.swing.JLabel JLabel3 = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>
	MapIconUI mapIconUI = null;
	Hashtable cloneHash = null;
  	boolean addFlag = true;
	Hashtable rootHash = null;

 	public MapIconAddModify(MapIconUI mainUI,boolean flag,java.applet.Applet applet)
	{
		this.applet = applet;
		mapIconUI = mainUI;
		addFlag = flag;
	}
   


  public MapIconAddModify()
  {
    //<Begin_MapIconAddModify>
    pack();
  
    //<End_MapIconAddModify>
  }

  public MapIconAddModify(java.applet.Applet applet)
  {
    //<Begin_MapIconAddModify_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_MapIconAddModify_java.applet.Applet>
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

  
    public void setUpProperties()
  { 

  //<Begin_setUpProperties> 

          try
          {
            JPanel1.setBorder(new javax.swing.border.EtchedBorder(0));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel1,ex); 
          }

//<UserCode_Begin_Bean_JPanel1>

//<UserCode_End_Bean_JPanel1>

          try
          {
            java.lang.String[]  cardPanelcardAndClassNames_array = new java.lang.String[ 2 ]; 
            cardPanelcardAndClassNames_array[ 0 ] = resourceBundle.getString("card1=com.adventnet.nms.runtimeconfig.AddModifyMainPanel");
            cardPanelcardAndClassNames_array[ 1 ] = resourceBundle.getString("card2=com.adventnet.nms.runtimeconfig.UserPropertyPanel");
            cardPanel.setCardAndClassNames(cardPanelcardAndClassNames_array);
            cardPanel.setBorder(new javax.swing.border.BevelBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+cardPanel,ex); 
          }

//<UserCode_Begin_Bean_cardPanel>

//<UserCode_End_Bean_cardPanel>

          try
          {
            JLabel2.setBorder(new javax.swing.border.BevelBorder(0));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel2,ex); 
          }

//<UserCode_Begin_Bean_JLabel2>

//<UserCode_End_Bean_JLabel2>

          try
          {
            JPanel3.setBorder(new javax.swing.border.EtchedBorder(0));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel3,ex); 
          }

//<UserCode_Begin_Bean_JPanel3>

//<UserCode_End_Bean_JPanel3>

          try
          {
            backButton.setText(resourceBundle.getString("Back"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+backButton,ex); 
          }

//<UserCode_Begin_Bean_backButton>

//<UserCode_End_Bean_backButton>

          try
          {
            nextButton.setText(resourceBundle.getString("Next"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+nextButton,ex); 
          }

//<UserCode_Begin_Bean_nextButton>

//<UserCode_End_Bean_nextButton>

          try
          {
            cancelButton.setText(resourceBundle.getString("Cancel"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+cancelButton,ex); 
          }

//<UserCode_Begin_Bean_cancelButton>

//<UserCode_End_Bean_cancelButton>

          try
          {
            JPanel8.setBorder(new javax.swing.border.EtchedBorder(0));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel8,ex); 
          }

//<UserCode_Begin_Bean_JPanel8>

//<UserCode_End_Bean_JPanel8>

          try
          {
            JLabel3.setText(resourceBundle.getString("Map UI settings"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel3,ex); 
          }

//<UserCode_Begin_Bean_JLabel3>

//<UserCode_End_Bean_JLabel3>
		cancelButton.setPreferredSize(new Dimension(cancelButton.getPreferredSize().width+0,cancelButton.getPreferredSize().height+4));
		nextButton.setPreferredSize(new Dimension(nextButton.getPreferredSize().width+6,nextButton.getPreferredSize().height+4));
		backButton.setPreferredSize(new Dimension(backButton.getPreferredSize().width+8,backButton.getPreferredSize().height+4));
		JLabel2.setPreferredSize(new Dimension(JLabel2.getPreferredSize().width+168,JLabel2.getPreferredSize().height+388));

  
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
        this.setSize(getPreferredSize().width+593,getPreferredSize().height+487); 
          setTitle(resourceBundle.getString("MapIconAddModify"));
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
	//JLabel3.setIcon(RuntimeConfigFrame.getCommonBuilderUIImpl().getImage("tips.png","images/runtimeadmin"));	
          setTitle(resourceBundle.getString("Map User Interface Settings"));
	cardPanel.showCard("card1");
	backButton.setEnabled(false);
	RuntimeConfigFrame.getCommonBuilderUIImpl().centerWindow(this);
	JLabel2.setIcon(RuntimeConfigFrame.getCommonBuilderUIImpl().getImage("mapicon.png","images/runtimeadmin"));
	//JTextArea1.setText("Help Text");
	//JTextArea1.setEditable(false);
	backButton.setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());
	nextButton.setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());
	cancelButton.setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());
	setModal(true);
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
        cardPanel= new com.adventnet.beans.panels.CardPanel(applet);
        JLabel2= new javax.swing.JLabel();
        JPanel3= new javax.swing.JPanel();
        backButton= new javax.swing.JButton();
        nextButton= new javax.swing.JButton();
        cancelButton= new javax.swing.JButton();
        JPanel8= new javax.swing.JPanel();
        JLabel3= new javax.swing.JLabel();

  
        //<End_initVariables>
  } 
   
  public void setUpGUI(Container container)
  { 

  //<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(JPanel1,cons);
JPanel1.setLayout(new BorderLayout(5,5));
JPanel1.add(cardPanel,BorderLayout.CENTER);
JPanel1.add(JLabel2,BorderLayout.WEST);
inset = new Insets(5,5,5,5);
setConstraints(0,4,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel3,cons);
JPanel3.setLayout(new FlowLayout(2,5,5));
JPanel3.add(backButton);
JPanel3.add(nextButton);
JPanel3.add(cancelButton);
inset = new Insets(5,5,5,0);
setConstraints(0,1,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel8,cons);
JPanel8.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel8.add(JLabel3,cons);

  
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
            if (input.equals("HOST")) value = "localhost"; 
            if (input.equals("RESOURCE_PROPERTIES")) value = "RuntimeAdministrationResources"; 
            if (input.equals("PORT")) value = "161"; 
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

	

 

	
	public void nextButtonActionPerformed()
	{
		if(nextButton.getText().equalsIgnoreCase(resourceBundle.getString("Finish")))
		{
			mapIconUI.updateEntries(cloneHash);
			this.setVisible(false);
			this.dispose();
		}
		else
		{
			Component comp1 = cardPanel.getSelectedCard();
			Hashtable temp = ((AddModifyMainPanel)comp1).getAllValues();
			String type = temp.get("TYPE").toString().trim();
			if(type.equals(""))
			{
				JOptionPane.showMessageDialog(null,resourceBundle.getString("Please specify a type"),resourceBundle.getString("Message"),JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			else
			{
				if(rootHash != null)
				{
					for(int i=0;i<rootHash.size();i++)	
					{
						Enumeration enumerate = rootHash.keys();
						while(enumerate.hasMoreElements())
						{
							String str = enumerate.nextElement().toString();
							if(str.equalsIgnoreCase(type))
							{
								JOptionPane.showMessageDialog(null,resourceBundle.getString("Type already exists"),resourceBundle.getString("Message"),JOptionPane.INFORMATION_MESSAGE);
								return;
							}
						}
					}
				}
				if(cloneHash == null)
				{
					cloneHash = new Hashtable();
				}
				cloneHash.put("TYPE",type);
				cloneHash.put("menuName",temp.get("menuName"));
				cloneHash.put("iconName",temp.get("iconName"));
				cloneHash.put("MAP_FILTER",temp.get("MAP_FILTER"));
			}
			cardPanel.showCard("card2");
			Component comp = cardPanel.getSelectedCard();
			if(addFlag)
				((UserPropertyPanel)comp).setValues(cloneHash); 
			else
				((UserPropertyPanel)comp).setValues(cloneHash); 
			nextButton.setText(resourceBundle.getString("Finish"));
			backButton.setEnabled(true);
		}
	}

 

	
	public void backButtonActionPerformed()
	{
		
		cardPanel.showCard("card1");
		nextButton.setText(resourceBundle.getString("Next"));
		nextButton.setVisible(true);
		backButton.setEnabled(false);
	}

 
//<Begin__class_nextButton_nextButton_conn1>

 class nextButton_nextButton_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_nextButton_nextButton_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  nextButtonActionPerformed();
     }
//<UserCode_End_Connection_nextButton_nextButton_conn1>
 }//<End__class_nextButton_nextButton_conn1>

 
//<Begin__class_backButton_backButton_conn1>

 class backButton_backButton_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_backButton_backButton_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  backButtonActionPerformed();
     }
//<UserCode_End_Connection_backButton_backButton_conn1>
 }//<End__class_backButton_backButton_conn1>
	
	public void populateTheUI(Hashtable mainHash)
	{
		cloneHash = mainHash;
		Component comp = cardPanel.getSelectedCard();
		((AddModifyMainPanel)comp).setValues(cloneHash,addFlag); 
	}


//<Begin__class_cancelButton_cancelButton_conn1>

 class cancelButton_cancelButton_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_cancelButton_cancelButton_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  cancelButtonActionPerformed();
     }
//<UserCode_End_Connection_cancelButton_cancelButton_conn1>
 }//<End__class_cancelButton_cancelButton_conn1>
	
	public void cancelButtonActionPerformed()
	{
		this.setVisible(false);
		this.dispose();
	}

	public void addRequest(Hashtable hash)
	{
		rootHash = hash;
	}

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}










