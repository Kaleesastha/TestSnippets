// $Id: Txn_Expn_DBVector.java,v 1.1 2002/06/25 06:49:30 vasus Exp $

/**
 * Txn_Expn_DBVector.java
 *
 *
 * Created: Fri Apr 26 16:46:01 2002
 *
 * @author Chitrapandian N
 * @version
 */

package txn;

import java.sql.PreparedStatement;

import com.adventnet.nms.store.DBVector;
import com.adventnet.nms.store.relational.RelationalAPI;
import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.util.RunProcessInterface;
import com.adventnet.management.transaction.TransactionAPI;
import com.adventnet.management.transaction.ConnectionPool;
import com.adventnet.management.transaction.UserTransactionException;

/**
 * Entry for testing DBVector in NmsProcessesBE.conf as,
 * <pre>
 * # DBVector's UserTransactionException Testing !
 * PROCESS   txn.Txn_Expn_DBVector
 * ARGS      NULL
 *
 * @author Chitrapandian N
 * @since AdventNet WebNMS 2.3 SP (Transaction Special Operation) 
 * @see RunProcessInterface
 */
public class Txn_Expn_DBVector implements RunProcessInterface
{
    private TransactionAPI txnAPI = null;
    private DBVector dbv = null;
    RelationalAPI relapi;

    private boolean initialized = false;

    String tableName = "TxnDBVector";

    public Txn_Expn_DBVector ()
    {
    }
   
