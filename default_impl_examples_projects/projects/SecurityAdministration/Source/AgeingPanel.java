

// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

//<Begin_Version>
//version$1
//<End_Version>
package com.adventnet.security.ui;




import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import  java.util.*;
import com.adventnet.nms.util.CommonBuilderUIInterface;




public class AgeingPanel extends JPanel 
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "SecurityAdministrationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JPanel JPanel4 = null;
	javax.swing.JLabel JLabel3 = null;
	com.adventnet.beans.text.NumericTextField NumericTextField1 = null;
	javax.swing.JLabel JLabel5 = null;
	javax.swing.JCheckBox JCheckBox1 = null;
	javax.swing.JPanel JPanel5 = null;
	javax.swing.JLabel JLabel4 = null;
	com.adventnet.beans.text.NumericTextField NumericTextField2 = null;
	javax.swing.JLabel JLabel6 = null;
	javax.swing.JCheckBox JCheckBox11 = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JLabel JLabel2 = null;
	javax.swing.JLabel JLabel1 = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>



  public AgeingPanel()
  {
    //<Begin_AgeingPanel>
    this.init();
  
    //<End_AgeingPanel>
  }

  public AgeingPanel(java.applet.Applet applet)
  {
    //<Begin_AgeingPanel_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_AgeingPanel_java.applet.Applet>
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
        setPreferredSize(new Dimension(getPreferredSize().width+502,getPreferredSize().height+367)); 
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

            JPanel4.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("User account expiry")));
            JPanel5.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("Password expiry")));
	JCheckBox1.setSelected(true);
	JCheckBox11.setSelected(true);

       JLabel1.setIcon(AuthMain.getBuilderUiIfInstance().getImage("addpassword.png"));
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
            JPanel4.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("User account expiry")));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel4,ex); 
          }

//<UserCode_Begin_Bean_JPanel4>

//<UserCode_End_Bean_JPanel4>

          try
          {
            JLabel3.setHorizontalAlignment(2);
            JLabel3.setFont(new Font("SansSerif",0,12));
            JLabel3.setHorizontalTextPosition(4);
            JLabel3.setText(resourceBundle.getString("This user account expires in"));
            JLabel3.setForeground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel3,ex); 
          }

//<UserCode_Begin_Bean_JLabel3>

//<UserCode_End_Bean_JLabel3>

          try
          {
            NumericTextField1.setEditable(true);
            NumericTextField1.setEnabled(false);
            NumericTextField1.setMinValue(-2147483648);
            NumericTextField1.setMaxValue(2147483647);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+NumericTextField1,ex); 
          }

//<UserCode_Begin_Bean_NumericTextField1>
NumericTextField1.setMinValue(0);
//<UserCode_End_Bean_NumericTextField1>

          try
          {
            JLabel5.setHorizontalAlignment(2);
            JLabel5.setFont(new Font("SansSerif",0,12));
            JLabel5.setHorizontalTextPosition(4);
            JLabel5.setText(resourceBundle.getString("Day(s)."));
            JLabel5.setForeground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel5,ex); 
          }

//<UserCode_Begin_Bean_JLabel5>

//<UserCode_End_Bean_JLabel5>

          try
          {
            JCheckBox1.setFont(new Font("SansSerif",0,12));
            JCheckBox1.setHorizontalTextPosition(4);
            JCheckBox1.setText(resourceBundle.getString("Account never expires"));
            JCheckBox1.setSelected(true);
            JCheckBox1.setForeground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JCheckBox1,ex); 
          }

//<UserCode_Begin_Bean_JCheckBox1>

//<UserCode_End_Bean_JCheckBox1>

          try
          {
            JPanel5.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("Password expiry")));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel5,ex); 
          }

//<UserCode_Begin_Bean_JPanel5>

//<UserCode_End_Bean_JPanel5>

          try
          {
            JLabel4.setHorizontalAlignment(2);
            JLabel4.setFont(new Font("SansSerif",0,12));
            JLabel4.setHorizontalTextPosition(4);
            JLabel4.setText(resourceBundle.getString("The password expires in        "));
            JLabel4.setForeground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel4,ex); 
          }

