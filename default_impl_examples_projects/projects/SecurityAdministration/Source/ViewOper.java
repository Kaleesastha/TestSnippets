
//$Id: ViewOper.java,v 1.1 2006/08/29 13:57:02 build Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.security.ui;




import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.tree.*;
import com.adventnet.nms.util.CommonBuilderUIInterface;


public class ViewOper extends JPanel 
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "SecurityAdministrationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JLabel JLabel2 = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	javax.swing.JTree JTree1 = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JLabel JLabel3 = null;
	javax.swing.JLabel JLabel4 = null;
	javax.swing.JLabel JLabel5 = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>

public Vector vec = null;
  
  public ViewOper()
  {
    //<Begin_ViewOper>
    this.init();
  
    //<End_ViewOper>
  }

  public ViewOper(java.applet.Applet applet)
  {
    //<Begin_ViewOper_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_ViewOper_java.applet.Applet>
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
            JLabel1.setMinimumSize(new Dimension(110,325));
            JLabel1.setPreferredSize(new Dimension(110,325));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel1,ex); 
          }

//<UserCode_Begin_Bean_JLabel1>

//<UserCode_End_Bean_JLabel1>

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
            JTree1.setAutoscrolls(true);
            JTree1.setLargeModel(true);
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
            JLabel3.setText(resourceBundle.getString("Not Selected Operations."));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel3,ex); 
          }

//<UserCode_Begin_Bean_JLabel3>

//<UserCode_End_Bean_JLabel3>

          try
          {
            JLabel4.setText(resourceBundle.getString("Denotes allowed operation."));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel4,ex); 
          }

//<UserCode_Begin_Bean_JLabel4>

//<UserCode_End_Bean_JLabel4>

          try
          {
            JLabel5.setText(resourceBundle.getString("Denotes disallowed operation."));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel5,ex); 
          }

//<UserCode_Begin_Bean_JLabel5>

