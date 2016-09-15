import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.rmi.server.UnicastRemoteObject;

public class remoteClient
{
    public static void main(String a[])	
    {
        try
        {
            remote rm = (remote) Naming.lookup("rmi://rajeshk:9999/remoteServer");
            System.out.println(rm.add(1,2));
            //System.out.println(UnicastRemoteObject.toStub(rm));	
        }
        catch(RemoteException re)
        {
            re.printStackTrace();
        }
        catch(NotBoundException nbe)
        {
            nbe.printStackTrace();
        }
        catch(MalformedURLException mue)
        {
            mue.printStackTrace();
        }
    }
}
