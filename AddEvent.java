/* $Id: AddEvent.java,v 1.1 2004/07/21 06:04:17 srividya Exp $
 *
 * Standalone tool which adds the specified number of Events into the system using EventAPI.addEvent() method.
 *
 * Usage: java AddEvent NumberofEvents hostName
 *
 * NumberOfEvents: Total number of Events to be added. (int)
 * hostName : name of the machine where WebNMS is running. (String)
 *
 */

import java.rmi.Naming;

import com.adventnet.nms.eventdb.*;

//import test.DoubleEvent;

public class AddEvent
{
    int totalCount=5;
    String hostName = null;
    EventAPI eapi = null;
    int severity = 1;

    public AddEvent(String stringCount,String hostName,int severity)
    {
        this.totalCount=Integer.parseInt(stringCount);
        this.hostName=hostName;
        getEventAPI();
	this.severity=severity;
    }

    private void getEventAPI()
    {
        try
        {
            eapi = (EventAPI)Naming.lookup("//"+hostName+"/EventAPI");
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
    }

    private void addEvent()
    {
        System.out.println("---------------------------");
        System.out.println("Adding Event");
        try
        {
            Event evt = new Event();
            evt.setEntity("eagle4_Frame1_Shelf1_Slot16");
            evt.setSource("eagle4_Frame1_Shelf1_Slot16");
            evt.setSeverity(severity);
	    String text="event";
	    //for(int k=0;k<13100;k++)
		    text=text+"event";
	    //evt.setText("[R] DPC is prohibited");
	    //evt.setText("REPT-E1F:FAC-E1 LOS failure");
	    //evt.setText("REPT-E1F:FAC-E1 available");
	    evt.setText("REPT CONTD: system alive");
	    String category = "check";
	    //for(int k=0;k<=40;k++)
		    category=category+"check";
            evt.setCategory(category);
            //evt.setUserProperty("MYSPECIALPROP4","4.05");
            evt.setTime(System.currentTimeMillis());
            eapi.addEvent(evt);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
    }

    private void addMulEvent()
    {
        System.out.println("---------------------------");
        System.out.println("Adding Event");
        try
        {
            for(int i=0;i<totalCount;i++)
            {
                Event evt = new Event();
                evt.setEntity("Test"+i);
                evt.setSource("Test"+i);
                evt.setSeverity((i%4)+1);
                evt.setText("This is text ");
                evt.setCategory("public");
                evt.setUserProperty("test1","testval"+i);
                evt.setUserProperty("MYSPECIALPROP1",i+"var1");
                evt.setUserProperty("MYSPECIALPROP2",i+"var2");
                evt.setUserProperty("MYSPECIALPROP3",i+"6");
                evt.setUserProperty("MYSPECIALPROP4",i+".7");
                evt.setTime(System.currentTimeMillis());
                eapi.addEvent(evt);
                Thread.sleep(5000);
            }
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
    }

    private void addMultipleEvents()
    {
        System.out.println("---------------------------");
        System.out.println("Adding Events");
        try
        {
            for(int i=0;i<totalCount;i++)
            {
                Event evt = new Event();
                evt.setEntity("Test"+i);
                evt.setSource("Test");
                evt.setSeverity((i%4)+1);
                evt.setText("This is text "+i);
                evt.setCategory("public");
                evt.setTime(System.currentTimeMillis());
                eapi.addEvent(evt);
            }
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
        System.out.println("---------------------------");
        System.out.println("Adding InputEvents");
        try
        {
            for(int i=0;i<totalCount;i++)
            {
                InputEvent evt = new InputEvent();
                evt.setEntity("Test"+i);
                evt.setSource("Test");
                evt.setSeverity((i%4)+1);
                evt.setText("This is text "+i);
                evt.setCategory("public");
                evt.setTime(System.currentTimeMillis());
                eapi.addEvent(evt);
            }
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
    }

    /*private void addExtendedDoubleEvent()
    {
        try
        {
        DoubleEvent evt = new DoubleEvent();
        evt.setEntity("Test1");
        evt.setSource("Test");
        evt.setSeverity(1);
        evt.setText("This is text ");
        evt.setCategory("public");
        evt.setTime(System.currentTimeMillis());
        evt.setSpecialEventName("SpecialEvent");
        evt.setSpecialEventIdentifier("kingand");
        evt.setSpecialEventSeverity(3);
        evt.setUserProperty("ev1","kumar");
        evt.setUserProperty("ev2","raj");
        double testing = 123456789.6746d;
        evt.setTesting(testing);
        eapi.addEvent(evt);
 }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
    }*/

    public static void main(String a[])
    {
        if(a.length < 3)
        {
            System.out.println("Usage: java AddEvent NumberofEvents hostName Integer_severity");
            return;
        }
        new AddEvent(a[0],a[1],Integer.parseInt(a[2])).addEvent();
        //new AddEvent(a[0],a[1]).addMulEvent();
        //new AddEvent(a[0],a[1]).addMultipleEvents();
        //new AddEvent(a[0],a[1]).addExtendedDoubleEvent();
    }
}
