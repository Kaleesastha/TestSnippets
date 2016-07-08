import java.io.*;
import com.adventnet.nms.util.*;
public class DBParams
{
	public static void main(String[] args)
	{	
		try{
			File file = new File("hibernate.cfg.xml");
			DBParamsParser parser = null;
			parser = DBParamsParser.getInstance(file);
			String url = parser.getURL();
			System.out.println("The URL is: " + url);
			String user = parser.getUserName();
			System.out.println("The Username is: " + user);
			String driver = parser.getDriverName();
			System.out.println("The Drivername is: " + driver);
			String passwd = parser.getPassword();
			System.out.println("The Password is: " + passwd);
			String DBPort = parser.getDBPort();
			System.out.println("The DBPort is: " + DBPort);
			String DBHost = parser.getDBHost();
			System.out.println("The DBHost  is: " + DBHost);
		} catch (Exception exp){exp.printStackTrace();}
	}
}
