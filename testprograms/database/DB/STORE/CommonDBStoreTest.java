package com.adventnet.nms.store.relational;

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

public class CommonDBStoreTest implements RunProcessInterface 
{
    TransactionAPI transAPI = null;
    RelationalAPI relapi = null;
    BufferedReader br = null;
    CommonDBStoreExtn cds = null;
    ManagedObject mobj = null;
    RelationalManagedObject relMo = null;
    Connection conn = null;

    Statement state = null;

	TopoAPI topo = null;
	static String nmsdir = null;	
	private final String SPECIAL = "";
	private final boolean AIM = false;

    public CommonDBStoreTest() throws Exception
    { 
        initDBService();
    }
   	
    private void initDBService() throws Exception
    {
        Thread.sleep(20000);
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

        while (relapi == null || transAPI == null || topo == null)
        {
            relapi = NmsUtil.relapi;
            transAPI = NmsUtil.relapi.getTransactionAPI();
            topo = (TopoAPI) NmsUtil.getAPI("TopoAPI");
            Thread.sleep(10);
        }
        System.out.println("relapi=" + relapi + " Topo "   + topo);
        conn =relapi.getConnection();
        state = conn.createStatement();
        cds = new CommonDBStoreExtn(150,true);
        System.out.println("\n CommonDBStore CREATED ");

        relMo = new RelationalManagedObject();
        relMo.init(relapi);
        relMo.setUserPropTableName("TOPOUSERPROPS");
    }
  
    public void testcase001()
    {
    
        try{

            String name = "case001";         
            mobj= new ManagedObject(); 
            mobj.setName(name);
            cds.addObject(mobj);
            System.out.println(" The mangedObject added ");
            ManagedObject test001 =(ManagedObject)cds.getObject("case001");
            String nametest = test001.getName();
        
            String sql ="select NAME  from ManagedObject where NAME='case001'";
        
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs001 =relapi.executeQuery(ps);
            String result = null;
            while(rs001.next())
            {
                result =rs001.getString(1);
            }    
            System.out.println("The value Obtained"+result); 
   
            if(name.equals(nametest)&& name.equals(result))
            {
                System.out.println("TestCaseNO:SF-DB-STORE-001:PASSED");
            }
             
        }

                
        catch(Exception e)
        {
            System.out.println("Exception :Case001");
            e.printStackTrace();
        }          
      
       
    }
    
    public void testcase002()
    {
       
        try{
            String name = "case002";
            mobj= new ManagedObject();
            mobj.setName(name);
       
            cds.addObject("ca2",mobj);

            ManagedObject test002 =(ManagedObject)cds.getObjectByKey("ca2");
            String nametest = test002.getName();
   
               
            String sql ="select NAME  from ManagedObject where NAME='case002'";
        
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs002 =relapi.executeQuery(ps);
            String result = null;
            while(rs002.next())
            {
                result =rs002.getString(1);
            }    
            System.out.println("The value Obtained"+result); 
   
           

            if(name.equals(nametest)&&  name.equals(result))
            {
                System.out.println("TestCaseNO:SF-DB-STORE-002:PASSED");
            }
             
        }

        catch(Exception e)
        {
            System.out.println("Exception :Case002");
            e.printStackTrace();
 
        } 

    }            




    public void testcase003()
    {
       
        try{
            String name = "case003";
            mobj= new ManagedObject();
            mobj.setName(name);
            cds.addObject(mobj,true);
               
            ManagedObject test003 =(ManagedObject)cds.getObject(name);
            String nametest = test003.getName();

            String sql ="select NAME  from ManagedObject where NAME='case003'";
        
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs003 =relapi.executeQuery(ps);
            String result = null;
            while(rs003.next())
            {
                result =rs003.getString(1);
            }    
            System.out.println("The value Obtained"+result); 
   
 
            if(name.equals(nametest)&& name.equals(result))
            {
                System.out.println("TestCaseNO:SF-DB-STORE-003:PASSED");
            }
             
        }

        catch(Exception e)
        {
            System.out.println("Exception :Case003");
            e.printStackTrace();
 
        } 

    }            



    public void testcase004()
    {
       
        try{
            String name = "case004";
            mobj= new ManagedObject();
            mobj.setName(name);
       
            cds.addObject("ca4",mobj,true);

            ManagedObject test004 =(ManagedObject)cds.getObjectByKey("ca4");
            System.out.println(test004);
            String nametest = test004.getName();
            System.out.println(nametest);
            String sql ="select NAME  from ManagedObject where NAME='case004'";
        
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs004 =relapi.executeQuery(ps);
            String result = null;
            while(rs004.next())
            {
                result =rs004.getString(1);
            }    
            System.out.println("The value Obtained"+result); 
   

            if(name.equals(nametest)&& name.equals(result))
            {
                System.out.println("TestCaseNO:SF-DB-STORE-004:PASSED");
            }
             
        }
        catch(Exception e)
        {
            System.out.println("Exception :Case004");
            e.printStackTrace();
 
        } 

    }            



    public void testcase005()
    {
       
        try{
            String name = "case005";
            mobj= new ManagedObject();
            mobj.setName(name);
       
            cds.addObject("ca5",mobj);

            ManagedObject test005 =(ManagedObject)cds.getByKey("ca5");
            String nametest = test005.getName();
   
            if(name.equals(nametest))
            {
                System.out.println("TestCaseNO:SF-DB-STORE-005:PASSED");
            }
             
        }

        catch(Exception e)
        {
            System.out.println("Exception :Case005");
            e.printStackTrace();
 
        } 

    }            

    public void testcase005_1()
    {
 
        try{
            String name = "case005";
            mobj= new ManagedObject();
            mobj.setName(name);
 
            cds.addObject("ca5",mobj);
 
            ManagedObject test005 =(ManagedObject)cds.getByKey("caaa5");
            System.out.println(test005);
            String nametest = test005.getName();
        }
 
        catch(Exception e)
        {
            System.out.println("Exception :Case005_1");
            e.printStackTrace();
 
        }
 
    } 

