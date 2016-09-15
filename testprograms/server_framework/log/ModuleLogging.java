package test;
import java.util.Properties;
import java.io.*; 
import com.adventnet.management.log.*;
import com.adventnet.nms.util.RunProcessInterface;

public class ModuleLogging implements RunProcessInterface
{
    public boolean boolvar;
    public void callMain(String args[]) 
    {
        boolean logging2=true,logging1=true;
        try{
            if(args[0].toLowerCase().equals("false"))
                logging1=false;
        }catch(Exception e){}
        try{
            if(args[1].toLowerCase().equals("false"))
                logging2=false;
        }catch(Exception e){}
        Properties prop=new Properties();
        String keyNames[]={"LOGGING_USER1","LOGGING_USER2"}; 
	String displayNames[]={"LOGGING_USER1","LOGGING_USER2"};
        Integer logLevels[]={new Integer(3),new Integer(3)};
        Boolean logging[]={new Boolean(logging1),new Boolean(logging2)}; 
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
                LogUser lus=LogMgr.getLogUser("LOGGING_USER1");
                LogUser lus2=LogMgr.getLogUser("LOGGING_USER2");
                
                for (int i=0;i<10;i++)
                {
                    lus.log(Integer.toString(i)+" message log user LOGGING_USER1 ",4);
                    lus2.log(Integer.toString(i)+" message log user LOGGING_USER2 ",4);
                }
            } 
        }
        return boolvar;
    }
    public void shutDown()
    {
       
    }
}

















