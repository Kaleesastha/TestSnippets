//$Id: ProvisioningDetails.java,v 1.1 2006/08/29 13:57:01 build Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.provisioning.ui;

import java.text.MessageFormat;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.table.*;
import java.util.*;

import com.adventnet.management.config.xml.*;
import com.adventnet.nms.provisioning.xml.*;
import javax.swing.border.*;
import com.adventnet.nms.util.NmsClientUtil;

public class ProvisioningDetails extends JPanel implements Icon
{
    //<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "ProvisioningClientResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JLabel HeaderLabel = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JPanel JListPanel = null;
	javax.swing.JLabel JLabel2 = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	javax.swing.JList JList1 = null;
	javax.swing.JPanel TablePanel = null;
	javax.swing.JPanel StatusPanel = null;
	javax.swing.JLabel JLabel3 = null;
	javax.swing.JLabel JLabel6 = null;
	javax.swing.JLabel JLabel4 = null;
	javax.swing.JLabel JLabel5 = null;
	javax.swing.JLabel JLabel7 = null;
	javax.swing.JLabel JLabel8 = null;
	javax.swing.JTabbedPane JTabbedPane1 = null;
	javax.swing.JScrollPane JScrollPane2 = null;
	javax.swing.JTable JTable1 = null;
	javax.swing.JScrollPane JScrollPane3 = null;
	javax.swing.JEditorPane JEditorPane1 = null;
	javax.swing.JPanel JPanel5 = null;
	javax.swing.JPanel JPanel6 = null;
	javax.swing.JLabel JLabel9 = null;
	javax.swing.JLabel JLabel10 = null;
	javax.swing.JLabel JLabel11 = null;
	javax.swing.JPanel JPanel7 = null;
	javax.swing.JButton JButton1 = null;
	//<End_Variable_Declarations>

    Vector vdr = null;
    CustomModel DefaultTableModel1= null;
    CustomListRenderer DefaultListRenderer1= null;


    public ProvisioningDetails()
  {
        //<Begin_ProvisioningDetails>
    this.init();
  
    //<End_ProvisioningDetails>
    }

    public ProvisioningDetails(java.applet.Applet applet)
  {
        //<Begin_ProvisioningDetails_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_ProvisioningDetails_java.applet.Applet>
    }
    public ProvisioningDetails(java.applet.Applet applet,ConfigResult taskResult)
    {
        this.applet = applet;
        this.init();
        try
        {
            vdr = taskResult.getSubTagsByName("DeviceResult");
            Vector JListVector =new Vector();
            for (int i=0;i<vdr.size();i++)
            {
                DeviceResult dr = (DeviceResult)(vdr.elementAt(i));
                JLabel l = new JLabel(dr.getAttribute("deviceName"));
                l.setBackground(JList1.getBackground());
                if (dr.getAttribute("configuration").equalsIgnoreCase("IGNORED"))
                {
                    l.setForeground(Color.gray);
                }
                else
                {
                    l.setForeground(Color.green.darker());
                    Vector arv= dr.getSubTagsByName("AttributeResult");
                    for(int k=0;k<arv.size();k++)
                    {
                        AttributeResult ar = (AttributeResult)(arv.elementAt(k));
                        if (!(ar.getAttribute("errorStatus").equals("0")))
                        {
                            l.setForeground(Color.red);
                            break;
                        }
                    }

                }
                JListVector.addElement(l);
            }
            JList1.setListData(JListVector);
            if (JList1.getComponentCount()>0)
            {
                JList1.setSelectedIndex(0);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        HeaderLabel.setText(MessageFormat.format(ProvClientUtils.getString("Device Details of the task : {0}"), new Object[]{taskResult.getAttribute("taskName")}));
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
        setPreferredSize(new Dimension(getPreferredSize().width+486,getPreferredSize().height+427)); 
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
            HeaderLabel.setForeground(new Color(-16777216));
            HeaderLabel.setHorizontalTextPosition(4);
            HeaderLabel.setText(ProvClientUtils.getString("Device Details of the Task : "));
            HeaderLabel.setHorizontalAlignment(0);
            HeaderLabel.setPreferredSize(new Dimension(164,21));
            HeaderLabel.setFont(new Font("Dialog",1,12));
          }
          catch(Exception ex)
          {
             showStatus(ProvClientUtils.getString("Exception while setting properties for bean"),ex); 
          }

          try
          {
            JPanel1.setBorder(new javax.swing.border.SoftBevelBorder(0));
          }
          catch(Exception ex)
          {
             showStatus(ProvClientUtils.getString("Exception while setting properties for bean"),ex); 
          }

          try
          {
            JListPanel.setMinimumSize(new Dimension(100,277));
            JListPanel.setPreferredSize(new Dimension(100,277));
          }
          catch(Exception ex)
          {
             showStatus(ProvClientUtils.getString("Exception while setting properties for bean"),ex); 
          }

          try
          {
            JLabel2.setForeground(new Color(-16777216));
            JLabel2.setHorizontalTextPosition(4);
            JLabel2.setText(ProvClientUtils.getString("Device List"));
            JLabel2.setHorizontalAlignment(0);
            JLabel2.setFont(new Font("Dialog",1,12));
            JLabel2.setPreferredSize(new Dimension(62,17));
            JLabel2.setMinimumSize(new Dimension(62,17));
          }
          catch(Exception ex)
          {
             showStatus(ProvClientUtils.getString("Exception while setting properties for bean"),ex); 
          }
JList1.setSelectionMode(ListSelectionModel .SINGLE_SELECTION);
DefaultListRenderer1 =new CustomListRenderer();
JList1.setCellRenderer(DefaultListRenderer1);

          try
          {
            TablePanel.setPreferredSize(new Dimension(303,305));
          }
          catch(Exception ex)
          {
             showStatus(ProvClientUtils.getString("Exception while setting properties for bean"),ex); 
          }

          try
          {
            StatusPanel.setPreferredSize(new Dimension(193,85));
            StatusPanel.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.LineBorder(new Color(-16777216),1),ProvClientUtils.getString("Status"),1,2,new Font("Dialog",1,12),new Color(-13434829)));
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
            JLabel3.setText(ProvClientUtils.getString("Device Name"));
          }
          catch(Exception ex)
          {
             showStatus(ProvClientUtils.getString("Exception while setting properties for bean"),ex); 
          }