    public void callMain (String[] args)
    {
        // This method is called by the NMS server during startup.
        init (args);
        initialized = true;	// This is very important	
        try
        {
            Thread.sleep(20000);
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
        dbv = new DBVector(tableName,"NAME",false,null,true);
        dbv.setMaxValue(0);
        System.out.println(" New DBVector == TxnDBVector == is added !");
    }

    private void process ()
    {
        System.out.println(" ========= Testing STARTED for "
                           +"Txn_Expn_DBVector  ========");
        testcase014(); // addElement(String key) METHOD !!
        testcase015(); // removeElement(String element) METHOD !!
        testcase016(); // removeAllElements() METHOD !!
        testcase017(); // size() METHOD !!
        testcase018(); // isEmpty() METHOD !!
        testcase019(); // clear() METHOD !!
        testcase020(); // contains(String value) METHOD !!
        testcase021(); // restore() METHOD !!
        testcase022(); // elements() METHOD !!
        testcase023(); // indexOf(String element) METHOD !!
        System.out.println("========= All TEST CASES Completed for "
                           +"Txn_Expn_DBVector  ========\n");
    }

    // addElement(String key) METHOD !!
    private void testcase014()
    {
        System.out.println(" Txn_Expn_DBVector addElement Testcase : DB-TXN-EXP-014 :: ENTER");
        try
        {
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                dbv.addElement("DBVTxnAdd");
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_DBVector addElement Testcase : "
                               +"DB-TXN-EXP-014 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBVector Testcase addElement : "
                               +"DB-TXN-EXP-014 :: PASSED");
            System.err.println(" Txn_Expn_DBVector addElement DB-TXN-EXP-014 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBVector addElement Testcase : "
                               +"DB-TXN-EXP-014 :: FAILED");
            System.err.println(" Txn_Expn_DBVector addElement DB-TXN-EXP-014 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    } 

    private void testcase015()
    {
        System.out.println(" Txn_Expn_DBVector removeElement Testcase : DB-TXN-EXP-015 :: ENTER");
        try
        {
            dbv.addElement("TxnRemove");
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                dbv.removeElement("TxnRemove");
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_DBVector removeElement Testcase : "
                               +"DB-TXN-EXP-015 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBVector Testcase removeElement : "
                               +"DB-TXN-EXP-015 :: PASSED");
            System.err.println(" Txn_Expn_DBVector removeElement DB-TXN-EXP-015 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBVector removeElement Testcase : "
                               +"DB-TXN-EXP-015 :: FAILED");
            System.err.println(" Txn_Expn_DBVector removeElement DB-TXN-EXP-015 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    } 

    private void testcase016()
    {
        System.out.println(" Txn_Expn_DBVector removeAllElements Testcase : DB-TXN-EXP-016 :: ENTER");
        try
        {
            dbv.addElement("TxnRemoveAllElements");
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                dbv.removeAllElements();
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_DBVector removeAllElements Testcase : "
                               +"DB-TXN-EXP-016 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBVector Testcase removeAllElements : "
                               +"DB-TXN-EXP-016 :: PASSED");
            System.err.println(" Txn_Expn_DBVector removeAllElements DB-TXN-EXP-016 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBVector removeAllElements Testcase : "
                               +"DB-TXN-EXP-016 :: FAILED");
            System.err.println(" Txn_Expn_DBVector removeAllElements DB-TXN-EXP-016 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    } 

    private void testcase017()
    {
        System.out.println(" Txn_Expn_DBVector size Testcase : DB-TXN-EXP-017 :: ENTER");
        try
        {
            dbv.addElement("TxnSize");
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                dbv.size();
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_DBVector size Testcase : "
                               +"DB-TXN-EXP-017 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBVector Testcase size : "
                               +"DB-TXN-EXP-017 :: PASSED");
            System.err.println(" Txn_Expn_DBVector size DB-TXN-EXP-017 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBVector size Testcase : "
                               +"DB-TXN-EXP-017 :: FAILED");
            System.err.println(" Txn_Expn_DBVector size DB-TXN-EXP-017 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    } 

    private void testcase018()
    {
        System.out.println(" Txn_Expn_DBVector isEmpty Testcase : DB-TXN-EXP-018 :: ENTER");
        try
        {
            dbv.addElement("TxnIsEmpty");
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                dbv.isEmpty();
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_DBVector isEmpty Testcase : "
                               +"DB-TXN-EXP-018 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBVector Testcase isEmpty : "
                               +"DB-TXN-EXP-018 :: PASSED");
            System.err.println(" Txn_Expn_DBVector isEmpty DB-TXN-EXP-018 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBVector isEmpty Testcase : "
                               +"DB-TXN-EXP-018 :: FAILED");
            System.err.println(" Txn_Expn_DBVector isEmpty DB-TXN-EXP-018 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    } 

    private void testcase019()
    {
        System.out.println(" Txn_Expn_DBVector clear Testcase : DB-TXN-EXP-019 :: ENTER");
        try
        {
            dbv.addElement("TxnClear");
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                dbv.clear();
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_DBVector clear Testcase : "
                               +"DB-TXN-EXP-019 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBVector Testcase clear : "
                               +"DB-TXN-EXP-019 :: PASSED");
            System.err.println(" Txn_Expn_DBVector clear DB-TXN-EXP-019 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBVector clear Testcase : "
                               +"DB-TXN-EXP-019 :: FAILED");
            System.err.println(" Txn_Expn_DBVector clear DB-TXN-EXP-019 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    } 


    private void testcase020()
    {
        System.out.println(" Txn_Expn_DBVector contains Testcase : DB-TXN-EXP-020 :: ENTER");
        try
        {
            dbv.addElement("TxnContains");
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                dbv.contains("TxnContains");
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_DBVector contains Testcase : "
                               +"DB-TXN-EXP-020 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBVector Testcase contains : "
                               +"DB-TXN-EXP-020 :: PASSED");
            System.err.println(" Txn_Expn_DBVector contains DB-TXN-EXP-020 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBVector contains Testcase : "
                               +"DB-TXN-EXP-020 :: FAILED");
            System.err.println(" Txn_Expn_DBVector contains DB-TXN-EXP-020 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    } 

    private void testcase021()
    {
        System.out.println(" Txn_Expn_DBVector restore Testcase : DB-TXN-EXP-021 :: ENTER");
        try
        {
            dbv.addElement("TxnRestore");
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                dbv.restore();
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_DBVector restore Testcase : "
                               +"DB-TXN-EXP-021 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBVector Testcase restore : "
                               +"DB-TXN-EXP-021 :: PASSED");
            System.err.println(" Txn_Expn_DBVector restore DB-TXN-EXP-021 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBVector restore Testcase : "
                               +"DB-TXN-EXP-021 :: FAILED");
            System.err.println(" Txn_Expn_DBVector restore DB-TXN-EXP-021 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    } 


    private void testcase022()
    {
        System.out.println(" Txn_Expn_DBVector elements Testcase : DB-TXN-EXP-022 :: ENTER");
        try
        {
            dbv.addElement("TxnElements");
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                dbv.elements();
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_DBVector elements Testcase : "
                               +"DB-TXN-EXP-022 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBVector Testcase elements : "
                               +"DB-TXN-EXP-022 :: PASSED");
            System.err.println(" Txn_Expn_DBVector elements DB-TXN-EXP-022 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBVector elements Testcase : "
                               +"DB-TXN-EXP-022 :: FAILED");
            System.err.println(" Txn_Expn_DBVector elements DB-TXN-EXP-022 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    } 

    private void testcase023()
    {
        System.out.println(" Txn_Expn_DBVector indexOf Testcase : DB-TXN-EXP-023 :: ENTER");
        try
        {
            dbv.addElement("TxnIndexOf");
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                dbv.indexOf("TxnIndexOf");
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_DBVector indexOf Testcase : "
                               +"DB-TXN-EXP-023 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBVector Testcase indexOf : "
                               +"DB-TXN-EXP-023 :: PASSED");
            System.err.println(" Txn_Expn_DBVector indexOf DB-TXN-EXP-023 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBVector indexOf Testcase : "
                               +"DB-TXN-EXP-023 :: FAILED");
            System.err.println(" Txn_Expn_DBVector indexOf DB-TXN-EXP-023 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    } 

}

//Txn_Expn_DBVector.java
