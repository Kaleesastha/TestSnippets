import com.adventnet.nms.eventdb.*;
import com.adventnet.nms.alertdb.*;
import com.adventnet.nms.trap.*;
import java.rmi.server.*;
import java.util.*;
import com.adventnet.nms.util.*;
import com.adventnet.snmp.snmp2.SnmpPDU;
import com.adventnet.snmp.sas.ProtocolDataUnit;
import com.adventnet.nms.poll.*;

public class CompleteObserver extends UnicastRemoteObject implements SocketListener,TrapObserver,EventObserver,AlertObserver,AlertListener,PollObserver,PollUnitObserver 
{
    public CompleteObserver() throws java.rmi.RemoteException
    {
        
    }

    public void update(int type,PolledData pd)
    {
        System.out.println(" Poll Unit Observer type and PolledData ==");
    }

    public void update(int type,PollingObject po)
    {
        System.out.println(" Poll Unit Observer PollingObject type and Polling Object ==");
    }

    /* public void update(Vector vect)
    {
        System.out.println(" Poll Unit Observer update Vector ==");
    }

    public void update(XMLNode node)
    {
        System.out.println(" Poll Unit Observre upate XMLNode ==");
        }*/

    public void dataUpdate(CollectedInfo data)
    {
        System.out.println(" Poll Data Update Collected Info ==="+this);
    }

    public void dataUpdate(String key,long time,long value)
    {
        System.out.println(" Poll Data update key long long =="+this);
    }
    
    public void dataUpdate(String key,long time,String value)
    {
        System.out.println(" Poll data update key long string =="+this);
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
