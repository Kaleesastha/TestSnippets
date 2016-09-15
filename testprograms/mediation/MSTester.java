/**

$Id: MSTester.java,v 1.1 2003/06/04 12:52:17 elangovan Exp $

File Name      : MSTester.java
Description    : It is an UI program having most of the demands of the testplans in the 2-tier and 		3-tier services.
Other Info     : This program is a more generalised version to validate most of the server side 		testcases in Mediation and MS testplans.
USAGE          : select the required fields in the options given and type the necessay fields and 		look for your answers in the textbox given below.
 
Things to do before running this program:
	1.Make sure that this program(MSTeseter.java) is stored in the <WEBNMS_HOME> directory.
	2.Have the following jar files in the CLASSPATH( ManagementServer.jar, 
	ManagementClient.jar,AdventNetJta.jar,AdventNetTL1.jar,AdventNetSNMP.jar,AdventNetCLI.jar)
	3.Set mibs to be loaded in this file.
	4.If it is a 3-tier service, then make sure that the Managedobject.xml is run before this 	MSTester.java is running.
	5.Compile the file and run as java -DMS_ROOT_DIR=../ test.ms.snmp.sync.MSTester
	6.In the textfield type any machine which is running at portno:161(this port number can be 	changed, by making changes in this program.
	7.once everything is set correctly we can get the response at the textfield given.

Owner Name     : G.Elangovan	

Change History	: G.Elangovan		03-06-2003		Initial version 

**/



package test.ms.snmp.sync;
import java.lang.*;
import java.util.*;
import java.net.*;
import java.lang.reflect.*;
import javax.swing.*;
import javax.swing.ButtonGroup;
import java.awt.*;
import java.awt.event.*;
//import test.ms.snmp.SnmpTest;
import java.util.Vector;
import java.io.*;
import java.applet.*;
import com.adventnet.management.*;
import com.adventnet.management.xml.*;
import com.adventnet.management.snmp.*;
import com.adventnet.management.client.ClientProperty;
import com.adventnet.management.tl1.*;
import com.adventnet.management.client.xml.*;
import com.adventnet.management.mstransport.*;
import com.adventnet.management.managedobject.*;
import com.adventnet.management.managedobject.MethodWrapper;

import com.adventnet.management.client.ManagementClient;
import com.adventnet.management.client.ClientProperty;
import com.adventnet.management.cli.CLIProperty;

public class  MSTester extends Applet implements MSEventListener,ProtocolListener,ExceptionListener{ 
 
    public  static Property sp;
    public  static ManagementServices ms = null;
    static ManagementServerResultEvent msre=null;
    static MethodWrapper mw =null;
    static  ManagedObjectProperty mprop =null;
    static  SnmpPerfTest spt=null;
    //modes
    public  static int msMode = 2;
    public  static String protocolMode = "ps";
    public static String communicationMode="sync";
    public static String protocol="SNMP";

    //host port
    public static String sessionId=null;
    public  static  String host = "localhost";
    public  static  String oid  = "1.1.0";

    static  String oids[] = {"1.5.0","1.4.0"};
    static  String value[] ={"hai","advent"};
    public static String serverhost="localhost";
    public static String serverport="9001";

    //panel
    static JPanel panel =null;
    //frame
    static JFrame frame = null;
    static JFrame framecheck=null;
    //radiobuttons
    static JRadioButton rbutton1=null;
    static JRadioButton rbutton2=null;
    static JRadioButton rbutton3=null;
    static JRadioButton rbutton4=null;
    static JRadioButton rbutton5=null;
    static JRadioButton rbutton6=null;
    static JRadioButton rbutton7=null;
    static JRadioButton rbutton8=null;
    static FileWriter writer=null;

    //combobox
    static JComboBox cb=null;
    static JComboBox cb1=null;

    //buttongroups
    static ButtonGroup bg1=null;
    static ButtonGroup bg2=null;
    static ButtonGroup bg3=null;
    static ButtonGroup bg4=null;

    //radio buttons.
    static JRadioButtonMenuItem jrbmt=null;

    //buttons
    static JButton unreg=null;
    static  JButton reg=null;
    static  JButton unrubber=null;
    static  JButton serverstart=null;
    static JButton performance=null;
    static JButton provperformance=null;
    static JButton check=null;

    //textarea
    static  JTextArea textarea=null;

    //textfield
    static  JTextField textfield=null;
    static JTextField textfield1=null;

    //flags    
    static int clientflag=0;
    static int l=0;
     int perf=1;
    static long begin=0;
    static int c=0;
    static double r=0.0;
    static int flag=0;
    static  boolean set=true;
    static boolean list=false;
    static boolean pflag=true;
    static boolean perfflag=false;

    static Applet applet;

    //method that will add all menu items  to frame and act according to the event fired
    //thro event listener

