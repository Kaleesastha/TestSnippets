package test;
import com.adventnet.nms.smtp.SmtpMailer;
public class SendEmailAction 
{
	public static void main(String args[])
	{
		try
		{
			System.err.println("send Email Notification called!!");
			String [] toArr={"test@testmail.com","test@testmail.com"};
			String smtphost="smtp.zoho.com";
			String username="test";
			String password="******";
			SmtpMailer mailer = new SmtpMailer(smtphost,"test@testmail.com",toArr,"smtp mail",null,username,password,true);//change true to false if non-ssl mode
			mailer.sendMessage("SSL Test mail with file attachement and with out Authentication");
			System.err.println("send Email Notification called completely!!");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		} 
	}

}
