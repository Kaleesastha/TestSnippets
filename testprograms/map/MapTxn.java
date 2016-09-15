package test;
import com.adventnet.nms.mapdb.*;
import com.adventnet.management.transaction.*;
import javax.transaction.RollbackException;
import com.adventnet.nms.util.*;
import java.util.*;

public class MapTxn implements RunProcessInterface
{
    
    public void getHandle()
    {
        MapAPI map=null;
        TransactionAPI transAPI = null;
        try
        {
            while(map==null)
            {
                map=(MapAPI)NmsUtil.getAPI("MapAPI");
                Thread.sleep(120000);
            }
        }
        catch(Exception e)
        {
            System.out.println(" Exception in getting handle "+e);
        }
        transAPI=NmsUtil.relapi.getTransactionAPI();
        
        
        try
        {
            try
            {
                transAPI.begin();
            }
            catch(Exception e)
            {
                System.out.println(" Exception while begiining transaction "+e);
            }
            
            Properties p = new Properties();
            p.put("label","Testmap");
            map.addMap("Testmap.netmap",p);
            transAPI.commit();
        }
        catch(Exception ee)
        {
            System.out.println("Exception inside the transactional block " +ee);
        }
    }
    
    public void callMain(String[] args)
    {
        
        getHandle();
    }
    
    public boolean isInitialized()
    {
        return true;
    }
    
    public void shutDown()
    {
        
    }
    
    
}
