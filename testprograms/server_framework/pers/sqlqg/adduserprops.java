
/*
This program is written to add a managedobject with a specified set of userproperties.
This program is to test the user property specific cases in SQLQueryGenerator class
Used for the plan db_pers_sqg_tp.html
Created by:kasturirangan
Date:3/6/03
*/
import com.adventnet.nms.topodb.*;
import com.adventnet.nms.util.*;
import java.rmi.Naming;


public class adduserprops

{

	public static void main(String args[])
	{

		try
			{

				ManagedObject mobj = null;
				TopoAPI topoApi = (TopoAPI)Naming.lookup("//localhost:1099/TopoAPI");
        		String name = "kasthurirengan.P";
				mobj= new ManagedObject();
				mobj.setName(name);
				mobj.setUserProperty("phone","5486");
				mobj.setUserProperty("bike","bajaj");
				topoApi.addObject(mobj);
				System.out.println(" The mangedObject added with name kasthurirengan.P");
				System.out.println(" Check it by the query select * from topouserprops");
			}

		catch(com.adventnet.nms.store.NmsStorageException e)

		{
			System.out.println("NmsStorageException");
		}

		catch(com.adventnet.management.transaction.UserTransactionException ee)

		{
			System.out.println("UserTransactionException");
		}
		catch(Exception ww)
		{
				System.out.println("Exception");
		}

	}
}