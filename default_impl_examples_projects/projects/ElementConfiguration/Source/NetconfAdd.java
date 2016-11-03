//$Id: NetconfAdd.java,v 1.1.4.1 2012/05/29 06:26:56 karen.r Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// Kindly refer to tcats_edit_source_files.html file under help/source_generation directory for details




//<Begin_Version>
//version$40
//<End_Version>
package com.adventnet.nms.config;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Vector;
import
 javax.swing.tree.*;




public class NetconfAdd extends JDialog
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "ElementConfigurationResources"; //No I18N
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel31 = null;
	javax.swing.JButton JButton2 = null;
	javax.swing.JButton JButton3 = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JPanel JPanel4 = null;
	javax.swing.JTree JTree1 = null;
	com.adventnet.beans.panels.CardPanel CardPanel1 = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.ButtonGroup ButtonGroup1 = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>

	DefaultTreeModel treeModel = null;
	DefaultMutableTreeNode rootNode = null;







	private ConfigPanel configPanel = null;
	private NetconfPanel mainPanel = null;
	private boolean isModify = false;

	public NetconfAdd(ConfigPanel configPanel, NetconfPanel mainPanel)
	{
		super(configPanel.configClientUtils.getParentFrame(mainPanel));

		this.configPanel = configPanel;
		this.mainPanel = mainPanel;

		applet = configPanel.applet;

		init();
		configInit();
	}

	public NetconfAdd(ConfigPanel configPanel,NetconfPanel mainPanel, Vector rowData)
	{
		super(configPanel.configClientUtils.getParentDialog(mainPanel));

		this.configPanel = configPanel;
		this.mainPanel = mainPanel;

		isModify = true;

		applet = configPanel.applet;

		init();
		configInit();

		populateData(rowData);
	}

	private void configInit()
	{
		java.lang.String[]  CardPanel1cardAndClassNames_array = new java.lang.String[ 4 ];
		
CardPanel1cardAndClassNames_array[ 0 ] = resourceBundle.getString("getPanel=com.adventnet.nms.config.GetConfigPanel");//No I18N
           		CardPanel1cardAndClassNames_array[ 1 ] = resourceBundle.getString("editPanel=com.adventnet.nms.config.EditConfigPanel");//No I18N
            		CardPanel1cardAndClassNames_array[ 2 ] = resourceBundle.getString("copyPanel=com.adventnet.nms.config.CopyConfigPanel");//No I18N
           		CardPanel1cardAndClassNames_array[ 3 ] = resourceBundle.getString("deletePanel=com.adventnet.nms.config.DeleteConfigPanel");//No I18N
            		CardPanel1.setCardAndClassNames(CardPanel1cardAndClassNames_array);
           		CardPanel1.setBorder(new javax.swing.border.SoftBevelBorder(0));
		CardPanel1.showCard("getPanel");//No I18N
		configPanel.configClientUtils.centerWindow(this);
	}





	private void populateData(Vector rowData)
	{
		String configOperation = (String)rowData.get(0);

		
if (configOperation.equals("get-config"))//No I18N
		{
			JTree1.setSelectionRow(1);
			((GetConfigPanel)CardPanel1.getCard("getPanel")).populateData(rowData);//No I18N
		}
		else if (configOperation.equals("edit-config"))//No I18N
		{
			JTree1.setSelectionRow(2);
			((EditConfigPanel)CardPanel1.getCard("editPanel")).populateData(rowData);//No I18N
		}
		else if (configOperation.equals("copy-config"))//No I18N
		{
			JTree1.setSelectionRow(3);
			((CopyConfigPanel)CardPanel1.getCard("copyPanel")).populateData(rowData);//No I18N
		}
		else if (configOperation.equals("delete-config"))//No I18N
		{
			JTree1.setSelectionRow(4);
			((DeleteConfigPanel)CardPanel1.getCard("deletePanel")).populateData(rowData);//No I18N
		}
	}


	public void okButtonActionPerformed()
	{

		 Vector rowData = new Vector();

	     int[] rows = JTree1.getSelectionRows();
	     if (rows==null || rows.length==0)
	     {
	         return;//this means no tree node is selected, so we take no action
	     }
	     int selected = rows[0];
	      if (selected == 1) // get-config
	     {
	    	 rowData = ((GetConfigPanel)(CardPanel1.getCard("getPanel"))).getRowData();//No I18N
	     }
	     else if (selected == 2) // edit-config
	     {
	    	 rowData = ((EditConfigPanel)(CardPanel1.getCard("editPanel"))).getRowData();//No I18N
	     }
	     else if(selected ==3) //copy-config
	     {
	    	 rowData = ((CopyConfigPanel)(CardPanel1.getCard("copyPanel"))).getRowData();//No I18N
	     }
	     else if (selected ==4) //delete-config
	     {
	    	 rowData = ((DeleteConfigPanel)(CardPanel1.getCard("deletePanel"))).getRowData();//No I18N
	     }
	     if(isModify)
	     {
	    	 mainPanel.updateRow(rowData);
	     }
	     else
	     {
	    	 mainPanel.addRow(rowData);
	     }

	     this.setVisible(true);
	     this.dispose();
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
	if(getParameter("RESOURCE_PROPERTIES" ) != null)//No I18N
	{
		localePropertiesFileName = getParameter("RESOURCE_PROPERTIES");//No I18N
	}
	resourceBundle = com.adventnet.apiutils.Utility.getBundle(localePropertiesFileName,getParameter("RESOURCE_LOCALE"),applet);//No I18N
        if (initialized) return; 
        this.setSize(getPreferredSize().width+650,getPreferredSize().height+500); 
          setTitle(resourceBundle.getString("Netconf Task Configuration"));//No I18N
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
          showStatus(resourceBundle.getString("Error in Initializing Netconf Task Configuration UI"),ex); //No I18N 
        } 
        // let us set the initialized variable to true so  
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
            if (input.equals("HOST")) value = "localhost"; //No I18N 
            if (input.equals("PORT")) value = "161"; //No I18N 
            if (input.equals("RESOURCE_PROPERTIES")) value = "ElementConfigurationResources"; //No I18N 
            }
        return value;

  
           //<End_getParameter_String>
  }
  public void setUpProperties()
  {
  //<Begin_setUpProperties> 

          try
          {
            JPanel31.setBorder(new javax.swing.border.EtchedBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel31,ex);//No I18N 
          }

//<UserCode_Begin_Bean_JPanel31>

//<UserCode_End_Bean_JPanel31>

          try
          {
            JButton2.setText(resourceBundle.getString("OK"));//No I18N
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton2,ex);//No I18N 
          }

