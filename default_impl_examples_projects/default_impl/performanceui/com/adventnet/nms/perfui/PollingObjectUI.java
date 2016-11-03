//$Id: PollingObjectUI.java,v 1.1.4.17 2013/09/06 14:56:11 nishanthini.k Exp $

package com.adventnet.nms.perfui;

import javax.swing.JPanel;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import javax.swing.table.TableColumn;

import javax.swing.JOptionPane;
import javax.swing.table.TableCellRenderer;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.JTableHeader;
import javax.swing.BorderFactory;


import com.adventnet.nms.xmlui.NmsPropertiesPanel;

import com.adventnet.nms.poll.Compare;
import com.adventnet.nms.pollui.PerfUIData;
import com.adventnet.nms.pollui.PollClient;

import com.adventnet.nms.util.NmsClientUtil;

import java.awt.AWTKeyStroke;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.FocusTraversalPolicy;
import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;
/**
 * class for first page of Polling Object wizard.
 * @author vijayalakshmiv
 */
public class PollingObjectUI extends NmsPropertiesPanel implements ActionListener,KeyListener,FocusListener{
	
	
    private JPanel PollObjListPanel;
    private JTable PollObjTable;
    private JPanel jPanel2;
    private PollingObjectPropsPanel propspanel;
    public static String operation="";
    private DefaultTableModel pollModel = new DefaultTableModel();
    private Vector names= new Vector();
    private PerfUIData perfdata;
    private JTextField searchfield;
    private JButton search;
    private Vector pollvec;
    private boolean tosearch=false;
    private JButton Add;
    private JButton Modify;
    private String panelkey = "PollingObjectUI";
    private JButton Delete;
    public Vector permissions = null;
    public Properties oldprops = null;
    public PollingObjectUI(PollingObjectPropsPanel propspanel)
    {
    	this.propspanel=propspanel;
    	setId(panelkey);
    	perfdata=PerfUIData.getInstance();
    	permissions = perfdata.getUserPermissions();
    	initComponents();
    	fetchPollingObjectNames();
    	
    }
    
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        PollObjListPanel = new javax.swing.JPanel();
        javax.swing.JScrollPane jScrollPane3 = new javax.swing.JScrollPane();
        pollModel = new DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                NmsClientUtil.GetString("javaui.perfgui.pollingobject.name"),NmsClientUtil.GetString("javaui.perfgui.pollingobject.status"),NmsClientUtil.GetString("javaui.perfgui.pollingobject.PollingPeriod")   }
        );
        
        PollObjTable = new JTable(pollModel)
        {
            public Component prepareRenderer(TableCellRenderer renderer,int Index_row, int Index_col) {
                Component comp = super.prepareRenderer(renderer, Index_row, Index_col);
                if (!isRowSelected(Index_row))
                {	
                if (Index_row % 2 == 0 && !isCellSelected(Index_row, Index_col)) {
                    comp.setBackground(new Color(245,245,245));
                }
                else {
                    comp.setBackground(Color.white);
                }
                }
                return comp;
            }
            public boolean isCellEditable(int rowIndex, int colIndex) {
            	  return false; 
            	  }
            
        }
        ;
        jPanel2 = new javax.swing.JPanel();
        JPanel ButtonPanel = new JPanel();
        Add = new JButton();
        Modify = new JButton();
        Delete = new JButton();
        JPanel SearchPanel = new JPanel();
        JLabel jLabel7 = new JLabel();
        searchfield = new JTextField();
        search = new JButton();

        setLayout(new java.awt.BorderLayout());

        PollObjListPanel.setBackground(new java.awt.Color(255, 255, 255));
       
        PollObjListPanel.setLayout(new java.awt.BorderLayout());

        jScrollPane3.setBackground(new java.awt.Color(255, 255, 255));

        PollObjTable.setFont(NmsClientUtil.getFont());
        PollObjTable.setSelectionBackground(new Color(90,135,226));
        PollObjTable.setSelectionForeground(Color.white);
        PollObjTable.setModel(pollModel);
        PollObjTable.setIntercellSpacing(new java.awt.Dimension(0, 0));
        PollObjTable.setRowHeight(24);
        PollObjTable.setShowHorizontalLines(false);
        PollObjTable.setShowVerticalLines(false);
        PollObjTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        PollObjTable.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.<AWTKeyStroke>emptySet());

        KeyStroke ks = KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0);

        Action nextTab = new AbstractAction("NextTab") {
            public void actionPerformed(ActionEvent evt) {
                Add.requestFocus();
            }
        };
        PollObjTable.getActionMap().put("NextTab", nextTab);
        PollObjTable.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(ks, "NextTab");


        ListSelectionModel cellSelectionModel = PollObjTable.getSelectionModel();
        cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent evt)
            {
                if(evt.getValueIsAdjusting())
                	return;
            	
               if(evt.getLastIndex()==pollModel.getRowCount())
               {
            	   Add.requestFocus();
               }
                
            }
        });
        /*JTableHeader header = PollObjTable.getTableHeader();
        header.setBackground(new Color(223,223,223));
        header.setPreferredSize(new Dimension(PollObjTable.getColumnModel().getTotalColumnWidth(), 24));
        header.setFont(new Font(NmsClientUtil.getFont().getName(),1,NmsClientUtil.getFont().getSize()));*/
        for(int i=0;i<=pollModel.getColumnCount()-1;i++)
        {
            TableColumn col=PollObjTable.getColumnModel().getColumn(i);
            col.setHeaderRenderer(new HeaderRenderer());
        }
        jScrollPane3.setViewportView(PollObjTable);

        jScrollPane3.getViewport().setBackground(Color.WHITE);;
        jScrollPane3.setBorder(BorderFactory.createLineBorder(new Color(255,255,255)));
        PollObjListPanel.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        add(PollObjListPanel, java.awt.BorderLayout.CENTER);

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.setPreferredSize(new java.awt.Dimension(600, 50));
        jPanel2.setLayout(new java.awt.BorderLayout());

        ButtonPanel.setBackground(new java.awt.Color(242, 242, 242));
        ButtonPanel.setPreferredSize(new java.awt.Dimension(270, 30));
        ButtonPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT,10, 15));

        Add.setBackground(new java.awt.Color(251, 251, 251));
        Add.setFont(NmsClientUtil.getFont());
        Add.setText(NmsClientUtil.GetString("javaui.perfgui.pollingobject.add"));
        Add.setBorderPainted(false);
        Add.setMargin(new java.awt.Insets(1, 2, 1, 2));
        Add.setMaximumSize(new java.awt.Dimension(60, 23));
        Add.setActionCommand("Add");//No I18N
        Add.setPreferredSize(new java.awt.Dimension(70, 25));
        //Add.setFocusPainted(false);
        setIcon(Add,"images/add.png");//No I18N
        Add.setToolTipText(NmsClientUtil.GetString("javaui.perfgui.pollingobject.tooltip.add"));
        Add.addActionListener(this);
