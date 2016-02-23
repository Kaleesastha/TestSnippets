import java.lang.*;
public class DBHostName
{

	public static void main(String[] s)
	{
		try
		{
			String dbName = s[0];
			String url= "";
			String dbHostName= "";
			String port = "";
			if(dbName.equalsIgnoreCase("mysql"))
				url="jdbc:mysql://nms-reg119:3306/testdb?dumpQueriesOnException=true&amp;jdbcCompliantTruncation=false";
			if(dbName.equalsIgnoreCase("oracle"))
				url="jdbc:oracle:thin:@HOST_NAME:PORT_NUMBER:CONNECT_STRING";
			if(dbName.equalsIgnoreCase("sybase"))
				url="jdbc:sybase:Tds:localhost:2638/WebNmsDB";
			if(dbName.equalsIgnoreCase("postgres"))
				url="jdbc:postgresql://localhost/WebNmsDB";
			if(dbName.equalsIgnoreCase("mssql"))
				url="jdbc:sqlserver://localhost:1433;databaseName=WebNmsDB";
			if(dbName.equalsIgnoreCase("mysql") || dbName.equalsIgnoreCase("mssql") || dbName.equalsIgnoreCase("postgres"))
			{
				String tmp[] = url.split(":");//No I18N
                                String tmp1[] = tmp[2].split("/");//NO I18N
                                dbHostName = tmp1[2];
				if(dbName.equalsIgnoreCase("mysql") || dbName.equalsIgnoreCase("postgres"))
				{
					if(tmp.length > 3)
					{
						String t[] = tmp[3].split("/");
						port = t[0];
						for(int k=0;k<t.length;k++)
						{
							System.err.println("==>"+t[k]);
						}
						String t2[]=t[1].split("\\?");
						System.err.println("DBName : "+t2[0]);
					}
					else
					{
						System.err.println("=========="+tmp[2]);
						String t[] = tmp[2].split("//");
						String t1[]=t[1].split("/");
						String t2[] = t1[1].split("\\?");
						System.err.println(">>>>"+t2[0]);
						if(dbName.equalsIgnoreCase("mysql"))
						{
							port = "3306";
						}
						else if (dbName.equalsIgnoreCase("postgres"))
							port = "5432";
					}
				}
				else if(dbName.equalsIgnoreCase("mssql"))
				{
					String t[] = tmp[tmp.length-1].split(";");
					port  = t[0];
				}
			}
			else if(dbName.equalsIgnoreCase("oracle"))//No I18N
			{
				String tmp[] = url.split(":");//No I18N
				dbHostName = tmp[3].substring(1,tmp[3].length());
				port =tmp[4];
			}
			else if(dbName.equalsIgnoreCase("sybase"))
			{
				String tmp[] = url.split(":");//No I18N
				String tmp1[] = tmp[3].split("/");
                                dbHostName = tmp1[0];
				String t[] = tmp[tmp.length-1].split("/");
				port =t[0];
			}
			System.err.println("host & port are ==>"+dbHostName+", "+port);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}

