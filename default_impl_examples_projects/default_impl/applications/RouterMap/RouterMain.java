
//$ID:$
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.examples.routermap;




import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.applet.*;
import java.awt.image.*;
import java.util.*;
import com.adventnet.nms.util.*;
import com.adventnet.nms.severity.*;
import com.adventnet.nms.store.*;
import com.adventnet.nms.startclient.*;


public class RouterMain extends JFrame  implements Runnable,com.adventnet.nms.startclient.NmsFrame,ActionListener,WindowListener
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	javax.swing.JPanel  Top = null;
	javax.swing.JPanel  JPanel1 = null;
	javax.swing.JButton  RouterBtn = null;
	com.adventnet.nms.examples.routermap.RouterPanel  eight = null;
	com.adventnet.nms.examples.routermap.RouterPanel  one = null;
	com.adventnet.nms.examples.routermap.RouterPanel2  six = null;
	com.adventnet.nms.examples.routermap.RouterPanel1  three = null;
	com.adventnet.nms.examples.routermap.RouterPanel1  four = null;
	com.adventnet.nms.examples.routermap.RouterPanel1  seven = null;
	com.adventnet.nms.examples.routermap.RouterPanel  two = null;
	com.adventnet.nms.examples.routermap.RouterPanel3  ten = null;
	com.adventnet.nms.examples.routermap.RouterPanel3  five = null;
	com.adventnet.nms.examples.routermap.RouterPanel2  nine = null;
	javax.swing.JPanel  JPanel2 = null;
	javax.swing.JPanel  JPanel3 = null;
	javax.swing.JLabel  JLabel1 = null;
	com.adventnet.beans.panels.CardPanel  JPanel4 = null;
	javax.swing.JPanel  JPanel5 = null;
	javax.swing.JButton  RefreshBtn = null;
	javax.swing.JButton  CloseBtn = null;
	//<End_Variable_Declarations>

          Vector routerobject = null;
	//SeverityInfo severityInfo = null;         
  

    Thread routerThread;  





   


  public RouterMain()
  {
    //<Begin_RouterMain>
    pack();
    this.setTitle("RouterMain");
  
    //<End_RouterMain>
  }

  public RouterMain(java.applet.Applet applet)
  {
    //<Begin_RouterMain_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setTitle("RouterMain");
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_RouterMain_java.applet.Applet>
  }

  public void setVisible(boolean bl)
  {
	  if(false)
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
  }

  public void init(javax.swing.JApplet applet)
  {
   this.applet = applet;
	if(false)
	{
   //<Begin_init_javax.swing.JApplet>
   this.applet = applet;
   setVisible(true);
  
   //<End_init_javax.swing.JApplet>
	}
		
    routerThread = new Thread(this); 
    routerThread.start();
  }

  public void run()
  {
	  String objRouter = applet.getParameter("name");
//No Internationalisation
	  try 
	  {
		  ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		  DataOutputStream outp = new DataOutputStream(byteStream);
		  outp.writeInt(GenericConstants.ROUTER_PROPERTIES);
		  outp.writeUTF(objRouter);
		  outp.flush();

		  byte[] bytes = byteStream.toByteArray();
		  outp.close();
		  byteStream.reset();
		  byteStream.close();
		  byte[] data = GenericSession.getInstance().syncSend(bytes);
		  if(data != null)
		  {
			  DataInputStream inp = new DataInputStream(new ByteArrayInputStream(data));
			  int reqId = inp.readInt();
			  if(reqId == GenericConstants.ROUTER_PROPERTIES)
			  {
				  int length = inp.readInt();
				  byte serverData[] = new byte[length];
				  inp.readFully(serverData);
				  routerobject = NmsClientUtil.deSerializeVector(serverData);
			  }
		  
		  init();
		  refresh();
		  super.setVisible(true);
		  }
		  /*init();
		  refresh();
		  super.setVisible(true);*/

		  else close();
			  


	  }
	  catch (Exception e) 
	  {
		  //NmsClientUtil.err(NmsClientUtil.getFrame(app),"IO Error sending request to server. "+e);//No Internationalisation
	  } 
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
        JPanel1= new javax.swing.JPanel();
        RouterBtn= new javax.swing.JButton();
        eight= new com.adventnet.nms.examples.routermap.RouterPanel(applet);
        one= new com.adventnet.nms.examples.routermap.RouterPanel(applet);
        six= new com.adventnet.nms.examples.routermap.RouterPanel2(applet);
        three= new com.adventnet.nms.examples.routermap.RouterPanel1(applet);
        four= new com.adventnet.nms.examples.routermap.RouterPanel1(applet);
        seven= new com.adventnet.nms.examples.routermap.RouterPanel1(applet);
        two= new com.adventnet.nms.examples.routermap.RouterPanel(applet);
        ten= new com.adventnet.nms.examples.routermap.RouterPanel3(applet);
        five= new com.adventnet.nms.examples.routermap.RouterPanel3(applet);
        nine= new com.adventnet.nms.examples.routermap.RouterPanel2(applet);
        JPanel2= new javax.swing.JPanel();
        JPanel3= new javax.swing.JPanel();
        JLabel1= new javax.swing.JLabel();
        JPanel4= new com.adventnet.beans.panels.CardPanel(applet);
        JPanel5= new javax.swing.JPanel();
        RefreshBtn= new javax.swing.JButton();
        CloseBtn= new javax.swing.JButton();

  //<End_initVariables>
        
  } 
   
   
  public void setUpGUI(Container container)throws Exception
  { 

  //<Begin_setUpGUI_Container>
container.add(Top,BorderLayout.CENTER);
	Top.setLayout(null);
	JPanel1.setBounds(0,0,225,415);
	Top.add(JPanel1);
	JPanel1.setLayout(null);
	RouterBtn.setBounds(60,95,110,165);
	JPanel1.add(RouterBtn);
	eight.setBounds(170,155,45,45);
	JPanel1.add(eight);
	one.setBounds(170,105,45,45);
	JPanel1.add(one);
	six.setBounds(65,40,45,55);
	JPanel1.add(six);
	three.setBounds(15,105,45,45);
	JPanel1.add(three);
	four.setBounds(15,205,45,45);
	JPanel1.add(four);
	seven.setBounds(15,155,45,45);
	JPanel1.add(seven);
	two.setBounds(170,205,45,45);
	JPanel1.add(two);
	ten.setBounds(120,260,45,55);
	JPanel1.add(ten);
	five.setBounds(65,260,45,55);
	JPanel1.add(five);
	nine.setBounds(120,40,45,55);
	JPanel1.add(nine);
	JPanel2.setBounds(220,0,455,415);
	Top.add(JPanel2);
	JPanel2.setLayout(new BorderLayout(5,5));
	JPanel2.add(JPanel3,BorderLayout.NORTH);
	JPanel3.setLayout(new FlowLayout(1,5,5));
	JPanel3.add(JLabel1);
	JPanel2.add(JPanel4,BorderLayout.CENTER);
	JPanel4.setLayout(new CardLayout(5,5));
	JPanel2.add(JPanel5,BorderLayout.SOUTH);
	JPanel5.setLayout(new FlowLayout(2,5,5));
	JPanel5.add(RefreshBtn);
	JPanel5.add(CloseBtn);
	
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
setResizable(false);
          try
          {
            JPanel1.setBackground(new Color(-1));
          }
          catch(Exception ex)
          {
             showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+JPanel1,ex); 
          }

          try
          {
            RouterBtn.setFocusPainted(false);

			RouterBtn.setIcon(NmsClientUtil.getImageIcon(applet.getDocumentBase() +"../images/router_new1.png"));//No Internationalisation
            RouterBtn.setBackground(new Color(-1));
          }
          catch(Exception ex)
          {
             showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+RouterBtn,ex); 
          }

          try
          {
            eight.setOpaque(true);
            eight.setBackground(new Color(-1));
          }
          catch(Exception ex)
          {
             showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+eight,ex); 
          }