          try
          {
            JLabel6.setHorizontalAlignment(2);
            JLabel6.setFont(new Font("SansSerif",0,12));
            JLabel6.setForeground(new Color(-16777216));
            JLabel6.setHorizontalTextPosition(4);
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
            JLabel4.setText(ProvClientUtils.getString("Configuration"));
          }
          catch(Exception ex)
          {
             showStatus(ProvClientUtils.getString("Exception while setting properties for bean"),ex); 
          }

          try
          {
            JLabel5.setHorizontalAlignment(2);
            JLabel5.setFont(new Font("SansSerif",0,12));
            JLabel5.setForeground(new Color(-16777216));
            JLabel5.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus(ProvClientUtils.getString("Exception while setting properties for bean ")+JLabel5,ex); 
          }

          try
          {
            JLabel7.setHorizontalAlignment(2);
            JLabel7.setFont(new Font("SansSerif",0,12));
            JLabel7.setForeground(new Color(-16777216));
            JLabel7.setHorizontalTextPosition(4);
            JLabel7.setText(ProvClientUtils.getString("Rollback Status"));
          }
          catch(Exception ex)
          {
             showStatus(ProvClientUtils.getString("Exception while setting properties for bean"),ex); 
          }

          try
          {
            JLabel8.setHorizontalAlignment(2);
            JLabel8.setFont(new Font("SansSerif",0,12));
            JLabel8.setForeground(new Color(-16777216));
            JLabel8.setHorizontalTextPosition(4);
          }
          catch(Exception ex)
          {
             showStatus(ProvClientUtils.getString("Exception while setting properties for bean"),ex); 
          }

          try
          {
            JTabbedPane1.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus(ProvClientUtils.getString("Exception while setting properties for bean"),ex); 
          }

          try
          {
            JScrollPane2.setBorder(new javax.swing.border.BevelBorder(0,new Color(-4214583),new Color(-5401704),new Color(-5401704),new Color(-4211044)));
          }
          catch(Exception ex)
          {
             showStatus(ProvClientUtils.getString("Exception while setting properties for bean"),ex); 
          }
Vector columnNameVector = new Vector();
 columnNameVector.addElement(ProvClientUtils.getString("Attribute Identifier"));
            columnNameVector.addElement(ProvClientUtils.getString("Configuration Status"));
            DefaultTableModel1 = new CustomModel();
            JTable1.setModel(DefaultTableModel1);
            DefaultTableModel1.setDataVector(new Vector(), columnNameVector);
            JTableHeader h = JTable1.getTableHeader();
            h.setPreferredSize(new Dimension(h.getWidth(),25));
            JTable1.setSelectionMode(ListSelectionModel .SINGLE_SELECTION);
 
