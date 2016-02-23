package test;
import javax.swing.*;
import java.util.*;

import com.adventnet.nms.startclient.*;
import com.adventnet.nms.util.*;

public class SetClientTitle extends AbstractBaseNmsPanel
{
	public void setProperties(Properties prop)
	{	
	}
	
	public void init(JApplet app)
	{
		NmsMainFrame mf=null;
		mf=(NmsMainFrame)NmsClientUtil.getParentFrame();
		System.out.println("The Title of the frame is :"+mf.getTitle());
		String s=mf.getTitle();
		mf.setTitle(s+" "+(String)NmsClientUtil.applet.getParameter("BE_HOST_NAME"));
	}
}
