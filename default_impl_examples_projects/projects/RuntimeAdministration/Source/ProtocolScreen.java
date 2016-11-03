
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

/* $Id: ProtocolScreen.java,v 1.1 2006/08/29 13:57:02 build Exp $ */

package com.adventnet.nms.runtimeconfig;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Vector;

public class ProtocolScreen extends JPanel implements ActionListener,MouseListener
{
    //<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "RuntimeAdministrationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel tipPanel = null;
	javax.swing.JTextArea taProtocol = null;
	javax.swing.JLabel lfEmpty = null;
	javax.swing.JScrollPane listScroller = null;
	javax.swing.JList listProtoScr = null;
	javax.swing.JButton bProperties = null;
	javax.swing.JPanel descrPanel = null;
	javax.swing.JTextArea taDes = null;
	javax.swing.JLabel lfImage = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>
     DefaultListModel datamodel = new DefaultListModel();
    SNMPScreen snmpScr = null;
    ICMPScreen icmpScr = null;
    NativePingScreen nativePingScr = null;
    boolean isModified = false;
	int index;


    private static Vector protoVec = null;

    private static Vector storeVec = new Vector();

    public static void setProtocolList(Vector vec)
    {
        protoVec = vec;

    }

    public static void setStoreVec(Vector vec)
    {
        storeVec = vec;
    }



    public ProtocolScreen()
    {
        //<Begin_ProtocolScreen>
    this.init();

    //<End_ProtocolScreen>
    }

    public ProtocolScreen(SNMPScreen SNMP,ICMPScreen ICMP,NativePingScreen natPing)
    {
	//mtest	resourceBundle = com.adventnet.apiutils.Utility.getBundle(getParameter("RESOURCE_PROPERTIES"),applet);
        snmpScr = SNMP;
        icmpScr = ICMP;
        nativePingScr = natPing;
    }


    public ProtocolScreen(java.applet.Applet applet)
  {
        //<Begin_ProtocolScreen_java.applet.Applet>
    this.applet = applet;
    this.init();

    //<End_ProtocolScreen_java.applet.Applet>
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
        tipPanel= new javax.swing.JPanel();
        taProtocol= new javax.swing.JTextArea();
        lfEmpty= new javax.swing.JLabel();
        listScroller= new javax.swing.JScrollPane();
        listProtoScr= new javax.swing.JList();
        bProperties= new javax.swing.JButton();
        descrPanel= new javax.swing.JPanel();
        taDes= new javax.swing.JTextArea();
        lfImage= new javax.swing.JLabel();


        //<End_initVariables>
        listProtoScr= new javax.swing.JList(datamodel);
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
setConstraints(0,0,0,1,0.1,0.0,cons.WEST,cons.HORIZONTAL,inset,0,0);
Top.add(tipPanel,cons);
tipPanel.setLayout(new BorderLayout(5,5));
tipPanel.add(taProtocol,BorderLayout.CENTER);
tipPanel.add(lfEmpty,BorderLayout.WEST);
inset = new Insets(5,5,5,5);
setConstraints(0,1,0,1,0.1,0.1,cons.WEST,cons.BOTH,inset,0,0);
Top.add(listScroller,cons);
listScroller.getViewport().add(listProtoScr);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.0,0.0,cons.EAST,cons.NONE,inset,0,0);
Top.add(bProperties,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,3,0,1,0.1,0.1,cons.WEST,cons.BOTH,inset,0,0);
Top.add(descrPanel,cons);
descrPanel.setLayout(new BorderLayout(5,5));
descrPanel.add(taDes,BorderLayout.CENTER);
descrPanel.add(lfImage,BorderLayout.WEST);


//<End_setUpGUI_Container>
	listProtoScr.addMouseListener(this);
        bProperties.addActionListener(this);
		bProperties.setMnemonic(KeyEvent.VK_P);
	}
    public void setUpProperties()
  {

        //<Begin_setUpProperties>

          try
          {
            tipPanel.setForeground(new Color(-16776443));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+tipPanel,ex);
          }

//<UserCode_Begin_Bean_tipPanel>

