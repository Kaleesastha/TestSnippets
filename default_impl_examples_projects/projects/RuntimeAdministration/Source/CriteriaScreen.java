//$Id: CriteriaScreen.java,v 1.1.6.1 2012/01/25 05:12:46 karen.r Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.runtimeconfig;

import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.text.*;

import com.adventnet.nms.util.SeedParser;
import com.adventnet.nms.util.XMLNode;

public class CriteriaScreen extends JPanel implements ActionListener
{
    //<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "RuntimeAdministrationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel tipsPanel = null;
	javax.swing.JLabel lfImage = null;
	javax.swing.JTextArea taCrit = null;
	javax.swing.JPanel tablePanel = null;
	javax.swing.JScrollPane tableScroller = null;
	javax.swing.JTable table = null;
	javax.swing.JPanel butPanel = null;
	javax.swing.JButton addButton = null;
	javax.swing.JButton delButton = null;
	javax.swing.JButton modButton = null;
	javax.swing.JPanel selectionPanel = null;
	javax.swing.JComboBox comboPropName = null;
	javax.swing.JLabel lfSelect = null;
	javax.swing.JLabel lfPropValue = null;
	javax.swing.JTextField tfPropValue = null;
	javax.swing.JCheckBox chbAllow = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>
        MyTableModel model = null;
    SeedParser sParser = null;
	boolean isModified = false; 

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
        tipsPanel= new javax.swing.JPanel();
        lfImage= new javax.swing.JLabel();
        taCrit= new javax.swing.JTextArea();
        tablePanel= new javax.swing.JPanel();
        tableScroller= new javax.swing.JScrollPane();
        table= new javax.swing.JTable();
        butPanel= new javax.swing.JPanel();
        addButton= new javax.swing.JButton();
        delButton= new javax.swing.JButton();
        modButton= new javax.swing.JButton();
        selectionPanel= new javax.swing.JPanel();
        comboPropName= new javax.swing.JComboBox();
        lfSelect= new javax.swing.JLabel();
        lfPropValue= new javax.swing.JLabel();
        tfPropValue= new javax.swing.JTextField();
        chbAllow= new javax.swing.JCheckBox();
  
        //<End_initVariables>
                ListSelectionModel rowSM = table.getSelectionModel();
        rowSM.addListSelectionListener(new ListSelectionListener() 
            {
                public void valueChanged(ListSelectionEvent e) 
                {
                    int row = table.getSelectedRow();
                    if(row == -1)
                    {
                        return;
                    }
                    chbAllow.setSelected(Boolean.valueOf((String)table.getValueAt(row,0)));
                    for(int i=0;i<comboPropName.getItemCount();i++)
                    {
                        if(table.getValueAt(row,1).equals(comboPropName.getItemAt(i)))
                        {
                            comboPropName.setSelectedIndex(i);
                        }
                    }
                    tfPropValue.setText((String)table.getValueAt(row,2));
                    delButton.setEnabled(true);
                    modButton.setEnabled(true);      
                }
            });
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
     
