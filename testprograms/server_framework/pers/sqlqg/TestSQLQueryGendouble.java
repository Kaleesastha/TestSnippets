
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

public class TestSQLQueryGendouble extends GenericServlet
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
//        q = new SQLQueryGenerator(conn, "TopoDB", "ManagedObject", "TOPOUSERPROPS", relImpl);
		q = new SQLQueryGenerator(conn, "EventDB", "Event", "EVENTUSERPROPS", relImpl);
        //q = new SQLQueryGenerator(conn, "AlertDB", "Alert", "ALERTUSERPROPS", relImpl);

        pw.println("Created an instance of the SQL Q G : "+q);
        pw.println("<br><br>");
    }


    private void test()
    {
        pw.println("Starting to test the TestCases!!!!");
        pw.println("<br><br>");
        long time1 = System.currentTimeMillis();

        /*
		This Case it to test all the double coloumn cases from 163 to 172
		Before doing this Follow the procedure as given in the TestPlan
		All Databases dont support Double columns so validate with care......

		*/

		        Vector v = null;
		        Enumeration e = null;
		        try
		        {
				Properties props1 = new Properties();
				            String colsToView1[] = {"testColumn"};
						    String sql1 = q.getSqlString(colsToView1,props1) ;
							pw.println(" 163 SQL String is " + sql1);
							pw.println("TESTCASE 163 OVER ************************************");


							v = q.executeQuery(sql1,colsToView1,props1,false);
							pw.println("vector returned " + v);
				            pw.println("TESTCASE 164 OVER ************************************");
				            pw.println("<br>");
		            		pw.println("<br>");

				Properties props = new Properties();
		        props.setProperty("testColumn","10.52" );
		        Vector tablesToQuery = new Vector();
				tablesToQuery.addElement("Event");
		        String colsToView[] = {"testColumn","name"};
			    String sql = q.getSqlString(colsToView,props) ;
				pw.println(" 165 SQL String is " + sql);
				pw.println("TESTCASE 165 OVER ************************************");

				v = q.executeQuery(sql, colsToView, props,false);
				pw.println("vector returned " + v);
			 	pw.println("<br>");
		        pw.println("<br>");
		        pw.println("TESTCASE 166 OVER ************************************");
		        pw.println("<br>");
		        pw.println("<br>");

		     sql = q.getSqlStringToGetAllProps(props,tablesToQuery);
		     pw.println(" 167 SQL String is " + sql);
			 v=q.executeQueryAndGetAllProps(sql, props);
			 pw.println("vector returned " + v);
		     pw.println("TESTCASE 168 OVER ************************************");
		     pw.println("<br>");
		     pw.println("<br>");


		     		pw.println( " case 169: o/p is "+ q.isNumericField("Event","testColumn"));
		            v =q.getDataWithAllProps(props,tablesToQuery,true);
		            pw.println("vector returned " + v);
		            pw.println("TESTCASE 170 OVER ************************************");
		 			pw.println("<br>");
		            pw.println("<br>");

		     		v =	 q.getData(colsToView,props,false,false);
		            pw.println("vector returned " + v);
		            pw.println("TESTCASE 171 & 172 OVER ************************************");
		            pw.println("<br>");
		            pw.println("<br>");
		        }

		        catch(Exception m)
		        {
					pw.println("TESTCASE 165to172 : into Exception ");
		        	m.printStackTrace();
		        }


        long time2 = System.currentTimeMillis();
        pw.println("COMPLETED ALL  TESTCASES IN THE TESTPLAN SF-PERS-SQG: ");
        pw.println("<br>");
        pw.println("Time taken to Validate the 	TestCases : "+(time2-time1));

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
