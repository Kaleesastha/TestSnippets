package txn;
import com.adventnet.nms.topodb.*;
import com.adventnet.nms.eventdb.*;
import com.adventnet.nms.mapdb.*;
import com.adventnet.nms.alertdb.*;
import com.adventnet.nms.eventdb.Event;
import com.adventnet.management.transaction.*;
import com.adventnet.nms.store.*;
import java.sql.*;
import java.util.*;
import java.io.*;
import java.rmi.*;
import javax.transaction.RollbackException;
import com.adventnet.nms.util.*;
import com.adventnet.nms.store.relational.*;                                     
import javax.servlet.*; 
import javax.servlet.http.*; 
import com.adventnet.nms.jmxagent.AgentDefValObject; 

/**
 * Entry for testing UserStorageAPI in NmsProcessesBE.conf as,
 * <pre>
 * #UserStorageAPI UserTransactionException Testing !
 * PROCESS   txn.Txn_Expn_UserStorageAPI
 * ARGS      NULL
 *
 * @author Vasudevan.S
 * @since AdventNet WebNMS 2.3 SP (Transaction Special Operation)
 * @see RunProcessInterface
 */  
public class  Txn_Expn_UserStorageAPI implements RunProcessInterface     

{
    HttpServletRequest req = null;
	PrintWriter out = null;
	ConnectionPool cp = null;
	TopoAPI topo = null;
	TransactionAPI tranAPI = null;
	ManagedObject mo = null;
	String objname = null;
	UserStorageAPI usapi = null;
	AgentDefValObject obj = null;
	RelationalAPI relapi = null;
	Connection conn = null;	
	int thread2=0;

public static Thread t = Thread.currentThread();


public void exec()
{
			  try{
			  Thread.sleep(20000);	
			  }catch(Exception e)
			  {
			  	e.printStackTrace();
				}
			  initDBService();
              System.out.println("into testclear       " + usapi );
			  testcase105();
			  testcase106();
			  testcase107();
			  testcase108();
			  testcase109();

}

public void initDBService() 
    {
    PureServerUtils.useJDBC = true;
    //br = new BufferedReader(new InputStreamReader(System.in));
 
    tranAPI = TransactionAPI.getInstance();
    relapi = NmsUtil.relapi;
	conn =relapi.getConnection();
	while(usapi==null)
	{
	usapi = (UserStorageAPI)NmsUtil.getAPI("UserStorageAPI");
	}
	while(obj==null)
	{
	obj = new AgentDefValObject();
	}
	
    System.out.println("\n UserStorage CREATED ");
    //query("name","ManagedObject");
    //System.out.println("\n\n");
    }   

public void testcase108()

{

			System.out.println("INTO TESTCASE : DB-TXN-EXP-108");
			
			try{
			obj.setName("tc");
			obj.setValue("tc");
			usapi.addObject(obj,"tc");
			}catch(Exception e)
			{
				e.printStackTrace();
			}

	try{
			tranAPI.begin(100);
				{
					Thread.sleep(1000);
					usapi.getObject("testcase051","AgentDefValObject");	
					
				}
			tranAPI.commit();
			 System.out.println("Testcase : DB-TXN-EXP-108 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Testcase : DB-TXN-EXP-108 :: PASSED");
            System.err.println
                ("  DB-TXN-EXP-108 :: UserTransactionException Received :: \n"
                 +ute);
 
        }catch(Exception e)
        {
            System.out.println(" Testcase : DB-TXN-EXP-108 :: FAILED");
            System.err.println(" TestCase : DB-TXN-EXP-108 :: Exception Received :: \n" +e);
        }
 
} 

public void testcase105()
 
{
 
            System.out.println("INTO TESTCASE : 105");
 
 
    try{
            tranAPI.begin(100);
                {
                    Thread.sleep(1000);
					 obj.setName("tc");
					 obj.setValue("tc");
				     usapi.addObject(obj,"tc"); 
 
                }
            tranAPI.commit();
		 System.out.println(" Testcase : DB-TXN-EXP-105 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Testcase : DB-TXN-EXP-105 :: PASSED");
            System.err.println
                ("Testcase  DB-TXN-EXP-105 :: UserTransactionException Received :: \n");
                 
        }catch(Exception e)
        {
            System.out.println("  Testcase : DB-TXN-EXP-105 :: FAILED");
            System.err.println("  TestCase : DB-TXN-EXP-105 :: Exception Received :: \n" +e);
        }
 
}  
public void testcase106()
 
{
 
            System.out.println("INTO TESTCASE : 106");
 
 
    try{
            tranAPI.begin(100);
                {
                    Thread.sleep(1000);
                     obj.setName("tc");
                     obj.setValue("tc");
                     usapi.deleteObject(obj,"tc");
 
                }
            tranAPI.commit();
         System.out.println(" Testcase : DB-TXN-EXP-106 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Testcase : DB-TXN-EXP-106 :: PASSED");
            System.err.println
                ("Testcase  DB-TXN-EXP-106 :: UserTransactionException Received :: \n"
                 +ute);
 
        }catch(Exception e)
        {
            System.out.println("  Testcase : DB-TXN-EXP-106 :: FAILED");
            System.err.println("  TestCase : DB-TXN-EXP-106 :: Exception Received :: \n" +e);
        }
}
public void testcase107()
 
{
 
            System.out.println("INTO TESTCASE : 107");
 
 
    try{
            tranAPI.begin(100);
                {
                    Thread.sleep(1000);
                     obj.setName("tc");
                     obj.setValue("tc");
                     usapi.updateObject(obj,"tc");
 
                }
            tranAPI.commit();
         System.out.println(" Testcase : DB-TXN-EXP-107 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Testcase : DB-TXN-EXP-107 :: PASSED");
            System.err.println
                ("Testcase  DB-TXN-EXP-107 :: UserTransactionException Received :: \n"
                 +ute);
 
        }catch(Exception e)
        {
            System.out.println("  Testcase : DB-TXN-EXP-107 :: FAILED");
            System.err.println("  TestCase : DB-TXN-EXP-107 :: Exception Received :: \n" +e);
        }
}
public void testcase109()
 
{
 
            System.out.println("INTO TESTCASE : 109");
 
 
    try{
            tranAPI.begin(100);
                {
                    Thread.sleep(1000);
					Properties p = new Properties();
                     usapi.getObjects("AgentDefValObject",p);
 
                }
            tranAPI.commit();
         System.out.println(" Testcase : DB-TXN-EXP-109 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Testcase : DB-TXN-EXP-109 :: PASSED");
            System.err.println
                ("Testcase  DB-TXN-EXP-105 :: UserTransactionException Received :: \n"
                 +ute);
 
        }catch(Exception e)
        {
            System.out.println("  Testcase : DB-TXN-EXP-109 :: FAILED");
            System.err.println("  TestCase : DB-TXN-EXP-109 :: Exception Received :: \n" +e);
        }
                                                                                                                            }

public boolean isInitialized()
    {
        return true;
    }
 
public void shutDown()
    {
 
    }
 
public  void callMain(String args[])
    {
 
        //nmsdir = "/advent3/2.3sp4/AdventNet/WebNMS/";
 
        try{
            Txn_Expn_UserStorageAPI ct = new Txn_Expn_UserStorageAPI();
            ct.exec();
        }
        catch(Exception e)
        {
            System.out.println(" Exception while instantiating the Program");
            e.printStackTrace();
 
        }
 
    }


} 
