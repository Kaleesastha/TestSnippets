/* $Id: Jmx_dynamic.java,v 1.2 2007/08/27 14:12:21 barathv Exp $
 * $(#)Jmx.java
 * Copyright (c) 1998 Adventnet, Inc. All Rights Reserved.
 * Please read the associated COPYRIGHTS file for more details.
 *
 * ADVENTNET, INC. MAKES NO REPRESENTATIONS OR WARRANTIES  ABOUT THE
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE, OR NON-INFRINGEMENT.  ADVENTNET, INC. SHALL
 * NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF
 * USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE  OR ITS DERIVATIVES.
 */

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.management.*;

import com.adventnet.html.dynamic.*;
import com.adventnet.adaptors.html.*;

import com.adventnet.utilities.logging.*;

/**
 * Sevlet class for the HTML adaptor of JMX agent. All the communication
 * of the JMX server to the browser through this servlet if the request
 * is for dynamic page request.
 */

public class Jmx_dynamic extends HttpServlet
{
	/* Mail class object for html */
	private DynamicMain main;

	private com.adventnet.utilities.logging.Log log;

	/**
	 * Method to initialize. This method is called once
	 * by the web-server.
	 */

	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);

		 try {
                        log = LogFactory.getInstance("HTML");
                }
                catch (InstantiationException ie)
                {
                        ie.printStackTrace();
                }
	}


	/**
	 * This method is invoked by the web-server when a get is
	 * called from the browser.
	 *
	 * @param sreq - the collection of data rcvd from the browser.
	 * @param sres - the collection of data send to the browser.
	 */
	public void doGet(HttpServletRequest sreq, HttpServletResponse sres)
		throws ServletException, IOException {
			sres.setContentType("text/html");
			String result = null;
			sreq.getSession(true);
			main = new DynamicMain();

			HtmlCreation htmlCr = new HtmlCreation();
                        try {
                            //htmlCr.setContextPath(sreq.getContextPath());
			    htmlCr.setContextPath("../");
                        } catch(NoSuchMethodError nsme) {
                            htmlCr.setContextPath("");
                        }

			main.registerHtmlInterface(htmlCr);
			main.registerErrorInterface(new DynamicError());

			Hashtable table = new Hashtable();

			HttpSession session = sreq.getSession(true);

			String id = session.getId();
			//HtmlAdaptor.setClientInfo(id);

			table.put((String)"Session", session);
			Enumeration e = sreq.getParameterNames();

			for (; e.hasMoreElements(); ) {
				Object paramName  = e.nextElement();
				String paramValue = sreq.getParameter(paramName.toString());
				log.trace("ParamName is "+paramName+"   "+"ParamValue is "+paramValue);

				if (paramValue.trim().length() != 0)
				{
					table.put(paramName, paramValue);
				}
			}


			PrintWriter p =  sres.getWriter();

			//System.out.println("Inside Get");
			result = main.processGetRequest(table);

			//System.out.println("The result is "  + result);

			p.println(result);
			p.flush();
			p.close();
			return;
		}

	public void doPost(HttpServletRequest sreq, HttpServletResponse sres)
		throws ServletException, IOException {
			sres.setContentType("text/html");
			String result = null;
			//sreq.getSession(true);

//			String s1 = sreq.getParameter("arrayType");

			//System.out.println("POST");
			main = new DynamicMain();

			HtmlCreation htmlCr = new HtmlCreation();
                        try {
                            //htmlCr.setContextPath(sreq.getContextPath());
			    htmlCr.setContextPath("../");
                        } catch(NoSuchMethodError nsme) {
                            htmlCr.setContextPath("");
                        }

			main.registerHtmlInterface(htmlCr);
			main.registerErrorInterface(new DynamicError());

			HttpSession session = sreq.getSession(true);

			String objName = sreq.getParameter("attrbName");
			Hashtable table = new Hashtable();
			table.put((String)"Session", session);

//			table = null;
			//System.out.println("inside do post");
			//System.out.println("Object name is " + objName);


//			if((Object)s1 == null){


				Enumeration e = sreq.getParameterNames();

				for (; e.hasMoreElements(); ) {
					Object paramName  = e.nextElement();
					String paramValue = sreq.getParameter(paramName.toString());
					log.trace("ParamName is "+paramName+"   "+"ParamValue is "+paramValue);
				if (paramValue.trim().length() != 0)
				{
					table.put(paramName, paramValue);
				}
				}
//			}
			if( (Object) table != null)
			{
				PrintWriter p =  sres.getWriter();

				result = main.processPostRequest(table);

				p.println(result);
				p.flush();
				p.close();
			};
/*			p.println("hai");
			p.flush();
			p.close(); */
			return ;



			//			doGet(sreq,sres);
		}
}
