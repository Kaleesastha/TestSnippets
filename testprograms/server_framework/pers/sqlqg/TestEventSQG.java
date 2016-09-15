import java.io.File;
import java.util.Vector;
import java.util.Properties;
import java.util.Enumeration;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import javax.servlet.GenericServlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.PrintWriter;

import com.adventnet.nms.util.DBParamsParser;
import com.adventnet.nms.util.PureUtils;
import com.adventnet.nms.db.util.SQLQueryGenerator;
import com.adventnet.nms.db.util.RelationalInterface;
import com.adventnet.nms.db.util.RelationalInterfaceImpl;
import com.adventnet.nms.store.relational.RelationalUtil;
import com.adventnet.nms.store.relational.RelationalAPI;


public class TestEventSQG  extends GenericServlet
{
	private static String rootDir;
	private SQLQueryGenerator queryGen;
	private Connection conn = null;
	private PrintWriter pw = null;
	Vector result = null;
	String specialProp = "EventAge";
	String colsToView[] = {"id","groupName","ownerName","category","severity","entity","createTime","message"};

	public void service(ServletRequest req,ServletResponse res)
	{
		try
		{
			res.setContentType("text/html");
			pw = res.getWriter();
			in();
			executeTestCase();	
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
		}
		catch(Exception ce)
		{
			pw.println(ce.getMessage());
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
		queryGen = new SQLQueryGenerator(conn, "EventDB", "Event", "EVENTUSERPROPS",relImpl);
		pw.println("<br>Created an instance of the SQLQG for module:EVENT : "+queryGen);
	}
	private void executeTestCase()
	{
		try
		{
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
		
		}
		catch(Exception excp)
		{
			excp.printStackTrace();
		}
	}
	private void testCase001()throws SQLException
	{
		pw.println("<br><br><b>TESTCASE ID 001 STARTED*************</b>");
		Properties base = new Properties();
		base.setProperty(specialProp,"RMi < 10 0");
		result = queryGen.getData(colsToView,base,null,null,true,1,20,"severity",true);
		printResult("001");
	}
	private void testCase002()throws SQLException
	{
		pw.println("<br><br><b>TESTCASE ID 002 STARTED*************</b>");
		Properties base = new Properties();
		base.setProperty(specialProp,"RMi <= 10 0");
		result = queryGen.getData(colsToView,base,null,null,true,1,20,"severity",true);
		printResult("002");
	}
	private void testCase003()throws SQLException
	{
		pw.println("<br><br><b>TESTCASE ID 003 STARTED*************</b>");
		Properties base = new Properties();
		base.setProperty(specialProp,"RMi > 10 0");
		result = queryGen.getData(colsToView,base,null,null,true,1,20,"severity",true);

		printResult("003");
		
	}
	private void testCase004()throws SQLException
	{

		pw.println("<br><br><b>TESTCASE ID 004 STARTED*************</b>");
		Properties base = new Properties();
		base.setProperty(specialProp,"RMi >= 10 0");
		result = queryGen.getData(colsToView,base,null,null,true,1,20,"severity",true);
		printResult("004");
	}
	private void testCase005()throws SQLException
	{
		pw.println("<br><br><b>TESTCASE ID 005 STARTED*************</b>");
		Properties base = new Properties();
		base.setProperty(specialProp,"RHr < 1 0");
		result = queryGen.getData(colsToView,base,null,null,true,1,20,"severity",true);
		printResult("005");

	}
	private void testCase006()throws SQLException
	{	
		pw.println("<br><br><b>TESTCASE ID 006 STARTED*************</b>");
		Properties base = new Properties();
		base.setProperty(specialProp,"RHr <= 1 0");
		result = queryGen.getData(colsToView,base,null,null,true,1,20,"severity",true);
		printResult("006");

	}
	private void testCase007()throws SQLException
	{
		pw.println("<br><br><b>TESTCASE ID 007 STARTED*************</b>");
		Properties base = new Properties();
		base.setProperty(specialProp,"RHr > 1 0");
		result = queryGen.getData(colsToView,base,null,null,true,1,20,"severity",true);
		printResult("007");
	}
	private void testCase008()throws SQLException
	{
		pw.println("<br><br><b>TESTCASE ID 008 STARTED*************</b>");
		Properties base = new Properties();
		base.setProperty(specialProp,"RHr >= 1 0");
		result = queryGen.getData(colsToView,base,null,null,true,1,20,"severity",true);
		printResult("008");
	}
	private void testCase009()throws SQLException
	{
		pw.println("<br><br><b>TESTCASE ID 009 STARTED*************</b>");
		Properties base = new Properties();
		base.setProperty(specialProp,"RDy < 1 0");
		result = queryGen.getData(colsToView,base,null,null,true,1,20,"severity",true);
		printResult("009");

	}
	private void testCase010()throws SQLException
	{
		pw.println("<br><br><b>TESTCASE ID 010 STARTED*************</b>");
		Properties base = new Properties();
		base.setProperty(specialProp,"RDy <= 1 0");
		result = queryGen.getData(colsToView,base,null,null,true,1,20,"severity",true);
		printResult("010");

	}
	private void testCase011()throws SQLException
	{
		pw.println("<br><br><b>TESTCASE ID 011 STARTED*************</b>");
		Properties base = new Properties();
		base.setProperty(specialProp,"RDy > 1 0");
		result = queryGen.getData(colsToView,base,null,null,true,1,20,"severity",true);
		printResult("011");
	}
	private void testCase012()throws SQLException
	{
		pw.println("<br><br><b>TESTCASE ID 012 STARTED*************</b>");
		Properties base = new Properties();
		base.setProperty(specialProp,"RDy >= 1 0");
		result = queryGen.getData(colsToView,base,null,null,true,1,20,"severity",true);
		printResult("012");

	}
	private void testCase013()throws SQLException
	{
		pw.println("<br><br><b>TESTCASE ID 013 STARTED*************</b>");
		Properties base = new Properties();
		base.setProperty(specialProp,"ADy == 1 0");
		result = queryGen.getData(colsToView,base,null,null,true,1,20,"severity",true);
		printResult("013");
	}
	private void testCase014()throws SQLException
	{
		pw.println("<br><br><b>TESTCASE ID 014 STARTED*************</b>");
		Properties base = new Properties();
		base.setProperty(specialProp,"ADy == 2 0");
		result = queryGen.getData(colsToView,base,null,null,true,1,20,"severity",true);
		printResult("014");
	}
	private void testCase015()throws SQLException
	{
		pw.println("<br><br><b>TESTCASE ID 015 STARTED*************</b>");
		Properties base = new Properties();
		base.setProperty(specialProp,"SDy -1");
		result = queryGen.getData(colsToView,base,null,null,true,1,20,"severity",true);
		printResult("015");

	}

	private void printResult(String caseID)
	{
		pw.println("<font color=\"red\"><b><br>Result for Case id "+caseID + " </b></font>");
		pw.println("<br>");
		Enumeration enu = result.elements();
		while(enu.hasMoreElements())
		{
			pw.println(enu.nextElement());
			pw.println("<br>");
		}
		pw.println("<b>END OF TESTCASE ID : " + caseID + " </b><br>");
	}
} 


/*

private void testCase016()throws SQLException
	{
		pw.println("<b>TESTCASE ID 016 STARTED*************</b>");
		Properties base = new Properties();
		base.setProperty("groupViewMode","max");
		result = queryGen.getData(colsToView,base,null,null,true,1,50,"severity",true);
		printResult("016");

	}
	private void testCase0161()throws SQLException
	{
		pw.println("<b>TESTCASE ID 016.1 STARTED*************</b>");
		Properties base = new Properties();
		base.setProperty("groupViewMode","max");
		result = queryGen.getData(colsToView,base,null,null,true,1,50,"severity",true);
		printResult("0161");
	}
	private void testCase017()throws SQLException
	{
		pw.println("<b>TESTCASE ID 017 STARTED*************</b>");
		Properties base = new Properties();
		base.setProperty("groupViewMode","latest");
		result = queryGen.getData(colsToView,base,null,null,true,1,50,"severity",true);
		printResult("017");
	}
*/
