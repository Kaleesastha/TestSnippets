
//$Id: Operations.java,v 1.1 2006/08/29 13:57:02 build Exp $
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
import javax.swing.tree.*;
import java.util.*;


public class Operations extends JDialog implements SecurityCommonInterface
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "SecurityAdministrationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JButton JButton1 = null;
	javax.swing.JButton JButton3 = null;
	javax.swing.JButton setOpButton = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	javax.swing.JTree JTree1 = null;
	javax.swing.JPanel JPanel4 = null;
	javax.swing.JTextField addText = null;
	javax.swing.JButton addButton = null;
	javax.swing.JButton removeButton = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JLabel JLabel2 = null;
	//<End_Variable_Declarations>

    	DefaultTreeModel treeModel = null;

  
   public static boolean runonce = false;

    

     





   


  

  

 
  public void setUpProperties()
  { 

  //<Begin_setUpProperties> 

          try
          {
            Top.setBorder(new javax.swing.border.EtchedBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+Top,ex); 
          }

//<UserCode_Begin_Bean_components>
//setResizable(false);
//<UserCode_End_Bean_components>

          try
          {
            JButton1.setFont(new Font("Dialog",0,13));
            JButton1.setText(resourceBundle.getString("Ok"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton1,ex); 
          }

//<UserCode_Begin_Bean_JButton1>
JButton1.setMnemonic('O');
//<UserCode_End_Bean_JButton1>

          try
          {
            JButton3.setFont(new Font("Dialog",0,13));
            JButton3.setText(resourceBundle.getString("Cancel"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JButton3,ex); 
          }

//<UserCode_Begin_Bean_JButton3>
JButton3.setMnemonic('C');
//<UserCode_End_Bean_JButton3>

          try
          {
            setOpButton.setToolTipText(resourceBundle.getString("Set Operations"));
            setOpButton.setText(resourceBundle.getString("Apply"));
            setOpButton.setFont(new Font("Dialog",0,14));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+setOpButton,ex); 
          }

//<UserCode_Begin_Bean_setOpButton>
setOpButton.setMnemonic('A');
//<UserCode_End_Bean_setOpButton>

          try
          {
            JScrollPane1.setBorder(new javax.swing.border.SoftBevelBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JScrollPane1,ex); 
          }

//<UserCode_Begin_Bean_JScrollPane1>

//<UserCode_End_Bean_JScrollPane1>

          try
          {
            JTree1.setRootVisible(true);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JTree1,ex); 
          }

//<UserCode_Begin_Bean_JTree1>

//<UserCode_End_Bean_JTree1>

          try
          {
            JPanel4.setBorder(new javax.swing.border.BevelBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel4,ex); 
          }

//<UserCode_Begin_Bean_JPanel4>

//<UserCode_End_Bean_JPanel4>

          try
          {
            addButton.setText(resourceBundle.getString("Add"));
            addButton.setFont(new Font("Dialog",0,13));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+addButton,ex); 
          }

//<UserCode_Begin_Bean_addButton>
addButton.setMnemonic('A');
//<UserCode_End_Bean_addButton>

          try
          {
            removeButton.setText(resourceBundle.getString("Remove"));
            removeButton.setFont(new Font("Dialog",0,13));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+removeButton,ex); 
          }

//<UserCode_Begin_Bean_removeButton>
removeButton.setMnemonic('R');
//<UserCode_End_Bean_removeButton>

          try
          {
            JLabel1.setFont(new Font("Dialog",0,12));
            JLabel1.setForeground(new Color(-16764109));
            JLabel1.setText(resourceBundle.getString("The following tree displays the whole hierarchy of operations that can be authorized for users."));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel1,ex); 
          }

//<UserCode_Begin_Bean_JLabel1>

