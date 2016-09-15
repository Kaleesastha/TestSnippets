import com.adventnet.security.authorization.SecuredAdminAPI;
import java.rmi.Naming;
import com.adventnet.security.crypto.*;
import com.adventnet.security.authentication.*;

public class UserTesting
{
    SecuredAdminAPI api = null;
    String oldpass = "public";
    int count=0;
    String hostName=null;

    public UserTesting(String hostName)
    {
        this.hostName=hostName;
        getAPI();
        changePassword();
    }

    private void getAPI()
    {
        try
        {
            RMIAccessAPI rmiapi = (RMIAccessAPI)Naming.lookup("//"+hostName+"/RMIAccessAPI");
            api = (SecuredAdminAPI)rmiapi.getAPI("root","public","SecuredAdminAPI");
            System.out.println(" api is =="+api);
           
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
            System.exit(0);
        }
    }
    
    private void changePassword()
    {
        while(true)
        {
            count+=1;
            try
            {
                CryptoGraphAPI secAPI = api.getCryptoAPI("root");
                System.out.println(" secAPI is =="+secAPI);
                String newpass = "public"+count;
                String newsec = secAPI.enCrypt(newpass);
                String oldsec = secAPI.enCrypt(oldpass);
                boolean result = api.changePassword("root",oldsec,newsec);
                System.out.println(" result changing from =="+oldpass+" new =="+newpass+" =="+result);
                oldpass=newpass;
                Thread.sleep(2);
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
                System.exit(0);
            }
        }
    }

    public static void main(String a[])
    {
        if(a.length != 1)
        {
            System.out.println(" java UserTesting hostName");
            return;
        }
        new UserTesting(a[0]);
    }
}