    public static  void client(){
        spt=new SnmpPerfTest();
        if(clientflag==0)
        {
            panel=new JPanel();
            try{
            FileWriter writer=new FileWriter("MSTesterLog.txt");
            }
            catch(Exception e)
            {
                System.out.println(" the exception is "+e);
            }
          
            JLabel oid1 = new JLabel("targetOID");

          //radiobuttons
            rbutton1=new JRadioButton("applet");
            rbutton1.setActionCommand("applet");
            rbutton2=new JRadioButton("appln");
            rbutton2.setActionCommand("appln");
            bg1=new ButtonGroup();
            bg1.add(rbutton1);
            bg1.add(rbutton2);


            rbutton3=new JRadioButton("sync");
            rbutton3.setActionCommand("sync");
            rbutton4=new JRadioButton("async");
            rbutton4.setActionCommand("async");
            bg2=new ButtonGroup();
            bg2.add(rbutton3);
            bg2.add(rbutton4);


            rbutton5=new JRadioButton("pn");
            rbutton5.setActionCommand("pn");
            rbutton6=new JRadioButton("ps");
            rbutton6.setActionCommand("ps");
            bg3=new ButtonGroup();
            bg3.add(rbutton5);
            bg3.add(rbutton6);



            rbutton7=new JRadioButton("two");
            rbutton7.setActionCommand("2");
            rbutton8=new JRadioButton("three");
            rbutton8.setActionCommand("3");
            bg4=new ButtonGroup();
            bg4.add(rbutton7);
            bg4.add(rbutton8);


            //adding a listener to radio buttons

            RadioListener myListener = new RadioListener();
            rbutton1.addActionListener(myListener);
            rbutton2.addActionListener(myListener);
            rbutton3.addActionListener(myListener);
            rbutton4.addActionListener(myListener);
            rbutton5.addActionListener(myListener);
            rbutton6.addActionListener(myListener);
            rbutton7.addActionListener(myListener);
            rbutton8.addActionListener(myListener);


            //textfield
       
            String[] str={"&1.1.0 %localhost $Command","&1.1.0 %localhost $Table","&1.1.0 %localhost $ColumnWalk","&1.1.0 &1.5.0  %localhost $Command","&1.4.0 &1.5.0 %localhost $Set"};

            cb=new JComboBox(str);
            cb.setEditable(set);

            //combobox for protocol selection.

            String[] pselection={"SNMP","TL1","CLI","MO"};

            cb1=new JComboBox(pselection);
            cb1.setEditable(set);

            //buttons
            unreg = new JButton("reg-client");
            unreg.setActionCommand("reg");
            reg=new JButton("reg-Event");
            reg.setActionCommand("reg");
            ImageIcon ii=new ImageIcon("u.gif");String args[])
    {
        if (args.length < 2 )
        {
            System.out.println(" Usage : javac  <targethost> <oid>");
        }
        
        getargs(args);
        if(args.length > 2)
        {
            ImageIcon iii=new ImageIcon("E.gif");
          unrubber = new JButton("C");
          unrubber.setToolTipText("click this button to erase the content of result textarea");
            serverstart=new JButton("Server");
            serverstart.setToolTipText("click this button to start the server");
            performance=new JButton("P-D");
            performance.setToolTipText("click to know the performance rate os MS with dummy provider");
           provperformance=new JButton("P-S");
            provperformance.setToolTipText("click to know the performance rate os MS with snmp provider");
   check=new JButton("C-L");
 check.setToolTipText("click to have the checklist for testing ms");

            //Jtextfield
            textfield=new JTextField("localhost",7);
            textfield1=new JTextField("9001",7);

            //textarea
            Color color =new Color(230,206,185);//130,196,198//230,196,185
            textarea= new JTextArea(25,50);
            int v=ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
            int h=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
            JScrollPane jsp=new JScrollPane(textarea,v,h);
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx=0; gbc.gridy=0;
            textarea.setFont(new Font("SansSerif",Font.BOLD,12));
            // textarea.setFontcolor("black");
            textarea.setEditable(true);
            textarea.setLineWrap(true);
            textarea.setBackground(color);

            //frame

            frame = new JFrame("MSTester - ManagementServer TestSuite");
            frame.setSize(680,600);
            //            frame.setBackground(color);
            frame.getContentPane().setLayout(new FlowLayout());
      
            //   frame.getContentPane().setLayout(new BorderLayout());
            //       frame.getContentPane().setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
            //intergrate them all with frame

            JLabel valuetosetproto = new JLabel(" protocol");

            frame.getContentPane().add(valuetosetproto);
            frame.getContentPane().add(cb1);
            JLabel valuetosetproto1 = new JLabel("");
            frame.getContentPane().add(valuetosetproto1);



            JLabel valuetoset6 = new JLabel("                  ");
            JLabel valuetoset1 = new JLabel("applet or appln");
            frame.getContentPane().add(valuetoset1);


         
            frame.getContentPane().add(valuetoset6);
            frame.getContentPane().add(rbutton1);
            frame.getContentPane().add(rbutton2);
            frame.getContentPane().add(valuetoset6);


            JLabel valuetoset2 = new JLabel("communication mode");
            frame.getContentPane().add(valuetoset2);
            frame.getContentPane().add(rbutton3);
            frame.getContentPane().add(rbutton4);


            JLabel valuetoset3 = new JLabel("protocolmode");
            frame.getContentPane().add(valuetoset3);
            frame.getContentPane().add(rbutton5);
            frame.getContentPane().add(rbutton6);


            JLabel valuetoset4 = new JLabel("msmode");
            frame.getContentPane().add(valuetoset4);
            frame.getContentPane().add(rbutton7);
            frame.getContentPane().add(rbutton8);

            JLabel valuetoset5 = new JLabel("textfield");

            frame.getContentPane().add(valuetoset5);
            frame.getContentPane().add(cb);
            frame.getContentPane().add(reg);
            frame.getContentPane().add(unreg);
            frame.getContentPane().add(valuetoset6);

            JLabel text = new JLabel("serverhost");
            frame.getContentPane().add(text);
            frame.getContentPane().add(textfield);
            JLabel textp = new JLabel("serverport");
            frame.getContentPane().add(textp);
            frame.getContentPane().add(textfield1);
      
 

            JLabel valuetoset7 = new JLabel("****************Result****************");
            frame.getContentPane().add(valuetoset7);
            frame.getContentPane().add(unrubber);
            frame.getContentPane().add(serverstart);
            frame.getContentPane().add(performance);
            frame.getContentPane().add(provperformance);
            frame.getContentPane().add(check);

            frame.getContentPane().add(jsp);
            // frame.setBackground(color);
            frame.setVisible(true);

          
        }
        //        frame.getContentPane().add(textarea);

        clientflag=1;





        performance.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent ae)
                {
                    System.out.println(" the friend");
                   
                    String[] ar={"kamal","king"};
                    boolean per=true;
                    int k=spt.mainthink(ar,per);
                    System.out.println(" end of perf");

                    /*     try{
                       
                           Process process=(Runtime.getRuntime()).exec("java test.ms.snmp.sync.SnmpPerfTest");
                           BufferedReader reader=new BufferedReader(new InputStreamReader(process.getInputStream()));
                         
                           InputStream ops=process.getInputStream();
                           while((reader.readLine())!=null)
                         {
                             String res=reader.readLine();
                             System.out.println("the res"+res); 
                             textarea.setText(res);
                         }
                       
                         process.destroy();             
                       
                   
                         //  action("SnmpSend");
                    }
                    catch(Exception er)
                    {
                    System.out.println(" the exception is "+er);
                        }*/
                            }
            });




        provperformance.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent ae)
                {
                    System.out.println(" the friend");
                   
                    String[] ar={"kamal","king"};
                    boolean per=false;
                    int k=spt.mainthink(ar,per);
               

                    /*     try{
                       
                           Process process=(Runtime.getRuntime()).exec("java test.ms.snmp.sync.SnmpPerfTest");
                           BufferedReader reader=new BufferedReader(new InputStreamReader(process.getInputStream()));
                         
                           InputStream ops=process.getInputStream();
                           while((reader.readLine())!=null)
                         {
                             String res=reader.readLine();
                             System.out.println("the res"+res); 
                             textarea.setText(res);
                         }
                       
                         process.destroy();             
                       
                   
                         //  action("SnmpSend");
                    }
                    catch(Exception er)
                    {
                    System.out.println(" the exception is "+er);
                        }*/
                            }
            });


                   
                   



        serverstart.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent ae)

                {
                    try{
                        //  String[] serverpar={"java com.adventnet.management.xml.MSXmlProcessor","-debug","3"};
                         Process process=(Runtime.getRuntime()).exec("java com.adventnet.management.xml.MSXmlProcessor");
                        //                  Process process=(Runtime.getRuntime()).exec("cmd /c \"\"LinkDownV1.bat");
                     
                    }catch(Exception e)
                    {
                        System.out.println(" the exception is "+e);
                    }
                    textarea.append("server is started");                   

                 
                }
            });


        unrubber.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent ae)

                {
                    textarea.setText("");
                
                }
            });

        textfield.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent ae)

                {
                    serverhost=textfield.getText();
                }
            });

        textfield1.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent ae)

                {
                    serverport=textfield1.getText();
                    System.out.println(" the port is "+serverport);
                }
            });
 

        cb1.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent ae)
                {
                    protocol=cb1.getSelectedItem().toString();
                   
                    if(protocolMode.equals("pn"))
                    {
                        textarea.setText("change the protocolMode to PS and than set protocol");
                    }
                    else{
                    setProp(protocol);
                    }
                    /*       if(protocol.equals("TL1"))
                             {
                             setProp();
                             }
                             else{
                             protocolspecific();
                             }*/
                }
            });



        //event listeners actions for combo box

        cb.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent ae)
                {
                    textarea.setText(" ");
                    String string =cb.getSelectedItem().toString();
                    System.out.println("DEBUG:textfield string "+string);
                    StringTokenizer token2=new StringTokenizer(string);
                
                    int i=0;
                    int j=0;
                    String[] sarray=new String[10];
                    while (token2.hasMoreTokens())
                    {
                        //getting the string from combobox

                        sarray[i]=token2.nextToken();
                        System.out.println("DEBUG://stringtokenizer "+sarray[i]);
                        if((sarray[i].indexOf('$'))==0)
                        {
                            //operation to be done is get from combobox
                            sarray[i]=sarray[i].substring(1);
                            try{
                                //  setProp(protocol);
                                action(sarray[i]);//call action to perform operation
                            }catch(Exception e)
                            {
                                System.out.println(" the Exception: "+e);
                            }
   


                        }
                        
                        if((sarray[i].indexOf('&'))==0)
                        {
                            if(j==1)
                            {
                                
                                // flag for setting multiset or multiget
                                list=true;
                            }

                            sarray[i]=sarray[i].substring(1);
                            //setting the object array
                            oids[j]=sarray[i];
                   
                            j++;
                        }
                        if((sarray[i].indexOf('%'))==0)
                        {
  
                            sarray[i]=sarray[i].substring(1);
                            //setting the host.
                            host=sarray[i];
                          
                        }

                        i++;
                    }
     
   
                }
            });

      

        //event listener for buttons

        unreg.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent ae)
                {
 
                    System.out.println("DEBUG//AC of Poll/actioncommand:"+unreg.getActionCommand());
                    if((unreg.getActionCommand()).equals("unreg"))
                    {
                        //unregistering the client
                       ms.unregisterClient(sp);
                        unreg.setText("reg-client");
                        unreg.setActionCommand("reg");
                    }
                   
                    else {
                        try{
                             pflag=false;
                            action("SnmpPoll");
                           //registering with the client
                            ms.registerClient(sp);
                        }
                        catch(Exception e)
                        {
                            System.out.println(" the exception is "+e);
                        }
                        unreg.setText("Unreg-client");
                        unreg.setActionCommand("unreg");
                    }
                }
            });

        //action listener for event button
                   
        reg.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent ae)
                {
                    System.out.println("DEBUG://AC of event/actioncommand:"+reg.getActionCommand());
                    if((reg.getActionCommand()).equals("unreg"))
                    {
                        //unregistering  for notification

                        ms.unregisterForNotification(sp);
                        reg.setText("reg-Event");
                        reg.setActionCommand("reg");
                    }
                   
                    else {
                        try{
                            action("SnmpTrap");
                            //registering for notification
                            System.out.println(" before registering for notification");
                            ms.registerForNotification(sp);
                        }
                        catch(Exception e)
                        {
                            System.out.println(" the exception is "+e);
                        }
                        reg.setText("Unreg-Event");
                        reg.setActionCommand("unreg");
                    }
                }
            });
                  

    }
 

    //init method for applet

    public  void init()
    {
        //     applet.setStub(new Applets());
    }



    public static  void doSnmpGet()
    {
        try {
            System.out.println("DEBUG:/In SYNCSEND/before syncsend");
        
       
            msre = ms.syncSend(sp);
        }
        catch (Exception e) {
            System.out.println("Exception at syncSend " + e);
            e.printStackTrace();
        }
	
        if(msre != null) {

            if(msre.getErrString() != null) {
                System.out.println("DEBUG://MSRE=NULL/Error String is = " + msre.getErrString());
                return ;
            }
            if(protocol.equals("TL1")&&!(protocolMode.equals("pn")))
            {
                textarea.append("**********TL1Response**********");
                textarea.append((msre.getResult()).toString());
                textarea.append("******************************");

            }
            if(protocol.equals("CLI"))
            {
                System.out.println(" in the result of cli");
                textarea.append("*************CLI Response**********");
                textarea.append((msre.getResult()).toString());
                textarea.append("*******************************");
                System.out.println(msre.getResult());
            }


            
	    
            if( msre.getResult() instanceof Object[] ) {
                Object[] objarr = (Object[])msre.getResult();
                for(int k=0;k<objarr.length ;k++)
                {
                    System.out.println(" Resultobj "+k+"="+objarr[k]);
                    textarea.append(objarr[k].toString()+"\n");
                }
            }

            //this if for getting result for column walk.

            if( msre.getResult() instanceof Vector ) {
                Vector v=(Vector)msre.getResult();
                System.out.println(" RECV Vector Of Size ="+v.size());

                for(int k=0;k<v.size() ; k++) {
                    Object[] objarr = (Object[])v.elementAt(k);
                    
                    for(int i=0; i< objarr.length ; i++) {
                        System.out.println("Result "+i+"==" + objarr[i]);
                        textarea.append(objarr[i].toString());
                    }
                }
            }

	    
        } else {
            System.out.println("Result Object is NULL!!!");
        }
    }

    //method for handling for send.

    public static void doSnmpSend()
    {
        System.out.println(" DEBUG://ASYNC/");
        int ret= ms.send(sp);
    }team,
   We will have a status meeting at 5 today.
