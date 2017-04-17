/*
$Id: NetMonitorPluginServlet.java,v 1.1 2006/08/29 13:57:03 build Exp $
*/
package com.adventnet.nms.servlets;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.adventnet.nms.util.GenericUtility;
import com.adventnet.nms.util.CommonUtil;

public class NetMonitorPluginServlet extends HttpServlet 
{
    static java.awt.Dimension dim = new java.awt.Dimension(500,350);

    public String getServletInfo() 
    {
        return "This is the NetMonitorPlugin Servlet";
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
        Hashtable ht = new Hashtable ();

        for(Enumeration parameterNames = req.getParameterNames();
            parameterNames.hasMoreElements();)
        {
            String param = (String)parameterNames.nextElement();
            String value = (String)req.getParameter(param);
            
            if (value == null) value = "-";
            
            ht.put(param,value);
        }

        props = new Properties();  // the props picked up from file
        app_width="800"; 
        app_height="600";
        pre_applet = new StringBuffer();
        post_applet = new StringBuffer();

        log( "called" );
        res.setStatus( HttpServletResponse.SC_OK );
        res.setContentType( "text/html" );
        
        PrintWriter p = res.getWriter();
        
        if (ht == null) p.println( " Error getting Parameters.");
        else 
        {
            
            String name = (String)ht.get("nms_name");

            getAppletTags("NetMonitor" + System.getProperty("file.separator") + name + ".html");    

            processLinks(pre_applet, req);
            processLinks(post_applet, req);
            
            p.println( pre_applet.toString() );    

            if (hasApplet) 
            {
                if(!hasObjectTag)
                {
                    p.println( "<OBJECT classid=\"clsid:8AD9C840-044E-11D1-B3E9-00805F499D93\"");	    
                    p.println(" width="+app_width+" height="+app_height );
                    p.println("codebase=\"http://java.sun.com/products/plugin/1.1/ jinstall-11-win32.cab \">");
                }
                p.println("<param name=SAS_PORT_DIR value=\"../html/\">" );

                // insert the parameters based on CGI args
                for (Enumeration en = ht.keys(); en.hasMoreElements(); ) 
                {
                    String name1 = (String) en.nextElement();
                    String val = (String) ht.get(name1);
                    if ( (name1.startsWith("\"")) &&
                         (name1.length() > 1) && (name1.endsWith("\"")) ) 
                    {
                        
                        String sub = name1.substring(1,name1.length()-1);
                        if (props.get(sub) != null) props.remove(sub);
                        
                    }
                    else 
                    {
                        if (props.get("\"" + name1 + "\"") != null) 
                            props.remove("\"" + name1 + "\"");
                    }
                    props.put(name1,val);
                }        
                for (Enumeration en = props.keys(); en.hasMoreElements(); ) 
                {
                    String name1 = (String) en.nextElement();          
                    String val = (String) props.get(name1);
                    p.println("<param name=" +name1 + " value=" +val +">" );
                }
                p.println("<COMMENT><EMBED");
                p.println(" width="+app_width+" height="+app_height);
                p.println(" SAS_PORT_DIR=\"../html/\" ");
                for (Enumeration en = props.keys(); en.hasMoreElements(); ) 
                {
                    String name1 = (String) en.nextElement();          
                    String val = (String) props.get(name1);
                    if(name1.startsWith("\"")) name1 = name1.substring(1,name1.length()-1);
                    p.println(" " + name1 + "=" + val);
                }
                p.print(">");
            }

            p.println( post_applet.toString() );
        }

        p.flush();
    }

    Properties props = new Properties();  // the props picked up from file
    String app_width=String.valueOf(dim.width); 
    String app_height=String.valueOf(dim.height);
    String archivename;
    

    void setParameter(String name, String value) 
    {
        if ( (name == null) || (value==null) ) return;

        if (name.toLowerCase().equals("height")) app_height=value;
        else if (name.toLowerCase().equals("width")) app_width=value;
        else
        {
            if(!name.startsWith("\"")) 
            {
                name = "\"" +  name + "\"";
            }
            props.put(name,value);
        }
    }

    void getAppletTags(String file) 
    {
        //DataInputStream is;
        InputStream is=null;
        StringBuffer sb = new StringBuffer();
        String s=null;
        filename = file;  // for reporting errors
        try 
        {
            //is =  new DataInputStream(new FileInputStream(file));
            is = CommonUtil.openFile(new File(file));
            BufferedReader d = new BufferedReader(new InputStreamReader(is));
            while ((s = d.readLine()) != null) sb.append(s+"\n");
            load(sb.toString());
        }
        catch (IOException e) 
        { 
            System.err.println("Reading applet file: "+file+": "+e);
        }
	finally{
		try{is.close(); is=null;} catch(Exception exp){}
	}   
    }

    StringBuffer pre_applet = new StringBuffer();
    StringBuffer post_applet = new StringBuffer();

    boolean hasApplet = false;
    boolean hasObjectTag = false;

