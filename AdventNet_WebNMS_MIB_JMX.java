 //$Id: AdventNet_WebNMS_MIB_JMX.java,v 1.19 2010/10/29 13:46:40 swaminathap Exp $
/* Copyright (c)  1996 - 2004  Adventnet, Inc. All Rights Reserved.
 * PLEASE READ THE ASSOCIATED COPYRIGHTS FILE FOR MORE DETAILS.
 * ADVENTNET, INC. MAKES NO REPRESENTATIONS OR WARRANTIES  ABOUT THE
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE, OR NON-INFRINGEMENT.
 * ADVENTNET, INC. SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE
 * AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE
 * OR ITS DERIVATIVES.
 */

/**
 * @Version :  6.0.0 Fri Mar 09 11:54:21 IST 2007
 * @Author  :  AdventNet Agent Toolkit Java Edition
 */

// Any changes made to this file will be lost, if regenerated.
// User code should be added within user tags for code merging support, if regenerated.

// Package Name (Dont Delete this comment)
package com.adventnet.nms.jmxagent;

////////////////////////////Importing the JAVA Packages////////////////////////
import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.net.InetAddress;
import java.net.URL;
import java.util.Vector;

///////////////////////////Importing the JMX API Packages///////////////////////
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;
import javax.management.RuntimeMBeanException;
import javax.management.modelmbean.RequiredModelMBean;
import javax.management.modelmbean.ModelMBeanNotificationBroadcaster;
import javax.management.loading.MLet;
import javax.jmx.openmbean.TabularData;
import javax.jmx.openmbean.CompositeData;

/////////////////////////Importing the AdventNet Packages///////////////////////
import com.adventnet.adaptors.rmi.RMIAdaptor;
import com.adventnet.adaptors.html.HtmlAdaptor;
import com.adventnet.adaptors.html.HtmlAdaptorServerImpl;
import com.adventnet.adaptors.corba.CorbaAdaptor;
import com.adventnet.adaptors.http.HttpConnector;
import com.adventnet.adaptors.http.HttpConnectorServerImpl;
import com.adventnet.adaptors.tl1.TL1Adaptor;
import com.adventnet.adaptors.tl1.TL1AdaptorInfo;
import com.adventnet.adaptors.tl1.Tl1CommIfcTable;
import com.adventnet.adaptors.tl1.Tl1TcpTable;
import com.adventnet.adaptors.tl1.security.*;
import com.adventnet.utilities.common.CommonUtils;
import com.adventnet.utils.jmx.JmxParamOptions;
import com.adventnet.utils.jmx.Utilities;

/////////////////////////Importing the AdventNet Snmp Packages///////////////////////
import com.adventnet.adaptors.snmp.AclTableMBean;
import com.adventnet.adaptors.snmp.ForwardingTableMBean;
import com.adventnet.adaptors.snmp.SnmpAdaptor;
import com.adventnet.adaptors.snmp.V3ForwardingTableMBean;
import com.adventnet.snmp.snmp2.agent.AclTable;
import com.adventnet.snmp.snmp2.agent.AgentUtil;
import com.adventnet.snmp.snmp2.agent.AgentRuntimeException;
import com.adventnet.snmp.snmp2.agent.SnmpTrapService;
import com.adventnet.snmp.snmp2.agent.ForwardingTable;
import com.adventnet.snmp.snmp2.agent.TrapRequestEvent;
import com.adventnet.snmp.snmp2.agent.V3ForwardingTable;


/////////////////////////Importing the AdventNet Logging Packages///////////////////////
import com.adventnet.utilities.logging.Level;
import com.adventnet.utilities.logging.LogFactory;
import com.adventnet.services.logger.LogFactoryController;

 // User code starts here
//by senthil
import com.adventnet.snmp.snmp2.agent.*;
import com.adventnet.nms.severity.*;
import com.adventnet.nms.topodb.*;
import com.adventnet.nms.store.relational.*;
import com.adventnet.nms.store.*;
import com.adventnet.management.transaction.PreparedStatementWrapper;
import java.sql.*;

//by shankar
import com.adventnet.nms.alertdb.*;
import com.adventnet.nms.eventdb.*;

//for snmpv3
import com.adventnet.snmp.snmp2.usm.*;
import com.adventnet.snmp.snmp2.vacm.*;
import com.adventnet.snmp.snmp2.*;

//import for cascader
import com.adventnet.services.cascading.*;

//for dynamicregistration
import com.adventnet.utils.agent.*;

//for Xml parsing
import com.adventnet.nms.util.*;

//for Html parsing
import javax.swing.text.html.*;
import javax.swing.text.html.parser.*;

//for log the print-outs
import com.adventnet.management.log.*;
import com.adventnet.agent.tl1.*;
import com.adventnet.nms.persistence.StorageAPI;

/**
 * This class is the main JMXAgent application.
 */
public class AdventNet_WebNMS_MIB_JMX
{
 private DynamicRegistration dynamicRegistration = null;

    //added by senthil

    //To store the def values
   StorageAPI userStorageApi = null;
    private static String jmxConfDir=PureUtils.rootDir+"conf" + File.separator + "jmx_agent" + File.separator + "conf";//No I18N

    //variables for other classes
    public String moUserPropNames = "";//No I18N
    public String moExtraPropNames = "";//No I18N

    //by shankar
    public String alertUserPropNames = "";//No I18N
    public String alertExtraPropNames = "";//No I18N
    public String eventUserPropNames = "";//No I18N
    public String statstableName ="";//No I18N

    public int sequNo =  0;
    public int maxRows = 5;//100;
    public int startIndex = 1;
    public int intLimit = 2147483647;

    public int logindex = 0 ;
    public int trapindex = 0 ;

    //these variables are used for accessing the AdventNetWebNMS database.
    com.adventnet.nms.topodb.TopoAPI topoAPI;
    com.adventnet.nms.alertdb.AlertAPI alertAPI;
    com.adventnet.nms.eventdb.EventAPI eventAPI;
    com.adventnet.nms.poll.PollAPI pollAPI;
    com.adventnet.nms.poll.PollMgr pollMgr;

    //by senthil
    TopoObserverImpl topoObsvrImpl = null;
    NotificationLogger notiLogger = null;
    //by shankar
    AlertObserverImpl alertObsvrImpl = null;
    EventObserverImpl eventObsvrImpl = null;

    TopoConfImpl topoConfImpl = null;
    PollFilterConfImpl pollFilterConfImpl = null;
    TrapConfImpl trapConfImpl = null;

    //by senthil
    //for create DB connection
    RelationalAPI rlAPI = null;

    int psSelectCountOfNotiLogID;
    int psSelectCountOfVarLogID;
    int psSelectFromNotiID;
    int psSelectFromVarID;
    int psInsertIntoNotiID;
    int psInsertIntoVarID;
    int psSelectMaxOfNotiLogID;
    int psSelectMinOfNotiLogID;
    int psDeleteRowOfNotiLogID;
    int psDeleteRowOfVarbindLogID;
    int psSelectNotiIndexOfNotiLogID;

    //for Mo table selection
    int psSelectMoID1;
    int psSelectMoID2;
    int psSelectMoID3;
    int psSelectMoID4;

    //by senthil
    //for cascading
    Cascader cascader = null;

    //for log the print-outs
    LogUser agentUser = null;
    LogUser agentErr = null;
     String[] args = null;

    boolean rmiAdaptor = false;
    boolean corbaAdaptor = false;
    boolean httpAdaptor = false;
    boolean htmlAdaptor = false;
    boolean snmpAdaptor = true;
    boolean tl1Adaptor = false;

    boolean topoObsvr = false;
    boolean faultObsvr = false;

    boolean authentication = true;

    boolean doPersist = false;
    boolean viewBasedAccess = false;
    boolean const_EngineID = false;
    // private int debugLevel = Level.DISABLED;

    String adventnetEnterpriseOID = ".1.3.6.1.4.1.2162.4";//No I18N
    static String enterpriseOID = "";//No I18N
 boolean newEnterpriseOID = true;
	// User code ends here
	///////////////////////////Class Level Variables////////////////////////

	/* Variable representing the Agent Working directory */
	public static String agentDir = ".";//No I18N

	/* Variable to act as a repository for the NotifHandlers.*/
	private static Hashtable notifTable = new Hashtable();

	/* Variable representing the NotificationListener object for various services.*/
	private NotificationListenerImpl notifImpl = null;

	/* The Server Repository for the JMXAgent */
	private MBeanServer server = null;

	/* Variables representing the different configurations for different adaptors.*/
	private RMIAdaptor rmiadaptor = null;
	// User code starts here
	/*
	private int rmiPort = 1099;
	*/
	static int rmiPort = 1099;
	// User code ends here

	private SnmpAdaptor snmpadaptor = null;
	// User code starts here
	/*
	private int snmpPort = 8001;
	*/
	static  int snmpPort = 8001;
	// User code ends here
	/* Variable to initiate traps from the Snmp interface of the JMXAgent */
	// User code starts here
	/*
	private SnmpTrapService trapListener = new SnmpTrapService ();
	*/
	private SnmpTrapForwader trapListener = null;
	// User code ends here
	private HtmlAdaptor htmladaptor= null;
	// User code starts here
	/*
	private int htmlPort = 8030;
	*/
	static int htmlPort=8030;
	// User code ends here
	/* Variables to indicate if SSL support and authentication is needed for the HtmlAdaptor */
	private boolean isHtmlSSL = false;
	private CorbaAdaptor corbaadaptor = null;
	// User code starts here
	/*
	private int corbaPort = 1050;
	*/
	static int corbaPort = 1050;
	// User code ends here

	private HttpConnector httpconnector= null;
	// User code starts here
	/*
	private int httpPort = 8050;
	*/
	 static int httpPort = 8050;
	private boolean isHttpSSL = false;

	private TL1Adaptor tl1adaptor = null;
	/*
	private int tl1Port = 9099;
	*/
	static int tl1Port = 9099;

	int debugLevel = Level.DISABLED;
	/* Variable to parse the Command line options while starting the Agent */
	private JmxParamOptions agentOptions = null;

	// Declaring all Generated MBeans and the getter methods to refer from the Agent application.
	private WebNMSSystemInstrument webNMSSystemInstrument = null;

	/**
	 * Returns the instance of the WebNMSSystemInstrument
	 */
	public WebNMSSystemInstrument getWebNMSSystemInstrument()
	{
		return webNMSSystemInstrument;
	}

	private WebNMSTopoMibInstrument webNMSTopoMibInstrument = null;

	/**
	 * Returns the instance of the WebNMSTopoMibInstrument
	 */
	public WebNMSTopoMibInstrument getWebNMSTopoMibInstrument()
	{
		return webNMSTopoMibInstrument;
	}

	private WebNMSFaultMibInstrument webNMSFaultMibInstrument = null;

	/**
	 * Returns the instance of the WebNMSFaultMibInstrument
	 */
	public WebNMSFaultMibInstrument getWebNMSFaultMibInstrument()
	{
		return webNMSFaultMibInstrument;
	}

	private WebNMSPerformanceMibInstrument webNMSPerformanceMibInstrument = null;

	/**
	 * Returns the instance of the WebNMSPerformanceMibInstrument
	 */
	public WebNMSPerformanceMibInstrument getWebNMSPerformanceMibInstrument()
	{
		return webNMSPerformanceMibInstrument;
	}

	private PersistentTrapsMibInstrument persistentTrapsMibInstrument = null;

	/**
	 * Returns the instance of the PersistentTrapsMibInstrument
	 */
	public PersistentTrapsMibInstrument getPersistentTrapsMibInstrument()
	{
		return persistentTrapsMibInstrument;
	}

	private TftpMibInstrument tftpMibInstrument = null;

	/**
	 * Returns the instance of the TftpMibInstrument
	 */
	public TftpMibInstrument getTftpMibInstrument()
	{
		return tftpMibInstrument;
	}

	private WebNMSPortsInstrument webNMSPortsInstrument = null;

	/**
	 * Returns the instance of the WebNMSPortsInstrument
	 */
	public WebNMSPortsInstrument getWebNMSPortsInstrument()
	{
		return webNMSPortsInstrument;
	}

	private WebNMSSchedulerTable webNMSSchedulerTable = null;

	/**
	 * Returns the instance of the WebNMSSchedulerTable
	 */
	public WebNMSSchedulerTable getWebNMSSchedulerTable()
	{
		return webNMSSchedulerTable;
	}
	private WebNMSMonitoringInstrument webNMSMonitoringInstrument = null;

	/**
	 * Returns the instance of the WebNMSMonitoringInstrument
	 */
	public WebNMSMonitoringInstrument getWebNMSMonitoringInstrument()
	{ 
		return webNMSMonitoringInstrument;
	}
	private MoTable moTable = null;

	/**
	 * Returns the instance of the MoTable
	 */
	public MoTable getMoTable()
	{
		return moTable;
	}

	private TopoObjTable topoObjTable = null;

	/**
	 * Returns the instance of the TopoObjTable
	 */
	public TopoObjTable getTopoObjTable()
	{
		return topoObjTable;
	}

	private NetworkTable networkTable = null;

	/**
	 * Returns the instance of the NetworkTable
	 */
	public NetworkTable getNetworkTable()
	{
		return networkTable;
	}

	private NodeTable nodeTable = null;

	/**
	 * Returns the instance of the NodeTable
	 */
	public NodeTable getNodeTable()
	{
		return nodeTable;
	}

	private IpAddressTable ipAddressTable = null;

	/**
	 * Returns the instance of the IpAddressTable
	 */
	public IpAddressTable getIpAddressTable()
	{
		return ipAddressTable;
	}

	private SnmpNodeTable snmpNodeTable = null;

	/**
	 * Returns the instance of the SnmpNodeTable
	 */
	public SnmpNodeTable getSnmpNodeTable()
	{
		return snmpNodeTable;
	}

	private SnmpInterfaceTable snmpInterfaceTable = null;

	/**
	 * Returns the instance of the SnmpInterfaceTable
	 */
	public SnmpInterfaceTable getSnmpInterfaceTable()
	{
		return snmpInterfaceTable;
	}

	private MoDerivedPropNameTable moDerivedPropNameTable = null;

	/**
	 * Returns the instance of the MoDerivedPropNameTable
	 */
	public MoDerivedPropNameTable getMoDerivedPropNameTable()
	{
		return moDerivedPropNameTable;
	}

	private MoNotificationMibInstrument moNotificationMibInstrument = null;

	/**
	 * Returns the instance of the MoNotificationMibInstrument
	 */
	public MoNotificationMibInstrument getMoNotificationMibInstrument()
	{
		return moNotificationMibInstrument;
	}

	private AlertTable alertTable = null;

	/**
	 * Returns the instance of the AlertTable
	 */
	public AlertTable getAlertTable()
	{
		return alertTable;
	}

	private AlertNotificationMibInstrument alertNotificationMibInstrument = null;

	/**
	 * Returns the instance of the AlertNotificationMibInstrument
	 */
	public AlertNotificationMibInstrument getAlertNotificationMibInstrument()
	{
		return alertNotificationMibInstrument;
	}

	private EventTable eventTable = null;

	/**
	 * Returns the instance of the EventTable
	 */
	public EventTable getEventTable()
	{
		return eventTable;
	}

	private WebNMSSeverityTable webNMSSeverityTable = null;

	/**
	 * Returns the instance of the WebNMSSeverityTable
	 */
	public WebNMSSeverityTable getWebNMSSeverityTable()
	{
		return webNMSSeverityTable;
	}

	private AlarmTable alarmTable = null;

	/**
	 * Returns the instance of the AlarmTable
	 */
	public AlarmTable getAlarmTable()
	{
		return alarmTable;
	}

	private PollTable pollTable = null;

	/**
	 * Returns the instance of the PollTable
	 */
	public PollTable getPollTable()
	{
		return pollTable;
	}

