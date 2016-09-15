import java.rmi.*;
import java.rmi.server.*;
import java.util.*;
import com.adventnet.nms.topodb.*;
import com.adventnet.nms.store.*;
import com.adventnet.nms.util.*;
import com.adventnet.nms.startnms.*;
import java.io.*;
	
public class TestTool implements Serializable {
	static TopoAPI topo = null;
	public TestTool() {
		Vector vect = new Vector();
		Enumeration enum;
		ManagedObject mobj;
		Properties prop;
		Set s;
		Iterator itr;
		String param = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
	}
	public void getGroupNames(TopoAPI topo) {
		try {
			System.out.println("GET-GROUP-NAMES");
			Vector vect = topo.getGroupNames();
			Enumeration enum = vect.elements();
			while(enum.hasMoreElements()) {
				System.out.println(enum.nextElement());
			}
		}catch(Exception e) {
			System.out.println("Exception" + e);
		}
	}
	public void getGroupNamesOfMO(TopoAPI topo) {
		try {
			System.out.println("GET-GROUP-NAMES-OF-MO");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Give name of object-->>");
			String param = br.readLine();
			String[] stri = topo.getGroupNamesOfMO(param);
			if (stri != null) {
				System.out.println(stri.length);
				for( int i=0; i<stri.length; i++) {
					System.out.println(stri[i].toString());
				}
			}else {
				System.out.println("null");
			}
		}catch(Exception e) {
			System.out.println("Exception in Group Names" + e);
			e.printStackTrace();
		}
	}
	public void getMembersOfGroup(TopoAPI topo) {
		try {
			System.out.println("GET-MEMBERS-OF-GROUP");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Give Group name-->>");
			String param = br.readLine();
			Vector vect = topo.getMembersOfGroup(param);
			System.out.println(vect.size());
			Enumeration enum = vect.elements();
			while (enum.hasMoreElements()) {
				System.out.println(enum.nextElement());
			}
		}catch(Exception e) {
			System.out.println("Exception in Get Members of group " + e);
		}
	}
	public void removeMOFromGroup(TopoAPI topo) {
		try {
			System.out.println("REMOVE-MO-FROM-GROUP");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Give number of Managed Objects-->>");
			int num = Integer.parseInt(br.readLine());
			String[] str = new String[num];
			for (int i=0; i<num; i++){
				System.out.print("Give name of [ " + i + " ] ManagedObject-->>");
				str[i] = br.readLine();
			}
			System.out.print("Give name of Group-->>");
			String param = br.readLine();
			System.out.println(topo.removeMOFromGroup(str, param));
		}catch(Exception e) {
			System.out.println("Exception in remove mo from group :" + e);
		}
	}
	public void setGroupsForMO(TopoAPI topo) {
		try {
			System.out.println("SET-GROUPS-FOR-MO");
			boolean append = false;
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Give name of Object-->>");
			String param = br.readLine();
			System.out.print("Give number of Groups-->>");
			int param1 = Integer.parseInt(br.readLine());
			String[] stri = new String[param1];
			for (int i=0; i<param1; i++) {
				System.out.print("Give groupname [ " + i + " ] -->>");
				stri[i] = br.readLine();
			}
			System.out.print("Give value for append-->>");
			String param2 = br.readLine();
			if (param2.equalsIgnoreCase("true")) {
				append = true;
			}
			System.out.println("append" + append);
			System.out.println(topo.setGroupsForMO(param,stri,append));
		}catch(Exception e) {
			System.out.println("Exception in set Groups for mo : " + e);
		}
	}
	public void setChildrenKeys(TopoAPI topo) {
		try {
			System.out.println("SET-CHILDREN-KEYS");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Give-Name-Of-Managed-Object-->>");
			String param = br.readLine();
			System.out.print("Give-Number-Of-Children-Keys-->>");
			int param1 = Integer.parseInt(br.readLine());
			String[] stri = new String[param1];
			for(int i=0; i<param1; i++) {
				System.out.print("Give Name Of Children Key [" + i + "]-->>");
				stri[i] = br.readLine();
			}
			ManagedObject mobj = topo.getByName(param);
			mobj.setChildrenKeys(stri);
			System.out.println("UpdateObject  :" + topo.updateObject(mobj));
		}catch(Exception e) {
			System.out.println("Exception in set Children Keys  :" + e);
		}
	}
	public void getChildrenKeys(TopoAPI topo) {
		try {
			System.out.println("GET-CHILDREN-KEYS");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("GIVE-NAME-OF-MANAGED-OBJECT-->>");
			String param = br.readLine();
			ManagedObject mobj = topo.getByName(param);
			String[] str = mobj.getChildrenKeys();
			if(str != null){
				for(int i=0; i<str.length; i++) {
					System.out.println("Child  :" + i + "-->>"+ str[i]);
				}
			}else {
				System.out.println("No Children keys");
			}
		}catch (Exception e) {
			System.out.println("Exception in get Children Key  :" + e);
		}
	}
	public void setParentKey(TopoAPI topo) {
		try {
			System.out.println("SET-PARENT-KEY");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("GIVE-NAME-OF-MANAGED-OBJECT-->>");
			String param = br.readLine();
			System.out.print("GIVE-NAME-OF-PARENT-KEY-->>");
			String param1 = br.readLine();
			ManagedObject ob = new ManagedObject();
			ob.setName(param);
			ob.setParentKey(param1);
			System.out.println("parent key" + ob.getParentKey());
			System.out.println(topo.addObject(ob));
		}catch(Exception e) {
			System.out.println("Exception in set ParentKey  : " + e);
		}
	}
	public void getParentKey(TopoAPI topo) {
		try {
			System.out.println("GET-PARENT-KEY");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("GIVE-NAME-OF-MANAGED-OBJECT-->>");
			String param = br.readLine();
			ManagedObject mobj = topo.getByName(param);
			System.out.println(mobj.getParentKey());
		}catch(Exception e) {
			System.out.println("Exception in getParentKey  :" + e);
		}
	}
	public void addObject(TopoAPI topo) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String param = null;
			System.out.println("ADD-OBJECT");
			System.out.print("give choice-->>");
			param = br.readLine();
			if (param.equalsIgnoreCase("network")){
				Network mobj = new Network();
				mobj.setDiscover(true);
				mobj.setManaged(true);
				mobj.setNetmask("255.255.255.0");
				System.out.print("Give IPAddress Of Object-->>");					
				param = br.readLine();
				mobj.setIpAddress(param);				
				System.out.println("ADD OBJECT  : " + topo.addObject(mobj));
			}else if (param.equalsIgnoreCase("node")) {
				Node mobj = new Node();
				System.out.print("Give IPAddress Of Object-->>");					
				param = br.readLine();
				mobj.setIpAddress(param);				
				System.out.println("ADD OBJECT  : " + topo.addObject(mobj));
			}else {
				ManagedObject mobj = new ManagedObject();
				System.out.print("Give name Of Object-->>");					
				param = br.readLine();
				mobj.setName(param);
				System.out.println("ADD OBJECT  : " + topo.addObject(mobj));
			}			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addObjectWithTwo(TopoAPI topo) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String param = null;
			System.out.println("ADD-OBJECT-WITH-TWO-PARAM");
			boolean seed;
			ManagedObject mobj = new ManagedObject();
			System.out.print("Give Name Of Object-->>");					
			param = br.readLine();
			if (param.length() <= 0) {
				System.out.println("No Name Given");
			}else{
				mobj.setName(param);
				System.out.print(" override the seed file (Say true/false)-->>");
				param = br.readLine();
				if (param.length() <= 0) {
					System.out.println("No Choice Given");
				}else{
					if (param.equalsIgnoreCase("true")) {
						seed = true;
					}else {
						seed = false;
					}
					System.out.println("ADD OBJECT WITH TWO PARAM : " + topo.addObject(mobj, seed));
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addNode(TopoAPI topo) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("ADD-NODE");
			Node n = new Node();
			System.out.print("Give IpAddress of Node-->>");					
			String param = br.readLine();
			if (param.length() <= 0) {
				System.out.println("No Name Given");
			}else{
				n.setIpAddress(param);
				//n.setName("rajesh");
				System.out.println("addnode " + topo.addNode(n));
			}
		}catch (Exception e) {
			System.out.println("Exception" + e);
			e.printStackTrace();
		}
	}
	