eight.setVisible(false);
          try
          {
            one.setOpaque(true);
            one.setToolTipText("");//No Internationalisation
            one.setBackground(new Color(-1));
          }
          catch(Exception ex)
          {
             showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+one,ex); 
          }
one.setVisible(false);
          try
          {
            six.setBackground(new Color(-1));
          }
          catch(Exception ex)
          {
             showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+six,ex); 
          }
six.setVisible(false);
          try
          {
            three.setOpaque(true);
            three.setBackground(new Color(-1));
          }
          catch(Exception ex)
          {
             showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+three,ex); 
          }
three.setVisible(false);
          try
          {
            four.setOpaque(true);
            four.setBackground(new Color(-1));
          }
          catch(Exception ex)
          {
             showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+four,ex); 
          }
four.setVisible(false);
          try
          {
            seven.setOpaque(true);
            seven.setBackground(new Color(-1));
          }
          catch(Exception ex)
          {
             showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+seven,ex); 
          }
seven.setVisible(false);
          try
          {
            two.setBackground(new Color(-1));
          }
          catch(Exception ex)
          {
             showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+two,ex); 
          }
two.setVisible(false);
          try
          {
            ten.setBackground(new Color(-1));
          }
          catch(Exception ex)
          {
             showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+ten,ex); 
          }
