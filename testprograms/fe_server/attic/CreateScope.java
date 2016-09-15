import com.adventnet.security.authorization.AuthorizedViewObject;
import com.adventnet.security.authorization.*;
import java.rmi.*;
import java.util.Properties;

/**
 * This class gives an example of how to use the API
 * "assignUserToGroup".
 */
public class CreateScope {

    public static void main(String args[]) {

        String name   = "//localhost/NmsAuthAdminAPI";
        if ( args.length < 4 ) 
        {
            System.out.println("Usage : CreateScope <userName> <groupName> <viewName> <viewproperty>");
            System.exit(1);    
        }
        String userName  = args[0];
        String groupName = args[1];
        String viewName  = args[2];
        String viewProperty=args[3];
        Properties viewProp = new Properties();
        //   viewProp.setProperty("ipAddress","192.168.4*");
        // viewProp.setProperty("type","Linux");
        //viewProp.setProperty("name","s*");
        //viewProp.setProperty("entity","r*");
		viewProp.setProperty("entity","s*");
		//Viewprop.setProperty("severity",3);
        AuthorizedViewObject avo = new AuthorizedViewObject();
        avo.setAuthorizedViewName(viewName);
        avo.setViewProperties(viewProp);
        
        try {
            /** 
             * Get the reference to remote Object, AuthorizationAdmin by
             * doing a lookup. 
             */
            CustomViewScopeAPI authAdmin = (CustomViewScopeAPI) Naming.lookup(name);
            if(authAdmin == null) 
            {
                System.out.println("Could not get a Reference to remote object, AuthorizationAdmin");
                System.exit(1);
            }
            else 
                System.out.println("Got a reference to remote object, AuthorizationAdmin");
            
            if( authAdmin.assignUserToGroup( userName, groupName)) // API Call.
                System.out.println("Assigned " + userName + " to " + groupName);
            else
                System.out.println("Error while assigning user to group.User may not exist.");
            
            authAdmin.assignViewToGroup(groupName,viewName);
            authAdmin.createAuthorizedView(avo);
            //authAdmin.assignViewToCVScope("Network Database", viewName);
            authAdmin.assignViewToCVScope(viewProperty, viewName);
            //authAdmin.modifyViewProperty (viewName, viewProp);
            
         }
         catch(Exception e) 
         {
             e.printStackTrace();
             System.out.println("Exception caught " +  e.getMessage());
             System.exit(1);
         }
         System.exit(0);
    }
}






