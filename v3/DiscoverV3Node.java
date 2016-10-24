/*Usage:
#PROCESS test.DiscoverV3Node network-IP node-IP netmask
PROCESS test.DiscoverV3Node
ARGS 172.21.0.0           172.21.142.245        255.255.0.0
*/
package test;

import com.adventnet.nms.util.*;
import com.adventnet.db.PopulateUserTable;
import com.adventnet.nms.topodb.*;
import java.util.Properties;

public class DiscoverV3Node implements RunProcessInterface
{
        public void callMain(String args[])
        {   
                try{
                        TopoAPI topoApi=(TopoAPI)NmsUtil.getAPI("TopoAPI"); 
                        while(topoApi == null) {
                                try{Thread.sleep(25000);} catch(Exception exp){}
                                topoApi=(TopoAPI)NmsUtil.getAPI("TopoAPI"); 
                        }   
                        PopulateUserTable populateUserTable=PopulateUserTable.getInstance();  
                        //hostIpaddress is the network address - Eg. 192.168.42.0 / 172.21.0.0
                        String netmask = "255.255.255.0";
                        String nodeId = "127.0.0.1";
                        String hostIpaddress[]= {args[0]}; 
                        Properties pro=new Properties(); 

                        /*NoAuthNoPriv*/
                        pro.put("SNMPAGENTPORT","8004"); 
                        pro.put("SECURITYLEVEL","NoAuthNoPriv"); 
                        pro.put("USERNAME","noAuthUser"); 
                        pro.put("CONTEXT","noAuth");
                        //populateUserTable.insertIntoUserTable(pro,hostIpaddress);  //Uncomment this to discover a node in NoAuthNoPriv mode
                        /*NoAuthNoPriv Ends*/

                        /*AuthNoPriv*/
                        pro.put("SECURITYLEVEL","AuthNoPriv"); 
                        pro.put("AUTHPROTOCOL","MD5"); 
                        pro.put("USERNAME","authUser"); 
                        pro.put("AUTHPASSWORD","authUser"); 
                        pro.put("CONTEXT","auth");
                        //populateUserTable.insertIntoUserTable(pro,hostIpaddress); //Uncomment this to discover a node in AuthNoPriv mode
                        /*AuthNoPriv Ends*/

                        /*AuthPriv*/
                        pro.put("SECURITYLEVEL","AuthPriv"); 
                        pro.put("AUTHPROTOCOL","MD5"); 
                        pro.put("PRIVPROTOCOL","CBC-DES"); 
                        pro.put("USERNAME","privUser"); 
                        pro.put("AUTHPASSWORD","authUser"); 
                        pro.put("PRIVPASSWORD","privUser"); 
                        pro.put("CONTEXT","priv");
                        /*AuthPriv Ends*/
                        System.err.println("props at last : "+pro);

                        populateUserTable.insertIntoUserTable(pro,hostIpaddress); //Comment this to when the security is NOT AuthPriv
                        System.err.println("After insertIntoUserTable");
                        /*UMCOMMENT THE BELOW code snippet if the argument is a NODE. If the argument is a network, DO NOT UNCOMMENT the below */
                        //args[1] : Node Address. args[2] - Netmask (If the netmask is not given, 255.255.255.0 is taken as netmask
                        if(args[1]!=null){
                                nodeId = args[1];
                        }   
                        if(args[2]!=null){
                                netmask = args[2];
                        }   
                        System.err.println("nodeId, netmask :"+nodeId+", "+netmask);
                        Node node=new Node(nodeId,netmask); 
                        node.setVersion("v3"); 
                        node.setUserName(pro.getProperty("USERNAME")); 
                        node.setIsDHCP(false);
                        node.setContextName(pro.getProperty("CONTEXT"));
                        Properties props = new Properties();
                        props.setProperty("version", "v3");
                        props.setProperty("snmpport",pro.getProperty("SNMPAGENTPORT"));
                        node.setProperties(props);
                        System.err.println("Result of Add Node : ==> "+topoApi.addNode(node,false,false,false));
                }   
                catch(Exception e) {e.printStackTrace();}
        }                            
        public boolean isInitialized()  {return true;}
        public void shutDown() {} 
}
