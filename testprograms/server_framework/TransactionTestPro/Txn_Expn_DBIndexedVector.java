// $Id: Txn_Expn_DBIndexedVector.java,v 1.1 2002/06/25 06:49:30 vasus Exp $

/**
 * Txn_Expn_DBIndexedVector.java
 *
 *
 * Created: Tue Apr 30 11:16:01 2002
 *
 * @author Chitrapandian N
 * @version
 */

package txn;

import java.sql.PreparedStatement;

import com.adventnet.nms.store.DBIndexedVector;
import com.adventnet.nms.store.relational.RelationalAPI;
import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.util.RunProcessInterface;
import com.adventnet.management.transaction.TransactionAPI;
import com.adventnet.management.transaction.ConnectionPool;
import com.adventnet.management.transaction.UserTransactionException;

/**
 * Entry for testing DBIndexedVector in NmsProcessesBE.conf as,
 * <pre>
 * # DBIndexedVector's UserTransactionException Testing !
 * PROCESS   txn.Txn_Expn_DBIndexedVector
 * ARGS      NULL
 *
 * @author Chitrapandian N
 * @version 1.0
 * @since AdventNet WebNMS 2.3 SP (Transaction Special Operation) 
 * @see RunProcessInterface
 */
public class Txn_Expn_DBIndexedVector implements RunProcessInterface     
{
    private TransactionAPI txnAPI = null;
    private DBIndexedVector dbv = null;
    RelationalAPI relapi;

    private boolean initialized = false;

    String tableName = "TxnDBIndexedVector";

    public Txn_Expn_DBIndexedVector ()
    {
    }
   
