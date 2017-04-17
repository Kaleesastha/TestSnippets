/* $Id: TerminateClientServlet.java,v 1.3 2010/10/29 13:46:45 swaminathap Exp $*/
package com.adventnet.nms.servlets;

import java.util.Hashtable;
import javax.servlet.http.*;
import javax.servlet.*;

public class TerminateClientServlet extends  HttpServlet
{
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException
    {
        doGet(req, res);
    }

    public synchronized void doGet(HttpServletRequest request, HttpServletResponse res) throws ServletException
    {
        java.util.Hashtable v = (java.util.Hashtable)request.getSession().getServletContext().getAttribute("CLIENTID_SESSIONS_MAPPING");//No I18N        
        HttpSession sess = (HttpSession)v.get(request.getHeader("clientID"));//NO I18N
	System.out.println("Remote Client Termination request received for the WEB CLIENT session "+sess);//No I18N
	try
	{
	    sess.invalidate();        	
	}
	catch(IllegalStateException exp)
	{
	    System.err.println("Session : "+sess + " is already invalidated.");//No I18N
	}
    }
}
