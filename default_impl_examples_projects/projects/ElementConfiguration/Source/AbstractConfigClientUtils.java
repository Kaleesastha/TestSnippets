//$Id: AbstractConfigClientUtils.java,v 1.1 2006/08/29 13:56:51 build Exp $

package com.adventnet.nms.config;

import java.awt.*;
import javax.swing.*;

import com.adventnet.management.config.*;

public abstract class AbstractConfigClientUtils
{
	public AbstractConfigClientUtils()
	{
	}

	public abstract ConfigClient getConfigClientInstance();

	public abstract void showHelp(String key);

	public abstract String getString(String str);

	public abstract Font getFont(String fontType);

	public abstract ImageIcon getImage(String image);

	public abstract void centerWindow(Component comp);

	public abstract void setBusyCursor(Component component);

	public abstract void setNormalCursor(Component component);

	public abstract String formatDate(long date);

	public String formatDate(String date)
	{
		try
		{
			return formatDate(Long.parseLong(date));
		}
		catch(NumberFormatException nfe)
		{
			return null;
		}
	}

	public JFrame getParentFrame(Component component)
	{
		if (component instanceof JFrame)
			return ((JFrame) component);

		while (true)
		{
			if (component == null)
				return new JFrame ();

			component = component.getParent ();

			if (component instanceof JFrame)
				break;
		}

		return ((JFrame) component);
	}

	public JDialog getParentDialog(Component component)
	{
		if (component instanceof JDialog)
			return ((JDialog) component);

		while (true)
		{
			if (component == null)
				return new JDialog ();

			component = component.getParent ();

			if (component instanceof JDialog)
				break;
		}

		return ((JDialog) component);

	}

	public void showErrorDialog(Component parent, String message, String title, String messageType)
	{
		if(messageType.equalsIgnoreCase("error"))
		{
			JOptionPane.showMessageDialog(parent,message,title,JOptionPane.ERROR_MESSAGE);
		}
		else if(messageType.equalsIgnoreCase("info"))
		{
			JOptionPane.showMessageDialog(parent,message,title,JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public boolean getConfirmation(Component component)
	{
		boolean confirmation = false;

		if(JOptionPane.showConfirmDialog(component, ConfigPanel.getInstance().configClientUtils.getString("Are you sure about deleting the selected item(s)?"), ConfigPanel.getInstance().configClientUtils.getString("Confirmation Message"), JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
		{
			confirmation = true;
		}

		return confirmation;
	}
}






