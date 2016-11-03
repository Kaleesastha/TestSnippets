//$Id: CustomPropTableModel.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $
package com.adventnet.nms.tools.mowizard ; 


 import java.awt.*;
 import java.awt.event.*;
 import javax.swing.*;
 import javax.swing.event.*;

 public class CustomPropTableModel extends javax.swing.table.DefaultTableModel
 {
	 public CustomPropTableModel()
	 {

	 }
	

	CustomPropTableModel(Object[] tableHdr,int rowNum) {
		super(tableHdr,rowNum);
	}
	
	public boolean isCellEditable(int row,int col) {
		return false;
	}
	
	public void setColumnIdentifiers(Object[] tableHdr) {
		super.setColumnIdentifiers(tableHdr);	
	}	

	public Object[] getColumnIdentifiers() {
		Object[] tableHdr=new Object[getColumnCount()];
		
		for(int i=getColumnCount()-1;i>=0;i--) {
			tableHdr[i]=getColumnName(i);
		}	
		return tableHdr;
	}	
	
 }

