
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// Kindly refer to tcats_edit_source_files.html file under help/source_generation directory for details

/*$Id: UninstallProgressPanel.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $*/

//<Begin_Version>
//version$6
//<End_Version>


package com.adventnet.studio.framework.installer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import com.adventnet.builder.utils.general.PureUtils;



public class UninstallProgressPanel extends JPanel implements ActionListener
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel centerPanel = null;
	javax.swing.JLabel progressLabel = null;
	javax.swing.JProgressBar uninstallProgress = null;
	javax.swing.JButton uninstButton = null;
	com.adventnet.beans.utilbeans.OptionDialogInformer OptionDialogInformer1 = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>

    PWSUninstallWizard uninstaller;
    private Timer timer;

    void setUninstaller(PWSUninstallWizard uninstallerarg)
    {
	uninstaller = uninstallerarg;
    }



  public UninstallProgressPanel()
  {
    //<Begin_UninstallProgressPanel>
    this.init();
  
    //<End_UninstallProgressPanel>
  }

  public UninstallProgressPanel(java.applet.Applet applet)
  {
    //<Begin_UninstallProgressPanel_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_UninstallProgressPanel_java.applet.Applet>
  }



    public void init()
  {
        //<Begin_init> 
        if (initialized) return; 
        setPreferredSize(new Dimension(getPreferredSize().width+647,getPreferredSize().height+456)); 
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
		    uninstallProgress.setValue(uninstaller.getCurrent());
		    progressLabel.setText(uninstaller.getMessage());
		    if(uninstaller.done())
			{
			    progressLabel.setText("Finished uninstallation ");
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
            uninstallProgress.setFont(new Font("SansSerif",0,12));
            uninstallProgress.setStringPainted(true);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+uninstallProgress,ex); 
          }

//<UserCode_Begin_Bean_uninstallProgress>

//<UserCode_End_Bean_uninstallProgress>

          try
          {
            uninstButton.setFont(new Font("SansSerif",0,12));
            uninstButton.setHorizontalTextPosition(4);
            uninstButton.setText("Uninstall");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+uninstButton,ex); 
          }

//<UserCode_Begin_Bean_uninstButton>

//<UserCode_End_Bean_uninstButton>

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

  
          //<End_setUpProperties>
  }
  public void initVariables()
  {
        //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        centerPanel= new javax.swing.JPanel();
        progressLabel= new javax.swing.JLabel();
        uninstallProgress= new javax.swing.JProgressBar();
        uninstButton= new javax.swing.JButton();
        OptionDialogInformer1= new com.adventnet.beans.utilbeans.OptionDialogInformer();

  
        //<End_initVariables>
  }
  public void setUpGUI(Container container)
  {
//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));
Top.add(centerPanel,BorderLayout.CENTER);
centerPanel.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
centerPanel.add(progressLabel,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,1,0,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
centerPanel.add(uninstallProgress,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,2,1,1,0.0,0.0,cons.EAST,cons.NONE,inset,0,0);
centerPanel.add(uninstButton,cons);

  
//<End_setUpGUI_Container>
  }
  public void setUpConnections()
  {
  //<Begin_setUpConnections> 

  
  //<End_setUpConnections>
      uninstButton.addActionListener(this);
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
      uninstallProgress.setMaximum(uninstaller.getLengthOfTask());
      uninstButton.setEnabled(false);
      Thread uninstallThread = new Thread()
	  {
	      public void run()
	      {
		  try
		      {
			  uninstaller.uninstall();
		      }
		  catch(Exception e)
		      {
			  OptionDialogInformer1.setMessage(e.getMessage());
			  OptionDialogInformer1.setDetailedMessage(PureUtils.getCorrectErrorMsg(e.getMessage(),e,
												"UninstallProgressPanel.actionPerformed"));
			  OptionDialogInformer1.showOptionDialog();
		      }
	      }
	  };
      uninstallThread.start();
      timer.start();
    }
  public void addActionListener(ActionListener alis)
  {
       uninstButton.addActionListener(alis);
  }
}





