//$Id: DefaultNmsFilterFrame.java,v 1.2 2007/02/22 15:02:55 srajeswari Exp $
package com.adventnet.nms.eventui;

import java.util.*;
import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.security.*;

import javax.swing.table.*;
import javax.swing.tree.*;
import javax.swing.JOptionPane;
import com.adventnet.nms.eventui.*;
import java.awt.event.ActionListener;
import com.adventnet.nms.eventdb.*;
import com.adventnet.snmp.ui.SasFileDialog;
import javax.swing.*;
import com.adventnet.nms.util.*;
import com.adventnet.nms.severity.SeverityInfo;

public class DefaultNmsFilterFrame extends NmsFilterFrame implements ActionListener{
    
    //String fileName = "../conf/event.filters";//no i18n
EventBrowser evtBrowser = null;
Vector mainVector = new Vector();
Hashtable mainHashtable = new Hashtable();
Hashtable notifiers = new Hashtable();
boolean tobesaved   = false;
boolean addOperation  = false;
ActionListener listen = null;
Properties currentProp = new Properties();
Icon add_action = null;
Icon add_action_mo =  null;
Icon delete_action = null;
Icon delete_action_mo = null;
Icon add_filter = null;
Icon add_filter_mo = null;
Icon delete_filter = null;
Icon delete_filter_mo = null;

int CANCEL = 0;
int NO = 1;
int YES   = 2;
   
    public DefaultNmsFilterFrame() 
    {
        initComponents();
	setDragNDrop();
	setESCFunction();
        actionTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        initIcons();      
        tree.setCellRenderer(new MyRenderer());
        DefaultMutableTreeNode startnode = new DefaultMutableTreeNode("Filters");//No i18n
        DefaultTreeModel model = new DefaultTreeModel(startnode);
        tree.setModel(model);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        pack();
    }
     

    public void setDragNDrop()
    {
	MouseListener ml = new MouseAdapter() {
		public void mousePressed(MouseEvent e) {
		    mousePressedAction(e);
		    /*int selRow = tree.getRowForLocation(e.getX(), e.getY());
		    TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
		    if(selRow != -1) {
			if(e.getClickCount() == 1) {
			    mySingleClick(selRow, selPath);
			}
			else if(e.getClickCount() == 2) {
			    myDoubleClick(selRow, selPath);
			}
			}*/
		}
		public void mouseEntered(MouseEvent e)
		{
		}

		public void mouseExited(MouseEvent e)
		{
		}

		public void mouseReleased(MouseEvent e)
		{
		    mouseReleasedAction(e);
		}

		public void mouseClicked(MouseEvent e)
		{
		}
	    };
	tree.addMouseListener(ml);
    }
    
    int sourceIndex = -1;
    
    public void mousePressedAction(MouseEvent e)
    {
	sourceIndex = tree.getRowForLocation(e.getX(), e.getY());
	TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
    }
    
    public void mouseReleasedAction(MouseEvent e)
    {
	int selRow = tree.getRowForLocation(e.getX(), e.getY());
	if(selRow == sourceIndex || selRow == -1)
	    {
		return;
	    }


	TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());

	DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
	DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();
	DefaultMutableTreeNode sourceNode = (DefaultMutableTreeNode)root.getChildAt(sourceIndex);
	root.remove(sourceIndex);
	root.insert(sourceNode, selRow);

