//$Id: DevDefScr.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $

package com.adventnet.nms.tools.mowizard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.table.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.InputSource;
import javax.xml.parsers.*;
import com.adventnet.nms.tools.objtorel.TransverseContainer;
import com.adventnet.nms.tools.objtorel.TransverseListener;
import com.adventnet.nms.tools.utils.*;
import java.util.*;
import com.adventnet.apiutils.BuilderResourceBundle;
import com.adventnet.nms.tools.utils.ToolsUtil;

public class DevDefScr extends JDialog implements ActionListener
{
    //<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	javax.swing.JPanel  Top = null;
	javax.swing.JScrollPane  tableScroller = null;
	javax.swing.JTable  tableOIDType = null;
	javax.swing.JPanel  buttonPanel = null;
	javax.swing.JButton  buttonSelect = null;
	javax.swing.JButton  buttonCancel = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>
    // Custom Decleration.
    TransverseContainer transCon=null;
    OIDTableModel modelOIDType=null;
    Object        selectedData[]=null;
	private BuilderResourceBundle resourceBundle=ToolsUtil.getBundle();    
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
	
    public void setUpProperties()throws Exception
  { 

        //<Begin_setUpProperties>

          try
          {
            buttonSelect.setToolTipText(resourceBundle.getString("Click to Add a new device"));
            buttonSelect.setText(resourceBundle.getString("Select Device"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+buttonSelect,ex); 
          }

          try
          {
            buttonCancel.setText(resourceBundle.getString("Cancel"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+buttonCancel,ex); 
          }

  //<End_setUpProperties>
        buttonSelect.addActionListener(this);
		buttonCancel.addActionListener(this);
        buttonCancel.setPreferredSize(buttonSelect.getPreferredSize());
    }
    
    public void init()
  { 
        if(false) {
            //<Begin_init>
        if (initialized == true) return; 
        this.setSize(getPreferredSize().width+686,getPreferredSize().height+477); 
          setTitle("new_screen1");
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
          showStatus(resourceBundle.getString("Error in init method"),ex); 
        } 
        // let us set the initialzed variable to true so  
        // we dont initialize again even if init is called 
        initialized = true; 
      setUpMenus();
      setUpToolBar();
        

  //<End_init>
        }
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
            showStatus(resourceBundle.getString("Error in init method"),ex); 
        } 
        // let us set the initialzed variable to true so  
        // we dont initialize again even if init is called 
        initialized = true; 
        setUpMenus();
        setUpToolBar();
    }
    
    public void setUpConnections()throws Exception
  { 

        //<Begin_setUpConnections>

  //<End_setUpConnections>
    }
    
    public void initVariables()throws Exception
  { 
        //<Begin_initVariables>
        Top= new javax.swing.JPanel();
        tableScroller= new javax.swing.JScrollPane();
        tableOIDType= new javax.swing.JTable();
        buttonPanel= new javax.swing.JPanel();
        buttonSelect= new javax.swing.JButton();
        buttonCancel= new javax.swing.JButton();

  //<End_initVariables>
        Object[] tableHeader = { resourceBundle.getString("OID"),resourceBundle.getString("Type"),resourceBundle.getString("Poll Interval"),resourceBundle.getString("UserTester"),resourceBundle.getString("DiscoveryFilter")};
        modelOIDType=new OIDTableModel(tableHeader,0);
        tableOIDType=new JTable(modelOIDType);
        tableScroller=new JScrollPane(tableOIDType);
    }
    
    public void setUpToolBar()
  { 
        //<Begin_setUpToolBar>

  //<End_setUpToolBar>
    }
    
