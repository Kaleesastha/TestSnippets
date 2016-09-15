// $Id: Txn_Expn_DBVectorTable.java,v 1.1 2002/06/25 06:49:30 vasus Exp $

/**
 * Txn_Expn_DBVectorTable.java
 *
 *
 * Created: Tue Apr 30 11:23:11 2002
 *
 * @author Chitrapandian N
 * @version
 */

package txn;

import java.util.Vector;
import java.sql.PreparedStatement;

import com.adventnet.nms.store.DBVectortable;
import com.adventnet.nms.store.relational.RelationalAPI;
import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.util.RunProcessInterface;
import com.adventnet.management.transaction.TransactionAPI;
import com.adventnet.management.transaction.ConnectionPool;
import com.adventnet.management.transaction.UserTransactionException;

/**
 * Entry for testing DBVectortable in NmsProcessesBE.conf as,
 * <pre>
 * # DBVectortable's UserTransactionException Testing !
 * PROCESS   txn.Txn_Expn_DBVectorTable
 * ARGS      NULL
 *
 * @author Chitrapandian N
 * @since AdventNet WebNMS 2.3 SP (Transaction Special Operation) 
 * @see RunProcessInterface
 */
public class Txn_Expn_DBVectorTable implements RunProcessInterface     
{
    private TransactionAPI txnAPI = null;
    private DBVectortable dbv = null;
    RelationalAPI relapi;

    private boolean initialized = false;

    String tableName = "TxnDBVectorTable";

    public Txn_Expn_DBVectorTable ()
    {
    }
   
