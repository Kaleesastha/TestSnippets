/* $Id: AddNode.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $ */

package com.adventnet.nms.jsp;

import java.util.Properties;
import java.util.Enumeration;

import com.adventnet.nms.fe.topo.TopoFE;
import com.adventnet.nms.fe.topo.TopoSessionBean;
import com.adventnet.nms.util.NmsLogMgr;
import com.adventnet.nms.util.GenericUtility;
import com.adventnet.nms.fe.common.ModuleNotInitializedException;

public class AddNode
{
        
	private String toReturn="Request could not be processed successfully.Please refer to logs for more details.";
	boolean configflag=false;
	boolean reachflag =false;
	boolean discoverParentNet=false;
	private String userName ="";
	private Properties objectProperties = null;
	private static TopoSessionBean topoSession = null;

	public AddNode(){
	}

	/**
	* Used to add a node
	*
	* @param pr The properties of the newly added node
	* @return a java.lang.String object stating that status of the Node being added 
	*
	*/
	public  String addNode(Properties pr) throws ModuleNotInitializedException
	{ 
		userName = pr.getProperty("userName");
                Properties p = new Properties();
                String temp = pr.getProperty("Node");
                if(temp != null && temp.trim().length() > 0)
                    {
                        p.setProperty("auditedObj", temp);
                    }
                p.setProperty("category", "Topo");

		if( userName == null || userName.trim().equals("") || userName.equals("null") || ! GenericUtility.isAuthorized(userName,"Add Node",p) ){
			return GenericUtility.unAuthorizedString;
		}
		if ( topoSession == null ){
			try{
				topoSession = TopoFE.getTopoSession();
			}catch(ModuleNotInitializedException e){
				NmsLogMgr.MISCERR.fail("Exception while reference to TopoSessionBean",e);
				throw e;
			}
		}
		objectProperties = new Properties();
		String name = pr.getProperty("Node");
		if ( (name == null) || name.trim().equals("") || name.trim().equals("0.0.0.0") )
		{
			toReturn = "Please enter a valid Host Name /IP Address";//No I18N
			return toReturn;
		}
		objectProperties.setProperty("name",com.adventnet.nms.util.WatchUtil.getDNSName(name));

		String ipAddress = com.adventnet.nms.util.WatchUtil.getIP(name);
		if (com.adventnet.nms.util.WatchUtil.getAddrArray(ipAddress.trim()) == null)
		{
			toReturn = "The Node's IP address is unavailable.";
			return toReturn;
		}
		if ( (ipAddress == null) || ipAddress.trim().equals("") || ipAddress.equals("0.0.0.0"))
		{
			toReturn = "Invalid IP address";
			return toReturn;
		}
		objectProperties.setProperty("ipAddress",ipAddress);

		String netmask = pr.getProperty("NetMask");
		if( netmask != null ){
			objectProperties.setProperty("netmask", netmask);
		}
        String community = pr.getProperty("community");
		
        if (community!=null)
        {
            objectProperties.setProperty("community",community);  					
        }
		String version = pr.getProperty("snmpVersion");
		if (version != null){
			objectProperties.setProperty("version", version);
			if(version.equals("v3")){
				String contextName = pr.getProperty("contextName");
				String user = pr.getProperty("UserName");
				if (contextName != null) {
					objectProperties.setProperty("contextName", contextName);
				}
				if (user != null) {
				objectProperties.setProperty("userName", user);
				}
			}
		}
        if(pr.getProperty("Add Overriding SeedFile Configuration") !=null)
			configflag = true;
		if(pr.getProperty("Add Even If Node Not Reachable") !=null)
			reachflag =true;
		if(pr.getProperty("discoverParentNet") != null){
			discoverParentNet=true;
		}
        /* This property is added to write the node entries into
         *  seedfile (Forgent)*/
		if(pr.getProperty("writeToSeedFile") != null){
            objectProperties.setProperty("writeToSeedFile","true");
		}
		String snmpport = pr.getProperty("SnmpAgentPort");
 		if( snmpport != null){
			objectProperties.setProperty("snmpport",snmpport);
		}
        String isSNMPStr = pr.getProperty("isSNMP");
		if ( isSNMPStr != null){
			objectProperties.setProperty("isSNMP",isSNMPStr);
		}
		String ipType = pr.getProperty("IPType");
                if(ipType != null)
                    {
                        objectProperties.setProperty("IPType",ipType);
                    }
                objectProperties.setProperty("type","Node");
		try
		{
			if (pr.getProperty("Return Immediately After Submitting Request") == null) {
				toReturn = topoSession.addObjectProperties(userName,objectProperties,configflag,discoverParentNet,reachflag);
			}
			else
			{
				Thread addNodeThread = new Thread() {
					public void run()
					{
						try
						{
							toReturn = topoSession.addObjectProperties(userName,objectProperties,configflag,discoverParentNet,reachflag);
						}
						catch(Exception rex)
						{
							NmsLogMgr.MISCERR.fail("Exception caught in AddNode Thread : " + rex.getMessage(),rex);
						}
					}
				};
				addNodeThread.start();
				toReturn = "Request is being processed. You could see events getting generated once the node gets added into the database.";
			}

		} 
		catch (Exception e)
		{
			NmsLogMgr.MISCERR.fail("Exception caught in AddNode : ",e);            
			return e.getMessage();
		}
		return toReturn;
	}
}
