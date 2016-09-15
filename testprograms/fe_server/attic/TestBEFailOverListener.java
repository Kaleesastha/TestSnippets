package test;
import com.adventnet.nms.fe.common.*;
public class TestBEFailOverListener implements BEFailOverListener
{
	public void preBEFailOverNotification(BEFailOverEvent event)
	{
		System.out.println("*****************************************");
		System.out.println(" Inside the preBEFailOverNotification ");
		System.out.println(" getOldBEHost() : "+ event.getOldBEHost());
		System.out.println(" getOldBEPort() : "+ event.getOldBEPort());
		System.out.println(" getOldRMIPort() : "+ event.getOldRMIPort());
	
		System.out.println(" getNewBEHost() : "+ event.getNewBEHost());
		System.out.println(" getNewBEPort() : "+ event.getNewBEPort());
		System.out.println(" getNewRMIPort() : "+ event.getNewRMIPort());
		System.out.println("*****************************************");
	}
	public void postBEFailOverNotification(BEFailOverEvent event)
	{
		System.out.println("*****************************************");
		System.out.println(" Inside the postBEFailOverNotification ");
		System.out.println(" getOldBEHost() : "+ event.getOldBEHost());
		System.out.println(" getOldBEPort() : "+ event.getOldBEPort());
		System.out.println(" getOldRMIPort() : "+ event.getOldRMIPort());
	
		System.out.println(" getNewBEHost() : "+ event.getNewBEHost());
		System.out.println(" getNewBEPort() : "+ event.getNewBEPort());
		System.out.println(" getNewRMIPort() : "+ event.getNewRMIPort());
	}
}
