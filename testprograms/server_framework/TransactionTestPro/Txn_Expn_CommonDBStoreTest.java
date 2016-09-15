package txn;

import java.sql.*;
import java.io.*;
import java.util.*;

import com.adventnet.management.transaction.*;
import com.adventnet.nms.store.*;
import com.adventnet.nms.store.relational.*;
import com.adventnet.nms.util.*;
import com.adventnet.nms.startnms.*;

import com.adventnet.nms.topodb.CommonTopoAPI;
import com.adventnet.nms.topodb.ManagedObject;
import com.adventnet.nms.topodb.DBServer;
import com.adventnet.nms.topodb.*;
import com.adventnet.nms.store.CommonDBStore;
import com.adventnet.nms.store.CommonDBStoreExtn;
import com.adventnet.nms.db.util.*;

public class Txn_Expn_CommonDBStoreTest implements RunProcessInterface
{
    TransactionAPI transAPI = null;
    RelationalAPI relapi = null;
    BufferedReader br = null;
    CommonDBStoreExtn cds = null;
    ManagedObject mobj = null;
    Connection conn = null;
	TopoAPI topo = null;
	static String nmsdir = null;
	private final String SPECIAL = "";
	private final boolean AIM = false;

    public Txn_Expn_CommonDBStoreTest() throws Exception
    {

       initDBService();

    }

    private void initDBService() throws Exception
    {
	//PureUtils.rootDir = nmsdir;
	//PureServerUtils.useJDBC = true;
	br = new BufferedReader(new InputStreamReader(System.in));

	NmsUtil.readServerParams();
	NmsUtil.setDBObjectsCacheSize();

    RelationalUtil.init(null);
	PureServerUtils.getDatabaseParams();
	System.out.println("rootDir is "+PureUtils.rootDir);
	System.out.println("url is "+PureServerUtils.url);
	NmsUtil.init();
	System.out.println("INITED");

	relapi = NmsUtil.relapi;
    transAPI = NmsUtil.relapi.getTransactionAPI();
    System.out.println("relapi=" + relapi + " Topo "   + topo);
    conn =relapi.getConnection();
	cds = new CommonDBStoreExtn(0,true);
	System.out.println("\n CommonDBStore CREATED ");
    }

public void testcase086()
    {

        try{

        String name = "case086";
        mobj= new ManagedObject();
        mobj.setName(name);
        cds.addObject(mobj);
        System.out.println(" The mangedObject added ");
		transAPI.begin(100);
		{
		Thread.sleep(2000);
                cds.clearMemory();
		}
		transAPI.commit();
        System.out.println(" Testcase : DB-TXN-EXP-086 :: Failed == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Testcase : DB-TXN-EXP-086 :: Failed");
            System.err.println
                ("Testcase  DB-TXN-EXP-086 :: UserTransactionException Received :: \n"
                 +ute);

        }catch(Exception e)
        {
            System.out.println("  Testcase : DB-TXN-EXP-086 :: Passed");
            System.err.println("  TestCase : DB-TXN-EXP-086 :: Exception Received :: \n" +e);
        }

}


public void testcase087()
    {

        try{

        String name = "case087";
        mobj= new ManagedObject();
        mobj.setName(name);
        cds.addObject(mobj);
        System.out.println(" The mangedObject added ");
		transAPI.begin(100);
		{
		Thread.sleep(2000);
        ManagedObject test001 =(ManagedObject)cds.getObject("case087");
		}
		transAPI.commit();
        System.out.println(" Testcase : DB-TXN-EXP-087 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Testcase : DB-TXN-EXP-087 :: PASSED");
            System.err.println
                ("Testcase  DB-TXN-EXP-087 :: UserTransactionException Received :: \n"
                 +ute);

        }catch(Exception e)
        {
            System.out.println("  Testcase : DB-TXN-EXP-087 :: FAILED");
            System.err.println("  TestCase : DB-TXN-EXP-087 :: Exception Received :: \n" +e);
        }

}

