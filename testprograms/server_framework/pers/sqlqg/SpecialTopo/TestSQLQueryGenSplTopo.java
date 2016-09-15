
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

public class TestSQLQueryGenSplTopo extends GenericServlet
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
        q = new SQLQueryGenerator(conn, "TopoDB", "ManagedObject", "TOPOUSERPROPS", relImpl);
	    pw.println("Created an instance of the SQL Q G : "+q);
        pw.println("<br><br>");
    }


    private void test()
    {
        pw.println("Starting to test the TestCases!!!!");
        pw.println("<br><br>");
        long time1 = System.currentTimeMillis();


        testCase103();
        testCase104();
        testCase105();
        testCase106();
        testCase107();
        testCase108();
        testCase109();
        testCase110();
        testCase111();
        testCase112();
        testCase113();
        testCase114();
        testCase115();
        testCase116();
        testCase117();
        testCase118();
        testCase119();
        testCase120();
        testCase121();
        testCase122();

        long time2 = System.currentTimeMillis();
        pw.println("COMPLETED ALL  TESTCASES IN THE TESTPLAN SF-PERS-SQG: ");
        pw.println("<br>");
        pw.println("Time taken to Validate the 	TestCases : "+(time2-time1));

    }

    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

    private void testCase103()
    {
		try
        {
            Properties props = new	Properties();
            props.setProperty("adminStatus", "DOWN" );
            String[] colsToView = {"mo2","mo3"};
            Vector v =	 q.getData(colsToView, props,false, false);
            Enumeration en = v.elements();
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE 0103 -> DATA : "+en.nextElement());
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


    private void testCase104()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty("adminStatus", "UP" );
            props.setProperty("specialName", "a*" );
            String[] colsToView = {"mo2","mo3"};
            Vector v =	 q.getData(colsToView, props,false, true);
            Enumeration en = v.elements();
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE 0104 -> DATA : "+en.nextElement());
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


    private void testCase105()
    {
        try
        {	pw.println("TESTCASE 0105");
            Properties props = new	Properties();
            props.setProperty("adminStatus", "UP" );
            props.setProperty("specialName", "s*" );
            String[] colsToView = {"mo2","mo3"};
            Vector v =	 q.getData(colsToView, props, true,false);
            Enumeration en = v.elements();
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE 0105 -> DATA : "+en.nextElement());
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

    private void testCase106()
    {
        try
        {	pw.println("TESTCASE 0106");
            Properties props = new	Properties();
            props.setProperty("adminStatus", "DOWN" );

            props.setProperty("name", "s*");

            String[] colsToView = {"mo2","mo3"};
            pw.println("new Query-");
            Vector v =	 q.getData(colsToView, props,true, true);
            Enumeration en = v.elements();
            String sql =q.getSqlString(colsToView,props,true);
            pw.println("Query-" +sql);
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE 0106******* -> DATA : "+en.nextElement());
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


    private void testCase107()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty("adminStatus", "UP" );
            props.setProperty("mo3", "3");
            String[] colsToView = {"mo2","mo3"};
            Vector v =	 q.getData(colsToView, props,false, false);
            Enumeration en = v.elements();
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE 0107 -> DATA : "+en.nextElement());
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

    private void testCase108()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty("adminStatus", "UP" );
            props.setProperty("mo3", "2");

            props.setProperty("mo2","3");



            String[] colsToView = {"mo2","mo3"};
            Vector v =	 q.getData(colsToView, props,false, false);
            Enumeration en = v.elements();
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE 0108 -> DATA : "+en.nextElement());
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
    private void testCase109()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty("specialName", "s*" );
            String[] colsToView = {"adminStatus"};
            Vector v =	 q.getData(colsToView, props,false, false);
            Enumeration en = v.elements();
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE 0109 -> DATA : "+en.nextElement());
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
    private void testCase110()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty("specialName", "S*" );
            String[] colsToView = {"adminStatus","mo3","mo2"};
            Vector v =	 q.getData(colsToView, props,false, true);
            Enumeration en = v.elements();
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE 110 -> DATA : "+en.nextElement());
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


    private void testCase111()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty("specialStatus", "2" );
            String[] colsToView = {"adminStatus","mo3","mo2"};
            Vector v =	 q.getData(colsToView, props,false,false);
            Enumeration en = v.elements();
            String sql =q.getSqlString(colsToView,props);
            pw.println("Test Case 111 Query-" +sql);
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE 111 -> DATA : "+en.nextElement());
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


    private void testCase112()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty("specialStatus", "2" );
            props.setProperty("specialName", "s*" );
            String[] colsToView = {"mo3","mo2"};
            Vector v =	 q.getData(colsToView, props,false,true);
            Enumeration en = v.elements();
            String sql =q.getSqlString(colsToView,props);
            pw.println("TESTCASE 112 Query-" +sql);
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE 112 -> DATA : "+en.nextElement());
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
    private void testCase113()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty("specialStatus", "2" );
            props.setProperty("specialName", "S*" );
            String[] colsToView = {"mo3","mo2"};
            Vector v =	 q.getData(colsToView, props,true,false);
            Enumeration en = v.elements();
            String sql =q.getSqlString(colsToView,props);
            pw.println("TESTCASE 113 Query-" +sql);
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE 113 -> DATA : "+en.nextElement());
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
    private void testCase114()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty("specialStatus", "2" );

            props.setProperty("specialName", "a*" );



            String[] colsToView = {"mo3","mo2"};
            Vector v =	 q.getData(colsToView, props,true,true);
            Enumeration en = v.elements();
            String sql =q.getSqlString(colsToView,props);
            pw.println("TESTCASE 114 Query-" +sql);
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE 114 -> DATA : "+en.nextElement());
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
    private void testCase115()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty("adminStatus", "DOWN" );
            props.setProperty("mo3", "2");
            String[] colsToView = {"mo3","mo2"};
            Vector v =	 q.getData(colsToView, props,false,false);
            Enumeration en = v.elements();
            String sql =q.getSqlString(colsToView,props);
            pw.println("TESTCASE 115 Query-" +sql);
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE 115 -> DATA : "+en.nextElement());
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
    private void testCase116()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty("adminStatus", "UP" );
            props.setProperty("mo3", "3");
            props.setProperty("mo2","1");



            String[] colsToView = {"mo3","mo2"};
            Vector v =	 q.getData(colsToView, props,false,false);
            Enumeration en = v.elements();
            String sql =q.getSqlString(colsToView,props);
            pw.println("TESTCASE 116 Query-" +sql);
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE 116 -> DATA : "+en.nextElement());
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

    private void testCase117()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty("name", "g*" );
            String[] colsToView = {"specialStatus"};
            Vector v =	 q.getData(colsToView, props,false,false);
            Enumeration en = v.elements();
            String sql =q.getSqlString(colsToView,props);
            pw.println("TESTCASE 117 Query-" +sql);
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE 117 -> DATA : "+en.nextElement());
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
    private void testCase118()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty("name", "s*" );
            String[] colsToView = {"specialStatus","mo3","mo2"};
            Vector v =	 q.getData(colsToView, props,false,false);
            Enumeration en = v.elements();
            String sql =q.getSqlString(colsToView,props);
            pw.println("TESTCASE 118 Query-" +sql);
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE 118-> DATA : "+en.nextElement());
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
    private void testCase119()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty("specialName", "s*" );
            props.setProperty("specialStatus", "2" );
            String[] colsToView = {"mo3","mo2"};
            Vector v =	 q.getData(colsToView, props,false,false);
            Enumeration en = v.elements();
            String sql =q.getSqlString(colsToView,props);
            pw.println("TESTCASE 119 Query-" +sql);
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE 119 -> DATA : "+en.nextElement());
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

    private void testCase120()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty("specialName", "a*" );
            props.setProperty("specialStatus", "3" );
            String[] colsToView = {"mo2","mo3","specialStatus","specialName" };
            Vector v =	 q.getData(colsToView, props,false,false);
            Enumeration en = v.elements();
            String sql =q.getSqlString(colsToView,props);
            pw.println("TESTCASE 120 Query-" +sql);
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE 120 -> DATA : "+en.nextElement());
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


    private void testCase121()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty("specialName", "s*" );
            props.setProperty("specialStatus", "2" );
            String[] colsToView = {"mo2","mo3","specialStatus","specialName"};
            Vector v =	 q.getData(colsToView, props,false,true);
            Enumeration en = v.elements();
            String sql =q.getSqlString(colsToView,props);
            pw.println("TESTCASE 121 Query-" +sql);
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE 121** -> DATA : "+en.nextElement());
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

    private void testCase122()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty("name", "r*" );
            String[] colsToView = {"mo2","mo3","specialStatus","specialName"};
            Vector v =	 q.getData(colsToView, props,false,false);
            Enumeration en = v.elements();
            String sql =q.getSqlString(colsToView,props);
            pw.println("TESTCASE 122 Query-" +sql);
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE 122 -> DATA : "+en.nextElement());
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
