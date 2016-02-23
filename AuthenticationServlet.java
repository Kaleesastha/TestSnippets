// $Id: AuthenticationServlet.java,v 1.8.2.11 2002/03/16 13:06:06 fakirm Exp $

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
import java.util.Vector;
import java.rmi.RemoteException;
import com.adventnet.nms.commonfe.GenericFEAPIImpl;

public class AuthenticationServlet extends HttpServlet{

	String userName = null;
        String  messageDigest = null;
        String password = null;
	boolean passwordExpired = false;
    boolean validate = true;
    boolean firstLogin =false;
 
    static boolean firstTime=true;
    //Added AdventNet
    static int maxSession=4; //this is default value incase of wrong configuration in serverparameters.conf
    
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
                String validationReq = null;
                String forCheck = null;
		HttpSession session = request.getSession(true);
		PrintWriter out = null;

                if( (validationReq = request.getHeader("validate"))== null )
                {
                    validationReq = request.getParameter("validate");
                }
                
                if(session != null)
                {
                
                    userName = (String) session.getAttribute("userName");


                    if(validationReq == null)
                       validationReq = (String)session.getAttribute("validate");
                } 

                if(validationReq != null)
                {
                    validate= new  Boolean(validationReq).booleanValue();
                }
                
                if(!validate)
                {
                    super.service(request,response);
                    /*out = response.getWriter();
                    out.println("Validation Not Required"); 
                    out.flush(); 
                    out.close(); 
                    return;*/

                }
                else
                {
                    if(userName == null)
                    {
                        if((userName = request.getHeader("userName"))== null )
                        {
                            userName = request.getParameter("userName");
                        }
                    }
                    
                    if((messageDigest = request.getHeader("messageDigest")) == null)
                    {
                        messageDigest =request.getParameter("messageDigest");
                    }

                    if(messageDigest != null)
                    {
                        messageDigest = URLDecoder.decode(messageDigest);
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

                            forCheck = password;
                        }
                    }    
                    
                    String hostAddress = request.getParameter("hostaddress");
                    Properties hostProperties = new Properties();
                    // as per the security team requirement hostAddress is sent as hostname
                    if(hostAddress != null)
                        hostProperties.put("hostname",hostAddress);
                    
                    if(userName!=null && forCheck!=null){
			boolean result = false;
                       
                        boolean sessionExceed=false;//Adventnet Added
                        try{
                            
                            //AdventNet Added
                            if(!isItOkayToAllowUser(userName))
                            {
                                sessionExceed=true;
                            }
                            //end of AdventNet Addition
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
                            if( exceptionType == AuthenticationException.PASSWORD_EXPIRED ){
                                // setting the result to true so that 
                                // the userName is put in the session, 
                                // which means the user is authenticated.
                                result = true;
                                message = "Password Expired";
                                // setting passwordExpired to true so that 
                                // the PASSWORDEXPIRED parameter is sent to the
                                // client from StartClientServlet.
                                passwordExpired = true;
                            }
                            else if(exceptionType == AuthenticationException.FIRST_LOGIN){
                                result = true;
                                message = "First Login";
                                firstLogin = true;
                            }else if( exceptionType == AuthenticationException.USER_EXPIRED ){
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
			 }
                        if(result){
                            session = request.getSession(true);
                            session.setAttribute("validate","False");
                            
                            if(password != null)
                                session.setAttribute("password",password);
                            
                            if(passwordExpired || firstLogin)
                            {
                                out = response.getWriter();
                                out.println(message);
                            }
                            
                            if(sessionExceed)//Adventnet Added
                            { //end of Adventnet Addition
                                
                                //hyu
                                message = "My message";
                                System.out.println("got here");
                                out = response.getWriter();
                                out.println(message);
                                out.flush();
                                out.close();
                                //end of hyu
                                
                                //Adventnet Added
                                sessionExceed=false;
                                return;
                            } 
                            //end of Adventnet Addition 
                            
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
		}
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

    public static boolean isItOkayToAllowUser(String userName)
        {
            Vector activeUsers =new Vector();
            try
            {
                activeUsers = GenericFEAPIImpl.getAPI().getActiveUsers();
            }
            catch(RemoteException re)
            {
                return false;
            }
            boolean userAlreadyLoggedIn = activeUsers.contains(userName);
            boolean reachedMaxAccounts = activeUsers.size() >=maxSession /*MAX_COUNT*/;
            return !(userAlreadyLoggedIn || reachedMaxAccounts);
        }
   
    //AdventNet Added
    //this method will be invoked on servlet initialization 
    //here the MAX_CLIENT_SESSIONS is read from the serverparameters.conf
    public void init()throws ServletException
    {
        super.init();
        maxSession=Integer.parseInt(NmsUtil.getParameter("MAX_CLIENT_SESSIONS"));//this is read from serverparameters.conf
    }       
    //end of AdventNet addition
}



