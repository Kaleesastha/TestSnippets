//Test program for testing Files Not Found when the AdventNet Themes are used and also for icon apperance in JOptionPane

//Procedure to be followed:

//1. Save the code snippet shown below as "ImageOnDialog.java" .

//2. Compile this file with "NmsClientClasses.jar" in the classpath.

//3. Edit startApplicationClient.bat/sh under "<WebNMS_HOME>/bin" directory to include this compiled class in the classpath.

//4.Include a menu-item tag in framemenu.xml which is present under <WebNMS_HOME>/html/defaultToAllUsers directory as following:

//      <MENU-ITEM name="Test"
//              action_type="INVOKE_CLASS"
//              action_value="test.ImageOnDialog">
//       </MENU-ITEM>

//5.Run startApplicationClient.bat/sh from <WebNMS_HOME>/bin directory.[The Application Client will get started].

//6.Select Tools-->Themes menu-item and apply any themes to the client.

//7.Now click on the menu-item Test under File menu and start doing the testcases.


package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class ImageOnDialog extends JFrame implements ActionListener,com.adventnet.nms.util.CustomClassInterface
{

	JButton warn = new JButton("Warning");
	JButton info = new JButton("Information");
	JButton question = new JButton("Question");
	JButton error = new JButton("Error");
	JPanel panel = new JPanel();

	public ImageOnDialog()
	{
		super();
		getContentPane().setLayout(new BorderLayout());
		panel.setLayout(new FlowLayout());
		panel.add(warn);
		panel.add(info);
		panel.add(question);
		panel.add(error);

		warn.addActionListener(this);
		info.addActionListener(this);
		question.addActionListener(this);
		error.addActionListener(this);

		getContentPane().add(panel,BorderLayout.CENTER);
		setLocation(300,300);
		setSize(500,100);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent ae)
	{
		String command = ae.getActionCommand();
		
		if(command.equals("Warning"))
		{
			JOptionPane.showMessageDialog(null,"This is a warning message","Warning",JOptionPane.WARNING_MESSAGE);
		}

		else if(command.equals("Information"))
		{
			JOptionPane.showMessageDialog(null,"This is an Information message","Information",JOptionPane.INFORMATION_MESSAGE);
		}

		else if(command.equals("Question"))
		{
			JOptionPane.showMessageDialog(null,"This is a Question message","Question",JOptionPane.QUESTION_MESSAGE);
		}

		else if(command.equals("Error"))
		{
			JOptionPane.showMessageDialog(null,"This is an Error message","Error",JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void main(String a[])
	{
		new ImageOnDialog();
	}

	public void setProperties(Properties [] p)
	{
	}

}