Thanks,
Rajalakshmy

    //method that handle snmpTable.

    public static void doSnmpTable()
    {
        System.out.println(" DEBUG: //Table:/");
           
        ms.syncSend(sp);
    }

    //method that recieve the result 
    public void setEventResult (Property p,Object notifEvent)
    {
        System.out.println("DEBUG://the event result/");
        if(protocol.equals("TL1"))
        {
            System.out.println(" ********* TL1 Autonomous  Message ************");
            TL1Event sp= (TL1Event)notifEvent;
            System.out.println(sp.getProperties());
            textarea.setText("\n");
            textarea.append("***********Autonomos message****************\n");
            textarea.append((sp.getProperties()).toString());
            System.out.println(" *******************************");

        }
        else{
            System.out.println(" ********* SNMP TRAP ************");
            textarea.append("************** SnmpTrap ************");
            textarea.append("\n");
            SnmpEvent se = (SnmpEvent)notifEvent ;
	
            Properties h= se.getProperties() ;

            {
                String[] keys=new String[h.size()];
                String[] values=new String[h.size()];
                int i=0 ;            
            
                Enumeration e=null;
	    
                for(i=0,  e= h.keys(); e.hasMoreElements();) {
                    keys[i]=(e.nextElement()).toString();
                    i ++;
                }
	    
                for(i=0, e= h.elements(); e.hasMoreElements();) {
                    values[i]=(e.nextElement()).toString();
                    i ++;
                }
	    
                for(i = 0; i < h.size(); i++) {
                    System.out.println(keys[i]+" = "+ values[i]);
                 
                    textarea.append((keys[i]+"="+values[i]));
                    textarea.append("\n");
                }
                System.out.println(" *******************************");
                textarea.append("*******************************");
            }
        }
    }   
    




team,
   We will have a status meeting at 5 today.
