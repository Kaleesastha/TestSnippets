// $Id: PieChart.java,v 1.1 2006/08/29 13:57:01 build Exp $
package com.adventnet.nms.alertui.accpanel;
import java.awt.*;


import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data .*;
import org.jfree.data .general.*;
import org.jfree.chart.labels.StandardPieToolTipGenerator;

public class PieChart extends ChartPanelWrapper
{
    private boolean is3Deffect;

    public PieChart()
    {
        this(false);
    }

    public PieChart(boolean is3Deffect)
    {
        super((is3Deffect) ?
              ChartFactory.createPieChart3D("NMS Pie Chart", null, true, true,false)
              :
              ChartFactory.createPieChart("NMS Pie Chart", null, true, true,false)
              );
        this.is3Deffect = is3Deffect;
        setSampleData();
    }

    public void setSampleData()
    {
        if(java.beans.Beans.isDesignTime())
        {
            DefaultPieDataset data = new DefaultPieDataset();
            data.setValue("others", new Double(20));
            data.setValue("Linux", new Double(120));
            data.setValue("Node", new Double(420));
            data.setValue("SUN", new Double(10));
            data.setValue("Switch", new Double(7));
            data.setValue("Windows", new Double(190));
            ((PiePlot)jfreeChart.getPlot()).setDataset(data);
        }
    }

    public boolean is3Deffect()
    {
        return is3Deffect;
    }

    public void set3Deffect(boolean is3Deffect)
    {
        this.is3Deffect = is3Deffect;
        if(is3Deffect)
        {
            jfreeChart = ChartFactory.createPieChart3D("NMS Pie Chart", ((PiePlot)jfreeChart.getPlot()).getDataset() , true, true,false);
            setChart(jfreeChart);
        }
        else
        {
            jfreeChart = ChartFactory.createPieChart("NMS Pie Chart", ((PiePlot)jfreeChart.getPlot()).getDataset() , true, true,false);
            setChart(jfreeChart);
        }
    }

    public java.awt.Color getPlotDefaultOutlineColor()
    {
        return (Color) (((PiePlot) jfreeChart.getPlot()).DEFAULT_OUTLINE_PAINT);
    }

    public java.awt.Stroke getPlotDefaultOutlineStroke()
    {
        return ((PiePlot) jfreeChart.getPlot()).DEFAULT_OUTLINE_STROKE;
    }

    public java.awt.Color getPlotDefaultColor()
    {
        return (Color) (((PiePlot) jfreeChart.getPlot()).getBaseSectionPaint());
    }

    /*public int getPlotDirection()
    {
        return ((PiePlot) jfreeChart.getPlot()).getDirection();
    }

    public double getPlotInteriorGap()
    {
        return ((PiePlot) jfreeChart.getPlot()).getInteriorGap();
    }
*/
    public PieDataset getPlotPieDataset()
    {
        return ((PiePlot) jfreeChart.getPlot()).getDataset();
    }

/*    public double getPlotRadius()
    {
        return ((PiePlot) jfreeChart.getPlot()).getRadius();
    }*/

    public java.awt.Font getPlotSectionLabelFont()
    {
        return ((PiePlot) jfreeChart.getPlot()).getLabelFont();
    }

    public double getPlotSectionLabelGap()
    {
        return ((PiePlot) jfreeChart.getPlot()).getLabelGap();
    }

    public java.awt.Color getPlotSectionLabelColor()
    {
        return (Color) (((PiePlot) jfreeChart.getPlot()).getLabelPaint());
    }

/*    public int getPlotSectionLabelType()
    {
        return ((PiePlot) jfreeChart.getPlot()).getSectionLabelType();
    }

    public java.awt.Font getPlotSeriesLabelFont()
    {
        return ((PiePlot) jfreeChart.getPlot()).getSeriesLabelFont();
    }

    public java.awt.Color getPlotSeriesLabelColor()
    {
        return (Color) (((PiePlot) jfreeChart.getPlot()).getSeriesLabelPaint());
    }

    public boolean getPlotShowSeriesLabels()
    {
        return ((PiePlot) jfreeChart.getPlot()).getShowSeriesLabels();
    }*/

    public double getPlotStartAngle()
    {
        return ((PiePlot) jfreeChart.getPlot()).getStartAngle();
    }

    public boolean isCircular()
    {
        return ((PiePlot) jfreeChart.getPlot()).isCircular();
    }

    /*public boolean isOutlineColorTableActive()
    {
        return ((PiePlot) jfreeChart.getPlot()).isOutlinePaintTableActive();
    }

    public boolean isOutlineStrokeTableActive()
    {
        return ((PiePlot) jfreeChart.getPlot()).isOutlineStrokeTableActive();
    }

    public boolean isColorTableActive()
    {
        return ((PiePlot) jfreeChart.getPlot()).isPaintTableActive();
    }*/

