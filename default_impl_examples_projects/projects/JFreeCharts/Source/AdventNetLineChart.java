//$Id:
package com.adventnet.nms.poll.graphs;

//import java classes
import java.awt.Font;
import java.awt.Color;
import java.io.IOException;
import java.util.Hashtable;
import java.text.*;
import java.text.Format;
import java.text.DateFormat;

//import jfreechart classes
import org.jfree.chart.*;
import org.jfree.chart.annotations.XYTextAnnotation;
import org.jfree.chart.axis.*;
import org.jfree.chart.plot.DefaultDrawingSupplier;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

// import AdventNet classes
import com.adventnet.nms.util.NmsClientUtil;

// though named as AdventnetLineChart, this is a generic class for all the charts using the same renderer

public class AdventNetLineChart extends AdventNetBaseChart
{
    Hashtable seriesNameNum = new Hashtable();

    public AdventNetLineChart( int TOC)
    {
         super(AdventNetLineChart.getJFreeChart(TOC));
         seriesNameNum.clear();
         setDefaultLook();
    }

    public AdventNetLineChart(JFreeChart Chart,int TOC)
    {
        super(Chart);
        seriesNameNum.clear();
        setDefaultLook();
        if (TOC==AdventNetBaseChart.LINECHART || TOC==AdventNetBaseChart.SCATTERCHART)
        {
            ((StandardXYItemRenderer)(this.getChart().getXYPlot().getRenderer())).setShapesFilled(true);
        }
    }

    static JFreeChart getJFreeChart(int TOC)
    {
         JFreeChart JFC = null;
         String title  = "Chart";
         String xAxisLabel = "X-Axis";
         String yAxisLabel = "Y-Axis";
         PlotOrientation orientation = PlotOrientation.VERTICAL;
         boolean legend = true;
         boolean tooltips = true;
         boolean urls = false;
          XYSeriesCollection dataset = new XYSeriesCollection();
	  String formatString = "({0}, {1}) = {2}";//No I18N
	  SimpleDateFormat xFormat = new SimpleDateFormat("MM.dd.yyyy HH:mm:ss");//No I18N
	  NumberFormat yFormat = NumberFormat.getInstance();
	  StandardXYToolTipGenerator toolgen = new StandardXYToolTipGenerator(formatString,xFormat,yFormat);
          if(TOC == AdventNetBaseChart.LINECHART)
          {
              JFC = ChartFactory.createXYLineChart(title,xAxisLabel,yAxisLabel,dataset,orientation,legend,tooltips,urls);
              ((XYLineAndShapeRenderer)(JFC.getXYPlot().getRenderer())).setShapesFilled(true);
	      (JFC.getXYPlot().getRenderer()).setToolTipGenerator(toolgen);
          }
          else if(TOC == AdventNetBaseChart.AREACHART)
          {
              JFC = ChartFactory.createXYAreaChart(title,xAxisLabel,yAxisLabel,dataset,orientation,legend,tooltips,urls);
	      (JFC.getXYPlot().getRenderer()).setToolTipGenerator(toolgen);
          }
          else if(TOC == AdventNetBaseChart.SCATTERCHART)
          {
              JFC = ChartFactory.createScatterPlot(title,xAxisLabel,yAxisLabel,dataset,orientation,legend,tooltips,urls);
              ((XYLineAndShapeRenderer)(JFC.getXYPlot().getRenderer())).setShapesFilled(true);
	      (JFC.getXYPlot().getRenderer()).setToolTipGenerator(toolgen);
          }
          else if(TOC == AdventNetBaseChart.XYSTEPCHART)
          {
              JFC = ChartFactory.createXYStepChart(title,xAxisLabel,yAxisLabel,dataset,orientation,legend,tooltips,urls);
	      (JFC.getXYPlot().getRenderer()).setToolTipGenerator(toolgen);
          }
          else  //default line chart
          {

              JFC = ChartFactory.createXYLineChart(title,xAxisLabel,yAxisLabel,dataset,orientation,legend,tooltips,urls);
              ((XYLineAndShapeRenderer)(JFC.getXYPlot().getRenderer())).setShapesFilled(true);
	       (JFC.getXYPlot().getRenderer()).setToolTipGenerator(toolgen);
          }
         return JFC;
    }

