//$Id: ConfigPanel.java,v 1.1 2006/08/29 13:56:51 build Exp $

package com.adventnet.nms.config;

import java.util.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;
import java.lang.reflect.*;
import com.adventnet.nms.startclient.AbstractBaseNmsPanel;

public class ConfigPanel extends AbstractBaseNmsPanel
{
	public JApplet applet = null;

	private String panelKey = "ConfigPanel";

	private String configClientUtilsClassName = null;
	private String responseHandlerClassName = null;	
	
	//public Vector protocols = new Vector();	

	protected String userName = null;

	protected Hashtable configNodeProperties = null;

	protected ConfigMainUI configMainUI = null;

	protected MibHandler mibHandler = null;
	
	protected AbstractConfigClientUtils configClientUtils = null;
	protected ResponseHandler configResponseHandler = null;
	
	protected boolean isStandAlone = false;

	private static ConfigPanel configPanel = null;

	private String helpKey = "Batch_Conf_Main_Help";

	public ConfigPanel()
	{
		this("com.adventnet.nms.config.ConfigClientUtils", "com.adventnet.nms.config.ConfigResponseHandler");
	}
	
	public ConfigPanel(String configClientUtilsClassName)
	{
		this(configClientUtilsClassName, "com.adventnet.nms.config.ConfigResponseHandler");
	}
	
	public ConfigPanel(String configClientUtilsClassName, String responseHandlerClassName)
	{
		this.configClientUtilsClassName = configClientUtilsClassName;
		this.responseHandlerClassName = responseHandlerClassName;

		configPanel = this;
	}

	public ConfigMainUI getConfigMainUI()
	{
		return configMainUI;
	}

	public void setStandAloneClient(boolean isStandAlone)
	{
		this.isStandAlone = isStandAlone;
	}

	public void init(JApplet applet)
	{
		this.applet = applet;

		if(!initializeConfigClient())
		{
			System.out.println("config client initialization failed"); 
		}	
	}

	public String key()
	{
		return panelKey;
	}

	public void setProperties(Properties properties)
	{
		if(configNodeProperties == null)
		{
			configNodeProperties = getCurrentNodeProperties();
			//initializeProtocols();
		}
	}

	public void actionPerformed(ActionEvent actionEvent)
	{
		String actionCommand = actionEvent.getActionCommand().trim();

		/*if(actionCommand.equals("Mainhelp"))
		  {
		  configClientUtils.showHelp(helpKey); //to avoid opening of help twice, due to default tool bar actions are handled by the client framework itself.
		  }*/

		if(configMainUI != null)
		{
			configMainUI.menuActionPerformed(actionCommand);
		}
	}

	private boolean initializeConfigClient()
	{
		try{
			configClientUtils = (AbstractConfigClientUtils)Class.forName(configClientUtilsClassName).newInstance();

			configResponseHandler = (ResponseHandler) Class.forName(responseHandlerClassName).newInstance();

			configResponseHandler.init(this);

			if(!isStandAlone || (applet.getParameter("CONFIG_PROTOCOL")).equalsIgnoreCase("snmp"))
				mibHandler = new MibHandler(this);

			configMainUI = new ConfigMainUI(this);
			
			add(configMainUI);

			return true;
		}catch(Exception e)
		{
			e.printStackTrace();
			
			return false;
		}
	}

	/*private void initializeProtocols()
	{
		if(configNodeProperties != null)
		{
			String protocolsValue = (String) configNodeProperties.get("PROTOCOLS");

			if(protocolsValue != null)
			{
				StringTokenizer tokenizer = new StringTokenizer(protocolsValue," ");

				while(tokenizer.hasMoreTokens())	
				{
					protocols.addElement(tokenizer.nextToken());
				}
			}
		}
	}*/

	public boolean isConfigPanelVisible()
	{
		boolean isVisible = false;

		Container container = getParent();

		while(true)
		{
			if(container instanceof JInternalFrame)
			{
				isVisible = ((JInternalFrame)container).isSelected();

				break;
			}
			else if(container instanceof JFrame)
			{
				if(((JFrame)container).getState() == Frame.NORMAL)
				{
					isVisible = true;
				}

				break;
			}		

			container = container.getParent();
		}

		return isVisible;
	}

	public static ConfigPanel getInstance()
	{
		return configPanel;
	}
}









