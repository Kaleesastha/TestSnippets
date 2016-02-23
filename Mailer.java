package test;
import com.adventnet.nms.smtp.*;
import com.adventnet.nms.util.*;
public class Mailer 
{
	public static void main(String args[])
	{
		System.out.println(" Inside Runnable ");
	SmtpMailer mailer = new SmtpMailer("smtp", "venkatramanan@adventnet.com", "venkatramanan@adventnet.com", "Test Mail", "venkatramanan", BASE64Encoder.encode("venkatramanan"));
	System.out.println("Mail Sent");

	}
}
