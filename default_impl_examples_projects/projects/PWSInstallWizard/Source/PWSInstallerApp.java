
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// Kindly refer to tcats_edit_source_files.html file under help/source_generation directory for details

/*$Id: PWSInstallerApp.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $*/
 
//<Begin_Version>
//version$44
//<End_Version>

package com.adventnet.studio.framework.installer;




import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.zip.*;

import com.adventnet.builder.utils.general.PureUtils;




public class PWSInstallerApp extends JFrame
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private static final String param[] = {};
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JPanel wizPanel = null;
	com.adventnet.studio.framework.installer.InstallPathPanel InstallPathPanel1 = null;
	com.adventnet.studio.framework.installer.ReadmePanel ReadmePanel1 = null;
	com.adventnet.studio.framework.installer.InstallProgressPanel InstallProgressPanel1 = null;
	javax.swing.JPanel titlePanel = null;
	javax.swing.JLabel wizTitleLabel = null;
	javax.swing.JSeparator titleSep = null;
	javax.swing.JLabel wizImgLabel = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JPanel wizButtonPanel = null;
	javax.swing.JButton backBtn = null;
	javax.swing.JButton nextBtn = null;
	javax.swing.JButton closeBtn = null;
	javax.swing.JSeparator JSeparator1 = null;
	com.adventnet.beans.utilbeans.OptionDialogInformer OptionDialogInformer1 = null;
	//<End_Variable_Declarations>

	String[] cardNames = new String[3];
	int currentCardIndex = 0;
	boolean standalone = true;
    private boolean result=false;
	String pwsNarFile;
	String installLocation;
    private Timer timer=null;
    InstallPwsNar install = null;
    private boolean isInstalled=false;//To identify whether a Studio NAR is already installed
	void setPwsNarFile(String arg)
	{
	     pwsNarFile = arg;
	}
	
	void setInstallLocation(String arg)
	{
	     installLocation = arg;
	}

    void setStandAlone(boolean flag)
    {
         standalone = flag;
         if(!standalone)
         {
              nextBtn.doClick();
              cardNames = new String[2];
              	cardNames[0] = "ReadmePanel1";
	cardNames[1] = "InstallProgressPanel1";
	currentCardIndex = 0;
	backBtn.setEnabled(false);	
         }
    }


  public PWSInstallerApp()
  {
    //<Begin_PWSInstallerApp>
    pack();
  
    //<End_PWSInstallerApp>
  }

  public PWSInstallerApp(java.applet.Applet applet)
  {
    //<Begin_PWSInstallerApp_java.applet.Applet>
    this.applet = applet;
    pack();
  
    //<End_PWSInstallerApp_java.applet.Applet>
  }

  public void setVisible(boolean bl)
  {
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

    public void init()
  {
        //<Begin_init> 
        if (initialized == true) return; 
        this.setSize(getPreferredSize().width+722,getPreferredSize().height+477); 
          setTitle("PWSInstallerApp");
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
          showStatus("Error in init method",ex); 
        } 
        // let us set the initialized variable to true so  
        // we dont initialize again even if init is called 
        initialized = true; 

  
        //<End_init>
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
            }
        return value;

  
           //<End_getParameter_String>
  } 
  public void stop()
  {
       //<Begin_stop> 
       if(!running)
 return;
 running=false;

  
       //<End_stop>
  } 
  public void start()
  {
       //<Begin_start> 
       if(running)
 return;
 running=true;

  
       //<End_start>
  } 
  public void setProperties(java.util.Properties props)
  {
         //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  } 
  public static void main(String [] args)
  {
         //<Begin_main_String[]> 
	com.adventnet.apiutils.Utility.parseAndSetParameters(param,args);
	PWSInstallerApp frame = new PWSInstallerApp();
	frame.setVisible(true);
	frame.addWindowListener(new WindowAdapter()
	 {
	    public void windowClosing(WindowEvent evt)
	       {
	          System.exit(0);
	       }
	 });

  
         //<End_main_String[]>
  } 
  public void setUpProperties()
  {
  //<Begin_setUpProperties> 

          try
          {
            Top.setBorder(new javax.swing.border.EmptyBorder(5,5,5,5));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+Top,ex); 
          }

//<UserCode_Begin_Bean_components>

//<UserCode_End_Bean_components>

          try
          {
            wizTitleLabel.setHorizontalAlignment(2);
            wizTitleLabel.setFont(new Font("SansSerif",0,12));
            wizTitleLabel.setForeground(new Color(-16777216));
            wizTitleLabel.setHorizontalTextPosition(4);
            wizTitleLabel.setText("Studio NAR installer wizard");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+wizTitleLabel,ex); 
          }