//<UserCode_Begin_Bean_JLabel4>

//<UserCode_End_Bean_JLabel4>

          try
          {
            NumericTextField2.setEnabled(false);
            NumericTextField2.setMinValue(-2147483648);
            NumericTextField2.setMaxValue(2147483647);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+NumericTextField2,ex); 
          }

//<UserCode_Begin_Bean_NumericTextField2>
NumericTextField2.setMinValue(0);
//<UserCode_End_Bean_NumericTextField2>

          try
          {
            JLabel6.setHorizontalAlignment(2);
            JLabel6.setFont(new Font("SansSerif",0,12));
            JLabel6.setHorizontalTextPosition(4);
            JLabel6.setText(resourceBundle.getString("Days(s)."));
            JLabel6.setForeground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel6,ex); 
          }

//<UserCode_Begin_Bean_JLabel6>

//<UserCode_End_Bean_JLabel6>

          try
          {
            JCheckBox11.setFont(new Font("SansSerif",0,12));
            JCheckBox11.setHorizontalTextPosition(4);
            JCheckBox11.setText(resourceBundle.getString("Password never expires"));
            JCheckBox11.setForeground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JCheckBox11,ex); 
          }

//<UserCode_Begin_Bean_JCheckBox11>

//<UserCode_End_Bean_JCheckBox11>

          try
          {
            JLabel2.setHorizontalAlignment(2);
            JLabel2.setFont(new Font("SansSerif",0,12));
            JLabel2.setHorizontalTextPosition(4);
            JLabel2.setForeground(new Color(-16764109));
            JLabel2.setText(resourceBundle.getString("<html><body><font face=\"sansserif,SansSerif\"><font size=3>   Please enter the number of days in which  <br><font face=\"sansserif,SansSerif\">    the user and/or the password expires...</font><br><br><font face=\"san sserif,SansSerif\">   A value of zero indicates no expiry. </font></body></html>"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel2,ex); 
          }

//<UserCode_Begin_Bean_JLabel2>

//<UserCode_End_Bean_JLabel2>
		JLabel2.setPreferredSize(new Dimension(JLabel2.getPreferredSize().width+613,JLabel2.getPreferredSize().height+0));
		JPanel3.setPreferredSize(new Dimension(JPanel3.getPreferredSize().width+10,JPanel3.getPreferredSize().height+10));
		JPanel2.setPreferredSize(new Dimension(JPanel2.getPreferredSize().width+10,JPanel2.getPreferredSize().height+10));

  
          //<End_setUpProperties>
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
        JPanel2= new javax.swing.JPanel();
        JPanel4= new javax.swing.JPanel();
        JLabel3= new javax.swing.JLabel();
        NumericTextField1= new com.adventnet.beans.text.NumericTextField();
        JLabel5= new javax.swing.JLabel();
        JCheckBox1= new javax.swing.JCheckBox();
        JPanel5= new javax.swing.JPanel();
        JLabel4= new javax.swing.JLabel();
        NumericTextField2= new com.adventnet.beans.text.NumericTextField();
        JLabel6= new javax.swing.JLabel();
        JCheckBox11= new javax.swing.JCheckBox();
        JPanel3= new javax.swing.JPanel();
        JLabel2= new javax.swing.JLabel();
        JLabel1= new javax.swing.JLabel();

  
        //<End_initVariables>
  } 
  public void setUpGUI(Container container)
  {
//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(1,0,1,1,0.8,0.1,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(JPanel1,cons);
JPanel1.setLayout(new BorderLayout(5,5));
JPanel1.add(JPanel2,BorderLayout.CENTER);
JPanel2.setLayout(new GridLayout(2,1,5,5));
JPanel2.add(JPanel4);
JPanel4.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.1,0.1,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel4.add(JLabel3,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,1,1,1,0.1,0.1,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel4.add(NumericTextField1,cons);
inset = new Insets(5,5,5,5);
setConstraints(2,1,1,1,0.1,0.1,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel4.add(JLabel5,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel4.add(JCheckBox1,cons);
JPanel2.add(JPanel5);
JPanel5.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.1,0.1,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel5.add(JLabel4,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,1,1,1,0.1,0.1,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel5.add(NumericTextField2,cons);
inset = new Insets(5,5,5,5);
setConstraints(2,1,1,1,0.1,0.1,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel5.add(JLabel6,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel5.add(JCheckBox11,cons);
JPanel1.add(JPanel3,BorderLayout.SOUTH);
JPanel3.setLayout(new BorderLayout(5,5));
JPanel3.add(JLabel2,BorderLayout.CENTER);
inset = new Insets(0,0,0,0);
setConstraints(0,0,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(JLabel1,cons);

  
//<End_setUpGUI_Container>
  } 
  public void setUpConnections()
  {
  //<Begin_setUpConnections> 

      JCheckBox11_NumericTextField2_conn1 JCheckBox11_NumericTextField2_conn11 =  new JCheckBox11_NumericTextField2_conn1();
      JCheckBox11.addItemListener(JCheckBox11_NumericTextField2_conn11);
      JCheckBox1_NumericTextField1_conn1 JCheckBox1_NumericTextField1_conn11 =  new JCheckBox1_NumericTextField1_conn1();
      JCheckBox1.addItemListener(JCheckBox1_NumericTextField1_conn11);
  
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

 


//<Begin__class_JCheckBox11_NumericTextField2_conn1>

 class JCheckBox11_NumericTextField2_conn1 implements java.awt.event.ItemListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JCheckBox11_NumericTextField2_conn1>



     //Listener Interface Methods Are Added Below 


     public void itemStateChanged( java.awt.event.ItemEvent arg0)
     {
        if( JCheckBox11.isSelected())
   {
         NumericTextField2.setEnabled(false);    
          NumericTextField2.setText("0");      
  } 
     else
   {
         NumericTextField2.setEnabled(true);        
  } 
     }
//<UserCode_End_Connection_JCheckBox11_NumericTextField2_conn1>
 }//<End__class_JCheckBox11_NumericTextField2_conn1>
//<Begin__class_JCheckBox1_NumericTextField1_conn1>

 class JCheckBox1_NumericTextField1_conn1 implements java.awt.event.ItemListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JCheckBox1_NumericTextField1_conn1>



     //Listener Interface Methods Are Added Below 


     public void itemStateChanged( java.awt.event.ItemEvent arg0)
     {
        if( JCheckBox1.isSelected())
   {
         NumericTextField1.setEnabled(false); 
         NumericTextField1.setText("0");   
  } 
     else
   {
         NumericTextField1.setEnabled(true);        
  } 
     }
//<UserCode_End_Connection_JCheckBox1_NumericTextField1_conn1>
 }//<End__class_JCheckBox1_NumericTextField1_conn1>
	

 public void setUserAttributes(int userage, int pwdage)
	{
		
 		
			NumericTextField1.setText(userage+"");
			if(userage != 0 )
			JCheckBox1.setSelected(false);
 		
 		
 		
			NumericTextField2.setText(pwdage+"");
			if(pwdage != 0){
			//System.out.println("lkhjefk");
			JCheckBox11.setSelected(false);
			}
 		
	}

  public boolean isAgeSet()
	{
		boolean ageSet = false;		
		if(!JCheckBox1.isSelected()  ||  !JCheckBox11.isSelected())
			{
				ageSet = true;
			}
 		return ageSet;	
  	}

  public Hashtable getUserAttributes()
	{
		Hashtable hash = new Hashtable();

		if(NumericTextField1.getText().equals("0"))
		{
			//hash.put("uage","null");
		}
		else
		{
			hash.put("uage",NumericTextField1.getText());
		}
		if(NumericTextField2.getText().equals("0"))
		{
		//hash.put("pwage","null");
		}
		else
		{
			hash.put("pwage",NumericTextField2.getText());
		}
		
		return hash;
	}


 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}




