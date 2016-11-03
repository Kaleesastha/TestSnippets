// $Id: ACCPanelChartInterface.java,v 1.1 2006/08/29 13:57:01 build Exp $
package com.adventnet.nms.alertui.accpanel;

import java.util.*;
import java.awt.*;
import com.adventnet.nms.severity.*;

public interface ACCPanelChartInterface
{
	public void updateCounts(Vector vect, SeverityInfo sevInfo);
	public Component getOriginalComponent();
	public boolean isHandCursorNeeded(int x, int y);
	public String getCategory(int x, int y);
	public int getSeverity(int x, int y);
	public void initialize(Vector severity);
}

