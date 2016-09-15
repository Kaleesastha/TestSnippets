import java.sql.*;
import java.util.Vector;
import java.lang.Class;
import java.io.*;
import java.util.Date;

//PLEASE EDIT THE DRIVER AND THE URL STRING AS PER YOUR NEED.
public class PerfTest extends Thread {
	String args[];
	public static void main(String args[])
	{
		PerfTest pf = new PerfTest();
		pf.args = args;
		/*PerfTest pt = new PerfTest();
		if(args.length == 1)
			pt.readFile(args);
		else if((args.length == 2) && (args[1].equals("-1")))
		{
		 	pf.start();
		}
			
		else
			pt.doTheWork(args);*/
		pf.dumpValues(args);
	}
	//this run method is basically to make this Thread keep running so that the number of polls per second 
	//is calculated for every pollinterval given.
	public void run()
	{
		try {
		while(true)
		{
			getNumPollsPerSecond(args);
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	//this method is basically used to the dump the values in the database so that it will 
	//facilate in testing the graphs based on the value that has been dumped.
	//the arguments that has to be passed to this method are
	//1. tablename in which data has to be dumped.
	//2. the pollid for which data has to be appended
	//In this by default 10 data will be added for the given id from the currenttime with a 1 minute gap and a 
	//value of 10000 with a increment of 500.
	//PLEASE DO EDIT THE CODE IF YOU WANT TO CHANGE THE NUMBER AND VALUE ADDED.
	
	public void dumpValues(String args[])
	{
		try {
			//instantiates the driver with the DriverManager for getting the connection.
			Class.forName("org.gjt.mm.mysql.Driver");
			//Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(ClassNotFoundException c)
		{
			c.printStackTrace();
		}
		Connection con = null;
		Vector times = new Vector();
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/WebNmsDB");
			//con = DriverManager.getConnection("jdbc:oracle:thin:@poll2k:1521:webnmsdb","poll","poll");
			String sqlString = "";
			//time on which data is added.
			long ttime = System.currentTimeMillis();
			//value that is dumped.
			long value = 10000;
				for(int i=0;i<10;i++)
				{
					sqlString = "insert into " + args[0]+" values("+args[1]+",'-1'," + ttime +","+value+") ";
					ttime = ttime + 60*1000;
					PreparedStatement stmt = con.prepareStatement(sqlString);
					stmt.executeQuery();
					value = value + 500;
				}
				//System.out.println("sqlString is "+ sqlString);
				
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	//this basically used to check the Number of polls/sec for the specified interval.
	//the arguments to be passed are
	//1. table name in which the collected data is stored.
	//2. the pollinterval in seconds after which the next count has to be taken.
	//this basically takes the number of data in the table and after the interval it gets
	//the count again, the (difference in count)/pollInterval gives the Number of polls/sec.
	//NO NEED TO CHANGE ANYTHING HERE.
	public void getNumPollsPerSecond(String[] args)
	{
		long pollInterval = new Long(args[1]).longValue();
		try {
			
			Class.forName("org.gjt.mm.mysql.Driver");
			//Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(ClassNotFoundException c)
		{
			c.printStackTrace();
		}
		Connection con = null;
		Vector times = new Vector();
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/WebNmsDB");
			//con = DriverManager.getConnection("jdbc:oracle:thin:@poll2k:1521:webnmsdb","poll","poll");
			String sqlString = "";
			if(args[0] != null)
			{
				sqlString = "select count(*) from " + args[0];
				//System.out.println("sqlString is "+ sqlString);
				PreparedStatement stmt = con.prepareStatement(sqlString);
				ResultSet rs = stmt.executeQuery();
				int count1 = 0;
				int count2 = 0;
				if(rs != null)
				{	
					rs.next();
					count1 = rs.getInt(1);
				//	System.out.println(count1);
				}
				Thread t = Thread.currentThread();
				t.sleep(pollInterval*1000);
				rs = stmt.executeQuery();
				if(rs != null)
				{
					rs.next();
					count2 = rs.getInt(1);
				//	System.out.println(count2);
				}
				long numPolls = count2 - count1;
				numPolls = numPolls/pollInterval;
				System.out.println("The Polls/sec is " + numPolls);
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	//This method is used to get the difference in time between the two consecutive storage
	//of the same data. This basically is used to check whether data is collected and stored 
	//after every pollinterval.
	//the following arguments has to be passed
	//1. the table in which the collected data is stored.
	//2. the pollid whose consecutive differences has to be obtained.
	//This prints nothing if there is only one data is collected for that pollid.
	public void doTheWork(String[] args)
	{
		try {
			
			Class.forName("org.gjt.mm.mysql.Driver");
			//Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(ClassNotFoundException c)
		{
			c.printStackTrace();
		}
		Connection con = null;
		Vector times = new Vector();
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/WebNmsDB");
			//con = DriverManager.getConnection("jdbc:oracle:thin:@poll2k:1521:webnmsdb","poll","poll");
			String sqlString = "";
			if((args[0] != null) && (args[1] != null))
			{
				sqlString = "select * from " + args[0] + " where POLLID=" + args[1];
				System.out.println("sqlString is " + sqlString);
				PreparedStatement stmt = con.prepareStatement(sqlString);
				ResultSet rs = stmt.executeQuery();
				if(rs == null) System.out.println("No result");
				if(rs != null)
				{
					while(rs.next())
					{
						times.addElement(rs.getString(3));
					}
					long[] timesArr = processTimesVector(times);
					if(timesArr == null)
					{
						System.out.println("No Data Collected wait!!!!");
						return;
					}
					for(int i=0; i<timesArr.length;i++)
					{
						if(i+1 == timesArr.length) break;
						int diff = getTimeDiff(timesArr[i+1], timesArr[i]);
						System.out.println(" pollinterval : " + diff);
					}
				}
			}
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	private long[] processTimesVector(Vector times)
	{
		if (times.size() == 0) return null;
		long la[] = new long[times.size()];
		try 
		{
			for (int i=0;i<la.length;i++) 
			{
				if(((String)times.elementAt(i)).indexOf(".") != -1)

				{
					Double dummy = new Double((String)times.elementAt(i));
					la[i] = dummy.longValue();
				}
				else

				{
					la[i] = Long.parseLong((String)times.elementAt(i));

				}
			}
		} catch (Exception nfe) {}
		
		return la;
	}
	//used to get the difference in times in seconds when two long values are passed.
	//first argument has to be greater than second to get the proper result.
	public int getTimeDiff(long l1, long l2)
	{
		long  val1 = l1/1000;
		long val2 = l2/1000;
		int diff = (int)(val1 - val2);
		return diff;
	}
	//used to get the difference in times between two consecutive data collection for a PolledData
	//in serialized mode.
	//the arguments to be passed are
	//1. the directory in which the collected data is stored i.e. WebNMS<home>/polldata/current
	//2. the pollid whose difference has to be obtained.
	public void readFile(String[] args)
	{
		try {
			long a1=-1;
			
			String s=args[0];
			String  c;
			if (args.length>0)
			{
				s=s+args[0];
			}
			DataInputStream d=new DataInputStream(new FileInputStream(s));
			if((c=d.readLine())!=null)
			{
				
				int index=c.indexOf(" ");
				String s1=c.substring(0,index).trim();
				String s2=c.substring(index).trim();
				a1=Long.parseLong(s1);
				
				//System.out.println("PollTime: "+new Date(Long.parseLong(s1))+"  PollVal: "+s2);
			}	
			while ((c=d.readLine())!=null)
			{
				
				int index=c.indexOf(" ");
				
				
				String s1=c.substring(0,index).trim();
				String s2=c.substring(index).trim();
				int diff=(int)(Long.parseLong(s1)-a1);
				diff=diff/1000;
				//System.out.println("PollTime: "+new Date(Long.parseLong(s1))+"Pollval: "+s2+"   Sec: "+diff );
				System.out.println("Diff is " + diff);
				a1=Long.parseLong(s1);
				
			}
			d.close();
		}
		catch(Exception exc)
		{
			exc.printStackTrace();
		}
	}
	
}
