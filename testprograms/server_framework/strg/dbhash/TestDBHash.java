package test;

import com.adventnet.nms.store.DBHashtable;
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
import java.util.*;

public class TestDBHash implements RunProcessInterface
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
			System.out.println("Tested all TestCases in the TestPlan sf_strg_dbhash ");
			System.out.println("Time taken to validate the TestPlan : "+(time2-time1));
		}
		catch(Exception q)
		{
			q.printStackTrace();
		}
	}


	public void shutDown()
	{
        dropTables();
        try
        {
            statement.close();
        }
        catch(Exception y)
        {
            y.printStackTrace();
        }
	}

	public boolean isInitialized()
	{
		return true;
	}

    private ArrayList tableNames = new ArrayList();

    private DBHashtable createDBHashTable(String htName, String keyStr, String valueStr, boolean readOnly, Connection conn, boolean createTable) throws Exception
    {
        tableNames.add(htName);
        if (createTable)
        {
            try
            {
                statement.executeUpdate("create table " + htName + " ( " + keyStr + " varchar(20), " + valueStr + " varchar(20))");
                return new DBHashtable(htName, keyStr, valueStr, readOnly, conn, createTable);
            }
            catch (Exception e)
            {
                System.out.println("Exception while creating DBHashtable : " + htName);
                e.printStackTrace();
                throw e;
            }
        }
        else
        {
            return new DBHashtable(htName, keyStr, valueStr, readOnly, conn, createTable);
        }
    }

	private void callMethods()
	{
        /*
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
		testCase33_1();
		testCase33_2();
		testCase33_3();
		testCase33_4();
		testCase033_5();
		testCase033_6();
		testCase033_7();
		testCase033_16();
		testCase033_17();
		testCase033_18();
		testCase33_19();
		testCase33_20();
		testCase33_21();
		testCase33_22();
		testCase33_25();
		testCase33_27();
		testCase33_30();
		testCase33_32();
        */

        testCase33_1();
        testCase33_2();
		testCase033_5();
		testCase033_16();
		testCase33_26();
		testCase33_27();
		testCase33_30();
        testCase33_33();
        testCase33_34();
        testCase33_35();
        testCase33_36();
        testCase33_37();
        testCase33_38();
	}

	private void dropTables()
	{
        for (int i = 0; i < tableNames.size(); i++)
        {
            String tName = (String) tableNames.get(i);
            try
            {
                statement.executeUpdate("drop table " + tName);
            }
            catch (Exception e)
            {
                System.out.println("Exception while droping table : "  + tName);
                e.printStackTrace();
            }
        }
	}

	private void testCase019()
	{
		DBHashtable h = null;
		ResultSet rs = null;
		try
		{
			statement.executeUpdate("create table rajeshtable19 (KEYSTRING varchar(20) , VALUESTRING varchar(20))");
			statement.executeUpdate("insert into rajeshtable19 values('key1','value1')");
			h = createDBHashTable("rajeshtable19","KEYSTRING","VALUESTRING",false,null,false);
			boolean flag1= false;
			if(h.contains("value1"))
			{
				flag1 = true;
			}
			h.put("key2","value2");
			rs = statement.executeQuery("select count(*) from rajeshtable19 where VALUESTRING like 'value2'");
			rs.next();
			int i = rs.getInt(1);
			int j = h.size();
			if((i==1)&&(flag1)&&(j==2))
			{
				System.out.println("TESTCASE 019 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 019 -> FAILED");
				System.out.println("019 FLAG IS ; "+flag1);
				System.out.println("019 I IS ; "+i);
				System.out.println("019 J IS ; "+j);
			}
		}
		catch(Exception m)
		{
			m.printStackTrace();
		}
		finally
		{
			try
			{
				rs.close();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			rs = null;
			h = null;
		}
	}
	
	private void testCase020()
	{
		DBHashtable h = null;
		ResultSet rs = null;
		try
		{
			h = createDBHashTable("rajeshtable20","KEYSTRING","VALUESTRING",false,null,true);
			h.put("key1","value1");
			String str = h.get("key1");
			System.out.println("GET VALUE ; " +str);
			rs = statement.executeQuery("select count(*) from rajeshtable20 where VALUESTRING like 'value1'");
			rs.next();
			int i = rs.getInt(1);
			int j = h.size();
			//statement.executeUpdate("delete from rajeshtable20");
			if((i==1)&&(h.contains("value1"))&&(j==1))
			{
				System.out.println("TESTCASE 020 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 020 -> FAILED");
				System.out.println("020 FLAG IS ; "+h.contains("value1"));
				System.out.println("020 I IS ;"+i);
				System.out.println("020 J IS ;"+j);
			}
		}
		catch(Exception m)
		{
			m.printStackTrace();
		}
		finally
		{
			try
			{
				rs.close();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			rs = null;
			h = null;
		}
	}

	private void testCase021()
	{
		DBHashtable h = null;
		boolean flag1 = false;
		try
		{
			statement.executeUpdate("create table rajeshtable21 (KEYSTRING varchar(20) , VALUESTRING varchar(20))");
			statement.executeUpdate("insert into rajeshtable21 values('key1','value1')");
			h = createDBHashTable("rajeshtable21","KEYSTRING","VALUESTRING",false,null,false);
			if(h.contains("value1"))
			{
				flag1 = true;
			}
			h.put("key2","value2");
			System.out.println("CASE 21 : "  +flag1);
			System.out.println("TESTCASE 021 -> FAILED");
			System.out.println("TESTCASE 021 ; Exception not thrown");
		}
		catch(Exception m)
		{
			m.printStackTrace();
			if(flag1)
			{
				System.out.println("TESTCASE 021 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 021 FLAG ;"+flag1);
			}
			
		}
		finally
		{
			h = null;
		}
	}


	private void testCase022()
	{
		DBHashtable h = null;
		try
		{
			DBHashtable.setMaxValue(10);
			h = createDBHashTable("rajeshtable22","testKey","testVal",false,null,true);
			for(int i=1;i<101;i++)
			{
				h.put("key"+i,"value"+i);
			}
			statement.executeUpdate("delete from rajeshtable22");
			int tr = 0;
			for(int i=1;i<101;i++)
			{
				if(h.contains("value"+i))
				{
					tr++;
				}
			}
			if( (tr==10) && (DBHashtable.getMaxValue()==10))
			{
				System.out.println("TESTCASE 022 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 022 -> FAILED");
				System.out.println("022 TR IS ; "+tr);
				System.out.println("022 GET MAX IS "+DBHashtable.getMaxValue());
			}
		}
		catch(Exception m)
		{
			m.printStackTrace();
		}
		finally
		{
			h = null;
			DBHashtable.setMaxValue(1000);
		}
	}
	
	private void testCase023()
	{
		DBHashtable h = null;
		ResultSet rs = null;
		try
		{
			h = createDBHashTable("rajeshtable23","KEYSTRING","VALUESTRING",false,null,true);
			h.put("key1","value1");
			h.put("key2","value2");
			rs = statement.executeQuery("select count(*) from rajeshtable23 where VALUESTRING like 'value1'");
			rs.next();
			int i = rs.getInt(1);
			int j = h.size();
			statement.executeUpdate("delete from rajeshtable23");
			if((i==1)&&(h.contains("value1"))&&(j==1))
			{
				System.out.println("TESTCASE 023 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 023 -> FAILED");
				System.out.println("023 FLAG IS ; "+h.contains("value1"));
				System.out.println("023 I IS ;"+i);
				System.out.println("023 J IS ;"+j);
			}
		}
		catch(Exception m)
		{
			m.printStackTrace();
		}
		finally
		{
			try
			{
				rs.close();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			rs = null;
			h = null;
		}
	}

	private void testCase024()
	{
		DBHashtable h = null;
		try
		{
			h = createDBHashTable("rajeshtable24","KEYSTRING","VALUESTRING",false,null,true);
			h.put("key1","value1");
			if(h.get("key1").equals("value1"))
			{
				System.out.println("TESTCASE 024 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 024 -> FAILED");
				System.out.println("024 GET RETURNS ; "+h.get("key1"));
			}
		}
		catch(Exception m)
		{
			m.printStackTrace();
		}
		finally
		{
			h = null;
		}
	}

	private void testCase025()
	{
		DBHashtable h = null;
		try
		{
			h = createDBHashTable("rajeshtable25","KEYSTRING","VALUESTRING",false,null,true);
			int size1 = h.size(); 
			for(int i=1;i<111;i++)
			{
				h.put("key"+i,"value"+i);
			}
			int size2 = h.size();
			if( (size1==0) && (size2==110) )
			{
				System.out.println("TESTCASE 025 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 025 -> FAILED");
				System.out.println("025 SIZE1 ; "+size1);
				System.out.println("025 SIZE2 ; "+size2);
			}
		}
		catch(Exception m)
		{
			m.printStackTrace();
		}
		finally
		{
			h = null;
		}
	}

	private void testCase026()
	{
		DBHashtable h = null;
		try
		{
			h = createDBHashTable("rajeshtable26","KEYSTRING","VALUESTRING",false,null,true);
			boolean flag1 = h.isEmpty();
			h.put("key1","value1");
			boolean flag2 = h.isEmpty();
			if((flag1)&&(!flag2))
			{
				System.out.println("TESTCASE 026 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 026 -> FAILED");
				System.out.println("026 FLAG1 ; "+flag1);
				System.out.println("026 FLAG2 ; "+flag2);
			}
		}
		catch(Exception m)
		{
			m.printStackTrace();
		}
		finally
		{
			h = null;
		}
	}
	
	private void testCase027()
	{
		DBHashtable h = null;
		ResultSet rs = null;
		try
		{
			h = createDBHashTable("rajeshtable27","testKey","testVal",false,null,true);
			h.put("key1","value1");
			h.clear();
			rs = statement.executeQuery("select count(*) from rajeshtable27 where testVal like 'value1'");
			rs.next();
			int i = rs.getInt(1);
			int j = h.size();
			if((i==0)&&(!h.contains("value1"))&&(j==0))
			{
				System.out.println("TESTCASE 027 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 027 -> FAILED");
				System.out.println("027 FLAG IS ; "+h.contains("value1"));
				System.out.println("027 I  IS ;"+i);
				System.out.println("027 j  IS ;"+j);
			}
		}
		catch(Exception m)
		{
			m.printStackTrace();
		}
		finally
		{
			try
			{
				rs.close();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			rs = null;
			h = null;
		}
	}
	private void testCase028()
	{
		DBHashtable h = null;
		ResultSet rs = null;
		try
		{
			h = createDBHashTable("rajeshtable28","testKey","testVal",false,null,true);
			h.put("key1","value1");
			h.remove("key1");
			rs = statement.executeQuery("select count(*) from rajeshtable28 where testVal like 'value1'");
			rs.next();
			int i = rs.getInt(1);
			int j = h.size();
			if((i==0)&&(!h.contains("value1"))&&(j==0))
			{
				System.out.println("TESTCASE 028 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 028 -> FAILED");
				System.out.println("028 FLAG IS ; "+h.contains("value1"));
				System.out.println("028 I IS ;"+i);
				System.out.println("028 J IS ;"+j);
			}
		}
		catch(Exception m)
		{
			m.printStackTrace();
		}
		finally
		{
			try
			{
				rs.close();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			rs = null;
			h = null;
		}
	}

	private void testCase029()
	{
		DBHashtable h = null;
		try
		{
			h = createDBHashTable("rajeshtable29","testKey","testVal",false,null,true);
			h.put("key1","value1");
			boolean flag1=h.contains("value1");
            h.clear();
			boolean flag2=h.contains("value1");
			if( (flag1)&&(!flag2))
			{
				System.out.println("TESTCASE 029 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 029 -> FAILED");
				System.out.println("029 FLAG1 IS ; "+flag1);
				System.out.println("029 FLAG2 IS ; "+flag2);
			}
		}
		catch(Exception m)
		{
			m.printStackTrace();
		}
		finally
		{
			h = null;
		}
	}

	private void testCase030()
	{
		DBHashtable h = null;
		try
		{
			h = createDBHashTable("rajeshtable30","testKey","testVal",false,null,true);
			h.put("key1","value1");
			boolean flag1=h.containsKey("key1");
			h.clear();
			boolean flag2=h.containsKey("key1");
			if( (flag1)&&(!flag2))
			{
				System.out.println("TESTCASE 030 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 030 -> FAILED");
				System.out.println("030 FLAG1 IS ; "+flag1);
				System.out.println("030 FLAG2 IS ; "+flag2);
			}
		}
		catch(Exception m)
		{
			m.printStackTrace();
		}
		finally
		{
			h = null;
		}
	}

	private void testCase031()
	{
		DBHashtable h = null;
		Enumeration e = null;
		try
		{
			h = createDBHashTable("rajeshtable31","testKey","testVal",false,null,true);
			h.put("key1","value1");
			e = h.keys();
			while(e.hasMoreElements())
			{
				if(e.nextElement().equals("key1"))
				{
					System.out.println("TESTCASE 031 -> PASSED");
				}
			}
		}
		catch(Exception k)
		{
			k.printStackTrace();
		}
	}
	
	private void testCase032()
	{
		DBHashtable h = null;
		Enumeration e = null;
		try
		{
			h = createDBHashTable("rajeshtable32","testKey","testVal",false,null,true);
			h.put("key1","value1");
			e = h.elements();
			while(e.hasMoreElements())
			{
				if(e.nextElement().equals("value1"))
				{
					System.out.println("TESTCASE 032 -> PASSED");
				}
			}
		}
		catch(Exception k)
		{
			k.printStackTrace();
		}
	}		
		
	private void testCase33_1()
	{
		DBHashtable h = null;
		ResultSet rs = null;
		try
		{
			h = createDBHashTable("rajeshtable33_1","testKey","testVal",false,null,true);
			try
			{
				t.begin();
			}
			catch(Exception m)
			{
				m.printStackTrace();
			}
			h.put("key1","value1");
			try
			{
				t.commit();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			rs = statement.executeQuery("select count(*) from rajeshtable33_1 where testVal like 'value1'");
			rs.next();
			int i = rs.getInt(1);
			int j = h.size();
			statement.executeUpdate("delete from rajeshtable33_1");
			if((i==1)&&(h.contains("value1"))&&(j==1))
			{
				System.out.println("TESTCASE 033.1 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 033.1 -> FAILED");
				System.out.println("033.1 FLAG IS ; "+h.contains("value1"));
				System.out.println("033.1 I IS ;"+i);
				System.out.println("033.1 J IS ;"+j);
			}
		}
		catch(Exception m)
		{
			m.printStackTrace();
		}
		finally
		{
			try
			{
				rs.close();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			rs = null;
			h = null;
		}
	}

	private void testCase33_2()
	{
		DBHashtable h = null;
		ResultSet rs = null;
		try
		{
			h = createDBHashTable("rajeshtable33_2","testKey","testVal",false,null,true);
			try
			{
				t.begin(-1);
			}
			catch(Exception m)
			{
				m.printStackTrace();
			}
			h.put("key1","value1");
            System.out.println("I AM SLEEEPING");
            Thread.sleep(1200);
			try
			{
				t.rollback("test rollback");
			}
			catch(Exception l)
			{
                l.printStackTrace();
			}
			rs = statement.executeQuery("select count(*) from rajeshtable33_2 where testVal like 'value1'");
			rs.next();
			int i = rs.getInt(1);
			if((i==0)&&(!h.contains("value1")))
			{
				System.out.println("TESTCASE 033.2 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 033.2 -> FAILED");
				System.out.println("033.2 FLAG IS ; "+h.contains("value1"));
				System.out.println("033.2 COUNT IS ;"+i);
			}
		}
		catch(Exception m)
		{
			m.printStackTrace();
		}
		finally
		{
			try
			{
				rs.close();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			rs = null;
			h = null;
		}
	}

	private void testCase33_3()
	{
		DBHashtable h = null;
		ResultSet rs = null;
		try
		{
			h = createDBHashTable("rajeshtable33_3","testKey","testVal",false,null,true);
			try
			{
				t.begin();
			}
			catch(Exception m)
			{
				m.printStackTrace();
			}
			h.put("key1","value1");
			try
			{
				Thread.sleep(27000);	
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			rs = statement.executeQuery("select count(*) from rajeshtable33_3 where testVal like 'value1'");
			rs.next();
			int i = rs.getInt(1);
			if(i==0)
			{
				System.out.println("TESTCASE 033.3 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 033.3 -> FAILED");
				System.out.println("033.3 COUNT IS ;"+i);
			}
		}
		catch(Exception m)
		{
			m.printStackTrace();
		}
		finally
		{
			try
			{
				rs.close();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			rs = null;
			h = null;
		}
	}

	private void testCase33_4()
	{
		DBHashtable h = null;
		ResultSet rs = null;
		try
		{
			h = createDBHashTable("rajeshtable33_4","testKey","testVal",false,null,true);
			try
			{
				t.begin();
			}
			catch(Exception m)
			{
				m.printStackTrace();
			}
			h.put("key1","value1");
			rs = statement.executeQuery("select count(*) from rajeshtable33_4 where testVal like 'value1'");
			rs.next();
			int i = rs.getInt(1);
			if((i==0)&&(!h.contains("value1")))
			{
				System.out.println("TESTCASE 033.4 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 033.4 -> FAILED");
				System.out.println("033.4 FLAG IS ; "+h.contains("value1"));
				System.out.println("033.4 COUNT IS ;"+i);
			}
			try
			{
				t.commit();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
		}
		catch(Exception m)
		{
			m.printStackTrace();
		}
		finally
		{
			try
			{
				rs.close();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			rs = null;
			h = null;
		}
	}

	private void testCase033_5()
	{
		DBHashtable h = null;
		ResultSet rs = null;
		try
		{
			h = createDBHashTable("rajeshtable33_5","testKey","testVal",false,null,true);
			h.put("key1","value1");
			try
			{
				t.begin();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			h.remove("key1");
			try
			{
				t.commit();
			}
			catch(Exception k)
			{
				k.printStackTrace();
			}
			rs = statement.executeQuery("select count(*) from rajeshtable33_5 where testVal like 'value1'");
			rs.next();
			int i = rs.getInt(1);
			int j = h.size();
			statement.executeUpdate("delete from rajeshtable33_5");
			if((i==0)&&(!h.contains("value1"))&&(j==0))
			{
				System.out.println("TESTCASE 033.5 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 033.5 -> FAILED");
				System.out.println("033.5 FLAG IS ; "+h.contains("value1"));
				System.out.println("033.5 I IS ;"+i);
				System.out.println("033.5 J IS ;"+j);
			}
		}
		catch(Exception m)
		{
			m.printStackTrace();
		}
		finally
		{
			try
			{
				rs.close();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			rs = null;
			h = null;
		}
	}

	private void testCase033_6()
	{
		DBHashtable h = null;
		ResultSet rs = null;
		try
		{
			h = createDBHashTable("rajeshtable33_6","testKey","testVal",false,null,true);
			h.put("key1","value1");
			try
			{
				t.begin();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			h.remove("key1");
			try
			{
				t.rollback("test rollback");
			}
			catch(Exception k)
			{
                //	k.printStackTrace();
			}
			rs = statement.executeQuery("select count(*) from rajeshtable33_6 where testVal like 'value1'");
			rs.next();
			int i = rs.getInt(1);
			int j = h.size();
			statement.executeUpdate("delete from rajeshtable33_6");
			if((i==1)&&(h.contains("value1"))&&(j==1))
			{
				System.out.println("TESTCASE 033.6 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 033.6 -> FAILED");
				System.out.println("033.6 FLAG IS ; "+h.contains("value1"));
				System.out.println("033.6 I IS ;"+i);
				System.out.println("033.6 J IS ;"+j);
			}
		}
		catch(Exception m)
		{
			m.printStackTrace();
		}
		finally
		{
			try
			{
				rs.close();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			rs = null;
			h = null;
		}
	}

	private void testCase033_7()
	{
		DBHashtable h = null;
		ResultSet rs = null;
		try
		{
			h = createDBHashTable("rajeshtable33_7","testKey","testVal",false,null,true);
			h.put("key1","value1");
			try
			{
				t.begin();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			h.remove("key1");
			try
			{
				Thread.sleep(27000);
			}
			catch(Exception k)
			{
				k.printStackTrace();
			}
			rs = statement.executeQuery("select count(*) from rajeshtable33_7 where testVal like 'value1'");
			rs.next();
			int i = rs.getInt(1);
			int j = h.size();
			if((i==1)&&(j==1))
			{
				System.out.println("TESTCASE 033.7 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 033.7 -> FAILED");
				System.out.println("033.7 I IS ;"+i);
				System.out.println("033.7 J IS ;"+j);
			}
		}
		catch(Exception m)
		{
			m.printStackTrace();
		}
		finally
		{
			try
			{
				rs.close();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			rs = null;
			h = null;
		}
	}

	private void testCase033_16()
	{
		DBHashtable h = null;
		ResultSet rs = null;
		try
		{
			h = createDBHashTable("rajeshtable33_16","testKey","testVal",false,null,true);
			h.add("key1","value1");
			rs = statement.executeQuery("select count(*) from rajeshtable33_16 where testVal like 'value1'");
			rs.next();
			int i = rs.getInt(1);
			int j = h.size();
			statement.executeUpdate("delete from rajeshtable33_16");
			if((i==1)&&(h.contains("value1"))&&(j==1))
			{
				System.out.println("TESTCASE 033.16 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 033.16 -> FAILED");
				System.out.println("033.16 FLAG IS ; "+h.contains("value1"));
				System.out.println("033.16 I  IS ;"+i);
				System.out.println("033.16 J  IS ;"+j);
			}
		}
		catch(Exception m)
		{
			m.printStackTrace();
		}
		finally
		{
			try
			{
				rs.close();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			rs = null;
			h = null;
		}
	}

	private void testCase033_17()
	{
		DBHashtable h = null;
		ResultSet rs = null;
		try
		{
			h = createDBHashTable("rajeshtable33_17","testKey","testVal",false,null,true);
			h.put("key1","value1");
			h.update("key1","newvalue");
			rs = statement.executeQuery("select testVal from rajeshtable33_17 where testKey like 'key1'");
			rs.next();
			String s1 = rs.getString(1);
			String s2 = h.get("key1");	
			if( (s1.equals("newvalue")) && (s2.equals("newvalue")))
			{
				System.out.println("TESTCASE 033.17 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 033.17 -> FAILED");
				System.out.println("TESTCASE 033.17 s1 ; "+s1);
				System.out.println("TESTCASE 033.17 s2 ; "+s2);
			}
		}
		catch(Exception m)
		{
			m.printStackTrace();
		}
		finally
		{
			try
			{
				rs.close();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			rs = null;
			h = null;
		}
	}

	private void testCase033_18()
	{
		DBHashtable h = null;
		try
		{
			h = createDBHashTable("rajeshtable33_18","testKey","testVal",false,null,true);
			h.update("key1","newvalue");
			System.out.println("TESTCASE 033.18 -> FAILED");
			System.out.println("TESTCASE 033.18 ; No Exception thrown");
		}
		catch(Exception m)
		{
			System.out.println("TESTCASE 33.18 -> PASSED");
			m.printStackTrace();
		}
		finally
		{
			h = null;
		}
	}

	private void testCase33_19()
	{
		DBHashtable h = null;
		ResultSet rs = null;
		try
		{
			h = createDBHashTable("rajeshtable33_19","testKey","testVal",false,null,true);
			try
			{
				t.begin();
			}
			catch(Exception m)
			{
				m.printStackTrace();
			}
			h.add("key1","value1");
			try
			{
				t.commit();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			rs = statement.executeQuery("select count(*) from rajeshtable33_19 where  testVal like 'value1'");
			rs.next();
			int i = rs.getInt(1);
			statement.executeUpdate("delete from rajeshtable33_19");
			if((i==1)&&(h.contains("value1")))
			{
				System.out.println("TESTCASE 033.19 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 033.19 -> FAILED");
				System.out.println("033.19 FLAG IS ; "+h.contains("value1"));
				System.out.println("033.19 COUNT IS ;"+i);
			}
		}
		catch(Exception m)
		{
			m.printStackTrace();
		}
		finally
		{
			try
			{
				rs.close();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			rs = null;
			h = null;
		}
	}

	private void testCase33_20()
	{
		DBHashtable h = null;
		ResultSet rs = null;
		try
		{
			h = createDBHashTable("rajeshtable33_20","testKey","testVal",false,null,true);
			try
			{
				t.begin();
			}
			catch(Exception m)
			{
				m.printStackTrace();
			}
			h.add("key1","value1");
			try
			{
				t.rollback("test rollback");
			}
			catch(Exception l)
			{
				//l.printStackTrace();
			}
			rs = statement.executeQuery("select count(*) from rajeshtable33_20 where  testVal like 'value1'");
			rs.next();
			int i = rs.getInt(1);
			statement.executeUpdate("delete from rajeshtable33_20");
			if((i==0)&&(!h.contains("value1")))
			{
				System.out.println("TESTCASE 033.20 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 033.20 -> FAILED");
				System.out.println("033.20 FLAG IS ; "+h.contains("value1"));
				System.out.println("033.20 COUNT IS ;"+i);
			}
		}
		catch(Exception m)
		{
			m.printStackTrace();
		}
		finally
		{
			try
			{
				rs.close();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			rs = null;
			h = null;
		}
	}

	private void testCase33_21()
	{
		DBHashtable h = null;
		ResultSet rs = null;
		try
		{
			h = createDBHashTable("rajeshtable33_21","testKey","testVal",false,null,true);
			h.add("key1","value1");
			try
			{
				t.begin();
			}
			catch(Exception m)
			{
				m.printStackTrace();
			}
			h.update("key1","newvalue");
			try
			{
				t.commit();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			rs = statement.executeQuery("select count(*) from rajeshtable33_21 where  testVal like 'newvalue'");
			rs.next();
			int i = rs.getInt(1);
			boolean flag = h.contains("newvalue");
			statement.executeUpdate("delete from rajeshtable33_21");
			if((i==1)&&(flag))
			{
				System.out.println("TESTCASE 033.21 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 033.21 -> FAILED");
				System.out.println("033.21 FLAG IS ; "+flag);
				System.out.println("033.21 COUNT IS ;"+i);
			}
		}
		catch(Exception m)
		{
			m.printStackTrace();
		}
		finally
		{
			try
			{
				rs.close();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			rs = null;
			h = null;
		}
	}

	private void testCase33_22()
	{
		DBHashtable h = null;
		ResultSet rs = null;
		try
		{
			h = createDBHashTable("rajeshtable33_22","testKey","testVal",false,null,true);
			h.add("key1","value1");
			try
			{
				t.begin();
			}
			catch(Exception m)
			{
				m.printStackTrace();
			}
			h.update("key1","newvalue");
			try
			{
				t.rollback("test rollback");
			}
			catch(Exception l)
			{
                //				l.printStackTrace();
			}
			rs = statement.executeQuery("select count(*) from rajeshtable33_22 where  testVal like 'newvalue'");
			rs.next();
			int i = rs.getInt(1);
			statement.executeUpdate("delete from rajeshtable33_22");
			if((i==0)&&(!h.contains("newvalue1")))
			{
				System.out.println("TESTCASE 033.22 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 033.22 -> FAILED");
				System.out.println("033.22 FLAG IS ; "+h.contains("value1"));
				System.out.println("033.22 COUNT IS ;"+i);
			}
		}
		catch(Exception m)
		{
			m.printStackTrace();
		}
		finally
		{
			try
			{
				rs.close();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			rs = null;
			h = null;
		}
	}


	private void testCase33_25()
	{
		DBHashtable h = null;
		ResultSet rs = null;
		try
		{
			h = createDBHashTable("rajeshtable33_25","testKey","testVal",false,null,true);
			h.put("key1","value1");
			try
			{
				t.begin();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			
			h.clear();
			try
			{
				t.commit();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			rs = statement.executeQuery("select count(*) from rajeshtable33_25 where testVal like 'value1'");
			rs.next();
			int i = rs.getInt(1);
			statement.executeUpdate("delete from rajeshtable33_25");
			if((i==0)&&(!h.contains("value1")))
			{
				System.out.println("TESTCASE 033.25 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 033.25 -> FAILED");
				System.out.println("033.25 FLAG IS ; "+h.contains("value1"));
				System.out.println("033.25 COUNT IS ;"+i);
			}
		}
		catch(Exception m)
		{
			m.printStackTrace();
		}
		finally
		{
			try
			{
				rs.close();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			rs = null;
			h = null;
		}
	}

    private void testCase33_26()
    {
        try
        {
            DBHashtable h = createDBHashTable("rajeshtable33_26","testKey","testVal",false,null,true);
            Runner1 r1 = new Runner1(statement, h, t);
            r1.start();
            try
            {
                Thread.sleep(2000);
            } catch (Exception e){}
            Runner2 r2 = new Runner2(statement, h, t);
            r2.start();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

	private void testCase33_27()
	{
		DBHashtable h = null;
		ResultSet rs = null;
		Enumeration en = null;
		try
		{
			h = createDBHashTable("rajeshtable33_27","testKey","testVal",false,null,true);
			h.put("key1","value1");
			try
			{
				t.begin();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			h.clear();
			try
			{
				t.rollback("test rollback");
			}
			catch(Exception l)
			{
                //				l.printStackTrace();
			}
			rs = statement.executeQuery("select count(*) from rajeshtable33_27 where testVal like 'value1'");
			rs.next();
			int i = rs.getInt(1);
			int j = h.size();
			statement.executeUpdate("delete from rajeshtable33_27");
			boolean flag = h.contains("value1");
			if((i==1)&&(flag)&&(j==1))
			{
				System.out.println("TESTCASE 033.27 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 033.27 -> FAILED");
				System.out.println("033.27 FLAG IS ; "+flag);
				System.out.println("033.27 COUNT IS ;"+i);
				en = h.elements();
				while(en.hasMoreElements())
				{
					System.out.println("ELEMENT 33.27 ; "+en.nextElement());
				}
			}
		}
		catch(Exception m)
		{
			m.printStackTrace();
		}
		finally
		{
			try
			{
				rs.close();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			rs = null;
			h = null;
		}
	}

	private void testCase33_30()
	{
		DBHashtable h = null;
		try
		{
			h = createDBHashTable("rajeshtable33_30","testKey","testVal",false,null,true);
			h.put("key1","value1");
			try
			{
				t.begin();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			h.clearCache();
			try
			{
				t.commit();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			statement.executeUpdate("delete from rajeshtable33_30");
			if(!(h.contains("value1")))
			{
				System.out.println("TESTCASE 033.30 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 033.30 -> FAILED");
				System.out.println("033.30 FLAG IS ; "+h.contains("value1"));
			}
		}
		catch(Exception m)
		{
			m.printStackTrace();
		}
		finally
		{
			h = null;
		}
	}

	private void testCase33_32()
	{
		DBHashtable h = null;
		try
		{
			h = createDBHashTable("rajeshtable33_32","testKey","testVal",false,null,true);
			h.put("key1","value1");
			h.clearCache();
			try
			{
				t.begin();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			h.restore();
			try
			{
				t.commit();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			statement.executeUpdate("delete from rajeshtable33_32");
			if((h.contains("value1")))
			{
				System.out.println("TESTCASE 033.32 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 033.32 -> FAILED");
				System.out.println("033.32 FLAG IS ; "+h.contains("value1"));
			}
		}
		catch(Exception m)
		{
			m.printStackTrace();
		}
		finally
		{
			h = null;
		}
	}

    private void testCase33_33()
	{
		DBHashtable h = null;
        ResultSet rs = null;
		try
		{
			h = createDBHashTable("rajeshtable33_33","testKey","testVal",false,null,true);
            Properties props = new Properties();
            for (int i = 0; i < 10; i++)
            {
                props.setProperty("Name-" + i, "Value-" + i);
            }
            t.begin();
            h.add(props);
            t.commit();

			rs = statement.executeQuery("select count(*) from rajeshtable33_33 where testVal like 'Value-%'");
			rs.next();
			int count = rs.getInt(1);
            boolean isObjectPresent = true;
            for (int i = 0; i < 10; i++)
            {
                isObjectPresent = isObjectPresent && h.contains("Value-" + i);
            }

			if((count==10) && isObjectPresent)
			{
				System.out.println("TESTCASE 033.33 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 033.33 -> FAILED");
				System.out.println("033.33 isObjectPresent IS ; " + isObjectPresent);
				System.out.println("033.33 COUNT IS ;"+count);
			}
		}
		catch(Exception m)
		{
			m.printStackTrace();
		}
		finally
		{
			h = null;
            try
            {
                rs.close();
            }catch (Exception e){}
		}
	}

    private void testCase33_34()
	{
		DBHashtable h = null;
        ResultSet rs = null;
		try
		{
			h = createDBHashTable("rajeshtable33_34","testKey","testVal",false,null,true);
            Properties props = new Properties();
            for (int i = 0; i < 10; i++)
            {
                props.setProperty("Name-" + i, "Value-" + i);
            }
            t.begin();
            h.add(props);
            try
            {
                t.rollback();
            } catch (Exception e){}

			rs = statement.executeQuery("select count(*) from rajeshtable33_34 where testVal like 'Value-%'");
			rs.next();
			int count = rs.getInt(1);
            boolean isObjectPresent = false;
            for (int i = 0; i < 10; i++)
            {
                isObjectPresent = isObjectPresent || h.contains("Value-" + i);
            }

			if((count==0) && !isObjectPresent)
			{
				System.out.println("TESTCASE 033.34 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 033.34 -> FAILED");
				System.out.println("033.34 isObjectPresent IS ; " + isObjectPresent);
				System.out.println("033.34 COUNT IS ;"+count);
			}
		}
		catch(Exception m)
		{
			m.printStackTrace();
		}
		finally
		{
			h = null;
            try
            {
                rs.close();
            }catch (Exception e){}
		}
	}


    private void testCase33_35()
	{
		DBHashtable h = null;
        ResultSet rs = null;
		try
		{
			h = createDBHashTable("rajeshtable33_35","testKey","testVal",false,null,true);
            Properties props = new Properties();
            for (int i = 0; i < 10; i++)
            {
                props.setProperty("Name-" + i, "Value-" + i);
            }
            t.begin();
            h.add(props);
            boolean isObjectPresent = true;
            for (int i = 0; i < 10; i++)
            {
                isObjectPresent = isObjectPresent && h.contains("Value-" + i);
            }
            try
            {
                t.rollback();
            }
            catch (Exception e){}

			rs = statement.executeQuery("select count(*) from rajeshtable33_35 where testVal like 'Value-%'");
			rs.next();
			int i = rs.getInt(1);

			if((i==0) && isObjectPresent)
			{
				System.out.println("TESTCASE 033.35 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 033.35 -> FAILED");
				System.out.println("033.35 isObjectPresent IS ; " + isObjectPresent);
				System.out.println("033.35 COUNT IS ;"+i);
			}
		}
		catch(Exception m)
		{
			m.printStackTrace();
		}
		finally
		{
			h = null;
            try
            {
                rs.close();
            }catch (Exception e){}
		}
	}

    private void testCase33_36()
	{
		DBHashtable h = null;
        ResultSet rs = null;
		try
		{
			h = createDBHashTable("rajeshtable33_36","testKey","testVal",false,null,true);
            Properties props = new Properties();
            for (int i = 0; i < 10; i++)
            {
                h.put("Name-" + i, "Value-" + i);
            }

            Vector vect = new Vector();
            for (int j = 0; j < 5; j++)
            {
                vect.add("Name-" + j);
            }
            t.begin();
            h.remove(vect);
            t.commit();

			rs = statement.executeQuery("select count(*) from rajeshtable33_36 where testVal like 'Value-%'");
			rs.next();
			int count = rs.getInt(1);
            boolean isObjectPresent = false;

            for (int i = 0; i < 5; i++)
            {
                if (h.contains("Value-" + i))
                {
                    System.out.println("Value-" + i);
                }
                isObjectPresent = isObjectPresent || h.contains("Value-" + i);
            }

			if((count==5) && !isObjectPresent)
			{
				System.out.println("TESTCASE 033.36 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 033.36 -> FAILED");
				System.out.println("033.36 isObjectPresent IS ; " + isObjectPresent);
				System.out.println("033.36 COUNT IS ;"+count);
			}
		}
		catch(Exception m)
		{
			m.printStackTrace();
		}
		finally
		{
			h = null;
            try
            {
                rs.close();
            }catch (Exception e){}
		}
	}

    private void testCase33_37()
	{
		DBHashtable h = null;
        ResultSet rs = null;
		try
		{
			h = createDBHashTable("rajeshtable33_37","testKey","testVal",false,null,true);
            Properties props = new Properties();
            for (int i = 0; i < 10; i++)
            {
                h.put("Name-" + i, "Value-" + i);
            }

            Vector vect = new Vector();
            for (int j = 0; j < 5; j++)
            {
                vect.add("Name-" + j);
            }
            t.begin();
            h.remove(vect);
            try
            {
                t.rollback();
            }catch (Exception e){}

			rs = statement.executeQuery("select count(*) from rajeshtable33_37 where testVal like 'Value-%'");
			rs.next();
			int count = rs.getInt(1);
            boolean isObjectPresent = true;

            for (int i = 0; i < 10; i++)
            {
                isObjectPresent = isObjectPresent && h.contains("Value-" + i);
            }

			if((count==10) && isObjectPresent)
			{
				System.out.println("TESTCASE 033.37 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 033.37 -> FAILED");
				System.out.println("033.37 isObjectPresent IS ; " + isObjectPresent);
				System.out.println("033.37 COUNT IS ;"+count);
			}
		}
		catch(Exception m)
		{
			m.printStackTrace();
		}
		finally
		{
			h = null;
            try
            {
                rs.close();
            }catch (Exception e){}
		}
	}

    private void testCase33_38()
	{
		DBHashtable h = null;
        ResultSet rs = null;
		try
		{
			h = createDBHashTable("rajeshtable33_38","testKey","testVal",false,null,true);
            Properties props = new Properties();
            for (int i = 0; i < 10; i++)
            {
                h.put("Name-" + i, "Value-" + i);
            }

            Vector vect = new Vector();
            for (int j = 0; j < 5; j++)
            {
                vect.add("JunkName-" + j);
            }

            t.begin();
            try
            {
                h.remove(vect);
            }
            catch (Exception e)
            {
                System.out.println("TESTCASE 033.38 -> Exception thrown : " + e);
                System.out.println("TESTCASE 033.38 -> PASSED");
                return;
            }
            t.commit();

            System.out.println("TESTCASE 033.38 -> FAILED. No Exception thrown during remove.");
		}
		catch(Exception m)
		{
			m.printStackTrace();
		}
		finally
		{
			h = null;
            try
            {
                rs.close();
            }catch (Exception e){}
		}
	}
}

class Runner1 extends Thread
{
    DBHashtable ht = null;
    TransactionAPI t = null;
    Statement statement = null;

    public Runner1(Statement statement, DBHashtable ht, TransactionAPI t)
    {
        this.ht = ht;
        this.t = t;
        this.statement = statement;
    }

    public void run()
    {
        try
        {
            ht.add("key111", "value111");
            ht.add("key222", "value222");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        try
        {
            System.out.println("ht.size() : " + ht.size());
            t.begin();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        try
        {
            ht.clear();
            System.out.println("ht.size() : " + ht.size());
            Thread.sleep(10000);
        } catch (Exception e){}

        try
        {
            t.commit();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

class Runner2 extends Thread
{
    DBHashtable ht = null;
    TransactionAPI t = null;
    Statement statement = null;

    public Runner2(Statement statement, DBHashtable ht, TransactionAPI t)
    {
        this.ht = ht;
        this.t = t;
        this.statement = statement;
    }

    public void run()
    {
        try
        {
            Thread.sleep(2000);
        } catch (Exception e) {}

        ResultSet rs = null;
        try
        {
            rs = statement.executeQuery("select count(*) from rajeshtable33_26");
            rs.next();
            int i = rs.getInt(1);
            statement.executeUpdate("delete from rajeshtable33_26");
            if ((i == 2) && (ht.get("key111").equals("value111")))
            {
                System.out.println("{DBHashtable] :: TESTCASE :: 033_26 -> PASSED");
            }
            else
            {
                System.out.println("{DBHashtable] :: TESTCASE :: 033_26 -> FAILED");
                System.out.println("i : " + i);
                System.out.println("ht.get : " + ht.get("key111"));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (rs != null)
            {
                try
                {
                    rs.close();
                }
                catch (Exception e){}
            }
        }
    }
}
