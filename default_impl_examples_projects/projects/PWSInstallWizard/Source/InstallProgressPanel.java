
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// Kindly refer to tcats_edit_source_files.html file under help/source_generation directory for details

/*$Id: InstallProgressPanel.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $*/

//<Begin_Version>
//version$10
//<End_Version>

package com.adventnet.studio.framework.installer;




import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import com.adventnet.builder.utils.general.PureUtils;



public class InstallProgressPanel extends JPanel  implements ActionListener
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JLabel progressLabel = null;
	javax.swing.JProgressBar installProgressBar = null;
	javax.swing.JButton installBtn = null;
	com.adventnet.beans.utilbeans.OptionDialogInformer OptionDialogInformer1 = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>

	private InstallPwsNar installer;
	private Timer timer;

    String installPath;


  	void setInstaller(InstallPwsNar arg)
  	{
  	     installer = arg;
  	}

    void setInstallPath(String arg)
    {
	installPath = arg;
    }


    public void init()
  {
        //<Begin_init> 
        if (initialized) return; 
        setPreferredSize(new Dimension(getPreferredSize().width+573,getPreferredSize().height+438)); 
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
          showStatus("Error in init method",ex); 
        } 
        // let us set the initialized variable to true so  
        // we dont initialize again even if init is called 
        initialized = true; 

  
        //<End_init>
           timer = new Timer(10, new ActionListener()
           {
           	 public void actionPerformed(ActionEvent evt)
           	 {
           	      installProgressBar.setValue(installer.getCurrent());
           	      progressLabel.setText(installer.getMessage());
           	      if(installer.done())
           	      {
			  progressLabel.setText("Finished installation ");
			  timer.stop();
           	      }
		      else if(installer.cancelled())
			  {
			      installProgressBar.setValue(0);
			      progressLabel.setText("Installation cancelled");
			      timer.stop();
			  }
           	 }
           });
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
  public void setUpProperties()
  {
  //<Begin_setUpProperties> 

          try
          {
            progressLabel.setHorizontalAlignment(2);
            progressLabel.setFont(new Font("SansSerif",0,12));
            progressLabel.setForeground(new Color(-16777216));
            progressLabel.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+progressLabel,ex); 
          }

//<UserCode_Begin_Bean_progressLabel>

//<UserCode_End_Bean_progressLabel>

          try
          {
            installProgressBar.setFont(new Font("SansSerif",0,12));
            installProgressBar.setStringPainted(true);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+installProgressBar,ex); 
          }

//<UserCode_Begin_Bean_installProgressBar>

//<UserCode_End_Bean_installProgressBar>

          try
          {
            installBtn.setFont(new Font("SansSerif",0,12));
            installBtn.setHorizontalTextPosition(4);
            installBtn.setText("Install");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+installBtn,ex); 
          }

//<UserCode_Begin_Bean_installBtn>

//<UserCode_End_Bean_installBtn>

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
	  OptionDialogInformer1.setComponent(getTopLevelAncestor());
//<UserCode_End_Bean_OptionDialogInformer1>

  
          //<End_setUpProperties>
  }
  public void initVariables()
  {
        //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        progressLabel= new javax.swing.JLabel();
        installProgressBar= new javax.swing.JProgressBar();
        installBtn= new javax.swing.JButton();
        OptionDialogInformer1= new com.adventnet.beans.utilbeans.OptionDialogInformer();

  
        //<End_initVariables>
  }




  public void setUpGUI(Container container)
  {
//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new GridBagLayout());
inset = new Insets(0,5,5,0);
setConstraints(0,0,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
Top.add(progressLabel,cons);
inset = new Insets(5,0,0,5);
setConstraints(0,1,0,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
Top.add(installProgressBar,cons);
inset = new Insets(20,0,0,5);
setConstraints(1,2,1,1,0.0,0.0,cons.EAST,cons.NONE,inset,0,0);
Top.add(installBtn,cons);

  
//<End_setUpGUI_Container>
  }
  public void setUpConnections()
  {
  //<Begin_setUpConnections> 

  
  //<End_setUpConnections>
      installBtn.addActionListener(this);
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






  public InstallProgressPanel()
  {
    //<Begin_InstallProgressPanel>
    this.init();
  
    //<End_InstallProgressPanel>
  }

  public InstallProgressPanel(java.applet.Applet applet)
  {
    //<Begin_InstallProgressPanel_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_InstallProgressPanel_java.applet.Applet>
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

  public void actionPerformed(ActionEvent evt)
  {
      installProgressBar.setMaximum(installer.getLengthOfTask());
      installBtn.setEnabled(false);
      Thread installThread = new Thread()
	  {
	      public void run()
	      {
		  try
		      {
	  		  installer.go();
		      }
		  catch(Exception e)
		      {
			  installer.cancelInstall();
			  OptionDialogInformer1.setMessage(e.getMessage());
			  OptionDialogInformer1.setDetailedMessage(PureUtils.getCorrectErrorMsg(e.getMessage(),e,
												"InstallProgressPanel.actionPerformed"));
			  OptionDialogInformer1.showOptionDialog();
			  try
			      {
				  installer.writeUninstallInfo();
				  (new PWSUninstallWizard(installer.getApplicationNameVersion(),installPath)).uninstall();
			      }
			  catch(Exception exp)
			      {
				  OptionDialogInformer1.setMessage(exp.getMessage());
				  OptionDialogInformer1.setDetailedMessage(PureUtils.getCorrectErrorMsg(exp.getMessage(),exp,
												"InstallProgressPanel.actionPerformed"));
				  OptionDialogInformer1.showOptionDialog();
			      }
		      }
	      }
	  };
      installThread.start();
      timer.start();
  }

  public void addActionListener(ActionListener alis)
  {
       installBtn.addActionListener(alis);
  }
}


