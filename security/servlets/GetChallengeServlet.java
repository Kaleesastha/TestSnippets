// $Id: GetChallengeServlet.java,v 1.3 2006/10/07 08:06:52 javeed Exp $
package com.adventnet.nms.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.IOException;
import java.net.*;
import java.util.Properties;
import com.adventnet.nms.util.PureServerUtilsFE;
import com.adventnet.nms.util.NmsUtil;
import com.adventnet.security.authentication.AuthenticationException;

public class GetChallengeServlet extends HttpServlet{

       
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
    {
	//res.getWriter().println("success");
    }

    String userName = null;

    public void  service(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
    {
		userName = null;
                String challenge = null;
                String challengeAndSessionID = null;
		HttpSession session = request.getSession(false);
		PrintWriter out = null;
	
                    if(session != null)
                    {
                        userName = (String) session.getAttribute("userName");
                    }
                    
                    if((userName = request.getHeader("userName"))== null )
                    {
                        userName = request.getParameter("userName");
                    }
                    
		    String hostAddress = request.getParameter("hostaddress");//No I18N
		    String hostPort = request.getParameter("hostPort");//No I18N
		    Properties hostProperties = new Properties();
		    // as per the security team requirement hostAddress is sent as hostname
		    if(hostAddress != null)
		    {
			    hostProperties.put("hostname",hostAddress);
		    }
		    if(hostPort != null)
		    {

			    hostProperties.put("hostport",hostPort);
		    }
		    hostProperties.put("webserveraddress",InetAddress.getLocalHost().getHostName());
		    hostProperties.put("servertype",NmsUtil.getServerType());

		    /*if(userName != null )
                    {
                        try{
                            challenge = PureServerUtilsFE.getChallenge(userName,hostProperties);
                            challenge = URLEncoder.encode(challenge);
                        }
                        catch(AuthenticationException ae){
                            out = response.getWriter();
                            out.println("No such user");
                        }
                        if(challenge != null)
                        {

                            session =request.getSession(true);
                            session.setAttribute("userName",userName);//No Internationalization
                            out = response.getWriter();
                            challengeAndSessionID = "SessionId="+session.getId() + ";Challenge="+challenge ;
                            out.println(challengeAndSessionID);
                        }
                    }*/
                    
                    
                    if( out != null ){
                        out.flush();
                        out.close();
                    }
                    
    }
}
    
    
    
