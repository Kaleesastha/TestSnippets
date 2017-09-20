//$Id: DynamicUpdateServlet.java,v 1.1.8.5 2013/08/02 12:37:14 shajahan Exp $
package com.adventnet.nms.webclient.common;

import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.directwebremoting.ScriptSession;
import org.directwebremoting.ServerContextFactory;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.dwrp.Batch;
import org.directwebremoting.event.ScriptSessionEvent;
import org.directwebremoting.event.ScriptSessionListener;
import org.directwebremoting.extend.FormField;
import org.directwebremoting.extend.ScriptSessionManager;

import com.adventnet.management.scheduler.Scheduler;
import com.adventnet.nms.alertdb.WebACCListener;
import com.adventnet.nms.db.util.DBXmlUtility;
import com.adventnet.nms.fe.map.WebMapListener;
import com.adventnet.nms.startnms.AcClient;
import com.adventnet.nms.startnms.MapUpdateClient;
import com.adventnet.nms.startnms.SessionUpdateClient;
import com.adventnet.nms.topodb.MoDetailsUpdateHandler;
import com.adventnet.nms.topodb.TreeTopoListener;
import com.adventnet.nms.util.PureServerUtilsFE;
import com.adventnet.nms.webclient.common.WebClientUtil;

public class DynamicUpdateServlet extends HttpServlet implements ScriptSessionListener 
{

	private SessionUpdateClient sessionUpdateClient;
	public static AcClient acClient = null;
	private DBXmlUtility dbUtility = null;
	private ScriptSessionManager ssManager = null;
	public static MoDetailsUpdateHandler handler=null;
	private static MapUpdateClient mapUClient = null;
//	public static AcClient acClient = null;

	
	public void init(ServletConfig config) throws ServletException
	{
		ssManager = ServerContextFactory.get().getContainer().getBean(ScriptSessionManager.class);
		ssManager.addScriptSessionListener(this);

		while(!PureServerUtilsFE.initialized)
		{
			try 
			{
				Thread.sleep(2000);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}

		sessionUpdateClient = (SessionUpdateClient)PureServerUtilsFE.clientSocketFE.getUpdateClient(SessionUpdateClient.updateID);
		mapUClient = (MapUpdateClient)PureServerUtilsFE.clientSocketFE.getUpdateClient(MapUpdateClient.updateID);
		acClient = new AcClient(PureServerUtilsFE.clientSocketFE);
		dbUtility = DBXmlUtility.getInstance(null);
		
        Scheduler sch = Scheduler.getScheduler("WebUpdateProcessor"); //No Internationalization
        if (sch == null)
        {
            sch = Scheduler.createScheduler("WebUpdateProcessor", 10); //No Internationalization
            sch.start();
        }
	}

	public void sessionCreated(ScriptSessionEvent event) 
	{
        PureServerUtilsFE.clientSocketFE.setHasRMIClients(true);
        
		HttpServletRequest request = WebContextFactory.get().getHttpServletRequest();
		
		ScriptSession ss = event.getSession();
		String ssId = ss.getId();
		request.setAttribute("DWR_SSID", ssId);

		String userName = (String) request.getSession().getAttribute(WebClientUtil.USER_KEY);		
		ss.setAttribute(WebClientUtil.USER_KEY, userName);
		
		Batch batch = (Batch) request.getAttribute("org.directwebremoting.dwrp.batch");
		if(batch!=null)
		{
			FormField formField = batch.getExtraParameters().get("a-WebCVKey"); 
			if(formField != null)
			{
				String cvKey = formField.getString();
				if(cvKey.startsWith("WebCVUser"))
				{
					WebCVUser cvUser = (WebCVUser) request.getSession().getAttribute(cvKey);
					if(cvUser != null){
					cvUser.setScriptSessionId(ssId);
					sessionUpdateClient.registerHandler(cvUser.getController(),cvUser.getUpdateHandler());
					ss.setAttribute("WebUpdateHandler", cvUser);
					}
					ss.setAttribute("HOME_CV_KEY", cvKey);
					request.getSession().removeAttribute(cvKey);
				}
				else if(cvKey.startsWith("WebMapListener"))
				{
					WebMapListener mapListener = (WebMapListener) request.getSession().getAttribute(cvKey);
					mapListener.setScriptSessionId(ssId);
					request.getSession().removeAttribute(cvKey);
					mapUClient.registerWebMapListener(mapListener);
					ss.setAttribute("WebMapListener", mapListener);
				}
			}
		}
		
		WebTreeListener treeListener = new WebTreeListener(userName, ssId);				
		dbUtility.registerForUpdates(userName, treeListener);
		ss.setAttribute("WebTreeListener", treeListener);
		
		String page = ss.getPage();
		if(page.equals("/topo/objectdetails.do"))
		{
			for(Enumeration en = request.getSession().getAttributeNames();en.hasMoreElements();)
			{
				String name = (String) en.nextElement();
				if(name.startsWith("details"))
				{
					String moname = (String) request.getSession().getAttribute(name);
					request.getSession().removeAttribute(name);
					ss.setAttribute("ScriptSessionID",ss.getId());
					ss.setAttribute("MoName",moname);

					if(handler != null)
					TreeTopoListener.registerUpdateHandler(handler);
				}
				
			}
			
		}
	}

	
	public void sessionDestroyed(ScriptSessionEvent event) 
	{
		ScriptSession ss = event.getSession();
		String ssId = ss.getId();
	
		Object obj =  ss.getAttribute("WebUpdateHandler"); 
		if(obj != null && obj instanceof WebCVUser)
		{
			WebCVUser cvUser = (WebCVUser) obj;
			sessionUpdateClient.deRegisterHandler(cvUser.getController(),cvUser.getUpdateHandler());
			cvUser.stopUpdate();
			cvUser = null;
		}
		else if(obj != null && obj instanceof ArrayList){
			ArrayList<WebCVUser> cvUsers = (ArrayList<WebCVUser>)obj;
			for(WebCVUser cvUser:cvUsers){
				sessionUpdateClient.deRegisterHandler(cvUser.getController(),cvUser.getUpdateHandler());
				cvUser.stopUpdate();
				cvUser = null;
			}
		}

		WebACCListener listener = (WebACCListener) ss.getAttribute("WebACCListener");
		if(listener != null)
		{
			acClient.deRegisterForACCUpdate(listener);
		}
		
		WebTreeListener treeListener = (WebTreeListener) ss.getAttribute("WebTreeListener");
		if(treeListener != null)
		{
			dbUtility.deRegisterForUpdates(treeListener.getUserName(), treeListener);
		}
		
		WebMapListener mapListener = (WebMapListener) ss.getAttribute("WebMapListener");
		if(mapListener != null)
		{
			mapUClient.deRegisterMapListener(mapListener);
		}
		if(ssManager.getAllScriptSessions().isEmpty())
		{
			PureServerUtilsFE.clientSocketFE.setHasRMIClients(false);
		}
	}	
}
