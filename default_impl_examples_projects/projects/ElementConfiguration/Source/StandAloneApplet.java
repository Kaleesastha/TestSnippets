package com.adventnet.nms.config;

import javax.swing.*;
import java.net.*;
import java.util.*;



public class StandAloneApplet extends JApplet
{
	URL docBase = null;
	
	URL codeBase = null;

	Properties appletProperties = new Properties();
	
	public URL getDocumentBase()
	{
		return docBase;
	}

	public void setCodeBase(URL codeBase)
	{
		this.codeBase = codeBase;
	}

	public URL getCodeBase()
	{
		return codeBase;
	}
	public void setDocumentBase(URL docBase)
	{
		this.docBase = docBase;
	}

	public void setParameter(String key, String value)
	{
		appletProperties.put(key, value);
	}

	public String getParameter(String key)
	{
		return (String) appletProperties.get(key);
	}

	public void setAppletProperties(Properties props)
	{
		appletProperties = props;
	}
}
