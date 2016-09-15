package test;

import com.adventnet.nms.store.DBSchedulerVector;
import com.adventnet.nms.store.NmsStorageException;
import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.util.RunProcessInterface;
import com.adventnet.nms.store.relational.RelationalAPI;
import com.adventnet.management.transaction.ConnectionPool;
import com.adventnet.management.transaction.TransactionAPI;

import javax.servlet.GenericServlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Vector;

public class TestDBSch implements RunProcessInterface
{
	private Connection connection = null ;
	private Statement statement = null ;
	private RelationalAPI relapi = null;
	private ConnectionPool connectionpool = null;
	private TransactionAPI t = null;

	public void callMain(String args[])
	{                                      
		try
		{
			relapi = NmsUtil.relapi;
			connectionpool = relapi.getConnectionPool();
			connection = connectionpool.getConnection();
			statement = connection.createStatement();
			t = relapi.getTransactionAPI();
			long time1 = System.currentTimeMillis();
			callMethods();
			long time2 = System.currentTimeMillis();
			System.out.println("Tested All the cases in the TestPlan sf_strg_dbsch");
			System.out.println("Time taken to test : "+(time2-time1));
		}
		catch(Exception q)
		{
			System.out.println("EXCEPTION in method ShutDown!->RajeshD : "+q);
			q.printStackTrace();
		}
	}

	private void dropTables()
	{            
		try
		{
			statement.executeUpdate("drop table rajeshtable100");
		}
		catch(Exception j)
		{
			j.printStackTrace();
		}
		try
		{
			statement.executeUpdate("drop table rajeshtable101");
		}
		catch(Exception j)
		{
			j.printStackTrace();
		}
	    try
		{
			statement.executeUpdate("drop table rajeshtable102");
		}
		catch(Exception j)
		{
			j.printStackTrace();
		}
		try
		{
			statement.executeUpdate("drop table rajeshtable102_1");
		}
		catch(Exception j)
		{
			j.printStackTrace();
		}
		try
		{
			statement.executeUpdate("drop table rajeshtable103");
		}
		catch(Exception j)
		{
			j.printStackTrace();
		}
		try
		{
			statement.executeUpdate("drop table rajeshtable104");
		}
		catch(Exception j)
		{
			j.printStackTrace();
		}
		try
		{
			statement.executeUpdate("drop table rajeshtable105");
		}
		catch(Exception j)
		{
			j.printStackTrace();
		}

		try
		{
			statement.executeUpdate("drop table rajeshtable106");
		}
		catch(Exception j)
		{
			j.printStackTrace();
		}
		try
		{
			statement.executeUpdate("drop table rajeshtable107");
		}
		catch(Exception j)
		{
			j.printStackTrace();
		}
		try
		{
			statement.executeUpdate("drop table rajeshtable108");
		}
		catch(Exception j)
		{
			j.printStackTrace();
		}
		try
		{
			statement.executeUpdate("drop table rajeshtable109");
		}
		catch(Exception j)
		{
			j.printStackTrace();
		}
		try
		{
			statement.executeUpdate("drop table rajeshtable94");
		}
		catch(Exception j)
		{
			j.printStackTrace();
		}
		try
		{
			statement.executeUpdate("drop table rajeshtable95");
		}
		catch(Exception j)
		{
			j.printStackTrace();
		}
		try
		{
			statement.executeUpdate("drop table rajeshtable97");
		}
		catch(Exception j)
		{
			j.printStackTrace();
		}
		try
		{
			statement.executeUpdate("drop table rajeshtable98");
		}
		catch(Exception j)
		{
			j.printStackTrace();
		}
		try
		{
			statement.executeUpdate("drop table rajeshtable99 ");
		}
		catch(Exception j)
		{
			j.printStackTrace();
		}
 
	}

	public void shutDown()
	{
        dropTables();
        try
        {
            statement.close();
            System.out.println("Closed the statement used to validate the sf_strg_dbsch ");
        }
        catch(Exception m)
        {
            m.printStackTrace();
            System.out.println("Exception while closing statement : "+m);
        }

	}

	public boolean isInitialized()
	{
		return true;
	}

	private void callMethods()
	{
		 testCase94();
		 testCase95();
		 testCase97();
		 testCase98();
		 testCase99();
		 testCase100();
		 testCase101();
		 testCase102();
		 testCase102_1();
		 testCase103();
		 testCase104();
		 testCase105();
		 testCase106();
		 testCase107();
		 testCase108();
		 testCase109();
	}
	
