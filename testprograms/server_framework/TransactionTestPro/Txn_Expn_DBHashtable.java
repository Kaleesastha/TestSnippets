// $Id: Txn_Expn_DBHashtable.java,v 1.1 2002/06/25 06:49:30 vasus Exp $

/**
 * Txn_Expn_DBHashtable.java
 *
 *
 * Created: Tue Apr 30 10:46:01 2002
 *
 * @author Chitrapandian N
 * @version
 */

package txn;

import java.sql.PreparedStatement;

import com.adventnet.nms.store.DBHashtable;
import com.adventnet.nms.store.relational.RelationalAPI;
import com.adventnet.nms.store.relational.RelationalAPI;
import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.util.RunProcessInterface;
import com.adventnet.management.transaction.TransactionAPI;
import com.adventnet.management.transaction.ConnectionPool;
import com.adventnet.management.transaction.UserTransactionException;

/**
 * Entry for testing DBHashtable in NmsProcessesBE.conf as,
 * <pre>
 * # DBHashtable's UserTransactionException Testing !
 * PROCESS   txn.Txn_Expn_DBHashtable
 * ARGS      NULL
 *
 * @author Chitrapandian N
 * @since AdventNet WebNMS 2.3 SP (Transaction Special Operation) 
 * @see RunProcessInterface
 */
public class Txn_Expn_DBHashtable implements RunProcessInterface      
{
	TransactionAPI txnAPI = null;
	DBHashtable dbv = null;
    RelationalAPI relapi;

    String tableName = "TxnDBHashtable" ;

    private boolean initialized = false;

    public Txn_Expn_DBHashtable ()
    {
    }
   
