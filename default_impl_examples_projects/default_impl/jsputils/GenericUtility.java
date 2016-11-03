/*
$Id: GenericUtility.java,v 1.2 2008/09/11 14:49:01 govardhini Exp $
*/
package com.adventnet.nms.util;
import java.io.*;
import java.rmi.*;
import java.util.*;
import java.text.*;
import java.awt.Color;
import com.adventnet.nms.util.NmsLogMgr;
import com.adventnet.management.log.Log;
import com.adventnet.nms.startnms.NmsMainFE;
import com.adventnet.nms.mapdb.MapAPI;
import com.adventnet.nms.poll.PollAPI;
import com.adventnet.nms.topodb.TopoAPI;
import com.adventnet.nms.eventdb.EventAPI;
import com.adventnet.nms.alertdb.AlertAPI;
import com.adventnet.nms.fe.map.MapFE;
import com.adventnet.nms.fe.perf.PollFE;
import com.adventnet.nms.fe.topo.TopoFE;
import com.adventnet.nms.fe.event.EventFE;
import com.adventnet.nms.fe.alert.AlertFE;
import com.adventnet.ldap.LdapConfReader;
import com.adventnet.management.authorization.ConfirmAuth;
import com.adventnet.ldap.GetPermission;
import com.adventnet.nms.severity.SeverityInfo;
import com.adventnet.nms.util.PureUtils;
import com.adventnet.nms.util.ClientParameters;
import com.adventnet.nms.util.XMLDataReader;
import com.adventnet.nms.util.XMLNode;
import com.adventnet.nms.util.CommonUtil;
import com.adventnet.security.authorization.AuthorizationEngine;
import com.adventnet.security.authorization.AuthorizationException;
import com.adventnet.nms.commonbe.*;
import com.adventnet.nms.fe.security.authorization.AuthObserverImpl;
import com.adventnet.security.audit.AuditAPI;

/**Used by many files to access comman methods and static variables .
 */
public class GenericUtility
{
  static TopoAPI topoAPI   = null;
  static AlertAPI alertAPI = null;
  static EventAPI eventAPI = null;
  static PollAPI pollAPI   = null;
  static MapAPI mapAPI     = null;
  /**Used to inform that user is unAuthorized to perform the task.
   */
  /** returns the dataFormat of Web NMS */

  public static  SimpleDateFormat NmsFormatter = new SimpleDateFormat ("MMM dd,yyyy hh:mm:ss a");
  /**
   * Stores the Offset of the time zone. 
   */
  public static int offset=0;

  public static long dateDiff = 0;

  /**
   * Contains the error message.
   */
  public static final String unAuthorizedString = "Sorry ! You are not authorized to perform this operation";
  /**Used as line seperator.
   */
  public static final String seperator = "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
  /**Used to store login time.
   */
  public static Hashtable KeyForCvcVSloginTime = new Hashtable();
  /**Used for Authentication confirmation.
   */
  private static ConfirmAuth conAuth;
  /**Used to verify whether used first time or not.
   */
  private static boolean firstTime = true;   

  /**Used as generator of random numbers for use in HTML UI.
   */
  public static Random generator =new Random ();

  /** Stores the help urls' and keys read from conf/help.conf*/
  private static Properties help = null;

    private static AuthObserverImpl authObs=null;
    private static AuditAPI auditAPI = null;

  /**This gets the TopoAPI for use by jspdata and servlets .
   * @return TopoAPI object
   */
  public static TopoAPI getTopoAPI() 
  {
	if(topoAPI != null)
	  return topoAPI;
	topoAPI = (TopoAPI)TopoFE.getTopoAPI();
	return topoAPI;
  } 

  /**This gets the EventAPI for use by jspdata and servlets.
   * @return EventAPI object.
   */
  public static EventAPI getEventAPI() 
  {
	if(eventAPI != null)
	  return eventAPI;
	eventAPI = (EventAPI)EventFE.getEventAPI();;
	return eventAPI;
  }
  /**This gets the AlertAPI for use by jspdata and servlets.
   * @return AlertAPI object.
   */
  public static AlertAPI getAlertAPI() 
  {
	alertAPI = (AlertAPI)AlertFE.getAlertAPI();
	return alertAPI;
  } 
  /**This gets the MapAPI for use by jspdata and servlets.
   * @return MapAPI object.
   */
  public static MapAPI getMapAPI() 
  {
	if(mapAPI != null)
	  return mapAPI;        
	mapAPI = (MapAPI)MapFE.getMapAPI();
	return mapAPI;
  } // end  getMapAPI()


