//$Id: DetailsFetcherAction.java,v 1.6.4.17 2013/08/07 05:29:33 nishanthini.k Exp $

package com.adventnet.nms.webclient.topo.details;

// Imports - 1 :: Java Imports
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Properties;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Vector;
import java.util.Hashtable;
import java.util.HashMap;
import java.util.Enumeration;
import java.util.Date;
import java.awt.Color;
import java.io.File;
import java.rmi.RemoteException;

// Imports - 2 :: Servlet Imports
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import java.io.PrintWriter;

// Imports - 3 :: Struts Imports
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.validator.DynaValidatorForm;
import org.jfree.data.general.DefaultPieDataset;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.omg.CORBA.Request;

// Imports - 4 :: AdventNet Web NMS Imports

import com.adventnet.nms.topodb.ManagedObject;
import com.adventnet.nms.topodb.SnmpInterface;
import com.adventnet.nms.topodb.TopoAPI;
import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.util.NmsLogMgr;
import com.adventnet.nms.util.PureServerUtilsFE;
import com.adventnet.nms.startnms.SessionUpdateClient;
import com.adventnet.nms.store.relational.RelationalUtil;
import com.adventnet.nms.poll.MultiplePolledData;
import com.adventnet.nms.poll.PollAPI;
import com.adventnet.nms.poll.CollectedData;
import com.adventnet.nms.poll.PolledData;
import com.adventnet.nms.util.PureUtils;
import com.adventnet.management.log.Log;
import com.adventnet.nms.util.NmsClientUtil;
import com.adventnet.nms.fe.common.CustomViewException;
import com.adventnet.nms.severity.SeverityInfo;
import com.adventnet.nms.poll.NmsPollException;
import com.adventnet.management.transaction.UserTransactionException;
import com.adventnet.nms.webclient.common.DynamicUpdateServlet;
import com.adventnet.nms.webclient.fault.alarm.AlarmViewUtil;
import com.adventnet.nms.webclient.topo.details.MODetailsXMLParser;
import com.adventnet.nms.webclient.performance.poll.IfcUtilizationCalculator;
import com.adventnet.nms.webclient.performance.reports.GraphInfoBean;
import com.adventnet.nms.webclient.performance.reports.GraphInfoReader;
import com.adventnet.nms.webclient.performance.reports.ReportUtil;
import com.adventnet.nms.webclient.report.FaultReportsUtility;
import com.adventnet.nms.fe.alert.AlertFE;
import com.adventnet.nms.fe.alert.AlertSessionBean;

// Imports - 5 :: AdventNet Web NMS - Web Client Imports
import com.adventnet.nms.webclient.topo.details.ObjectDetailsHandler;
import com.adventnet.nms.webclient.topo.util.TopoWebClientUtility;
//import com.adventnet.nms.webclient.topo.MOproperty.QueryRequestIDs;
import com.adventnet.nms.webclient.topo.MOproperty.MODetailsHandler;
import com.adventnet.nms.webclient.common.WebCVUser;
import com.adventnet.nms.webclient.common.WebClientUtil;
import com.adventnet.nms.webclient.common.StatusIconConfigurationPopulator;
import com.adventnet.nms.webclient.fault.alarm.AlarmUpdateHandler;
import com.adventnet.nms.webclient.fault.alarm.AlarmViewUtil;
import com.adventnet.nms.webclient.topo.details.MODetailsXMLParser;
//import com.adventnet.nms.webclient.fault.alarm.AlarmViewUtil;
//import com.adventnet.nms.fe.common.*;
//import com.adventnet.nms.alertdb.Alert;
import com.adventnet.nms.alertdb.AlertAPI;
import com.adventnet.nms.alertdb.Alert;
//import com.adventnet.nms.fe.alert.AlertFE;
import com.adventnet.nms.fe.common.ViewData;
//import com.adventnet.nms.fe.common.ViewCriteria;
//import com.adventnet.nms.fe.common.CustomViewProperties;
import com.adventnet.nms.fe.common.TableColumn;
import com.adventnet.nms.fe.common.ModuleNotInitializedException;
import com.adventnet.nms.util.GenericUtility;
/**
 * <p align="justify">The <i>DetailsFetcherAction</i> class fetches the details that needs
 * to be displayed in the details sheet from the Topology Database. The <i>DetailsFetcherAction</i>
 * is a dispatcher between the user request to invoke the object details and type
 * of action that can display the object details for that particular type of
 * Object. This dispatcher will select an appropriate Action for each details page invocation,
 * based on the <strong>classname</strong> property of the ManagedObject.
 * <p>This Action class fetches the business data that needs to be displayed in the
 * details page from the ObjectDetailsHandler. User who need to display customized
 * data can extend this Action class and customize the business data attribute,
 * before setting the data in the request.</p>
 * <p>This means you should design with the following items in mind, while
 * customizing the business data to display it in the details sheet:</p>
 * <ul>
 *   <li>All the attributes that needs to displayed in the UI <b>MUST</b> have an
 *     entry for the property in the <strong>topo-struts-config.xml</strong> under
 *     the form-bean definition, that matches the classname property of the
 *     ManagedObject.
 *   <li>A matching ForwardConfig entry (Global Forward) for each distinct
 *     classname property of the ManagedObject should be configured in the
 *     topo-struts-config.xml.</li>
 *   <li>Users who wants to customize the Business Data (add / modify) can extend
 *     this DetailsFetcherAction class and override the <i>getBusinessData</i>()
 *     and <i>customizeDisplayProperties</i>() methods.
 *   <li>Please take care of calling the <i>getBusinessData</i>() and <i>customizeDisplayProperties</i>()
 *     methods of the super class.
 *   <li>As the <strong>classname</strong> is used for dispatching the control to
 *     appropriate Action classes ( EX : ManagedObject : ManagedObjectAction ),user
 *     should <b>NOT</b> remove the classname property from the business data</li>
 * </ul>
 *
 * @see     org.apache.struts.actions.DispatchAction
 * @see     com.adventnet.nms.webclient.topo.details.ObjectDetailsAction
 * @see     com.adventnet.nms.webclient.topo.details.DetailsHandlingAction
 *
 * @author R. Balaji
 * @version $Id: DetailsFetcherAction.java,v 1.6.4.17 2013/08/07 05:29:33 nishanthini.k Exp $ 
 * @since Web NMS 4.5
 **/
public class DetailsFetcherAction extends DispatchAction {

