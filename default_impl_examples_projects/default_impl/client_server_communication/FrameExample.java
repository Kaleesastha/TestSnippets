//$Id: FrameExample.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.net.URLEncoder;

import com.adventnet.nms.util.*;
import com.adventnet.nms.startclient.NmsFrame;
import com.adventnet.beans.digit.*;
import com.adventnet.snmp.beans.SnmpPoller;
import com.adventnet.snmp.snmp2.SnmpVarBind;
import com.adventnet.snmp.snmp2.SnmpVar;
import com.adventnet.snmp.snmp2.SnmpCounter64;
import com.adventnet.snmp.snmp2.SnmpAPI;

/*
 * This is an example of a frame which can be popped up from the map view
 * or the list view by specifying in a menu .In this example we will pop up
 * this frame from the snmpmenu and it will enable one to see the collected
 * data as it is getting collected for the agent . This example will also
 * illustrate the use of the common socket for communication with the server
 *
 * This examples uses the TestClient.java and TestSession.java present in
 * examples/client_server_communication/  and ProcessTest.java present in
 * examples/new_module
 * For this example to work the ProcessTest module needs to be started.
 * Please refer to ProcessTest.java in examples/new_module for further details
 *
 *
 */




public class FrameExample extends JFrame implements NmsFrame , ActionListener ,ListSelectionListener {



    // The reference to the Applet , NmsMainApplet which will be passed to this
    // class when it
    JApplet a;


    static boolean initialized = false;

    Vector pollkeyData ;
    //lists the polleddata keys that are there for that agent that is selected.
    JList pollkey_list ;

    JLabel statsLabel;
    JLabel timeLabel;
    JLabel periodLabel;
    JLabel valueLabel;
    JLabel toplabel;
    //this button is used to deregister the key that is selected from being updated.
    JButton deregisterButton;
    //this is used to register for the data collected for the key that is selected.
    JButton registerButton;
    //clicking this button shows a graph which shows all the data that are
    //collected for that particular key that was selected.
    JButton collectedStatsButton;
    //this button is to check the data collection for that key currently.
    JButton currentStatsButton;
    JButton reportsButton;
    JButton helpButton;
    JButton closeButton;
    //this field shows the key that is selected.
    JTextField statsText;
    //shows the time at which data is collected.
    JTextField timeText;
    //shows the period after which polling will be done for that key.
    JTextField periodText;
    //the value collected will be shown here.
    DigDisp valueField;
    String agt = null;
    Hashtable pollPeriods = new Hashtable();
    /* Vector holds Properties of all PolledData for an agent */
    Vector agentPollData = new Vector();
    private String command_string = null;

