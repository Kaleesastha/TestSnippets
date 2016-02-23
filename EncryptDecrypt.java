package com.adventnet.utils;
import com.adventnet.utils.StringEncrypter;
import com.adventnet.utils.SnmpUtils;

public class EncryptDecrypt
{
    
    public static void main(String[] s)
    {
	    if(s.length < 1)
	    {
		    System.err.println("Usage: java com.adventnet.utils.EncryptDecrypt password");
		    System.exit(0);
	    }
	    try
	    {
		    System.err.println("password is:"+s[0]);
		    StringEncrypter se = new  StringEncrypter();
		    String encrytedText = se.encrypt(s[0]);
		    System.err.println("Encrypted String is::"+encrytedText);

		    byte[] decrypByteArray = se.decrypt(SnmpUtils.getBytes(encrytedText));
		    String decryptedPassword = new String(decrypByteArray);
		    System.err.println("Decrypted password is::"+decryptedPassword);
	    }
	    catch(Exception e)
	    {
		    e.printStackTrace();
	    }
    }
}
