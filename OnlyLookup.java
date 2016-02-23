package test;
import java.rmi.*;
import com.adventnet.nms.commonbe.GenericBEAPI;
public class OnlyLookup{
	public static void main(String[] args){

		try{
			while(true){
				GenericBEAPI genBE= (GenericBEAPI)Naming.lookup("//"+args[0]+":"+args[1]+"/GenericBEAPI");
				while( genBE == null)
				{
					Thread.sleep(2000);
				}
				System.err.println("Last count is : "+genBE.checkHeartBeat());
				Thread.sleep(20000);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
