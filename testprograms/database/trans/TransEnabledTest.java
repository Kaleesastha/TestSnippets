// Decompiled by Jad v1.5.7f. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TransEnabledTest.java

package com.adventnet.management.transaction;

import com.adventnet.nms.alertdb.AlertAPI;
import com.adventnet.nms.eventdb.Event;
import com.adventnet.nms.eventdb.EventAPI;
import com.adventnet.nms.mapdb.MapAPI;
import com.adventnet.nms.poll.PollAPI;
import com.adventnet.nms.poll.PolledData;
import com.adventnet.nms.store.NmsStorageException;
import com.adventnet.nms.store.relational.RelationalAPI;
import com.adventnet.nms.topodb.ManagedObject;
import com.adventnet.nms.topodb.TopoAPI;
import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.util.RunProcessInterface;
import java.io.PrintStream;
import java.sql.*;

// Referenced classes of package com.adventnet.management.transaction:
//            ConnectionPool, PreparedStatementWrapper, TransactionAPI

public class TransEnabledTest implements RunProcessInterface
{

	TransactionAPI transactionapi = null;
	ConnectionPool connectionpool = null;
	RelationalAPI relationalapi=null;
	public TransEnabledTest()throws SQLException, NmsStorageException
	{
		transactionapi = NmsUtil.relapi.getTransactionAPI();
		connectionpool = NmsUtil.relapi.getConnectionPool();
		relationalapi=NmsUtil.relapi;
		initializeAction();
		
	
		sftrans044();


        
        sftrans006();
        sftrans007();
        sftrans010();
		sftrans018();
		sftrans019();
		sftrans020();
		sftrans021();
		sftrans022();
		sftrans023();
		sftrans025();
		sftrans026();
		sftrans027();
		sftrans028();
		sftrans029();
		sftrans030();
		sftrans031();
		sftrans032();
		sftrans033034();
		sftrans035();
		sftrans036();
		sftrans038();
		sftrans039();
		sftrans040();
		sftrans041();
		sftrans054();
		sftrans055();
		sftrans056();
		sftrans057();
        
	}

	private void initializeAction()
	{
		try
		{
			executeQuery("drop table RAJESHSFTRANS008");
			executeQuery("drop table RajeshSFTRANS011");
			executeQuery("drop table RajeshSFTRANS016");
			executeQuery("drop table RAJESHSFTRANS0181");
			executeQuery("drop table RAJESHSFTRANS0182");
			executeQuery("drop table RAJESHSFTRANS0191");
			executeQuery("drop table RAJESHSFTRANS0192");
			executeQuery("drop table RAJESHSFTRANS021");
			executeQuery("drop table RajeshSFTRANS023");
			executeQuery("drop table RAJESHSFTRANS0251");
			executeQuery("drop table RAJESHSFTRANS0252");
			executeQuery("drop table RAJESHSFTRANS0261");
			executeQuery("drop table RAJESHSFTRANS0262");
			executeQuery("drop table RAJESHSFTRANS0271");
			executeQuery("drop table RAJESHSFTRANS0272");
			executeQuery("drop table RAJESHSFTRANS029");
			executeQuery("drop table RAJESHSFTRANS035");
			executeQuery("create table RAJESHSFTRANS008(name varchar(20))");
			executeQuery("create table RajeshSFTRANS011(name varchar(20))");
			executeQuery("create table RajeshSFTRANS016(name varchar(20))");
			executeQuery("create table RAJESHSFTRANS0181(name varchar(20))");
			executeQuery("create table RAJESHSFTRANS0182(name varchar(20))");
			executeQuery("create table RAJESHSFTRANS0191(name varchar(20))");
			executeQuery("create table RAJESHSFTRANS0192(name varchar(20))");
			executeQuery("create table RAJESHSFTRANS021(name varchar(20))");
			executeQuery("create table RajeshSFTRANS023(name varchar(20))");
			executeQuery("create table RAJESHSFTRANS0251(name varchar(20))");
			executeQuery("create table RAJESHSFTRANS0252(name varchar(20))");
			executeQuery("create table RAJESHSFTRANS0261(name varchar(20))");
			executeQuery("create table RAJESHSFTRANS0262(name varchar(20))");
			executeQuery("create table RAJESHSFTRANS0271(name varchar(20))");
			executeQuery("create table RAJESHSFTRANS0272(name varchar(20))");
			executeQuery("create table RAJESHSFTRANS029(name varchar(20))");
			executeQuery("create table RAJESHSFTRANS035(name varchar(20))");
		}
		catch(Exception excp)
		{
			excp.printStackTrace();
		}
	}
	private void executeQuery(String query)
	{
		try
		{
			relationalapi.execute(query);	
		}
		catch(Exception excp)
		{	
		}
	}
	public void callMain(String as[])
	{
	}

	public boolean isInitialized()
	{
		return true;
	}

	public static void main(String args[])throws SQLException, NmsStorageException
	{
		new TransEnabledTest();
	}

	public void sftrans001() throws SQLException
	{
		try
		{
			transactionapi.begin();
			java.sql.Connection connection = connectionpool.getConnectionForTransaction();
			if(connection != null)
				System.out.println("sftrans001:PASSED");
			else
				System.out.println("sftrans01:FAILED");
		}
		catch(Exception ex) 
		{
			ex.printStackTrace();
		}
		finally
		{
			try
			{
				transactionapi.commit();
			}
			catch(Exception excp)
			{
			}
		}
	}

