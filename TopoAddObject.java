import java.util.*;
import java.rmi.Naming;
import com.adventnet.nms.topodb.*;
import com.adventnet.nms.topodb.ManagedObject;
import java.rmi.server.RMISocketFactory;
public class TopoAddObject
{
        public static void main(String[] args) throws Exception
        {
                try{
			if(args.length != 2)
			{
				System.err.println("USAGE: java TopoAddObject <NMS_Server_Host> <RMI_REG_PORT>");
				System.exit(0);
			}
                        //ManagedObject mo_Node=new ManagedObject();
                        TopoAPI topocs = (TopoAPI)Naming.lookup("//"+args[0]+":"+args[1]+"/TopoAPI");
			System.err.println("topocs is::"+topocs);

                        /*String NodeName = "Node_2"+args[0];
                        mo_Node.setName(NodeName);
			mo_Node.setType("Node");
                Properties prop = new Properties();
                prop.put("groupIDs","xxxx");
                mo_Node.setProperties(prop);
                System.err.println("Add Object result is::"+topocs.addObject(mo_Node));*/
                Properties prop = new Properties();
                prop.put("name","*");
                System.err.println("getObjectNamesWithProps ::"+topocs.getObjectNamesWithProps(prop));
                }
                catch(Exception e)
                {
                        e.printStackTrace();
                }

        }
}
