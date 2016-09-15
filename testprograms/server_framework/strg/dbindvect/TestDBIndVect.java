package test;

import com.adventnet.nms.store.DBIndexedVector;
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

public class TestDBIndVect implements RunProcessInterface
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
			System.out.println("Validated all the TestCases in sf_strg_dbindvect TestPlan");
			System.out.println("Time taken to validate the TestCases in sf_strg_dbindvect : "+(time2-time1));
			try
			{
				statement.close();
			}
			catch(Exception y)
			{
				System.out.println("EXCEPTION in method ShutDown!->RajeshD : "+y);
				y.printStackTrace();
			}
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
			statement.executeUpdate("drop table rajeshtable70");
		}
		catch(Exception m)
		{
			m.printStackTrace();
		}
		try
		{
			statement.executeUpdate("drop table rajeshtable71");
		}
		catch(Exception m)
		{
			m.printStackTrace();
		}
		try
		{
			statement.executeUpdate("drop table rajeshtable72");
		}
		catch(Exception m)
		{
			m.printStackTrace();
		}
		try
		{
			statement.executeUpdate("drop table rajeshtable73");
		}
		catch(Exception m)
		{
			m.printStackTrace();
		}
		try
		{
			statement.executeUpdate("drop table rajeshtable74");
		}
		catch(Exception m)
		{
			m.printStackTrace();
		}
		try
		{
			statement.executeUpdate("drop table rajeshtable75");
		}
		catch(Exception m)
		{
			m.printStackTrace();
		}
		try
		{
			statement.executeUpdate("drop table rajeshtable76");
		}
		catch(Exception m)
		{
			m.printStackTrace();
		}
		try
		{
			statement.executeUpdate("drop table rajeshtable77");
		}
		catch(Exception m)
		{
			m.printStackTrace();
		}
		try
		{
			statement.executeUpdate("drop table rajeshtable78");
		}
		catch(Exception m)
		{
			m.printStackTrace();
		}
		try
		{
			statement.executeUpdate("drop table rajeshtable79");
		}
		catch(Exception m)
		{
			m.printStackTrace();
		}
		try
		{
			statement.executeUpdate("drop table rajeshtable80");
		}
		catch(Exception m)
		{
			m.printStackTrace();
		}
		try
		{
			statement.executeUpdate("drop table rajeshtable81");
		}
		catch(Exception m)
		{
			m.printStackTrace();
		}
		try
		{
			statement.executeUpdate("drop table rajeshtable82");
		}
		catch(Exception m)
		{
			m.printStackTrace();
		}
		try
		{
			statement.executeUpdate("drop table rajeshtable83");
		}
		catch(Exception m)
		{
			m.printStackTrace();
		}
		try
		{
			statement.executeUpdate("drop table rajeshtable85");
		}
		catch(Exception m)
		{
			m.printStackTrace();
		}
		try
		{
			statement.executeUpdate("drop table rajeshtable87");
		}
		catch(Exception m)
		{
			m.printStackTrace();
		}
		try
		{
			statement.executeUpdate("drop table rajeshtable86");
		}
		catch(Exception m)
		{
			m.printStackTrace();
		}

		try
		{
			statement.executeUpdate("drop table rajeshtable88");
		}
		catch(Exception m)
		{
			m.printStackTrace();
		}
		try
		{
			statement.executeUpdate("drop table rajeshtable89");
		}
		catch(Exception m)
		{
			m.printStackTrace();
		}
		try
		{
			statement.executeUpdate("drop table rajeshtable90");
		}
		catch(Exception m)
		{
			m.printStackTrace();
		}
		try
		{
			statement.executeUpdate("drop table rajeshtable92");
		}
		catch(Exception m)
		{
			m.printStackTrace();
		}
		try
		{
			statement.executeUpdate("drop table rajeshtable93A");
		}
		catch(Exception m)
		{
			m.printStackTrace();
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
		testCase70();
		testCase71();
		testCase72();
		testCase79();
		testCase73();
		testCase74();
		testCase75();
		testCase76();
		testCase77();
		testCase78();
		testCase80();
		testCase81();
		testCase82();
		testCase83();
		testCase84();
		testCase85();
		testCase86();
		testCase87();
		testCase88();
		testCase89();
		testCase90();
		testCase91();
		testCase92();
		testCase93A(); 
	}

	private void testCase70()
	{
		DBIndexedVector d = null;
		ResultSet rs = null;
		String s1 = null;
		try
		{
			statement.executeUpdate("create table rajeshtable70(VALUESTRING varchar(20),POSITION int)");
			d = new DBIndexedVector("rajeshtable70",null,false);
			d.addElement("111");
			rs = statement.executeQuery("select * from rajeshtable70");
			rs.next();
			s1 = rs.getString(1);
			statement.executeUpdate("delete from rajeshtable70");
			boolean flag = d.contains("111");
			if( (s1.equals("111")) && (flag) )
			{
				System.out.println("TESTCASE 070 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 070 -> FAILED");
				System.out.println("TESTCASE 070 : S1 : "+s1);
				System.out.println("TESTCASE 070 : FLAG "+flag);
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
			catch(Exception k)
			{
				k.printStackTrace();
			}
			rs = null;
			d = null;
			s1 = null;
		}
	}

	private void testCase71()
	{
		DBIndexedVector d = null;
		ResultSet rs = null;
		String s1 = null;
		try
		{
			d = new DBIndexedVector("rajeshtable71",null,true);
			d.addElement("111");
			rs = statement.executeQuery("select * from rajeshtable71");
			rs.next();
			s1 = rs.getString(1);
			statement.executeUpdate("delete from rajeshtable71");
			boolean flag = d.contains("111");
			if( (s1.equals("111")) && (flag) )
			{
				System.out.println("TESTCASE 071 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 071 -> FAILED");
				System.out.println("TESTCASE 071 : S1 : "+s1);
				System.out.println("TESTCASE 071 : FLAG "+flag);
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
			catch(Exception k)
			{
				k.printStackTrace();
			}
			rs = null;
			d = null;
			s1 = null;
		}
	}		
				
	
	private void testCase72()
	{
		DBIndexedVector d = null;
		try
		{
			DBIndexedVector.setMaxValue(10);
			d = new DBIndexedVector("rajeshtable72",null,true);
			for(int i=1;i<26;i++)
			{
				d.addElement("11"+i);
			}
			statement.executeUpdate("delete from rajeshtable72");
			int tr = 0;
			int fal = 0;
			int max = d.getMaxValue();
			for(int i=1;i<26;i++)
			{
				if(d.contains("11"+i))
				{
					tr++;
				}
				else
				{
					fal++;
				}
			}
			if( (tr==10) && (fal==15) && (max==10) )
			{
				System.out.println("TESTCASE 072 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 072 -> FAILED");
				System.out.println("TESTCASE 072 TRUE : "+tr);
				System.out.println("TESTCASE 072 FALSE : "+fal);
				System.out.println("TESTCASE 072 MAX : "+max);
			}
		}
		catch(Exception s)
		{
			s.printStackTrace();
		}
		finally
		{
			d = null;
			DBIndexedVector.setMaxValue(1000);
		}
	}

	private void testCase79()
	{
		DBIndexedVector d = null;
		ResultSet rs = null;
		String s1 = null;
		try
		{
			d = new DBIndexedVector("rajeshtable79",null,true);
			d.addElement("111");
			rs = statement.executeQuery("select * from rajeshtable79");
			rs.next();
			s1 = rs.getString(1);
			statement.executeUpdate("delete from rajeshtable79");
			boolean flag = d.contains("111");
			if( (s1.equals("111")) && (flag) )
			{
				System.out.println("TESTCASE 079 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 079 -> FAILED");
				System.out.println("TESTCASE 079 : S1 : "+s1);
				System.out.println("TESTCASE 079 : FLAG "+flag);
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
			catch(Exception k)
			{
				k.printStackTrace();
			}
			rs = null;
			d = null;
			s1 = null;
		}
	}

	private void testCase73()
	{
		DBIndexedVector d = null;
		String s1 = null;
		String s14 = null;
		try
		{
			d = new DBIndexedVector("rajeshtable73",null,true);
			for(int i=1;i<16;i++)
			{
				d.addElement("11"+i);
			}
			s1 = d.elementAt(1);
			s14 = d.elementAt(14);
			if(  (s1.equals("112")) &&  (s14.equals("1115")) )
			{
				System.out.println("TESTCASE 073 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 073 -> FAILED");
				System.out.println("TESTCASE 073 : S1 : "+s1);
				System.out.println("TESTCASE 073 : S14 : "+s14);
			}
		}
		catch(Exception l)
		{
			l.printStackTrace();
		}
		finally
		{
			d = null;
			s1 = null;
			s14 = null;
		}
	}

	private void testCase74()
	{
		DBIndexedVector d = null;
		String s1 = null;
		try
		{
			d = new DBIndexedVector("rajeshtable74",null,true);
			for(int i=1;i<5;i++)
			{
				d.addElement("11"+i);
			}
			s1 = d.elementAt(-1);
			System.out.println("TESTCASE 074 -> FAILED");
			System.out.println("TESTCASE 074 : Exception not thrown");
		}
		catch(Exception k)
		{
		//	k.printStackTrace();
			System.out.println("TESTCASE 074 : PASSED");
		}
		finally
		{
			d = null;
			s1 = null;
		}
	}

	private void testCase75()
	{
		DBIndexedVector d = null;
		String s1 = null;
		try
		{
			d = new DBIndexedVector("rajeshtable75",null,true);
			for(int i=1;i<1202;i++)
			{
				d.addElement("11"+i);
			}
			s1 = d.elementAt(1200);
			if(s1.equals("111201"))
			{
				System.out.println("TESTCASE 075 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 075 -> FAILED");
				System.out.println("TESTCASE 075 : S1 : "+s1);
			}
		}
		catch(Exception k)
		{
			k.printStackTrace();
		}
		finally
		{
			d = null;
			s1 = null;
		}
	}

	private void testCase76()
	{
		DBIndexedVector d = null;
		String s1 = null;
		String s2 = null;
		ResultSet rs = null;
		try
		{
			d = new DBIndexedVector("rajeshtable76",null,true);
			d.insertElementAt("111",20);
			rs = statement.executeQuery("select VALUESTRING from rajeshtable76 where POSITION=20");
			rs.next();
			s1 = rs.getString(1);
			s2 = d.elementAt(20);			
			if( (s1.equals("111")) && (s2.equals("111")))
			{
				System.out.println("TESTCASE 076 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 076 -> FAILED");
				System.out.println("TESTCASE 076 : S1 :"+s1);
				System.out.println("TESTCASE 076 : S2 :"+s2);
			}
		}
		catch(Exception k)
		{
			k.printStackTrace();
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
			s1 = null;
			s2 = null;
			d = null;
		}
	}

	
	private void testCase77()
	{
		DBIndexedVector d = null;
		try
		{
			d = new DBIndexedVector("rajeshtable77",null,true);
			d.insertElementAt("111",-1);
			System.out.println("TESTCASE 077 -> FAILED");
			System.out.println("TESTCASE 077 : No Exception thrown");
		}
		catch(Exception k)
		{
			System.out.println("TESTCASE 077 : PASSED");
			//k.printStackTrace();
		}
		finally
		{
			d = null;
		}
	}	

	private void testCase78()
	{
		DBIndexedVector d = null;
		ResultSet rs = null;
		String s1 = null;
		String s2 = null;
		try
		{
			d = new DBIndexedVector("rajeshtable78",null,true);
			for(int i=1;i<1202;i++)
			{
				d.insertElementAt("11"+i,i);
			}
			rs = statement.executeQuery("select VALUESTRING from rajeshtable78 where POSITION=1200");
			rs.next();
			s1 = rs.getString(1);
			s2 = d.elementAt(1200);
			if( (s1.equals("111200")) && (s2.equals("111200")) )
			{
				System.out.println("TESTCASE 078 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 078 -> FAILED");
				System.out.println("TESTCASE 078 : S1 : "+s1);
				System.out.println("TESTCASE 078 : S2 : "+s2);
			}
		}
		catch(Exception k)
		{
			k.printStackTrace();
		}
		finally
		{
			d = null;
			s1 = null;
			s2 = null;
			try
			{
				rs.close();
			}
			catch(Exception m)
			{
				m.printStackTrace();
			}
			rs = null;
		}
	}

	private void testCase80()
	{
		DBIndexedVector d = null;
		try
		{
			d = new DBIndexedVector("rajeshtable80",null,true);
			int size1 = d.size();
			for(int i=1;i<121;i++)
			{
				d.addElement("11"+i);
			}
			int size2 = d.size();
			if(  (size2==120)  && (size1==0) )
			{
				System.out.println("TESTCASE 080 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 080 -> FAILED");
				System.out.println("TESTCASE 080 : SIZE1 :"+size1);
				System.out.println("TESTCASE 080 : SIZE2 :"+size2);
			}
		}
		catch(Exception m)
		{
			m.printStackTrace();
		}
		finally
		{
			d = null;
		}
	}
	
	private void testCase81()
	{
		DBIndexedVector d = null;
		try
		{
			d = new DBIndexedVector("rajeshtable81",null,true);
			boolean flag1 = d.isEmpty();
			d.addElement("111");
			boolean flag2 = d.isEmpty();
			if(  (flag1)  && (!flag2) )
			{
				System.out.println("TESTCASE 081 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 081 -> FAILED");
				System.out.println("TESTCASE 081 : FLAG1 :"+flag1);
				System.out.println("TESTCASE 081 : FLAG2 :"+flag2);
			}
		}
		catch(Exception m)
		{
			m.printStackTrace();
		}
		finally
		{
			d = null;
		}
	}

	private void testCase82()
	{
		DBIndexedVector d = null;
		ResultSet rs = null;
		try
		{
			d = new DBIndexedVector("rajeshtable82",null,true);
			for(int i=1;i<11;i++)
			{
				d.addElement("11"+i);
			}
			d.removeAllElements();
			rs = statement.executeQuery("select count(*) from rajeshtable82");
			rs.next();
			int cnt = rs.getInt(1);
			boolean flag = true;
			for(int i=1;i<11;i++)
			{
				if(d.contains("11"+i))
				{
					flag = false;
				}
			}
			if( (cnt==0) && (flag) )
			{
				System.out.println("TESTCASE 082 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 082 -> FAILED");
				System.out.println("TESTCASE 082 : CNT  : "+cnt);
				System.out.println("TESTCASE 082 : FLAG :"+flag);
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
			catch(Exception k)
			{
				k.printStackTrace();
			}
			rs = null;
			d = null;
		}
	}		

	private void testCase83()
	{
		DBIndexedVector d = null;
		ResultSet rs = null;
		try
		{
			d = new DBIndexedVector("rajeshtable83",null,true);
			for(int i=1;i<11;i++)
			{
				d.addElement("11"+i);
			}
			d.removeElementAt(6);
			rs = statement.executeQuery("select count(*) from rajeshtable83");
			rs.next();
			int cnt = rs.getInt(1);
			boolean flag = d.contains("117");
			if( (cnt==9) && (!flag) )
			{
				System.out.println("TESTCASE 083 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 083 -> FAILED");
				System.out.println("TESTCASE 083 : CNT  : "+cnt);
				System.out.println("TESTCASE 083 : FLAG :"+flag);
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
			catch(Exception k)
			{
				k.printStackTrace();
			}
			rs = null;
			d = null;
		}
	}

	private void testCase84()
	{
		DBIndexedVector d = null;
		try
		{
			d = new DBIndexedVector("rajeshtable83",null,true);
			d.removeElementAt(-1);
			System.out.println("TESTCASE 084 -> FAILED");
			System.out.println("TESTCASE 084 : No Exception thrown");
		}
		catch(Exception m)
		{
			m.printStackTrace();
			System.out.println("TESTCASE 084 -> PASSED");
		}
		finally
		{
			d = null;
		}
	}

	private void testCase85()
	{
		DBIndexedVector d = null;
		ResultSet rs = null;
		try
		{
			d = new DBIndexedVector("rajeshtable85",null,true);
			for(int i=1;i<1202;i++)
			{
				d.addElement("11"+i);
			}
			d.removeElementAt(1200);
			rs = statement.executeQuery("select count(*) from rajeshtable85 where POSITION=1200");
			rs.next();
			int cnt = rs.getInt(1);
			boolean flag = d.contains("111201");
			if( (cnt==0) && (!flag) )
			{
				System.out.println("TESTCASE 085 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 085 -> FAILED");
				System.out.println("TESTCASE 085 : CNT  : "+cnt);
				System.out.println("TESTCASE 085 : FLAG :"+flag);
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
			catch(Exception k)
			{
				k.printStackTrace();
			}
			rs = null;
			d = null;
		}
	}
	
	private void testCase86()
	{
		DBIndexedVector d = null;
		try
		{
			d = new DBIndexedVector("rajeshtable86",null,true);
			boolean flag1 = d.contains("111");
			d.addElement("111");
			boolean flag2 = d.contains("111");
			if(  (!flag1)  && (flag2) )
			{
				System.out.println("TESTCASE 086 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 086 -> FAILED");
				System.out.println("TESTCASE 086 : FLAG1 :"+flag1);
				System.out.println("TESTCASE 086 : FLAG2 :"+flag2);
			}
		}
		catch(Exception m)
		{
			m.printStackTrace();
		}
		finally
		{
			d = null;
		}
	}

	private void testCase87()
	{
		DBIndexedVector d = null;
		Enumeration en = null;
		try
		{
			d = new DBIndexedVector("rajeshtable87",null,true);
			d.addElement("111");
			en = d.elements();
			while(en.hasMoreElements())
			{
				if(en.nextElement().equals("111"))
				{
					System.out.println("TESTCASE 087 -> PASSED");
				}
				else
				{
					System.out.println("TESTCASE 087 -> FAILED");
				}
			}
		}
		catch(Exception m)
		{
			m.printStackTrace();
		}
		finally
		{
			d = null;
			en = null;
		}
	}
	
	private void testCase88()
	{
		DBIndexedVector d = null;
		try
		{
			d = new DBIndexedVector("rajeshtable88",null,true);
			int index1 = d.indexOf("111");
			d.addElement("110");
			d.addElement("111");
			int index2 = d.indexOf("111");
			if(  (index1==-1)  && (index2==1) )
			{
				System.out.println("TESTCASE 088 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 088 -> FAILED");
				System.out.println("TESTCASE 088 : INDEX1 :"+index1);
				System.out.println("TESTCASE 088 : INDEX2 :"+index2);
			}
		}
		catch(Exception m)
		{
			m.printStackTrace();
		}
		finally
		{
			d = null;
		}
	}

	private void testCase89()
	{
		DBIndexedVector d = null;
		ResultSet rs = null;
		try
		{
			d = new DBIndexedVector("rajeshtable89",null,true);
			for(int i=1;i<11;i++)
			{
				d.addElement("11"+i);
			}
			d.removeElement("111");
			rs = statement.executeQuery("select count(*) from rajeshtable89 where VALUESTRING like '111'");
			rs.next();
			int cnt = rs.getInt(1);
			boolean flag = d.contains("111");
			if( (cnt==0) && (!flag) )
			{
				System.out.println("TESTCASE 089 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 089 -> FAILED");
				System.out.println("TESTCASE 089 : CNT  : "+cnt);
				System.out.println("TESTCASE 089 : FLAG :"+flag);
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
			catch(Exception k)
			{
				k.printStackTrace();
			}
			rs = null;
			d = null;
		}
	}		

	private void testCase90()
	{
		DBIndexedVector d = null;
		ResultSet rs = null;
		try
		{
			d = new DBIndexedVector("rajeshtable90",null,true);
			for(int i=1;i<11;i++)
			{
				d.addElement("11"+i);
			}
			d.reinitialize();
			rs = statement.executeQuery("select count(*) from rajeshtable90");
			rs.next();
			int cnt = rs.getInt(1);
			boolean flag = true;
			for(int i=1;i<11;i++)
			{
				if(d.contains("11"+i))
				{
					flag = false;
				}
			}
			if( (cnt==0) && (flag) )
			{
				System.out.println("TESTCASE 090 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 090 -> FAILED");
				System.out.println("TESTCASE 090 : CNT  : "+cnt);
				System.out.println("TESTCASE 090 : FLAG :"+flag);
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
			catch(Exception k)
			{
				k.printStackTrace();
			}
			rs = null;
			d = null;
		}
	}
	
	private void testCase91()
	{
		DBIndexedVector d = null;
		DBIndexedVector d1 = null;
		try
		{
			d = new DBIndexedVector("rajeshtable91",null,false);
			d.addElement("111");
			d1 = (DBIndexedVector)d.clone();
			if(d1.contains("111"))
			{
				System.out.println("TESTCASE 091 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 092 -> FAILED");
			}
		}
		catch(Exception m)
		{
			m.printStackTrace();
		}
		finally
		{
			d = null;
			d1 = null;
		}
	}

	private void testCase92()
	{
		DBIndexedVector d = null;
		ResultSet rs = null;
		try
		{
			d = new DBIndexedVector("rajeshtable92",null,true);
			for(int i=1;i<11;i++)
			{
				d.addElement("11"+i);
			}
			d.removeElementsFromIndexedVector("116");
			rs = statement.executeQuery("select count(*) from rajeshtable92");
			rs.next();
			int cnt = rs.getInt(1);
			boolean flag = d.contains("114");
			if( (cnt==5) && (!flag) )
			{
				System.out.println("TESTCASE 092 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 092 -> FAILED");
				System.out.println("TESTCASE 092 : CNT  : "+cnt);
				System.out.println("TESTCASE 092 : FLAG :"+flag);
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
			catch(Exception k)
			{
				k.printStackTrace();
			}
			rs = null;
			d = null;
		}
	}

	private void testCase93A()
	{
		DBIndexedVector d = null;
		ResultSet rs = null;
		try
		{
			d = new DBIndexedVector("rajeshtable93A",null,true);
			for(int i=1;i<11;i++)
			{
				d.addElement("11"+i);
			}
			d.clear();
			rs = statement.executeQuery("select count(*) from rajeshtable93A");
			rs.next();
			int cnt = rs.getInt(1);
			boolean flag = true;
			for(int i=1;i<11;i++)
			{
				if(d.contains("11"+i))
				{
					flag = false;
				}
			}
			if( (cnt==0) && (flag) )
			{
				System.out.println("TESTCASE 093A -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 093A -> FAILED");
				System.out.println("TESTCASE 093A : CNT  : "+cnt);
				System.out.println("TESTCASE 093A : FLAG :"+flag);
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
			catch(Exception k)
			{
				k.printStackTrace();
			}
			rs = null;
			d = null;
		}
	}
}
