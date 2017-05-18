package test;

import com.adventnet.nms.util.*;
import java.sql.*;
import java.util.*;
import java.io.*;
public class CheckReplication implements RunProcessInterface
{
        public void callMain(String args[])
        {
                while(true){
                        logDBSlaveInfo();
                        try{Thread.sleep(20000);} catch(Exception exp) {}
                }
        }
        public boolean isInitialized()
        {
                return true;
        }
        public void shutDown()
        {
                System.out.println("Shutdown of CheckReplication called");
        }
        private void logDBSlaveInfo()
        {
                String getStr = "show slave status"; //No Internationalization
                Statement stmt=null;
                ResultSet rs=null;
                String masterHost = null;
                try
                {
                        Properties props = new Properties();
                        stmt = NmsUtil.relapi.query(getStr, true);
                        rs = stmt.getResultSet();
                        ResultSetMetaData md = rs.getMetaData();
                        int columnCount = md.getColumnCount();
                        System.err.println("\n######################## PRIMARY DATABASE SLAVE STATUS STARTS #############################");//No I18N
                        while(rs.next())
                        {
                                for (int i=1; i<=columnCount; i++) {
                                        String key = md.getColumnName(i);
                                        String value = rs.getString(i);
                                        //System.err.println("key, value : " +key+", "+value);
                                        if(key != null && value != null){
                                                props.setProperty(md.getColumnName(i),rs.getString(i));//No I18N
                                        }
                                }
                        }
                        System.err.println("props are :"+props);
                        String slave_io = props.getProperty("Slave_IO_Running");
                        String slave_sql = props.getProperty("Slave_SQL_Running");
			if(!slave_sql.equalsIgnoreCase("yes") || !slave_io.equalsIgnoreCase("yes")) {
				String message = "Replication might be broken"; //No I18N
				ByteArrayOutputStream byteStream=new ByteArrayOutputStream();
				DataOutputStream outp=new DataOutputStream(byteStream);
				outp.writeInt(97); // this int is to indicate that it is a broadcast info.
				outp.writeUTF(message);
				outp.writeUTF("ALLCLIENTS"); //No I18N
				outp.flush();
				PureServerUtilsBE.specialSessionBE.broadcastToAllClients("BROADCAST_FROM_CLIENT","0",byteStream.toByteArray()); //No I18N
			}
                        masterHost = props.getProperty("Master_Host");
                }
                catch(Exception e3)
                {
                        System.err.println("Exception in getting slave status for Primary DB");//No I18N
                        e3.printStackTrace();
                }
                finally{
                        if(rs!=null)
                        {
                                try{rs.close();}catch(Exception e){}
                        }
                        if(stmt!=null)
                        {
                                try{stmt.close();}catch(Exception e){}
                        }
                }
                Statement stmt2=null;
                ResultSet rs2=null;
                Connection conn =  null;
                Properties props = new Properties();
                try{
                        DBParamsParser dbpp = DBParamsParser.getInstance();
                        String url = dbpp.getURL();//No I18N
                        String host = dbpp.getDBHost();
                        url = url.replaceAll(host,masterHost);
                        System.err.println("url is : "+url);
                        String userName = dbpp.getUserName();//No I18N
                        String password = dbpp.getPassword();//No I18N
                        String drivername=dbpp.getDriverName();//No I18N

                        Class.forName(drivername);
                        conn = DriverManager.getConnection(url,userName,password);
                        if (conn != null)
                        {
                                stmt2=conn.createStatement();
                                rs2 = stmt2.executeQuery(getStr);
                                ResultSetMetaData md = rs2.getMetaData();
                                int columnCount = md.getColumnCount();
                                System.err.println("\n######################## STANDBY DATABASE SLAVE STATUS STARTS #########################");//No I18N
                                while(rs2.next())
                                {
                                        for (int i=1; i<=columnCount; i++) {
                                                String key = md.getColumnName(i);
                                                String value = rs2.getString(i);
                                                //System.err.println("key, value : " +key+", "+value);
                                                if(key != null && value != null){
                                                        props.setProperty(md.getColumnName(i),rs2.getString(i));//No I18N
                                                }
                                        }
                                }
                                System.err.println("props are :"+props);
                                String slave_io = props.getProperty("Slave_IO_Running");
                                String slave_sql = props.getProperty("Slave_SQL_Running");
				if(!slave_sql.equalsIgnoreCase("yes") || !slave_io.equalsIgnoreCase("yes")) {
					String message = "Replication might be broken"; //No I18N
					ByteArrayOutputStream byteStream=new ByteArrayOutputStream();
					DataOutputStream outp=new DataOutputStream(byteStream);
					outp.writeInt(97); // this int is to indicate that it is a broadcast info.
					outp.writeUTF(message);
					outp.writeUTF("ALLCLIENTS"); //No I18N
					outp.flush();
					PureServerUtilsBE.specialSessionBE.broadcastToAllClients("BROADCAST_FROM_CLIENT","0",byteStream.toByteArray()); //No I18N
				}
                        }
                }
                catch(Exception e3)
                {
                        System.err.println("Exception in getting slave status for STANDBY DB");//No I18N
                        e3.printStackTrace();
                }
                finally{
                        if(rs2!=null)
                        {
                                try{rs2.close();}catch(Exception e){}
                        }
                        if(stmt2!=null)
                        {
                                try{stmt2.close();}catch(Exception e){}
                        }
                        if(conn!=null)
                        {
                                try{conn.close();}catch(Exception e){}
                        }
                }
        }
}
