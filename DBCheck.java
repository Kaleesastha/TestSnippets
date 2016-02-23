import java.io.*;
import java.lang.*;
import java.sql.*;
import com.adventnet.nms.util.*;

public class DBCheck
{
	public static void main(String args[])
	{
		String str;
		//String url = "jdbc:mysql://venkatramanan/testdb";
		String url = "jdbc:mysql://localhost/sp2db?dumpQueriesOnException=true&amp;jdbcCompliantTruncation=false";
		String drivername= "com.mysql.jdbc.Driver";
		try{
			Class.forName(drivername);
			Connection conn = DriverManager.getConnection(url, "root", "" );
			//Thread.sleep(Integer.parseInt(args[0]));
			System.out.println("After wait");
			Statement stmt = null;
			stmt=conn.createStatement();
			str= "INSERT INTO test123 VALUES ('20')";
			stmt.execute(str);
			System.out.println("after execute");
		}
		catch (Exception exp)
		{exp.printStackTrace();}

	}
}
