/**
 * $Id: TestMS.java,v 1.1.1.1 2001/01/10 15:20:13 nms Exp $
 */

import com.adventnet.management.*;
//import com.adventnet.management.tl1.*;
import com.adventnet.management.snmp.*;

public class TestMS
{
    public static void main( String[] args ) 
    {
        ManagementServer ms = new ManagementServer();
        //ms.setDebug(true);
        
        /*TL1Property prop = new TL1Property();
        //prop.setTargetHost("localhost");
        //prop.setTargetPort("9099");
        prop.setTargetId("target");
        prop.setResultType(Property.RESULT_STRING);
        */
        SnmpProperty prop = new SnmpProperty();
        //prop.setTargetHost("linux-server");
        prop.setObjectID(".1.3.6.1.2.1.1.5.0");
        //prop.setOperationType(Property.OP_WRITE);
        //prop.setWriteValue("raji");
        //prop.setWriteType( SnmpProperty.SET_TYPE_STRING );
        //prop.setWriteCommunity("public");
        //prop.setMibsToBeLoaded("d:\\ManagementBuilder\\mibs\\RFC1213-MIB");
        
        ManagementServerResultEvent re;
        int id = 0;
        
        //re = ms.syncSend(prop);
        
        /*id = ms.send(prop);
        //System.out.println( "Request send successfully with id = " + id );
        while( !ms.checkResponse(id) );
        {
            try
            {
                Thread.sleep(10);
            }
            catch( Exception e )
            {
            }
        }
        re = ms.receive(id);
        //while( (re = ms.receive(id)) == null );
        System.out.println("Result (send)= " + re.getResult().toString());
        */
        re = ms.syncSend(prop);
        /*id = ms.send(prop);
        while( !ms.checkResponse(id) );
        re = ms.receive(id);*/
        //while( (re = ms.receive(id)) == null );
        if( re == null )
            System.out.println( "Result is null" );
        else
            System.out.println("Result (syncsend)= " + re.getResult().toString());
        
    }
}