	private ThresholdTable thresholdTable = null;

	/**
	 * Returns the instance of the ThresholdTable
	 */
	public ThresholdTable getThresholdTable()
	{
		return thresholdTable;
	}

	private StatsdataInstrument statsdataInstrument = null;

	/**
	 * Returns the instance of the StatsdataInstrument
	 */
	public StatsdataInstrument getStatsdataInstrument()
	{
		return statsdataInstrument;
	}

	private NotiLogTable notiLogTable = null;

	/**
	 * Returns the instance of the NotiLogTable
	 */
	public NotiLogTable getNotiLogTable()
	{
		return notiLogTable;
	}

	private VarbindLogTable varbindLogTable = null;

	/**
	 * Returns the instance of the VarbindLogTable
	 */
	public VarbindLogTable getVarbindLogTable()
	{
		return varbindLogTable;
	}

	private ProxyTable proxyTable = null;

	/**
	 * Returns the instance of the ProxyTable
	 */
	public ProxyTable getProxyTable()
	{
		return proxyTable;
	}

	private SubAgentTable subAgentTable = null;

	/**
	 * Returns the instance of the SubAgentTable
	 */
	public SubAgentTable getSubAgentTable()
	{
		return subAgentTable;
	}

	private TopologyConfigurationInstrument topologyConfigurationInstrument = null;

	/**
	 * Returns the instance of the TopologyConfigurationInstrument
	 */
	public TopologyConfigurationInstrument getTopologyConfigurationInstrument()
	{
		return topologyConfigurationInstrument;
	}

	private TrapPortTable trapPortTable = null;

	/**
	 * Returns the instance of the TrapPortTable
	 */
	public TrapPortTable getTrapPortTable()
	{
		return trapPortTable;
	}

	private MoNotiVarbindsInstrument moNotiVarbindsInstrument = null;

	/**
	 * Returns the instance of the MoNotiVarbindsInstrument
	 */
	public MoNotiVarbindsInstrument getMoNotiVarbindsInstrument()
	{
		return moNotiVarbindsInstrument;
	}

	private AlertNotiVarbindsInstrument alertNotiVarbindsInstrument = null;

	/**
	 * Returns the instance of the AlertNotiVarbindsInstrument
	 */
	public AlertNotiVarbindsInstrument getAlertNotiVarbindsInstrument()
	{
		return alertNotiVarbindsInstrument;
	}

	private StatsDataTable statsDataTable = null;

	/**
	 * Returns the instance of the StatsDataTable
	 */
	public StatsDataTable getStatsDataTable()
	{
		return statsDataTable;
	}

	private PerfNotiVarbindsInstrument perfNotiVarbindsInstrument = null;

	/**
	 * Returns the instance of the PerfNotiVarbindsInstrument
	 */
	public PerfNotiVarbindsInstrument getPerfNotiVarbindsInstrument()
	{
		return perfNotiVarbindsInstrument;
	}

	private RediscoveryConfigurationInstrument rediscoveryConfigurationInstrument = null;

	/**
	 * Returns the instance of the RediscoveryConfigurationInstrument
	 */
	public RediscoveryConfigurationInstrument getRediscoveryConfigurationInstrument()
	{
		return rediscoveryConfigurationInstrument;
	}

	private SnmpPingConfigurationInstrument snmpPingConfigurationInstrument = null;

	/**
	 * Returns the instance of the SnmpPingConfigurationInstrument
	 */
	public SnmpPingConfigurationInstrument getSnmpPingConfigurationInstrument()
	{
		return snmpPingConfigurationInstrument;
	}

	private IcmpPingConfigurationInstrument icmpPingConfigurationInstrument = null;

	/**
	 * Returns the instance of the IcmpPingConfigurationInstrument
	 */
	public IcmpPingConfigurationInstrument getIcmpPingConfigurationInstrument()
	{
		return icmpPingConfigurationInstrument;
	}

	private NativePingConfigurationInstrument nativePingConfigurationInstrument = null;

	/**
	 * Returns the instance of the NativePingConfigurationInstrument
	 */
	public NativePingConfigurationInstrument getNativePingConfigurationInstrument()
	{
		return nativePingConfigurationInstrument;
	}

	private NetworkDiscoveryTable networkDiscoveryTable = null;

	/**
	 * Returns the instance of the NetworkDiscoveryTable
	 */
	public NetworkDiscoveryTable getNetworkDiscoveryTable()
	{
		return networkDiscoveryTable;
	}

	private NodeDiscoveryTable nodeDiscoveryTable = null;

	/**
	 * Returns the instance of the NodeDiscoveryTable
	 */
	public NodeDiscoveryTable getNodeDiscoveryTable()
	{
		return nodeDiscoveryTable;
	}

	private MoCriteriaTable moCriteriaTable = null;

	/**
	 * Returns the instance of the MoCriteriaTable
	 */
	public MoCriteriaTable getMoCriteriaTable()
	{
		return moCriteriaTable;
	}

	private DiscoveryFilterTable discoveryFilterTable = null;

	/**
	 * Returns the instance of the DiscoveryFilterTable
	 */
	public DiscoveryFilterTable getDiscoveryFilterTable()
	{
		return discoveryFilterTable;
	}

	private TrapFilterTable trapFilterTable = null;

	/**
	 * Returns the instance of the TrapFilterTable
	 */
	public TrapFilterTable getTrapFilterTable()
	{
		return trapFilterTable;
	}

	private PollingFiltersTable pollingFiltersTable = null;

	/**
	 * Returns the instance of the PollingFiltersTable
	 */
	public PollingFiltersTable getPollingFiltersTable()
	{
		return pollingFiltersTable;
	}

	private SnmpV3ConfigurationInstrument snmpV3ConfigurationInstrument = null;

	/**
	 * Returns the instance of the SnmpV3ConfigurationInstrument
	 */
	public SnmpV3ConfigurationInstrument getSnmpV3ConfigurationInstrument()
	{
		return snmpV3ConfigurationInstrument;
	}


	/**
	 * Constructor with commandline options.
	 * @param args String[] representing the Commandline  options selected
	 * while starting the Agent.
	 */
	public AdventNet_WebNMS_MIB_JMX(String[] args)
	{
	      // User code starts here
        this.args = args;

        /*
         * Getting the argument values
         */
        for(int i = 0;i < args.length;i += 2)
        {
            if((args[i].equalsIgnoreCase("RMI")) && (args[i+1].equalsIgnoreCase("true")))//No I18N
                rmiAdaptor = true;
            if((args[i].equalsIgnoreCase("CORBA")) && (args[i+1].equalsIgnoreCase("true")))//No I18N
                corbaAdaptor = true;
            if((args[i].equalsIgnoreCase("HTTP")) && (args[i+1].equalsIgnoreCase("true")))//No I18N
                httpAdaptor = true;
            if((args[i].equalsIgnoreCase("HTML")) && (args[i+1].equalsIgnoreCase("true")))//No I18N
                htmlAdaptor = true;
            if((args[i].equalsIgnoreCase("SNMP")) && (args[i+1].equalsIgnoreCase("false")))//No I18N
                snmpAdaptor = false;
            if((args[i].equalsIgnoreCase("TL1")) && (args[i+1].equalsIgnoreCase("true")))//No I18N
                tl1Adaptor = true;
            if((args[i].equalsIgnoreCase("TopoObserver")) && (args[i+1].equalsIgnoreCase("true")))//No I18N
                topoObsvr = true;
            if((args[i].equalsIgnoreCase("FaultObserver")) && (args[i+1].equalsIgnoreCase("true")))//No I18N
                faultObsvr = true;
            if((args[i].equalsIgnoreCase("Authentication")) && (args[i+1].equalsIgnoreCase("false")))//No I18N
                authentication = false;
            if((args[i].equalsIgnoreCase("Persist")) && (args[i+1].equalsIgnoreCase("true")))//No I18N
                doPersist = true;
	    if((args[i].equalsIgnoreCase("Constant_V3EngineID")) && (args[i+1].equalsIgnoreCase("true")))//No I18N
	    {
 	        const_EngineID = true;
	    }
        }
        if(true)
        {
            String[] a = new String[]{"-d","1"};//No I18N
            agentOptions = new JmxParamOptions(a);
        }
        else
            // User code ends here
		agentOptions = new JmxParamOptions(args);
		initialize();
	}

	/**
	 * Constructor with the Commandline options.
	 * @param agentOptions JMXParamOptions representing the CommandLine
	 * options selected while starting the Agent.
	 */
	public AdventNet_WebNMS_MIB_JMX(JmxParamOptions agentOptions)
	{
		this.agentOptions = agentOptions;
		initialize();
	}

	/**
	 * Main method for the Agent .This acts as the entry point for the
	 * JMXAgent.
	 */
	public static void main(String[] args)
	{
		AdventNet_WebNMS_MIB_JMX agent = new AdventNet_WebNMS_MIB_JMX(args);
	}

	/**
	 * This method will be used to shutdown the JMXAgent.
	 * All the Adaptors will be stopped and the resources cleaned .
	 */
	public void shutDownJmxAgent()
	{
		rmiadaptor.stopService();
		snmpadaptor.stopService();
		htmladaptor.stopService();
		corbaadaptor.stopService();
		httpconnector.stopService();
		tl1adaptor.stopService();
		MBeanServerFactory.releaseMBeanServer(server);
		if(topoObsvrImpl!=null && topoAPI!=null)
        {
            topoObsvrImpl.deRegister();
        }
        if(alertObsvrImpl!=null && alertAPI!=null)
        {
            alertObsvrImpl.deRegister();
        }
        if(eventObsvrImpl!=null && eventAPI!=null)
        {
            eventObsvrImpl.deRegister();
        }					
	}

	/**
	 * This method will be called by the Constructor and responsible for
	 * initializing the Agent and registering the Adaptors and services
	 */
	public void initialize()
	{
		// User code starts here
		 try
        {
            debugLevel = Integer.parseInt(getArgString("debugLevel"));//No I18N
        }
        catch(Exception e)
        {
        }
        LogFactory.setLoggingLevel(debugLevel);
		if(!snmpAdaptor && !rmiAdaptor && !corbaAdaptor && !tl1Adaptor && !httpAdaptor && !htmlAdaptor)
		{
			System.out.println(NmsUtil.GetString("Agent Started with no adaptors"));//No I18N
			WebNMSAgentApp.initialized = true;
			return;
		}

		//for log the print-outs
		agentUser = LogMgr.getLogUser("AGENTUSER");//No I18N
		agentErr = LogMgr.getLogUser("AGENTERR");//No I18N

		//by senthil
		createDB();
		initPrStatements();
		initStartIndex();

		enterpriseOID = getArgString("EnterpriseOID");//No I18N

		if(enterpriseOID.equals(""))//No I18N
		{
			newEnterpriseOID = false;
			enterpriseOID = adventnetEnterpriseOID;
		}

		new GenUtils();
		new TopoUtils();
		new FaultUtils();


		//for topo observer
		if(topoObsvr)
		{
			topoObsvrImpl = new TopoObserverImpl(this);
		}

		//by shankar
		//for alert observer
                if(faultObsvr)
                {
                    alertObsvrImpl = new AlertObserverImpl(this);
                }
		eventObsvrImpl = new EventObserverImpl(this);
		initializeNotificationValues();

		//by senthil
		//for notification log
		notiLogger = new NotificationLogger(this);
		//Setting the agent reference in CommonUtils, so that
		//this can be used in xxxInstrument and xxxEntries
		CommonUtils.setAgentReference(this);
		int maxThreads = getArgValue("-maxThreads");//No I18N
		maxThreads = (maxThreads != -1) ? maxThreads : 2;
		System.setProperty("jmx.modelmbean.threads", String.valueOf(maxThreads));//No I18N
		System.setProperty("jmx.notificationbroadcaster.threads","1");//No I18N
		/*

		//Initialising the Adaptor Settings
		initAdaptorSettings();
		*/
		 int temp = getArgValue("-rmiport");//No I18N
        rmiPort = (temp != -1) ? temp : NmsUtil.getRegistryPort();

        temp = getArgValue("-snmpport");//No I18N
        snmpPort = (temp != -1) ? temp :snmpPort;

        temp = getArgValue("-htmlport");//No I18N
        htmlPort = (temp != -1) ? temp : htmlPort;

        temp = getArgValue("-httpport");//No I18N
        httpPort = (temp != -1) ? temp :httpPort;

        temp = getArgValue("-corbaport");//No I18N
        corbaPort = (temp != -1) ? temp :corbaPort;

        temp = getArgValue("-tl1port");//No I18N
        tl1Port = (temp != -1) ? temp :tl1Port;
        // User code ends here

		//setting classloader
		Utilities.setClassLoader(this.getClass().getClassLoader());
		server = MBeanServerFactory.createMBeanServer();
		try
		{
			//Registering the AdventNet Adaptors
			registerAdventNetAdaptors();
			// User code starts here
			WebNMSAgentApp.initialized = true;

			while(!NmsUtil.webNMSModulesInitialized)
			{
				try
				{
					Thread.sleep(100);
				}
				catch (Exception e)
				{
					continue;
				}
			}
			// User code ends here
			//Registering the MBeans
			registerMBeans();
			//Registering the JMX Services
			registerJMXServices();
		}catch(Exception e)
		{
			System.out.println("Exception occured while Registration"); //No I18N
			e.printStackTrace();
		}
	}

	/**
	 * This method initialises the Adaptor Setting values.
	 */
	private void initAdaptorSettings()
	{
		if(agentOptions.getHttpPort() != -1)
			httpPort = agentOptions.getHttpPort();
		if(agentOptions.getSnmpPort() != -1)
			snmpPort = agentOptions.getSnmpPort();
		if(agentOptions.getHtmlPort() != -1)
			htmlPort = agentOptions.getHtmlPort();
		if(agentOptions.getRmiPort() != -1)
			rmiPort = agentOptions.getRmiPort();
		if(agentOptions.getCorbaPort() != -1)
			corbaPort = agentOptions.getCorbaPort();
		if(agentOptions.getTL1Port() != -1)
			tl1Port = agentOptions.getTL1Port();
		if(agentOptions.getDebugLevel() != -1)
		debugLevel = agentOptions.getDebugLevel();

		LogFactory.setLoggingLevel(debugLevel);
	}

