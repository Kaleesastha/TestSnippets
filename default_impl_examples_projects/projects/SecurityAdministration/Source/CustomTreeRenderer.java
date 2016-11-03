//$Id: CustomTreeRenderer.java,v 1.1 2006/08/29 13:57:02 build Exp $
package com.adventnet.security.ui ; 

import java.awt.*;
import java.util.*;
import java.awt.Component;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;
import com.adventnet.beans.utilbeans.*;
import com.adventnet.nms.util.CommonBuilderUIInterface;


public class CustomTreeRenderer extends JLabel implements TreeCellRenderer
{

    ImageIcon task1 = null;
    ImageIcon unch = null;
    ImageIcon ch = null;
    ImageIcon tick_disabled = null;
    ImageIcon cross_disabled = null;
    
    public CustomTreeRenderer()
    {
        task1 = AuthMain.getBuilderUiIfInstance().getImage("circle.png");
        unch = AuthMain.getBuilderUiIfInstance().getImage("wrong01.png");
        ch = AuthMain.getBuilderUiIfInstance().getImage("tick01.png");
        cross_disabled = AuthMain.getBuilderUiIfInstance().getImage("cross_disabled.png");//NO I18N
        tick_disabled = AuthMain.getBuilderUiIfInstance().getImage("tick_disabled.png");//NO I18N
        setOpaque(false);
    }

public Component getTreeCellRendererComponent(JTree tree,Object value,boolean selected,boolean expanded,boolean leaf,int row,boolean hasFocus)
    {
	TreeObject tob = (TreeObject)(((DefaultMutableTreeNode)value).getUserObject());
	setText(tob.toString());
	
	if(tob.getString().equals("Operation Tree Root"))
 {
		setToolTipText("Root Node Can not be modified");//NO I18N
		setIcon(cross_disabled);
		return this;
	}
	setIcon(unch);

	if(tob.getInt() ==0) {
		setToolTipText("Not allowed");//NO I18N
		setIcon(task1);
	} else if(tob.getInt() ==2)
 {
		setToolTipText("Not allowed");//NO I18N
		setIcon(unch);
  
	} else if(tob.getInt() ==1) {
		setToolTipText("Allowed");//NO I18N
		setIcon(ch);
	} else if (tob.getInt() == 3)  {
		setToolTipText("Allowed");//NO I18N
		setIcon(tick_disabled);
	} else if (tob.getInt() == 4) {
		setToolTipText("Not allowed");//NO I18N
		setIcon(cross_disabled);
	}
	return this;
    }
 }




























