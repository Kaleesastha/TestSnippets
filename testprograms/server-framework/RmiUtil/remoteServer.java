import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.rmi.Naming;

public class remoteServer extends UnicastRemoteObject implements remote
{
	
	public remoteServer()throws RemoteException
	{
	}
	
	public int add(int a, int b)
	{
		return a+b;
	}	
	public static void main(String a[]) throws Exception
	{
		//remoteServer rs = new remoteServer();
		//Naming.rebind("rmi://localhost:9999/remoteServer",rs);
                System.out.println(" ************************* ");
	}
}	
		
