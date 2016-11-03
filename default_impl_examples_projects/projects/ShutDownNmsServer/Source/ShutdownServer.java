/* $Id: */
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.launcher.nms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import com.adventnet.nms.util.StandAloneShutDown;

public class ShutdownServer extends JFrame implements ActionListener
{
    //<Begin_Variable_Declarations>
    private boolean initialized = false;
    private java.applet.Applet applet = null;
    private String localePropertiesFileName = "ShutDownNmsServerResources";
    static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
    private boolean running=false;
    javax.swing.JPanel Top = null;
    javax.swing.JPanel butPanel = null;
    private javax.swing.JButton okButton = null;
    private javax.swing.JButton cancelButton = null;
    javax.swing.JPanel mainPanel = null;
    private javax.swing.JTextField tfHost = null;
    private javax.swing.JLabel lfHost = null;
    private javax.swing.JLabel lfUser = null;
    private javax.swing.JLabel lfPassword = null;
    private javax.swing.JTextField tfUser = null;
    private javax.swing.JPasswordField tfPassword = null;
    private javax.swing.JButton settingsButton = null;
    private javax.swing.JLabel lfEmpty2 = null;
    javax.swing.JLabel lfEmpty1 = null;
    javax.swing.JLabel lfEmpty3 = null;
    GridBagConstraints cons = new GridBagConstraints();
    Insets inset;
    //<End_Variable_Declarations>
    protected String webportValue = "9090"; //default value
    protected String rmiportValue = "1099"; //default value
    protected String tcpportValue = "2000"; //default value
    private static boolean isMain = false;

    private boolean closeonexit = true;
    private boolean shutdown_status = false;

    private ShutdownSettings shDownSet = null;

    public ShutdownServer(String[] cargs,boolean closeonexit)
    {
        this.closeonexit = closeonexit;
        if(!isMain)
        {
            com.adventnet.apiutils.Utility.setParameter("RESOURCE_PROPERTIES",localePropertiesFileName);
            com.adventnet.apiutils.Utility.setParameter("NMS_RESOURCE_DIRECTORY","./html");

            com.adventnet.apiutils.Utility.setParameter("RESOURCE_LOCALE",java.util.Locale.getDefault().toString());
        }
        initialize(cargs);
    }

    private void initialize(String[] args)
    {
        init();
        /*
        if(args.length >= 16)
        {
            tfHost.setText(args[1]);
            webportValue=args[3];
            rmiportValue=args[5];
            tcpportValue=args[7];
            tfUser.setText(args[9]);
            tfPassword.setText("");
        }
        */
        for(int i = 0; i<args.length; i++)
        {
            if(args[i].equals("-HOST_NAME"))
            {
                tfHost.setText(args[i+1]);
                i++;
            }

            else if(args[i].equals("-WEB_SERVER_PORT"))
            {
                webportValue=args[i+1];
                i++;
            }
            else if(args[i].equals("-RMI_PORT"))
            {
                rmiportValue=args[i+1];
                i++;
            }
            else if(args[i].equals("-TCP_PORT"))
            {
                tcpportValue=args[i+1];
                i++;
            }
            else if(args[i].equals("-USER_NAME"))
            {
                 tfUser.setText(args[i+1]);
                i++;
            }
        }
        tfPassword.setText("");
    }

