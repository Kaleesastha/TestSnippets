/* $Id: SnmpNmsTest.java,v 1.1 2003/05/14 05:46:39 ramarb Exp $ */

//Management Server

import com.adventnet.management.client.xml.*;
import com.adventnet.management.client.ManagementClient;
import com.adventnet.management.*;

//PUT AdventNetSNMP.jar in classpath while compiling

import com.adventnet.management.snmp.*;
import java.util.Vector;

/**
 * This is an example program for performing SnmpNmsTest operation on  a single
 * agent by providing the Targethostname and the OID as command line 
 * arguements.This program uses ManagementServer's syncSend method and not 
 * the registerClient method for querying the agent.
 */

public class SnmpNmsTest  implements MSEventListener
{
    
    private  static String host = null;
    private static   String oid  = null;
    private static  String eventId =null;
    
    public static void mcSnmpTest( String host1 , String oid1)
    {
        host = host1;
	oid = oid1;
        SnmpNmsTest s = new SnmpNmsTest();
        s.doSnmpNmsTest();
        if(eventId == null){
            s.doSnmpTrapTest(); }       

    }


    /**
     * This method instantiates the ManagementServer and SnmpProtocolProvider 
     * classes, sets the necessary properties and calls the syncSend method 
     * of the Managementserver for getting the result value.In this program
     * this method is called by the main method.
     */
    private static ManagementClient mc=null;
    public void doSnmpNmsTest()
    {       

        //Instantiating Management Server.
        String a[]={"","com.adventnet.nms.client.ms.MSClientTransporter","",""};
        mc = ManagementClient.getInstance(a);

        //Instantiating SnmpProperty.
        SnmpProperty sp = new SnmpProperty();

                
        //Setting SNMP properties.
        sp.setTargetHost(host);
        sp.setObjectID(oid);
        sp.setOperationType(com.adventnet.management.Property.OP_READ);
        
        ManagementServerResultEvent re = null;

        try
        {
            re = mc.syncSend(sp);
        }
        catch (Exception e)
        {
            System.out.println("Exception at syncSend " + e);
        }
        if( re.getErrString() != null)
        {
            //System.err.println("Error="+re.getErrString() );
            MSSnmpTestPanel.setMSTextArea(re.getErrString() );
            return;
        }


        if(re != null)
        {
            String result = re.getStringResult();
            if( re.getResult() instanceof Object[] )
            {
                Object[] objarr = (Object[])re.getResult();
                
                for(int i=0; i< objarr.length ; i++)
                {
                    // System.out.println("Result "+i+"==" + objarr[i]);
                    MSSnmpTestPanel.setMSTextArea(objarr[i].toString());
                }
            }
        }
        else
        {
            System.err.println("Could not able to get the reply");
        }

    }

    public void failoverTest()
    {
        String a[]={"","com.adventnet.nms.client.ms.MSClientTransporter","",""};
        ManagementClient mc = ManagementClient.getInstance(a);
        
        if(mc.isRunning())
        {
            mc.reconnect(a);
        }
        
    }

    /**
     * The main method instantiates this class-SnmpNmsTest, and calls its 
     * methods, namely parseArgs() and doSnmpNmsTest()
     */
    public static void main(String[] args)
    {

    }


    public void doSnmpTrapTest()
    {
        //host = hosttext.getText();
        //oid = oidtext.getText();
        SnmpProperty sp = new SnmpProperty();
        sp.setComponent(this);

        sp.setTargetHost(host);
        sp.setLocalPort("4009");
        sp.setObjectID("1.5.0");
        
        String[] str = {"java.lang.String"};
        Object[] obj = new Object[1];
        
        try
        {
            eventId = mc.registerForNotification (sp);
        }  
        catch(Exception ep)
        {
            System.out.println("Exceep is "+ ep);
        }
    }

    public void setEventResult (Property eP, Object notifEvent)
    {
        // System.out.println(" notifResult="+notifEvent);
        //System.out.println(" ********* SNMP TRAP ************");
        SnmpEvent sp= (SnmpEvent)notifEvent;

        //System.out.println(" Event Vals ="+sp.getProperties()) ;
        //System.out.println(" *******************************");


        MSSnmpTestPanel.setMSTextArea("SNMP Event Values="+sp.getProperties()) ;
    }

}