Thanks,
Rajalakshmy
    public  void setProtocolResult( Property prop, Object result )
    {
        System.out.println(" the protocol is "+protocol);
      
     
     if(protocol.equals("CLI"))
            {

                System.out.println(" in the result of cli");
                textarea.append("*************CLI Response**********");
                textarea.append((result.toString()));
                textarea.append("*******************************");
                //               System.out.println(result.getResult());
                return;
            }

  
     
        if(protocol.equals("TL1")&& !(protocolMode.equals("pn"))) 
        {
            System.out.println(" the exact place");
            textarea.append(result.toString());
            return;
        }
        else{
	
            if( result instanceof Object[] )
            {
                Object[] objarr = (Object[])result;
                for(int i=0; i< objarr.length ; i++) {
                    //textarea.set
                    System.out.println("Result "+i+"==" + objarr[i]);
                    textarea.append(objarr[i].toString()+"\n");
                }
                return;
            }
            System.out.println(" the pflag is "+pflag);
            if(pflag)
            {
        
                MCTableResult mcResult= (MCTableResult) result;
                if( mcResult.isEndOfTable != MCTableResult.TABLE_END)
                {
                    int col= ((Object[])mcResult.rowArray[0]).length;
                    int row= ((Object[][])mcResult.rowArray).length;
                    System.out.println(" Row="+row);
                    System.out.println(" COOls="+col); 
                    for( int j= 0; j < row ; j++) {
                        for( int i= 0; i < col ; i++) {
                            System.out.print(" rowElem ="+ mcResult.rowArray[j][i]) ;
                            textarea.append( mcResult.rowArray[j][i].toString()+"\t");
                        }
                        System.out.println();
                    }
                } else {
                    System.out.println(" TABLE END");
                    if((mcResult.rowArray.length > 0) && (((Object[])mcResult.rowArray[0]).length > 0)) {
                        int col= ((Object[])mcResult.rowArray[0]).length;
                        int row= ((Object[][])mcResult.rowArray).length;
                        System.out.println(" Row="+row);
                        System.out.println(" COOls="+col);
		
                        for( int j= 0; j < row ; j++) {
                            for( int i= 0; i < col ; i++) {
                                System.out.print(" rowElem ="+ mcResult.rowArray[j][i].toString()) ;
                                textarea.append( mcResult.rowArray[j][i].toString());

                            }
                            System.out.println();
                        }
                    }
            
                }
        
            }
        
            else{
                pflag=true;
            }
        }
        
  
    }

    public void processError( Property prop, int errorIndex , String errorMessage)
    {
        System.out.println("DEBUG://Process Error in Get Operation !!!" );
        textarea.setText("Exception in Get Operation !!! "+errorMessage);
        System.err.println(" Error String is = " + errorMessage);
    }
    
    public void processError( Property prop, Object o)
    {
        System.out.println("DEBUG://Error in Get Operation !!!" );
        textarea.setText("Error in Get Operation !!! "+o.toString());

        System.out.println("Error Object is = " + o);
    }
    
    public void processException( Property prop,Exception mcs)
    {
        System.out.println("DEBUG://Exception in Get Operation !!!" );
        textarea.setText("Exception in Get Operation !!! "+mcs);
        System.out.println("Exception is = "+mcs);
    }
    public static void main(String args[])throws IOException
    {
        // SnmpPerfTest spt=new SnmpPerfTest();
        client();
      
    }

    //setup ((TL1Property)Sp)erty for Protocol neutral.
    public static void setProp(String init)
    {
        System.out.println(" DEBUG://in init PROP/"+protocol);
        if(protocolMode.equals("ps"))
        {
            if(init.equals("SNMP"))
            {
               
                sp=new SnmpProperty();
            }
            if(init.equals("TL1"))
            {
                sp=new TL1Property();
            }
            if(init.equals("CLI"))
            {
                sp = new CLIProperty();
            }
            if(init.equals("MO"))
            {
                  sp=new ManagedObjectProperty();
            }

        }
        else{
            if(init.equals("pn"))
            {
                System.out.println(" init client prop");
                    sp=new ClientProperty();
            }
        }
        
    }


    public static void setupProperty(double r)
    {
        System.out.println(" DEBUG://Setup Prop of MO");
        if(flag==1)
        {
            //  sp = new ManagedObjectProperty();
            mprop = new ManagedObjectProperty();
            mprop.setUserProperty("RECONNECT_NMSHOST","kamalg");
            mw = new MethodWrapper("getName");
            ((ManagedObjectProperty)sp).addMethod(mw);
            ((ManagedObjectProperty)sp).setManagedObjectName("kamalg");
            ((ManagedObjectProperty)sp).setComponent(new MSTester());

            ms.sendAction(mprop);
            //    Thread.sleep(3);
        }
        //table
        if(flag==2)
        {
            textarea.setText("the table operation cannot be performed for Mo");
        }
        //columnwalk.
        if(flag==3)
        {
            textarea.setText("this operation cannot be performed for MO");
        }
        
        //do xmlset.
        if(flag==8)
        {
  mprop = new ManagedObjectProperty();
            mprop.setUserProperty("RECONNECT_NMSHOST","localhost");
            ((ManagedObjectProperty)sp).addMethod("setName");
            ((ManagedObjectProperty)sp) .setManagedObjectName("kamalg");
            ((ManagedObjectProperty)sp).setComponent(new MSTester());
            ((ManagedObjectProperty)sp).setHeirarchy("true");
            ((ManagedObjectProperty)sp).setOperationType(Property.OP_WRITE);
            ((ManagedObjectProperty)sp).setWriteValue("xmlAdvent");
    ms.sendAction(mprop);
        }
        //set
        if(flag==4)
        {
            System.out.println(" in setup prop of set");
 mprop = new ManagedObjectProperty();
            mprop.setUserProperty("RECONNECT_NMSHOST","localhost");
            ((ManagedObjectProperty)sp).addMethod("setName");
            ((ManagedObjectProperty)sp).setManagedObjectName("kamalg");
            ((ManagedObjectProperty)sp).setComponent(new MSTester());
            ((ManagedObjectProperty)sp).setOperationType(Property.OP_WRITE);
            ((ManagedObjectProperty)sp).setWriteValue("advent");
    ms.sendAction(mprop);
        }
        //poll
	
        if(flag==5)
        {
            mprop = new ManagedObjectProperty();  
            mprop.setUserProperty("RECONNECT_NMSHOST","localhost");
            //Poll mc = new Poll();
            ((ManagedObjectProperty)sp).setComponent(new MSTester());
	
            ((ManagedObjectProperty)sp).setMaRe:nagedObjectName("kamalg");
            ((ManagedObjectProperty)sp).setUserProperty("RECONNECT_NMSHOST","kamalg");
            MethodWrapper mw = new MethodWrapper("getName");
            ((ManagedObjectProperty)sp).addMethod(mw);
	        
            ms.sendAction(sp);
            //      ms.registerClient(sp);
      
        }
         if(flag==6)
           {
      
               mprop = new ManagedObjectProperty();  
               System.out.println(" setup property for Mo update");
               ((ManagedObjectProperty)sp).setComponent(new MSTester());
               ((ManagedObjectProperty)sp).setUpdateType("Property Update");
               ((ManagedObjectProperty)sp).setHeirarchy("true");
               mprop.setUserProperty("RECONNECT_NMSHOST","localhost");
               ms.sendAction(mprop);
               
          
           }
    }


       
          

    public static void setupProperty(int c)
    {team,
   We will have a status meeting at 5 today.
Thanks,
Rajalakshmy
        System.out.println(" DEBUG://Setup Prop of CLI");
        //CLI Property.
        String promptList[] = { "$"};
        String commandList[] = { "date"};
        if(flag==1)
        {
            System.out.println(" in the excat class");
            ((CLIProperty)sp).setTargetHost("arajesh");
            ((CLIProperty)sp).setTargetPort("23");
            ((CLIProperty)sp).setLoginName("guest");
            ((CLIProperty)sp).setLoginPrompt("login:");
            ((CLIProperty)sp).setPassword("guest");
            ((CLIProperty)sp).setPasswordPrompt("Password:");
            ((CLIProperty)sp).setPromptList(promptList);
            ((CLIProperty)sp).setShellPrompt("$");
            ((CLIProperty)sp).setCommandList(commandList);
            ((CLIProperty)sp).setComponent(new MSTester());


             ms.establishSession(sp);
            System.out.println(" end of setup property");
	
        }
        if(flag==2)Re:
        {
           textarea.setText("the table operation cannot be performed for CLI");
        }
        if(flag==3)
        {
           textarea.setText("this operation cannot be performed for CLI");
        }
        if(flag==5)
        {
           textarea.setText("this operation cannot be performed for CLI");
        }
        if(flag==6)
        {
           textarea.setText("this operation cannot be performed for CLI");
        }
        if(flag==4)
        {
            textarea.setText("this operation cannot be performed for CLI");
        }


    }


    public static void setupProperty(String protocol,int l)
    {
        System.out.println(" DEBUG://In setup prop of TL1");
        //get
        if(flag==1)
        {
            ((TL1Property)sp).setTargetHost(host);
            ((TL1Property)sp).setTargetPort("9099");
            ((TL1Property)sp).setResultType(Property.RESULT_STRING);
            ((TL1Property)sp).setComponent(new MSTester());
            ((TL1Property)sp).setCommandCode("ACT-USER");
            ((TL1Property)sp).setAccessId("ROOT");
            sessionId = ms.establishSession(sp);
            System.out.println(" the sessionId"+sessionId);
            ((TL1Property)sp).setSessionId(sessionId);	
            ((TL1Property)sp).setMessagePayloadBlock("FACTORY1%");
        }

        //poll
        if (flag==5)
        {
            ((TL1Property)sp).setTargetHost(host);
            ((TL1Property)sp).setTargetPort("9099");
            ((TL1Property)sp).setResultType(Property.RESULT_STRING);
            sessionId = ms.establishSession(sp);
            ((TL1Property)sp).setSessionId(sessionId);
            ((TL1Property)sp).setCommandCode("ED-IP");
            //((TL1Property)sp)setTargetId("TARGET1");
            // ((TL1Property)sp)setAccessId("ETHER");
            ((TL1Property)sp).setMessagePayloadBlock("GATEWAY=ON,IP=199.190.212.4,MASK=255.255.255.0,BROADCAST=199.190.212.255,MTU=1500");
            ((TL1Property)sp).setComponent(new MSTester());
            ((TL1Property)sp).setPollInterval(5000);

        }
        //AUTONOMOS
        if(flag==6)
        {
            ((TL1Property)sp).setTargetHost(host);
            ((TL1Property)sp).setTargetPort("9099");
            ((TL1Property)sp).setResultType(Property.RESULT_STRING);
      
        
            /* Register The Component for reciving  Autonomous messages
             *  throteam,
   We will have a status meeting at 5 today.
Thanks,
Rajalakshmyugh MSNotifyListener Interface */
            System.out.println(" the object is set here");
            ((TL1Property)sp).setComponent(new MSTester());

            /* Specify the TL1 Command code used for checking the connection 
             * status */
	
            ((TL1Property)sp).setCommandCode("CHECK");
            /* Establish a Unique TL1 session with the TL1 Device through the
             * Management Server  .
             */
            sessionId = ms.establishSession(sp);
            ((TL1Property)sp).setSessionId(seRe:ssionId);	
        }
        //table
        if(flag==2)
        {
           textarea.setText("the table operation cannot be performed for TL1");
        }
        if(flag==3)
        {
           textarea.setText("this operation cannot be performed for TL1");
        }
 if(flag==4)
        {
           textarea.setText("this  operation cannot be performed for TL1");
        }

 


    }
    public static void setupProperty(String protocolMode)
    {
        System.out.println("DEBUG:// In setup prop of PN");
        //columnwalk.
        if (flag==3)
        {
            ((ClientProperty)sp).setUserProperty("TargetHost",host);
            ((ClientProperty)sp).setURL("RFC1213-MIB.xml#RFC1213-MIB%org%dod%internet%mgmt%mib-2%interfaces%ifTable");
            ((ClientProperty)sp).setUserProperty("TargetHost",host);
            String[] si={"interfaces.ifTable.ifEntry.ifDescr" ,"interfaces.ifTable.ifEntry.ifIndex"};

            ((ClientProperty)sp).setUserProperty("TargetPort","161");
            ((ClientProperty)sp).setComponent(new MSTester());
     
        }
  //table
        if (flag==2 )
        {
       
         
      ((ClientPropeteam,
   We will have a status meeting at 5 today.
Thanks,
Rajalakshmyrty)sp).setURL("RFC1213-MIB.xml#RFC1213-MIB%org%dod%internet%mgmt%mib-2%interfaces%ifTable");
((ClientProperty)sp).setUserProperty("TargetHost",host);
// ((ClientProperty)sp).setUserProperty("TargetHost",host);
            ((ClientProperty)sp).setOperationType(7);
            // ((ClientProperty)sp).setMibsToBeLoaded ("RFC1213-MIB");   
          String[] s={"interfaces.ifTable.ifEntry.ifDescr","interfaces.ifTable.ifEntry.ifIndex"};
            ((ClientProperty)sp).setUserProperty("TargetPort","161");
            ((ClientProperty)sp).setComponent(new MSTester());
        }
        if(flag==1 )//get
        {
            ((ClientProperty)sp).setUserProperty("TargetHost",host);
            ((ClientProperty)sp).setURL("file:RFC1213-MIB.xml#RFC1213-MIB%org%dod%internet%mgmt%mib-2%system%sysDescr");
            ((ClientProperty)sp).setUserProperty("TargetHost",host);
            ((ClientProperty)sp).setUserProperty("TargetPort","161");
            ((ClientProperty)sp).setComponent(new MSTester());
        }

        if(flag==5)
        {
            ((ClientProperty)sp).setUserProperty("TargetHost",host);
            //Poll.
            ((ClientProperty)sp).setURL("RFC1213-MIB.xml#RFC1213-MIB%org%dod%internet%mgmt%mib-2%system%sysDescr");
            ((ClientProperty)sp).setUserProperty("TargetHost",host);
            ((ClientProperty)sp).setUserProperty("TargetPort","161");
            ((ClientProperty)sp).setComponent(new MSTester());
        }
        
        //trap.
        if(flag==6)
        {
            ((ClientProperty)sp).setUserProperty("TargetHost",host);
            ((ClientProperty)sp).setURL("RFC1213-MIB.xml#RFC1213-MIB%notifications%egpNeighborLoss");
        
            ((ClientProperty)sp).setUserProperty("TargetHost",host);
            ((ClientProperty)sp).setUserProperty("LocalPort","4009");
            ((ClientProperty)sp).setComponent(new MSTester());
        }
        //Set
        if(flag==4)
        {
            
            
        }


    }

    // setup property for protocol neutral.

    public static  void  setupProperty()
    {
        System.out.println(" DEBUG://setup prop of SNMP");
        //setup property for SnmpGet 
        if(flag==1)
        {

            if(list)
            {
                ((SnmpProperty) sp).setObjectIDList(oids);
            }
            else{
                //((Snmpproperty) sp).setObjectIDList(oid);
                ((SnmpProperty) sp).setObjectID(oids[0]);
            }
            ((SnmpProperty) sp).setTargetHost(host);
 ((SnmpProperty) sp).setTimeout(5000);
            ((SnmpProperty) sp).setTargetPort("161");
            ((SnmpProperty) sp).setMibsToBeLoaded ("RFC1213-MIB");   
            ((SnmpProperty) sp).setComponent(new MSTester());   
            ((SnmpProperty) sp).setOperationType(com.adventnet.management.Property.OP_READ);
        }

        //setup property for snmp table

        if(flag==2)
        {
            ((SnmpProperty) sp).setTargetHost(host);
            ((SnmpProperty) sp).setTargetPort("161");
            ((SnmpProperty) sp).setMibsToBeLoaded ("RFC1213-MIB");   
                
            ((SnmpProperty) sp).setComponent(new MSTester());
            ((SnmpProperty) sp).setTableOID(".1.3.6.1.2.1.2.2");
            ((SnmpProperty) sp).setObjectID(".1.3.6.1.2.1.2.2");
            //((SnmpProperty) sp).setTableOID("ifTable");
            //((SnmpProperty) sp).setObjectID("ifTable");
            ((SnmpProperty) sp).setOperationType(com.adventnet.management.snmp.SnmpProperty.OP_GET_TABLE);
            ((SnmpProperty) sp).setResultType(com.adventnet.management.Property.RESULT_OBJECT);
        }

        //setup property for snmpcolumnwalk

        if(flag==3)
        {
            ((SnmpProperty) sp).setTargetHost(host);
            ((SnmpProperty) sp).setTargetPort("161");
            ((SnmpProperty) sp).setTableOID("");
            //Snmpproperty)sp).setObjectID(oid);
            ( (SnmpProperty)sp).setMibsToBeLoaded ("RFC1213-MIB");
            ( (SnmpProperty)sp).setComponent(new MSTester());
             
            String[] s={"interfaces.ifTable.ifEntry.ifDescr" ,"interfaces.ifTable.ifEntry.ifIndex"};
            ((SnmpProperty) sp).setObjectIDList(s);
            //    ((Snmpproperty) sp).setMibsToBeLoaded ("RFC1213-MIB");   
            System.out.println(" in the column walk");
            ((SnmpProperty) sp).setOperationType(com.adventnet.management.snmp.SnmpProperty.OP_GET_COLUMN_WALK);
        }

        //setup property for snmpSet
        if(flag==4)
        {
            if(list)
            {
                ((SnmpProperty) sp).setObjectIDList(oids);
                ((SnmpProperty) sp).setWriteValues(value);
            }
            else{
                ((SnmpProperty) sp).setObjectID(oids[0]);
                ((SnmpProperty) sp).setWriteValue("venkat");
            }
            ((SnmpProperty) sp).setTargetHost(host);
            ((SnmpProperty) sp).setTargetPort("161");
            ((SnmpProperty) sp).setMibsToBeLoaded ("RFC1213-MIB");   
         
            ((SnmpProperty) sp).setOperationType(com.adventnet.management.Property.OP_WRITE);
          
        }

        //setup property for snmp Poll.
        if (flag==5)
        {
            ((SnmpProperty) sp).setTargetHost(host);
            ((SnmpProperty) sp).setTargetPort("161");

            ( (SnmpProperty)sp).setMibsToBeLoaded ("RFC1213-MIB");
            String[] nst=null;
            ((SnmpProperty) sp).setObjectIDList(nst);
            ((SnmpProperty) sp).setOperationType(com.adventnet.management.Property.OP_READ);
      
            ((SnmpProperty) sp).setObjectID("1.1.0");
            ((SnmpProperty) sp).setComponent(new MSTester());
            ((SnmpProperty) sp).setPollInterval(2000);
        }

        //setup property
        if (flag==6)
        {
            ((SnmpProperty) sp).setTargetHost(host);
            ((SnmpProperty) sp).setTargetPort("161");
            ((SnmpProperty) sp).setMibsToBeLoaded ("RFC1213-MIB");   
            ((SnmpProperty) sp).setComponent(new MSTester());
            ((SnmpProperty) sp).setLocalPort("4009");
        }
    }



    //actionstar.
    public static void action(String action)throws IOException
    {
       
        //Get
        System.out.println(" DEBUG://action/String: "+action);
       
        if(action.equals("Command"))
        {
            flag=1;
            if((protocolMode.equals("ps")) && (protocol.equals("TL1")))
            {
                setupProperty(protocol,l);//setting prop for tl1.
               
            }
            if((protocolMode.equals("ps")) && (protocol.equals("CLI")))
            {
                setupProperty(c);  //setting prop for cli.
            }
            if((protocolMode.equals("ps")) && (protocol.equals("SNMP")))
            {
                setupProperty(); //setting prop for snmp.
            }
            if((protocolMode.equals("ps")) && (protocol.equals("MO")))
            {
             
                setupProperty(r); //setting prop for MO
            }
            if(protocolMode.equals("pn"))
            {
                setupProperty(protocolMode);
            }
            if(communicationMode.equals("async"))
            {
                System.out.println(" DEBUG CALLING THE ASYNC SEND");
                doSnmpSend();
            }
            else{
                              
                doSnmpGet();
            }
        }
  
        
        //Table

        if(action.equals("Table"))
        {
            flag=2;Re:
            if((protocolMode.equals("ps")) && (protocol.equals("TL1")))
            {
                setupProperty(protocol,l);//setting prop for tl1.
            }
            if((protocolMode.equals("ps")) && (protocol.equals("CLI")))
            {
                setupProperty(c);  //setting prop for cli.
            }
            if((protocolMode.equals("ps")) && (protocol.equals("SNMP")))
            {
                setupProperty(); //setting prop for snmp.
            }
            if((protocolMode.equals("ps")) && (protocol.equals("MO")))
            {
                setupProperty(r); //setting prop for MO
            }
            if(protocolMode.equals("pn"))
            {
                System.out.println(" before setup property for Protocol neutral");
                setupProperty(protocolMode);
            }
            
            doSnmpTable();

        }

        //Poll.

        if(action.equals("SnmpPoll"))
        {
            flag=5;
            if((protocolMode.equals("ps")) && (protocol.equals("TL1")))
            {
                setupProperty(protocol,l);//setting prop for tl1.
            }
            if((protocolMode.equals("ps")) && (protocol.equals("CLI")))
            {
                setupProperty(c);  //setting prop for cli.
            }
            if((protocolMode.equals("ps")) && (protocol.equals("SNMP")))
            {
                setupProperty(); //setting prop for snmp.
            }
            if((protocolMode.equals("ps")) && (protocol.equals("MO")))
            {
                setupProperty(r); //setting prop for MO
            }
 if(protocolMode.equals("pn"))
            {
                setupProperty(protocolMode);
            }
           
        
        }

        //asyncsend

        if (action.equals("SnmpSend"))
        {
            flag=1;
            if((protocolMode.equals("ps")) && (protocol.equals("TL1")))
            {
                setupProperty(protocol,l);//setting prop for tl1.
            }
            if((protocolMode.equals("ps")) && (protocol.equals("CLI")))
            {
                setupProperty(c);  //setting prop for cli.
            }
            if((protocolMode.equals("ps")) && (protocol.equals("SNMP")))
            {
                setupProperty(); //setting prop for snmp.
            }
            if((protocolMode.equals("ps")) && (protocol.equals("MO")))
            {
                setupProperty(r); //setting prop for MO
            }
 if(protocolMode.equals("pn"))
            {
                setupProperty(protocolMode);
            }
Re:
           
            doSnmpSend();
        }


        //ColumnWalk.

        if(action.equals("ColumnWalk"))
        {
            flag=3;
            if((protocolMode.equals("ps")) && (protocol.equals("TL1")))
            {
                setupProperty(protocol,l);//setting prop for tl1.
            }
            if((protocolMode.equals("ps")) && (protocol.equals("CLI")))
            {
                setupProperty(c);  //setting prop for cli.
            }
            if((protocolMode.equals("ps")) && (protocol.equals("SNMP")))
            {
                setupProperty(); //setting prop for snmp.
            }
            if((protocolMode.equals("ps")) && (protocol.equals("MO")))
            {
                setupProperty(r); //setting prop for MO
            }
 if(protocolMode.equals("pn"))
            {
                setupProperty(protocolMode);
            }

            doSnmpGet();
        }

        //Trap.

        if(action.equals("SnmpTrap"))
        {
            flag=6;

            if((protocolMode.equals("ps")) && (protocol.equals("TL1")))
            {
                setupProperty(protocol,l);//setting prop for tl1.
            }
            if((protocolMode.equals("ps")) && (protocol.equals("CLI")))
            {
                setupProperty(c);  //setting prop for cli.
            }
            if((protocolMode.equals("ps")) && (protocol.equals("SNMP")))
            {
                setupProperty(); //setting prop for snmp.
            }
            if((protocolMode.equals("ps")) && (protocol.equals("MO")))
            {
                setupProperty(r); //setting prop for MO
            }
 if(protocolMode.equals("pn"))
            {
                setupProperty(protocolMode);
            }
            
        }
        
        //Set.
       
        if(action.equals("Set"))
        {Re:
            flag=4;
            if((protocolMode.equals("ps")) && (protocol.equals("TL1")))
            {
                setupProperty(protocol,l);//setting prop for tl1.
            }
            if((protocolMode.equals("ps")) && (protocol.equals("CLI")))
            {
                setupProperty(c);  //setting prop for cli.
            }
            if((protocolMode.equals("ps")) && (protocol.equals("SNMP")))
            {
                setupProperty(); //setting prop for snmp.
            }
            if((protocolMode.equals("ps")) && (protocol.equals("MO")))
            {
                setupProperty(r); //setting prop for MO
            }
 if(protocolMode.equals("pn"))
            {
                setupProperty(protocolMode);
            }
            if(communicationMode.equals("async"))
            {

                doSnmpSend();
            }
            else{

                doSnmpGet();
            }
        }
        textarea.append("\n*********************************\n");
    }



    public void start()
    {
        getinstanceApplet(msMode,protocolMode,new MSTester());    
        client();
      
    }


    public static void getinstance(int args)
    {
        msMode = args;
        System.out.println("DEBUG://get instance/appln/"+msMode);
            
        if(msMode==3)
        {        
            try{  
                String a[]={"TCP","com.adventnet.management.transport.TcpClientTransportImpl",serverhost,serverport};

               ms = ManagementServicesAPI.getInstance(a,msMode);

             
            }catch(Exception e)
            {

                textarea.setText("the server is not instantiated");
            }

        }
        else{
                                      
            ms = ManagementServicesAPI.getInstance((String[])null,msMode);
         
        }
    }
    public static void getinstanceApplet(int ms_mode,String protocol_mode,Object obj)
    {       
        System.out.println("DEBUG://get instance/applet/"+msMode);
        try{
        ms = ManagementServicesAPI.getInstance((Applet)obj,msMode);
        }catch(Exception e)
        {
            textarea.setText("the server is not instantiated");
        }Re:
      
        System.out.println(" the test");
        /* if(protocol_mode.equals("ps"))
           {
           protocolspecific();
           }
           if(protocol_mode.equals("pn"))
           {
           protocolneutral();
           }*/
                       
                 

    }
    public static void parseArgs(String args[])
    {
        if (args.length < 2 )
        {
            System.out.println(" Usage : javac  <targethost> <oid>");
        }
        
        getargs(args);
        if(args.length > 2)
        {
            msMode=Integer.parseInt(args[2]);
        }
     
        getinstance(msMode);
        if(args.length > 3)
        {
            protocolMode=args[3];
        }


        if(protocolMode.equals("ps"))
        {
            System.out.println(" debug: default snmp property");
            sp=new SnmpProperty();
  
            //      protocolspecific();
        }
        if(protocolMode.equals("pn"))
        {
            setProp(protocolMode);
        }
        
    }

    public  static void getargs(String[] args)
    {
        host=args[0];
        oid=args[1];
    }



    public static void  getapplet()
    {
        System.out.println(" DEBUG://Settup prop fop applet stub");
        int port=9191;
        MyAppletStub mstub=new MyAppletStub(host,port);
        mstub.setParameter("CODE","test.ms.snmp.sync.MSTester.class");
        mstub.setParameter("ARCHIVE","jars/xalan.jar,jars/crimson.jar,jars/jaxp.jar,jars/AdventNetSnmpProviderServer.jar,jars/AdventNetSnmp.jar,jars/AdventNetFramework.jar,jars/AdventNetSnmp.jar,jars/ManagementServer.jar,jars/crimson.jar,jars/jaxp.jar,classes/framework/");
        // mstub.setParameter("PROTOCOL_MODE","ps");
        mstub.setParameter("WIDTH","400");
        mstub.setParameter("HEIGHT","200");
        if(msMode==3)
        {
            mstub.setParameter("PORT","161");
            mstub.setParameter("CLIENT_SERVER","TCP");
            mstub.setParameter("CLIENT_CLASS_NAME", "com.adventnet.management.transport.TcpClientTransportImpl");
        }
        getinstanceApplet(msMode,protocolMode,mstub);    
    }
}
class RadioListener extends MyAppletStub implements ActionListener { 
    static String runningMode="appln";
    int port=9191;