  /**This gets the PollAPI for use by jspdata and servlets.
   * @return PollAPI object.
   */
  public static PollAPI getPollAPI() 
  {
	if(pollAPI != null)
	  return pollAPI;
	pollAPI = PollFE.getPollAPI();
	return pollAPI;
  } // end  getPollAPI()

  /**Used for setting colors based on severity.
   * 
   * @param col The string representing the color.
   * 
   * @return Color object.
   */
  public static java.awt.Color getColor(String col) 
  {
	int sev=-1;
	try 
	{
	  sev = Integer.parseInt(col);
	  if (SeverityInfo.getInstance().contains(sev))
	  {
		Color color = SeverityInfo.getInstance().getColor(sev);
		return color;
	  }
	}
	catch(NumberFormatException e)
	{
	  Color color = SeverityInfo.getInstance().getColor(col);
	  return color;
	}
	return null;
  }

  /**Used to get the HTML string equivalent of the given Color object.
   * 
   * @param col Color Object.
   * 
   * @return String giving the RGB numbers.
   */
  public static String getHtmlColor(java.awt.Color col)
  {
	return (col==null) ? null : Integer.toHexString(col.getRGB()).substring(2);
	//we do a substring to omit the string representing the alpha component - rraj.
	//getRGB() returns the RGB value representing the color in the default sRGB ColorModel. (Bits 24-31 are alpha, 16-23 are red, 8-15 are green, 0-7 are blue).
  }

  /** 
   * formats the date which is read from ClientParameters.conf.
   *
   * @param	time	time in long
   * @param dateFormat	SimpleDateFormat object
   * @return	String in date/time format.
   */
  public static String formatDate (long time,SimpleDateFormat dateFormat)
  {
	Date date = new Date (time);

	if (date == null)
	{
	  return "Invalid Date";
	}
	return formatDate(date,dateFormat);
  }

  /** 
   * formats the date which is read from ClientParameters.conf.
   *
   * @param		date		Date Object
   * @param		dateFormat	SimpleDateFormat
   * @return	String in date/time format.
   */
  public static String formatDate (Date date,SimpleDateFormat dateFormat)
  {
	if (date == null)
	{
	  return "Invalid Date";
	}
	String s = null;
	//DateFormat df = NmsFormatter=dateFormat;
	/*TimeZone tz=TimeZone.getDefault();

	//    offset=Integer.parseInt(timeZone);
	if(tz != null)
	{
	  //  tz.setRawOffset(offset);
	  df.setTimeZone(tz);
	}
	*/
	s = dateFormat.format (date);
	return s;
  }

  /**
   * parses the given string and returns the equivalent date 
   * @param str a string
   * @return Date 
   * @exception ParseException :Signals that an error has been reached unexpectedly while parsing
   * @deprecated use {@link formatDate(long time,SimpleDateFormat dateFormat)} instead.
   */ 
  public static Date parseDate (String str) throws ParseException
  {
	  if(true)
		  return new Date();

	  DateFormat df = NmsFormatter;
	  /*
		 if(tz != null)
		 {
		 tz.setRawOffset(offset);
		 df.setTimeZone(tz);
		 }
	   */
	  Date dt = df.parse (str);
	  return dt;
  }

  /**
   * <code>getParsedTime:</code>To parse a date string and returns the number of milliseconds 
   * @param dt a <code>Date</code> value
   * @return a <code>long</code> value - number of milliseconds 
   */
  public static long getParsedTime (Date dt)
  {
	return (dt.getTime () - dateDiff);
  }

  /**need to deprecate this method. Use SeverityInfo.getInstance().getColor(sev) instead
   * 
   * @param sev The number representing the severity.
   * 
   * @return String The name equvalent of the severity number.
   */
  public static String getStatusColor(int sev)
  {
	String sevName = SeverityInfo.getInstance().getName(sev);
	if (sevName == null)
	  sevName = SeverityInfo.getInstance().getName(SeverityInfo.getInstance().getUnknown());
	return sevName;
  } 

