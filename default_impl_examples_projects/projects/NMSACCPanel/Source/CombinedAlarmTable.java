// $Id: CombinedAlarmTable.java,v 1.1 2006/08/29 13:57:01 build Exp $

// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// Kindly refer to tcats_edit_source_files.html file under help/source_generation directory for details

//<Begin_Version>
//version$3
//<End_Version>
package com.adventnet.nms.alertui.accpanel;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.io.*;
import java.util.*;

import com.adventnet.nms.severity.*;
import com.adventnet.nms.util.*;


import java.awt.event.*;

public class CombinedAlarmTable extends JPanel  implements ACCPanelChartInterface,com.adventnet.apiutils.ParameterChangeListener
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JLabel JLabel2 = null;
	javax.swing.JSeparator JSeparator1 = null;
	javax.swing.JTable combinedTable = null;
	private com.adventnet.apiutils.ParameterObject po= null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>
	private boolean firstTime = true;
	private DefaultTableModel comTabMod;
	private Vector severityList=null;
	private String categoryBgValue=NmsClientUtil.applet.getParameter("ACC_CATEGORIES_BGCOLOR");
	private Color categoryBgColor=null;

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
        if (initialized) return; 
        setPreferredSize(new Dimension(getPreferredSize().width+250,getPreferredSize().height+200)); 
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
          showStatus("Error in init method",ex); 
        } 
        // let us set the initialized variable to true so  
        // we dont initialize again even if init is called 
        initialized = true; 

  
        //<End_init>
	}
	public String getParameter(String input)
  {
		//<Begin_getParameter_String> 
	if (po != null) 
	 { 
	   if(po.getParameter(input) != null) 
	     {
	       return (String)po.getParameter(input); 
	     }
	 }
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
            JPanel1.setBackground(new Color(-1));
            JPanel1.setPreferredSize(new Dimension(137,10));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+JPanel1,ex); 
          }

//<UserCode_Begin_Bean_JPanel1>

	
//<UserCode_End_Bean_JPanel1>

          try
          {
            JLabel1.setForeground(new Color(-16777216));
            JLabel1.setHorizontalTextPosition(4);
            JLabel1.setText("Severity");
            JLabel1.setHorizontalAlignment(0);
            JLabel1.setOpaque(true);
            JLabel1.setBackground(new Color(-1));
            JLabel1.setVerticalAlignment(1);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+JLabel1,ex); 
          }

//<UserCode_Begin_Bean_JLabel1>
		JLabel1.setText(NmsClientUtil.GetString("nmsclient.accalarmui.Severity"));
	
//<UserCode_End_Bean_JLabel1>

          try
          {
            JLabel2.setForeground(new Color(-16777216));
            JLabel2.setHorizontalTextPosition(4);
            JLabel2.setText("Category");
            JLabel2.setOpaque(true);
            JLabel2.setBackground(new Color(-1));
            JLabel2.setVerticalAlignment(1);
            JLabel2.setHorizontalAlignment(0);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+JLabel2,ex); 
          }

//<UserCode_Begin_Bean_JLabel2>
		JLabel2.setText(NmsClientUtil.GetString("nmsclient.accalarmui.Category"));
	
//<UserCode_End_Bean_JLabel2>

          try
          {
            combinedTable.setAutoResizeMode(0);
            combinedTable.setRowMargin(4);
            combinedTable.setShowVerticalLines(true);
            combinedTable.setShowHorizontalLines(true);
            combinedTable.setIntercellSpacing(new Dimension(5,4));
            combinedTable.setBackground(new Color(-1));
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+combinedTable,ex); 
          }