    public void setUpGUI(Container container)
  { 

        //<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,0,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
Top.add(tipsPanel,cons);
tipsPanel.setLayout(new BorderLayout(5,5));
tipsPanel.add(lfImage,BorderLayout.WEST);
tipsPanel.add(taCrit,BorderLayout.CENTER);
inset = new Insets(5,5,5,5);
setConstraints(0,1,0,1,0.1,0.1,cons.WEST,cons.BOTH,inset,0,0);
Top.add(tablePanel,cons);
tablePanel.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,0,1,0.1,0.1,cons.WEST,cons.BOTH,inset,0,0);
tablePanel.add(tableScroller,cons);
tableScroller.getViewport().add(table);
inset = new Insets(5,5,5,5);
setConstraints(0,3,0,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
Top.add(butPanel,cons);
butPanel.setLayout(new GridBagLayout());
inset = new Insets(10,10,10,20);
setConstraints(0,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
butPanel.add(addButton,cons);
inset = new Insets(10,20,10,20);
setConstraints(1,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
butPanel.add(delButton,cons);
inset = new Insets(10,20,10,10);
setConstraints(2,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
butPanel.add(modButton,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,2,0,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
Top.add(selectionPanel,cons);
selectionPanel.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(1,1,0,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
selectionPanel.add(comboPropName,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
selectionPanel.add(lfSelect,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
selectionPanel.add(lfPropValue,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,2,0,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
selectionPanel.add(tfPropValue,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
selectionPanel.add(chbAllow,cons);

  
//<End_setUpGUI_Container>
	table.getTableHeader().setReorderingAllowed(false);
	addButton.addActionListener(this);
    delButton.addActionListener(this);
    modButton.addActionListener(this);
	addButton.setMnemonic(KeyEvent.VK_A); 
   	delButton.setMnemonic(KeyEvent.VK_D); 
   	modButton.setMnemonic(KeyEvent.VK_M); 
   comboPropName.addKeyListener(new KeyAdapter()
            {
                public void keyPressed(KeyEvent ke)
                {
                    if(ke.getKeyCode()== ke.VK_ENTER)
                    {
                        Add_Clicked();
                    }
                } 
            });
    
	comboPropName.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String propName = ((String)comboPropName.getSelectedItem()).trim();
				if(propName.equals("ipAddress"))
				{
					tfPropValue.setText("");
				}
			}
		});

        tfPropValue.addKeyListener(new KeyAdapter()
            {
		public void keyTyped(KeyEvent ke)
                {
			String propName = ((String)comboPropName.getSelectedItem()).trim();
             		if(propName.equals("ipAddress")) 
			{
				String value = tfPropValue.getText().trim();
				if((ke.getKeyChar() >= '0' && ke.getKeyChar() <='9') || (ke.getKeyChar() == '.') || (ke.getKeyChar()== ke.VK_BACK_SPACE) || (ke.getKeyChar() == '!') || (ke.getKeyChar() == '*'))
          			{
                                	//DO Nothing
             			}
				else
				{
					ke.consume();
					Toolkit.getDefaultToolkit().beep();
				}

			}
        	}

                public void keyPressed(KeyEvent ke)
                {
                    if(ke.getKeyCode()== ke.VK_ENTER)
                    {
                        Add_Clicked();
                    }
                } 
            });
        
        addButton.addKeyListener(new KeyAdapter()
            {
                public void keyPressed(KeyEvent ke)
                {
                    if(ke.getKeyCode()== ke.VK_ENTER)
                    {
                        Add_Clicked();
                    }
                } 
            });
        
        delButton.addKeyListener(new KeyAdapter()
            {
                public void keyPressed(KeyEvent ke)
                {
                    if(ke.getKeyCode()== ke.VK_DELETE)
                    {
                        Delete_Clicked();
                    }
                } 
            });
       
    } 
    public void setUpProperties()
  { 

        //<Begin_setUpProperties> 

          try
          {
            taCrit.setLineWrap(true);
            taCrit.setWrapStyleWord(true);
            taCrit.setEditable(false);
            taCrit.setText(resourceBundle.getString("Specify the Managed Object properties based on which discovery can be enabled/disabled."));
            taCrit.setFont(new Font("Dialog",0,10));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+taCrit,ex); 
          }

//<UserCode_Begin_Bean_taCrit>

//<UserCode_End_Bean_taCrit>

          try
          {
            tablePanel.setBorder(new javax.swing.border.LineBorder(new Color(-13434880),1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+tablePanel,ex); 
          }

//<UserCode_Begin_Bean_tablePanel>

//<UserCode_End_Bean_tablePanel>

          try
          {
            table.setAutoResizeMode(4);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+table,ex); 
          }

//<UserCode_Begin_Bean_table>

//<UserCode_End_Bean_table>

          try
          {
            butPanel.setBorder(new javax.swing.border.EtchedBorder(0));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+butPanel,ex); 
          }

//<UserCode_Begin_Bean_butPanel>

//<UserCode_End_Bean_butPanel>

          try
          {
            addButton.setBorder(new javax.swing.border.BevelBorder(0));
            addButton.setHorizontalTextPosition(0);
            addButton.setLabel(resourceBundle.getString("Add"));
            addButton.setMaximumSize(new Dimension(73,27));
            addButton.setMinimumSize(new Dimension(73,27));
            addButton.setPreferredSize(new Dimension(73,27));
            addButton.setToolTipText(resourceBundle.getString("Click here to Add a row to the table."));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+addButton,ex); 
          }

//<UserCode_Begin_Bean_addButton>

//<UserCode_End_Bean_addButton>

          try
          {
            delButton.setLabel(resourceBundle.getString("Delete"));
            delButton.setBorder(new javax.swing.border.BevelBorder(0));
            delButton.setEnabled(false);
            delButton.setHorizontalTextPosition(0);
            delButton.setText(resourceBundle.getString("Delete"));
            delButton.setMaximumSize(new Dimension(73,27));
            delButton.setMinimumSize(new Dimension(73,27));
            delButton.setPreferredSize(new Dimension(73,27));
            delButton.setToolTipText(resourceBundle.getString("Click here to Delete a row from the table."));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+delButton,ex); 
          }

//<UserCode_Begin_Bean_delButton>

//<UserCode_End_Bean_delButton>

          try
          {
            modButton.setBorder(new javax.swing.border.BevelBorder(0));
            modButton.setEnabled(false);
            modButton.setHorizontalTextPosition(0);
            modButton.setLabel(resourceBundle.getString("Modify"));
            modButton.setMaximumSize(new Dimension(73,27));
            modButton.setMinimumSize(new Dimension(73,27));
            modButton.setPreferredSize(new Dimension(73,27));
            modButton.setToolTipText(resourceBundle.getString("Click here to Modify an existing row in the table."));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+modButton,ex); 
          }

//<UserCode_Begin_Bean_modButton>

//<UserCode_End_Bean_modButton>

          try
          {
            comboPropName.setToolTipText(resourceBundle.getString("Select"));
            comboPropName.setEditable(true);
            comboPropName.setBorder(new javax.swing.border.EtchedBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+comboPropName,ex); 
          }

//<UserCode_Begin_Bean_comboPropName>

//<UserCode_End_Bean_comboPropName>

          try
          {
            lfSelect.setForeground(new Color(-16777214));
            lfSelect.setText(resourceBundle.getString("Property Name"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfSelect,ex); 
          }

//<UserCode_Begin_Bean_lfSelect>

//<UserCode_End_Bean_lfSelect>

          try
          {
            lfPropValue.setText(resourceBundle.getString("Property Value"));
            lfPropValue.setForeground(new Color(-16777216));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfPropValue,ex); 
          }

//<UserCode_Begin_Bean_lfPropValue>

//<UserCode_End_Bean_lfPropValue>

          try
          {
            tfPropValue.setToolTipText(resourceBundle.getString("Property Value"));
            tfPropValue.setBorder(new javax.swing.border.BevelBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+tfPropValue,ex); 
          }

//<UserCode_Begin_Bean_tfPropValue>

//<UserCode_End_Bean_tfPropValue>

	  try
          {
            chbAllow.setText(resourceBundle.getString("Allow Criteria"));
            chbAllow.setToolTipText(resourceBundle.getString("Allow Criteria"));
            chbAllow.setSelected(true);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+chbAllow,ex); 
          }

//<UserCode_Begin_Bean_chbAllow>

//<UserCode_End_Bean_chbAllow>
		taCrit.setPreferredSize(new Dimension(taCrit.getPreferredSize().width+0,taCrit.getPreferredSize().height+5));

  
          //<End_setUpProperties>
	lfImage.setHorizontalAlignment(0);
        lfImage.setHorizontalTextPosition(0);
       lfImage.setIcon(MainScreen.getCommonInterface().getImage("tips.png","images/runtimeadmin"));
        taCrit.setBackground(tipsPanel.getBackground());
        taCrit.setForeground(Color.black);
        taCrit.setFont(new Font("Dialog",0,11));
        //addButton.setPreferredSize(delButton.getPreferredSize());
    
    } 

