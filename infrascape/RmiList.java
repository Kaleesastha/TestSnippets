/*javac -d . RmiList.java
java test.RmiList <IP_Address_of_server> <RMI_PORT*/
package test;
import java.util.*;
import java.rmi.*;
import com.adventnet.nms.topodb.TopoAPI;

public class RmiList {
    public static void main(String[] argv) {
        System.out.println("main: started");
        String urlString = "//"+argv[0]+":"+argv[1];
        try{
            String[] n = Naming.list(urlString);
            for (int i=0; i<n.length; i++) System.out.println(n[i]);
        }catch (Exception re){
            System.out.println ( "Error in getting the handle: " + re);
            re.printStackTrace();
        } try{
            TopoAPI topo = (TopoAPI)Naming.lookup(urlString+"/TopoAPI");
            System.err.println("==> " +topo.getDiscoveryParameters());
        }catch (Exception re){
            System.out.println ( "Error in getting the handle: " + re);
            re.printStackTrace();
        }
    }
}
