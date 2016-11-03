//$Id: PolledDataUI.java,v 1.1.4.12 2013/08/31 12:50:01 vijayalakshmiv Exp $

package com.adventnet.nms.perfui;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

//import com.adventnet.nms.pollui.StatsAdminPanel;
import com.adventnet.nms.pollui.PerfUIData;
import com.adventnet.nms.util.NmsClientUtil;
import com.adventnet.nms.xmlui.NmsPropertiesPanel;
/**
 *This class creates the default left hand side Polled data list panel which will be
 *present Polled Data wizard
 *
 * @author vijayalakshmiv
 */
public class PolledDataUI extends NmsPropertiesPanel implements ActionListener{
	
	
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private  javax.swing.JTable table;
   
    public Vector agents;
    private DefaultListModel hostmodel= new DefaultListModel();
    public Hashtable hosts = new Hashtable();
  
   public DefaultTableModel pollModel;
   private PolledDataPropsPanel propspanel;
   public static String operation="Add";//NO I18N
   //Because user can enter values even without clicking on Add button
   private int selectedrow=0;
   private boolean first=true;
   private Vector permissions = null;
   private PerfUIData perfdata=PerfUIData.getInstance();
   private String agent="";
   String key = "PolledDataUI";
   
    public PolledDataUI(PolledDataPropsPanel propspanel) {
        
    	perfdata=PerfUIData.getInstance();
    	permissions = perfdata.getUserPermissions();
    	setId(key);
    	initComponents();
        this.propspanel=propspanel;
       
       
    }

    public PolledDataUI(Vector pdata,String agent)
    {
        initComponents();
        hosts.put(agent,pdata);
        this.agent=agent;
    }
        
    
    private void initComponents() {

      try{
    	jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 5));
        setPreferredSize(new java.awt.Dimension(220, 700));
        setLayout(new java.awt.BorderLayout());

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jScrollPane1.setForeground(new java.awt.Color(255, 255, 255));

