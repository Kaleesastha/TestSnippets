package test;

import com.adventnet.nms.fe.common.*;


public class TestBEFailOverObserver implements BEFailOverObserver
{
	public void reconnectTo(String host)
	{
		System.out.println("*****************************************");
		System.out.println(" Inside the reconnect to ");
		System.out.println(" getOldBEHost() : "+ host);
		System.out.println("*****************************************");

	}

}
