/* $Id: FetchJVMDetails.java,v 1.10.6.1 2014/02/12 09:13:50 venkatramanan Exp $ */

package com.adventnet.nms.webclient.management;

import java.util.Hashtable;
import java.util.Properties;
import java.util.Enumeration;
import java.util.StringTokenizer;
import com.adventnet.nms.poll.PollAPI;
import com.adventnet.nms.util.NmsUtil;
import org.apache.struts.action.Action;
import com.adventnet.nms.util.CommonUtil;
import org.apache.struts.action.ActionForm;
import com.adventnet.nms.poll.CollectedData;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import javax.servlet.http.HttpServletResponse;
import com.adventnet.nms.webclient.sysadmin.AdminStatusUtil;
import com.adventnet.nms.webclient.common.WebClientUtil;
import com.adventnet.nms.util.GenericUtility;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;



public class FetchJVMDetails extends Action
{
    PollAPI pollAPI = null;
    String memoryOID = "((((.1.3.6.1.4.1.42.2.145.3.163.1.1.2.11.0+.1.3.6.1.4.1.42.2.145.3.163.1.1.2.21.0))*100)/((.1.3.6.1.4.1.42.2.145.3.163.1.1.2.13.0+.1.3.6.1.4.1.42.2.145.3.163.1.1.2.23.0)))";//No I18N
    

    public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
    {    
	    String userName = (String)request.getSession().getAttribute(WebClientUtil.USER_KEY);

	    if (!GenericUtility.isAuthorized(userName,"Runtime Administration"))
	    {
		    ActionMessages actionMsg = new ActionMessages();
		    ActionMessage messageKey = new ActionMessage(NmsUtil.GetString("webclient.admin.authorizationcheck.warning"));
		    actionMsg.add("message",messageKey); //No Internationalization
		    request.setAttribute(Globals.MESSAGE_KEY, actionMsg);
		    return mapping.findForward("messagePage"); //No Internationalization
	    }

        Hashtable jvmHt = (Hashtable)AdminStatusUtil.getInstance().getResponseFromBE("GET_JVM_DETAILS");//No I18N                
        Properties bejvmProps = (Properties)jvmHt.get("BEServer");//No I18N
        Properties standByjvmProps = (Properties)jvmHt.get("StandByServer");//No I18N
        Hashtable feJVMDetails = (Hashtable)jvmHt.get("FEServer");//No I18N
        Hashtable clientJVMDetails = (Hashtable)jvmHt.get("WebNMSClients");//No I18N                                     
        pollAPI = (PollAPI)NmsUtil.getAPI("PollAPI");//No I18N

        setBEMemoryAndCPUDetails(bejvmProps);
        Hashtable modifiedFEJVMDetails = new Hashtable();        
        if(feJVMDetails != null)
        {
            modifiedFEJVMDetails = processFEJVMDetails(feJVMDetails);
        }
        Hashtable modifiedHt = processClientJVMDetails(clientJVMDetails, request);     
        //Hashtable modifiedClientJVMDetails = processClientJVMDetails(clientJVMDetails);
        //request.getSession().setAttribute("CLIENTJVMs",modifiedClientJVMDetails);//No I18N        
        request.getSession().setAttribute("ClientJVMs",modifiedHt);//No I18N
        request.getSession().setAttribute("FEJVMs",modifiedFEJVMDetails);//No I18N        
        request.getSession().setAttribute("BEJVM",bejvmProps);//No I18N
        request.getSession().setAttribute("STANDBY",standByjvmProps);//No I18N
        request.setAttribute("CurrentClientSessionId",jvmHt.get("CurrentClientSessionID"));//No I18N
        request.setAttribute("ClientTermination",request.getAttribute("ClientTermination"));//No I18N
	request.setAttribute("DetailsFile",request.getSession().getAttribute("DetailsFile"));//No I18N
	request.getSession().removeAttribute("DetailsFile");//No I18N
	request.setAttribute("ThreadFile",request.getSession().getAttribute("ThreadFile"));//No I18N
	request.getSession().removeAttribute("ThreadFile");//No I18N
	request.setAttribute("DetailsFilePath",request.getSession().getAttribute("DetailsFilePath"));//No I18N
	request.getSession().removeAttribute("DetailsFilePath");//No I18N
	request.setAttribute("ThreadFilePath",request.getSession().getAttribute("ThreadFilePath"));//No I18N
	request.getSession().removeAttribute("ThreadFilePath");//No I18N
	request.setAttribute("selectedJVMTab",request.getSession().getAttribute("selectedJVMTab"));//No I18N
	request.getSession().removeAttribute("selectedJVMTab");//No I18N
        return mapping.findForward("showDetails");//No I18N
    }


