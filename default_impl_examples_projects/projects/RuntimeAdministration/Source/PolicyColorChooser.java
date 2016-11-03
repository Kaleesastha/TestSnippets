

// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.runtimeconfig;





//$Id: PolicyColorChooser.java,v 1.1.6.1 2012/01/25 05:12:46 karen.r Exp $



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Hashtable;
import java.util.Vector;
import com.adventnet.nms.util.CommonBuilderUIInterface;

public class PolicyColorChooser extends JDialog 
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "RuntimeAdministrationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JPanel JPanel5 = null;
	javax.swing.JLabel JLabel2 = null;
	javax.swing.JButton JButton1 = null;
	javax.swing.JPanel JPanel6 = null;
	javax.swing.JLabel JLabel3 = null;
	javax.swing.JButton JButton2 = null;
	javax.swing.JPanel JPanel7 = null;
	javax.swing.JLabel JLabel4 = null;
	javax.swing.JButton JButton3 = null;
	javax.swing.JPanel JPanel8 = null;
	javax.swing.JLabel JLabel5 = null;
	javax.swing.JButton JButton4 = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JButton JButton5 = null;
	javax.swing.JButton JButton6 = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>
	
	PolicyConfigurationUI mainui = null;
	Color disColor = null;
	Color stopColor = null;
	Color execColor = null;
	Color schedColor = null;
  	Color disnew = null;
	Color stopnew = null;
	Color execnew = null;
	Color schednew = null;
	CommonBuilderUIInterface uiUtils = null;
	String disabled = null;
	String stopped = null;
	String exec = null;
	String scheduled = null;









	public void setButtonBackground(Hashtable colorHash)
	{
		disabled =(String)colorHash.get("DISABLED");
		setRGB(disabled,1);
		exec =(String)colorHash.get("EXECUTED");
		setRGB(exec,3);
		stopped =(String) colorHash.get("STOPPED");
		setRGB(stopped,2);
		scheduled =(String) colorHash.get("SCHEDULED"); 
		setRGB(scheduled,4);
	}

	public void setRGB(String str,int button)
	{
		int i = str.indexOf("-");
		String red = str.substring(0,i);
		int r= Integer.parseInt(red);
		int j = str.indexOf("-",i+1);
		String green = str.substring(i+1,j);
		int g= Integer.parseInt(green);
		String blue = str.substring(j+1);
		int b= Integer.parseInt(blue);
		if(button == 1)
		{
			disColor = new Color(r,g,b);
			JButton1.setBackground(disColor);
		}
		else if(button == 2)
		{
			stopColor = new Color(r,g,b);
			JButton2.setBackground(stopColor);
		}
		else if(button == 3)
		{
			execColor = new Color(r,g,b);
			JButton3.setBackground(execColor);		
		}
		else
		{
			schedColor = new Color(r,g,b);
			JButton4.setBackground(schedColor);
		}

	}
   


	public PolicyColorChooser(PolicyConfigurationUI policyui , java.applet.Applet applet)
	{
  		this(applet);
  		initializeVariables(policyui);
	}
	
	public PolicyColorChooser(PolicyConfigurationUI policyui)
	{
  		this();
  		initializeVariables(policyui);
	}
 
  private void initializeVariables(PolicyConfigurationUI policyui)
  {
	  uiUtils = RuntimeConfigFrame.getCommonBuilderUIImpl();
	  mainui = policyui;
  }
  public PolicyColorChooser()
  {
			
    //<Begin_PolicyColorChooser>
    pack();
  
    //<End_PolicyColorChooser>
  }

  public PolicyColorChooser(java.applet.Applet applet)
  {
    //<Begin_PolicyColorChooser_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_PolicyColorChooser_java.applet.Applet>
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
            JLabel1.setText(resourceBundle.getString("Policy Color Configuration"));
            JLabel1.setForeground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel1,ex); 
          }

//<UserCode_Begin_Bean_JLabel1>