    public  void actionPerformed(ActionEvent e) {
        String mode=  e.getActionCommand();
        if((mode.equals("2"))||(mode.equals("3")))
        {
            System.out.println("DEBUG:// the running mode is "+runningMode);
            System.out.println("DEBUG:// the msmode is "+msMode);
            msMode=Integer.parseInt(mode);
            if(runningMode.equals("applet"))
            {
                getapplet();
            

            }
            else{
                getinstance(msMode);
            }
        }
        if((mode.equals("appln"))||(mode.equals("applet")))
        {
            runningMode=mode;
            if(runningMode.equals("applet"))
            {
                //    getapplet(runningMode);
            }
        }Re:


        if ((mode.equals("ps"))||(mode.equals("pn")))
        {
            protocolMode=mode;

            if(protocolMode.equals("ps"))
            {
  
                  setProp(protocol);
            }
            if(protocolMode.equals("pn"))
            {
                setProp(protocolMode);
            }

        }

        if((mode.equals("sync"))||(mode.equals("async")))
        {
            communicationMode=mode;
        }
               
               
    }
}


class MyAppletStub  extends MSTester implements AppletStub {

	String host = null;
	int port = 0;
	URL url = null;
	MyAppletContext myAC = null;

	/**
	 * Constructs the stub with host and port.
	 * 
	 * @param h the host to which the applet is connected.
	 * @param p the port to which the applet is connected on the
	 * host machine.
	 * 
	 */
    public MyAppletStub()
    {
    }
	public MyAppletStub(String h,int p)
	{
		host = h;
		port = p;
		myAC = new MyAppletContext();

	}
	
