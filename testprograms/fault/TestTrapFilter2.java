package test;

import com.adventnet.nms.eventdb.*; 
import com.adventnet.snmp.snmp2.*; 

public class TestTrapFilter2 implements com.adventnet.nms.eventdb.TrapFilter
{
    static int severityInt = 1;  
    public Object applyTrapFilter(SnmpPDU pdu)  
    {  
        System.out.println("********This is the check at firsttime***********");  
        
        Event evt = new Event();  
        evt.setEntity( "TestEntity" );  
        evt.setEntity( "TestEntity" );  
        evt.setText( "TestMessage");  
        evt.setCategory("Test" );  
        if(severityInt == 6)  
        {  
            severityInt = 1;  
        }  
        evt.setSeverity(severityInt);  
        severityInt++;  
        return evt;  
    }
}