    private String chartType = null; //The chart type (jfreechart).
	/*
     * When the user clicks the menu item , this class will be instantiated
     * and this method is called to initialize . After this method the
     * setVisible() method will be invoked
     */
    public void init(JApplet applet) {

        //setSize(700,500);
        a = applet;
        // One can use the menu to pass information about the mapsymbol or
        //list item selected . Here we get the information of the name of
        //the ManagedObject that is selected .
        // if you see the snmpmenu.menu you will know how the parameters
        // can be passed
        setTitle(NmsClientUtil.GetString("Performance Monitoring"));
        agt = a.getParameter("AGENT");//No Internationalisation
		chartType = a.getParameter("ChartType");
        //System.err.println(" In frame example . about to initialize for agent:"+agt);

        // Instantiate some UI components to show the data .
        statsLabel = new JLabel (NmsClientUtil.GetString("Statistic"));
        timeLabel = new JLabel (NmsClientUtil.GetString("Time"));
        periodLabel = new JLabel (NmsClientUtil.GetString("Poll period"));
        valueLabel = new JLabel (NmsClientUtil.GetString("Value"));

        deregisterButton = new JButton();
	deregisterButton.setVisible(false);
        registerButton = new JButton();
        collectedStatsButton = new JButton();
        currentStatsButton = new JButton();
        reportsButton = new JButton();
        helpButton = new JButton();
        closeButton = new JButton();
        // The DigDisp or the Digital Display is component which is available in
        // the AdventNet Management Builder
        // com.adventnet.beans.digit  package
        valueField = new DigDisp();
        // Set the number of Digits which u want to see .If any number is longer
        // than this then it is not shown .
        valueField.setNumDigits(10);

        deregisterButton.setText(NmsClientUtil.GetString("Deregister"));
        deregisterButton.addActionListener(this);
        deregisterButton.setEnabled(false);
        registerButton.setText(NmsClientUtil.GetString("Register"));
        registerButton.addActionListener(this);
        collectedStatsButton.setText(NmsClientUtil.GetString("Collected Statistics"));
        collectedStatsButton.addActionListener(this);
        currentStatsButton.setText(NmsClientUtil.GetString("Current Statistics"));
        currentStatsButton.addActionListener(this);
        reportsButton.setText(NmsClientUtil.GetString("View Reports"));
        reportsButton.addActionListener(this);
        helpButton.setText(NmsClientUtil.GetString("Help"));
        closeButton.setText(NmsClientUtil.GetString("Close"));
        helpButton.addActionListener(this);
        closeButton.addActionListener(this);

        statsText = new JTextField("                ");//No Internationalisation
        timeText = new JTextField("                ");//No Internationalisation
        periodText = new JTextField("                ");//No Internationalisation

        statsText.setEditable(false);
        timeText.setEditable(false);
        periodText.setEditable(false);



        //Add Components using the default FlowLayout.
        pollkey_list = new JList();
        pollkey_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        pollkey_list.setBackground(Color.white);
        pollkey_list.addListSelectionListener(this);
        pollkeyData = new Vector();
        pollkey_list.setListData(pollkeyData);
        JScrollPane spane1 = new JScrollPane(pollkey_list);
        spane1.setBorder(new javax.swing.border.TitledBorder(NmsClientUtil.GetString("Polled Statistics")));

        JPanel pan = new JPanel();
        GridBagLayout gridBag = new GridBagLayout();
        pan.setLayout(gridBag);
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = gc.HORIZONTAL;
        gc.insets = new Insets(10,3,10,1);
        gc.gridx = 0;
        gc.gridy = 0;
        gc.weightx = 1;
        gridBag.setConstraints(statsLabel,gc);
        pan.add(statsLabel);
        gc.insets = new Insets(10,1,10,3);
        gc.gridx = 1;
        gc.gridy = 0;
        gc.weightx = 1;
        gridBag.setConstraints(statsText,gc);
        pan.add(statsText);
        gc.insets = new Insets(10,3,10,1);
        gc.gridx = 0;
        gc.gridy = 1;
        gc.weightx = 1;
        gridBag.setConstraints(timeLabel,gc);
        pan.add(timeLabel);
        gc.insets = new Insets(10,1,10,3);
        gc.gridx = 1;
        gc.gridy = 1;
        gc.weightx = 1;
        gridBag.setConstraints(timeText,gc);
        pan.add(timeText);
        gc.insets = new Insets(10,3,10,1);
        gc.gridx = 0;
        gc.gridy = 2;
        gc.weightx = 1;
        gridBag.setConstraints(periodLabel,gc);
        pan.add(periodLabel);
        gc.insets = new Insets(10,1,10,3);
        gc.gridx = 1;
        gc.gridy = 2;
        gc.weightx = 1;
        gridBag.setConstraints(periodText,gc);
        pan.add(periodText);
        gc.insets = new Insets(10,3,10,1);
        gc.gridx = 0;
        gc.gridy = 3;
        gc.weightx = 1;
        gridBag.setConstraints(valueLabel,gc);
        pan.add(valueLabel);
        gc.insets = new Insets(10,1,10,3);
        gc.gridx = 1;
        gc.gridy = 3;
        gc.weightx = 1;
        gridBag.setConstraints(valueField,gc);
        pan.add(valueField);

        JPanel bottomPanel = new JPanel();
        GridBagLayout gridBag1 = new GridBagLayout();
        bottomPanel.setLayout(gridBag1);

        gc.insets = new Insets(10,2,10,2);
        gc.gridx = 0;
        gc.gridy = 0;
        gc.weightx = 1;
        gridBag1.setConstraints(registerButton,gc);
        bottomPanel.add(registerButton);

        gc.gridx = 0;
        gc.gridy = 0;
        gc.weightx = 1;
        gridBag1.setConstraints(deregisterButton,gc);
        bottomPanel.add(deregisterButton);

        gc.gridx = 1;
        gc.gridy = 0;
        gc.weightx = 1;
        gridBag1.setConstraints(collectedStatsButton,gc);
        bottomPanel.add(collectedStatsButton);

        gc.gridx = 2;
        gc.gridy = 0;
        gc.weightx = 1;
        gridBag1.setConstraints(currentStatsButton,gc);
        bottomPanel.add(currentStatsButton);

        gc.gridx = 0;
        gc.gridy = 1;
        gc.weightx = 1;
        gridBag1.setConstraints(reportsButton,gc);
        bottomPanel.add(reportsButton);

        gc.gridx = 2;
        gc.gridy = 1;
        gc.weightx = 1;
        gridBag1.setConstraints(helpButton,gc);
        bottomPanel.add(helpButton);


        gc.gridx = 1;
        gc.gridy = 1;
        gc.weightx =1 ;
        gridBag1.setConstraints(closeButton,gc);
        bottomPanel.add(closeButton);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,spane1,pan);
        splitPane.setDividerSize(2);
        splitPane.setDividerLocation(150);
        JPanel mainpanel = new JPanel();
        mainpanel.setLayout(new BorderLayout());
        mainpanel.add("Center",splitPane);  //No Internationalisation
        JPanel toppanel = new JPanel();
        toppanel.setLayout(new BorderLayout());
        JLabel toplabel = new JLabel(NmsClientUtil.GetString("Polled statistics for agent ")+agt);
        toppanel.add("Center",toplabel);//No Internationalisation
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add("North",toppanel);//No Internationalisation
        getContentPane().add("Center",mainpanel);//No Internationalisation
        getContentPane().add("South",bottomPanel);//No Internationalisation
        addWindowListener(new MyWindowCloser());
        //setResizable(false);
        //getContentPane().add(mainpanel);