	private void testCase94()
	{
		DBSchedulerVector d = null;
		ResultSet rs = null;
		String s = null;
		try
		{
			statement.executeUpdate("create table rajeshtable94(VALUESTRING varchar(20), TIMEVAL int)");
			d = new DBSchedulerVector("rajeshtable94",null,false);
			d.addElement(111,"element1");
			rs = statement.executeQuery("select * from rajeshtable94");
			rs.next();
			s = rs.getString(1);
			if(s.equals("element1"))
			{
				System.out.println("TESTCASE 094 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 094 S : "+s);
			}
		}
		catch(Exception l)
		{
			l.printStackTrace();
		}
		finally
		{
			try
			{
				rs.close();
			}
			catch(Exception f)
			{
				f.printStackTrace();
			}
			rs = null;
			s = null;
			d = null;
		}
	}

	private void testCase95()
	{
		DBSchedulerVector d = null;
		ResultSet rs = null;
		String s = null;
		try
		{
			d = new DBSchedulerVector("rajeshtable95",null,true);
			d.addElement(111,"element1");
			rs = statement.executeQuery("select * from rajeshtable95");
			rs.next();
			s = rs.getString(1);
			if(s.equals("element1"))
			{
				System.out.println("TESTCASE 095 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 095 S : "+s);
			}
		}
		catch(Exception l)
		{
			l.printStackTrace();
		}
		finally
		{
			try
			{
				rs.close();
			}
			catch(Exception f)
			{
				f.printStackTrace();
			}
			rs = null;
			s = null;
			d = null;
		}
	}
	
	
	private void testCase97()
	{
		DBSchedulerVector d = null;
		ResultSet rs = null;
		String s = null;
		try
		{
			d = new DBSchedulerVector("rajeshtable97",null,true);
			d.addElement(111,"element1");
			rs = statement.executeQuery("select * from rajeshtable97");
			rs.next();
			s = rs.getString(1);
			int size = d.size();
			if( (s.equals("element1")) && (size==1))
			{
				System.out.println("TESTCASE 097 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 097 S : "+s);
				System.out.println("SIZE : "+size);
			}
		}
		catch(Exception l)
		{
			l.printStackTrace();
		}
		finally
		{
			try
			{
				rs.close();
			}
			catch(Exception f)
			{
				f.printStackTrace();
			}
			rs = null;
			s = null;
			d = null;
		}
	}
	
	private void testCase98()
	{
		DBSchedulerVector d = null;
		ResultSet rs = null;
		String s = null;
		try
		{
			d = new DBSchedulerVector("rajeshtable98",null,true);
			d.addElement(111,"element1");
			d.updateElement(122,"element1");
			rs = statement.executeQuery("select VALUESTRING from rajeshtable98 where TIMEVAL=122");
			rs.next();
			s = rs.getString(1);
			if(s.equals("element1"))
			{
				System.out.println("TESTCASE 098 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 098 S : "+s);
			}
		}
		catch(Exception l)
		{
			l.printStackTrace();
		}
		finally
		{
			try
			{
				rs.close();
			}
			catch(Exception f)
			{
				f.printStackTrace();
			}
			rs = null;
			s = null;
			d = null;
		}
	}
	
	private void testCase99()
	{
		DBSchedulerVector d = null;
		try
		{
			d = new DBSchedulerVector("rajeshtable99",null,true);
			int size1 = d.size();
			for(int i=1;i<111;i++)
			{
				d.addElement(111,"element"+i);
			}
			int size2 = d.size();
			if( (size1==0) && (size2==110) )
			{
				System.out.println("TESTCASE 099 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 099 -> FAILED");
				System.out.println("TESTCASE 099 : SIZE1 : "+size1);
				System.out.println("TESTCASE 099 : SIZE2 : "+size2);
			}
		}
		catch(Exception l)
		{
			l.printStackTrace();
		}
		finally
		{
			d = null;
		}
	}
	
	private void testCase100()
	{
		DBSchedulerVector d = null;
		try
		{
			d = new DBSchedulerVector("rajeshtable100",null,true);
			boolean flag1 = d.isEmpty();
			d.addElement(111,"element1");
			boolean flag2 = d.isEmpty();
			if( (flag1) && (!flag2) )
			{
				System.out.println("TESTCASE 100 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 100 -> FAILED");
				System.out.println("TESTCASE 100 FLAG1 : "+flag1);
				System.out.println("TESTCASE 100 FLAG2 : "+flag2);
			}
		}
		catch(Exception l)
		{
			l.printStackTrace();
		}
		finally
		{
			d = null;
		}
	}