//<UserCode_End_Bean_JLabel1>

          try
          {
            JLabel2.setFont(new Font("Dialog",0,12));
            JLabel2.setForeground(new Color(-16764109));
            JLabel2.setText(resourceBundle.getString("Select a tree node to add an operation under it, click on apply to save changes to server"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel2,ex); 
          }

//<UserCode_Begin_Bean_JLabel2>

//<UserCode_End_Bean_JLabel2>
		JPanel1.setPreferredSize(new Dimension(JPanel1.getPreferredSize().width+10,JPanel1.getPreferredSize().height+10));
		removeButton.setPreferredSize(new Dimension(removeButton.getPreferredSize().width+6,removeButton.getPreferredSize().height+0));
		addButton.setPreferredSize(new Dimension(addButton.getPreferredSize().width+20,addButton.getPreferredSize().height+0));
		addText.setPreferredSize(new Dimension(addText.getPreferredSize().width+241,addText.getPreferredSize().height+6));
		JPanel4.setPreferredSize(new Dimension(JPanel4.getPreferredSize().width+13,JPanel4.getPreferredSize().height+0));
		JScrollPane1.setPreferredSize(new Dimension(JScrollPane1.getPreferredSize().width+0,JScrollPane1.getPreferredSize().height+1));
		JPanel3.setPreferredSize(new Dimension(JPanel3.getPreferredSize().width+10,JPanel3.getPreferredSize().height+38));
		setOpButton.setPreferredSize(new Dimension(setOpButton.getPreferredSize().width+6,setOpButton.getPreferredSize().height+0));
		JButton3.setPreferredSize(new Dimension(JButton3.getPreferredSize().width+4,JButton3.getPreferredSize().height+1));
		JButton1.setPreferredSize(new Dimension(JButton1.getPreferredSize().width+24,JButton1.getPreferredSize().height+1));
		JPanel2.setPreferredSize(new Dimension(JPanel2.getPreferredSize().width+306,JPanel2.getPreferredSize().height+0));

  
          //<End_setUpProperties>

            JPanel3.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("Operation Tree Configuration")));
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
        this.setSize(getPreferredSize().width+529,getPreferredSize().height+501); 
          setTitle(resourceBundle.getString("Operations"));
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
		
		          setTitle(resourceBundle.getString("Operations"));
		if(!AuthMain.standalone)
		{
			JTree1.setRootVisible(false);
		}
		else
		{
			JTree1.setRootVisible(true);
		}
		//setIconImage(AuthMain.getBuilderUiIfInstance().getFrameIcon());	
		AuthMain.getBuilderUiIfInstance().centerWindow(this);
                    	DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
		renderer.setLeafIcon(AuthMain.getBuilderUiIfInstance().getImage("circle.png"));
		renderer.setOpenIcon(AuthMain.getBuilderUiIfInstance().getImage("circle.png"));
		renderer.setClosedIcon(AuthMain.getBuilderUiIfInstance().getImage("circle.png"));
		JTree1.setCellRenderer(renderer);
		JViewport jv = new JViewport();
		JButton jb = new JButton(resourceBundle.getString("Operations Tree"));
		jb.setFocusPainted(false);
		jb.setToolTipText(resourceBundle.getString("Expand / Collapse Tree"));
		//for(int i=0;i<JTree1.getRowCount();i++)
	   	
		jb.addActionListener(new ActionListener()  
										{ 
												int count = 2;
		  										public void actionPerformed(java.awt.event.ActionEvent ev)					
													{														
													if((count % 2)!=0)	
												    		{
													for(int i=1;i<JTree1.getRowCount();i++)
	  										 		JTree1.expandRow(i);
													count+=1;
												 		}	
													else
												  		{
													for(int i=1;i<JTree1.getRowCount()-1;i++)
	  												JTree1.collapseRow(i);
													count+=1;
													  	}
													}			
									    	});
  	jv.setView(jb);
 	JScrollPane1.setColumnHeader(jv);
			

		addWindowListener(new WindowAdapter()
						{
							public void windowClosing(WindowEvent we)
								{
									close();
								}
						}
					   );

	//fireDataChanged();
	JTree1.setModel(model.getTreeModel());	
	//System.out.println("LEAD SELECTED ROW "+JTree1.getLeadSelectionRow());
	for(int i=0;i<JTree1.getRowCount();i++)	
	JTree1.expandRow(i);			
 
}
  public void setUpConnections()
  { 

  //<Begin_setUpConnections> 

      addButton_addButton_conn1 addButton_addButton_conn11 =  new addButton_addButton_conn1();
      addButton.addActionListener(addButton_addButton_conn11);
      JButton3_JButton3_conn1 JButton3_JButton3_conn11 =  new JButton3_JButton3_conn1();
      JButton3.addActionListener(JButton3_JButton3_conn11);
      setOpButton_setOpButton_conn1 setOpButton_setOpButton_conn11 =  new setOpButton_setOpButton_conn1();
      setOpButton.addActionListener(setOpButton_setOpButton_conn11);
      JTree1_JTree1_conn1 JTree1_JTree1_conn11 =  new JTree1_JTree1_conn1();
      JTree1.addTreeSelectionListener(JTree1_JTree1_conn11);
      removeButton_removeButton_conn1 removeButton_removeButton_conn11 =  new removeButton_removeButton_conn1();
      removeButton.addActionListener(removeButton_removeButton_conn11);
      JButton1_JButton1_conn1 JButton1_JButton1_conn11 =  new JButton1_JButton1_conn1();
      JButton1.addActionListener(JButton1_JButton1_conn11);
  
      //<End_setUpConnections>
  } 
  public void initVariables()
  { 
	if(false)
		{
      //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        JPanel2= new javax.swing.JPanel();
        JButton1= new javax.swing.JButton();
        JButton3= new javax.swing.JButton();
        setOpButton= new javax.swing.JButton();
        JPanel3= new javax.swing.JPanel();
        JScrollPane1= new javax.swing.JScrollPane();
        JTree1= new javax.swing.JTree();
        JPanel4= new javax.swing.JPanel();
        addText= new javax.swing.JTextField();
        addButton= new javax.swing.JButton();
        removeButton= new javax.swing.JButton();
        JPanel1= new javax.swing.JPanel();
        JLabel1= new javax.swing.JLabel();
        JLabel2= new javax.swing.JLabel();

  
        //<End_initVariables>
         }	
		
        Top= new javax.swing.JPanel();
        JPanel2= new javax.swing.JPanel();
        JButton1= new javax.swing.JButton();
        JButton3= new javax.swing.JButton();
        setOpButton= new javax.swing.JButton();
        JPanel3= new javax.swing.JPanel();
        JScrollPane1= new javax.swing.JScrollPane();
        JPanel4= new javax.swing.JPanel();
        addText= new javax.swing.JTextField();
        addButton= new javax.swing.JButton();
        removeButton= new javax.swing.JButton();
        JPanel1= new javax.swing.JPanel();
        JLabel1= new javax.swing.JLabel();
        JLabel2= new javax.swing.JLabel();
           Object previousExpanded = UIManager.put("Tree.expandedIcon",AuthMain.getBuilderUiIfInstance().getImage("expand3.png"));
           Object previousCollapsed = UIManager.put("Tree.collapsedIcon", AuthMain.getBuilderUiIfInstance().getImage("collapse3.png"));
       JTree1= new javax.swing.JTree();
       UIManager.put("Tree.expandedIcon", previousExpanded);
       UIManager.put("Tree.collapsedIcon", previousCollapsed);	
       JTree1.putClientProperty("JTree.lineStyle", "Angled");			
		

  } 
   
  public void setUpGUI(Container container)
  { 

  //<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));
Top.add(JPanel2,BorderLayout.SOUTH);
JPanel2.setLayout(new FlowLayout(2,5,5));
JPanel2.add(JButton1);
JPanel2.add(JButton3);
JPanel2.add(setOpButton);
Top.add(JPanel3,BorderLayout.CENTER);
JPanel3.setLayout(new BorderLayout(5,5));
JPanel3.add(JScrollPane1,BorderLayout.CENTER);
JScrollPane1.getViewport().add(JTree1);
JPanel3.add(JPanel4,BorderLayout.SOUTH);
JPanel4.setLayout(new FlowLayout(2,5,5));
JPanel4.add(addText);
JPanel4.add(addButton);
JPanel4.add(removeButton);
JPanel3.add(JPanel1,BorderLayout.NORTH);
JPanel1.setLayout(new GridLayout(2,1,10,10));
JPanel1.add(JLabel1);
JPanel1.add(JLabel2);

  
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




	
     DefaultMutableTreeNode node1 = null; 
	boolean flag = true;
	public void addValue(String str)
	 {
	  DefaultMutableTreeNode def = new DefaultMutableTreeNode(str);
	  node1.add(def);
	  //model.addOperation(node1.getUserObject().toString(),str,"0");
	  Object o = (Object)node1.getUserObject();
	  if(o instanceof TreeObject)
	 {
	       model.addOperation((((TreeObject)o).getString()),str,"0");
	 }
	 else
	 {
	    model.addOperation(o.toString(),str,"0");
	 }
 	 JTree1.updateUI();	
	  flag = false;	
	 }	
	
	

  public void fillData()
	{

		DefaultMutableTreeNode root  = new DefaultMutableTreeNode();
		treeModel = new DefaultTreeModel(root);
        	           JTree1.setModel(treeModel);
		
	}	                      

 private boolean search = false;
	

public void find(DefaultMutableTreeNode rootnode, Object obj)
	{

	   java.util.Vector vec = new java.util.Vector();
	   for(int i=0;i<rootnode.getChildCount();i++)
			    {
			 	   DefaultMutableTreeNode node1 = (DefaultMutableTreeNode)rootnode.getChildAt(i);
 				   vec.add(node1.getUserObject().toString());
				   Object o = (Object)node1.getUserObject();
				   if( o instanceof TreeObject)
				   {
				   	vec.add(((TreeObject)o).getString());
				   }
 			    	   find(node1,obj);
			    }
	   if(vec.contains(obj.toString()))	
	    {
		search = true;
           	return ;
	    }

	}

     
private AbstractSecurityModel model = null;	
 
public void setSecurityModel(AbstractSecurityModel sModel)
	{
 		model = sModel	;	
	}	
	
 public void setBuilderUiImpl(com.adventnet.nms.util.CommonBuilderUIImpl uiImpl)
	{
		
	}
	
 public void fireDataChanged()
 	{	
 	       JTree1.setModel(model.getTreeModel());
	       for(int i=0;i<JTree1.getRowCount();i++)
	       JTree1.expandRow(i);
 	}	
	
public void close()
	{
	     	//AdvPropScreen.getInstance().enableButtons();
		runonce = false;
		model = null;
		//removeAll();
		dispose();
	}

public void showError(String error)
	{
		Object[] options =
 {resourceBundle.getString("OK")};
          JOptionPane.showOptionDialog (null,
                                  resourceBundle.getString(error),
                                  resourceBundle.getString("Error Message"),
                                  JOptionPane.DEFAULT_OPTION,
                                  JOptionPane.WARNING_MESSAGE,
                                  null, options, options[0]);

	}		
	
 
   public void registerWithModel()
	{
		model.registerWithModel(this);
	}	

                        


//<Begin__class_setOpButton_setOpButton_conn1>

 class setOpButton_setOpButton_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_setOpButton_setOpButton_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
model.setOperations();   
     }
