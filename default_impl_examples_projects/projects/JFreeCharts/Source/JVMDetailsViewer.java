//$Id: JVMDetailsViewer.java,v 1.2 2007/07/31 14:28:11 sumitha Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// Kindly refer to tcats_edit_source_files.html file under help/source_generation directory for details




//<Begin_Version>
//version$31
//<End_Version>
package com.adventnet.nms.poll.graphs;

// import java classes
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

import java.util.Vector;

import java.util.StringTokenizer;

import java.util.Hashtable;

import com.adventnet.nms.poll.*;
import com.adventnet.nms.poll.NmsPollException;
//import JFreeChart

import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.annotations.XYTextAnnotation;
import org.jfree.chart.axis.*;
import org.jfree.chart.plot.DefaultDrawingSupplier;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import com.adventnet.snmp.beans.ResultEvent;
import com.adventnet.snmp.beans.ResultListener;

// import AdventNet classes
import com.adventnet.nms.util.NmsClientUtil;
import com.adventnet.nms.poll.CollectedData;

public class JVMDetailsViewer extends JFrame  implements  Runnable,com.adventnet.apiutils.ParameterChangeListener
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "JFreeChartsResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel mainPanel = null;
	javax.swing.JPanel graphTablePanel = null;
	javax.swing.JPanel graphPanel = null;
	javax.swing.JPanel tablePanel = null;
	javax.swing.JPanel labelPanel = null;
	javax.swing.JLabel dataName = null;
	javax.swing.JScrollPane tablePane = null;
	javax.swing.JTable tableView = null;
	javax.swing.JPanel graphTypePanel = null;
	javax.swing.JButton lineButton = null;
	javax.swing.JButton barButton = null;
	javax.swing.JButton areaButton = null;
	javax.swing.JButton scatterButton = null;
	javax.swing.JButton xystepButton = null;
	javax.swing.JButton tableButton = null;
	javax.swing.JPanel toolBarPanel = null;
	javax.swing.JPanel toolBar1Panel = null;
	javax.swing.JButton saveButton = null;
	javax.swing.JButton clearGraphButton = null;
	javax.swing.JLabel JLabel1 = null;
	javax.swing.JPanel toolBar2Panel = null;
	javax.swing.JButton helpButton = null;
	javax.swing.JButton closeButton = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JComboBox JComboBox1 = null;
	javax.swing.JPanel rangeSettingsPanel = null;
	javax.swing.JRadioButton customRange = null;
	javax.swing.JRadioButton defaultRange = null;
	javax.swing.JPanel plotButtonPanel = null;
	javax.swing.JButton plotButton = null;
	javax.swing.JRadioButton todayRange = null;
	javax.swing.JRadioButton day7Range = null;
	javax.swing.JPanel customRangePanel = null;
	com.adventnet.beans.probeans.ProDateTimeComponent fromTimeComponent = null;
	javax.swing.JLabel fromLabel = null;
	com.adventnet.beans.probeans.ProDateTimeComponent toTimeComponent = null;
	javax.swing.JLabel toLabel = null;
	javax.swing.JRadioButton autorefresh = null;
	javax.swing.ButtonGroup rangeButtonGroup = null;
	com.adventnet.beans.utilbeans.OptionDialogInformer OptionDialogInformer1 = null;
	private com.adventnet.apiutils.ParameterObject po= null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>

	JVMGraphController graphController = null;
	AdventNetBaseChart baseChart = null;
	private String currentlyVisibleChart = "lineButton";
	private Properties chartProperties = null;
	private Hashtable plotData = null;
	String selItem = null;
	public static boolean isAutoRefresh = false;

Hashtable pDataNameVsDetails = null;

  public void setVisible(boolean bl)
  {

                 //<Begin_setVisible_boolean>
                 //<End_setVisible_boolean>

                 //<Begin_setVisible_boolean>
       	if(bl)
       	{
       	  init();
          start();
        }
        else
        {
          stop();
        }
        super.setVisible(bl);
  
                 //<End_setVisible_boolean>
                   }

    public void stop()
  {
       //<Begin_stop> 
       if(!running)
 return;
 running=false;

  
       //<End_stop>
  }
  public void start()
  {
       //<Begin_start> 
       if(running)
 return;
 running=true;

  
       //<End_start>
  }
  public void init()
  {

        //<Begin_init> 
	if(getParameter("RESOURCE_PROPERTIES" ) != null)
	{
		localePropertiesFileName = getParameter("RESOURCE_PROPERTIES");
	}
	resourceBundle = com.adventnet.apiutils.Utility.getBundle(localePropertiesFileName,getParameter("RESOURCE_LOCALE"),applet);
        if (initialized) return; 
        this.setSize(getPreferredSize().width+800,getPreferredSize().height+426); 
          setTitle(resourceBundle.getString("CollectedGraphViewer"));
        Container container = getContentPane();
        container.setLayout(new BorderLayout()); 
        try 
        { 
          initVariables(); 
          setUpGUI(container); 
          setUpProperties(); 
          setUpConnections(); 
        } 
        catch(Exception ex) 
        { 
          showStatus(resourceBundle.getString("Error in init method"),ex); 
        } 
        // let us set the initialized variable to true so  
        // we dont initialize again even if init is called 
        initialized = true; 

  
         //<End_init>
 	setSize(987,517);
 	setInitialSettings();

 	this.addWindowListener(new WindowAdapter() {

                public void windowClosing(WindowEvent e) {
                     isAutoRefresh = false;
                    dispose();
                }
            });
     }
  public String getParameter(String input)
  {
           //<Begin_getParameter_String> 
	if (po != null) 
	 { 
	   if(po.getParameter(input) != null) 
	     {
	       return (String)po.getParameter(input); 
	     }
	 }
           String value = null;
           if ( applet != null)
           {    
                 value = applet.getParameter(input);
           }    
           else
           {    
                 value = (String)com.adventnet.apiutils.Utility.getParameter(input);
           }    
           if(value == null)
           {
            }
        return value;

  
         //<End_getParameter_String>
  }
  public void setUpProperties()
  {
  //<Begin_setUpProperties> 

          try
          {
            Top.setMinimumSize(new Dimension(10,10));
            Top.setPreferredSize(new Dimension(950,486));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+Top,ex); 
          }

//<UserCode_Begin_Bean_components>

//<UserCode_End_Bean_components>

          try
          {
            mainPanel.setPreferredSize(new Dimension(347,546));
            mainPanel.setMinimumSize(new Dimension(347,546));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+mainPanel,ex); 
          }

//<UserCode_Begin_Bean_mainPanel>

//<UserCode_End_Bean_mainPanel>

          try
          {
            graphPanel.setPreferredSize(new Dimension(230,4));
            graphPanel.setMinimumSize(new Dimension(230,4));
            graphPanel.setBorder(new javax.swing.border.BevelBorder(0));
            graphPanel.setMaximumSize(new Dimension(32767,32767));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+graphPanel,ex); 
          }