//<UserCode_Begin_Bean_wizTitleLabel>

//<UserCode_End_Bean_wizTitleLabel>

          try
          {
            wizImgLabel.setHorizontalAlignment(2);
            wizImgLabel.setFont(new Font("SansSerif",0,12));
            wizImgLabel.setForeground(new Color(-16777216));
            wizImgLabel.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+wizImgLabel,ex); 
          }

//<UserCode_Begin_Bean_wizImgLabel>
  wizImgLabel.setIcon(com.adventnet.apiutils.Utility.findImage("/com/adventnet/studio/framework/installer/images/pwsinstaller_wiz.png",null));
//<UserCode_End_Bean_wizImgLabel>

          try
          {
            backBtn.setFont(new Font("SansSerif",0,12));
            backBtn.setHorizontalTextPosition(4);
            backBtn.setText("Back");
            backBtn.setEnabled(false);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+backBtn,ex); 
          }

//<UserCode_Begin_Bean_backBtn>

//<UserCode_End_Bean_backBtn>

          try
          {
            nextBtn.setFont(new Font("SansSerif",0,12));
            nextBtn.setHorizontalTextPosition(4);
            nextBtn.setText("Next");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+nextBtn,ex); 
          }

//<UserCode_Begin_Bean_nextBtn>

//<UserCode_End_Bean_nextBtn>

          try
          {
            closeBtn.setFont(new Font("SansSerif",0,12));
            closeBtn.setHorizontalTextPosition(4);
            closeBtn.setText("Close");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+closeBtn,ex); 
          }

//<UserCode_Begin_Bean_closeBtn>

//<UserCode_End_Bean_closeBtn>

          try
          {
            OptionDialogInformer1.setDialogType(1);
            OptionDialogInformer1.setDialogTitle("Error");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+OptionDialogInformer1,ex); 
          }

//<UserCode_Begin_Bean_OptionDialogInformer1>
	  OptionDialogInformer1.setComponent(this);
