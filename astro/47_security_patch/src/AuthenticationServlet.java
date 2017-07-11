// $Id: AuthenticationServlet.java,v 1.11.2.3.6.8 2009/09/04 11:30:32 sureshbabu Exp $

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
import java.util.List;
import java.rmi.RemoteException;

import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.commonfe.GenericFEAPIImpl;
import com.adventnet.nms.util.PureServerUtilsFE;
import com.adventnet.security.authentication.AuthenticationException;
import com.adventnet.security.authorization.AuthorizationAdmin;

public class AuthenticationServlet extends HttpServlet{

	String userName = null;
        String  messageDigest = null;
        String password = null;
	boolean passwordExpired = false;
	static int FIRST_LOGIN=1111 ;// Setting an arbitrary value to the FIRST_LOGIN variable
    boolean firstLogin =false;

    int maxSession = -1;
    //Added by Balan on 13/03/03 for Velankanni's issue In Applet Client Lock out Screen gets
    //unlocked even with typing junk password
    boolean  bisValidUser = false;
    //Add Ends

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
	res.getWriter().println("success");
    }

	public void  service(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

		// one servlet instance may serve the second request also.
		// in that case the userName and password variables will contain values
		// which were given previously during the first request.  so, even if
		// wrong username/password is given when the user tries to login for the second time
		// the user will be allowed into the system. This initialization is added to fix that bug.
		userName = null;
		password = null;

		passwordExpired = false;
                firstLogin = false;
		String message = "";
                String challenge = null;
                String forCheck = null;
		HttpSession session = request.getSession(true);
		PrintWriter out = null;

                //Added by Balan on 13/03/03
                  bisValidUser = false;
                //Add Ends
                if(session != null)
                {
                    userName = (String) session.getAttribute("userName");
                }

                if(userName != null)
                {
                    //Commented by Balan on 13/03/03
                    //super.service(request,response);
                    // Comment Ends
                    //Added by Balan on 13/03/03
                     bisValidUser = true;
                   //Add Ends
                    /*out = response.getWriter();
                    out.println("Validation Not Required");
                    out.flush();
                    out.close();
                    return;*/

                }
                 //Commented by Balan on 13/03/03
                   /* else
                   {*/
                //Comment Ends
                    if(userName == null)
                    {
                        if((userName = request.getHeader("userName"))== null )
                        {
                            userName = request.getParameter("userName");
                        }
                    }

                    if ( request.getHeader("messageDigest") == null)
                    {
                        message = "Invalid messageDigest.Unable to create new session";//NO I18N
                        out = response.getWriter();
                        out.println(message);
                        out.flush();
                        out.close();
                        return ;
                    }

                    if((messageDigest = request.getHeader("messageDigest")) == null)
                    {
                        messageDigest =request.getParameter("messageDigest");
                    }

                    if(messageDigest != null)
                    {
                        try
                        {
                            messageDigest = URLDecoder.decode(messageDigest);
                        }
                        catch(Exception e)
                        {
                        }
                        forCheck = messageDigest;
                    }
                    else
                    {
                        password = (String) session.getAttribute("password");

                        if(password == null)
                        {
                            if( (password = request.getHeader("password")) == null)
                            {
                                password = request.getParameter("password");
                            }
                        }
                        forCheck = password;
                    }


                    String hostAddress = request.getParameter("hostaddress");
		    String hostPort = request.getParameter("hostPort");//No I18N
		    //String webServerAddress=request.getParameter("webserveraddress");//No I18N
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
                    if(userName!=null && forCheck!=null){
			boolean result = false;
			try{
                            if(isLimitExceeded())
                            {
                                message = "Maximum Client Session exceeds, unable to create new session";
                                out = response.getWriter();
                                out.println(message);
                                out.flush();
                                out.close();
                                return;
                            }
                            if(messageDigest != null)
                            {
                                result = PureServerUtilsFE.authenticateUser(userName,messageDigest,hostProperties);
                            }
                            else
                            {
                                result = PureServerUtilsFE.isPasswordCorrect(userName,password,hostProperties);
                            }
			}catch(AuthenticationException e){
                            int exceptionType = e.getExceptionType();
		/* For fixing the issue of not accepting password on FIRST TIME LOGIN  Ticket: 12115291*/
			    FIRST_LOGIN =exceptionType; // Determining whether login for the first time
			    
                            if( exceptionType == AuthenticationException.PASSWORD_EXPIRED )
                            {
                                // setting the result to true so that
                                // the userName is put in the session,
                                // which means the user is authenticated.
                                result = true;
                                if (NmsUtil.checkAuthorization(userName, "Change Self Password"))
                                	message = "Password Expired";
                                else
                                {
                                    //Unauthorised to Change the expired Password. -- Karen.
                                	message = "Password Expired Unauthorised"; //No I18N
                                }

                                // setting passwordExpired to true so that
                                // the PASSWORDEXPIRED parameter is sent to the
                                // client from StartClientServlet.
                                passwordExpired = true;
                            }
                            else if(exceptionType == AuthenticationException.FIRST_LOGIN)
                            {
				try
				{
    				    	result = true;
                                	if (NmsUtil.checkAuthorization(userName, "Change Self Password"))
                                	{
                                    		message = "First Login";                                        
                                	}
                                	else
                                	{
                                    		//Unauthorised to Change the expired Password. -- Karen.
                                    		message = "First Login Unauthorized"; //No I18N
				    		AuthorizationAdmin authAdmin = (AuthorizationAdmin) NmsUtil.getAPI ("NmsAuthAdminAPI");//No I18N
				    		authAdmin.setUserStatus(userName,"enabled");//No I18N 
                                	}                                
                                	firstLogin = true;
                            	}
			    	catch(Exception exception)
			    	{
					exception.printStackTrace();
			    	}
			    }
                            else if( exceptionType == AuthenticationException.USER_EXPIRED ){
                                message = "User Expired";
                            } else if( exceptionType == AuthenticationException.LOGIN_FAILED ){
                                message = "Login Failed";
                            } else if( exceptionType == AuthenticationException.USER_DISABLED ){
                                message = "User Disabled";
                            } else if( exceptionType == AuthenticationException.USER_FORCED_OUT ){
                                message = "User Forced Out";
                            } else if( exceptionType == -1 ) {
                                message = e.getMessage();
                            }
                            if( message == null || message.trim().equals("") || message.trim().equals("null") ){
                                // if we do not get any messages, we send "No Message"
                                // to the client.
                                    message="No Message";
                            }
                            
                            if(!passwordExpired && !firstLogin){
                                out = response.getWriter();
                                out.println(message);
                                out.flush();
                                out.close();
                                return;
                            }
                            if(message.equals("First Login Unauthorized"))//No I18N
                            {
                                message="";
                                firstLogin=false;
                            }
			 }
                        if(result){
                             //Commented by Balan on 13/03/03
                            /*session = request.getSession(true);
                            session.setAttribute("userName",userName);
                            if(password != null)
                            session.setAttribute("password",password);*/
                            //Comment Ends
                            //Added by Balan on 13/03/03
                            if(!bisValidUser)
                            {
                                session = request.getSession(true);
                                session.setAttribute("userName",userName);
                                if(password != null)
                                session.setAttribute("password",password);
				// During the Remote Authentication , GetChallengeServlet will not be called from NmsClientUtil and hence the sessionid will be null.
				// So while AuthenticationServlet is executed the sessionid will be passed on here, which we will use for other
				// http communications like ChangePasswordServlet.  
				String sessionID = "SessionId="+session.getId(); // No I18N
				out = response.getWriter();
				out.println(sessionID);
                            }
                            //Add Ends

                            if(passwordExpired || firstLogin)
                            {
                                out = response.getWriter();
                                out.println(message);
                            }
                            super.service(request,response);
			} else {
                            out = response.getWriter();
                            out.println("unAuthorized");
			}
                    }
                    else{

                        out = response.getWriter();
                        out.println("unAuthorized");
                    }

                    if( out != null ){
			out.flush();
			out.close();
                    }
                 //Commented by Balan on 13/03/03
                 //}
                 //Comment Ends
	}

	/**
	This can be used to get the userName in servlets which extend this servlet.
	@return String the username
	*/
	protected String getUserName(){
		return this.userName;
	}

	/**
	This can be used to get the password in servlets which extend this servlet.
	@return String the password for the user.
	*/
    protected String getPassword(){
        return this.password;
    }

	protected boolean isPasswordExpired(){
		return this.passwordExpired;
	}

    public boolean isLimitExceeded()
    {
        List actualUsers = null;
        try
        {
            actualUsers = GenericFEAPIImpl.getAPI().getActualUsers();
        }
        catch(RemoteException re)
        {
            return true;
        }

       // if(maxSession > 0 && actualUsers.size() >= maxSession)
	if(maxSession >= 0 && actualUsers.size() >= maxSession)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    //this method will be invoked on servlet initialization
    //here the MAX_CLIENT_SESSIONS is read from the serverparameters.conf
    public void init()throws ServletException
    {
        super.init();
        String count = NmsUtil.getParameter("MAX_CLIENT_SESSIONS");
        if(count != null)
        {
            try
            {
                maxSession = Integer.parseInt(count);
            }
            catch(Exception ex)
            {
                maxSession = -1;
            }
        }
    }

}

