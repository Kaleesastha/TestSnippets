/* $Id: ShowMibTree.java,v 1.1 2006/08/29 13:56:51 build Exp $ */

package com.adventnet.nms.config;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

import java.applet.Applet;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import javax.swing.tree.*;

// AdventNet Snmp Utils
import com.adventnet.snmp.snmp2.*;
import com.adventnet.snmp.mibs.*;
import com.adventnet.snmp.ui.*;
import com.adventnet.snmp.beans.SnmpTarget;
import com.adventnet.nms.util.NmsClientUtil;

//import JediUtils.PureUtils;

class ShowMibTree  implements ActionListener,TreeSelectionListener, WindowListener

{
	private JPanel descriptionPanel = null;
	private JPanel oid_descPanel    = null;
	private JPanel oidFieldPanel    = null;
	private JPanel buttonPanel = null;

	private JButton okbut;  
	private JButton  cancelbut;

	private JButton loadbut;
	private JButton  unloadbut;

	private MibTree mibTreeView = null;
	private JSplitPane pane = null;

	private JTextField resultField =null;
	private JTextField oidField = new JTextField();


	private JTextArea describeText = null;  

	private String mibsToLoad = "";
	private String duplicateMibsToLoad = "";
	private ActionListener mibloadlistener = null;


	private Image frameIcon = null;

	//private JFrame frame;
	private JDialog frame;

	private ConfigPanel configPanel = null;

	private String key = "";
    
        private boolean isInitialMIBsLoaded = false;

