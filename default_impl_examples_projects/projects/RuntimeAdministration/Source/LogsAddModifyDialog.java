

// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.runtimeconfig;




//$Id: LogsAddModifyDialog.java,v 1.1 2006/08/29 13:57:02 build Exp $



import com.adventnet.nms.util.CommonBuilderUIInterface;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class LogsAddModifyDialog extends JDialog 
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
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JButton okButton = null;
	javax.swing.JButton cancelButton = null;
	javax.swing.JPanel JPanel5 = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JLabel JLabel2 = null;
	javax.swing.JTextField fileName = null;
	javax.swing.JLabel JLabel3 = null;
	javax.swing.JTextField directory = null;
	javax.swing.JLabel JLabel4 = null;
	javax.swing.JTextField maxLines = null;
	javax.swing.JLabel JLabel5 = null;
	javax.swing.JTextField fileCount = null;
	javax.swing.JLabel JLabel7 = null;
	javax.swing.JCheckBox timeStampCheck = null;
	javax.swing.JLabel JLabel8 = null;
	javax.swing.JCheckBox loggingCheck = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>

	Hashtable keyNodeHashtable = null;
	boolean modifyFlag = true;  
	boolean addFlag = true;  
	LoggingMainUI mainUI = null;  
	CommonBuilderUIInterface uiUtils = null;

















	 public LogsAddModifyDialog(LoggingMainUI ui,java.applet.Applet applet)	
	{
		this.applet = applet;
		mainUI = ui;
		commonInit();
	}


	public void commonInit()
	{
		uiUtils = RuntimeConfigFrame.getCommonBuilderUIImpl();
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
            JLabel1.setText(resourceBundle.getString("Log Details"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel1,ex); 
          }

//<UserCode_Begin_Bean_JLabel1>

//<UserCode_End_Bean_JLabel1>

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
            okButton.setText(resourceBundle.getString("OK"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+okButton,ex); 
          }

//<UserCode_Begin_Bean_okButton>

//<UserCode_End_Bean_okButton>

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
            JPanel5.setBorder(new javax.swing.border.EtchedBorder(0));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel5,ex); 
          }

//<UserCode_Begin_Bean_JPanel5>

//<UserCode_End_Bean_JPanel5>

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
            JLabel2.setText(resourceBundle.getString("Log File Name"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel2,ex); 
          }

//<UserCode_Begin_Bean_JLabel2>

