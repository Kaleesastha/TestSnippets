//$Id: DeviceListGenerator.java,v 1.1.6.1 2012/05/29 06:26:56 karen.r Exp $
package com.adventnet.nms.config;

import java.util.*;

import com.adventnet.management.config.xml.*;
import com.adventnet.management.config.snmp.*;
import com.adventnet.management.config.telnet.*;
import com.adventnet.management.config.ftp.*;
import com.adventnet.management.config.netconf.NetconfDevice;

public class DeviceListGenerator
{
	private String deviceListName = null;
	private String protocol = null;
	private Vector deviceVec = null;

	public DeviceListGenerator()
	{
	}

	public DeviceListGenerator(String deviceListName)
	{
		this.deviceListName = deviceListName;
	}

	public DeviceListGenerator(String deviceListName, String protocol)
	{
		this.deviceListName = deviceListName;
		this.protocol = protocol;
	}

	public void setDeviceListName(String deviceListName)
	{
		this.deviceListName = deviceListName;
	}

	public void setProtocol(String protocol)
	{
		this.protocol = protocol;
	}

	public void setDevices(Vector deviceVec)	
	{
		this.deviceVec = deviceVec;
	}

	public Device[] getDevices()
	{
		Device[] devices = null;

		if(protocol != null && deviceVec != null)
		{
			devices = null;

			if(protocol.equalsIgnoreCase("SNMP"))
			{
				devices = generateSNMPDeviceList();
			}
			else if(protocol.equalsIgnoreCase("Telnet"))
			{
				devices = generateTelnetDeviceList();
			}
			else if(protocol.equalsIgnoreCase("TFTP"))
			{
				devices = generateTFTPDeviceList();
			}
			else if(protocol.equalsIgnoreCase("FTP"))
			{
				devices = generateFTPDeviceList();
			}
			else if(protocol.equalsIgnoreCase("TL1"))
			{
				devices = generateTL1DeviceList();
			}
			else if(protocol.equalsIgnoreCase("Netconf"))//No I18N
			{
				devices = generateNetconfDeviceList();
			}
		}

		return devices;
	}

	public String generateDeviceList()
	{
		String deviceList = null;

		DeviceList dl = new DeviceList(deviceListName, protocol.toLowerCase());
		dl.setDevices(getDevices());

		deviceList = dl.getDeviceList();
		
		return deviceList;
	}

	private Device[] generateSNMPDeviceList()
	{
		SnmpDevice[] devices = new SnmpDevice[deviceVec.size()];

		for(int i=0; i<deviceVec.size(); i++)
		{
			Vector device = (Vector)deviceVec.elementAt(i);

			String snmpVersion = (String)device.elementAt(3);

			if(snmpVersion != null)
			{
				if(snmpVersion.equals("v1"))
				{
					snmpVersion = SnmpAttributeConstants.SNMP_VERSION_1;
				}
				else if(snmpVersion.equals("v2c"))
				{
					snmpVersion = SnmpAttributeConstants.SNMP_VERSION_2C;
				}
				else if(snmpVersion.equals("v3"))
				{
					snmpVersion = SnmpAttributeConstants.SNMP_VERSION_3;
				}
			}

			devices[i] = new SnmpDevice((String)device.elementAt(0), (String)device.elementAt(1), (String)device.elementAt(2), snmpVersion);

			if(device.elementAt(4) != null)
			{
				String str = device.elementAt(4).toString().trim();

				if(!str.equals(""))
				{
					devices[i].setUserName(str);
				}
			}

			if(device.elementAt(5) != null)
			{
				String str = device.elementAt(5).toString().trim();

				if(!str.equals(""))
				{
					devices[i].setContextName(str);
				}
			}

			if(device.elementAt(6) != null)
			{
				String str = device.elementAt(6).toString().trim();

				if(!str.equals(""))
				{
					devices[i].setContextID(str);
				}
			}

			if(device.elementAt(7) != null)
			{
				String str = device.elementAt(7).toString().trim();

				if(!str.equals(""))
				{
					devices[i].setMaxRepetitions(str);
				}
			}

			if(device.elementAt(8) != null)
			{
				String str = device.elementAt(8).toString().trim();

				if(!str.equals(""))
				{
					devices[i].setNonRepeaters(str);
				}
			}

			if(device.elementAt(9) != null)
			{
				String str = device.elementAt(9).toString().trim();

				if(!str.equals(""))
				{
					devices[i].setAuthProtocol(str);
				}
			}

			if(device.elementAt(10) != null)
			{
				String str = device.elementAt(10).toString().trim();

				if(!str.equals(""))
				{
					devices[i].setAuthPassword(str);
				}
			}

			if(device.elementAt(11) != null)
			{
				String str = device.elementAt(11).toString().trim();

				if(!str.equals(""))
				{
					devices[i].setPrivPassword(str);
				}
			}

			if(device.elementAt(12) != null)
			{
					devices[i].setRetries(Integer.parseInt((String)device.elementAt(12)));
			}

			if(device.elementAt(13) != null)
			{
					devices[i].setTimeout(Integer.parseInt((String)device.elementAt(13)));
			}

		}

		return devices;
	}