	/**
	 * Handles the Resize of the applet.
	 * 
	 * @param width the width of the resized applet
	 * @param height the height of the resized applet.
	 */
	public void appletResize(int width,int height)
	{
	}

	/**
	 * Gets a handler to the applet's context.In our case we return
	 * the myAppletContext object.
	 *
	 * @return  the applet's context.
	 */
	public AppletContext getAppletContext()
	{
		return myAC;
	}

	/**
	 * Get the base URL for new MSTester() applet.
	 * 
	 * @return URL the base url for new MSTester() applet.
	 */
	public URL getCodeBase()Re:
	{
		if(url == null)
		{
			try
			{
                //#ifdef MSP
                // Change in servlet path - MSP
				url = new URL("http://" + host + ":" + port +"/");//servlet/");//No Internationalisation // MSP
                    //#else
                    //		url = new URL("http://" + host + ":" + port + "/servlets/");//No Internationalisation
                    //#endif

			}
			catch(Exception exc)
			{
				url = null;
			}
		}
		return url;
	}

	 /**
     * Returns an absolute URL naming the directory of the document in which
     * the applet is embedded. 
     * 
     * @return  URL of the document that contains new MSTester()  applet.
     */
	public URL getDocumentBase()
	{
		if(url == null)
		{
                     try
		       {
                   //#ifdef MSP      
// change in servlet path
                   url = new URL("http://" + host + ":" + port + "/");//servlet/");//No Internationalisation //MSP
             	        }
			catch(Exception exc)
			{
				url = null;
			}
		}
		return url;
	}
	
