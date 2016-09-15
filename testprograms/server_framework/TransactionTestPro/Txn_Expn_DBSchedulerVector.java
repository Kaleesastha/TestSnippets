// $Id: Txn_Expn_DBSchedulerVector.java,v 1.1 2002/06/25 06:49:30 vasus Exp $

/**
 * Txn_Expn_DBSchedulerVector.java
 *
 *
 * Created: Fri Apr 26 16:46:01 2002
 *
 * @author Chitrapandian N
 * @version
 */

package txn;

import java.sql.PreparedStatement;

import com.adventnet.nms.store.DBSchedulerVector;
import com.adventnet.nms.store.relational.RelationalAPI;
import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.util.RunProcessInterface;
import com.adventnet.management.transaction.TransactionAPI;
import com.adventnet.management.transaction.ConnectionPool;
import com.adventnet.management.transaction.UserTransactionException;

/**
 * Entry for testing DBSchedulerVector in NmsProcessesBE.conf as,
 * <pre>
 * # DBSchedulerVector's UserTransactionException Testing !
 * PROCESS   txn.Txn_Expn_DBSchedulerVector
 * ARGS      NULL
 *
 * @author Chitrapandian N
 * @since AdventNet WebNMS 2.3 SP (Transaction Special Operation) 
 * @see RunProcessInterface
 */
public class Txn_Expn_DBSchedulerVector implements RunProcessInterface
{
    private TransactionAPI txnAPI = null;
    private DBSchedulerVector dbv = null;
    RelationalAPI relapi;

    private boolean initialized = false;

    String tableName = "TxnDBSchVector";

    public Txn_Expn_DBSchedulerVector ()
    {
    }
   
    public void callMain (String[] args)
    {
        // This method is called by the NMS server during startup.
        init (args);
        initialized = true;	// This is very important	
        try
        {
            Thread.sleep(40000);
        }
        catch(Exception e){}
        process ();	
    }

    public boolean isInitialized ()
    {
        return initialized;
    }

    public void shutDown ()
    {
        // handle shutdown for the process
        dropTable(tableName);
    }

    private void dropTable(String tableName)
    {
        try
        {
            String query = "drop table "+tableName;
            PreparedStatement ps = relapi.getPreparedStatement(query);
            ps.executeUpdate();
            ps.close();
            System.out.println(" <<==== TABLE "+tableName+" DROPPED ===>> ");
        }
        catch(Exception e)
        {
            System.err.println("Exception while trying to dropping table "+tableName+" "+e);
        }
	
    }

    private void init (String[] args)
    {
        // do process specific initialization
        relapi = NmsUtil.relapi;
        txnAPI = NmsUtil.relapi.getTransactionAPI();
        dbv = new DBSchedulerVector(tableName,null,true);
        dbv.setMaxValue(0);
        System.out.println(" New DBSchedularVector == TxnDBSchVector == is added !");
    }

    private void process ()
    {
        System.out.println(" ========= Testing STARTED for "
                           +"Txn_Expn_DBSchedulerVector ========");
        testcase036(); // addElement(long time, String key) METHOD !!
        testcase037(); // removeElement(String element) METHOD !!
        testcase038(); // removeElement(long value) METHOD !!
        testcase039(); // removeAllElements() METHOD !!
        testcase040(); // clear() METHOD !!
        testcase041(); // restore() METHOD !!
        testcase042(); // elements(long value) METHOD !!
        testcase043(); // getMinElement() METHOD !!
        testcase044(); // getMinElement(long value) METHOD !!
        System.out.println("========= All TEST CASES Completed for "
                           +"Txn_Expn_DBSchedulerVector ========\n");
    }