    public void testcase006()
    {
       
        try{
            String name = "case006";
            mobj= new ManagedObject();
            mobj.setName(name);
       
            cds.addObject("ca6",mobj);

            String nametest =cds.getNameByKey("ca6");
            System.out.println("key    " + nametest);              
   
            if(name.equals(nametest))
            {
                System.out.println("TestCaseNO:SF-DB-STORE-006:PASSED");
            }
             
        }

        catch(Exception e)
        {
            System.out.println("Exception :Case006");
            e.printStackTrace();
 
        } 

    }            



    public void testcase007()
    {
       
        try{
            String name = "case007";
            mobj= new ManagedObject();
            mobj.setName(name);
       
            cds.addObject(mobj);

            ManagedObject test007 =(ManagedObject)cds.getObject(name);
            String nametest = test007.getName();
   
            if(name.equals(nametest))
            {
                System.out.println("TestCaseNO:SF-DB-STORE-007:PASSED");
            }
             
        }

        catch(Exception e)
        {
            System.out.println("Exception :Case007");
            e.printStackTrace();
 
        } 

    }            



    public void testcase008()
    {
       
        try{
            String name = "case008";
            mobj= new ManagedObject();
            mobj.setName(name);
       
            cds.addObject("ca8",mobj,true);

            ManagedObject test008 =(ManagedObject)cds.getObjectByKey("ca8",true);
            String nametest = test008.getName();
   
            if(name.equals(nametest))
            {
                System.out.println("TestCaseNO:SF-DB-STORE-008:PASSED");
            }
            else
            {
                System.out.println("TestCaseNO:SF-DB-STORE-008:FAILED");
			}			 	
             
        }

        catch(Exception e)
        {
            System.out.println("Exception :Case008");
            e.printStackTrace();
 
        } 

    }            

    public void testcase008_1()
	{
		try{
            String name = "case008_1";
            mobj= new ManagedObject();
            mobj.setName(name);
 
            cds.addObject("ca8_1",mobj,true);
 
            ManagedObject test008 =(ManagedObject)cds.getObjectByKey("ca8_1",true);
            String nametest = test008.getName();
 
            if(name.equals(nametest))
            {
                System.out.println("TestCaseNO:SF-DB-STORE-008:PASSED");
            }
 
        }
 
        catch(Exception e)
        {
            System.out.println("Exception :Case008_1");
            e.printStackTrace();
 
        }
    }               	





    public void testcase009()
    {
       
        try{
            String name = "case009";
            mobj= new ManagedObject();
            mobj.setName(name);
       
            cds.addObject("ca8",mobj,true);

            String nametest = cds.getObjectKey("ca8");
             
   
            if(name.equals(nametest))
            {
                System.out.println("TestCaseNO:SF-DB-STORE-009:PASSED");
            }
             
        }

        catch(Exception e)
        {
            System.out.println("Exception :Case009");
            e.printStackTrace();
 
        } 

    }            

    public void testcase010()
    {
       
        try{
            String name = "case010";
            mobj= new ManagedObject();
            mobj.setName(name);
       
            cds.addObject(mobj);

			
			
            Properties prop = new Properties();
            prop.setProperty("name","case01*");

            Vector vec = cds.getObjectNamesWithProps(prop);
            int size = vec.size();
            System.out.println(" The Size:"+size);
            

			String sql ="select name  from ManagedObject where NAME='case010'";
 
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs010 =relapi.executeQuery(ps);
            String result = null;
            while(rs010.next())
            {
                result =rs010.getString(1);
            }
            System.out.println("The value Obtained"+result);	


			if((size==1)&& result.equals("case010"));
            {
                System.out.println("TestCaseNO:SF-DB-STORE-010:PASSED");
            }       

             
        }

        catch(Exception e)
        {
            System.out.println("Exception :Case010");
            e.printStackTrace();
 
        } 

    }            

    public void testcase011()
    {
       
        try{
            String name = "case011";
            mobj= new ManagedObject();
            mobj.setName(name);
       
            cds.addObject(mobj);

            Properties prop = new Properties();
            prop.setProperty("name","case01*");
            prop.setProperty("displayName","case011");

            Vector vec = cds.getObjectNamesWithProps(prop);
            int size = vec.size();
            System.out.println(" The Size:"+size);
   
            if(size==1)
            {
                System.out.println("TestCaseNO:SF-DB-STORE-011:PASSED");
            }
             
        }

        catch(Exception e)
        {
            System.out.println("Exception :Case011");
            e.printStackTrace();
 
        } 

    }            


    public void testcase012()
    {
       
        try{
            String name = "case012";
            mobj= new ManagedObject();
            mobj.setName(name);
            mobj.setUserProperty("userProp1", "value1");
            mobj.setUserProperty("userProp2", "value2");
       
            cds.addObject(mobj);

            Properties prop = new Properties();
            prop.setProperty("name","case012");
            prop.setProperty("userProp1","value*");

            Vector vec = cds.getObjectNamesWithProps(prop);
            int size = vec.size();
            System.out.println(" The Size:"+size);
   
            if(size==1)
            {
                System.out.println("TestCaseNO:SF-DB-STORE-012:PASSED");
            }
        }
        catch(Exception e)
        {
            System.out.println("Exception :Case012");
            e.printStackTrace();
        } 
    }            

    public void testcase013()
    {
       
        try{
            String name = "case013";
            mobj= new ManagedObject();
            mobj.setName(name);
            cds.addObject(mobj);
            cds.putKey("ca13",mobj);
            ManagedObject test013 =(ManagedObject)cds.getObjectByKey("ca13");
            String nametest = test013.getName();
   
            if(name.equals(nametest))
            {
                System.out.println("TestCaseNO:SF-DB-STORE-013:PASSED");
            }
             
        }

        catch(Exception e)
        {
            System.out.println("Exception :Case013");
            e.printStackTrace();
 
        } 

    }            


    public void testcase014()
    {
       
        try{
            String name = "case014";
            mobj= new ManagedObject();
            mobj.setName(name);
       
            cds.addObject("ca14",mobj);

            cds.removeKey("ca14");  

            ManagedObject test014 =(ManagedObject)cds.getObjectByKey("ca14");
			System.out.println(test014);
   
            if(test014==null)
            {
                System.out.println("TestCaseNO:SF-DB-STORE-014:PASSED");
            }
             
        }

        catch(Exception e)
        {
            System.out.println("Exception :Case014");
            e.printStackTrace();
 
        } 

    }            