//<UserCode_Begin_Bean_graphPanel>

//<UserCode_End_Bean_graphPanel>

          try
          {
            dataName.setForeground(new Color(-16777216));
            dataName.setHorizontalTextPosition(0);
            dataName.setHorizontalAlignment(0);
            dataName.setFont(new Font("Bitstream Charter",1,16));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+dataName,ex); 
          }

//<UserCode_Begin_Bean_dataName>

//<UserCode_End_Bean_dataName>

          try
          {
            graphTypePanel.setBorder(new javax.swing.border.BevelBorder(1));
            graphTypePanel.setMinimumSize(new Dimension(62,440));
            graphTypePanel.setPreferredSize(new Dimension(62,440));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+graphTypePanel,ex); 
          }

//<UserCode_Begin_Bean_graphTypePanel>

//<UserCode_End_Bean_graphTypePanel>

          try
          {
            lineButton.setFont(new Font("SansSerif",0,12));
            lineButton.setHorizontalTextPosition(4);
            lineButton.setToolTipText(resourceBundle.getString("Line Chart"));
            lineButton.setText(resourceBundle.getString(""));
            lineButton.setPreferredSize(new Dimension(50,50));
            lineButton.setMaximumSize(new Dimension(50,50));
            lineButton.setMinimumSize(new Dimension(50,50));
            lineButton.setActionCommand(resourceBundle.getString("lineButton"));
            lineButton.setIcon(com.adventnet.apiutils.Utility.findImage("./images/graphs/line.png",applet,true));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+lineButton,ex); 
          }

//<UserCode_Begin_Bean_lineButton>
	//For the  Action command not to be internationalized
	lineButton.setActionCommand("lineButton");
//<UserCode_End_Bean_lineButton>

          try
          {
            barButton.setFont(new Font("SansSerif",0,12));
            barButton.setHorizontalTextPosition(4);
            barButton.setActionCommand(resourceBundle.getString("barButton"));
            barButton.setToolTipText(resourceBundle.getString("Bar Chart"));
            barButton.setIcon(com.adventnet.apiutils.Utility.findImage("./images/graphs/bargraph_48.png",applet,true));
            barButton.setMaximumSize(new Dimension(50,50));
            barButton.setMinimumSize(new Dimension(50,50));
            barButton.setPreferredSize(new Dimension(50,50));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+barButton,ex); 
          }

//<UserCode_Begin_Bean_barButton>
	//For the  Action command not to be internationalized
	barButton.setActionCommand("barButton");
//<UserCode_End_Bean_barButton>

          try
          {
            areaButton.setFont(new Font("SansSerif",0,12));
            areaButton.setHorizontalTextPosition(4);
            areaButton.setActionCommand(resourceBundle.getString("areaButton"));
            areaButton.setToolTipText(resourceBundle.getString("Area Chart"));
            areaButton.setIcon(com.adventnet.apiutils.Utility.findImage("./images/graphs/area_48.png",applet,true));
            areaButton.setMaximumSize(new Dimension(50,50));
            areaButton.setMinimumSize(new Dimension(50,50));
            areaButton.setPreferredSize(new Dimension(50,50));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+areaButton,ex); 
          }

//<UserCode_Begin_Bean_areaButton>
	//For the  Action command not to be internationalized
	areaButton.setActionCommand("areaButton");
//<UserCode_End_Bean_areaButton>

          try
          {
            scatterButton.setFont(new Font("SansSerif",0,12));
            scatterButton.setHorizontalTextPosition(4);
            scatterButton.setActionCommand(resourceBundle.getString("scatterButton"));
            scatterButton.setIcon(com.adventnet.apiutils.Utility.findImage("./images/graphs/scatter_48.png",applet,true));
            scatterButton.setToolTipText(resourceBundle.getString("Scatter Chart"));
            scatterButton.setMaximumSize(new Dimension(50,50));
            scatterButton.setMinimumSize(new Dimension(50,50));
            scatterButton.setPreferredSize(new Dimension(50,50));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+scatterButton,ex); 
          }

//<UserCode_Begin_Bean_scatterButton>
	//For the  Action command not to be internationalized
	scatterButton.setActionCommand("scatterButton");
//<UserCode_End_Bean_scatterButton>

          try
          {
            xystepButton.setFont(new Font("SansSerif",0,12));
            xystepButton.setHorizontalTextPosition(4);
            xystepButton.setActionCommand(resourceBundle.getString("xystepButton"));
            xystepButton.setMaximumSize(new Dimension(50,50));
            xystepButton.setMinimumSize(new Dimension(50,50));
            xystepButton.setPreferredSize(new Dimension(50,50));
            xystepButton.setToolTipText(resourceBundle.getString("XYStep Chart"));
            xystepButton.setIcon(com.adventnet.apiutils.Utility.findImage("./images/graphs/xystep_48.png",applet,true));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+xystepButton,ex); 
          }

//<UserCode_Begin_Bean_xystepButton>
	//For the  Action command not to be internationalized
	xystepButton.setActionCommand("xystepButton");
//<UserCode_End_Bean_xystepButton>

          try
          {
            tableButton.setFont(new Font("SansSerif",0,12));
            tableButton.setHorizontalTextPosition(4);
            tableButton.setActionCommand(resourceBundle.getString("tableButton"));
            tableButton.setToolTipText(resourceBundle.getString("Table"));
            tableButton.setIcon(com.adventnet.apiutils.Utility.findImage("./images/graphs/table.png",applet,true));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+tableButton,ex); 
          }

//<UserCode_Begin_Bean_tableButton>
	//For the  Action command not to be internationalized
	tableButton.setActionCommand("tableButton");
//<UserCode_End_Bean_tableButton>

          try
          {
            toolBarPanel.setBorder(new javax.swing.border.EtchedBorder(1));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+toolBarPanel,ex); 
          }

//<UserCode_Begin_Bean_toolBarPanel>

//<UserCode_End_Bean_toolBarPanel>

          try
          {
            toolBar1Panel.setMaximumSize(new Dimension(101,53));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+toolBar1Panel,ex); 
          }

//<UserCode_Begin_Bean_toolBar1Panel>

//<UserCode_End_Bean_toolBar1Panel>

          try
          {
            saveButton.setFont(new Font("SansSerif",0,12));
            saveButton.setHorizontalTextPosition(4);
            saveButton.setMaximumSize(new Dimension(35,35));
            saveButton.setMinimumSize(new Dimension(35,35));
            saveButton.setPreferredSize(new Dimension(35,35));
            saveButton.setToolTipText(resourceBundle.getString("Save"));
            saveButton.setIcon(com.adventnet.apiutils.Utility.findImage("./images/graphs/save.png",applet,true));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+saveButton,ex); 
          }

//<UserCode_Begin_Bean_saveButton>
	if (NmsClientUtil.getClientType()!="APPLICATIONCLIENT")
		{
		    saveButton.setEnabled(false);
		}