//<UserCode_End_Connection_setOpButton_setOpButton_conn1>
 }//<End__class_setOpButton_setOpButton_conn1>
                        


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
//<Begin__class_JButton3_JButton3_conn1>

 class JButton3_JButton3_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton3_JButton3_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
 
close();
  
     }
//<UserCode_End_Connection_JButton3_JButton3_conn1>
 }//<End__class_JButton3_JButton3_conn1>
                



 
//<Begin__class_addButton_addButton_conn1>

 class addButton_addButton_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_addButton_addButton_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
if(JTree1.isSelectionEmpty() ) 
 {
Utilities.errorMessage(resourceBundle.getString("Please select the node under which you want \nto add an operation")); 
return;
 }  
if(addText.getText().equals(""))
 {
  return; 
 }  
  
 search = false;
 find(((DefaultMutableTreeNode)JTree1.getModel( ).getRoot()) , addText.getText()); 
 if(!search)
 {  
    DefaultMutableTreeNode def = new DefaultMutableTreeNode(addText.getText()); 
    addValue(addText.getText());    
     addText.setText("" );  
 }
 else
 {
 Utilities.errorMessage(resourceBundle.getString("Operation Already Added"));   
 }     
   
     }
//<UserCode_End_Connection_addButton_addButton_conn1>
 }//<End__class_addButton_addButton_conn1>


