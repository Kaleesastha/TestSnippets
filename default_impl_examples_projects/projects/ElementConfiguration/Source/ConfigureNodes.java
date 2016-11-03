//$Id: ConfigureNodes.java,v 1.1 2006/08/29 13:56:51 build Exp $
package com.adventnet.nms.config;

import java.util.*;

import com.adventnet.nms.util.*;

public class ConfigureNodes implements CustomClassInterface
{
	public ConfigureNodes()
	{
	}
	
	public void setProperties(Properties[] properties)
	{
		if(properties != null && properties.length > 0)		
		{
			Vector devices = new Vector();
			
			for(int i=0; i<properties.length; i++)
			{
				Properties prop = properties[i];

				if(prop != null)
				{
					String name = (String)prop.get("HOST");
					String type = (String)prop.get("TYPE");

					if(!type.equalsIgnoreCase("Interface") && !type.equalsIgnoreCase("Network") && !type.equalsIgnoreCase("Port"))
					{
						devices.addElement(name);
					}
					
					//System.out.println("Device Name :"+name);
					//System.out.println("Device Type :"+type);
				}
			}

			if(devices.size() > 0)
			{
				ConfigureDevices configureDevices = new ConfigureDevices(ConfigPanel.getInstance(), devices);
				configureDevices.setVisible(true);
			}
		}
	}
}
