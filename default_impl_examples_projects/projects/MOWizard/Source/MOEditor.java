//$Id: MOEditor.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $
package com.adventnet.nms.tools.mowizard;

import com.adventnet.editor.JMacs;
import java.awt.Container;
import java.awt.GridLayout;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.*;
import java.io.*;

public class MOEditor extends JPanel 
{
	public JMacs jmax;
    public MOEditor()
    {
		jmax = new JMacs(false);
        this.setLayout(new GridLayout(1, 1));
        jmax.setEditorPanel(this);
		jmax.setCurrentView(1);
    }

    public void OpenSourceFile(String s)
    {
		jmax.openFile(new File(s));
		JPanel dummyPanel=new JPanel();
		dummyPanel.add(jmax.getOOBrowserTreeSp());
    }

    public void closeFile(String s)
    {
        jmax.closeFile(new File(s));
    }

    public void exitEditor()
    {
        jmax.promptSaveAndExit();
    }

    public JMacs getJMacs()
    {
        return jmax;
    }

    public JPanel getMOEditorPanelfromJMacs()
    {
        return jmax.getEditorPanel();
    }
    public void setEditMenuItems(javax.swing.JMenuItem[] menuitems)
	{
		jmax.setEditMenuItems(menuitems);
	}

    public void saveSourceFile(File file)
    {
        jmax.saveFile(file);
    }
/*	public void setText(String strToAppend)
	{
		jmax.setEditorText(strToAppend);
	}
	public String getText()
	{
		return jmax.getEditorText();
	}*/
	public void setParentForFind(JFrame frm)
	{
		jmax.setParentForFindDialog(frm);	
	}
   }
