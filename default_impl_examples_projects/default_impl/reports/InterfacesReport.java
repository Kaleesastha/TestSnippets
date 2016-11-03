/*
   $Id: InterfacesReport.java,v 1.5.4.1 2012/04/05 08:54:49 wesley Exp $
 */

/**
 * InterfacesReport.java
 */

package test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;

import com.adventnet.management.log.Log;
import com.adventnet.nms.poll.CollectedData;
import com.adventnet.nms.poll.MultiplePolledData;
import com.adventnet.nms.poll.PollAPI;
import com.adventnet.nms.poll.PolledData;
import com.adventnet.nms.poll.Reporter;
import com.adventnet.nms.server.dataarchiver.ReportIfc;
import com.adventnet.nms.util.NmsLogMgr;
import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.util.PureUtils;

/**
 * This is an example of a report generator for WebNMS.
 * It is scheduled to run daily, past midnight. It is also can be 
 * scheduled to run on weekly basis or hourly basis or particular day on
 * a week or particular day of a month. An entry of this class
 * should be given in the <WebNMS-HOME>/conf/ReportsManagement.xml file.
 * Any number of reports can be run in this way.
 * In this example reports are generated for the PolledData whose
 * name starts with "INTERFACE". The report is stored in reports/daily/
 * directory of WebNMS in interfaces_report<date>.html file.
 */

public class InterfacesReport implements ReportIfc
{

	/*
	 * This method is invoked by the scheduler as per configured in the ReportsManagement.xml file.
	 * @see com.adventnet.nms.server.dataarchiver.ReportIfc#runReport(java.util.ArrayList)
	 */
	public boolean runReport(ArrayList<Properties> propsGroup)
	{
		PollAPI api = (PollAPI) NmsUtil.getAPI("PollAPI");
		Calendar cal = Calendar.getInstance();
		String date = (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DAY_OF_MONTH) + "-" + cal.get(Calendar.YEAR);//No I18N
		runReport(api,date);
		return true;
	}
	
	/** 
	 * This method is used to generate the report.
	 */

