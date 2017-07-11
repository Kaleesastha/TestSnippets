//$Id$
package com.adventnet.security.authentication;

import java.rmi.RemoteException;
import java.util.*;
import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.security.authentication.NmsAuthenticationAPI;
import com.adventnet.security.authentication.*;
import com.adventnet.security.authorization.AuthorizationAdmin;


public class ExtendedAuthenticationImpl extends NmsAuthenticationAPI 
{
	private static RelationalAuthenticationAPI relAuthenticationAPI = null;
	public ExtendedAuthenticationImpl() throws RemoteException
	{
		super();
	}

	public void init(Object obj) throws RemoteException, AuthenticationException{
		super.init(obj);
		relAuthenticationAPI = relAuthenAPI;
	}
	public static Hashtable getCredentialsForUser(String user) {
		try{
			return relAuthenticationAPI.getUserAndPasswords(user);
		} catch (Exception exp){ exp.printStackTrace();return null;}
	}
}
