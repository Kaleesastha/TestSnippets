// $Id: SeverityAlarmChart.java,v 1.1.6.1 2012/01/25 05:12:45 karen.r Exp $

// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// Kindly refer to tcats_edit_source_files.html file under help/source_generation directory for details

//<Begin_Version>
//version$3
//<End_Version>
package com.adventnet.nms.alertui.accpanel;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

import com.adventnet.nms.severity.*;



import org.jfree.data.*;
import org.jfree.data.general.*;
import org.jfree.chart.*;
import org.jfree.chart.title.*;
import org.jfree.chart.plot.*;
import org.jfree.chart.entity.*;
import java.awt.event.*;
import com.adventnet.nms.util.NmsClientUtil;

public class SeverityAlarmChart extends JPanel  implements   ACCPanelChartInterface,com.adventnet.apiutils.ParameterChangeListener
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	com.adventnet.nms.alertui.accpanel.PieChart severityChart = null;
	private com.adventnet.apiutils.ParameterObject po= null;
	//<End_Variable_Declarations>
	private Vector severityList=null;
	private String categoryList=null;





	public void stop()
  {
		//<Begin_stop>
       if(!running)
 return;
 running=false;


       //<End_stop>
	}
	public void start()
  {
		//<Begin_start>
       if(running)
 return;
 running=true;


       //<End_start>
	}
	public void init()
  {

		//<Begin_init>
        if (initialized) return;
        setPreferredSize(new Dimension(getPreferredSize().width+250,getPreferredSize().height+200));
        setSize(getPreferredSize());
        Container container = this;
        container.setLayout(new BorderLayout());
        try
        {
          initVariables();
          setUpGUI(container);
          setUpProperties();
          setUpConnections();
        }
        catch(Exception ex)
        {
          showStatus("Error in init method",ex);
        }
        // let us set the initialized variable to true so
        // we dont initialize again even if init is called
        initialized = true;


        //<End_init>
	}
	public String getParameter(String input)
  {
		//<Begin_getParameter_String>
	if (po != null)
	 {
	   if(po.getParameter(input) != null)
	     {
	       return (String)po.getParameter(input);
	     }
	 }
           String value = null;
           if ( applet != null)
           {
                 value = applet.getParameter(input);
           }
           else
           {
                 value = (String)com.adventnet.apiutils.Utility.getParameter(input);
           }
           if(value == null)
           {
            }
        return value;


         //<End_getParameter_String>
	}
	public void setUpProperties()
  {
		//<Begin_setUpProperties>

          try
          {
            severityChart.set3Deffect(true);
            severityChart.set3DPlotDepthFactor((double)0.1);
            severityChart.setBackground(new Color(-1));
            //severityChart.setLegendOutlineColor(new Color(-1));
            severityChart.setPlotOutlineColor(new Color(-1));
            //severityChart.setPlotSectionLabelType(2);
            severityChart.setTitle((org.jfree.chart.title.TextTitle) null);
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+severityChart,ex);
          }

//<UserCode_Begin_Bean_severityChart>


//<UserCode_End_Bean_severityChart>


          //<End_setUpProperties>
	}
	public void initVariables()
  {
		//<Begin_initVariables>
	 if(po == null)
	{
	po = new com.adventnet.apiutils.ParameterObject();
	}
        Top= new javax.swing.JPanel();
        severityChart= new com.adventnet.nms.alertui.accpanel.PieChart();
        initializeParameters();


          //<End_initVariables>
	severityList=new Vector();
	}
	public void setUpGUI(Container container)
  {
		//<Begin_setUpGUI_Container>
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(0,0));
Top.add(severityChart,BorderLayout.CENTER);
severityChart.setLayout(new FlowLayout(1,5,5));