	public void sftrans003()throws SQLException
	{
		try
		{
			transactionapi.begin();
			transactionapi.begin();
			ConnectionPool connectionpool = NmsUtil.relapi.getConnectionPool();
			int i = connectionpool.getNestingLevel();
			if(i > 0)
				System.out.println("sftrans003:PASSED");
			else
				System.out.println("sftrans003:FAILED");
		}
		catch(Exception ex) 
		{
			ex.printStackTrace();
		}
		finally
		{
			try
			{
				transactionapi.commit();
				transactionapi.commit();
			}
			catch(Exception excp)
			{
			}
		}
	}

	public void sftrans004()throws SQLException
	{
		try
		{
			transactionapi.begin();
			int i = connectionpool.getNestingLevel();
			System.out.println("SFTRANS004 : VALUE "+i);
			if(i == 0)
				System.out.println("sftrans004:PASSED");
			else
				System.out.println("sftrans004:FAILED");
		}
		catch(Exception ex) 
		{
			ex.printStackTrace();
		}
		finally
		{
			try
			{
				transactionapi.commit();
			}
			catch(Exception excp)
			{
			}
		}
	}

	public void sftrans006() throws SQLException, NmsStorageException
	{
		try
		{
			new CallInner("006",20000).start();
			new CallInner("006",20000).start();
			CallInner ci = new CallInner("006",200);
            ci.start();

            Thread.sleep(1000);
            if (ci.getException() != null)
            {
                throw ci.getException();
            }
			System.out.println("SFTRANS006:FAILED");
		}
		catch(Exception ex) 
		{
			ex.printStackTrace();
			System.out.println("SFTRANS006:PASSED");
			System.out.println("006:Exception");
		}
		finally
		{
			try
			{
				Thread.sleep(25000);
			}
			catch(Exception r)
			{
				r.printStackTrace();
			}
		}

	}


	public void sftrans007()
		throws SQLException, NmsStorageException
		{
			try
			{
				new CallInner("007",5000).start();
				new CallInner("007",10000).start();
				new CallInner("007",9000).start();

                System.out.println("SFTRANS007:PASSED");
			}
			catch(Exception ex) 
			{
                System.out.println("SFTRANS007:FAILED");
				System.out.println("Inside exception for 007");
				ex.printStackTrace();
			}
		}

	public void sftrans008()
		throws SQLException, NmsStorageException
		{
			try
			{
				transactionapi.begin(2000);
				String s1 = "insert into RAJESHSFTRANS008 values('Rajesh1')";
				relationalapi.execute(s1);
				Thread.sleep(3000);
				transactionapi.commit();
			}
			catch(Exception ex) 
			{
				ex.printStackTrace();
			}
			try
			{
				String s2 = "select * from RAJESHSFTRANS008 where name like ?";
				int i = relationalapi.getPreparedStatementID(s2);
				PreparedStatementWrapper preparedstatementwrapper = relationalapi.fetchPreparedStatement(i);
				PreparedStatement preparedstatement = preparedstatementwrapper.getPreparedStatement();
				preparedstatement.setString(1, "Rajesh1");
				ResultSet resultset = relationalapi.executeQuery(preparedstatement);
				if(!resultset.next())
				{
					System.out.println("SFTRANS008:PASSED");  
				}
				else 
				{ 
					System.out.println("SFTRANS008:FAILED");   
				}
			}
			catch(Exception ex) 
			{
				ex.printStackTrace();
			}
		}

	public void sftrans009() throws SQLException, NmsStorageException
	{
		try
		{
			System.out.println("009 CALLED");
			new CallInner("009",10000).start();
			new CallInner("009",10000).start();
			new CallInner("009",10000).start();
			new CallInner("009",10000).start();
			Connection connection = connectionpool.getConnectionForTransaction();
			if(connection != null)
				System.out.println("SFTRANS009:FAILED");
			else
				System.out.println("SFTRANS009:PASSED");
		}
		catch(Exception ex) {System.out.println("009:Exception");
			ex.printStackTrace();}
	}

	public void sftrans010()
		throws SQLException, NmsStorageException
		{
			try
			{
				new CallInner("010",10).start();
				new CallInner("010",10000).start();
				new CallInner("010",10000).start();
                System.out.println("SFTRANS010:PASSED");
			}
			catch(Exception ex) 
			{
                System.out.println("SFTRANS010:FAILED");
				System.out.println("Inside exception for 010");
				ex.printStackTrace();
			}
		}

	public void sftrans011()
		throws SQLException, NmsStorageException
		{
			try
			{
				transactionapi.begin(-1);
				//connectionpool.setTransactionTimeOut(100);
				transactionapi.setTransactionTimeout(100);
				String s1 = "insert into RajeshSFTRANS011 values('Rajesh1')";
				connectionpool.execute(s1);
				String s2 = "select name  from RajeshSFTRANS011 where name like ?" ;
				int i = relationalapi.getPreparedStatementID(s2);
				PreparedStatementWrapper preparedstatementwrapper = relationalapi.fetchPreparedStatement(i);
				PreparedStatement preparedstatement = preparedstatementwrapper.getPreparedStatement();
				preparedstatement.setString(1,"Rajesh1");
				ResultSet resultset = relationalapi.executeQuery(preparedstatement);
				resultset.next();
				String s3 = resultset.getString(1);
				relationalapi.returnPreparedStatement(preparedstatementwrapper);
				transactionapi.commit();
				if(s3.equals("Rajesh1"))
					System.out.println("SFTRANS011:PASSED");
				else
					System.out.println("sftrans011:FAILED");
			}
			catch(Exception e)
			{
				System.out.println("011:"+e);
			}
		}

