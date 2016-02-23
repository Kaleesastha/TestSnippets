import java.io.*;
import java.lang.*;
import java.sql.*;

public class InsertDataV6IP
{
	public static void main(String args[])
	{
		int i=1;
		String str;
		//String url = "jdbc:oracle:thin:@2009:80:0:0:0:0:0:2:1521:orcl";
		//String url = "jdbc:oracle:thin:@[2009:80:0:0:0:0:0:2]:1521:webnmsdb";
		String url = "jdbc:postgresql://[fe80:0:0:0:d6be:d9ff:fe12:4645]/sha152sp1";
		//String url = "jdbc:postgresql://localhost:5432/sha152sp1";
		//String url = "jdbc:oracle:thin:@daiki-pc.adventnet.local:1521:webnmsdb";
		//String drivername= "oracle.jdbc.driver.OracleDriver";
		String drivername= "org.postgresql.Driver";
		try{
			Class.forName(drivername);
			Connection conn = DriverManager.getConnection(url, "postgres", "postgres" );
			Statement stmt = null;
			stmt=conn.createStatement();
			str ="create table Event(  \"ID\" INTEGER NOT NULL, \"TEXT\" varchar(200), \"CATEGORY\" varchar(100), \"DDOMAIN\" varchar(100), \"NETWORK\" varchar(100), \"NODE\" varchar(100), \"ENTITY\" varchar(100), \"SEVERITY\" INTEGER, \"TTIME\" BIGINT, \"SOURCE\" varchar(100), \"HELPURL\" varchar(100), \"WEBNMS\" varchar(100), \"GROUPNAME\" varchar(100), \"OWNERNAME\" varchar(25) NOT NULL, PRIMARY KEY (\"ID\",\"OWNERNAME\"), index Event0_ndx (\"ID\"), index Event1_ndx (\"OWNERNAME\"))";
			stmt.execute(str);
			System.err.println("After creating Event table");
		}
		catch (Exception exp)
		{exp.printStackTrace();}
	}
}
