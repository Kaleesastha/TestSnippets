/* $Id: ThreadCountGraphDS.java,v 1.2.4.3.2.1 2014/02/12 09:13:50 venkatramanan Exp $ */
package com.adventnet.nms.webclient.home.widgets;


import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;

import com.adventnet.nms.poll.CollectedData;
import com.adventnet.nms.poll.PollAPI;
import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.util.PureUtils;
import com.adventnet.nms.webclient.home.DataSource;
import com.adventnet.nms.webclient.home.DataSourceResponse;
import com.adventnet.nms.webclient.home.HomePageDetails;
import com.adventnet.nms.webclient.home.WidgetParams;
import com.adventnet.nms.webclient.management.GraphUtil;
import com.adventnet.nms.webclient.sysadmin.AdminStatusUtil;
import com.adventnet.nms.webclient.performance.reports.ReportUtil;
import com.adventnet.nms.webclient.util.WebClientHomePageUtil;
import com.adventnet.nms.management.WebNMSBEResourceMonitoring;

public class ThreadCountGraphDS implements DataSource {

    PollAPI pollAPI = null;
    GraphUtil graphutil = null;
    
	public DataSourceResponse generateWidgetData(WidgetParams params) {
		// TODO Auto-generated method stub
       Hashtable jvmHt = (Hashtable)AdminStatusUtil.getInstance().getResponseFromBE("GET_JVM_DETAILS");//No I18N
       if(jvmHt!=null)
       {
    	   Properties bejvmProps = (Properties)jvmHt.get("BEServer");//No I18N
    	   getGraph(bejvmProps,params.request,params.associationID);
       }
		DataSourceResponse dataSrcResponse = new DataSourceResponse();
		String returnValue = "error"; //No i18n
		if(jvmHt !=null)
		{
			returnValue = "hcgraph";//No i18n
		}
		dataSrcResponse.forward=returnValue;//No i18n
		return dataSrcResponse;
	}

	void getGraph(Properties bejvmProps,HttpServletRequest request,long associationId)
	{
        Long[] rate = null;
        Double[] drate = null;
        Long[] time = null;
        String hostAddress = bejvmProps.getProperty("HOSTADDRESS");//No I18N
	        String snmpPort = bejvmProps.getProperty("SNMP_Port");//No i18n
	        String jvmType ="BE";//No i18n
	        String gtype = "area";//No I18N

	        String period = "1";//No I18N
	        Hashtable ht = (Hashtable)request.getSession().getAttribute("JVMChartRefreshInterval");//No I18N

	        
	        if(ht != null)
	        {
	                Properties beProps = (Properties)ht.get("BE");//No I18N
	                if(beProps != null)
	                {
	                    gtype = beProps.getProperty("GraphType");//No I18N
	                    period = beProps.getProperty("Period");//No I18N
	                }
	                
	        }
	            
	            if(period == null)
	            {
	                period = "1";//No I18N
	            }
	            int reportInterval = new Integer(period).intValue();
	            
	 			   if(pollAPI ==null)
				   {
					   pollAPI = (PollAPI)NmsUtil.getAPI("PollAPI");//No I18N
				   }
            try
            {
                //String pdKey = "JVMPD_"+jvmType+"_"+snmpPort+"_MonitorThread\tBE_"+hostAddress+"\t.1.3.6.1.4.1.42.2.145.3.163.1.1.3.1.0";//No I18N
                String pdKey = "JVMPD_"+jvmType+"_"+snmpPort+"_MonitorThread\tBE_"+hostAddress+"\t"+WebNMSBEResourceMonitoring.threadOID;//No I18N

                long endTime = System.currentTimeMillis();
                long timeInterval = (long)1000*60*60*24*reportInterval;
                long startTime = System.currentTimeMillis()-timeInterval;
                

	        boolean noDataFlag=false;

	        
	        String type = "";
	        
		    Vector <String> criteria = HomePageDetails.getInstance().getWidgetCriteria(associationId, true);

		    if(criteria!=null && criteria.size()!=0)
		    {
		    	JSONObject jsoncriteria=null;
		    	try
		    	{
		    		jsoncriteria = new JSONObject(criteria.get(0));
			    	if(jsoncriteria!=null)
			    	{
			    		type = jsoncriteria.getString("graphType");//No i18n
			    	}
		    	}
		    	catch(JSONException jse)
		    	{
		    		jse.printStackTrace();
		    	}
		    }
		    if(type ==null || type.equals(""))//No i18n
	        {
	        	type="line";
	        	//graphType = GraphUtil.AREACHART;
	        }
	        request.setAttribute("chartType",type.toLowerCase());
	        ReportUtil rep = ReportUtil.getInstance();
	        JSONArray datarr = rep.fetchJsonforPd(pdKey, startTime, endTime, type);
	        	if(datarr.length()==0){
				request.setAttribute("GraphError","NODATA");
	        	}
				else 
				{	
					JSONObject err = (JSONObject)datarr.get(0);
					if(err.has("GraphError"))
				
					request.setAttribute("GraphError",err.get("GraphError"));
				
					else
					request.setAttribute("data",datarr);
				}
	        	request.setAttribute("xtitle","Time");
	        	request.setAttribute("ytitle","Thread Count");
	        	request.setAttribute("widgetid",new Long(associationId).toString());
	        	request.setAttribute("usemarker",false);
            }
            catch(Exception exp)
            {
            	exp.printStackTrace();
            }
	}

	void customizeChart(JFreeChart chart)
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");//No I18N
	    DateAxis dateAxis= new DateAxis(""); //No I18N
	    dateAxis.setDateFormatOverride(dateFormat);
	    chart.getXYPlot().setDomainAxis(dateAxis);
	    chart.getXYPlot().getRangeAxis().setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        chart.setBackgroundPaint(Color.WHITE);
        chart.getTitle().setFont(new Font("Sans Serif",Font.PLAIN,10));//No i18n
        chart.getXYPlot().getRenderer().setBaseItemLabelFont(new Font("Sans Serif",Font.PLAIN,10));//No i18n
        chart.getXYPlot().setOutlinePaint(new Color(255,255,255));
        chart.getXYPlot().setBackgroundPaint(new Color(255,255,255));
        chart.setBorderVisible(false);
        chart.getXYPlot().getRenderer().setPaint(Color.ORANGE);
	}

		
		
	
}

