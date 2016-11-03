//$Id: NMSACCPanel.java,v 1.1 2006/08/29 13:57:01 build Exp $

package com.adventnet.nms.alertui.accpanel;

import java.awt.*;
import javax.swing.*;
import java.util.*;

import com.adventnet.nms.util.*;
import com.adventnet.nms.alertui.*;
import com.adventnet.nms.util.NmsClientUtil;

public class NMSACCPanel extends AlertCounts
{
	private boolean isAdded;
	public NMSACCPanel() throws ClassNotFoundException
	{
		super((AlertApplet) NmsUiAPI.getNmsPanelInstance("AlertApplet"));
		Class.forName("org.jfree.chart.ChartPanel");
		removeAll();
		setLayout(new BorderLayout(5,5));
		add(ACCPanel.getInstance(), BorderLayout.CENTER);
		isAdded = true;
		
	}


	public void updateCounts(Vector vect)
	{
		while(!isAdded)
		{
			try
			{
				Thread.sleep(100);
			}
			catch(InterruptedException ie)
			{
			}
		}
		ACCPanel.getInstance().updateCounts(vect);
	}
}