//<Begin__class_removeButton_removeButton_conn1>

 class removeButton_removeButton_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_removeButton_removeButton_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
        String leaf = "0";
        if(JTree1.isSelectionEmpty())
        {
             Utilities.errorMessage(resourceBundle.getString("Please select the operation which is to be removed"));   
             return;
        }
        
        if( JOptionPane.showConfirmDialog(null,
                                          resourceBundle.getString( "Do you really want to delete this operation ?"),
                                          resourceBundle.getString("Warning!"),
                                           JOptionPane.YES_NO_OPTION,
                                          JOptionPane.WARNING_MESSAGE,
                                           null) == JOptionPane.YES_OPTION) 
        {
             DefaultMutableTreeNode node = (DefaultMutableTreeNode) JTree1.getLastSelectedPathComponent();
             DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode)node.getParent();
             //String parent = parentNode.getUserObject().toString();
             String parent = null;
             Object o = (Object)parentNode.getUserObject();
             if(o instanceof TreeObject)
             {
                  parent = ((TreeObject)o).getString();    
             }
             else
             {
                  parent = o.toString();
             }
             
             if(!node.isLeaf())
             {
                  if( JOptionPane.showConfirmDialog(null,
                                             resourceBundle.getString("This will delete its child operations as well "),
                                             resourceBundle.getString("Warning!"),
                                              JOptionPane.OK_CANCEL_OPTION,
                                            JOptionPane.WARNING_MESSAGE,
                                             null) == JOptionPane.OK_OPTION) 
                  {
                       leaf = "1";
                       ((DefaultTreeModel)JTree1.getModel()).removeNodeFromParent((DefaultMutableTreeNode)node);
                       Object ob = (Object)node.getUserObject();
                       String theChild = null;
                       if(ob instanceof TreeObject)
                       {
                            theChild = ((TreeObject)ob).getString(); 
                       }
                       else
                       {
                            theChild = ob.toString();
                       }
                       model.removeOperation(parent,theChild,leaf);
                       JTree1.updateUI(); 
                  }
             }
             else
             {
                  leaf = "0";
                  ((DefaultTreeModel)JTree1.getModel()).removeNodeFromParent((DefaultMutableTreeNode)node);
                  //model.removeOperation(parent,node.getUserObject().toString(),leaf);
                  Object ob = (Object)node.getUserObject();
                  String theChild = null;
                  if(ob instanceof TreeObject)
                  {
                       theChild = ((TreeObject)ob).getString();
                  }
                  else
                  {
                       theChild = ob.toString();
                  }
                  
                  model.removeOperation(parent,theChild,leaf); 
                  JTree1.updateUI();
             }
        }
       
     }
