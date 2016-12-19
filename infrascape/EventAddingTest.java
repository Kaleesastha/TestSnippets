package com.dgca;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import com.adventnet.nms.eventdb.*;
import com.adventnet.nms.fault.FaultException;
import com.adventnet.nms.store.NmsStorageException;

public class EventAddingTest {

	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {

		EventAPI eventAPI = (EventAPI) Naming.lookup("//localhost/EventAPI");
		System.out.println(eventAPI);
		String categ = "Topology";
		String msg = "Cisco Alarm Message";
		String sever = "1";
		String failObj = args[0];
		String node = args[0];
		String source = args[0];

		// String severity="2";

		//InputEvent inputEvent = new InputEvent(categ, msg, sever, failObj, node, source);
		Event event = new Event();
		event.setCategory(categ);
		event.setText(msg);
		event.setSeverity(1);
		event.setEntity(failObj);
		event.setSource(source);
		event.setTime(System.currentTimeMillis());
		try {
			eventAPI.addEvent(event);
			//eventAPI.addEvent(inputEvent);
		} catch (NmsStorageException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (FaultException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			int count = eventAPI.getTotalEventCount();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
