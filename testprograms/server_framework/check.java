import com.adventnet.util.PortChecker;

public class check
{
    public static void main(String args[])
    {
        PortChecker pc = new PortChecker();
        String host = args[0];
        int port =Integer.parseInt(args[1]);
        boolean result = pc.isPortFree(host,port);
        System.out.println(" the result is -" + result);
    }
} 
