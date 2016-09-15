import com.adventnet.security.authorization.AuthorizedViewObject;
import com.adventnet.security.authorization.*;
import java.rmi.*;
import java.util.Properties;

/**
 * This class gives an example of how to use the API
 * "assignUserToGroup".
 */
public class ModifyScope {

    public static void main(String args[]) {

        String name      = "//localhost/NmsAuthAdminAPI";
        if ( args.length < 3 ) 
        {
            System.out.println("Usage : ModifyScope <userName> <groupName> <viewName> ");
            System.exit(1);    
        }
        String userName  = args[0];
        String groupName = args[1];
        String viewName  = args[2];
        Properties viewProp = new Properties();
          viewProp.setProperty("ipAddress","192.168.4*");
          // viewProp.setProperty("type","Linux");
          //  viewProp.setProperty("name","s*");
             // viewProp.setProperty("entity","k*");
             // viewProp.setProperty("severity","m*");
        

        try {
            /** 
             * Get the reference to remote Object, AuthorizationAdmin by
             * doing a lookup. 
             */
            CustomViewScopeAPI authAdmin = (CustomViewScopeAPI) Naming.lookup(name);
            if(authAdmin == null) {
                System.out.println("Could not get a Reference to remote object, AuthorizationAdmin");
                System.exit(1);
            }
            else 
                System.out.println("Got a reference to remote object, AuthorizationAdmin");
            authAdmin.modifyViewProperty (viewName, viewProp);
            
        }catch(Exception e) {
            e.printStackTrace();
            System.out.println("Exception caught " +  e.getMessage());
            System.exit(1);
        }
        System.exit(0);
    }
}






