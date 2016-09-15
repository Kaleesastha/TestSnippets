//$Id: TestSqlQG.java,v 1.1 2001/05/03 08:09:44 tmkarthi Exp $
/**
 * TestSqlQG.java
 *
 *
 * Created: Wed Apr 25 22:22:54 2001
 *
 * @author Karthikeyan T.M
 * @version
 */
package test;

import java.util.*;
import java.io.File;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

import com.adventnet.nms.util.DBParamsParser;
import com.adventnet.nms.util.PureUtils;
import com.adventnet.nms.db.util.SQLQueryGenerator;
import com.adventnet.nms.db.util.RelationalInterface;
import com.adventnet.nms.db.util.RelationalInterfaceImpl;
import com.adventnet.nms.store.relational.RelationalUtil;
import com.adventnet.nms.store.relational.RelationalAPI;
public class TestSqlQG
{
    public static String rootDir;
    public SQLQueryGenerator queryGen;
    private Connection conn = null;
    
    public TestSqlQG ()
    {
        init();
        getDatabaseProduct();
        
        // Your code goes here

    }
    
    private void init()
    {
        DBParamsParser parse = null;
        String parseFileName = PureUtils.rootDir + "conf" + "/" + "database_params.conf";
        File parseFile = new File(parseFileName);
        try
        {
            parse = DBParamsParser.getInstance(parseFile);//Database related details are read to create a DB connection. 
        }
        catch(Exception et)
        {
            et.printStackTrace();
        }
        String url = parse.getURL();
        String user = parse.getUserName();
        String driver = parse.getDriverName();
        String passwd = parse.getPassword();
        try
        {
            Class.forName(driver);
            conn=DriverManager.getConnection(url,user,passwd);
            System.out.println("CONNECTION...:  " + conn);
        }
        catch(Exception ce)
        {
            System.out.println(ce.getMessage());
            ce.printStackTrace();
        }

        try
        {
            RelationalAPI relapi = new RelationalAPI(url,user,passwd,driver,false);
            RelationalUtil.init(relapi);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        RelationalInterfaceImpl relImpl = new RelationalInterfaceImpl();
        //Instantiate SQLQueryGenerator for the module you want
        // For Topo
        queryGen = new SQLQueryGenerator(conn, "TopoDB", "ManagedObject", "TOPOUSERPROPS", relImpl);
        // For Alerts module
        //queryGen = new SQLQueryGenerator(conn, "AlertDB", "Alert", "ALERTUSERPROPS", relImpl);
        // For Events module
        //queryGen = new SQLQueryGenerator(conn, "EventDB", "Event", "EVENTUSERPROPS", relImpl);
    }

    private void getDatabaseProduct()
    {
        System.out.println("Database Product used is " + queryGen.getDatabaseProduct());
    }

    /**
     * Parses comma separated string into a Properties.
     */
    public static Properties parseProperties(String value)
    {
        Properties prop = new Properties();
        if(value != null)
        {
            StringTokenizer tok = new StringTokenizer(value, ",");
            String next = null;
            while(tok.hasMoreTokens())
            {
                next = tok.nextToken();
                prop.setProperty(next.substring(0, next.indexOf('=')), next.substring(next.indexOf('=') + 1));
            }
        }
        return prop;

    }

    public static void main(String[] args)
    {
        if(args.length < 1)
        {
            System.out.println("Usage: java TestSqlQG <WebNMS_HOME>");
            System.exit(1);
        }
        rootDir = args[0] + "/";
        PureUtils.rootDir = rootDir;
        TestSqlQG test = new TestSqlQG();
    }
} // TestSqlQG