    private void setBEMemoryAndCPUDetails(Properties bejvmProps)
    {
        String hostAddr = bejvmProps.getProperty("HOSTADDRESS");//No I18N\
        String snmpPort = bejvmProps.getProperty("SNMP_Port");//No I18N
        long currentTime = System.currentTimeMillis();
        long startTime = currentTime-(1000*60*60);
        fetchBECurrentCPUUtilization(bejvmProps, hostAddr, snmpPort, startTime, currentTime);
        fetchBECurrentMemoryUtilization(bejvmProps, hostAddr, snmpPort, startTime, currentTime);
    }

    private void setFEMemoryAndCPUDetails(Properties jvmProps)
    {
        String hostAddr = jvmProps.getProperty("HOSTADDRESS");//No I18N
        String snmpPort = jvmProps.getProperty("FE_JVM_SNMP_PORT");//No I18N
        long currentTime = System.currentTimeMillis();
        long startTime = currentTime-(1000*60*60);
        fetchFECurrentCPUUtilization(jvmProps, hostAddr, snmpPort, startTime, currentTime);
        fetchFECurrentMemoryUtilization(jvmProps, hostAddr, snmpPort, startTime, currentTime);
    }

    private void setClientMemoryAndCPUDetails(Properties props)
    {
        String hostAddr = props.getProperty("HOSTADDRESS");//No I18N
        String snmpPort = props.getProperty("CLIENT_JVM_SNMP_PORT");//No I18N
        long currentTime = System.currentTimeMillis();
        long startTime = currentTime-(1000*60*60);

        fetchClientCurrentCPUUtilization(props, hostAddr, snmpPort, startTime, currentTime);
        fetchClientCurrentMemoryUtilization(props, hostAddr, snmpPort, startTime, currentTime);
    }


    public Hashtable processFEJVMDetails(Hashtable jvmDetails)
    {
        Hashtable tempHt = new Hashtable();
        int feJVMCounter = 1;
        for(Enumeration jvmEnum = jvmDetails.keys();jvmEnum.hasMoreElements();)
        {
            Integer feSessionID = (Integer)jvmEnum.nextElement();
            Properties jvmProps = (Properties)jvmDetails.get(feSessionID);
            jvmProps.setProperty("FESESSIONID",feSessionID.toString());//No I18N
            setFEMemoryAndCPUDetails(jvmProps);
            tempHt.put("FEServer "+feJVMCounter,jvmProps);//No I18N
            feJVMCounter++;
        }
        return tempHt;
    }

    public Hashtable processClientJVMDetails(Hashtable clientJVMDetails,HttpServletRequest request)
    {
        Hashtable tempHt = new Hashtable();

        for(Enumeration en1=clientJVMDetails.keys();en1.hasMoreElements(); )
        {
            String key = (String)en1.nextElement();//No I18N
            Properties props = (Properties)clientJVMDetails.get(key);//No I18N
            
            StringTokenizer st1 = new StringTokenizer(key,"*");//No I18N
            if(st1.hasMoreTokens())
            {
                String mssBEInstance = st1.nextToken();//No I18N
                String session1 = st1.nextToken();//No I18N
                String feSession = st1.nextToken();//No I18N            
                props.setProperty("BESESSION",session1);//No I18N
                props.setProperty("FESESSION",feSession);//No I18N
                props.setProperty("BESESSIONINSTANCE",mssBEInstance);//No I18N
                
                if(props.getProperty("CLIENT_TYPE").equals("WEBCLIENT"))
                {
                    String currentClientId = (String)request.getSession().getAttribute("CLIENTID");
                    String currentServerPort = (new Integer(request.getServerPort())).toString();
                    if((request.getServerName().equals(props.getProperty("FE_SERVER_NAME"))||request.getServerName().equals("localhost")||request.getServerName().equals(props.getProperty("IPAddress"))||request.getServerName().equals(props.getProperty("FE_SERVER_ADDRESS"))||request.getServerName().equals(props.getProperty("CLIENT_IPADDRESS"))) && currentServerPort.equals(props.getProperty("FE_SERVER_PORT")) && currentClientId.equals(props.getProperty("CLIENT_ID")))
                    {
                        props.setProperty("TerminateClientFeature","Disable");
                    }
                }

                else if(props.getProperty("CLIENT_TYPE").equals("APPLICATIONCLIENT"))//No I18N
                {
                    setClientMemoryAndCPUDetails(props);
                }
                tempHt.put(session1+"_"+props.getProperty("FE_SERVER_PORT")+"_"+feSession,props);//No I18N
            }
        }
        return tempHt;
    }

