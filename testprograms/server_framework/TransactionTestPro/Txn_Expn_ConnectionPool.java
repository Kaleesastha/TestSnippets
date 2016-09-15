// $Id: Txn_Expn_ConnectionPool.java,v 1.2 2003/05/30 12:21:12 kasturirangan Exp $

/**
 * Txn_Expn_ConnectionPool.java
 *
 *
 * Created: Tue Apr 30 18:33:31 2002
 *
 * @author Chitrapandian N
 * @version
 */

package txn;

import java.util.Vector;

import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.util.RunProcessInterface;
import com.adventnet.management.transaction.TransactionAPI;
import com.adventnet.management.transaction.ConnectionPool;
import com.adventnet.management.transaction.UserTransactionException;

/**
 * Entry for testing ConnectionPool in NmsProcessesBE.conf as,
 * <pre>
 * # ConnectionPool's UserTransactionException Testing !
 * PROCESS   txn.Txn_Expn_ConnectionPool
 * ARGS      NULL
 *
 * @author Chitrapandian N
 * @version 1.0
 * @since AdventNet WebNMS 2.3 SP (Transaction Special Operation)
 * @see RunProcessInterface
 */
public class Txn_Expn_ConnectionPool implements RunProcessInterface
{
    private TransactionAPI txnAPI = null;
    private ConnectionPool cp = null;

    private boolean initialized = false;

    //private int sleep = 150000;
    public Txn_Expn_ConnectionPool ()
    {
    }

    public void callMain (String[] args)
    {
        // This method is called by the NMS server during startup.
        try
        {
            Thread.sleep(20000);
        }
        catch(Exception e){}
        init (args);
        initialized = true;	// This is very important
        process ();
    }

    public boolean isInitialized ()
    {
        return initialized;
    }

    public void shutDown ()
    {
        // handle shutdown for the process
    }

    private void init (String[] args)
    {
        // do process specific initialization
        txnAPI = NmsUtil.relapi.getTransactionAPI();
        cp = NmsUtil.relapi.getConnectionPool();
        System.out.println(" ConnectionPool instance RECEIVED !" +cp);
    }

    private void process ()
    {
        System.out.println(" ========= Testing STARTED for "
                           +"Txn_Expn_ConnectionPool  ========");
        testcase077();
        testcase078();
        testcase079();
        testcase080();
        testcase081();
        testcase082();
         testcase083();
        testcase084();
        testcase085();
        System.out.println("========= All TEST CASES Completed for "
                           +"Txn_Expn_ConnectionPool  ========\n");
    }

