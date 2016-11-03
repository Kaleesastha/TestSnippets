
//$Id: DBXmlToolUI.java,v 1.2.10.2 2013/05/16 09:25:07 wesley Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// Kindly refer to tcats_edit_source_files.html file under help/source_generation directory for details




//<Begin_Version>
//version$4
//<End_Version>
package com.adventnet.nms.db.util;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.net.URL;

import com.adventnet.nms.webclient.util.*;

public class DBXmlToolUI extends JFrame  implements  ActionListener,com.adventnet.apiutils.ParameterChangeListener
{

    //THIS PROJECT CANNOT BE REGENERATED.

    MyResourceBundle resourceBundle = null;

	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "DBXmlToolResources";
    static com.adventnet.apiutils.BuilderResourceBundle resourceBundleNew = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel topPanel = null;
	javax.swing.JLabel titleLabel = null;
	javax.swing.JTextArea helpArea = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JPanel contentPanel = null;
	javax.swing.JLabel imageLabel = null;
	javax.swing.JPanel tablePanel = null;
	javax.swing.JPanel JPanel5 = null;
	javax.swing.JRadioButton updateDB_RButton = null;
	javax.swing.JRadioButton updateXML_RButton = null;
	javax.swing.JPanel JPanel6 = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	javax.swing.JTable table = null;
	javax.swing.JCheckBox selectAllCheckBox = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JLabel JLabel2 = null;
	javax.swing.JComboBox JComboBox1 = null;
	javax.swing.JPanel bottomPanel = null;
	javax.swing.JButton updateButton = null;
	javax.swing.JButton closeButton = null;
	javax.swing.JLabel statusLabel = null;
	javax.swing.table.DefaultTableModel tableModel = null;
	javax.swing.ButtonGroup buttonGroup = null;
	private com.adventnet.apiutils.ParameterObject po= null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>


    private ImageIcon xmlToDbIcon = new ImageIcon(getClass().getResource("xml_db_wizard.jpg"));
    private ImageIcon dbToXmlIcon = new ImageIcon(getClass().getResource("db_xml_wizard.jpg"));
    private ImageIcon frameIcon = new ImageIcon(getClass().getResource("frameicon.png"));
    private ImageIcon titleImage = new ImageIcon(getClass().getResource("title_image.jpg"));
 


  


 

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
  public void init()
  {
        //<Begin_init> 
	if(getParameter("RESOURCE_PROPERTIES" ) != null)
	{
		localePropertiesFileName = getParameter("RESOURCE_PROPERTIES");
	}
		resourceBundleNew = com.adventnet.apiutils.Utility.getBundle(localePropertiesFileName,getParameter("RESOURCE_LOCALE"),applet);
        if (initialized) return; 
        this.setSize(getPreferredSize().width+440,getPreferredSize().height+422); 
          setTitle(resourceBundleNew.getString("ScreenTitle"));
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
          showStatus(resourceBundleNew.getString("Error in init method"),ex); 
        } 
        // let us set the initialized variable to true so  
        // we dont initialize again even if init is called 
        initialized = true; 

  
         //<End_init>

     userInit();
  } 
  public String getParameter(String input)
  {
           //<Begin_getParameter_String> 
	if (po != null) 
	 { 
	   if(po.getParameter(input) != null) 
	     {
	       return (String)po.getParameter(input); 
	     }
	 }
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
            topPanel.setBorder(new javax.swing.border.EtchedBorder(1));
            topPanel.setBackground(new Color(-1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+topPanel,ex); 
          }

//<UserCode_Begin_Bean_topPanel>

//<UserCode_End_Bean_topPanel>

          try
          {
            titleLabel.setHorizontalAlignment(2);
            titleLabel.setFont(new Font("SansSerif",0,12));
            titleLabel.setForeground(new Color(-16777216));
            titleLabel.setHorizontalTextPosition(4);
            titleLabel.setBackground(new Color(-1));
            titleLabel.setOpaque(true);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundleNew.getString("Exception while setting properties for bean ")+titleLabel,ex); 
          }

//<UserCode_Begin_Bean_titleLabel>

