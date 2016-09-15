/**
 * $Id: TestRmiGen.java,v 1.1 2002/06/05 10:01:43 rameshj Exp $
 */

import java.rmi.*;
import com.adventnet.nms.commonbe.*;

public class TestRmiGen
{
	
	
	public static void main( String[] args ) throws Exception 
	{
		String host = "localhost";
		String portOption = ":1099";
		for(int i = 0; i < args.length;i++)
		{
			if(args[i].equals("-h"))
			{
				host = args[i+1];
				i++;
			}
			else if(args[i].equals("-p"))
			{
				portOption = ":" + args[i+1];
				i++;
			}
			else if(args[i].equals("-np"))
			{
				portOption = "";
			}
		}
		
		try{
			GenericBEAPI genapi=(GenericBEAPI)Naming.lookup("//"+host+portOption+"/GenericBEAPI");
			if(genapi.isReadyForConnection())
			{
				System.out.println(" Connection ready:");
			}
			else
			{
				System.out.println(" connection not ready");
			}

		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
}