        pollModel=new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "", ""
            }
        );
        table.setForeground(new java.awt.Color(0, 0, 0));
        table.setBackground(new Color(255,255,255));
        table.setModel(pollModel);
        table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        table.setIntercellSpacing(new java.awt.Dimension(0, 0));
        table.setRowHeight(24);
        table.setCellSelectionEnabled(false);
        table.setSelectionBackground(new java.awt.Color(90,135,226));
        table.setSelectionForeground(Color.white);
        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        table.setShowHorizontalLines(false);
        table.setShowVerticalLines(false);
        //table.setCellSelectionEnabled(false);
        jScrollPane1.setViewportView(table);
        jScrollPane1.getViewport().setBackground(new java.awt.Color(255,255,255));
        JTableHeader header = table.getTableHeader();
        table.setTableHeader(null);
        //header.setBackground(new Color(255,255,255));
        //header.setPreferredSize(new Dimension(table.getColumnModel().getTotalColumnWidth(), 24));
        //header.setFont(new java.awt.Font("Verdana", 1, 12));//NO I18N
        for(int i=0;i<=pollModel.getColumnCount()-1;i++)
        {
            TableColumn col=table.getColumnModel().getColumn(i);
            col.setHeaderRenderer(new HeaderRenderer());
        }
        TableColumn column0 = table.getColumnModel().getColumn(0);
        column0.setPreferredWidth(155);
        column0.setCellRenderer(new HeaderRenderer());
        

        TableColumn column =table.getColumnModel().getColumn(1);
        if(permissions != null && permissions.contains("Modify Polling Units"))//NO I18N
        {
            column.setCellEditor(new NmsTableCellEditor(new JCheckBox()));
            column.setCellRenderer(new ButtonRenderer());
        }
        column.setPreferredWidth(20);

        TableColumn column1 =table.getColumnModel().getColumn(2);
        if(permissions != null && permissions.contains("Remove Polling Units"))//NO I18N
        {
            column1.setCellEditor(new NmsTableCellEditor(new JCheckBox()));
            column1.setCellRenderer(new ButtonRenderer());
        }
        column1.setPreferredWidth(20);

        table.setCellSelectionEnabled(false);
        ListSelectionModel cellSelectionModel = table.getSelectionModel();
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent evt)
            {
            	int selected_column=table.getSelectedColumn();
            	if(selected_column==0)
            	{
            		table.clearSelection();
            		return;
            	}
                if(!evt.getValueIsAdjusting())
                {	
                	if(operation.equals("delete"))
                	{
                		operation="Add";
                	}
                	else
                	{
                		operation="modify";//NO I18N
                		populateProperties();
                	}
                }
                
            }
        });

        add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.setBackground(new java.awt.Color(223, 223, 223));
        jPanel1.setPreferredSize(new java.awt.Dimension(10, 35));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10,3));

        jLabel1.setFont(new java.awt.Font(NmsClientUtil.getFont().getName(),1,NmsClientUtil.getFont().getSize()));//NO I18N
        jLabel1.setText(NmsClientUtil.GetString("javaui.perfgui.polleddata.listtitle"));//fix for japan team reported issue //NO I18N
        jPanel1.add(jLabel1);

        jSeparator1.setForeground(new java.awt.Color(204, 204, 204));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setPreferredSize(new java.awt.Dimension(2, 40));
        jPanel1.add(jSeparator1);
        if(permissions.contains("Add Polling Units"))//NO I18N
        {
        jButton1.setFont(new Font(NmsClientUtil.getFont().getName(),0,12));
        try {
			jButton1.setIcon(NmsClientUtil.getImageIcon(new URL(NmsClientUtil.applet.getDocumentBase()+"../images/add.png")));//NO I18N
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        String add=NmsClientUtil.GetString("javaui.perfgui.polleddata.addnew");
        jButton1.setText(add);//NO I18N
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setToolTipText(NmsClientUtil.GetString("javaui.perfgui.polleddata.tooltip.add"));
        jButton1.setIconTextGap(7);
        jButton1.setActionCommand("Add");//NO I18N
        jButton1.addActionListener(this);
               	
        jPanel1.add(jButton1);
        }
        add(jPanel1, java.awt.BorderLayout.PAGE_START);
      }
      catch(Exception e)
      {
    	 e.printStackTrace();
      }
    }
    	
    	
        private void buttonAction(ActionEvent evt)
        {
        	if(evt.getActionCommand().equalsIgnoreCase("Add"))//NO I18N
        	{
        		operation="Add";
        		propspanel.doCancel();
        		propspanel.clearAll();
        		table.clearSelection();
        		String[] toenable = {"name","dnsName","parentObj","protocol","pollerName","oid","numericType"};//NO I18N
        		propspanel.disableEnableComponents(toenable,true,"PDpage1");
        		propspanel.agentfield.setText(propspanel.agentvalue);
        		Component comp=propspanel.getControl("browsemib");
                if(comp!=null)
                	comp.setEnabled(true);
                operation="Add";
           	}
        	
        	else if(evt.getActionCommand().equalsIgnoreCase("modify"))//NO I18N
        	{
        		operation="modify";//NO I18N
        		String[] todisable = {"name","dnsName","parentObj","protocol","pollerName","oid","numericType"};//NO I18N
        		propspanel.disableEnableComponents(todisable,false,"PDpage1");
        	}
        	else if(evt.getActionCommand().equalsIgnoreCase("delete"))//NO I18N
        	{
        		operation="delete";//NO I18N
        		selectedrow=table.getSelectedRow();
        		String pdname =(String) pollModel.getValueAt(selectedrow,0);
        		if(pdname == null)
                {
                    JOptionPane.showMessageDialog(this,NmsClientUtil.GetString("javaui.perfgui.polleddata.selection.errormessage"),NmsClientUtil.GetString("Error Message"),JOptionPane.ERROR_MESSAGE);
                    return;
                }
        		String pdkey=(String) propspanel.polldata.get(pdname);
        		
                
                int sel=JOptionPane.showConfirmDialog(this,NmsClientUtil.GetString("javaui.perfgui.polleddata.delete.confirmation"),NmsClientUtil.GetString("Confirmation message"),JOptionPane.OK_CANCEL_OPTION);
                if(sel==JOptionPane.OK_OPTION && pdkey!=null)
                {
                    deletePolledData(pdkey,selectedrow);
                    operation = "delete";
                }
                else
                {
                    return;
                }
        	}
            
        }
        private void populateProperties()
        {
            	
        	selectedrow=table.getSelectedRow();
        	
           	if(selectedrow<0)
        		return;
        	String name=(String)pollModel.getValueAt(selectedrow,0);
        	
              String pd =(String)propspanel.polldata.get(name);
              Properties props = new Properties();
              props.put("name",pd);
              perfdata.sendDataToServer(props,"Polled Data Properties");
                            
         }
        
