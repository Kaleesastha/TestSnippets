//$id:$
//$Date: 2003/06/04 06:35:04 $
//$Author: asivakumar $


/* This is a test servlet */
package com.adventnet.management.transaction;
//package test;
import java.sql.*;
import com.adventnet.management.transaction.ConnectionPool;
import com.adventnet.management.transaction.TransactionAPI;
import com.adventnet.nms.store.relational.RelationalAPI;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
//import com.adventnet.management.transaction;
//import com.adventnet.management.transaction;
import com.adventnet.management.transaction.TransactionAPI;

import com.adventnet.management.transaction.PreparedStatementWrapper;
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
import com.adventnet.snmp.mibs.MibOperations;
import com.adventnet.nms.topodb.*;
import com.adventnet.nms.store.*;
import com.adventnet.nms.store.relational.*;
import com.adventnet.nms.util.NmsLogMgr;
import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.util.PureServerUtils;

import com.adventnet.management.authorization.*;

public class SF_API_TBA extends HttpServlet
{
    CommonDBStoreExtn cds = null;
    ManagedObject mobj = null;
    ResultSet rs =null;
    PreparedStatementWrapper psw=null;
    Connection con1=null,con2=null;
    PreparedStatement ps=null;
    RelationalAPI relapi = null;
    PrintWriter out=null;
    HttpServletRequest req = null;
    Connection conn = null;
    ConnectionPool cp = null;


    public void doPost(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException 
    {
        doGet(req, res);
    }
    
    public synchronized void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        /* create table Oracletable(

         */
        out=res.getWriter();
        out.println(new Date());
        //SF_API_NmsUtil();

        //SF_API_NmsPureUtilsFE();
        //SF_API_83();
        // SF_API_83_1();
        //SF_API_134();
        //out.println("SF-API-147 result is "+ NmsUtil.isSubQuerySupported());
        //SF_API_169();
        //SF_API_178_1();
        //SF_API_181_1();
        //SF_API_182_2();  //This may take a few minutes to execute
        //SF_API_189A();  //Failed cases
        //SF_API_198();   //Method getStatus unknown
        //SF_API_201();
        //SF_API_202();
        //SF_API_203();
        // out.println("SF-API-294 TESTCASE IS : The boolean value is "+ NmsUtil.checkString(null,"N",true));

        //SF_API_ISNUMERIC_for_mysql();  //This method will work with MYsql database
        //SF_API_GetDataType_for_mysql(); //This method will work with MYsql database
        //SF_API_ISNUMERIC_for_oracle();   //This method will work with oracle database
        //SF_API_GetDataType_for_oracle();    //This method will work with oracle database
        forOracleTestcase_323_324_325();  //This case 325 will work only with oracle database
    }


