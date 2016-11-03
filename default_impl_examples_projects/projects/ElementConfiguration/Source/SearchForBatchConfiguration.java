//$Id: SearchForBatchConfiguration.java,v 1.1 2006/08/29 13:56:51 build Exp $
package com.adventnet.nms.config;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

import com.adventnet.nms.util.DeviceQuery;
import com.adventnet.nms.config.ConfigPanel;
import com.adventnet.management.config.*;


public class SearchForBatchConfiguration extends DeviceQuery implements ConfigResponseListener
{
    private ConfigPanel configPanel = null;
    private ConfigResponseListener configListener = null;
    private static Hashtable listenerVsSearchWindow = new Hashtable();
	
    public SearchForBatchConfiguration()
    {        
    }
    
	public SearchForBatchConfiguration(ConfigPanel configPanel, JApplet applet, ConfigResponseListener listener)
    {
        super(configPanel.configClientUtils.getParentFrame(configPanel), configPanel.configClientUtils.getString("Search For Devices"), applet, true);
				
		this.configPanel = configPanel;
        configListener = listener;

		configPanel.configResponseHandler.sendRequest(NmsConfigConstants.GET_ALL_NETWORKS, null, this);
        searchImage = configPanel.configClientUtils.getImage(applet.getDocumentBase() + "../images/find.png");

        headingVector.addElement("Name");
        headingVector.addElement("Managed");
        headingVector.addElement("Type");
        headingVector.addElement("Snmp");
        headingVector.addElement("IpAddress");
        headingVector.addElement("Status");
        headingVector.addElement("InUse");
        headingVector.addElement("Router");
		
        headerNames.addElement("name");
        headerNames.addElement("managed");
        headerNames.addElement("type");
        headerNames.addElement("isSNMP");
        headerNames.addElement("ipAddress");
        headerNames.addElement("stringstatus");
        headerNames.addElement("inUse");
        headerNames.addElement("isRouter");

        typeVector.addElement("String");
        typeVector.addElement("String");
        typeVector.addElement("String");
        typeVector.addElement("String");
        typeVector.addElement("String");
        typeVector.addElement("String");
        typeVector.addElement("String");
        typeVector.addElement("String");

		addWindowListener(new WindowAdapter()
			{ 
				public void windowClosing(WindowEvent e)
				{ 
					dispose();
				}
				public void windowClosed(WindowEvent we)
				{
					listenerVsSearchWindow.remove(configListener);
				}				
			});
    }
	
	public static void showSearchWindow(ConfigPanel configPanel, JApplet applet, ConfigResponseListener listener)
	{
		SearchForBatchConfiguration searchWindow = (SearchForBatchConfiguration)listenerVsSearchWindow.get(listener);
		if(  searchWindow == null )
		{
			searchWindow = new SearchForBatchConfiguration(configPanel, applet, listener);
			listenerVsSearchWindow.put(listener, searchWindow);
		}
		else
		{
			searchWindow.requestFocus();
		}
	}

    public void submitSearch(Properties criteriaProperty, String matchAnyOrAll)
    {
		criteriaProperty.remove("Arg");
		Object [] params = {criteriaProperty};
        configPanel.configResponseHandler.sendRequest(NmsConfigConstants.GET_MO_WITH_CRITERIA, params, configListener);
    }

    public String getString(String key)
    {
        return configPanel.configClientUtils.getString(key);
    }

    public void response(ConfigResultEvent cre)
    {
		int workId = cre.getWorkID();
        int errorCode = cre.getErrorCode();
        String uniqueId = cre.getRequestID();
        if(workId == NmsConfigConstants.GET_ALL_NETWORKS)
        {
            Vector networkDetails = cre.getAllNetworks();
			networkDetails.addElement("All");
            networkVector = networkDetails;
			this.setVisible(true);
        }
    }
}
