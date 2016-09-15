package test;

import com.adventnet.nms.store.DBVectortable;
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

public class TestDBVecttab implements RunProcessInterface 
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
			dropTables();
			System.out.println("Validated all the TestCases in sf_strg_dbvecttab TestPlan");
			System.out.println("Time taken to validate the TestCases in sf_strg_dbvecttab : "+(time2-time1));
			try
			{
				statement.close();
			}
			catch(Exception y)
			{
				y.printStackTrace();
			}
		}
		catch(Exception q)
		{
			q.printStackTrace();
		}
	}

	private void dropTables()
	{
		try
		{
			statement.executeUpdate("drop table rajeshtable155");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		try
		{
			statement.executeUpdate("drop table rajeshtable156");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		try
		{
			statement.executeUpdate("drop table rajeshtable158");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		try
		{
			statement.executeUpdate("drop table rajeshtable159");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		try
		{
			statement.executeUpdate("drop table rajeshtable160");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		try
		{
			statement.executeUpdate("drop table rajeshtable161");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		try
		{
			statement.executeUpdate("drop table rajeshtable162");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		try
		{
			statement.executeUpdate("drop table rajeshtable163");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		try
		{
			statement.executeUpdate("drop table rajeshtable49");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		try
		{
			statement.executeUpdate("drop table rajeshtable50");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		try
		{
			statement.executeUpdate("drop table rajeshtable51");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		try
		{
			statement.executeUpdate("drop table rajeshtable52");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		try
		{
			statement.executeUpdate("drop table rajeshtable53");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		try
		{
			statement.executeUpdate("drop table rajeshtable54");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		try
		{
			statement.executeUpdate("drop table rajeshtable55");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		try
		{
			statement.executeUpdate("drop table rajeshtable56");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		try
		{
			statement.executeUpdate("drop table rajeshtable57");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		try
		{
			statement.executeUpdate("drop table rajeshtable58");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		try
		{
			statement.executeUpdate("drop table rajeshtable59");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		try
		{
			statement.executeUpdate("drop table rajeshtable60");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		try
		{
			statement.executeUpdate("drop table rajeshtable61");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		try
		{
			statement.executeUpdate("drop table rajeshtable62");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		try
		{
			statement.executeUpdate("drop table rajeshtable63");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		try
		{
			statement.executeUpdate("drop table rajeshtable64");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}


	public void shutDown()
	{
	}

	public boolean isInitialized()
	{
		return true;
	}

	private void callMethods()
	{
		try
		{                                     
			testCase49();
			testCase50();
			testCase51();
			testCase52();
			testCase53();
			testCase54();
			testCase55();
			testCase56();
			testCase57();
			testCase58();
			testCase59();
			testCase60();
			testCase61();
			testCase62();
			testCase63();
			testCase64();

			
			testCase155();
			testCase156();
			testCase158();
			testCase159();
			testCase160();
			testCase161();
			testCase162();
			testCase163();
		}
		catch(Exception l)
		{
			l.printStackTrace();
		}
	}
	
	private void testCase49()
	{
		DBVectortable d = null;
		ResultSet rs = null;
		Vector v = null;
		try
		{
			statement.executeUpdate("create table rajeshtable49(KEYSTRING varchar(20) , VALUESTRING varchar(20))");
			d = new DBVectortable("rajeshtable49","KEYSTRING","VALUESTRING",false,null,false);
			v = new Vector();
			v.addElement("element1");
			d.put("key1",v);
			rs = statement.executeQuery("select VALUESTRING from rajeshtable49 where KEYSTRING like 'key1'");
			rs.next();
			String s = rs.getString(1);
			System.out.println("TESTCASE 49-RS : " + s);
			statement.executeUpdate("delete from rajeshtable49");
			if((s.equals("element1"))&&(d.containsKey("key1")))
			{
				System.out.println("TESTCASE 049 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 049 -> FAILED");
				System.out.println("TESTCASE 049 : s = "+s);
				System.out.println("TESTCASE 049 Flag : "+d.contains("element1"));
			}
		}
		catch(Exception l)
		{
			l.printStackTrace();
		}
		finally
		{
			v= null;
			try
			{
				rs.close();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			rs = null;
			d = null;
		}
	}
		
	private void testCase50()
	{
		DBVectortable d = null;
		ResultSet rs = null;
		Vector v = null;
		try
		{
			d = new DBVectortable("rajeshtable50","KEYSTRING","VALUESTRING",false,null,true);
			v = new Vector();
			v.addElement("element1");
			d.put("key1",v);
			rs = statement.executeQuery("select VALUESTRING from rajeshtable50 where KEYSTRING like 'key1'");
			rs.next();
			String s = rs.getString(1);
			System.out.println("TESTCASE 50 : RS : " + s);
			statement.executeUpdate("delete from rajeshtable50");
			if((s.equals("element1"))&&(d.containsKey("key1")))
			{
				System.out.println("TESTCASE 050 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 050 -> FAILED");
				System.out.println("TESTCASE 050 : s = "+s);
				System.out.println("TESTCASE 050 Flag : "+d.contains("element1"));
			}
		}
		catch(Exception l)
		{
			l.printStackTrace();
		}
		finally
		{
			v= null;
			try
			{
				rs.close();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			rs = null;
			d = null;
		}
	}
			
	private void testCase51()
	{
		DBVectortable d = null;
		Vector v = null;
		boolean flag1 = false;
		
		try
		{
			
			statement.executeUpdate("create table rajeshtable51(KEYSTRING varchar(20) , VALUESTRING varchar(20))");
			statement.executeUpdate("insert into rajeshtable51 values ('key1','element1')");
			d = new DBVectortable("rajeshtable51","KEYSTRING","VALUESTRING",true,null,false);
			flag1 = d.contains("element1");
			v = new Vector();
			v.addElement("element1");
			d.put("key1",v);
			System.out.println("TESTCASE 051 -> FAILED");
			System.out.println("TESTCASE 051 : No Exception thrown");
		}
		catch(Exception m)
		{
			m.printStackTrace();
			if(flag1)
			{
				System.out.println("TESTCASE 051 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 051 -> FAILED");
				System.out.println("TESTCASE 051 Flag : "+flag1);
			}
		}
		finally
		{
			v= null;
			d = null;
		}
	}

	private void testCase52()
	{
		DBVectortable d = null;
		Vector v = null;
		try
		{
			DBVectortable.setMaxValue(10);
			d = new DBVectortable("rajeshtable52","testKey","testValue",false,null,true);
			v = new Vector();
			v.addElement("element1");
			for(int i=1;i<26;i++)
			{
				d.put("key"+i,v);
			}
			statement.executeUpdate("delete from rajeshtable52");
			int tr = 0;int fal = 0;
			for(int i=1;i<26;i++)
			{
				if(d.containsKey("key"+i))
				{
					tr++;
				}
				else
				{
					fal++;
				}
			}
			if( (tr==10) && (fal==15))
			{
				System.out.println("TESTCASE 052 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 052 -> FAILED");
				System.out.println("TESTCASE 052 : tr : "+tr);
				System.out.println("TESTCASE 052 : fal : "+fal);
			}
		}
		catch(Exception y)
		{
			y.printStackTrace();
		}
		finally
		{
			d = null;
			v = null;
			DBVectortable.setMaxValue(1000);
		}
	}
				
	private void testCase53()
	{
		DBVectortable d = null;
		ResultSet rs = null;
		Vector v = null;
		try
		{
			d = new DBVectortable("rajeshtable53","KEYSTRING","VALUESTRING",false,null,true);
			v = new Vector();
			v.addElement("element1");
			d.put("key1",v);
			rs = statement.executeQuery("select VALUESTRING from rajeshtable53 where KEYSTRING like 'key1'");
			rs.next();
			String s = rs.getString(1);
			statement.executeUpdate("delete from rajeshtable53");
			if((s.equals("element1"))&&(d.containsKey("key1")))
			{
				System.out.println("TESTCASE 053 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 053 -> FAILED");
				System.out.println("TESTCASE 053 : s = "+s);
				System.out.println("TESTCASE 053 Flag : "+d.contains("element1"));
			}
		}
		catch(Exception l)
		{
			l.printStackTrace();
		}
		finally
		{
			v= null;
			try
			{
				rs.close();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			rs = null;
			d = null;
		}
	}

	private void testCase54()
	{
		DBVectortable d = null;
		Vector v = null;
		Vector v1 = null;
		Enumeration en = null;
		try
		{
			d = new DBVectortable("rajeshtable54","KEYSTRING","VALUESTRING",false,null,true);
			v = new Vector();
			v.addElement("element1");
			d.put("key1",v);
			v1 = d.get("key1");
			en = v1.elements();
			while(en.hasMoreElements())
			{
				if(en.nextElement().equals("element1"))
				{
					System.out.println("TESTCASE 054 -> PASSED");
				}
			}
		}
		catch(Exception l)
		{
			l.printStackTrace();
		}
		finally
		{
			v= null;
			d = null;
			v1 = null;
			en = null;
		}
	}

	private void testCase55()
	{
		DBVectortable d = null;
		ResultSet rs = null;
		Vector v = null;
		try
		{
			d = new DBVectortable("rajeshtable55","KEYSTRING","VALUESTRING",false,null,true);
			v = new Vector();
			v.addElement("element1");
			d.addPut("key1",v);
			rs = statement.executeQuery("select VALUESTRING from rajeshtable55 where KEYSTRING like 'key1'");
			rs.next();
			String s = rs.getString(1);
			statement.executeUpdate("delete from rajeshtable55");
			if((s.equals("element1"))&&(d.containsKey("key1")))
			{
				System.out.println("TESTCASE 055 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 055 -> FAILED");
				System.out.println("TESTCASE 055 : s = "+s);
				System.out.println("TESTCASE 055 Flag : "+d.contains("element1"));
			}
		}
		catch(Exception l)
		{
			l.printStackTrace();
		}
		finally
		{
			v= null;
			try
			{
				rs.close();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			rs = null;
			d = null;
		}
	}

	private void testCase56()
	{
		DBVectortable d = null;
		Vector v = null;
		try
		{
			d = new DBVectortable("rajeshtable56","testKey","testValue",false,null,true);
			int size1 = d.size();
			v = new Vector();
			v.addElement("element1");
			for(int i=1;i<26;i++)
			{
				d.put("key"+i,v);
			}
			int size2 = d.size();
			if((size1==0)&&(size2==25))
			{
				System.out.println("TESTCASE 056 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 056 -> FAILED");
				System.out.println("TESTCASE 056 : size1 : "+size1);
				System.out.println("TESTCASE 056 : size2 : "+size2);
			}
		}
		catch(Exception l)
		{
			l.printStackTrace();
		}
		finally
		{
			d = null;
			v = null;
		}
	}

	private void testCase57()
	{
		DBVectortable d = null;
		Vector v = null;
		try
		{
			d = new DBVectortable("rajeshtable57","testKey","testValue",false,null,true);
			boolean flag1 = d.isEmpty();
			v = new Vector();
			v.addElement("element1");
			d.put("key1",v);
			
			boolean flag2 = d.isEmpty();
			if((flag1)&&(!flag2))
			{
				System.out.println("TESTCASE 057 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 057 -> FAILED");
				System.out.println("TESTCASE 057 : flag1 : "+flag1);
				System.out.println("TESTCASE 057 : flag2 : "+flag2);
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

	private void testCase58()
	{
		DBVectortable d = null;
		ResultSet rs = null;
		Vector v = null;
		try
		{
			d = new DBVectortable("rajeshtable58","KEYSTRING","VALUESTRING",false,null,true);
			v = new Vector();
			v.addElement("element1");
			d.put("key1",v);
			d.clear();
			rs = statement.executeQuery("select count(*) from rajeshtable58");
			rs.next();
			int cnt = rs.getInt(1);
			boolean flag = d.contains("element1");
			if((cnt==0)&&(!flag))
			{
				System.out.println("TESTCASE 058 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 058 -> FAILED");
				System.out.println("TESTCASE 058 : cnt = "+cnt);
				System.out.println("TESTCASE 058 Flag : "+flag);
			}
		}
		catch(Exception l)
		{
			l.printStackTrace();
		}
		finally
		{
			v= null;
			try
			{
				rs.close();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			rs = null;
			d = null;
		}
	}

	private void testCase59()
	{
		DBVectortable d = null;
		ResultSet rs = null;
		Vector v = null;
		try
		{
			d = new DBVectortable("rajeshtable59","KEYSTRING","VALUESTRING",false,null,true);
			v = new Vector();
			v.addElement("element1");
			v.addElement("element2");
			d.put("key1",v);
			d.remove("key1","element1");
			rs = statement.executeQuery("select count(*) from rajeshtable59");
			rs.next();
			int cnt = rs.getInt(1);
			boolean flag = d.contains("element1");
			if((cnt==1)&&(!flag))
			{
				System.out.println("TESTCASE 059 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 059 -> FAILED");
				System.out.println("TESTCASE 059 : cnt = "+cnt);
				System.out.println("TESTCASE 059 Flag : "+flag);
			}
		}
		catch(Exception l)
		{
			l.printStackTrace();
		}
		finally
		{
			v= null;
			try
			{
				rs.close();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			rs = null;
			d = null;
		}
	}

	private void testCase60()
	{
		DBVectortable d = null;
		ResultSet rs = null;
		Vector v = null;
		try
		{
			d = new DBVectortable("rajeshtable60","KEYSTRING","VALUESTRING",false,null,true);
			v = new Vector();
			v.addElement("element1");
			v.addElement("element2");
			d.put("key1",v);
			d.remove("key1");
			rs = statement.executeQuery("select count(*) from rajeshtable60");
			rs.next();
			int cnt = rs.getInt(1);
			boolean flag = d.contains("element1");
			if((cnt==0)&&(!flag))
			{
				System.out.println("TESTCASE 060 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 060 -> FAILED");
				System.out.println("TESTCASE 060 : cnt = "+cnt);
				System.out.println("TESTCASE 060 Flag : "+flag);
			}
		}
		catch(Exception l)
		{
			l.printStackTrace();
		}
		finally
		{
			v= null;
			try
			{
				rs.close();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			rs = null;
			d = null;
		}
	}

	private void testCase61()
	{
		DBVectortable d = null;
		Vector v = null;
		try
		{
			d = new DBVectortable("rajeshtable61","KEYSTRING","VALUESTRING",false,null,true);
			boolean flag1 = d.contains("element1");
			v = new Vector();
			v.addElement("element1");
			d.put("key1",v);
			boolean flag2 = d.contains("element1");
			if( (!flag1) && (flag2) )
			{
				System.out.println("TESTCASE 061 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 061 -> FAILED");
				System.out.println("TESTCASE 061 : flag1 "+flag1);
				System.out.println("TESTCASE 061 : flag2 "+flag2);
			}
		}
		catch(Exception l)
		{
			l.printStackTrace();
		}
		finally
		{
			v= null;
			d = null;
		}
	}

	private void testCase62()
	{
		DBVectortable d = null;
		Vector v = null;
		try
		{
			d = new DBVectortable("rajeshtable62","KEYSTRING","VALUESTRING",false,null,true);
			boolean flag1 = d.containsKey("key1");
			v = new Vector();
			v.addElement("element1");
			d.put("key1",v);
			boolean flag2 = d.containsKey("key1");
			if( (!flag1) && (flag2) )
			{
				System.out.println("TESTCASE 062 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 062 -> FAILED");
				System.out.println("TESTCASE 062 : flag1 "+flag1);
				System.out.println("TESTCASE 062 : flag2 "+flag2);
			}
		}
		catch(Exception l)
		{
			l.printStackTrace();
		}
		finally
		{
			v= null;
			d = null;
		}
	}

	private void testCase63()
	{
		DBVectortable d = null;
		Vector v = null;
		Enumeration en = null;
		try
		{
			d = new DBVectortable("rajeshtable63","KEYSTRING","VALUESTRING",false,null,true);
			v = new Vector();
			v.addElement("element1");
			d.put("key1",v);
			en = d.keys();
			while(en.hasMoreElements())
			{
				if(en.nextElement().equals("key1"))
				{
					System.out.println("TESTCASE 063 -> PASSED");
				}
				else
				{
					System.out.println("TESTCASE 063 -> FAILED");
				}
			}
		}
		catch(Exception l)
		{
			l.printStackTrace();
		}
		finally
		{
			v= null;
			d = null;
			en = null;
		}
	}

	private void testCase64()
	{
		DBVectortable d = null;
		Vector v = null;
		Enumeration en = null;
		try
		{
			d = new DBVectortable("rajeshtable64","KEYSTRING","VALUESTRING",false,null,true);
			v = new Vector();
			v.addElement("element1");
			d.put("key1",v);
			en = d.elements();
			while(en.hasMoreElements())
			{
				if(en.nextElement().equals("element1"))
				{
					System.out.println("TESTCASE 064 -> PASSED");
				}
				else
				{
					System.out.println("TESTCASE 064 -> FAILED");
				}
			}
		}
		catch(Exception l)
		{
			l.printStackTrace();
		}
		finally
		{
			v= null;
			d = null;
			en = null;
		}
	}

	private void testCase155()
	{
		DBVectortable d = null;
		ResultSet rs = null;
		Vector v = null;
		try
		{
			d = new DBVectortable("rajeshtable155","KEYSTRING","VALUESTRING",false,null,true);
			try
			{
				t.begin();
			}
			catch(Exception m)
			{
				m.printStackTrace();
			}
			v = new Vector();
			v.addElement("element1");
			d.put("key1",v);
			try
			{
				t.commit();
			}
			catch(Exception k)
			{
				k.printStackTrace();
			}
			rs = statement.executeQuery("select VALUESTRING from rajeshtable155 where KEYSTRING like 'key1'");
			rs.next();
			String s = rs.getString(1);
			statement.executeUpdate("delete from rajeshtable155");
			if((s.equals("element1"))&&(d.containsKey("key1")))
			{
				System.out.println("TESTCASE 155 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 155 -> FAILED");
				System.out.println("TESTCASE 155 : s = "+s);
				System.out.println("TESTCASE 155 Flag : "+d.containsKey("key1"));
			}
		}
		catch(Exception l)
		{
			l.printStackTrace();
		}
		finally
		{
			v= null;
			try
			{
				rs.close();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			rs = null;
			d = null;
		}
	}

	private void testCase156()
	{
		DBVectortable d = null;
		ResultSet rs = null;
		Vector v = null;
		try
		{
			d = new DBVectortable("rajeshtable156","KEYSTRING","VALUESTRING",false,null,true);
			try
			{
				t.begin();
			}
			catch(Exception m)
			{
				m.printStackTrace();
			}
			v = new Vector();
			v.addElement("element1");
			d.put("key1",v);
			try
			{
				t.rollback("test rollback");
			}
			catch(Exception k)
			{
				k.printStackTrace();
			}
			rs = statement.executeQuery("select count(*) from rajeshtable156");
			rs.next();
			int cnt  = rs.getInt(1);
			if((cnt==0)&&(!d.containsKey("key1")))
			{
				System.out.println("TESTCASE 156 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 156 -> FAILED");
				System.out.println("TESTCASE 156 : cnt = "+cnt);
				System.out.println("TESTCASE 156 Flag : "+d.containsKey("key1"));
			}
		}
		catch(Exception l)
		{
			l.printStackTrace();
		}
		finally
		{
			v= null;
			try
			{
				rs.close();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			rs = null;
			d = null;
		}
	}

	private void testCase158()
	{
		DBVectortable d = null;
		ResultSet rs = null;
		Vector v = null;
		try
		{
			d = new DBVectortable("rajeshtable158","KEYSTRING","VALUESTRING",false,null,true);
			try
			{
				t.begin();
			}
			catch(Exception m)
			{
				m.printStackTrace();
			}
			v = new Vector();
			v.addElement("element1");
			//v.addElement("element2");
			d.addPut("key1",v);
			try
			{
				t.commit();
			}
			catch(Exception k)
			{
				k.printStackTrace();
			}
			rs = statement.executeQuery("select count(*) from rajeshtable158 where KEYSTRING like 'key1'");
			rs.next();
			int cnt = rs.getInt(1);
			statement.executeUpdate("delete from rajeshtable158");
			System.out.println("COUNT : " + cnt);
			if((cnt==1)&&(d.containsKey("key1")))
			{
				System.out.println("TESTCASE 158 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 158 -> FAILED");
				System.out.println("TESTCASE 158 : cnt  = "+cnt);
				System.out.println("TESTCASE 158 Flag : "+d.containsKey("key1"));
			}
		}
		catch(Exception l)
		{
			l.printStackTrace();
		}
		finally
		{
			v= null;
			try
			{
				rs.close();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			rs = null;
			d = null;
		}
	}

	private void testCase159()
	{
		DBVectortable d = null;
		ResultSet rs = null;
		Vector v = null;
		try
		{
			d = new DBVectortable("rajeshtable159","KEYSTRING","VALUESTRING",false,null,true);
			try
			{
				t.begin();
			}
			catch(Exception m)
			{
				m.printStackTrace();
			}
			v = new Vector();
			v.addElement("element1");
			//v.addElement("element2");
			d.addPut("key1",v);
			try
			{
				t.rollback("test rollback");
			}
			catch(Exception k)
			{
				k.printStackTrace();
			}
			rs = statement.executeQuery("select count(*) from rajeshtable159 where KEYSTRING like 'key1'");
			rs.next();
			int cnt = rs.getInt(1);
			if((cnt==0)&&(!d.containsKey("element1")))
			{
				System.out.println("TESTCASE 159 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 159 -> FAILED");
				System.out.println("TESTCASE 159 : cnt  = "+cnt);
				System.out.println("TESTCASE 159 Flag : "+d.containsKey("key1"));
			}
		}
		catch(Exception l)
		{
			l.printStackTrace();
		}
		finally
		{
			v= null;
			try
			{
				rs.close();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			rs = null;
			d = null;
		}
	}

	private void testCase160()
	{
		DBVectortable d = null;
		ResultSet rs = null;
		Vector v = null;
		try
		{
			d = new DBVectortable("rajeshtable160","KEYSTRING","VALUESTRING",false,null,true);
			v = new Vector();
			v.addElement("element1");
			d.put("key1",v);
			try
			{
				t.begin();
			}
			catch(Exception k)
			{
				k.printStackTrace();
			}
			d.remove("key1");
			try
			{
				t.commit();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			rs = statement.executeQuery("select count(*) from rajeshtable160");
			rs.next();
			int cnt = rs.getInt(1);
			boolean flag = d.containsKey("key1");
			if((cnt==0)&&(!flag))
			{
				System.out.println("TESTCASE 160 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 160 -> FAILED");
				System.out.println("TESTCASE 160 : cnt = "+cnt);
				System.out.println("TESTCASE 160 Flag : "+flag);
			}
		}
		catch(Exception l)
		{
			l.printStackTrace();
		}
		finally
		{
			v= null;
			try
			{
				rs.close();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			rs = null;
			d = null;
		}
	}

	private void testCase161()
	{
		DBVectortable d = null;
		ResultSet rs = null;
		Vector v = null;
		try
		{
			d = new DBVectortable("rajeshtable161","KEYSTRING","VALUESTRING",false,null,true);
			v = new Vector();
			v.addElement("element1");
			d.put("key1",v);
			try
			{
				t.begin();
			}
			catch(Exception k)
			{
				k.printStackTrace();
			}
			d.remove("key1");
			try
			{
				t.rollback("test rollback");
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			rs = statement.executeQuery("select count(*) from rajeshtable161");
			rs.next();
			int cnt = rs.getInt(1);
			boolean flag = d.containsKey("key1");
			if((cnt==1)&&(flag))
			{
				System.out.println("TESTCASE 161 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 161 -> FAILED");
				System.out.println("TESTCASE 161 : cnt = "+cnt);
				System.out.println("TESTCASE 161 Flag : "+flag);
			}
		}
		catch(Exception l)
		{
			l.printStackTrace();
		}
		finally
		{
			v= null;
			try
			{
				rs.close();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			rs = null;
			d = null;
		}
	}

	private void testCase162()
	{
		DBVectortable d = null;
		ResultSet rs = null;
		Vector v = null;
		try
		{
			d = new DBVectortable("rajeshtable162","KEYSTRING","VALUESTRING",false,null,true);
			v = new Vector();
			v.addElement("element1");
			v.addElement("element2");
			d.put("key1",v);
			try
			{
				t.begin();
			}
			catch(Exception m)
			{
				m.printStackTrace();
			}
			d.remove("key1","element1");
			try
			{
				t.commit();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			rs = statement.executeQuery("select count(*) from rajeshtable162");
			rs.next();
			int cnt = rs.getInt(1);
			boolean flag = d.contains("element1");
			if((cnt==1)&&(!flag))
			{
				System.out.println("TESTCASE 162 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 162 -> FAILED");
				System.out.println("TESTCASE 162 : cnt = "+cnt);
				System.out.println("TESTCASE 162 Flag : "+flag);
			}
		}
		catch(Exception l)
		{
			l.printStackTrace();
		}
		finally
		{
			v= null;
			try
			{
				rs.close();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			rs = null;
			d = null;
		}
	}

	private void testCase163()
	{
		DBVectortable d = null;
		ResultSet rs = null;
		Vector v = null;
		try
		{
			d = new DBVectortable("rajeshtable163","KEYSTRING","VALUESTRING",false,null,true);
			v = new Vector();
			v.addElement("element1");
			v.addElement("element2");
			d.put("key1",v);
			try
			{
				t.begin();
			}
			catch(Exception m)
			{
				m.printStackTrace();
			}
			d.remove("key1","element1");
			try
			{
				t.rollback("test rollback");
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			rs = statement.executeQuery("select count(*) from rajeshtable163");
			rs.next();
			int cnt = rs.getInt(1);
			boolean flag = d.contains("element1");
			if((cnt==2)&&(flag))
			{
				System.out.println("TESTCASE 163 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 163 -> FAILED");
				System.out.println("TESTCASE 163 : cnt = "+cnt);
				System.out.println("TESTCASE 163 Flag : "+flag);
			}
		}
		catch(Exception l)
		{
			l.printStackTrace();
		}
		finally
		{
			v= null;
			try
			{
				rs.close();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			rs = null;
			d = null;
		}
	}
}
