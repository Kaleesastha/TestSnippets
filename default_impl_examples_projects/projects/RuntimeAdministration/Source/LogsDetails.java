

// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.runtimeconfig;



//$Id: LogsDetails.java,v 1.2 2007/02/22 15:03:07 srajeswari Exp $




import com.adventnet.nms.util.CommonBuilderUIInterface;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class LogsDetails extends JDialog 
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
	javax.swing.JLabel label6 = null;
	javax.swing.JTextField linesCached = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JPanel JPanel6 = null;
	javax.swing.JPanel JPanel7 = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	javax.swing.JTable table = null;
	javax.swing.JPanel JPanel4 = null;
	javax.swing.JButton JButton1 = null;
	javax.swing.table.DefaultTableModel tableModel = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>

	CommonBuilderUIInterface uiUtils  = null;
  
  
  




























   


  public LogsDetails()
  {
	uiUtils = RuntimeConfigFrame.getCommonBuilderUIImpl();
    //<Begin_LogsDetails>
    pack();
  
    //<End_LogsDetails>
	
  }

  public LogsDetails(java.applet.Applet applet)
  {
	uiUtils = RuntimeConfigFrame.getCommonBuilderUIImpl();
    //<Begin_LogsDetails_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_LogsDetails_java.applet.Applet>
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
            JLabel1.setText(resourceBundle.getString("Logs Details"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel1,ex); 
          }

//<UserCode_Begin_Bean_JLabel1>

//<UserCode_End_Bean_JLabel1>

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

          try
          {
            label6.setText(resourceBundle.getString("Maximum Lines Cached"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+label6,ex); 
          }

//<UserCode_Begin_Bean_label6>

//<UserCode_End_Bean_label6>

          try
          {
            JPanel6.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(0),resourceBundle.getString("Advanced Properties"),0,0,new Font("Dialog",0,12),new Color(-10066279)));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel6,ex); 
          }

//<UserCode_Begin_Bean_JPanel6>

//<UserCode_End_Bean_JPanel6>

          try
          {
            table.setModel(tableModel);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+table,ex); 
          }

//<UserCode_Begin_Bean_table>

//<UserCode_End_Bean_table>

          try
          {
            JPanel4.setBorder(new javax.swing.border.EtchedBorder(0));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel4,ex); 
          }

//<UserCode_Begin_Bean_JPanel4>

