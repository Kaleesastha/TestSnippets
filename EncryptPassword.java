import com.adventnet.security.authorization.*;
public class EncryptPassword
{
	public static void main(String args[])
	{
		try{
			if(args.length < 1)
			{
				System.out.println("Usage: java  -cp $NMS_CLASSES EncryptPassword [plain text password]");
				System.exit(0);
			}
			String plainText = com.adventnet.security.authorization.Coding.convertToNewBase(args[0]);
			System.out.println("The password is " + plainText);
		}
		catch(Exception e)
		{e.printStackTrace();}
	}
}
