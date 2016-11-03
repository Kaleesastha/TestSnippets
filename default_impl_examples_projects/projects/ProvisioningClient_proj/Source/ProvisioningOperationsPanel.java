//$Id: ProvisioningOperationsPanel.java,v 1.3.2.1 2013/12/04 10:09:47 venkatramanan Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

package com.adventnet.nms.provisioning.ui;

import java.text.MessageFormat;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

import com.adventnet.nms.provisioning.messaging.*;
import com.adventnet.nms.provisioning.server.*;
import com.adventnet.nms.provisioning.*;
import com.adventnet.nms.provisioning.xml.*;
import com.adventnet.nms.util.NmsClientUtil;
import 	com.adventnet.beans.probeans.ProListViewDefaultTableModel;
import 	com.adventnet.beans.probeans.ProTable;
import com.adventnet.nms.startclient.AbstractBaseNmsPanel;
import com.adventnet.nms.startclient.NmsMainApplet;
import com.adventnet.nms.util.DateTimeComponent;
import com.adventnet.management.config.CommonUtil;
import test.provisioning.TemplateNmsFrame;
import com.adventnet.nms.util.NmsUiAPI;
import com.adventnet.nms.util.PureClientUtils;
import com.adventnet.nms.util.NmsMenuItem;
import com.adventnet.management.i18n.AdventNetResourceBundle;
import com.adventnet.nms.util.ListViewCellRenderer;
import java.net.URL;
import javax.swing.table.TableColumnModel;



