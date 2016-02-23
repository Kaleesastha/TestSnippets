package test;

import com.adventnet.nms.util.*;
import java.lang.reflect.*;

public class ReflectionExample implements RunProcessInterface
{
	public void callMain(String args[])
	{
		try{
			String passwordText = args[0];
			String plainText = "";
			if(passwordText.startsWith("0x"))
			{
				passwordText = passwordText.substring(2,passwordText.length());
				String coding = "com.adventnet.security.authorization.Coding";
				Class class_NmsUtil = Class.forName("com.adventnet.nms.util.NmsUtil");
				Method method_getParameter = (Method)class_NmsUtil.getMethod("getParameter", new Class[]{java.lang.String.class});
				Object codingClass = method_getParameter.invoke(class_NmsUtil, new Object[]{"CRYPTO_CLASS"});
				System.err.println("coding class is:: "+codingClass);
				if (codingClass != null)
				{
					coding = codingClass.toString();
				}
				Class class_Coding = Class.forName(coding);
				Method method_convertFromBase = (Method)class_Coding.getMethod("convertFromBase", new Class[]{java.lang.String.class});
				Object plaintextPassword = method_convertFromBase.invoke(class_Coding,new Object[]{passwordText});
				plainText = plaintextPassword.toString();
			}
			else
			{
				plainText = passwordText;
			}
			System.err.println("The password is " + plainText);
		}
		catch(Exception e) {e.printStackTrace();}
	}
	public boolean isInitialized() {return true;}
	public void shutDown(){}
}