//<UserCode_Begin_Bean_JButton2>

//<UserCode_End_Bean_JButton2>

          try
          {
            JButton3.setText(resourceBundle.getString("Cancel"));//No I18N
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton3,ex);//No I18N 
          }

//<UserCode_Begin_Bean_JButton3>

//<UserCode_End_Bean_JButton3>

          try
          {
            JPanel3.setBorder(new javax.swing.border.BevelBorder(0));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel3,ex); //No I18N 
          }

//<UserCode_Begin_Bean_JPanel3>

//<UserCode_End_Bean_JPanel3>

          try
          {
            JPanel4.setBorder(new javax.swing.border.SoftBevelBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel4,ex); //No I18N 
          }

//<UserCode_Begin_Bean_JPanel4>

//<UserCode_End_Bean_JPanel4>

          try
          {
            JTree1.setMinimumSize(new Dimension(120,150));
            JTree1.setMaximumSize(new Dimension(150,150));
            JTree1.setPreferredSize(new Dimension(125,150));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JTree1,ex); //No I18N 
          }

//<UserCode_Begin_Bean_JTree1>
rootNode = new DefaultMutableTreeNode("Config Operation");//No I18N
if(treeModel == null)
{
	treeModel = new DefaultTreeModel(rootNode);
	JTree1.setModel(treeModel);
}
JTree1.putClientProperty("JTree.lineStyle", "Angled");//No I18N
JTree1.setRowHeight(22);
TreePath path = null;
DefaultMutableTreeNode getNode = new DefaultMutableTreeNode("Get Config");//No I18N
rootNode.add(getNode);
if(path == null) path = new TreePath(treeModel.getPathToRoot(getNode));
DefaultMutableTreeNode editNode = new DefaultMutableTreeNode("Edit Config");//No I18N
rootNode.add(editNode);
DefaultMutableTreeNode copyNode = new DefaultMutableTreeNode("Copy Config");//No I18N
rootNode.add(copyNode);
DefaultMutableTreeNode deleteNode = new DefaultMutableTreeNode("Delete Config");//No I18N
rootNode.add(deleteNode);
treeModel.reload();
JTree1.scrollPathToVisible(path);
JTree1.setShowsRootHandles(true);
JTree1.setRootVisible(true);
JTree1.setSelectionRow(1);

//<UserCode_End_Bean_JTree1>

          try
          {
            CardPanel1.setBorder(new javax.swing.border.SoftBevelBorder(0));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+CardPanel1,ex); //No I18N 
          }

//<UserCode_Begin_Bean_CardPanel1>

//<UserCode_End_Bean_CardPanel1>

          try
          {
            JPanel1.setBorder(new javax.swing.border.EtchedBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel1,ex); //No I18N 
          }

//<UserCode_Begin_Bean_JPanel1>

//<UserCode_End_Bean_JPanel1>

          try
          {
            JLabel1.setText(resourceBundle.getString("Netconf Task Configuration"));//No I18N
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel1,ex);//No I18N 
          }

//<UserCode_Begin_Bean_JLabel1>