 public void testcase088()
    {

        try{

        String name = "case088";
        mobj= new ManagedObject();
        mobj.setName(name);
        cds.addObject(mobj);
        System.out.println(" The mangedObject added ");
		transAPI.begin(100);
		{
		Thread.sleep(2000);
        ManagedObject test001 =(ManagedObject)cds.getObject("case088",false);
		}
		transAPI.commit();
        System.out.println(" Testcase : DB-TXN-EXP-088 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Testcase : DB-TXN-EXP-088 :: PASSED");
            System.err.println
                ("Testcase  DB-TXN-EXP-088 :: UserTransactionException Received :: \n"
                 +ute);

        }catch(Exception e)
        {
            System.out.println("  Testcase : DB-TXN-EXP-088 :: FAILED");
            System.err.println("  TestCase : DB-TXN-EXP-088 :: Exception Received :: \n" +e);
        }

}


public void testcase091()
    {

        try{
            String name = "case002";
             mobj= new ManagedObject();
             mobj.setName(name);

             cds.addObject("ca2",mobj);
		transAPI.begin(100);
		{
		Thread.sleep(2000);
        ManagedObject test002 =(ManagedObject)cds.getObjectByKey("ca2",true);
		}
		transAPI.commit();
        System.out.println(" Testcase : DB-TXN-EXP-091 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Testcase : DB-TXN-EXP-091 :: PASSED");
            System.err.println
                ("Testcase  DB-TXN-EXP-091 :: UserTransactionException Received :: \n"
                 +ute);

        }catch(Exception e)
        {
            System.out.println("  Testcase : DB-TXN-EXP-091 :: FAILED");
            System.err.println("  TestCase : DB-TXN-EXP-091 :: Exception Received :: \n" +e);
        }

}

public void testcase097()
{

        try{
            String name = "case097";
             mobj= new ManagedObject();
             mobj.setName(name);
			 transAPI.begin(100);
			 {
			 Thread.sleep(2000);
             cds.addObject(true,mobj);
             }
			 transAPI.commit();
			 System.out.println(" Testcase : DB-TXN-EXP-097 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Testcase : DB-TXN-EXP-097 :: PASSED");
            System.err.println
                ("Testcase  DB-TXN-EXP-097 :: UserTransactionException Received :: \n"
                 +ute);

        }catch(Exception e)
        {
            System.out.println("  Testcase : DB-TXN-EXP-097 :: FAILED");
            System.err.println("  TestCase : DB-TXN-EXP-097 :: Exception Received :: \n" +e);
        }

}


public void testcase098()
 {

   try{
             String name = "case098";
             mobj= new ManagedObject();
             mobj.setName(name);
       		 transAPI.begin(100);
			 {
			 Thread.sleep(3000);
             cds.addObject("ca4",mobj);//kaschange
			 }
			 transAPI.commit();
			  System.out.println(" Testcase : DB-TXN-EXP-098 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Testcase : DB-TXN-EXP-098 :: PASSED");
            System.err.println
                ("Testcase  DB-TXN-EXP-098 :: UserTransactionException Received :: \n"
                 +ute);

        }catch(Exception e)
        {
            System.out.println("  Testcase : DB-TXN-EXP-098 :: FAILED");
            System.err.println("  TestCase : DB-TXN-EXP-098 :: Exception Received :: \n" +e);
        }

}

public void testcase099()
{

        try{
            String name = "case099";
             mobj= new ManagedObject();
             mobj.setName(name);
             transAPI.begin(100);
             {
             Thread.sleep(2000);
             cds.addObject(mobj,true);
             }
             transAPI.commit();
             System.out.println(" Testcase : DB-TXN-EXP-099 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Testcase : DB-TXN-EXP-099 :: PASSED");
            System.err.println
                ("Testcase  DB-TXN-EXP-099 :: UserTransactionException Received :: \n"
                 +ute);

        }catch(Exception e)
        {
            System.out.println("  Testcase : DB-TXN-EXP-099 :: FAILED");
            System.err.println("  TestCase : DB-TXN-EXP-099 :: Exception Received :: \n" +e);
        }

}


public void testcase093()
    {

        try{
            String name = "case005";
             mobj= new ManagedObject();
             mobj.setName(name);

             cds.addObject("ca5",mobj);
			 transAPI.begin(100);
			 {
			 Thread.sleep(1000);
             ManagedObject test005 =(ManagedObject)cds.getByKey("ca5");
			 }
			 transAPI.commit();
			 System.out.println(" Testcase : DB-TXN-EXP-093 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Testcase : DB-TXN-EXP-093 :: PASSED");
            System.err.println
                ("Testcase  DB-TXN-EXP-093 :: UserTransactionException Received :: \n"
                 +ute);

        }catch(Exception e)
        {
            System.out.println("  Testcase : DB-TXN-EXP-093 :: FAILED");
            System.err.println("  TestCase : DB-TXN-EXP-093 :: Exception Received :: \n" +e);
        }

}

public void testcase094()
    {

        try{
            String name = "case094";
             mobj= new ManagedObject();
             mobj.setName(name);

             cds.addObject("ca94",mobj);
			 transAPI.begin(100);
			 {
			 Thread.sleep(1000);
             ManagedObject test005 =(ManagedObject)cds.getObjectByKeyOnlyFromMemory("ca94");
			 }
			 transAPI.commit();
			 System.out.println(" Testcase : DB-TXN-EXP-094 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Testcase : DB-TXN-EXP-094 :: PASSED");
            System.err.println
                ("Testcase  DB-TXN-EXP-094 :: UserTransactionException Received :: \n"
                 +ute);

        }catch(Exception e)
        {
            System.out.println("  Testcase : DB-TXN-EXP-094 :: FAILED");
            System.err.println("  TestCase : DB-TXN-EXP-094 :: Exception Received :: \n" +e);
        }

}


public void testcase089()
    {

        try{
            String name = "case089";
             mobj= new ManagedObject();
             mobj.setName(name);

             cds.addObject("ca6",mobj);
			 transAPI.begin(100);
			 {
			 Thread.sleep(2000);
             String nametest =cds.getNameByKey("ca6");
			 }
			transAPI.commit();
   			System.out.println(" Testcase : DB-TXN-EXP-089 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Testcase : DB-TXN-EXP-089 :: PASSED");
            System.err.println
                ("Testcase  DB-TXN-EXP-089 :: UserTransactionException Received :: \n"
                 +ute);

        }catch(Exception e)
        {
            System.out.println("  Testcase : DB-TXN-EXP-089 :: FAILED");
            System.err.println("  TestCase : DB-TXN-EXP-089 :: Exception Received :: \n" +e);
        }

}

public void testcase100()
    {

        try{
            String name = "case100";
             mobj= new ManagedObject();
             mobj.setName(name);
       		 transAPI.begin(100);
			 {
			 Thread.sleep(1000);
             cds.addObject("ca8",mobj,true);
			 }
			 transAPI.commit();
			 System.out.println(" Testcase : DB-TXN-EXP-100 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Testcase : DB-TXN-EXP-100 :: PASSED");
            System.err.println
                ("Testcase  DB-TXN-EXP-100 :: UserTransactionException Received :: \n"
                 +ute);

        }catch(Exception e)
        {
            System.out.println("  Testcase : DB-TXN-EXP-100 :: FAILED");
            System.err.println("  TestCase : DB-TXN-EXP-100 :: Exception Received :: \n" +e);
        }

}

public void testcase092()
    {

        try{
            String name = "case092";
             mobj= new ManagedObject();
             mobj.setName(name);

             cds.addObject("ca8",mobj,true);
			 transAPI.begin(100);
			 {
			 Thread.sleep(2000);
             String nametest = cds.getObjectKey("ca8");
			 }
			 transAPI.commit();
			 System.out.println(" Testcase : DB-TXN-EXP-092 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Testcase : DB-TXN-EXP-092 :: PASSED");
            System.err.println
                ("Testcase  DB-TXN-EXP-092 :: UserTransactionException Received :: \n"
                 +ute);

        }catch(Exception e)
        {
            System.out.println("  Testcase : DB-TXN-EXP-092 :: FAILED");
            System.err.println("  TestCase : DB-TXN-EXP-092 :: Exception Received :: \n" +e);
        }

}


public void testcase095()
    {

        try{
            String name = "case010";
             mobj= new ManagedObject();
             mobj.setName(name);

             cds.addObject(mobj);
             Properties prop = new Properties();
			 transAPI.begin(200);
			 {
			 Thread.sleep(2000);
             Vector vec = cds.getObjectNamesWithProps(prop);
			 }
			 transAPI.commit();
		 	System.out.println(" Testcase : DB-TXN-EXP-095 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Testcase : DB-TXN-EXP-095 :: PASSED");
            System.err.println
                ("Testcase  DB-TXN-EXP-095 :: UserTransactionException Received :: \n"
                 +ute);

        }catch(Exception e)
        {
            System.out.println("  Testcase : DB-TXN-EXP-095 :: FAILED");
            System.err.println("  TestCase : DB-TXN-EXP-095 :: Exception Received :: \n" +e);
        }

}

public void testcase096()
    {

        try{
            String name = "case011";
             mobj= new ManagedObject();
             mobj.setName(name);
       		transAPI.begin(100);
			{
			Thread.sleep(2000);
            cds.addObject(mobj);
			}
			transAPI.commit();
			System.out.println(" Testcase : DB-TXN-EXP-096 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Testcase : DB-TXN-EXP-096 :: PASSED");
            System.err.println
                ("Testcase  DB-TXN-EXP-096 :: UserTransactionException Received :: \n"
                 +ute);

        }catch(Exception e)
        {
            System.out.println("  Testcase : DB-TXN-EXP-096 :: FAILED");
            System.err.println("  TestCase : DB-TXN-EXP-096 :: Exception Received :: \n" +e);
        }

}

public void testcase090()
    {

        try{
             String name = "case090";
             mobj= new ManagedObject();
             mobj.setName(name);
             cds.addObject(mobj);
			 transAPI.begin(100);
			 {
			 Thread.sleep(1200);
             ManagedObject test013 =(ManagedObject)cds.getObjectByKey("ca13");
  			 }
		transAPI.commit();
		System.out.println(" Testcase : DB-TXN-EXP-090 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Testcase : DB-TXN-EXP-090 :: PASSED");
            System.err.println
                ("Testcase  DB-TXN-EXP-090 :: UserTransactionException Received :: \n"
                 +ute);

        }catch(Exception e)
        {
            System.out.println("  Testcase : DB-TXN-EXP-090 :: FAILED");
            System.err.println("  TestCase : DB-TXN-EXP-090 :: Exception Received :: \n" +e);
        }

}

public void testcase104()
    {

        try{
            String name = "case104";
             mobj= new ManagedObject();
             mobj.setName(name);

             cds.addObject("ca14",mobj);
			 transAPI.begin(100);
			 {
			 Thread.sleep(2000);
             cds.removeKey("ca14");
			 }
			 transAPI.commit();
			System.out.println(" Testcase : DB-TXN-EXP-104 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Testcase : DB-TXN-EXP-104 :: PASSED");
            System.err.println
                ("Testcase  DB-TXN-EXP-104 :: UserTransactionException Received :: \n"
                 +ute);

        }catch(Exception e)
        {
            System.out.println("  Testcase : DB-TXN-EXP-104 :: FAILED");
            System.err.println("  TestCase : DB-TXN-EXP-104 :: Exception Received :: \n" +e);
        }

}

public void testcase101()
    {

        try{
            String name = "case101";
             mobj= new ManagedObject();
             mobj.setName(name);

             cds.addObject(mobj);
             mobj = new ManagedObject();
             mobj.setName(name);
             mobj.setDisplayName("aravind");
			 transAPI.begin(100);
			 {
			 Thread.sleep(2000);
             cds.updateObject(mobj);
			 }
			 transAPI.commit();
			 System.out.println(" Testcase : DB-TXN-EXP-101 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Testcase : DB-TXN-EXP-101 :: PASSED");
            System.err.println
                ("Testcase  DB-TXN-EXP-101 :: UserTransactionException Received :: \n"
                 +ute);

        }catch(Exception e)
        {
            System.out.println("  Testcase : DB-TXN-EXP-101 :: FAILED");
            System.err.println("  TestCase : DB-TXN-EXP-101 :: Exception Received :: \n" +e);
        }

}


public void testcase103()
    {

        try{
            String name = "case103";
             mobj= new ManagedObject();
             mobj.setName(name);

             cds.addObject(mobj);
			transAPI.begin(100);
		 	{
			Thread.sleep(2000);
            cds.deleteObject(name);
			}
			transAPI.commit();
			System.out.println(" Testcase : DB-TXN-EXP-103 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Testcase : DB-TXN-EXP-103 :: PASSED");
            System.err.println
                ("Testcase  DB-TXN-EXP-103 :: UserTransactionException Received :: \n"
                 +ute);

        }catch(Exception e)
        {
            System.out.println("  Testcase : DB-TXN-EXP-103 :: FAILED");
            System.err.println("  TestCase : DB-TXN-EXP-103 :: Exception Received :: \n" +e);
        }

}

public void testcase102()
    {
     try{
            String name = "case102";
             mobj= new ManagedObject();
             mobj.setName(name);
             cds.addObject(mobj);
            ManagedObject test018 =(ManagedObject)cds.getObject(name);
            String testname = test018.getName();

			transAPI.begin(100);
			{
			Thread.sleep(2000);
            cds.deleteObject(mobj);
            }
			transAPI.commit();
			System.out.println(" Testcase : DB-TXN-EXP-102 :: FAILED == END");
        }
        catch(UserTransactionException ute)
        {
            System.out.println(" Testcase : DB-TXN-EXP-102 :: PASSED");
            System.err.println
                ("Testcase  DB-TXN-EXP-102 :: UserTransactionException Received :: \n"
                 +ute);

        }catch(Exception e)
        {
            System.out.println("  Testcase : DB-TXN-EXP-102 :: FAILED");
            System.err.println("  TestCase : DB-TXN-EXP-102 :: Exception Received :: \n" +e);
        }

}

public void exec()
{
                        testcase086();
                        testcase087();
                        testcase088();
			testcase089();
			testcase090();
			testcase091();
			testcase092();
			testcase093();
                        testcase094();
			testcase095();
			testcase096();
			testcase097();
			testcase098();
			testcase099();
			testcase100();
			testcase101();
			testcase102();
			testcase103();
			testcase104();


        System.out.println(" ALL test cases executed");
}





public  void callMain(String args[])
{

     //nmsdir = "/advent3/2.3sp4/AdventNet/WebNMS/";

  try{
            CommonDBStoreTest ct = new CommonDBStoreTest();
			ct.exec();
      }
  catch(Exception e)
      {
            System.out.println(" Exception while instantiating the Program");
            e.printStackTrace();

          }

    }



/*public void callMain(String[] args)
{
System.out.println("callMain called");
getHandle();

}*/



public boolean isInitialized()
{
return true;
}

public void shutDown()
{

}



}
