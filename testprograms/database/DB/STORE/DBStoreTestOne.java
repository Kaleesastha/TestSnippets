package com.adventnet.nms.store.relational;
import java.sql.*;
import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.adventnet.nms.store.*;
import com.adventnet.nms.util.*;
import com.adventnet.management.transaction.*;
import java.util.*;
import com.adventnet.nms.eventdb.*;
import com.adventnet.nms.store.relational.*;
import com.adventnet.nms.topodb.ManagedObject;
public class DBStoreTestOne implements RunProcessInterface
{
    RelationalAPI relapi=null;
    CommonDBStoreExtnForEvent cds = null;
    RelationalEvent relEvent=null;

    CommonDBStoreExtnForManagedObject cdsMo= null;
    RelationalManagedObject relMo =null;

    TransactionAPI transApi= null;
    public boolean isInitialized()
    {
        return true;        
    }

    public void shutDown()
    {
        
    }
    public void callMain(String args[])
    {
        try
        {
            Thread.sleep(20000);//Wait for Framework initialization
            relapi=NmsUtil.relapi;
            transApi=relapi.getTransactionAPI();
            
            cds=new CommonDBStoreExtnForEvent(150,true);
            relEvent = new RelationalEvent();
            relEvent.init(relapi);
            relEvent.setUserPropTableName("EVENTUSERPROPS");
            
            cdsMo = new CommonDBStoreExtnForManagedObject(150,true);
            
                        
            //DBSTORE012();
            //DBSTORE018_1();
            //DBSTORE027();
            //DBSTORE030();
            //DBSTORE031();
            //DBSTORE032();
            //DBSTORE032_1();
            //DBSTORE032_2();
            //DBSTORE032_3();
            //DBSTORE032_4();
            //DBSTORE048();
            //DBSTORE049();
            //DBSTORE050();
            //DBSTORE051();
            //DBSTORE052();
            //DBSTORE053();
            //DBSTORE054();
            //DBSTORE055();
            DBSTORE056();
            //DBSTORE057();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void DBSTORE012()
    {
        try
        {
            Properties myProp=new Properties();
            myProp.setProperty("sunil","linus");
            
            for(int i=0;i<10;i++)
            {                
                Event evt = new Event();
                String source = "customer";
                evt.setSource(source);
                evt.setEntity(source);
                evt.setCategory("cust");
                evt.setText("cust");
                evt.setSeverity(3);
                evt.setId(i);
                evt.setProperties(myProp);
                cds.addObject(evt);
            }
                        
            Properties criteria= new Properties();
            criteria.setProperty("sunil","li*");
            
            Vector vec=cds.getObjectNamesWithProps(criteria);
            System.err.println("Test Case -- DBSTORE012");
            System.err.println("Vector of Event Objects with IDs ::: "+vec);
        }
        
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
    }


    public void DBSTORE018_1()
    {
        try
        {
            Event evt = new Event();
            String source = "customer";
            evt.setSource(source);
            evt.setEntity(source);
            evt.setCategory("cust");
            evt.setText("cust");
            evt.setSeverity(3);
            evt.setId(3);
            cds.addObject(evt);
            System.err.println("Test Case -- DBSTORE018.1");
            System.err.println("Object is in Memory --> "+cds.isObjectInMemory("3"));
            cds.removeFromMemoryTable("3");
            System.err.println("Now Removed...Object is in Memory --> "+cds.isObjectInMemory("3"));
        }
        
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
    }
    public void DBSTORE027()
    {
        try
        {
            Event evt = new Event();
            String source = "customer";
            evt.setSource(source);
            evt.setEntity(source);
            evt.setCategory("cust");
            evt.setText("cust");
            evt.setSeverity(3);
            evt.setId(99);
            cds.addObject(evt);
            
            DbStoreOne ds =new DbStoreOne();
            ds.start();
            
            transApi.begin();
            cds.deleteObject(evt);
            Thread.sleep(10000);
            transApi.commit();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
    }

    private class DbStoreOne extends Thread
    {
        public void run()
        {
            try
            {
                Object obj=cds.getObject("99");            
                if(obj!=null)
                {
                    System.err.println("Got the Object....DB-STORE-027 --> PASSED");
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
    
    public void DBSTORE030()//Not Working
    {
        try
        {
            
            ManagedObject mo= new ManagedObject();
            mo.setName("030");
            cdsMo.addObject(mo);

            DbStoreTwo ds =new DbStoreTwo();
            ds.start();
            
            transApi.begin(35000);
            Thread.sleep(3000);
            cdsMo.deleteObject(mo);
            System.err.println("DB_STORE-030:: Deleted ");
            Thread.sleep(10000);
            transApi.commit();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    private class DbStoreTwo extends Thread
    {
        public void run()
        {
            
            try
            {
                transApi.begin(-1);
                ManagedObject mo=(ManagedObject)cdsMo.getObject("030");            
                Thread.sleep(5000);
                if(mo!=null)
                {
                    mo.setName("Changed");
                    cdsMo.updateObject(mo);
                    System.err.println("DB_STORE-030:: Updated ");
                }
                transApi.commit();
            }
            catch(Exception e)
            {
                System.err.println("DB-STORE-030- PASSED--If NmsStorageException throws");
                e.printStackTrace();
                
            }
        }
    }


    public void DBSTORE031()
    {
        try
        {
            ManagedObject mo= new ManagedObject();
            mo.setName("031");
            cdsMo.addObject(mo);

            DbStoreThree ds =new DbStoreThree();
            ds.start();
            
            transApi.begin(35000);
            mo.setName("changed");
            cdsMo.updateObject(mo);
            System.err.println("DB_STORE-031:: Updated ");
            Thread.sleep(20000);
            transApi.commit();
            
            Object obj=cdsMo.getObject("031");
            if(obj==null)
            {
                System.err.println("DP-STORE-031 --> PASSED");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    private class DbStoreThree extends Thread
    {
        public void run()
        {
            
            try
            {
                transApi.begin(-1);
                Thread.sleep(10000);
                ManagedObject mo=(ManagedObject)cdsMo.getObject("031");            
                
                if(mo!=null)
                {
                    cdsMo.deleteObject(mo);
                    System.err.println("DB_STORE-031:: Deleted ");
                }
                transApi.commit();
            }
            catch(Exception e)
            {
                e.printStackTrace();
                
            }
        }
    }



    public void DBSTORE032()
    {
        try
        {
            ManagedObject mo= new ManagedObject();
            mo.setName("032");
            cdsMo.addObject(mo);

            DbStoreFour ds =new DbStoreFour();
            ds.start();
            
            transApi.begin(35000);
            mo.setDisplayName("FirstChange");
            cdsMo.updateObject(mo);
            Thread.sleep(20000);
            transApi.commit();
            
            Thread.sleep(10000);
            mo=(ManagedObject)cdsMo.getObject("032");
            String dispName=mo.getDisplayName();
            
            if(dispName.equalsIgnoreCase("SecondChange"))
            {
                System.err.println("TestCase DB-STORE-032 --> PASSED");
            }
            
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    private class DbStoreFour extends Thread
    {
        public void run()
        {
            
            try
            {
                transApi.begin(-1);
                Thread.sleep(10000);
                ManagedObject mo=(ManagedObject)cdsMo.getObject("032");            
            
                if(mo!=null)
                {
                    mo.setDisplayName("SecondChange");
                    cdsMo.updateObject(mo);
                }
                transApi.commit();
            }
            catch(Exception e)
            {
                e.printStackTrace();
                
            }
        }
    }


    public void DBSTORE032_1()
    {
        try
        {
            ManagedObject mo= new ManagedObject();
            mo.setName("032_1");
            cdsMo.addObject(mo);

            DbStoreFive ds =new DbStoreFive();
            ds.start();
            
            transApi.begin(35000);
            mo.setDisplayName("First");
            cdsMo.deleteObject(mo);
            System.err.println("DB-STORE-32_1:::--> DELETED");
            Thread.sleep(20000);
            transApi.commit();
            
            Thread.sleep(10000);
            mo=(ManagedObject)cdsMo.getObject("032_1");
                        
            if(mo==null)
            {
                System.err.println("TestCase DB-STORE-032.1 --> PASSED");
            }
            
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    private class DbStoreFive extends Thread
    {
        public void run()
        {
            
            try
            {
                transApi.begin(-1);
                Thread.sleep(10000);
                ManagedObject mo=(ManagedObject)cdsMo.getObject("032_1");            
            
                if(mo!=null)
                {
                    mo.setDisplayName("Second");
                    cdsMo.updateObject(mo);
                    System.err.println("DB-STORE-32_1:::--> UPDATED BUT RollBacked");
                }
                transApi.rollback();

            }
            catch(Exception e)
            {
                e.printStackTrace();
                
            }
        }
    }


    public void DBSTORE032_2()
    {
        try
        {
            ManagedObject mo= new ManagedObject();
            mo.setName("032_2");
            cdsMo.addObject(mo);

            DbStoreSix ds =new DbStoreSix();
            ds.start();
            
            transApi.begin(35000);
            cdsMo.deleteObject(mo);
            System.err.println("DB-STORE-32.2:::--> DELETED");
            Thread.sleep(20000);
            transApi.commit();
            
            Thread.sleep(10000);
            mo=(ManagedObject)cdsMo.getObject("032_2");
                        
            if(mo==null)
            {
                System.err.println("TestCase DB-STORE-032.2 --> PASSED");
            }
            else
                System.err.println("TestCase DB-STORE-032.2 --> FAILED");
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    private class DbStoreSix extends Thread
    {
        public void run()
        {
            
            try
            {
                Thread.sleep(10000);
                ManagedObject mo=(ManagedObject)cdsMo.getObject("032_2");            
            
                if(mo!=null)
                {
                    System.err.println("DB-STORE-32.2:::--> Got the Object");
                }
                
            }
            catch(Exception e)
            {
                e.printStackTrace();
                
            }
        }
    }



    public void DBSTORE032_3()
    {
        try
        {
            ManagedObject mo= new ManagedObject();
            mo.setName("032_3");
            cdsMo.addObject(mo);

            DbStoreSeven ds =new DbStoreSeven();
            ds.start();
            
            transApi.begin(35000);
            cdsMo.deleteObject(mo);
            System.err.println("DB-STORE-32.3:::--> DELETED");
            transApi.rollback();

            
                     
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
            try
            {
                ManagedObject mo=(ManagedObject)cdsMo.getObject("032_3");
                
                if(mo!=null)
                {
                    System.err.println("TestCase DB-STORE-032.3 --> PASSED");
                }
                else
                    System.err.println("TestCase DB-STORE-032.3 --> FAILED");
            }
            catch(Exception ee)
            {
                e.printStackTrace();
            }
        }
    }
    private class DbStoreSeven extends Thread
    {
        public void run()
        {
            
            try
            {
                transApi.begin(-1);
                Thread.sleep(10000);
                ManagedObject mo=(ManagedObject)cdsMo.getObject("032_3");            
            
                if(mo!=null)
                {
                    System.err.println("DB-STORE-32.3:::--> Got the Object");
                }
                transApi.rollback();
                
            }
            catch(Exception e)
            {
                e.printStackTrace();
                try
                {
                    ManagedObject mo=(ManagedObject)cdsMo.getObject("032_3");
                    if(mo!=null)
                    {
                        System.err.println("TestCase DB-STORE-032.3 --> PASSED");
                    }
                    else
                    System.err.println("TestCase DB-STORE-032.3 --> FAILED");
                }
                catch(Exception ee)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    public void DBSTORE032_4()
    {
        try
        {
            ManagedObject mo= new ManagedObject();
            mo.setName("032_4");
            mo.setDisplayName("FirstChange");
            cdsMo.addObject(mo);
            
            DbStoreEight ds =new DbStoreEight();
            ds.start();
            
            transApi.begin(35000);
            cdsMo.deleteObject(mo);
            System.err.println("DB-STORE-32.4:::--> DELETED");
            Thread.sleep(20000);
            transApi.rollback();
            
                        
        }
        catch(Exception e)
        {
            e.printStackTrace();
            try
            {
                ManagedObject mo=(ManagedObject)cdsMo.getObject("032_4");
                
                if(mo!=null)
                {
                    System.err.println("TestCase DB-STORE-032.4 --> PASSED");
                }
                else
                    System.err.println("TestCase DB-STORE-032.4 --> FAILED");
            }
            catch(Exception ee)
            {
                e.printStackTrace();
            }
        }
    }
    private class DbStoreEight extends Thread
    {
        public void run()
        {

            try
            {
                transApi.begin(-1);
                Thread.sleep(10000);
                ManagedObject mo=(ManagedObject)cdsMo.getObject("032_4");            
            
                if(mo!=null)
                {
                    System.err.println("DB-STORE-32.4:::--> Got the Object");
                    mo.setDisplayName("SecondChange");
                    cdsMo.updateObject(mo);
                }
                transApi.rollback();
            }
            catch(Exception e)
            {
                e.printStackTrace();
                try
                {
                    ManagedObject mo=(ManagedObject)cdsMo.getObject("032_4");            
                    String dispName=mo.getDisplayName();
                    if(dispName.equalsIgnoreCase("FirstChange"))
                    {
                        System.err.println("Test Case DB-STORE-32.4:: -->  PASSED");
                    }
                    else
                        System.err.println("Test Case DB-STORE-32.4:: -->  FAILED");
                }
                catch(Exception ee)
                {
                    ee.printStackTrace();
                }
            }
        }
    }

    public void DBSTORE048()
    {
        boolean flag=cdsMo.isObjectInMemory("Invalid");
        if(!flag)
        {
            System.err.println("Test Case DB-STORE-048 --> PASSED");
        }
    }
    public void DBSTORE049()
    {
        try
        {
            transApi.begin();
            ManagedObject mo = new ManagedObject();
            mo.setName("049");
            cdsMo.addObject(mo);
            boolean flag1=cdsMo.isObjectInMemory("049");
            transApi.commit();
            
            boolean flag2 = cdsMo.isObjectInMemory("049");
            if(flag1&&flag2)
                System.err.println("DB-STORE-049 --> PASSED");
            else
                System.err.println("DB-STORE-049 --> FAILED");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void DBSTORE050()
    {
        boolean flag1=false;
        try
        {
            transApi.begin();
            ManagedObject mo = new ManagedObject();
            mo.setName("050");
           
            cdsMo.addObject(mo);
            flag1=cdsMo.isObjectInMemory("050");
            transApi.rollback();
           
        }
        catch(Exception e)
        {
            e.printStackTrace();
            boolean flag2 = cdsMo.isObjectInMemory("050");
            if(flag1&&!flag2)
                System.err.println("DB-STORE-050 --> PASSED");
            else
                System.err.println("DB-STORE-050 --> FAILED");
        }

    }


    public void DBSTORE051()
    {
        
        DBStoreNine dbs= new DBStoreNine();
        dbs.start();
        try
        {
            transApi.begin();
            for(int i=0;i<10;i++)
            {
                ManagedObject mo = new ManagedObject();
                mo.setName("051"+i); 
                cdsMo.addObject(mo);
            }
            Thread.sleep(5000);
            System.err.println("Not Yet Rolled Back");
            transApi.rollback();
        }
        catch(Exception e)
        {
            try
            {
                e.printStackTrace();
                ManagedObject mObj1=(ManagedObject)cdsMo.getObject("0511");
                ManagedObject mObj2=(ManagedObject)cdsMo.getObject("0512");
                if(mObj1!=null && mObj2!=null)
                    System.err.println("Test Case DB-STORE-051-FAILED");
                else
                    System.err.println("Test Case DB-STORE-051-PASSED");
            }
            catch(Exception ee)
            {
                e.printStackTrace();
            }
        }
    }
    
    private class DBStoreNine extends Thread
    {
        public void run()
        {
            try
            {
                transApi.begin();
                Thread.sleep(2000);
                ManagedObject mObj1=(ManagedObject)cdsMo.getObject("0511");
                ManagedObject mObj2=(ManagedObject)cdsMo.getObject("0512");
                if(mObj1!=null && mObj2!=null)
                    System.err.println("Test Case DB-STORE-051-FAILED");
                else
                    System.err.println("Test Case DB-STORE-051''-PASSED");
                transApi.commit();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
    
    public void DBSTORE052()
    {
        try
        {
            ManagedObject mo = new ManagedObject();
            mo.setName("052"); 
            cdsMo.addObject(mo);

            transApi.begin();
            cdsMo.removeFromMemoryTable("052");
            boolean flag=cdsMo.isObjectInMemory("052");
            if(!flag) 
                System.err.println("Test Case DB-STORE-052 --> PASSED");
            else
                System.err.println("Test Case DB-STORE-052 --> FAILED");
            transApi.commit();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        
    }

    public void DBSTORE053()
    {
        try
        {
            ManagedObject mo = new ManagedObject();
            mo.setName("053"); 
            cdsMo.addObject(mo);
            
            DBStoreTen db=new DBStoreTen();
            db.start();
            
            transApi.begin();
            Thread.sleep(3000);
            cdsMo.removeFromMemoryTable("053");
            transApi.commit();
            
            boolean flag=cdsMo.isObjectInMemory("053");
            if(!flag) 
                System.err.println("Test Case DB-STORE-053 --> PASSED");
            else
                System.err.println("Test Case DB-STORE-053 --> FAILED");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        
    }
    
    private class DBStoreTen extends Thread
    {
        public void run()
        {
            try
            {
                transApi.begin();
                
                ManagedObject mObj2=(ManagedObject)cdsMo.getObject("053");
                if(mObj2!=null)
                    System.err.println("Test Case DB-STORE-053''-PASSED");
                else
                    System.err.println("Test Case DB-STORE-053''-FAILED");
                transApi.commit();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public void DBSTORE054()
    {
        try
        {
            Vector vec= new Vector();
            for(int i=0;i<10;i++)
            {
                ManagedObject mo = new ManagedObject();
                mo.setName("054"+i); 
                vec.addElement("054"+i);
                cdsMo.addObject(mo);
            }
            transApi.begin();
            cdsMo.removeFromMemoryTable(vec);
            boolean flag=true;
            for(int i=0;i<10;i++)
            {
                flag=cdsMo.isObjectInMemory("054"+i);
            }
            if(!flag) 
                System.err.println("Test Case DB-STORE-054 --> PASSED");
            else
                System.err.println("Test Case DB-STORE-054 --> FAILED");
            transApi.commit();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        
    }
    public void DBSTORE055()
    {
        try
        {
            Vector vec= new Vector();
            for(int i=0;i<10;i++)
            {
                ManagedObject mo = new ManagedObject();
                mo.setName("055"+i); 
                vec.addElement("055"+i);
                cdsMo.addObject(mo);
            }
            
            DBStoreEleven db=new DBStoreEleven();
            db.start();
            
            transApi.begin();
            Thread.sleep(3000);
            cdsMo.removeFromMemoryTable(vec);
            transApi.commit();
            
            boolean flag=true;
            for(int i=0;i<10;i++)
            {
                flag=cdsMo.isObjectInMemory("055"+i);
            }
                if(!flag) 
                    System.err.println("Test Case DB-STORE-055 --> PASSED");
                else
                    System.err.println("Test Case DB-STORE-055 --> FAILED");
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        
    }
    
    private class DBStoreEleven extends Thread
    {
        public void run()
        {
            try
            {
                transApi.begin();
                ManagedObject mObj2=null;
                for(int i=0;i<10;i++)
                {                   
                    mObj2=(ManagedObject)cdsMo.getObject("055"+i);
                    if(mObj2==null) 
                        break;
                    else
                        continue;
                }
                    if(mObj2!=null)
                        System.err.println("Test Case DB-STORE-055''-PASSED");
                    else
                        System.err.println("Test Case DB-STORE-055''-FAILED");
                transApi.commit();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public void DBSTORE056()
    {
        try
        {
            transApi.begin(-1);
            
            Vector v= new Vector();
            
            for(int i=150;i<200;i++)
            {
                
                Event evt = new Event();
                String source = "customer";
                evt.setSource(source);
                evt.setEntity(source);
                evt.setCategory("cust");
                evt.setText("cust");
                evt.setSeverity(3);
                evt.setId(i);
                v.addElement(evt);
            }
            relEvent.addBatchObjects(v);
            cds.addObjects(v);
            transApi.commit();
            
            Thread.sleep(40000);
            System.err.println("Sleep is over");
            Event obj=null;
            for(int i=150;i<200;i++)
            {
                String str= new String(""+i);
                obj=(Event)cds.getObject(str);
                if(obj==null)
                {
                    System.err.println("sdfasdf "+ str);
                    break;
                }
            }
            
            if(obj==null)
                System.err.println("Test Case DB-STORE-056:: FAILED");
            else
                System.err.println("Test Case DB-STORE-056:: PASSED");            
                        
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }


    public void DBSTORE057()
    {
        try
        {
            transApi.begin(-1);
            
            Vector v= new Vector();
            
            for(int i=200;i<300;i++)
            {
                
                Event evt = new Event();
                String source = "customer";
                evt.setSource(source);
                evt.setEntity(source);
                evt.setCategory("cust");
                evt.setText("cust");
                evt.setSeverity(3);
                evt.setId(i);
                v.addElement(evt);
            }
            relEvent.addBatchObjects(v);
            cds.addObjects(v);
            transApi.rollback();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            try
            {
                Event obj=null;
                for(int i=200;i<300;i++)
                {
                    String str= new String(""+i);
                    obj=(Event)cds.getObject(str);
                    if(obj!=null)
                    {
                        break;
                    }
            }
            if(obj!=null)
                System.err.println("Test Case DB-STORE-057:: FAILED");
            else
                System.err.println("Test Case DB-STORE-057:: PASSED");            
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }
        }



    }
        
}




