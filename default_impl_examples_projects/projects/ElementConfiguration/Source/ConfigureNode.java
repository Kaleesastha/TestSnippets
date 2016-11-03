//$Id: ConfigureNode.java,v 1.1 2006/08/29 13:56:51 build Exp $
package com.adventnet.nms.config;

import java.util.*;

import javax.swing.*;

import com.adventnet.nms.startclient.*;

public class ConfigureNode implements NmsFrame
{
	private ConfigureDevices configureDevices = null;
	
	public ConfigureNode()
	{
	}

	public void init(JApplet applet)
	{
		String name = applet.getParameter("HOST");
		String type = applet.getParameter("TYPE");
		
		//System.out.println("Host name :"+name);
		//System.out.println("type :"+type);
		
		if(!type.equalsIgnoreCase("Interface") && !type.equalsIgnoreCase("Network") && !type.equalsIgnoreCase("Port"))
		{
			Vector devices = new Vector();

			devices.addElement(name);

			configureDevices = new ConfigureDevices(ConfigPanel.getInstance(), devices);
		}
		//else
		//{
		//	System.out.println("Please select a Node");
		//}
	}

	public void setVisible(boolean isVisible)
	{
		if(configureDevices != null)
		{
			configureDevices.setVisible(true);
		}
	}
}