    public void callMain (String[] args)
    {
        // This method is called by the NMS server during startup.
        init (args);
        initialized = true;	// This is very important	
        try
        {
            Thread.sleep(100000);
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
        dbv = new DBVectortable(tableName,"NAME","value",false,null,true);
        dbv.setMaxValue(0);
        System.out.println(" New DBVectortable == Txndbvectortable == is CREATED !");
    }

    private void process ()
    {
        System.out.println(" ========= Testing STARTED for "
                           +"Txn_Expn_DBVectorTable  ========");
        testcase056();
        testcase056_1();
        testcase056_2();
        testcase57();
        testcase58();
        testcase59();
        testcase60();
        testcase61();	
        testcase62();	
        testcase63();	
        testcase64();
        testcase65();
        //testcase66();
        testcase67();
        testcase68();
        testcase69();
        System.out.println("========= All TEST CASES Completed for "
                           +"Txn_Expn_DBVectorTable  ========\n");
    }

    private void testcase056()
    {
        System.out.println(" Txn_Expn_DBVectorTable keys Testcase : "
                           +"DB-TXN-EXP-056 :: ENTER");
        try
        {
            Vector v = new Vector();
			dbv.addPut("TxnVT056",v);
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                dbv.keys();
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_DBVectorTable keys Testcase : "
                               +"DB-TXN-EXP-056 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBVectorTable Testcase keys : "
                               +"DB-TXN-EXP-056 :: PASSED");
            System.err.println(" Txn_Expn_DBVectorTable keys DB-TXN-EXP-056 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBVectorTable keys Testcase : "
                               +"DB-TXN-EXP-056 :: FAILED");
            System.err.println(" Txn_Expn_DBVectorTable keys DB-TXN-EXP-056 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    } 

    private void testcase056_1()
    {
        System.out.println(" Txn_Expn_DBVectorTable elements Testcase : "
                           +"DB-TXN-EXP-056.1 :: ENTER");
        try
        {
            Vector v = new Vector();
			dbv.addPut("TxnVT056_1",v);
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                dbv.elements();
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_DBVectorTable elements Testcase : "
                               +"DB-TXN-EXP-056.1 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBVectorTable Testcase elements : "
                               +"DB-TXN-EXP-056.1 :: PASSED");
            System.err.println(" Txn_Expn_DBVectorTable elements DB-TXN-EXP-056.1 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBVectorTable elements Testcase : "
                               +"DB-TXN-EXP-056.1 :: FAILED");
            System.err.println(" Txn_Expn_DBVectorTable elements DB-TXN-EXP-056.1 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    } 

    private void testcase056_2()
    {
        System.out.println(" Txn_Expn_DBVectorTable get Testcase : "
                           +"DB-TXN-EXP-056.2 :: ENTER");
        try
        {
            Vector v = new Vector();
			dbv.addPut("TxnVT056_2",v);
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                dbv.get("TxnVT056_2");
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_DBVectorTable get Testcase : "
                               +"DB-TXN-EXP-056.2 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBVectorTable Testcase get : "
                               +"DB-TXN-EXP-056.2 :: PASSED");
            System.err.println(" Txn_Expn_DBVectorTable get DB-TXN-EXP-056.2 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBVectorTable get Testcase : "
                               +"DB-TXN-EXP-056.2 :: FAILED");
            System.err.println(" Txn_Expn_DBVectorTable get DB-TXN-EXP-056.2 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    } 

    private void testcase57()
    {
        System.out.println(" Txn_Expn_DBVectorTable getProperty Testcase : "
                           +"DB-TXN-EXP-057 :: ENTER");
        try
        {
            Vector v = new Vector();
			dbv.addPut("TxnVT057",v);
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                dbv.getProperty("TxnVT057");
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_DBVectorTable getProperty Testcase : "
                               +"DB-TXN-EXP-057 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBVectorTable Testcase getProperty : "
                               +"DB-TXN-EXP-057 :: PASSED");
            System.err.println(" Txn_Expn_DBVectorTable getProperty DB-TXN-EXP-057 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBVectorTable getProperty Testcase : "
                               +"DB-TXN-EXP-057 :: FAILED");
            System.err.println(" Txn_Expn_DBVectorTable getProperty DB-TXN-EXP-057 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    } 

    private void testcase58()
    {
        System.out.println(" Txn_Expn_DBVectorTable put Testcase : "
                           +"DB-TXN-EXP-058 :: ENTER");
        try
        {
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                Vector v = new Vector();
                dbv.put("TxnVT058",v);
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_DBVectorTable put Testcase : "
                               +"DB-TXN-EXP-058 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBVectorTable Testcase put : "
                               +"DB-TXN-EXP-058 :: PASSED");
            System.err.println(" Txn_Expn_DBVectorTable put DB-TXN-EXP-058 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBVectorTable put Testcase : "
                               +"DB-TXN-EXP-058 :: FAILED");
            System.err.println(" Txn_Expn_DBVectorTable put DB-TXN-EXP-058 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    } 

    private void testcase59()
    {
        System.out.println(" Txn_Expn_DBVectorTable addPut Testcase : "
                           +"DB-TXN-EXP-059 :: ENTER");
        try
        {
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                Vector v = new Vector();
                dbv.addPut("TxnVT059",v);
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_DBVectorTable addPut Testcase : "
                               +"DB-TXN-EXP-059 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBVectorTable Testcase addPut : "
                               +"DB-TXN-EXP-059 :: PASSED");
            System.err.println(" Txn_Expn_DBVectorTable addPut DB-TXN-EXP-059 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBVectorTable addPut Testcase : "
                               +"DB-TXN-EXP-059 :: FAILED");
            System.err.println(" Txn_Expn_DBVectorTable addPut DB-TXN-EXP-059 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    } 

    private void testcase60()
    {
        System.out.println(" Txn_Expn_DBVectorTable size Testcase : "
                           +"DB-TXN-EXP-060 :: ENTER");
        try
        {
            Vector v = new Vector();
            dbv.put("TxnVT060",v);
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                dbv.size();
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_DBVectorTable size Testcase : "
                               +"DB-TXN-EXP-060 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBVectorTable Testcase size : "
                               +"DB-TXN-EXP-060 :: PASSED");
            System.err.println(" Txn_Expn_DBVectorTable size DB-TXN-EXP-060 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBVectorTable size Testcase : "
                               +"DB-TXN-EXP-060 :: FAILED");
            System.err.println(" Txn_Expn_DBVectorTable size DB-TXN-EXP-060 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    } 

    private void testcase61()
    {
        System.out.println(" Txn_Expn_DBVectorTable isEmpty Testcase : "
                           +"DB-TXN-EXP-061 :: ENTER");
        try
        {
            Vector v = new Vector();
            dbv.put("TxnVT061",v);
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                dbv.isEmpty();
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_DBVectorTable isEmpty Testcase : "
                               +"DB-TXN-EXP-061 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBVectorTable Testcase isEmpty : "
                               +"DB-TXN-EXP-061 :: PASSED");
            System.err.println(" Txn_Expn_DBVectorTable isEmpty DB-TXN-EXP-061 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBVectorTable isEmpty Testcase : "
                               +"DB-TXN-EXP-061 :: FAILED");
            System.err.println(" Txn_Expn_DBVectorTable isEmpty DB-TXN-EXP-061 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    } 


    private void testcase62()
    {
        System.out.println(" Txn_Expn_DBVectorTable clear Testcase : "
                           +"DB-TXN-EXP-062 :: ENTER");
        try
        {
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                dbv.clear();
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_DBVectorTable clear Testcase : "
                               +"DB-TXN-EXP-062 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBVectorTable Testcase clear : "
                               +"DB-TXN-EXP-062 :: PASSED");
            System.err.println(" Txn_Expn_DBVectorTable clear DB-TXN-EXP-062 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBVectorTable clear Testcase : "
                               +"DB-TXN-EXP-062 :: FAILED");
            System.err.println(" Txn_Expn_DBVectorTable clear DB-TXN-EXP-062 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    } 


    private void testcase63()
    {
        System.out.println(" Txn_Expn_DBVectorTable remove Testcase : "
                           +"DB-TXN-EXP-063 :: ENTER");
        try
        {
            Vector v = new Vector();
            dbv.put("TxnVT063",v);
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                dbv.remove("TxnVT063");
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_DBVectorTable remove Testcase : "
                               +"DB-TXN-EXP-063 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBVectorTable Testcase remove : "
                               +"DB-TXN-EXP-063 :: PASSED");
            System.err.println(" Txn_Expn_DBVectorTable remove DB-TXN-EXP-063 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBVectorTable remove Testcase : "
                               +"DB-TXN-EXP-063 :: FAILED");
            System.err.println(" Txn_Expn_DBVectorTable remove DB-TXN-EXP-063 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    } 


    private void testcase64()
    {
        System.out.println(" Txn_Expn_DBVectorTable remove Testcase : "
                           +"DB-TXN-EXP-064 :: ENTER");
        try
        {
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                dbv.remove("name","DBelement");
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_DBVectorTable remove Testcase : "
                               +"DB-TXN-EXP-064 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBVectorTable Testcase remove : "
                               +"DB-TXN-EXP-064 :: PASSED");
            System.err.println(" Txn_Expn_DBVectorTable remove DB-TXN-EXP-064 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBVectorTable remove Testcase : "
                               +"DB-TXN-EXP-064 :: FAILED");
            System.err.println(" Txn_Expn_DBVectorTable remove DB-TXN-EXP-064 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    } 

    private void testcase65()
    {
        System.out.println(" Txn_Expn_DBVectorTable contains Testcase : "
                           +"DB-TXN-EXP-065 :: ENTER");
        try
        {
            Vector v = new Vector();
            dbv.put("TxnVT065",v);
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                dbv.contains("TxnVT065");
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_DBVectorTable contains Testcase : "
                               +"DB-TXN-EXP-065 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBVectorTable Testcase contains : "
                               +"DB-TXN-EXP-065 :: PASSED");
            System.err.println(" Txn_Expn_DBVectorTable contains DB-TXN-EXP-065 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBVectorTable contains Testcase : "
                               +"DB-TXN-EXP-065 :: FAILED");
            System.err.println(" Txn_Expn_DBVectorTable contains DB-TXN-EXP-065 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    } 

    /*private void testcase66()
      {
 
      System.out.println("############ INTO TESTCASE DB-TXN-EXP-066 " );
 
      try{
      txnAPI.begin(100);
      {
      Thread.sleep(2000);
      dbv.containsValue("DBelement");
      }
      txnAPI.commit();
      System.out.println("Txn_Expn_DBVectorTable.containsValue(String) Testcase : DB-TXN-EXP-066 :: FAILED == END");
      }
      catch(UserTransactionException ute)
      {
      System.out.println("Txn_Expn_DBDBVectorTable.containsValue(String) Testcase : DB-TXN-EXP-066 :: PASSED");
      System.err.println
      (" Txn_Expn_DBHashtable DB-TXN-EXP-067 :: UserTransactionException Received :: \n"
      +ute);
 
      }catch(Exception e)
      {
      System.out.println(" Txn_Expn_DBVectorTable.containsValue(String) Testcase : DB-TXN-EXP-066 :: FAILED");
      System.err.println(" Txn_Expn_DBVectortable DB-TXN-EXP-066 :: Exception Received :: \n" +e);
      }
      } */ 

    private void testcase67()
    {
        System.out.println(" Txn_Expn_DBVectorTable containsKey Testcase : "
                           +"DB-TXN-EXP-067 :: ENTER");
        try
        {
            Vector v = new Vector();
            dbv.put("TxnVT067",v);
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                dbv.containsKey("TxnVT067");
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_DBVectorTable containsKey Testcase : "
                               +"DB-TXN-EXP-067 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBVectorTable Testcase containsKey : "
                               +"DB-TXN-EXP-067 :: PASSED");
            System.err.println(" Txn_Expn_DBVectorTable containsKey DB-TXN-EXP-067 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBVectorTable containsKey Testcase : "
                               +"DB-TXN-EXP-067 :: FAILED");
            System.err.println(" Txn_Expn_DBVectorTable containsKey DB-TXN-EXP-067 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    } 

    private void testcase68()
    {
        System.out.println(" Txn_Expn_DBVectorTable restore Testcase : "
                           +"DB-TXN-EXP-068 :: ENTER");
        try
        {
            Vector v = new Vector();
            dbv.put("TxnVT068",v);
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                dbv.restore();
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_DBVectorTable restore Testcase : "
                               +"DB-TXN-EXP-068 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBVectorTable Testcase restore : "
                               +"DB-TXN-EXP-068 :: PASSED");
            System.err.println(" Txn_Expn_DBVectorTable restore DB-TXN-EXP-068 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBVectorTable restore Testcase : "
                               +"DB-TXN-EXP-068 :: FAILED");
            System.err.println(" Txn_Expn_DBVectorTable restore DB-TXN-EXP-068 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    } 

    private void testcase69()
    {
        System.out.println(" Txn_Expn_DBVectorTable deleteEntriesEndingWith Testcase : "
                           +"DB-TXN-EXP-069 :: ENTER");
        try
        {
            Vector v = new Vector();
            dbv.put("TxnVT069e",v);
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                dbv.deleteEntriesEndingWith("e");
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_DBVectorTable deleteEntriesEndingWith Testcase : "
                               +"DB-TXN-EXP-069 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBVectorTable Testcase deleteEntriesEndingWith : "
                               +"DB-TXN-EXP-069 :: PASSED");
            System.err.println(" Txn_Expn_DBVectorTable deleteEntriesEndingWith DB-TXN-EXP-069 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBVectorTable deleteEntriesEndingWith Testcase : "
                               +"DB-TXN-EXP-069 :: FAILED");
            System.err.println(" Txn_Expn_DBVectorTable deleteEntriesEndingWith DB-TXN-EXP-069 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    } 
}


