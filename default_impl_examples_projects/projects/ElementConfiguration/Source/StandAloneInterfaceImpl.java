//$Id: StandAloneInterfaceImpl.java,v 1.2 2007/02/23 10:45:18 srajeswari Exp $
package com.adventnet.nms.config;


import java.io.*;
import java.util.*;

import javax.swing.JApplet;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Element;
import org.w3c.dom.Document;

import org.xml.sax.InputSource;


import com.adventnet.management.config.xml.*;

public class StandAloneInterfaceImpl 
{
	private JApplet applet = null;
	
	public StandAloneInterfaceImpl(JApplet applet)
	{
		this.applet = applet;
	}
	
	public String getTask(String taskName)
	{
		System.out.println("the task name is " + taskName);
		StringBuffer sb = new StringBuffer();
		try
		{
			File taskFile = new File(System.getProperty("user.dir") + File.separator + "configtasks" + File.separator + taskName + ".xml");
			FileInputStream fis = new FileInputStream(taskFile);
			int c = -1;
			while((c=fis.read()) != -1)
				sb.append((char)c);
		}
		catch(Exception ex)
		{
			return null;
		}
		return sb.toString();
	}
	
	public String getLastExecutedTime(String taskName)
	{
		return null;
	}
	
	public boolean deleteTask(String taskName)
	{
		try
		{
			File taskFile = new File(System.getProperty("user.dir") + File.separator + "configtasks" + File.separator + taskName + ".xml");
			if(taskFile.exists())
				taskFile.delete();
			return true;
		}
		catch(Exception ex)
		{
		}
		return false;
	}
	
