//$Id: TopoTrigger.java,v 1.2 2010/10/29 13:46:04 swaminathap Exp $
package com.adventnet.nms.provisioning.server;
import com.adventnet.nms.provisioning.*;
import com.adventnet.nms.topodb.*;
import com.adventnet.nms.util.*;
/**
 * This class provides a default implementation for handling Topo notifications.<br>
 * This obtains instance of TopoAPI.
 * This creates an instance for TopoNotificationFilter.To create an instance for this,
 * an XML node is needed. This XMLNode is created by reading an XML file specified with 
 * the value of triggerCriteria attribute.This should be a relative filepath 
 * from &lt;WebNMS Home&gt; directory.This file should contain an XML tag in 
 * following format.<br> 
 * &lt;?xml version="1.0" encoding="ISO-8859-1"?&gt;<br> 
 *
 * &lt;APPLICATION&gt; <br> 
 * &lt;APPLN_NAME <br> 
 *  	applnName="userValue1" /&gt; <br> 
 * &lt;NOTIFICATION <br> 
 *  	Type="userValue2" <br> 
 *  	Type1="userValue3"/&gt; <br> 
 * &lt;MO_PROP_TEST <br> 
 *  	isNetwork="userValue4" <br> 
 *  	discoveryStatus="userValue5"/&gt; <br> 
 * &lt;USERCLASS <br> 
 *  	userClassName="userValue6" /&gt; <br> 
 * &lt;MO_PROPS <br> 
 *  	prop1="userValue7" <br> 
 *  	prop2="userValue8" /&gt; <br> 
 * &lt;ARGUMENTS <br> 
 *  	arg1="userValue9" <br> 
 *  	arg2="userValue10"/&gt;<br>  
 * &lt;/APPLICATION&gt; <br> 
 * Description for the above tags are given as follows:   
 * <table BORDER WIDTH="96%" NOSAVE >
 * <tr NOSAVE>
 * <td NOSAVE>
 * <center>Tag</center>
 * </td>
 *
 * <td>
 * <center>Description</center>
 * </td>
 * </tr>
 *
 * <tr>
 * <td>APPLN_NAME</td>
 *
 * <td>Application Name which should be unique.</td>
 * </tr>
 *
 * <tr>
 * <td>NOTIFICATION</td>
 *
 * <td> The notification types like "Added","Deleted","Property","Status","Managed",
 * "Deleted" and "Discover"</td>
 * </tr>
 *
 * <tr>
 * <td>MO_PROP_TEST</td>
 *
 * <td>The properties of managedobject which is used as the match criteria
 * for the object</td>
 * </tr>
 *
 * <tr>
 * <td>USERCLASS</td>
 *
 * <td>Userclass which implements TopoNotificationHandler interface</td>
 * </tr>
 *
 * <tr>
 * <td>MO_PROPS</td>
 *
 * <td>The properties of the object for which the notification is sent. Only
 * the properties specified within this tag will be obtained in the first
 * element of the properties array in&nbsp; 
 * TopoNotificationHandler.handleNotification(Properties[]).</td>
 * </tr>
 *
 * <tr>
 * <td>ARGUMENTS</td>
 *
 * <td>This tag is optional. If the user wants some arguments to be used in
 * the TopoNotificationHandler.handleNotification(Properties[]) method,this
 * tag can be used.The arguments will be obtained in the second element of
 * the properties array.</td>
 * </tr>
 * </table></center>
 * This also implements handleNotification method of TopoNotificationHandler interface
 * to call the trigger method of ProvisioningTrigger(super class).
 * This class also registers and deregisters the ToponotificationFilter
 * @see com.adventnet.nms.topodb.TopoAPI
 * @see com.adventnet.nms.topodb.TopoNotificationHandler
 * @see com.adventnet.nms.topodb.TopoNotificationFilter
 */
public class TopoTrigger extends ProvisioningTrigger implements TopoNotificationHandler
{
/** This holds an instance of TopoAPI obtained with NmsUtil.getAPI(String) method.*/
	TopoAPI topoApi;
/** This will be used to register and deregister for Topo notifications.*/
	TopoNotificationFilter tnf;
/**
 *	 Instantiate TopoAPI.
 *	 TopoAPI(topoApi) instance is obtained with NmsUtil.getAPI(String) method.
 */
	public TopoTrigger()
	{
		topoApi = (TopoAPI) NmsUtil.getAPI ("TopoAPI");
	}
/**
 * Obtains criteria for Topo notification ,creates an instance for 
 * TopoNotificationFilter and registers the TopoNotificationFilter instance 
 * with the TopoAPI to receive triggers. XMLDataReader instance is created to 
 * read an XML file specified with the value of triggerCriteria attribute.
 * This should be a relative filepath from &lt;WebNMS Home&gt; directory.
 * Using that instance,an XMLNode instance is created.With this XMLNode instance 
 * a new TopoNotificationFilter instance is created and registrerd.
 */
	public void register() throws TriggerException
	{
		int count = 1;
		try
		{
			while(topoApi == null || !topoApi.isInitialized()) 
			{
				count++;
				try
				{
				Thread.sleep(500);
				}
				catch(InterruptedException ie) {}
				topoApi = (TopoAPI) NmsUtil.getAPI ("TopoAPI");//No I18N
				if(topoApi != null && topoApi.isInitialized())
				{
					break;
				}

				if(count >120)
				{
					throw new TriggerException("unable to get the TopoAPI");
				}
			}
		}
		catch(Exception e)
		{
			throw new TriggerException("Trigger cannot be registered "+e.getMessage());
		}
		String triggerCriteriaAttribute=getTemplate().getAttribute("triggerCriteria");
		if    (triggerCriteriaAttribute !=null && triggerCriteriaAttribute.length() !=0)
		{
			try
			{
				XMLDataReader xdr= new XMLDataReader(PureUtils.rootDir+"/"+triggerCriteriaAttribute,false);
				tnf=new TopoNotificationFilter(xdr.getRootNode(),this);
			}
			catch (Exception e)
			{
				throw new TriggerException("Failed while reading the criteria file "+e.getMessage());
			}
		}
		else
		{
			throw new TriggerException("No triggerCriteria specified");
		}
		try
		{
			topoApi.registerNotificationFilter(tnf);
		}
		catch(java.rmi.RemoteException e)
		{
			throw new TriggerException("Trigger cannot be registered "+e.getMessage());
		}
	}

/**
 * Deregisters the Toponotification instance to the TopoAPI to stop 
 * listening for triggers.
 */
	public void deregister() throws TriggerException
	{
		try
		{
			topoApi.unregisterNotificationFilter(tnf);
		}
		catch(java.rmi.RemoteException e)
		{
			throw new TriggerException("Trigger cannot be deregistered "+e.getMessage());
		}		
	}

/**
 * Triggers the provisioning operation.
 * This method is called from the Topo module when any network impulse arrives 
 * satisfying the user specifications in the TopoNotificationCriteria.xml file.
 */
	public boolean handleNotification(java.util.Properties[] prop)
	{
		try
		{
			trigger(null);
			return true;
		}
		catch(TriggerException e)
		{
			e.printStackTrace();
		}	
		return false;
	}
}	
