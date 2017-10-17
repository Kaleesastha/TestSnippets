/* This is a test servlet */
package WEB;
import java.sql.*;
import com.adventnet.management.transaction.ConnectionPool;
import com.adventnet.management.transaction.TransactionAPI;
import com.adventnet.nms.store.relational.RelationalAPI;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import com.adventnet.management.log.SystemUtil;
import com.adventnet.nms.util.WatchUtil;
import com.adventnet.nms.store.relational.RelationalUtil;
import com.adventnet.management.transport.TransportUtil;
import java.io.*;
import java.net.*;
import java.util.Properties;
import java.util.Vector;
import java.util.Date;
import java.util.Hashtable;
import com.adventnet.nms.util.*;
import com.adventnet.management.log.Log;
import com.adventnet.nms.startnms.NmsCorbaMain;
import com.adventnet.snmp.beans.SnmpTarget;

public class TestServlet extends HttpServlet
{
    HttpServletRequest req = null;
    public void doPost(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException 
    {
        doGet(req, res);
    }
    
    public synchronized void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
            
                SnmpTarget target = PureServerUtils.getSnmpTarget();
                WatchUtil wu = new WatchUtil();
                //File dbppfile = new File("d:/beta/AdventNet/WebNMS/conf/database_params.conf");
                File dbppfile = new File("c:/WebNMS/conf/database_params.conf");
		System.out.println( "#####################");
		System.out.println( "Loaded Mibs = " + target.getMibModules() );
                System.out.println( "############DB PARAMSPARSER TESTING#############");
                DBParamsParser dbpp = new DBParamsParser();
                dbpp=DBParamsParser.getInstance(dbppfile);
                if(dbpp!=null)
                {
                    System.out.println("not null");
                }
                System.out.println("URL is "+ dbpp.getURL());
                System.out.println("password is "+ dbpp.getPassword());
                System.out.println("driver name is "+ dbpp.getDriverName());
                System.out.println("user name is "+ dbpp.getUserName());
                System.out.println("no. of transaction connections is "+ dbpp.getNumberOfTransactionalConnections());
                System.out.println("no. of non transaction connections is "+ dbpp.getNumberOfNonTransactionalConnections());
                Hashtable urlhash = dbpp.getURLTable();
                System.out.println("URL table is "+ urlhash);
                Hashtable passwordhash = dbpp.getPasswordTable();
                System.out.println("password table is "+ passwordhash);
                Hashtable usernamehash = dbpp.getUserNameTable();
                System.out.println("username table is "+ usernamehash);
                Hashtable drivernamehash = dbpp.getDriverNameTable();
                System.out.println("drivername table is "+ drivernamehash);
                System.out.println("********** NmsUtil ***********");
                System.out.println("aimroot is " + NmsUtil.AIM_ROOT);
		System.out.println("country is " + NmsUtil.country);
		System.out.println("current_keys is " + NmsUtil.current_keys);
		System.out.println("initial mibOps is " + NmsUtil.mibOps);
		NmsUtil.addMIBModule("Printer-MIB");
		System.out.println("mibops after loading mibmodule is " + NmsUtil.mibOps);
                System.out.println("checkonestring returns " + NmsUtil.checkOneString("srik*","srikanth"));
		System.out.println("checkstring returns " + NmsUtil.checkString("srikanth","sri*th"));
		System.out.println("datestring is " + NmsUtil.dateString(5857235));
		System.out.println("datestring is " + NmsUtil.dateString("967705857235"));
                System.out.println("deletefile returned " + NmsUtil.deleteFile(".","NETMREG.DATA"));
		Properties p = new Properties();
		p.put("name","srikanth");
		byte[] barray = NmsUtil.serializeProperties(p);
		System.out.println("deserialized Properties output is " + NmsUtil.deSerializeProperties(barray));
		
		Vector v = new Vector();
		v.addElement("Raji");
		barray = NmsUtil.serializeVector(v);
		System.out.println("deserialized Vector output is " + NmsUtil.deSerializeVector(barray));
		
		String[] sarr = new String[2];
		sarr[0] = "raji";
		sarr[1] = "vidhya";
		barray = NmsUtil.serializeStringArray(sarr);
		System.out.println("deserialized String Array output is " + NmsUtil.deSerializeStringArray(barray));
		//SystemUtil.cout.println("This message will be printed on the console");
		System.out.println("alertapi is " + NmsUtil.getAlertAPI());
		System.out.println( "topoapi is " + NmsUtil.getTopoAPI());
		System.out.println( "eventapi is " + NmsUtil.getEventAPI());
		System.out.println( "mapapi is " + NmsUtil.getMapAPI());
		System.out.println( "pollapi is " + NmsUtil.getPollAPI());
		NmsLogMgr.MISCUSER.log( "#######alertapi is " + NmsUtil.getAlertAPI(), Log.SUMMARY);
		System.out.println("getconfirmauth is " + NmsUtil.getConfirmAuth());
                System.out.println("getboolean prop " + NmsUtil.getBooleanProp("true"));
		System.out.println(" ");
                System.out.println(" ############### WatchUtil testing ########");
                System.out.println("SF-API-204 result is " + wu.getDefaultNetMask("123.23.3.0"));
                System.out.println("SF-API-205 result is " + wu.getDefaultNetMask("190.23.3.0"));
                System.out.println("SF-API-206 result is " + wu.getDefaultNetMask("198.23.3.0"));
                //System.out.println("SF-API-207 result is " + wu.getDefaultNetMask("265.23.3.0"));
                //System.out.println("SF-API-208 result is " + wu.getDefaultNetMask("123.23w.3.0"));
                //System.out.println("SF-API-209 result is " + wu.getDefaultNetMask("123,23,3.0"));                   
                //System.out.println("SF-API-210 result is " + wu.getDefaultNetMask("0.0.0.0"));
                System.out.println("SF-API-211 result is " + wu.getDefaultNetMask(null));
                System.out.println("SF-API-212 result is " + wu.getNumIPs("127.4.5.0","255.0.0.0"));
                System.out.println("SF-API-213 result is " + wu.getNumIPs("127.4.5.0","255.255.0.0"));
                System.out.println("SF-API-214 result is " + wu.getNumIPs("127.4.5.0","255.255.255.0"));
                System.out.println("SF-API-215 result is " + wu.getNumIPs("191.4.5.0","255.0.0.0"));
                System.out.println("SF-API-216 result is " + wu.getNumIPs("191.4.5.0","255.255.0.0"));
                System.out.println("SF-API-217 result is " + wu.getNumIPs("191.4.5.0","255.255.255.0"));
                System.out.println("SF-API-218 result is " + wu.getNumIPs("194.4.5.0","255.0.0.0"));
                System.out.println("SF-API-219 result is " + wu.getNumIPs("194.4.5.0","255.255.0.0"));
                System.out.println("SF-API-220 result is " + wu.getNumIPs("194.4.5.0","255.255.255.0"));
                System.out.println("SF-API-221 result is " + wu.getNumIPs("192.168.4.0","255.255.0.255"));
                System.out.println("SF-API-222 result is " + wu.getNumIPs("192.168.4.0","255.0.0.255"));
                System.out.println("SF-API-223 result is " + wu.getNumIPs("194.4.5.0","0.0.0.255"));
                System.out.println("SF-API-224 result is " + wu.getNumIPs("192.168.4.0","2552552550"));
                System.out.println("SF-API-225 result is " + wu.getNumIPs("194.168.4.0","255r.255y.255u.0"));
                System.out.println("SF-API-226 result is " + wu.getNumIPs("192.168.5.0","255.255.265.0"));
                System.out.println("SF-API-227 result is " + wu.getNumIPs("334.45.56.0","255.255.255.0"));
                System.out.println("SF-API-228 result is " + wu.getNumIPs("192e.168,5.0","255.255.255.0"));
                System.out.println("SF-API-229 result is " + wu.getNumIPs("192.45.445.0","255.255.255.0"));
                System.out.println("SF-API-230 result is " + wu.getNumIPs("192.168.4.0",null));
                System.out.println("SF-API-231 result is " + wu.getNumIPs(null,"255.255.255.0"));
                System.out.println("SF-API-232 result is " + wu.getNumIPs(null,null));
                System.out.println("SF-API-239 result is " + wu.getIPList("192.168.4.0","255.255.255.255"));
                System.out.println("SF-API-240 result is " + wu.getIPList("192.168.4.0","255e.255.255.0"));
                System.out.println("SF-API-241 result is " + wu.getIPList("323.45.67.77","255.255.255.0"));
                System.out.println("SF-API-242 result is " + wu.getIPList("192.168e.4,77","255.255.255.0"));
                System.out.println("SF-API-243 result is " + wu.getIPList(null,"255.255.255.0"));
                System.out.println("SF-API-244 result is " + wu.getIPList("192.168.4.0",null));
                System.out.println("SF-API-245 result is " + wu.getIPList("192.168.4.265","255.256.255.0"));
                System.out.println("SF-API-246 result is " + wu.getIPList(null,null));
                System.out.println("SF-API-247 result is " + wu.getIPList("0","0"));
                System.out.println("SF-API-248 result is " + wu.getNetAddr("192.168.4.45","255.255.255.0"));
                System.out.println("SF-API-249 result is " + wu.getNetAddr("192.168.4.45","255.255.0.0"));
                System.out.println("SF-API-250 result is " + wu.getNetAddr("192.168.4.45","255.0.0.0"));
                System.out.println("SF-API-251 result is " + wu.getNetAddr("192.168.4.373","255.255.255.0"));
                System.out.println("SF-API-252 result is " + wu.getNetAddr("192.168,4,45","255.255.255.0"));
                System.out.println("SF-API-253 result is " + wu.getNetAddr("192t.168y.4.45","255.255.255.0"));
                System.out.println("SF-API-254 result is " + wu.getNetAddr("192.168.4.45","255.255.265.255"));
                System.out.println("SF-API-255 result is " + wu.getNetAddr("192.168.4.45","255.265.255.255"));
                System.out.println("SF-API-256 result is " + wu.getNetAddr(null,null));
                System.out.println("SF-API-257 result is " + wu.getNetAddr("192.168.4.45",null));
                System.out.println("SF-API-258 result is " + wu.getNetAddr(null,"255.255.255.0"));
                System.out.println("SF-API-264 result is " + wu.getDNSName("192.168.4.45"));
                System.out.println("SF-API-265 result is " + wu.getDNSName("192.4t.25y.0"));
                System.out.println("SF-API-266 result is " + wu.getIP("rprabhu"));
                System.out.println("SF-API-267 result is " + wu.getIP("filby"));
                System.out.println("SF-API-268 result is " + wu.inNet("192.168.4.45","192.168.4.0","255.255.255.0"));
                System.out.println("SF-API-269 result is " + wu.inNet("192.256.4.45","192.168.4t.0","255.255.255.255"));
                Vector v1= new Vector();
                v1.addElement("192.168.4.43");
                v1.addElement("192.168.4.44");
                v1.addElement("192.168.4.45");
                v1.addElement("192.168.4.46");
                Vector v2= new Vector();
                v2.addElement("192.345.264.3");
                v2.addElement("192e.168.4.45");
                Vector v3= new Vector();
                v3.addElement("192.168.4.45");
                v3.addElement("192.168.4.45");
                v3.addElement("192.168.4.45");
                Vector v4= new Vector();
                v4.addElement("192.168.4.41");
                v4.addElement("192.168.4.42");
                v4.addElement("192.168.4.43");
                v4.addElement("192.168.4.44");
                v4.addElement("192.168.4.45");
                Vector v5= new Vector();
                v5.addElement("192.168.4.61");
                v5.addElement("192.168.4.62");
                v5.addElement("192.168.4.63");
                v5.addElement("192.168.4.64");
                v5.addElement("192.168.4.65");
                Vector v6= new Vector();
                v6.addElement("192.168.4.76");
                Vector v7=new Vector();
                v7.addElement("192.168.4.86");
                System.out.println("SF-API-270 result is " + wu.getMinMaxAddr(v1,true));
                System.out.println("SF-API-271 result is " + wu.getMinMaxAddr(v1,false));
                System.out.println("SF-API-272 result is " + wu.getMinMaxAddr(v2,true));
                System.out.println("SF-API-273 result is " + wu.getMinMaxAddr(v2,false));
                System.out.println("SF-API-273A result is " + wu.getMinMaxAddr(v3,true));
                System.out.println("SF-API-273B result is " + wu.getMinMaxAddr(v3,false));
                System.out.println("SF-API-274 result is " + wu.isAddressInRange("192.168.4.45",v4,v5));
                System.out.println("SF-API-275 result is " + wu.isAddressInRange("192.168.4.55",v4,v5));
                System.out.println("SF-API-275A result is " + wu.isAddressInRange("192.168.4.76",v6,v7));
                wu.setDNSEnabled(true);
                System.out.println("SF-API-276 result is " + wu.isDNSEnabled());

                System.out.println(" ************* NMS UTIL TESTING **********");
                NmsUtil.init();
                System.out.println("SF-API-147A result is true");
                System.out.println("SF-API-129 result is "+ NmsUtil.language);
                System.out.println("SF-API-130 result is "+ NmsUtil.userName);
                System.out.println("SF-API-131 result is "+ NmsUtil.mibFiles);
                System.out.println("SF-API-132 result is "+ NmsUtil.relapi);
                System.out.println("SF-API-133 result is "+ NmsUtil.getAIM_ROOT());
                System.out.println("SF-API-136 result is "+ NmsUtil.getAPI(null));
                System.out.println("SF-API-134 result is "+ NmsUtil.mibFiles);
                System.out.println("SF-API-147 result is "+ NmsUtil.isSubQuerySupported());

                System.out.println("############## NMS FILE TUIL TESTING ##############");
                System.out.println("SF-API-026 result is "+NmsUtil.getImageType());
                NmsUtil.setStatusPollPingRetries(10);
                NmsUtil.setStatusPollSnmpRetries(20);
                System.out.println("SF-API-039 result is "+NmsUtil.getStatusPollPingRetries());
                System.out.println("SF-API-040 result is "+NmsUtil.getStatusPollSnmpRetries());
                System.out.println("SF-API-046 result is "+NmsUtil.isFreshStart());
                System.out.println("SF-API-047 result is "+NmsUtil.isNMSRunning());
                System.out.println("SF-API-048 result is "+NmsUtil.loadInterfacesList());
                NmsUtil.readServerParams();
                System.out.println("SF-API-050A crosscheck result is "+NmsUtil.getParameter("NMS_BE_PORT"));
                System.out.println("#############PureServerUtils Testing ###############");
                PureServerUtils.getDatabaseParams();
                System.out.println("SF-API-071A result is "+PureServerUtils.transactionalConnections);
                System.out.println("SF-API-071B result is "+PureServerUtils.nonTransactionalConnections);
                PureServerUtils.loadMibs();
                File f1=new File("Tools.config");
                File f2= new File("logs");
                File f3=new File("servers.config");
                File f4 = new File("builder.config");
                //File f5 = new File ("filby123.txt");
                File f6=new File ("apache/conf/httpd.conf");
                File f7 = new File("BuildInfo.txt");
                File f8 = new File("test.txt");
                PureServerUtils.backupFile(f1,f2);
                PureServerUtils.copyFile("setEnv.bat","fil.bat");
                PureServerUtils.copyFile(f7,f8);
                //PureServerUtils.deleteFile(new File("filtest.txt"));
                System.out.println("SF-API-078 result is "+PureServerUtils.isEveryThingOk(f3));
                System.out.println("SF-API-077 result is "+PureServerUtils.isEveryThingOk(f4));
                //System.out.println("SF-API-079 result is "+PureServerUtils.isEveryThingOk(f5));
                System.out.println("SF-API-081 result is "+PureServerUtils.getValue(f6,"PORT"));
                PureServerUtils.executeNetstatCommand();
                try
                {
                    System.out.println("SF-API-084 result is "+PureServerUtils.getRMIURL("TopoAPI"));
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
                System.out.println(" ******************* RELATIONAL TUIL TESTING *************");
                System.out.println("SF-API-088 result is "+ RelationalUtil.getCriterion("name","one,two",false,false));
                System.out.println("SF-API-089 result is "+ RelationalUtil.getCriterion("no","10,20",true,true));
                System.out.println("SF-API-090 result is "+ RelationalUtil.getCriterion("no","10,20",true,false));
                System.out.println("SF-API-091 result is "+ RelationalUtil.getCriterion("no","<between 40 and 50",true,false));
                System.out.println("SF-API-092 result is "+ RelationalUtil.getCriterion("no","<between 40 and 50",true,true));
                System.out.println("SF-API-093 result is "+ RelationalUtil.convertToString(40l));
                System.out.println("SF-API-094 result is "+ RelationalUtil.convertStatusPropToInt("Critical"));
                System.out.println("SF-API-095 result is "+ RelationalUtil.convertStatusPropToInt(null));
                System.out.println("SF-API-096 result is "+ RelationalUtil.convertStatusPropToInt("filby123"));
                System.out.println("SF-API-097 result is "+ RelationalUtil.convertStatusPropToInt(""));
                Vector vec1 = new Vector();
                vec1.addElement("asdfg");
                vec1.addElement("yuifg");
                vec1.addElement("fgfg");
                Vector vec2 = new Vector();
                vec2.addElement("asdfg");
                vec2.addElement("dfsdfg");
                vec2.addElement("pldfg");
                Vector vec3 = new Vector();
                System.out.println("SF-API-098 result is "+ RelationalUtil.getIntersectionOfTwoVectors(vec1,vec2));
                System.out.println("SF-API-099 result is "+ RelationalUtil.getIntersectionOfTwoVectors(vec1,vec3));
                System.out.println("SF-API-100 result is "+ RelationalUtil.getUnionOfTwoVectors(vec1,vec2));
                System.out.println("SF-API-101 result is "+ RelationalUtil.getClassnamesBelongingToModule("topodb"));
                System.out.println("SF-API-102 result is "+ RelationalUtil.getRelationalObjectNameForClass("com.adventnet.nms.topodb.ManagedObject"));
                System.out.println("SF-API-103 result is "+ RelationalUtil.getTableNameForClass("topodb"));
                System.out.println("SF-API-105 result is "+ RelationalUtil.getColumnNamesOfTable("IpAddress"));
                System.out.println("SF-API-106 result is "+ RelationalUtil.getNumericColumnsOfTable("IpAddress"));
                System.out.println("SF-API-107 result is "+ RelationalUtil.getPrimaryKeysOfTable("IpAddress"));
                System.out.println("SF-API-108 result is "+ RelationalUtil.getAlias("sysName"));
                System.out.println("SF-API-109 result is "+ RelationalUtil.getAntiAlias("SYSNAME"));
                System.out.println("*********** TRANSPORT UTIL TESTING ***********");
                TransportUtil.writePortToHtml(1234,"logs","filtest.html"); 
                TransportUtil.writePortToHtml(1234,"logs","freakytest.html"); 
                try
                {
                System.out.println("SF-API-126 result is " +TransportUtil.readPort(new URL("http://192.168.4.45:9090/html/NMSSocketPort.html"),"NMSSocketPort.html")); 
                //System.out.println("SF-API-127 result is "+TransportUtil.readPort(new URL("http://192.168.4.45:9090/html/NMSInvalidPort.html"),"NMSInvalidPort.html")); 
                //System.out.println("SF-API-128 result is "+ TransportUtil.readPort(new URL("http://192.168.4.45:8989//html/NmsPos.html"),"NMSSocketPort.html"));
                }catch(Exception e)
                {
                    e.printStackTrace();
                }
                System.out.println(" &&&&&&&&&&&& Common Util Testing &&&&&&&&&&");
                CommonUtil cu = new CommonUtil();
                Properties pr = new Properties();
                pr.put("1","1");
                pr.put("2","2");
                byte[] ba = cu.serializeProperties(pr);
                System.out.println("SF-API-149 result is "+cu.deSerializeProperties(ba));
                System.out.println("SF-API-150 result is "+cu.NmsFormatter.toPattern());
                System.out.println("FS-API-151 result is "+cu.dateString(23l));
                System.out.println("FS-API-152 result is "+cu.dateString(0));
                System.out.println("FS-API-153 result is "+cu.dateString(-12));
                System.out.println("FS-API-154 result is "+cu.dateString(""+System.currentTimeMillis()));
                System.out.println("FS-API-155 result is "+cu.dateString(null));
                //System.out.println("FS-API-156 result is "+cu.dateString("12xxx"));

                System.out.println("@@@@@@@@ NMS Common Util Testing @@@@@@@@");
                CommonUtilCarrier.getInstance().setDEBUG(true);
                CommonUtilCarrier.getInstance().getDEBUG();
                CommonUtilCarrier.getInstance().dbg("this is print for the dbg method of common util carrier class");
                CommonUtilCarrier.getInstance().out("this message will be printed with the tag misc in nmsout.txt log file");
                CommonUtilCarrier.getInstance().err("this is an error message print and will be printed in nmserr file");
                CommonUtilCarrier.getInstance().showError("this is an error message redirected to stderr file");
                System.out.println("SF-API-167 result is "+CommonUtilCarrier.getInstance().getMibFiles());
                System.out.println("SF-API-168 result is "+CommonUtilCarrier.getInstance().getMIBDIR());
                NmsUtil.mibOps = CommonUtilCarrier.getInstance().getMibOperations();
                CommonUtilCarrier.getInstance().addMIBModule("AdventNet-WebNMS-MIB");
                System.out.println("SF-API-169 result is " +NmsUtil.getMibFiles().contains("AdventNet-WebNMS-MIB"));
                CommonUtilCarrier.getInstance().showInfo("This is a test message and this will be printed in stdout file");
                
                System.out.println("%%%%%%%%%%%%%%%% RelationalAPI testing %%%%%%%%%%");
                RelationalAPI rapi = NmsUtil.relapi;
                System.out.println("SF-API-173 result is "+rapi.url);
                System.out.println("SF-API-173 result is "+rapi.userName);
                System.out.println("SF-API-175 result is "+rapi.password);
                System.out.println("SF-API-176 result is "+rapi.driverName);
                Connection con1 = rapi.getConnection();
                Connection con2 = rapi.getConnection();
                if(con1!=con2)
                {
                    System.out.println("TRUE");
                }
                String sqlstring1 = "insert into dbtest values(1,'adventnet')";
                String sqlstring2 = "insert into dbtest values('stringinsteadofint',advent')";
                String sqlstring3 = "insert into dbtest values(2,'snmpapi')"; 
                String sqlstring4 = "insert into dbtest values( ? , ?)"; 
                String sqlstring5 = "insert into dbtest values ('stringinsteadofint','topoapi')";
                String sqlstring6 = "select name from dbtest where id=?";
                String sqlstring7 = "select name from dbtest where id=5";
                String delimiter = "+";
                Vector tempvec = new Vector();
                tempvec.addElement(sqlstring6);
                tempvec.addElement(sqlstring7);
                String combine = sqlstring6+delimiter+sqlstring7;
                try
                {
                    PreparedStatement ps = rapi.getPreparedStatement(sqlstring6); 
                    PreparedStatement ps1 = rapi.getPreparedStatement(sqlstring4); 
                    DatabaseMetaData rmd = rapi.getMetaData();
                    Connection con3 = rapi.getConnection();
                    DatabaseMetaData rmd12 = rapi.getMetaData(con3);
                    System.out.println("SF-API-185 result is " +rmd.getDatabaseProductName());
                    System.out.println("SF-API-185A result is " +rmd12.getDatabaseProductName()); 
                    //ps1.setString(2,"stringinsteadofint");
                    ps.setInt(1,1); 
                    //ps.setString(2,"filbert");
                    ResultSet rs= rapi.executeQuery(ps);
                    rs.next();
                    System.out.println("SF-API-192 result is "+rs.getString(1));
                    //System.out.println("SF-API-190 result is "+rapi.executeUpdate(ps));
                    //rapi.execute(sqlstring1);
                    //rapi.execute(sqlstring2);
                    //rapi.executeTheStatement(sqlstring3);
                    //rapi.executeTheStatement(ps);
                    //rapi.executeTheStatement(ps1);
                    //rapi.executeTheStatement(new Vector());
                    //rapi.executeTheStatement(sqlstring5);
                    //Statement st1= rapi.query(combine,delimiter);
                    //ResultSet[] rs = rapi.query(tempvec); 
                    //System.out.println("Size == "+rs.length);
                    //System.out.println("rs 1 "+rs[0].next());
                    //System.out.println("SF-API-189A result is " +rs[0].getString(1)); 
                    //System.out.println("rs2 "+rs[0].next());
                    //rs[1].next();
                    //System.out.println("SF-API-189A result is " +rs[1].getString(1)); 
                    ConnectionPool cp =rapi.getConnectionPool();
                    if(cp!=null)
                    {
                        System.out.println("SF-API-203A result is true");
                    }
                    TransactionAPI tapi = rapi.getTransactionAPI();
                    //rapi.disconnect();
                }
                catch(Exception exp)
                {
                    exp.printStackTrace();
                }
                
                //	System.out.println( "ORB Instance = " + NmsCorbaMain.getInstance().getOrb() );
//		System.out.println( "NamingContext Instance " + NmsCorbaMain.getInstance().getNmsContext() );
		//System.out.println(" ");
	}
}









