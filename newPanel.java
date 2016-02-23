import com.adventnet.nms.startclient.*;
import javax.swing.JApplet;
import com.adventnet.nms.util.PureClientUtils;
import com.adventnet.nms.util.*;
import  javax.swing.JMenuBar;
import com.adventnet.nms.trap.SocketListener;
import com.adventnet.nms.eventdb.TrapAPI;
import com.adventnet.snmp.sas.ProtocolDataUnit;
import com.adventnet.security.authentication.RMIAccessAPI;
import java.rmi.Naming; 
import java.rmi.server.UnicastRemoteObject;

public class newPanel extends UnicastRemoteObject implements NmsPanel,SocketListener
{
    private static String PANEL_ID = "NEWPANEL_CLIENT"; // No i18n
    private static TrapAPI api = null;


    public newPanel() throws java.rmi.RemoteException
    {
        try
        {
            RMIAccessAPI rmiapi = (RMIAccessAPI) Naming.lookup("//" + NmsClientUtil.applet.getCodeBase().getHost() + "/RMIAccessAPI");
            api = (TrapAPI)rmiapi.getAPI("root","public","TrapAPI");
            // Registering the SocketListener to receive Traps.
            api.registerForTrap(162,this);
            System.out.println("Successfully registered as Trap Listener. The API is :"+api);
        }
        catch(Exception e)
        {
            System.out.println("Exception Occured While registering to receive Traps ");
            e.printStackTrace();
        }    

    }

    
    public void actionPerformed(java.awt.event.ActionEvent ae)
    {

    }

    public void receivedData(ProtocolDataUnit protocoldataunit)
    {
        // To check whether the Trap Notification is received.
        System.out.println("receivedData :"+protocoldataunit); 
    }

    public void init(JApplet applet)
    {
        
    }

	public synchronized void receive(byte data[])
    {
        System.out.println("receive method of newPanel called");         
    }


    public void start()
    {

    }

    public void stop()
    {
        // Here we deregister the SocketListener. This stop() method will be called when the browser is closed. In that scenario, you can deregister the socket listener. 
        System.out.println("The TrapAPI is :"+api);
        try{
        api.deRegisterForTrap(162, this);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        System.out.println("After calling deRegisterForTrap"); 

        //On closing the browser, the java console also closes. To see the message printout we have included, I have included a delay here. 
        try{
        Thread.sleep(5000);
        }

        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    public void destroy(java.lang.String action)
    {
        System.out.println("destroy method of newPanel called"); 
    }

    public void setProperties(java.util.Properties props) 
    {
        System.out.println("setProperties method of newPanel called");         
    }

    public java.lang.String key()
    {
        return "";
    }
    public JMenuBar getPanelMenuBar()
    {
        return new JMenuBar();
    }

    public void setPanelMenuBar(JMenuBar mb)
    {
        
    }
    public void fireNmsPanelEvent(NmsPanelEvent n)
    {

    }
    public void  removeNmsPanelEventListener(NmsPanelEventListener np)
    {

    }
    
    public void addNmsPanelEventListener(NmsPanelEventListener np)
    {
        
    }



}
