import java.rmi.RemoteException;
import java.rmi.Remote;

public interface remote extends Remote
{
	public int add(int a,int b)throws RemoteException;
}	
