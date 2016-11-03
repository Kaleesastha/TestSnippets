//$Id: TableToString.java,v 1.1 2006/08/29 13:57:02 build Exp $
 package com.adventnet.security.ui ; 


 import java.awt.*;
 import java.awt.event.*;
 import javax.swing.*;
 import javax.swing.event.*;
 import javax.swing.table.*;
import java.io.File;
import java.util.Vector;


 public class TableToString 
 {
	StringBuffer buffer = null;
	StringBuffer tempBuffer = null;
	
	public TableToString()
	 {

	 }
	public String convertToStream(JTable table)
	{
		buffer = new StringBuffer();
		String seper = " # ";
		DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
		Vector tempVec = tableModel.getDataVector();
		int size = tempVec.size();

		for(int i=0;i<size;i++)
		{
			Vector temp = (Vector)tempVec.elementAt(i);
			int size1 = temp.size();
			StringBuffer columnHead = new StringBuffer();
			tempBuffer = new StringBuffer();
			for(int j=0;j<size1;j++)
			{
				 if(i == 0 && j==0)
				{
					for(int k = 0;k<size1;k++)
					{
						String name = tableModel.getColumnName(k);
						columnHead.append(name);
						if(k != size1-1)
						{
							columnHead.append("   ::   ");
						}	
					}
					buffer.append("======================================================================");
					buffer.append(seper);
					buffer.append(columnHead);
					buffer.append(seper);
					buffer.append("======================================================================");
					buffer.append(seper);
				}
				tempBuffer.append(temp.elementAt(j));
				if(j != size1-1)
				{
					tempBuffer.append("  ::   ");
				}	
			}	
			buffer.append(tempBuffer);
			buffer.append(seper);
			buffer.append("------------------------------------------------------------------------");
			buffer.append(seper);
		}
		return new String(buffer);
	}
	 
 }




