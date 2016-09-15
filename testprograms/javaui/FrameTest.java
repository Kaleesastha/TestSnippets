package com.adventnet.clienttest;

import com.adventnet.nms.startclient.*;

import javax.swing.*;
import java.awt.*;


/** This class is for testing Frame integration in WebNMS.
Paste the following lines in one of the menu files.
eg., user/root/framemenu.xml. Then compile the classs to classes/ dir. 
Restart the client.

	<MENU-ITEM name = "Frame Test"
			action_type = "openframe"
			action_value ="com.adventnet.clienttest.FrameTest" 
			shortcut_key="t"
		    accelerator_modifier="CNTRL"
	  		  accelerator_key="t">
	</MENU-ITEM>
*/
public class FrameTest extends JFrame implements NmsFrame
{

	public FrameTest()
	{
		System.out.println("inside constructor");
		setTitle("NmsFrame Integration Test");
		JPanel myPanel  = new JPanel();
		myPanel.setLayout(new BorderLayout());
		JLabel frameTest = new JLabel("Test frame ");
		myPanel.add(frameTest,BorderLayout.CENTER);
		getContentPane().add(myPanel);
		setSize(375,200);

	}

	/** init method with Applet parameter - NmsFrame method*/
  public void init(JApplet app)
  {
 			System.out.println("init called"); 
  }

	/** The show method to show the frame - NmsFrame method */
  public void setVisible(boolean flag)
  {
  	System.out.println("set visible called "+flag); 
	super.setVisible(flag);
  }


	
}


