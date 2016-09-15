// $Id: Txn_Expn_RelationalAPI.java,v 1.3 2003/05/30 12:22:57 kasturirangan Exp $

/**
 * Txn_Expn_RelationalAPI.java
 *
 *
 * Created: Tue Apr 30 18:33:31 2002
 *
 * @author Chitrapandian N
 * @version
 */

package txn;

import java.util.Vector;
import java.sql.PreparedStatement;

import javax.transaction.RollbackException;

import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.util.RunProcessInterface;
import com.adventnet.nms.store.relational.RelationalAPI;
import com.adventnet.nms.store.NmsStorageException;
import com.adventnet.management.transaction.TransactionAPI;
import com.adventnet.management.transaction.UserTransactionException;

/**
 * Entry for testing RelationalAPI in NmsProcessesBE.conf as,
 * <pre>
 * # RelationalAPI's UserTransactionException Testing !
 * PROCESS   txn.Txn_Expn_RelationalAPI
 * ARGS      NULL
 *
 * @author Chitrapandian N
 * @version 1.0
 * @since AdventNet WebNMS 2.3 SP (Transaction Special Operation)
 * @see RunProcessInterface
 */
public class Txn_Expn_RelationalAPI implements RunProcessInterface
{
    private TransactionAPI txnAPI = null;
    private RelationalAPI relapi = null;

    private boolean initialized = false;

