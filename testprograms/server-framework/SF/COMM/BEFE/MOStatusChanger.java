import com.adventnet.nms.eventdb.*;
import java.rmi.Naming;
import java.util.Vector;
import com.adventnet.nms.util.*;
import java.util.*;
public class MOStatusChanger extends Thread
{
    public MOStatusChanger()
    {
    }
    static EventAPI api=null;
    static Properties prop=new Properties();
    public static void sendTrap(String behost)
    {
        
        try
	    {
		int port =1099;
                api = (EventAPI)Naming.lookup("//"+behost+":"+port+"/EventAPI");//No Internationalization
                prop.setProperty("test","test");//No Internationalization
                
            }
        
        catch (Exception e)
        {
            e.printStackTrace();
            
        }
       
    }
    
    public static void main(String args[])
    {
        try
        {
            MOStatusChanger ms1 = new MOStatusChanger();
            ms1.sendTrap(args[0]);
            ms1.start();
            
        }
	catch(Exception e)
        {
            e.printStackTrace();
        }
        
    }

    public void run()
    {

        try
        {
            String src=new String();
            while(true)
                    {
                        for(int sev=1;sev<=4;sev++)
                        {
                            for(int j=1;j<=2;j++)
                            {
                                
                                for(int i=1;i<=249;i++)
                                {
                                    Thread.sleep(400);
                                    Event evt = new Event();
                                    if(j==1)
                                    {
                                        src="192.1.3.";//No Internationalization
                                    }
                                    if(j==2)
                                    {
                                        src="192.2.2.";//No Internationalization
                                    }
                                    src=src+i;
                                    System.err.println("IP " + src);//No Internationalization
                                    evt.setSource(src);
                                    evt.setEntity(src);
                                    evt.setCategory("Test");//No Internationalization
                                    evt.setText("Test");//No Internationalization
                                    evt.setSeverity(sev);
                                    evt.setProperties(prop);
                                    evt.setId(i);
                                    api.addEvent(evt);              
                                }
                            }
                        }
                    }
        
    }
        catch(Exception e){e.printStackTrace();}
    }
    
}