    public void testcase015()
    {
       
        try{
            String name = "case015";
            mobj= new ManagedObject();
            mobj.setName(name);
       
            cds.addObject(mobj);
            mobj = new ManagedObject();
            mobj.setName(name);
            mobj.setDisplayName("aravind");
            cds.updateObject(mobj);    

            ManagedObject test015 =(ManagedObject)cds.getObject(name);
            String nametest= test015.getDisplayName();
			
           
            String sql ="select displayName  from ManagedObject where NAME='case015'";
        
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs015 =relapi.executeQuery(ps);
            String result = null;
            while(rs015.next())
            {
                result =rs015.getString(1);
            }    
            System.out.println("The value Obtained"+result); 
   
   
            if(nametest.equals("aravind")&& result.equals("aravind"))
            {
                System.out.println("TestCaseNO:SF-DB-STORE-015:PASSED");
            }
             
        }

        catch(Exception e)
        {
            System.out.println("Exception :Case015");
            e.printStackTrace();
 
        } 

    }            


    public void testcase016()
    {
       
        try{
            String name = "case016";
            mobj= new ManagedObject();
            mobj.setName(name);
       
            cds.addObject("ca16",mobj);

            cds.removeKey("ca16");  

            ManagedObject test016 =(ManagedObject)cds.getObjectByKey("ca16");
   
            if(test016==null)
            {
                System.out.println("TestCaseNO:SF-DB-STORE-016:PASSED");
            }
             
        }

        catch(Exception e)
        {
            System.out.println("Exception :Case016");
            e.printStackTrace();
 
        } 

    }            


    public void testcase017()
    {
       
        try{
            String name = "case017";
            mobj= new ManagedObject();
            mobj.setName(name);
       
            cds.addObject(mobj);

            ManagedObject test017 =(ManagedObject)cds.getObject(name);
   
            String testname = test017.getName();
            System.out.println(" The Object added:"+ testname);

            cds.deleteObject(name);
            System.out.println(" The Object deleted");
              
            ManagedObject  test0171 =(ManagedObject)cds.getObject(name);
           
            String sql ="select count(*)  from ManagedObject where NAME='case017'";
        
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs017 =relapi.executeQuery(ps);
            int result =0;
            while(rs017.next())
            {
                result =rs017.getInt(1);
            }    
            System.out.println("The value Obtained"+result); 
    
            
            
            if(testname.equals(name) && test0171==null && result==0)
            {
                System.out.println("TestCaseNO:SF-DB-STORE-017:PASSED");
            }
             
        }

        catch(Exception e)
        {
            System.out.println("Exception :Case017");
            e.printStackTrace();
 
        } 

    }            


    public void testcase018()
    {
        try{
            String name = "case018";
            mobj= new ManagedObject();
            mobj.setName(name);
       
            cds.addObject(mobj);

            ManagedObject test018 =(ManagedObject)cds.getObject(name);
   
            String testname = test018.getName();
            
            cds.deleteObject(mobj);
              
            ManagedObject  test0181 =(ManagedObject)cds.getObject(name);
   
            String sql ="select count(*) from ManagedObject where NAME='case018'";
        
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs018 =relapi.executeQuery(ps);
            int result =0;
            while(rs018.next())
            {
                result =rs018.getInt(1);
            }    
            System.out.println("The value Obtained"+result); 
   
            
            
            if(testname.equals(name) && test0181==null && result==0)
            {
                System.out.println("TestCaseNO:SF-DB-STORE-018:PASSED");
            }
             
        }
       
        

        catch(Exception e)
        {
            System.out.println("Exception :Case018");
            e.printStackTrace();
 
        } 

    }            


    public void testcase019()
    {
        try{
            String name = "case019";
            mobj= new ManagedObject();
            mobj.setName(name);
             
            transAPI.begin();   
            cds.addObject(mobj);
            transAPI.commit();
        
            ManagedObject test019 =(ManagedObject)cds.getObject(name);
   
            String testname = test019.getName();
            
            String sql ="select NAME  from ManagedObject where NAME='case019'";
        
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs019 =relapi.executeQuery(ps);
            String result = null;
            while(rs019.next())
            {
                result =rs019.getString(1);
            }    
            System.out.println("The value Obtained"+result); 
               
   
            
            
            if(testname.equals(name) && name.equals(result))
            {
                System.out.println("TestCaseNO:SF-DB-STORE-019:PASSED");
            }
             
        }
       
        

        catch(Exception e)
        {
            System.out.println("Exception :Case019");
            e.printStackTrace();
 
        } 

    }            



    public void testcase020()
    {
        try{
            String name = "case020";
            mobj= new ManagedObject();
            mobj.setName(name);
             
            transAPI.begin();   
            cds.addObject(mobj);
            transAPI.rollback();
        
            ManagedObject test020 =(ManagedObject)cds.getObject(name);
   
                        
            if(test020==null)
            {
                System.out.println("TestCaseNO:SF-DB-STORE-020:PASSED");
            }
             
        }
       
        

        catch(Exception e)
        {
            System.out.println("Exception :Case020");
            e.printStackTrace();
 
        } 

    }            



      
    public void testcase021()
    {
        ManagedObject test021 = null;
        try{
            String name = "case021";
            mobj= new ManagedObject();
            mobj.setName(name);
             
            transAPI.begin(5000);   
            cds.addObject(mobj);
            Thread.sleep(6000); 
            transAPI.commit();
        
            test021 =(ManagedObject)cds.getObject(name);
            System.out.println(test021.getName());
  			if(test021 == null )
            {
                System.out.println("TestCaseNO:SF-DB-STORE-021:PASSED");
			}
			else
			{
				System.out.println("TestCaseNO:SF-DB-STORE-021:PASSED");
            }
  
             
        }
   
        catch(Exception e)
        {
            System.out.println("Exception :Case021");
            e.printStackTrace();
 
        } 
                 
    }            



    public void testcase022()
    {
        try{
            
            MyThread1 t1 = new MyThread1();
            t1.setName("testcase022");
            t1.start();  
            Thread.sleep(10000);
            MyThread2 t2 = new MyThread2();
            t2.setName("testcase022");
            t2.start();  
                  
                                
        }

        catch(Exception e)
        {
            System.out.println("Exception :Case022");
            e.printStackTrace();
 
        } 

    }            