    private void setDefaultLook()
    {
         this.getChart().setBackgroundPaint(Color.white);
         this.getChart().getXYPlot().getRangeAxis().setAutoRange(true);
         this.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(0),"",0,2,new Font("Dialog",0,12),new Color(13434880)));
         setDrawingSupplier(new AdventNetDrawingSupplier());

    }

    public void setDrawingSupplier(DefaultDrawingSupplier drawingSupplier)
    {
        this.getChart().getPlot().setDrawingSupplier(drawingSupplier);
    }

    public void setXTimeAxis()
    {
         SimpleDateFormat df = new SimpleDateFormat("MM.dd.yyyy HH:mm:ss");
         DateAxis ds= new DateAxis(NmsClientUtil.GetString("Time"));
         ds.setDateFormatOverride(df);
         this.getChart().getXYPlot().setDomainAxis(ds);
         this.getChart().getXYPlot().getRangeAxis().setStandardTickUnits(NumberAxis.createIntegerTickUnits());
    }

    public void clearChart()
    {
    super.clearChart();
        seriesNameNum.clear();
        ((XYSeriesCollection)(this.getChart().getXYPlot().getDataset())).removeAllSeries();
    }



    // Data for one series at current point of time
    public void setData(String series, double yVal)
    {
         double xVals[] = new double[1];
         long xVal = 0;
         ValueAxis axis = this.getChart().getXYPlot().getDomainAxis();
         if( axis instanceof DateAxis)
         {
             xVal = System.currentTimeMillis();
         }
         xVals[0] = xVal;

         double yVals[] = new double[1];
         yVals[0] = yVal;

         setData(series, xVals, yVals);
    }

    // Data for one series at different point of time
    public void setData(String series, double xVals[], double yVals[])
    {
         String s[] = new String[1];
         double x[][] = new double[1][xVals.length];
         double y[][] = new double[1][yVals.length];
         x[0] = xVals;
         y[0] = yVals;
         setData(s, x, y, true);
    }

    // Data for many series at the same point of time
    public void setData(String series[], double yVals[])
    {
         double x[][] = new double[yVals.length][1];
         double y[][] = new double[yVals.length][1];
         long xVal = 0;
        ValueAxis axis = this.getChart().getXYPlot().getDomainAxis();
         if( axis instanceof DateAxis)
         {
             xVal = System.currentTimeMillis();
         }
         for(int i=0;i<yVals.length;i++)
         {
              y[i][0] = yVals[i];
              x[i][0] = xVal;
         }
         setData(series, x, y, true);
    }

    public void setData(String series[], double xVals[][], double yVals[][], boolean flag)
    {
         this.getChart().getXYPlot().clearAnnotations();
         this.getChart().getXYPlot().getDomainAxis().setAutoRange(true);
         XYSeriesCollection seriesCollection = new XYSeriesCollection();
         if(series!=null)
         {
            int count = series.length;
            for(int i=0;i<count;i++)
            {
                String seriesName = series[i];
        seriesNameNum.put(seriesName,new Integer(i));
                XYSeries seriesData = new XYSeries(seriesName);
                double xValues[] = xVals[i];
                double yValues[] = yVals[i];
        for(int j=0;j<xValues.length;j++)
        {
                    double xValue = xValues[j];
                    double yValue = yValues[j];
                    seriesData.add(xValue, yValue);
        }
        seriesCollection.addSeries(seriesData);
            }
         }
         this.getChart().getXYPlot().setDataset(seriesCollection);
         //fix not to display -1 legend -- Karen
		if ((series.length == 1) && (series[0].equals("-1")))
		{
			this.getChart().removeLegend();
		}
    }

    // Data for one series at current point of time
    public void appendData(String series, double yVal)
    {
         double xVals[] = new double[1];
         long xVal = 0;
         ValueAxis axis = this.getChart().getXYPlot().getDomainAxis();
         if( axis instanceof DateAxis)
         {
             xVal = System.currentTimeMillis();
         }
         xVals[0] = xVal;

         double yVals[] = new double[1];
         yVals[0] = yVal;

         appendData(series, xVals, yVals);
    }

    // Data for one series at different point of time
    public void appendData(String series, double xVals[], double yVals[])
    {
         String s[] = new String[1];
         double x[][] = new double[1][xVals.length];
         double y[][] = new double[1][yVals.length];
         x[0] = xVals;
         y[0] = yVals;
         appendData(s, x, y);
    }


    public void appendData(String series[], double xVals[][], double yVals[][])
    {
         this.getChart().getXYPlot().clearAnnotations();
         this.getChart().getXYPlot().getDomainAxis().setAutoRange(true);
         XYSeriesCollection seriesCollection = (XYSeriesCollection)this.getChart().getXYPlot().getDataset();
         if(series!=null)
         {
            int count = series.length;
            for(int i=0;i<count;i++)
            {
                String seriesName = series[i];
                Integer seriesNum = (Integer)seriesNameNum.get(seriesName);
                if(seriesNum!=null)
                {
                     int seriesInt = seriesNum.intValue();
                     XYSeries seriesData = seriesCollection.getSeries(seriesInt);
                     double xValues[] = xVals[i];
                     double yValues[] = yVals[i];
                     for(int j=0;j<xValues.length;j++)
                     {
                    double xValue = xValues[j];
                    double yValue = yValues[j];
                    seriesData.add(xValue, yValue);
                      }
                }
                else
                {
                     seriesNameNum.put(seriesName,new Integer(seriesNameNum.size()));
                     double xValues[] = xVals[i];
                     double yValues[] = yVals[i];
                     XYSeries seriesData = new XYSeries(seriesName);
             for(int j=0;j<xValues.length;j++)
             {
                    double xValue = xValues[j];
                    double yValue = yValues[j];
                    seriesData.add(xValue, yValue);
             }
            seriesCollection.addSeries(seriesData);
                }
            }
         }
    }

    public void setMessage(String message)
    {
           this.getChart().getXYPlot().clearAnnotations();
          ValueAxis xAxis = this.getChart().getXYPlot().getDomainAxis();
          xAxis.setLowerBound(0);
          xAxis.setUpperBound(message.length()+1);
          XYTextAnnotation annotation = new XYTextAnnotation(message,message.length()/2,0.5);
          annotation.setFont(new Font("Dialog", Font.BOLD, 12));
          this.getChart().getXYPlot().addAnnotation(annotation);
    }

    public void setAxisLabels(String xLabel, String yLabel)
    {
          ValueAxis xAxis = this.getChart().getXYPlot().getDomainAxis();
           ValueAxis yAxis = this.getChart().getXYPlot().getRangeAxis();
           xAxis.setLabel(xLabel);
           yAxis.setLabel(yLabel);
    }
}
