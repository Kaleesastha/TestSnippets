package test;

import com.adventnet.tools.prevalent.*;


public class LicenseUtil
{
	public static String getLicenseType()
	{
		return Wield.getInstance().getUserType();
	}

	public static long getEvaluationDays()
	{
		return Wield.getInstance().getEvaluationDays();
	}

	public static void main(String args[])
	{
		/*Wield wield=Wield.getInstance();
		wield.validateInvoke("AdventNet Web NMS",true);
		try{
			if(!wield.isBare())
			{
				System.err.println("ImProper license");
				System.exit(0);
			}
			else
				System.err.println("Proper license");
		}
		catch(Exception exp)
		{exp.printStackTrace();}*/
		String nms_home = System.getProperty("webnms.rootdir");
		String classes = "classes";
		//String license_path = nms_home+"/classes/AdventNetLicense.xml";
		String license_path ="/home/local/ZOHOCORP/venkat-0773/todel/AdventNetLicense.xml";
		WebLicenseUpgrade wlu = new WebLicenseUpgrade(nms_home,classes,license_path);
		boolean valid_license = wlu.isValidLicense();
		if(!valid_license)
			System.err.println("ImProper license");
		else
			System.err.println("Proper license");
	}
}