	/**
	 * This method registers all the services used by the Agent.
	 */
	private void registerJMXServices() throws Exception
	{
		String name = null;
		notifImpl = new NotificationListenerImpl();
		RequiredModelMBean rmm = null;
		String xmlLocation =  null;
		 //Register all the services here.

		//Registering the MLet Service.
		MLet mlet = new MLet();
		name = "Services:type=MLet"; //No I18N
		server.registerMBean(mlet,new ObjectName(name));

		//Registering the Log Controller MBean.
		LogFactoryController logFactoryController= new LogFactoryController();
		name = "Services:type=logging"; //No I18N
		server.registerMBean(logFactoryController,new ObjectName(name));

		 // User code starts here
                              if (snmpAdaptor)
                              {
                                   try
                                   {
                              // User code ends here

		AclTable aclTable = new AclTable(snmpadaptor.getSnmpAgent(),"AccessControlTable.xml","xml"); //No I18N
		AclTableMBean aclMbean = new com.adventnet.adaptors.snmp.AclTableMBean(aclTable);
		// User code starts here
		/*
		xmlLocation ="conf" + File.separator + "apiTablesxml" + File.separator + "AclTable.xml"; //No I18N
		*/
		 xmlLocation = jmxConfDir + File.separator + "apiTablesxml" + File.separator + "AclTable.xml";//No I18N
		rmm = new RequiredModelMBean(com.adventnet.utils.jmx.Utilities.convertXmlToModelMBeanInfo(xmlLocation));
		rmm.setManagedResource(aclMbean, "objectReference"); //No I18N
		String nameAcl = "Adaptors:type=SNMPAdaptor,name=AclTable"; //No I18N
		server.registerMBean(rmm, new ObjectName(nameAcl));
		// User code starts here
                                   }
                                    catch(Exception e)
                {
                    e.printStackTrace();
                }

                String viewBasedAcc = getArgString("ViewBasedAccess");//No I18N
                if(viewBasedAcc.equals("true"))//No I18N
                {
                    viewBasedAccess = true;
                }
                if(viewBasedAccess)
                {
                    try
                    {
                       com.adventnet.snmp.snmp2.agent. VaclTable vaclTable = new com.adventnet.snmp.snmp2.agent.VaclTable(snmpadaptor.getSnmpAgent(),"ViewAccessControlTable.xml","xml"); //No I18N
                        com.adventnet.adaptors.snmp.VaclTableMBean vaclMbean = new com.adventnet.adaptors.snmp.VaclTableMBean(vaclTable);
                        xmlLocation = jmxConfDir + File.separator + "apiTablesxml"+File.separator+"VaclTable.xml"; //No I18N
                        rmm = new RequiredModelMBean(com.adventnet.utils.jmx.Utilities.convertXmlToModelMBeanInfo(xmlLocation));
                        rmm.setManagedResource(vaclMbean, "ObjectReference"); //No I18N //No I18N
                        String nameVacl = "Adaptors:type=SNMPAdaptor,name=VaclTable"; //No I18N
                        server.registerMBean(rmm, new ObjectName(nameVacl));
                    }
                    catch(Exception e)
                    {

                    }
                }
                try
                {
                    //namicRegistration = new DynamicRegistration(true,PureUtils.rootDir + "conf/jmx_agent","SubAgent.txt");//No I18N
                    dynamicRegistration = new DynamicRegistration(true,null,"SubAgent.txt");//No I18N
                    dynamicRegistration.setRegisteredOid(enterpriseOID + ".8.1.1");//No I18N
                    dynamicRegistration.addRegistrationListener(snmpadaptor);
                }
                catch(Exception e)
                {
                    agentErr.log(NmsUtil.GetString("Problem while reading and writing the file: conf/jmx_agent/SubAgent.txt"),1);//No I18N
                }
        }
		// User code ends here

	}

	/**
	 * This method registers all the Adaptors used by the Agent.
	 */
	private void registerAdventNetAdaptors() throws Exception
	{
		String name = null;
		String xmlLocation = null;
		RequiredModelMBean rmm = null;

		// User code starts here
		if (rmiAdaptor)
		{
			try
			{
		// User code ends here

		rmiadaptor = new RMIAdaptor();
		name = "Adaptors:type=RMIAdaptor"; //No I18N
		rmiadaptor.setPort(rmiPort);
		server.registerMBean(rmiadaptor, new ObjectName(name));

		// User code starts here
			}
			catch(Exception e)
			{
				agentErr.log("RMI Bind Exception... ",1);//No I18N
			}
		}
		if (snmpAdaptor)
		{
			agentDir = PureUtils.rootDir+ "conf" + File.separator + "jmx_agent";//No I18N
		// User code ends here

		AgentUtil.setAgentDir(agentDir);
		// User code starts here
			try
			{

				snmpadaptor = new SnmpAdaptor(snmpPort);
				name = "Adaptors:type=SNMPAdaptor";//No I18N
				snmpadaptor.setSnmpDebugLevel(new Integer(1));
				com.adventnet.snmp.snmp2.SnmpAPI api = snmpadaptor.getSnmpAgent().getSnmpAPI();

				String snmpVersion = getArgString("-snmpVersion");//No I18N
				if(!snmpVersion.trim().equals(""))//No I18N
				{
					snmpadaptor.setSnmpVersion(snmpVersion);
				}
				trapListener = new SnmpTrapForwader();

				if(snmpadaptor.getSnmpVersion().equals("v3"))//No I18N
				{

					V3ForwardingTable forwardingTable = new V3ForwardingTable(trapListener,"V3TrapForwardingTable.xml","xml"); //No I18N
					V3ForwardingTableMBean forMbean = new com.adventnet.adaptors.snmp.V3ForwardingTableMBean(forwardingTable);

					/*
					   xmlLocation ="conf" + File.separator + "apiTablesxml" + File.separator + "V3ForwardingTable.xml"; //No I18N
					   */
					String xmllLocation = jmxConfDir + File.separator +"apiTablesxml"+File.separator+ "V3ForwardingTable.xml";//No I18N


					rmm = new RequiredModelMBean(com.adventnet.utils.jmx.Utilities.convertXmlToModelMBeanInfo(xmllLocation));
					rmm.setManagedResource(forMbean, "objectReference"); //No I18N
					String nameFor = "Adaptors:type=SNMPAdaptor,name=V3ForwardingTable"; //No I18N
					server.registerMBean(rmm, new ObjectName(nameFor));

					//Registering the SnmpAdaptor
					/*
					   try{
					   snmpadaptor = new SnmpAdaptor(snmpPort);

					  	 name = "Adaptors:type=SNMPAdaptor"; //No I18N
					   	snmpadaptor.setSnmpDebugLevel(new Integer(debugLevel));
					  */

					snmpadaptor.setSnmpVersion("V3", false);//No I18N
					snmpadaptor.getSnmpAgent().setSnmpV3Compliance(true);

					/*
					   byte[] engineID = AgentUtil.genEngineID("127.0.0.1", snmpPort);//No I18N
					   snmpadaptor.getSnmpAgent().getSnmpAPI().setSnmpEngineID(engineID);
					   snmpadaptor.getSnmpAgent().initializeAdaptorV3Settings("XML File", false);//No I18N
					   */
					trapListener.setTrapForwardingTable(forwardingTable);
					if (const_EngineID)
					{              
						String evar = null;         
						try
						{
							evar = (System.getProperty("V3ENGINEID"));    
						}
						catch(Exception e)
						{
							e.printStackTrace();        
						}

						if (evar!= null)        
						{        
							byte[] engineID = evar.getBytes();
							api.setSnmpEngineID(engineID);
						}
						else
						{
							String hostip = (String)InetAddress.getLocalHost().getHostAddress();          
							byte[] engineID = hostip.getBytes();                          
							api.setSnmpEngineID(engineID);              
						}                                    
					}

					api.setV3DatabaseFlag(true);
					api.initJdbcParams(rlAPI.driverName,rlAPI.url,rlAPI.userName,rlAPI.password);
					initUSMEntries(api,snmpadaptor.getSnmpAgent());


				}
				else
				{
				     ForwardingTable v1v2TrapForwardingTable = new ForwardingTable(trapListener,"V1V2TrapForwardingTable.xml","xml");//No I18N
					ForwardingTableMBean v1v2ForMbean = new com.adventnet.adaptors.snmp.ForwardingTableMBean(v1v2TrapForwardingTable);
					xmlLocation = jmxConfDir + File.separator +"apiTablesxml"+File.separator+ "ForwardingTable.xml";//No I18N
					rmm = new RequiredModelMBean(com.adventnet.utils.jmx.Utilities.convertXmlToModelMBeanInfo(xmlLocation));
					rmm.setManagedResource(v1v2ForMbean, "ObjectReference"); //No I18N
					String v1v2ForName = "Adaptors:type=SNMPAdaptor,name=V1V2ForwardingTable";//No I18N
					trapListener.setTrapForwardingTable(v1v2TrapForwardingTable);
					server.registerMBean(rmm, new ObjectName(v1v2ForName));
				}



				snmpadaptor.setAsynchronousMode(new Boolean(true));
				snmpadaptor.setMaximumThreads(new Integer("2")); //No I18N
				snmpadaptor.addTrapRequestListener(trapListener);
				snmpadaptor.getSnmpAgent().setDefaultTrap(false);

				/*
				trapListener.setTrapForwardingTable(forwardingTable);
				*/
				snmpadaptor.getSnmpAgent().disableStartUpTrap(true);
				server.registerMBean(snmpadaptor, new ObjectName(name));
			}catch(RuntimeMBeanException e){
				RuntimeException re = e.getTargetException();
				if(re instanceof AgentRuntimeException){
					System.out.println("Bind Exception : Port " + snmpPort + " is in use");//No I18N
				}else{
					re.printStackTrace();
				}
			}
			/*if(snmpadaptor.getSnmpVersion().equals("v3"))
			{
				SnmpAPI api = snmpadaptor.getSnmpAgent().getSnmpAPI();
				((SnmpVacm)api.getACMProvider().getAccessControlModel(api.SNMP_VERSION_3)).setAcmUsed(false);
			}*/
			//added for handling traps...
			snmpadaptor.setTrapHandler(this);
			/*

		ForwardingTable forwardingTable = new ForwardingTable(trapListener,"V1V2TrapForwardingTable.xml","xml"); //No I18N
		ForwardingTableMBean forMbean = new com.adventnet.adaptors.snmp.ForwardingTableMBean(forwardingTable);
		xmlLocation ="conf" + File.separator + "apiTablesxml" + File.separator + "ForwardingTable.xml"; //No I18N
		rmm = new RequiredModelMBean(com.adventnet.utils.jmx.Utilities.convertXmlToModelMBeanInfo(xmlLocation));
		rmm.setManagedResource(forMbean, "objectReference"); //No I18N
		String nameFor = "Adaptors:type=SNMPAdaptor,name=V1V2ForwardingTable"; //No I18N
		server.registerMBean(rmm, new ObjectName(nameFor));

		//Registering the SnmpAdaptor
		try{
			snmpadaptor = new SnmpAdaptor(snmpPort);
			name = "Adaptors:type=SNMPAdaptor"; //No I18N
			snmpadaptor.setSnmpDebugLevel(new Integer(debugLevel));
			snmpadaptor.setSnmpVersion("V2c", false);//No I18N
			snmpadaptor.setAsynchronousMode(new Boolean(true));
			snmpadaptor.setMaximumThreads(new Integer("2"));//No I18N
			snmpadaptor.addTrapRequestListener(trapListener);
			snmpadaptor.getSnmpAgent().setDefaultTrap(false);
			trapListener.setTrapForwardingTable(forwardingTable);
			snmpadaptor.getSnmpAgent().disableStartUpTrap(true);
			server.registerMBean(snmpadaptor, new ObjectName(name));
		}catch(RuntimeMBeanException e){
			RuntimeException re = e.getTargetException();
			if(re instanceof AgentRuntimeException){
				System.out.println("Bind Exception : Port " + snmpPort + " is in use");//No I18N
			}else{
				re.printStackTrace();
			}
		}
		//added for handling traps...
			snmpadaptor.setTrapHandler(this);
		*/
			}
			if(htmlAdaptor)
		{
		  try
		  {
		       /*


		//Registering the Html Adaptor
		htmladaptor= new HtmlAdaptor(htmlPort);
		htmladaptor.setParentDir(agentDir);
		htmladaptor.addHttpServerInterface(new HtmlAdaptorServerImpl());
		*/
		htmladaptor = new HtmlAdaptor(PureUtils.rootDir);
		 htmladaptor.addHttpServerInterface(new com.adventnet.adaptors.html.ApacheServerImpl(htmlPort,authentication));
		 // User code ends here
		name = "Adaptors:type=HTMLAdaptor"; //No I18N
		server.registerMBean(htmladaptor, new ObjectName(name));

		// User code starts here
		/*
		htmladaptor.setLogFile(agentDir+"/JMXNorthboundproject/JmxNorthBound/agent/bin/HtmlNotif.ser");//No I18N
		*/
		System.out.println(NmsUtil.GetString("Html Adaptor started... at port ") + htmlPort);//No I18N
		}
		 catch(Exception e)
            {
                System.err.println(" ObjectName of the HtmlAdapter from catch -->"+name);//No I18N
                agentErr.fail(NmsUtil.GetString("Exception "),e);//No I18N
            }
            if(httpAdaptor)
            {
                 try
                 {
                      /*


		//Registering the Http Connector
		httpconnector= new HttpConnector(httpPort);
		httpconnector.setParentDir(agentDir);
		httpconnector.addHttpServerInterface(new HttpConnectorServerImpl());
		*/
		 httpconnector= new HttpConnector();
		  httpconnector.addHttpServerInterface(new com.adventnet.adaptors.http.HttpServerImpl(httpPort,server));
		// User code ends here
		name = "Adaptors:type=HTTPConnector"; //No I18N
		server.registerMBean(httpconnector, new ObjectName(name));

		// User code starts here
		 System.out.println(NmsUtil.GetString("Http Connector started... at port ") + httpPort);//No I18N
            }
             catch(Exception e)
            {
                agentErr.fail(NmsUtil.GetString("Exception "),e);//No I18N
            }
            }
		}
		 if(corbaAdaptor)
		 {
		  try
            {
                 // User code ends here


		//Registering the Corba Adaptor
		corbaadaptor= new CorbaAdaptor();
		name = "Adaptors:type=CORBAAdaptor"; //No I18N
		corbaadaptor.setPort(corbaPort);
		server.registerMBean(corbaadaptor, new ObjectName(name));
		// User code starts here
            }
            catch(Exception e)
            {
                agentErr.fail(NmsUtil.GetString("Exception "),e);//No I18N
            }
		 }
		  if(tl1Adaptor)
		  {
		       try
		       {
		            // User code ends here


		//Registering the TL1 Adaptor
		tl1adaptor= new TL1Adaptor();
		tl1adaptor.setDebugLevel(debugLevel);
		tl1adaptor.setStandAlone(false);
		name = "Adaptors:type=TL1Adaptor"; //No I18N
		tl1adaptor.setPort(tl1Port);
		server.registerMBean(tl1adaptor, new ObjectName(name));
		tl1adaptor.enableBuiltInCommands(true);
		tl1adaptor.enableSelfMonitorSupport(true);
		tl1adaptor.setSessionTimeOutInSeconds(900);
		tl1adaptor.enableCraftInterface(false);

		//Registering the TL1 Adaptor Info Table
		TL1AdaptorInfo tl1Info = new TL1AdaptorInfo();
		name = "Adaptors:type=TL1Adaptor,name=TL1AdaptorInfo"; //No I18N
		//xmlLocation = agentDir+"/conf/apiTablesxml/TL1AdaptorInfo.xml"; //No I18N
		xmlLocation=jmxConfDir+File.separator+"apiTablesxml"+File.separator+"TL1AdaptorInfo.xml";//No I18N
		rmm = new RequiredModelMBean(com.adventnet.utils.jmx.Utilities.convertXmlToModelMBeanInfo(xmlLocation));
		rmm.setManagedResource(tl1Info,"objectReference"); //No I18N
		server.registerMBean(rmm,new ObjectName(name));

		//Registering the TL1 Tcp Table
		Tl1TcpTable tl1TcpTable = new Tl1TcpTable(tl1adaptor);
		name = "Adaptors:type=TL1Adaptor,name=Tl1TcpTable"; //No I18N
		//xmlLocation = agentDir+"/conf/apiTablesxml/Tl1TcpTable.xml"; //No I18N
		xmlLocation=jmxConfDir+File.separator+"apiTablesxml"+File.separator+"Tl1TcpTable.xml";//No I18N
		rmm = new RequiredModelMBean(com.adventnet.utils.jmx.Utilities.convertXmlToModelMBeanInfo(xmlLocation));
		rmm.setManagedResource(tl1TcpTable,"objectReference"); //No I18N
		server.registerMBean(rmm,new ObjectName(name));

		//Registering the TL1 Communication Table
		Tl1CommIfcTable tl1CommIfcTable = new Tl1CommIfcTable(tl1adaptor);
		name = "Adaptors:type=TL1Adaptor,name=Tl1CommIfcTable"; //No I18N
		//User code starts here
		//xmlLocation = agentDir+"/conf/apiTablesxml/Tl1CommIfcTable.xml"; //No I18N
		xmlLocation=jmxConfDir+File.separator+"apiTablesxml"+File.separator+"Tl1CommIfcTable.xml";//No I18N
		//User code ends here

		rmm = new RequiredModelMBean(com.adventnet.utils.jmx.Utilities.convertXmlToModelMBeanInfo(xmlLocation));
		rmm.setManagedResource(tl1CommIfcTable,"objectReference"); //No I18N
		server.registerMBean(rmm,new ObjectName(name));

		// User code starts here
		       }
		       catch(Exception e)
            {
                agentErr.fail(NmsUtil.GetString("Exception "),e);//No I18N
            }
		  }
		   try
        {
            cascader = new Cascader();
            name = server.getDefaultDomain() + ":type=com.adventnet.services.cascading.Cascader,instance=1";//No I18N
            server.registerMBean(cascader, new ObjectName(name));
      }
        catch(Exception e)
        {
            agentErr.fail(NmsUtil.GetString("Exception "),e);//No I18N
        }
		initAPIs();
        initDefValues();

        topoConfImpl = new TopoConfImpl(this);
        pollFilterConfImpl = new PollFilterConfImpl(this);
        trapConfImpl = new TrapConfImpl(this);
		// User code ends here
	}

