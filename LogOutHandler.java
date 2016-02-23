package com.adventnet.nms.startclient;
import com.adventnet.nms.util.NmsClientUtil;

public class LogOutHandler{
	public static void main(String argv[]){
		NmsClientUtil.closeTheClient("Test Message for stopping client");
		WebNMSClient.main(new String[]{"localhost", "9090", "no", "en", "US"});
	}
}
