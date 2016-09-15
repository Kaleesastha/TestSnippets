
package test;import com.adventnet.nms.store.relational.ConnectionAuthenticationInterface;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionAuthenticationInterfaceImpl implements ConnectionAuthenticationInterface
{
	public void authenticateConnection(Connection con) throws SQLException	{
		System.out.println("------------------------------");		System.out.println("Connection "+con.getCatalog());	}
}
