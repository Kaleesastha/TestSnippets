package txn;

import javax.transaction.RollbackException;

import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.util.RunProcessInterface;
import com.adventnet.nms.store.Notifier;
import com.adventnet.management.transaction.TransactionAPI;
import com.adventnet.management.transaction.ConnectionPool;
import com.adventnet.management.transaction.UserTransactionException;

/**
 * Entry for testing Notify_TIP in NmsProcessesBE.conf as,
 * <pre>

 * PROCESS   txn.Txn_Notify_TIP
 * ARGS      NULL
 *
 * @since AdventNet WebNMS 2.3 SP (Transaction Special Operation) 
 * @see RunProcessInterface
 */
public class Txn_Notify_TIP implements RunProcessInterface
{
    private TransactionAPI txnAPI = null;
    
    private Notifier notifier;
    private ExampleDispatcher exdis;

    private boolean initialized = false;

    public Txn_Notify_TIP()
    {
    }

    public void callMain(String[] args)
    {
        // This method is called by the NMS server during startup.
        init (args);
        initialized = true;	// This is very important	
        try
        {
            Thread.sleep(30000);
        }
        catch(Exception e){}
        process ();	
    }

    public static void main(String[] args)
    {
        Txn_Notify_TIP tnt = new Txn_Notify_TIP();
        tnt.init(args);
        try
        {
            Thread.sleep(30000);
        }
        catch(Exception e)
        {}
        tnt.process();	
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

        notifier = new Notifier();
        exdis = new ExampleDispatcher();
        notifier.addObserver(exdis);
    }

    private void process ()
    {
        System.out.println(" =========   Testing STARTED for "
                           +"Txn_Notify_TIP   ========\n");
        System.out.println("--------------------------------------------------------------------------------");
        testCase015();
        System.out.println("--------------------------------------------------------------------------------");
        //testCase018();
        System.out.println("--------------------------------------------------------------------------------");
        //testCase019();
        System.out.println("--------------------------------------------------------------------------------");
        testCase027();
        System.out.println("--------------------------------------------------------------------------------");
        //testCase030();
        System.out.println("--------------------------------------------------------------------------------");
        //testCase031();
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("========= All TEST CASES Completed for "
                           +"Txn_Notify_TIP  ========\n");
    }

    private void testCase015()
    {
        System.out.println(" Txn_Notify_TIP Testcase : DB-TXN-NCM-015 :: ENTER");
        try
        {
            txnAPI.begin();
            {
                NotificationObject nobj = new NotificationObject("DB-TXN-NCM-015", null);
                notifier.notify(nobj);
            }
            System.out.println("The Transaction is to be commited");
            txnAPI.commit();
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Notify_TIP Testcase : DB-TXN-NCM-015 :: FAILED");
            System.err.println(" Txn_Notify_TIP Testcase : DB-TXN-NCM-015 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
            try{
                txnAPI.rollback();
            }catch(Exception ex){}
        }
        System.out.println(" Txn_Notify_TIP Testcase : DB-TXN-NCM-015 :: EXIT\n");
    }

    private void testCase018()
    {
        System.out.println(" Txn_Notify_TIP Testcase : DB-TXN-NCM-018 :: ENTER");
        try
        {
            txnAPI.begin(TransactionAPI.NOTIFY_TC,250000);
            {
                NotificationObject nobj = new NotificationObject("DB-TXN-NCM-018", null);
                notifier.notify(nobj);
            }
            System.out.println("The Transaction is to be commited");
            txnAPI.commit();
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Notify_TIP Testcase : DB-TXN-NCM-018 :: FAILED");
            System.err.println(" Txn_Notify_TIP Testcase : DB-TXN-NCM-018 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
            try{
                txnAPI.rollback();
            }catch(Exception ex){}
        }
        System.out.println(" Txn_Notify_TIP Testcase : DB-TXN-NCM-018 :: EXIT\n");
    }

    private void testCase019()
    {
        System.out.println(" Txn_Notify_TIP Testcase : DB-TXN-NCM-019 :: ENTER");
        try
        {
            txnAPI.begin(TransactionAPI.NOTIFY_NONE,250000);
            {
                NotificationObject nobj = new NotificationObject("DB-TXN-NCM-019", null);
                notifier.notify(nobj);
            }
            System.out.println("The Transaction is to be commited");
            txnAPI.commit();
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Notify_TIP Testcase : DB-TXN-NCM-019 :: FAILED");
            System.err.println(" Txn_Notify_TIP Testcase : DB-TXN-NCM-019 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
            try{
                txnAPI.rollback();
            }catch(Exception ex){}
        }
        System.out.println(" Txn_Notify_TIP Testcase : DB-TXN-NCM-019 :: EXIT\n");
    }

    private void testCase027()
    {
        System.out.println(" Txn_Notify_TIP Testcase : DB-TXN-NCM-027 :: ENTER");
        try
        {
            txnAPI.begin();
            {
                NotificationObject nobj = new NotificationObject("DB-TXN-NCM-027", null);
                notifier.notify("Added", nobj);
            }
            System.out.println("The Transaction is to be commited");
            txnAPI.commit();
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Notify_TIP Testcase : DB-TXN-NCM-027 :: FAILED");
            System.err.println(" Txn_Notify_TIP Testcase : DB-TXN-NCM-027 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
            try{
                txnAPI.rollback();
            }catch(Exception ex){}
        }
        System.out.println(" Txn_Notify_TIP Testcase : DB-TXN-NCM-027 :: EXIT\n");
    }

    private void testCase030()
    {
        System.out.println(" Txn_Notify_TIP Testcase : DB-TXN-NCM-030 :: ENTER");
        try
        {
            txnAPI.begin(TransactionAPI.NOTIFY_TC,250000);
            {
                NotificationObject nobj = new NotificationObject("DB-TXN-NCM-030", null);
                notifier.notify("Added", nobj);
            }
            System.out.println("The Transaction is to be commited");
            txnAPI.commit();
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Notify_TIP Testcase : DB-TXN-NCM-030 :: FAILED");
            System.err.println(" Txn_Notify_TIP Testcase : DB-TXN-NCM-030 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
            try{
                txnAPI.rollback();
            }catch(Exception ex){}
        }
        System.out.println(" Txn_Notify_TIP Testcase : DB-TXN-NCM-030 :: EXIT\n");
    }

    private void testCase031()
    {
        System.out.println(" Txn_Notify_TIP Testcase : DB-TXN-NCM-031 :: ENTER");
        try
        {
            txnAPI.begin(TransactionAPI.NOTIFY_NONE,250000);
            {
                NotificationObject nobj = new NotificationObject("DB-TXN-NCM-031", null);
                notifier.notify("Added", nobj);
            }
            System.out.println("The Transaction is to be commited");
            txnAPI.commit();
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Notify_TIP Testcase : DB-TXN-NCM-031 :: FAILED");
            System.err.println(" Txn_Notify_TIP Testcase : DB-TXN-NCM-031 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
            try{
                txnAPI.rollback();
            }catch(Exception ex){}
        }
        System.out.println(" Txn_Notify_TIP Testcase : DB-TXN-NCM-031 :: EXIT\n");
    }

}
