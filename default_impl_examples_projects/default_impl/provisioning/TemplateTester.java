//$Id: TemplateTester.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
package test.provisioning;

import java.io.*;
import java.util.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

import com.adventnet.nms.provisioning.xml.*;
import com.adventnet.nms.provisioning.ui.*;
import com.adventnet.nms.provisioning.*;
import com.adventnet.nms.util.PureClientUtils;
import com.adventnet.nms.util.ClientFrameWorkAPI;
import com.adventnet.nms.util.NmsClientUtil;

public class TemplateTester extends JFrame implements WindowListener
{

    TemplateUIPanel tp;

	public TemplateTester() throws Exception
	{
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);
		Container container = getContentPane();
		tp = new TemplateUIPanel();
		container.add(tp);
		setTitle("Provisioning");
		pack();
		setVisible(true);
		tp.setFocus(tp);
	}

	public void windowOpened(WindowEvent ev)
	{
	}

	public void windowDeiconified(WindowEvent ev)
	{
	}

	public void windowIconified(WindowEvent ev)
	{
	}

	public void windowActivated(WindowEvent ev)
	{
	}

	public void windowDeactivated(WindowEvent ev)
	{
	}

	public void windowClosed(WindowEvent ev)
	{
		System.exit(0);
	}

	public void windowClosing(WindowEvent ev)
	{
        if(tp != null)
        {
            tp.confirmAndClose();
        }
	}

	public static void main (String[] args) throws Exception
	{
		new TemplateTester();
	} 

}

