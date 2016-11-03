//$Id: DiscStatus.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $
package com.adventnet.nms.tools.mowizard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.applet.*;
import com.adventnet.nms.tools.objtorel.TransverseContainer;
import com.adventnet.nms.tools.objtorel.TransverseListener;
import com.adventnet.nms.tools.utils.ToolsFileFilter;
import org.w3c.dom.*;
import org.xml.sax.*;
import com.adventnet.apiutils.BuilderResourceBundle;
import com.adventnet.nms.tools.utils.ToolsUtil;


/**
 *  This screen is used to define both Discovery Filter and User Tester Routines  
 *  When this screen is invoked for defining User Tester this will define UserTester
 *  Routines, and similar behavior is exhibited while defining Discovery Filter.  
 */

public class DiscStatus extends AbstractTransversePanel implements ActionListener,TransverseListener,ItemListener 
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	javax.swing.JPanel  Top = null;
	javax.swing.JPanel  paneloption = null;
	javax.swing.JPanel  panelLoad = null;
	javax.swing.JLabel  loadLabel = null;
	javax.swing.JTextField  text_LoadPath = null;
	javax.swing.JButton  browseLoadButton = null;
	javax.swing.JRadioButton  rdb_load = null;
	javax.swing.JRadioButton  rdb_define = null;
	javax.swing.JLabel  spaceLabel = null;
	javax.swing.JLabel  defineLabel = null;
	javax.swing.JTextField  text_definepath = null;
	javax.swing.JTextArea  txtInfo = null;
	javax.swing.JLabel  labelFill = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>
	// Custom Declerations to support both Discovery Service and Status Polling Service. 
	ToolsFileFilter tff=null;

	ButtonGroup grouploadDefine=null;
	int serviceType=-1;
	JFileChooser fc=null;
	TransverseContainer transCon=null;
	private BuilderResourceBundle resourceBundle=ToolsUtil.getBundle();
	public void setServiceType(int sType) {
		// Service Type  = 1  ----- UserTester
		// Service Type  = 2  ----- Discovery Filter
		serviceType=sType;
	}

	public int getServiceType() {
		return serviceType;
	}	

	public void loadScreenData() {
		Document doc=(Document)transCon.getTransverseComponent("XMLMODEL");
		if(serviceType==1){
			Element statPoll=(Element)doc.getElementsByTagName("USER_TESTER").item(0);
			if(statPoll != null && statPoll.getAttribute("fileName")!=null && statPoll.getAttribute("fileName").trim().length()!=0){
				String status=statPoll.getAttribute("loaded");
				if(status.equals("true")){
					rdb_load.setSelected(true);
					text_definepath.setEnabled(false);
					text_LoadPath.setText(statPoll.getAttribute("fileName"));
				}
				else{
					rdb_define.setSelected(true);
					text_LoadPath.setEnabled(false);
					browseLoadButton.setEnabled(true);
					text_definepath.setText(statPoll.getAttribute("className"));
				}
			}
		}
		if(serviceType==2){
			Element discFil=(Element)doc.getElementsByTagName("DISC_FILTER").item(0);
			if(discFil != null && discFil.getAttribute("fileName")!=null && discFil.getAttribute("fileName").trim().length()!=0){
				String status=discFil.getAttribute("loaded");
				if(status.equals("true")){
					rdb_load.setSelected(true);
					rdb_define.setEnabled(false);
					text_definepath.setEnabled(false);
					text_LoadPath.setText(discFil.getAttribute("fileName"));
				}
				else{
					rdb_load.setEnabled(false);
					rdb_define.setSelected(true);
					text_LoadPath.setEnabled(false);
					browseLoadButton.setEnabled(true);
					text_definepath.setText(discFil.getAttribute("className"));
				}
			}	
		}
	}	
	public void setJMacsProperties() {
		
	}

	

	public void setUpProperties()throws Exception
  { 

		//<Begin_setUpProperties>

          try
          {
            paneloption.setBorder(new javax.swing.border.LineBorder(new Color(-16777216),1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+paneloption,ex); 
          }

          try
          {
            loadLabel.setText("");
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+loadLabel,ex); 
          }

          try
          {
            browseLoadButton.setText(resourceBundle.getString("Browse"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+browseLoadButton,ex); 
          }

          try
          {
            txtInfo.setWrapStyleWord(true);
            txtInfo.setEditable(false);
            txtInfo.setText("");
            txtInfo.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.LineBorder(new Color(-16777216),1),resourceBundle.getString("Help"),0,0,new Font("Dialog",0,12),new Color(-13434829)));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+txtInfo,ex); 
          }

  //<End_setUpProperties>

  		rdb_define.setSelected(true);
		text_LoadPath.setEnabled(false);
		browseLoadButton.setEnabled(false);
		browseLoadButton.addActionListener(this);
		rdb_define.addItemListener(this);
		rdb_load.addItemListener(this);
		txtInfo.setBackground(getBackground());
		txtInfo.setLineWrap(true);
		txtInfo.setFont(new Font("SansSerif",Font.PLAIN,14));

	} 
	public void init()
  { 

		//<Begin_init>
        if (initialized == true) return; 
        //setPreferredSize(new Dimension(getPreferredSize().width+686,getPreferredSize().height+569)); 
        //setSize(getPreferredSize()); 
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
	public void setUpConnections()throws Exception
  { 

		//<Begin_setUpConnections>

  //<End_setUpConnections>
	} 
	public void initVariables()throws Exception
  { 

		//<Begin_initVariables>
        Top= new javax.swing.JPanel();
        paneloption= new javax.swing.JPanel();
        panelLoad= new javax.swing.JPanel();
        loadLabel= new javax.swing.JLabel();
        text_LoadPath= new javax.swing.JTextField();
        browseLoadButton= new javax.swing.JButton();
        rdb_load= new javax.swing.JRadioButton();
        rdb_define= new javax.swing.JRadioButton();
        spaceLabel= new javax.swing.JLabel();
        defineLabel= new javax.swing.JLabel();
        text_definepath= new javax.swing.JTextField();
        txtInfo= new javax.swing.JTextArea();
        labelFill= new javax.swing.JLabel();

  //<End_initVariables>
		grouploadDefine=new ButtonGroup();
		grouploadDefine.add(rdb_define);
		grouploadDefine.add(rdb_load);
		tff=new ToolsFileFilter();


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
	Top.setLayout(new BorderLayout(5,5));
	Top.add(paneloption,BorderLayout.CENTER);
	paneloption.setLayout(new GridBagLayout());
	inset = new Insets(5,5,5,5);
	setConstraints(0,1,1,1,0.1,0.1,cons.WEST,cons.BOTH,inset,0,0);
	paneloption.add(panelLoad,cons);
	panelLoad.setLayout(new GridBagLayout());
	inset = new Insets(5,20,5,5);
	setConstraints(0,3,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
	panelLoad.add(loadLabel,cons);
	inset = new Insets(5,5,5,5);
	setConstraints(1,3,1,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
	panelLoad.add(text_LoadPath,cons);
	inset = new Insets(5,5,5,5);
	setConstraints(2,3,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
	panelLoad.add(browseLoadButton,cons);
	inset = new Insets(10,5,0,0);
	setConstraints(0,2,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
	panelLoad.add(rdb_load,cons);
	inset = new Insets(10,5,0,0);
	setConstraints(0,0,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
	panelLoad.add(rdb_define,cons);
	inset = new Insets(5,5,5,5);
	setConstraints(0,4,0,1,0.1,0.1,cons.WEST,cons.BOTH,inset,0,0);
	panelLoad.add(spaceLabel,cons);
	inset = new Insets(5,20,5,5);
	setConstraints(0,1,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
	panelLoad.add(defineLabel,cons);
	inset = new Insets(5,5,5,5);
	setConstraints(1,1,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
	panelLoad.add(text_definepath,cons);
	inset = new Insets(5,5,5,5);
	setConstraints(0,0,1,1,0.1,0.1,cons.WEST,cons.BOTH,inset,0,0);
	paneloption.add(txtInfo,cons);
	Top.add(labelFill,BorderLayout.WEST);
	
  //<End_setUpGUI_Container>

		grouploadDefine.add(rdb_define);
		grouploadDefine.add(rdb_load);


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


	public DiscStatus()
  {
		//<Begin_DiscStatus>
    this.init();
  
    //<End_DiscStatus>
	}

	public DiscStatus(java.applet.Applet applet)
  {
		//<Begin_DiscStatus_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_DiscStatus_java.applet.Applet>
	}

	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==rdb_define) {
			defineService_Clicked(ae);
		}
		else if(ae.getSource()==rdb_load) {
			loadService_Clicked(ae);
		}
		else if(ae.getSource()==browseLoadButton) {
			browseLoadButton_Clicked(ae);
		}
	}	
	
	private void loadService_Clicked(ActionEvent ae) {
	}


	private void browseLoadButton_Clicked(ActionEvent ae) {
		int returnVal=0;
		if(fc==null) {
			fc=new JFileChooser();
			fc.setCurrentDirectory(new File("."));
		}
		if(serviceType==1) {
			fc.setDialogTitle(resourceBundle.getString("Load User Tester Java Source File"));
		}	
		else if(serviceType==2) {
			fc.setDialogTitle(resourceBundle.getString("Load Discovery Filter Source File"));
		}
		tff.addExtension("java");
		fc.setFileFilter(tff);
		returnVal=fc.showOpenDialog(this);
		if(returnVal==JFileChooser.APPROVE_OPTION) {
			text_LoadPath.setText(fc.getSelectedFile().toString());
		}
	}		


	private void defineService_Clicked(ActionEvent ae) {
		// Find out the service TYpe and work according to it. 
		text_LoadPath.setEnabled(false);
		browseLoadButton.setEnabled(false);
		switch(serviceType) {
			case 1: // This service Type is for defining User Tester.
				defineUserTester();			
				break;
			case 2: // This service Type is for defining Discovery Filter.
				defineDiscoveryFilter();
				break;
		}
	}

	public void defineDiscoveryFilter() {
		rdb_define.setText(resourceBundle.getString("Define Discovery Filter"));
		rdb_load.setText(resourceBundle.getString("Load Discovery Filter"));
		loadLabel.setText(resourceBundle.getString("FileName"));
		defineLabel.setText(resourceBundle.getString("ClassName"));
		txtInfo.setText(resourceBundle.getString("Installation of a Discovery Filter can be carried out in two ways.")+"\n\n"+resourceBundle.getString("1)By defining a new Discovery Filter.")+"\n\n" +resourceBundle.getString("2)By Loading an existing Discovery Filter.")+"\n\n"+resourceBundle.getString("For Defining a Discovery Filter classname should be entered.The package name is optional.")+"\n\n"+resourceBundle.getString("An existing Discovery Filter can be loaded by entering the fullpath of the .java file.")+"\n\n"+resourceBundle.getString("All the relevant updations of the Discovery Filter are reflected only after successful compilation of the Discovery Filter source"));
	}	

	public void defineUserTester() {
		// Do initializations.
		rdb_define.setText(resourceBundle.getString("Define Status Poller"));
		rdb_load.setText(resourceBundle.getString("Load Status Poller"));
		loadLabel.setText(resourceBundle.getString("FileName"));
		defineLabel.setText(resourceBundle.getString("ClassName"));
		txtInfo.setText(resourceBundle.getString("Installation of a StatusPoller can be carried out in two ways.")+"\n\n"+resourceBundle.getString("1)By defining a new Status Poller.")+"\n\n"+resourceBundle.getString("2)By Loading an existing Status Poller.")+"\n\n"+resourceBundle.getString("For Defining a Status Poller classname should be entered.The package name is optional.")+"\n\n"+resourceBundle.getString("An existing Status Poller can be loaded by entering the fullpath of the .java file.")+"\n\n"+resourceBundle.getString("All the relevant updations of the Status Poller are reflected only after successful compilation of the Status Poller source"));
	}	
	public void addTransverseContainer(TransverseContainer tCon){
		transCon=tCon;
	}

	public int nextActionPerformed(String str) {
		if(rdb_load.isSelected() && text_LoadPath.getText().trim().length()==0) {
			if(serviceType==1){
				JOptionPane.showMessageDialog(null,resourceBundle.getString("Please select a Status Poller "),resourceBundle.getString("Error"),JOptionPane.ERROR_MESSAGE);
				return -1;
			}
			if(serviceType==2){
				JOptionPane.showMessageDialog(null,resourceBundle.getString("Please select a Discovery Filter "),resourceBundle.getString("Error"),JOptionPane.ERROR_MESSAGE);
				return -1;
			}
		}
		
		if(rdb_load.isSelected() && !(new File(text_LoadPath.getText().trim()).exists())){
			JOptionPane.showMessageDialog(null,
                        java.text.MessageFormat.format(resourceBundle.getString("The FileName {0} does not exist."), new String[]{""+text_LoadPath.getText().trim()}),
                        resourceBundle.getString("Error"),JOptionPane.ERROR_MESSAGE);

			return -1;
		}
		
		if(rdb_load.isSelected()) {
			transCon.addTransverseComponent("LOAD","true");
			transCon.addTransverseComponent("FILENAME",text_LoadPath.getText().trim());
		}
		
		FileWriter discFil=null;
		if(!rdb_load.isSelected()){
			if(text_definepath.getText().trim().length()==0){
				if(serviceType==1){
					JOptionPane.showMessageDialog(null,resourceBundle.getString("Please enter a Status Poller "),resourceBundle.getString("Error"),JOptionPane.ERROR_MESSAGE);
					return -1;
				}
				if(serviceType==2){
					JOptionPane.showMessageDialog(null,resourceBundle.getString("Please enter a Discovery Filter "),resourceBundle.getString("Error"),JOptionPane.ERROR_MESSAGE);
					return -1;
				}
			}
			
			if(serviceType==1){
				discFil=(FileWriter)transCon.getTransverseComponent("UserFile");
				transCon.addTransverseComponent("LOAD","false");
			}
			if(serviceType==2){
				discFil=(FileWriter)transCon.getTransverseComponent("DiscFile");
				transCon.addTransverseComponent("LOAD","false");
			}
			transCon.addTransverseComponent("FILENAME",text_definepath.getText().trim());

		}
		transCon.addTransverseComponent("SERVICETYPE",String.valueOf(serviceType));
		return 1;
	}

	public int previousActionPerformed(String str) {
		return 1;
	}

	public boolean finishActionPerformed() {
		return false;
	}

	public void cancelActionPerformed(String str) {
		((JDialog)getParentContainer(this)).dispose();
	}

	public void closeActionPerformed() {
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
  public Container getParentContainer(Container con)
  {
	  while(!(con instanceof JDialog)){
		  con=con.getParent();
	  }
	  return con;
  }

  public void itemStateChanged(ItemEvent ie)
  {
	  if(ie.getStateChange()==ItemEvent.SELECTED){
		  if(ie.getSource()==rdb_load){
			  browseLoadButton.setEnabled(true);
			  text_definepath.setEnabled(false);
			  text_LoadPath.setEnabled(true);
			  defineLabel.setEnabled(false);
			  loadLabel.setEnabled(true);
		  }
		  if(ie.getSource()==rdb_define){
			  browseLoadButton.setEnabled(false);
			  text_definepath.setEnabled(true);
			  text_LoadPath.setEnabled(false);
			  defineLabel.setEnabled(true);
			  loadLabel.setEnabled(false);
		  }
	  }
  }
}
