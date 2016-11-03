//$Id: PreviewPanel.java,v 1.1 2006/08/29 13:57:01 build Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.provisioning.ui;



import java.text.MessageFormat;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import com.adventnet.nms.util.NmsUtil;



public class PreviewPanel extends JFrame 
{
    //<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "ProvisioningClientResources";
        public static  Font  locf=null;

	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	javax.swing.JTextArea JTextArea1 = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JPanel JPanel4 = null;
	javax.swing.JCheckBox JCheckBox1 = null;
	javax.swing.JPanel JPanel5 = null;
	javax.swing.JButton JButton1 = null;
	javax.swing.JButton JButton2 = null;
	javax.swing.JButton JButton4 = null;
	javax.swing.JButton JButton3 = null;
	//<End_Variable_Declarations>

           String text = "Description";
          JTextArea descriptionTextArea = new JTextArea();
          JScrollPane jsp = new JScrollPane(descriptionTextArea);
           JDialog dialog;
          boolean firstTime = true;

    public PreviewPanel()
  {
        //<Begin_PreviewPanel>
    pack();
  
    //<End_PreviewPanel>
}

    public PreviewPanel(java.applet.Applet applet)
  {
        //<Begin_PreviewPanel_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_PreviewPanel_java.applet.Applet>
	}

   private void initialize()
   {
	 JOptionPane option = new JOptionPane();
	 dialog = option.createDialog(PreviewPanel.this,ProvClientUtils.getString("Description"));
	dialog.setModal(false);
	dialog.getContentPane().removeAll();
               dialog.setSize(500,120);
	 dialog.getContentPane().add(jsp);
	 dialog.addWindowListener(new WindowAdapter()
	   {
	    public void windowClosing(WindowEvent we)
	    {
	     dialog.dispose();
	    }
	  });
     }

    public void showDescription()
    {
	 if(firstTime)
	{
		Dimension dd= getSize();
	              Dimension prd=dialog.getSize();
	 	Point pd= getLocationOnScreen();
		int x=(int)(pd.getX()+(dd.getWidth()-prd.getWidth())/2);
		int y=(int)(pd.getY()+(dd.getHeight()-prd.getHeight())/2);
		dialog.setLocation(x,y);
		firstTime = false;
	}
	 dialog.show();
    }
     