	/** 
	 * Returns the value of the named parameter in the HTML tag.
	 * 
	 * @param name the parameter name
	 * @return value the corresponding value for new MSTester() parameter.
	 */

	public String  getParameter(String name)
	{
		return myAC.getParam(name);
	}

	/**
	 * Sets the named parameter with the corresponding value.
	 * 
	 * @param name the named parameter
	 * @param value the value corresponding to the name.
	 * 
 */
	public void  setParameter(String name,String value)
	{
		myAC.setParam(name,value);
	}
	
	/**
     * Determines if the applet is active.
     * 
     * @return state of new MSTester() applet.
     */
	

	public boolean isActive()
	{
		return false;
	}

}

 class SnmpPerfTest implements ProtocolListener
{
 Property sp=null;
    long begin =0;
    private  String host = "localhost";
    private  String oid  = null;
    private  static ManagementServerUtil msUtil = null;
    static boolean perflag=false;


    /**
     * This method instantiates the ManagementServer and SnmpProtocolProvider 
     * classes, sets the necessary properties and calls the syncSend method 
     * of the Managementserver for getting the result value.In this program
     * this method is called by the main method.
     */
    public void doSnmpPerfTest(boolean perflag)
    {       

        //Instantiating Management Server.
        //  ManagementServer ms = ManagementServer.getInstance();

        //Instantiating SnmpProperty.
        if (perflag)
        {
            /*
            System.out.println(" dummy");
              sp = new DummyProperty();
              
              ((DummyProperty)sp).setComponent(this); */
        }
        else{
            sp=new SnmpProperty();
            ((SnmpProperty) sp).setTargetHost(host);
                ((SnmpProperty) sp).setObjectID("1.5.0");
            ((SnmpProperty) sp).setTimeout(5000);
            ((SnmpProperty) sp).setTargetPort("161");
            ((SnmpProperty) sp).setMibsToBeLoaded ("RFC1213-MIB");   
            ((SnmpProperty) sp).setComponent(this);   
            ((SnmpProperty) sp).setOperationType(com.adventnet.management.Property.OP_READ);

        }

        try
        {
                                     
                    begin =System.currentTimeMillis();
                    System.out.println(" before therequest");
                    for(int i=0; i< 1000 ; i++)
                    {
                        //          System.out.println(" before request send");
                       MSTester. ms.send(sp);
                    }
                    System.out.println(" after the request");
                    
                    
        }
        catch (Exception e)
        {
            System.out.println("Exception at Send " + e);
            e.printStackTrace();
        }

    }

