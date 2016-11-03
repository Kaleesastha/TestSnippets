/* $Id: AddNetwork.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $ ## 127 ## */

package com.adventnet.nms.jsp;

import java.util.Properties;
import java.util.Enumeration;
import java.util.StringTokenizer;

import com.adventnet.nms.fe.topo.TopoFE;
import com.adventnet.nms.fe.topo.TopoSessionBean;
import com.adventnet.nms.util.NmsLogMgr;
import com.adventnet.nms.util.GenericUtility;
import com.adventnet.nms.fe.common.ModuleNotInitializedException;

public class AddNetwork
{
	private Properties prop;
	private String toReturn = "Request could not be processed successfully.Please refer to logs for more details.";
	boolean configflag = false;
	boolean managedFlag = false;
	private static TopoSessionBean topoSession = null;
	private Properties objectProperties = null;
	private String userName = "";

	public AddNetwork(){
	}

	/**
	 * This method is used to add the Networks
	 *
	 * @param prop The properties of the network being added
	 * @return a java.lang.String object stating the Status of addition of network
	 *
	 **/
	public String addNetwork(Properties pr) throws ModuleNotInitializedException
	{
		userName = (String)pr.remove("userName");
                Properties p = new Properties();
                String temp = pr.getProperty("NetworkAddress");
                if(temp != null && temp.trim().length() > 0)
                    {
                        p.setProperty("auditedObj", temp);
                    }
                p.setProperty("category", "Topo");
		if( userName == null || userName.trim().equals("") || userName.equals("null") || ! GenericUtility.isAuthorized(userName, "Add Network", p) ){
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
		String name = pr.getProperty("NetworkAddress");
		if(name==null || name.equals(""))return "";
		objectProperties.setProperty("name",name);

		if(pr.getProperty("Add Overriding SeedFile Configuration") !=null)
			configflag = true;
         /* This property is added to write the node entries into
          *  seedfile (Forgent)*/
        if(pr.getProperty("writeToSeedFile") != null){
            objectProperties.setProperty("writeToSeedFile","true");
		}

		StringTokenizer st = new StringTokenizer(name, ".");

		if ((name==null) || name.trim().equals("") || name.trim().equals("0.0.0.0") ||(st.countTokens() != 4))
		{

			toReturn = "Invalid Address";
			return toReturn;
		}

		objectProperties.setProperty("ipAddress",com.adventnet.nms.util.WatchUtil.getIP(name));
		objectProperties.setProperty("netmask",pr.getProperty("NetMask"));

		if ( pr.getProperty("discover") != null ) {
			objectProperties.setProperty("discover","true");
		}
		else {
			objectProperties.setProperty("discover","false");
		}

		if (pr.getProperty("managed") != null){
			objectProperties.setProperty("managed","true");
		}
		else {
			objectProperties.setProperty("managed","false");
		}

		objectProperties.setProperty("statusPollEnabled","false");

		objectProperties.setProperty("type","Network");
		try
		{
			if (pr.getProperty("Return Immediately After Submitting Request") == null)
			{
				toReturn=addNetwork();
			}
			else
			{
				Thread addNetworkThread = new Thread(){
					public void run()
					{
						toReturn= addNetwork();
					}
				};
				addNetworkThread.start();
				toReturn = "Request is being processed. You could see events getting generated once the network gets added into the database.";
			}
		}
		catch (Exception e)
		{
			NmsLogMgr.MISCERR.fail("Exception caught in AddNetwork: " ,e);            
			return e.getMessage();
		}
		return toReturn;
	}
	private String addNetwork()
	{
		try
		{
			toReturn = topoSession.addObjectProperties(userName,objectProperties,configflag,false,false);
		}
		catch (Exception e)
		{
			NmsLogMgr.MISCERR.fail("Exception caught in AddNetwork.addNetwork :  " + e.getMessage(),e);
			toReturn = "Exception in AddNetwork. Refer logs for details";
			return toReturn;
		}
		return toReturn;
	}
}
