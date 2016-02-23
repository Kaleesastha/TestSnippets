import java.io.*;
import java.lang.*;
import java.sql.*;
import com.adventnet.nms.util.*;

public class CheckReplication
{
   public static void main(String args[])
   {
	String url = "jdbc:mysql://localhost/aricent?dumpQueriesOnException=true&amp;jdbcCompliantTruncation=false" ;
	String userName = "root";
	String password ="";
	String drivername = "com.mysql.jdbc.Driver";
	Connection conn =  null; 
	Statement stmt = null;
	try{
		DBParamsParser parser = DBParamsParser.getInstance(new File(PureUtils.rootDir+"classes/hbnlin/hibernate.cfg.xml"));
		url = PureServerUtils.url;
		userName = PureServerUtils.userName;
		password = PureServerUtils.password;
		drivername= PureServerUtils.driverName;
		PureServerUtils.getDatabaseParams();
	}
	catch (Exception e1)
	{
		System.err.println("Exception in getting Secondary params");
		e1.printStackTrace();
	}
	try{
	Class.forName(drivername);
	conn = DriverManager.getConnection(url,userName,password);
	}
	catch(Exception e2)
	{

		System.err.println("Exception in getting Connection with Secondary DB");
		e2.printStackTrace();
	}
	if (conn != null)
	{
		try{
			stmt=conn.createStatement();
			String str= "show slave status";
			ResultSet rs = stmt.executeQuery(str);
			ResultSetMetaData md = rs.getMetaData();
			int count = md.getColumnCount();
			while(rs.next())
			{
				for (int i=1; i<=count; i++) {
					System.err.println("--\n");
					System.err.println(rs.getString(i));
				}
			}
		}
		catch(Exception e3)
		{
			System.err.println("DEBUG:Exception in getting slave status in replication");
			e3.printStackTrace();
		}
	}
	else
	{
		System.err.println("DEBUG:Secondary DB Connection is NULL ");
	}

	/*if(conn == null)
	{
		System.out.println("Connection is: " + conn);
	}*/
	
	//DBParamsParser dbpp=DBParamsParser.getInstance(new File("C:/7"+"/"+"conf"+"/"+"database_params.conf"));
	//System.out.println(" The Database name is " +dbpp.getDatabaseName());
	
	}
	/*catch (Exception exp)
	{exp.printStackTrace();}*/
	
        
/*	System.out.println("The time before Inserting data is " +System.currentTimeMillis());
	for (i=10001; i<=60000; i++)
	{
	//str = ("INSERT INTO EVENT VALUES ('vbala', 20, '2431521'," + i + ")");
	
	}
	System.out.println("The time after Inserting data is " + System.currentTimeMillis());*/
     private void logDBSlaveInfo()
 	     {
 	             Statement stmt=null;
 	             ResultSet rs=null;
 	             try
 	             {
 	                     String getStr = "show slave status"; //No Internationalization
 	                     stmt = NmsUtil.relapi.query(getStr, true);
 	                     rs = stmt.getResultSet();
 	                     ResultSetMetaData md = rs.getMetaData();
 	                     int columnCount = md.getColumnCount();
 	                     System.err.println("\n######################## PRIMARY DATABASE SLAVE STATUS STARTS #############################");//No I18N
 	                     while(rs.next())
 	                     {
 	                             for (int i=1; i<=columnCount; i++) {
 	                                     System.err.println("["+md.getColumnName(i)+"\t: "+rs.getString(i)+" ]");//No I18N
 	                             }
 	                     }
 	                     System.err.println("######################## PRIMARY DATABASE SLAVE STATUS ENDS #########################");//No I18N
 	             }
 	             catch(Exception e3)
 	             {
 	                     System.err.println("Exception in getting slave status for Primary DB");//No I18N
 	                     e3.printStackTrace();
 	             }
 	             finally{
 	                     if(rs!=null)
 	                     {
 	                             try{rs.close();}catch(Exception e){}
 	                     }
 	                     if(stmt!=null)
 	                     {
 	                             try{stmt.close();}catch(Exception e){}
 	                     }
 	             }
 	             String url ="";//No I18N
 	             String userName="";//No I18N
 	             String password="";//No I18N
 	             String drivername="";//No I18N
 	             Connection conn =  null; 
 	             try{
 	                     File file= new File(PureUtils.rootDir+"conf/secondary/database_params.conf");//No I18N
 	                     if(!file.exists())
 	                     {
 	                             System.err.println(PureUtils.rootDir+"/conf/secondary/database_params.conf is not present! Unable to get replicated DB's status!");//No I18N
 	                             return;
 	                     }
 	                     DBParamsParser parser = DBParamsParser.purgeInstance(file);
 	                     url = PureServerUtils.url;
 	                     userName = PureServerUtils.userName;
 	                     password = PureServerUtils.password;
 	                     drivername= PureServerUtils.driverName;
 	                     PureServerUtils.getDatabaseParams();
 	             }
 	             catch (Exception e1)
 	             {
 	                     System.err.println("Exception in getting Secondary params");//No I18N
 	                     e1.printStackTrace();
 	             }
 	             try{
 	                     Class.forName(drivername);
 	                     conn = DriverManager.getConnection(url,userName,password);
 	             }
 	             catch(Exception e2)
 	             {
 	                     System.err.println("Exception in getting Connection with Secondary DB");//No I18N
 	                     e2.printStackTrace();
 	             }
 	             if (conn != null)
 	             {
 	                     try{
 	                             stmt=conn.createStatement();
 	                             String str= "show slave status";//No I18N
 	                             rs = stmt.executeQuery(str);
 	                             ResultSetMetaData md = rs.getMetaData();
 	                             int columnCount = md.getColumnCount();
 	                             System.err.println("\n######################## STANDBY DATABASE SLAVE STATUS STARTS #########################");//No I18N
 	                             while(rs.next())
 	                             {
 	                                     for (int i=1; i<=columnCount; i++) {
 	                                             System.err.println("["+md.getColumnName(i)+"\t: "+rs.getString(i)+" ]");//No I18N
 	                                     }
 	                             }
 	                             System.err.println("######################## STANDBY DATABASE SLAVE STATUS ENDS #########################");//No I18N
 	                     }
 	                     catch(Exception e3)
 	                     {
 	                             System.err.println("Exception in getting slave status for STANDBY DB");//No I18N
 	                             e3.printStackTrace();
 	                     }
 	                     finally{
 	                             if(rs!=null)
 	                             {
 	                                     try{rs.close();}catch(Exception e){}
 	                             }
 	                             if(stmt!=null)
 	                             {
 	                                     try{stmt.close();}catch(Exception e){}
 	                             }
 	                     }
 	             }
 	             else
 	             {
 	                     System.err.println("Secondary DB Connection is NULL ");//No I18N
 	             }
 	     }

	}
  // }