    public void testcase024()
    {
       
        try{
            String name = "case024";
            mobj= new ManagedObject();
            mobj.setName(name);
       
            cds.addObject(mobj);

            ManagedObject test024 =(ManagedObject)cds.getObject(name);
   
            String testname = test024.getName();
            System.out.println(" The Object added:"+ testname);
            transAPI.begin();
            cds.deleteObject(name);
            transAPI.commit();
            System.out.println(" The Object deleted");
              
            ManagedObject  test0241 =(ManagedObject)cds.getObject(name);
   
            
            
            if(testname.equals(name) && test0241==null)
            {
                System.out.println("TestCaseNO:SF-DB-STORE-024:PASSED");
            }
             
        }

        catch(Exception e)
        {
            System.out.println("Exception :Case024");
            e.printStackTrace();
 
        } 

    }            

    public void testcase025()
    {
        String testname=null;
        try{
            String name = "case025";
            mobj= new ManagedObject();
            mobj.setName(name);
       
            cds.addObject(mobj);

            ManagedObject test025 =(ManagedObject)cds.getObject(name);
   
            testname = test025.getName();
            System.out.println(" The Object added:"+ testname);
            transAPI.begin();
            cds.deleteObject(name);
            transAPI.rollback();
                         
            ManagedObject  test0251 =(ManagedObject)cds.getObject(name);
            testname = test0251.getName();
   
            if(testname.equals(name))
            {
                System.out.println("TestCaseNO:SF-DB-STORE-025:PASSED");
            }
             
        }

        catch(Exception e)
        {
            System.out.println("Exception :Case025");
            e.printStackTrace();
 
        } 

    }            

    public void testcase026()
    {   String name = null;
    String testname=null;
    try{
        name = "case026";
        mobj= new ManagedObject();
        mobj.setName(name);
       
        cds.addObject(mobj);

        ManagedObject test026 =(ManagedObject)cds.getObject(name);
   
        testname = test026.getName();
        System.out.println(" The Object added:"+ testname);
        transAPI.begin(4000);
        cds.deleteObject(name);
        Thread.sleep(5000);

        transAPI.commit();
            
              
        ManagedObject  test0261 =(ManagedObject)cds.getObject(name);
        System.out.println(test0261.getName());
        testname = test0261.getName();

        if(testname.equals(name))
        {
            System.out.println("TestCaseNO:SF-DB-STORE-026:PASSED");
        }
        else
        {
			System.out.println("TestCaseNO:SF-DB-STORE-026:FAILED");	
        }	

    }

    catch(Exception e)
    {
        System.out.println("Exception :Case026");
 
        e.printStackTrace();
 
    } 

    }            

    public void testcase028()
    {   String name = null;
    String testname=null;
    try{
        name = "case028";
        mobj= new ManagedObject();
        mobj.setName(name);
              
        transAPI.begin(); 
        cds.addObject(mobj);
        cds.deleteObject(name);
        transAPI.commit();
        ManagedObject test028 =(ManagedObject)cds.getObject(name);
        if(test028==null)
        {
            System.out.println("TestCaseNO:SF-DB-STORE-028:PASSED");
        }

                      
    }

    catch(Exception e)
    {
        System.out.println("Exception :Case028");
            
        e.printStackTrace();
 
    } 

    }            
    public void testcase029()
    {   String name = null;
    String testname=null;
    try{
        name = "case029";
        mobj= new ManagedObject();
        mobj.setName(name);
              
        transAPI.begin(); 
        cds.addObject(mobj);
        cds.deleteObject(name);
        transAPI.rollback();
        ManagedObject test029 =(ManagedObject)cds.getObject(name);
        if(test029==null)
        {
            System.out.println("TestCaseNO:SF-DB-STORE-029:PASSED");
        }

                      
    }

    catch(Exception e)
    {
        System.out.println("Exception :Case029");
            
        e.printStackTrace();
 
    } 

    }            



    public void testcase023()
    {   String name = null;
    String testname=null;
    try{
        transAPI.begin(1000); 
             
        for(int i=1;i<200;i++)
        {
            name = "case0000"+i;
            mobj= new ManagedObject();
            mobj.setName(name);
            cds.addObject(mobj);
            Thread.sleep(10);
        } 
                       
        transAPI.commit();

    }
    catch(Exception e)
    {
        System.out.println("Exception in testcase023:"+e);
        e.printStackTrace();
    }
      
    try
    {

        String sql="select count(*) from ManagedObject where name like 'case0000%'";   
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs023 =relapi.executeQuery(ps);
        int result = 0;
        while(rs023.next())
        {
            result =rs023.getInt(1);
        }    
        System.out.println("The value Obtained"+result);             


        if(result==0)
        {
            System.out.println("TestCaseNO:SF-DB-STORE-023:PASSED");
        }

                      
    }

    catch(Exception e)
    {
        System.out.println("Exception :Case023");
            
        e.printStackTrace();
 
    } 

    }            

    public void testcase018_1()
	{

		try{
            String name = "testcase18_1";
            mobj= new ManagedObject();
            mobj.setName(name);
            cds.addObject("ca4",mobj,true);
            String key = new String("ca4");
            ManagedObject test004 =(ManagedObject)cds.getObjectByKey("ca4");
            System.out.println(test004);
            cds.removeFromMemoryTable(key);	
            Object returnvalue = cds.getxx("ca4");
            System.out.println("removed Object" + returnvalue);
            if(returnvalue == null)
            {
                System.out.println("TESTCASE  SF-DB-STORE-018_1  : == PASSED");
            }
            else
            {
                System.out.println("TESTCASE  SF-DB-STORE-018_1  : =FAILED");
            }
		}catch(Exception e)
		{
			System.out.println("Exception       : SF-DB-STORE-018_1");
			e.printStackTrace();
		}

	}

