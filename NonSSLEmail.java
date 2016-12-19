package test;
import com.adventnet.nms.smtp.SmtpMailer;
public class NonSSLEmail
{
	public static void main(String args[])
	{
		String [] toArr={"ToAddress@Domain.com","ToAddress1@Domain2.com"}; //Change these addresses to valid to addresses
		String smtphost="smtp.google.com";//Change these host to valid smtp addresses
		String username="testUser@gmail.com";//Change this username addresses to valid username
		String password = args[0]; //Encrypt the password using NMS_HOME/bin/admintools/EncryptPassword.sh and provide this
		String fromAddr = args[1]; //Configure a valid from mail id here
		try
		{
			/*SendEMail call without secure mail - with UserName & Password*/
			SmtpMailer sendEMail =  new SmtpMailer (smtphost,fromAddr,toArr,"Non-SSL-SendEMail - UserName Password set",null,username,password);
			sendEMail.sendMessage("Non SSL Test mail without file attachement - but with UserName & Password");
			System.err.println("send Email Notification called completely!!");
		}
		catch(Exception e){e.printStackTrace();} 
	}
}
