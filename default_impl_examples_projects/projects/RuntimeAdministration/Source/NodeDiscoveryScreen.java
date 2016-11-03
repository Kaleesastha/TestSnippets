//$Id: NodeDiscoveryScreen.java,v 1.3.4.1 2012/01/25 05:12:46 karen.r Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

//<Begin_Version>
//version$63
//<End_Version>
package com.adventnet.nms.runtimeconfig;

import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.applet.*;

import com.adventnet.nms.util.XMLNode;
import com.adventnet.nms.util.SeedParser;


public class NodeDiscoveryScreen extends JPanel implements ActionListener
{
    //<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "RuntimeAdministrationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel entriesPanel = null;
	javax.swing.JLabel lfUser = null;
	javax.swing.JTextField tfUser = null;
	javax.swing.JTextField tfContext = null;
	javax.swing.JLabel lfContext = null;
	javax.swing.JLabel lfComm = null;
	javax.swing.JLabel lfPort = null;
	javax.swing.JLabel lfVersion = null;
	javax.swing.JTextField tfPort = null;
	javax.swing.JComboBox comboVersion = null;
	javax.swing.JLabel lfIPAddr = null;
	javax.swing.JLabel lfNetMask = null;
	javax.swing.JCheckBox chbDisc = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JCheckBox parentNetCheck = null;
	javax.swing.JButton v3PropButton = null;
	javax.swing.JTextField tfComm = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JComboBox jcb1 = null;
	javax.swing.JComboBox jcb2 = null;
	javax.swing.JComboBox jcb3 = null;
	javax.swing.JComboBox jcb4 = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JComboBox comboIPAddr = null;
	javax.swing.JButton addMore = null;
	javax.swing.JPanel tablePanel = null;
	javax.swing.JScrollPane tableScroller = null;
	javax.swing.JTable table = null;
	javax.swing.JPanel butPanel = null;
	javax.swing.JButton addButton = null;
	javax.swing.JButton delButton = null;
	javax.swing.JButton modButton = null;
	javax.swing.JPanel imagePanel = null;
	javax.swing.JLabel lfImage = null;
	javax.swing.JTextArea taNode = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>
     MyTableModel model = null;
    SeedParser sParser = null;
    
    Vector toDiscoverNode = new Vector();
    Vector notDiscoverNode = new Vector();
    boolean isModified = false;
    V3Properties v3Prop = null;
    String ipAddr = null;
    Vector nodeVector = new Vector();
        
