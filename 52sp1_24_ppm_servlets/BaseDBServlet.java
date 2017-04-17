/*
$Id: BaseDBServlet.java,v 1.1 2006/08/29 13:57:03 build Exp $
*/
package com.adventnet.nms.servlets;

import java.io.*;
import java.util.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.lang.reflect.*;
import java.net.URLEncoder;
import com.adventnet.nms.util.*;
import com.adventnet.nms.store.relational.*;
import com.adventnet.nms.commonfe.GenericFEAPIImpl;
import com.adventnet.nms.servlets.AuthenticationServlet;
/**
 *  This servlet is a base for servlets that collect data from the database.
 *  It stores and initializes some commonly used parameters.
 *  
 *  
 */

public class BaseDBServlet extends AuthenticationServlet
{

    /** The properties passed from JSP file **/
    Properties parameters;

    /** The properties passed from JSP file **/
    Hashtable paramTable;	
    /** The columns to be displayed **/
    Vector desiredCols = null;
    /** The types of the columns to be displayed **/
    Vector columnType = null;

    /** The maximum number of rows to display **/
    int MAX_COUNT = 1000;

    /** The table header passed from JSP file **/
    String tableHeader = null;
    /** The table row template passed from JSP file **/
    String template = null;
    /**  The SQL Query string passed from JSP file **/
    String sqlQuery = null;
    /**  The SQL Query string passed from JSP file **/
    String table = null;
		
    
    /** The relational API instance used by all servlets **/
    static RelationalAPI relapi = null;

    /** url a database url of the form jdbc:subprotocol:DataSourceName*/
    String url = "jdbc:odbc:WebNmsDB";
    
    /** login username for database*/
    String userName = "admin";
    
    /** login password for database*/
    String password = "public";
    
    /** Database Driver name for eg sun.jdbc.odbc.JdbcOdbcDriver*/
    String driverName = "sun.jdbc.odbc.JdbcOdbcDriver"; 
  
    PrintWriter out = null;



    /**
     * Initialize Servlet.
     *
     * @param config servlet configuration, stored by superclass
     * @exception ServletException passed on from superclass
     */
    
    public void init(ServletConfig config) throws ServletException
    {        
        super.init(config);
    }  
    

    /**
     * Handle POST the same as GET.
     * This method is simply a call to doGet().
     *
     * @param req encapsulates the request to the servlet
     * @param resp encapsulates the response from the servlet
     * @see javax.servlet.http.HttpServletRequest#getPathTranslated
     * @exception ServletException will be passed on from included servlets
     * @see #doGet
     */
    
    public void doPost(HttpServletRequest req, HttpServletResponse res)
	 throws ServletException, IOException 
    {
        doGet(req, res);
    }

    
    /**
     * Initialize the parameters, print nothing, and return.
     *
     * @param req encapsulates the request to the servlet
     * @param resp encapsulates the response from the servlet
     * @see javax.servlet.http.HttpServletRequest#getPathTranslated
     * @exception ServletException will be passed on from included servlets
     * @see #doGet
     */
    
    public synchronized void doGet(HttpServletRequest req, HttpServletResponse res)
	 throws ServletException, IOException 
    {
	paramTable=(Hashtable)req.getAttribute("parameters");
	out = res.getWriter();;
	getParameters(req);
        setMaxCount();

        String cols = parameters.getProperty("tableColumns");
        if(cols != null) {
	    desiredCols = new Vector();
	    StringTokenizer st = new StringTokenizer(cols);
	    while (st.hasMoreTokens()) desiredCols.addElement(st.nextToken());
	} else errorPage("Table columns unspecified. ", req, res);
		
        cols = parameters.getProperty("COLUMN-TYPE");
	columnType = new Vector();
        if(cols != null) {
	    StringTokenizer st = new StringTokenizer(cols);
	    while (st.hasMoreTokens()) columnType.addElement(st.nextToken());
	} else errorPage("Table column types unspecified. ", req, res);

        tableHeader = parameters.getProperty("tableHeader");
        template = parameters.getProperty("template");
        table = parameters.getProperty("table");
	
	sqlQuery = parameters.getProperty("SQL-QUERY");

	if (relapi == null) relapi = NmsUtil.relapi;
	if (relapi == null) try {
	    System.err.println("DB Servlet: NmsUtil.relapi null. Use direct");
        String url = PureServerUtils.url;
        String userName = PureServerUtils.userName;
        String password = PureServerUtils.password;
        String driverName = PureServerUtils.driverName;
	    RelationalAPI relapi = 
		new RelationalAPI(url, userName, password, driverName, true);
	    //relapi.connect();

	} catch (Exception ex) {
	    errorPage("Cannot connect to SQL database: ", req,res);
	}

    }
    

