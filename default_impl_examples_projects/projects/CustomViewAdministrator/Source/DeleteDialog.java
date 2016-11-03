
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.tools.CustomView;




import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.table.*;
import java.util.*;



public class DeleteDialog extends JDialog implements ActionListener,ItemListener
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JCheckBox userOnly = null;
	javax.swing.JCheckBox allGroups = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JCheckBox particularGroup = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	javax.swing.JTable JTable1 = null;
	javax.swing.JCheckBox deleteAllChilds = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JButton ok = null;
	javax.swing.JButton cancel = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>
GroupSelectionTableModel model=null;
	ButtonGroup group=null;
String userName="";	
UserGroupInformation userGroupInfo=null;
  	Vector groupList=null;
	Vector usersList=null;
	String okOrCancel="";


  

  

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

  
    public void init()
  {
        //<Begin_init> 
        if (initialized == true) return; 
        this.setSize(getPreferredSize().width+526,getPreferredSize().height+347); 
          setTitle("");
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
          showStatus("Error in init method",ex); 
        } 
        // let us set the initialzed variable to true so  
        // we dont initialize again even if init is called 
        initialized = true; 


  
        //<End_init>
	this.setModal(true);
	setTitle("Delete CustomView");
	Toolkit kit=Toolkit.getDefaultToolkit();
	Dimension dim=kit.getScreenSize();
	this.setLocation((dim.width-(int)this.getSize().getWidth())/2,(dim.height-(int)this.getSize().getHeight())/2);
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
            JPanel1.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.LineBorder(new Color(-16777216),1),"",0,0,new Font("Dialog",0,12),new Color(-16764109)));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+JPanel1,ex); 
          }

          try
          {
            userOnly.setFont(new Font("SansSerif",0,12));
            userOnly.setHorizontalTextPosition(4);
            userOnly.setText(" this user only");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+userOnly,ex); 
          }

          try
          {
            allGroups.setFont(new Font("SansSerif",0,12));
            allGroups.setHorizontalTextPosition(4);
            allGroups.setText("all the groups to which the user belongs");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+allGroups,ex); 
          }

          try
          {
            JLabel1.setHorizontalAlignment(2);
            JLabel1.setFont(new Font("SansSerif",0,12));
            JLabel1.setForeground(new Color(-16777216));
            JLabel1.setHorizontalTextPosition(4);
            JLabel1.setText("Delete the customview  for");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+JLabel1,ex); 
          }

          try
          {
            particularGroup.setFont(new Font("SansSerif",0,12));
            particularGroup.setHorizontalTextPosition(4);
            particularGroup.setText("any particular group to which the user belongs");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+particularGroup,ex); 
          }

          try
          {
            deleteAllChilds.setFont(new Font("SansSerif",0,12));
            deleteAllChilds.setHorizontalTextPosition(4);
            deleteAllChilds.setSelected(false);
            deleteAllChilds.setText("Delete all the childrens present under this custom view");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+deleteAllChilds,ex); 
          }

          try
          {
            ok.setFont(new Font("SansSerif",0,12));
            ok.setHorizontalTextPosition(4);
            ok.setText("OK");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+ok,ex); 
          }

          try
          {
            cancel.setFont(new Font("SansSerif",0,12));
            cancel.setHorizontalTextPosition(4);
            cancel.setText("Cancel");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+cancel,ex); 
          }
		ok.setPreferredSize(new Dimension(ok.getPreferredSize().width+17,ok.getPreferredSize().height+1));

  
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
        JPanel1= new javax.swing.JPanel();
        userOnly= new javax.swing.JCheckBox();
        allGroups= new javax.swing.JCheckBox();
        JLabel1= new javax.swing.JLabel();
        particularGroup= new javax.swing.JCheckBox();
        JScrollPane1= new javax.swing.JScrollPane();
        JTable1= new javax.swing.JTable();
        deleteAllChilds= new javax.swing.JCheckBox();
        JPanel2= new javax.swing.JPanel();
        ok= new javax.swing.JButton();
        cancel= new javax.swing.JButton();

  
        //<End_initVariables>
