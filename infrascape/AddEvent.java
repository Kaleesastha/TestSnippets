import java.rmi.Naming;
import com.adventnet.nms.eventdb.*;
import com.adventnet.nms.topodb.TopoAPI;
import com.adventnet.nms.util.NmsUtil;

import java.text.*;
import java.util.*;
import java.rmi.Naming;
import java.io.*;
import com.adventnet.nms.util.*;
import com.adventnet.nms.topodb.*;
import java.util.Vector;
//import java.rmi.server.RMISocketFactory;
//import test.RMISSLServerSocketFactory;

public class AddEvent
{
    String hostName = null;
    EventAPI eapi = null;
    int severity = 1;
    String text = "";
    String moName="";

    public AddEvent(String hostName, String moName, int severity, String text)
    {
        this.hostName=hostName;
	this.severity=severity;
	this.text = text;
	this.moName = moName;
    }

    private void addEvent()
    {
        System.out.println("---------------------------");
        System.out.println("Adding Event");
        try
        {
            eapi = (EventAPI)Naming.lookup("//"+hostName+"/EventAPI");
            Event evt = new Event();
            evt.setEntity(moName);
            evt.setSource(moName);
            evt.setSeverity(severity);
	    //String text="event";
		    //text=text+"event";
	    //evt.setText("[R] DPC is prohibited");
	    //evt.setText("REPT-E1F:FAC-E1 LOS failure");
	    //evt.setText("REPT-E1F:FAC-E1 available");
	    //evt.setText("REPT CONTD: system alive");
	    evt.setText(this.text);
	    String category = "check";
            evt.setTime(System.currentTimeMillis());
            eapi.addEvent(evt);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
    }
    public static void main(String a[]) throws Exception
    {
	    //RMISocketFactory sf = new RMISSLServerSocketFactory();
	    //RMISocketFactory.setSocketFactory(sf);
        if(a.length < 4)
        {
            System.out.println("Usage: java AddEvent hostName MOName Integer_severity message");
            return;
        }
	TopoAPI tapi = (TopoAPI) Naming.lookup("//localhost/TopoAPI");
	ManagedObject mo = new ManagedObject();
	mo.setName(a[1]);
	if (tapi.getByName(a[1]) == null) {
		try{
			tapi.addObject(mo);
		} catch(Exception exp){
			exp.printStackTrace();
			System.exit(1);
		}
	}
        new AddEvent(a[0],a[1],Integer.parseInt(a[2]),a[3]).addEvent();
    }
}