    private void fetchBECurrentCPUUtilization(Properties bejvmProps, String hostAddr, String snmpPort, long startTime, long currentTime)
    {
        try
        {          
            String cpuOID = getCPUOID(bejvmProps.getProperty("OSName"));//No I18N
            String pdKey = "JVMPD_BE_"+snmpPort+"_MonitorCPU\tBE_"+hostAddr+"\t"+cpuOID; //No I18N
            CollectedData collVec = pollAPI.getCollectedValues(pdKey,startTime,currentTime);//No I18N
            if(collVec != null)
            {
                Long[] rate = (Long[])collVec.getValues();
                bejvmProps.setProperty("CPUUTIL",(rate[rate.length-1]).toString()+"%");//No I18N
            }
            else
            {
                bejvmProps.setProperty("CPUUTIL","NA");//No I18N
            }
        }
        catch(com.adventnet.management.transaction.UserTransactionException utExcep)
        {
            bejvmProps.setProperty("CPUUTIL","NA");//No I18N
            utExcep.printStackTrace();
        }
        catch(com.adventnet.nms.poll.NmsPollException excep)
        {
            bejvmProps.setProperty("CPUUTIL","NA");//No I18N
            excep.printStackTrace();
        }
        catch(java.rmi.RemoteException excep)
        {
            bejvmProps.setProperty("CPUUTIL","NA");//No I18N
            excep.printStackTrace();
        }
    }

    private void fetchBECurrentMemoryUtilization(Properties bejvmProps, String hostAddr, String snmpPort, long startTime, long currentTime)
    {
        try
        {
            String pdKey = "JVMPD_BE_"+snmpPort+"_MonitorMemory\tBE_"+hostAddr+"\t"+memoryOID; //No I18N
            CollectedData collVec = pollAPI.getCollectedValues(pdKey,startTime,currentTime);//No I18N
            if(collVec != null)
            {
                Long[] rate = (Long[])collVec.getValues();
                bejvmProps.setProperty("MEMUTIL",(rate[rate.length-1]).toString()+"%");//No I18N
            }
            else
            {
                bejvmProps.setProperty("MEMUTIL","NA");//No I18N
            }            
        }
        catch(com.adventnet.management.transaction.UserTransactionException utExcep)
        {
            bejvmProps.setProperty("MEMUTIL","NA");//No I18N
            utExcep.printStackTrace();
        }
        catch(com.adventnet.nms.poll.NmsPollException excep)
        {
            bejvmProps.setProperty("MEMUTIL","NA");//No I18N
            excep.printStackTrace();
        }
        catch(java.rmi.RemoteException excep)
        {
            bejvmProps.setProperty("MEMUTIL","NA");//No I18N
            excep.printStackTrace();
        }        
    }                       

    private void fetchFECurrentCPUUtilization(Properties jvmProps, String hostAddr, String snmpPort, long startTime, long currentTime)
    {
        try
        {  
            String cpuOID = getCPUOID(jvmProps.getProperty("OSName"));//No I18N
            String pdKey = "JVMPD_FE_"+snmpPort+"_MonitorCPU\tFE_"+hostAddr+"\t"+cpuOID; //No I18N
            CollectedData collVec = pollAPI.getCollectedValues(pdKey,startTime,currentTime);//No I18N
            if(collVec != null)
            {
                Long[] rate = (Long[])collVec.getValues();
                jvmProps.setProperty("CPUUTIL",(rate[rate.length-1]).toString()+"%");//No I18N
            }
            else
            {
                jvmProps.setProperty("CPUUTIL","NA");//No I18N
            }
        }
        catch(com.adventnet.management.transaction.UserTransactionException utExcep)
        {
            jvmProps.setProperty("CPUUTIL","NA");//No I18N
            utExcep.printStackTrace();
        }
        catch(com.adventnet.nms.poll.NmsPollException excep)
        {
            jvmProps.setProperty("CPUUTIL","NA");//No I18N
            excep.printStackTrace();
        }
        catch(java.rmi.RemoteException excep)
        {
            jvmProps.setProperty("CPUUTIL","NA");//No I18N
            excep.printStackTrace();
        }
    }


    private void fetchFECurrentMemoryUtilization(Properties jvmProps, String hostAddr, String snmpPort, long startTime, long currentTime)
    {
        try
        {
            String pdKey = "JVMPD_FE_"+snmpPort+"_MonitorMemory\tFE_"+hostAddr+"\t"+memoryOID; //No I18N
            CollectedData collVec = pollAPI.getCollectedValues(pdKey,startTime,currentTime);//No I18N
            if(collVec != null)
            {
                Long[] rate = (Long[])collVec.getValues();
                jvmProps.setProperty("MEMUTIL",(rate[rate.length-1]).toString()+"%");//No I18N
            }
            else
            {
                jvmProps.setProperty("MEMUTIL","NA");//No I18N
            }            
        }
        catch(com.adventnet.management.transaction.UserTransactionException utExcep)
        {
            jvmProps.setProperty("MEMUTIL","NA");//No I18N
            utExcep.printStackTrace();
        }
        catch(com.adventnet.nms.poll.NmsPollException excep)
        {
            jvmProps.setProperty("MEMUTIL","NA");//No I18N
            excep.printStackTrace();
        }
        catch(java.rmi.RemoteException excep)
        {
            jvmProps.setProperty("MEMUTIL","NA");//No I18N
            excep.printStackTrace();
        }        
    }