//<UserCode_Begin_Bean_combinedTable>

	
//<UserCode_End_Bean_combinedTable>
		combinedTable.setPreferredSize(new Dimension(combinedTable.getPreferredSize().width+212,combinedTable.getPreferredSize().height+130));
		JPanel1.setPreferredSize(new Dimension(JPanel1.getPreferredSize().width+87,JPanel1.getPreferredSize().height+6));

  
          //<End_setUpProperties>
	}
	public void initVariables()
  {
		//<Begin_initVariables> 
	 if(po == null)
	{
	po = new com.adventnet.apiutils.ParameterObject();
	}
        Top= new javax.swing.JPanel();
        JPanel1= new javax.swing.JPanel();
        JLabel1= new javax.swing.JLabel();
        JLabel2= new javax.swing.JLabel();
        JSeparator1= new javax.swing.JSeparator();
        combinedTable= new javax.swing.JTable();
        initializeParameters(); 

  
          //<End_initVariables>
	severityList=new Vector();
	if(categoryBgValue!=null){
	   categoryBgColor=NmsClientUtil.getColorFromRGBValue(categoryBgValue);
	}
	}
	public void setUpGUI(Container container)
  {
		//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(0,0));
Top.add(JPanel1,BorderLayout.NORTH);
JPanel1.setLayout(new GridBagLayout());
inset = new Insets(0,0,0,0);
setConstraints(0,0,1,1,0.8,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel1.add(JLabel1,cons);
inset = new Insets(0,0,0,0);
setConstraints(1,0,1,1,0.2,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel1.add(JLabel2,cons);
inset = new Insets(0,0,0,0);
setConstraints(0,1,0,1,0.0,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel1.add(JSeparator1,cons);
Top.add(combinedTable,BorderLayout.CENTER);

  
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





	public CombinedAlarmTable()
  {
		//<Begin_CombinedAlarmTable>
    this.init();
  
    //<End_CombinedAlarmTable>
	}

	public CombinedAlarmTable(java.applet.Applet applet)
  {
		//<Begin_CombinedAlarmTable_java.applet.Applet>
    this.applet = applet;
    this.init();
  
    //<End_CombinedAlarmTable_java.applet.Applet>
	}
	public void setProperties(java.util.Properties props)
  {
		//<Begin_setProperties_java.util.Properties> 
	if(po != null)
	{
	po.setParameters(props);
	}
  
         //<End_setProperties_java.util.Properties>
	}
	private void initializeParameters()
  {
		//<Begin_initializeParameters> 
	 if(po != null) 
	   {
	    po.addParameterChangeListener(this);
	   }

  
          //<End_initializeParameters>
	}
	public void destroy()
  {
		//<Begin_destroy> 
	if(po != null)
	{
	 po.removeParameterChangeListener(this);
	}
  
         //<End_destroy>
	}
	public void setParameterObject(com.adventnet.apiutils.ParameterObject paramObj)
  {
		//<Begin_setParameterObject_com.adventnet.apiutils.ParameterObject> 
	this.po=paramObj;
	initializeParameters();
  
         //<End_setParameterObject_com.adventnet.apiutils.ParameterObject>
	}
	public void parameterChanged(com.adventnet.apiutils.ParameterObject paramObj)
  {
		//<Begin_parameterChanged_com.adventnet.apiutils.ParameterObject> 

  
  //<End_parameterChanged_com.adventnet.apiutils.ParameterObject>
	}

	public void cleanTableModel(DefaultTableModel  dtm)
	{
		int rowCount = dtm.getRowCount();
		for(int i=0;i<rowCount;i++)
		{
			dtm.removeRow(0);
		}
	}

	public void updateCounts(Vector vect, SeverityInfo sevInfo)
	{
		try
		{
			int colCount = combinedTable.getColumnCount();
			String[] totRow = null;
			String[] categoryTotal=null;
			cleanTableModel(comTabMod);
			for(Enumeration enu = vect.elements();enu.hasMoreElements();)
			{
				String[] rowVector = new String[colCount];
				Properties severityProp = (Properties) enu.nextElement();
				String category = severityProp.getProperty("category");
				if(category == null) break;
				String total = severityProp.getProperty("total");
				for(Enumeration sevEnu = severityProp.propertyNames();sevEnu.hasMoreElements();)
				{
					String sevStr = sevEnu.nextElement().toString();
					//NmsClientUtil.dbg("Severity : "+sevStr);
					String sevTotStr = severityProp.getProperty(sevStr);
					if(sevStr.equals("category") || sevStr.equals("total"))
					{
						continue;
					}
					int colNum = severityList.indexOf(sevStr);
					if(colNum!=-1){
						rowVector[colNum] = sevTotStr;
					}
				}
				rowVector[colCount-2] = total;
				rowVector[colCount-1] = category;
				if(category.equals("Totals"))
				{
					rowVector[colCount-1] = NmsClientUtil.GetString("nmsclient.comalarm.Total");
					totRow = rowVector;
				}
				else if(category.equals("Categories-Total")){
					rowVector[colCount-1] = NmsClientUtil.GetString("nmsclient.comalarm.categoryTotal");
 	  				categoryTotal= rowVector;
				}
				else if(!rowVector[colCount-2].equals("0"))
				{
					comTabMod.addRow(rowVector);
				}
			}

			//To add categorytotal and totals at the end of the table.
				//modified by Tinku to remove the rows when there is no Alert
			if(categoryTotal!=null  && !categoryTotal[colCount-2].equals("0")){
				comTabMod.addRow(categoryTotal);
			}
			if(totRow!=null){
				comTabMod.addRow(totRow);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public boolean isHandCursorNeeded(int x, int y)
	{
		boolean needed = false;
		if(y <= (comTabMod.getRowCount() * combinedTable.getRowHeight()))
		{
			needed = true;
		}
		return needed;
	}

	public String getCategory(int x, int y)
	{
		String cat = null;
		try
		{
			int  rowCount=comTabMod.getRowCount();
			int colCount = comTabMod.getColumnCount();
			int rowHeight=combinedTable.getRowHeight();
			if(y <= (rowCount*rowHeight))
			{
				int seleRow = combinedTable.getSelectedRow();
				if(seleRow!=-1){
					cat = combinedTable.getValueAt(seleRow, colCount-1).toString();
				}
			}
			//NmsClientUtil.dbg("Sele cate "+cat);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		if(cat !=null && cat.equals(NmsClientUtil.GetString("nmsclient.comalarm.Total"))){
			 cat = "all";
		}
		else if(cat !=null && cat.equals(NmsClientUtil.GetString("nmsclient.comalarm.categoryTotal"))){
			cat=getAllViewingCategories();	
		}
		return cat;
	}

	private String getAllViewingCategories(){
		int rowCount=comTabMod.getRowCount();
		int colCount=comTabMod.getColumnCount();
		String categories="";
		for(int i=0;i<rowCount;i++){
			String category=combinedTable.getValueAt(i,colCount-1).toString();
			if(category.equals(NmsClientUtil.GetString("nmsclient.comalarm.Total"))
			   || category.equals(NmsClientUtil.GetString("nmsclient.comalarm.categoryTotal"))){
				continue;
			}
			if(categories.equals("")){
				categories=category;
			}
			else{
				categories=categories+","+category;
			}

		}
		return categories;	
	}

	public int getSeverity(int x, int y)
	{
		int sev = -1;
		try
		{
			int rowCount=comTabMod.getRowCount();
			int columnCount=comTabMod.getColumnCount();
			int rowHeight=combinedTable.getRowHeight();
			if(y <= (rowCount*rowHeight))
			{
				int seleCol = combinedTable.getSelectedColumn();
				if(seleCol!=-1 && seleCol < columnCount-2)
				{
					String sevName=(String)severityList.elementAt(seleCol);
				
					sev = NmsClientUtil.severityInfo.getValue(sevName);
				}
			}
			//NmsClientUtil.dbg("Sele sev "+sev);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return sev;
	}
	public Component getOriginalComponent()
	{
		return combinedTable;
	}

	class SeverityTableCellRenderer extends DefaultTableCellRenderer
	{
		public SeverityTableCellRenderer()
		{
		}

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
				boolean hasFocus, int row, int column)
		{
			JLabel lab = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			lab.setOpaque(true);
			lab.setForeground(Color.black);
			String categoryName=(String)table.getValueAt(row,table.getColumnCount()-1);
	//		if(categoryName!=null && (categoryName.equals(NmsClientUtil.GetString("nmsclient.comalarm.Total"))
	//					||categoryName.equals(NmsClientUtil.GetString("nmsclient.comalarm.categoryTotal"))))
	//<Added to To give the respective severity colors for all the rows in the tabular view - Tinku
	                                    if(categoryName==null)
			{
				//last row either total row or categoriestotal row.
				lab.setBackground(Color.white);
			}
			else if(column==(table.getColumnCount()-2)){
				//total column
				lab.setBackground(Color.white);
			}
			else if(column==(table.getColumnCount()-1)){
				//Category column
				if(categoryBgColor!=null){
					lab.setBackground(categoryBgColor);
				}
				else{
					lab.setBackground(Color.white);
				}

			}
			else
			{
				//severity columns
				String severity=(String)severityList.elementAt(column);
				Color bgColor = NmsClientUtil.severityInfo.getColor(severity);
				if(bgColor!=null){
					lab.setBackground(bgColor);
				}
				if(column == 0){
					 lab.setForeground(Color.white);
				}
			}
			if(value!=null){
				lab.setText(value.toString());
			}
			if(column < table.getColumnCount()-1)
			{
				lab.setHorizontalAlignment(JLabel.CENTER);
			}
			else
			{
				lab.setHorizontalAlignment(JLabel.LEFT);
			}
			return lab;
		}
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

	public void initialize(Vector severity){
		this.severityList=severity;
		int colCount = severity.size() + 2;
		comTabMod = new DefaultTableModel(0, colCount)
		{
			public boolean isCellEditable(int r, int c)
			{
				return false;
			}
		};
		combinedTable.setModel(comTabMod);

		TableColumnModel tcm = combinedTable.getColumnModel();
		TableColumn tc=null;
		for(int i=0;i<severity.size();i++){
			tc = tcm.getColumn(i);
			tc.setPreferredWidth(25);

		}
		tc = tcm.getColumn(colCount-2);
		tc.setPreferredWidth(25);
		tc = tcm.getColumn(colCount-1);
		tc.setPreferredWidth(70);

		combinedTable.setDefaultRenderer(combinedTable.getColumnClass(0), new SeverityTableCellRenderer());
		

	}
}