	/**
	 * This method registers all the MBeans .
	 */
	private void registerMBeans() throws Exception
	{
		String name = null;
		// User code starts here
		String xmlLocation="";//No I18N
		// User code ends here
		RequiredModelMBean rmm = null;
		webNMSSystemInstrument= new WebNMSSystemInstrument();
		// User code start here
		webNMSSystemInstrument.setAgentReference(this);
		xmlLocation=jmxConfDir+File.separator+"xml"+File.separator+"mbeans"+File.separator+"WebNMSSystemInstrument.xml";//No I18N
		// User code ends here
		rmm = new RequiredModelMBean(com.adventnet.utils.jmx.Utilities.convertXmlToModelMBeanInfo(xmlLocation));
		rmm.setManagedResource(webNMSSystemInstrument, "objectReference");//No I18N
		name = "AdventNet_WebNMS_MIB_JMX:type=WebNMSSystemInstrument";//No I18N
		server.registerMBean(rmm, new ObjectName(name));
		
		webNMSTopoMibInstrument= new WebNMSTopoMibInstrument();
		// User code starts here
		xmlLocation=jmxConfDir+File.separator+"xml"+File.separator+"mbeans"+File.separator+"WebNMSTopoMibInstrument.xml";//No I18N
		// User code ends here
		rmm = new RequiredModelMBean(com.adventnet.utils.jmx.Utilities.convertXmlToModelMBeanInfo(xmlLocation));
		rmm.setManagedResource(webNMSTopoMibInstrument, "objectReference");//No I18N
		name = "AdventNet_WebNMS_MIB_JMX:type=WebNMSTopoMibInstrument";//No I18N
		server.registerMBean(rmm, new ObjectName(name));
		// User code starts here
		webNMSTopoMibInstrument.setAgentReference(this);
		// User code ends here

		webNMSFaultMibInstrument= new WebNMSFaultMibInstrument();
		// User code starts here
		xmlLocation=jmxConfDir+File.separator+"xml"+File.separator+"mbeans"+File.separator+"WebNMSFaultMibInstrument.xml";//No I18N
		// User code ends here
		rmm = new RequiredModelMBean(com.adventnet.utils.jmx.Utilities.convertXmlToModelMBeanInfo(xmlLocation));
		rmm.setManagedResource(webNMSFaultMibInstrument, "objectReference");//No I18N
		name = "AdventNet_WebNMS_MIB_JMX:type=WebNMSFaultMibInstrument";//No I18N
		server.registerMBean(rmm, new ObjectName(name));
		// User code starts here
		webNMSFaultMibInstrument.setAgentReference(this);
		// User code ends here

		webNMSPerformanceMibInstrument= new WebNMSPerformanceMibInstrument();
		// User code starts here
		 xmlLocation = jmxConfDir+File.separator+"xml"+File.separator+"mbeans"+File.separator+"WebNMSPerformanceMibInstrument.xml";//No I18N
		 // User code ends here
		rmm = new RequiredModelMBean(com.adventnet.utils.jmx.Utilities.convertXmlToModelMBeanInfo(xmlLocation));
		rmm.setManagedResource(webNMSPerformanceMibInstrument, "objectReference");//No I18N
		name = "AdventNet_WebNMS_MIB_JMX:type=WebNMSPerformanceMibInstrument";//No I18N
		server.registerMBean(rmm, new ObjectName(name));
		// User code starts here
		webNMSPerformanceMibInstrument.setAgentReference(this);
		// User code ends here

		persistentTrapsMibInstrument= new PersistentTrapsMibInstrument();
		// User code starts here
		 xmlLocation = jmxConfDir+File.separator+"xml"+File.separator+"mbeans"+File.separator+"PersistentTrapsMibInstrument.xml";//No I18N
		 persistentTrapsMibInstrument.setAgentReference(this);
		// User code ends here
		rmm = new RequiredModelMBean(com.adventnet.utils.jmx.Utilities.convertXmlToModelMBeanInfo(xmlLocation));
		rmm.setManagedResource(persistentTrapsMibInstrument, "objectReference");//No I18N
		notifTable.put(persistentTrapsMibInstrument , rmm);
		name = "AdventNet_WebNMS_MIB_JMX:type=PersistentTrapsMibInstrument";//No I18N
		server.registerMBean(rmm, new ObjectName(name));

		tftpMibInstrument= new TftpMibInstrument();
		// User code starts here
		xmlLocation = jmxConfDir+File.separator+"xml"+File.separator+"mbeans"+File.separator+"TftpMibInstrument.xml";//No I18N
		tftpMibInstrument.setAgentReference(this);
		// User code ends here
		rmm = new RequiredModelMBean(com.adventnet.utils.jmx.Utilities.convertXmlToModelMBeanInfo(xmlLocation));
		rmm.setManagedResource(tftpMibInstrument, "objectReference");//No I18N
		name = "AdventNet_WebNMS_MIB_JMX:type=TftpMibInstrument";//No I18N
		server.registerMBean(rmm, new ObjectName(name));

		webNMSPortsInstrument= new WebNMSPortsInstrument();
		//  User code starts here
		xmlLocation = jmxConfDir+File.separator+"xml"+File.separator+"mbeans"+File.separator+"WebNMSPortsInstrument.xml";//No I18N
		webNMSPortsInstrument.setAgentReference(this);
		// User code ends here
		rmm = new RequiredModelMBean(com.adventnet.utils.jmx.Utilities.convertXmlToModelMBeanInfo(xmlLocation));
		rmm.setManagedResource(webNMSPortsInstrument, "objectReference");//No I18N
		name = "AdventNet_WebNMS_MIB_JMX:type=WebNMSPortsInstrument";//No I18N
		server.registerMBean(rmm, new ObjectName(name));

		webNMSSchedulerTable= new WebNMSSchedulerTable(this);
		// User code starts here
		xmlLocation = jmxConfDir+File.separator+"xml"+File.separator+"mbeans"+File.separator+"WebNMSSchedulerTable.xml";//No I18N
		webNMSSchedulerTable.setAgentReference(this);
		// User code ends here
		rmm = new RequiredModelMBean(com.adventnet.utils.jmx.Utilities.convertXmlToModelMBeanInfo(xmlLocation));
		rmm.setManagedResource(webNMSSchedulerTable, "objectReference");//No I18N
		name = "AdventNet_WebNMS_MIB_JMX:type=WebNMSSchedulerTable";//No I18N
		server.registerMBean(rmm, new ObjectName(name));
		
		webNMSMonitoringInstrument= new WebNMSMonitoringInstrument();
		// User code starts here
		xmlLocation = jmxConfDir+File.separator+"xml"+File.separator+"mbeans"+File.separator+"WebNMSMonitoringInstrument.xml";//No I18N
		webNMSMonitoringInstrument.setAgentReference(this);
		// User code ends here
		rmm = new RequiredModelMBean(com.adventnet.utils.jmx.Utilities.convertXmlToModelMBeanInfo(xmlLocation));
		rmm.setManagedResource(webNMSMonitoringInstrument, "objectReference");//No I18N
		name = "AdventNet_WebNMS_MIB_JMX:type=WebNMSMonitoringInstrument";//No I18N
		server.registerMBean(rmm, new ObjectName(name));
		
		moTable= new MoTable(this);
		// User code starts here
		xmlLocation = jmxConfDir+File.separator+"xml"+File.separator+"mbeans"+File.separator+"MoTable.xml";//No I18N
		// User code ends here
		rmm = new RequiredModelMBean(com.adventnet.utils.jmx.Utilities.convertXmlToModelMBeanInfo(xmlLocation));
		rmm.setManagedResource(moTable, "objectReference");//No I18N
		notifTable.put(moTable ,rmm);
		name = "AdventNet_WebNMS_MIB_JMX:type=MoTable";//No I18N
		server.registerMBean(rmm, new ObjectName(name));

		topoObjTable= new TopoObjTable(this);
		// User code starts here
		xmlLocation = jmxConfDir+File.separator+"xml"+File.separator+"mbeans"+File.separator+"TopoObjTable.xml";//No I18N
		// User code ends here
		rmm = new RequiredModelMBean(com.adventnet.utils.jmx.Utilities.convertXmlToModelMBeanInfo(xmlLocation));
		rmm.setManagedResource(topoObjTable, "objectReference");//No I18N
		name = "AdventNet_WebNMS_MIB_JMX:type=TopoObjTable";//No I18N
		server.registerMBean(rmm, new ObjectName(name));
		topoObjTable.setExternalIndexHandler(this);

		networkTable= new NetworkTable(this);
		// User code starts here
		xmlLocation = jmxConfDir+File.separator+"xml"+File.separator+"mbeans"+File.separator+"NetworkTable.xml";//No I18N
		// User code ends here
		rmm = new RequiredModelMBean(com.adventnet.utils.jmx.Utilities.convertXmlToModelMBeanInfo(xmlLocation));
		rmm.setManagedResource(networkTable, "objectReference");//No I18N
		name = "AdventNet_WebNMS_MIB_JMX:type=NetworkTable";//No I18N
		server.registerMBean(rmm, new ObjectName(name));
		networkTable.setExternalIndexHandler(this);

		nodeTable= new NodeTable(this);
		// User code starts here
		xmlLocation = jmxConfDir+File.separator+"xml"+File.separator+"mbeans"+File.separator+"NodeTable.xml";//No I18N
		// User code ends here
		rmm = new RequiredModelMBean(com.adventnet.utils.jmx.Utilities.convertXmlToModelMBeanInfo(xmlLocation));
		rmm.setManagedResource(nodeTable, "objectReference");//No I18N
		name = "AdventNet_WebNMS_MIB_JMX:type=NodeTable";//No I18N
		server.registerMBean(rmm, new ObjectName(name));
		nodeTable.setExternalIndexHandler(this);

		ipAddressTable= new IpAddressTable(this);
		// User code starts here
		xmlLocation = jmxConfDir+File.separator+"xml"+File.separator+"mbeans"+File.separator+"IpAddressTable.xml";//No I18N
		// User code ends here
		rmm = new RequiredModelMBean(com.adventnet.utils.jmx.Utilities.convertXmlToModelMBeanInfo(xmlLocation));
		rmm.setManagedResource(ipAddressTable, "objectReference");//No I18N
		name = "AdventNet_WebNMS_MIB_JMX:type=IpAddressTable";//No I18N
		server.registerMBean(rmm, new ObjectName(name));
		ipAddressTable.setExternalIndexHandler(this);

		snmpNodeTable= new SnmpNodeTable(this);
		// User code starts here
			xmlLocation = jmxConfDir+File.separator+"xml"+File.separator+"mbeans"+File.separator+"SnmpNodeTable.xml";//No I18N
		// User code ends here
		rmm = new RequiredModelMBean(com.adventnet.utils.jmx.Utilities.convertXmlToModelMBeanInfo(xmlLocation));
		rmm.setManagedResource(snmpNodeTable, "objectReference");//No I18N
		name = "AdventNet_WebNMS_MIB_JMX:type=SnmpNodeTable";//No I18N
		server.registerMBean(rmm, new ObjectName(name));
		snmpNodeTable.setExternalIndexHandler(this);

		snmpInterfaceTable= new SnmpInterfaceTable(this);
		// User code starts here
		xmlLocation = jmxConfDir+File.separator+"xml"+File.separator+"mbeans"+File.separator+"SnmpInterfaceTable.xml";//No I18N
		// User code ends here
		rmm = new RequiredModelMBean(com.adventnet.utils.jmx.Utilities.convertXmlToModelMBeanInfo(xmlLocation));
		rmm.setManagedResource(snmpInterfaceTable, "objectReference");//No I18N
		name = "AdventNet_WebNMS_MIB_JMX:type=SnmpInterfaceTable";//No I18N
		server.registerMBean(rmm, new ObjectName(name));
		snmpInterfaceTable.setExternalIndexHandler(this);

		moDerivedPropNameTable= new MoDerivedPropNameTable(this);
		// User code starts here
		xmlLocation = jmxConfDir+File.separator+"xml"+File.separator+"mbeans"+File.separator+"MoDerivedPropNameTable.xml";//No I18N
		moDerivedPropNameTable.setAgentReference(this);
		// User code ends here
		rmm = new RequiredModelMBean(com.adventnet.utils.jmx.Utilities.convertXmlToModelMBeanInfo(xmlLocation));
		rmm.setManagedResource(moDerivedPropNameTable, "objectReference");//No I18N
		name = "AdventNet_WebNMS_MIB_JMX:type=MoDerivedPropNameTable";//No I18N
		server.registerMBean(rmm, new ObjectName(name));

		moNotificationMibInstrument= new MoNotificationMibInstrument();
		// User code starts here
		xmlLocation = jmxConfDir+File.separator+"xml"+File.separator+"mbeans"+File.separator+"MoNotificationMibInstrument.xml";//No I18N
		// User code ends here
		rmm = new RequiredModelMBean(com.adventnet.utils.jmx.Utilities.convertXmlToModelMBeanInfo(xmlLocation));
		rmm.setManagedResource(moNotificationMibInstrument, "objectReference");//No I18N
		name = "AdventNet_WebNMS_MIB_JMX:type=MoNotificationMibInstrument";//No I18N
		server.registerMBean(rmm, new ObjectName(name));
		// User code starts here
		moNotificationMibInstrument.setAgentReference(this);
		// User code ends here

		alertTable= new AlertTable(this);
		// User code starts here
		xmlLocation = jmxConfDir+File.separator+"xml"+File.separator+"mbeans"+File.separator+"AlertTable.xml";//No I18N
		// User code ends here
		rmm = new RequiredModelMBean(com.adventnet.utils.jmx.Utilities.convertXmlToModelMBeanInfo(xmlLocation));
		rmm.setManagedResource(alertTable, "objectReference");//No I18N
		name = "AdventNet_WebNMS_MIB_JMX:type=AlertTable";//No I18N
		server.registerMBean(rmm, new ObjectName(name));

		alertNotificationMibInstrument= new AlertNotificationMibInstrument();
		// User code starts here
		xmlLocation = jmxConfDir+File.separator+"xml"+File.separator+"mbeans"+File.separator+"AlertNotificationMibInstrument.xml";//No I18N
		// User code ends here
		rmm = new RequiredModelMBean(com.adventnet.utils.jmx.Utilities.convertXmlToModelMBeanInfo(xmlLocation));
		rmm.setManagedResource(alertNotificationMibInstrument, "objectReference");//No I18N
		name = "AdventNet_WebNMS_MIB_JMX:type=AlertNotificationMibInstrument";//No I18N
		server.registerMBean(rmm, new ObjectName(name));
		// User code starts here
		alertNotificationMibInstrument.setAgentReference(this);
		// User code ends here

		eventTable= new EventTable(this);
		// User code starts here
		xmlLocation = jmxConfDir+File.separator+"xml"+File.separator+"mbeans"+File.separator+"EventTable.xml";//No I18N
		// User code ends here
		rmm = new RequiredModelMBean(com.adventnet.utils.jmx.Utilities.convertXmlToModelMBeanInfo(xmlLocation));
		rmm.setManagedResource(eventTable, "objectReference");//No I18N
		name = "AdventNet_WebNMS_MIB_JMX:type=EventTable";//No I18N
		server.registerMBean(rmm, new ObjectName(name));

		webNMSSeverityTable= new WebNMSSeverityTable(this);
		// User code starts here
		xmlLocation = jmxConfDir+File.separator+"xml"+File.separator+"mbeans"+File.separator+"WebNMSSeverityTable.xml";//No I18N
		// User code ends here
		rmm = new RequiredModelMBean(com.adventnet.utils.jmx.Utilities.convertXmlToModelMBeanInfo(xmlLocation));
		rmm.setManagedResource(webNMSSeverityTable, "objectReference");//No I18N
		name = "AdventNet_WebNMS_MIB_JMX:type=WebNMSSeverityTable";//No I18N
		server.registerMBean(rmm, new ObjectName(name));

		alarmTable= new AlarmTable(this);
		// User code starts here
		xmlLocation = jmxConfDir+File.separator+"xml"+File.separator+"mbeans"+File.separator+"AlarmTable.xml";//No I18N
		// User code ends here
		rmm = new RequiredModelMBean(com.adventnet.utils.jmx.Utilities.convertXmlToModelMBeanInfo(xmlLocation));
		rmm.setManagedResource(alarmTable, "objectReference");//No I18N
		name = "AdventNet_WebNMS_MIB_JMX:type=AlarmTable";//No I18N
		server.registerMBean(rmm, new ObjectName(name));

		pollTable= new PollTable(this);
		// User code starts here
		xmlLocation = jmxConfDir+File.separator+"xml"+File.separator+"mbeans"+File.separator+"PollTable.xml";//No I18N
		// User code ends here
		rmm = new RequiredModelMBean(com.adventnet.utils.jmx.Utilities.convertXmlToModelMBeanInfo(xmlLocation));
		rmm.setManagedResource(pollTable, "objectReference");//No I18N
		name = "AdventNet_WebNMS_MIB_JMX:type=PollTable";//No I18N
		server.registerMBean(rmm, new ObjectName(name));

		thresholdTable= new ThresholdTable(this);
		// User code starts here
		xmlLocation = jmxConfDir+File.separator+"xml"+File.separator+"mbeans"+File.separator+"ThresholdTable.xml";//No I18N
		// User code ends here
		rmm = new RequiredModelMBean(com.adventnet.utils.jmx.Utilities.convertXmlToModelMBeanInfo(xmlLocation));
		rmm.setManagedResource(thresholdTable, "objectReference");//No I18N
		name = "AdventNet_WebNMS_MIB_JMX:type=ThresholdTable";//No I18N
		server.registerMBean(rmm, new ObjectName(name));

		statsdataInstrument= new StatsdataInstrument();
		// User code starts here
		xmlLocation =jmxConfDir+File.separator+"xml"+File.separator+"mbeans"+File.separator+"StatsdataInstrument.xml";//No I18N
		statsdataInstrument.setAgentReference(this);
		// User code ends here
		rmm = new RequiredModelMBean(com.adventnet.utils.jmx.Utilities.convertXmlToModelMBeanInfo(xmlLocation));
		rmm.setManagedResource(statsdataInstrument, "objectReference");//No I18N
		name = "AdventNet_WebNMS_MIB_JMX:type=StatsdataInstrument";//No I18N
		server.registerMBean(rmm, new ObjectName(name));

		notiLogTable= new NotiLogTable(this);
		// User code starts here
		xmlLocation = jmxConfDir+File.separator+"xml"+File.separator+"mbeans"+File.separator+"NotiLogTable.xml";//No I18N

		// User code ends here
		rmm = new RequiredModelMBean(com.adventnet.utils.jmx.Utilities.convertXmlToModelMBeanInfo(xmlLocation));
		rmm.setManagedResource(notiLogTable, "objectReference");//No I18N
		name = "AdventNet_WebNMS_MIB_JMX:type=NotiLogTable";//No I18N
		server.registerMBean(rmm, new ObjectName(name));

		varbindLogTable= new VarbindLogTable(this);
		// User code starts here
		xmlLocation =jmxConfDir+File.separator+"xml"+File.separator+"mbeans"+File.separator+"VarbindLogTable.xml";//No I18N
		// User code ends here
		rmm = new RequiredModelMBean(com.adventnet.utils.jmx.Utilities.convertXmlToModelMBeanInfo(xmlLocation));
		rmm.setManagedResource(varbindLogTable, "objectReference");//No I18N
		name = "AdventNet_WebNMS_MIB_JMX:type=VarbindLogTable";//No I18N
		server.registerMBean(rmm, new ObjectName(name));
		varbindLogTable.setExternalIndexHandler(this);

		proxyTable= new ProxyTable(this);
		// User code starts here
		xmlLocation = jmxConfDir+File.separator+"xml"+File.separator+"mbeans"+File.separator+"ProxyTable.xml";//No I18N
		// User code ends here
		rmm = new RequiredModelMBean(com.adventnet.utils.jmx.Utilities.convertXmlToModelMBeanInfo(xmlLocation));
		rmm.setManagedResource(proxyTable, "objectReference");//No I18N
		name = "AdventNet_WebNMS_MIB_JMX:type=ProxyTable";//No I18N
		server.registerMBean(rmm, new ObjectName(name));

		subAgentTable= new SubAgentTable(this);
		// User code starts here
		xmlLocation = jmxConfDir+File.separator+"xml"+File.separator+"mbeans"+File.separator+"SubAgentTable.xml";//No I18N
		// User code ends here
		rmm = new RequiredModelMBean(com.adventnet.utils.jmx.Utilities.convertXmlToModelMBeanInfo(xmlLocation));
		rmm.setManagedResource(subAgentTable, "objectReference");//No I18N
		name = "AdventNet_WebNMS_MIB_JMX:type=SubAgentTable";//No I18N
		server.registerMBean(rmm, new ObjectName(name));


		topologyConfigurationInstrument= new TopologyConfigurationInstrument();
		// User code starts here
		xmlLocation = jmxConfDir+File.separator+"xml"+File.separator+"mbeans"+File.separator+"TopologyConfigurationInstrument.xml";//No I18N
		// User code ends here
		rmm = new RequiredModelMBean(com.adventnet.utils.jmx.Utilities.convertXmlToModelMBeanInfo(xmlLocation));
		rmm.setManagedResource(topologyConfigurationInstrument, "objectReference");//No I18N
		name = "AdventNet_WebNMS_MIB_JMX:type=TopologyConfigurationInstrument";//No I18N
		server.registerMBean(rmm, new ObjectName(name));
		// User code starts here
		topologyConfigurationInstrument.setAgentReference(this);
		// User code ends here

		trapPortTable= new TrapPortTable(this);
		// User code starts here
		xmlLocation = jmxConfDir+File.separator+"xml"+File.separator+"mbeans"+File.separator+"TrapPortTable.xml";//No I18N
		//xmlLocation = PureUtils.rootDir+"conf"+File.separator+"jmx_agent"+File.separator+"TrapPortTable.xml";//No I18N
		// User code ends here
		rmm = new RequiredModelMBean(com.adventnet.utils.jmx.Utilities.convertXmlToModelMBeanInfo(xmlLocation));
		rmm.setManagedResource(trapPortTable, "objectReference");//No I18N
		name = "AdventNet_WebNMS_MIB_JMX:type=TrapPortTable";//No I18N
		server.registerMBean(rmm, new ObjectName(name));

		moNotiVarbindsInstrument= new MoNotiVarbindsInstrument();
		// User code starts here
		xmlLocation = jmxConfDir+File.separator+"xml"+File.separator+"mbeans"+File.separator+"MoNotiVarbindsInstrument.xml";//No I18N
		// User code ends here
		rmm = new RequiredModelMBean(com.adventnet.utils.jmx.Utilities.convertXmlToModelMBeanInfo(xmlLocation));
		rmm.setManagedResource(moNotiVarbindsInstrument, "objectReference");//No I18N
		notifTable.put(moNotiVarbindsInstrument , rmm);
		name = "AdventNet_WebNMS_MIB_JMX:type=MoNotiVarbindsInstrument";//No I18N
		server.registerMBean(rmm, new ObjectName(name));

		alertNotiVarbindsInstrument= new AlertNotiVarbindsInstrument();
		// User code starts here
		xmlLocation = jmxConfDir+File.separator+"xml"+File.separator+"mbeans"+File.separator+"AlertNotiVarbindsInstrument.xml";//No I18N
		// User code ends here
		rmm = new RequiredModelMBean(com.adventnet.utils.jmx.Utilities.convertXmlToModelMBeanInfo(xmlLocation));
		rmm.setManagedResource(alertNotiVarbindsInstrument, "objectReference");//No I18N
		notifTable.put(alertNotiVarbindsInstrument , rmm);
		name = "AdventNet_WebNMS_MIB_JMX:type=AlertNotiVarbindsInstrument";//No I18N
		server.registerMBean(rmm, new ObjectName(name));

		statsDataTable= new StatsDataTable(this);
		// User code starts here
		xmlLocation =jmxConfDir+File.separator+"xml"+File.separator+"mbeans"+File.separator+"StatsDataTable.xml";//No I18N
		// User code ends here
		rmm = new RequiredModelMBean(com.adventnet.utils.jmx.Utilities.convertXmlToModelMBeanInfo(xmlLocation));
		rmm.setManagedResource(statsDataTable, "objectReference");//No I18N
		name = "AdventNet_WebNMS_MIB_JMX:type=StatsDataTable";//No I18N
		server.registerMBean(rmm, new ObjectName(name));

		perfNotiVarbindsInstrument= new PerfNotiVarbindsInstrument();
		// User code starts here
		xmlLocation = jmxConfDir+File.separator+"xml"+File.separator+"mbeans"+File.separator+"PerfNotiVarbindsInstrument.xml";//No I18N
		// User code ends here
		rmm = new RequiredModelMBean(com.adventnet.utils.jmx.Utilities.convertXmlToModelMBeanInfo(xmlLocation));
		rmm.setManagedResource(perfNotiVarbindsInstrument, "objectReference");//No I18N
		name = "AdventNet_WebNMS_MIB_JMX:type=PerfNotiVarbindsInstrument";//No I18N
		server.registerMBean(rmm, new ObjectName(name));

		rediscoveryConfigurationInstrument= new RediscoveryConfigurationInstrument();
		rediscoveryConfigurationInstrument.setAgentReference(this);
		// User code starts here
		xmlLocation = jmxConfDir+File.separator+"xml"+File.separator+"mbeans"+File.separator+"RediscoveryConfigurationInstrument.xml";//No I18N
		// User code ends here
		rmm = new RequiredModelMBean(com.adventnet.utils.jmx.Utilities.convertXmlToModelMBeanInfo(xmlLocation));
		rmm.setManagedResource(rediscoveryConfigurationInstrument, "objectReference");//No I18N
		name = "AdventNet_WebNMS_MIB_JMX:type=RediscoveryConfigurationInstrument";//No I18N
		server.registerMBean(rmm, new ObjectName(name));

		snmpPingConfigurationInstrument= new SnmpPingConfigurationInstrument();
		snmpPingConfigurationInstrument.setAgentReference(this);
		// User code starts here
		xmlLocation = jmxConfDir+File.separator+"xml"+File.separator+"mbeans"+File.separator+"SnmpPingConfigurationInstrument.xml";//No I18N
		rmm = new RequiredModelMBean(com.adventnet.utils.jmx.Utilities.convertXmlToModelMBeanInfo(xmlLocation));
		rmm.setManagedResource(snmpPingConfigurationInstrument, "objectReference");//No I18N
		name = "AdventNet_WebNMS_MIB_JMX:type=SnmpPingConfigurationInstrument";//No I18N
		server.registerMBean(rmm, new ObjectName(name));

		icmpPingConfigurationInstrument= new IcmpPingConfigurationInstrument();
		icmpPingConfigurationInstrument.setAgentReference(this);
		// User code starts here
		xmlLocation = jmxConfDir+File.separator+"xml"+File.separator+"mbeans"+File.separator+"IcmpPingConfigurationInstrument.xml";//No I18N
		// User code ends here
		rmm = new RequiredModelMBean(com.adventnet.utils.jmx.Utilities.convertXmlToModelMBeanInfo(xmlLocation));
		rmm.setManagedResource(icmpPingConfigurationInstrument, "objectReference");//No I18N
		name = "AdventNet_WebNMS_MIB_JMX:type=IcmpPingConfigurationInstrument";//No I18N
		server.registerMBean(rmm, new ObjectName(name));

		nativePingConfigurationInstrument= new NativePingConfigurationInstrument();
		nativePingConfigurationInstrument.setAgentReference(this);
		// User code starts here
		xmlLocation = jmxConfDir+File.separator+"xml"+File.separator+"mbeans"+File.separator+"NativePingConfigurationInstrument.xml";//No I18N
		// User code ends here
		rmm = new RequiredModelMBean(com.adventnet.utils.jmx.Utilities.convertXmlToModelMBeanInfo(xmlLocation));
		rmm.setManagedResource(nativePingConfigurationInstrument, "objectReference");//No I18N
		name = "AdventNet_WebNMS_MIB_JMX:type=NativePingConfigurationInstrument";//No I18N
		server.registerMBean(rmm, new ObjectName(name));

		networkDiscoveryTable= new NetworkDiscoveryTable();
		networkDiscoveryTable.setAgentReference(this);
		// User code starts here
		xmlLocation = jmxConfDir+File.separator+"xml"+File.separator+"mbeans"+File.separator+"NetworkDiscoveryTable.xml";//No I18N
		// User code ends here
		rmm = new RequiredModelMBean(com.adventnet.utils.jmx.Utilities.convertXmlToModelMBeanInfo(xmlLocation));
		rmm.setManagedResource(networkDiscoveryTable, "objectReference");//No I18N
		name = "AdventNet_WebNMS_MIB_JMX:type=NetworkDiscoveryTable";//No I18N
		server.registerMBean(rmm, new ObjectName(name));

		nodeDiscoveryTable= new NodeDiscoveryTable();
		nodeDiscoveryTable.setAgentReference(this);
		// User code starts here
		xmlLocation = jmxConfDir+File.separator+"xml"+File.separator+"mbeans"+File.separator+"NodeDiscoveryTable.xml";//No I18N
		// User code ends here
		rmm = new RequiredModelMBean(com.adventnet.utils.jmx.Utilities.convertXmlToModelMBeanInfo(xmlLocation));
		rmm.setManagedResource(nodeDiscoveryTable, "objectReference");//No I18N
		name = "AdventNet_WebNMS_MIB_JMX:type=NodeDiscoveryTable";//No I18N
		server.registerMBean(rmm, new ObjectName(name));

		moCriteriaTable= new MoCriteriaTable(this);
		// User code starts here
		xmlLocation = jmxConfDir+File.separator+"xml"+File.separator+"mbeans"+File.separator+"MoCriteriaTable.xml";//No I18N
		// User code ends here
		rmm = new RequiredModelMBean(com.adventnet.utils.jmx.Utilities.convertXmlToModelMBeanInfo(xmlLocation));
		rmm.setManagedResource(moCriteriaTable, "objectReference");//No I18N
		name = "AdventNet_WebNMS_MIB_JMX:type=MoCriteriaTable";//No I18N
		server.registerMBean(rmm, new ObjectName(name));

		discoveryFilterTable= new DiscoveryFilterTable();
		discoveryFilterTable.setAgentReference(this);
		// User code starts here
		xmlLocation = jmxConfDir+File.separator+"xml"+File.separator+"mbeans"+File.separator+"DiscoveryFilterTable.xml";//No I18N
		// User code ends here
		rmm = new RequiredModelMBean(com.adventnet.utils.jmx.Utilities.convertXmlToModelMBeanInfo(xmlLocation));
		rmm.setManagedResource(discoveryFilterTable, "objectReference");//No I18N
		name = "AdventNet_WebNMS_MIB_JMX:type=DiscoveryFilterTable";//No I18N
		server.registerMBean(rmm, new ObjectName(name));

		trapFilterTable= new TrapFilterTable(this);
		// User code starts here
		xmlLocation = jmxConfDir+File.separator+"xml"+File.separator+"mbeans"+File.separator+"TrapFilterTable.xml";//No I18N
		// User code ends here
		rmm = new RequiredModelMBean(com.adventnet.utils.jmx.Utilities.convertXmlToModelMBeanInfo(xmlLocation));
		rmm.setManagedResource(trapFilterTable, "objectReference");//No I18N
		name = "AdventNet_WebNMS_MIB_JMX:type=TrapFilterTable";//No I18N
		server.registerMBean(rmm, new ObjectName(name));

		pollingFiltersTable= new PollingFiltersTable();
		pollingFiltersTable.setAgentReference(this);
		// User code starts here
		xmlLocation = jmxConfDir+File.separator+"xml"+File.separator+"mbeans"+File.separator+"PollingFiltersTable.xml";//No I18N
		// User code ends here
		rmm = new RequiredModelMBean(com.adventnet.utils.jmx.Utilities.convertXmlToModelMBeanInfo(xmlLocation));
		rmm.setManagedResource(pollingFiltersTable, "objectReference");//No I18N
		name = "AdventNet_WebNMS_MIB_JMX:type=PollingFiltersTable";//No I18N
		server.registerMBean(rmm, new ObjectName(name));

		snmpV3ConfigurationInstrument= new SnmpV3ConfigurationInstrument();
		snmpV3ConfigurationInstrument.setAgentReference(this);
		// User code starts here
		xmlLocation = jmxConfDir+File.separator+"xml"+File.separator+"mbeans"+File.separator+"SnmpV3ConfigurationInstrument.xml";//No I18N
		// User code ends here
		rmm = new RequiredModelMBean(com.adventnet.utils.jmx.Utilities.convertXmlToModelMBeanInfo(xmlLocation));
		rmm.setManagedResource(snmpV3ConfigurationInstrument, "objectReference");//No I18N
		name = "AdventNet_WebNMS_MIB_JMX:type=SnmpV3ConfigurationInstrument";//No I18N
		server.registerMBean(rmm, new ObjectName(name));
	}

