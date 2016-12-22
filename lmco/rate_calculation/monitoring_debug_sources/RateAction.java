//$Id: RateAction.java,v 1.24.4.5.2.1 2014/02/12 09:13:50 venkatramanan Exp $

package com.adventnet.nms.webclient.management;

import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.util.PureUtils;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.HashMap;
import java.util.Vector;
import java.util.Enumeration;
import java.util.Properties;
import javax.servlet.http.*;
import org.apache.struts.action.*;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import com.adventnet.nms.poll.PollAPI;
import com.adventnet.nms.poll.PolledData;
import com.adventnet.nms.poll.MultiplePolledData;
import com.adventnet.nms.poll.CollectedData;
import com.adventnet.nms.management.*;
import com.adventnet.nms.util.InternalUtil;
import com.adventnet.nms.util.NmsUtil;
import org.jfree.chart.ChartRenderingInfo;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.adventnet.nms.webclient.performance.reports.ReportUtil;

public class RateAction extends Action
{
	private static String winCpuOID = ".1.3.6.1.2.1.25.3.3.1.2"; //No I18N
	private static String linCpuOID = ".1.3.6.1.4.1.2021.10.1.5";//No I18N
	static{
		Hashtable mapping = NmsUtil.getDeviceOIDAndTypes();
		String value = (String)mapping.get("win_CPU_OID");//No I18N
		if(value != null && !value.equals(""))//No I18N
		{
			winCpuOID = value;
		}
	}
	static{
		Hashtable mapping = NmsUtil.getDeviceOIDAndTypes();
		String value = (String)mapping.get("linux_CPU_OID");//No I18N
		if(value != null && !value.equals(""))//No I18N
		{
			linCpuOID = value;
		}
	}

    GraphUtil graphutil = null;
    
