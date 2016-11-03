//$Id: NetworkDiscoveryScreen.java,v 1.1.6.1 2012/01/25 05:12:46 karen.r Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

//<Begin_Version>
//version$75
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

import com.adventnet.nms.util.SeedParser;
import com.adventnet.nms.util.XMLNode;

public class NetworkDiscoveryScreen extends JPanel implements ActionListener
{

    //<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "RuntimeAdministrationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel entriesPanel = null;
	com.adventnet.beans.probeans.ProIpAddressComponent proEnd = null;
	javax.swing.JPanel rbPanel = null;
	javax.swing.JRadioButton rbEntire = null;
	javax.swing.JRadioButton rbRange = null;
	javax.swing.JCheckBox chbDisc = null;
	com.adventnet.beans.probeans.ProIpAddressComponent proIPAddr = null;
	javax.swing.JLabel lfIPAddr = null;
	javax.swing.JLabel lfNetMask = null;
	javax.swing.JCheckBox chbDHCP = null;
	javax.swing.JLabel lfStart = null;
	com.adventnet.beans.probeans.ProIpAddressComponent proStart = null;
	javax.swing.JLabel lfEnd = null;
	javax.swing.JCheckBox snmpDiscCh = null;
	javax.swing.JButton snmpDiscButton = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JComboBox jcb1 = null;
	javax.swing.JComboBox jcb2 = null;
	javax.swing.JComboBox jcb3 = null;
	javax.swing.JComboBox jcb4 = null;
	javax.swing.JPanel imagePanel = null;
	javax.swing.JLabel lfImage = null;
	javax.swing.JTextArea taNetwork = null;
	javax.swing.JPanel butPanel = null;
	javax.swing.JButton addButton = null;
	javax.swing.JButton delButton = null;
	javax.swing.JButton modButton = null;
	javax.swing.JPanel tablePanel = null;
	javax.swing.JScrollPane tableScroller = null;
	javax.swing.JTable table = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>
    MyTableModel model = null;
    SeedParser sParser = null;
    ButtonGroup butGroup = null;
    Vector toDiscoverNet = new Vector();
    Vector notDiscoverNet = new Vector();
    boolean isModified = false;
    //SNMPV3propScreen snmpPropScreen = null;
    SNMPpropConfigScreen snmpPropConfigScreen = null;
    String snmpVer = null;
    String agentPortV1 =null;
    String communityV1 = null;
    //Vector snmpPropertiesVector = null;
    String contextName = null;
    String ipAddr = null;
    Vector networkVector = new Vector();
    Network network = null;  
    public void start()
  { 

        //<Begin_start> 
       if(running)
 return;
 running=true;

  
       //<End_start>
  chbDisc.setSelected(true);     
    } 
    
