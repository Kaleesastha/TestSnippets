/*
$Id: DBGraph.java,v 1.3 2008/10/20 13:34:29 aravinds Exp $
*/
/**
 *  DBGraph.java
 */

package com.adventnet.nms.servlets;

import java.io.*;
import java.util.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.lang.reflect.*;
import java.text.SimpleDateFormat;
import java.awt.image.*;
import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.util.PureUtils;
import com.adventnet.nms.util.GenericUtility;
import com.adventnet.nms.util.NmsLogMgr;
import com.adventnet.nms.jsp.GetImages;
import com.adventnet.management.log.Log;

/**
 *  This servlet graphs data from an DB Table in a configurable
 *  way.  It is called from an JSP file, which contains parameters
 *  it needs.  See the DBGraph.ssi file for an example.
 *  
 */
public class DBGraph extends BaseDBServlet
{
   
    /** whether to time-average the data **/   
    boolean average = false;
    /**  The type of the graph to be generated  **/
    String plotType = "PlotLine";
    /**  The graphing interval passed to the HTML graphing class  **/
    String interval = "all";
    /** The title of the graph.  The 'title' variable changes each time servlet used  **/
    String graphTitle = "DB Graph", title = null;
    String queryString ="";
    Hashtable plotColsTable = null;
	private java.awt.Image img =null;
	private boolean dataAvailable=false;
    /** This is the main servlet service method **/
    public synchronized void doGet(HttpServletRequest req, HttpServletResponse res)
	 throws ServletException, IOException 
    {
        super.doGet(req,res);
    com.adventnet.nms.jsp.GetImages getimage=new com.adventnet.nms.jsp.GetImages();  
 	 plotType = parameters.getProperty("PLOT_TYPE");
	String avg = parameters.getProperty("TIME_AVERAGE");
	if (avg!=null) if (avg.equals("true")) average = true;

	String plotCols = parameters.getProperty("PLOT_VARS");
	if (plotCols == null) {   // ERROR
	    errorPage("No columns to plot: ", req,res);
	    
        } else {
	    plotColsTable = new Hashtable();
	    StringTokenizer st = new StringTokenizer(plotCols);
	    while (st.hasMoreTokens()) 
		plotColsTable.put(st.nextToken(), new Vector());
	}

	int numLines = plotColsTable.size();
	
        String cols = parameters.getProperty("tableColumns");
        if(cols != null) {
	    desiredCols = new Vector();
	    StringTokenizer st = new StringTokenizer(cols);
	    while (st.hasMoreTokens()) desiredCols.addElement(st.nextToken());
	}
		
        cols = parameters.getProperty("COLUMN-TYPE");
	columnType = new Vector();
        if(cols != null) {
	    StringTokenizer st = new StringTokenizer(cols);
	    while (st.hasMoreTokens()) columnType.addElement(st.nextToken());
	}
		
	Connection con = relapi.getConnection();
    Statement statement = null;
    ResultSet resultSet=null; 
	try
	    {
		 statement = con.createStatement();
		int rowCount=0;

		if (table != null) { //substitute statsdata with desired table

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
			System.err.println("AvgReportServlet: no STATSDATA found in query string."+sqlQuery);
		    else {
			int j = sqlQuery.indexOf(" ", i);
			sqlQuery = sqlQuery.substring(0,i) + table + " " +  
			    sqlQuery.substring(j);
		    }
		}
		
		String tagQuery = getTagString();
		sqlQuery = sqlQuery + tagQuery;
		sqlQuery = sqlQuery + " " + getOrderByString();

		System.err.println("finalquery is :: " + sqlQuery);
						
		resultSet = statement.executeQuery(sqlQuery);
		ResultSetMetaData metaData = resultSet.getMetaData();
		int numberOfColumns =  metaData.getColumnCount();	
		long last_counter[] = new long[desiredCols.size()];
		for (int i=0;i<desiredCols.size();i++)	last_counter[i] = -1;
		long delta = 300000;
		while (resultSet.next()) {
		    for(int i=0;i<desiredCols.size();i++) {
			
 			if (plotColsTable.get(desiredCols.elementAt(i)) == null)
			    continue;

			if(columnType.elementAt(i).equals("Counter")) {
			    long current = resultSet.getLong(i+1);
			    //long val = ( current - last_counter[i] );
			    Vector v = 
				(Vector) plotColsTable.get(desiredCols.elementAt(i));
			    if (v!= null) v.addElement(new Long(current));
			    //last_counter[i] = current;
			}
			
			if(columnType.elementAt(i).equals("Integer")) {
			    long val = resultSet.getLong(i+1);
			    Vector v = 
				(Vector) plotColsTable.get(desiredCols.elementAt(i));
			    if (v!= null) v.addElement(new Long(val));
			}

			if(columnType.elementAt(i).equals("Time")) {
			    long time = resultSet.getLong(i+1);
			    if (last_counter[i] != -1) delta=time-last_counter[i];
			    Vector v =  (Vector) plotColsTable.get("TTIME");
			    if (v!= null) v.addElement(new Long(time));
			    last_counter[i] = time;
			}


		    }
		    rowCount++;
		    if(rowCount == MAX_COUNT)break;		
		}
		if(rowCount > 0) dataAvailable = true;
		resultSet.close();
		statement.close();
		
		// First take care of the time vector - or X axis
 		Vector v = (Vector) plotColsTable.get("TTIME");
		if (v == null) errorPage("DB Graph: Time Column absent.",req,res);
		long[] longx = new long[v.size()];//x points for thegraph
		long[] value =new long[v.size()];//y points for the graph
		for (int i=0;i<v.size();i++) 
		  {
		     longx[i] = ((Long)v.elementAt(i)).longValue();
			}
			
		plotColsTable.remove("TTIME");
		
		title = graphTitle;
		// Then each of the remaining vectors
		int j = 0; 
		long[][] longy = new long[plotColsTable.size()][];
		for (Enumeration en = plotColsTable.keys();en.hasMoreElements();) {

		    String key = (String) en.nextElement();
		    title = title + "  Line"+(j+1) + ": " + key;
		    v = (Vector) plotColsTable.get(key);
		    longy[j] = new long[v.size()];
		    //value[] = new long[v.size()];
		    for (int i=0;i<v.size();i++) 
			{
			  //longy[j][i] = ((Long)v.elementAt(i)).longValue();
			  value[j] = ((Long)v.elementAt(i)).longValue();
		      j++;
			}

		}
		//writePage(out, longx, longy, req, res);
		if(plotType.equals("PlotLine"))
		{
			img=getimage.getLineGraphImage(true,longx,value,title);
		}
		else
		{
			img=getimage.getBarGraphImage(true,longx,value,title);
		}


		//get the file name . the file name is the time in long when it was generated.
		java.util.Date dat=new java.util.Date();
		String st ="";
		if(req.getQueryString()!=null)
			queryString =req.getQueryString();

		HttpSession session = req.getSession(false); 
		String username = null;
		if(session != null){
			username = (String)session.getAttribute("userName");
		}
		if(username == null){
			username = req.getParameter("userName");
		}
		if(username == null){
			return;
		}

		StringBuffer buf = new StringBuffer();
		String path;
		if(!NmsUtil.createUserDir)
		{
			path = PureUtils.usersDir+"html/defaultsToAllUsers"; //No I18N
		}
		else
		{
			path = PureUtils.usersDir+"users/"+username; //No I18N
		}
		buf.append(path);
		buf.append("/");// No I18N            
		buf.append(String.valueOf(dat.getTime()));
		buf.append(".jpg");//added by vidhya to get the extension as .jpg
		st=buf.toString();//st : file name.
		//remove previous jpg graph file.
		Filt filtobj=new Filt();
		File f=new File(path);
		File filarr[]=f.listFiles(filtobj);
		for(int i=0;i<filarr.length;i++)
			filarr[i].delete();

		try
		{//convert the image got to jpeg format.
			if(dataAvailable)
			{
				getimage.encodeImage(st,img);
			}
		}

		catch(Exception e)
		{
       
			NmsLogMgr.MISCERR.fail("Exception in PollGraphs " + e.getMessage(),e);            
			out.println("Exception has occured in PollGraphs. Please refer logs for more details.");
			// e.printStackTrace();
			return;
		}
         
		out.println( "<HTML><HEAD>" );
		out.println( "<TITLE>");
		out.println( NmsUtil.GetString("AdventNet"));
		out.println(" "); 
		out.print(NmsUtil.GetString("WebNMS"));
		out.println(" Graphs</TITLE>" );
		out.println("<LINK REL=STYLESHEET TYPE=\"text/css\" HREF=\"../template/nmshtmlui.css\">" );
		out.println("<meta http-equiv=\"pragma\" content=\"no-cache\">");
		out.println( "</HEAD><BODY>");
		// if(ht.get("table")!=null)
		out.println("<CENTER><TABLE BORDER=\"0\" CELLPADDING=\"0\" CELLSPACING=\"0\">");

		out.println("<TR><TD><FONT ID=\"cap\"><CENTER>"+title+"</CENTER></FONT></TD></TR></TABLE>");

	
		//else
		if(dataAvailable)
		{
			if(st.indexOf(PureUtils.rootDir) != -1){
				st=st.substring(PureUtils.rootDir.length());
			}
			out.println("<IMG SRC=\""+"../"+st+" \" BORDER=\"0\" >");
		}
		else
		{   
			out.println("<H3><CENTER>No data Available for the time interval "+interval+"<BR>for <FONT ID=\"cap\"><CENTER>"+title+"</CENTER></FONT></CENTER></H3>");

		}
		out.flush();

		}

	catch (SQLException ex) {
		System.err.println("Exception in executeQuery"+ex+" for "+sqlQuery);
		errorPage("DB Graph could not get data: "+ex, req, res);
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
   
 }
 
 class Filt implements FilenameFilter
    {
        public boolean accept(File f,String s)
        {
            //String temp=s.substring(s.length()-3);
            if(s.endsWith(NmsUtil.getImageType()))
                return true;
            else
                return false;
        }
   }
}