    public void callMain (String[] args)
    {
        // This method is called by the NMS server during startup.
        init (args);
        initialized = true;	// This is very important	
        try
        {
            Thread.sleep(120000);
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
        dbv = new DBIndexedVector(tableName,null,true);
        dbv.setMaxValue(0);
        System.out.println(" New DBIndexedVector == TxnDBIndexedVector == is CREATED !");
    }

    private void process ()
    {
        System.out.println(" ========= Testing STARTED for "
                           +"Txn_Expn_DBIndexedVector  ========");
        testcase024();
        testcase025();
        testcase026();
        testcase027();
        testcase028();
        testcase029();
        testcase030();
        testcase031();
        testcase032();
        testcase033();
        testcase034();
        testcase035();
        System.out.println("========= All TEST CASES Completed for "
                           +"Txn_Expn_DBIndexedVector  ========\n");
    }


    private void testcase024()
    {
        System.out.println(" Txn_Expn_DBIndexedVector Testcase == addElement : "
                           +"DB-TXN-EXP-024 :: ENTER");
        try
        {
			dbv.addElement("12" );
            dbv.addElement("1" );
            dbv.addElement("2" );
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                dbv.addElement("24" );
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_DBIndexedVector Testcase == addElement: "
                               +"DB-TXN-EXP-024 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBIndexedVector Testcase == addElement : "
                               +"DB-TXN-EXP-024 :: PASSED");
            System.err.println(" Txn_Expn_DBIndexedVector addElement DB-TXN-EXP-024 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBIndexedVector Testcase == addElement: "
                               +"DB-TXN-EXP-024 :: FAILED");
            System.err.println(" Txn_Expn_DBIndexedVector addElement DB-TXN-EXP-024 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    }

    private void testcase025()
    {
        System.out.println(" Txn_Expn_DBIndexedVector Testcase == insertElementAt : "
                           +"DB-TXN-EXP-025 :: ENTER");
        try
        {
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                dbv.insertElementAt("25",2);
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_DBIndexedVector Testcase == insertElementAt: "
                               +"DB-TXN-EXP-025 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBIndexedVector Testcase == insertElementAt : "
                               +"DB-TXN-EXP-025 :: PASSED");
            System.err.println(" Txn_Expn_DBIndexedVector insertElementAt DB-TXN-EXP-025 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBIndexedVector Testcase == insertElementAt: "
                               +"DB-TXN-EXP-025 :: FAILED");
            System.err.println(" Txn_Expn_DBIndexedVector insertElementAt DB-TXN-EXP-025 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    }
 
    private void testcase026()
    {
        System.out.println(" Txn_Expn_DBIndexedVector Testcase == removeElement : "
                           +"DB-TXN-EXP-026 :: ENTER");
        try
        {
			dbv.addElement("6" );
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                dbv.removeElement("6" );
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_DBIndexedVector Testcase == removeElement: "
                               +"DB-TXN-EXP-026 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBIndexedVector Testcase == removeElement : "
                               +"DB-TXN-EXP-026 :: PASSED");
            System.err.println(" Txn_Expn_DBIndexedVector removeElement DB-TXN-EXP-026 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBIndexedVector Testcase == removeElement: "
                               +"DB-TXN-EXP-026 :: FAILED");
            System.err.println(" Txn_Expn_DBIndexedVector removeElement DB-TXN-EXP-026 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    }
  
    private void testcase027()
    {
        System.out.println(" Txn_Expn_DBIndexedVector Testcase == removeAllElements : "
                           +"DB-TXN-EXP-027 :: ENTER");
        try
        {
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                dbv.removeAllElements();
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_DBIndexedVector Testcase == removeAllElements: "
                               +"DB-TXN-EXP-027 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBIndexedVector Testcase == removeAllElements : "
                               +"DB-TXN-EXP-027 :: PASSED");
            System.err.println(" Txn_Expn_DBIndexedVector removeAllElements DB-TXN-EXP-027 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBIndexedVector Testcase == removeAllElements: "
                               +"DB-TXN-EXP-027 :: FAILED");
            System.err.println(" Txn_Expn_DBIndexedVector removeAllElements DB-TXN-EXP-027 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    }
 
    private void testcase028()
    {
        System.out.println(" Txn_Expn_DBIndexedVector Testcase == removeElementAt : "
                           +"DB-TXN-EXP-028 :: ENTER");
        try
        {
			dbv.addElement("7");
			dbv.addElement("8");
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                dbv.removeElementAt(1);
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_DBIndexedVector Testcase == removeElementAt: "
                               +"DB-TXN-EXP-028 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBIndexedVector Testcase == removeElementAt : "
                               +"DB-TXN-EXP-028 :: PASSED");
            System.err.println(" Txn_Expn_DBIndexedVector removeElementAt DB-TXN-EXP-028 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBIndexedVector Testcase == removeElementAt: "
                               +"DB-TXN-EXP-028 :: FAILED");
            System.err.println(" Txn_Expn_DBIndexedVector removeElementAt DB-TXN-EXP-028 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    }
 
    private void testcase029()
    {
        System.out.println(" Txn_Expn_DBIndexedVector Testcase == size : "
                           +"DB-TXN-EXP-029 :: ENTER");
        try
        {
			dbv.addElement("9");
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                dbv.size();
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_DBIndexedVector Testcase == size: "
                               +"DB-TXN-EXP-029 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBIndexedVector Testcase == size : "
                               +"DB-TXN-EXP-029 :: PASSED");
            System.err.println(" Txn_Expn_DBIndexedVector size DB-TXN-EXP-029 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBIndexedVector Testcase == size: "
                               +"DB-TXN-EXP-029 :: FAILED");
            System.err.println(" Txn_Expn_DBIndexedVector size DB-TXN-EXP-029 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    }

    private void testcase030()
    {
        System.out.println(" Txn_Expn_DBIndexedVector Testcase == clear : "
                           +"DB-TXN-EXP-030 :: ENTER");
        try
        {
			dbv.addElement("30");
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                dbv.clear();
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_DBIndexedVector Testcase == clear: "
                               +"DB-TXN-EXP-030 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBIndexedVector Testcase == clear : "
                               +"DB-TXN-EXP-030 :: PASSED");
            System.err.println(" Txn_Expn_DBIndexedVector clear DB-TXN-EXP-030 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBIndexedVector Testcase == clear: "
                               +"DB-TXN-EXP-030 :: FAILED");
            System.err.println(" Txn_Expn_DBIndexedVector clear DB-TXN-EXP-030 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    }

    private void testcase031()
    {
        System.out.println(" Txn_Expn_DBIndexedVector Testcase == contains : "
                           +"DB-TXN-EXP-031 :: ENTER");
        try
        {
			dbv.addElement("31");
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                dbv.contains("31");
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_DBIndexedVector Testcase == contains: "
                               +"DB-TXN-EXP-031 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBIndexedVector Testcase == contains : "
                               +"DB-TXN-EXP-031 :: PASSED");
            System.err.println(" Txn_Expn_DBIndexedVector contains DB-TXN-EXP-031 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBIndexedVector Testcase == contains: "
                               +"DB-TXN-EXP-031 :: FAILED");
            System.err.println(" Txn_Expn_DBIndexedVector contains DB-TXN-EXP-031 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    }
 
 
    private void testcase032()
    {
        System.out.println(" Txn_Expn_DBIndexedVector Testcase == restore : "
                           +"DB-TXN-EXP-032 :: ENTER");
        try
        {
			dbv.addElement("32");
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                dbv.restore();
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_DBIndexedVector Testcase == restore: "
                               +"DB-TXN-EXP-032 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBIndexedVector Testcase == restore : "
                               +"DB-TXN-EXP-032 :: PASSED");
            System.err.println(" Txn_Expn_DBIndexedVector restore DB-TXN-EXP-032 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBIndexedVector Testcase == restore: "
                               +"DB-TXN-EXP-032 :: FAILED");
            System.err.println(" Txn_Expn_DBIndexedVector restore DB-TXN-EXP-032 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    }

    private void testcase033()
    {
        System.out.println(" Txn_Expn_DBIndexedVector Testcase == elementAt : "
                           +"DB-TXN-EXP-033 :: ENTER");
        try
        {
			dbv.addElement("33");
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                dbv.elementAt(1);
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_DBIndexedVector Testcase == elementAt: "
                               +"DB-TXN-EXP-033 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBIndexedVector Testcase == elementAt : "
                               +"DB-TXN-EXP-033 :: PASSED");
            System.err.println(" Txn_Expn_DBIndexedVector elementAt DB-TXN-EXP-033 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBIndexedVector Testcase == elementAt: "
                               +"DB-TXN-EXP-033 :: FAILED");
            System.err.println(" Txn_Expn_DBIndexedVector elementAt DB-TXN-EXP-033 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    }

    private void testcase034()
    {
        System.out.println(" Txn_Expn_DBIndexedVector Testcase == indexOf : "
                           +"DB-TXN-EXP-034 :: ENTER");
        try
        {
			dbv.addElement("34");
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                dbv.indexOf("34");
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_DBIndexedVector Testcase == indexOf: "
                               +"DB-TXN-EXP-034 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBIndexedVector Testcase == indexOf : "
                               +"DB-TXN-EXP-034 :: PASSED");
            System.err.println(" Txn_Expn_DBIndexedVector indexOf DB-TXN-EXP-034 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBIndexedVector Testcase == indexOf: "
                               +"DB-TXN-EXP-034 :: FAILED");
            System.err.println(" Txn_Expn_DBIndexedVector indexOf DB-TXN-EXP-034 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    }
 
    private void testcase035()
    {
        System.out.println(" Txn_Expn_DBIndexedVector Testcase == get : "
                           +"DB-TXN-EXP-035 :: ENTER");
        try
        {
			dbv.addElement("35");
            txnAPI.begin(100);
            {
                Thread.sleep(200);
                dbv.get(1);
            }
            txnAPI.commit();
            System.out.println("Txn_Expn_DBIndexedVector Testcase == get: "
                               +"DB-TXN-EXP-035 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Txn_Expn_DBIndexedVector Testcase == get : "
                               +"DB-TXN-EXP-035 :: PASSED");
            System.err.println(" Txn_Expn_DBIndexedVector get DB-TXN-EXP-035 :: "
                               +"UserTransactionException Received :: \n" +ute);
        }
        catch(Exception e)
        {
            System.out.println(" Txn_Expn_DBIndexedVector Testcase == get: "
                               +"DB-TXN-EXP-035 :: FAILED");
            System.err.println(" Txn_Expn_DBIndexedVector get DB-TXN-EXP-035 ::"
                               +" Exception Received :: \n" +e);
            e.printStackTrace();
        }
    }
} 

//Txn_Expn_DBIndexedVector.java
