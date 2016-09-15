package txn;
import com.adventnet.management.transaction.*;
import com.adventnet.nms.store.*;
import java.sql.*;
import java.util.*;
import java.io.*;
import java.rmi.*;
import javax.transaction.RollbackException;
import com.adventnet.nms.util.*;
import com.adventnet.nms.store.relational.*;
//import javax.servlet.*;
//import javax.servlet.http.*;
import com.adventnet.management.policydb.*;
import com.adventnet.nms.policies.*;
import com.adventnet.management.policydb.PolicyEvent;

public class  NMSPException implements RunProcessInterface

{
    //  HttpServletRequest req = null;
	PrintWriter out = null;
	ConnectionPool cp = null;
	TransactionAPI tranAPI = null;
	String objname = null;
	DBIndexedVector dbv = null;
	RelationalAPI relapi = null;
	Connection conn = null;
	NmsPolicyAPI api = null;
	int thread2=0;

public static Thread t = Thread.currentThread();


public void exec()
{
			  try{
			  		Thread.sleep(40000);
				}catch(Exception e)
				{
				}
			  initDBService();
			  testcase110();
			  testcase111();
			  testcase112();
			  testcase113();
			  testcase114();//kaschange
			  testcase115();//kaschange
			  testcase116();
			  testcase117();
			  testcase118();

}

public void initDBService()
    {
    PureServerUtils.useJDBC = true;
    //br = new BufferedReader(new InputStreamReader(System.in));
	while(tranAPI==null)
	{
    tranAPI = TransactionAPI.getInstance();
	System.out.println(tranAPI);
	}
    relapi = NmsUtil.relapi;
	conn =relapi.getConnection();
	while(api==null)
	{
	api = (NmsPolicyAPI)NmsUtil.getAPI("NmsPolicyAPI");
	System.out.println("NMSPolicyAPI not initialised");
	}
    System.out.println("\n NMSPolicy CREATED ");
    //query("name","ManagedObject");
    //System.out.println("\n\n");
    }

public void testcase110()

{

			System.out.println("INTO TESTCASE : 110");
	try{
			tranAPI.begin(100);
				{
					PolicyObject obj1 = new TesterPolicy();
					 obj1.setName("testpolicy3");
					Thread.sleep(1000);
				    api.addPolicy(obj1);
				}
			tranAPI.commit();
			System.out.println(" Testcase : DB-TXN-EXP-110 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Testcase : DB-TXN-EXP-110 :: PASSED");
            System.err.println
                ("Testcase  DB-TXN-EXP-110 :: UserTransactionException Received :: \n"
                 +ute);

        }catch(Exception e)
        {
            System.out.println("  Testcase : DB-TXN-EXP-110 :: FAILED");
            System.err.println("  TestCase : DB-TXN-EXP-110 :: Exception Received :: \n" +e);
            try
            {
                tranAPI.rollback();
            }
            catch(Exception exx)
            {}
        }

}
public void testcase111()

{

            System.out.println("INTO TESTCASE : 111");


    try{

            tranAPI.begin(100);
                {

                    PolicyObject obj1 = new TesterPolicy();
                     obj1.setName("testpolicy3");
                    Thread.sleep(1000);
                    api.deletePolicy("testpolicy3");
                }
            tranAPI.commit();
 			 System.out.println("UserTransactionException in  TESTCASE  : 111 : FAILED");
        }catch(UserTransactionException  ute)
            {
                System.out.println("UserTransactionException in  TESTCASE  : 111 : PASSED");
                 System.err.println("  TestCase : DB-TXN-EXP-111 :: Exception Received :: \n" +ute);
            }
		catch(Exception e)
            {
                System.out.println("Exception  TESTCASE  : 111");
                e.printStackTrace();
                try
                {
                    tranAPI.rollback();
                }
                catch(Exception exx)
                {}
            }
}

public void testcase112()

{

            System.out.println("INTO TESTCASE : 112");


    try{

            tranAPI.begin(100);
                {

                    Thread.sleep(1000);
                    api.getPolicy("testpolicy3",true);
                }
        	    tranAPI.commit();
        		System.out.println("UserTransactionException in  TESTCASE  : 112 : FAILED");
        }
        catch(UserTransactionException  ute)
            {
                System.out.println("UserTransactionException in  TESTCASE  : 112 : PASSED");
                 System.err.println("  TestCase : DB-TXN-EXP-111 :: Exception Received :: \n" +ute);
            }
        catch(Exception e)
            {
                System.out.println("Exception  TESTCASE  : 112");
                e.printStackTrace();
                try
                {
                    tranAPI.rollback();
                }
                catch(Exception exx)
                {}
            }
}

public void testcase113()

{
	System.out.println("INTO TESTCASE : 113");
    try{
            tranAPI.begin(100);
                {
                    Thread.sleep(1000);
                    api.getPolicy("testpolicy3");
                }
            tranAPI.commit();
 		System.out.println("UserTransactionException in  TESTCASE  : 113 : FAILED");
        }
        catch(UserTransactionException  ute)
            {
                System.out.println("UserTransactionException in  TESTCASE  : 113 : PASSED");
                 System.err.println("  TestCase : DB-TXN-EXP-113 :: Exception Received :: \n" +ute);
            }
        catch(Exception e)
            {
                System.out.println("Exception  TESTCASE  : 113");
                e.printStackTrace();
                try
                {
                    tranAPI.rollback();
                }
                catch(Exception exx)
                {}
           }
}

public void testcase114()

{
	System.out.println("INTO TESTCASE : 114");
    try{

		//First PolicyEvent has to be constructed as
		PolicyEvent pe = new PolicyEvent("kaif");
		//then policies which has to executed are added as
		pe.addPolicyNamesToTrigger("kaiftestpolicy1");
		//then added PolicyObject with the name "testpolicy1" can be executed as

        tranAPI.begin(100);
                {
                    Thread.sleep(1000);
                    api.executePolicy(pe);
                }
            tranAPI.commit();
 		System.out.println("UserTransactionException in  TESTCASE  : 114 : FAILED");
        }
        catch(UserTransactionException  ute)
            {
                System.out.println("UserTransactionException in  TESTCASE  : 114 : PASSED");
                 System.err.println("  TestCase : DB-TXN-EXP-114 :: Exception Received :: \n" +ute);
            }
        catch(Exception e)
            {
                System.out.println("Exception  TESTCASE  : 114 FAILED"+ e);
                e.printStackTrace();
                try
                {
                    tranAPI.rollback();
                }
                catch(Exception exx)
                {}
           }
}

public void testcase115()

{
	System.out.println("INTO TESTCASE : 115");
    try{
		PolicyObject obj1 = new TesterPolicy();
		obj1.setName("testpolicy115");
	    api.addPolicy(obj1);
            tranAPI.begin(100);
                {
                    Thread.sleep(1000);
					api.setPolicyStatus("testpolicy115",2);

                }
            tranAPI.commit();
 		System.out.println("UserTransactionException in  TESTCASE  : 115 : FAILED");
        }
        catch(UserTransactionException  ute)
            {
                System.out.println("UserTransactionException in  TESTCASE  : 115 : PASSED");
                 System.err.println("  TestCase : DB-TXN-EXP-115 :: Exception Received :: \n" +ute);
            }
        catch(Exception e)
            {
                System.out.println("Exception  TESTCASE  : 115"+ e);
                e.printStackTrace();
                try
                {
                    tranAPI.rollback();
                }
                catch(Exception exx)
                {}
           }
}

public void testcase116()

{

            System.out.println("INTO TESTCASE : 116");


    try{

            tranAPI.begin(100);
                {

                    Thread.sleep(1000);
					Properties p = new Properties();
                    api.updatePolicy("testpolicy3",p);
                }
            tranAPI.commit();

        }catch(UserTransactionException  ute)
            {
                System.out.println("UserTransactionException in  TESTCASE  : 116 : PASSED");
                ute.printStackTrace();
            }
        catch(Exception e)
            {
                System.out.println("Exception  TESTCASE  : 116");
                e.printStackTrace();
                try
                {
                    tranAPI.rollback();
                }
                catch(Exception exx)
                {}
            }
}

public void testcase117()

{

            System.out.println("INTO TESTCASE : 117");


    try{

            tranAPI.begin(100);
                {

                    Thread.sleep(1000);
                    api.stopPolicy("testpolicy3");
                }
            tranAPI.commit();

        }catch(UserTransactionException  ute)
            {
                System.out.println("UserTransactionException in  TESTCASE  : 117 : PASSED");
                ute.printStackTrace();
            }
        catch(Exception e)
            {
                System.out.println("Exception  TESTCASE  : 117");
                e.printStackTrace();
                try
                {
                    tranAPI.rollback();
                }
                catch(Exception exx)
                {}
            }
}
public void testcase118()

{

            System.out.println("INTO TESTCASE : 118");


    try{

            tranAPI.begin(100);
                {

                    Thread.sleep(1000);
                    api.getPolicyNames();
                }
            tranAPI.commit();

        }catch(UserTransactionException  ute)
            {
                System.out.println("UserTransactionException in  TESTCASE  : 118 : PASSED");
                ute.printStackTrace();
            }
        catch(Exception e)
            {
                System.out.println("Exception  TESTCASE  : 118");
                e.printStackTrace();
                try
                {
                    tranAPI.rollback();
                }
                catch(Exception exx)
                {}
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
            NMSPException ct = new NMSPException();
            ct.exec();
        }
        catch(Exception e)
        {
            System.out.println(" Exception while instantiating the Program");
            e.printStackTrace();

        }

    }
}
