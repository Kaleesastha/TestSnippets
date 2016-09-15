import com.adventnet.nms.eventdb.*;
import java.rmi.Naming;
import java.util.*;
import com.adventnet.nms.alertdb.*;
public class GetTrapParsers
{
    TrapAPI trapAPI = null;
    EventAPI eventAPI = null;
    AlertAPI alertAPI = null;
    
    public GetTrapParsers()
    {
        initAPI();
    }

    private void initAPI()
    {
        try
        {
            trapAPI = (TrapAPI)Naming.lookup("//saravanakumar/TrapAPI");
            eventAPI = (EventAPI)Naming.lookup("//saravanakumar/EventAPI");
            alertAPI = (AlertAPI)Naming.lookup("//saravanakumar/AlertAPI");
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
    }
    
    public void getTrapParsers()
    {
        try
        {
            for(int i=0;i<50;i++)
            {
                //TrapParser[] array = api.getTrapParsersFromMib("mibs/BRIDGE-MIB");
                //api.saveTrapParsersToFile(array,"king.filters");
                //Vector events = eventAPI.getEvents(new InputEvent(),new InputEvent());
                //eventAPI.purgeEventDB();
                
                String entity = (String)alertAPI.getCompleteList().elementAt(0);
                /*
                for(int j=0;j<10;j++)
                {
                    AlertAnnotation ann = new AlertAnnotation();
                    ann.setEntity(entity);
                    ann.setNotes(" count"+j);
                    ann.setModTime(System.currentTimeMillis());
                    ann.setWho("king"+j);
                    
                    alertAPI.addAnnotation(ann);
                }
                alertAPI.deleteAlertAnnotation(entity);*/
                
                //Vector vect = alertAPI.getAlertsBasedOnGroupViewMode();
                /********
                Alert alt = alertAPI.getNextAlertBasedOnModtime(entity);
                
                Alert alt2 = alertAPI.getOldestModifiedAlert();
                System.out.println(" alt =="+alt+" alt2="+alt2);*/

                Vector size = alertAPI.getObjectNamesWithProps(new Properties());
                System.out.println("count " +i);
            }
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
    }
    
    public static void main(String a[])
    {
        new GetTrapParsers().getTrapParsers();
    }
}
