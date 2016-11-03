//$Id: ProvisioningResult.java,v 1.1 2006/08/29 13:57:01 build Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.provisioning.ui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.table.*;

import com.adventnet.nms.provisioning.xml.*;
import com.adventnet.management.config.xml.*;
import com.adventnet.nms.util.NmsClientUtil;

public class ProvisioningResult extends JPanel 
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "ProvisioningClientResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JLabel JLabel3 = null;
	javax.swing.JLabel JLabel2 = null;
	javax.swing.JLabel JLabel4 = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	javax.swing.JTable JTable1 = null;
	//<End_Variable_Declarations>

	TemplateResult tr =null;
	Vector vtr = null;
	Vector vcr = null;
	CustomModel DefaultTableModel1= null;

  
	public ProvisioningResult(java.applet.Applet applet,java.lang.String statusString,java.lang.String resultString)
	{
		this.applet = applet;
		this.init();
		try
		{
			tr=new TemplateResult(resultString);
			vtr = tr.getSubTagsByName("ConfigResult");
			int numberOfSuccessConfigurations = 0;
			JLabel4.setText(vtr.size()+"");
			for (int i=0;i<vtr.size();i++)
			{
				ConfigResult cr = (ConfigResult)(vtr.elementAt(i));
				Vector newRow=new Vector();
				newRow.addElement(cr.getAttribute("taskName"));
				newRow.add(ProvClientUtils.getString("Configuration Successful"));
				vcr = cr.getSubTagsByName("DeviceResult"); 
				boolean noError = true;
				for (int j=0;((j<vcr.size())&&(noError));j++)
				{
					DeviceResult dr =(DeviceResult)(vcr.elementAt(j));
					if (!(dr.getAttribute("rollbackStatus").equals("")))
					{
						newRow.set(1,ProvClientUtils.getString("RollBack Attempted"));
						noError = false;
						break;
					}
					else
					{
						Vector arv= dr.getSubTagsByName("AttributeResult");
						for(int k=0;k<arv.size();k++)
						{
							AttributeResult ar = (AttributeResult)(arv.elementAt(k));
							if (!(ar.getAttribute("errorStatus").equals("0")))
							{
								newRow.set(1,ProvClientUtils.getString("Configuration Attempted"));
								noError = false;
								break;
							}
						}
					}
				}
				if (noError)
				{
					numberOfSuccessConfigurations++;
				}
				DefaultTableModel1.addRow(newRow);
			}
			if (tr.getAttribute("status").equalsIgnoreCase("SUCCEEDED"))
			{
                
				JLabel3.setText(ProvClientUtils.getString("Successfully Completed"));
				JLabel3.setForeground(Color.green.darker());
			}
			else
			{
				if (numberOfSuccessConfigurations == 0)
				{
					JLabel3.setText(ProvClientUtils.getString("Provisioning Failed"));
					JLabel3.setForeground(Color.red.darker());
				}
				else
				{
					JLabel3.setText(ProvClientUtils.getString("Partially Successful"));
					JLabel3.setForeground(Color.red.darker());
				}
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
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
	if(getParameter("RESOURCE_PROPERTIES" ) != null)
	{
		localePropertiesFileName = getParameter("RESOURCE_PROPERTIES");
	}
	resourceBundle = com.adventnet.apiutils.Utility.getBundle(localePropertiesFileName,getParameter("RESOURCE_LOCALE"),applet);
        if (initialized == true) return; 
        setPreferredSize(new Dimension(getPreferredSize().width+674,getPreferredSize().height+514)); 
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
          showStatus(ProvClientUtils.getString("Error in init method"),ex); 
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
            if (input.equals("RESOURCE_PROPERTIES")) value = "ProvisioningClientResources"; 
            }
        return value;

  
           //<End_getParameter_String>
  } 
  public void setUpProperties()
  {
  //<Begin_setUpProperties> 

          try
          {
            Top.setMinimumSize(new Dimension(600,520));
            Top.setPreferredSize(new Dimension(600,520));
          }
          catch(Exception ex)
          {
             showStatus(ProvClientUtils.getString("Exception while setting properties for bean"),ex); 
          }

          try
          {
            JLabel1.setHorizontalAlignment(2);
            JLabel1.setFont(new Font("SansSerif",0,12));
            JLabel1.setForeground(new Color(-16777216));
            JLabel1.setHorizontalTextPosition(4);
            JLabel1.setText(ProvClientUtils.getString("Executed Provisioning Template Status"));
          }
          catch(Exception ex)
          {
             showStatus(ProvClientUtils.getString("Exception while setting properties for bean"),ex); 
          }

          try
          {
            JLabel3.setHorizontalAlignment(2);
            JLabel3.setFont(new Font("SansSerif",0,12));
            JLabel3.setForeground(new Color(-16777216));
            JLabel3.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus(ProvClientUtils.getString("Exception while setting properties for bean"),ex); 
          }

          try
          {
            JLabel2.setHorizontalAlignment(2);
            JLabel2.setFont(new Font("SansSerif",0,12));
            JLabel2.setForeground(new Color(-16777216));
            JLabel2.setHorizontalTextPosition(4);
            JLabel2.setText(ProvClientUtils.getString("Number of Tasks Executed"));
          }
          catch(Exception ex)
          {
             showStatus(ProvClientUtils.getString("Exception while setting properties for bean"),ex); 
          }

          try
          {
            JLabel4.setHorizontalAlignment(2);
            JLabel4.setFont(new Font("SansSerif",0,12));
            JLabel4.setForeground(new Color(-16777216));
            JLabel4.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus(ProvClientUtils.getString("Exception while setting properties for bean"),ex); 
          }

          try
          {
            JScrollPane1.setMinimumSize(new Dimension(550,500));
            JScrollPane1.setPreferredSize(new Dimension(550,500));
          }
          catch(Exception ex)
          {
             showStatus(ProvClientUtils.getString("Exception while setting properties for bean"),ex); 
          }
Vector columnNameVector = new Vector();
columnNameVector.addElement(ProvClientUtils.getString("Task Name"));
columnNameVector.addElement(ProvClientUtils.getString("Provisioning Status"));
DefaultTableModel1 = new CustomModel();
JTable1.setModel(DefaultTableModel1);
DefaultTableModel1.setDataVector(new Vector(), columnNameVector);
JTableHeader h = JTable1.getTableHeader();
h.setPreferredSize(new Dimension(h.getWidth(),25));
JTable1.setSelectionMode(ListSelectionModel .SINGLE_SELECTION);
 		JPanel1.setPreferredSize(new Dimension(JPanel1.getPreferredSize().width+158,JPanel1.getPreferredSize().height+10));

  
          //<End_setUpProperties>
  } 
  public void initVariables()
  {
        //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        JPanel1= new javax.swing.JPanel();
        JLabel1= new javax.swing.JLabel();
        JLabel3= new javax.swing.JLabel();
        JLabel2= new javax.swing.JLabel();
        JLabel4= new javax.swing.JLabel();
        JScrollPane1= new javax.swing.JScrollPane();
        JTable1= new javax.swing.JTable();

  
        //<End_initVariables>
  } 
  public void setUpGUI(Container container)
  {
//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));
Top.add(JPanel1,BorderLayout.NORTH);
JPanel1.setLayout(new GridLayout(2,2,5,5));
JPanel1.add(JLabel1);
JPanel1.add(JLabel3);
JPanel1.add(JLabel2);
JPanel1.add(JLabel4);
Top.add(JScrollPane1,BorderLayout.CENTER);
JScrollPane1.getViewport().add(JTable1);

  
//<End_setUpGUI_Container>
  } 
  public void setUpConnections()
  {
  //<Begin_setUpConnections> 

      java.awt.event.MouseAdapter JTable1_JTable1_conn1 =  new java.awt.event.MouseAdapter()
  {

     //<TOP_PART> - Please do not remove this comment or write any custom code above this

     //Listener Interface Methods Are Added Below 


     public void mouseClicked( java.awt.event.MouseEvent arg0)
     {
   if(arg0.getClickCount()==2)
  {
    tableRowSelectedEvent();
  }
     }
};
      JTable1.addMouseListener(JTable1_JTable1_conn1);
  
      //<End_setUpConnections>
  } 



  
  public void showStatus(String message)
  {
     //<Begin_showStatus_String>
     System.out.println(message);
     //<End_showStatus_String>
  }
  public void showStatus(String message,Exception ex)
  {
     //<Begin_showStatus_String_Exception>
     System.out.println(message);
     ex.printStackTrace();
     //<End_showStatus_String_Exception>
  }





  public ProvisioningResult()
  {
    //<Begin_ProvisioningResult>
    this.init();
  
    //<End_ProvisioningResult>
  }

  public ProvisioningResult(java.applet.Applet applet)
  {
    //<Begin_ProvisioningResult_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_ProvisioningResult_java.applet.Applet>
  }


	public void tableRowSelectedEvent()
	{
		if (JTable1.getSelectedRowCount()<1)
		{
			JOptionPane.showMessageDialog(ProvisioningResult.this, ProvClientUtils.getString("Select a task to view its details"),ProvClientUtils.getString("Provisioning Result"),JOptionPane.INFORMATION_MESSAGE);
		}
		else
		{
			JDialog d=(new JOptionPane()).createDialog(this,
					ProvClientUtils.getString("Provisioning - Result Details"));
			d.getContentPane().removeAll();
			d.getContentPane().setLayout(new BorderLayout());
			ProvisioningDetails p= new ProvisioningDetails(applet,(ConfigResult)(vtr.elementAt(JTable1.getSelectedRow())));
			d.getContentPane().add(p,BorderLayout.CENTER);
			d.setSize(p.getSize());
			Dimension dd= getSize();
			Dimension prd=p.getSize();
			Point pd= getLocationOnScreen();
			int x=(int)(pd.getX()+(dd.getWidth()-prd.getWidth())/2);
			int y=(int)(pd.getY()+(dd.getHeight()-prd.getHeight())/2);
			d.setLocation(x,y);
			d.setResizable(false);
			d.setVisible(true);
			d.addWindowListener
			(
				new WindowAdapter()
				{
					public void windowDeactivated(WindowEvent e)
					{
						((JDialog)e.getSource()).removeWindowListener(this);
						((JDialog)e.getSource()).dispose();
					}
					public void windowClosed(WindowEvent e)
					{
						((JDialog)e.getSource()).removeWindowListener(this);
						((JDialog)e.getSource()).dispose();
					}
				}
			);
		}
  
	}
	class CustomModel extends DefaultTableModel
	{
		public boolean isCellEditable(int row, int col)
		{
			return false;
		}
	}
}