    private void testcase077()
    {
        System.out.println(" Txn_Expn_ConnectionPool Testcase == getMetaData : "
                           +"DB-TXN-EXP-077 :: ENTER");
        try
        {
            txnAPI.begin(100);
            {
                Thread.sleep(1200);
                cp.getMetaData();
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_ConnectionPool Testcase == getMetaData: "
                               +"DB-TXN-EXP-077 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_ConnectionPool Testcase == getMetaData : "
                               +"DB-TXN-EXP-077 :: PASSED");
            System.err.println(" Txn_Expn_ConnectionPool getMetaData DB-TXN-EXP-077 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_ConnectionPool Testcase == getMetaData: "
                               +"DB-TXN-EXP-077 :: FAILED");
            System.err.println(" Txn_Expn_ConnectionPool getMetaData DB-TXN-EXP-077 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
            try{
            txnAPI.rollback();
            }catch(Exception ex){}
        }
    }

    private void testcase078()
    {
        System.out.println(" Txn_Expn_ConnectionPool Testcase == executeTheStatement : "
                           +"DB-TXN-EXP-078 :: ENTER");
        try
        {
            txnAPI.begin(100);
            {
                Thread.sleep(1200);
                cp.executeTheStatement("select * from Node");
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_ConnectionPool Testcase == executeTheStatement: "
                               +"DB-TXN-EXP-078 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_ConnectionPool Testcase == executeTheStatement : "
                               +"DB-TXN-EXP-078 :: PASSED");
            System.err.println(" Txn_Expn_ConnectionPool executeTheStatement DB-TXN-EXP-078 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_ConnectionPool Testcase == executeTheStatement: "
                               +"DB-TXN-EXP-078 :: FAILED");
            System.err.println(" Txn_Expn_ConnectionPool executeTheStatement DB-TXN-EXP-078 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
            try{
            txnAPI.rollback();
            }catch(Exception ex){}
        }
    }

    private void testcase079()
    {
        System.out.println(" Txn_Expn_ConnectionPool Testcase == executeUpdateStmt : "
                           +"DB-TXN-EXP-079 :: ENTER");
        try
        {
            txnAPI.begin(100);
            {
                Thread.sleep(1200);//KASCHANGE
                cp.executeUpdateStmt("select count(*) from node");
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_ConnectionPool Testcase == executeUpdateStmt: "
                               +"DB-TXN-EXP-079 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_ConnectionPool Testcase == executeUpdateStmt : "
                               +"DB-TXN-EXP-079 :: PASSED");
            System.err.println(" Txn_Expn_ConnectionPool executeUpdateStmt DB-TXN-EXP-079 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_ConnectionPool Testcase == executeUpdateStmt: "
                               +"DB-TXN-EXP-079 :: FAILED");
            System.err.println(" Txn_Expn_ConnectionPool executeUpdateStmt DB-TXN-EXP-079 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
            try{
            txnAPI.rollback();
            }catch(Exception ex){}
        }
    }

    private void testcase080()
    {
        System.out.println(" Txn_Expn_ConnectionPool Testcase == executeQueryStmt : "
                           +"DB-TXN-EXP-080 :: ENTER");
        try
        {
            txnAPI.begin(100);
            {
                Thread.sleep(1200);
                cp.executeQueryStmt("select * from Node");
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_ConnectionPool Testcase == executeQueryStmt: "
                               +"DB-TXN-EXP-080 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_ConnectionPool Testcase == executeQueryStmt : "
                               +"DB-TXN-EXP-080 :: PASSED");
            System.err.println(" Txn_Expn_ConnectionPool executeQueryStmt DB-TXN-EXP-080 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_ConnectionPool Testcase == executeQueryStmt: "
                               +"DB-TXN-EXP-080 :: FAILED");
            System.err.println(" Txn_Expn_ConnectionPool executeQueryStmt DB-TXN-EXP-080 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
            try{
            txnAPI.rollback();
            }catch(Exception ex){}
        }
    }

    private void testcase081()
    {
        System.out.println(" Txn_Expn_ConnectionPool Testcase == execute : "
                           +"DB-TXN-EXP-081 :: ENTER");
        try
        {
            txnAPI.begin(100);
            {
                Thread.sleep(1200);
                cp.execute("select * from Node");
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_ConnectionPool Testcase == execute: "
                               +"DB-TXN-EXP-081 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_ConnectionPool Testcase == execute : "
                               +"DB-TXN-EXP-081 :: PASSED");
            System.err.println(" Txn_Expn_ConnectionPool execute DB-TXN-EXP-081 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_ConnectionPool Testcase == execute: "
                               +"DB-TXN-EXP-081 :: FAILED");
            System.err.println(" Txn_Expn_ConnectionPool execute DB-TXN-EXP-081 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
            try{
            txnAPI.rollback();
            }catch(Exception ex){}
        }
    }

    private void testcase082()
    {
        System.out.println(" Txn_Expn_ConnectionPool Testcase == prepareStatement : "
                           +"DB-TXN-EXP-082 :: ENTER");
        try
        {
            txnAPI.begin(100);
            {
                Thread.sleep(1200);
                cp.prepareStatement("select * from Node");
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_ConnectionPool Testcase == prepareStatement: "
                               +"DB-TXN-EXP-082 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_ConnectionPool Testcase == prepareStatement : "
                               +"DB-TXN-EXP-082 :: PASSED");
            System.err.println(" Txn_Expn_ConnectionPool prepareStatement DB-TXN-EXP-082 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_ConnectionPool Testcase == prepareStatement: "
                               +"DB-TXN-EXP-082 :: FAILED");
            System.err.println(" Txn_Expn_ConnectionPool prepareStatement DB-TXN-EXP-082 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
            try{
            txnAPI.rollback();
            }catch(Exception ex){}
        }
    }

    private void testcase083()
    {
        System.out.println(" Txn_Expn_ConnectionPool Testcase == getPreparedStatement : "
                           +"DB-TXN-EXP-083 :: ENTER");
        try
        {
            txnAPI.begin(100);
            {
                Thread.sleep(1200);
                cp.getPreparedStatement("select * from Node",false);
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_ConnectionPool Testcase == getPreparedStatement: "
                               +"DB-TXN-EXP-083 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_ConnectionPool Testcase == getPreparedStatement : "
                               +"DB-TXN-EXP-083 :: PASSED");
            System.err.println(" Txn_Expn_ConnectionPool getPreparedStatement DB-TXN-EXP-083 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_ConnectionPool Testcase == getPreparedStatement: "
                               +"DB-TXN-EXP-083 :: FAILED");
            System.err.println(" Txn_Expn_ConnectionPool getPreparedStatement DB-TXN-EXP-083 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
            try{
            txnAPI.rollback();
            }catch(Exception ex){}
        }
    }

    private void testcase084()
    {
        System.out.println(" Txn_Expn_ConnectionPool Testcase == getPreparedStatement : "
                           +"DB-TXN-EXP-084 :: ENTER");
        try
        {
            txnAPI.begin(100);
            {
                Thread.sleep(1200);
                cp.getPreparedStatement("select * from Node");
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_ConnectionPool Testcase == getPreparedStatement: "
                               +"DB-TXN-EXP-084 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_ConnectionPool Testcase == getPreparedStatement : "
                               +"DB-TXN-EXP-084 :: PASSED");
            System.err.println(" Txn_Expn_ConnectionPool getPreparedStatement DB-TXN-EXP-084 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_ConnectionPool Testcase == getPreparedStatement: "
                               +"DB-TXN-EXP-084 :: FAILED");
            System.err.println(" Txn_Expn_ConnectionPool getPreparedStatement DB-TXN-EXP-084 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
            try{
            txnAPI.rollback();
            }catch(Exception ex){}
        }
    }

    private void testcase085()
    {
        System.out.println(" Txn_Expn_ConnectionPool Testcase == query : "
                           +"DB-TXN-EXP-085 :: ENTER");
        try
        {
            txnAPI.begin(100);
            {
                Thread.sleep(1200);
                cp.query("select * from Node",false);
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_ConnectionPool Testcase == query: "
                               +"DB-TXN-EXP-085 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_ConnectionPool Testcase == query : "
                               +"DB-TXN-EXP-085 :: PASSED");
            System.err.println(" Txn_Expn_ConnectionPool query DB-TXN-EXP-085 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_ConnectionPool Testcase == query: "
                               +"DB-TXN-EXP-085 :: FAILED");
            System.err.println(" Txn_Expn_ConnectionPool query DB-TXN-EXP-085 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
            try{
            txnAPI.rollback();
            }catch(Exception ex){}
        }
    }
}

//Txn_Expn_ConnectionPool.java
