// $Id: ChartPanelWrapper.java,v 1.1 2006/08/29 13:57:01 build Exp $

package com.adventnet.nms.alertui.accpanel;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



import org.jfree.chart.*;
import org.jfree.chart.title.*;
import org.jfree.chart.renderer.*;
import org.jfree.chart.event.*;
import org.jfree.chart.plot.*;
import org.jfree.data.*;
import org.jfree.data.general.*;
import org.jfree.chart.editor.*;
import org.jfree.ui.*;
//import org.jfree.chart.ui.ChartPropertyEditPanel;

import com.adventnet.nms.util.NmsClientUtil;
public abstract class ChartPanelWrapper extends ChartPanel
{
	protected JFreeChart jfreeChart;

	public ChartPanelWrapper(JFreeChart jfc)
	{
		super(jfc);
		initDefaultChartProps(jfc);
		initDefaultChartPanelProps();
	}

	public void initDefaultChartProps(JFreeChart jfc)
	{
		jfreeChart = jfc;
		jfreeChart.setBackgroundPaint(super.getBackground());
		setPlotOutlineStroke(new BasicStroke((float)0.1));
		//jfreeChart.getTitle().setFont(super.getFont());
		jfreeChart.getTitle().setPaint(super.getForeground());
		setFont(new Font(NmsClientUtil.getFont().getName(), Font.PLAIN, 16));

		NmsDrawingSupplier drawingSupplier = new NmsDrawingSupplier();
		Plot plot = jfreeChart.getPlot();
		if(plot instanceof XYPlot)
		{
			((XYPlot) plot).setDrawingSupplier(drawingSupplier);
		}
		else if(plot instanceof CategoryPlot)
		{
			((CategoryPlot) plot).setDrawingSupplier(drawingSupplier);
		}
	}