	public Vector getTasks()
	{
		Vector taskVector = new Vector();
		try
		{
			File taskFile = new File(System.getProperty("user.dir") + File.separator + "configtasks");
			String[] taskArray = taskFile.list();
			for(int i = 0; i < taskArray.length; i++)
			{
				//unnecessary check. Feeling sleepy so no more heavy thinking.
				if(taskArray[i].indexOf(".xml") == -1)
					continue;
				try
				{
					String taskName = taskArray[i].substring(0, taskArray[i].indexOf('.'));
					taskVector.add(taskName);
				}
				catch(Exception ex)
				{
				}
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
		return taskVector;
	}
	
	public String getDeviceList(String deviceListName)
	{	
		StringBuffer sb = new StringBuffer();
		try
		{
			File taskFile = new File(System.getProperty("user.dir") + File.separator + "configtasks" + File.separator + "devicelist" + File.separator + deviceListName + ".xml");
			FileInputStream fis = new FileInputStream(taskFile);
			int c = -1;
			while((c=fis.read()) != -1)
				sb.append((char)c);
		}
		catch(Exception ex)
		{
			return null;
		}
		return sb.toString();
	}
	
	public String getDataSource(String dataSourceName)
	{
		System.out.println("the datasource name is " + dataSourceName);
		StringBuffer sb = new StringBuffer();
		try
		{
			File taskFile = new File(System.getProperty("user.dir") + File.separator + "configtasks" + File.separator + "datasource" + File.separator + dataSourceName + ".xml");
			FileInputStream fis = new FileInputStream(taskFile);
			int c = -1;
			while((c=fis.read()) != -1)
				sb.append((char)c);
		}
		catch(Exception ex)
		{
			return null;
		}
		return sb.toString();
	}

	public boolean saveTask(String taskXML)
	{
		System.out.println(" taskXML is : " + taskXML);
		try
		{
			ConfigTask ct = new ConfigTask(taskXML);
			String taskName = ct.getTaskName();
			File taskFile = new File(System.getProperty("user.dir") + File.separator + "configtasks" + File.separator + taskName + ".xml");

			FileOutputStream fos = new FileOutputStream(taskFile);
			fos.write(taskXML.getBytes());
			fos.flush();
			fos.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();

			return false;
		}
		return true;
	}

	public boolean saveDeviceList(String deviceListXML)
	{
		System.out.println(" deviceListXML is : " + deviceListXML);

		try
		{
			DeviceList deviceList = new DeviceList(deviceListXML);
			String deviceListName = deviceList.getDeviceListName();

			File taskFile = new File(System.getProperty("user.dir") + File.separator + "configtasks" + File.separator + "devicelist" + File.separator + deviceListName + ".xml");
			FileOutputStream fos = new FileOutputStream(taskFile);
			fos.write(deviceListXML.getBytes());
			fos.flush();
			fos.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean saveDataSource(String datasourceXML)
	{
		System.out.println(" datasourceXML is : " + datasourceXML);
		try
		{
			DataSource ds = new DataSource(datasourceXML);
			String dsName = ds.getDataSourceName();
			File taskFile = new File(System.getProperty("user.dir") + File.separator + "configtasks" + File.separator + "datasource" + File.separator + dsName + ".xml");
			FileOutputStream fos = new FileOutputStream(taskFile);
			fos.write(datasourceXML.getBytes());
			fos.flush();
			fos.close();
		}
		catch(Exception ex)
		{
			return false;
		}
		return true;
	}

	public boolean deleteDeviceListMap(String taskName)
	{
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try
		{
			File testFile = new File(System.getProperty("user.dir") + File.separator + "configtasks" + File.separator + "devicelist" + File.separator + "TaskSpecificList.serial");
			if(!testFile.exists())
			{
				return false;
			}
			fis = new FileInputStream(System.getProperty("user.dir") + File.separator + "configtasks" + File.separator + "devicelist" + File.separator + "TaskSpecificList.serial");
			ois = new ObjectInputStream(fis);
			Hashtable taskToList = (Hashtable)ois.readObject();
			if(taskToList == null)
			{
				return false;
			}
			taskToList.remove(taskName);

			fos = new FileOutputStream(System.getProperty("user.dir") + File.separator + "configtasks" + File.separator + "devicelist" + File.separator + "TaskSpecificList.serial");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(taskToList);
			oos.flush();
			return true;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			try
			{
				ois.close();
				fis.close();
				oos.close();
				fos.close();
			}
			catch(Exception ex)
			{
			}
		}
		return false;
	}
	
	public boolean saveDeviceListMap(String taskName, Vector deviceListNames)
	{
		System.out.println("The save deviceList map for task " + taskName);
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try
		{
			File testFile = new File(System.getProperty("user.dir") + File.separator + "configtasks" + File.separator + "devicelist" + File.separator + "TaskSpecificList.serial");
			if(!testFile.exists())
			{
				Hashtable taskToList = new Hashtable();
				taskToList.put(taskName, deviceListNames);
				fos = new FileOutputStream(System.getProperty("user.dir") + File.separator + "configtasks" + File.separator + "devicelist" + File.separator + "TaskSpecificList.serial");
				oos = new ObjectOutputStream(fos);
				oos.writeObject(taskToList);
				oos.flush();
				return true;
			}
			fis = new FileInputStream(System.getProperty("user.dir") + File.separator + "configtasks" + File.separator + "devicelist" + File.separator + "TaskSpecificList.serial");
			ois = new ObjectInputStream(fis);
			Hashtable taskToList = (Hashtable)ois.readObject();
			if(taskToList == null)
			{
				taskToList = new Hashtable();
			}
			taskToList.put(taskName, deviceListNames);

			fos = new FileOutputStream(System.getProperty("user.dir") + File.separator + "configtasks" + File.separator + "devicelist" + File.separator + "TaskSpecificList.serial");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(taskToList);
			oos.flush();
			return true;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			try
			{
				ois.close();
				fis.close();
				oos.close();
				fos.close();
			}
			catch(Exception ex)
			{
			}
		}
		return false;
	}

	public Vector getProtocolSpecificDeviceLists(String protocol)
	{
		Vector toReturn = null;
		try
		{
			File testFile = new File(System.getProperty("user.dir") + File.separator + "configtasks" + File.separator + "devicelist");
			String deviceLists[] = testFile.list();
			for(int i = 0 ; i < deviceLists.length; i++)
			{
				String fileName = deviceLists[i];
				if(fileName.indexOf(".xml") != -1)
				{
					Document document = null; //changed the crimson API into org.w3c.dom API to avoid JDK1.5 compilation error
					File file = new File(System.getProperty("user.dir") + File.separator + "configtasks" + File.separator + "devicelist" + File.separator + fileName);
					try
					{
						DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
						dbf.setValidating(false);// No dtd validations.
						DocumentBuilder builder = dbf.newDocumentBuilder();
						document = builder.parse(file);

						if(document == null)
							return null;

						Element root = document.getDocumentElement();
						root.normalize();
						if(root.getAttribute("protocol").equalsIgnoreCase(protocol))
						{
							if(toReturn == null)
								toReturn = new Vector();
							toReturn.add(root.getAttribute("name"));
						}
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			}
		}
		catch(Exception ex)
		{
		}
		return toReturn;
	}
	
	public Vector getTaskSpecificDeviceLists(String taskName)
	{
		try
		{
			File testFile = new File(System.getProperty("user.dir") + File.separator + "configtasks" + File.separator + "devicelist" + File.separator + "TaskSpecificList.serial");
			if(!testFile.exists())
			{
				return null;
			}
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + File.separator + "configtasks" + File.separator + "devicelist" + File.separator + "TaskSpecificList.serial");
			ObjectInputStream ois = new ObjectInputStream(fis);
			Hashtable taskToList = (Hashtable)ois.readObject();
			System.out.println("the hash table is " + taskToList);
			if(taskToList != null)
				return (Vector)taskToList.get(taskName);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return null;
	}


	public boolean deleteDeviceList(String deviceListName)
	{
		try
		{
			File toDelete = new File(System.getProperty("user.dir") + File.separator + "configtasks" + File.separator + "devicelist" + File.separator + deviceListName + ".xml");
			toDelete.delete();
			return true;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return false;
	}

	public String[] getAssociatedDataSource(String taskName)
	{
		String[] toReturn = null;
		Vector tempVector = new Vector();
		try
		{
			File testFile = new File(System.getProperty("user.dir") + File.separator + "configtasks" + File.separator + "datasource");
			String dataSources[] = testFile.list();
			for(int i = 0 ; i < dataSources.length; i++)
			{
				String fileName = dataSources[i];
				if(fileName.indexOf(".xml") != -1)
				{
					Document document = null; //Changed the crimson API into org.w3c.dom API to avoid JDK1.5 compilation error.
					File file = new File(System.getProperty("user.dir") + File.separator + "configtasks" + File.separator + "datasource" + File.separator + fileName);
					try
					{
						DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
						dbf.setValidating(false);// No dtd validations.
						DocumentBuilder builder = dbf.newDocumentBuilder();
			 builder.parse(file);

						if(document == null)
							return null;

						Element root = document.getDocumentElement();
						root.normalize();
						if(root.getAttribute("associatedTasks").equalsIgnoreCase(taskName))
						{
							tempVector.add(root.getAttribute("name"));
						}
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			}
		}
		catch(Exception ex)
		{
		}
		if(tempVector.size() != 0)
		{
			toReturn = new String[tempVector.size()];
			for(int i = 0 ; i < tempVector.size(); i++)
			{
				toReturn[i] = (String) tempVector.elementAt(i);
			}
		}
		return toReturn;

	}

	public boolean deleteDataSource(String dataSourceName)
	{
		try
		{
			File taskFile = new File(System.getProperty("user.dir") + File.separator + "configtasks" + File.separator + "datasource" + File.separator + dataSourceName + ".xml");
			if(taskFile.exists())
				taskFile.delete();
			return true;
		}
		catch(Exception ex)
		{
		}
		return false;
	}
	
}