	private void testCase101()
	{
		DBSchedulerVector d = null;
		ResultSet rs = null;
		try
		{
			d = new DBSchedulerVector("rajeshtable101",null,true);
			for(int i=1;i<111;i++)
			{
				d.addElement(111,"element"+i);
			}
			d.removeAllElements();
			rs = statement.executeQuery("select count(*) from rajeshtable101");
			rs.next();
			int size1 = rs.getInt(1);
			int size2 = d.size();
			if( (size1==0) && (size2==0) )
			{
				System.out.println("TESTCASE 101 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 101 -> FAILED");
				System.out.println("TESTCASE 101 : SIZE1 : "+size1);
				System.out.println("TESTCASE 101 : SIZE2 : "+size2);
			}
		}
		catch(Exception l)
		{
			l.printStackTrace();
		}
		finally
		{
			try
			{
				rs.close();
			}
			catch(Exception f)
			{
				f.printStackTrace();
			}
			rs = null;
			d = null;
		}
	}

	private void testCase102()
	{
		DBSchedulerVector d = null;
		ResultSet rs = null;
		try
		{
			d = new DBSchedulerVector("rajeshtable102",null,true);
			d.addElement(111,"element1");
			d.addElement(122,"element2");
			d.removeElement("element1");
			rs = statement.executeQuery("select count(*) from rajeshtable102");
			rs.next();
			int size1 = rs.getInt(1);
			int size2 = d.size();
			if((size1==1) && (size2==1))
			{
				System.out.println("TESTCASE 102 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 102 -> FAILED");
				System.out.println("TESTCASE 102 SIZE1 : "+size1);
				System.out.println("TESTCASE 102 SIZE2 : "+size2);
			}
		}
		catch(Exception l)
		{
			l.printStackTrace();
		}
		finally
		{
			try
			{
				rs.close();
			}
			catch(Exception f)
			{
				f.printStackTrace();
			}
			rs = null;
			d = null;
		}
	}

	private void testCase102_1()
	{
		DBSchedulerVector d = null;
		try
		{
			d = new DBSchedulerVector("rajeshtable102_1",null,true);
			d.addElement(111,"element1");
			d.removeElement("element1111");
			System.out.println("TESTCASE 102.1 -> FAILED");
			System.out.println("TESTCASE 102.1 : No Exception thrown");
		}
		catch(Exception u)
		{
			u.printStackTrace();
			int size = d.size();
			if(size==1)
			{
				System.out.println("TESTCASE 102.1 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 102.1 -> FAILED");
				System.out.println("TESTCASE 102.1 SIZE : "+size);
			}
		}
		finally
		{
			d = null;
		}
	}

	private void testCase103()
	{
		DBSchedulerVector d = null;
		ResultSet rs = null;
		try
		{
			d = new DBSchedulerVector("rajeshtable103",null,true);
			for(int i=1;i<111;i++)
			{
				d.addElement(111,"element"+i);
			}
			d.reinitialize();
			rs = statement.executeQuery("select count(*) from rajeshtable103");
			rs.next();
			int size1 = rs.getInt(1);
			int size2 = d.size();
			if( (size1==0) && (size2==0) )
			{
				System.out.println("TESTCASE 103 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 103 -> FAILED");
				System.out.println("TESTCASE 103 : SIZE1 : "+size1);
				System.out.println("TESTCASE 103 : SIZE2 : "+size2);
			}
		}
		catch(Exception l)
		{
			l.printStackTrace();
		}
		finally
		{
			try
			{
				rs.close();
			}
			catch(Exception f)
			{
				f.printStackTrace();
			}
			rs = null;
			d = null;
		}
	}


	private void testCase104()
	{
		DBSchedulerVector d = null;
		try
		{
			d = new DBSchedulerVector("rajeshtable104",null,true);
			d.addElement(111,"element1");
			d.addElement(112,"element2");
			d.addElement(113,"element3");
			d.addElement(114,"element4");
			d.addElement(115,"element5");
			long time = d.getMinElement(111);
			if(time==112)
			{
				System.out.println("TESTCASE 104 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 104 -> FAILED");
				System.out.println("TESTCASE 104 : TIME :"+time);
				
			}
		}
		catch(Exception l)
		{
			l.printStackTrace();
		}
		finally
		{
			d = null;
		}
	}

	private void testCase105()
	{
		DBSchedulerVector d = null;
		Vector v = null;
		Enumeration en = null;
		
		try
		{
			d = new DBSchedulerVector("rajeshtable105",null,true);
			d.addElement(111,"element1");
			d.addElement(112,"element2");
			d.addElement(113,"element3");
			d.addElement(114,"element4");
			d.addElement(115,"element5");
			v = d.getScheduledElements(111,5);
			en = v.elements();
			boolean flag = true;
			int k = 2;
			while(en.hasMoreElements())
			{
				Vector v1 = (Vector)en.nextElement(); 
				String temp = v1.toString();
				if(temp.indexOf("element"+k)<0)
				{
					flag = false;
				}
				k++;
			}
			if(flag)
			{
				System.out.println("TESTCASE 105 : PASSED");
			}
			else
			{
				System.out.println("TESTCASE 105 : FAILED");
			}
		}
		catch(Exception m)
		{
			m.printStackTrace();
		}
		finally
		{
			en = null;
			d = null;
			v = null;
		}
	}

