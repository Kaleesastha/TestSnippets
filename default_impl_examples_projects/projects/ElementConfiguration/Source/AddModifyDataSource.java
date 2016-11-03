
//$Id: AddModifyDataSource.java,v 1.1 2006/08/29 13:56:51 build Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.config;


import javax.swing.border.*;
import java.util.*;
import java.net.URL;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import com.adventnet.management.config.*;

public class AddModifyDataSource extends JDialog implements ConfigResponseListener, HelpInterface
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "ElementConfigurationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JPanel detailsLabel = null;
	javax.swing.JPanel neLabel = null;
	javax.swing.JPanel ivLabel = null;
	javax.swing.JPanel uiLabel = null;
	javax.swing.JPanel JPanel4 = null;
	javax.swing.JPanel cardPanel = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JButton okButton = null;
	javax.swing.JButton cancelButton = null;
	javax.swing.JPanel JPanel5 = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JTextField nameText = null;
	javax.swing.JPanel JPanel6 = null;
	javax.swing.JPanel JPanel7 = null;
	javax.swing.JLabel JLabel2 = null;
	javax.swing.JPanel JPanel8 = null;
	javax.swing.JButton helpButton = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>











	public AddModifyDataSource()
  {
		//<Begin_AddModifyDataSource>
    pack();
  
    //<End_AddModifyDataSource>
	}

	public AddModifyDataSource(java.applet.Applet applet)
  {
		//<Begin_AddModifyDataSource_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_AddModifyDataSource_java.applet.Applet>
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


	public void start()
  { 

		//<Begin_start> 
       if(running)
 return;
 running=true;

  
       //<End_start>
	} 
	public void initVariables()
  { 

		//<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        JPanel1= new javax.swing.JPanel();
        JPanel3= new javax.swing.JPanel();
        detailsLabel= new javax.swing.JPanel();
        neLabel= new javax.swing.JPanel();
        ivLabel= new javax.swing.JPanel();
        uiLabel= new javax.swing.JPanel();
        JPanel4= new javax.swing.JPanel();
        cardPanel= new javax.swing.JPanel();
        JPanel2= new javax.swing.JPanel();
        okButton= new javax.swing.JButton();
        cancelButton= new javax.swing.JButton();
        JPanel5= new javax.swing.JPanel();
        JLabel1= new javax.swing.JLabel();
        nameText= new javax.swing.JTextField();
        JPanel6= new javax.swing.JPanel();
        JPanel7= new javax.swing.JPanel();
        JLabel2= new javax.swing.JLabel();
        JPanel8= new javax.swing.JPanel();
        helpButton= new javax.swing.JButton();

  
        //<End_initVariables>
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

	public void setUpGUI(Container container)
  { 

		//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(JPanel1,cons);
JPanel1.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.1,cons.CENTER,cons.VERTICAL,inset,0,0);
JPanel1.add(JPanel3,cons);
JPanel3.setLayout(new GridLayout(4,1,0,0));
JPanel3.add(detailsLabel);
detailsLabel.setLayout(new FlowLayout(1,5,5));
JPanel3.add(neLabel);
neLabel.setLayout(new FlowLayout(1,5,5));
JPanel3.add(ivLabel);
ivLabel.setLayout(new FlowLayout(1,5,5));
JPanel3.add(uiLabel);
uiLabel.setLayout(new FlowLayout(1,5,5));
inset = new Insets(5,5,5,5);
setConstraints(2,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel1.add(JPanel4,cons);
JPanel4.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel4.add(cardPanel,cons);
cardPanel.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel4.add(JPanel2,cons);
JPanel2.setLayout(new FlowLayout(2,5,5));
JPanel2.add(okButton);
JPanel2.add(cancelButton);
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel4.add(JPanel5,cons);
JPanel5.setLayout(new FlowLayout(1,8,8));
JPanel5.add(JLabel1);
JPanel5.add(nameText);
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel6,cons);
JPanel6.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel6.add(JPanel7,cons);
JPanel7.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(-1,-1,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel7.add(JLabel2,cons);
inset = new Insets(5,5,5,20);
setConstraints(1,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel6.add(JPanel8,cons);
JPanel8.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(-1,-1,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel8.add(helpButton,cons);

  
//<End_setUpGUI_Container>
	} 
	public void setUpProperties()
  { 

		//<Begin_setUpProperties> 

          try
          {
            JPanel3.setBorder(new javax.swing.border.BevelBorder(1));
            JPanel3.setBackground(new Color(-1));
            JPanel3.setOpaque(true);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel3,ex); 
          }

//<UserCode_Begin_Bean_JPanel3>

//<UserCode_End_Bean_JPanel3>

          try
          {
            detailsLabel.setBackground(new Color(-1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+detailsLabel,ex); 
          }

//<UserCode_Begin_Bean_detailsLabel>

//<UserCode_End_Bean_detailsLabel>

          try
          {
            neLabel.setBackground(new Color(-1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+neLabel,ex); 
          }

//<UserCode_Begin_Bean_neLabel>

//<UserCode_End_Bean_neLabel>

          try
          {
            ivLabel.setBackground(new Color(-1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+ivLabel,ex); 
          }

//<UserCode_Begin_Bean_ivLabel>

//<UserCode_End_Bean_ivLabel>

          try
          {
            uiLabel.setBackground(new Color(-1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+uiLabel,ex); 
          }

//<UserCode_Begin_Bean_uiLabel>

//<UserCode_End_Bean_uiLabel>

          try
          {
            JPanel4.setBackground(new Color(-3355444));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel4,ex); 
          }

//<UserCode_Begin_Bean_JPanel4>

//<UserCode_End_Bean_JPanel4>

          try
          {
            cardPanel.setBackground(new Color(-3355444));
            cardPanel.setBorder(new javax.swing.border.SoftBevelBorder(0));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+cardPanel,ex); 
          }

//<UserCode_Begin_Bean_cardPanel>

//<UserCode_End_Bean_cardPanel>

          try
          {
            okButton.setText(resourceBundle.getString("OK"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+okButton,ex); 
          }

//<UserCode_Begin_Bean_okButton>

//<UserCode_End_Bean_okButton>

          try
          {
            cancelButton.setText(resourceBundle.getString("Cancel"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+cancelButton,ex); 
          }

//<UserCode_Begin_Bean_cancelButton>

//<UserCode_End_Bean_cancelButton>

          try
          {
            JPanel5.setBorder(new javax.swing.border.LineBorder(new Color(-1),1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel5,ex); 
          }

//<UserCode_Begin_Bean_JPanel5>

//<UserCode_End_Bean_JPanel5>

          try
          {
            JLabel1.setForeground(new Color(-16764109));
            JLabel1.setText(resourceBundle.getString("Data Source Name"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel1,ex); 
          }

//<UserCode_Begin_Bean_JLabel1>

//<UserCode_End_Bean_JLabel1>

          try
          {
            JPanel6.setBorder(new javax.swing.border.EtchedBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel6,ex); 
          }

//<UserCode_Begin_Bean_JPanel6>

//<UserCode_End_Bean_JPanel6>

          try
          {
            JLabel2.setText(resourceBundle.getString("Data Source Configuration"));
            JLabel2.setForeground(new Color(-16764109));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel2,ex); 
          }

//<UserCode_Begin_Bean_JLabel2>

//<UserCode_End_Bean_JLabel2>

          try
          {
            helpButton.setPreferredSize(new Dimension(35,23));
            helpButton.setMaximumSize(new Dimension(35,23));
            helpButton.setMinimumSize(new Dimension(35,23));
            helpButton.setBackground(new Color(-1));
            helpButton.setFocusPainted(false);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+helpButton,ex); 
          }

//<UserCode_Begin_Bean_helpButton>

//<UserCode_End_Bean_helpButton>
		nameText.setPreferredSize(new Dimension(nameText.getPreferredSize().width+291,nameText.getPreferredSize().height+0));
		JLabel1.setPreferredSize(new Dimension(JLabel1.getPreferredSize().width+18,JLabel1.getPreferredSize().height+0));
		okButton.setPreferredSize(new Dimension(okButton.getPreferredSize().width+22,okButton.getPreferredSize().height+0));

  
          //<End_setUpProperties>
	} 
	public void setUpConnections()
  { 

		//<Begin_setUpConnections> 

      helpButton_helpButton_conn1 helpButton_helpButton_conn11 =  new helpButton_helpButton_conn1();
      helpButton.addActionListener(helpButton_helpButton_conn11);
      neLabel_neLabel_conn1 neLabel_neLabel_conn11 =  new neLabel_neLabel_conn1();
      neLabel.addMouseListener(neLabel_neLabel_conn11);
      ivLabel_ivLabel_conn1 ivLabel_ivLabel_conn11 =  new ivLabel_ivLabel_conn1();
      ivLabel.addMouseListener(ivLabel_ivLabel_conn11);
      detailsLabel_detailsLabel_conn1 detailsLabel_detailsLabel_conn11 =  new detailsLabel_detailsLabel_conn1();
      detailsLabel.addMouseListener(detailsLabel_detailsLabel_conn11);
      uiLabel_uiLabel_conn1 uiLabel_uiLabel_conn11 =  new uiLabel_uiLabel_conn1();
      uiLabel.addMouseListener(uiLabel_uiLabel_conn11);
      okButton_okButton_conn1 okButton_okButton_conn11 =  new okButton_okButton_conn1();
      okButton.addActionListener(okButton_okButton_conn11);
      cancelButton_cancelButton_conn1 cancelButton_cancelButton_conn11 =  new cancelButton_cancelButton_conn1();
      cancelButton.addActionListener(cancelButton_cancelButton_conn11);
  
      //<End_setUpConnections>
	} 
	public void stop()
  { 

		//<Begin_stop> 
       if(!running)
 return;
 running=false;

  
       //<End_stop>
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
            if (input.equals("RESOURCE_PROPERTIES")) value = "ElementConfigurationResources"; 
            if (input.equals("PORT")) value = "161"; 
            }
        return value;

  
           //<End_getParameter_String>
	} 
	public void init()
  { 

		//<Begin_init> 
	if(getParameter("RESOURCE_PROPERTIES" ) != null)
	{
		localePropertiesFileName = getParameter("RESOURCE_PROPERTIES");
	}
	resourceBundle = com.adventnet.apiutils.Utility.getBundle(localePropertiesFileName,getParameter("RESOURCE_LOCALE"),applet);
        if (initialized) return; 
        this.setSize(getPreferredSize().width+727,getPreferredSize().height+500); 
          setTitle(resourceBundle.getString("AddModifyDataSource"));
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
        // let us set the initialized variable to true so  
        // we dont initialize again even if init is called 
        initialized = true; 

  
         //<End_init>

		
		

	} 


	public void constructLabels()
	{
		detailsImageLabel = new JLabel();
		neImageLabel = new JLabel();
		ivImageLabel = new JLabel();
		uiImageLabel = new JLabel();

		detailsTextLabel = new JLabel(resourceBundle.getString("Template Details"));
		neTextLabel = new JLabel(resourceBundle.getString("Network Input"));
		ivTextLabel = new JLabel(resourceBundle.getString("Inventory Input"));
		uiTextLabel = new JLabel(resourceBundle.getString("User Input"));

		URL docBase = configPanel.applet.getDocumentBase();
		String test = docBase+"../images/devicesearch.png" ;
		ImageIcon image = configPanel.configClientUtils.getImage(test);
		String details = docBase+"../images/templatedetails.png" ;		
		ImageIcon detailsImage = configPanel.configClientUtils.getImage(details);
		String ne  =docBase+"../images/ntwkinput.png" ;		
		ImageIcon neImage = configPanel.configClientUtils.getImage(ne);		
		String iv  =docBase+"../images/inventoryinput.png" ;		
		ImageIcon ivImage = configPanel.configClientUtils.getImage(iv);				
		String ui  =docBase+"../images/userinput.png" ;		
		ImageIcon uiImage = configPanel.configClientUtils.getImage(ui);				
		String help = docBase+"../images/alarm.png";

		ImageIcon hicon1 = configPanel.configClientUtils.getImage(configPanel.applet.getDocumentBase()+"../images/contextualhelp.png");
		helpButton.setIcon(hicon1);

		//ImageIcon icon = configPanel.configClientUtils.getImage(help);
		//JLabel4.setIcon(icon);
		detailsImageLabel.setIcon(detailsImage);
		neImageLabel.setIcon(neImage);
		ivImageLabel.setIcon(ivImage);
		uiImageLabel.setIcon(uiImage);

		detailsLabel.add(detailsImageLabel,BorderLayout.CENTER);
		detailsLabel.add(detailsTextLabel,BorderLayout.SOUTH);
		neLabel.add(neImageLabel,BorderLayout.CENTER);
		neLabel.add(neTextLabel,BorderLayout.SOUTH);
		ivLabel.add(ivImageLabel,BorderLayout.CENTER);
		ivLabel.add(ivTextLabel,BorderLayout.SOUTH);
		uiLabel.add(uiImageLabel,BorderLayout.CENTER);
		uiLabel.add(uiTextLabel,BorderLayout.SOUTH);

		detailsImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		neImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ivImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		uiImageLabel.setHorizontalAlignment(SwingConstants.CENTER);

		detailsImageLabel.setOpaque(true);
		neImageLabel.setOpaque(true);
		ivImageLabel.setOpaque(true);
		uiImageLabel.setOpaque(true);

		detailsImageLabel.setBackground(Color.white);
		neImageLabel.setBackground(Color.white);
		ivImageLabel.setBackground(Color.white);
		uiImageLabel.setBackground(Color.white);

		detailsTextLabel.setHorizontalAlignment(SwingConstants.CENTER);
		neTextLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ivTextLabel.setHorizontalAlignment(SwingConstants.CENTER);
		uiTextLabel.setHorizontalAlignment(SwingConstants.CENTER);

		detailsTextLabel.setForeground(Color.black);
		neTextLabel.setForeground(Color.black);
		ivTextLabel.setForeground(Color.black);
		uiTextLabel.setForeground(Color.black);

		Font labelFont = new Font("dialog",Font.BOLD,13);
		detailsTextLabel.setFont(labelFont);
		neTextLabel.setFont(labelFont);
		ivTextLabel.setFont(labelFont);
		uiTextLabel.setFont(labelFont);

		detailsTextLabel.setOpaque(true);
		neTextLabel.setOpaque(true);
		ivTextLabel.setOpaque(true);
		uiTextLabel.setOpaque(true);

		detailsTextLabel.setBackground(Color.white);
		neTextLabel.setBackground(Color.white);
		ivTextLabel.setBackground(Color.white);
		uiTextLabel.setBackground(Color.white);

		detailsLabel.repaint();
		neLabel.repaint();
		ivLabel.repaint();
		uiLabel.repaint();
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



	//<Begin__class_detailsLabel_detailsLabel_conn1>

 class detailsLabel_detailsLabel_conn1 extends java.awt.event.MouseAdapter implements java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_detailsLabel_detailsLabel_conn1>



     //Listener Interface Methods Are Added Below 


     public void mouseEntered( java.awt.event.MouseEvent arg0)
     {
  detailsPanelMouseEnteredEvent(arg0);
     }



     public void mouseExited( java.awt.event.MouseEvent arg0)
     {
  detailsPanelMouseExitedEvent(arg0);
     }
 


     public void mouseClicked( java.awt.event.MouseEvent arg0)
     {

     }
 


     public void mousePressed( java.awt.event.MouseEvent arg0)
     {
  detailsPanelMouseClickedEvent(arg0);  
     }
   
//<UserCode_End_Connection_detailsLabel_detailsLabel_conn1>
 }//<End__class_detailsLabel_detailsLabel_conn1>


	public void detailsPanelMouseEnteredEvent(MouseEvent me)
	{
		JPanel label = (JPanel)me.getSource();
		if(label.equals(detailsLabel))
		{
			if(!isDetailsClicked)
			{
				detailsLabel.setCursor(handCursor);
			}
		}

		else if(label.equals(neLabel))
		{
			if(!isNeClicked)
			{
				neLabel.setCursor(handCursor);
			}
		}

		else if(label.equals(ivLabel))
		{
			if(!isIvClicked)
			{
				ivLabel.setCursor(handCursor);
			}
		}

		else if(label.equals(uiLabel))
		{
			if(!isUiClicked)
			{
				uiLabel.setCursor(handCursor);
			}
		}
	}


	public void detailsPanelMouseExitedEvent(MouseEvent me)
	{
		JPanel label = (JPanel)me.getSource();
		if(label.equals(detailsLabel))
		{
			if(!isDetailsClicked)
			{
				//detailsLabel.setOpaque(false);
				detailsLabel.setCursor(normalCursor);
			}
		}

		else if(label.equals(neLabel))
		{
			if(!isNeClicked)
			{
				//neLabel.setOpaque(false);
				neLabel.setCursor(normalCursor);
			}
		}

		else if(label.equals(ivLabel))
		{
			if(!isIvClicked)
			{
				//ivLabel.setOpaque(false);
				ivLabel.setCursor(normalCursor);
			}
		}

		else if(label.equals(uiLabel))
		{
			if(!isUiClicked)
			{
				//uiLabel.setOpaque(false);
				uiLabel.setCursor(normalCursor);
			}
		}
	}

	public void setDefaultExcept(String str)
	{
		if(str.equals("details"))
		{
			defaultForNE();
			defaultForIV();
			defaultForUI();
		}

		else if(str.equals("ne"))
		{
			defaultForDetails();
			defaultForIV();
			defaultForUI();
		}

		else if(str.equals("iv"))
		{
			defaultForDetails();
			defaultForNE();
			defaultForUI();
		}

		else if(str.equals("ui"))
		{
			defaultForDetails();
			defaultForNE();
			defaultForIV();
		}
	}

	public void defaultForDetails()
	{
		detailsLabel.setForeground(Color.black);
		detailsLabel.setBackground(Color.white);
		detailsImageLabel.setForeground(Color.black);
		detailsImageLabel.setBackground(Color.white);
		detailsTextLabel.setForeground(Color.black);
		detailsTextLabel.setBackground(Color.white);
	}

	public void defaultForNE()
	{
		neLabel.setForeground(Color.black);
		neLabel.setBackground(Color.white);
		neImageLabel.setForeground(Color.black);
		neImageLabel.setBackground(Color.white);
		neTextLabel.setForeground(Color.black);
		neTextLabel.setBackground(Color.white);
	}

	public void defaultForIV()
	{
		ivLabel.setForeground(Color.black);
		ivLabel.setBackground(Color.white);
		ivImageLabel.setForeground(Color.black);
		ivImageLabel.setBackground(Color.white);
		ivTextLabel.setForeground(Color.black);
		ivTextLabel.setBackground(Color.white);
	}

	public void defaultForUI()
	{
		uiLabel.setForeground(Color.black);
		uiLabel.setBackground(Color.white);
		uiImageLabel.setForeground(Color.black);
		uiImageLabel.setBackground(Color.white);
		uiTextLabel.setForeground(Color.black);
		uiTextLabel.setBackground(Color.white);
	}	
	public void detailsPanelMouseClickedEvent(MouseEvent me)
	{
		JPanel panel = (JPanel)me.getSource();
		if(panel.equals(detailsLabel))
		{
			isDetailsClicked = true;
			isNeClicked = false;
			isIvClicked = false;
			isUiClicked = false;
			detailsLabel.setForeground(Color.white);
			detailsLabel.setBackground(selectionColor);
			detailsTextLabel.setForeground(Color.white);
			detailsImageLabel.setForeground(Color.white);
			detailsTextLabel.setBackground(selectionColor);
			detailsImageLabel.setBackground(selectionColor);			
			/*neLabel.setOpaque(false);
			  ivLabel.setOpaque(false);
			  uiLabel.setOpaque(false);*/
			setDefaultExcept("details");
			cardLayout.show(cardPanel,"DetailsPanel");
		}

		if(panel.equals(neLabel))
		{
			isDetailsClicked = false;
			isNeClicked = true;
			isIvClicked = false;
			isUiClicked = false;
			//neLabel.setOpaque(true);
			neLabel.setForeground(Color.white);
			neLabel.setBackground(selectionColor);
			neTextLabel.setForeground(Color.white);
			neImageLabel.setForeground(Color.white);
			neTextLabel.setBackground(selectionColor);
			neImageLabel.setBackground(selectionColor);			
			/*detailsLabel.setOpaque(false);
			  ivLabel.setOpaque(false);
			  uiLabel.setOpaque(false);*/
			setDefaultExcept("ne");			
			cardLayout.show(cardPanel,"NetworkInput");
		}

		if(panel.equals(ivLabel))
		{
			isDetailsClicked = false;
			isNeClicked = false;
			isIvClicked = true;
			isUiClicked = false;
			//ivLabel.setOpaque(true);
			ivLabel.setForeground(Color.white);
			ivLabel.setBackground(selectionColor);
			ivTextLabel.setForeground(Color.white);
			ivImageLabel.setForeground(Color.white);
			ivTextLabel.setBackground(selectionColor);
			ivImageLabel.setBackground(selectionColor);
			/*detailsLabel.setOpaque(false);
			  neLabel.setOpaque(false);
			  uiLabel.setOpaque(false);*/
			setDefaultExcept("iv");			
			cardLayout.show(cardPanel,"InventoryInput");
		}

		if(panel.equals(uiLabel))
		{
			isDetailsClicked = false;
			isNeClicked = false;
			isIvClicked = false;
			isUiClicked = true;
			//uiLabel.setOpaque(true);
			uiLabel.setForeground(Color.white);
			uiLabel.setBackground(selectionColor);
			uiTextLabel.setForeground(Color.white);
			uiImageLabel.setForeground(Color.white);
			uiTextLabel.setBackground(selectionColor);
			uiImageLabel.setBackground(selectionColor);
			/*detailsLabel.setOpaque(false);
			  neLabel.setOpaque(false);
			  ivLabel.setOpaque(false);*/
			setDefaultExcept("ui");			
			cardLayout.show(cardPanel,"UserInput");
		}
		neLabel.repaint();
		ivLabel.repaint();
		detailsLabel.repaint();
		uiLabel.repaint();

		//JPanel3.setBackground(Color.red);
		//neLabel.setBackground(Color.red);
		JPanel3.repaint();
	}




	//<Begin__class_neLabel_neLabel_conn1>

 class neLabel_neLabel_conn1 extends java.awt.event.MouseAdapter implements java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_neLabel_neLabel_conn1>



     //Listener Interface Methods Are Added Below 


     public void mouseEntered( java.awt.event.MouseEvent arg0)
     {
  detailsPanelMouseEnteredEvent(arg0);
     }



     public void mouseExited( java.awt.event.MouseEvent arg0)
     {
  detailsPanelMouseExitedEvent(arg0); 
}
 


     public void mouseClicked( java.awt.event.MouseEvent arg0)
     {

     }
 


     public void mousePressed( java.awt.event.MouseEvent arg0)
     {
  detailsPanelMouseClickedEvent(arg0);  
     }
   
//<UserCode_End_Connection_neLabel_neLabel_conn1>
 }//<End__class_neLabel_neLabel_conn1>
	//<Begin__class_ivLabel_ivLabel_conn1>

 class ivLabel_ivLabel_conn1 extends java.awt.event.MouseAdapter implements java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_ivLabel_ivLabel_conn1>



     //Listener Interface Methods Are Added Below 


     public void mouseEntered( java.awt.event.MouseEvent arg0)
     {
  detailsPanelMouseEnteredEvent(arg0);
     }



     public void mouseExited( java.awt.event.MouseEvent arg0)
     {
  detailsPanelMouseExitedEvent(arg0);
     }
 


     public void mouseClicked( java.awt.event.MouseEvent arg0)
     {

     }
 


     public void mousePressed( java.awt.event.MouseEvent arg0)
     {
  detailsPanelMouseClickedEvent(arg0);  
     }
   
//<UserCode_End_Connection_ivLabel_ivLabel_conn1>
 }//<End__class_ivLabel_ivLabel_conn1>
	//<Begin__class_uiLabel_uiLabel_conn1>

 class uiLabel_uiLabel_conn1 extends java.awt.event.MouseAdapter implements java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_uiLabel_uiLabel_conn1>



     //Listener Interface Methods Are Added Below 


     public void mouseEntered( java.awt.event.MouseEvent arg0)
     {
  detailsPanelMouseEnteredEvent(arg0);
     }



     public void mouseExited( java.awt.event.MouseEvent arg0)
     {
  detailsPanelMouseExitedEvent(arg0);
     }
 


     public void mouseClicked( java.awt.event.MouseEvent arg0)
     {

     }
 


     public void mousePressed( java.awt.event.MouseEvent arg0)
     {
  detailsPanelMouseClickedEvent(arg0);  
     }
   
//<UserCode_End_Connection_uiLabel_uiLabel_conn1>
 }//<End__class_uiLabel_uiLabel_conn1>


	//<Begin__class_okButton_okButton_conn1>

 class okButton_okButton_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_okButton_okButton_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  okButtonActionPerformed();
     }
//<UserCode_End_Connection_okButton_okButton_conn1>
 }//<End__class_okButton_okButton_conn1>
	//<Begin__class_cancelButton_cancelButton_conn1>

 class cancelButton_cancelButton_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_cancelButton_cancelButton_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  cancelButtonActionPerformed();
     }
