//$Id: ConfFileMigration.java,v 1.2 2010/10/29 13:45:52 swaminathap Exp $
package com.webnms.nms.migration;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;

import com.adventnet.nms.poll.ReportObject;
import com.adventnet.nms.server.dataarchiver.DataMgmtXmlParser;
import com.adventnet.nms.util.MailConfigParser;
import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.util.PureUtils;
import com.adventnet.nms.util.XMLDataReader;
import com.adventnet.nms.util.XMLDataWriter;
import com.adventnet.nms.util.XMLNode;


public class ConfFileMigration
{

	/**
	 * This class performs the migration of details like reports,mail details from the old configuration 
	 * file to the new ones.
	 */
	
	private String home;
	
	public ConfFileMigration(String home)
	{
		this.home = home;
	}
	
	public static void main(String[] args)
	{
		if(args.length < 2)
		{
			System.err.println("Usage : ConfFileMigration <Nms Home> <option: All/Mail/Reports>"); //No I18N
			return;
		}

		String home = args[0]+File.separator;
		String mode = args[1];
		ConfFileMigration obj = new ConfFileMigration(home);
		
		if(mode.equalsIgnoreCase("reports"))
		{
			obj.migrateReports();
		}
		else if(mode.equalsIgnoreCase("mail"))
		{
			obj.migrateMailSettings();
		}
		else
		{
			obj.migrateAll();
		}
			
	}
	
	/*
	 * This method performs all the migration
	 */
	public void migrateAll()
	{
		migrateReports();
		migrateMailSettings();
	}
	
	/*
	 * This method performs the report details migration
	 */
	public void migrateReports()
	{
		System.out.println("Starting Report migration from reports.xml"); //No I18N
		ArrayList<ReportObject> objects = readAndCreateReports();
		
		NmsUtil.AIM_ROOT = home;
		DataMgmtXmlParser parser = DataMgmtXmlParser.getInstance();
		
		int size = objects.size();
		for(int i=0;i<size;i++)
		{
			ReportObject obj = objects.get(i);
			obj.setName("Report_"+(i+1));
			parser.addNode(obj.getProperties());
		}
		
		System.out.println("Completed migration of report details to ReportsManagement.xml"); //No I18N
	}
	
	private ArrayList<ReportObject> readAndCreateReports()
	{
		ArrayList<ReportObject> result = new ArrayList<ReportObject>();
		String oldFile = home+File.separator+"conf"+File.separator+"reports.conf";
		
		XMLDataReader reader = new XMLDataReader(oldFile,false);
		Vector children = reader.getRootChildNodes();
        for(Enumeration en = children.elements(); en.hasMoreElements();)
        {
            XMLNode node = (XMLNode)en.nextElement();
            if(node.getNodeType() == XMLNode.ELEMENT)
            {
                Hashtable attributes = node.getAttributeList();
                ReportObject repObj = getData(attributes);
                if(repObj != null)
                {
                	result.add(repObj);
                }
            }
        }
		
		return result;
	}
	
	private ReportObject getData(Hashtable attrs)
	{
		ReportObject ro = new ReportObject();
		String reportClassName = (String)attrs.get("className");//No Internationalisation
		if(reportClassName == null)
		{
			return null;
		}
		
		ro.setClassName(reportClassName);
		String hourValue = (String)attrs.get("HOUR");//No Internationalisation
		if(hourValue == null || (hourValue != null && hourValue.trim().equals("")))//No Internationalisation
		{
			String weekly = (String)attrs.get("WEEKLY");//No Internationalisation
			String daily = (String)attrs.get("DAILY");//No Internationalisation

			if(weekly != null && weekly.equalsIgnoreCase("true"))//No Internationalisation
			{
				ro.setWeekly(true);
			}
			else if(daily != null && daily.equalsIgnoreCase("true"))//No Internationalisation
			{
				ro.setDaily(true);
			}
		}
		else
		{
			ro.setHour(hourValue);
			String dayofweek = (String)attrs.get("DAY_OF_THE_WEEK");//No Internationalisation
			String dayofmonth= (String)attrs.get("DAY_OF_THE_MONTH");//No Internationalisation

			if(dayofweek != null && !dayofweek.equals("")) //No Internationalisation
			{ 
				ro.setWeekDays(dayofweek);
			}
			else if(dayofmonth != null && !dayofmonth.equals("")) //No Internationalisation
			{ 
				ro.setMonthDays(dayofmonth);
			}
		}
		return ro;
	}

	/*
	 * This method performs the mail settings migration
	 */
	public void migrateMailSettings()
	{
		System.out.println("Starting migration of mail settings from notifications.conf"); //No I18N
		ArrayList<Properties> newPropList = readAndFetchMailDetails();
		PureUtils.rootDir = home;
		MailConfigParser parser = MailConfigParser.getInstance();
		Hashtable<String,Properties> mailList = parser.getAllMailServerDetails();
		for(Properties props : newPropList)
		{
			String name = (String) props.remove("name");
			mailList.put(name+"_smtp",props);		
		}
		
		parser.setMailServerDetails(mailList);
		System.out.println("Completed importing details to mailserver.conf"); //No I18N
		
	}
	
	private ArrayList<Properties> readAndFetchMailDetails()
	{
		String fileName = home+"conf"+File.separator+"notifications.conf"; //No I18N
		ArrayList<Properties> mailSettings = new ArrayList<Properties>();
		
		XMLDataReader reader = new XMLDataReader(fileName,false);
		XMLNode rootNode = reader.getRootNode();
		Vector nodes = rootNode.getChildNodes();
		int size = nodes.size();
		for(int i=0;i<size;i++)
		{
			XMLNode node = (XMLNode)nodes.elementAt(i);
            if(node.getNodeType() == XMLNode.ELEMENT)
            {
                Hashtable attrs = node.getAttributeList();
                String className = (String)attrs.get("className");
                if(className.equals("com.adventnet.nms.eventdb.SendEmail"))
                {
                	Properties props = new Properties();
                	try
                	{
                		props.setProperty("name",(String)attrs.get("name"));
                		props.setProperty("SMTP_SERVER",(String)attrs.get("server"));
                		props.setProperty("USER_NAME",(String)attrs.get("username"));
                		props.setProperty("PASSWORD",(String)attrs.get("password"));
                		if(props.getProperty("USER_NAME").trim().equals(""))
                		{
                			props.setProperty("AUTH","false");
                		}
                		else
                		{
                			props.setProperty("AUTH","true");
                		}
                		props.setProperty("FROM_ADDRESS",(String)attrs.get("fromAddress"));
                		props.setProperty("TO_ADDRESS",(String)attrs.get("toAddress"));
                		props.setProperty("PORT","25");
                		props.setProperty("SMTP_SSL","false");
                		mailSettings.add(props);
                		node.removeAttribute("server");
                		node.removeAttribute("username");
                		node.removeAttribute("password");
                		node.removeAttribute("fromAddress");
                		node.removeAttribute("toAddress");
                		node.setAttribute("smtpAccountName",props.getProperty("name")+"_smtp");

                	}
                	catch(Exception exp)
                	{
                		System.err.println("Incomplete details present for node : "+node); //No I18N
                		continue;
                	}
                }
            }
		}
		
		if(mailSettings.size() > 0)
		{
			new XMLDataWriter(fileName,rootNode);
		}
		
		return mailSettings;
	}
	

}