//<End_setUpGUI_Container>
	}
	public void setUpConnections()
  {
		//<Begin_setUpConnections>


  //<End_setUpConnections>
	}




	public void showStatus(String message)
  {
		//<Begin_showStatus_String>
     System.out.println("Internal Error :"+ message);
     //<End_showStatus_String>
	}
	public void showStatus(String message,Exception ex)
  {
		//<Begin_showStatus_String_Exception>
     System.out.println("Internal Error :"+ message);
     ex.printStackTrace();
     //<End_showStatus_String_Exception>
	}


	public SeverityAlarmChart()
  {
		this.init();

		//<Begin_SeverityAlarmChart>
    this.init();

    //<End_SeverityAlarmChart>
	}

	public SeverityAlarmChart(java.applet.Applet applet)
  {
		//<Begin_SeverityAlarmChart_java.applet.Applet>
    this.applet = applet;
    this.init();

    //<End_SeverityAlarmChart_java.applet.Applet>
	}
	public void setProperties(java.util.Properties props)
  {
		//<Begin_setProperties_java.util.Properties>
	if(po != null)
	{
	po.setParameters(props);
	}

         //<End_setProperties_java.util.Properties>
	}
	private void initializeParameters()
  {
		//<Begin_initializeParameters>
	 if(po != null)
	   {
	    po.addParameterChangeListener(this);
	   }


          //<End_initializeParameters>
	}
	public void destroy()
  {
		//<Begin_destroy>
	if(po != null)
	{
	 po.removeParameterChangeListener(this);
	}

         //<End_destroy>
	}
	public void setParameterObject(com.adventnet.apiutils.ParameterObject paramObj)
  {
		//<Begin_setParameterObject_com.adventnet.apiutils.ParameterObject>
	this.po=paramObj;
	initializeParameters();

         //<End_setParameterObject_com.adventnet.apiutils.ParameterObject>
	}
	public void parameterChanged(com.adventnet.apiutils.ParameterObject paramObj)
  {
		//<Begin_parameterChanged_com.adventnet.apiutils.ParameterObject>


  //<End_parameterChanged_com.adventnet.apiutils.ParameterObject>
	}

	public void updateCounts(Vector vect, SeverityInfo sevInfo)
	{
		if(!plotPieChartFor(vect,sevInfo,"Totals")){
			plotPieChartFor(vect,sevInfo,"Categories-Total");
		}
	}

	private boolean plotPieChartFor(Vector vect,SeverityInfo sevInfo,String cat){
		for(Enumeration enu = vect.elements();enu.hasMoreElements();)
		{
			Properties severityProp = (Properties) enu.nextElement();
			String category = severityProp.getProperty("category");
			if(category == null) break;
			if(category.equals(cat))
			{
				updateSeverityPieChart(severityProp, sevInfo);
				categoryList=getAllCategories(vect);
				return true;
			}
		}
		return false;

	}

	private String getAllCategories(Vector vect){
            String categories=null;
		for(Enumeration enu = vect.elements();enu.hasMoreElements();)
		{
			Properties severityProp = (Properties) enu.nextElement();
			String category = severityProp.getProperty("category");
			if(category.equals("Totals")||category.equals("Categories-Total")){
				continue;
			}
			if(categories==null){
				categories=category;
			}
			else{
				categories=categories+","+category;
			}
		}
		return categories;
	}


	public void updateSeverityPieChart(Properties severityProp, SeverityInfo sevInfo)
	{
		Hashtable sevVsCount=new Hashtable();
		DefaultPieDataset data = new DefaultPieDataset();
		for(Enumeration enu = severityProp.propertyNames();enu.hasMoreElements();)
		{
			String severity = enu.nextElement().toString();
			if(severity.equals("category") || severity.equals("total"))
			{
				continue;
			}
			String value=severityProp.getProperty(severity);
			sevVsCount.put(severity,value);
		}
		int sevSize=severityList.size();
		for(int i=0;i<sevSize;i++)
		{
			String sevName=severityList.elementAt(i).toString();
			String value=sevVsCount.get(sevName).toString();
			int val = Integer.parseInt(value);
			Color sevColor = sevInfo.getColor(sevName);
			data.setValue(sevName, new Double(val));
			((PiePlot) severityChart.getPlot()).setSectionPaint(i, sevColor);
		}
		//NmsClientUtil.dbg("Setting severity data");
		((PiePlot)severityChart.getPlot()).setDataset(data);
	}

	public boolean isHandCursorNeeded(int x, int y)
	{
		boolean needed = false;
		ChartEntity secEntity = (ChartEntity) severityChart.getEntityForPoint(x, y);
		if(secEntity != null && secEntity instanceof PieSectionEntity)
		{
			needed = true;
		}
		return needed;
	}

	public String getCategory(int x, int y)
	{
		 try
                {
                        ChartEntity secEntity = severityChart.getEntityForPoint(x, y);
                        if(secEntity != null && secEntity instanceof PieSectionEntity)
                        {
				return categoryList;

                        }
                }
                catch(Exception e)
                {
                }
		return null;

	}

	public int getSeverity(int x, int y)
	{
		int sev = -1;
		try
		{
			ChartEntity secEntity = severityChart.getEntityForPoint(x, y);
			if(secEntity != null && secEntity instanceof PieSectionEntity)
			{
				PieSectionEntity section = (PieSectionEntity) secEntity;
				int series  = section.getSectionIndex();
	  	                String sevName=(String)severityList.elementAt(series);
                                sev = NmsClientUtil.severityInfo.getValue(sevName);

			}
		}
		catch(Exception e)
		{
		}
		return sev;
	}
	public Component getOriginalComponent()
	{
		return severityChart;
	}
	public void initialize(Vector v){
		this.severityList=v;
	}
}