//<UserCode_End_Bean_JLabel1>

          try
          {
            JPanel2.setBorder(new javax.swing.border.EtchedBorder(0));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel2,ex); 
          }

//<UserCode_Begin_Bean_JPanel2>

//<UserCode_End_Bean_JPanel2>

          try
          {
            JLabel2.setText(resourceBundle.getString("Color of the Disabled Policy"));
            JLabel2.setHorizontalTextPosition(10);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel2,ex); 
          }

//<UserCode_Begin_Bean_JLabel2>

//<UserCode_End_Bean_JLabel2>

          try
          {
            JButton1.setPreferredSize(new Dimension(100,30));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton1,ex); 
          }

//<UserCode_Begin_Bean_JButton1>

//<UserCode_End_Bean_JButton1>

          try
          {
            JLabel3.setText(resourceBundle.getString("Color of the Stopped Policy"));
            JLabel3.setHorizontalTextPosition(10);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel3,ex); 
          }

//<UserCode_Begin_Bean_JLabel3>

//<UserCode_End_Bean_JLabel3>

          try
          {
            JButton2.setPreferredSize(new Dimension(100,30));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton2,ex); 
          }

//<UserCode_Begin_Bean_JButton2>

//<UserCode_End_Bean_JButton2>

          try
          {
            JLabel4.setHorizontalTextPosition(10);
            JLabel4.setText(resourceBundle.getString("Color of the Executed Policy"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel4,ex); 
          }

//<UserCode_Begin_Bean_JLabel4>

//<UserCode_End_Bean_JLabel4>

          try
          {
            JButton3.setPreferredSize(new Dimension(100,30));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton3,ex); 
          }

//<UserCode_Begin_Bean_JButton3>

//<UserCode_End_Bean_JButton3>

          try
          {
            JLabel5.setText(resourceBundle.getString("Color of the Scheduled Policy"));
            JLabel5.setHorizontalTextPosition(10);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel5,ex); 
          }

//<UserCode_Begin_Bean_JLabel5>

//<UserCode_End_Bean_JLabel5>

          try
          {
            JButton4.setPreferredSize(new Dimension(100,30));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton4,ex); 
          }

//<UserCode_Begin_Bean_JButton4>

//<UserCode_End_Bean_JButton4>

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
            JButton5.setText(resourceBundle.getString("OK"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton5,ex); 
          }

//<UserCode_Begin_Bean_JButton5>

//<UserCode_End_Bean_JButton5>

          try
          {
            JButton6.setText(resourceBundle.getString("Cancel"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton6,ex); 
          }

//<UserCode_Begin_Bean_JButton6>

//<UserCode_End_Bean_JButton6>
		JButton6.setPreferredSize(new Dimension(JButton6.getPreferredSize().width+8,JButton6.getPreferredSize().height+0));
		JButton5.setPreferredSize(new Dimension(JButton5.getPreferredSize().width+22,JButton5.getPreferredSize().height+0));
		JLabel1.setPreferredSize(new Dimension(JLabel1.getPreferredSize().width+43,JLabel1.getPreferredSize().height+0));

  
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
        this.setSize(getPreferredSize().width+473,getPreferredSize().height+391); 
          setTitle(resourceBundle.getString("PolicyColorChooser"));
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
JButton5.setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());
	JButton6.setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());