//<UserCode_End_Bean_JPanel4>

          try
          {
            JButton1.setText(resourceBundle.getString("Close"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton1,ex); 
          }

//<UserCode_Begin_Bean_JButton1>

//<UserCode_End_Bean_JButton1>

//<UserCode_Begin_Bean_tableModel>
tableModel.setDataVector(
             new Object[0][0],
            new Object[]{"Key Name","Display Name","Log Level","Enable Logging"});
//<UserCode_End_Bean_tableModel>
		JButton1.setPreferredSize(new Dimension(JButton1.getPreferredSize().width+0,JButton1.getPreferredSize().height+4));
		JLabel1.setPreferredSize(new Dimension(JLabel1.getPreferredSize().width+0,JLabel1.getPreferredSize().height+4));

  
          //<End_setUpProperties>

		//Added by Balan for Close Button not visible fully code duplicated
		JButton1.setPreferredSize(new Dimension(JButton1.getPreferredSize().width+6,JButton1.getPreferredSize().height));
		//Add Ends
tableModel.setDataVector(
             new Object[0][0],
            new Object[]{resourceBundle.getString("Key Name"),resourceBundle.getString("Display Name"),resourceBundle.getString("Log Level"),resourceBundle.getString("Enable Logging")});	

 TitledBorder tl=(TitledBorder)JPanel6.getBorder();
 tl.setTitle(resourceBundle.getString("Advanced Properties"));
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
        this.setSize(getPreferredSize().width+474,getPreferredSize().height+469); 
          setTitle(resourceBundle.getString("LogsDetails"));
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
	table.setDefaultEditor(Object.class,null);
	table.setDefaultRenderer(Object.class,new CustomRenderer());
	table.getTableHeader().setReorderingAllowed(false);
	JButton1.setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());
        setTitle(resourceBundle.getString("Logs Details")); 
  } 
  public void setUpConnections()
  { 

  //<Begin_setUpConnections> 

      JButton1_JButton1_conn1 JButton1_JButton1_conn11 =  new JButton1_JButton1_conn1();
      JButton1.addActionListener(JButton1_JButton1_conn11);
  
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
        label6= new javax.swing.JLabel();
        linesCached= new javax.swing.JTextField();
        JPanel3= new javax.swing.JPanel();
        JPanel6= new javax.swing.JPanel();
        JPanel7= new javax.swing.JPanel();
        JScrollPane1= new javax.swing.JScrollPane();
        table= new javax.swing.JTable();
        JPanel4= new javax.swing.JPanel();
        JButton1= new javax.swing.JButton();
        tableModel= new javax.swing.table.DefaultTableModel();

  
        //<End_initVariables>
  } 
   
  public void setUpGUI(Container container)
  { 

  //<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel1,cons);
JPanel1.setLayout(new FlowLayout(1,5,5));
JPanel1.add(JLabel1);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel2,cons);
JPanel2.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(0,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel2.add(JPanel5,cons);
JPanel5.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel5.add(JLabel2,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel5.add(fileName,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel5.add(JLabel3,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,1,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel5.add(directory,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel5.add(JLabel4,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,2,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel5.add(maxLines,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,3,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel5.add(JLabel5,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,3,1,1,0.0,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel5.add(fileCount,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,5,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel5.add(JLabel7,cons);
inset = new Insets(5,5,5,0);
setConstraints(1,5,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel5.add(timeStampCheck,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,6,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel5.add(JLabel8,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,6,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel5.add(loggingCheck,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,4,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel5.add(label6,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,4,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel5.add(linesCached,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(JPanel3,cons);
JPanel3.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(0,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel3.add(JPanel6,cons);
JPanel6.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel6.add(JPanel7,cons);
JPanel7.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(0,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel7.add(JScrollPane1,cons);
JScrollPane1.getViewport().add(table);
inset = new Insets(5,5,5,5);
setConstraints(0,3,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel4,cons);
JPanel4.setLayout(new FlowLayout(1,5,5));
JPanel4.add(JButton1);

  
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

	public void populateTheUI(Hashtable mainHash,boolean isSpecial)
	{
			String file = mainHash.get("FileName").toString();
//			if(file.equals("stderr.txt") || file.equals("stdout.txt"))
//				specialFlag = true;
			Hashtable keyNodeHashtable = (Hashtable)mainHash.get("keyNodeVect");
			if(keyNodeHashtable != null)
			fillTable(keyNodeHashtable);
			fileName.setText(mainHash.get("FileName").toString());
			directory.setText(mainHash.get("LogsDirectory").toString());
			maxLines.setText(mainHash.get("MaxLines").toString());
			fileCount.setText(mainHash.get("FileCount").toString());
			if(mainHash.get("MaxLinesCached") != null)
			{
				linesCached.setText(mainHash.get("MaxLinesCached").toString());
			}
			else
			{
				linesCached.setText("");
			}
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
			timeStampCheck.setEnabled(false);
			linesCached.setEditable(false);
			
			
			if(isSpecial)
			{
				//JPanel3.setVisible(false);
//				JPanel2.setBorder(new javax.swing.border.EmptyBorder(0,0,0,0));
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
	}

	public void fillTable(Hashtable mainHash)
	{
		Enumeration enumerate = mainHash.keys();
		while(enumerate.hasMoreElements())
		{
			Hashtable hash = (Hashtable)mainHash.get(enumerate.nextElement());
			Vector vect = new Vector();
			vect.add(hash.get("Name"));
			vect.add(hash.get("DisplayName"));
			String str = hash.get("LogLevel").toString().trim();
			if(str.equals("1"))
			{
				vect.add("Summary");
			}
			else if(str.equals("2"))
			{
				vect.add("Intermediate Details");
			}
			else if(str.equals("3"))//no internationalisation
			{
				vect.add("Verbose");
			}
                        else 
                            {
                                vect.add("Debug");//no internationalisation
                            }
			vect.add(hash.get("Logging"));
			tableModel.addRow(vect);
		}
	}

 


 
//<Begin__class_JButton1_JButton1_conn1>

 class JButton1_JButton1_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton1_JButton1_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  LogsDetails.this.setVisible(false);
  LogsDetails.this.dispose(); 
     }
//<UserCode_End_Connection_JButton1_JButton1_conn1>
 }//<End__class_JButton1_JButton1_conn1>

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}






