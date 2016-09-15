
import com.adventnet.nms.store.relational.*;
import com.adventnet.nms.util.*;
import com.adventnet.management.transaction.*;
import com.adventnet.management.scheduler.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
public class ConnectionPoolTestTwo extends HttpServlet
{
    RelationalAPI relapi= null;
    ConnectionPool cPool=null;
    PrintWriter out=null;
    public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
    {
        doGet(req,res);

    }   
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException
    {
        relapi=NmsUtil.relapi;
        cPool = relapi.getConnectionPool();
        out=res.getWriter();
        DBCP010();
        //DBCP043();
        // DBCP050();
        //DBCP051();
        //DBCP056();
        //DBCP075();//Create the table "new" before test this case
        //DBCP083();
        //DBCP085();
        //DBCP090();
        //DBCP093();
        //DBCP100();//Create the table before executing the testcase
        DBCP106_1();
        DBCP106_2();
        DBCP132();
        //DBCP133();
    }
    public void DBCP010()
    {
        out.println("Test Case DB-CP-010");
        String sqlString ="select * from Node where name=?";
        int id[] = new int[200];
        for(int i=0;i<200;i++) 
        { 
            id[i] = relapi.getPreparedStatementID(sqlString);
        }
        out.println("PASSED- if all ids are of same value");
        for(int i=0;i<200;i++)
        {
            out.println(id[i]);
        }
    }
     
    public void DBCP043()//Change the proper username "kernel3"
    {
        try
        {
            out.println("Test Case DB-CP-043");
            ConnectionPool cPool43 = new ConnectionPool("jdbc:oracle:thin:@kernel-nt:1521:msp1", "kernel3", "kernel","oracle.jdbc.driver.OracleDriver");
            cPool43.createConnection(5, 3);
            int transConnection = cPool43.getNumOfTransactionConnections();
            int nonTransConnection = cPool43.getNumOfNonTransactionConnections();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            out.println("PASSED -if it throws Exception--Check out in stderr.txt");
        }
    }

    public void DBCP050()
    {
        try
        {
            out.println("Test Case DB-CP-050");
            ConnectionPool cPool50 = new ConnectionPool("jdbc:oracle:thin:@kernel-nt:1521:msp1", "kernel3", "kernel3","oracle.jdbc.driver.OracleDriver");
            Scheduler sc = Scheduler.createScheduler("test");
            cPool50.setScheduler(sc);
            String scName=cPool50.getScheduler().getName();
            out.println("Name of the scheduler is "+ scName +"--PASSED if Name is -test-");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            
        }
    }

    public void DBCP051()
    {
        try
        {
            out.println("Test Case DB-CP-051");
            ConnectionPool cPool51 = new ConnectionPool("jdbc:oracle:thin:@kernel-nt:1521:msp1", "kernel3", "kernel3","oracle.jdbc.driver.OracleDriver");
            String scName=cPool51.getScheduler().getName();
            out.println("Name of the scheduler is "+ scName +"--PASSED if Name is -transaction-");
            out.println("Note:: Make sure that call to DBCP050() is commented");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            
        }
    }

    public void DBCP056()
    {
        out.println("Test Case -DB-CP-056 ");
        ConnectionPool cPool56 = new ConnectionPool("jdbc:oracle:thin:@kernel-nt:1521:msp1", "kernel3", "kernel3","oracle.jdbc.driver.OracleDriver,3,3,test.conAuth");
        int transConnection = cPool56.getNumOfTransactionConnections();
        int nonTransConnection = cPool56.getNumOfNonTransactionConnections();
        out.println("Number of Transaction Connection "+ transConnection);
        out.println("Number of Non Transaction Connection "+ nonTransConnection);
        out.println("Transaction Enabled :"+ cPool56.isTransactionEnabled());
    }

    public void DBCP075()//Create the table new before test this case-
                         //???NotWorking
    {
        out.println("Test Case -DB-CP-075 ");
        try
        {
            Statement state75 = cPool.query("select Name from new where id = 3", false);
            ResultSet rs75 = state75.getResultSet();
            if (rs75.next())
            {
                String name = rs75.getString(1);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            out.println("PASSED --if NmsStorageException occurs---check out in stderr.txt");
        }
    }
    public void DBCP083()
    {
        try
        {
            out.println("Test Case - DB-CP-083");
            String sqlString = "select name from new where idd = ?";
            PreparedStatement ps = cPool.getPreparedStatement(sqlString);
            ps.setInt(1,1);
            cPool.executeQuery(ps);
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
            out.println("PASSED - if NmsStorageExcepiton occurs- check out in stderr.txt");
        }
    }

    public void DBCP085()
    {
        out.println("Test Case - DB-CP-085");
        int id= cPool.getPreparedStatementID(null);
        out.println("PASSED if value is -1 -->"+id );
    }

    public void DBCP090()
    {
        out.println("Test Case - DB-CP-090");
        out.println("PASSED if it return false -->"+cPool.isInTransaction());
    }

    public void DBCP093()//Nms will shutdown ??Not Working
    {
        out.println("Test Case - DB-CP-093");
        cPool.disconnectAll();
        out.println("PASSED if Nms got Shutdownnn");
    }
    
    public void DBCP100()//Create the table before executing the testcase
    {
        try
        {
            out.println("Test Case - DB-CP-100");
            String sqlString100 = new String("insert into new values(1,\'NET\')");
            cPool.executeUpdateStmt(sqlString100);            
            out.println("Check out the database for inserted values");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void DBCP106_1()
    {
        try
        {
            out.println("Test Case DB-CP-106.1");
            String sqlString106_1 = new String("sel * from ManagedObject");
            int id=cPool.getPreparedStatementID(sqlString106_1);
            cPool.fetchPreparedStatement(id);
            out.println("Check out the stderr for expected exceptions");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void DBCP106_2()
    {
        try
        {
            out.println("Test Case DB-CP-106.2");
            String sqlString106_2= new String("sel * from ManagedObject");
            PreparedStatement ps=cPool.getPreparedStatement(sqlString106_2);
            out.println("Check out the stderr for expected exceptions");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void DBCP132()
    {
        try
        {
            out.println("Test case DBCP-132");
            String sql="create table test132( id int , name varchar (20))";
            PreparedStatement ps = relapi.getPreparedStatement(sql);
            int n = relapi.executeUpdate(ps);
            out.println("Table test132 created :"+ n);
            out.println("Check out expected exceptions in the stderr");
            PreparedStatement  pp = cPool.getPreparedStatement("insert into test132 values(?,?)");
            pp.setString(1,"1");
            pp.setString(2,"Advent");
            pp.addBatch();
            pp.setString(1,"148");
            pp.setString(2,"kernel");
            pp.addBatch();
            pp.executeBatch();
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void DBCP133()//Test Case fails
    {
        try
        {
            out.println("Test case DBCP-133");
            String sql="create table test133( id int , name varchar (20))";
            PreparedStatement ps = relapi.getPreparedStatement(sql);
            int n = relapi.executeUpdate(ps);
            out.println("Table test133 created :"+ n);
            out.println("Check out expected exceptions in the stderr");
            PreparedStatement  pp = cPool.getPreparedStatement("insert into test133 values(?,?)");
            pp.setString(1,"1");
            pp.setString(2,"Advent");
            pp.addBatch();
            pp.setString(1,"148888888888888898888888888888888888888888999999999999999999999999999999999999999999999999999999999999999999");
            pp.setString(2,"kernel");
            pp.addBatch();
            pp.executeBatch();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}