    public void SF_API_ISNUMERIC_for_mysql()
    {
        
        out.println("This line add ed nw");
        out.println("SF_API_301 :is the int numeric type      "+RelationalUtil.isNumericField("fordatatype","INTDATA"));
        out.println("SF_API_302 :is the integer numeric type  "+RelationalUtil.isNumericField("fordatatype","integerdata"));
        out.println("SF_API_303 :is the double numeric type   "+RelationalUtil.isNumericField("fordatatype","doubledata"));
        out.println("SF_API_304 :is the float numeric type    "+RelationalUtil.isNumericField("fordatatype","floatdata"));
        out.println("SF_API_306 :is the numeric numeric type  "+RelationalUtil.isNumericField("fordatatype","numericdata"));
        out.println("SF_API_307 :is the decimal numeric type  "+RelationalUtil.isNumericField("fordatatype","decimaldata"));
           
        out.println("SF_API_309 :is the smallint numeric type "+RelationalUtil.isNumericField("fordatatype","smallintd"));
        out.println("SF_API_310 :is the bigint numeric type   "+RelationalUtil.isNumericField("fordatatype","bigintd"));   
       
    }

public void SF_API_GetDataType_for_mysql()
    {
        out.println("Its new message AGAIN");

        out.println("SF_API_311  :is the integer datatype  type "+RelationalUtil.getDataType("TopoDB","INTDATA"));
        out.println("SF_API_312  :is the integer datatype  type "+RelationalUtil.getDataType("TopoDB","INTEGERDATA"));
        out.println("SF_API_313  :is the double datatype type   "+RelationalUtil.getDataType("TopoDB","DOUBLEDATA"));
        out.println("SF_API_314  :is the float datatype  type   "+RelationalUtil.getDataType("TopoDB","FLOATDATA"));
        out.println("SF_API_316  :is the numeric datatype type  "+RelationalUtil.getDataType("TopoDB","NUMERICDATA"));
        out.println("SF_API_317  :is the decimal datatype type  "+RelationalUtil.getDataType("TopoDB","DECIMALDATA"));
         
        out.println("SF_API_319  :is the smallint datatype type "+RelationalUtil.getDataType("TopoDB","SMALLINTD"));
        out.println("SF_API_320  :is the bigint datatype  type  "+RelationalUtil.getDataType("TopoDB","BIGINTD"));
        out.println("SF_API_321  :Invalid module name type      "+RelationalUtil.getDataType("TopoDB123","STATUS"));
        out.println("SF_API_322  :Invalid column name type      "+RelationalUtil.getDataType("TopoDB","NAME1234"));

        out.println("ManagedObject variable :  datatype type is "+RelationalUtil.getDataType("TopoDB","STATUS"));
        out.println("ManagedObject variable :  datatype type is "+RelationalUtil.getDataType("TopoDB","NAME"));


        //out.println("is the STRING datatype type "+RelationalUtil.getDataType("TopoDB","NAME"));
        

    }

public void  SF_API_ISNUMERIC_for_oracle()
    {
        out.println("This testcase SF_API_308 will produce the expected result only with oracle database.    ");
    
        out.println("SF_API_305 :is the number a NUMBER type "+RelationalUtil.isNumericField("Alert","MODTIME"));     
        out.println("SF_API_308 :is the number a LONG type "+RelationalUtil.isNumericField("fordatatype","longdata")); 

    }


    public void SF_API_GetDataType_for_oracle()
    {

        out.println("SF_API_315  :is it a number type "+RelationalUtil.getDataType("TopoDB","NUMBERDATA"));
        out.println("SF_API_318  :is it a long type   "+RelationalUtil.getDataType("TopoDB","LONGDATA"));
    }

    public void forOracleTestcase_323_324_325()
    {
        MetaDataInfo md2 = RelationalUtil.getMetaData("ManagedObject");
        out.println("SF_API_323  :This metadata contains the table :"+md2.getTableName());
        MetaDataInfo md1 = RelationalUtil.getMetaData("UserObject123");
        out.println("SF_API_325  :This invalid metadata table will return null :"+md1);
        MetaDataInfo md3 = RelationalUtil.getMetaData("UserObject");
        out.println("SF_API_325  :This metadata contains the table :"+md3.getTableName());
        
    }


    public void   SF_API_NmsUtil()
    {
        out.println("SF-API-08   :The vector available under in server is "+NmsUtil.current_keys);
        // out.println("SF-API-37   :The  Relative logs of this server is "+NmsUtil.        
        out.println("SF-API-47   :Is the NMS server is running :"+NmsUtil.isNMSRunning());
        //NmsUtil.readServerparams();
        out.println("SF-API-50A :NMS_BE_PORT for this server is "+ NmsUtil.getParameter("NMS_BE_PORT"));
        NmsUtil.setConfirmAuth(new DummyPermission1());
        out.println("SF-API-56 :"+NmsUtil.getConfirmAuth());
        out.println("SF-API-60B My Server running type is  :"+NmsUtil.getServerType());
    }
    
    public void   SF_API_NmsPureUtilsFE()
    {
        
        out.println("SF-API-65 :The be_host for StandAlone FE is "+PureServerUtilsFE.be_host);
        out.println("SF-API-66 :The port no of BE is "+PureServerUtilsFE.be_port);
        out.println("SF-API-67 :The registry port  of BE is "+PureServerUtilsFE.be_registry_port);
    }

public void  SF_API_83()
    {
        PureServerUtils.backUpInProcess = false;
        MYThread th82=new MYThread();
        out.println("first thread yet to start");
        th82.start();
        try{
            Thread.sleep(10000);
       }
        catch(Exception ee){out.println("The exception is "+ee);}
        out.println("Before backupinprocess variable true   : "+PureServerUtils.backUpInProcess);
        PureServerUtils.backUpInProcess = true;
        out.println("first thread sleep over "+PureServerUtils.backUpInProcess);

    }
    public class MYThread extends Thread
    {
        public void run()
            {
                out.println("Inside 2nd Independent thread method");
                out.println(PureServerUtils.backUpInProcess());
                out.println("Back execution over");
            }
    }

