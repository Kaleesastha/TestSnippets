
import com.adventnet.nms.topodb.*;
import com.adventnet.nms.util.*;

import java.rmi.*;
import java.util.*;

public class AddObjects extends Thread 
{
	TopoAPI tapi = null ;
	String name = null;

	public AddObjects()
	{
		try
		{
			getAPI();
			start();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

	void getAPI() throws Exception 
	{
		if( tapi == null )
		{
			tapi = (TopoAPI) Naming.lookup("rmi://localhost/TopoAPI");
		}
	}

	public static void main(String[] args)	
	{
		try
		{
			AddObjects addObj = new AddObjects();
		}catch(Exception exp)
		{
			exp.printStackTrace();
		}
	}


	public void run() 
	{
		ManagedObject mo = null;
		Properties prop = null ;
		boolean result = false ;
		String start = "195.195." ;
		int end = 0;
		int ip = 0;
		Node no = null ;
		int i = 0 ;
		while( true )
		{
			try
			{
				if( (i%255) == 0 ) 
				{
					end++;
					ip = 0;
					i= 1;
					System.out.println("Network is "+start+end+".0");
					try
					{
						tapi.addNetwork( start+end+".0" , "255.255.255.0" );
					}catch(Exception exp)
					{
						System.out.println("Exception while adding Network object ");
						exp.printStackTrace();
					}
				}
				else ip++;

				//Adding ManagedObject.
				name = start+end+"."+i;
				//	name = start+i;	
				System.out.println("APPL: object name is "+name);
				
				mo = new ManagedObject();
				mo.setName(name);
				mo.setDisplayName( name );
				if( tapi == null ) getAPI()  ;
				result = tapi.addObject( mo );
				System.out.println("APPL: addObject() status is "+result +"  for is "+name);
				//sleep(5000);
				
				i++;
			}catch(Exception exp)
			{
				exp.printStackTrace();
			}
		}
	}
}







