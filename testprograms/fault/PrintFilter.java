//$Id: PrintFilter.java,v 1.1 2002/10/06 12:36:25 srikrishnan Exp $

package test;

import com.adventnet.nms.util.XMLNode;
import com.adventnet.nms.util.XMLDataWriter;
import com.adventnet.nms.util.XMLDataReader;
import java.util.Hashtable;
import java.util.Vector;

import java.rmi.*;
import java.io.*;

// AdventNet imports
import com.adventnet.snmp.snmp2.*;
import com.adventnet.snmp.beans.*;
import com.adventnet.nms.topodb.*;
import com.adventnet.nms.util.*;
import com.adventnet.util.*;
import com.adventnet.management.log.*;
import com.adventnet.nms.eventdb.*;

/** 
This class is used to forward the traps
**/
public class PrintFilter implements TrapFilter  
{
   
    private void initSnmp()
    {
    }
    
    public PrintFilter()
    {
        
    }   

    public Object applyTrapFilter(SnmpPDU trap_pdu) 
    {
        try
        {
                System.out.println("Trap Receiver :  Agent ADDR : "+trap_pdu.getAgentAddr());
                System.out.println("Trap Receiver : Remote Host : "+trap_pdu.getRemoteHost());
                System.out.println("varbs : "+trap_pdu.printVarBinds());
        }
        catch(Exception e)
        {
            System.out.println("error in snmp session");
        }
        return trap_pdu;    
    }

}