    public void start()
  { 

        //<Begin_start> 
       if(running)
 return;
 running=true;

  
       //<End_start>
    //chbDisc.setEnabled(true);
    //chbDisc.setSelected(true);
    //chbDisc.doClick();
    
    } 
    public void initVariables()
  { 

        //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        entriesPanel= new javax.swing.JPanel();
        lfUser= new javax.swing.JLabel();
        tfUser= new javax.swing.JTextField();
        tfContext= new javax.swing.JTextField();
        lfContext= new javax.swing.JLabel();
        lfComm= new javax.swing.JLabel();
        lfPort= new javax.swing.JLabel();
        lfVersion= new javax.swing.JLabel();
        tfPort= new javax.swing.JTextField();
        comboVersion= new javax.swing.JComboBox();
        lfIPAddr= new javax.swing.JLabel();
        lfNetMask= new javax.swing.JLabel();
        chbDisc= new javax.swing.JCheckBox();
        JLabel1= new javax.swing.JLabel();
        parentNetCheck= new javax.swing.JCheckBox();
        v3PropButton= new javax.swing.JButton();
        tfComm= new javax.swing.JTextField();
        JPanel1= new javax.swing.JPanel();
        jcb1= new javax.swing.JComboBox();
        jcb2= new javax.swing.JComboBox();
        jcb3= new javax.swing.JComboBox();
        jcb4= new javax.swing.JComboBox();
        JPanel2= new javax.swing.JPanel();
        comboIPAddr= new javax.swing.JComboBox();
        addMore= new javax.swing.JButton();
        tablePanel= new javax.swing.JPanel();
        tableScroller= new javax.swing.JScrollPane();
        table= new javax.swing.JTable();
        butPanel= new javax.swing.JPanel();
        addButton= new javax.swing.JButton();
        delButton= new javax.swing.JButton();
        modButton= new javax.swing.JButton();
        imagePanel= new javax.swing.JPanel();
        lfImage= new javax.swing.JLabel();
        taNode= new javax.swing.JTextArea();

  
        //<End_initVariables>
              
              
	parentNetCheck.setSelected(true);
        delButton.setEnabled(false);
        modButton.setEnabled(false);
        lfUser.setEnabled(false);
      	tfUser.setEditable(false);
        lfContext.setEnabled(false);
        tfContext.setEditable(false);   
        v3PropButton.setEnabled(false);
        //v3Prop = new V3Properties();
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
                      chbDisc.setSelected(Boolean.valueOf((String)table.getValueAt(row,0)));
                    
		if(chbDisc.isSelected())
		{
		     	JLabel1.setEnabled(true);
			parentNetCheck.setEnabled(true);
		           String str = (String)table.getValueAt(row,1);
			if(str.equals("true"))
			{
				parentNetCheck.setSelected(true);
                                parentNetCheck.setEnabled(false); //modified for Lucent	
			}
			else
			{
				parentNetCheck.setSelected(false);
                                  parentNetCheck.setEnabled(true); //modified for Lucent
			}
		}
		else
		{
			JLabel1.setEnabled(false);
			parentNetCheck.setEnabled(false);
		}
                    
                    if(table.getValueAt(row,2) != null)
                    {
                       comboIPAddr.removeAllItems();  
                       comboIPAddr.configureEditor(comboIPAddr.getEditor(),(String)table.getValueAt(row,2));
                    }
                    if(table.getValueAt(row,3) != null)
                    {
 //                        tfNetMask.setText((String)table.getValueAt(row,3));
 		String str=(String)table.getValueAt(row,3); 
    
	           StringTokenizer strToken = new StringTokenizer(str,"."); 
	           String[] arr=new String[4]; 
	           int i=0; 
	           if (strToken != null) 
	           { 
               	    while(strToken.hasMoreTokens()) 
                   	{ 
	                   arr[i]= strToken.nextToken(); 
                   	    i++; 
		} 
	          } 
                 
                   jcb1.setSelectedItem(arr[0]); 
                   jcb2.setSelectedItem(arr[1]); 
                   jcb3.setSelectedItem(arr[2]); 
                   jcb4.setSelectedItem(arr[3]); 

                     }
		if(((String)table.getValueAt(row,0)).equals("true"))
		{
		lfComm.setEnabled(true);
		    tfComm.setEditable(true);
			lfPort.setEnabled(true);
		    lfVersion.setEnabled(true);
		    tfPort.setEditable(true);
		    comboVersion.setEnabled(true);
                    if(table.getValueAt(row,5) != null)
                    {
                        tfPort.setText((String)table.getValueAt(row,5));
                    }
                    if(table.getValueAt(row,6)!= null)
                    {
                        if(table.getValueAt(row,6).equals(resourceBundle.getString("v1")))
                        {
                            comboVersion.setSelectedIndex(0);
                            tfUser.setText("");
                            tfContext.setText("");
			lfUser.setEnabled(false);
		    tfUser.setEditable(false);
		    tfContext.setEditable(false);
		    lfContext.setEnabled(false);
                            if(table.getValueAt(row,4) != null)
                            {
                                tfComm.setText((String)table.getValueAt(row,4));
                            }
                        }
                        else if(table.getValueAt(row,6).equals(resourceBundle.getString("v2")))
                        {
                            comboVersion.setSelectedIndex(1);
                            tfUser.setText("");
                            tfContext.setText("");
			lfUser.setEnabled(false);
		    tfUser.setEditable(false);
		    tfContext.setEditable(false);
		    lfContext.setEnabled(false);
                            if(table.getValueAt(row,4) != null)
                            {
                                tfComm.setText((String)table.getValueAt(row,4));
                            }
                        }
                        else if(table.getValueAt(row,6).equals(resourceBundle.getString("v3")))
                        {
                            comboVersion.setSelectedIndex(2);
			lfUser.setEnabled(true);
		    tfUser.setEditable(true);
		    tfContext.setEditable(true);
		    lfContext.setEnabled(true);
                            if(table.getValueAt(row,7) != null)
                            {
                                tfUser.setText((String)table.getValueAt(row,7));
                            }
                            if(table.getValueAt(row,8) != null)
                            {
                                tfContext.setText((String)table.getValueAt(row,8));
                            }
                        }
		}
                    }
		else if(((String)table.getValueAt(row,0)).equals("false"))
		{
		comboVersion.setSelectedIndex(0);
		    comboVersion.setEnabled(false);
		lfComm.setEnabled(false);
			tfComm.setText("public");
		    tfComm.setEditable(false);
			lfPort.setEnabled(false);
		    	lfVersion.setEnabled(false);
		tfPort.setText("161");
		    tfPort.setEditable(false);
tfUser.setText("");
                            tfContext.setText("");
	lfUser.setEnabled(false);
		    tfUser.setEditable(false);
		    tfContext.setEditable(false);
		    lfContext.setEnabled(false);
		}
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
setConstraints(0,2,0,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
Top.add(entriesPanel,cons);
entriesPanel.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,4,1,1,0.0,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
entriesPanel.add(lfUser,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,4,1,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
entriesPanel.add(tfUser,cons);
inset = new Insets(5,5,5,5);
setConstraints(3,4,1,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
entriesPanel.add(tfContext,cons);
inset = new Insets(5,5,5,5);
setConstraints(2,4,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
entriesPanel.add(lfContext,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,3,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
entriesPanel.add(lfComm,cons);
inset = new Insets(5,5,5,5);
setConstraints(2,3,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
entriesPanel.add(lfPort,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
entriesPanel.add(lfVersion,cons);
inset = new Insets(5,5,5,5);
setConstraints(3,3,1,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
entriesPanel.add(tfPort,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,2,1,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
entriesPanel.add(comboVersion,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
entriesPanel.add(lfIPAddr,cons);
inset = new Insets(5,5,5,5);
setConstraints(2,1,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
entriesPanel.add(lfNetMask,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
entriesPanel.add(chbDisc,cons);
inset = new Insets(5,5,5,5);
setConstraints(2,2,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
entriesPanel.add(JLabel1,cons);
inset = new Insets(5,2,5,5);
setConstraints(3,2,1,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
entriesPanel.add(parentNetCheck,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,5,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
entriesPanel.add(v3PropButton,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,3,1,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
entriesPanel.add(tfComm,cons);
inset = new Insets(5,5,5,5);
setConstraints(3,1,1,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
entriesPanel.add(JPanel1,cons);
JPanel1.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(0,0,1,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
JPanel1.add(jcb1,cons);
inset = new Insets(0,0,0,0);
setConstraints(1,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel1.add(jcb2,cons);
inset = new Insets(0,0,0,0);
setConstraints(2,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel1.add(jcb3,cons);
inset = new Insets(0,0,0,0);
setConstraints(3,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel1.add(jcb4,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,1,1,1,0.8,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
entriesPanel.add(JPanel2,cons);
JPanel2.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,0);
setConstraints(0,0,1,1,1.0,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
JPanel2.add(comboIPAddr,cons);
inset = new Insets(0,0,0,0);
setConstraints(1,0,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel2.add(addMore,cons);
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
setConstraints(0,0,0,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
Top.add(imagePanel,cons);
imagePanel.setLayout(new BorderLayout(5,5));
imagePanel.add(lfImage,BorderLayout.WEST);
imagePanel.add(taNode,BorderLayout.CENTER);

  
//<End_setUpGUI_Container>
	table.getTableHeader().setReorderingAllowed(false);
	chbDisc.addActionListener(this);
	comboVersion.addActionListener(this);
    addButton.addActionListener(this);
    delButton.addActionListener(this);
    modButton.addActionListener(this);
    v3PropButton.addActionListener(this);
	addButton.setMnemonic(KeyEvent.VK_A); 
   	delButton.setMnemonic(KeyEvent.VK_D); 
   	modButton.setMnemonic(KeyEvent.VK_M); 
   	addMore.addActionListener(this);
   	addMore.setMargin(new java.awt.Insets(0,0,0,0));
	comboIPAddr.addActionListener(this);
     	JTextField comboIPText =((JTextField)comboIPAddr.getEditor().getEditorComponent());
            comboIPText.addKeyListener(new KeyAdapter()
            {
	 public void keyTyped(KeyEvent ke) 
	 { 
             if((ke.getKeyChar() >= '0' && ke.getKeyChar() <='9') || (ke.getKeyChar() == '.') || (ke.getKeyChar()== ke.VK_BACK_SPACE))
	  { 
                		//DO Nothing 
             } 
               	else
		{ 
                    	ke.consume(); 
                    	Toolkit.getDefaultToolkit().beep(); 
                	} 
    	}
                public void keyPressed(KeyEvent ke)
                {
                    if(ke.getKeyCode()== ke.VK_ENTER)
                    {
                    }
                } 
            });

 /*       tfNetMask.addKeyListener(new KeyAdapter()
            {
		public void keyTyped(KeyEvent ke) 
		{ 
                if((ke.getKeyChar() >= '0' && ke.getKeyChar() <='9') || (ke.getKeyChar() == '.') || (ke.getKeyChar()== ke.VK_BACK_SPACE))
		{ 
                	//DO Nothing 
                } 
                else
		{ 
                   	ke.consume(); 
                    	Toolkit.getDefaultToolkit().beep(); 
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
 */
        tfComm.addKeyListener(new KeyAdapter()
            {
                public void keyPressed(KeyEvent ke)
                {
                    if(ke.getKeyCode()== ke.VK_ENTER)
                    {
                        Add_Clicked();
                    }
                } 
            });

        comboVersion.addKeyListener(new KeyAdapter()
            {
                public void keyPressed(KeyEvent ke)
                {
                    if(ke.getKeyCode()== ke.VK_ENTER)
                    {
                        Add_Clicked();
                    }
                } 
            });

        tfUser.addKeyListener(new KeyAdapter()
            {
                public void keyPressed(KeyEvent ke)
                {
                    if(ke.getKeyCode()== ke.VK_ENTER)
                    {
                        Add_Clicked();
                    }
                } 
            });

        tfContext.addKeyListener(new KeyAdapter()
            {
                public void keyPressed(KeyEvent ke)
                {
                    if(ke.getKeyCode()== ke.VK_ENTER)
                    {
                        Add_Clicked();
                    }
                } 
            });

        tfPort.addKeyListener(new KeyAdapter()
            {
				public void keyTyped(KeyEvent ke) 
				{ 
                	if((ke.getKeyChar() >= '0' && ke.getKeyChar() <='9') || (ke.getKeyChar()== ke.VK_BACK_SPACE))
					{ 
                		//DO Nothing 
                	} 
                	else
					{ 
                    	ke.consume(); 
                    	Toolkit.getDefaultToolkit().beep(); 
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
            lfUser.setText(resourceBundle.getString("UserName"));
            lfUser.setForeground(new Color(-16449024));
            lfUser.setFont(new Font("Dialog",1,12)); //No I18N
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfUser,ex); 
          }

//<UserCode_Begin_Bean_lfUser>

//<UserCode_End_Bean_lfUser>

          try
          {
            tfUser.setToolTipText(resourceBundle.getString("UserName"));
            tfUser.setBorder(new javax.swing.border.BevelBorder(1));
            tfUser.setEditable(false);
            tfUser.setEnabled(true);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+tfUser,ex); 
          }

//<UserCode_Begin_Bean_tfUser>

//<UserCode_End_Bean_tfUser>

          try
          {
            tfContext.setToolTipText(resourceBundle.getString("ContextName"));
            tfContext.setBorder(new javax.swing.border.BevelBorder(1));
            tfContext.setEditable(false);
            tfContext.setEnabled(true);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+tfContext,ex); 
          }

//<UserCode_Begin_Bean_tfContext>

//<UserCode_End_Bean_tfContext>

          try
          {
            lfContext.setText(resourceBundle.getString("ContextName"));
            lfContext.setForeground(new Color(-16777215));
            lfContext.setFont(new Font("Dialog",1,12)); //No I18N
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfContext,ex); 
          }

//<UserCode_Begin_Bean_lfContext>

//<UserCode_End_Bean_lfContext>

          try
          {
            lfComm.setText(resourceBundle.getString("Community"));
            lfComm.setForeground(new Color(-16777216));
            lfComm.setFont(new Font("Dialog",1,12)); //No I18N
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfComm,ex); 
          }

//<UserCode_Begin_Bean_lfComm>

//<UserCode_End_Bean_lfComm>

          try
          {
            lfPort.setText(resourceBundle.getString("SNMPAgentPort"));
            lfPort.setForeground(new Color(-16777214));
            lfPort.setToolTipText(resourceBundle.getString(""));
            lfPort.setFont(new Font("Dialog",1,12)); //No I18N
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfPort,ex); 
          }

//<UserCode_Begin_Bean_lfPort>

//<UserCode_End_Bean_lfPort>

          try
          {
            lfVersion.setText(resourceBundle.getString("SNMP Version"));
            lfVersion.setForeground(new Color(-16777205));
            lfVersion.setFont(new Font("Dialog",1,12)); //No I18N
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfVersion,ex); 
          }

//<UserCode_Begin_Bean_lfVersion>

//<UserCode_End_Bean_lfVersion>

          try
          {
            tfPort.setToolTipText(resourceBundle.getString("SNMPAgentPort"));
            tfPort.setText(resourceBundle.getString("161"));
            tfPort.setBorder(new javax.swing.border.BevelBorder(1));
            tfPort.setSelectionEnd(6);
            tfPort.setSelectionStart(3);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+tfPort,ex); 
          }

//<UserCode_Begin_Bean_tfPort>

//<UserCode_End_Bean_tfPort>

          try
          {
            comboVersion.setBorder(new javax.swing.border.EtchedBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+comboVersion,ex); 
          }

//<UserCode_Begin_Bean_comboVersion>

//<UserCode_End_Bean_comboVersion>

          try
          {
            lfIPAddr.setForeground(new Color(-16711414));
            lfIPAddr.setText(resourceBundle.getString("IPAddress(es)"));
            lfIPAddr.setFont(new Font("Dialog",1,12)); //No I18N
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfIPAddr,ex); 
          }

//<UserCode_Begin_Bean_lfIPAddr>

//<UserCode_End_Bean_lfIPAddr>

          try
          {
            lfNetMask.setToolTipText(resourceBundle.getString("NetMask"));
            lfNetMask.setText(resourceBundle.getString("NetMask"));
            lfNetMask.setForeground(new Color(-16777206));
            lfNetMask.setFont(new Font("Dialog",1,12)); //No I18N
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfNetMask,ex); 
          }

//<UserCode_Begin_Bean_lfNetMask>

//<UserCode_End_Bean_lfNetMask>

          try
          {
            chbDisc.setText(resourceBundle.getString("Discovery"));
            chbDisc.setSelected(true);
            chbDisc.setLabel(resourceBundle.getString("Discover"));
            chbDisc.setToolTipText(resourceBundle.getString("Discover"));
            chbDisc.setFont(new Font("Dialog",1,12)); //No I18N
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+chbDisc,ex); 
          }

//<UserCode_Begin_Bean_chbDisc>

//<UserCode_End_Bean_chbDisc>

          try
          {
            JLabel1.setHorizontalAlignment(2);
            JLabel1.setForeground(new Color(-16777216));
            JLabel1.setHorizontalTextPosition(4);
            JLabel1.setText(resourceBundle.getString("Discover Parent Net"));
            JLabel1.setFont(new Font("Dialog",1,12));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel1,ex); 
          }

//<UserCode_Begin_Bean_JLabel1>

//<UserCode_End_Bean_JLabel1>

          try
          {
            parentNetCheck.setFont(new Font("SansSerif",0,12));
            parentNetCheck.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+parentNetCheck,ex); 
          }

//<UserCode_Begin_Bean_parentNetCheck>

//<UserCode_End_Bean_parentNetCheck>

          try
          {
            v3PropButton.setText(resourceBundle.getString("Properties"));
            v3PropButton.setFont(new Font("Dialog",1,12)); //No I18N
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+v3PropButton,ex); 
          }

//<UserCode_Begin_Bean_v3PropButton>

//<UserCode_End_Bean_v3PropButton>

          try
          {
            tfComm.setText(resourceBundle.getString("public"));
            tfComm.setToolTipText(resourceBundle.getString("Community"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+tfComm,ex); 
          }

//<UserCode_Begin_Bean_tfComm>

//<UserCode_End_Bean_tfComm>

          try
          {
            jcb1.setBackground(new Color(-1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+jcb1,ex); 
          }

//<UserCode_Begin_Bean_jcb1>

//<UserCode_End_Bean_jcb1>

          try
          {
            jcb2.setBackground(new Color(-1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+jcb2,ex); 
          }

//<UserCode_Begin_Bean_jcb2>

//<UserCode_End_Bean_jcb2>

          try
          {
            jcb3.setBackground(new Color(-1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+jcb3,ex); 
          }

//<UserCode_Begin_Bean_jcb3>

//<UserCode_End_Bean_jcb3>

          try
          {
            jcb4.setBackground(new Color(-1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+jcb4,ex); 
          }

//<UserCode_Begin_Bean_jcb4>

//<UserCode_End_Bean_jcb4>

          try
          {
            comboIPAddr.setToolTipText(resourceBundle.getString("IPAddress")); //No I18N
            comboIPAddr.setBorder(new javax.swing.border.BevelBorder(1));
            comboIPAddr.setEditable(true);
            comboIPAddr.setFont(new Font("monospaced",0,12)); //No I18N
            comboIPAddr.setMinimumSize(new Dimension(31,22));
            comboIPAddr.setActionCommand(resourceBundle.getString("comboIPAddr")); //No I18N
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+comboIPAddr,ex);  //No I18N
          }

//<UserCode_Begin_Bean_comboIPAddr>

//<UserCode_End_Bean_comboIPAddr>

          try
          {
            addMore.setIcon(com.adventnet.apiutils.Utility.findImage("./images/zoomPlus.png",applet,true)); //No I18N
            addMore.setActionCommand(resourceBundle.getString("Add More")); //No I18N
            addMore.setToolTipText(resourceBundle.getString("Click here to add more IP Address(es).")); //No I18N
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+addMore,ex);  //No I18N
          }

//<UserCode_Begin_Bean_addMore>

//<UserCode_End_Bean_addMore>

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
            addButton.setToolTipText(resourceBundle.getString("Click here to Add a row to the table."));
            addButton.setHorizontalTextPosition(0);
            addButton.setPreferredSize(new Dimension(73,27));
            addButton.setText(resourceBundle.getString("Add"));
            addButton.setMinimumSize(new Dimension(73,27));
            addButton.setMaximumSize(new Dimension(73,27));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+addButton,ex); 
          }

//<UserCode_Begin_Bean_addButton>

//<UserCode_End_Bean_addButton>

          try
          {
            delButton.setText(resourceBundle.getString("Delete"));
            delButton.setBorder(new javax.swing.border.BevelBorder(0));
            delButton.setHorizontalTextPosition(0);
            delButton.setMaximumSize(new Dimension(73,27));
            delButton.setMinimumSize(new Dimension(73,27));
            delButton.setPreferredSize(new Dimension(73,27));
            delButton.setLabel(resourceBundle.getString("Delete"));
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
            modButton.setText(resourceBundle.getString("Modify"));
            modButton.setBorder(new javax.swing.border.BevelBorder(0));
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
            taNode.setWrapStyleWord(true);
            taNode.setLineWrap(true);
            taNode.setEditable(false);
            taNode.setText(resourceBundle.getString("Configure Node Discovery parameters.IPAddress,Netmask,Community,Port,SNMP Version,UserName and ContextName are the key parameters for Node Discovery."));
            taNode.setFont(new Font("Dialog",0,10));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+taNode,ex); 
          }

//<UserCode_Begin_Bean_taNode>

//<UserCode_End_Bean_taNode>
		taNode.setPreferredSize(new Dimension(taNode.getPreferredSize().width+297,taNode.getPreferredSize().height+8));

  
          //<End_setUpProperties>
        lfImage.setHorizontalAlignment(0);
        lfImage.setHorizontalTextPosition(0);
       // lfImage.setIcon(MainScreen.getCommonInterface().getImage("tips.png","images/runtimeadmin"));
        taNode.setFont(new Font("Dialog",0,11));
        taNode.setForeground(Color.black);
        taNode.setBackground(imagePanel.getBackground());
	addMore.setToolTipText(resourceBundle.getString("javaclient.runtime.nodedisc.addbutton.tolltip"));
        //addButton.setPreferredSize(delButton.getPreferredSize());
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
    
    public Vector addToXMLNode() 
    {
	String istoDiscNode = "false"; //No I18N
        int toDiscNode = 0;
        int notToDiscNode = 0;
        Node toNode[]=null;
        Node notNode[]=null;
        
        for(int i=0;i<table.getRowCount();i++) 
        {
            istoDiscNode = (String)table.getValueAt(i,0);
            if(istoDiscNode.equals("true")) 
            {
                toDiscNode++;
            }
            else 
            {
                notToDiscNode++;
            }
        }
        if(toDiscNode != 0) 
        {
            toNode = new Node[toDiscNode];
        }
        if(notToDiscNode != 0) 
        {
            notNode = new Node[notToDiscNode];
        }
        
        toDiscNode = notToDiscNode = 0;

        for(int i=0;i<table.getRowCount();i++) 
        {
            if(((String)table.getValueAt(i,0)).equals("true")) 
            {
                toNode[toDiscNode] = (Node)nodeVector.elementAt(i);
    	    
    	    //commented by saravana
    	    /* toNode[toDiscNode].setParentNetDiscoveryAllowed((String)table.getValueAt(i,1));
                toNode[toDiscNode].setIPAddress((String)table.getValueAt(i,2));
                toNode[toDiscNode].setNetMask((String)table.getValueAt(i,3));
                toNode[toDiscNode].setCommunity((String)table.getValueAt(i,4));
                toNode[toDiscNode].setPort((String)table.getValueAt(i,5));
                toNode[toDiscNode].setVersion((String)table.getValueAt(i,6));
                toNode[toDiscNode].setUserName((String)table.getValueAt(i,7));
                toNode[toDiscNode].setContextName((String)table.getValueAt(i,8));
                //vkarthik
                String ip = (String)table.getValueAt(i,2);
                String ver = (String)table.getValueAt(i,6);
                if(ver.equals("v3"))
                {
               	 if(ip.equals(ipAddr))
                	{
                      	String sL = (String)propData.elementAt(0);
                      	String aP = (String)propData.elementAt(1);
                      	String aPwd =(String)propData.elementAt(2);
                      	String pP = (String)propData.elementAt(3);
                      	String pPwd = (String)propData.elementAt(4);
                      	if(sL != null)
                      		toNode[toDiscNode].setSecLevel(sL);
                      	if(aP != null)
                      		toNode[toDiscNode].setAuthPr(aP);
                      	if(aPwd != null)
                      		toNode[toDiscNode].setAuthPass(aPwd);
                      	if(pP != null)
                      		toNode[toDiscNode].setPrivPr(pP);
                     		 if(pPwd != null)
                      		toNode[toDiscNode].setPrivPass(pPwd);
                    
                	}
                	
                }
                */
                toDiscNode++;
            }
            else 
            {
	      notNode[notToDiscNode] = (Node)nodeVector.elementAt(i);
	
	/* commented by saravana	     
	if(parentNetCheck.isSelected())
	     {
 		notNode[toDiscNode].setParentNetDiscoveryAllowed((String)table.getValueAt(i,1));
	     }
                notNode[notToDiscNode].setIPAddress((String)table.getValueAt(i,2));
                notNode[notToDiscNode].setNetMask((String)table.getValueAt(i,3));
                notNode[notToDiscNode].setCommunity((String)table.getValueAt(i,4));
                notNode[notToDiscNode].setPort((String)table.getValueAt(i,5));
                notNode[notToDiscNode].setVersion((String)table.getValueAt(i,6));
                notNode[notToDiscNode].setUserName((String)table.getValueAt(i,7));
                notNode[notToDiscNode].setContextName((String)table.getValueAt(i,8));
                */
                notToDiscNode++;
            }
        }
        setToDiscoverNode(toNode);
        setNotToDiscoverNode(notNode);
       
        XMLNode tempToDiscNode = new XMLNode();
        tempToDiscNode.setNodeType(XMLNode.ELEMENT);
        tempToDiscNode.setNodeName("TO_DISCOVERIP");

        XMLNode tempNotToDiscNode = new XMLNode();
        tempNotToDiscNode.setNodeType(XMLNode.ELEMENT);
        tempNotToDiscNode.setNodeName("NOT_TO_DISCOVERIP");
        
       Node tempNode = null;
       XMLNode  tempXMLNode = null;
        for(int i=0;i<toDiscoverNode.size();i++) 
        {
            tempNode = (Node)toDiscoverNode.elementAt(i);
            tempXMLNode = NodesToXMLNode(tempNode,true);
            tempToDiscNode.addChildNode(tempXMLNode);
            if(tempNode.getVersion().equalsIgnoreCase("V3"))
            {
            	 getSNMPV3Node(tempNode,tempXMLNode);
            }
        }
        
        Node tempNotNode = null;
        
        for(int i=0;i<notDiscoverNode.size();i++) 
        {
            tempNotNode = (Node)notDiscoverNode.elementAt(i);
            tempXMLNode = NodesToXMLNode(tempNotNode,false);
            tempNotToDiscNode.addChildNode(tempXMLNode);
           /* if(tempNode.getVersion().equalsIgnoreCase("V3"))
            {
            	getSNMPV3Node(tempNotNode,tempXMLNode);
            }*/
        }
        
        Vector xmlNodes = new Vector(2);
        xmlNodes.addElement(tempToDiscNode);
        xmlNodes.addElement(tempNotToDiscNode);
        
        return xmlNodes;

    }
       
    public void setToDiscoverNode(Node[] toDiscNode) 
    {
        toDiscoverNode=new Vector();
        if(toDiscNode!= null)
        {
            for(int i=0;i<toDiscNode.length;i++)
            {
                toDiscoverNode.add(toDiscNode[i]);
            }
        }
    }
    
    public Node[] getToDiscoverNode() 
    {
        Node[] toNode = new Node[toDiscoverNode.size()];
        for(int i=0;i<toDiscoverNode.size();i++)
        {
            toNode[i]= (Node)toDiscoverNode.elementAt(i);
        }
        return toNode;
    }
    
    public void setNotToDiscoverNode(Node[] notDiscNode) 
    {
        notDiscoverNode = new Vector();
        if(notDiscNode != null)
        {
            for(int i=0;i<notDiscNode.length;i++)
            {
                notDiscoverNode.add(notDiscNode[i]);
            }
        }
    }
    
    public Node[] getNotToDiscoverNode() 
    {
        Node[] notNode = new Node[notDiscoverNode.size()];
        for(int i=0;i<notDiscoverNode.size();i++)
        {
            notNode[i]= (Node)notDiscoverNode.elementAt(i);
        }
        return notNode;
    }

    public XMLNode NodesToXMLNode(Node node,boolean toDisc) 
    {
        XMLNode xmlNode = new XMLNode();
        xmlNode.setNodeType(XMLNode.ELEMENT);
        xmlNode.setNodeName("ip");
	if(toDisc)
	{
        		if(node.getParentNetDiscoveryAllowed() != null)
		{
		            xmlNode.setAttribute("DISCOVER_PARENTNET",node.getParentNetDiscoveryAllowed().trim());
	        }
	}
        if(node.getIPAddress() != null)
        {
            xmlNode.setAttribute("NODE_ID",node.getIPAddress().trim());
        }
        if(node.getNetMask() != null)
        {
            xmlNode.setAttribute("NETMASK",node.getNetMask().trim());
        }
        if(node.getCommunity() != null)
        {
            xmlNode.setAttribute("COMMUNITY",node.getCommunity().trim());
        }
        if(node.getPort() !=null)
        {
            xmlNode.setAttribute("SNMPAGENTPORT",node.getPort().trim());
        }
        if(node.getVersion() != null)
        {
            xmlNode.setAttribute("SNMP_VERSION",node.getVersion().trim());
        }
        if(node.getUserName() != null)
        {
            xmlNode.setAttribute("USERNAME",node.getUserName().trim());
        }
        if(node.getContextName() != null)
        {
            xmlNode.setAttribute("CONTEXT_NAME",node.getContextName().trim());
        }
       
        return xmlNode;
    }

    public XMLNode giveXMLNodeToNode()
    {
        Vector temp = sParser.getXMLNode("TO_DISCOVERIP");
        XMLNode rootNode = null;

        if(temp != null) 
        {
            for (int i=0; i<temp.size(); i++)
            {
                rootNode = (XMLNode)temp.elementAt(i);
            }
        }
        
        return rootNode;
        
    }
   //vkarthik
    private void getSNMPV3Node(Node node,XMLNode ipNode)
    {
        XMLNode tempV3Node = new XMLNode();
        tempV3Node.setNodeType(XMLNode.ELEMENT);
        tempV3Node.setNodeName("SNMP_V3");
        
        if(node.getSecLevel() != null)
        {
        	tempV3Node.setAttribute("SECURITYLEVEL",node.getSecLevel()); 
        }
        else
        {
        	return;     
        }
        if(node.getAuthPr() != null)
        {
        	tempV3Node.setAttribute("AUTHPROTOCOL",node.getAuthPr()); 
        }
        if(node.getAuthPass() != null)
        {
        	tempV3Node.setAttribute("AUTHPASSWORD",node.getAuthPass()); 
        }
        if(node.getPrivPr() != null)
        {
        	tempV3Node.setAttribute("PRIVPROTOCOL",node.getPrivPr()); 
        }
        if(node.getPrivPass() != null)
        {
        	tempV3Node.setAttribute("PRIVPASSWORD",node.getPrivPass()); 
        }
        if(node.getPort() != null)
        {
        	tempV3Node.setAttribute("SNMPAGENTPORT",node.getPort());
        }
        if(node.getUserName() != null)
        {
        	tempV3Node.setAttribute("USERNAME",node.getUserName());
        }

        ipNode.addChildNode(tempV3Node);
    }
    
    public XMLNode giveXMLNodeNotToNode()
    {

        Vector temp = sParser.getXMLNode("NOT_TO_DISCOVERIP");
        XMLNode rootNode = null;

        if(temp != null) 
        {
            for (int i=0; i<temp.size(); i++)
            {
                rootNode = (XMLNode)temp.elementAt(i);
            }
        }
        
        return rootNode;
    }

    public void populateObjectToNode() 
    {
        XMLNode tempNode = giveXMLNodeToNode();
        if(tempNode != null)
        {
            if(tempNode.getNodeName().trim().equals("TO_DISCOVERIP")) 
            {
                XMLNode TO_DISCOVERIP = tempNode;
                Vector toDiscNode = TO_DISCOVERIP.getChildNodes();
                for(int i=0;i<toDiscNode.size();i++) 
                {
                    XMLNode xmlToNode = (XMLNode)toDiscNode.elementAt(i);
                    Node node = new Node();
                    node.populateObject(xmlToNode);
                    toDiscoverNode.add(node);
                    nodeVector.addElement(node);
                }
            }
        }
    }

    public void populateObjectNotToNode() 
    {
        XMLNode tempNode = giveXMLNodeNotToNode();
        if(tempNode!=null)
        {
            if(tempNode.getNodeName().trim().equals("NOT_TO_DISCOVERIP")) 
            {
                XMLNode NOT_TO_DISCOVERIP = tempNode;
                Vector notDiscNode = NOT_TO_DISCOVERIP.getChildNodes();
                for(int i=0;i<notDiscNode.size();i++)
                {
                    XMLNode xmlNotNode = (XMLNode)notDiscNode.elementAt(i);
                    Node node = new Node();
                    node.populateObject(xmlNotNode);
                    notDiscoverNode.add(node);
                    nodeVector.addElement(node);
                }
            }
        }
    }

    public void populatingScreen() 
    {

        populateObjectToNode();
        populateObjectNotToNode();
        
        Node todiscNode[] = getToDiscoverNode();
        Node notdiscNode[] = getNotToDiscoverNode();
        Object data[] = null;
        
        for(int i=0;i<todiscNode.length;i++)
        {
            data = new Object[9];
            data[0] = new String("true");//No I18N
            if(todiscNode[i].getParentNetDiscoveryAllowed() != null)
            {
                data[1] = todiscNode[i].getParentNetDiscoveryAllowed().trim();
            }
	else
	{
		data[1] = "true";
	}
            if(todiscNode[i].getIPAddress() != null)
            {
                data[2] = todiscNode[i].getIPAddress().trim();
            }
            if(todiscNode[i].getNetMask() != null)
            {
                data[3] = todiscNode[i].getNetMask().trim();
            }
            if(todiscNode[i].getCommunity() != null)
            {
                data[4] = todiscNode[i].getCommunity().trim();
            }
            if(todiscNode[i].getPort() != null)
            {
                data[5] = todiscNode[i].getPort().trim();
            }
            if(todiscNode[i].getVersion() != null)
            {
                data[6] = todiscNode[i].getVersion().trim();
            }
            if(todiscNode[i].getUserName() != null)
            {
                data[7] = todiscNode[i].getUserName().trim();
            }
            if(todiscNode[i].getContextName() != null)
            {
                data[8] = todiscNode[i].getContextName().trim();
            }

            ((MyTableModel)table.getModel()).addRow(data);
        }
        for(int i=0;i<notdiscNode.length;i++) 
        {
            data = new Object[9];
            data[0] = new String("false"); //No I18N
            /*if(todiscNode[i].getParentNetDiscoveryAllowed() != null)
            {
                data[1] = todiscNode[i].getParentNetDiscoveryAllowed().trim();
            }*/
	data[2]  = "";
            if(notdiscNode[i].getIPAddress() != null)
            {
                data[2] = notdiscNode[i].getIPAddress().trim();
            }
            if( notdiscNode[i].getNetMask() != null)
            {
                data[3] = notdiscNode[i].getNetMask().trim();
            }
            if(notdiscNode[i].getCommunity() != null)
            {
                data[4] = notdiscNode[i].getCommunity().trim();
            }
            if(notdiscNode[i].getPort() != null)
            {
                data[5] = notdiscNode[i].getPort().trim();
            }
            if(notdiscNode[i].getVersion() != null)
            {
                data[6] = notdiscNode[i].getVersion().trim();
            }
            if(notdiscNode[i].getUserName() != null)
            {
                data[7] = notdiscNode[i].getUserName().trim();
            }
            if(notdiscNode[i].getContextName() != null)
            {
                data[8] = notdiscNode[i].getContextName().trim();
            }
            
            ((MyTableModel)table.getModel()).addRow(data);
        }
    }


    public void actionPerformed(ActionEvent ae)
    {
       	//if(ae.getSource() == comboIPAddr.getEditor().getEditorComponent())
       	if(ae.getActionCommand().equals("comboIPAddr"))
       	{
           	String text=((JTextField)comboIPAddr.getEditor().getEditorComponent()).getText();
           	if(((DefaultComboBoxModel)comboIPAddr.getModel()).getIndexOf(text) == -1)
          		 {
              		 if(text != null && !text.equals(""))
              		 {
              		 	comboIPAddr.addItem(text);
              		 }
          		 }
      	 }


		if(ae.getSource() == chbDisc)
		{
			if(chbDisc.isSelected())
			{
				parentNetCheck.setEnabled(true);
				lfUser.setEnabled(false);
			   	 tfUser.setEditable(false);
			   	 tfContext.setEditable(false);
			   	 lfContext.setEnabled(false);
				lfComm.setEnabled(true);
			   	 tfComm.setEditable(true);
				lfPort.setEnabled(true);
			   	 lfVersion.setEnabled(true);
			   	 JLabel1.setEnabled(true);
		   		 tfPort.setEditable(true);
			  	  comboVersion.setEnabled(true);
			   	 v3PropButton.setEnabled(false);
			}
			else
			{
				v3PropButton.setEnabled(false);
				parentNetCheck.setEnabled(false);
				lfUser.setEnabled(false);
			   	 tfUser.setEditable(false);
		   		 tfContext.setEditable(false);
			   	 lfContext.setEnabled(false);
				lfComm.setEnabled(false);
			   	 tfComm.setEditable(false);
				lfPort.setEnabled(false);
			  	lfVersion.setEnabled(false);
			  	JLabel1.setEnabled(false);
			 	  tfPort.setEditable(false);
		   		 comboVersion.setEnabled(false);
			}
		}	
	if(ae.getActionCommand().equals("Properties"))
	{
	    int rowIndex = table.getSelectedRow();
	    
	    Node selectedNode = null;
	    if(rowIndex == -1)
	    {
	    	selectedNode = new Node();
	    }
	    else
	    {
	    	selectedNode = (Node)nodeVector.elementAt(rowIndex);
	    }
	    
	    v3Prop = new V3Properties(selectedNode);
	    v3Prop.populateScreen();
	    v3Prop.setVisible(true); 
	       
	}
        if (ae.getSource()== comboVersion && comboVersion.getSelectedIndex()==2)
        {
            lfComm.setEnabled(false);
            tfComm.setText("");
            tfComm.setEditable(false);
            lfUser.setEnabled(true);
            tfUser.setEditable(true);
            lfContext.setEnabled(true);
            tfContext.setEditable(true);
            v3PropButton.setEnabled(true);
			            
        }
        else if(ae.getSource()== comboVersion && (comboVersion.getSelectedIndex()== 0 || comboVersion.getSelectedIndex()== 1))
        {
            lfComm.setEnabled(true);
            tfComm.setText("public");
            tfComm.setEditable(true);
            lfUser.setEnabled(false);
            tfUser.setEditable(false);
            tfUser.setText("");
            lfContext.setEnabled(false);
            tfContext.setEditable(false);
            tfContext.setText("");     
            v3PropButton.setEnabled(false);
        }
        else if(ae.getActionCommand().equals("Add More"))
        {
             AddMoreIPAddress addMoreIP = new AddMoreIPAddress(this,applet);
        }
        
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
    
    Vector sameIP = null;
    public boolean checkComboandJTable()
    {
    		sameIP = new Vector(0);
    		int ipAddrCount = comboIPAddr.getItemCount();
    		for(int i=0;i<ipAddrCount;i++)
    		{
    			String ipaddress = (String)comboIPAddr.getItemAt(i);
    			for(int j=0;j<table.getRowCount();j++)
    			{
    				String name = (String)table.getValueAt(j,2);
    				if((((String)table.getValueAt(j,2)).trim().equals(ipaddress)))
    				{
    					sameIP.addElement(ipaddress);
    				}
    			}     
       	 	}
    		
    		if(sameIP.size() != 0)
    		{
    				return true;
    		}
    		else
    		{
    				return false;
    		}
 }        	 
   public void Add_Clicked()
    {
        
        //Error Message Construction
        String errMsg = resourceBundle.getString("Please enter ");
        String sTmp = null;	
         sTmp = (String)comboIPAddr.getSelectedItem();
		 String strEditor = ((JTextField)comboIPAddr.getEditor().getEditorComponent()).getText();
        if(sTmp == null || sTmp.trim().length()== 0) 
		{
			if((strEditor==null||strEditor.trim().length()==0))
			{            
				errMsg = errMsg+resourceBundle.getString("< IPAddress >");
			}
			else
			{
				comboIPAddr.setSelectedItem(strEditor);
				//comboIPAddr.removeAllItems();
				//comboIPAddr.setSelectedItem((String)table.getValueAt(table.getSelectedRow(),2));
			}
        }
         
 /*       if(tfNetMask.getText().trim().length()== 0)
        {
            errMsg = errMsg + resourceBundle.getString("< NetMask >");
        }
 */       
	if(chbDisc.isSelected())
	{
        if(comboVersion.getSelectedIndex()== 0 || comboVersion.getSelectedIndex()== 1)
        {
            if(tfComm.getText().trim().length()== 0)
            {
                errMsg = errMsg + resourceBundle.getString("< Community >");
                
            }
        }

        if(tfPort.getText().trim().length()== 0)
        {
            errMsg = errMsg + resourceBundle.getString("< Port >");
        }
        
        if(comboVersion.getSelectedIndex()== 2)
        {
            if(tfUser.getText().trim().length()== 0)
            {
                errMsg = errMsg + resourceBundle.getString("< UserName >");
            }
           /* if(tfContext.getText().trim().length()== 0)
            {
                errMsg = errMsg +resourceBundle.getString("< ContextName >");
            } */
	}
        }
        // End of Error Message Construction        
        if(errMsg.equals(resourceBundle.getString("Please enter ")))
        {
            String netmask = new String();
	 String community = new String();
            String port = new String();
            String version = new String();
            String user = new String();
            String context = new String();
            String parentNet = new String();
            
            //String ipaddress = (String)comboIPAddr.getSelectedItem();
 //           netmask = tfNetMask.getText().trim();
 	netmask = (String)(jcb1.getSelectedItem()+"."+jcb2.getSelectedItem()+"."+jcb3.getSelectedItem()+"."+jcb4.getSelectedItem());
 
	if(chbDisc.isSelected())
	{
            if(comboVersion.getSelectedIndex() == 0 || comboVersion.getSelectedIndex() == 1)
            {
                community = tfComm.getText().trim();
            }
            else if(comboVersion.getSelectedIndex() == 2)
            {
                community = "";
            }
            port = tfPort.getText().trim();
            version = (String)comboVersion.getSelectedItem();
            user = tfUser.getText().trim();
            context = tfContext.getText().trim();
	}
	
	if(checkComboandJTable())
	{
		 String msg = java.text.MessageFormat.format(resourceBundle.getString("javaclient.runtime.nodedisc.duplicate.check"), sameIP);
                int opt = JOptionPane.showConfirmDialog (null,msg, resourceBundle.getString ("AdventNet") + " " + resourceBundle.getString ("Confirm Dialog"), JOptionPane.OK_CANCEL_OPTION); //No I18N

                    if (opt == JOptionPane.OK_OPTION)
                    {
                    		AddMoreIPAddress rearnge = new AddMoreIPAddress(this,sameIP,applet);
                    		return;
                    }
	}

      	parentNet = "";
	if(chbDisc.isSelected())
	{
		if(parentNetCheck.isSelected())
		{
			parentNet = "true";
		}
		else
		{
			parentNet = "false";
		}
	}
	//vkarthik	
	 int ipAddrCount = comboIPAddr.getItemCount();
	 boolean ipsame = false;
            for(int i=0;i<ipAddrCount;i++)
	 {
            	String ipaddress = (String)comboIPAddr.getItemAt(i);
            	ipsame = false;
		for(int j=0;j<table.getRowCount();j++)
           	 {
                	 	
                	 	if((((String)table.getValueAt(j,2)).trim().equals(ipaddress)) == true)
                		{
                    		           ipsame = true;
                	 		continue;
                    		 }
                    }
                      if(ipsame)
		{
			continue;
		}
            	
		Object row[] = {String.valueOf(chbDisc.isSelected()),parentNet,ipaddress,netmask,community,port,version,user,context};
  	         	((DefaultTableModel)table.getModel()).addRow(row);
                     	 Node tempNode = null;
                 // To set the v3 security parameters - priya
      	 if(table.getSelectedRow() != -1)
                     {
                     	 tempNode = (Node)nodeVector.elementAt(table.getSelectedRow());
                         v3Prop = new V3Properties(tempNode);
                     }           
            	if(v3Prop == null)
            	{
              		tempNode = new Node();
            	 }
           	else
           	 {
                  		tempNode=v3Prop.getNode();
                 	}
           	 Node resultNode = (Node)tempNode.clone();
           	 resultNode.setIPAddress(ipaddress);
           	 resultNode.setNetMask(netmask);
           	 resultNode.setPort(port);
           	 resultNode.setVersion(version);
           	 resultNode.setUserName(user);
           	 resultNode.setContextName(context);
           	 resultNode.setParentNetDiscoveryAllowed(parentNet);
           	 resultNode.setCommunity(community);
          	 nodeVector.addElement(resultNode); 
          	             }
           comboIPAddr.removeAllItems();
            v3Prop = null;
            comboIPAddr.removeAllItems();
            comboIPAddr.setSelectedItem("");
            comboVersion.setSelectedIndex(0);
            chbDisc.setSelected(true);

 //            tfNetMask.setText("");
            tfUser.setText("");
            tfContext.setText("");
            isModified = true;
            if(table.getSelectedRow() != -1)
             {
 	            table.removeRowSelectionInterval(table.getSelectedRow(),0);
            }            
    
            
        }
        else 
        {
            //show Error Message
            JOptionPane.showMessageDialog(this,errMsg,resourceBundle.getString("Information"),JOptionPane.INFORMATION_MESSAGE);
        }
	//Reset the values after Clicking Add     
	jcb1.setSelectedIndex(0); 
	jcb2.setSelectedIndex(0); 
	jcb3.setSelectedIndex(0); 
	jcb4.setSelectedIndex(0); 
	
	lfVersion.setEnabled(true);
	comboVersion.setEnabled(true);
	JLabel1.setEnabled(true);
	parentNetCheck.setEnabled(true);
	lfPort.setEnabled(true);
	tfPort.setEnabled(true);
	tfPort.setEditable(true);

    }

    public void Delete_Clicked()
    {
        
        if(table.getRowCount() == 0)        {
            JOptionPane.showMessageDialog(this,resourceBundle.getString("There is no item to delete from the table."),resourceBundle.getString("Information"),JOptionPane.INFORMATION_MESSAGE);
        }
        else if(table.getSelectedRowCount() != 0)
        {
            int selectedRow = table.getSelectedRow();
            nodeVector.removeElementAt(selectedRow);
            ((DefaultTableModel)table.getModel()).removeRow(selectedRow);
            comboIPAddr.setSelectedItem("");
 //           tfNetMask.setText("");
            tfUser.setText("");
            tfContext.setText("");
              // To set default values for community and agent port during deletion
             tfComm.setText("public");
            tfPort.setText("161");
            delButton.setEnabled(false);
            modButton.setEnabled(false);
            addButton.setEnabled(true);
            chbDisc.setSelected(true);
            isModified = true;
            comboIPAddr.removeAllItems();
            
        }
        else
        {        
            JOptionPane.showMessageDialog(this,resourceBundle.getString("You have not selected any item in the table."),resourceBundle.getString("Information"),JOptionPane.INFORMATION_MESSAGE);
        }
	// Reset the values after Clicking Delete     
	jcb1.setSelectedIndex(0); 
	jcb2.setSelectedIndex(0); 
	jcb3.setSelectedIndex(0); 
	jcb4.setSelectedIndex(0); 		 
	
	lfVersion.setEnabled(true);
	comboVersion.setEnabled(true);
	comboVersion.setSelectedIndex(0);
	JLabel1.setEnabled(true);
	parentNetCheck.setEnabled(true);
	lfComm.setEnabled(true);
	tfComm.setEnabled(true);
	tfComm.setEditable(true);
	lfPort.setEnabled(true);
	tfPort.setEnabled(true);
	tfPort.setEditable(true);
    }
	
    public void Modify_Clicked()
    {
            String ipaddress = new String();
            String netmask = new String();
            String community = new String();
            String port = new String();
            String version = new String();
            String user = new String();
            String context = new String();
            String parentNet = new String();
            addButton.setEnabled(true);
        
        int row = table.getSelectedRow();
        String message = null;
        if(row == -1)
        {
            return;
        } 
            for(int i=0;i<table.getRowCount();i++)
            {
                if( i != row)
                {
                    if(((String)table.getValueAt(i,2)).trim().equals(comboIPAddr.getSelectedItem()))
                    {
                        JOptionPane.showMessageDialog(this,resourceBundle.getString("This IPAddress is already entered in the table."),resourceBundle.getString("Information"),JOptionPane.INFORMATION_MESSAGE);
                        comboIPAddr.setSelectedItem((String)table.getValueAt(table.getSelectedRow(),2)); 
                        return;
                    }
                }
            }       
            
        table.setValueAt(String.valueOf(chbDisc.isSelected()),row,0);
	if(chbDisc.isSelected())
	{
		if(parentNetCheck.isSelected())
		{	
			parentNet="true";
			table.setValueAt("true",row,1);
		}
		else
		{
			parentNet="false";
			table.setValueAt("false",row,1);
		}
	}
	else
	{
		table.setValueAt("",row,1);
	}
        //Error Message Construction
        String errMsg = resourceBundle.getString("Please enter ");
        String sTmp = (String)comboIPAddr.getSelectedItem();
         if (sTmp == null || sTmp.trim().length()== 0)
         {
          if(table.getSelectedRow() != -1)
          {
                 comboIPAddr.setSelectedItem((String)table.getValueAt(table.getSelectedRow(),2));
          }   
       }  
       sTmp = (String)comboIPAddr.getSelectedItem(); 
       if(sTmp.trim().length()== 0)
        {
            errMsg = errMsg+resourceBundle.getString("< IPAddress >");
        }
        
 /*       if(tfNetMask.getText().trim().length()== 0)
        {
            errMsg = errMsg + resourceBundle.getString("< NetMask >");
        }
  */        
        if(chbDisc.isSelected())
       {
        if(comboVersion.getSelectedIndex()== 0 || comboVersion.getSelectedIndex()== 1)
        {
            if(tfComm.getText().trim().length()== 0)
            {
                errMsg = errMsg + resourceBundle.getString("< Community >");
            }
        }
        if(tfPort.getText().trim().length()== 0)
        {
            errMsg = errMsg + resourceBundle.getString("< Port >");
        }
        
        if(comboVersion.getSelectedIndex()== 2)
        {
            if(tfUser.getText().trim().length()== 0)
            {
                errMsg = errMsg + resourceBundle.getString("< UserName >");
            }
           /* if(tfContext.getText().trim().length()== 0)
            {
                //errMsg = errMsg +resourceBundle.getString("< ContextName >");
            } 
            */
        }
        }
        // End of Error Message Construction 
        if(errMsg.equals(resourceBundle.getString("Please enter ")))
        {

	netmask = (String)(jcb1.getSelectedItem()+"."+jcb2.getSelectedItem()+"."+jcb3.getSelectedItem()+"."+jcb4.getSelectedItem()); 
            String sTmp1 = (String)comboIPAddr.getSelectedItem();
            if(sTmp1.trim().length()!= 0)
            {
                ipaddress = (String)comboIPAddr.getSelectedItem();
                table.setValueAt(ipaddress,row,2);
            }
            
 //           if(tfNetMask.getText().trim().length()!= 0)
 	if(netmask.length()!= 0)
            {
 //               netmask = tfNetMask.getText();
                table.setValueAt(netmask,row,3);
            }
            if(comboVersion.getSelectedIndex()== 0 || comboVersion.getSelectedIndex()== 1 )
            {
           	    if(tfComm.getText().trim().length()!= 0) 
                {
                    community=tfComm.getText();
                    table.setValueAt(community,row,4);
                }
                else
                {
                	table.setValueAt("",row,4);
                } 
            }
  
            
            if(tfPort.getText().trim().length()!=0)
            {
                port=tfPort.getText();
                table.setValueAt(port,row,5);
            }
            
            version = (String)comboVersion.getSelectedItem();
            table.setValueAt(version,row,6);
            
            if(comboVersion.getSelectedIndex()== 2)
            {
                if(tfUser.getText().trim().length()!= 0)
                {
                    user = tfUser.getText();
                    table.setValueAt(user,row,7);
                }
                
                if(tfContext.getText().trim().length()!= 0)
                {
                    context=tfContext.getText();
                    table.setValueAt(context,row,8);
                }
            }
            else
            {
                table.setValueAt("",row,7);
                table.setValueAt("",row,8);
            }
	
	Node node =(Node)nodeVector.elementAt(row);
	node.setIPAddress(ipaddress);
           node.setNetMask(netmask);
           node.setPort(port);
           node.setVersion(version);
           node.setUserName(user);
           node.setContextName(context);
           node.setParentNetDiscoveryAllowed(parentNet);
           node.setCommunity(community);
            chbDisc.setSelected(true);
            parentNetCheck.setSelected(true);
            comboIPAddr.setSelectedItem("");
 //           tfNetMask.setText("");
            tfUser.setText("");
            tfContext.setText("");
            tfComm.setText("public");
            tfPort.setText("161");
            comboVersion.setSelectedIndex(0);
            lfVersion.setEnabled(true);
            comboVersion.setEnabled(true);
            table.clearSelection();
            delButton.setEnabled(false);
            modButton.setEnabled(false);
            isModified = true;
            v3Prop = null;
            node = null;
	JLabel1.setEnabled(true);
            parentNetCheck.setEnabled(true);
            comboIPAddr.removeAllItems();
            if(table.getSelectedRow() != -1)
             {
 	            table.removeRowSelectionInterval(table.getSelectedRow(),0);
            }            
    
        }
        else
        {
            //show Error Message
            JOptionPane.showMessageDialog(this,errMsg,resourceBundle.getString("Information"),JOptionPane.INFORMATION_MESSAGE);
        }
	// Reset the values after Clicking Modify     
	jcb1.setSelectedIndex(0); 
	jcb2.setSelectedIndex(0); 
	jcb3.setSelectedIndex(0); 
	jcb4.setSelectedIndex(0); 
	
	lfPort.setEnabled(true);
	tfPort.setEnabled(true);
	tfPort.setEditable(true);
	JLabel1.setEnabled(true);
        
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
     * @param v  Value toa assign to isModified.
     */
    public void setIsModified(boolean val) 
    {
        isModified = val;
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
            if (input.equals("RESOURCE_PROPERTIES")) value = "RuntimeAdministrationResources"; 
            if (input.equals("HOST")) value = "localhost"; 
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
        setPreferredSize(new Dimension(getPreferredSize().width+764,getPreferredSize().height+499)); 
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
jcb1.addItem("255"); 
jcb1.addItem("254"); 
jcb1.addItem("252"); 
jcb1.addItem("248"); 
jcb1.addItem("240"); 
jcb1.addItem("224"); 
jcb1.addItem("192"); 
jcb1.addItem("128"); 
jcb1.addItem("0"); 
    
jcb2.addItem("255"); 
jcb2.addItem("254"); 
jcb2.addItem("252"); 
jcb2.addItem("248"); 
jcb2.addItem("240"); 
jcb2.addItem("224"); 
jcb2.addItem("192"); 
jcb2.addItem("128"); 
jcb2.addItem("0"); 
    
jcb3.addItem("255"); 
jcb3.addItem("254"); 
jcb3.addItem("252"); 
jcb3.addItem("248"); 
jcb3.addItem("240"); 
jcb3.addItem("224"); 
jcb3.addItem("192"); 
jcb3.addItem("128"); 
jcb3.addItem("0");	

jcb4.addItem("0"); 
jcb4.addItem("128"); 
jcb4.addItem("192"); 
jcb4.addItem("224"); 
jcb4.addItem("240"); 
jcb4.addItem("248"); 
jcb4.addItem("252"); 
jcb4.addItem("254"); 
jcb4.addItem("255"); 

	Object tableHdr[]= {resourceBundle.getString("Discover"),resourceBundle.getString("Parent Net"),resourceBundle.getString("IPAddress"),resourceBundle.getString("NetMask"),resourceBundle.getString("Community"),resourceBundle.getString("Port"),resourceBundle.getString("Version"),resourceBundle.getString("UserName"),resourceBundle.getString("ContextName")}; 
	comboVersion.addItem("v1");
	comboVersion.addItem("v2");
	comboVersion.addItem("v3");
	model = new MyTableModel(tableHdr,0);
        table.setModel(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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

    public NodeDiscoveryScreen(SeedParser parser)
    {
        sParser = parser;
    }

    

    

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }

  public NodeDiscoveryScreen()
  {
    //<Begin_NodeDiscoveryScreen>
    this.init();
  
    //<End_NodeDiscoveryScreen>
  }

  public NodeDiscoveryScreen(java.applet.Applet applet)
  {
    //<Begin_NodeDiscoveryScreen_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_NodeDiscoveryScreen_java.applet.Applet>
  }
  //added for RTA spp fix
 public void dispose()
 {

     chbDisc.removeActionListener(this);
     comboVersion.removeActionListener(this);
     addButton.removeActionListener(this);
     delButton.removeActionListener(this);
     modButton.removeActionListener(this);
     v3PropButton.removeActionListener(this);
     removeKeyList(comboIPAddr);
 //     removeKeyList(tfNetMask);
     removeKeyList(tfComm);
     removeKeyList(comboVersion);
     removeKeyList(tfUser);
     removeKeyList(tfContext);
     removeKeyList(tfPort);
     removeKeyList(addButton);
     removeKeyList(delButton);
     tableScroller.removeAll();
 
     v3PropButton = null;
     modButton=null;
     delButton=null;
     addButton=null;
     comboVersion=null;
     chbDisc=null;
     model = null;
     toDiscoverNode = null;
     notDiscoverNode = null;
     butPanel=null;
     tablePanel=null;
     entriesPanel=null;
     table=null;
     Top=null;
   }
     public void removeKeyList(Component jc)
     {
          EventListener el[] = jc.getListeners(KeyListener.class);
          for(int i=0;i<el.length;i++){
              if(el[i] instanceof KeyListener) jc.removeKeyListener((KeyListener)el[i]);
             }
          el=null;
     }
    //end
}

class Node
{
    String  ParentNet = "true";
    String IPAddress;
    String NetMask;
    String Community;
    String Port;
    String Version;
    String UserName;
    String ContextName;
    String securityLevel;
    String authProtocol;
    String authPassword;    
    String privProtocol;
    String privPassword;
    Node()
    {
        // No argument constructor
    }
            
    /**
     * Get the value of IPAddress.
     * @return value of IPAddress.
     */

    public String getParentNetDiscoveryAllowed()
    {
    	return ParentNet;
    }

    public void setParentNetDiscoveryAllowed(String str)
    {
	ParentNet = str;
    }

    public String getIPAddress() 
    {
        return IPAddress;
    }
        
    /**
     * Set the value of IPAddress.
     * @param v  Value to assign to IPAddress.
     */
    public void setIPAddress(String ipAddr) 
    {
        this.IPAddress = ipAddr;
    }
    
    /**
     * Get the value of NetMask.
     * @return value of NetMask.
     */
    public String getNetMask() 
    {
        return NetMask;
    }
    
    /**
     * Set the value of NetMask.
     * @param v  Value to assign to NetMask.
     */
    public void setNetMask(String netMask) 
    {
        this.NetMask = netMask;
    }
    
    /**
     * Get the value of Community.
     * @return value of Community.
     */
    public String getCommunity() 
    {
        return Community;
    }
    
    /**
     * Set the value of Community.
     * @param v  Value to assign to Community.
     */
    public void setCommunity(String comm) 
    {
        this.Community = comm;
    }
    
    /**
     * Get the value of Port.
     * @return value of Port.
     */
    public String getPort() 
    {
        return Port;
    }
    
    /**
     * Set the value of Port.
     * @param v  Value to assign to Port.
     */
    public void setPort(String port) 
    {
        this.Port = port;
    }
    
    /**
     * Get the value of Version.
     * @return value of Version.
     */
    public String getVersion() 
    {
        return Version;
    }
    
    /**
     * Set the value of Version.
     * @param v  Value to assign to Version.
     */
    public void setVersion(String version)
    {
     // RTA fix for Lucent 
     // Feature ID :11307692
         if (version==null)
         {
          this.Version="";
         }
        else
         {

          this.Version = version;
         }
    }


    
    /**
     * Get the value of UserName.
     * @return value of UserName.
     */
    public String getUserName() 
    {
        return UserName;
    }
    
    /**
     * Set the value of UserName.
     * @param v  Value to assign to UserName.
     */
    public void setUserName(String user) 
    {
        this.UserName = user;
    }
    
    /**
     * Get the value of ContextName.
     * @return value of ContextName.
     */
    public String getContextName() 
    {
        return ContextName;
    }
    
    /**
     * Set the value of ContextName.
     * @param v  Value to assign to ContextName.
     */
    public void setContextName(String context) 
    {
        this.ContextName = context;
    }
    
    public void setSecLevel(String secLevel)
    {
         this.securityLevel = secLevel;
    }    
    public String getSecLevel()
    {
         return securityLevel;
    }
    
    public void setAuthPr(String aProtocol)
    {
         this.authProtocol = aProtocol;
    }    
    public String getAuthPr()
    {
         return authProtocol;
    }
    
    public void setAuthPass(String aPass)
    {
         this.authPassword = aPass;
    }    
    public String getAuthPass()
    {
         return authPassword;
    }
    
    public void setPrivPr(String pProtocol)
    {
         this.privProtocol = pProtocol;
    }    
    public String getPrivPr()
    {
         return privProtocol;
    }
        
        
    public void setPrivPass(String pPass)
    {
         this.privPassword = pPass;
    }    
    public String getPrivPass()
    {
         return privPassword;
    }
        
    public Object clone()
    {
        Node node = new Node();
        node.setParentNetDiscoveryAllowed(this.getParentNetDiscoveryAllowed());
        node.setIPAddress(this.getIPAddress());
        node.setNetMask(this.getNetMask());
        node.setCommunity(this.getCommunity());
        node.setPort(this.getPort());
        node.setVersion(this.getVersion());
        node.setUserName(this.getUserName());
        node.setContextName(this.getContextName());
        node.setPrivPr(this.getPrivPr());
        node.setPrivPass(this.getPrivPass());
        node.setAuthPr(this.getAuthPr());
        node.setAuthPass(getAuthPass());
        node.setSecLevel(this.getSecLevel());
        return node;        
    }    
    public void populateObject(XMLNode ip) 
    {
        setParentNetDiscoveryAllowed((String)ip.getAttribute("DISCOVER_PARENTNET"));
        setIPAddress((String)ip.getAttribute("NODE_ID"));
        setNetMask((String)ip.getAttribute("NETMASK"));
        setCommunity((String)ip.getAttribute("COMMUNITY"));
        setPort((String)ip.getAttribute("SNMPAGENTPORT"));
        setVersion((String)ip.getAttribute("SNMP_VERSION"));
        setUserName((String)ip.getAttribute("USERNAME"));
        setContextName((String)ip.getAttribute("CONTEXT_NAME"));
        
        Vector v3Nodes = (Vector)ip.getChildNodes();
        for(int i=0;i<v3Nodes.size();i++)
        {
             XMLNode node = (XMLNode)v3Nodes.elementAt(i);
             
             //as of now we have only one user per TO_DISCOVERIP
             setPrivPr((String)node.getAttribute("PRIVPROTOCOL"));
             setPrivPass((String)node.getAttribute("PRIVPASSWORD"));
             setAuthPr((String)node.getAttribute("AUTHPROTOCOL"));
             setAuthPass((String)node.getAttribute("AUTHPASSWORD"));
             setSecLevel((String)node.getAttribute("SECURITYLEVEL"));
        }
    }
   

}














