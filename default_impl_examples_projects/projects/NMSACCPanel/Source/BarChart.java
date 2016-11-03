// $Id: BarChart.java,v 1.1 2006/08/29 13:57:01 build Exp $
package com.adventnet.nms.alertui.accpanel;



import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.*;
import org.jfree.data.category.DefaultCategoryDataset;

public class BarChart extends ChartPanelWrapper
{
    boolean isVertical;
    boolean is3DEffect;
    boolean isStacked;

    public BarChart()
    {
        super(ChartFactory.createBarChart(ChartUtil.getString("NMS Bar Chart"), "Category", "Value", null, PlotOrientation.HORIZONTAL, true, true, false));
        //setSampleData();
    }

    public boolean isStacked()
    {
        return isStacked;
    }

    public void setStacked(boolean isStacked)
    {
        this.isStacked = isStacked;
        loadChart(isVertical, is3DEffect, isStacked);
        setSampleData();
    }

    public boolean isVertical()
    {
        return isVertical;
    }

    public void setVertical(boolean isVertical)
    {
        this.isVertical = isVertical;
        loadChart(isVertical, is3DEffect, isStacked);
        setSampleData();
    }

    public boolean is3DEffect()
    {
        return is3DEffect;
    }

    public void set3DEffect(boolean is3DEffect)
	{
		set3DEffect(is3DEffect, this.isVertical);
	}

    public void set3DEffect(boolean is3DEffect, boolean vertical)
    {
        this.is3DEffect = is3DEffect;
        loadChart(vertical, is3DEffect, isStacked);
        setSampleData();
    }

    protected void loadChart(boolean isVertical, boolean is3DEffect, boolean isStacked)
    {
        if(isVertical)
        {
            if(is3DEffect)
            {
                if(isStacked)
                {
                    jfreeChart = ChartFactory.createStackedBarChart3D(ChartUtil.getString("NMS Bar Chart"),"Category", "Value", null,PlotOrientation.VERTICAL, true, true, false);
                }
                else
                {
                    jfreeChart = ChartFactory.createBarChart3D(ChartUtil.getString("NMS Bar Chart"),"Category", "Value", null, PlotOrientation.VERTICAL, true, true, false);
                }
            }
            else
            {
                if(isStacked)
                {
                    jfreeChart = ChartFactory.createStackedBarChart(ChartUtil.getString("NMS Bar Chart"),
                                                                            "Category", "Value", null,PlotOrientation.VERTICAL, true, true, false);
                }
                else
                {
                    jfreeChart = ChartFactory.createBarChart(ChartUtil.getString("NMS Bar Chart"), "Category", "Value", null,PlotOrientation.VERTICAL, true, true, false);
                }
            }
        }
        else
        {
            if(is3DEffect)
            {
                jfreeChart = ChartFactory.createBarChart3D(ChartUtil.getString("NMS Bar Chart"),
                                                                     "Category", "Value", null, PlotOrientation.HORIZONTAL, true, true, false);
            }
            else
            {
                if(isStacked)
                {
                    jfreeChart = ChartFactory.createStackedBarChart(ChartUtil.getString("NMS Bar Chart"),"Category", "Value", null, PlotOrientation.HORIZONTAL, true, true, false);
                }
                else
                {
                    jfreeChart = ChartFactory.createBarChart(ChartUtil.getString("NMS	 Bar Chart"), "Category", "Value", null, PlotOrientation.HORIZONTAL, true, true, false);
                }
            }
        }
        setChart(jfreeChart);
    }

    public void setSampleData()
    {
        if(java.beans.Beans.isDesignTime())
        {
            // row keys...
            String series1 = "First";
            String series2 = "Second";
            String series3 = "Third";

            // column keys...
            String type1 = "Type 1";
            String type2 = "Type 2";
            String type3 = "Type 3";
            String type4 = "Type 4";
            String type5 = "Type 5";
            String type6 = "Type 6";
            String type7 = "Type 7";
            String type8 = "Type 8";

            DefaultCategoryDataset dataset = new DefaultCategoryDataset();

            dataset.addValue(1.0, series1, type1);
            dataset.addValue(4.0, series1, type2);
            dataset.addValue(3.0, series1, type3);
            dataset.addValue(5.0, series1, type4);
            dataset.addValue(5.0, series1, type5);
            dataset.addValue(7.0, series1, type6);
            dataset.addValue(7.0, series1, type7);
            dataset.addValue(8.0, series1, type8);

            dataset.addValue(5.0, series2, type1);
            dataset.addValue(7.0, series2, type2);
            dataset.addValue(6.0, series2, type3);
            dataset.addValue(8.0, series2, type4);
            dataset.addValue(4.0, series2, type5);
            dataset.addValue(4.0, series2, type6);
            dataset.addValue(2.0, series2, type7);
            dataset.addValue(1.0, series2, type8);

            dataset.addValue(4.0, series3, type1);
            dataset.addValue(3.0, series3, type2);
            dataset.addValue(2.0, series3, type3);
            dataset.addValue(3.0, series3, type4);
            dataset.addValue(6.0, series3, type5);
            dataset.addValue(3.0, series3, type6);
            dataset.addValue(4.0, series3, type7);
            dataset.addValue(3.0, series3, type8);
            jfreeChart.getCategoryPlot().setDataset(dataset);
        }
    }
}