    public void setUpGUI(Container container)throws Exception
  { 

        //<Begin_setUpGUI_Container>
container.add(Top,BorderLayout.CENTER);
	Top.setLayout(new GridBagLayout());
	inset = new Insets(10,10,10,10);
	setConstraints(0,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
	Top.add(tableScroller,cons);
	tableScroller.getViewport().add(tableOIDType);
	inset = new Insets(0,10,10,10);
	setConstraints(0,1,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
	Top.add(buttonPanel,cons);
	buttonPanel.setLayout(new FlowLayout(2,10,5));
	buttonPanel.add(buttonSelect);
	buttonPanel.add(buttonCancel);
	
  //<End_setUpGUI_Container>
    }
    
    public void setUpMenus()
  { 

        //<Begin_setUpMenus>

  //<End_setUpMenus>
    }
    
    public void stop()
  { 

        //<Begin_stop>

  //<End_stop>
    }
    
    public void start()
  { 

        //<Begin_start>

  //<End_start>
    } 

    public void showStatus(String message)
  {
        //<Begin_showStatus_String>
     System.out.println(resourceBundle.getString("Internal Error :")+ message);
     //<End_showStatus_String>
    }
    
    public void showStatus(String message,Exception ex)
  {
        //<Begin_showStatus_String_Exception>
     System.out.println(resourceBundle.getString("Internal Error :")+ message);
     ex.printStackTrace();
     //<End_showStatus_String_Exception>
    }

    public boolean loadDataFromFile() {
        try {
            //XmlDocument doc;
            DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            DocumentBuilder db=dbf.newDocumentBuilder();
            Document doc=null;
            System.setProperty("nms.home",System.getProperty("user.dir"));
            String nmsHome=System.getProperty("nms.home");
            doc=db.parse(new File(nmsHome+File.separator+"conf"+File.separator+"OIDType.data"));
            populateDeviceTable(doc.getDocumentElement());
        }
        catch (Exception e) {
            /*Exception   x = e;
              if (e.getException () != null)
              x = e.getException (); */
            e.printStackTrace ();
            return false;

        } /*catch (IOException ioe) {
            ioe.printStackTrace ();
            return false;
            }*/
        return true;
    }
    
	private int getPropIndex(String propName) {
		propName=propName.trim().toUpperCase();
		if(propName.equals("OID")) {
			return 0;
		}
		else if(propName.equals("TYPE")) {
			return 1;
		}
		else if(propName.equals("POLL_INTERVAL")) {
			return 2;
		}
		else if(propName.equals("USER_TESTER")) {
			return 3;
		}
		else if(propName.equals("DISC_FILTER")) {
			return 4;
		}
		return -1;
	}
	
    private void populateDeviceTable(Element node) {
        if(node==null) {
            return;
        }
        NamedNodeMap nnn=node.getAttributes();
        Object data[]= {"  ","  ","  ","  ","  "};
        for(int i=0;i<nnn.getLength();i++) {
            Node n=nnn.item(i);
            // Using section to populate Table
            switch(getPropIndex(n.getNodeName())) {
            case 0:
                data[0]=n.getNodeValue();
                break;
            case 1:
                data[1]=n.getNodeValue();
                break;
            case 2:
                data[2]=n.getNodeValue();
                break;
            case 3:
                if(n.getNodeValue()!=null) {
                    data[3]=n.getNodeValue();
                }
                else {
                    data[3]="  ";
                }
                break;
            case 4:
                if(n.getNodeValue()!=null) {
                    data[4]=n.getNodeValue();
                }
                else {
                    data[4]="  ";
                }
                break;
            }	
        }
        ((DefaultTableModel)tableOIDType.getModel()).addRow(data);
        for(Node n=node.getFirstChild(); n != null; n=n.getNextSibling()) {
            if(n.getNodeType()==Node.ELEMENT_NODE) {
                populateDeviceTable((Element)n);
            }
        }
    }    

    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==buttonSelect) {
            buttonSelect_Clicked(ae);
        }
        else if(ae.getSource()==buttonCancel) {
            buttonCancel_Clicked(ae);
        }
    }

    private void buttonCancel_Clicked(ActionEvent ae) {
        selectedData=null;
		this.dispose();
    }

    public boolean isDeviceSelected() {
        if(selectedData==null) {
            return false;
        }
        else {
            return true;
        }
    }
    
    private Container getParentContainer(Container con) {
        for(; !(con instanceof Window);con=con.getParent());
        return (Window)con;
    }
    
    public void addTransverseContainer(TransverseContainer tCon) throws NullPointerException {
        if(tCon==null) {
            throw new NullPointerException(resourceBundle.getString("Trying to add null to a TransverseContainer"));
        }
        transCon=tCon;
    }

    public DevDefScr(JDialog parent,String title) {
        super(parent,title,true);
    }
    
    public DevDefScr()
  {
        //<Begin_DevDefScr>
    pack();
    this.setTitle(resourceBundle.getString("DevDefScr"));
  
    //<End_DevDefScr>
    }

    public DevDefScr(java.applet.Applet applet)
  {
        //<Begin_DevDefScr_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setTitle(resourceBundle.getString("DevDefScr"));
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_DevDefScr_java.applet.Applet>
    }
	
	
    public static void main(String[] args) {
        JFrame frame=new JFrame();
        DevDefScr dds=new DevDefScr();
        dds.init();
        dds.loadDataFromFile();
        TransverseContainer tCon=new TransverseContainer();
        dds.addTransverseContainer(tCon);
        frame.getContentPane().add(dds,BorderLayout.CENTER);
        frame.setSize(600,400);
        frame.setLocation(200,150);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent we) {
                    System.exit(0);
                }
            });
        if(false) {
            //<Begin_main_String[]>
            //<End_main_String[]>
        }
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

    private void buttonSelect_Clicked(ActionEvent ae) {
        // When this button is clicked if any row is selected save in a datastructure.
        if(tableOIDType.getSelectedRow()==-1) {
            return;
        }
        int row=tableOIDType.getSelectedRow();
        Object data[]= {
            tableOIDType.getValueAt(row,0),tableOIDType.getValueAt(row,1),tableOIDType.getValueAt(row,2),
            tableOIDType.getValueAt(row,3),tableOIDType.getValueAt(row,4)};
        selectedData=data;
        this.setVisible(false);
    }

     

    
    public Object[] getSelectedDevice() {
        return selectedData;
    }
    
    private Window getParentContainer() {
        Container con=getParent();
        for(;!(con instanceof Window);con=con.getParent());
        return (Window)con;
    }

    public void setVisible(boolean bl)
  {
        if(false) {
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
        super.setVisible(bl);
    }
}

class OIDTableModel extends DefaultTableModel {
    OIDTableModel(Object[] tHdr,int rowNum) {
        super(tHdr,rowNum);
    }

    public boolean isCellEditable(int row,int col) {
        return false;
    }
}