    /** Get the SQL query string portion for the order by clause 
      if SORT_COLUMN or SORT_COLUMN_DESC is specified **/
    String getOrderByString()
    {
	String sql="";
	String s;

	s = parameters.getProperty("SORT_COLUMN");
	if ( (s!=null) && (!s.trim().equals("")) && (!s.equalsIgnoreCase("all"))) 
	    {
		sql=sql+ " order by " + s;
	    }
	else
	    {
		s = parameters.getProperty("SORT_COLUMN_DESC");
		if ( (s!=null) && (!s.trim().equals("")) && (!s.equalsIgnoreCase("all")))
		    {
			sql=sql+ " order by " + s + " desc";
		    }
	    }
		
	return sql;
    }
	

    /** Get the SQL query string portion for queries on any table column  **/
    String getTagString()
    {
	Properties prop=new Properties();
	String sql="";
	String time1= "";
	String time2= "";
		
	String s;
	String ts = parameters.getProperty("time1");
	String ts2 = parameters.getProperty("time2");
	if ( (ts != null) && !ts.trim().equals("") ) {
	    try {
            //java.util.Date dt=NmsClientUtil.parseDate(ts);//Client dependency ...
            java.util.Date dt = GenericUtility.parseDate(ts);            
            time1 = String.valueOf(GenericUtility.getParsedTime(dt));
	    } catch (Exception ile) {
		GenericFEAPIImpl.log(NmsUtil.GetString("Invalid Date Value") + " " + ts + " - " + NmsUtil.GetString("Use the Pattern")+NmsUtil.GetString(GenericUtility.NmsFormatter.format(new java.util.Date())) );
	    }
	} else time1="";
		
	if ((ts2 != null) && !ts2.trim().equals("")) {
	    try {
            //java.util.Date dt=NmsClientUtil.parseDate(ts2); //Client Dependency...            
            java.util.Date dt=GenericUtility.parseDate(ts2);            
            time2 = String.valueOf(GenericUtility.getParsedTime(dt));
	    } catch (Exception ile) {
		GenericFEAPIImpl.log(NmsUtil.GetString("Invalid Date Value") + " " + ts2 + " - " + NmsUtil.GetString("Use the Pattern")+NmsUtil.GetString(GenericUtility.NmsFormatter.format(new java.util.Date())) );
	    }
	}  else  time2="";
		
	if ( (!time1.equals("")) || (!time2.equals("")) ) 
		sql=sql+returnSQLfortime("rowcreatetime",time1,time2);
		
	for(int i=0;i<desiredCols.size();i++) {
	    s = parameters.getProperty((String)desiredCols.elementAt(i));
	    if ( (s!=null) && (!s.trim().equals("")) && (!s.equalsIgnoreCase("all"))) {
		sql=sql+ checkTypeAndReturnSQL((String)desiredCols.elementAt(i),s,(String)columnType.elementAt(i));
	    }
	}
		
	return sql;
    }


    /**  Get the SQL query string portion for queries based on time.   **/
    public String returnSQLfortime(String columnName,String lowerLimit,
				   String upperLimit)
    {
	String temp=" AND ("+columnName+" BETWEEN "+lowerLimit+
	    " AND "+upperLimit+")";
	return temp;
    }
    

