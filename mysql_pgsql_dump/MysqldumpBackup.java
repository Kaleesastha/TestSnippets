
package jdbc;

import java.io.File;
import java.util.Calendar;
import java.util.StringTokenizer;
import java.util.Date;
import java.util.Vector;
import java.util.Hashtable;
import java.sql.*;
import java.lang.reflect.*;
import java.util.zip.*;
import java.util.Enumeration;
import com.adventnet.nms.util.*;
import java.io.*;
import com.adventnet.management.backup.BackUpInterface;
import com.adventnet.nms.util.XMLNode;
import com.adventnet.nms.util.XMLDataWriter;
import com.adventnet.nms.store.relational.MetaDataInfo;
import com.webnms.nms.ha.SystemCommands;

public class MysqldumpBackup implements BackUpInterface
{
    private static boolean ch=false;
    private static boolean isSeparateJVM=false;
    private static String destinationDir = null;

    private DBParamsParser dbpp=null;
    private File backupDir=null;
    private Connection conn=null;
    private String backupFileName=null;
    String dbhost = null;
    String schema = null;
    int dbport = -1;
    private String dbproductName=null;
    private static String mysqlhome = null;
    private static String pgsqlhome = null;
    private boolean timesten=false;
    private boolean oracle=false;
    private boolean alreadyExists=false;
    private String seconds = null;

    private boolean dynamicCheck = true; 

	private boolean selectiveTableBackup  = false ; 

	private String[] tablesToBackup = null;
	

    
    private static int LIMIT = 5000;

    public MysqldumpBackup()
    {
        if(isSeparateJVM)
        {
            startAnimate();
        }
    }

    private void startMySql()
    {
        String arg[] = {"NMS_HOME" ,rootdir ,"URL", dbpp.getURL() ,"CREATE_DB" , "false"};//No Internationalisation
        CreateWebNmsDB.main(arg);   
    }