public class ProvisioningOperationsPanel extends AbstractBaseNmsPanel implements MouseListener,Runnable
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "ProvisioningClientResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JScrollPane JScrollPane1 = null;
	com.adventnet.beans.probeans.ProListView listView = null;
	//<End_Variable_Declarations>
 	ProvisioningAPIImpl_SessionStub api ;
	ProListViewDefaultTableModel viewModel;
	ProTable table;
	Properties provisioningProperties = new Properties();
	String selectedTemplateName;
    boolean reloadingInProgress = false;
    static boolean firstTime=true;

	private JPanel p= new JPanel(new GridLayout(4,1));
	private JPanel rp=new JPanel(new GridLayout(1,3));
	private	JLabel cl= new JLabel();
	//private	JRadioButton rb1=new JRadioButton("Hours");
	//private	JRadioButton rb2=new JRadioButton("Minutes");
	//private	JRadioButton rb3=new JRadioButton("Seconds");
	private	JRadioButton rb1;
	private	JRadioButton rb2;
	private	JRadioButton rb3;
	private	JSlider s=new JSlider();
	private	JLabel l=new JLabel();
	private	ButtonGroup bg=new ButtonGroup();
	private long reloadTime=5*60*1000;
	private long nextReloadTime;
 	private ListViewCellRenderer cellRenderer=null; 

  	public void init(JApplet a)
	{
		applet =a;
		this.init();
	}
	public void setProperties(Properties p)
	{
		provisioningProperties=p;
		if(firstTime)
		{
			SwingUtilities.invokeLater(new Runnable()
					{
						public void run()
						{
							reloadTable(getVectorOfTuples());
						}
					});
			PureClientUtils.scheduler.scheduleTask(this,System.currentTimeMillis()+reloadTime);
			nextReloadTime=System.currentTimeMillis()+reloadTime;
			firstTime=false;
		}
		if (nextReloadTime>System.currentTimeMillis()+10000)
		{
			SwingUtilities.invokeLater(new Runnable()
					{
						public void run()
						{
							reloadTable(getVectorOfTuples());
						}
					});
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
        setPreferredSize(new Dimension(getPreferredSize().width+714,getPreferredSize().height+442)); 
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
        	String errorMessage = ex.getMessage() != null ? ex.getMessage() : ex.toString();	
			System.err.println(MessageFormat.format(resourceBundle.getString("Error in init method of the ProvisioningOperationsPanel: {0}"), new Object[]{errorMessage})); 
        } 
        // let us set the initialzed variable to true so  
        // we dont initialize again even if init is called 
        initialized = true; 

  
         //<End_init>
	try
	{
		api=new ProvisioningAPIImpl_SessionStub();
		Vector columnNames=new Vector();
		columnNames.addElement(resourceBundle.getString("Identifier"));
		columnNames.addElement(resourceBundle.getString("Template Name"));
		columnNames.addElement(resourceBundle.getString("Scheduled Time"));
		columnNames.addElement(resourceBundle.getString("Stage"));
		columnNames.addElement(resourceBundle.getString("Status"));
		columnNames.addElement(resourceBundle.getString("Username")); //NO I18N
		viewModel= new ProListViewDefaultTableModel(columnNames);
		listView.setListViewModel(viewModel);
                listView.setResourceBundle(AdventNetResourceBundle.getInstance());
		listView.setBrowsePanelFont(NmsClientUtil.getFont());
		Color[] colors={new Color(223,223,223),new Color(223,223,223),new Color(223,223,223)};
		listView.setComponentBackground(colors);

		table=listView.getProTable();
		table.setShowVerticalLines(false);
		table.setShowHorizontalLines(false);
		TableColumnModel colModel=table.getColumnModel();
		int colCount=colModel.getColumnCount();
		for(int i=0;i<colCount;i++){
			colModel.getColumn(i).setCellRenderer(cellRenderer);
		}

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(this);
	}
	catch(Exception e)
	{
		System.err.println(resourceBundle.getString("Cannot get ProvisioningAPI in NMSClient"));
	}
			rb1=new JRadioButton(resourceBundle.getString("Hours"));
			rb2=new JRadioButton(resourceBundle.getString("Minutes"));
			rb3=new JRadioButton(resourceBundle.getString("Seconds"));
			bg.add(rb1);
			bg.add(rb2);
			bg.add(rb3);
			rp.add(rb1);
			rp.add(rb2);
			rp.add(rb3);
			rb1.setActionCommand("Hours");
			rb2.setActionCommand("Minutes");
			rb3.setActionCommand("Seconds");
			s.setPaintTrack(true);
			p.add(cl);
			p.add(s);
			p.add(rp);
			p.add(l);
			ProvClientUtils.englishToNative=AdventNetResourceBundle.getInstance();
  } 
      void reloadTable(Vector v)
      {
        try
		{
			if(v == null) return;
		    viewModel.setDataVector(v);
		    //viewModel.setFromIndex(1);
	        int size = v.size();
			if(size != 0)
			{
				viewModel.setStartIndex(1);
				viewModel.setEndIndex(size); //Issue ID 11372986
			}
		    //viewModel.setTotalCount(size);
	    	//viewModel.setPageLength(size<25?size:25);
	        //To avoid compilation error the following changes are made.
			//JComboBox jcb = listView.getPageLengthComboBox();
	    	//int len=listView.getPageLengthComboBox().getItemCount();
			JComboBox jcb = listView.getPageLengthComponent().getComboBox();
			int len = jcb.getItemCount();
		    for (int i=0;i<len;i++)
	    	{
            	/*Integer value = (Integer)jcb.getItemAt(i);
	            int val = value.intValue();
    	        if(val > size) 
        	    {
            	    //listView.deletePageLengthForIndex(i);  
					listView.getPageLengthComponent().removeItemAt(i);
	                len--;
    	            i--;
        	    }*/
				listView.getPageLengthComponent().removeItemAt(i);
			}
			//listView.addPageLength(25);
			//listView.addPageLength(size<25?size:25);
	    	//viewModel.refreshModel();
		    viewModel.refresh();
			if (size==0)
			{
	    		//viewModel.setFromIndex(0);
				//viewModel.setStartIndex(0);
				viewModel.setViewVector(v);
				listView.getButtonGroup().setEnabled(false);
			}
		    if (selectedTemplateName==null)
	    	{
			deleteReloadAllMenuItem();
		    }
		}
		catch(Exception me)
		{
			me.printStackTrace();
			System.err.println(resourceBundle.getString("Error while getting Operation list"));
		}
      }
	Vector getVectorOfTuples()
	{
        synchronized(this)
        {
            if(reloadingInProgress)
            {
                return null;
            }
            reloadingInProgress = true;
        }
		Vector dataVector=new Vector();
		try
		{
		    String [][] data=null;
		    if (selectedTemplateName==null)
		    {
			data = api.getAllTuples();
		    }
		    else
		    {
			data = api.getSelectedTuples(selectedTemplateName);
		    }
		    if (data!=null)
		    {
			for (int i=0;i<data.length;i++)
			{
				Vector rowVector=new Vector();
				for (int j=0;j<data[i].length;j++)
				{
					rowVector.addElement(data[i][j]);
				}
				dataVector.addElement(rowVector);
			}
		    }
		}
		catch(Exception e)
		{
            synchronized(this)
            {
                reloadingInProgress = false;
            }
			if((e.getMessage()!=null)&&(!(e.getMessage().trim().equals(""))))
			{
				 if (e.getMessage().trim().length()>50)
				{
					test.provisioning.MessageWindow.showMessage(NmsClientUtil.getParentFrame(),resourceBundle.getString("Provisioning"),e.getMessage());
				}
				else
				{
					JOptionPane.showMessageDialog(this,e.getMessage(),resourceBundle.getString("Provisioning"),JOptionPane.ERROR_MESSAGE);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this,resourceBundle.getString("Error occured while getting data of activities"),resourceBundle.getString("Provisioning"),JOptionPane.INFORMATION_MESSAGE);
			}
		}
        synchronized(this)
        {
            reloadingInProgress = false;
        }
	    return dataVector;
	}
    	public String key()
	 {
		return "ProvisioningOperationsPanel";
	}
	public String getPanelClassName()
	{
		return "com.adventnet.nms.provisioning.ui.ProvisioningOperationsPanel";
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

  
  //<End_setUpProperties>
  } 
  public void initVariables()
  {
        //<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        JScrollPane1= new javax.swing.JScrollPane();
        listView= new com.adventnet.beans.probeans.ProListView();

  
        //<End_initVariables>
	cellRenderer=new ListViewCellRenderer();
	changeNavigationButtons();

  } 
  public void setUpGUI(Container container)
  {
//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(6,6));
Top.add(JScrollPane1,BorderLayout.CENTER);
JScrollPane1.getViewport().add(listView);

  
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
     System.err.println(MessageFormat.format(resourceBundle.getString("Internal Error in ProvisioningOperationsPanel: {0}"), new Object[]{message}));
     //<End_showStatus_String>
  }
  public void showStatus(String message,Exception ex)
  {
     //<Begin_showStatus_String_Exception>
     System.err.println(MessageFormat.format(resourceBundle.getString("Internal Error in ProvisioningOperationsPanel: {0}"), new Object[]{message}));
     ex.printStackTrace();
     //<End_showStatus_String_Exception>
  }







  public ProvisioningOperationsPanel()
  {
    //<Begin_ProvisioningOperationsPanel>
    //this.init();
	 this.start();
  
    //<End_ProvisioningOperationsPanel>
  }

  public ProvisioningOperationsPanel(java.applet.Applet applet)
  {
    //<Begin_ProvisioningOperationsPanel_java.applet.Applet>
    this.applet = applet;
    //this.init();
	 this.start(); 
  
    //<End_ProvisioningOperationsPanel_java.applet.Applet>
  }
	public  void mouseEntered(MouseEvent e) 
	{
	}
	public  void mouseExited(MouseEvent e) 
	{
	}
	public  void mousePressed(MouseEvent e) 
	{
	}
	public  void mouseReleased(MouseEvent e) 
	{
	}
	public  void mouseClicked(MouseEvent me) 
	{
		Object source=me.getSource();
		if(source instanceof JTable)
		{
			if(me.isMetaDown())
			{
				JTable table = (JTable)source;
				Point point = me.getPoint();
				int row = table.rowAtPoint(point);
				if(row != -1)
				{
					table.setRowSelectionInterval(row, row);
					JPopupMenu popupMenu = getTableMenu().getPopupMenu();
					if(popupMenu != null)
					{
						SwingUtilities.convertPointToScreen(point, table);	
						Dimension scr_dim =	Toolkit.getDefaultToolkit().getScreenSize();
						if((point.getX() + popupMenu.getPreferredSize().getWidth()) > scr_dim.getWidth())
						{								
							point.setLocation((point.getX() - popupMenu.getPreferredSize().getWidth()), point.getY());
						}
						if((point.getY() + popupMenu.getPreferredSize().getHeight()) > scr_dim.getHeight())
						{	
							point.setLocation(point.getX(), (point.getY() - popupMenu.getPreferredSize().getHeight()));
						}
						SwingUtilities.convertPointFromScreen(point, table);
						popupMenu.show(table, (int)point.getX(), (int)point.getY());
					}
				}
			}
			else if(me.getClickCount()==2)
			{
				viewResult();
			}
		}
	}
	
	private JMenu getTableMenu()
	{
		StringTokenizer tokenizer = new StringTokenizer((String)getCurrentNodeProperties().get("TABLE-POPUP-MENU"), ",");

		JMenu tableMenu = null;	
		
		if(tokenizer.countTokens() > 0)
		{
			JMenuBar panelMenuBar = getPanelMenuBar();
			
			Vector menus = new Vector();

			while(tokenizer.hasMoreTokens())
			{
				String token = tokenizer.nextToken().trim();

				for(int i=0; i<panelMenuBar.getMenuCount(); i++)
				{
					JMenu menu = panelMenuBar.getMenu(i);

					if(menu.getText().equals(ProvClientUtils.getString(token)))
					{
						menus.addElement(menu);
					}
				}	
			}

			if(menus.size() > 0)
			{
				tableMenu = new JMenu();

				if(menus.size() == 1)	
				{
					JMenu menu = (JMenu)menus.elementAt(0);

					if(menu != null)
					{
						int component_count = menu.getMenuComponentCount();

						for(int j=0; j<component_count; j++)
						{
							Component component  = menu.getMenuComponent(0);

							tableMenu.add(component);
						}
					}
				}
				else
				{
					for(int i=0; i<menus.size(); i++)
					{
						JMenu menu = (JMenu)menus.elementAt(i);

						if(menu != null)
						{
							tableMenu.add(menu);

							if(i < (menus.size() - 1))
							{
								tableMenu.addSeparator();
							}
						}
					}
				}
			}
		}

		return tableMenu;
	}

	public void actionPerformed(ActionEvent e)
	{
		String es=e.getActionCommand();
		if (es.equals("View Result"))
		{
			viewResult();
		}
		else if (es.equals("Stop"))
		{
			int r=table.getSelectedRow();
			if (r>-1)
			{
				int c4=table.convertColumnIndexToView(4);
				String stat=table.getValueAt(r,c4).toString().trim();
				int c0=table.convertColumnIndexToView(0);
				String opId=table.getValueAt(r,c0).toString().trim();
				if (stat.equalsIgnoreCase("ABORTED"))
				{
					JOptionPane.showMessageDialog(this, MessageFormat.format(resourceBundle.getString("Activity with id {0} is already ABORTED"), new Object[]{opId}), resourceBundle.getString("Provisioning"),JOptionPane.PLAIN_MESSAGE);
				}
				else
				{
					try
					{
						api.stopScheduledTask(opId);
						reloadTable(getVectorOfTuples());
						JOptionPane.showMessageDialog(this,MessageFormat.format(resourceBundle.getString("Activity with id {0} is Stopped"), new Object[]{opId}),resourceBundle.getString("Provisioning"),JOptionPane.INFORMATION_MESSAGE);
					}
					catch(Exception exc)
					{
						if((exc.getMessage()!=null)&&(!(exc.getMessage().trim().equals(""))))
						{
							if (exc.getMessage().trim().length()>50)
							{
								test.provisioning.MessageWindow.showMessage(NmsClientUtil.getParentFrame(),resourceBundle.getString("Provisioning"),exc.getMessage());
							}
							else
							{
								JOptionPane.showMessageDialog(this,exc.getMessage(),resourceBundle.getString("Provisioning"),JOptionPane.ERROR_MESSAGE);
							}
						}
						else
						{
							JOptionPane.showMessageDialog(this, MessageFormat.format(resourceBundle.getString("Error occured while stoping the activity with id {0}"), new Object[]{opId}), resourceBundle.getString("Provisioning"),JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this,resourceBundle.getString("Select a scheduled Activity to stop it"),resourceBundle.getString("Provisioning"),JOptionPane.INFORMATION_MESSAGE);
			}
		}
		else if (es.equals("Refresh"))
		{
			reloadTable(getVectorOfTuples());
		}
		else if (es.equals("ShowAll"))
		{
			selectedTemplateName=null;
			reloadTable(getVectorOfTuples());
		}
		else if (es.equals("Delete"))
		{
			int r=table.getSelectedRow();
			if (r>-1)
			{
				int c4=table.convertColumnIndexToView(4);
				String stat=table.getValueAt(r,c4).toString().trim();
				if ((stat.equalsIgnoreCase("FAILED"))||(stat.equalsIgnoreCase("FINISHED"))||(stat.equalsIgnoreCase("ABORTED")))
				{
					int c0=table.convertColumnIndexToView(0);
					String opId=table.getValueAt(r,c0).toString().trim();
					int ok=JOptionPane.showConfirmDialog(this, MessageFormat.format(resourceBundle.getString("Data for activity id {0} will be permanently deleted from server.Do you want to continue ?"), new Object[]{opId}),resourceBundle.getString("Provisioning"),JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
					if (ok==JOptionPane.YES_OPTION)
					{
						try
						{
							api.deleteTuple(opId);
							JOptionPane.showMessageDialog(this,MessageFormat.format(resourceBundle.getString("Data deleted for activity with id {0}"), new Object[]{opId}), resourceBundle.getString("Provisioning"),JOptionPane.INFORMATION_MESSAGE);
							reloadTable(getVectorOfTuples());
						}	
						catch(Exception exception)
						{
							if((exception.getMessage()!=null)&&(!(exception.getMessage().trim().equals(""))))
							{
								 if (exception.getMessage().trim().length()>50)
								{
									test.provisioning.MessageWindow.showMessage(NmsClientUtil.getParentFrame(),resourceBundle.getString("Provisioning"),exception.getMessage());
								}
								else
								{
									JOptionPane.showMessageDialog(this,exception.getMessage(),resourceBundle.getString("Provisioning"),JOptionPane.ERROR_MESSAGE);
								}
							}
							else
							{
								JOptionPane.showMessageDialog(this, MessageFormat.format(resourceBundle.getString("Error occured while deleting the activity with id {0}"), new Object[]{opId}),resourceBundle.getString("Provisioning"),JOptionPane.INFORMATION_MESSAGE);
							}
						}
					}
				}
				else
				{
					JOptionPane.showMessageDialog(this,resourceBundle.getString("Provisioning activity is not completed.Please try after some time"),resourceBundle.getString("Provisioning"),JOptionPane.INFORMATION_MESSAGE);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this,resourceBundle.getString("Select an Activity to delete its data"),resourceBundle.getString("Provisioning"),JOptionPane.INFORMATION_MESSAGE);
			}
		}
		else if (es.equals("Screen Refresh Interval"))
		{
			ItemListener il=new ItemListener()
			{
				public void itemStateChanged(ItemEvent e)
				{
					if (rb1.isSelected())
					{
						s.setMinimum(1);
						s.setMaximum(24);
			l.setText(MessageFormat.format(resourceBundle.getString("New Settings : {0} "), new Object[]{new Integer(s.getValue())})+resourceBundle.getString("Hours"));
					}
					else if (rb2.isSelected())
					{
						s.setMinimum(1);
						s.setMaximum(59);
			l.setText(MessageFormat.format(resourceBundle.getString("New Settings : {0} "), new Object[]{new Integer(s.getValue())})+resourceBundle.getString("Minutes"));
					}
					else if (rb3.isSelected())
					{
						s.setMinimum(30);
						s.setMaximum(59);
			l.setText(MessageFormat.format(resourceBundle.getString("New Settings : {0} "), new Object[]{new Integer(s.getValue())})+resourceBundle.getString("Seconds"));
					}
				}
			};

			ChangeListener c=new ChangeListener()
			{
				public void stateChanged(ChangeEvent e)
				{
			l.setText(MessageFormat.format(resourceBundle.getString("New Settings : {0} ")+resourceBundle.getString(bg.getSelection().getActionCommand()), new Object[]{new Integer(s.getValue())}));
				}
			};
			rb1.addItemListener(il);
			rb2.addItemListener(il);
			rb3.addItemListener(il);
			s.addChangeListener(c);
			if(reloadTime>=60*60*1000)
			{
				int time=((int)(reloadTime/(60*60*1000)));
				cl.setText(MessageFormat.format(resourceBundle.getString("Current Settings : {0} "), new Object[]{new Integer(time)})+resourceBundle.getString("Hours"));
				rb1.setSelected(true);
				s.setValue(time);
			}
			else if(reloadTime>=60*1000)
			{
				int time=((int)(reloadTime/(60*1000)));
				cl.setText(MessageFormat.format(resourceBundle.getString("Current Settings : {0} "), new Object[]{new Integer(time)})+resourceBundle.getString("Minutes"));
				rb2.setSelected(true);
				s.setValue(time);
			}
			else
			{
				int time=((int)(reloadTime/1000));
				cl.setText(MessageFormat.format(resourceBundle.getString("Current Settings : {0} "), new Object[]{new Integer(time)})+resourceBundle.getString("Seconds"));
				rb3.setSelected(true);
				s.setValue(time);
			}
			cl.updateUI();
			l.updateUI();
			s.updateUI();
			rb1.updateUI();
			rb2.updateUI();
			rb3.updateUI();
			rp.updateUI();
			p.updateUI();
			int i=JOptionPane.showConfirmDialog(this,p,resourceBundle.getString("Provisioning"),JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
			if (i==JOptionPane.OK_OPTION)
			{
				PureClientUtils.scheduler.removeTask(this);
				nextReloadTime=0;
				if (rb1.isSelected())
				{
					reloadTime=s.getValue()*60*60*1000;
				}
				else if (rb2.isSelected())
				{
					reloadTime=s.getValue()*60*1000;
				}
				else if (rb3.isSelected())
				{
					reloadTime=s.getValue()*1000;
				}
				PureClientUtils.scheduler.scheduleTask(this,System.currentTimeMillis()+reloadTime);
				nextReloadTime=System.currentTimeMillis()+reloadTime;
			}
			s.removeChangeListener(c);
			rb1.removeItemListener(il);
			rb2.removeItemListener(il);
			rb3.removeItemListener(il);
		}
		else if(es.equals("provOperationsHelp"))
		{
			NmsClientUtil.showHelpBasedOnKey(key());
		}
	}
	
	public void run()
	{
            //Added check to see if socket is alive. Esp. license rejected
            //Applet clients socket 
            if (PureClientUtils.commonSocket != null)
            {
		reloadTable(getVectorOfTuples());
		PureClientUtils.scheduler.scheduleTask(this,System.currentTimeMillis()+reloadTime);
		nextReloadTime=System.currentTimeMillis()+reloadTime;
            }
	}
	public void addReloadAllMenuItem()
	{
		Vector v=NmsUiAPI.getMenuVector(key());
		if (v!=null)
		{
			for (int i=0;i<v.size();i++)
			{
				JMenu m=(JMenu)(v.elementAt(i));
				if (m.getText().equals(resourceBundle.getString("ActivityList")))
				{
					boolean missing=true;
					for(int j=0;j<m.getItemCount();j++)
					{
						JMenuItem mi=m.getItem(j);
						if(mi!=null)
						{
							String s=mi.getText();
							if(s.equals(resourceBundle.getString("ShowAll")))
							{
								missing=false;
								break;
							}
						}
					}
					if (missing)
					{
						NmsMenuItem mi=new NmsMenuItem(resourceBundle.getString("ShowAll"));
						mi.setActionCommand("ShowAll");
						mi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5,Event.SHIFT_MASK));
						mi.setFont(NmsClientUtil.getFont());
						mi.addActionListener(this);
						m.add(mi);
					}
				}
			}
			NmsUiAPI.setMenuVector(key(),v);
		}
	}
	public void deleteReloadAllMenuItem()
	{
		Vector v=NmsUiAPI.getMenuVector(key());
		if (v!=null)
		{
			for (int i=0;i<v.size();i++)
			{
				JMenu m=(JMenu)(v.elementAt(i));
				if (m.getText().equals(resourceBundle.getString("ActivityList")))
				{
					for(int j=0;j<m.getItemCount();j++)
					{
						JMenuItem mi=m.getItem(j);
						if(mi!=null)
						{
							String s=mi.getText();
							if(s.equals(resourceBundle.getString("ShowAll")))
							{
								m.remove(mi);
								NmsUiAPI.setMenuVector(key(),v);
								selectedTemplateName=null;
								break;
							}
						}
					}
				}
			}
		}
	}

	public JMenu getTreePopupMenu()
	{
		return getTableMenu();
	}
	public void setSelectedTemplateName(String s)
	{
		selectedTemplateName=s;
	}
	public String getSelectedTemplateName()
	{
		return selectedTemplateName;
	}
	private void viewResult()
	{
		int r=table.getSelectedRow();
		if (r>-1)
		{
			int c4=table.convertColumnIndexToView(4);
			String stat=table.getValueAt(r,c4).toString().trim();
			if ((stat.equalsIgnoreCase("FINISHED_RESCHEDULED"))||(stat.equalsIgnoreCase("FINISHED"))||(stat.equalsIgnoreCase("ABORTED")))
			{
				int c0=table.convertColumnIndexToView(0);
				String opId=table.getValueAt(r,c0).toString().trim();
				try
				{
					String res=api.viewResult(opId);
					TemplateResult tr = new TemplateResult(res);
					if (tr.getAttribute("errorMessage").equals(""))
					{
						ProvisioningResult resPanel=new ProvisioningResult(null,stat,res);
						JPanel tempPanel=new JPanel();
						JButton b=new JButton(resourceBundle.getString("Close"));
						b.addActionListener
							(
							 new ActionListener()
							 {
							 public void actionPerformed(ActionEvent ev)
							 {
							 JButton s=(JButton)ev.getSource();
							 s.getTopLevelAncestor().setVisible(false);
							 }
							 }
							);
						tempPanel.add(b);
						JDialog d=(new JOptionPane()).createDialog(this,MessageFormat.format(resourceBundle.getString("ProvisioningResult {0}"), new Object[]{opId}));
						d.getContentPane().removeAll();
						d.getContentPane().setLayout(new BorderLayout());
						d.getContentPane().add(resPanel,BorderLayout.CENTER);
						d.getContentPane().add(tempPanel,BorderLayout.SOUTH);
						d.setSize((int)(getWidth()*3/4),(int)(getHeight()*3/4));
						NmsClientUtil.centerWindow(d);
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
					else 
					{
						 if (tr.getAttribute("errorMessage").trim().length()>50)
						{
							test.provisioning.MessageWindow.showMessage(NmsClientUtil.getParentFrame(),resourceBundle.getString("Provisioning"),tr.getAttribute("errorMessage"));
						}
						else
						{
							JOptionPane.showMessageDialog(this,tr.getAttribute("errorMessage"),resourceBundle.getString("Provisioning Result"),JOptionPane.INFORMATION_MESSAGE);
						}
					}

				}
				catch(Exception exc)
				{
					if((exc.getMessage()!=null)&&(!(exc.getMessage().trim().equals(""))))					 
					{
						 if (exc.getMessage().trim().length()>50)
						{
							test.provisioning.MessageWindow.showMessage(NmsClientUtil.getParentFrame(),resourceBundle.getString("Provisioning"),exc.getMessage());
						}
						else
						{
							JOptionPane.showMessageDialog(this,exc.getMessage(),resourceBundle.getString("Provisioning Result"),JOptionPane.INFORMATION_MESSAGE);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(this, MessageFormat.format(resourceBundle.getString("Error occured while trying to get the result of the activity with id {0}"),new Object[]{opId}),resourceBundle.getString("Provisioning"),JOptionPane.INFORMATION_MESSAGE);
					}
			    }
			}
			else
			{
				JOptionPane.showMessageDialog(this,resourceBundle.getString("Provisioning Activity is not completed.Please try after some time"),resourceBundle.getString("Provisioning"),JOptionPane.INFORMATION_MESSAGE);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(this,resourceBundle.getString("Select an Activity to view the result"),resourceBundle.getString("Provisioning"),JOptionPane.INFORMATION_MESSAGE);
		}
	}
	



	private void changeNavigationButtons(){
		String imageDir=null;
		Icon[] imageIcons=new Icon[6];
		if(NmsClientUtil.applet != null)
		{
			imageDir = NmsClientUtil.applet.getDocumentBase () +    "../images";//No Internationalisation
		}

		//String imageDir = NmsClientUtil.applet.getDocumentBase () +   "../images";//No Internationalisation
		try
		{
			JButton button=listView.getButtonGroup().getFirstButton();
			button.setIcon(NmsClientUtil.getImageIcon(new URL (imageDir+"/first" + NmsClientUtil.getImageType())));//No Internationalisation
			button.setDisabledIcon(NmsClientUtil.getImageIcon(new URL (imageDir+"/first_dis" + NmsClientUtil.getImageType())));//No Internationalisation
			button.setRolloverIcon(NmsClientUtil.getImageIcon(new URL (imageDir+"/first_mo" + NmsClientUtil.getImageType())));//No Internationalisation
			button.setBorderPainted(false);

			button=listView.getButtonGroup().getLastButton();
			button.setIcon(NmsClientUtil.getImageIcon(new URL (imageDir+"/last" + NmsClientUtil.getImageType())));//No Internationalisation
			button.setDisabledIcon(NmsClientUtil.getImageIcon(new URL (imageDir+"/last_dis" + NmsClientUtil.getImageType())));//No Internationalisation
			button.setRolloverIcon(NmsClientUtil.getImageIcon(new URL (imageDir+"/last_mo" + NmsClientUtil.getImageType())));//No Internationalisation
			button.setBorderPainted(false);


			button=listView.getButtonGroup().getNextButton();
			button.setIcon(NmsClientUtil.getImageIcon(new URL (imageDir+"/next" + NmsClientUtil.getImageType())));//No Internationalisation
			button.setDisabledIcon(NmsClientUtil.getImageIcon(new URL (imageDir+"/next_dis" + NmsClientUtil.getImageType())));//No Internationalisation
			button.setRolloverIcon(NmsClientUtil.getImageIcon(new URL (imageDir+"/next_mo" + NmsClientUtil.getImageType())));//No Internationalisation
			button.setBorderPainted(false);

			button=listView.getButtonGroup().getPreviousButton();
			button.setIcon(NmsClientUtil.getImageIcon(new URL (imageDir+"/back1" + NmsClientUtil.getImageType())));//No Internationalisation
			button.setDisabledIcon(NmsClientUtil.getImageIcon(new URL (imageDir+"/back1_dis" + NmsClientUtil.getImageType())));//No Internationalisation
			button.setRolloverIcon(NmsClientUtil.getImageIcon(new URL (imageDir+"/back_mo" + NmsClientUtil.getImageType())));//No Internationalisation
			button.setBorderPainted(false);

		
		}
		catch(Exception e){
			NmsClientUtil.err(null,NmsClientUtil.GetString("Images not found : ") + e.getMessage()); //No Internationalization
			e.printStackTrace();
		}
	}


  
}











