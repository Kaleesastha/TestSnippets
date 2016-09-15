package com.adventnet.management.policydb;

import java.util.Properties;

public class PolicyObjectImpl extends PolicyObject
{

//	Properties prop  = null;
	protected void executeAction(PolicyEvent policyEvent)
	{
		System.out.println("execute action!!!");
	}

	public Properties getProperties()
	{
		Properties prop = new Properties();
		try
		{
			prop.setProperty("name",getName());
			prop.setProperty("groupName","eerd个日批那么");
	  		prop.setProperty("status",getStatus()+"");
	  		prop.setProperty("period","-1");

	
		}
		catch(Exception r)
		{
			r.printStackTrace();
		}
		return prop;
	}

	public void setProperties(Properties prop)
	{
		setName(prop.getProperty("name"));
		setGroupName(prop.getProperty("gropuName"));
		setStatus(Integer.parseInt(prop.getProperty("status")));
		//setPeriod(Integer.parseInt(prop.getProperty("period")));

		System.out.println("set whatever u want");
	}
}