  /** Used to get the user readable String format of the given date in long.
   * 
   * @param date The date in long.
   * 
   * @return String The user readable date format.
   */
  public static String dateString(long date)
  { 
	Date d = new Date();
	d.setTime(date);
	Calendar cal = Calendar.getInstance();
	cal.setTime(d);
	String s = null;
	s = NmsUtil.NmsFormatter.format(d);
	return s;
  }

  /**This is used for cleanup of Custom Views Vector 
   * during regular period of time ("1800000" in long).
   * 
   * @param ht The Hashtable which contains the login time.
   * 
   * @return Cleaned Vector object.
   */
  public static Vector cleanUp(Hashtable ht)
  {      
	Date date = new Date();
	Vector vec = new Vector();
	for(Enumeration en = ht.keys();en.hasMoreElements();)
	{
	  String key = (String)en.nextElement();
	  long diff = date.getTime() - ((Date)(ht.get(key))).getTime();
	  if(diff >= Long.parseLong("1800000"))
	  {
		vec.addElement(key);
		ht.remove(key);
		NmsUtil.current_keys.removeElement(key);
	  }
	}
	return vec;
  }
  /** To replace a particular string with a character.
   * 
   * @param s The String to be replaced.
   * @param tok The tokenizer.
   * @param c The character to be replaced instead of the string. 
   * 
   * @return String The String which has the required changes.
   */
  public static String replace(String s, String tok, String c)
  {
	if (tok == null) return s;
	int i = -1;
	while ((i = s.indexOf(tok)) != -1)
	{
	  s = s.substring(0,i) + c + s.substring(i+tok.length());
	}
	return s;
  } // end replace()

  /**Used to provide the Hashtable containing the incrementation values 
   * from conf file for a given user.
   * 
   * @param userName The name of the user for whom the incrementation values 
   * have to be known
   * 
   * @return Hashtable containing the incrementation values.
   */
  public static Hashtable populateIncrementsVector(String userName)
  {
	Hashtable incrementsHash = new Hashtable();
	InputStream is = null;
	BufferedReader d = null;
	try
	{
	  //DataInputStream is =  new DataInputStream(new FileInputStream(PureUtils.usersDir +"users/" + userName + "/increments.conf"));

	  if (!NmsUtil.createUserDir) //No I18N
	  {

	  	is =  CommonUtil.openFile(new File(PureUtils.usersDir +"html"+File.separator+"defaultsToAllUsers"+File.separator+"increments.conf")); //No I18N
	  }
	  else
          {
	  	is =  CommonUtil.openFile(new File(PureUtils.usersDir +"users"+File.separator + userName + File.separator +"increments.conf")); //No I18N
	  }
	  d = new BufferedReader(new InputStreamReader(is));
	  String s;
	  while ((s = d.readLine()) != null)
	  {
		  if(s.trim().equals("") || s.startsWith("#"))
		  {
			  continue;
		  }
		StringTokenizer tok = new StringTokenizer(s);
		String key = tok.nextToken();
		tok.nextToken(); // Just to skip the colon.
		Vector increments = new Vector();
		while (tok.hasMoreTokens())
		{
		  String next = tok.nextToken();
		  try
		  {
			Integer.parseInt(next);
			increments.addElement(next);
		  }
		  catch (NumberFormatException ex) 
		  {
			System.err.println("Invalid increment value " + s + " in increments.conf");
		  }
		}
		if(increments.size() == 0)
		{
		  for(int i = 1;i<6;i++)
		  {
			increments.addElement(String.valueOf(i*10));//put 10,20,...50 in case nothing is specifed in file.
		  }
		}
		incrementsHash.put(key, increments);
	  }
	}
	catch(IOException e)
	{
	  NmsLogMgr.MISCERR.fail("Exception reading increments.conf" + e,null);
	}
	finally{
		try{is.close(); is = null;} catch(Exception exp){}
		try{d.close(); d = null;} catch(Exception exp){}
	}
	return incrementsHash;
  }
  /**To check if the given userName is an authorized userName.
   * 
   * @param	userName	name to be checked. 
   * @param	dn			Distinguished name.
   * 
   * @return boolean whether autherized or not.
   */
  public static boolean isAuthorized_userName(String userName, String dn)
  {    
	if(NmsMainFE.singleJVM)
	{
	  if(NmsUtil.getConfirmAuth() != null)
	  {
		if(LdapConfReader.LDAP_STATUS.equalsIgnoreCase("YES"))
		  return NmsUtil.getConfirmAuth().isUserAuthorised(userName,dn);
	  }
	  else
		return true; // Not sure what to return in this case
	  return true;    
	}
	else
	{
	  if(conAuth == null)
	  {
		if(firstTime)
		{
		  initialize();
		  firstTime = false;
		}
	  }
	  if(conAuth != null)
	  {
		return conAuth.isUserAuthorised(userName,dn);
	  }

	  return true; //again not sure what to return
	}            
  }



