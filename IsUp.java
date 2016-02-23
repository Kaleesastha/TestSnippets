import java.net.*;
public class IsUp{
	public static void main(String[] s) throws Exception{
		InetAddress addr = InetAddress.getByName(s[0]);
		NetworkInterface netIF = NetworkInterface.getByInetAddress(addr);
		if(netIF==null)
		{
			System.err.println("No value");
		}
		while(true){
			try{
				boolean isUP = netIF.isUp();
				System.err.println(s[0]+ "is Up!!");
				Thread.sleep(9000);
			}
			catch (Exception exp){ exp.printStackTrace();}
		}
	}
}
