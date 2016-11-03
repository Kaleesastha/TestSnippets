
//$Id: SnmpTablePanel.java,v 1.2 2007/02/22 15:03:04 srajeswari Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// For more details please see EditingSourceFiles.html present in the docs directory

//<Begin_Version>
//version$1
//<End_Version>
package com.adventnet.nms.config;



import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.io.*;


public class SnmpTablePanel extends JPanel implements MibHandlerListener
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "ElementConfigurationResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JSplitPane JSplitPane1 = null;
	javax.swing.JScrollPane scrollPane = null;
	javax.swing.JTable table = null;
	javax.swing.JList list = null;
	javax.swing.JPanel JPanel3 = null;
	javax.swing.JPanel JPanel4 = null;
	javax.swing.JButton addTableButton = null;
	javax.swing.JButton removeTableButton = null;
	javax.swing.JPanel JPanel5 = null;
	javax.swing.JButton addRowButton = null;
	javax.swing.JButton modifyRowButton = null;
	javax.swing.JButton deleteRowButton = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JTextArea JTextArea1 = null;
	javax.swing.table.DefaultTableModel tableModel = null;
	javax.swing.DefaultListModel listModel = null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>

	private String previousTable = null;

	public SnmpTablePanel()
  {
		//<Begin_SnmpTablePanel>
    this.init();
  
    //<End_SnmpTablePanel>
	}

	public SnmpTablePanel(java.applet.Applet applet)
  {
		//<Begin_SnmpTablePanel_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_SnmpTablePanel_java.applet.Applet>
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
	public void setUpProperties()
  { 

		//<Begin_setUpProperties> 

          try
          {
            scrollPane.setVerticalScrollBarPolicy(22);
            scrollPane.setHorizontalScrollBarPolicy(32);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+scrollPane,ex); 
          }

//<UserCode_Begin_Bean_scrollPane>

//<UserCode_End_Bean_scrollPane>

          try
          {
            table.setModel(tableModel);
            table.setRowHeight(21);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+table,ex); 
          }

//<UserCode_Begin_Bean_table>

//<UserCode_End_Bean_table>

          try
          {
            list.setModel(listModel);
            list.setSelectionMode(0);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+list,ex); 
          }

//<UserCode_Begin_Bean_list>

//<UserCode_End_Bean_list>

          try
          {
            JPanel3.setBorder(new javax.swing.border.EtchedBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel3,ex); 
          }

//<UserCode_Begin_Bean_JPanel3>

//<UserCode_End_Bean_JPanel3>

          try
          {
            JPanel4.setBorder(new javax.swing.border.BevelBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel4,ex); 
          }

//<UserCode_Begin_Bean_JPanel4>