//<UserCode_End_Bean_saveButton>

          try
          {
            clearGraphButton.setFont(new Font("SansSerif",0,12));
            clearGraphButton.setHorizontalTextPosition(4);
            clearGraphButton.setMaximumSize(new Dimension(35,35));
            clearGraphButton.setMinimumSize(new Dimension(35,35));
            clearGraphButton.setPreferredSize(new Dimension(35,35));
            clearGraphButton.setText(resourceBundle.getString(""));
            clearGraphButton.setToolTipText(resourceBundle.getString("Clear Graph"));
            clearGraphButton.setIcon(com.adventnet.apiutils.Utility.findImage("./images/graphs/clear.png",applet,true));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+clearGraphButton,ex); 
          }

//<UserCode_Begin_Bean_clearGraphButton>

//<UserCode_End_Bean_clearGraphButton>

          try
          {
            JLabel1.setHorizontalAlignment(2);
            JLabel1.setForeground(new Color(-16777216));
            JLabel1.setHorizontalTextPosition(4);
            JLabel1.setText(resourceBundle.getString("Web NMS JVM Monitoring"));
            JLabel1.setIconTextGap(4);
            JLabel1.setFont(new Font("sansserif",1,14));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JLabel1,ex); 
          }

//<UserCode_Begin_Bean_JLabel1>
JLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
//<UserCode_End_Bean_JLabel1>

          try
          {
            toolBar2Panel.setMaximumSize(new Dimension(101,53));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+toolBar2Panel,ex); 
          }

//<UserCode_Begin_Bean_toolBar2Panel>

//<UserCode_End_Bean_toolBar2Panel>

          try
          {
            helpButton.setFont(new Font("SansSerif",0,12));
            helpButton.setHorizontalTextPosition(4);
            helpButton.setMaximumSize(new Dimension(35,35));
            helpButton.setMinimumSize(new Dimension(35,35));
            helpButton.setPreferredSize(new Dimension(35,35));
            helpButton.setIcon(com.adventnet.apiutils.Utility.findImage("./images/graphs/help.png",applet,true));
            helpButton.setFocusPainted(true);
            helpButton.setToolTipText(resourceBundle.getString("Help"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+helpButton,ex); 
          }

//<UserCode_Begin_Bean_helpButton>

//<UserCode_End_Bean_helpButton>

          try
          {
            closeButton.setFont(new Font("SansSerif",0,12));
            closeButton.setHorizontalTextPosition(4);
            closeButton.setMaximumSize(new Dimension(35,35));
            closeButton.setMinimumSize(new Dimension(35,35));
            closeButton.setPreferredSize(new Dimension(35,35));
            closeButton.setIcon(com.adventnet.apiutils.Utility.findImage("./images/graphs/exit.png",applet,true));
            closeButton.setText(resourceBundle.getString(""));
            closeButton.setToolTipText(resourceBundle.getString("Exit"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+closeButton,ex); 
          }

//<UserCode_Begin_Bean_closeButton>

//<UserCode_End_Bean_closeButton>

          try
          {
            JComboBox1.setFont(new Font("SansSerif",0,12));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+JComboBox1,ex); 
          }

//<UserCode_Begin_Bean_JComboBox1>
JComboBox1.setBackground(new Color(-1));
Enumeration en = JVMGraphInvoker.pDataNameVsDetails.keys();
while(en.hasMoreElements())
{
     JComboBox1.addItem(en.nextElement());
}
/*try
{
      PollAPI pAPI = (PollAPI)java.rmi.Naming.lookup("//gnanasekar:1099/PollAPI");
      Vector pollVec = pAPI.getCompleteList();
      String pDataName = null;
      pDataNameVsDetails = new Hashtable();
      for(int i=0;i<pollVec.size();i++)
      {
            pDataName = (String)pollVec.elementAt(i);
            if(pDataName.endsWith("RestrictedPolledData"))
            {
                 StringTokenizer st = new StringTokenizer(pDataName,"\t");
                 	String name = st.nextToken();
                 pDataNameVsDetails.put(name,pDataName);
                 JComboBox1.addItem(name);
            }	
      }
}
catch(Exception e)
{
     e.printStackTrace();
}*/
//<UserCode_End_Bean_JComboBox1>

          try
          {
            rangeSettingsPanel.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(0),resourceBundle.getString("Date Range Settings"),0,2,new Font("dialog.bold",0,12),new Color(-13434829)));
            rangeSettingsPanel.setPreferredSize(new Dimension(245,90));
            rangeSettingsPanel.setMinimumSize(new Dimension(245,90));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+rangeSettingsPanel,ex); 
          }

//<UserCode_Begin_Bean_rangeSettingsPanel>

//<UserCode_End_Bean_rangeSettingsPanel>

          try
          {
            customRange.setFont(new Font("SansSerif",0,12));
            customRange.setHorizontalTextPosition(4);
            customRange.setHorizontalAlignment(2);
            customRange.setVerticalAlignment(0);
            customRange.setVerticalTextPosition(0);
            customRange.setText(resourceBundle.getString("Custom     "));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+customRange,ex); 
          }

//<UserCode_Begin_Bean_customRange>

//<UserCode_End_Bean_customRange>

          try
          {
            defaultRange.setFont(new Font("SansSerif",0,12));
            defaultRange.setHorizontalTextPosition(4);
            defaultRange.setHorizontalAlignment(2);
            defaultRange.setVerticalAlignment(0);
            defaultRange.setVerticalTextPosition(0);
            defaultRange.setText(resourceBundle.getString("Last 24hrs "));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+defaultRange,ex); 
          }

//<UserCode_Begin_Bean_defaultRange>


