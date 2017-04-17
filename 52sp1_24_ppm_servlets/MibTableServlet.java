/*
$Id: MibTableServlet.java,v 1.1 2006/08/29 13:57:03 build Exp $
*/
/**
 *  MibTableServletServlet.java
 */

package com.adventnet.nms.servlets;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.lang.reflect.*;
import com.adventnet.nms.util.*;
import com.adventnet.snmp.beans.*;
import com.adventnet.snmp.snmp2.*;
import com.adventnet.snmp.mibs.*;
import com.adventnet.nms.jsp.MibBrowserMain;
import com.adventnet.nms.servlets.AuthenticationServlet;
/**
 *  This servlet displays a MIB Table from an agent in a configurable
 *  way.  It is called from an JSP file, which contains parameters
 *  it needs.  See the RouteTable.ssi file for an example.
 *  Using for example /ssi/RouteTable.ssi?HOST=localhost,
 *  will display the routing table.
 */
public class MibTableServlet extends AuthenticationServlet
    {    
    Properties parameters;
    Hashtable paramTable;

    /**
     * Initialize MibTableServlet.
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
    
    public synchronized void doGet(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException 
        {
        
        //create the proprties object which would have all the 
        // parameters that came along with the request stream.        
        parameters = new Properties();
        
        //to write back into the stream.
        PrintWriter out = new PrintWriter(res.getOutputStream());
	paramTable=(Hashtable)req.getAttribute("parameters");        
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
        String tableHeader = parameters.getProperty("tableHeader");

        String template = parameters.getProperty("template");

        if (!initSnmp(parameters, out)) return;

        if (template == null) 
            {
            StringBuffer sb = new StringBuffer();
            sb.append("<TR BGCOLOR=#add8e6>");
            for (Enumeration en=desiredCols.elements();en.hasMoreElements();)
                sb.append("<TD>#"+en.nextElement()+"#</TD>");
            sb.append("</TR>");
            template = sb.toString();
            }

        if (tableHeader == null) 
            {
            StringBuffer sb = new StringBuffer();
            sb.append("<TABLE WIDTH=300 BORDER=2 CELLPADDING=0 CELLSPACING=0><TR BGCOLOR=#4169e1>");
            for (Enumeration en=desiredCols.elements();en.hasMoreElements();)
                sb.append("<TD>"+en.nextElement()+"</TD>");
            sb.append("</TR>");
            tableHeader = sb.toString();
            }

        //Everything seems to be OK. Let's print the table header now.
        out.println(tableHeader);
        
        //  it's time to populate the table.
        SnmpOID rootoid = target.getSnmpOIDList()[0];
        int maxtry = 0;
        while ( maxtry++ < 10000) 
            {
            // limit the max getnexts to 10000
            SnmpVarBind result[] = target.snmpGetNextVariableBindings();
            if (result == null) break;
            if ( !SnmpTarget.isInSubTree(rootoid,target.getSnmpOIDList()[0]) )
                break;  // check first column

            String entry = template;
            for (int i=0;i<result.length;i++) 
                {
                // print the values
                entry = replace(entry, "#"+desiredCols.elementAt(i)+"#",
                                mibOps.toString(result[i].getVariable(),
                                                result[i].getObjectID()));
                }
            out.println(entry);
            out.flush();
            }

        out.println("</TABLE>");
        out.flush();

        if (maxtry == 1) 
            {
            // we did not get a valid row
            out.println("<p>");
            out.println("Request failed, timed out or no available data. \n"+
                        target.getErrorString());
            out.flush();
            } 

        }
    

    void sendErrorMessage(String errorMessage, PrintWriter out)
        {        
        try
            {            
            out.println(errorMessage);
            out.println("</TD>");
            out.println("</TABLE>");
            out.flush();
            out.close();
           return;
            }
        catch(Exception e)
            {            
            NmsLogMgr.MISCERR.fail("Exception in MibTableServlet : "+e,e);            
            }
        }   
    

    
    boolean getBoolean(String s)
        {
        if(s != null && s.equalsIgnoreCase("true"))
            return true;
        else
            return false;
        }


    // Use an SNMP target bean to perform SNMP operations
    SnmpTarget target = new SnmpTarget();
    MibOperations mibOps ;
    static MibModule mod;
    // This is the vector of columns
    Vector desiredCols = null;


    /** This initializes the SNMP parameters **/
    boolean initSnmp(Properties p,  PrintWriter out) 
        {

        String version = p.getProperty("version");
        if(version != null) 
            {
            // if SNMP version is specified, set it
            if(version.equals("v2"))
                target.setSnmpVersion( SnmpTarget.VERSION2C ) ;
            else if(version.equals("v1"))
                target.setSnmpVersion( SnmpTarget.VERSION1 );
            else
                {
                System.out.println("Invalid Version Number in MibtableServlet"); 
                
                }
            }


        String host = p.getProperty("HOST");
        if(host == null)
            {
            sendErrorMessage("Parameter HOST missing." , out);
            return false;
            }

        target.setTargetHost( host );  // set the agent hostname


        String community = p.getProperty("COMMUNITY");
        if (community != null) // set the community if specified
            target.setCommunity( community );

        
        String port = p.getProperty("PORT");
        String retries = p.getProperty("RETRIES");
        String timeout = p.getProperty("TIMEOUT");
        try 
            {
            // set the timeout/retries/port parameters, if specified
            if (port != null) 
                target.setTargetPort( Integer.parseInt(port) );
            if (retries != null) 
                target.setRetries( Integer.parseInt(retries) );
            if (timeout != null) 
                target.setTimeout( Integer.parseInt(timeout) );
            }
        catch (NumberFormatException ex) 
            {
            NmsLogMgr.MISCERR.fail("Invalid Integer Argument in MibTableServlet "+ex,ex);
            
            }
        
        //mib loading.......
        String mibs = parameters.getProperty("mibsToLoad");
        String hashKey=parameters.getProperty("keyFortable");
        //getting the key for loading the particular mib.
        //this is used by the loadModulesTable to get us the particular mib 
        //or store the mib under the key name if new mib loaded.
        if(mibs != null)
            try
                {mod=MibBrowserMain.loadMib(mibs,hashKey);}
            catch (Exception ex)
                {NmsLogMgr.MISCERR.fail("MibTableServlet error loading mibs: "+mibs+" : "+ex,ex); }
        //end of mib loading.
        
        
        desiredCols = null;
        String cols = parameters.getProperty("mibColumns");
        if(cols != null)
            {
            desiredCols = new Vector();
            StringTokenizer st = new StringTokenizer(cols);
            while (st.hasMoreTokens()) desiredCols.addElement(st.nextToken());
            }
        String mibTable = parameters.getProperty("mibTableName");
        if(mibTable == null)
            {
            sendErrorMessage("Parameter mibTableName missing." , out);
            return false;
            }

        // get a reference to the MIB operations instance
        mibOps = MibBrowserMain.getMibOperations();
        SnmpOID tableOID = mibOps.getSnmpOID(mibTable); // get table OID

        if (tableOID == null) 
            {
            // could not get table OID - try target
            mibOps = target.getMibOperations();
            tableOID = mibOps.getSnmpOID(mibTable);
            }

        MibNode tableNode = mibOps.getMibNode(tableOID); // get table MIB node

        if (tableNode == null) 
            {
            // could not get table MIB node - try target
            mibOps = target.getMibOperations();
            tableOID = mibOps.getSnmpOID(mibTable);
            tableNode = mibOps.getMibNode(tableOID);
            }
        

        if (tableNode == null) 
            {
            // could not get table MIB node
            sendErrorMessage("Cannot find MIB node for table.  Correct MIB must be loaded: "+mibTable, out);  
            return false;
            } 

        Vector columns = tableNode.getTableItems();
        if ( (columns == null) || (columns.size() == 0) ) 
            {
            // try the parent
            MibNode parent = tableNode.getParent(); // assuming entry
            columns = parent.getTableItems();  // this is the table
            if ( (columns == null) || (columns.size() == 0) ) 
                {
                sendErrorMessage("Not a table.  No columns: "+mibTable, out);
                return false;
                }
            } 

        if (desiredCols != null) 
            {
            for (Enumeration en=desiredCols.elements();en.hasMoreElements();)
                if (!columns.contains(en.nextElement())) 
                    {
                    sendErrorMessage("Improper mibColumns values: "+cols,out);
                    return false;
                    }
            columns = desiredCols;
            }
        else desiredCols = columns;

        // We need to confirm the first column is read-accessible
        while (columns.size() > 0) 
            {
            SnmpOID firstOID = mibOps.getSnmpOID((String)columns.elementAt(0));
            MibNode firstNode = mibOps.getMibNode(firstOID);
            if ( (firstNode.getAccess() != SnmpAPI.RONLY) &&
                 (firstNode.getAccess() != SnmpAPI.RWRITE) ) 
                {
                
                columns.removeElementAt(0);
                }
            else break;
            }
        
        // create OID array from table columns
        SnmpOID soids[] = new SnmpOID[columns.size()];
        for (int i=0;i<soids.length;i++) 
            soids[i] = mibOps.getSnmpOID((String)columns.elementAt(i));
        
        target.setSnmpOIDList(soids); 

        return true;
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
            sb.append(value.replace(' ', '+'));
        else sb.append(value);

        if (i+key.length() < s.length()) 
            sb.append(s.substring(i+key.length()));
        if (s.indexOf(key) != -1) 
            return replace( sb.toString(), key, value );
        return sb.toString();
        }
    }