//        Add.addKeyListener(this);
        
        if(permissions != null && permissions.contains("Add Polling Object"))
        ButtonPanel.add(Add);

        Modify.setBackground(new java.awt.Color(251, 251, 251));
        Modify.setFont(NmsClientUtil.getFont());
        Modify.setText(NmsClientUtil.GetString("javaui.perfgui.pollingobject.modify"));
        Modify.setActionCommand("Modify");//No I18N
        Modify.setBorderPainted(false);
        Modify.setMargin(new java.awt.Insets(1, 2, 1, 2));
        Modify.setPreferredSize(new java.awt.Dimension(70, 25));
        //Modify.setFocusPainted(false);
        Modify.addActionListener(this);
        Modify.setToolTipText(NmsClientUtil.GetString("javaui.perfgui.pollingobject.tooltip.modify"));
        setIcon(Modify,"images/edit.png");//No I18N
        if(permissions != null && permissions.contains("Modify Polling Object"))
        ButtonPanel.add(Modify);

        Delete.setBackground(new java.awt.Color(251, 251, 251));
        Delete.setFont(NmsClientUtil.getFont());
        Delete.setText(NmsClientUtil.GetString("javaui.perfgui.pollingobject.delete"));
        Delete.setToolTipText(NmsClientUtil.GetString("javaui.perfgui.pollingobject.tooltip.delete"));
        Delete.setActionCommand("Delete");//No I18N
        Delete.setBorderPainted(false);
        Delete.setMargin(new java.awt.Insets(0, 0, 0, 0));
        Delete.setPreferredSize(new java.awt.Dimension(60, 25));
        //Delete.setFocusPainted(false);
        Delete.addActionListener(this);
        setIcon(Delete,"images/remove.png");//No I18N
        if(permissions != null && permissions.contains("Delete Polling Object"))
        ButtonPanel.add(Delete);
        
        JSeparator sep = new JSeparator(JSeparator.VERTICAL);
        sep.setPreferredSize(new Dimension(1,30));
        sep.setForeground(new java.awt.Color(204, 204, 204));
        //UIManager.put("Separator.background",Color.red);
        sep.setBackground(Color.white);
        ButtonPanel.add(sep);
       
        

        jPanel2.add(ButtonPanel, java.awt.BorderLayout.CENTER);

        SearchPanel.setBackground(new java.awt.Color(242, 242, 242));
        SearchPanel.setPreferredSize(new java.awt.Dimension(290, 50));
        SearchPanel.setLayout(new java.awt.GridBagLayout());

        jLabel7.setFont(new Font(NmsClientUtil.getFont().getName(),0,12));
        jLabel7.setText(NmsClientUtil.GetString("javaui.perfgui.pollingobject.search"));
        
        jLabel7.setPreferredSize(new java.awt.Dimension(50, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.02;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 4, 0);
        SearchPanel.add(jLabel7, gridBagConstraints);

        searchfield.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        searchfield.setPreferredSize(new java.awt.Dimension(90, 25));
        searchfield.setToolTipText(NmsClientUtil.GetString("javaui.perfgui.pollingobject.tooltip.search"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.9;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 4, 0);
        SearchPanel.add(searchfield, gridBagConstraints);

        try {
        	search.setIcon(NmsClientUtil.getImageIcon(new URL(NmsClientUtil.applet.getDocumentBase()+"../images/search_perf.png")));//No I18N
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // NOI18N
        search.setIconTextGap(0);
        search.setContentAreaFilled(false);
        search.setBorderPainted(false);
	search.setActionCommand("search");
	search.addActionListener(this);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 0.07;
        gridBagConstraints.insets = new java.awt.Insets(8, 9, 4, 0);
        searchfield.addActionListener(this);
        searchfield.setActionCommand("search");
        //search.setFocusPainted(false);
//        searchfield.addKeyListener(this);
        SearchPanel.add(search, gridBagConstraints);

        jPanel2.add(SearchPanel, java.awt.BorderLayout.LINE_END);

        add(jPanel2, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>

    private void PollObjActionPerformed(ActionEvent evt)
    {
    	
    	if(evt.getActionCommand().equalsIgnoreCase("add"))//NO I18N
    	{
    		
    		addPollObj();
    		   		
    	}
    	else if(evt.getActionCommand().equalsIgnoreCase("modify"))//NO I18N
    	{
    		modifyPollObj();
      	}
    	else if(evt.getActionCommand().equalsIgnoreCase("delete"))
    	{
    		deletePollObj(PollObjTable.getSelectedRow());
    	}
    	else if(evt.getActionCommand().equalsIgnoreCase("search"))
    	{
    		searchPollObj();
    	}
    	
    }
    
    private void addPollObj()
    {
    	propspanel.operation="add";//NO I18N
		propspanel.changeWizardControls(true);
		propspanel.addPollObj();
    	operation = "Add";
    }
    
    private void modifyPollObj()
    {
    	try{
    	propspanel.operation="modify";//No I18N
    	operation = "Modify";
		int row = PollObjTable.getSelectedRow();
		if(row <0)
		{
			JOptionPane.showMessageDialog(this,NmsClientUtil.GetString("javaui.perfgui.pollingobject.noobjectselected.errormessage"),NmsClientUtil.GetString("Error Message"),JOptionPane.ERROR_MESSAGE);
			return;
		}
        String pollname=(String)pollModel.getValueAt(row,0);
        Properties props = new Properties();
        props.put("name",pollname);
        perfdata.sendDataToServer(props,"Polling Object properties");
        //NmsClientUtil.busyCursor(this);
        //propspanel.changeWizardControls(true);
    	}
    	catch(Exception e)
    	{
    		//System.out.println("Some exception here");
    		e.printStackTrace();
    	}
    }
    private void searchPollObj()
    {
    	
    	String tofind = searchfield.getText();
    	String condition="";
    	if(tofind.equals(""))
    	{
    		tofind="*";
    	}
    	else if(!tofind.contains("*"))
    	{
    		tofind="*"+tofind+"*";
    	}
    	doSearch(tofind,condition);
    	
    }
    
    private Vector searchforContain(String condition,String tofind)
    {
    	Vector row = new Vector();
    	int rows=pollvec.size();
    		
    		for(int i=0;i<=rows-1;i++)
    		{
    			Properties props = (Properties) pollvec.elementAt(i);
    			String name=(String) props.get("name");
        		String interval =(String)props.get("pollingPeriod");
        		String status =(String)props.get("status");
        		if(condition.equals("contains"))
            	{
        			if (name.contains(tofind))
        			{
    				//row=fetchVector(row, name, interval, status);
    				  row.add(props);  				
        			}
            	}
    		else{
    		if (!name.contains(tofind))
    			{
    				//row=fetchVector(row, name, interval, status);
    				row.add(props);
    				    				
    			}
    		}
    		
    	}
    	
    	return row;
    	
    }
	private Vector searchforEnd(String condition,String tofind)
	{
		Vector row = new Vector();
    	int rows=pollvec.size();
    	for(int i=0;i<=rows-1;i++)
    		{
    			Properties props = (Properties) pollvec.elementAt(i);
    			String name=(String) props.get("name");
        		String interval =(String)props.get("pollingPeriod");
        		String status =(String)props.get("status");
        		if(condition.equals("endswith"))
            	{
    			if (name.endsWith(tofind))
    			{
    				//row=fetchVector(row, name, interval, status);
    				row.add(props);
    				    				
    			}
            	}
        		else{
        			if (!name.endsWith(tofind))
        			{
        				//row=fetchVector(row, name, interval, status);
        				row.add(props);
        				    				
        			}
        		}
        		
    		}
    	
    	
    	
    	return row;
    	
	}
    private Vector searchforequals(String condition,String tofind)
    {
    	Vector row = new Vector();
    	int rows=pollModel.getRowCount();
    	for(int i=0;i<=rows-1;i++)
    		{
    		Properties props = (Properties) pollvec.elementAt(i);
			String name=(String) props.get("name");
    		String interval =(String)props.get("pollingPeriod");
    		String status =(String)props.get("status");
        		if(condition.equals("equals"))
            	{
        			if (name.equalsIgnoreCase(tofind))
        			{
    				row.add(props);
    				    				
        			}
            	}	
    		 	else
    		 	{
    		 		if (!name.equalsIgnoreCase(tofind))
    		 		{
    				row.add(props);
    				    				
    		 		}
    		 	}
    		
    		}
    	
    	return row;
    	
    }

    private Vector searchforStarts(String condition,String tofind)
    {
    	Vector row = new Vector();
    	int rows=pollvec.size();
    	
    		for(int i=0;i<=rows-1;i++)
    		{
    			Properties props = (Properties) pollvec.elementAt(i);
    			String name=(String) props.get("name");
        		String interval =(String)props.get("pollingPeriod");
        		String status =(String)props.get("status");
        		if(condition.equals("startswith"))
            	{
        			if (name.startsWith(tofind))
        			{
    				
    				//row=fetchVector(row, name, interval, status);
    		    		row.add(props);			
        			}
            	}	
    		
    	   	else{
    		
    	   		if (!name.startsWith(tofind))
    			{
    				row.add(props);
    			    				
    			}
    		}
    	}
    	
    	return row;
    		
    }
    
    private Vector fetchVector(Vector row,String name,String interval,String status)
    {
    	Properties rowprops = new Properties();
    	rowprops.put("name",name);
		rowprops.put("pollingPeriod",interval);
		rowprops.put("status",status);
		row.add(rowprops);
		
		return row;
    }
    private void doSearch(String tofind,String condition)
    {
    
    	tosearch=true;
    if(!tofind.contains("*")&&!tofind.contains("!"))
    {
    	condition="equals";
    	tofind=searchfield.getText();
    	populatePollObjTable(searchforequals(condition, tofind));
    }
	
	else if(tofind.startsWith("!")){
		int notbegin=tofind.indexOf("!*");
		int notend = tofind.lastIndexOf("*");
		if(notend==1)
		{
			condition="notend";			
			tofind=tofind.substring(2);
			populatePollObjTable(searchforEnd(condition, tofind));
		}
		else if(notend>1&&notbegin==0)
		{
			condition="notcontain";
			tofind=tofind.substring(2,notend);
			populatePollObjTable(searchforContain(condition, tofind));
		}
		else if(notbegin==-1&&notend ==-1)
		{
			condition="notequals";
			tofind=tofind.substring(1);
			populatePollObjTable(searchforequals(condition, tofind));
		}
		else if(notbegin==-1 && notend>1)
		{
			condition="notstart";
			tofind=tofind.substring(1,notend);
			populatePollObjTable(searchforStarts(condition, tofind));
		}
	}	
	else if(tofind.contains("*") && !tofind.contains("!"))
		{
			int begin = tofind.indexOf("*");
			int end=tofind.lastIndexOf("*");
			if(begin!=end)
			{
				condition="contains";
				tofind=tofind.substring(begin+1,end);
				populatePollObjTable(searchforContain(condition, tofind));
			}
			else if(begin==end && begin==0){
				
				condition="endswith";
				tofind=tofind.substring(1);
				populatePollObjTable(searchforEnd(condition, tofind));
			}
			else if(begin==end && begin>0)
			{
				condition="startswith";
				tofind=tofind.substring(0,end);
				populatePollObjTable(searchforStarts(condition, tofind));
			}
		}
	else{
		
	fetchPollingObjectNames();
	
    }
    tosearch=false;
    }
    
    public void setData(Properties props)
    {
    	
    	if(!props.containsKey("names"))
    	{	
    	int row=PollObjTable.getSelectedRow();
    	 propspanel.setPollObjProps(props);
         propspanel.changeWizardControls(true);
         propspanel.modifyPollObj(row);
         oldprops = props;
    	}
    	else
    	{
    		Vector v = (Vector) props.get("names");
    		populatePollObjTable(v);
    	}
    }
  public void handleError()
  {
	  int index = PollObjTable.getRowCount()-1;
	  if(operation.equalsIgnoreCase("Add"))
	  {
		  SwingUtilities.invokeLater(new PollingObjectUpdater(index,"delete"));
	  }
	  else if(operation.equalsIgnoreCase("Modify"))
	  {
		  if(oldprops != null)
		  {
			  String period =(String) oldprops.get("pollingPeriod");//NO I18N
		      String status = ((Boolean) oldprops.get("status")).toString();
		        SwingUtilities.invokeLater(new PollingObjectUpdater(period,status,index,"modify"));
		  }
	  }
  }
   private void deletePollObj(int row)
    {
      	if(row <0)
		{
			JOptionPane.showMessageDialog(this,NmsClientUtil.GetString("javaui.perfgui.pollingobject.noobjectselected.errormessage"),NmsClientUtil.GetString("Error Message"),JOptionPane.ERROR_MESSAGE);
			return;
		}         
         
        int re = JOptionPane.showConfirmDialog (this,NmsClientUtil.GetString("javaui.perfgui.pollingobject.delete.confirmation"),NmsClientUtil.GetString("Confirm Deletion"),JOptionPane.ERROR_MESSAGE);//No I18N
        if(re==0)
        {
            String result="";
            String ex="";
           
            try
            {
            	String pollname=(String)pollModel.getValueAt(row,0);
            	Properties props  = new Properties();
            	props.put("name",pollname);
            	perfdata.sendDataToServer(props,"Delete Polling Object");
            	names.remove(pollname);
            	//if(result.equalsIgnoreCase("success"))
            	Runnable run = new Runnable()
            	{
                
				public void run()
				{
					int row = PollObjTable.getSelectedRow();
					String pollname=(String)pollModel.getValueAt(row,0);
					pollModel.removeRow(row);
				    	int rows=pollvec.size();
			    		for(int i=0;i<=rows-1;i++)
			    		{
			    			Properties props = (Properties) pollvec.elementAt(i);
			    			String name=(String) props.get("name");
			    			if(pollname.equalsIgnoreCase(name))
			    			{
			    				pollvec.removeElementAt(i);
			    				break;
			    			}	
			    		}
					PollObjTable.updateUI();
	             }
            	};
            	SwingUtilities.invokeLater(run);
            	
                //else{
                StringTokenizer str = new StringTokenizer(result,",");//No I18N
                while(str.hasMoreElements())
                {
                str.nextToken();
                ex=str.nextToken();
                JOptionPane.showMessageDialog(this,"Exception while deleting Polling Object  "+ex,NmsClientUtil.GetString("Error"),JOptionPane.ERROR);//No I18N
                }
                return;
         //}
        }
        catch(Exception e)
        {
                
        }
    }
    }        
    public void addPollObject(String[] rowdata,Properties props)
    {
    	perfdata.sendDataToServer(props,"Add Polling Object");
    	String name = (String) props.get("name");
    	Properties common = (Properties) props.get("CommonCriteria");
    	String period = common.getProperty("pollingPeriod");
    	String status = (String) props.get("status");
    	SwingUtilities.invokeLater(new PollingObjectUpdater(name,period,status,"add"));
    	names.add(name);//Adding to check duplicate po getting added.WEBNMS7872
    	//fetchPollingObjectNames();
    	//pollModel.addRow(rowdata);
    }
    private Vector convertMcProps(Vector compare)
    {
     Vector v = new Vector();
     for(Enumeration en =compare.elements();en.hasMoreElements();)
     { 
       Properties props = new Properties();
       Compare obj=(Compare)en.nextElement();
 	   String type=(String)obj.getType();
 	   props.put("type",type);//NO I18N
 	   String property =(String)obj.getProperty();
 	   props.put("property",property);//NO I18N
 	   String condition =(String)obj.getCondition();
 	   props.put("condition",condition);//NO I18N
 	   String value =(String)obj.getValue();
 	   props.put("value",value);//NO I18N
 	   
 	   v.add(props);
     }
     
     return v;
    }
    
    private void fetchPollingObjectNames()
    {
        try {
              
               
        	perfdata.sendDataToServer(null,"Polling Object names");
                //populatePollObjTable(PollObj);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,NmsClientUtil.GetString("javaclient.perfgui.pollingobjects.exception.message"),NmsClientUtil.GetString("Error Message"),JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
    }

 public void populatePollObjTable(Vector Pobj)
    {
     if(!tosearch)
	 pollvec=Pobj;  
	 try{
    	
		 SwingUtilities.invokeLater(new PollingObjectUpdater(Pobj));
		 
        }
       catch(Exception e)
       {
           JOptionPane.showMessageDialog(PollObjTable,NmsClientUtil.GetString("javaclient.perfgui.pollingobjectlist.exception.message"),NmsClientUtil.GetString("Error Message"),JOptionPane.ERROR_MESSAGE);
           e.printStackTrace();
       }
    }
 
    public void modifyPollTable(Properties prop,int index)
    {
    	index = PollObjTable.getSelectedRow();
    	perfdata.sendDataToServer(prop,"Modify Polling Object");
    	String period =propspanel.getValue("pollingPeriod");//NO I18N
        String status =(String)prop.get("status");//NO I18N
        SwingUtilities.invokeLater(new PollingObjectUpdater(period,status,index,"modify"));
    }
    
    public Vector getNamesVector()
    {
    	return names;
    }
    
    private void setIcon(JButton button,String icon)
    {
    	button.setIcon(NmsClientUtil.getImageIcon(NmsClientUtil.applet.getDocumentBase()+"../"+icon));//NO I18N
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
   
   setFont(new Font(NmsClientUtil.getFont().getName(),1,12));

   setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
   
   setPreferredSize(new Dimension(PollObjTable.getColumnModel().getTotalColumnWidth(), 24));
   
 

   return this;

   }


   }

public void actionPerformed(ActionEvent evt) {
	// TODO Auto-generated method stub
	PollObjActionPerformed(evt);
}


public void keyPressed(KeyEvent e) {
	
	// TODO Auto-generated method stub
/*	if(e.getKeyCode()== e.VK_ENTER)			//Commented since we are already performing these actions in ActionListener 
	{
			if(e.getSource()==search)
				searchPollObj();
			else if(e.getSource()==Add)
			{
				addPollObj();
			}
			else if(e.getSource()==Modify)
			{
				modifyPollObj();
			}
			else if(e.getSource()==Delete)
			{
				deletePollObj();
			}
	}	*/
	
	
}
class PollingObjectUpdater implements Runnable{

	Vector pollobj;
	String status;
	String period;
	int index;
	String name;
	String operation="";
	public PollingObjectUpdater(Vector pollobj)
	{
		this.pollobj=pollobj;
	}
	public PollingObjectUpdater(String name, String period,String status,String operation)
	{
		this.status=status;
		this.period=period;
		this.name=name;
		this.operation=operation;
	}
	public PollingObjectUpdater(String period,String status,int index,String operation)
	{
		this.status=status;
		this.period=period;
		this.index=index;
		this.operation=operation;
	}
	public PollingObjectUpdater(int index,String operation)
	{
		this.index=index;
		this.operation=operation;
	}
	
	public void run() {
		// TODO Auto-generated method stub
		if(operation.equals("delete"))
		{	
		String pollname=(String)pollModel.getValueAt(index,0);
		pollModel.removeRow(index);
	    	int rows=pollvec.size();
    		for(int i=0;i<=rows-1;i++)
    		{
    			Properties props = (Properties) pollvec.elementAt(i);
    			String name=(String) props.get("name");
    			if(pollname.equalsIgnoreCase(name))
    			{
    				pollvec.removeElementAt(i);
    				break;
    			}	
    		}
		PollObjTable.updateUI();
		}
		else if(pollobj!= null)
        {
           	while(pollModel.getRowCount()>0)
        	{
        	 pollModel.removeRow(0);
        	 
            }
           names.clear();
           for(int i=0;i<=pollobj.size()-1;i++)
           {
           Properties prop =(Properties)pollobj.elementAt(i);

           String name=(String)prop.get("name");//NO I18N
           
           names.add(name);
           String period =(String)prop.get("pollingPeriod");//NO I18N
           String status =(String)prop.get("status");//NO I18N
           Object[] data={name,status,period};
           pollModel.addRow(data);

          }
           PollObjTable.updateUI();
         }
		else if(operation.equalsIgnoreCase("modify"))
		{
			pollModel.setValueAt(period,index,2);
			pollModel.setValueAt(status,index,1);
			PollObjTable.updateUI();
		}
		else if(operation.equalsIgnoreCase("add"))
		{
			index = pollModel.getRowCount();
			Object[] rowData={name,status,period};
			pollModel.addRow(rowData);
			PollObjTable.updateUI();
		}
		
		
	}
	
}
public void keyReleased(KeyEvent e) {
	// TODO Auto-generated method stub
	
}

public void keyTyped(KeyEvent e) {
	// TODO Auto-generated method stub
	
}

public void focusGained(FocusEvent e) {
	// TODO Auto-generated method stub
	
}

public void focusLost(FocusEvent e) {
	// TODO Auto-generated method stub
	
}

  
}

