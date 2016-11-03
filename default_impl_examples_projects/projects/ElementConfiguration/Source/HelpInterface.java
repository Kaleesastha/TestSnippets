//$Id: HelpInterface.java,v 1.1 2006/08/29 13:56:51 build Exp $

package com.adventnet.nms.config ; 


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public interface HelpInterface 
{
	public String  getHelpFor(String str);

	public String getTitle();
}

