<HTML>
<HEAD>
<meta http-equiv="Pragma" content="no-cache">
<TITLE>Reports</TITLE>
<!---------------------This file is Report.jsp------------------------------>
<LINK REL=STYLESHEET TYPE="text/css" HREF="../template/nmshtmlui.css">
<!-- this is the html file that gets called to all types  of reports-->
</HEAD>

<%@page import ="com.adventnet.nms.topodb.*"%>
<%@page import ="com.adventnet.nms.store.relational.*"%>
<%@page import ="com.adventnet.management.*"%>
<%@page import ="com.adventnet.management.scheduler.*"%> 
<%@page import ="java.rmi.*"%>
<%@page import ="java.util.*"%>
<%@page import ="java.io.*"%>
<%@page import ="test.*"%>
<%@page import ="java.sql.*"%>
<%@page import = "com.adventnet.management.transaction.*"%>
<%@page import ="com.adventnet.nms.severity.*"%>
<%@page import ="javax.transaction.RollbackException"%>
<%@page import ="com.adventnet.nms.util.*"%>
<%@page import ="com.adventnet.nms.store.NmsStorageException"%>





<%

class TableModifier
{

    public  TableModifier()
    {
    }
    
    public void createTable(RelationalAPI relapi)
    {
        try
            {
                String sql="create table mytest( id int , name varchar (20))";
                PreparedStatement ps = relapi.getPreparedStatement(sql);
                int n = relapi.executeUpdate(ps);
               System.out.println("Table mytest created :"+ n);
            }
        catch(Exception e)
            {
               System.out.println("Exception in creating table mytest :"+e.getMessage());
                e.printStackTrace();
            }
    }
  
    public void dropTable(RelationalAPI relapi)
    {
        try
            {
                String sql="drop table mytest";
                PreparedStatement ps = relapi.getPreparedStatement(sql);
                int n = relapi.executeUpdate(ps);
                System.out.println("Table mytest dropped :"+ n);
            }
        catch(Exception e)
            {
                System.out.println("Exception in dropping table mytest :"+e.getMessage());
                e.printStackTrace();
            }
    }

}
     RelationalAPI relapi = null;
     PreparedStatementWrapper ps = null;
     ConnectionPool cpp = null; 
    
      String url = null;
      String usrname = null;
    	String paswd = null;
      String driver = null;  
     String db= null;
     TransactionAPI tx=TransactionAPI.getInstance();    

try
{
    relapi=NmsUtil.relapi;
    cpp=relapi.getConnectionPool();
    System.out.println("ConnectionPool Instance :"+cpp);
    url = cpp.url;
    usrname = cpp.userName;
    paswd = cpp.password;
    driver = cpp.driverName;  
}

catch(Exception e)
{
    out.println("Exception in getting Instance of ConnectionPool:"+e.getMessage());
    e.printStackTrace();
}

TableModifier tm = new TableModifier();
try
{
/*
//This is for case DB-CP-127
tm.createTable(relapi);
tx.begin();
PreparedStatement  pp = cpp.getPreparedStatement("insert into new values(?,?)");
pp.setString(1,"1");
pp.setString(2,"Advent");
pp.addBatch();
pp.executeBatch(pp);
tx.commit();
//End of CASE DB-CP-127
*/
//This is for case DB-CP-128
tm.dropTable(relapi);
tm.createTable(relapi);
tx.begin();
PreparedStatement  pp = cpp.getPreparedStatement("insert into mytest values(?,?)");
pp.setString(1,"1");
pp.setString(2,"Advent");
pp.addBatch();
pp.setString(1,"1221");
pp.setString(2,"kernel");
pp.addBatch();
pp.setString(1,"121");
pp.setString(2,"framework");
pp.addBatch();
cpp.executeBatch(pp);
tx.commit();
//End of case DB-CP-128
/*
//This is for case DB-CP-129

tm.dropTable(relapi)
tm.createTable(relapi);
tx.begin();
PreparedStatement  pp = cpp.getPreparedStatement("insert into new values(?,?)");
pp.setString(1,"1");
pp.setString(2,"Advent");
pp.addBatch();
pp.setString(1,"1221");
pp.setString(2,"kernel");
//pp.addBatch();
pp.setString(1,"121");
pp.setString(2,"framework");
pp.addBatch();
pp.executeBatch(pp);
tx.commit();
//End of DB-CP-129

//This is for case DB-CP-130

tm.dropTable(relapi)
tm.createTable(relapi);
tx.begin();
PreparedStatement  pp = cpp.getPreparedStatement("insert into new values(?,?)");
pp.setString(1,"1");
pp.setString(2,"Advent");
pp.addBatch();
pp.setString(1,"1221");
pp.setString(2,"kernel");
pp.addBatch();
pp.setString(1,"121");
pp.setString(2,"framework");
pp.addBatch();
pp.executeBatch(pp);
tx.rollBack();
//End of DB-CP-130

//This is for case DB-CP-131
tm.dropTable(relapi)
tm.createTable(relapi);

PreparedStatement  pp = cpp.getPreparedStatement("insert into new values(?,?)");
pp.setString(1,"1");
pp.setString(2,"Advent");
pp.addBatch();
pp.executeBatch(pp);

//End of CASE DB-CP-131


//This is for case DB-CP-133

tm.dropTable(relapi)
tm.createTable(relapi);

PreparedStatement  pp = cpp.getPreparedStatement("insert into new values(?,?)");
pp.setString(1,"1");
pp.setString(2,"Advent");
pp.addBatch();
pp.setString(1,"148888888888888");
pp.setString(2,"kernel");
pp.addBatch();
pp.executeBatch(pp);

//End of DB-CP-133
*/
}
catch(Exception e)
{
    out.println("Exception thrown "+e.getMessage());
    e.printStackTrace();
}


%>
