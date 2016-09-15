import java.util.*;
import java.rmi.*;
import com.adventnet.nms.topodb.*;

class AddNetwork 
{
	public static void main(String args[])throws Exception
	{
		TopoAPI api=(TopoAPI)Naming.lookup("rmi://"+args[1] +":1099/TopoAPI");
		String name=getNetworkName(args);
	//	for( int i=0; i<= 100 ; i++) 
		while(true)
		{
			try
			{
				System.out.println("Network Delete Start -----------> "+name);
				api.deleteObjectAndSubElements(name);
				System.out.println("Deleted");
			//	Thread.sleep(90000);
			//	Thread.sleep(1000);
				System.out.println("Network Add Start ------> for "+name);
				api.addNetwork(name,"255.255.255.0");
				System.out.println("Added");
			}catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
	}

	private static String getNetworkName(String[] array)
	{
		String name="192.168.5.0";
		try
		{
			name=array[0];
		}
		catch(Exception excp)
		{
			excp.printStackTrace();
		}
		return name;
	}
}

