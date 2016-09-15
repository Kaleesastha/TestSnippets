// $Id: Txn_Expn_ConnectionPoolTimes.java,v 1.1 2003/05/30 12:24:36 kasturirangan Exp $

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
import com.adventnet.nms.store.NmsStorageException;
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
public class Txn_Expn_ConnectionPooltimes implements RunProcessInterface
{
    private TransactionAPI txnAPI = null;
    private ConnectionPool cp = null;

    private boolean initialized = false;

    //private int sleep = 150000;
    public void Txn_Expn_ConnectionPool()
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
        testcase119();
        testcase120();
        testcase121();
        System.out.println("========= All TEST CASES Completed for "
                           +"Txn_Expn_ConnectionPool  ========\n");
    }


    private void testcase119()
    {
        System.out.println(" Txn_Expn_ConnectionPool Testcase == getPreparedStatement : "
                           +"DB-TXN-EXP-119 :: ENTER");
        try
        {

                cp.getPreparedStatement("sele * from");

            txnAPI.commit();
            System.out.println("Txn_Expn_ConnectionPool Testcase == getPreparedStatement: "
                               +"DB-TXN-EXP-119 :: FAILED == END");
        }
        catch(NmsStorageException ute)
        {
            System.out.println(" Txn_Expn_ConnectionPool Testcase == getPreparedStatement : "
                               +"DB-TXN-EXP-119 :: PASSED");
            System.err.println(" Txn_Expn_ConnectionPool getPreparedStatement DB-TXN-EXP-119 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_ConnectionPool Testcase == getPreparedStatement: "
                               +"DB-TXN-EXP-119 :: FAILED");
            System.err.println(" Txn_Expn_ConnectionPool getPreparedStatement DB-TXN-EXP-119 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
            try{
            txnAPI.rollback();
            }catch(Exception ex){}
        }
    }

private void testcase120()
    {
        System.out.println(" Txn_Expn_ConnectionPool Testcase == getPreparedStatement : "
                           +"DB-TXN-EXP-120 :: ENTER");
        try
        {

                cp.getPreparedStatement("sele * from",false);

            txnAPI.commit();
            System.out.println("Txn_Expn_ConnectionPool Testcase == getPreparedStatement: "
                               +"DB-TXN-EXP-120 :: FAILED == END");
        }
        catch(NmsStorageException ute)
        {
            System.out.println(" Txn_Expn_ConnectionPool Testcase == getPreparedStatement : "
                               +"DB-TXN-EXP-120 :: PASSED");
            System.err.println(" Txn_Expn_ConnectionPool getPreparedStatement DB-TXN-EXP-120 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_ConnectionPool Testcase == getPreparedStatement: "
                               +"DB-TXN-EXP-120 :: FAILED");
            System.err.println(" Txn_Expn_ConnectionPool getPreparedStatement DB-TXN-EXP-120 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
            try{
            txnAPI.rollback();
            }catch(Exception ex){}
        }
    }


private void testcase121()
    {
        System.out.println(" Txn_Expn_ConnectionPool Testcase == preparedStatement : "
                           +"DB-TXN-EXP-121 :: ENTER");
        try
        {

                cp.prepareStatement("sele * from");

            txnAPI.commit();
            System.out.println("Txn_Expn_ConnectionPool Testcase == getPreparedStatement: "
                               +"DB-TXN-EXP-121 :: FAILED == END");
        }
        catch(NmsStorageException ute)
        {
            System.out.println(" Txn_Expn_ConnectionPool Testcase == getPreparedStatement : "
                               +"DB-TXN-EXP-121 :: PASSED");
            System.err.println(" Txn_Expn_ConnectionPool getPreparedStatement DB-TXN-EXP-121 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_ConnectionPool Testcase == getPreparedStatement: "
                               +"DB-TXN-EXP-121 :: FAILED");
            System.err.println(" Txn_Expn_ConnectionPool getPreparedStatement DB-TXN-EXP-121 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
            try{
            txnAPI.rollback();
            }catch(Exception ex){}
        }
    }



}

//Txn_Expn_ConnectionPool.java