          try
          {
            JEditorPane1.setContentType("text/html");
            JEditorPane1.setEditable(false);
            JEditorPane1.setBackground(new Color(-2365716));
          }
          catch(Exception ex)
          {
             showStatus(ProvClientUtils.getString("Exception while setting properties for bean"),ex); 
          }
JPanel5.add(new JSeparator(), "Center");
          try
          {
            JLabel9.setForeground(new Color(-16777216));
            JLabel9.setHorizontalTextPosition(4);
            JLabel9.setText(ProvClientUtils.getString("Configuration Success"));
            JLabel9.setFont(new Font("Dialog",0,12));
            JLabel9.setHorizontalAlignment(4);
          }
          catch(Exception ex)
          {
             showStatus(ProvClientUtils.getString("Exception while setting properties for bean"),ex); 
          }
JLabel9.setIcon(this);
          try
          {
            JLabel10.setForeground(new Color(-16777216));
            JLabel10.setHorizontalTextPosition(4);
            JLabel10.setText(ProvClientUtils.getString("Configuraiton Failed"));
            JLabel10.setFont(new Font("Dialog",0,12));
            JLabel10.setHorizontalAlignment(4);
          }
          catch(Exception ex)
          {
             showStatus(ProvClientUtils.getString("Exception while setting properties for bean"),ex); 
          }
JLabel10.setIcon(this);
          try
          {
            JLabel11.setForeground(new Color(-16777216));
            JLabel11.setHorizontalTextPosition(4);
            JLabel11.setText(ProvClientUtils.getString("Configuration Ignored"));
            JLabel11.setFont(new Font("Dialog",0,12));
            JLabel11.setHorizontalAlignment(4);
          }
          catch(Exception ex)
          {
             showStatus(ProvClientUtils.getString("Exception while setting properties for bean"),ex); 
          }
JLabel11.setIcon(this);
          try
          {
            JButton1.setHorizontalTextPosition(4);
            JButton1.setText(ProvClientUtils.getString("Close"));
            JButton1.setFont(new Font("Dialog",1,12));
            JButton1.setBorder(new javax.swing.border.SoftBevelBorder(0));
          }
          catch(Exception ex)
          {
             showStatus(ProvClientUtils.getString("Exception while setting properties for bean"),ex); 
          }
		JButton1.setPreferredSize(new Dimension(JButton1.getPreferredSize().width+22,JButton1.getPreferredSize().height+8));
		JPanel7.setPreferredSize(new Dimension(JPanel7.getPreferredSize().width+357,JPanel7.getPreferredSize().height+0));
		JPanel6.setPreferredSize(new Dimension(JPanel6.getPreferredSize().width+10,JPanel6.getPreferredSize().height+10));
		JPanel5.setPreferredSize(new Dimension(JPanel5.getPreferredSize().width+51,JPanel5.getPreferredSize().height+10));
		StatusPanel.setPreferredSize(new Dimension(StatusPanel.getPreferredSize().width+59,StatusPanel.getPreferredSize().height+18));
		TablePanel.setPreferredSize(new Dimension(TablePanel.getPreferredSize().width+10,TablePanel.getPreferredSize().height+10));
		JScrollPane1.setPreferredSize(new Dimension(JScrollPane1.getPreferredSize().width+3,JScrollPane1.getPreferredSize().height+131));
		JLabel2.setPreferredSize(new Dimension(JLabel2.getPreferredSize().width+30,JLabel2.getPreferredSize().height+0));
		JListPanel.setPreferredSize(new Dimension(JListPanel.getPreferredSize().width+15,JListPanel.getPreferredSize().height+10));
		JPanel1.setPreferredSize(new Dimension(JPanel1.getPreferredSize().width+112,JPanel1.getPreferredSize().height+10));
		HeaderLabel.setPreferredSize(new Dimension(HeaderLabel.getPreferredSize().width+500,HeaderLabel.getPreferredSize().height+8));

  
          //<End_setUpProperties>
    } 
    public void initVariables()
  {
        //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        HeaderLabel= new javax.swing.JLabel();
        JPanel1= new javax.swing.JPanel();
        JListPanel= new javax.swing.JPanel();
        JLabel2= new javax.swing.JLabel();
        JScrollPane1= new javax.swing.JScrollPane();
        JList1= new javax.swing.JList();
        TablePanel= new javax.swing.JPanel();
        StatusPanel= new javax.swing.JPanel();
        JLabel3= new javax.swing.JLabel();
        JLabel6= new javax.swing.JLabel();
        JLabel4= new javax.swing.JLabel();
        JLabel5= new javax.swing.JLabel();
        JLabel7= new javax.swing.JLabel();
        JLabel8= new javax.swing.JLabel();
        JTabbedPane1= new javax.swing.JTabbedPane();
        JScrollPane2= new javax.swing.JScrollPane();
        JTable1= new JTable();
        JScrollPane3= new javax.swing.JScrollPane();
        JEditorPane1= new javax.swing.JEditorPane();
        JPanel5= new javax.swing.JPanel();
        JPanel6= new javax.swing.JPanel();
        JLabel9= new javax.swing.JLabel();
        JLabel10= new javax.swing.JLabel();
        JLabel11= new javax.swing.JLabel();
        JPanel7= new javax.swing.JPanel();
        JButton1= new javax.swing.JButton();

  
        //<End_initVariables>
    } 
    public void setUpGUI(Container container)
  {
        //<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));
Top.add(HeaderLabel,BorderLayout.NORTH);
Top.add(JPanel1,BorderLayout.CENTER);
JPanel1.setLayout(new BorderLayout(5,5));
JPanel1.add(JListPanel,BorderLayout.WEST);
JListPanel.setLayout(new BorderLayout(5,5));
JListPanel.add(JLabel2,BorderLayout.NORTH);
JListPanel.add(JScrollPane1,BorderLayout.CENTER);
JScrollPane1.getViewport().add(JList1);
JPanel1.add(TablePanel,BorderLayout.CENTER);
TablePanel.setLayout(new BorderLayout(5,5));
TablePanel.add(StatusPanel,BorderLayout.NORTH);
StatusPanel.setLayout(new GridLayout(3,2,5,5));
StatusPanel.add(JLabel3);
StatusPanel.add(JLabel6);
StatusPanel.add(JLabel4);
StatusPanel.add(JLabel5);
StatusPanel.add(JLabel7);
StatusPanel.add(JLabel8);
TablePanel.add(JTabbedPane1,BorderLayout.CENTER);
JTabbedPane1.addTab(ProvClientUtils.getString("Tabular View"),null,JScrollPane2,null);
JScrollPane2.getViewport().add(JTable1);
JTabbedPane1.addTab(ProvClientUtils.getString("Text View"),null,JScrollPane3,null);
JScrollPane3.getViewport().add(JEditorPane1);
Top.add(JPanel5,BorderLayout.SOUTH);
JPanel5.setLayout(new BorderLayout(5,5));
JPanel5.add(JPanel6,BorderLayout.NORTH);
JPanel6.setLayout(new GridLayout(1,3,1,1));
JPanel6.add(JLabel9);
JPanel6.add(JLabel10);
JPanel6.add(JLabel11);
JPanel5.add(JPanel7,BorderLayout.SOUTH);
JPanel7.setLayout(new FlowLayout(2,0,0));
JPanel7.add(JButton1);

  
//<End_setUpGUI_Container>
    } 
    public void setUpConnections()
  {
        //<Begin_setUpConnections> 

      JList1_JList1_conn JList1_JList1_conn1 =  new JList1_JList1_conn();
      JList1.addListSelectionListener(JList1_JList1_conn1);
      java.awt.event.ActionListener JButton1_JButton1_conn1 =  new java.awt.event.ActionListener()
  {

     //<TOP_PART> - Please do not remove this comment or write any custom code above this

     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
          JButton1.getTopLevelAncestor().setVisible(false);
          ((Window)JButton1.getTopLevelAncestor()).dispose(); 
     }
};
      JButton1.addActionListener(JButton1_JButton1_conn1);
  
      //<End_setUpConnections>
    } 




    public void showStatus(String message)
  {
        //<Begin_showStatus_String>
     //<End_showStatus_String>
    }
    public void showStatus(String message,Exception ex)
  {
        //<Begin_showStatus_String_Exception>
     System.out.println(message);
     ex.printStackTrace();
     //<End_showStatus_String_Exception>
    }





    public int getIconHeight()
    {
        return 10;
    }
    public int getIconWidth()
    {
        return 10;
    }
    public void paintIcon (Component c, Graphics g,int x, int y) 
    {
        int width = getIconWidth();
        int height = getIconHeight();
        if (c == JLabel9)
        {
            g.setColor (Color.green.darker());
            g.fillRect (x, y, width, height);
            g.setColor (Color.black);
            g.drawRect (x, y, width, height);
        }
        else if (c == JLabel10)
        {
            g.setColor (Color.red);
            g.fillRect (x, y, width, height);
            g.setColor (Color.black);
            g.drawRect (x, y, width, height);
        }
        else if (c == JLabel11)
        {
            g.setColor (Color.gray);
            g.fillRect (x, y, width, height);
            g.setColor (Color.black);
            g.drawRect (x, y, width, height);
        }
    }

    class CustomModel extends DefaultTableModel
    {
        public boolean isCellEditable(int row, int col)
        {
            return false;
        }
    }

    class CustomListRenderer extends DefaultListCellRenderer
    {
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
        {
            JLabel l = (JLabel)value;
            setText(l.getText());
            setForeground(l.getForeground());
            setBackground(l.getBackground());
            if(isSelected)
            {
                setFont(new Font(l.getFont().getName(),Font.BOLD,l.getFont().getSize()));
                setBorder(new LineBorder(Color.black,1));
            }
            else
            {
                setFont(new Font(l.getFont().getName(),Font.PLAIN,l.getFont().getSize()));
                setBorder(null);
            }
            return this;
        }
    }

    class CustomTable extends JTable
    {
        public String getToolTipText(MouseEvent event) 
        {
            Point p = event.getPoint();
            int columnIndex = columnAtPoint(p);
            int rowIndex = rowAtPoint(p);
            if(columnIndex != -1 && rowIndex != -1)
            {
                Object obj  = JTable1.getValueAt(rowIndex , columnIndex);
                if(obj == null) return " ";
                String value = obj.toString();
                StringBuffer buffer = new StringBuffer(value);
                buffer.insert(0,"<html>");
                buffer.append("</html>");
                for(int k = 0 ; k < buffer.length() ; k++)
                {
                    if(buffer.charAt(k) == '\n')
                    {
                        buffer.deleteCharAt(k);
                        buffer.insert(k,"<br>");
                    }
                }
                return new String(buffer);
            }
            return "   ";
        }

    }


	public void listValueChanged()
	{
		int selectedIndex = JList1.getSelectedIndex();
		if(selectedIndex < 0) return;
		DeviceResult dr =(DeviceResult)(vdr.elementAt(selectedIndex));
		JLabel6.setText(dr.getAttribute("deviceName"));
		JLabel l = (JLabel)( JList1.getSelectedValue());
		if (l.getForeground().equals(Color.green.darker()))
		{
			JLabel5.setText(ProvClientUtils.getString("Success")); 
		}
		else if (l.getForeground().equals(Color.red))
		{
			JLabel5.setText(ProvClientUtils.getString("Failed")); 
		}
		else
		{
			JLabel5.setText(dr.getAttribute("configuration")); 
		} 
		if (!(dr.getAttribute("rollbackStatus").equals("")))
		{
			JLabel8.setText(dr.getAttribute("rollbackStatus")); 
		}
		else
		{
			JLabel8.setText("---"); 
		}
		Vector columnNameVector = new Vector();
		String atid=ProvClientUtils.getString("Attribute Identifier");
		String confStat=ProvClientUtils.getString("Configuration Status");
		columnNameVector.addElement(atid);
		columnNameVector.addElement(confStat);
		DefaultTableModel1.setDataVector(new Vector(), columnNameVector);
		StringBuffer edBuf=new StringBuffer("<html><body>");
		if (dr.getAttribute("configuration").equalsIgnoreCase("ATTEMPTED"))
		{
			try
			{
				Vector arv =dr.getSubTagsByName("AttributeResult");
				for (int i=0;i<arv.size();i++)
				{
					Vector newRow =new Vector();

					AttributeResult ar =(AttributeResult)(arv.elementAt(i));
					String id=ar.getAttribute("identifier");
					String message=ar.getAttribute("message");
					newRow.add(id);
					newRow.add(message);
					DefaultTableModel1.addRow(newRow);
					edBuf.append("<font face=\"SansSerif\" size=\"2\">");
					edBuf.append("<pre>");
					edBuf.append(atid);
					edBuf.append(" : ");
					edBuf.append(id);
					edBuf.append("<br>");
					edBuf.append(confStat);
					edBuf.append(" : ");
					edBuf.append(message);
					edBuf.append("</pre>");
					edBuf.append("<hr>");
					edBuf.append("</font>");
				}
				edBuf.append("</body></html>");
				JEditorPane1.setText(edBuf.toString());

			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
		}
	}

    //<Begin__class_JList1_JList1_conn>

 class JList1_JList1_conn implements javax.swing.event.ListSelectionListener, java.io.Serializable 
  {

     //<TOP_PART> - Please do not remove this comment or write any custom code above this

     //Listener Interface Methods Are Added Below 


     public void valueChanged( javax.swing.event.ListSelectionEvent arg0)
     {
	listValueChanged();
   }
}
//<End__class_JList1_JList1_conn>
}






