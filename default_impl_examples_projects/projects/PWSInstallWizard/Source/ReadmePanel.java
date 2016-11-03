
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// Kindly refer to tcats_edit_source_files.html file under help/source_generation directory for details

/*$Id: ReadmePanel.java,v 1.3 2008/05/08 07:54:43 wesley Exp $*/
 
//<Begin_Version>
//version$13
//<End_Version>
package com.adventnet.studio.framework.installer;




import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.text.html.*;




public class ReadmePanel extends JPanel implements ActionListener
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JLabel readmeLabel = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	javax.swing.JEditorPane readmeArea = null;
	javax.swing.JPanel JPanel1 = null;
	/*The entries of select database option in the
	Deployment wizard has been commented here**/
	//javax.swing.JLabel dbLabel = null;
	//javax.swing.JComboBox dbCombo = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>

	private InstallPwsNar installer;

  	void setInstaller(InstallPwsNar arg)
  	{
  	     installer = arg;
	     //dbCombo.removeActionListener(this);
	     //((DefaultComboBoxModel)dbCombo.getModel()).removeAllElements();
	     //dbCombo.addActionListener(this);
	     try
		 {
		     URL readmeURL = installer.getReadMeURL();
		     if ( readmeURL != null ) {
			 readmeArea.setPage(readmeURL);			 
		     } // end of if ()
		     
		     //String[] dbs = installer.getDBsForAppliction();
		     /*for(int i = 0 ; i < dbs.length ; i ++)
			 {
			     dbCombo.addItem(dbs[i]);
			 }*/
		 }
	     catch(Exception e)
		 {

		     System.out.println(getClientProperty("charset"));
		     
		     e.printStackTrace();
		 }
	}	
    

  public ReadmePanel()
  {
    //<Begin_ReadmePanel>
    this.init();
  
    //<End_ReadmePanel>
  }

  public ReadmePanel(java.applet.Applet applet)
  {
    //<Begin_ReadmePanel_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_ReadmePanel_java.applet.Applet>
  }

  

    public void init()
  {
        //<Begin_init> 
        if (initialized == true) return; 
        setPreferredSize(new Dimension(getPreferredSize().width+722,getPreferredSize().height+477)); 
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
            readmeLabel.setHorizontalAlignment(2);
            readmeLabel.setFont(new Font("SansSerif",0,12));
            readmeLabel.setForeground(new Color(-16777216));
            readmeLabel.setHorizontalTextPosition(4);
            readmeLabel.setText("Please read the following information");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+readmeLabel,ex); 
          }

//<UserCode_Begin_Bean_readmeLabel>

//<UserCode_End_Bean_readmeLabel>

          try
          {
            readmeArea.setText("A read me ...");
            readmeArea.setEditable(false);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+readmeArea,ex); 
          }

//<UserCode_Begin_Bean_readmeArea>


//<UserCode_End_Bean_readmeArea>

          /*try
          {
            dbLabel.setHorizontalAlignment(2);
            dbLabel.setFont(new Font("SansSerif",0,12));
            dbLabel.setForeground(new Color(-16777216));
            dbLabel.setHorizontalTextPosition(4);
            dbLabel.setText("Select Application Database");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+dbLabel,ex); 
          }*/

//<UserCode_Begin_Bean_dbLabel>

//<UserCode_End_Bean_dbLabel>

          /*try
          {
            dbCombo.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+dbCombo,ex); 
          }*/

//<UserCode_Begin_Bean_dbCombo>

//<UserCode_End_Bean_dbCombo>
		JPanel1.setPreferredSize(new Dimension(JPanel1.getPreferredSize().width+376,JPanel1.getPreferredSize().height+18));
		JScrollPane1.setPreferredSize(new Dimension(JScrollPane1.getPreferredSize().width+0,JScrollPane1.getPreferredSize().height+9));

  
          //<End_setUpProperties>
  } 
  public void initVariables()
  {
        //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        readmeLabel= new javax.swing.JLabel();
        JScrollPane1= new javax.swing.JScrollPane();
        readmeArea= new javax.swing.JEditorPane();
        JPanel1= new javax.swing.JPanel();
        //dbLabel= new javax.swing.JLabel();
        //dbCombo= new javax.swing.JComboBox();

  
        //<End_initVariables>
  } 
   
   
   
   
  public void setUpGUI(Container container)
  {
//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));
Top.add(readmeLabel,BorderLayout.NORTH);
Top.add(JScrollPane1,BorderLayout.CENTER);
JScrollPane1.getViewport().add(readmeArea);
Top.add(JPanel1,BorderLayout.SOUTH);
JPanel1.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
//JPanel1.add(dbLabel,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.1,0.0,cons.WEST,cons.NONE,inset,0,0);
//JPanel1.add(dbCombo,cons);

  
//<End_setUpGUI_Container>
  } 
  public void setUpConnections()
  {
  //<Begin_setUpConnections> 

  
  //<End_setUpConnections>
      //dbCombo.addActionListener(this);
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
	//installer.setApplicationDatabase((String)dbCombo.getSelectedItem());
    }
  
}





