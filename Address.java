/**Address.java**/
import java.net.InetAddress;
public class Address
{
        public static void main(String[] s)
        {
                /*try
                {
                        System.err.println("The machine address is:"+java.net.InetAddress.getLocalHost().getHostAddress());
			System.err.println("FQDN is:"+InetAddress.getLocalHost().getCanonicalHostName());
			System.err.println("Server Address is:"+java.net.InetAddress.getByName(s[0]).getHostAddress());
                }
                catch(Exception e){e.printStackTrace();}*/
                try{
			System.err.println("getHostAddress is:"+java.net.InetAddress.getByName(s[0]).getHostAddress());
                        System.err.println("getCanonicalHostName is ::"+(InetAddress.getByName(s[0])).getCanonicalHostName());
                }
                catch(Exception ex){ex.printStackTrace();}
        }
}