//<UserCode_End_Bean_defaultRange>

          try
          {
            plotButton.setFont(new Font("SansSerif",0,12));
            plotButton.setHorizontalTextPosition(4);
            plotButton.setText(resourceBundle.getString("Plot Chart"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+plotButton,ex); 
          }

//<UserCode_Begin_Bean_plotButton>

//<UserCode_End_Bean_plotButton>

          try
          {
            todayRange.setFont(new Font("SansSerif",0,12));
            todayRange.setHorizontalTextPosition(4);
            todayRange.setHorizontalAlignment(2);
            todayRange.setVerticalAlignment(0);
            todayRange.setVerticalTextPosition(0);
            todayRange.setText(resourceBundle.getString("Today"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+todayRange,ex); 
          }

//<UserCode_Begin_Bean_todayRange>

//<UserCode_End_Bean_todayRange>

          try
          {
            day7Range.setFont(new Font("SansSerif",0,12));
            day7Range.setHorizontalTextPosition(4);
            day7Range.setHorizontalAlignment(2);
            day7Range.setVerticalAlignment(0);
            day7Range.setVerticalTextPosition(0);
            day7Range.setText(resourceBundle.getString("Last one week"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+day7Range,ex); 
          }

//<UserCode_Begin_Bean_day7Range>

//<UserCode_End_Bean_day7Range>

          try
          {
            fromTimeComponent.setMaximumSize(new Dimension(210,22));
            fromTimeComponent.setMinimumSize(new Dimension(210,22));
            fromTimeComponent.setPreferredSize(new Dimension(210,22));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+fromTimeComponent,ex); 
          }

//<UserCode_Begin_Bean_fromTimeComponent>

//<UserCode_End_Bean_fromTimeComponent>

          try
          {
            fromLabel.setHorizontalAlignment(2);
            fromLabel.setFont(new Font("SansSerif",0,12));
            fromLabel.setForeground(new Color(-16777216));
            fromLabel.setHorizontalTextPosition(4);
            fromLabel.setText(resourceBundle.getString("From"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+fromLabel,ex); 
          }

//<UserCode_Begin_Bean_fromLabel>

//<UserCode_End_Bean_fromLabel>

          try
          {
            toTimeComponent.setMaximumSize(new Dimension(210,22));
            toTimeComponent.setMinimumSize(new Dimension(210,22));
            toTimeComponent.setPreferredSize(new Dimension(210,22));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+toTimeComponent,ex); 
          }

//<UserCode_Begin_Bean_toTimeComponent>

//<UserCode_End_Bean_toTimeComponent>

          try
          {
            toLabel.setHorizontalAlignment(2);
            toLabel.setFont(new Font("SansSerif",0,12));
            toLabel.setForeground(new Color(-16777216));
            toLabel.setHorizontalTextPosition(4);
            toLabel.setText(resourceBundle.getString("To"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+toLabel,ex); 
          }

//<UserCode_Begin_Bean_toLabel>

//<UserCode_End_Bean_toLabel>

          try
          {
            autorefresh.setFont(new Font("SansSerif",0,12));
            autorefresh.setHorizontalTextPosition(4);
            autorefresh.setHorizontalAlignment(2);
            autorefresh.setVerticalAlignment(0);
            autorefresh.setVerticalTextPosition(0);
            autorefresh.setText(resourceBundle.getString("Auto Refresh   "));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+autorefresh,ex); 
          }

//<UserCode_Begin_Bean_autorefresh>

//<UserCode_End_Bean_autorefresh>

          try
          {
            OptionDialogInformer1.setDialogType(2);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+OptionDialogInformer1,ex); 
          }

//<UserCode_Begin_Bean_OptionDialogInformer1>

//<UserCode_End_Bean_OptionDialogInformer1>
		plotButton.setPreferredSize(new Dimension(plotButton.getPreferredSize().width+9,plotButton.getPreferredSize().height+3));
		rangeSettingsPanel.setPreferredSize(new Dimension(rangeSettingsPanel.getPreferredSize().width+0,rangeSettingsPanel.getPreferredSize().height+333));
		JPanel1.setPreferredSize(new Dimension(JPanel1.getPreferredSize().width+10,JPanel1.getPreferredSize().height+10));
		toolBar2Panel.setPreferredSize(new Dimension(toolBar2Panel.getPreferredSize().width+25,toolBar2Panel.getPreferredSize().height+0));
		JLabel1.setPreferredSize(new Dimension(JLabel1.getPreferredSize().width+257,JLabel1.getPreferredSize().height+18));
		toolBarPanel.setPreferredSize(new Dimension(toolBarPanel.getPreferredSize().width+10,toolBarPanel.getPreferredSize().height+10));
		graphTypePanel.setPreferredSize(new Dimension(graphTypePanel.getPreferredSize().width+11,graphTypePanel.getPreferredSize().height+10));
		dataName.setPreferredSize(new Dimension(dataName.getPreferredSize().width+332,dataName.getPreferredSize().height+0));
		graphTablePanel.setPreferredSize(new Dimension(graphTablePanel.getPreferredSize().width+15,graphTablePanel.getPreferredSize().height+21));
		mainPanel.setPreferredSize(new Dimension(mainPanel.getPreferredSize().width+441,mainPanel.getPreferredSize().height+10));

  
          //<End_setUpProperties>

	lineButton.setBorder(new javax.swing.border.BevelBorder(0));

          saveButton.setActionCommand("save");
          clearGraphButton.setActionCommand("clear");
          helpButton.setActionCommand("help");
          closeButton.setActionCommand("close");

          defaultRange.setActionCommand("default");
          todayRange.setActionCommand("today");
          day7Range.setActionCommand("week");
          customRange.setActionCommand("custom");
	autorefresh.setActionCommand("autorefresh");

          rangeButtonGroup.add(defaultRange);
          rangeButtonGroup.add(customRange);
          rangeButtonGroup.add(todayRange);
          rangeButtonGroup.add(day7Range);
          rangeButtonGroup.add(autorefresh);
  }
  public void initVariables()
  {
        //<Begin_initVariables> 
	 if(po == null)
	{
	po = new com.adventnet.apiutils.ParameterObject();
	}
        Top= new javax.swing.JPanel();
        mainPanel= new javax.swing.JPanel();
        graphTablePanel= new javax.swing.JPanel();
        graphPanel= new javax.swing.JPanel();
        tablePanel= new javax.swing.JPanel();
        labelPanel= new javax.swing.JPanel();
        dataName= new javax.swing.JLabel();
        tablePane= new javax.swing.JScrollPane();
        tableView= new javax.swing.JTable();
        graphTypePanel= new javax.swing.JPanel();
        lineButton= new javax.swing.JButton();
        barButton= new javax.swing.JButton();
        areaButton= new javax.swing.JButton();
        scatterButton= new javax.swing.JButton();
        xystepButton= new javax.swing.JButton();
        tableButton= new javax.swing.JButton();
        toolBarPanel= new javax.swing.JPanel();
        toolBar1Panel= new javax.swing.JPanel();
        saveButton= new javax.swing.JButton();
        clearGraphButton= new javax.swing.JButton();
        JLabel1= new javax.swing.JLabel();
        toolBar2Panel= new javax.swing.JPanel();
        helpButton= new javax.swing.JButton();
        closeButton= new javax.swing.JButton();
        JPanel1= new javax.swing.JPanel();
        JComboBox1= new javax.swing.JComboBox();
        rangeSettingsPanel= new javax.swing.JPanel();
        customRange= new javax.swing.JRadioButton();
        defaultRange= new javax.swing.JRadioButton();
        plotButtonPanel= new javax.swing.JPanel();
        plotButton= new javax.swing.JButton();
        todayRange= new javax.swing.JRadioButton();
        day7Range= new javax.swing.JRadioButton();
        customRangePanel= new javax.swing.JPanel();
        fromTimeComponent= new com.adventnet.beans.probeans.ProDateTimeComponent();
        fromLabel= new javax.swing.JLabel();
        toTimeComponent= new com.adventnet.beans.probeans.ProDateTimeComponent();
        toLabel= new javax.swing.JLabel();
        autorefresh= new javax.swing.JRadioButton();
        rangeButtonGroup= new javax.swing.ButtonGroup();
        OptionDialogInformer1= new com.adventnet.beans.utilbeans.OptionDialogInformer();
        initializeParameters(); 

  
          //<End_initVariables>

		baseChart = new AdventNetLineChart(AdventNetBaseChart.LINECHART);
		setIconImage(NmsClientUtil.getParentFrame().getIconImage());

  }

   public void setUpGUI(Container container)
  {
//<Begin_setUpGUI_Container> 
container.add(Top,BorderLayout.CENTER);
Top.setLayout(new BorderLayout(5,5));
Top.add(mainPanel,BorderLayout.CENTER);
mainPanel.setLayout(new BorderLayout(5,5));
mainPanel.add(graphTablePanel,BorderLayout.CENTER);
graphTablePanel.setLayout(new CardLayout(5,5));
graphTablePanel.add(graphPanel,"graphView");
graphPanel.setLayout(new BorderLayout(5,5));
graphTablePanel.add(tablePanel,"TableView");
tablePanel.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
tablePanel.add(labelPanel,cons);
labelPanel.setLayout(new BorderLayout(5,5));
labelPanel.add(dataName,BorderLayout.CENTER);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
tablePanel.add(tablePane,cons);
tablePane.getViewport().add(tableView);
mainPanel.add(graphTypePanel,BorderLayout.WEST);
graphTypePanel.setLayout(new GridLayout(7,0,5,5));
graphTypePanel.add(lineButton);
graphTypePanel.add(barButton);
graphTypePanel.add(areaButton);
graphTypePanel.add(scatterButton);
graphTypePanel.add(xystepButton);
graphTypePanel.add(tableButton);
mainPanel.add(toolBarPanel,BorderLayout.NORTH);
toolBarPanel.setLayout(new BorderLayout(5,5));
toolBarPanel.add(toolBar1Panel,BorderLayout.CENTER);
toolBar1Panel.setLayout(new FlowLayout(0,5,5));
toolBar1Panel.add(saveButton);
toolBar1Panel.add(clearGraphButton);
toolBar1Panel.add(JLabel1);
toolBarPanel.add(toolBar2Panel,BorderLayout.EAST);
toolBar2Panel.setLayout(new FlowLayout(1,5,5));
toolBar2Panel.add(helpButton);
toolBar2Panel.add(closeButton);
mainPanel.add(JPanel1,BorderLayout.EAST);
JPanel1.setLayout(new BorderLayout(5,5));
JPanel1.add(JComboBox1,BorderLayout.NORTH);
JPanel1.add(rangeSettingsPanel,BorderLayout.CENTER);
rangeSettingsPanel.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,5);
setConstraints(0,4,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
rangeSettingsPanel.add(customRange,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
rangeSettingsPanel.add(defaultRange,cons);
inset = new Insets(20,0,10,0);
setConstraints(0,6,1,1,0.0,0.0,cons.EAST,cons.NONE,inset,0,0);
rangeSettingsPanel.add(plotButtonPanel,cons);
plotButtonPanel.setLayout(new FlowLayout(2,5,5));
plotButtonPanel.add(plotButton);
inset = new Insets(5,5,5,5);
setConstraints(0,2,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
rangeSettingsPanel.add(todayRange,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,3,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
rangeSettingsPanel.add(day7Range,cons);
inset = new Insets(0,5,5,0);
setConstraints(0,5,1,1,0.0,0.0,cons.EAST,cons.NONE,inset,0,0);
rangeSettingsPanel.add(customRangePanel,cons);
customRangePanel.setLayout(new GridBagLayout());
inset = new Insets(1,1,1,1);
setConstraints(0,1,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
customRangePanel.add(fromTimeComponent,cons);
inset = new Insets(1,1,1,1);
setConstraints(0,0,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
customRangePanel.add(fromLabel,cons);
inset = new Insets(1,1,1,1);
setConstraints(0,3,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
customRangePanel.add(toTimeComponent,cons);
inset = new Insets(1,1,1,1);
setConstraints(0,2,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
customRangePanel.add(toLabel,cons);
inset = new Insets(5,5,5,5);
setConstraints(0,0,1,1,0.0,0.0,cons.WEST,cons.NONE,inset,0,0);
rangeSettingsPanel.add(autorefresh,cons);

  
//<End_setUpGUI_Container>
  graphPanel.add(baseChart,BorderLayout.CENTER);
  }
  public void setUpConnections()
  {
  //<Begin_setUpConnections> 

      helpButton_helpButton_conn helpButton_helpButton_conn1 =  new helpButton_helpButton_conn();
      helpButton.addActionListener(helpButton_helpButton_conn1);
      defaultRange_autorefresh_conn defaultRange_autorefresh_conn1 =  new defaultRange_autorefresh_conn();
      defaultRange.addActionListener(defaultRange_autorefresh_conn1);
      PlotButton_PlotButton_conn PlotButton_PlotButton_conn1 =  new PlotButton_PlotButton_conn();
      plotButton.addActionListener(PlotButton_PlotButton_conn1);
      JButton1_JButton1_conn JButton1_JButton1_conn1 =  new JButton1_JButton1_conn();
      saveButton.addActionListener(JButton1_JButton1_conn1);
      todayRange_defaultRange_conn todayRange_defaultRange_conn1 =  new todayRange_defaultRange_conn();
      todayRange.addActionListener(todayRange_defaultRange_conn1);
      customRange_customRange_conn customRange_customRange_conn1 =  new customRange_customRange_conn();
      customRange.addActionListener(customRange_customRange_conn1);
      tableButton_tableButton_conn tableButton_tableButton_conn1 =  new tableButton_tableButton_conn();
      tableButton.addActionListener(tableButton_tableButton_conn1);
      day7Range_defaultRange_conn day7Range_defaultRange_conn1 =  new day7Range_defaultRange_conn();
      day7Range.addActionListener(day7Range_defaultRange_conn1);
      autorefresh_autorefresh_conn autorefresh_autorefresh_conn1 =  new autorefresh_autorefresh_conn();
      autorefresh.addActionListener(autorefresh_autorefresh_conn1);
      JButton2_JButton2_conn JButton2_JButton2_conn1 =  new JButton2_JButton2_conn();
      clearGraphButton.addActionListener(JButton2_JButton2_conn1);
      lineButton_graphPanel_conn lineButton_graphPanel_conn1 =  new lineButton_graphPanel_conn();
      lineButton.addActionListener(lineButton_graphPanel_conn1);
      barButton.addActionListener(lineButton_graphPanel_conn1);
      areaButton.addActionListener(lineButton_graphPanel_conn1);
      scatterButton.addActionListener(lineButton_graphPanel_conn1);
      xystepButton.addActionListener(lineButton_graphPanel_conn1);
      closeButton_closeButton_conn closeButton_closeButton_conn1 =  new closeButton_closeButton_conn();
      closeButton.addActionListener(closeButton_closeButton_conn1);
      DefaultRange_CustomRangePanel_conn DefaultRange_CustomRangePanel_conn1 =  new DefaultRange_CustomRangePanel_conn();
      customRange.addItemListener(DefaultRange_CustomRangePanel_conn1);
  
      //<End_setUpConnections>
  }




  public void showStatus(String message)
  {
     //<Begin_showStatus_String>
     System.out.println("Internal Error :"+ message);
     //<End_showStatus_String>
  }
  public void showStatus(String message,Exception ex)
  {
     //<Begin_showStatus_String_Exception>
     System.out.println("Internal Error :"+ message);
     ex.printStackTrace();
     //<End_showStatus_String_Exception>
  }




    

  

  public void setProperties(java.util.Properties props)
  {
         //<Begin_setProperties_java.util.Properties> 
	if(po != null)
	{
	po.setParameters(props);
	}
  
         //<End_setProperties_java.util.Properties>
  }
  private void initializeParameters()
  {
          //<Begin_initializeParameters> 
	 if(po != null) 
	   {
	    po.addParameterChangeListener(this);
	   }

  
          //<End_initializeParameters>
  }
  public void destroy()
  {
         //<Begin_destroy> 
	if(po != null)
	{
	 po.removeParameterChangeListener(this);
	}
  
         //<End_destroy>
  }
  public void setParameterObject(com.adventnet.apiutils.ParameterObject paramObj)
  {
         //<Begin_setParameterObject_com.adventnet.apiutils.ParameterObject> 
	this.po=paramObj;

  
         //<End_setParameterObject_com.adventnet.apiutils.ParameterObject>
  }
  public void parameterChanged(com.adventnet.apiutils.ParameterObject paramObj)
  {
  //<Begin_parameterChanged_com.adventnet.apiutils.ParameterObject> 

  
  //<End_parameterChanged_com.adventnet.apiutils.ParameterObject>
  }

    private void setInitialSettings()
    {
        lineButton.setSelected(true);
        defaultRange.setSelected(true);
        setCustomCompEnabled(false);
        baseChart.setXTimeAxis();
        showLineChart();
    }

    public void setUIVisible()
    {
        NmsClientUtil.centerWindow(this);
        super.setVisible(true);
    }

    public void setChartProperties(Properties props)
    {
        chartProperties = props;
        String title = props.getProperty("title");
        String subTitle = props.getProperty("subTitle");
        String xLabel = props.getProperty("xLabel");
        String yLabel = props.getProperty("yLabel");
        if(title!=null)
        {
            baseChart.setTitle(title);
        }
        if(subTitle!=null)
        {
            baseChart.getChart().clearSubtitles();
            baseChart.setSubTitle(subTitle);
        }
        if(xLabel!=null && yLabel!=null)
        {
            baseChart.setAxisLabels(resourceBundle.getString(xLabel), yLabel);
        }
    }

    public void processHelp()
    {
        NmsClientUtil.showHelpBasedOnKey("Poll_CollectedStatistics_Details");
    }

    public void processSave()
    {
        baseChart.save();
    }

    public void processClearChart()
    {
        baseChart.clearChart();
    }

    public void processExit()
    {
        dispose();
    }

    public void showLineChart()
    {
        //((CardLayout)graphPanel.getLayout()).show(graphPanel,"line");
    }

    public synchronized void fetchForNewRange()
    {
        long startTime = 0;
        long endTime = 0;

        ButtonModel bm = rangeButtonGroup.getSelection();
        String action = bm.getActionCommand();
        if(action.equalsIgnoreCase("default"))
        {
            endTime = System.currentTimeMillis();
            startTime = endTime - (24*60*60*1000);
        }
        else if(action.equalsIgnoreCase("today"))
        {
            endTime = System.currentTimeMillis();
            Calendar c = Calendar.getInstance();
            c.set(c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DATE),0,0,0);
            startTime = c.getTime().getTime();
        }
        else if(action.equalsIgnoreCase("week"))
        {
            endTime = System.currentTimeMillis();
            Calendar c = Calendar.getInstance();
            c.set(c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DATE)-7,0,0,0);
            startTime = c.getTime().getTime();
        }
        else if(action.equalsIgnoreCase("custom"))
        {
            try
            {
                java.util.Date date1 = fromTimeComponent.getDate();
                java.util.Date date2 = toTimeComponent.getDate();
                if(date1.after(date2))
                {
                    setDataNA("Date Range Setting incorrect [From-Date is greater than To-Date]");
                    return;
                }
                startTime = date1.getTime();
                endTime = date2.getTime();
            }
            catch(Exception e)
            {
                e.printStackTrace();
                return;
            }
        }
        
        else if(action.equalsIgnoreCase("autorefresh"))
        {
             
              endTime = System.currentTimeMillis();
            Calendar c = Calendar.getInstance();
            c.set(c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DATE),0,0,0);
            startTime = c.getTime().getTime();            
            /*try
            {
                java.util.Date date1 = toTimeComponent.getDate();
                java.util.Date date2 = new Date(System.currentTimeMillis());
                if(date1.after(date2))
                {
                    setDataNA("Date Range Setting incorrect [From-Date is greater than To-Date]");
                    return;
                }
                startTime = date1.getTime();
                endTime = date2.getTime();
            }
            catch(Exception e)
            {
                e.printStackTrace();
                return;
            }*/
        }
        fromTimeComponent.setDate(new Date(startTime));
        toTimeComponent.setDate(new Date(endTime));
        Thread th = new Thread(this,"Snmp Collected Statistics Event Handler");
        th.start();
    }

    public void run()
    {
        NmsClientUtil.busyCursor(this);
        long startTime=0;
        long endTime = 0;
        try
        {
            java.util.Date date1 = fromTimeComponent.getDate();
            startTime= date1.getTime();
            java.util.Date date2 = toTimeComponent.getDate();
            endTime = date2.getTime();
        }
        catch(Exception e)
        {
            setDataNA("Select an date for which you like  to collect Polled Data.");
            NmsClientUtil.normalCursor(this);
            return;
        }
        graphController.fetchData((String)JComboBox1.getSelectedItem(),startTime,endTime);
        NmsClientUtil.normalCursor(this);
        try
        {
            Thread.sleep(1000);
        }
        catch(Exception e1)
        {
        }
        plotButton.setEnabled(true);
    }

    private void setCustomCompEnabled(boolean b)
    {
        fromLabel.setEnabled(b);
        toLabel.setEnabled(b);
        fromTimeComponent.setEnabled(b);
        toTimeComponent.setEnabled(b);
    }

    public void setController(JVMGraphController cgc)
    {
        this.graphController = cgc;
    }

    public void disableViewer(String message)
    {
        baseChart.setMessage(resourceBundle.getString(message));
        saveButton.setEnabled(false);
        clearGraphButton.setEnabled(false);
        lineButton.setEnabled(false);
        barButton.setEnabled(false);
        areaButton.setEnabled(false);
        scatterButton.setEnabled(false);
        xystepButton.setEnabled(false);
        tableButton.setEnabled(false);
    }

    public synchronized void plotData(Hashtable data)
    {
       if (currentlyVisibleChart.equals("tableButton"))
        {
            plotTableData(data);
            return;
        }
        baseChart.clearChart();

        if ( data == null )
        {
            setDataNA();
            return;
        }
        try
        {
            plotData = data;
            int count = data.size();
            String series [] = new String[count];
            double [][]xVals = null;
            double [][]yVals = null;
            boolean firstTime=true;
            int x =0;
            for(Enumeration e=data.keys();e.hasMoreElements();)
            {
                String seriesName = (String)e.nextElement();
                CollectedData cdata = (CollectedData)data.get(seriesName);

                series[x] = seriesName;
                Long[] times = cdata.getTimes();
                Long[] values = (Long[])cdata.getValues();
                if(firstTime)
                {
                    int num = times.length;
                    xVals = new double[count][num];
                    yVals = new double[count][num];
                    firstTime = false;
                }
                for(int y=0;y<times.length;y++)
                {
                    xVals[x][y] = times[y].doubleValue();
                    yVals[x][y] = values[y].doubleValue();
                }
                x++;
            }
            baseChart.setData(series, xVals, yVals, true);
            lineButton.setEnabled(true);
            barButton.setEnabled(true);
            areaButton.setEnabled(true);
            scatterButton.setEnabled(true);
            xystepButton.setEnabled(true);
            tableButton.setEnabled(true);
            if (NmsClientUtil.getClientType().equals("APPLICATIONCLIENT"))
            {
                saveButton.setEnabled(true);
            }
            clearGraphButton.setEnabled(true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void setDataNA()
    {
	    baseChart.clearChart();
	    disableViewer("No data available");
    }

    public void setDataNA(String message)
    {
     	baseChart.clearChart();
     	//baseChart.setMessage(resourceBundle.getString(message));
     	disableViewer(message);
    }

//<Begin__class_helpButton_helpButton_conn>

 class helpButton_helpButton_conn implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_helpButton_helpButton_conn>

     //Listener Interface Methods Are Added Below


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
          processHelp();
     }
//<UserCode_End_Connection_helpButton_helpButton_conn>
 }//<End__class_helpButton_helpButton_conn>


  public void setConstraints(int x,int y,int width,int height,double wtX,double wtY,int anchor,int fill,Insets inset,int padX,int padY )
  {
         //<Begin_setConstraints_int_int_int_int_double_double_int_int_Insets_int_int> 
	cons.gridx = x;
	cons.gridy = y;
	cons.gridwidth = width;
	cons.gridheight = height;
	cons.weightx = wtX;
	cons.weighty = wtY;
	cons.anchor = anchor;
	cons.fill = fill;
	cons.insets = inset;
	cons.ipadx = padX;
	cons.ipady = padY;
	
  
         //<End_setConstraints_int_int_int_int_double_double_int_int_Insets_int_int>
  }


public void showErrorMessage()
{
	OptionDialogInformer1.showOptionDialog( this, resourceBundle.getString("Error Message"), 0,2, resourceBundle.getString("No JVM Monitoring polled data is available"),resourceBundle.getString("Start Web NMS server or Application Client with JDK1.5 or higher version to get JVM Monitoring Polled Data. ") );
}


//<Begin__class_PlotButton_PlotButton_conn>

 class PlotButton_PlotButton_conn implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_PlotButton_PlotButton_conn>



     //Listener Interface Methods Are Added Below


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
         plotButton.setEnabled(false);
          if(JComboBox1.getSelectedItem() != null && (!JComboBox1.getSelectedItem().equals("")))
          {
               if(!isAutoRefresh)
               {                  
                   fetchForNewRange();                        
               }
               else
               {
                   Runnable run = new Runnable()
                   {
                       public void run()
                       {
                           while(isAutoRefresh)
                           {
                               fetchForNewRange();
                               try
                               {
                                   Thread.sleep(15000);
                               }
                               catch(Exception e)
                               {
                               }
                           }
                       }
                       };
                   Thread t = new Thread(run);
                   t.start();
               }
          }
          else
          {
               	showErrorMessage();
          }          
     }
//<UserCode_End_Connection_PlotButton_PlotButton_conn>
 }//<End__class_PlotButton_PlotButton_conn>
//<Begin__class_JButton1_JButton1_conn>

 class JButton1_JButton1_conn implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton1_JButton1_conn>

     //Listener Interface Methods Are Added Below


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
          processSave();
     }
//<UserCode_End_Connection_JButton1_JButton1_conn>
 }//<End__class_JButton1_JButton1_conn>
//<Begin__class_JButton2_JButton2_conn>

 class JButton2_JButton2_conn implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_JButton2_JButton2_conn>

     //Listener Interface Methods Are Added Below


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
          processClearChart();
     }
