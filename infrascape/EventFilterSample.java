package com.dgca;

import com.adventnet.nms.alertdb.Alert;
import com.adventnet.nms.eventdb.Event;
import com.adventnet.nms.eventdb.FilterClient;

public class EventFilterSample implements FilterClient {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Event filter(Event event) {
		// TODO Auto-generated method stub
		System.out.println("Event Filter Called" +event.getSnmpPDU());
		System.err.println("Event's properties : " +event.getProperties());
		if (event.getSeverity()<0 ){
			event.setSeverity(1);
		}
		if(event.getText() ==null || event.getText().trim().equals("")){
			event.setText("Text Critical Message "+event.getSource());
		}
		return event;
	}

	@Override
	public Alert filter(Alert alert) {
		// TODO Auto-generated method stub
		System.out.println("Alert Filter Called " +alert.getSeverity());
		System.err.println("Alert's properties: " +alert.getProperties());
		return alert;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

}
