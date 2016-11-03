// $Id: CategoryAlarmChart.java,v 1.1 2006/08/29 13:57:01 build Exp $

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
import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.chart.entity.*;
import org.jfree.data.general.*;
import java.awt.event.*;
import com.adventnet.nms.util.NmsClientUtil;

public class CategoryAlarmChart extends JPanel  implements   ACCPanelChartInterface,com.adventnet.apiutils.ParameterChangeListener
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	com.adventnet.nms.alertui.accpanel.PieChart categoryChart = null;
	private com.adventnet.apiutils.ParameterObject po= null;
	//<End_Variable_Declarations>



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
            categoryChart.set3Deffect(true);
            categoryChart.set3DPlotDepthFactor((double)0.1);
            categoryChart.setBackground(new Color(-1));
            //categoryChart.setLegendOutlineColor(new Color(-1));
            categoryChart.setPlotOutlineColor(new Color(-1));
            //categoryChart.setPlotSectionLabelType(2);
            //categoryChart.setPlotSeriesLabelFont(new Font("Verdana",1,11));
            categoryChart.setTitle((org.jfree.chart.title.TextTitle) null);
            categoryChart.setFont(NmsClientUtil.getFont());
          }
          catch(Exception ex)
          {
             showStatus("Exception while setting properties for bean "+categoryChart,ex);
          }

//<UserCode_Begin_Bean_categoryChart>

            //categoryChart.setPlotSeriesLabelFont(new Font(NmsClientUtil.getFont().getName(),1,11));

//<UserCode_End_Bean_categoryChart>


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
        categoryChart= new com.adventnet.nms.alertui.accpanel.PieChart();
        initializeParameters();


          //<End_initVariables>
	}
	public void setUpGUI(Container container)
  {
		//<Begin_setUpGUI_Container>
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(0,0));
Top.add(categoryChart,BorderLayout.CENTER);
categoryChart.setLayout(new FlowLayout(1,5,5));


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





	public CategoryAlarmChart()
  {
		//<Begin_CategoryAlarmChart>
    this.init();

    //<End_CategoryAlarmChart>
	}

	public CategoryAlarmChart(java.applet.Applet applet)
  {
		//<Begin_CategoryAlarmChart_java.applet.Applet>
    this.applet = applet;
    this.init();

    //<End_CategoryAlarmChart_java.applet.Applet>
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
		Properties categoryCount = new Properties();
		for(Enumeration enu = vect.elements();enu.hasMoreElements();)
		{
			Properties severityProp = (Properties) enu.nextElement();
			String category = severityProp.getProperty("category");
			if(category == null) break;
			if(!category.equals("Totals"))
			{
				int total = Integer.parseInt(severityProp.getProperty("total"));
				categoryCount.setProperty(category, total+"");
			}
		}
		DefaultPieDataset data = new DefaultPieDataset();
		for(Enumeration enu = categoryCount.propertyNames();enu.hasMoreElements();)
		{
			String category = enu.nextElement().toString();
			int origiValue = Integer.parseInt(categoryCount.getProperty(category));
			data.setValue(category, new Double(origiValue));
		}
		((PiePlot)categoryChart.getPlot()).setDataset(data);
	}

	public boolean isHandCursorNeeded(int x, int y)
	{
		boolean needed = false;
		ChartEntity secEntity = (ChartEntity) categoryChart.getEntityForPoint(x, y);
		if(secEntity != null && secEntity instanceof PieSectionEntity)
		{
			needed = true;
		}
		return needed;
	}

	public String getCategory(int x, int y)
	{
		String cat = null;
		try
		{
			ChartEntity secEntity = categoryChart.getEntityForPoint(x, y);
			if(secEntity != null && secEntity instanceof PieSectionEntity)
			{
				PieSectionEntity section = (PieSectionEntity) secEntity;
				cat = section.toString();
			}
		}
		catch(Exception e)
		{
		}
		return cat;
	}

	public int getSeverity(int x, int y)
	{
		int sev = -1;
		return sev;
	}

	public Component getOriginalComponent()
	{
		return categoryChart;
	}
   public void initialize(Vector v){
        }

}