//<UserCode_End_Connection_cancelButton_cancelButton_conn1>
 }//<End__class_cancelButton_cancelButton_conn1>


	//<===================================================================================================================>

	private	ConfigPanel configPanel = null;  

	private	boolean isDetailsClicked = false;
	private	boolean isNeClicked = false;
	private boolean isIvClicked = false;
	private	boolean isUiClicked = false;

	private	JLabel detailsImageLabel = null;
	private	JLabel neImageLabel = null;
	private	JLabel ivImageLabel = null;
	private	JLabel uiImageLabel = null;

	private	JLabel detailsTextLabel = null;
	private	JLabel neTextLabel = null;
	private	JLabel ivTextLabel = null;
	private	JLabel uiTextLabel = null;

	//private Color selectionColor = new Color(0,102,102);
	private Color selectionColor = Color.blue;
	private	Color highlightColor = new Color(153,153,255);
	private Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);
	private Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);

	private	CardLayout cardLayout = new CardLayout(2,2);	

	private TemplateDetailsPanel templateDetailsPanel = null;
	private	NetworkInputPanel networkInputPanel = null;
	private	InventoryInputPanel inventoryInputPanel = null;
	private	UserInputPanel userInputPanel = null;

	private DataSourceForTemplate dataSourceMainDialog = null;

	private DataSourceReader dataSourceReader = null;

	private HelpDialog helpDialog = null;

	private Vector templateDetails = null;

	public AddModifyDataSource(ConfigPanel configPanel, DataSourceForTemplate dataSourceMainDialog, Vector templateDetails)
	{
		super(configPanel.configClientUtils.getParentDialog(dataSourceMainDialog));

		this.configPanel = configPanel;
		this.dataSourceMainDialog = dataSourceMainDialog;
		this.templateDetails = templateDetails;
		
		applet = configPanel.applet;

		init();
		configInit();
	}

	public AddModifyDataSource(ConfigPanel configPanel, DataSourceForTemplate dataSourceMainDialog, DataSourceReader dataSourceReader,  Vector templateDetails)
	{
		super(configPanel.configClientUtils.getParentDialog(dataSourceMainDialog));

		this.configPanel = configPanel;
		this.dataSourceMainDialog = dataSourceMainDialog;
		this.dataSourceReader = dataSourceReader;
		this.templateDetails = templateDetails;
		
		applet = configPanel.applet;

		init();
		configInit();
	}

	private void configInit()
	{
		setTitle(resourceBundle.getString("Data Source Configuration"));
		detailsLabel.setLayout(new BorderLayout());
		neLabel.setLayout(new BorderLayout());
		ivLabel.setLayout(new BorderLayout());
		uiLabel.setLayout(new BorderLayout());

		if(dataSourceReader != null)
		{
			nameText.setText(dataSourceReader.getDataSourceName());	
			nameText.setEnabled(false);
			nameText.setDisabledTextColor(Color.black);
		}

		constructLabels();
		cardPanel.setLayout(cardLayout);
		templateDetailsPanel = new TemplateDetailsPanel(configPanel, templateDetails);
		networkInputPanel = new NetworkInputPanel(configPanel, templateDetails);
		inventoryInputPanel = new InventoryInputPanel(configPanel, templateDetails);
		userInputPanel = new UserInputPanel(configPanel, templateDetails);
		cardLayout.addLayoutComponent("DetailsPanel",templateDetailsPanel);
		cardLayout.addLayoutComponent("NetworkInput",networkInputPanel);
		cardLayout.addLayoutComponent("InventoryInput",inventoryInputPanel);
		cardLayout.addLayoutComponent("UserInput",userInputPanel);
		cardPanel.add("DetailsPanel",templateDetailsPanel);
		cardPanel.add("NetworkInput",networkInputPanel);
		cardPanel.add("InventoryInput",inventoryInputPanel);
		cardPanel.add("UserInput",userInputPanel);
		detailsLabel.setOpaque(true);
		detailsLabel.setForeground(Color.white);
		detailsLabel.setBackground(selectionColor);
		detailsImageLabel.setForeground(Color.white);
		detailsImageLabel.setBackground(selectionColor);
		detailsTextLabel.setForeground(Color.white);
		detailsTextLabel.setBackground(selectionColor);
		cardLayout.show(cardPanel,"DetailsPanel");
		//setDefaultExcept("details");
		isDetailsClicked = true;
		repaint();

		if(dataSourceReader != null)
		{
			Hashtable dataSourceAttributes = dataSourceReader.getDataSourceAttributes();

			networkInputPanel.setValues((Vector)dataSourceAttributes.get("NEInput"));
			inventoryInputPanel.setValues((Vector)dataSourceAttributes.get("InventoryInput"));
			userInputPanel.setValues((Vector)dataSourceAttributes.get("UserInput"));

		}

		configPanel.configClientUtils.centerWindow(this);
	}

	public void okButtonActionPerformed()
	{
		Properties dataSourceProp = new Properties();

		if((nameText.getText().trim()).equals(""))
		{
			configPanel.configClientUtils.showErrorDialog(this, resourceBundle.getString("Please specify the Data Source name"), resourceBundle.getString("Info"), "info");
			return;
		}

		dataSourceProp.put("name", nameText.getText());
		dataSourceProp.put("description", "");
		dataSourceProp.put("template", templateDetails.elementAt(0));
		dataSourceProp.put("protocol", templateDetails.elementAt(2));

		Hashtable dataSourceAttributes = new Hashtable();

		dataSourceAttributes.put("NEInput", networkInputPanel.getAllValues());
		dataSourceAttributes.put("InventoryInput", inventoryInputPanel.getAllValues());
		dataSourceAttributes.put("UserInput", userInputPanel.getAllValues());

		dataSourceProp.put("attributes", dataSourceAttributes);

		DataSourceXMLGenerator xmlGen = new DataSourceXMLGenerator();

		Object[] params = {xmlGen.getDataSource(dataSourceProp)};
		configPanel.configResponseHandler.sendRequest(NmsConfigConstants.SAVE_DATASOURCE, params, this);
		
		setVisible(false);
	}

	public void cancelButtonActionPerformed()	
	{
		setVisible(false);
		dispose();
	}

	public void response(ConfigResultEvent configResultEvent)
	{
		int workID = configResultEvent.getWorkID();
		int errorCode = configResultEvent.getErrorCode();
		
		String uniqueID = configResultEvent.getRequestID();

		if(workID == NmsConfigConstants.SAVE_DATASOURCE)
		{
			if(errorCode == NmsConfigConstants.NO_ERROR)
			{
				if(dataSourceReader == null)
				{
					dataSourceMainDialog.addDataSourceToView(nameText.getText());
				}
			}
			else
			{
				configPanel.configClientUtils.showErrorDialog(this, configResultEvent.getErrorString(), resourceBundle.getString("Error"), "error");
			}

			dispose();
		}
	}




	//<Begin__class_helpButton_helpButton_conn1>

 class helpButton_helpButton_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_helpButton_helpButton_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
    contextSensitiveHelpActionPerformed();
     }
//<UserCode_End_Connection_helpButton_helpButton_conn1>
 }//<End__class_helpButton_helpButton_conn1>

	public void contextSensitiveHelpActionPerformed()
	{
		if(helpDialog == null)
		{
			helpDialog = new HelpDialog(configPanel, this);
		}
		else
		{
			if(!helpDialog.isVisible())
			{
				configPanel.configClientUtils.centerWindow(helpDialog);
			}
		}

		helpDialog.setVisible(true);
	}

	public String  getHelpFor(String key)
	{
		String helpContent = "";

		if(key.equals(resourceBundle.getString("Add/Modify Data Source")))
		{
			helpContent = "\t" + resourceBundle.getString("Data source operations allow the user to create new data source or modify an existing data source. The details of the template will be shown by default. User can select the corresponding Input type where the user can view the place holders and also enter the values corresponding to the input type selected. A unique name can be given to the data source created and then clicking \"OK\" will save the data source in the database with the given name. User can create any number of data sources for a template task.");
		}

		return helpContent;
	}

	public String getTitle()
	{
		return resourceBundle.getString("Add/Modify Data Source");
	}

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}
