package test;

import com.adventnet.nms.store.DBPropertytable;
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
import java.util.Properties;

public class TestDBProp implements RunProcessInterface
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
			System.out.println("Validated all TestCases of SF-STRG-DBProp : Time Taken : "+(time2-time1));
			dropTables();
			System.err.println("Dropped the Tables Created -> RajeshD");
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
		try{statement.executeUpdate("drop table rajeshtable125");}catch(Exception l){l.printStackTrace();}
		try{statement.executeUpdate("drop table rajeshtable126");}catch(Exception l){l.printStackTrace();}
		try{statement.executeUpdate("drop table rajeshtable128");}catch(Exception l){l.printStackTrace();}
		try{statement.executeUpdate("drop table rajeshtable129");}catch(Exception l){l.printStackTrace();}
		try{statement.executeUpdate("drop table rajeshtable34");}catch(Exception l){l.printStackTrace();}
		try{statement.executeUpdate("drop table rajeshtable35");}catch(Exception l){l.printStackTrace();}
		try{statement.executeUpdate("drop table rajeshtable36");}catch(Exception l){l.printStackTrace();}
		try{statement.executeUpdate("drop table rajeshtable37");}catch(Exception l){l.printStackTrace();}
		try{statement.executeUpdate("drop table rajeshtable38");}catch(Exception l){l.printStackTrace();}
		try{statement.executeUpdate("drop table rajeshtable39");}catch(Exception l){l.printStackTrace();}
		try{statement.executeUpdate("drop table rajeshtable40");}catch(Exception l){l.printStackTrace();}
		try{statement.executeUpdate("drop table rajeshtable41");}catch(Exception l){l.printStackTrace();}
		try{statement.executeUpdate("drop table rajeshtable42");}catch(Exception l){l.printStackTrace();}
		try{statement.executeUpdate("drop table rajeshtable43");}catch(Exception l){l.printStackTrace();}
		try{statement.executeUpdate("drop table rajeshtable44");}catch(Exception l){l.printStackTrace();}
		try{statement.executeUpdate("drop table rajeshtable45");}catch(Exception l){l.printStackTrace();}
		 try{statement.executeUpdate("drop table rajeshtable46");}catch(Exception l){l.printStackTrace();}
		  try{statement.executeUpdate("drop table rajeshtable47");}catch(Exception l){l.printStackTrace();}
		                 

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
			testCase34();
			testCase35();
			testCase36();
			testCase37();
			testCase38();
			testCase39();
			testCase40();
			testCase41();
			testCase42();
			testCase43();
			testCase44();
			testCase45();
			testCase46();
			testCase47();
			testCase125();
			testCase126();
			testCase128();
			testCase129(); 
		}
		catch(Exception l)
		{
			l.printStackTrace();
		}
	}
	
	private void testCase34()
	{
		DBPropertytable p = null;
		ResultSet rs = null;
		Properties prop1 = null;
		Properties prop2 = null;
		try
		{
			statement.executeUpdate("create table rajeshtable34(KEYSTRING varchar(20),PROPNAME varchar(20),PROPVAL varchar(20))");
			p = new DBPropertytable("rajeshtable34","KEYSTRING","PROPNAME","PROPVAL",false,null,false);
			prop1 = new Properties();
			prop1.put("name1","value1");
			p.put("key1",prop1);
			rs = statement.executeQuery("select PROPVAL from rajeshtable34 where KEYSTRING like 'key1' and PROPNAME like 'name1'");
			rs.next();
			String str1 = rs.getString(1);
			statement.executeUpdate("delete from rajeshtable34");
			prop2 = p.get("key1");
			if(prop2==null)
			{
				System.out.println("TESTCASE 034 -> FAILED(The get method returns null , the object is not added in the cache");
			}
			else
			{
				String str2 = (String)prop2.get("name1");
				if((str1.equals("value1"))&&(str2.equals("value1")))
				{
					System.out.println("TESTCASE 034 -> PASSED");
				}
				else
				{
					System.out.println("TESTCASE 034 -> FAILED");
					System.out.println("034 STR1 = "+str1);
					System.out.println("034 STR2 = "+str2);
				}
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
			catch(Exception m)
			{
				m.printStackTrace();
			}
			rs = null;
			prop1 = null;
			prop2 = null;
			p = null;
		}
	}
	
	private void testCase35()
	{
		DBPropertytable p = null;
		ResultSet rs = null;
		Properties prop1 = null;
		Properties prop2 = null;
		try
		{
			p = new DBPropertytable("rajeshtable35","KEYSTRING","PROPNAME","PROPVAL",false,null,true);
			prop1 = new Properties();
			prop1.put("name1","value1");
			p.put("key1",prop1);
			rs = statement.executeQuery("select PROPVAL from rajeshtable35 where KEYSTRING like 'key1' and PROPNAME like 'name1'");
			rs.next();
			String str1 = rs.getString(1);
			statement.executeUpdate("delete from rajeshtable35");
			prop2 = p.get("key1");
			if(prop2 == null)
			{
				System.out.println("TESTCASE 035 -> FAILED(The get method returns null, the object is not added in the cache)");
			}
			else
			{
				String str2 = (String)prop2.get("name1");
				if((str1.equals("value1"))&&(str2.equals("value1")))
				{
					System.out.println("TESTCASE 035 -> PASSED");
				}
				else
				{	
					System.out.println("TESTCASE 035 -> FAILED");
					System.out.println("035 STR1 = "+str1);
					System.out.println("035 STR2 = "+str2);
				}
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
			catch(Exception m)
			{
				m.printStackTrace();
			}
			rs = null;
			prop1 = null;
			prop2 = null;
			p = null;
		}
	}		

	private void testCase36()
	{
		DBPropertytable p = null;
		Properties prop1 = null;
		String str1 = null;
		try
		{
			statement.executeUpdate("create table rajeshtable36(KEYSTRING varchar(20),PROPNAME varchar(20),PROPVAL varchar(20))");
			statement.executeUpdate("insert into rajeshtable36 values('key1','name1','value1')");
			p = new DBPropertytable("rajeshtable36","KEYSTRING","PROPNAME","PROPVAL",true,null,false);
			prop1 = p.get("key1");
			str1 = (String)prop1.get("name1");
			p.put("key2",prop1);
			System.out.println("TESTCASE 036 -> FAILED(No Exception thrown - for readOnly - true)");
		}
		catch(Exception n)
		{
			n.printStackTrace();
			if(str1.equals("value1"))
			{
				System.out.println("TESTCASE 036 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 036 -> FAILED");
				System.out.println("036 STR1 = "+str1);
			}
		}
		finally
		{
			prop1 = null;
			p = null;
		}
	}

	private void testCase37()
	{
		DBPropertytable p = null;
		Properties prop1 = null;
		Properties prop2 = null;
		int cnt=0;
		try
		{
			p = new DBPropertytable("rajeshtable37","KEYSTRING","PROPNAME","PROPVAL",false,null,true);
			p.setMaxValue(10);
			prop1 = new Properties();
			prop1.put("name1","value1");
			for(int i=1;i<16;i++)
			{
			p.put("key"+i,prop1);
			}
			statement.executeUpdate("delete from rajeshtable37");
			for(int i=1;i<16;i++)
			{
				prop2 = p.get("key"+i);
				if(prop2==null)
				{
					cnt++;
				}
			}
			if((cnt==5) && (p.getMaxValue()==10))
			{
				System.out.println("TESTCASE 037 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 037 -> FAILED");
				System.out.println("037 CNT  = "+cnt);
				System.out.println("If the cnt =15 it means that no object is added in the database - testcase fails");
				System.out.println("037 getmaxvalue  = "+p.getMaxValue());
			}
		}
		catch(Exception l)
		{
			l.printStackTrace();
		}
		finally
		{
			prop1 = null;
			prop2 = null;
			p.setMaxValue(1000);
			p = null;
		}
	}		
	
	private void testCase38()
	{
		DBPropertytable p = null;
		ResultSet rs = null;
		Properties prop1 = null;
		Properties prop2 = null;
		try
		{
			p = new DBPropertytable("rajeshtable38","KEYSTRING","PROPNAME","PROPVAL",false,null,true);
			prop1 = new Properties();
			prop1.put("name1","value1");
			p.put("key1",prop1);
			rs = statement.executeQuery("select PROPVAL from rajeshtable38 where KEYSTRING like 'key1' and PROPNAME like 'name1'");
			rs.next();
			String str1 = rs.getString(1);
			statement.executeUpdate("delete from rajeshtable38");
			prop2 = p.get("key1");
			if(prop2 == null)
			{
				System.out.println("TESTCASE 038 -> FAILED(Object not added in the cache - get method returns null)");
			}
			else
			{
				String str2 = (String)prop2.get("name1");
				if((str1.equals("value1"))&&(str2.equals("value1")))
				{
					System.out.println("TESTCASE 038 -> PASSED");
				}
				else
				{
					System.out.println("TESTCASE 038 -> FAILED");
					System.out.println("038 STR1 = "+str1);
					System.out.println("038 STR2 = "+str2);
				}
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
			catch(Exception m)
			{
				m.printStackTrace();
			}
			rs = null;
			prop1 = null;
			prop2 = null;
			p = null;
		}
	}		

	private void testCase39()
	{
		DBPropertytable p = null;
		Properties prop1 = null;
		Properties prop2 = null;
		try
		{
			p = new DBPropertytable("rajeshtable39","KEYSTRING","PROPNAME","PROPVAL",false,null,true);
			prop1 = new Properties();
			prop1.put("name1","value1");
			p.put("key1",prop1);
			prop2 = p.get("key1");
			String str1 = (String)prop2.get("name1");
			if(str1.equals("value1"))
			{
				System.out.println("TESTCASE 039 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 039 -> FAILED");
				System.out.println("039 STR1 = "+str1);
			}
		}
		catch(Exception l)
		{
			l.printStackTrace();
		}
		finally
		{
			prop1 = null;
			prop2 = null;
			p = null;
		}
	}

	private void testCase40()
	{
		DBPropertytable p = null;
		Properties prop1 = null;
		int cnt;
		try
		{
			p = new DBPropertytable("rajeshtable40","KEYSTRING","PROPNAME","PROPVAL",false,null,true);
			int size1 = p.size();
			prop1 = new Properties();
			prop1.put("name1","value1");
			for(int i=1;i<16;i++)
			{
			p.put("key"+i,prop1);
			}
			if((p.size()==15) && (size1==0))
			{
				System.out.println("TESTCASE 040 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 040 -> FAILED");
				System.out.println("040 SIZE  = "+p.size());
				System.out.println("040 SIZE1 = "+size1);
			}
		}
		catch(Exception l)
		{
			l.printStackTrace();
		}
		finally
		{
			prop1 = null;
			p = null;
		}
	}		

	private void testCase41()
	{
		DBPropertytable p = null;
		Properties prop1 = null;
		try
		{
			p = new DBPropertytable("rajeshtable41","KEYSTRING","PROPNAME","PROPVAL",false,null,true);
			boolean flag1 = p.isEmpty();
			prop1 = new Properties();
			prop1.put("name1","value1");
			p.put("key1",prop1);
			boolean flag2 = p.isEmpty();
			if((flag1) && (!flag2))
			{
				System.out.println("TESTCASE 041 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 041 -> FAILED");
				System.out.println("041 FLAG1  = "+flag1);
				System.out.println("041 FLAG2 = "+flag2);
			}
		}
		catch(Exception l)
		{
			l.printStackTrace();
		}
		finally
		{
			prop1 = null;
			p = null;
		}
	}

	private void testCase42()
	{
		DBPropertytable p = null;
		ResultSet rs = null;
		Properties prop1 = null;
		Properties prop2 = null;
		try
		{
			p = new DBPropertytable("rajeshtable42","KEYSTRING","PROPNAME","PROPVAL",false,null,true);
			prop1 = new Properties();
			prop1.put("name1","value1");
			p.put("key1",prop1);
			p.clear();
			rs = statement.executeQuery("select count(*) from rajeshtable42");
			rs.next();
			int cnt1 = rs.getInt(1);
			prop2 = p.get("key1");
			if( (cnt1==0) && (prop2==null))
			{
				System.out.println("TESTCASE 042 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 042 -> FAILED");
				System.out.println("042 CNT = "+cnt1);
				System.out.println("042 prop2 = "+prop2);
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
			catch(Exception m)
			{
				m.printStackTrace();
			}
			rs = null;
			prop1 = null;
			prop2 = null;
			p = null;
		}
	}		
	
	private void testCase43()
	{
		DBPropertytable p = null;
		ResultSet rs = null;
		Properties prop1 = null;
		Properties prop2 = null;
		try
		{
			p = new DBPropertytable("rajeshtable43","KEYSTRING","PROPNAME","PROPVAL",false,null,true);
			prop1 = new Properties();
			prop1.put("name1","value1");
			p.put("key1",prop1);
			p.remove("key1");
			rs = statement.executeQuery("select count(*) from rajeshtable43");
			rs.next();
			int cnt1 = rs.getInt(1);
			prop2 = p.get("key1");
			if( (cnt1==0) && (prop2==null))
			{
				System.out.println("TESTCASE 043 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 043 -> FAILED");
				System.out.println("043 CNT = "+cnt1);
				System.out.println("043 prop2 = "+prop2);
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
			catch(Exception m)
			{
				m.printStackTrace();
			}
			rs = null;
			prop1 = null;
			prop2 = null;
			p = null;
		}
	}

	private void testCase44()
	{
		DBPropertytable p = null;
		Properties prop1 = null;
		Properties prop2 = null;
		try
		{
			p = new DBPropertytable("rajeshtable44","KEYSTRING","PROPNAME","PROPVAL",false,null,true);
			prop2 = new Properties();
			prop2.put("name1","value1");
			boolean flag1 = p.contains(prop2);
			prop1 = new Properties();
			prop1.put("name1","value1");
			p.put("key1",prop1);
			boolean flag2 = p.contains(prop2);
			if((!flag1) && (flag2))
			{
				System.out.println("TESTCASE 044 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 044 -> FAILED");
				System.out.println("044 FLAG1  = "+flag1);
				System.out.println("044 FLAG2 = "+flag2);
			}
		}
		catch(Exception l)
		{
			l.printStackTrace();
		}
		finally
		{
			prop1 = null;
			prop2 = null;
			p = null;
		}
	}

	private void testCase45()
	{
		DBPropertytable p = null;
		Properties prop1 = null;
		try
		{
			p = new DBPropertytable("rajeshtable45","KEYSTRING","PROPNAME","PROPVAL",false,null,true);
			boolean flag1 = p.containsKey("key1");
			prop1 = new Properties();
			prop1.put("name1","value1");
			p.put("key1",prop1);
			boolean flag2 = p.containsKey("key1");
			if((!flag1) && (flag2))
			{
				System.out.println("TESTCASE 045 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 045 -> FAILED");
				System.out.println("045 FLAG1  = "+flag1);
				System.out.println("045 FLAG2 = "+flag2);
			}
		}
		catch(Exception l)
		{
			l.printStackTrace();
		}
		finally
		{
			prop1 = null;
			p = null;
		}
	}

	private void testCase46()
	{
		DBPropertytable p = null;
		Properties prop1 = null;
		Enumeration en = null;
		try
		{
			p = new DBPropertytable("rajeshtable46","KEYSTRING","PROPNAME","PROPVAL",false,null,true);
			prop1 = new Properties();
			prop1.put("name1","value1");
			p.put("key1",prop1);
			en = p.keys();
			while(en.hasMoreElements())
			{
				if(en.nextElement().equals("key1"))
				{
					System.out.println("TESTCASE 046 -> PASSED");
				}
				else
				{
					System.out.println("TESTCASE 046 -> FAILED");
				}
			}
		}
		catch(Exception m)
		{
			m.printStackTrace();
		}
		finally
		{
			en = null;
			prop1 = null;
			p = null;
		}
	}

	private void testCase47()
	{
		DBPropertytable p = null;
		Properties prop1 = null;
		Properties prop2 = null;
		Enumeration en = null;
		try
		{
			p = new DBPropertytable("rajeshtable47","KEYSTRING","PROPNAME","PROPVAL",false,null,true);
			prop1 = new Properties();
			prop1.put("name1","value1");
			p.put("key1",prop1);
			System.out.println("CASE 47  Properties : " + p);
			en = p.elements();
			while(en.hasMoreElements())
			{
				prop2 = (Properties)en.nextElement();
				if(prop2==null)
				{
					System.out.println("TESTCASE 047 -> FAILED , Property object in Enumeration is null");
				}
				else
				{
					String temp = (String)prop2.get("name1");
					if(temp.equals("value1"))
					{
						System.out.println("TESTCASE 047 -> PASSED");
					}
					else
					{
						System.out.println("TESTCASE 047 -> FAILED");
					}
				}	
			}
		}
		catch(Exception m)
		{
			m.printStackTrace();
		}
		finally
		{
			en = null;
			prop1 = null;
			prop2 = null;
			p = null;
		}
	}

	private void testCase125()
	{
		DBPropertytable p = null;
		ResultSet rs = null;
		Properties prop1 = null;
		Properties prop2 = null;
		try
		{
			p = new DBPropertytable("rajeshtable125","KEYSTRING","PROPNAME","PROPVAL",false,null,true);
			prop1 = new Properties();
			prop1.put("name1","value1");
			try
			{
				t.begin();
			}
			catch(Exception m)
			{
				m.printStackTrace();
			}
			p.put("key1",prop1);
			try
			{
				t.commit();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			rs = statement.executeQuery("select PROPVAL from rajeshtable125 where KEYSTRING like 'key1' and PROPNAME like 'name1'");
			rs.next();
			String str1 = rs.getString(1);
			statement.executeUpdate("delete from rajeshtable125");
			prop2 = p.get("key1");
			System.out.println("Case 125 : PROPERTIES : " + prop2);
			if(prop2==null)
			{
				System.out.println("TESTCASE 125 -> FAILED(The method get returns null)");
			}
			else
			{
				String str2 = (String)prop2.get("name1");
				if((str1.equals("value1"))&&(str2.equals("value1")))
				{
					System.out.println("TESTCASE 125 -> PASSED");
				}
				else
				{
					System.out.println("TESTCASE 125 -> FAILED");
					System.out.println("125 STR1 = "+str1);
					System.out.println("125 STR2 = "+str2);
				}
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
			catch(Exception m)
			{
				m.printStackTrace();
			}
			rs = null;
			prop1 = null;
			prop2 = null;
			p = null;
		}
	}		

	private void testCase126()
	{
		DBPropertytable p = null;
		ResultSet rs = null;
		Properties prop1 = null;
		Properties prop2 = null;
		try
		{
			p = new DBPropertytable("rajeshtable126","KEYSTRING","PROPNAME","PROPVAL",false,null,true);
			prop1 = new Properties();
			prop1.put("name1","value1");
			try
			{
				t.begin();
			}
			catch(Exception m)
			{
				m.printStackTrace();
			}
			p.put("key1",prop1);
			try
			{
				t.rollback("test rollback");
			}
			catch(Exception l)
			{
		//		l.printStackTrace();
			}
			rs = statement.executeQuery("select count(*) from rajeshtable126");
			rs.next();
			int cnt1 = rs.getInt(1);
			prop2 = p.get("key1");
			if((cnt1==0)&&(prop2==null))
			{
				System.out.println("TESTCASE 126 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 126 -> FAILED");
				System.out.println("126 CNT1 = "+cnt1);
				System.out.println("126 PROP2 = "+prop2);
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
			catch(Exception m)
			{
				m.printStackTrace();
			}
			rs = null;
			prop1 = null;
			prop2 = null;
			p = null;
		}
	}		

	private void testCase128()
	{
		DBPropertytable p = null;
		ResultSet rs = null;
		Properties prop1 = null;
		Properties prop2 = null;
		try
		{
			p = new DBPropertytable("rajeshtable128","KEYSTRING","PROPNAME","PROPVAL",false,null,true);
			prop1 = new Properties();
			prop1.put("name1","value1");
			p.put("key1",prop1);
			try
			{
				t.begin();
			}
			catch(Exception m)
			{
				m.printStackTrace();
			}
			p.remove("key1");
			try
			{
				t.commit();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			rs = statement.executeQuery("select count(*) from rajeshtable128");
			rs.next();
			int cnt1 = rs.getInt(1);
			prop2 = p.get("key1");
			if((cnt1==0)&&(prop2==null))
			{
				System.out.println("TESTCASE 128 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 128 -> FAILED");
				System.out.println("128 CNT1 = "+cnt1);
				System.out.println("128 PROP2 = "+prop2);
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
			catch(Exception m)
			{
				m.printStackTrace();
			}
			rs = null;
			prop1 = null;
			prop2 = null;
			p = null;
		}
	}		

	private void testCase129()
	{
		DBPropertytable p = null;
		ResultSet rs = null;
		Properties prop1 = null;
		Properties prop2 = null;
		try
		{
			p = new DBPropertytable("rajeshtable129","KEYSTRING","PROPNAME","PROPVAL",false,null,true);
			prop1 = new Properties();
			prop1.put("name1","value1");
			p.put("key1",prop1);
			try
			{
				t.begin();
			}
			catch(Exception m)
			{
				m.printStackTrace();
			}
			p.remove("key1");
			try
			{
				t.rollback("test rollback");
			}
			catch(Exception l)
			{
		//		l.printStackTrace();
			}
			rs = statement.executeQuery("select PROPVAL from rajeshtable129 where KEYSTRING like 'key1' and PROPNAME like 'name1'");
			rs.next();
			String str1 = rs.getString(1);
			statement.executeUpdate("delete from rajeshtable129");
			prop2 = p.get("key1");
			if(prop2 == null)
			{
				System.out.println("TESTCASE 129 -> FAILED(get method returns null , object not added in the cache");
			}
			else
			{
				String str2 = (String)prop2.get("name1");
				if((str1.equals("value1"))&&(str2.equals("value2")))
				{
					System.out.println("TESTCASE 129 -> PASSED");
				}
				else
				{
					System.out.println("TESTCASE 129 -> FAILED");
					System.out.println("129 STR1 = "+str1);
					System.out.println("129 STR2 = "+str2);
				}
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
			catch(Exception m)
			{
				m.printStackTrace();
			}
			rs = null;
			prop1 = null;
			prop2 = null;
			p = null;
		}
	}
}
