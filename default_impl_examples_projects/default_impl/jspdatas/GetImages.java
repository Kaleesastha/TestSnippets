/*
$Id: GetImages.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
*/
package com.adventnet.nms.jsp;

import java.awt.*;
import java.awt.image.*;
import java.io.FileOutputStream;
import com.sun.image.codec.jpeg.*;  
import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.util.GenericUtility;
import com.adventnet.nms.util.NmsLogMgr;
import com.adventnet.beans.graphs.LineGraph; 
import com.adventnet.beans.graphs.BarGraph; 
import com.sun.jimi.core.Jimi;
import com.sun.jimi.core.JimiException;

import com.adventnet.management.log.Log;

public class GetImages
{
	public GetImages()
	{
	}

	/**
	 * Returns the line graph image object for the given data
	 *
	 * @param dataAvailable boolean stating the availability of data
	 * @param datePlotPoints the starting point of the line graph
	 * @param lya the end point of the graph 
	 * @param title  the title of the image
	 * @return java.awt.Image object 
	 **/
	public Image getLineGraphImage(boolean dataAvailable,long[] datePlotPoints,long[] lya,String title) 
        {
		LineGraph lg = new LineGraph(); 
		Image img;
                lg.setXLabel("Time (hours). Time Interval : All ");
                lg.setYLabel("Polled Data (long)");
                lg.setDateFormat(3);
		lg.setBounds(0,0,900,500);
		lg.setStrokeValue((float) 0.5);
		lg.setShowyLabel(true);//changed to true
		lg.setYScalePoints(10);
		lg.setXScalePoints(6);
		lg.setShowXPointsHorizontal(true);
		lg.setAbsoluteTime(true);
		lg.setShowxLabel(true);
		int len = lya.length;
		long range = (long)(datePlotPoints[len-1] - datePlotPoints[0]);
		int xrange = (int)(((range + 1000)) + 3600000) ;
		lg.setXRange(xrange);
		lg.setMinX(datePlotPoints[0]);
		lg.setStartTime(datePlotPoints[0]);
		lg.setLabelFont(new Font("Helvetica",0,12));
		lg.setTitleFont(new Font("Helvetica",0,14));
		lg.setScaleFont(new Font("Helvetica",0,12));
		lg.setXLabelColor(Color.black);
                lg.setYLabelColor(Color.black);
                lg.setXPointColor(Color.black);
                lg.setYPointColor(Color.black);
		lg.setBgcolor(Color.white);
		lg.setFgcolor(Color.black);
		lg.setLinecolor(Color.red);
		lg.setShowMarkers(true);
		lg.setBottomInset(25);
		lg.setRightInset(20);
		lg.setTopInset(5);
		lg.setLeftInset(10);
		lg.setAbsoluteTime(true);
		//lg.setStaticData(true);
		lg.setXGrids(12);
		lg.setYGrids(10);
		lg.setTitle(title);
		lg.setShowTitle(true);
		lg.setTitleColor(Color.black);

		if(!dataAvailable)
			NmsLogMgr.MISCUSER.log("No Data Available",Log.SUMMARY);
		else
			lg.setResult(0,datePlotPoints,lya);
		img =lg.getImage(900,500);
		return img;
	}




	/**
	 * Returns the bar graph image object for the given data
	 *
	 * @param dataAvailable boolean stating the availability of data
	 * @param datePlotPoints  the starting point of the line graph
	 * @param lya the end point of the graph 
	 * @param title the title of the image
	 * @return java.awt.Image object 
	 **/

	public Image getBarGraphImage(boolean dataAvailable,long[] datePlotPoints,long[] lya,String title) 
        {
		BarGraph BarGraph1=new BarGraph();   
		Image img;
                BarGraph1.setXLabel("Time (hours). Time Interval : All ");
                BarGraph1.setYLabel("Polled Data (long)");
		BarGraph1.setBounds(0,0,900,500);
		BarGraph1.setShowyLabel(true);
		BarGraph1.setStrokeValue((float) 4.0);
		BarGraph1.setYScalePoints(10);
		BarGraph1.setXScalePoints(6);
		BarGraph1.setShowXPointsHorizontal(true);
		BarGraph1.setShowxLabel(true);
		BarGraph1.setLeftInset(10);
		int len = lya.length;
		long range = (datePlotPoints[len-1] - datePlotPoints[0]);
		int xrange = (int)(((range + 1000)) + 3600000);
		BarGraph1.setXRange(xrange);
		BarGraph1.setMinX(datePlotPoints[0]);
		BarGraph1.setStartTime(datePlotPoints[0]);
		BarGraph1.setAbsoluteTime(true);
		BarGraph1.setBottomInset(25);
		BarGraph1.setRightInset(20);
		BarGraph1.setTopInset(5);
		BarGraph1.setShowMarkers(true);
		//BarGraph1.setStaticData(true);
		BarGraph1.setXGrids(12);
		BarGraph1.setYGrids(10);
		BarGraph1.setShowxLabel(true); 
                BarGraph1.setDateFormat(3);
		BarGraph1.setTitleFont(new Font("Helvetica",0,14));
		BarGraph1.setScaleFont(new Font("Helvetica",0,12));
		BarGraph1.setLabelFont(new Font("Helvetica",0,12));		
		BarGraph1.setXLabelColor(new Color(-65383));
		BarGraph1.setXLabelColor(Color.black);
                BarGraph1.setYLabelColor(Color.black);
                BarGraph1.setXPointColor(Color.black);
                BarGraph1.setYPointColor(Color.black);
		BarGraph1.setBgcolor(Color.white);
		BarGraph1.setFgcolor(Color.black);
		BarGraph1.setBarColor(Color.red);   
		BarGraph1.setTitle(title);
		BarGraph1.setShowTitle(true);
		BarGraph1.setTitleColor(Color.black);
		if(!dataAvailable)
			NmsLogMgr.MISCUSER.log("No Data Available",Log.SUMMARY);
		else
			BarGraph1.setResult(0,datePlotPoints,lya);

		img =BarGraph1.getImage(900,500);
		return img;
	}

	/**
	 * Used to encode the image
	 *
	 * @param st the file name 
	 * @param img the java.awt.Image object that has to be encoded
	 *
	 **/
	public void encodeImage(String st,Image img) {
		try {
			Jimi.putImage(img,st);
		} catch(Exception e) {
			NmsLogMgr.MISCERR.fail("Exception in GetImages.java " + e.getMessage(),e);
			return;
		}             
	}
}	
