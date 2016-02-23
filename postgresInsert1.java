import java.sql.*;
public class postgresInsert1
{
	public static void main(String args[]) {
		String url = "jdbc:postgresql://localhost/testdb";
		String drivername= "org.postgresql.Driver";
		String str="";
		Statement stmt = null;
		Connection conn = null;
		try{
			Class.forName(drivername);
			conn = DriverManager.getConnection(url, "postgres", "postgres" );
			stmt=conn.createStatement();
			str= "create table users1 (emp citext, pass text);";
			stmt.execute(str);
			str= "insert into users1 values('b', 'cddd');";
			stmt.execute(str);
		}
		catch (Exception exp) {exp.printStackTrace();}
		finally{
			try{ if(conn !=null) {conn.close();}
				if(stmt != null) {stmt.close();}
			} catch (Exception e){}
		}
	} }