    public void testcase032_2()
    {
        try
        {
            String name = "case032_2";
            mobj= new ManagedObject(); 
            mobj.setName(name);
            cds.addObject(mobj);
            transAPI.begin();
            ManagedObject mo = topo.checkOut("case032_2", 10);
            topo.deleteObject(mo, true, true);

            ThreadFor032_2 th = new ThreadFor032_2();
            th.start();
            Thread.sleep(2000);
            transAPI.commit();
            if (topo.getByName("case032_2") == null)
            {
                if (th.isObjectPresent())
                {
                    System.out.println("Case 32_2 passed");
                }
                else
                {
                    System.out.println("Case 32_2 Failed. The object is not present in the DB even before commit.");
                }
            }
            else
            {
                System.out.println("Case 32_2 Failed. The object is present in the DB even after commit.");
            }
        }
        catch(Exception e)
        {
            System.out.println("Exception testcase SF-DB-STORE-032_2:FAILED" );
            e.printStackTrace();
        }
 
    }

    private class ThreadFor032_2 extends Thread
    {
        boolean isObjectPresent = false;

        public void run()
        {
            try
            {
                if (topo.getByName("case032_2") != null)
                {
                    isObjectPresent = true;
                }
            }
            catch (Exception e)
            {
                System.out.println("Case 32_2 failed");
                e.printStackTrace();
            }
        }

        public boolean isObjectPresent()
        {
            return isObjectPresent;
        }
    }


    public void testcase033()
    {
        try{
			String objname = new String();

			if(cds.getByKey("adddd") == null)
            {
                System.out.println("TestCaseNO:SF-DB-STORE-033:PASSED" );
            }	

        }catch(Exception e)
        {
            System.out.println("Exception testcase SF-DB-STORE-033:FAILED" );
            e.printStackTrace();
        }
 
    }


    public void testcase034()
	{
		try{
			if(cds.getObject("ddddd") == null)
            {
				System.out.println("TestCaseNO:SF-DB-STORE-034:PASSED" );
            }
        }catch(Exception e)
        {
            System.out.println("Exception testcase SF-DB-STORE-034 :FAILED" );
            e.printStackTrace();
        }

	}
	


    public void testcase035()
    {
        try{
             
            if(cds.getObjectByKey("ca35",true) == null)
            {
                System.out.println("TestCaseNO:SF-DB-STORE-035:PASSED");
            }
 
        }
 
        catch(Exception e)
        {
            System.out.println("Exception :Case035");
            e.printStackTrace();
 
        }
    } 




    private class MyThread1 extends Thread
    {
        TransactionAPI tranapi = NmsUtil.relapi.getTransactionAPI();
        public void run()
        {
            try{ 
                ManagedObject mobj2= new ManagedObject();
                String name=Thread.currentThread().getName();
             
                System.out.println("Thread I");
                name="case027";
                mobj2.setName(name);
                cds.addObject(mobj2);
                System.out.println(" Object added");
            
                tranapi.begin(60000);
                cds.deleteObject("case027");
                System.out.println("Object deleted in Thread I");
                Thread.sleep(30000);
                tranapi.commit();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }  
        }

    }

  
    private class MyThread2 extends Thread
    { 
        TransactionAPI tranapi = NmsUtil.relapi.getTransactionAPI();
        ManagedObject mo= null;    
        public void run()
        {
            try{     
                System.out.println(" Thread II startted");
                String name=Thread.currentThread().getName();
                tranapi.begin();
                mo = new ManagedObject();
                ManagedObject test022 = (ManagedObject)cds.getObject("case022");
                System.out.println("Object obtaine"+test022);
                tranapi.commit();
                if(test022==null)
                {
                    System.out.println("TestCaseNO:SF-DB-STORE-022:PASSED"); 
                }  
            }
   
            catch(Exception e)
            {
                e.printStackTrace();
            }  
        }

    }


    public void testcase036()
	{
 
        try{
            
			String name = "case036";
            mobj= new ManagedObject();
            mobj.setName(name);
 
            cds.addObject(mobj);
 
            ManagedObject test036 =(ManagedObject)cds.getObject(name);
 
            String testname = test036.getName();
            cds.deleteObject(test036); 
		
        }catch(Exception e)
        {
			
            System.out.println("Exception :Case036");
            e.printStackTrace();
        }
	}


    public void testcase037()
	{
		try{
            cds.deleteObject("testcase037");
            ManagedObject mobj37 =(ManagedObject)cds.getObject("testcase037"); 
			if(mobj37 == null)
			{
				System.out.println("TestCaseNO:SF-DB-STORE-037:PASSED");
			}
			else
			{
				System.out.println("TestCaseNO:SF-DB-STORE-037:FAILED");
			}
        }catch(Exception e)
        {
            System.out.println("Exception :Case037");
            e.printStackTrace();
        }
	}		



    public void testcase038()
	{
		 
		try{
            String name = "testcase037";
            mobj = new ManagedObject();
            mobj.setName(name);
            mobj.setDisplayName("aravind");
            cds.updateObject(mobj);
			ManagedObject mo1 = topo.getByName("testcase037");
			String s = mo1.getDisplayName();

			if(s.equals("aravind"))
			{
				System.out.println("TestCaseNO:SF-DB-STORE-038:PASSED");
			}
			else
			{
				System.out.println("TestCaseNO:SF-DB-STORE-038:FAILED");
		    }		
			 
		}
		
		catch(Exception e)
		{
			System.out.println("Exception :Case038");
			e.printStackTrace();		
		}
	}



    public void testcase039()
    {
 
        try{
 
            //String nametest =cds.getNameByKey("ca66666");
            //System.out.println("key    " + nametest);
 
            if(cds.getNameByKey("ca66666") == null)
            {
                System.out.println("TestCaseNO:SF-DB-STORE-039:PASSED");
            }
            else
            {
                System.out.println("TestCaseNO:SF-DB-STORE-039:FAILED");
			} 	
 
        }
 
        catch(Exception e)
        {
            System.out.println("Exception :Case039");
            e.printStackTrace();
 
        }
 
    } 

