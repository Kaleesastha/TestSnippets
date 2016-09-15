package test;

import com.adventnet.nms.store.DBVector;
import com.adventnet.nms.store.NmsStorageException;
import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.util.RunProcessInterface;
import com.adventnet.nms.store.relational.RelationalAPI;
import com.adventnet.management.transaction.ConnectionPool;
import com.adventnet.management.transaction.TransactionAPI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Vector;

public class TestDBVector implements RunProcessInterface,Runnable
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
			System.out.println("TestCases in sf_strg_dbvector TestPlan have been tested ");
			System.out.println("Time Taken to validate the TestPlan sf_strg_dbvector : "+(time2-time1));
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
			try
			{
				statement.executeUpdate("drop table rajeshtable10");
			}
			catch(Exception m)
			{
				m.printStackTrace();
			}
			try
			{
				statement.executeUpdate("drop table rajeshtable10_1");
			}
			catch(Exception m)
			{
				m.printStackTrace();
			}
			try
			{
				statement.executeUpdate("drop table rajeshtable10_2");
			}
			catch(Exception m)
			{
				m.printStackTrace();
			}
			try{
				statement.executeUpdate("drop table rajeshtable10_3");
			}
			catch(Exception m)
			{
				m.printStackTrace();
			}
			try
			{
				statement.executeUpdate("drop table rajeshtable10_4");
			}
			catch(Exception m)
			{
				m.printStackTrace();
			}
			try
			{
				statement.executeUpdate("drop table rajeshtable11");
			}
			catch(Exception m)
			{
				m.printStackTrace();
			}
			try
			{
				statement.executeUpdate("drop table rajeshtable11_1");
			}
			catch(Exception m)
			{
				m.printStackTrace();
			}
			try
			{
				statement.executeUpdate("drop table rajeshtable11_2");
			}
			catch(Exception m)
			{
				m.printStackTrace();
			}
			try
			{
				statement.executeUpdate("drop table rajeshtable11_3");
			}
			catch(Exception m)
			{
				m.printStackTrace();
			}
			try
			{
				statement.executeUpdate("drop table rajeshtable11_4");
			}
			catch(Exception m)
			{
				m.printStackTrace();
			}
			try
			{
				statement.executeUpdate("drop table rajeshtable11_5");
			}
			catch(Exception m)
			{
				m.printStackTrace();
			}
			try
			{
				statement.executeUpdate("drop table rajeshtable12");
			}
			catch(Exception m)
			{
				m.printStackTrace();
			}
			try
			{
				statement.executeUpdate("drop table rajeshtable12_1");
			}
			catch(Exception m)
			{
				m.printStackTrace();
			}
			try
			{
				statement.executeUpdate("drop table rajeshtable12_2");
			}
			catch(Exception m)
			{
				m.printStackTrace();
			}
			try
			{
				statement.executeUpdate("drop table rajeshtable12_3");
			}
			catch(Exception m)
			{
				m.printStackTrace();
			}
			try
			{
				statement.executeUpdate("drop table rajeshtable13");
			}
			catch(Exception m)
			{
				m.printStackTrace();
			}
			try
			{
				statement.executeUpdate("drop table rajeshtable13_1");
			}
			catch(Exception m)
			{
				m.printStackTrace();
			}
			try
			{
				statement.executeUpdate("drop table rajeshtable13_1");
			}
			catch(Exception m)
			{
				m.printStackTrace();
			}
			try
			{
				statement.executeUpdate("drop table rajeshtable13_2");
			}
			catch(Exception m)
			{
				m.printStackTrace();
			}
			try
			{
				statement.executeUpdate("drop table rajeshtable13_3");
			}
			catch(Exception m)
			{
				m.printStackTrace();
			}
			try
			{
				statement.executeUpdate("drop table rajeshtable13_4");
			}
			catch(Exception m)
			{m.printStackTrace();
			}
			try{statement.executeUpdate("drop table rajeshtable14");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable14_1");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable14_2");}catch(Exception m){m.printStackTrace();}

			try{statement.executeUpdate("drop table rajeshtable14_3");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable14_4");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable15");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable15_1");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable15_2");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable15_3");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable15_4");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable15_5");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable15_6");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable16");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable16_1");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable16_2");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable16_3");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable16_4");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable18A");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable18A_1");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable18A_2");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable18A_3");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable18B");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable18C");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable18C_1");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable18C_2");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable18C_3");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable18_1");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable18_16");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable18_16_1");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable18_17");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable18_18");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable18_18_1");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable18_18_2");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable18_18_3");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable18_18_4");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable18_1_1");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable18_2");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable18_21");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable18_21_1");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable18_21_2");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable18_21_3");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable18_21_4");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable18_21_6");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable18_21_7");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable18_21_8");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable18_3");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable18_4");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable18_4_2");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable18_4_3");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable18_4_4");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable18_5");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable18_5_1");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable18_6");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable18_7");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable18_7_1");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable18_7_3");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable18_7_4");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable18_7_5");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable4_1");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable4_2");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable4_3");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable5");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable5_1");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable5_2");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable6");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable6_1");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable7");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable7_1");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable7_2");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable7_3");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable7_4");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable7_5");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable8");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable9");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable9_1");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable9_2");}catch(Exception m){m.printStackTrace();}
			try{statement.executeUpdate("drop table rajeshtable18_21_5");}catch(Exception m){m.printStackTrace();}
		}
		catch(Exception u)
		{
			u.printStackTrace();
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

	private void callMethods()
	{
		try
		{

			long time1 = System.currentTimeMillis();
            /*testCase4_1();
              testCase4_2();
              testCase4_3();
              //testCase4_4();
              testCase5();
              testCase5_1();
              testCase5_2();
              //testCase5_3();
              testCase6();
              testCase6_1();
              testCase7();
              testCase7_1();
              testCase7_2();
              testCase7_3();
              testCase7_4();
              testCase7_5(); 
              testCase8();
              testCase8_1();
              testCase9();
              testCase9_1();
              testCase9_2();
              testCase10();
              testCase10_1();
              testCase10_2();
              testCase10_3();
              testCase10_4(); 
              testCase11();
              testCase11_1();
              testCase11_2();
              testCase11_3();
              testCase11_4();
              testCase11_5(); 
              testCase12();
              testCase12_1();
              testCase12_2();
              testCase12_3();
              testCase13();
              testCase13_1();
              testCase13_2();
              testCase13_3();
              testCase13_4();
              testCase14();
              testCase14_1();
              testCase14_2();
              testCase14_3();
              testCase14_4();
              testCase15();
              testCase15_1();
              testCase15_2();
              testCase15_3();
              testCase15_4();
              testCase15_5();
              testCase15_6();
              testCase16();
              testCase16_1();
              testCase16_2();
              testCase16_3();
              testCase16_4();
              testCase18A();
              testCase18A_1();
              testCase18A_2();
              testCase18A_3();
              testCase18B();
              testCase18B_1();  
              testCase18C();
              testCase18C_1();
              testCase18C_2();
              testCase18C_3();
              testCase18D();
              testCase18D_1();
              testCase18_1();
              testCase18_1_1();
              testCase18_2();
              testCase18_3();
              testCase18_4(); 
              testCase18_4_2();
              testCase18_4_3();
              testCase18_4_4();
              testCase18_5();
              testCase18_5_1();
              testCase18_6();
              testCase18_7();
              testCase18_7_1();
              testCase18_7_3();
              testCase18_7_4();
              testCase18_7_5(); 
              testCase18_16();
              testCase18_16_1(); 
              testCase18_17(); 
              testCase18_18();
              testCase18_18_1(); 
              testCase18_18_2();
              testCase18_18_3();
              testCase18_18_4(); 
            */

            /*testCase18_21();
              testCase18_21_1();                                
              testCase18_21_4();
              testCase18_21_2();
              testCase18_21_3();
              testCase18_21_6();
              testCase18_21_7();    
              testCase18_21_8();*/


			long time2 = System.currentTimeMillis();
			System.out.println("Time Taken to test all the testcases : "+(time2-time1));
		}
		catch(Exception r)
		{
			r.printStackTrace();
		}
	}

	private void testCase4_1()
	{
		try
		{
			statement.executeUpdate("create table rajeshtable4_1(VALUESTRING varchar(20))");
			statement.executeUpdate("insert into rajeshtable4_1 values('element1')");
			DBVector d = new DBVector("rajeshtable4_1");
			if(d.contains("element1"))
			{
				System.out.println("TESTCASE 004.1 -> PASSED");
			}
			d = null;
		}
		catch(Exception r)
		{
			r.printStackTrace();
		}
	}

	private void testCase4_2()
	{
		ResultSet rs = null;
		DBVector d = null;
		try
		{

			statement.executeUpdate("create table rajeshtable4_2(VALUESTRING varchar(20))");
			statement.executeUpdate("insert into rajeshtable4_2 values('element1')");
			statement.executeUpdate("insert into rajeshtable4_2 values('element2')");
			statement.executeUpdate("insert into rajeshtable4_2 values('element3')");
			statement.executeUpdate("insert into rajeshtable4_2 values('element4')");
			System.out.println("Records Inserted");
			d = new DBVector("rajeshtable4_2");
			d.addElement("testElement4_2");
			rs = statement.executeQuery("select count(*) from rajeshtable4_2");
			rs.next();
			int cnt1 = rs.getInt(1);

			if((d.contains("testElement4_2"))&&(cnt1==5))
			{
				System.out.println("TESTCASE 004.2 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 004.2 -> FAILED");
			}

		}
		catch(Exception i)
		{
			i.printStackTrace();
		}
		finally
		{
			try
			{
				if(rs!=null)
				{
					rs.close();
				}
			}
			catch(Exception o)
			{
			}
			d = null;
		}

	}

	private void testCase4_3()
	{
		DBVector d = null;
		ResultSet rs = null;
		try
		{
			statement.executeUpdate("create table rajeshtable4_3(VALUESTRING varchar(20))");
			statement.executeUpdate("insert into rajeshtable4_3 values('element1')");
			d = new DBVector("rajeshtable4_3");
			d.removeElement("element1");
			rs = statement.executeQuery("select count(*) from rajeshtable4_3 where VALUESTRING like 'element1'");
			rs.next();
			int cnt3 = rs.getInt(1);

			if((!d.contains("element1"))&&(cnt3==0))
			{
				System.out.println("TESTCASE 004.3 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 004.3 -> FAILED");
			}
		}
		catch(Exception r)
		{
			r.printStackTrace();
		}
		finally
		{
			try
			{
				if(rs!=null)
				{
					rs.close();
				} 
			}
			catch(Exception u)
			{
			}
			d = null;
		}
	}

	/*private void testCase4_4()
      {
      DBVector d = null;
      try
      {
      d = new DBVector("aaaa");
      System.out.println("TESTCASE 004.4 -> FAILED");
      }
      catch(Exception n)
      {
      System.out.println("TESTCASE 004.4 -> PASSED");
      n.printStackTrace();
      }
      finally
      {
      d = null;
      }
      }*/

	private void testCase5()
	{
		DBVector d = null;
		ResultSet rs = null;
		try
		{
			statement.executeUpdate("create table rajeshtable5(VALUESTRING varchar(20))");
			statement.executeUpdate("insert into rajeshtable5 values('element1')");
			statement.executeUpdate("insert into rajeshtable5 values('element2')");
			d = new DBVector("rajeshtable5","VALUESTRING",false,null,false);
			boolean flag1 = false;
			boolean flag2 = false;
			Enumeration en = d.elements();
			while(en.hasMoreElements())
			{
				String temp = (String)en.nextElement();
				if(temp.equals("element1"))
				{
					flag1 = true;
				}
				if(temp.equals("element2"))
				{
					flag2 = true;
				}
			}
			if((flag1)&&(flag2))
			{
				System.out.println("TESTCASE 005 -> PASSED");
			}
		}
		catch(Exception i)
		{
			i.printStackTrace();
		}
		finally
		{
			try
			{
				if(rs!=null)
				{
					rs.close();
				} 
			}
			catch(Exception y)
			{
			}
			d = null;
		}
	}

	private void testCase5_1()
	{
		ResultSet rs = null;
		DBVector d = null;
		try
		{
			statement.executeUpdate("create table rajeshtable5_1(VALUESTRING varchar(20))");
			d = new DBVector("rajeshtable5_1","VALUESTRING",false,null,false);
			d.addElement("testElement5_1");
			rs = statement.executeQuery("select count(*) from rajeshtable5_1 where VALUESTRING like 'testElement5_1'");
			int cnt = -1;
			if(rs.next())
			{
				cnt = rs.getInt(1);
			}
			if((d.contains("testElement5_1"))&&(cnt==1))
			{
				System.out.println("TESTCASE 005.1 -> PASSED");
			}
		}
		catch(Exception t)
		{
			t.printStackTrace();
		}
		finally
		{
			try
			{
				if(rs!=null)
				{
					rs.close();
				} 
			}
			catch(Exception o)
			{
			}
			d = null;
		}
	}

	private void testCase5_2()
	{
		ResultSet rs = null;
		DBVector d = null;
		try
		{
			statement.executeUpdate("create table rajeshtable5_2(VALUESTRING varchar(25))");
			statement.executeUpdate("insert into rajeshtable5_2 values('element1')");
			d = new DBVector("rajeshtable5_2","VALUESTRING",false,null,false);
			d.removeElement("element1");
			rs = statement.executeQuery("select count(*) from rajeshtable5_2 where VALUESTRING like 'element1'");
			rs.next();
			int cnt = rs.getInt(1);
			
			if((!d.contains("element1"))&&(cnt==0))
			{
				System.out.println("TESTCASE 005.2 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 005.2 -> FAILED");
			}

		}
		catch(Exception t)
		{
			t.printStackTrace();
		}
		finally
		{
			try
			{
				if(rs!=null)
				{
					rs.close();
				} 
			}
			catch(Exception o)
			{
			}
			d = null;
		}
	}

	/*private void testCase5_3()
      {
      DBVector d = null;
      try
      {
      d = new DBVector("tableee","VALUESTRING",false,null,false);
      System.out.println("TESTCASE 005.3 -> FAILED(No Exception thrown)");
      }
      catch(Exception t)
      {
      t.printStackTrace();
      System.out.println("TESTCASE 005.3 -> PASSED");
      }
      finally
      {
      d = null;

      }
      }*/

	private void testCase6()
	{
		ResultSet rs = null;
		DBVector d = null;
		try
		{
			d = new DBVector("rajeshtable6","testCol",false,null,true);
			d.addElement("testElement6");
			rs = statement.executeQuery("select count(*) from rajeshtable6 where testCol like 'testElement6'");
			rs.next();
			int cnt = rs.getInt(1);
			if((d.contains("testElement6"))&&(cnt==1))
			{
				System.out.println("TESTCASE 006 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 006 -> FAILED");
			}
		}
		catch(Exception o)
		{
			o.printStackTrace();
		}
		finally
		{
			d = null;
			try
			{
				if(rs!=null)
				{
					rs.close();
				} 
			}
			catch(Exception o)
			{
			}
		}
	}

	private void testCase6_1()
	{
		ResultSet rs = null;
		ResultSet rs1 = null;
		DBVector d = null;
		try
		{
			d = new DBVector("rajeshtable6_1","testCol",false,null,true);
			d.addElement("testElement6_1");
			//statement.executeUpdate("insert into rajeshtable6_1 values('testElement6_1')");
			
			rs = statement.executeQuery("select count(*) from rajeshtable6_1 where testCol like 'testElement6_1'");
			rs.next();
			int cnt = rs.getInt(1);
			if((d.contains("testElement6_1"))&&(cnt==1))
			{
				System.out.println("TESTCASE 006.1 -> After adding an element");
			}
			d.removeElement("testElement6_1");
			rs1 = statement.executeQuery("select count(*) from rajeshtable6_1 where testCol like 'testElement6_1'");
			rs1.next();
			int cnt1 = rs1.getInt(1);
			if((!d.contains("testElement6_1"))&&(cnt1==0))
			{
				System.out.println("TESTCASE 006.1 -> PASSED");
			}
			else
			{				
				System.out.println("TESTCASE 006.1 -> FAILED");
			}
			
		}
		catch(Exception o)
		{
			o.printStackTrace();
		}
		finally
		{
			d = null;
			try
			{
				if(rs!=null)
				{
					rs.close();
				} 
				if(rs1!=null)
				{
					rs1.close();
				} 
			}
			catch(Exception g)
			{
			}
		}
	}


	private void testCase7()
	{
		DBVector d = null;
		try
		{
			statement.executeUpdate("create table rajeshtable7(VALUESTRING varchar(20))");
			statement.executeUpdate("insert into rajeshtable7 values('element1')");
			d = new DBVector("rajeshtable7","VALUESTRING",true,null,false);
			if(d.contains("element1"))
			{
				System.out.println("TESTCASE 007 -> PASSED");
			}
		}
		catch(Exception r)
		{
			r.printStackTrace();
		}
		finally
		{
			d = null;
		}
	}

	private void testCase7_1()
	{
		DBVector d = null;
		try
		{
			statement.executeUpdate("create table rajeshtable7_1(VALUESTRING varchar(20))");
			statement.executeUpdate("insert into rajeshtable7_1 values('element1')");
			d = new DBVector("rajeshtable7_1","VALUESTRING",true,null,false);
			d.addElement("element1");
			System.out.println("TESTCASE 007.1 - > FAILED(No Exception thrown)");
		}
		catch(Exception r)
		{
			r.printStackTrace();
			System.out.println("TESTCASE 007.1 -> PASSED");
		}
		finally
		{
			d = null;
		}

	}

	private void testCase7_2()
	{
		DBVector d = null;
		try
		{
			statement.executeUpdate("create table rajeshtable7_2(VALUESTRING varchar(20))");
			statement.executeUpdate("insert into rajeshtable7_2 values('element1')");
			d = new DBVector("rajeshtable7_2","VALUESTRING",true,null,false);
			d.removeElement("element1");
			System.out.println("TESTCASE 007.2 - > FAILED(No Exception failed)");
		}
		catch(Exception g)
		{
			g.printStackTrace();
			System.out.println("TESTCASE 007.2 -> PASSED");
		}
		finally
		{
			d = null;
		}
	}

	private void testCase7_3()
	{
		DBVector d = null;
		try
		{
			statement.executeUpdate("create table rajeshtable7_3(VALUESTRING varchar(20))");
			statement.executeUpdate("insert into rajeshtable7_3 values('element1')");
			d = new DBVector("rajeshtable7_3","VALUESTRING",true,null,true);

			if (d.contains("element1"))
			{
				System.out.println("TESTCASE 007.3 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 007.3 -> FAILED");
			}
		}
		catch(Exception i)
		{
			System.out.println("TESTCASE 007.3 -> FAILED");
			i.printStackTrace();
		}
		finally
		{
			try
			{
			}
			catch(Exception u)
			{
				u.printStackTrace();
			}
			d = null;
		}
	}

	private void testCase7_4()
	{
		DBVector d = null;
		try
		{
			statement.executeUpdate("create table rajeshtable7_4(VALUESTRING varchar(20))");
			statement.executeUpdate("insert into rajeshtable7_4 values('element1')");
			statement.executeUpdate("insert into rajeshtable7_4 values('element2')");
			statement.executeUpdate("insert into rajeshtable7_4 values('element3')");
			d = new DBVector("rajeshtable7_4","VALUESTRING",true,null,true);
			d.addElement("element4");
			System.out.println("TESTCASE 007.4 -> FAILED(No Exception thrown)");
		}
		catch(Exception l)
		{
			l.printStackTrace();
			System.out.println("TESTCASE 007.4 -> PASSED");
		}
		finally
		{
			d = null;
		}
	}

	private void testCase7_5()
	{
		DBVector d = null;
		try
		{

			statement.executeUpdate("create table rajeshtable7_5(VALUESTRING varchar(20))");
			statement.executeUpdate("insert into rajeshtable7_5 values('element1')");
			d = new DBVector("rajeshtable7_5","VALUESTRING",true,null,true);
			d.removeElement("element1");
			System.out.println("TESTCASE 007.5 -> FAILED(No Exception thrown)");
		}
		catch(Exception l)
		{
			l.printStackTrace();
			System.out.println("TESTCASE 007.5 -> PASSED");
		}
		finally
		{
			d = null;
		}
	}


	private void testCase8()
	{
		DBVector d = null;
		try
		{
			DBVector.setMaxValue(12);
			d = new DBVector("rajeshtable8","testCol",false,null,true);
			for(int i=1;i<26;i++)
			{
				d.addElement("element"+i);
			}
			statement.executeUpdate("delete from rajeshtable8");
			int tr = 0;
			int fal = 0;
			for(int i=1;i<26;i++)
			{
				if(d.contains("element"+i))
				{
					tr++;
				}
				else
				{
					fal++;
				}
			}
			if((tr==12)&&(fal==13))
			{
				System.out.println("TESTCASE 008 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 008 -> FAILED");
				System.out.println("Value of tr : "+tr);
				System.out.println("Value of fal : "+fal);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			d = null;
			DBVector.setMaxValue(1000);
		}
	}

	private void testCase8_1()
	{
		try
		{
			DBVector.setMaxValue(-112);
			System.out.println("TESTCASE 008.1 -> FAILED(No Exception thrown");
		}
		catch(Exception u)
		{
			u.printStackTrace();
			System.out.println("TESTCASE 008.1 -> PASSED");
		}
		finally
		{
			DBVector.setMaxValue(1000);
		}
	}

	private void testCase9()
	{
		DBVector d = null;
		ResultSet rs = null;
		try
		{
			d = new DBVector("rajeshtable9","testCol",false,null,true);
			d.addElement("element1");
			boolean flag1= false;
			rs = statement.executeQuery("select * from rajeshtable9");
			if(rs.next())
			{
				if(rs.getString(1).equals("element1"))
				{
					flag1 = true;
				}
			}
			statement.executeUpdate("delete from rajeshtable9");
			if((d.contains("element1"))&&(flag1))
			{
				System.out.println("TESTCASE 009 -> PASSED");
			}
		}
		catch(Exception l)
		{
			l.printStackTrace();
		}
		finally
		{
			d = null;
			try
			{
				if(rs!=null)
				{
					rs.close();
				} 
			}
			catch(Exception i)
			{
			}
		}
	}

	private void testCase9_1()
	{
		DBVector d = null;
		try
		{
			DBVector.setMaxValue(10);
			d = new DBVector("rajeshtable9_1","testCol",false,null,true);
			for(int i=1;i<26;i++)
			{
				d.addElement("element"+i);
			}
			
			ResultSet rs = statement.executeQuery("select count(*) from rajeshtable9");
			rs.next();
			System.out.println("COUNT : " + rs.getInt(1));
			boolean flag = true;
			statement.executeUpdate("delete from rajeshtable9_1");
			for(int i=1;i<26;i++)
			{
				if(!d.contains("element"+i))
				{
					flag=false;
				}
			}
			System.out.println("FLAG : " + flag);
			if(flag)
			{
				System.out.println("TESTCASE 009.1 -> PASSED");
			}
			else
			{				
				System.out.println("TESTCASE 009.1 -> FAILED");
			}
		}
		catch(Exception u)
		{
			u.printStackTrace();
		}
		finally
		{
			d = null;
		}
	}

	private void testCase9_2()
	{
		DBVector d = null;
		ResultSet rs = null;
		try
		{
			statement.executeUpdate("create table rajeshtable9_2 (VALUESTRING varchar(10))");
			d =  new DBVector("rajeshtable9_2","VALUESTRING",false,null,false);
			try
			{
				d.addElement("rajeshdrajeshdrajeshd");
			}
			catch(Exception u)
			{
				System.out.println("exception while addElement for 9.2"+u);
				u.printStackTrace();
			}
			rs = statement.executeQuery("select count(*) from rajeshtable9_2");
			String db = null;
			boolean flag = false;
			if(rs.next())
			{
				db = rs.getString(1);
				System.out.println("DB COUNT : " + db);
				if(d.contains(db))
				{
					System.out.println("inside d.contains(db)");
					flag = true;
				}
			}
			else
			{
				if((!d.contains("rajeshdraj"))&&(!d.contains("rajeshdrajeshdrajeshd")))
				{
					System.out.println("inside rajeshdraj");
					flag = true;
				}
			}
			if(flag)
			{
				System.out.println("TESTCASE 009.2 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 009.2 -> FAILED");
			}
		}
		catch(Exception o)
		{
			o.printStackTrace();
		}
		finally
		{
			d = null;
			try
			{
				if(rs!=null)
				{
					rs.close();
				} 
			}
			catch(Exception m)
			{
				m.printStackTrace();
			}
		}
	}

	private void testCase10()
	{
		DBVector d = null;
		try
		{
			d = new DBVector("rajeshtable10","testCol",false,null,true);
			for(int i=1;i<101;i++)
			{
				d.addElement("element"+i);
			}
			if(d.size()==100)
			{
				System.out.println("TESTCASE 010 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 010 -> FAILED");
				System.out.println("CNT : "+d.size());
			}
		}
		catch(Exception o)
		{
			o.printStackTrace();
		}
		finally
		{
			d = null;
		}
	}

	private void testCase10_1()
	{
		DBVector d = null;
		try
		{
			DBVector.setMaxValue(10);
			d = new DBVector("rajeshtable10_1","testCol",false,null,true);
			for(int i=1;i<21;i++)
			{
				d.addElement("element"+i);
			}
			if(d.size()==20)
			{
				System.out.println("TESTCASE 10.1 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 10.1 -> FAILED");
				System.out.println("SIZE : "+d.size());
			}
		}
		catch(Exception l)
		{
			l.printStackTrace();
		}
		finally
		{
			DBVector.setMaxValue(1000);
			d = null;
		}
	}

	private void testCase10_2()
	{
		DBVector d = null;
		try
		{
			d = new DBVector("rajeshtable10_2","testCol",false,null,true);
			for(int i=1;i<51;i++)
			{
				d.addElement("element"+i);
			}
			d.clearCache();
			if(d.size()==50)
			{
				System.out.println("TESTCASE 10.2 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 10.2 -> FAILED");
				System.out.println("SIZE : "+d.size());
			}
		}
		catch(Exception j)
		{
			j.printStackTrace();
		}
		finally
		{
			d = null;
		}
	}

	private void testCase10_3()
	{
		DBVector d = null;
		try
		{
			d = new DBVector("rajeshtable10_3","testCol",false,null,true);
			for(int i=1;i<51;i++)
			{
				d.addElement("element"+i);
			}
			d.removeAllElements();
			if(d.size()==0)
			{
				System.out.println("TESTCASE 10.3 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 10.3 -> FAILED");
				System.out.println("SIZE : "+d.size());
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

	private void testCase10_4()
	{
		DBVector d = null;
		try
		{
			d = new DBVector("rajeshtable10_4","testCol",false,null,true);
			if(d.size()==0)
			{
				System.out.println("TESTCASE 10.4 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 10.4 -> FAILED");
				System.out.println("SIZE : "+d.size());
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

	private void testCase11()
	{
		DBVector d = null;
		try
		{
			d = new DBVector("rajeshtable11","testCol",false,null,true);
			if(d.isEmpty())
			{
				System.out.println("TESTCASE 011 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 011 -> FAILED");
			}

		}
		catch(Exception o)
		{
			o.printStackTrace();
		}
	}

	private void testCase11_1()
	{
		DBVector d = null;
		try
		{
			d = new DBVector("rajeshtable11_1","testCol",false,null,true);
			d.addElement("element1");
			if(d.isEmpty())
			{
				System.out.println("TESTCASE 011.1 -> FAILED");
			}
			else
			{
				System.out.println("TESTCASE 011.1 -> PASSED");
			}
		}
		catch(Exception i)
		{
			i.printStackTrace();

		}
		finally
		{
			d = null;
		}
	}

	private void testCase11_2()
	{
		DBVector d = null;
		try
		{
			d = new DBVector("rajeshtable11_2","testCol",false,null,true);
			d.addElement("element1");
			d.clearCache();
			if(d.isEmpty())
			{
				System.out.println("TESTCASE 011.2 -> FAILED");
			}
			else
			{
				System.out.println("TESTCASE 011.2 -> PASSED");
			}
		}
		catch(Exception i)
		{
			i.printStackTrace();

		}
		finally
		{
			d = null;
		}
	}

	private void testCase11_3()
	{
		DBVector d = null;
		try
		{
			d = new DBVector("rajeshtable11_3","testCol",false,null,true);
			d.addElement("element1");
			statement.executeUpdate("delete from rajeshtable11_3");
			if(d.isEmpty())
			{
				System.out.println("TESTCASE 11.3 -> FAILED");
			}
			else
			{
				System.out.println("TESTCASE 11.3 -> PASSED");
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

	private void testCase11_4()
	{
		DBVector d = null;
		try
		{
			DBVector.setMaxValue(0);
			d = new DBVector("rajeshtable11_4","testCol",false,null,true);
			d.addElement("element1");
			statement.executeUpdate("delete from rajeshtable11_4");
			if(d.isEmpty())
			{
				System.out.println("TESTCASE 11.4 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 11.4 -> FAILED");
			}
		}
		catch(Exception l)
		{
			l.printStackTrace();
		}
		finally
		{
			d = null;
			DBVector.setMaxValue(1000);
		}
	}

	private void testCase11_5()
	{
		DBVector d = null;
		try
		{
			DBVector.setMaxValue(0);
			d = new DBVector("rajeshtable11_5","testCol",false,null,true);
			d.addElement("element1");
			if(d.isEmpty())
			{
				System.out.println("TESTCASE 11.5 -> FAILED");
			}
			else
			{
				System.out.println("TESTCASE 11.5 -> PASSED");
			}
		}
		catch(Exception l)
		{
			l.printStackTrace();
		}
		finally
		{
			d = null;
			DBVector.setMaxValue(1000);
		}
	}

	private void testCase12()
	{
		DBVector d = null;
		ResultSet rs = null;
		try
		{
			d = new DBVector("rajeshtable12","testCol",false,null,true);
			for(int i=1;i<15;i++)
			{
				d.addElement("element"+i);
			}
			d.removeAllElements();
			rs = statement.executeQuery("select count(*) from rajeshtable12");
			rs.next();
			int cnt = rs.getInt(1);
			boolean flag  = true;
			for(int i=1;i<15;i++)
			{
				if(d.contains("element"+i))
				{
					flag = false;
				}
			}
			if((flag)&&(cnt==0))
			{
				System.out.println("TESTCASE 012 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 012 -> FAILED");
			}
		}
		catch(Exception ee)
		{
			ee.printStackTrace();
		}
		finally
		{
			d = null;
			try
			{
				if(rs!=null)
				{
					rs.close();
				} 
			}
			catch(Exception l)
			{
			}
		}
	}


	private void testCase12_1()
	{
		DBVector d = null;
		try
		{
			d = new DBVector("rajeshtable12_1","testCol",false,null,true);
			for(int i=1;i<15;i++)
			{
				d.addElement("element"+i);
			}
			statement.executeUpdate("delete from  rajeshtable12_1");
			d.removeAllElements();
			boolean flag  = true;
			for(int i=1;i<15;i++)
			{
				if(d.contains("element"+i))
				{
					flag = false;
				}
			}
			if((flag))
			{
				System.out.println("TESTCASE 012.1 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 012.1 -> FAILED");
			}
		}
		catch(Exception ee)
		{
			ee.printStackTrace();
		}
		finally
		{
			d = null;
		}
	}

	private void testCase12_2()
	{
		DBVector d = null;
		ResultSet rs = null;
		try
		{
			d = new DBVector("rajeshtable12_2","testCol",false,null,true);
			for(int i=1;i<15;i++)
			{
				d.addElement("element"+i);
			}
			d.clearCache();
			d.removeAllElements();
			rs = statement.executeQuery("select count(*) from rajeshtable12_2");
			rs.next();
			int cnt = rs.getInt(1);
			if(cnt==0)
			{
				System.out.println("TESTCASE 012.2 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 012.2 -> FAILED");
			}
		}
		catch(Exception ee)
		{
			ee.printStackTrace();
		}
		finally
		{
			d = null;
			try
			{
				if(rs!=null)
				{
					rs.close();
				} 
			}
			catch(Exception l)
			{
			}
		}
	}

	private void testCase12_3()
	{
		DBVector d = null;
		try
		{
			statement.executeUpdate("create table rajeshtable12_3(VALUESTRING varchar(20))");
			d = new DBVector("rajeshtable12_3","testCol",true,null,true);
			statement.executeUpdate("insert into rajeshtable12_3 values('element1')");
			d.removeAllElements();
			System.out.println("TESTCASE 12.3 -> FAILED(No Exception thrown)");
		}
		catch(Exception j)
		{
			System.out.println("TESTCASE 12.3 -> PASSED");
		}
		finally
		{
			d = null;
		}
	}

	private void testCase13()
	{
		DBVector d = null;
		try
		{
			d = new DBVector("rajeshtable13","testCol",false,null,true);
			d.addElement("element1");
			if(d.contains("element1"))
			{
				System.out.println("TESTCASE 013 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 013 -> FAILED");
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

	private void testCase13_1()
	{
		DBVector d = null;
		try
		{
			d = new DBVector("rajeshtable13_1","testCol",false,null,true);
			if(d.contains("element1"))
			{
				System.out.println("TESTCASE 013.1 -> FAILED");
			}
			else
			{
				System.out.println("TESTCASE 013.1 -> PASSED");
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

	private void testCase13_2()
	{
		DBVector d = null;
		try
		{
			d = new DBVector("rajeshtable13_2","testCol",false,null,true);
			d.addElement("element1");
			d.removeElement("element1");
			if(d.contains("element1"))
			{
				System.out.println("TESTCASE 013.2 -> FAILED");
			}
			else
			{
				System.out.println("TESTCASE 013.2 -> PASSED");
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

	private void testCase13_3()
	{	
		DBVector d = null;
		try
		{
			d = new DBVector("rajeshtable13_3","testCol",false,null,true);
			d.addElement("element1");
			statement.executeUpdate("delete from rajeshtable13_3");
			if(d.contains("element1"))
			{
				System.out.println("TESTCASE 013.3 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 013.3 -> FAILED");
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

	private void testCase13_4()
	{	
		DBVector d = null;
		try
		{
			d = new DBVector("rajeshtable13_4","testCol",false,null,true);
			d.addElement("element1");
			d.clearCache();
			if(d.contains("element1"))
			{
				System.out.println("TESTCASE 013.4 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 013.4 -> FAILED");
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

	private void testCase14()
	{
		DBVector d = null;
		try
		{
			d = new DBVector("rajeshtable14","testCol",false,null,true);
			d.addElement("element1");
			Enumeration en = d.elements();
			while(en.hasMoreElements())
			{
				if(en.nextElement().equals("element1"))
				{
					System.out.println("TESTCASE 014 -> PASSED");
				}
				else
				{
					System.out.println("TESTCASE 014 -> FAILED");
				}
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

	private void testCase14_1()
	{
		DBVector d = null;
		try
		{
			DBVector.setMaxValue(10);	
			d = new DBVector("rajeshtable14_1","testCol",false,null,true);
			for(int i=1;i<51;i++)
			{
				d.addElement("element"+i);
			}
			Enumeration en = d.elements();
			int cnt=0;
			while(en.hasMoreElements())
			{
				cnt++;
				System.out.println(en.nextElement());
			}
			if(cnt==50)
			{
				System.out.println("TESTCASE 14.1 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 14.1 -> FAILED");
				System.out.println("SIZE : "+cnt);
			}

		}
		catch(Exception l)
		{
			l.printStackTrace();
		}
		finally
		{
			d = null;
			DBVector.setMaxValue(1000);
		}
	}	

	private void testCase14_2()
	{
		DBVector d = null;
		try
		{
			DBVector.setMaxValue(0);	
			d = new DBVector("rajeshtable14_2","testCol",false,null,true);
			for(int i=1;i<51;i++)
			{
				d.addElement("element"+i);
			}
			Enumeration en = d.elements();
			int cnt=0;
			while(en.hasMoreElements())
			{
				cnt++;
				System.out.println(en.nextElement());
			}
			if(cnt==50)
			{
				System.out.println("TESTCASE 14.2 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 14.2 -> FAILED");
				System.out.println("SIZE : "+cnt);
			}

		}
		catch(Exception l)
		{
			l.printStackTrace();
		}
		finally
		{
			d = null;
			DBVector.setMaxValue(1000);
		}
	}

	private void testCase14_3()
	{
		DBVector d = null;
		try
		{
			d = new DBVector("rajeshtable14_3","testCol",false,null,true);
			for(int i=1;i<51;i++)
			{
				d.addElement("element"+i);
			}
			statement.executeUpdate("delete from  rajeshtable14_3");
			Enumeration en = d.elements();
			int cnt=0;
			while(en.hasMoreElements())
			{
				cnt++;
				System.out.println(en.nextElement());
			}
			if(cnt==50)
			{
				System.out.println("TESTCASE 14.3 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 14.3 -> FAILED");
				System.out.println("SIZE : "+cnt);
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

	private void testCase14_4()
	{
		DBVector d = null;
		try
		{
			d = new DBVector("rajeshtable14_4","testCol",false,null,true);
			for(int i=1;i<51;i++)
			{
				d.addElement("element"+i);
			}
			d.clearCache();
			Enumeration en = d.elements();
			int cnt=0;
			while(en.hasMoreElements())
			{
				cnt++;
				System.out.println(en.nextElement());
			}
			if(cnt==50)
			{
				System.out.println("TESTCASE 14.4 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 14.4 -> FAILED");
				System.out.println("SIZE : "+cnt);
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

	private void testCase15()
	{
		DBVector d = null;
		try
		{
			d = new DBVector("rajeshtable15","testCol",false,null,true);
			d.addElement("element1");
			d.addElement("element2");
			if((d.indexOf("element1")==0)&&(d.indexOf("element2")==1))
			{
				System.out.println("TESTCASE 015 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 015 -> FAILED");
				System.out.println("INDEX OF ELEMENT1 : "+d.indexOf("element1"));
				System.out.println("INDEX OF ELEMENT2 : "+d.indexOf("element2"));
			}
		}
		catch(Exception n)
		{
			n.printStackTrace();
		}
		finally
		{
			d = null;
		}
	}

	private void testCase15_1()
	{
		DBVector d = null;
		try
		{
			DBVector.setMaxValue(10);
			d = new DBVector("rajeshtable15_1","testCol",false,null,true);
			for(int i=1;i<16;i++)
			{
				d.addElement("element"+i);
			}
			if((d.indexOf("element1")==0)&&(d.indexOf("element15")==14))
			{
				System.out.println("TESTCASE 015.1 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 015.1 -> FAILED");
				System.out.println("INDEX OF ELEMENT1 : "+d.indexOf("element1"));
				System.out.println("INDEX OF ELEMENT15 : "+d.indexOf("element15"));
			}
		}
		catch(Exception n)
		{
			n.printStackTrace();
		}
		finally
		{
			d = null;
			DBVector.setMaxValue(1000);
		}
	}


	private void testCase15_2()
	{
		DBVector d = null;
		try
		{
			DBVector.setMaxValue(0);
			d = new DBVector("rajeshtable15_2","testCol",false,null,true);
			d.addElement("element1");
			if((d.indexOf("element1")==0))
			{
				System.out.println("TESTCASE 015.2 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 015.2 -> FAILED");
				System.out.println("INDEX OF ELEMENT1 : "+d.indexOf("element1"));
			}
		}
		catch(Exception n)
		{
			n.printStackTrace();
		}
		finally
		{
			d = null;
			DBVector.setMaxValue(1000);
		}
	}			

	private void testCase15_3()
	{
		DBVector d = null;
		try
		{
			d = new DBVector("rajeshtable15_3","testCol",false,null,true);
			d.addElement("element1");
			statement.executeUpdate("delete from rajeshtable15_3");
			if((d.indexOf("element1")==0))
			{
				System.out.println("TESTCASE 015.3 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 015.3 -> FAILED");
				System.out.println("INDEX OF ELEMENT1 : "+d.indexOf("element1"));
			}
		}
		catch(Exception n)
		{
			n.printStackTrace();
		}
		finally
		{
			d = null;
		}
	}

	private void testCase15_4()
	{
		DBVector d = null;
		try
		{
			d = new DBVector("rajeshtable15_4","testCol",false,null,true);
			d.addElement("element1");
			d.clearCache();
			if((d.indexOf("element1")==0))
			{
				System.out.println("TESTCASE 015.4 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 015.4 -> FAILED");
				System.out.println("INDEX OF ELEMENT1 : "+d.indexOf("element1"));
			}
		}
		catch(Exception n)
		{
			n.printStackTrace();
		}
		finally
		{
			d = null;
		}
	}

	private void testCase15_5()
	{
		DBVector d = null;
		try
		{
			d = new DBVector("rajeshtable15_5","testCol",false,null,true);
			if((d.indexOf("element1")==-1))
			{
				System.out.println("TESTCASE 015.5 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 015.5 -> FAILED");
				System.out.println("INDEX OF ELEMENT1 : "+d.indexOf("element1"));
			}
		}
		catch(Exception n)
		{
			n.printStackTrace();
		}
		finally
		{
			d = null;
		}
	}

	private void testCase15_6()
	{
		DBVector d = null;
		try
		{
			d = new DBVector("rajeshtable15_6","testCol",false,null,true);
			d.addElement("element1");
			int a = d.indexOf("element1");
			d.removeElement("element1");
			int b = d.indexOf("element1");
			if((a==0)&&(b==-1))
			{
				System.out.println("TESTCASE 015.6 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 015.6 -> FAILED");
				System.out.println("INDEX OF ELEMENT1 : "+d.indexOf("element1"));
			}
		}
		catch(Exception n)
		{
			n.printStackTrace();
		}
		finally
		{
			d = null;
		}
	}

	private void testCase16()
	{
		DBVector d = null;
		ResultSet rs = null;

		try
		{
			d = new DBVector("rajeshtable16","testCol",false,null,true);
			d.addElement("element1");
			d.removeElement("element1");
			rs = statement.executeQuery("select count(*) from rajeshtable16");
			rs.next();
			int cnt = rs.getInt(1);
			boolean flag = d.contains("element1");
			if((cnt==0)&&(!flag))
			{
				System.out.println("TESTCASE 016 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 016 -> FAILED");
				System.out.println("CNT IS : "+cnt);
				System.out.println("FLAG IS : "+flag);
			}
		}
		catch(Exception n)
		{
			n.printStackTrace();
		}
		finally
		{
			d = null;
			try
			{
				if(rs!=null)
				{
					rs.close();
				} 
			}
			catch(Exception l)
			{
			}

		}
	}

	private void testCase16_1()
	{
		ResultSet rs = null;
		DBVector d = null;
		try
		{
			d = new DBVector("rajeshtable16_1","testCol",false,null,true);
			d.addElement("element1");
			d.clearCache();
			d.removeElement("element1");
			rs = statement.executeQuery("select count(*) from rajeshtable16_1");
			rs.next();
			int cnt = rs.getInt(1);
			if((cnt==0))
			{
				System.out.println("TESTCASE 016.1 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 016.1 -> FAILED");
				System.out.println("CNT IS : "+cnt);
			}
		}
		catch(Exception n)
		{
			n.printStackTrace();
		}
		finally
		{
			d = null;
			try
			{
				if(rs!=null)
				{
					rs.close();
				} 
			}
			catch(Exception l)
			{
			}

		}
	}

	private void testCase16_2()
	{
		DBVector d = null;
		try
		{
			d = new DBVector("rajeshtable16_2","testCol",false,null,true);
			d.addElement("element1");
			statement.execute("delete from rajeshtable16_2");
			d.removeElement("element1");
			boolean flag = d.contains("element1");
			if(!flag)
			{
				System.out.println("TESTCASE 016.2 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 016.2 -> FAILED");
				System.out.println("FLAG IS : "+flag);
			}
		}
		catch(Exception n)
		{
			n.printStackTrace();
		}
		finally
		{
			d = null;
		}
	}

	private void testCase16_3()
	{
		DBVector d = null;
		try
		{
			d = new DBVector("rajeshtable16_3","testCol",false,null,true);
			d.removeElement("element1");
			System.out.println("TESTCASE 16.3 -> FAILED");
		}
		catch(Exception l)
		{
			System.out.println("TESTCASE 016.3 -> PASSED");
			l.printStackTrace();
		}
		finally
		{
			d = null;
		}
	}

	private void testCase16_4()
	{
		DBVector d = null;
		try
		{
			statement.executeUpdate("create table rajeshtable16_4(VALUESTRING varchar(20))");
			statement.executeUpdate("insert into rajeshtable16_4 values('element1')");
			d = new DBVector("rajeshtable16_4","VALUESTRING",true,null,true);
			d.removeElement("element1");
			System.out.println("TESTCASE 016.4 -> FAILED");
		}
		catch(Exception n)
		{
			System.out.println("TESTCASE 016.4 -> PASSED");
			n.printStackTrace();
		}
		finally
		{
			d = null;
		}
	}

	private void testCase18A()
	{
		DBVector d = null;
		try
		{
			statement.executeUpdate("create table rajeshtable18A (VALUESTRING varchar(20))");
			statement.executeUpdate("insert into rajeshtable18A values('element1')");
			d = new DBVector("rajeshtable18A","VALUESTRING",false,null,false);
			d.restore();
			if(d.contains("element1"))
			{
				System.out.println("TESTCASE 18A -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 18A -> FAILED");
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

	private void testCase18A_1()
	{
		DBVector d = null;
		ResultSet rs = null;
		try
		{
			d = new DBVector("rajeshtable18A_1","testCol",false,null,true);
			d.addElement("element1");
			d.clearCache();
			rs = statement.executeQuery("select count(*) from rajeshtable18A_1");
			rs.next();
			int cnt = rs.getInt(1);
			System.out.println("RSCount For TESTCASE 18A.1 " + cnt);
			d.restore();
			boolean flag = d.contains("element1");
			if(flag)
			{
				System.out.println("TESTCASE 018A.1 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 018A.1 -> FAILED");
				System.out.println("FLAG IS : "+flag);
			}
		}
		catch(Exception n)
		{
			n.printStackTrace();
		}
		finally
		{
			d = null;
		}
	}

	private void testCase18A_2()
	{
		DBVector d = null;
		try
		{
			DBVector.setMaxValue(0);
			statement.executeUpdate("create table rajeshtable18A_2 (VALUESTRING varchar(20))");
			statement.executeUpdate("insert into rajeshtable18A_2 values ('element1')");
			d = new DBVector("rajeshtable18A_2","VALUESTRING",false,null,false);
			d.restore();
			statement.executeUpdate("delete from rajeshtable18A_2");
			boolean flag = d.contains("element1");
			if(flag)
			{
				System.out.println("TESTCASE 018A.2 -> FAILED");
			}
			else
			{
				System.out.println("TESTCASE 018A.2 -> PASSED");
				System.out.println("FLAG IS : "+flag);
			}
		}
		catch(Exception n)
		{
			n.printStackTrace();
		}
		finally
		{
			d = null;
			DBVector.setMaxValue(1000);
		}
	}

	private void testCase18A_3()
	{
		DBVector d = null;
		try
		{
			statement.executeUpdate("create table rajeshtable18A_3 (VALUESTRING varchar(20))");
			statement.executeUpdate("insert into rajeshtable18A_3 values ('element1')");
			d = new DBVector("rajeshtable18A_3","VALUESTRING",true,null,false);
			d.restore();
			//statement.executeUpdate("delete from rajeshtable18A_3");
			boolean flag = d.contains("element1");
			if(!flag)
			{
				System.out.println("TESTCASE 018A.3 -> FAILED");
				System.out.println("FLAG IS : "+flag);
			}
			else
			{
				System.out.println("TESTCASE 018A.3 -> PASSED");
			}
		}
		catch(Exception n)
		{
			n.printStackTrace();
		}
		finally
		{
			d = null;
		}
	}

	private void testCase18B()
	{
		DBVector d = null;
		ResultSet rs = null;
		try
		{
			d = new DBVector("rajeshtable18B","testCol",false,null,true);
			d.addElement("element1");
			d.clearCache();
			boolean flag1 = d.contains("element1");
			rs = statement.executeQuery("select count(*) from rajeshtable18B");
			rs.next();
			int cnt = rs.getInt(1);
			statement.executeUpdate("delete from rajeshtable18B");
			boolean flag2 = d.contains("element1");
			if((flag1)&&(!flag2)&&(cnt==1))
			{
				System.out.println("TESTCASE 018B -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 018B -> FAILED");
				System.out.println("FLAG1 IS : "+flag1);
				System.out.println("FLAG2 IS : "+flag2);
				System.out.println("CNT IS : "+cnt);
			}
		}
		catch(Exception n)
		{
			n.printStackTrace();
		}
		finally
		{
			d = null;
			try
			{
				if(rs!=null)
				{
					rs.close();
				} 
			}
			catch(Exception k)
			{
			}

		}
	}

	private void testCase18B_1()
	{
		DBVector d = null;
		try
		{
			d = new DBVector("rajeshtable18B_1","testCol",true,null,true);
			d.clearCache();
			System.out.println("TESTCASE 018B.1 -> FAILED");
		}
		catch(Exception l)
		{
			System.out.println("TESTCASE 018B.1 -> PASSED");
			l.printStackTrace();
		}
		finally
		{
			d = null;
		}
	}

	private void testCase18C()
	{
		DBVector d = null;
		ResultSet rs = null;
		try
		{
			d = new DBVector("rajeshtable18C","testCol",false,null,true);
			for(int i=1;i<15;i++)
			{
				d.addElement("element"+i);
			}
			//d.removeAllElements();
			d.clear();
			rs = statement.executeQuery("select count(*) from rajeshtable18C");
			rs.next();
			int cnt = rs.getInt(1);
			
			boolean flag  = true;
			for(int i=1;i<15;i++)
			{
				if(d.contains("element"+i))
				{
					flag = false;
				}
			}
			if((flag)&&(cnt==0))
			{
				System.out.println("TESTCASE 018C -> PASSED");
			}
		}
		catch(Exception ee)
		{
			ee.printStackTrace();
		}
		finally
		{
			d = null;
			try
			{
				if(rs!=null)
				{
					rs.close();
				} 
			}
			catch(Exception l)
			{
			}
		}
	}


	private void testCase18C_1()
	{
		DBVector d = null;
		try
		{
			d = new DBVector("rajeshtable18C_1","testCol",false,null,true);
			for(int i=1;i<15;i++)
			{
				d.addElement("element"+i);
			}
			statement.executeUpdate("delete from  rajeshtable18C_1");
			//d.removeAllElements();
			d.clear();
			boolean flag  = true;
			for(int i=1;i<15;i++)
			{
				if(d.contains("element"+i))
				{
					flag = false;
				}
			}
			if((flag))
			{
				System.out.println("TESTCASE 018C.1 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 018C.1 -> FAILED");
			}
		}
		catch(Exception ee)
		{
			ee.printStackTrace();
		}
		finally
		{
			d = null;
		}
	}

	private void testCase18C_2()
	{
		DBVector d = null;
		ResultSet rs = null;
		try
		{
			d = new DBVector("rajeshtable18C_2","testCol",false,null,true);
			for(int i=1;i<15;i++)
			{
				d.addElement("element"+i);
			}
			d.clearCache();
			d.clear();
			//d.removeAllElements();

			rs = statement.executeQuery("select count(*) from rajeshtable18C_2");
			rs.next();
			int cnt = rs.getInt(1);
			if(cnt==0)
			{
				System.out.println("TESTCASE 018C.2 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 018C.2 -> FAILED");
			}
		}
		catch(Exception ee)
		{
			ee.printStackTrace();
		}
		finally
		{
			d = null;
			try
			{
				if(rs!=null)
				{
					rs.close();
				} 
			}
			catch(Exception l)
			{
			}
		}
	}

	private void testCase18C_3()
	{
		System.out.println("Inside 18C.3");
		DBVector d = null;
		ResultSet rs = null;
		try
		{
	

			d = new DBVector("rajeshtable18C_3","testCol",true,null,true);
			statement.executeUpdate("create table rajeshtable18C_3(VALUESTRING varchar(20))");
			statement.executeUpdate("insert into rajeshtable18C_3 values('element1')");
			d.clear();
			//d.removeAllElements();
			rs = statement.executeQuery("select count(*) from rajeshtable18C_3");
			rs.next();
			int cnt = rs.getInt(1);
			
			if(cnt==1)
			{
				System.out.println("TESTCASE 18C.3 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 18C.3 -> FAILED");
				System.out.println("TestCASE 18C.3 : COUNT is is : "+cnt);
			}
		}
		catch(Exception s)
		{
			s.printStackTrace();
		}
		finally
		{
			d = null;
			try
			{
				if(rs!=null)
				{
					rs.close();
				} 
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}

		}
	}

	private void testCase18D()
	{
		try
		{
			if(DBVector.getMaxValue()==1000)
			{
				System.out.println("TESTCASE 018D -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 018D -> FAILED");
				System.out.println("value : "+DBVector.getMaxValue());
			}
		}
		catch(Exception k)
		{
			k.printStackTrace();
		}
	}

	private void testCase18D_1()
	{
		try
		{
			DBVector.setMaxValue(111);
			if(DBVector.getMaxValue()==111)
			{
				System.out.println("TESTCASE 018D.1 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 018D.1 -> FAILED");
				System.out.println("value : "+DBVector.getMaxValue());
			}
		}
		catch(Exception k)
		{
			k.printStackTrace();
		}
		finally
		{
			DBVector.setMaxValue(1000);
		}
	}

	private void testCase18_1()
	{
		ResultSet rs = null;
		DBVector d = null;
		try
		{

			d = new DBVector("rajeshtable18_1","testCol",false,null,true);
			try
			{
				t.begin();	
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			d.addElement("testElement18_1");
			try
			{
				t.commit();
			}
			catch(Exception m)
			{
				m.printStackTrace();
			}
			rs = statement.executeQuery("select * from rajeshtable18_1");
			String val = null;
			if(rs.next())
			{
				val = rs.getString(1);
			}
			if(val!=null && ((d.contains("testElement18_1"))&&(val.equals("testElement18_1"))))
			{
				System.out.println("TESTCASE 018.1 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 018.1 -> FAILED");
				System.out.println("VALUE FOR TESTCASE 018.1  : "+val);
			}

		}
		catch(Exception i)
		{
			i.printStackTrace();
		}
		finally
		{
			try
			{
				if(rs!=null)
				{
					rs.close();
				} 
			}
			catch(Exception o)
			{
			}
			d = null;
		}

	}

	private void testCase18_1_1()
	{
		ResultSet rs = null;
		DBVector d = null;
		Statement stmt = null;
		try
		{
			d = new DBVector("rajeshtable18_1_1","testCol",false,null,true);
			try
			{
				t.begin();	
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			d.addElement("testElement18_1_1");
			connection = connectionpool.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select count(*) from rajeshtable18_1_1");
			rs.next();
			int cnt = rs.getInt(1);
			
			if((d.contains("testElement18_1_1"))&&(cnt==1))
			{
				System.out.println("TESTCASE 018.1.1 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 018.1.1 -> FAILED");
				System.out.println("TESTCASE 18.1.1 : REASON : CNT : "+cnt+" : d.conta : "+d.contains("testElement18_1_1"));
			}
			try
			{
				t.commit();
			}
			catch(Exception m)
			{
				m.printStackTrace();
			}

		}
		catch(Exception i)
		{
			i.printStackTrace();
		}
		finally
		{
			try
			{
				if(rs!=null)
				{
					rs.close();
				}
				if(stmt!=null)
				{
					stmt.close();
				}
			}
			catch(Exception o)
			{
			}
			d = null;
		}

	}

	private void testCase18_2()
	{
		ResultSet rs = null;
		DBVector d = null;
		try
		{

			d = new DBVector("rajeshtable18_2","testCol",false,null,true);
			try
			{
				t.begin();	
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			d.addElement("testElement18_2");
			try
			{
				t.rollback("TESTCASE ID : 18.2 :testing rollback");
			}
			catch(Exception m)
			{
				//	m.printStackTrace();
			}
			rs = statement.executeQuery("select count(*) from rajeshtable18_2");
			rs.next();
			int cnt  = rs.getInt(1);
			
			if((!d.contains("testElement18_2"))&&(cnt==0))
			{
				System.out.println("TESTCASE 018.2 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 018.2 -> FAILED");
				System.out.println("TESTCASE 18.2 : REASON : CNT : "+cnt+" : d.conta : "+d.contains("testElement18_2"));
			}

		}
		catch(Exception i)
		{
			i.printStackTrace();
		}
		finally
		{
			try
			{
				if(rs!=null)
				{
					rs.close();
				} 
			}
			catch(Exception o)
			{
			}
			d = null;
		}

	}

	private void testCase18_3()
	{
		ResultSet rs = null;
		DBVector d = null;
		try
		{

			d = new DBVector("rajeshtable18_3","testCol",false,null,true);
			try
			{
				t.begin();	
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			d.addElement("testElement18_3");
			try
			{
				Thread.sleep(21000);
			}
			catch(Exception m)
			{
				m.printStackTrace();
			}
			try
			{
				t.commit();
			}
			catch(Exception m)
			{
				m.printStackTrace();
			}
			rs = statement.executeQuery("select count(*) from rajeshtable18_3");
			rs.next();
			int cnt = rs.getInt(1);
			
			try
			{
				d.contains("testElement18_3");
			}
			catch(Exception excp)
			{
			}
			if((!d.contains("testElement18_3"))&&(cnt==0))
			{
				System.out.println("TESTCASE 018.3 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 018.3 -> FAILED");
				System.out.println("TESTCASE 018.3 : cnt : "+cnt+" : bool : "+d.contains("testElement18_3"));
			}


		}
		catch(Exception i)
		{
			i.printStackTrace();
		}
		finally
		{
			try
			{
				if(rs!=null)
				{
					rs.close();
				} 
			}
			catch(Exception o)
			{
			}
			d = null;
		}

	}

	private void testCase18_4()
	{
		try
		{
			Thread t1 = new Thread(this);
			t1.setName("tc18_4-1");
			Thread t2 = new Thread(this);
			t2.setName("tc18_4-2");
			Thread t3 = new Thread(this);
			t3.setName("tc18_4-3");
			t1.start();
			t2.start();
			t3.start();
		}
		catch(Exception l)
		{
			l.printStackTrace();
		}
	}


	DBVector d1 = null;
	DBVector d2 = null;
	DBVector d3 = null;
	DBVector d4 = null;
	boolean flag2 = false;
	boolean flag3 = false;
	boolean flag4 = false;
	boolean flag5 = false;
	boolean flag6 = false;
	boolean flag7 = false;
	boolean flag8 = false;
	ResultSet rs1 = null;
	ResultSet rs2 = null;
	ResultSet rs3 = null;
	ResultSet rs4 = null;
	ResultSet rs5 = null;
	ResultSet rs6 = null;

    boolean flagFor18_21_5 = true;

	boolean flag1 = false;
	public void run()
	{
        Statement stmt1=null;
        Statement stmt2=null;
        Statement stmt3=null;
        Statement stmt4=null;
        Statement stmt5=null;
        Statement stmt6=null;
        Statement stmt7=null;
		try
		{
			String name = Thread.currentThread().getName();
			System.out.println("Name of the thread is : "+name);
			if(name.equals("tc18_4-1"))
			{
				d1 = new DBVector("rajeshtable18_4","testCol",false,null,true);
				try
				{
					t.begin();
				}
				catch(Exception l)
				{
					l.printStackTrace();
				}
				d1.addElement("testElement18_4");
				flag1 = true;
				System.out.println("FLAG1 set to true  - tc18_4-1");
				try
				{
					Thread.sleep(3000);
				}
				catch(Exception l)
				{
					l.printStackTrace();
				}
				try
				{
					t.commit();
				}
				catch(Exception l)
				{
					l.printStackTrace();
				}
				flag2 = true;
				System.out.println("FLAG2 set to true  - tc18_4-1");
			}
			if(name.equals("tc18_4-2"))
			{
				try
				{
					while(!flag1)
					{
						System.out.println("FLAG1 STILL FALSE");
                        Thread.sleep(1000);
					}
					System.out.println("FLAG1 set to true - tc18_4-2 : "+flag1);
					connection = connectionpool.getConnection();
					stmt1 = connection.createStatement(); 
					rs1 = stmt1.executeQuery("select * from rajeshtable18_4");
					String val1=null;
					if(rs1.next())
					{
						val1 = rs1.getString(1);
					}
					System.out.println("RS VALUE : " + val1);
					System.out.println("CACHE : " + d1.contains("testElement18_4"));
					if(val1!=null && ((d1.contains("testElement18_4"))||(val1.equals("testElement18_4"))))
					{
						System.out.println("TESTCASE 18.4 -> FAILED");
						System.out.println("18.4--- val1 : "+val1+" d1.contains "+d1.contains("testElement18_4"));
					}
					else
					{
						System.out.println("TESTCASE 18.4 -> PASSED");
						System.out.println("18.4--- val1 : "+val1+" d1.contains "+d1.contains("testElement18_4"));
					}
				}
				catch(Exception excp)
				{
					excp.printStackTrace();
				}
                finally
                {
                    try { rs1.close(); } catch (Exception excp) {}
                }
			}

			if(name.equals("tc18_4-3"))
			{
				try
				{
					while(!flag2)
					{
						System.out.println("FLAG2 STILL FALSE");
                        Thread.sleep(1000);
					}
					System.out.println("FLAG2 set to true  - tc18_4-3");
					connection = connectionpool.getConnection();
					stmt2 = connection.createStatement();
					rs2 = stmt2.executeQuery("select * from rajeshtable18_4");
					String val2=null;
					if(rs2!=null && rs2.next())
					{
						val2 = rs2.getString(1);
					}
					System.out.println("values for 18.4.1 are val2 : "+val2+" d1 : "+d1+" ");
					if(val2!=null && d1.contains("testElement18_4") && val2.equals("testElement18_4"))
					{
						System.out.println("TESTCASE 18.4.1 -> PASSED");
						System.out.println("value for 18.4.1 is : "+val2+" d1.contains "+d1.contains("testElement18_4"));
					}
					else
					{
						System.out.println("TESTCASE 18.4.1 -> FAILED");
						System.out.println("value for 18.4.1 is : "+val2+" d1.contains "+d1.contains("testElement18_4"));
					}
				}
				catch(Exception excp)
				{
					excp.printStackTrace();
				}
                finally
                {
                    try { rs2.close(); } catch (Exception excp) {}
                }
			}

			if(name.equals("tc18_7-1"))
			{
				d2 = new DBVector("rajeshtable18_7_1","testCol",false,null,true);
				d2.addElement("testElement18_7_1");
				try
				{
					t.begin();
				}
				catch(Exception l)
				{
					l.printStackTrace();
				}
				d2.removeElement("testElement18_7_1");
				flag3 = true;
				try
				{
					Thread.sleep(3000);
				}
				catch(Exception l)
				{
					l.printStackTrace();
				}
				try
				{
					t.commit();
				}
				catch(Exception l)
				{
					l.printStackTrace();
				}
				flag4 = true;
			}
			if(name.equals("tc18_7-2"))
			{
				try
				{
					while(!flag3)
					{
						System.out.println("FLAG3 STILL FALSE");
                        Thread.sleep(1000);
					}
					System.out.println("FLAG3 set to true  - tc18_7-2");
					connection = connectionpool.getConnection();
					stmt3 = connection.createStatement();
					rs3 = stmt3.executeQuery("select * from rajeshtable18_7_1");
					String val3 = null;
					if(rs3.next())
					{
						val3 = rs3.getString(1);
					}
					if(val3!=null && ((d2.contains("testElement18_7_1"))&&(val3.equals("testElement18_7_1"))))
					{
						System.out.println("TESTCASE 18.7.1 -> PASSED");
					}
					else
					{
						System.out.println("TESTCASE 18.7.1 -> FAILED");
					}
				}
				catch(Exception excp)
				{
					excp.printStackTrace();
				}
                finally
                {
                    try { rs3.close(); } catch (Exception excp) {}
                }
			}

			if(name.equals("tc18_7-3"))
			{
				try
				{
					while(!flag4)
					{
						System.out.println("FLAG4 STILL FALSE");
                        Thread.sleep(1000);
					}
					System.out.println("FLAG4 set to true  - tc18_7-3");
					connection = connectionpool.getConnection();
					stmt4 = connection.createStatement();
					rs4 = stmt4.executeQuery("select * from rajeshtable18_7_1");
					String val4=null;
					if(rs4.next())
					{
						val4 = rs4.getString(1);
					}
					if(val4!=null && ((d2.contains("testElement18_7_1"))||(val4.equals("testElement18_7_1"))))
					{
						System.out.println("TESTCASE 18.7.2 -> FAILED");
					}
					else
					{
						System.out.println("TESTCASE 18.7.2 -> PASSED");
					}
				}
				catch(Exception excp)
				{
					excp.printStackTrace();
				}
                finally
                {
                    try { rs4.close(); } catch (Exception excp) {}
                }
			}

			if(name.equals("tc18_17-1"))
			{
				d3 = new DBVector("rajeshtable18_17","testCol",false,null,true);
				d3.addElement("testElement18_17");
				try
				{
					t.begin();
				}
				catch(Exception l)
				{
					l.printStackTrace();
				}
				d3.clear();
				flag5 = true;
				try
				{
					Thread.sleep(3000);
				}
				catch(Exception l)
				{
					l.printStackTrace();
				}
				try
				{
					t.commit();
				}
				catch(Exception l)
				{
					l.printStackTrace();
				}
				flag6 = true;
			}
			if(name.equals("tc18_17-2"))
			{
				try
				{
					while(!flag5)
					{
						System.out.println("FLAG5 STILL FALSE");
                        Thread.sleep(1000);
					}
					System.out.println("FLAG5 set to true  - tc18_17-2");
					connection = connectionpool.getConnection();
					stmt5 = connection.createStatement();
					rs5 = stmt5.executeQuery("select * from rajeshtable18_17");
					String val5 = null;
					if(rs5.next())
					{
						val5 = rs5.getString(1);
					}
					if(val5!=null && ((d3.contains("testElement18_17"))&&(val5.equals("testElement18_17"))))
					{
						System.out.println("TESTCASE 18.17 -> PASSED");
					}
					else
					{
						System.out.println("TESTCASE 18.17 -> FAILED");
						System.out.println("value returned for 18.17 is : "+val5+" contains "+d3.contains("testElement18_17"));
					}
				}
				catch(Exception excp)
				{
					excp.printStackTrace();
				}
                finally
                {
                    try { rs5.close(); } catch (Exception excp) {}
                }
			}

			if(name.equals("tc18_17-3"))
			{
				try
				{
					while(!flag6)
					{
						System.out.println("FLAG6 STILL FALSE");
                        Thread.sleep(1000);
					}
					System.out.println("FLAG6 set to true  - tc18_17-3");
					connection = connectionpool.getConnection();
					stmt6 = connection.createStatement();
					rs6 = stmt6.executeQuery("select * from rajeshtable18_17");
					String val6=null;
					if(rs6.next())
					{
						val6 = rs6.getString(1);
					}
					if(val6!=null && ((d3.contains("testElement18_17"))||(val6.equals("testElement18_7"))))
					{
						System.out.println("TESTCASE 18.17.1 -> FAILED");
						System.out.println("testElement18_17 contains "+d3.contains("testElement18_17")+" val is "+val6);
					}
					else
					{
						System.out.println("TESTCASE 18.17.1 -> PASSED");
					}
				}
				catch(Exception excp)
				{
					excp.printStackTrace();
				}
                finally
                {
                    try { rs6.close(); } catch (Exception excp) {}
                }
			}

			if(name.equals("tc18_21-41"))
			{
				d4 = new DBVector("rajeshtable18_21_4","testCol",false,null,true);
				d4.addElement("testElement18_21_4");
				try
				{
					t.begin();
				}
				catch(Exception l)
				{
					l.printStackTrace();
				}
				d4.clearCache();
				flag7 = true;
				try
				{
					Thread.sleep(3000);
				}
				catch(Exception l)
				{
					l.printStackTrace();
				}
				try
				{
					t.commit();
				}
				catch(Exception l)
				{
					l.printStackTrace();
				}
				flag8 = true;
			}
			if(name.equals("tc18_21-42"))
			{
				while(!flag7)
				{
					System.out.println("FLAG7 STILL FALSE");
                    Thread.sleep(1000);
				}
				System.out.println("FLAG7 set to true  - tc18_21-42");
				if(d4.contains("testElement18_21_4"))
				{
					System.out.println("TESTCASE 18.21.4 -> PASSED");
				}
				else
				{
					System.out.println("TESTCASE 18.21.4 -> FAILED");
				}
			}

			if(name.equals("tc18_21-43"))
			{
				while(!flag8)
				{
					System.out.println("FLAG8 STILL FALSE");
                    Thread.sleep(1000);
				}
				statement.executeUpdate("delete from rajeshtable18_21_4");
				if(d4.contains("testElement18_21_4"))
				{
					System.out.println("TESTCASE 18.21.5 -> FAILED");
					System.out.println("contains for 18.21.5 is : "+d4.contains("testElement18_21_4"));
				}
				else
				{
					System.out.println("TESTCASE 18.21.5 -> PASSED");
				}
            }

            if (name.equals("tc18_21_5"))
            {
                DBVector dbVect = new DBVector("rajeshtable18_21_5","testCol",false,null,false);
                for (int i = 0; i < 10; i++)
                {
                    String elem = "rajeshtable18_21_5_ " + i;
                    if (dbVect.contains(elem))
                    {
                        System.out.println(elem + " : " + dbVect.contains(elem));
                        flagFor18_21_5 = false;
                    }
                }
            }
		}
		catch(Exception n)
		{
			System.out.println("Exception inside run : "+n.getMessage());
			n.printStackTrace();
		}
		finally
		{
			try
			{
				//rs1.close();
                stmt1.close();
			}
			catch(Exception c)
			{
				c.printStackTrace();
			}
			try
			{
				//rs2.close();
                stmt2.close();
			}
			catch(Exception c)
			{
				c.printStackTrace();
			}
			//			d1 = null;
			//			d2 = null;
			//			d3 = null;
			try
			{
				//rs3.close();
                stmt3.close();
			}
			catch(Exception c)
			{
				c.printStackTrace();
			}
			try
			{
				//rs4.close();
                stmt4.close();
			}
			catch(Exception c)
			{
				c.printStackTrace();
			}
			try
			{
				if(rs5!=null)
                {
					//rs5.close();
                    stmt5.close();
                }
			}
			catch(Exception c)
			{
				c.printStackTrace();
			}
			try
			{
				if(rs6!=null)
                {
					//rs6.close();
                    stmt6.close();
                }
			}
			catch(Exception c)
			{
				c.printStackTrace();
			}
		}
	}

	private void testCase18_4_2()
	{
		ResultSet rs = null;
		DBVector d = null;
		try
		{
			d = new DBVector("rajeshtable18_4_2","testCol",false,null,true);
			try
			{
				t.begin();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			try
			{
				t.begin();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			d.addElement("testElement18_4_2");
			try
			{
				t.commit();
			}
			catch(Exception j)
			{
				j.printStackTrace();
			}
			try
			{
				t.commit();
			}
			catch(Exception j)
			{
				j.printStackTrace();
			}
			rs = statement.executeQuery("select * from rajeshtable18_4_2");
			String val = null;
			if(rs.next())
			{
				val = rs.getString(1);
			}
			if(val!=null && ((val.equals("testElement18_4_2"))&&(d.contains("testElement18_4_2"))))
			{
				System.out.println("TESTCASE 018.4.2 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 018.4.2 -> FAILED");
			}
		}
		catch(Exception l)
		{
			l.printStackTrace();
		}
		finally
		{
			d = null;
			try
			{
				if(rs!=null)
				{
					rs.close();
				} 
			}
			catch(Exception k)
			{
				k.printStackTrace();
			}
		}

	}

	private void testCase18_4_3()
	{
		ResultSet rs = null;
		DBVector d = null;
		try
		{
			d = new DBVector("rajeshtable18_4_3","testCol",false,null,true);
			try
			{
				t.begin();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			try
			{
				t.begin();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			d.addElement("testElement18_4_3");
			rs = statement.executeQuery("select count(*) from rajeshtable18_4_3");
			rs.next();
			int cnt = rs.getInt(1);
			
			if((d.contains("testElement18_4_3"))||(cnt==1))
			{
				System.out.println("TESTCASE 018.4.3 -> FAILED");
				System.out.println("testElement18_4_3 contains "+d.contains("testElement18_4_3")+" cnt is "+cnt);
			}
			else
			{
				System.out.println("TESTCASE 018.4.3 -> PASSED");
			}
			try
			{
				t.commit();
			}
			catch(Exception j)
			{
				j.printStackTrace();
			}
			try
			{
				t.commit();
			}
			catch(Exception j)
			{
				j.printStackTrace();
			}
		}
		catch(Exception l)
		{
			l.printStackTrace();
		}
		finally
		{
			d = null;
			try
			{
				if(rs!=null)
				{
					rs.close();
				} 
			}
			catch(Exception k)
			{
				k.printStackTrace();
			}
		}

	}

	private void testCase18_4_4()
	{
		ResultSet rs = null;
		DBVector d = null;
		Statement stmt = null;
		try
		{
			d = new DBVector("rajeshtable18_4_4","testCol",false,null,true);
			try
			{
				t.begin();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			try
			{
				t.begin();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			d.addElement("testElement18_4_4");
			try
			{
				t.commit();
			}
			catch(Exception r)
			{
				r.printStackTrace();
			}
			connection = connectionpool.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select count(*) from rajeshtable18_4_4");
			int cnt = -1;
			if(rs.next())
			{
				cnt = rs.getInt(1);
			}
			if((d.contains("testElement18_4_4"))||(cnt==1))
			{
				System.out.println("TESTCASE 018.4.4 -> FAILED");
				System.out.println("testElement18_4_4 contains "+d.contains("testElement18_4_4")+" cnt is "+cnt);
			}
			else
			{
				System.out.println("TESTCASE 018.4.4 -> PASSED");
			}
			try
			{
				t.commit();
			}
			catch(Exception j)
			{
				j.printStackTrace();
			}
		}
		catch(Exception l)
		{
			l.printStackTrace();
		}
		finally
		{
			d = null;
			try
			{
				if(rs!=null)
				{
					rs.close();
				} 
				if(stmt!=null)
					stmt.close();
			}
			catch(Exception k)
			{
				k.printStackTrace();
			}
		}

	}

	private void testCase18_5()
	{
		ResultSet rs = null;
		DBVector d = null;
		try
		{
			d = new DBVector("rajeshtable18_5","testCol",false,null,true);
			d.addElement("testElement18_5");
			try
			{
				t.begin();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			d.removeElement("testElement18_5");
			try
			{
				t.commit();
			}
			catch(Exception j)
			{
				j.printStackTrace();
			}
			rs = statement.executeQuery("select count(*) from rajeshtable18_5");
			rs.next();
			int cnt = rs.getInt(1);
			
			if((cnt==1)||(d.contains("testElement18_5")))
			{
				System.out.println("TESTCASE 018.5 -> FAILED");
			}
			else
			{
				System.out.println("TESTCASE 018.5 -> PASSED");
			}
		}
		catch(Exception l)
		{
			l.printStackTrace();
		}
		finally
		{
			d = null;
			try
			{
				if(rs!=null)
				{
					rs.close();
				} 
			}
			catch(Exception k)
			{
				k.printStackTrace();
			}
		}


	}

	private void testCase18_5_1()
	{
		ResultSet rs = null;
		DBVector d = null;
		Statement stmt = null;
		try
		{
			d = new DBVector("rajeshtable18_5_1","testCol",false,null,true);
			d.addElement("testElement18_5_1");
			try
			{
				t.begin();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			d.removeElement("testElement18_5_1");
			connection = connectionpool.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select count(*) from rajeshtable18_5_1");
			rs.next();
			int cnt = rs.getInt(1);
			
			if((cnt==0)&&(!d.contains("testElement18_5_1")))
			{
				System.out.println("TESTCASE 018.5.1 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 018.5.1 -> FAILED");
				System.out.println("testElement18_5_1 contains "+d.contains("testElement18_5_1")+" cnt is "+cnt);
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
		catch(Exception l)
		{
			l.printStackTrace();
		}
		finally
		{
			d = null;
			try
			{
				if(rs!=null)
				{
					rs.close();
				}
				if(stmt!=null)
				{
					stmt.close();
				}
			}
			catch(Exception k)
			{
				k.printStackTrace();
			}
		}


	}

	private void testCase18_6()
	{
		ResultSet rs = null;
		DBVector d = null;
		try
		{
			d = new DBVector("rajeshtable18_6","testCol",false,null,true);
			d.addElement("testElement18_6");
			try
			{
				t.begin();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			//d.removeElement("testElement18_6");
			d.clear();
			try
			{
				t.rollback("testing rollback");
			}
			catch(Exception j)
			{
				//				j.printStackTrace();
			}
			rs = statement.executeQuery("select count(*) from rajeshtable18_6");
			rs.next();
			int cnt = rs.getInt(1);
			
			if((cnt==1)&&(d.contains("testElement18_6")))
			{
				System.out.println("TESTCASE 018.6 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 018.6 -> FAILED");
			}
		}
		catch(Exception l)
		{
			l.printStackTrace();
		}
		finally
		{
			d = null;
			try
			{
				if(rs!=null)
				{
					rs.close();
				} 
			}
			catch(Exception k)
			{
				k.printStackTrace();
			}
		}

	}

	private void testCase18_7()
	{
		ResultSet rs = null;
		DBVector d = null;
		try
		{
			d = new DBVector("rajeshtable18_7","testCol",false,null,true);
			d.addElement("testElement18_7");
			try
			{
				t.begin();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			d.removeElement("testElement18_7");
			Thread.sleep(21000);
			try
			{
				t.commit();
			}
			catch(Exception j)
			{
				j.printStackTrace();
			}
			rs = statement.executeQuery("select count(*) from rajeshtable18_7");
			rs.next();
			int cnt = rs.getInt(1);
			
			if((cnt==1)&&(d.contains("testElement18_7")))
			{
				System.out.println("TESTCASE 018.7 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 018.7 -> FAILED");
			}
		}
		catch(Exception l)
		{
			l.printStackTrace();
		}
		finally
		{
			d = null;
			try
			{
				if(rs!=null)
				{
					rs.close();
				} 
			}
			catch(Exception k)
			{
				k.printStackTrace();
			}
		}

	}

	private void testCase18_7_1()
	{
		try
		{
			Thread t1 = new Thread(this);
			t1.setName("tc18_7-1");
			Thread t2 = new Thread(this);
			t2.setName("tc18_7-2");
			Thread t3 = new Thread(this);
			t3.setName("tc18_7-3");
			t1.start();
			t2.start();
			t3.start();
		}
		catch(Exception l)
		{
			l.printStackTrace();
		}

	}//method ends here


	private void testCase18_7_3()
	{
		ResultSet rs = null;
		DBVector d = null;
		try
		{
			d = new DBVector("rajeshtable18_7_3","testCol",false,null,true);
			d.addElement("testElement18_7_3");
			try
			{
				t.begin();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			try
			{
				t.begin();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			d.removeElement("testElement18_7_3");
			try
			{
				t.commit();
			}
			catch(Exception j)
			{
				j.printStackTrace();
			}
			try
			{
				t.commit();
			}
			catch(Exception j)
			{
				j.printStackTrace();
			}
			rs = statement.executeQuery("select count(*) from rajeshtable18_7_3");
			int cnt = -1;
			if(rs!=null && rs.next())
			{
				cnt = rs.getInt(1);
			}
			if((cnt==1)||(d.contains("testElement18_7_3")))
			{
				System.out.println("TESTCASE 018.7.3 -> FAILED");
				System.out.println("testElement18_7_3 contains "+d.contains("testElement18_7_3")+" cnt is "+cnt);
			}
			else
			{
				System.out.println("TESTCASE 018.17.3 -> PASSED");
			}
		}
		catch(Exception l)
		{
			System.out.println("Exception while executing 018.17.3 ");
			l.printStackTrace();
		}
		finally
		{
			d = null;
			try
			{
				if(rs!=null)
				{
					rs.close();
				}
			}
			catch(Exception k)
			{
				k.printStackTrace();
			}
		}

	}

	private void testCase18_7_4()
	{
		ResultSet rs = null;
		DBVector d = null;
		try
		{
			d = new DBVector("rajeshtable18_7_4","testCol",false,null,true);
			d.addElement("testElement18_7_4");
			try
			{
				t.begin();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			try
			{
				t.begin();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			d.removeElement("testElement18_7_4");
			rs = statement.executeQuery("select * from rajeshtable18_7_4");
			String val = null;
			if(rs.next())
			{
				val = rs.getString(1);
			}
			if(val!=null && ((val.equals("testElement18_7_4"))&&(d.contains("testElement18_7_4"))))
			{
				System.out.println("TESTCASE 018.7.4 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 018.7.4 -> FAILED");
				System.out.println("value for 018.7.4  is : "+val+" contains testElement18_7_4 "+d.contains("testElement18_7_4"));
			}
			try
			{
				t.commit();
			}
			catch(Exception j)
			{
				j.printStackTrace();
			}
			try
			{
				t.commit();
			}
			catch(Exception j)
			{
				j.printStackTrace();
			}
		}
		catch(Exception l)
		{
			l.printStackTrace();
		}
		finally
		{
			d = null;
			try
			{
				if(rs!=null)
				{
					rs.close();
				}
			}
			catch(Exception k)
			{
				k.printStackTrace();
			}
		}

	}

	private void testCase18_7_5()
	{
		ResultSet rs = null;
		DBVector d = null;
		try
		{
			d = new DBVector("rajeshtable18_7_5","testCol",false,null,true);
			d.addElement("testElement18_7_5");
			try
			{
				t.begin();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			try
			{
				t.begin();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			d.removeElement("testElement18_7_5");
			try
			{
				t.commit();
			}
			catch(Exception r)
			{
				r.printStackTrace();
			}
			rs = statement.executeQuery("select * from rajeshtable18_7_5");
			String val = null;
			if(rs.next())
			{
				val = rs.getString(1);
			}
			if(val!=null && ((val.equals("testElement18_7_5"))&&(d.contains("testElement18_7_5"))))
			{
				System.out.println("TESTCASE 018.17.5 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 018.17.5 -> PASSED");
			}
			try
			{
				t.commit();
			}
			catch(Exception j)
			{
				j.printStackTrace();
			}
		}
		catch(Exception l)
		{
			l.printStackTrace();
		}
		finally
		{
			d = null;
			try
			{
				if(rs!=null)
				{
					rs.close();
				}
			}
			catch(Exception k)
			{
				k.printStackTrace();
			}
		}

	}

	private void testCase18_16()
	{
		ResultSet rs = null;
		DBVector d = null;
		try
		{
			d = new DBVector("rajeshtable18_16","testCol",false,null,true);
			d.addElement("testElement18_16");
			try
			{
				t.begin();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			d.clear();
			try
			{
				t.commit();
			}
			catch(Exception j)
			{
				j.printStackTrace();
			}
			String val = null;
			rs = statement.executeQuery("select * from rajeshtable18_16");
			if(rs.next())
			{
				val = rs.getString(1);
			}
			if(val!=null && ((val.equals("testElement18_16"))||(d.contains("testElement18_16"))))
			{
				System.out.println("TESTCASE 018.16 -> FAILED");
			}
			else
			{
				System.out.println("TESTCASE 018.16 -> PASSED");
			}
		}
		catch(Exception l)
		{
			l.printStackTrace();
		}
		finally
		{
			d = null;
			try
			{
				if(rs!=null)
				{
					rs.close();
				}
			}
			catch(Exception k)
			{
				k.printStackTrace();
			}
		}


	}

	private void testCase18_16_1()
	{
		ResultSet rs = null;
		DBVector d = null;
		Statement stmt = null;
		try
		{
			d = new DBVector("rajeshtable18_16_1","testCol",false,null,true);
			d.addElement("testElement18_16_1");
			try
			{
				t.begin();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			d.clear();
			connection = connectionpool.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from rajeshtable18_16_1");
			String val = null;
			if(rs.next())
			{
				val = rs.getString(1);
			}
			if(val!=null && ((val.equals("testElement18_16_1"))&&(d.contains("testElement18_16_1"))))
			{
				System.out.println("TESTCASE 018.16.1 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 018.16.1 -> FAILED");
				System.out.println("value returned for 018.16.1  is : "+val+" contains "+d.contains("testElement18_16_1"));
			}
			try
			{
				t.commit();
			}
			catch(Exception j)
			{
				j.printStackTrace();
			}
		}
		catch(Exception l)
		{
			l.printStackTrace();
		}
		finally
		{
			d = null;
			try
			{
				if(rs!=null)
				{
					rs.close();
				}
				if(stmt!=null)
					stmt.close();
			}
			catch(Exception k)
			{
				k.printStackTrace();
			}
		}


	}

	private void testCase18_17()
	{
		try
		{
			Thread t1 = new Thread(this);
			t1.setName("tc18_17-1");
			Thread t2 = new Thread(this);
			t2.setName("tc18_17-2");
			Thread t3 = new Thread(this);
			t3.setName("tc18_17-3");
			t1.start();
			t2.start();
			t3.start();
		}
		catch(Exception l)
		{
			l.printStackTrace();
		}
	}

	private void testCase18_18()
	{
		ResultSet rs = null;
		DBVector d = null;
		try
		{
			d = new DBVector("rajeshtable18_18","testCol",false,null,true);
			d.addElement("testElement18_18");
			try
			{
				t.begin();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			d.clear();
			try
			{
				t.rollback("testing rollback");
			}
			catch(Exception j)
			{
				//j.printStackTrace();
			}
			rs = statement.executeQuery("select * from rajeshtable18_18");
			String val = null;
			if(rs.next())
			{
				val = rs.getString(1);
			}
			if(val!=null && ((val.equals("testElement18_18"))&&(d.contains("testElement18_18"))))
			{
				System.out.println("TESTCASE 018.18 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 018.18 -> FAILED");
			}
		}
		catch(Exception l)
		{
			l.printStackTrace();
		}
		finally
		{
			d = null;
			try
			{
				if(rs!=null)
				{
					rs.close();
				}
			}
			catch(Exception k)
			{
				k.printStackTrace();
			}
		}


	}

	private void testCase18_18_1()
	{
		ResultSet rs = null;
		DBVector d = null;
		try
		{
			d = new DBVector("rajeshtable18_18_1","testCol",false,null,true);
			d.addElement("testElement18_18_1");
			try
			{
				t.begin();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			d.clear();
			Thread.sleep(21000);
			try
			{
				t.commit();
			}
			catch(Exception m)
			{
				m.printStackTrace();
			}
			rs = statement.executeQuery("select count(*) from rajeshtable18_18_1");
			rs.next();
			int cnt = rs.getInt(1);
			System.out.println("COUNT : " + cnt);
			try
			{
				d.contains("testElement18_18_1");
			}
			catch(Exception excp)
			{
				System.out.println("Exceptions : " + excp);
				excp.printStackTrace();
			}
			System.out.println("CaCHE : " + d.contains("testElement18_18_1"));
			if((cnt==1)&&(d.contains("testElement18_18_1")))
			{
				System.out.println("TESTCASE 018.18.1 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 018.18.1 -> FAILED");
			}
		}
		catch(Exception l)
		{
			l.printStackTrace();
		}
		finally
		{
			d = null;
			try
			{
				if(rs!=null)
				{
					rs.close();
				}
			}
			catch(Exception k)
			{
				k.printStackTrace();
			}
		}
	}

	private void testCase18_18_2()
	{
		ResultSet rs = null;
		DBVector d = null;
		try
		{
			d = new DBVector("rajeshtable18_18_2","testCol",false,null,true);
			d.addElement("testElement18_18_2");
			try
			{
				t.begin();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			try
			{
				t.begin();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			d.clear();
			try
			{
				t.commit();
			}
			catch(Exception j)
			{
				j.printStackTrace();
			}
			try
			{
				t.commit();
			}
			catch(Exception j)
			{
				j.printStackTrace();
			}
			rs = statement.executeQuery("select count(*) from rajeshtable18_18_2");
			rs.next();
			int cnt = rs.getInt(1);
			
			if((cnt==1)||(d.contains("testElement18_18_2")))
			{
				System.out.println("TESTCASE 018.18.2 -> FAILED");
			}
			else
			{
				System.out.println("TESTCASE 018.18.2 -> PASSED");
			}
		}
		catch(Exception l)
		{
			l.printStackTrace();
		}
		finally
		{
			d = null;
			try
			{
				if(rs!=null)
				{
					rs.close();
				}
			}
			catch(Exception k)
			{
				k.printStackTrace();
			}
		}

	}

	private void testCase18_18_3()
	{
		ResultSet rs = null;
		DBVector d = null;
		Statement stmt = null;
		try
		{
			d = new DBVector("rajeshtable18_18_3","testCol",false,null,true);
			d.addElement("testElement18_18_3");
			try
			{
				t.begin();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			try
			{
				t.begin();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			d.clear();
			connection = connectionpool.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from rajeshtable18_18_3");
			String val = null;
			if(rs.next())
			{
				val = rs.getString(1);
			}
			System.out.println("d.contains for 18_18_3 is "+d.contains("testElement18_18_3"));
			if(val!=null && ((val.equals("testElement18_18_3"))&&(d.contains("testElement18_18_3"))))
			{
				System.out.println("TESTCASE 018.18.3 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 018.18.3 -> FAILED");
				System.out.println("value for 018.18.3  is : "+val);
			}
			try
			{
				t.commit();
			}
			catch(Exception j)
			{
				j.printStackTrace();
			}
			try
			{
				t.commit();
			}
			catch(Exception j)
			{
				j.printStackTrace();
			}
		}
		catch(Exception l)
		{
			l.printStackTrace();
		}
		finally
		{
			d = null;
			try
			{
				if(rs!=null)
				{
					rs.close();
				}
				if(stmt!=null)
					stmt.close();
			}
			catch(Exception k)
			{
				k.printStackTrace();
			}
		}

	}

	private void testCase18_18_4()
	{
		ResultSet rs = null;
		DBVector d = null;
		try
		{
			d = new DBVector("rajeshtable18_18_4","testCol",false,null,true);
			d.addElement("testElement18_18_4");
			try
			{
				t.begin();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			try
			{
				t.begin();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			d.clear();
			//d.removeElement("testElement18_18_4");
			try
			{
				t.commit();
			}
			catch(Exception r)
			{
				r.printStackTrace();
			}
			rs = statement.executeQuery("select * from rajeshtable18_18_4");
			String val = null;
			if(rs.next())
			{
				val = rs.getString(1);
			}
			if(val!=null && ((val.equals("testElement18_18_4"))&&(d.contains("testElement18_18_4"))))
			{
				System.out.println("TESTCASE 018.18.4 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 018.18.4 -> PASSED");
			}
			try
			{
				t.commit();
			}
			catch(Exception j)
			{
				j.printStackTrace();
			}
		}
		catch(Exception l)
		{
			l.printStackTrace();
		}
		finally
		{
			d = null;
			try
			{
				if(rs!=null)
				{
					rs.close();
				}
			}
			catch(Exception k)
			{
				k.printStackTrace();
			}
		}

	}


	private void testCase18_21()
	{
		DBVector d = null;
		try
		{
			d = new DBVector("rajeshtable18_21","testCol",false,null,true);
			d.addElement("testElement18_21");
			try
			{
				t.begin();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			d.clearCache();
			try
			{
				t.commit();
			}
			catch(Exception j)
			{
				j.printStackTrace();
			}
			if(d.contains("testElement18_21"))
			{
				System.out.println("TESTCASE 018.21 -> FAILED");
			}
			else
			{
				System.out.println("TESTCASE 018.21 -> PASSED");
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

	private void testCase18_21_1()
	{
		DBVector d = null;
		try
		{
			d = new DBVector("rajeshtable18_21_1","testCol",false,null,true);
			d.addElement("testElement18_21_1");
			try
			{
				t.begin();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			d.clearCache();
			if(d.contains("testElement18_21_1"))
			{
				System.out.println("TESTCASE 018.21.1 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 018.21.1 -> FAILED");
			}
			try
			{
				t.commit();
			}
			catch(Exception j)
			{
				j.printStackTrace();
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

	private void testCase18_21_4()
	{
		try
		{
			Thread t1 = new Thread(this);
			t1.setName("tc18_21-41");
			Thread t2 = new Thread(this);
			t2.setName("tc18_21-42");
			Thread t3 = new Thread(this);
			t3.setName("tc18_21-43");
			t1.start();
			t2.start();
			t3.start();
		}
		catch(Exception l)
		{
			l.printStackTrace();
		}
	}

	private void testCase18_21_2()
	{
		DBVector d = null;
		try
		{
			d = new DBVector("rajeshtable18_21_2","testCol",false,null,true);
			d.addElement("testElement18_21_2");
			try
			{
				t.begin();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			d.clearCache();
			try
			{
				t.rollback("testing rollback");
			}
			catch(Exception j)
			{
				//				j.printStackTrace();
			}
			if(d.contains("testElement18_21_2"))
			{
				System.out.println("TESTCASE 018.21.2 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 018.21.2 -> FAILED");
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

	private void testCase18_21_3()
	{
		DBVector d = null;
		try
		{
			d = new DBVector("rajeshtable18_21_3","testCol",false,null,true);
			d.addElement("testElement18_21_3");
			try
			{
				t.begin();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			d.clearCache();
			Thread.sleep(21000);
			try
			{
				t.commit();
			}
			catch(Exception j)
			{
				j.printStackTrace();
			}
			if(d.contains("testElement18_21_3"))
			{
				System.out.println("TESTCASE 018.21.3 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 018.21.3 -> FAILED");
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

	private void testCase18_21_5()
	{
		DBVector d = null;
		try
		{
			d = new DBVector("rajeshtable18_21_5","testCol",false,null,true);
            for (int i = 0; i < 10; i++)
            {
                d.addElement("testElement18_21_5_" + i);
            }
			try
			{
				t.begin();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			d.clearCache();
			try
			{
				t.commit();
			}
			catch(Exception j)
			{
				j.printStackTrace();
			}
            statement.executeUpdate("delete from rajeshtable18_21_5");

            Thread th = new Thread(this, "tc18_21_5");
            th.start();
            Thread.sleep(2000);

			if(flagFor18_21_5)
			{
				System.out.println("TESTCASE 018.21.5 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 018.21.5 -> FAILED");
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

	private void testCase18_21_6()
	{
		ResultSet rs = null;
		DBVector d = null;
		try
		{
			d = new DBVector("rajeshtable18_21_6","testCol",false,null,true);
			d.addElement("testElement18_21_6");
			try
			{
				t.begin();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			try
			{
				t.begin();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			d.clearCache();
			try
			{
				t.commit();
			}
			catch(Exception j)
			{
				j.printStackTrace();
			}
			try
			{
				t.commit();
			}
			catch(Exception j)
			{
				j.printStackTrace();
			}
			statement.executeUpdate("delete from rajeshtable18_21_6");
			if(d.contains("testElement18_21_6"))
			{
				System.out.println("TESTCASE 018.21.6 -> FAILED");
			}
			else
			{
				System.out.println("TESTCASE 018.21.6 -> PASSED");
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

	private void testCase18_21_8()
	{
		DBVector d = null;
		try
		{
			d = new DBVector("rajeshtable18_21_8","testCol",false,null,true);
			d.addElement("testElement18_21_8");
			try
			{
				t.begin();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			try
			{
				t.begin();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			d.clearCache();
			if(d.contains("testElement18_21_8"))
			{
				System.out.println("TESTCASE 018.21.8 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 018.21_8 -> FAILED");
			}
			try
			{
				t.commit();
			}
			catch(Exception j)
			{
				j.printStackTrace();
			}
			try
			{
				t.commit();
			}
			catch(Exception j)
			{
				j.printStackTrace();
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

	private void testCase18_21_7()
	{
		DBVector d = null;
		try
		{
			d = new DBVector("rajeshtable18_21_7","testCol",false,null,true);
			d.addElement("testElement18_21_7");
			try
			{
				t.begin();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			try
			{
				t.begin();
			}
			catch(Exception l)
			{
				l.printStackTrace();
			}
			d.clearCache();
			try
			{
				t.commit();
			}
			catch(Exception r)
			{
				r.printStackTrace();
			}
			if(d.contains("testElement18_21_7"))
			{
				System.out.println("TESTCASE 018.21.7 -> PASSED");
			}
			else
			{
				System.out.println("TESTCASE 018.21.7 -> FAILED");
			}
			try
			{
				t.commit();
			}
			catch(Exception j)
			{
				j.printStackTrace();
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
}


