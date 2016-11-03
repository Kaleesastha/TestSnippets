//$Id: CurrentGraphViewer.java,v 1.1.6.1 2012/04/05 08:26:06 wesley Exp $
// Please do not edit code in between the comments "//<Begin_...>" and "//<End_...>
// Any changes made within the comments will be lost ,if  regenerated.
// Kindly refer to tcats_edit_source_files.html file under help/source_generation directory for details




//<Begin_Version>
//version$50
//<End_Version>

package com.adventnet.nms.poll.graphs;

// import java classes
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.DecimalFormat;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

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


// import AdventNet classes
import com.adventnet.nms.util.NmsClientUtil;




public class CurrentGraphViewer extends JFrame  implements com.adventnet.apiutils.ParameterChangeListener
{
	//<Begin_Variable_Declarations>
	private boolean initialized = false;
	private java.applet.Applet applet = null;
	private String localePropertiesFileName = "JFreeChartsResources";
	static com.adventnet.apiutils.BuilderResourceBundle resourceBundle = null;
	private boolean running=false;
	javax.swing.JPanel Top = null;
	javax.swing.JPanel JPanel1 = null;
	javax.swing.JPanel toolBarPanel = null;
	javax.swing.JPanel toolBar1Panel = null;
	javax.swing.JButton saveButton = null;
	javax.swing.JButton clearGraphButton = null;
	javax.swing.JButton pollerButton = null;
	javax.swing.JLabel pollingLabel = null;
	com.adventnet.beans.utilbeans.NumericSpinControl pollInterval = null;
	javax.swing.JLabel PollingLabel1 = null;
	javax.swing.JPanel toolBar2Panel = null;
	javax.swing.JButton helpButton = null;
	javax.swing.JButton closeButton = null;
	javax.swing.JPanel graphTypePanel = null;
	javax.swing.JButton lineButton = null;
	javax.swing.JButton barButton = null;
	javax.swing.JButton areaButton = null;
	javax.swing.JButton scatterButton = null;
	javax.swing.JButton xystepButton = null;
	javax.swing.JButton tableButton = null;
	javax.swing.JPanel graphPanel = null;
	javax.swing.JPanel JPanel2 = null;
	javax.swing.JPanel labelPanel = null;
	javax.swing.JLabel dataName = null;
	javax.swing.JScrollPane tablePane = null;
	javax.swing.JTable tableView = null;
	private com.adventnet.apiutils.ParameterObject po= null;
	GridBagConstraints cons = new GridBagConstraints();
	Insets inset;
	//<End_Variable_Declarations>

