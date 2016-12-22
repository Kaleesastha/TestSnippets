/* $Id: CPUUtilizationGraphDS.java,v 1.2.4.3.2.1 2014/02/12 09:13:50 venkatramanan Exp $ */
package com.adventnet.nms.webclient.home.widgets;


import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import com.adventnet.nms.webclient.home.DataSource;
import com.adventnet.nms.webclient.home.DataSourceResponse;
import com.adventnet.nms.webclient.home.HomePageDetails;
import com.adventnet.nms.webclient.home.WidgetParams;
import com.adventnet.nms.webclient.management.GraphUtil;
import com.adventnet.nms.poll.MultiplePolledData;
import com.adventnet.nms.poll.PollAPI;
import com.adventnet.nms.poll.PolledData;
import com.adventnet.nms.poll.NmsDataDecoder;
import com.adventnet.nms.webclient.performance.reports.ReportUtil;
import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.util.PureUtils;
import com.adventnet.nms.poll.CollectedData;
import com.adventnet.nms.webclient.sysadmin.AdminStatusUtil;
import com.adventnet.nms.webclient.util.WebClientHomePageUtil;


public class CPUUtilizationGraphDS implements DataSource {

    PollAPI pollAPI = null;
    GraphUtil graphutil = null;
	
	public DataSourceResponse generateWidgetData(WidgetParams params)
	{
		// TODO Auto-generated method stub
        Hashtable jvmHt = (Hashtable)AdminStatusUtil.getInstance().getResponseFromBE("GET_JVM_DETAILS");//No I18N
        if(jvmHt==null)
        {
        	System.err.println(NmsUtil.GetString("No data found for CPU Utilization"));//No i18n
    		DataSourceResponse dataSrcResponse = new DataSourceResponse();
    		dataSrcResponse.forward="error";//No i18n
    		return dataSrcResponse;
        }
        Properties bejvmProps = (Properties)jvmHt.get("BEServer");//No I18N

		if(pollAPI ==null)
		{
		   pollAPI = (PollAPI)NmsUtil.getAPI("PollAPI");//No I18N
		}

        getGraph(bejvmProps,params.request,params.associationID);
       
		DataSourceResponse dataSrcResponse = new DataSourceResponse();
		dataSrcResponse.forward="hcgraph";//No i18n
		return dataSrcResponse;
	}
	
	   
	void getGraph(Properties bejvmProps,HttpServletRequest request,long associationId)
	{
        String hostAddress = bejvmProps.getProperty("HOSTADDRESS");//No I18N
        String osName = bejvmProps.getProperty("OSName");//No i18n
        String snmpPort = bejvmProps.getProperty("SNMP_Port");//No i18n

        String cpuOID = this.getCPUOID(osName);
        String pdKey = "JVMPD_BE_"+snmpPort+"_MonitorCPU\tBE_"+hostAddress+"\t"+cpuOID; //No I18N
        long endTime = System.currentTimeMillis();
        long startTime = endTime-(1000*60*60);

        HashMap hMap=null;
        try
        {
            /*PolledData pData = pollAPI.getPolledData(pdKey);
            if(pData != null)
            {
                hMap = new HashMap();
                if (pData.getIsMultiplePolledData())
                {
                    Vector mpdInstances = pollAPI.getInstances((MultiplePolledData)pData);
                    Enumeration enumerate = mpdInstances.elements();
                    while (enumerate.hasMoreElements())
                    {
                        String inst = (String) enumerate.nextElement();
                        CollectedData cdata = pollAPI.getCollectedData(inst, pData.getKey(),startTime, currentTime);
                        if(cdata!=null)
                        {
                            hMap.put(inst,cdata);
                        }
                    }
                }
            }
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }*/
        boolean noDataFlag=false;

        //int graphType = GraphUtil.LINECHART;
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
	    if(type ==null || type.equalsIgnoreCase(""))//No i18n
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
				{	
				request.setAttribute("data",datarr);
				request.setAttribute("xtitle",NmsUtil.GetString("webclient.performance.reports.index.cpuutilization.xaxisname"));
				request.setAttribute("ytitle","%");
				}
			}
        	request.setAttribute("widgetid",new Long(associationId).toString());
        	request.setAttribute("usermarker",false);
       
        /*else
        {
        	graphType = GraphUtil.LINECHART;
        }
        String title = hostAddress;//No I18N
        graphutil = new GraphUtil(graphType,title);
        graphutil.setXTimeAxis();
        if (hMap==null)
        {
            graphutil.setNoData();//No I18N
            noDataFlag = true;
        }
        if(!noDataFlag)
        {
            graphutil.setXTimeAxis();
            graphutil.setHMapData(new String[]{NmsUtil.GetString("CPU Utlilization")},hMap);//No I18N
        }
        customizeChart(graphutil.getChart());
        setUpImage("CPUUtilization",request,associationId,snmpPort);//No I18N*/
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
	}

	void customizeChart(JFreeChart chart)
	{
        chart.setBackgroundPaint(Color.WHITE);
        chart.getTitle().setFont(new Font("Sans Serif",Font.PLAIN,10));//No i18n
        chart.getXYPlot().getRenderer().setBaseItemLabelFont(new Font("Sans Serif",Font.PLAIN,10));//No i18n
        chart.getXYPlot().setOutlinePaint(new Color(255,255,255));
        chart.getXYPlot().setBackgroundPaint(new Color(255,255,255));
        chart.setBorderVisible(false);
        chart.getXYPlot().getRenderer().setPaint(Color.cyan);
        //chart.getXYPlot().setNoDataMessage(NmsUtil.GetString("webclient.fault.activealarm.nodata")); //No i18n
	}
	
		public synchronized void setUpImage(String Rate, HttpServletRequest request,long associationId, String snmpPort)
		{
		    try
		    {
		        int width = 400;
		        int height = 150;
		        
		        String user = (String)request.getSession().getAttribute("userName");//No I18N
		        String dir = null;//No I18N
		        String fileName = null;
					if(!NmsUtil.createUserDir)
					{
						dir = File.separator+"html"+File.separator+"defaultsToAllUsers"+File.separator+"widgets"+File.separator+Rate+associationId+File.separator;//No I18N
						fileName = user +"_" +Rate+System.currentTimeMillis()+ ".jpg";//No I18N
					}
					else
					{
						dir = File.separator+"users"+File.separator+ user +File.separator+"widgets"+File.separator+Rate+associationId+File.separator;//No I18N	
						fileName = Rate +System.currentTimeMillis()+ ".jpg";//No I18N
					}
		
		        WebClientHomePageUtil.setupDir(dir, user);
		        
		        String imagePath = dir + fileName;//No I18N
		        String absPath = PureUtils.rootDir + imagePath;//No I18N
		        ChartUtilities.saveChartAsJPEG(new File(absPath), graphutil.getChart(), width, height);//No I18N
		        request.setAttribute("imageName", imagePath);//No I18N
		        
		    }
		    catch(Exception e)
		    {
		        e.printStackTrace();
		    }
		}
			
	    private String getCPUOID(String osName)
	    {      
	        String cpuOID = null;
	        if(osName.startsWith("Win"))//No I18N
	        {
	            //cpuOID = ".1.3.6.1.2.1.25.3.3.1.2";//No I18N
			cpuOID = NmsDataDecoder.hrProcLoadID;
	        }
	        else
	        {
	            //cpuOID = ".1.3.6.1.4.1.2021.10.1.5";//No I18N
			cpuOID = NmsDataDecoder.loLoadInt;
	        }
	        return cpuOID;
	    }
	   

}