	public void sftrans013()
		throws SQLException
		{
			//			TransactionAPI transactionapi = NmsUtil.relapi.getTransactionAPI();
			try
			{
				transactionapi.begin(10000);
			}
			catch(Exception exception)
			{
				System.out.println("sftrans013:" + exception);
			}
			try
			{
				transactionapi.begin(10000);
			}
			catch(Exception exception1)
			{
				System.out.println("sftrans013:" + exception1);
			}
			int i = connectionpool.getNestingLevel();
			if(i > 0)
				System.out.println("sftrans013:PASSED");
			else
				System.out.println("sftrans013:FAILED");
			try
			{
				transactionapi.commit();
				transactionapi.commit();
			}
			catch(Exception excp)
			{
				excp.printStackTrace();
			}
		}

	public void sftrans014()
		throws SQLException
		{
			try
			{
				transactionapi.begin(10000);
				int i = connectionpool.getNestingLevel();
				System.out.println("Nesting Level is 014014 : "+i);
				if(i == 0)
					System.out.println("sftrans014:PASSED");
				else
					System.out.println("sftrans014:FAILED");
			}
			catch(Exception e)
			{
				System.out.println("014 : "+e);
			}
			finally
			{
				try
				{
					transactionapi.commit();
				}
				catch(Exception excp)
				{}
			}
		}

	public void sftrans016()
		throws SQLException
		{
			try
			{
				transactionapi.begin();
				String s1 = "insert into RajeshSFTRANS016 values('Rajesh1')";
				connectionpool.execute(s1);
				transactionapi.commit();
				String s2 = "select name  from RajeshSFTRANS016 where name like ?";
				int i = relationalapi.getPreparedStatementID(s2);
				PreparedStatementWrapper preparedstatementwrapper = relationalapi.fetchPreparedStatement(i);
				PreparedStatement preparedstatement = preparedstatementwrapper.getPreparedStatement();
				preparedstatement.setString(1, "Rajesh1");
				ResultSet resultset =relationalapi.executeQuery(preparedstatement);
				String s3=null;
				while(resultset.next())
				{
					s3 = resultset.getString(1);
				}
				relationalapi.returnPreparedStatement(preparedstatementwrapper);
				if(s3.equals("Rajesh1"))
				{
					System.out.println("sftrans016:PASSED");
				}
				else
				{
					System.out.println("sftrans016:FAILED");
				}
			}
			catch(Exception exception)
			{
				System.out.println("Exception for sftrans016");
				exception.printStackTrace();
			}
		}

	public void sftrans018() throws SQLException, NmsStorageException
		{
			System.out.println("Latest Source         ");
			try
			{
				transactionapi.begin();
				String s2 = "insert into RAJESHSFTRANS0181 values('Rajesh1')";
				connectionpool.execute(s2);
				transactionapi.begin();
				String s3 = "insert into RAJESHSFTRANS0182 values('Rajesh2')";
				connectionpool.execute(s3);
				//transactionapi.commit();
				Thread.sleep(25000);
				try
				{
					transactionapi.commit();
				}
				catch(Exception excp)
				{}
				
				String s4 = "select name from RAJESHSFTRANS0181";
				int i = relationalapi.getPreparedStatementID(s4);
				PreparedStatementWrapper preparedstatementwrapper = relationalapi.fetchPreparedStatement(i);
				PreparedStatement preparedstatement = preparedstatementwrapper.getPreparedStatement();
				ResultSet resultset = null;
				resultset = preparedstatement.executeQuery();
				String s5=null;
				while(resultset.next())
				{
					s5 = resultset.getString(1);
				}
				relationalapi.returnPreparedStatement(preparedstatementwrapper);
				String s6 = "select name from RAJESHSFTRANS0182";
				int j = relationalapi.getPreparedStatementID(s6);
				PreparedStatementWrapper preparedstatementwrapper1 = relationalapi.fetchPreparedStatement(j);
				PreparedStatement preparedstatement1 = preparedstatementwrapper1.getPreparedStatement();
				ResultSet resultset1 = preparedstatement1.executeQuery();
				String s7=null;
				while(resultset1.next())
				{
					s7 = resultset1.getString(1);
				}
				relationalapi.returnPreparedStatement(preparedstatementwrapper1);
				System.out.println("s5 and s7 vals for 018 are  "+s5+" "+s7);
				if(s5==null && s7==null)
				{
					System.out.println("SFTRANS018:PASSED"); 	
				}
				else if(s5.equals("Rajesh1") && s7.equals("Rajesh2"))
				{
					System.out.println("SFTRANS018:FAILED");
				}
				
			}
			catch(Exception exception)
			{
				System.out.println("Inside Exception for 018");
				exception.printStackTrace();
			}
			finally
			{
				try
				{
					transactionapi.commit();
				}
				catch(Exception excp)
				{}
			}
		}

