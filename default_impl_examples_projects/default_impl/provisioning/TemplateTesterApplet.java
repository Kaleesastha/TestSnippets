//$Id: TemplateTesterApplet.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
package test.provisioning;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.applet.*;
import java.util.*;
import com.adventnet.nms.provisioning.ui.*;
import com.adventnet.nms.provisioning.ProvisioningAPI;
import com.adventnet.nms.provisioning.server.ProvisioningAPIImpl_SessionStub;
import com.adventnet.nms.util.ClientFrameWorkAPI;
import com.adventnet.nms.util.NmsClientUtil;

import javax.swing.border.*;

public class TemplateTesterApplet extends JApplet 
{
    private boolean initialized = false;
    private java.applet.Applet applet = null;
    TemplateUIPanel  TemplateUIPanel1 = null;

    JTextField userField = new JTextField();
    JPasswordField passField = new JPasswordField();
    JComboBox typeCombo = new JComboBox();
    final JFrame frame = new JFrame();
	private ClientFrameWorkAPI clientFrameWorkAPI; 
    public TemplateTesterApplet()
    {
        this.applet = this;
    }

    public TemplateTesterApplet(java.applet.Applet applet)
    {
        this.applet = applet;
        init();
    }

    public void init()
	{
			TemplateUIPanel1 = new TemplateUIPanel(applet);
			getContentPane().setLayout(new BorderLayout());
			getContentPane().add(TemplateUIPanel1,BorderLayout.CENTER);
	} 

	
    public void stop()
    { 

        //<Begin_stop>

        //<End_stop>
    } 
    public void start()
    { 

        //<Begin_start>

        //<End_start>
    } 

	public void destroy()
	{
		if (clientFrameWorkAPI!=null)
		{
			NmsClientUtil.cleanUpForAppletReload();
			ProvisioningAPIImpl_SessionStub.socket = null;
			//Need to get the fix from Client team about destroy the ClientFrameWorkAPI instance.
			//clientFrameWorkAPI.destroy();
		}
	}
}