//<UserCode_End_Bean_tipPanel>

          try
          {
            taProtocol.setEditable(false);
            taProtocol.setWrapStyleWord(true);
            taProtocol.setLineWrap(true);
            taProtocol.setText(resourceBundle.getString("Protocols supported by Web NMS discovery process."));
            taProtocol.setFont(new Font("Dialog",0,10));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+taProtocol,ex);
          }

//<UserCode_Begin_Bean_taProtocol>

//<UserCode_End_Bean_taProtocol>

          try
          {
            listProtoScr.setEnabled(true);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+listProtoScr,ex);
          }

//<UserCode_Begin_Bean_listProtoScr>

//<UserCode_End_Bean_listProtoScr>

          try
          {
            bProperties.setBorder(new javax.swing.border.BevelBorder(0));
            bProperties.setHorizontalTextPosition(0);
            bProperties.setText(resourceBundle.getString("Properties"));
            bProperties.setPreferredSize(new Dimension(95,27));
            bProperties.setMinimumSize(new Dimension(95,27));
            bProperties.setMaximumSize(new Dimension(95,27));
            bProperties.setToolTipText(resourceBundle.getString("Select an item from the above list and click here to configure the respective properties."));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+bProperties,ex);
          }

//<UserCode_Begin_Bean_bProperties>

//<UserCode_End_Bean_bProperties>

          try
          {
            descrPanel.setBorder(new javax.swing.border.EtchedBorder(0));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+descrPanel,ex);
          }

//<UserCode_Begin_Bean_descrPanel>

//<UserCode_End_Bean_descrPanel>

          try
          {
            taDes.setEnabled(true);
            taDes.setEditable(false);
            taDes.setWrapStyleWord(true);
            taDes.setLineWrap(true);
            taDes.setFont(new Font("Dialog",0,11));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+taDes,ex);
          }

//<UserCode_Begin_Bean_taDes>

//<UserCode_End_Bean_taDes>
		taDes.setPreferredSize(new Dimension(taDes.getPreferredSize().width+0,taDes.getPreferredSize().height+107));
		taProtocol.setPreferredSize(new Dimension(taProtocol.getPreferredSize().width+0,taProtocol.getPreferredSize().height+5));


          //<End_setUpProperties>

        taProtocol.setBackground(tipPanel.getBackground());
        taProtocol.setForeground(Color.black);
        taProtocol.setFont(new Font("Dialog",0,11));
        taDes.setText("\n"
		+resourceBundle.getString("SNMP facilitates communication between a managed device (a device with an SNMP agent, e.g. a router), and an SNMP manager or management application (represents a user of network management).")
		+"\n\n"
		+resourceBundle.getString("Internet Control Message Protocol(ICMP), is a required protocol tightly integrated with IP. ICMP messages, delivered in IP packets, are used in out of band messages related to network operation or misoperation.Since ICMP uses IP, ICMP packet delivery is unreliable, so hosts cannot count on receiving ICMP packets in case of any network problem."));
        taDes.setBackground(descrPanel.getBackground());
        taDes.setForeground(Color.black);
        taDes.setFont(new Font("Dialog",0,11));
        lfImage.setHorizontalAlignment(0);
        lfImage.setHorizontalTextPosition(0);
        lfImage.setIcon(MainScreen.getCommonInterface().getImage("allprotocols.png","images/runtimeadmin"));
        lfEmpty.setHorizontalAlignment(0);
        lfEmpty.setHorizontalTextPosition(0);
        lfEmpty.setIcon(MainScreen.getCommonInterface().getImage("tips.png","images/runtimeadmin"));
    }