	CurrentGraphController graphController = null;
	AdventNetBaseChart baseChart = null;
	private Properties chartProperties = null;
	private String currentlyVisibleChart = "lineButton";

/*
  public void setVisible(boolean bl)
  {
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
*/

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
        this.setSize(getPreferredSize().width+800,getPreferredSize().height+425);
          setTitle(resourceBundle.getString("CurrentGraphViewer"));
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
  setInitialSettings();
   this.addWindowListener(new WindowAdapter() {

                public void windowClosing(WindowEvent e) {
		    graphController.close();
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
            pollerButton.setFont(new Font("SansSerif",0,12));
            pollerButton.setHorizontalTextPosition(4);
            pollerButton.setPreferredSize(new Dimension(35,35));
            pollerButton.setMaximumSize(new Dimension(35,35));
            pollerButton.setMinimumSize(new Dimension(35,35));
            pollerButton.setToolTipText(resourceBundle.getString("Stop Poller"));
            pollerButton.setIcon(com.adventnet.apiutils.Utility.findImage("./images/graphs/stop.png",applet,true));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+pollerButton,ex);
          }

//<UserCode_Begin_Bean_pollerButton>

//<UserCode_End_Bean_pollerButton>

          try
          {
            pollingLabel.setHorizontalAlignment(2);
            pollingLabel.setFont(new Font("SansSerif",0,12));
            pollingLabel.setForeground(new Color(-16777216));
            pollingLabel.setHorizontalTextPosition(4);
            pollingLabel.setToolTipText(resourceBundle.getString(""));
            pollingLabel.setText(resourceBundle.getString("Polling interval"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+pollingLabel,ex);
          }

//<UserCode_Begin_Bean_pollingLabel>

//<UserCode_End_Bean_pollingLabel>

          try
          {
            pollInterval.setMaximumSize(new Dimension(50,35));
            pollInterval.setMinimumSize(new Dimension(50,35));
            pollInterval.setPreferredSize(new Dimension(50,35));
            pollInterval.setValue(0);
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+pollInterval,ex);
          }

//<UserCode_Begin_Bean_pollInterval>

//<UserCode_End_Bean_pollInterval>

          try
          {
            PollingLabel1.setHorizontalAlignment(2);
            PollingLabel1.setFont(new Font("SansSerif",0,12));
            PollingLabel1.setForeground(new Color(-16777216));
            PollingLabel1.setHorizontalTextPosition(4);
            PollingLabel1.setText(resourceBundle.getString("Seconds"));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+PollingLabel1,ex);
          }

//<UserCode_Begin_Bean_PollingLabel1>

//<UserCode_End_Bean_PollingLabel1>

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
            graphTypePanel.setBorder(new javax.swing.border.BevelBorder(1));
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
            lineButton.setIcon(com.adventnet.apiutils.Utility.findImage("./images/graphs/line.png",applet,true));
            lineButton.setToolTipText(resourceBundle.getString("Line Chart"));
            lineButton.setText(resourceBundle.getString(""));
            lineButton.setMaximumSize(new Dimension(50,50));
            lineButton.setMinimumSize(new Dimension(50,50));
            lineButton.setPreferredSize(new Dimension(50,50));
            lineButton.setActionCommand(resourceBundle.getString("lineButton"));
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
            barButton.setIcon(com.adventnet.apiutils.Utility.findImage("./images/graphs/bargraph_48.png",applet,true));
            barButton.setToolTipText(resourceBundle.getString("Bar Chart"));
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
            areaButton.setIcon(com.adventnet.apiutils.Utility.findImage("./images/graphs/area_48.png",applet,true));
            areaButton.setToolTipText(resourceBundle.getString("Area Chart"));
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
            xystepButton.setToolTipText(resourceBundle.getString("XYStep Chart"));
            xystepButton.setMaximumSize(new Dimension(50,50));
            xystepButton.setMinimumSize(new Dimension(50,50));
            xystepButton.setPreferredSize(new Dimension(50,50));
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
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+tableButton,ex);
          }

//<UserCode_Begin_Bean_tableButton>
	  tableButton.setActionCommand("tableButton");
	  tableButton.setIcon(com.adventnet.apiutils.Utility.findImage("./images/graphs/table.png",applet,true));
//<UserCode_End_Bean_tableButton>

          try
          {
            graphPanel.setBorder(new javax.swing.border.BevelBorder(0));
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
            dataName.setHorizontalAlignment(0);
            dataName.setHorizontalTextPosition(0);
            dataName.setFont(new Font("Bitstream Charter",1,16));
          }
          catch(Exception ex)
          {
             showStatus(resourceBundle.getString("Exception while setting properties for bean ")+dataName,ex);
          }

//<UserCode_Begin_Bean_dataName>