    public void  SF_API_83_1()
    {
        
        try{
            PureServerUtils.backUpInProcess = false;
            relapi = NmsUtil.relapi;
            conn =relapi.getConnection();
            //state = conn.createStatement();
            cds = new CommonDBStoreExtn(150,true);
            
            String name = "backupinprocess";         
            mobj= new ManagedObject(); 
            mobj.setName(name);
            cds.addObject(mobj);
            out.println(" The mangedObject added ");
            ManagedObject backup1 =(ManagedObject)cds.getObject(name);
            String nametest = backup1.getName();
        
            String sql ="select NAME  from ManagedObject where NAME='backupinprocess'";
        
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs001 =relapi.executeQuery(ps);
            String result = null;
            while(rs001.next())
                {
                    result =rs001.getString(1);
                }    
            out.println("The value Obtained"+result); 
   
            if(name.equals(nametest)&& name.equals(result))
                {
                    out.println("TestCaseNO:SF_API_83_1:PASSED");
                }   
              }
        catch(Exception e)
            {
                out.println("Exception :"+e);
                e.printStackTrace();
            }    

        try{
            cds.deleteObject(mobj);
            out.println("The object has been deleted");
        }
            catch(Exception ee)
            {
                out.println("Exception :"+ee);
                ee.printStackTrace();
            }    
    }





public void SF_API_134()
    {
        try{

            InputStream ins=new FileInputStream("C:/iWeb nms401/AdventNet/WebNMS/html/EnglishToNative.properties");
            out.println("Is the file is able to read"+ins.available());
        
             
            NmsUtil.initializeResourceBundle(ins);
            out.println("The key is "+ NmsUtil.GetString("Product"));
        }
        catch(Exception ee)
            {
                out.println("The exception is "+ee);
                ee.printStackTrace();
            }   
    }




public void  SF_API_169()
    {
        NmsUtil.mibOps = new MibOperations() ; 
        
        CommonUtilCarrier.getInstance().addMIBModule("AdventNet-WebNMS-MIB");
        out.println("Is the MIB loaded :"+NmsUtil.getMibFiles().contains("AdventNet-WebNMS-MIB"));
        //out.println("The MIB that has been looooaded is "+NmsUtil.mibOps.getMibModule("AdventNet-WebNMS-MIB"));

    }


public void SF_API_178_1()
    {
  int i=0;
        out.println("This is tast case sf-api-178.1");
        relapi = NmsUtil.relapi;
        
        String sqlString = "insert into new values (1, 'ad')";
        out.println("before while loop");
        while(i<1005)
            {
                try{
                    out.print(i +"  ");
                    relapi.execute(sqlString); 
                    
                }catch(Exception ee)
                    {}
                i++;
            }
    }


    public void SF_API_181_1()
    {

        int i=0;
        out.println("This is tast case sf-api-181.1");
        relapi = NmsUtil.relapi;
        String sqlString = "insert into JunkTable  values ( ? , ?)";
        try{        
            PreparedStatement ps = relapi.getPreparedStatement(sqlString);
            ps.setString(1,"abcd");
            ps.setString(2,"adventNet");
        }
        catch(Exception ee)
            {
                out.println("There is an exception "+ee);
                ee.printStackTrace();
            }
        out.println("before while loop");
        while(i<1005)
            {
                try{
                    out.print(i +"  ");
                    relapi.executeTheStatement(ps); 
                    
                }catch(Exception ee)
                    {}
                i++;
            }
    }


    public void SF_API_182_2()
    {
        int i=0;
        relapi = NmsUtil.relapi;
        String sqlString = "insert into news values (1,'adventNet')";
        out.println("before while loop");
        while(i<1005)
            {
                try{
                    out.print(i +"  ");
                    relapi.executeTheStatement(sqlString); 
                    
                }catch(Exception ee)
                    {}
                i++;
            }
    }




