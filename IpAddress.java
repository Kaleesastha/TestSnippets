import java.net.InetAddress;
public class IpAddress
{
        public static void main(String[] s)
        {
                try
                {
                        System.err.println("InetAddress.getLocalHost().getHostName()==>"+InetAddress.getLocalHost().getHostName());
                        System.err.println("InetAddress.getLocalHost().getHostAddress()==>"+InetAddress.getLocalHost().getHostAddress());
                }
                catch(Exception ex)
                {
                        ex.printStackTrace();
                }
        }
}
