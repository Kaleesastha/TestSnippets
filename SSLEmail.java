/*SSLEmail - Usage: invoke as java test.SSLEmail <encrypted_password> <fromAddress>*/
package test;
import com.adventnet.nms.smtp.SmtpMailer;
public class SSLEmail
{
        public static void main(String args[])
        {
                String [] toArr={"venkatramanan@zohocorp.com"};//configure valid mail ids here
                String smtphost="smtp.zoho.com"; 
                String username="venkatramanan";  //configure valid mail user name here
                String password = args[0];  //Encrypt the password using NMS_HOME/bin/admintools/EncryptPassword.sh and provide this
                String fromAddr = args[1]; //configure a valid from mail id here
                try
                {
                        /*SendEMail call without secure mail - with UserName & Password*/
                        SmtpMailer sendEMail = new SmtpMailer(smtphost, fromAddr,toArr, "SSL Mail-SendEMail - UserName Password set",null,username,password,true);
                        sendEMail.sendMessage("SSL Test mail without file attachement - but with UserName & Password");
                        System.err.println("send Email Notification called completely!!");
                }
                catch(Exception e){e.printStackTrace();}
        }
}