//        public void setPolledDataProps(Properties props)
//        {
//        	
//        	if(props.get("numericType") != null)
//        	handleForNumerictype(props);
//        	propspanel.setData(props);
//        	propspanel.actualprops=props;
//            String[] todisable = {"name","dnsName","parentObj","protocol","pollerName","oid","numerictype"};//NO I18N
//            propspanel.disableEnableComponents(todisable,false,"PDpage1");
//            Component comp=propspanel.getControl("browsemib");
//            if(comp!=null)
//            	comp.setEnabled(false);
//            first = false;
//            
//        }
        
        private void handleForNumerictype(Properties props) {
			// TODO Auto-generated method stub
			
        	String numericString = props.get("numericType").toString();
        	
        	if(numericString.equalsIgnoreCase("1"))
        	{
        		numericString = "long";
        	}
        	else if(numericString.equalsIgnoreCase("2"))
        	{
        		numericString =  "string";
        	}
        	else if(numericString.equalsIgnoreCase("3"))
        	{
        		numericString =  "decimal";
        	}
        	else
        	{
        		numericString = "unknown";
        	}
        	props.put("numerictype",numericString);
        	
		}
        public void handleError()
        {
          int index = pollModel.getRowCount()-1;
      	  if(operation.equalsIgnoreCase("Add"))
      	  {
      		  SwingUtilities.invokeLater(new PolledDataUpdater(index));
      	  }
      	  
        }
        public void setData(Properties props)
        {
        if(props.containsKey("Polleddata"))
        {	
        	try
        	{
        	Vector Polldata = (Vector) props.get("Polleddata");
        	String selectedPolledData = props.getProperty("selectedpd");
        	clearTable();
        	SwingUtilities.invokeLater(new PolledDataUpdater(Polldata,selectedPolledData));
        	String[] todisable = {"name","dnsName","parentObj","protocol","pollerName","oid","numericType"};//NO I18N
            propspanel.disableEnableComponents(todisable,true,"PDpage1");
        	Component comp=propspanel.getControl("browsemib");
            if(comp!=null)
            	comp.setEnabled(true);
            operation="Add";
            propspanel.clearAll();
            propspanel.setValue("agent",agent);
        	       	
        }
        catch(NullPointerException e)
        {
           return;
        }
        }
        else{
        	if(props.get("numericType") != null)
            	handleForNumerictype(props);
            	propspanel.setData(props);
            	propspanel.actualprops=props;
                String[] todisable = {"name","dnsName","parentObj","protocol","pollerName","oid","numerictype"};//NO I18N
                propspanel.disableEnableComponents(todisable,false,"PDpage1");
                Component comp=propspanel.getControl("browsemib");
                if(comp!=null)
                	comp.setEnabled(false);
                first = false;
        }
        }
        
        private void deletePolledData(String pd,int row)
        {
           
           //String result="";
        	Properties props = new Properties();
        	props.put("name",pd);
        	perfdata.sendDataToServer(props,"Delete Polled Data");
           //if(result.equalsIgnoreCase("success"))//NO I18N
        	   {
               NmsClientUtil.normalCursor(this);
               SwingUtilities.invokeLater(new PolledDataUpdater(row));
                   //
               
                        
                   propspanel.clearAll();
                   propspanel.setValue("agent",agent); 
                    String[] todisable = {"name","dnsName","parentObj","protocol","pollerName","oid"};//NO I18N
                    propspanel.disableEnableComponents(todisable,true,"PDpage1");
                	Component comp=propspanel.getControl("browsemib");
                    if(comp!=null)
                    	comp.setEnabled(true);
                    operation="delete";
             }
             /*else{
               StringTokenizer str = new StringTokenizer(result,",");//NO I18N
               while(str.hasMoreElements())
               {
               str.nextToken();
               String message=str.nextToken();
               JOptionPane.showMessageDialog(this,NmsClientUtil.GetString("javaui.perfgui.polleddata.delete.error  "),NmsClientUtil.GetString("Error Message"),JOptionPane.ERROR);
               }
              return;
           //}*/
        
          
        }
        
        protected synchronized void fireTableChanged(javax.swing.event.TableModelEvent evt)
        {
            try
            {
                TableModelListener[] listeners = pollModel.getTableModelListeners();
            	int size = listeners.length;
                for (int i=0;i<size;i++)
                {
                    TableModelListener listener = (TableModelListener) listeners[i];
                    if (listener instanceof JTable)
                    {
                        ((JTable)listener).updateUI();
                        ((JTable)listener).repaint();
                        continue;
                    }

                    listener.tableChanged(evt);
                }
            }
            catch (Exception e)
            {
                
            }
        }

        
        public void sendReqForPolledData(String agent)
        {
        	Properties props = new Properties();
        	props.put("agentname",agent);
        	perfdata.sendDataToServer(props,"Fetch Polled Data");
        }
        
        public void modifyPolledData(Properties props)
        {
        	perfdata.sendDataToServer(props,"Modify Polled Data");
        }

       
        public void addPolledData(Properties prop)
        {
        	perfdata.sendDataToServer(prop,"Add Polled Data");
        	String pdname=(String)prop.get("name");//NO I18N
//            String[] row = {(String)prop.get("name")};	//NO I18N
//            pollModel.addRow(row);
//            table.updateUI();
//         
        	SwingUtilities.invokeLater(new PolledDataUpdater(pdname));
            String pdkey=pdname+"\t"+prop.get("agent")+"\t"+prop.get("oid");//NO I18N
            
        }
        
        public void clearTable()
        {
        	int count = pollModel.getRowCount();
        	while(count>0)
        	{
        		pollModel.removeRow(count-1);
        		count--;
        	}
        }

 
   

       public class NmsTableCellEditor extends DefaultCellEditor implements ActionListener
{
	  protected JButton button;

