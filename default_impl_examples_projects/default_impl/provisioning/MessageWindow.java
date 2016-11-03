/* $Id: MessageWindow.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $ */
/**
 * MessageWindow.java	
 * Copyright (c) 1998 AdventNet, Inc. All Rights Reserved.
 * Please read the associated COPYRIGHTS file for more details.

 * ADVENTNET, INC. MAKES NO REPRESENTATIONS OR WARRANTIES
 * ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE, OR NON-INFRINGEMENT.  ADVENTNET,
 * INC. SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
 * LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE
 * OR ITS DERIVATIVES.
 */
package test.provisioning;
import java.io.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import com.adventnet.nms.provisioning.ui.ProvClientUtils;

/** Dialog to display error Messages. */
public final class MessageWindow extends JDialog implements ActionListener,WindowListener
{
	JTextArea messageTA = new JTextArea();
	JScrollPane messageJsp = new JScrollPane(messageTA);
	JButton okBttn = new JButton(ProvClientUtils.getString("Ok"));
	private Point screenCenter;

	private static MessageWindow defaultMessageWindow;
	// To make sure Nobody instantiates this class by mistake
	private MessageWindow(JFrame frameArg)
	{
		super( frameArg,true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setUpGui();
		okBttn.addActionListener(this);
		addWindowListener(this);
		okBttn.requestDefaultFocus();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenCenter = new Point((screenSize.width/2),(screenSize.height/2));
	}

	private void setUpGui()
	{
		getContentPane().setLayout(new BorderLayout(5,5));

		messageTA.setLineWrap(true);
		messageTA.setWrapStyleWord(true);
		messageTA.setEditable(false);
		messageTA.setOpaque(false);
		messageTA.setBackground(getBackground());
		messageJsp.setBorder(new CompoundBorder(new TitledBorder(""),new EmptyBorder(5,5,2,5)));
		getContentPane().add(messageJsp,BorderLayout.CENTER);

		JPanel jp = new JPanel();
		getContentPane().add(jp,BorderLayout.SOUTH);

		okBttn.setMnemonic('O');
		jp.add(okBttn);
	}

	public static void showMessage(JFrame ownerArg,String titleArg,String messageArg)
	{
		if(defaultMessageWindow == null)
		{
			defaultMessageWindow = new MessageWindow(ownerArg);
		}
		defaultMessageWindow.showMessage(titleArg,messageArg, 6, 60);
	}

	private void showMessage(String titleArg,String messageArg,int rowsArg,int columnsArg)
	{
		setTitle(titleArg);
		setResizable(true);
		messageTA.setRows(rowsArg);
		messageTA.setColumns(columnsArg);
		messageTA.setText(messageArg);
		messageTA.setRequestFocusEnabled(false);
		pack();
		setSize(screenCenter.x,150);
		setLocation(screenCenter.x - getSize().width/2,
				screenCenter.y - getSize().height/2);
		setResizable(false);
		setVisible(true);
	}


	public void actionPerformed(ActionEvent aEvtArg)
	{
		setVisible(false);
	}

	public void windowClosing(WindowEvent e)
	{
		setVisible(false);
	}

	public void windowActivated(WindowEvent e)
	{
		okBttn.requestFocus();
	}

	public void windowOpened(WindowEvent e)
	{}
	public void windowClosed(WindowEvent e)
	{}
	public void windowIconified(WindowEvent e)
	{}
	public void windowDeiconified(WindowEvent e)
	{}
	public void windowDeactivated(WindowEvent e)
	{}

}