ten.setVisible(false);
          try
          {
            five.setBackground(new Color(-1));
          }
          catch(Exception ex)
          {
             showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+five,ex); 
          }
five.setVisible(false);
          try
          {
            nine.setBackground(new Color(-1));
          }
          catch(Exception ex)
          {
             showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+nine,ex); 
          }
nine.setVisible(false);
          try
          {
            JPanel2.setBackground(new Color(-1));
          }
          catch(Exception ex)
          {
             showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+JPanel2,ex); 
          }

          try
          {
            JPanel3.setBackground(new Color(-1));
          }
          catch(Exception ex)
          {
             showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+JPanel3,ex); 
          }

          try
          {
            JLabel1.setBackground(new Color(-1));
            JLabel1.setHorizontalTextPosition(0);
            JLabel1.setHorizontalAlignment(0);
            JLabel1.setText(NmsClientUtil.GetString("Router details"));
            JLabel1.setFont(new Font("Dialog",1,14));//No Internationalisation
          }
          catch(Exception ex)
          {
             showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+JLabel1,ex); 
          }

          try
          {
            JPanel4.setBackground(new Color(-1));
            java.lang.String[]  JPanel4cardAndClassNames_array = new java.lang.String[ 2 ]; 
            JPanel4cardAndClassNames_array[ 0 ] = "router=com.adventnet.nms.examples.routermap.RouterDetails";//No Internationalisation
            JPanel4cardAndClassNames_array[ 1 ] = "inter=com.adventnet.nms.examples.routermap.IFDetails";//No Internationalisation
            JPanel4.setCardAndClassNames(JPanel4cardAndClassNames_array);
          }
          catch(Exception ex)
          {
             showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+JPanel4,ex); 
          }

          try
          {
            JPanel5.setBackground(new Color(-1));
          }
          catch(Exception ex)
          {
             showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+JPanel5,ex); 
          }

          try
          {
            RefreshBtn.setText(NmsClientUtil.GetString("Refresh"));
          }
          catch(Exception ex)
          {
             showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+RefreshBtn,ex); 
          }

          try
          {
            CloseBtn.setText(NmsClientUtil.GetString("Close"));
          }
          catch(Exception ex)
          {
             showStatus(NmsClientUtil.GetString("Exception while setting properties for bean ")+CloseBtn,ex); 
          }
		CloseBtn.setPreferredSize(new Dimension(CloseBtn.getPreferredSize().width+12,CloseBtn.getPreferredSize().height+0));
		JPanel5.setPreferredSize(new Dimension(JPanel5.getPreferredSize().width+229,JPanel5.getPreferredSize().height+4));
		JPanel4.setPreferredSize(new Dimension(JPanel4.getPreferredSize().width+507,JPanel4.getPreferredSize().height+293));
		JLabel1.setPreferredSize(new Dimension(JLabel1.getPreferredSize().width+132,JLabel1.getPreferredSize().height+8));
		JPanel3.setPreferredSize(new Dimension(JPanel3.getPreferredSize().width+168,JPanel3.getPreferredSize().height+6));

  //<End_setUpProperties>
  } 
  public void setUpConnections()throws Exception
  { 

  //<Begin_setUpConnections>

      nine_nine_conn1 nine_nine_conn11 =  new nine_nine_conn1();
      nine.addMouseListener(nine_nine_conn11);
      six_six_conn1 six_six_conn11 =  new six_six_conn1();
      six.addMouseListener(six_six_conn11);
      eight_eight_conn1 eight_eight_conn11 =  new eight_eight_conn1();
      eight.addMouseListener(eight_eight_conn11);
      one_one_conn1 one_one_conn11 =  new one_one_conn1();
      one.addMouseListener(one_one_conn11);
      four_four_conn1 four_four_conn11 =  new four_four_conn1();
      four.addMouseListener(four_four_conn11);
      ten_ten_conn1 ten_ten_conn11 =  new ten_ten_conn1();
      ten.addMouseListener(ten_ten_conn11);
      two_two_conn1 two_two_conn11 =  new two_two_conn1();
      two.addMouseListener(two_two_conn11);
      three_three_conn2 three_three_conn21 =  new three_three_conn2();
      three.addMouseListener(three_three_conn21);
      seven_seven_conn1 seven_seven_conn11 =  new seven_seven_conn1();
      seven.addMouseListener(seven_seven_conn11);
      five_five_conn1 five_five_conn11 =  new five_five_conn1();
      five.addMouseListener(five_five_conn11);
      RouterBtn_RouterBtn_conn2 RouterBtn_RouterBtn_conn21 =  new RouterBtn_RouterBtn_conn2();
      RouterBtn.addMouseListener(RouterBtn_RouterBtn_conn21);
      CloseBtn_CloseBtn_conn1 CloseBtn_CloseBtn_conn11 =  new CloseBtn_CloseBtn_conn1();
      CloseBtn.addActionListener(CloseBtn_CloseBtn_conn11);
      RouterBtn_RouterBtn_conn1 RouterBtn_RouterBtn_conn11 =  new RouterBtn_RouterBtn_conn1();
      RouterBtn.addActionListener(RouterBtn_RouterBtn_conn11);
  //<End_setUpConnections>
  	  RefreshBtn.setActionCommand("refresh");	//No Internationalisation
      RefreshBtn.addActionListener(this); 
      NmsClientUtil.centerWindow(this);	
      addWindowListener(this);
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
        this.setSize(getPreferredSize().width+682,getPreferredSize().height+419); 
          setTitle(NmsClientUtil.GetString("Router Display Panel"));
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
          showStatus(NmsClientUtil.GetString("Error in init method"),ex); 
        } 
        // let us set the initialzed variable to true so  
        // we dont initialize again even if init is called 
        initialized = true; 
      setUpMenus();
      setUpToolBar();
        

  //<End_init>
  setIconImage(NmsClientUtil.getFrameIcon());
}

 void refresh(){

     JPanel4.showCard("router");//No Internationalisation
     if(routerobject != null)
	{
        ((com.adventnet.nms.examples.routermap.RouterDetails)JPanel4.getCard("router")).setValues((Properties)routerobject.elementAt(0));//No Internationalisation
        Properties prop = (Properties)routerobject.elementAt(0);
        RouterBtn.setBackground(SeverityInfo.getInstance().getColor(Integer.parseInt(prop.getProperty("status"))));//No Internationalisation
        RouterBtn.setToolTipText(prop.getProperty("ipAddress"));//No Internationalisation
           JLabel1.setText(NmsClientUtil.GetString("Router details  :  ")+prop.getProperty("name")); //No Internationalisation
	}
      if(routerobject.size() >=2)
	{
      one.setVisible(true);
      Properties prop = (Properties)routerobject.elementAt(1);
      one.setValues(SeverityInfo.getInstance().getColor(Integer.parseInt(prop.getProperty("status"))), prop.getProperty("ipAddress"));//No Internationalisation
      one.setToolTipText(prop.getProperty("ipAddress"));//No Internationalisation
	}
      if(routerobject.size() >=3)
	{
      two.setVisible(true);		
      Properties prop = (Properties)routerobject.elementAt(2);
      two.setValues(SeverityInfo.getInstance().getColor(Integer.parseInt(prop.getProperty("status"))), prop.getProperty("ipAddress"));//No Internationalisation
      two.setToolTipText(prop.getProperty("ipAddress"));//No Internationalisation
	}
     if(routerobject.size() >=4)
	{
      three.setVisible(true);
      Properties prop = (Properties)routerobject.elementAt(3);
      three.setValues(SeverityInfo.getInstance().getColor(Integer.parseInt(prop.getProperty("status"))), prop.getProperty("ipAddress"));//No Internationalisation
      three.setToolTipText(prop.getProperty("ipAddress"));//No Internationalisation
	}
      if(routerobject.size() >=5)
	{
      four.setVisible(true);
      Properties prop = (Properties)routerobject.elementAt(4);
      four.setValues(SeverityInfo.getInstance().getColor(Integer.parseInt(prop.getProperty("status"))), prop.getProperty("ipAddress"));//No Internationalisation
      four.setToolTipText(prop.getProperty("ipAddress"));//No Internationalisation
	}
      if(routerobject.size() >=6)
	{
      five.setVisible(true);
      Properties prop = (Properties)routerobject.elementAt(5);
      five.setValues(SeverityInfo.getInstance().getColor(Integer.parseInt(prop.getProperty("status"))), prop.getProperty("ipAddress"));//No Internationalisation
      five.setToolTipText(prop.getProperty("ipAddress"));//No Internationalisation
	}
      if(routerobject.size() >=7)
	{
      six.setVisible(true);
      Properties prop = (Properties)routerobject.elementAt(6);
      six.setValues(SeverityInfo.getInstance().getColor(Integer.parseInt(prop.getProperty("status"))), prop.getProperty("ipAddress"));//No Internationalisation
      six.setToolTipText(prop.getProperty("ipAddress"));//No Internationalisation
	}
      if(routerobject.size() >=8)
	{
      seven.setVisible(true);
      Properties prop = (Properties)routerobject.elementAt(7);
      seven.setValues(SeverityInfo.getInstance().getColor(Integer.parseInt(prop.getProperty("status"))), prop.getProperty("ipAddress"));//No Internationalisation
      seven.setToolTipText(prop.getProperty("ipAddress"));//No Internationalisation
	}
     if(routerobject.size() >=9)
	{
      eight.setVisible(true);
      Properties prop = (Properties)routerobject.elementAt(8);
      eight.setValues(SeverityInfo.getInstance().getColor(Integer.parseInt(prop.getProperty("status"))), prop.getProperty("ipAddress"));//No Internationalisation
      eight.setToolTipText(prop.getProperty("ipAddress"));//No Internationalisation
	}
      if(routerobject.size() >=10)
	{
      nine.setVisible(true);
      Properties prop = (Properties)routerobject.elementAt(9);
      nine.setValues(SeverityInfo.getInstance().getColor(Integer.parseInt(prop.getProperty("status"))), prop.getProperty("ipAddress"));//No Internationalisation
      nine.setToolTipText(prop.getProperty("ipAddress"));//No Internationalisation
	}
      if(routerobject.size() >=11)
	{
      ten.setVisible(true);
      Properties prop = (Properties)routerobject.elementAt(10);
      ten.setValues(SeverityInfo.getInstance().getColor(Integer.parseInt(prop.getProperty("status"))), prop.getProperty("ipAddress"));//No Internationalisation
      ten.setToolTipText(prop.getProperty("ipAddress"));//No Internationalisation
	}
    

  } 
 
