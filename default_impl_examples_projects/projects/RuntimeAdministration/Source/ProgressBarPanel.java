
//$Id: ProgressBarPanel.java,v 1.1 2006/08/29 13:57:02 build Exp $

package com.adventnet.nms.runtimeconfig;

import java.util.Date;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class ProgressBarPanel extends JPanel implements Runnable 
{
		Thread t;
		//static final int COUNT_MAX=300;
		//static final int COUNT_MAX=5;
		static final int COUNT_MAX=20;
		public static boolean shouldRun = true;
		public static SmartProgressBar pBar = null;
                //Added by Balan on 4/7/03 for the Memory leak issue reported by SPP Team
                   public static boolean bCanbeRepainted       = false; 
                //Add Ends
		public ProgressBarPanel()
		{
				pBar = new SmartProgressBar();
				setLayout(new BorderLayout());
				setPreferredSize(new Dimension(180,14));
				setMinimumSize(new Dimension(180,14));
				setMaximumSize(new Dimension(180,14)); 
				add(pBar,BorderLayout.CENTER);
				t = new Thread(this);
				shouldRun = true;
				t.start();
		}	

		public void run()
		{
				int counter = 0;
				String s = "";
				while(shouldRun)
				{
						/*if(counter == COUNT_MAX)
						{
								Date d = new Date();
								long l = d.getTime();
								Date dnew = new Date(l);
								if(dnew.getSeconds() <= 9)
								{
									String s1 = "0"+dnew.getSeconds();
									s = dnew.getHours()+" : "+dnew.getMinutes()+" : "+s1;
								}
								else
								{
									s = dnew.getHours()+" : "+dnew.getMinutes()+" : "+dnew.getSeconds();
								}*/	
							//	RuntimeConfigFrame.updateTime(s);
								//System.out.println("Hourss.."+dnew.getHours()+" : "+dnew.getMinutes()+" : "+dnew.getSeconds());
								counter = 0;
						//}
                                                                if(bCanbeRepainted)
                                                                {
                                                                    pBar.moveToNextPosition();
                                                                    pBar.repaint();
                                                                }
						//pBar.paint(pBar.getGraphics());
						//pBar.paintComponent(pBar.getGraphics());

						try
						{
								Thread.sleep(10);
								//Thread.sleep(200);
						}
						catch (Exception e)
						{}
						counter++;
				}
				if(!shouldRun)
				{
					t.stop();
				}
		}
    //Added by Balan on 4/7/03 for the Memory leak issue reported by SPP Team
    public void dispose()
    {
        pBar= null; 
    }
    //Add Ends

}
