
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>//No Internationalisation
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.examples.routermap;




import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import com.adventnet.nms.util.*;

public class IFDetails extends JPanel 
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	javax.swing.JPanel  Top = null;
	javax.swing.JTextField  IFTypetext = null;
	javax.swing.JTextField  MACAdText = null;
	javax.swing.JTextField  IFSpeedText = null;
	javax.swing.JTextField  IFDescrText = null;
	javax.swing.JTextField  NetmaskText = null;
	javax.swing.JTextField  IpAddrText = null;
	javax.swing.JLabel  IftypeLab = null;
	javax.swing.JLabel  MacAdLab = null;
	javax.swing.JLabel  IfSpeed = null;
	javax.swing.JLabel  IfDescrlab = null;
	javax.swing.JLabel  NetMaskLab = null;
	javax.swing.JLabel  IpAddLab = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>


  
  
  
   


  public IFDetails()
  {
    //<Begin_IFDetails>
    this.init();
  
    //<End_IFDetails>
  }

  public IFDetails(java.applet.Applet applet)
  {
    //<Begin_IFDetails_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_IFDetails_java.applet.Applet>
  }

 
    public void start()
  { 

  //<Begin_start>

  //<End_start>
  } 
  public void initVariables()throws Exception
  { 

  //<Begin_initVariables>
        Top= new javax.swing.JPanel();
        IFTypetext= new javax.swing.JTextField();
        MACAdText= new javax.swing.JTextField();
        IFSpeedText= new javax.swing.JTextField();
        IFDescrText= new javax.swing.JTextField();
        NetmaskText= new javax.swing.JTextField();
        IpAddrText= new javax.swing.JTextField();
        IftypeLab= new javax.swing.JLabel();
        MacAdLab= new javax.swing.JLabel();
        IfSpeed= new javax.swing.JLabel();
        IfDescrlab= new javax.swing.JLabel();
        NetMaskLab= new javax.swing.JLabel();
        IpAddLab= new javax.swing.JLabel();

  //<End_initVariables>
  } 
   
  public void setUpGUI(Container container)throws Exception
  { 

  //<Begin_setUpGUI_Container>
container.add(Top,BorderLayout.CENTER);
	Top.setLayout(new GridBagLayout());
	inset = new Insets(5,5,5,20);
	setConstraints(1,5,1,1,0.1,0.2,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
	Top.add(IFTypetext,cons);
	inset = new Insets(5,5,5,20);
	setConstraints(1,4,1,1,0.0,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
	Top.add(MACAdText,cons);
	inset = new Insets(5,5,5,20);
	setConstraints(1,3,1,1,0.0,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
	Top.add(IFSpeedText,cons);
	inset = new Insets(5,5,5,20);
	setConstraints(1,2,1,1,0.0,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
	Top.add(IFDescrText,cons);
	inset = new Insets(5,5,5,20);
	setConstraints(1,1,1,1,0.0,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
	Top.add(NetmaskText,cons);
	inset = new Insets(20,5,5,20);
	setConstraints(1,0,1,1,0.0,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
	Top.add(IpAddrText,cons);
	inset = new Insets(5,5,5,5);
	setConstraints(0,5,1,1,0.0,0.2,cons.NORTHWEST,cons.NONE,inset,0,0);
	Top.add(IftypeLab,cons);
	inset = new Insets(5,5,5,5);
	setConstraints(0,4,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
	Top.add(MacAdLab,cons);
	inset = new Insets(5,5,5,5);
	setConstraints(0,3,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
	Top.add(IfSpeed,cons);
	inset = new Insets(5,5,5,5);
	setConstraints(0,2,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
	Top.add(IfDescrlab,cons);
	inset = new Insets(5,5,5,5);
	setConstraints(0,1,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
	Top.add(NetMaskLab,cons);
	inset = new Insets(20,5,5,5);
	setConstraints(0,0,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
	Top.add(IpAddLab,cons);
	
  //<End_setUpGUI_Container>
  } 
  public void setUpProperties()throws Exception
  { 

  //<Begin_setUpProperties>

          try
          {
            Top.setBackground(new Color(-1));
          }
          catch(Exception ex)
          {
             showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+Top,ex); 
          }

          try
          {
            IFTypetext.setBackground(new Color(-13108));
            IFTypetext.setEditable(false);
          }
          catch(Exception ex)
          {
             showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+IFTypetext,ex); 
          }

          try
          {
            MACAdText.setBackground(new Color(-13108));
            MACAdText.setEditable(false);
          }
          catch(Exception ex)
          {
             showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+MACAdText,ex); 
          }

          try
          {
            IFSpeedText.setBackground(new Color(-13108));
            IFSpeedText.setEditable(false);
          }
          catch(Exception ex)
          {
             showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+IFSpeedText,ex); 
          }

          try
          {
            IFDescrText.setBackground(new Color(-13108));
            IFDescrText.setEditable(false);
          }
          catch(Exception ex)
          {
             showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+IFDescrText,ex); 
          }

          try
          {
            NetmaskText.setBackground(new Color(-13108));
            NetmaskText.setEditable(false);
          }
          catch(Exception ex)
          {
             showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+NetmaskText,ex); 
          }

          try
          {
            IpAddrText.setBackground(new Color(-13108));
            IpAddrText.setEditable(false);
          }
          catch(Exception ex)
          {
             showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+IpAddrText,ex); 
          }

          try
          {
            IftypeLab.setBackground(new Color(-15987442));
            IftypeLab.setForeground(new Color(-16383743));
            IftypeLab.setText(NmsClientUtil.GetString("Physical media"));
          }
          catch(Exception ex)
          {
             showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+IftypeLab,ex); 
          }

          try
          {
            MacAdLab.setText(NmsClientUtil.GetString("MAC Address"));
            MacAdLab.setBackground(new Color(-16185078));
            MacAdLab.setForeground(new Color(-16056320));
          }
          catch(Exception ex)
          {
             showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+MacAdLab,ex); 
          }

          try
          {
            IfSpeed.setForeground(new Color(-16119543));
            IfSpeed.setText(NmsClientUtil.GetString("Interface Speed (Mbps)"));
          }
          catch(Exception ex)
          {
             showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+IfSpeed,ex); 
          }

          try
          {
            IfDescrlab.setText(NmsClientUtil.GetString("Interface Descriptor"));
            IfDescrlab.setForeground(new Color(-15856371));
          }
          catch(Exception ex)
          {
             showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+IfDescrlab,ex); 
          }

          try
          {
            NetMaskLab.setText(NmsClientUtil.GetString("Netmask"));
            NetMaskLab.setForeground(new Color(-16252928));
          }
          catch(Exception ex)
          {
             showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+NetMaskLab,ex); 
          }

          try
          {
            IpAddLab.setText(NmsClientUtil.GetString("Ip Address"));
            IpAddLab.setForeground(new Color(-16774389));
          }
          catch(Exception ex)
          {
             showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+IpAddLab,ex); 
          }

  //<End_setUpProperties>
  } 
  public void setUpConnections()throws Exception
  { 

  //<Begin_setUpConnections>

  //<End_setUpConnections>
  } 
  public void stop()
  { 

  //<Begin_stop>

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
            if (input.equals("HOST")) value = "localhost"; //No Internationalisation
            if (input.equals("PORT")) value = "161"; //No Internationalisation
            }
        return value;

  //<End_getParameter_String>
  } 
  public void init()
  { 

  //<Begin_init>
        if (initialized == true) return; 
        setPreferredSize(new Dimension(getPreferredSize().width+450,getPreferredSize().height+262)); 
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
          showStatus(NmsClientUtil.GetString("Error in init method"),ex); 
        } 
        // let us set the initialzed variable to true so  
        // we dont initialize again even if init is called 
        initialized = true; 
      setUpMenus();
      setUpToolBar();
        

  //<End_init>
  } 

     

  
  public void showStatus(String message)
  {
     //<Begin_showStatus_String>
     System.out.println(NmsClientUtil.GetString("Internal Error :")+ message);
     //<End_showStatus_String>
  }
  public void showStatus(String message,Exception ex)
  {
     //<Begin_showStatus_String_Exception>
     System.out.println(NmsClientUtil.GetString("Internal Error :")+ message);
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

  public void setValues(Properties prop)
	{
		IpAddrText.setText(prop.getProperty("ipAddress"));	//No Internationalisation
		NetmaskText.setText(prop.getProperty("netmask"));//No Internationalisation
		IFDescrText.setText(prop.getProperty("ifDescr"));//No Internationalisation
		double speed = Integer.parseInt(prop.getProperty("ifSpeed"))/1000000;//No Internationalisation
		IFSpeedText.setText(speed +"");//No Internationalisation
		MACAdText.setText(prop.getProperty("physAddr"));//No Internationalisation
		
		switch(Integer.parseInt(prop.getProperty("physMedia")))//No Internationalisation
			{
				case 1:  { IFTypetext.setText("other"); break; }//No Internationalisation
				case 2:	  { IFTypetext.setText("regular1822"); break; }	//No Internationalisation
				case 3:  { IFTypetext.setText("hdh1822"); break; }	//No Internationalisation
				case 4:  { IFTypetext.setText("ddn-x25"); break; }	//No Internationalisation
				case 5:  { IFTypetext.setText("rfc877-x25");	break; }//No Internationalisation
				case 6:	  { IFTypetext.setText("ethernet-csmacd"); break; }				//No Internationalisation
				case 7:  { IFTypetext.setText("iso 88023 - csmacd");	break; }//No Internationalisation
				case 8:  { IFTypetext.setText("iso 88024 - tokenBus"); break; }//No Internationalisation
				case 9:  { IFTypetext.setText("iso 88025 - tokenRing"); break; }	//No Internationalisation
				case 10: {  IFTypetext.setText("iso 88026 - man");  break; }					//No Internationalisation
				case 11: { IFTypetext.setText("starLan");  break; }	//No Internationalisation
				case 12: { IFTypetext.setText("proteon-10 Mbit"); break; }	//No Internationalisation
				case 13: { IFTypetext.setText("proteon-80 Mbit"); break; }	//No Internationalisation
				case 14: { IFTypetext.setText("hyperchannel"); break; }					//No Internationalisation
				case 15: { IFTypetext.setText("fddi"); break; }	//No Internationalisation
				case 16: { IFTypetext.setText("lapb"); break; }	//No Internationalisation
				case 17: { IFTypetext.setText("sdlc"); break; }	//No Internationalisation
				case 18: { IFTypetext.setText("ds1");	break; } 				//No Internationalisation
				case 19: { IFTypetext.setText("e1"); break; }	//No Internationalisation
				case 20: { IFTypetext.setText("basicISDN"); break; }	//No Internationalisation
				case 21: { IFTypetext.setText("primaryISDN");	break; }//No Internationalisation
				case 22: { IFTypetext.setText("propPointToPointSerial"); break; }					//No Internationalisation
				case 23: { IFTypetext.setText("ppp"); break; }	//No Internationalisation
				case 24: { IFTypetext.setText("softwareLoopback"); break; }	//No Internationalisation
				case 25: { IFTypetext.setText("eon"); break; }	//No Internationalisation
				case 26: { IFTypetext.setText("ethernet -3Mbit");  break; }					//No Internationalisation
				case 27: { IFTypetext.setText("nsip"); break; }	//No Internationalisation
				case 28: { IFTypetext.setText("slip"); break; }	//No Internationalisation
				case 29: { IFTypetext.setText("ultra"); break; }	//No Internationalisation
				case 30: { IFTypetext.setText("ds3"); break; }					//No Internationalisation
				case 31: { IFTypetext.setText("sip"); break; }	//No Internationalisation
				case 32: { IFTypetext.setText("frame-relay"); break; }	//No Internationalisation
					
			}

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
}