//<UserCode_End_Connection_JButton2_JButton2_conn>
 }//<End__class_JButton2_JButton2_conn>
//<Begin__class_closeButton_closeButton_conn>

 class closeButton_closeButton_conn implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_closeButton_closeButton_conn>

     //Listener Interface Methods Are Added Below


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
          processExit();
     }
//<UserCode_End_Connection_closeButton_closeButton_conn>
 }//<End__class_closeButton_closeButton_conn>


//<Begin__class_DefaultRange_CustomRangePanel_conn>

 class DefaultRange_CustomRangePanel_conn implements java.awt.event.ItemListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_DefaultRange_CustomRangePanel_conn>

     //Listener Interface Methods Are Added Below


     public void itemStateChanged( java.awt.event.ItemEvent arg0)
     {
          int state = arg0.getStateChange();
          if(state==ItemEvent.SELECTED)
          {
               setCustomCompEnabled(true);
          }
          else if(state==ItemEvent.DESELECTED)
          {
               setCustomCompEnabled(false);
          }
     }
//<UserCode_End_Connection_DefaultRange_CustomRangePanel_conn>
 }//<End__class_DefaultRange_CustomRangePanel_conn>


//<Begin__class_lineButton_graphPanel_conn>

 class lineButton_graphPanel_conn implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_lineButton_graphPanel_conn>

     //Listener Interface Methods Are Added Below


    public void actionPerformed( java.awt.event.ActionEvent arg0)
    {
        String command = arg0.getActionCommand();

        if(command.equals(currentlyVisibleChart))
        {
            return;
        }

        ((CardLayout)graphTablePanel.getLayout()).show(graphTablePanel,"graphView");

        saveButton.setEnabled(true);
        clearGraphButton.setEnabled(true);

        graphPanel.remove(baseChart);
        graphPanel.updateUI();

        if(command.equals("lineButton"))
        {
            baseChart = new AdventNetLineChart(AdventNetBaseChart.LINECHART);
        }
        else if(command.equals("barButton"))
        {
            baseChart = new AdventNetBarChart();
        }
        else if(command.equals("areaButton"))
        {
            baseChart = new AdventNetLineChart(AdventNetBaseChart.AREACHART);
        }
        else if(command.equals("scatterButton"))
        {
            baseChart = new AdventNetLineChart(AdventNetBaseChart.SCATTERCHART);
        }
        else if(command.equals("xystepButton"))
        {
            baseChart = new AdventNetLineChart(AdventNetBaseChart.XYSTEPCHART);
        }

        baseChart.setXTimeAxis();
        setChartProperties(chartProperties);
        graphPanel.add(baseChart, BorderLayout.CENTER);
        graphPanel.updateUI();
        currentlyVisibleChart = command;

        plotData(plotData);
    }
