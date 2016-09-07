/*$Id: PasswordEncryptionImpl.java,v 1.1.4.1 2015/03/24 13:43:39 venkatramanan Exp $
 * Created on Aug 8, 2007
 * */

package com.adventnet.security.authentication;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.adventnet.security.authentication.Base64;
import com.adventnet.security.authentication.PasswordOneWayEncryptionAPI;
import com.adventnet.security.authentication.PasswordEncryptionException;

/**
 * @author jegannathan
 *
 */
public class PasswordEncryptionImpl implements PasswordOneWayEncryptionAPI {
// ADDON_ENCRYPTION_KEY will be appended with the Password and the will be digested.
	private static final String ADDON_ENCRYPTION_KEY = "oneway"; // No I18N
	
	/* (non-Javadoc)
	 * @see com.adventnet.security.authentication.PasswordOneWayEncryptionAPI#enCrypt(java.lang.String)
	 */
	public String enCrypt(String plainText) throws PasswordEncryptionException 
	{

		MessageDigest md = null;
		try
		{
			md = MessageDigest.getInstance("SHA-256"); // No I18N
		}
		catch(NoSuchAlgorithmException e)
		{
			throw new PasswordEncryptionException(e.getMessage(),e);
		}
		try
		{
			md.update(plainText.getBytes("UTF8")); // No I18N
			md.update(ADDON_ENCRYPTION_KEY.getBytes("UTF8")); // No I18N
		}
		catch(UnsupportedEncodingException e)
		{
			throw new PasswordEncryptionException(e.getMessage(),e);
		}

		byte raw[] = md.digest();
		return Base64.encodeBytes(raw);

	}
}

