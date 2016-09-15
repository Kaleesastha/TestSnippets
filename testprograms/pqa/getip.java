import com.adventnet.snmp.beans.SnmpTarget;
import  com.adventnet.snmp.snmp2.SnmpVar;

//import java.lang.object;
 
public class getip extends SnmpTarget {
    public static void main (String args[])
{

    SnmpTarget target = new SnmpTarget();
    target.setTargetHost("localhost");  // set host, or other parameters
    target.setObjectID(".1.3.6.1.2.1.2.2.1.6.2");  // or 1.1.0 with standard prefix
     String  result = target.snmpGet();
    
    if (result == null) {
        System.err.println("Failed: "+target.getErrorString());}
    else 
    {
        System.out.println("Response: "+result);
    }
}
}










