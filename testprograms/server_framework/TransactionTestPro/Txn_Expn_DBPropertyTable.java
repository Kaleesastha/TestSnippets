// $Id: Txn_Expn_DBPropertyTable.java,v 1.1 2002/06/25 06:49:30 vasus Exp $

/**
 * Txn_Expn_DBPropertyTable.java
 *
 *
 * Created: Mon Apr 29 16:32:41 2002
 *
 * @author Chitrapandian N
 * @version
 */

package txn;

import java.util.Properties;
import java.sql.PreparedStatement;

import com.adventnet.nms.store.DBPropertytable;
import com.adventnet.nms.store.relational.RelationalAPI;
import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.util.RunProcessInterface;
import com.adventnet.management.transaction.TransactionAPI;
import com.adventnet.management.transaction.ConnectionPool;
import com.adventnet.management.transaction.UserTransactionException;

/**
 * Entry for testing DBPropertytable in NmsProcessesBE.conf as,
 * <pre>
 * # DBPropertytable's UserTransactionException Testing !
 * PROCESS   txn.Txn_Expn_DBPropertyTable
 * ARGS      NULL
 *
 * @author Chitrapandian N
 * @since AdventNet WebNMS 2.3 SP (Transaction Special Operation) 
 * @see RunProcessInterface
 */
public class Txn_Expn_DBPropertyTable implements RunProcessInterface
{
    private TransactionAPI txnAPI = null;
    private DBPropertytable dbv = null;
    RelationalAPI relapi;

    private boolean initialized = false;

    String tableName = "TxnDBPropertytable";

    public Txn_Expn_DBPropertyTable ()
    {
    }
   
