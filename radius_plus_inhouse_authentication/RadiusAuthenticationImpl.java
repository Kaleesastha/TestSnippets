/*$Id: RadiusAuthenticationImpl.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $*/
/*
 * @(#)RadiusEncryption.java
 * Copyright (c) 1996-2002 Adventnet, Inc. All Rights Reserved.
 * Please read the associated COPYRIGHTS file for more details.
 *
 * ADVENTNET, INC. MAKES NO REPRESENTATIONS OR WARRANTIES  ABOUT THE
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE, OR NON-INFRINGEMENT.  ADVENTNET, INC. SHALL
 * NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF
 * USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE  OR ITS DERIVATIVES.
 */

package com.adventnet.nms.security.authentication;
import com.adventnet.nms.util.*;
import java.rmi.RemoteException;
import com.adventnet.security.AuthUtil;

import com.adventnet.nms.util.*;
import com.adventnet.nms.util.CommonModuleAPI;
import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.security.authentication.NmsAuthenticationAPI;
import com.adventnet.security.authorization.AuthorizationAdmin;
import com.adventnet.security.authentication.*;
import com.adventnet.security.crypto.*;
import java.util.Properties;

/**
 * RadiusAuthenticationImpl class extends NmsAuthenticationAPI to do custom
 * authentication through RADIUS server.
 *
 * An entry of this class should be given in NmsProcessesBE.conf for
 * NmsAuthenticationManager process, in AuthenticationImpl parameter so that
 * this class would be called for authentication instead of default NMS
 * authentication.
 *
 * @author Rajagopal N
 * @version $Revision: 1.1.1.1 $
 * @since WebNMS 4.5.0 + SP1
 */ 

public class RadiusAuthenticationImpl extends NmsAuthenticationAPI implements CommonModuleAPI
{
    CryptoGraphAPI crypto = null;

    public RadiusAuthenticationImpl() throws RemoteException
    {
        super();
        try
        {
            crypto = NmsUtil.getCryptoGraphAPI();
            System.err.println("RADIUS authentication is initialized with: "+crypto);//No Internationalisation
        }
        catch ( Exception e )
        {
            e.printStackTrace(); 
        }
    }

    public boolean verifyCredentials(String user, String key) throws RemoteException, AuthenticationException
    {
        String password = null;
        if ( crypto != null )
        {
            try
            {
		    //Thread.dumpStack();
                //decrypt the password before authentication with RADIUS server
		System.err.println("key is  :"+key);
                password = crypto.deCrypt(key);
            }
            catch (CryptoGraphException e)
            {
                e.printStackTrace(); 
            }
        }

		String challenge = "";
		Properties userProps = null;

		String regAPIName = "NmsAuthenticationAPI";
        AuthenticationAPI authenAPI = null;
        authenAPI = (AuthenticationAPI)NmsUtil.getAPI(regAPIName);


		 try
            {
                challenge = authenAPI.getChallenge(user,userProps);
            }
            catch (AuthenticationException e)
            {
                e.printStackTrace(); 
                if (e.getMessage().equals(NmsUtil.GetString("No such user")))
                {
                    return false;
                }
                else
                {
                    NmsLogMgr.MISCERR.fail("Exception in getting the challenge",e);
                }
            }
            catch (RemoteException re)
            {
                re.printStackTrace(); 
                NmsLogMgr.MISCERR.fail("Exception in getting the challenge",re);
            }
            try
            {
                key    = AuthUtil.getChallengeKey(user,password,challenge);
            }
            catch (AuthenticationException e)
            {
                e.printStackTrace(); 
                NmsLogMgr.MISCERR.fail("Exception in verifying the password",e);
            }

        //authenticate the user using RADIUS server and return the status
	    System.err.println("password is : : "+password);
	    int radius_result = -1;
	    try{
		    System.err.println("before radius_result password is : : "+password);
		   radius_result = RadiusUtil.getInstance().authenticateUser(user,password);
	    }
	    catch(Exception exp){
		    exp.printStackTrace();
		    return false;
	    }
	    System.err.println("radius_result is : "+radius_result);
	    if( radius_result == 2){
		    System.err.println("RADIUS: ACCESS_ACCEPT");//No Internationalisation 
		    boolean isUserPresent = true;
		    try{
			    AuthorizationAdmin admin = (AuthorizationAdmin)NmsUtil.getAPI("NmsAuthAdminAPI");
			    isUserPresent = admin.isUserNamePresent(user);
			    System.err.println("isUserPresent is::"+isUserPresent);//No Internationalisation 
			    if(! isUserPresent){
				    if (password == null || password.equals("")){
					    password = "dummy";
				    }
				    admin.createUser(user,"Admin",password);}
			    System.err.println("After creating User");//No Internationalisation 
			    Thread.sleep(2500);
		    }
		    catch(Exception e){e.printStackTrace();}
		    return true;
	    }
	    else if( radius_result == 3){
		    return false;
	    }
	    else if( radius_result == 0){
		    System.err.println("Going to call inhouse authentication");
		    return super.verifyCredentials(user,key); 
	    }
	    else if( radius_result == -1){
		    return false;
	    }
	    System.err.println("After all checks returning false!!");
	    return false;

    }

    public boolean verifyCredentials(String user, AuthenticationTicket ticket) throws RemoteException, AuthenticationException
    {
	    System.err.println("password from ticket.getPassword is :  "+ticket.getPassword());//No Internationalisation
        return verifyCredentials(user,ticket.getPassword());
    }
    public String getChallenge(String user, Properties props) throws RemoteException, AuthenticationException
    {
	    try{
		    String challenge1 = super.getChallenge(user,props);
		    return challenge1;
	    }
	    catch(AuthenticationException exp)
	    {
		    System.err.println("Exception in getChallenge :: ");//No Internationalisation
		    if(exp.getMessage().indexOf("No such user") != -1)
		    {
			    System.err.println("returning radius-user-Authorized ");//No Internationalisation
			    return "authorized";
		    }
	    }
	    return null;
    }
}
