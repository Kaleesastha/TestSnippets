/* $Id: AddEvent.java,v 1.1 2003/01/13 12:02:24 rajalakshmytr Exp $
 *
 * Standalone tool which adds the specified number of Events into the system using EventAPI.addEvent() method.
 *
 * Usage: java AddEvent NumberofEvents hostName 
 *
 * NumberOfEvents: Total number of Events to be added. (int)
 * hostName : name of the machine where WebNMS is running. (String)
 *
 */

import java.rmi.Naming;

import com.adventnet.nms.eventdb.*;
import com.adventnet.security.authorization.AuthorizationAdmin;
import test.*;

public class AddEvent
{
    int totalCount=1;
    String hostName = null;
    EventAPI eapi = null;

    public AddEvent(String stringCount,String hostName)
    {
        this.totalCount=Integer.parseInt(stringCount);
        this.hostName=hostName;
        getEventAPI();
    }
    
    private void getEventAPI()
    {
        try
        {
            eapi = (EventAPI)Naming.lookup("//"+hostName+"/EventAPI");
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
    }

    private void addEvent()
    {
        try
        {
            for(int i=0;i<totalCount;i++)
            {
                Event evt = new Event();
                evt.setEntity("Test"+i);
                evt.setSource("Test");
                evt.setSeverity((i%4)+1);
                evt.setText("This is text "+i);
                //evt.setCategory("public");
                evt.setTime(System.currentTimeMillis());

                eapi.addEvent(evt);
            }
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
    }

    public static void main(String a[])
    {
	    /**/
	    /*try{
	    AuthorizationAdmin authAdmin = (AuthorizationAdmin) Naming. lookup ("//localhost/NmsAuthAdminAPI");
	    System.out.println ( "Successfully got the handle for AuthorizationAdmin");
	    authAdmin.setUserStatus("test", "enabled");
    }
    catch (Exception remoteException) 
    {
	    System.out.println ( "Error in getting the handle for AuthorizationAdmin"); 
    }
    System.exit(0);*/

        if(a.length !=2)
        {
            System.out.println("Usage: java AddEvent NumberofEvents hostName");
            return;
        }
        new AddEvent(a[0],a[1]).addEvent();
    }
}
