/* $Id: MonitorDatabaseDefaultImpl.java,v 1.1.6.1 2013/05/13 13:04:02 venkatramanan Exp $ */
package test;


import java.util.regex.Pattern;
import java.util.regex.Matcher;

import com.adventnet.management.log.Log;
import com.adventnet.nms.util.NmsLogMgr;
import com.adventnet.nms.util.Ping;
import com.adventnet.nms.util.PureServerUtils;
import com.adventnet.nms.admin.MonitorDatabaseInterface;
import com.adventnet.nms.util.DBParamsParser;

public class MonitorDatabaseDefaultImpl implements MonitorDatabaseInterface
{

	private static String host = null;

	private static final String DATABASE = PureServerUtils.getDatabaseName().toLowerCase(); 
	private static Pattern mysqlPattern = Pattern.compile("jdbc:mysql://([a-zA-Z0-9_\\-.]+|)((:(\\d+))|)/([a-zA-Z][a-zA-Z0-9_\\-]*)(\\?.*)?");//No Internationalization
	private static Pattern oraclePattern = Pattern.compile("jdbc:oracle:thin:@([a-zA-Z0-9_\\-.]+|)((:(\\d+))|):([a-zA-Z][a-zA-Z0-9_\\-]*)(\\?.*)?");//No Internationalization

	public void MonitorDatabaseDefaultImpl() throws Exception
	{
		String URL = DBParamsParser.getInstance().getURL();
		host = DBParamsParser.getInstance().getDBHost();
		//parseForHostPort(URL);
	}

	//This is done to initialize the host and port only once.
	//This is the method called to check the reachability of databae. The Implementation class of MonitorDatabaseInterface 
	//should implement this method, and return the boolean based on the reachibility. In this class we make use of the 
	//DBParamsParser and the URL of the location where the the hibernate.cfg.xml is present.
	public boolean dbReachable() throws Exception
	{
		if(host==null)
		{
			host = DBParamsParser.getInstance().getDBHost();
			//String URL = DBParamsParser.getInstance().getURL();
			//parseForHostPort(URL);
		}
		try
		{
			boolean pingResponse = Ping.ping(host);
			NmsLogMgr.HAUSER.log("MonitorDatabaseDefaultImpl  :  database machine "+host+" reachable : "+pingResponse, Log.DEBUG); //No I18N
			return 	pingResponse;
		}
		catch(Exception e)
		{
			//System.err.println("Exception while monitoring database disabling the service");//No Internationalization
			NmsLogMgr.HAERR.log("Exception while monitoring database disabling the service: "+e.getMessage(), Log.SUMMARY);//No Internationalization
			e.printStackTrace();
			throw e;
		}
	}

	// This method can be used to parse for the host and port from the given db url.
	private void parseForHostPort(String url)
	{
		if (DATABASE.equals("mysql")) 
		{
			Matcher matcher = mysqlPattern.matcher(url);
			if (matcher.matches()) 
			{
				if (matcher.group(1) != null && matcher.group(1).length() > 0){
					host=(matcher.group(1));
				}
			}
		}
		else if(DATABASE.equals("oracle"))
		{
			Matcher matcher = oraclePattern.matcher(url);
			if (matcher.matches()) 
			{
				if (matcher.group(1) != null && matcher.group(1).length() > 0) {
					host=(matcher.group(1));
				}
			}			
		}
	}
}

                                                                                                 