//<UserCode_End_Bean_JPanel4>

          try
          {
            addTableButton.setText(resourceBundle.getString("Add Table"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+addTableButton,ex); 
          }

//<UserCode_Begin_Bean_addTableButton>

//<UserCode_End_Bean_addTableButton>

          try
          {
            removeTableButton.setText(resourceBundle.getString("Remove Table"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+removeTableButton,ex); 
          }

//<UserCode_Begin_Bean_removeTableButton>

//<UserCode_End_Bean_removeTableButton>

          try
          {
            JPanel5.setBorder(new javax.swing.border.BevelBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel5,ex); 
          }

//<UserCode_Begin_Bean_JPanel5>

//<UserCode_End_Bean_JPanel5>

          try
          {
            addRowButton.setText(resourceBundle.getString("Add Row"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+addRowButton,ex); 
          }

//<UserCode_Begin_Bean_addRowButton>

//<UserCode_End_Bean_addRowButton>

          try
          {
            modifyRowButton.setText(resourceBundle.getString("Modify Row"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+modifyRowButton,ex); 
          }

//<UserCode_Begin_Bean_modifyRowButton>

//<UserCode_End_Bean_modifyRowButton>

          try
          {
            deleteRowButton.setText(resourceBundle.getString("Delete Row"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+deleteRowButton,ex); 
          }

//<UserCode_Begin_Bean_deleteRowButton>

//<UserCode_End_Bean_deleteRowButton>

          try
          {
            JPanel1.setBorder(new javax.swing.border.EtchedBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JPanel1,ex); 
          }

//<UserCode_Begin_Bean_JPanel1>

//<UserCode_End_Bean_JPanel1>

          try
          {
            JTextArea1.setOpaque(false);
            JTextArea1.setEditable(false);
            JTextArea1.setLineWrap(true);
            JTextArea1.setWrapStyleWord(true);
            JTextArea1.setText(resourceBundle.getString("Use the buttons given below the Table to configure \" Table Attributes\" for this task."));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JTextArea1,ex); 
          }

//<UserCode_Begin_Bean_JTextArea1>

//<UserCode_End_Bean_JTextArea1>

//<UserCode_Begin_Bean_tableModel>


//<UserCode_End_Bean_tableModel>
		JPanel5.setPreferredSize(new Dimension(JPanel5.getPreferredSize().width+10,JPanel5.getPreferredSize().height+10));

  
          //<End_setUpProperties>
	} 
	public void init()
  { 

		//<Begin_init> 
	if(getParameter("RESOURCE_PROPERTIES" ) != null)
	{
		localePropertiesFileName = getParameter("RESOURCE_PROPERTIES");
	}
	resourceBundle = com.adventnet.apiutils.Utility.getBundle(localePropertiesFileName,getParameter("RESOURCE_LOCALE"),applet);
        if (initialized) return; 
        setPreferredSize(new Dimension(getPreferredSize().width+646,getPreferredSize().height+485)); 
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
          showStatus(resourceBundle.getString("Error in init method"),ex); 
        } 
        // let us set the initialized variable to true so  
        // we dont initialize again even if init is called 
        initialized = true; 

  
         //<End_init>

	} 
	public void setUpConnections()
  { 

		//<Begin_setUpConnections> 

      JButton2_JButton2_conn1 JButton2_JButton2_conn11 =  new JButton2_JButton2_conn1();
      removeTableButton.addActionListener(JButton2_JButton2_conn11);
      deleteRowButton_deleteRowButton_conn1 deleteRowButton_deleteRowButton_conn11 =  new deleteRowButton_deleteRowButton_conn1();
      deleteRowButton.addActionListener(deleteRowButton_deleteRowButton_conn11);
      JButton3_JButton3_conn1 JButton3_JButton3_conn11 =  new JButton3_JButton3_conn1();
      addRowButton.addActionListener(JButton3_JButton3_conn11);
      list_list_conn2 list_list_conn21 =  new list_list_conn2();
      list.addListSelectionListener(list_list_conn21);
      JButton4_JButton4_conn1 JButton4_JButton4_conn11 =  new JButton4_JButton4_conn1();
      modifyRowButton.addActionListener(JButton4_JButton4_conn11);
      JButton1_JButton1_conn1 JButton1_JButton1_conn11 =  new JButton1_JButton1_conn1();
      addTableButton.addActionListener(JButton1_JButton1_conn11);
  
      //<End_setUpConnections>
	} 
	public void initVariables()
  { 

		//<Begin_initVariables> 
        Top= new javax.swing.JPanel();
        JPanel2= new javax.swing.JPanel();
        JSplitPane1= new javax.swing.JSplitPane();
        scrollPane= new javax.swing.JScrollPane();
        table= new javax.swing.JTable();
        list= new javax.swing.JList();
        JPanel3= new javax.swing.JPanel();
        JPanel4= new javax.swing.JPanel();
        addTableButton= new javax.swing.JButton();
        removeTableButton= new javax.swing.JButton();
        JPanel5= new javax.swing.JPanel();
        addRowButton= new javax.swing.JButton();
        modifyRowButton= new javax.swing.JButton();
        deleteRowButton= new javax.swing.JButton();
        JPanel1= new javax.swing.JPanel();
        JLabel1= new javax.swing.JLabel();
        JTextArea1= new javax.swing.JTextArea();
        tableModel= new javax.swing.table.DefaultTableModel();
        listModel= new javax.swing.DefaultListModel();

  
        //<End_initVariables>
	} 

	public void setUpGUI(Container container)
  { 

		//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
Top.add(JPanel2,cons);
JPanel2.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(0,0,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel2.add(JSplitPane1,cons);
JSplitPane1.setRightComponent(scrollPane);
scrollPane.getViewport().add(table);
JSplitPane1.setLeftComponent(list);
JSplitPane1.setDividerLocation(112);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel3,cons);
JPanel3.setLayout(new BorderLayout(15,5));
JPanel3.add(JPanel4,BorderLayout.WEST);
JPanel4.setLayout(new FlowLayout(1,5,5));
JPanel4.add(addTableButton);
JPanel4.add(removeTableButton);
JPanel3.add(JPanel5,BorderLayout.CENTER);
JPanel5.setLayout(new GridLayout(1,0,5,5));
JPanel5.add(addRowButton);
JPanel5.add(modifyRowButton);
JPanel5.add(deleteRowButton);
inset = new Insets(15,5,5,5);
setConstraints(0,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
Top.add(JPanel1,cons);
JPanel1.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.NORTHWEST,cons.NONE,inset,0,0);
JPanel1.add(JLabel1,cons);
inset = new Insets(5,5,5,5);
setConstraints(1,0,1,1,0.1,0.0,cons.NORTHWEST,cons.HORIZONTAL,inset,0,0);
JPanel1.add(JTextArea1,cons);

  
//<End_setUpGUI_Container>
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
            if (input.equals("HOST")) value = "localhost"; 
            if (input.equals("RESOURCE_PROPERTIES")) value = "ElementConfigurationResources"; 
            if (input.equals("PORT")) value = "161"; 
            }
        return value;

  
           //<End_getParameter_String>
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


	public void setResult(Properties data)
	{
		String identifier = data.get("identifier").toString();
		String label = data.get("label").toString();
		String temp = label+"("+identifier+")";
		boolean flag = false;
		/*		
				Enumeration enumerate = hashtableForAllTables.keys();
				while(enumerate.hasMoreElements())
				{
				String str = enumerate.nextElement().toString();
				if(str.equals(temp))
				{
				flag = true;
				}
				}
		 */
		int length = listModel.getSize();
		for(int i = 0; i < length; i++)
		{
			String str = (String)listModel.getElementAt(i);
			if(str.equals(temp))
			{
				flag = true;
			}
		}
		if(!flag)
		{
			updateDataStructures(); 
			listModel.addElement(temp);
			if(listModel.size() == 1)
			{
				addRowButton.setEnabled(true);	
				removeTableButton.setEnabled(true);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, resourceBundle.getString("This table already exists in the list"), resourceBundle.getString("Info"), JOptionPane.INFORMATION_MESSAGE);
			//addTableButtonActionPerformed();
			return;
		}	
		list.updateUI();

		Vector dataVector = (Vector)data.get("columns");

		mainVectorForColumns = dataVector;
		Vector v = new Vector();
		v.addElement(dataVector);
		v.addElement(new Vector());
		hashtableForAllTables.put(temp,v);
		tableModel.setDataVector(new Vector(),mainVectorForColumns);
		updateColumnWidths();
		previousTable = temp;
		list.setSelectedValue(temp,true);
	}

	private void updateColumnWidths()
	{
		// Processing done to adjust column widths
		int scrollWidth = scrollPane.getWidth() - 10; // to get tables width.
		int columnsTotalWidth = 0;
		int[] columnWidth = new int[mainVectorForColumns.size()];
		for(int i = 0; i < mainVectorForColumns.size(); i++)
		{
			String columnName = (String)mainVectorForColumns.elementAt(i);
			int width = columnName.length() * 12;
			columnWidth[i] = width;
			columnsTotalWidth += width;
		}
		int adjustValue = 0;
		if(columnsTotalWidth < scrollWidth)
		{
			table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			adjustValue = (scrollWidth - columnsTotalWidth) / mainVectorForColumns.size();
		}
		else
		{
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		}
		for(int i = 0; i < mainVectorForColumns.size(); i++)
		{
			table.getColumnModel().getColumn(i).setPreferredWidth(columnWidth[i] + adjustValue);
		}
		table.updateUI();
	}

	//<Begin__class_JButton2_JButton2_conn1>

 class JButton2_JButton2_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton2_JButton2_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  removeTableButtonActionPerformed();
     }
//<UserCode_End_Connection_JButton2_JButton2_conn1>
 }//<End__class_JButton2_JButton2_conn1>
	//<Begin__class_deleteRowButton_deleteRowButton_conn1>

 class deleteRowButton_deleteRowButton_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_deleteRowButton_deleteRowButton_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  deleteRowButtonActionPerformed();
     }