group=new ButtonGroup();
group.add(userOnly);
group.add(allGroups);
group.add(particularGroup);
group.setSelected(userOnly.getModel(),true);
userOnly.addItemListener(this);
allGroups.addItemListener(this);
particularGroup.addItemListener(this);
model=new GroupSelectionTableModel();
JTable1.setModel(model);
ok.addActionListener(this);
cancel.addActionListener(this);

  } 
  public void setUpGUI(Container container)
  {
//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));
Top.add(JPanel1,BorderLayout.CENTER);
JPanel1.setLayout(new GridBagLayout());
inset = new Insets(5,25,5,5);
setConstraints(0,2,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel1.add(userOnly,cons);
inset = new Insets(5,25,5,5);
setConstraints(0,3,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel1.add(allGroups,cons);
inset = new Insets(15,5,5,5);
setConstraints(0,1,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel1.add(JLabel1,cons);
inset = new Insets(5,25,5,5);
setConstraints(0,4,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel1.add(particularGroup,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,5,0,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel1.add(JScrollPane1,cons);
JScrollPane1.getViewport().add(JTable1);
inset = new Insets(20,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel1.add(deleteAllChilds,cons);
Top.add(JPanel2,BorderLayout.SOUTH);
JPanel2.setLayout(new FlowLayout(1,5,5));
JPanel2.add(ok);
JPanel2.add(cancel);

  
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




  public DeleteDialog()
  {
    //<Begin_DeleteDialog>
    pack();
    this.setTitle("DeleteDialog");
  
    //<End_DeleteDialog>
  }

  public DeleteDialog(java.applet.Applet applet)
  {
    //<Begin_DeleteDialog_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setTitle("DeleteDialog");
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_DeleteDialog_java.applet.Applet>
  }
  public DeleteDialog(String user)
  {
		super(CustomViewTypes.topFrame);
	  userName=user;
	  groupList=getUserGroups();
  }

public void itemStateChanged(ItemEvent ie)
{
	if(ie.getSource()==userOnly)
	{
		removeRows();
	}
	else if(ie.getSource()==allGroups)
	{
		removeRows();
	}
	else if(ie.getSource()==particularGroup)
	{
		removeRows();
		for(int i=0;i<groupList.size();i++)
		{
			String group=groupList.elementAt(i).toString();
			Object[] object={group,new Boolean(true)};
			model.addRow(object);
		}	
	}
}
public void removeRows()
{
	int count=model.getRowCount();
	if(count!=-1)
	{
		for(int i=0;i<count;i++)
		{
			model.removeRow(0);
		}
	}
}
public Vector getUserGroups()
    {
	Vector userGroup=new Vector();
	 
	if(userGroupInfo==null)
            {
                userGroupInfo=new UserGroupInformation();
            }

	 Vector groups=userGroupInfo.getAllGroups();
	
	 Vector users=userGroupInfo.getAllUsers();	
	if(users!=null)
	{
		for(int i=0;i<users.size();i++)
		{
			Vector user=(Vector)users.elementAt(i);
			for(int j=0;j<user.size();j++)
			{
				if(user.elementAt(j).toString().equals(userName))
				{
					userGroup.add(groups.elementAt(i).toString());
				}
			}
		}
	}
	return userGroup;
	

	

    }
public boolean validateSelection()
{
	
	if(particularGroup.isSelected())
	{
		int count=model.getRowCount();
		for(int i=0;i<count;i++)
		{
			
			String s=model.getValueAt(i,1).toString();
			if(s.equals("true"))
			{
				return true;
			}
		}
	return false;			
	}
	//if particulargroup is not selected we have to return true
	return true;
	
}
public Vector getUsersList(Vector groups)
{
	Vector users=new Vector();
	for(int i=0;i<groups.size();i++)
	{
		Vector v=userGroupInfo.getUsersForGroup(groups.elementAt(i).toString());	
		if(v!=null)
		{
			for(int j=0;j<v.size();j++)
			{
				if(!users.contains(v.elementAt(j).toString()))
				{
					users.add(v.elementAt(j).toString());
				}
			}
		}
	}
	return users;
}
public Vector getUsers()
{
	Vector v=null; 
	if(okOrCancel.equals("ok"))
	{
		if(userOnly.isSelected())
		{
			v=new Vector();
			v.add(userName);
		
		}
		else if(allGroups.isSelected())
		{
			v=getUsersList(groupList);
		}
		else if(particularGroup.isSelected())
		{
			Vector selectedGroups=new Vector();
			int count=model.getRowCount();
			for(int i=0;i<count;i++)
			{
				String s=model.getValueAt(i,1).toString();
				if(s.equals("true"))
				{
					selectedGroups.add(model.getValueAt(i,0).toString());
				}
			}
			v=getUsersList(selectedGroups);
		}
	}
	return v;
}
public boolean getDeleteChilds()
{
	if(deleteAllChilds.isSelected())
	{
		return true;
	}
	else
	{
		return false;
	}
}		
public void actionPerformed(ActionEvent e)
{
	if(e.getSource()==ok)
	{
		if(validateSelection())
		{
			okOrCancel="ok";
			setVisible(false);
		}
		else
		{
			JOptionPane.showMessageDialog(null,"Select a group for deleting this custom view","Message",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	else if(e.getSource()==cancel)
	{
			okOrCancel="cancel";
			setVisible(false);
	}
}
}

