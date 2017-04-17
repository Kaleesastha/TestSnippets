// $Id: ChangePasswordServlet.java,v 1.2 2007/06/29 15:16:10 sunilg Exp $

package com.adventnet.nms.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;

import com.adventnet.nms.authentication.UserConfigAPI;
import com.adventnet.nms.authentication.UserConfigException;
import com.adventnet.nms.util.NmsUtil;
import com.adventnet.security.AuthUtil;
import java.net.URLDecoder;//added by Balan on 13/03/03
import com.adventnet.security.authentication.AuthenticationException; //For Ticket ID:12115291 - fixing the first time login issue

public class ChangePasswordServlet extends HttpServlet {


	private String userName = null;
    private String oldPassword = null;
	private String newPassword = null;
	private int passwordAge = 0;
	private boolean result = false;
	private boolean resetPassword = false;
        private boolean firstLogin = false;        //For Ticket ID:12115291 - fixing the first time login issue
	UserConfigAPI userConfigAPI = (UserConfigAPI)NmsUtil.getAPI("UserConfigAPI");

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// initializing the variables for serving this request.
		userName = null;
		oldPassword = null;
		newPassword = null;
		String temp = null;
		passwordAge = 0;
		result = false;
		resetPassword = false;

		PrintWriter out = response.getWriter();

		// trying to get the already created session.
		HttpSession session = request.getSession(true);

		if( session != null ){
			if(session.getAttribute("userName") == null){
				out.println("unAthorized");
				out.flush();
				out.close();
				return;
			}
		}else{
			// this will occur only iff the client does not send the
			// JSESSIONID in the header.
			out.println("session is null");
		}

		// trying to get the userName from the header
		// and if it is null, it is got from the request.
		userName = request.getHeader("userName");
		if( userName == null ){
			userName = request.getParameter("userName");
		}

		// validating the parameters
		if( userName == null ){
			out.println("Required Parameter(s) missing");
			return;
		}

		resetPassword = new Boolean(request.getHeader("resetPassword")).booleanValue();

		// resetPassword will be true when the client wants
		// to continue with the same password. Otherwise it would
		// be false/null, in which case the newPassword and passwordAge
		// will passed from the client.
		if( !resetPassword ){
			//For Ticket ID:12115291 - fixing the first time login issue
                          if(AuthenticationServlet.FIRST_LOGIN == AuthenticationException.FIRST_LOGIN || AuthenticationServlet.FIRST_LOGIN == AuthenticationException.PASSWORD_EXPIRED)
		  {
 			 firstLogin = true;
	 		}
                        
                         AuthenticationServlet.FIRST_LOGIN = 1111; 
			// Fix ends

            // getting the oldPassword from the header.
			// and if it is null, it is got from the request.
			oldPassword = request.getHeader("curPassword");//No I18N
			if( oldPassword == null ){
				oldPassword = request.getParameter("curPassword");//No I18N
			}

			// getting the newPassword from the header.
			// and if it is null, it is got from the request.
			newPassword = request.getHeader("newPassword");
			if( newPassword == null ){
				newPassword = request.getParameter("newPassword");
			}

			// getting the passwordAge from the header.
			// and if it is null, it is got from the request.
			temp = request.getHeader("passwordAge");
			if( temp == null ){
				temp = request.getParameter("passwordAge");
			}

			// if newPassword or password are not passed when
			// the user wants to change his password, the servlet
			// will return;
                        if(firstLogin)
                        {
                          if( newPassword == null || temp == null){
                                out.println("Required Parameter(s) missing");
                                out.flush();
                                out.close();
                                return;
                        }

                        }
			//For Ticket ID:12115291 - fixing the first time login issue
			else 
                        {
                              if(oldPassword==null || newPassword == null || temp == null){
				out.println("Required Parameter(s) missing");
				out.flush();
				out.close();
				return;
			}
                        }    
		}

		try{
			// calling the changePassword method in UserConfigAPI
			if(resetPassword){
				// if the user wants to reset the password,
				// the oldPassword will be passed as newPassword.
				// and in this case, passwordAge will not be set
				// and its' value will be reset with the value
				// in the database. This is taken care by changePassword().
				
				//For Ticket ID:12115291 - fixing the first time login issue
			result = userConfigAPI.changePassword(userName, "", ""); //No Internationalization
			}else{
                   
                if(!firstLogin)
               { 
                oldPassword = URLDecoder.decode(oldPassword);
                //Added by Balan on 13/03/03 for the bug client hangs while changing
                //the password which starts with "W/w"
               newPassword = URLDecoder.decode(newPassword);
                //Add Ends
			result = userConfigAPI.changePassword(userName, AuthUtil.decryptString(oldPassword,"WqrTnvA"),AuthUtil.decryptString(newPassword,"WqrTnvA"));//No I18N    
                }    
                   else{
                    newPassword = URLDecoder.decode(newPassword);  
                      result = userConfigAPI.changePassword(userName,"" ,AuthUtil.decryptString(newPassword,"WqrTnvA"));//No I18N             
                        firstLogin =false; //  For Ticket ID:12115291 - fixing the first time login issue
                        }


				if(result){

                                        
					// since the password has been changed successfully
					// it has to be updated in the session also.
					session.setAttribute("password",AuthUtil.decryptString(newPassword,"WqrTnvA"));

					// setting the password age.
                    if(temp != null && !temp.equals(""))
                    {
                        passwordAge = Integer.parseInt(temp);
                        userConfigAPI.setPasswordAge(userName, passwordAge);
                    }
				}
			}
			if(!result){
				// if changePassword() method returns false,
				// we return failure.
				out.println("failure");
				out.flush();
				out.close();
				return;
			}
		}catch(RemoteException e){
			// if RemoteException occurs, we return failure.
			out.println("failure");
			out.flush();
			out.close();
			return;
		}catch(UserConfigException e){
			// if UserConfigException occurs, we return failure.
			out.println(e.getMessage());
			out.flush();
			out.close();
			return;
		}
		out.println("success");
		out.flush();
		out.close();
	}
}
