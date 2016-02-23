import java.io.*;
import java.lang.*;
import java.sql.*;
import com.adventnet.nms.util.*;

public class InsertData
{
   public static void main(String args[])
   {
	int i=1;
	String str;
       String url = "jdbc:oracle:thin:@192.168.15.198:1521:oradb";
       //String url = "jdbc:firebirdsql:venkatramanan/3050:C:/Firebird/bin/WEBNMSDB";
//	String url = "jdbc:mysql://localhost:3306/comnew?useUnicode=true&jdbcCompliantTruncation=false&dumpQueriesOnException=true&characterEncoding=utf8";
       String drivername= "oracle.jdbc.driver.OracleDriver";
	//String drivername= "com.mysql.jdbc.Driver";
	
	try{
	Class.forName(drivername);
	Connection conn = DriverManager.getConnection(url, "root", "" );
	Statement stmt = null;
	stmt=conn.createStatement();
 //str= "INSERT INTO Event VALUES ( 102 , 'Database Backup Started' , 'No Detail' , 'NULL' , 'NULL' , 'NULL' , 'BIGNET3243;Database Backup Started' , 6 , "+new Date(System.currentTimeMillis())+"  , 'BIGNET3243' , 'NULL' , 'NULL' , 'NULL' , 'NULL' )";
	//str = "insert into sp3.USMTABLE (DBKEY, HOST, PORT, ENGINENAME, ENGINEID, USERNAME, SECURITYLEVEL, SECURITYNAME, AUTHPROTOCOL, AUTHPASSWORD, AUTHKEY, PRIVPROTOCOL, PRIVPASSWORD, PRIVKEY, ENGINETIME, ENGINEBOOTS, LATESTRCVDENGTIME, `LOCALTIME`) VALUES ('test1','test','test','test','test','test','test','test','test','test','test','test','test','test','test','test','test','test')";
	str = "insert into emp values ('NKi\\u00F1o')";
	stmt.execute(str);

	/*if(conn == null)
	{
		System.out.println("Connection is: " + conn);
	}*/
	
	//DBParamsParser dbpp=DBParamsParser.getInstance(new File("C:/7"+"/"+"conf"+"/"+"database_params.conf"));
	//System.out.println(" The Database name is " +dbpp.getDatabaseName());
	
	}
	catch (Exception exp)
	{exp.printStackTrace();}
	
        
/*	System.out.println("The time before Inserting data is " +System.currentTimeMillis());
	for (i=10001; i<=60000; i++)
	{
	//str = ("INSERT INTO EVENT VALUES ('vbala', 20, '2431521'," + i + ")");
	
	}
	System.out.println("The time after Inserting data is " + System.currentTimeMillis());*/
	}
   }
