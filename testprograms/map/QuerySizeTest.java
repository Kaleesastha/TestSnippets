import java.rmi.*;
import com.adventnet.nms.topodb.*;
import java.util.* ;
import com.adventnet.nms.topodb.CommonTopoAPI;
import com.adventnet.nms.topodb.BulkOperationsHandler;



public class QuerySizeTest
{
    public static void main(String args[])
    {
        System.out.println(" testingss");
        Properties prop;
        BulkOperationsHandler bulk=null;
        ManagedObject mo = null;
        String name= null;
        try

            { 
                for(int i =0 ;i<700 ;i++)
                {
                    name= "FymbolNameIsSetItTo100CharactersToTestTheMaxQueryIssueInMySQLSoPleaseDoCheckAnyErrorIsThrownInLogSymbolNameIsSetItTo100CharactersToTestTheMaxQueryIssueInMySQLSoPleaseD"+i;
                    
                    TopoAPI topo=(TopoAPI) Naming.lookup("//localhost/TopoAPI");
                    //System.out.println(" topo 0-->"+topo);
                    Properties pr = new Properties();
                    mo = new ManagedObject();
                    mo.setName(name);
                    boolean add = topo.addObject(mo);
                    System.out.println("result"+ add);
                }
                
            }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("error" + e);
        }
    }
    
}
    
 