//<UserCode_End_Bean_OptionDialogInformer1>
		wizButtonPanel.setPreferredSize(new Dimension(wizButtonPanel.getPreferredSize().width+465,wizButtonPanel.getPreferredSize().height+2));
		JPanel3.setPreferredSize(new Dimension(JPanel3.getPreferredSize().width+10,JPanel3.getPreferredSize().height+10));
		wizTitleLabel.setPreferredSize(new Dimension(wizTitleLabel.getPreferredSize().width+42,wizTitleLabel.getPreferredSize().height+0));
		titlePanel.setPreferredSize(new Dimension(titlePanel.getPreferredSize().width+299,titlePanel.getPreferredSize().height+24));
		JPanel2.setPreferredSize(new Dimension(JPanel2.getPreferredSize().width+10,JPanel2.getPreferredSize().height+10));
		JPanel1.setPreferredSize(new Dimension(JPanel1.getPreferredSize().width+148,JPanel1.getPreferredSize().height+10));

  
          //<End_setUpProperties>

  } 
  public void initVariables()
  {
        //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        JPanel1= new javax.swing.JPanel();
        JPanel2= new javax.swing.JPanel();
        wizPanel= new javax.swing.JPanel();
        InstallPathPanel1= new com.adventnet.studio.framework.installer.InstallPathPanel(applet);
        ReadmePanel1= new com.adventnet.studio.framework.installer.ReadmePanel(applet);
        InstallProgressPanel1= new com.adventnet.studio.framework.installer.InstallProgressPanel(applet);
        titlePanel= new javax.swing.JPanel();
        wizTitleLabel= new javax.swing.JLabel();
        titleSep= new javax.swing.JSeparator();
        wizImgLabel= new javax.swing.JLabel();
        JPanel3= new javax.swing.JPanel();
        wizButtonPanel= new javax.swing.JPanel();
        backBtn= new javax.swing.JButton();
        nextBtn= new javax.swing.JButton();
        closeBtn= new javax.swing.JButton();
        JSeparator1= new javax.swing.JSeparator();
        OptionDialogInformer1= new com.adventnet.beans.utilbeans.OptionDialogInformer();

  
        //<End_initVariables>

	//Timer will watch the installation operation
	//If the installation completes/fails close
	//button is made enabled.
	timer = new Timer(10, new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
		    if(install.done()||install.cancelled()){
			closeBtn.setEnabled(true);
			timer.stop();
		    }       
		}
	    });
  } 
   
   
   
   
  public void setUpGUI(Container container)
  {
//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));
Top.add(JPanel1,BorderLayout.CENTER);
JPanel1.setLayout(new BorderLayout(5,5));
JPanel1.add(JPanel2,BorderLayout.CENTER);
JPanel2.setLayout(new BorderLayout(5,5));
JPanel2.add(wizPanel,BorderLayout.CENTER);
wizPanel.setLayout(new CardLayout(5,5));
wizPanel.add(InstallPathPanel1,"InstallPathPanel1");
wizPanel.add(ReadmePanel1,"ReadmePanel1");
wizPanel.add(InstallProgressPanel1,"InstallProgressPanel1");
JPanel2.add(titlePanel,BorderLayout.NORTH);
titlePanel.setLayout(new BorderLayout(5,5));
titlePanel.add(wizTitleLabel,BorderLayout.CENTER);
titlePanel.add(titleSep,BorderLayout.SOUTH);
JPanel1.add(wizImgLabel,BorderLayout.WEST);
Top.add(JPanel3,BorderLayout.SOUTH);
JPanel3.setLayout(new BorderLayout(5,5));
JPanel3.add(wizButtonPanel,BorderLayout.CENTER);
wizButtonPanel.setLayout(new FlowLayout(2,5,5));
wizButtonPanel.add(backBtn);
wizButtonPanel.add(nextBtn);
wizButtonPanel.add(closeBtn);
JPanel3.add(JSeparator1,BorderLayout.NORTH);

  
//<End_setUpGUI_Container>
	cardNames[0] = "InstallPathPanel1";
	cardNames[1] = "ReadmePanel1";
	cardNames[2] = "InstallProgressPanel1";
  } 
  public void setUpConnections()
  {
  InstallProgressPanel1.addActionListener(new ActionListener()
  	{
  	     public void actionPerformed(ActionEvent evt)
  	     {
		 //Back button and close buttons are disabled during installation.
  	          backBtn.setEnabled(false);
		  closeBtn.setEnabled(false);
		  timer.start();
		  result=true;
  	     }
	    
  	});

  //<Begin_setUpConnections> 

      closeBtn_closeBtn_conn closeBtn_closeBtn_conn1 =  new closeBtn_closeBtn_conn();
      closeBtn.addActionListener(closeBtn_closeBtn_conn1);
      backBtn_backBtn_conn backBtn_backBtn_conn1 =  new backBtn_backBtn_conn();
      backBtn.addActionListener(backBtn_backBtn_conn1);
      nextBtn_nextBtn_conn nextBtn_nextBtn_conn1 =  new nextBtn_nextBtn_conn();
      nextBtn.addActionListener(nextBtn_nextBtn_conn1);
  
      //<End_setUpConnections>
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
  
    void showCard(boolean next) {

	CardLayout cl = (CardLayout)wizPanel.getLayout();
	if(next) {
	    // Setting installer to the next two panels
	    if(cardNames[currentCardIndex].equals("InstallPathPanel1")) {
		try {
		    if(standalone) {
			install = new InstallPwsNar(InstallPathPanel1.getNarFile(),InstallPathPanel1.getInstallPath());
		    }
		    else {
			install = new InstallPwsNar(pwsNarFile,installLocation);
		    }
		}
		catch(ZipException e) {
		    OptionDialogInformer1.setMessage("Check if the nar file exists and format is proper");
		    OptionDialogInformer1.setDetailedMessage(PureUtils.getCorrectErrorMsg(e.getMessage(),e,"PWSInstallerApp.showStatus"));
		    OptionDialogInformer1.showOptionDialog();
		    result=false;
		    return;
		}
		catch(IOException e) {
		    OptionDialogInformer1.setMessage("Check if the nar file exists and format is proper");
		    OptionDialogInformer1.setDetailedMessage(PureUtils.getCorrectErrorMsg(e.getMessage(),e,"PWSInstallerApp.showStatus"));
		    OptionDialogInformer1.showOptionDialog();
		    result=false;
		    return;
		}
		catch(RuntimeException e) {
		    OptionDialogInformer1.setMessage(e.getMessage());
		    OptionDialogInformer1.setDetailedMessage(PureUtils.getCorrectErrorMsg(e.getMessage(),e,"PWSInstallerApp.showStatus"));
		    OptionDialogInformer1.showOptionDialog();
		    result=false;
		    return;
		}
		install.printApplicationNameAndVersion();

		if(install.checkIfSomeApplicationInstalled()) {
		    OptionDialogInformer1.setMessage("A version of Studio application is already installed , uninstall " 
						     + "and install the new version of application ...");
		    OptionDialogInformer1.setDetailedMessage("");
		    OptionDialogInformer1.showOptionDialog();
		    isInstalled=true;
		    return;
		}
		SwingUtilities.invokeLater(new SetupUIThread());
	    }
            	
	    currentCardIndex ++;

	    if(currentCardIndex == (cardNames.length -1)) {
		nextBtn.setEnabled(false);
	    }
	    else {
		nextBtn.setEnabled(true);
	    }
	    backBtn.setEnabled(true);	

	}
	else {
	    currentCardIndex --;
	    if(cardNames[currentCardIndex].equals("InstallPathPanel1")) {
		wizTitleLabel.setText("Studio NAR installer wizard");
	    }
	    if(currentCardIndex == 0) {
		backBtn.setEnabled(false);
	    }
	    else {
		backBtn.setEnabled(true);
	    }
	    nextBtn.setEnabled(true);
	}
	cl.show(wizPanel,cardNames[currentCardIndex]);
    }


    // Starting the in a separte thread
    // so as to give a better UI experiance
    class SetupUIThread implements Runnable {
	public void run() {
	    Cursor defCur = PWSInstallerApp.this.getCursor();
	    PWSInstallerApp.this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
	    wizTitleLabel.setText("Welcome to installation of " + install.getApplicationNameVersion());
	    ReadmePanel1.setInstaller(install);
	    InstallProgressPanel1.setInstallPath(InstallPathPanel1.getInstallPath());
	    InstallProgressPanel1.setInstaller(install);
	    PWSInstallerApp.this.setCursor(defCur);
	}
    }


//<Begin__class_backBtn_backBtn_conn>

 class backBtn_backBtn_conn implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_backBtn_backBtn_conn>

     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
	 showCard(false);
     }
