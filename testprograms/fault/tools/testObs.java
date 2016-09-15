/* $Id: testObs.java,v 1.1 2003/01/13 12:03:29 rajalakshmytr Exp $
 *
 * TestProgram which can acts as EventObserver,TrapObserver,SocketListener,AlertObserver and AlertListener. 
 * By editing code you can configure that. You have to make rmic for this.
 *
 * Usage: java testObs hostName
 *
 * hostName: name of the machine where NMS is running.
 */

import java.util.*;
import java.rmi.Naming;
import java.rmi.server.UnicastRemoteObject;

import com.adventnet.nms.eventdb.*;
import com.adventnet.nms.alertdb.*;
import com.adventnet.nms.util.*;
import com.adventnet.nms.trap.SocketListener;
import com.adventnet.snmp.snmp2.*;
import com.adventnet.snmp.sas.*;

public class testObs extends UnicastRemoteObject implements TrapObserver, EventObserver, AlertObserver,AlertListener,SocketListener 
{
    private AlertAPI aapi = null;
    private EventAPI eapi = null;
    private SendEmail mail = null;
    private int sevNumber =1;	
    private TrapAPI tapi = null;
	
    public testObs() throws java.rmi.RemoteException
    {
    }

    public testObs(String name) throws Exception
    {
        getEventAPI(name);
        getAlertAPI(name);
        getTrapAPI(name);
        registerForAll();
        registerShutdownHook();
        mail = new SendEmail();
    }	

    public void receivedData(com.adventnet.snmp.sas.ProtocolDataUnit data) throws java.rmi.RemoteException
    {
        System.out.println(" TRAP Received for ProtocolDataUnit ========================");
    }

    public void getTopoAPI(String name) throws java.rmi.RemoteException
    {
        try
        {
            String apiString = "//"+name+"/TopoAPI";
            //	topoapi =(TopoAPI)Naming.lookup(apiString);
            System.out.println("TopoAPI Handle received Successfully ");
        }
        catch(Exception e)
        {
            System.out.println("exception is"+e);
        }
    }

    public void getTrapAPI(String name) throws java.rmi.RemoteException
    {
        try
        {
            String apiString = "//"+name+"/TrapAPI";
            tapi =(TrapAPI)Naming.lookup(apiString);
            System.out.println("TrapAPI Handle received Successfully ");
        }
        catch(Exception e)
        {
            System.out.println("exception is"+e);
        }
    }

    public void getEventAPI(String name) throws java.rmi.RemoteException
    {
        try
        {
            String apiString = "//"+name+"/EventAPI";
            eapi =(EventAPI)Naming.lookup(apiString);
            System.out.println("After getting eventapi");
        }
        catch(Exception e)
        {
            System.out.println("exception is"+e);
        }
    }

    public void getAlertAPI(String name) throws java.rmi.RemoteException
    {
        try
        {
            String apiString = "//"+name+"/AlertAPI";
            aapi =(AlertAPI)Naming.lookup(apiString);
            System.out.println("After getting alertapi");
        }
        catch(Exception e)
        {
            System.out.println("exception is"+e);
        }
    }

    public void update(XMLNode node)
    {

        System.out.println("the node is =="+node);
        Vector child = node.getChildNodes();
        for (int i = 0;i<child.size();i++)
        {
            XMLNode childnode =(XMLNode) child.elementAt(i);
            System.out.println("the child node =="+childnode);
            if(child.elementAt(i).toString().equals("DATA"))
            {
                Hashtable att = new Hashtable();
                att= childnode.getAttributeList();
                System.out.println("The has table is =="+att);
                String query = (String) att.get("sqlQuery");
                System.out.println("Query =="+query);
            }
        }
        XMLDataWriter writer = new XMLDataWriter("/home/saravanakumar/text.conf",node);
    }		

    public void listenAlert(AlertActionInformer action) throws java.rmi.RemoteException
    {	
        System.out.println("#########################################");
        System.out.println("Notification for AlertActionInfromer ");
        System.out.println("the boolean is =="+action.isBatchUpdate());
        Alert alt = action.getAlert();
        System.out.println("the alert is =="+action.getAlert());
        if(alt != null)
        {
            System.out.println("class anme ==========>"+alt.getClass().getName());
            System.out.println("Alert properties =="+alt.getProperties());
            getAnnotations(alt.getEntity());
			
        }	
        if(action.isBatchUpdate())
        {
            Vector v = action.getAlertList();
            //System.out.println("size os alertlist is =="+
            for(int i=0;i<v.size();i++)
            {
                Alert king = (Alert)v.elementAt(i);
                System.out.println("classname==============?"+king.getClass().getName());
                System.out.println("alert't properties =="+king.getProperties());
                getAnnotations(king.getEntity());
		//System.out.println("the alertlist is =="+action.getAlertList());
            }
        }	
        System.out.println("the opeations is =="+action.getOperation()+" userName =="+action.getUserName());
        System.out.println("notest is =="+action.getNotes());
        System.out.println("#########################################");
    }

