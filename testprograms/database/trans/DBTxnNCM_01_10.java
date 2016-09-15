package txn;

import java.util.Vector;

import com.adventnet.management.transaction.TransactionHandler;
import com.adventnet.management.transaction.TransactionAPI;

import com.adventnet.nms.store.relational.RelationalAPI;
import com.adventnet.nms.store.relational.RelationalUtil;

import com.adventnet.nms.store.NotificationCacheMaintainer;

import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.util.PureUtils;
import com.adventnet.nms.util.PureServerUtils;
import com.adventnet.nms.util.RunProcessInterface;

public class DBTxnNCM_01_10 implements RunProcessInterface
{

    private TransactionAPI tapi = null;
    private RelationalAPI relapi = null;
    private SampleHandler sampleHandler = null;
    private NotificationCacheMaintainer maintainer = null;
    static String nmsdir = null;	
    private boolean initialized = false;

    public DBTxnNCM_01_10()
    {}

    public void callMain(String[] args)
    {
        try
        {
            Thread.sleep(30000);
            init(args);
        }
        catch(Exception e)
        {
        }
        initialized = true;
        process();	
    }

    public boolean isInitialized()
    {
        return initialized;
    }

    public void shutDown()
    {
        // handle shutdown for the process
    }

    private void init(String[] args) throws Exception
    {
        // The following initialization is needed if run in a separate JVM without NMS.
        /*
          PureUtils.rootDir = nmsdir;
          PureServerUtils.useJDBC = true;

          NmsUtil.readServerParams();
          NmsUtil.setDBObjectsCacheSize();
          RelationalUtil.init(null);
          PureServerUtils.getDatabaseParams();
          System.out.println("rootDir is "+PureUtils.rootDir);
          System.out.println("url is "+PureServerUtils.url);
          NmsUtil.init();
          System.out.println("INITED");
        */
        relapi = NmsUtil.relapi;
        tapi = NmsUtil.relapi.getTransactionAPI();

        sampleHandler = new SampleHandler();
        maintainer = new NotificationCacheMaintainer();
    }

    private void process()
    {
        System.out.println("--------------------------------------------------------------------------------");
        testCase001();
        System.out.println("--------------------------------------------------------------------------------");
        testCase002();
        System.out.println("--------------------------------------------------------------------------------");
        testCase003();
        System.out.println("--------------------------------------------------------------------------------");
        testCase004();
        System.out.println("--------------------------------------------------------------------------------");
        testCase005();
        System.out.println("--------------------------------------------------------------------------------");
        testCase006();
        System.out.println("--------------------------------------------------------------------------------");
        testCase007();
        System.out.println("--------------------------------------------------------------------------------");
        testCase007_1();
        System.out.println("--------------------------------------------------------------------------------");
        testCase008();
        System.out.println("--------------------------------------------------------------------------------");
        testCase009();
        System.out.println("--------------------------------------------------------------------------------");
        testCase010();
        System.out.println("--------------------------------------------------------------------------------"); 
    }

