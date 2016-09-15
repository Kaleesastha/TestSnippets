package test;
import java.util.Properties;
import java.io.*; 
import com.adventnet.management.log.*;
import com.adventnet.nms.util.RunProcessInterface;

public class DynamicLogUser implements RunProcessInterface
{
    public boolean boolvar;

    public DynamicLogUser()
    {
        boolvar=false;
    }
    
    public void callMain(String args[]) 
    {
	    System.err.println("DynamicLogUser initialised");
        Properties prop=new Properties();
        String keyNames[]={"DYNAMIC_USER1","DYNAMIC_USER2","DYNAMIC_USER3"}; 
	String displayNames[]={"Dynamicuser1","Dynamicuser2","Dynamicuser3"};
        Integer logLevels[]={new Integer(3),new Integer(3),new Integer(3)};
        Boolean logging[]={new Boolean(true),new Boolean(true),new Boolean(true)}; 
        prop.setProperty("FileName","Dynamicusers.txt");
        prop.setProperty("MaxLines","1000");
        prop.setProperty("FileCount","10");
        prop.setProperty("LogsDirectory","logs");
        prop.setProperty("MaxLinesCached","0");
        prop.setProperty("UseTimeStamp","true");
        // prop.setProperty("Logging","true");
        prop.put("Name",keyNames);
        prop.put("DisplayName",displayNames);
        prop.put("LogLevel",logLevels);
        prop.put("Logging",logging);
        try{ 
             if(args[0]==null)LogMgr.createLogUser(prop,false);   
             else if(args[0]=="true")LogMgr.createLogUser(prop,true);  
             else LogMgr.createLogUser(prop,true);
             boolvar=true;
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
    int test=0;
    public boolean isInitialized()
    {
        
        if(boolvar)
        {
            test=test+1;
            if(test==1)
            {
                try
                {
                    LogUser user1=LogMgr.getLogUser("DYNAMIC_USER1");
                    LogUser user2=LogMgr.getLogUser("DYNAMIC_USER2");
                    LogUser user3=LogMgr.getLogUser("DYNAMIC_USER3");
                    user1.log("This is test for loguser support dynamically it is inititialized",3);
                    
                    for (int i=0;i<100;i++)
                    {
                        user1.log(Integer.toString(i)+" message log xmlwriter",3);
                        user2.log(Integer.toString(i)+" message log xmlwriter",3);
                        user3.log(Integer.toString(i)+" message log xmlwriter",3);
                    }
                }
                catch(Exception e)
                {
                System.out.println("Exception while logging message for dynamic loguser "+e);
                }
            }
        }
        
        return boolvar;
    }
    
    public void shutDown()
    {
       
    }
}