public void actionPerformed(ActionEvent ae)
{
	String action       = ae.getActionCommand();
	if(action.equals("refresh"))
	{
     routerThread = new Thread(this); 
	 routerThread.start();	
	}
		
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


 public void close()
	{
		dispose();
	}

public void  setBorderType(MouseEvent me, int type)
{
	if(type == 1)
	{
		((JPanel)me.getSource()).setBorder(new javax.swing.border.BevelBorder(javax.swing.border.BevelBorder.RAISED));
		setCursor(new Cursor(Cursor.HAND_CURSOR));					
	}
	if(type == 2)
	{
		((JPanel)me.getSource()).setBorder(new javax.swing.border.MatteBorder(0,0,0,0,Color.white));		
		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));	
	}

}
public void windowIconified(WindowEvent evt)
{
}
public void windowMaximised(WindowEvent evt)
{
}
public void windowDeiconified(WindowEvent evt)
{
}
public void windowOpened(WindowEvent evt)
{
}
public void windowDeactivated (WindowEvent evt)
{
}
public void windowActivated (WindowEvent evt)
{
}
public void windowClosed(WindowEvent evt)
{
}


public void  windowClosing(WindowEvent evt)
{
	JFrame frame = (JFrame)evt.getSource();
	frame.setVisible(false);
	frame.dispose();

}





