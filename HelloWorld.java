package test;
import java.lang.Integer;
import java.io.*;
import java.util.regex.Pattern;
public class HelloWorld
{
	public static void main(String args[])
	{
		/*try{
			//String file = args[0].substring(0,args[0].length()-1);
			String file = args[0].trim();
			File f = new File (file);
			System.err.println("Does the file "+file+" exists? "+f.exists());
		}
		catch(Exception exp){exp.printStackTrace();}*/
		/*String s = args[0];
		boolean hasNonAlpha = s.matches("^.*[^a-zA-Z0-9 ].*$");
		System.err.println("hasNonAlpha is : "+hasNonAlpha);*/
		//String s = args[0];
		System.err.println("==> "+System.getProperty("user.dir"));
		/*Pattern p = Pattern.compile("[^a-zA-Z]");
		boolean hasSpecialChar = p.matcher(s).find();
		System.err.println("hasNonAlpha is : "+hasSpecialChar);*/
		//boolean result = s.matches("[-+]?\\d*\\.?\\d+");
		//System.err.println("result is : "+result);
	}
	static int getInt()
	{
		//unsigned int a = 4294967296;
		int a = 429493;
		return a;
	}
}
