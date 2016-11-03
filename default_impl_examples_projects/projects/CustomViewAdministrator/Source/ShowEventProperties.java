

// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.tools.CustomView;




import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import java.util.*;


public class ShowEventProperties extends JDialog implements ActionListener
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JCheckBox classChek = null;
	javax.swing.JTextField classField = null;
	javax.swing.JCheckBox messageChek = null;
	javax.swing.JTextField messageField = null;
	javax.swing.JCheckBox timeChek = null;
	javax.swing.JTextField timeField = null;
	javax.swing.JCheckBox categoryChek = null;
	javax.swing.JTextField categoryField = null;
	javax.swing.JCheckBox networkChek = null;
	javax.swing.JTextField networkField = null;
	javax.swing.JCheckBox entityChek = null;
	javax.swing.JTextField entityField = null;
	javax.swing.JCheckBox sourceChek = null;
	javax.swing.JTextField sourceField = null;
	javax.swing.JCheckBox helpurlChek = null;
	javax.swing.JTextField helpurlField = null;
	javax.swing.JCheckBox idChek = null;
	javax.swing.JTextField idField = null;
	javax.swing.JCheckBox domainChek = null;
	javax.swing.JTextField domainField = null;
	javax.swing.JCheckBox nodeChek = null;
	javax.swing.JTextField nodeField = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JButton bAdditionalProps = null;
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
        this.setSize(getPreferredSize().width+486,getPreferredSize().height+314); 
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
            classChek.setFont(new Font("SansSerif",0,12));
            classChek.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+classChek,ex); 
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
            messageChek.setFont(new Font("SansSerif",0,12));
            messageChek.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+messageChek,ex); 
          }

          try
          {
            messageField.setHorizontalAlignment(2);
            messageField.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+messageField,ex); 
          }

          try
          {
            timeChek.setFont(new Font("SansSerif",0,12));
            timeChek.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+timeChek,ex); 
          }

          try
          {
            timeField.setHorizontalAlignment(2);
            timeField.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+timeField,ex); 
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
            categoryField.setHorizontalAlignment(2);
            categoryField.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+categoryField,ex); 
          }

          try
          {
            networkChek.setFont(new Font("SansSerif",0,12));
            networkChek.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+networkChek,ex); 
          }

          try
          {
            networkField.setHorizontalAlignment(2);
            networkField.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+networkField,ex); 
          }

          try
          {
            entityChek.setFont(new Font("SansSerif",0,12));
            entityChek.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+entityChek,ex); 
          }

          try
          {
            entityField.setHorizontalAlignment(2);
            entityField.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+entityField,ex); 
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
            sourceField.setHorizontalAlignment(2);
            sourceField.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+sourceField,ex); 
          }

          try
          {
            helpurlChek.setFont(new Font("SansSerif",0,12));
            helpurlChek.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+helpurlChek,ex); 
          }

          try
          {
            helpurlField.setHorizontalAlignment(2);
            helpurlField.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+helpurlField,ex); 
          }

          try
          {
            idChek.setFont(new Font("SansSerif",0,12));
            idChek.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+idChek,ex); 
          }

          try
          {
            idField.setHorizontalAlignment(2);
            idField.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+idField,ex); 
          }

          try
          {
            domainChek.setFont(new Font("SansSerif",0,12));
            domainChek.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+domainChek,ex); 
          }

          try
          {
            domainField.setHorizontalAlignment(2);
            domainField.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+domainField,ex); 
          }

          try
          {
            nodeChek.setFont(new Font("SansSerif",0,12));
            nodeChek.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+nodeChek,ex); 
          }

          try
          {
            nodeField.setHorizontalAlignment(2);
            nodeField.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+nodeField,ex); 
          }

          try
          {
            JLabel1.setHorizontalAlignment(2);
            JLabel1.setFont(new Font("SansSerif",0,12));
            JLabel1.setForeground(new Color(-16777216));
            JLabel1.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+JLabel1,ex); 
          }

          try
          {
            bAdditionalProps.setFont(new Font("SansSerif",0,12));
            bAdditionalProps.setHorizontalTextPosition(4);
            bAdditionalProps.setText("Additional Properties");
            bAdditionalProps.setBorder(new javax.swing.border.BevelBorder(0));
            bAdditionalProps.setLabel("Additional table Columns");
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+bAdditionalProps,ex); 
          }

          try
          {
            ok.setFont(new Font("SansSerif",0,12));
            ok.setHorizontalTextPosition(4);
            ok.setText("OK");
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
            cancel.setBorder(new javax.swing.border.BevelBorder(0));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+cancel,ex); 
          }
		cancel.setPreferredSize(new Dimension(cancel.getPreferredSize().width+29,cancel.getPreferredSize().height+6));
		ok.setPreferredSize(new Dimension(ok.getPreferredSize().width+51,ok.getPreferredSize().height+6));
		bAdditionalProps.setPreferredSize(new Dimension(bAdditionalProps.getPreferredSize().width+24,bAdditionalProps.getPreferredSize().height+6));
		JPanel1.setPreferredSize(new Dimension(JPanel1.getPreferredSize().width+279,JPanel1.getPreferredSize().height+3));
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
        JPanel2= new javax.swing.JPanel();
        classChek= new javax.swing.JCheckBox();
        classField= new javax.swing.JTextField();
        messageChek= new javax.swing.JCheckBox();
        messageField= new javax.swing.JTextField();
        timeChek= new javax.swing.JCheckBox();
        timeField= new javax.swing.JTextField();
        categoryChek= new javax.swing.JCheckBox();
        categoryField= new javax.swing.JTextField();
        networkChek= new javax.swing.JCheckBox();
        networkField= new javax.swing.JTextField();
        entityChek= new javax.swing.JCheckBox();
        entityField= new javax.swing.JTextField();
        sourceChek= new javax.swing.JCheckBox();
        sourceField= new javax.swing.JTextField();
        helpurlChek= new javax.swing.JCheckBox();
        helpurlField= new javax.swing.JTextField();
        idChek= new javax.swing.JCheckBox();
        idField= new javax.swing.JTextField();
        domainChek= new javax.swing.JCheckBox();
        domainField= new javax.swing.JTextField();
        nodeChek= new javax.swing.JCheckBox();
        nodeField= new javax.swing.JTextField();
        JLabel1= new javax.swing.JLabel();
        JPanel1= new javax.swing.JPanel();
        bAdditionalProps= new javax.swing.JButton();
        JPanel4= new javax.swing.JPanel();
        ok= new javax.swing.JButton();
        cancel= new javax.swing.JButton();

  
        //<End_initVariables>