uiUtils.centerWindow(this);	
        setTitle(resourceBundle.getString("Policy Color Chooser"));
	setModal(true);
  } 
  public void setUpConnections()
  { 

  //<Begin_setUpConnections> 

      JButton5_JButton5_conn1 JButton5_JButton5_conn11 =  new JButton5_JButton5_conn1();
      JButton5.addActionListener(JButton5_JButton5_conn11);
      JButton2_JButton2_conn1 JButton2_JButton2_conn11 =  new JButton2_JButton2_conn1();
      JButton2.addActionListener(JButton2_JButton2_conn11);
      JButton3_JButton3_conn1 JButton3_JButton3_conn11 =  new JButton3_JButton3_conn1();
      JButton3.addActionListener(JButton3_JButton3_conn11);
      JButton4_JButton4_conn1 JButton4_JButton4_conn11 =  new JButton4_JButton4_conn1();
      JButton4.addActionListener(JButton4_JButton4_conn11);
      JButton1_JButton1_conn1 JButton1_JButton1_conn11 =  new JButton1_JButton1_conn1();
      JButton1.addActionListener(JButton1_JButton1_conn11);
      JButton6_JButton6_conn1 JButton6_JButton6_conn11 =  new JButton6_JButton6_conn1();
      JButton6.addActionListener(JButton6_JButton6_conn11);
  
      //<End_setUpConnections>
  } 
  public void initVariables()
  { 

  //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        JPanel1= new javax.swing.JPanel();
        JLabel1= new javax.swing.JLabel();
        JPanel2= new javax.swing.JPanel();
        JPanel5= new javax.swing.JPanel();
        JLabel2= new javax.swing.JLabel();
        JButton1= new javax.swing.JButton();
        JPanel6= new javax.swing.JPanel();
        JLabel3= new javax.swing.JLabel();
        JButton2= new javax.swing.JButton();
        JPanel7= new javax.swing.JPanel();
        JLabel4= new javax.swing.JLabel();
        JButton3= new javax.swing.JButton();
        JPanel8= new javax.swing.JPanel();
        JLabel5= new javax.swing.JLabel();
        JButton4= new javax.swing.JButton();
        JPanel3= new javax.swing.JPanel();
        JButton5= new javax.swing.JButton();
        JButton6= new javax.swing.JButton();

  
        //<End_initVariables>
  } 
   
  public void setUpGUI(Container container)
  { 

  //<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new GridBagLayout());
inset = new Insets(10,10,10,10);
setConstraints(0,0,1,1,0.0,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel1,cons);
JPanel1.setLayout(new FlowLayout(1,5,5));
JPanel1.add(JLabel1);
inset = new Insets(10,10,10,10);
setConstraints(0,1,1,1,0.1,0.1,cons.NORTHWEST,cons.BOTH,inset,0,0);
Top.add(JPanel2,cons);
JPanel2.setLayout(new GridBagLayout());
inset = new Insets(10,0,0,0);
setConstraints(0,0,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel2.add(JPanel5,cons);
JPanel5.setLayout(new GridBagLayout());
inset = new Insets(10,10,10,0);
setConstraints(0,0,1,1,0.5,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel5.add(JLabel2,cons);
inset = new Insets(10,0,10,40);
setConstraints(1,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,20,0);
JPanel5.add(JButton1,cons);
inset = new Insets(0,0,0,0);
setConstraints(0,1,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel2.add(JPanel6,cons);
JPanel6.setLayout(new GridBagLayout());
inset = new Insets(10,10,10,0);
setConstraints(0,0,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel6.add(JLabel3,cons);
inset = new Insets(10,0,10,40);
setConstraints(1,0,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,20,0);
JPanel6.add(JButton2,cons);
inset = new Insets(0,0,0,0);
setConstraints(0,2,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel2.add(JPanel7,cons);
JPanel7.setLayout(new GridBagLayout());
inset = new Insets(10,10,10,0);
setConstraints(0,0,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel7.add(JLabel4,cons);
inset = new Insets(10,0,10,40);
setConstraints(1,0,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,20,0);
JPanel7.add(JButton3,cons);
inset = new Insets(0,0,10,0);
setConstraints(0,3,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel2.add(JPanel8,cons);
JPanel8.setLayout(new GridBagLayout());
inset = new Insets(10,10,10,0);
setConstraints(0,0,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel8.add(JLabel5,cons);
inset = new Insets(10,0,10,40);
setConstraints(1,0,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,20,0);
JPanel8.add(JButton4,cons);
inset = new Insets(10,10,10,10);
setConstraints(0,2,1,1,0.0,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel3,cons);
JPanel3.setLayout(new FlowLayout(1,5,5));
JPanel3.add(JButton5);
JPanel3.add(JButton6);

  
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


 
//<Begin__class_JButton1_JButton1_conn1>

 class JButton1_JButton1_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton1_JButton1_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
 disnew = JColorChooser.showDialog(null,"Policy Color Chooser",disColor);
 if (disnew != null)
 {
  JButton1.setBackground(disnew);
 }  
     }
//<UserCode_End_Connection_JButton1_JButton1_conn1>
 }//<End__class_JButton1_JButton1_conn1>

 
//<Begin__class_JButton2_JButton2_conn1>

 class JButton2_JButton2_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton2_JButton2_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
 stopnew = JColorChooser.showDialog(null,"Policy Color Chooser",disColor);
 if (stopnew != null)
 {
  JButton2.setBackground(stopnew);
 }  
     }
//<UserCode_End_Connection_JButton2_JButton2_conn1>
 }//<End__class_JButton2_JButton2_conn1>
//<Begin__class_JButton3_JButton3_conn1>

 class JButton3_JButton3_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton3_JButton3_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
 execnew= JColorChooser.showDialog(null,"Policy Color Chooser",disColor);
 if (execnew != null)
 {
  JButton3.setBackground(execnew);
 }  
     }
//<UserCode_End_Connection_JButton3_JButton3_conn1>
 }//<End__class_JButton3_JButton3_conn1>
//<Begin__class_JButton4_JButton4_conn1>

 class JButton4_JButton4_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton4_JButton4_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
 schednew = JColorChooser.showDialog(null,"Policy Color Chooser",disColor);
 if (schednew != null)
 {
  JButton4.setBackground(schednew);
 }  
     }
//<UserCode_End_Connection_JButton4_JButton4_conn1>
 }//<End__class_JButton4_JButton4_conn1>

 
//<Begin__class_JButton5_JButton5_conn1>

 class JButton5_JButton5_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton5_JButton5_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
 String newDisColor = null;
 String newStopColor = null;
 String newExecColor = null;
 String newSchedColor = null; 
 if (disnew != null)
 { 
  String red = disnew.getRed()+"";
  String blue = disnew.getBlue()+"";
  String green = disnew.getGreen()+""; 
  newDisColor = red+"-"+green+"-"+blue;
 }
 else
 {
  newDisColor  = disabled;
 }  
 if(stopnew != null)
 {
  String red = stopnew.getRed()+"";
  String blue = stopnew.getBlue()+"";
  String green = stopnew.getGreen()+"";
  newStopColor = red+"-"+green+"-"+blue;
 }
 else
 {
  newStopColor = stopped;
 }  
 if (execnew != null) 
 {
  String red = execnew.getRed()+"";
  String blue = execnew.getBlue()+"";
  String green = execnew.getGreen()+"";
  newExecColor = red+"-"+green+"-"+blue; 
 }
 else
 {
  newExecColor = exec; 
 }  
 if(schednew != null)
 {
  String red = schednew.getRed()+"";
  String blue = schednew.getBlue()+"";
  String green = schednew.getGreen()+"";
  newSchedColor= red+"-"+green+"-"+blue; 
 }  
 else
 {
  newSchedColor = scheduled;
 }  
 Hashtable colorHash = new Hashtable();
 colorHash.put("DISABLED",newDisColor);
 colorHash.put("STOPPED",newStopColor);
 colorHash.put("SCHEDULED",newSchedColor);
 colorHash.put("EXECUTED",newExecColor); 
 mainui.newPolicyColor(colorHash);
 PolicyColorChooser.this.dispose(); 
     }
//<UserCode_End_Connection_JButton5_JButton5_conn1>
 }//<End__class_JButton5_JButton5_conn1>


//<Begin__class_JButton6_JButton6_conn1>

 class JButton6_JButton6_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton6_JButton6_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
 PolicyColorChooser.this.dispose(); 
     }
//<UserCode_End_Connection_JButton6_JButton6_conn1>
 }//<End__class_JButton6_JButton6_conn1>

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}