//<UserCode_End_Bean_dataName>
		dataName.setPreferredSize(new Dimension(dataName.getPreferredSize().width+601,dataName.getPreferredSize().height+17));
		graphPanel.setPreferredSize(new Dimension(graphPanel.getPreferredSize().width+10,graphPanel.getPreferredSize().height+10));
		graphTypePanel.setPreferredSize(new Dimension(graphTypePanel.getPreferredSize().width+15,graphTypePanel.getPreferredSize().height+10));
		toolBar2Panel.setPreferredSize(new Dimension(toolBar2Panel.getPreferredSize().width+25,toolBar2Panel.getPreferredSize().height+0));
		pollingLabel.setPreferredSize(new Dimension(pollingLabel.getPreferredSize().width+3,pollingLabel.getPreferredSize().height+0));
		toolBar1Panel.setPreferredSize(new Dimension(toolBar1Panel.getPreferredSize().width+127,toolBar1Panel.getPreferredSize().height+0));
		toolBarPanel.setPreferredSize(new Dimension(toolBarPanel.getPreferredSize().width+10,toolBarPanel.getPreferredSize().height+10));
		JPanel1.setPreferredSize(new Dimension(JPanel1.getPreferredSize().width+10,JPanel1.getPreferredSize().height+10));


          //<End_setUpProperties>

 	lineButton.setBorder(new javax.swing.border.BevelBorder(0));

          saveButton.setActionCommand("save");
          clearGraphButton.setActionCommand("clear");
          helpButton.setActionCommand("help");
          closeButton.setActionCommand("close");
          pollerButton.setActionCommand("stop");
  }
  public void initVariables()
  {
        //<Begin_initVariables>
	 if(po == null)
	{
	po = new com.adventnet.apiutils.ParameterObject();
	}
        Top= new javax.swing.JPanel();
        JPanel1= new javax.swing.JPanel();
        toolBarPanel= new javax.swing.JPanel();
        toolBar1Panel= new javax.swing.JPanel();
        saveButton= new javax.swing.JButton();
        clearGraphButton= new javax.swing.JButton();
        pollerButton= new javax.swing.JButton();
        pollingLabel= new javax.swing.JLabel();
        pollInterval= new com.adventnet.beans.utilbeans.NumericSpinControl();
        PollingLabel1= new javax.swing.JLabel();
        toolBar2Panel= new javax.swing.JPanel();
        helpButton= new javax.swing.JButton();
        closeButton= new javax.swing.JButton();
        graphTypePanel= new javax.swing.JPanel();
        lineButton= new javax.swing.JButton();
        barButton= new javax.swing.JButton();
        areaButton= new javax.swing.JButton();
        scatterButton= new javax.swing.JButton();
        xystepButton= new javax.swing.JButton();
        tableButton= new javax.swing.JButton();
        graphPanel= new javax.swing.JPanel();
        JPanel2= new javax.swing.JPanel();
        labelPanel= new javax.swing.JPanel();
        dataName= new javax.swing.JLabel();
        tablePane= new javax.swing.JScrollPane();
        tableView= new javax.swing.JTable();
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
Top.add(JPanel1,BorderLayout.CENTER);
JPanel1.setLayout(new BorderLayout(5,5));
JPanel1.add(toolBarPanel,BorderLayout.NORTH);
toolBarPanel.setLayout(new BorderLayout(5,5));
toolBarPanel.add(toolBar1Panel,BorderLayout.CENTER);
toolBar1Panel.setLayout(new FlowLayout(0,5,5));
toolBar1Panel.add(saveButton);
toolBar1Panel.add(clearGraphButton);
toolBar1Panel.add(pollerButton);
toolBar1Panel.add(pollingLabel);
toolBar1Panel.add(pollInterval);
toolBar1Panel.add(PollingLabel1);
toolBarPanel.add(toolBar2Panel,BorderLayout.EAST);
toolBar2Panel.setLayout(new FlowLayout(2,5,5));
toolBar2Panel.add(helpButton);
toolBar2Panel.add(closeButton);
JPanel1.add(graphTypePanel,BorderLayout.WEST);
graphTypePanel.setLayout(new GridLayout(7,0,5,5));
graphTypePanel.add(lineButton);
graphTypePanel.add(barButton);
graphTypePanel.add(areaButton);
graphTypePanel.add(scatterButton);
graphTypePanel.add(xystepButton);
graphTypePanel.add(tableButton);
JPanel1.add(graphPanel,BorderLayout.CENTER);
graphPanel.setLayout(new BorderLayout(5,5));
graphPanel.add(JPanel2,BorderLayout.CENTER);
JPanel2.setLayout(new GridBagLayout());
inset = new Insets(5,5,5,0);
setConstraints(0,0,1,1,0.1,0.0,cons.CENTER,cons.HORIZONTAL,inset,0,0);
JPanel2.add(labelPanel,cons);
labelPanel.setLayout(new BorderLayout(5,5));
labelPanel.add(dataName,BorderLayout.CENTER);
inset = new Insets(5,5,5,5);
setConstraints(0,1,1,1,0.1,0.1,cons.CENTER,cons.BOTH,inset,0,0);
JPanel2.add(tablePane,cons);
tablePane.getViewport().add(tableView);


//<End_setUpGUI_Container>
//graphPanel.add(lineChart,"line");
graphPanel.add(baseChart,BorderLayout.CENTER);
  }
  public void setUpConnections()
  {
  //<Begin_setUpConnections>

      helpButton_helpButton_conn helpButton_helpButton_conn1 =  new helpButton_helpButton_conn();
      helpButton.addActionListener(helpButton_helpButton_conn1);
      pollInterval_graphPanel_conn pollInterval_graphPanel_conn1 =  new pollInterval_graphPanel_conn();
      pollInterval.addActionListener(pollInterval_graphPanel_conn1);
      saveButton_saveButton_conn saveButton_saveButton_conn1 =  new saveButton_saveButton_conn();
      saveButton.addActionListener(saveButton_saveButton_conn1);
      tableButton_tableButton_conn tableButton_tableButton_conn1 =  new tableButton_tableButton_conn();
      tableButton.addActionListener(tableButton_tableButton_conn1);
      pollerButton_pollerButton_conn pollerButton_pollerButton_conn1 =  new pollerButton_pollerButton_conn();
      pollerButton.addActionListener(pollerButton_pollerButton_conn1);
      lineButton_graphPanel_conn lineButton_graphPanel_conn1 =  new lineButton_graphPanel_conn();
      lineButton.addActionListener(lineButton_graphPanel_conn1);
      barButton.addActionListener(lineButton_graphPanel_conn1);
      areaButton.addActionListener(lineButton_graphPanel_conn1);
      scatterButton.addActionListener(lineButton_graphPanel_conn1);
      xystepButton.addActionListener(lineButton_graphPanel_conn1);
      closeButton_closeButton_conn closeButton_closeButton_conn1 =  new closeButton_closeButton_conn();
      closeButton.addActionListener(closeButton_closeButton_conn1);
      clearGraphButton_clearGraphButton_conn clearGraphButton_clearGraphButton_conn1 =  new clearGraphButton_clearGraphButton_conn();
      clearGraphButton.addActionListener(clearGraphButton_clearGraphButton_conn1);

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





  public CurrentGraphViewer()
  {
	applet = NmsClientUtil.applet;
	init();
       /*
    //<Begin_CurrentGraphViewer>
    pack();

    //<End_CurrentGraphViewer>
 */

    //<Begin_CurrentGraphViewer>
    pack();

    //<End_CurrentGraphViewer>
  }

  public CurrentGraphViewer(java.applet.Applet applet)
  {
    //<Begin_CurrentGraphViewer_java.applet.Applet>
    this.applet = applet;
    pack();
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    //<End_CurrentGraphViewer_java.applet.Applet>
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

    public void setUIVisible()
    {
        NmsClientUtil.centerWindow(this);
        setVisible(true);
    }

    private void setInitialSettings()
    {
        lineButton.setSelected(true);
        baseChart.setXTimeAxis();
        baseChart.clearChart();
    }

    public void  setChartProperties(Properties props)
    {
        chartProperties = props;
        String title = props.getProperty("title");
        String subTitle = props.getProperty("subTitle");
        String xLabel = props.getProperty("xLabel");
        String yLabel = props.getProperty("yLabel");

        if (title != null)
        {
            baseChart.setTitle(title);
        }

        if (subTitle != null)
        {
            baseChart.setSubTitle(subTitle);
        }

        if (xLabel != null && yLabel != null)
        {
            baseChart.setAxisLabels(resourceBundle.getString(xLabel), yLabel);
        }
    }

    public void setPollPeriod(int p)
    {
        pollInterval.setValue(p);
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
        graphController.close();
    }

    public void changePollerStatus(String action)
    {
        if (action.equals("stop"))
        {
            graphController.stopPoll();
            pollerButton.setIcon(com.adventnet.apiutils.Utility.findImage("./images/graphs/start.png",applet,true));
            pollerButton.setActionCommand("start");
            pollerButton.setToolTipText("Start Poller");
        }
        else
        {
            pollerButton.setIcon(com.adventnet.apiutils.Utility.findImage("./images/graphs/stop.png",applet,true));
            pollerButton.setActionCommand("stop");
            pollerButton.setToolTipText("Stop Poller");
            setPollerInterval();
            graphController.restartPoll();
        }
    }

    public void setPollerInterval()
    {
        int i = (new Long(pollInterval.getValue())).intValue();
        graphController.setPollInterval(i);
    }

    public void setController(CurrentGraphController cgc)
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
        pollerButton.setEnabled(false);
        pollInterval.setEnabled(false);
        tableButton.setEnabled(false);
    }

    public void disableStringTypeFields(String message)
    {
        System.out.println("disable string type field: "+message);
        baseChart.setMessage(resourceBundle.getString(message));
        saveButton.setEnabled(false);
        clearGraphButton.setEnabled(false);
        lineButton.setEnabled(false);
        barButton.setEnabled(false);
        areaButton.setEnabled(false);
        scatterButton.setEnabled(false);
        xystepButton.setEnabled(false);
        //String type supported in table view
        tableButton.setEnabled(true);
        pollerButton.setEnabled(true);
        pollInterval.setEnabled(true);

        showTable(baseChart.getData());

        currentlyVisibleChart = "tableButton";
    }

    public void setDataNA()
    {
        setDataNA("No data available");
    }

    public void setDataNA(String message)
    {
        baseChart.setMessage(resourceBundle.getString(message));
    }

    public void plotData(Hashtable data)
    {
        if (data != null)
        {
            if (tablePane.isVisible())
            {
                appendTableView(data);
            }

            int count = data.size();
            String series[] = new String[count];
            double yVals[] = new double[count];
            int num =0;

            for (Enumeration e = data.keys(); e.hasMoreElements();)
            {
                String s = (String)e.nextElement();
                Object obj = data.get(s);

                if (!(obj instanceof Double))
                {
                    //String type data, so only table view support
                    return;
                }
                double y = ((Double)(data.get(s))).doubleValue();
                series[num] = s;
                yVals[num] = y;
                num++;
            }
            baseChart.appendData(series, yVals);
        }
        else
        {
            setDataNA();
        }
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
//<Begin__class_saveButton_saveButton_conn>

 class saveButton_saveButton_conn implements java.awt.event.ActionListener, java.io.Serializable
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_saveButton_saveButton_conn>

     //Listener Interface Methods Are Added Below


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
          processSave();
     }
//<UserCode_End_Connection_saveButton_saveButton_conn>
 }//<End__class_saveButton_saveButton_conn>
