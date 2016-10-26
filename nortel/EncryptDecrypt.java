package com.adventnet.nms.util;
import com.nortel.iems.common.IEMSCryptoGraphAPIImpl;
import java.io.*;
public class EncryptDecrypt {
	public static void main(String[] s) {
		try {
			IEMSCryptoGraphAPIImpl iems = new IEMSCryptoGraphAPIImpl();
			String encrypted = iems.enCrypt(s[0]);
			String decrypted = iems.deCrypt(encrypted);
			System.err.println("Encrypted string is : "+encrypted);
			System.err.println("Decrypted string is : "+decrypted);
		} catch (Exception e) {e.printStackTrace();} 
	}
}
