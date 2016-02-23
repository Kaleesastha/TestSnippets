import java.util.*;
import java.rmi.Naming;
import com.adventnet.nms.topodb.*;
public class GetObjectPropsWithProps { 
        public static void main(String[] args) throws Exception {
                try{
			if(args.length != 2) {
				System.err.println("USAGE: java GetObjectPropsWithProps <NMS_Server_Host> <RMI_REG_PORT>");
				System.exit(0);
			}
                        TopoAPI topocs = (TopoAPI)Naming.lookup("//"+args[0]+":"+args[1]+"/TopoAPI");
			Properties criteria = new Properties();

			/*For SNMP uncomment below 3 lines and comment the next 3 lines*/
			criteria.put("type","tl1Node");
			String fetchProperties[] = {"name","tl1port"};
			Vector MOVector  =  topocs.getObjectPropsWithProps("TL1Node",fetchProperties,criteria,true,false); 

			/*criteria.put("isSNMP","true");
			String fetchProperties[] = {"name","snmpport"};
			Vector MOVector  =  topocs.getObjectPropsWithProps("SnmpNode",fetchProperties,criteria,true,false);*/

			ListIterator iter = MOVector.listIterator();
			Vector ports = new Vector();
			while (iter.hasNext()) {
				Properties prop = (Properties)iter.next();
				System.err.println("==>"+prop);
				//String port =  prop.getProperty("snmpport");
				String port =  prop.getProperty("tl1port");
				if(!ports.contains(port)){
					ports.add(port);
				}
			}   
			System.err.println("Ports:"+ports);
                }
                catch(Exception e) {e.printStackTrace();}
        }
}