//<Begin__class_clearGraphButton_clearGraphButton_conn>

 class clearGraphButton_clearGraphButton_conn implements java.awt.event.ActionListener, java.io.Serializable
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_clearGraphButton_clearGraphButton_conn>

     //Listener Interface Methods Are Added Below


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
          processClearChart();
     }
//<UserCode_End_Connection_clearGraphButton_clearGraphButton_conn>
 }//<End__class_clearGraphButton_clearGraphButton_conn>
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


//<Begin__class_pollerButton_pollerButton_conn>

 class pollerButton_pollerButton_conn implements java.awt.event.ActionListener, java.io.Serializable
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_pollerButton_pollerButton_conn>

     //Listener Interface Methods Are Added Below


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
          String action = arg0.getActionCommand();
          changePollerStatus(action);
     }
//<UserCode_End_Connection_pollerButton_pollerButton_conn>
 }//<End__class_pollerButton_pollerButton_conn>


//<Begin__class_lineButton_graphPanel_conn>

 class lineButton_graphPanel_conn implements java.awt.event.ActionListener, java.io.Serializable
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_lineButton_graphPanel_conn>


     //Listener Interface Methods Are Added Below


    public void actionPerformed( java.awt.event.ActionEvent arg0)
    {
        JPanel2.setVisible(false);
        tablePane.setVisible(false);
        labelPanel.setVisible(false);
        saveButton.setEnabled(true);
        clearGraphButton.setEnabled(true);

        String command = arg0.getActionCommand();

        if(command.equals(currentlyVisibleChart))
        {
            return;
        }

        Hashtable data = baseChart.getData();
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
        baseChart.setData(data);
        currentlyVisibleChart = command;
    }
