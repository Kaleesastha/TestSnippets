
/**
 * Txn_Notify_TC.java
 *
 *
 * Created: Mon May 06 12:13:17 2002
 *
 * @author <a href="mailto: "Chitrapandian N</a>
 * @version
 */
package txn;

import javax.transaction.RollbackException;

import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.util.RunProcessInterface;
import com.adventnet.nms.store.Notifier;
import com.adventnet.management.transaction.TransactionAPI;
import com.adventnet.management.transaction.ConnectionPool;
import com.adventnet.management.transaction.UserTransactionException;

/**
 * Entry for testing Notify_TC in NmsProcessesBE.conf as,
 * <pre>
 * # Global NOTIFY_TC Notification type Testing!
 * PROCESS   txn.Txn_Notify_TC
 * ARGS      NULL
 *
 * @author Chitrapandian N
 * @since AdventNet WebNMS 2.3 SP (Transaction Special Operation) 
 * @see RunProcessInterface
 */
public class Txn_Notify_TC implements RunProcessInterface
{
    private TransactionAPI txnAPI = null;
    
    private Notifier notifier;
    private ExampleDispatcher exdis;

    private boolean initialized = false;

    public Txn_Notify_TC ()
    {
    }

    public void callMain (String[] args)
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
        Txn_Notify_TC tnt = new Txn_Notify_TC();
        tnt.init(args);
        try
        {
            Thread.sleep(60000);
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
                           +"Txn_Notify_TC   ========\n");
        System.out.println("--------------------------------------------------------------------------------");
        testCase014();
        System.out.println("--------------------------------------------------------------------------------");
        testCase017();
        System.out.println("--------------------------------------------------------------------------------");
        testCase020();
        System.out.println("--------------------------------------------------------------------------------");
        testCase021();
        System.out.println("--------------------------------------------------------------------------------");
        testCase022();
        System.out.println("--------------------------------------------------------------------------------");
        testCase023();
        System.out.println("--------------------------------------------------------------------------------");
        testCase024();
        System.out.println("--------------------------------------------------------------------------------");
        testCase025();
        System.out.println("--------------------------------------------------------------------------------");
        testCase025_1();
        System.out.println("--------------------------------------------------------------------------------");
        testCase025_2();
        System.out.println("--------------------------------------------------------------------------------");
        testCase026();
        System.out.println("--------------------------------------------------------------------------------");
        testCase029();
        System.out.println("--------------------------------------------------------------------------------");
        testCase032();
        System.out.println("--------------------------------------------------------------------------------");
        testCase033();
        System.out.println("--------------------------------------------------------------------------------");
        testCase034();
        System.out.println("--------------------------------------------------------------------------------");
        testCase035();
        System.out.println("--------------------------------------------------------------------------------");
        testCase036();
        System.out.println("--------------------------------------------------------------------------------");
        testCase037();
        System.out.println("--------------------------------------------------------------------------------");
        testCase038();
        System.out.println("--------------------------------------------------------------------------------");
        testCase039();
        System.out.println("--------------------------------------------------------------------------------");
        testCase040();
        System.out.println("--------------------------------------------------------------------------------");
        testCase041();
        System.out.println("--------------------------------------------------------------------------------");
        testCase042();
        System.out.println("--------------------------------------------------------------------------------");
        testCase043();
        System.out.println("--------------------------------------------------------------------------------");
        testCase044();
        System.out.println("--------------------------------------------------------------------------------");
        testCase045();
        System.out.println("--------------------------------------------------------------------------------");
        testCase046();
        System.out.println("--------------------------------------------------------------------------------");
        testCase047();
        System.out.println("--------------------------------------------------------------------------------");
        testCase049();
        System.out.println("--------------------------------------------------------------------------------");
        testCase050();
        System.out.println("------------------------------------------------------------------------------");
        testCase051();
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("========= All TEST CASES Completed for "
                           +"Txn_Notify_TC  ========\n");
    }

