/* $Id: APIsBound.java,v 1.1 2003/06/19 12:34:31 priya Exp $
 *
 * File Name      : APIsBound.java
 * Description    : To Check the CorboNorthBound functionality 
 * Other Info     : Run the naming service using the command tnameserv -ORBInitialPort 1050 
 *
 * USAGE          : java APIsBound -ORBInitialPort 1050 -ORBInitialHost localhost
 * Parameter Desc : set the OrbParms to 1050 in corba_parameters                 
 *
 * Owner Name     : priya
 * Change History(Author Date(dd-mm-yyy) and Description of methods added/modifed/deleted):
 */

// CORBA imports
import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;

// NMS IDL imports
import com.adventnet.nms.topodb.corba.*;
import com.adventnet.nms.alertdb.corba.*;
import com.adventnet.nms.mapdb.corba.*;
import com.adventnet.nms.eventdb.corba.*;
import com.adventnet.nms.poll.corba.*;

// CORBA utilities
//import com.adventnet.corba.util.NamingServiceClientUtil;

public class APIsBound {
   public static void main (String [] args) {
        try{
            // Initialize the ORB
            ORB orb = ORB.init(args,null);

         
	    // Get the com/adventnet/nms NamingContext using NamingService
            NameComponent nc1 = new NameComponent( "com","com");
            NameComponent nc2 = new NameComponent( "adventnet","adventnet");
            NameComponent nc3 = new NameComponent( "nms","nms");
            NameComponent[] nmsNC = { nc1, nc2, nc3};
            //NamingContext nmsContext = NamingServiceClientUtil.getContextByName(nmsNC, orb, "NameService", "");
            
            NamingContext nmsContext = NamingContextHelper.narrow((NamingContextHelper.narrow(orb.resolve_initial_references("NameService"))).resolve(nmsNC));
            NameComponent ncomp = new NameComponent("TopoAPI","");
            NameComponent[] nca = { ncomp };
            TopoAPI_CI topo_api = TopoAPI_CIHelper.narrow(nmsContext.resolve(nca));

            ncomp = new NameComponent("AlertAPI","");
            NameComponent[] nca1 = { ncomp };
            AlertAPI_CI alert_api = AlertAPI_CIHelper.narrow(nmsContext.resolve(nca1));


            ncomp = new NameComponent("MapAPI","");
            NameComponent[] nca2 = { ncomp };
            MapAPI_CI map_api = MapAPI_CIHelper.narrow(nmsContext.resolve(nca2));


            ncomp = new NameComponent("EventAPI","");
            NameComponent[] nca3 = { ncomp };
            EventAPI_CI event_api = EventAPI_CIHelper.narrow(nmsContext.resolve(nca3));


            ncomp = new NameComponent("PollAPI","");
            NameComponent[] nca4 = { ncomp };
            PollAPI_CI poll_api = PollAPI_CIHelper.narrow(nmsContext.resolve(nca4));


            if(topo_api!=null)
                {
                    System.out.println("TopoAPI - bound in registry");
                }
            else
                {
                    System.out.println("TopoAPI - not bound in registry");
                }

            if(alert_api!=null)
                {
                    System.out.println("AlertAPI - bound in registry");
                }
            else
                {
                    System.out.println("AlertAPI - not bound in registry");
                }
            


            if(map_api!=null)
                {
                    System.out.println("MapAPI - bound in registry");
                }
            else
                {
                    System.out.println("MapAPI - not bound in registry");
                }



            if(event_api!=null)
                {
                    System.out.println("EventAPI - bound in registry");
                }
            else
                {
                    System.out.println("EventAPI - not bound in registry");
                }



            if(poll_api!=null)
                {
                    System.out.println("PollAPI - bound in registry");
                }
            else
                {
                    System.out.println("PollAPI - not bound in registry");
                }

            /*
              org.omg.CORBA.Object objectRef =null; 
              
              // resolve the Object Reference using NamingService
              try{
              objectRef = NamingServiceClientUtil.getObjectByName(nmsContext, "TopoAPI");
              if(objectRef!=null)
              {
              System.out.println("TopoAPI - bound in registry");import com.adventnet.nms.alertdb.corba.*;
              }
              else
              {
              System.out.println("TopoAPI - not bound in registry");
              }
              }
              catch(NullPointerException e)
              {
              System.out.println("TopoAPI - not bound in registry");
              }
            */
            /*
	try{
	 objectRef = NamingServiceClientUtil.getObjectByName(nmsContext, "MapAPI");
            if(objectRef!=null)
	    {
	     System.out.println("MapAPI - bound in registry");
	    }
	    else
	    {
		System.out.println("MapAPI - not bound in registry");
     	    }
		}
           catch(NullPointerException e)
           {
         		System.out.println("MapAPI - not bound in registry");
           }

	try{
	 objectRef = NamingServiceClientUtil.getObjectByName(nmsContext, "EventAPI");
            if(objectRef!=null)
	    {
	     System.out.println("EventAPI - bound in registry");
	    }
	    else
	    {
		System.out.println("EventAPI - not bound in registry");
     	    }
		}
           catch(NullPointerException e)
           {
         		System.out.println("EventAPI - not bound in registry");
           }

	try{
	 objectRef = NamingServiceClientUtil.getObjectByName(nmsContext, "AlertAPI");
            if(objectRef!=null)
	    {
	     System.out.println("AlertAPI - bound in registry");
	    }
	    else
	    {
		System.out.println("AlertAPI - not bound in registry");
     	    }
		}
           catch(NullPointerException e)
           {
         		System.out.println("AlertAPI - not bound in registry");
           }

	try{	import com.adventnet.nms.topodb.corba.*;
	 objectRef = NamingServiceClientUtil.getObjectByName(nmsContext, "PollAPI");
            if(objectRef!=null)
	    {
	     System.out.println("PollAPI - bound in registry");
	    }
	    else
	    {
		System.out.println("PollAPI - not bound in registry");
     	    }
		}
           catch(NullPointerException e)
           {
         		System.out.println("PollAPI - not bound in registry");
           }
            */
	/*
            // Obtain the TopoAPI_CI ref from it 
            TopoAPI_CI topo_api = TopoAPI_CIHelper.narrow(objectRef);
 
            // Retrieve the names from the server 
            String[] names_arr = topo_api.getCompleteList_CT();
            for (int i=0; i< names_arr.length; i++) 
               System.out.println(i + 1 + ". " + names_arr[i]);
          */
 
        } catch (Exception ex) {
          System.out.println("--- Exception -----"); 
	  ex.printStackTrace();
        }
   }
}