//<UserCode_End_Bean_JLabel2>

          try
          {
            JLabel3.setText(resourceBundle.getString("Logging Directory"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel3,ex); 
          }

//<UserCode_Begin_Bean_JLabel3>

//<UserCode_End_Bean_JLabel3>

          try
          {
            JLabel4.setText(resourceBundle.getString("Maximum Number of Lines Per File"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel4,ex); 
          }

//<UserCode_Begin_Bean_JLabel4>

//<UserCode_End_Bean_JLabel4>

          try
          {
            JLabel5.setText(resourceBundle.getString("Maximum Number Of Files"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel5,ex); 
          }

//<UserCode_Begin_Bean_JLabel5>

//<UserCode_End_Bean_JLabel5>

          try
          {
            JLabel7.setText(resourceBundle.getString("Use Time Stamp ?"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel7,ex); 
          }

//<UserCode_Begin_Bean_JLabel7>

//<UserCode_End_Bean_JLabel7>

          try
          {
            JLabel8.setText(resourceBundle.getString("Enable Logging ?"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel8,ex); 
          }

//<UserCode_Begin_Bean_JLabel8>
//JLabel7.setVisible(false);
//JCheckBox1.setVisible(false);
//<UserCode_End_Bean_JLabel8>
		cancelButton.setPreferredSize(new Dimension(cancelButton.getPreferredSize().width+0,cancelButton.getPreferredSize().height+4));
		okButton.setPreferredSize(new Dimension(okButton.getPreferredSize().width+18,okButton.getPreferredSize().height+4));
		JLabel1.setPreferredSize(new Dimension(JLabel1.getPreferredSize().width+0,JLabel1.getPreferredSize().height+4));

  
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
        this.setSize(getPreferredSize().width+464,getPreferredSize().height+407); 
          setTitle(resourceBundle.getString("LogsAddModifyDialog"));
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
	uiUtils.centerWindow(this);
	okButton.setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());
	cancelButton.setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());
        setTitle(resourceBundle.getString("Logging Configuration")); 
  } 
  public void setUpConnections()
  { 

  //<Begin_setUpConnections> 

      okButton_okButton_conn1 okButton_okButton_conn11 =  new okButton_okButton_conn1();
      okButton.addActionListener(okButton_okButton_conn11);
      cancelButton_cancelButton_conn1 cancelButton_cancelButton_conn11 =  new cancelButton_cancelButton_conn1();
      cancelButton.addActionListener(cancelButton_cancelButton_conn11);
  
      //<End_setUpConnections>
  } 
  public void initVariables()
  { 

  //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        JPanel1= new javax.swing.JPanel();
        JLabel1= new javax.swing.JLabel();
        JPanel3= new javax.swing.JPanel();
        okButton= new javax.swing.JButton();
        cancelButton= new javax.swing.JButton();
        JPanel5= new javax.swing.JPanel();
        JPanel2= new javax.swing.JPanel();
        JLabel2= new javax.swing.JLabel();
        fileName= new javax.swing.JTextField();
        JLabel3= new javax.swing.JLabel();
        directory= new javax.swing.JTextField();
        JLabel4= new javax.swing.JLabel();
        maxLines= new javax.swing.JTextField();
        JLabel5= new javax.swing.JLabel();
        fileCount= new javax.swing.JTextField();
        JLabel7= new javax.swing.JLabel();
        timeStampCheck= new javax.swing.JCheckBox();
        JLabel8= new javax.swing.JLabel();
        loggingCheck= new javax.swing.JCheckBox();

  
        //<End_initVariables>
  } 
   
  public void setUpGUI(Container container)
  { 

  //<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new GridBagLayout());
inset = new Insets(10,10,5,10);
setConstraints(0,0,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel1,cons);
JPanel1.setLayout(new FlowLayout(1,5,5));
JPanel1.add(JLabel1);
inset = new Insets(5,10,5,10);
setConstraints(0,2,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel3,cons);
JPanel3.setLayout(new FlowLayout(1,5,5));
JPanel3.add(okButton);
JPanel3.add(cancelButton);
inset = new Insets(5,10,5,10);
setConstraints(0,1,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(JPanel5,cons);
JPanel5.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel5.add(JPanel2,cons);
JPanel2.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel2.add(JLabel2,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel2.add(fileName,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel2.add(JLabel3,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,1,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel2.add(directory,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel2.add(JLabel4,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,2,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel2.add(maxLines,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,3,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel2.add(JLabel5,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,3,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel2.add(fileCount,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,5,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel2.add(JLabel7,cons);
inset = new Insets(5,5,5,0);
setConstraints(1,5,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel2.add(timeStampCheck,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,6,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel2.add(JLabel8,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,6,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel2.add(loggingCheck,cons);

  
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


 

	
	public void populateTheUI(Hashtable mainHash,boolean flag,boolean isDetails)
	{
			if(isDetails)
				modifyFlag = false;
			else
				modifyFlag = true;
			addFlag = false;
			boolean specialFlag = false;
			String file = mainHash.get("FileName").toString();
			if(file.equals("stderr.txt") || file.equals("stdout.txt"))
				specialFlag = true;
			keyNodeHashtable = (Hashtable)mainHash.get("keyNodeVect");
			fileName.setText(mainHash.get("FileName").toString());
			directory.setText(mainHash.get("LogsDirectory").toString());
			maxLines.setText(mainHash.get("MaxLines").toString());
			fileCount.setText(mainHash.get("FileCount").toString());
			if(mainHash.get("UseTimeStamp").toString().equals("true"))
			{
				timeStampCheck.setSelected(true);
			}
			else
			{
				timeStampCheck.setSelected(false);
			}
			if(isDetails)
			{
				fileName.setEditable(false);
				directory.setEditable(false);
				maxLines.setEditable(false);
				fileCount.setEditable(false);
				//linesCached.setEditable(false);
				timeStampCheck.setEnabled(false);
			}
			else
			{
				fileName.setEditable(false);
				directory.setEditable(true);
				maxLines.setEditable(true);
				fileCount.setEditable(true);
				//linesCached.setEditable(true);
				timeStampCheck.setEnabled(true);
			}
			if(flag && specialFlag)
			{
				
				JPanel2.setBorder(new javax.swing.border.EmptyBorder(0,0,0,0));
				if(mainHash.get("Logging").toString().equals("true"))
				{
					loggingCheck.setSelected(true);
				}
				else
				{
					loggingCheck.setSelected(false);
				}
			}
			else
			{
				JLabel8.setVisible(false);
				loggingCheck.setVisible(false);
			}
			if(isDetails)
			{
				loggingCheck.setEnabled(false);
			}
			else
			{
				loggingCheck.setEnabled(true);
			}

	}	

	public void advancedPropertiesButtonActionPerformed()
	{
		LogsAdvancedProperties advancedProperties = new LogsAdvancedProperties(this);
		advancedProperties.init();
		if(!addFlag)
		advancedProperties.populateTheUI(keyNodeHashtable,modifyFlag);
		advancedProperties.setVisible(true);
	}

	public void reOrder()
	{
			JLabel8.setVisible(false);
			loggingCheck.setVisible(false);		
	}

	public void updateKeyNodeVector(Hashtable hash)
	{
		keyNodeHashtable = hash;
	}


//<Begin__class_okButton_okButton_conn1>

 class okButton_okButton_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_okButton_okButton_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  okButtonActionPerformed();
     }
//<UserCode_End_Connection_okButton_okButton_conn1>
 }//<End__class_okButton_okButton_conn1>
	
	public void okButtonActionPerformed()
	{
		String name = fileName.getText();
		if(name.equals(""))
		{
			JOptionPane.showMessageDialog(null,resourceBundle.getString("Please specify a file name"),resourceBundle.getString("Message"),JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		Hashtable finalHash = new Hashtable();
		Hashtable childHash = new Hashtable();
		if(name.equals("stdout.txt") || name.equals("stderr.txt"))
		{
			if(loggingCheck.isSelected())
				childHash.put("Logging","true");
			else
				childHash.put("Logging","false");
		}
		childHash.put("FileName",name);
		childHash.put("MaxLines",maxLines.getText());
		//childHash.put("MaxLinesCached",linesCached.getText());
		childHash.put("FileCount",fileCount.getText());
		childHash.put("LogsDirectory",directory.getText());
		if(timeStampCheck.isSelected())
			childHash.put("UseTimeStamp","true");
		else
			childHash.put("UseTimeStamp","false");
		if(keyNodeHashtable == null)
		{
			keyNodeHashtable = new Hashtable();
		}
		childHash.put("keyNodeVect",keyNodeHashtable);
		finalHash.put(name,childHash);
		mainUI.updateEntries(finalHash);
		this.dispose();
		this.setVisible(false);
	}


   


  public LogsAddModifyDialog()
  {
	uiUtils = RuntimeConfigFrame.getCommonBuilderUIImpl();
    //<Begin_LogsAddModifyDialog>
    pack();
  
    //<End_LogsAddModifyDialog>
  }

  public LogsAddModifyDialog(java.applet.Applet applet)
  {
	commonInit();
    //<Begin_LogsAddModifyDialog_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_LogsAddModifyDialog_java.applet.Applet>
  }
//<Begin__class_cancelButton_cancelButton_conn1>

 class cancelButton_cancelButton_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_cancelButton_cancelButton_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  LogsAddModifyDialog.this.dispose();
  LogsAddModifyDialog.this.setVisible(false);
     }
//<UserCode_End_Connection_cancelButton_cancelButton_conn1>
 }//<End__class_cancelButton_cancelButton_conn1>
	
	public void specialEntry(Hashtable mainHash)
	{
			boolean specialFlag = false;
			String file = mainHash.get("FileName").toString();
			if(file.equals("stderr.txt") || file.equals("stdout.txt"))
				specialFlag = true;
//			keyNodeHashtable = (Hashtable)mainHash.get("keyNodeVect");
			fileName.setText(mainHash.get("FileName").toString());
			directory.setText(mainHash.get("LogsDirectory").toString());
			maxLines.setText(mainHash.get("MaxLines").toString());
			fileCount.setText(mainHash.get("FileCount").toString());
			//if(mainHash.get("MaxLinesCached") != null)
				//linesCached.setText(mainHash.get("MaxLinesCached").toString());
			if(mainHash.get("UseTimeStamp").toString().equals("true"))
			{
				timeStampCheck.setSelected(true);
			}
			else
			{
				timeStampCheck.setSelected(false);
			}
			fileName.setEditable(false);
			directory.setEditable(false);
			maxLines.setEditable(false);
			fileCount.setEditable(false);
			//linesCached.setEditable(false);
			timeStampCheck.setEnabled(false);
			if(specialFlag)
			{
				
				JPanel2.setBorder(new javax.swing.border.EmptyBorder(0,0,0,0));
				if(mainHash.get("Logging").toString().equals("true"))
				{
					loggingCheck.setSelected(true);
				}
				else
				{
					loggingCheck.setSelected(false);
				}
			}
			else
			{
				JLabel8.setVisible(false);
				loggingCheck.setVisible(false);
			}
			
			loggingCheck.setEnabled(false);
			okButton.setVisible(false);
			cancelButton.setText(resourceBundle.getString("Close"));
		}

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}




