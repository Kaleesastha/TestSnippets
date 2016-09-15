
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

public class TestSQLQueryGen extends GenericServlet
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
		//q = new SQLQueryGenerator(conn, "EventDB", "Event", "EVENTUSERPROPS", relImpl);
        //q = new SQLQueryGenerator(conn, "AlertDB", "Alert", "ALERTUSERPROPS", relImpl);

        pw.println("Created an instance of the SQL Q G : "+q);
        pw.println("<br><br>");
    }


    private void test()
    {
        pw.println("Starting to test the TestCases!!!!");
        pw.println("<br><br>");
        long time1 = System.currentTimeMillis();


		testCase001();
        testCase002();
        testCase003();
        testCase004();
        testCase005();
        testCase006();
        testCase007();
        testCase008();
        testCase009();
        testCase010();
        testCase011();
        testCase012();
        testCase013();
        testCase014();
        testCase015();
        testCase016();
        testCase017();
        testCase018();
        testCase019();
        testCase020();
        testCase021();
        testCase022();
        testCase023();
        testCase024();
        testCase025();
        testCase026();
        testCase027();
        testCase028();
        testCase029();
        testCase030();
        testCase031();
        testCase032();
        testCase033();
        testCase034();
        testCase035();
        testCase036();
        testCase037();
        testCase038();
        testCase039();
        testCase040();
        testCase041();
        testCase042();
        testCase043();
        testCase044();
        testCase045();
        testCase046();
        testCase047();
        testCase048();
        testCase049();
        testCase050();
        testCase051();
        testCase052();
        testCase053();
        testCase054();
        testCase055();
        testCase056();
        testCase057();
        testCase058();
        testCase059();
        testCase059_1();
        testCase059_2();
        testCase059_3();
        testCase059_4();
        testCase059_5();
        testCase059_6();
        testCase059_7();
        testCase059_8();
        testCase059_9();
        testCase059_10();
        testCase059_11();
        testCase059_12();
        testCase059_13();
        testCase059_14();
        testCase059_15();
        testCase059_16();
        testCase059_17();
        testCase059_18();
        testCase060();
        testCase061();
        testCase062();
        testCase063();
        testCase064();
        testCase065();
        testCase066();
        testCase067();
        testCase068();
        testCase069();
        testCase069_1();
        testCase069_2();
        testCase069_3();
        testCase069_4();
        testCase069_5();
        testCase069_6();
        testCase069_7();
        testCase070();
        testCase071();
        testCase071_1();
        testCase071_2();
        testCase071_3();
        testCase072();
        testCase073();
        testCase074();
        testCase075();
        testCase076();
        testCase077();
        testCase078();
        testCase079();
        testCase080();
        testCase081();
        testCase082();
        testCase083();
        testCase084();
        testCase085();
        testCase086();
        testCase087();
        testCase088();
        testCase089();
        testCase090();
        testCase091();
        testCase092();
        testCase093();
        testCase094();
        testCase095();
        testCase096();
        testCase097();
        testCase098();
        testcase99();
        testCase100();
        testCase101();
        testCase102();
        testCase173();
        testCase174_1();
        testCase174_2();
        testCase175();
        testCase176();
        testCase177();
        testCase178();
        testCase179();
        testCase180();
        testCase181();
        testCase182();
        testCase183();

        long time2 = System.currentTimeMillis();
        pw.println("COMPLETED ALL  TESTCASES IN THE TESTPLAN SF-PERS-SQG: ");
        pw.println("<br>");
        pw.println("Time taken to Validate the 	TestCases : "+(time2-time1));

    }



    private void testCase001()
    {

		/*
		  getDatabaseProduct()
		  Returns 1 : If the Server is running with Oracle database
                  2 : If the Server is running with MySQL database
                  3 : If the Server is running with MSSQL database
				  4 : If the Server is running with HSQL database
                  5 : If the Server is running with SYBASE database
                  6 : If the Server is running with SOLID database
                  7 : If the Server is running with TIMESTEN database
                  0 : If the Server is running with any other database not supported by WebNMS
		*/

        try
        {
            int i = q.getDatabaseProduct();

            if(i==2)
            {
                pw.println("<br><b><font color=\"red\">TESTCASE ID :001 -> PASSED </font><br> Database Product ID : " + i + " </b>");
				pw.println("<br><br>");
            }
            else
            {
                pw.println("<font color=\"red\">TESTCASE001 -> FAILED(i = "+i+") </font>");
				pw.println("<br><br>");
            }
        }
        catch(Exception u)
        {
            u.printStackTrace();
        }
    }


    private void testCase002()
    {
        try
        {
            String[] colsToView = {"name"};
            Vector tablesToQuery = new Vector();
            tablesToQuery.addElement("TopoObject");
            String sql = q.getSqlString(colsToView,null) ;
            pw.println("<b><font color=\"red\">TESTCASE 002 SQL String is: <br></font>" +sql + " </b>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }

    private void testCase003()
    {
        try
        {
            String[] colsToView = {"name","status"};
            String sql = q.getSqlString(colsToView,null) ;
            pw.println("<b><font color=\"red\">TESTCASE 003 SQL String is: </font><br>"+sql + " </b>");
            pw.println("<br><br>");
		}
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }

    private void testCase004()
    {
        try
        {
            String[] colsToView = {"ipAddress"};
            String sql = q.getSqlString(colsToView,null) ;
            pw.println("<b><font color=\"red\">TESTCASE 004 SQL String is: </font><br>"+sql+ " </b>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }

    private void testCase005()
    {
        try
        {
            String[] colsToView = {"status","ipAddress"};
            String sql = q.getSqlString(colsToView,null) ;
            pw.println("<b><font color=\"red\">TESTCASE 005 SQL String is: </font><br>  "+sql +" </b>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }

    private void testCase006()
    {
        try
        {
            String colsToView[] = {"userprop"};
            String sql = q.getSqlString(colsToView,null);
            pw.println("<b><font color=\"red\">TESTCASE 006 SQL String is: </font><br> "+sql +" </b>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }

    private void testCase007()
    {
        try
        {
            String colsToView[] = {"displayName","userprops"};
            String sql = q.getSqlString(colsToView,null);
            pw.println("<b><font color=\"red\">TESTCASE 007 SQL String is:  </font><br> "+sql +" </b>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }


    private void testCase008()
    {
        try
        {
            String colsToView[] = {"userprop","ipAddress"};
            String sql = q.getSqlString(colsToView,null);
            pw.println("<b><font color=\"red\">TESTCASE 008 SQL String is: </font><br> "+sql +" </b>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }

    private void testCase009()
    {
        try
        {
            String colsToView[] = {"userprop","ipAddress","status"};
            String sql = q.getSqlString(colsToView,null);
            pw.println("<b><font color=\"red\">TESTCASE 009 SQL String is: </font><br>"+sql +" </b>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }


    private void testCase010()
    {
        try
        {
            String[] colsToView = {"name"};
            Properties base = new Properties();
            base.put("name","IF*");
            String sql = q.getSqlString(colsToView,base) ;
            pw.println("<b><font color=\"red\">TESTCASE 010 SQL String is: </font><br>"+sql +" </b>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }

    private void testCase011()
    {
        try
        {
            String[] colsToView = {"status"};
            Properties base = new Properties();
            base.put("name","IF*");
            String sql = q.getSqlString(colsToView,base) ;
            pw.println("<b><font color=\"red\">TESTCASE 011 SQL String is: </font><br>"+sql +" </b>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }

    private void testCase012()
    {
        try
        {
            String[] colsToView = {"ipAddress"};
            Properties base = new Properties();
            base.put("name","IF*");
            String sql = q.getSqlString(colsToView,base) ;
            pw.println("<b><font color=\"red\">TESTCASE 012 SQL String is:  </font><br>"+sql +" </b>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }


    private void testCase013()
    {
        try
        {
            String[] colsToView = {"status","ipAddress"};
            Properties base = new Properties();
            base.put("name","IF*");
            String sql = q.getSqlString(colsToView,base);
            pw.println("<b><font color=\"red\">TESTCASE 013 SQL String is:  </font><br>"+sql +" </b>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }


    private void testCase014()
    {
        try
        {
            String[] colsToView = {"userprop"};
            Properties base = new Properties();
            base.put("name","IF*");
            String sql = q.getSqlString(colsToView,base) ;
            pw.println("<b><font color=\"red\">TESTCASE 014 SQL String is: </font><br>"+sql +" </b>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }

    private void testCase015()
    {
        try
        {
            String[] colsToView = {"userprop","displayName"};
            Properties base = new Properties();
            base.put("name","IF*");
            String sql = q.getSqlString(colsToView,base) ;
            pw.println("<b><font color=\"red\">TESTCASE 015 SQL String is: </font><br>"+sql +" </b>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }

    private void testCase016()
    {
        try
        {
            String[] colsToView = {"ipAddress","userprop"};
            Properties base = new Properties();
            base.put("name","IF*");
            String sql = q.getSqlString(colsToView,base) ;
            pw.println("<b><font color=\"red\">TESTCASE 016 SQL String is: </font><br>"+sql+" </b>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }

    private void testCase017()
    {
        try
        {
            String[] colsToView = {"ipAddress","userprop","status"};
            Properties base = new Properties();
            base.put("name","IF*");
            String sql = q.getSqlString(colsToView,base);
            pw.println("<b><font color=\"red\">TESTCASE 017 SQL String is: </font><br>"+sql+" </b>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }

    private void testCase018()
    {
        try
        {
            String[] colsToView = {"name"};
            Properties base = new Properties();
            base.put("managed","false");
            String sql = q.getSqlString(colsToView,base) ;
            pw.println("<b><font color=\"red\">TESTCASE 018 SQL String is: </font><br>"+sql+" </b>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }

    private void testCase019()
    {
        try
        {
            String[] colsToView = {"status"};
            Properties base = new Properties();
            base.put("managed","false");
            String sql = q.getSqlString(colsToView,base) ;
            pw.println("<b><font color=\"red\">TESTCASE 019 SQL String is: </font><br>"+sql+" </b>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }

    private void testCase020()
    {
        try
        {
            String[] colsToView = {"ipAddress"};
            Properties base = new Properties();
            base.put("managed","false");
            String sql = q.getSqlString(colsToView,base) ;
            pw.println("<b><font color=\"red\">TESTCASE 020 SQL String is: </font><br>"+sql+" </b>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }

    private void testCase021()
    {
        try
        {
            String[] colsToView = {"ipAddress","status"};
            Properties base = new Properties();
            base.put("managed","false");
            String sql = q.getSqlString(colsToView,base);
            pw.println("<b><font color=\"red\">TESTCASE 021 SQL String is: </font><br>"+sql+" </b>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }

    private void testCase022()
    {
        try
        {
            String[] colsToView = {"userprop"};
            Properties base = new Properties();
            base.put("managed","false");
            String sql = q.getSqlString(colsToView,base) ;
            pw.println("<b><font color=\"red\">TESTCASE 022 SQL String is: </font><br>"+sql+" </b>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }

    private void testCase023()
    {
        try
        {
            String[] colsToView = {"userprop","displayName"};
            Properties base = new Properties();
            base.put("managed","false");
            String sql = q.getSqlString(colsToView,base) ;
            pw.println("<b><font color=\"red\">TESTCASE 023 SQL String is: </font><br>"+sql+" </b>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }


    private void testCase024()
    {
        try
        {
            String[] colsToView = {"userprop","ipAddress"};
            Properties base = new Properties();
            base.put("managed","false");
            String sql = q.getSqlString(colsToView,base) ;
            pw.println("<b><font color=\"red\">TESTCASE 024 SQL String is: </font><br>"+sql+" </b>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }


    private void testCase025()
    {
        try
        {
            String[] colsToView = {"userprop","ipAddress","status","numPorts"};
            Properties base = new Properties();
            base.put("managed","false");
            String sql = q.getSqlString(colsToView,base) ;
            pw.println("<b><font color=\"red\">TESTCASE 025 SQL String is: </font><br>"+sql+" </b>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }

    private void testCase026()
    {
        try
        {
            String[] colsToView = {"name"};
            Properties base = new Properties();
            base.put("isSNMP", "false" );
            String sql = q.getSqlString(colsToView,base) ;
            pw.println("<b><font color=\"red\">TESTCASE 026 SQL String is: </font><br>"+sql+" </b>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }

    private void testCase027()
    {
        try
        {
            String[] colsToView = {"status"};
            Properties base = new Properties();
            base.put("isSNMP", "false" );
            String sql = q.getSqlString(colsToView,base) ;
            pw.println("<b><font color=\"red\">TESTCASE 027 SQL String is: </font><br>"+sql+" </b>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }

    private void testCase028()
    {
        try
        {
            String[] colsToView = {"ipAddress"};
            Properties base = new Properties();
            base.put("isSNMP", "false" );
            String sql = q.getSqlString(colsToView,base) ;
            pw.println("<b><font color=\"red\">TESTCASE 028 SQL String is: </font><br>"+sql+" </b>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }


    private void testCase029()
    {

        try
        {
            String[] colsToView = {"status","ipAddress"};
            Properties base = new Properties();
            base.put("isSNMP", "false" );
            String sql = q.getSqlString(colsToView,base) ;
            pw.println("<b><font color=\"red\">TESTCASE 029 SQL String is: </font><br>"+sql+" </b>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }



    private void testCase030()
    {
        try
        {
            String[] colsToView = {"userprop"};
            Properties base = new Properties();
            base.put("isSNMP", "false" );
            String sql = q.getSqlString(colsToView,base) ;
            pw.println("<b><font color=\"red\">TESTCASE 030 SQL String is: </font><br>"+sql+" </b>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }

    private void testCase031()
    {
        try
        {
            String[] colsToView = {"displayName","userprop"};
            Properties base = new Properties();
            base.put("isSNMP", "false" );
            String sql = q.getSqlString(colsToView,base) ;
            pw.println("<b><font color=\"red\">TESTCASE **031* SQL String is: </font><br>"+sql +" </b>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }

    private void testCase032()
    {
        try
        {
            String[] colsToView = {"userprop","ipAddress"};
            Properties base = new Properties();
            base.put("isSNMP", "false" );
            String sql = q.getSqlString(colsToView,base) ;
            pw.println("<b><font color=\"red\">TESTCASE 032 SQL String is: </font><br>"+sql+" </b>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }

    private void testCase033()
    {
        try
        {
            String[] colsToView = {"status","userprop","ipAddress"};
            Properties base = new Properties();
            base.put("isSNMP", "false" );
            String sql = q.getSqlString(colsToView,base) ;
            pw.println("<b><font color=\"red\">TESTCASE 033 SQL String is: </font><br>"+sql+" </b>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }

    private void testCase034()
    {
        try
        {
            String[] colsToView = {"name"};
            Properties base = new Properties();
            base.put("managed","true");
			base.put("isSNMP", "false" );
            String sql = q.getSqlString(colsToView,base) ;
            pw.println("<b><font color=\"red\">TESTCASE 034 SQL String is: </font><br>"+sql+" </b>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }

    private void testCase035()
    {
        try
        {
            String[] colsToView = {"status"};
            Properties base = new Properties();
            base.put("managed","true");
			base.put("isSNMP", "false" );
            String sql = q.getSqlString(colsToView,base) ;
            pw.println("<b><font color=\"red\">TESTCASE 035 SQL String is: </font><br>"+sql+" </b>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }

    private void testCase036()
    {
        try
        {
            String[] colsToView = {"ipAddress"};
            Properties base = new Properties();
            base.put("managed","true");
			base.put("isSNMP", "false" );
            String sql = q.getSqlString(colsToView,base) ;
            pw.println("<b><font color=\"red\">TESTCASE 036 SQL String is: </font><br>"+sql+" </b>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }


    private void testCase037()
    {
        try
        {
            String[] colsToView = {"status","ipAddress"};
            Properties base = new Properties();
            base.put("managed","true");
			base.put("isSNMP", "false" );
            String sql = q.getSqlString(colsToView,base) ;
            pw.println("<b><font color=\"red\">TESTCASE 037 SQL String is: </font><br>"+sql+" </b>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }

    private void testCase038()
    {
        try
        {
            String[] colsToView = {"userprop"};
            Properties base = new Properties();
            base.put("managed","true");
			base.put("isSNMP", "false" );
            String sql = q.getSqlString(colsToView,base) ;
            pw.println("<b><font color=\"red\">TESTCASE 038 SQL String is: </font><br>"+sql+" </b>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }

    private void testCase039()
    {
        try
        {
            String[] colsToView = {"name","userprop"};
            Properties base = new Properties();
            base.put("managed","true");
			base.put("isSNMP", "false" );
            String sql = q.getSqlString(colsToView,base) ;
            pw.println("<b><font color=\"red\">TESTCASE 039 SQL String is: </font><br>"+sql+" </b>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }

    private void testCase040()
    {
        try
        {
            String[] colsToView = {"userprop","ipAddress" };
            Properties base = new Properties();
            base.put("managed","true");
            base.put("isSNMP", "false" );
            String sql = q.getSqlString(colsToView,base) ;
            pw.println("<b><font color=\"red\">TESTCASE 040 SQL String is: </font><br>"+sql+" </b>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }


    private void testCase041()
    {
        try
        {
            String[] colsToView = {"status","userprop","ipAddress"};
            Properties base = new Properties();
            base.put("managed","true");
            base.put("isSNMP", "false" );
            String sql = q.getSqlString(colsToView,base) ;
            pw.println("<b><font color=\"red\">TESTCASE 041 SQL String is: </font><br>"+sql+" </b>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }


    private void testCase042()
    {
        try
        {
            String[] colsToView = {"name"};
            Properties base = new Properties();
            //base.put("consoleLightString","R*");
			base.put("userprop","15");
            String sql = q.getSqlString(colsToView,base) ;
            pw.println("<b><font color=\"red\">TESTCASE 042 SQL String is: </font><br>"+sql+" </b>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }

    private void testCase043()
    {
        try
        {
            String[] colsToView = {"status"};
            Properties base = new Properties();
            //base.put("consoleLightString","R*");
			base.put("userprop","15");
            String sql = q.getSqlString(colsToView,base) ;
            pw.println("<b><font color=\"red\">TESTCASE 043 SQL String is: </font><br>"+sql+" </b>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }

    private void testCase044()
    {
        try
        {
            String[] colsToView = {"ipAddress"};
            Properties base = new Properties();
			base.put("userprop","15");
			// base.put("consoleLightString","R*");
            String sql = q.getSqlString(colsToView,base);
            pw.println("<b><font color=\"red\">TESTCASE 044 SQL String is: </font><br>"+sql+" </b>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }


    private void testCase045()
    {
        try
        {
            String[] colsToView = {"ipAddress","status"};
            Properties base = new Properties();
			base.put("userprop","15");
            //base.put("consoleLightString","R*");
            String sql = q.getSqlString(colsToView,base);
            pw.println("<b><font color=\"red\">TESTCASE 045 SQL String is: </font><br>"+sql+" </b>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }

    private void testCase046()
    {
        try
        {
            String[] colsToView = {"userprop"};
            Properties base = new Properties();
			base.put("userprop","15");
            //base.put("consoleLightString","R*");
            String sql = q.getSqlString(colsToView,base);
            pw.println("<b><font color=\"red\">TESTCASE 046 SQL String is: </font><br>"+sql+" </b>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }

    private void testCase047()
    {
        try
        {
            String[] colsToView = {"displayName","userprop"};
            Properties base = new Properties();
			base.put("userprop","15");
            //base.put("consoleLightString","R*");
            String sql = q.getSqlString(colsToView,base);
            pw.println("<b><font color=\"red\">TESTCASE 047 SQL String is: </font><br>"+sql+" </b>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }


    private void testCase048()
    {
        try
        {
            String[] colsToView = {"userprop","ipAddress"};
            Properties base = new Properties();
			base.put("userprop","15");
			// base.put("consoleLightString","R*");
            String sql = q.getSqlString(colsToView,base);
            pw.println("<b><font color=\"red\">TESTCASE 048 SQL String is: </font><br>"+sql+" </b>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }

    private void testCase049()
    {
        try
        {
            String[] colsToView = {"status","userprop","ipAddress"};
            Properties base = new Properties();
			base.put("userprop","15");
            //base.put("consoleLightString","R*");
            String sql = q.getSqlString(colsToView,base);
            pw.println("<b><font color=\"red\">TESTCASE 049 SQL String is: </font><br>"+sql+" </b>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }


    private void testCase050()
    {
        try
        {
            String[] colsToView = {"name"};
            Properties base = new Properties();
            base.put("name", "IF*");
            base.put("userprop","15");
            String sql = q.getSqlString(colsToView,base) ;
            pw.println("<b><font color=\"red\">TESTCASE 050 SQL String is: </font><br>"+sql+" </b>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }

    private void testCase051()
    {
        try
        {
            String[] colsToView = {"status"};
            Properties base = new Properties();
			base.put("name", "IF*");
            base.put("userprop","15");
            String sql = q.getSqlString(colsToView,base) ;
            pw.println("<b><font color=\"red\">TESTCASE 051 SQL String is:  </font><br>"+sql+" </b>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }

    private void testCase052()
    {
        try
        {
            String[] colsToView = {"ipAddress"};
            Properties base = new Properties();
			base.put("name", "IF*");
            base.put("userprop","15");
            String sql = q.getSqlString(colsToView,base) ;
            pw.println("<b><font color=\"red\">TESTCASE 052 SQL String is:  </font><br>"+sql+" </b>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }

    private void testCase053()
    {
        try
        {
            String[] colsToView = {"ipAddress","status"};
            Properties base = new Properties();
			base.put("name", "IF*");
            base.put("userprop","15");
            String sql = q.getSqlString(colsToView,base) ;
            pw.println("<b><font color=\"red\">TESTCASE 053 SQL String is:  </font><br>"+sql+" </b>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }

    private void testCase054()
    {
        try
        {
            String[] colsToView = {"userprop"};
            Properties base = new Properties();
			base.put("name", "IF*");
            base.put("userprop","15");
            String sql = q.getSqlString(colsToView,base) ;
            pw.println("<b><font color=\"red\">TESTCASE 054 SQL String is:  </font><br>"+sql+" </b>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }

    private void testCase055()
    {
        try
        {
            String[] colsToView = {"userprop","displayName"};
            Properties base = new Properties();
            base.put("displayName","IF*");
			base.put("userprop","15");
            String sql = q.getSqlString(colsToView,base) ;
            pw.println("<b><font color=\"red\">TESTCASE 055 SQL String is:  </font><br>"+sql+" </b>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }

    private void testCase056()
    {
        try
        {
            String[] colsToView = {"userprop","ipAddress"};
            Properties base = new Properties();
            base.put("name", "IF*");
			base.put("userprop","15");
            String sql = q.getSqlString(colsToView,base) ;
            pw.println("<b><font color=\"red\">TESTCASE 056 SQL String is:  </font><br>"+sql+" </b>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }


    private void testCase057()
    {
        try
        {
            String[] colsToView = {"status","userprop","ipAddress"};
            Properties base = new Properties();
            base.put("name", "IF*");
			base.put("userprop","15");
            String sql = q.getSqlString(colsToView,base) ;
            pw.println("<b><font color=\"red\">TESTCASE 057 SQL String is: </font><br>"+sql+" </b>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }

    private void testCase058()
    {
        try
        {
            String[] colsToView = {"status","isRouter","ipAddress"};
            Properties base = new Properties();
            base.put("name", "IF*");
            base.put("isSNMP", "true");
            String sql = q.getSqlString(colsToView,base,true) ;
            pw.println("<b><font color=\"red\">TESTCASE 058 SQL String is: </font><br>"+sql+" </b>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }

    private void testCase059()
    {
        try
        {
            String[] colsToView = {"status","isRouter"};
            Properties base = new Properties();
            base.put("name", "IF*");
            base.put("isSNMP", "true");
            //base.put("phone","5*");
			base.put("userprop","15");
            String sql = q.getSqlString(colsToView,base,true) ;
            pw.println("<b><font color=\"red\">TESTCASE *059 SQL String is: </font><br>"+sql+" </b>");
            pw.println("<br><br>");
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }

    private void testCase059_1()
    {
        try
        {
            String[] colsToView = {"sysOID","netmask"}  ;
            Properties base = new Properties();
            base.put("type" ,"L*");
            Properties add = new Properties();
            add.put("sysOID","*.3.1*");
            Vector tablesToQuery = new Vector();
            tablesToQuery.addElement("ManagedObject");
            tablesToQuery.addElement("TopoObject");
            tablesToQuery.addElement("SnmpNode");
            String sql = q.getSqlString(colsToView,base,add,tablesToQuery,true) ;
            pw.println("<b><font color=\"red\">TESTCASE 059.1 SQL String is: </font><br>"+sql+" </b>");
            pw.println("<br><br>");
        }
        catch(Exception i)
        {
            i.printStackTrace();
        }
    }

    private void testCase059_2()
    {
        try
        {
            String[] colsToView = {"sysOID","netmask"}  ;
            Properties base = new Properties();
            base.put("type" ,"Linux");
            Properties add = new Properties();
            add.put("isSNMP","true");
            Vector tablesToQuery = new Vector();
            tablesToQuery.addElement("ManagedObject");
            tablesToQuery.addElement("TopoObject");
            tablesToQuery.addElement("SnmpNode");
            String sql = q.getSqlString(colsToView,base,add,tablesToQuery,false) ;
            pw.println("<b><font color=\"red\">TESTCASE 059.2 SQL String is: </font><br>"+sql+" </b>");
            pw.println("<br><br>");
        }
        catch(Exception i)
        {
            i.printStackTrace();
        }
    }

    private void testCase059_3()
    {
        try
        {
            String[] colsToView = {"isSNMP","netmask"}  ;
            Properties base = new Properties();
            base.put("type" ,"Linux");
            Properties add = new Properties();
            add.put("sysOID","*.3.1*");
            Vector tablesToQuery = new Vector();
            tablesToQuery.addElement("ManagedObject");
            tablesToQuery.addElement("TopoObject");
            tablesToQuery.addElement("SnmpNode");
            String sql = q.getSqlString(colsToView,base,add,tablesToQuery,true) ;
            pw.println("<b><font color=\"red\">TESTCASE 059.3 SQL String is: </font><br>"+sql+" </b>");
            pw.println("<br><br>");

        }
        catch(Exception i)
        {
            i.printStackTrace();
        }
    }

    private void testCase059_4()
    {
        try
        {
            String[] colsToView = {"sysOID","netmask"}  ;
            Properties base = new Properties();
            base.put("type" ,"Linux");
            Properties add = new Properties();
            add.put("sysOID","*.3.1*");
            String sql = q.getSqlString(colsToView,base,add,null,true) ;
            pw.println("<b><font color=\"red\">TESTCASE 059.4 SQL String is:  </font><br>"+sql+" </b>");
            pw.println("<br><br>");
        }
        catch(Exception i)
        {
            i.printStackTrace();
        }
    }

    private void testCase059_5()
    {
        try
        {
            String[] colsToView = {"sysOID","IFDESCR"};
            Properties add = new Properties();
            add.put("sysOID","0.3.1*");
            //add.put("up1","*n*");
            Vector tablesToQuery = new Vector();
            tablesToQuery.addElement("ManagedObject");
            tablesToQuery.addElement("TopoObject");
            String sql = q.getSqlString(colsToView,null,add,null,true) ;
            pw.println("<b><font color=\"red\">TESTCASE ID 059.5 SQL String is: </font><br>"+sql+" </b>");
            pw.println("<br><br>");
        }
        catch(Exception i)
        {
            i.printStackTrace();
        }
    }

	/*   private void testCase059_6()
    {
        try
        {
            String[] colsToView = {"sysOID","netmask"}  ;
            Properties add = new Properties();
            add.put("phone","*5*");
            Vector tablesToQuery = new Vector();
            tablesToQuery.addElement("ManagedObject");
            tablesToQuery.addElement("TopoObject");
            String sql = q.getSqlString(colsToView,null,add,tablesToQuery,false);
            pw.println("<b><font color=\"red\">TESTCASE 059_6 SQL String is: </font><br>"+sql+" </b>");
            pw.println("<br><br>");
        }
        catch(Exception i)
        {
            i.printStackTrace();
        }
    }
	*/

    private void testCase059_6()
    {

        try
        {
            String[] colsToView = {"sysOID","netmask"};
            Properties additionalCriteria = new Properties();
            additionalCriteria.put("up1","*n*");
            Vector tablesToQuery = new Vector();
            tablesToQuery.addElement("ManagedObject");
            tablesToQuery.addElement("TopoObject");
            String sql = q.getSqlString(colsToView,null,additionalCriteria,tablesToQuery,false) ;
            pw.println("<b><font color=\"red\">TESTCASE 59.6 SQL String is:  </font><br>"+sql+" </b>");
            pw.println("<br><br>");
        }

        catch(Exception k)
        {
            k.printStackTrace();
        }

    }

    private void testCase059_7()
    {
        try
        {
            String[] colsToView = {"up1","sysOID","netmask"};
            Properties additionalCriteria = new Properties();
			additionalCriteria.put("up1","*n*");
			// additionalCriteria.put("phone","*5*");
            Vector tablesToQuery = new Vector();
            tablesToQuery.addElement("ManagedObject");
            tablesToQuery.addElement("TopoObject");
            String sql = q.getSqlString(colsToView,null,additionalCriteria,tablesToQuery,false) ;
            pw.println("<b><font color=\"red\">TESTCASE 59.7 SQL String is: </font><br>"+sql+" </b>");
            pw.println("<br><br>");
        }

        catch(Exception k)
        {
            k.printStackTrace();
        }

    }


    /*private void testCase059_7()
    {
        try
        {
            String[] colsToView = {"up1","sysOID","netmask"} ;
            Properties add = new Properties();
            add.put("up1","*n*");
            Vector tablesToQuery = new Vector();
            tablesToQuery.addElement("ManagedObject");
            tablesToQuery.addElement("TopoObject");
            String sql = q.getSqlString(colsToView,null,add,tablesToQuery,true);
            pw.println("TESTCASE 059_7 SQL String is: "+sql);
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception i)
        {
            i.printStackTrace();
        }
		}*/

    private void testCase059_8()
    {

		/*
		In this program two rows,
		Use the command:
		insert into topouserprops values('kasturirangan','null','phone','5486');
		insert into topouserprops values('kasturirangan','null','bike','bajaj')
		Note:The name col should match with the main table ( eg. Managedobject table for topo module)
		*/
        try
        {
            String[] colsToView = {"propname","propval","ipAddress","community"} ;
            Properties base = new Properties();
            base.put("name","*a*");
            base.put("phone","*5*");
            base.put("bike","*b*");
            Properties add = new Properties();
            add.put("phone","*5*");
            add.put("bike","*b*");
            add.put("name","*a*");
            String sql = q.getSqlString(colsToView,base,add,false);
            pw.println("TESTCASE 059.8 SQL String is: "+sql);
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception i)
        {
            i.printStackTrace();
        }
    }

    private void testCase059_9()
    {
        try
        {
            String[] colsToView = {"propname","propval","ipAddress","community"} ;
            Properties base = new Properties();
            base.put("name","*a*");
            base.put("phone","*5*");
            base.put("bike","*b*");
            Properties add = new Properties();
            add.put("phone","*4*");
            add.put("bike","*i*");
            String sql = q.getSqlString(colsToView,base,add,true);
            pw.println("TESTCASE #059_9 SQL String is: "+sql);
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception i)
        {
            i.printStackTrace();
        }
    }

    private void testCase059_10()
    {
        try
        {
            String[] colsToView = {"propname","propval","ipAddress","community"} ;
            Properties base = new Properties();
            base.put("name","*a*");
            Properties add = new Properties();
            add.put("name","*s*");
            String sql = q.getSqlString(colsToView,base,add,true);
            pw.println("TESTCASE 059_10 SQL String is: "+sql);
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception i)
        {
            i.printStackTrace();
        }
    }

    private void testCase059_11()
    {
        try
        {
            String[] colsToView = {"ipAddress","community"} ;
            Properties base = new Properties();
            base.put("name","*a*");
            base.put("phone","*5*");
            base.put("bike","*b*");
            Properties add = new Properties();
            add.put("name","*s*");
            String sql = q.getSqlString(colsToView,base,add,true);
            pw.println("TESTCASE 059_11 SQL String is: "+sql);
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception i)
        {
            i.printStackTrace();
        }
    }

    private void testCase059_12()
    {
        try
        {
            String[] colsToView = {"ipAddress","community"} ;
            Properties base = new Properties();
            base.put("name","*a*");
            Properties add = new Properties();
            base.put("name","*s*");
            base.put("phone","*5*");
            base.put("bike","*b*");
            String sql = q.getSqlString(colsToView,base,add,false);
            pw.println("TESTCASE 059_12 SQL String is: "+sql);
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception i)
        {
            i.printStackTrace();
        }
    }

    private void testCase059_13()
    {
        try
        {
            String[] colsToView = {"ipAddress","community"} ;
            Properties base = new Properties();
            base.put("name","*a*");
            Properties add = new Properties();
            add.put("name","*s*");
            add.put("phone","*5*");
            add.put("bike","*b*");
            Vector tablesToQuery = new Vector();
            tablesToQuery.addElement("ManagedObject");
            tablesToQuery.addElement("TOPOUSERPROPS");
            String sql = q.getSqlString(colsToView,base,add,true);
            pw.println("TESTCASE 059_13 SQL String is: "+sql);
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception i)
        {
            i.printStackTrace();
        }
    }

    private void testCase059_14()
    {
        try
        {
            String[] colsToView = {"propname","propval","ipAddress","community"} ;
            Properties base = new Properties();
            base.put("name","*a*");
            base.put("phone","*5*");
            base.put("bike","*b*");
            Properties add = new Properties();
            add.put("name","*s*");
            String sql = q.getSqlString(colsToView,base,add,true);
            pw.println("TESTCASE 059_14 SQL String is: "+sql);
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception i)
        {
            i.printStackTrace();
        }
    }


    private void testCase059_15()
    {
        try
        {
            String[] colsToView = {"propname","propval","ipAddress","community"} ;
            Properties base = new Properties();
            base.put("name","*a*");
            Properties add = new Properties();
            add.put("name","*s*");
            add.put("phone","*5*");
            add.put("bike","*b*");
            Vector tablesToQuery = new Vector();
            tablesToQuery.addElement("ManagedObject");
            tablesToQuery.addElement("TOPOUSERPROPS");
            String sql = q.getSqlString(colsToView,base,add,true);
            pw.println("TESTCASE 059_15 SQL String is: "+sql);
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception i)
        {
            i.printStackTrace();
        }
    }

    private void testCase059_16()
    {
        try
        {
            String[] colsToView = {"propname","propval","ipAddress","community"} ;
            Properties base = new Properties();
            base.put("name","*s*");
            Properties add = new Properties();
            add.put("name","*a*");
            add.put("phone","*5*");
            add.put("bike","*b*");
            String sql = q.getSqlString(colsToView,base,add,false);
            pw.println("TESTCASE 059_16 SQL String is: "+sql);
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception i)
        {
            i.printStackTrace();
        }
    }

    private void testCase059_17()
    {
        try
        {
            String[] colsToView = {"ipAddress","community"} ;
            Properties base = new Properties();
            base.put("name","*a*");
            base.put("phone","*5*");
            base.put("bike","*b*");
            Properties add = new Properties();
            add.put("name","*s*");
            add.put("phone","*5*");
            add.put("bike","*a*");
            String sql = q.getSqlString(colsToView,base,add,true);
            pw.println("<br><br>");
            pw.println("<br><br>");
            pw.println("TESTCASE 059_17 SQL String is: "+sql);
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception i)
        {
            i.printStackTrace();
        }
    }

    private void testCase059_18()
    {
        try
        {
            String[] colsToView = {"ipAddress","community"} ;
            Properties base = new Properties();
            base.put("name","*a*");
            base.put("phone","*5*");
            base.put("bike","*b*");
            Properties add = new Properties();
            add.put("name","*s*");
            add.put("phone","*4*");
            add.put("bike","*i*");
            String sql = q.getSqlString(colsToView,base,add,false);
            pw.println("TESTCASE 059_18 SQL String is: "+sql);
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception i)
        {
            i.printStackTrace();
        }
    }

    private void testCase060()
    {
        try
        {

            Properties props = new Properties();
            props.setProperty("name","IF*" );
            props.setProperty("isSNMP",	"true" );
            String[] colsToView = {"status","isRouter"};
            String sql = q.getSqlString(colsToView,props);
            pw.println("TESTCASE060 SQL FETCHED IS IS : "+sql);
            Vector v = q.executeQuery(sql, colsToView, props,false);
            Enumeration en = v.elements();
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE060 DATA : "+en.nextElement());
                pw.println("<br><br>");
            }
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception j)
        {
            j.printStackTrace();
        }
    }

    private void testCase061()
    {
        try
        {

            Properties props = new Properties();
            props.setProperty("name","IF*" );
            props.setProperty("isSNMP",	"true" );
            String[] colsToView = {"status","isRouter","propname"};
            String sql = q.getSqlString(colsToView,props);
            pw.println("TESTCASE061 SQL FETCHED IS IS : "+sql);
            Vector v = q.executeQuery(sql, colsToView, props,false);
            Enumeration en = v.elements();
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE061 DATA : "+en.nextElement());
                pw.println("<br><br>");
            }
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception j)
        {
            j.printStackTrace();
        }
    }

    private void testCase062()
    {
        try
        {

            Properties props = new Properties();
            props.setProperty("name","*s*" );
            props.setProperty("isSNMP",	"true" );
            props.setProperty("phone","*5*" );
            props.setProperty("isInterface","f*");
            String[] colsToView = {"status","isRouter","userprop"};
            String sql = q.getSqlString(colsToView,props);
            pw.println("TESTCASE##062 SQL FETCHED IS IS : "+sql);
            Vector v = q.executeQuery(sql, colsToView, props,false);
            Enumeration en = v.elements();
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE062 DATA : "+en.nextElement());
                pw.println("<br><br>");
            }
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception j)
        {
            j.printStackTrace();
        }
    }

    private void testCase063()
    {
        try
        {

            Properties props = new Properties();
            props.setProperty("name","IF*" );
            props.setProperty("isSNMP",	"true" );
            props.setProperty("phone","*5*" );
            props.setProperty("isInterface","t*");
            String[] colsToView = {"status","isRouter"};
            String sql = q.getSqlString(colsToView,props);
            pw.println("TESTCASE063 SQL FETCHED IS IS : "+sql);
            Vector v = q.executeQuery(sql, colsToView, props,false);
            Enumeration en = v.elements();
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE063 DATA : "+en.nextElement());
                pw.println("<br><br>");
            }
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception j)
        {
            j.printStackTrace();
        }
    }


    private void testCase064()
    {
        try
        {

            Properties props = new Properties();
            props.setProperty("name","IF*" );
            props.setProperty("isSNMP",	"true" );
            String[] colsToView = {"name"};
            String sql = q.getSqlString(colsToView,props);
            pw.println("TESTCASE064 SQL FETCHED IS IS : "+sql);
            Vector v = q.executeQuery(sql, colsToView, props,true);
            Enumeration en = v.elements();
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE064 DATA : "+en.nextElement());
                pw.println("<br><br>");
            }
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception j)
        {
            j.printStackTrace();
        }
    }

    private void testCase065()
    {
        try
        {

            Properties props = new Properties();
            props.setProperty("name","*a*" );
            props.setProperty("isNode","true" );
            props.setProperty("phone","*5*");
            String[] colsToView = {"name"};
            String sql = q.getSqlString(colsToView,props);
            pw.println("TESTCASE065 SQL FETCHED IS IS : "+sql);
            Vector v = q.executeQuery(sql, colsToView, props,false);
            Enumeration en = v.elements();
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE065 DATA : "+en.nextElement());
                pw.println("<br><br>");
            }
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception j)
        {
            j.printStackTrace();
        }
    }

    private void testCase066()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty("name","IF*" );
            props.setProperty("isSNMP","true" );
            String[] colsToView = {"status","isRouter"};
            Vector v =	 q.getData(colsToView, props,false, false);
            Enumeration en = v.elements();
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE 066 -> DATA : "+en.nextElement());
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

    private void testCase067()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty("name","IF*" );
            props.setProperty("isSNMP","true" );
            String[] colsToView = {"status","isRouter","userprop"};
            Vector v =	 q.getData(colsToView, props,false,true);
            Enumeration en = v.elements();
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE *067 -> DATA : "+en.nextElement());
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

    private void testCase068()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty("name","*a*");
            props.setProperty("isNode","true" );
            props.setProperty("phone","*5*");
            String[] colsToView = {"name"};
            Vector v =	 q.getData(colsToView, props,true,false);
            Enumeration en = v.elements();
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE 068 -> DATA : "+en.nextElement());
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

    private void testCase069()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty("name","*a*");
            props.setProperty("isNode","true");
            props.setProperty("phone","*5*");
            String[] colsToView = {"name"};
            Vector v =	 q.getData(colsToView, props,true,true);
            Enumeration en = v.elements();
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE 069 -> DATA : "+en.nextElement());
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
    private void testCase069_1()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty("sysOID","*.1.3.1*" );
            String[] colsToView = {"sysOID","netmask"};
            Vector tablesToQuery = new Vector();
            tablesToQuery.addElement("ManagedObject");
            tablesToQuery.addElement("TopoObject");
            tablesToQuery.addElement("SnmpNode");
            Vector v =	 q.getData(colsToView, props,tablesToQuery,false,false);
            Enumeration en = v.elements();
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE 069_1 -> DATA : "+en.nextElement());
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

    private void testCase069_2()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty("isSNMP","true" );
            String[] colsToView = {"sysOID","netmask"};
            Vector tablesToQuery = new Vector();
            tablesToQuery.addElement("ManagedObject");
            tablesToQuery.addElement("TopoObject");
            tablesToQuery.addElement("SnmpNode");
            Vector v =	 q.getData(colsToView, props,tablesToQuery,false,false);
            Enumeration en = v.elements();
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE 069_2 -> DATA : "+en.nextElement());
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

    private void testCase069_3()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty("sysOID","*.1.3.1*" );
            String[] colsToView = {"isSNMP","netmask"};
            Vector tablesToQuery = new Vector();
            tablesToQuery.addElement("ManagedObject");
            tablesToQuery.addElement("TopoObject");
            tablesToQuery.addElement("SnmpNode");
            Vector v =	 q.getData(colsToView, props,tablesToQuery,false,false);
            Enumeration en = v.elements();
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE 069_3 -> DATA : "+en.nextElement());
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

    private void testCase069_4()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty("sysOID","*.1.2.1*" );
            String[] colsToView = {"sysOID","netmask"};pw.println("ksakjasdhf");
            Vector v =	 q.getData(colsToView, props,null,false,false);
            Enumeration en = v.elements();
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE 069_4 -> DATA : "+en.nextElement());
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


    private void testCase069_5()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty("sysOID","*.1.2.1*" );
            String[] colsToView = {"sysOID","IFDESCR"};
            Vector tablesToQuery = new Vector();
            tablesToQuery.addElement("ManagedObject");
            tablesToQuery.addElement("TopoObject");
            Vector v =	 q.getData(colsToView, props,tablesToQuery,false,true);
            Enumeration en = v.elements();
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE 069_5 -> DATA : "+en.nextElement());
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

    private void testCase069_6()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty("phone","*5*" );
            String[] colsToView = {"sysOID","netmask"};
            Vector tablesToQuery = new Vector();
            tablesToQuery.addElement("ManagedObject");
            tablesToQuery.addElement("TopoObject");
            Vector v =	 q.getData(colsToView, props,tablesToQuery,false,true);
            Enumeration en = v.elements();
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE 069_6 -> DATA : "+en.nextElement());
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

    private void testCase069_7()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty("phone","*5*" );
            String[] colsToView = {"sysOID","netmask","userprop"};
            Vector tablesToQuery = new Vector();
            tablesToQuery.addElement("ManagedObject");
            tablesToQuery.addElement("TopoObject");
            Vector v =	q.getData(colsToView, props,tablesToQuery,false,false);
            Enumeration en = v.elements();
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE 069_7 -> DATA : "+en.nextElement());
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




    private void testCase070()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty("name","IF*" );
            props.setProperty("isSNMP",	"true" );
            Vector tablesToQuery = new Vector();
            tablesToQuery.addElement("ManagedObject");
            tablesToQuery.addElement("TopoObject");
            String sql = q.getSqlStringToGetAllProps(props,tablesToQuery, false);
            pw.println("TESTCASE 070 -> SQL String returned : "+sql);
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception i)
        {
            i.printStackTrace();
        }
    }


    private void testCase071()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty("name","IF*" );
            props.setProperty("isSNMP",	"true");
            Vector tablesToQuery = new Vector();
            tablesToQuery.addElement("ManagedObject");
            tablesToQuery.addElement("TopoObject");
            String sql = q.getSqlStringToGetAllProps(props,tablesToQuery, false);
            pw.println("TESTCASE 071 -> SQL String returned : "+sql);
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception i)
        {
            i.printStackTrace();
        }
    }

    private void testCase071_1()
    {
        try
        {
            Properties baseCriteria = new Properties();
            baseCriteria.put("type" , "Linux" );
            Properties additionalCriteria = new	Properties();
            additionalCriteria.put( "sysOID","*.0.2.1*");
            Vector tablesToQuery = new Vector();
            tablesToQuery.addElement("ManagedObject");
            tablesToQuery.addElement("SnmpNode");
            String sql = q.getSqlStringToGetAllProps(baseCriteria,additionalCriteria,tablesToQuery,false);
            pw.println("TESTCASE 71_1 SQL QUERY : "+sql);
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception j)
        {
            j.printStackTrace();
        }
    }

    private void testCase071_2()
    {
        try
        {
            Properties additionalCriteria = new	Properties();
            additionalCriteria.put( "sysOID","*.1.2.1*");
            Vector tablesToQuery = new Vector();
            tablesToQuery.addElement("ManagedObject");
            tablesToQuery.addElement("TopoObject");
            String sql = q.getSqlStringToGetAllProps(null,additionalCriteria,tablesToQuery,false);
            pw.println("TESTCASE 71_2 SQL QUERY : "+sql);
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception j)
        {
            j.printStackTrace();
        }
    }





    private void testCase071_3()
    {
    try
    {
        Properties additionalCriteria = new Properties();
        additionalCriteria.put("phone","*5*");
        Vector tablesToQuery = new Vector();
        tablesToQuery.addElement("ManagedObject");
     //   tablesToQuery.addElement("TOPOUSERPROPS");
        String sql = q.getSqlStringToGetAllProps(null,additionalCriteria,null,false);
        pw.println("TESTCASE 71.3 SQL String is: "+sql);
        pw.println("<br><br>");
    }

    catch(Exception k)
    {pw.println("into 71-3 "+k.toString());
    k.printStackTrace();
    }

    }


    private void testCase072()
    {
        try
        {pw.println("TESTCASE #072# DATA : ");
        Properties props = new	Properties();
        props.setProperty("name", "IF*");
        props.setProperty("isSNMP",	"true");
        Vector tablesToQuery = new Vector();
        tablesToQuery.addElement("ManagedObject");
        tablesToQuery.addElement("Topoobject");
        pw.println("before getallprops :<br> ");
        String sql = q.getSqlStringToGetAllProps(props,tablesToQuery);
        pw.println("TESTCASE #072 SQL QUERY IS : "+sql);
        pw.println("<br><br>");

        Vector v =	q.executeQueryAndGetAllProps(sql, props);
        Enumeration en = v.elements();
        while(en.hasMoreElements())
        {
            pw.println("TESTCASE 072 DATA : "+en.nextElement());
            pw.println("<br><br>");
        }
        pw.println("<br><br>");
        pw.println("<br><br>");
        }
        catch(Exception j)
        {pw.println("into 72"+j.toString());
        j.printStackTrace();
        }
    }

    private void testCase073()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty("name", "*k*");
            //props.setProperty("isSNMP",	"true");
            props.setProperty("phone","*5*");
            Vector tablesToQuery = new Vector();
            tablesToQuery.addElement("ManagedObject");
            tablesToQuery.addElement("TopoObject");
            String sql = q.getSqlStringToGetAllProps(props,tablesToQuery);
            pw.println("TESTCASE #073# SQL QUERY IS : "+ sql);
            Vector v =	q.executeQueryAndGetAllProps(sql, props);
            Enumeration en = v.elements();
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE 073 DATA : "+en.nextElement());
                pw.println("<br><br>");
            }
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception j)
        {
            j.printStackTrace();
        }
    }

    private void testCase074()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty("name","IF*" );
            props.setProperty("isSNMP","true" );
            Vector tablesToQuery = new Vector();
            tablesToQuery.addElement("ManagedObject");
            tablesToQuery.addElement("TopoObject");
            Vector v = 	q.getDataWithAllProps(props,tablesToQuery, false);
            Enumeration en = v.elements();
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE 074 DATA : "+en.nextElement());
                pw.println("<br><br>");
            }
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception i)
        {
            i.printStackTrace();
        }
    }

    private void testCase075()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty("name","*k*" );
            props.setProperty("isSNMP","true");
            props.setProperty("phone","*5*");
            Vector tablesToQuery = new Vector();
            tablesToQuery.addElement("ManagedObject");
            tablesToQuery.addElement("TopoObject");
            Vector v = 	q.getDataWithAllProps(props,tablesToQuery, false);
            Enumeration en = v.elements();
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE 075 DATA : "+en.nextElement());
                pw.println("<br><br>");
            }
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception i)
        {
            i.printStackTrace();
        }
    }

    private void testCase076()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty("name","IF*" );
            props.setProperty("isSNMP","true");
            Vector tablesToQuery = new Vector();
            tablesToQuery.addElement("ManagedObject");
            tablesToQuery.addElement("TopoObject");
            Vector v = 	q.getDataWithAllProps(props,tablesToQuery, true);
            Enumeration en = v.elements();
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE 076 DATA : "+en.nextElement());
                pw.println("<br><br>");
            }
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception i)
        {
            i.printStackTrace();
        }
    }

    private void testCase077()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty("name","*k*" );
            props.setProperty("isSNMP","true");
            props.setProperty("phone","*5*");
            Vector tablesToQuery = new Vector();
            tablesToQuery.addElement("ManagedObject");
            tablesToQuery.addElement("TopoObject");
            Vector v = 	q.getDataWithAllProps(props,tablesToQuery,true);
            Enumeration en = v.elements();
            while(en.hasMoreElements())
            {
                pw.println("TESTCASE #077 DATA : "+en.nextElement());
                pw.println("<br><br>");
            }
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception i)
        {
            i.printStackTrace();
        }
    }

    private void testCase078()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty("name","*" );
            Vector tablesToQuery = new Vector();
            tablesToQuery.addElement("ManagedObject");
            tablesToQuery.addElement("Node");
            Vector v = q.getDataWithAllProps(props,tablesToQuery);
			            Enumeration en = v.elements();
			            while(en.hasMoreElements())
			            {
			                pw.println("TESTCASE #078 DATA : "+en.nextElement());
			                pw.println("<br><br>");
			            }
			            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception i)
        {
            i.printStackTrace();
        }
    }


private void testCase079()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty("name","i*" );
            String[] colsToView = {"name"};
            Vector tablesToQuery = new Vector();
            tablesToQuery.addElement("ManagedObject");
            tablesToQuery.addElement("Node");
            String sql = q.getSqlString(colsToView,props) ;
            pw.println("TESTCASE #079 String Is  : "+sql);
			        }
        catch(Exception i)
        {
            i.printStackTrace();
        }
    }


    private void testCase080()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty("name","!i*" );
            String[] colsToView = {"name"};
            String sql = q.getSqlString(colsToView,props);
            pw.println("TESTCASE080 QUERY RETURNED : "+sql);
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception i)
        {
            i.printStackTrace();
        }
    }

    private void testCase081()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty("name","!*i" );
            String[] colsToView = {"name"};
            String sql = q.getSqlString(colsToView,props);
            pw.println("TESTCASE081 QUERY RETURNED : "+sql);
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception i)
        {
            i.printStackTrace();
        }
    }

    private void testCase082()
    {
        try
        {	pw.println(" Searching for name kasturirangan....");
            Properties props = new	Properties();
            props.setProperty("name","kasturirangan" );
            String[] colsToView = {"name"};
            String sql = q.getSqlString(colsToView,props);
            pw.println("TESTCASE082 QUERY RETURNED : "+sql);
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception i)
        {
            i.printStackTrace();
        }
    }


    private void testCase083()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty("name","<*>" );
            String[] colsToView = {"name"};
            String sql = q.getSqlString(colsToView,props);
            pw.println("TESTCASE083 QUERY RETURNED : "+sql);
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception i)
        {
            i.printStackTrace();
        }
    }


    private void testCase084()
    {
        try
        {	pw.println("Properties given are kasturirangan and vasus");
            Properties props = new	Properties();
            props.setProperty("name","kasturirangan,vasus" );
            String[] colsToView = {"name"};
            String sql = q.getSqlString(colsToView,props);
            pw.println("TESTCASE084 QUERY RETURNED : "+sql);
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception i)
        {
            i.printStackTrace();
        }
    }

    private void testCase085()
    {
        try
        {	pw.println("Properties given are k* & *s");
            Properties props = new	Properties();
            props.setProperty("name","k*&&*s" );
            String[] colsToView = {"name"};
            String sql = q.getSqlString(colsToView,props);
            pw.println("TESTCASE#085 QUERY RETURNED : "+sql);
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception i)
        {
            i.printStackTrace();
        }
    }


    private void testCase086()
    {
        try
        {	pw.println("Properties given are kasturirangan and vasus");
            Properties props = new	Properties();
            props.setProperty("name","<between> kasturirangan and vasus" );
            String[] colsToView = {"name"};
            String sql = q.getSqlString(colsToView,props);
            pw.println("TESTCASE086 QUERY RETURNED : "+sql);
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception i)
        {
            i.printStackTrace();
        }
    }

    private void testCase087()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty("pollInterval","<between> 250 and 3000" );
            String[] colsToView = {"name"};
            String sql = q.getSqlString(colsToView,props);
            pw.println("TESTCASE######087 QUERY RETURNED : "+sql);
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception i)
        {
            i.printStackTrace();
        }
    }


    private void testCase088()
    {
        try
        {
            Properties props = new	Properties();
            props.setProperty("pollinterval","<between> 3000" );
            String[] colsToView = {"name"};
            String sql = q.getSqlString(colsToView,props);
            pw.println("TESTCASE#088 QUERY RETURNED : "+sql);
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception i)
        {
            i.printStackTrace();
        }
    }

    private void testCase089()
    {
        try
        {	pw.println("Properties given are kasturirangan");
            Properties props = new	Properties();
            props.setProperty("name","<between>kasturirangan" );
            String[] colsToView = {"name"};
            String sql = q.getSqlString(colsToView,props);
            pw.println("TESTCASE089 QUERY RETURNED : "+sql);
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception i)
        {
            i.printStackTrace();
        }
    }

    private void testCase090()
    {
        try
        {	pw.println("Properties given are vasus and suresh ");
            Properties	props = new Properties();
            props.setProperty("name"," <BETWEEN> vasus	and suresh");
            String[] colsToView = {"name","up1"};
            String sql = q.getSqlString(colsToView,props);
            pw.println("TESTCASE090 QUERY RETURNED : "+sql);
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception k)
        {
            k.printStackTrace();
        }
    }

    private void testCase091()
    {
        try
        {
            Properties	props = new Properties();
            props.setProperty("name"," <BETWEEN> 300 and 3000");
            String[] colsToView = {"name","up1"};
            String sql = q.getSqlString(colsToView,props);
            pw.println("TESTCASE091 QUERY RETURNED : "+sql);
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception k)
        {
            k.printStackTrace();
        }
    }

    private void testCase092()
    {
        try
        {	pw.println("Properties given are vasus and rprabhu ");
            Properties	props = new Properties();
            props.setProperty("name"," <BETWEEN> vasus and rprabhu");
            String[] colsToView = {"name","up1"};
            String sql = q.getSqlString(colsToView,props);
            pw.println("TESTCASE092 QUERY RETURNED : "+sql);
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception k)
        {
            k.printStackTrace();
        }
    }

    private void testCase093()
    {
        try
        {
            Properties	props = new Properties();
            props.setProperty("name"," <BETWEEN> 3000 and 300");
            String[] colsToView = {"name","up1"};
            String sql = q.getSqlString(colsToView,props);
            pw.println("TESTCASE093 QUERY RETURNED : "+sql);
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception k)
        {
            k.printStackTrace();
        }
    }


    private void testCase094()
    {
        try
        {
            Properties props1 = new Properties();
            props1.setProperty("name", "v*" );
            Properties props2 = new Properties();
            props2.setProperty( "isSNMP", "true" );
            String sql = q.getSqlStringForCount(props1,props2,false);
            pw.println("TESTCASE 094 : SQL STRING IS : "+sql);
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception k)
        {
            k.printStackTrace();
        }
    }

    private void testCase095()
    {
        try
        {
            Properties props1 = new Properties();
            props1.setProperty("name", "v*" );
            Properties props2 = new Properties();
            props2.setProperty( "isSNMP", "true" );
            props2.setProperty("POLLINTERVAL","3600");
            String sql = q.getSqlStringForCount(props1,props2,true);
            pw.println("TESTCASE 095 : SQL STRING IS : "+sql);
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception k)
        {
            k.printStackTrace();
        }
    }

    private void testCase096()
    {
        try
        {
            Properties props1 = new Properties();
            props1.setProperty("name", "v*" );
            props1.setProperty("type", "N*" );
            Properties props2 = new Properties();
            props2.setProperty( "displayName", "true" );
            props2.setProperty("POLLINTERVAL","3600");
            String sql = q.getSqlStringForCount(props1,props2,false);
            pw.println("TESTCASE 095 : SQL STRING IS : "+sql);
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception k)
        {
            k.printStackTrace();
        }
    }

    private void testCase097()
    {
        try
        {
            Properties props1 = new Properties();
            props1.setProperty("name", "v*" );
            props1.setProperty("type","N*" );
            Properties props2 = new Properties();
            props2.setProperty( "displayName","v*");
            props2.setProperty("POLLINTERVAL","3600");
            String sql = q.getSqlStringForCount(props1,props2, true);
            pw.println("TESTCASE 097 SQL STRING IS  : "+sql);
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception o)
        {
            o.printStackTrace();
        }
    }

    private void testCase098()
    {
        try
        {
            Properties props1 = new Properties();
            props1.setProperty("name", "v*" );
            props1.setProperty("type","s*" );
            Properties props2 = new Properties();
            props2.setProperty( "displayName","v*");
            props2.setProperty("POLLINTERVAL","3600");
            String sql = q.getSqlStringForCount(props1,props2, true);
            pw.println("TESTCASE 098 SQL STRING IS  : "+sql);
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception o)
        {
            o.printStackTrace();
        }
    }


    private void testcase99()
    {
        try
        {

            Properties props1 = new Properties();
            props1.setProperty("name", "v*" );
            props1.setProperty("type","s*" );
            Properties props2 = new Properties();
            props2.setProperty( "displayName","v*");
            props2.setProperty("POLLINTERVAL" , "3600");
            String sql = q.getSqlStringForCount(props1,props2, false);
            pw.println("TESTCASE 99 : " +sql);
            pw.println("<br><br>");
            pw.println("<br><br>");
        }
        catch(Exception u)
        {
            u.printStackTrace();
        }
    }


    private void testCase100()
    {
        try
        {
            int count = q.executeAndGetCount("select count(*) from ManagedObject");
            pw.println("TESTCASE 100 : COUNT is  : "+count);
        }
        catch(Exception o)
        {
            o.printStackTrace();
        }
    }

    private void testCase101()
    {
        try
        {
            int count = q.executeAndGetCount("select count(*) from ManagedObject where POLLINTERVAL = 300");
            pw.println("TESTCASE 101 : COUNT is  : "+count);
        }
        catch(Exception o)
        {
            o.printStackTrace();
        }
    }

    private void testCase102()
    {
        try
        {
            int count = q.executeAndGetCount("select count(*) from ManagedObject where POLLINTERVAL = 3600 and name = 'vasus'");
            pw.println("TESTCASE 102 : COUNT is  : "+count);
        }
        catch(Exception o)
        {
            o.printStackTrace();
        }
    }


    private void testCase173()
    {
		pw.println("TESTCASE 173 *******************************************");
    try
    {
        Vector v = null;
         Properties props = new	Properties();
        Enumeration e = null;
        String colsToView[] = {"ipAddress","isSNMP","netmask","name","status","type","ownerName","managed","classname"};
        String sql =q.getSqlString(colsToView,props);
		pw.println("Query-" +sql);
        v = q.getData(colsToView, null ,null , null,true,1,10,"status",true);
        e = v.elements();
        pw.println("TESTCASE 173 *******************************************");
        pw.println("<br>");
        while(e.hasMoreElements())
        {
            pw.println("Element testCase173 : "+e.nextElement());
            pw.println("<br>");
        }
        v = null;
        e = null;
        pw.println("TESTCASE 173 OVER ************************************");
        pw.println("<br>");
        pw.println("<br>");
    }

    catch(Exception m)
    {
        pw.println(m.toString());
        m.printStackTrace();
    }
    }

    private void testCase174_1()
    {
        Vector v = null;
        Enumeration e = null;
        try
        {
            pw.println("TESTCASE  174_1***************#########**********************");
            pw.println("T#$#%$$GFRGFDT$TGFGVFXGDFGWT$%TE@T$#^T$T^$#TG$%$EDGFE@T@$#TR");
            pw.println("<br>");
            String colsToView[] = {"ipAddress","isSNMP","netmask","name","status","type","ownerName","managed","classname"};
            v = q.getData(colsToView, null ,null , null,true,0,2,"status",true);
            e = v.elements();
            while(e.hasMoreElements())
            {
                pw.println("Element testCase174_1 : "+e.nextElement());
                pw.println("<br>");
            }
            pw.println("TESTCASE 174_1 OVER ***************************");
            pw.println("<br>");
            pw.println("<br>");
            pw.println("<br>");
            pw.println("<br>");
            pw.println("<br>");
            pw.println("<br>");
        }
        catch(Exception m)
        {pw.println(m.toString());
        m.printStackTrace();
        }
        finally
        {
            v = null;
            e = null;
        }
    }

    private void testCase174_2()
    {
        Vector v = null;
        Enumeration e = null;
        try
        {
            pw.println("TESTCASE  174_2*************************************");

            pw.println("<br>");
            String colsToView[] = {"ipAddress","isSNMP","netmask","name","status","type","ownerName","managed","classname"};
            v = q.getData(colsToView, null ,null , null,true,1,3,"status",true);
            e = v.elements();
            while(e.hasMoreElements())
            {
                pw.println("Element testCase174_2 : "+e.nextElement());
                pw.println("<br>");
            }
            pw.println("TESTCASE 174_2 OVER ***************************");
            pw.println("<br>");

            pw.println("<br>");
        }
        catch(Exception m)
        {pw.println(m.toString());
        m.printStackTrace();
        }
        finally
        {
            v = null;
            e = null;
        }
    }

    private void testCase175()
    {
        Vector v = null;
        try
        {
            pw.println("TESTCASE  175***************************************");
            pw.println("<br>");
            String colsToView[] = {"ipAddress","isSNMP","netmask","name","status","type","ownerName","managed","classname"};
            v = q.getData(colsToView, null ,null , null,true,-12,10,"status",true);

            pw.println("TESTCASE 175 : FAILED(No Exception is thrown on invoking the method with a negative start index )");
            pw.println("<br>");

            pw.println("TESTCASE  175***     OVER     ****************************");
            pw.println("<br>");
            pw.println("<br>");
        }
        catch(Exception m)
        {
            pw.println("TESTCASE 175 : PASSED (Exception thrown)");
            m.printStackTrace();
        }
        finally
        {
            v = null;
        }
    }


    private void testCase176()
    {
        Vector v = null;
        try
        {
            pw.println("TESTCASE  176***************************************");
            pw.println("<br>");
            String colsToView[] = {"ipAddress","isSNMP","netmask","name","status","type","ownerName","managed","classname"};
            v = q.getData(colsToView, null ,null , null,true,55130,40,"status",true);

            pw.println("TESTCASE 176 : FAILED(No Exception is thrown on invoking the method with a negative start index )");
            pw.println("<br>");

            pw.println("TESTCASE  176***    OVER     ****************************");
            pw.println("<br>");
            pw.println("<br>");
          }
        catch(Exception m)
        {
            pw.println("TESTCASE 176 : PASSED (Exception thrown)");
            m.printStackTrace();
        }
        finally
        {
            v = null;
        }
    }

    private void testCase177()
    {
        Vector v = null;
        Enumeration e = null;
        try
        {
            pw.println("TESTCASE  177*************************************");
            pw.println("<br>");
            String colsToView[] = {"ipAddress","isSNMP","netmask","name","status","type","ownerName","managed","classname"};
            v = q.getData(colsToView, null ,null , null,true,10,5000,"status",true);
            e = v.elements();
            int nn = 0;
            while(e.hasMoreElements())
            {
                nn++;
                pw.println("Element testCase177 : "+e.nextElement());
                pw.println("<br>");

            }
            pw.println("TESTCASE 177 n isis is : "+nn);
            pw.println("TESTCASE 177 OVER ***************************");
            pw.println("<br>");
            pw.println("<br>");
        }
        catch(Exception m)
        {pw.println("Into 177" + m.toString());
        m.printStackTrace();
        }
        finally
        {
            v = null;
            e = null;
        }
    }

    private void testCase178()
    {
        Vector v = null;
        try
        {
            pw.println("TESTCASE  178***************************************");
            pw.println("<br>");
            String colsToView[] = {"ipAddress","isSNMP","netmask","name","status","type","ownerName","managed","classname"};
            v = q.getData(colsToView, null ,null , null,true,30,-40,"status",true);

            pw.println("TESTCASE 178 : FAILED(No Exception is thrown on invoking the method with a negative start index )");
            pw.println("<br>");

            pw.println("TESTCASE  178***    OVER     ****************************");
            pw.println("<br>");
            pw.println("<br>");
        }
        catch(Exception m)
        {
            pw.println("TESTCASE 178 : PASSED (Exception thrown)");
            m.printStackTrace();
        }
        finally
        {
            v = null;
        }
    }

    private void testCase179()
    {
        Vector v = null;
        Enumeration e = null;
        try
        {
            String colsToView[] = {"ipAddress","isSNMP","netmask","name","status","type","ownerName","managed","classname"};
            v = q.getData(colsToView, null ,null , null,true,1,10,"status",true);
            e = v.elements();
            pw.println("TESTCASE 179 *******************************************");
            pw.println("<br>");
            while(e.hasMoreElements())
            {
                pw.println("Element testCase179 : "+e.nextElement());
                pw.println("<br>");
            }
            pw.println("TESTCASE 179 OVER ************************************");
            pw.println("<br>");
            pw.println("<br>");
            pw.println("<br>");
            pw.println("<br>");
            pw.println("<br>");
            pw.println("<br>");
            pw.println("<br>");
            pw.println("<br>");
            pw.println("<br>");
        }

        catch(Exception m)
        {pw.println("Into 179" + m.toString());
        m.printStackTrace();
        }
    }


    private void testCase180()
    {
        Vector v = null;
        Enumeration e = null;
        try
        {
            String colsToView[] = {"ipAddress","isSNMP","netmask","name","status","type","ownerName","managed","classname"};
            v = q.getData(colsToView, null ,null , null,true,1,10,"isSNMP",true);
            e = v.elements();
            pw.println("TESTCASE 180 *******************************************");
            pw.println("<br>");
            while(e.hasMoreElements())
            {
                pw.println("Element testCase180 : "+e.nextElement());
                pw.println("<br>");
            }
            pw.println("TESTCASE 180 OVER ************************************");
            pw.println("<br>");
            pw.println("<br>");
            pw.println("<br>");
            pw.println("<br>");
            pw.println("<br>");
            pw.println("<br>");
            pw.println("<br>");
        }

        catch(Exception m)
        {
            System.out.println("EXCEPTION THROWN IN TESTCASE 180 ");
            m.printStackTrace();
        }
    }

    private void testCase181()
    {
        Vector v = null;
        Enumeration e = null;
        try
        {
            String colsToView[] = {"ipAddress","isSNMP","netmask","name","status","type","ownerName","managed","classname"};
            v = q.getData(colsToView, null ,null , null,true,1,10,"version",true);
            e = v.elements();
            pw.println("TESTCASE 181 *******************************************");
            pw.println("TESTCASE 181 181 181 1#System.out.println(%R$TRFETR");
            pw.println("<br>");
            while(e.hasMoreElements())
            {
                pw.println("Element testCase181 : "+e.nextElement());
                pw.println("<br>");
            }
            pw.println("TESTCASE 181 OVER ************************************");
            pw.println("<br>");
            pw.println("<br>");
            pw.println("<br>");
            pw.println("<br>");
            pw.println("<br>");
            pw.println("<br>");
            pw.println("<br>");
            pw.println("<br>");
        }

        catch(Exception m)
        {
            System.out.println("EXCEPTION THROWN IN TESTCASE 181 ");
            m.printStackTrace();
        }
    }


    private void testCase182()
    {
        Vector v = null;
        try
        {
            pw.println("TESTCASE  182***************************************");
            pw.println("<br>");
            String colsToView[] = {"ipAddress","isSNMP","netmask","name","status","type","ownerName","managed","classname"};
            v = q.getData(colsToView, null ,null , null,true,1,10,"dxyz",true);

            pw.println("TESTCASE 182 : FAILED(No Exception is thrown on invoking the method with a negative start index )");
            pw.println("<br>");

            pw.println("TESTCASE  182***    OVER     ****************************");
            pw.println("<br>");
            pw.println("<br>");
            pw.println("<br>");
            pw.println("<br>");
            pw.println("<br>");
            pw.println("<br>");
            pw.println("<br>");
        }
        catch(Exception m)
        {
            pw.println("TESTCASE 182 : PASSED (Exception thrown)");
            m.printStackTrace();
        }
        finally
        {
            v = null;
        }
    }


    private void testCase183()
    {
        Vector v = null;
        Enumeration e = null;
        try
        {
            String colsToView[] = {"ipAddress","isSNMP","netmask","name","status","type","ownerName","managed","classname"};
            v = q.getData(colsToView, null ,null , null,true,1,10,"status",false);
            e = v.elements();
            pw.println("TESTCASE 183 *******************************************");
            pw.println("<br>");
            while(e.hasMoreElements())
            {
                pw.println("Element testCase183 : "+e.nextElement());
                pw.println("<br>");
            }
            pw.println("TESTCASE 183 OVER ************************************");
            pw.println("<br>");
            pw.println("<br>");
        }

        catch(Exception m)
        {pw.println("TESTCASE 183 : into Exception ");
        m.printStackTrace();
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