    //private int sleep = 150000;
    public Txn_Expn_RelationalAPI ()
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
        relapi = NmsUtil.relapi;
        System.out.println(" RelationalAPI instance RECEIVED !" +relapi);
    }

    private void process ()
    {
        System.out.println(" ========= Testing STARTED for "
                           +"Txn_Expn_RelationalAPI  ========");
        testcase070();
        testcase071();
        testcase072();
        testcase072_1();
        testcase073();
        testcase074();
        testcase075();
        testcase076();
        System.out.println("========= All TEST CASES Completed for "
                           +"Txn_Expn_RelationalAPI  ========\n");
    }

    private void testcase070()
    {
        System.out.println(" Txn_Expn_RelationalAPI Testcase == getMetaData : "
                           +"DB-TXN-EXP-070 :: ENTER");
        try
        {
            txnAPI.begin(100);
            {
                Thread.sleep(1200);
                relapi.getMetaData();
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_RelationalAPI Testcase == getMetaData: "
                               +"DB-TXN-EXP-070 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_RelationalAPI Testcase == getMetaData : "
                               +"DB-TXN-EXP-070 :: PASSED");
            System.err.println(" Txn_Expn_RelationalAPI getMetaData DB-TXN-EXP-070 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_RelationalAPI Testcase == getMetaData: "
                               +"DB-TXN-EXP-070 :: FAILED");
            System.err.println(" Txn_Expn_RelationalAPI getMetaData DB-TXN-EXP-070 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
            try{
            txnAPI.rollback();
            }catch(Exception ex){}
        }
    }

    private void testcase071()
    {
        System.out.println(" Txn_Expn_RelationalAPI Testcase == executeTheStatement (UserTransactionException): "
                           +"DB-TXN-EXP-071 :: ENTER");
        try
        {
            txnAPI.begin(100);
            {
                Thread.sleep(1200);
                relapi.executeTheStatement("select * from Node");
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_RelationalAPI Testcase == executeTheStatement: "
                               +"DB-TXN-EXP-071 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_RelationalAPI Testcase == executeTheStatement : "
                               +"DB-TXN-EXP-071 :: PASSED");
            System.err.println(" Txn_Expn_RelationalAPI executeTheStatement DB-TXN-EXP-071 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_RelationalAPI Testcase == executeTheStatement: "
                               +"DB-TXN-EXP-071 :: FAILED");
            System.err.println(" Txn_Expn_RelationalAPI executeTheStatement DB-TXN-EXP-071 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
            try{
            txnAPI.rollback();
            }catch(Exception ex){}
        }
    }

    private void testcase072()
    {
        System.out.println(" Txn_Expn_RelationalAPI Testcase == executeTheStatement (NmsStorageException) : "
                           +"DB-TXN-EXP-072 :: ENTER");
        try
        {
            txnAPI.begin();
            {
                relapi.executeTheStatement("sele * from Node");
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_RelationalAPI Testcase == executeTheStatement : "
                               +"DB-TXN-EXP-072 :: FAILED == END");
        }
        catch(NmsTransactionException nse)//kaschange
        {
            System.out.println(" Txn_Expn_RelationalAPI Testcase == executeTheStatement : "
                               +"DB-TXN-EXP-072 :: PASSED");
            System.err.println(" Txn_Expn_RelationalAPI executeTheStatement DB-TXN-EXP-072 :: "
                               +"NmsTransactionException Received :: \n" +nse);
            try{
            txnAPI.rollback();
            }catch(Exception ex){}
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_RelationalAPI Testcase == executeTheStatement: "
                               +"DB-TXN-EXP-072 :: FAILED");
            System.err.println(" Txn_Expn_RelationalAPI executeTheStatement DB-TXN-EXP-072 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
            try{
            txnAPI.rollback();
            }catch(Exception ex){}
        }
    }

    private void testcase072_1()
    {
        System.out.println(" Txn_Expn_RelationalAPI Testcase == executeUpdate : "
                           +"DB-TXN-EXP-072.1 :: ENTER");
        try
        {
            PreparedStatement ps = relapi.getPreparedStatement("select count(*) from Node");
            txnAPI.begin(100);
            {
                Thread.sleep(1200);
                relapi.executeUpdate(ps);
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_RelationalAPI Testcase == executeUpdate: "
                               +"DB-TXN-EXP-072.1 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_RelationalAPI Testcase == executeUpdate : "
                               +"DB-TXN-EXP-072.1 :: PASSED");
            System.err.println(" Txn_Expn_RelationalAPI executeUpdate DB-TXN-EXP-072.1 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
       /* catch(RollbackException ute)
        {
            System.out.println(" Txn_Expn_RelationalAPI Testcase == executeUpdate : "
                               +"DB-TXN-EXP-072.1 :: PASSED");
            System.err.println(" Txn_Expn_RelationalAPI executeUpdate DB-TXN-EXP-072.1 :: "
                               +"RollbackException Received :: \n" +ute);
        }*///kaschange
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_RelationalAPI Testcase == executeUpdate: "
                               +"DB-TXN-EXP-072.1 :: FAILED");
            System.err.println(" Txn_Expn_RelationalAPI executeUpdate DB-TXN-EXP-072.1 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
            try{
            txnAPI.rollback();
            }catch(Exception ex){}
        }
    }

    private void testcase073()
    {
        System.out.println(" Txn_Expn_RelationalAPI Testcase == execute : "
                           +"DB-TXN-EXP-073 :: ENTER");
        try
        {
            txnAPI.begin(100);
            {
                Thread.sleep(1200);
                relapi.execute("select * from Node");
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_RelationalAPI Testcase == execute: "
                               +"DB-TXN-EXP-073 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_RelationalAPI Testcase == execute : "
                               +"DB-TXN-EXP-073 :: PASSED");
            System.err.println(" Txn_Expn_RelationalAPI execute DB-TXN-EXP-073 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_RelationalAPI Testcase == execute: "
                               +"DB-TXN-EXP-073 :: FAILED");
            System.err.println(" Txn_Expn_RelationalAPI execute DB-TXN-EXP-073 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
            try{
            txnAPI.rollback();
            }catch(Exception ex){}
        }
    }

    private void testcase074()
    {
        System.out.println(" Txn_Expn_RelationalAPI Testcase == getPreparedStatement : "
                           +"DB-TXN-EXP-074 :: ENTER");
        try
        {
            txnAPI.begin(100);
            {
                Thread.sleep(3000);//kaschange
                relapi.getPreparedStatement("select * from Node",false);//kaschange/
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_RelationalAPI Testcase == getPreparedStatement: "
                               +"DB-TXN-EXP-074 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_RelationalAPI Testcase == getPreparedStatement : "
                               +"DB-TXN-EXP-074 :: PASSED");
            System.err.println(" Txn_Expn_RelationalAPI getPreparedStatement DB-TXN-EXP-074 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_RelationalAPI Testcase == getPreparedStatement: "
                               +"DB-TXN-EXP-074 :: FAILED");
            System.err.println(" Txn_Expn_RelationalAPI getPreparedStatement DB-TXN-EXP-074 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
            try{
            txnAPI.rollback();
            }catch(Exception ex){}
        }
    }

    private void testcase075()
    {
        System.out.println(" Txn_Expn_RelationalAPI Testcase == getPreparedStatement : "
                           +"DB-TXN-EXP-075 :: ENTER");
        try
        {
            txnAPI.begin(100);
            {
                Thread.sleep(3000);//kaschange
                relapi.getPreparedStatement("select * from Node");
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_RelationalAPI Testcase == getPreparedStatement: "
                               +"DB-TXN-EXP-075 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_RelationalAPI Testcase == getPreparedStatement : "
                               +"DB-TXN-EXP-075 :: PASSED");
            System.err.println(" Txn_Expn_RelationalAPI getPreparedStatement DB-TXN-EXP-075 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_RelationalAPI Testcase == getPreparedStatement: "
                               +"DB-TXN-EXP-075 :: FAILED");
            System.err.println(" Txn_Expn_RelationalAPI getPreparedStatement DB-TXN-EXP-075 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
            try{
            txnAPI.rollback();
            }catch(Exception ex){}
        }
    }

    private void testcase076()
    {
        System.out.println(" Txn_Expn_RelationalAPI Testcase == query : "
                           +"DB-TXN-EXP-076 :: ENTER");
        try
        {
            txnAPI.begin(100);
            {
                Thread.sleep(1200);
                relapi.query("select * from Node",false);
            }
            txnAPI.commit();
            System.out.println("Txn	_Expn_RelationalAPI Testcase == query: "
                               +"DB-TXN-EXP-076 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_RelationalAPI Testcase == query : "
                               +"DB-TXN-EXP-076 :: PASSED");
            System.err.println(" Txn_Expn_RelationalAPI query DB-TXN-EXP-076 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_RelationalAPI Testcase == query: "
                               +"DB-TXN-EXP-076 :: FAILED");
            System.err.println(" Txn_Expn_RelationalAPI query DB-TXN-EXP-076 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
            try{
            txnAPI.rollback();
            }catch(Exception ex){}
        }
    }
}

//Txn_Expn_RelationalAPI.java
