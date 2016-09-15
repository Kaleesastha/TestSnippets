import java.rmi.Naming;
import java.lang.reflect.Method;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.rmi.server.UnicastRemoteObject;
import java.lang.Class;

public class remoteAPIs
{
    public static void main(String a[])	
    {
        try
        {
            String host = a[0];
            String port = a[1];
            String rm[] = Naming.list("rmi://"+host+":"+port+"/");
            // System.out.println(" the string returned is - "+rm);
            for(int i=0;i<rm.length;i++)
            {
                int as = i+1;
                System.out.println(" API number "+ as +" : " +rm[i]);
                //remote rem = (remote)Naming.lookup(rm[i]);
                //Method[] me = rem.getClass().getDeclaredMethods();
                //for(int j=0;j<me.length;j++)
                //{
                  //  System.out.println("           The API "+ i+1 +" has the following methods");
                  //  System.out.println("           - "+me[j]);
                //}
            }
        }
        catch(RemoteException re)
        {
            re.printStackTrace();
        }
        catch(MalformedURLException mue)
        {
            mue.printStackTrace();
        }
       // catch(NotBoundException nbe)
       // {
       //     nbe.printStackTrace();
       // }
    }
}
