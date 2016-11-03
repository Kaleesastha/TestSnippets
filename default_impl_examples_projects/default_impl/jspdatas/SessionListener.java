// $Id: SessionListener.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $

package com.adventnet.nms.jsp;

import java.util.Properties;

import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionBindingEvent;

import com.adventnet.nms.util.NmsLogMgr;
import com.adventnet.nms.jsp.JspUtility;
import com.adventnet.management.log.Log;
import com.adventnet.nms.commonfe.GenericFEAPIImpl;

public class SessionListener implements HttpSessionBindingListener
{
	Properties props = null;

	public SessionListener(Properties props)
	{
		this.props = props;
	}

	public void valueBound(HttpSessionBindingEvent e)
	{
		// we don't do anything here now.
	}

	public void valueUnbound(HttpSessionBindingEvent e)
	{
		String userName =  props.getProperty("userName");
		String hostname =  props.getProperty("hostname");

		// Removing the user from the active user list.
		GenericFEAPIImpl api = 	(GenericFEAPIImpl) GenericFEAPIImpl.getAPI();
		if(api != null && userName != null)
		{
			api.removeActiveUser(userName);
		}

		NmsLogMgr.MISCUSER.log("Session for '"+ userName +"' logged in from "+ hostname + " has expired at" + JspUtility.gettime(), Log.SUMMARY);

	}
}
