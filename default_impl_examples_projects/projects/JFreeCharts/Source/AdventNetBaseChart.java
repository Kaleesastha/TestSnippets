package com.adventnet.nms.poll.graphs;

//import Java classes
import java.util.Date;
import java.util.Hashtable;
import java.util.Enumeration;
import java.util.Vector;
import java.text.DateFormat;
import java.io.IOException;
import java.awt.Font;

//import jfreechart classes
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.title.TextTitle;

public abstract class AdventNetBaseChart extends ChartPanel
{

 	private Hashtable dataTable = new Hashtable();
    	public static int LINECHART=1;
	public static int BARCHART=2;
	public static int AREACHART=3;
	public static int SCATTERCHART=4;
	public static int XYSTEPCHART=5;
    /**
     * Constructs an AdventNetBaseChart with the default data source.
     *
     * @param chart <code>JFreeChart</code> instance.
     */
    public AdventNetBaseChart(JFreeChart chart)
    {
        super(chart);
    }

    /**
     * Constructs an AdventNetBaseChart with the default data source.
     *
     * @param chart <code>JFreeChart</code> instance.
     * @param useBuffer  a flag controlling whether or not an off-screen buffer is used.
     */
    public AdventNetBaseChart(JFreeChart chart,
                              boolean useBuffer)
    {
        super(chart, useBuffer);
     }

    /**
     * Constructs an AdventNetBaseChart with the default data source.
     *
     * @param chart       <code>JFreeChart</code> instance.
     * @param properties  a flag indicating whether or not the chart property
     *                    editor should be available via the popup menu.
     * @param save        a flag indicating whether or not save options should
     *                    be available via the popup menu.
     * @param print       a flag indicating whether or not the print option
     *                    should be available via the popup menu.
     * @param zoom        a flag indicating whether or not zoom options should
     *                    be added to the popup menu.
     * @param tooltips    a flag indicating whether or not tooltips should be
     *                    enabled for the chart.
     */
    public AdventNetBaseChart(JFreeChart chart,
                              boolean properties,
                              boolean save,
                              boolean print,
                              boolean zoom,
                              boolean tooltips)
    {
        super(chart, properties, save, print, zoom, tooltips);
    }

    /**
     * Constructs an AdventNetBaseChart with the default data source.
     *
     * @param chart             <code>JFreeChart</code> instance.
     * @param width             the preferred width of the panel.
     * @param height            the preferred height of the panel.
     * @param minimumDrawWidth  the minimum drawing width.
     * @param minimumDrawHeight the minimum drawing height.
     * @param maximumDrawWidth  the maximum drawing width.
     * @param maximumDrawHeight the maximum drawing height.
     * @param useBuffer         a flag that indicates whether to use the
     *                          off-screen buffer to improve performance.
     * @param properties        a flag indicating whether or not the chart
     *                          property editor should be available via the
     *                          popup menu.
     * @param save              a flag indicating whether or not save options
     *                          should be available via the popup menu.
     * @param print             a flag indicating whether or not the print option
     *                          should be available via the popup menu.
     * @param zoom              a flag indicating whether or not zoom options
     *                          should be added to the popup menu.
     * @param tooltips          a flag indicating whether or not tooltips should
     *                          be enabled for the chart.
     */
    public AdventNetBaseChart(JFreeChart chart,
                              int width, int height,
                              int minimumDrawWidth, int minimumDrawHeight,
                              int maximumDrawWidth, int maximumDrawHeight,
                              boolean useBuffer, boolean properties,
                              boolean save, boolean print,
                              boolean zoom, boolean tooltips)
    {
        super(chart, width, height,
              minimumDrawWidth, minimumDrawHeight,
              maximumDrawWidth, maximumDrawHeight,
              useBuffer, properties, save, print, zoom, tooltips);
    }

    public void save()
    {
         try
         {
         	doSaveAs();
         }
         catch(IOException e)
         {
         }
    }

    public void setTitle(String title)
    {
         this.getChart().setTitle(title);
    }

   public void setSubTitle(String subTitle)
    {
         TextTitle stitle = new TextTitle(subTitle);
        stitle.setFont(new Font("Dialog", Font.PLAIN, 12));
        this.getChart().addSubtitle(stitle);
    }

    public final void appendData(String series[], double Vals[])
	{
		 long xVal = System.currentTimeMillis();
         for(int i=0; i<series.length; i++)
		 {
			 Vector vec = (Vector)dataTable.get(series[i]);
			 if(vec == null)
			 {
				 vec = new Vector();
				 dataTable.put(series[i], vec);
			 }
			 vec.add(new Double((double)xVal));
			 vec.add(new Double(Vals[i]));
		 }
		 double xVals[][] = new double[Vals.length][1];
         double yVals[][] = new double[Vals.length][1];
         for(int i=0;i<yVals.length;i++)
         {
              yVals[i][0] = Vals[i];
              xVals[i][0] = (double)xVal;
         }
		 appendData(series, xVals, yVals);

	}

   //Abstract methods need to be implemented by the sub classes.
    public abstract void setAxisLabels(String xLabel, String yLabel);
    public abstract void setMessage(String message);
    public abstract void setXTimeAxis();
    public abstract void setData(String[] series, double xVals[][], double yVals[][], boolean flag);
    public abstract void appendData(String[] series, double xVals[][], double yVals[][]);

	public final void setData(Hashtable table)
	{
		this.dataTable = table;
		if(table.isEmpty())
			return;
		Enumeration e = dataTable.elements();
		Vector vec = (Vector)e.nextElement();
		int size =  vec.size()/2;
		String series[] = new String[dataTable.size()];
		double xVals[][] = new double[dataTable.size()][size];
		double yVals[][] = new double[dataTable.size()][size];
		e = dataTable.keys();
		int k=0;
		while(e.hasMoreElements())
		{
			series[k] = (String)e.nextElement();
			vec = (Vector)dataTable.get(series[k]);
			double[] xtemp = new double[size];
			double[] ytemp = new double[size];
			int j=0;
			for(int i=0; i<vec.size(); i+=2)
			{
				xtemp[j]= ((Double)vec.elementAt(i)).doubleValue();
				ytemp[j]= ((Double)vec.elementAt(i+1)).doubleValue();
				j++;
			}
			xVals[k] = xtemp;
			yVals[k] = ytemp;
			k++;
		}
		setData(series, xVals, yVals, false);
	}

    //This method should be overriddern by subclasses. In the subclasses we should call super.clearChart() for
	//clear the Hashtable entries.
	public void clearChart()
	{
		dataTable.clear();
	}

	public final Hashtable getData()
	{
		return dataTable;
	}


}






