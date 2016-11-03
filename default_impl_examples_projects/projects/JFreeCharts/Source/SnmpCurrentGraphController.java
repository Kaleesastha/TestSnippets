/* $Id: SnmpCurrentGraphController.java,v 1.4.6.1 2012/04/05 08:30:26 wesley Exp $*/
package com.adventnet.nms.poll.graphs;

// import java classes
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.math.BigInteger;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;
import java.net.InetAddress;

// import AdventNet classes
import com.adventnet.nms.startclient.NmsPanel;
import com.adventnet.nms.startclient.NmsMainApplet;
import com.adventnet.nms.util.NmsClientUtil;
import com.adventnet.nms.util.NmsUiAPI;
import com.adventnet.nms.pollui.StatsAdminPanel;

// import SnmpAPI classes
import com.adventnet.snmp.beans.SnmpPoller;
import com.adventnet.snmp.beans.ResultEvent;
import com.adventnet.snmp.beans.ResultListener;
import com.adventnet.snmp.mibs.MibOperations;
import com.adventnet.snmp.snmp2.SnmpOID;
import com.adventnet.snmp.snmp2.SnmpAPI;
import com.adventnet.snmp.snmp2.SnmpPDU;
import com.adventnet.snmp.snmp2.SnmpVar;
import com.adventnet.snmp.snmp2.SnmpVarBind;
import com.adventnet.snmp.snmp2.SnmpCounter64;
import com.adventnet.snmp.snmp2.SnmpTimeticks;
import com.adventnet.snmp.snmp2.SASProtocolOptions;

