//$Id: ProvisioningApiHandler.java,v 1.2 2007/08/02 12:25:28 srajeswari Exp $
package com.adventnet.nms.provisioning.ui;

import java.util.*;
import java.io.*;
import java.applet.*;
import java.net.*;
import javax.naming.Context;

import com.adventnet.nms.provisioning.xml.*;
import com.adventnet.management.config.xml.*;
import com.adventnet.nms.provisioning.*;
import com.adventnet.nms.provisioning.ext.*;
import com.adventnet.nms.provisioning.ui.ProvClientUtils;
import com.adventnet.nms.provisioning.server.ProvisioningAPIImpl_SessionStub;
//import com.adventnet.security.authentication.RMIAccessAPI;
import org.w3c.dom.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import com.adventnet.nms.util.NmsClientUtil;
import com.adventnet.nms.util.PureClientUtils;
import com.adventnet.nms.util.ClientFrameWorkAPI;

public class ProvisioningApiHandler
{

	ProvisioningAPI api = null;
	Properties prop;
	//int rmiRegPort =1099;
	//String serverName = "localhost";
	private boolean failOverOccured;
	private boolean exit;
	String userName;
	private String password;
	String connectionType;

	public ProvisioningApiHandler(Properties p) throws Exception
	{
		prop=p;
		userName=p.getProperty("USERNAME");
		if (userName==null)
		{
			throw new Exception(ProvClientUtils.getString("No USERNAME provided"));
		}
		connectionType=p.getProperty("CONNECTION_TYPE");
		if ((connectionType==null)||(connectionType.trim().equals("")))
		{
			connectionType="RMI";
		}
		password=p.getProperty("PASSWORD");
		reInitializeAPI(p);
	}


	/** Invoke the provisioning functions by doing an apply. **/
	public String provision(Template provisionTemplate) throws InvalidTemplateException,java.rmi.RemoteException,TemplateInitializationException, OperationFailedException
		{
			while (failOverOccured)
			{
				if (exit)
				{
					return null;
				}
				else
				{
					try
					{
						Thread.sleep(1000);
					}
					catch(InterruptedException ie)
					{
						// ie.printStackTrace();
					}
				}
			}	

			Element element = provisionTemplate.getElement();
			StringWriter writer = new StringWriter();
			try
			{
				TransformerFactory fac = TransformerFactory.newInstance();
				Transformer trans = fac.newTransformer();
				trans.setOutputProperty("indent","yes");//No I18N
				trans.setOutputProperty("encoding","UTF-8");//No I18N
				DOMSource domSource = new DOMSource(element);
				StreamResult streamResult = new StreamResult(writer);
				trans.transform(domSource, streamResult);
			}
			catch(Exception e1)
			{
				e1.printStackTrace();
			}

			return api.provision(writer.toString());
		}


	/** 
	 * Load the template from server using supplied parameters, and 
	 * render the forms.  
	 **/
	public String getTemplate(String templatename,String MOName) throws java.rmi.RemoteException, TemplateNotFoundException, TemplateInitializationException
	{
		/*	if(api == null)
			{
				lookupAPI();
			}*/
			while (failOverOccured)
			{
				if (exit)
				{
					return null;
				}
				else
				{
					try
					{
						Thread.sleep(1000);
					}
					catch(InterruptedException ie)
					{
						// ie.printStackTrace();
					}
				}
			}
			return api.getTemplate(templatename,MOName);
	}
	public void setProperties(Properties p)
	{
		prop = p;
	}

	public Properties getProperties()
	{
		return prop;
	}

	//Method by Tony
	/*public ProvisioningAPI lookupAPI() throws java.rmi.RemoteException
	{
		if (api!=null) return api; 
		try 
		{
			if (tp.getApplet() != null) 
			{
				api = (ProvisioningAPI) java.rmi.Naming.lookup
					("//"+tp.getApplet().getDocumentBase().getHost() 
					 +":"+rmiRegPort+ "/ProvisioningAPI");
			}
			else if (serverName != null) 
			{
				api = (ProvisioningAPI) java.rmi.Naming.lookup
					("//"+serverName+":"+rmiRegPort+"/ProvisioningAPI");
			}
		}
		catch (Exception ex) 
		{ 
			//tp.appendStatus(ex.getClass().getName()+ " occurred " );
			//tp.appendStatus(ex.getMessage());
			System.err.println("Error initializing: " + ex);
			throw new java.rmi.RemoteException(ex.toString());
		}
		return api;
	}*/ 



	/** Get the list of templates from the provisioning server. **/
	public String[] getTemplateList()throws java.rmi.RemoteException
	{
		/*if(api == null)
		{
			lookupAPI();
		}*/
		while (failOverOccured)
		{
			if (exit)
			{
				return null;
			}
			else
			{
				try
				{
					Thread.sleep(1000);
				}
				catch(InterruptedException ie)
				{
					// ie.printStackTrace();
				}
			}
		}
		return api.getTemplateList(); 
	}