//<UserCode_End_Connection_lineButton_graphPanel_conn>
 }//<End__class_lineButton_graphPanel_conn>



  public void setVisible(boolean bl)
  {
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


//<Begin__class_pollInterval_graphPanel_conn>

 class pollInterval_graphPanel_conn implements java.awt.event.ActionListener, java.io.Serializable
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_pollInterval_graphPanel_conn>

     //Listener Interface Methods Are Added Below


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
          setPollerInterval();
     }
//<UserCode_End_Connection_pollInterval_graphPanel_conn>
 }//<End__class_pollInterval_graphPanel_conn>


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


//<Begin__class_tableButton_tableButton_conn>

 class tableButton_tableButton_conn implements java.awt.event.ActionListener, java.io.Serializable
  {

     //<TOP_PART>
//<UserCode_Begin_Connection_tableButton_tableButton_conn>

     //Listener Interface Methods Are Added Below


     public void actionPerformed( java.awt.event.ActionEvent arg0)
     {
          String command = arg0.getActionCommand();
          if(command.equals(currentlyVisibleChart))
          {
               return;
          }
          Hashtable data = baseChart.getData();
          showTable(data);
          currentlyVisibleChart = command;
     }
//<UserCode_End_Connection_tableButton_tableButton_conn>
 }//<End__class_tableButton_tableButton_conn>

    private void showTable(Hashtable data)
    {
        graphPanel.remove(baseChart);
        saveButton.setEnabled(false);
        clearGraphButton.setEnabled(false);

        String title = chartProperties.getProperty("title");
        String job = chartProperties.getProperty("subTitle");
        String disName = chartProperties.getProperty("yLabel");
        dataName.setText(title + " [ " + disName + " ] - " + job);

        graphPanel.add(JPanel2, BorderLayout.CENTER);

        tableView = new JTable(new DefaultTableModel()
        {
            public int getColumnCount()
            {
                return 3;
            }

            public String getColumnName(int col)
            {
                if (col == 0)
                {
                    if (isMultiplePolledData)
                    {
                        return resourceBundle.getString("Instance");
                    }
                    else
                    {
                        return resourceBundle.getString("Data Identifier");
                    }
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

        tablePane.getViewport().add(tableView);

        JPanel2.setVisible(true);
        labelPanel.setVisible(true);
        tablePane.setVisible(true);
        graphPanel.updateUI();

        appendTableView(data);
    }

    private boolean isMultiplePolledData = false;

    public void setIsMultiplePolledData(boolean isMultiple)
    {
        this.isMultiplePolledData = isMultiple;
    }

    public boolean getIsMultiplePolledData()
    {
        return isMultiplePolledData;
    }

    private void appendTableView(Hashtable data)
    {
        DefaultTableModel tableMdl = (DefaultTableModel)tableView.getModel();
        DecimalFormat df = new DecimalFormat("0.####");

        for (Enumeration e = data.keys(); e.hasMoreElements();)
        {
            String seriesName = (String)e.nextElement();
            Object dataObj = data.get(seriesName);

            if (dataObj instanceof Vector)
            {
                Vector dataVec = (Vector)dataObj;
                int size = dataVec.size();

                for (int i = 0; i < size;)
                {
                    Double existTime = (Double)dataVec.get(i++);
                    String time = (new Date(existTime.longValue())).toString();

                    String existVal = null;
                    Object dObj = dataVec.get(i++);

                    if (dObj instanceof Double)
                    {
                    	
                        existVal = df.format((Double)dObj);
                    }
                    else if(dObj instanceof String)
                    {
                        existVal = (String)dObj;
                    }
                    String[] rowValues = {seriesName, time, existVal};
                    tableMdl.addRow(rowValues);
                }
            }
            else
            {
                String currentTime = (new Date(System.currentTimeMillis())).toString();
                String actData = null;

                if (dataObj instanceof Double)
                {
                	actData = df.format((Double)dataObj);
                }
                else if (dataObj instanceof String)
                {
                    actData = (String)dataObj;
                }
                String[] rowValues = {seriesName, currentTime, actData};
                tableMdl.addRow(rowValues);
            }
        }
    }
}























































