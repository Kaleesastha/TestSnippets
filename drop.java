import java.io.*;
import java.lang.*;
import java.sql.*;
//import com.adventnet.security.authorization.*;
import java.util.*;
public class drop
{
   public static void main(String args[])
   {
   try{
	String filename = "C:"+File.separator+"WebNMS"+File.separator+"BE12"+File.separator+"conf"+File.separator+"DatabaseSchema.conf";
 BufferedReader din = new BufferedReader(new FileReader(filename));
            String line = "";
            //String stmt = "";
       
       String url = "jdbc:solid://localhost:1313/dba/dba";
       String drivername= "solid.jdbc.SolidDriver";

	Class.forName(drivername);
	Connection conn = DriverManager.getConnection(url, "sysdba", "masterkey");
	Statement stmt = null;
	stmt=conn.createStatement();
/*	String Str1 ="INSERT INTO TopoObject(NAME,IPADDRESS,NETMASK,COMMUNITY,WRITECOMMUNITY,SNMPPORT,ISDHCP,BASEMIBS,OWNERNAME,VERSION,USERNAME,CONTEXTNAME,ISSNMP,ISNODE,ISNETWORK,ISINTERFACE)VALUES('1119.119.119.0' , '119.119.119.0' , '255.255.255.0' , 'public' , 'public' , 161 , 'false' , '' , '2NULL' , '' , '' , '' , 'false' , 'false' , 'true' , 'false' )";
	stmt.addBatch(Str1);
	String Str2="INSERT INTO MapContainer(NAME,OBJNAME,MAPNAME,OWNERNAME,LABEL,ICONNAME,MENUNAME,WIDTH,HEIGHT,X,Y,WEBNMS,GROUPNAME,ANCHORED,CURRENTTOPOLOGY,TOPOLOGY,CONTAINMENT,OBJTYPE,PARENTNAME)VALUES('192.168.111.1190' , '192.168.111.90' , '192.168.111.10Switches.netmap' , '2NULL' , '192.168.111.90 : Wireless LAN Dual Mode Access Point' , 'switch2.png' , 'null' , -1 , -1 , -1 , -1 , '' , 'null' , 'false' , 'switch' , '$switch' , 'true' , 1 , 'null' )";
	stmt.addBatch(Str2);
	stmt.executeBatch();*/
	while ((line = din.readLine()) != null)
	{
	if(line.startsWith("drop table"))
	{
		//System.out.println(line);
		try{stmt.execute(line);
		System.out.println(line);}
		catch(Exception ex){ex.printStackTrace();}
	}
	}
   }
   catch(Exception ex){ex.printStackTrace();}
 
         }
}