	  public NmsTableCellEditor(JCheckBox cb) {
	    super(cb);

	    button = new JButton();
        button.setOpaque(true);
        button.setMargin(new java.awt.Insets(2,2,2,2));
        button.setIconTextGap(0);
        button.addActionListener(this); 
    	
           }

       public Component getTableCellEditorComponent(JTable table1, Object value,
	                   boolean isSelected, int row, int column) {
		    button.setBorderPainted(false);
		    button.setContentAreaFilled(false);
		    button.setFocusPainted(false);
		    button.setEnabled(true);
		   
        //   button.setForeground(Color.BLACK);
                    
           
                    if(column ==1)
                    {
                    button.setActionCommand("modify");//NO I18N
                    }
                    else if(column ==2)
                    {
                        button.setActionCommand("delete");//NO I18N
                    }

      return button;
	  }

	  public Object getCellEditorValue() {
		  return button.getText();
	  }

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		 JButton button=(JButton)e.getSource();
 		fireEditingStopped();
 		
 		buttonAction(e);
	}

}

       class ButtonRenderer extends JButton implements TableCellRenderer {

		public ButtonRenderer() {
	        setOpaque(true);
	}

		public Component getTableCellRendererComponent(JTable table, Object value,
	                boolean isSelected, boolean hasFocus, int row, int column) {

			try {
                    if(column ==1)
                    {
                    
						setIcon(NmsClientUtil.getImageIcon(new URL(NmsClientUtil.applet.getDocumentBase()+"../images/edit.png")));//NO I18N
					        setToolTipText(NmsClientUtil.GetString("javaui.perfgui.polleddata.tooltip.modify"));
                    }
                    else if(column ==2)
                    {
                    setIcon(NmsClientUtil.getImageIcon(new URL(NmsClientUtil.applet.getDocumentBase()+"../images/remove.png")));//NO I18N
                              setToolTipText(NmsClientUtil.GetString("javaui.perfgui.polleddata.tooltip.delete")); 
                    }
                    } catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
                    
		    setBorderPainted(false);
		    setContentAreaFilled(false);
		    setFocusPainted(false);
		    setEnabled(true);
		    
            setForeground(Color.BLACK);
            setMargin(new java.awt.Insets(2,2,2,2));
           return this;
	}
	}

       
        class HeaderRenderer extends JLabel implements TableCellRenderer

       {

       public Component getTableCellRendererComponent(JTable table,

       Object value,

       boolean hasFocus,

       boolean isSelected,

       int row,

       int col)

       {

       setText(value.toString());

       setBorder(BorderFactory.createEmptyBorder(2,2,2,2));

       setPreferredSize(new Dimension(table.getColumnModel().getTotalColumnWidth(),32));
       return this;

       }


       }
        
       class PolledDataUpdater implements Runnable
       {
    	Vector Polldata;
    	String selectedPolledData;
    	String name;
    	int row=-1;
    	public PolledDataUpdater(Vector Polldata,String selectedPolledData)
    	{
    		this.Polldata=Polldata;
    		this.selectedPolledData=selectedPolledData;
    	}
    	public PolledDataUpdater(String name)
    	{
    		this.name=name;
    	}
    	public PolledDataUpdater(int row)
    	{
    		this.row=row;
    	}
		public void run() {
			// TODO Auto-generated method stub
		if(Polldata != null)
		{	
			for(int i=0;i<=Polldata.size()-1;i++)
        	{
        	
            String key =(String)Polldata.elementAt(i);
        	StringTokenizer st = new StringTokenizer(key, "\t");//NO I18N
        	String name=st.nextToken();
        	propspanel.polldata.put(name,key);
            String[] rowData={name,"",""};//NO I18N
        	pollModel.addRow(rowData);
        	if(selectedPolledData!=null && key.equalsIgnoreCase(selectedPolledData)){
        		
        		selectedrow=i;
        		table.setRowSelectionInterval(i, i);
        		table.setSelectionBackground(new java.awt.Color(90,135,226));
                table.setSelectionForeground(Color.white);
        		
        	}
        	}
        	pollModel.fireTableDataChanged();
		}
		
		else if(name != null)
		{
			 String[] row = {name};	//NO I18N
	            pollModel.addRow(row);
	            table.updateUI();
		}
		else if(row !=-1)
		{

			pollModel.removeRow(row);
            table.updateUI();
            pollModel.fireTableRowsDeleted(row,row);
            
            table.clearSelection();
		}
		}   
       }

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			buttonAction(e);
		}
		
		public void setAgent(String agent)
		{
			this.agent=agent;
		}

		public void resetAction()
		{
			//table.clearSelection();
			if(operation.equalsIgnoreCase("Add"))
			{
	    		table.clearSelection();
	    		String[] toenable = {"name","dnsName","parentObj","protocol","pollerName","oid","numericType"};//NO I18N
	    		propspanel.disableEnableComponents(toenable,true,"PDpage1");
	    		propspanel.agentfield.setText(propspanel.agentvalue);
	    		Component comp=propspanel.getControl("browsemib");
	            if(comp!=null)
	            	comp.setEnabled(true);
	            operation="Add";
			}
			else if (operation.equalsIgnoreCase("modify") ||operation.equalsIgnoreCase("delete"))
			{
				int row = table.getSelectedRow();
				int col = table.getSelectedColumn();
				table.clearSelection();
				table.changeSelection(row, col, false, false);
				operation="modify";//NO I18N
        		String[] todisable = {"name","dnsName","parentObj","protocol","pollerName","oid","numericType"};//NO I18N
        		propspanel.disableEnableComponents(todisable,false,"PDpage1");
			}
		}
		
		
}



