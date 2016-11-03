/* $Id: CorbaTableDemoListenerImpl.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $ */
import java.util.*;
import com.adventnet.snmp.snmp2.*;
import com.adventnet.snmp.mibs.*;
import com.adventnet.snmp.beans.*;
import javax.swing.table.*;
import javax.swing.event.*;

public class CorbaTableDemoListenerImpl extends 
com.adventnet.snmp.corba._SnmpTableListenerImplBase  implements TableModel {

	public CorbaTableDemoListenerImpl( com.adventnet.snmp.corba.SnmpTable tbl ){
		this.table = tbl;
	}
            
    // This method is called when trap is received by SnmpTrapReceiver
    public void tableChanged(com.adventnet.snmp.corba.TableEvent tblEvent) {
		try {
			StringBuffer sb = new StringBuffer();
			if ( tblEvent.startRow == 0) {
				tableData = new Vector();
				colName = new String[table.getColumnCount()];
				for (int i=0;i<table.getColumnCount();i++)
					colName[i]=table.getColumnName(i);	
			}
			if (table.getRowCount() == 0)
				System.err.println("Request failed or timed out. \n"+
						table.getErrorString());
			else {
				sb = new StringBuffer();
				String data[] = new String[table.getColumnCount()];
		        for (int j= tblEvent.startRow;j<=tblEvent.endRow;j++) { 
					for (int i=0;i<table.getColumnCount();i++)
					{
						data[i] = table.getValueAt(j,i);
					}
					tableData.addElement(data);
					TableModelEvent tevt = new TableModelEvent(
							this,tblEvent.startRow,tblEvent.endRow,
							tblEvent.column, tblEvent.Type);
					for (Enumeration en=tablelisteners.elements();
							en.hasMoreElements();) {
						TableModelListener rl = (TableModelListener) 
								en.nextElement();
						rl.tableChanged(tevt);
					}   
				}
	        }
	    } catch (Exception ex) {
			System.err.println("Failed: "+ ex);
		}
	} 

    com.adventnet.snmp.corba.SnmpTable table = null;    
	Vector tableData= null;
	String [] colName = null;
	Vector tablelisteners = new Vector();
    
	public  void addTableModelListener(TableModelListener l){
		tablelisteners.addElement(l);
	}
	public  Class getColumnClass(int columnIndex){
		return String.class;
	}
	public String getColumnName(int columnIndex){
		return table.getColumnName(columnIndex);
	} 
	public int getRowCount(){
		if(tableData == null)
			return 0;
		return tableData.size();
	} 
	public Object getValueAt(int rowIndex, int columnIndex){
		if( tableData.size() ==0)
			return null;
		String [] a = (String [])tableData.elementAt(rowIndex);
		return a[columnIndex];
	} 
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	} 
	public void removeTableModelListener(TableModelListener l){
		tablelisteners.removeElement(l);
	} 
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		
	}
	public int getColumnCount(){
		return table.getColumnCount();
	} 
}
