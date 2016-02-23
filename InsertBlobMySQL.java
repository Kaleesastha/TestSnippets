import java.sql.*;
import java.io.*;
 
class InsertBlobMySQL
{
	public static void main(String[] args) throws SQLException {

		Connection con=null;
		ResultSet rs=null;
		PreparedStatement psmt=null;
		FileInputStream fis;
		String url="jdbc:mysql://localhost:3306/blobdb";
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con=DriverManager.getConnection(url,"root","");

			File image=new File("/home/local/ZOHOCORP/venkat-0773/kbase/images/Enable_Logging_enable_console.png");

			psmt=con.prepareStatement("insert into inimage(name,city,image)"
					+"values(?,?,?)");
			psmt.setString(1,"Nitika");
			psmt.setString(2,"Delhi");

			fis=new FileInputStream(image);

			psmt.setBinaryStream(3, (InputStream)fis, (int)(image.length()));
			int s = psmt.executeUpdate();

			if(s>0) {
				System.out.println("Image Uploaded successfully !");
			}
			else {
				System.out.println("unsucessfull to upload image.");
			}
			con.close();
			psmt.close();
		}catch(Exception ex){
			System.out.println("Error in connection : "+ex);
		}
	}
}