	public void  runReport(PollAPI api, String date)
	{
        
		try
		{
			/**
			 * File is created for writing a report in the specified
			 * location then check is made whether we can write to the
			 * file created.
			 */

			String filePath=PureUtils.rootDir+"reports/daily/interfaces_report"+date+".html";        

			File f = new File(filePath);

			if ( (f.exists()) && (!f.canWrite()) )
			{
				System.err.println(NmsUtil.GetString("Cannot open file for writing: reports/daily/interfaces_report")+date+".html");
				return;
			}


			PrintWriter p = new PrintWriter(new FileOutputStream(f));

			p.println( "<HTML><HEAD>" );
			p.println( "<TITLE>Daily Interfaces Report for "+date+"</TITLE>" );
			p.println( "<LINK REL=STYLESHEET TYPE=\"text/css\" HREF=\"../../template/nmshtmlui.css\">" );
			p.println( "</HEAD><BODY>" );
			p.println( "<FONT ID=\"cap\">Daily Interfaces Report for "+date+"</FONT>" );
			p.println( "<B>Note: </B> This report only applies to interfaces that are on systems supporting SNMP.  Not all interfaces support SNMP and not all that support SNMP support the required counters." );
			p.println( "<HR>" );


			/**
			 * Here vector of keys of PolledData is retrieved using the api
			 * and stored in the vector pdList. Then this pdList vector is
			 * enumerated and check is made to find out the PolledData key
			 * whose name starts with "INTERFACE".
			 */


			Vector pdList = api.getCompleteList();

			if (pdList == null)
			{	  
				p.println("Error in accessing  PollAPI." );
			}
			else
			{ 

				Vector v = new Vector();

				for (Enumeration e=pdList.elements();e.hasMoreElements();)
				{
					String key = (String) e.nextElement();
					if (key.startsWith("INTERFACE"))
						v.addElement(key);
				}

				/**
				 * Check whether we have any data to report on and proceed
				 * with report generation if we have any.
				 */

				if (v.size() == 0)
					p.println( "No SNMP interfaces to report on." );
				else
				{ 
					/**
					 * Here the Hashtable 'interf' is created to store the 
					 * ipdaddress and the corresponding key vector of interfaces
					 * for which data has been collected.
					 * The data collected for this interfaces is stored in the
					 * 'stats' Hashtable. The vector 'keyVect' is created to
					 * store the keys of this interfaces.
					 */

					Hashtable interf = new Hashtable(); 
					Hashtable stats = new Hashtable(); 

					Vector keyVect = new Vector();

					/**
					 * Here the vector containing the required keys are 
					 * enumerated and the corresponding PolledData of the
					 * keys are retrieved. Then the collected values for this
					 * PolledData are retrieved. From this the average and the
					 * maximum value of the collected values are found out for
					 * this PolledData for generating reports.
					 */


					for (Enumeration e=v.elements();e.hasMoreElements();)
					{
						String key = (String) e.nextElement();

						StringTokenizer tok = new StringTokenizer(key);
						String statname=tok.nextToken();
						String ipaddr = tok.nextToken();

						PolledData pd = api.getPolledData(key);

						if(pd==null)continue;

						if (!pd.getSave()) continue;

						long max=0, avg =0 ;double dmax = 0 , davg = 0;

						/**
						 * Here the Hashtable 'values' is created to store the
						 * average and maximum values of collected values for
						 * the PolledData with the correponding statistic and
						 * name. The TITLE and LABLE also are stored in this 
						 * for future use during chart generation. The PolledData
						 * is checked for MultiplePolledData, if yes, then the
						 * field 'Mulitple' is also added to this.
						 */


						Hashtable values = new Hashtable();  

						Long[] vals = null;
						Double[] dvals = null;
						if (!pd.getIsMultiplePolledData())
						{
							int num  = pd.getNumericType();
							if(num == 1 || num == 3)
							{
								java.text.DateFormat df = new java.text.SimpleDateFormat("MM-dd-yyyy");//No I18N
								CollectedData cd =api.getCollectedData(pd.getKey(), df.parse(date));

								if (cd == null || cd.getValues()==null)
								{
									continue;
								}

								Object obj = (Object[]) cd.getValues();
								if(obj instanceof Long[])
								{
									vals = (Long[])obj;
								}
								else if(obj instanceof Double[])
								{
									dvals = (Double[])obj;
								} 

								if(vals != null)
								{
								for (int j=0;j<vals.length;j++)
								{
									avg += vals[j];

									if (vals[j] > max)
										max = vals[j];
								}

								if (vals.length>0)
									avg = avg/vals.length;

								int pdperiod =pd.getPeriod();
								avg = avg/pdperiod;
								max=max/pdperiod;
								}
								else if(dvals != null)
								{
									for (int j=0;j<dvals.length;j++)
									{
										davg += dvals[j];

										if (dvals[j] > dmax)
											dmax = dvals[j];
									}

									if (dvals.length>0)
										davg = davg/dvals.length;

									int pdperiod =pd.getPeriod();
									davg = davg/pdperiod;
									dmax=dmax/pdperiod;
								}
							}
						}
						values.put("Statistic",statname);
						if(vals != null)
						{
						values.put("Avg",String.valueOf(avg));
						values.put("Max",String.valueOf(max));
						}
						else if(dvals != null)
						{
							values.put("Avg",String.valueOf(davg));
							values.put("Max",String.valueOf(dmax));
						}

						// Reddy
						//	    values.put("FILE", pd.filename);

						String pdname = pd.getKey();
						pdname = replace(pdname, "\t", "__tab__");
						values.put("name", pdname);
						// Reddy


						values.put("TITLE", statname+"_of_"+ipaddr+ "_for_"+date);

						values.put("LABELS", pd.getOid());

						String multiple ="no";

						if (pd.getIsMultiplePolledData())
						{
							MultiplePolledData mpd = null;
							try
							{
								mpd = (MultiplePolledData) pd;
							}
							catch(Exception ex)
							{
								mpd = new MultiplePolledData();
								mpd.setProperties(pd.getProperties());
								mpd.setId(pd.getId());
							}
							multiple = "yes";
						}

						values.put("MULTIPLE",multiple);

						/**
						 * Here the Hashtable 'values' is put into the 'stats' 
						 * Hashtable with the corresponding key of PolledData.
						 * Then the vector 'keyVect' is checked for key and if
						 * it is null then the PolledData key is added to it.
						 * Then the keyVect and ipaddress are added to the
						 * 'interf' Hashtable.
						 */

						stats.put(key,values);

						keyVect = (Vector) interf.get(ipaddr);

						if (keyVect==null)
							keyVect = new Vector();

						keyVect.addElement(key);

						interf.put(ipaddr,keyVect);

					} // end for each polled data

					p.println( "<TABLE BORDER=\"2\" CELLPADDING=\"4\" CELLSPACING=\"6\">");
					p.println( "<TR ID=\"high\"><TH>Net Interface (SNMP Supported)</TH>"+ "<TH>Statistic Name</TH>"+ "<TH>Average (/sec)</TH>"+ "<TH>Maximum (/sec)</TH></TR>");

					/**
					 * Here the 'interf' Hastable is enumerated to get the Hashtable
					 * which contains the values using the keys. From this Hashtable
					 * the values are retrieved to print the line graph and bar graph
					 * and also the maximum and average of collected values.
					 */

					for (Enumeration e=interf.keys();e.hasMoreElements();)
					{
						String ipad = (String) e.nextElement();
						keyVect = (Vector) interf.get(ipad);

						if ( (keyVect==null)||(keyVect.size()==0)) continue;

						for (Enumeration en =keyVect.elements();en.hasMoreElements();)
						{
							String key1 = (String) en.nextElement();

							Hashtable values = new Hashtable();
							values = (Hashtable) stats.get(key1);

							if (values==null) continue;

							p.println( "<TR><TD>"
									+"<A HREF=\"../../jsp/PollGraphs.jsp?PlotType=PlotBar&"
									//			 +"FILE="+values.get("FILE")
									+"name="+values.get("name")
									+"&DATE="+date
									+"&LABELS="+values.get("LABELS")
									+"&TITLE="+values.get("TITLE")
									+"&MULTIPLE="+values.get("MULTIPLE")
									+"\"><IMG SRC=\"../../images/graph2.png\" "
									+"WIDTH=\"20\" HEIGHT=\"20\" BORDER=\"0\" ALIGN=\"RIGHT\"></A> "

									+"<A HREF=\"../../jsp/PollGraphs.jsp?PlotType=PlotLine&"
									//			 +"FILE="+values.get("FILE")
									+"name="+values.get("name")
									+"&DATE="+date
									+"&LABELS="+values.get("LABELS")
									+"&TITLE="+values.get("TITLE")
									+"&MULTIPLE="+values.get("MULTIPLE")
									+"\"><IMG SRC=\"../../images/graph1.png\" "
									+"WIDTH=\"20\" HEIGHT=\"20\" BORDER=\"0\" ALIGN=\"RIGHT\"></A> "
									+"<B>IF-"+ipad+"</B></TD>"

								+ "<TD>" + values.get("Statistic") +"</TD>"
								+ "<TD>" + values.get("Avg") + "</TD>"
								+ "<TD>" + values.get("Max") + "</TD></TR>");
						}
					}
					p.println( "</TABLE>");
				}
			}
			p.println( "<HR>" );
			p.println( "</BODY></HTML>" );
			p.flush();
			p.close();

		}


		catch(com.adventnet.nms.poll.NmsPollException ne)
		{
                    System.err.println(NmsUtil.GetString("Exception while generating default reports"));//No Internationalisation
                    System.err.println(NmsUtil.GetString("Exception occurred while retrieving data. May be data was not collected for the PolledData and the table for the date: ") + date + " got deleted.");
                    if(NmsLogMgr.MISCERR.getLevel()==Log.DEBUG)
                    {
                        ne.printStackTrace();
                    }
		}


		catch (Exception ex)
		{
                    System.err.println(NmsUtil.GetString("Exception while generating default reports"));//No Internationalisation
                    System.out.println(NmsUtil.GetString("Remote exception: ") + ex.getMessage());
                    if(NmsLogMgr.MISCERR.getLevel()==Log.DEBUG)
                    {
                        ex.printStackTrace();
                    }
		}

	} // end runReport()