//<UserCode_End_Bean_JLabel1>
		JLabel1.setPreferredSize(new Dimension(JLabel1.getPreferredSize().width+3,JLabel1.getPreferredSize().height+3));
		JTree1.setPreferredSize(new Dimension(JTree1.getPreferredSize().width+0,JTree1.getPreferredSize().height+147));
		JButton2.setPreferredSize(new Dimension(JButton2.getPreferredSize().width+21,JButton2.getPreferredSize().height+0));

  
          //<End_setUpProperties>
  }
  public void initVariables()
  {
        //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        JPanel31= new javax.swing.JPanel();
        JButton2= new javax.swing.JButton();
        JButton3= new javax.swing.JButton();
        JPanel3= new javax.swing.JPanel();
        JPanel4= new javax.swing.JPanel();
        JTree1= new javax.swing.JTree();
        CardPanel1= new com.adventnet.beans.panels.CardPanel(applet);
        JPanel1= new javax.swing.JPanel();
        JLabel1= new javax.swing.JLabel();
        ButtonGroup1= new javax.swing.ButtonGroup();

  
        //<End_initVariables>
  }
  public void setUpGUI(Container container)
  {
//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(0,2,1,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel31,cons);
JPanel31.setLayout(new FlowLayout(1,5,5));
JPanel31.add(JButton2);
JPanel31.add(JButton3);
inset = new Insets(0,0,0,0);
setConstraints(0,1,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel3,cons);
JPanel3.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0015,0.1,cons.NORTHWEST,cons.VERTICAL,inset,0,0);
JPanel3.add(JPanel4,cons);
JPanel4.setLayout(new BorderLayout(5,5));
JPanel4.add(JTree1,BorderLayout.CENTER);
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.1,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel3.add(CardPanel1,cons);
inset = new Insets(0,0,0,0);
setConstraints(0,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel1,cons);
JPanel1.setLayout(new FlowLayout(1,5,5));
JPanel1.add(JLabel1);

  
//<End_setUpGUI_Container>
  }
  public void setUpConnections()
  {
  //<Begin_setUpConnections> 

      JButton3_JButton3_conn JButton3_JButton3_conn1 =  new JButton3_JButton3_conn();
      JButton3.addActionListener(JButton3_JButton3_conn1);
      JTree1_CardPanel1_conn JTree1_CardPanel1_conn1 =  new JTree1_CardPanel1_conn();
      JTree1.addTreeSelectionListener(JTree1_CardPanel1_conn1);
      JButton2_JButton2_conn JButton2_JButton2_conn1 =  new JButton2_JButton2_conn();
      JButton2.addActionListener(JButton2_JButton2_conn1);
  
      //<End_setUpConnections>
  }




  public void showStatus(String message)
  {
     //<Begin_showStatus_String>
     System.out.println("Internal Error :"+ message);//No I18N
     //<End_showStatus_String>
  }
  public void showStatus(String message,Exception ex)
  {
     //<Begin_showStatus_String_Exception>
     System.out.println("Internal Error :"+ message);//No I18N
     ex.printStackTrace();
     //<End_showStatus_String_Exception>
  }






   public void cancelButtonActionPerformed()
   {
        this.setVisible(false);
        this.dispose();
   }

  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
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




  public NetconfAdd()
  {
    //<Begin_NetconfAdd>
    pack();
  
    //<End_NetconfAdd>
  }

  public NetconfAdd(java.applet.Applet applet)
  {
    //<Begin_NetconfAdd_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_NetconfAdd_java.applet.Applet>
  }









//<Begin__class_JTree1_CardPanel1_conn>

 class JTree1_CardPanel1_conn implements javax.swing.event.TreeSelectionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JTree1_CardPanel1_conn>

     //Listener Interface Methods Are Added Below


     public void valueChanged( javax.swing.event.TreeSelectionEvent arg0)
     {
              DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                       JTree1.getLastSelectedPathComponent();

    	if (node == null)
    	{
    	     return;
    	 }

	if (node.isLeaf()) {
		String treeName = (String)node.getUserObject();
		
		if (treeName.equals("Get Config"))//No I18N
		{
		     CardPanel1.showCard("getPanel");//No I18N
		}
		else if (treeName.equals("Edit Config"))//No I18N
		{
		     CardPanel1.showCard("editPanel");//No I18N
		}
		else if (treeName.equals("Copy Config"))//No I18N
		{
		     CardPanel1.showCard("copyPanel");//No I18N
		}
		else if (treeName.equals("Delete Config"))//No I18N
		{
		     CardPanel1.showCard("deletePanel");//No I18N
		}
    	} else {
         		CardPanel1.showCard("getPanel");//No I18N
     	}

     }
//<UserCode_End_Connection_JTree1_CardPanel1_conn>
 }//<End__class_JTree1_CardPanel1_conn>




//<Begin__class_JButton3_JButton3_conn>

 class JButton3_JButton3_conn implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton3_JButton3_conn>

     //Listener Interface Methods Are Added Below


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
          	cancelButtonActionPerformed();
     }


//<UserCode_End_Connection_JButton3_JButton3_conn>
 }//<End__class_JButton3_JButton3_conn>


//<Begin__class_JButton2_JButton2_conn>

 class JButton2_JButton2_conn implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton2_JButton2_conn>
     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
          okButtonActionPerformed();
     }
//<UserCode_End_Connection_JButton2_JButton2_conn>
 }//<End__class_JButton2_JButton2_conn>

}

