	/**
	 * Getter method for the NotificationBroadcaster object
	 * used by the MBeans.
	 */
	public static ModelMBeanNotificationBroadcaster getNotifHandler(Object source)
	{
	     	// User code starts here
	      /*
		return (ModelMBeanNotificationBroadcaster)notifTable.get(source);
		*/
		ModelMBeanNotificationBroadcaster notifHandler = null;
        try
        {
            if(notifTable != null)
                notifHandler = (ModelMBeanNotificationBroadcaster)(notifTable.get(source));
        }
        catch(Exception e)
        {
            return null;
        }
        return notifHandler;
		// User code ends here
	}

	/**
	 * This Method is called if this index is
	 * used as external index by any other table
	 */
	public boolean checkExternalMoNameIndex(Object value)
	{
		return checkExternal(moTable.getMoTable(), "MoNameIndex", value);//No I18N
	}
	/**
	 * This Method is called if this index is
	 * used as external index by any other table
	 */
	public boolean checkExternalNotiLogIndex(Object value)
	{
		return checkExternal(notiLogTable.getNotiLogTable(), "NotiLogIndex", value);//No I18N
	}

	private boolean checkExternal(TabularData tab, String name, Object value)
	{
		if(value == null)
			return false;

		for(Enumeration e = tab.enumerate(); e.hasMoreElements();)
		{
			CompositeData comp = tab.getRow((Object[])e.nextElement());
			if(value.equals(comp.getDataItem(name)))
				return true;
		}
		return false;
	}

