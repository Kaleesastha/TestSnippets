import com.adventnet.nms.eventdb.*;
import com.adventnet.nms.alertdb.*;
import com.adventnet.nms.trap.*;
import java.rmi.server.*;
import java.util.*;
import com.adventnet.nms.util.*;
import com.adventnet.snmp.snmp2.SnmpPDU;
import com.adventnet.snmp.sas.ProtocolDataUnit;
import com.adventnet.nms.poll.*;

public class CompleteObserver extends UnicastRemoteObject implements SocketListener,TrapObserver,EventObserver,AlertObserver,AlertListener
{
    public CompleteObserver() throws java.rmi.RemoteException
    {
        
    }

    public void receivedData(com.adventnet.snmp.sas.ProtocolDataUnit pdu)
    {
        System.out.println(" SocketListener Notification ="+this);
    }

    public void update(SnmpPDU pdu)
    {
        System.out.println(" TrapObserver Notification =="+this);
    }

    public void update(Event evt)
    {
        System.out.println(" EventObserver Notification =="+this);
    }

    public void update(Alert alt)
    {
        System.out.println(" AlertObserver Update =="+this);
    }

    public void update(Vector vec)
    {
        System.out.println(" AlertObserver Update =="+this);
    }
    
    public void update(XMLNode node)
    {
        System.out.println(" AlertListener Notification =="+this);
    }

    public void listenAlert(AlertActionInformer main)
    {
        System.out.println(" AlertListener Notification =="+this);
    }
}