	private void testCase106()
	{
		DBSchedulerVector d = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		String s1 = null;
		String s2 = null;
		try
		{
			d = new DBSchedulerVector("rajeshtable106",null,true);
			d.addElement(111,"element1");
			d.addElement(112,"element2");
			d.reduceTimeVal(10);
			rs1 = statement.executeQuery("select VALUESTRING from rajeshtable106 where TIMEVAL=101");
			rs1.next();
			s1 = rs1.getString(1);
			rs2 = statement.executeQuery("select VALUESTRING from rajeshtable106 where TIMEVAL=102");
			rs2.next();
			s2 = rs2.getString(1);
			if( (s1.equals("element1")) && (s2.equals("element2")))
			{
				System.out.println("TESTCASE 106 : PASSED");
			}
			else
			{
				System.out.println("TESTCASE 106 : FAILED");
				System.out.println("106 S1 : "+s1);
				System.out.println("106 S2 : "+s1);
			}
		}
		catch(Exception p)
		{
			p.printStackTrace();
		}
		finally
		{
			s1 = null;
			s2 = null;
			d = null;
			try
			{
				rs1.close();
			}
			catch(Exception k)
			{
				k.printStackTrace();
			}
			try
			{
				rs2.close();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			rs1 = null ; rs2 = null;
		}
	}
	
	private void testCase107()
	{
		DBSchedulerVector d = null;
		Vector v = null;
		try
		{
			d = new DBSchedulerVector("rajeshtable107",null,true);
			d.addElement(111,"element1");
			d.addElement(112,"element2");
			d.addElement(113,"element3");
			d.addElement(114,"element4");
			d.addElement(115,"element5");
			v = d.getScheduledElements(111,5,1);
			int size = v.size();
			if(size == 1)
			{
				System.out.println("TESTCASE 107 : PASSED");
			}
			else
			{
				System.out.println("TESTCASE 107 : FAILED");
				System.out.println("TESTCASE 107 : SIZE : "+size);
			}
		}
		catch(Exception m)
		{
			m.printStackTrace();
		}
		finally
		{
			d = null;
			v = null;
		}
	}
			
	private void testCase108()
	{
		DBSchedulerVector d = null;
		ResultSet rs = null;
		try
		{
			d = new DBSchedulerVector("rajeshtable108",null,true);
			d.addElement(111,"element1");
			d.addElement(111,"element2");
			d.addElement(111,"element3");
			d.addElement(114,"element4");
			d.addElement(115,"element5");
			d.removeElement(111);
			rs = statement.executeQuery("select count(*) from rajeshtable108 where TIMEVAL=111");
			rs.next();
			int size1 = rs.getInt(1);
			int size2 = d.size();
			if( (size1 == 0) && (size2==2))
			{
				System.out.println("TESTCASE 108 : PASSED");
			}
			else
			{
				System.out.println("TESTCASE 108 : FAILED");
				System.out.println("TESTCASE 108 : SIZE1 : "+size1);
				System.out.println("TESTCASE 108 : SIZE2 : "+size2);
			}
		}
		catch(Exception m)
		{
			m.printStackTrace();
		}
		finally
		{
			d = null;
			try
			{
				rs.close();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			rs = null;
		}
		
	}

	private void testCase109()
	{
		DBSchedulerVector d = null;
		Vector v = null;
		Enumeration en1 = null;
		
		try
		{
			d = new DBSchedulerVector("rajeshtable109",null,true);
			d.addElement(111,"element1");
			d.addElement(112,"element2");
			d.addElement(113,"element3");
			d.addElement(114,"element4");
			d.addElement(115,"element5");
			en1 = d.elements(111);
			int k=2;
			boolean flag = true;
			while(en1.hasMoreElements())
			{
				Vector temp1 = (Vector)en1.nextElement();
				String str = temp1.toString();
				if(str.indexOf("element"+k)<0)
				{
					flag = false;
				}
				k++;
			}
			if(flag)
			{
				System.out.println("TESTCASE 109 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 109 -> FAILED");
			}
		}
		catch(Exception j)
		{
			j.printStackTrace();
		}
		finally
		{
			en1 = null;
			v = null;
			d = null;
		}
	}
	
}