//<Begin__class_RouterBtn_RouterBtn_conn1>

 class RouterBtn_RouterBtn_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>  - Please do not remove this comment or write any custom code above this

     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
         JPanel4.showCard("router"); //No Internationalisation
         Properties prop = (Properties)routerobject.elementAt(0); 
         ((com.adventnet.nms.examples.routermap.RouterDetails)JPanel4.getCard("router")).setValues((Properties)routerobject.elementAt(0));//No Internationalisation
           JLabel1.setText(NmsClientUtil.GetString("Router details  :  ")+prop.getProperty("name")); //No Internationalisation

     }
}
//<End__class_RouterBtn_RouterBtn_conn1>






//<Begin__class_CloseBtn_CloseBtn_conn1>

 class CloseBtn_CloseBtn_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART> - Please do not remove this comment or write any custom code above this

     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
 close(); 
     }
}
//<End__class_CloseBtn_CloseBtn_conn1>








//<Begin__class_RouterBtn_RouterBtn_conn2>

 class RouterBtn_RouterBtn_conn2 extends java.awt.event.MouseAdapter implements java.io.Serializable 
  {

     //<TOP_PART> - Please do not remove this comment or write any custom code above this

     //Listener Interface Methods Are Added Below 


     public void mouseEntered( java.awt.event.MouseEvent arg0)
     {
 setCursor(new Cursor(Cursor.HAND_CURSOR)); 
     }



     public void mouseExited( java.awt.event.MouseEvent arg0)
     {
  setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); 
     }
 } 
