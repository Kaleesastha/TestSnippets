package com.adventnet.nms.help;

import javax.swing.JApplet;
import javax.swing.JFrame;

import com.adventnet.nms.util.NmsClientUtil;
import com.adventnet.nms.startclient.NmsFrame;
import com.adventnet.management.i18n.AdventNetResourceBundle;
import com.adventnet.help.about.AboutDialog;

public class WebNMSAboutDialog implements NmsFrame
{

    private static com.adventnet.help.about.AboutDialog aboutDlg = null;
    

    public WebNMSAboutDialog()
    {}
    
    public void init(JApplet applet)
    {
        if(aboutDlg == null)
        {
            initializeAboutDialog();
        }
    }

    public void setVisible(boolean show)
    {
        if(aboutDlg == null)
        {
            initializeAboutDialog();
        }

        aboutDlg.setVisible(show);
    }
    
    public void initializeAboutDialog()
    {
        JFrame parentFrame  = NmsClientUtil.getParentFrame();
        String productNameStr    = NmsClientUtil.GetString("Product Name");
        String productVersionStr = NmsClientUtil.GetString("Version x.x");
        boolean setModal         = true;
        
        String param = NmsClientUtil.getClientType();

        if(param != null && param.equals("JAVAWEBSTART") || param.equals("APPLETCLIENT") )
        {
            aboutDlg = new com.adventnet.help.about.AboutDialog(NmsClientUtil.applet,parentFrame,setModal,AdventNetResourceBundle.getInstance(),null);
        }
        else
        {
            aboutDlg = new com.adventnet.help.about.AboutDialog(parentFrame,setModal,AdventNetResourceBundle.getInstance(),null);
        }
        aboutDlg.setProductName(productNameStr);
        aboutDlg.setProductVersion(productVersionStr);
        aboutDlg.init();
        aboutDlg.updateValues();
        NmsClientUtil.centerWindow(aboutDlg);
    }
    
}