	/**
	 * This method returns the reference of the MBeanServer to the interested MBeans
	 */
	public MBeanServer getServer()
	{
		return server;
	}

// User code starts here
    /*
     * To send the TL1 Notifications
     */

    void sendTL1AutoMsg(String almType , Vector varbindVec, Object source)
    {
        String alarmType = almType;

        int noVar = varbindVec.size();
        String[] values = new String[noVar];
        String[] keys = new String[noVar];

        Hashtable[] respLines = new Hashtable[noVar];
        String[] respLineTypes = new String[noVar];
        boolean[] nameEqualsValue = new boolean[noVar];

        for(int i = 0;i < varbindVec.size();i++)
        {
            values[i] = ((SnmpVarBind)varbindVec.elementAt(i)).toString();
            keys[i] = i + "";//No I18N
        }

        for(int i=0; i < noVar; i++)
        {
            respLines[i] = new Hashtable();
            respLines[i].put(keys[i],values[i]);
            respLineTypes[i] = TL1AgentResponseInfo.QUOTED;
            nameEqualsValue[i] = false;
        }

        Vector sessList = tl1adaptor.getAllSession();

        TL1AgentResponseInfo autoInfo = new TL1AgentResponseInfo();
        autoInfo.setResponseKeys(keys);
        autoInfo.setResponseLines(respLines);
        autoInfo.setResponseLineTypes(respLineTypes);
        autoInfo.setNameEqualsValue(nameEqualsValue);
        autoInfo.setSessionList(sessList);

        javax.management.Notification notif = new javax.management.Notification(alarmType, this, 1);

        notif.setUserData(autoInfo);

        try
        {
            ModelMBeanNotificationBroadcaster notifHandler = getNotifHandler(source);
            if(notifHandler != null)
                notifHandler.sendNotification(notif);
        }
        catch(Exception e)
        {
            agentErr.fail(NmsUtil.GetString("Exception "),e);//No I18N
        }
        }
    // User code ends here

	/**
	 * This method will be invoked from the SnmpAdaptor
	 * for sending alertClearNotification traps
	 */
	public void alertClearNotification (Vector varbindVector)
	{
	     // User code starts here
        //add noti into log
        if(doPersist)
            notiLogger.addNotificationToLog(varbindVector,FaultUtils.resolveFaultOID(FaultUtils.CLEAR_ALARM_NOTIFICATION));

        if(snmpAdaptor)
        {
            // User code ends here
		TrapRequestEvent te =  new TrapRequestEvent(this,varbindVector,TrapRequestEvent.TFTABLE,".1.3.6.1.4.1.2162.4.3.8.2",1);//No I18N
				// User code starts here
            te.setSourceForTrapInfo(TrapRequestEvent.TFTABLE);
            // User code ends here

		te.setTimeTicks(snmpadaptor.getUpTime().longValue());

		if(trapListener != null){
			trapListener.sendTrap(te);
			// User code starts here
			seqNumIncrement();
			// User code ends here
		}
		// User code starts here
        }

        //TL1AutoMsg
        if(tl1Adaptor)
            sendTL1AutoMsg("adventnet.tl1.alertClearNotification",varbindVector,getAlertNotiVarbindsInstrument());//No I18N
        // User code ends here
	}

	/**
	 * This method will be invoked from the SnmpAdaptor
	 * for sending alertCriticalNotification traps
	 */
	public void alertCriticalNotification (Vector varbindVector)
	{
	     // User code starts here
        //add noti into log
        if(doPersist)
            notiLogger.addNotificationToLog(varbindVector,FaultUtils.resolveFaultOID(FaultUtils.CRITICAL_ALARM_NOTIFICATION));

        if(snmpAdaptor)
        {
            // User code ends here
		TrapRequestEvent te =  new TrapRequestEvent(this,varbindVector,TrapRequestEvent.TFTABLE,".1.3.6.1.4.1.2162.4.3.8.2",5);//No I18N
				 // User code starts here
            te.setSourceForTrapInfo(TrapRequestEvent.TFTABLE);
            // User code ends here

		te.setTimeTicks(snmpadaptor.getUpTime().longValue());

		if(trapListener != null){
			trapListener.sendTrap(te);
			// User code starts here
			seqNumIncrement();
			// User code ends here
		}
		// User code starts here
        }

        //TL1AutoMsg
        if(tl1Adaptor)
            sendTL1AutoMsg("adventnet.tl1.alertCriticalNotification",varbindVector,getAlertNotiVarbindsInstrument());//No I18N
        // User code ends here
	}

	/**
	 * This method will be invoked from the SnmpAdaptor
	 * for sending moDeenrolNotification traps
	 */
	public void moDeenrolNotification (Vector varbindVector)
	{
	      // User code starts here
        //by senthil
        //add noti into log
        if(doPersist)
            notiLogger.addNotificationToLog(varbindVector,TopoUtils.moDeenrolNotification);

        if(snmpAdaptor)
        {
            // User code ends here
		TrapRequestEvent te =  new TrapRequestEvent(this,varbindVector,TrapRequestEvent.TFTABLE,".1.3.6.1.4.1.2162.4.2.14.2",2);//No I18N
				 // User code starts here
            te.setSourceForTrapInfo(TrapRequestEvent.TFTABLE);
            // User code ends here

		te.setTimeTicks(snmpadaptor.getUpTime().longValue());

		if(trapListener != null){
			trapListener.sendTrap(te);
			// User code starts here
			seqNumIncrement();
			// User code ends here
		}
		// User code starts here
        }

        //TL1AutoMs
        if(tl1Adaptor)
            sendTL1AutoMsg("adventnet.tl1.moDeenrolNotification",varbindVector,getMoNotiVarbindsInstrument());//No I18N
        // User code ends here
	}

	/**
	 * This method will be invoked from the SnmpAdaptor
	 * for sending moAttrChangeNotification traps
	 */
	public void moAttrChangeNotification (Vector varbindVector)
	{
	     // User code starts here
        //add noti into log
        if(doPersist)
            notiLogger.addNotificationToLog(varbindVector,TopoUtils.moAttrChangeNotification);

        if(snmpAdaptor)
        {
            // User code ends here
		TrapRequestEvent te =  new TrapRequestEvent(this,varbindVector,TrapRequestEvent.TFTABLE,".1.3.6.1.4.1.2162.4.2.14.2",3);//No I18N
				  // User code starts here
            te.setSourceForTrapInfo(TrapRequestEvent.TFTABLE);
            // User code ends here

		te.setTimeTicks(snmpadaptor.getUpTime().longValue());

		if(trapListener != null){
			trapListener.sendTrap(te);
			// User code starts here
			seqNumIncrement();
			// User code ends here
		}
		 // User code starts here
        }

        //TL1AutoMsg
        if(tl1Adaptor)
            sendTL1AutoMsg("adventnet.tl1.moAttrChangeNotification",varbindVector,getMoNotiVarbindsInstrument());//No I18N
        // User code ends here
	}

	/**
	 * This method will be invoked from the SnmpAdaptor
	 * for sending alertWarningNotification traps
	 */
	public void alertWarningNotification (Vector varbindVector)
	{
	     // User code starts here
        //add noti into log
        if(doPersist)
            notiLogger.addNotificationToLog(varbindVector,FaultUtils.resolveFaultOID(FaultUtils.WARNING_ALARM_NOTIFICATION));

        if(snmpAdaptor)
        {
            // User code ends here
		TrapRequestEvent te =  new TrapRequestEvent(this,varbindVector,TrapRequestEvent.TFTABLE,".1.3.6.1.4.1.2162.4.3.8.2",2);//No I18N
				 // User code starts here
            te.setSourceForTrapInfo(TrapRequestEvent.TFTABLE);
            // User code ends here

		te.setTimeTicks(snmpadaptor.getUpTime().longValue());

		if(trapListener != null){
			trapListener.sendTrap(te);
			// User code starts here
			seqNumIncrement();
			// User code ends here
		}
		// User code starts here
        }

        //TL1AutoMsg
        if(tl1Adaptor)
            sendTL1AutoMsg("adventnet.tl1.alertWarningNotification",varbindVector,getAlertNotiVarbindsInstrument());//No I18N
        // User code ends here
	}

	/**
	 * This method will be invoked from the SnmpAdaptor
	 * for sending alertMajorNotification traps
	 */
	public void alertMajorNotification (Vector varbindVector)
	{
	      // User code starts here
        //add noti into log
        if(doPersist)
            notiLogger.addNotificationToLog(varbindVector,FaultUtils.resolveFaultOID(FaultUtils.MAJOR_ALARM_NOTIFICATION));

        if(snmpAdaptor)
        {
            // User code ends here
		TrapRequestEvent te =  new TrapRequestEvent(this,varbindVector,TrapRequestEvent.TFTABLE,".1.3.6.1.4.1.2162.4.3.8.2",4);//No I18N
				// User code starts here
            te.setSourceForTrapInfo(TrapRequestEvent.TFTABLE);
            // User code ends here


		te.setTimeTicks(snmpadaptor.getUpTime().longValue());

		if(trapListener != null){
			trapListener.sendTrap(te);
			// User code starts here
			seqNumIncrement();
			// User code ends here
		}
		 // User code starts here
        }

        //TL1AutoMsg
        if(tl1Adaptor)
            sendTL1AutoMsg("adventnet.tl1.alertMajorNotification",varbindVector,getAlertNotiVarbindsInstrument());//No I18N
        // User code ends here
	}

	/**
	 * This method will be invoked from the SnmpAdaptor
	 * for sending alertMinorNotification traps
	 */
	public void alertMinorNotification (Vector varbindVector)
	{
	      // User code starts here
        //add noti into log
        if(doPersist)
            notiLogger.addNotificationToLog(varbindVector,FaultUtils.resolveFaultOID(FaultUtils.MINOR_ALARM_NOTIFICATION));

        if(snmpAdaptor)
        {
            // User code ends here
		TrapRequestEvent te =  new TrapRequestEvent(this,varbindVector,TrapRequestEvent.TFTABLE,".1.3.6.1.4.1.2162.4.3.8.2",3);//No I18N
				 // User code starts here
            te.setSourceForTrapInfo(TrapRequestEvent.TFTABLE);
            // User code ends here

		te.setTimeTicks(snmpadaptor.getUpTime().longValue());

		if(trapListener != null){
			trapListener.sendTrap(te);
			// User code starts here
			seqNumIncrement();
			// User code ends here
		}
		// User code starts here
        }

        //TL1AutoMsg
        if(tl1Adaptor)
            sendTL1AutoMsg("adventnet.tl1.alertMinorNotification",varbindVector,getAlertNotiVarbindsInstrument());//No I18N
        // User code ends here
	}

	/**
	 * This method will be invoked from the SnmpAdaptor
	 * for sending moEnrolNotification traps
	 */
	public void moEnrolNotification (Vector varbindVector)
	{
	     // User code starts here
	       if(doPersist)
            notiLogger.addNotificationToLog(varbindVector,TopoUtils.moEnrolNotification);

        //send noti
        if(snmpAdaptor)
        {
            // User code ends here
		TrapRequestEvent te =  new TrapRequestEvent(this,varbindVector,TrapRequestEvent.TFTABLE,".1.3.6.1.4.1.2162.4.2.14.2",1);//No I18N


		te.setTimeTicks(snmpadaptor.getUpTime().longValue());
		 // User code starts here
            te.setSourceForTrapInfo(TrapRequestEvent.TFTABLE);
            // User code ends here


		if(trapListener != null){
			trapListener.sendTrap(te);
			// User code starts here
		seqNumIncrement();
		  // User code ends here
		}
		// User code starts here
        //TL1AutoMsg
        if(tl1Adaptor)
            sendTL1AutoMsg("adventnet.tl1.moEnrolNotification",varbindVector,getMoNotiVarbindsInstrument());//No I18N
        // User code ends here
        }
	}

