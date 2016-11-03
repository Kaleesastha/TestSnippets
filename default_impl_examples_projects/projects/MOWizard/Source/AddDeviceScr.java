//$Id: AddDeviceScr.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $
package com.adventnet.nms.tools.mowizard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import com.adventnet.nms.tools.objtorel.TransverseContainer;
import com.adventnet.nms.tools.objtorel.TransverseListener;
import com.adventnet.nms.tools.utils.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import com.adventnet.apiutils.BuilderResourceBundle;


public class AddDeviceScr extends AbstractTransversePanel implements ActionListener,TransverseListener
{
	
    //<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	javax.swing.JPanel  Top = null;
	javax.swing.JLabel  label_OID = null;
	javax.swing.JLabel  label_Type = null;
	javax.swing.JLabel  label_PollInt = null;
	javax.swing.JTextField  text_EOID = null;
	javax.swing.JTextField  text_DevType = null;
	javax.swing.JTextField  text_PolInt = null;
	javax.swing.JTextField  text_UsrTst = null;
	javax.swing.JTextField  text_DiscFilter = null;
	javax.swing.JButton  bUserTest = null;
	javax.swing.JButton  bDiscover = null;
	javax.swing.JPanel  fillPanel = null;
	javax.swing.JButton  buttonSelect = null;
	javax.swing.JCheckBox  chb_UserTester = null;
	javax.swing.JCheckBox  chb_DiscFilter = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>	

    //Custom declerations for this class    
    TransverseContainer transCon=null;
	TransversePanel Usertp=null;
	TransversePanel Disctp=null;
	FileWriter UsrTst_fWrite=null;
    FileWriter DscFil_fWrite=null;
	DiscStatus discStat=null;
	DiscStatus userStat=null;
	DevDefScr dds=null;
	JDialog dialog=null;
	JLabel  imageLabel=null;
	private BuilderResourceBundle resourceBundle =ToolsUtil.getBundle() ;

       
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
        bUserTest.addActionListener(this);
        bDiscover.addActionListener(this);
        buttonSelect.addActionListener(this);
        //<Begin_setUpProperties>