        // After taking care of the ui , let us try to set up communicastion
        // with the server .
        if (!initialized) start();
        if (testclient.connected) {
            // If the communication has been established then get all the keys
            // of th configured polls for this agent
            testclient.getAllPollKeys(agt,this);
        }
    }


    // Ideally you can have many frames opened . but all of them should share
    // the same client for communicating with the server .
    // The TestClient will be used to communicate with the server .

    static TestClient testclient;

    void start() {
	try {
	    // instantiate an instance of test client for communication
	    if (testclient==null)
		testclient = new TestClient();
	    // open a communication channel with the server
	    if (!testclient.connected)
		testclient.open();
	    initialized=true;
	} catch (Exception e) {
	    System.err.println(NmsClientUtil.GetString("Error opening Test client"));
	}
    }

    String removeAgentFromKey(String key) throws Exception
    {
        StringTokenizer keyTokens = new StringTokenizer(key);
        String keyLbl = "";
        int i = 1;
        while(keyTokens.hasMoreTokens())
        {
            if(i==1)
            {
                keyLbl=keyLbl+keyTokens.nextToken()+" - ";
            }
            else if(i==3)
            {
                keyLbl=keyLbl+keyTokens.nextToken();
                break;
            }
            else
            {
                keyTokens.nextToken();
            }
            i++;
        }
	return keyLbl;
    }

    // when the server informs of the configured poll keys for the agent
    // display them in a list
    void displayPollKeyList(int siz,Vector v) {
	pollkeyData = v;
	Vector v2 = new Vector();
	for (int i=0;i<pollkeyData.size();i++) {
	    String key = (String) pollkeyData.elementAt(i);
	    try {
		v2.addElement(removeAgentFromKey(key));
	    } catch (Exception anye) {
		v2.addElement(key);
	    }
	}
	pollkey_list.setListData(v2);
	if (siz>0)
	    pollkey_list.setSelectedIndex(0);
	validate();
    }

    /* Added in SP8 for PolledData properties(port number required to plot current statistics) */
    void setPolledDataForAgent(Vector v)
    {
        if(v!=null)
        {
            agentPollData = v;
        }
    }

    public void actionPerformed(ActionEvent e) {
	// deregister for the polled statistic selected
        if (e.getActionCommand().equals(NmsClientUtil.GetString("Deregister"))) {
            registerButton.setVisible(true);
            deregisterButton.setVisible(false);
            int numb = pollkey_list.getSelectedIndex();
            String s = null;
            if ( (numb>=0) && (numb<pollkeyData.size()) )
                s=(String) pollkeyData.elementAt(numb);
            if (s!=null) {
                testclient.deregisterForData(s);
                pollPeriods.remove(s);
            }
            //deregisterButton.setEnabled(false);
            //registerButton.setEnabled(true);
            pollkey_list.setEnabled(true);
        } else if (e.getActionCommand().equals(NmsClientUtil.GetString("Register"))) {
            // register for the polled statistic selected
            registerButton.setVisible(false);
            deregisterButton.setVisible(true);
            deregisterButton.setEnabled(true);
            int numb = pollkey_list.getSelectedIndex();
            String s = null;
            if ( (numb>=0) && (numb<pollkeyData.size()) )
                s=(String)pollkeyData.elementAt(numb);
            if (s!=null)
                testclient.registerForData(s);


            //registerButton.setEnabled(false);
            //deregisterButton.setEnabled(true);

            pollkey_list.setEnabled(false);
        }
        else if(e.getActionCommand().equals(NmsClientUtil.GetString("Collected Statistics")))
        {
            getMultipleData();
            command_string = "Collected";//No Internationalisation
        }
        else if(e.getActionCommand().equals(NmsClientUtil.GetString("Current Statistics")))
        {
            getMultipleData();
            command_string = "Current";//No Internationalisation
        }
        else if(e.getActionCommand().equals(NmsClientUtil.GetString("View Reports")))
        {
            Properties pdProps = null;
            int numb = pollkey_list.getSelectedIndex();
            if ( (numb>=0) && (numb<agentPollData.size()) )
                pdProps=(Properties)agentPollData.elementAt(numb);

            if(pdProps!=null)
            {
                StringBuffer repUrl=new StringBuffer();
                repUrl.append(a.getCodeBase().toString()+"..");//No Internationalisation

                Object[] obj = NmsClientUtil.getWebURLParams("Reports");
                if(obj!=null)
                {
                    // Get report URL
                    Hashtable repAction = (Hashtable)obj[0];
                    String jurl = (String)repAction.get("URL");
                    repUrl.append(jurl);

                    // Get params to be appended in report URL
                    Hashtable repParams = null;
                    if(obj[1]!=null)
                    {
                        repParams = (Hashtable)obj[1];
                    }
                    if(repParams!=null)
                    {
                        StringBuffer bufParams = new StringBuffer("");
                        boolean firstTime=true;
                        for(Enumeration eparam=repParams.keys();eparam.hasMoreElements();)
                        {
                            String paramKey = (String)eparam.nextElement();
                            String valPlaceHolder = (String)repParams.get(paramKey);
                            String val = "";

                            if(valPlaceHolder.indexOf('$')>-1)
                            {
                                String paramVal = valPlaceHolder.substring(1);

                                if(paramVal.equalsIgnoreCase("jsessionid"))
                                {
                                    repUrl.append(";"+paramKey+"=");
                                    repUrl.append(NmsClientUtil.applet.getParameter(paramVal));
                                    continue;
                                }
                                else if(paramKey.equalsIgnoreCase("Language"))
                                {
                                    val = NmsClientUtil.language;
                                }
                                else if(paramKey.equalsIgnoreCase("Country"))
                                {
                                    val = NmsClientUtil.country;
                                }
                                else
                                {
                                    val = (String)pdProps.getProperty(paramVal);
                                }
                            }
                            else
                            {
                                val = valPlaceHolder;
                            }

                            if(val!=null)
                            {
                                // append param key
                                if(firstTime)
                                {
                                    bufParams.append("?");
                                    firstTime=false;
                                }
                                else
                                {
                                    bufParams.append("&");
                                }
                                bufParams.append(paramKey+"=");

                                //append param value
                                bufParams.append(URLEncoder.encode(val));
                            }
                        }
                        repUrl.append(bufParams.toString());
                    }
                    NmsClientUtil.showURLInNW(a,repUrl.toString());
                }
                else
                {
                    System.err.println(NmsClientUtil.GetString("Invalid report file"));
                }
            }
        }
        else if(e.getActionCommand().equals(NmsClientUtil.GetString("Help")))
        {
            NmsClientUtil.showHelpBasedOnKey("NWDB_Monitor_Collections");//No Internationalisation
            // NmsClientUtil.showHelp("../help/User_Guide/Java_UI/Maps/mapmenus.html"); //No Internationalisation
        }
        else if(e.getActionCommand().equals(NmsClientUtil.GetString("Close")))
        {
            deregisterForAgent();
            dispose();

        }
    }

    // put the polling period of the polled statistic so that you can
    // show it to the user
    void putPollPeriod(String key,int ii) {
	pollPeriods.put(key,String.valueOf(ii));
    }

    String getPollPeriod(String key) {
	String s="";//No Internationalisation
	if (key!=null)
	    s =(String) pollPeriods.get(key);
	if (s==null) s="";//No Internationalisation
	return s;
    }


    // When the server updates the client with the newly collected statistics
    // display the data on sreen
    void displayData(String key,String time,String value) {
	try {
	    statsText.setText(removeAgentFromKey(key));
	} catch (Exception anye1) {
	    statsText.setText(key);
	}
	try {
	    long l1 = Long.parseLong(time);
	    if (l1!=0) {
		Date d = new Date(l1);
		timeText.setText(d.toString());
	    } else
		timeText.setText("");//No Internationalisation
	} catch (Exception anye) {
	    timeText.setText(time);
	}
	try {
	    long l2 = Long.parseLong(value);
	    // Set the value o=in the digital display . This display is a
	    // component available in the AdventNet Management Builder .
	    valueField.setNumericValue(l2);
	} catch (Exception anye) {
	    valueField.setNumericValue(0);
	}
	periodText.setText(getPollPeriod(key));


    }


    /*
     * This is called immedaitely after the init method . When a user selects
     * the menu item then a new instance of this class is instantiated the init
     * method called and then this method is called with the boolean true
     */


    public void setVisible(boolean flag) {
	setSize(500,400);
	NmsClientUtil.centerWindow(this);
	super.setVisible(flag);
    }

    // when the window is closed deregiter for thw polls of this agent so
    // that you do not hold up any resources in the server .

    void deregisterForAgent() {
	testclient.removeFrame(agt);
    }

    /*
     * Listen to the list for change in selection . As of now nothing is done
     * when selection changes
     */

    public void valueChanged(ListSelectionEvent e)
    {

    }




    class MyWindowCloser extends WindowAdapter {
        public void windowClosing(WindowEvent e)
        {

            deregisterForAgent();
            Window win = e.getWindow();
            win.setVisible(false);
            win.dispose();
        }


    }

    public void setMultiple(String mul)
    {
        String keyer = "";    //No Internationalisation
        String title = "";    //No Internationalisation
        String agent = "";    //No Internationalisation
        String name = "";     //No Internationalisation
        String oid = "";      //No Internationalisation
        String ownerName =""; //No Internationalisation
        int numb = pollkey_list.getSelectedIndex();
		Properties pdProp = (Properties)agentPollData.elementAt(numb);
        String s = null;
        /* Vector to hold OIDs in case of multiple type PolledData. */
        Vector mulOid = null;
        /* PolledData type(false if String else true) */
        boolean dType = false;

        if ( (numb>=0) && (numb<pollkeyData.size()) )
            s=(String)pollkeyData.elementAt(numb);
        if (s!=null)
        {
            StringTokenizer str = new StringTokenizer(s);
            name = str.nextToken();
            agent = str.nextToken();
            oid = str.nextToken();
            try
            {
                ownerName = str.nextToken();
            }
            catch(Exception e)
            {
            }
            keyer = name + "__tab__" + agent + "__tab__" + oid;//No Internationalisation
            if(ownerName!=null)
            {
                if(!(ownerName.trim()).equals("") && (!ownerName.equalsIgnoreCase("NULL")))
                {
                    keyer=keyer+"__tab__"+ownerName;
                }
            }
            title = name + " " + agent + " " + oid;//No Internationalisation
        }
        if(command_string.equals("Collected"))//No Internationalisation
        {
            /* set busy cursor to ConfiguredCollection panel
             * WAIT_CURSOR 3 (as defined in java.awt.Cursor)
             */
            setCursorForPanel(this, 3);

            testclient.showCollectedGraph(a,keyer.trim(),title,mul,pdProp, chartType);

            /* set default cursor to ConfiguredCollection panel
             * DEFAULT_CURSOR 0 (as defined in java.awt.Cursor)
             */
            setCursorForPanel(this, 0);
        }
        else if(command_string.equals("Current"))//No Internationalisation
        {
			SnmpPoller poller = null;
			if(chartType != null)
			{
            	testclient.showCurrentGraph(keyer,title,poller,a,mulOid, pdProp, chartType);
				return;
			}
            /* set busy cursor to ConfiguredCollection panel
             * WAIT_CURSOR 3 (as defined in java.awt.Cursor)
             */
            setCursorForPanel(this, 3);

            poller = new SnmpPoller(NmsClientUtil.applet);

            // NetworkSegmentation
            String dnsName =((Properties)agentPollData.elementAt(numb)).getProperty("dnsName");
            if( dnsName!=null)
            {
                poller.setTargetHost(dnsName);
            }
            else
            {
                poller.setTargetHost(agent);
            }
            //            poller.setTargetHost(agent);

            poller.setObjectID(oid);
            poller.setPollInterval(15);
            int portNumber = Integer.parseInt(((Properties)agentPollData.elementAt(numb)).getProperty("port"));
            if(portNumber>0)
                poller.setTargetPort(portNumber);
            else
                poller.setTargetPort(161);
            String abSol = ((Properties)agentPollData.elementAt(numb)).getProperty("saveAbsolutes");
            if(abSol!=null && abSol.equals("true"))
            {
                poller.setAbsoluteCounters(true);
            }

            if(mul.equals("true"))//No Internationalisation
            {
                SnmpVarBind varbind = poller.snmpGetNextVariableBinding();
                if(varbind != null)
                {
                    dType = checkForPolledDataType(varbind); // check if String type PolledData
                    if(!dType)
                        return;
                    else
                    {
                        mulOid = new Vector();
                        mulOid.addElement(oid);
                        boolean oidTest = true;
                        String vartest = null;
                        int length = oid.length();
                        while (true)
                        {
                            vartest = varbind.getObjectID().toString();
                            int index = vartest.indexOf(oid);
                            if(index  == -1)
                                break;
                            poller.setObjectID(vartest);
                            mulOid.addElement(vartest.substring(index+length));
                            varbind = poller.snmpGetNextVariableBinding();
                        }
                        oid = oid+(String)mulOid.elementAt(1);
                        poller.setObjectID(oid);
                    }
                }
                else
                {
                    NmsClientUtil.normalCursor(this);
                    NmsClientUtil.err (NmsClientUtil.GetString ("Cannot get the Value for this Statistics Request Timed Out to "+ oid));
                    return;
                }
            }
            else
            {
                SnmpVarBind varbind = poller.snmpGetVariableBinding();
                if(varbind == null)
                {
                    NmsClientUtil.err (NmsClientUtil.GetString ("Cannot get the Value for this Statistics Request Timed Out to"+ oid));
                    NmsClientUtil.normalCursor(this);
                    return;
                }
                dType = checkForPolledDataType(varbind);
                if(!dType)
                    return;
            }
            testclient.showCurrentGraph(keyer,title,poller,a,mulOid, pdProp, chartType);

            /* set default cursor to ConfiguredCollection panel
             * DEFAULT_CURSOR 0 (as defined in java.awt.Cursor)
             */
            setCursorForPanel(this, 0);
        }
    }

    /* Added in SP8: support for Counter64/TimeTicks/Integer type PolledData */
    private boolean checkForPolledDataType(SnmpVarBind varbind)
    {
        SnmpVar var = varbind.getVariable();
        try
        {
            String Val = var.toString();
            long value = Long.parseLong(Val);
        }
        catch (NumberFormatException exc)
        {
            try
            {
                if(var.getType() == SnmpAPI.COUNTER64)
                {
                    return true;
                }
                else if(var.getType() == SnmpAPI.TIMETICKS)
                {
                    return true;
                }
                else
                {
                    NmsClientUtil.err(NmsClientUtil.GetString("Cannot plot Graph"
                                                              +" for String Values "));
                    NmsClientUtil.normalCursor(this);
                    return false;
                }
            }
            catch(NumberFormatException nfe)
            {
                NmsClientUtil.err(NmsClientUtil.GetString("Cannot plot Graph"
                                                          +" for String Values "));
                NmsClientUtil.normalCursor(this);
                return false;
            }
        }
        return true;
    }

    private void getMultipleData()
    {
        int numb = pollkey_list.getSelectedIndex();
        String s = null;
        if ( (numb>=0) && (numb<pollkeyData.size()) )
            s=(String)pollkeyData.elementAt(numb);
        if (s!=null)
            testclient.getMultiple(s);
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