	public void sftrans019() throws SQLException, NmsStorageException
	{
		try
		{
			transactionapi.begin();
			String s2 = "insert into RAJESHSFTRANS0191 values('Rajesh1')";
			connectionpool.execute(s2);
			transactionapi.begin();
			String s3 = "insert into RAJESHSFTRANS0192 values('Rajesh2')";
			connectionpool.execute(s3);
			transactionapi.commit();
			transactionapi.commit();
			String s4 = "select name  from RAJESHSFTRANS0191 where name like ?";
			int i = relationalapi.getPreparedStatementID(s4);
			PreparedStatementWrapper preparedstatementwrapper = relationalapi.fetchPreparedStatement(i);
			PreparedStatement preparedstatement = preparedstatementwrapper.getPreparedStatement();
			preparedstatement.setString(1, "Rajesh1");
			ResultSet resultset = preparedstatement.executeQuery();
			String s5=null;
			String s7=null;
			while(resultset.next())
			{
				s5 = resultset.getString(1);
			}
			relationalapi.returnPreparedStatement(preparedstatementwrapper);
			String s6 = "select name from RAJESHSFTRANS0192 where name like ?";
			int j = relationalapi.getPreparedStatementID(s6);
			PreparedStatementWrapper preparedstatementwrapper1 = relationalapi.fetchPreparedStatement(j);
			PreparedStatement preparedstatement1 = preparedstatementwrapper1.getPreparedStatement();
			preparedstatement1.setString(1, "Rajesh2");
			ResultSet resultset1 = preparedstatement1.executeQuery();
			while(resultset1.next())
			{
				s7 = resultset1.getString(1);
			}
			relationalapi.returnPreparedStatement(preparedstatementwrapper1);
			System.out.println("S5 and S7 vals are "+s5+" "+s7);
			if(s5.equals("Rajesh1") && s7.equals("Rajesh2"))
			{
				System.out.println("SFTRANS019:PASSED");
			}
			else
			{
				System.out.println("SFTRANS019:FAILED");
			}
		}
		catch(Exception e)
		{
			System.out.println("019019: "+e);
			e.printStackTrace();
		}
	}

	public void sftrans020()throws SQLException
	{
		try
		{
			boolean flag = true;
			transactionapi.begin();
			int i = connectionpool.getNestingLevel();
			System.out.println("0202020 : i :"+i);
			if(i != 0)
			{
				flag = false;
			}
			transactionapi.begin();
			int j = connectionpool.getNestingLevel();
			System.out.println("02002020 : j : "+j);
			if(j != 1)
			{
				flag = false;
			}
			transactionapi.commit();
			int k = connectionpool.getNestingLevel();
			System.out.println("02020020 : k : "+k);
			if(k != 0)
			{
				flag = false;
			}
			if(flag)
			{
				System.out.println("SFTRANS020:PASSED");
			}
			else
			{
				System.out.println("SFTRANS020:FAILED");
			}
		}
		catch(Exception e)
		{
			System.out.println("020020 : "+e);
		}
		finally
		{
			try
			{
				transactionapi.commit();
			}
			catch(Exception excp)
			{}
		}
	}

	public void sftrans021()
		throws SQLException, NmsStorageException
		{
			try
			{
				transactionapi.begin();
				transactionapi.setTransactionTimeout(100);
				transactionapi.commit();
				transactionapi.begin();
			}
			catch(Exception exception)
			{
				System.err.println("sftrans021:" + exception);
			}
			try
			{
				Thread.sleep(200);
				transactionapi.commit();
				System.out.println("SFTRANS021:FAILED");
			}
			catch(Exception exception1)
			{
				System.err.println("sftrans021:" + exception1);
				System.out.println("SFTRANS021:PASSED");
			}
		}

	public void sftrans022()throws SQLException
	{
		try
		{

			transactionapi.begin();
			transactionapi.commit();
			java.sql.Connection connection = connectionpool.getConnectionForTransaction();
			System.out.println("022022CONNECTION"+connection);
			if(connection != null)
			{
				System.out.println("sftrans022:FAILED");
			}
			else
			{
				System.out.println("sftrans022:PASSED");
			}
		}
		catch(Exception e){System.err.println("SFTRANS022:"+e);}
	}

	public void sftrans023()
		throws SQLException, NmsStorageException
		{
			try
			{
				transactionapi.begin();
				String s1 = "insert into RajeshSFTRANS023 values('Rajesh1')";
				relationalapi.execute(s1);
				try
				{
					transactionapi.rollback();
				}
				catch(Exception uu)
				{
					uu.printStackTrace();
				}
				String s2 = "select count(*) from RajeshSFTRANS023 where name like ?";
				int i = relationalapi.getPreparedStatementID(s2);
				PreparedStatementWrapper preparedstatementwrapper = relationalapi.fetchPreparedStatement(i);
				PreparedStatement preparedstatement = preparedstatementwrapper.getPreparedStatement();
				preparedstatement.setString(1, "Rajesh1");
				ResultSet resultset = relationalapi.executeQuery(preparedstatement);
				resultset.next();
				String s3 = resultset.getString(1);
				relationalapi.returnPreparedStatement(preparedstatementwrapper);
				if(s3.equals("0"))
					System.out.println("SFTRANS023:PASSED");
				else
					System.out.println("SFTRANS023:FAILED");
			}
			catch(Exception e)
			{
				System.out.println("023:Exception"+e);
				e.printStackTrace();
			}
		}

