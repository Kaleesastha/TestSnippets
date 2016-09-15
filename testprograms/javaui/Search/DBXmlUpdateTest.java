
import java.io.*;
import java.util.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import com.adventnet.nms.util.PureUtils;
import com.adventnet.nms.util.PureServerUtils;
import com.adventnet.nms.util.*;
import org.w3c.dom.Element;
import com.adventnet.nms.fe.common.*;
import com.adventnet.nms.db.util.*;

public class DBXmlUpdateTest  
{
    
    DBXmlUpdate dbup = null;
    
    private void startProcess(String WebNmsHome, String Database, String option)
    {

        Connection con=null; 
        
        String webnmshome = WebNmsHome+File.separator;
        System.out.println("WEBNMS HOME IS "+webnmshome);
        PureUtils.rootDir=webnmshome;
        PureUtils.usersDir=webnmshome;
        String db = Database.toUpperCase(); 
        System.out.println("DATA BASE IS " +db);
        
        try
        {
            if (db.equals("MYSQL"))
            {
                con = getMYSQLConnection();
            }
            else if (db.equals("ORACLE"))
            {
                con = getORACLEConnection();
            }
            else if (db.equals("TIMESTEN"))
            {
                con = getTIMESTENConnection();
            }
            else if (db.equals("SOLID"))
            {
                con = getSOLIDConnection();
            }
            else if (db.equals("SYBASE"))
            {
                con = getSYBASEConnection();
            }
            else
            {
                System.out.println("DATABASE NOT SELECTED");
                System.exit(0);
            }
            
            dbup = new DBXmlUpdate(con);     /** getting the instance of DBXmlUpdate **/     
        }
        catch(Exception e)
        {
            System.out.println(" unable to instantiate DBXmlUpdate");
            e.printStackTrace();
            System.exit(0);
        }
        
        System.out.println(" DBXmlUpdate object is successfully created");

        if(option != null)
        {
            if(option.equals("5"))
            {
                executeTestCase005();
            }
            else if(option.equals("19"))
            {
                executeTestCase019();
            }
        }
        else
        {
            executeTestCase001();
            executeTestCase003();
            executeTestCase004();
            executeTestCase010();
            executeTestCase011();
            executeTestCase012();
            executeTestCase014();
            executeTestCase015();
            executeTestCase016();
            executeTestCase017();
            executeTestCase018();
        }
        System.exit(0);
    }
    