//<UserCode_End_Connection_removeButton_removeButton_conn1>
 }//<End__class_removeButton_removeButton_conn1>


//<Begin__class_JTree1_JTree1_conn1>

 class JTree1_JTree1_conn1 implements javax.swing.event.TreeSelectionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JTree1_JTree1_conn1>



     //Listener Interface Methods Are Added Below 


   public void valueChanged( javax.swing.event.TreeSelectionEvent arg0)
     {
       
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                           JTree1.getLastSelectedPathComponent();
   
      if (node == null) return;
      node1 = node;
      Object nodeInfo = node.getUserObject();
 
  
 JTree1.expandRow(JTree1.getMaxSelectionRow()); 
 flag =true; 
   
     }
//<UserCode_End_Connection_JTree1_JTree1_conn1>
 }//<End__class_JTree1_JTree1_conn1>


   


  

  public Operations(java.applet.Applet applet)
  {
    //<Begin_Operations_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_Operations_java.applet.Applet>
  }
	
	
  public Operations(Frame owner,java.applet.Applet applet)
  {
    super(owner);		
    this.applet = applet;
    pack();
    this.setTitle("Operations");
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
     
  }	


 //<Begin__class_JButton1_JButton1_conn1>

 class JButton1_JButton1_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton1_JButton1_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  model.setOperations();
 close();
     }
//<UserCode_End_Connection_JButton1_JButton1_conn1>
 }//<End__class_JButton1_JButton1_conn1>

	





  public Operations()
  {
    //<Begin_Operations>
    pack();
  
    //<End_Operations>
  }

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}


































