    public double get3DPlotDepthFactor()
    {
        PiePlot piePlot = (PiePlot) jfreeChart.getPlot();
        if(piePlot instanceof PiePlot3D)
        {
            return ((PiePlot3D) piePlot).getDepthFactor();
        }
        else
        {
            return 0.0;
        }
    }



    public void set3DPlotDepthFactor(double newDepthFactor)
    {
        PiePlot piePlot = (PiePlot) jfreeChart.getPlot();
        if(piePlot instanceof PiePlot3D)
        {
            ((PiePlot3D) piePlot).setDepthFactor(newDepthFactor);
        }
        repaint();
    }

    public void setPlotCircular(boolean flag)
    {
        ((PiePlot) jfreeChart.getPlot()).setCircular(flag);
    }

/*    public void setPlotDefaultOutlineColor(java.awt.Color color)
    {
        ((PiePlot) jfreeChart.getPlot()).setDefaultOutlinePaint(color);
    }

    public void setPlotDefaultOutlineStroke(java.awt.Stroke stroke)
    {
        ((PiePlot) jfreeChart.getPlot()).setDefaultOutlineStroke(stroke);
    }

    public void setPlotDefaultColor(java.awt.Color color)
    {
        ((PiePlot) jfreeChart.getPlot()).setDefaultPaint(color);
    }

    public void setPlotDirection(int direction)
    {
        ((PiePlot) jfreeChart.getPlot()).setDirection(direction);
    }*/

    public void setPlotInteriorGap(double percent)
    {
        ((PiePlot) jfreeChart.getPlot()).setInteriorGap(percent);
    }

    /*public void setPlotOutlineColorTableActive(boolean active)
    {
        ((PiePlot) jfreeChart.getPlot()).setOutlinePaintTableActive(active);
    }

    public void setPlotOutlineStrokeTableActive(boolean active)
    {
        ((PiePlot) jfreeChart.getPlot()).setOutlineStrokeTableActive(active);
    }

    public void setPlotColorTableActive(boolean active)
    {
        ((PiePlot) jfreeChart.getPlot()).setPaintTableActive(active);
    }

    public void setPlotPercentFormat(java.text.NumberFormat format)
    {
        ((PiePlot) jfreeChart.getPlot()).setPercentFormat(format);
    }

    public void setPlotPercentFormatString(java.lang.String format)
    {
        ((PiePlot) jfreeChart.getPlot()).setPercentFormatString(format);
    }

    public void setPlotRadius(double percent)
    {
        ((PiePlot) jfreeChart.getPlot()).setRadius(percent);
    }*/

    public void setPlotSectionLabelFont(java.awt.Font font)
    {
        ((PiePlot) jfreeChart.getPlot()).setLabelFont(font);
    }

    public void setPlotSectionLabelGap(double percent)
    {
        ((PiePlot) jfreeChart.getPlot()).setLabelGap(percent);
    }

    public void setPlotSectionLabelColor(java.awt.Color color)
    {
        ((PiePlot) jfreeChart.getPlot()).setLabelPaint(color);
    }

/*    public void setPlotSectionLabelType(int type)
    {
        ((PiePlot) jfreeChart.getPlot()).setSectionLabelType(type);
    }

    public void setPlotSeriesLabelFont(java.awt.Font font)
    {
        ((PiePlot) jfreeChart.getPlot()).setSeriesLabelFont(font);
    }

    public void setPlotSeriesLabelColor(java.awt.Color color)
    {
        ((PiePlot) jfreeChart.getPlot()).setSeriesLabelPaint(color);
    }

    public void setPlotShowSeriesLabels(boolean flag)
    {
        ((PiePlot) jfreeChart.getPlot()).setShowSeriesLabels(flag);
    }*/

    public void setPlotStartAngle(double angle)
    {
        ((PiePlot) jfreeChart.getPlot()).setStartAngle(angle);
    }

/*    public void setPlotValueFormat(java.text.NumberFormat format)
    {
        ((PiePlot) jfreeChart.getPlot()).setValueFormat(format);
    }

    public void setPlotValueFormatString(java.lang.String format)
    {
        ((PiePlot) jfreeChart.getPlot()).setValueFormatString(format);
    }*/

    public void setPaint( int i, Paint pnt )
    {
        ((PiePlot) jfreeChart.getPlot()).setSectionPaint( i, pnt);
    }

    public void setToolTipGenerator(StandardPieToolTipGenerator generator)
    {
        ((PiePlot) jfreeChart.getPlot()).setToolTipGenerator( generator );
    }
    /**
       PieToolTipGenerator getToolTipGenerator()
       void setToolTipGenerator(PieToolTipGenerator generator)
       PieURLGenerator getURLGenerator()
       void setURLGenerator(PieURLGenerator generator)
       java.util.Collection getKeys()
       LegendItemCollection getLegendItems()
       java.lang.String getPlotType()
    **/
}



