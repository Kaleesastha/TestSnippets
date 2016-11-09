import java.sql.*;
public class OracleInsert
{
	public static void main(String args[]) {
		String drivername= "oracle.jdbc.OracleDriver";
		String url = "jdbc:oracle:thin:@10.24.66.2:1521:ORACLE" ;
		String userName = "webnms";
		String password = "webnms";
 		String str="";
		Statement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try{
			Class.forName(drivername);
			conn = DriverManager.getConnection(url, userName,password );
			stmt=conn.createStatement();
			try{
				str= "create table users1 (emp VARCHAR(20), pass VARCHAR(20));";
				stmt.execute(str);
			}catch(Exception exp){
				String message = exp.getMessage().toLowerCase();
				if(message.indexOf("already exists") != -1){
					System.err.println("table already exists");
				}
				else{exp.printStackTrace();}
			}
			str= "insert into users1 values('b', 'cddd');";
			stmt.execute(str);

			String query = "select emp, pass from users1";
			stmt.executeQuery(query);
			rs = stmt.getResultSet();
			System.err.println("emp \tpass");
			System.err.println("=============");
			while(rs.next()) {
				String emp = rs.getString(1);
				String pass = rs.getString(2);
				System.err.println(emp +" \t"+pass);
			}
		}
		catch (Exception exp) {exp.printStackTrace();}
		finally{
			try{ if(conn !=null) {conn.close();}
				if(stmt != null) {stmt.close();}
				if(rs != null) {rs.close();}
			} catch (Exception e){}
		}
	} }
