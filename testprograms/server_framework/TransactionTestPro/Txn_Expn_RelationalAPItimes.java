// $Id: Txn_Expn_RelationalAPItimes.java,v 1.1 2003/05/30 12:25:56 kasturirangan Exp $

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
public class Txn_Expn_RelationalAPItimes implements RunProcessInterface
{
    private TransactionAPI txnAPI = null;
    private RelationalAPI relapi = null;

    private boolean initialized = false;

    //private int sleep = 150000;
    public void Txn_Expn_RelationalAPI()
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
        testcase122();
        testcase123();
         System.out.println("========= All TEST CASES Completed for "
                           +"Txn_Expn_RelationalAPI  ========\n");
    }

    private void testcase122()
    {
        System.out.println(" Txn_Expn_RelationalAPI Testcase == getPreparedStatement : "
                           +"DB-TXN-EXP-122 :: ENTER");
        try
        {
            relapi.getPreparedStatement("sel * from ",false);//kaschange/
            txnAPI.commit();
            System.out.println("Txn_Expn_RelationalAPI Testcase == getPreparedStatement: "
                               +"DB-TXN-EXP-122 :: FAILED == END");
        }
        catch(NmsStorageException ute)
        {
            System.out.println(" Txn_Expn_RelationalAPI Testcase == getPreparedStatement : "
                               +"DB-TXN-EXP-122 :: PASSED");
            System.err.println(" Txn_Expn_RelationalAPI getPreparedStatement DB-TXN-EXP-122 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_RelationalAPI Testcase == getPreparedStatement: "
                               +"DB-TXN-EXP-122 :: FAILED");
            System.err.println(" Txn_Expn_RelationalAPI getPreparedStatement DB-TXN-EXP-122 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
            try{
            txnAPI.rollback();
            }catch(Exception ex){}
        }
    }

    private void testcase123()
    {
        System.out.println(" Txn_Expn_RelationalAPI Testcase == getPreparedStatement : "
                           +"DB-TXN-EXP-123 :: ENTER");
        try
        {
            txnAPI.begin(100);
            {
                Thread.sleep(3000);//kaschange
                relapi.getPreparedStatement("sel * from");
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_RelationalAPI Testcase == getPreparedStatement: "
                               +"DB-TXN-EXP-123 :: FAILED == END");
        }
        catch(NmsStorageException ute)
        {
            System.out.println(" Txn_Expn_RelationalAPI Testcase == getPreparedStatement : "
                               +"DB-TXN-EXP-123 :: PASSED");
            System.err.println(" Txn_Expn_RelationalAPI getPreparedStatement DB-TXN-EXP-123 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_RelationalAPI Testcase == getPreparedStatement: "
                               +"DB-TXN-EXP-123 :: FAILED");
            System.err.println(" Txn_Expn_RelationalAPI getPreparedStatement DB-TXN-EXP-123 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
            try{
            txnAPI.rollback();
            }catch(Exception ex){}
        }
    }


}

//Txn_Expn_RelationalAPI.java
