/*
$Id: BaseReportServlet.java,v 1.1 2006/08/29 13:57:03 build Exp $
*/

package com.adventnet.nms.servlets;

import java.io.*;
import java.util.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.lang.reflect.*;
import com.adventnet.nms.util.*;
import java.text.*;

/**
 *  This servlet displays a report of collected data from the database.
 *  This is used to view statistics data from the database.
 *  It is called from an JSP file, which contains parameters
 *  it needs.  
 *  
 *  The SQL statement to query the database is supplied through JSP
 *  
 *  
 */

public class BaseReportServlet extends BaseDBServlet
{

    /** This page query string to get this page again using HTTP GET.
    Can also be used to open other JSP pages with same parameters. **/
    String pageQueryString;
    Connection con; 

    /**
     * This method generates the data.
     *
     * @param req encapsulates the request to the servlet
     * @param resp encapsulates the response from the servlet
     * @see javax.servlet.http.HttpServletRequest#getPathTranslated
     * @exception ServletException will be passed on from included servlets
     * @see #doGet
     */
    
    public synchronized void doGet(HttpServletRequest req, 
				   HttpServletResponse res)
	 throws ServletException, IOException 
    {

	super.doGet(req,res);  // call the base which inits some params

	getPageQueryString();

        // Let's print the table header now.
	String thdr = replace(tableHeader,"#PAGE_QUERY_STRING#", pageQueryString);
        out.println(thdr);

        //  it's time to populate the table.
		
	try{ con = relapi.getConnection();}catch(Exception e){return;}
        Statement statement = null;
        ResultSet resultSet=null;
	try {
	     statement = con.createStatement();
         
	    int rowCount=0;

	    String tagQuery = getTagString(); // the new constraints
	    sqlQuery = sqlQuery + tagQuery;

	    // Add the order by constraint
	    sqlQuery = sqlQuery + " " + getOrderByString();

	    if (table != null) {  // substitute statsdata with desired table

		String tdate = parameters.getProperty("tableDate");
		if (tdate != null) {  // add the date if specified
		    if (tdate.equals("TODAY")) {
			java.util.Date d = new java.util.Date();
			SimpleDateFormat formatter
			    = new SimpleDateFormat ("M_d_yyyy");
			tdate = formatter.format(d);
		    }
		    table = table + tdate;
		}
		int i = sqlQuery.indexOf("STATSDATA");
		if (i == -1) 
		    System.err.println("BaseReportServlet: no STATSDATA found in query string."+sqlQuery);
		else {
		    int j = sqlQuery.indexOf(" ", i);
		    sqlQuery = sqlQuery.substring(0,i) + table + " " +  
			sqlQuery.substring(j);
		}
	    }

	    System.err.println("finalquery is :: " + sqlQuery);
	    
	    resultSet = statement.executeQuery(sqlQuery);
	    ResultSetMetaData metaData = resultSet.getMetaData();
	    int numberOfColumns =  metaData.getColumnCount();

	    while (resultSet.next()) {
		String entry = template;
		for(int i=0;i<desiredCols.size();i++) {
		    Object obj = resultSet.getObject(i+1);
		    String result = obj.toString();

		    if(columnType.elementAt(i).equals("Time")) {
			long time = resultSet.getLong(i+1);
			//if (last_counter[i] != -1) delta=time-last_counter[i];
			java.util.Date d = new java.util.Date(time);
			SimpleDateFormat formatter
			    = new SimpleDateFormat ("MM/dd/yyyy HH:mm:ss");
			result = formatter.format(d);
			//last_counter[i] = time;
		    }

		    entry = replace(entry,"#"+desiredCols.elementAt(i)+"#",result);
                }
		rowCount++;
		if(rowCount == MAX_COUNT)break;		
		entry = replace(entry,"#PAGE_QUERY_STRING#", pageQueryString);
		out.println(entry);
		out.flush(); 		//break;
	    }
	    resultSet.close();
	    statement.close();

	} catch (SQLException ex) {
	    System.err.println("Exception in executeQuery"+ex+" for "+sqlQuery);
	}
    finally
    {
        try{
            if (resultSet !=null)
                resultSet.close();
            if (statement !=null)
                statement.close();
        }
        catch(Exception e) {}
        
    }	
	out.flush();
    }
    

    /** Get the page query string we can use, i.e. table and tableDate **/
    void getPageQueryString() {

        StringBuffer sb = new StringBuffer();
	String s = parameters.getProperty("table");
	if (s!=null) { 
	    sb.append("table="+s);
	
	    s = parameters.getProperty("tableDate");
	    if (s!=null) sb.append("&tableDate="+s);
	}

	s = parameters.getProperty("AGENT");
	if (s!=null) sb.append("&AGENT="+s);


	s = parameters.getProperty("NAME");
	if (s!=null) sb.append("&NAME="+s);


	s = parameters.getProperty("OID");
	if (s!=null) sb.append("&OID="+s);


	s = parameters.getProperty("VAL");
	if (s!=null) sb.append("&VAL="+s);

	pageQueryString = sb.toString();

    }

}