    public void callMain (String[] args)
    {
        // This method is called by the NMS server during startup.
        init (args);
        initialized = true;	// This is very important	
        try
        {
            Thread.sleep(80000);
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
        dbv = new DBHashtable(tableName,"NAME","VALUE",false,null,true);
        dbv.setMaxValue(0);
        System.out.println(" New DBHashtable == TxnDBHashtabe == CREATED !");
    }

    private void process ()
    {
        System.out.println(" ========= Testing STARTED for "
                           +"Txn_Expn_DBHashtable ========");
        testcase001();
        testcase002();
        testcase003();
        testcase004();
        testcase005();
        testcase006();
        testcase007();
        testcase008();
        testcase009();
        testcase010();
        testcase011();
        testcase012();
        testcase013();
        System.out.println("========= All TEST CASES Completed for "
                           +"Txn_Expn_DBHashtable ========\n");
    }

    private void testcase001()
    {
        System.out.println(" Txn_Expn_DBHashtable Testcase == get : DB-TXN-EXP-001 :: ENTER");
        try
        {
            dbv.put("name1" ,"TxnGet001_1" );
            dbv.put("name2" ,"TxnGet001_2" );
            dbv.put("name3" ,"TxnGet001_3" );
            dbv.put("name4" ,"TxnGet001_4" );
            dbv.put("name5" ,"TxnGet001_5" );
			txnAPI.begin(100);
            {
                Thread.sleep(200);
                dbv.get("name1");
            }
			txnAPI.commit();
            System.out.println("Txn_Expn_DBHashtable Testcase == get: "
                               +"DB-TXN-EXP-001 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBHashtable Testcase == get : "
                               +"DB-TXN-EXP-001 :: PASSED");
            System.err.println(" Txn_Expn_DBHashtable get DB-TXN-EXP-001 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBHashtable Testcase == get: "
                               +"DB-TXN-EXP-001 :: FAILED");
            System.err.println(" Txn_Expn_DBHashtable get DB-TXN-EXP-001 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    }

    private void testcase002()
    {
        System.out.println(" Txn_Expn_DBHashtable Testcase == getProperty : "
                           +"DB-TXN-EXP-002 :: ENTER");
        try
        {
            txnAPI.begin(100);
            {
                Thread.sleep(1200);
                dbv.getProperty("name1");
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_DBHashtable Testcase == getProperty: "
                               +"DB-TXN-EXP-002 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBHashtable Testcase == getProperty : "
                               +"DB-TXN-EXP-002 :: PASSED");
            System.err.println(" Txn_Expn_DBHashtable getProperty DB-TXN-EXP-002 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBHashtable Testcase == getProperty: "
                               +"DB-TXN-EXP-002 :: FAILED");
            System.err.println(" Txn_Expn_DBHashtable getProperty DB-TXN-EXP-002 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    }


    private void testcase003()
    {
        System.out.println(" Txn_Expn_DBHashtable Testcase == add : "
                           +"DB-TXN-EXP-003 :: ENTER");
        try
        {
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                dbv.add("name003","TxnAdd003");
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_DBHashtable Testcase == add: "
                               +"DB-TXN-EXP-003 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBHashtable Testcase == add : "
                               +"DB-TXN-EXP-003 :: PASSED");
            System.err.println(" Txn_Expn_DBHashtable add DB-TXN-EXP-003 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBHashtable Testcase == add: "
                               +"DB-TXN-EXP-003 :: FAILED");
            System.err.println(" Txn_Expn_DBHashtable add DB-TXN-EXP-003 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    }
 
 
    private void testcase004()
    {
        System.out.println(" Txn_Expn_DBHashtable Testcase == update : "
                           +"DB-TXN-EXP-004 :: ENTER");
        try
        {
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                dbv.update("name003","TxnUpdate004");
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_DBHashtable Testcase == update: "
                               +"DB-TXN-EXP-004 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBHashtable Testcase == update : "
                               +"DB-TXN-EXP-004 :: PASSED");
            System.err.println(" Txn_Expn_DBHashtable update DB-TXN-EXP-004 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBHashtable Testcase == update: "
                               +"DB-TXN-EXP-004 :: FAILED");
            System.err.println(" Txn_Expn_DBHashtable update DB-TXN-EXP-004 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    }
  
    private void testcase005()
    {
        System.out.println(" Txn_Expn_DBHashtable Testcase == put : "
                           +"DB-TXN-EXP-005 :: ENTER");
        try
        {
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                dbv.put("name005","TxnPut005");
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_DBHashtable Testcase == put: "
                               +"DB-TXN-EXP-005 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBHashtable Testcase == put : "
                               +"DB-TXN-EXP-005 :: PASSED");
            System.err.println(" Txn_Expn_DBHashtable put DB-TXN-EXP-005 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBHashtable Testcase == put: "
                               +"DB-TXN-EXP-005 :: FAILED");
            System.err.println(" Txn_Expn_DBHashtable put DB-TXN-EXP-005 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    }
 
    private void testcase006()
    {
        System.out.println(" Txn_Expn_DBHashtable Testcase == size : "
                           +"DB-TXN-EXP-006 :: ENTER");
        try
        {
            dbv.put("name006","TxnSize006");
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                dbv.size();
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_DBHashtable Testcase == size: "
                               +"DB-TXN-EXP-006 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBHashtable Testcase == size : "
                               +"DB-TXN-EXP-006 :: PASSED");
            System.err.println(" Txn_Expn_DBHashtable size DB-TXN-EXP-006 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBHashtable Testcase == size: "
                               +"DB-TXN-EXP-006 :: FAILED");
            System.err.println(" Txn_Expn_DBHashtable size DB-TXN-EXP-006 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    }
 
    private void testcase007()
    {
        System.out.println(" Txn_Expn_DBHashtable Testcase == isEmpty : "
                           +"DB-TXN-EXP-007 :: ENTER");
        try
        {
            dbv.put("name007","TxnIsEmpty007");
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                dbv.isEmpty();
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_DBHashtable Testcase == isEmpty: "
                               +"DB-TXN-EXP-007 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBHashtable Testcase == isEmpty : "
                               +"DB-TXN-EXP-007 :: PASSED");
            System.err.println(" Txn_Expn_DBHashtable isEmpty DB-TXN-EXP-007 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBHashtable Testcase == isEmpty: "
                               +"DB-TXN-EXP-007 :: FAILED");
            System.err.println(" Txn_Expn_DBHashtable isEmpty DB-TXN-EXP-007 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    }
 
    private void testcase008()
    {
        System.out.println(" Txn_Expn_DBHashtable Testcase == clear : "
                           +"DB-TXN-EXP-008 :: ENTER");
        try
        {
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                dbv.clear();
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_DBHashtable Testcase == clear: "
                               +"DB-TXN-EXP-008 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBHashtable Testcase == clear : "
                               +"DB-TXN-EXP-008 :: PASSED");
            System.err.println(" Txn_Expn_DBHashtable clear DB-TXN-EXP-008 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBHashtable Testcase == clear: "
                               +"DB-TXN-EXP-008 :: FAILED");
            System.err.println(" Txn_Expn_DBHashtable clear DB-TXN-EXP-008 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    }
 
    private void testcase009()
    {
        System.out.println(" Txn_Expn_DBHashtable Testcase == remove : "
                           +"DB-TXN-EXP-009 :: ENTER");
        try
        {
            dbv.put("name009","TxnRemove009");
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                dbv.remove("name009");
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_DBHashtable Testcase == remove: "
                               +"DB-TXN-EXP-009 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBHashtable Testcase == remove : "
                               +"DB-TXN-EXP-009 :: PASSED");
            System.err.println(" Txn_Expn_DBHashtable remove DB-TXN-EXP-009 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBHashtable Testcase == remove: "
                               +"DB-TXN-EXP-009 :: FAILED");
            System.err.println(" Txn_Expn_DBHashtable remove DB-TXN-EXP-009 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    }
 
    private void testcase010()
    {
        System.out.println(" Txn_Expn_DBHashtable Testcase == contains : "
                           +"DB-TXN-EXP-010 :: ENTER");
        try
        {
            dbv.put("name010","TxnContains010");
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                dbv.contains("name010");
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_DBHashtable Testcase == contains: "
                               +"DB-TXN-EXP-010 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBHashtable Testcase == contains : "
                               +"DB-TXN-EXP-010 :: PASSED");
            System.err.println(" Txn_Expn_DBHashtable contains DB-TXN-EXP-010 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBHashtable Testcase == contains: "
                               +"DB-TXN-EXP-010 :: FAILED");
            System.err.println(" Txn_Expn_DBHashtable contains DB-TXN-EXP-010 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    }
  
    private void testcase011()
    {
        System.out.println(" Txn_Expn_DBHashtable Testcase == containsValue : "
                           +"DB-TXN-EXP-011 :: ENTER");
        try
        {
            dbv.put("name011","TxnContainsValue011");
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                dbv.containsValue("TxnContainsValue011");
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_DBHashtable Testcase == containsValue: "
                               +"DB-TXN-EXP-011 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBHashtable Testcase == containsValue : "
                               +"DB-TXN-EXP-011 :: PASSED");
            System.err.println(" Txn_Expn_DBHashtable containsValue DB-TXN-EXP-011 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBHashtable Testcase == containsValue: "
                               +"DB-TXN-EXP-011 :: FAILED");
            System.err.println(" Txn_Expn_DBHashtable containsValue DB-TXN-EXP-011 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    }
 
    private void testcase012()
    {
        System.out.println(" Txn_Expn_DBHashtable Testcase == containsKey : "
                           +"DB-TXN-EXP-012 :: ENTER");
        try
        {
            dbv.put("name012","TxnContainsValue012");
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                dbv.containsKey("name012");
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_DBHashtable Testcase == containsKey: "
                               +"DB-TXN-EXP-012 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBHashtable Testcase == containsKey : "
                               +"DB-TXN-EXP-012 :: PASSED");
            System.err.println(" Txn_Expn_DBHashtable containsKey DB-TXN-EXP-012 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBHashtable Testcase == containsKey: "
                               +"DB-TXN-EXP-012 :: FAILED");
            System.err.println(" Txn_Expn_DBHashtable containsKey DB-TXN-EXP-012 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    }
 
    private void testcase013()
    {
        System.out.println(" Txn_Expn_DBHashtable Testcase == restore : "
                           +"DB-TXN-EXP-013 :: ENTER");
        try
        {
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                dbv.restore();
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_DBHashtable Testcase == restore: "
                               +"DB-TXN-EXP-013 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBHashtable Testcase == restore : "
                               +"DB-TXN-EXP-013 :: PASSED");
            System.err.println(" Txn_Expn_DBHashtable restore DB-TXN-EXP-013 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBHashtable Testcase == restore: "
                               +"DB-TXN-EXP-013 :: FAILED");
            System.err.println(" Txn_Expn_DBHashtable restore DB-TXN-EXP-013 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    }

} 

//Txn_Expn_DBHashtable.java