//<End__class_RouterBtn_RouterBtn_conn2>

 







 








 
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

 
//<Begin__class_nine_nine_conn1>

 class nine_nine_conn1 extends java.awt.event.MouseAdapter implements java.io.Serializable 
  {

     //<TOP_PART> - Please do not remove this comment or write any custom code above this

     //Listener Interface Methods Are Added Below 


     public void mousePressed( java.awt.event.MouseEvent arg0)
     {
    JPanel4.showCard("inter");//No Internationalisation
           ((com.adventnet.nms.examples.routermap.IFDetails)JPanel4.getCard("inter")).setValues((Properties)routerobject.elementAt(9)); //No Internationalisation
            JLabel1.setText(NmsClientUtil.GetString("Interface Details")); 
    }
 
     public void mouseEntered( java.awt.event.MouseEvent arg0)
     {
                setBorderType(arg0,1);  
     }
 
     public void mouseExited( java.awt.event.MouseEvent arg0)
     {
                 setBorderType(arg0,2);  
     }   

}
//<End__class_nine_nine_conn1>
//<Begin__class_six_six_conn1>

 class six_six_conn1 extends java.awt.event.MouseAdapter implements java.io.Serializable 
  {

     //<TOP_PART> - Please do not remove this comment or write any custom code above this

     //Listener Interface Methods Are Added Below 


     public void mousePressed( java.awt.event.MouseEvent arg0)
     {
   JPanel4.showCard("inter");//No Internationalisation
   
         ((com.adventnet.nms.examples.routermap.IFDetails)JPanel4.getCard("inter")).setValues((Properties)routerobject.elementAt(6)); //No Internationalisation
            JLabel1.setText(NmsClientUtil.GetString("Interface Details")); 
    }
 
     public void mouseEntered( java.awt.event.MouseEvent arg0)
     {
                setBorderType(arg0,1);  
     }
 
     public void mouseExited( java.awt.event.MouseEvent arg0)
     {
                 setBorderType(arg0,2);  
     }   
 
  }
