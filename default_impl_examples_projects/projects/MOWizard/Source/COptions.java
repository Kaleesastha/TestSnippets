//$Id: COptions.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $
package com.adventnet.nms.tools.mowizard;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import com.adventnet.nms.tools.objtorel.TransverseContainer;
import com.adventnet.nms.tools.utils.*;
import com.adventnet.nms.util.*;
import java.util.StringTokenizer;
import java.util.Vector;
import org.w3c.dom.*;
import org.xml.sax.*;
import com.adventnet.nms.tools.objtorel.EditableList;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;
import com.adventnet.apiutils.BuilderResourceBundle;

public class COptions extends JDialog implements ActionListener 
{
    private boolean initialized = false;
    JPanel  Top = null;
    JPanel  compilePanel = null;
    JLabel  labelJDK = null;
    JTextField  textJDK= null;
    JLabel  labelClassPath = null;
    JTextField  textClassPath= null;
    JLabel  spaceLabel = null;
    JButton  compOkButton = null;
    JButton  compCancelButton = null;
	JButton browseButton=null;
	JButton  clsAdder=null;
	JPanel oKCancelPanel=null;

    GridBagConstraints cons = new GridBagConstraints();
    Insets inset;
    JFileChooser fileLoad=null;
	JDialog dlg=null;

    JFileChooser chooseFile=null;
    String JDKPath=null;
    String Classpath=null;
	EditableList edl=null;
	boolean oKClicked=false;
	private BuilderResourceBundle resourceBundle=ToolsUtil.getBundle();
    TransverseContainer transCon=null;

    public COptions(JFrame jf,String title) {
        super(jf,title,true);
        this.setTitle(resourceBundle.getString("Compile Options"));
    }
    public COptions(JDialog jd,String title){
        super(jd,title,true);
        this.setTitle(resourceBundle.getString("Compile Options"));
    }

    public void setVisible(boolean bl) {
        super.setVisible(bl);
    }

	public void setUpProperties(){ 
        labelJDK.setText(resourceBundle.getString("JDK Directory"));
        labelClassPath.setText(resourceBundle.getString("CLASSPATH"));
        compOkButton.setText(resourceBundle.getString("  OK  "));
		browseButton.setText(resourceBundle.getString("Browse"));
        compCancelButton.setText(resourceBundle.getString("Cancel"));
		clsAdder.setText(resourceBundle.getString("Add"));
		textClassPath.setEnabled(false);
		compOkButton.setMnemonic(KeyEvent.VK_O);
		compCancelButton.setMnemonic(KeyEvent.VK_C);
        compOkButton.addActionListener(this);
        compCancelButton.addActionListener(this);
		browseButton.addActionListener(this);
		clsAdder.addActionListener(this);
		clsAdder.setPreferredSize(browseButton.getPreferredSize());
    }
	public void init() { 
        if (initialized == true) return; 
        this.setSize(getPreferredSize().width+600,getPreferredSize().height+125); 
        this.setResizable(false);
        Container container = getContentPane();
        container.setLayout(new BorderLayout()); 
        initVariables(); 
        setUpGUI(container); 
        setUpProperties(); 
    } 

