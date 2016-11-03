
//$Id: GlassIconPanel.java,v 1.1 2006/08/29 13:57:02 build Exp $

package com.adventnet.nms.runtimeconfig;

import java.awt.*;
import java.awt.event.*;
import java.io.PrintStream;
import java.util.EventObject;
import javax.swing.*;

public class GlassIconPanel extends JPanel
    implements MouseMotionListener, MouseListener, ActionListener
{

    public GlassIconPanel()
    {
        curMode = 0;
        setLayout(null);
        setOpaque(false);
        addMouseListener(this);
        addMouseMotionListener(this);
        add(displayLbl);
    }

    public void actionPerformed(ActionEvent actionevent)
    {
        //System.out.println(" aEvtArg " + actionevent.getActionCommand());
    }

    private void cleanUp()
    {
        setVisible(false);
        displayLbl.setVisible(false);
        setCursor(Cursor.getPredefinedCursor(0));
        parentFrame.setGlassPane(origGlassPane);
        parentFrame = null;
        origGlassPane = null;
        actLis = null;
    }

    public MouseEvent getLastEvent()
    {
        return lastEvent;
    }

    public static void main(String args[])
    {
        JFrame jframe = new JFrame();
        GlassIconPanel glassiconpanel = new GlassIconPanel();
        //glassiconpanel.showGlassPanel(glassiconpanel, jframe, 0, null, "Hello");
        jframe.setSize(500, 500);
        jframe.setVisible(true);
    }

    public void mouseClicked(MouseEvent mouseevent)
    {
        if(curMode == 0)
            processFinished(mouseevent);
    }

    public void mouseDragged(MouseEvent mouseevent)
    {
        if(curMode == 1)
            processMove(mouseevent);
    }

    public void mouseEntered(MouseEvent mouseevent)
    {
    }

    public void mouseExited(MouseEvent mouseevent)
    {
    }

    public void mouseMoved(MouseEvent mouseevent)
    {
        if(curMode == 0)
            processMove(mouseevent);
    }

    public void mousePressed(MouseEvent mouseevent)
    {
    }

    public void mouseReleased(MouseEvent mouseevent)
    {
        if(curMode == 1)
            processFinished(mouseevent);
    }

    private void processFinished(MouseEvent mouseevent)
    {
    	if(shiftFlag || controlFlag)
	{}
	else if(shiftFlag && controlFlag)
	{}
	else
	{
        	lastEvent = SwingUtilities.convertMouseEvent((Component)mouseevent.getSource(), mouseevent, parentFrame);
	        setVisible(false);
       		actLis.actionPerformed(new ActionEvent(this, 0, "FINISHED"));
	        cleanUp();
	}	
    }

    private void processMove(MouseEvent mouseevent)
    {
    	if(shiftFlag || controlFlag)
	{}
	else if(shiftFlag && controlFlag)
	{}
	else
	{
	        lastEvent = SwingUtilities.convertMouseEvent((Component)mouseevent.getSource(), mouseevent, parentFrame);
      		displayLbl.setLocation(mouseevent.getX() + 10, mouseevent.getY());
       		//setCursor(new Cursor(Cursor.MOVE_CURSOR));
      		displayLbl.setVisible(true);
	        actLis.actionPerformed(new ActionEvent(this, 0, "MOVED"));
	}
    }

    public void showGlassPanel(ActionListener actionlistener, JFrame jframe, int i, ImageIcon imageicon, String s,boolean sFlag,boolean cFlag)
    {
    	shiftFlag = sFlag;
	controlFlag = cFlag;
        if(parentFrame != null)
            cleanUp();
        actLis = actionlistener;
        parentFrame = jframe;
        origGlassPane = parentFrame.getGlassPane();
        parentFrame.setGlassPane(this);
        curMode = i;
        displayLbl.setIcon(imageicon);
        displayLbl.setText(s);
        displayLbl.setSize(displayLbl.getPreferredSize());
        //setCursor(new Cursor(Cursor.MOVE_CURSOR));
        setVisible(true);
    }

    public static final int MOUSE_MOVE = 0;
    public static final int MOUSE_DRAG = 1;
    private int curMode;
    public static final String MOVED = "MOVED";
    public static final String FINISHED = "FINISHED";
    private final JLabel displayLbl = new JLabel();
    private Component origGlassPane;
    private ActionListener actLis;
    private JFrame parentFrame;
    private MouseEvent lastEvent;
    boolean shiftFlag = false;
    boolean controlFlag = false;
}