  /**
   * This method is used to get a List of operations authorized for 
   * this specified user name.
   * @param userName the name of the user for which the operations
   * list is to be retrived.
   * @return A Vector which contains a list of operations authorized
   * for the specified user name.
   */ 


  public static Vector getOperationsForUser(String userName) {

	Vector result= new Vector();
	AuthorizationEngine authEngine = null;
	try{
	  authEngine = (AuthorizationEngine) NmsUtil.getAPI("NmsAuthEngineAPI");
	}catch(Exception e){
	  NmsLogMgr.MISCERR.fail("Exception while getting ref. to AuthorizationEngine",e);
	}
	if(authEngine != null){
	  try{
		result = authEngine.getOperationsForUser(userName);
	  }catch(AuthorizationException e){
		NmsLogMgr.MISCERR.fail("Exception while getoperationsforuser",e);
	  }catch(Exception e){
		NmsLogMgr.MISCERR.fail("Exception while getoperationsforuser",e);
	  }
	} else {
	  System.err.println("Cannot get reference to AuthorizationEngine !!");
	}
	return result;
  }




  //for initialization.






  private static void initialize()
  {
	LdapConfReader.readTheConfFile();

	if(LdapConfReader.LDAP_STATUS.equalsIgnoreCase("YES"))

	  conAuth = (ConfirmAuth)(new GetPermission());
  }
  /**
   * Used to get the absolute URL
   *
   * @param	menuName	the name of the menu
   * @param	userName 	the name of the user
   * @return	a String containing the file path
   * @throws	IOException
   **/

  public static String getFilePath(String menuName,String userName)throws IOException
  {
	File file=null;
	String url=PureUtils.usersDir+"users"+File.separator+userName+File.separator+menuName+".xml";
	try
	{
	  file=new File(url);
	  if(!file.exists())
	  {
		url=PureUtils.rootDir+"html"+File.separator+"defaultsToNewUsers"+File.separator+menuName+".xml";
	  }
	}catch(Exception e)
	{
	  e.printStackTrace();	
	}

	return url;
  }

  /**To check if the given Custom View Name is unique in the level in which it is to be added.
   * 
   * @param cvName  The custom view name that is to checked.
   * @param parentId  The ID of the XML node under which the custom view is to added.
   * @param keyForCVC  The key used by Custom View Controller i.e. the Session ID.
   * @param userName  The User who adds the custom view.
   * 
   * @return boolean Whether the Custom View Name is unique in the level in which it is to be added.
   public static boolean isCustomViewNameUnique(String cvName, String parentId, String keyForCVC, String userName)
   {
   XMLNode cvNode;
  //Changed here from AlertFE to TopoFE.. These sort of methods will no longer be used in the
  //2.3 release. Just for making things work in the phase I build this change has been done!!
  cvNode=CustomViewAPI.getRootXMLNodeForUser(keyForCVC,userName,TopoFE.cvc);
  XMLDataReader dataReader = new XMLDataReader();
  cvNode = dataReader.searchNodeForId(parentId,cvNode);
  if(cvNode!=null)
  {
  Enumeration e = cvNode.children();
  for( ;e.hasMoreElements(); )
  {
  cvNode = (XMLNode)e.nextElement();
  if(cvNode.getAttribute("TREE-NAME").toString().equals(cvName))
  {
  return false;
  }
  }
  }
  return true;
  }
   */

