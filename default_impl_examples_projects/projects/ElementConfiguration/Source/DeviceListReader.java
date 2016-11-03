//$Id: DeviceListReader.java,v 1.1.6.1 2012/05/29 06:26:56 karen.r Exp $
package com.adventnet.nms.config;

import java.util.*;

import com.adventnet.management.config.xml.*;

public class DeviceListReader
{
	private String protocol = null;
	private Vector devices = null;

	public DeviceListReader(String deviceList)
	{
		try{
			parse(deviceList);	
		}catch(Exception e)
		{
			System.out.println("exception occured during device list parsing");
		}
	}

	public DeviceListReader(String protocol, Device[] deviceArr)
	{
		parse(protocol, deviceArr);
	}

	public String getProtocol()
	{
		return protocol;
	}

	public Vector getDevices()
	{
		return devices;
	}

	private void parse(String deviceList) throws Exception
	{
		DeviceList reader = new DeviceList(deviceList);

		protocol = reader.getProtocol();

		Device[] deviceArr = reader.getDevices();

		devices = parseDevices(deviceArr);
	}

	private void parse(String protocol, Device[] deviceArr)
	{
		this.protocol = protocol;

		devices = parseDevices(deviceArr);
	}

	private Vector parseDevices(Device[] deviceArr)
	{
		Vector vec = null;

		if(protocol.equalsIgnoreCase("SNMP"))
		{
			vec = getSNMPDevices(deviceArr);
		}
		else if(protocol.equalsIgnoreCase("Telnet"))
		{
			vec = getTelnetDevices(deviceArr);
		}
		else if(protocol.equalsIgnoreCase("TFTP"))
		{
			vec = getTFTPDevices(deviceArr);
		}
		else if(protocol.equalsIgnoreCase("FTP"))
		{
			vec = getFTPDevices(deviceArr);
		}
		else if(protocol.equalsIgnoreCase("TL1"))
		{
			vec = getTL1Devices(deviceArr);
		}
		else if (protocol.equalsIgnoreCase("Netconf"))//No I18N
		{
			vec = getFTPDevices(deviceArr);//reusing this method as parameters are same
		}

		return vec;
	}

	private Vector getSNMPDevices(Device[] deviceArr)
	{
		Vector deviceVec = new Vector();

		for(int i=0; i<deviceArr.length; i++)
		{
			Vector device = new Vector();

			device.addElement(deviceArr[i].getAttribute("host"));
			device.addElement(deviceArr[i].getAttribute("port"));
			device.addElement(deviceArr[i].getAttribute("community"));
			device.addElement(deviceArr[i].getAttribute("version"));
			device.addElement(deviceArr[i].getAttribute("userName"));
			device.addElement(deviceArr[i].getAttribute("contextName"));
			device.addElement(deviceArr[i].getAttribute("contextID"));
			device.addElement(deviceArr[i].getAttribute("maxRepetitions"));
			device.addElement(deviceArr[i].getAttribute("nonRepeaters"));
			device.addElement(deviceArr[i].getAttribute("authProtocol"));
			device.addElement(deviceArr[i].getAttribute("authPassword"));
			device.addElement(deviceArr[i].getAttribute("privPassword"));
			device.addElement(deviceArr[i].getAttribute("retries"));
			device.addElement(deviceArr[i].getAttribute("timeout"));

			deviceVec.addElement(device);
		}

		return deviceVec;
	}

	private Vector getTelnetDevices(Device[] deviceArr)
	{
		Vector deviceVec = new Vector();

		for(int i=0; i<deviceArr.length; i++)
		{
			Vector device = new Vector();

			device.addElement(deviceArr[i].getAttribute("host"));
			device.addElement(deviceArr[i].getAttribute("port"));
			device.addElement(deviceArr[i].getAttribute("loginName"));
			device.addElement(deviceArr[i].getAttribute("password"));
			device.addElement(deviceArr[i].getAttribute("loginPrompt"));
			device.addElement(deviceArr[i].getAttribute("passwordPrompt"));
			device.addElement(deviceArr[i].getAttribute("shellPrompt"));
			device.addElement(deviceArr[i].getAttribute("retries"));
			device.addElement(deviceArr[i].getAttribute("timeout"));

			deviceVec.addElement(device);
		}

		return deviceVec;
	}

	private Vector getTFTPDevices(Device[] deviceArr)
	{
		Vector deviceVec = new Vector();

		for(int i=0; i<deviceArr.length; i++)
		{
			Vector device = new Vector();

			device.addElement(deviceArr[i].getAttribute("host"));
			device.addElement(deviceArr[i].getAttribute("port"));
			device.addElement(deviceArr[i].getAttribute("retries"));
			device.addElement(deviceArr[i].getAttribute("timeout"));

			deviceVec.addElement(device);
		}

		return deviceVec;
	}

	private Vector getFTPDevices(Device[] deviceArr)
	{
		Vector deviceVec = new Vector();

		for(int i=0; i<deviceArr.length; i++)
		{
			Vector device = new Vector();

			device.addElement(deviceArr[i].getAttribute("host"));
			device.addElement(deviceArr[i].getAttribute("port"));
			device.addElement(deviceArr[i].getAttribute("userName"));
			device.addElement(deviceArr[i].getAttribute("password"));
			device.addElement(deviceArr[i].getAttribute("retries"));
			device.addElement(deviceArr[i].getAttribute("timeout"));

			deviceVec.addElement(device);
		}

		return deviceVec;
	}

	private Vector getTL1Devices(Device[] deviceArr)
	{
		Vector deviceVec = new Vector();

		for(int i=0; i<deviceArr.length; i++)
		{
			Vector device = new Vector();

			device.addElement(deviceArr[i].getAttribute("host"));
			device.addElement(deviceArr[i].getAttribute("port"));
			device.addElement(deviceArr[i].getAttribute("retries"));
			device.addElement(deviceArr[i].getAttribute("timeout"));

			deviceVec.addElement(device);
		}

		return deviceVec;
	}
}