     public void populateObjectAllowCrit()
    {
        Vector temp = sParser.getXMLNode("ALLOW_CRITERIA");
              
        XMLNode tempNode = null;
        StringTokenizer strTok = null;
        Hashtable hTable = new Hashtable();
        if(temp!= null) 
        {
            for (int i=0; i<temp.size(); i++)
            {
                tempNode = (XMLNode)temp.elementAt(i);
                String key = null;
                String value = null;
                if(tempNode!= null)
                {
                    Hashtable hTab = tempNode.getAttributeList();
                    if(hTab != null)
                    {
                        for(Enumeration en = hTab.keys();en.hasMoreElements();) 
                        {
                            key = (String)en.nextElement();
                            if(hTab.get(key) != null)
                            {
                                value = (String)hTab.get(key);
                            }
                            if(hTable.containsKey(key))
                            {
                                String newValue = ((String)hTable.get(key)).trim()+" "+value;
                                hTable.put(key,newValue);  
                            } 
                            else 
                            {
                                hTable.put(key,value);
                            }
                        }
                    }
                }
            }
            if(hTable != null)
            {
                for(Enumeration en = hTable.keys();en.hasMoreElements();)
                {
                    String propName = (String)en.nextElement();
                    strTok = new StringTokenizer((String)hTable.get(propName));
                    while(strTok.hasMoreTokens())
                    {
                        Object rowData[] = new Object[3];
                        rowData[0] = new String("true");//No I18N
                        rowData[1] = propName;
                        rowData[2] = strTok.nextToken();
                        ((DefaultTableModel)table.getModel()).addRow(rowData);
                        Vector vec = new Vector();
                        for(int i=0;i<comboPropName.getItemCount();i++)
                        {
                            vec.addElement(comboPropName.getItemAt(i));
                        }

                        if(!(vec.contains(propName)))
                        {
                            comboPropName.addItem(propName);
                            //break;
                        }
                    }
                }
            }
        }
    }

