package com.nortel.iems.common;

import com.adventnet.security.crypto.CryptoGraphAPI;
import com.adventnet.security.crypto.CryptoGraphException;
import com.adventnet.security.authorization.Base64;
import com.adventnet.security.crypto.CryptoGraphException;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.SecretKey;
import javax.crypto.spec.DESedeKeySpec;
import java.security.spec.KeySpec;

public class IEMSCryptoGraphAPIImpl implements CryptoGraphAPI
 {

    private static final String UNICODE_FORMAT = "UTF8";
    private static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
    private static final String DEFAULT_ENCRYPTION_KEY = "This is a fairly long phrase used to encrypt and decrypt";

    private static Cipher cipher;
    private static KeySpec keySpec;
    private static SecretKeyFactory keyFactory;

    static
    {
        try
        {
            byte[] keyAsBytes = DEFAULT_ENCRYPTION_KEY.getBytes(UNICODE_FORMAT);

            keySpec = new DESedeKeySpec(keyAsBytes);

            keyFactory = SecretKeyFactory.getInstance(DESEDE_ENCRYPTION_SCHEME);

            cipher = Cipher.getInstance(DESEDE_ENCRYPTION_SCHEME);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }


    private String bytes2String(byte[] bytes)
    {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < bytes.length; i++)
        {
            stringBuffer.append((char) bytes[i]);
        }
        return stringBuffer.toString();
    }



public String enCrypt ( String encryptString ) throws CryptoGraphException
{
    try
    {
        SecretKey key = keyFactory.generateSecret(keySpec);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] cleartext = encryptString.getBytes(UNICODE_FORMAT);
        byte[] ciphertext = cipher.doFinal(cleartext);

        return Base64.encodeBytes(ciphertext);
    }
    catch(Exception ex)
    {
        ex.printStackTrace();
        return encryptString;
    }

}

public String deCrypt ( String decryptString ) throws CryptoGraphException
{
	/*{
	  try
	  {
	  SecretKey key = keyFactory.generateSecret(keySpec);
	  cipher.init(Cipher.DECRYPT_MODE, key);
	  byte[] cleartext = Base64.decode(decryptString);
	  byte[] ciphertext = cipher.doFinal(cleartext);

	  return bytes2String(ciphertext);
	  }
	  catch(Exception ex)
	  {
	  ex.printStackTrace();
	  return decryptString;
	  }
	  }*/
	try
	{
		synchronized (cipher)
		{
			SecretKey key = keyFactory.generateSecret(keySpec);
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] cleartext = Base64.decode(decryptString);
			byte[] ciphertext = cipher.doFinal(cleartext);
			return bytes2String(ciphertext);
		}


	}
	catch(Exception ex)
	{
		ex.printStackTrace();
		return decryptString;
	}
}
}