    private void startAnimate()
    {
        System.out.print("\n\nPlease wait ! Backup in Progress.....................  ");

        PrintAnimate pran=new PrintAnimate();
        Thread t=new Thread(pran);
        t.start();
    } 


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
                    if(!ch)
                    {
                        Thread.sleep(75);
                    }
                    else
                    {
                        System.out.print("\b O.K.");
			System.out.println("\n\n\nBackup data file \"" + backupFileName + "\" successfully created and taking backup is completed. ");//No Internationalisation
                        System.exit(1);
                        break;
                    }

                }catch(InterruptedException e)
		{
			System.exit(1);
			break;
		}
                catch(Exception ee)
		{
			System.exit(1);
			break;
		}
            }
        }
    }  
    
    private void init(Date date)
    {
        backupFileName=getFileName(date);

        if(destinationDir == null)
            // If not set in the main() or in previous run, set it
            // now.
        {
            // Get it from serverparameters
            destinationDir = NmsUtil.getParameter("BACKUP_DESTINATION");
            if(destinationDir == null)
                // If not specified in serverparameters.conf also,
                // then take the default directory.
            {
                destinationDir = new File(rootdir, "backup").getAbsolutePath();
            }
        }
        backupDir = new File(destinationDir);

        if (!backupDir.exists()) backupDir.mkdir();
    }

    // read BackUp.conf file and get the list of table names to be backed up
    private String[] getAllTableNames()
    {
		if(selectiveTableBackup){
			if( tablesToBackup != null){
				return tablesToBackup ;
			}
			return new String[0];
		}
        String url = rootdir + "/conf/BackUp.conf";
        String tableList[] = null;
        boolean customTables = false;

        XMLDataReader xmlreader = new XMLDataReader(url, false);
        XMLNode node = null;
        Vector childNodes = xmlreader.getRootChildNodes();
        String tableNames = null;
        String tables = null;
        boolean flag = true;
        String dynamicTables = null;
        String limit = null;

        for (Enumeration en = childNodes.elements(); en.hasMoreElements();)
        {
            node = (XMLNode) en.nextElement();

            if (node.getNodeType() == XMLNode.COMMENT)
            {
                continue;
            }         

            if (node.getNodeName().equals("BACKUP"))
            {
                continue;
            }
            
            if (node.getNodeName().equals("FILES_TO_BACKUP"))
            {
                continue;
            }
            
            if (node.getNodeName().equals("TABLES_TO_BACKUP"))
            {
                tables = (String) node.getAttribute("TABLES");
                dynamicTables = (String) node.getAttribute("DYNAMIC_TABLES");
                limit = (String) node.getAttribute("LIMIT");

                if (limit != null)
                {
                    LIMIT = new Integer(limit).intValue();
                }

                if (dynamicTables != null)
                {
                    if (dynamicTables.equalsIgnoreCase("false"))
                    {
                        dynamicCheck = false;
                    }
                    else if (dynamicTables.equalsIgnoreCase("true"))
                    {
                        dynamicCheck = true;
                    }
                }

                if (tables != null)
                {
                    if (tables.equalsIgnoreCase("LISTED"))
                    {
                        if (flag)
                        {
                            tableNames = (String) node.getAttribute("TABLE_NAMES");
                        }
                        else
                        {
                            tableNames = tableNames + "," + (String) node.getAttribute("TABLE_NAMES");
                        } 
                        customTables = true;
                    }
                    else if (tables.equalsIgnoreCase("ALL"))
                    {
                        customTables = false;
                        break;
                    }
                }
                else
                {
                    System.err.println(" BackUp.conf contains invalid entry for TABLES  --> "+tables+". Please correct this and try again.");
                    System.exit(1);
                }
                flag = false;
            }
        }

        if (customTables)
        {
            StringTokenizer stkr = new StringTokenizer(tableNames, ",");   
            Vector tmpList = new Vector();

            for(;stkr.hasMoreTokens();)
            {
                tmpList.addElement(stkr.nextToken().trim());
            }
            
            if (dynamicCheck)
            {
                Vector tmpVect = getDynamicSchema();
                tmpList = getDynamicTableNames(tmpVect,tmpList);
            }
            /*else
            {
                writeDBDetailsToXML();
            }*/
            
            tableList = new String[tmpList.size()];

            int count = 0;

            for (Enumeration en = tmpList.elements(); en.hasMoreElements();)
            {
                tableList[count++] = (String)en.nextElement();
            }
        }
        else 
        {
            tableList = getAllTableNamesFromDatabaseSchema();
        }
        return tableList;
    }
    
    /**
     * This method is called whenever the back up is scheduled.
     * 
     * @date Date the date at which this has to be run
     */
    public void runBackUp(Date date)
    {
        try
        {
            init(date);
            backupInsertCommands(backupFileName,date);
            backupConfFiles();
        }
        catch(Exception ex)
        {
		ex.printStackTrace();
	    String message = ex.getMessage();
	    if (message.indexOf("No space left on device") != -1)
	    {
		System.out.println("Backup cannot be done, as there is no space left on the device. Please clear the disk space and then proceed with backup." );
		System.exit(1);
	    }
	    else
	    {
	    	System.err.println("Exception while taking backup on : "+ date + "" + ex.getMessage()); 
	    }
            //ex.printStackTrace();
        }
    }

    private String month(int i)
    {
        switch(i)
        {
            case 0: return "JAN";
            case 1: return "FEB";
            case 2: return "MAR";
            case 3: return "APR";
            case 4: return "MAY";
            case 5: return "JUN";
            case 6: return "JUL";
            case 7: return "AUG";
            case 8: return "SEP";
            case 9: return "OCT";
            case 10: return "NOV";
            case 11: return "DEC";
        }
        return "";
    }

    private String getFileName(Date date)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        String minute = String.valueOf(cal.get(Calendar.MINUTE));
        seconds=String.valueOf(cal.get(Calendar.SECOND));
        if(cal.get(Calendar.MINUTE)<10) minute="0"+minute;
        String filename ="BackUp_"+ month(cal.get(Calendar.MONTH)) +   
            + cal.get(Calendar.DAY_OF_MONTH) + "_"  
            + cal.get(Calendar.YEAR) + "_" 
            + cal.get(Calendar.HOUR_OF_DAY) + "_" 
            + minute;
        return filename;

    }

    private Vector getDynamicSchema()
    {
        Vector tmpDynTab = null;
        FileInputStream fin = null;
        BufferedReader din = null;
        try
        {
            File file = new File(rootdir + "/"+"conf"+"/"+"DatabaseSchema.conf");
            fin= new FileInputStream(file);
            din = new BufferedReader(new InputStreamReader(fin));
            String line = null;
            tmpDynTab = new Vector();

            while ((line = din.readLine()) != null)
            {
                line = line.trim();
                if (line.startsWith("create table")) 
                {
                    if (line.indexOf("%") != -1)
                    {
                        StringTokenizer tmpstr = new StringTokenizer(line);
                        tmpstr.nextToken();
                        tmpstr.nextToken();
                        String tmptbl = tmpstr.nextToken();
                        tmptbl = tmptbl.replace('%',' ');
                        int index = tmptbl.indexOf("(");
                        if (index !=-1)
                        {
                            tmptbl = tmptbl.substring(0,index);
                            tmpDynTab.addElement(tmptbl.trim());
                        }    
                    }
                }
            }
        }
        catch(Exception e){}
        finally{
            try{
                fin.close();
                din.close();
            }
            catch(Exception e){}
        }
        return tmpDynTab;
    }

    private String[] getAllTableNamesFromDatabaseSchema()
    {
        String tmptables[]=null;
        FileInputStream fin=null;
        BufferedReader din=null;
        try
        {
            File file=new File(rootdir + "/"+"conf"+"/"+"DatabaseSchema.conf");
            fin= new FileInputStream(file);
            din = new BufferedReader(new InputStreamReader(fin));
            String line=null;
            Vector tmpvect=new Vector();
            Vector tmpDynTab=new Vector();
            while ( (line = din.readLine()) != null)
            {
                line = line.trim();
                if (line.startsWith("create table")) 
                {
                    StringTokenizer tmpstr=new StringTokenizer(line);
                    tmpstr.nextToken();
                    tmpstr.nextToken();
                    String tmptbl=tmpstr.nextToken();
                    int index=tmptbl.indexOf("(");
                    if(index !=-1)
                    {
                        tmptbl=tmptbl.substring(0,index);
                        tmpvect.addElement(tmptbl);
                    }    
                    else
                    {
                        tmpvect.addElement(tmptbl);
                    }
                    if(tmptbl.indexOf("%")!=-1)
                    {
                        tmptbl=tmptbl.replace('%',' ');
                        tmpDynTab.addElement(tmptbl);
                    }
                }

            }

            int i=0;
            //if(tmpDynTab.size()!=0)
            tmpvect=getDynamicTableNames(tmpDynTab,tmpvect);
            tmpvect.remove("BEFailOver");
            tmptables=new String[tmpvect.size()];
            for(Enumeration en=tmpvect.elements();en.hasMoreElements();)
            {
                tmptables[i++]=(String)en.nextElement(); 
            }
        }
        catch(Exception e)
	{
		//	e.printStackTrace();
		String message = e.getMessage();
		if (message.indexOf("No space left on device") != -1)
		{
			System.out.println("Backup cannot be done, as there is no space left on the device. Please clear the disk space and then proceed with backup." );//No Internationalization
			System.exit(1);
		}
		else
		{
			System.err.println("Error occurred while getting tables : "+e.getMessage()); // no i18n	
		}
	}
        finally{
            try{
                fin.close();
                din.close();
            }
            catch(Exception e){}
        }

        return tmptables;
    }

    private String parseAndGetCreateStringForDynTable(String tag)
    {
        String filename = "";
        String stTag="<"+tag+">";
        String endTag="</"+tag+">";
        try
        {
            filename = rootdir + "/conf/DatabaseSchema.conf";	
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line = "";
            String stmt = "";
            while((line=br.readLine())!=null)
            {
                if (!line.equals(stTag)) continue;
                while (!(line=br.readLine()).equals(endTag))
                    stmt = stmt + line;
            }
            stmt=stmt.substring(stmt.indexOf("("));
            return stmt;
        }
        catch (IOException e)
        {
            System.err.println("IOException while reading the file "+e.getMessage());
            return null;
        }
    }


    private Vector getDynamicTableNames(Vector tmpDynTab,Vector allTab)
    {
        try
        {
            Vector tableLists=MetaDataInfo.getTables(conn);
            for(Enumeration enTab=tableLists.elements();enTab.hasMoreElements();)
            {
                String tab=((String)enTab.nextElement()).toUpperCase();
                for(Enumeration en=tmpDynTab.elements();en.hasMoreElements();)
                {
                    String dyn=((String)en.nextElement()).trim();
                    if(tab.startsWith(dyn))
                    {
                        allTab.remove(dyn+"%");
                        allTab.addElement(tab);
                    }
                }
            }

            PreparedStatement prp1 = null;
            ResultSet rs1 = null;
            try
            {
                String query = "select * from StatsTables";
                prp1 = conn.prepareStatement(query);
                rs1 = prp1.executeQuery();

                while (rs1.next())
                {
                    String taName = rs1.getString(1);
                    String crSche = rs1.getString(2);

                    if (crSche != null)
                    {
                        crSche = crSche.substring(crSche.indexOf("("));
                    }

                    if (taName != null)
                    {
                        // following thing is done to handle the table name with
                        // % appended to it in StatsTables
                        
                        int index = taName.indexOf("%");

                        if (index != -1)
                        {
                            taName = taName.substring(0, index);

                            for (Enumeration enTab = tableLists.elements(); enTab.hasMoreElements();)
                            {
                                String tab = ((String)enTab.nextElement()).toUpperCase();

                                if (tab.startsWith(taName))
                                {
                                    allTab.addElement(tab);
                                }
                            }
                        }
                        else
                        {
                            allTab.addElement(taName);
                        }
                    }

                }
            }
            catch (Exception e)
            {
                //e.printStackTrace();
		String message = e.getMessage();
		if (message.indexOf("No space left on device") != -1)
		{
			System.out.println("Backup cannot be done, as there is no space left on the device. Please clear the disk space and then proceed with backup." );//No Internationalization
			System.exit(1);
		}
		else
		{
			System.err.println("Error occurred while getting dynamic tables : "+e.getMessage());  // no i18n	
		}
            }
            finally
            {
                if(rs1!=null) rs1.close();
                if(prp1!=null) prp1.close();
            }
        }
        catch (Exception e)
        {
            //e.printStackTrace();
	    String message = e.getMessage();
		if (message.indexOf("No space left on device") != -1)
		{
			System.out.println("Backup cannot be done, as there is no space left on the device. Please clear the disk space and then proceed with backup." );//No Internationalization
			System.exit(1);
		}
		else
		{
			System.err.println("Error occurred while getting dynamic tables from the database : "+e.getMessage());  // no i18n	
		}
        }
        return allTab;
    }

    private void backupInsertCommands(String filename, Date date) throws Exception
    {
        Vector tmpvect=new Vector();
        Vector vec=new Vector();
        String tableNames[]=null;

        if(!isSeparateJVM)
        {
            try
            {
                dbpp=DBParamsParser.getInstance(new File(rootdir+"/classes/hbnlib/hibernate.cfg.xml"));
                conn=NmsUtil.relapi.getConnection();
                dbproductName=dbpp.getDatabaseName();
                rootdir=PureUtils.rootDir;
                tableNames = getAllTableNames();
		mysqlhome=System.getProperty("mysql.home");
		if(mysqlhome == null)
		{
			mysqlhome=rootdir+"/mysql";
		}
		if(mysqlhome.indexOf(".") >= 0)
		{
			mysqlhome=new File(mysqlhome).getAbsolutePath();
		}
            }catch(Exception ex)
            {
                try{
			dbpp=DBParamsParser.getInstance(new File(rootdir+"/classes/hbnlib/hibernate.cfg.xml"));
                    if(dbpp.getURL().toLowerCase().indexOf("mysql")!=-1)
                        startMySql();

                    Class.forName(dbpp.getDriverName());
                    conn = DriverManager.getConnection(dbpp.getURL(),dbpp.getUserName(),dbpp.getPassword());
                    dbproductName=dbpp.getDatabaseName();
		    if(dbproductName.equalsIgnoreCase("postgresql")){
			    pgsqlhome = System.getProperty("pgsql.home");
		    }
		    if(pgsqlhome == null)
		    {
			    pgsqlhome=rootdir+"/pgsql";
		    }
		    if(pgsqlhome.indexOf(".") >= 0)
		    {
			    pgsqlhome=new File(pgsqlhome).getAbsolutePath();
		    }
                    System.out.println("Database name:"+dbproductName);
                    tableNames = getAllTableNames();
                }
                catch(Exception e)
		{
			String message = e.getMessage();
			if (message.indexOf("No space left on device") != -1)
			{
				System.out.println("Backup cannot be done, as there is no space left on the device. Please clear the disk space and then proceed with backup." );
			}
			else
			{
				System.err.println("Error occurred while getting DB connection : "+e.getMessage());  // no i18n	
			}
                }

            }
        }
        else
        {
            try{
			dbpp=DBParamsParser.getInstance(new File(rootdir+"/classes/hbnlib/hibernate.cfg.xml"));
                if(dbpp.getURL().toLowerCase().indexOf("mysql")!=-1)
                    startMySql();
                Class.forName(dbpp.getDriverName());
                conn = DriverManager.getConnection(dbpp.getURL(),dbpp.getUserName(),dbpp.getPassword());   
                dbproductName=dbpp.getDatabaseName();
                tableNames = getAllTableNames();

            }catch(Exception e)
            {
		String message = e.getMessage();
		if (message.indexOf("No space left on device") != -1)
		{
			System.out.println("Backup cannot be done, as there is no space left on the device. Please clear the disk space and then proceed with backup." );
			System.exit(1);
		}
		else
		{
			System.err.println("Error occurred while getting DB connection : "+e.getMessage()); // no i18n
			System.exit(1);
		}
            }
        }

        if(dbproductName.equalsIgnoreCase("timesten"))
            timesten=true;
        if(dbproductName.equalsIgnoreCase("oracle"))
        {
            oracle=true;
        }
	
	StringBuffer buff = null;
	try
	{
		buff = new StringBuffer();
		for (int i =0; i < tableNames.length; i ++)
		{
			String tab = tableNames[i];
			buff.append(tab);
			buff.append(" ");
		}
	}
	catch (Exception ex)
	{
		ex.printStackTrace();
	}
	parseForHostAndPort(dbpp.getURL());
	String commandToExecute = "";
	String mhome = mysqlhome; 
	if(dbproductName.equalsIgnoreCase("postgresql")) {
		mhome = pgsqlhome;
	}
	String appPath = mhome.replace('/','\\');
	String osName = System.getProperty("os.name");
	System.err.println("dbproductName is : "+dbproductName);
	if(dbproductName.equalsIgnoreCase("mysql")) {
		if((osName.toLowerCase().indexOf("windows")) != -1)
		{
			commandToExecute = "\""+appPath+ File.separator + "bin" + File.separator+ "mysqldump" + "\"" + "--hex-blob " ;
			commandToExecute = addUserPasswordHostPort(commandToExecute);
			//commandToExecute = commandToExecute + " " + buff.toString();
			System.out.println("Command ------------ >" +commandToExecute);
		}
		else if(osName.startsWith("Linux") || osName.startsWith("Mac")) 
		{
			commandToExecute=mysqlhome+"/bin/mysqldump  --hex-blob  --socket=/tmp/mysql.sock ";
			commandToExecute = addUserPasswordHostPort(commandToExecute);
			//commandToExecute = commandToExecute + " " + buff.toString();
			System.err.println("commandToExecute is:"+commandToExecute);
		}
		else if(osName.equals("SunOS")) 
		{
			commandToExecute=mysqlhome+"/bin/mysqldump  --hex-blob ";
			commandToExecute = addUserPasswordHostPort(commandToExecute);
			//commandToExecute = commandToExecute + " " + buff.toString();
		}
	}
	if(dbproductName.equalsIgnoreCase("postgresql")) {
		if((osName.toLowerCase().indexOf("windows")) != -1)
		{
			commandToExecute = "\""+appPath+ File.separator + "bin" + File.separator+ "mysqldump" + "\"" + "--hex-blob " ;
			commandToExecute = addUserPasswordHostPort(commandToExecute);
			//commandToExecute = commandToExecute + " " + buff.toString();
			System.out.println("Command ------------ >" +commandToExecute);
		}
		else if(osName.startsWith("Linux") || osName.startsWith("Mac")) 
		{
			commandToExecute=pgsqlhome+"/bin/pg_dump ";
			commandToExecute = addUserPasswordHostPort(commandToExecute);
			//commandToExecute = commandToExecute + " " + buff.toString();
		}
		else if(osName.equals("SunOS")) 
		{
			commandToExecute=pgsqlhome+"/bin/mysqldump  --hex-blob ";
			commandToExecute = addUserPasswordHostPort(commandToExecute);
			//commandToExecute = commandToExecute + " " + buff.toString();
		}
		System.err.println("commandToExecute is:"+commandToExecute);
	}
	FileOutputStream fs = null;
	File f = null;
	InputStream is = null;
	InputStreamReader isr = null;
	BufferedReader br = null;
	try
	{
		Process process = Runtime.getRuntime().exec(commandToExecute);
		f = new File(destinationDir + File.separator + backupFileName +".sql");
		fs = new FileOutputStream(f);
		is = process.getInputStream();
		isr = new InputStreamReader(is);
	        br = new BufferedReader(isr);
		String line;
		while ((line = br.readLine()) != null) 
		{
			line = line + "\n";
			fs.write(line.getBytes());
		}
	}
	catch (IOException io)
	{
		io.printStackTrace();
		System.out.println("IO Exception : "+io.getCause()+"      "+io.getMessage());
	}
	catch (Exception ex)
	{
		ex.printStackTrace();
	}
	finally
	{
		try
		{
			if (fs != null)
			{
				fs.close();
			}
			if (is != null)
			{
				is.close();
			}
			if (isr != null)
			{
				isr.close();
			}
			if (br != null)
			{
				br.close();
			}
		}		
		catch (Exception io)
		{
			System.err.println("Error in closing the IO Streams "+io); // No I18n
		}
	}
    }

    
   
    //Reads BackUp.conf file and get all the file names and directory names to
    //be backed up. Then takes backup of the specified files from WebNms Home
    //directory to WebNMS/backup directory
    private void backupConfFiles() throws Exception
    {
        String url = rootdir + "/conf/BackUp.conf";//No Internationalization
        Hashtable filesHash = BackupUtil.getAllFileNames(url);

        Vector dirsToBackup = null;
        Vector filesToBackup = null;

        if (filesHash.containsKey("dir"))
        {
            dirsToBackup = BackupUtil.getFilesFromString((String) filesHash.get("dir"));//No Internationalization

            if ((dirsToBackup != null) && (dirsToBackup.size() != 0))
            {
                backupAllDirectoryFiles(dirsToBackup);
            }
        }

        if (filesHash.containsKey("file"))
        {
            filesToBackup = BackupUtil.getFilesFromString((String) filesHash.get("file"));//No Internationalization

            if ((filesToBackup != null) && (filesToBackup.size() != 0))
            {
                for (int i = 0; i < filesToBackup.size(); i++)
                {
                    String fileToBackup = (String) filesToBackup.elementAt(i);
                    
                    String name = new File(fileToBackup).getName();

                    String dirPath = fileToBackup.substring(0, fileToBackup.indexOf(name));

                    File f = new File(backupDir + "/" + dirPath);//No Internationalization

                    f.mkdirs();
                    
                    File sourceFile = new File(rootdir + "/" + fileToBackup);//No Internationalization
                    File destinationFile = new File(backupDir + "/" + fileToBackup);//No Internationalization

                    if (BackupUtil.isEveryThingOk(sourceFile))
                    {
                        BackupUtil.copyFile(sourceFile, destinationFile);
                    }
                }
            }
        }
    }
    
    //Takes backup of all the files under the specified directories recursively
    private void backupAllDirectoryFiles(Vector dirsToBackup)
    {
        Vector allFilesVector = null;

        for (int i = 0; i < dirsToBackup.size(); i++)
        {
            String dirToBackup = (String) dirsToBackup.elementAt(i);

            File f = new File(dirToBackup);

            if (f.exists())
            {
                if (f.isDirectory())
                {
                    allFilesVector = new Vector();
                    BackupUtil.recurseAndGetTheFiles(f, allFilesVector);
                }
            }

            if (allFilesVector != null)
            {
                for (int j = 0; j < allFilesVector.size(); j++)
                {
                    File fileToBackup = (File) allFilesVector.elementAt(j);
                    String path = fileToBackup.getPath();

                    if (fileToBackup.isDirectory())
                    {
                        File dirFile = new File(backupDir + "/" + path);//No Internationalization
                        File parentDir = dirFile.getParentFile();
                        if(parentDir != null)
                        {
                            if(!parentDir.exists())
                            {
                                parentDir.mkdirs();
                            }
                        }
                    }
                    else
                    {
                        File sourceFile = new File(rootdir + "/" + path);//No Internationalization
                        File destinationFile = new File(backupDir + "/" + path);//No Internationalization
                        File parentDir = destinationFile.getParentFile();
                        if(parentDir != null)
                        {
                            if(!parentDir.exists())
                            {
                                parentDir.mkdirs();
                            }
                        }
                        if (BackupUtil.isEveryThingOk(sourceFile))
                        {
                            BackupUtil.copyFile(sourceFile, destinationFile);
                        }
                    }
                }
            }
        }
    }
        
    


    
    private static String rootdir=null;

    
    private static int fetchSize=500;

    public static void main(String args[]) throws Exception
    {
        rootdir=System.getProperty("webnms.rootdir");

        int argc=args.length;
        for (int argn = 0; argn < argc ; ++argn )
        {
            if (args[argn].equalsIgnoreCase("-n") && argn + 1 < argc)
            {
                ++argn;
                try{
                    fetchSize = Integer.parseInt(args[argn]);
                }
                catch(Exception e){System.out.println("Invalid FETCH_SIZE. It sets with default value 500");}
            }
            else if (args[argn].equalsIgnoreCase("-d") && argn + 1 < argc)
            {
                ++argn;
                destinationDir = args[argn];
            }

        }
	mysqlhome=System.getProperty("mysql.home");
	if(mysqlhome == null)
	{
		mysqlhome=rootdir+"/mysql";
	}
	if(mysqlhome.indexOf(".") >= 0)
	{
		mysqlhome=new File(mysqlhome).getAbsolutePath();
	}	

	pgsqlhome=System.getProperty("pgsql.home");
	if(pgsqlhome == null)
	{
		pgsqlhome=rootdir+"/pgsql";
	}
	if(pgsqlhome.indexOf(".") >= 0)
	{
		pgsqlhome=new File(pgsqlhome).getAbsolutePath();
	}	
        String separate=System.getProperty("separateJVM");
        if(separate.equalsIgnoreCase("true"))
            isSeparateJVM=true;

        MysqldumpBackup backup=new MysqldumpBackup();
        Date date=Calendar.getInstance().getTime();
        try{
            backup.runBackUp(date);
        }
        catch(Exception e)
	{
		e.printStackTrace();
		String message = e.getMessage();
		if (message.indexOf("No space left on device") != -1)
		{
			System.out.println("Backup cannot be done, as there is no space left on the device. Please clear the disk space and then proceed with backup." );
			System.exit(1);
		}
		else
		{
			System.err.println("Error occurred while doing backup : "+e.getMessage()); // no i18n	
		}
		
	}
        CreateWebNmsDB.stopMysqld();
        ch=true;
    }


	public  void  setRootDir(String rootDirectory ){
		rootdir  = rootDirectory;
	}
	
	public void setSelectiveTableBackup( boolean value){
		selectiveTableBackup = value ;
	}

	public void setTablesToBackUp( String[]  tableNames){
		tablesToBackup = tableNames ;	
	}

	public void handleDiscSpaceException(String message)
	{
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
				if (schema == null){
					schema = "WebNmsDB";
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
	private String addUserPasswordHostPort(String commandToExecute)
	{
		String userName = dbpp.getUserName();
		if(dbproductName.equalsIgnoreCase("mysql")){
			if(userName != null)
			{
				commandToExecute = commandToExecute + " -u " + userName;
			}
			String password = dbpp.getPassword();
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
			String host = dbpp.getDBHost();
			if (host != null)
			{
				commandToExecute = commandToExecute + " -h " + host;
			}
			String port = dbpp.getDBPort();
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
}