    /**
     * The main method instantiates this class-SnmpPerfTest, and calls its 
     * methods, namely parseArgs() and doSnmpPerfTest()
     */
    public int  mainthink(String[] args,boolean perf)
    {
        perflag=perf;

        System.out.println(" the killer");
        //        MSTester mst=new MSTester();
           SnmpPerfTest s = new SnmpPerfTest();
         s.doSnmpPerfTest(perflag);
         return 0;
  
        }
    static  int cnt=0;
    public void processError( Property prop, Object o){}
    public void processError( Property prop, int errorIndex , String errorMessage)
    {
        cnt ++;
      if(cnt == 1000)
        {
            //      System.out.println(" the res 100"+string);
               long end=System.currentTimeMillis();
            MSTester.textarea.append("The time taken to recieve 1000 response messages" + String.valueOf(end-begin) + "ms \n" );
            cnt=0;
        }

    }

    

    String string ="kamal";
    public void setProtocolResult( Property prop, Object result )
    {
     
        //  System.out.println(" the kamal"+string);  
        Object res [] =(Object[]) result;
        cnt ++;
          if(cnt == 1000)
        {
           long end=System.currentTimeMillis();
            MSTester.textarea.append("The time taken to recieve 1000 response messages" + String.valueOf(end-begin) + "ms \n" );
            cnt=0;
        }
     
    
}
 }


 class MyAppletContext implements AppletContext {

	/** A Property to store all the applet parameters.*/
	private Properties table = new Properties();
       	
	/**
     * Finds and returns the applet in the document represented by this
     * applet context with the given name. The name can be set in the
     * HTML tag by setting the <code>name</code> attribute.The default 
     * implementation of this class though returns a <code>null</code>
     * value.
     *
     * @param   name   an applet name.
     * @return  the applet with the given name, or <code>null</code> if
     *          not found.
     */
	public Applet getApplet(String name)
	{
		return null;
	}
	
    /**
     * Finds all the applets in the document represented by this applet
     * context.
     *
     * @return  an enumeration of all applets in the document represented by
     *          this applet context.This method by default returns null
     */

	public Enumeration getApplets()
	{
		return null;
	}
	
	/**
	 * Creates an Audio Clip.At present we don't support this also. So,we
	 * just return a <code>null</code> value.
	 * 
	 * @param   url   an absolute URL giving the location of the audio clip.
     * @return  the audio clip at the specified URL.
	 */
	public AudioClip getAudioClip(URL url)
	{
		return null;
	}
	
	/**
     * Returns an <code>Image</code> object that can then be painted on
     * the screen. The <code>url</code> argument<code> </code>that is
     * passed as an argument must specify an absolute URL.
     * <p>
     * This method always returns immediately, whether or not the image
     * exists. When the applet attempts to draw the image on the screen,
     * the data will be loaded. The graphics primitives that draw the
     * image will incrementally paint on the screen.
     *
     * @param   url   an absolute URL giving the location of the image.
     * @return  the image at the specified URL.
     * @see     java.awt.Image
     */
	public Image getImage(URL url)
	{
		if (url != null) 
		{
			return checkImage(url);
		}
		return null;
	}
	
	/**
     * Replaces the Web page currently being viewed with the given URL.
     * This method may be ignored by applet contexts that are not
     * browsers.
     *
     * @param   url   an absolute URL giving the location of the document.
     */
	public void showDocument(URL url)
	{  
            /** target window is set by default as 'sameWindow'
             */

        }
	
	 /**
     * Requests that the browser or applet viewer show the Web page
     * indicated by the <code>url</code> argument. The
     * <code>target</code> argument indicates in which HTML frame the
     * document is to be displayed.
     * The target argument is interpreted as follows:
     * <p>
     * <center><table border="3">
     * <tr><td><code>"_self"</code>  <td>Show in the window and frame that
     *                                   contain the applet.</tr>
     * <tr><td><code>"_parent"</code><td>Show in the applet's parent frame. If
     *                                   the applet's frame has no parent frame,
     *                                   acts the same as "_self".</tr>
     * <tr><td><code>"_top"</code>   <td>Show in the top-level frame of the applet's
     *                                   window. If the applet's frame is the
     *                                   top-level frame, acts the same as "_self".</tr>
     * <tr><td><code>"_blank"</code> <td>Show in a new, unnamed
     *                                   top-level window.</tr>
     * <tr><td><i>name</i><td>Show in the frame or window named <i>name</i>. If
     *                        a target named <i>name</i> does not already exist, a
     *                        new top-level window with the specified name is created,
     *                        and the document is shown there.</tr>
     * </table> </center>
     * <p>
     * An applet viewer or browser is free to ignore <code>showDocument</code>.
     *
     * @param   url   an absolute URL giving the location of the document.
     * @param   target   a <code>String</code> indicating where to display
     *                   the page.
     */
    public void showDocument(URL url,String target)
    { 
    }  
       
        /** target window is set as 'newWindow' or 'sameWindow'
    }
	
	 /**
     * Requests that the argument string be displayed in the
     * "status window". Many browsers and applet viewers
     * provide such a window, where the application can inform users of
     * its current state.
     *
     * @param   status   a string to display in the status window.
     */
	public void showStatus(String status)
	{
	}

	/**
	 * Returns the value corrsponding to the parameter <i>name</i>.
	 * For ex., a getParam("USER_NAME") will return the logged-in
	 * user name.
	 * 
	 * @param name the parameter to be searched for.
	 * 
	 * @return String the corresponding value to the parameter
	 */
	public String getParam(String name)
	{
		return table.getProperty(name);
	}
	
	/**
	 * Set the value for the paramter <i>name</i> with the <code>value</code>
	 * 
	 * @param name the parameter whose value is to be SET.
	 * @param value the value to be set 
	 */
	public void setParam(String name,String value)
	{
		table.put(name,value);
	}
	
	/**
	 * Checks whether the URL could be connected to or not.On a successful
	 * connection this method would return the Image Object formed using the
	 * URL.Idelly called from the getImage(URL).
	 * 
	 * @see getImage
	 * 
	 * @param url the absolute url to be looked into.
	 * @return image the Image object constructed from the URL.
	 */
	private Image checkImage(URL url)
	{
        return null;

	}
}



