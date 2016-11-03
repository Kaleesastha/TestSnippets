
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.tools.CustomView;




import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.event.*;


public class ShowAlertProperties extends JDialog implements ActionListener
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JButton AdditionalProps = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JTextField dateField = null;
	javax.swing.JTextField AlertMsgField = null;
	javax.swing.JTextField ownerField = null;
	javax.swing.JTextField classField = null;
	javax.swing.JTextField prevSevField = null;
	javax.swing.JTextField FailureObjField = null;
	javax.swing.JCheckBox grpNameChek = null;
	javax.swing.JCheckBox AlertMsgChek = null;
	javax.swing.JCheckBox prevSevChek = null;
	javax.swing.JCheckBox ownerChek = null;
	javax.swing.JCheckBox createTimeChek = null;
	javax.swing.JTextField createTimeField = null;
	javax.swing.JCheckBox classChek = null;
	javax.swing.JCheckBox sourceChek = null;
	javax.swing.JCheckBox dateChek = null;
	javax.swing.JTextField grpNameField = null;
	javax.swing.JCheckBox categoryChek = null;
	javax.swing.JTextField sourceField = null;
	javax.swing.JCheckBox failObjChek = null;
	javax.swing.JLabel JLabel2 = null;
	javax.swing.JTextField categoryField = null;
	javax.swing.JPanel JPanel4 = null;
	javax.swing.JButton ok = null;
	javax.swing.JButton cancel = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>
             
          AdditionalCriteria criteria=null;
          Properties additionalColumns=null;
	Vector selectedProps=null;
	Properties selectedColumns=null;
  


 

  

    public void init()
  {
        //<Begin_init> 
        if (initialized == true) return; 
        this.setSize(getPreferredSize().width+521,getPreferredSize().height+308); 
          setTitle("");
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
	this.setModal(true);
	Toolkit kit=Toolkit.getDefaultToolkit();
	Dimension dim=kit.getScreenSize();
	
	this.setLocation((dim.width-(int)this.getSize().getWidth())/2,(dim.height-(int)this.getSize().getHeight())/2);
	
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
            JPanel3.setBorder(new javax.swing.border.EtchedBorder(0));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+JPanel3,ex); 
          }

          try
          {
            AdditionalProps.setFont(new Font("SansSerif",0,12));
            AdditionalProps.setHorizontalTextPosition(4);
            AdditionalProps.setBorder(new javax.swing.border.BevelBorder(0));
            AdditionalProps.setPreferredSize(new Dimension(150,27));
            AdditionalProps.setMinimumSize(new Dimension(150,27));
            AdditionalProps.setMaximumSize(new Dimension(150,27));
            AdditionalProps.setText("Additional table columns");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+AdditionalProps,ex); 
          }

          try
          {
            dateField.setHorizontalAlignment(2);
            dateField.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+dateField,ex); 
          }

          try
          {
            AlertMsgField.setHorizontalAlignment(2);
            AlertMsgField.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+AlertMsgField,ex); 
          }

          try
          {
            ownerField.setHorizontalAlignment(2);
            ownerField.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+ownerField,ex); 
          }

          try
          {
            classField.setHorizontalAlignment(2);
            classField.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+classField,ex); 
          }

          try
          {
            prevSevField.setHorizontalAlignment(2);
            prevSevField.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+prevSevField,ex); 
          }

          try
          {
            FailureObjField.setHorizontalAlignment(2);
            FailureObjField.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+FailureObjField,ex); 
          }

          try
          {
            grpNameChek.setFont(new Font("SansSerif",0,12));
            grpNameChek.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+grpNameChek,ex); 
          }

          try
          {
            AlertMsgChek.setFont(new Font("SansSerif",0,12));
            AlertMsgChek.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+AlertMsgChek,ex); 
          }

          try
          {
            prevSevChek.setFont(new Font("SansSerif",0,12));
            prevSevChek.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+prevSevChek,ex); 
          }

          try
          {
            ownerChek.setFont(new Font("SansSerif",0,12));
            ownerChek.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+ownerChek,ex); 
          }

          try
          {
            createTimeChek.setFont(new Font("SansSerif",0,12));
            createTimeChek.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+createTimeChek,ex); 
          }

          try
          {
            createTimeField.setHorizontalAlignment(2);
            createTimeField.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+createTimeField,ex); 
          }

          try
          {
            classChek.setFont(new Font("SansSerif",0,12));
            classChek.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+classChek,ex); 
          }

          try
          {
            sourceChek.setFont(new Font("SansSerif",0,12));
            sourceChek.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+sourceChek,ex); 
          }

          try
          {
            dateChek.setFont(new Font("SansSerif",0,12));
            dateChek.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+dateChek,ex); 
          }

          try
          {
            grpNameField.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+grpNameField,ex); 
          }

          try
          {
            categoryChek.setFont(new Font("SansSerif",0,12));
            categoryChek.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+categoryChek,ex); 
          }

          try
          {
            sourceField.setHorizontalAlignment(2);
            sourceField.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+sourceField,ex); 
          }

          try
          {
            failObjChek.setFont(new Font("SansSerif",0,12));
            failObjChek.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+failObjChek,ex); 
          }

          try
          {
            JLabel2.setHorizontalAlignment(2);
            JLabel2.setFont(new Font("SansSerif",0,12));
            JLabel2.setForeground(new Color(-16777216));
            JLabel2.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+JLabel2,ex); 
          }

          try
          {
            categoryField.setHorizontalAlignment(2);
            categoryField.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+categoryField,ex); 
          }

          try
          {
            ok.setFont(new Font("SansSerif",0,12));
            ok.setHorizontalTextPosition(4);
            ok.setText("OK");
            ok.setMaximumSize(new Dimension(43,27));
            ok.setMinimumSize(new Dimension(43,27));
            ok.setPreferredSize(new Dimension(43,27));
            ok.setBorder(new javax.swing.border.BevelBorder(0));
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
            cancel.setMaximumSize(new Dimension(43,27));
            cancel.setMinimumSize(new Dimension(43,27));
            cancel.setPreferredSize(new Dimension(43,27));
            cancel.setBorder(new javax.swing.border.BevelBorder(0));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+cancel,ex); 
          }
		cancel.setPreferredSize(new Dimension(cancel.getPreferredSize().width+35,cancel.getPreferredSize().height+2));
		ok.setPreferredSize(new Dimension(ok.getPreferredSize().width+34,ok.getPreferredSize().height+4));
		JPanel2.setPreferredSize(new Dimension(JPanel2.getPreferredSize().width+4,JPanel2.getPreferredSize().height+4));
		AdditionalProps.setPreferredSize(new Dimension(AdditionalProps.getPreferredSize().width+0,AdditionalProps.getPreferredSize().height+6));
		JPanel1.setPreferredSize(new Dimension(JPanel1.getPreferredSize().width+10,JPanel1.getPreferredSize().height+4));
		JPanel3.setPreferredSize(new Dimension(JPanel3.getPreferredSize().width+10,JPanel3.getPreferredSize().height+10));

  
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
        JPanel3= new javax.swing.JPanel();
        JPanel1= new javax.swing.JPanel();
        AdditionalProps= new javax.swing.JButton();
        JPanel2= new javax.swing.JPanel();
        dateField= new javax.swing.JTextField();
        AlertMsgField= new javax.swing.JTextField();
        ownerField= new javax.swing.JTextField();
        classField= new javax.swing.JTextField();
        prevSevField= new javax.swing.JTextField();
        FailureObjField= new javax.swing.JTextField();
        grpNameChek= new javax.swing.JCheckBox();
        AlertMsgChek= new javax.swing.JCheckBox();
        prevSevChek= new javax.swing.JCheckBox();
        ownerChek= new javax.swing.JCheckBox();
        createTimeChek= new javax.swing.JCheckBox();
        createTimeField= new javax.swing.JTextField();
        classChek= new javax.swing.JCheckBox();
        sourceChek= new javax.swing.JCheckBox();
        dateChek= new javax.swing.JCheckBox();
        grpNameField= new javax.swing.JTextField();
        categoryChek= new javax.swing.JCheckBox();
        sourceField= new javax.swing.JTextField();
        failObjChek= new javax.swing.JCheckBox();
        JLabel2= new javax.swing.JLabel();
        categoryField= new javax.swing.JTextField();
        JPanel4= new javax.swing.JPanel();
        ok= new javax.swing.JButton();
        cancel= new javax.swing.JButton();

  
        //<End_initVariables>
         AdditionalProps.addActionListener(this);	
	ok.addActionListener(this);
	cancel.addActionListener(this);

	
  } 
  public void setUpGUI(Container container)
  {
//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));
Top.add(JPanel3,BorderLayout.CENTER);
JPanel3.setLayout(new BorderLayout(5,5));
JPanel3.add(JPanel1,BorderLayout.SOUTH);
JPanel1.setLayout(new FlowLayout(2,10,5));
JPanel1.add(AdditionalProps);
JPanel3.add(JPanel2,BorderLayout.CENTER);
JPanel2.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(3,1,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel2.add(dateField,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,2,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel2.add(AlertMsgField,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,1,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel2.add(ownerField,cons);
inset = new Insets(15,5,5,5);
setConstraints(1,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel2.add(classField,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,3,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel2.add(prevSevField,cons);
inset = new Insets(15,5,5,5);
setConstraints(3,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel2.add(FailureObjField,cons);
inset = new Insets(5,5,5,5);
setConstraints(2,2,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel2.add(grpNameChek,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel2.add(AlertMsgChek,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,3,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel2.add(prevSevChek,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel2.add(ownerChek,cons);
inset = new Insets(5,5,5,5);
setConstraints(2,4,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel2.add(createTimeChek,cons);
inset = new Insets(5,5,5,5);
setConstraints(3,4,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel2.add(createTimeField,cons);
inset = new Insets(15,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel2.add(classChek,cons);
inset = new Insets(5,5,5,5);
setConstraints(2,3,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel2.add(sourceChek,cons);
inset = new Insets(5,5,5,5);
setConstraints(2,1,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel2.add(dateChek,cons);
inset = new Insets(5,5,5,5);
setConstraints(3,2,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel2.add(grpNameField,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,4,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel2.add(categoryChek,cons);
inset = new Insets(5,5,5,5);
setConstraints(3,3,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel2.add(sourceField,cons);
inset = new Insets(15,5,5,5);
setConstraints(2,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel2.add(failObjChek,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,5,0,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel2.add(JLabel2,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,4,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel2.add(categoryField,cons);
Top.add(JPanel4,BorderLayout.SOUTH);
JPanel4.setLayout(new FlowLayout(1,10,5));
JPanel4.add(ok);
JPanel4.add(cancel);

  
//<End_setUpGUI_Container>
		
 // AlertProperties ip=new AlertProperties();
  //setProperties(ip.getIdVsDisplayNames(),ip.getCheckedColumns());	
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

 public void showProperties(Properties p)
 {
	int i=0;		
	Top.removeAll();	
	Insets insets=new Insets(5,5,5,5);
	/*for(Enumeration e=p.propertyNames();e.hasMoreElements();)
	{
	      String str=e.nextElement().toString();	
	      JCheckBox "c"+str=new JCheckBox();
                setConstraints(0,i,cons.RELATIVE,1,0.0,0.0,cons.WEST,cons.NONE,insets,0,0);	
	      Top.add(Top,str);
	      str=p.getProperty(str);	
	      JTextField "t"+str=new JTextField(str);
                setConstraints(1,i,cons.REMAINDER,1,0.0,0.0,cons.WEST,cons.NONE,insets,0,0);	
                 Top.add(Top,str);
                i++;		 
	}*/
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




  public ShowAlertProperties()
  {
    //<Begin_ShowAlertProperties>
    pack();
    this.setTitle("ShowAlertProperties");
  
    //<End_ShowAlertProperties>
this.init();
  }

  public ShowAlertProperties(java.applet.Applet applet)
  {
    //<Begin_ShowAlertProperties_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setTitle("ShowAlertProperties");
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_ShowAlertProperties_java.applet.Applet>
  }
public void actionPerformed(ActionEvent e)
{
	if(e.getSource().equals(AdditionalProps))
	{
		if(criteria==null)
		{
			criteria=new AdditionalCriteria("Additional Table Columns",additionalColumns,selectedProps);
		}
		else
		{
			
			Properties prop=criteria.getProps();
			criteria=new AdditionalCriteria("Additonal Table Columns",prop,null);
		}
		criteria.setVisible(true);
	}
	if(e.getSource().equals(ok))
	{
		selectedColumns=getSelectedColumns();
		setVisible(false);	
	}
	if(e.getSource().equals(cancel))
	{
		setVisible(false);
	}
}
public Properties getColumns()
{
	return selectedColumns;	
}
public void setProperties(Properties displayName,Properties checkedColumns)
	{
		setDisplayNames(displayName);
		setSelection(checkedColumns);

	}
	public void setDisplayNames(Properties displayName)
	{
		Vector vec=new Vector();
		if(displayName.get("severity")!=null)
		{
			classField.setText((String)displayName.get("severity"));
			vec.add("severity");	
		}
		if(displayName.get("who")!=null)
		{
			ownerField.setText((String)displayName.get("who"));
			vec.add("who");	
		}
		if(displayName.get("message")!=null)
		{
			AlertMsgField.setText((String)displayName.get("message"));
			vec.add("message");	
		}
		if(displayName.get("previousSeverity")!=null)
		{
			prevSevField.setText((String)displayName.get("previousSeverity"));
			vec.add("previousSeverity");	
		}
		if(displayName.get("category")!=null)
		{
			categoryField.setText((String)displayName.get("category"));
			vec.add("category");	
		}
		if(displayName.get("entity")!=null)
		{
	          		FailureObjField.setText((String)displayName.get("entity"));
			vec.add("entity");	
		}
		if(displayName.get("modTime")!=null)
		{
			dateField.setText((String)displayName.get("modTime"));
			vec.add("modTime");	
		}
		if(displayName.get("groupName")!=null)
		{
			grpNameField.setText((String)displayName.get("groupName"));
			vec.add("groupName");	
		}
		if(displayName.get("source")!=null)
		{
			sourceField.setText((String)displayName.get("source"));
			vec.add("source");	
		}
		if(displayName.get("createTime")!=null)
		{
			createTimeField.setText((String)displayName.get("createTime"));
			vec.add("createTime");	
		}
		selectedProps=vec;
		additionalColumns=displayName;
	}

	public void setSelection(Properties checkedColumns)
	{
		selectedColumns=checkedColumns;
		if(checkedColumns.get("severity")!=null)
		{
			classChek.setSelected(true);
		}
		else
		{
			classChek.setSelected(false);
		}
		if(checkedColumns.get("who")!=null)
		{
			ownerChek.setSelected(true);
		}
		else
		{
			ownerChek.setSelected(false);
		}
		if(checkedColumns.get("message")!=null)
		{
			AlertMsgChek.setSelected(true);
		}
		else
		{
			AlertMsgChek.setSelected(false);
		}
		if(checkedColumns.get("previousSeverity")!=null)
		{
			prevSevChek.setSelected(true);
		}
		else
		{
			prevSevChek.setSelected(false);
		}
		if(checkedColumns.get("category")!=null)
		{
			categoryChek.setSelected(true);
		}
		else
		{
			categoryChek.setSelected(false);
		}
		if(checkedColumns.get("entity")!=null)
		{
			failObjChek.setSelected(true);
		}
		else
		{
			failObjChek.setSelected(false);
		}
		if(checkedColumns.get("modTime")!=null)
		{
			dateChek.setSelected(true);
		}
		else
		{
			dateChek.setSelected(false);
		}
		if(checkedColumns.get("groupName")!=null)
		{
			grpNameChek.setSelected(true);
		}
		else
		{
			grpNameChek.setSelected(false);
		}
		if(checkedColumns.get("source")!=null)
		{
			sourceChek.setSelected(true);
		}
		else
		{
			sourceChek.setSelected(false);
		}
		if(checkedColumns.get("createTime")!=null)
		{
			createTimeChek.setSelected(true);
		}
		else
		{
			createTimeChek.setSelected(false);
		}
		
	}
	public Properties getSelectedColumns()
	{
		
		selectedColumns=new Properties();
		if(classChek.isSelected())
		{
			selectedColumns.put("severity",classField.getText());
		}
		if(ownerChek.isSelected())
		{
			selectedColumns.put("who",ownerField.getText());
		}
		if(AlertMsgChek.isSelected())
		{
			selectedColumns.put("message",AlertMsgField.getText());
		}

		if(prevSevChek.isSelected())
		{
			selectedColumns.put("previousSeverity",prevSevField.getText());
		}
		if(categoryChek.isSelected())
		{
			selectedColumns.put("category",categoryField.getText());
		}
		if(failObjChek.isSelected())
		{
			selectedColumns.put("entity",FailureObjField.getText());
		}
		if(dateChek.isSelected())
		{
			selectedColumns.put("modTime",dateField.getText());
		}
		if(grpNameChek.isSelected())
		{
			selectedColumns.put("groupName",grpNameField.getText());
		}
		if(sourceChek.isSelected())
		{
			selectedColumns.put("source",sourceField.getText());
		}
		if(createTimeChek.isSelected())
		{
			selectedColumns.put("createTime",createTimeField.getText());
		}
		if(criteria!=null && criteria.getProps().size()>0)
		{
	        		Properties properties=criteria.getProps();
		          for(Enumeration e=properties.propertyNames();e.hasMoreElements();)
		        	{
			      String str=(String)e.nextElement();
		                selectedColumns.put(str,properties.get(str));
	        	          }
		}
		return selectedColumns;
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
}










