//<UserCode_End_Bean_JLabel5>
		JPanel2.setPreferredSize(new Dimension(JPanel2.getPreferredSize().width+89,JPanel2.getPreferredSize().height+10));
		JScrollPane1.setPreferredSize(new Dimension(JScrollPane1.getPreferredSize().width+228,JScrollPane1.getPreferredSize().height+97));
		JLabel2.setPreferredSize(new Dimension(JLabel2.getPreferredSize().width+325,JLabel2.getPreferredSize().height+14));

  
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
        setPreferredSize(new Dimension(getPreferredSize().width+496,getPreferredSize().height+332)); 
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
		if(!AuthMain.standalone)
		{
			JTree1.setRootVisible(false);
		}
		else
		{
			JTree1.setRootVisible(true);
		}
	JLabel1.setIcon(AuthMain.getBuilderUiIfInstance().getImage("addview.png"));	

	JLabel4.setIcon(AuthMain.getBuilderUiIfInstance().getImage("tick01.png"));	
	JLabel5.setIcon(AuthMain.getBuilderUiIfInstance().getImage("wrong01.png"));		
	JLabel3.setIcon(AuthMain.getBuilderUiIfInstance().getImage("circle.png"));			
  } 
  public void setUpConnections()
  { 

  //<Begin_setUpConnections> 

      JTree1_JTree1_conn2 JTree1_JTree1_conn21 =  new JTree1_JTree1_conn2();
      JTree1.addMouseListener(JTree1_JTree1_conn21);
      JTree1_JTree1_conn1 JTree1_JTree1_conn11 =  new JTree1_JTree1_conn1();
      JTree1.addMouseMotionListener(JTree1_JTree1_conn11);
  
      //<End_setUpConnections>
  } 
  public void initVariables()
  { 
     
     if(false)
		{
  //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        JLabel1= new javax.swing.JLabel();
        JPanel1= new javax.swing.JPanel();
        JLabel2= new javax.swing.JLabel();
        JScrollPane1= new javax.swing.JScrollPane();
        JTree1= new javax.swing.JTree();
        JPanel2= new javax.swing.JPanel();
        JLabel3= new javax.swing.JLabel();
        JLabel4= new javax.swing.JLabel();
        JLabel5= new javax.swing.JLabel();

  
        //<End_initVariables>
			}
        Top= new javax.swing.JPanel();
        JLabel1= new javax.swing.JLabel();
        JPanel1= new javax.swing.JPanel();
        JLabel2= new javax.swing.JLabel();
        JScrollPane1= new javax.swing.JScrollPane();
        JPanel2= new javax.swing.JPanel();
        JLabel3= new javax.swing.JLabel();
        JLabel4= new javax.swing.JLabel();
        JLabel5= new javax.swing.JLabel();		
		
       Object previousExpanded = UIManager.put("Tree.expandedIcon",AuthMain.getBuilderUiIfInstance().getImage("expand3.png"));
       Object previousCollapsed = UIManager.put("Tree.collapsedIcon", AuthMain.getBuilderUiIfInstance().getImage("collapse3.png"));
       JTree1= new javax.swing.JTree();
       UIManager.put("Tree.expandedIcon", previousExpanded);
       UIManager.put("Tree.collapsedIcon", previousCollapsed);
      JTree1.putClientProperty("JTree.lineStyle", "Angled");				
       vec = new Vector();
  } 
   
  public void setUpGUI(Container container)
  { 

  //<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(0,0,1,1,0.2,0.1,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(JLabel1,cons);
inset = new Insets(0,0,0,0);
setConstraints(1,0,1,1,0.8,0.1,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(JPanel1,cons);
JPanel1.setLayout(new BorderLayout(5,5));
JPanel1.add(JLabel2,BorderLayout.NORTH);
JPanel1.add(JScrollPane1,BorderLayout.CENTER);
JScrollPane1.getViewport().add(JTree1);
JPanel1.add(JPanel2,BorderLayout.SOUTH);
JPanel2.setLayout(new GridLayout(3,1,5,5));
JPanel2.add(JLabel3);
JPanel2.add(JLabel4);
JPanel2.add(JLabel5);

  
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
	
public void setTreeModel(DefaultTreeModel treeModel)
	{
	 DefaultTreeModel model = treeModel;
	 JTree1.setModel(model);
	 for(int i=0;i<JTree1.getRowCount();i++)
            JTree1.expandRow(i);
	 JTree1.setCellRenderer(new CustomTreeRenderer());	
	
	}	

public void setViewName(String vname)
	{
	JPanel1.setBorder(new javax.swing.border.TitledBorder(resourceBundle.getString("Select Operations for group : ")+ vname));
	}

Hashtable viewoperations = null;
Hashtable operhash = new Hashtable();
DefaultTreeModel tempModel = null;

	
public void constructViewOperationHash(DefaultMutableTreeNode parentnode)
    {
        DefaultMutableTreeNode parent = parentnode;
        DefaultMutableTreeNode child = null;
        for(int i = 0; i < parent.getChildCount(); i++)
          {
            child = (DefaultMutableTreeNode)tempModel.getChild(parent, i);
            //String userob = ((TreeObject)child.getUserObject()).toString();
 	String userob = ((TreeObject)child.getUserObject()).getString();
            if(viewoperations.containsKey(userob) && viewoperations.get(userob).equals("1") && !child.isLeaf())
             {
                for(int j = 0; j < child.getChildCount(); j++)
                {
                    DefaultMutableTreeNode childofchild = (DefaultMutableTreeNode)tempModel.getChild(child, j);
                    //String userob1 = ((TreeObject)childofchild.getUserObject()).toString();
		String userob1 = ((TreeObject)childofchild.getUserObject()).getString();
		if(viewoperations.containsKey(userob1) && viewoperations.get(userob1).equals("0"))
			{
		                    viewoperations.put(userob1, "0");
			}
		else
			{
		                    viewoperations.put(userob1, "1");
			}
	     }
            }
            constructViewOperationHash(child);
          }

    }

public void recursivelySelectChildren(DefaultMutableTreeNode node, int select)
	{
		
	  for(int i=0;i<node.getChildCount();i++)
	   {
	 	DefaultMutableTreeNode child =(DefaultMutableTreeNode)( (( DefaultTreeModel) JTree1.getModel()).getChild(node,i)); 
		if(select == 0)
		{
		      ((TreeObject)child.getUserObject()).setInt(0);     
			//System.out.println("REMOVE "+node.toString());			
		      //operhash.remove(node.toString());				
		      //operhash.remove(child.toString());		
		operhash.remove(((TreeObject)node.getUserObject()).getString());
                      operhash.remove(((TreeObject)child.getUserObject()).getString());	
		}
		else if(select ==1) 
		{
		      ((TreeObject)child.getUserObject()).setInt(1);     
		       //operhash.remove(child.toString());				
	  	operhash.remove(((TreeObject)child.getUserObject()).getString());	
		}
		else if(select ==2)
		{
			((TreeObject)child.getUserObject()).setInt(2);     		
			 //operhash.remove(child.toString());	
			operhash.remove(((TreeObject)child.getUserObject()).getString());	
		}
			
			
		
	           if(!child.isLeaf())
		{
	              recursivelySelectChildren(child,select);	
		}
	   } 	

            }
	
public void recursivelyUnSelectChild(DefaultMutableTreeNode node,int select){
	
	}
	
public java.util.Hashtable getOperations()
	{
		if(operhash.size() == 0)
			operhash = null;
		return operhash;
	}	

 public void renderOpTree(DefaultMutableTreeNode parentnode)
    {

        DefaultMutableTreeNode parent = parentnode;
        DefaultMutableTreeNode child = null;
        for(int i = 0; i < parent.getChildCount(); i++)
         {
            child = (DefaultMutableTreeNode)tempModel.getChild(parent, i);
            //String userob = ((TreeObject)child.getUserObject()).toString();
 	String userob = ((TreeObject)child.getUserObject()).getString();
            if(viewoperations.containsKey(userob))
            {
                if(viewoperations.get(userob).equals("1"))
                {
                    ((TreeObject)child.getUserObject()).setIncluded(true);
                    if(!child.isLeaf())
                    {
                        for(int j = 0; j < child.getChildCount(); j++)
                        {
                            DefaultMutableTreeNode childofchild = (DefaultMutableTreeNode)tempModel.getChild(child, j);
                            ((TreeObject)childofchild.getUserObject()).setSelected(true);
                            //String userob1 = ((TreeObject)childofchild.getUserObject()).toString();
		String userob1 = ((TreeObject)childofchild.getUserObject()).getString();
                            ((TreeObject)childofchild.getUserObject()).setIncluded(true);
			
                            if(viewoperations.containsKey(userob1))
                            {
                                if(viewoperations.get(userob).equals("0"))
                                    ((TreeObject)childofchild.getUserObject()).setExcluded(true);//
                            }
                            else
                            {
                                viewoperations.put(userob1, "1");
                            }
                        }

                    }
                }
                else
                if(viewoperations.get(userob).equals("0"))
                    ((TreeObject)child.getUserObject()).setExcluded(true);//
            }
            else
            {
                ((TreeObject)child.getUserObject()).setSelected(false);
            }
            renderOpTree(child);
         }

    }
	
Hashtable temp = new Hashtable();
	public void setOperations(String forTheView, java.util.Hashtable hash)
		{
	
		 for(Enumeration e = hash.keys(); e.hasMoreElements();)
		  {
			Object key = e.nextElement();
			temp.put(key,hash.get(key));
		  }

		String vname = forTheView;
		JLabel1.setText(resourceBundle.getString("Select Operations for view : ") + vname);
		setOperHash(hash);
         }
public void setOperHash(Hashtable hashtable)
	{
		viewoperations = hashtable;
		tempModel = (DefaultTreeModel)JTree1.getModel();
		constructViewOperationHash((DefaultMutableTreeNode)JTree1.getModel().getRoot());
          		renderOpTree((DefaultMutableTreeNode)JTree1.getModel().getRoot());
 		operhash = temp;
	}	
	
//<Begin__class_JTree1_JTree1_conn2>

 class JTree1_JTree1_conn2 extends java.awt.event.MouseAdapter implements java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JTree1_JTree1_conn2>



     //Listener Interface Methods Are Added Below 


     public void mousePressed( java.awt.event.MouseEvent arg0)
     { 
   int row = JTree1.getRowForLocation(arg0.getX(), arg0.getY()); 
 if(row == -1)
 return;
  TreePath  path =  JTree1.getPathForRow(row); 
  DefaultMutableTreeNode node =  (DefaultMutableTreeNode )path.getLastPathComponent();
  TreeObject tob = (TreeObject)node.getUserObject() ;
 if(tob.getString().equals("Operation Tree Root"))
  {
     return;
  } 

  if(tob.getInt() == 0)  
 {
  //System.out.println("from no select to select");   
     tob.setInt(1);    
     operhash.put(tob.getString(),"1");     
   //if(!node.isLeaf() && !operhash.contains(node.toString()))
if(!node.isLeaf() && !operhash.contains(tob.getString()))
 {
 //System.out.println("for recurdive selection");    
  recursivelySelectChildren(node, 1);  
 }   
 }
else if(tob.getInt() ==1)
 {
 
//   System.out.println("for selected toexclude");   
  
 tob.setInt(2);  
  
   operhash.put(tob.getString(),"0");      
     //if(!node.isLeaf() && !operhash.contains(node.toString()))
if(!node.isLeaf() && !operhash.contains(tob.getString()))
 {
 //System.out.println("for recurdive selection");    
  recursivelySelectChildren(node, 2);  
 }     
 }

else if(tob.getInt() == 2 )  
{
     
   //if(operhash.containsKey(node.toString())){    
 if(operhash.containsKey(tob.getString())){    
   tob.setInt(0);  
   
   if(!node.isLeaf() )
   {
   recursivelySelectChildren(node,0);  
    }   
  operhash.remove(tob.getString());      
      }    
 }
//System.out.println("HASHTABLE "+operhash);
JTree1.repaint(); 
   }  
//<UserCode_End_Connection_JTree1_JTree1_conn2>
 }//<End__class_JTree1_JTree1_conn2>
//<Begin__class_JTree1_JTree1_conn1>

 class JTree1_JTree1_conn1 extends java.awt.event.MouseMotionAdapter implements java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JTree1_JTree1_conn1>



     //Listener Interface Methods Are Added Below 


     public void mouseMoved( java.awt.event.MouseEvent arg0)
     {
 int x = arg0.getX();
 int y=arg0.getY();
  
 if(JTree1.getRowForLocation(x,y) != -1 ) 
  {
   setCursor(new Cursor(Cursor.HAND_CURSOR));
  } 
 else
  {
   setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
  }   
     }
//<UserCode_End_Connection_JTree1_JTree1_conn1>
 }//<End__class_JTree1_JTree1_conn1>

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}




















