    public void initVariables()
  { 
	
        //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        entriesPanel= new javax.swing.JPanel();
        proEnd= new com.adventnet.beans.probeans.ProIpAddressComponent();
        rbPanel= new javax.swing.JPanel();
        rbEntire= new javax.swing.JRadioButton();
        rbRange= new javax.swing.JRadioButton();
        chbDisc= new javax.swing.JCheckBox();
        proIPAddr= new com.adventnet.beans.probeans.ProIpAddressComponent();
        lfIPAddr= new javax.swing.JLabel();
        lfNetMask= new javax.swing.JLabel();
        chbDHCP= new javax.swing.JCheckBox();
        lfStart= new javax.swing.JLabel();
        proStart= new com.adventnet.beans.probeans.ProIpAddressComponent();
        lfEnd= new javax.swing.JLabel();
        snmpDiscCh= new javax.swing.JCheckBox();
        snmpDiscButton= new javax.swing.JButton();
        JPanel1= new javax.swing.JPanel();
        jcb1= new javax.swing.JComboBox();
        jcb2= new javax.swing.JComboBox();
        jcb3= new javax.swing.JComboBox();
        jcb4= new javax.swing.JComboBox();
        imagePanel= new javax.swing.JPanel();
        lfImage= new javax.swing.JLabel();
        taNetwork= new javax.swing.JTextArea();
        butPanel= new javax.swing.JPanel();
        addButton= new javax.swing.JButton();
        delButton= new javax.swing.JButton();
        modButton= new javax.swing.JButton();
        tablePanel= new javax.swing.JPanel();
        tableScroller= new javax.swing.JScrollPane();
        table= new javax.swing.JTable();

  
        //<End_initVariables>
        butGroup = new javax.swing.ButtonGroup();	
        //chbDisc.setSelected(true);
        delButton.setEnabled(false);
        modButton.setEnabled(false);
        snmpDiscButton.setEnabled(false);
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
                    if ( row != -1)
                    {
                        	
	                     Network selNetwork = new Network();
	                     selNetwork = (Network)networkVector.elementAt(row);
	                     network = (Network)selNetwork.clone();
                   }
                    if(row >= 0)
                    {
                   	String ver = ((Network)networkVector.elementAt(row)).getVersion(); 
                    	if(ver != null)
                    	{
                    		if(ver.equalsIgnoreCase("V1") || ver.equalsIgnoreCase("V2") || ver.equalsIgnoreCase("V3")) 
                    		{
	                    	     snmpDiscCh.setSelected(true);
	                    	     snmpDiscButton.setEnabled(true);
	                    	     
            	        	}   
                    	}
                    	if(ver == null || ver.equals(""))
                    	{
                    	     snmpDiscCh.setSelected(false);
                    	     snmpDiscButton.setEnabled(false);
                    	}
                    }
                    chbDisc.setSelected(Boolean.valueOf((String)table.getValueAt(row,0)));
                    snmpDiscCh.setEnabled(Boolean.valueOf((String)table.getValueAt(row,0)));
                   
                    if(table.getValueAt(row,1) != null)
                    {
                        proIPAddr.setText((String)table.getValueAt(row,1));
                    }
                    if(table.getValueAt(row,2) != null)
                    {
    //                    tfNetMask.setText((String)table.getValueAt(row,2));
    
	    String str=(String)table.getValueAt(row,2); 
    
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
	        if((((String)table.getValueAt(row,0))).equals("true"))
	        {
			rbRange.setEnabled(true);
			if(!((String)table.getValueAt(row,3)).trim().equals("") && !((String)table.getValueAt(row,4)).trim().equals(""))
                    		{
		                        rbRange.setSelected(true);
                      		  lfStart.setEnabled(true);
		                        proStart.setEditable(true);
		                        proStart.setEnabled(true);
                      		  lfEnd.setEnabled(true);
		                        proEnd.setEditable(true);
		                        proEnd.setEnabled(true);
                      		  proStart.setText((String)table.getValueAt(row,3));
		                        proEnd.setText((String)table.getValueAt(row,4));
				  chbDHCP.setEnabled(true);
				  chbDHCP.setSelected(Boolean.valueOf((String)table.getValueAt(row,5)));
                    		}
            		else if((((String)table.getValueAt(row,3)).trim()).equals("") && (((String)table.getValueAt(row,4)).trim()).equals(""))
                    		{
		                        rbEntire.setSelected(true);
                      		  lfStart.setEnabled(false);
		                        proStart.setEditable(false);
		                        proStart.setEnabled(false);
                      		  lfEnd.setEnabled(false);
		                        proEnd.setEditable(false);
		                        proEnd.setEnabled(false);
                      		  proStart.setText("0.0.0.0");
		                        proEnd.setText("0.0.0.0");
				  chbDHCP.setEnabled(false);
		  		  chbDHCP.setSelected(false);	
                   		}
					
		}
		else if((((String)table.getValueAt(row,0))).equals("false"))
		{
			rbEntire.setSelected(true);
			rbRange.setSelected(false);
			rbRange.setEnabled(false);	
			lfStart.setEnabled(false);
                    		proStart.setEditable(false);
                    		proStart.setEnabled(false);
                   		lfEnd.setEnabled(false);
                   		proEnd.setEditable(false);
                   		proEnd.setEnabled(false);
                   		proStart.setText("0.0.0.0");
                   		proEnd.setText("0.0.0.0");
			chbDHCP.setSelected(false);
			chbDHCP.setEnabled(false);
		}
					             
                    delButton.setEnabled(true);
                    modButton.setEnabled(true);
                   
                                   
                }
            });	
    } 
    
    public Container getParentCon(Container con)
    {
    while(!(con instanceof JDialog))     
    	{
    	     con = con.getParent();
         }
      return con;
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
inset = new Insets(5,0,5,0);
setConstraints(3,2,1,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
entriesPanel.add(proEnd,cons);
inset = new Insets(5,5,5,15);
setConstraints(1,0,-1,1,0.4,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
entriesPanel.add(rbPanel,cons);
rbPanel.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,20);
setConstraints(0,0,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
rbPanel.add(rbEntire,cons);
inset = new Insets(0,20,0,0);
setConstraints(1,0,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
rbPanel.add(rbRange,cons);
inset = new Insets(5,5,5,0);
setConstraints(0,0,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
entriesPanel.add(chbDisc,cons);
inset = new Insets(5,5,5,10);
setConstraints(1,1,1,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
entriesPanel.add(proIPAddr,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
entriesPanel.add(lfIPAddr,cons);
inset = new Insets(5,5,5,5);
setConstraints(2,1,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
entriesPanel.add(lfNetMask,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,3,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
entriesPanel.add(chbDHCP,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
entriesPanel.add(lfStart,cons);
inset = new Insets(5,5,5,10);
setConstraints(1,2,1,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
entriesPanel.add(proStart,cons);
inset = new Insets(5,5,5,5);
setConstraints(2,2,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
entriesPanel.add(lfEnd,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,4,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
entriesPanel.add(snmpDiscCh,cons);
inset = new Insets(5,5,5,10);
setConstraints(1,4,1,1,0.0,0.0,cons.EAST,cons.NONE,inset,0,0);
entriesPanel.add(snmpDiscButton,cons);
inset = new Insets(5,0,5,0);
setConstraints(3,1,1,1,0.1,0.0,cons.WEST,cons.NONE,inset,0,0);
entriesPanel.add(JPanel1,cons);
JPanel1.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(0,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel1.add(jcb1,cons);
inset = new Insets(0,0,0,0);
setConstraints(1,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel1.add(jcb2,cons);
inset = new Insets(0,0,0,0);
setConstraints(2,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel1.add(jcb3,cons);
inset = new Insets(0,0,0,0);
setConstraints(3,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel1.add(jcb4,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,0,0,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
Top.add(imagePanel,cons);
imagePanel.setLayout(new BorderLayout(5,5));
imagePanel.add(lfImage,BorderLayout.WEST);
imagePanel.add(taNetwork,BorderLayout.CENTER);
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
setConstraints(0,1,0,1,0.1,1.0,cons.WEST,cons.BOTH,inset,0,0);
Top.add(tablePanel,cons);
tablePanel.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,0,1,0.1,0.1,cons.WEST,cons.BOTH,inset,0,0);
tablePanel.add(tableScroller,cons);
tableScroller.getViewport().add(table);

  
//<End_setUpGUI_Container>
	table.getTableHeader().setReorderingAllowed(false);
   	 butGroup.add(rbEntire);
            butGroup.add(rbRange);
   	chbDisc.addActionListener(this);
           rbEntire.addActionListener(this);
           rbRange.addActionListener(this);
	addButton.addActionListener(this);
	delButton.addActionListener(this);
	modButton.addActionListener(this);
	addButton.setMnemonic(KeyEvent.VK_A); 
   	delButton.setMnemonic(KeyEvent.VK_D); 
   	modButton.setMnemonic(KeyEvent.VK_M); 
   	snmpDiscButton.addActionListener(this);
   	snmpDiscCh.addActionListener(this);
   	

   /*     proIPAddr.addKeyListener(new KeyAdapter()
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
            } );  
      
        tfNetMask.addKeyListener(new KeyAdapter()
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
            
      

        tfStart.addKeyListener(new KeyAdapter()
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
             

        tfEnd.addKeyListener(new KeyAdapter()
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
            entriesPanel.setMinimumSize(new Dimension(650,236));
            entriesPanel.setPreferredSize(new Dimension(650,236));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+entriesPanel,ex); 
          }

//<UserCode_Begin_Bean_entriesPanel>

//<UserCode_End_Bean_entriesPanel>

          try
          {
            proEnd.setToolTipText(resourceBundle.getString("End IP"));
            proEnd.setBorder(new javax.swing.border.BevelBorder(1));
            proEnd.setEditable(false);
            proEnd.setEnabled(false);
            proEnd.setMinimumSize(new Dimension(60,22));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+proEnd,ex); 
          }

//<UserCode_Begin_Bean_proEnd>

//<UserCode_End_Bean_proEnd>

          try
          {
            rbEntire.setLabel(resourceBundle.getString("Entire Network"));
            rbEntire.setSelected(true);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+rbEntire,ex); 
          }

//<UserCode_Begin_Bean_rbEntire>

//<UserCode_End_Bean_rbEntire>

          try
          {
            rbRange.setLabel(resourceBundle.getString("Set of Nodes"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+rbRange,ex); 
          }

//<UserCode_Begin_Bean_rbRange>

//<UserCode_End_Bean_rbRange>

          try
          {
            chbDisc.setText(resourceBundle.getString("Discover"));
            chbDisc.setToolTipText(resourceBundle.getString("Discover"));
            chbDisc.setSelected(true);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+chbDisc,ex); 
          }

//<UserCode_Begin_Bean_chbDisc>

//<UserCode_End_Bean_chbDisc>

          try
          {
            proIPAddr.setToolTipText(resourceBundle.getString("IP Address"));
            proIPAddr.setBorder(new javax.swing.border.BevelBorder(1));
            proIPAddr.setMinimumSize(new Dimension(60,22));
            proIPAddr.setPreferredSize(new Dimension(60,22));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+proIPAddr,ex); 
          }

//<UserCode_Begin_Bean_proIPAddr>

//<UserCode_End_Bean_proIPAddr>

          try
          {
            lfIPAddr.setText(resourceBundle.getString("IPAddress"));
            lfIPAddr.setForeground(new Color(-16777214));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfIPAddr,ex); 
          }

//<UserCode_Begin_Bean_lfIPAddr>

//<UserCode_End_Bean_lfIPAddr>

          try
          {
            lfNetMask.setText(resourceBundle.getString("NetMask"));
            lfNetMask.setForeground(new Color(-16777214));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfNetMask,ex); 
          }

//<UserCode_Begin_Bean_lfNetMask>

//<UserCode_End_Bean_lfNetMask>

          try
          {
            chbDHCP.setLabel(resourceBundle.getString("DHCP"));
            chbDHCP.setEnabled(false);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+chbDHCP,ex); 
          }

//<UserCode_Begin_Bean_chbDHCP>

//<UserCode_End_Bean_chbDHCP>

          try
          {
            lfStart.setText(resourceBundle.getString("Start IP"));
            lfStart.setForeground(new Color(-16777214));
            lfStart.setEnabled(false);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfStart,ex); 
          }

//<UserCode_Begin_Bean_lfStart>

//<UserCode_End_Bean_lfStart>

          try
          {
            proStart.setToolTipText(resourceBundle.getString("Start IP"));
            proStart.setBorder(new javax.swing.border.BevelBorder(1));
            proStart.setEditable(false);
            proStart.setEnabled(false);
            proStart.setMinimumSize(new Dimension(60,22));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+proStart,ex); 
          }

//<UserCode_Begin_Bean_proStart>

//<UserCode_End_Bean_proStart>

          try
          {
            lfEnd.setText(resourceBundle.getString("End IP"));
            lfEnd.setForeground(new Color(-16777213));
            lfEnd.setEnabled(false);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfEnd,ex); 
          }

//<UserCode_Begin_Bean_lfEnd>

//<UserCode_End_Bean_lfEnd>

          try
          {
            snmpDiscCh.setText(resourceBundle.getString("SNMP Discovery"));
            snmpDiscCh.setLabel(resourceBundle.getString("SNMP"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+snmpDiscCh,ex); 
          }

//<UserCode_Begin_Bean_snmpDiscCh>

//<UserCode_End_Bean_snmpDiscCh>

          try
          {
            snmpDiscButton.setText(resourceBundle.getString("Configure Properties"));
            snmpDiscButton.setLabel(resourceBundle.getString("SNMP Properties"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+snmpDiscButton,ex); 
          }

//<UserCode_Begin_Bean_snmpDiscButton>

//<UserCode_End_Bean_snmpDiscButton>

          try
          {
            JPanel1.setToolTipText(resourceBundle.getString("Netmask"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel1,ex); 
          }

//<UserCode_Begin_Bean_JPanel1>

//<UserCode_End_Bean_JPanel1>

          try
          {
            jcb1.setAutoscrolls(false);
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
            taNetwork.setWrapStyleWord(true);
            taNetwork.setLineWrap(true);
            taNetwork.setEditable(false);
            taNetwork.setText(resourceBundle.getString("Configure Network Discovery parameters.IPAddress,Netmask,StartIP,EndIP and DHCP are the key parameters for Network Discovery."));
            taNetwork.setForeground(new Color(-16777216));
            taNetwork.setFont(new Font("Dialog",0,10));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+taNetwork,ex); 
          }

//<UserCode_Begin_Bean_taNetwork>

//<UserCode_End_Bean_taNetwork>

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
            addButton.setText(resourceBundle.getString("Add"));
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
            delButton.setText(resourceBundle.getString("Delete"));
            delButton.setBorder(new javax.swing.border.BevelBorder(0));
            delButton.setHorizontalTextPosition(0);
            delButton.setLabel(resourceBundle.getString("Delete"));
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
            modButton.setLabel(resourceBundle.getString("   Modify   "));
            modButton.setHorizontalTextPosition(0);
            modButton.setText(resourceBundle.getString("Modify"));
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
            tablePanel.setBorder(new javax.swing.border.LineBorder(new Color(-15987700),1));
            tablePanel.setPreferredSize(new Dimension(58,58));
            tablePanel.setMinimumSize(new Dimension(58,58));
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
		taNetwork.setPreferredSize(new Dimension(taNetwork.getPreferredSize().width+0,taNetwork.getPreferredSize().height+6));

  
          //<End_setUpProperties>
        lfImage.setHorizontalAlignment(0);
        lfImage.setHorizontalTextPosition(0);
       // lfImage.setIcon(MainScreen.getCommonInterface().getImage("tips.png","images/runtimeadmin"));
        taNetwork.setFont(new Font("Dialog",0,11));
        taNetwork.setBackground(imagePanel.getBackground());
        taNetwork.setForeground(Color.black);
     } 

     public void actionPerformed(ActionEvent ae)
     {
		
		if(ae.getSource() == snmpDiscCh && snmpDiscCh.isSelected() == true)
		{
		    snmpDiscButton.setEnabled(true);
		}
		if(ae.getSource() == snmpDiscCh && snmpDiscCh.isSelected() == false)
		{
			snmpDiscButton.setEnabled(false);     
		}
		
		if(ae.getSource() == snmpDiscButton)
		{
  		if (network == null)
  		{
  			network = new Network();      
  		 }
  		snmpPropConfigScreen = new SNMPpropConfigScreen(network);
  		snmpPropConfigScreen.populateScreen();
		snmpPropConfigScreen.setVisible(true);
               }	
		
		if(ae.getSource() == chbDisc && chbDisc.isSelected())
		{
		  snmpDiscCh.setEnabled(true);
		  if(rbEntire.isSelected())
		 {
		   lfStart.setEnabled(false);
		   proStart.setEditable(false);
		   proStart.setEnabled(false);
		   lfEnd.setEnabled(false);
	                         proEnd.setEditable(false);
	                         proEnd.setEnabled(false);
		   rbRange.setEnabled(true);
		   chbDHCP.setEnabled(false);
		}
		else if(rbRange.isSelected())
		{
		lfStart.setEnabled(true);
          		proStart.setEditable(true);
          		proStart.setEnabled(true);
	           lfEnd.setEnabled(true);
	           proEnd.setEditable(true);
	           proEnd.setEnabled(true);
		rbRange.setEnabled(true);
		chbDHCP.setEnabled(true);
		}
		}	
		else if(ae.getSource() == chbDisc && !chbDisc.isSelected())
		{
		  snmpDiscCh.setSelected(false);
		   snmpDiscCh.setEnabled(false);
		  snmpDiscButton.setEnabled(false);
		  rbRange.setSelected(false);
		  rbRange.setEnabled(false);
		   rbEntire.setSelected(true);
		  lfStart.setEnabled(false);
		   proStart.setEditable(false);
		   proStart.setEnabled(false);
		   lfEnd.setEnabled(false);
	              proEnd.setEditable(false);
	              proEnd.setEnabled(false);
		    chbDHCP.setEnabled(false);
		}
     	if(chbDisc.isSelected() && (ae.getSource() == rbEntire && rbEntire.isSelected()))
        {
            lfStart.setEnabled(false);
			proStart.setText("0.0.0.0");
			proStart.setEditable(false);
			proStart.setEnabled(false);
            lfEnd.setEnabled(false);
            proEnd.setEditable(false);
            proEnd.setEnabled(false);
			rbRange.setEnabled(true);
			proEnd.setText("0.0.0.0");
			chbDHCP.setEnabled(false);
			chbDHCP.setSelected(false);
       	}
        else if(chbDisc.isSelected() && (ae.getSource() == rbRange && rbRange.isSelected()))
        {
            lfStart.setEnabled(true);
            lfEnd.setEnabled(true);
            proStart.setEditable(true);
            proStart.setEnabled(true);
            proEnd.setEnabled(true);
            proEnd.setEditable(true);
			rbRange.setEnabled(true);
	chbDHCP.setEnabled(true);
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

    public void Add_Clicked()
    {
        //ErrorMessage Construction
	MainScreen.setApplyButton(true);
        String errMsg =  resourceBundle.getString("Please enter ");
        if(proIPAddr.getText().trim().length()== 0)
        {
            errMsg = errMsg + resourceBundle.getString("< NetworkIP >");
        }
 //       if(tfNetMask.getText().trim().length()== 0)
 //       {
 //           errMsg = errMsg + resourceBundle.getString("< NetMask >");
  //      }
        if(rbRange.isSelected())
        {
            if(proStart.getText().trim().length()== 0)
            {
                errMsg = errMsg + resourceBundle.getString("< StartIP >");
            }
            if(proEnd.getText().trim().length()== 0)
            {
                errMsg = errMsg + resourceBundle.getString("< EndIP >"); 
            }
        }
        
        if(errMsg.equals(resourceBundle.getString("Please enter ")))
        {
            String ipaddress = new String();
            String netmask = new String();
            String startIP = new String();
            String endIP = new String();
            
            ipaddress = proIPAddr.getText().trim();
   //         netmask = tfNetMask.getText().trim();
	netmask = (String)(jcb1.getSelectedItem()+"."+jcb2.getSelectedItem()+"."+jcb3.getSelectedItem()+"."+jcb4.getSelectedItem());
            if(rbRange.isSelected())
            {
                startIP = proStart.getText().trim();
                endIP = proEnd.getText().trim();
            }
            else if(rbEntire.isSelected())
            {
                startIP = "";
                endIP = "";
            }
            if(rbRange.isSelected())
            {
                   if ((proStart.getText().trim()).startsWith("0.") || (proEnd.getText().trim()).startsWith("0.") || (ipaddress.startsWith("0.")))
                     	{
                     	     JOptionPane.showMessageDialog(this,resourceBundle.getString("Please enter a valid IP Address for the Start IP , End IP and IPAddress fields"),resourceBundle.getString("Information"),JOptionPane.INFORMATION_MESSAGE);
                     	     return;              	       	     
                     	}
                 
                for(int i=0;i<table.getRowCount();i++)
                {
                    if(((String)table.getValueAt(i,3)).trim().equals(proStart.getText().trim()) && ((String)table.getValueAt(i,4)).trim().equals(proEnd.getText().trim()))
                    {
                        JOptionPane.showMessageDialog(this,resourceBundle.getString("This range of IPAddresses are already entered in the table."),resourceBundle.getString("Information"),JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                }          
            }
            if(rbEntire.isSelected())
            {
                 if ((proIPAddr.getText().trim()).startsWith("0."))
                     	{
                     	     JOptionPane.showMessageDialog(this,resourceBundle.getString("Please enter a valid IPAddress"),resourceBundle.getString("Information"),JOptionPane.INFORMATION_MESSAGE);
                     	     return;
                     	}
                 
                 
                for(int i=0;i<table.getRowCount();i++)
                {
                    if(((String)table.getValueAt(i,1)).trim().equals(proIPAddr.getText().trim()))
                    {
                        JOptionPane.showMessageDialog(this,resourceBundle.getString("This NetworkIP is already entered in the table."),resourceBundle.getString("Information"),JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                }            
            }
           Object[] rowData = {String.valueOf(chbDisc.isSelected()),ipaddress,netmask,startIP,endIP,String.valueOf(chbDHCP.isSelected())};
	((DefaultTableModel)table.getModel()).addRow(rowData);

	if (network == null)
	{
	     network = new Network();
   
            }
              if(!snmpDiscCh.isSelected()) 
            {
            	network.flushSnmpData();     
            }
    network.setIPAddress(ipaddress);
    network.setNetMask(netmask);
    network.setStartIP(startIP);
    network.setEndIP(endIP);
    network.setDHCP(String.valueOf(chbDHCP.isSelected()));
    int size = networkVector.size();
    networkVector.addElement(network);
 	 snmpPropConfigScreen=null;
 	 network = null;
            //chbDisc.setSelected(false);
            chbDisc.setSelected(true);
	rbRange.setEnabled(true);
	rbEntire.setSelected(true);
            proIPAddr.setText("");
       //     tfNetMask.setText("");
	lfStart.setEnabled(false);
            proStart.setText("0.0.0.0");
	proStart.setEditable(false);
	proStart.setEnabled(false);
	lfEnd.setEnabled(false);
            proEnd.setText("0.0.0.0");
	proEnd.setEditable(false);
	proEnd.setEnabled(false);
	chbDHCP.setEnabled(false);
            chbDHCP.setSelected(false);
            isModified = true;
            snmpDiscCh.setSelected(false);
            snmpDiscButton.setEnabled(false);
                           
        }
        
        else
        {
            //show Error Message
            JOptionPane.showMessageDialog(this,errMsg,resourceBundle.getString("Information"),JOptionPane.INFORMATION_MESSAGE);
        }

        //Reset the values after Clicking Add       
        proIPAddr.setText("0.0.0.0");
        proStart.setText("0.0.0.0");
        proEnd.setText("0.0.0.0");
        
        jcb1.setSelectedIndex(0); 
         jcb2.setSelectedIndex(0); 
         jcb3.setSelectedIndex(0); 
         jcb4.setSelectedIndex(0); 
        
    }

    public void Delete_Clicked()
    { 
        if(table.getRowCount() == 0)
        {
            JOptionPane.showMessageDialog(this,resourceBundle.getString("There is no item to delete from the table."),resourceBundle.getString("Information"),JOptionPane.INFORMATION_MESSAGE);
        }
        else if(table.getSelectedRowCount() >= 0)
        {
             networkVector.removeElementAt(table.getSelectedRow());
            ((DefaultTableModel)table.getModel()).removeRow(table.getSelectedRow());
            rbEntire.setSelected(true);
            proIPAddr.setText("0.0.0.0");
     //      tfNetMask.setText("");
            lfStart.setEnabled(false);
            proStart.setText("0.0.0.0");
            proStart.setEnabled(false);
            lfEnd.setEnabled(false);
            proEnd.setText("0.0.0.0");
            proEnd.setEnabled(false);
            chbDHCP.setEnabled(false);
            chbDHCP.setSelected(false);
            chbDisc.setSelected(true);
            delButton.setEnabled(false);
            modButton.setEnabled(false);
            addButton.setEnabled(true);
            isModified = true;
        }
        else
        {        
            JOptionPane.showMessageDialog(this,resourceBundle.getString("You have not selected any item in the table."),resourceBundle.getString("Information"),JOptionPane.INFORMATION_MESSAGE);
        }
	//    Reset the valuesafter clicking Delete 
	proIPAddr.setText("0.0.0.0");
	proStart.setText("0.0.0.0");
	proEnd.setText("0.0.0.0");
	
	jcb1.setSelectedIndex(0); 
	jcb2.setSelectedIndex(0); 
	jcb3.setSelectedIndex(0); 
	jcb4.setSelectedIndex(0); 
		
	
	MainScreen.setApplyButton(true);
	
    }
    
    public void Modify_Clicked()
    {
        int row = table.getSelectedRow();
        if(row == -1)
        {
            return;
        } 

        // Start avoid Duplicate entries  -priya
       if(rbRange.isSelected())
        {
              if ((proStart.getText().trim()).startsWith("0.") || (proEnd.getText().trim()).startsWith("0.") || (proIPAddr.getText().trim()).startsWith("0."))
                     	{
                     	     JOptionPane.showMessageDialog(this,resourceBundle.getString("Please enter a valid IP Address for the Start IP , End IP and IPAddress fields"),resourceBundle.getString("Information"),JOptionPane.INFORMATION_MESSAGE);
                     	     return;              	       	     
                     	}
             
            for(int i=0;i<table.getRowCount();i++)
            {
                if( i !=  row)
                {
                    if(((String)table.getValueAt(i,3)).trim().equals(proStart.getText().trim()) && ((String)table.getValueAt(i,4)).trim().equals(proEnd.getText().trim()))
                    {
                        JOptionPane.showMessageDialog(this,resourceBundle.getString("This range of IPAddresses are already entered in the table."),resourceBundle.getString("Information"),JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                }        
            }  
        }
        if(rbEntire.isSelected())
        {
               if ((proIPAddr.getText().trim()).startsWith("0."))
                     	{
                     	     JOptionPane.showMessageDialog(this,resourceBundle.getString("Please enter a valid IPAddress"),resourceBundle.getString("Information"),JOptionPane.INFORMATION_MESSAGE);
                     	}
             
             
            for(int i=0;i<table.getRowCount();i++)
            {
                if( i !=  row)
                {
                    if(((String)table.getValueAt(i,1)).trim().equals(proIPAddr.getText().trim()))
                    {
                        JOptionPane.showMessageDialog(this,resourceBundle.getString("This NetworkIP is already entered in the table."),resourceBundle.getString("Information"),JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                }        
            }
        }

              // End avoid Duplicate entries  -priya  
        table.setValueAt(String.valueOf(chbDisc.isSelected()),row,0);
     
        //Error Message Construction
        String errMsg =  resourceBundle.getString("Please enter ");
        if(proIPAddr.getText().trim().length()==0)
        {
            errMsg = errMsg + resourceBundle.getString("< NetworkIP >");
        }
    //    if(tfNetMask.getText().trim().length()==0)
    //    {
     //       errMsg = errMsg + resourceBundle.getString("< NetMask >");
     //   }
        if(rbRange.isSelected())
        {
            if(proStart.getText().trim().length()== 0)
            {
                errMsg = errMsg + resourceBundle.getString("< StartIP >");
            }
            if(proEnd.getText().trim().length()== 0)
            {
                errMsg = errMsg + resourceBundle.getString("< EndIP >"); 
            }
        }
        // End of Error Message Construction  

        if(errMsg.equals(resourceBundle.getString("Please enter ")))
        {
            //new
            String ipaddress = new String();
            String netmask = new String();
            netmask = (String)(jcb1.getSelectedItem()+"."+jcb2.getSelectedItem()+"."+jcb3.getSelectedItem()+"."+jcb4.getSelectedItem());
            String startIP = new String();
            String endIP = new String();

            if(proIPAddr.getText().trim().length()!= 0)
            {
                ipaddress = proIPAddr.getText();
                table.setValueAt(ipaddress,row,1);
            }

     //       if(tfNetMask.getText().trim().length()!= 0)
          	if(netmask.length()!=0) 	
	      {
      //          netmask=tfNetMask.getText();
	  table.setValueAt(netmask,row,2);
	       }
            if(rbRange.isSelected())
            {
                if(proStart.getText().trim().length() != 0 )
                {
                    startIP = proStart.getText();
                    table.setValueAt(startIP,row,3);
                }
                if(proEnd.getText().trim().length() != 0)
                {
                    endIP = proEnd.getText();
                    table.setValueAt(endIP,row,4);
                }
            }
            else if(rbEntire.isSelected())
            {
                startIP="";
                endIP="";
                table.setValueAt("",row,3);
                table.setValueAt("",row,4);
                
                proStart.setEnabled(false);
                proEnd.setEnabled(false);
                
            }
            table.setValueAt(String.valueOf(chbDHCP.isSelected()),row,5);
            networkVector.removeElementAt(row);
            networkVector.insertElementAt(network,row);
            network.setIPAddress(ipaddress);
            network.setNetMask(netmask);
            network.setStartIP(startIP);
            network.setEndIP(endIP);
            network.setDHCP(String.valueOf(chbDHCP.isSelected()));
            if(!snmpDiscCh.isSelected())
            {
            	network.flushSnmpData();     
            }
            chbDisc.setSelected(true);
            rbRange.setEnabled(true);
            rbEntire.setSelected(true);
            proIPAddr.setText("");
   //         tfNetMask.setText("");
            lfStart.setEnabled(false);
            proStart.setText("0.0.0.0");
            proStart.setEnabled(false);
            lfEnd.setEnabled(false);
            proEnd.setText("0.0.0.0");
            chbDHCP.setSelected(false);
            chbDHCP.setEnabled(false);
            table.clearSelection();
            delButton.setEnabled(false);
            modButton.setEnabled(false);
            addButton.setEnabled(true);
            isModified = true;
            snmpPropConfigScreen=null;
            network = null;
	snmpDiscCh.setSelected(false);
            snmpDiscButton.setEnabled(false);
            if(table.getSelectedRow() != -1)
             {
 	            table.removeRowSelectionInterval(table.getSelectedRow(),0);
            }            
    
             
            // new - end
        }
        else
        {
            //show Error Message
            JOptionPane.showMessageDialog(this,errMsg,resourceBundle.getString("Information"),JOptionPane.INFORMATION_MESSAGE);
        }
        //Reset the values after Clicking Modify       
        proIPAddr.setText("0.0.0.0");
        proStart.setText("0.0.0.0");
        proEnd.setText("0.0.0.0");
        
        jcb1.setSelectedIndex(0); 
         jcb2.setSelectedIndex(0); 
         jcb3.setSelectedIndex(0); 
         jcb4.setSelectedIndex(0); 
        
        
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
        setPreferredSize(new Dimension(getPreferredSize().width+675,getPreferredSize().height+533)); 
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
   
		Object tableHdr[]= {resourceBundle.getString("Discover"),resourceBundle.getString("IPAddress"),resourceBundle.getString("NetMask"),resourceBundle.getString("StartIP"),resourceBundle.getString("EndIP"),resourceBundle.getString("DHCP")};
		model = new MyTableModel(tableHdr,0);
        table.setModel(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

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
            if(col == 0 || col == 5)
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
        String istoDiscNet = "false";//No I18N
        int toDiscNet = 0;
        int notToDiscNet = 0;
        Network toNet[] = null;
        Network notNet[] = null;
        
        for(int i=0;i<table.getRowCount();i++) 
        {
            istoDiscNet = (String)table.getValueAt(i,0);
            if(istoDiscNet.equals("true")) 
            {
                toDiscNet++;
            }
            else 
            {
                notToDiscNet++;
            }
        }
        if(toDiscNet!= 0) 
        {
            toNet = new Network[toDiscNet];
        }
        if(notToDiscNet!= 0) 
        {
            notNet = new Network[notToDiscNet];
        }

        toDiscNet = notToDiscNet = 0;
        
        for(int i=0;i<table.getRowCount();i++) 
        {
            if(((String)table.getValueAt(i,0)).equals("true")) 
            {
                /*toNet[toDiscNet] = new Network();
                toNet[toDiscNet].setIPAddress((String)table.getValueAt(i,1));
                toNet[toDiscNet].setNetMask((String)table.getValueAt(i,2));
                if(((String)table.getValueAt(i,3)).trim() != "")
                {
                    toNet[toDiscNet].setStartIP((String)table.getValueAt(i,3));
                }
                if(((String)table.getValueAt(i,4)).trim() != "")
                {
                    toNet[toDiscNet].setEndIP((String)table.getValueAt(i,4));
                }
                toNet[toDiscNet].setDHCP(((Boolean)table.getValueAt(i,5)).toString());*/
               
               toNet[toDiscNet]=(Network)networkVector.elementAt(i);
               
                toDiscNet++;
            }
            else 
            {
                /*notNet[notToDiscNet]= new Network();
                notNet[notToDiscNet].setIPAddress((String)table.getValueAt(i,1));
                notNet[notToDiscNet].setNetMask((String)table.getValueAt(i,2));
                if(((String)table.getValueAt(i,3)).trim() != "")
                {
                    notNet[notToDiscNet].setStartIP((String)table.getValueAt(i,3));
                }
                if(((String)table.getValueAt(i,4)).trim() != "")
                {
                    notNet[notToDiscNet].setEndIP((String)table.getValueAt(i,4));
                }
                notNet[notToDiscNet].setDHCP(((Boolean)table.getValueAt(i,5)).toString());*/
               notNet[notToDiscNet]=(Network)networkVector.elementAt(i);
                notToDiscNet++;
            }
        } 

        setToDiscoverNet(toNet);
        setNotToDiscoverNet(notNet);
       
        XMLNode tempToDiscNet = new XMLNode();
        tempToDiscNet.setNodeType(XMLNode.ELEMENT);
        tempToDiscNet.setNodeName("TO_DISCOVER");

        XMLNode tempNotToDiscNet = new XMLNode();
        tempNotToDiscNet.setNodeType(XMLNode.ELEMENT);
        tempNotToDiscNet.setNodeName("NOT_TO_DISCOVER");

        Network tempNet = null;
        
        for(int i=0;i<toDiscoverNet.size();i++) 
        {
            tempNet = (Network)toDiscoverNet.elementAt(i);
            tempToDiscNet.addChildNode(tempNet.getXMLNode());
            //tempToDiscNet.addChildNode(NetToXMLNode(tempNet));
        }
        
        Network tempNotNet=null;
        
        for(int i=0;i<notDiscoverNet.size();i++) 
        {
            tempNotNet = (Network)notDiscoverNet.elementAt(i);
            //new 
              tempNotToDiscNet.addChildNode(tempNotNet.getXMLNode());
              //tempNotToDiscNet.addChildNode(NetToXMLNode(tempNotNet));
        }
        
        Vector xmlNodes = new Vector(2);
        xmlNodes.addElement(tempToDiscNet);
       
        xmlNodes.addElement(tempNotToDiscNet);

        
        return xmlNodes;
    }

    public void setToDiscoverNet(Network[] toDiscNet) 
    {
        toDiscoverNet = new Vector();
        if(toDiscNet!= null)
        {
            for(int i=0;i<toDiscNet.length;i++)
            {
                toDiscoverNet.add(toDiscNet[i]);
            }
            
        }
    }
    
    public Network[] getToDiscoverNet() 
    {
        Network[] toNet = new Network[toDiscoverNet.size()];
        for(int i=0;i<toDiscoverNet.size();i++)
        {
            toNet[i]= (Network)toDiscoverNet.elementAt(i);
        }
        return toNet;
    }
    
    public void setNotToDiscoverNet(Network[] notDiscNet) 
    {
        notDiscoverNet = new Vector();
        if(notDiscNet!= null)
        {
            for(int i=0;i<notDiscNet.length;i++)
            {
                notDiscoverNet.add(notDiscNet[i]);
            }
        }
    }
    
    public Network[] getNotToDiscoverNet() 
    {
        Network[] notNet = new Network[notDiscoverNet.size()];
        for(int i=0;i<notDiscoverNet.size();i++)
        {
            notNet[i]= (Network)notDiscoverNet.elementAt(i);
        }
        return notNet;
    }
    
    public XMLNode giveXMLNodeToDiscover()
    {
        Vector temp = sParser.getXMLNode("TO_DISCOVER");

        XMLNode rootNode = null;

        if(temp!=null) 
        {
            for (int i=0; i<temp.size(); i++)
            {
                rootNode = (XMLNode)temp.elementAt(i);
            }
        }
        
        return rootNode;
        
    }
    public XMLNode giveXMLNodeNotToDiscover()
    {
        Vector temp = sParser.getXMLNode("NOT_TO_DISCOVER");
        XMLNode rootNode = null;

        if(temp!=null) 
        {
            for (int i=0; i<temp.size(); i++)
            {
                rootNode = (XMLNode)temp.elementAt(i);
            }
        }
        
        return rootNode;
        
    }

    public void populateObjectToDiscover() 
    {
        XMLNode tempNode = giveXMLNodeToDiscover();
        
        if(tempNode!= null)
        {
            if(tempNode.getNodeName().equals("TO_DISCOVER")) 
            {
                XMLNode TO_DISCOVER = tempNode;
                Vector toDiscNet = TO_DISCOVER.getChildNodes();
                for(int i=0;i<toDiscNet.size();i++) 
                {
                    XMLNode xmlToNet = (XMLNode)toDiscNet.elementAt(i);
                    Network net = new Network();
                    net.populateObject(xmlToNet);
                    toDiscoverNet.add(net);
                    networkVector.add(net);
  	                  
                }
            }
        }
    }
    
    public void populateObjectNotToDiscover() 
    {
        XMLNode tempNode = giveXMLNodeNotToDiscover();

        if(tempNode!= null)
        {
            if(tempNode.getNodeName().equals("NOT_TO_DISCOVER")) 
            {
                XMLNode NOT_TO_DISCOVER = tempNode;
                Vector notDiscNet = NOT_TO_DISCOVER.getChildNodes();
                for(int i=0;i<notDiscNet.size();i++) 
                {
                    XMLNode xmlNotNet = (XMLNode)notDiscNet.elementAt(i);
                    Network net = new Network();
                    net.populateObject(xmlNotNet);
                    notDiscoverNet.add(net);
                    networkVector.add(net);
                }
            }
        }
    
    }
    
    public void populatingScreen() 
    {
        populateObjectToDiscover();
        populateObjectNotToDiscover();
        //populatePropScreen();
        
        Network todiscNet[] = getToDiscoverNet();
        Network notdiscNet[] = getNotToDiscoverNet();
        Object data[]= null;
                
        for(int i=0;i<todiscNet.length;i++) 
        {
            data = new Object[6];
            data[0] = new String("true");//No I18N
            if(todiscNet[i].getIPAddress() != null)
            {
                data[1] = todiscNet[i].getIPAddress().trim();
            }
            else
            {
                data[1] = "";  
            }
            if(todiscNet[i].getNetMask() != null)
            {
                data[2] = todiscNet[i].getNetMask().trim();
            }
            else
            {
                data[2] = "";
            }
            if(todiscNet[i].getStartIP() != null && todiscNet[i].getEndIP() != null)
            {
                data[3] = todiscNet[i].getStartIP().trim();
                data[4] =  todiscNet[i].getEndIP().trim();
            }
            else
            {
                data[3] = "";
                data[4] = "";
            }
            if(todiscNet[i].getDHCP() != null && (todiscNet[i].getDHCP().trim()).equals("true"))
            {
                data[5] = new String("true");//No I18N
            }
            else 
            {
                data[5] = new String("false");//No I18N
            }
            ((MyTableModel)table.getModel()).addRow(data);
        }
        
        for(int i=0;i<notdiscNet.length;i++)
        {
            data = new Object[6];
            data[0] = new String("false");//No I18N
            if(notdiscNet[i].getIPAddress() != null)
            {
                data[1] = notdiscNet[i].getIPAddress().trim();
            }
            else
            {
                data[1] = "";  
            }
            if(notdiscNet[i].getNetMask() != null)
            {
                data[2] = notdiscNet[i].getNetMask().trim();
            }
            else
            {
                data[2] = "";
            }
            if(notdiscNet[i].getStartIP() != null &&  notdiscNet[i].getEndIP() != null)
            {
                data[3] = notdiscNet[i].getStartIP().trim();
                data[4] =  notdiscNet[i].getEndIP().trim();
            }
            else
            {
                data[3] = "";
                data[4] = "";
            }
            if(notdiscNet[i].getDHCP() != null && (notdiscNet[i].getDHCP().trim()).equals("true"))
            {
                data[5] = new String("true");//No I18N
            }
            else 
            {
                data[5] = new String("false");//No I18N   
            }
            ((MyTableModel)table.getModel()).addRow(data);
        }
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

    public NetworkDiscoveryScreen(SeedParser parser)
    {
//mtest	resourceBundle = com.adventnet.apiutils.Utility.getBundle(getParameter("RESOURCE_PROPERTIES"),applet);
        sParser = parser;
        
    }
  
    
    public NetworkDiscoveryScreen()
  {
       
        //<Begin_NetworkDiscoveryScreen>
    this.init();
  
    //<End_NetworkDiscoveryScreen>
   
    }

    public NetworkDiscoveryScreen(java.applet.Applet applet)
  {
        //<Begin_NetworkDiscoveryScreen_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_NetworkDiscoveryScreen_java.applet.Applet>
    }

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  
  }
  //added for RTA Fix spp team

    public void dispose()
    {
	// THEME-II Start
	networkVector.removeAllElements();
	snmpPropConfigScreen.network = null;
	snmpPropConfigScreen.dispose();
	snmpPropConfigScreen=null;
	// THEME-II End.
       
       chbDisc.removeActionListener(this);
       rbEntire.removeActionListener(this);
       rbRange.removeActionListener(this);
       addButton.removeActionListener(this);
       delButton.removeActionListener(this);
       modButton.removeActionListener(this);
       snmpDiscCh.removeActionListener(this);
       snmpDiscButton.removeActionListener(this);
       tableScroller.removeAll();
  //     removeKeyList(tfIPAddr);
       removeKeyList(proIPAddr);
  
 //      removeKeyList(tfNetMask);
       removeKeyList(proStart);
       removeKeyList(proEnd);
       removeKeyList(addButton);
       removeKeyList(delButton);
       snmpDiscCh = null;
       snmpDiscButton = null;
       modButton= null;
       delButton= null;
       addButton= null;
       rbRange= null;
       rbEntire= null;
       chbDisc= null;
       model = null;
       butGroup = null;
       toDiscoverNet = null;
       notDiscoverNet =null;
       butPanel=null;
       rbPanel=null;
       Top=null;
       table=null;
    }
      public void removeKeyList(Component jc)
      {
          EventListener el[] = jc.getListeners(KeyListener.class);
          for(int i=0;i<el.length;i++){
             if(el[i] instanceof KeyListener) jc.removeKeyListener((KeyListener)el[i]);
            }
         el=null;
      }
   //end of RTA add
   
   }

