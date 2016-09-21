/*$Id: RadiusEncryption.java,v 1.1.4.1 2004/02/14 12:31:24 rajagopal Exp $*/
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

package com.adventnet.security.authentication;

import com.adventnet.security.crypto.CryptoGraphAPI;
import com.adventnet.security.authorization.Coding;

/**
 * Example implementation class for encrypting and decrypting password.
 * Client uses this class for encrypting the password before sending 
 * the same to server. Server decrypts the encrypted password from the client 
 * and use the same for RADIUS authentication.
 * So this class, should be present in both the server and client.
 *
 * An entry of this class should be given in WebNMS/conf/serverparameters.conf
 * as follows for the server usage:
 *
 * CRYPTO_CLASS com.adventnet.security.authentication.RadiusEncryption
 * 
 * In the client, this parameter should be given in
 * startApplicationClient.sh/bat as a command line argument
 *
 * @author Rajagopal N
 * @version $Revision: 1.1.4.1 $
 * @since WebNMS 4.5.0 + SP1
 */  
public class RadiusEncryption implements CryptoGraphAPI
{
    /**
     * Encrypts the plain password
     * @param plainText password to be encrypted
     * @return String encrypted password
     */
    public String enCrypt(String plainText)
    {
       return Coding.convertToNewBase(plainText); 
    }
    
    /**
     * Decrypts the encrypted password
     * @param cipherText encrypted password to be decrypted
     * @return String plain password
     */
    public String deCrypt(String cipherText) 
    {
        String toReturn = null;
        try
        {
            toReturn = Coding.convertFromBase(cipherText);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return toReturn;
    }
}
