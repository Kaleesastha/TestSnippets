// $id: $
/* $Id: TestTopoActionListener.java,v 1.2 2003/06/09 12:06:08 priya Exp $
 *
 * File Name      : TestTopoActionListerner.java
 * Description    : To register ,unregister the notification handler and to get the notification details
 *                  received by the handler.
 * Other Info     : Compile the program and place it in WebNMS_HOME/classes directory
 *                  create stub and skeleton using rmic <classname> and place these files under WebNMS_HOME/classes directory
                    to register the handle run the prg & to unregister it use Ctrl +c 
 *
 * USAGE          : java TopoActionListerner
 * Parameter Desc :                 
 *
 * Owner Name     : Priya
 * Change History(Author Date(dd-mm-yyy) and Description of methods added/modifed/deleted):
 */

//package test;
import java.rmi.Naming;
import java.io.*;
import java.rmi.server.UnicastRemoteObject;
import com.adventnet.nms.util.*;
import com.adventnet.nms.topodb.*;
public class TestTopoActionListener implements Runnable,TopoActionListener
{
    private TopoAPI api = null;
    ManagedObject obj=null;
    Runtime rt = null;
    BufferedReader br=null;
    public TestTopoActionListener()
    {
        rt = Runtime.getRuntime();
        rt.addShutdownHook(new Thread(this));
        this.getTopoAPI();
    }

    public void run()
    {
        try
        {
            System.out.println(" TopoActionListener unregistered : " + api.unregister(this));
        }
        catch (Exception e)
        {
            System.out.println(" Exception while unregistering.." + e);
            e.printStackTrace();
        }
    }
    
    public static void main(String args[]) throws IOException
    {

	//	System.out.println(" Main called...");
	new TestTopoActionListener();
            
    }

    private void getTopoAPI()
    {
        try
        {
            while(api == null)
            {
                api = (TopoAPI) Naming.lookup("//localhost/TopoAPI");
                Thread.sleep(1000);
            }
            System.out.println(" Successfully got the TopoAPI handle.");
            UnicastRemoteObject.exportObject(this);
            System.out.println(" TopoActionListener registered : " + api.register(this));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void update(TopoNotificationEvent evt)
    {
        System.out.println("          Details Received from Topology Module for the Notification Handler    ");
	System.out.println("");
        System.out.println("          Event received is " + evt);
        System.out.println();
        System.out.println("          Type of database change : " + evt.getUpdateType());
	obj = evt.getNewManagedObject();
        if(obj != null)
        {
	    System.out.println("           Details for getNewManagedObject() ");
	    System.out.println("           ================================= " );
	    (obj.getProperties()).list(System.out);
	    System.out.println(" ");
	}
	obj = evt.getOldManagedObject();
	if(obj != null)
        { 
	    System.out.println("           Details for getOldManagedObject() ");
	    System.out.println("           ================================= " );
	    (obj.getProperties()).list(System.out);
	    System.out.println(" ");
	} 
	System.out.println("               Details for getModifiedPropertyKeys() ");
	System.out.println("               ====================================  ");
	System.out.println(" Modified property keys are " + evt.getModifiedPropertyKeys());
		
    }
    
}// TestTopoActionListener