    public void SF_API_189A()
    { 
         // Before using this method, create a table and insert some records in the database using dbquery tool
        //create table new(id int , name varchar(20))
            //insert into new values(1,'sivakumar')

        try{
            relapi = NmsUtil.relapi;
        String sqlString1 = "select name from new where id = 2";
        String sqlString2 = "select name from new where id = 3";
        String sqlString3 = "select name from new where id = 2";
        //String delimiter = ":";
        out.println("Very first");
        String combine = sqlString1+":"+sqlString2+":"+sqlString3;
        out.println("Before resultset");
        try{
        ResultSet[] rss=relapi.query(combine,":");
        out.println("before while one "+rss[0].toString());
        
        while(rss[0].next())
            {
                out.println("+++++dagsdga");
                out.println("+++++++" + rss[0].getString(1));
            }
        out.println("Before while two");
        while(rss[1].next())
            {
                out.println("+++++++asdgaf" + rss[1].getString(1));
            }
        while(rss[2].next())
            {
                out.println("++++++reryetr" + rss[2].getString(1));
                
            }
        }
        catch(Exception e)
            {
                out.println("The exception iAAAAAszzzzzzsssssssssss "+e);
                e.printStackTrace();
            }  
        
        }
        catch(Exception ee)
            {
                out.println("The exception is "+ee);
                System.err.println("Exception"+ee);
                ee.printStackTrace();
            }
    }


    public void SF_API_198()
    { 
        String name =null;
        try{
        // Before using this method, create a table and insert some records in the database using dbquery tool
        //create table new(id int , name varchar(20))
            //insert into new values(1,'sivakumar')
        relapi = NmsUtil.relapi;
        String sqlString = "select name from new where id = ?";
        int id = relapi.getPreparedStatementID(sqlString); 
        psw = relapi.fetchPreparedStatement(id);

        
        PreparedStatement prstmt = psw.getPreparedStatement();
        try{        
            prstmt.setInt(1,1);
        }
        catch(Exception ee){out.println("Theere is an exception here "); }
        String sql = psw.getSqlString();
        out.println("The sql string is  :::::::: "+sql);
        rs = relapi.executeQuery(prstmt); 
        out.println("before while loop");
        while(rs.next())
            {
                out.println("Before executing::::::::::");
                name = rs.getString(1);
            }
        out.println("The sql string is!!!!!!!!! "+name);
        try{        
            relapi.returnPreparedStatement(psw);
            //out.println("The current status of the psw is "+psw.getStatus());
        }catch(Exception ee)
            {
                out.println("The err is "+ee);
            }
        
        }
        catch(Exception ie)
            {
                out.println("The exception is :::::"+ie);
                ie.printStackTrace();
            }




    }
    public void SF_API_201()
    {
        relapi = NmsUtil.relapi;
        TransactionAPI tapi = relapi.getTransactionAPI();
        try { 
            tapi.begin();}catch(Exception e) {} 
        try { 
            //some queries 
            boolean trans = relapi.isInTransaction(); 
            out.println("Is the object is ready for transaction :"+trans);

            tapi.commit(); 
        } 
        catch(Exception e) 
            { 
                try{
                    tapi.rollback();
                }catch(Exception ee){}    
            } 
    }





    public void SF_API_202()
    {
        relapi = NmsUtil.relapi;
        TransactionAPI tapi = relapi.getTransactionAPI();
        String sqlString=null;
        try{
            tapi.begin();
        }catch(Exception e) {}
        try{
            sqlString = "insert into new  values ( ? , ?)";
            ps = relapi.getPreparedStatement(sqlString,true);
            con1 = ps.getConnection();
            tapi.commit();
        }
        catch(Exception e)
            {
                try{
                tapi.rollback();
                }catch(Exception ee){}
            }
        try{
        PreparedStatement ps1 = relapi.getPreparedStatement(sqlString);
        con2 = ps1.getConnection();
        out.println(con2);
        out.println(con1);
        if(con1 ==con2)
            out.println("The two connections are equal");
        else 
            out.println("The two connections are not equal");
        }
        catch(Exception ee)
            {
                out.println("xception is "+ee);
            }
    }
    public void SF_API_203()
    {
        try{
            relapi = NmsUtil.relapi;
            out.println("Before calling disconnect method");
            relapi.disconnect();
            out.println("After calling disconnect method!!!!!!!");
        }
        catch(Exception ee)
            {
                out.println("The exception is "+ee);
            }
        
    }
}



