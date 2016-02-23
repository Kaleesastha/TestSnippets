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
		try {
			AuthorizationAdmin authAdmin = (AuthorizationAdmin) Naming.lookup(name);
			if(authAdmin == null) {
				System.out.println("Could not get a Reference to remote object, AuthorizationAdmin");
				System.exit(1);
			}
			else 
			{
				System.out.println("Got a reference to remote object, AuthorizationAdmin");
				authAdmin.createUser(args[0],"Admin");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
