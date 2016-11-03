
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// Kindly refer to tcats_edit_source_files.html file under help/source_generation directory for details

/*$Id: PWSUninstallerApp.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $*/

 
//<Begin_Version>
//version$19
//<End_Version>

package com.adventnet.studio.framework.installer;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import com.adventnet.builder.utils.general.PureUtils;



public class PWSUninstallerApp extends JFrame implements ActionListener
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private static final String param[] = {};
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JLabel imgLabel = null;
	javax.swing.JPanel bottomPanel = null;
	javax.swing.JPanel buttonPanel = null;
	javax.swing.JButton backBtn = null;
	javax.swing.JButton nextBtn = null;
	javax.swing.JButton closeBtn = null;
	javax.swing.JSeparator buttonSeparator = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JPanel uninstPanel = null;
	UninstallPathPanel UninstallPathPanel1 = null;
	UninstallProgressPanel UninstallProgressPanel1 = null;
	javax.swing.JPanel tiltlePanel = null;
	javax.swing.JSeparator titleSeparator = null;
	javax.swing.JLabel titleLabel = null;
	com.adventnet.beans.utilbeans.OptionDialogInformer OptionDialogInformer1 = null;
	//<End_Variable_Declarations>
        private Timer timer=null;
	String[] cardNames = new String[2];
	int currentCardIndex = 0;
	boolean standalone = true;
	String applicationName;
	String uninstallLocation;
        private boolean result=false;
        private PWSUninstallWizard uninstall = null;
	void setApplicationName(String arg)
	{
	     applicationName = arg;
	}
	
	void setUninstallLocation(String arg)
	{
	     uninstallLocation = arg;
	}
	
	void setStandAlone(boolean flag)
	{
	     standalone = flag;
	     if(!standalone)
	     {
	          nextBtn.doClick();
	          cardNames = new String[1];
	          cardNames[0] = "UninstallProgressPanel1";
	          currentCardIndex = 0;
	          buttonPanel.remove(nextBtn);
	          buttonPanel.remove(backBtn);
	          buttonPanel.validate();
	     }
	}
	
	JPanel getTop()
	{
	     return Top;
	}


  public PWSUninstallerApp()
  {
    //<Begin_PWSUninstallerApp>
    pack();
  
    //<End_PWSUninstallerApp>
  }

  public PWSUninstallerApp(java.applet.Applet applet)
  {
    //<Begin_PWSUninstallerApp_java.applet.Applet>
    this.applet = applet;
    pack();
  
    //<End_PWSUninstallerApp_java.applet.Applet>
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
        this.setSize(getPreferredSize().width+661,getPreferredSize().height+456); 
          setTitle("PWSUninstallerApp");
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
	PWSUninstallerApp frame = new PWSUninstallerApp();
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
            imgLabel.setHorizontalAlignment(2);
            imgLabel.setFont(new Font("SansSerif",0,12));
            imgLabel.setForeground(new Color(-16777216));
            imgLabel.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+imgLabel,ex); 
          }

//<UserCode_Begin_Bean_imgLabel>

	  imgLabel.setIcon(com.adventnet.apiutils.Utility.findImage("/com/adventnet/studio/framework/installer/images/pwsinstaller_wiz.png",null));
//<UserCode_End_Bean_imgLabel>

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
            titleLabel.setHorizontalAlignment(2);
            titleLabel.setFont(new Font("SansSerif",0,12));
            titleLabel.setForeground(new Color(-16777216));
            titleLabel.setHorizontalTextPosition(4);
            titleLabel.setText("Studio NAR Uninstallation Wizard");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+titleLabel,ex); 
          }

//<UserCode_Begin_Bean_titleLabel>

//<UserCode_End_Bean_titleLabel>

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

