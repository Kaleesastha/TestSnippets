package com.adventnet.management.transaction;
import com.adventnet.nms.store.relational.*;
import com.adventnet.nms.util.*;
import java.sql.*;
import java.util.*;
public class ConnectionPoolTestOne implements RunProcessInterface, ConnectionAuthenticationInterface
{
    RelationalAPI relapi= null;
    ConnectionPool cPool=null;
    public ConnectionPoolTestOne()
    {
        relapi= NmsUtil.relapi;
        cPool=relapi.getConnectionPool();
    }
    public void callMain(String args[])
    {
        ConnectionPoolTestOne cp= new ConnectionPoolTestOne();
        cp.DBCP007();
        //cp.DBCP022();
        //cp.DBCP034();
        //cp.DBCP097();

    }
    public void DBCP007()
    {
        System.err.println("Test Case - DB-CP-007");
        String sqlString= "select * from Node where name=?";
        int id=relapi.getPreparedStatementID(sqlString);
        long before=System.currentTimeMillis();
        System.err.println("Time before fetching the PreparedStatement"+ before);
        PreparedStatementWrapper ps= relapi.fetchPreparedStatement(id);
        long after=ps.getTimeOfConnection();
        System.err.println("Time after fetching the PreparedStatement"+ after);
        System.err.println("Total Time Taken "+ (after-before) );
    }
    public void DBCP022()
    {
        System.err.println("Test Case - DB-CP-022");
        String sqlString= "select * from Node where name=?";
        int id=relapi.getPreparedStatementID(sqlString);
        PreparedStatementWrapper ps= relapi.fetchPreparedStatement(id);
        
        long t1= ps.getTimeOfConnection();
        System.err.println("Time at first fetch"+ t1);
        
        ps.setTimeOfConnection(t1+2000);//Changing the time of connection
        ps= relapi.fetchPreparedStatement(id);
        
        t1= ps.getTimeOfConnection();
        System.err.println("Time at second fetch"+ t1);
        System.err.println("----PASSED if above time is differ from previous");
    }

    public void authenticateConnection(Connection con)
    {
        System.err.println("Connection is created--DB-CP-023-PASSED");//Check this in console
    }

    public void DBCP034()
    {
        System.err.println("Test Case - DB-CP-034");
        Vector data = new Vector(); 
        for(int i = 0;i<5;i++) 
        {
            try
            {
                ConnectionPool conPool=relapi.getConnectionPool();
                conPool.lockConnectionForTransaction(10000);
                
                String sqlString= "select * from Node where name=?";
                int id=relapi.getPreparedStatementID(sqlString);
                PreparedStatementWrapper ps = relapi.fetchPreparedStatement(id );
                Connection con = ps.getPreparedStatement().getConnection(); 
                if(!data.contains(con)) data.add(con);
                relapi.returnPreparedStatement(ps);
                conPool.freeConnectionForTransaction(); 
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
        int size = data.size(); 
        System.err.println("DB-CP-034-PASSED:: if the value is equal to the number of Transaction Connection set "+ size);
    }
    public void DBCP097()//TestCase Fails!!
    {
        try
        {
            System.err.println("Test Case - DB-CP-097");
            PreparedStatementWrapper psw = cPool.fetchStatement(); 
            System.err.println("PASSED if result is false -->"+psw.getStatement().getConnection().getAutoCommit());  
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public boolean isInitialized()
    {
        return true;
    }

    public void shutDown()
    {
    }

}

