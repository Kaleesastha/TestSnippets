//$Id :$
package com.adventnet.nms.tools.CustomView;

import javax.swing.table.*;
import java.util.*;
class CustomizerTableModel extends DefaultTableModel
{
    CustomizerTableModel(Vector v,int i)
    {
        super(v,i);
    }
	public boolean isCellEditable(int row, int col) 
	{
    	if(col==1)
			return true;	
		else
			return false;
	}


}