	public ShowMibTree(ConfigPanel configPanel)
	{
		this.configPanel = configPanel;
		this.applet = configPanel.applet;

		if(!configPanel.isStandAlone)
		{
			try
			{
				frameIcon = configPanel.configClientUtils.getImage(applet.getDocumentBase() + applet.getParameter("FRAME_ICON")).getImage();
			}
			catch(Exception ex)
			{
				frameIcon = null;
			}
		}
		//frame=new JFrame();
		//frame=new JDialog(configPanel.configClientUtils.getParentFrame(configPanel));
		
		//frame.setTitle(configPanel.configClientUtils.getString("Browse MIBs"));
		//frame.setSize(400,400);

		//frame.setIconImage(frameIcon);

		okbut = new JButton(configPanel.configClientUtils.getString("Select"));
		cancelbut = new JButton(configPanel.configClientUtils.getString("Cancel"));

		loadbut = new JButton(configPanel.configClientUtils.getString("Load"));
		unloadbut = new JButton(configPanel.configClientUtils.getString("UnLoad"));

		resultField = new JTextField();

		if(configPanel.isStandAlone)
		{
			mibOps = new MibOperations();
            this.applet=applet;
			mibTreeView = new MibTree(mibOps);
		}		
		else				
		{		
			SnmpTarget target = new SnmpTarget(applet);
			mibOps = target.getMibOperations();
            mibOps.setParsingLevel(MibOperations.LENIENT);
			mibTreeView = new MibTree(mibOps, applet);
		}
		mibTreeView.setBackground(Color.white);
		mibTreeView.tree.setBackground(Color.white);

		descriptionPanel = new JPanel();
		oid_descPanel = new JPanel();
		oidFieldPanel = new JPanel();
		
		pane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

		describeText =  new JTextArea();

		oidField.setEditable(false);

		oid_descPanel.setLayout(new BorderLayout());
		oidFieldPanel.setLayout(new BorderLayout());
		oidFieldPanel.setBorder(BorderFactory.createTitledBorder("ObjectID"));
		oidFieldPanel.add(oidField,BorderLayout.CENTER);

		describeText.setEditable(false);
		describeText.setBackground(new Color(220,220,220));
		describeText.setBorder(BorderFactory.createBevelBorder(1));
		describeText.setLineWrap(true);

		descriptionPanel.setLayout(new BorderLayout());
		descriptionPanel.setBorder(BorderFactory.createTitledBorder("Definition"));
		descriptionPanel.add(new JScrollPane(describeText),BorderLayout.CENTER);

		oid_descPanel.add(oidFieldPanel,BorderLayout.NORTH);
		oid_descPanel.add(descriptionPanel,BorderLayout.CENTER);

		mibTreeView.setPreferredSize(new Dimension(200,300));

		JPanel loadPanel = new JPanel();
		loadPanel.setLayout(new FlowLayout(FlowLayout.CENTER,15,2));
		loadPanel.setBorder(BorderFactory.createTitledBorder(
					new EtchedBorder(Color.white, Color.gray), 
					""));
		loadPanel.add(loadbut);
		loadPanel.add(unloadbut);

		JPanel mibPanel = new JPanel();
		mibPanel.setLayout(new BorderLayout());
		mibPanel.add(mibTreeView,BorderLayout.CENTER);
		mibPanel.add(loadPanel,BorderLayout.SOUTH);


		pane.setLeftComponent(mibPanel);
		//pane.setRightComponent(new JScrollPane(descriptionPanel));            
		pane.setRightComponent(oid_descPanel);          


		buttonPanel = new JPanel();

		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER,15,5));
		buttonPanel.setBorder(BorderFactory.createTitledBorder(
					new EtchedBorder(Color.white, Color.gray), 
					""));

		buttonPanel.add(okbut);
		buttonPanel.add(cancelbut);

		// Adding Listeners
		loadbut.addActionListener(this);
		unloadbut.addActionListener(this);
		okbut.addActionListener(this);
		cancelbut.addActionListener(this);

		mibTreeView.getTree().addTreeSelectionListener(this);

		/*frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(pane,BorderLayout.CENTER);
		frame.getContentPane().add(buttonPanel,BorderLayout.SOUTH);

		// Adding window listener.
		frame.addWindowListener(this);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);  */

		//loadInitialMIBs();
	}

	public void setKey(String key)
	{
		this.key = key;
	}

	private void loadInitialMIBs()
	{
                isInitialMIBsLoaded = true;
                
		String initialMIBs = applet.getParameter("INITIAL_MIBS");

		StringTokenizer tokenizer;

		if(initialMIBs.indexOf(',') != -1)
		{
			tokenizer = new StringTokenizer(initialMIBs, ",");
		}
		else
		{
			tokenizer = new StringTokenizer(initialMIBs);
		}

		String baseMIBsDir = "";

		if(configPanel.isStandAlone)
			baseMIBsDir = applet.getParameter("MIBS_DIR_PATH");
		else	
			baseMIBsDir = applet.getDocumentBase() + applet.getParameter("MIBS_DIR").trim();

		while(tokenizer.hasMoreTokens())
		{
			loadMIB(baseMIBsDir+"/"+tokenizer.nextToken().trim());
		}
	}

	public void valueChanged(TreeSelectionEvent e) 
	{
		Object selected = e.getPath().getLastPathComponent();
		if(describeText == null)
		{
			describeText = new JTextArea();
		}
		describeText.setText("");
		oidField.setText("");
		if (selected instanceof NodeData) 
		{
			if (((NodeData)selected).isMibTrap()) 
			{                                                                                       
				if(describeText != null) 
				{
					describeText.setText("Enterprise  : " + ((NodeData)selected).getMibTrap().getEnterpriseString()+"\n");
					describeText.append("Variables : " + ((NodeData)selected).getMibTrap().getVariables()+"\n");
					describeText.append("Description : " + ((NodeData)selected).getMibTrap().getDescription()+"\n");
					describeText.append("Value : " + ((NodeData)selected).getMibTrap().getValue()+"\n");

				}
			}
			else  if (((NodeData)selected).isMibTC()) 
			{                               
				if(describeText != null) 
				{
					describeText.setText("Status  : " + ((NodeData)selected).getMibTC().getStatus()+"\n");
					describeText.append("Description : " + ((NodeData)selected).getMibTC().getTCDescription()+"\n");
					//describeText.append("Syntax : " + ((NodeData)selected).getMibTC().getSyntax().toString()+"\n");                                       
					describeText.append("Syntax : " + ((NodeData)selected).getMibTC().getSyntax().getDescription()+"\n");                                   
				}
			}
			else if( (MibNode)((NodeData)selected).getUserObject() != null  )
			{                                       
				if(describeText != null) 
				{
					LeafSyntax lsyntax = ((MibNode)((NodeData)selected).getUserObject()).getSyntax();
					String lsy = "";
					if (lsyntax!=null)
					{
						//lsy =  lsyntax.getName();
						lsy =  lsyntax.getDescription();
					}

					describeText.setText("Syntax : " + lsy+"\n");
					describeText.append("Status : " + ((MibNode)((NodeData)selected).getUserObject()).getStatus()+"\n");
					describeText.append("Access : " + ((MibNode)((NodeData)selected).getUserObject()).printAccess() +"\n");
					describeText.append("Reference : " + ((MibNode)((NodeData)selected).getUserObject()).getReference() +"\n");
					java.util.Vector v = ((MibNode)((NodeData)selected).getUserObject()).getIndexNames();
					String index = "";
					if (v!=null)
					{
						for (int i=0;i<v.size();i++) 
						{
							index += v.elementAt(i).toString() + " ";
						}
					}

					describeText.append("Index : " + index+"\n");
					describeText.append("Object ID : " + ((MibNode)((NodeData)selected).getUserObject()).getNumberedOIDString()+"\n");
					describeText.append("String Object ID : " +((MibNode)((NodeData)selected).getUserObject()).getOIDString() +"\n");
					describeText.append("Description:\n" + ((MibNode)((NodeData)selected).getUserObject()).getDescription()+"\n");
				}
				if(getNumericOID() !=null)
				{
					oidField.setText(getNumericOID());
				}
			}

			try
			{
				describeText.setCaretPosition(1);
			}
			catch(java.lang.IllegalArgumentException iae)
			{
			}
			catch(Exception ex)
			{
			}
		} 
	}

	public void setComponent(JTextField mtf)
	{
		resultField = mtf;

		if( (mtf != null) && (mtf.getText() != null))
		{
			setOidString(mtf.getText());
		}
	}

	private String[] getListOfMibModules(String s, String delimiter)
	{
		StringTokenizer filestring = new StringTokenizer(s,delimiter);

		int tokens = filestring.countTokens();
		String[] filelist = new String[tokens];

		if(tokens != 0)
		{
			for(int i = 0 ; i < tokens ; i++)
			{
				String filetok = filestring.nextToken().trim();
				filetok = filetok.replace('"',' ').trim();
				filelist[i] = new File(filetok).getName();
			}                       
		}
		return filelist;
	}



	//--------------------------------------------------------
	/* Returns the MibNode.
	   This method return null, if the oid is invalid. */

	MibNode getMibNode(String oid)
	{
		if(isNumberedOID(oid))
		{
			return mibOps.getMibNode(new SnmpOID(oid));
		}
		else
		{
			return mibOps.getMibNode(oid);
		}
	}
	
	public Vector getIndexesForTable(MibNode mibNode)
	{
		return mibNode.getIndexes(mibOps);
	}

	// Returns true for numbered oid, otherwise false.
	boolean isNumberedOID(String oid)
	{
		for ( int i =0;i < oid.length();++i)
		{
			Character c = new Character( oid.charAt(i));
			if ( c.compareTo(new Character('.') ) == 0 )
				continue;
			else if ( Character.isDigit(c.charValue()) )
				continue;
			else
				return false;

		}
		return true;
	}
	// To get the oid
	String getOID(String oid)
	{
		MibNode mibNode=null;
		String numOID=null;
		if (! isNumberedOID(oid) )
		{	
			numOID=getNumberedOID(oid);
			if ( numOID == null)
				return null;
			oid=numOID;
		}
		mibNode=getMibNode(oid);
		if ( mibNode == null)
			return null;
		return oid; 

	}
	// To get the numbered oid, for  labels.
	//This method does n't use SnmpOID to construct the MibNode
	// If the OID is scalar, ".0" is appended to it.
	String getNumberedOID(String sOID)
	{
		MibNode mNode = mibOps.getMibNode(sOID);
		if ( !(mNode == null))
		{
			String oid= mNode.getNumberedOIDString();
			if ( mNode.isScalar() )
			{
				if ( !oid.endsWith(".0") )
					oid += ".0";
			}
			return oid;

		}
		return null;
	}

	String getLabel(String oid)
	{
		if ( getMibNode(oid) == null)
			return null;
		return getMibNode(oid).getLabel();
	}
	public String getTypeLabel(String oid)
	{
		oid=getOID(oid);
		if ( oid == null)
			return null;
		MibNode mNode=getMibNode(oid );
		if ( mNode != null)
		{					
			if(mNode.isLeaf())
			{
				SnmpOID soid =new SnmpOID(oid);
				LeafSyntax ls =  mibOps.getLeafSyntax(soid);
				return ls.getName();
			}
		}
		return null;

	}
	public Byte getType(String oid)
	{
		oid=getOID(oid);
		if ( oid == null)
			return null;
		MibNode mNode=getMibNode(oid );
		if ( mNode != null)
		{					
			if ( mNode.isLeaf() && mNode.isScalar() )
			{
				SnmpOID soid =new SnmpOID(oid);
				LeafSyntax ls =  mibOps.getLeafSyntax(soid);
				return new Byte(ls.getType());
			}
			else if(mNode.isLeaf())
			{
				SnmpOID soid =new SnmpOID(oid);
				LeafSyntax ls =  mibOps.getLeafSyntax(soid);
				return new Byte(ls.getType());
			}
		}
		return null;
	}
	// Returns the type for the passed String type(i.e INTEGER,Row_Status)
	String getValueForType(String type)
	{
		LeafSyntax ls = mibOps.getSyntaxByName(type);
		if ( ls != null)
		{
			byte val = ls.getType();
			return (new Byte(val)).toString();
		}
		else
			return null;
	}
	// Returns true, if the value is compatible with the type passed.
	boolean checkValueForType(String val ,byte type)
	{
		SnmpVar sv=null;
		try
		{
			sv=SnmpVar.createVariable(val,type);
		}
		catch(SnmpException se )
		{
			System.err.println(se.toString());
			return false;
		}

		if ( sv != null)
		{
			return true;
		}

		return false;

	}

	// ---------------------------------------------------------------
	boolean isTable(String oid)
	{

		String numOID=getOID(oid);
		if ( numOID == null)
			return false;
		return getMibNode(numOID).isTable();

	}

	Vector getIndexNames(String oid)
	{
		String numOID=getOID(oid);
		if ( numOID == null)
			return null;
		MibNode mibNode=getMibNode(numOID);
		if ( mibNode == null )
			return null;
		mibNode=mibNode.getChild(1);
		if ( mibNode == null )
			return null;
		return mibNode.getIndexNames();


	}

	//------------------------------------------------------------

	private String getNumericOID()
	{
		try
		{		String s = mibTreeView.getSelectedMibNode().getNumberedOIDString();                     
			if (s==null || s.trim().equals("")) 
			{
				return null;
			}                       
			SnmpOID oid = new SnmpOID(s);
			MibNode node = mibTreeView.getSelectedMibNode();
			String ret = oid.toString();
			if (node!=null)
			{
				if (node.isScalar())
				{
					if (!ret.endsWith(".0")) 
					{
						ret += ".0";
					}
				}
			}
			return ret;
		} 
		catch (Exception ex)
		{
			//System.err.println("Error getting OID: "+ex);
		}
		return null;
	}
	public String getLabel()
	{
		return getMibNode(getOidString()).getLabel();
	}
	public Byte getType()
	{
		MibNode mNode=getMibNode(getOidString() );
		if ( mNode.isLeaf() && mNode.isScalar() )
		{
			SnmpOID soid =new SnmpOID(getOidString());
			LeafSyntax ls =  mibOps.getLeafSyntax(soid);
			return new Byte(ls.getType());
		}
		else
			return null;
	}
	public String getMibModules()
	{
		return mibTreeView.getMibModules();
	}

	public void actionPerformed(ActionEvent e)
	{               
        
		Object o = e.getSource();

		if(o==okbut)
		{       
			String oid = oidField.getText();
			resultField.setText(oid);
			mibsToLoad = duplicateMibsToLoad;
			//mNode=getMibNode(oid);

			MibNode mibNode = getMibNode(oid);
			MibNode tnode = null;
			if(mibNode != null)
			{
				if(key.equalsIgnoreCase("SCALAR"))
				{
					if(!mibNode.isScalar())
					{
						tnode = mibNode.getParent().getParent();
						if(!tnode.isTable())
						{
							configPanel.configClientUtils.showErrorDialog(frame, configPanel.configClientUtils.getString("Please select a scalar / columnar OID"), configPanel.configClientUtils.getString("Info"), "info");
							return;
						}
						else
						{
							if(!(JOptionPane.showConfirmDialog(configPanel, ConfigPanel.getInstance().configClientUtils.getString("You have selected a Columnar OID. Would you like to define index values?"), ConfigPanel.getInstance().configClientUtils.getString("Confirmation Message"), JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION))
							{
								return;

							}
						}
					}
				}
				else if(key.equalsIgnoreCase("TABLE"))
				{
					if(!mibNode.isTable())
					{
						configPanel.configClientUtils.showErrorDialog(frame, configPanel.configClientUtils.getString("Please select a table OID"), configPanel.configClientUtils.getString("Info"), "info");
						return;
					}
				}
			}
			else
			{
				if(key.equalsIgnoreCase("SCALAR"))
				{
					configPanel.configClientUtils.showErrorDialog(frame, configPanel.configClientUtils.getString("Please select a valid scalar OID"), configPanel.configClientUtils.getString("Info"), "info");
				}
				else if(key.equalsIgnoreCase("TABLE"))
				{
					configPanel.configClientUtils.showErrorDialog(frame, configPanel.configClientUtils.getString("Please select a valid table OID"), configPanel.configClientUtils.getString("Info"), "info");
				}

				return;
			}

			fireMibActionPerform();
			
			closeHandler();
		}
		else if(o==cancelbut)
		{                               
			closeHandler();
		}
		else if(o == loadbut)
		{       
			loadAction();
		}
		else if(o == unloadbut)
		{ 
			unloadAction();
		}
        else if ( e.getActionCommand().equals("Open") )
		{
			loadMIB(sfd.getSelectedFileUrl());
            sasFileChooser.dispose();
		}
        else if ( e.getActionCommand().equals("Close") && o instanceof SasFileDialog )
		{
            sasFileChooser.dispose();
		}
	}

	private void loadMIB(String miburl)
	{
		try
		{						
			mibTreeView.addMibs("\"" + miburl + "\"");
		}
		catch(Exception ex)
		{
			System.err.println("Error occurred while loading the " + miburl + " - " + ex.getMessage());
			//ex.printStackTrace();
		}
	}
	JFrame sasFileChooser;
	SasFileDialog sfd;
	Applet applet;
	MibOperations mibOps;
	MibNode mNode;

	public MibOperations getMibOperations()
	{
		return mibOps;
	}



	private void loadAction()
	{   
		if(configPanel.isStandAlone)
		{
			JFileChooser fileChooser = new JFileChooser(applet.getParameter("MIBS_DIR_PATH"));

			if(fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION)
			{
				loadMIB(fileChooser.getSelectedFile().toString());
			}
		}
		else
		{
            
            sasFileChooser = new JFrame();
			sfd = new SasFileDialog(applet);

			sfd.init();
			sfd.addActionListener(this); 
            
            sasFileChooser.getContentPane().add(sfd);
			sasFileChooser.setIconImage(frameIcon);
            sasFileChooser.setTitle(NmsClientUtil.GetString("Nms File Dialog"));
            sasFileChooser.pack();
            sasFileChooser.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            sasFileChooser.setSize(400,400);
			configPanel.configClientUtils.centerWindow(sasFileChooser);
			sasFileChooser.setVisible(true);        
		}
	}

	private void unloadAction()
	{
		if(mibTreeView!=null)
		{
			try
			{       
				if(mibTreeView.isGlobalView() && mibTreeView.getSelectedMibNode() instanceof MibNode)
				{
					mibTreeView.deleteMib(" ");
					duplicateMibsToLoad = "";
				}
				else
				{
					String mibName = mibTreeView.getSelectedMibModule().getName();                                                          
					mibTreeView.deleteMib(mibName);
					deleteFromList(mibName);                                
				}

				//fireMibActionPerform();
			}
			catch(Exception e)
			{

			}
		}
	}

	private void fireMibActionPerform()
	{
		if(mibloadlistener != null)
		{
			mibloadlistener.actionPerformed(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,"Ok"));
		}
	}

	private void deleteFromList(String mib)
	{
		StringTokenizer filestring = new StringTokenizer(duplicateMibsToLoad,"|");              

		int tokens = filestring.countTokens();

		if(tokens ==0)
		{
			return;
		}               
		Vector filelist = new Vector(tokens);           

		for(int i = 0 ; i < tokens ; i++)
		{
			String filetok = filestring.nextToken().trim();
			filetok = filetok.replace('"',' ').trim();
			filelist.addElement(filetok);
		}                       

		int len = filelist.size();
		if(len!=0)
		{
			for(Enumeration enu = filelist.elements(); enu.hasMoreElements(); )
			{
				String name = enu.nextElement().toString().trim();      
				boolean ismatching = name.regionMatches(0,mib,0,mib.length()-1);
				int n = name.indexOf(mib);
				if(n !=-1)
				{
					filelist.removeElement(name);
				}                                       
			}
		}
		// reconstructing mibstoLoad
		duplicateMibsToLoad = "";
		for(Enumeration enu = filelist.elements(); enu.hasMoreElements(); )
		{
			String mibnm =enu.nextElement().toString().trim();
			duplicateMibsToLoad += mibnm + "|"; 
		}               
	}



	public void addMibLoadListener(ActionListener lis)
	{
		mibloadlistener = lis;
	}

	public void removeMibLoadListener(ActionListener lis)
	{
		if(     mibloadlistener == lis)
		{
			mibloadlistener = null;
		}
	}       

	public void isModificationRequired(boolean required)
	{                               
		if(required)
		{
			oid_descPanel.remove(oidFieldPanel);
			okbut.setText(configPanel.configClientUtils.getString("Ok"));            
		}
		else
		{
			oid_descPanel.add(oidFieldPanel,BorderLayout.NORTH);    
			okbut.setText(configPanel.configClientUtils.getString("Select"));                                
		}
	}

	public void showMib(Container parent)
	{
		frame = new JDialog(configPanel.configClientUtils.getParentDialog(parent));

		frame.setTitle(configPanel.configClientUtils.getString("Browse MIBs"));
	
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(pane,BorderLayout.CENTER);
		frame.getContentPane().add(buttonPanel,BorderLayout.SOUTH);

		// Adding window listener.
		frame.addWindowListener(this);
		
		frame.setSize(500, 500);

		configPanel.configClientUtils.centerWindow(frame);
                
                // Initial MIBs given in the clientparameters.conf are to be loaded when showing this frame for the first time
                if(!isInitialMIBsLoaded)
                    loadInitialMIBs();

		frame.setVisible(true);
	}

	public void closeHandler()
	{
		frame.setVisible(false);
		frame.dispose();
		frame = null;
	}

	public String getOidString()
	{
		return oidField.getText();
	}

	public void setOidString(String str)
	{
		oidField.setText(str);
	}

	// Window Listener.
	public void windowOpened(WindowEvent e) {}
	public void windowClosing(WindowEvent e) 
	{
		closeHandler();
	}
	public void windowClosed(WindowEvent e) {}
	public void windowIconified(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {}
	public void windowActivated(WindowEvent e) 
	{
		if(oidField.isVisible())
		{
			oidField.requestFocus();
		}
	}
	public void windowDeactivated(WindowEvent e) {}

}