/**
     * Get the value of isModified.
     * @return value of isModified.
     */
    public boolean getIsModified()
    {
        boolean val = false;
        if(snmpScr.isModified || icmpScr.isModified || nativePingScr.isModified)
        {
            val = true;
        }
        return val;
    }

    /**
     * Set the value of isModified.
     * @param v  Value to assign to isModified.
     */
    public void setIsModified(boolean val)
    {
        isModified = val;
        snmpScr.isModified = val;
        icmpScr.isModified = val;
        nativePingScr.isModified = val;
    }

    public void setUpConnections()
  {

        //<Begin_setUpConnections>


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
            if (input.equals("RESOURCE_PROPERTIES")) value = "RuntimeAdministrationResources";
            if (input.equals("PORT")) value = "161";
            }
        return value;


           //<End_getParameter_String>
    }

    public void init(java.applet.Applet app)
	{
		this.applet = app;
		init();
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
        setPreferredSize(new Dimension(getPreferredSize().width+574,getPreferredSize().height+395));
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
        // let us set the initialized variable to true so
        // we dont initialize again even if init is called
        initialized = true;


         //<End_init>

          // This is done as part of Trim and pack to remove the unwanted entries from the protocol Screen -- Kamalg

        String[] proto = {"SNMP","ICMP","NativePing","TL1"};

        if(storeVec.size() > 0 )
        {
            for(int i = 0 ; i < storeVec.size() ; i++ )
            {

                datamodel.addElement(resourceBundle.getString((String)storeVec.elementAt(i)));
            }

        }
        else
        {
            for(int i=0 ; i < proto.length;i++)
            {
                String protoKey = proto[i];
                if(protoVec == null || !protoVec.contains(protoKey))
                {
                    storeVec.addElement(protoKey);
                    datamodel.addElement(resourceBundle.getString(protoKey));
                }

            }
        }


        /*datamodel.addElement(resourceBundle.getString("SNMP"));
          datamodel.addElement(resourceBundle.getString("ICMP"));
          datamodel.addElement(resourceBundle.getString("NativePing"));
          datamodel.addElement(resourceBundle.getString("TL1"));
          datamodel.addElement(resourceBundle.getString("XML")); */
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

    public void mousePressed(MouseEvent e)
    {
    }
    public void mouseReleased(MouseEvent e)
    {
    }
    public void mouseEntered(MouseEvent e)
    {
    }
    public void mouseExited(MouseEvent e)
    {
    }

    public void mouseClicked(MouseEvent e)
    {
        if(((index = listProtoScr.getSelectedIndex())== -1) && (e.getSource()== bProperties))
        {
            JOptionPane.showMessageDialog(this,resourceBundle.getString("Please select an item listed above."),resourceBundle.getString("Information"),JOptionPane.INFORMATION_MESSAGE);
        }
        else if (((index = listProtoScr.getSelectedIndex())>= 0) && (e.getSource()== bProperties))
        {
            String protocolName = (String)storeVec .elementAt(index);
            if(protocolName != null)
            {
                setProtocolVisible(protocolName);
            }
        }
    }


    public void setProtocolVisible(String proto)
    {
        if(proto == null )
            return;

        if( proto.equalsIgnoreCase("SNMP"))
        {
            snmpScr.setVisible(true);
        }
        else if(proto.equalsIgnoreCase("ICMP"))
        {
                icmpScr.setVisible(true);
        }
        else if(proto.equalsIgnoreCase("NativePing"))
        {
            nativePingScr.setVisible(true);
        }
        else if(proto.equalsIgnoreCase("TL1"))
        {

            com.adventnet.nms.runtimeconfig.TL1MainScreen mscr = new com.adventnet.nms.runtimeconfig.TL1MainScreen(MainScreen.frame);
            mscr.isClient = MainScreen.isClient;
            mscr.init(applet) ;
            if(TL1MainScreen.isTL1)
            {
                mscr.setVisible(true);
            }
            else
            {
                mscr.setVisible(false);
            }

        }
    }

 	public void actionPerformed(ActionEvent ae)
    {
        if(((index = listProtoScr.getSelectedIndex())== -1) && (ae.getSource()== bProperties))
        {
            JOptionPane.showMessageDialog(this,resourceBundle.getString("Please select an item listed above."),resourceBundle.getString("Information"),JOptionPane.INFORMATION_MESSAGE);
        }
        else if (((index = listProtoScr.getSelectedIndex())>= 0) && (ae.getSource()== bProperties))
        {
            String protocolName = (String)storeVec .elementAt(index);
            if(protocolName != null)
            {
                setProtocolVisible(protocolName);
            }
        }


    }

    public void setProperties(java.util.Properties props)
    {
        //<Begin_setProperties_java.util.Properties>


  //<End_setProperties_java.util.Properties>
    }
//added for RTA SPP fix

  public void dispose()
   {
           listProtoScr.removeMouseListener(this);
           bProperties.removeActionListener(this);
           listProtoScr=null;
           bProperties=null;
           datamodel =null;
           snmpScr = null;
           icmpScr = null;
           nativePingScr = null;
  }
}




























