import com.adventnet.launcher.*;

import java.io.*;

public class LicenceChecker
{
	public static void main(String args[])
	{
		String keydir="."+File.separator;

		LicensingKey key=new LicensingKey("AdventNet Web NMS2.2",keydir);
		if(!key.isValidationOk())
		{
			System.exit(1);
		}
		else
		{
			KeyDetails keydetails = key.getLicenseDetails();

			System.out.println("Categary: " + keydetails.getTypeOfLicense());	
			System.out.println("No of Nodes: " + keydetails.getNodeNumber());	
		}
	}
}

