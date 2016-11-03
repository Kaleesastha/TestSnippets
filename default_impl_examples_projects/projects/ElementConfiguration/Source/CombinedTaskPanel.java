
//$Id: CombinedTaskPanel.java,v 1.2 2007/02/22 15:03:04 srajeswari Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.config;


import java.util.*;

import javax.swing.table.*;
import javax.swing.tree.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class CombinedTaskPanel extends JPanel 
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "ElementConfigurationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel5 = null;
	javax.swing.JPanel JPanel6 = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	javax.swing.JTree tree = null;
	javax.swing.JPanel JPanel7 = null;
	javax.swing.JScrollPane JScrollPane2 = null;
	javax.swing.JTable JTable1 = null;
	javax.swing.JButton JButton1 = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.table.DefaultTableModel tableModel = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>

	private	ConfigPanel configPanel = null;

	DefaultMutableTreeNode rootNode = null;

	DefaultTreeModel model = null;	

	public CombinedTaskPanel(ConfigPanel configPanel)
	{	
		this.configPanel = configPanel;

		applet  = configPanel.applet;

		init();
		configInit();
	}

	private void configInit()
	{
		JButton button = new JButton(resourceBundle.getString("Available Sub-Task"));
		button.setFocusPainted(false);
		button.setBackground(new Color(0,102,102));
		button.setForeground(Color.white);
		button.setFont(new Font("dialog",Font.BOLD,13));
		JViewport viewPort = new JViewport();
		viewPort.setView(button);
		JScrollPane1.setColumnHeader(viewPort);
		tree.setShowsRootHandles(true);
		tree.setRootVisible(false);
		JTableHeader header = JTable1.getTableHeader();
		//header.setPreferredSize(new Dimension((int)(JTable1.getPreferredSize()).width,22));
		//header.setMinimumSize(new Dimension((int)(JTable1.getMinimumSize()).width,22));		
		header.setPreferredSize(new Dimension(0,0));
		header.setMinimumSize(new Dimension(0,0));				
		header.setMaximumSize(new Dimension(0,0));					
		JButton1.setBackground(new Color(0,102,102));
		JButton1.setFont(new Font("dialog",Font.BOLD,13));		
		header.setVisible(false);
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
            JPanel6.setBorder(new javax.swing.border.BevelBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel6,ex); 
          }

//<UserCode_Begin_Bean_JPanel6>

//<UserCode_End_Bean_JPanel6>

          try
          {
            JPanel7.setBorder(new javax.swing.border.BevelBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel7,ex); 
          }

//<UserCode_Begin_Bean_JPanel7>

//<UserCode_End_Bean_JPanel7>

          try
          {
            JScrollPane2.setBorder(new javax.swing.border.BevelBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JScrollPane2,ex); 
          }

//<UserCode_Begin_Bean_JScrollPane2>

//<UserCode_End_Bean_JScrollPane2>

          try
          {
            JTable1.setModel(tableModel);
            JTable1.setRowHeight(21);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JTable1,ex); 
          }

//<UserCode_Begin_Bean_JTable1>

//<UserCode_End_Bean_JTable1>

          try
          {
            JButton1.setText(resourceBundle.getString("Sub-Task Details"));
            JButton1.setForeground(new Color(-1));
            JButton1.setFocusPainted(false);
            JButton1.setBackground(new Color(-16751002));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton1,ex); 
          }

//<UserCode_Begin_Bean_JButton1>

//<UserCode_End_Bean_JButton1>

          try
          {
            JPanel2.setBorder(new javax.swing.border.EtchedBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel2,ex); 
          }

//<UserCode_Begin_Bean_JPanel2>

//<UserCode_End_Bean_JPanel2>

          try
          {
            JLabel1.setText(resourceBundle.getString("Combined Task Panel"));
            JLabel1.setForeground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel1,ex); 
          }

//<UserCode_Begin_Bean_JLabel1>