    public void callMain (String[] args)
    {
        // This method is called by the NMS server during startup.
        init (args);
        initialized = true;	// This is very important	
        try
        {
            Thread.sleep(60000);
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
        dbv = new DBPropertytable(tableName, "KEYSTRING", "PROPKEY",
                                  "PROPVALUE", false, null, true);
        dbv.setMaxValue(0);
        System.out.println(" New DBPropertytable == TxnDBPropertytable == is added !");
    }

    private void process ()
    {
        System.out.println(" ========= Testing STARTED for "
                           +"Txn_Expn_DBPropertyTable ========");
        testcase045();   // get(String key) METHOD !!
        testcase046();   // getProperty(String key) METHOD !!
        testcase047();   // put(String key, Properties prop) METHOD !!
        testcase048();   // size() METHOD !!
        testcase049();   // isEmpty() METHOD !!
        testcase050();   // clear() METHOD !!  
        testcase051();   // remove(String key) METHOD !!
        testcase052();   // contains(Properties prop) METHOD !!
        testcase053();   // containsValue(Properties prop) METHOD !!
        testcase054();   // containsKey(String value) METHOD !!
        testcase055();   // restore() METHOD !!
        testcase055_1(); // keys() METHOD !!
        testcase055_2(); // elements() METHOD !!
        System.out.println("========= All TEST CASES Completed for "
                           +"Txn_Expn_DBPropertyTable ========\n");
    }


    private void testcase045()
    {
        System.out.println(" Txn_Expn_DBPropertyTable Testcase == get : DB-TXN-EXP-045 :: ENTER");
        try
        {
            Properties prop = new Properties();
            dbv.put("DBPVTxnGet", prop);
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                dbv.get("DBPVTxnGet");
            }
            txnAPI.commit();
            System.out.println(" Txn_Expn_DBPropertyTable get Testcase : "
                               +"DB-TXN-EXP-045 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBPropertyTable Testcase == get : "
                               +"DB-TXN-EXP-045 :: PASSED");
            System.err.println(" Txn_Expn_DBPropertyTable get DB-TXN-EXP-045 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBPropertyTable get Testcase : "
                               +"DB-TXN-EXP-045 :: FAILED");
            System.err.println(" Txn_Expn_DBPropertyTable get DB-TXN-EXP-045 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    } 

    private void testcase046()
    {
        System.out.println(" Txn_Expn_DBPropertyTable Testcase == getProperty : DB-TXN-EXP-046 :: ENTER");
        try
        {
            Properties prop = new Properties();
            prop.put("Key046","Value046");
            dbv.put("DBPVTxnGetProperty", prop);
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                dbv.getProperty("DBPVTxnGetProperty");
            }
            txnAPI.commit();
            System.out.println(" Txn_Expn_DBPropertyTable Testcase == getProperty : "
                               +"DB-TXN-EXP-046 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBPropertyTable Testcase == getProperty : "
                               +"DB-TXN-EXP-046 :: PASSED");
            System.err.println(" Txn_Expn_DBPropertyTable getProperty DB-TXN-EXP-046 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBPropertyTable getProperty Testcase : "
                               +"DB-TXN-EXP-046 :: FAILED");
            System.err.println(" Txn_Expn_DBPropertyTable getProperty DB-TXN-EXP-046 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    } 

    private void testcase047()
    {
        System.out.println(" Txn_Expn_DBPropertyTable Testcase == put : DB-TXN-EXP-047 :: ENTER");
        try
        {
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                Properties prop = new Properties();
                prop.put("Key047","Value047");
                dbv.put("DBPVTxnPut", prop);
            }
            txnAPI.commit();
            System.out.println(" Txn_Expn_DBPropertyTable put Testcase : "
                               +"DB-TXN-EXP-047 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBPropertyTable Testcase == put : "
                               +"DB-TXN-EXP-047 :: PASSED");
            System.err.println(" Txn_Expn_DBPropertyTable put DB-TXN-EXP-047 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBPropertyTable put Testcase : "
                               +"DB-TXN-EXP-047 :: FAILED");
            System.err.println(" Txn_Expn_DBPropertyTable put DB-TXN-EXP-047 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    } 

    private void testcase048()
    {
        System.out.println(" Txn_Expn_DBPropertyTable Testcase == size : DB-TXN-EXP-048 :: ENTER");
        try
        {
            Properties prop = new Properties();
            prop.put("Key048","Value048");
            dbv.put("DBPVTxnSize", prop);
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                dbv.size();
            }
            txnAPI.commit();
            System.out.println(" Txn_Expn_DBPropertyTable size Testcase : "
                               +"DB-TXN-EXP-048 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBPropertyTable Testcase == size : "
                               +"DB-TXN-EXP-048 :: PASSED");
            System.err.println(" Txn_Expn_DBPropertyTable size DB-TXN-EXP-048 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBPropertyTable size Testcase : "
                               +"DB-TXN-EXP-048 :: FAILED");
            System.err.println(" Txn_Expn_DBPropertyTable size DB-TXN-EXP-048 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    } 

    private void testcase049()
    {
        System.out.println(" Txn_Expn_DBPropertyTable Testcase == isEmpty : DB-TXN-EXP-049 :: ENTER");
        try
        {
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                dbv.isEmpty();
            }
            txnAPI.commit();
            System.out.println(" Txn_Expn_DBPropertyTable isEmpty Testcase : "
                               +"DB-TXN-EXP-049 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBPropertyTable Testcase == isEmpty : "
                               +"DB-TXN-EXP-049 :: PASSED");
            System.err.println(" Txn_Expn_DBPropertyTable isEmpty DB-TXN-EXP-049 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBPropertyTable isEmpty Testcase : "
                               +"DB-TXN-EXP-049 :: FAILED");
            System.err.println(" Txn_Expn_DBPropertyTable isEmpty DB-TXN-EXP-049 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    } 

    private void testcase050()
    {
        System.out.println(" Txn_Expn_DBPropertyTable Testcase == clear : DB-TXN-EXP-050 :: ENTER");
        try
        {
            Properties prop = new Properties();
            prop.put("Key050","Value050");
            dbv.put("DBPVTxnClear", prop);
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                dbv.clear();
            }
            txnAPI.commit();
            System.out.println(" Txn_Expn_DBPropertyTable clear Testcase : "
                               +"DB-TXN-EXP-050 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBPropertyTable Testcase == clear : "
                               +"DB-TXN-EXP-050 :: PASSED");
            System.err.println(" Txn_Expn_DBPropertyTable clear DB-TXN-EXP-050 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBPropertyTable clear Testcase : "
                               +"DB-TXN-EXP-050 :: FAILED");
            System.err.println(" Txn_Expn_DBPropertyTable clear DB-TXN-EXP-050 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    } 

    private void testcase051()
    {
        System.out.println(" Txn_Expn_DBPropertyTable Testcase == remove : DB-TXN-EXP-051 :: ENTER");
        try
        {
            Properties prop = new Properties();
            prop.put("Key051","Value051");
            dbv.put("DBPVTxnRemove", prop);
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                dbv.remove("Key051");
            }
            txnAPI.commit();
            System.out.println(" Txn_Expn_DBPropertyTable remove Testcase : "
                               +"DB-TXN-EXP-051 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBPropertyTable Testcase == remove : "
                               +"DB-TXN-EXP-051 :: PASSED");
            System.err.println(" Txn_Expn_DBPropertyTable remove DB-TXN-EXP-051 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBPropertyTable remove Testcase : "
                               +"DB-TXN-EXP-051 :: FAILED");
            System.err.println(" Txn_Expn_DBPropertyTable remove DB-TXN-EXP-051 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    } 

    private void testcase052()
    {
        System.out.println(" Txn_Expn_DBPropertyTable Testcase == contains : DB-TXN-EXP-052 :: ENTER");
        try
        {
            Properties prop = new Properties();
            prop.put("Key052","Value052");
            dbv.put("DBPVTxnContains", prop);
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                dbv.contains(prop);
            }
            txnAPI.commit();
            System.out.println(" Txn_Expn_DBPropertyTable contains Testcase : "
                               +"DB-TXN-EXP-052 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBPropertyTable Testcase == contains : "
                               +"DB-TXN-EXP-052 :: PASSED");
            System.err.println(" Txn_Expn_DBPropertyTable contains DB-TXN-EXP-052 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBPropertyTable contains Testcase : "
                               +"DB-TXN-EXP-052 :: FAILED");
            System.err.println(" Txn_Expn_DBPropertyTable contains DB-TXN-EXP-052 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    } 

    private void testcase053()
    {
        System.out.println(" Txn_Expn_DBPropertyTable Testcase == containsValue : DB-TXN-EXP-053 :: ENTER");
        try
        {
            Properties prop = new Properties();
            prop.put("Key053","Value053");
            dbv.put("DBPVTxnContainsValue", prop);
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                dbv.containsValue(prop);
            }
            txnAPI.commit();
            System.out.println(" Txn_Expn_DBPropertyTable containsValue Testcase : "
                               +"DB-TXN-EXP-053 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBPropertyTable Testcase == containsValue : "
                               +"DB-TXN-EXP-053 :: PASSED");
            System.err.println(" Txn_Expn_DBPropertyTable containsValue DB-TXN-EXP-053 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBPropertyTable containsValue Testcase : "
                               +"DB-TXN-EXP-053 :: FAILED");
            System.err.println(" Txn_Expn_DBPropertyTable containsValue DB-TXN-EXP-053 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    } 

    private void testcase054()
    {
        System.out.println(" Txn_Expn_DBPropertyTable Testcase == containsKey : DB-TXN-EXP-054 :: ENTER");
        try
        {
            Properties prop = new Properties();
            prop.put("Key054","Value054");
            dbv.put("DBPVTxnContainsKey", prop);
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                dbv.containsKey("DBPVTxnContainsKey");
            }
            txnAPI.commit();
            System.out.println(" Txn_Expn_DBPropertyTable containsKey Testcase : "
                               +"DB-TXN-EXP-054 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBPropertyTable Testcase == containsKey : "
                               +"DB-TXN-EXP-054 :: PASSED");
            System.err.println(" Txn_Expn_DBPropertyTable containsKey DB-TXN-EXP-054 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBPropertyTable containsKey Testcase : "
                               +"DB-TXN-EXP-054 :: FAILED");
            System.err.println(" Txn_Expn_DBPropertyTable containsKey DB-TXN-EXP-054 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    } 


    private void testcase055()
    {
        System.out.println(" Txn_Expn_DBPropertyTable Testcase == restore : DB-TXN-EXP-055 :: ENTER");
        try
        {
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                dbv.restore();
            }
            txnAPI.commit();
            System.out.println(" Txn_Expn_DBPropertyTable restore Testcase : "
                               +"DB-TXN-EXP-055 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBPropertyTable Testcase == restore : "
                               +"DB-TXN-EXP-055 :: PASSED");
            System.err.println(" Txn_Expn_DBPropertyTable restore DB-TXN-EXP-055 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBPropertyTable restore Testcase : "
                               +"DB-TXN-EXP-055 :: FAILED");
            System.err.println(" Txn_Expn_DBPropertyTable restore DB-TXN-EXP-055 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    } 

    private void testcase055_1()
    {
        System.out.println(" Txn_Expn_DBPropertyTable Testcase == keys : DB-TXN-EXP-055.1 :: ENTER");
        try
        {
            Properties prop = new Properties();
            prop.put("Key055_1","Value055_1");
            dbv.put("DBPVTxnKeys", prop);
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                dbv.keys();
            }
            txnAPI.commit();
            System.out.println(" Txn_Expn_DBPropertyTable keys Testcase : "
                               +"DB-TXN-EXP-055.1 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBPropertyTable Testcase == keys : "
                               +"DB-TXN-EXP-055.1 :: PASSED");
            System.err.println(" Txn_Expn_DBPropertyTable keys DB-TXN-EXP-055.1 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBPropertyTable keys Testcase : "
                               +"DB-TXN-EXP-055.1 :: FAILED");
            System.err.println(" Txn_Expn_DBPropertyTable keys DB-TXN-EXP-055.1 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    } 

    private void testcase055_2()
    {
        System.out.println(" Txn_Expn_DBPropertyTable Testcase == elements : DB-TXN-EXP-055.2 :: ENTER");
        try
        {
            Properties prop = new Properties();
            prop.put("Key055_2","Value055_2");
            dbv.put("DBPVTxnElements", prop);
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                dbv.elements();
            }
            txnAPI.commit();
            System.out.println(" Txn_Expn_DBPropertyTable elements Testcase : "
                               +"DB-TXN-EXP-055.2 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBPropertyTable Testcase == elements : "
                               +"DB-TXN-EXP-055.2 :: PASSED");
            System.err.println(" Txn_Expn_DBPropertyTable elements DB-TXN-EXP-055.2 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBPropertyTable elements Testcase : "
                               +"DB-TXN-EXP-055.2 :: FAILED");
            System.err.println(" Txn_Expn_DBPropertyTable elements DB-TXN-EXP-055.2 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    } 

}

//Txn_Expn_DBPropertyTable.java
