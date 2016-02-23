import java.io.*;
import java.lang.*;
import java.sql.*;

public class postgresInsert
{
	public static void main(String args[])
	{
		String url = "jdbc:postgresql://localhost/unicodedb";
		String drivername= "org.postgresql.Driver";
		String str="";

		Statement stmt = null;
		Connection conn = null;
		try{
			Class.forName(drivername);
			conn = DriverManager.getConnection(url, "postgres", "postgres" );
			stmt=conn.createStatement();
			/*str= "create table users1 (emp citext, pass text);";
			stmt.execute(str);
			str= "insert into users1 values('b', 'cddd');";
			stmt.execute(str);
			str= "insert into users1 values('b', 'cffff');";
			stmt.execute(str);*/
			str= "INSERT INTO Event(ID,DISCRIMINATOR,TEXT,CATEGORY,NETWORK,NODE,ENTITY,SEVERITY,TTIME,SOURCE,WEBNMS,GROUPNAME,SYSTEMLOCATION,SUBNET,IPADDRESS_SYSNAME,TRAPTYPE)VALUES (133228 , 'Event' , 'No Trap Parser defined for received trap: Enterprise: .1.3.6.1.4.1.1096.1.1.1 Generic Type: 6 Specific Type: 3 Variable Bindings: .1.3.6.1.4.1.1096.1.1.1.1.1.0: �, .1.3.6.1.4.1.1096.1.1.1.1.2.0: 7, .1.3.6.1.4.1.1096.1.1.1.1.3.0: PORT 1 RNA, .1.3.6.1.4.1.1096.1.1.1.1.4.0: Port 1 Ring-No-Answer, .1.3.6.1.4.1.1096.1.1.1.1.5.0:  PORT 1, .1.3.6.1.4.1.1096.1.1.1.1.6.0: Mon Mar 19 15:15:51 2012, .1.3.6.1.4.1.1096.1.1.1.1.7.0:' , 'Info Events' , null , '10.35.84.10' , '10.35.84.10' , 6 , '1332184551493' , '10.35.84.10' , null , null , 'Da Swamp' , '10.35.84.0' , '10.35.84.10 (p70apc)' , 'NULL' )";
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
