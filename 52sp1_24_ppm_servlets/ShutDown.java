/*
   $Id: ShutDown.java,v 1.3 2007/07/16 14:36:58 venkatramanan Exp $
 */
package com.adventnet.nms.servlets;

import java.io.*;
import java.util.Properties;
import java.util.Enumeration;
import java.net.URLDecoder;

import javax.servlet.*;
import javax.servlet.http.*;

import com.adventnet.nms.admin.ShutDownException;
import com.adventnet.nms.util.NmsLogMgr;
import com.adventnet.nms.fe.admin.ShutDownAPIFEImpl;
import com.adventnet.security.AuthUtil;
import com.adventnet.security.crypto.CryptoGraphAPI;
import com.adventnet.nms.util.NmsUtil;
/**Used in Shutdown process of NMS server and Apache server.
 */
public class ShutDown extends HttpServlet implements Runnable
{
    	Properties parameters;

	/**init method of Servlet Interface called for initialization.
	 * 
	 * @param config gives ServletConfig object.
	 * 
	 * @exception ServletException if servlet problem has occured.
	 */
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
	}

	/** overrides doPost method of HttpServlet.
	 * 
	 * @param req HttpServletRequest object.
	 * @param res HttpServletResponse object.
	 * 
	 * @return void.
	 * 
	 * @exception ServletException if servlet problem has occured. 
	 * @exception IOException produced by failed or interrupted I/O operations.
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException 
		{
			doGet(req, res);
		}
    
    ShutDownAPIFEImpl api = null;


	/** overrides doGet method of HttpServlet.
	 * 
	 * @param req HttpServletRequest object.
	 * @param res HttpServletResponse object.
	 * 
	 * @return void.
	 * 
	 * @exception ServletException if servlet problem has occured. 
	 * @exception IOException produced by failed or interrupted I/O operations.
	 */
	public synchronized void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
            api = ShutDownAPIFEImpl.getInstance();
		parameters = new Properties();

		res.setContentType("text/html");
		PrintWriter out = res.getWriter();

		for(Enumeration parameterNames = req.getParameterNames(); parameterNames.hasMoreElements();)
		{
			String param = (String)parameterNames.nextElement();
			String value = (String)req.getParameter(param);
			if (param == null) param = "-";
			parameters.put(param,value);
		}
		String confirm = parameters.getProperty("confirm");
		if( confirm == null ){
			out.println("Required parameter(s) missing.");
			return;
		}
		if(confirm.equalsIgnoreCase("Yes"))
		{
			String userName = req.getHeader("userName");//NO I18N
			String password = req.getHeader("password");//NO I18N
			/*Addressing Vulnerability Issue: for encrypting password & handling CRYPTO CLASS - 3rd Party Authentication*/
			password = URLDecoder.decode(password);
			try
			{
				CryptoGraphAPI crypto = NmsUtil.getCryptoGraphAPI();
		        	if (crypto != null)
        			{
            			password = crypto.deCrypt(password);
        			}
				else
				{
				password = AuthUtil.decryptString(password,"WqrTnvA");//No I18N
				}
			}
			catch (Exception ex)
			{
				out.println("Exception while authenticating the user. Probably the Crypto Class' instance is not proper."); // NO I18N
				ex.printStackTrace();
			}
			if( userName == null || password == null ){
				userName = req.getParameter("userName");
				password = req.getParameter("password");
			}
			if (userName==null || password==null) 
			{
				//returning 401 - for unauthorized access
				//res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				out.println("unAuthorized");
				out.flush();
				out.close();

			}	
			try
			{
				boolean success = api.shutDownServer(userName, password, false);
				if (success)
				{
					printSuccessMessage(out);
					//shuts apache server and terminates the JVM.
					Thread th = new Thread(this);
                                        th.start();
				}
				else
				{
					System.err.println("ShutDown failed");
					out = res.getWriter();
					out.println("unAuthorized");
					out.flush();
					out.close();
					throw new IOException(); // only for standalone shutdown
				}
			}
			catch (Exception e)
			{
				if (e instanceof ShutDownException)
				{
					ShutDownException sde = (ShutDownException)e;
					if (sde.getErrorCode() == ShutDownException.AUTHENTICATION_FAILURE)
					{
						out.println("unAuthenticated");
					}
					else if (sde.getErrorCode() == ShutDownException.AUTHORIZATION_FAILURE)
					{
						out.println("unAuthorized");
					}

				}
				out.println("Exception while shutting down " + e);
				e.printStackTrace();
				out.flush();
				out.close();
			}
		}
	}

	//to separate methods based on tasks.
	private void printSuccessMessage(PrintWriter out)
	{
		out.println("<html>");
		out.println("<LINK REL=STYLESHEET TYPE=text/css HREF=../template/nmshtmlui.css>");
		out.println("<body bgColor=white>");
		out.println("<br><br><br><br>");
		out.println("<center>");
		out.println("<font id=cap>The Server has been successfully Shutdown</font>");
		out.println("</center>");
		out.println("</body>");
		out.println("<html>");
		out.flush();
		out.close();
	}

    public void run()
    {
        try
        {
	    Thread.sleep(100);
            api.shutDownApacheServer();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