	static String replace(String s, String tok, String c)
	{
		if (tok == null)
			return s;

		int i = -1;

		while ((i = s.indexOf(tok)) != -1)
		{
			s = s.substring(0,i) + c + s.substring(i+tok.length());
		}

		return s;

	} // end replace()


}

/** This gets the TopoAPI to access network database **/
/*
   public TopoAPI getTopoAPI() {
   TopoAPI api=null;
   try {
   if (!com.adventnet.nms.startnms.NmsMain.smallNet) {  // if we're multiple JVMs using RMI
   api = (TopoAPI) java.rmi.Naming.lookup("//" +
   java.net.InetAddress.getLocalHost().getHostName() + "/TopoAPI");
   } else api = com.adventnet.nms.topodb.DBServer.topodb;

   } catch (Exception e) {
   NmsUtil.out("Remote exception: " + e.getMessage());
   e.printStackTrace();
   }
   return api;
   } // end  getTopoAPI()


 */
/* This was in the earlier version . It has been changed now
   try {
   ipaddr=(java.net.InetAddress.getByName(ipaddr)).getHostAddress();
   } catch (UnknownHostException uhe) {
   NmsUtil.err("In Interfaces Reports: Unknown host "+ipaddr+" "+uhe);
   }

   String oid = tok.nextToken();
// get Interface object
IpAddress intf = topoapi.getInterface(ipaddr);
if (intf == null) {
NmsUtil.err("Interface Object not in database: "+ipaddr);
continue;
}
PolledData pd = api.getPolledData(key);
Vector data = pd.getData(date);
if (data == null) continue;
long vals[] = (long[]) data.elementAt(1);
long max=0, avg =0;
for (int j=0;j<vals.length;j++) {
avg += vals[j];
if (vals[j] > max) max = vals[j];
}
if (vals.length>0) avg = avg/vals.length;
int pdperiod =pd.period;
if (oid.startsWith("2.2.1.10.")) { // the inOctets
avg = avg/pdperiod;
max=max/pdperiod;
values.put(intf.name+"InAvg", String.valueOf(avg));
values.put(intf.name+"InMax", String.valueOf(max));
} else if (oid.startsWith("2.2.1.13.")) { // the indiscards
values.put(intf.name+"InDisAvg", String.valueOf(avg));
values.put(intf.name+"InDisMax", String.valueOf(max));
} else if (oid.startsWith("2.2.1.16.")) { // the outOctets
	avg = avg/pdperiod;
	max=max/pdperiod;
	values.put(intf.name+"OutAvg", String.valueOf(avg));
	values.put(intf.name+"OutMax", String.valueOf(max));
} else if (oid.startsWith("2.2.1.19.")) { // the out Discards
	values.put(intf.name+"OutDisAvg", String.valueOf(avg));
	values.put(intf.name+"OutDisMax", String.valueOf(max));
}

if (!interfaces.contains(intf.name))
	interfaces.addElement(intf.name);
	*/

