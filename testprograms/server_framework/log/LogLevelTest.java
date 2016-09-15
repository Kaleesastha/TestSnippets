package test;
import java.util.Properties;
import java.io.*; 
import com.adventnet.management.log.*;
import com.adventnet.nms.util.RunProcessInterface;
import com.adventnet.nms.util.*;

public class LogLevelTest implements RunProcessInterface
{
    public boolean boolvar;
    public LogLevelTest()
    {
        boolvar=false;
    }
    
    public void callMain(String args[]) 
    {
        int level=4;
        if(args[0]!=null)
        {
            try{
            level=Integer.parseInt(args[0]);
            }catch(Exception ex){}
        }
        for (int j=0;j<10;j++)
            SystemUtil.cout.println("test for message in the console");
        Properties prop=new Properties();
        String keyNames[]={"DYNAMIC_USER1"}; 
	String displayNames[]={"Dynamicuser1"};
        Integer logLevels[]={new Integer(level)};
        Boolean logging[]={new Boolean(true)}; 
        prop.setProperty("FileName","Dynusers.txt");
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
                LogUser lus=LogMgr.getLogUser("DYNAMIC_USER1");
                for (int i=0;i<10;i++)
                {
                    lus.log(Integer.toString(i)+" message log log level Four",4);
                    lus.log(Integer.toString(i)+" message log log level Three",3);
                    lus.log(Integer.toString(i)+" message log log level Two",2);
                    lus.log(Integer.toString(i)+" message log log level One",1);
                }
                
            }
        }
        return boolvar;
    }
    public void shutDown()
    {
       
    }
}






