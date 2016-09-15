package prepstat;

import com.adventnet.nms.util.RunProcessInterface;
import com.adventnet.management.transaction.ConnectionPool;

public class TestPrepStat implements RunProcessInterface
{
  private boolean initialized = false;

  public void callMain(String args[])
  {
	initialized = true;
	ConnectionPool pool = ConnectionPool.getInstance();
	while(true)
	{
		try
		{
			int id = pool.getPreparedStatementID("select * from ManagedObject");
			pool.fetchPreparedStatement(id);	
			Thread.sleep(20000);
		}
		catch(Exception excp)
		{
			excp.printStackTrace();
		}
	}
  }
  public boolean isInitialized()
  {
	return initialized;
  }
  public void shutDown()
  {
  }
}
