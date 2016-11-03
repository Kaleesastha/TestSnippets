//$Id:
 package com.adventnet.nms.poll.graphs;


 import java.awt.*;
 import java.awt.event.*;
 import javax.swing.*;
 import javax.swing.event.*;

 //import java util classes
 import java.util.*;
 import java.text.SimpleDateFormat;

 //import jfree chart classes
import org.jfree.data.general.DatasetUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryAxis3D;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.AxisState;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.plot.DefaultDrawingSupplier;
import org.jfree.chart.annotations.CategoryTextAnnotation;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.CategoryTick;

// import AdventNet classes
import com.adventnet.nms.util.NmsClientUtil;



public class AdventNetBarChart extends AdventNetBaseChart
 {
	static double[][] data = {};

	 Hashtable seriesNameNum = new Hashtable();
	 public AdventNetBarChart()
	 {
	      super(ChartFactory.createBarChart3D("Bar Chart", "X-Axis", "Y-Axis",
	      				DatasetUtilities.createCategoryDataset("Category", "value" , data),
	      				PlotOrientation.VERTICAL, true, true, false));
	      	setDefaultLook();
	 }

	private void setDefaultLook()
    {
         this.getChart().setBackgroundPaint(Color.white);
         this.getChart().getCategoryPlot().getRangeAxis().setAutoRange(true);
         this.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(0),"",0,2,new Font("Dialog",0,12),new Color(-13434880)));
         setDrawingSupplier(new AdventNetDrawingSupplier());
//      ((StandardXYItemRenderer)(this.getChart().getXYPlot().getRenderer())).setPlotShapes(true);
    }

    public void setDrawingSupplier(DefaultDrawingSupplier drawingSupplier)
    {
        this.getChart().getPlot().setDrawingSupplier(drawingSupplier);
    }



  public void setAxisLabels(String xLabel, String yLabel)
  {
       CategoryAxis cAxis = this.getChart().getCategoryPlot().getDomainAxis();
       ValueAxis vAxis = this.getChart().getCategoryPlot().getRangeAxis();
       cAxis.setLabel(xLabel);
       vAxis.setLabel(yLabel);
       //cAxis.setSkipCategoryLabelsToFit(true);

  }

  public  void setXTimeAxis()
    {
         //this.getChart().getCategoryPlot().setDomainAxis(new CategoryAxis(NmsClientUtil.GetString("Time")));
         //Changed to draw bar chart label at angle -- Karen
         SkipLblCategoryAxis categoryAxis = new SkipLblCategoryAxis(NmsClientUtil.GetString("Time"));
		 categoryAxis.setSkipLabel(true);
		 categoryAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
         this.getChart().getCategoryPlot().setDomainAxis ((CategoryAxis)categoryAxis);
         //end change
         this.getChart().getCategoryPlot().getRangeAxis().setStandardTickUnits(NumberAxis.createIntegerTickUnits());
    }

    //added to skip categoryLabels -- Karen
    public class SkipLblCategoryAxis extends CategoryAxis3D
    {
        private boolean skipLabel = true;

        public SkipLblCategoryAxis(String name)
        {
            super(name);
        }

        public void setSkipLabel(boolean skplbl)
        {
            skipLabel = skplbl;
        }

        public boolean getSkipLabel()
        {
            return skipLabel;
        }


        public java.util.List refreshTicks(java.awt.Graphics2D g2,
                                   AxisState state,
                                   java.awt.geom.Rectangle2D dataArea,
                                   org.jfree.ui.RectangleEdge edge)
        {
            java.util.List allTicks = super.refreshTicks(g2, state, dataArea, edge);
            if(!skipLabel)
                return allTicks;
            else
            {
                //code for skipping the labels goes here
                java.util.List toRet = new java.util.ArrayList();
                int cnt = allTicks.size();
                if (cnt<=5)
                   return allTicks;

                int interval = cnt/5;
                for(int i = 0; i<cnt;)
                {
                    toRet.add(allTicks.get(i));
                    i=i+interval;
                }
                return toRet;
            }
        }


    }
    //end

    public  void setMessage(String message)
    {
          //CategoryAxis xAxis = this.getChart().getCategoryPlot().getDomainAxis();
          //xAxis.setLowerMargin(0);
          //xAxis.setUpperMargin(message.length()+1);
	  this.getChart().getCategoryPlot().setNoDataMessage(message);
	  this.getChart().getCategoryPlot().setNoDataMessageFont(new Font("Dialog", Font.BOLD, 12));//No I18N

          //CategoryTextAnnotation annotation = new CategoryTextAnnotation(message,"Category", 0.5);
          //annotation.setFont(new Font("Dialog", Font.BOLD, 12));
          //this.getChart().getCategoryPlot().addAnnotation(annotation);
    }

    public  void clearChart()
    {
		super.clearChart();
         DefaultCategoryDataset dataset = new DefaultCategoryDataset();
         this.getChart().getCategoryPlot().setDataset(dataset);
    }

    public void setData(String[] series, double xVals[][], double yVals[][], boolean flag)
    {
		this.getChart().getCategoryPlot().getRangeAxis().setAutoRange(true);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
         if(series!=null)
         {
         	int count = series.length;
         	for(int i=0;i<count;i++)
         	{
         	 	String seriesName = series[i];
         	 	double xValues[] = xVals[i];
         	 	double yValues[] = yVals[i];
         	 	for(int j=0;j<xValues.length;j++)
		{
         	 		double xValue = xValues[j];
					String date = null;
					if(flag)
					{
         	 			date = convertDateValue(xValue, "MM.dd.yyyy H:mm");
					}
					else
					{
						date = convertDateValue(xValue, "MM.dd.yyyy H:mm:ss");
					}
         	 		double yValue = yValues[j];
         	 		dataset.addValue(yValue, seriesName, date);
		}
         	}
         }
         this.getChart().getCategoryPlot().setDataset(dataset);
         //fix not to display -1 legend -- Karen
         if ((series.length == 1) && (series[0].equals("-1")))
         {
         	this.getChart().removeLegend();
		}
    }

    private String convertDateValue(double xValue, String Dateformat)
    {
         long value = (long)xValue;
         Date date = new Date(value);
         SimpleDateFormat format = new SimpleDateFormat(Dateformat);
         return format.format(date);

    }

    public  void appendData(String series[], double xVals[][], double[][] yVals)
    {
         this.getChart().getCategoryPlot().getRangeAxis().setAutoRange(true);
         DefaultCategoryDataset dataset = (DefaultCategoryDataset)this.getChart().getCategoryPlot().getDataset();
         if(series!=null)
         {
         	int count = series.length;
         	for(int i=0;i<count;i++)
         	{
         	 	String seriesName = series[i];
         	 	double xValues[] = xVals[i];
         	 	double yValues[] = yVals[i];
         	 	for(int j=0;j<xValues.length;j++)
		{
         	 		double xValue = xValues[j];
         	 		String date = convertDateValue(xValue, "MM.dd.yyyy H:mm:ss");
         	 		double yValue = yValues[j];
         	 		dataset.addValue(yValue, seriesName, date);
		}
         	}
         }
         this.getChart().getCategoryPlot().setDataset(dataset);

    }
 }




