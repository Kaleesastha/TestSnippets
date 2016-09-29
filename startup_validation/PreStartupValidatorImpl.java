//$Id: PreStartupValidatorImpl.java,v 1.8 2008/03/12 11:34:42 parimala Exp $

package test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

import com.adventnet.nms.admin.PreStartupValidator;
import com.adventnet.nms.admin.StartUpAction;
import com.adventnet.nms.util.DBParamsParser;
import com.adventnet.nms.util.NmsUtil;

public class PreStartupValidatorImpl implements PreStartupValidator
{
    public PreStartupValidatorImpl ()
    {
        
    }

    public int validate(int module, Object obj)
    {
        if (module==PreStartupValidator.DATABASE)
        {
            if (obj==null)
            {
                return checkSupportedDBVersions();
            }else
            {
                Exception e = (Exception)obj;
                if (e instanceof SQLException)
                {
                    // handle MySQL database error scenarios here.
                    if (e.getMessage().indexOf("Server configuration denies access to data source")!=-1 ||
                        e.getMessage().indexOf("is not allowed to connect to this MySQL server")!=-1) // No I18N
                    {
                        System.err.println("\n"+NmsUtil.GetString("server.framework.prestartupvalidator.err1")+" "); //No I18N
                        System.err.println(NmsUtil.GetString("server.framework.prestartupvalidator.dbcmd")+":\n"); //No I18N
                        System.err.println("grant all privileges on *.* to 'root'@'%';\n"); //No I18N
                        System.err.println("flush privileges;\n\n"); //No I18N
                        System.err.println("************************************************************************\n"); // No I18N
                    }else if (e.getMessage().indexOf("Unknown database")!=-1) // No I18N
                    {
                        System.err.println("\n"+NmsUtil.GetString("server.framework.prestartupvalidator.dbport")+" "); //No I18N
                        System.err.println("\n"+NmsUtil.GetString("server.framework.prestartupvalidator.dbperm1")+" <MySQL_HOME>/data "
                                           +NmsUtil.GetString("server.framework.prestartupvalidator.dbperm2")); //No I18N
                        System.err.println("************************************************************************\n"); // No I18N
                    }else if (e.getMessage().indexOf("Bad handshake")!=-1) // No I18N
                    {
                        System.err.println("\n"+NmsUtil.GetString("server.framework.prestartupvalidator.dbdriver1")+"\n"); //No I18N
                        System.err.println(NmsUtil.GetString("server.framework.prestartupvalidator.dbdriver2")+" <NMS_HOME>/setEnv.bat/sh\n\n"); //No I18N
                        System.err.println("************************************************************************\n"); // No I18N
                    }
                }
                return StartUpAction.ABORT;
            }
        }
        return StartUpAction.CONTINUE;
    }

    private int checkSupportedDBVersions()
    {
        // check mysql, oracle and other driver versions here.
        // Abort NMS if the version is lower that the supported version else continue.
        try
        {

            DBParamsParser parser = DBParamsParser.getInstance();
            if (parser == null)
            {
                System.err.println("DBParamsParser null"); // No I18N
                return StartUpAction.ABORT;
            }
            Connection conn = DriverManager.getConnection(parser.getURL(),parser.getUserName(),parser.getPassword());
            if (conn == null)
            {
                System.err.println("DB connection object null"); // No I18N
                return StartUpAction.ABORT;
            }
            String databaseName = conn.getMetaData().getDatabaseProductName();
            String databaseVersion = conn.getMetaData().getDatabaseProductVersion();            
            int supportedVersion = 5;
            int dbVersion = 0;
            if (databaseName.equalsIgnoreCase("Oracle"))
            {
                String temp = databaseVersion.substring(0,databaseVersion.indexOf(".")); //No I18N
                dbVersion = Integer.parseInt(temp.substring(temp.lastIndexOf(" ")+1,temp.length()));
            }else if (databaseVersion.indexOf(".")!=-1) //No I18N
            {
                dbVersion = Integer.parseInt(databaseVersion.substring(0,databaseVersion.indexOf("."))); // No I18N
            }else
            {
                dbVersion = Integer.parseInt(databaseVersion);
            }
            //System.out.println(" db version ### "+dbVersion);

            if (databaseName.equalsIgnoreCase("Oracle"))
            {
                supportedVersion = 9;
            }else if (databaseName.equalsIgnoreCase("MySQL")) // No I18N
            {
                supportedVersion = 5;
            }else if (databaseName.equalsIgnoreCase("SQL Anywhere")) // No I18N
            {
                supportedVersion = 10;
            }else if (databaseName.equalsIgnoreCase("PostgreSQL")) // No I18N
            {
                supportedVersion = 8;
            }
            else if (databaseName.equalsIgnoreCase("Microsoft SQL Server")) // No I18N
            {
                supportedVersion = 9;
            }
	            if (dbVersion<supportedVersion)
            {
                System.out.println("\n"+NmsUtil.GetString("server.framework.prestartupvalidator.dbversion.current")+" "+databaseName+databaseVersion); //No I18N
                System.err.println("\n"+NmsUtil.GetString("server.framework.prestartupvalidator.dbversion.minor1")+" "+databaseName+supportedVersion // No I18N
                                   +" "+NmsUtil.GetString("server.framework.prestartupvalidator.dbversion.minor2")+"\n"); //No I18N
                return StartUpAction.ABORT;
            }
	    /*else if (dbVersion>supportedVersion)
            {
                System.out.println("\n"+NmsUtil.GetString("server.framework.prestartupvalidator.dbversion.current")+" "+databaseName+databaseVersion); //No I18N
                System.err.println("\n"+NmsUtil.GetString("server.framework.prestartupvalidator.dbversion.major1")+" "+databaseName+supportedVersion // No I18N
                                   +" "+NmsUtil.GetString("server.framework.prestartupvalidator.dbversion.major2")+".\n"); //No I18N
            }*/
            return StartUpAction.CONTINUE;

        } catch(SQLException ex)
        {
            ex.printStackTrace();
            return StartUpAction.ABORT;
        }catch (Exception e)
        {
            e.printStackTrace();
            return StartUpAction.ABORT;
        }

    }

}// PreStartupValidatorImpl
