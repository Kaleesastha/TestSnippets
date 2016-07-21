package test;

import java.rmi.Naming;
import com.adventnet.nms.topodb.*;
import com.adventnet.nms.eventdb.*;

public class EventGeneration {
	public static void main(String[] args) {
		try {
			EventAPI eAPI = (EventAPI) Naming.lookup("//127.0.0.1/EventAPI");
			TopoAPI tapi = (TopoAPI) Naming.lookup("//127.0.0.1/TopoAPI");
			int i = 1;
			String moName = "";
			System.err.println("before while loop");
			ManagedObject mo = new ManagedObject();
			while(i <= 300) {
				moName = args[0]+i;
				mo.setName(moName);
				try{tapi.addObject(mo);} catch(Exception exp) {exp.printStackTrace();}
				System.err.println(moName +" is added");
				i++;
			}
			System.err.println("Before sleep");
			Thread.sleep(10000);
			System.err.println("After sleep");
			i=1;
			while (i<=300){
				moName = args[0]+i;
				Event e = new Event();
				e.setCategory("EventTesting");
				for(int k=5; k >= 1; k--) {
					e.setSeverity(k);
					e.setEntity(moName);
					e.setSource(moName);
					e.setText("test"+i);
					e.setTime(System.currentTimeMillis());
					eAPI.addEvent(e);
					Thread.sleep(10);
				}
				System.err.println(moName +"'s Events are added");
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