//<UserCode_End_Connection_backBtn_backBtn_conn>
 }//<End__class_backBtn_backBtn_conn>
//<Begin__class_nextBtn_nextBtn_conn>

 class nextBtn_nextBtn_conn implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_nextBtn_nextBtn_conn>

     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
	 
	 showCard(true);
     }
//<UserCode_End_Connection_nextBtn_nextBtn_conn>
 }//<End__class_nextBtn_nextBtn_conn>

 


//<Begin__class_closeBtn_closeBtn_conn>

 class closeBtn_closeBtn_conn implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_closeBtn_closeBtn_conn>

     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
     	if(standalone)
     	{
	          System.exit(0);
     	}

     }
//<UserCode_End_Connection_closeBtn_closeBtn_conn>
 }//<End__class_closeBtn_closeBtn_conn>

	void addActionListener(ActionListener alis)
	{
	     closeBtn.addActionListener(alis);
	     
	     
	  
	}
	
	JPanel getTop()
	{
	     return Top;
	}
    boolean getResult(){
	if(install==null){
	    return false;
	}
	else if(install.cancelled()){
	    return false;
	}
	else if(!result){
	    return false;
	}

	return true;
    }
    
    boolean canProceed(){
	if(install==null){
	    return false;
	}
	else if(install.cancelled()){
	    return false;
	}
	else if(isInstalled){
	    return false;
	}
	return true;
    }

    public String[] getInstalledNarsList(){
	return install.getInstalledNarsList();
    }


    //Check whether the installation process is going on
    public boolean isProcessing(){
	int current=install.getCurrent();
	int total=install.getLengthOfTask();
	if(current>1&&current<total){
	    return true;
	}
	return false;
    }
}



