  /**To replace all spaces in a string with <xmp>%20</xmp>  
   *	- used for encoding the URL names with space in between
   * 
   * @param name  The string to be to be changed
   * 
   * @return String The encoded string with all spaces replaced by <xmp>%20</xmp>
   */
  public static String encode(String name)
  {
	return replace(name," ","%20");
  }
 
  private static Hashtable tbl=null;
  /** To get the date format with timezone set.The timezone to be used is read 
   * from the clientparameters.conf file 
   * 
   * @param userName  The string gives the user 
   * 
   * @return DateFormat The date format with timezone set.
   */
  public static DateFormat getDateFormat(String userName)
  {
	  ClientParameters clientParams = null;
	  if(tbl == null)
	  {
		  tbl = new Hashtable();
		  clientParams = new ClientParameters();
		  clientParams.readClientParameters(tbl);
	  }

	  Hashtable userParams=new Hashtable();
	  clientParams = new ClientParameters(userName);
	  clientParams.readClientParameters(userParams);

	  Enumeration keys = tbl.keys();
	  while(keys.hasMoreElements())
	  {
		  Object key = keys.nextElement();
		  if(!userParams.containsKey(key))
		  {
			  userParams.put(key,tbl.get(key));
		  }
	  }

	  String tmp=(String)userParams.get("DATE_FORMAT");
	  if(tmp==null) {
		  NmsLogMgr.MISCUSER.log("DATE_FORMAT from conf file is null, So using default date format MMM dd,yyyy hh:mm:ss ",Log.VERBOSE);

		  tmp="MMM dd,yyyy hh:mm:ss a";
	  }
	  SimpleDateFormat dateFormat = new SimpleDateFormat (tmp);
	  //timezone from clientparameters.conf
	  String timezone_preference=(String)userParams.get("TIMEZONE_PREFERENCE");
	  TimeZone tz=TimeZone.getDefault();

	  if(timezone_preference!=null && timezone_preference.equals("BE"))
	  {
		  BEServerContext svrCntxt=PureServerUtilsFE.getBEServerContext();
		  Properties prop=svrCntxt.getProperties();
		  String beTimeZoneID=(String)prop.get("TimeZoneID");
		  tz=tz.getTimeZone(beTimeZoneID);
	  }
	  dateFormat.setTimeZone(tz);
	  tmp=null;
	  return dateFormat;
  }
    /**
     * Used to check whether the given user is authorized to perform the specified operation
	 * @param userName the user name which you want to know the whether the operation is permitted
	 * @param operation the operation for which you need to know the whether the user is allowed to do.
	 * @return boolean Returns true if the given user is authorized to do the specified operation, 
     * else returns false
     */
    public static boolean isAuthorized(String userName,String operation) 
    {
        if (userName == null || userName.trim().equals("") || userName.equals("null") || operation == null || operation.trim().equals("") || operation.equals("null")) 
        {
            return false;
        }

        try
        {
            if (auditAPI == null)
            {
                auditAPI = (AuditAPI) NmsUtil.getAPI("NmsAuditAPI");//No Internationalization
            }

            if(authObs==null)
            {
                authObs=AuthObserverImpl.getInstance();
            }

            boolean result = authObs.checkForOperation(userName,operation);
            audit(userName, operation, result);
            return result;
        }
        catch(Exception e)
        {
            NmsLogMgr.MISCERR.fail("Exception while doing authorization check for user "+userName+"\n",e);//No Internationalization
            audit(userName, operation, false);
            return false;
        }

        //Commented by Balan
        /*
           boolean status = false;
           AuthorizationEngine authEngine = null;
           try{
           authEngine = (AuthorizationEngine) NmsUtil.getAPI("NmsAuthEngineAPI");
           }catch(Exception e){
           NmsLogMgr.MISCERR.fail("Exception while getting ref. to AuthorizationEngine",e);
           }
           if(authEngine != null){
           try{
           status = authEngine.isAuthorized(userName, operation);
           }catch(AuthorizationException e){
           NmsLogMgr.MISCERR.fail("Exception while checking for authorization",e);
           }catch(Exception e){
           NmsLogMgr.MISCERR.fail("Exception while checking for authorization",e);
           }
           } else {
           System.err.println("Cannot get reference to AuthorizationEngine !!");
           }
           return status;
         */
        //Comment Ends
    }

