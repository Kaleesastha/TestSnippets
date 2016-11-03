//$Id: StandAloneClientUtils.java,v 1.1 2006/08/29 13:56:51 build Exp $

package com.adventnet.nms.config;

import java.awt.*;
import javax.swing.*;

import com.adventnet.management.config.*;

public class StandAloneClientUtils extends AbstractConfigClientUtils
{
	private String localePropertiesFileName = "ElementConfigurationResources";
	
	private com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	
	public StandAloneClientUtils()
	{
		//Empty Constructor
	}

	public ConfigClient getConfigClientInstance()
	{
		return null;
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
		return null;
	}

	public void centerWindow(Component comp)
	{
		
	}

	public void showHelp(String key)
	{
		
	}

	public Font getFont(String fontType)
	{
		return null;
	}

	public void setBusyCursor(Component component)
	{
		
	}

	public void setNormalCursor(Component component)
	{
		
	}

	private void setCursorForAllComponents(Component component, Cursor cursor)
	{
		
	}

	public String formatDate(long date)
	{
		return null;
	}

}
