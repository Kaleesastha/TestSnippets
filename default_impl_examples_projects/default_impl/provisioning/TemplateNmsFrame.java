//$Id: TemplateNmsFrame.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
package test.provisioning;

import java.text.MessageFormat;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.applet.*;
import java.util.*;

import com.adventnet.nms.util.*;
import com.adventnet.nms.startclient.NmsFrame;
import com.adventnet.nms.util.PureClientUtils;
import com.adventnet.nms.provisioning.xml.*;
import com.adventnet.nms.provisioning.ui.*;
import com.adventnet.nms.provisioning.*;

public class TemplateNmsFrame extends JFrame implements NmsFrame, ActionListener,WindowListener
{
    private boolean initialized = false;
    private java.applet.Applet applet = null;
    TemplateUIPanel  TemplateUIPanel1 = null;
	private boolean showTemplateParametersPanel;
	public TemplateNmsFrame()
	{
	}

	public TemplateNmsFrame(boolean show)
	{
		showTemplateParametersPanel=show;
	}

    public void init(JApplet applet)
    { 
	try
	{
	    this.applet = applet;
      	String icon =applet.getParameter("FRAME_ICON");//No Internationalisation
      	setIconImage(NmsClientUtil.getImage(applet.getDocumentBase() + icon));
	    if (initialized == true) return;
	    Container container = getContentPane();
	    container.setLayout(new BorderLayout()); 
		Properties prop=new Properties();
        prop.put("HOSTNAME",applet.getDocumentBase().getHost());
        prop.put("WEB-SERVER-PORT",applet.getDocumentBase().getPort()+"");
		prop.put("USERNAME",NmsClientUtil.getUserName());
		prop.put("CONNECTION_TYPE","SESSION");
		//ProvisioningApiHandler ph=new ProvisioningApiHandler(prop);
		TemplateUIPanel1 = new TemplateUIPanel(applet,prop);
		PureClientUtils.addFailOverListener(TemplateUIPanel1,PureClientUtils.MAIN_SOCKET);
	    container.add(TemplateUIPanel1, BorderLayout.CENTER);
		if (showTemplateParametersPanel)
		{
			TemplateUIPanel1.showTemplateParametersPanel(applet.getParameter("TemplateName"));
		}
		else
		{
	    	TemplateUIPanel1.getProvisionPanel(applet.getParameter("TemplateName"));
		}
		setTitle(MessageFormat.format(ProvClientUtils.getString("Provisioning {0}"), new Object[]{applet.getParameter("TemplateName")}));;
	    initialized = true;
	    pack(); 
        NmsClientUtil.centerWindow(this);
	  	setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);
	}
	catch(Exception exc)
	{
	    System.out.println(ProvClientUtils.getString("Error inside NMS Frame"));
	    exc.printStackTrace();
	}
    } 

    public void actionPerformed(ActionEvent e) 
    {
        setVisible(false);
    }

    /* *
     * This is called immedaitely after the init method . When a user selects
     * the menu item then a new instance of this class is instantiated the init
     * method called and then this method is called with the boolean true
     * */ 
	 
    /*public void setVisible(boolean flag) 
    {
        if(initialized)
        {
			if (!(flag))
			{
				PureClientUtils.removeFailOverListener(TemplateUIPanel1,PureClientUtils.MAIN_SOCKET);
                TemplateUIPanel1.disposeDescriptionDialog();
            	super.setVisible(flag);
				dispose();
			}
			else
			{
            	NmsClientUtil.centerWindow(this);
            	super.setVisible(flag);
			}
        }
    }*/
	public void setVisible(boolean b)
	{
		super.setVisible(b);
		if(b)
		{
			TemplateUIPanel1.setFocus(this);
		}
	}
	 public void dispose()
	 {
		PureClientUtils.removeFailOverListener(TemplateUIPanel1,PureClientUtils.MAIN_SOCKET);
        TemplateUIPanel1.disposeDescriptionDialog();
		super.dispose();
	 	
	 }
	/*public TemplateUIPanel getTemplateUIPanel()
	{
		return TemplateUIPanel1;
	}*/
	public void setScheduled(boolean b)
	{
		TemplateUIPanel1.setScheduled(b);
	}

	/*public boolean canBeClosed()
	{
		return !(TemplateUIPanel1.isFormsPanelShowing()&&(TemplateUIPanel1.isSubmittedTemplate()));
	}

	public String getOperationId()
	{
		return TemplateUIPanel1.getOperationId();
	}*/

	public void windowActivated(WindowEvent we)
	{
	}

	public void windowDeactivated(WindowEvent we)
	{
	}

	public void windowIconified(WindowEvent we)
	{
	}

	public void windowDeiconified(WindowEvent we)
	{
	}
	public void windowClosing(WindowEvent we)
	{
		/*if (we.getSource() instanceof TemplateNmsFrame)
		{
			TemplateUIPanel tp=((TemplateNmsFrame)(we.getSource())).getTemplateUIPanel();
			if (tp!=null)
			{
				tp.confirmAndClose();
			}
		}*/
		TemplateUIPanel1.confirmAndClose();
	}


	public void windowClosed(WindowEvent we)
	{
		/*if (we.getSource() instanceof TemplateNmsFrame)
		{
			TemplateNmsFrame tnf=(TemplateNmsFrame)(we.getSource());
			if (!tnf.canBeClosed())
			{
				String opId=tnf.getOperationId();
				if(opId!=null)
				{
					try
					{
						api.stopRunningOperation(opId);
					}
					catch (Exception exc)
					{
						if((exc.getMessage()!=null)&&(!(exc.getMessage().trim().equals(""))))
						{
							if (exc.getMessage().trim().length()>50)
							{
								test.provisioning.MessageWindow.showMessage(NmsClientUtil.getParentFrame(),resourceBundle.getString("Provisioning"),exc.getMessage());
							}
							else
							{
								JOptionPane.showMessageDialog(this,exc.getMessage(),resourceBundle.getString("Provisioning"),JOptionPane.ERROR_MESSAGE);
							}
						}
						else
						{
							JOptionPane.showMessageDialog(this,resourceBundle.getString("Error occured while stoping the operation with id")+" = "+opId,resourceBundle.getString("Provisioning"),JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			}
		}*/
	}

	public void windowOpened(WindowEvent we)
	{
	}

 }
