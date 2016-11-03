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

import java.rmi.RemoteException;

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
            System.out.println("RADIUS authentication is initialized with: "+crypto);//No Internationalisation
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
                //decrypt the password before authentication with RADIUS server
                password = crypto.deCrypt(key);
            }
            catch (CryptoGraphException e)
            {
                e.printStackTrace(); 
            }
        }
        //authenticate the user using RADIUS server and return the status
        return RadiusUtil.getInstance().authenticateUser(user,password);
    }

    public boolean verifyCredentials(String user, AuthenticationTicket ticket) throws RemoteException, AuthenticationException
    {
        return verifyCredentials(user,ticket.getPassword());
    }
}
