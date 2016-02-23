import java.sql.*;
import java.util.*;
import java.io.*;
public class Student
{
	public static void main(String args[])
	{
		Connection con=null;
		Statement stm=null;
		try
		{
			int count;
			System.out.println("1...");

			Class.forName("com.timesten.jdbc.TimesTenDriver").newInstance();
			System.out.println("2...");
                                                                               
			con=DriverManager.getConnection("jdbc:timesten:direct:WebNmsDB ","root","");				
			System.out.println("3...");	
			stm=con.createStatement();
	
		
			System.out.println("connection established");
			for (int j=1;j<=10;j++)
			{
				System.out.println("enter 1 to add record");
				System.out.println("enter 2 to rem record");
				System.out.println("enter 3 to view all the records");
				System.out.println("enter 4 to view particular record");
				System.out.println("enter 5 to exit");
				InputStreamReader reader    = new InputStreamReader(System.in); 
           			BufferedReader  br  = new BufferedReader(reader); 
				String str= br.readLine();
				int i=Integer.parseInt(str);
				String quote = "'";
				ResultSet rs=null;
				switch(i)
					{
					case 1:	System.out.println("Enter the name ");
					       	InputStreamReader reader1 = new InputStreamReader(System.in);
						BufferedReader br1 = new BufferedReader(reader1);	
						String str1=br1.readLine();				
						System.out.println("Enter the DOB as YYYY-MM-DD");
						InputStreamReader reader2 = new InputStreamReader(System.in);
						BufferedReader br2 = new BufferedReader(reader2);
						String str2=br2.readLine();
						System.out.println("Enter the DOJ as YYYY-MM-DD");
						InputStreamReader reader3 = new InputStreamReader(System.in);
						BufferedReader br3 = new BufferedReader(reader3);
						String str3=br3.readLine();
						System.out.println("Enter the class");
						InputStreamReader reader4 = new InputStreamReader(System.in);
						BufferedReader br4 = new BufferedReader(reader4);
						String str4=br4.readLine();										      stm.executeUpdate("Insert into student (Name,DOB,DOJ,Class) values("+quote+str1+quote+","+quote+str2+quote+","+quote+str2+quote+","+quote+str4+quote+");");
						//stm.close();
						break;
					case 2:System.out.println("Enter the name whose record to be deleted");
						InputStreamReader reader5 = new InputStreamReader(System.in);
						BufferedReader br5 = new BufferedReader(reader5);	
						String str5=br5.readLine();
						stm.executeUpdate("delete from student where Name="+quote+str5+quote+";");
						break;
					case 3: stm.executeQuery("select * from Node;");
						rs=stm.getResultSet();
						System.out.println("Name 		DOB	 		DOJ		 	CLASS");
							while (rs.next()!=false) 
							{
							String name=rs.getString("NAME");
							System.out.print(name+"		");
							String dob=rs.getString("ISROUTER");
							System.out.print(dob+"		");
							String doj=rs.getString("OWNERNAME");
							/*System.out.print(doj+"		");
							int Class=rs.getInt("class");
							System.out.println(Class+"	");*/
							}											break;
					case 4:System.out.println("Enter the name whose record to be viewed");
						InputStreamReader reader6 = new InputStreamReader(System.in);
						BufferedReader br6 = new BufferedReader(reader6);	
						String str6=br6.readLine();
						stm.executeQuery("select * from student where Name="+quote+str6+quote+";");
						rs=stm.getResultSet();
						System.out.println("Name 		DOB	 		DOJ		 	CLASS");

							while (rs.next()!=false) 
							{
							String name=rs.getString("Name");
							System.out.print(name+"		");
							String dob=rs.getString("DOB");
							System.out.print(dob+"		");
							String doj=rs.getString("DOJ");
							System.out.print(doj+"		");
							int Class=rs.getInt("class");
							System.out.println(Class+"	");
							}		
						break;
					case 5:System.out.println("Logging out");
						System.exit(0);
					}
			}
			
		}
		catch(Exception e)
		{
		System.out.println(e);
		}
		
	}
}
