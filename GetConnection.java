package test;

import com.adventnet.nms.util.*;
import com.adventnet.management.transaction.ConnectionPool;
import java.sql.Connection;
public class GetConnection implements RunProcessInterface
{
	public void callMain(String args[])
	{
		try{
			Thread.sleep(50000);
			Connection con = ConnectionPool.getInstance().getConnection();
			System.err.println("Got connection");
			Thread.sleep(700000);
		}
		catch(Exception e)
		{
			System.err.println("this test is Fail");
			e.printStackTrace();
		}
	}
	public boolean isInitialized()
	{
		return true;
	}
	public void shutDown()
	{

	}
}
