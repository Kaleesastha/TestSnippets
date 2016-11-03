/*
$Id: CurrentGraphInvoker.java,v 1.1 2006/08/29 13:56:51 build Exp $ 
*/
/** 
 *     <MENU-ITEM name = "Current Statistic"
 *	         action_command ="INVOKE_CLASS:com.adventnet.nms.poll.graphs.CurrentGraphInvoker"
 *	         shortcut_key="C"
 *	         accelerator_modifier="CNTRL-SHIFT"
 *                 accelerator_key="P">
 *     </MENU-ITEM>
  **/


package com.adventnet.nms.poll.graphs;

// import java classes
import java.awt.Component;
import java.awt.Cursor;
import java.util.Properties;
import java.text.MessageFormat;

import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

// import AdventNet classes
import com.adventnet.nms.util.CustomClassInterface;
import com.adventnet.nms.util.NmsClientUtil;
import com.adventnet.nms.util.NmsUiAPI;

public class CurrentGraphInvoker implements CustomClassInterface
{
public int protocolCheck;
     public void setProperties(Properties[] p)
    {
        if(p == null)
        {
            NmsClientUtil.showError(NmsClientUtil.GetString("Select an host in the List for which you like to plot the Statistics"));
            return;
        }
  
        if(p.length > 1)
        {
            NmsClientUtil.showError(NmsClientUtil.GetString("Select an Polling Unit in the Table for which you like to plot the Statistics"));
            return;
        }
           
        final Properties selectedRow = (Properties) p[0];
        final Properties treeNodeProperties = NmsUiAPI.getTreeNodeProperties(NmsUiAPI.getCurrentNodeId());

        // Invoke graph in different thread
        Runnable run = new Runnable()
            {
                public void run()
                {
                    JPanel panelComp = null;
                    String panelkey = null;

                    if(treeNodeProperties != null)
                        panelkey = (String)treeNodeProperties.getProperty("PANEL-KEY");
                    
                    if(panelkey != null)
                        panelComp = (JPanel)NmsUiAPI.getNmsPanelInstance(panelkey);
                    
                    /* set busy cursor to ConfiguredCollection panel 
                     * WAIT_CURSOR 3 (as defined in java.awt.Cursor)
                     */
                    if(panelComp != null)
                        setCursorForPanel(panelComp, 3);

                    NmsClientUtil.showStatusOnLabel("Processing...");
     try
      { 
                    String protocol = selectedRow.getProperty("protocol");
                    String getProtocols=NmsClientUtil.applet.getParameter("GRAPH_PROTOCOLS");
                  
                     
                    if(getProtocols!=null)
                    {
                        protocolCheck=getProtocols.indexOf(protocol);
                     }
                         else
                    {
                        protocolCheck=-1;
                     }
                  if(protocol != null)
                    {
                        if(protocol.equalsIgnoreCase("SNMP")||protocolCheck!=-1)
                        {
                            processForSNMP(selectedRow);
                        }
                        else
                        {
                            Object mfArguments[] = {protocol};
                            String htmlformat = MessageFormat.format(NmsClientUtil.GetString("Graph implementation not available for {0}"),mfArguments);
                            JOptionPane.showMessageDialog(panelComp, htmlformat,NmsClientUtil.GetString("Error"),JOptionPane.ERROR_MESSAGE);
                        }                
                    }
                    else
                    {
                        String htmlformat = NmsClientUtil.GetString("Selected PolledData's Protocol property is NULL");
                        JOptionPane.showMessageDialog(panelComp, htmlformat,NmsClientUtil.GetString("Error"),JOptionPane.ERROR_MESSAGE);
                    }
                    }catch(Exception e)
                    {
                         e.printStackTrace();
                    }
                    

                    /* set default cursor to ConfiguredCollection panel 
                     * DEFAULT_CURSOR 0 (as defined in java.awt.Cursor)
                     */
                    if(panelComp != null)
                        setCursorForPanel(panelComp, 0);

                    NmsClientUtil.showStatusOnLabel("Done");
                }
            };
        new Thread(run).start();
    }
 
    private void processForSNMP(Properties selectedRow)
    {
            SnmpCurrentGraphController scgc = new SnmpCurrentGraphController(selectedRow);        
    }

    /** Sets the cursor for panel passed as input
     *
     * @param panelComp Panel for which the cursor is set
     * @param cursorType Cursor that should be set, takes value as defined in java.awt.Cursor
     *
     * @see Cursor 
     */
    private void setCursorForPanel(Component panelComp, int type)
    {
        final Component tempComp = panelComp;
        final int cursortype = type;
        Runnable run = new Runnable()
            {
                public void run()
                {
                    tempComp.setCursor(new Cursor(cursortype));
                }
            };
        SwingUtilities.invokeLater(run);
    }
}


