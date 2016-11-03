//$Id: ParentSelection.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory


package com.adventnet.nms.tools.CustomView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.table.*;
import javax.swing.event.*;


public class ParentSelection extends JPanel implements ItemListener
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	javax.swing.JTable userVsParent = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JCheckBox isAllSelectedUsers = null;
	javax.swing.JTextField parentForAllUsers = null;
	javax.swing.JLabel JLabel1 = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>
	JComboBox temp = null;
	CustomizerTableModel ctm=null;

	Vector columns=null;
	Vector users=null;
	Vector treeCombos=null;
	TreeCombo combo=null;
	CustomizerTreeModel model=null;
	ComboRenderer renderer=null;	
	ComboEditor editor=null;
	Hashtable userVsParentId=null;
  public ParentSelection()
  {

    //<Begin_ParentSelection>
    this.init();
  
    //<End_ParentSelection>
	
  }


  public ParentSelection(java.applet.Applet applet)
  {
    //<Begin_ParentSelection_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_ParentSelection_java.applet.Applet>
  }


  


 

  

    public void init()
  {
        //<Begin_init> 
        if (initialized == true) return; 
        setPreferredSize(new Dimension(getPreferredSize().width+429,getPreferredSize().height+442)); 
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
          showStatus("Error in init method",ex); 
        } 
        // let us set the initialzed variable to true so  
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
            }
        return value;

  
           //<End_getParameter_String>
  } 
  public void setUpProperties()
  {
  //<Begin_setUpProperties> 

          try
          {
            Top.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.LineBorder(new Color(-16777216),1),"Parent Selection",1,0,new Font("Dialog",0,12),new Color(-16764109)));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+Top,ex); 
          }

          try
          {
            userVsParent.setRowHeight(23);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+userVsParent,ex); 
          }

          try
          {
            isAllSelectedUsers.setFont(new Font("SansSerif",0,12));
            isAllSelectedUsers.setHorizontalTextPosition(4);
            isAllSelectedUsers.setText("For All the below listed users");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+isAllSelectedUsers,ex); 
          }

          try
          {
            parentForAllUsers.setHorizontalAlignment(2);
            parentForAllUsers.setFont(new Font("SansSerif",0,12));
            parentForAllUsers.setEditable(false);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+parentForAllUsers,ex); 
          }

          try
          {
            JLabel1.setHorizontalAlignment(2);
            JLabel1.setFont(new Font("SansSerif",0,12));
            JLabel1.setForeground(new Color(-16777216));
            JLabel1.setHorizontalTextPosition(4);
            JLabel1.setText("Parent Node Name");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+JLabel1,ex); 
          }
		JPanel2.setPreferredSize(new Dimension(JPanel2.getPreferredSize().width+15,JPanel2.getPreferredSize().height+0));

  
          //<End_setUpProperties>
  } 
  public void start()
  {
  //<Begin_start> 

  
  //<End_start>

  } 
  public void stop()
  {
  //<Begin_stop> 

  
  //<End_stop>
  } 
  public void initVariables()
  {
        //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        JPanel2= new javax.swing.JPanel();
        JScrollPane1= new javax.swing.JScrollPane();
        userVsParent= new javax.swing.JTable();
        JPanel1= new javax.swing.JPanel();
        isAllSelectedUsers= new javax.swing.JCheckBox();
        parentForAllUsers= new javax.swing.JTextField();
        JLabel1= new javax.swing.JLabel();

  
        //<End_initVariables>
		isAllSelectedUsers.addItemListener(this);
		userVsParentId=new Hashtable();
		
	
		
		
	
	
	
		
		
		
		
  } 
  public void setUpGUI(Container container)
  {
//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));
Top.add(JPanel2,BorderLayout.CENTER);
JPanel2.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel2.add(JScrollPane1,cons);
JScrollPane1.getViewport().add(userVsParent);
Top.add(JPanel1,BorderLayout.NORTH);
JPanel1.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel1.add(isAllSelectedUsers,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,1,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel1.add(parentForAllUsers,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel1.add(JLabel1,cons);

  
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
class ComboEditor implements TableCellEditor
{
	int i;
	public Component getTableCellEditorComponent(JTable table,Object value,boolean isSelected,int row,int column)
	{
		i=row;
		return ((TreeCombo)treeCombos.elementAt(row));
	}
	public void addCellEditorListener(CellEditorListener l){}
	public void cancelCellEditing(){}
	public boolean isCellEditable(EventObject eo){return true;}
	public void removeCellEditorListener(CellEditorListener l){}
	public boolean shouldSelectCell(EventObject eo){return true;}
	public boolean stopCellEditing(){
				//fireEditingStopped();
		return true;}
	public Object getCellEditorValue(){
		return ((TreeCombo)treeCombos.elementAt(i)).getSelectedItem();}
	
} 
class ComboRenderer implements TableCellRenderer
{
	public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean b,int row,int column)
	{
		
			
		return ((TreeCombo)treeCombos.elementAt(row));
	}
}

public void itemStateChanged(ItemEvent e)
{
		if(e.getSource().equals(isAllSelectedUsers))
		{
			if(isAllSelectedUsers.isSelected())
			{
				userVsParent.setEnabled(false);
				parentForAllUsers.setEditable(true);
			}
			else
			{
				userVsParent.setEnabled(true);
				parentForAllUsers.setEditable(false);
			}
		}
}
public void setUsers(Vector usersList,String createOrModify,String parent)
{

	setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		users=usersList;
		treeCombos=new Vector();
		columns=new Vector();
		columns.add("User Name");
		columns.add("Parent Node");
		ctm=new CustomizerTableModel(columns,0);
		userVsParent.setModel(ctm);	
		
		for(int i=0;i<usersList.size();i++)
		{
			if(model==null)
			{
				model=new CustomizerTreeModel();
			}
			combo=new TreeCombo();
			
			combo.setProperties(model.getModel((String)usersList.elementAt(i)),model.getTreeVsImage(),model.getTreeVsId());
			Hashtable hash=model.getTreeVsId();
			userVsParentId.put(usersList.elementAt(i).toString(),hash);
			if(createOrModify.equals("modify"))
			{
				combo.setParentNode(parent);
						
			}
			treeCombos.add(combo);
			String[] s={usersList.elementAt(i).toString()};
			ctm.addRow(s);
		}
		if(editor==null)
		{
			editor=  new ComboEditor();
		}
		if(renderer==null)
		{
			renderer=new ComboRenderer();
		}
		userVsParent.getColumnModel().getColumn(1).setCellEditor(editor);
		userVsParent.getColumnModel().getColumn(1).setCellRenderer(renderer);
	setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

}
public boolean validateParent()
{
	if(isAllSelectedUsers.isSelected())
	{
		if(parentForAllUsers.getText().trim().equals(""))
		{
				JOptionPane.showMessageDialog(null,"Please specify a parent node for all selected users","Message",JOptionPane.INFORMATION_MESSAGE);
				return false;
		}
	}
	return true;

}
public Properties getUserVsParent()
{
		
	Properties p=new Properties();	
	for(int i=0;i<users.size();i++)
	{
	  	if(!isAllSelectedUsers.isSelected())
		{
			TreeCombo tc=(TreeCombo)treeCombos.elementAt(i);
			String parent=((ListEntry)tc.getSelectedItem()).getTreeId();
			p.put(users.elementAt(i).toString(),parent);
		}
		else 
		{
			String user=users.elementAt(i).toString();
			String parentName=parentForAllUsers.getText();
			Hashtable hash=(Hashtable)userVsParentId.get(user);	
			if(hash.containsKey(parentName))
			{
				p.put(user,hash.get(parentName).toString());
			}
			else
			{
				System.out.println("For the user: "+user+" the parent name"+parentName+" doesn't exists");
			}
			/*String parentId=getParentId(user,parentName);
			if(parentId!=null)
			{
				p.put(user,parentId);
			}
			else
			{
				System.out.println("For the user: "+user+" the parent name"+parentName+" doesn't exists");
			}*/
		}
	}
	return p;
}

public String getParentId(String user,String parentName)
{
		if(model==null)
		{
			model=new CustomizerTreeModel();
		}
		model.getModel(user);
		return model.getParentId(parentName);

}
public void disableAll()
{
		parentForAllUsers.setEditable(false);
		userVsParent.setEnabled(false);
		isAllSelectedUsers.setEnabled(false);
		
}
public void setProperties(Properties p,String showOrModfiy)
{
	
	String user="";
	String parentId="";
	String parentName="";
	if(p.containsKey("user"))
	{
		user=p.getProperty("user");
	}
	if(p.containsKey("parentid"))
	{

		parentId=p.getProperty("parentid");
	}
	if(p.containsKey("parentname"))
	{
		parentName=p.getProperty("parentname");
	}
	if(showOrModfiy.equals("modify"))
	{
	//This is used while modification where we have to show the tree combo 
		Vector v=new Vector(1);
		v.add(user);
		setUsers(v,"modify",parentId);

	}
	else
	{
	//this part of code is used only for showing properties on right side
		columns=new Vector();
		columns.add("User Name");
		columns.add("Parent Node");
		ctm=new CustomizerTableModel(columns,0);
		userVsParent.setModel(ctm);			
		String[] s={user,parentName};
		ctm.addRow(s);
	}
	
}
}















