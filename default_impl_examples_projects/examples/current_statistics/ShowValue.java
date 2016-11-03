//$Id: ShowValue.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $

// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.pollui;

import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

import com.adventnet.nms.util.NmsClientUtil;

public class ShowValue extends JFrame 
{
    //<Begin_Variable_Declarations>
    private boolean initialized = false;
    private java.applet.Applet applet = null;
    private boolean running=false;
    javax.swing.JPanel Top = null;
    javax.swing.JPanel mainPanel = null;
    javax.swing.JPanel centerPanel = null;
    javax.swing.JScrollPane tablePane = null;
    javax.swing.JTable table = null;
    javax.swing.JPanel intervalPanel = null;
    javax.swing.JComboBox instCombo = null;
    javax.swing.JLabel intervalLabel = null;
    javax.swing.JLabel sizeLabel = null;
    javax.swing.JLabel instLabel = null;
    com.adventnet.utils.NumericTextField intervalField = null;
    com.adventnet.utils.NumericTextField sizeField = null;
    javax.swing.JButton clearButton = null;
    javax.swing.JPanel southPanel = null;
    javax.swing.JButton stopButton = null;
    javax.swing.JButton closeButton = null;
    GridBagConstraints cons = new GridBagConstraints();
    private Vector time =null;
    private Vector data =new Vector();
    Insets inset;
    //<End_Variable_Declarations>
    private SnmpQuery sq =null;
    private Vector muloid =null;
    private String oid = null; 
    private int length =0; 
    private int count=0;
    private int size=20;
    private String [] values= null;
    private String [] times =null;
    private boolean first = true;
    private Vector columns= null;
    public ShowValue()
    {
        //<Begin_ShowValue>
        pack();
  
        //<End_ShowValue>
    }

    public ShowValue(SnmpQuery snmp,Vector mul, String titl)
    {
        sq=snmp;		
        muloid=mul;	
        pack();
        this.setTitle("Current Statistics for "+ titl); 
        setIconImage(NmsClientUtil.getFrameIcon());
        columns=new Vector();
        data=new Vector();
        columns.add("Time of Collection");
        columns.add("Value Collected");  

        this.addWindowListener(new WindowAdapter()
            {
                public void windowClosing(WindowEvent e) {
                    values=null;
                    times=null;
                    data=null;
                    sq.close();
                }
            });
    }	

  
    public ShowValue(java.applet.Applet applet)
    {
        //<Begin_ShowValue_java.applet.Applet>
        this.applet = applet;
        pack();
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
        //<End_ShowValue_java.applet.Applet>
    }


  
    public synchronized void setResult(double res )
    {
        try
        {
            Vector val = new Vector();	   
            if(first)
            {
                first=false;
            } 
            if(count>size-1)
            {
                count=size;
                data.remove(0);
                val.add(NmsClientUtil.formatDate(System.currentTimeMillis()));
                val.add(String.valueOf(res));
                data.add(size-1,val);       
            }	 
            else
            {
                val.add(NmsClientUtil.formatDate(System.currentTimeMillis()));
                val.add(String.valueOf(res));
                data.add(val);
                count++;
            }
            DefaultTableModel model= new DefaultTableModel(data,columns);
            table.setModel(model); 
            val=null;
        }
        catch(Exception ex)
        {
        }
    }
  