//<UserCode_End_Bean_OptionDialogInformer1>
		tiltlePanel.setPreferredSize(new Dimension(tiltlePanel.getPreferredSize().width+10,tiltlePanel.getPreferredSize().height+10));
		JPanel1.setPreferredSize(new Dimension(JPanel1.getPreferredSize().width+10,JPanel1.getPreferredSize().height+10));
		bottomPanel.setPreferredSize(new Dimension(bottomPanel.getPreferredSize().width+10,bottomPanel.getPreferredSize().height+10));
		imgLabel.setPreferredSize(new Dimension(imgLabel.getPreferredSize().width+0,imgLabel.getPreferredSize().height+360));

  
          //<End_setUpProperties>
  } 
  public void initVariables()
  {
        //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        imgLabel= new javax.swing.JLabel();
        bottomPanel= new javax.swing.JPanel();
        buttonPanel= new javax.swing.JPanel();
        backBtn= new javax.swing.JButton();
        nextBtn= new javax.swing.JButton();
        closeBtn= new javax.swing.JButton();
        buttonSeparator= new javax.swing.JSeparator();
        JPanel1= new javax.swing.JPanel();
        uninstPanel= new javax.swing.JPanel();
        UninstallPathPanel1= new UninstallPathPanel(applet);
        UninstallProgressPanel1= new UninstallProgressPanel(applet);
        tiltlePanel= new javax.swing.JPanel();
        titleSeparator= new javax.swing.JSeparator();
        titleLabel= new javax.swing.JLabel();
        OptionDialogInformer1= new com.adventnet.beans.utilbeans.OptionDialogInformer();

  
        //<End_initVariables>
	
	//Timer will enable the close button after uninstallation
	//completed
	timer = new Timer(10, new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
		    if(uninstall.done()){
			closeBtn.setEnabled(true);
			timer.stop();
		    }       
		}
	    });
	cardNames[0] = "UninstallPathPanel1";
	cardNames[1] = "UninstallProgressPanel1";
  } 
  public void setUpGUI(Container container)
  {
//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));
Top.add(imgLabel,BorderLayout.WEST);
Top.add(bottomPanel,BorderLayout.SOUTH);
bottomPanel.setLayout(new BorderLayout(5,5));
bottomPanel.add(buttonPanel,BorderLayout.CENTER);
buttonPanel.setLayout(new FlowLayout(2,5,5));
buttonPanel.add(backBtn);
buttonPanel.add(nextBtn);
buttonPanel.add(closeBtn);
bottomPanel.add(buttonSeparator,BorderLayout.NORTH);
Top.add(JPanel1,BorderLayout.CENTER);
JPanel1.setLayout(new BorderLayout(5,5));
JPanel1.add(uninstPanel,BorderLayout.CENTER);
uninstPanel.setLayout(new CardLayout(5,5));
uninstPanel.add(UninstallPathPanel1,"UninstallPathPanel1");
uninstPanel.add(UninstallProgressPanel1,"UninstallProgressPanel1");
JPanel1.add(tiltlePanel,BorderLayout.NORTH);
tiltlePanel.setLayout(new BorderLayout(5,5));
tiltlePanel.add(titleSeparator,BorderLayout.SOUTH);
tiltlePanel.add(titleLabel,BorderLayout.CENTER);

  
//<End_setUpGUI_Container>
  } 
  public void setUpConnections()
  {
  nextBtn.addActionListener(this);
  backBtn.addActionListener(this);
  closeBtn.addActionListener(this);

  //Actionperformed method for uninstall button
  //Close button is disabled while uninstallation
  UninstallProgressPanel1.addActionListener(new ActionListener(){
	  public void actionPerformed(ActionEvent evt){
	      closeBtn.setEnabled(false);
	      timer.start();
	      result=true;
	  }
  });
  //<Begin_setUpConnections> 

  
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
	CardLayout cl = (CardLayout)uninstPanel.getLayout();
	if(next) {
	    if(cardNames[currentCardIndex].equals("UninstallPathPanel1")) {


		try {

		    if(standalone) {
			uninstall = new PWSUninstallWizard(UninstallPathPanel1.getApplicationNameVersion(),UninstallPathPanel1.getUninstallLocation());
		    }
		    else {
			uninstall = new PWSUninstallWizard(applicationName,uninstallLocation);
		    }
		}
		catch(IOException e) {
		    OptionDialogInformer1.setMessage("Please provide proper uninstall location");
		    OptionDialogInformer1.setDetailedMessage(PureUtils.getCorrectErrorMsg(e.getMessage(),e,"PWSUninstallerApp.showCard"));
		    OptionDialogInformer1.showOptionDialog();
		    return;
		}
		catch(Exception e) {
		    OptionDialogInformer1.setMessage(e.getMessage());
		    OptionDialogInformer1.setDetailedMessage(PureUtils.getCorrectErrorMsg(e.getMessage(),e,"PWSUninstallerApp.showCard"));
		    OptionDialogInformer1.showOptionDialog();
		    return;
		}
			
		UninstallProgressPanel1.setUninstaller(uninstall);
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
		titleLabel.setText("Studio NAR Uninstallation wizard");
	    }
	    if(currentCardIndex == 0) {
		backBtn.setEnabled(false);
	    }
	    else {
		backBtn.setEnabled(true);
	    }
	    nextBtn.setEnabled(true);
	}
	cl.show(uninstPanel,cardNames[currentCardIndex]);
    }
 

    public void actionPerformed(ActionEvent evt)
    {
	if(evt.getSource() == nextBtn) {
	    showCard(true);
	}
	else if(evt.getSource() == backBtn) {
	    showCard(false);
	}
	else if(evt.getSource() == closeBtn) {
	    if(standalone)
		{
		    System.exit(0);
		}
	}
    }  
	
    void addActionListener(ActionListener alis) {
	closeBtn.addActionListener(alis);
    }

    public boolean getResult(){
	return result;
    }
    
    public String[] getUnInstalledNarsList(){
	return uninstall.getUnInstalledNarsList();
    }
    
    //Returns true if uninstallation in progress
    public boolean isProcessing(){
	int current=uninstall.getCurrent();
	int total=uninstall.getLengthOfTask();
	if(current>1&&current<total){
	    return true;
	}
	return false;
    }
}



