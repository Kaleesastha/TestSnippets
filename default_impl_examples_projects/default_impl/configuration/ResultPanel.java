/* $Id: ResultPanel.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $ */
/*
 * @(#)ResultPanel.java	
 *
 * Copyright (c) 1996-2000 Adventnet, Inc. All Rights Reserved.
 * Please read the included COPYRIGHTS file for more details.
 * 
 * ADVENTNET, INC. MAKES NO REPRESENTATIONS OR WARRANTIES 
 * ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, 
 * FITNESS FOR A PARTICULAR PURPOSE, OR NON-INFRINGEMENT.  ADVENTNET,
 * INC. SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY 
 * LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE 
 * OR ITS DERIVATIVES.
 */

package com.adventnet.nms.example.config;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import com.adventnet.nms.util.NmsClientUtil;

/**
 * A class for showing the results of device configuration.
 */
class ResultPanel extends JPanel implements ActionListener
{
	JTextArea resultArea = null;
	JButton clear = null;
	JButton back = null;
	DeviceConfigDemo frame = null;

	public ResultPanel(DeviceConfigDemo frame)
	{
		this.frame = frame;
		setLayout(new BorderLayout(15, 15));
		resultArea = new JTextArea(20, 20);
		JScrollPane areaJPane = new JScrollPane(resultArea);
		add(areaJPane, "Center");

		JPanel commandPanel = new JPanel();

		back = new JButton(NmsClientUtil.GetString("Back", 'B'));
		back.setMnemonic('B');
		back.addActionListener(this);
		commandPanel.add(back);

		clear = new JButton(NmsClientUtil.GetString("Clear", 'C'));
		clear.setMnemonic('C');		
		clear.addActionListener(this);
		commandPanel.add(clear);

		add(commandPanel, "South");
		clear.setActionCommand("Clear");
		back.setActionCommand("Back");
	}

	void append(String result)
	{
		resultArea.append(result);
	}

	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getActionCommand().equals("Back"))
		{
			frame.getContentPane().remove(this);
			frame.getContentPane().add(frame.conPanel);
			frame.conPanel.updateUI();
		}
		else if(ae.getActionCommand().equals("Clear"))
		{
			resultArea.setText("");
		}
	}
}	
