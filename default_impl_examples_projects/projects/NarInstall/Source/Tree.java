

/*
  $Id: Tree.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $
 */


// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory


//<Begin_Version>
//version$1
//<End_Version>
package com.adventnet.nms.tools.nar;



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import com.adventnet.nms.tools.utils.FilePreviewer;
import java.net.URL;
import com.adventnet.nms.tools.utils.ImgConv;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import com.adventnet.nms.tools.utils.DBConnect;
import java.sql.*;
import com.adventnet.nms.tools.utils.ToolsUtil;
public class Tree extends JPanel implements ActionListener,ItemListener,ListSelectionListener
{
//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "DeploymentWizard";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel4 = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JButton add = null;
	javax.swing.JButton delete = null;
	javax.swing.JPanel JPanel5 = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	javax.swing.JTable JTable1 = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JComboBox usersList = null;
	javax.swing.JButton select = null;
	javax.swing.JButton browse = null;
	javax.swing.JTextField nodeNameField = null;
	javax.swing.JTextField nodeIconField = null;
	javax.swing.JLabel users = null;
	javax.swing.JTextField nodeParentField = null;
	javax.swing.JLabel nodeName = null;
	javax.swing.JLabel nodeIcon = null;
	javax.swing.JLabel nodeParent = null;
	javax.swing.JButton advanced = null;
	javax.swing.JPanel JPanel6 = null;
	javax.swing.JLabel JLabel2 = null;
	javax.swing.JCheckBox treeCheck = null;
	javax.swing.JLabel JLabel1 = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>
    Users user;
    File f_icon;
   	TreeAdvanced ta=null; 
    ConstructTree construct;
    Hashtable resulttable=new Hashtable();
    String tree=null;
    Vector columnNames=null;
    TreeTableModel tableModel;
    Hashtable userTable=new Hashtable();
    Vector tableValues;
	Hashtable advancedValues=null;
	ListSelectionModel lsm=null;
	boolean comboChanged=false;
	DBConnect dbc=null;
  public Tree()
  {
    //<Begin_Tree>
    this.init();
  
    //<End_Tree>
  }

  public Tree(java.applet.Applet applet)
  {
    //<Begin_Tree_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_Tree_java.applet.Applet>
  }

 
    public void start()
  { 

  //<Begin_start> 
       if(running)
 return;
 running=true;

  
       //<End_start>
  } 
  public void initVariables()
  { 

  //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        JPanel4= new javax.swing.JPanel();
        JPanel3= new javax.swing.JPanel();
        add= new javax.swing.JButton();
        delete= new javax.swing.JButton();
        JPanel5= new javax.swing.JPanel();
        JPanel2= new javax.swing.JPanel();
        JScrollPane1= new javax.swing.JScrollPane();
        JTable1= new javax.swing.JTable();
        JPanel1= new javax.swing.JPanel();
        usersList= new javax.swing.JComboBox();
        select= new javax.swing.JButton();
        browse= new javax.swing.JButton();
        nodeNameField= new javax.swing.JTextField();
        nodeIconField= new javax.swing.JTextField();
        users= new javax.swing.JLabel();
        nodeParentField= new javax.swing.JTextField();
        nodeName= new javax.swing.JLabel();
        nodeIcon= new javax.swing.JLabel();
        nodeParent= new javax.swing.JLabel();
        advanced= new javax.swing.JButton();
        JPanel6= new javax.swing.JPanel();
        JLabel2= new javax.swing.JLabel();
        treeCheck= new javax.swing.JCheckBox();
        JLabel1= new javax.swing.JLabel();

  
        //<End_initVariables>
        columnNames =new Vector();
    	    
	        browse.addActionListener(this);
        browse.setActionCommand("browse");
        select.addActionListener(this);
        select.setActionCommand("select");
        add.addActionListener(this);
        add.setActionCommand("add");
        delete.addActionListener(this);
        delete.setActionCommand("delete");
		advanced.addActionListener(this);
		advanced.setActionCommand("advanced");
        usersList.addItemListener(this);
		treeCheck.addItemListener(this);
        columnNames.add(resourceBundle.getString("User"));
        columnNames.add(resourceBundle.getString("TreeName"));
        columnNames.add(resourceBundle.getString("TreeIcon"));
        columnNames.add(resourceBundle.getString("ParentNode"));
		add.setMnemonic('a');
		delete.setMnemonic('d');
		browse.setMnemonic('r');
		select.setMnemonic('s');
		advanced.setEnabled(false);	
        tableModel=new TreeTableModel(columnNames,0);
        disableAll();
  } 
   