    private synchronized void updateTable(int newsiz)
    {
        try
        { 
            if(newsiz<size)
            {
                if(newsiz<count)
                {
                    for(int i=0;i<count-newsiz;i++)
                    {
                        data.remove(0);
                    }	
                    DefaultTableModel model= new DefaultTableModel(data,columns);
                    table.setModel(model); 			   
                }
            }	 
            size=newsiz;
        }
        catch(Exception ex)
        {
        }	    
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
    public void init()
    {
	   
        //<Begin_init> 
        if (initialized == true) return; 
        this.setSize(750,400); 
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
            showStatus("Error in init method",ex); 
        } 
        // let us set the initialized variable to true so  
        // we dont initialize again even if init is called 
        initialized = true; 

  
        //<End_init>
    
        if(muloid!=null && muloid.size()>0)
        {
            oid=(String)muloid.remove(0); 
            for(int i=0;i<muloid.size();i++)
            {
                instCombo.addItem(muloid.elementAt(i)); 
            }  
        }
        else
        {
            intervalPanel.remove(instLabel);  
            intervalPanel.remove(instCombo);
        }     
 

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
    public void setUpProperties()
    {
        //<Begin_setUpProperties> 


        try
        {
            intervalPanel.setBorder(new javax.swing.border.EtchedBorder(0));
        }
        catch(Exception ex)
        {
            showStatus("Exception while setting properties for bean "+intervalPanel,ex); 
        }

        //<UserCode_Begin_Bean_intervalPanel>

//<UserCode_End_Bean_intervalPanel>

        try
        {
            instCombo.setFont(new Font("Dialog",0,12));
        }
        catch(Exception ex)
        {
            showStatus("Exception while setting properties for bean "+instCombo,ex); 
        }

        //<UserCode_Begin_Bean_instCombo>

//<UserCode_End_Bean_instCombo>

        try
        {
            intervalLabel.setText("Interval");
            intervalLabel.setForeground(new Color(-16777216));
            intervalLabel.setFont(new Font("Dialog",0,12));
        }
        catch(Exception ex)
        {
            showStatus("Exception while setting properties for bean "+intervalLabel,ex); 
        }
	  
        try
        {
            sizeLabel.setText("Window Size");
            sizeLabel.setForeground(new Color(-16777216));
            sizeLabel.setFont(new Font("Dialog",0,12));
        }
        catch(Exception ex)
        {
            showStatus("Exception while setting properties for bean "+intervalLabel,ex); 
        }

        //<UserCode_Begin_Bean_intervalLabel>

//<UserCode_End_Bean_intervalLabel>

        try
        {
            instLabel.setForeground(new Color(-16777216));
            instLabel.setText("Instance");
            instLabel.setFont(new Font("Dialog",0,12));
        }
        catch(Exception ex)
        {
            showStatus("Exception while setting properties for bean "+instLabel,ex); 
        }

        //<UserCode_Begin_Bean_instLabel>

//<UserCode_End_Bean_instLabel>

        try
        {
            intervalField.setPreferredSize(new Dimension(60,21));
            intervalField.setText("15");
        }
        catch(Exception ex)
        {
            showStatus("Exception while setting properties for bean "+intervalField,ex); 
        }
          
        try
        {
            sizeField.setPreferredSize(new Dimension(60,21));
            sizeField.setText("20");
        }
        catch(Exception ex)
        {
            showStatus("Exception while setting properties for bean "+intervalField,ex); 
        }
        //<UserCode_Begin_Bean_intervalField>

//<UserCode_End_Bean_intervalField>

        try
        {
            clearButton.setText("Clear");
            clearButton.setFont(new Font("Dialog",0,12));
        }
        catch(Exception ex)
        {
            showStatus("Exception while setting properties for bean "+clearButton,ex); 
        }

        //<UserCode_Begin_Bean_clearButton>

//<UserCode_End_Bean_clearButton>

        try
        {
            stopButton.setText("Stop");
            stopButton.setFont(new Font("Dialog",0,12));
        }
        catch(Exception ex)
        {
            showStatus("Exception while setting properties for bean "+stopButton,ex); 
        }

        //<UserCode_Begin_Bean_stopButton>

//<UserCode_End_Bean_stopButton>

        try
        {
            closeButton.setText("Close");
            closeButton.setFont(new Font("Dialog",0,12));
            tablePane.setBorder(new javax.swing.border.BevelBorder(1));
        }
        catch(Exception ex)
        {
            showStatus("Exception while setting properties for bean "+closeButton,ex); 
        }

        //<UserCode_Begin_Bean_closeButton>

        //<UserCode_End_Bean_closeButton>

  
        //<End_setUpProperties>
    } 
    public void initVariables()
    {
        //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        mainPanel= new javax.swing.JPanel();
        centerPanel= new javax.swing.JPanel();
        tablePane= new javax.swing.JScrollPane();
        intervalPanel= new javax.swing.JPanel();
        instCombo= new javax.swing.JComboBox();
        intervalLabel= new javax.swing.JLabel();
        sizeLabel= new javax.swing.JLabel();
        instLabel= new javax.swing.JLabel();
        intervalField= new com.adventnet.utils.NumericTextField(15);
        sizeField= new com.adventnet.utils.NumericTextField(15);
        clearButton= new javax.swing.JButton();
        southPanel= new javax.swing.JPanel();
        stopButton= new javax.swing.JButton();
        closeButton= new javax.swing.JButton();
	table= new javax.swing.JTable();
        //<End_initVariables>
    } 
    public void setUpGUI(Container container)
    {
        //<Begin_setUpGUI_Container> 
	container.add(Top,BorderLayout.CENTER);
	Top.setLayout(new GridBagLayout());
	inset = new Insets(0,0,0,0);
	setConstraints(0,0,1,1,1.0,1.0,cons.CENTER,cons.BOTH,inset,0,0);
	Top.add(mainPanel,cons);
	mainPanel.setLayout(new BorderLayout(5,5));
	mainPanel.add(centerPanel,BorderLayout.CENTER);
	centerPanel.setLayout(new GridBagLayout());
	inset = new Insets(0,0,0,0);
	setConstraints(0,0,1,1,1.0,0.9,cons.CENTER,cons.BOTH,inset,0,0);
	centerPanel.add(tablePane,cons);
	tablePane.getViewport().add(table);
	inset = new Insets(0,0,0,0);
	setConstraints(0,1,1,1,1.0,0.1,cons.CENTER,cons.BOTH,inset,0,0);
	centerPanel.add(intervalPanel,cons);
	intervalPanel.setLayout(new GridBagLayout());
	inset = new Insets(2,5,2,5);
	setConstraints(3,0,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
	intervalPanel.add(instCombo,cons);
	inset = new Insets(2,5,2,5);
	setConstraints(0,0,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
	intervalPanel.add(intervalLabel,cons);
	inset = new Insets(2,5,2,5);
	setConstraints(2,0,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
	intervalPanel.add(instLabel,cons);
	inset = new Insets(2,5,2,5);
	setConstraints(1,0,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
	intervalPanel.add(intervalField,cons);
	inset = new Insets(2,5,2,5);
	inset = new Insets(2,5,2,5);
	setConstraints(4,0,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
	intervalPanel.add(sizeLabel,cons);
	inset = new Insets(2,5,2,5);
	setConstraints(5,0,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
	intervalPanel.add(sizeField,cons);
	setConstraints(6,0,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
	intervalPanel.add(clearButton,cons);
	mainPanel.add(southPanel,BorderLayout.SOUTH);
	southPanel.setLayout(new GridBagLayout());
	inset = new Insets(5,10,5,10);
	setConstraints(0,0,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
	southPanel.add(stopButton,cons);
	inset = new Insets(5,10,5,10);
	setConstraints(1,0,1,1,0.0,0.0,cons.CENTER,cons.BOTH,inset,0,0);
	southPanel.add(closeButton,cons);
        //<End_setUpGUI_Container>
    } 
    public void setUpConnections()
    {
        //<Begin_setUpConnections> 

        instCombo_instCombo_conn instCombo_instCombo_conn1 =  new instCombo_instCombo_conn();
        instCombo.addItemListener(instCombo_instCombo_conn1);
        intervalField_intervalField_conn1 intervalField_intervalField_conn11 =  new intervalField_intervalField_conn1();
        intervalField.addKeyListener(intervalField_intervalField_conn11);
        sizeField_sizeField_conn1 sizeField_sizeField_conn11 =  new sizeField_sizeField_conn1();
        sizeField.addKeyListener(sizeField_sizeField_conn11);
        stopButton_stopButton_conn stopButton_stopButton_conn1 =  new stopButton_stopButton_conn();
        stopButton.addActionListener(stopButton_stopButton_conn1);
        clearButton_clearButton_conn clearButton_clearButton_conn1 =  new clearButton_clearButton_conn();
        clearButton.addActionListener(clearButton_clearButton_conn1);
        closeButton_closeButton_conn closeButton_closeButton_conn1 =  new closeButton_closeButton_conn();
        closeButton.addActionListener(closeButton_closeButton_conn1);
  
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

 
    private void clearList()
    {
        count=0;	 
        data = new Vector();
    }	  

    //<Begin__class_closeButton_closeButton_conn>

    class closeButton_closeButton_conn implements java.awt.event.ActionListener, java.io.Serializable 
    {

        //<TOP_PART>
        //<UserCode_Begin_Connection_closeButton_closeButton_conn>
        //Listener Interface Methods Are Added Below 


        public void actionPerformed( java.awt.event.ActionEvent arg0)
        {
            values=null;
            times=null;
            data=null;
            sq.close(); 
        }
        //<UserCode_End_Connection_closeButton_closeButton_conn>
    }//<End__class_closeButton_closeButton_conn>

 


    //<Begin__class_stopButton_stopButton_conn>

    class stopButton_stopButton_conn implements java.awt.event.ActionListener, java.io.Serializable 
    {

        //<TOP_PART> - Please do not remove this comment or write any custom code above this

        //<UserCode_Begin_Connection_stopButton_stopButton_conn>
        //Listener Interface Methods Are Added Below 


        public void actionPerformed( java.awt.event.ActionEvent arg0)
        {
            if(stopButton.getText().equals("Stop"))
            {  
                stopButton.setText( "Start");  
                sq.stopPoll();   
            }   
            else
            {  
                try
                { 
                    long period=Long.parseLong(intervalField.getText());   
                    sq.setPollInterval(period);  
                }
                catch(Exception e) {}  
                stopButton.setText( "Stop");    
                sq.restartPoll();   
            }   
        }
        //<UserCode_End_Connection_stopButton_stopButton_conn>
    }//<End__class_stopButton_stopButton_conn>

 


    //<Begin__class_learButton_clearButton_conn>

    class clearButton_clearButton_conn implements java.awt.event.ActionListener, java.io.Serializable 
    {

        //<TOP_PART> - Please do not remove this comment or write any custom code above this

        //<UserCode_Begin_Connection_clearButton_clearButton_conn>
        //Listener Interface Methods Are Added Below 


        public void actionPerformed( java.awt.event.ActionEvent arg0)
        {
            clearList();

        }
        //<UserCode_End_Connection_clearButton_clearButton_conn>
    }//<End__class_clearButton_clearButton_conn>

 


    //<Begin__class_instCombo_instCombo_conn>

    class instCombo_instCombo_conn implements java.awt.event.ItemListener, java.io.Serializable 
    {

        //<TOP_PART> - Please do not remove this comment or write any custom code above this

        //<UserCode_Begin_Connection_instCombo_instCombo_conn>
        //Listener Interface Methods Are Added Below 


        public void itemStateChanged( java.awt.event.ItemEvent arg0)
        {
            String oidStr = oid+(String)instCombo.getSelectedItem();
            if(!(sq.getObjectID().equals(oidStr)))
            {
                sq.stopPoll();
                sq.setObjectID(oidStr);
                clearList();
                sq.restartPoll();
            } 
        }
        //<UserCode_End_Connection_instCombo_instCombo_conn>
    }//<End__class_instCombo_instCombo_conn>


    //<Begin__class_intervalField_intervalField_conn1>

    class intervalField_intervalField_conn1 extends java.awt.event.KeyAdapter implements java.io.Serializable 
    {

        //<TOP_PART> - Please do not remove this comment or write any custom code above this

        //<UserCode_Begin_Connection_intervalField_intervalField_conn1>
        //Listener Interface Methods Are Added Below 


        public void keyPressed( java.awt.event.KeyEvent arg0)
        {
            try
            { 
                long period=Long.parseLong(intervalField.getText());   
                sq.setPollInterval(period);  
            }
            catch(Exception e) {}
        }
        //<UserCode_End_Connection_intervalField_intervalField_conn1>
    }//<End__class_intervalField_intervalField_conn1>

  




    class sizeField_sizeField_conn1 extends java.awt.event.KeyAdapter implements java.io.Serializable 
    {

        //<TOP_PART> - Please do not remove this comment or write any custom code above this

        //<UserCode_Begin_Connection_sizeField_sizeField_conn1>
        //Listener Interface Methods Are Added Below 


        public void keyPressed( java.awt.event.KeyEvent arg0)
        {
            try
            {
                if(arg0.getKeyCode()==KeyEvent.VK_ENTER)
                {	  
                    int newsize=Integer.parseInt(sizeField.getText());
                    if(newsize==0)
                    { 
                        NmsClientUtil.err(NmsClientUtil.GetString("Window Size should be greater than 0")); 
                        return;
                    }
                    updateTable(newsize);
                } 
            }
            catch(Exception e) {}
        }
        //<UserCode_End_Connection_sizeField_sizeField_conn1>
    }//<End__class_sizeField_sizeField_conn1>

  

}