    /** This loads from a stream */
    void load(String data) throws IOException 
    {
        StringBuffer sb = pre_applet;
        StringTokenizer st=new StringTokenizer(data, "<>=\r\n \t", true);
        String tok_val;
        boolean opentag=false;
        while (st.hasMoreTokens()) 
        {
            tok_val=st.nextToken();
            if (opentag) 
            {
                if (tok_val.toLowerCase().trim().equals("param") || tok_val.toLowerCase().trim().equals("embed")) 
                {
                    hasApplet = true;
                    if(tok_val.equalsIgnoreCase("param")) hasObjectTag = true;
                    getApplet(st, tok_val);
                    sb = post_applet;
                    if (st.hasMoreTokens()) 
                    {
                        tok_val=st.nextToken();                        
                    }
                    else tok_val="";
                } 
                else sb.append("<");
            } 
            if (tok_val.equals("<")) 
            {
                if (!opentag) opentag=true; 
                else
                {
                    opentag=false;
                    sb.append("<"); 
                }
            }
            else 
            {
                opentag=false;
                sb.append(tok_val);
            }
        }
    }

    /** get applet */
    void getApplet(StringTokenizer tok) throws IOException 
    {
        String s = nextToken(tok); 
        while (!s.equals(">")) 
        {
            String name=s, value=param(tok);
            setParameter(name,value);
            s = nextToken(tok);
        }
        s = nextToken(tok);

        while (!s.trim().toLowerCase().equals("/applet")) 
        {
            if (s.trim().toLowerCase().equals("param")) 
            {
                String name=null, value=null;
                while (tok.hasMoreTokens()) 
                {
                    s = nextToken(tok);
                    if (s.trim().toLowerCase().equals("name")) name=param(tok);
                    else if (s.trim().toLowerCase().equals("value")) value=param(tok);
                    else break;
                }
                setParameter(name,value);
            }
            s = nextToken(tok);
        }

        while (!s.equals(">")) 
        {
            s = nextToken(tok);
        }
    }
    
    /** get applet */
    void getApplet(StringTokenizer tok,String ieornn) throws IOException 
    {
        if(ieornn.equalsIgnoreCase("embed"))
        {
            String s = nextToken(tok); 
            while (!s.equals(">")) 
            {
                String name=s, value=param(tok);
                setParameter(name,value);
                s = nextToken(tok);
            }    
        }
        else 
        {
            String name=null, value=null;
            while (tok.hasMoreTokens()) 
            {
                String s= nextToken(tok);
                if (s.trim().toLowerCase().equals("name")) name=param(tok);
                else if (s.trim().toLowerCase().equals("value")) value=param(tok);
                else break;
            }
            setParameter(name,value);
            String s= nextToken(tok);
            while (!s.trim().toLowerCase().equals("embed") && !s.trim().toLowerCase().equals("/object")) 
            {
                if (s.trim().toLowerCase().equals("param")) 
                {
                    name=null; value=null;
                    while (tok.hasMoreTokens()) 
                    {
                        s = nextToken(tok);
                        if (s.trim().toLowerCase().equals("name")) name=param(tok);
                        else if (s.trim().toLowerCase().equals("value")) value=param(tok);
                        else break;
                    }
                    setParameter(name,value);
                }
                s = nextToken(tok);                    
            }
            
            if(s.equalsIgnoreCase("embed"))
            {              
                s = nextToken(tok);
                while (!s.equals(">")) 
                {
                    name=s; value=param(tok);
                    setParameter(name,value);
                    s = nextToken(tok);
                }
            }  
        }        
    }

    /** Change HTML text with links to substitute for $NMS_ARGS tag **/
    void processLinks(StringBuffer sb, HttpServletRequest req) 
    {
        String qstr = req.getQueryString();
        if ( (qstr == null) || qstr.trim().equals("")) return;
        String s = sb.toString();
        s =  GenericUtility.replace(s, "$NMS_ARGS", "?" + qstr);
        sb.setLength(0);
        sb.append(s);
    }

    /** get parameter */
    String param(StringTokenizer tok) throws IOException 
    {
        String s = nextToken(tok);
        String toReturn = "";
        if (!s.equals("="))error("Invalid parameter", tok);
        s = nextToken(tok);
        while(!(s.equals(" ")) && !(s.equals("\t")) && !(s.equals("\n")) && !(s.equals("\r")) && !(s.equals("<")) && !(s.equals(">")) )
        {
            toReturn = toReturn + s;
            s = myNextToken(tok);        
        }
        
        return toReturn;
    }

    String nextToken(StringTokenizer tok) throws IOException 
    {
        if (!tok.hasMoreTokens()) error("Invalid data", tok);
        String s = tok.nextToken();
        if (s.trim().equals("")) return nextToken(tok);
        return s;
    }

    //This would also return the token which is just a whiteSpace.
    String myNextToken(StringTokenizer tok) throws IOException 
    {
        if (!tok.hasMoreTokens()) error("Invalid data", tok);
        String s = tok.nextToken();    
        return s;
    }

    String filename = "";
    /** An error printing convenience while getting Tokens */
    String error(String s, StringTokenizer tok) throws IOException 
    {
        throw new IOException("Error reading HTML: " + s + "\n" +
            "File: " + filename );
    }
}
