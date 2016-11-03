package com.adventnet.nms.security.authentication;

import java.rmi.RemoteException;
import java.util.Properties;
import java.util.Hashtable;

import com.adventnet.nms.util.CommonModuleAPI;
import com.adventnet.nms.util.NmsUtil;
import com.adventnet.security.authentication.*;
import com.adventnet.security.crypto.*;
import com.adventnet.security.AuthUtil;

import com.adventnet.nms.security.authentication.NmsAuthenticationAPI;
import com.adventnet.nms.util.PureServerUtilsFE;
import com.adventnet.nms.util.PureUtils;
import com.adventnet.nms.store.relational.RelationalAPI;
import com.adventnet.management.i18n.AdventNetResourceBundle;
import com.adventnet.security.authorization.RelationalAuthAuditAPI;
import com.adventnet.security.authorization.*;
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
    boolean isVerified = false; //MITEL: To ensure that the user is authenticated
    Hashtable userProp = new Hashtable();
    /*RelationalAPI authRelAPI = null;
    RelationalAuthenticationAPI relAuthenAPI = null;
    RelationalAuthAuditAPI authAudit = null;*/

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

    /*public void init(Object obj) throws RemoteException, AuthenticationException
    {
	    System.err.println("init called");
	    if(obj instanceof RelationalAPI){
		    System.err.println("NOT throwing exception!");
		    this.authRelAPI = (RelationalAPI)obj;
	    }
	    else
	    {
		    System.err.println("throwing exception!");
		    throw new AuthenticationException(AdventNetResourceBundle.getInstance().getString("RelationalAPI"));
	    }
	    relAuthenAPI = new RelationalAuthenticationAPI();
	    relAuthenAPI.setSecurityConfPath(PureUtils.rootDir+"conf/securitydbData.xml");
	    relAuthenAPI.init(authRelAPI);
	    authAudit = new RelationalAuthAuditAPI();
	    try 
	    {
		    authAudit.init(authRelAPI);
	    }
	    catch ( Exception e)
	    {
		    throw new AuthenticationException(e.getMessage());
	    }
	    relAuthenAPI.databaseName = databaseName;
    }*/
    public boolean verifyCredentials(String user, String key) throws RemoteException, AuthenticationException
    {
	    String password = null;
	    System.err.println("1: Before decrypt");
	    System.err.println("User ID ::" + user);
	    System.err.println("Password :: " + key);

	if ( crypto != null )
	{
		if(!user.equalsIgnoreCase("test") && !user.equalsIgnoreCase("master")){
			//return super.verifyCredentials(user,key);
				    String challenge = PureServerUtilsFE.getChallenge(user);
				    System.err.println("RadiusAuthenticationImpl called with user "+user+", "+challenge);
				    try
				    {
					    //decrypt the password before authentication with RADIUS server
					    password = crypto.deCrypt(key);
				    }
				    catch(CryptoGraphException exp){
					    exp.printStackTrace();
					    password = key;
				    }
				    catch (ArrayIndexOutOfBoundsException e)
				    {
					    e.printStackTrace();
					    return super.verifyCredentials(user,key);
				    }
				    String messageDigest = AuthUtil.getChallengeKey(user,password,challenge);
				    return super.verifyCredentials(user,messageDigest);
		}
		else{
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
		System.err.println("After decrypt Password :: " + password);
		isVerified = RadiusUtil.getInstance().authenticateUser(user,password);//MITEL: To authenticate the user.
	}
//1111
	    /*if ( crypto != null )
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
	    else
	    {
		    isVerified = RadiusUtil.getInstance().authenticateUser(user,password);//MITEL: To authenticate the user.
	    }*/
	    System.err.println("isVerified is : "+isVerified);
	    /*if(isVerified)
	    {
		    AuthorizationAdmin authAdmin = null;//No I18N
		    try{
			    authAdmin = (AuthorizationAdmin) NmsUtil.getAPI ("NmsAuthAdminAPI");//No I18N
		    }
		    catch(Exception exp){
			    exp.printStackTrace();
		    }
		    DefaultValidator validator = new DefaultValidator();
		    boolean isPasswordValid = validator.validate(user,password);//MITEL: To check whether the authenticated user's password is a 'valid' one
		    if (!isPasswordValid)
		    {
			    try{
				    authAdmin.setUserStatus(user,"passwordExpired");//MITEL: If the password is NOT meeting the rules, make the status as passwordExpired
			    }
			    catch(Exception exp){
				    exp.printStackTrace();
			    }
		    }
		    try{userProp = authAdmin.getAllAttributes(user);} catch(Exception exp){exp.printStackTrace();}
		    System.err.println("userProp is : "+userProp);
		    String userStatus = (String)userProp.get("status");
		    if(userStatus.equals("passwordExpired"))//MITEL: If the status is 'passwordExpired' or 'new' throw AuthenticationException but with proper message
		    {
			    throw new AuthenticationException(AuthenticationException.PASSWORD_EXPIRED,AdventNetResourceBundle.getInstance().getString("Password Expired"));
		    }
		    else if(userStatus.equals("new"))//MITEL: If the user is not yet logged in, throw AuthenticationException with 'First Login' as message
		    {
			    throw new AuthenticationException(AuthenticationException.FIRST_LOGIN,AdventNetResourceBundle.getInstance().getString("First Login"));
		    }
	    }
	    else
	    {
		    return false;
	    }*/
	    return isVerified;
    }

    public boolean verifyCredentials(String user, AuthenticationTicket ticket) throws RemoteException, AuthenticationException
    {
        System.out.println("user proprties ::" + ticket.getUserProperties());
	Properties hostProperties = ticket.getUserProperties();
	String key=ticket.getKey();
	System.err.println("key :: " + key);
	String password=ticket.getPassword();
	System.err.println("Password :: " + password);
	System.err.println("verifyCredentials called for "+user);
	System.err.println("2: Before decrypt");
	System.err.println("User ID ::" + user);

	if ( crypto != null )
	{
		if(!user.equalsIgnoreCase("test") && !user.equalsIgnoreCase("master")){
			//if((key == null && password != null) || key.equals(password)) {
			if((key == null && password != null)) {
				String challenge = PureServerUtilsFE.getChallenge(user);
				try{
					password = crypto.deCrypt(password);
				}
				catch (Exception expp){expp.printStackTrace();} 
				String messageDigest = AuthUtil.getChallengeKey(user,password,challenge);
				return super.verifyCredentials(user,messageDigest);
				//return super.verifyCredentials(user,ticket);
			}else{
				return super.verifyCredentials(user,ticket.getPassword());
			}
		}
		else{
		try
		{
			//decrypt the password before authentication with RADIUS server
			password = crypto.deCrypt(password);
		}
		catch (CryptoGraphException e)
		{
			e.printStackTrace();
			password = key;
		}
			System.err.println("After decrypt Password :: " + password);
			isVerified = RadiusUtil.getInstance().authenticateUser(user,password);//MITEL: To authenticate the user.
		}
	}
	/*else
	{
		try{
			System.err.println("Password :: " + key);
			System.err.println("verifyCredentials called for other user "+user);
			//isVerified = super.verifyCredentials(user,ticket);//MITEL: To authenticate the user.
			isVerified = PureServerUtilsFE.isPasswordCorrect(user,password);//MITEL: To authenticate the user.
		}
		catch(AuthenticationException exp)
		{
			exp.printStackTrace();
			if(exp.getExceptionType() == AuthenticationException.PASSWORD_EXPIRED)
				throw new AuthenticationException(AuthenticationException.PASSWORD_EXPIRED,AdventNetResourceBundle.getInstance().getString("Password Expired"));
			else if(exp.getExceptionType() == AuthenticationException.FIRST_LOGIN)
				throw new AuthenticationException(AuthenticationException.FIRST_LOGIN,AdventNetResourceBundle.getInstance().getString("First Login"));
		}
	}*/
	System.err.println("isVerified is : "+isVerified);
	/*if(isVerified)
	{
		AuthorizationAdmin authAdmin = null;//No I18N
		try{
			authAdmin = (AuthorizationAdmin) NmsUtil.getAPI ("NmsAuthAdminAPI");//No I18N
		}
		catch(Exception exp){
			exp.printStackTrace();
		}
		DefaultValidator validator = new DefaultValidator();
		boolean isPasswordValid = validator.validate(user,password);//MITEL: To check whether the authenticated user's password is a 'valid' one
		if (!isPasswordValid)
		{
			try{
				authAdmin.setUserStatus(user,"passwordExpired");//MITEL: If the password is NOT meeting the rules, make the status as passwordExpired
			}
			catch(Exception exp){
				exp.printStackTrace();
			}
		}
		try{userProp = authAdmin.getAllAttributes(user);} catch(Exception exp){exp.printStackTrace();}
		System.err.println("userProp is : "+userProp);
		String userStatus = (String)userProp.get("status");
		if(userStatus.equals("passwordExpired"))//MITEL: If the status is 'passwordExpired' or 'new' throw AuthenticationException but with proper message
		{
			throw new AuthenticationException(AuthenticationException.PASSWORD_EXPIRED,AdventNetResourceBundle.getInstance().getString("Password Expired"));
		}
		else if(userStatus.equals("new"))//MITEL: If the user is not yet logged in, throw AuthenticationException with 'First Login' as message
		{
			throw new AuthenticationException(AuthenticationException.FIRST_LOGIN,AdventNetResourceBundle.getInstance().getString("First Login"));
		}
	}
	else
	{
		return false;
	}*/
	return isVerified;
/**/
        //return verifyCredentials(user,ticket.getPassword());
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
	//MITEL: Just return a DUMMY test as challenge as this is not at all needed in external authentication
	public String getChallenge(String user, Properties props) throws RemoteException, AuthenticationException
	{
		System.err.println("getChallenge called for user with props:: "+user+"=="+props);//No Internationalisation
			//return "test";
		if(user.equals("test") || user.equals("master"))
		{
			System.err.println("getChallenge called for user  "+user);
			return "test";
		}
		else{
			System.err.println("getChallenge in else called for user  "+user);
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
		}
		return null;
	}
}