	private Device[] generateTelnetDeviceList()
	{
		TelnetDevice[] devices = new TelnetDevice[deviceVec.size()];			
		
		for(int i=0; i<deviceVec.size(); i++)
		{
			Vector device = (Vector)deviceVec.elementAt(i);
			
			devices[i] = new TelnetDevice((String)device.elementAt(0), (String)device.elementAt(1), (String)device.elementAt(2), (String)device.elementAt(3), Integer.parseInt((String)device.elementAt(7)), Integer.parseInt((String)device.elementAt(8)));

			if(device.elementAt(4) != null)
			{
				String str = device.elementAt(4).toString().trim();

				if(!str.equals(""))
				{
					devices[i].setLoginPrompt(str);
				}
			}

			if(device.elementAt(5) != null)
			{
				String str = device.elementAt(5).toString().trim();

				if(!str.equals(""))
				{
					devices[i].setPasswordPrompt(str);
				}
			}

			if(device.elementAt(6) != null)
			{
				String str = device.elementAt(6).toString().trim();

				if(!str.equals(""))
				{
					devices[i].setShellPrompt(str);
				}
			}
		}

		return devices;
	}
	
	private Device[] generateFTPDeviceList()
	{
		FTPDevice[] devices = new FTPDevice[deviceVec.size()];

		for(int i=0; i<deviceVec.size(); i++)
		{
			Vector device = (Vector)deviceVec.elementAt(i);	
			
			devices[i] = new FTPDevice((String)device.elementAt(0), Integer.parseInt((String)device.elementAt(1)), (String)device.elementAt(2), (String)device.elementAt(3));

			devices[i].setRetries(Integer.parseInt((String)device.elementAt(4)));
			
			devices[i].setTimeout(Integer.parseInt((String)device.elementAt(5)));
		}

		return devices;
	}
	
	private Device[] generateTFTPDeviceList()
	{
		Device[] devices = new Device[deviceVec.size()];

		for(int i=0; i<deviceVec.size(); i++)
		{
			Vector device = (Vector)deviceVec.elementAt(i);	
			
			devices[i] = new Device((String)device.elementAt(0), (String)device.elementAt(1), Integer.parseInt((String)device.elementAt(2)), Integer.parseInt((String)device.elementAt(3)));
		}

		return devices;
	}
	
	private Device[] generateTL1DeviceList()
	{
		Device[] devices = new Device[deviceVec.size()];

		for(int i=0; i<deviceVec.size(); i++)
		{
			Vector device = (Vector)deviceVec.elementAt(i);
			
			devices[i] = new Device((String)device.elementAt(0), (String)device.elementAt(1), Integer.parseInt((String)device.elementAt(2)), Integer.parseInt((String)device.elementAt(3)));
		}

		return devices;
	}

	private Device[] generateNetconfDeviceList()
	{
		Device[] devices = new Device[deviceVec.size()];
		for(int i=0; i<deviceVec.size(); i++)
		{
			Vector device = (Vector)deviceVec.elementAt(i);
			
			devices[i] = new NetconfDevice((String)device.elementAt(0), (String)device.elementAt(1), (String)device.elementAt(2), (String)device.elementAt(3));
			devices[i].setRetries(Integer.parseInt((String)device.elementAt(4)));
			
			devices[i].setTimeout(Integer.parseInt((String)device.elementAt(5)));
		}
		return devices;
	}
}