	/**
	 * <p>Handles the client request to display the details sheet, and fetches the 
	 * details from the TopoSessionBean. This method will return the control back to
	 * the apropriate action class which can handle the properties in the details
	 * sheet.</p>
	 *
	 * @param mapping The ActionMapping used to select this instance
	 * @param form ObjectDetailsForm which comes with the details that needs to be displayed.
	 * @param request The non-HTTP request we are processing
	 * @param response The non-HTTP response we are creating
	 *
	 * @exception Exception the execption thrown by the application business logic.
	 *
	 * @author R. Balaji
	 * @since Web NMS 4.5
	 **/
	public ActionForward unspecified(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
	throws Exception {

		String userName = (String)request.getSession().getAttribute(WebClientUtil.USER_KEY);
		 request.setAttribute("formopage",true);
		// Fetch the name of the ManagedObject from the request.
		int GET_SNAPSHOT_DATA = 216;
		int GET_AGENT_DATA_COLLECTED = 102;
		boolean drawGraph = true;
		String nameOfTheMO=(String)request.getParameter("name"); //No Internationalization

		String searchName = (String)request.getAttribute("searchName"); //No Internationalization
        request.setAttribute("ForPerfMonitors","false"); //No Internationalization
        request.setAttribute("ForInterfaceTable","false"); //No Internationalization
        request.setAttribute("ForMOGrahps","false"); //No Internationalization
        
        /* Added for map actions */
        String mapName = request.getParameter("mapName");//No Internationalization
        if(mapName == null || mapName.equals("null") || mapName.trim().equals("")) 
        {
            request.setAttribute("MapActions","false");//No Internationalization
            
        }
        else
        {
        	request.setAttribute("MapActions","true");//No Internationalization
        	
        }
        /* Added for map actions */
        
		if(searchName != null)
		{
			nameOfTheMO = searchName;
			request.setAttribute("name" ,searchName);  //No Internationalization
		}
		String entity = (String)request.getParameter("entity"); //No I18N

		if (entity != null)
		{
			Properties alertprop = new Properties();//No I18N
			alertprop.setProperty("entity",entity);//No I18N
			AlertAPI alertAPI=(AlertAPI)NmsUtil.getAPI("AlertAPI");//No I18N
			Vector alertvec = alertAPI.getObjects(null,alertprop);//NO I18N
			Alert alert = (Alert)alertvec.elementAt(0);//No I18N
			nameOfTheMO = alert.getSource();//No I18N
			request.setAttribute("name",nameOfTheMO);// No I18N
			request.setAttribute("MOName",nameOfTheMO);//No I18N
                       
		}
                else
                {
                      request.setAttribute("MOName",nameOfTheMO);//No I18N
                      request.setAttribute("name",nameOfTheMO);// No I18N
                }

		String req = request.getParameter("requestid"); //No Internationalization
		String category = request.getParameter("category"); //No Internationalization
		
		request.setAttribute("CategoryForMO",category);//No I18N

		Properties deviceData = null;
		Vector graphsList = null;
		Properties businessData = null;
		Properties alarmData = null;
		Vector intfData = null;
		Hashtable servData = null;

		// Let us initialize the size with two ( as the default is 10, and we will not have so many messages)
		ArrayList messages = new ArrayList(2);
		String className = null;

		// we are sending the html <MAP> tag, so we need to set the proper content-type
		HttpSession session = request.getSession();
		ServletContext application = session.getServletContext();
		response.setContentType("text/html;charset=UTF-8"); //No Internationalization
		PrintWriter out = response.getWriter();

		if( req ==null || req.equals("UPDATESTATUS") || req.equals("SERVICE")) //No Internationalization
		{
			req = (String) request.getAttribute("requestid"); //No Internationalization
		}

		// This is the classname property of the super class , for which the system can find
		// details page configured.
		ActionForward af = null;

		if( nameOfTheMO ==null)
		{
			messages.add(" Unable to find the NAME of the object from the request");
			NmsLogMgr.TOPOERR.fail(NmsUtil.GetString("Unable to find the name of the MO, for displaying the Details Sheet"),null);

		}
		else
		{         
			// Get the Business Data that needs to be displayed in the ObjectDetails sheet.
			try
			{
				if(req != null && req.equals("GET_IF_DETAILS") )
				{
					String instance = ""+request.getParameter("Instance"); //No Internationalization
					
					businessData = getIntfDetails(nameOfTheMO,userName,instance);
					if(businessData != null) {
						request.setAttribute("intfName",businessData.getProperty("name")); //No Internationalization
						nameOfTheMO = businessData.getProperty("name");
						request.setAttribute("MOName",nameOfTheMO);
						request.setAttribute("name",nameOfTheMO);// No I18N
					}
				}
				else
				{
					businessData = getBusinessData(nameOfTheMO,userName);
				}
				if(businessData!=null)
				{
					request.setAttribute("discoveryStatus", businessData.get("discoveryStatus"));
					SimpleDateFormat dateFormat = (SimpleDateFormat)WebClientUtil.getDateFormat(userName);
					if(businessData.containsKey("statusUpdateTime"))
					{
						String time = (String) businessData.get("statusUpdateTime");
						Date d = new Date(Long.parseLong(time));
						businessData.put("statusUpdateTime", dateFormat.format(d));
					}
					if(businessData.containsKey("statusChangeTime"))
					{
						String time1 = (String) businessData.get("statusChangeTime");
						Date d1 = new Date(Long.parseLong(time1));
						businessData.put("statusChangeTime", dateFormat.format(d1));
					}
					className = businessData.getProperty("classname");
					// compare this data with XML and accordingly display
					Properties businessData1 = (Properties) businessData
							.clone();
					MODetailsXMLParser parser = null;
					try {
						JSONObject json = parseXMLandFetch(
								businessData1,
								request.getServerName(),
								new Integer(request.getServerPort()).toString(),
								request.getProtocol(), parser);
						request.setAttribute("modetails", json);
					} catch (JSONException jexp) {
						System.err
						.println("JSON Exception while parsing xml file"
								+ parser.getXmlFile()
								+ " "
								+ jexp.getMessage());
						messages.add("JSON Exception while parsing xml file"
								+ parser.getXmlFile()
								+ "Refer server side logs for further details");
						messages.addAll(parser.getMessages());
					}
				}
				request.setAttribute("hasPd",checkForPd(nameOfTheMO));
				if(businessData !=null)
				{                   
					// Customize the business data as display properties.
					//businessData=customizeBusinessData(nameOfTheMO,userName, businessData);
                    if(className==null)
                    {
                    	className=businessData.getProperty("classname");
                    }
					request.setAttribute("ClassNameForMO",className); //No Internationalization

					if(className == null)
					{
						messages.add("Unable to classify the type of the ManagedObject : "+nameOfTheMO);   //No Internationalization
						NmsLogMgr.TOPOERR.fail(NmsUtil.GetString("Unable to get classname property of the MO"),null);  //No Internationalization
					}
					else
					{
						af= (ActionForward)mapping.findForward(className);
					}
					
					Random rn = new Random();
					int unique = rn.nextInt();
					String key = "details"+unique;
					request.getSession().setAttribute(key,nameOfTheMO);
					DetailsUpdateHandler handler = DetailsUpdateHandler.getInstance();
					DynamicUpdateServlet.handler=handler;

					String isSnmp = businessData.getProperty("isSNMP");
					String moType = businessData.getProperty("type");
					boolean isRouterType = Boolean.parseBoolean(businessData.getProperty("isRouter"));//No I18N
					request.setAttribute("TypeForMO",moType);//No Internationalization
					request.setAttribute("SnmpCheckForMO",isSnmp);//No Internationalization
					request.setAttribute("RouterCheckForMO",isRouterType);//No Internationalization

					
				}
				else
				{
					if(nameOfTheMO.contains("Resource Monitoring")||(nameOfTheMO.contains("Monitoring")&&(nameOfTheMO.contains("Memory")||nameOfTheMO.contains("CPU")||nameOfTheMO.contains("Thread")||nameOfTheMO.contains("StatusPollingRate")||nameOfTheMO.contains("TrapRate")||nameOfTheMO.contains("AlertRate")||nameOfTheMO.contains("EventRate")||nameOfTheMO.contains("DataCollectionRate"))))
					{
						String str = java.text.MessageFormat.format(NmsUtil.GetString("webclient.topo.objectdetails.resourceMonitoringObject"),new String[]{nameOfTheMO});
						messages.add(str);
						NmsLogMgr.TOPOERR.fail(NmsUtil.GetString("Details will not be available for Resource Monitoring objects."),null);//No Internationalization

					}
					else
					{
						messages.add(NmsUtil.GetString("Unable to fetch details for the object :") +" " +nameOfTheMO);
						NmsLogMgr.TOPOERR.fail(NmsUtil.GetString("Unable to get details from the Database"),null);
					}
				}          
			}
			catch(ModuleNotInitializedException miex)
			{
				messages.add("Topology module is yet to be started, Please try again after some time : "+nameOfTheMO);
				NmsLogMgr.TOPOERR.fail(NmsUtil.GetString("Unable to get details from the Database"),miex);
			}
			catch(Exception ex)
			{
				messages.add("Error occured while fetching details for the object : "+nameOfTheMO);
				NmsLogMgr.TOPOERR.fail(NmsUtil.GetString("Unable to get details from the Database"),ex);
			}           

			// added for the display of port details page
            if( className != null && className.equals("PortObject"))
            {
//				getAlarmsforMOPage(request, response, nameOfTheMO);
            	WebCVUser cvUser = fetchRecentAlarms(request,response);

				String key = "WebCVUser"+System.currentTimeMillis();
				while(request.getSession().getAttribute(key) != null)
				{
					key = "WebCVUser"+System.currentTimeMillis();
				}
				request.setAttribute("WebCVKey", key);
				request.getSession().setAttribute(key, cvUser);
            	// code snippet for alarm summary part started
				/*Properties p = new Properties();
				p.setProperty("source",nameOfTheMO); //No Internationalization
				ViewData data = null;
				data = MODetailsHandler.getInstance().fetchAlertViewData("Alerts", "10", "0", "modTime", "false", userName, p); //No Internationalization
				Vector v = data.getData();
				
				if( v.size() != 0)
				{
					Properties a = (Properties) v.firstElement();
					String recentAlarmImg = a.getProperty("imgsrc");//No I18N
					String recentAlarmMsg = a.getProperty("message");//No I18N
					request.setAttribute("RecentAlarmImg",recentAlarmImg);//No I18N
					request.setAttribute("RecentAlarmMsg",recentAlarmMsg);//No I18N
					if(v.size() > 1)
					{
						Properties secondAlarm = (Properties)v.get(1); 
						String recentAlarmImg1 = secondAlarm.getProperty("imgsrc");//No I18N
						String recentAlarmMsg1 = secondAlarm.getProperty("message");//No I18N
						request.setAttribute("RecentAlarmImg1",recentAlarmImg1);//No I18N
						request.setAttribute("RecentAlarmMsg1",recentAlarmMsg1);//No I18N
					}
					else
					{
						request.setAttribute("RecentAlarmImg1","NODATA");//No I18N
						request.setAttribute("RecentAlarmMsg1","NODATA");//No I18N
					}
					AlertAPI api=AlertFE.getAlertAPI();
					generateAlertReportsOnCategory(v,"source",nameOfTheMO,request,response,api);//No I18N
				}
				else
				{
					request.setAttribute("alarmChart","NODATA");//No I18N
					request.setAttribute("RecentAlarmImg","NODATA");//No I18N
					request.setAttribute("RecentAlarmImg1","NODATA");//No I18N
				}*/
				// code snippet for alarm summary part ended
				
            	request.setAttribute("CpuImagePath","NODATA");//No I18N
				request.setAttribute("MemImagePath","NODATA");//No I18N
				request.setAttribute("DiskImagePath","NODATA");//No I18N
            }
            // added for the display of port details page
			if(className != null && af==null)
			{

				af= getForwardForSuperClass(mapping,className);

				if(af==null)
				{
					messages.add("Unable to find the Details page configured for this device : "+nameOfTheMO);
					NmsLogMgr.TOPOERR.fail(NmsUtil.GetString("Unable to find the details page configured for this type of Device"),null);
				}
				else
				{
					/** Important note :
					 * As we have found the ForwardConfig mapping for the nearest possible super class of 
					 * the object, we need to reuse this information in all other Action classes like
					 * 1. DetailsHandlingAction
					 * 2. ObjectDetailsAction
					 * Hence updating the classname property. We should take care of removing this property 
					 * while updating the object.
					 **/

					className = af.getName();

					businessData.setProperty("classname",className); //No Internationalization
				}
			}        
		}

		if(messages.isEmpty())
		{
			request.setAttribute("businessData",businessData); //No Internationalization
			if(req == null)
			{
				req = "SNAPSHOT"; //No Internationalization
			}
			String type = businessData.getProperty("type");
			if(req.equals("SNAPSHOT") && type.equals("Interface") ) //No Internationalization
			{

				try
				{

					String instance = ""+businessData.getProperty("ifIndex"); //No Internationalization
					
					nameOfTheMO = businessData.getProperty("parentNode");//No I18N
					
					int requestid=GET_AGENT_DATA_COLLECTED;
					response.setContentType("text/html;charset=UTF-8"); //No Internationalization
					//PrintWriter out = response.getWriter();
					session.setAttribute("moType","Interface"); 
					request.setAttribute("InstanceForMO",instance);//No Internationalization
					//deviceData = getIntfGraphDetails(nameOfTheMO,userName,requestid,instance,session,out);
					
					//session.setAttribute("IntfGraph",deviceData); //No Internationalization
					drawGraph=false;
					request.setAttribute("graphPath","NODATA");//No I18N
					// code snippet for alarm summary part started
					Properties p = new Properties();
					String mo_source = "IF-"+nameOfTheMO;//No I18N
//					getAlarmsforMOPage(request, response, mo_source);
					WebCVUser cvUser = fetchRecentAlarms(request,response);

					String key = "WebCVUser"+System.currentTimeMillis();
					while(request.getSession().getAttribute(key) != null)
					{
						key = "WebCVUser"+System.currentTimeMillis();
					}
					request.setAttribute("WebCVKey", key);
					request.getSession().setAttribute(key, cvUser);
					/*p.setProperty("source",mo_source); //No Internationalization
					ViewData data = null;
					data = MODetailsHandler.getInstance().fetchAlertViewData("Alerts", "10", "0", "modTime", "false", userName, p); //No Internationalization
					Vector v = data.getData();
					
					if( v.size() != 0)
					{
						Properties a = (Properties) v.firstElement();
						String recentAlarmImg = a.getProperty("imgsrc");//No I18N
						String recentAlarmMsg = a.getProperty("message");//No I18N
						request.setAttribute("RecentAlarmImg",recentAlarmImg);//No I18N
						request.setAttribute("RecentAlarmMsg",recentAlarmMsg);//No I18N
						if(v.size() > 1)
						{
							Properties secondAlarm = (Properties)v.get(1); 
							String recentAlarmImg1 = secondAlarm.getProperty("imgsrc");//No I18N
							String recentAlarmMsg1 = secondAlarm.getProperty("message");//No I18N
							request.setAttribute("RecentAlarmImg1",recentAlarmImg1);//No I18N
							request.setAttribute("RecentAlarmMsg1",recentAlarmMsg1);//No I18N
						}
						else
						{
							request.setAttribute("RecentAlarmImg1","NODATA");//No I18N
							request.setAttribute("RecentAlarmMsg1","NODATA");//No I18N	
						}
						AlertAPI api=AlertFE.getAlertAPI();
						generateAlertReportsOnCategory(v,"source",mo_source,request,response,api);//No I18N
					}
					else
					{
						request.setAttribute("alarmChart","NODATA");//No I18N
						request.setAttribute("RecentAlarmImg","NODATA");//No I18N
						request.setAttribute("RecentAlarmImg1","NODATA");//No I18N
					}*/
					// code snippet for alarm summary part ended
					
					
					request.setAttribute("CpuImagePath","NODATA");//No I18N
					request.setAttribute("MemImagePath","NODATA");//No I18N
					request.setAttribute("DiskImagePath","NODATA");//No I18N
					
					SeverityInfo sev = SeverityInfo.getInstance();
					String status = businessData.getProperty("status");
					String sevname = sev.getName(Integer.parseInt(status));
					String icon  = fetchImageForMO(sevname,type);
					request.setAttribute("images",icon);
					Color c=sev.getColor(Integer.parseInt(status));
					String color = "rgb("+c.getRed()+","+c.getGreen()+","+c.getBlue()+")";
					request.setAttribute("color",color);
					StatusIconConfigurationPopulator cp = StatusIconConfigurationPopulator.getInstance();
					String stat = cp.getStatusImage(status);
					request.setAttribute("statusicon",stat);
				
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			else if(req.equals("GET_IF_DETAILS") || type.equals("Port") ) //No Internationalization
			{
				try
				{
					String instance = request.getParameter("Instance"); //No Internationalization
					if(instance == null)        
					{
						instance = businessData.getProperty("portIfIndex"); //No Internationalization
						nameOfTheMO = businessData.getProperty("parentKey");
					}
					request.setAttribute("InstanceForMO",instance);//No Internationalization
					int requestid=GET_AGENT_DATA_COLLECTED;
					response.setContentType("text/html;charset=UTF-8"); //No Internationalization
					session.setAttribute("moType","Switch"); //No Internationalization
					//deviceData = getIntfGraphDetails(nameOfTheMO,userName,requestid,instance,session,out);
					//request.setAttribute("IntfGraph",deviceData); //No Internationalization
					drawGraph = false;
					SeverityInfo sev = SeverityInfo.getInstance();
					String status = businessData.getProperty("status");
					String sevname = sev.getName(Integer.parseInt(status));
					String icon  = fetchImageForMO(sevname,type);
					request.setAttribute("images",icon);
					Color c=sev.getColor(Integer.parseInt(status));
					String color = "rgb("+c.getRed()+","+c.getGreen()+","+c.getBlue()+")";
					request.setAttribute("color",color);
					StatusIconConfigurationPopulator cp = StatusIconConfigurationPopulator.getInstance();
					String stat = cp.getStatusImage(status);
					request.setAttribute("statusicon",stat);
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			else if(req.equals("SNAPSHOT")) //No Internationalization
			{

				try
				{
					int requestid= GET_SNAPSHOT_DATA;
					className=businessData.getProperty("classname");//No I18N
                    
                    
					session.setAttribute("classname",className);//No I18N
						
										
					Properties p = new Properties();
					p.setProperty("source",nameOfTheMO); //No Internationalization
					SeverityInfo sev = SeverityInfo.getInstance();
					String status = businessData.getProperty("status");
					String sevname = sev.getName(Integer.parseInt(status));
					String icon  = fetchImageForMO(sevname,type); 
					request.setAttribute("images",icon);
					Color c=sev.getColor(Integer.parseInt(status));
					String color = "rgb("+c.getRed()+","+c.getGreen()+","+c.getBlue()+")";
					request.setAttribute("color",color);
					//request.setAttribute("stringstatus",sev.getName(Integer.parseInt(status)));
					StatusIconConfigurationPopulator cp = StatusIconConfigurationPopulator.getInstance();
					String stat = cp.getStatusImage(status);
					request.setAttribute("statusicon",stat);
					if(businessData.containsKey("ipAddress"))
						request.setAttribute("IpAddress",businessData.getProperty("ipAddress"));
							// code snippet for alarm summary part started
							
							WebCVUser cvUser = fetchRecentAlarms(request,response);

							String key = "WebCVUser"+System.currentTimeMillis();
							while(request.getSession().getAttribute(key) != null)
							{
								key = "WebCVUser"+System.currentTimeMillis();
							}
							request.setAttribute("WebCVKey", key);
							request.getSession().setAttribute(key, cvUser);
			}
		
			
			catch(Exception e)
			{
				e.printStackTrace();
			}		/*(if( v.size() != 0)
					{
						Properties firstAlarm = (Properties) v.firstElement();
						String recentAlarmImg = firstAlarm.getProperty("imgsrc");//No I18N
						String recentAlarmMsg = firstAlarm.getProperty("message");//No I18N
						request.setAttribute("RecentAlarmImg",recentAlarmImg);//No I18N
						request.setAttribute("RecentAlarmMsg",recentAlarmMsg);//No I18N
						if(v.size() > 1)
						{
							Properties secondAlarm = (Properties)v.get(1); 
							String recentAlarmImg1 = secondAlarm.getProperty("imgsrc");//No I18N
							String recentAlarmMsg1 = secondAlarm.getProperty("message");//No I18N
							request.setAttribute("RecentAlarmImg1",recentAlarmImg1);//No I18N
							request.setAttribute("RecentAlarmMsg1",recentAlarmMsg1);//No I18N
						}
						else
						{
							request.setAttribute("RecentAlarmImg1","NODATA");//No I18N
							request.setAttribute("RecentAlarmMsg1","NODATA");//No I18N	
						}
						AlertAPI api=AlertFE.getAlertAPI();
						generateAlertReportsOnCategory(v,"source",nameOfTheMO,request,response,api);//No I18N
					}
					else
					{
						request.setAttribute("alarmChart","NODATA");//No I18N
						request.setAttribute("RecentAlarmImg","NODATA");//No I18N
						request.setAttribute("RecentAlarmMsg","NODATA");//No I18N
						request.setAttribute("RecentAlarmImg1","NODATA");//No I18N
						request.setAttribute("RecentAlarmMsg1","NODATA");//No I18N
					}
					// code snippet for alarm summary part ended
					if(v != null && v.size() > 0)
					{
						request.setAttribute("LastAlarm", v.elementAt(0)); //No Internationalization
						request.setAttribute("viewData", data.getData()); //No Internationalization
					}else
					{
						request.setAttribute("viewData", "NODATA"); //No Internationalization
					}
				}catch(Exception e)
				{
					e.printStackTrace();
					messages.add("Unable to fetch Managed Object details for the object : "+nameOfTheMO); //No Internationalization
					request.setAttribute("messages",messages); //No Internationalization
					return mapping.findForward("onerror"); //No Internationalization

				}
				TableColumn[] headerList = null;
				headerList = MODetailsHandler.getInstance().getHeaderList("Alerts",userName); //No Internationalization
				request.setAttribute("headerList", headerList); //No Internationalization
                */
				//request.setAttribute("details",deviceData); //No Internationalization
				request.setAttribute("name",nameOfTheMO ); //No Internationalization
			}else if(req.equals("UPDATESTATUS")) //No Internationalization
			{
				try
				{
					getStatusUpdate(nameOfTheMO);
				}catch(Exception e)
				{
					e.printStackTrace();
					messages.add("Unable to update the status of the Managed Object : "+nameOfTheMO); //No Internationalization
					request.setAttribute("messages",messages); //No Internationalization
					return mapping.findForward("onerror"); //No Internationalization
				}
			}
			boolean  modifyobject= GenericUtility.isAuthorized(userName, "Modify Object", false);//No I18N
			request.setAttribute("modifyObject",new Boolean(modifyobject));  //No Internationalization
			return af;
		}
		else
		{    
			request.setAttribute("messages",messages); //No Internationalization
			af = (ActionForward)mapping.findForward("onerror"); //No Internationalization
			return af;   
		}     
	}
	
	private String fetchImageForMO(String sevname,String type) {
		// TODO Auto-generated method stub
		TopoWebClientUtility twc = TopoWebClientUtility.getInstance();
		String image =  twc.getIconForObjectDetails(sevname, type);
		if(image==null||image.equals(""))
		{
			image = twc.getIconForObjectDetails(sevname,"Node");
		}
		return image;
	}

	public ActionForward getRecentAlarms(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
	throws Exception {
		WebCVUser cvUser = fetchRecentAlarms(request,response);
		replaceOldScriptSession(cvUser,request);
		return mapping.findForward("recentalarmspage");
	}
	
	protected WebCVUser fetchRecentAlarms(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String userName = (String) request.getSession().getAttribute(WebClientUtil.USER_KEY);
		String nameOfTheMO=(String)request.getAttribute("name");//No Internationalization
		if(nameOfTheMO == null)
		{
			nameOfTheMO=(String)request.getParameter("name");
		}
		Properties p = new Properties();
		p.setProperty("source",nameOfTheMO); //No Internationalization
		ViewData data = null;
		data = MODetailsHandler.getInstance().fetchAlertViewData("Alerts", "10", "0", null, "false", userName, p); //No Internationalization
		if(data != null)
		{					
			Vector alarms = data.getData();

			TableColumn[] headerList = AlarmViewUtil.getInstance().getHeaderList("Alerts",userName); //No Internationalization
			if(alarms != null && alarms.size() > 0)
			{
				request.setAttribute("viewData",alarms ); //No Internationalization
				request.setAttribute("headerList", headerList); //No Internationalization
			}
		}
		else
		{
			request.setAttribute("showAlarms","false");// this is handled in jsp file to hide the snapshot info table in this case.//No I18N
		}
		WebCVUser cvUser = new WebCVUser("Alerts","Alerts", userName,"alert"); 
		cvUser.setIsHomePageCV(true);
		AlertSessionBean AlertSession = null;
		try 
		{
			AlertSession = AlertFE.getAlertSession();
		}
		catch (ModuleNotInitializedException e) 
		{
			e.printStackTrace();
		}
		AlarmUpdateHandler handler = new AlarmUpdateHandler(cvUser,AlertSession, userName, "Alerts", true);
		cvUser.setUpdateHandler(handler);
		handler.setViewDataForUpdate((ViewData)data.clone());
		handler.setViewIdToUpdate("Alerts");
		
		return cvUser;
	}
	
	protected void replaceOldScriptSession( WebCVUser cvUser, HttpServletRequest request){
		try{
			String webcvkey = (String)request.getParameter("HomeWebCVKey");
			if(webcvkey != null && !webcvkey.equalsIgnoreCase("NULL")){
				org.directwebremoting.Container container = org.directwebremoting.ServerContextFactory.get().getContainer();
				org.directwebremoting.extend.ScriptSessionManager manager = container.getBean(org.directwebremoting.extend.ScriptSessionManager.class);
				java.util.Collection<org.directwebremoting.ScriptSession> sessions = manager.getAllScriptSessions();
				java.util.Iterator<org.directwebremoting.ScriptSession> iterator = sessions.iterator();
				while(iterator.hasNext()){
					org.directwebremoting.ScriptSession ss = iterator.next();
					WebCVUser user = null;
					
					if(webcvkey.equals(ss.getAttribute("HOME_CV_KEY"))) {
						WebCVUser oldCVUser = (WebCVUser)ss.getAttribute("WebUpdateHandler");
						SessionUpdateClient sessionUpdateClient = (SessionUpdateClient)PureServerUtilsFE.clientSocketFE.getUpdateClient(SessionUpdateClient.updateID);
						
						if(oldCVUser != null){
							sessionUpdateClient.deRegisterHandler(oldCVUser.getController(),oldCVUser.getUpdateHandler());
							oldCVUser = null;
						}
						String ssid = ss.getId();
						cvUser.setScriptSessionId(ssid);
						
						sessionUpdateClient.registerHandler(cvUser.getController(),cvUser.getUpdateHandler());
						ss.setAttribute("WebUpdateHandler", cvUser);
						break;
					}
				}
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public void getAlarmsforMOPage(HttpServletRequest request, HttpServletResponse response, String source)
	{
		//String entity = (String) request.getAttribute("MOname");
		Properties criteria = new Properties();
		criteria.put("source",source);
		String userName = (String)request.getSession().getAttribute(WebClientUtil.USER_KEY);
		try {
			ViewData data = MODetailsHandler.getInstance().fetchAlertViewData("Alerts", "10", "0", "modTime", "false", userName, criteria); //No Internationalization
			if(data != null)
			{       
				Vector alarms = data.getData();
				TableColumn[] headerList = null;
				headerList = AlarmViewUtil.getInstance().getHeaderList("Alerts",userName); //No Internationalization
				if(alarms != null && alarms.size() > 0)
				{
					request.setAttribute("viewData",alarms ); //No Internationalization
					request.setAttribute("headerList", headerList); //No Internationalization
				}
			}
			else
			{
				request.setAttribute("showAlarms","false");// this is handled in jsp file to hide the snapshot info table in this case.//No I18N
			}
		} catch (CustomViewException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  //No Internationalization
	}
	private boolean checkForPd(String nameOfTheMO) {
		// TODO Auto-generated method stub
		PollAPI papi = (PollAPI) NmsUtil.getAPI("PollAPI");
		try {
			Vector polls = papi.getPollsForAgent(nameOfTheMO);
			if(polls!=null && polls.size()>0)
				return true;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UserTransactionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NmsPollException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}
	
	 private JSONObject parseXMLandFetch(Properties businessData, String host,String port, String protocol, MODetailsXMLParser parser)
               throws JSONException {
		 parser = new MODetailsXMLParser(businessData, host, port, protocol);
		 JSONObject json = parser.getMODetailsJson();
		 return json;
	 }

	/**
	 * This method will fetch the Action mapping for the nearest super class of the given className.
	 * This method will be called only if the system is not able to find entry for the className from the 
	 * <strong> topo-struts-config.xml</strong> file.
	 *
	 * @param mapping ActionMapping instance.
	 * @param className Class Name of the ManagedObject.
	 *
	 * @author R. Balaji
	 * @since Web NMS 4.5
	 */
	private ActionForward getForwardForSuperClass(ActionMapping mapping, String className)
	{
		ActionForward forward = null;

		String[] superClasses = TopoWebClientUtility.getInstance().getMOHierarchy(className);
		int size=superClasses.length;
		for(int i=0;i<size;i++)
		{
			forward = mapping.findForward(superClasses[i]);
			if(forward !=null)
			{
				NmsLogMgr.TOPOERR.fail(NmsUtil.GetString("Unable to find the details page configured for "+className+" hence forwarding the action to the configuration of it's super class :" +superClasses[i]),null); //No Internationalization
				return forward;
			}
		} 
		return forward;
	}


	public Vector getObjects(String className, Properties criteria) throws ModuleNotInitializedException
	{
		ObjectDetailsHandler handler =new ObjectDetailsHandler();
		return handler.getObjects(className, criteria);
	}

	/**
	 * <p>Get the Business data of the ManagedObject from the TopoSessionBean, using the name of
	 * the ManagedObject.Users, who need to display custom data / data for their custom object, can override this
	 * method.&nbsp;</p>
	 * <p><b>Note:</b> Please do not forget to call the getBusinessData() method of
	 * the super class (super.getBusinessData()), in order to update the details of
	 * the default properties.</p>
	 *
	 * @param nameOfTheMO Name of the ManagedObject, to which the Details page needs to be displayed.
	 * @param userName Name of the User.
	 * @return Properties of the ManagedObject.
	 *
	 * @exception ModuleNotInitializedException the execption thrown by the application , when the topology
	 *  module is not initilized.
	 *
	 * @author R. Balaji
	 * @since Web NMS 4.5
	 *
	 */
	public Properties getBusinessData(String nameOfTheMO, String userName) throws ModuleNotInitializedException
	{
		ObjectDetailsHandler handler =new ObjectDetailsHandler();
		Properties moDetails= handler.getManagedObjectDetails(nameOfTheMO,userName);
		return moDetails;
	}

	/**
	 * Customize the Business Data and prepare the view data in a user friendly manner. Customization includes,
	 * customizing the following informations,
	 * <ul>
	 * <li>status ( int - String )</li>
	 * <li>discoveryStatus ( int - String )</li>
	 * <li>statusUpdateTime ( long - String )</li>
	 * <li>statusChangeTime ( long - String </li>
	 * <li>statusImage (Image based on the status of the MO)</li>
	 * <li>typeImage (Image based on the type of the MO)</li>
	 * </ul>
	 *
	 * @param nameOfTheMO Name of the ManagedObject, to which the Details page needs to be displayed.
	 * @param userName Name of the User.
	 * @param businessData Properties of the ManagedObject.
	 * @return Customized Properties of the ManagedObject.
	 *
	 * @author R. Balaji
	 * @since Web NMS 4.5
	 */

	public Properties customizeBusinessData(String nameOfTheMO, String userName, Properties businessData)
	{
		ObjectDetailsHandler handler =new ObjectDetailsHandler();
		Properties moDetails= handler.customizeDisplayProperties(businessData,userName);
		return businessData;
	}

	public void getStatusUpdate(String nameOfTheMO) throws ModuleNotInitializedException
	{

		MODetailsHandler handler = MODetailsHandler.getInstance();
		handler.UpdateStatus(nameOfTheMO);

	}

	public Properties getIntfGraphDetails(String nameOfTheMO, String userName,int requestid,String Instance, HttpSession session, PrintWriter out) throws ModuleNotInitializedException
	{
		try
		{
			MODetailsHandler handler = MODetailsHandler.getInstance();
			Properties chartProp = new Properties();
			chartProp.setProperty("chartType","AREA"); //No Internationalization
			chartProp.setProperty("moType","Interface"); //No Internationalization
			
			Properties moDetails= handler.getInterfaceGraph(nameOfTheMO,userName,requestid,"",Instance,chartProp,session,out); //No Internationalization*/
			return moDetails;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}


	public Properties getRouterGraph(String nameOfTheMO, String userName,int requestid, String category, HttpSession session, PrintWriter out) throws ModuleNotInitializedException
	{
		try
		{
			MODetailsHandler handler = MODetailsHandler.getInstance();
			Properties chartProp = new Properties();
			chartProp.setProperty("chartType","AREA"); //No Internationalization
			chartProp.setProperty("moType","Router"); //No Internationalization
			Properties moDetails= handler.getRouterGraphDetails(nameOfTheMO,userName,requestid,"","device", chartProp, session, out); //No Internationalization

			return moDetails;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public Properties getDeviceData(String nameOfTheMO, String userName,int requestid, String classname, String category, HttpSession session, PrintWriter out) throws ModuleNotInitializedException
	{
		try
		{
			MODetailsHandler handler = MODetailsHandler.getInstance();
			Properties moDetails= handler.getManagedObjectDetails(nameOfTheMO,userName,requestid, classname, category, session, out);

			return moDetails;
		}catch(Exception e)
		{ 
			e.printStackTrace();
		}
		return null;
	}

	public Properties getIntfDetails(String nameOfTheMO, String userName,String Instance) throws ModuleNotInitializedException
	{
		try
		{
			MODetailsHandler handler = MODetailsHandler.getInstance();
			Properties moDetails= handler.getInterfaceDetails(nameOfTheMO,userName,Instance);
			return moDetails;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	private  Properties  customizeInterfaceProps(Properties p) 
	{
		Properties prop = new Properties();
		prop.setProperty("name",(String)p.remove("name"));
		String ifDescr = (String)p.remove("ifDescr"); //No Internationalization
		if(ifDescr == null || ifDescr.trim().equals("")) //No Internationalization
		{
			ifDescr = "N/A";  //No Internationalization
		}
		prop.setProperty("ifDescr",ifDescr);
		prop.setProperty("ifSpeed",(String)p.remove("ifSpeed"));
		prop.setProperty("ifIndex",(String)p.remove("ifIndex"));
		String severity = (String) p.remove("status");
		String status = SeverityInfo.getInstance().getName(Integer.parseInt(severity));
		prop.setProperty("status",status.toLowerCase());
		String physAddr = (String)p.remove("physAddr"); //No Internationalization
		if(physAddr == null || physAddr.trim().equals("") ) //No Internationalization
		{
			physAddr = "N/A"; //No Internationalization
		}
		prop.setProperty("physAddr",physAddr);
		return prop;
	}

	private  Properties  customizePortProps(Properties p) 
	{
		Properties prop = new Properties();
		prop.setProperty("name",(String)p.remove("name"));
		prop.setProperty("ifDescr",(String)p.remove("portIfDescr"));
		prop.setProperty("ifSpeed",(String)p.remove("portIfSpeed"));
		prop.setProperty("ifIndex",(String)p.remove("portIfIndex"));
		prop.setProperty("status",(String)p.remove("status"));
		prop.setProperty("physAddr","N/A");
		return prop;
	}


	private Vector getInterfaceAsVector(String userName, String nameOfTheMO)
	{
		try
		{
			ObjectDetailsHandler handler =new ObjectDetailsHandler();
			Vector ipAddressVec = handler.getInterfacesOfNode(userName, nameOfTheMO);
			return ipAddressVec;
		}catch(Exception e)
		{   e.printStackTrace();
		}
		return null;
	}

	public Vector getGraphsList(String nameOfTheMO) throws ModuleNotInitializedException
	{
		try
		{
			MODetailsHandler handler = MODetailsHandler.getInstance();
			Vector graphs = handler.getManagedObjectGraphs(nameOfTheMO);
			return graphs;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	public void drawGraphForMO(String name,String className,String type,String uName,HttpServletRequest request, HttpServletResponse response)
	{
		HttpSession session = request.getSession();
		ServletContext application = session.getServletContext();
        HashMap graphInfohMap = new HashMap();
        String xmlName = PureUtils.rootDir + "webclient"+File.separator+"perf"+File.separator+"conf"+File.separator+"GraphInfo.xml";//No I18N

        try
        {
        	graphInfohMap.putAll(GraphInfoReader.getInstance().loadGraphInfo(xmlName));
        }
        catch(Exception exp)
        {
        	exp.printStackTrace();
        }
        if(application != null)
        {
        	application.setAttribute(ReportUtil.GRAPH_INFO_KEY, graphInfohMap);
        }

		PollAPI pai = null;
		Vector pollVector = null;
		Vector pollsForDisplay = new Vector();
		Vector morePollsList = new Vector();
		ReportUtil repUtil  = ReportUtil.getInstance();
		   	    
	 	Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);

        long startTime = cal.getTimeInMillis();
        long endTime = System.currentTimeMillis();
	    try
	    {
	    	pai = (PollAPI)NmsUtil.getAPI("PollAPI");//No I18N
	    	pollVector = pai.getPollsForAgent(name);
	    	
	    	   
	    		Enumeration e = pollVector.elements();
	    		while(e.hasMoreElements())
	    		{
	    				    			
	    			String key = (String)e.nextElement();
	    			
	    			PolledData pd = pai.getPolledData(key);
							
	    			if(pd.getIsMultiplePolledData())
	    			{
	    				CollectedData cd1=null;
	    				Vector allInstances = repUtil.getAllInstances(pd);
	    				if(allInstances.size() != 0)
	    				{
	    				  String instance = (String)allInstances.get(0);
	    				  cd1 = pai.getCollectedData(instance,key,startTime,endTime);
	    				}
	    						
	    						
	    				if(cd1 != null && pollsForDisplay.size() < 6 && !key.startsWith("hrStorageType_RAM") && !key.startsWith("hrStorageAllocationUnits_RAM") && !key.startsWith("hrStorageUsed_RAM") && !key.startsWith("hrStorageSize_RAM") && !key.startsWith("RouterMemoryUtilization") && !key.startsWith("RouterCPUUtilization") && !key.startsWith("MemoryUtilization") && !key.startsWith("CPUUtilization") && !key.startsWith("SwitchMemoryUtilization") && !key.startsWith("INTERFACE_in_octets") && !key.startsWith("INTERFACE_out_octets") && !key.startsWith("InterfaceInUtilization") && !key.startsWith("InterfaceOutUtilization"))//No I18N
		    			{
		    				pollsForDisplay.add(key);		
		    			}
		    			else
		    			{
		    			    morePollsList.add(key);	
		    			}
	    			}
	    			else 
	    			{
	    			    CollectedData cd = pai.getCollectedValues(key, startTime, endTime);
	    			    if(cd != null && pollsForDisplay.size() < 6 && !key.startsWith("hrStorageType_RAM") && !key.startsWith("hrStorageAllocationUnits_RAM") && !key.startsWith("hrStorageUsed_RAM") && !key.startsWith("hrStorageSize_RAM") && !key.startsWith("RouterMemoryUtilization") && !key.startsWith("RouterCPUUtilization") && !key.startsWith("MemoryUtilization") && !key.startsWith("CPUUtilization") && !key.startsWith("SwitchMemoryUtilization") && !key.startsWith("INTERFACE_in_octets") && !key.startsWith("INTERFACE_out_octets") && !key.startsWith("InterfaceInUtilization") && !key.startsWith("InterfaceOutUtilization"))//No I18N
	    			    {
	    				    pollsForDisplay.add(key);
	    				}
	    			    else
	    			    {
	    			        morePollsList.add(key);	
	    			    }
	    			} 
	    			
	    			
	    		}
	    }
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	    showMorePdList(morePollsList,request);
	    
	    if( pollsForDisplay == null || pollsForDisplay.size() == 0 )
	    {
	    	request.setAttribute("perfImageCount",0); //No I18N
	    }
	    else
	    {
	    	Vector<Properties> imageProb = new Vector<Properties>();
	    	int pollCount = pollsForDisplay.size();
	    	for(int i = 0 ; i <pollCount && i < 6; i ++)
	    	{
	    		String firstGraphKey = (String)pollsForDisplay.get(i);
	    	/*	try
			     {
			        PolledData pd1 = pai.getPolledData(firstGraphKey);
			        if(pd1.getIsMultiplePolledData())
			        {
				      request.setAttribute("AllInst1","TRUE");//No I18N
			        }
			        else
			        {
				      request.setAttribute("AllInst1","FALSE");//No I18N
			        }
			     }
			     catch(Exception e)
			     {
				    e.printStackTrace();
			     } */
			     
			     String arr1[] = firstGraphKey.split("\t");//No I18N
			     String firstGraphName = arr1[0];
			     String firstDisplayName = getDisplayNameForGraph(firstGraphName,application);
			     String firstImagePath = getMoreGraphImagePath(firstDisplayName,uName);
			     try
			     {
			     boolean firstFlag = generateGraphForMO(pollCount,firstDisplayName,firstImagePath,firstGraphKey,startTime,endTime,response.getWriter());
			     
			     if(firstFlag)
			     {
			    	 Properties graphProb = new Properties();
			    	 graphProb.setProperty("FirstName",firstGraphName); //No I18N
			    	 graphProb.setProperty("FirstTitle",firstDisplayName); //No I18N
			    	 graphProb.setProperty("FirstPath",firstImagePath); //No I18N
			    	 
			    	imageProb.add(graphProb);
			     }
			     else
			     {
				    request.setAttribute("FirstName"+i,"ERROR");//No I18N
				    request.setAttribute("FirstTitle"+i,firstDisplayName);//No I18N
				    request.setAttribute("FirstPath"+i,"ERROR");//No I18N
			     }
			     }
			     catch(Exception e)
			     {
			    	 e.printStackTrace();
			     }
	    	}
	    		if(imageProb.size() == 5)
	    		{
	    			imageProb.remove(4);
	    		}
	    		
	    		request.setAttribute("perfImageCount",imageProb.size()); //No I18N
	    		request.setAttribute("imagePathKeys",imageProb); //No I18N
	    }
		}
	
	public void getPdList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		JSONObject json = new JSONObject();
		try {

			json = getPdList(request.getParameter("moname"),request.getParameter("tosearch"),request.getParameter("agent"),request);
			String search = request.getParameter("tosearch");
			if(json == null && (search == null||search.equals("")))
			{

			}
			/*if(polls == null)
              json.put("grapherror","No Polled data found to match given criteria ");
              else
              json = showMorePdList(polls,request);*/

		} catch (Exception e) {
			String message = "Exception while fetching graphs for MO"
					+ request.getParameter("moname");
			NmsLogMgr.POLLERR.log(message, Log.INTERMEDIATE_DETAIL);
			e.printStackTrace();
			json.put("grapherror", message);
			if (request != null){
				request.setAttribute("grapherror", message);
			}
		} finally {
			PrintWriter pw = response.getWriter();
			pw.println(json);
			pw.flush();
		}

	}
	private JSONObject getPdList(String name,String tosearch,String agent,HttpServletRequest req)throws Exception {

		JSONObject json = null;
		PollAPI pai = null;
		//Vector pollVector = new Vector();
		Vector pollsForDisplay = new Vector();
		Vector<PolledData> poll=null;

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);

		long starttime = cal.getTimeInMillis();

		long endtime = System.currentTimeMillis();



		pai = (PollAPI) NmsUtil.getAPI("PollAPI");// No I18N
		if(tosearch == null || tosearch.equals("null") || tosearch.equals("")){
			tosearch="*";
		}

		Properties match = new Properties();
		match.put("name",tosearch);
		if(agent==null || agent.equals("")||agent.equals("null"))
			match.put("agent",name);
		else
			match.put("agent",agent);
		poll = pai.getObjects(null, match);
		if(poll == null || poll.isEmpty()){
			if(tosearch.equalsIgnoreCase("*"))
			{       
				NmsLogMgr.POLLUSER.log("No Polled data found to match the criteria "+match,Log.INTERMEDIATE_DETAIL);

			}
			else{
				NmsLogMgr.POLLUSER.log("No Polled data configured for Managed Object "+name,Log.INTERMEDIATE_DETAIL);

			}
			return null;
		}

		json = getPollsForDisplay(poll,pollsForDisplay,req);




		return json;
	}

	private JSONObject getPollsForDisplay(Vector<PolledData> polls,Vector pdlist,HttpServletRequest req) throws JSONException, RemoteException, UserTransactionException, NmsPollException
	{
		String strt = req.getParameter("start");
		String lgth = req.getParameter("length");
		PollAPI api = (PollAPI) NmsUtil.getAPI("PollAPI");
		int start = 1;
		int length = 10;
		if (strt != null && !strt.equals("")) {
			start = Integer.parseInt(strt);
			start--;
		}
		if (lgth != null && !lgth.equals("")) {
			length = Integer.parseInt(lgth);
		}
		int end = start+length;
		if (end > polls.size())
			end = polls.size();
		JSONObject json = new JSONObject();
		Hashtable keyhash = new Hashtable();
		for(int i=start;i<=end-1;i++)
		{
			PolledData pd = polls.elementAt(i);
			String key = pd.getKey();
			if(pd==null)
			{
				end=i;
				break;
			}
			pdlist.add(key);
			keyhash.put(pd.getName(),key);

		}
		if (pdlist.size() != 0 && pdlist != null) {

			if (req != null) {
				req.setAttribute("PdList", pdlist);
				req.setAttribute("PdKeys", keyhash);
				req.setAttribute("total", polls.size());
				req.setAttribute("end",end);
				//req.setAttribute("name", nameOfTheMO);// No I18N

			}
			json.put("PdList", pdlist);// No I18N
			json.put("PdKeys",keyhash);
			json.put("start", start+1);// Since start and end will have to
			// displayed starting from 1.
			json.put("end", end);
			json.put("total", polls.size());

		} else {
			json.put("PdList", "NODATA");// No I18N
		}

		return json;

	}
	
	private long[] getlongArray(Long l[])
    {
    int size = l.length;
    long lng[] = new long[size];
    for(int i=0;i<size;i++)
    {
            lng[i] = l[i].longValue();
    }
    return lng;
    }
	
	private double[] getdoubleArray(Double l[])
	{
	int size = l.length;
	double dble[] = new double[size];
	for(int i=0;i<size;i++)
	{
		dble[i] = l[i].doubleValue();
	}
	return dble;
	}

	

	protected void setupDir(String dir,String userName)
	{
		String absoluteDir =  PureUtils.rootDir + dir;
		File f = new File(absoluteDir);
		if (f.exists())
		{
			String[] list = f.list();
			if (list != null)
			{
				int l = list.length;
				for (int i = 0; i < l; i++)
				{
					File f1 = new File(absoluteDir + list[i]);
					NmsLogMgr.POLLUSER.log("Trying to remove Graph image "+f1.getAbsolutePath()+".", Log.DEBUG);//No Internationalization
					boolean b = false;
					if(!NmsUtil.createUserDir)
					{
						if((f1.getName()).startsWith(userName))
						{
							f1.delete();
						}	
					}
					else
					{
						f1.delete();
					}


					if (b)
					{
						NmsLogMgr.POLLUSER.log("Graph image "+f1.getAbsolutePath()+" has been deleted.",Log.DEBUG);//No Internationalization
					}
				}
			}
		}
		else
		{
			NmsLogMgr.POLLUSER.log("Trying to create directory : "+f.getAbsolutePath(), Log.DEBUG);//No Internationalization
			f.mkdirs();
		}
	}
	
	public String getMoreGraphImagePath(String name,String userName)
	{
		String imagePath = null;
		String dir = null;

		if(!NmsUtil.createUserDir)
		{
			//If CREATE_USERDIR is set then all files will be created under html/defaultToAllUsers/
			dir = "/html/defaultsToAllUsers/perf_images/"+name+"/";//No Internationalization
		}
		else
		{
			dir =  "/users/" + userName + "/perf_images/"+name+"/";//No Internationalization

		}

		
		setupDir(dir,userName);

		if(!NmsUtil.createUserDir)
		{
			
			imagePath = dir + userName +"_"+ System.currentTimeMillis() + ".jpg";//No Internationalization
		}
		else
		{
			
			imagePath = dir + System.currentTimeMillis() + ".jpg";//No Internationalization
		}
		return imagePath;
	}
	
    public void showMorePdList(Vector list,HttpServletRequest req)
    {
    	Vector pdlist = new Vector();
    	Enumeration eu = list.elements();
    	while(eu.hasMoreElements())
    	{
    		String temp = (String)eu.nextElement();
    		String arr1[] = temp.split("\t");//No I18N
    		String key = arr1[0];
    		if(!key.equals("hrStorageType_RAM") && !key.equals("hrStorageAllocationUnits_RAM") && !key.equals("hrStorageUsed_RAM") && !key.equals("hrStorageSize_RAM") && !key.equals("RouterMemoryUtilization") && !key.equals("RouterCPUUtilization") && !key.equals("MemoryUtilization") && !key.equals("CPUUtilization") && !key.equals("SwitchMemoryUtilization"))//No I18N
    		{	
    		  pdlist.add(arr1[0]);
    		}
    	}
    	if(pdlist.size() != 0 && pdlist != null)
    	{
    	  req.setAttribute("PdList",pdlist);//No I18N
    	}
    	else
    	{
    	  req.setAttribute("PdList","NODATA");//No I18N
    	}
    }
    
    public String getDisplayNameForGraph(String name,ServletContext application)
    {
    	String grKey = ReportUtil.GRAPH_INFO_KEY;
    	HashMap gBeanHMap = (HashMap) application.getAttribute(grKey);
    	
    		GraphInfoBean gBean = (GraphInfoBean) gBeanHMap.get(name);
    		String toRet = null;
            if(gBean != null)
            {
            	toRet = NmsUtil.GetString(gBean.getTitle());
            }
            else
            {
            		
            			toRet = name;
            }
            return toRet;
    }
    
    public boolean generateGraphForMO(int pollCount,String title,String imagePath,String pdKey,long startTime,long endTime, PrintWriter printWriter)
    {
    	PollAPI pai = null;
    	ReportUtil repUtil = ReportUtil.getInstance();
    	boolean flagToRet = false;
    	PolledData pd = null;
    	Vector firstInstance = new Vector();
    	String arr1[] = pdKey.split("\t");//No I18N
    	String name=arr1[0];
    	String graphWidth = "285"; //No I18N 
    	String graphHeight = "175"; //No I18N
    	
    	if((pollCount == 4) || (pollCount == 2) || (pollCount == 5))
    	{
    		graphWidth = "350"; //No I18N
    	}
    	else if(pollCount == 1)
    	{
    		graphWidth = "550"; //No I18N
    		graphHeight = "300"; //No I18N
    	}
    	else
    	{
    		
    	}
    	
    	Properties graphProp = new Properties();
		graphProp.setProperty("imagePath",imagePath);//No I18N
		graphProp.setProperty("startTime",String.valueOf(startTime));//No I18N
		graphProp.setProperty("endTime",String.valueOf(endTime));//No I18N
		graphProp.setProperty("width",graphWidth);//No I18N 
		graphProp.setProperty("height",graphHeight);//No I18N 
		graphProp.setProperty("xAxis","Time");//No I18N
		graphProp.setProperty("yAxis","Values");//No I18N
		graphProp.setProperty("dateFormat","HH:mm");//No I18N
		
		try
		{
		   pai = (PollAPI)NmsUtil.getAPI("PollAPI");//No I18N
		   pd = pai.getPolledData(pdKey);
		   if(pd.getIsMultiplePolledData())
		   {
			  Vector allinstances = repUtil.getAllInstances(pd);
			  for(int i = 0 ; i < allinstances.size(); i++)
			  {
			  		firstInstance.add(allinstances.get(i));
			  }
		   } 
		   
		   Map cdMap =  repUtil.getCollectedData(pd,firstInstance,startTime,endTime);
		   TreeMap tempTreeMap = new TreeMap(cdMap);
		   Set set = tempTreeMap.keySet();
           Iterator iterator = set.iterator();
           Vector dataVector = new Vector();
           while(iterator.hasNext())
           {
        	   String cdKey = (String) iterator.next();
               CollectedData cData = (CollectedData) cdMap.get(cdKey);
               Long[] times = cData.getTimes();
               long[] values = null;
               double[] dvalues = null;
               String oid = pd.getOid();
		       String protocol=pd.getProtocol();
		       String community = pd.getCommunity();
		       int idx=-1;
               if(protocol != null && protocol.equals("SNMP"))//No I18N
		 	   {
					idx = oid.lastIndexOf(".");//No Internationalization
					String IFSPEEDOID = "2.2.1.5";//No Internationalization
					if(pd.getIsMultiplePolledData())
					{
						IFSPEEDOID = "2.2.1.5"+cdKey;//No Internationalization
					}
					else
					{
						IFSPEEDOID = "2.2.1.5"+oid.substring(idx);//No Internationalization
					}

					String agentName = arr1[1];

					if (name.equals("CPUUtilization"))//No Internationalization
					{
						Object[] obj = cData.getValues();
						if(obj instanceof Long[])
						{
							values = IfcUtilizationCalculator.calculateCPUUtilization((Long[])obj);
						}
						else if(obj instanceof Double[])
						{
							dvalues = IfcUtilizationCalculator.calculateCPUUtilization((Double[])obj);
						}
					}
					else if (name.equals("InterfaceInUtilization"))//No Internationalization
					{
						long ifSpeed = repUtil.getSnmpData(agentName,community,IFSPEEDOID);
						if (ifSpeed <= 0)
						{
							return false;
						}
						int pollInterval = pd.getPeriod();
						Object[] obj = cData.getValues();
						if(obj instanceof Long[])
						{
							values = IfcUtilizationCalculator.calculateInterfaceInUtilization((Long[])obj, ifSpeed, pollInterval);
						}
						else if(obj instanceof Double[])
						{
							dvalues = IfcUtilizationCalculator.calculateInterfaceInUtilization((Double[])obj, ifSpeed, pollInterval);
						}
					}
					else if (name.equals("InterfaceOutUtilization"))//No Internationalization
					{
						long ifSpeed = repUtil.getSnmpData(agentName,community,IFSPEEDOID);
						if (ifSpeed <= 0)
						{
							return false;
						}
						int pollInterval = pd.getPeriod();
						Object[] obj = cData.getValues();
						if(obj instanceof Long[])
						{
							values = IfcUtilizationCalculator.calculateInterfaceOutUtilization((Long[])obj, ifSpeed, pollInterval);
						}
						else if(obj instanceof Double[])
						{
							dvalues = IfcUtilizationCalculator.calculateInterfaceOutUtilization((Double[])obj, ifSpeed, pollInterval);
						}
					}
				    else
				    {
				    	Object[] obj = cData.getValues();
						if(obj instanceof Long[])
						{
							values = getlongArray((Long[])obj);
						}
						else if(obj instanceof Double[])
						{
							dvalues = getdoubleArray((Double[])obj);
						}
				    }
			   //modified for SNMP dependency (next four lines included)
			   }
               else
			   {
            	   Object[] obj  = cData.getValues();
                  	if(obj instanceof Long[])
                  	{
                  		values = getlongArray((Long[])obj);
                  	}
                  	else if(obj instanceof Double[])
                  	{
                  		dvalues = getdoubleArray((Double[])obj);
                  	}
			   }
               
               Properties dataProp = new Properties();
               Long[] lValues = null;
               Double[] dValues = null;
               if(values != null)
               {
            	   int length = values.length;
            	   lValues = new Long[length];
            	   for(int i=0;i<length;i++)
            	   {
            		   lValues[i]=new Long(values[i]);
            	   }
            	   dataProp.put("Values",lValues);//No Internationalization
               }
               else if(dvalues != null)
               {
            	   int length = dvalues.length;
            	   dValues = new Double[length];
            	   for(int i=0;i<length;i++)
            	   {
            		   dValues[i]=new Double(dvalues[i]);
            	   }
            	   dataProp.put("Values",dValues);//No Internationalization
               }
               dataProp.put("Times",times);//No Internationalization
               dataProp.setProperty("YAXISTEXT",cdKey);//No I18N
               dataVector.add(dataProp);
           }//end of while

           flagToRet = repUtil.generateLineWithoutPtsGraph(title,dataVector,graphProp,printWriter);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    	
    	return flagToRet;
    }
    
    public void generateAlertReportsOnCategory(Vector<Properties> vec,String type,String source,HttpServletRequest request,HttpServletResponse response,AlertAPI api) throws Exception
	{
                
		Vector<String> cats=new Vector<String>();
		for(Properties prop:vec)
		{
			String cat=prop.getProperty("category");//No I18N
			if(!cats.contains(cat))

			{
				cats.addElement(cat);
			}
		}
		/*FaultReportsUtility fru = FaultReportsUtility.getInstance();
		DefaultPieDataset data = fru.getPieDataSet(cats,type,source,api);	
		String filename = fru.createPieChart(data,request,response,api,cats,type,source);
        cats.addElement("Total");//No I18N
        request.setAttribute("alarmChart",filename);
		request.setAttribute("categoryList",cats);//No I18N*/
	}
    
    public ActionForward getPerformanceMonitors(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	HttpSession session = request.getSession();
    	PrintWriter out = response.getWriter();
    	String userName = (String)session.getAttribute(WebClientUtil.USER_KEY);
    	String name = request.getParameter("name");//No I18N
    	String classname = request.getParameter("classname");//No I18N
    	String category = request.getParameter("category");//No I18N
    	int requestId = Integer.parseInt(request.getParameter("requestId"));//No I18N
    	Properties deviceData = getDeviceData(name,userName,requestId, classname, category, session, out);
    	 JSONObject tosend = new JSONObject();
    	 if(deviceData != null)
    	 {
    		 String cpusage = deviceData.getProperty("CPU_USAGE");
    		 if(cpusage == null||cpusage.equals(""))
    			 cpusage="NODATA";
    		 tosend.put("CPU_USAGE",cpusage);
    		 
    		 String mem = deviceData.getProperty("RAM_USAGE");
    		 if(mem == null || mem.equals(""))
    			 mem="NODATA";
    		 tosend.put("RAM_USAGE", mem);
    		 
    		 String disk = deviceData.getProperty("DISK_USAGE");
    		 if(disk == null||disk.equals(""))
    			 disk="NODATA";
    		 tosend.put("DISK_USAGE",disk);
    	 }
    	 //request.setAttribute("details", deviceData);// No I18N
    	 request.setAttribute("name", name);// No I18N
    	 request.setAttribute("ClassName", classname);// No I18N
    	 request.setAttribute("ForPerfMonitors", "true");// No I18N
    	 out.println(tosend);
    	 out.flush();
    	 return null;// No I18N
    }
    	
    
    public ActionForward getInterfaceTableList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	HttpSession session = request.getSession();
    	PrintWriter out = response.getWriter();
    	String moType = request.getParameter("type");//No I18N
    	String nameOfTheMO = request.getParameter("name");//No I18N
    	String classname = request.getParameter("classname");//No I18N
    	String category = request.getParameter("category");//No I18N
    	int requestId = Integer.parseInt(request.getParameter("requestId"));//No I18N
    	String userName = (String)session.getAttribute(WebClientUtil.USER_KEY);
    	String isSnmp = request.getParameter("isSnmp");//No I18N
    	if(isSnmp != null && isSnmp.equals("true") && moType!=null && (!moType.equals("Switch")) && (!moType.equals("Interface"))) //No Internationalization
		{

			Vector interfaceList = getInterfaceAsVector(userName, nameOfTheMO);
			Vector interfaceVec = new Vector();

			if(interfaceList != null && interfaceList.size()>0)
			{
				for(Enumeration en = interfaceList.elements(); en.hasMoreElements();)
				{
					String ipa = (String)en.nextElement();
					Properties p = getBusinessData(ipa,userName); //ipAddress.getProperties();
					Properties prop = customizeInterfaceProps(p);
					p.clear();
					interfaceVec.add(prop);
				}
			}
			request.setAttribute("IF_DATA",interfaceVec); //No Internationalization
		}
    	
    	if(classname.equals("SwitchObject")) //No Internationalization
		{
    		Properties deviceData = getDeviceData(nameOfTheMO,userName,requestId, classname, category, session, out);
			Vector ifVect = (Vector)deviceData.get("IF_DATA"); //No Internationalization
			request.setAttribute("details",deviceData);//No Internationalization
			request.setAttribute("name",nameOfTheMO);//No I18N
			request.setAttribute("IF_DATA",ifVect); //No Internationalization
		}
    	request.setAttribute("ForInterfaceTable","true"); //No Internationalization
    	return mapping.findForward("intftable");//No I18N	
    }
    
    public ActionForward getMOGraphs(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	HttpSession session = request.getSession();
    	PrintWriter out = response.getWriter();
    	String moType = request.getParameter("type");//No I18N
    	String nameOfTheMO = request.getParameter("name");//No I18N
    	String classname = request.getParameter("classname");//No I18N
    	String category = request.getParameter("category");//No I18N
    	int requestId = Integer.parseInt(request.getParameter("requestId"));//No I18N
    	String userName = (String)session.getAttribute(WebClientUtil.USER_KEY);
    	String isSnmp = request.getParameter("isSnmp");//No I18N
    	boolean isRouterType = Boolean.parseBoolean(request.getParameter("isRouter"));//No I18N
    	String instance = request.getParameter("instance");//No I18N
    	    	
    	ManagedObject mob = null;
		TopoAPI tp = (TopoAPI) NmsUtil.getAPI("TopoAPI");//No I18N
		if(tp != null)
		{
			mob = tp.getByName(nameOfTheMO);
		}
		if(isRouterType == false && !moType.equals("Interface") && !moType.equals("Port") && mob instanceof com.adventnet.nms.topodb.SnmpNode)//No I18N
		{
          
		  drawGraphForMO(nameOfTheMO,classname,moType,userName,request,response);
		  request.setAttribute("name",nameOfTheMO);//No I18N
		  request.setAttribute("ForMOGrahps","true"); //No Internationalization

		}
		else
		{
			request.setAttribute("ForMOGrahps","true"); //No Internationalization
			request.setAttribute("graphPath","NODATA");//No I18N

		}
		
		
		if(isRouterType)
		{
			Vector graphsList = getGraphsList(nameOfTheMO);//No I18N
			
            if(graphsList != null)
			{
				HashMap pollsList = new HashMap();
				for(int i=0;i<graphsList.size();i++)
				{
					StringTokenizer st1 = new StringTokenizer((String)graphsList.elementAt(i),"\t");//No I18N
					String name = st1.nextToken();//No I18N
					String title = null;//No I18N

					if(name != null)//No I18N
					{
						title = name;//No I18N
					}

					pollsList.put(name,NmsUtil.GetString(title));
				}
				request.setAttribute("name",nameOfTheMO);//No I18N
				request.setAttribute("PollsList",pollsList);//No I18N
                
			}			

            Properties rout = getRouterGraph(nameOfTheMO, userName, requestId, category, session,out);
            request.setAttribute("RouterGraph",rout); //No Internationalization
			request.setAttribute("ForMOGrahps","true"); //No Internationalization
			return mapping.findForward("routergrphs");//No Internationalization
		}
		
		if(moType.equals("Port") || moType.equals("Interface"))//No I18N
		{
			int requestID=102;
			ManagedObject motest = tp.getByName(nameOfTheMO);
			if(motest instanceof SnmpInterface)
			{
				SnmpInterface si = (SnmpInterface)motest;
				nameOfTheMO = si.getParentNode();
			}
			else
			{
				nameOfTheMO = motest.getParentKey();
			}
			
			Properties deviceData = getIntfGraphDetails(nameOfTheMO,userName,requestID,instance,session,out);
			
			request.setAttribute("IntfGraph",deviceData); //No Internationalization
			request.setAttribute("MOTYPE",moType);//No Internationalization
			request.setAttribute("ForMOGrahps","true"); //No Internationalization
		}
		
		return mapping.findForward("grphs");//No I18N

    }
    
    public void getGraph(ActionMapping mapping, ActionForm form,
              HttpServletRequest request, HttpServletResponse response) throws JSONException
              {
      long t1 = System.currentTimeMillis();
      JSONArray data = null;
      JSONObject error = new JSONObject();
      PrintWriter pw=null;
      try {
      pw=response.getWriter();
      //JSONObject error = new JSONObject();
      String charttype =request.getParameter("chart$");
      
      String pdkey = request.getParameter("Pdkey");
      PollAPI pai = (PollAPI) NmsUtil.getAPI("PollAPI");// No I18N
      String st = request.getParameter("starttime");
      long starttime=0;
      long endtime=0;
      String ed = request.getParameter("endtime");
      if(st!= null && !st.equals("")){
      starttime = new Long(new Double(st).longValue());
      }
      if(ed!= null && !ed.equals("")){
              endtime = new Long(new Double(ed).longValue());
              }
      
      if (starttime == 0 || endtime == 0) {
              Calendar cal = Calendar.getInstance();
              cal.set(Calendar.HOUR_OF_DAY, 0);
              cal.set(Calendar.MINUTE, 0);
              cal.set(Calendar.SECOND, 0);

              starttime = cal.getTimeInMillis();

              endtime = System.currentTimeMillis();
      }

      String ImagePath = "";
      if (pai != null) {
              PolledData pd = pai.getPolledData(pdkey);
              Calendar cal = Calendar.getInstance();
              cal.set(Calendar.HOUR_OF_DAY, 0);
              cal.set(Calendar.MINUTE, 0);
              cal.set(Calendar.SECOND, 0);

              String GraphName = pd.getName();
              CollectedData cd = null;
              boolean hasdata = true;
              if(pd==null)
              {
                      error.put("GraphError","No polled data found matching key "+pdkey);
              }
              if (pdkey.startsWith("hrStorageType_RAM")
                              || pdkey.startsWith("hrStorageAllocationUnits_RAM")
                              || pdkey.startsWith("hrStorageUsed_RAM")
                              || pdkey.startsWith("hrStorageSize_RAM")
                              || pdkey.startsWith("RouterMemoryUtilization")
                              && !pdkey.startsWith("RouterCPUUtilization")
                              || pdkey.startsWith("MemoryUtilization")
                              || pdkey.startsWith("CPUUtilization")
                              || pdkey.startsWith("SwitchMemoryUtilization"))// No I18N
              {
                      hasdata = false;

              }
              if(hasdata)
              {       
                      
                      data=ReportUtil.getInstance().fetchJsonforPd(pdkey, starttime, endtime, charttype);
                      long t2 = System.currentTimeMillis();
                      long diff= t2-t1;
                      
              } 
              

              if(data== null)
              {
                      error.put("GraphError","Exception while fetching data for graphs.Refer server side logs for further details");
              }
              else if(data.length()==0)
              {       
                      error.put("GraphError","webclient.topo.objectdetails.graphs.na");
              }
              
              
              
              }
              
              }
              catch (Exception e) {
              error.put("GraphError","Exception while fetching data for graphs.Refer server side logs for further details");
              NmsLogMgr.POLLERR.log("Exception while fetching data for PolledData  "+e.getMessage(),Log.INTERMEDIATE_DETAIL);
              e.printStackTrace();
              }
              finally{
              if(error.length()>=1)
              {
                      data = new JSONArray();
                      data.put(error);
              }
              pw.println(data);
              pw.flush();
              pw.close();
              }
              
              }

    private JSONObject getJsonDataforCD(CollectedData cd,long[] values,long interval,boolean first,String charttype) throws JSONException
    {
    	JSONObject obj = new JSONObject();
    	JSONArray mdata = new JSONArray();
    	JSONObject mobj = new JSONObject();
    	JSONArray ddata = new JSONArray();
    	double[] values1;
    	Long[] times = cd.getTimes();
    	int skip = 1;
    	int limit = 1000;
    	int per = 500;
    	int upto = new Double(Math.floor(limit/3)).intValue();
    	if(charttype== null ||charttype.equals("")||charttype.equals("undefined"))
    		charttype="normal";
    	//obj.put("chart$","norma");
    	if(times.length>limit)
    	{
    		//long interval = times[1]-times[0];
    		skip = new Long(new Double(Math.floor(times.length/per)).longValue()).intValue();
    		if(first)
    		{       
    			//charttype="master";
    			//obj.put("chart$","master");
    			obj.put("from",times[0]);
    			obj.put("to",times[upto]);
    			obj.put("period",interval);
    			//obj.put("mdchart",true);
    		}
    		//System.out.println("Skip is "+skip+":::"+first+":::"+obj);
    	}
    	if(values == null)
    	{       
    		Object vals = cd.getValues();
    		if(vals instanceof Long[] )
    		{       
    			values = getlongArray((Long[]) vals);
    			if(values == null || values.length==0)
    				return null;
    			setJsonData(values,times,skip,mdata);
    		}
    		else if(vals instanceof Double[])
    		{
    			values1 = getdoubleArray((Double[]) vals);
    			if(values1 == null || values1.length==0)
    				return null;
    			setJsonData(values1,times,skip,mdata);
    		}
    	}
    	//System.out.println("DEBUG values are "+values);

    	/*{     
              int k = 0;
              int count = 0;
              int length = times.length;
              while(true)
              {
                      k= (count)*skip;
                      if(k<=length-1)
                      {       
                      JSONArray mdatarr = new JSONArray();
                      mdatarr.put(times[k].longValue());

                      mdatarr.put(values[k]);
                      mdata.put(mdatarr);
                      count++;
                      }
                      else
                              break;
              }*/
    	if(first){

    		if(skip>1)
    		{
    			charttype="master";
    		}
    		else if(skip==1 && charttype.equalsIgnoreCase("master"))
    		{
    			charttype="detail";

    		}
    		else if(skip==1 && charttype.equalsIgnoreCase("detail"))
    			charttype="normal";

    	}
    	obj.put("chart$",charttype);
    	obj.put("data",mdata);
    	return obj;
    }
    
    private void setJsonData(long[] values,Long[] times,int skip,JSONArray mdata)
      {
              int k = 0;
              int count = 0;
              int length = times.length;
              while(true)
              {
                      k= (count)*skip;
                      if(k<=length-1)
                      {       
                      JSONArray mdatarr = new JSONArray();
                      mdatarr.put(times[k].longValue());
                      
                      mdatarr.put(values[k]);
                      mdata.put(mdatarr);
                      count++;
                      }
                      else
                              break;
              }
      }
      
      private void setJsonData(double[] values,Long[] times,int skip,JSONArray mdata) throws JSONException
      {
              int k = 0;
              int count = 0;
              int length = times.length;
              while(true)
              {
                      k= (count)*skip;
                      if(k<=length-1)
                      {       
                      JSONArray mdatarr = new JSONArray();
                      mdatarr.put(times[k].longValue());
                      
                      mdatarr.put(values[k]);
                      mdata.put(mdatarr);
                      count++;
                      }
                      else
                              break;
              }
      }
      private int setChartParams(JSONObject obj,Long[] times) throws JSONException
      {
              int limit = 2000;
              int per = 500;
              int k = 3 ;// the fraction of values to be seen intially
              int skip = new Long(new Double(Math.floor(times.length/per)).longValue()).intValue();
              int upto = new Double(Math.floor(limit/k)).intValue();
              obj.put("chart$","master");
              obj.put("from",times[0]);
              obj.put("to",times[upto]);
              
              return skip;
              
      }
      public void getGraphforMOPage(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
      {
    	  try{
    		  String separator = "/";
    		  String moName = request.getParameter("moname");
    		  String key = request.getParameter("Pdkey");
    		  JSONArray data = new JSONArray();
    		  JSONObject error = new JSONObject();
    		  Vector vecInstances = null;
    		  String uName = WebClientUtil.USER_KEY;
    		  String charttype = request.getParameter("charttype");
    		  String period = request.getParameter("period");//No Internationalization

    		  NmsLogMgr.POLLUSER.log(NmsUtil.GetString("webclient.performance.reports.action.moname")+" : "+moName, Log.DEBUG);//No Internationalization
    		  NmsLogMgr.POLLUSER.log(NmsUtil.GetString("webclient.performance.reports.action.defaultperiod")+" : "+period, Log.DEBUG);//No Internationalization
    		  ReportUtil repUtil = ReportUtil.getInstance();
    		  PollAPI papi = (PollAPI) NmsUtil.getAPI("PollAPI");
    		  //String strDateFormat="";
    		  Date startDateSel = new Date();
    		  Date endDateSel = new Date();

    		  if (period.equals("today"))
    		  {
    			  startDateSel.setHours(0);
    			  startDateSel.setMinutes(0);
    			  startDateSel.setSeconds(0);
    		  }
    		  else if(period.equalsIgnoreCase("yesterday"))
    		  {

    			  startDateSel.setHours(0);
    			  startDateSel.setMinutes(0);
    			  startDateSel.setSeconds(0);

    			  startDateSel = new Date(startDateSel.getTime()-86400000);
    			  //86400000 = 24 hours * 60 minutes * 60 secs * 1000ms
    			  endDateSel.setHours(0);
    			  endDateSel.setMinutes(0);
    			  endDateSel.setSeconds(0);

    		  }
    		  else if (period.equals("past7days"))
    		  {
    			  startDateSel = new Date(startDateSel.getTime() - 604800000l);
    			  // 604800000 = 7days * 24 hours * 60 minutes * 60 secs * 1000ms
    		  }

    		  long startDate = startDateSel.getTime();
    		  long endDate = endDateSel.getTime();

    		  long timeDiff = endDate - startDate;
    		  int reportRangeType = ReportUtil.DAY;

    		  if (timeDiff < 604800000l && timeDiff>86400000) // 1 week
    		  {
    			  reportRangeType = ReportUtil.WEEK;
    		  }

    		  String pollName = request.getParameter("Pollname");

    		  PolledData pData = null;
    		  try
    		  {
    			  pData = papi.getPolledData(key);
    			  //                      repUtil.getPolledData(moName, pollName);

    			  if (pData == null)
    			  {
    				  error.put("GraphError",NmsUtil.GetString("webclient.performance.reports.nopolleddatafound"));
    				  return;
    				  //return getErrorActionForward("webclient.performance.reports.nopolleddatafound",request,mapping);//No I18n
    			  }
			  //String community = pData.getCommunity();//No I18N
    			  data =ReportUtil.getInstance().fetchJsonforPd(pData.getKey(),startDate, endDate,charttype);
    			  if(data.length()==0)
    			  {
    				  error.put("GraphError","NODATA");

    			  }
    		  }
    		  catch(Exception e)
    		  {
    			  NmsLogMgr.POLLERR.log("Exception while fetching graphs for MO"+moName,Log.INTERMEDIATE_DETAIL);
    			  e.printStackTrace();
    			  error.put("GraphError","Exception while fetching graphs for MO"+moName);
    		  }
    		  finally{
    			  PrintWriter pw = response.getWriter();
    			  if(error.length()>=1)
    			  {
    				  data = new JSONArray();
    				  data.put(error);
    			  }
    			  pw.println(data);
    			  pw.flush();
    			  pw.close();
    		  }
    	  }
    	  catch(Exception e)
    	  {
    		  System.err.println(NmsUtil.GetString("Exception while fetching graph for snapshot view "));
    		  e.printStackTrace();
    	  }
      }
      
    
}