	public void sftrans025()
	{
		try
		{
			transactionapi.begin();
			String s2 = "insert into RAJESHSFTRANS0251 values('Rajesh1')";
			relationalapi.execute(s2);
			transactionapi.begin();
			String s3 = "insert into RAJESHSFTRANS0252 values('Rajesh2')";
			relationalapi.execute(s3);
			try
			{
				transactionapi.rollback();
			}
			catch(Exception oo)
			{
				oo.printStackTrace();
			}
			String s4 = "select count(*) from RAJESHSFTRANS0251 where name like ?";
			int i = relationalapi.getPreparedStatementID(s4);
			PreparedStatementWrapper preparedstatementwrapper = relationalapi.fetchPreparedStatement(i);
			ResultSet resultset = null;
			PreparedStatement preparedstatement = preparedstatementwrapper.getPreparedStatement();
			preparedstatement.setString(1, "Rajesh1");
			resultset = relationalapi.executeQuery(preparedstatement);
			resultset.next();
			String s5 = resultset.getString(1);
			relationalapi.returnPreparedStatement(preparedstatementwrapper);
			String s6 = "select count(*) from RAJESHSFTRANS0252 where name like ?";
			int j = relationalapi.getPreparedStatementID(s6);
			PreparedStatementWrapper preparedstatementwrapper1 = relationalapi.fetchPreparedStatement(j);
			PreparedStatement preparedstatement1 = preparedstatementwrapper1.getPreparedStatement();
			preparedstatement1.setString(1, "Rajesh2");
			ResultSet resultset1 = relationalapi.executeQuery(preparedstatement);
			resultset1.next();
			String s7 = resultset1.getString(1);
			relationalapi.returnPreparedStatement(preparedstatementwrapper1);
			if(s5.equals("0") && s7.equals("0"))
				System.out.println("SFTRANS025:PASSED");
			else
				System.out.println("SFTRANS025:FAILED");
		}
		catch(Exception ex) {System.out.println("025025:"+ex);
			ex.printStackTrace();}
		finally
		{
			try
			{
				transactionapi.commit();
			}
			catch(Exception excp)
			{
				excp.printStackTrace();
			}
		}
	}

	public void sftrans026()
		throws SQLException
		{
			try
			{
				transactionapi.begin();
				String s2 = "insert into RAJESHSFTRANS0261 values('Rajesh1')";
				relationalapi.execute(s2);
				transactionapi.begin();
				String s3 = "insert into RAJESHSFTRANS0262 values('Rajesh2')";
				relationalapi.execute(s3);
				try
				{
					transactionapi.rollback();
				}
				catch(Exception rr)
				{
					rr.printStackTrace();
				}
				try
				{
					transactionapi.rollback();
				}
				catch(Exception yy)
				{
					yy.printStackTrace();
				}
				String s4 = "select count(*) from RAJESHSFTRANS0261 where name like ?";
				int i = relationalapi.getPreparedStatementID(s4);
				PreparedStatementWrapper preparedstatementwrapper = relationalapi.fetchPreparedStatement(i);
				PreparedStatement preparedstatement = preparedstatementwrapper.getPreparedStatement();
				ResultSet resultset = null;
				preparedstatement.setString(1, "Rajesh1");
				resultset = relationalapi.executeQuery(preparedstatement);
				resultset.next();
				String s5 = resultset.getString(1);
				relationalapi.returnPreparedStatement(preparedstatementwrapper);
				String s6 = "select count(*) from RAJESHSFTRANS0262 where name like ?";
				int j = relationalapi.getPreparedStatementID(s6);
				PreparedStatementWrapper preparedstatementwrapper1 = relationalapi.fetchPreparedStatement(j);
				PreparedStatement preparedstatement1 = preparedstatementwrapper1.getPreparedStatement();
				ResultSet resultset1 = null;
				preparedstatement1.setString(1, "Rajesh2");
				resultset1 = relationalapi.executeQuery(preparedstatement);
				resultset1.next();
				String s7 = resultset1.getString(1);
				relationalapi.returnPreparedStatement(preparedstatementwrapper1);
				if(s5.equals("0") && s7.equals("0"))
					System.out.println("SFTRANS026:PASSED");
				else
					System.out.println("SFTRANS026:FAILED");
			}
			catch(Exception ex) {System.out.println("026026 : "+ex); 
				ex.printStackTrace();
			}
		}