  public void setUpGUI(Container container)
  { 

  //<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));
Top.add(JPanel4,BorderLayout.CENTER);
JPanel4.setLayout(new BorderLayout(5,5));
JPanel4.add(JPanel3,BorderLayout.SOUTH);
JPanel3.setLayout(new FlowLayout(1,5,5));
JPanel3.add(add);
JPanel3.add(delete);
JPanel4.add(JPanel5,BorderLayout.CENTER);
JPanel5.setLayout(new BorderLayout(5,5));
JPanel5.add(JPanel2,BorderLayout.CENTER);
JPanel2.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(0,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel2.add(JScrollPane1,cons);
JScrollPane1.getViewport().add(JTable1);
JPanel5.add(JPanel1,BorderLayout.SOUTH);
JPanel1.setLayout(new GridBagLayout());
inset = new Insets(4,4,4,4);
setConstraints(1,1,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel1.add(usersList,cons);
inset = new Insets(5,5,5,5);
setConstraints(2,4,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel1.add(select,cons);
inset = new Insets(4,4,4,4);
setConstraints(2,3,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
JPanel1.add(browse,cons);
inset = new Insets(4,4,4,4);
setConstraints(1,2,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel1.add(nodeNameField,cons);
inset = new Insets(4,4,4,4);
setConstraints(1,3,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel1.add(nodeIconField,cons);
inset = new Insets(4,4,4,4);
setConstraints(0,1,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel1.add(users,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,4,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel1.add(nodeParentField,cons);
inset = new Insets(4,4,4,4);
setConstraints(0,2,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel1.add(nodeName,cons);
inset = new Insets(4,4,4,4);
setConstraints(0,3,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel1.add(nodeIcon,cons);
inset = new Insets(4,4,4,4);
setConstraints(0,4,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel1.add(nodeParent,cons);
inset = new Insets(5,5,5,5);
setConstraints(2,5,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel1.add(advanced,cons);
JPanel5.add(JPanel6,BorderLayout.NORTH);
JPanel6.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel6.add(JLabel2,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel6.add(treeCheck,cons);
Top.add(JLabel1,BorderLayout.WEST);

  
//<End_setUpGUI_Container>

        JTable1.setModel(tableModel);
        //JTable1.addMouseListener(this);
		//JTable1.addListSelectionListener(this);
		JTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lsm=JTable1.getSelectionModel();
		lsm.addListSelectionListener(this);

        
  } 
  public void setUpProperties()
  { 

  //<Begin_setUpProperties> 

          try
          {
            Top.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.LineBorder(new Color(-16777216),1),resourceBundle.getString("Tree Information"),2,0,new Font("Dialog",0,12),new Color(-16764109)));
            Top.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+Top,ex); 
          }

//<UserCode_Begin_Bean_components>

//<UserCode_End_Bean_components>

          try
          {
            JPanel4.setBorder(new javax.swing.border.BevelBorder(0));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel4,ex); 
          }

//<UserCode_Begin_Bean_JPanel4>

//<UserCode_End_Bean_JPanel4>

          try
          {
            add.setText(resourceBundle.getString("Add"));
            add.setFont(new Font("Dialog",0,12));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+add,ex); 
          }

//<UserCode_Begin_Bean_add>
            add.setText(ToolsUtil.getMenuName(resourceBundle.getString("Add"),'a'));

//<UserCode_End_Bean_add>

          try
          {
            delete.setFont(new Font("Dialog",0,12));
            delete.setText(resourceBundle.getString("Delete"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+delete,ex); 
          }

//<UserCode_Begin_Bean_delete>
            delete.setText(ToolsUtil.getMenuName(resourceBundle.getString("Delete"),'d'));

//<UserCode_End_Bean_delete>

          try
          {
            select.setText(resourceBundle.getString("Select"));
            select.setFont(new Font("Dialog",0,12));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+select,ex); 
          }

//<UserCode_Begin_Bean_select>
            select.setText(ToolsUtil.getMenuName(resourceBundle.getString("Select"),'s'));

//<UserCode_End_Bean_select>

          try
          {
            browse.setFont(new Font("Dialog",0,12));
            browse.setText(resourceBundle.getString("Browse"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+browse,ex); 
          }

//<UserCode_Begin_Bean_browse>
            browse.setText(ToolsUtil.getMenuName(resourceBundle.getString("Browse"),'r'));

//<UserCode_End_Bean_browse>

          try
          {
            users.setText(resourceBundle.getString("Users"));
            users.setFont(new Font("Dialog",0,12));
            users.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+users,ex); 
          }

//<UserCode_Begin_Bean_users>

//<UserCode_End_Bean_users>

          try
          {
            nodeName.setFont(new Font("Dialog",0,12));
            nodeName.setForeground(new Color(-16777216));
            nodeName.setText(resourceBundle.getString("Tree Node Name"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+nodeName,ex); 
          }

//<UserCode_Begin_Bean_nodeName>

//<UserCode_End_Bean_nodeName>

          try
          {
            nodeIcon.setText(resourceBundle.getString("Tree Node Icon"));
            nodeIcon.setFont(new Font("Dialog",0,12));
            nodeIcon.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+nodeIcon,ex); 
          }

//<UserCode_Begin_Bean_nodeIcon>

//<UserCode_End_Bean_nodeIcon>

          try
          {
            nodeParent.setText(resourceBundle.getString("Parent Node"));
            nodeParent.setFont(new Font("Dialog",0,12));
            nodeParent.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+nodeParent,ex); 
          }

//<UserCode_Begin_Bean_nodeParent>

//<UserCode_End_Bean_nodeParent>

          try
          {
            advanced.setFont(new Font("SansSerif",0,12));
            advanced.setHorizontalTextPosition(0);
            advanced.setText(resourceBundle.getString("Advanced"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+advanced,ex); 
          }

//<UserCode_Begin_Bean_advanced>

//<UserCode_End_Bean_advanced>

          try
          {
            JLabel2.setText(resourceBundle.getString("Show In WebNms Tree"));
            JLabel2.setFont(new Font("Dialog",0,12));
            JLabel2.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel2,ex); 
          }

//<UserCode_Begin_Bean_JLabel2>

//<UserCode_End_Bean_JLabel2>

          try
          {
            treeCheck.setSelected(false);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+treeCheck,ex); 
          }

//<UserCode_Begin_Bean_treeCheck>

//<UserCode_End_Bean_treeCheck>
		JLabel1.setPreferredSize(new Dimension(JLabel1.getPreferredSize().width+168,JLabel1.getPreferredSize().height+388));
		JPanel1.setPreferredSize(new Dimension(JPanel1.getPreferredSize().width+65,JPanel1.getPreferredSize().height+0));
		JPanel2.setPreferredSize(new Dimension(JPanel2.getPreferredSize().width+221,JPanel2.getPreferredSize().height+100));
		JPanel5.setPreferredSize(new Dimension(JPanel5.getPreferredSize().width+10,JPanel5.getPreferredSize().height+10));
		JPanel4.setPreferredSize(new Dimension(JPanel4.getPreferredSize().width+10,JPanel4.getPreferredSize().height+10));

  
          //<End_setUpProperties>

                 nodeName.setText(resourceBundle.getString("Tree Node Name"));
                 URL url=getClass().getResource("images"+File.separator+"treedetail.jpg");
                 JLabel1.setIcon(new ImageIcon(ImgConv.getImage(url.toString())));
  } 
	public void enableAll()
	{
    	        
            
                usersList.setEnabled(true);
		nodeNameField.setEnabled(true);
		nodeIconField.setEnabled(true);
		nodeParentField.setEnabled(true);
		browse.setEnabled(true);
		select.setEnabled(true);
		add.setEnabled(true);
		delete.setEnabled(true);
                JTable1.setEnabled(true);
	}
    
	public void disableAll()
	{
		usersList.setEnabled(false);
		nodeNameField.setEnabled(false);
		nodeIconField.setEnabled(false);
		nodeParentField.setEnabled(false);
		browse.setEnabled(false);
		select.setEnabled(false);
		add.setEnabled(false);
		delete.setEnabled(false);
                JTable1.setEnabled(false);
				advanced.setEnabled(false);
		
	}
	public boolean isDataBase()
	{
		String fileName = System.getProperty("user.dir") + File.separator + "conf" + File.separator + "database_params.conf";
		dbc=new DBConnect(fileName);
		Connection con;
		boolean database=true;
		try
		{
			dbc.createConnection();
		}
		catch(ClassNotFoundException cnfe)
		{
			database=false;

		}
		try
		{
			con = dbc.getConnection();
		}
		catch(SQLException sqle)
		{
			database=false;

		}
		return database;
	}

    public void setUsers(Vector v)
    {
       
        // usersList.setSelectedItem("");
        usersList.removeAllItems();
        if(v!=null)
        {
            for(int i=0;i<v.size();i++)
            {
                
                usersList.addItem(v.elementAt(i));
                
            }
            int count=tableModel.getRowCount();
            if(count!=-1)
            {
                for(int i=0;i<count;i++)
                {
                    String userName=tableModel.getValueAt(i,0).toString();
                    if(!v.contains(userName))
                    {
                        tableModel.removeRow(i);
                        userTable.remove(userName);
                        i--;
                        count--;
                    }
                }
            }
        }
       
    }
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getActionCommand().equals("browse"))
        {
            JFileChooser jfc=null;
            NarFileFilter ff = new NarFileFilter();
            ff.addExtension("png");
            ff.addExtension("jpg");
            ff.setDescription("Image files" );
            int ret;
            jfc = new JFileChooser(System.getProperty("user.dir")+File.separator+"images");   // new
            FilePreviewer previewer = new FilePreviewer(jfc);
            jfc.setAccessory(previewer);
            jfc.setDialogType(JFileChooser.CUSTOM_DIALOG);
            jfc.setDialogTitle(resourceBundle.getString("Select the Icon File"));
            jfc.setApproveButtonMnemonic('s');
            jfc.setFileFilter(ff);
            ret = jfc.showDialog(null,ToolsUtil.getMenuName(resourceBundle.getString("Select"),'s'));
            if ( ret == 0)
            {
                f_icon=jfc.getSelectedFile();
                nodeIconField.setText(f_icon.toString() );
            }
        }
        if(ae.getActionCommand().equals("select"))
        {
            if(usersList.getSelectedItem()!=null)
            {
                construct=new ConstructTree(usersList.getSelectedItem().toString());
                construct.showtree();
                tree=construct.gettreename();
                if(tree!=null&&!tree.equals(""))
                {
                    nodeParentField.setText(tree);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null,resourceBundle.getString("Select an user to view his corresponding tree"),resourceBundle.getString("Message"),JOptionPane.YES_NO_OPTION);
            }
  
        }
        if(ae.getActionCommand().equals("add"))
        {
            addToTable();
			
        }
        if(ae.getActionCommand().equals("delete"))
        {
            deleteFromTable();
        }
		if(ae.getActionCommand().equals("advanced"))
		{
			if(ta==null)
				ta=new TreeAdvanced();
			ta.user=usersList.getSelectedItem().toString();	
			ta.setVisible(true);	
		}
    }
    public void addToTable()
    {
        String username=usersList.getSelectedItem().toString();
        String enteredTree=nodeNameField.getText();
        String enteredicon=nodeIconField.getText();
        String enteredparent=nodeParentField.getText();
		
        if(username==null)
        {
            JOptionPane.showMessageDialog(this,resourceBundle.getString("Select an user"),resourceBundle.getString("Message"),JOptionPane.INFORMATION_MESSAGE);
        }
        else if(enteredTree==null || enteredTree.equals(""))
        {
            JOptionPane.showMessageDialog(this,resourceBundle.getString("Enter a Tree Name"),resourceBundle.getString("Message"),JOptionPane.INFORMATION_MESSAGE);
        }
		
        else if(enteredicon==null || enteredicon.equals(""))
        {
            JOptionPane.showMessageDialog(this,resourceBundle.getString("Select an Icon for Tree"),resourceBundle.getString("Message"),JOptionPane.INFORMATION_MESSAGE);
        }
        else if(enteredparent==null || enteredparent.equals(""))
        {
            JOptionPane.showMessageDialog(this,resourceBundle.getString("Select a Parent Node For Your Node"),resourceBundle.getString("Message"),JOptionPane.INFORMATION_MESSAGE);
        }
		
        if(username!=null&&enteredTree!=null&&!(enteredTree.equals(""))&&enteredicon!=null&&!(enteredicon.equals(""))&&enteredparent!=null&&!(enteredparent.equals("")))
        {
            construct=new ConstructTree(username);
            if(construct.checknode(enteredTree))
            {

                if(checkIconFile(enteredicon))
                {

                    if(construct.checkParent(enteredparent))
                    {
                      
                        String combine=enteredTree+","+enteredicon+","+enteredparent;
                                            
                        userTable.put(username,combine);
                        Vector v2=new Vector();
                        v2.add(username);
                        v2.add(enteredTree);
                        v2.add(enteredicon);
                        v2.add(enteredparent);
						add(v2,username);
						Vector v1=construct.getNodeId(enteredparent);
						resulttable.put(username,v1);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(this,resourceBundle.getString("select an Existing parent"),resourceBundle.getString("Message"),JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(this,resourceBundle.getString("Enter a valid icon file"),resourceBundle.getString("Message"),JOptionPane.INFORMATION_MESSAGE);
                }
					
            }
            else
            {
                JOptionPane.showMessageDialog(this,resourceBundle.getString("The tree node name already exists please give another name"),resourceBundle.getString("Information"),JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    public void deleteFromTable()
    {
		
        if(JTable1.getSelectedRow()!= -1)
        {
            int rowindex=JTable1.getSelectedRow();
		
            String delete=tableModel.getValueAt(rowindex,0).toString();
            tableModel.removeRow(rowindex);
            userTable.remove(delete);
			if(tableModel.getDataVector().size()!=0)
			{	
				JTable1.setRowSelectionInterval(0,0);	
			}
			else
			{
				nodeNameField.setText("");
				nodeIconField.setText("");
				nodeParentField.setText("");
				advanced.setEnabled(false);
			}
			
        }
        else
        {
            JOptionPane.showMessageDialog(this,resourceBundle.getString("Select a row from table to delete"),resourceBundle.getString("Message"),JOptionPane.INFORMATION_MESSAGE);
        }
    }

     public boolean checkIconFile(String txt_icon)
    {
        File f_icon = new File(txt_icon);
        String s_icon = f_icon.toString();
        String last = s_icon.substring(s_icon.lastIndexOf(".")+1,s_icon.length());
        
        if( f_icon.exists() && f_icon.isFile() )
        {
            if ( last.equalsIgnoreCase("png") || last.equalsIgnoreCase("jpg") )
            {
                return true;   
            }
            
            else
            {
               
                return false;
                
            }
        }
        else
        {
            return false;
        }
    }
    public void add(Vector v,String name)
    {	
        Vector check=new Vector();
        int row=tableModel.getRowCount();
        for(int i=0;i<row;i++)
        {
					
            check.add(tableModel.getValueAt(i,0).toString());
					
        }
        if(!check.contains(name))
        {
				
            tableModel.addRow(v);
			int i=tableModel.getDataVector().size();
			JTable1.setRowSelectionInterval(i-1,i-1);

				advanced.setEnabled(true);	
        }
        else
        {
            for(int j=0;j<check.size();j++)
            {
                if(check.elementAt(j).toString().equalsIgnoreCase(name))
                {
                    tableModel.removeRow(j);
                    tableModel.insertRow(j,v);
					JTable1.setRowSelectionInterval(j,j);

				advanced.setEnabled(true);	
                }
                else
                    continue;
            }
        }
    }
	public void itemStateChanged(ItemEvent ie)
	{
		if(ie.getSource().equals(usersList))
		{
			comboChanged=true;
			String key=(String)usersList.getSelectedItem();
			String details=null;
			if(key!=null)
			{
				if(userTable.containsKey(key))
				{
					details=userTable.get(key).toString(); 
				}
			}
			if(details!=null)
			{
				if(!details.equals(""))
				{
					StringTokenizer token=new StringTokenizer(details,",");
					String[] previousdetails=new String[token.countTokens()];
					int i=0;
					while(token.hasMoreTokens())
					{
						previousdetails[i]=token.nextToken();
						i++;
					}
					nodeNameField.setText(previousdetails[0]);
					nodeIconField.setText(previousdetails[1]);
				nodeParentField.setText(previousdetails[2]);
				advanced.setEnabled(true);	
				
				}
				else
				{
					nodeNameField.setText("");
					nodeIconField.setText("");
					nodeParentField.setText("");

		advanced.setEnabled(false);	
				}

			}
			else
			{
				nodeNameField.setText("");
				nodeIconField.setText("");
				nodeParentField.setText("");

		advanced.setEnabled(false);	
			}
			Vector v=tableModel.getDataVector();
			boolean present=false;
			if(v!=null)
			{
				for(int i=0;i<v.size();i++)
				{
					Vector v1=(Vector)v.elementAt(i);
					if(v1.elementAt(0).toString().equals(key))
					{
						present=true;
						JTable1.setRowSelectionInterval(i,i);
						break;
					}
				}
			}
			
			if(!present)
			{
				JTable1.clearSelection();	
			}
			comboChanged=false;
		}
			if(ie.getSource().equals(treeCheck))
			{
				if(treeCheck.isSelected())
				{
					if(isDataBase())
					{
							enableAll();
					}
					else
					{
						String[] message={resourceBundle.getString("WebNms tree will be updated in cold start of server only"),resourceBundle.getString("              Do you want to continue")};
						if(JOptionPane.showConfirmDialog(InstUninst.topFrame,message,resourceBundle.getString("Confirmation"),JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
						{
							enableAll();
						
						}
						else
						{
							treeCheck.setSelected(false);
						}
					}

				}
				else
				{
					disableAll();
				}
			}
		
	}
    public void setTableValues()
    {
        tableValues=new Vector();
        Vector values=new Vector();
		Hashtable advance=null;
		if(ta!=null)
		{
		 advance=ta.getAdvancedInfo();
		}
        if(treeCheck.isSelected())
        {
            values=tableModel.getDataVector();
            for(int i=0;i<values.size();i++)
            {
                Vector v=(Vector)((Vector)values.elementAt(i)).clone();
                String parentname=v.elementAt(3).toString();
                Vector results=(Vector)resulttable.get(v.elementAt(0).toString());
                if(results.elementAt(0).toString().equals(parentname))
                {
                    String parentid=results.elementAt(1).toString();
                    v.setElementAt(parentid,3);
                }
				Hashtable h=new Hashtable();
				h.put("username",v.elementAt(0));
				h.put("treename",v.elementAt(1));
				h.put("treeicon",v.elementAt(2));
				h.put("parentnode",v.elementAt(3));
				if(advance!=null&&advance.containsKey(v.elementAt(0).toString()))
				{
					Hashtable hash=(Hashtable)advance.get(v.elementAt(0).toString());	
					if(hash.containsKey("menufile"))
					{
						h.put("menufilename",hash.get("menufile"));
					}
					if(hash.containsKey("treepopup"))
					{
						h.put("treepopup",hash.get("treepopup"));
					}
					if(hash.containsKey("tablepopup"))
					{
						h.put("tablepopup",hash.get("tablepopup"));
					}

				}
                tableValues.add(h);
            }
        }
    }
    public Vector getTableValues()
    {
       
        return tableValues;
    }
  public void setUpConnections()
  { 

  //<Begin_setUpConnections> 

  
  //<End_setUpConnections>
  } 
  public void stop()
  { 

  //<Begin_stop> 
       if(!running)
 return;
 running=false;

  
       //<End_stop>
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
            if (input.equals("PORT")) value = "161"; 
            }
        return value;

  
           //<End_getParameter_String>
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
        setPreferredSize(new Dimension(getPreferredSize().width+668,getPreferredSize().height+417)); 
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
  } 
    public boolean validateTrees()
    {
        if(treeCheck.isSelected()) 
        { 
            if(tableValues.isEmpty())
            {
                JOptionPane.showMessageDialog(null,resourceBundle.getString("Add information to show in WebNms Tree"),resourceBundle.getString("Message"),JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
            else if(tableValues.size()!=usersList.getItemCount())
            {
                if(JOptionPane.showConfirmDialog(null,resourceBundle.getString("Tree information is not configured for all selected users")+" \n"+resourceBundle.getString("    Do you want to continue"),resourceBundle.getString("confirm"),JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
        return true;
    }
	public void valueChanged(ListSelectionEvent le)
	{
			if(!comboChanged)
			{
			int rowindex=JTable1.getSelectedRow();
            if(rowindex!=-1)
            {
                 if(tableModel.getValueAt(rowindex,0)!=null)
                 {
                    usersList.setSelectedItem(tableModel.getValueAt(rowindex,0).toString());
                 }
                else
                {
                    usersList.setSelectedItem("");
                }
                if(tableModel.getValueAt(rowindex,1) != null)
                {
                    nodeNameField.setText(tableModel.getValueAt(rowindex,1).toString());
                }
                else
                {
                    nodeNameField.setText("");
                }
                if(tableModel.getValueAt(rowindex,2)!=null)
                {
                    nodeIconField.setText(tableModel.getValueAt(rowindex,2).toString());
                }
                else
                {
                    nodeIconField.setText("");
                }
                if(tableModel.getValueAt(rowindex,3)!=null)
                {
                    nodeParentField.setText(tableModel.getValueAt(rowindex,3).toString());
                }
                else
                {
                    nodeParentField.setText("");
                }
                
            }
            else
            {
                usersList.setSelectedItem("");
                nodeNameField.setText("");
                nodeIconField.setText("");
                nodeParentField.setText("");
				
            }
			}
	}

    /*public void mouseClicked(MouseEvent me)
    {
        if(me.getSource().equals(JTable1))
        {
            
            int rowindex=JTable1.getSelectedRow();
            if(rowindex!=-1)
            {
                 if(tableModel.getValueAt(rowindex,0)!=null)
                 {
                    usersList.setSelectedItem(tableModel.getValueAt(rowindex,0).toString());
                 }
                else
                {
                    usersList.setSelectedItem("");
                }
                if(tableModel.getValueAt(rowindex,1) != null)
                {
                    nodeNameField.setText(tableModel.getValueAt(rowindex,1).toString());
                }
                else
                {
                    nodeNameField.setText("");
                }
                if(tableModel.getValueAt(rowindex,2)!=null)
                {
                    nodeIconField.setText(tableModel.getValueAt(rowindex,2).toString());
                }
                else
                {
                    nodeIconField.setText("");
                }
                if(tableModel.getValueAt(rowindex,3)!=null)
                {
                    nodeParentField.setText(tableModel.getValueAt(rowindex,3).toString());
                }
                else
                {
                    nodeParentField.setText("");
                }
                
            }
            else
            {
                usersList.setSelectedItem("");
                nodeNameField.setText("");
                nodeIconField.setText("");
                nodeParentField.setText("");
            }
        }
    }
    
     public void mouseEntered(MouseEvent me)
	{
	}
	public void mouseExited(MouseEvent me)
	{
	}
	public void mousePressed(MouseEvent me)
	{
	}
	public void mouseReleased(MouseEvent me)
	{
	}*/
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



 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}




