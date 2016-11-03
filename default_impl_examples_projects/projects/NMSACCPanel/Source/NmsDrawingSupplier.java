// $Id: NmsDrawingSupplier.java,v 1.1 2006/08/29 13:57:01 build Exp $

package com.adventnet.nms.alertui.accpanel;

import java.awt.Paint;
import java.awt.Color;

import org.jfree.chart.plot.DefaultDrawingSupplier;

public class NmsDrawingSupplier extends DefaultDrawingSupplier
{
	private int paintIndex;
	private Color[] paintSequence = 
						{
							new Color(51,211,51), // green
							new Color(0,0,172), // blue
							new Color(255,128,0), // red

							new Color(41,132,41), // green
							new Color(74,75,161), // blue
							new Color(252,90,90), // red

							new Color(34,139,68), // green
							new Color(0,0,255), // blue
							new Color(198,58,58), // red

							new Color(246,171,101), // green
							new Color(206,52,117), // blue
							new Color(168,126,89) // red
						};

	public Paint getNextPaint()
	{
		Paint result = paintSequence[paintIndex % paintSequence.length];
		paintIndex++;
		return result;
	}
}