//<UserCode_End_Connection_lineButton_graphPanel_conn>
 }//<End__class_lineButton_graphPanel_conn>


//<Begin__class_tableButton_tableButton_conn>

 class tableButton_tableButton_conn implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_tableButton_tableButton_conn>

     //Listener Interface Methods Are Added Below


    public void actionPerformed( java.awt.event.ActionEvent arg0)
    {
        String command = arg0.getActionCommand();

        if (command.equals(currentlyVisibleChart))
        {
            return;
        }
        showTable(command, plotData);
    }

//<UserCode_End_Connection_tableButton_tableButton_conn>
 }//<End__class_tableButton_tableButton_conn>

    private void plotTableData(Hashtable data)
    {
        if (data != null)
        {
            plotData = data;

            DefaultTableModel tableMdl = (DefaultTableModel)tableView.getModel();
            tableMdl.setDataVector(new Vector(),null);

            for (Enumeration e = data.keys(); e.hasMoreElements();)
            {
                String seriesName = (String)e.nextElement();

                CollectedData cdata = (CollectedData)data.get(seriesName);

                Long[] times = cdata.getTimes();

                int type = cdata.getType();

                Long[] longValues = null;
                String[] strValues = null;

                if (type == 1)
                {
                    longValues = (Long[])cdata.getValues();
                }
                else if (type == 2)
                {
                    strValues = (String[])cdata.getValues();
                }

                for (int i = 0; i < times.length; i++)
                {
                    Vector rowdata = new Vector();

                    rowdata.add(seriesName);

                    rowdata.add(new Date(times[i].longValue()).toString());

                    if (type == 1)
                    {
                        rowdata.add(longValues[i].toString());
                    }
                    else if (type == 2)
                    {
                        rowdata.add(strValues[i]);
                    }
                    tableMdl.addRow(rowdata);
                }
            }
        }
        else
        {
            setDataNA();
        }
    }

    public void plotStrTypeData(Hashtable data)
    {
        baseChart.clearChart();
        baseChart.setMessage(resourceBundle.getString("Cannot plot [Data is String type]"));
        saveButton.setEnabled(false);
        clearGraphButton.setEnabled(false);

        if (!customRange.isSelected())
        {
        	fromTimeComponent.setEnabled(false);
        	toTimeComponent.setEnabled(false);
        }
        lineButton.setEnabled(false);
        barButton.setEnabled(false);
        areaButton.setEnabled(false);
        scatterButton.setEnabled(false);
        xystepButton.setEnabled(false);
        showTable("tableButton", data);
    }

    void showTable(String command, Hashtable plotData)
    {
        ((CardLayout)graphTablePanel.getLayout()).show(graphTablePanel,"TableView");

        saveButton.setEnabled(false);
        clearGraphButton.setEnabled(false);
        constructTable();
        String title = chartProperties.getProperty("title");
        String subTitle = chartProperties.getProperty("subTitle");
        String desc = chartProperties.getProperty("yLabel");
        dataName.setText(title + " [ " + desc + " ] - " + subTitle);

        plotTableData(plotData);
        currentlyVisibleChart = command;
    }

    private void constructTable()
    {
        tableView.setModel(new DefaultTableModel()
        {
            public int getColumnCount()
            {
                return 3;
            }

            public String getColumnName(int col)
            {
                if (col == 0)
                {
                    return resourceBundle.getString("Instance");
                }
                else if(col == 1)
                {
                    return resourceBundle.getString("Time of Collection");
                }
                else
                {
                    return resourceBundle.getString("Value Collected");
                }
            }

            public boolean isCellEditable(int row,int col)
            {
                return false;
            }
        });
    }




    

  
  
  public void setSelectedPData(String selItem)
  {
       	this.selItem = selItem;
       	JComboBox1.setSelectedItem(selItem);
  }
  