    private void testcase036()
    {
        System.out.println(" Txn_Expn_DBSchedulerVector addElement Testcase : DB-TXN-EXP-036 :: ENTER");
        try
        {
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                dbv.addElement(1036, "DBSchVecTxnAdd");
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_DBSchedulerVector addElement Testcase : "
                               +"DB-TXN-EXP-036 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBSchedulerVector Testcase addElement : "
                               +"DB-TXN-EXP-036 :: PASSED");
            System.err.println(" Txn_Expn_DBSchedulerVector addElement DB-TXN-EXP-036 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBSchedulerVector addElement Testcase : "
                               +"DB-TXN-EXP-036 :: FAILED");
            System.err.println(" Txn_Expn_DBSchedulerVector addElement DB-TXN-EXP-036 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    } 

    private void testcase037()
    {
        System.out.println(" Txn_Expn_DBSchedulerVector removeElement(Str) Testcase : DB-TXN-EXP-037 :: ENTER");
        try
        {
            dbv.addElement(1037, "TxnRemoveString");
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                dbv.removeElement("TxnRemoveString");
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_DBSchedulerVector removeElement(Str) Testcase : "
                               +"DB-TXN-EXP-037 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBSchedulerVector Testcase removeElement(Str) : "
                               +"DB-TXN-EXP-037 :: PASSED");
            System.err.println(" Txn_Expn_DBSchedulerVector removeElement(Str) DB-TXN-EXP-037 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBSchedulerVector removeElement(Str) Testcase : "
                               +"DB-TXN-EXP-037 :: FAILED");
            System.err.println(" Txn_Expn_DBSchedulerVector removeElement(Str) DB-TXN-EXP-037 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    } 

    private void testcase038()
    {
        System.out.println(" Txn_Expn_DBSchedulerVector removeElement(long) Testcase : DB-TXN-EXP-038 :: ENTER");
        try
        {
            dbv.addElement(1038, "TxnRemoveLong");
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                dbv.removeElement(1038);
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_DBSchedulerVector removeElement(long) Testcase : "
                               +"DB-TXN-EXP-038 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBSchedulerVector Testcase removeElement(long) : "
                               +"DB-TXN-EXP-038 :: PASSED");
            System.err.println(" Txn_Expn_DBSchedulerVector removeElement(long) DB-TXN-EXP-038 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBSchedulerVector removeElement(long) Testcase : "
                               +"DB-TXN-EXP-038 :: FAILED");
            System.err.println(" Txn_Expn_DBSchedulerVector removeElement(long) DB-TXN-EXP-038 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    } 

    private void testcase039()
    {
        System.out.println(" Txn_Expn_DBSchedulerVector removeAllElements Testcase : DB-TXN-EXP-039 :: ENTER");
        try
        {
            dbv.addElement(1039, "TxnRemoveAllElements");
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                dbv.removeAllElements();
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_DBSchedulerVector removeAllElements Testcase : "
                               +"DB-TXN-EXP-039 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBSchedulerVector Testcase removeAllElements : "
                               +"DB-TXN-EXP-039 :: PASSED");
            System.err.println(" Txn_Expn_DBSchedulerVector removeAllElements DB-TXN-EXP-039 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBSchedulerVector removeAllElements Testcase : "
                               +"DB-TXN-EXP-039 :: FAILED");
            System.err.println(" Txn_Expn_DBSchedulerVector removeAllElements DB-TXN-EXP-039 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    } 

    private void testcase040()
    {
        System.out.println(" Txn_Expn_DBSchedulerVector clear Testcase : DB-TXN-EXP-040 :: ENTER");
        try
        {
            dbv.addElement(1040, "TxnSchClear");
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                dbv.clear();
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_DBSchedulerVector clear Testcase : "
                               +"DB-TXN-EXP-040 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBSchedulerVector Testcase clear : "
                               +"DB-TXN-EXP-040 :: PASSED");
            System.err.println(" Txn_Expn_DBSchedulerVector clear DB-TXN-EXP-040 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBSchedulerVector clear Testcase : "
                               +"DB-TXN-EXP-040 :: FAILED");
            System.err.println(" Txn_Expn_DBSchedulerVector clear DB-TXN-EXP-040 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    } 

    private void testcase041()
    {
        System.out.println(" Txn_Expn_DBSchedulerVector restore Testcase : DB-TXN-EXP-041 :: ENTER");
        try
        {
            dbv.addElement(1041, "TxnSchRestore");
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                dbv.restore();
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_DBSchedulerVector restore Testcase : "
                               +"DB-TXN-EXP-041 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBSchedulerVector Testcase restore : "
                               +"DB-TXN-EXP-041 :: PASSED");
            System.err.println(" Txn_Expn_DBSchedulerVector restore DB-TXN-EXP-041 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBSchedulerVector restore Testcase : "
                               +"DB-TXN-EXP-041 :: FAILED");
            System.err.println(" Txn_Expn_DBSchedulerVector restore DB-TXN-EXP-041 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    } 
    
    private void testcase042()
    {
        System.out.println(" Txn_Expn_DBSchedulerVector elements Testcase : DB-TXN-EXP-042 :: ENTER");
        try
        {
            dbv.addElement(1042, "TxnSchElements");
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                dbv.elements(1036);
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_DBSchedulerVector elements Testcase : "
                               +"DB-TXN-EXP-042 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBSchedulerVector Testcase elements : "
                               +"DB-TXN-EXP-042 :: PASSED");
            System.err.println(" Txn_Expn_DBSchedulerVector elements DB-TXN-EXP-042 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBSchedulerVector elements Testcase : "
                               +"DB-TXN-EXP-042 :: FAILED");
            System.err.println(" Txn_Expn_DBSchedulerVector elements DB-TXN-EXP-042 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    } 

    private void testcase043()
    {
        System.out.println(" Txn_Expn_DBSchedulerVector getMinElement Testcase : DB-TXN-EXP-043 :: ENTER");
        try
        {
            dbv.addElement(1043, "TxngetMinElement");
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                dbv.getMinElement();
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_DBSchedulerVector getMinElement Testcase : "
                               +"DB-TXN-EXP-043 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBSchedulerVector Testcase getMinElement : "
                               +"DB-TXN-EXP-043 :: PASSED");
            System.err.println(" Txn_Expn_DBSchedulerVector getMinElement DB-TXN-EXP-043 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBSchedulerVector getMinElement Testcase : "
                               +"DB-TXN-EXP-043 :: FAILED");
            System.err.println(" Txn_Expn_DBSchedulerVector getMinElement DB-TXN-EXP-043 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    } 

    private void testcase044()
    {
        System.out.println(" Txn_Expn_DBSchedulerVector getMinElement(long) Testcase : DB-TXN-EXP-044 :: ENTER");
        try
        {
            dbv.addElement(1014, "TxngetMinElement(long)");
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                dbv.getMinElement(1000);
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_DBSchedulerVector getMinElement(long) Testcase : "
                               +"DB-TXN-EXP-044 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBSchedulerVector Testcase getMinElement(long) : "
                               +"DB-TXN-EXP-044 :: PASSED");
            System.err.println(" Txn_Expn_DBSchedulerVector getMinElement(long) DB-TXN-EXP-044 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBSchedulerVector getMinElement(long) Testcase : "
                               +"DB-TXN-EXP-044 :: FAILED");
            System.err.println(" Txn_Expn_DBSchedulerVector getMinElement(long) DB-TXN-EXP-044 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    } 

}

//Txn_Expn_DBSchedulerVector.java

