//$Id: ConfigClientUtils.java,v 1.1 2006/08/29 13:56:51 build Exp $

package com.adventnet.nms.config;

import java.awt.*;
import javax.swing.*;

import com.adventnet.management.config.*;

import com.adventnet.nms.util.NmsClientUtil;

public class ConfigClientUtils extends AbstractConfigClientUtils
{
	private String localePropertiesFileName = "ElementConfigurationResources";
	
	private com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	
	public ConfigClientUtils()
	{
		ConfigPanel configPanel = ConfigPanel.getInstance();
		
		if(configPanel.applet.getParameter("RESOURCE_PROPERTIES" ) != null)
		{
			localePropertiesFileName = configPanel.applet.getParameter("RESOURCE_PROPERTIES");
		}
		
		resourceBundle = com.adventnet.apiutils.Utility.getBundle(localePropertiesFileName,configPanel.applet.getParameter("RESOURCE_LOCALE"), configPanel.applet);
	}

	public ConfigClient getConfigClientInstance()
	{
		ConfigClient configClient = null;

		try{
			configClient = ConfigClient.getInstance("com.adventnet.nms.config.NmsConfigClient", null, null, NmsClientUtil.getUserName(), null);
		}catch(Exception e)
		{
			System.out.println(getString("exception occured during configclient instance creation"));
		}

		return configClient;
	}

	public String getString(String str)
	{
		if(resourceBundle != null)
		{
			return resourceBundle.getString(str);
		}
		else
		{
			return str;
		}
	}

	public ImageIcon getImage(String image)
	{
		return NmsClientUtil.getImageIcon(image);
	}

	public void centerWindow(Component comp)
	{
		NmsClientUtil.centerWindow(comp);
	}

	public void showHelp(String key)
	{
		NmsClientUtil.showHelpBasedOnKey(key);
	}

	public Font getFont(String fontType)
	{
		return NmsClientUtil.getFont(fontType);
	}

	public void setBusyCursor(Component component)
	{
		if(component != null)
		{
			component = SwingUtilities.getRoot(component);
			setCursorForAllComponents(component, NmsClientUtil.wait_cursor);
		}
	}

	public void setNormalCursor(Component component)
	{
		if(component != null)
		{
			component = SwingUtilities.getRoot(component);
			setCursorForAllComponents(component, NmsClientUtil.normal_cursor);
		}
	}

	private void setCursorForAllComponents(Component component, Cursor cursor)
	{
		if(component instanceof Container)
		{
			Container container = (Container)component;
			Component[] components = container.getComponents();

			if(components != null && components.length > 0)
			{
				for(int i=0; i<components.length; i++)
				{
					components[i].setCursor(cursor);
					setCursorForAllComponents(components[i], cursor);
				}
			}
		}
	}

	public String formatDate(long date)
	{
		return NmsClientUtil.formatDate(date);
	}

}