bAdditionalProps.addActionListener(this);
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
JPanel3.add(JPanel2,BorderLayout.CENTER);
JPanel2.setLayout(new GridBagLayout());
inset = new Insets(15,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel2.add(classChek,cons);
inset = new Insets(15,5,5,5);
setConstraints(1,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel2.add(classField,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel2.add(messageChek,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,1,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel2.add(messageField,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel2.add(timeChek,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,2,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel2.add(timeField,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,3,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel2.add(categoryChek,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,3,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel2.add(categoryField,cons);
inset = new Insets(15,5,5,5);
setConstraints(2,0,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel2.add(networkChek,cons);
inset = new Insets(15,5,5,5);
setConstraints(3,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel2.add(networkField,cons);
inset = new Insets(5,5,5,5);
setConstraints(2,1,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel2.add(entityChek,cons);
inset = new Insets(5,5,5,5);
setConstraints(3,1,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel2.add(entityField,cons);
inset = new Insets(5,5,5,5);
setConstraints(2,2,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel2.add(sourceChek,cons);
inset = new Insets(5,5,5,5);
setConstraints(3,2,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel2.add(sourceField,cons);
inset = new Insets(5,5,5,5);
setConstraints(2,3,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel2.add(helpurlChek,cons);
inset = new Insets(5,5,5,5);
setConstraints(3,3,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel2.add(helpurlField,cons);
inset = new Insets(5,5,5,5);
setConstraints(2,4,1,1,0.0,0.0,cons.CENTER,cons.NONE,inset,0,0);
JPanel2.add(idChek,cons);
inset = new Insets(5,5,5,5);
setConstraints(3,4,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel2.add(idField,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,4,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel2.add(domainChek,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,4,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel2.add(domainField,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,5,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
JPanel2.add(nodeChek,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,5,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel2.add(nodeField,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,6,0,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel2.add(JLabel1,cons);
JPanel3.add(JPanel1,BorderLayout.SOUTH);
JPanel1.setLayout(new FlowLayout(2,10,5));
JPanel1.add(bAdditionalProps);
Top.add(JPanel4,BorderLayout.SOUTH);
JPanel4.setLayout(new FlowLayout(1,5,5));
JPanel4.add(ok);
JPanel4.add(cancel);

  
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
public void setProperties(Properties displayName,Properties checkedColumns)
	{
		setDisplayNames(displayName);
		setSelection(checkedColumns);

	}
public Properties getColumns()
{
	return selectedColumns;	
}


	public void setDisplayNames(Properties displayName)
	{
		Vector vec=new Vector();
		if(displayName.get("severity")!=null)
		{
			classField.setText((String)displayName.get("severity"));
			vec.add("severity");	
		}
		if(displayName.get("text")!=null)
		{
			messageField.setText((String)displayName.get("text"));
			vec.add("text");	
		}
		if(displayName.get("time")!=null)
		{
			timeField.setText((String)displayName.get("time"));
			vec.add("time");	
		}
		if(displayName.get("category")!=null)
		{
			categoryField.setText((String)displayName.get("category"));
			vec.add("category");	
		}
		if(displayName.get("network")!=null)
		{
			networkField.setText((String)displayName.get("network"));
			vec.add("network");	
		}
		if(displayName.get("entity")!=null)
		{
			entityField.setText((String)displayName.get("entity"));
			vec.add("entity");	
		}
		if(displayName.get("source")!=null)
		{
			sourceField.setText((String)displayName.get("source"));
			vec.add("source");	
		}
		if(displayName.get("helpURL")!=null)
		{
			helpurlField.setText((String)displayName.get("helpURL"));
			vec.add("helpURL");	
		}
		if(displayName.get("id")!=null)
		{
			idField.setText((String)displayName.get("id"));
			vec.add("id");	
		}
		if(displayName.get("domain")!=null)
		{
			domainField.setText((String)displayName.get("domain"));
			vec.add("domain");	
		}
		if(displayName.get("node")!=null)
		{
			nodeField.setText((String)displayName.get("node"));
			vec.add("node");	
		}
		additionalColumns=displayName;
		selectedProps=vec;
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
		if(checkedColumns.get("text")!=null)
		{
			messageChek.setSelected(true);
		}
		else
		{
			messageChek.setSelected(false);
		}
		if(checkedColumns.get("time")!=null)
		{
			timeChek.setSelected(true);
		}
		else
		{
			timeChek.setSelected(false);
		}
		if(checkedColumns.get("category")!=null)
		{
			categoryChek.setSelected(true);
		}
		else
		{
			categoryChek.setSelected(false);
		}
		if(checkedColumns.get("entiry")!=null)
		{
			entityChek.setSelected(true);
		}
		else
		{
			entityChek.setSelected(false);
		}
		if(checkedColumns.get("source")!=null)
		{
			sourceChek.setSelected(true);
		}
		else
		{
			sourceChek.setSelected(false);
		}
		if(checkedColumns.get("helpURL")!=null)
		{
			helpurlChek.setSelected(true);
		}
		else
		{
			helpurlChek.setSelected(false);
		}
		if(checkedColumns.get("id")!=null)
		{
			idChek.setSelected(true);
		}
		else
		{
			idChek.setSelected(false);
		}
		if(checkedColumns.get("domain")!=null)
		{
			domainChek.setSelected(true);
		}
		else
		{
			domainChek.setSelected(false);
		}
		if(checkedColumns.get("node")!=null)
		{
			nodeChek.setSelected(true);
		}
		else
		{
			nodeChek.setSelected(false);
		}
		
	}
	public Properties getSelectedColumns()
	{
		selectedColumns=new Properties();
		if(nodeChek.isSelected())
		{
			selectedColumns.put("node",nodeField.getText());
		}
		if(categoryChek.isSelected())
		{
			selectedColumns.put("category",categoryField.getText());
		}
		if(classChek.isSelected())
		{
			selectedColumns.put("severity",classField.getText());
		}

		if(messageChek.isSelected())
		{
			selectedColumns.put("text",messageField.getText());
		}
		if(timeChek.isSelected())
		{
			selectedColumns.put("time",timeField.getText());
		}
		if(networkChek.isSelected())
		{
			selectedColumns.put("network",networkField.getText());
		}
		if(entityChek.isSelected())
		{
			selectedColumns.put("entity",entityField.getText());
		}
		if(sourceChek.isSelected())
		{
			selectedColumns.put("source",sourceField.getText());
		}
		if(helpurlChek.isSelected())
		{
			selectedColumns.put("helpURL",helpurlField.getText());
		}
		if(idChek.isSelected())
		{
			selectedColumns.put("id",idField.getText());
		}
		if(domainChek.isSelected())
		{
			selectedColumns.put("domain",domainField.getText());
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
public void actionPerformed(ActionEvent e)
{
	if(e.getSource().equals(bAdditionalProps))
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


  public ShowEventProperties()
  {
    //<Begin_ShowEventProperties>
    pack();
    this.setTitle("ShowEventProperties");
  
    //<End_ShowEventProperties>
this.init();
  }

  public ShowEventProperties(java.applet.Applet applet)
  {
    //<Begin_ShowEventProperties_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setTitle("ShowEventProperties");
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_ShowEventProperties_java.applet.Applet>
  }
}