//<End__class_six_six_conn1>
//<Begin__class_eight_eight_conn1>

 class eight_eight_conn1 extends java.awt.event.MouseAdapter implements java.io.Serializable 
  {

     //<TOP_PART> - Please do not remove this comment or write any custom code above this

     //Listener Interface Methods Are Added Below 


     public void mousePressed( java.awt.event.MouseEvent arg0)
     {
    JPanel4.showCard("inter");//No Internationalisation
        ((com.adventnet.nms.examples.routermap.IFDetails)JPanel4.getCard("inter")).setValues((Properties)routerobject.elementAt(8)); //No Internationalisation
            JLabel1.setText(NmsClientUtil.GetString("Interface Details")); 
    }
 
     public void mouseEntered( java.awt.event.MouseEvent arg0)
     {
                setBorderType(arg0,1);  
     }
 
     public void mouseExited( java.awt.event.MouseEvent arg0)
     {
                 setBorderType(arg0,2);  
     }   

}
//<End__class_eight_eight_conn1>
//<Begin__class_one_one_conn1>

 class one_one_conn1 extends java.awt.event.MouseAdapter implements java.io.Serializable 
  {

     //<TOP_PART> - Please do not remove this comment or write any custom code above this

     //Listener Interface Methods Are Added Below 


     public void mouseClicked( java.awt.event.MouseEvent arg0)
     {
 
     }



     public void mousePressed( java.awt.event.MouseEvent arg0)
     {
  
 JPanel4.showCard("inter"); //No Internationalisation
 if(routerobject.size() > 1)
  {
    ((com.adventnet.nms.examples.routermap.IFDetails)JPanel4.getCard("inter")).setValues((Properties)routerobject.elementAt(1)); //No Internationalisation
    JLabel1.setText(NmsClientUtil.GetString("Interface Details"));  
  }
 
}
 


     public void mouseEntered( java.awt.event.MouseEvent arg0)
     {
 setBorderType(arg0,1);

     }
 


     public void mouseExited( java.awt.event.MouseEvent arg0)
     {
  setBorderType(arg0,2);

     }
 }   
//<End__class_one_one_conn1>
//<Begin__class_four_four_conn1>

 class four_four_conn1 extends java.awt.event.MouseAdapter implements java.io.Serializable 
  {

     //<TOP_PART> - Please do not remove this comment or write any custom code above this

     //Listener Interface Methods Are Added Below 


     public void mousePressed( java.awt.event.MouseEvent arg0)
     {
 JPanel4.showCard("inter");//No Internationalisation
   //if(routerobject.size() == 5)
   {
        ((com.adventnet.nms.examples.routermap.IFDetails)JPanel4.getCard("inter")).setValues((Properties)routerobject.elementAt(4)); //No Internationalisation
        JLabel1.setText(NmsClientUtil.GetString("Interface Details")); 
   }  
      }
 
     public void mouseEntered( java.awt.event.MouseEvent arg0)
     {
                setBorderType(arg0,1);  
     }
 
     public void mouseExited( java.awt.event.MouseEvent arg0)
     {
                 setBorderType(arg0,2);  
     } 
}
//<End__class_four_four_conn1>
//<Begin__class_ten_ten_conn1>

 class ten_ten_conn1 extends java.awt.event.MouseAdapter implements java.io.Serializable 
  {

     //<TOP_PART> - Please do not remove this comment or write any custom code above this

     //Listener Interface Methods Are Added Below 


     public void mousePressed( java.awt.event.MouseEvent arg0)
     {
    JPanel4.showCard("inter");//No Internationalisation
         ((com.adventnet.nms.examples.routermap.IFDetails)JPanel4.getCard("inter")).setValues((Properties)routerobject.elementAt(10)); //No Internationalisation
            JLabel1.setText(NmsClientUtil.GetString("Interface Details")); 
    }
 
     public void mouseEntered( java.awt.event.MouseEvent arg0)
     {
                setBorderType(arg0,1);  
     }
 
     public void mouseExited( java.awt.event.MouseEvent arg0)
     {
                 setBorderType(arg0,2);  
     }   

}
//<End__class_ten_ten_conn1>
//<Begin__class_two_two_conn1>

 class two_two_conn1 extends java.awt.event.MouseAdapter implements java.io.Serializable 
  {

     //<TOP_PART> - Please do not remove this comment or write any custom code above this

     //Listener Interface Methods Are Added Below 


     public void mousePressed( java.awt.event.MouseEvent arg0)
     {
 JPanel4.showCard("inter");//No Internationalisation
//  if(routerobject.size() > 2)
   {
   ((com.adventnet.nms.examples.routermap.IFDetails)JPanel4.getCard("inter")).setValues((Properties)routerobject.elementAt(2)); //No Internationalisation
  JLabel1.setText(NmsClientUtil.GetString("Interface Details"));  
   } 
   
     }



     public void mouseEntered( java.awt.event.MouseEvent arg0)
     {
 setBorderType(arg0,1); 
     }
 


     public void mouseExited( java.awt.event.MouseEvent arg0)
     {
  setBorderType(arg0,2);  
     }
 }  