// THEME-II Start
public class SnmpCurrentGraphController implements ResultListener, ActionListener, CurrentGraphController 
						   // THEME-II End
{
    Properties selectedPD = new Properties();
    Vector instanceVector = new Vector();
    int POLL_FREQ = 15;
    String pdkey      = "";

    private long negatelastBit = 0X7FFFFFFFFFFFFFFFL;

    /* Maximum value for Counter 64 type OID */
    private double maxC64Value = 18446744073709551615d;

    /* setResult method will be called even while getting the instances of 
       PolledData but we should not plot this point and should start plotting 
       only after we register as poller observer. This variable takes care of 
       this condition.
    */
    boolean firstPlotValue = false;

    /* Holds the previous value for Counter type OID's to calculate the diff 
       (value) to be plotted when saveAbsolutes is false
     */
    Hashtable prevValueHashtable = new Hashtable();

    SnmpPoller poller = null;
    CurrentGraphViewer viewer = null;
  
    public SnmpCurrentGraphController(Properties selectedPD)
    {
        this.selectedPD = selectedPD;
        registerWithPollClient();        
        initializeViewer();
        initializePoller();
        boolean canPlot = checkIfActive();
        
        if (canPlot)
        {
            boolean agentUP = getInstancesForPD();
            
            if (agentUP)
            {
                poller.setPollInterval(POLL_FREQ);
                poller.addResultListener(this);
                // THEME-II Start
                poller.run();        
                // THEME-II End
                firstPlotValue = true;
            }
            else
            {
                poller.removeResultListener(this);
            }
        }
        viewer.setUIVisible();
    }

    private void registerWithPollClient()
    {
        NmsPanel panel = NmsUiAPI.getNmsPanelInstance("StatsAdminPanel");        
        if(panel != null)
        {
            ((StatsAdminPanel)panel).addListener(this);
        }
    }

    public void initializeViewer()
    {
        viewer = new CurrentGraphViewer();
        viewer.setController(this);
        viewer.setPollPeriod(POLL_FREQ);
        Properties props = new Properties();
        String title = selectedPD.getProperty("agent");
        String subTitle = selectedPD.getProperty("name");

        if (selectedPD.getProperty("isMultiplePolledData").equals("true"))
        {
            viewer.setIsMultiplePolledData(true);
        }
        
        props.put("title",title);
        props.put("subTitle",subTitle);
        props.put("xLabel","Time of Collection");
        String yAxisLabel = selectedPD.getProperty("yAxisLabel");
        
        if(yAxisLabel != null)
        {
            props.put("yLabel",yAxisLabel);
        }
        else
        {
            props.put("yLabel",selectedPD.getProperty("oid"));
        }
        viewer.setChartProperties(props);
        viewer.setDataNA("Fetching Data....");
    }

    public void initializePoller()
    {
        String name      = selectedPD.getProperty("name"); 
        String oid       = selectedPD.getProperty ("oid");
        String agent     = selectedPD.getProperty ("agent");
        String ownerName = selectedPD.getProperty("ownerName");
        String dnsName   = selectedPD.getProperty ("dnsName");
        String abSol     = selectedPD.getProperty("saveAbsolutes");
        String port      = selectedPD.getProperty("port");
        String community = selectedPD.getProperty ("community");
        pdkey = name+"\t"+agent+"\t"+oid;

        if (ownerName!=null)
        {
            if(!ownerName.equals("") && (!ownerName.equalsIgnoreCase("NULL")))
            {
                pdkey=pdkey+"\t"+ownerName;
            }
        }
        selectedPD.setProperty("pdkey",pdkey);
	String snmpVersion = selectedPD.getProperty("snmpVersion");
        // To set the virtaulIP address for SASProtocolOptions
        SASProtocolOptions saspro = new SASProtocolOptions();
        String localip = (String)NmsClientUtil.applet.getDocumentBase().getHost(); // getting the IPaddress of the server
        try
        {
            InetAddress addr = InetAddress.getByName(localip);
            localip = addr.getHostAddress().trim();
        }
        catch(Exception e)
        {
            System.err.println (NmsClientUtil.GetString ("Exception while getting the IPAddress") + e);
        }

        saspro.setLocalAddresses(new String[] {localip});
        saspro.setApplet(NmsClientUtil.applet);
        poller = new SnmpPoller(1,saspro);

        //poller = new SnmpPoller(NmsClientUtil.applet);
        poller.setTargetHost(dnsName);
        poller.setObjectID(oid);
        poller.setTargetPort(Integer.parseInt(port));
        poller.setCommunity(community);
	poller.setSnmpVersion(SnmpPoller.VERSION1);
        if(snmpVersion =="v2" || snmpVersion.equals("v2"))
	{
		poller.setSnmpVersion(SnmpPoller.VERSION2C );
	}
	else if(snmpVersion == "v3" || snmpVersion.equals("v3"))
	{
		String v3UserName = selectedPD.getProperty("userName");
		String contextName = selectedPD.getProperty("contextName");
		poller.setV3DatabaseFlag(true);
		poller.setSnmpVersion(SnmpPoller.VERSION3);
		poller.setPrincipal(v3UserName);
		poller.setContextName(contextName);
	}

        if(abSol!=null && abSol.equals("true"))
        {
            poller.setAbsoluteCounters(true);  
        }
        String timeOut = ((NmsMainApplet)NmsClientUtil.applet).getParameter ("snmp_timeout");
        if(timeOut != null)
        {
            int to =(Integer.valueOf(timeOut.trim())).intValue();
            try
            {
                poller.setTimeout (to);
            }
            catch (NumberFormatException ne)
            {
                System.err.println (NmsClientUtil.GetString ("Invalid Snmp Timeout Parameter:") + timeOut);
            }
        }
    }

    public boolean checkIfActive()
    {
        String currstat = NmsClientUtil.applet.getParameter("CURR-STAT");
        
        if ((selectedPD.getProperty("active").equalsIgnoreCase("false"))
                && (currstat==null || !currstat.equals("true")))
        {
            viewer.disableViewer("PolledData Inactive");
            return false;
        }
        return true;
    }

    public boolean getInstancesForPD()
    {
        String oid = selectedPD.getProperty("oid");
       	MibOperations  mibOps=poller.getMibOperations();
	SnmpOID soid=mibOps.getSnmpOID(oid);        
	if(soid!=null)
	{
		oid=soid.toString();
	} 
        if (selectedPD.getProperty("isMultiplePolledData").equals("true"))
        { 
            SnmpVarBind varbind = poller.snmpGetNextVariableBinding();
            
            if (varbind == null)
            {
                viewer.disableViewer("No Response from agent");
                return false;
            }
            
            boolean isSupported = checkPDType(varbind);
            
            if (!isSupported)
            {
                viewer.disableStringTypeFields("Cannot plot [Data is String type]");
            }
            else
            {  
                instanceVector = new Vector();
                String vartest = null;
                while (true)  
                {
                    vartest = varbind.getObjectID().toString();
                    int index = vartest.indexOf(oid);
                    if(index  == -1)
                        break;
                    instanceVector.addElement(vartest); 
                    poller.setObjectID(vartest);
                    varbind = poller.snmpGetNextVariableBinding();
                }
                String oid_list [] = new String[instanceVector.size()];
                for(int i=0;i<instanceVector.size();i++)
                {
                     oid_list[i] = (String)instanceVector.get(i);
                }
                poller.setObjectIDList(oid_list);
                String [] result = poller.getObjectIDList();
            }
        }
        else
        {
            SnmpVarBind varbind = poller.snmpGetVariableBinding();
            if(varbind == null)
            {
                viewer.disableViewer("No Response from agent");
                return false;
            }       
            
            boolean isSupported = checkPDType(varbind);
            if(!isSupported)
            {
                viewer.disableStringTypeFields("Cannot plot [Data is String type]");
            }
        }
        return true;
    }

    private boolean checkPDType(SnmpVarBind varbind)
    {
        SnmpVar var = varbind.getVariable();
        String Val = var.toString();
        try 
        {
            // captures all types except String/COUNTER64/TIMETICKS
            long value = Long.parseLong(Val);
        }
        catch (NumberFormatException exc) 
        {
        	try{
        		double value = Double.parseDouble(Val);
        	}
        	catch(NumberFormatException exce)
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
                return false;
            }
        	}
        }
        return true;
    }

    // THEME-II Start
    /*
    public void run() 
    {
        poller.run();        
    }
    */
    // THEME-II End

    public String getObjectID()
    {
        return poller.getObjectID();
    }

    private double findCounterDiff(Long currentValue,Long prevValue)
    {
        double cValue = currentValue.doubleValue();
        double pValue = prevValue.doubleValue();
        double counterdiff = cValue - pValue;

        if (counterdiff < 0)
        {
            counterdiff = (cValue + 0xFFFFFFFFL - pValue)+1;
        }

        if (counterdiff < 0 || counterdiff > 0xFFFFFFFFL) 
            counterdiff = 0;
        
        return counterdiff;
    }

    private double findCounter64Diff(BigInteger currentValue,BigInteger prevValue)
    {
        double counter64diff = (currentValue.subtract(prevValue)).doubleValue();

        if (counter64diff < 0)
        {
            BigInteger temp = currentValue.add(getMax(negatelastBit));
            counter64diff = ((temp.subtract(prevValue)).doubleValue())+1;
        }

        if (counter64diff < 0 || counter64diff > maxC64Value) 
            counter64diff = 0;
        
        return counter64diff;
    }

    private BigInteger getMax(long bit)
    {
    	BigInteger bg1 = BigInteger.valueOf(bit);
        BigInteger bg2 = BigInteger.valueOf(1).shiftLeft(63);
        BigInteger maxbg = bg1.or(bg2);
        return maxbg;
    }
    
    public void setResult(ResultEvent resevt)
    {
        Hashtable currvals = new Hashtable();
        
        SnmpPDU spdu =(SnmpPDU)resevt.getResponse();
        
        if(spdu ==null)
            return;	
        Vector binds = spdu.getVariableBindings();
        
        if (binds == null) 
        {
            viewer.disableViewer("No Response from agent");
            return;
        }
        
        String oid = selectedPD.getProperty("oid");
	MibOperations  mibOps=poller.getMibOperations();
	SnmpOID soid=mibOps.getSnmpOID(oid);        
	if(soid!=null)
	{
		oid=soid.toString();
	}
        int length = oid.length();

        for (Enumeration e = binds.elements(); e.hasMoreElements();)
        {
            SnmpVarBind bind=(SnmpVarBind)e.nextElement();
            
            if (bind == null)
            {
                viewer.disableViewer("No Response from agent");
                return;
            }
            
            String fulloid = bind.getObjectID().toString();
            int index = fulloid.indexOf(oid);
            
            String instance = fulloid.substring(index+length);
            
            if (instance==null || instance.trim().equals(""))
            	instance = fulloid;
            double res = 0;
            SnmpVar var = bind.getVariable();

            if(var == null || (var.getType() == SnmpAPI.NULLOBJ))
            {
                viewer.disableViewer("No Response from agent");
                return;
            }

            if(var.getType() == SnmpAPI.INTEGER)
            {
                Double Val = new Double(((Integer)var.toValue()).doubleValue());
                res = Val.doubleValue();
            }
            else if (var.getType() == SnmpAPI.COUNTER)
            {
                Long currentValue = (Long)var.toValue();
                
                if(!(poller.getAbsoluteCounters()))
                {
                    Object pValue = prevValueHashtable.get(instance);
                    if(pValue != null)
                    {
                        Long prevValue = (Long)pValue;
                        if(prevValue != currentValue)
                        {
                            res = findCounterDiff(currentValue,prevValue);
                        }
                        else
                        {
                            res = 0;
                        }
                    }
                    prevValueHashtable.put(instance,currentValue);
                }
                else
                {
                    res=currentValue.doubleValue();     
                }
            }
            else if(var.getType()== SnmpAPI.COUNTER64)
            {
                BigInteger currentValue = ((SnmpCounter64)var).toBigInteger();		 

                if(!(poller.getAbsoluteCounters()))
                {
                    Object pValue = prevValueHashtable.get(instance);
                    if(pValue != null)
                    {
                        BigInteger prevValue = (BigInteger)pValue;
                        if(prevValue != currentValue)
                        {
                            res = findCounter64Diff(currentValue,prevValue);
                        }
                        else
                        {
                            res = 0;
                        }
                    }
                    prevValueHashtable.put(instance,currentValue);
                }
                else
                {
                    res=currentValue.doubleValue();     
                } 	
            }
            else if(var.getType()== SnmpAPI.TIMETICKS)
            {   
                res = (double)(((SnmpTimeticks)var).longValue());		
            }
            else
            {
                //else try a long type
                try  
                { 
                    res = ((Long)var.toValue()).doubleValue();
                } 
                catch (ClassCastException cce) 
                {
                    currvals.put(instance,var.toString());
                    continue;
                }
            }
            currvals.put(instance,new Double(res));
        } 

        if (firstPlotValue && currvals!=null && currvals.size()>0)
        {          
            plotData(currvals);
        }
    }

    public void setNumericResult(long e)
    {
    }

    public void setStringResult(String s)
    {
    }

    public void stopPoll()
    {
	poller.setAutoActive(false);
        poller.stopPolling();
    }

    public void restartPoll()
    { 
        poller.restartPolling();
    }
    
    public void setPollInterval(int period)
    {
         poller.setPollInterval(period);
    }


    public void close()
    {
        viewer.dispose();
        poller.stopPolling();
        poller.removeResultListener(this);

        NmsPanel panel=NmsUiAPI.getNmsPanelInstance("StatsAdminPanel");        
        if(panel!=null)
        {
            ((StatsAdminPanel)panel).removeListener(this);
        }
    }

    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();
        StringTokenizer to=new StringTokenizer(command,":");
        String k=to.nextToken();
        String active=to.nextToken();
        if(k.equals(pdkey))
        {
            if(active.equals("false"))
                stopPoll();
            else
                restartPoll();
        }
    }

    public void plotData(Hashtable vals)
    {
        viewer.plotData(vals);
    }
}









