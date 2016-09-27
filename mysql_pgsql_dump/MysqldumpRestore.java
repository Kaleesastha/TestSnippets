package jdbc;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.*;
import java.lang.reflect.*;
import java.util.zip.*;
import java.io.*;
import com.adventnet.nms.util.*;
import com.adventnet.nms.store.relational.RelationalUtil;
import java.lang.*;
import jdbc.RestoreBackup;
import com.adventnet.nms.protocol.NmsPDU;
import com.adventnet.nms.startnms.GenericConstants;
import java.net.Socket;

public class MysqldumpRestore 
{

	//    private MysqldumpRestore restore=null;   
	private Hashtable urlTable = new Hashtable();
	private Hashtable driverNameTable = new Hashtable();
	private Hashtable passwordTable = new Hashtable();
	private Hashtable userNameTable = new Hashtable();	
	DBParamsParser dbparser = null; 
	private static String rootDir=null;
	private static String mysqlhome=null;
	private static String pgsqlhome=null;
	private static String dbproductName = null;
	String dbhost = null;
	String schema = null;
	int dbport = -1;
	private static boolean chk=false;
	private static String backupHome=null;
	private static String enabledebug="true"; //No Internationalisation
	private PrintStream logout=null;
	private File logfile=null;
	private String logfilename=null;

	private static boolean isBatchUpdateSupported = false;

	private void startAnimate()
	{
		System.out.print(NmsUtil.GetString("server.framework.restore.inprogressmsg"));          
		PrintAnimate pran=new PrintAnimate();
		Thread t=new Thread(pran);
		t.start();
	}

	private void setLogFileName(String str)
	{
		logfilename=str;

	}
	public MysqldumpRestore()
	{
	}

	private  void startDemon()
	{
		for(Enumeration en = urlTable.elements();en.hasMoreElements();)
		{
			String entries = (String)en.nextElement();
			if (entries.indexOf("mysql") > 0 )//No Internationalisation
			{
				String arg[] = {"NMS_HOME" ,rootDir ,"URL", entries ,"CREATE_DB" , "true"};//No Internationalisation
				CreateWebNmsDB.main(arg);
				break;

			}
		}
	}          

	private boolean isStartMysql=false;

	//Start Check for server Running.
	private boolean checkForBEFailOverTablePresent(Connection conn)
	{
		String dummyQuery="select * from BEFailOver";
		ResultSet rs=null;
		PreparedStatement ps=null;
		try{
			ps=conn.prepareStatement(dummyQuery);
			rs=ps.executeQuery();
			return true;
		}catch(Exception e)
		{
			return false;
		}finally{
			try{
				if(ps!=null)
				{
					ps.close();
				}
				if(rs!=null)
				{
					rs.close();
				}
			}catch(Exception e)
			{
				return false;
			}
		}
	}