 public static boolean isAuthorized(String userName,String operation,boolean log) 
    {
        if (userName == null || userName.trim().equals("") || userName.equals("null") || operation == null || operation.trim().equals("") || operation.equals("null")) 
            {
                return false;
            }

        try
            {
                if (auditAPI == null)
                    {
                        auditAPI = (AuditAPI) NmsUtil.getAPI("NmsAuditAPI");//No Internationalization
                    }

                if(authObs==null)
                    {
                        authObs=AuthObserverImpl.getInstance();
                    }

                boolean result = authObs.checkForOperation(userName,operation);
                if(log)
                    {
                        audit(userName, operation, result);
                    }
                return result;
            }
        catch(Exception e)
            {
                NmsLogMgr.MISCERR.fail("Exception while doing authorization check for user "+userName+"\n",e);//No Internationalization
                if(log)
                    {
                        audit(userName, operation, false);
                    }
		
                return false;
            }
    }
		
        
    	
    public static boolean isAuthorized(String userName,String operation, Properties auditProp) 
    {
        return isAuthorized(userName, operation, auditProp, true);
    }

    public static boolean isAuthorized(String userName,String operation, Properties auditProp, boolean log) 
    {
        boolean result = isAuthorized(userName, operation, false);
        if(log)
            {
                if(auditProp == null)
                    {
                        auditProp = new Properties();
                    }
                auditProp.setProperty("operation", operation); //No Internationalization
                auditProp.setProperty("status",(result ? "SUCCESS" : "FAILURE")); //No Internationalization
                audit(userName, auditProp);
            }
        return result;
    }
    
    
    private static synchronized void audit(String userName, String operationName, boolean status)
    {
        Properties auditProp = new Properties();
        auditProp.put("operation",operationName);//No Internationalisation
        auditProp.put("status",(status ? "SUCCESS" : "FAILURE"));//No Internationalisation
        audit(userName, auditProp);
    }

    private static synchronized void audit(String userName, Properties auditProp)
    {
        try
            {
                if (auditAPI != null)
                    {
                        auditAPI.audit(userName, auditProp);
                    }
            }
        catch (Exception ae)
            {
                NmsLogMgr.MISCERR.fail("Exception while auditing in GenericUtility", ae);//No Internationalization
                ae.printStackTrace();
            }
    }
    /*
    private static synchronized void audit(String userName, String operationName, boolean status)
    {
        if (auditAPI != null)
        {
            Properties auditProp = new Properties();
            try
            {
                auditProp.put("OPERATION",operationName);//No Internationalisation
                auditProp.put("STATUS",(status ? "SUCCESS" : "FAILURE"));//No Internationalisation

                auditAPI.audit(userName, auditProp);
            }
            catch (Exception ae)
            {
                NmsLogMgr.MISCERR.fail("Exception while auditing in GenericUtility", ae);//No Internationalization
                ae.printStackTrace();
            }
        }
    }
    */
  /**
	Reads the conf/help.conf file and sets all the urls' and their keys in a java.util.Properties object
   */
  private static void readHelpConf () {

	help = new Properties();
	String url = PureUtils.rootDir+"conf/help.conf";
	try{
	  XMLDataReader xmlDataReader =  new XMLDataReader(url,false);
	  Vector nodes = xmlDataReader.getRootChildNodes(false);
	  Vector node  = ((XMLNode)nodes.elementAt(0)).getChildNodes();
	  int size = node.size();
	  for(int i=0;i<size;i++)
	  {
		XMLNode helpNode = (XMLNode) node.elementAt(i);
		if( helpNode.getNodeType() == XMLNode.COMMENT ){
		  continue;
		}
		Hashtable xml = helpNode.getAttributeList();
		String key = (String)xml.get("key");
		String value = (String)xml.get("value");
		help.setProperty(key,value);

	  } 
	}catch(Exception e) {
	  NmsLogMgr.MISCERR.fail("Exception while reading conf/help.conf",e);
	}
  }

  /** 
	Returns the help URL when the key is given. The helpURL is read from conf/help.conf
	@param key the key for which you want the help URL
	@return Returns help URL whose key is given
   **/
  public static String getHelpURL(String key) {
	if(help == null){
	  readHelpConf();
	}
	return help.getProperty(key);
  }
}