	/**Get the status of the Result**/ 
	public String getResultStatus(String id) throws NoSuchOperationException,java.rmi.RemoteException,InvalidTemplateException
	{
		String result=null;		
		try
		{
			boolean flag = false;
			while(!flag)
			{
				String status = getStatus(id);
				OperationStatus opStat=new OperationStatus(status);
				status=opStat.getAttribute("status");	
				if(status.equalsIgnoreCase("INPROGRESS") || status.equalsIgnoreCase("NOTSTARTED") )
				{
					try
					{
						Thread.sleep(1000);
					}
					catch(InterruptedException ie)
					{
					}
				}
				else
				{
					flag = true;

				}
			}

			while(failOverOccured) 
			{
				if (exit)
				{
					throw new java.rmi.RemoteException();
				}
				else
				{
					try
					{
						Thread.sleep(1000);
					}
					catch(InterruptedException ie)
					{
					}
				}	
			}
			result=api.getResult(id);
		}
		catch (IncompleteOperationException ine)
		{
		}
		catch (NoSuchOperationException e)
		{
			throw e;
		}
		catch (java.rmi.RemoteException re)
		{
			throw re;
		}
		return result;
	}
	public String getStatus(String id) throws NoSuchOperationException,java.rmi.RemoteException
	{
		String s="";
		try
		{
			while(failOverOccured) 
			{
				if (exit)
				{
					throw new java.rmi.RemoteException();
				}
				else
				{
					try
					{
						Thread.sleep(1000);
					}	
					catch(InterruptedException ie)
					{
						ie.printStackTrace();
					}
				}	
			}
			s=api.getStatus(id);
		}
		catch (NoSuchOperationException e)
		{
			throw e;
		}
		catch (java.rmi.RemoteException re)
		{
			if (failOverOccured)
			{
				return getStatus(id);
			}
			else
			{
				throw re;
			}	
		}
		return s;
	}

	/** Get the list of template parameters from the provisioning server. **/
	public String getTemplateParameters(String name)	
		throws java.rmi.RemoteException, TemplateNotFoundException
		{
			/*if(api == null)
			{
				lookupAPI();
			}*/
			while (failOverOccured)
			{
				if (exit)
				{
					return null;
				}
				else
				{
					try
					{
						Thread.sleep(1000);
					}
					catch(InterruptedException ie)
					{
						//ie.printStackTrace();
					}
				}
			}	
			return api.getTemplateParameters(name);	
		}


	public boolean isExit()
	{
		return exit;
	}
	public void setExit(boolean b)
	{
		exit=b;
	}
	public boolean isFailOverOccured()
	{
		return failOverOccured;
	}
	public void setFailOverOccured(boolean b)
	{
		failOverOccured=b;
	}
	public void reInitializeAPI(Properties p) throws Exception
	{
		if (connectionType.equalsIgnoreCase("rmi"))
		{
			if (password==null)
			{
				throw new Exception(ProvClientUtils.getString("invalid password"));
			}
			String servername=p.getProperty("HOSTNAME");
			if ((servername==null)||(servername.trim().equals("")))
			{
				servername="localhost";
			}
			String rmiport=p.getProperty("RMI_REG_PORT");
			if ((rmiport==null)||(rmiport.trim().equals("")))
			{
				rmiport="1099";
			}
			else
			{
				try
				{
					rmiport=Integer.parseInt(rmiport.trim())+"";
				}
				catch(Exception e)
				{
					throw new Exception(ProvClientUtils.getString("Invalid RMI_REG_PORT"));
				}
			}
			com.adventnet.security.authentication.RMIAccessAPI r= (com.adventnet.security.authentication.RMIAccessAPI)java.rmi.Naming.lookup("rmi://"+servername+":"+rmiport+"/RMIAccessAPI");
			api = (ProvisioningAPI)r.getAPI(userName,password,"ProvisioningAPI");
		}
		else if (connectionType.equalsIgnoreCase("JMS"))
		{
			password=p.getProperty("PASSWORD");
			Object cfObj=p.remove("CONTEXT_FACTORY");			
			if (cfObj==null)
			{
				throw new Exception(ProvClientUtils.getString("No CONTEXT_FACTORY provided"));
			}
			else
			{
				p.put(Context.INITIAL_CONTEXT_FACTORY,cfObj);
			}
			Object proURLObj=p.remove("PROVIDER_URL");			
			if (proURLObj != null)
			{
				p.put(Context.PROVIDER_URL,proURLObj);
			}
			p.put("MODE", "JMS");
			//p.put("USERNAME", userName);
			//p.put("PASSWORD", password);
			api=ProvClientUtils.getProvisioningAPI(p);
		}
		else if (connectionType.equalsIgnoreCase("SESSION"))
		{
			if (PureClientUtils.commonSocket==null)
			{
				String hostName=p.getProperty("HOSTNAME");
				if (hostName==null)
				{
					hostName="localhost";
				}
				String port=p.getProperty("WEB-SERVER-PORT");
				if ((port==null)||(port.trim().equals("")))
				{
					System.err.println(ProvClientUtils.getString("No WEB-SERVER-PORT provided. Trying with default port 9090"));
					port="9090";
				}
				String language=p.getProperty("LANGUAGE");
				if ((language==null)||(language.trim().length()<2))
				{
					language="en";
				}
				String country=p.getProperty("COUNTRY");
				if ((country==null)||(country.trim().length()<2))
				{
					country="US";
				}
				Properties prop = new Properties();
				prop.put("USERNAME",userName);
				prop.put("PASSWORD",password);
				prop.put("HOSTNAME",hostName);
				prop.put("WEB-SERVER-PORT",port);
				prop.put("LANGUAGE",language);
                prop.put("COUNTRY",country);
				new ClientFrameWorkAPI(prop);
			}
			api = new ProvisioningAPIImpl_SessionStub();
		}
		else
		{
			throw new Exception(ProvClientUtils.getString("Invalid CONNECTION_TYPE"));
		}
	}

    public Properties getConnectionProperties()
    {
        return prop;
    }
	public void stopRunningOperation(String opId) throws Exception
	{
		if ((api instanceof ProvisioningAPIImpl_SessionStub)&&(opId!=null))
		{
				if((opId!=null)&&(opId.trim().length()>0))
				{
					((ProvisioningAPIImpl_SessionStub)api).stopRunningOperation(opId);
				}
				
		}
	}
}    

