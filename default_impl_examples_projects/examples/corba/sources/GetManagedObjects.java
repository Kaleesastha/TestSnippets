/*
 * Copyright (c) AdventNet, Inc.,1999. All Rights Reserved
 * file:   GetNodeNames.java
 * @author: Sreenivas Kanumuru
 *
 * This class demonstrates the use of the NMS CORBA APIs. 
 * Here, the TopoAPI module is accessed and names of all
 * discovered objects are retrieved. 
 */

// CORBA imports
import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;

// NMS IDL imports
import com.adventnet.nms.topodb.corba.*;

// CORBA utilities
//import com.adventnet.corba.util.NamingServiceClientUtil;

public class GetManagedObjects {
   public static void main (String [] args) {
        try{
            // Initialize the ORB
            ORB orb = ORB.init(args,null);

            // Get the com/adventnet/nms NamingContext using NamingService
            NameComponent nc1 = new NameComponent( "com","com");
            NameComponent nc2 = new NameComponent( "adventnet","adventnet");
            NameComponent nc3 = new NameComponent( "nms","nms");
            NameComponent[] nmsNC = { nc1, nc2, nc3};
            NamingContext nmsContext = NamingServiceClientUtil.getContextByName(nmsNC, orb, "NameService", "com/adventnet/nms");

            // resolve the Object Reference using NamingService
            org.omg.CORBA.Object objectRef = NamingServiceClientUtil.getObjectByName(nmsContext, "TopoAPI");

            // Obtain the TopoAPI_CI ref from it 
            TopoAPI_CI topo_api = TopoAPI_CIHelper.narrow(objectRef);
 
            // Retrieve the names from the server 
            String[] names_arr = topo_api.getCompleteList_CT();
            for (int i=0; i< names_arr.length; i++) 
               System.out.println(i + 1 + ". " + names_arr[i]);
 
        } catch (Exception ex) {
            ex.printStackTrace();
        }
   }
}