    public void setText(String text)
   {
	descriptionTextArea.setText(text);
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
    public void init( )
  {
        //<Begin_init> 
        if (initialized == true) return; 
        this.setSize(getPreferredSize().width+657,getPreferredSize().height+520); 
          setTitle(ProvClientUtils.getString("PreviewPanel"));
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
          showStatus(ProvClientUtils.getString("Error in init method"),ex); 
        } 
        // let us set the initialzed variable to true so  
        // we dont initialize again even if init is called 
        initialized = true; 

  
         //<End_init>
        initialize();
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
            if (input.equals("RESOURCE_PROPERTIES")) value = "ProvisioningClientResources"; 
            }
        return value;

  
           //<End_getParameter_String>
    } 
    public void setUpProperties()
  {
        //<Begin_setUpProperties> 

          try
          {
            JScrollPane1.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.LineBorder(new Color(-16777216),1),ProvClientUtils.getString("Status"),0,0,locf,new Color(-10066279)));

          }
          catch(Exception ex)
          {
             showStatus(ProvClientUtils.getString("Exception while setting properties for bean"),ex); 
          }

          try
          {
            JTextArea1.setWrapStyleWord(true);
            JTextArea1.setEditable(false);
            JTextArea1.setEnabled(false);
            JTextArea1.setOpaque(false);
          }
          catch(Exception ex)
          {
             showStatus(ProvClientUtils.getString("Exception while setting properties for bean"),ex); 
          }

          try
          {
            JCheckBox1.setFont(locf);
            JCheckBox1.setHorizontalTextPosition(4);
            JCheckBox1.setText(ProvClientUtils.getString("Show Debug Information"));
          }
          catch(Exception ex)
          {
             showStatus(ProvClientUtils.getString("Exception while setting properties for bean"),ex); 
          }

          try
          {
            JButton1.setFont(locf);
            JButton1.setHorizontalTextPosition(4);
            JButton1.setText(ProvClientUtils.getString("Back"));
           // JButton1.setPreferredSize(new Dimension(61,27));
          }
          catch(Exception ex)
          {
             showStatus(ProvClientUtils.getString("Exception while setting properties for bean"),ex); 
          }

          try
          {
            JButton2.setFont(locf);
            JButton2.setHorizontalTextPosition(4);
            JButton2.setText(ProvClientUtils.getString("Next"));
          }
          catch(Exception ex)
          {
             showStatus(ProvClientUtils.getString("Exception while setting properties for bean"),ex); 
          }

          try
          {
            JButton4.setFont(locf);
            JButton4.setHorizontalTextPosition(4);
            JButton4.setActionCommand("Show");
            JButton4.setText(ProvClientUtils.getString("Description"));
          }
          catch(Exception ex)
          {
             showStatus(ProvClientUtils.getString("Exception while setting properties for bean"),ex); 
          }

          try
          {
            JButton3.setFont(locf);
            JButton3.setHorizontalTextPosition(4);
            JButton3.setText(ProvClientUtils.getString("Close"));
          }
          catch(Exception ex)
          {
             showStatus(ProvClientUtils.getString("Exception while setting properties for bean"),ex); 
          }
		JPanel5.setPreferredSize(new Dimension(JPanel5.getPreferredSize().width+38,JPanel5.getPreferredSize().height+0));
		JPanel4.setPreferredSize(new Dimension(JPanel4.getPreferredSize().width+421,JPanel4.getPreferredSize().height+10));
		JPanel3.setPreferredSize(new Dimension(JPanel3.getPreferredSize().width+10,JPanel3.getPreferredSize().height+10));
		JScrollPane1.setPreferredSize(new Dimension(JScrollPane1.getPreferredSize().width+568,JScrollPane1.getPreferredSize().height+2));
		JPanel2.setPreferredSize(new Dimension(JPanel2.getPreferredSize().width+15,JPanel2.getPreferredSize().height+10));
		JPanel1.setPreferredSize(new Dimension(JPanel1.getPreferredSize().width+636,JPanel1.getPreferredSize().height+264));

  
          //<End_setUpProperties>
    } 
    public void initVariables()
  {
        //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        JPanel1= new javax.swing.JPanel();
        JPanel2= new javax.swing.JPanel();
        JScrollPane1= new javax.swing.JScrollPane();
        JTextArea1= new javax.swing.JTextArea();
        JPanel3= new javax.swing.JPanel();
        JPanel4= new javax.swing.JPanel();
        JCheckBox1= new javax.swing.JCheckBox();
        JPanel5= new javax.swing.JPanel();
        JButton1= new javax.swing.JButton();
        JButton2= new javax.swing.JButton();
        JButton4= new javax.swing.JButton();
        JButton3= new javax.swing.JButton();
	//<End_initVariables

    } 
    public void setUpGUI(Container container)
  {
        //<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));
Top.add(JPanel1,BorderLayout.CENTER);
JPanel1.setLayout(new BorderLayout(5,5));
Top.add(JPanel2,BorderLayout.SOUTH);
JPanel2.setLayout(new BorderLayout(5,5));
JPanel2.add(JScrollPane1,BorderLayout.NORTH);
JScrollPane1.getViewport().add(JTextArea1);
JPanel2.add(JPanel3,BorderLayout.CENTER);
JPanel3.setLayout(new BorderLayout(5,5));
JPanel3.add(JPanel4,BorderLayout.NORTH);
JPanel4.setLayout(new BorderLayout(5,5));
JPanel4.add(JCheckBox1,BorderLayout.WEST);
JPanel3.add(JPanel5,BorderLayout.CENTER);
JPanel5.setLayout(new FlowLayout(1,5,5));
JPanel5.add(JButton1);
JPanel5.add(JButton2);
JPanel5.add(JButton4);
JPanel5.add(JButton3);

  
//<End_setUpGUI_Container>
    } 
    public void setUpConnections()
  {
        //<Begin_setUpConnections> 

      java.awt.event.ActionListener JButton3_JButton3_conn1 =  new java.awt.event.ActionListener()
  {

     //<TOP_PART> - Please do not remove this comment or write any custom code above this

     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  dispose();
     }
};
      JButton3.addActionListener(JButton3_JButton3_conn1);
      JButton4_JButton4_conn JButton4_JButton4_conn1 =  new JButton4_JButton4_conn();
      JButton4.addActionListener(JButton4_JButton4_conn1);
  
      //<End_setUpConnections>
    } 




    public void showStatus(String message)
  {
        //<Begin_showStatus_String>
     System.out.println(message);
     //<End_showStatus_String>
    }
    public void showStatus(String message,Exception ex)
  {
        //<Begin_showStatus_String_Exception>
     System.out.println(message);
     ex.printStackTrace();
     //<End_showStatus_String_Exception>
    }

    public void showTemplate(JPanel panel)
    {
        JPanel1.add(new JScrollPane(panel),"Center");
        Dimension d = panel.getPreferredSize();
        int height = (int)d.getHeight();
        int width = (int)d.getWidth();
        setSize(width+50,height+150);
        show();
    }


 


//<Begin__class_JButton4_JButton4_conn>

 class JButton4_JButton4_conn implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART> - Please do not remove this comment or write any custom code above this

     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  showDescription();
     }

}

//<End__class_JButton4_JButton4_conn>
}


















