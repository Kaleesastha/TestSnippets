import java.io.*;
import java.lang.*;
import java.sql.*;

public class MySQLInsert
{
	public static void main(String args[])
	{
		//String url = "jdbc:mysql://192.168.16.56/test";
		String url = "jdbc:mysql://localhost/nms5?jdbcCompliantTruncation=false";
		String drivername= "org.gjt.mm.mysql.Driver";
		String str="";

		Statement stmt = null;
		Connection conn = null;
		try{
			Class.forName(drivername);
			conn = DriverManager.getConnection(url, "root", "" );
			stmt=conn.createStatement();
			str= "create table users3 (emp varchar(3));";
			stmt.execute(str);
			str= "insert into users3 values('1234');";
			stmt.execute(str);
		}
		catch (Exception exp)
		{exp.printStackTrace();}
		finally{
			try{
				if(conn !=null)
					conn.close();
				if(stmt != null)
					stmt.close();
			}
			catch (Exception e){}
		}

	}
}
