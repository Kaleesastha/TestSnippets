/**
 * $Id: TestFrame.java,v 1.1.1.1 2001/01/10 15:20:13 nms Exp $
 */

package testbefe;

import com.adventnet.nms.startclient.NmsFrame;

import java.awt.event.*;
import javax.swing.*;

public class TestFrame implements NmsFrame
{
	TestClient tc;
	JFrame frame;
	
	public void init(JApplet app)
	{
		TestFrame tf = new TestFrame();
		frame = new JFrame();
		frame.setSize( 300, 300 );
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener( new WindowAdapter()
			{
			public void windowClosing( WindowEvent we )
			{
				if( we.getSource().equals(frame) )
				{
					System.out.println( "###Inside TestFrame - windowClosing() method..." );
					tc.close();
					frame.dispose();
				}
			}});
		frame.setVisible(true);
		tc = new TestClient();
		tc.sendData();
	}
	
	public void setVisible( boolean visible )
	{
		frame.setVisible(visible);
	}
}