    public void populateObjectDisallowCrit() 
    {
        Vector temp = sParser.getXMLNode("DISALLOW_CRITERIA");
              
        XMLNode tempNode = null;
        StringTokenizer strTok = null;
        Hashtable hTable = new Hashtable();
        if(temp!= null) 
        {
            for (int i=0; i<temp.size(); i++)
            {
                tempNode = (XMLNode)temp.elementAt(i);
                String key = null;
                String value = null;
                if(tempNode!= null)
                {
                    Hashtable hTab = tempNode.getAttributeList();
                    if(hTab != null)
                    {
                        for(Enumeration en = hTab.keys();en.hasMoreElements();) 
                        {
                            key = (String)en.nextElement();
                            if(hTab.get(key) != null)
                            {
                                value = (String)hTab.get(key);
                            }
                            if(hTable.containsKey(key))
                            {
                                String newValue = ((String)hTable.get(key)).trim()+" "+value;
                                hTable.put(key,newValue);  
                            } 
                            else 
                            {
                                hTable.put(key,value);
                            }
                        }
                    }
                }
               
            }
            if(hTable != null)
            {
                for(Enumeration en = hTable.keys();en.hasMoreElements();)
                {
                    String propName = (String)en.nextElement();
                    strTok = new StringTokenizer((String)hTable.get(propName));
                    while(strTok.hasMoreTokens())
                    {
                        Object rowData[] = new Object[3];
                        rowData[0] = new String("false");//No I18N
                        rowData[1] = propName;
                        rowData[2] = strTok.nextToken();
                        ((DefaultTableModel)table.getModel()).addRow(rowData);
                        Vector vec = new Vector();
                        for(int i=0;i<comboPropName.getItemCount();i++)
                        {
                            vec.addElement(comboPropName.getItemAt(i));
                        }
                        
                       if(!(vec.contains(propName)))
                        {
                            comboPropName.addItem(propName);
	                    }
                        
                    }
                }
            }
        }  
        
    }

    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()== addButton)
        {
            Add_Clicked();
        }
        else if (ae.getSource()== delButton)
        {
            Delete_Clicked();
        }
        else if (ae.getSource() == modButton)
        {
            Modify_Clicked();
        }
    }
    
    public void Add_Clicked()
    {    
        
        String propName = ((String)comboPropName.getSelectedItem()).trim();
		for(int i=0; i<=9; i++)
		{
			String number = Integer.toString(i);
			if(propName.startsWith(number))
			{ 
       			 JOptionPane.showMessageDialog(this,resourceBundle.getString("First character should not be a Number."),resourceBundle.getString("Information"),JOptionPane.INFORMATION_MESSAGE); 
       			 return;	
			} 
		}
        if(propName.indexOf(" ")>0)
        {
            JOptionPane.showMessageDialog(this,resourceBundle.getString("Sorry!Only AlphaNumeric characters are allowed in Property Name."),resourceBundle.getString("Information"),JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        String errMsg =  resourceBundle.getString("Please enter ");
         
        //Error Message Construction
        if(comboPropName.getSelectedItem() == null ||((String)comboPropName.getSelectedItem()).trim().length() == 0)
        {
            errMsg = errMsg + resourceBundle.getString("< Property Name > ");
        }

        if(tfPropValue.getText().trim().length()== 0)
        {
            errMsg = errMsg + resourceBundle.getString("< Property Value > ");
        }
        if(tfPropValue.getText().endsWith(".")) 
	{
		errMsg = resourceBundle.getString("javaui.criteriatab.validateip");
	}
	String propertyValue = tfPropValue.getText().trim();
	if(propName.equals("ipAddress"))
	{
		StringTokenizer strTok = new StringTokenizer(propertyValue,"."); //No I18N
		boolean showErrMessage = false;
		if(strTok.countTokens() > 4)
		{
			showErrMessage = true;	
		}
		else
		{
		int count = 0;
		while(strTok.hasMoreElements())
		{
			count = count + 1;
			String st = strTok.nextToken();
			int len = st.length();
			if((count == 1 && len > 4) || (count != 1 && len>3))
			{
				showErrMessage = true;
				break;
			}
		}
		}
		if(showErrMessage)
		{
			errMsg = resourceBundle.getString("javaui.criteriatab.validateip");
		}
	}
        if(errMsg.equals(resourceBundle.getString("Please enter ")))
        {
            //String propName = new String();
            String propValue = new String();
             
            //propName = ((String)comboPropName.getSelectedItem()).trim();
            propValue = tfPropValue.getText().trim();
            for(int i=0;i<table.getRowCount();i++)
            {
                if(((String)table.getValueAt(i,1)).trim().equals(propName) &&((String)table.getValueAt(i,2)).trim().equals(propValue))
                {
                    JOptionPane.showMessageDialog(this,resourceBundle.getString("This value has already been entered in the table."),resourceBundle.getString("Information"),JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            }          
            Object rowData[] = {String.valueOf(chbAllow.isSelected()),propName,propValue};
            ((DefaultTableModel)table.getModel()).addRow(rowData);
            isModified = true;
            Vector vec = new Vector();
            for(int i=0;i<comboPropName.getItemCount();i++)
            {
                vec.addElement(comboPropName.getItemAt(i));
            }
            
            if(!(vec.contains(propName)))
            {
                comboPropName.addItem(propName);
            }
                chbAllow.setSelected(true);
            }
        
        else 
        {
            //show Error Message
            JOptionPane.showMessageDialog(this,errMsg,resourceBundle.getString("Information"),JOptionPane.INFORMATION_MESSAGE);
        }
        
		MainScreen.setApplyButton(true);
    }
    
    public void Delete_Clicked()
    { 
        
        if ( table.getSelectedRowCount() != 0)
        {
            ((DefaultTableModel)table.getModel()).removeRow(table.getSelectedRow());
            tfPropValue.setText("");
            chbAllow.setSelected(true);
            delButton.setEnabled(false);
            modButton.setEnabled(false);
            isModified = true;
        }
        
        else
        {        
            JOptionPane.showMessageDialog(this,resourceBundle.getString("You have not selected any item in the table."),resourceBundle.getString("Information"),JOptionPane.INFORMATION_MESSAGE);
        }
		MainScreen.setApplyButton(true);
    }
    
    public void Modify_Clicked()
    {
        
        int row = table.getSelectedRow();
        
        if(row == -1) 
        {
            return;
        }    
        
        for(int i=0;i<table.getRowCount();i++)
        {
            if( i != row)
            {
               if(((String)table.getValueAt(i,1)).trim().equals(((String)comboPropName.getSelectedItem()).trim()) &&((String)table.getValueAt(i,2)).trim().equals(tfPropValue.getText().trim()))
                {
                    JOptionPane.showMessageDialog(this,resourceBundle.getString("This value has already been entered in the table."),resourceBundle.getString("Information"),JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            }
        }       

        String errMsg =  resourceBundle.getString("Please enter ");
        
        //Error Message Construction
        if(comboPropName.getSelectedItem() == null ||((String)comboPropName.getSelectedItem()).trim().length() == 0)
        {
            errMsg = errMsg + resourceBundle.getString("< Property Name > ");
        }
        
        if(comboPropName.getSelectedItem()!= null && ((String)comboPropName.getSelectedItem()).trim().length() != 0 && tfPropValue.getText().trim().length()== 0)
        {
            errMsg = errMsg + resourceBundle.getString("< Property Value > ");
        }
	String propertyValue = tfPropValue.getText().trim();
	if(propertyValue.endsWith("."))
	{
		errMsg = null;
		errMsg = resourceBundle.getString("javaui.criteriatab.validateip");
	}

	String propertyName = ((String)comboPropName.getSelectedItem()).trim();
	if(propertyName.equals("ipAddress"))
	{
		StringTokenizer strTok = new StringTokenizer(propertyValue,"."); //No I18N
		boolean showErrMessage = false;
		if(strTok.countTokens() > 4)
		{
			showErrMessage = true;
		}
		else
		{
		int count = 0;
		while(strTok.hasMoreElements())
		{
			count = count + 1;
			String st = strTok.nextToken();
			int len = st.length();
			if((count == 1 && len > 4) || (count != 1 && len>3))
			{
				showErrMessage = true;
				break;
			}
		}
		}
		if(showErrMessage)
		{
			errMsg = null;
			errMsg = resourceBundle.getString("javaui.criteriatab.validateip");
		}
	}	
        if(errMsg.equals(resourceBundle.getString("Please enter ")))
        {
            String propName = new String();
            String propValue = new String();
            
            propName = ((String)comboPropName.getSelectedItem()).trim();
            propValue = tfPropValue.getText().trim();
           
            table.setValueAt(String.valueOf(chbAllow.isSelected()),row,0); 
            table.setValueAt(propName,row,1);
            table.setValueAt(propValue,row,2);
            table.clearSelection();
            tfPropValue.setText("");
            delButton.setEnabled(false);
            modButton.setEnabled(false);
            chbAllow.setSelected(true);
            isModified = true;

        }
        else 
        {
            //show Error Message
            JOptionPane.showMessageDialog(this,errMsg,resourceBundle.getString("Information"),JOptionPane.INFORMATION_MESSAGE);
        } 
		MainScreen.setApplyButton(true);
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
            if (input.equals("RESOURCE_PROPERTIES")) value = "RuntimeAdministrationResources"; 
            if (input.equals("PORT")) value = "161"; 
            }
        return value;

  
           //<End_getParameter_String>
    } 

    public void init(java.applet.Applet app)
	{
		this.applet = app;
		init();
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
        setPreferredSize(new Dimension(getPreferredSize().width+651,getPreferredSize().height+423)); 
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
Object tableHdr[]= {resourceBundle.getString("Allow Criteria"),resourceBundle.getString("Property Name"),resourceBundle.getString("Property Value")};
		comboPropName.addItem("name");
        comboPropName.addItem("type");
        comboPropName.addItem("ipAddress");
        comboPropName.addItem("sysOID");
        comboPropName.addItem("isSNMP");
        model = new MyTableModel(tableHdr,0);
		table.setModel(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    } 

     public Vector addToXMLNode()
    {
        XMLNode nodeAllow = new XMLNode();
        nodeAllow.setNodeType(XMLNode.ELEMENT);
        nodeAllow.setNodeName("ALLOW_CRITERIA");
        
        XMLNode nodeDisallow = new XMLNode();
        nodeDisallow.setNodeType(XMLNode.ELEMENT);
        nodeDisallow.setNodeName("DISALLOW_CRITERIA");
        
        for(int row=0;row<table.getRowCount();row++)
        {
            if(((String)table.getValueAt(row,0)).equals("true"))//No I18N 
            {
                if((nodeAllow.getAttribute((String)table.getValueAt(row,1)) != null))
                {
                    String value = (nodeAllow.getAttribute((String)table.getValueAt(row,1))).toString();
                    String newValue = value + " " + ((String)table.getValueAt(row,2)).toString();
                    
                    nodeAllow.setAttribute((String)table.getValueAt(row,1),newValue);
                }
                else
                {
                    nodeAllow.setAttribute((String)table.getValueAt(row,1),(String)table.getValueAt(row,2));
                }
            }
            
            else if(((String)table.getValueAt(row,0)).equals("false"))//No I18N 
            {
                if((nodeDisallow.getAttribute((String)table.getValueAt(row,1)) != null))
                {
                    String value = (nodeDisallow.getAttribute((String)table.getValueAt(row,1))).toString();
                    String newValue = value + " " + (String)table.getValueAt(row,2);
                    
                    nodeDisallow.setAttribute((String)table.getValueAt(row,1),newValue);
                }
                else
                {
                    nodeDisallow.setAttribute((String)table.getValueAt(row,1),(String)table.getValueAt(row,2));
                }
            }

        }
        Vector xmlNodes = new Vector();
        xmlNodes.addElement(nodeAllow);
        xmlNodes.addElement(nodeDisallow);
            
        return xmlNodes;
    }
      
    
    /**
     * Get the value of isModified.
     * @return value of isModified.
     */
    public boolean getIsModified() 
    {
        return isModified;
    }
    
    /**
     * Set the value of isModified.
     * @param v  Value to assign to isModified.
     */
    public void setIsModified(boolean val) 
    {
        isModified = val;
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

    public CriteriaScreen(SeedParser parser)
    {
        sParser = parser;
    }

    public CriteriaScreen()
  {
        //<Begin_CriteriaScreen>
    this.init();
  
    //<End_CriteriaScreen>
    }

    public CriteriaScreen(java.applet.Applet applet)
  {
        //<Begin_CriteriaScreen_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_CriteriaScreen_java.applet.Applet>
    }

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}

class MyTableModel extends DefaultTableModel
{
    MyTableModel(Object[]Header,int num)
    {
        super(Header,0);
    } 
    public boolean isCellEditable(int row,int col) 
    {
        return false;
    }
    public Class getColumnClass(int col)
    {
        if(col == 0)
        { 
            return new String("false").getClass();//No I18N
        }
        else
        {
            return new Object().getClass();
            
        }
    }
}  

