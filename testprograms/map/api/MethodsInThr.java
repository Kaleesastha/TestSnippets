package test;

import java.rmi.*;
import java.util.*;
import java.util.Random;
import java.lang.Thread;
import com.adventnet.nms.mapdb.MapAPI; 
import com.adventnet.management.transaction.*; 
import javax.transaction.RollbackException;
import com.adventnet.nms.util.*; 
import com.adventnet.nms.topodb.*; 

public class MethodsInThr implements RunProcessInterface
{
    MapAPI mapApi=null;
    TransactionAPI transAPI = null;
    
    private void startTesting()
    {
    
        try
        {
        
/*        try
        {
            mapApi= (MapAPI)Naming.lookup("rmi://ssuresh/MapAPI");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        } */
    
	while(mapApi==null)
            {
                mapApi=(MapAPI)NmsUtil.getAPI("MapAPI");
                Thread.sleep(120000);
            }
        
    
        transAPI=NmsUtil.relapi.getTransactionAPI();
        transAPI.begin();
        
        
        Properties p = new Properties();
        p.put("label","Testmap");
        mapApi.addMap("Testmap.netmap",p);
        Thread.sleep(100);
        Add add = new Add();
        Update update = new Update();
        Delete del = new Delete();
        add.start();
        update.start();
        del.start();

        transAPI.commit();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void callMain(String[] args)
    {
        MethodsInThr mt = new MethodsInThr();
        mt.startTesting();
    }

    Properties p1=null;
    private class Add extends Thread
    {
        private Add()
        {
        }

        public void run()
        {
            try
            {
            for(int i=0;i<5;i++)
            {
                p1=new Properties();
                p1.put("name","s"+i);
                p1.put("label","s"+i);
                boolean add=mapApi.addSymbol("192.168.4.0.netmap",p1);
                System.out.println(" Symbol---> s"+i+" Added ---->"+add);
            }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    Properties p2=null;

    private class Update extends Thread
    {
        private Update()
        {
        }

        
        public void run()
        {
            try
            {
                for(int i=0;i<5;i++)
            {
                p2=new Properties();
                p2.put("label","s11"+i);
                p2.put("name","c"+i);
                boolean up=mapApi.addContainer("192.168.4.0.netmap",p2);
                System.out.println(" Symbol---> s"+i+" Updated------>"+up);
            }  
            }
            catch (Exception e)
            {
                e.printStackTrace();
                
            }
        }
    }

    Properties p3=null;
    private class Delete extends Thread
    {
        private Delete()
        {
            
        }

        public void run()
        {
            try
            {
                for(int i=0;i<5;i++)
                {
                    p3.put("name","g"+i);
                    boolean delete=mapApi.addGroup("192.168.4.0.netmap",p3);
                    System.out.println(" Symbol---> s"+i+" Deleted------>"+delete);
                }  
            }
            catch (Exception e)
            {
                e.printStackTrace();
                
            }
        }
        
        
    }
	
    public boolean isInitialized()
    {
        return true;
    }

    public void shutDown()
    {

    }
}
