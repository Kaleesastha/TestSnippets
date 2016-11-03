// $Id: I18NUtils.java,v 1.1.1.1 2006/12/08 11:57:39 gramkumar Exp $
 package com.adventnet.management.i18n.tools ; 


 import java.awt.*;
 import java.awt.event.*;
 import javax.swing.*;
 import javax.swing.event.*;

 public class I18NUtils 
 {

	      /**Size of the screen.*/
	public static Dimension screenSize;     /**Window location.*/
	public static final int TOPLEFT = 0;     /**Window location.*/
	public static final int TOPRIGHT = 1;     /**Window location.*/
	public static final int BOTTOMLEFT = 2;     /**Window location.*/
	public static final int BOTTOMRIGHT = 3;     /**Window location.*/
	public static final int CENTER = 4;      	/**Static block to initialize the screen size.*/
    static
    {
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //screenCenter = new Point((screenSize.width/2),(screenSize.height/2));
    } 
    /**Window positioning by specifying the location interms of x and y.*/ 
	public static void WindowPositionSize(Window win, int locationx,int locationy, int wpercent, int hpercent) 
    { 
        if (screenSize == null)return; 
        if ((locationx > screenSize.width) || (locationy > screenSize.height)) return; 
        win.setSize(I18NUtils.screenSize.width*wpercent/100,I18NUtils.screenSize.height*hpercent/100); 
        win.setLocation(locationx,locationy); 
    }  
     
	/**Window positioning by specifying the location like TOPLEFT, BOTTOMRIGHT,etc. 
	 * Size of window can be specified as percentage of the screen width and height.*/ 
    public static void WindowPositionSize(Window win, int location, int wpercent, int hpercent) 
    { 
        if (screenSize == null) return; 
         
        win.setSize(I18NUtils.screenSize.width*wpercent/100,I18NUtils.screenSize.height*hpercent/100); 
         
        switch(location) 
        { 
        case 0: 
            win.setLocation(5,5); break; 
        case 1: 
            win.setLocation(I18NUtils.screenSize.width - win.getSize().width - 5, 5);  
            break; 
        case 2: 
            win.setLocation(5,I18NUtils.screenSize.height - win.getSize().height - 5);  
            break; 
        case 3: 
            win.setLocation(I18NUtils.screenSize.width - win.getSize().width - 5,I18NUtils.screenSize.height - win.getSize().height - 5); 
            break; 
        case 4: 
            win.setLocation((I18NUtils.screenSize.width - win.getSize().width)/2 ,(I18NUtils.screenSize.height - win.getSize().height)/2); 
            break; 
        } 
         
    }  
	 /**Window positioning by specifying the location like TOPLEFT, BOTTOMRIGHT,etc. */ 
	public static void WindowPosition(Window win, int location) 
    { 
        if (screenSize == null) return; 
         
        //win.setSize(WindowUtil.screenSize.width*wpercent/100,WindowUtil.screenSize.height*hpercent/100); 
         
        switch(location) 
        { 
        case 0: 
            win.setLocation(5,5); break; 
        case 1: 
            win.setLocation(I18NUtils.screenSize.width - win.getSize().width - 5, 5);  
            break; 
        case 2: 
            win.setLocation(5,I18NUtils.screenSize.height - win.getSize().height - 5);  
            break; 
        case 3: 
            win.setLocation(I18NUtils.screenSize.width - win.getSize().width - 5,I18NUtils.screenSize.height - win.getSize().height - 5); 
            break; 
        case 4: 
            win.setLocation((I18NUtils.screenSize.width - win.getSize().width)/2 ,(I18NUtils.screenSize.height - win.getSize().height)/2); 
            break; 
        } 
         
    } 
	  /**Window position at  Center*/
 public static void WindowPosition(Window win) 
    { 
        if (screenSize == null) return;
		win.setLocation((I18NUtils.screenSize.width - win.getSize().width)/2 ,(I18NUtils.screenSize.height - win.getSize().height)/2);
	}
 }







