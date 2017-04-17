/*
$Id: SaveFrameSizeServlet.java,v 1.3 2008/10/20 13:28:13 aravinds Exp $
*/
package com.adventnet.nms.servlets;

import java.io.*;
import java.util.*;
import java.rmi.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.lang.reflect.*;
import com.adventnet.nms.util.*;
import com.adventnet.nms.netwatch.*;


public class SaveFrameSizeServlet extends HttpServlet
{

    String userName = null;
    public String getServletInfo()
    {
        return "This Servlet saves the internal frame size and locations";
    }
	/**
     * Services a single request from the client.
     * 
     * @param req the servlet request
     * @param res the servlet response
     * 
     * @exception ServletException when an exception has occurred
	 * @exception IOException produced by failed or interrupted I/O operations.
	 * 
     */
    public void doPost(HttpServletRequest req, HttpServletResponse res ) throws ServletException, IOException
    {
        doGet(req,res);
    }

    public void doGet( HttpServletRequest req, HttpServletResponse res ) throws ServletException, IOException
    {
	    //to write back into the stream.
	    PrintWriter p = new PrintWriter(res.getOutputStream());
	    res.setContentType("text/html");

	    Hashtable ht = new Properties();

		      //populate the properties.
		      for(Enumeration paramNames = req.getParameterNames(); paramNames.hasMoreElements();)
		      {
			      String param = (String)paramNames.nextElement();
			      String value = (String)req.getParameter(param);
			      if (param.equalsIgnoreCase("UserName"))
			      {
				      userName = value;
				      continue;
			      }
			      if (param == null) param = "-";
			      if (ht.containsKey(param))
				      ht.remove(param);
			      ht.put(param,value);
		      }        

		Properties prop = new Properties();
		File file;
		if(!NmsUtil.createUserDir)
		{
			file = new File(PureUtils.usersDir +"html/defaultsToAllUsers/" + userName + "_FramesInfo.conf");//No I18N	
		}
		else
		{
			file = new File(PureUtils.usersDir +"users/" + userName + "/FramesInfo.conf"); //No I18N
		}
		if (file.exists())
		{

                    //InputStream in = new FileInputStream(file);
                    InputStream in = CommonUtil.openFile(file);
			prop.load(in);
			in.close();
		}
		for (Enumeration e=ht.keys();e.hasMoreElements();)
		{
			String key = (String) e.nextElement();
			String value = (String) ht.get(key);
			if (prop.containsKey(key))
				prop.remove(key);
			prop.put(key,value);	
		}
		OutputStream out = new FileOutputStream(file);
		try{
		prop.store(out,"Size and Location of Internal Frames");
		}catch(IOException ioe){
			throw ioe;
		}
		p.flush();
    	
	      
	}
}
