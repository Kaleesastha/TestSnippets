import com.adventnet.snmp.beans.*;

public class FetchDetails
{

    public static void main(String[] args)
    {
        SnmpTarget target = new SnmpTarget();
        target.setTargetHost("localhost");
        target.setTargetPort(8001);
        target.setObjectID(".1.3.6.1.4.1.2162.4.1.2.0");
        System.out.println(" WebNMS Host Name: "+target.snmpGet());
        target.setObjectID(".1.3.6.1.4.1.2162.4.1.3.0");
        System.out.println(" WebNMS Server IP Address : "+target.snmpGet());
        target.setObjectID(".1.3.6.1.4.1.2162.4.1.9.2.0");
        System.out.println(" WebNMS Server Socket Port : "+target.snmpGet());
        target.setObjectID(".1.3.6.1.4.1.2162.4.1.7.0");
        System.out.println(" WebNMS Server Uptime : "+target.snmpGet());        
        System.exit(0);
    }
    
}
