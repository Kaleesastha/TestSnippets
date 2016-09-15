package test;
import com.adventnet.nms.util.*;
import com.adventnet.security.authorization.*;
import com.adventnet.security.AuthUtil;
import java.sql.*;
import java.io.*;
public class GetEncryptedPasswords {
	public static void main (String args[]) {
		Connection con=null;
		Statement stm=null;
		ResultSet rs = null;
		try{
			File file = new File("hibernate.cfg.xml");
			if(!file.exists()){
				System.err.println("Please copy hibernate.cfg.xml from NMS_HOME/classes/hbnlib to this directory and then execute this file");
				System.exit(0);
			}
			DBParamsParser parse = DBParamsParser.getInstance(file);
			String url = parse.getURL();
			String user = parse.getUserName();
			String driver = parse.getDriverName();
			String passwd = parse.getPassword();
			Class.forName(driver);
			con=DriverManager.getConnection(url ,user, passwd);				
			stm=con.createStatement();
			String query = "select USERNAME, PASSWORD from UserPasswordTable";
			stm.executeQuery(query);
			rs = stm.getResultSet();
			System.err.println("userName \toldPassword \tplainTextPassword \tnewEncryptedPassword");
			System.err.println("=================================================================");
			while(rs.next()) {
				String userName = rs.getString(1);
				String password = rs.getString(2);
				String plainText = Coding.oldconvertFromBase(password);
				System.err.println(userName +" \t"+password+" \t"+plainText+" \t"+AuthUtil.getOneWayEncryptedText(plainText));
			}
		} catch(ArrayIndexOutOfBoundsException ex){
			System.err.println("Seems the passwords are not properly encrypted (Already SHA-1 encrypted?). Passwords should have been encrypted with Coding.convertToNewBase() only");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{stm.close(); stm = null;
				rs.close(); rs=null;
				con.close(); con = null;
			} catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