//<End__class_two_two_conn1>
//<Begin__class_three_three_conn2>

 class three_three_conn2 extends java.awt.event.MouseAdapter implements java.io.Serializable 
  {

     //<TOP_PART> - Please do not remove this comment or write any custom code above this

     //Listener Interface Methods Are Added Below 


     public void mousePressed( java.awt.event.MouseEvent arg0)
     {
 JPanel4.showCard("inter");//No Internationalisation
  //if(routerobject.size() == 4)
  {
      ((com.adventnet.nms.examples.routermap.IFDetails)JPanel4.getCard("inter")).setValues((Properties)routerobject.elementAt(3)); //No Internationalisation
      JLabel1.setText(NmsClientUtil.GetString("Interface Details")); 
 }  
  
     }



     public void mouseEntered( java.awt.event.MouseEvent arg0)
     {
  setBorderType(arg0,1);  
     }
 


     public void mouseExited( java.awt.event.MouseEvent arg0)
     {
  setBorderType(arg0,2);  
     }
 }  
//<End__class_three_three_conn2>
//<Begin__class_seven_seven_conn1>

 class seven_seven_conn1 extends java.awt.event.MouseAdapter implements java.io.Serializable 
  {

     //<TOP_PART> - Please do not remove this comment or write any custom code above this

     //Listener Interface Methods Are Added Below 


     public void mousePressed( java.awt.event.MouseEvent arg0)
     {
    JPanel4.showCard("inter");//No Internationalisation
            ((com.adventnet.nms.examples.routermap.IFDetails)JPanel4.getCard("inter")).setValues((Properties)routerobject.elementAt(7)); //No Internationalisation
            JLabel1.setText(NmsClientUtil.GetString("Interface Details")); 
    }
 
     public void mouseEntered( java.awt.event.MouseEvent arg0)
     {
                setBorderType(arg0,1);  
     }
 
     public void mouseExited( java.awt.event.MouseEvent arg0)
     {
                 setBorderType(arg0,2);  
     }   
  
    
}
//<End__class_seven_seven_conn1>
//<Begin__class_five_five_conn1>

 class five_five_conn1 extends java.awt.event.MouseAdapter implements java.io.Serializable 
  {

     //<TOP_PART> - Please do not remove this comment or write any custom code above this

     //Listener Interface Methods Are Added Below 

 public void mousePressed( java.awt.event.MouseEvent arg0)
     {
 JPanel4.showCard("inter");//No Internationalisation
   //if(routerobject.size() == 6)
   {
        ((com.adventnet.nms.examples.routermap.IFDetails)JPanel4.getCard("inter")).setValues((Properties)routerobject.elementAt(5)); //No Internationalisation
        JLabel1.setText(NmsClientUtil.GetString("Interface Details")); 
   }  
      }
 
     public void mouseEntered( java.awt.event.MouseEvent arg0)
     {
                setBorderType(arg0,1);  
     }
 
     public void mouseExited( java.awt.event.MouseEvent arg0)
     {
                 setBorderType(arg0,2);  
     }  
  
}
//<End__class_five_five_conn1>
}























