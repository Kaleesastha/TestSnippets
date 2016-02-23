//package jdbc;
import java.io.*;
import java.lang.*;
import java.sql.*;
import com.adventnet.nms.util.*;
import jdbc.*;

public class DatabaseOperations
{
   public static void main(String args[])
   {
  	if(NmsUtil.isNMSRunning())
	System.out.println("WebNMS runs!");
	else
	System.out.println("WebNMS sleeps!");
       	
	try{
	String mysql_home = PureUtils.rootDir + File.separator + "mysql";
       	String webserver_rootdir=PureUtils.rootDir + File.separator + "apache";
	String backupHome=PureUtils.rootDir + File.separator + "bin" + File.separator +"backup";
	System.setProperty("webnms.rootdir", PureUtils.rootDir);
	System.setProperty("mysql.home", mysql_home );
	System.setProperty("separateJVM", "true");
	System.setProperty("NMS_HOME", "C:" + File.separator + "SP2");
	System.setProperty("webserver.rootdir", webserver_rootdir);
	System.setProperty("backupHome",backupHome);
	
	//CreateSchema.main(args);
	/*For Reinitialize*/
	/*USAGE: java test MODE ALL */
	DropSchema.main(args);

	/*For BackUp*/
	//BackUpImpl.main(args);
	/*USAGE: java test */
	
	/*For Restore */
	/*String arg []= args;
	arg=args;
	jdbc.RestoreBackup.main(arg);
	USAGE: java test FILE C:\SP2\backup\BackUp_JAN31_2006_15_17.data 
	*/
	/*For Restore ends*/
	}
	catch(Exception e){e.printStackTrace();}
   }
}