          try
          {
            Top.setMaximumSize(new Dimension(107,17));
            Top.setMinimumSize(new Dimension(107,17));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+Top,ex); 
          }

          try
          {
            label_OID.setText(resourceBundle.getString("Enterprise OID"));
            label_OID.setForeground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+label_OID,ex); 
          }

          try
          {
            label_Type.setText(resourceBundle.getString("DeviceType"));
            label_Type.setForeground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+label_Type,ex); 
          }

          try
          {
            label_PollInt.setText(resourceBundle.getString("Poll Interval"));
            label_PollInt.setForeground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+label_PollInt,ex); 
          }

          try
          {
            text_EOID.setToolTipText(resourceBundle.getString("Please enter the Enterprise OID"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+text_EOID,ex); 
          }

          try
          {
            text_DevType.setToolTipText(resourceBundle.getString("Please enter Device Type"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+text_DevType,ex); 
          }

          try
          {
            text_PolInt.setToolTipText(resourceBundle.getString("Please enter Polling Interval"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+text_PolInt,ex); 
          }

          try
          {
            text_UsrTst.setToolTipText(resourceBundle.getString("Please enter User Tester Class Name"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+text_UsrTst,ex); 
          }

          try
          {
            text_DiscFilter.setToolTipText(resourceBundle.getString("Please enter Discovery Filter Class Name"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+text_DiscFilter,ex); 
          }

          try
          {
            bUserTest.setActionCommand("Define ");
            bUserTest.setText(resourceBundle.getString("Select"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+bUserTest,ex); 
          }

          try
          {
            bDiscover.setText(resourceBundle.getString("Select"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+bDiscover,ex); 
          }

          try
          {
            buttonSelect.setText(resourceBundle.getString("Select Device"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+buttonSelect,ex); 
          }

          try
          {
            chb_UserTester.setText(resourceBundle.getString("UserTester"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+chb_UserTester,ex); 
          }

          try
          {
            chb_DiscFilter.setText(resourceBundle.getString("Discovery Filter"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+chb_DiscFilter,ex); 
          }

  //<End_setUpProperties>
        bUserTest.setPreferredSize(buttonSelect.getPreferredSize());
        bDiscover.setPreferredSize(buttonSelect.getPreferredSize());
        chb_DiscFilter.addActionListener(this);
        chb_UserTester.addActionListener(this);
		bDiscover.setEnabled(false);
		bUserTest.setEnabled(false);
		text_UsrTst.setEnabled(false);
		text_DiscFilter.setEnabled(false);
		text_PolInt.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent ke){
				if((ke.getKeyChar()>='0'&& ke.getKeyChar()<='9') || ke.getKeyChar()==ke.VK_BACK_SPACE){
				}
				else{
					Toolkit.getDefaultToolkit().beep();
					ke.consume();
				}
			}
		});
		imageLabel=new JLabel("");	
    }
    
    public void init()
  { 
      
        if(false) {
            //<Begin_init>
        if (initialized == true) return; 
        setPreferredSize(new Dimension(getPreferredSize().width+788,getPreferredSize().height+563)); 
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
        // let us set the initialzed variable to true so  
        // we dont initialize again even if init is called 
        initialized = true; 
      setUpMenus();
      setUpToolBar();
        

  //<End_init>
        }
        
        if (initialized == true) return; 
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
        // let us set the initialzed variable to true so  
        // we dont initialize again even if init is called 
        initialized = true; 
        setUpMenus();
        setUpToolBar();
    } 
    
    public void initVariables()throws Exception
  {    
        //<Begin_initVariables>
        Top= new javax.swing.JPanel();
        label_OID= new javax.swing.JLabel();
        label_Type= new javax.swing.JLabel();
        label_PollInt= new javax.swing.JLabel();
        text_EOID= new javax.swing.JTextField();
        text_DevType= new javax.swing.JTextField();
        text_PolInt= new javax.swing.JTextField();
        text_UsrTst= new javax.swing.JTextField();
        text_DiscFilter= new javax.swing.JTextField();
        bUserTest= new javax.swing.JButton();
        bDiscover= new javax.swing.JButton();
        fillPanel= new javax.swing.JPanel();
        buttonSelect= new javax.swing.JButton();
        chb_UserTester= new javax.swing.JCheckBox();
        chb_DiscFilter= new javax.swing.JCheckBox();

  //<End_initVariables>
        
    } 

    public void setUpGUI(Container container)throws Exception
  { 
        //<Begin_setUpGUI_Container>
container.add(Top,BorderLayout.CENTER);
	Top.setLayout(new GridBagLayout());
	inset = new Insets(10,10,5,10);
	setConstraints(0,0,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
	Top.add(label_OID,cons);
	inset = new Insets(5,10,5,10);
	setConstraints(0,1,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
	Top.add(label_Type,cons);
	inset = new Insets(5,10,5,10);
	setConstraints(0,2,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
	Top.add(label_PollInt,cons);
	inset = new Insets(10,10,5,10);
	setConstraints(1,0,-1,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
	Top.add(text_EOID,cons);
	inset = new Insets(5,10,5,10);
	setConstraints(1,1,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
	Top.add(text_DevType,cons);
	inset = new Insets(5,10,5,10);
	setConstraints(1,2,0,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
	Top.add(text_PolInt,cons);
	inset = new Insets(5,10,5,10);
	setConstraints(1,3,-1,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
	Top.add(text_UsrTst,cons);
	inset = new Insets(5,10,5,10);
	setConstraints(1,4,-1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
	Top.add(text_DiscFilter,cons);
	inset = new Insets(5,0,5,10);
	setConstraints(10,3,0,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
	Top.add(bUserTest,cons);
	inset = new Insets(5,0,5,10);
	setConstraints(10,4,0,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
	Top.add(bDiscover,cons);
	inset = new Insets(0,0,0,0);
	setConstraints(0,5,0,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
	Top.add(fillPanel,cons);
	fillPanel.setLayout(new FlowLayout(1,5,5));
	inset = new Insets(0,0,0,0);
	setConstraints(10,0,0,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
	Top.add(buttonSelect,cons);
	inset = new Insets(5,10,5,10);
	setConstraints(0,3,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
	Top.add(chb_UserTester,cons);
	inset = new Insets(5,10,5,10);
	setConstraints(0,4,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
	Top.add(chb_DiscFilter,cons);
	
  //<End_setUpGUI_Container>
    }

    private Window getParentContainer() {
        Container con=getParent();
        for(;!(con instanceof Window);con=con.getParent());
        return (Window)con;
    }

	public Point getScrLoc(Component comp) {
		// This method will return the x,y that will place a container in the center 
		// of the screen. 
		Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
		int width=(int)(d.getWidth()-comp.getSize().getWidth())/2;
		int height=(int)(d.getHeight()-comp.getSize().getHeight())/2;
		return new Point(width,height);
	}
    
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==bUserTest) {
            // Instantiate File writer with numeric argument as 1 since it represents UserTester 
            // class definition.
            bUserTest_Clicked(ae);
        }
        else if(ae.getSource()==bDiscover) {
            // Instantiate File writer with numeric argument as 2 since it represents Discovery Filter
            // class definition.
            bDiscover_Clicked(ae);
        }
        else if(ae.getSource()==buttonSelect) {
            buttonSelect_Clicked(ae);
        }
        else if(ae.getSource()==chb_DiscFilter) {
            if(chb_DiscFilter.isSelected()) {
                text_DiscFilter.setEnabled(true);
                bDiscover.setEnabled(true);
            }
            else {
				text_DiscFilter.setText("");
                text_DiscFilter.setEnabled(false);
				
                bDiscover.setEnabled(false);
            }
        }
        else if(ae.getSource()==chb_UserTester) {
            if(chb_UserTester.isSelected()) {
                text_UsrTst.setEnabled(true);
                bUserTest.setEnabled(true);
            }
            else {
				text_UsrTst.setText("");
                text_UsrTst.setEnabled(false);
                bUserTest.setEnabled(false);
            }
        }
    }	
	
    private void buttonSelect_Clicked(ActionEvent ae) {
        // Bring up the table of OIDType.data.
	if(dds==null) {
            JDialog dlg=(JDialog)getParentContainer();
            dds=new DevDefScr(dlg,resourceBundle.getString("List of Existing Devices"));
            dds.init();
            dds.setSize((int)dlg.getSize().getWidth()-100,(int)dlg.getSize().getHeight()-50);
            dds.setLocation(getScrLoc(dds));
            dds.loadDataFromFile();
        }
        dds.setVisible(true);
        if(dds.isDeviceSelected()) {
            Object data[]=dds.getSelectedDevice();
            text_EOID.setText((String)data[0]);
            text_DevType.setText((String)data[1]);
            text_PolInt.setText((String)data[2]);
			if(((String)data[3]).trim().length()!=0) {
	            text_DiscFilter.setText((String)data[3]);
				chb_DiscFilter.setSelected(true);
			}
			else {
				chb_DiscFilter.setSelected(false);
				text_DiscFilter.setText("");
			}
			if(((String)data[4]).trim().length()!=0) {
				text_UsrTst.setText((String)data[4]);
				chb_UserTester.setSelected(true);
			}
			else {
				text_UsrTst.setText((String)data[4]);
				chb_UserTester.setSelected(false);
			}
        }
    }
		
    
    void okButton_Clicked(ActionEvent ae) {

    }
    

    void bUserTest_Clicked(ActionEvent ae) {
		if(Usertp==null){
			Usertp=new TransversePanel();
			Usertp.setImageLabel(imageLabel);
			Usertp.addTransverseContainer(transCon);
			userStat=new DiscStatus();
			userStat.setServiceType(1);
			userStat.defineUserTester();
			UsrTst_fWrite=new FileWriter();
			Usertp.addComponents("UserTester",userStat,"."+File.separator+"images"+File.separator+"statuspoller.jpg");
			Usertp.addComponents("UserFile",UsrTst_fWrite,"."+File.separator+"images"+File.separator+"statuspoller.jpg");
			Usertp.initialize();
			userStat.loadScreenData();
			dialog=new JDialog((JDialog)getParentContainer(),resourceBundle.getString("Editor For Status Poller "),true);
			dialog.getContentPane().add(Usertp,BorderLayout.CENTER);
			dialog.getContentPane().add(imageLabel,BorderLayout.WEST);
			dialog.setSize(new Dimension(800,550));
			dialog.setLocation(getScrLoc(dialog));
		}	
		
		dialog.setVisible(true);
        // TODO: Since this button is closed try to get data from TransverseContainer
        // and do a setText();*/	

        if(transCon.containsKey("USR_TST_CLSNAME")==false) {
            return;
        }
        text_UsrTst.setText((String)transCon.getTransverseComponent("USR_TST_CLSNAME"));	
    }

    public void loadScreenData() {
        Document doc=(Document)transCon.getTransverseComponent("XMLMODEL");
        Element  deviceScr=(Element)doc.getElementsByTagName("DEVICE_PARAMS").item(0);
        if(deviceScr==null) {
            return;
        }
        text_EOID.setText(deviceScr.getAttribute("eoid"));
        text_DevType.setText(deviceScr.getAttribute("devType"));
        text_PolInt.setText(deviceScr.getAttribute("pollInt"));
        // if UserTester is defined then
        Element usrTst=(Element)doc.getElementsByTagName("USER_TESTER").item(0);
        if(usrTst!=null && usrTst.getAttribute("fileName")!=null &&
		   usrTst.getAttribute("fileName").trim().length()!=0 ) {
            chb_UserTester.setSelected(true);
			bUserTest.setEnabled(true);
            text_UsrTst.setText(usrTst.getAttribute("className"));
			transCon.addTransverseComponent("USR_TST_CLSNAME",usrTst.getAttribute("className"));
			transCon.addTransverseComponent("USR_TST_FILENAME",usrTst.getAttribute("fileName"));
			transCon.addTransverseComponent("USR_LOAD",usrTst.getAttribute("loaded"));
        }
        else {
            chb_UserTester.setSelected(false);
            text_UsrTst.setText("");
            text_UsrTst.setEnabled(false);
        }
        Element dscFil=(Element)doc.getElementsByTagName("DISC_FILTER").item(0);
        if(dscFil!=null && dscFil.getAttribute("fileName")!=null &&
			dscFil.getAttribute("fileName").trim().length()!=0) {
            chb_DiscFilter.setSelected(true);
			bDiscover.setEnabled(true);
            text_DiscFilter.setText(dscFil.getAttribute("className"));
			transCon.addTransverseComponent("DSC_FIL_CLSNAME",dscFil.getAttribute("className"));
			transCon.addTransverseComponent("DSC_FIL_FILENAME",dscFil.getAttribute("fileName"));
			transCon.addTransverseComponent("DSC_LOAD",dscFil.getAttribute("loaded"));
        }
        else {
            chb_DiscFilter.setSelected(false);
            text_DiscFilter.setText("");
            text_DiscFilter.setEnabled(false);
        }
    }

    
    void bDiscover_Clicked(ActionEvent ae) {
		if(Disctp==null){
			Disctp=new TransversePanel();
			Disctp.setImageLabel(imageLabel);
			Disctp.addTransverseContainer(transCon);
			discStat=new DiscStatus();
			discStat.setServiceType(2);
			discStat.defineDiscoveryFilter();
			DscFil_fWrite=new FileWriter();
			Disctp.addComponents("DiscFilter",discStat,"."+File.separator+"images"+File.separator+"discoveryfilter.jpg");
			Disctp.addComponents("DiscFile",DscFil_fWrite,"."+File.separator+"images"+File.separator+"discoveryfilter.jpg");
			Disctp.initialize();
			discStat.loadScreenData();
			dialog=new JDialog((JDialog)getParentContainer(),resourceBundle.getString("Editor For Discovery Filter"),true);
			dialog.getContentPane().add(Disctp,BorderLayout.CENTER);
			dialog.getContentPane().add(imageLabel,BorderLayout.WEST);
			dialog.setSize(new Dimension(800,550));
			dialog.setLocation(getScrLoc(dialog));
		}
		dialog.setVisible(true);

        // TODO: Since this screen is closed try to get data from TransverseContainer 
        // and do a setText();
		
        if(transCon.containsKey("DSC_FIL_CLSNAME")==false) {
            return;
        }
        text_DiscFilter.setText((String)transCon.getTransverseComponent("DSC_FIL_CLSNAME"));	
    }
    
    public Object[] getDeviceDetails() {
        // THis function is called by the caller of this dialog after this is closed either thru OK
        // or Cancel
        
        String userTester,discFil;
        
        if(text_UsrTst.getText().trim().length()==0) {
            userTester="  ";
        }
        else {
            userTester=text_UsrTst.getText().trim();
        }
        
        if(text_DiscFilter.getText().trim().length()==0) {
            discFil="  ";
        }
        
        else {
            discFil=text_DiscFilter.getText().trim();
        }
        
        Object data[] = {
            text_EOID.getText().trim(),text_DevType.getText().trim(),text_PolInt.getText().trim(),
            userTester,discFil
        };

        return data;
    }
    
    public void addTransverseContainer(TransverseContainer tCon) {
        transCon=tCon;
    }
    
    public int nextActionPerformed(String str) {
        // Do all validations and then make proper entries.	
        String errMsg=resourceBundle.getString("Sorry !! You have missed ");
        boolean err=false;
        if(text_EOID.getText().length()==0) {
            errMsg = errMsg+resourceBundle.getString(" Enterprise OID,");
            err=true;
        }
        if(text_DevType.getText().length()==0) {
            errMsg=errMsg+resourceBundle.getString(" Device Type, ");
            err=true;
        }
        if(text_PolInt.getText().length()==0) {
            errMsg = errMsg + resourceBundle.getString(" Poll Interval ");
            err=true;
        }
        if(err) {
            JOptionPane.showMessageDialog(null,errMsg,resourceBundle.getString("Error"),JOptionPane.OK_OPTION);
            return -1;
        }
        else {
            // create and add User Tester
            Document doc=(Document)transCon.getTransverseComponent("XMLMODEL");
            // create DEVICE_PARAMS node and add to CLASS_INFO
            Element deviceScr=(Element)doc.getElementsByTagName("DEVICE_PARAMS").item(0);
            if(deviceScr==null) {
                deviceScr=doc.createElement("DEVICE_PARAMS");
                deviceScr.setAttribute("eoid",text_EOID.getText().trim());
                deviceScr.setAttribute("devType",text_DevType.getText().trim());
                deviceScr.setAttribute("pollInt",text_PolInt.getText().trim());
                doc.getDocumentElement().appendChild(deviceScr);
            }
            else {
                deviceScr.setAttribute("eoid",text_EOID.getText().trim());
                deviceScr.setAttribute("devType",text_DevType.getText().trim());
                deviceScr.setAttribute("pollInt",text_PolInt.getText().trim());
            }
            Element UsrTstElement=(Element)doc.getElementsByTagName("USER_TESTER").item(0);
            Element DscFilElement=(Element)doc.getElementsByTagName("DISC_FILTER").item(0);
            
            if(chb_UserTester.isSelected()) {
				if(text_UsrTst.getText().trim().length()==0) {
					JOptionPane.showMessageDialog(null,resourceBundle.getString("Ooops!!! You have missed Status Poller Class Name"),resourceBundle.getString("Error"),JOptionPane.OK_OPTION);
					return -1;
				}
                // may be selected in two cases.
                // 1. already selected
                // case 1 may be found out by examining the tag in the XML
                // Document.
                //
                if(UsrTstElement!=null) {
                    UsrTstElement.setAttribute("className",(String)transCon.getTransverseComponent("USR_TST_CLSNAME"));
                    UsrTstElement.setAttribute("fileName",(String)transCon.getTransverseComponent("USR_TST_FILENAME"));
					UsrTstElement.setAttribute("loaded",(String)transCon.getTransverseComponent("USR_LOAD"));
                }
                else {
                    // 2. newly defined.
                    // case 2 may be found out by examining the tag in the XML Document.
                    Element userTest=doc.createElement("USER_TESTER");
                    userTest.setAttribute("className",(String)transCon.getTransverseComponent("USR_TST_CLSNAME"));
                    userTest.setAttribute("fileName",(String)transCon.getTransverseComponent("USR_TST_FILENAME"));
					userTest.setAttribute("loaded",(String)transCon.getTransverseComponent("USR_LOAD"));
                    doc.getDocumentElement().appendChild(userTest);
                }
            }
            else {
                // my be deselected in two cases.
                // 1. previously deselected
                
                // 2. now deselected.
                if(UsrTstElement!=null) {
                    //doc.getDocumentElement().removeChild(UsrTstElement);
					UsrTstElement.setAttribute("className","");
					UsrTstElement.setAttribute("fileName","");
					UsrTstElement.setAttribute("loaded","");
                }
            }

			//handle Discovery Filter
            if(chb_DiscFilter.isSelected()) {
				if(text_DiscFilter.getText().trim().length()==0) {
					JOptionPane.showMessageDialog(null,resourceBundle.getString("Ooops!!! You have missed Discovery Filter Class Name"),resourceBundle.getString("Error"),JOptionPane.OK_OPTION);
					return -1;
				}
                if(DscFilElement!=null) {
                    DscFilElement.setAttribute("className",(String)transCon.getTransverseComponent("DSC_FIL_CLSNAME"));
                    DscFilElement.setAttribute("fileName",(String)transCon.getTransverseComponent("DSC_FIL_FILENAME"));
					DscFilElement.setAttribute("loaded",(String)transCon.getTransverseComponent("DSC_LOAD"));
                }
                else {
                    // 2. newly defined.
                    // case 2 may be found out by examining the tag in the XML Document.
                     DscFilElement=doc.createElement("DISC_FILTER");
                    DscFilElement.setAttribute("className",(String)transCon.getTransverseComponent("DSC_FIL_CLSNAME"));
                    DscFilElement.setAttribute("fileName",(String)transCon.getTransverseComponent("DSC_FIL_FILENAME"));
					DscFilElement.setAttribute("loaded",(String)transCon.getTransverseComponent("DSC_LOAD"));
                    doc.getDocumentElement().appendChild(DscFilElement);
                }
            }
            else {
                if(DscFilElement!=null) {
                    //doc.getDocumentElement().removeChild(DscFilElement);
					DscFilElement.setAttribute("className","");
					DscFilElement.setAttribute("fileName","");
					DscFilElement.setAttribute("loaded","");

                }
            }
        }
        return 1;
    }
    public int previousActionPerformed(String str) {
        return 1;
    }
    
    public boolean finishActionPerformed() {
        return false;
    }
    
    public void cancelActionPerformed(String str) {
        if(str.trim().equals("Screen2")) {
            if(JOptionPane.showConfirmDialog(null,resourceBundle.getString("MO Generation is not complete. Do you want to cancel ???"),resourceBundle.getString("Warning"),JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
                getParentContainer().dispose();
            }
        }
    }
    
    public void closeActionPerformed() {
        
    }


    public AddDeviceScr()
  {
        //<Begin_AddDeviceScr>
    this.init();
  
    //<End_AddDeviceScr>
    }

    public AddDeviceScr(java.applet.Applet applet)
  {
        //<Begin_AddDeviceScr_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_AddDeviceScr_java.applet.Applet>
    }

     
    public void setUpConnections()throws Exception
  { 

        //<Begin_setUpConnections>

  //<End_setUpConnections>
    }
    
    public void setUpToolBar()
  { 

        //<Begin_setUpToolBar>

  //<End_setUpToolBar>
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
}



