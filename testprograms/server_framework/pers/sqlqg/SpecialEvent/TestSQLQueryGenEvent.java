
import java.io.File;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import javax.servlet.GenericServlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.PrintWriter;
import java.sql.Statement;
import java.util.Vector;
import java.util.Properties;
import java.util.Enumeration;

import com.adventnet.nms.util.DBParamsParser;
import com.adventnet.nms.util.PureUtils;
import com.adventnet.nms.db.util.SQLQueryGenerator;
import com.adventnet.nms.db.util.RelationalInterface;
import com.adventnet.nms.db.util.RelationalInterfaceImpl;
import com.adventnet.nms.store.relational.RelationalUtil;
import com.adventnet.nms.store.relational.RelationalAPI;

public class TestSQLQueryGenEvent extends GenericServlet
{
    private static String rootDir;
    private SQLQueryGenerator q;
    private Connection conn = null;
    private PrintWriter pw = null;
    private Statement statement = null;

    public void service(ServletRequest req,ServletResponse res)
    {
        try
        {
            res.setContentType("text/html");
            pw = res.getWriter();
            in();
            test();
            close();
    	}
        catch(Exception j)
        {
            j.printStackTrace();
        }
    }


    private void in()
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
            pw.println("CONNECTION...:  " + conn);
            pw.println("<br><br>");
            statement = conn.createStatement();

        }
        catch(Exception ce)
        {
            pw.println(ce.getMessage());
            pw.println("<br><br>");
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
		q = new SQLQueryGenerator(conn, "EventDB", "Event", "EVENTUSERPROPS", relImpl);
        pw.println("Created an instance of the SQL Q G : "+q);
        pw.println("<br><br>");
    }


    private void test()
    {
        pw.println("Starting to test the TestCases!!!!");
        pw.println("<br><br>");
        long time1 = System.currentTimeMillis();

        testCase123();
        testCase124();
        testCase125();
        testCase126();
        testCase127();
        testCase128();
        testCase129();
        testCase130();
        testCase131();
        testCase132();
        testCase133();
        testCase134();
        testCase135();
        testCase136();
        testCase137();
        testCase138();
        testCase139();
        testCase140();
        testCase141();
        testCase142();

        long time2 = System.currentTimeMillis();
        pw.println("COMPLETED ALL  TESTCASES IN THE TESTPLAN SF-PERS-SQG: ");
        pw.println("<br>");
        pw.println("Time taken to Validate the 	TestCases : "+(time2-time1));

    }

    private void testCase123()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty("specialEventName", "SpecialEvent" );
            String[] colsToView = {"specialEventIdentifier","specialEventId" };
            Vector v =	 q.getData(colsToView, props,false,false);
            Enumeration en = v.elements();
            String sql =q.getSqlString(colsToView,props);
            pw.println("TESTCASE 123 Query-" +sql);
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE 123 -> DATA : "+en.nextElement());
                pw.println("<br><br>");
            }
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception y)
        {
            y.printStackTrace();
        }
    }

    private void testCase124()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty("specialEventName", "SpecialEvent" );
            props.setProperty("node", "I*" );
            String[] colsToView = {"specialEventIdentifier","specialEventId" };
            Vector v =	 q.getData(colsToView, props,false,true);
            Enumeration en = v.elements();
            String sql =q.getSqlString(colsToView,props);
            pw.println("TESTCASE 124 Query-" +sql);
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE 124 -> DATA : "+en.nextElement());
                pw.println("<br><br>");
            }
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception y)
        {
            y.printStackTrace();
        }
    }
    private void testCase125()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty("specialEventName", "SpecialEvent" );
            String[] colsToView = {"specialEventIdentifier","specialEventId" };
            Vector v =	 q.getData(colsToView, props,true,false);
            Enumeration en = v.elements();
            String sql =q.getSqlString(colsToView,props);
            pw.println("testCase125 Query-" +sql);
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE 125**** -> DATA : "+en.nextElement());
                pw.println("<br><br>");
            }
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception y)
        {
            y.printStackTrace();
        }
    }
    private void testCase126()
    {
    try
    {
        Properties props = new	Properties();
        props.setProperty("specialEventName", "SpecialEvent" );


        String[] colsToView = {"specialEventIdentifier","specialEventId" };
        Vector v =	 q.getData(colsToView, props,false,false);
        Enumeration en = v.elements();
        String sql =q.getSqlString(colsToView,props);
				pw.println("TESTCASE 126 Query-" +sql);
        while(en.hasMoreElements())
        {
            pw.println("<br>TESTCASE 126-> DATA : "+en.nextElement());
            pw.println("<br><br>");
        }
        pw.println("<br><br>");
        pw.println("<br><br>");
    }
    catch(Exception y)
    {
        y.printStackTrace();
    }
    }

    private void testCase127()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty("specialEventName", "SpecialEvent" );
            props.setProperty( "specialEventIdentifier", "kingand" );

            String[] colsToView = {"specialEventIdentifier","specialEventId" };
            Vector v =	 q.getData(colsToView, props,false,false);
            Enumeration en = v.elements();
            String sql =q.getSqlString(colsToView,props);
            pw.println("TESTCASE 127 Query-" +sql);
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE 127 -> DATA : "+en.nextElement());
                pw.println("<br><br>");
            }
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception y)
        {
            y.printStackTrace();
        }
    }

    private void testCase128()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty("specialEventName", "SpecialEvent" );
            props.setProperty( "specialEventIdentifier", "kingand" );
            props.setProperty("specialEventId", "raj" );
            String[] colsToView = {"specialEventIdentifier","specialEventId","specialEventName" };
            Vector v =	 q.getData(colsToView, props,false,false);
            Enumeration en = v.elements();
            String sql =q.getSqlString(colsToView,props);
            pw.println("TESTCASE 128 Query-" +sql);
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE 128 -> DATA : "+en.nextElement());
                pw.println("<br><br>");
            }
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception y)
        {
            y.printStackTrace();
        }
    }

    private void testCase129()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty("name", "*" );
            String[] colsToView = {"specialEventName" };
            Vector v =	 q.getData(colsToView, props,false,false);
            Enumeration en = v.elements();
            String sql =q.getSqlString(colsToView,props);
            pw.println("TESTCASE 129 Query-" +sql);
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE 129 -> DATA : "+en.nextElement());
                pw.println("<br><br>");
            }
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception y)
        {
            y.printStackTrace();
        }
    }

    private void testCase130()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty("specialEventName", "*" );

            String[] colsToView = {"specialEventName","specialEventIdentifier" ,"specialEventId"};
            Vector v =	 q.getData(colsToView, props,false,false);
            Enumeration en = v.elements();
            String sql =q.getSqlString(colsToView,props);
            pw.println("TESTCASE 130 Query-" +sql);
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE 130 -> DATA : "+en.nextElement());
                pw.println("<br><br>");
            }
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception y)
        {
            y.printStackTrace();
        }
    }

    private void testCase131()
	    {
	        try
	        {
	            Properties props = new	Properties();
	            props.setProperty( "specialEventSeverity", "10" );
	            String[] colsToView = {"specialEventName","specialEventIdentifier" ,"specialEventId"};
	            Vector v =	 q.getData(colsToView, props,false,false);
	            Enumeration en = v.elements();
	            String sql =q.getSqlString(colsToView,props);
	            pw.println("TESTCASE 131 Query-" +sql);
	            while(en.hasMoreElements())
	            {
	                pw.println("TESTCASE 131 -> DATA : "+en.nextElement());
	                pw.println("<br><br>");
	            }
	            pw.println("<br><br>");
	            pw.println("<br><br>");
	        }
	        catch(Exception y)
	        {
	            y.printStackTrace();
	        }
    }