	model = new DefaultTreeModel(root);
	tree.setModel(model);
	tree.setSelectionRow(selRow);
    }
    
    public void setESCFunction()
    {
	KeyStroke escStroke = KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE,0);
	((JComponent)getRootPane()).registerKeyboardAction(new ActionListener(){
		public void actionPerformed(ActionEvent evt)
		{
		    escAction();
		}
	    }, "Cancel", escStroke, JComponent.WHEN_IN_FOCUSED_WINDOW);//No i18n
    }

    public void escAction()
    {
	this.setVisible(false);
	this.dispose();
    }

    public void setProperties(Vector hst, ActionListener lst)
    {
        listen = lst;
        mainVector = hst;
        populateTree(mainVector);
        
        int size = hst.size(); 
        mainHashtable = new Hashtable();

        for(int i = 0; i < size; i++)
        {
             Properties prop = (Properties)mainVector.elementAt(i);
             String name = prop.getProperty("name");
             mainHashtable.put(name, prop);
        }
        tree.setSelectionRow(0);
    }
    
    public void populateTree(Vector hst)
    {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("filters");//No i18n
        DefaultTreeModel model = new DefaultTreeModel(root);
        
        int size = hst.size();
        
        for(int i = 0; i < size; i++)
        {
            Properties prop = (Properties)hst.elementAt(i);
            String key = prop.getProperty("name");
            DefaultMutableTreeNode child = new DefaultMutableTreeNode(key);
            root.add(child);
        }
        
        tree.setModel(model);
        deleteFilterButton.setEnabled(true);
    }
    
   public void addTreeNode(String treename)
    {
        DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();
              
        DefaultMutableTreeNode child = new DefaultMutableTreeNode(treename);
        root.add(child);
        
        model = new DefaultTreeModel(root);
        tree.setModel(model);
        
        int row = root.getChildCount();
        tree.setSelectionRow(row - 1);
        setCurrentUIProperties((Properties)mainHashtable.get(treename));
        deleteFilterButton.setEnabled(true);
    }
    
    public Vector getProperties()
    {
        Vector result = new Vector();
        
        DefaultMutableTreeNode root = (DefaultMutableTreeNode)((DefaultTreeModel)tree.getModel()).getRoot();

        for(Enumeration  enumerate   = root.children(); enumerate.hasMoreElements(); )
        {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode)enumerate.nextElement();
            String   treename  = node.toString();
           
            Properties prop    = (Properties)mainHashtable.get(treename);
            Properties newProp = new Properties();
            for(Enumeration en = prop.propertyNames(); en.hasMoreElements();)
            {
                String key = (String)en.nextElement();
                String value = prop.getProperty(key);
                newProp.setProperty(key,value);
            }
            result.add(newProp);
        }
        return result;
    }
    
    public void setCurrentUIProperties(Properties prop)
    {
	currentProp = prop;
        if(prop == null) prop = new Properties();
        
        String name = prop.getProperty("name");
        if(name == null) name = "";
        nameTextField.setText(name);
        
        String sever = prop.getProperty("stringseverity");
        if(sever == null) sever = "";
        severity.setText(sever);
        
        String source = prop.getProperty("source");
        if(source == null) source = "";
        sourceTextField.setText(source);
     
        String actionString = prop.getProperty("action");
        
        Vector vec = getActionsAsVector(actionString);
        addNewActions(vec);
    }
      
    public Properties getCurrentUIProperties()
    {
        //Properties prop = new Properties();
	if(currentProp == null)
	    {
		currentProp = new Properties();
	    }
	Properties prop = currentProp;

        String name = nameTextField.getText();
	prop.setProperty("name", name);
	        
        String source = sourceTextField.getText();
	if(!source.equals(""))
	    {
		prop.setProperty("source", source);
	    }
	    else //Issue ID 11348018
		{
		  	prop.remove("source"); //No I18N
	    }
        
        String sever = severity.getText();
	if(!sever.equals(""))
	    {
		prop.setProperty("stringseverity", sever);
	    }
	    else //Issue ID 11348018
		{
		 	prop.remove("stringseverity"); //No I18N
	    }
        
        DefaultTableModel model = (DefaultTableModel)actionTable.getModel();
        
        int size  = model.getRowCount();
        
        String actionString = "";
        for(int i = 0; i < size; i ++)
	    {
		String key = (String)model.getValueAt(i, 0);
		if(key == null)
		    {
			key = "";
		    }
		if(actionString.equals(""))
		    {
			actionString = key;
		    }
		else
		    {
			actionString = actionString + "," + key; //No i18n
		    }
	    }
        
        prop.setProperty("action", actionString);
        
        return prop;
    }
    
    public void addNewActions(Vector vec)
    {
        DefaultTableModel model = (DefaultTableModel)actionTable.getModel();
        int size = model.getRowCount();
        for(int i = 0; i < size; i++)
        {
            model.removeRow(0);
        }
        
        int si = vec.size();
        
        for(int i = 0; i < si; i++)
        {
            String row = (String)vec.elementAt(i);
            Vector rowv = new Vector();
            rowv.addElement(row);
            model.addRow(rowv);
        }
        
        if(model.getRowCount() > 0)
        {
            actionTable.setRowSelectionInterval(si-1 , si-1);
        }
        addActionButton.setEnabled(true);
	removeActionButton.setEnabled(true);
    }
    
    /*public void addTable(String str)
    {
        DefaultTableModel model = (DefaultTableModel)actionTable.getModel();
        int size  = model.getRowCount();
        
        for(int i = 0; i < size; i ++)
        {
            String key = (String)model.getValueAt(i, 0);
            if(str.equals(key))
            {
                
            }
        }
        
        Vector vec = new Vector();
        vec.addElement(str);
        model.addRow(vec);
    }not necessary*/
    
    public DefaultTableModel getTableModel()
    {
        DefaultTableModel model =(DefaultTableModel) actionTable.getModel();
        return model;
    }
    
    public Vector getActionsAsVector(String str)
    {
        Vector vec = new Vector();
        if(str == null) return vec;
        StringTokenizer tok = new StringTokenizer(str, ",");//No i18n
        for(;tok.hasMoreElements();)
        {
            String key = (String)tok.nextElement();
            vec.add(key);
        }
        return vec;
    }
    
    public int showConfirmDialog(String msg, String title)
    {
        int option = javax.swing.JOptionPane.showConfirmDialog(this, msg, title,javax.swing.JOptionPane.YES_NO_CANCEL_OPTION);
        if(option == javax.swing.JOptionPane.YES_OPTION)
        {
            return YES;
        }
        else if(option == javax.swing.JOptionPane.NO_OPTION)
        {
            return NO;
        }
        else
        {
            return CANCEL;
        }
    }
    
    public boolean localUpdate(Properties prop)
    {      
        DefaultTreeModel       model = (DefaultTreeModel)tree.getModel();
        DefaultMutableTreeNode root  = (DefaultMutableTreeNode)model.getRoot();

	int filterCount = root.getChildCount();
	if(filterCount <= 0)
	    {
		return true;
	    }
        if(prop == null)
        {
            prop = getCurrentUIProperties();
        }
        String action = prop.getProperty("action");

        if(!(action != null && !action.equals("")))
	    {
		JOptionPane.showMessageDialog(this, NmsClientUtil.GetString("javaui.filter.cannotadd"), NmsClientUtil.GetString("javaui.filter.errormessage"), JOptionPane.ERROR_MESSAGE);//No i18n
		return false;
	    }
             
        tobesaved  = false;
        int[] rows = tree.getSelectionRows();
        int row    = rows[0];
        
        DefaultMutableTreeNode child = (DefaultMutableTreeNode)root.getChildAt(row);
        String oldname = child.toString();
        
        String name = prop.getProperty("name");//No i18n

	Vector childVector = new Vector();

	for(Enumeration en = root.children(); en.hasMoreElements();)
	    {
		String str = ((TreeNode)en.nextElement()).toString();
		childVector.addElement(str);
	    }
	childVector.remove(oldname);

	if(childVector.contains(name))
	    {
		JOptionPane.showMessageDialog(this, NmsClientUtil.GetString("javaui.filter.samefilter"), NmsClientUtil.GetString("javaui.filter.errormessage"), JOptionPane.ERROR_MESSAGE);//No i18n
		return false;
	    }

	root.remove(row);
        mainVector.removeElementAt(row);
        mainHashtable.remove(oldname);

        DefaultMutableTreeNode node = new DefaultMutableTreeNode(name);
        root.insert(node, row);
        model = new DefaultTreeModel(root);
        tree.setModel(model);

        tree.setSelectionRow(row);
        setCurrentUIProperties(prop);
        mainHashtable.put(name, prop);
        mainVector.add(row, prop);
        
        tobesaved = false;
        tree.setSelectionRow(row);
        setCurrentUIProperties(prop);
        addOperation = false;
        
        return true;
    }
    
    public void setNotifications(Hashtable hst)
    {
        notifiers = hst;
    }
    
    public Hashtable getNotifications()
    {
        return notifiers;
    }
    
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel3      = new javax.swing.JPanel();
        jPanel1      = new javax.swing.JPanel();
        leftPanel    = new javax.swing.JPanel();
        jPanel4      = new javax.swing.JPanel();
        jPanel17     = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tree         = new javax.swing.JTree();
        jPanel5      = new javax.swing.JPanel();
        addFilterButton = new javax.swing.JButton();
        deleteFilterButton = new javax.swing.JButton();
        mainPanel    = new javax.swing.JPanel();
        jPanel6      = new javax.swing.JPanel();
        jSeparator1  = new javax.swing.JSeparator();
        jPanel13     = new javax.swing.JPanel();
        jPanel7      = new javax.swing.JPanel();
        tablePanel   = new javax.swing.JPanel();
        jPanel10     = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        actionTable  = new javax.swing.JTable();
        jPanel9      = new javax.swing.JPanel();
        jPanel11     = new javax.swing.JPanel();
        addActionButton    = new javax.swing.JButton();
        removeActionButton = new javax.swing.JButton();
        jPanel12      = new javax.swing.JPanel();
        jPanel14      = new javax.swing.JPanel();
        criteriaPanel = new javax.swing.JPanel();
        jLabel4       = new javax.swing.JLabel();
        jLabel5       = new javax.swing.JLabel();
        sourceTextField = new javax.swing.JTextField();
        advanceButton = new javax.swing.JButton();
        jPanel18      = new javax.swing.JPanel();
        severity      = new javax.swing.JTextField();
        severityButton = new javax.swing.JButton();
        namePanel     = new javax.swing.JPanel();
        jLabel3       = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        jPanel2       = new javax.swing.JPanel();
        headLabel     = new javax.swing.JLabel();
        jPanel20      = new javax.swing.JPanel();
        statusPanel   = new javax.swing.JPanel();
        status        = new javax.swing.JLabel();
        jPanel19      = new javax.swing.JPanel();
        buttonsPanel  = new javax.swing.JPanel();
        jPanel8       = new javax.swing.JPanel();
        jSeparator3   = new javax.swing.JSeparator();
        buttonPanel   = new javax.swing.JPanel();
        jPanel15      = new javax.swing.JPanel();
        jPanel16      = new javax.swing.JPanel();
        okButton      = new javax.swing.JButton();
        applyButton   = new javax.swing.JButton();
        cancelButton  = new javax.swing.JButton();
        jPanel21 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(NmsClientUtil.GetString("Event Filters"));//No i18n
        setIconImage(getIconImage());
        addWindowListener(new java.awt.event.WindowAdapter() 
	    {
		public void windowClosing(java.awt.event.WindowEvent evt) 
		{
		    exitForm(evt);
		}
	    });

        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel3.setMinimumSize(new java.awt.Dimension(422, 460));
        jPanel3.setPreferredSize(new java.awt.Dimension(422, 460));
        jPanel1.setLayout(new java.awt.BorderLayout());

        leftPanel.setLayout(new java.awt.BorderLayout());

        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel17.setLayout(new java.awt.BorderLayout());

        jPanel17.setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(5, 5, 5, 5)));
        tree.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        tree.setToolTipText("");
        tree.setRootVisible(false);
        tree.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() 
	    {
		public void valueChanged(javax.swing.event.TreeSelectionEvent evt) 
		{
		    treeValueChanged(evt);
		}
	    });
	
        jScrollPane2.setViewportView(tree);
	
	JPanel upMovePanel = new JPanel(new BorderLayout());
	JPanel upCPanel = new JPanel();
	upMovePanel.add(upCPanel,java.awt.BorderLayout.CENTER); 
	
	JButton upButton = new JButton(getNmsIcon("images/up.png"));//No i18n
        upButton.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        upButton.setAlignmentY(0.0F);
        upButton.setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(1, 1, 1, 1)));
        upButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        upButton.addActionListener(new java.awt.event.ActionListener() 
	    {
		public void actionPerformed(java.awt.event.ActionEvent evt) 
		{
		    upButtonActionPerformed(evt);
		}
	    });
	upMovePanel.add(upButton, java.awt.BorderLayout.EAST);
	
	JPanel downMovePanel = new JPanel(new BorderLayout());
	JPanel downCPanel = new JPanel();
	upMovePanel.add(downCPanel,java.awt.BorderLayout.CENTER); 

	JButton downButton = new JButton(getNmsIcon("images/down.png"));//No i18n
        downButton.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        downButton.setAlignmentY(0.0F);
        downButton.setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(1, 1, 1, 1)));
        downButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        downButton.addActionListener(new java.awt.event.ActionListener() 
	    {
		public void actionPerformed(java.awt.event.ActionEvent evt) 
		{
		    downButtonActionPerformed(evt);
		}
	    });
	downMovePanel.add(downButton, java.awt.BorderLayout.EAST);
	
        jPanel17.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel17, java.awt.BorderLayout.CENTER);

        leftPanel.add(jPanel4, java.awt.BorderLayout.CENTER);

        addFilterButton.setIcon(getNmsIcon("images/add_user.png"));//No i18n
        addFilterButton.setToolTipText(NmsClientUtil.GetString("javaui.filter.AddFilter"));
        addFilterButton.setAlignmentY(0.0F);
        addFilterButton.setBorderPainted(false);
        addFilterButton.setContentAreaFilled(false);
        addFilterButton.setFocusPainted(false);
        //addFilterButton.setIconTextGap(0);
        addFilterButton.setMargin(new java.awt.Insets(0, 6, 0, 6));
        addFilterButton.setSelected(true);
        addFilterButton.addActionListener(new java.awt.event.ActionListener() 
	    {
		public void actionPerformed(java.awt.event.ActionEvent evt) 
		{
		    addFilterButtonActionPerformed(evt);
		}
	    });
        addFilterButton.addMouseListener(new java.awt.event.MouseAdapter() 
	    {
		public void mouseEntered(java.awt.event.MouseEvent evt) 
		{
		    addFilterButtonMouseEntered(evt);
		}
		public void mouseExited(java.awt.event.MouseEvent evt) 
		{
		    addFilterButtonMouseExited(evt);
		}
		public void mousePressed(java.awt.event.MouseEvent evt) 
		{
		    addFilterButtonMousePressed(evt);
		}
		public void mouseReleased(java.awt.event.MouseEvent evt) 
		{
		    addFilterButtonMouseReleased(evt);
		}
	    });
	
        jPanel5.add(addFilterButton);
	
        deleteFilterButton.setIcon(getNmsIcon("images/delete_mo.png"));//No i18n
        deleteFilterButton.setToolTipText(NmsClientUtil.GetString("javaui.filter.DeleteFilter"));
        deleteFilterButton.setAlignmentY(0.0F);
        deleteFilterButton.setBorderPainted(false);
        deleteFilterButton.setContentAreaFilled(false);
        deleteFilterButton.setFocusPainted(false);
        //deleteFilterButton.setIconTextGap(0);
        deleteFilterButton.setMargin(new java.awt.Insets(0, 6, 0, 6));
        deleteFilterButton.setSelected(true);
        deleteFilterButton.addActionListener(new java.awt.event.ActionListener() 
	    {
		public void actionPerformed(java.awt.event.ActionEvent evt) 
		{
		    deleteFilterButtonActionPerformed(evt);
		}
	    });
        deleteFilterButton.addMouseListener(new java.awt.event.MouseAdapter() 
	    {
		public void mouseEntered(java.awt.event.MouseEvent evt) 
		{
		    deleteFilterButtonMouseEntered(evt);
		}
		public void mouseExited(java.awt.event.MouseEvent evt) 
		{
		    deleteFilterButtonMouseExited(evt);
		}
		public void mousePressed(java.awt.event.MouseEvent evt) 
		{
		    deleteFilterButtonMousePressed(evt);
		}
		public void mouseReleased(java.awt.event.MouseEvent evt) 
		{
		    deleteFilterButtonMouseReleased(evt);
		}
	    });
	
        jPanel5.add(deleteFilterButton);

        leftPanel.add(jPanel5, java.awt.BorderLayout.SOUTH);

        jPanel1.add(leftPanel, java.awt.BorderLayout.WEST);

        mainPanel.setLayout(new java.awt.BorderLayout());

        jPanel6.setLayout(new java.awt.BorderLayout());

        jPanel6.setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(5, 1, 5, 1)));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel6.add(jSeparator1, java.awt.BorderLayout.CENTER);

        mainPanel.add(jPanel6, java.awt.BorderLayout.WEST);

        jPanel13.setLayout(new java.awt.BorderLayout());

        jPanel7.setLayout(new java.awt.BorderLayout());

        jPanel7.setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(5, 5, 5, 5)));
        jPanel7.setMinimumSize(new java.awt.Dimension(251, 333));
        jPanel7.setNextFocusableComponent(addFilterButton);
        tablePanel.setLayout(new java.awt.BorderLayout());

        tablePanel.setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(20, 20, 20, 20)));
        jPanel10.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(100, 110));
        jScrollPane1.setAutoscrolls(true);
        actionTable.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
	//actionTable.setGridColor(Color.white);
	//actionTable.setSelectionForeground(Color.white);
        actionTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                NmsClientUtil.GetString("javaui.filter.ActionsList")
            }
        ) {
		boolean[] canEdit = new boolean []{ false };
		
		public boolean isCellEditable(int rowIndex, int columnIndex) 
		{
		    return canEdit [columnIndex];
		}
	    });
        actionTable.addMouseListener(new java.awt.event.MouseAdapter() 
	    {
		public void mouseClicked(java.awt.event.MouseEvent evt) 
		{
		    actionTableMouseClicked(evt);
		}
		public void mousePressed(java.awt.event.MouseEvent evt) 
		{
		    actionTableMousePressed(evt);
		}
		public void mouseReleased(java.awt.event.MouseEvent evt) 
		{
		    actionTableMouseReleased(evt);
		}
	    });
	
        jScrollPane1.setViewportView(actionTable);

        jPanel10.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        tablePanel.add(jPanel10, java.awt.BorderLayout.CENTER);

        jPanel9.setLayout(new java.awt.BorderLayout());

        jPanel11.setLayout(new java.awt.BorderLayout());

        addActionButton.setIcon(getNmsIcon("images/add_operation.png"));//No i18n
        addActionButton.setToolTipText(NmsClientUtil.GetString("javaui.filter.AddAction"));
        addActionButton.setBorderPainted(false);
        addActionButton.setContentAreaFilled(false);
        addActionButton.setFocusPainted(false);
        addActionButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        addActionButton.setSelected(true);
        addActionButton.addActionListener(new java.awt.event.ActionListener() 
	    {
		public void actionPerformed(java.awt.event.ActionEvent evt) 
		{
		    addActionButtonActionPerformed(evt);
		}
	    });
        addActionButton.addMouseListener(new java.awt.event.MouseAdapter() 
	    {
		public void mouseEntered(java.awt.event.MouseEvent evt) 
		{
		    addActionButtonMouseEntered(evt);
		}
		public void mouseExited(java.awt.event.MouseEvent evt) 
		{
		    addActionButtonMouseExited(evt);
		}
		public void mousePressed(java.awt.event.MouseEvent evt) 
		{
		    addActionButtonMousePressed(evt);
		}
		public void mouseReleased(java.awt.event.MouseEvent evt) 
		{
		    addActionButtonMouseReleased(evt);
		}
	    });
	
        jPanel11.add(addActionButton, java.awt.BorderLayout.NORTH);
	
        removeActionButton.setIcon(getNmsIcon("images/delete_mo.png"));//No i18n
        removeActionButton.setToolTipText(NmsClientUtil.GetString("javaui.filter.DeleteAction"));
        removeActionButton.setBorderPainted(false);
        removeActionButton.setContentAreaFilled(false);
        removeActionButton.setFocusPainted(false);
        removeActionButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        removeActionButton.setSelected(true);
        removeActionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeActionButtonActionPerformed(evt);
            }
        });
        removeActionButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                removeActionButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                removeActionButtonMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                removeActionButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                removeActionButtonMouseReleased(evt);
            }
        });

        jPanel11.add(removeActionButton, java.awt.BorderLayout.SOUTH);

        jPanel9.add(jPanel11, java.awt.BorderLayout.NORTH);

        jPanel12.setLayout(new java.awt.BorderLayout());

        jPanel9.add(jPanel12, java.awt.BorderLayout.CENTER);

        tablePanel.add(jPanel9, java.awt.BorderLayout.EAST);

        jPanel7.add(tablePanel, java.awt.BorderLayout.CENTER);

        jPanel14.setLayout(new java.awt.BorderLayout());

        criteriaPanel.setLayout(new java.awt.GridBagLayout());

        criteriaPanel.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(javax.swing.border.EtchedBorder.RAISED), NmsClientUtil.GetString("javaui.filter.MatchCriteria"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 10)));//No i18n
        jLabel4.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        jLabel4.setText(NmsClientUtil.GetString("javaui.filter.Source"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 8);
        criteriaPanel.add(jLabel4, gridBagConstraints);

        jLabel5.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        jLabel5.setText(NmsClientUtil.GetString("javaui.filter.Severity"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 8);
        criteriaPanel.add(jLabel5, gridBagConstraints);

        sourceTextField.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 14, 5, 5);
        criteriaPanel.add(sourceTextField, gridBagConstraints);

        advanceButton.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        advanceButton.setText(NmsClientUtil.GetString("javaui.filter.Advanced"));
        advanceButton.setToolTipText("Advanced Properties");//No i18n
        advanceButton.addActionListener(new java.awt.event.ActionListener() 
	    {
		public void actionPerformed(java.awt.event.ActionEvent evt) 
		{
		    advanceButtonActionPerformed(evt);
		}
	    });
        advanceButton.addMouseListener(new java.awt.event.MouseAdapter() 
	    {
		public void mouseEntered(java.awt.event.MouseEvent evt) 
		{
		    advanceButtonMouseEntered(evt);
		}
		public void mouseExited(java.awt.event.MouseEvent evt) 
		{
		    advanceButtonMouseExited(evt);
		}
	    });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        criteriaPanel.add(advanceButton, gridBagConstraints);

        jPanel18.setLayout(new java.awt.BorderLayout());

        severity.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        jPanel18.add(severity, java.awt.BorderLayout.CENTER);

        severityButton.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        severityButton.setAlignmentY(0.0F);
        severityButton.setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(1, 1, 1, 1)));
        severityButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        severityButton.addActionListener(new java.awt.event.ActionListener() 
	    {
		public void actionPerformed(java.awt.event.ActionEvent evt) 
		{
		    severityButtonActionPerformed(evt);
		}
	    });

        jPanel18.add(severityButton, java.awt.BorderLayout.EAST);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 14, 5, 5);
        criteriaPanel.add(jPanel18, gridBagConstraints);

        jPanel14.add(criteriaPanel, java.awt.BorderLayout.CENTER);

        namePanel.setLayout(new java.awt.GridBagLayout());

        namePanel.setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(10, 1, 10, 1)));
        jLabel3.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        jLabel3.setText(NmsClientUtil.GetString("javaui.filter.name"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        namePanel.add(jLabel3, gridBagConstraints);

        nameTextField.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        namePanel.add(nameTextField, gridBagConstraints);

        jPanel14.add(namePanel, java.awt.BorderLayout.NORTH);

        jPanel7.add(jPanel14, java.awt.BorderLayout.NORTH);

        jPanel13.add(jPanel7, java.awt.BorderLayout.CENTER);

        mainPanel.add(jPanel13, java.awt.BorderLayout.CENTER);

        jPanel1.add(mainPanel, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setLayout(new java.awt.BorderLayout());
	
	String headIcon = "images/set_notifications_top.jpg";//No i18n
	if(type.equals("EventFilter"))
	    {
		headIcon = "images/eventFilters_top.jpg";//No i18n
	    }
	else if(type.equals("AlertFilter"))
	    {
		headIcon = "images/alertFilters_top.jpg";//No i18n
	    }
    headLabel.setBackground(Color.white);
	headLabel.setIcon(getNmsIcon(headIcon));//No i18n
	jPanel2.setBackground(new Color(230, 230, 230));    
        jPanel2.add(headLabel, java.awt.BorderLayout.CENTER);
    jPanel3.setBackground(new Color(204, 210, 210));
        jPanel3.add(jPanel2, java.awt.BorderLayout.NORTH);

        jPanel20.setLayout(new java.awt.BorderLayout());

        statusPanel.setLayout(new java.awt.BorderLayout());

        statusPanel.setBorder(new javax.swing.border.BevelBorder(javax.swing.border.BevelBorder.LOWERED));
        status.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        status.setText(NmsClientUtil.GetString("javaui.filter.Ready"));
        statusPanel.add(status, java.awt.BorderLayout.CENTER);

        jPanel20.add(statusPanel, java.awt.BorderLayout.SOUTH);

        jPanel19.setLayout(new java.awt.BorderLayout());

        buttonsPanel.setLayout(new java.awt.BorderLayout());

        jPanel8.setLayout(new java.awt.BorderLayout());

        jPanel8.setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(1, 5, 1, 5)));
        jPanel8.add(jSeparator3, java.awt.BorderLayout.CENTER);

        buttonsPanel.add(jPanel8, java.awt.BorderLayout.NORTH);

        buttonPanel.setLayout(new java.awt.BorderLayout());

        buttonPanel.setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(10, 10, 10, 10)));
        buttonPanel.add(jPanel15, java.awt.BorderLayout.CENTER);

        jPanel16.setLayout(new java.awt.GridLayout(1, 0, 5, 0));

        okButton.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        okButton.setText(NmsClientUtil.GetString("javaui.filter.OK"));
        okButton.addActionListener(new java.awt.event.ActionListener() 
	    {
		public void actionPerformed(java.awt.event.ActionEvent evt) 
		{
		    okButtonActionPerformed(evt);
		}
	    });
	
        jPanel16.add(okButton);

        applyButton.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        applyButton.setText(NmsClientUtil.GetString("javaui.filter.Apply"));
        applyButton.addActionListener(new java.awt.event.ActionListener() 
	    {
		public void actionPerformed(java.awt.event.ActionEvent evt) 
		{
		    applyButtonActionPerformed(evt);
		}
	    });
	
        jPanel16.add(applyButton);

        cancelButton.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        cancelButton.setText(NmsClientUtil.GetString("javaui.filter.Cancel"));
        cancelButton.addActionListener(new java.awt.event.ActionListener() 
	    {
		public void actionPerformed(java.awt.event.ActionEvent evt) 
		{
		    cancelButtonActionPerformed(evt);
		}
	    });

        jPanel16.add(cancelButton);

        buttonPanel.add(jPanel16, java.awt.BorderLayout.EAST);

        jPanel21.setLayout(new java.awt.GridBagLayout());

        jButton1.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        jButton1.setText(NmsClientUtil.GetString("javaui.filter.LoadFile"));
        jButton1.addActionListener(new java.awt.event.ActionListener() 
	    {
		public void actionPerformed(java.awt.event.ActionEvent evt) 
		{
		    jButton1ActionPerformed(evt);
		}
	    });
	
        jPanel21.add(jButton1, new java.awt.GridBagConstraints());

        buttonPanel.add(jPanel21, java.awt.BorderLayout.WEST);

        buttonsPanel.add(buttonPanel, java.awt.BorderLayout.CENTER);

        jPanel19.add(buttonsPanel, java.awt.BorderLayout.CENTER);

        jPanel20.add(jPanel19, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel20, java.awt.BorderLayout.SOUTH);

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        pack();
    }

    FilterFileDialog dia = null;
    private void jButton1ActionPerformed (java.awt.event.ActionEvent evt) 
    {
	dia = new FilterFileDialog(this, false);
	NmsClientUtil.centerWindow(dia);
        dia.show();
    }
    
    int axnStartPoint = -1;
    int axnEndPoint = -1;
    
    private void actionTableMouseReleased (java.awt.event.MouseEvent evt) 
    {
        axnEndPoint = ((JTable)evt.getSource ()).getSelectedRow ();
        
        if(axnStartPoint == -1 || axnEndPoint == -1)
	    {
		return;
	    }
        if(axnEndPoint != axnStartPoint)
	    {
		getTableModel().moveRow(axnStartPoint, axnStartPoint, axnEndPoint);
	    }
    }
    
    private void actionTableMousePressed (java.awt.event.MouseEvent evt) 
    {
        axnStartPoint = ((JTable)evt.getSource ()).getSelectedRow ();
    }
    
    private void actionTableMouseClicked (java.awt.event.MouseEvent evt) 
    {
    }
       
    private void upButtonActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    private void downButtonActionPerformed(java.awt.event.ActionEvent evt)
    {
    }

    private void severityButtonActionPerformed (java.awt.event.ActionEvent evt) 
    {
        JPopupMenu jp = new JPopupMenu();

	Vector vec = NmsClientUtil.severityInfo.getNames(SeverityInfo.EXCLUDE_NO_CRITICALITY);

        for(Enumeration en = vec.elements(); en.hasMoreElements();)
        {
            String str = (String)en.nextElement();
            JMenuItem item = new JMenuItem(str);
            item.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
            item.addActionListener(new ActionListener()
               {
                  public void actionPerformed(ActionEvent e)  
                    {
                        String a = severity.getText() + " " + e.getActionCommand();
                        severity.setText(a);
                    }
                }
            );
            jp.add(item);
        }
        
        jp.show(severityButton, 0,5 );
    }

    private void advanceButtonMouseExited (java.awt.event.MouseEvent evt) 
    {     
        showStatus("Ready");//No i18n
    }

    private void advanceButtonMouseEntered (java.awt.event.MouseEvent evt) 
    { 
        showStatus("Click this Button to add more criteria Properties");//No i18n
    }

    private void removeActionButtonMouseExited (java.awt.event.MouseEvent evt) 
    {     
        showStatus("Ready");//No i18n
    }

    private void removeActionButtonMouseEntered (java.awt.event.MouseEvent evt) 
    {     
        showStatus("Click this Button to delete the selected Action");//No i18n
    }

    private void addActionButtonMouseExited (java.awt.event.MouseEvent evt) 
    {
            showStatus("Ready");//No i18n
    }

    private void addActionButtonMouseEntered (java.awt.event.MouseEvent evt) 
    {
        showStatus("Click this button to add action");//No i18n
    }

    private void deleteFilterButtonMouseEntered (java.awt.event.MouseEvent evt) 
    {
        showStatus("Click this Button to delete the filter");//No i18n
    }

    private void addFilterButtonMouseExited (java.awt.event.MouseEvent evt) 
    {
        showStatus("Ready");//No i18n
    }

    private void deleteFilterButtonMouseExited (java.awt.event.MouseEvent evt) 
    {
        showStatus("Ready");//No i18n
    }

    private void addFilterButtonMouseEntered (java.awt.event.MouseEvent evt) 
    {
        showStatus("Click this Button to add New Filter");//No i18n
    }

    private Icon getNmsIcon(String image)
    {
        String imagePath = null;
        if(NmsClientUtil.applet != null)
	    {
		imagePath = NmsClientUtil.applet.getDocumentBase() + "../" + image;//No i18n
	    }
        else
	    {
		imagePath = image;
	    }
        if(imagePath == null)
	    {
		return null;
	    }
        try
	    {
		if(!standAlone)
		    {
			return NmsClientUtil.getImageIcon(new URL(imagePath));
		    }
		else
		    {
			ImageIcon icon = new ImageIcon(imagePath);
			return icon;
		    }
	    }
        catch(Exception e)
	    {
		System.err.println(e.getMessage());
		return null;
	    }  
    }
    
    public void initIcons()
    {
	String sadd_action = "images/add_action.png";//No i18n
	add_action = getNmsIcon(sadd_action);
	addActionButton.setIcon(add_action);
	
	String sadd_action_mo =  "images/add_action_mo.png";//No i18n
	add_action_mo = getNmsIcon(sadd_action_mo);
	
	String sdelete_action = "images/delete_action.png";//No i18n
	delete_action = getNmsIcon(sdelete_action);
	removeActionButton.setIcon(delete_action);
	
	String sdelete_action_mo = "images/delete_action_mo.png";//No i18n
	delete_action_mo = getNmsIcon(sdelete_action_mo);
	
	String sadd_filter = "images/add_filter.png";//No i18n
	add_filter = getNmsIcon(sadd_filter);
	addFilterButton.setIcon(add_filter);
	
	String sadd_filter_mo = "images/add_filter_mo.png";//No i18n
	add_filter_mo = getNmsIcon(sadd_filter_mo);
	
	String sdelete_filter = "images/delete_filter.png";//No i18n
	delete_filter = getNmsIcon(sdelete_filter);
	deleteFilterButton.setIcon(delete_filter);
	
	String sdelete_filter_mo = "images/delete_filter_mo.png";//No i18n
	delete_filter_mo = getNmsIcon(sdelete_filter_mo);
	
	severityButton.setIcon(getNmsIcon("images/click.png"));//No i18n
	
    }
    
    private void removeActionButtonActionPerformed(java.awt.event.ActionEvent evt) 
    {
        if(actionTable.getRowCount() < 1)
	    {
		return;
	    }
        int row = actionTable.getSelectedRow();
        if(row >= 0)
	    {
		int option = JOptionPane.showConfirmDialog(new JFrame(), NmsClientUtil.GetString("javaui.filter.confirmdeleteaction"), NmsClientUtil.GetString("javaui.filter.confirmationmessage"), JOptionPane.YES_NO_OPTION);//No i18n
		if(option != JOptionPane.YES_OPTION)
            {
                return;
            }
            getTableModel().removeRow(row);
        }
        else
        {
           JOptionPane.showConfirmDialog(this, NmsClientUtil.GetString("javaui.filter.noaction"), NmsClientUtil.GetString("javaui.filter.errormessage"), JOptionPane.DEFAULT_OPTION);//No i18n
        }
        if(actionTable.getRowCount() < 1)
	    {
		removeActionButton.setEnabled(false);
	    }
    }
    
    private void removeActionButtonMouseReleased(java.awt.event.MouseEvent evt) 
    {
        removeActionButton.setIcon(delete_action);
    }

    private void removeActionButtonMousePressed(java.awt.event.MouseEvent evt) 
    {
        removeActionButton.setIcon(delete_action_mo);
    }

    private void addActionButtonMouseReleased(java.awt.event.MouseEvent evt) 
    {
        addActionButton.setIcon(add_action);
    }

    private void addActionButtonMousePressed(java.awt.event.MouseEvent evt) 
    {
        addActionButton.setIcon(add_action_mo);
    }

    private void deleteFilterButtonMouseReleased(java.awt.event.MouseEvent evt) 
    {
        deleteFilterButton.setIcon(delete_filter);
    }

    private void deleteFilterButtonMousePressed(java.awt.event.MouseEvent evt) 
    {
        deleteFilterButton.setIcon(delete_filter_mo);
    }

    private void addFilterButtonMouseReleased(java.awt.event.MouseEvent evt) 
    {
        addFilterButton.setIcon(add_filter);
    }

    private void addFilterButtonMousePressed(java.awt.event.MouseEvent evt) 
    {
        addFilterButton.setIcon(add_filter_mo);
    }

    private void addActionButtonActionPerformed(java.awt.event.ActionEvent evt) 
    {
	DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
        DefaultMutableTreeNode root  = (DefaultMutableTreeNode)model.getRoot();
	
	int filterCount = root.getChildCount();
	if(filterCount <= 0)
	    {
		JOptionPane.showMessageDialog(this, NmsClientUtil.GetString("javaui.filter.nofilter"), NmsClientUtil.GetString("javaui.filter.errormessage"), JOptionPane.ERROR_MESSAGE);//No i18n
		return;
	    }
	
        AddAction action = new AddAction(this, true, evtBrowser);
        NmsClientUtil.centerWindow(action);
        action.show();
	
	removeActionButton.setEnabled(true);
    }

    private void advanceButtonActionPerformed(java.awt.event.ActionEvent evt) 
    {
        showStatus("Done");//No i18n
	
	DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
        DefaultMutableTreeNode root  = (DefaultMutableTreeNode)model.getRoot();
	
	int filterCount = root.getChildCount();
	if(filterCount <= 0)
	    {
		JOptionPane.showMessageDialog(this, NmsClientUtil.GetString("javaui.filter.nofilter"), NmsClientUtil.GetString("javaui.filter.errormessage"), JOptionPane.ERROR_MESSAGE);//No i18n
		return;
	    }

        AdvancedFrame aframe = new AdvancedFrame(this, true, currentProp);
        NmsClientUtil.centerWindow(aframe);
        aframe.show();
    }

    private void deleteFilterButtonActionPerformed(java.awt.event.ActionEvent evt) {
	DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
        DefaultMutableTreeNode root  = (DefaultMutableTreeNode)model.getRoot();
	
	int filterCount = root.getChildCount();
	if(filterCount <= 0)
	    {
		JOptionPane.showMessageDialog(this,NmsClientUtil.GetString("javaui.filter.nofilter"), NmsClientUtil.GetString("javaui.filter.errormessage"),JOptionPane.ERROR_MESSAGE);//No i18n
		return;
	    }
        JOptionPane op = new JOptionPane();
        op.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
        int option = op.showConfirmDialog(this, NmsClientUtil.GetString("javaui.filter.confirmdeletefilter"), NmsClientUtil.GetString("javaui.filter.confirmationmessage"),javax.swing.JOptionPane.YES_NO_OPTION);//No i18n
        
        if(option != JOptionPane.YES_OPTION)
        {
            return;
        }
        else
        {   
            deleteNode();
        }
    }

    public void deleteNode()
    {
	tobesaved = false;      

	int[] rows = tree.getSelectionRows();
	int row    = rows[0];

	DefaultTreeModel      model = (DefaultTreeModel)tree.getModel();
	DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();
	root.remove(row);
	
	model = new DefaultTreeModel(root);
	tree.setModel(model);
	
	Properties prop = (Properties)mainVector.remove(row);
	String     name = prop.getProperty("name");
	mainHashtable.remove(name);
	
	if(root.getChildCount() != 0)
            {
                int newrow = row - 1;
                if(newrow < 0) 
		    {
			newrow = 0;
		    }
                
                tree.setSelectionRow(newrow);
                setCurrentUIProperties((Properties)mainVector.elementAt(newrow));
            }
            else
            {
                setCurrentUIProperties(null);
                deleteFilterButton.setEnabled(false);
            }
     }
    
    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if(tobesaved)
        {
            int option = showConfirmDialog(NmsClientUtil.GetString("javaui.filter.savechanges"), NmsClientUtil.GetString("javaui.filter.confirmationmessage"));//No i18n
            if(option == YES)
            {
                applyButtonActionPerformed(evt);
            }
            else if(option == CANCEL)
            {
                return;
            }
        }
        setVisible(false);
        dispose();
    }
   
    private void addFilterButtonActionPerformed(java.awt.event.ActionEvent evt) 
    {
	if(tobesaved)
	    {
		int option = JOptionPane.showConfirmDialog(this, NmsClientUtil.GetString("javaui.filter.savechanges"), NmsClientUtil.GetString("javaui.filter.confirmationmessage"), JOptionPane.YES_NO_OPTION);//No i18n
		if(option == JOptionPane.YES_OPTION)
		    {
			tobesaved    = false;
			boolean flag = localUpdate(null);
			if(!flag)
			    {
				tobesaved = true;
				return;
			    }
		    }
		else
		    {
			return;
		    }
	    }
	Properties prop = new Properties();
        
        String  newName = getNewName();
        prop.setProperty("name", newName);
        
        mainHashtable.put(newName, prop);
        mainVector.add(prop);
        
        addTreeNode(newName);

        tobesaved    = true;
        addOperation = true;

        applyButton.setEnabled(true);
        nameTextField.setEnabled (true);
    }

    private String getNewName()
    {
        int i = 0;
        while(true)
	    {
		String testName = "New_Filter" + i;//No i18n
		Object obj = mainHashtable.get(testName);
		if(obj == null)
		    {
			return testName;
		    }
		else
		    {
			i++;
		    }
	    }
    }
    
    private void treeValueChanged(javax.swing.event.TreeSelectionEvent evt) {
	
        TreePath oldPath = evt.getOldLeadSelectionPath();
        TreePath newPath = evt.getNewLeadSelectionPath();
	
        if(tobesaved)
	    {
		Properties prop = getCurrentUIProperties();
		int      option = JOptionPane.showConfirmDialog(this, NmsClientUtil.GetString("javaui.filter.savechanges"), NmsClientUtil.GetString("javaui.filter.confirmationmessage"), JOptionPane.YES_NO_OPTION);//No i18n
		if(option == JOptionPane.YES_OPTION)
		    {
			tobesaved = false;
			tree.setSelectionPath(oldPath);
			boolean flag = localUpdate(prop);
			if(flag)
			    {
				tree.setSelectionPath(newPath);       
			    }
			else
			    {
				tree.setSelectionPath(oldPath);
				tobesaved = true;
				addOperation = true;
				return;
			    }
		    }
		else
		    {
			if(addOperation)
			    {
				tobesaved = false;
				
				tree.setSelectionPath(oldPath);
				deleteNode();
				
				tree.setSelectionPath(newPath);
				addOperation = false;
			    }
		    }
	    }
        
        TreePath treepath = evt.getPath();
        Object   node     = treepath.getLastPathComponent();
        String   nodename = node.toString();
        
        currentProp = (Properties)mainHashtable.get(nodename);
	if(currentProp == null)
	    {
		currentProp = new Properties();
	    }

        setCurrentUIProperties(currentProp);
    }
    
    private void applyButtonActionPerformed(java.awt.event.ActionEvent evt) 
    {
        boolean flag = localUpdate(null);
	if(flag == false)
	    {
		return;
	    }
	
        Properties prop = getCurrentUIProperties();
        tobesaved       = false;

        String name = prop.getProperty("name");
	if(name != null && !name.trim().equals(""))
	    {
		DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
		DefaultMutableTreeNode root  = (DefaultMutableTreeNode)model.getRoot();
		
		int filterCount = root.getChildCount();
		if(filterCount <= 0)
		    {
			JOptionPane.showMessageDialog(this, NmsClientUtil.GetString("javaui.filter.nofilter"), NmsClientUtil.GetString("javaui.filter.errormessage"), JOptionPane.ERROR_MESSAGE);//No i18n
			return;
		    }
		mainHashtable.put(name, currentProp);
	    }
	
	applyFilterAction();
    }

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) 
    {
        applyButtonActionPerformed(evt);
        setVisible(false);
        dispose();
    }
    
    private void exitForm(java.awt.event.WindowEvent evt) 
    {
        setVisible(false);
        dispose();
    }
    
    public static void main(String args[]) 
    {
        new DefaultNmsFilterFrame().show();
    }
    
    public void actionPerformed(java.awt.event.ActionEvent e) 
    {
	if(e.getActionCommand().equals("Cancel"))
	    {
		this.setVisible(false);
		this.dispose();
	    }
    }
    
    private javax.swing.JTable actionTable;
    private javax.swing.JButton addActionButton;
    private javax.swing.JButton addFilterButton;
    private javax.swing.JButton advanceButton;
    private javax.swing.JButton applyButton;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JPanel criteriaPanel;
    private javax.swing.JButton deleteFilterButton;
    private javax.swing.JLabel headLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel namePanel;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JButton okButton;
    private javax.swing.JButton removeActionButton;
    private javax.swing.JTextField severity;
    private javax.swing.JButton severityButton;
    private javax.swing.JTextField sourceTextField;
    private javax.swing.JLabel status;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JPanel tablePanel;
    private javax.swing.JTree tree;

    public void showStatus(String str)
    { 
        status.setText(str);
    }

    public class MyRenderer extends DefaultTreeCellRenderer 
    {
        public MyRenderer() 
        {
        }
	
        public Component getTreeCellRendererComponent(JTree tree,
						      Object value,
						      boolean sel,
						      boolean expanded,
						      boolean leaf,
						      int row,
						      boolean hasFocus) 
        {
	    
            super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
            String val = value.toString();
            Icon  icon = getNmsIcon("images/filter.png");//No i18n
            setIcon(icon);
            setToolTipText(val);
            return this;
        }
    }
    
    public class FilterFileDialog extends javax.swing.JDialog 
    {
	public FilterFileDialog (java.awt.Frame parent, boolean modal) 
	{
	    super (parent, modal);
	    initComponents ();
	    filterFileName.setText(fileName);
	}
	
	private void initComponents() 
	{
	    java.awt.GridBagConstraints gridBagConstraints;
	    
	    loadMainPanel = new javax.swing.JPanel();
	    headfilelbl = new javax.swing.JLabel();
	    filenamelbl = new javax.swing.JLabel();
	    loadpanel = new javax.swing.JPanel();
	    pan1 = new javax.swing.JPanel();
	    pan2 = new javax.swing.JPanel();
	    pan3 = new javax.swing.JPanel();
	    loadButton = new javax.swing.JButton();
	    saveButton = new javax.swing.JButton();
	    closeloadButton = new javax.swing.JButton();
	    pan4 = new javax.swing.JPanel();
	    seplbl1 = new javax.swing.JSeparator();
	    seplbl2 = new javax.swing.JSeparator();
	    pan6 = new javax.swing.JPanel();
	    pan5 = new javax.swing.JPanel();
	    filterFileName = new javax.swing.JTextField();
	    loadFilterBrowserButton = new javax.swing.JButton();
	    
	    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
	    setTitle("Filter Details");//No i18n
	    loadMainPanel.setLayout(new java.awt.GridBagLayout());
	    
	    seplbl1.setPreferredSize(new java.awt.Dimension(401,5));
	    //loadMainPanel.setPreferredSize(new java.awt.Dimension(340, 140));
	    headfilelbl.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
	    headfilelbl.setText("Filter File");//No i18n
	    gridBagConstraints = new java.awt.GridBagConstraints();
	    gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 0);
	    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
	    loadMainPanel.add(headfilelbl, gridBagConstraints);
	    
	    filenamelbl.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
	    filenamelbl.setText("File Name");//No i18n
	    gridBagConstraints = new java.awt.GridBagConstraints();
	    gridBagConstraints.gridx = 0;
	    gridBagConstraints.gridy = 1;
	    gridBagConstraints.insets = new java.awt.Insets(10, 15, 10, 5);
	    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
	    loadMainPanel.add(filenamelbl, gridBagConstraints);
	    
	    loadpanel.setLayout(new java.awt.BorderLayout());
	    
	    pan1.setLayout(new java.awt.BorderLayout());
	    
	    pan2.setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(5, 5, 5, 5)));
	    pan1.add(pan2, java.awt.BorderLayout.CENTER);
	    
	    pan3.setLayout(new java.awt.GridLayout(1,0,5,0));
	    
	    pan3.setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(5, 5, 5, 10)));
	    loadButton.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
	    loadButton.setText("Load");//No i18n
	    loadButton.addActionListener(new java.awt.event.ActionListener() 
		{
		    public void actionPerformed(java.awt.event.ActionEvent evt) 
		    {
			loadButtonActionPerformed(evt);
		    }
		});
	    
	    pan3.add(loadButton);
	    
	    saveButton.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
	    saveButton.setText("Save");//No i18n
	    saveButton.addActionListener(new java.awt.event.ActionListener() 
		{
		    public void actionPerformed(java.awt.event.ActionEvent evt) 
		    {
			saveButtonActionPerformed(evt);
		    }
		});
	    pan3.add(saveButton);
	    
	    closeloadButton.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
	    closeloadButton.setText("Cancel");//No i18n
	    closeloadButton.addActionListener(new java.awt.event.ActionListener() 
		{
		    public void actionPerformed(java.awt.event.ActionEvent evt) 
		    {
			closeloadButtonActionPerformed(evt);
		    }
		});
	    
	    pan3.add(closeloadButton);
	    
	    pan1.add(pan3, java.awt.BorderLayout.EAST);
	    
	    loadpanel.add(pan1, java.awt.BorderLayout.CENTER);
	    
	    pan4.setLayout(new java.awt.BorderLayout());
	    
	    pan4.setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(5, 5, 5, 5)));
	    pan4.add(seplbl1, java.awt.BorderLayout.CENTER);
	    
	    loadpanel.add(pan4, java.awt.BorderLayout.NORTH);
	    
	    gridBagConstraints = new java.awt.GridBagConstraints();
	    gridBagConstraints.gridx = 0;
	    gridBagConstraints.gridy = 2;
	    gridBagConstraints.gridwidth = 2;
	    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
	    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
	    gridBagConstraints.weightx = 0.1;
	    loadMainPanel.add(loadpanel, gridBagConstraints);
	    
	    gridBagConstraints = new java.awt.GridBagConstraints();
	    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
	    gridBagConstraints.insets = new java.awt.Insets(15, 1, 10, 5);
	    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
	    loadMainPanel.add(seplbl2, gridBagConstraints);
	    
	    pan6.setLayout(new java.awt.BorderLayout());
	    
	    pan5.setLayout(new java.awt.BorderLayout());
	    
	    pan5.setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(1, 1, 1, 5)));
	    filterFileName.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
	    pan5.add(filterFileName, java.awt.BorderLayout.NORTH);
	    
	    pan6.add(pan5, java.awt.BorderLayout.CENTER);
	    
	    loadFilterBrowserButton.setFont(NmsClientUtil.getFont("DIALOG"));//No i18n
	    loadFilterBrowserButton.setIcon(getNmsIcon("images/browse.png"));//No i18n
	    loadFilterBrowserButton.setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(1, 1, 1, 1)));
	    loadFilterBrowserButton.setMargin(new java.awt.Insets(0,0,0,0));
	    loadFilterBrowserButton.addActionListener(new java.awt.event.ActionListener() 
		{
		    public void actionPerformed(java.awt.event.ActionEvent evt) 
		    {
			loadFilterBrowserButtonActionPerformed(evt);
		    }
		});
	    
	    pan6.add(loadFilterBrowserButton, java.awt.BorderLayout.EAST);
	    
	    gridBagConstraints = new java.awt.GridBagConstraints();
	    gridBagConstraints.gridx = 1;
	    gridBagConstraints.gridy = 1;
	    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
	    gridBagConstraints.insets = new java.awt.Insets(10, 5, 10, 15);
	    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
	    loadMainPanel.add(pan6, gridBagConstraints);
	    
	    getContentPane().add(loadMainPanel, java.awt.BorderLayout.CENTER);
	    
	    pack();
	}
	
	JFileChooser  chooser   = null;
	JFrame        frame     = null;
	SasFileDialog sasDialog = null;
	
	private void loadFilterBrowserButtonActionPerformed (java.awt.event.ActionEvent evt) 
	{
	    if(standAlone) 
		{
		    chooser = new JFileChooser(com.adventnet.nms.util.PureUtils.rootDir);
		    chooser.setApproveButtonText("Attach");//No i18n
		    loadFilterBrowserButton.addActionListener(new java.awt.event.ActionListener() 
			{
			    public void actionPerformed(java.awt.event.ActionEvent evt) 
			    {
				if((evt.getActionCommand()).equalsIgnoreCase("ApproveSelection"))
				    {
					String rtDir = com.adventnet.nms.util.PureUtils.rootDir;
					int    len   = rtDir.length();
					String fileUrl = "";
					try 
					    {
						File selectedFile = chooser.getSelectedFile();
						fileUrl = selectedFile.getCanonicalPath();
					    }
					catch(Exception e) 
					    {
						e.printStackTrace();
					    }
					
					filterFileName.setText(fileUrl);
				    }
			    }
			});
		    int retVal = chooser.showOpenDialog(new JFrame());
		}
	    else
		{
		    frame = new JFrame();
		    sasDialog = new  SasFileDialog(applet);
		    sasDialog.addActionListener(new java.awt.event.ActionListener() 
			{
			    public void actionPerformed(java.awt.event.ActionEvent evt) 
			    {
				String str = evt.getActionCommand();
				if(str.equalsIgnoreCase("Open"))
				    {
					if(!standAlone)
					    {
						String fileUrl = sasDialog.getSelectedFileUrl();
						frame.setVisible(false);
						frame.dispose();
						filterFileName.setText(fileUrl);
					    }
				    }                     
				else if(str.equalsIgnoreCase("Close"))
				    {
					frame.setVisible(false);
					frame.dispose();
				    }
			    }
			});            
		    
		    sasDialog.init();
		    sasDialog.setDirectory("");
		    sasDialog.setVisible(true);
		    frame.getContentPane().setLayout(new BorderLayout());
		    frame.getContentPane().add(sasDialog);
		    frame.setSize(150, 200);
		    frame.setTitle(NmsClientUtil.GetString(" File Attachment "));//No i18n
		    frame.pack();
		    NmsClientUtil.centerWindow(frame);
		    frame.setVisible(true);
		}
	}
	
	private void closeloadButtonActionPerformed (java.awt.event.ActionEvent evt) 
	{
	    this.setVisible(false);
	    this.dispose();
	}
	
	private void loadButtonActionPerformed (java.awt.event.ActionEvent evt) 
	{
	    fileName = get();
	    loadFileAction(fileName);
	    this.setVisible(false);
	    this.dispose();
	}
	
	private void saveButtonActionPerformed (java.awt.event.ActionEvent evt) 
	{

	    boolean flag = localUpdate(null);
	    if(flag == false)
		{
		    this.setVisible(false);
		    this.dispose();
		    return;
		}
	    
	    Properties prop = getCurrentUIProperties();
	    tobesaved       = false;
	    
	    String name = prop.getProperty("name");
	    if(name != null && !name.trim().equals(""))
		{
		    mainHashtable.put(name, currentProp);
		}
	    
	    fileName = get();
	    saveFileAction(fileName);
	    this.setVisible(false);
	    this.dispose();
	}
	
	public void set(String fileName)
	{
	    if(fileName == null)
		{
		    return;
		}
	    filterFileName.setText(fileName);
	}
	
	public String get()
	{
	    String fn = filterFileName.getText();
	    return fn;
	}
    
	private javax.swing.JButton closeloadButton;
	private javax.swing.JButton saveButton;
	private javax.swing.JLabel filenamelbl;
	private javax.swing.JTextField filterFileName;
	private javax.swing.JLabel headfilelbl;
	private javax.swing.JButton loadButton;
	private javax.swing.JButton loadFilterBrowserButton;
	private javax.swing.JPanel loadMainPanel;
	private javax.swing.JPanel loadpanel;
	private javax.swing.JPanel pan1;
	private javax.swing.JPanel pan2;
	private javax.swing.JPanel pan3;
	private javax.swing.JPanel pan4;
	private javax.swing.JPanel pan5;
	private javax.swing.JPanel pan6;
	private javax.swing.JSeparator seplbl1;
	private javax.swing.JSeparator seplbl2;
    }
}