	private boolean isServerRunning(Connection conn)
	{
		if(!checkForBEFailOverTablePresent(conn))
		{
			return false;
		}
		String queryForPrimary="select HOSTADDRESS,NMSBEPORT from BEFailOver where " +RelationalUtil.getAlias("SERVERROLE")+" = 'PRIMARY'";
		String queryForStandBy="select HOSTADDRESS,NMSBEPORT from BEFailOver where "+ RelationalUtil.getAlias("SERVERROLE")+" = 'STANDBY'";
		PreparedStatement ps=null;
		ResultSet rs=null;
		String priServerHostName=null;
		int priServerBEPort=0;
		try{
			ps=conn.prepareStatement(queryForPrimary);
			rs=ps.executeQuery();
			while(rs.next())
			{
				priServerHostName=rs.getString(1);
				priServerBEPort=rs.getInt(2);
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try{
				if(ps!=null)
					ps.close();
				if(rs!=null)
					rs.close();
			}catch(Exception rse)
			{
			}
		}

		boolean primaryServerStatus=checkForRunning(priServerHostName,priServerBEPort);
		if(primaryServerStatus)
		{
			return true;
		}
		else
		{
			String staServerHostName=null;
			int staServerBEPort=0;
			try{
				ps=conn.prepareStatement(queryForStandBy);
				rs=ps.executeQuery();
				while(rs.next())
				{
					staServerHostName=rs.getString(1);
					staServerBEPort=rs.getInt(2);
				}
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
			finally
			{
				try{
					if(ps!=null)
						ps.close();
					if(rs!=null)
						rs.close();
				}catch(Exception rse)
				{
				}
			}
			return checkForRunning(staServerHostName,staServerBEPort);
		}

	}

	private boolean checkForRunning(String hostName,int port)
	{
		Socket sock=null;
		ByteArrayOutputStream bArr=null;
		DataOutputStream dStream=null;
		try{
			bArr=new ByteArrayOutputStream();
			dStream=new DataOutputStream(bArr);
			dStream.writeInt(GenericConstants.SERVER_TYPE);
			NmsPDU nmsPdu=new NmsPDU("GENERIC_ID","1",bArr.toByteArray());
			//sock=new Socket(hostName,port);
			sock=SocketUtil.createTCPSocket(hostName,port);
			sendPdu(sock,nmsPdu);
			String response=receiveDataFromServer(sock);
			if(response.equals("BE"))
			{
				return true;
			}
			else
			{
				return false;
			}
		}catch(Exception e)
		{
			return false;
		}
		finally
		{
			try{
				if(sock !=null)
					sock.close();
				if(bArr!= null)
					bArr.close();
				if(dStream != null)
					dStream.close();
			}catch(Exception e1)
			{
			}
		}
	}


	private void sendPdu(Socket sock,NmsPDU nmspdu) throws Exception
	{
		byte bArr[]=NmsPDU.serializeNmsPDU(nmspdu);
		DataOutputStream dos=new DataOutputStream(sock.getOutputStream());
		int len=bArr.length;
		if(len > 0)
		{
			dos.writeInt(len);
			dos.write(bArr,0,len);
			dos.flush();
		}
	}

	private String receiveDataFromServer(Socket sock) throws Exception
	{
		DataInputStream dis=new DataInputStream(sock.getInputStream());
		ByteArrayInputStream bais=null;
		DataInputStream dispdu=null;
		while(true)
		{
			int avail=dis.available();
			if(avail > 0)
			{
				int len=dis.readInt();
				byte[] bArr=new byte[len];
				dis.readFully(bArr);
				NmsPDU pdu=NmsPDU.deSerializeNmsPDU(bArr);
				bais=new ByteArrayInputStream(pdu.data);
				dispdu=new DataInputStream(bais);
				int id=dispdu.readInt();
				String res=dispdu.readUTF();
				return res;
			}
		}
	}

	private void putdebug(String debugmessage)
	{
		if(enabledebug.equalsIgnoreCase("true"))
		{

			logout.println(debugmessage); 
			logout.flush();
			System.out.println(debugmessage);
		}
	}
	/* This inner class is used to animate while restoring is in progress.*/
	class PrintAnimate extends Thread
	{
		PrintAnimate()
		{
			super();
		}
		public void run()
		{
			int i=0;
			char c[]={'|','/','-','\\'};
			while(true)
			{
				System.out.print("\b"+c[i]);
				i++;
				if(i>3) i=0;
				try{
					if(!chk)
					{
						Thread.sleep(75);
					}
					else
					{
						System.out.print(NmsUtil.GetString("server.framework.restore.successmsg"));
						break;
					}

				}catch(InterruptedException e){}
				catch(Exception ee){}
			}
		}
	}
	private static void usage()
	{
		System.out.println(" Usage : java MysqldumpRestore  [Backup File]");
		System.exit(1);

	}


	private void restoreConfFiles(String filename) throws Exception
	{
		String url = backupHome + "/" + "TablesToRestore.conf";//No Internationalization
		Hashtable filesHash = BackupUtil.getAllFileNames(url);

		Vector dirsToRestore = null;
		Vector filesToRestore = null;

		if (filesHash.containsKey("dir"))
		{
			putdebug("The following files will be restored now to NMS_Home");//No Internationalisation

			dirsToRestore = BackupUtil.getFilesFromString((String) filesHash.get("dir"));//No Internationalization

			if ((dirsToRestore != null) && (dirsToRestore.size() != 0))
			{
				restoreAllDirectoryFiles(dirsToRestore,filename);
			}
		}

		if (filesHash.containsKey("file"))
		{

			filesToRestore = BackupUtil.getFilesFromString((String) filesHash.get("file"));//No Internationalization

			if ((filesToRestore != null) && (filesToRestore.size() != 0))
			{
				for (int i = 0; i < filesToRestore.size(); i++)
				{
					String fileToRestore = (String) filesToRestore.elementAt(i);
					String name = new File(fileToRestore).getName();

					//removes file name from the file path
					String dirPath = fileToRestore.substring(0, fileToRestore.indexOf(name));

					File f = new File(rootDir + "/" + dirPath);//No Internationalization


					f.mkdirs();
					String path=filename.substring(0, filename.lastIndexOf(File.separator));//No Internationalization
					File sourceFile = new File(path + "/" + fileToRestore);//No Internationalization
					File destinationFile = new File(rootDir + "/" + fileToRestore);//No Internationalization

					if (BackupUtil.isEveryThingOk(sourceFile))
					{
						BackupUtil.copyFile(sourceFile, destinationFile);
						putdebug(destinationFile.toString());
					}
				}
				putdebug("All the files & directories specified in the FILES_TO_RESTORE tag are successfully restored");//No Internationalisation
			}
		}
		if(logout !=null)
			logout.close();
	}

	private void restoreAllDirectoryFiles(Vector dirsToRestore,String filename)
	{
		Vector allFilesVector = null;
		String fPath=filename.substring(0, filename.lastIndexOf(File.separator));//No Internationalization
		for (int i = 0; i < dirsToRestore.size(); i++)
		{
			String dirToRestore = (String) dirsToRestore.elementAt(i);

			File f = new File(fPath + "/" + dirToRestore);//No Internationalization
			if (f.exists())
			{
				if (f.isDirectory())
				{
					allFilesVector = new Vector();
					BackupUtil.recurseAndGetTheFiles(f, allFilesVector);
				}
			}

			for (int j = 0; j < allFilesVector.size(); j++)
			{
				File fileToRestore = (File) allFilesVector.elementAt(j);
				String path = fileToRestore.getPath();
				int index=path.indexOf(dirToRestore);
				String tmp=path.substring(index);                                
				//removes "backup/" from the path//No Internationalization
				// path = path.substring(7);

				if (fileToRestore.isDirectory())
				{
					File dirFile = new File(rootDir + "/" +tmp);//No Internationalization
					putdebug(rootDir + File.separator +dirFile.toString());

					if (!dirFile.exists())
					{
						dirFile.mkdir();
					}
				}
				else
				{
					File sourceFile = new File(path);//No Internationalization
					File destinationFile = new File(rootDir + "/" + tmp);//No Internationalization

					putdebug(destinationFile.toString());
					if (BackupUtil.isEveryThingOk(sourceFile))
					{
						BackupUtil.copyFile(sourceFile, destinationFile);
					}
				}
			}
		}
	}

	public static void main(String args[]) 
	{
		try
		{
			NmsUtil.readServerParams();
			FrameworkAPI.initializeResourceBundle();
		}
		catch(Exception e)
		{
			System.out.println("Error occured while reading server parameters  :" + e);
			e.printStackTrace();
		}
		rootDir = System.getProperty("webnms.rootdir");
		MysqldumpRestore restore=new MysqldumpRestore();
		restore.InitializeDBParms();
		if(dbproductName.equalsIgnoreCase("mysql")){
			mysqlhome=System.getProperty("mysql.home");
			if(mysqlhome == null)
			{
				mysqlhome=rootDir+"/mysql";
			}
			if(mysqlhome.indexOf(".") >= 0)
			{
				mysqlhome=new File(mysqlhome).getAbsolutePath();
			}	
		}
		else if(dbproductName.equalsIgnoreCase("postgresql")){
			pgsqlhome = System.getProperty("pgsql.home");
			if(pgsqlhome == null)
			{
				pgsqlhome=rootDir+"/pgsql";
			}
			if(pgsqlhome.indexOf(".") >= 0)
			{
				pgsqlhome=new File(pgsqlhome).getAbsolutePath();
			}
		}
		enabledebug = System.getProperty("enableDebug"); //NO I18N
		if(enabledebug!= null && enabledebug.equalsIgnoreCase("false"))
			enabledebug = "false"; //No Internationalization
		else
			enabledebug = "true"; //No Internationalization
		backupHome = System.getProperty("backupHome");

		int argc = args.length;

		if (argc <= 1)
		{
			usage();
		}


		String str ="";
		String line = null;
		/*File file = null;
		  DBParamsParser dbparser = null; 
		  try 
		  {
		  file = new File(rootDir + "/"+"conf"+"/"+"database_params.conf");
		  dbparser = DBParamsParser.getInstance(file);
		  }
		  catch (Exception ex)
		  {
		  System.err.println("FileReader Exception"+ex);
		  System.exit(1);
		  }*/

		int argn;
		String filename = null;
		String path = null;

		for ( argn = 0; argn < argc ; ++argn )
		{
			if (args[argn].equalsIgnoreCase("FILE") && argn + 1 < argc)
			{
				++argn;
				filename = args[argn];
			}
		}

		//If there is no slash(/ or \)  in the given filename, then append 
		//WebNMS-Home and backup dir assuming that file alone is given
		if (filename.indexOf(File.separator) < 0)
		{
			restore.setLogFileName(filename);
			filename = rootDir + File.separator + "backup" + File.separator + filename;//No Internationalization
		}
		else
		{
			restore.setLogFileName(filename.substring((filename.lastIndexOf(File.separator) + 1)));
		}
		// restore.doRestore(filename);
		boolean isMysqldump = false;
		File fs = new File(filename);
		if (fs.exists())
		{
			try
			{
				ZipFile zm=new ZipFile(fs);
				isMysqldump = false;
			}
			catch (ZipException ze)
			{
				//This is an mysqldump file
				isMysqldump = true;
			}
			catch (IOException ex)
			{
			}
		}
		if (isMysqldump)
		{
			restore.performRestore(filename);
		}
		else
		{
			RestoreBackup.main(args);
			return;
		}
		try
		{
			restore.restoreConfFiles(filename);
		}
		catch (Exception e)
		{
			System.out.println("Exception while restoring the conf files: "+e.getMessage());//No Internationalization  
			e.printStackTrace();
		}
		chk = true;
		/*try{
		  File rmfile=new File(filename+".tmp");
		  rmfile.delete();
		}
		catch(Exception e){e.printStackTrace();}*/
		CreateWebNmsDB.stopMysqld();
	}
	private void performRestore(String filename)
	{
		String mhome = mysqlhome; 
		if(dbproductName.equalsIgnoreCase("postgresql")){
			mhome = pgsqlhome;
		}
		Connection con = getConnection();
		if (con != null)
		{
			if (isServerRunning(con))
			{
				System.out.println(NmsUtil.GetString("WebNMS Server is running. Kindly restore the database after the WebNMS server is Shutdown."));
				System.exit(1);
			}
		}
		String appPath = mhome.replace('/','\\');
		try
		{
			logfile=new File(rootDir+"/"+"backup"+"/"+logfilename+"_restore.log");
			logfile.createNewFile();
			logout=new PrintStream(new FileOutputStream(logfile));
		}
		catch (Exception e){
			System.out.println(" Exception while creating log file."); //No Internationalisation
			e.printStackTrace();
		}
		logout.println("********************************************************************");
		logout.println("            Restoration Log Messages ");  //No Internationalisation
		logout.println("\nDate  : "+Calendar.getInstance().getTime());
		logout.println("\n********************************************************************");
		putdebug("Starting to restore the "+dbproductName+" dump file "+filename);
		startAnimate();
		int status = -1; 
		String osName = System.getProperty("os.name");
		if (dbproductName.equalsIgnoreCase("mysql")) { 
			if((osName.toLowerCase().indexOf("windows")) != -1)
			{
				String commandToExecute = "cmd /c \"\""+appPath+ File.separator + "bin" + File.separator+ "mysql"+"\"\" ";
				commandToExecute = addUserPasswordHostPort(commandToExecute);
				commandToExecute = commandToExecute + " < " + filename;
				try
				{
					Process process = Runtime.getRuntime().exec(commandToExecute);
					status = process.waitFor();
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
			else if(osName.startsWith("Linux") || osName.startsWith("Mac")) 
			{
				String commandToExecute=mysqlhome+"/bin/mysql --socket=/tmp/mysql.sock ";//No internationalization
				commandToExecute = addUserPasswordHostPort(commandToExecute);
				commandToExecute = commandToExecute + " < " + filename;
				System.err.println("commandToExecute is : "+commandToExecute);
				String command[] = {"/bin/sh","-c",commandToExecute};
				try
				{
					Process process = Runtime.getRuntime().exec(command);
					status = process.waitFor();
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
			else if(osName.equals("SunOS")) 
			{
				String commandToExecute=mysqlhome+"/bin/mysql ";//No internationalization
				commandToExecute = addUserPasswordHostPort(commandToExecute);
				commandToExecute = commandToExecute + " < " + filename;
				String command[] = {"/bin/sh","-c",commandToExecute};
				try
				{
					Process process = Runtime.getRuntime().exec(command);
					status = process.waitFor();
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		}
		else if (dbproductName.equalsIgnoreCase("postgresql")) { 
			if((osName.toLowerCase().indexOf("windows")) != -1)
			{
				String commandToExecute = "cmd /c \"\""+appPath+ File.separator + "bin" + File.separator+ "psql"+"\"\" ";
				commandToExecute = addUserPasswordHostPort(commandToExecute);
				commandToExecute = commandToExecute + " < " + filename;
				try
				{
					Process process = Runtime.getRuntime().exec(commandToExecute);
					status = process.waitFor();
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
			else if(osName.startsWith("Linux") || osName.startsWith("Mac")) 
			{
				String commandToExecute=pgsqlhome+"/bin/psql ";//No internationalization
				commandToExecute = addUserPasswordHostPort(commandToExecute);
				commandToExecute = commandToExecute + " < " + filename;
				System.err.println("commandToExecute is : "+commandToExecute);
				String command[] = {"/bin/sh","-c",commandToExecute};
				try
				{
					Process process = Runtime.getRuntime().exec(command);
					status = process.waitFor();
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
			else if(osName.equals("SunOS")) 
			{
				String commandToExecute=mysqlhome+"/bin/psql ";//No internationalization
				commandToExecute = addUserPasswordHostPort(commandToExecute);
				commandToExecute = commandToExecute + " < " + filename;
				String command[] = {"/bin/sh","-c",commandToExecute};
				try
				{
					Process process = Runtime.getRuntime().exec(command);
					status = process.waitFor();
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		}
		if (status ==0)
		{
			putdebug("\nSuccessfully restored the "+dbproductName+"  dump file "+filename);
		}
		else
		{
			putdebug(dbproductName+" dump file "+filename+ " not properly restored. Exit code : "+status);
		}


	}
	private String addUserPasswordHostPort(String commandToExecute)
	{
		String userName = dbparser.getUserName();
		if(dbproductName.equalsIgnoreCase("mysql")){
			if(userName != null)
			{
				commandToExecute = commandToExecute + " -u " + userName;
			}
			String password = dbparser.getPassword();
			if(password != null)
			{
				commandToExecute = commandToExecute + " --password=" + password;
			}
			if (dbhost != null)
			{
				commandToExecute = commandToExecute + " -h " + dbhost;
			}
			if(dbport != 3306)
			{
				commandToExecute = commandToExecute + " --port=" + dbport; //NO I18N
			}
			if(schema != null);
			{
				commandToExecute = commandToExecute + " " +schema;
			}
		}
		else if (dbproductName.equalsIgnoreCase("postgresql")){
			if(userName != null)
			{
				commandToExecute = commandToExecute + " -U " + userName;
			}
			String host = dbparser.getDBHost();
			if (host != null)
			{
				commandToExecute = commandToExecute + " -h " + host;
			}
			String port = dbparser.getDBPort();
			if(port!=null && !port.equals("5432"))
			{
				commandToExecute = commandToExecute + " --port=" + dbport; //NO I18N
			}
			if(schema != null);
			{
				commandToExecute = commandToExecute + " " +schema;
			}
			/*String password = dbpp.getPassword();
			  if(password != null)
			  {
			  commandToExecute = "export PGPASSWORD="+password +" && "+commandToExecute ;
			  }*/
		}
		return commandToExecute;	
	}
	private void parseForHostAndPort(String url)
	{
		Class mysqlPattern = null;
		Method method_compile =null;
		Object PatternMysql=null;
		try
		{
			mysqlPattern = Class.forName("java.util.regex.Pattern");//No Internationalization
			method_compile = (Method)mysqlPattern.getMethod("compile",new Class[]{java.lang.String.class});//No Internationalization
			PatternMysql=method_compile.invoke(null,new Object[]{new String("jdbc:mysql://([a-zA-Z0-9_\\-.]+|)((:(\\d+))|)/([a-zA-Z][a-zA-Z0-9_\\-]*)(\\?.*)?")});//No Internationalization
			if(dbproductName.equalsIgnoreCase("postgresql")){
				PatternMysql=method_compile.invoke(null,new Object[]{new String("jdbc:postgresql://([a-zA-Z0-9_\\-.]+|)((:(\\d+))|)/([a-zA-Z][a-zA-Z0-9_\\-]*)(\\?.*)?")});//No Internationalization
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		String port = "null";//No Internationalization
		try{

			Class CharSequenceClass=Class.forName("java.lang.CharSequence");//NO I18N
			Method method_matcher = (Method)mysqlPattern.getMethod("matcher",new Class[]{CharSequenceClass});//No Internationalization
			Class MatcherClass = Class.forName("java.util.regex.Matcher");//No Internationalization
			Object matcher_object=method_matcher.invoke(PatternMysql,new Object[]{new java.lang.String(url)});

			Method group_method = (Method)MatcherClass.getMethod("group",new Class[]{Integer.TYPE});//No Internationalization
			Method matches_method = (Method)MatcherClass.getMethod("matches",new Class[]{});//No Internationalization
			Object matval=matches_method.invoke(matcher_object,new Object[]{});
			if(((Boolean)matval).booleanValue())
			{

				String hostVal= (String)group_method.invoke(matcher_object,new Object[]{new Integer(1)});
				if(hostVal!=null && hostVal.length() > 0)
				{
					dbhost=hostVal;
				}
				String portVal= (String)group_method.invoke(matcher_object,new Object[]{new Integer(4)});
				if(portVal !=null && portVal.length() > 0)
				{
					port=portVal;
				}
				String sch  = (String)group_method.invoke(matcher_object,new Object[]{new Integer(5)});
				if (sch != null && sch.length() > 0)
				{
					schema = sch;
				}
			}
			System.err.println("schema name is : "+schema);
		}
		catch(Exception cnfe)
		{

		}
		try{
			dbport=Integer.parseInt(port);
		}
		catch(NumberFormatException e)
		{
			if(port.equals("null") || port.equals(""))
			{
				dbport = 3306;
			}
			else
			{
				System.err.println("setting port to default -1");//NO I18N
			}
		}
	}
	private void InitializeDBParms()
	{
		File file = null;
		try 
		{
			file = new File(rootDir + "/classes/hbnlib/hibernate.cfg.xml");
			dbparser = DBParamsParser.getInstance(file);
			dbproductName = dbparser.getDatabaseName();
		}
		catch (Exception ex)
		{
			System.err.println("FileReader Exception"+ex);
			System.exit(1);
		}
		/*urlTable = dbparser.getURLTable();
		userNameTable = dbparser.getUserNameTable();
		passwordTable = dbparser.getPasswordTable();
		driverNameTable = dbparser.getDriverNameTable();*/
		parseForHostAndPort(dbparser.getURL());
	}
	private Connection getConnection()
	{
		String modules=null;
		String drivername=null;
		String urlstr=null;
		String username=null;
		String password=null;
		Statement stmt=null;
		Connection con = null;
		try 
		{

			//for(Enumeration en = driverNameTable.keys();en.hasMoreElements();)  
			//{
				//modules    = (String)en.nextElement();				
				drivername	= dbparser.getDriverName();		
				urlstr	= dbparser.getURL();		
				username   =dbparser.getUserName();
				password   = dbparser.getPassword();		
			//}
			startDemon();
			try 
			{
				Class.forName(drivername);
			} catch (Exception e)
			{
				System.err.println("MysqldumpRestore exception"+e);
				e.printStackTrace();
			}

			con = DriverManager.getConnection(urlstr,username,password);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return con;
	}
}