//<UserCode_End_Bean_titleLabel>

          try
          {
            helpArea.setWrapStyleWord(true);
            helpArea.setEditable(false);
            helpArea.setText(resourceBundleNew.getString("This area will contain some help text"));
            helpArea.setBackground(new Color(-1));
            helpArea.setLineWrap(true);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundleNew.getString("Exception while setting properties for bean ")+helpArea,ex); 
          }

//<UserCode_Begin_Bean_helpArea>

//<UserCode_End_Bean_helpArea>

          try
          {
            JLabel1.setHorizontalAlignment(2);
            JLabel1.setForeground(new Color(-16777216));
            JLabel1.setHorizontalTextPosition(4);
            JLabel1.setText(resourceBundleNew.getString("Tree Configuration Updater"));
            JLabel1.setFont(new Font("sansserif",1,12));
            JLabel1.setBackground(new Color(-1));
            JLabel1.setOpaque(true);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundleNew.getString("Exception while setting properties for bean ")+JLabel1,ex); 
          }

//<UserCode_Begin_Bean_JLabel1>

//<UserCode_End_Bean_JLabel1>

          try
          {
            imageLabel.setHorizontalAlignment(2);
            imageLabel.setFont(new Font("SansSerif",0,12));
            imageLabel.setForeground(new Color(-16777216));
            imageLabel.setHorizontalTextPosition(4);
            imageLabel.setBorder(new javax.swing.border.BevelBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundleNew.getString("Exception while setting properties for bean ")+imageLabel,ex); 
          }

//<UserCode_Begin_Bean_imageLabel>

//<UserCode_End_Bean_imageLabel>

          try
          {
            tablePanel.setBorder(new javax.swing.border.EtchedBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundleNew.getString("Exception while setting properties for bean ")+tablePanel,ex); 
          }

//<UserCode_Begin_Bean_tablePanel>