    private Connection getMYSQLConnection()
    {
        try
        {
            Class.forName("org.gjt.mm.mysql.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost/WebNmsDB","root",null);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    private Connection getORACLEConnection()
    {
        try
        {
            
            Class.forName("oracle.jdbc.driver.OracleDriver");
            return DriverManager.getConnection("jdbc:oracle:thin:@nms-clienttest1:1521:CATSDB","test","test");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }      
    }

    private Connection getTIMESTENConnection()
    {
        try
        {
            Class.forName("com.timesten.jdbc.TimesTenDriver");
            return DriverManager.getConnection("jdbc:timesten:direct:WebNmsDB","root",null);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    private Connection getSOLIDConnection()
    {
        try
        {
            Class.forName("solid.jdbc.SolidDriver");
            return DriverManager.getConnection("jdbc:solid://nms-clienttest1:1313/dba/dba","dba","dba"); 
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    private Connection getSYBASEConnection()
    {
        try
        {
            Class.forName("com.sybase.jdbc2.jdbc.SybDriver");
            return DriverManager.getConnection("jdbc:sybase:Tds:fe-test:2048/feDB","sa",null);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    private void executeTestCase001()
    {
        System.out.println("********* Executing the Test Case 001 **********");
        System.out.println(" Test Case Description : updates the database for the user : root");
        boolean res1=false;
        try
        {
            res1 = dbup.updateDB("root","Tree.xml");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        if(res1)
        {
            System.out.println("Result for Test case 001 : PASSED ");
            System.out.println(" Result Description : check the database whether the entries of the user root are updated");
        }
        else
        {
            System.out.println("Result for Test case 001 : FAILED ");
        }
        System.out.println(" *********** Test Case 001 Finished *************");
    }

    private void executeTestCase003()
    {
        System.out.println("********** Executing the Test Case 003 ***********");
        System.out.println("Test case Description : updateDB method when data is present in PanelTree for the user : root");
        boolean res3=false;
        try
        {
            res3 = dbup.updateDB("root","Tree.xml");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        if(res3)
        {
        
            System.out.println("Result for Test case 003 : FAILED ");
        }
        else
        {
            System.out.println("Result for Test case 003 : PASSED ");
        }
        System.out.println(" *********** Test Case 003 Finished ************");
    }

    private void executeTestCase004()
    {
        System.out.println("********** Executing the Test Case 004 ***********");
        System.out.println("Test case 004: UpdateDB for username: All");
        boolean res4=false;
        try
        {
            res4 = dbup.updateDB("All","Tree.xml");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        if(res4)
        {
            System.out.println("Result for Test case 004 : PASSED ");
            System.out.println(" Result Description : check the database whether the entries of the user root are updated");
        }
        else
        {
            System.out.println("Result for Test case 004 : FAILED ");
        }
        System.out.println("************ Test case 004 Finished ***********");
    }

    private void executeTestCase005()
    {
        System.out.println("********** executing the test case 005 ***********");
        System.out.println("Test case 005: Delete PanelTree and PanelProps table and updateDB for user :root");
        boolean res5=false;
        try
        {
            res5 = dbup.updateDB("root","Tree.xml");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        if(res5)
        {
            System.out.println("Result for Test case 005 : PASSED ");
            System.out.println(" Result Description : check the database whether the entries of the user root are updated");
        }
        else
        {
            System.out.println("Result for Test case 005 : FAILED ");
        }
        System.out.println("*********** Test case 005 Finished ***********");
    }

    private void executeTestCase010()
    {
        System.out.println("********** Executing the Test Case 010 ***********");
        System.out.println("Test case 010: update data base with User Name: null");
                
        try
        {
            boolean res10 = dbup.updateDB(null,"Tree.xml");
        }
        catch(Exception e)
        {
            System.out.println("Exception is ....");
            e.printStackTrace();
            System.out.println(" Result Description : check the exception is : user name cannot be null");
            System.out.println("Result for Test case 010 : PASSED");
            return;
        }
                
        System.out.println("Result for Test case 010 : FAILED");
        System.out.println("********** Test case 010 Finished ***********");
    }

    private void executeTestCase011()
    {
        System.out.println("********** Executing the Test Case 011 ***********");
        System.out.println("Test case 011: update data base with File Name :invaid");
                                
        try
        {
            boolean res11 = dbup.updateDB("root","Test.xml");
        }
        catch(Exception e)
        {
            System.out.println("Exception is ....");
            e.printStackTrace();
            System.out.println(" Result Description : check the exception is : user name cannot be null");
            System.out.println("Result for Test case 011 : PASSED");
            return;
        }
                
        System.out.println("Result for Test case 011 : FAILED");
        System.out.println("********* Test case 011 Finished ***********");
    }

    private void executeTestCase012()
    {
        System.out.println("********** Executing the Test Case 012 ***********");
        System.out.println("Test case 012: update data base with User Name: invaid");
                                                
        try
        {
            boolean res12 = dbup.updateDB("test","Tree.xml");
        }
        catch(Exception e)
        {
            System.out.println("Exception is ....");
            e.printStackTrace();
            System.out.println(" Result Description : check the exception is : user name cannot be null");
            System.out.println("Result for Test case 012 : PASSED");
            return;
        }
                
        System.out.println("Result for Test case 012 : FAILED");
        System.out.println("********** Test case 012 Finished ***********");
    }

    private void executeTestCase014()
    {
        System.out.println("********** Executing the Test Case 014 ***********");
        System.out.println("Test case 014: Write to XML  for user name : root");
        boolean res14=false;
        try
        {
            res14 = dbup.writeToXml("root","hi.xml");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        if(res14)
        {
            System.out.println("Result for Test case 014 : PASSED ");
            System.out.println(" Result Description : check the database whether the entries of the user root are written");
        }
        else
        {
            System.out.println("Result for Test case 014 : FAILED ");
        }
        System.out.println("********* Test case 014 Finished ************");
    }

    private void executeTestCase015()
    {
        System.out.println("********** Executing the Test Case 015 ***********");
        System.out.println("Test case 015: Write to XML  for user name : null");
        
        try
        {
            boolean res15 = dbup.writeToXml(null,"Test.xml");
        }
        catch(Exception e)
        {
            System.out.println("Exception is ....");
            e.printStackTrace();
            System.out.println(" Result Description : check the exception is : user name cannot be null");
            System.out.println("Result for Test case 015 : PASSED");
            return;
        }
        
        System.out.println("Result for Test case 015 : FAILED");
        System.out.println("********* Test case 015 Finished ************");
    }

    private void executeTestCase016()
    {
        System.out.println("********** Executing the Test Case 016 ***********");
        System.out.println("Test case 016: Write to XML  for File name : null");
        try
        {
            boolean res16 = dbup.writeToXml("root",null);
        }
        catch(Exception e)
        {
            System.out.println("Exception is ....");
            e.printStackTrace();
            System.out.println(" Result Description : check the exception is : user name cannot be null");
            System.out.println("Result for Test case 016 : PASSED");
            return;
        }
        
        System.out.println("Result for Test case 016 : FAILED");
        System.out.println("********* Test case 016 Finished ************");
    }

    private void executeTestCase017()
    {
        System.out.println("********** Executing the Test Case 017 ***********");
        System.out.println("Test case 017: Write to XML  for File name : invalid");
        try
        {
            boolean res17 = dbup.writeToXml("root","test");
        }
        catch(Exception e)
        {
            System.out.println("Exception is ....");
            e.printStackTrace();
            System.out.println(" Result Description : check the exception is : user name cannot be null");
            System.out.println("Result for Test case 017 : PASSED");
            return;
        }
        
        System.out.println("Result for Test case 017 : FAILED");
        System.out.println("********* Test case 017 Finished ************");
    }

    private void executeTestCase018()
    {
        System.out.println("********** Executing the Test Case 018 ***********");
        System.out.println("Test case 018: Write to XML  for user name : invalid");
        try
        {
            boolean res18 = dbup.writeToXml("test","Tree.xml");
        }
        catch(Exception e)
        {
            System.out.println("Exception is ....");
            e.printStackTrace();
            System.out.println(" Result Description : com.adventnet.nms.store.NmsStorageException: E:/AdventNet/WebNMS/users/test/Tree.xml (The system cannot find the path specified)");
            System.out.println("Result for Test case 018 : PASSED");
            return;
        }
        
        System.out.println("Result for Test case 018 : FAILED");
        System.out.println("********* Test case 018 Finished ************");
    }

    private void executeTestCase019()
    {
        System.out.println("********** Executing the Test Case 019 ***********");
        System.out.println("Test case 019: First delete PanelTree and PanelProps tables and do updateDB and Write to XML operation for the user : root");
        
        try
        {
            boolean res191 = dbup.updateDB("root","Tree.xml");      
        }
        catch(Exception e)
        {
            System.out.println("Exception is ....");
            e.printStackTrace();
            System.out.println(" Result Description : check the exception is : user name cannot be null");
            System.out.println("Result for Test case 019 : PASSED");
            return;
        }
        
        try
        {
            boolean res192 = dbup.writeToXml("root","Tree.xml");
        }
        catch(Exception e)
        {
            System.out.println("Exception is ....");
            e.printStackTrace();
            System.out.println(" Result Description : check the exception is : user name cannot be null");
            System.out.println("Result for Test case 019 : PASSED");
            return;
        }
        
        System.out.println("Result for Test case 019 : FAILED");
        System.out.println("********* Test case 019 Finished ************");  
    }

    public static void main(String args[]) throws Exception
    {    
        
        DBXmlUpdateTest obj = new DBXmlUpdateTest();
        
        if ((args.length >= 3))
        {
            obj.startProcess(args[0],args[1],args[2]);
        }
        else if (args.length ==2) 
        {
            obj.startProcess(args[0],args[1],null);
        }
        else
        {
            System.out.println("USAGE: java DBXmlUpdateTest <Full path of WebNMS_Home> DATABASE 5 or 19 ");
            System.out.println("Full path of WebbNMS_Home : depending upon your operating system");
            System.out.println("DATABASE : Give your data base name like MYSQL/SOLID/TIMESTEN/ORACLE/SYBASE");
            
            System.out.println("5 or 19 : Test case 5 or 19 that If you want to run separately");
            
        }
    }
    
}



    