	public void addNodeWithThree(TopoAPI topo) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String param = null;
			System.out.println("ADD-NODE-WITH-THREE-PARAM");
			boolean seed;
			boolean reach;
			Node n = new Node();
			System.out.print("Give IpAddress of Node-->>");
			param = br.readLine();
			if (param.length() <= 0) {
				System.out.println("No Name Given");
			}else {
				n.setIpAddress(param);
				System.out.print("Give value for seedFlag(say \"true/false\")-->>");
				String param1 = br.readLine();
				if (param1.equalsIgnoreCase("true")) {
					seed = true;
				}else {
					seed = false;
				}
				System.out.print("Give value for reachFlag(say \"true/false\")-->>");
				String param2 = br.readLine();
				if (param2.equalsIgnoreCase("true")) {
					reach = true;
				}else {
					reach = false;
				}
				System.out.println("addnode " + topo.addNode(n,seed,reach));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addNodeToTopoDB(TopoAPI topo) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String param = null;
			System.out.println("ADD-NODE-TO-TOPODB");
			System.out.print("Give IpAddress Of Node-->>");					
			String ipadd = br.readLine();
			if (ipadd.length() <= 0) {
				System.out.println("No Choice Given");
			}else{
				System.out.print("Give Netmask Of Node-->>");					
				String netmask = br.readLine();
				if (netmask.length() <= 0) {
					System.out.println("No Choice Given");
				}else{
					System.out.println("Add Node To TopoDB : " + topo.addNodeToTopoDB(ipadd, netmask));
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void addNodeToTopoDBWithFour(TopoAPI topo) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Give IpAddress of Node-->>");
			String ipAddr = br.readLine();
			System.out.print("Give Netmask of Node-->>");
			String netMask = br.readLine();
			System.out.print("Give Community of Node-->>");
			String community = br.readLine();
			System.out.print("Give SNMP port of Node-->>");
			int snmpPort = Integer.parseInt(br.readLine());
			System.out.println("Add Node :" + topo.addNodeToTopoDB(ipAddr, netMask,community, snmpPort));
		}catch(Exception e) {
			System.out.println("Exception in addNodeToTopoDB with four parameters");
		}
	}
	public void addNodesToTopoDBWithStringArray(TopoAPI topo) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String param = null;
			System.out.println("ADD-NODES-TO-TOPODB-WITH-STRING-ARRAY");
			int chr;
			br = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Give Number Of Nodes-->>");
			param = br.readLine();
			chr = Integer.parseInt(param);
			System.out.println(chr);
			String[] s24 = new String[chr];
			for(int i=0; i<chr; i++) {
				BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
				System.out.print("Give Ip Address Of " + (i + 1) + "th Node-->>");					
				s24[i] = br1.readLine();
			}
			System.out.print("Give Netmask Of Node-->>");	
			BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
			param = br2.readLine();
			System.out.println("addnodes : " + topo.addNodesToTopoDB(s24, param));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void addNodesToTopoDBWithStartEndIP(TopoAPI topo) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String param = null;
			System.out.println("ADD-NODES-TO-TOPODB-WITH-START-END-IP");
			System.out.print("Give Start IPAddress-->>");					
			String param1 = br.readLine();
			if (param1.length() <= 0) {
				System.out.println("No Choice Given");
			}else{
				System.out.print("Give End IPAddress-->>");					
				String param2 = br.readLine();
				if (param2.length() <= 0) {
					System.out.println("No Choice Given");
				}else{
					System.out.print("Give NetMask-->>");					
					String param3 = br.readLine();
					if (param3.length() <= 0) {
						System.out.println("No Choice Given");
					}else{
						System.out.println("addnodes : " + topo.addNodesToTopoDB(param1, param2, param3));
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void addNetwork(TopoAPI topo) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String param = null;
			System.out.println("ADD-NETWORK");
			System.out.print("Give Network IPAddress-->>");					
			param = br.readLine();
			if (param.length() <= 0) {
				System.out.println("No Choice Given");
			}else{
				System.out.print("Give NetMask-->>");					
				String param1 = br.readLine();
				if (param1.length() <= 0) {
					System.out.println("No Choice Given");
				}else{
					System.out.println("addnetwork : " + topo.addNetwork(param, param1));
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void changeDiscInterval(TopoAPI topo) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String param = null;
			System.out.println("CHANGE-DISC-INTERVAL");
			System.out.print("Give Net IpAddress-->> ");
			String net = br.readLine();
			int chr;
			System.out.print("Give Disc Interval value-->>");
			param = br.readLine();
			chr = Integer.parseInt(param);
			System.out.println(chr);
			System.out.println(topo.changeDiscInterval(net,chr));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void changePingRetries(TopoAPI topo) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String param = null;
			System.out.println("CHANGE-PING-RETRIES");
			System.out.print("Give Net IpAddress-->> ");
			String net = br.readLine();
			int chr;
			System.out.print("Give Ping Retries Value-->>");
			param = br.readLine();
			chr = Integer.parseInt(param);
			System.out.println(chr);
			System.out.println(topo.changePingRetries(net,chr));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void changeSnmpRetries(TopoAPI topo) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String param = null;
			System.out.println("CHANGE-SNMP-RETRIES");
			System.out.print("Give Net IpAddress-->> ");
			String net = br.readLine();
			int chr;
			System.out.print("Give Value of SNMP Retries-->>");
			param = br.readLine();
			chr = Integer.parseInt(param);
			System.out.println(chr);
			System.out.println(topo.changeSnmpRetries(net,chr));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public boolean deleteObject(TopoAPI topo) throws RemoteException {
		Object ob = this.getCompleteList(topo).elementAt(0);
		boolean b = topo.deleteObject(topo.getByName((String)ob));
		if(b) {
			return !(this.getCompleteList(topo).contains(ob));
		}
		return !this.getCompleteList(topo).contains(ob);
	}
	public boolean deleteObjectWithTwo(TopoAPI topo, String str, boolean sub) {
		try {
			if (str.equalsIgnoreCase("Node")) {
				Object ob = this.getNodes(topo).elementAt(0);
//				System.out.println("object : " + ob);
				Node mobj = (Node)topo.getByName((String)ob);
				Vector vect = mobj.getIpaddrs();
				boolean b = topo.deleteObject(mobj, sub);	
				if(sub) {
					//this.getCompleteList(topo).c
					boolean b1 = !(this.getCompleteList(topo).contains(ob) || this.getNodes(topo).contains(ob));
					boolean b2 = true;
					if (vect != null) {
						for (int i=0; i<vect.size(); i++) {
//							System.out.println(vect.elementAt(i));
							b2 = b2 && ! (this.getInterfaces(topo).contains(vect.elementAt(i)) || (this.getCompleteList(topo).contains(vect.elementAt(i))));
						}
					}
					return b1 && b2;
				}
				return !(this.getCompleteList(topo).contains(ob) || this.getNodes(topo).contains(ob));
			}
			if (str.equalsIgnoreCase("Network")){
				Object ob = this.getNetworks(topo).elementAt(0);
				Network net = (Network)topo.getByName((String) ob);
				Vector interfaces = net.getIpaddrs();
				Vector nodes = net.getNodes();
				boolean b = topo.deleteObject(net, sub);
				if (sub) {
					boolean b1 = !(this.getCompleteList(topo).contains(ob) || this.getNetworks(topo).contains(ob));
					boolean b2 = true;
					boolean b3 = true;
					if (nodes != null) {
						for (int i=0; i<nodes.size(); i++) {
							b2 = b2 && !(this.getCompleteList(topo).contains(nodes.elementAt(i)) || 
										 this.getNodes(topo).contains(nodes.elementAt(i)));
						}
					}
					if (interfaces != null) {
						for (int j=0; j<interfaces.size(); j++) {
							b3 = b3 && !(this.getCompleteList(topo).contains(interfaces.elementAt(j)) ||
									 this.getNodes(topo).contains(interfaces.elementAt(j)));
						}
					}
					return b1 && b2 && b3;
				}
				return !(this.getCompleteList(topo).contains(ob) || this.getNetworks(topo).contains(ob));
			}
		}catch(Exception e) {}		
		return false;
	}
	public ManagedObject getByName(TopoAPI topo) {
		try {
			return topo.getByName((String)this.getNodes(topo).elementAt(0));
		}catch(Exception e) {
			System.out.println("Exception  " + e);
		}
		return null;
	}
	public Vector getCompleteList(TopoAPI topo) {
		Vector vect = null;
		try {
			vect = topo.getCompleteList();
		}catch(Exception e) {
			//System.out.println("Exception  " + e);
		}
		return vect;

	}
	public Properties getDiscoveryParameters(TopoAPI topo){
		try {
			return topo.getDiscoveryParameters();
		}catch(Exception e) {
			System.out.println("Exception  " + e);
		}
		return null;
	}
	public boolean getInterface(TopoAPI topo) {
		try {
			IpAddress ip = topo.getInterface((String)this.getInterfaces(topo).elementAt(0));
			ip.getParentNode();
			ip.getParentNet();
			return true;
		}catch(Exception e) {
			System.out.println("Exception  " + e);
		}
		return false;
	}
	public boolean getInterfaceObjectKey(TopoAPI topo) {
		try {
			IpAddress ip = topo.getInterface((String)this.getInterfaces(topo).elementAt(0));
			if(ip.getName().equalsIgnoreCase(topo.getInterfaceObjectKey(ip.getIpAddress()))) {
				return true;
			}else {
				return false;
			}
		}catch(Exception e) {}
		return false;
	}
	public Vector getInterfaces(TopoAPI topo) {
		Vector vect = null;
		try {
			vect = topo.getInterfaces();
		}catch(Exception e) {}
		return vect;
	}
	public Vector getInterfacesOfNetwork(TopoAPI topo) {
		Vector vect = null;
		try {
			vect = topo.getInterfacesOfNetwork((String)this.getNetworks(topo).elementAt(0));
		}catch(Exception e) {}
		return vect;
	}
	public boolean getInterfacesOfNode(TopoAPI topo) {
		try {
			Node n = topo.getNode((String)this.getNodes(topo).elementAt(0));
			Vector ve = n.getIpaddrs();
			Vector vect = topo.getInterfacesOfNode(n.getName());
			System.out.println(ve);
			System.out.println(vect);
			if( ve.equals(vect)) {
				return true;
			}else {
				return false;
			}
		}catch(Exception ex) {}
		return false;
	}
	public Node getNode(TopoAPI topo) {
		try {
			return topo.getNode((String)this.getNodes(topo).elementAt(0));
		}catch(Exception e) {
			System.out.println("Exception  " + e);
		}
		return null;
	}
	public Properties getNodeProperties(TopoAPI topo) {
		try {
			return topo.getNodeProperties((String)this.getNodes(topo).elementAt(0));
		}catch(Exception e) {
			System.out.println("Exception  " + e);
		}
		return null;
	}
	public Vector getNodesOfNetwork(TopoAPI topo) {
		Vector vect = null;
		try {
			vect = topo.getNodesOfNetwork((String)this.getNetworks(topo).elementAt(0));
		}catch(Exception e) {}
		return vect;
	}
	public Vector getNodes(TopoAPI topo) {
		Vector vect = null;
		try {
			vect = topo.getNodes();
		}catch(Exception e) {
			System.out.println("Exception  " + e);
		}
		return vect;
	}
	public Network getNet(TopoAPI topo) {
		try {
			System.out.println(this.getNetworks(topo).elementAt(1));
			return topo.getNet((String)this.getNetworks(topo).elementAt(1));			
		}catch(Exception e) {
			System.out.println("Exception  " + e);
		}
		return null;
	}
	public Vector getNetworks(TopoAPI topo) {
		Vector vect = null;
		try {
			vect = topo.getNetworks();
		}catch(Exception e) {
			System.out.println("Exception  " + e);
		}
		return vect;
	}
	public boolean getObjects(TopoAPI topo) {
		try {
			String str1 = null;
			String str = null;
			Properties proper = new Properties();
			proper.put("type", "SnmpNode");
			Vector vect = topo.getObjects("ManagedObject", proper);
			Enumeration enum = vect.elements();
			while(enum.hasMoreElements()) {
				str1 = enum.nextElement().toString();
				System.out.println(str1);
				Properties prop = topo.getPropertiesOfObject(str1);
				Set s = prop.keySet();
				Iterator it = s.iterator();
				while (it.hasNext()) {
					str = (String)it.next();
					if(!(prop.getProperty(str).equals(proper.getProperty(str)))) {
						return false;
					}
				}
			}
		}catch (Exception e) {
			System.out.println("Exception  " + e);
			return false;
		}
		return true;
	}
	public boolean getObjectNamesWithProps(TopoAPI topo, Properties proper) {
		try {
			Vector vect = topo.getObjectNamesWithProps(proper);
			String str = null;
			Enumeration enum = vect.elements();
			while(enum.hasMoreElements()) {
				Properties prop = topo.getPropertiesOfObject(enum.nextElement().toString());
				Set s = proper.keySet();
				Iterator itr = s.iterator();
				while(itr.hasNext()) {
					str = (String)itr.next();
					if (!(prop.getProperty(str).equals(proper.getProperty(str)))) {
						return false;
					}
				}
			}
		}catch(Exception e) {
			System.out.println("Exception  " + e);
			return false;
		}
		return true;
	}
	public Properties getPropertiesOfObject(TopoAPI topo) {
		try {
			return topo.getPropertiesOfObject((String)this.getCompleteList(topo).elementAt(0));
		}catch(Exception e) {
			System.out.println("Exception  " + e);
		}
		return null;
	}
	public boolean refreshObject(TopoAPI topo) {
		try {
			return topo.refreshObject(topo.getNode((String)this.getNodes(topo).elementAt(0)));
		}catch(Exception e) {
			System.out.println("Exception  " + e);
		}
		return false;
	}
	public boolean removeNodeFromTopoDB(TopoAPI topo) {
		try {
			Object name = this.getNodes(topo).elementAt(0);
			System.out.println(name);
			String ip = (topo.getNode((String)name).getIpAddress());
			boolean b = topo.removeNodeFromTopoDB(ip);
			boolean b1 = !(this.getCompleteList(topo).contains(name) &&
						 this.getNodes(topo).contains(name));
			return b && b1;
		}catch(Exception e) {
			System.out.println("Exception  " + e);
		}
		return false;
	}
	public boolean removeNodesFromTopoDB(TopoAPI topo) {
		try {
			String[] str = new String[4];
			for (int i=0; i<2; i++) {
				str[i] = (String)this.getNodes(topo).elementAt(i);
				System.out.println(str[i]);
			}
			boolean b = topo.removeNodesFromTopoDB(str);
			boolean b1 = true;
			for (int j=0; j<2; j++) {
				b1 = b1 && !((this.getNodes(topo).contains(str[j])) && 
					 (this.getCompleteList(topo).contains(str[j])));
			}
			return b && b1;
		}catch(Exception e){
			System.out.println("Exception  " + e);
		}
		return false;
	}
	public void removeAddressRangeToDiscover(TopoAPI topo) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("REMOVE-ADDRESS-RANGE-TO-DISCOVER");
			System.out.print("Give Start IPAddress-->>");
			String param1 = br.readLine();
			if (param1.length() <= 0) {
				System.out.println("No Choice Given");
			}else{
				System.out.print("Give End IPAddress-->>");
				String param2 = br.readLine();
				if (param2.length() <= 0) {
					System.out.println("No Choice Given");
				}else{
					System.out.print("Give Net IPAddress-->>");					
					String param3 = br.readLine();
					if (param3.length() <= 0) {
						System.out.println("No Choice Given");
					}else{
						System.out.print("Give NetMask-->>");
						String param4 = br.readLine();
						if (param4.length() <= 0) {
							System.out.println("No Choice Given");
						}else {
							try {
								System.out.println("Raghav");
								System.out.println(topo.removeAddressRangeToDiscover(param1, param2, param3, param4));
							}catch(Exception e) {
								System.out.println("Exception : " + e);
							}
						}
					}
				}
			}
		}catch(Exception e) {
			System.out.println("Exception  " + e);
		}
	}
	public void setAddressRangeToDiscover(TopoAPI topo) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("SET-ADDRESS-RANGE-TO-DISCOVER");
			System.out.print("Give Start IPAddress-->>");					
			String param1 = br.readLine();
			if (param1.length() <= 0) {
				System.out.println("No Choice Given");
			}else{
				System.out.print("Give End IPAddress-->>");					
				String param2 = br.readLine();
				if (param2.length() <= 0) {
					System.out.println("No Choice Given");
				}else{
					System.out.print("Give Net IPAddress-->>");					
					String param3 = br.readLine();
					if (param3.length() <= 0) {
						System.out.println("No Choice Given");
					}else{
						System.out.print("Give NetMask-->>");
						String param4 = br.readLine();
						if (param4.length() <= 0) {
							System.out.println("No Choice Given");
						}else {
							try {
								System.out.println("param1  :" + param1);
								System.out.println("param2  :" + param2);
								System.out.println("param3  :" + param3);
								System.out.println("param4  :" + param4);
								System.out.println(topo.setAddressRangeToDiscover(param1, param2, param3, param4));
							}catch (Exception ex) {
								System.out.println("Caught Exception" + ex);
							}															  
						}	
					}
				}
			}
		}catch(Exception e) {
			System.out.println("Exception  " + e);
		}
	}
	public void setDiscover(TopoAPI topo) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("SET-DISCOVER");
			System.out.print("Give Net IPAddress-->>");					
			String param = br.readLine();
			System.out.println("setdiscovery  : " + topo.setDiscover(param, true));
		}catch(Exception e) {
			System.out.println("Exception  " + e);
		}
	}
	public void stopDiscover(TopoAPI topo) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("STOP-DISCOVER");
			System.out.print("Give Net IPAddress-->>");					
			String param = br.readLine();
			System.out.println("stopdiscovery  : " + topo.setDiscover(param, false));
		}catch(Exception e) {
			System.out.println("Exception  " + e);
		}
	}		
	public void setDiscoveryParameters(TopoAPI topo) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("SET-DISCOVERY-PARAMETERS");
			Properties prop = new Properties();
			System.out.print("Give property key-->>");
			String param = br.readLine();
			System.out.print("Give property value-->>");
			String param1 = br.readLine();
			//prop.clear();
			prop.put(param,param1);
			try {
				System.out.println(prop.size());
				//prop.clear();
				System.out.println("SET--DISCOVERY-PARAMETERS : " + topo.setDiscoveryParameters(prop));
			}catch(Exception e) {
				System.out.println("Exception in set Disc Param" + e);
			}
			Set s = prop.keySet();
			Iterator itr = s.iterator();
			while(itr.hasNext()){
				String str = (String)itr.next();
				System.out.println("The Property of : " + str + " is : " +
								prop.getProperty(str));
			}
		}catch(Exception e) {
			System.out.println("Exception  " + e);
		}
	}
	public void setManaged(TopoAPI topo) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("SET-MANAGED");
			boolean setmanage;
			System.out.print("GiveNameOfNode-->>");					
			String param = br.readLine();
			System.out.print("Want to manage Network(say true/false)-->>");					
			String param1 = br.readLine();
			if (param1.equalsIgnoreCase("true")) {
				setmanage = true;
			}else {
				setmanage = false;
			}
			topo.setManaged(param, setmanage);
		}catch(Exception e) {
			System.out.println("Exception  " + e);
		}		
	}
	public void setManagedAllNode(TopoAPI topo) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("SET-MANAGED-ALL-NODE");
			boolean setmanage;
			System.out.print("Give Name Of Node-->>");					
			String param = br.readLine();
			Node n = topo.getNode(param);
			System.out.print("Want to manage Node(say true/false)-->>");					
			String param1 = br.readLine();
			if (param1.equalsIgnoreCase("true")) {
				setmanage = true;
			}else {
				setmanage = false;
			}
			topo.setManagedAll(n,setmanage);
		}catch(Exception e) {
			System.out.println("Exception  " + e);
		}
	}
	public void setManagedAllNetwork(TopoAPI topo){
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("SET-MANAGED-ALL-NETWORK");
			boolean setmanage;
			System.out.print("Give Name Of Network-->>");					
			String param = br.readLine();
			Network n = topo.getNet(param);
			System.out.print("Want to manage Network(say true/false)-->>");					
			String param1 = br.readLine();
			if (param1.equalsIgnoreCase("true")) {
				setmanage = true;
			}else {
				setmanage = false;
			}
			topo.setManagedAll(n,setmanage);
		}catch(Exception e) {
			System.out.println("Exception  " + e);
		}
	}
	public void updateObject(TopoAPI topo) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("UPDATE-OBJECT");
			System.out.print("GiveNameOfNode-->>");					
			String param = br.readLine();
			/*RmiLookUpExample rmiup = new RmiLookUpExample();
			rmiup.getByName(topo);*/
			ManagedObject mobj = topo.getByName(param);
			if(mobj != null) {
				System.out.print("Give the display name you want to change-->>");
				param = br.readLine();
				mobj.setDisplayName(param);
				System.out.println("update " + topo.updateObject(mobj));
			}else {
				System.out.println("update " + topo.updateObject(mobj));
			}
		}catch(Exception e) {
			System.out.println("Exception  " + e);
		}
	}
	public void updateStatus(TopoAPI topo) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("UPDATE-STATUS");
			System.out.print("GiveNameOfNode-->>");					
			String param = br.readLine();
			System.out.print("Give Status Value-->>");
			int chr;
			String param1 = br.readLine();
			chr = Integer.parseInt(param1);
			System.out.println(chr);
			System.out.println("UPDATE STATUS : " + topo.updateStatus(param, chr));
		}catch(Exception e) {
			System.out.println("Exception  " + e);
		}
	}
	public void checkOut(TopoAPI topo) {
		try {
			System.out.print("GiveNameOfNode-->>");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String param = br.readLine();
			System.out.println("new");
			ManagedObject mobj = topo.checkOut(param);
			if (mobj != null) {
				System.out.println("UNLOCK");
				System.out.print("Press 'Enter' to unlock");
				param = br.readLine();
				topo.unlock(mobj);
			}else {
				System.out.println("Null value");
			}			
		}catch(Exception e) {
			System.out.println("Exception  " + e);
			e.printStackTrace();
		}
	}
	public void checkOutIfAvailable(TopoAPI topo) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("CHECK-OUT-IF-AVAILABLE");
			System.out.print("GiveNameOfNode-->>");					
			String param = br.readLine();
			ManagedObject mobj = topo.checkOutIfAvailable(param);
			if (mobj != null) {
				System.out.println("UNLOCK");
				System.out.print("Press 'Enter'to unlock");
				param = br.readLine();
				topo.unlock(mobj);
			}else {
				System.out.println("Null value");
			}
		}catch(Exception e) {
			System.out.println("Exception  " + e);
		}				
	}
	public boolean handle(){
		try {
			Runtime r = Runtime.getRuntime();
			Process p = r.exec("java RmiLookUpExample");
			BufferedReader bre = new BufferedReader(new InputStreamReader(p.getInputStream()));
			if (bre.readLine() != null) {
				return true;
			}
		}catch(Exception e) {
			System.out.println("Exception in receiving handle");
			e.printStackTrace();
		}
		return false;

	}

	public static void main(String args[]) {
		try {
			topo = (TopoAPI)Naming.lookup("rmi://" 
									  + java.net.InetAddress.getByName("topont").getHostName() 
									  + "/TopoAPI");
			TestTool testObj = new TestTool();
			System.out.println("handle");
			boolean b = testObj.handle();
			System.out.println(b);
			if (b) {
				////////GETCOMPLETELIST
				Vector ve = testObj.getCompleteList(topo);
				if (ve != null) {
					System.out.println("GET-COMPLETE-LIST                   : " + "PASSED");
				}else {
					System.out.println("GET-COMPLETE-LIST                   : " + "FAILED");
				}
				ve = testObj.getNodes(topo);
				if (ve != null) {
					System.out.println("GET-NODES                           : " + "PASSED");
				}else {
					System.out.println("GET-NODES                           : " + "FAILED");
				}
				ve = testObj.getInterfaces(topo);
				if (ve != null) {
					System.out.println("GET-INTERFACES                      : " + "PASSED");
				}else {
					System.out.println("GET-INTERFACES                      : " + "FAILED");
				}
				ve = testObj.getNetworks(topo);
				if (ve != null) {
					System.out.println("GET-NETWORKS                        : " + "PASSED");
				}else {
					System.out.println("GET-NETWORKS                        : " + "FAILED");
				}
				ve = testObj.getNodesOfNetwork(topo);
				if (ve != null) {
					System.out.println("GET-NODES-OF-NETWORK                : " + "PASSED");
				}else {
					System.out.println("GET-NODES-OF-NETWORK                : " + "FAILED");
				}
				ve = testObj.getInterfacesOfNetwork(topo);
				if (ve != null) {
					System.out.println("GET-INTERFACES-OF-NETWORK           : " + "PASSED");
				}else {
					System.out.println("GET-INTERFACES-OF-NETWORK           : " + "FAILED");
				}
				if (testObj.getByName(topo) != null) {
					System.out.println("GET-BY-NAME                         : " + "PASSED");
				}else {
					System.out.println("GET-BY-NAME                         : " + "FAILED");
				}
				if (testObj.getDiscoveryParameters(topo) != null) {
					System.out.println("GET-DISCOVERY-PARAMETERS            : " + "PASSED");
				}else {
					System.out.println("GET-DISCOVERY-PARAMETERS            : " + "FAILED");
				}
				if (testObj.getInterface(topo)) {
					System.out.println("GET-INTERFACE                       : " + "PASSED");
				}else {
					System.out.println("GET-INTERFACE                       : " + "FAILED");
				}
				if(testObj.getInterfaceObjectKey(topo)) {
					System.out.println("GET-INTERFACE-OBJECT-KEY            : " + "PASSED");
				}else {
					System.out.println("GET-INTERFACE-OBJECT-KEY            : " + "FAILED");
				}
				if(testObj.getInterfacesOfNode(topo)) {
					System.out.println("GET-INTERFACES-OF-NODE              : " + "PASSED");
				}else {
					System.out.println("GET-INTERFACES-OF-NODE              : " + "FAILED");
				}
				if (testObj.getNet(topo) != null) {
					System.out.println("GET-NETWORK                         : " + "PASSED");
				}else {
					System.out.println("GET-NETWORK                         : " + "FAILED");
				}
				if (testObj.getNode(topo) != null) {
					System.out.println("GET-NODE                            : " + "PASSED");
				}else {
					System.out.println("GET-NODE                            : " + "FAILED");
				}
				if (testObj.getNodeProperties(topo) != null) {
					System.out.println("GET-NODE-PROPERTIES                 : " + "PASSED");
				}else {
					System.out.println("GET-NODE-PROPERTIES                 : " + "FAILED");
				}
				if (testObj.getPropertiesOfObject(topo) != null) {
					System.out.println("GET-PROPERTIES-OF-OBJECT            : " + "PASSED");
				}else {
					System.out.println("GET-PROPERTIES-OF-OBJECT            : " + "FAILED");
				}
				if (testObj.refreshObject(topo)) {
					System.out.println("REFRESH-OBJECT                      : " + "PASSED");
				}else {
					System.out.println("REFRESH-OBJECT                      : " + "FAILED");
				}
				if (testObj.removeNodeFromTopoDB(topo)) {
					System.out.println("REMOVE-NODE-FROM-TOPODB             : " + "PASSED");
				}else {
					System.out.println("REMOVE-NODE-FROM-TOPODB             : " + "FAILED");
				}
				if(testObj.removeNodesFromTopoDB(topo)) {
					System.out.println("REMOVE-NODES-FROM-TOPODB            : " + "PASSED");
				}else {
					System.out.println("REMOVE-NODES-FROM-TOPODB            : " + "FAILED");
				}
				if(testObj.deleteObject(topo)) {
					System.out.println("DELETE-OBJECT                       : " + "PASSED");
				}else {
					System.out.println("DELETE-OBJECT                       : " + "FAILED");
				}
				if(testObj.deleteObjectWithTwo(topo, "Node", true)) {
					System.out.println("DELETE-OBJECT-WITH-TWO-FOR-NODE     : " + "PASSED");
				}else {
					System.out.println("DELETE-OBJECT-WITH-TWO-FOR-NODE     : " + "FAILED");
				}
				if(testObj.deleteObjectWithTwo(topo, "Node", false)) {
					System.out.println("DELETE-OBJECT-WITH-TWO-FOR-NODE     : " + "PASSED");
				}else {
					System.out.println("DELETE-OBJECT-WITH-TWO-FOR-NODE     : " + "FAILED");
				}
				if(testObj.deleteObjectWithTwo(topo, "Network", true)) {
					System.out.println("DELETE-OBJECT-WITH-TWO-FOR-NETWORK  : " + "PASSED");
				}else {
					System.out.println("DELETE-OBJECT-WITH-TWO-FOR-NETWORK  : " + "FAILED");
				}
				if(testObj.deleteObjectWithTwo(topo, "Network", false)) {
					System.out.println("DELETE-OBJECT-WITH-TWO-FOR-NETWORK  : " + "PASSED");
				}else {
					System.out.println("DELETE-OBJECT-WITH-TWO-FOR-NETWORK  : " + "FAILED");
				}
				Properties prop = new Properties();
				prop.put("type", "Win*");
				if (testObj.getObjectNamesWithProps(topo, prop)) {
					System.out.println("GET-OBJECT-NAMES-WITH-PROPS         : " + "PASSED");
				}else {
					System.out.println("GET-OBJECT-NAMES-WITH-PROPS         : " + "FAILED");
				}
				if(testObj.getObjects(topo)) {
					System.out.println("GET-OBJECTS                         : " + "PASSED");
				}else {
					System.out.println("GET-OBJECTS                         : " + "FAILED");
				}
			}
		}catch(Exception e) {			
			e.printStackTrace();
			System.out.println("errrrrrrrrrrrrrrrrrrrr");
		}
	}
}