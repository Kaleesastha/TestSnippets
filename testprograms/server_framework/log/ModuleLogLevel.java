package test;
import java.util.Properties;
import java.io.*; 
import com.adventnet.management.log.*;
import com.adventnet.nms.util.RunProcessInterface;

public class ModuleLogLevel implements RunProcessInterface
{
    public boolean boolvar;
    public ModuleLogLevel()
    {
        boolvar=false;
    }
    
    public void callMain(String args[]) 
    {
        int level=4;
        int level2=4;
        if(args[0]!=null)
        {
            level=Integer.parseInt(args[0]);
        }
        if(args[1]!=null)
        {
            level2=Integer.parseInt(args[1]);
        }

        Properties prop=new Properties();
        String keyNames[]={"LOGLEVEL_USER1","LOGLEVEL_USER2"}; 
	String displayNames[]={"LOGLEVEL_USER1","LOGLEVEL_USER2"};
        Integer logLevels[]={new Integer(level),new Integer(level2)};
        Boolean logging[]={new Boolean(true),new Boolean(true)}; 
        prop.setProperty("FileName","loglevel.txt");
        prop.setProperty("MaxLines","1000");
        prop.setProperty("FileCount","10");
        prop.setProperty("LogsDirectory","logs");
        prop.setProperty("MaxLinesCached","0");
        prop.setProperty("UseTimeStamp","true");
        //      prop.setProperty("Logging","true");
        prop.put("Name",keyNames);
        prop.put("DisplayName",displayNames);
        prop.put("LogLevel",logLevels);
        prop.put("Logging",logging);
        LogMgr.createLogUser(prop,false);   
        boolvar=true;
        
        
    }
    int test=0;
    public boolean isInitialized()
    {
                  
        if(boolvar)
        {
            test=test+1;
            if(test==1)
            {
                LogUser lus=LogMgr.getLogUser("LOGLEVEL_USER1");
                LogUser lus2=LogMgr.getLogUser("LOGLEVEL_USER2");
                
                for (int i=0;i<10;i++)
                {
                    lus.log(Integer.toString(i)+" message log user LOGLEVEL_USER1 ",4);
                    lus2.log(Integer.toString(i)+" message log user LOGLEVEL_USER2 ",4);
                }
            } 
        }
        return boolvar;
    }
    public void shutDown()
    {
       
    }
}

