private void testCase051()
    {
        System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-051 :: ENTER");
        try
        {
            txnAPI.begin(30000);
            {
                NotificationObject nobj = new NotificationObject("DB-TXN-NCM-014", null);
                notifier.notify(nobj);
            }
            Thread.sleep(20000);
            System.out.println("The Transaction is to be commited");
            txnAPI.commit();
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-051 :: FAILED");
            System.err.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-051 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
            try{
                txnAPI.rollback();
            }catch(Exception ex){}
        }
        System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-051 :: EXIT\n");
    }



    private void testCase014()
    {
        System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-014 :: ENTER");
        try
        {
            txnAPI.begin();
            {
                NotificationObject nobj = new NotificationObject("DB-TXN-NCM-014", null);
                notifier.notify(nobj);
            }
            System.out.println("The Transaction is to be commited");
            txnAPI.commit();
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-014 :: FAILED");
            System.err.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-014 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
            try{
                txnAPI.rollback();
            }catch(Exception ex){}
        }
        System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-014 :: EXIT\n");
    }

    private void testCase017()
    {
        System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-017 :: ENTER");
        try
        {
            txnAPI.begin(TransactionAPI.NOTIFY_TIP,250000);
            {
                NotificationObject nobj = new NotificationObject("DB-TXN-NCM-017", null);
                notifier.notify(nobj);
            }
            System.out.println("The Transaction is to be commited");
            txnAPI.commit();
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-017 :: FAILED");
            System.err.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-017 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
            try{
                txnAPI.rollback();
            }catch(Exception ex){}
        }
        System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-017 :: EXIT\n");
    }

    private void testCase020()
    {
        System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-020 :: ENTER");
        try
        {
            txnAPI.begin(TransactionAPI.NOTIFY_TC,20000);
            {
                NotificationObject nobj = new NotificationObject("DB-TXN-NCM-020", null);
                notifier.notify(nobj);
            }
            System.out.println("The Transaction is to be commited");
            txnAPI.commit();
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-020 :: FAILED");
            System.err.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-020 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
            try{
                txnAPI.rollback();
            }catch(Exception ex){}
        }
        System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-020 :: EXIT\n");
    }

    private void testCase021()
    {
        System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-021 :: ENTER");
        try
        {
            txnAPI.begin(TransactionAPI.NOTIFY_TIP,250000);
            {
                NotificationObject nobj = new NotificationObject("DB-TXN-NCM-021", null);
                notifier.notify(nobj);
            }
            System.out.println("The Transaction is to be commited");
            txnAPI.commit();
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-021 :: FAILED");
            System.err.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-021 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
            try{
                txnAPI.rollback();
            }catch(Exception ex){}
        }
        System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-021 :: EXIT\n");
    }

    private void testCase022()
    {
        System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-022 :: ENTER");
        try
        {
            txnAPI.begin(TransactionAPI.NOTIFY_NONE,250000);
            {
                NotificationObject nobj = new NotificationObject("DB-TXN-NCM-022", null);
                notifier.notify(nobj);
            }
            System.out.println("The Transaction is to be commited");
            txnAPI.commit();
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-022 :: FAILED");
            System.err.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-022 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
            try{
                txnAPI.rollback();
            }catch(Exception ex){}
        }
        System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-022 :: EXIT\n");
    }

    private void testCase023()
    {
        System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-023 :: ENTER");
        try
        {
            txnAPI.begin(TransactionAPI.NOTIFY_TC,250000);
            {
                NotificationObject nobj = new NotificationObject("DB-TXN-NCM-023", null);
                notifier.notify(nobj);
            }
            try{
                txnAPI.rollback();
            }catch(Exception ex){}
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-023 :: FAILED");
            System.err.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-023 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
        System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-023 :: EXIT\n");
    }

    private void testCase024()
    {
        System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-024 :: ENTER");
        try
        {
            txnAPI.begin(TransactionAPI.NOTIFY_TIP,250000);
            {
                NotificationObject nobj = new NotificationObject("DB-TXN-NCM-024", null);
                notifier.notify(nobj);
            }
            try{
                txnAPI.rollback();
            }catch(Exception ex){}
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-024 :: FAILED");
            System.err.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-024 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
        System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-024 :: EXIT\n");
    }

    private void testCase025()
    {
        System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-025 :: ENTER");
        try
        {
            txnAPI.begin(TransactionAPI.NOTIFY_NONE,250000);
            {
                NotificationObject nobj = new NotificationObject("DB-TXN-NCM-025", null);
                notifier.notify(nobj);
            }
            try{
                txnAPI.rollback();
            }catch(Exception ex){}
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-025 :: FAILED");
            System.err.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-025 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
        System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-025 :: EXIT\n");
    }


 private void testCase025_1()
    {
        System.out.println(" Txn_Notify_TC Testcase 25.1 but internally contains : DB-TXN-NCM-029 :: ENTER");
        try
        {
            txnAPI.setNotificationType(TransactionAPI.NOTIFY_TIP);
            txnAPI.begin(25000);
            {
                NotificationObject nobj = new NotificationObject("DB-TXN-NCM-029", null);
                notifier.notify("Added", nobj);
            }
            System.out.println("The Transaction is to be commited");
            txnAPI.commit();
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-029 :: FAILED");
            System.err.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-029 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
            try{
                txnAPI.rollback();
            }catch(Exception ex){}
        }
        System.out.println(" Txn_Notify_TC Testcase 25.1 contains  : DB-TXN-NCM-029 :: EXIT\n");
    }


    private void testCase025_2()
    {
        System.out.println(" Txn_Notify_TC Testcase 25.2 but contains: DB-TXN-NCM-020 :: ENTER");
        try
        {
            txnAPI.setNotificationType(TransactionAPI.NOTIFY_TC);
            txnAPI.begin(20000);
            System.out.println(" The Notification type is "+txnAPI.getNotificationType(Thread.currentThread()));
            {
                NotificationObject nobj = new NotificationObject("DB-TXN-NCM-020", null);
                notifier.notify(nobj);
            }
            System.out.println("The Transaction is to be commited");
            txnAPI.commit();
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-020 :: FAILED");
            System.err.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-020 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
            try{
                txnAPI.rollback();
            }catch(Exception ex){}
        }
        System.out.println(" Txn_Notify_TC Testcase 25.2 but contains : DB-TXN-NCM-020 :: EXIT\n");
    }


    
    private void testCase026()
    {
        System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-026 :: ENTER");
        try
        {
            txnAPI.begin();
            {
                NotificationObject nobj = new NotificationObject("DB-TXN-NCM-026", null);
                notifier.notify("Added", nobj);
            }
            System.out.println("The Transaction is to be commited");
            txnAPI.commit();
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-026 :: FAILED");
            System.err.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-026 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
            try{
                txnAPI.rollback();
            }catch(Exception ex){}
        }
        System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-026 :: EXIT\n");
    }

    private void testCase029()
    {
        System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-029 :: ENTER");
        try
        {
            txnAPI.begin(TransactionAPI.NOTIFY_TIP,250000);
            {
                NotificationObject nobj = new NotificationObject("DB-TXN-NCM-029", null);
                notifier.notify("Added", nobj);
            }
            System.out.println("The Transaction is to be commited");
            txnAPI.commit();
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-029 :: FAILED");
            System.err.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-029 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
            try{
                txnAPI.rollback();
            }catch(Exception ex){}
        }
        System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-029 :: EXIT\n");
    }

    private void testCase032()
    {
        System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-032 :: ENTER");
        try
        {
            txnAPI.begin(TransactionAPI.NOTIFY_TC,250000);
            {
                NotificationObject nobj = new NotificationObject("DB-TXN-NCM-032", null);
                notifier.notify("Added", nobj);
            }
            System.out.println("The Transaction is to be commited");
            txnAPI.commit();
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-032 :: FAILED");
            System.err.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-032 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
            try{
                txnAPI.rollback();
            }catch(Exception ex){}
        }
        System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-032 :: EXIT\n");
    }

    private void testCase033()
    {
        System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-033 :: ENTER");
        try
        {
            txnAPI.begin(TransactionAPI.NOTIFY_TIP,250000);
            {
                NotificationObject nobj = new NotificationObject("DB-TXN-NCM-033", null);
                notifier.notify("Added", nobj);
            }
            System.out.println("The Transaction is to be commited");
            txnAPI.commit();
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-033 :: FAILED");
            System.err.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-033 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
            try{
                txnAPI.rollback();
            }catch(Exception ex){}
        }
        System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-033 :: EXIT\n");
    }

    private void testCase034()
    {
        System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-034 :: ENTER");
        try
        {
            txnAPI.begin(TransactionAPI.NOTIFY_NONE,250000);
            {
                NotificationObject nobj = new NotificationObject("DB-TXN-NCM-034", null);
                notifier.notify("Added", nobj);
            }
            System.out.println("The Transaction is to be commited");
            txnAPI.commit();
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-034 :: FAILED");
            System.err.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-034 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
            try{
                txnAPI.rollback();
            }catch(Exception ex){}
        }
        System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-034 :: EXIT\n");
    }

    private void testCase035()
    {
        System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-035 :: ENTER");
        try
        {
            txnAPI.begin(TransactionAPI.NOTIFY_TC,250000);
            {
                NotificationObject nobj = new NotificationObject("DB-TXN-NCM-035", null);
                notifier.notify("Added", nobj);
            }
            try{
                txnAPI.rollback();
            }catch(Exception ex){}
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-035 :: FAILED");
            System.err.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-035 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
        System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-035 :: EXIT\n");
    }

    private void testCase036()
    {
        System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-036 :: ENTER");
        try
        {
            txnAPI.begin(TransactionAPI.NOTIFY_TIP,250000);
            {
                NotificationObject nobj = new NotificationObject("DB-TXN-NCM-036", null);
                notifier.notify("Added", nobj);
            }
            try{
                txnAPI.rollback();
            }catch(Exception ex){}
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-036 :: FAILED");
            System.err.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-036 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
        System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-036 :: EXIT\n");
    }

    private void testCase037()
    {
        System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-037 :: ENTER");
        try
        {
            txnAPI.begin(TransactionAPI.NOTIFY_NONE,250000);
            {
                NotificationObject nobj = new NotificationObject("DB-TXN-NCM-037", null);
                notifier.notify("Added", nobj);
            }
            try{
                txnAPI.rollback();
            }catch(Exception ex){}
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-037 :: FAILED");
            System.err.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-037 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
        System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-037 :: EXIT\n");
    }

    private void testCase038()
    {
        System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-038 :: ENTER");
        try
        {
            txnAPI.begin(TransactionAPI.NOTIFY_TC,250000);
            {
                NotificationObject nobj = new NotificationObject("DB-TXN-NCM-038", null);
                notifier.notify(nobj);
                notifier.notify("Added", nobj);
            }
            System.out.println("The Transaction is to be commited");
            txnAPI.commit();
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-038 :: FAILED");
            System.err.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-038 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
            try{
                txnAPI.rollback();
            }catch(Exception ex){}
        }
        System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-038 :: EXIT\n");
    }

    private void testCase039()
    {
        System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-039 :: ENTER");
        try
        {
            txnAPI.begin(TransactionAPI.NOTIFY_TIP,250000);
            {
                NotificationObject nobj = new NotificationObject("DB-TXN-NCM-039", null);
                notifier.notify(nobj);
                notifier.notify("Added", nobj);
            }
            System.out.println("The Transaction is to be commited");
            txnAPI.commit();
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-039 :: FAILED");
            System.err.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-039 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
            try{
                txnAPI.rollback();
            }catch(Exception ex){}
        }
        System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-039 :: EXIT\n");
    }

    private void testCase040()
    {
        System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-040 :: ENTER");
        try
        {
            txnAPI.begin(TransactionAPI.NOTIFY_TC,250000);
            {
                NotificationObject nobj = new NotificationObject("DB-TXN-NCM-040", null);
                notifier.notify(nobj);
                notifier.notify("Added", nobj);
                NotificationObject nobj1 = new NotificationObject("DB-TXN-NCM-040.1", null);
                notifier.notify(nobj1);
            }
            System.out.println("The Transaction is to be commited");
            txnAPI.commit();
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-040 :: FAILED");
            System.err.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-040 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
            try{
                txnAPI.rollback();
            }catch(Exception ex){}
        }
        System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-040 :: EXIT\n");
    }

    private void testCase041()
    {
        System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-041 :: ENTER");
        try
        {
            txnAPI.begin(TransactionAPI.NOTIFY_NONE,250000);
            {
                NotificationObject nobj = new NotificationObject("DB-TXN-NCM-041", null);
                notifier.notify(nobj);
            }
            System.out.println("The Transaction is to be commited");
            txnAPI.commit();
            {
                NotificationObject nobj1 = new NotificationObject("DB-TXN-NCM-041.1", null);
                notifier.notify(nobj1);
            }
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-041 :: FAILED");
            System.err.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-041 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
            try{
                txnAPI.rollback();
            }catch(Exception ex){}
        }
        System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-041 :: EXIT\n");
    }

    private void testCase042()
    {
        System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-042 :: ENTER");
        try
        {
            txnAPI.begin(TransactionAPI.NOTIFY_NONE,250000);
            {
                NotificationObject nobj = new NotificationObject("DB-TXN-NCM-042", null);
                notifier.notify(nobj);
            }
            try
            {
                txnAPI.rollback();
            }
            catch(Exception e) 
            {}
            {
                NotificationObject nobj1 = new NotificationObject("DB-TXN-NCM-042.1", null);
                notifier.notify(nobj1);
            }
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-042 :: FAILED");
            System.err.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-042 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
            try{
                txnAPI.rollback();
            }catch(Exception ex){}
        }
        System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-042 :: EXIT\n");
    }

    private void testCase043()
    {
        System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-043 :: ENTER");
        try
        {
            txnAPI.begin(TransactionAPI.NOTIFY_TC,250000);
            {
                NotificationObject nobj = new NotificationObject("DB-TXN-NCM-043", null);
                notifier.notify("Added", nobj);
                notifier.notify("Updated", nobj);
                notifier.notify("Deleted", nobj);
            }
            System.out.println("The Transaction is to be commited");
            txnAPI.commit();
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-043 :: FAILED");
            System.err.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-043 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
            try{
                txnAPI.rollback();
            }catch(Exception ex){}
        }
        System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-043 :: EXIT\n");
    }

    private void testCase044()
    {
        System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-044 :: ENTER");
        try
        {
            txnAPI.begin(TransactionAPI.NOTIFY_TIP,250000);
            {
                NotificationObject nobj = new NotificationObject("DB-TXN-NCM-044", null);
                notifier.notify("Added", nobj);
                notifier.notify("Updated", nobj);
                notifier.notify("Deleted", nobj);
            }
            System.out.println("The Transaction is to be commited");
            txnAPI.commit();
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-044 :: FAILED");
            System.err.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-044 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
            try{
                txnAPI.rollback();
            }catch(Exception ex){}
        }
        System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-044 :: EXIT\n");
    }

    private void testCase045()
    {
        System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-045 :: ENTER");
        try
        {
            txnAPI.begin(TransactionAPI.NOTIFY_TC,20000);
            {
                NotificationObject nobj = new NotificationObject("DB-TXN-NCM-045", null);
                notifier.notify(nobj);
            }
            try{
                Thread.sleep(30000);
            }catch(Exception ex){}
            try
            {
                System.out.println("The Transaction is to be commited");
                txnAPI.commit();
            }
            catch(RollbackException ute)
            {
                System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-045 :: PASSED");
            }
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-045 :: FAILED");
            System.err.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-045 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
        System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-045 :: EXIT\n");
    }

    private void testCase046()
    {
        System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-046 :: ENTER");
        try
        {
            txnAPI.begin(TransactionAPI.NOTIFY_TIP,20000);
            {
                NotificationObject nobj = new NotificationObject("DB-TXN-NCM-046", null);
                notifier.notify(nobj);
            }
            try{
                Thread.sleep(30000);
            }catch(Exception ex){}
            try
            {
                System.out.println("The Transaction is to be commited");
                txnAPI.commit();
            }
            catch(RollbackException ute)
            {
                System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-046 :: PASSED");
            }
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-046 :: FAILED");
            System.err.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-046 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
        System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-046 :: EXIT\n");
    }

    private void testCase047()
    {
        System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-047 :: ENTER");
        try
        {
            txnAPI.begin(TransactionAPI.NOTIFY_TC,0);
            {
                NotificationObject nobj = new NotificationObject("DB-TXN-NCM-047", null);
                notifier.notify(nobj);
            }
            try{
                Thread.sleep(25000);
            }catch(Exception ex){}
            try
            {
                System.out.println("The Transaction is to be commited");
                txnAPI.commit();
            }
            catch(RollbackException ute)
            {
                System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-047 :: PASSED");
            }
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-047 :: FAILED");
            System.err.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-047 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
        System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-047 :: EXIT\n");
    }

    private void testCase049()
    {
        System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-049 :: ENTER");
        try
        {
            txnAPI.begin(TransactionAPI.NOTIFY_TC,-1);
            {
                NotificationObject nobj = new NotificationObject("DB-TXN-NCM-049", null);
                notifier.notify(nobj);
            }
            try{
                Thread.sleep(30000);
            }catch(Exception ex){}
            System.out.println("The Transaction is to be commited");
            txnAPI.commit();
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-049 :: FAILED");
            System.err.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-049 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
        System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-049 :: EXIT\n");
    }

    private void testCase050()
    {
        System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-050 :: ENTER");
        try
        {
            txnAPI.begin(TransactionAPI.NOTIFY_TC,40000);
            {
                NotificationObject nobj = new NotificationObject("DB-TXN-NCM-050", null);
                notifier.notify(nobj);
            }
            try{
                Thread.sleep(45000);
            }catch(Exception ex){}
            try
            {
                System.out.println("The Transaction is to be commited");
                txnAPI.commit();
            }
            catch(RollbackException ute)
            {
                System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-050 :: PASSED");
            }
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-050 :: FAILED");
            System.err.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-050 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
        System.out.println(" Txn_Notify_TC Testcase : DB-TXN-NCM-050 :: EXIT\n");
    }

}
// Txn_Notify_TC.java














