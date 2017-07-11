/* $Id: AuthenticationFilter.java,v 1.4.6.1 2017/05/22 09:42:55 venkatramanan Exp $ */

package com.adventnet.nms.webclient.login;

import java.io.IOException;
import java.util.Locale;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;
import com.adventnet.nms.util.NmsLogMgr;
import com.adventnet.management.log.Log;
import com.adventnet.nms.webclient.common.WebClientUtil;
import com.adventnet.security.authorization.AuthorizationAdmin;
import com.adventnet.nms.util.NmsUtil;
/**
*	This class implements <code>Filter</code> used to perform
*	authentication task on request to the resource like Servlets or
*	JSP pages.
*
*	@author R.Ramesh Babu
*	@version $Id: AuthenticationFilter.java,v 1.4.6.1 2017/05/22 09:42:55 venkatramanan Exp $
*	@since Web NMS 4.5
*	
*/
public class AuthenticationFilter implements Filter 
{
/**
	Called by the web container to indicate to a filter that it is being
	placed into service. The servlet container calls the init method
	exactly once after instantiating the filter. The init method must
	complete successfully before the filter is asked to do any filtering
	work. 
	@param config a filter configuration object used by a servlet
		 container used to pass information to a filter during
		 initialization
	@throws ServletException if any exception occured during initialization
*/

    private FilterConfig fConfig = null;
    private String[] fileList = null;
    public void init(FilterConfig config) throws ServletException 
    {
        logMessage("AuthenticationFilter has been instantiated...");
	fConfig = config;
	String strFileList = fConfig.getInitParameter("excludeAuthentication");
	StringTokenizer token = new StringTokenizer(strFileList, ",");
	int size = token.countTokens();
	fileList = new String[size];
	int count = 0;
	while(token.hasMoreTokens())
	{
		fileList[count] = token.nextToken();
		count++;
	}	
    }

/**
*	This doFilter method of the AuthenticationFilter is called by the
*	container each time a request/response pair is passed through the
*	chain due to a client request for a resource at the end of the chain.
*	If the user invokes any jsp, with out logging in or user session is
*	expired, then this method redirects control to login page to provide
*	authentication.
*
*	@param srequest <code>ServletRequest</code> object to provide client
*			 request information
*	@param sresponse <code>ServletResponse</code> object to send response
*			 to client
*	@param chain a <code>FilterChain</code> is an object provided by the
*		servlet container to give a view into the invocation chain
*		of a filtered request for a resource.
*
*	@throws IOException if any IO Exception occured during processing
*	@throws ServletRequest if any ServletException occured during
*				processing
*/



    public void doFilter(ServletRequest srequest, ServletResponse  sresponse,
                         FilterChain chain) throws IOException, ServletException 
    {

        RequestDispatcher dispatcher = null;

        HttpServletRequest request = (HttpServletRequest)srequest;

        HttpSession session = request.getSession();
        String userName = (String)session.getAttribute(WebClientUtil.USER_KEY);
        String requestPath = request.getRequestURI();

	((HttpServletResponse)sresponse).setHeader("Pragma","no-cache"); //No I18N
	((HttpServletResponse)sresponse).setHeader("Cache-Control", "no-store"); //No I18N
	((HttpServletResponse)sresponse).setDateHeader("Expires",0); //No I18N

	if(requestPath.equals("/jsp/Login.do") && session.getAttribute("clienttype")!=null && session.getAttribute("clienttype").equals("applet")&& userName!=null && (!userName.equals("")))//no i18n
	{
		session.invalidate();
		dispatcher = request.getRequestDispatcher("/LoginPage.do");//no i18n
		dispatcher.forward(srequest, sresponse);
	}
        if(session.getAttribute("InitTab")!=null)
        {
            if((request.getRequestURI().indexOf("/Logout.do")==-1) && (request.getRequestURI().indexOf("Login.do")==-1))//No I18N
            {
                String urlPath = (String)request.getQueryString();//No I18N
                if(urlPath != null)
                {
                    if(!urlPath.startsWith("javascript"))//No I18N
                    {
                        int index = urlPath.indexOf("viewId");//No I18N
                        if(index != -1)
                        {
                            String viewIdString = urlPath.substring(index+7,urlPath.length());//No I18N
                            String viewId = "";//No I18N
                            if(viewIdString.indexOf("&")!= -1)//No I18N
                            {
                                viewId = viewIdString.substring(0,viewIdString.indexOf("&"));//No I18N
                            }
                            else
                            {
                                viewId = viewIdString;
                            }
                            session.setAttribute("InitTab",viewId);
                        }
                        else
                        {
                            if(session.getAttribute("InitTab")!= null && (!((String)session.getAttribute("InitTab")).equals("")))
                            {
                                session.setAttribute("InitTab",session.getAttribute("InitTab"));
                            }
                            else
                            {
                                session.setAttribute("InitTab",WebClientUtil.getClientParameter(userName, "INIT_PANEL"));
                            }
                        }
                    }
                }
            }
        }

	boolean value1 = request.isRequestedSessionIdFromURL();
	String jsessionid = session.getId();
	//Note: fix for using jsessionid in SSO cases
	if(value1)
	{
		Cookie[] cookies = request.getCookies();
		boolean hascookie = false;
		if(cookies != null)
		{	
			for(int i=0;i<cookies.length;i++)
			{
				Cookie cookie = cookies[i];
				if(cookie.getName().equals("JSESSIONID"))
				{
					hascookie = true;
					break;
				}
			}
			if(!hascookie)
			{	
				Cookie cook=new Cookie("JSESSIONID",jsessionid);
				//System.out.println(getServlet().+"::"+request.getPathInfo()+"::::"+request.getContextPath()+":::"+request.getRequestURI());
				cook.setPath("/");
				((HttpServletResponse)sresponse).addCookie(cook);
			}
		}
	}

	int size = fileList.length;
	boolean flag = false;
	if (userName != null)
	{
	   flag = true;
	}
	if (!flag)
	{
		for(int i=0;i<size;i++)
		{
			if (requestPath.indexOf(fileList[i])!= -1)
			{
				flag  = true;
				break;
			}	
		}
	}

	if (flag)
        {
            chain.doFilter(srequest,sresponse);
        }
        else
        {
            //Save the request URL in the session.
            session.setAttribute("requestPath", requestPath);
            userName = srequest.getParameter(WebClientUtil.USER_KEY);
            if(userName != null)
            {
                dispatcher = request.getRequestDispatcher("/jsp/Login.do");
            }
            else
            {
                Enumeration enumerate = srequest.getParameterNames();
                HashMap hashMap = new HashMap();
                while(enumerate.hasMoreElements())
                {
                    String key = (String) enumerate.nextElement();
                    String value = srequest.getParameter(key);
                    hashMap.put(key, value);
                }
                hashMap.put("requestPath", requestPath);
                session.setAttribute("requestParams", hashMap);			
                dispatcher = request.getRequestDispatcher("/preLogin.do");
            }
            dispatcher.forward(srequest, sresponse);
        }
    }

/**
* 	Called by the web container to indicate to a <code>AuthenticationFilter
*	</code> that it is being taken out of service
*/
    public void destroy() 
    {
    }

    private void logMessage(String str)
    {
        NmsLogMgr.MISCUSER.log(str, Log.DEBUG);
    }

}