//<Begin__class_todayRange_defaultRange_conn>

 class todayRange_defaultRange_conn implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_todayRange_defaultRange_conn>

     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
          isAutoRefresh = false;
          plotButton.setEnabled(true);
     }
//<UserCode_End_Connection_todayRange_defaultRange_conn>
 }//<End__class_todayRange_defaultRange_conn>
//<Begin__class_customRange_customRange_conn>

 class customRange_customRange_conn implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_customRange_customRange_conn>

     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
          isAutoRefresh = false;
          plotButton.setEnabled(true);
     }
//<UserCode_End_Connection_customRange_customRange_conn>
 }//<End__class_customRange_customRange_conn>
//<Begin__class_day7Range_defaultRange_conn>

 class day7Range_defaultRange_conn implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_day7Range_defaultRange_conn>

     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
          isAutoRefresh = false;
          plotButton.setEnabled(true);
     }
//<UserCode_End_Connection_day7Range_defaultRange_conn>
 }//<End__class_day7Range_defaultRange_conn>
//<Begin__class_autorefresh_autorefresh_conn>

 class autorefresh_autorefresh_conn implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_autorefresh_autorefresh_conn>

     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
          isAutoRefresh = true;
     }
//<UserCode_End_Connection_autorefresh_autorefresh_conn>
 }//<End__class_autorefresh_autorefresh_conn>

 


//<Begin__class_defaultRange_autorefresh_conn>

 class defaultRange_autorefresh_conn implements java.awt.event.ActionListener, java.io.Serializable 
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_defaultRange_autorefresh_conn>

     //Listener Interface Methods Are Added Below 


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
          isAutoRefresh = false;
          plotButton.setEnabled(true);
     }
//<UserCode_End_Connection_defaultRange_autorefresh_conn>
 }//<End__class_defaultRange_autorefresh_conn>




  public JVMDetailsViewer()
  {
      applet = NmsClientUtil.applet;
       init();
    //<Begin_JVMDetailsViewer>
    pack();
  
    //<End_JVMDetailsViewer>
  }

  public JVMDetailsViewer(java.applet.Applet applet)
  {
    //<Begin_JVMDetailsViewer_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  
    //<End_JVMDetailsViewer_java.applet.Applet>
  }
}


































































