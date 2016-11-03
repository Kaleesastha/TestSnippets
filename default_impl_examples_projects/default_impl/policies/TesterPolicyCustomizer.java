/*
$Id: TesterPolicyCustomizer.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
*/
package com.adventnet.nms.policyui;

import  com.adventnet.nms.policyui.PolicyObjectCustomizer;

import java.util.*;
import javax.swing.JFrame;
import javax.swing.JWindow;
import javax.swing.JTextField;
import java.awt.event.*;
import java.awt.Dimension;
import java.awt.Window;

public class TesterPolicyCustomizer extends PolicyObjectCustomizer
{
	private Properties prop = null;
	
	public Properties getProperties()
	{
		return this.prop;
	}

	public void setProperties(Properties p)
	{
		this.prop = p;
	}
	
    public void init(Properties p)
	{
		this.prop = p;
	};


    public  Window getCustomizer()
	{
		TesterPolicyUI tester =  new TesterPolicyUI(this,prop);
		tester.pack();
		return tester;
	};


}
