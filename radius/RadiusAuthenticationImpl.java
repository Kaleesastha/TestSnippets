package com.adventnet.nms.security.authentication;

import java.rmi.RemoteException;
import java.util.Properties;

import com.adventnet.nms.util.CommonModuleAPI;
import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.security.authentication.NmsAuthenticationAPI;
import com.adventnet.security.authentication.*;
import com.adventnet.security.crypto.*;

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
 * @version $Revision: 1.1.4.3 $
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
            System.err.println("NEW RADIUS authentication is initialized with: "+crypto);//No Internationalisation
        }
        catch ( Exception e )
        {
            e.printStackTrace(); 
        }
    }

    public boolean verifyCredentials(String user, String key) throws RemoteException, AuthenticationException
    {
	    String password = null;
	    System.err.println("Before decrypt");
	    System.err.println("User ID ::" + user);
	    System.err.println("Password :: " + key);

	    if ( crypto != null )
	    {
		    try
		    {
			    //decrypt the password before authentication with RADIUS server
			    password = crypto.deCrypt(key);
		    }
		    catch (CryptoGraphException e)
		    {
			    e.printStackTrace();
			    password = key;
		    }
	    }
	    System.err.println("After decrypt");
	    System.err.println("User ID ::" + user);
	    System.err.println("Password :: " + password);
	    //authenticate the user using RADIUS server and return the status
	    return RadiusUtil.getInstance().authenticateUser(user,password);
    }

    public boolean verifyCredentials(String user, AuthenticationTicket ticket) throws RemoteException, AuthenticationException
    {
        System.err.println("user proprties ::" + ticket.getUserProperties());
        System.err.println("get key ::" + ticket.getKey());
        System.err.println("get Pass ::" + ticket.getPassword());
        return verifyCredentials(user,ticket.getPassword());
    }

	public String getChallenge(String user) throws RemoteException, AuthenticationException
	{
		System.err.println("getChallenge called for user :: "+user);//No Internationalisation
		try{
			String challenge1 = super.getChallenge(user);
			return challenge1;
		}
		catch(AuthenticationException exp)
		{
			System.err.println("Exception in getChallenge :: ");//No Internationalisation
			exp.printStackTrace();
			if(exp.getMessage().indexOf("No such user") != -1)
			{
				System.err.println("returning radius-user-Authorized ");//No Internationalisation
				return "authorized";
			}
		}
		System.err.println("returning NULL!! ");//No Internationalisation
		return null;
	}
	public String getChallenge(String user, Properties props) throws RemoteException, AuthenticationException
	{
		System.err.println("getChallenge called for user with props:: "+user+"=="+props);//No Internationalisation
		try{
			String challenge1 = super.getChallenge(user,props);
			return challenge1;
		}
		catch(AuthenticationException exp)
		{
			System.err.println("Exception in getChallenge :: ");//No Internationalisation
			exp.printStackTrace();
			if(exp.getMessage().indexOf("No such user") != -1)
			{
				System.err.println("returning radius-user-Authorized ");//No Internationalisation
				return "authorized";
			}
		}
		System.err.println("returning NULL!! ");//No Internationalisation
		return null;
	}
}