	public void sftrans027()
		throws SQLException
		{
			try
			{
				transactionapi.begin();
				String s2 = "insert into RAJESHSFTRANS0271 values('Rajesh1')";
				relationalapi.execute(s2);
				transactionapi.begin();
				String s3 = "insert into RAJESHSFTRANS0272 values('Rajesh2')";
				relationalapi.execute(s3);
				try
				{
					transactionapi.rollback();
				}
				catch(Exception uu)
				{
					uu.printStackTrace();
				}
				transactionapi.commit();
				String s4 = "select count(*) from RAJESHSFTRANS0271 where name like ?";
				int i = relationalapi.getPreparedStatementID(s4);
				PreparedStatementWrapper preparedstatementwrapper = relationalapi.fetchPreparedStatement(i);
				PreparedStatement preparedstatement = preparedstatementwrapper.getPreparedStatement();
				preparedstatement.setString(1, "Rajesh1");
				ResultSet resultset = relationalapi.executeQuery(preparedstatement);
				resultset.next();
				String s5 = resultset.getString(1);
				relationalapi.returnPreparedStatement(preparedstatementwrapper);
				String s6 = "select count(*) from RAJESHSFTRANS0272 where name like ?";
				int j = relationalapi.getPreparedStatementID(s6);
				PreparedStatementWrapper preparedstatementwrapper1 = relationalapi.fetchPreparedStatement(j);
				PreparedStatement preparedstatement1 = preparedstatementwrapper1.getPreparedStatement();
				preparedstatement1.setString(1, "Rajesh2");
				ResultSet resultset1 = relationalapi.executeQuery(preparedstatement1);
				resultset1.next();
				String s7 = resultset1.getString(1);
				relationalapi.returnPreparedStatement(preparedstatementwrapper1);
				if(s5.equals("0") && s7.equals("0"))
					System.out.println("SFTRANS027:PASSED");
				else
					System.out.println("SFTRANS027:FAILED");
			}
			catch(Exception ex) {System.out.println("SFTRANS027:PASSED");
				ex.printStackTrace();}
		}

	public void sftrans028()
	{
		try
		{
			boolean flag = true;
			transactionapi.begin();
			int i = connectionpool.getNestingLevel();
			System.out.println("SFTRANS028:I=="+i);
			if(i != 0)
				flag = false;
			transactionapi.begin();
			int j = connectionpool.getNestingLevel();
			System.out.println("SFTRANS028:J=="+j);
			if(j != 1)
				flag = false;
			transactionapi.begin();
			int k = connectionpool.getNestingLevel();
			System.out.println("SFTRANS028:K=="+k);
			if(k != 2)
				flag = false;
			try
			{
				transactionapi.rollback();
			}
			catch(Exception ii)
			{
				ii.printStackTrace();
			}
			int l = connectionpool.getNestingLevel();
			System.out.println("SFTRANS028:L=="+l);
			if(l != -1)
				flag = false;
			if(flag)
				System.out.println("SFTRANS028:PASSED");
			else
				System.out.println("SFTRANS028:FAILED");
		}
		catch(Exception ex) {ex.printStackTrace();}
		finally
		{
			try
			{
				transactionapi.commit();
				transactionapi.commit();
			}
			catch(Exception excp)
			{}
		}
	}

	public void sftrans029()
		throws SQLException
		{
			try
			{
				transactionapi.begin();
				transactionapi.setTransactionTimeout(100);
				transactionapi.commit();
				transactionapi.begin();
				String s1 = "insert into RAJESHSFTRANS029 values('Rajesh1')";
				relationalapi.execute(s1);
				Thread.sleep(200L);
				try
				{
					transactionapi.rollback();
				}
				catch(Exception rr)
				{
					rr.printStackTrace();
				}
				String s2 = "select ? from RAJESHSFTRANS029";
				int i = relationalapi.getPreparedStatementID(s2);
				PreparedStatementWrapper preparedstatementwrapper = relationalapi.fetchPreparedStatement(i);
				PreparedStatement preparedstatement = preparedstatementwrapper.getPreparedStatement();
				preparedstatement.setString(1, "name");
				ResultSet resultset = preparedstatement.executeQuery();
				String s3=null;
				while(resultset.next())
				{
					s3 = resultset.getString(1);
				}
				relationalapi.returnPreparedStatement(preparedstatementwrapper);
				if(s3!=null && s3.equals("Rajesh1"))
					System.out.println("SFTRANS029:FAILED");
				else
					System.out.println("SFTRANS029:PASSED");
			}
			catch(Exception ex) 
			{
				System.out.println("SFTRANS029:PASSED  --- Inside excp");
				ex.printStackTrace();
			}
		}

	public void sftrans030()
		throws SQLException
		{
			try
			{
				transactionapi.begin();
				try
				{
					transactionapi.rollback();
				}
				catch(Exception t)
				{
					t.printStackTrace();
				}

				ConnectionPool connectionpool = NmsUtil.relapi.getConnectionPool();
				java.sql.Connection connection = connectionpool.getConnectionForTransaction();
				if(connection != null)
					System.out.println("sftrans030:FAILED");
				else
					System.out.println("sftrans030:PASSED");
			}
			catch(Exception ex) {ex.printStackTrace();}
		}

	public void sftrans031()
		throws SQLException
		{
			try
			{
				transactionapi.begin();
			}
			catch(Exception uuy)
			{
				uuy.printStackTrace();
			}
			try
			{
				transactionapi.rollback("031:PASSED");
			}
			catch(Exception exception)
			{
				System.err.println("sftrans031:" + exception);
			}
		}

	public void sftrans032()
	{
		try
		{
			transactionapi.begin();
			try
			{
				transactionapi.rollback("032:PASSED");
			}
			catch(Exception o)
			{
				o.printStackTrace();
			}
				
		}
		catch(Exception ex) {ex.printStackTrace();}
	}

