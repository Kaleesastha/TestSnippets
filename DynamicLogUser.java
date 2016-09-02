package test;
import java.util.Properties;
import java.io.*; 
import com.adventnet.management.log.*;
import com.adventnet.nms.util.*;

public class DynamicLogUser implements RunProcessInterface
{
	public boolean boolvar;

	public DynamicLogUser()
	{
		boolvar=false;
	}

	public void callMain(String args[]) 
	{
		System.err.println("DynamicLogUser initialised");
		doDynamicLogging();
	}
	private void doDynamicLogging() {
		Properties prop=new Properties();
		String keyNames[]={"DYNAMIC_USER1","DYNAMIC_USER2","DYNAMIC_USER3"}; 
		String displayNames[]={"Dynamicuser1","Dynamicuser2","Dynamicuser3"};
		Integer logLevels[]={new Integer(3),new Integer(3),new Integer(3)};
		Boolean logging[]={new Boolean(true),new Boolean(true),new Boolean(true)}; 
		prop.setProperty("FileName","Dynamicusers.txt");
		prop.setProperty("MaxLines","1000");
		prop.setProperty("FileCount","10");
		prop.setProperty("LogsDirectory","logs");
		prop.setProperty("MaxLinesCached","0");
		prop.setProperty("UseTimeStamp","true");
		prop.put("Name",keyNames);
		prop.put("DisplayName",displayNames);
		prop.put("LogLevel",logLevels);
		prop.put("Logging",logging);
		try{ 
			LogMgr.createLogUser(prop,true);  
			//boolvar=true;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			System.out.println(ex);
		}
		testLogging();
		prop.setProperty("FileName","txt12.txt");
		try{ 
			LogMgr.createLogUser(prop,true);  
			System.err.println("LogUser file changed");
			//boolvar=true;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			System.out.println(ex);
		}
		testLogging();
		prop.setProperty("FileName","12txt11234.txt");
		try{ 
			LogMgr.createLogUser(prop,true);  
			System.err.println("LogUser file changed");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			System.out.println(ex);
		}
		testLogging();

		String keyNames2[]={"MISCUSER"}; 
		String displayNames2[]={"MISC"};
		Integer logLevels2[]={new Integer(3)};
		Boolean logging2[]={new Boolean(true)}; 
		prop.setProperty("FileName","new_nms_out.txt");
		prop.setProperty("MaxLines","1000");
		prop.setProperty("FileCount","10");
		prop.setProperty("LogsDirectory","logs");
		prop.setProperty("MaxLinesCached","0");
		prop.setProperty("UseTimeStamp","true");
		prop.put("Name",keyNames2);
		prop.put("DisplayName",displayNames2);
		prop.put("LogLevel",logLevels2);
		prop.put("Logging",logging2);
		try{ 
			LogMgr.createLogUser(prop,true);  
			System.err.println("##############LogUser file changed");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			System.out.println(ex);
		}
	}
	private void testLogging(){
		try
		{
			LogUser user1=LogMgr.getLogUser("DYNAMIC_USER1");
			LogUser user2=LogMgr.getLogUser("DYNAMIC_USER2");
			LogUser user3=LogMgr.getLogUser("DYNAMIC_USER3");
			user1.log("This is test for loguser support dynamically it is inititialized",3);

			for (int i=0;i<100;i++)
			{
				user1.log(Integer.toString(i)+" message log xmlwriter",3);
				user2.log(Integer.toString(i)+" message log xmlwriter",3);
				user3.log(Integer.toString(i)+" message log xmlwriter",3);
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception while logging message for dynamic loguser "+e);
		}
	}
	int test=0;
	public boolean isInitialized() { return true;}
	public void shutDown() {}
}