    public void testcase040()
    {
 
        try{
            String name = "case2141";
            mobj= new ManagedObject();
            mobj.setName(name);
            cds.addObject(mobj);
 
            ManagedObject test040 =(ManagedObject)cds.getObject(name);
            String nametest = test040.getDisplayName();
            System.out.println(nametest);
            test040.setDisplayName("kumar");
 
            cds.updateObjectInMemoryOnly(test040);
            ManagedObject test411 =(ManagedObject)cds.getObject("case2141",true);
 
            System.out.println("updateobjectinmemory " +     test411.getDisplayName());
 
 
            String sql ="select DISPLAYNAME  from ManagedObject where NAME='case2141'";
 
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs040 =relapi.executeQuery(ps);
            String result = null;
            while(rs040.next())
            {
                result =rs040.getString(1);
            }
            System.out.println("The value Obtained  "     +result);
 
 
            if(name.equals(nametest)&& name.equals(result))
            {
                System.out.println("TestCaseNO:SF-DB-STORE-040:PASSED");
            }
            else 
			{
                System.out.println("TestCaseNO:SF-DB-STORE-040:FAILED");
            }
        }
 
        catch(Exception e)
        {
            System.out.println("Exception :Case040");
            e.printStackTrace();
 
        }
    }  


    public void testcase046()
    {
        try
        {
            ManagedObject mo = new ManagedObject();
            mo.setName("case46");
            cds.addObject(mo);

            state.executeUpdate("delete from ManagedObject where name like 'case46'");
            if (cds.isObjectInMemory("case46"))
            {
                System.out.println("Case 046 Passed");
            }
            else
            {
                System.out.println("Case 046 failed");
            }
        }
        catch (Exception e)
        {
            System.out.println("Case 046 failed");
            e.printStackTrace();
        }
    }


    public void testcase047()
    {
        try
        {
            state.executeUpdate("insert into managedobject (name, ownername, displayname, parentkey) values ('case47', 'NULL', 'case47', 'NULL')");
            if (! cds.isObjectInMemory("case47"))
            {
                System.out.println("Case 047 Passed");
            }
            else
            {
                System.out.println("Case 047 failed");
            }
        }
        catch (Exception e)
        {
            System.out.println("Case 047 failed");
            e.printStackTrace();
        }
    }


    public void testcase049()
    {
        try
        {
            transAPI.begin();
            ManagedObject mo = new ManagedObject();
            mo.setName("case49");
            cds.addObject(mo);

            boolean flag1 = cds.isObjectInMemory("case49");
            transAPI.commit();
            if (flag1 && cds.isObjectInMemory("case49"))
            {
                System.out.println("Case 049 Passed");
            }
            else
            {
                System.out.println("Case 049 failed");
                System.out.println("Flag 1 : " + flag1);
            }
        }
        catch (Exception e)
        {
            System.out.println("Case 049 failed");
            e.printStackTrace();
        }
    }

    public void testcase050()
    {
        try
        {
            transAPI.begin();
            ManagedObject mo = new ManagedObject();
            mo.setName("case50");
            cds.addObject(mo);

            boolean flag1 = cds.isObjectInMemory("case50");
            try
            {
                transAPI.rollback();
            }catch (Exception e){}
            if (flag1 && ! cds.isObjectInMemory("case50"))
            {
                System.out.println("Case 050 Passed");
            }
            else
            {
                System.out.println("Case 050 failed");
                System.out.println("Flag 1 : " + flag1);
            }
        }
        catch (Exception e)
        {
            System.out.println("Case 050 failed");
            e.printStackTrace();
        }
    }

    public void testcase051()
    {
        try
        {
            transAPI.begin();
            ManagedObject mo = new ManagedObject();
            mo.setName("case51");
            cds.addObject(mo);

            boolean flag1 = cds.isObjectInMemory("case51");
            ThreadFor051 th = new ThreadFor051();
            th.start();
            Thread.sleep(2000);
            try
            {
                transAPI.rollback();
            } catch (Exception e){}

            boolean flag2 = th.isObjectPresent();

            if (flag1 && !flag2 && ! cds.isObjectInMemory("case51"))
            {
                System.out.println("Case 051 Passed");
            }
            else
            {
                System.out.println("Case 051 failed");
                System.out.println("Flag 1 : " + flag1);
                System.out.println("Flag 2 : " + flag2);
            }
        }
        catch (Exception e)
        {
            System.out.println("Case 051 failed");
            e.printStackTrace();
        }
    }

    private class ThreadFor051 extends Thread
    {
        boolean isObjectPresent = false;

        public void run()
        {
            try
            {
                transAPI.begin();
                if (cds.isObjectInMemory("case51"))
                {
                    isObjectPresent = true;
                }
                transAPI.commit();
            }
            catch (Exception e)
            {
                System.out.println("Case 51 failed");
                e.printStackTrace();
            }
        }

        public boolean isObjectPresent()
        {
            return isObjectPresent;
        }
    }