	public void sftrans033034()
		throws SQLException
		{
			transactionapi.setTransactionTimeout(10000);
			int i = transactionapi.getTransactionTimeOut();
			if(i == 10000)
			{
				System.out.println("SFTRANS033:PASSED");
				System.out.println("SFTRANS034:PASSED");
			} else
			{
				System.out.println("SFTRANS:FAILED");
				System.out.println("SFTRANS:FAILED");
			}
		}

	public void sftrans035()
		throws SQLException
		{
			try
			{
				transactionapi.begin();
				String s1 = "insert into RAJESHSFTRANS035 values('Rajesh1')";
				connectionpool.execute(s1);
				String s2 = "select name from RAJESHSFTRANS035 where name like ?";
				int i = relationalapi.getPreparedStatementID(s2);
				PreparedStatementWrapper preparedstatementwrapper = relationalapi.fetchPreparedStatement(i);
				PreparedStatement preparedstatement = preparedstatementwrapper.getPreparedStatement();
				preparedstatement.setString(1, "Rajesh1");
				ResultSet resultset = relationalapi.executeQuery(preparedstatement);
				resultset.next();
				String s3 = resultset.getString(1);
				relationalapi.returnPreparedStatement(preparedstatementwrapper);
				transactionapi.commit();
				if(s3.equals("Rajesh1"))
					System.out.println("SFTRANS035:PASSED");
				else
					System.out.println("SFTRANS035:FAILED");
			}
			catch(Exception ex) {
				System.err.println("SFTRANS0353535:");
				ex.printStackTrace();}
		}

	
	

	public void sftrans036()
		throws SQLException
		{
			int i = connectionpool.getNumOfTransactionConnections();
		   if(i == 3)
				System.out.println("SFTRANS036:PASSED");
			else
				System.out.println("SFTRANS036:FAILED");
		}

	public void sftrans037()
		throws SQLException
		{
			boolean flag = connectionpool.isTransactionEnabled();
			if(flag)
				System.out.println("SFTRANS037:PASSED");
			else
				System.out.println("SFTRANS037:FAILED");
		}

	public void sftrans038()
		throws SQLException
		{
			boolean flag = connectionpool.isTransactionEnabled();
			if(flag)
				System.out.println("SFTRANS038:PASSED");
			else
				System.out.println("SFTRANS038:FAILED");
		}

	public void sftrans039()
		throws SQLException
		{
			boolean flag = connectionpool.isTransactionEnabled();
			if(flag)
				System.out.println("SFTRANS039:PASSED");
			else
				System.out.println("SFTRANS039:FAILED");
		}

	public void sftrans040()
		throws SQLException
		{
			if(connectionpool.getNumOfTransactionConnections() == 3)
				System.out.println("SFTRANS040:PASSED");
			else
				System.out.println("SFTRANS040:FAILED");
		}

	public void sftrans041() throws SQLException, NmsStorageException
	{
		if(connectionpool.getNumOfNonTransactionConnections() == 3)
			System.out.println("SFTRANS041:PASSED");
		else
			System.out.println("SFTRANS041:FAILED");
	}

	public void sftrans043() throws SQLException, NmsStorageException
	{
		int i = connectionpool.getNumOfTransactionConnections();
		if(i == 3)
			System.out.println("SFTRANS036:PASSED");
		else
			System.out.println("SFTRANS036:FAILED" + i);
	}

	public void sftrans044() throws SQLException, NmsStorageException
	{
		if(connectionpool.getNumOfNonTransactionConnections() == 7)
			System.out.println("SFTRANS044:PASSED");
		else
			System.out.println("SFTRANS044:FAILED");
	}

	public void sftrans054()
		throws SQLException, NmsStorageException
		{
			try
			{
				boolean flag = true;
				TopoAPI topoapi = null;
				while(topoapi==null)
				{
					topoapi = (TopoAPI)NmsUtil.getAPI("TopoAPI");
					Thread.sleep(1000);
				}
				String s = "RajeshMOBJ";
				transactionapi.begin();
				ManagedObject managedobject = new ManagedObject();
				managedobject.setName(s);
				topoapi.addObject(managedobject);
				if(topoapi.getByName(s) == null)
				{
					System.out.println("First get !=null");
					flag = false;
				}
				transactionapi.commit();
				if(topoapi.getByName(s) != null)
				{
					System.out.println("Second get ==null");
					flag = true;
				}
				if(flag)
					System.out.println("SFTRANS054:PASSED");
				else
					System.out.println("SFTRANS054:FAILED");
			}
			catch(Exception exception)
			{
				System.err.println("sftrans054:" + exception);
			}
		}

