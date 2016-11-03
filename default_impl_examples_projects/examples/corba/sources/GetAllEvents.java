/*
 * Copyright AdventNet, Inc., 1999
 *
 * File : GetEventUpdates
 *
 * This class demonstrates the usage of CORBA API.
 */

 //CORBA imports

 import org.omg.CORBA.*;
 import org.omg.CosNaming.*;
 import org.omg.CosNaming.NamingContextPackage.*;

 //java imports
 import java.util.*;
import com.adventnet.nms.util.*;
 // WebNMS imports

 import com.adventnet.nms.eventdb.*;
 import com.adventnet.nms.common.corba.*;
 import com.adventnet.nms.eventdb.corba.*;
 import com.adventnet.util.*;

 public class GetAllEvents
 {
 	public static void main(String args[])
 	{
 		ORB orb = null;
        Property_CT[] prop = null;
        NVProperties NVP = null;
            
 		try
 		{
 			orb = orb.init(args,null);

 			NameComponent nc  = new NameComponent("com","com");
 			NameComponent nc1 = new NameComponent("adventnet","adventnet");
 			NameComponent nc2 = new NameComponent("nms","nms");

 			NameComponent[] nca = {nc,nc1,nc2};

 			NamingContext nmsContext = NamingContextHelper.narrow(orb.resolve_initial_references("NameService"));

 			NamingContext NC = NamingContextHelper.narrow(nmsContext.resolve(nca));

			NameComponent ncomp = new NameComponent("EventAPI","");
			NameComponent[] NCA = {ncomp};
			
 			EventAPI_CI eventapi = EventAPI_CIHelper.narrow(NC.resolve(NCA));
			String[] names_arr = eventapi.getCompleteList_CT();
            for (int i=0; i< names_arr.length; i++)
            {            	
                prop = eventapi.getPropertiesOfObject_CT(names_arr[i]);
                NVP = new NVProperties(prop);
                System.out.print(i+1 + ". ");
                System.out.print("Source = " + NVP.getProperty("source"));
                System.out.print(" | Entity = " + NVP.getProperty("entity"));
                System.out.print(" | Node = " + NVP.getProperty("node"));
                System.out.print(" | Severity = " + NVP.getProperty("stringseverity"));
                System.out.print(" | Category = " + NVP.getProperty("category"));
                System.out.println(" | CreateTime = " + new Date(Long.parseLong(NVP.getProperty("time"))));
                System.out.println(" | Description = " + NVP.getProperty("text"));
                System.out.println("--------------------------------------------------------------------------");
            }
 		}
 		catch(Exception e)
 		{
 			System.out.println(e);
 		}
 	}
 }
 