    /** Get the SQL query string portion for queries based on type  **/
    String checkTypeAndReturnSQL(String columnName,String value,String columnType)
    {
	if((columnType.equals("Counter")) || (columnType.equals("Integer")))
	    {
		return SQLforrange(columnName,value);
	    }
	else if(columnType.equals("String"))
	    {
		return returnSQL(columnName,value);
	    }
	return "";
    }
	
	
    /** Get the SQL query string portion for queries for specific values  **/
    public String returnSQL(String columnName,String value)
    {
	String template="";
	if (value!=null || !(value.trim().equals("")))
	    template="AND ("+columnName+" LIKE '%"+value+"%')";
	return template;
    }
	

    /** Get the SQL query string portion for queries based on counter range **/
    public String SQLforrange(String columnName,String value)
    {
	String template="";
	StringTokenizer main=new StringTokenizer(value.trim(),",");
	String stt;
	while(main.hasMoreTokens())
	    {
		stt=main.nextToken();
		int index=-1;
		StringTokenizer st=new StringTokenizer(stt,"-");
		if (st.countTokens()!=2) {
		    template=template+" AND ("+columnName+" > "+st.nextToken().trim()+" )";
		    continue;
		}
		else
		    {
			String st1=st.nextToken().trim();
			String st2=st.nextToken().trim();
			int lowerLimit,upperLimit;
			try
			    {
				lowerLimit=Integer.parseInt(st1);
				upperLimit=Integer.parseInt(st2);
			    }
			catch(NumberFormatException nfe)
			    {
				continue;
			    }
			template= template+" AND ("+columnName+" BETWEEN "+lowerLimit+" AND "+upperLimit+")";
		    }
	    }
	return template;
    }
  

    /** Get the parameters passed to teh servlet.  
      Place in parameters object **/
    void getParameters(HttpServletRequest req) {
	//create the proprties object which would have all the 
        // parameters that came along with the request stream.        
        parameters = new Properties();


        //populate the properties.
        for(Enumeration parameterNames = req.getParameterNames();
            parameterNames.hasMoreElements();)
	    {
		String param = (String)parameterNames.nextElement();
		String value = (String)req.getParameter(param);
            
		if (value == null) value = "-";
	       
		parameters.put(param,value);
	    }
	if(paramTable!=null)
	{	
	for(Enumeration parameternames = paramTable.keys();
            parameternames.hasMoreElements();)
	    {
		String param = (String)parameternames.nextElement();
		String value = (String)paramTable.get(param);
            
		if (value == null) value = "-";
	       
		parameters.put(param,value);
	    }
	}


    }
    

    /** Set the maximim count of number of rows to display.  **/
    void setMaxCount() {
	String maxcount = parameters.getProperty("MAX_COUNT");
	if (maxcount!=null) try {
	    MAX_COUNT= Integer.parseInt(maxcount);
	} catch (Exception ex) {
	    System.err.println("RmonTableServlet MAX_COUNT invalid in ssi: "+
			       maxcount + ": " + ex);
	}
    }
	

    /** Generate an error page and throw an exception - 
      quick exit for errors.  **/
    void errorPage(String s, HttpServletRequest req, HttpServletResponse res) 
	 
    {
        out.println(s);
    }
    
    /**  This gets a property value - replaces nulls with empty strings.  **/
    public String propValue(String nameOfTheProp,Properties prop)
    {
	String value=prop.getProperty(nameOfTheProp);
	if (value==null) return "" ;
	else return value;
    }

    /** replace multiple occurances of key with value in string s **/
    String replace( String s, String key, String value)
    {
	if ((s == null) || (s.indexOf(key) == -1))
	    return s;
	int i = s.indexOf(key);
	String prefix = s.substring(0,i);
	StringBuffer sb = new StringBuffer(prefix);

	// need to take care of spaces in MAC addrs
	if (prefix.lastIndexOf("<A") > prefix.lastIndexOf(">"))
        {
            if(key.indexOf("OID") != -1)
            {
                sb.append(URLEncoder.encode(value));
            }
            else
            {
                sb.append(value.replace(' ', '+'));
            }
        }
	else sb.append(value);

	if (i+key.length() < s.length()) 
	    sb.append(s.substring(i+key.length()));
	if (s.indexOf(key) != -1) 
	    return replace( sb.toString(), key, value );
	return sb.toString();
    }
}