	/**
	 * This method will be invoked from the SnmpAdaptor
	 * for sending thresholdNotification traps
	 */
	public void thresholdNotification (Vector varbindVector)
	{
	     // User code starts here
	     if(doPersist)
            notiLogger.addNotificationToLog(varbindVector,EventUtils.thresholdNotification);

		if(snmpAdaptor)
		{
		     // User code ends here
		TrapRequestEvent te =  new TrapRequestEvent(this,varbindVector,TrapRequestEvent.TFTABLE,".1.3.6.1.4.1.2162.4.4.7.1",1);//No I18N

		te.setTimeTicks(snmpadaptor.getUpTime().longValue());

		if(trapListener != null){
			trapListener.sendTrap(te);
			// User code starts here
			seqNumIncrement();
			// User code ends here
		}
		// User code starts here
		}

		//TL1AutoMsg
		if(tl1Adaptor)
		{
			sendTL1AutoMsg("adventnet.tl1.thresholdNotification",varbindVector,getPerfNotiVarbindsInstrument());//No I18N
		}
		// User code ends here
	}

// User code starts here
    boolean getFirstMo(String name[])
    {
	    ResultSet rs = null;

	    PreparedStatementWrapper pstatForMo = null;
	    PreparedStatement pstatementForMo = null;
	    try
	    {
		    //rs = topoDB.query("SELECT MIN(NAME) FROM ManagedObject");//No I18N
		    pstatForMo = rlAPI.fetchPreparedStatement(psSelectMoID1);
		    pstatementForMo = pstatForMo.getPreparedStatement();
		    rs = rlAPI.executeQuery(pstatementForMo);
		    rs.next();
		    name[0] = (String)rs.getObject(1);
		    return true;
	    }
	    catch(Exception e)
	    {
		    agentErr.fail(NmsUtil.GetString("Exception "),e); //No I18N
		    return false;
	    }
	    finally
	    {
		    try{
			    rs.close();
		    }catch(Exception e){}
		    rlAPI.returnPreparedStatement(pstatForMo);
	    }

	    /*//rs = topoDB.query("SELECT MIN(NAME) FROM ManagedObject WHERE OWNERNAME = '" + name[1] + "'");//No I18N
	      pstatForMo = rlAPI.fetchPreparedStatement(psSelectMoID2);
	      pstatementForMo = pstatForMo.getPreparedStatement();
	      pstatementForMo.setString(1,name[1]);
	      rs = rlAPI.executeQuery(pstatementForMo);
	      rs.next();
	      name[0] = (String)rs.getObject(1);

	      return true;*/
    }
    boolean getNextMo(String name[])
    {
        ResultSet rs = null;

        PreparedStatementWrapper pstatForMo = null;
        PreparedStatement pstatementForMo = null;
        try
        {
	    //rs = topoDB.query("SELECT MIN(NAME) FROM ManagedObject WHERE NAME > '" + name[0] + "' AND OWNERNAME = '" + name[1] + "'");//No I18N
	    pstatForMo = rlAPI.fetchPreparedStatement(psSelectMoID3);
	    pstatementForMo = pstatForMo.getPreparedStatement();
	    pstatementForMo.setString(1,name[0]);
	    //pstatementForMo.setString(2,name[1]);
	    rs = rlAPI.executeQuery(pstatementForMo);

            //For next ownerName
            /*if(!rs.next())
            {
                try
                {
                    rs.close();
                    //rs = topoDB.query("SELECT MIN(OWNERNAME) FROM ManagedObject WHERE OWNERNAME > '" + name[1] + "'");//No I18N

		    // THEME-II Start

		    rlAPI.returnPreparedStatement(pstatForMo);

		    // THEME-II End

                    pstatForMo = rlAPI.fetchPreparedStatement(psSelectMoID4);
                    pstatementForMo = pstatForMo.getPreparedStatement();
                    pstatementForMo.setString(1,name[1]);
                    rs = rlAPI.executeQuery(pstatementForMo);
                    if(!rs.next())
                        return false;
                    name[1] = (String)rs.getObject(2);
                }
                finally
                {
                    try{
                        rs.close();
                    }catch(Exception e){}
		    // THEME-II Start

		    rlAPI.returnPreparedStatement(pstatForMo);

		    // THEME-II End

                }

                //SELECT MIN(NAME) WHERE OWNERNAME=?
                pstatForMo = rlAPI.fetchPreparedStatement(psSelectMoID2);
                pstatementForMo = pstatForMo.getPreparedStatement();
                pstatementForMo.setString(1,name[1]);
                rs = rlAPI.executeQuery(pstatementForMo);
                rs.next();
            }*/
   
	   while (rs.next())
	   { 
	    	name[0] = (String)rs.getObject(1);
	   }

            return true;
        }
        catch(Exception e)
        {
	    agentErr.fail(NmsUtil.GetString("Exception "),e);//No I18N
            return false;
        }
        finally
        {
            try{
                rs.close();
            }catch(Exception e){}
            rlAPI.returnPreparedStatement(pstatForMo);
        }
    }
        public boolean syntaxOK(String value)
    {
        java.util.StringTokenizer st1 = new java.util.StringTokenizer(value,",");//No I18N
        while(st1.hasMoreTokens())
        {
            try
            {
                String token = st1.nextToken();
                char[] ch = token.trim().toCharArray();
                for(int i =0 ; i < ch.length; i++)
                {
                    if( !( (((int)ch[i] >= 48) && ((int)ch[i] <=57)) ||
                           (((int)ch[i] >= 65) && ((int)ch[i] <=90)) ||
                           (((int)ch[i] >= 97) && ((int)ch[i] <=122)) ) )
                    {
                        return false;
                    }
                }
            }
            catch(Exception e)
            {
                agentErr.fail(NmsUtil.GetString("Exception "),e);//No I18N
                return false;
            }
        }
        return true;
    }
    public String getPropValues(Properties prop,String propNames)
    {
        String values = "";//No I18N
        if(propNames.trim().equalsIgnoreCase("all"))//No I18N
        {
            try
            {
                Enumeration en = prop.propertyNames();
                while(en.hasMoreElements())
                {
                    if(!values.equals("")) values +=",";//No I18N

                    String propName = ((String)en.nextElement()).trim();
                    try
                    {
                        String value = prop.getProperty(propName);
                        values += propName + "=" + value;//No I18N
                    }
                    catch(Exception e){}
                }
            }
            catch(Exception e)
            {
                values = "No User Properties";//No I18N
            }
        }
        else
        {
            java.util.StringTokenizer st1 = new java.util.StringTokenizer(propNames,",");//No I18N
            while(st1.hasMoreTokens())
            {
                if(!values.equals("")) values +=",";//No I18N

                String propName = st1.nextToken().trim();
                try
                {
                    String value = prop.getProperty(propName);
                    values += propName + "=" + value;//No I18N
                }
                catch(Exception e)
                {
                    values += propName + "=null";//No I18N
                }
            }
        }

        return values;
    }
    int getArgValue(String text)
    {
        for(int i = 0 ;i< args.length;i += 2)
        {
            if(args[i].trim().equalsIgnoreCase(text))
            {
                if(text.equals("-rmiport") && args[i+1].trim().equals("$NMS_RMI_REG_PORT"))//No I18N
                    break;
                return Integer.parseInt(args[i+1]);
            }
        }
        return -1;
    }

    String getArgString(String text)
    {
        for(int i = 0 ;i< args.length;i += 2)
        {
            if(args[i].trim().equalsIgnoreCase(text))
            {
                return args[i+1];
            }
        }
        return "";//No I18N
    }
    /*
     * Initializing the user object proprty details
     */
    String usrObjTypes[] = null;
    String tableOids[] = null;
    String tableName[] = null;

    void initUsrObjTypesAndProps()
    {
        Vector usrObjTab = getMoDerivedPropNameTable().getTableVector();
        int usrObjTabSize = 0;
        if(usrObjTab != null)
        {
            usrObjTabSize = usrObjTab.size();
            usrObjTypes = new String[usrObjTabSize];
            tableOids = new String[usrObjTabSize];
            tableName = new String[usrObjTabSize];

            for(int i = 0;i < usrObjTabSize;i++)
            {
                MoDerivedPropNameEntry usrObjEntry = (MoDerivedPropNameEntry)usrObjTab.elementAt(i);
                try
                {
                    usrObjTypes[i] = usrObjEntry.getObjClassName();
                    tableOids[i] = usrObjEntry.getTableOid();
                    tableName[i] = usrObjEntry.getTableName();
                }
                catch(Exception e) {}
            }
        }
    }
        String getChildTableOID(String moClassName, String tableClassName)
    {
        this.initUsrObjTypesAndProps();

        moClassName = getCorrspondingClassName(moClassName);
        tableClassName = getCorrspondingClassName(tableClassName);

        if(moClassName.equals(tableClassName))
        {
            return ".0";//No I18N
        }
        else
        {
            String parentClassName = "";//No I18N
            while(true)
            {
                try
                {
                    parentClassName = Class.forName(moClassName).getSuperclass().getName();
                    if(parentClassName.equals(tableClassName))
                        return getTableOid(moClassName);
                    moClassName = parentClassName;
                }
                catch(Exception e)
                {
                    return ".0";//No I18N
                }
            }
        }
    }

    String getChildTableName(String OID)
    {
        for(int i=0; i<tableOids.length;i++)
        {
            if(tableOids[i].equals(OID))
                return tableName[i];
        }
        return "";//No I18N
    }

    String getCorrspondingClassName(String className)
    {
        for (int i = 0; i < usrObjTypes.length; i++)
        {
            if(usrObjTypes[i].endsWith("." + className))//No I18N
                return usrObjTypes[i];
        }

        return className;
    }
String getTableOid(String className)
    {
        for (int i = 0; i < usrObjTypes.length; i++)
        {
            if(usrObjTypes[i].equals(className))
                return tableOids[i];
        }

        return className;
    }
    /*
     * Getting the values from Xml files
     */
    public int[] getFromXmlFile(String file, String id)
    {

        int [] val = null;
        XMLDataReader xmld = new XMLDataReader("file:"+new File(file).getAbsolutePath());//No I18N

        try
        {
            Vector v = xmld.getRootChildNodes();
            String temp = "";//No I18N
            for(int i = 0;i < v.size(); i++)
            {
                XMLNode xmlNode = (XMLNode)v.elementAt(i);
                if(xmlNode.getNodeType() == XMLNode.ELEMENT)
                    temp += (xmlNode).getAttribute(id) + " ";//No I18N
            }

            java.util.StringTokenizer st1 = new java.util.StringTokenizer(temp);
            val = new int[st1.countTokens()];
            int i = 0;
            while(st1.hasMoreTokens())
            {
                val[i++] = Integer.parseInt(st1.nextToken());
            }
        }
        catch(Exception e)
        {
            agentErr.fail(NmsUtil.GetString("Exception "),e);//No I18N
		}
        return val;
    }

    /*
     * Getting the values from HTML files
     */
    public int getFromHtmlFile(String file,String text)
    {
        try
        {
            HtmlParser parser = new HtmlParser(text);
            java.io.Reader reader = new java.io.FileReader(file);
            new ParserDelegator().parse(reader, parser, true);
            reader.close();
            return parser.port;
        }
        catch(Exception e)
        {
            agentErr.fail(NmsUtil.GetString("Exception "),e);//No I18N
            return -1;
        }
    }

    /*
     * Inner class to read the HTML files
     */
    class HtmlParser extends HTMLEditorKit.ParserCallback
    {
        String text = "";//No I18N
        int port = 0;
        HtmlParser(String text)
        {
            super();
            this.text = text;
        }

        public void handleText(char[] data, int pos)
        {
            String datum = new String(data);
            if(datum.indexOf(text) != -1)
            {
                this.port = Integer.parseInt(datum.substring(datum.indexOf(':')+1,datum.length()).trim());
            }
        }
    }
/*
     * To Shut down the JMX-Agent
     */
    public void stopAgent()
    {
        if(snmpadaptor != null)
        {
            snmpadaptor.stopService();
            snmpadaptor.getSnmpAgent().getSnmpAPI().close();
        }
        if(rmiadaptor != null)
            rmiadaptor.stopService();
        if(corbaadaptor != null)
            corbaadaptor.stopService();
        if(httpconnector != null)
            httpconnector.stopService();
        if(htmladaptor != null)
            htmladaptor.stopService();
        if(tl1adaptor != null)
            tl1adaptor.stopService();

        //To stop all threads
        if(com.adventnet.utilities.scheduler.Scheduler.getScheduler("JMX") != null)//No I18N
            com.adventnet.utilities.scheduler.Scheduler.getScheduler("JMX").stopAll();//No I18N

        //To close SnmpSession for TrapListener
        if(trapListener != null)
            trapListener.closeTrapSession();
        if(topoObsvrImpl!=null && topoAPI!=null)
        {
            topoObsvrImpl.deRegister();
        }
        if(alertObsvrImpl!=null && alertAPI!=null)
        {
            alertObsvrImpl.deRegister();
        }
        if(eventObsvrImpl!=null && eventAPI!=null)
        {
            eventObsvrImpl.deRegister();
        }

    }
    /*
     * Snmp V3 initial settings
     * There will be some variables that are hardcoded here ..
     */
    int authProtocol = USMUserEntry.MD5_AUTH;
    int privProtocol = USMUserEntry.CBC_DES;

    String authPassword = "authUser";//No I18N
    String privPassword = "privUser";//No I18N

    String engineId = null;

    private void initUSMEntries(SnmpAPI api,SnmpAgent agentName)
    {
        int psID = rlAPI.getPreparedStatementID("SELECT HOST,USERNAME FROM USMTABLE");//No I18N

        ResultSet rs = null;
        PreparedStatementWrapper pstat = null;
        PreparedStatement pstatement = null;
        try
        {
            pstat = rlAPI.fetchPreparedStatement(psID);
            pstatement = pstat.getPreparedStatement();
            rs = rlAPI.executeQuery(pstatement);
            while(rs.next())
            {
                String host = (String)rs.getObject(1);
                String userName = (String)rs.getObject(2);

                if(host.equalsIgnoreCase(agentName.getLocalAddress()) && userName.equalsIgnoreCase("authUser"))//No I18N
                    return;
            }
        }
        catch(Exception e)
        {
        }
        finally
        {
            try{
                rs.close();
            }catch(Exception e){}
            rlAPI.returnPreparedStatement(pstat);
        }

        USMUserTable USMTable = ((USMUserTable)(api.getSecurityProvider().getTable(3)));

        //Get the EngineId from the API.
        byte[] engineId = api.getSnmpEngineID();
        engineId = api.getSnmpEngineID();

        SnmpEngineEntry e = new SnmpEngineEntry(agentName.getLocalAddress(),agentName.getPort());
        e.setEngineID(engineId);
        api.getSnmpEngine().addEntry(e);

        //Adding the noAuthUser entry.
        USMUserEntry noAuthUser =  new USMUserEntry(new String("noAuthUser").getBytes(),engineId);//No I18N
        noAuthUser.setSecurityLevel(Snmp3Message.NO_AUTH_NO_PRIV);
        noAuthUser.setEngineEntry(e);

        USMTable.addEntry(noAuthUser);

        //Adding the authUser entry.
        // Create a new USMUserEntry for the userName and engineID pair.
        USMUserEntry authUser = new USMUserEntry(new String("authUser").getBytes(), engineId);//No I18N

        authUser.setAuthProtocol(authProtocol);
        authUser.setAuthPassword(authPassword.getBytes());
        // Convert the auth password to key.
        byte[] authKey = USMUtils.password_to_key(authProtocol,
                                                  authPassword.getBytes(),
                                                  authPassword.getBytes().length,
                                                  engineId);
        authUser.setAuthKey(authKey);
        authUser.setSecurityLevel(Snmp3Message.AUTH_NO_PRIV);

        authUser.setEngineEntry(e);

        USMTable.addEntry(authUser);

        //Adding the privUSer entry.
        USMUserEntry privUser = new USMUserEntry(new String("privUser").getBytes(),  engineId);//No I18N
        privUser.setAuthProtocol(authProtocol);
        privUser.setAuthPassword(authPassword.getBytes());
        privUser.setPrivProtocol(privProtocol);
        privUser.setPrivPassword(privPassword.getBytes());

        //set the already calculated authKey itself.
        privUser.setAuthKey(authKey);
        //calculate the privKey .
        byte[] privKey = USMUtils.password_to_key(authProtocol,
                                                  privPassword.getBytes(),
                                                  privPassword.getBytes().length,
                                                  engineId);
        privUser.setPrivKey(privKey);

        privUser.setSecurityLevel(Snmp3Message.AUTH_PRIV);

        //set the engine entry.
        privUser.setEngineEntry(e);
        USMTable.addEntry(privUser);
    }
   private int seqNumIncrement()
	{
		if(sequNo>=Integer.MAX_VALUE)
		{
			sequNo=0;
		}
		return sequNo++;
	}