    public void testcase056()
    {
        ResultSet rs = null;

        try
        {
            Vector vect = new Vector();
            for (int i = 0; i < 50; i++)
            {
                ManagedObject mo = new ManagedObject();
                mo.setName("Case056-" + i);
                vect.add(mo);
            }
            transAPI.begin(-1);
            relMo.addBatchObjects(vect);
            cds.addObjects(vect);
            transAPI.commit();

            state = conn.createStatement();
            rs = state.executeQuery("select name from managedobject where name like 'Case056-%'");
            int count = 0;
            while (rs.next())
            {
                count++;
            }
            System.out.println("No. of object added : " + count);
            if (count == 50)
            {
                System.out.println("TEST CASE 056 PASSED");
            }
            else
            {
                System.out.println("TEST CASE 056 FAILED");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Test Case 56 FAILED");
        }
        finally
        {
            try
            {
                rs.close();
            }
            catch (Exception e){}
        }
    }

    public void testcase057()
    {
        ResultSet rs = null;

        try
        {
            Vector vect = new Vector();
            for (int i = 0; i < 50; i++)
            {
                ManagedObject mo = new ManagedObject();
                mo.setName("Case057-" + i);
                vect.add(mo);
            }
            transAPI.begin(-1);
            relMo.addBatchObjects(vect);
            cds.addObjects(vect);
            try
            {
                transAPI.rollback();
            }
            catch (Exception e)
            {
            }

            state = conn.createStatement();
            rs = state.executeQuery("select name from managedobject where name like 'Case057-%'");
            int count = 0;
            while (rs.next())
            {
                count++;
            }
            System.out.println("No. of object added : " + count);
            if (count == 0)
            {
                System.out.println("TEST CASE 057 PASSED");
            }
            else
            {
                System.out.println("TEST CASE 057 FAILED");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Test Case 57 FAILED");
        }
        finally
        {
            try
            {
                rs.close();
            }
            catch (Exception e){}
        }
    }

    // Deprecated
    public void testcase058()
    {
        ResultSet rs = null;

        try
        {
            Vector vect = new Vector();
            for (int i = 0; i < 50; i++)
            {
                ManagedObject mo = new ManagedObject();
                mo.setName("Case058-" + i);
                vect.add(mo);
            }
            relMo.addBatchObjects(vect);
            cds.addObjects(vect);

            state = conn.createStatement();
            rs = state.executeQuery("select name from managedobject where name like 'Case058-%'");
            int count = 0;
            while (rs.next())
            {
                count++;
            }
            System.out.println("No. of object added : " + count);
            if (count != 50)
            {
                System.out.println("TEST CASE 058 PASSED");
            }
            else
            {
                System.out.println("TEST CASE 058 FAILED");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Test Case 58 FAILED");
        }
        finally
        {
            try
            {
                rs.close();
            }
            catch (Exception e){}
        }
    }

    public void testcase059()
    {
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        try
        {
            Vector vect = new Vector();
            for (int i = 0; i < 50; i++)
            {
                ManagedObject mo = new ManagedObject();
                mo.setName("Case059-" + i);
                mo.setUserProperty("UserProp", "SomeValue-" + i);
                vect.add(mo);
            }
            transAPI.begin(-1);
            relMo.addBatchObjects(vect);
            cds.addObjects(vect);
            transAPI.commit();

            state = conn.createStatement();
            rs1 = state.executeQuery("select name from managedobject where name like 'Case059-%'");
            int count1 = 0;
            while (rs1.next())
            {
                count1++;
            }
            System.out.println("No. of objects added in ManagedObject Table: " + count1);

            rs2 = state.executeQuery("select name from topouserprops where name like 'Case059-%'");
            int count2 = 0;
            while (rs2.next())
            {
                count2++;
            }
            System.out.println("No. of objects added in TopoUserProps Table: " + count2);

            if (count1 == 50 && count2 == 50)
            {
                System.out.println("TEST CASE 059 PASSED");
            }
            else
            {
                System.out.println("TEST CASE 059 FAILED");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Test Case 59 FAILED");
        }
        finally
        {
            try
            {
                rs1.close();
                rs2.close();
            }
            catch (Exception e){}
        }
    }

    public void testcase060()
    {
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        try
        {
            Vector vect = new Vector();
            for (int i = 0; i < 50; i++)
            {
                ManagedObject mo = new ManagedObject();
                mo.setName("Case060-" + i);
                vect.add(mo);
            }
            transAPI.begin(-1);
            relMo.addBatchObjects(vect);
            cds.addObjects(vect);
            transAPI.commit();

            state = conn.createStatement();
            rs1 = state.executeQuery("select name from managedobject where name like 'Case060-%'");
            int count1 = 0;
            while (rs1.next())
            {
                count1++;
            }
            System.out.println("No. of objects added in ManagedObject Table: " + count1);

            rs2 = state.executeQuery("select name from topouserprops where name like 'Case060-%'");
            int count2 = 0;
            while (rs2.next())
            {
                count2++;
            }
            System.out.println("No. of objects added in TopoUserProps Table: " + count2);

            if (count1 == 50 && count2 == 0)
            {
                System.out.println("TEST CASE 060 PASSED");
            }
            else
            {
                System.out.println("TEST CASE 060 FAILED");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Test Case 60 FAILED");
        }
        finally
        {
            try
            {
                rs1.close();
                rs2.close();
            }
            catch (Exception e){}
        }
    }

    public void testcase061()
    {
        ResultSet rs = null;
        try
        {
            Vector vect = new Vector();
            for (int i = 0; i < 50; i++)
            {
                ManagedObject mo = new ManagedObject();
                mo.setName("Case061-" + i);
                vect.add(mo);
            }
            transAPI.begin(-1);
            relMo.addBatchObjects(vect);
            cds.addObjects(vect);
            transAPI.commit();

            Properties props = new Properties();
            props.setProperty("name", "Case061-*");
            Vector moList = topo.getObjects("ManagedObject", props);
            for (int i = 0; i < moList.size(); i++)
            {
                ManagedObject mo = (ManagedObject) moList.elementAt(i);
                mo.setManaged(false);
            }

            transAPI.begin(-1);
            relMo.updateObjects(moList);
            cds.updateObjects(moList);
            transAPI.commit();


            state = conn.createStatement();
            rs = state.executeQuery("select name, managed from managedobject where name like 'Case061-%'");
            int count = 0;
            boolean isUpdated = true;
            while (rs.next())
            {
                String managed = rs.getString(2);
                if (managed.equalsIgnoreCase("true"))
                {
                    isUpdated = false;
                    System.out.println("Case 061 : Object not updated for : " + rs.getString(0));
                }
                else
                {
                    count++;
                }
            }
            System.out.println("No. of objects successfully updated : " + count);

            if (count == 50)
            {
                System.out.println("TEST CASE 061 PASSED");
            }
            else
            {
                System.out.println("TEST CASE 061 FAILED");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Test Case 061 FAILED");
        }
        finally
        {
            try
            {
                rs.close();
            }
            catch (Exception e){}
        }
    }

    public void testcase062()
    {
        ResultSet rs = null;
        try
        {
            Vector vect = new Vector();
            for (int i = 0; i < 50; i++)
            {
                ManagedObject mo = new ManagedObject();
                mo.setName("Case062-" + i);
                vect.add(mo);
            }
            transAPI.begin(-1);
            relMo.addBatchObjects(vect);
            cds.addObjects(vect);
            transAPI.commit();

            Properties props = new Properties();
            props.setProperty("name", "Case062-*");
            Vector moList = topo.getObjects("ManagedObject", props);

            transAPI.begin(-1);
            relMo.deleteObjects(moList);
            cds.deleteObjects(moList);
            transAPI.commit();

            state = conn.createStatement();
            rs = state.executeQuery("select name, managed from managedobject where name like 'Case062-%'");
            int count = 0;
            while (rs.next())
            {
                count++;
            }
            System.out.println("No. of objects present in DB : " + count);

            if (count == 0)
            {
                System.out.println("TEST CASE 062 PASSED");
            }
            else
            {
                System.out.println("TEST CASE 062 FAILED");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Test Case 062 FAILED");
        }
        finally
        {
            try
            {
                rs.close();
            }
            catch (Exception e){}
        }
    }

    public void testcase063()
    {
        ResultSet rs = null;
        try
        {
            Vector vect = new Vector();
            for (int i = 0; i < 50; i++)
            {
                ManagedObject mo = new ManagedObject();
                mo.setName("Case063-" + i);
                vect.add(mo);
            }
            transAPI.begin(-1);
            relMo.addBatchObjects(vect);
            cds.addObjects(vect);
            transAPI.commit();

            Properties props = new Properties();
            props.setProperty("name", "Case063-*");
            Vector moList = topo.getObjects("ManagedObject", props);

            transAPI.begin(-1);
            relMo.deleteObjects(moList);
            cds.deleteObjects(moList);
            try
            {
                transAPI.rollback();
            }
            catch (Exception e)
            {
            }

            state = conn.createStatement();
            rs = state.executeQuery("select name, managed from managedobject where name like 'Case063-%'");
            int count = 0;
            while (rs.next())
            {
                count++;
            }
            System.out.println("No. of objects present in DB : " + count);

            if (count == 50)
            {
                System.out.println("TEST CASE 063 PASSED");
            }
            else
            {
                System.out.println("TEST CASE 063 FAILED");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Test Case 063 FAILED");
        }
        finally
        {
            try
            {
                rs.close();
            }
            catch (Exception e){}
        }
    }


    public void exec()
    {
        /*
          testcase008_1();
          testcase024();
          testcase032_2();
          testcase012();
          testcase032_2();
          testcase046();
          testcase047();
          testcase049();
          testcase050();
          testcase051();
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
          testcase013();
          testcase014();
          testcase015();
          testcase016();
          testcase017();
          testcase018();
          testcase018_1();
          testcase019();
          testcase024();
          testcase020();
          testcase021();
          testcase022();
          testcase023();
          testcase025();
          testcase026();
          testcase028();
          testcase029();
          testcase033();
          testcase034();
          testcase035();
          testcase036();
          testcase037();
          testcase038();
          testcase039();
          testcase040();
          testcase064();
        */
        testcase018_1();
        testcase032_2();
        testcase046();
        testcase047();
        testcase049();
        testcase050();
        testcase051();
        testcase056();
        testcase057();
        //testcase058(); Deprecated
        testcase059();
        testcase060();
        testcase061();
        testcase062();
        testcase063();
        testcase064();
        System.out.println(" ALL test cases executed");
    }

    private void testcase030()
    {
        System.out.println("--------------------------------------------------------------------------------");
        ManagedObject mo = new ManagedObject();
        mo.setName("TestObject030");
        mo.setType("Node");
        try
        {
            cds.addObject(mo);
            System.out.println("GET object : " + cds.getObject("TestObject030"));
            System.out.println("Added the MO " + Thread.currentThread());
            transAPI.begin(35000);
            System.out.println("Transaction began 35000");
            cds.deleteObject("TestObject030");
            System.out.println("Delete object called");
            TestCase030Helper th = new TestCase030Helper("TestObject030");
            Thread.sleep(20000);
            try
            {
                transAPI.commit();
                System.out.println("Committed 35000");
            } catch (Exception e) {}
            Thread.sleep(10000);
            if (th.getTestResult())
            {
                System.out.println("030 passed");
            }
            else
            {
                System.out.println("030 Failed");
            }
        }
        catch (Exception e)
        {
        }
        System.out.println("--------------------------------------------------------------------------------");
    }

    private class TestCase030Helper extends Thread
    {
        String objName = null;
        private boolean testResult = false;

        public TestCase030Helper(String objName)
        {
            this.objName = objName;
            start();
        }

        public boolean getTestResult()
        {
            return testResult;
        }

        public void run()
        {
            System.out.println("Helper started");
            try
            {
                transAPI.begin(-1);
                System.out.println("Transaction began -1 : " + Thread.currentThread());
            } catch (Exception e){}
            try
            {
                ManagedObject mo = (ManagedObject) cds.getObject(objName);
                System.out.println("MO is : " +  mo);
                mo.setDisplayName("NEWNAME");
                cds.updateObject(mo);
                System.out.println("Updateobject called");
                testResult = false;
            }
            catch (Exception e)
            {
                e.printStackTrace();
                testResult = true;
            }
            try
            {
                transAPI.rollback();
            } catch (Exception ee){}
        }
    }

    private void testcase064()
    {
        System.out.println("--------------------------------------------------------------------------------");
        ManagedObject mo = new ManagedObject();
        String name = "TestMO-" + System.currentTimeMillis();
        mo.setName(name);
        try
        {
            cds.addObject(mo);
            System.out.println("isObjectInMemory before clearMemory() : " + cds.isObjectInMemory(name));
            cds.clearMemory();
            System.out.println("isObjectInMemory after clearMemory() : " + cds.isObjectInMemory(name));

            System.out.println("isObjectInMemory before wipeOutThingsFromMemoryTable() : " + cds.isObjectInMemory(name));
            cds.wipeOutThingsFromMemoryTable();
            System.out.println("isObjectInMemory after wipeOutThingsFromMemoryTable() : " + cds.isObjectInMemory(name));

            transAPI.begin();
            System.out.println("The object got is : " + cds.getObject(name));
            boolean stat1 = cds.isObjectInMemory(name);
            System.out.println("isObjectInMemory : " +  stat1);
            try
            {
                transAPI.rollback();
            }
            catch (Exception e)
            {
            }
            boolean stat2 = cds.isObjectInMemory(name);
            System.out.println("isObjectInMemory : " + stat2);
            if (stat1 && stat2)
            {
                System.out.println("CASE 064 PASSED");
            }
            else
            {
                System.out.println("CASE 064 FAILED");
            }
        }
        catch (Exception e)
        {
            System.out.println("CASE 064 FAILED");
        }
        System.out.println("--------------------------------------------------------------------------------");
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


    private class Getname extends Thread
    {
        public void run()
        {
            try
            {
                ManagedObject mo1 = (ManagedObject)topo.getByName("testcase040");
                String name = mo1.getName();
                if(name.equals("testcase040"))
                {
                    System.out.println("TestCaseNO:SF-DB-STORE-040:PASSED");
                }
		
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
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