	public void sftrans055()
		throws SQLException, NmsStorageException
		{
			try
			{
				boolean flag = true;
				ResultSet resultset = NmsUtil.relapi.query("select max(id) from Event");
				int i;
				for(i = 0; resultset.next(); i = resultset.getInt(1));
		//		transactionapi.begin();
				String s = "RajeshEvent" + i;
				transactionapi.begin();
				AlertAPI alertapi = null;
				while(alertapi==null)
				{
					alertapi = (AlertAPI)NmsUtil.getAPI("AlertAPI");
				}
				EventAPI eventapi = null;
				while(eventapi==null)
				{
					eventapi = (EventAPI)NmsUtil.getAPI("EventAPI");
				}
				Event event = new Event();
				event.setId(i);
				event.setEntity(s);
				event.setSource(s);
				event.setSeverity(3);
				eventapi.addEvent(event);
				if(eventapi.getEventByID(i) == null)
				{
					System.out.println("eventapi.getEventByID(i) == null");
					flag = false;
				}
				if(alertapi.getAlert(s) == null)
				{
					System.out.println("alertapi.getAlert(s) == null");
					flag = false;
				}
				transactionapi.commit();
				if(eventapi.getEventByID(i) != null)
				{
					System.out.println("eventapi.getEventByID(i) != null");
					flag = true;
				}
				if(alertapi.getAlert(s) != null)
				{
					System.out.println("alertapi.getAlert(s) != null");
					flag = true;
				}
				if(flag)
				{
					System.out.println("SFTRANS055:PASSED");
				}
				else
				{
					System.out.println("SFTRANS055:FAILED");
				}
			}
			catch(Exception exception)
			{
				System.err.println("sftrans055:" + exception);
			}
			finally
			{
				try
				{
					transactionapi.commit();
				}
				catch(Exception excp)
				{
				}
			}
		}

	public void sftrans056()
	{
		try
		{
			boolean flag = true;
			byte byte0 = 2;
			String s = "testobject" + byte0;
			MapAPI mapapi = null;
			while(mapapi==null)
			{
				mapapi = (MapAPI)NmsUtil.getAPI("MapAPI");
			}
			RelationalAPI relationalapi = NmsUtil.relapi;
			transactionapi.begin();
			s = "testmap_" + byte0 + ".netmap";
			mapapi.addMap(s, null);
			if(mapapi.getMap(s) == null)
			{
				System.out.println("mapapi.getMap(s) == null");
				flag = false;
			}
			transactionapi.commit();
			if(mapapi.getMap(s) != null)
			{
				System.out.println("mapapi.getMap(s) == null");
				flag = true;
			}
			if(flag)
			{
				System.out.println("SFTRANS056:PASSED");
			}
			else
			{
				System.out.println("SFTRANS056:FAILED");
			}
		}
		catch(Exception exception)
		{
			System.err.println("sftrans056:" + exception);
		}
	}

	public void sftrans057()
	{
		try
		{
			boolean flag = true;
			byte byte0 = 16;
			String s = "polleddata" + byte0;
			RelationalAPI relationalapi = NmsUtil.relapi;
			String s1 = "agent";
			String s2 = "4.4.0";
			String s3 = s + "\t" + s1 + "\t" + s2;
			PollAPI pollapi = null;
			while(pollapi==null)
			{
				pollapi = (PollAPI)NmsUtil.getAPI("PollAPI");
			}
			PolledData polleddata = new PolledData();
			polleddata.setName(s);
			polleddata.setAgent(s1);
			polleddata.setOid(s2);
			transactionapi.begin(100000);
			pollapi.addPoll(polleddata);
			if(pollapi.getPolledData(s3) == null)
			{
				System.out.println("pollapi.getPolledData(s3) != null");
				flag = false;
			}
			transactionapi.commit();
			if(pollapi.getPolledData(s3) != null)
			{
				System.out.println("pollapi.getPolledData(s3) == null");
				flag = true;
			}
			if(flag)
			{
				System.out.println("SFTRANS057:PASSED");
			}
			else
			{
				System.out.println("SFTRANS057:FAILED");
			}
		}
		catch(Exception exception)
		{
			System.err.println("sftrans057:" + exception);
			exception.printStackTrace();
		}
	}

	public void shutDown()
	{
        executeQuery("drop table RAJESHSFTRANS008");
        executeQuery("drop table RajeshSFTRANS011");
        executeQuery("drop table RajeshSFTRANS016");
        executeQuery("drop table RAJESHSFTRANS0181");
        executeQuery("drop table RAJESHSFTRANS0182");
        executeQuery("drop table RAJESHSFTRANS0191");
        executeQuery("drop table RAJESHSFTRANS0192");
        executeQuery("drop table RAJESHSFTRANS021");
        executeQuery("drop table RajeshSFTRANS023");
        executeQuery("drop table RAJESHSFTRANS0251");
        executeQuery("drop table RAJESHSFTRANS0252");
        executeQuery("drop table RAJESHSFTRANS0261");
        executeQuery("drop table RAJESHSFTRANS0262");
        executeQuery("drop table RAJESHSFTRANS0271");
        executeQuery("drop table RAJESHSFTRANS0272");
        executeQuery("drop table RAJESHSFTRANS029");
        executeQuery("drop table RAJESHSFTRANS035");
	}

	private class CallInner extends Thread
	{
		int time;
		String id;
		CallInner(String id,int time)
		{
		
			super();
			this.time=time;
			this.id=id;
		}

        Exception ex = null;

        public Exception getException()
        {
            return ex;
        }
		public void run()
		{
			try
			{
				transactionapi.begin(time);
				Thread.sleep((time));
				transactionapi.commit();
			}
			catch(Exception excp)
			{
                this.ex = excp;
				System.out.println("free connection not available for id:"+id);
			}
		}
	}
}
