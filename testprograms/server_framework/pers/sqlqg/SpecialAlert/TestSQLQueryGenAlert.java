

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

public class TestSQLQueryGenAlert extends GenericServlet
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
        q = new SQLQueryGenerator(conn, "AlertDB", "Alert", "ALERTUSERPROPS", relImpl);
        pw.println("Created an instance of the SQL Q G : "+q);
        pw.println("<br><br>");
    }


    private void test()
    {
        pw.println("Starting to test the TestCases!!!!");
        pw.println("<br><br>");
        long time1 = System.currentTimeMillis();
        testCase143();
        testCase144();
        testCase145();
        testCase146();
        testCase147(); //148th Test case is Deprecated
        testCase149();
        testCase150();
        testCase151();
        testCase152();
        testCase153();
        testCase154();
        testCase155();
         //156th Test case is Deprecated
        testCase157();
        testCase158();
        testCase159();
        testCase160();
        testCase161();
        testCase162();
        long time2 = System.currentTimeMillis();
        pw.println("COMPLETED ALL  TESTCASES IN THE TESTPLAN SF-PERS-SQG: ");
        pw.println("<br>");
        pw.println("Time taken to Validate the 	TestCases : "+(time2-time1));

    }


    private void testCase143()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty("entity", "*1" );
            String[] colsToView = {"specialAlertSeverity1" ,"specialAlertSeverity","entity"};
            Vector v =	 q.getData(colsToView, props,false,false);
            Enumeration en = v.elements();
            String sql =q.getSqlString(colsToView,props);
            pw.println("testCase143 Query-" +sql);
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE 143-> DATA : "+en.nextElement());
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


    private void testCase144()
    {
        try
        {
            Properties props = new	Properties();
         	props.setProperty("entity", "*1" );
            String[] colsToView = {"specialAlertSeverity1" ,"specialAlertSeverity","entity"};
            Vector v =	 q.getData(colsToView, props,false,true);
            Enumeration en = v.elements();
            String sql =q.getSqlString(colsToView,props);
            pw.println("testCase144 Query-" +sql);
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE 144-> DATA : "+en.nextElement());
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

    private void testCase145()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty("entity", "duplexnine" );


            String[] colsToView = {"entity" ,"specialAlertSeverity","entity"};
            Vector v =	 q.getData(colsToView, props,true,false);
            Enumeration en = v.elements();
            String sql =q.getSqlString(colsToView,props);
            pw.println("testCase145 Query-" +sql);
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE 145-> DATA : "+en.nextElement());
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

    private void testCase146()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty("entity", "duplexnine" );


            String[] colsToView = {"entity" ,"specialAlertSeverity","entity"};
            Vector v =	 q.getData(colsToView, props,true,true);
            Enumeration en = v.elements();
            String sql =q.getSqlString(colsToView,props);
            pw.println("testCase146 Query-" +sql);
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE 146-> DATA : "+en.nextElement());
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


 private void testCase147()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty("entity", "duplexnine" );
            String[] colsToView = {"entity" ,"specialAlertSeverity","entity"};

            Vector v =	 q.getData(colsToView, props,false,false);
            Enumeration en = v.elements();
            String sql =q.getSqlString(colsToView,props);
            pw.println("testCase147 Query-" +sql);
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE 147-> DATA : "+en.nextElement());
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


    private void testCase149()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty("name", "*" );
            ;


            String[] colsToView = {"entity" ,"specialAlertSeverity","entity","priority"};


            Vector v =	 q.getData(colsToView, props,false,false);
            Enumeration en = v.elements();
            String sql =q.getSqlString(colsToView,props);
            pw.println("testCase149 Query-" +sql);
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE 149-> DATA : "+en.nextElement());
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


private void testCase150()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty("entity", "*" );
            String[] colsToView = {"entity" ,"specialAlertSeverity","entity","priority"};
            Vector v =	 q.getData(colsToView, props,false,false);
            Enumeration en = v.elements();
            String sql =q.getSqlString(colsToView,props);
            pw.println("testCase150 Query-" +sql);
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE 150-> DATA : "+en.nextElement());
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


    private void testCase151()
    {
        try
        {
            Properties props = new Properties();
			props.setProperty("specialAlertSeverity1","5");
			String[] colsToView = {"specialAlertSeverity1","entity"};
            Vector v =	 q.getData(colsToView, props,false,false);
            Enumeration en = v.elements();
            String sql =q.getSqlString(colsToView,props);
            pw.println("testCase151 Query-" +sql);
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE 151-> DATA : "+en.nextElement());
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

    private void testCase152()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty("specialAlertSeverity1","5");
            String[] colsToView = {"entity" ,"specialAlertSeverity1"};
            Vector v =	 q.getData(colsToView, props,false,true);
            Enumeration en = v.elements();
            String sql =q.getSqlString(colsToView,props);
            pw.println("testCase152 Query-" +sql);
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE 152-> DATA : "+en.nextElement());
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

    private void testCase153()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty("specialAlertSeverity1","5");



            String[] colsToView = {"entity" ,"specialAlertSeverity1"};
            Vector v =	 q.getData(colsToView, props,true,false);
            Enumeration en = v.elements();
            String sql =q.getSqlString(colsToView,props);
            pw.println("testCase153 Query-" +sql);
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE 153-> DATA : "+en.nextElement());
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

    private void testCase154()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty("specialAlertSeverity1","5");
            String[] colsToView = {"entity" ,"specialAlertSeverity1"};
            Vector v =	 q.getData(colsToView, props,true,true);
            Enumeration en = v.elements();
            String sql =q.getSqlString(colsToView,props);
            pw.println("testCase154 Query-" +sql);
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE 154-> DATA : "+en.nextElement());
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

    private void testCase155()
	    {
	        try
	        {//pw.println(" into 155th Testcase");
	            Properties props = new	Properties();
	            props.setProperty("specialAlertSeverity1","5");
	            props.setProperty("entity","aas2");
	            String[] colsToView = {"entity" ,"specialAlertSeverity1"};
	            Vector v =	 q.getData(colsToView, props,true,true);
	            Enumeration en = v.elements();
	            String sql =q.getSqlString(colsToView,props);
	            pw.println("testCase155 Query-" +sql);
	            while(en.hasMoreElements())
	            {
	                pw.println("TESTCASE 155-> DATA : "+en.nextElement());
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

    private void testCase157()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty("entity", "*" );
            String[] colsToView = {"entity" ,"specialAlertSeverity1","priority"};
            Vector v =	 q.getData(colsToView, props,false,false);
            Enumeration en = v.elements();
            String sql =q.getSqlString(colsToView,props);
            pw.println("testCase157 Query-" +sql);
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE 157-> DATA : "+en.nextElement());
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


    private void testCase158()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty("entity", "*" );
            String[] colsToView = {"entity" ,"specialAlertSeverity1","priority"};
            Vector v =	 q.getData(colsToView, props,false,false);
            Enumeration en = v.elements();
            String sql =q.getSqlString(colsToView,props);
            pw.println("testCase158 Query-" +sql);
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE 158-> DATA : "+en.nextElement());
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

private void testCase159()
    {
        try
        {
            Properties props = new Properties();
			props.setProperty("specialAlertSeverity1","5");
            String[] colsToView = {"specialAlertSeverity1","specialAlertSource","priority"};
            Vector v =	 q.getData(colsToView, props,false,false);
            Enumeration en = v.elements();
            String sql =q.getSqlString(colsToView,props);
            pw.println("testCase159 Query-" +sql);
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE 159-> DATA : "+en.nextElement());
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



private void testCase160()
    {
        try
        {
            Properties props = new Properties();
			props.setProperty("specialAlertSeverity1","5");
			props.setProperty("specialAlertSource","rainbow*") ;
            String[] colsToView = {"specialAlertSeverity1","specialAlertSource"};
            Vector v =	 q.getData(colsToView, props,false,false);
            Enumeration en = v.elements();
            String sql =q.getSqlString(colsToView,props);
            pw.println("testCase160 Query-" +sql);
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE 160-> DATA : "+en.nextElement());
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

private void testCase161()
    {
        try
        {
            Properties props = new Properties();
			props.setProperty("specialAlertSeverity1","5");
			String[] colsToView = {"specialAlertSeverity1","specialAlertSource"};
            Vector v =	 q.getData(colsToView, props,false,true);
            Enumeration en = v.elements();
            String sql =q.getSqlString(colsToView,props);
            pw.println("testCase161 Query-" +sql);
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE 161-> DATA : "+en.nextElement());
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

private void testCase162()
    {
        try
        {
            Properties props = new Properties();
			props.setProperty("entity","*");
			String[] colsToView = {"specialAlertSeverity1","specialAlertSource"};
            Vector v =	 q.getData(colsToView, props,false,false);
            Enumeration en = v.elements();
            String sql =q.getSqlString(colsToView,props);
            pw.println("testCase162 Query-" +sql);
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE 162-> DATA : "+en.nextElement());
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