    private void testCase001()
    {
        System.out.println(" DBTxnNCM_01_10 Testcase : DB-TXN-NCM-001 :: Enter");
        try
        {
            tapi.begin();
            tapi.commit();
            System.out.println("Commit called before registering. So, no Notification.");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        tapi.register(sampleHandler);
        System.out.println("TransactionHandler instance registered.");
        try
        {
            tapi.begin();
            tapi.commit();
            System.out.println("Commit called after register. Notification should be recieved.");
            tapi.begin();
            tapi.commit();
            System.out.println("Commit called after register. Notification should be recieved.");
            tapi.begin();
            tapi.commit();
            System.out.println("Commit called after register. Notification should be recieved.");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        tapi.unregister(sampleHandler);
        System.out.println(" DBTxnNCM_01_10 Testcase : DB-TXN-NCM-001 :: Exit");
    }

    private void testCase002()
    {
        System.out.println(" DBTxnNCM_01_10 Testcase : DB-TXN-NCM-002 :: Enter");
        tapi.register(sampleHandler);
        try
        {
            tapi.begin();
            tapi.commit();
            System.out.println("Commit called before unregister. Notification will be recieved.");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        tapi.unregister(sampleHandler);
        System.out.println("TransactionHandler instance unregistered.");
        try
        {
            tapi.begin();
            tapi.commit();
            System.out.println("Commit called after unregister. Notification should not be recieved.");
            tapi.begin();
            tapi.commit();
            System.out.println("Commit called after unregister. Notification should not be recieved.");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        System.out.println(" DBTxnNCM_01_10 Testcase : DB-TXN-NCM-002 :: Exit");
    }

    private void testCase003()
    {
        System.out.println(" DBTxnNCM_01_10 Testcase : DB-TXN-NCM-003 :: Enter");
        try
        {
            tapi.begin();
            System.out.println("TransactionAPI's begin method called (First).");
            tapi.begin();
            System.out.println("TransactionAPI's begin method called again (nested begin).");
            tapi.register(sampleHandler);
            System.out.println("TransactionHandler instance registered.");
            tapi.commit();
            System.out.println("Commit called. Nested commit. So, no Notification.");
            tapi.commit();
            System.out.println("Outermost Commit called. Notification should be recieved.");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        tapi.unregister(sampleHandler);
        System.out.println(" DBTxnNCM_01_10 Testcase : DB-TXN-NCM-003 :: Exit");
    }

    private void testCase004()
    {
        System.out.println(" DBTxnNCM_01_10 Testcase : DB-TXN-NCM-004 :: Enter");
        try
        {
            tapi.begin();
            System.out.println("TransactionAPI's begin method called (First).");
            tapi.begin();
            System.out.println("TransactionAPI's begin method called again (nested begin).");
            tapi.register(sampleHandler);
            System.out.println("TransactionHandler instance registered.");
            try{            
                tapi.rollback();
            }
            catch(Exception ee){}
            System.out.println("Rollback called. Nested rollback. Even then notification will be send. So, Notification should be received.");
            try{            
                tapi.rollback();
            }
            catch(Exception ee){}
            
            System.out.println("Outermost Rollback called. No Notification, since notification already recieved.");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        tapi.unregister(sampleHandler);
        System.out.println(" DBTxnNCM_01_10 Testcase : DB-TXN-NCM-004 :: Exit");
    }

    private void testCase005()
    {
        System.out.println(" DBTxnNCM_01_10 Testcase : DB-TXN-NCM-005 :: Enter");
        try
        {
            Thread context = Thread.currentThread();
            maintainer.addNotificationCache(sampleHandler, "DB-TXN-NCM-005");
            Vector v = maintainer.getNotificationCache(sampleHandler, context);
            System.out.println("The object added in Notification cache is " + v);
            if (v != null && v.size() == 1 && ((String)v.elementAt(0)).trim().equals("DB-TXN-NCM-005"))
            {
                System.out.println(" DBTxnNCM_01_10 Testcase : DB-TXN-NCM-005 :: PASSED");
            } 
            else
            {
                System.out.println(" DBTxnNCM_01_10 Testcase : DB-TXN-NCM-005 :: FAILED");
            }
            maintainer.removeNotificationCache(sampleHandler, context);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        System.out.println(" DBTxnNCM_01_10 Testcase : DB-TXN-NCM-005 :: Exit");
    }

    private void testCase006()
    {
        System.out.println(" DBTxnNCM_01_10 Testcase : DB-TXN-NCM-006 :: Enter");
        try
        {
            Thread context = Thread.currentThread();
            maintainer.addNotificationCache(sampleHandler, "DB-TXN-NCM-006A");
            maintainer.addNotificationCache(sampleHandler, "DB-TXN-NCM-006B");
            Vector v = maintainer.getNotificationCache(sampleHandler, context);
            if (v != null && v.size() == 2 && ((String)v.elementAt(0)).trim().equals("DB-TXN-NCM-006A") && ((String)v.elementAt(1)).trim().equals("DB-TXN-NCM-006B"))
            {
                System.out.println(" DBTxnNCM_01_10 Testcase : DB-TXN-NCM-006 :: PASSED");
            } 
            else
            {
                System.out.println(" DBTxnNCM_01_10 Testcase : DB-TXN-NCM-006 :: FAILED");
            }
            maintainer.removeNotificationCache(sampleHandler, context);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        System.out.println(" DBTxnNCM_01_10 Testcase : DB-TXN-NCM-006 :: Exit");
    }

    private void testCase007()
    {
        System.out.println(" DBTxnNCM_01_10 Testcase : DB-TXN-NCM-007 :: Enter");
        try
        {
            Thread context = Thread.currentThread();
            maintainer.addNotificationCache(sampleHandler, "DB-TXN-NCM-007");
            maintainer.removeNotificationCache(sampleHandler, context);
            Vector v = maintainer.getNotificationCache(sampleHandler, context);
            if (v == null || v.size() == 0)
            {
                System.out.println(" DBTxnNCM_01_10 Testcase : DB-TXN-NCM-007 :: PASSED");
            } 
            else
            {
                System.out.println(" DBTxnNCM_01_10 Testcase : DB-TXN-NCM-007 :: FAILED");
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        System.out.println(" DBTxnNCM_01_10 Testcase : DB-TXN-NCM-007 :: Exit");
    }

    private void testCase007_1()
    {
        System.out.println(" DBTxnNCM_01_10 Testcase : DB-TXN-NCM-007.1 :: Enter");
        try
        {
            Thread context = Thread.currentThread();
            maintainer.addNotificationCache(sampleHandler, "DB-TXN-NCM-007.1");
            Vector v = maintainer.removeNotificationCache(sampleHandler, context);
            if (v != null && v.size() == 1 && ((String)v.elementAt(0)).trim().equals("DB-TXN-NCM-007.1")) 
            {
                System.out.println(" DBTxnNCM_01_10 Testcase : DB-TXN-NCM-007.1 :: PASSED");
            } 
            else
            {
                System.out.println(" DBTxnNCM_01_10 Testcase : DB-TXN-NCM-007.1 :: FAILED");
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        System.out.println(" DBTxnNCM_01_10 Testcase : DB-TXN-NCM-007.1 :: Exit");
    }

    private void testCase008()
    {
        System.out.println(" DBTxnNCM_01_10 Testcase : DB-TXN-NCM-008 :: Enter");
        try
        {
            Thread context = Thread.currentThread();
            maintainer.addNotificationCache(sampleHandler, "DB-TXN-NCM-008");
            Vector v = maintainer.getNotificationCache(sampleHandler, context);
            maintainer.removeNotificationCache(sampleHandler, context);
            Vector v1 = maintainer.getNotificationCache(sampleHandler, context);
            if (v != null && v.size() == 1 && ((String)v.elementAt(0)).trim().equals("DB-TXN-NCM-008") 
                && (v1 == null || v1.size() == 0)) 
            {
                System.out.println(" DBTxnNCM_01_10 Testcase : DB-TXN-NCM-008 :: PASSED");
            } 
            else
            {
                System.out.println(" DBTxnNCM_01_10 Testcase : DB-TXN-NCM-008 :: FAILED");
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        System.out.println(" DBTxnNCM_01_10 Testcase : DB-TXN-NCM-008 :: Exit");
    }

    private void testCase009()
    {
        System.out.println(" DBTxnNCM_01_10 Testcase : DB-TXN-NCM-009 :: Enter");
        try
        {
            Thread context = Thread.currentThread();
            tapi.begin();
            tapi.commit();
            System.out.println("Transaction begun and committed. No notification, since no registeration.");
            tapi.begin();
            tapi.addTransactionHandler(sampleHandler, context);
            tapi.commit();
            System.out.println("TransactionHandler instance added for this transaction alone. So, notification is send and the handler instance is removed");
            tapi.begin();
            tapi.commit();
            System.out.println("Transaction begun and committed. No notification, since no registeration.");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        System.out.println(" DBTxnNCM_01_10 Testcase : DB-TXN-NCM-009 :: Exit");
    }

    private void testCase010()
    {
        System.out.println(" DBTxnNCM_01_10 Testcase : DB-TXN-NCM-010 :: Enter");
        try
        {
            Thread context = Thread.currentThread();
            tapi.begin();
            tapi.commit();
            System.out.println("Transaction begun and committed. No notification, since no registeration.");
            tapi.begin();
            tapi.addTransactionHandler(sampleHandler, context);
            tapi.removeTransactionHandler(sampleHandler, context);
            tapi.commit();
            System.out.println("TransactionHandler instance added and removed for this transaction alone. So, no Notification.");
            tapi.begin();
            tapi.commit();
            System.out.println("Transaction begun and committed. No notification, since no registeration.");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        System.out.println(" DBTxnNCM_01_10 Testcase : DB-TXN-NCM-010 :: Exit");
    }

    public static void main(String[] args) throws Exception
    {
        nmsdir = args[0];
        DBTxnNCM_01_10 tht = new DBTxnNCM_01_10();
        tht.init(args);
        try
        {
            //Thread.sleep(60000);
        }
        catch(Exception e)
        {
        }
        tht.process();
    }

}


class SampleHandler implements TransactionHandler
{

    public void notifyCommit(Thread context)
    {
        System.out.println("In SampleHandler notifyCommit() with thread reference as " + context);
    }

    public void notifyRollback(Thread context)
    {
        System.out.println("In SampleHandler notifyRollback() with thread reference as " + context);
    }
}
