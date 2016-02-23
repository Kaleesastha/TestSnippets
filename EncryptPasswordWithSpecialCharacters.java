package test;
import com.adventnet.security.authorization.Coding;
import com.adventnet.security.common.SecurityXMLParser;
import java.io.*;

/**
 * This Utility class is used to display the encrypted password.
 */
public class EncryptPasswordWithSpecialCharacters
{

    private void encrypt(String args[])
    {
	SecurityXMLParser xmlParser = null;

        String nmsRoot=args[0];
        String username=args[1];
        String password=args[2];
	System.out.println("args is::"+args[3]);
        //String encryptPassword=args[3];
        String encryptPassword=processPassword(args[3]);
        try
        {
            xmlParser = new SecurityXMLParser();
            String f = nmsRoot+"/conf/securitydbData.xml";//No Internationalisation
            InputStream is = xmlParser.openFile(new File(f));
            xmlParser.parseInputStream(is);
        }catch(Exception e)
        {
            System.out.println("Exception while instantiating SecurityXMLParser :");
            e.printStackTrace();
            System.exit(1);
        }
        boolean isAuth=xmlParser.isAuthenticated(username,password);
        String enpassword=null;
        if(isAuth)
        {
             enpassword = Coding.convertToNewBase(encryptPassword);
 
             System.out.println("Encrypted Password for password \""+args[3]+"\" is : "+enpassword); 
        }
        else
        {
            System.err.println("Authentication failed for this User \""+username+"\". Cannot display encrypted password.");
        }

    }

    public static void main (String args[])
    {
        EncryptPasswordWithSpecialCharacters enPass=new EncryptPasswordWithSpecialCharacters();
        enPass.encrypt(args);
    }
    private static String processPassword(String plainTextPassword)
    {
	    char pwd [] = plainTextPassword.toCharArray();
	    String passwordWithSlashes="";
	    for(int j=0;j<pwd.length;j++)
	    {
		    passwordWithSlashes =passwordWithSlashes + "\\"+pwd[j];
	    }
	    System.out.println("passwordWithSlashes is:: "+passwordWithSlashes);
	    return passwordWithSlashes;
    }
}
//----------------------
