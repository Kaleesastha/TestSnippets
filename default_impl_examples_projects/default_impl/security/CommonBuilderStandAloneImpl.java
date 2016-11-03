//$Id: CommonBuilderStandAloneImpl.java,v 1.2 2010/10/29 13:45:55 swaminathap Exp $

package com.adventnet.studio.security;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.io.File;
import java.net.*;

import com.adventnet.security.ui.*;
public class CommonBuilderStandAloneImpl extends com.adventnet.nms.util.CommonBuilderUIInterface
{

    private Cursor busyCursor = new Cursor (Cursor.WAIT_CURSOR);
    private Cursor normalCursor = new Cursor (Cursor.DEFAULT_CURSOR);
    Hashtable imgcache =null;
	private String url=null;
	public CommonBuilderStandAloneImpl()
	{
		imgcache = new Hashtable();
		url=this.getClass().getPackage().getName();
		url=url.replace('.',File.separatorChar);
		//url=System.getProperty("user.dir")+File.separator+"classes"+File.separator+url+File.separator+"images"+File.separator;
		File imageDir= new File(System.getProperty("user.dir"));//No I18N
		File imgDir=imageDir.getParentFile().getParentFile();//This is done since I there is a java bug in solaris when we use ../../ to traverse to previous directory.
		url=imgDir.getAbsolutePath()+File.separator+"images"+File.separator;//No I18N
	}
	public void initialize(java.util.Properties props)
	{
	}
    public ImageIcon getImage(String iconName)
    {
        ImageIcon icon = null;
        if(imgcache.containsKey(iconName))
           {
			  return (ImageIcon)imgcache.get(iconName);
           }
        try 
        {
        icon = new ImageIcon(url+iconName);
        imgcache.put(iconName,icon);
            }
        catch(Exception mue)
            {
                System.out.println("Error creating URL for image");//No I18N
				mue.printStackTrace();
            }
        return icon;
    }

    public ImageIcon getImage(String iconName ,String dirName)
    {
       return new ImageIcon(getNmsHome()+File.separator+dirName+File.separator+iconName);
    }

    public Image getFrameIcon()
    {
        return com.adventnet.apiutils.Utility.findImage("com/adventnet/studio/tool/images/studio.png",null,true).getImage();//No I18N
    }

    private String getNmsHome()
    {
        return System.getProperty("user.dir");//No I18N
    }
    
    public Font getFont()
    {
        return new Font ("Helvetica", Font.PLAIN, 12);//No I18N
    }
    
    /**
     * This method will be used by the UI to center the any Component in the context 
     * of the application in which this UI is used. 
     */ 
    public void centerWindow(Component comp)
    {
        Dimension screenSize  = Toolkit.getDefaultToolkit ().getScreenSize ();
        Point screenCenter = new Point ((screenSize.width / 2), (screenSize.height / 2));
        int x = screenCenter.x - comp.getSize ().width / 2;
        int y = screenCenter.y - comp.getSize ().height / 2;
        if (x < 0)
            x = 0;
        if (y < 0)
            y = 0;
        comp.setLocation (x, y);
    }

    /**
     * This method will set busy cursor for the component passed. 
     */
    public void setBusyCursor(Component comp)
    {
        comp.setCursor(busyCursor);
    }
    
    /**
     * This method should set the default cursor for the component passed. 
     */
    public void setDefaultCursor(Component comp)
    {
        comp.setCursor(normalCursor);
    }

	public void showAboutWindow()
	{
	}
}