    //by shankar
    private void initializeNotificationValues()
    {
        SeverityInfo sevInfo = SeverityInfo.getInstance();

        SeverityIterator iterator = sevInfo.getIterator();

        if(iterator.moveToClear())
        {
            FaultUtils.CLEAR_ALARM_NOTIFICATION = iterator.getCurrent();;
            FaultUtils.WARNING_ALARM_NOTIFICATION = iterator.getNextCriticality();
            FaultUtils.MINOR_ALARM_NOTIFICATION = iterator.getNextCriticality();
            FaultUtils.MAJOR_ALARM_NOTIFICATION = iterator.getNextCriticality();
            FaultUtils.CRITICAL_ALARM_NOTIFICATION = iterator.getNextCriticality();
        }
    }


    

    //by senthil
    /*
     * Getting all NMS API's
     */
    private void initAPIs()
    {
	// THEME-II Start
        for(int i = 0; i < 20; i++)
	    // THEME-II End
        {
            initTopo();

            initAlert();

            initEvent();

            initPoll();

            initUserStorageAPI();

            if((topoAPI == null) || (alertAPI == null) ||
               (pollAPI == null) || (pollMgr == null) || (eventAPI == null) || (userStorageApi == null))
            {
                try
                {
		    // THEME-II Start
                    Thread.sleep(100);
		    // THEME-II End
                }
                catch (InterruptedException iex) { }
                continue;
            }
            else
            {
                //agentUser.log("Got all the APIs and registered",1);//No I18N
                break;
            }
        }//end of while loop.
    }

    boolean initTopo()
    {
        if(topoAPI == null)
        {
       	  topoAPI = com.adventnet.nms.topodb.DBServer.topodb;
          if(topoAPI != null && topoObsvr)
		  {
			topoObsvrImpl.register(topoAPI);
            return true;
		  }
		}
		if(topoAPI != null)
		{
            return true;
		}
        return false;
    }

    boolean initAlert()
    {
        
        if(alertAPI == null)
        {
            alertAPI = com.adventnet.nms.alertdb.FaultMgr.alertapi;
			if(alertAPI != null && faultObsvr)
			{
				alertObsvrImpl.register(alertAPI);
				return true;
			}

        }
        
        if(alertAPI != null)
		{
            return true;
		}
        return false;
    }

    boolean initEvent()
    {
        
        if(eventAPI == null)
        {
            eventAPI = com.adventnet.nms.eventdb.EventMgr.eventapi;
         if(eventAPI != null)
		 {
			eventObsvrImpl.register(eventAPI);
            return true;
		 }
        }
		if(eventAPI != null)
		{
            return true;
		}
        return false;
    }

    boolean initPoll()
    {
        try
        {
            if(pollAPI == null)
                pollAPI = com.adventnet.nms.poll.Collector.pollmgr;
            if(pollMgr == null)
                pollMgr = com.adventnet.nms.poll.Collector.pollmgr;
        }
        catch (Exception e)
        {
            agentErr.fail(NmsUtil.GetString("Remote exception: "),e);//No I18N
        }

        if((pollAPI != null) && (pollMgr != null))
            return true;
        else
            return false;
    }

	private boolean firstPrint=true;

    boolean initUserStorageAPI()
    {
        if(userStorageApi == null)
        {
		
			
			//userStorageApi = (StorageAPI)NmsUtil.getAPI("StorageAPI");//No I18N
		
			//IMPORTANT NOTE: Uncomment the above line for getting the StorageAPI
			
			//If you uncomment that line, you have to provide or uncomment the following entry in hibernate.cfg.xml
			// <mapping resource="com/adventnet/nms/jmxagent/AgentDefValObject.hbm.xml"/>

			// Also add AdventNetWebNmsAgent.jar in classpath for the scripts DB involved scripts like reinitialize_nms.sh/bat etc & wherever it is required
			//
			if(userStorageApi==null && firstPrint)
			{
				System.out.println("Table AgentDefValObject not created for JMX Agent");//no i18n
				firstPrint=false;
			}

        }
        if(userStorageApi != null)
            return true;
        else
            return false;
    }

    /*
     * Getting the DataBase connection
     */
    private void createDB()
    {
        try
        {
            //Class.forName("org.gjt.mm.mysql.Driver");//No I18N
            //c = DriverManager.getConnection("jdbc:mysql://localhost/WebNmsDB");//No I18N

            rlAPI = NmsUtil.relapi;
        }
        catch(Exception e)
        {
            agentErr.fail(NmsUtil.GetString("Remote exception: "),e);//No I18N
        }
    }

    /*
     * Initialzing the SQL querys
     */
    private void initPrStatements()
    {
        //to get the min and max of notiindex
        psSelectMaxOfNotiLogID = rlAPI.getPreparedStatementID("SELECT MAX(" + RelationalUtil.getAlias("logTime") + ") FROM NotificationLog");//No I18N
        psSelectMinOfNotiLogID = rlAPI.getPreparedStatementID("SELECT MIN(" + RelationalUtil.getAlias("logTime") + ") FROM NotificationLog");//No I18N
        psSelectNotiIndexOfNotiLogID = rlAPI.getPreparedStatementID("SELECT " + RelationalUtil.getAlias("notiIndex") + " FROM NotificationLog WHERE " + RelationalUtil.getAlias("logTime") + "=?");//No I18N

        //to SELECT the COUNTs of NotificationLog and VarBindLog
        psSelectCountOfNotiLogID = rlAPI.getPreparedStatementID("SELECT COUNT(" + RelationalUtil.getAlias("notiIndex") + ") FROM NotificationLog");//No I18N
        psSelectCountOfVarLogID = rlAPI.getPreparedStatementID("SELECT COUNT(" + RelationalUtil.getAlias("varIndex") + ") FROM VarBindLog");//No I18N

        //to SELECT of NotificationLog and VarBindLog
        psSelectFromNotiID = rlAPI.getPreparedStatementID("SELECT * FROM NotificationLog WHERE " + RelationalUtil.getAlias("notiIndex") + "=?");//No I18N
        psSelectFromVarID = rlAPI.getPreparedStatementID("SELECT * FROM VarBindLog WHERE " + RelationalUtil.getAlias("notiIndex") + "=? AND " + RelationalUtil.getAlias("varIndex") + "=?");//No I18N

        //to INSERT Into NotificationLog and VarBindLog
        psInsertIntoNotiID = rlAPI.getPreparedStatementID("INSERT INTO NotificationLog (" + RelationalUtil.getAlias("notiIndex") + "," + RelationalUtil.getAlias("logTime") + "," + RelationalUtil.getAlias("noVarBinds") + "," + RelationalUtil.getAlias("notiOID") + ") VALUES(?,?,?,?)");//No I18N
        psInsertIntoVarID = rlAPI.getPreparedStatementID("INSERT INTO VarBindLog (" + RelationalUtil.getAlias("notiIndex") + "," + RelationalUtil.getAlias("varIndex") + "," + RelationalUtil.getAlias("varType") + "," + RelationalUtil.getAlias("varValue") + ") VALUES (?,?,?,?)");//No I18N

        //to DELETE from NotificationLog and VarbindLog
        psDeleteRowOfNotiLogID = rlAPI.getPreparedStatementID("DELETE FROM NotificationLog WHERE " + RelationalUtil.getAlias("notiIndex") + "=?");//No I18N
        psDeleteRowOfVarbindLogID = rlAPI.getPreparedStatementID("DELETE FROM VarBindLog WHERE " + RelationalUtil.getAlias("notiIndex") + "=?");//No I18N

        //to SELECT from Mo
        psSelectMoID1 = rlAPI.getPreparedStatementID("SELECT MIN(" + RelationalUtil.getAlias("name") + ") FROM ManagedObject");//No I18N
        psSelectMoID2 = rlAPI.getPreparedStatementID("SELECT MIN(" + RelationalUtil.getAlias("name") + ") FROM ManagedObject WHERE " + RelationalUtil.getAlias("ownerName") + "=?");//No I18N
        psSelectMoID3 = rlAPI.getPreparedStatementID("SELECT MIN(" + RelationalUtil.getAlias("name") + ") FROM ManagedObject WHERE " + RelationalUtil.getAlias("name") + ">?");//No I18N
        psSelectMoID4 = rlAPI.getPreparedStatementID("SELECT MIN(" + RelationalUtil.getAlias("ownerName") + ") FROM ManagedObject WHERE " + RelationalUtil.getAlias("ownerName") + ">?");//No I18N
    }

    private void initStartIndex()
    {
        ResultSet rs = null;
        PreparedStatementWrapper pstat = null;
        PreparedStatement pstatement = null;
        long minLogtime = 0;

        //init startIndex
        try
        {
            pstat = rlAPI.fetchPreparedStatement(psSelectMinOfNotiLogID);
            pstatement = pstat.getPreparedStatement();

            rs = rlAPI.executeQuery(pstatement);
            rs.next();
            if(PureServerUtils.getDatabaseName().equalsIgnoreCase("timesten"))//No I18N
                minLogtime = Long.parseLong(rs.getString(1));
            else
                minLogtime = rs.getLong(1);
        }
        catch(Exception e)
        {
            startIndex = 1;
            sequNo = 0;
            return;
        }
        finally
        {
            try{
                rs.close();
            }
            catch(Exception e)
            {}
            rlAPI.returnPreparedStatement(pstat);
        }

        try
        {
            pstat = rlAPI.fetchPreparedStatement(psSelectNotiIndexOfNotiLogID);
            pstatement = pstat.getPreparedStatement();
            pstatement.setLong(1,minLogtime);
            rs = rlAPI.executeQuery(pstatement);
            rs.next();
            startIndex = rs.getInt(1);
            if(startIndex == 0) startIndex = 1;
        }
        catch(Exception e)
        {
            startIndex = 1;
            sequNo = 0;
            return;
        }
        finally
        {
            try{
                rs.close();
            }
            catch(Exception e)
            {}
            rlAPI.returnPreparedStatement(pstat);
        }

        //init seqNo
        long maxLogtime = 0;
        try
        {
            pstat = rlAPI.fetchPreparedStatement(psSelectMaxOfNotiLogID);
            pstatement = pstat.getPreparedStatement();

            rs = rlAPI.executeQuery(pstatement);
            rs.next();
            if(PureServerUtils.getDatabaseName().equalsIgnoreCase("timesten"))//No I18N
                maxLogtime = Long.parseLong(rs.getString(1));
            else
                maxLogtime = rs.getLong(1);
        }
        catch(Exception e)
        {
            startIndex = 1;
            sequNo = 0;
            return;
        }
        finally
        {
            try
            {
                rs.close();
            }
            catch(Exception e)
            {
            }
            rlAPI.returnPreparedStatement(pstat);
        }

        try
        {
            pstat = rlAPI.fetchPreparedStatement(psSelectNotiIndexOfNotiLogID);
            pstatement = pstat.getPreparedStatement();
            pstatement.setLong(1,maxLogtime);
            rs = rlAPI.executeQuery(pstatement);
            rs.next();
            sequNo = rs.getInt(1);
        }
        catch(Exception e)
        {
            startIndex = 1;
            sequNo = 0;
        }
        finally
        {
            try
            {
                rs.close();
            }catch(Exception e){}
            rlAPI.returnPreparedStatement(pstat);
        }
    }

    /*
     * Initializing the property names from DataBase using UserStorageAPI
     */
    void initDefValues()
    {
        moUserPropNames = getDefValue("moUserPropNames");//No I18N
        moExtraPropNames = getDefValue("moExtraPropNames");//No I18N
        alertUserPropNames = getDefValue("alertUserPropNames");//No I18N
        alertExtraPropNames = getDefValue("alertExtraPropNames");//No I18N
        eventUserPropNames = getDefValue("eventUserPropNames");//No I18N

        String tempVal = getDefValue("maxRows");//No I18N
        maxRows = (!tempVal.equals("")) ? Integer.parseInt(tempVal) : maxRows;//No I18N
    }

    public String getDefValue(String name)
    {
        if(!initUserStorageAPI())
            return "";//No I18N

        AgentDefValObject object = null;

        try
        {
            object = (AgentDefValObject)userStorageApi.getObject(name,"com.adventnet.nms.jmxagent.AgentDefValObject");//No I18N
            if(object != null)
                return object.getValue();
        }
        catch(Exception e)
        {
            agentErr.fail(NmsUtil.GetString("Exception "),e);//No I18N
        }

        return "";//No I18N
    }
        public void updateDefValue(String name, String value)
    {
        if(!initUserStorageAPI())
            return;

        try
        {
            
         AgentDefValObject oldObject = (AgentDefValObject)userStorageApi.getObject(name,"com.adventnet.nms.jmxagent.AgentDefValObject");//No I18N
		  if(oldObject==null)
		  {

       		    AgentDefValObject object = new AgentDefValObject();
				object.setName(name);
        	    object.setValue(value);
                userStorageApi.addObject(object);
		  }
		  else
		  {
			oldObject.setValue(value);
			userStorageApi.updateObject(oldObject,name);
		  }
        }
        catch(Exception e)
        {

 	       agentErr.fail(NmsUtil.GetString("Exception "),e);//No I18N
        }
    }
        /*
     * Getting the Notification count
     */
    public int getNotiLogRowCount()
    {
        int rowCount = 0;
        PreparedStatementWrapper pstat = null;
        ResultSet rs = null;
        try
        {
            pstat = rlAPI.fetchPreparedStatement(psSelectCountOfNotiLogID);
            PreparedStatement pstatement = pstat.getPreparedStatement();

            rs = rlAPI.executeQuery(pstatement);
            rs.next();
            rowCount =  rs.getInt(1);
        }
        catch(Exception e)
        {
            agentErr.fail(NmsUtil.GetString("Exception "),e);//No I18N
        }
        finally
        {
            try{
                rs.close();
            }catch(Exception e){}
            rlAPI.returnPreparedStatement(pstat);
        }

        return rowCount;
    }

 public Vector getObjectsFromDB(String className,Properties match)
    {
        Vector vec=null;
        try
            {
                vec=topoAPI.getObjects(className,match);
            }
        catch(Exception e)
            {
                e.printStackTrace();

            }
        return vec;
    }
// User code ends here
}







