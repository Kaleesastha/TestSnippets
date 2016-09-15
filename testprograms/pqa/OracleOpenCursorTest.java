/** This is a test program for Oracle Database to work with java using JDBC.
 * This tests how many cursors are open for that particular user. 
 * @author T.S.Srikanth
 */
import java.sql.*;
import java.io.*;
import java.util.*;

public class OracleOpenCursorTest
{
	private static Connection conn = null;
	public static void main (String args []) throws Exception
	{
		String url = (args.length > 0 ) ? args[0] : "jdbc:oracle:thin:@localhost:1521:orcl";
		String username = (args.length > 1 ) ? args[1] : "SCOTT";
		String password = (args.length > 2 ) ? args[2] : "TIGER";

		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		conn = DriverManager.getConnection(url,username,password);
		try
		{
			Statement stmt = conn.createStatement();
			ResultSet rs = null;
			rs = stmt.executeQuery("select sql_text,count(*) from v$open_cursor group by sql_text order by count(*) desc");
			while (rs.next())
			{
				System.out.println(rs.getString(1) + "\t" + rs.getString(2));
			}
			rs = stmt.executeQuery("select count(*) from v$open_cursor");
			while (rs.next())
			{
				System.out.println("No of Open cursors " + rs.getInt(1));
			}
			rs = stmt.executeQuery("select count(*) from ManagedObject");
			if (rs.next()) System.out.println("No of ManagedObjects " + rs.getString(1));
			stmt.close();
			rs.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