    public void check()
    {

	String text1 = tfHost.getText();
	String text2 = tfUser.getText();
	String text3 = tfPassword.getText();


	if((text1.length() != 0) && (text2.length() != 0) && (text3.length()!=0))
        {
	    ShutdownThread st = new ShutdownThread(this);
            st.start();
        }
        else
        {
            JOptionPane.showMessageDialog(this,resourceBundle.getString("Please enter valid values"),resourceBundle.getString("Shutdown Authentication"),JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        if(source == okButton || source == tfPassword)
        {
            check();
        }
        else  if (source == settingsButton)
        {
            if(shDownSet == null)
            {
                shDownSet = new ShutdownSettings(this);
            }

            shDownSet.setValues(webportValue,rmiportValue,tcpportValue);
            shDownSet.setVisible(true);

        }
        else if (source == cancelButton)
        {
            //Note: calling dispose is required otherwise garbage will
            //      not be collected if invoked from the Launcher
            //      Shutdown Interface.

            this.dispose();
            this.setVisible(false);

            if(closeonexit)
            {
                System.exit(0);
            }

        }
    }



    public boolean getShutdownStatus()
    {
	return shutdown_status;
    }

    public void setPortValues(String wvalue,String rvalue,String tvalue)
    {
	webportValue = wvalue;
	rmiportValue = rvalue;
	tcpportValue = tvalue;
    }

    public void enableButtons()
    {

        tfHost.setEnabled(true);
        tfUser.setEnabled(true);
        tfPassword.setEnabled(true);
        settingsButton.setEnabled(true);
        okButton.setEnabled(true);
        cancelButton.setEnabled(true);
    }

    public void disableButtons()
    {

        tfHost.setEnabled(false);
        tfUser.setEnabled(false);
        tfPassword.setEnabled(false);
        settingsButton.setEnabled(false);
        okButton.setEnabled(false);
        cancelButton.setEnabled(false);
    }


    class ShutdownThread extends Thread
    {
	ShutdownServer frame = null;
	ShutdownThread(ShutdownServer frame)
	{
	    this.frame=frame;
	}
	public void run()
	{
	    //windowListener_enabled = false;
	    frame.disableButtons();
            try
            {
                StandAloneShutDown sh = new StandAloneShutDown();
                sh.shutDownServer(tfHost.getText().trim(),webportValue,rmiportValue,tcpportValue,tfUser.getText().trim(),tfPassword.getText().trim());
		if(!StandAloneShutDown.shutdownStatus)
		{
			JOptionPane.showMessageDialog(frame,resourceBundle.getString("javaui.shutdown.error.rmiconnection"),resourceBundle.getString("javaui.shutdown.error.title"),JOptionPane.ERROR_MESSAGE);
			frame.setVisible(true);
			shutdown_status = false;
			frame.enableButtons();
			StandAloneShutDown.shutdownStatus = true;
		}
		else
		{
                	JOptionPane.showMessageDialog(frame,resourceBundle.getString("Web NMS Server successfully shutdown"),resourceBundle.getString("Shutdown Status"),JOptionPane.INFORMATION_MESSAGE);

	                frame.setVisible(false);

        	        shutdown_status = true;

                	//Note: Ideally we should not call System.exit
	                //in this line. But currently no other way.

        	        if(closeonexit)
                	{
	                    System.exit(0);
        	        }
		}
            }
            catch(java.net.UnknownHostException UHExcep)
            {
                JOptionPane.showMessageDialog(frame,resourceBundle.getString("javaui.shutdown.error.invalidhost"),resourceBundle.getString("javaui.shutdown.error.title"),JOptionPane.ERROR_MESSAGE);
                 frame.setVisible(true);
                 shutdown_status = false;
                 frame.enableButtons();
            }
            catch(java.rmi.RemoteException rmiExcep)
            {
                JOptionPane.showMessageDialog(frame,resourceBundle.getString("javaui.shutdown.error.rmiconnection"),resourceBundle.getString("javaui.shutdown.error.title"),JOptionPane.ERROR_MESSAGE);
                frame.setVisible(true);
                shutdown_status = false;
                frame.enableButtons();
            }
            catch(java.net.ConnectException connectExcep)
            {
                JOptionPane.showMessageDialog(frame,resourceBundle.getString("javaui.shutdown.error.connection"),resourceBundle.getString("javaui.shutdown.error.title"),JOptionPane.ERROR_MESSAGE);
                frame.setVisible(true);
                shutdown_status = false;
                frame.enableButtons();
            }            
            catch(Exception exception)
            {
                String message = exception.getMessage();
                int len = 40;
                int tol = 5;
                String[] messg = new String[message.length()/len + 1];
                int intmessg = 0;
                while(message.length() > len)
                {
                    String str1 = message.substring(0,len);
                    String str2 = message.substring(len);
                    if(str2.indexOf(" ") > 5)
                    {
                        int lspace = str1.lastIndexOf(" ");
                        String st1 = str1.substring(0, lspace);
                        String st2 = str1.substring(lspace);
                        str1 = st1;
                        str2 = st2 + str2;
                    }
                    else
                    {
                        int l = str2.indexOf(" ");
                        String st1 = str2.substring(0,l);
                        String st2 = str2.substring(l);
                        str1 = str1 + st1;
                        str2 = st2;
                    }
                    messg[intmessg] = str1;
                    message = str2.trim();
                    intmessg++;
                }
                if(message.length() > 0)
                {
                    messg[intmessg] = message;
                }
                 JOptionPane.showMessageDialog(frame,messg,resourceBundle.getString("javaui.shutdown.error.title"),JOptionPane.ERROR_MESSAGE);
                 frame.setVisible(true);
                 shutdown_status = false;
                 frame.enableButtons();
            }
            //windowListener_enabled=true;
            frame.enableButtons();
	}
    }

    public static void main(String args[])
    {
        String[] argName = new String[3];
        isMain=true;
        for(int i=0,j=0;i<args.length;i++)
        {
            if(args[i].trim().equals("-NMS_RESOURCE_DIRECTORY")|| args[i].trim().equals("-RESOURCE_PROPERTIES") || args[i].trim().equals("-RESOURCE_LOCALE"))
            {
                argName[j] = args[i].substring(1);
                i++;
                j++;
            }
        }
        com.adventnet.apiutils.Utility.parseAndSetParameters(argName,args);

        String  HOST_NAME = "";
        String webportValue ="";
        String  rmiportValue = "";
        String  tcpportValue = "";
        String  USER_NAME = "";
        String PASSWORD = "";
        boolean femode = false;
        /*

        for(int i = 0; i<args.length; i++)
        {
            if(args[i].equals("-femode"))
            {
                femode=true;
            }
            else if(args[i].equals("-h"))
            {
                HOST_NAME=args[i+1];
                i++;
            }
            else if(args[i].equals("-wp"))
            {
                webportValue=args[i+1];
                i++;
            }
            else if(args[i].equals("-rp"))
            {
                rmiportValue=args[i+1];
                i++;
            }
            else if(args[i].equals("-tp"))
            {
                tcpportValue=args[i+1];
                i++;
            }
            else if(args[i].equals("-u"))
            {
                USER_NAME=args[i+1];
                i++;
            }
            else if(args[i].equals("-p"))
            {
                PASSWORD=args[i+1];
            }
        }
        */

        if((USER_NAME.equals(""))||(PASSWORD.equals("")))
        {
            try
            {
                JFrame jframe = new JFrame();
            }
            catch (Throwable throwable)
            {
                System.out.println("Error:Could not instantiate X - Windows");
                System.out.println("Use the Command line arguments: [USERNAME][PASSWORD] to run");
                System.exit(1);
            }
        }

        else
        {
            try
            {
                StandAloneShutDown sh = new StandAloneShutDown();
                sh.shutDownServer(HOST_NAME,webportValue,rmiportValue,tcpportValue,USER_NAME,PASSWORD);
            }
            catch (Exception exp)
            {
                System.out.println(exp);

            }
	}

        String newargs[] = {HOST_NAME,webportValue,rmiportValue,tcpportValue,USER_NAME,PASSWORD};
        ShutdownServer s = new ShutdownServer(args,true);
        s.setVisible(true);
        s.setFocusOnPasswordField();
        s.addWindowListener( new WindowAdapter()
            {
                public void windowClosing( WindowEvent we)
                {
                    System.exit(0);
                }
            });
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
	if(getParameter("RESOURCE_PROPERTIES" ) != null)
	{
            localePropertiesFileName = getParameter("RESOURCE_PROPERTIES");
	}
	resourceBundle = com.adventnet.apiutils.Utility.getBundle(localePropertiesFileName,getParameter("RESOURCE_LOCALE"),applet);
        if (initialized == true) return;
        this.setSize(getPreferredSize().width+426,getPreferredSize().height+217);
        setTitle("Shutdown Web NMS Server");
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
        // let us set the initialzed variable to true so
        // we dont initialize again even if init is called
        initialized = true;



        //<End_init>
        setTitle(resourceBundle.getString("Shutdown Web NMS Server"));

        this.setSize(450,230);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension d = kit.getScreenSize();
        this.setLocation((d.width-450)/2,(d.height-230)/2);
	Image image = kit.getImage(System.getProperty("user.dir")+File.separator+"images"+File.separator+"adventneticon.jpg");
        this.setIconImage(image);
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
            if (input.equals("MS_MODE")) value = "3";
            if (input.equals("RESOURCE_PROPERTIES")) value = "ShutDownNmsServerResources";
        }
        return value;


        //<End_getParameter_String>
    }
    public void setUpProperties()
    {
        //<Begin_setUpProperties>

        try
        {
            butPanel.setBorder(new javax.swing.border.EtchedBorder(1));
        }
        catch(Exception ex)
        {
            showStatus(resourceBundle.getString("Exception while setting properties for bean ")+butPanel,ex);
        }

        try
        {
            okButton.setBorder(new javax.swing.border.BevelBorder(0));
            okButton.setLabel(resourceBundle.getString("OK"));
            okButton.setHorizontalTextPosition(0);
            okButton.setMinimumSize(new Dimension(73,27));
            okButton.setPreferredSize(new Dimension(73,27));
            okButton.setFont(new Font("Dialog",1,12));
            okButton.setMaximumSize(new Dimension(73,27));
        }
        catch(Exception ex)
        {
            showStatus(resourceBundle.getString("Exception while setting properties for bean ")+okButton,ex);
        }

        try
        {
            cancelButton.setBorder(new javax.swing.border.BevelBorder(0));
            cancelButton.setHorizontalTextPosition(0);
            cancelButton.setLabel(resourceBundle.getString("Cancel"));
            cancelButton.setMaximumSize(new Dimension(73,27));
            cancelButton.setMinimumSize(new Dimension(73,27));
            cancelButton.setFont(new Font("Dialog",1,12));
            cancelButton.setPreferredSize(new Dimension(73,27));
        }
        catch(Exception ex)
        {
            showStatus(resourceBundle.getString("Exception while setting properties for bean ")+cancelButton,ex);
        }

        try
        {
            mainPanel.setBorder(new javax.swing.border.EtchedBorder(1));
        }
        catch(Exception ex)
        {
            showStatus(resourceBundle.getString("Exception while setting properties for bean ")+mainPanel,ex);
        }

        try
        {
            tfHost.setHorizontalAlignment(2);
            tfHost.setFont(new Font("SansSerif",0,12));
            tfHost.setBorder(new javax.swing.border.BevelBorder(1));
        }
        catch(Exception ex)
        {
            showStatus(resourceBundle.getString("Exception while setting properties for bean ")+tfHost,ex);
        }

        try
        {
            lfHost.setHorizontalAlignment(2);
            lfHost.setForeground(new Color(-16777216));
            lfHost.setHorizontalTextPosition(4);
            lfHost.setText(resourceBundle.getString("Host Name"));
            lfHost.setFont(new Font("Dialog",1,12));
        }
        catch(Exception ex)
        {
            showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfHost,ex);
        }

        try
        {
            lfUser.setHorizontalAlignment(2);
            lfUser.setForeground(new Color(-16777216));
            lfUser.setHorizontalTextPosition(4);
            lfUser.setFont(new Font("Dialog",1,12));
            lfUser.setText(resourceBundle.getString("User Name"));
        }
        catch(Exception ex)
        {
            showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfUser,ex);
        }