	public void initVariables() { 
        Top= new JPanel();
        compilePanel= new JPanel();
        labelJDK= new JLabel();
        textJDK= new JTextField();
        labelClassPath= new JLabel();
        textClassPath= new JTextField();
        spaceLabel= new JLabel();
        oKCancelPanel=new JPanel();
        compOkButton= new JButton();
        compCancelButton= new JButton();
		browseButton=new JButton();
		clsAdder=new JButton();
		oKCancelPanel=new JPanel();
    }
	public void setUpGUI(Container container) 
    { 
        container.add(compilePanel,BorderLayout.CENTER);
        compilePanel.setLayout(new GridBagLayout());
        inset = new Insets(5,2,5,2);
        setConstraints(0,0,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
        compilePanel.add(labelJDK,cons);
		
        inset = new Insets(5,0,5,5);
        setConstraints(1,0,cons.RELATIVE,1,1.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
        compilePanel.add(textJDK,cons);
		
		inset = new Insets(2,2,2,2);
		setConstraints(2,0,cons.REMAINDER,1,0,0,cons.EAST,cons.NONE,inset,0,0);
		compilePanel.add(browseButton,cons);

        inset = new Insets(2,2,2,2);
        setConstraints(0,1,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
        compilePanel.add(labelClassPath,cons);
        
		inset = new Insets(2,2,2,2);
        setConstraints(1,1,cons.RELATIVE,1,1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
        compilePanel.add(textClassPath,cons);

		inset = new Insets(2,2,2,2);
        setConstraints(2,1,cons.REMAINDER,1,0,0.0,cons.CENTER,cons.NONE,inset,0,0);
        compilePanel.add(clsAdder,cons);

        inset = new Insets(0,0,0,0);
        setConstraints(0,2,cons.REMAINDER,1,1.0,0.0,cons.EAST,cons.NONE,inset,0,0);
        container.add(oKCancelPanel,BorderLayout.SOUTH);
		
        oKCancelPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        oKCancelPanel.add(compOkButton);
        oKCancelPanel.add(compCancelButton);
    }
	
	public void setConstraints(int x,int y,int width,int height,double wtX,double wtY,int anchor,int fill,Insets inset,int padX,int padY ) { 
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
    }

	private void updateClassPath() {
		// TODO: 
		// Read the corresponding classpath entries from the document and update the textbox;
		// after the dialog is closed, get the classpath entries and reupdate the document.  
		// Show Global classPath options which is written in coptions.xml
		if(edl==null){
			edl=new EditableList(resourceBundle.getString("Current ClassPath entries"));
			edl.setClasspathEditable();
			dlg=new JDialog(this,resourceBundle.getString("Update ClassPath Entries"),true);
			dlg.getContentPane().add(edl,BorderLayout.CENTER);
			JButton closeButton=new JButton(resourceBundle.getString("Close"));
			closeButton.addActionListener(this);
			closeButton.setActionCommand("Close");
			JPanel closePanel=new JPanel();
			closePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
			closePanel.add(closeButton);
			dlg.getContentPane().add(closePanel,BorderLayout.SOUTH);
			dlg.pack();
			dlg.setLocation(getScrLoc(dlg));
		}
		ToolsFileFilter mff=new ToolsFileFilter();
		mff.addExtension("jar");
		mff.addExtension("zip");
		edl.setFileFilter(mff);
		Element options=null;
		Document doc=null;
		try {
			File globalOptions=new File("."+File.separator+"projects"+File.separator+"coptions.xml");
			if(globalOptions.exists()) {
				DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
				DocumentBuilder db=dbf.newDocumentBuilder();
				doc=db.parse(globalOptions);
				options=(Element)doc.getElementsByTagName("Options").item(0);
				if(options==null) {
					// Global Options are not available;
					options=doc.createElement("COMPILE_OPTIONS");
					doc.getDocumentElement().appendChild(options);
				}
				edl.setClassPath(options.getAttribute("ClassPath"));
			}
			if(textClassPath.getText().trim().length()!=0){
				edl.setClassPath(textClassPath.getText().trim());
			}
			dlg.setVisible(true);
			textClassPath.setText(edl.getClassPath());
			// Now all updations are made in the previously show classpath adder.  
			// get the classpath entries from the editable List and update it to the document. 
			if(!globalOptions.exists()) {
				writeToXML();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==compCancelButton){
			compCancelButton_Clicked(ae);
		}
		else if(ae.getSource()==compOkButton){
			compOkButton_Clicked(ae);
		}
		else if(ae.getSource()==browseButton){
			browseButton_Clicked(ae);
		}
		else if(ae.getSource()==clsAdder){
			 updateClassPath();
		}
		else if(ae.getActionCommand().equals("Close")){
			textClassPath.setText(edl.getClassPath());
			dlg.dispose();
		}
	}
	
	public boolean isOKClicked() {
		return oKClicked;
	}
	
	public void compCancelButton_Clicked(ActionEvent ae) {
		oKClicked=false;
        this.setVisible(false);
    }

    public void compOkButton_Clicked(ActionEvent ae) {
        if(writeToXML() == true){
			oKClicked=true;
            this.setVisible(false);
        }
    }
	public void browseButton_Clicked(ActionEvent ae)
	{
		if(chooseFile==null) {
			chooseFile=new JFileChooser();
			chooseFile.setCurrentDirectory(new File(System.getProperty("user.dir")));
			chooseFile.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			chooseFile.setDialogTitle(resourceBundle.getString("Open"));
		}	
		int returnVal=chooseFile.showOpenDialog(this);
		if(returnVal==JFileChooser.APPROVE_OPTION){
			textJDK.setText(chooseFile.getSelectedFile().getAbsolutePath());
		}
	}
	
	public Point getScrLoc(Component comp) {
		Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
		int width=(int)(d.getWidth()-comp.getSize().getWidth())/2;
		int height=(int)(d.getHeight()-comp.getSize().getHeight())/2;
		return new Point(width,height);
	}

	public boolean writeToXML()
    {
        JDKPath=textJDK.getText().trim();
        Classpath=textClassPath.getText().trim();
		if(Classpath.trim().length()==0){
			Classpath=System.getProperty("path.separator")+"."+File.separator+"classes";
		}

        if(validateCompileArgs() == false){
            return false;
        }
        File f=new File(System.getProperty("user.dir")+File.separator+"projects"+File.separator+"coptions.xml");
        XMLNode root=new XMLNode();
        XMLNode addNode=new XMLNode();
        root.setNodeType(XMLNode.ELEMENT);
        root.setNodeName("Compile_Options");
        addNode.setNodeName("Options");
        addNode.setNodeType(XMLNode.ELEMENT);
        addNode.setAttribute("JDKPath",textJDK.getText().trim());
        addNode.setAttribute("ClassPath",Classpath);
        root.addChildNode(addNode);
        XMLDataWriter xmlwrite=new XMLDataWriter(f.toString(),root);		
        return true;
    }

	public boolean readXML()
    {
        File f=new File(System.getProperty("user.dir")+File.separator
                        +"projects"+File.separator+"coptions.xml");
        if(!f.exists()) return false;
        XMLDataReader xmlread=new XMLDataReader(f.toString(),false);
        XMLNode root=xmlread.getRootNode();
        Vector v=root.getChildNodes();
        for(int i=0;i<v.size();i++)
        {
            XMLNode	node=(XMLNode)v.elementAt(i);
            JDKPath=(node.getAttribute("JDKPath")).toString();
            Classpath=(node.getAttribute("ClassPath")).toString();
        }
        return true;
    }

    public void initialize()
    {
        textJDK.setText(JDKPath.trim());
        textClassPath.setText(Classpath.trim());
    }
		
	public void readFromXMLDocument() {
		if(transCon==null) {
			readXML();
			initialize();
		}
		Document doc=(Document)transCon.getTransverseComponent("XMLMODEL");
		Element compileOptions=(Element)doc.getElementsByTagName("COMPILE_OPTIONS");
		textClassPath.setText(compileOptions.getAttribute("ClassPath"));
	}
	
	public boolean validateCompileArgs()
    {
        if(JDKPath.trim().length()==0) {
            JOptionPane.showMessageDialog(null,resourceBundle.getString("Please enter the where JDK is installed"),resourceBundle.getString("Error"),JOptionPane.ERROR_MESSAGE);
            return false;
        }	
        if((new File(JDKPath.trim()+File.separator+"bin"+File.separator+"javac").exists() ==false) && (new File(JDKPath.trim()+File.separator+"bin"+File.separator+"javac.exe").exists()==false)) {
            JOptionPane.showMessageDialog(null,JDKPath.trim()+File.separator+"bin"+File.separator+"javac"+"not found",resourceBundle.getString("Error"),JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