//<UserCode_End_Bean_tablePanel>

          try
          {
            updateDB_RButton.setFont(new Font("SansSerif",0,12));
            updateDB_RButton.setHorizontalTextPosition(4);
            updateDB_RButton.setHorizontalAlignment(2);
            updateDB_RButton.setVerticalAlignment(0);
            updateDB_RButton.setVerticalTextPosition(0);
            updateDB_RButton.setText(resourceBundle.getString("Update Database"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundleNew.getString("Exception while setting properties for bean ")+updateDB_RButton,ex); 
          }

//<UserCode_Begin_Bean_updateDB_RButton>

//<UserCode_End_Bean_updateDB_RButton>

          try
          {
            updateXML_RButton.setFont(new Font("SansSerif",0,12));
            updateXML_RButton.setHorizontalTextPosition(4);
            updateXML_RButton.setHorizontalAlignment(2);
            updateXML_RButton.setVerticalAlignment(0);
            updateXML_RButton.setVerticalTextPosition(0);
            updateXML_RButton.setText(resourceBundleNew.getString("Update XML File"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundleNew.getString("Exception while setting properties for bean ")+updateXML_RButton,ex); 
          }

//<UserCode_Begin_Bean_updateXML_RButton>

//<UserCode_End_Bean_updateXML_RButton>

          try
          {
            JPanel6.setBorder(new javax.swing.border.BevelBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundleNew.getString("Exception while setting properties for bean ")+JPanel6,ex); 
          }

//<UserCode_Begin_Bean_JPanel6>

//<UserCode_End_Bean_JPanel6>

          try
          {
            table.setModel(tableModel);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundleNew.getString("Exception while setting properties for bean ")+table,ex); 
          }

//<UserCode_Begin_Bean_table>

//<UserCode_End_Bean_table>

          try
          {
            selectAllCheckBox.setFont(new Font("SansSerif",0,12));
            selectAllCheckBox.setHorizontalTextPosition(4);
            selectAllCheckBox.setText(resourceBundleNew.getString("Select all users"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundleNew.getString("Exception while setting properties for bean ")+selectAllCheckBox,ex); 
          }

//<UserCode_Begin_Bean_selectAllCheckBox>

//<UserCode_End_Bean_selectAllCheckBox>
          
          try
          {
            JLabel2.setHorizontalAlignment(2);
            JLabel2.setFont(new Font("SansSerif",0,12));
            JLabel2.setForeground(new Color(-16777216));
            JLabel2.setHorizontalTextPosition(4);
            JLabel2.setText(resourceBundleNew.getString(" Select File"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundleNew.getString("Exception while setting properties for bean ")+JLabel2,ex); 
          }

//<UserCode_Begin_Bean_JLabel2>

//<UserCode_End_Bean_JLabel2>

          try
          {
            JComboBox1.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundleNew.getString("Exception while setting properties for bean ")+JComboBox1,ex); 
          }

//<UserCode_Begin_Bean_JComboBox1>

//<UserCode_End_Bean_JComboBox1>

          try
          {
            bottomPanel.setBorder(new javax.swing.border.EtchedBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundleNew.getString("Exception while setting properties for bean ")+bottomPanel,ex); 
          }

//<UserCode_Begin_Bean_bottomPanel>

//<UserCode_End_Bean_bottomPanel>

          try
          {
            updateButton.setFont(new Font("SansSerif",0,12));
            updateButton.setHorizontalTextPosition(4);
            updateButton.setText(resourceBundleNew.getString("Update"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundleNew.getString("Exception while setting properties for bean ")+updateButton,ex); 
          }

//<UserCode_Begin_Bean_updateButton>

//<UserCode_End_Bean_updateButton>

          try
          {
            closeButton.setFont(new Font("SansSerif",0,12));
            closeButton.setHorizontalTextPosition(4);
            closeButton.setText(resourceBundleNew.getString("Close"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundleNew.getString("Exception while setting properties for bean ")+closeButton,ex); 
          }

//<UserCode_Begin_Bean_closeButton>

//<UserCode_End_Bean_closeButton>

          try
          {
            statusLabel.setHorizontalAlignment(2);
            statusLabel.setFont(new Font("SansSerif",0,12));
            statusLabel.setHorizontalTextPosition(4);
            statusLabel.setBackground(new Color(-15529596));
            statusLabel.setOpaque(true);
            statusLabel.setForeground(new Color(-1));
            statusLabel.setBorder(new javax.swing.border.EtchedBorder(1));
            statusLabel.setText(resourceBundleNew.getString("Select users from the table to do the update operation"));
            statusLabel.setIcon(com.adventnet.apiutils.Utility.findImage("./images/alarm.png",applet,true));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundleNew.getString("Exception while setting properties for bean ")+statusLabel,ex); 
          }

//<UserCode_Begin_Bean_statusLabel>

//<UserCode_End_Bean_statusLabel>
		closeButton.setPreferredSize(new Dimension(closeButton.getPreferredSize().width+7,closeButton.getPreferredSize().height+2));
		updateButton.setPreferredSize(new Dimension(updateButton.getPreferredSize().width+3,updateButton.getPreferredSize().height+2));

  
          //<End_setUpProperties>
  } 

    private void initializeResourceBundle()
    {
        Locale locale = Locale.getDefault();
        String country = locale.getCountry();
        String language = locale.getLanguage();

        String resourceFileName = "DBXmlToolResources"+"_"+language+"_"+country+".properties";
        File resFile = new File("./html/" + resourceFileName);
        URL url = null;

        try
        {
            if(resFile.exists())
            {
                url = resFile.toURL();
            }
            else
            {
                resourceFileName = "./html/DBXmlToolResources.properties";
                resFile = new File(resourceFileName);
                url = resFile.toURL();
            }
            resourceBundle = new MyResourceBundle(url.openStream());
        }
        catch(Exception ee)
        {
            resourceBundle = new MyResourceBundle();
            System.out.println("Error while creating resource bundle");
        }
    }


  public void initVariables()
  {
        //<Begin_initVariables> 
	 if(po == null)
	{
	po = new com.adventnet.apiutils.ParameterObject();
	}
        Top= new javax.swing.JPanel();
        topPanel= new javax.swing.JPanel();
        titleLabel= new javax.swing.JLabel();
        helpArea= new javax.swing.JTextArea();
        JLabel1= new javax.swing.JLabel();
        contentPanel= new javax.swing.JPanel();
        imageLabel= new javax.swing.JLabel();
        tablePanel= new javax.swing.JPanel();
        JPanel5= new javax.swing.JPanel();
        updateDB_RButton= new javax.swing.JRadioButton();
        updateXML_RButton= new javax.swing.JRadioButton();
        JPanel6= new javax.swing.JPanel();
        JScrollPane1= new javax.swing.JScrollPane();
        table= new javax.swing.JTable();
        selectAllCheckBox= new javax.swing.JCheckBox();
        JPanel1= new javax.swing.JPanel();
        JLabel2= new javax.swing.JLabel();
        JComboBox1= new javax.swing.JComboBox();
        bottomPanel= new javax.swing.JPanel();
        updateButton= new javax.swing.JButton();
        closeButton= new javax.swing.JButton();
        statusLabel= new javax.swing.JLabel();
        tableModel= new javax.swing.table.DefaultTableModel();
        buttonGroup= new javax.swing.ButtonGroup();
        initializeParameters(); 

  
          //<End_initVariables>
  } 
  public void setUpGUI(Container container)
  {
//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(topPanel,cons);
topPanel.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,2,0.0,0.1,cons.NORTHWEST,cons.VERTICAL,inset,0,0);
topPanel.add(titleLabel,cons);
inset = new Insets(2,5,5,5);
setConstraints(0,1,1,1,0.1,0.1,cons.NORTHWEST,cons.BOTH,inset,0,0);
topPanel.add(helpArea,cons);
inset = new Insets(5,5,0,5);
setConstraints(0,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
topPanel.add(JLabel1,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(contentPanel,cons);
contentPanel.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.1,cons.CENTER,cons.VERTICAL,inset,0,0);
contentPanel.add(imageLabel,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
contentPanel.add(tablePanel,cons);
tablePanel.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
tablePanel.add(JPanel5,cons);
JPanel5.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.5,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel5.add(updateDB_RButton,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.5,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel5.add(updateXML_RButton,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
tablePanel.add(JPanel6,cons);
JPanel6.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(0,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel6.add(JScrollPane1,cons);
JScrollPane1.getViewport().add(table);
inset = new Insets(5,5,5,5);
setConstraints(0,3,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
tablePanel.add(selectAllCheckBox,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
tablePanel.add(JPanel1,cons);
JPanel1.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(0,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel1.add(JLabel2,cons);
inset = new Insets(0,0,0,0);
setConstraints(1,0,1,1,0.5,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel1.add(JComboBox1,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(bottomPanel,cons);
bottomPanel.setLayout(new FlowLayout(2,5,5));
bottomPanel.add(updateButton);
bottomPanel.add(closeButton);
inset = new Insets(5,5,5,5);
setConstraints(0,3,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(statusLabel,cons);

  
//<End_setUpGUI_Container>
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





  public DBXmlToolUI()
  {
    //<Begin_DBXmlToolUI>
    pack();
  
    //<End_DBXmlToolUI>
    initializeResourceBundle();
	init();
	setVisible(true);
  }

  public DBXmlToolUI(java.applet.Applet applet)
  {
    //<Begin_DBXmlToolUI_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_DBXmlToolUI_java.applet.Applet>
  } 
  public void setProperties(java.util.Properties props)
  {
         //<Begin_setProperties_java.util.Properties> 
	if(po != null)
	{
	po.setParameters(props);
	}
  
         //<End_setProperties_java.util.Properties>
  } 
  private void initializeParameters()
  {
          //<Begin_initializeParameters> 
	 if(po != null) 
	   {
	    po.addParameterChangeListener(this);
	   }

  
          //<End_initializeParameters>
  } 
  public void destroy()
  {
         //<Begin_destroy> 
	if(po != null)
	{
	 po.removeParameterChangeListener(this);
	}
  
         //<End_destroy>
  } 
  public void setParameterObject(com.adventnet.apiutils.ParameterObject paramObj)
  {
         //<Begin_setParameterObject_com.adventnet.apiutils.ParameterObject> 
	this.po=paramObj;

  
         //<End_setParameterObject_com.adventnet.apiutils.ParameterObject>
  } 
  public void parameterChanged(com.adventnet.apiutils.ParameterObject paramObj)
  {
  //<Begin_parameterChanged_com.adventnet.apiutils.ParameterObject> 

  
  //<End_parameterChanged_com.adventnet.apiutils.ParameterObject>
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
  
  private void userInit()
  {
	 updateButton.setEnabled(false);
               buttonGroup.add(updateDB_RButton);
	buttonGroup.add(updateXML_RButton);
	updateDB_RButton.setSelected(true);
	helpArea.setText(resourceBundle.getString("This tool is used to update the contents of the \" Tree.xml \" file to the database and vice versa"));
              Vector columnVector = new Vector();
      	columnVector.add(" ");
	columnVector.add(resourceBundle.getString("Users List"));
	tableModel.setDataVector(new Vector(),columnVector);
	table.setDefaultEditor(Object.class,null);
	table.setDefaultRenderer(Object.class,new CustomRenderer());
	table.setRowHeight(22);
	table.setShowGrid(true);
	table.getTableHeader().setReorderingAllowed(false);	
	table.setToolTipText(resourceBundle.getString("Click on the table rows to Select users"));
	TableColumn column = table.getColumnModel().getColumn(0);
	column.setMaxWidth(27);
	setTitle(resourceBundle.getString("Tree Configuration Updater"));
	createListeners();
	populateUsersList();
	centerWindow(this);
	setIconImage(frameIcon.getImage());
	titleLabel.setIcon(titleImage);
	imageLabel.setIcon(xmlToDbIcon);
	String items[]={"Tree.xml","Dashboards.xml"};//No I18N
	JComboBox1.addItem("Tree.xml");//No I18N
	JComboBox1.addItem("Dashboards.xml");//No I18N
	addWindowListener(new WindowAdapter()
	 {
	    public void windowClosing(WindowEvent evt)
	       {
	          System.exit(0);
	       }
	 });
  }
  
      public void actionPerformed(ActionEvent ae)
    {
        String command = ae.getActionCommand();
        
        if(command.equals("UpdateDB"))
        {
            imageLabel.setIcon(dbToXmlIcon);
            imageLabel.repaint();
        }
        else if(command.equals("UpdateXML"))
        {
            imageLabel.setIcon(xmlToDbIcon);
            imageLabel.repaint();
        }
        else if(command.equals("SelectAll"))
        {
            int count = table.getRowCount();
            if(selectAllCheckBox.isSelected())
            {
                 updateButton.setEnabled(true);
                for(int i=0;i<count;i++)
                {
                    UserObject object = (UserObject)table.getValueAt(i,1);
                    object.setSelected(true);
                }
            }
            else
            {
                 updateButton.setEnabled(false);                 
                for(int i=0;i<count;i++)
                {
                    UserObject object = (UserObject)table.getValueAt(i,1);
                    object.setSelected(false);
                }
            }
            table.repaint();
        }

        else if(command.equals("Update"))
        {
            okButtonActionPerformed();
        }
        else if(command.equals("Close"))
        {
            System.exit(0);
        }
    }
    
    private void okButtonActionPerformed()
    {
        int count = table.getRowCount();
        final Vector vector = new Vector();
        for(int i=0;i<count;i++)
        {
            UserObject object = (UserObject)table.getValueAt(i,1);
            if(object.isSelected())
            {
                vector.add(object.getValue());
            }
        }
        if(vector.size() == 0)
        {
	    JOptionPane.showMessageDialog(this,resourceBundle.getString("Please select atleast one user to do the update operation"));
            return;
	}

        Thread runThread = new Thread()
        {
            public void run()
            {
                String operation = null;
                if(updateXML_RButton.isSelected())
                {
                    operation = "UpdateXML";
                }
                else
                {
                    operation = "UpdateDB";
                }
                setCursor(Cursor.WAIT_CURSOR);
                updateButton.setEnabled(false);
                closeButton.setEnabled(false);
                selectAllCheckBox.setEnabled(false);
                updateDB_RButton.setEnabled(false);
                updateXML_RButton.setEnabled(false);
                statusLabel.setText(resourceBundle.getString("Update in progress.. Please wait"));
                int selectedItem = JComboBox1.getSelectedIndex();
                
                if(selectedItem == 0)
                {
                	DBXmlTool tool = new DBXmlTool(operation,vector);
                	setResult(tool.runTool());
                }
                else
                {
                	DashboardTool tool = new DashboardTool(operation,vector);
                	setResult(tool.runTool());
                }
            }
        };
        runThread.start();
    }
    
     private void setResult(Vector v)
    {
        setCursor(Cursor.DEFAULT_CURSOR);
        String operation = "";
        if(updateDB_RButton.isSelected())
        {
             operation = "Update Database";
        }
        else
        {
             operation = "Update XML File";
        }
	ResultViewer auditUI = new ResultViewer(this,v,operation, resourceBundle);
	updateButton.setEnabled(true);
        closeButton.setEnabled(true);
        selectAllCheckBox.setEnabled(true); 
                updateDB_RButton.setEnabled(true);
                updateXML_RButton.setEnabled(true);        
        statusLabel.setText(resourceBundle.getString("Done"));
    }
    
        public void centerWindow(Component c)
    {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int width = dim.width/2-(c.getSize().width/2);
        int height = dim.height/2-(c.getSize().height/2);
        c.setLocation(width,height);
    }
  
    private void createListeners()
    {
        updateXML_RButton.setActionCommand("UpdateDB");
        updateDB_RButton.setActionCommand("UpdateXML");
        updateXML_RButton.addActionListener(this);
        updateDB_RButton.addActionListener(this);

        table.addMouseListener(new TableMouseListener());

        selectAllCheckBox.setActionCommand("SelectAll");
        selectAllCheckBox.addActionListener(this);

        updateButton.setActionCommand("Update");
        closeButton.setActionCommand("Close");
        updateButton.addActionListener(this);
        closeButton.addActionListener(this);

    }
  
     private void populateUsersList()
    {
        String userDir = System.getProperty("user.dir");
        String absolutePath = userDir+File.separator+"users"; 
        File file = new File(absolutePath);
	if(file.exists()) 
	{
		if((file.listFiles()).length > 0)
		{
			String [] array = file.list();
			for(int i=0;i<array.length;i++)
			{
				File temp = new File(absolutePath+File.separator+array[i]);
				if(temp.isDirectory())
				{
					Vector v = new Vector();
					v.add("");
					v.add(new UserObject(array[i]));
					tableModel.addRow(v);
				}
			}
		}
		else
		{
		        JOptionPane.showMessageDialog(null, resourceBundle.getString("A Directory by name \"users\" is empty under the WebNMS Directory ")); //No I18N
			System.exit(0);
		}
	}
	else
	{
      		JOptionPane.showMessageDialog(null, resourceBundle.getString("A Directory by name \"users\" does not exist under the WebNMS Directory")); //No I18N
	    	System.exit(0);
	}
    }
    
    
        class UserObject
    {
        private boolean isSelected = false;
        private String value = null;
        
        public UserObject(String str)
        {
            value = str;
        }

        public UserObject(String str, boolean selected)
        {
            value = str;
            isSelected = selected;
        }

        public boolean isSelected()
        {
            return isSelected;
        }

        public String getValue()
        {
            return value;
        }

        public void setValue(String str)
        {
            value = str;
        }

        public void setSelected(boolean b)
        {
            isSelected = b;
        }
    }

    class CustomRenderer extends JLabel implements TableCellRenderer
    {
        ImageIcon selectedIcon = null;
        ImageIcon notSelectedIcon = null;

//        Color color = new Color(255,255,210);

        public CustomRenderer()
        {
            super();
            setOpaque(true);
            setFont(new Font("Dialog",Font.PLAIN,12));
            setHorizontalAlignment(SwingConstants.CENTER);
            selectedIcon = new ImageIcon(getClass().getResource("selected.png"));
            notSelectedIcon = new ImageIcon(getClass().getResource("notselected.png"));
        }

        public Component getTableCellRendererComponent(JTable table, Object value,
                                      boolean isSelected, boolean hasFocus, 
                                      int row, int column)
        {
            if(isSelected)
            {
                setBackground(table.getSelectionBackground());
                setForeground(table.getSelectionForeground());
            }
            else
            {
                setBackground(table.getBackground());
                setForeground(table.getForeground());
            }

            if(column == 1)
            {
                UserObject object = (UserObject)value;
                setIcon(null);
                setText(object.getValue());
                setHorizontalAlignment(SwingConstants.LEFT);
            }

            else
            {
                UserObject object = (UserObject)table.getValueAt(row,1);
                if(object.isSelected())
                {
                    setIcon(selectedIcon);
                }
                else
                {
                    setIcon(notSelectedIcon);
                }
  //              setBackground(color);
                setHorizontalAlignment(SwingConstants.CENTER);
                setText(value.toString());
            }
            return this;
        }
    }

    class TableMouseListener extends MouseAdapter
    {
        public void mousePressed(MouseEvent me)
        {
            Point point = me.getPoint();
            int row = table.rowAtPoint(point);
            UserObject object = (UserObject)table.getValueAt(row,1);
            selectAllCheckBox.setSelected(false);
            if(object.isSelected())
            {
                object.setSelected(false);
            }
            else
            {
                object.setSelected(true);
            }
            table.repaint();             
             if(table.getSelectedRow() != -1)
             {
                  boolean flag = false;
                  int count = table.getRowCount();
	        for(int i=0;i<count;i++)
	        {
	            UserObject temp = (UserObject)table.getValueAt(i,1);
	            if(temp.isSelected())
	            {
	                flag = true;
	                break;
	            }
	        }
	        if(flag)                  
                  updateButton.setEnabled(true);
                  else
                  {
                       updateButton.setEnabled(false);
                  }
             }
             else
             {
                  boolean flag = false;
                  int count = table.getRowCount();
	        for(int i=0;i<count;i++)
	        {
	            UserObject temp = (UserObject)table.getValueAt(i,1);
	            if(temp.isSelected())
	            {
	                flag = true;
	                break;
	            }
	        }
	        if(!flag)
	        {
                  	updateButton.setEnabled(false);
	        }
                else
                {
                    updateButton.setEnabled(true);
                }
             }

        }
    }
    
    public static void main(String args[])
    {
        int length = args.length;
        if(length != 2)
        {
            new DBXmlToolUI();
        }
        else
        {
            String action = args[0];
            if(action.equalsIgnoreCase("UPDATEDB") || action.equalsIgnoreCase("UPDATEXML"))
            {
                String user = args[1];
                DBXmlTool tool = new DBXmlTool(action,user);
            }
            else if(action.equalsIgnoreCase("UPDATEDASHBOARDDB") || action.equalsIgnoreCase("UPDATEDASHBOARDXML"))
    	    {
    		Vector<String> users = new Vector<String>();
    		users.add(args[1]);
    		String operation = "UPDATEDB";
    		if(action.equalsIgnoreCase("UPDATEDASHBOARDXML"))
    		{
    			operation = "UPDATEXML";
    		}
    		DashboardTool tool = new DashboardTool(operation, users);
    		Vector<OperationResult> result = tool.runTool();
    		if(result != null)
    		{
    			OperationResult or = result.elementAt(0);
    			if(or.getUpdateOperationResult().equalsIgnoreCase("Success"))
    			{
    				System.out.println("\n\""+operation+"\" operation Successful for user "+or.getUserName());
    			}
    			else{
    				System.err.println("\n\""+operation+"\" operation Failed for user "+or.getUserName()+"\n");
    			}
    		}
    		else{
    			 System.err.println("\nUpdate Failed");
    		}
    	    }
            else
            {
                System.err.println("\nUsage : DBXmlTool updateDB/updateXML <User Name>\n");
                System.err.println("                      OR                         \n");
                System.err.println("Invoke the script without any arguments to bring up the UI");
                System.err.println("                      OR                         \n");
                System.err.println("Usage : DBXmlTool updatedashboarddb/updatedashboardXML <User Name>");
                System.exit(0);
            }
        }
    }

    class MyResourceBundle extends ResourceBundle
    {
        PropertyResourceBundle bundle = null;

        public MyResourceBundle()
        {
        }

        public MyResourceBundle(InputStream stream) throws IOException
        {
            bundle = new PropertyResourceBundle(stream);
        }

        public Object handleGetObject(String key)
        {
            if(bundle == null)
            {
                return key;	
            }

            String value = (String)bundle.handleGetObject(key);
            if(value == null || "".equals(value) )
            {  
                return (Object)key;
            }
            else
            {
                return value;
            }
        }

        public Enumeration getKeys()
        {
            return bundle.getKeys();
        }        
    }

}