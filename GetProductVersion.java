package test;
import com.adventnet.tools.update.installer.*;
import java.util.Properties;
public class GetProductVersion
{
	public static void main(String args[])
	{
		try{
			String nmsRoot = ".";
			if(args[0] != null)
			{
				nmsRoot = args[0];
			}
			UpdateManager up = new UpdateManager("conf",nmsRoot);
			System.err.println("product version is::"+up.getProductVersion());
			System.err.println("SP version is::"+up.getCurrentServicePackVersion());
			String arr[] = up.getAllServicePackVersions(nmsRoot); //gives all service packs - including customer PPMs
			System.err.println("The SPs are::");
			for(int i=0;i<arr.length;i++)
				System.err.println(arr[i]);
			UpdateManagerParser logParse = new UpdateManagerParser(nmsRoot+"/conf/update_conf.xml");
			Properties logFileProp = logParse.getGeneralProps(); 
			System.err.println("logFileProp::"+logFileProp);
		}
		catch (Exception exp)
		{exp.printStackTrace();}
	}
}
/* http://nms-support.zohocorpin.com:8080/WorkOrder.do?woMode=viewWO&woID=6715&
http://nms-support.zohocorpin.com:8080/WorkOrder.do?woMode=viewWO&woID=3053724& */
