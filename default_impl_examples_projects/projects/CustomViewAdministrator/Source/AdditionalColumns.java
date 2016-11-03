//$Id: AdditionalColumns.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory




package com.adventnet.nms.tools.CustomView;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;



public class AdditionalColumns extends JDialog implements ActionListener
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JPanel JPanel4 = null;
	javax.swing.JButton more = null;
	javax.swing.JButton fewer = null;
	javax.swing.JButton remove = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JCheckBox JCheckBox1 = null;
	javax.swing.JPanel JPanel5 = null;
	javax.swing.JTextField JTextField11 = null;
	javax.swing.JTextField JTextField1 = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JButton ok = null;
	javax.swing.JButton cancel = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>
Vector finalVector=null;
	int count=0;


  

  


  


 

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
        if (initialized == true) return; 
        this.setSize(getPreferredSize().width+468,getPreferredSize().height+235); 
          setTitle("User defined table columns");
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
        // let us set the initialzed variable to true so  
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
  public void setUpProperties()
  {
  //<Begin_setUpProperties> 

          try
          {
            JPanel1.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.LineBorder(new Color(-16777216),1),"Specify additional table columns",1,0,new Font("Dialog",0,12),new Color(-16764109)));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+JPanel1,ex); 
          }

          try
          {
            more.setFont(new Font("SansSerif",0,12));
            more.setHorizontalTextPosition(4);
            more.setText("More");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+more,ex); 
          }

          try
          {
            fewer.setFont(new Font("SansSerif",0,12));
            fewer.setHorizontalTextPosition(4);
            fewer.setText("Fewer");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+fewer,ex); 
          }

          try
          {
            remove.setFont(new Font("SansSerif",0,12));
            remove.setHorizontalTextPosition(4);
            remove.setText("Remove");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+remove,ex); 
          }

          try
          {
            JCheckBox1.setFont(new Font("SansSerif",0,12));
            JCheckBox1.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+JCheckBox1,ex); 
          }

          try
          {
            JTextField11.setHorizontalAlignment(2);
            JTextField11.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+JTextField11,ex); 
          }

          try
          {
            JTextField1.setHorizontalAlignment(2);
            JTextField1.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+JTextField1,ex); 
          }

          try
          {
            ok.setFont(new Font("SansSerif",0,12));
            ok.setHorizontalTextPosition(4);
            ok.setText("OK");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+ok,ex); 
          }

          try
          {
            cancel.setFont(new Font("SansSerif",0,12));
            cancel.setHorizontalTextPosition(4);
            cancel.setText("Cancel");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+cancel,ex); 
          }
		JPanel1.setPreferredSize(new Dimension(JPanel1.getPreferredSize().width+10,JPanel1.getPreferredSize().height+10));

  
          //<End_setUpProperties>
  } 
  public void start()
  {
  //<Begin_start> 

  
  //<End_start>
  } 
  public void stop()
  {
  //<Begin_stop> 

  
  //<End_stop>
  } 
  public void initVariables()
  {
        //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        JPanel1= new javax.swing.JPanel();
        JPanel4= new javax.swing.JPanel();
        more= new javax.swing.JButton();
        fewer= new javax.swing.JButton();
        remove= new javax.swing.JButton();
        JScrollPane1= new javax.swing.JScrollPane();
        JPanel3= new javax.swing.JPanel();
        JCheckBox1= new javax.swing.JCheckBox();
        JPanel5= new javax.swing.JPanel();
        JTextField11= new javax.swing.JTextField();
        JTextField1= new javax.swing.JTextField();
        JPanel2= new javax.swing.JPanel();
        ok= new javax.swing.JButton();
        cancel= new javax.swing.JButton();

  
        //<End_initVariables>
	finalVector=new Vector();
	more.addActionListener(this);
	fewer.addActionListener(this);
	fewer.setEnabled(false);
  } 
  public void setUpGUI(Container container)
  {
//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));
Top.add(JPanel1,BorderLayout.CENTER);
JPanel1.setLayout(new BorderLayout(5,5));
JPanel1.add(JPanel4,BorderLayout.SOUTH);
JPanel4.setLayout(new FlowLayout(1,5,5));
JPanel4.add(more);
JPanel4.add(fewer);
JPanel4.add(remove);
JPanel1.add(JScrollPane1,BorderLayout.CENTER);
JScrollPane1.getViewport().add(JPanel3);
JPanel3.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel3.add(JCheckBox1,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,0,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel3.add(JPanel5,cons);
JPanel5.setLayout(new GridLayout(1,0,5,5));
JPanel5.add(JTextField11);
JPanel5.add(JTextField1);
Top.add(JPanel2,BorderLayout.SOUTH);
JPanel2.setLayout(new FlowLayout(1,5,5));
JPanel2.add(ok);
JPanel2.add(cancel);

  
//<End_setUpGUI_Container>
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





  public AdditionalColumns()
  {
    //<Begin_AdditionalColumns>
    pack();
    this.setTitle("AdditionalColumns");
  
    //<End_AdditionalColumns>
  }

  public AdditionalColumns(java.applet.Applet applet)
  {
    //<Begin_AdditionalColumns_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setTitle("AdditionalColumns");
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_AdditionalColumns_java.applet.Applet>
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
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource().equals(more))
		{
			count++;
			addRow(count);
			JPanel3.updateUI();
			fewer.setEnabled(true);
		}
		if(e.getSource().equals(fewer))
		{
			count--;
			removeRow(count);
			JPanel3.updateUI();
			if(count==0)
			{
				fewer.setEnabled(false);
			}
		}	
	}
	public void addRow(int i)
	{

		Vector v=new Vector();
		v.add(new JCheckBox());
		v.add(new JTextField());
		v.add(new JTextField());
		v.add(new JPanel());
		finalVector.add(v);
		setConstraints(0,i,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
		JPanel3.add(((JCheckBox)v.elementAt(0)),cons);
		inset = new Insets(5,5,5,5);
		JPanel panel=(JPanel)v.elementAt(3);
		setConstraints(1,i,0,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
		JPanel3.add(panel,cons);
		panel.setLayout(new GridLayout(1,0,5,5));
		panel.add((JTextField)v.elementAt(1));
		panel.add((JTextField)v.elementAt(2));
		
	}
	public void removeRow(int i)
	{
		
		Vector v=(Vector)finalVector.elementAt(i);
		JPanel3.remove((JCheckBox)v.elementAt(0));
		JPanel3.remove((JTextField)v.elementAt(1));
		JPanel3.remove((JTextField)v.elementAt(2));
		JPanel3.remove((JPanel)v.elementAt(3));
		finalVector.remove(i);
	}
}







