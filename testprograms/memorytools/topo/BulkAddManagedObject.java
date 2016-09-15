import com.adventnet.nms.topodb.*;
import java.rmi.Naming;
import java.util.*;

public class BulkAddManagedObject
{
    TopoAPI topoAPI = null;

    public BulkAddManagedObject()
    {
        try
        {
            topoAPI = (TopoAPI)Naming.lookup("//saravanakumar/TopoAPI");
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
        addManagedObjects();
    }

    private void addManagedObjects()
    {
        Vector nodes = new Vector();
        int nodeValue=0;
        
        for (int i = 1;i<=10000;i++)
        {
            SnmpNode snmpNode = new SnmpNode();
            nodeValue=i%250+1;
            if(nodeValue == 0)
            {
                nodeValue=250;
            }
            snmpNode.setName("snmpnode"+i);
            snmpNode.setIpAddress("192.168.4."+nodeValue);
            snmpNode.setIsDHCP(false);
            snmpNode.setIsNode(true);
            snmpNode.setIsInterface(false);
            snmpNode.setIsSNMP(true);
            snmpNode.setManaged(true);
            snmpNode.setStatusPollEnabled(false);
            snmpNode.setCommunity("public");
            nodes.addElement(snmpNode);
        }

        try
        {
            topoAPI.addObjects("com.adventnet.nms.topodb.SnmpNode",nodes);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
    }
    
    public static void main(String a[])
    {
        BulkAddManagedObject ob = new BulkAddManagedObject();
    }
}


