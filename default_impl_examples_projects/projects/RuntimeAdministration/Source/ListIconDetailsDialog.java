

// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.runtimeconfig;




//$Id: ListIconDetailsDialog.java,v 1.2.6.1 2012/01/25 05:12:46 karen.r Exp $




import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.table.*;
import com.adventnet.nms.util.CommonBuilderUIInterface;

public class ListIconDetailsDialog extends JDialog 
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
	javax.swing.JScrollPane JScrollPane1 = null;
	javax.swing.JTable table = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JButton closeButton = null;
	javax.swing.JPanel JPanel4 = null;
	javax.swing.JLabel typeLabel = null;
	javax.swing.JTextField typeText = null;
	javax.swing.JLabel menuLabel = null;
	javax.swing.JTextField menuText = null;
	javax.swing.table.DefaultTableModel tableModel = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>

	CommonBuilderUIInterface uiUtils = null;
	TableColumn checkColumn = null;
	ListIconRenderer d =null;  
  










   


  public ListIconDetailsDialog()
  {
	uiUtils = RuntimeConfigFrame.getCommonBuilderUIImpl();
    //<Begin_ListIconDetailsDialog>
    pack();
  
    //<End_ListIconDetailsDialog>
	
 }

  public ListIconDetailsDialog(java.applet.Applet applet)
  {
  	this.applet=applet;
	uiUtils = RuntimeConfigFrame.getCommonBuilderUIImpl();
    //<Begin_ListIconDetailsDialog_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_ListIconDetailsDialog_java.applet.Applet>
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
            JLabel1.setText(resourceBundle.getString("Details"));
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
            closeButton.setText(resourceBundle.getString("Close"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+closeButton,ex); 
          }

//<UserCode_Begin_Bean_closeButton>

//<UserCode_End_Bean_closeButton>

          try
          {
            typeLabel.setText(resourceBundle.getString("Type"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+typeLabel,ex); 
          }

//<UserCode_Begin_Bean_typeLabel>

//<UserCode_End_Bean_typeLabel>

          try
          {
            menuLabel.setText(resourceBundle.getString("Menu File Name"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+menuLabel,ex); 
          }

//<UserCode_Begin_Bean_menuLabel>

//<UserCode_End_Bean_menuLabel>

//<UserCode_Begin_Bean_tableModel>
tableModel.setDataVector(
             new Object[0][0],
            new Object[]{"Image Category","Image Name","Image"});
//<UserCode_End_Bean_tableModel>
		closeButton.setPreferredSize(new Dimension(closeButton.getPreferredSize().width+0,closeButton.getPreferredSize().height+4));
		JLabel1.setPreferredSize(new Dimension(JLabel1.getPreferredSize().width+0,JLabel1.getPreferredSize().height+4));

  
          //<End_setUpProperties>
tableModel.setDataVector(
             new Object[0][0],
            new Object[]{resourceBundle.getString("Image Category"),resourceBundle.getString("Image Name"),resourceBundle.getString("Image")});	
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
        this.setSize(getPreferredSize().width+399,getPreferredSize().height+355); 
          setTitle(resourceBundle.getString("ListIconDetailsDialog"));
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
		table.getTableHeader().setReorderingAllowed(false);
		typeText.setEditable(false);
		menuText.setEditable(false);
		checkColumn = table.getColumnModel().getColumn(2);
		d = new ListIconRenderer();
		d.setHorizontalAlignment(SwingConstants.CENTER);
		d.setOpaque(true);
		table.setDefaultEditor(Object.class,null);
		checkColumn.setCellRenderer(d); 
		TableColumn firstColumn = table.getColumnModel().getColumn(0);
		firstColumn.setCellRenderer(new CustomRenderer());
		TableColumn secondColumn = table.getColumnModel().getColumn(1);
		secondColumn.setCellRenderer(new CustomRenderer());	
		closeButton.setFont(RuntimeConfigFrame.getCommonBuilderUIImpl().getFont());

                setTitle(resourceBundle.getString("ManagedObject User Interface Settings")); 
		setModal(true);
  } 
  public void setUpConnections()
  { 

  //<Begin_setUpConnections> 

      closeButton_closeButton_conn1 closeButton_closeButton_conn11 =  new closeButton_closeButton_conn1();
      closeButton.addActionListener(closeButton_closeButton_conn11);
  
      //<End_setUpConnections>
  } 
  public void initVariables()
  { 

  //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        JPanel1= new javax.swing.JPanel();
        JLabel1= new javax.swing.JLabel();
        JPanel2= new javax.swing.JPanel();
        JScrollPane1= new javax.swing.JScrollPane();
        table= new javax.swing.JTable();
        JPanel3= new javax.swing.JPanel();
        closeButton= new javax.swing.JButton();
        JPanel4= new javax.swing.JPanel();
        typeLabel= new javax.swing.JLabel();
        typeText= new javax.swing.JTextField();
        menuLabel= new javax.swing.JLabel();
        menuText= new javax.swing.JTextField();
        tableModel= new javax.swing.table.DefaultTableModel();

  
        //<End_initVariables>
  } 
   
  public void setUpGUI(Container container)
  { 

  //<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new GridBagLayout());
inset = new Insets(10,10,10,10);
setConstraints(0,0,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel1,cons);
JPanel1.setLayout(new FlowLayout(1,5,5));
JPanel1.add(JLabel1);
inset = new Insets(10,10,10,10);
setConstraints(0,2,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(JPanel2,cons);
JPanel2.setLayout(new GridBagLayout());
inset = new Insets(5,0,0,0);
setConstraints(0,2,2,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel2.add(JScrollPane1,cons);
JScrollPane1.getViewport().add(table);
inset = new Insets(10,10,10,10);
setConstraints(0,3,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel3,cons);
JPanel3.setLayout(new FlowLayout(1,5,5));
JPanel3.add(closeButton);
inset = new Insets(10,10,10,10);
setConstraints(0,1,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel4,cons);
JPanel4.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel4.add(typeLabel,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel4.add(typeText,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel4.add(menuLabel,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,1,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel4.add(menuText,cons);

  
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

	public void populateDetails(Hashtable hash)
	{
		boolean transparentFlag = false;
		Enumeration enumerate = hash.keys();
		{
			while (enumerate.hasMoreElements())
			{
				if(enumerate.nextElement().toString().equalsIgnoreCase("DEFAULT_TRANSPARENT_IMG"))
				{
					transparentFlag = true;
					break;
				}
			}
		}
		if(!transparentFlag)
		{
			Enumeration en = hash.keys();
			{
				while (en.hasMoreElements())
				{
					String sev = en.nextElement().toString();
					if(sev.equals("TYPE") || sev.equals("MENU"))
					{
						continue;
					}
					else
					{
						addRow(sev.substring(0,sev.indexOf("_")),hash.get(sev).toString());
					}
				}
			}
		}
		else
		{
			addRow("TRANSPARENT IMAGE",hash.get("DEFAULT_TRANSPARENT_IMG").toString());
		}
					
		typeText.setText(hash.get("TYPE").toString());
		menuText.setText(hash.get("MENU").toString());
	}

	public void addRow(String name,String image)
	{
			Vector v =new Vector();
			v.add(name);
			v.add(image);
			if(image.equals(""))
			{}
			else
				v.add(uiUtils.getImage(image,"icons"));
			tableModel.addRow(v);
			table.setDefaultEditor(Object.class,null);
	 }


 
//<Begin__class_closeButton_closeButton_conn1>

 class closeButton_closeButton_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_closeButton_closeButton_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  ListIconDetailsDialog.this.setVisible(false);
  ListIconDetailsDialog.this.dispose(); 
     }
//<UserCode_End_Connection_closeButton_closeButton_conn1>
 }//<End__class_closeButton_closeButton_conn1>

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}





















