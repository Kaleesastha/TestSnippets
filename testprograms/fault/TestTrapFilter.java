package test;

import com.adventnet.nms.eventdb.*;
import com.adventnet.snmp.snmp2.*; 

public class TestTrapFilter implements com.adventnet.nms.eventdb.TrapFilter
{
    public Object applyTrapFilter(SnmpPDU pdu)  
    {  
        System.out.println("********TestTrapFilter***********");  
        return pdu;  
    }
}