//<UserCode_End_Connection_deleteRowButton_deleteRowButton_conn1>
 }//<End__class_deleteRowButton_deleteRowButton_conn1>
	//<Begin__class_JButton3_JButton3_conn1>

 class JButton3_JButton3_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton3_JButton3_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  addRowButtonActionPerformed();
     }
//<UserCode_End_Connection_JButton3_JButton3_conn1>
 }//<End__class_JButton3_JButton3_conn1>
	//<Begin__class_JButton4_JButton4_conn1>

 class JButton4_JButton4_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton4_JButton4_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  modifyRowButtonActionPerformed();
     }
//<UserCode_End_Connection_JButton4_JButton4_conn1>
 }//<End__class_JButton4_JButton4_conn1>
	//<Begin__class_JButton1_JButton1_conn1>

 class JButton1_JButton1_conn1 implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton1_JButton1_conn1>



     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
  addTableButtonActionPerformed();
     }
//<UserCode_End_Connection_JButton1_JButton1_conn1>
 }//<End__class_JButton1_JButton1_conn1>

	public void addTableButtonActionPerformed()
	{
		configPanel.mibHandler.addMibHandlerListener(this);
		configPanel.mibHandler.showMib(this, "TABLE");
	}

	public void removeTableButtonActionPerformed()
	{
		int index = list.getSelectedIndex();
		if(index != -1)
		{
			//JOptionPane jp = new JOptionPane(); 
			//int option =  jp.showConfirmDialog(null, resourceBundle.getString("Are you sure about deleting the selected item"),null,jp.YES_NO_OPTION);
			if (configPanel.configClientUtils.getConfirmation(this))
			{ 
				hashtableForAllTables.remove(list.getSelectedValue());
				previousTable = null; //thiagu
				tableModel.setDataVector(new Vector(),new Vector());
				listModel.remove(index);
			}	
		}
		if(list.getModel().getSize() < 1)
		{
			removeTableButton.setEnabled(false);
			addRowButton.setEnabled(false);
			modifyRowButton.setEnabled(false);	
			deleteRowButton.setEnabled(false);	
		}
	}

	public void addRowButtonActionPerformed()
	{
		formColumnVector();
		if(mainVectorForColumns != null && mainVectorForColumns.size() > 0)
		{
			SnmpTableAdd s = new SnmpTableAdd(configPanel,SnmpTablePanel.this,isTemplate);
			configPanel.configClientUtils.centerWindow(s);
			s.populateUI(mainVectorForColumns);
			s.setVisible(true);
			modifyFlag = false;
		}
	}

	private void formColumnVector()
	{
		updateDataStructures();
		int colCount = table.getColumnCount();
		mainVectorForColumns = new Vector();
		for(int j = 0; j < colCount; j++)
		{
			mainVectorForColumns.add(table.getColumnName(j));
		}
	}

	public void modifyRowButtonActionPerformed()
	{
		formColumnVector();
		int row = table.getSelectedRow();
		if(row == -1)
		{
			configPanel.configClientUtils.showErrorDialog(configPanel.configClientUtils.getParentFrame(this), resourceBundle.getString("Select a row from the table to Modify"), resourceBundle.getString("Info"), "info");
			return;
		}
		selectedRowForModify = row;
		modifyFlag = true;
		if(row != -1)
		{
			SnmpTableAdd s = new SnmpTableAdd(configPanel,this,isTemplate);
			configPanel.configClientUtils.centerWindow(s);
			Vector v = new Vector();//(Vector)mainVectorForData.elementAt(row);
			for(int i = 0 ; i < tableModel.getColumnCount(); i++)
			{
				v.add(table.getValueAt(row,i));
			}
			s.populateUIForModify(mainVectorForColumns,v);
			s.setVisible(true);
		}
	}

	public void deleteRowButtonActionPerformed()
	{
		int [] selectedRows = table.getSelectedRows();
		int length = selectedRows.length;
		if(length == 0)
		{
			configPanel.configClientUtils.showErrorDialog(configPanel.configClientUtils.getParentFrame(this), resourceBundle.getString("Select a row from the table to Delete"), resourceBundle.getString("Info"), "info");
			return;
		}

		if (configPanel.configClientUtils.getConfirmation(this))
		{ 
			for(int j=length-1;j >=0;j--)
			{
				tableModel.removeRow(selectedRows[j]);
			} 
		}
		if(tableModel.getRowCount() < 1)
		{
			modifyRowButton.setEnabled(false);
			deleteRowButton.setEnabled(false);
		}
		else
		{
			if(tableModel.getRowCount() > selectedRows[0])
				table.setRowSelectionInterval(selectedRows[0], selectedRows[0]);
			else
				table.setRowSelectionInterval(selectedRows[0]-1, selectedRows[0]-1);
		}
	}

	public void updateDataStructures()
	{
		if(previousTable != null)
		{
			Vector mainVector = new Vector();
			Vector headerVector = new Vector();
			Vector dataVector = new Vector();
			Vector rowVector = null;
			int rowCount = table.getRowCount();
			int colCount = table.getColumnCount();
			for(int j = 0; j < colCount; j++)
			{
				headerVector.add(table.getColumnName(j));
			}
			for(int i = 0; i < rowCount; i++)
			{
				rowVector = new Vector();
				for(int j = 0; j < colCount; j++)
				{
					rowVector.add(table.getValueAt(i,j));
				}
				dataVector.add(rowVector);
			}
			mainVector.add(headerVector);
			mainVector.add(dataVector);
			hashtableForAllTables.put(previousTable,mainVector);
		}
	}

	public void jlistMousePressedEvent()
	{
		if(list.getSelectedIndex() != -1)
		{
			updateDataStructures(); 
			removeTableButton.setEnabled(true);
			addRowButton.setEnabled(true);
			String temp = list.getSelectedValue().toString();
			Vector tempVector = (Vector)hashtableForAllTables.get(temp);
			mainVectorForColumns = (Vector)tempVector.elementAt(0);
			mainVectorForData = (Vector)tempVector.elementAt(1);
			tableModel.setDataVector(mainVectorForData,mainVectorForColumns);
			updateColumnWidths();
			previousTable = list.getSelectedValue().toString(); 
		}	
	}

	public void updateEntries(Vector v)
	{
		String temp = list.getSelectedValue().toString();
		Vector tempVect = (Vector)hashtableForAllTables.get(temp);	
		Vector dataVect = (Vector)tempVect.elementAt(1);
		if(!modifyFlag)
		{
			dataVect.addElement(v);
		}
		else
		{
			dataVect.setElementAt(v, selectedRowForModify);
		}
		
		mainVectorForData = dataVect;
		if(mainVectorForColumns.size() < 3)
		{
			currentlyShowingColumns = mainVectorForColumns.size();
		}
		else
		{	
			currentlyShowingColumns = 3;
		}

		tableModel.setDataVector(mainVectorForData,mainVectorForColumns);
	}

	public Hashtable getAllValues()
	{
		updateDataStructures();
		Vector v = new Vector();
		Enumeration enumerate = hashtableForAllTables.keys();
		Hashtable hashtableToReturn = new Hashtable();
		while(enumerate.hasMoreElements())
		{
			String tableName = enumerate.nextElement().toString();
			Vector data = (Vector)hashtableForAllTables.get(tableName);
			Vector columnVector = (Vector)data.elementAt(0);
			Vector dataVector = (Vector)data.elementAt(1);
			hashtableToReturn.put(tableName,data);
		}
		return hashtableToReturn;
	}

	public void setValues(Hashtable data)
	{
		if(data != null)
		{
			hashtableForAllTables = data;
			Enumeration enumerate = hashtableForAllTables.keys();
			while(enumerate.hasMoreElements())
			{
				listModel.addElement(enumerate.nextElement());
			}
		}
	}

	private ConfigPanel configPanel = null;

	private Vector mainVectorForColumns = new Vector();
	private Vector mainVectorForData = new Vector();
	private int currentlyShowingColumns = 3;
	private Hashtable hashtableForAllTables = new Hashtable();
	private boolean isTemplate = false;
	private boolean modifyFlag = false;
	private int selectedRowForModify = 0;

	public SnmpTablePanel(ConfigPanel configPanel, boolean isTemplate)
	{
		this.configPanel = configPanel;
		this.isTemplate = isTemplate;

		applet = configPanel.applet;

		init();
		configInit();
	}

	private void configInit()
	{
		setPreferredSize(new Dimension(611,485));
		configPanel.mibHandler.addMibHandlerListener(this);
		//		scrollPane.getVerticalScrollBar().setMinimumSize(new Dimension(20,20));
		//		scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(20,20));
		ImageIcon nextIcon = configPanel.configClientUtils.getImage(configPanel.applet.getDocumentBase()+"../images/next_column.png");
		ImageIcon backIcon = configPanel.configClientUtils.getImage(configPanel.applet.getDocumentBase()+"../images/previous_column.png");

		table.setDefaultRenderer(Object.class,new SimpleTableSelectionRenderer());
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setDefaultEditor(Object.class,null);

		//setting mnemonics
		addTableButton.setMnemonic('D');
		removeTableButton.setMnemonic('E');
		addRowButton.setMnemonic('A');
		modifyRowButton.setMnemonic('M');
		deleteRowButton.setMnemonic('T');

	}

	public void tableMousePresses(java.awt.event.MouseEvent arg0)
	{
		int row = table.getSelectedRow();
		if(row != -1)
		{
			modifyRowButton.setEnabled(true);
			deleteRowButton.setEnabled(true);
		}

	}













	//<Begin__class_list_list_conn2>

 class list_list_conn2 implements javax.swing.event.ListSelectionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_list_list_conn2>



     //Listener Interface Methods Are Added Below 


     public void valueChanged( javax.swing.event.ListSelectionEvent arg0)
     {
  jlistMousePressedEvent();
     }
//<UserCode_End_Connection_list_list_conn2>
 }//<End__class_list_list_conn2>

 
  public void setProperties(java.util.Properties props)
  {
  //<Begin_setProperties_java.util.Properties> 

  
  //<End_setProperties_java.util.Properties>
  }
}