private void testCase132()
	    {
	        try
	        {
	            Properties props = new	Properties();
	            props.setProperty( "specialEventSeverity", "10" );
	            props.setProperty("node", "I*" );
	            String[] colsToView = {"specialEventName","specialEventIdentifier" ,"specialEventId"};
	            Vector v =	 q.getData(colsToView, props,false,false);
	            Enumeration en = v.elements();
	            String sql =q.getSqlString(colsToView,props);
	            pw.println("Test Case 132 Query-" +sql);
	            while(en.hasMoreElements())
	            {
	                pw.println("TESTCASE 132 -> DATA : "+en.nextElement());
	                pw.println("<br><br>");
	            }
	            pw.println("<br><br>");
	            pw.println("<br><br>");
	        }
	        catch(Exception y)
	        {
	            y.printStackTrace();
	        }
    }


    private void testCase133()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty( "specialEventSeverity", "10" );
            String[] colsToView = {"specialEventIdentifier" ,"specialEventId"};
            Vector v =	 q.getData(colsToView, props,true,false);
            Enumeration en = v.elements();
            String sql =q.getSqlString(colsToView,props);
            pw.println("TESTCASE 133 Query-" +sql);
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE 133 -> DATA : "+en.nextElement());
                pw.println("<br><br>");
            }
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception y)
        {
            y.printStackTrace();
        }
    }

    private void testCase134()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty( "specialEventSeverity", "2" );
            String[] colsToView = {"specialEventIdentifier" ,"specialEventId"};
            Vector v =	 q.getData(colsToView, props,true,true);
            Enumeration en = v.elements();
            String sql =q.getSqlString(colsToView,props);
            pw.println("TESTCASE 134 Query-" +sql);
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE 134 -> DATA : "+en.nextElement());
                pw.println("<br><br>");
            }
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception y)
        {
            y.printStackTrace();
        }
    }


    private void testCase135()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty( "specialEventSeverity", "2" );
            props.setProperty( "specialEventIdentifier", "kingand" );
            String[] colsToView = {"specialEventIdentifier" ,"specialEventId"};
            Vector v =	 q.getData(colsToView, props,false,false);
            Enumeration en = v.elements();
            String sql =q.getSqlString(colsToView,props);
            pw.println("TESTCASE 135 Query-" +sql);
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE 135-> DATA : "+en.nextElement());
                pw.println("<br><br>");
            }
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception y)
        {
            y.printStackTrace();
        }
    }

    private void testCase136()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty( "specialEventSeverity", "10" );
            props.setProperty( "specialEventIdentifier", "kingand" );
            props.setProperty("specialEventId", "raj" );
            String[] colsToView = {"specialEventIdentifier" ,"specialEventId"};
            Vector v =	 q.getData(colsToView, props,false,false);
            Enumeration en = v.elements();
            String sql =q.getSqlString(colsToView,props);
            pw.println("TESTCASE 136 Query-" +sql);
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE 136-> DATA : "+en.nextElement());
                pw.println("<br><br>");
            }
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception y)
        {
            y.printStackTrace();
        }
    }

    private void testCase137()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty("node", "*" );
            String[] colsToView = {"specialEventSeverity"};
            Vector v =	 q.getData(colsToView, props,false,false);
            Enumeration en = v.elements();
            String sql =q.getSqlString(colsToView,props);
            pw.println("Test case 137 Query-" +sql);
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE 137-> DATA : "+en.nextElement());
                pw.println("<br><br>");
            }
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception y)
        {
            y.printStackTrace();
        }
    }

    private void testCase138()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty( "specialEventSeverity", "10" );
            String[] colsToView = {"specialEventIdentifier" ,"specialEventId","specialEventSeverity"};
            Vector v =	 q.getData(colsToView, props,false,false);
            Enumeration en = v.elements();
            String sql =q.getSqlString(colsToView,props);
            pw.println("TESTCASE 138 Query-" +sql);
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE 138-> DATA : "+en.nextElement());
                pw.println("<br><br>");
            }
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception y)
        {
            y.printStackTrace();
        }
    }

    private void testCase139()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty("specialEventName", "SpecialEvent" );
            props.setProperty( "specialEventSeverity", "10" );

            String[] colsToView = {"specialEventIdentifier" ,"specialEventId"};
            Vector v =	 q.getData(colsToView, props,false,false);
            Enumeration en = v.elements();
            String sql =q.getSqlString(colsToView,props);
            pw.println("TESTCASE 139 Query-" +sql);
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE 139 -> DATA : "+en.nextElement());
                pw.println("<br><br>");
            }
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception y)
        {
            y.printStackTrace();
        }
    }

    private void testCase140()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty("specialEventName", "SpecialEvent" );
            props.setProperty( "specialEventSeverity", "10" );
            String[] colsToView = {"specialEventIdentifier" ,"specialEventId","specialEventName"};
            Vector v =	 q.getData(colsToView, props,false,false);
            Enumeration en = v.elements();
            String sql =q.getSqlString(colsToView,props);
            pw.println("TESTCASE 140 Query-" +sql);
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE 140-> DATA : "+en.nextElement());
                pw.println("<br><br>");
            }
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception y)
        {
            y.printStackTrace();
        }
    }

    private void testCase141()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty("specialEventName", "SpecialEvent" );
            props.setProperty( "specialEventSeverity", "10" );
            String[] colsToView = {"specialEventIdentifier" ,"specialEventId","specialEventName"};
            Vector v =	 q.getData(colsToView, props,false,true);
            Enumeration en = v.elements();
            String sql =q.getSqlString(colsToView,props);
            pw.println("TESTCASE 141 Query-" +sql);
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE 141-> DATA : "+en.nextElement());
                pw.println("<br><br>");
            }
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception y)
        {
            y.printStackTrace();
        }
    }


    private void testCase142()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty("id", "<between> 1" );
            String[] colsToView = {"specialEventIdentifier" ,"specialEventId","specialEventName"};
            Vector v =	 q.getData(colsToView, props,false,false);
            Enumeration en = v.elements();
            String sql =q.getSqlString(colsToView,props);
            pw.println("TESTCASE 142 Query-" +sql);
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE 142-> DATA : "+en.nextElement());
                pw.println("<br><br>");
            }
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception y)
        {
            y.printStackTrace();
        }
    }


    private void close()
    {
        try
        {
            conn.close();
        }
        catch(Exception ii)
        {
            ii.printStackTrace();
        }
        try
        {
            statement.close();
        }
        catch(Exception k)
        {
            k.printStackTrace();
        }
    }




}
