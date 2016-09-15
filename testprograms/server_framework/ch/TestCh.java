import com.adventnet.nms.eventdb.Event;
import com.adventnet.nms.eventdb.EventAPI;
import com.adventnet.nms.util.NmsUtil;
import javax.servlet.GenericServlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.PrintWriter;
import java.util.Vector;
import java.util.Properties;
import java.util.Enumeration;
import java.sql.*;
import java.rmi.*;
import com.adventnet.nms.store.relational.RelationalAPI;

public class TestCh extends GenericServlet
{
	PrintWriter pw = null;
	EventAPI eapi = null;
	Statement s = null;
	RelationalAPI r = null;
	Connection con = null;
	public void service(ServletRequest req,ServletResponse res)
	{
		try 
		{
			res.setContentType("text/html");
			pw = res.getWriter();
			r = NmsUtil.relapi;
			con = r.getConnection();
			s = con.createStatement();
			test();
		}
		catch(Exception k)
		{
			k.printStackTrace();
		}
	}

	private void test()
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
			testCase016();
			testCase017();
			testCase018();
			testCase019();
			testCase020();
			testCase021();
		}
		catch(Exception d)
		{
			d.printStackTrace();
		}
	}


	private void testCase001()
	{
		try
		{
			Event e = new Event ();
			pw.println("1");
			e.setText("돨돨角돨角몸角角");            //some Chinese fonts
			e.setSource("testSource");
			e.setEntity("testEntity");
			e.setId(3002);
			e.setSeverity(1);
			eapi = (EventAPI) NmsUtil.getAPI  ("EventAPI");
			eapi.addEvent(e);
			pw.println("Added Event 돨돨角돨角몸角角 for TESTCASE 001 , check in the Application client and Database");
			pw.println("<br><br>");
		}
		catch(Exception l)
		{
			l.printStackTrace();
		}
	}

	private void testCase002()
	{
		try
		{
			Vector v = (Vector)eapi.getCompleteList();
			Enumeration en = v.elements();
			pw.println("EN obt");
			while(en.hasMoreElements())
			{
				int id = Integer.parseInt(en.nextElement().toString());
				Event eve = (Event)eapi.getEventByID(id);
				if(eve.getSeverity()==1)
				{
					System.out.println("RAJESHD RAJESHD The Message is : "+eve.getText());
				}
				pw.println("Retrieved all the elements and displayed in the stdout.txt log file - for TESTCASE 002, check for the displayed element in the log file");
				pw.println("<br><br>");
			} 
		}
		catch (Exception e)
		{
			e.printStackTrace(); 
		}
	}


	private void testCase003()
	{
		try
		{
			s.execute("create table 가가가 (name varchar(20))" );
			pw.println("Table Created 3 over ");
			pw.println("<br><br>");
		}
		catch(Exception u)
		{
			u.printStackTrace();
			pw.println("3 EXCEPTION");
			pw.println("<br><br>");
		}
	}


	private void testCase004()
	{
		try
		{
			s.execute("create table a가ds가가ss(name varchar(20))" );
			pw.println("Table Created   4 over");
			pw.println("<br><br>");
		}
		catch(Exception u)
		{
			u.printStackTrace();
			pw.println("4 EXCEPTION");
			pw.println("<br><br>");
		}	
	}

	private void testCase005()
	{
		try
		{
			s.execute("insert into 가가가 values ('EngValue')");
			pw.println("Table Created  5 over");
			pw.println("<br><br>");
		}
		catch(Exception u)
		{
			u.printStackTrace();
			pw.println("EXCEPTION TESTCASE 005");
			pw.println("<br><br>");
		}
	}

	private void testCase006()
	{
		try
		{
			s.execute("insert into 가가가 values ('가가가가가가')");
			pw.println("Table Created 6 over ");
			pw.println("<br><br>");
		}
		catch(Exception u)
		{
			u.printStackTrace();
			pw.println("6 EXCEPTION");
			pw.println("<br><br>");
		}
	}


	private void testCase007()
	{
		try
		{
			s.execute("insert into 가가가 values ('sss가d가f가s')");
			pw.println("Table Created   7 over ");
			pw.println("<br><br>");
		}
		catch(Exception u)
		{	
			u.printStackTrace();
			pw.println("7 EXCEPTION");
			pw.println("<br><br>");
		}
	}

	private void testCase008()
	{
		try
		{
			s.execute("insert into aaaa values ('sss가d가f가ssss가d가f가ssss가d가f가ssss가d가f가ssss가d가f가ssss가d가f가ssss가d가f가ssss가d가f가ssss가d가f가ssss가d가f가ssss가d가f가ssss가d가f가ssss가d가f가ssss가d가f가ssss가d가f가ssss가d가f가ssss가d가f가ssss가d가f가ssss가d가f가ssss가d가f가ssss가d가f가ssss가d가f가ssss가d가f가ssss가d가f가s')");
			pw.println("Table Created   8 over ");
			pw.println("<br><br>");
		}
		catch(Exception u)
		{
			u.printStackTrace();
			pw.println("8 EXCEPTION");
			pw.println("<br><br>");
		}	
	}

	private void testCase009()
	{
		try
		{
			s.execute("create table adss(가가가가 varchar(20))" );
			s.execute("insert into adss values('sss')");
			pw.println("Table Created   9 over");
			pw.println("<br><br>");
		}
		catch(Exception u)
		{   
			u.printStackTrace();
			pw.println("9 EXCEPTION");
			pw.println("<br><br>");
		}
	}

	private void testCase010()
	{
		try
		{
			s.execute("insert into adss values ('가가가')");
			pw.println("Table Created   10 over ");
			pw.println("<br><br>");
		}
		catch(Exception u)
		{
			u.printStackTrace();
			pw.println("10 EXCEPTION");
			pw.println("<br><br>");
		}
	}

	private void testCase011()
	{
		try
		{
			ResultSet rss1 =   s.executeQuery("select name from 가가가 where name like 'EngValue'");
			while(rss1.next())
			{
				pw.println("TESTCASE 11 ELEMENT : "+rss1.next());                
				pw.println("<br><br>");
			}
			pw.println("11 OVER OVER");
			pw.println("<br><br>");
		}
		catch(Exception i)
		{
			i.printStackTrace();
			pw.println("11 EXCEPTION");
			pw.println("<br><br>");
		}
	}

	private void testCase012()
	{
		try
		{
			ResultSet rss2 =   s.executeQuery("select name from 가가가 where name like '가가가가가가'");
			while(rss2.next())
			{
				pw.println("TESTCASE 12 ELEMENT : "+rss2.next());
				pw.println("<br><br>");
			}
		pw.println("12 OVER OVER");
		pw.println("<br><br>");
		}
		catch(Exception k)
		{
			k.printStackTrace();
		}
	}



	private void testCase013()
	{
		try
		{
			ResultSet rss3 =   s.executeQuery("select 가가가가 from adss where name like 'sss'");
			while(rss3.next())
			{
				pw.println("TESTCASE 13 ELEMENT : "+rss3.next());
				pw.println("<br><br>");
			}
			pw.println("13 OVER OVER");
			pw.println("<br><br>");
		}
		catch(Exception i)
		{
			i.printStackTrace();
			pw.println("13 EXCEPTION");
			pw.println("<br><br>");
		}
	}


	private void testCase014()
	{
		try
		{
			ResultSet rss4 =   s.executeQuery("select 가가가가 from adss where name like '가가가'");
			while(rss4.next())                                                  
			{
				pw.println("TESTCASE 14 ELEMENT : "+rss4.next());
				pw.println("<br><br>");
			}
			pw.println("14 OVER OVER");
			pw.println("<br><br>");
		}
		catch(Exception i)
		{
			i.printStackTrace();
			pw.println("14 EXCEPTION");
			pw.println("<br><br>");
		}
	}
	
	private void testCase015()
	{
		try
		{
			s.executeUpdate("update aaaa set name='가가가가가가' where name like 'bbb'");
			pw.println("TESTCASE 15 UPDATION OVER OVER");
			pw.println("<br><br>");
		}
		catch(Exception ds)
		{
			ds.printStackTrace();
			pw.println("15 EXCEPTION");
			pw.println("<br><br>");
		}
	}

	private void testCase016()
	{
		try
		{
			s.executeUpdate("update aaaa set name='ssss' where name like '청청청'");
			pw.println("TESTCASE 16 UPDATION OVER OVER");
			pw.println("<br><br>");
		}
		catch(Exception ds)
		{
			ds.printStackTrace();
			pw.println("16 EXCEPTION");
			pw.println("<br><br>");
		}
	}

	private void testCase017()
	{
		try
		{
			s.executeUpdate("update aaaa set name='sssss' where name like '청청청청'");
			pw.println("TESTCASE 17 UPDATION OVER OVER");
			pw.println("<br><br>");
		}
		catch(Exception ds)
		{
			ds.printStackTrace();
			pw.println("17 EXCEPTION");
			pw.println("<br><br>");
		}
	}
	

	private void testCase018()
	{
		try
		{
			s.executeUpdate("update adss set 가가가가='가가가가가가' where name like '가가가'");
			pw.println("TESTCASE 18 UPDATION OVER OVER");
			pw.println("<br><br>");
		}
		catch(Exception ds)
		{
			ds.printStackTrace();
			pw.println("18 EXCEPTION");
			pw.println("<br><br>");
		}
	}

	private void testCase019()
	{
		try
		{
			s.executeUpdate("delete from 가가가'");
			pw.println("UPDATION OVER TESTCASE 19 19");
			pw.println("<br><br>");
		}
		catch(Exception i)
		{
			i.printStackTrace();                                                  
			pw.println("EXCEPTION 19");
			pw.println("<br><br>");
		}
	}

	private void testCase020()
	{
		try
		{
			s.executeUpdate("delete from aaaa where name like '랄랄랄'");
			pw.println("UPDATION OVER TESTCASE 20 20");
			pw.println("<br><br>");
		}
		catch(Exception i)
		{	
			i.printStackTrace();
			pw.println("EXCEPTION 20");
			pw.println("<br><br>");
		}
	}



	private void testCase021()
	{
		try
		{
			s.executeUpdate("delete from adss where 가가가가 like '%가%'");
			pw.println("UPDATION OVER TESTCASE 21 21");
			pw.println("<br><br>");
		}
		catch(Exception i)
		{
			i.printStackTrace();
			pw.println("EXCEPTION 21");      
			pw.println("<br><br>");
		}
	}
}






																																																																																																																																																																																																																																																																																																																																													
																																																																																																																																															

																																																																					 
