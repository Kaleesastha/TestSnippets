/*
$Id: AvgReportServlet.java,v 1.1 2006/08/29 13:57:03 build Exp $
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

public class AvgReportServlet extends BaseDBServlet
{

    /** This page query string to get this page again using HTTP GET.
    Can also be used to open other JSP pages with same parameters. **/
    String pageQueryString;
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
	 Connection con; 
	super.doGet(req,res);  // call the base which inits some params

	getPageQueryString();

        // Let's print the table header now.
	String thdr = replace(tableHeader,"#PAGE_QUERY_STRING#", pageQueryString);
        out.println(thdr);

        //  it's time to populate the table.
	try{	
	 con = relapi.getConnection();
	}catch(Exception ex)
	{return ;}
	
     Statement statement = null;
     ResultSet resultSet=null;
     ResultSet rs=null;
     Vector instVect=new Vector();
	try {
            statement = con.createStatement();

	    //int rowCount=0;

	    String tagQuery = getTagString(); // the new constraints
	    sqlQuery = sqlQuery + tagQuery;
	    // Add the order by constraint
	    //sqlQuery = sqlQuery + " " + getOrderByString();

	    if (table != null) {  // substitute statsdata with desired table

		String tdate = parameters.getProperty("tableDate");
		if (tdate != null) {  // add the date if specified
		    if (tdate.equals("TODAY")) {
			java.util.Date d = new java.util.Date();
			SimpleDateFormat formatter
			    = new SimpleDateFormat ("M_d_yyyy");
			tdate = formatter.format(d);
		    }
                    
                    if(table.endsWith("%"))
                        table=table.substring(0,table.length()-1);
		    table = table + tdate;
		}
                
		int i = sqlQuery.indexOf("STATSDATA");
		if (i == -1) 
		    System.err.println("AvgReportServlet: no STATSDATA found in query string."+sqlQuery);
		else {
                    
		    int j = sqlQuery.indexOf(" ", i);
		    sqlQuery = sqlQuery.substring(0,i) + table + " " +  
			sqlQuery.substring(j);
		}
            }

            String isMultiplePolledData =(String)parameters.getProperty("isMultiplePolledData");

            if(isMultiplePolledData==null)
            {
                sqlQuery = sqlQuery + " " + getOrderByString();
                System.err.println("finalquery is :: " + sqlQuery);
                getResult(con,statement , resultSet, sqlQuery);

            }
            else
            {
            if((isMultiplePolledData.equals("true")))
            {
                String instanceQuery="select distinct INSTANCE from PolledData, "
                   +table+" "+"where POLLID=ID "+tagQuery;
                System.err.println(" instance Query is :: "+instanceQuery);

                rs=statement.executeQuery(instanceQuery);

                instVect=new Vector();

                while(rs.next())
                {
                    instVect.addElement(rs.getString(1));
                    
                }
                try{
                rs.close();
                }
                catch(Exception e1){}
            }
            else
            {
                instVect.addElement("-1");
            }
            
            for(Enumeration e=instVect.elements();e.hasMoreElements();)
            {
                String in=(String)e.nextElement();
                String newQuery=sqlQuery+"  AND INSTANCE like '"+in+
                    "'  "+getOrderByString();
                System.err.println(" final Query is :: "+newQuery);

                getResult(con, statement,resultSet , newQuery);
            }
            }
	    statement.close();

	} catch (SQLException ex) {
	    System.err.println("Exception in executeQuery"+ex+" for "+sqlQuery);
	}
        catch(Exception e1)
        {
            System.err.println("Exception in generating reports"+e1);
        }
    finally
    {
        try{
            if (resultSet !=null)
                resultSet.close();
            if(rs!=null)
                rs.close();
            if (statement !=null)
                statement.close();

        }
        catch(Exception e) {}
        
    }
    
	out.flush();
    }
    

    /** Get the SQL query string portion for the order by clause 
      if SORT_COLUMN or SORT_COLUMN_DESC is specified.
      In this case pollid is also added since we're averaging over pollid **/
    String getOrderByString()
    {
	String sql="";
	String s;

	s = parameters.getProperty("SORT_COLUMN");
	if ( (s!=null) && (!s.trim().equals("")) && (!s.equalsIgnoreCase("all"))) 
	    {
		sql=sql+ " order by " + s + ", POLLID ";
		return sql;
	    }
	else
	    {
		s = parameters.getProperty("SORT_COLUMN_DESC");
		if ( (s!=null) && (!s.trim().equals("")) && (!s.equalsIgnoreCase("all")))
		    {
			sql=sql+ " order by " + s + ", POLLID " + " desc";
			return sql;
		    }
	    }
	sql=sql+ " order by POLLID";
	return sql;
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

	pageQueryString = sb.toString();

    }

    void getResult(Connection con,Statement statement, ResultSet resultSet,String sqlQuery) throws Exception
    {

        int rowCount=0;
        resultSet = statement.executeQuery(sqlQuery);
        ResultSetMetaData metaData = resultSet.getMetaData();
        int numberOfColumns =  metaData.getColumnCount();
        
        // The way this works is by first ordering by pollid
        // so all entries for a pollid come together.
        // Then take the avg, max, min for a pollid.
        
        long pollid = -1;  // The identifier for a row to be printed
        String result[] = new String[desiredCols.size()];
        long sum=0, max=0, min=-1, id = 0;
        int num_polls = 0;
        String entry = template;
        
        while (resultSet.next()) {
            
            // first check if done with a polled data
            for(int i=0;i<desiredCols.size();i++) {
                if(columnType.elementAt(i).equals("POLLID")) {
                    id = resultSet.getLong(i+1);
                    
                    if (pollid == -1) pollid = id;
                    if (id != pollid) {  // done with one polled data
                        for(int j=0;j<desiredCols.size();j++) {
                            entry = replace(entry,"#"+desiredCols.elementAt(j)+"#",result[j]);
                        }			
                        sum = max = num_polls = 0;min = -1;
                        entry = replace(entry,"#PAGE_QUERY_STRING#", 
                                        pageQueryString);
                        out.println(entry);
                        out.flush(); 		
                        entry = template;
                        rowCount++;
                    }
                    
                    pollid = id;
                }
            }
            
            num_polls++;
            for(int i=0;i<desiredCols.size();i++) {

                Object obj = resultSet.getObject(i+1);
                result[i] = obj.toString();
                
                if(columnType.elementAt(i).equals("Counter-Avg")) {
                    long val = resultSet.getLong(i+1);
                    sum += val;
                    result[i] = String.valueOf(sum/num_polls);
                }
                
                if(columnType.elementAt(i).equals("Counter-Min")) {
                    long val = resultSet.getLong(i+1);
                    if ((min > val) || (min == -1)) min = val;
                    result[i] = String.valueOf(min);
                }
                
                if(columnType.elementAt(i).equals("Counter-Max")) {
			long val = resultSet.getLong(i+1);
			if (max < val) max = val;
			result[i] = String.valueOf(max);
                }

            }
            
            if(rowCount == MAX_COUNT)break;		
        }
        
        if (pollid != -1) {  // we did have some data - print last entry
            for(int i=0;i<desiredCols.size();i++) {
                entry = replace(entry,"#"+desiredCols.elementAt(i)+"#",result[i]);
            }			
            
            entry = replace(entry,"#PAGE_QUERY_STRING#", pageQueryString);
            // don't print partial data - which would be inaccurate
            if(rowCount != MAX_COUNT) out.println(entry);
        }

        try{
        resultSet.close();
        }
        catch(Exception e1){}
    }
    
}