    public synchronized ActionForward execute(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest request, HttpServletResponse httpservletresponse) throws Exception
    {
    	String rateType = request.getParameter("rateType"); //No I18N
        Hashtable hashtable = new Hashtable();
        Long[] rate = null;
        Long[] time = null;
        int reportInterval = 1;
        int graphWidth = 191;
        int graphHeight = 157;
        String hostName = request.getParameter("hostName");//No I18N
        String snmpPort = request.getParameter("snmpPort");//No I18N
        String jvmType = request.getParameter("jvmType");//No I18N
        String osName = request.getParameter("osName");//No I18N
        String zoom = request.getParameter("zoom");//No I18N
        String associationId = request.getParameter("divId");
        request.setAttribute("JVMGraph", "jvm");
        request.setAttribute("HostName",hostName);//No I18N
        request.setAttribute("SNMPPort",snmpPort);//No I18N
        request.setAttribute("JVMType",jvmType);//No I18N
        request.setAttribute("ReportType",rateType);//No I18N
	Hashtable feJVMs = (Hashtable)request.getSession().getAttribute("FEJVMs");
	Hashtable clientJVMs = 	(Hashtable)request.getSession().getAttribute("ClientJVMs");
	String gtype=request.getParameter("graphType");//No I18N
	
        if(zoom != null && (!zoom.equals("")))//No I18N
        {
            request.setAttribute("ZoomMode","yes");//No I18N
            graphWidth = 540;
            graphHeight = 220;
        }
	if(gtype==null||gtype.equals(""))//No I18N
	{
       gtype = "area";//No I18N
	}

        String period = "1";//No I18N
        Hashtable ht = (Hashtable)request.getSession().getAttribute("JVMChartRefreshInterval");//No I18N


        // ************** Hashtable will have the mapping of JVM Vs GraphType&Period. If the user changes the GRAPH option, then this mapping will have details ***************************** //
        if(ht != null)
        {
            if(jvmType.equals("BE"))//No I18N
            {
                Properties beProps = (Properties)ht.get("BE");//No I18N
                if(beProps != null)
                {
                    gtype = beProps.getProperty("GraphType");//No I18N
                    period = beProps.getProperty("Period");//No I18N
                }
            }
            else if(jvmType.equals("FE"))//No I18N
            {
                    Properties feProps = (Properties)ht.get("FE");//No I18N
                    if(feProps != null)
                    {
                    	gtype = feProps.getProperty("GraphType");//No I18N
                    	period = feProps.getProperty("Period");//No I18N
                    }
            }
            else if(jvmType.equals("CLIENT"))//No I18N
            {
                    Properties feProps = (Properties)ht.get("CLIENT");//No I18N
                    if(feProps != null)
                    {
                    	gtype = feProps.getProperty("GraphType");//No I18N
                    	period = feProps.getProperty("Period");//No I18N
                    }
            }
        }
        if(gtype == null)
        {
            gtype = "area";//No I18N
        }

        if(period == null)
        {
            period = "1";//No I18N
        }

        reportInterval = new Integer(period).intValue();
        long timeInterval = (long)1000*60*60*24*reportInterval;
        long startTime = System.currentTimeMillis()-timeInterval;
        long currentTime = System.currentTimeMillis();
	String agentName = "";//No I18N
	if(jvmType.equals("CLIENT"))//No I18N
	{
		agentName="Client_";//No I18N
	}
	else
	{
		agentName=jvmType+"_";//No I18N
	}
	
        String pdKey = null;
        PollAPI pollAPI = (PollAPI)NmsUtil.getAPI("PollAPI");//No I18N
        HashMap hMap = null;
        // ************************************ Block for fetching Collected Values from Database ********************************* //


        if (rateType.equals("MemoryUtilization"))//No I18N
        {
	    request.setAttribute("ChartRefreshInterval","60");//No I18N
	    try
            {
		    pdKey = "JVMPD_"+jvmType+"_"+snmpPort+"_MonitorMemory\t"+agentName+hostName+"\t((.1.3.6.1.4.1.42.2.145.3.163.1.1.2.11.0+.1.3.6.1.4.1.42.2.145.3.163.1.1.2.21.0)*100)/(.1.3.6.1.4.1.42.2.145.3.163.1.1.2.13.0+.1.3.6.1.4.1.42.2.145.3.163.1.1.2.23.0)"; //No I18N
            ReportUtil rep = ReportUtil.getInstance();
	        JSONArray datarr = rep.fetchJsonforPd(pdKey, startTime, currentTime, gtype);
	        
	        	if(datarr.length()==0){
				request.setAttribute("GraphError","NODATA");
	        	}
				else 
				{	
					JSONObject err = (JSONObject)datarr.get(0);
					if(err.has("GraphError"))
					{
					request.setAttribute("GraphError",err.get("GraphError"));}
					else{
					request.setAttribute("chartType",gtype.toLowerCase());
					request.setAttribute("data",datarr);}
				}
	        	request.setAttribute("xtitle",NmsUtil.GetString("webclient.performance.reports.defaultxaxis"));
	        	request.setAttribute("ytitle",NmsUtil.GetString("webclient.percentage.chat.yaxis"));
	            request.setAttribute("divId", associationId);
	            request.setAttribute("usemarker",""); 
            }
            catch(Exception e)
            {
                //e.printStackTrace();
            }
        }

        else if (rateType.equals("CPUUtilization"))//No I18N
        {
	    request.setAttribute("ChartRefreshInterval","60");//No I18N
	    try
            {
                String cpuOID = null;
                if(osName.startsWith("Win"))//No I18N
                {
			cpuOID = winCpuOID;
                }
                else
                {
			cpuOID = linCpuOID;
                }
                pdKey = "JVMPD_"+jvmType+"_"+snmpPort+"_MonitorCPU\t"+agentName+hostName+"\t"+cpuOID; //No I18N
                ReportUtil rep = ReportUtil.getInstance();
    	        JSONArray datarr = rep.fetchJsonforPd(pdKey, startTime, currentTime, gtype);
    	        
    	        	if(datarr.length()==0){
    				request.setAttribute("GraphError","NODATA");
    	        	}
    				else 
    				{	
    					JSONObject err = (JSONObject)datarr.get(0);
    					if(err.has("GraphError"))
    					{
    					request.setAttribute("GraphError",err.get("GraphError"));}
    					else{
    					request.setAttribute("data",datarr);
    					request.setAttribute("chartType",gtype.toLowerCase());}
    				}
    	        	request.setAttribute("xtitle",NmsUtil.GetString("webclient.performance.reports.defaultxaxis"));
    	        	request.setAttribute("ytitle",NmsUtil.GetString("webclient.percentage.chat.yaxis"));
    	            request.setAttribute("divId", associationId);
    	            request.setAttribute("usemarker","");
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }

        else if(rateType.equals("ThreadCount"))//No I18N
        {
	    request.setAttribute("ChartRefreshInterval","60");//No I18N
            try
            {
                pdKey = "JVMPD_"+jvmType+"_"+snmpPort+"_MonitorThread\t"+agentName+hostName+"\t.1.3.6.1.4.1.42.2.145.3.163.1.1.3.1.0";//No I18N
                ReportUtil rep = ReportUtil.getInstance();
    	        JSONArray datarr = rep.fetchJsonforPd(pdKey, startTime, currentTime, gtype);
    	        
    	        	if(datarr.length()==0){
    				request.setAttribute("GraphError","NODATA");
    	        	}
    				else 
    				{	
    					JSONObject err = (JSONObject)datarr.get(0);
    					if(err.has("GraphError"))
    					{
    					request.setAttribute("GraphError",err.get("GraphError"));
    					}
    					else{
    					request.setAttribute("data",datarr); 
    					request.setAttribute("chartType",gtype.toLowerCase()); }
    				}
    	        	request.setAttribute("xtitle",NmsUtil.GetString("webclient.performance.reports.defaultxaxis"));
    	        	request.setAttribute("ytitle",NmsUtil.GetString("webclient.performance.reports.yaxisThreadCount"));
    	            request.setAttribute("divId", associationId);
    	            request.setAttribute("usemarker","");
            }
            catch(Exception e1)
            {
                //e1.printStackTrace();
            }
        }

        else if(rateType.equals("StatusPollingRate"))//No I18N
        {
        	request.setAttribute("ChartRefreshInterval","60");//No I18N
            JSONArray dataCollectionRate = RateUtil.getRates(rateType,startTime);
            JSONArray tosend = new JSONArray();
            JSONObject obj = new JSONObject();
            try {
    			obj.put("data", dataCollectionRate);
    			obj.put("name", rateType);
    			tosend.put(obj);
    		} catch (JSONException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
            if(dataCollectionRate.length()==0){
    			request.setAttribute("GraphError","NODATA");
    			//datarr.put(error);
    		}
    		else 
    		{	
    			request.setAttribute("data",tosend);
    			request.setAttribute("chartType",gtype.toLowerCase());
    			//request.setAttribute("usemarker",false);
    		}
            request.setAttribute("xtitle",NmsUtil.GetString("webclient.performance.reports.defaultxaxis"));
            request.setAttribute("ytitle",NmsUtil.GetString("webclient.performance.reports.defaultyaxis"));
            request.setAttribute("divId", associationId);
        }

        else if(rateType.equals("DataCollectionRate"))
        {
        	   JSONArray dataCollectionRate = RateUtil.getRates(rateType,startTime);
               JSONArray tosend = new JSONArray();
               JSONObject obj = new JSONObject();
               try {
       			obj.put("data", dataCollectionRate);
       			obj.put("name", rateType);
       			tosend.put(obj);
       		} catch (JSONException e) {
       			// TODO Auto-generated catch block
       			e.printStackTrace();
       		}
               if(dataCollectionRate.length()==0){
       			request.setAttribute("GraphError","NODATA");
       		}
       		else 
       		{	
       			request.setAttribute("data",tosend);
       			request.setAttribute("chartType",gtype.toLowerCase());
       		}
               request.setAttribute("xtitle",NmsUtil.GetString("webclient.performance.reports.defaultxaxis"));
               request.setAttribute("ytitle",NmsUtil.GetString("webclient.performance.reports.yaxisDC"));
               request.setAttribute("divId", associationId);
        }
        else if(rateType.equals("TrapRate"))
        {
        	JSONArray dataCollectionRate = RateUtil.getRates(rateType,startTime);
            JSONArray tosend = new JSONArray();
            JSONObject obj = new JSONObject();
            try {
    			obj.put("data", dataCollectionRate);
    			obj.put("name", rateType);
    			tosend.put(obj);
    		} catch (JSONException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
            if(dataCollectionRate.length()==0){
    			request.setAttribute("GraphError","NODATA");
    		}
    		else 
    		{	
    			request.setAttribute("data",tosend);
    			request.setAttribute("chartType",gtype.toLowerCase());
    		}
            request.setAttribute("xtitle",NmsUtil.GetString("webclient.performance.reports.defaultxaxis"));
            request.setAttribute("ytitle",NmsUtil.GetString("webclient.performance.reports.yaxisTrapRate"));
            request.setAttribute("divId", associationId);
        }
        else if(rateType.equals("EventRate"))
        {
        	JSONArray dataCollectionRate = RateUtil.getRates(rateType,startTime);
            JSONArray tosend = new JSONArray();
            JSONObject obj = new JSONObject();
            try {
    			obj.put("data", dataCollectionRate);
    			obj.put("name", rateType);
    			tosend.put(obj);
    		} catch (JSONException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
            if(dataCollectionRate.length()==0){
    			request.setAttribute("GraphError","NODATA");
    		}
    		else 
    		{	
    			request.setAttribute("data",tosend);
    			request.setAttribute("chartType",gtype.toLowerCase());
    		}
            request.setAttribute("xtitle",NmsUtil.GetString("webclient.performance.reports.defaultxaxis"));
            request.setAttribute("ytitle",NmsUtil.GetString("webclient.performance.reports.yaxisEventRate"));
            request.setAttribute("divId", associationId);
        }
        else if(rateType.equals("AlertRate"))
        {
        	JSONArray dataCollectionRate = RateUtil.getRates(rateType,startTime);
            JSONArray tosend = new JSONArray();
            JSONObject obj = new JSONObject();
            try {
    			obj.put("data", dataCollectionRate);
    			obj.put("name", rateType);
    			tosend.put(obj);
    		} catch (JSONException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
            if(dataCollectionRate.length()==0){
    			request.setAttribute("GraphError","NODATA");
    		}
    		else 
    		{	
    			request.setAttribute("data",tosend);
    			request.setAttribute("chartType",gtype.toLowerCase());
    		}
            request.setAttribute("xtitle",NmsUtil.GetString("webclient.performance.reports.defaultxaxis"));
            request.setAttribute("ytitle",NmsUtil.GetString("webclient.performance.reports.yaxisAlertRate"));
            request.setAttribute("divId", associationId);
        }
        return actionmapping.findForward("hcgraph");
    
    }

}


