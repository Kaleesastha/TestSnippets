/*
$Id: CollectedTableHandler.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $ 
*/
/*
 * @(#)CollectedGraphHandler.java	
 * Copyright (c) 1996 Advent Network Management, Inc. All Rights Reserved.
 * Please read the associated COPYRIGHTS file for more details.
 * 
 * ADVENT NETWORK MANAGEMENT, INC. MAKES NO REPRESENTATIONS OR WARRANTIES 
 * ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, 
 * FITNESS FOR A PARTICULAR PURPOSE, OR NON-INFRINGEMENT.  ADVENT NETWORK 
 * MANAGEMENT, INC. SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY 
 * LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE 
 * OR ITS DERIVATIVES.
 */
package com.adventnet.nms.pollui;

import java.util.*;
import java.applet.Applet;
import java.awt.Component;
import com.adventnet.nms.util.*;
import com.adventnet.snmp.beans.SnmpPoller;
import com.adventnet.snmp.snmp2.SnmpVarBind;
import com.adventnet.snmp.snmp2.SnmpVar;
import com.adventnet.nms.pollui.tl1.TL1CollectedDataHandler;
import com.adventnet.nms.startclient.NmsMainApplet;

/** 
  This Class handles the invocation of Collected Statistics graph, When Collected 
  Statistics Menu is clicked in Configured collection Panel. As this class 
  implements CustomClass interface the menu action command is of INVOKE_CLASS. 
  This allows user to Customize the graph.

  Following menu action action in pollmenu.xml will instantiate this class for 
  handling Collected  statistics.

      <MENU-ITEM name = "Collected Statistic"
	         action_command ="INVOKE_CLASS:com.adventnet.nms.pollui.CollectedTableHandler"
	         shortcut_key  = "P"
	         accelerator_modifier="CNTRL"
                 accelerator_key="O">
     </MENU-ITEM>
      		


  
  @author Karthick Srinivasan 
  @version 1.0
  @since WebNMS2.3 SP1 

 **/
 
public class CollectedTableHandler implements CustomClassInterface
{
    /** this variable holds the instance of NmsMainFrame
        reference to which normal or busy cursor is  set.
    */
    private Component parentFrame = null;
 
    /** This variable holds the instance of NmsMainApplet.
     */         
    private NmsMainApplet applet =null;
    /** This method is method due to implementation of CustomClassInterface.
        Here array of Properties will be passed corresponding to selected 
        host and statistics in Configured Collection Panel. 
        @param Properties[]  
    */
    public void setProperties(Properties[] p)
    {
        /** if User don't select any host in the list passed object to this 
            method will be null. Hence we show a JOption pane stating the mistake 
            and return from this class.
        */
       
        if(p == null)
            {
                NmsClientUtil.showError(NmsClientUtil.GetString("Select an host in the List for which you like to plot the Statistics"));
                return;
            }
  
        /** if User select only an single host in the list and not the polled data, 
            resulting Property array size will be more than one. In that array 
            each Property object will correspond to each Polled Data. As it will
             be feasible to show current statistics only for single Polled Data 
             at a time, we restrict user to select a single Polled Data.
        */
       
        if(p.length > 1)
             {
                NmsClientUtil.showError(NmsClientUtil.GetString("Select an Polling Unit in the Table for which you like to plot the Statistics"));
                return;
            }
           
        parentFrame = NmsClientUtil.getParentFrame();
        NmsClientUtil.busyCursor(parentFrame);
        applet = (NmsMainApplet)NmsClientUtil.applet;   
        Properties selectedRow = (Properties) p[0];
        
        // Check if TL1 Graphing
        String protocol = null;
        String tl1cmd = null;
        try 
            {   
                protocol = selectedRow.getProperty("protocol");
                tl1cmd = selectedRow.getProperty("oid");
            } 
        catch (Exception ex) {}
        
        if ( ((protocol != null) && protocol.equals("TL1")) ||
             ((tl1cmd != null) && (tl1cmd.indexOf(";") != -1)) )
            showTL1CollectedGraph(selectedRow);
        else
            showSnmpCollectedGraph(selectedRow);       
    
    }
 
    /** This method take care of showing Collected Statistics 
        in case when the selected polled data is of TL1 type.
        TL1Poller source are under 
        WebNMSHome/projects/PerformanceGraphs/Source/. As it is a 
        builder Projects it will enable you to customize the graphs
        as per your wish.
       @param Properties of the selected Polling Unit.
    */
    private void  showTL1CollectedGraph(Properties selectedRow)
    {
        NmsClientUtil.showStatusOnLabel("Processing...");
        new TL1CollectedDataHandler(selectedRow, applet);
        NmsClientUtil.normalCursor(parentFrame);
    }

   /** This method take care of showing Collected Statistics 
        in case when the selected polled data is of SNMP type.

        @param Properties of the selected Polling Unit 
    */
     private void  showSnmpCollectedGraph(Properties selectedRow)
    {
        String agentName = (String) selectedRow.getProperty ("agent");
        String oid_str = ((String) selectedRow.getProperty ("oid")).trim ();
        String stat_name = ((String) selectedRow.getProperty ("name")).trim ();
        String owner_name = ((String) selectedRow.getProperty("ownerName"));
        String multiple = ((String)selectedRow.getProperty("isMultiplePolledData"));
        String keyer = "";

         /** As Statistics and it related files are under 
            WebNMSHome/projects/PerformanceGraphs/Source/ it will enable 
            you to customize the graphs further.
        */
        String title = stat_name + " " + agentName + " " + oid_str;
        StatisticsForTable statistics = new StatisticsForTable(selectedRow, title);
        NmsClientUtil.normalCursor(parentFrame);
    }
}











