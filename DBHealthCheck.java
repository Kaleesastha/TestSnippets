//$Id: $@
package test;

import java.sql.*;
import java.lang.Runtime;

import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.store.relational.RelationalAPI;
import com.adventnet.nms.store.relational.RelationalUtil;
import com.adventnet.nms.store.NmsStorageException;
import com.adventnet.management.log.SystemUtil;
import com.adventnet.management.transaction.PreparedStatementWrapper;
import com.adventnet.nms.util.RunProcessInterface;
import com.adventnet.management.scheduler.*;
import com.adventnet.nms.admin.ShutDownServer;
import com.adventnet.nms.admin.ShutDownHook;
import com.adventnet.management.transaction.UserTransactionException;
import com.adventnet.management.transaction.ConnectionPool;

/* This Process queries the database every specified interval.If the result for the query is not obtained with in the
   time-out value, it assumes that the DB machine is not in network or the DB has gone down and cleans up all the connection
   to the database.*/

public class DBHealthCheck implements RunProcessInterface,Runnable
{
	private RelationalAPI relapi;
	private int checkId; //preparedstatement id
	private int interval;// time interval in which the query will be executed.
	private int timeout; // Time out value before which a query has to return.
	private Scheduler sch = null;

	public void dbinit()
	{
		relapi = NmsUtil.relapi;
		String checkStr = "select * from BEFailOver ";
		checkId = relapi.getPreparedStatementID(checkStr);
		sch=getScheduler();

	}

	//implementation of runnable (for nms scheduler to run)
	public void run()
	{
		PreparedStatementWrapper psw = relapi.fetchPreparedStatement(checkId);
		PreparedStatement ps = psw.getPreparedStatement();
		ResultSet count = null;
		try
		{
			DBTimeoutHandler handler= new DBTimeoutHandler (sch,timeout);
			count = relapi.executeQuery(ps);
			sch.removeTask(handler);
		}
		catch (Exception nse)
		{
			nse.printStackTrace();
		}

		finally
		{
			if(count!=null)
			{
				try
				{
					count.close();
					count=null;
				}catch(Exception e){}
			}
			relapi.returnPreparedStatement(psw);
		}
		sch.scheduleTask(this, System.currentTimeMillis() +interval*1000 );//re-scheduling the thread.
	}
	public class DBTimeoutHandler implements Runnable
	{
		private int timeOutValue = 5000;
		/**
		 * Construct the object
		 */
		public DBTimeoutHandler (Scheduler sch ,int timeout)
		{
			this.timeOutValue = timeout;
			sch.scheduleTask(this , System.currentTimeMillis() + timeOutValue*1000);
		}
		public void run()
		{
			try
			{
				System.err.println("ADVENTNET DEBUG: SERVER IS GOING TO GET SHUTDOWN");

				System.err.println("Exitting from WebNMS");

				System.out.println("Going to Exit");
				//System.exit(0);Apache would be shutdown after this & WebNMS would be killed.Hence any clean up NOT PERTAINING to Database should be done here. **/
				ShutDownHook.exitJVM= false;
				ShutDownServer sh = new ShutDownServer();
				sh.shutDownWebServer(0);

				Runtime.getRuntime().halt(0);
			}catch(Exception e)
			{e.printStackTrace();}
		}
	}// DBTimeoutHandler
	public void callMain(String args[])
	{
		System.out.println("ADVENTNET DEBUG:THIS ENSURES THE START OF THE DB_HEALTH_CHECK ");
		dbinit();
		interval= Integer.parseInt(args[0]);
		timeout=Integer.parseInt(args[1]);
		sch.scheduleTask(this, System.currentTimeMillis() +interval*1000 );
	}
	private Scheduler getScheduler()
	{
		if (sch==null)
		{
			sch = Scheduler.createScheduler("dbhealthcheck" , 2);
			if(!sch.isAlive())
			{

				sch.start();
			}
		}
		return sch;
	}

	public void shutDown()
	{
	}

	public boolean isInitialized()
	{
		return true;
	}

}
