import java.rmi.registry.LocateRegistry;
public class RMIRegistry{
	public static void main(String[] s){
		try{
			int port = 1199;
			LocateRegistry.createRegistry(port);
			try{System.err.println("sleeping");Thread.sleep(20000);} catch (Exception exp){}
		}
		catch(Exception exp){
			exp.printStackTrace();
		} } }