    private void fetchClientCurrentCPUUtilization(Properties props, String hostAddr, String snmpPort, long startTime, long currentTime)
    {
        try
        {  
            String cpuOID = getCPUOID(props.getProperty("OSName"));//No I18N
            String pdKey = "JVMPD_CLIENT_"+snmpPort+"_MonitorCPU\tClient_"+hostAddr+"\t"+cpuOID; //No I18N
            CollectedData collVec = pollAPI.getCollectedValues(pdKey,startTime,currentTime);//No I18N
            if(collVec != null)
            {
                Long[] rate = (Long[])collVec.getValues();
                props.setProperty("CPUUTIL",(rate[rate.length-1]).toString()+"%");//No I18N
            }
            else
            {
                props.setProperty("CPUUTIL","NA");//No I18N
            }
        }
        catch(com.adventnet.management.transaction.UserTransactionException utExcep)
        {
            props.setProperty("CPUUTIL","NA");//No I18N
            utExcep.printStackTrace();
        }
        catch(com.adventnet.nms.poll.NmsPollException excep)
        {
            props.setProperty("CPUUTIL","NA");//No I18N
            excep.printStackTrace();
        }
        catch(java.rmi.RemoteException excep)
        {
            props.setProperty("CPUUTIL","NA");//No I18N
            excep.printStackTrace();
        }
    }


    private void fetchClientCurrentMemoryUtilization(Properties props, String hostAddr, String snmpPort, long startTime, long currentTime)
    {
        try
        {
            String pdKey = "JVMPD_CLIENT_"+snmpPort+"_MonitorMemory\tClient_"+hostAddr+"\t"+memoryOID; //No I18N
            CollectedData collVec = pollAPI.getCollectedValues(pdKey,startTime,currentTime);//No I18N
            if(collVec != null)
            {
                Long[] rate = (Long[])collVec.getValues();
                props.setProperty("MEMUTIL",(rate[rate.length-1]).toString()+"%");//No I18N
            }
            else
            {
                props.setProperty("MEMUTIL","NA");//No I18N
            }            
        }
        catch(com.adventnet.management.transaction.UserTransactionException utExcep)
        {
            props.setProperty("MEMUTIL","NA");//No I18N
            utExcep.printStackTrace();
        }
        catch(com.adventnet.nms.poll.NmsPollException excep)
        {
            props.setProperty("MEMUTIL","NA");//No I18N
            excep.printStackTrace();
        }
        catch(java.rmi.RemoteException excep)
        {
            props.setProperty("MEMUTIL","NA");//No I18N
            excep.printStackTrace();
        }        
    }



    /*public Hashtable processClientJVMDetails(Hashtable clientJVMDetails)
    {
        Hashtable tempHt = new Hashtable();
        for(Enumeration clientJVMEnum=clientJVMDetails.keys();clientJVMEnum.hasMoreElements(); )
        {
            String key = (String)clientJVMEnum.nextElement();//No I18N
            Properties props = (Properties)CommonUtil.deSerializeObject((byte[])clientJVMDetails.get(key));   //No I18N         
            StringTokenizer st1 = new StringTokenizer(key,"*");//No I18N
            if(st1.hasMoreTokens())
            {
                String mssBEInstance = st1.nextToken();//No I18N
                String beSession = st1.nextToken();//No I18N
                String feSession = st1.nextToken();     //No I18N       
                props.setProperty("BESESSION",beSession);//No I18N
                props.setProperty("FESESSION",feSession);//No I18N
                props.setProperty("BESESSIONINSTANCE",mssBEInstance);//No I18N
                String serverDetails = props.getProperty("FE_SERVER_NAME")+"_"+props.getProperty("FE_SERVER_PORT")+"_"+beSession+"_"+feSession;    //No I18N            
                tempHt.put(serverDetails,props);//No I18N
            }                       
        }
        return tempHt;
        }*/

    private String getCPUOID(String osName)
    {      
        String cpuOID = null;
        if(osName.startsWith("Win"))//No I18N
        {
            cpuOID = ".1.3.6.1.2.1.25.3.3.1.2";//No I18N
        }
        else
        {
            cpuOID = ".1.3.6.1.4.1.2021.10.1.5";//No I18N
        }
        return cpuOID;
    }
}
