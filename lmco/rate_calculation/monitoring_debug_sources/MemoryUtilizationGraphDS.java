/* $Id: MemoryUtilizationGraphDS.java,v 1.2.4.4.2.2 2014/03/20 09:30:05 venkatramanan Exp $ */
package com.adventnet.nms.webclient.home.widgets;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.adventnet.nms.webclient.home.DataSource;
import com.adventnet.nms.webclient.home.DataSourceResponse;
import com.adventnet.nms.webclient.home.HomePageDetails;
import com.adventnet.nms.webclient.home.WidgetParams;
import com.adventnet.nms.webclient.management.GraphUtil;
import com.adventnet.nms.webclient.performance.reports.ReportUtil;
import com.adventnet.nms.poll.PollAPI;
import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.util.PureUtils;
import com.adventnet.nms.poll.CollectedData;
import com.adventnet.nms.webclient.sysadmin.AdminStatusUtil;
import com.adventnet.nms.webclient.util.WebClientHomePageUtil;
import com.adventnet.nms.management.WebNMSBEResourceMonitoring;

public class MemoryUtilizationGraphDS implements DataSource
{

    PollAPI pollAPI = null;
    GraphUtil graphutil = null;
    
	public DataSourceResponse generateWidgetData(WidgetParams params) {
		// TODO Auto-generated method stub
		
        Hashtable jvmHt = (Hashtable)AdminStatusUtil.getInstance().getResponseFromBE("GET_JVM_DETAILS");//No I18N
        if(jvmHt==null)
        {
        	System.err.println(NmsUtil.GetString("No data found for Memory Utilization"));//No i18n
    		DataSourceResponse dataSrcResponse = new DataSourceResponse();
    		dataSrcResponse.forward="error";//No i18n
    		return dataSrcResponse;
        }
        Properties bejvmProps = (Properties)jvmHt.get("BEServer");//No I18N
        
        if(pollAPI ==null)
		   {
			   pollAPI = (PollAPI)NmsUtil.getAPI("PollAPI");//No I18N
		   }
        getDataToPlot(bejvmProps,params.request,params.associationID);

 
		DataSourceResponse dataSrcResponse = new DataSourceResponse();
		dataSrcResponse.forward="hcgraph";//No i18n
		return dataSrcResponse;
	}
	

	
	   
	void getDataToPlot(Properties bejvmProps,HttpServletRequest request,long associationId)
	{
        Long[] rate = null;
        Double[] drate = null;
        Long[] time = null;
        String hostAddress = bejvmProps.getProperty("HOSTADDRESS");//No I18N
	        String snmpPort = bejvmProps.getProperty("SNMP_Port");//No i18n
	        String jvmType ="BE";//No i18n

		    try
	        {
		        //String pdKey = "JVMPD_"+jvmType+"_"+snmpPort+"_MonitorMemory\tBE_"+hostAddress+"\t.1.3.6.1.4.1.42.2.145.3.163.1.1.2.11.0+.1.3.6.1.4.1.42.2.145.3.163.1.1.2.21.0"; //No I18N
			String oid = WebNMSBEResourceMonitoring.memoryFormula;
		        String pdKey = "JVMPD_"+jvmType+"_"+snmpPort+"_MonitorMemory\tBE_"+hostAddress+"\t"+oid; //No I18N
		        
		        long endTime = System.currentTimeMillis();
		        long startTime = endTime-(1000*60*60);
				   
	                /*CollectedData collVect = pollAPI.getCollectedValues(pdKey,startTime,currentTime);//No I18N
	                if(collVect !=null)
	                {
		                //rate = (Long[])collVect.getValues();
	                	Object[] obj = collVect.getValues();
	                	if(obj instanceof Long[])
	                	{
	                		rate = (Long[])obj;
	                	}
	                	else if(obj instanceof Double[])
	                	{
	                		drate = (Double[])obj;
	                	}
		                time = collVect.getTimes();
	                }*/
	        
	        boolean noDataFlag=false;

	        int graphType = GraphUtil.LINECHART;
	        
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
				
					else{
					request.setAttribute("data",datarr);
					request.setAttribute("xtitle",NmsUtil.GetString("webclient.performance.reports.index.cpuutilization.xaxisname"));
					request.setAttribute("ytitle","%");
					}
				}
	        	request.setAttribute("widgetid",new Long(associationId).toString());
	        	request.setAttribute("usemarker",false);
	        /*else
	        {
	        	graphType = GraphUtil.LINECHART;
	        }
	        
	        String title = hostAddress;//No I18N
	        graphutil = new GraphUtil(graphType,title);
	        graphutil.setXTimeAxis();
	        if (rate==null || time==null || rate.length==0 || time.length==0 || drate==null || drate.length==0)
            {
                graphutil.setNoData();//No I18N
                noDataFlag = true;
            }
	        if(!noDataFlag)
	        {
	            graphutil.setXTimeAxis();
	            if(rate != null)
	            {
	            	graphutil.setLongData(new String[]{NmsUtil.GetString("Rate Graph")}, time, rate);//No I18N
	            }
	            else
	            {
	            	graphutil.setDecimalData(new String[]{NmsUtil.GetString("Rate Graph")}, time, drate);//No I18N
	            }
	        }
	        
	        String user = (String)request.getSession().getAttribute("userName");//No I18N
	        customizeChart(graphutil.getChart());
	        
	        int width = 400;
    	    String widthValue =request.getParameter("columnWidth");//No i18n
    	    if(widthValue!=null && !(widthValue.equals("undefined")) && !(widthValue.equals("")))//while editing and doing a cancel the value might come as undefined.
    	    {
    	    	width = Integer.parseInt(widthValue)*10;
    	    }
    	    
    	    int height = width/2;
    	    
    	    if((rate == null) || (rate.length == 0))
    	    {
    	    		height = 150;
    	    }
	        String fileName = WebClientHomePageUtil.setUpImage("MemoryUtilization",graphutil.getChart(),user,associationId,width,height);//No I18N
	        request.setAttribute("imageName", fileName);//No i18n
    		request.setAttribute("imgwidth",width);
    		request.setAttribute("imgheight",height);*/
	        }
	        catch(Exception e)
	        {
	        	e.printStackTrace();
	        }
	}

	void customizeChart(JFreeChart chart)
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");//No I18N
	    DateAxis dateAxis= new DateAxis("");//No I18N
	    dateAxis.setDateFormatOverride(dateFormat);
	    chart.getXYPlot().setDomainAxis(dateAxis);
	    chart.getXYPlot().getRangeAxis().setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        chart.setBackgroundPaint(Color.WHITE);
        chart.getTitle().setFont(new Font("Sans Serif",Font.PLAIN,10));//No i18n
        chart.getXYPlot().getRenderer().setBaseItemLabelFont(new Font("Sans Serif",Font.PLAIN,10));//No i18n
        chart.getXYPlot().setOutlinePaint(new Color(255,255,255));
        chart.getXYPlot().setBackgroundPaint(new Color(255,255,255));
        chart.setBorderVisible(false);
        chart.getXYPlot().getRenderer().setPaint(Color.cyan);
	}
	

}



