package test;

import com.adventnet.nms.topodb.*;
import java.rmi.Naming;
public class AddV2NodeRMI
{
        public static void main(String args[])
        {
                try{
                        TopoAPI topo = null;
                        SnmpNode node = null;
                        String community = args[0];
                        topo = (TopoAPI) Naming.lookup("//localhost/TopoAPI");
                        node = new SnmpNode();
                        //node.setIpAddress("fe80::2247:47ff:febe:3c65");
                        node.setIpAddress("fe80::2247:47ff:feb1:3c65");
                        node.setNetmask("ffff:ffff:ffff:ffff:0:0:0:0");
                        node.setSnmpport(8003);
                        node.setCommunity(community);
                        node.setWriteCommunity(community);
                        String message = topo.addNode(node, true, false, false);
                        System.err.println("message after addNode : "+message);
                }
                catch(Exception e)
                {
                        e.printStackTrace();
                }
        }
}
