import java.util.Properties;
import com.adventnet.nms.util.ClientFrameWorkAPI;
import com.adventnet.security.ui.*;
import javax.swing.*;

public class CreateSession
{

	static SecurityModel secModel = null;

	public CreateSession()
	{
		
	}
	
	static void doCreate(String[] args)
	{
		Properties prop = new Properties();
		prop.put("HOSTNAME", args[0]);
		prop.put("USERNAME", args[1]);
		prop.put("PASSWORD", args[2]);
		prop.put("WEB-SERVER-PORT", args[3]);
		
		ClientFrameWorkAPI clientFrame = null;
		
		
		try
		{
			clientFrame = new ClientFrameWorkAPI(prop);
			secModel = new SecurityModel("com.adventnet.security.ui.SecuritySession");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	
		//AuthMain securityclient = new AuthMain((JApplet)clientFrame.getAppletInstance());
	}

	static void closeSession()
	{
		secModel.cleanUp();
	}
	
	public static void main(String[] args)
	{
		if(args.length<4)
		{
			System.out.println("<Usage>:- java CreateSession <HostName> <UserName> <Password> <WebServer-Port>");
			System.exit(0);
		}
		doCreate(args);
		System.out.println(" All Users : "+secModel.getAllUserNames());

		closeSession();
		System.out.println(" ");
		System.exit(0);
	}

}









