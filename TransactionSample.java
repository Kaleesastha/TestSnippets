import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author javadb.com
 */
public class TransactionSample{
    
    /**
     * Updates tables using a transaction
     */
    public void updateDatabaseWithTransaction() {
        
        Connection connection = null;
	Statement statement = null;
	String nameOfDriver = "oracle.jdbc.driver.OracleDriver";
	String databaseURL = "jdbc:oracle:thin:@172.18.96.203:1521:orcl2";
	String userid = "webnms";
	String password = "webnms";        
        try {
		Class.forName(nameOfDriver);
            connection = DriverManager.getConnection(databaseURL,userid,password);
            //Here we set auto commit to false so no changes will take
            //effect immediately.
            connection.setAutoCommit(false);
            
            statement = connection.createStatement();
            
            //Execute the queries
	    statement.executeUpdate("SET TRANSACTION ISOLATION LEVEL READ COMMITTED");
	    statement.executeUpdate("UPDATE Table1 SET Name = 'foo' WHERE Name = '1'");
            
            //No changes has been made in the database yet, so now we will commit
            //the changes.
            connection.commit();
            
            
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
            
            try {
                //An error occured so we rollback the changes.
                connection.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace();
            }
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        new TransactionSample().updateDatabaseWithTransaction();
        
    }
}