	public void initDefaultChartPanelProps()
	{
		//JPopupMenu popMenu = getPopupMenu();
		setPopupMenu(null);
		setMouseZoomable(true);
		setPreferredSize(new Dimension(150,150));
		MouseAdapter doubleClickListener = new MouseAdapter()
		{
			public void mouseClicked(MouseEvent me)
			{
				if(me.getClickCount() == 2 && !ChartUtil.DEBUG)
				{
					ChartEditor panel = ChartEditorManager.getChartEditor(jfreeChart);
					int result = JOptionPane.showConfirmDialog(ChartPanelWrapper.this, panel,"Chart Properties", JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
					if (result == JOptionPane.OK_OPTION)
					{
						panel.updateChart(jfreeChart);
					}
				}
			}
		};
		addMouseListener(doubleClickListener);
	}

	public void setChartSize(int width, int height)
	{
		setPreferredSize(new Dimension(width, height));
//System.out.println("settingsize");
	}

	public Dimension getChartSize()
	{
		return getPreferredSize();
	}

	public void setChart(JFreeChart jfc)
	{
		super.setChart(jfc);
		initDefaultChartProps(jfc);
		initDefaultChartPanelProps();
	}

	public void setForeground(java.awt.Color color)
	{
		super.setForeground(color);
		if(jfreeChart != null)
		{
			//setTitleColor(color);
		}
	}

	public void setBackground(java.awt.Color color)
	{
		super.setBackground(color);
		if(jfreeChart != null)
		{
			jfreeChart.setBackgroundPaint(color);
		}
	}

	public void setFont(Font font)
	{
		super.setFont(font);
		if(jfreeChart != null)
		{
			setTitleFont(font);
		}
	}

	public void setBackground(java.awt.Paint paint)
	{
		if(jfreeChart != null)
		{
			jfreeChart.setBackgroundPaint(paint);
		}
	}

	public Image getChartAsImage()
	{
		java.awt.image.BufferedImage bi = jfreeChart.createBufferedImage(386, 264);
		return bi;
	}
	/**  Methods needed to change Chart Legend -  BEGIN    **/

	public java.awt.Color getLegendBackgroundColor()
	{
		return (Color) ((LegendTitle) jfreeChart.getLegend()).getBackgroundPaint();
	}

	public java.awt.Font getLegendItemFont()
	{
		return ((LegendTitle) jfreeChart.getLegend()).getItemFont();
	}

	public java.awt.Color getLegendItemColor()
	{
		return (Color) ((LegendTitle) jfreeChart.getLegend()).getItemPaint();
	}

	/*public Spacer getLegendOuterGap()
	{
		return ((LegendTitle) jfreeChart.getLegend()).getOuterGap();
	}

	public java.awt.Color getLegendOutlineColor()
	{
		return (Color) ((LegendTitle) jfreeChart.getLegend()).getOutlinePaint();
	}

	public boolean getLegendOutlineShapes()
	{
		return ((StandardLegend) jfreeChart.getLegend()).getOutlineShapes();
	}

	public java.awt.Stroke getLegendOutlineStroke()
	{
		return ((StandardLegend) jfreeChart.getLegend()).getOutlineStroke();
	}

	public java.awt.Color getLegendShapeOutlineColor()
	{
		return (Color) ((StandardLegend) jfreeChart.getLegend()).getShapeOutlinePaint();
	}

	public java.awt.Stroke getLegendShapeOutlineStroke()
	{
		return ((StandardLegend) jfreeChart.getLegend()).getShapeOutlineStroke();
	}

	public java.lang.String getLegendTitle()
	{
		return ((StandardLegend) jfreeChart.getLegend()).getTitle();
	}

	public java.awt.Font getLegendTitleFont()
	{
		return ((StandardLegend) jfreeChart.getLegend()).getTitleFont();
	}*/

	public void setLegendBackgroundColor(java.awt.Color color)
	{
		((LegendTitle) jfreeChart.getLegend()).setBackgroundPaint(color);
	}

	/*public void setLegendDisplaySeriesShapes(boolean flag)
	{
		((StandardLegend) jfreeChart.getLegend()).setDisplaySeriesShapes(flag);
	}*/

	public void setLegendItemFont(java.awt.Font font)
	{
		((LegendTitle) jfreeChart.getLegend()).setItemFont(font);
	}

	public void setLegendItemColor(java.awt.Color color)
	{
		((LegendTitle) jfreeChart.getLegend()).setItemPaint(color);
	}

	/*public void setLegendOutlineColor(java.awt.Color color)
	{
		((StandardLegend) jfreeChart.getLegend()).setOutlinePaint(color);
	}

	public void setLegendOutlineShapes(boolean flag)
	{
		((StandardLegend) jfreeChart.getLegend()).setOutlineShapes(flag);
	}

	public void setLegendOutlineStroke(java.awt.Stroke stroke)
	{
		((StandardLegend) jfreeChart.getLegend()).setOutlineStroke(stroke);
	}

	public void setLegendShapeOutlineColor(java.awt.Color color)
	{
		((StandardLegend) jfreeChart.getLegend()).setShapeOutlinePaint(color);
	}

	public void setLegendShapeOutlineStroke(java.awt.Stroke stroke)
	{
		((StandardLegend) jfreeChart.getLegend()).setShapeOutlineStroke(stroke);
	}

	public void setLegendTitle(java.lang.String title)
	{
		((StandardLegend) jfreeChart.getLegend()).setTitle(title);
	}

	public void setLegendTitleFont(java.awt.Font font)
	{
		((StandardLegend) jfreeChart.getLegend()).setTitleFont(font);
	}*/

	/**  Methods needed to change Chart Title -  BEGIN   **/

	/**  Methods needed to change Chart Title -  BEGIN   **/
	public Color getTitleColor()
	{
		return (Color) jfreeChart.getTitle().getPaint();
	}

	public void setTitleColor(Color color)
	{
		jfreeChart.getTitle().setPaint(color);
	}

	public String getTitleText()
	{
		return jfreeChart.getTitle().getText();
	}

	public void setTitleText(String text)
	{
		jfreeChart.getTitle().setText(text);
	}

	public Font getTitleFont()
	{
		return jfreeChart.getTitle().getFont();
	}

	public void setTitleFont(Font font)
	{
		jfreeChart.getTitle().setFont(font);
	}
	/**  Methods needed to change Chart Title -  END  **/

	/**  Methods needed to change Chart Plot -  BEGIN **/

	public float getPlotBackgroundAlpha()
	{
		return jfreeChart.getPlot().getBackgroundAlpha();
	}

	public java.awt.Image getPlotBackgroundImage()
	{
		return jfreeChart.getPlot().getBackgroundImage();
	}

	public int getPlotBackgroundImageAlignment()
	{
		return jfreeChart.getPlot().getBackgroundImageAlignment();
	}

	public java.awt.Paint getPlotBackgroundPaint()
	{
		return jfreeChart.getPlot().getBackgroundPaint();
	}

	public java.awt.Color getPlotBackgroundColor()
	{
		return (Color) jfreeChart.getPlot().getBackgroundPaint();
	}
/*	public double getPlotDataAreaRatio()
	{
		return jfreeChart.getPlot().getDataAreaRatio();
	}

	public Dataset getPlotDataset()
	{
		return jfreeChart.getPlot().getDataset();
	}
*/
	public DatasetGroup getPlotDatasetGroup()
	{
		return jfreeChart.getPlot().getDatasetGroup();
	}

	public float getPlotForegroundAlpha()
	{
		return jfreeChart.getPlot().getForegroundAlpha();
	}

	public RectangleInsets getPlotInsets()
	{
		return jfreeChart.getPlot().getInsets();
	}

	public LegendItemCollection getPlotLegendItems()
	{
		return jfreeChart.getPlot().getLegendItems();
	}

	public java.lang.String getPlotNoDataMessage()
	{
		return jfreeChart.getPlot().getNoDataMessage();
	}

	public java.awt.Font getPlotNoDataMessageFont()
	{
		return jfreeChart.getPlot().getNoDataMessageFont();
	}

	public java.awt.Color getPlotOutlineColor()
	{
		return (Color) jfreeChart.getPlot().getOutlinePaint();
	}

	public java.awt.Stroke getPlotOutlineStroke()
	{
		return jfreeChart.getPlot().getOutlineStroke();
	}

	public Plot getPlotParent()
	{
		return jfreeChart.getPlot().getParent();
	}

	public Plot getPlotRootPlot()
	{
		return jfreeChart.getPlot().getRootPlot();
	}

/*	public Dataset getPlotSecondaryDataset()
	{
		return jfreeChart.getPlot().getSecondaryDataset();
	}
*/

	public void setPlotBackgroundAlpha(float alpha)
	{
		jfreeChart.getPlot().setBackgroundAlpha(alpha);
	}

	public void setPlotBackgroundImage(java.awt.Image image)
	{
		jfreeChart.getPlot().setBackgroundImage(image);
	}

	public void setPlotBackgroundImageAlignment(int alignment)
	{
		jfreeChart.getPlot().setBackgroundImageAlignment(alignment);
	}

	public void setPlotBackgroundColor(java.awt.Color color)
	{
		jfreeChart.getPlot().setBackgroundPaint(color);
	}

/*	public void setPlotDataAreaRatio(double ratio)
	{
		jfreeChart.getPlot().setDataAreaRatio(ratio);
	}

	public void setPlotDataset(Dataset data)
	{
		jfreeChart.getPlot().setDataset(data);
	}
*/
	public void setPlotForegroundAlpha(float alpha)
	{
		jfreeChart.getPlot().setForegroundAlpha(alpha);
	}

	public void setPlotInsets(RectangleInsets insets)
	{
		jfreeChart.getPlot().setInsets(insets);
	}

	public void setPlotInsets(RectangleInsets insets, boolean notify)
	{
		jfreeChart.getPlot().setInsets(insets, notify);
	}

	public void setPlotNoDataMessage(java.lang.String message)
	{
		jfreeChart.getPlot().setNoDataMessage(message);
	}

	public void setPlotNoDataMessageFont(java.awt.Font font)
	{
		jfreeChart.getPlot().setNoDataMessageFont(font);
	}

	public void setPlotOutlineColor(java.awt.Color color)
	{
		jfreeChart.getPlot().setOutlinePaint(color);
	}

	public void setPlotOutlineStroke(java.awt.Stroke stroke)
	{
		jfreeChart.getPlot().setOutlineStroke(stroke);
	}

	public void setPlotParent(Plot parent)
	{
		jfreeChart.getPlot().setParent(parent);
	}

	/*public void setPlotSecondaryDataset(Dataset dataset)
	{
		jfreeChart.getPlot().setSecondaryDataset(dataset);
	}*/

	/**  Methods needed to change Chart Plot -  END   **/








	public void addChangeListener(ChartChangeListener listener)
	{
		jfreeChart.addChangeListener(listener);
	}

	public void addProgressListener(ChartProgressListener listener)
	{
		jfreeChart.addProgressListener(listener);
	}

	public void addSubtitle(Title subtitle)
	{
		jfreeChart.addSubtitle(subtitle);
	}

	public java.awt.image.BufferedImage createBufferedImage(int width, int height)
	{
		return jfreeChart.createBufferedImage(width, height);
	}

	public java.awt.image.BufferedImage createBufferedImage(int width, int height, ChartRenderingInfo info)
	{
		return jfreeChart.createBufferedImage(width, height, info);
	}

	public void draw(java.awt.Graphics2D g2, java.awt.geom.Rectangle2D area)
	{
		jfreeChart.draw(g2, area);
	}

	public void draw(java.awt.Graphics2D g2, java.awt.geom.Rectangle2D chartArea, ChartRenderingInfo info)
	{
		jfreeChart.draw(g2, chartArea, info);
	}

/*	public void drawTitle(AbstractTitle title, java.awt.Graphics2D g2, java.awt.geom.Rectangle2D nonTitleArea)
	{
		jfreeChart.drawTitle(title, g2, nonTitleArea);
	}*/

	public boolean getAntiAlias()
	{
		return jfreeChart.getAntiAlias();
	}

	public java.awt.Image getBackgroundImage()
	{
		return jfreeChart.getBackgroundImage();
	}

	public int getBackgroundImageAlignment()
	{
		return jfreeChart.getBackgroundImageAlignment();
	}

	public float getBackgroundImageAlpha()
	{
		return jfreeChart.getBackgroundImageAlpha();
	}

	public java.awt.Paint getBackgroundPaint()
	{
		return jfreeChart.getBackgroundPaint();
	}

	public CategoryPlot getCategoryPlot()
	{
		return jfreeChart.getCategoryPlot();
	}

	public LegendTitle getLegend()
	{
		return jfreeChart.getLegend();
	}

	public Plot getPlot()
	{
		return jfreeChart.getPlot();
	}

	public Title getSubtitle(int index)
	{
		return jfreeChart.getSubtitle(index);
	}

	public int getSubtitleCount()
	{
		return jfreeChart.getSubtitleCount();
	}

	public java.util.List getSubtitles()
	{
		return jfreeChart.getSubtitles();
	}

/*public boolean getSuppressChartChangeEvents()
	{
		return jfreeChart.getSuppressChartChangeEvents();
	}*/

	public TextTitle getTitle()
	{
		return jfreeChart.getTitle();
	}

	public XYPlot getXYPlot()
	{
		return jfreeChart.getXYPlot();
	}

	public void handleClick(int x, int y, ChartRenderingInfo info)
	{
		jfreeChart.handleClick(x, y, info);
	}

/*	public void legendChanged(LegendChangeEvent event)
	{
		jfreeChart.legendChanged(event);
	}*/

	public void plotChanged(PlotChangeEvent event)
	{
		jfreeChart.plotChanged(event);
	}

	public void removeChangeListener(ChartChangeListener listener)
	{
		jfreeChart.removeChangeListener(listener);
	}

	public void removeProgressListener(ChartProgressListener listener)
	{
		jfreeChart.removeProgressListener(listener);
	}

	public void setAntiAlias(boolean flag)
	{
		jfreeChart.setAntiAlias(flag);
	}

	public void setBackgroundImage(java.awt.Image image)
	{
		jfreeChart.setBackgroundImage(image);
	}

	public void setBackgroundImageAlignment(int alignment)
	{
		jfreeChart.setBackgroundImageAlignment(alignment);
	}

	public void setBackgroundImageAlpha(float alpha)
	{
		jfreeChart.setBackgroundImageAlpha(alpha);
	}

	public void setBackgroundPaint(java.awt.Paint color)
	{
		jfreeChart.setBackgroundPaint(color);
	}

	public void setLegend(LegendTitle legend)
	{
		jfreeChart.addLegend(legend);
	}

	public void setSubtitles(java.util.List subtitles)
	{
		jfreeChart.setSubtitles(subtitles);
	}

	/*public void setSuppressChartChangeEvents(boolean flag)
	{
		jfreeChart.setSuppressChartChangeEvents(flag);
	}*/

	public void setTitle(java.lang.String title)
	{
		jfreeChart.setTitle(title);
	}

	public void setTitle(TextTitle title)
	{
		jfreeChart.setTitle(title);
	}

	public void titleChanged(TitleChangeEvent event)
	{
		jfreeChart.titleChanged(event);
	}

	public JFreeChart getChart()
	{
		return jfreeChart;
	}
}