//<UserCode_End_Bean_JLabel1>

  
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
        setPreferredSize(new Dimension(getPreferredSize().width+573,getPreferredSize().height+451)); 
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

		JTable1.setDefaultRenderer(Object.class,new SimpleTableSelectionRenderer());
		JTable1.setDefaultEditor(Object.class,null);
		constructTree();
	} 



	public void constructTree()
	{
		rootNode = new DefaultMutableTreeNode(new MultipleListSelectionObject(resourceBundle.getString("Protocols")));

		Hashtable allTasks = configPanel.configMainUI.getAllTasks();

		for(Enumeration enumerate = allTasks.keys(); enumerate.hasMoreElements(); )
		{
			String proto = (String)enumerate.nextElement();

			Vector tasks = (Vector)allTasks.get(proto);

			DefaultMutableTreeNode protoNode = new DefaultMutableTreeNode(new MultipleListSelectionObject(resourceBundle.getString(proto.toUpperCase())));

			rootNode.add(protoNode);

			for(int i=0; i<tasks.size(); i++)
			{
				String tskName = (String)tasks.elementAt(i);					

				if(!configPanel.configMainUI.isTemplate(tskName))
				{
					protoNode.add(new DefaultMutableTreeNode(new MultipleListSelectionObject(tskName)));
				}
			}
		}

		model = new DefaultTreeModel(rootNode);
		tree.setModel(model);
		//tree.setCellRenderer(new TreeRendererForCombinedTask(configPanel));
		tree.setCellRenderer(new CombinedTaskRenderer(configPanel));
		tree.putClientProperty("JTree.lineStyle", "Angled");

		Vector columnVector = new Vector();
		columnVector.addElement(resourceBundle.getString("Name"));
		columnVector.addElement(resourceBundle.getString("Value"));
		Vector dataVector = new Vector();
		for(int i=0;i<6;i++)
		{
			Vector v = new Vector();
			if(i == 0)
				v.addElement(resourceBundle.getString("Task Name"));
			else if(i == 1)
				v.addElement(resourceBundle.getString("Task Description"));
			else if(i == 2)
				v.addElement(resourceBundle.getString("Protocol"));
			else if(i == 3)
				v.addElement(resourceBundle.getString("Template"));
			else if(i == 4)
				v.addElement(resourceBundle.getString("Rollback"));
			else
				v.addElement(resourceBundle.getString("Last Execution Time"));
			v.addElement("");
			dataVector.addElement(v);
		}
		tableModel.setDataVector(dataVector,columnVector);



	}



	public void setUpConnections()
  { 

		//<Begin_setUpConnections> 

      tree_tree_conn1 tree_tree_conn11 =  new tree_tree_conn1();
      tree.addMouseListener(tree_tree_conn11);
      JPanel2_JLabel1_conn1 JPanel2_JLabel1_conn11 =  new JPanel2_JLabel1_conn1();
      JPanel2.addMouseListener(JPanel2_JLabel1_conn11);
  
      //<End_setUpConnections>
	} 
	public void initVariables()
  { 

		//<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        JPanel5= new javax.swing.JPanel();
        JPanel6= new javax.swing.JPanel();
        JScrollPane1= new javax.swing.JScrollPane();
        tree= new javax.swing.JTree();
        JPanel7= new javax.swing.JPanel();
        JScrollPane2= new javax.swing.JScrollPane();
        JTable1= new javax.swing.JTable();
        JButton1= new javax.swing.JButton();
        JPanel2= new javax.swing.JPanel();
        JLabel1= new javax.swing.JLabel();
        tableModel= new javax.swing.table.DefaultTableModel();

  
        //<End_initVariables>
	} 

	public void setUpGUI(Container container)
  { 

		//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(JPanel5,cons);
JPanel5.setLayout(new GridLayout(1,2,5,5));
JPanel5.add(JPanel6);
JPanel6.setLayout(new BorderLayout(5,5));
JPanel6.add(JScrollPane1,BorderLayout.CENTER);
JScrollPane1.getViewport().add(tree);
JPanel5.add(JPanel7);
JPanel7.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(0,1,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel7.add(JScrollPane2,cons);
JScrollPane2.getViewport().add(JTable1);
inset = new Insets(0,0,0,0);
setConstraints(0,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel7.add(JButton1,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel2,cons);
JPanel2.setLayout(new FlowLayout(1,5,5));
JPanel2.add(JLabel1);

  
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
            if (input.equals("RESOURCE_PROPERTIES")) value = "ElementConfigurationResources"; 
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






	public CombinedTaskPanel()
  {
		//<Begin_CombinedTaskPanel>
    this.init();
  
    //<End_CombinedTaskPanel>
	}

	public CombinedTaskPanel(java.applet.Applet applet)
  {
		//<Begin_CombinedTaskPanel_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_CombinedTaskPanel_java.applet.Applet>
	}






	//<Begin__class_tree_tree_conn1>

 class tree_tree_conn1 extends java.awt.event.MouseAdapter implements java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_tree_tree_conn1>



     //Listener Interface Methods Are Added Below 


     public void mousePressed( java.awt.event.MouseEvent arg0)
     {
  treeMousePressedEvent(arg0);
     }
//<UserCode_End_Connection_tree_tree_conn1>
 }//<End__class_tree_tree_conn1>

	public void treeMousePressedEvent(MouseEvent me)
	{
		Point point = me.getPoint();
		int x = (int)point.getX();
		int y = (int)point.getY();
		int row = tree.getClosestRowForLocation(x,y);
		TreePath path = tree.getPathForRow(row);
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)path.getLastPathComponent();
		MultipleListSelectionObject object = (MultipleListSelectionObject)node.getUserObject();

		if(object.getState())
		{
			object.setFalseState();
		}

		else
		{
			object.setTrueState();
		}

		fillCorrespondingDetails(configPanel.configMainUI.getTaskDetails(node.toString()));

		tree.repaint();
	}


	//<Begin__class_JPanel2_JLabel1_conn1>

 class JPanel2_JLabel1_conn1 extends java.awt.event.MouseAdapter implements java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JPanel2_JLabel1_conn1>



     //Listener Interface Methods Are Added Below 


     public void mousePressed( java.awt.event.MouseEvent arg0)
     {
  labelMousePressedEvent();
     }
//<UserCode_End_Connection_JPanel2_JLabel1_conn1>
 }//<End__class_JPanel2_JLabel1_conn1>

	public void labelMousePressedEvent()
	{
	}

	public Vector getSelectedTasks()
	{
		Vector selectedTasks = new Vector();
		for(int i = 0; i < rootNode.getChildCount(); i++)	
		{
			DefaultMutableTreeNode node = (DefaultMutableTreeNode)rootNode.getChildAt(i);
			MultipleListSelectionObject obj = (MultipleListSelectionObject)node.getUserObject();

			for(int j = 0; j < node.getChildCount(); j++)
			{
				DefaultMutableTreeNode node1 = (DefaultMutableTreeNode)node.getChildAt(j);
				MultipleListSelectionObject object = (MultipleListSelectionObject)node1.getUserObject();
				if(object.getState())
				{
					selectedTasks.addElement(node1.toString());
				}
			}

		}
		return selectedTasks;
	}
	private void fillCorrespondingDetails(Vector details)
	{
		if(details != null && details.size() > 0) 
		{
			for(int i = 0; i < details.size(); i++)
			{
				tableModel.setValueAt(details.elementAt(i),i,1);				
			}		
		}
		else
		{
			for(int i = 0; i < 6; i++)
			{
				tableModel.setValueAt("",i,1);				
			}
		}
	}

	public void setValues(Vector details)
	{
		DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode)model.getRoot();
		int noOfChilds = rootNode.getChildCount();
		for(int i = 0; i < noOfChilds; i++)
		{
			DefaultMutableTreeNode protocolChild = (DefaultMutableTreeNode)rootNode.getChildAt(i);
			if(! protocolChild.isLeaf())
			{
				int noOfSubChilds = protocolChild.getChildCount();
				for(int j = 0; j < noOfSubChilds; j++)
				{
					DefaultMutableTreeNode taskName = (DefaultMutableTreeNode)protocolChild.getChildAt(j);
					String task = taskName.toString();
					for(int k = 0; k < details.size(); k++)
					{
						String element = (String)details.elementAt(k);
						if(element.equals(task))
						{

							MultipleListSelectionObject object = (MultipleListSelectionObject)taskName.getUserObject();
							object.setTrueState();
						}
					}
				}
			}
		}
	}


 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}