        try
        {
            lfPassword.setHorizontalAlignment(2);
            lfPassword.setForeground(new Color(-16777216));
            lfPassword.setHorizontalTextPosition(4);
            lfPassword.setText(resourceBundle.getString("Password"));
            lfPassword.setFont(new Font("Dialog",1,12));
        }
        catch(Exception ex)
        {
            showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfPassword,ex);
        }

        try
        {
            tfUser.setHorizontalAlignment(2);
            tfUser.setFont(new Font("SansSerif",0,12));
            tfUser.setBorder(new javax.swing.border.BevelBorder(1));
        }
        catch(Exception ex)
        {
            showStatus(resourceBundle.getString("Exception while setting properties for bean ")+tfUser,ex);
        }

        try
        {
            tfPassword.setHorizontalAlignment(2);
            tfPassword.setFont(new Font("SansSerif",0,12));
            tfPassword.setBorder(new javax.swing.border.BevelBorder(1));
        }
        catch(Exception ex)
        {
            showStatus(resourceBundle.getString("Exception while setting properties for bean ")+tfPassword,ex);
        }

        try
        {
            settingsButton.setBorder(new javax.swing.border.BevelBorder(0));
            settingsButton.setHorizontalTextPosition(0);
            settingsButton.setFont(new Font("Dialog",1,12));
            settingsButton.setMaximumSize(new Dimension(80,27));
            settingsButton.setMinimumSize(new Dimension(80,27));
            settingsButton.setPreferredSize(new Dimension(80,27));
            settingsButton.setText(resourceBundle.getString("Settings"));
        }
        catch(Exception ex)
        {
            showStatus(resourceBundle.getString("Exception while setting properties for bean ")+settingsButton,ex);
        }

        try
        {
            lfEmpty2.setHorizontalAlignment(2);
            lfEmpty2.setFont(new Font("SansSerif",0,12));
            lfEmpty2.setForeground(new Color(-16777216));
            lfEmpty2.setHorizontalTextPosition(4);
        }
        catch(Exception ex)
        {
            showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfEmpty2,ex);
        }

        try
        {
            lfEmpty1.setHorizontalAlignment(2);
            lfEmpty1.setFont(new Font("SansSerif",0,12));
            lfEmpty1.setForeground(new Color(-16777216));
            lfEmpty1.setHorizontalTextPosition(4);
        }
        catch(Exception ex)
        {
            showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfEmpty1,ex);
        }

        try
        {
            lfEmpty3.setHorizontalAlignment(2);
            lfEmpty3.setFont(new Font("SansSerif",0,12));
            lfEmpty3.setForeground(new Color(-16777216));
            lfEmpty3.setHorizontalTextPosition(4);
        }
        catch(Exception ex)
        {
            showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lfEmpty3,ex);
        }


        //<End_setUpProperties>
    }
    public void start()
    {
        //<Begin_start>
        if(running)
            return;
        running=true;


        //<End_start>
    }
    public void stop()
    {
        //<Begin_stop>
        if(!running)
            return;
        running=false;


        //<End_stop>
    }
    public void initVariables()
    {
        //<Begin_initVariables>
        Top= new javax.swing.JPanel();
        butPanel= new javax.swing.JPanel();
        okButton= new javax.swing.JButton();
        cancelButton= new javax.swing.JButton();
        mainPanel= new javax.swing.JPanel();
        tfHost= new javax.swing.JTextField();
        lfHost= new javax.swing.JLabel();
        lfUser= new javax.swing.JLabel();
        lfPassword= new javax.swing.JLabel();
        tfUser= new javax.swing.JTextField();
        tfPassword= new javax.swing.JPasswordField();
        settingsButton= new javax.swing.JButton();
        lfEmpty2= new javax.swing.JLabel();
        lfEmpty1= new javax.swing.JLabel();
        lfEmpty3= new javax.swing.JLabel();


        //<End_initVariables>
    }
    public void setUpGUI(Container container)
    {
        //<Begin_setUpGUI_Container>
        container.add(Top,BorderLayout.CENTER);
        Top.setLayout(new BorderLayout(5,5));
        Top.add(butPanel,BorderLayout.SOUTH);
        butPanel.setLayout(new FlowLayout(1,15,10));
        butPanel.add(okButton);
        butPanel.add(cancelButton);
        Top.add(mainPanel,BorderLayout.CENTER);
        mainPanel.setLayout(new GridBagLayout());
        inset = new Insets(5,5,5,5);
        setConstraints(1,1,0,1,0.1,0.0,cons.EAST,cons.BOTH,inset,0,0);
        mainPanel.add(tfHost,cons);
        inset = new Insets(5,5,5,5);
        setConstraints(0,1,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
        mainPanel.add(lfHost,cons);
        inset = new Insets(5,5,5,5);
        setConstraints(0,2,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
        mainPanel.add(lfUser,cons);
        inset = new Insets(5,5,5,5);
        setConstraints(0,3,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
        mainPanel.add(lfPassword,cons);
        inset = new Insets(5,5,5,5);
        setConstraints(1,2,0,1,0.1,0.0,cons.EAST,cons.BOTH,inset,0,0);
        mainPanel.add(tfUser,cons);
        inset = new Insets(5,5,5,5);
        setConstraints(1,3,0,1,0.1,0.0,cons.EAST,cons.BOTH,inset,0,0);
        mainPanel.add(tfPassword,cons);
        inset = new Insets(10,5,5,5);
        setConstraints(1,4,1,1,0.0,0.0,cons.EAST,cons.NONE,inset,0,0);
        mainPanel.add(settingsButton,cons);
        inset = new Insets(10,5,5,5);
        setConstraints(0,4,1,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
        mainPanel.add(lfEmpty2,cons);
        inset = new Insets(5,5,5,5);
        setConstraints(0,0,0,1,0.0,0.0,cons.WEST,cons.BOTH,inset,0,0);
        mainPanel.add(lfEmpty1,cons);
        inset = new Insets(5,5,5,5);
        setConstraints(0,5,0,0,0.1,0.1,cons.WEST,cons.BOTH,inset,0,0);
        mainPanel.add(lfEmpty3,cons);


        //<End_setUpGUI_Container>
        tfPassword.addActionListener(this);
        settingsButton.addActionListener(this);
        okButton.addActionListener(this);
        cancelButton.addActionListener(this);
    }

    public void setFocusOnPasswordField()
    {
        tfPassword.requestFocus();
    }

    public void setUpConnections()
    {
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




    public ShutdownServer()
    {
        //<Begin_ShutdownServer>
        pack();

        //<End_ShutdownServer>


    }

    public ShutdownServer(java.applet.Applet applet)
    {
        //<Begin_ShutdownServer_java.applet.Applet>
        this.applet = applet;
        pack();
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        //<End_ShutdownServer_java.applet.Applet>
    }
}













































