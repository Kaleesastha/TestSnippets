import com.adventnet.nms.eventdb.*;
import com.adventnet.nms.alertdb.*;

import java.io.*;
import java.util.*;
import java.rmi.Naming;

public class TestAlert
{

    private EventAPI eapi;
    private AlertAPI aapi;
    private String columnName;

    private Event evt;

    BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));

    public TestAlert()
    {
    }

    private Event getDefaultEvent()
    {
        Event evt = new Event();
        evt.setText("This is exampel Event");
        evt.setCategory("Test");
        evt.setTime(System.currentTimeMillis());
        return evt;
    }
    public void getAPI() throws Exception
    {
        String host = bReader.readLine();
		eapi = (EventAPI)Naming.lookup("//" + host +"/EventAPI");
		aapi = (AlertAPI)Naming.lookup("//" + host +"/AlertAPI");

        System.out.print("Enter the double datatype column name : ");
        columnName = bReader.readLine();
    }

	public static void main(String[] a) throws Exception
	{
        TestAlert te = new TestAlert();
        System.out.println("Test program for Double Data type");
        System.out.println();
        System.out.print("Enter the hostname : ");

        te.getAPI();
        System.out.println("Succesfully received the handle");

        te.process();
    }

    public void process() throws Exception
    {
        while (true)
        {
            System.out.println();
            System.out.print("Enter your choice : addAlert/deleteAlert/addAlertLoop/deleteAlertLoop/exit  : ");

            String choice = bReader.readLine();

            if (choice.equalsIgnoreCase("addAlert"))
            {
                addAlert();
            }
            else if (choice.equalsIgnoreCase("deleteAlert"))
            {
                deleteAlert();
            }
            else if (choice.equalsIgnoreCase("addAlertLoop"))
            {
                addAlertLoop();
            }
            else if (choice.equalsIgnoreCase("deleteAlertLoop"))
            {
                deleteAlertLoop();
            }
            else if (choice.equalsIgnoreCase("exit"))
            {
                System.exit(0);
            }
            else
            {
                System.out.println("Unknown option");
            }
        }
    }

    public void addAlert() throws Exception
    {
        System.out.print("Enter object name : ");
        String source = bReader.readLine();

        Event evt = getDefaultEvent();
        evt.setSource(source);
        evt.setEntity(source);
        System.out.print("Enter the double value : ");
        double value = Double.parseDouble(bReader.readLine());
        evt.setUserProperty(columnName, "" + value);

        System.out.print("Enter the severity (Integer value) : ");
        int severity = Integer.parseInt(bReader.readLine());
        evt.setSeverity(severity);
        eapi.addEvent((Event)evt);
        System.out.println("Added event for : " + source);
    }


    public void addAlertLoop() throws Exception
    {
        System.out.print("Enter object name : ");
        String source = bReader.readLine();

        System.out.print("Enter the no. of objects : ");
        int count = Integer.parseInt(bReader.readLine());
        System.out.print("Enter the starting double value : ");
        double value = Double.parseDouble(bReader.readLine());
        System.out.print("Enter the increment double value : ");
        double incr = Double.parseDouble(bReader.readLine());
        System.out.print("Enter the severity (Integer value) : ");
        int severity = Integer.parseInt(bReader.readLine());
        
        for (int i = 0; i < count; i++)
        {
            Event evt = getDefaultEvent();
            evt.setSource(source + i);
            evt.setEntity(source + i);
            evt.setSeverity(severity);
            evt.setUserProperty(columnName, "" + (value + (incr * i)));
            eapi.addEvent((Event) evt);
            System.out.println("Added event for : " + source + i);
        }
    }


    public void deleteAlert() throws Exception
    {
        System.out.print("Enter object name : ");
        String source = bReader.readLine();

        Alert alert = aapi.checkOutIfAvailable(source);
        if (alert != null)
        {
            aapi.deleteAlert(alert, false);
            System.out.println("Delete alert for object : " + source);
        }
    }


    public void deleteAlertLoop() throws Exception
    {
        System.out.print("Enter object name : ");
        String source = bReader.readLine();

        System.out.print("Enter the no. of objects : ");
        int count = Integer.parseInt(bReader.readLine());
        Alert alert;       
        
        for (int i = 0; i < count; i++)
        {
            alert = aapi.checkOutIfAvailable(source + i);
            if (alert != null)
            {
                aapi.deleteAlert(alert, false);
                System.out.println("Delete alert for object : " + source + i);
            }
        }
    }
}
