/*
 * Copyright AdventNet, Inc., 1999
 * File : GetAlertUpdates.java
 * @ author Devesh Gupta
 *
 * This class demonstrates the use of CORBA APIs
 * Here the AlertAPi is accessed through CORBA
 * and AlertObserver registers with it to get
 * notifications of Alert.
 */

//CORBA imports
import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;

//java imports
import java.util.*;

//WebNMS imports
import com.adventnet.util.*;
import com.adventnet.nms.alertdb.*;
 import com.adventnet.nms.common.corba.*;
import com.adventnet.nms.alertdb.corba.*;

public class GetAllAlerts
{
    public static void main(String args[])
    {
        Property_CT[] prop = null;
        NVProperties NVP = null;
        
        //initialize the orb
        ORB orb = ORB.init(args,null);
        
        NameComponent nc1 = new NameComponent("com","com");
        NameComponent nc2 = new NameComponent("adventnet","adventnet");
        NameComponent nc3 = new NameComponent("nms","nms");
        NameComponent[] NC = {nc1,nc2,nc3};
        
        try
        {
            NamingContext nmsContext =
                                      NamingContextHelper.narrow((NamingContextHelper.narrow(orb.resolve_initial_references("NameService"))).resolve(NC));
            NameComponent ncomp = new NameComponent("AlertAPI","");
            NameComponent[] nca = { ncomp };
            AlertAPI_CI alert_api = AlertAPI_CIHelper.narrow(nmsContext.resolve(nca));
            String[] names_arr = alert_api.getCompleteList_CT();
            for (int i=0; i< names_arr.length; i++)
            {            	
                prop = alert_api.getPropertiesOfObject_CT(names_arr[i]);
                NVP = new NVProperties(prop);
                System.out.print(i+1 + ". ");
                System.out.print("Source = " + NVP.getProperty("source"));
                System.out.print(" | Entity = " + NVP.getProperty("entity"));                
                System.out.print(" | Severity = " + NVP.getProperty("stringseverity"));
                System.out.print(" | PreviousSeverity = " + NVP.getProperty("stringpreviousseverity"));
                System.out.print(" | Category = " + NVP.getProperty("category"));
                System.out.print(" | CreateTime = " + new Date(Long.parseLong(NVP.getProperty("createTime"))));
                System.out.println(" | ModifiedTime = " + new Date(Long.parseLong(NVP.getProperty("modTime"))));
                System.out.println(" | Description = " + NVP.getProperty("message"));
                System.out.println("--------------------------------------------------------------------------");
//                System.out.println(NVP);
            }                                                                                                                                                  
        }
        catch(Exception e)
        {
            System.err.println ();
            e.printStackTrace();
        }

    }
}

