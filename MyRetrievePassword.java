import com.adventnet.security.authorization.*;
import com.adventnet.security.authorization.Coding;
import java.lang.reflect.*;
public class MyRetrievePassword
{
   public static void main(String args[])
   {
	   try{
		if(args.length < 1)
		{
			System.out.println("Usage: java  -cp $NMS_CLASSES MyRetrievePassword <Encrypted password>");
			System.exit(0);
		}
		String plainText = com.adventnet.security.authorization.Coding.convertFromBase(args[0]);
		System.out.println("The password is " + plainText);
		System.out.println("==================================");
		String coding = "com.adventnet.security.authorization.Coding";
		Class class_NmsUtil = Class.forName("com.adventnet.nms.util.NmsUtil");
		Method method_getParameter = (Method)class_NmsUtil.getMethod("getParameter", new Class[]{java.lang.String.class});
		Object codingClass = method_getParameter.invoke(class_NmsUtil, new Object[]{"CRYPTO_CLASS"});
		System.out.println("coding class is:: "+codingClass);
		if (codingClass != null)
		{
			coding = codingClass.toString();
		}
		Class class_Coding = Class.forName(coding);
		Method method_convertFromBase = (Method)class_Coding.getMethod("convertFromBase", new Class[]{java.lang.String.class});
		Object plaintextPassword = method_convertFromBase.invoke(class_Coding,new Object[]{args[0]});
		System.err.println("plain text password is:" + plaintextPassword.toString());
	      }
	   catch(Exception e)
	   {e.printStackTrace();}
   }
}