    private void getAnnotations(String entity)
    {
        try
        {
            Vector ann = aapi.getAnnotation(entity);
            if(ann == null)
            {
                System.out.println(" ANNOTATION ARE NULLLLLLLLLLLLLLLLLL");
                return;
            }	
            System.out.println(" Annotation Vector is =="+ann);
            System.out.println(" Vector size is for entity =="+entity+"===="+ann.size());
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }	
    }		
	
    public void registerShutdownHook() throws Exception
    {
        Runtime.getRuntime().addShutdownHook(new myThread());
        System.out.println("Adding Shutdown Hook==");
    }	

    public void registerForAll() throws Exception
    {
        System.out.println("Whether observer for trap =="+eapi.register(this));
        System.out.println("Whether observer for event=="+eapi.registerForEvents(this));
        System.out.println("Whether observer for alert=="+aapi.registerForAlerts(this));
        System.out.println("Whether Listener for Alert=="+aapi.addAlertListener(this));
        tapi.registerForTrap(5000,this);
        System.out.println("Whether Listener for Trap ==true");
    }	

    public void deregisterForAll() throws Exception
    {
        System.out.println("Deregitration for Alert Observer=="+aapi.deregisterForAlerts(this));
        System.out.println("Deregistration for Trap=="+eapi.deregister(this));
        System.out.println("Deregistration for Event =="+eapi.deregisterForEvents(this));
        System.out.println("Deregistration for Alert Listener =="+aapi.removeAlertListener(this));
        tapi.deRegister(this);
        System.out.println("Deregistration for SocketListener ==true");
    }	

    public void update(Vector v)
    {
        System.out.println("update from alert in vector");
        System.out.println("IN OBSERVERRRRRRRRRRRRRRRRRRR==");
        for(int i=0;i<v.size();i++)
        {
            Alert king = (Alert)v.elementAt(i);
            System.out.println("alert't properties =="+king.getProperties());
            //System.out.println("the alertlist is =="+action.getAlertList());
        }
    }

    private void addEvent(String name)
    {
        if(sevNumber == 6)
        {
            sevNumber =1;
        }
        Event evt = new Event();
        evt.setEntity(name);
        evt.setSource(name);
        evt.setCategory("Unmanaged");
        evt.setText("This is unmanaged test=="+sevNumber);
        evt.setSeverity(sevNumber);
        sevNumber+=1;
        try
        {
            eapi.addEvent(evt);
        }
        catch(Exception ee)
        {
            System.out.println("Exception in adding Events ");
            ee.printStackTrace();
        }	
        System.out.println("Event added with source==="+name);
    }	

    public void update(SnmpPDU pdu)
    {
        System.out.println("Trap received from "+pdu.getAgentAddr());
        System.out.println("GT is ==="+pdu.getTrapType());
        System.out.println("ST is =="+pdu.getSpecificType());
        System.out.println("TrapOID is =="+pdu.getVariable(1));
        System.out.println("The trap time is "+System.currentTimeMillis());
    }

    public void update(Event e)
    {
        System.out.println("Event received with entity=="+e.getEntity());
        System.out.println("the text is --=="+e.getText());
        Properties p = new Properties();
        p.put("managed","false");
        System.out.println("the severrity=="+e.getSeverity());
        /*if(e.getSeverity() ==1)
          {
          try
          {
          Vector v = tapi.getObjectNamesWithProps(p);
          for(int i = 0;i<v.size();i++)
          {
          addEvent((String)v.elementAt(i));
          }
          }
          catch(Exception te)
          {
          System.out.println("Exception in getting Topo Unmanaged Objects");
          te.printStackTrace();
          }
          }*/
    }

    public void update(Alert a)
    {
        System.out.println("Thre result form ALERTOBSERVER");
        System.out.println("Alert received with entity=="+a.getEntity());
        System.out.println("the propeties are =="+a.getProperties());
        //mail.runAction(a);
    }	
   
    public void setProperties()
    {
        Properties p = new Properties();
        p.put("name","test");
        p.put("fromAddress","saravanakumar@ndia.adventnet.com");
        p.put("toAddress","saravanakumar@ndia.adventnet.com");
        p.put("server","mail-server");
        p.put("message","this is filter test");
        p.put("subject","greetings");
        mail.setProperties(p);
    }

    public static void main(String a[]) throws Exception 
    {
        if(a.length !=1)
        {
            System.out.println("USAGE: java tesObs hostName");
            return;
        }	
        testObs obs= new testObs(a[0]);
    }

    class myThread extends Thread
    {
        public void run()
        {
            try
            {
                deregisterForAll();
            }
            catch(Exception ee)
            {
                System.out.println("Exception in Shutdowning==");
                ee.printStackTrace();
            }	
        }
    }	
}
