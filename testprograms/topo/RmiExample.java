import java.rmi.*;
import java.rmi.server.*;
import java.util.*;
import com.adventnet.nms.topodb.*;
import com.adventnet.nms.store.*;
import com.adventnet.nms.util.*;
import com.adventnet.nms.startnms.*;
import test.*;
import java.io.*;


public class RmiExample implements TopoObserver, Serializable, TopoSubscriber 
{
    static TopoAPI topo = null;
    public RmiExample() throws Exception
    {

        Vector vect = new Vector();
        Enumeration enumm;
        ManagedObject mobj;
        Properties prop;
        Set s;
        Iterator itr;
        String param = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    }
    // TOPO_SUBSCRIBER & TOPO_OBSERVER METHODS
    public void initialize(NmsTreeNode rootNode) throws RemoteException {
    }
    public void update(String type, ManagedObject obj, String[] path) throws RemoteException 
    {
        System.out.println("Update method of TopoSubscriber called for " + obj);
        System.out.println("type " + type);
        System.out.println("path " + path);
        System.out.println();
        System.out.println();
    }
    public void update(String type, String name) {
        System.out.println();   	
        System.out.println();
        System.out.println("Type: ");
        System.out.println("  Name: ");
    } 
    public void update(String type, ManagedObject obj) {
        System.out.println();
        System.out.println();   	
        System.out.println("type      :" + type);
        // if(type.equalsIgnoreCase("deleted")) {
        //System.out.println("Raghav");
        //System.out.println(topo);
        //Properties prop = topo.getPropertiesOfObject(mobj.getname());
        System.out.println("mobj      :" + obj);
        System.out.println("raghav-in-update");
        // }
    } 
    ////////////////// 
    
    /////////LOOP_METHODS
    public void updateStatusLoop(TopoAPI topo) {
        try {
            Vector vect = topo.getCompleteList();
            Enumeration enumm = vect.elements();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Give value for Staus update-->>");
            int val = Integer.parseInt(br.readLine());
            while(enumm.hasMoreElements()) {
                String name = enumm.nextElement().toString();
                System.out.println("Name  : " + name);
                System.out.println("UpdateStatus  " + topo.updateStatus(name,val));
            }
        }catch(Exception e) {
            System.out.println("Exception in update loop  :" + e);
        }
    } 
    public void updateObjectLoop(TopoAPI topo) {
        try {
            Vector vect = topo.getCompleteList();
            Enumeration enumm = vect.elements();
            ManagedObject mobj;
            while (enumm.hasMoreElements()) {
                String str = enumm.nextElement().toString();
                System.out.println(str);
                mobj = topo.getByName(str);
                mobj.setDisplayName("IF-10.51.17.121");
                System.out.println("Update Object  :" + topo.updateObject(mobj));
            }
        }catch(Exception e) {
            System.out.println("Exception : " + e);
        }
    }
    public void refreshObjectLoop(TopoAPI topo) {
        try {
            Vector vect = topo.getCompleteList();
            Enumeration enumm = vect.elements();
            ManagedObject mobj;
            while (enumm.hasMoreElements()) {
                String str = enumm.nextElement().toString();
                mobj = topo.getByName(str);
                if (mobj instanceof Node) {
                    System.out.println();
                    System.out.println("Node name " + mobj);
                    System.out.println("Refresh Node   : " + topo.refreshObject(mobj));
                }else {
                    System.out.println("not a node " + mobj);
                    System.out.println();
                }
            }
        }catch(Exception e) {
            System.out.println("Exception  : " + e);
        }
    }
    public void addObjectLoop(TopoAPI topo) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Give name of ManagedObject-->>");
            String str = br.readLine();
            System.out.print("Give number of objects-->>");
            int count = Integer.parseInt(br.readLine());
            ManagedObject mobj;
            for (int i=0; i<count; i++) {
		//str = str + i;
                mobj = new ManagedObject();
                mobj.setName(str + i);
                System.out.println("adding object : " + str + i + topo.addObject(mobj));
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void addNetLoop(TopoAPI topo) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Give net Address-->>");
            String param = br.readLine();
            System.out.print("Give netmask-->>");
            String netmask = br.readLine();
            StringTokenizer st = new StringTokenizer(param, ".");
            int count = st.countTokens();
            String[] stArr = new String[count];
            int i=0;
            while(st.hasMoreTokens()) {
                stArr[i] = st.nextToken();
                i = i + 1;
            }
            int Ip = Integer.parseInt(stArr[i-2]);
            int l = 1;
            do {
                System.out.println("Add Network :" + topo.addNetwork(param, netmask));
                l = l+1;
                Ip = Ip + 1;
                stArr[i-2] = String.valueOf(Ip);
                param = stArr[0];
                for(int j=1; j<(i); j++) {
                    param = param + "." + stArr[j];
                }
                System.out.println("para " + param);
            }while(l < 100);
        }catch(Exception e) {
            System.out.println("Exception " + e);
        }
    }
    public void addNodeLoop(TopoAPI topo) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Give node IpAddress-->>");
            String param = br.readLine();
            StringTokenizer st = new StringTokenizer(param, ".");
            String[] stArr = new String[st.countTokens()];
            int i = 0;
            while(st.hasMoreTokens()) {
                stArr[i] = st.nextToken();
                i = i + 1;
            }
            int Ip = Integer.parseInt(stArr[i-1]);
            int l = 1;
            do {
                Node no = new Node();
                l = l + 1;
                stArr[i-1] = String.valueOf(Ip);
                Ip = Ip + 1;
                param = stArr[0];
                for(int j=1; j<i; j++) {
                    param = param + "." + stArr[j];
                }
                System.out.println(param);
                no.setIpAddress(param);
                System.out.println("IpAddrs :" + no.getIpAddress());
                System.out.println("AddNode  : " + topo.addNode(no));
            }while(l<100);
        }catch(Exception e) {
            System.out.println("Exception in addNodeLoop  :" + e);
        }
    }
    public void deleteObjectLoop(TopoAPI topo) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Give name of Object-->>");
            String param = br.readLine();
            StringTokenizer st = new StringTokenizer(param, ".");
            int i = 0;
            String[] stArr = new String[st.countTokens()];
            while(st.hasMoreTokens()) {
                stArr[i] = st.nextToken();
                i = i + 1;
            }
            int Ip = Integer.parseInt(stArr[i-2]);
            int l = 0;
            do {
                stArr[i-2] = String.valueOf(Ip);
                Ip = Ip + 1;
                l = l + 1;
                param = stArr[0];
                for (int j=1; j<i; j++) {
                    param = param + "." + stArr[j];
                }
                System.out.println("param  : " + param);
                ManagedObject mobj = topo.getByName(param);
                System.out.println("Delete Object" + topo.deleteObject(mobj));
            }while(l<100);
        }catch( Exception e) {
            System.out.println("Exception in delete Object Loop  " + e);
        }
    }
    ///////////////////////

    ////////LOCK_UNLOCK
    public void unlock(TopoAPI topo) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Give name of managedObject-->>");
            String str = br.readLine();
            ManagedObject mobj = topo.getByName(str);
            System.out.println("unlock -------");
            topo.unlock(mobj);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void checkOut(TopoAPI topo) {
        try {
            System.out.print("GiveNameOfNode-->>");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String param = br.readLine();
            System.out.println("new");
            //System.out.println(topo.checkOut(param));
            ManagedObject mobj = topo.checkOut(param);
            //ManagedObject mobj = topo.getByName(param);
            if (mobj != null) {
                System.out.println("UNLOCK");
                updateObject(topo);
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
    public void lock(TopoAPI topo) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Give name of object to be locked-->>");
            String param = br.readLine();
            ManagedObject mobj = topo.getByName(param);
            System.out.print("Give type of lock-->>");
            int type = Integer.parseInt(br.readLine());
            System.out.print("Give timeout value-->");
            int timeout = Integer.parseInt(br.readLine());
            ManagedObject newObj = (ManagedObject)topo.lock(mobj, type, timeout);
            System.out.println("writepermission " + topo.checkWritePermission(newObj));
            System.out.print("Press Enter to unlock-->>");
            br.readLine();
            topo.unlock(newObj);
        }catch(Exception e) {
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
                System.out.println("obj not found");
            }
        }catch(Exception e) {
            System.out.println("Exception  " + e);
        }				
    }
    public void getCurrentLockType(TopoAPI topo) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Give ManagedObject name-->>");
            String param = br.readLine();
            ManagedObject mobj = topo.getByName(param);
            System.out.println("Current lock type of " + param + " is " + topo.getCurrentLockType(mobj));
        }catch(Exception e) {
            System.out.println("Exception " + e);
        }
    }
    public void checkWritePermission(TopoAPI topo) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Give managedObject name-->>");
            String param = br.readLine();
            ManagedObject mobj = topo.getByName(param);
            System.out.println("Write Permission for " + param + " is "  + topo.checkWritePermission(mobj));
        }catch(Exception e) {
            System.out.println("Exception " + e);
        }
    }
    /////////////
    
    ///////PARENT_CHILD_GROUP_RELATION
    
    public void getGroupNames(TopoAPI topo) {
        try {
            System.out.println("GET-GROUP-NAMES");
            Vector vect = topo.getGroupNames();
            Enumeration enumm = vect.elements();
            while(enumm.hasMoreElements()) {
                System.out.println(enumm.nextElement());
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
            Enumeration enumm = vect.elements();
            while (enumm.hasMoreElements()) {
                System.out.println(enumm.nextElement());
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
            append = Boolean.valueOf(br.readLine()).booleanValue();
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
            System.out.println("updateObject" + topo.updateObject(ob));
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
    public void addObjectWithGroupProperties(TopoAPI topo) {
        try {
            System.out.println("ADD-OBJECT-WITH-PROPERTIES");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("GIVE-NAME-OF-MANAGEDOBJECT-->>");
            String name = br.readLine();
            ManagedObject obj = new ManagedObject();
            obj.setName(name);
            System.out.print("Give Number of GroupNames-->>");
            int num = Integer.parseInt(br.readLine());
            String[] str = new String[num];
            for (int i=0; i<num; i++) {
		//BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Give GroupNames [" + i + "] -->>");
                str[i] = br.readLine();
            }
            //System.out.println("str " + str);
            //System.out.print("Give GroupNames for MO-->>");
            obj.setGroupNames(str);
            System.out.println("group names " + obj.getGroupNames());
            System.out.println(topo.addObject(obj));
        }catch(Exception e) {
            System.out.println("Exception in addObjectWithProperties");
        }
    }
    ////////////////
    public void addObject(TopoAPI topo) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String param = null;
            String param1 = null;
            System.out.println("ADD-OBJECT");
            System.out.print("give choice-->>");
            param = br.readLine();
            if (param.equalsIgnoreCase("network"))
            {
                Network mobj = new Network();
                mobj.setManaged(false);
                mobj.setNetmask("255.255.255.0");
                //System.out.print("Give Owner Name of object-->>");
                //String name = br.readLine();
                //mobj.setOwnerName(null);
                System.out.print("Give IPAddress Of Object-->>");
                param = br.readLine();
                mobj.setIpAddress(param);
                System.out.println("Give the number of properties --->");
                int propnum=Integer.parseInt(br.readLine());
                Properties objprop=new Properties();
                for(int i=0;i<propnum;i++)
                {
                    System.out.print("Give property key-->>");
                    param = br.readLine();
                    System.out.print("Give property value-->>");
                    param1 = br.readLine();
                    objprop.put(param, param1);
                    
                }
                
                mobj.setProperties(objprop);
                System.out.println("ADD OBJECT  : " + topo.addObject(mobj));
            }
            
            else if (param.equalsIgnoreCase("node"))
            {
                Node mobj = new Node();
                System.out.print("Give IPAddress Of Object-->>");
                param = br.readLine();
                System.out.print("Give Name Of Object-->>");
                mobj.setName(br.readLine());
                mobj.setIpAddress(param);
                System.out.println("Give the number of properties --->");
                int propnum=Integer.parseInt(br.readLine());
                Properties objprop=new Properties();
                for(int i=0;i<propnum;i++)
                {
                    System.out.print("Give property key-->>");
                    param = br.readLine();
                    System.out.print("Give property value-->>");
                    param1 = br.readLine();
                    objprop.put(param, param1);
                    
                }
                mobj.setProperties(objprop);
                System.out.println("Add Object ----> "+topo.addObject(mobj));
                
            }
            else if (param.equalsIgnoreCase("snmpnode"))
            {
                SnmpNode mobj = new SnmpNode();
                System.out.print("Give IPAddress Of Object-->>");
                param = br.readLine();
                System.out.print("Give Name Of Object-->>");
                mobj.setName(br.readLine());
                mobj.setIpAddress(param);
                System.out.println("Give the number of properties --->");
                int propnum=Integer.parseInt(br.readLine());
                Properties objprop=new Properties();
                for(int i=0;i<propnum;i++)
                {
                    System.out.print("Give property key-->>");
                    param = br.readLine();
                    System.out.print("Give property value-->>");
                    param1 = br.readLine();
                    objprop.put(param, param1);
                    
                }
                mobj.setProperties(objprop);
                System.out.println("Add Object ----> "+topo.addObject(mobj));
                
            }
            else if (param.equalsIgnoreCase("container")) {
                ManagedObject mobj=new ManagedObject();  
                System.out.print(" Give name of OBject-->> ");
                param = br.readLine();
                mobj.setName(param);
                mobj.setIsContainer(true);
                System.out.println("Give the number of properties --->");
                int propnum=Integer.parseInt(br.readLine());
                Properties objprop=new Properties();
                for(int i=0;i<propnum;i++)
                {
                    System.out.print("Give property key-->>");
                    param = br.readLine();
                    System.out.print("Give property value-->>");
                    param1 = br.readLine();
                    objprop.put(param, param1);
                    
                }
                  
                  mobj.setProperties(objprop);
                  System.out.println("Add Object ----> "+topo.addObject(mobj));
                  
            }
            else if(param.equalsIgnoreCase("group"))
            {
                ManagedObject mobj = new ManagedObject();
                
                System.out.print(" Give name of Object-->>");
                param = br.readLine();
                mobj.setName(param);
                mobj.setIsGroup(true);
                System.out.println("Give the number of properties --->");
                int propnum=Integer.parseInt(br.readLine());
                Properties objprop=new Properties();
                for(int i=0;i<propnum;i++)
                {
                    System.out.print("Give property key-->>");
                    param = br.readLine();
                    System.out.print("Give property value-->>");
                    param1 = br.readLine();
                    objprop.put(param, param1);
                    
                }
            }
            else if(param.equalsIgnoreCase("topoobject"))
            {
                TopoObject mobj = new TopoObject();
                
                System.out.print(" Give name of Object-->>");
                param = br.readLine();
                mobj.setName(param);
                System.out.println("Give the number of properties --->");
                int propnum=Integer.parseInt(br.readLine());
                Properties objprop=new Properties();
                for(int i=0;i<propnum;i++)
                {
                    System.out.print("Give property key-->>");
                    param = br.readLine();
                    System.out.print("Give property value-->>");
                    param1 = br.readLine();
                    objprop.put(param, param1);
                    
                }
                
                mobj.setProperties(objprop);
                System.out.println("Add Object ----> "+topo.addObject(mobj));
                
            }


            else if (param.equalsIgnoreCase("ipaddress"))
            {
                IpAddress mobj = new IpAddress();
                System.out.print("Give IPAddress Of Object-->>");
                param = br.readLine();
                System.out.print("Give Name Of Object-->>");
                mobj.setName(br.readLine());
                mobj.setIpAddress(param);
                mobj.setNetmask("255.255.255.0");
                System.out.println("Give the number of properties --->");
                int propnum=Integer.parseInt(br.readLine());
                Properties objprop=new Properties();
                for(int i=0;i<propnum;i++)
                {
                    System.out.print("Give property key-->>");
                    param = br.readLine();
                    System.out.print("Give property value-->>");
                    param1 = br.readLine();
                    objprop.put(param, param1);
                    
                }
                mobj.setProperties(objprop);
                System.out.println("Add Object ----> "+topo.addObject(mobj));
                
            }
            
            else if (param.equalsIgnoreCase("snmpinterface"))
            {
                SnmpInterface mobj = new SnmpInterface();
                System.out.print("Give IPAddress Of Object-->>");
                param = br.readLine();
                System.out.print("Give Name Of Object-->>");
                mobj.setName(br.readLine());
                mobj.setIpAddress(param);
                mobj.setNetmask("255.255.255.0");
                System.out.println("Give the number of properties --->");
                int propnum=Integer.parseInt(br.readLine());
                Properties objprop=new Properties();
                for(int i=0;i<propnum;i++)
                {
                    System.out.print("Give property key-->>");
                    param = br.readLine();
                    System.out.print("Give property value-->>");
                    param1 = br.readLine();
                    objprop.put(param, param1);
                    
                }
                mobj.setProperties(objprop);
                System.out.println("Add Object ----> "+topo.addObject(mobj));
                
            }
            else {
                ManagedObject mobj = new ManagedObject();
                System.out.print("Give name Of Object-->>");
                param = br.readLine();
                mobj.setName(param);
                System.out.println("Give the number of properties --->");
                int propnum=Integer.parseInt(br.readLine());
                Properties objprop=new Properties();
                for(int i=0;i<propnum;i++)
                {
                    System.out.print("Give property key-->>");
                    param = br.readLine();
                    System.out.print("Give property value-->>");
                    param1 = br.readLine();
                    objprop.put(param, param1);
                    
                }
                
                mobj.setProperties(objprop);
            System.out.println("Add Object ----> "+topo.addObject(mobj));
            
            }
            


            
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

     public void addObjectWithProp(TopoAPI topo) {
         try {
             BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             String param = null;
             String param1 = null;
             System.out.println("ADD-OBJECT");
             System.out.print("give choice-->>");
             param = br.readLine();
             if (param.equalsIgnoreCase("network")){
                 Network mobj = new Network();
		/*mobj.setDiscover(true);
                  mobj.setManaged(true);
                  mobj.setNetmask("255.255.255.0");*/
                 System.out.print("Give Owner Name of object-->>");
                 String name = br.readLine();
                 mobj.setParentKey(name);
                 /*                  System.out.print("Give IPAddress Of Object-->>");
                  param = br.readLine();
                  mobj.setIpAddress(param);
                  System.out.print("Give number of properties-->>");
                  int num = Integer.parseInt(br.readLine());
                  Properties prop = new Properties();
                  for (int i=0; i<num; i++) {
                  System.out.print("Give property key-->>");
                  param = br.readLine();
                  System.out.print("Give property value-->>");
                  param1 = br.readLine();
                  prop.put(param, param1);
                  }
                  mobj.setProperties(prop);*/
                 System.out.println("ADD OBJECT  : " + topo.addObject(mobj));
             }else if (param.equalsIgnoreCase("node")) {
                 Node mobj = new Node();
		/*System.out.print("Give IPAddress Of Object-->>");
                  param = br.readLine();
                  System.out.print("Give Name Of Object-->>");
                  mobj.setName(br.readLine());
                  mobj.setIpAddress(param);*/
                 System.out.print("Give number of properties-->>");
                 int num = Integer.parseInt(br.readLine());
                 Properties prop = new Properties();
                 for (int i=0; i<num; i++) {
                     System.out.print("Give property key-->>");
                     param = br.readLine();
                     System.out.print("Give property value-->>");
                     param1 = br.readLine();
                     prop.put(param, param1);
                 }
                 mobj.setProperties(prop);
                 System.out.println("ADD OBJECT  : " + topo.addObject(mobj));
             }else {
                 ManagedObject mobj = new ManagedObject();
                 System.out.print("Give name Of Object-->>");
                 param = br.readLine();
                 mobj.setName(param);
                 System.out.print("Give number of properties-->>");
                 //                 int num = Integer.parseInt(br.readLine());
                 /*Properties prop = new Properties();
                   for (int i=0; i<num; i++) {
                   System.out.print("Give property key-->>");
                   param = br.readLine();
                   System.out.print("Give property value-->>");
                   param1 = br.readLine();
                   prop.put(param, param1);
                   }
                   mobj.setProperties(prop);*/
                 System.out.print("Give Owner Name of object-->>");
                 String name = br.readLine();
                 mobj.setParentKey(name);

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
                    seed = Boolean.valueOf(param).booleanValue();
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
            // n.setManaged(false);
            System.out.print("Give IpAddress of Node-->>");
            String param = br.readLine();
            if (param.length() <= 0) {
                System.out.println("No Name Given");
            }else{
                n.setIpAddress(param);
                System.out.print("Give number of properties-->>");
                int num = Integer.parseInt(br.readLine());
                Properties prop = new Properties();
                for (int i=0; i<num; i++) {
                    System.out.print(" Give property key-->>");
                    String key = br.readLine();
                    System.out.print(" Give property value-->>");
                    prop.put(key, br.readLine());
                }
                n.setProperties(prop);
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
                seed = Boolean.valueOf(br.readLine()).booleanValue();
                System.out.print("Give value for reachFlag(say \"true/false\")-->>");
                reach = Boolean.valueOf(br.readLine()).booleanValue();
                System.out.println("addnode " + topo.addNode(n,seed,reach));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addNodeWithFour(TopoAPI topo) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print(" Give seed flag value -->>");
            String param = br.readLine();
            boolean seed = Boolean.valueOf(param).booleanValue();
            System.out.print(" Give reachflag value -->>");
            param = br.readLine();
            boolean reach = Boolean.valueOf(param).booleanValue();
            System.out.print(" Give discoverFalg value -->>");
            param = br.readLine();
            boolean discover = Boolean.valueOf(param).booleanValue();
            System.out.print(" Give IpAddress -->>");
            Node n = new Node();
            n.setIpAddress(br.readLine());
            //n.setManaged(false);
            //n.setNetmask("255.255.33");
            //n.setIsDHCP(true);

            //  n.setPollInterval(666);
            System.out.println(" Add NOde " + topo.addNode(n, seed, reach, discover));
        }catch(Exception e) {
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
    public void changePollInterval(TopoAPI topo) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String param = null;
            System.out.println("CHANGE-POLL-INTERVAL");
            System.out.print("Give Net IpAddress-->> ");
            String net = br.readLine();
            int chr;
            System.out.print("Give Value for poll interval-->>");
            param = br.readLine();
            chr = Integer.parseInt(param);
            System.out.println(chr);
                        System.out.println(topo.changePollInterval(net, chr*60));
        }catch (Exception e) {
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
    public void deleteObject(TopoAPI topo) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String param = null;
            System.out.println("DELETE-OBJECT");
            System.out.print("Give Name Of Object-->>");
            param = br.readLine();
           ManagedObject mobj=topo.getByName(param);
           //   CMCCardObject mobj =(CMCCardObject) topo.getByName(param);
            System.out.println("delete " + topo.deleteObject(mobj));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

 public void deleteObjects(TopoAPI topo) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String param = null;
            System.out.println("DELETE-OBJECT");
            System.out.print("Give classname -->>");
            String classname = br.readLine();
            if(classname.equals("null"))
            {
                classname=null;
            }
            System.out.println("classname given is "+classname);
            Properties prop = new Properties();
            System.out.print("Give number of properties-->>");
            int n = Integer.parseInt(br.readLine());
            for (int i=0; i<n; i++) {
                System.out.print("Give property key-->>");
                param = br.readLine();
                System.out.print("Give property value-->>");
                String param1 = br.readLine();
                prop.put(param,param1);
            }
            topo.deleteObjects(classname,prop);
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public void deleteObjectWithTwo(TopoAPI topo) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String param = null;
            System.out.println("DELETE-OBJECT");
            boolean sub;
            System.out.print("Give Name Of Object-->>");
            param = br.readLine();
            ManagedObject mobj = topo.getByName(param);
            System.out.print("Want to delete sub-elements \"say(true/false)\"-->>");
            param = br.readLine();
            if (param.length() > 0) {
                sub = Boolean.valueOf(param).booleanValue();
                System.out.println("delete " + topo.deleteObject(mobj, sub));
            }else {
                System.out.println("No Choice Given");
            }
        }catch(Exception e) {
            System.out.println("Exception  " + e);
        }
    }

    public void deleteObjectAndSubElements(TopoAPI topo)
    {
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String param = null;
            System.out.println("DELETE-OBJECT");
            boolean sub;
            System.out.print("Give Name Of Object-->>");
            param = br.readLine();
            
            System.out.println("delete " + topo.deleteObjectAndSubElements(param));
        }
        
    catch(Exception e) {
        System.out.println("Exception  " + e);
    }
    }

 public void deleteObjectWithThree(TopoAPI topo) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String param = null;
            System.out.println("DELETE-OBJECT");
            boolean sub=true;
            boolean sub1;
            System.out.print("Give Name Of Object-->>");
            param = br.readLine();
            ManagedObject mobj = topo.checkOut(param,2);
            System.out.print("Want to delete sub-elements \"say(true/false)\"-->>");
            param = br.readLine();
            if (param.length()== 0)
            {
                System.out.println("No Choice Given");
            }
            else
            {
            sub = Boolean.valueOf(param).booleanValue();    
            }
            System.out.println("Want to deal with locks \"say (true/false)\"---> ");
            param=br.readLine();
            if (param.length()== 0)
            {
                    System.out.println("No Choice Given");
            }
            else
            {
            sub1 = Boolean.valueOf(param).booleanValue();
            System.out.println("Delete "+topo.deleteObject(mobj,sub,sub1));
            }
        }catch(Exception e) {
            System.out.println("Exception  " + e);
        }
    }

 public void deleteObjectcsd(TopoAPI topo) 
    {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String param = null;
            System.out.println("DELETE-OBJECT");
            
            System.out.print("Give Name Of Object-->>");
            param = br.readLine();
            ManagedObject mobj = topo.checkOut(param,2);
            System.out.print("Give level of relationship-->>");
            param = br.readLine();
            Integer lev = new Integer(param);
            int level=lev.intValue();
            topo.deleteObjectAndSubElements(mobj.getName());
    }
    catch(Exception e) 
    {
        System.out.println("Exception  " + e);
    }
}




    public void getByName(TopoAPI topo) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String param = null;
            String param1 = null;
            System.out.println("GET-BY-NAME");
            System.out.print("Give Name Of Object-->>");
            param = br.readLine();
            ManagedObject mobj = topo.getByName(param.trim());
            if (mobj == null) {
                System.out.println("Given Name \"" + param + "\" not exist in DataBase");
                return;
            }
            Properties p = mobj.getProperties();
            for (Enumeration enumm = p.keys(); enumm.hasMoreElements();) {
                String key = (String)enumm.nextElement();
                System.out.println(key + "  value ---------     " + p.getProperty(key));
            }
        }catch(Exception e) {
            System.out.println("Exception  " + e);
        }
    }
    public void getCompleteList(TopoAPI topo) {
        try {
            System.out.println("GET-COMPLETE-LIST");
            Vector vect = topo.getCompleteList();
            Enumeration enumm = vect.elements();
            while(enumm.hasMoreElements()){
                System.out.println(enumm.nextElement());
            }
            System.out.println(vect.size());
        }catch(Exception e) {
            System.out.println("Exception  " + e);
        }
    }
    public void getDiscoveryParameters(TopoAPI topo){
        try {
            System.out.println("GET-DISCOVERY-PARAMETERS");
            Properties prop = topo.getDiscoveryParameters();
            Set s = prop.keySet();
            Iterator itr = s.iterator();
            while(itr.hasNext()){
                String str = (String)itr.next();
                System.out.println("Property of : " + str + " is : " + prop.getProperty(str));
            }
        }catch(Exception e) {
            System.out.println("Exception  " + e);
        }
    }
    public void getInterface(TopoAPI topo) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String param = null;
            System.out.println("GET--INTERFACE");
            System.out.print("Give IPAddress of Interface-->>");					
            param = br.readLine();
            try {
                IpAddress ip = topo.getInterface(param);
                System.out.println(ip);
                System.out.println("Parent--Node : " + ip.getParentNode());
                System.out.println("Parent--Net  : " + ip.getParentNet());
            }catch(NullPointerException e) {
                System.out.println("Given IPAddress \"" + param + "\" not exist in DataBase");
            }
        }catch(Exception e) {
            System.out.println("Exception  " + e);
        }
    }
    public void getInterfaceObjectKey(TopoAPI topo) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String param = null;
            System.out.println("GET-INTERFACE-OBJECT-KEY");
            System.out.print("Give IPAddress Of Interface-->>");					
            param = br.readLine();
            if(topo.getInterfaceObjectKey(param) == null) {
                System.out.println("Given IPAddress \"" + param + "\" not exist in DataBase");
            }else {
                System.out.println("INTERFACE OBJECT KEY : " + topo.getInterfaceObjectKey(param));
            }
        }catch(Exception e) {
            System.out.println("Exception  " + e);
        }
    }
    public void getInterfaces(TopoAPI topo) {
        try {
            System.out.println("GET--INTERFACES");
            Vector vect = topo.getInterfaces();
            System.out.println("Interfaces : " + vect.size());
            Enumeration enumm = vect.elements();
            while(enumm.hasMoreElements()) {
                System.out.println(enumm.nextElement());
            }
        }catch(Exception e) {
            System.out.println("Exception  " + e);
        }
    }
    public void getInterfacesOfNetwork(TopoAPI topo) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String param = null;
            System.out.println("GET-INTERFACES-OF-NETWORK");
            System.out.print("GiveNetworkIPAddress-->>");					
            param = br.readLine();
            try {
                Vector vect = topo.getInterfacesOfNetwork(param);
                System.out.println(vect.size());
                if (vect.size() == 0) {
                    System.out.println("No Interfaces of network \"" + param + "\" exist");
                }else {
                    Enumeration enumm = vect.elements();
                    while(enumm.hasMoreElements()) {
                        System.out.println(enumm.nextElement());
                    }
                }
            }catch (NullPointerException e) {
                System.out.println("No Interfaces of Network \"" + param + "\"  exist in DataBase");
            }
        }catch(Exception e) {
            System.out.println("Exception  " + e);
        }
    }
    public void setStatusPollEnabled(TopoAPI topo)
    {
         try {
             boolean sub;
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String param = null;
            System.out.print("GiveNameOfNode-->>");					
            param = br.readLine();
            ManagedObject obj=topo.getByName(param);
            System.out.println("Want to enable status poll \" say (true/false)\"---->");
            param = br.readLine();
            if (param.length() > 0) 
            {
                sub = Boolean.valueOf(param).booleanValue();
                obj.setStatusPollEnabled(sub);
                System.out.println("Object updated"+topo.updateObject(obj));
            }
            else {
                System.out.println("No Choice Given");
            }
            }
         catch(Exception e)
         {
             System.out.println("Exception "+e);
         }
    }


  
    public void getInterfacesOfNode(TopoAPI topo) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String param = null;
            System.out.println("GET-INTERFACES-OF-NODE");
            System.out.print("GiveNameOfNode-->>");					
            param = br.readLine();
            Vector vect = topo.getInterfacesOfNode(param);
            if (vect.size() > 0){
                System.out.println(vect.size());
                Enumeration enumm = vect.elements();
                while(enumm.hasMoreElements()) {
                    System.out.println(enumm.nextElement());
                }
            }else {
                System.out.println("Given Name \"" + param + "\" not exist in DataBase");
            }
        }catch(NullPointerException e) {
            System.out.println("Given Name not exist in DataBase");
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    public void getNode(TopoAPI topo) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String param = null;
            System.out.println("GET-NODE");
            System.out.print("GiveNameOfNode-->>");					
            param = br.readLine();
            Node n = topo.getNode(param);
            if (n != null) {
                System.out.println("node " + n);
                Vector vect = n.getParentNets();
                System.out.println("Parent nets" + vect);
		//if (vect.size() > 0) {
		//Enumeration enumm = vect.elements();
		/*while (enumm.hasMoreElements()) {
                  System.out.println(enumm.nextElement());
                  }*/
                System.out.println("IP Address : " + n.getIpAddress());
		//}else {
                //System.out.println("Vect size is zero");
		//}
            }else {
                System.out.println("Node \" " + param + " \"not exist in database");
            }
        }catch(Exception e) {
            System.out.println("Exception  " + e);
        }
    }
    public void getNodeProperties(TopoAPI topo) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("GET-NODE-PROPERTIES");
            System.out.print("GiveNameOfNode-->>");					
            String param = br.readLine();
            Properties prop = topo.getNodeProperties(param);
            if(prop != null) {
                Set s = prop.keySet();
                Iterator itr = s.iterator();
                while(itr.hasNext()){
                    String str = (String)itr.next();
                    System.out.println("Property of : " + str + " is : " +
                                       prop.getProperty(str));
                }
            }else {
                System.out.println("Given Name \"" + param + "\" not exist in DataBase");
            }
        }catch(Exception e) {
            System.out.println("Exception  " + e);
        }
    }
    public void getNodesOfNetwork(TopoAPI topo) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("GET-NODES-OF-NETWORK");
            System.out.print("GiveNetworkIPAddress-->>");					
            String param = br.readLine();
            Vector vect = topo.getNodesOfNetwork(param);
            if (vect.size() == 0) {
                System.out.println("No Nodes of network \"" + param + "\" exist");
            }else {
                Enumeration enumm = vect.elements();
                while(enumm.hasMoreElements()) {
                    System.out.println(enumm.nextElement());
                }
            }
        }catch(NullPointerException e) {
            System.out.println("Given Network not exist in DataBase");
        }catch(Exception e) {
            System.out.println("Exception  " + e);
        }
    }
    public void getNodes(TopoAPI topo) {
        try {
            System.out.println("GET--NODES");
            Vector vect = topo.getNodes();
            if (vect.size() == 0) {
                System.out.println("No Nodes exist");
            }
            System.out.println("vect size : " + vect.size());
            Enumeration enumm = vect.elements();
            while (enumm.hasMoreElements()){
                System.out.println(enumm.nextElement());
            }
        }catch(Exception e) {
            System.out.println("Exception  " + e);
        }
    }
    public void getNet(TopoAPI topo) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("GET-NET");			
            System.out.print("GiveNetworkAddress-->>");
            String param = br.readLine();
            Network net = topo.getNet(param);
            if(net != null){
                Vector vect = net.getIpaddrs();
                Enumeration enumm = vect.elements();
                System.out.println("The IpAddress of Interfaces : ");
                while(enumm.hasMoreElements()) {
                    System.out.println(enumm.nextElement());
                }
                System.out.println();
                System.out.println();
                vect = net.getNodes();
                enumm = vect.elements();
                System.out.println("The Names of Nodes : ");
                while(enumm.hasMoreElements()) {
                    System.out.println(enumm.nextElement());
                }
                System.out.println();
                System.out.println("The Network : " + net + " is Discovered : " + net.getDiscover());
                System.out.println();
                vect =	net.getSubNets();
                enumm = vect.elements();
                while(enumm.hasMoreElements()) {
                    System.out.println(enumm.nextElement());
                }
                System.out.println();
                System.out.println();
                vect = net.getSubNetMasks();
                enumm = vect.elements();
                while (enumm.hasMoreElements()) {
                    System.out.println(enumm.nextElement());
                } 
            }else {
                System.out.println("Given Network \"" + param + "\" not exist in DataBase");
            }
        }catch(Exception e) {
            System.out.println("Exception  " + e);
        }
    }
    public void getNetworks(TopoAPI topo) {
        try {
            System.out.println("GET-NETWORKS");
            Vector vect = topo.getNetworks();
            Enumeration enumm = vect.elements();
            while(enumm.hasMoreElements()) {
                System.out.println(enumm.nextElement());
            }
        }catch(Exception e) {
            System.out.println("Exception  " + e);
        }
    }
    public void getObjectProp(TopoAPI topo) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("GET-OBJECT-PROPERTIES");
            System.out.print("Give classname -->>");
            String param2 = br.readLine();
            Properties prop = new Properties();
            System.out.print("Give property key-->>");
            String param = br.readLine();
            System.out.print("Give Property value-->>");
            String param1 = br.readLine();
            prop.put(param,param1);
            Vector vect = topo.getObjects(param2, prop);
            Enumeration enumm = vect.elements();
            while(enumm.hasMoreElements()) {
		//System.out.println("get-object : " + enumm.nextElement());
                prop = ((ManagedObject)enumm.nextElement()).getProperties();
                Set s = prop.keySet();
                Iterator it = s.iterator();
                while (it.hasNext()) {
                    String str = (String)it.next();
                    System.out.println(" Property of : " + str + " is : " + prop.getProperty(str));
                }
                System.out.print("Press 'enter' to view next object property");
                String param24 = br.readLine();
                System.out.println();
                System.out.println();
            }
        }catch(Exception e) {
            System.out.println("Exception " + e);
        }
    }
    public void isManagedObjectPresent(TopoAPI topo)
    {
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Give the Key of the object --->");
            String param=br.readLine();
            System.out.println("isManagedObjectPresent  "+topo.isManagedObjectPresent(param));
        }   
            catch(Exception e)
            {
                System.out.println("Exception "+e);
            }
    }
    public void getMOClassHierarchy(TopoAPI topo)
    {
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Give the Key of the object --->");
            String param=br.readLine();
            String[] sr = topo.getMOClassHierarchy(param);

            System.out.println("The Class Hierarchy is "+topo.getMOClassHierarchy(param));
        }
        catch(Exception e)
        {
            System.out.println("Exception  "+e);    
        }
    }
    public void clearLockForObject(TopoAPI topo)
    {
        try
        {

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Give the name of the object --->");
            String param=br.readLine();
            System.out.println("Give the lock type of the object -->");
            int n = Integer.parseInt(br.readLine());
            topo.clearLockForObject(param,n);
            System.out.println("The lock type "+n+" for the object "+param+" is cleared");
        }
        catch(Exception e)
        {
            System.out.println(" Exception "+e);
        }
    }

 public void setDisTopoLoggingMode(TopoAPI topo)
    {
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Want to set topo logging level (say true/false) --->");
            String param=br.readLine();
            boolean log=Boolean.valueOf(param).booleanValue();
            topo.setDisTopoLoggingMode(log);
            System.out.println("setDisTopoLoggingMode   "+log);
        }   
            catch(Exception e)
            {
                System.out.println("Exception "+e);
            }
    }
    public void getObjects(TopoAPI topo) {
        System.out.println("Raghav");
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("GET-OBJECTS");
            System.out.print("Give classname -->>");
            String param2 = br.readLine();
            Properties prop = new Properties();
            System.out.print("Give property key-->>");
            String param = br.readLine();
            System.out.print("Give Property value-->>");
            String param1 = br.readLine();
            prop.put(param,param1);
            Vector vect = topo.getObjects(param2, prop);
            if (vect != null) {
                for (int i=0; i<vect.size(); i++) {
                    System.out.println(vect.elementAt(i));
                }
            }
        }catch (Exception e) {
            System.out.println("Exception  " + e);
        }
    }
    public void getObjectNamesWithProps(TopoAPI topo) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("GET-OBJECT-NAME-WITH-PROPS");
            Properties prop = new Properties();
            System.out.print("Give number of properties-->>");
            int n = Integer.parseInt(br.readLine());
            for (int i=0; i<n; i++) {
                System.out.print("Give property key-->>");
                String param = br.readLine();
                System.out.print("Give property value-->>");
                String param1 = br.readLine();
                prop.put(param,param1);
            }
            Vector vect = topo.getObjectNamesWithProps(prop);
            Enumeration enumm = vect.elements();
            while(enumm.hasMoreElements()) {
                System.out.println(enumm.nextElement());
            }
            System.out.println(vect.size());
        }catch(Exception e) {
            System.out.println("Exception  " + e);
        }
    }
  public void getObjectNamesWithPropsWithOR(TopoAPI topo) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("GET-OBJECT-NAME-WITH-PROPS");
            Properties prop = new Properties();
            System.out.print("Give number of properties-->>");
            int n = Integer.parseInt(br.readLine());
            for (int i=0; i<n; i++) {
                System.out.print("Give property key-->>");
                String param = br.readLine();
                System.out.print("Give property value-->>");
                String param1 = br.readLine();
                prop.put(param,param1);
            }
            System.out.println("Want to perform OR operation (true/false) --->");
            boolean or = Boolean.valueOf(br.readLine()).booleanValue();
            Vector vect = topo.getObjectNamesWithProps(prop,or);
            Enumeration enumm = vect.elements();
            while(enumm.hasMoreElements()) {
                System.out.println(enumm.nextElement());
            }
            System.out.println(vect.size());
        }catch(Exception e) {
            System.out.println("Exception  " + e);
        }
    }
    public void getPropertiesOfObject(TopoAPI topo) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("GET-PROPERTIES-OF-OBJECT");
            System.out.print("Give Name Of Object-->>");					
            String param = br.readLine();
            Properties prop = topo.getPropertiesOfObject(param);
            if(prop != null) {
                Set s = prop.keySet();
                Iterator itr = s.iterator();
                while(itr.hasNext()){
                    String str = (String)itr.next();
                    System.out.println(" Property of : " + str + " is : " +
                                       prop.getProperty(str));
                }
            }else {
                System.out.println("Given Name \"" + param + "\" not exist in DataBase");
            }
        }catch(Exception e) {
            System.out.println("Exception  " + e);
        }
    }
    public void refreshObject(TopoAPI topo) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("REFRESH-OBJECT");
            System.out.print("GiveNameOfNode-->>");					
            String param = br.readLine();
            try {
                ManagedObject mobj = topo.getByName(param);
                System.out.println("refresh object " + topo.refreshObject(mobj));			
            }catch(ClassCastException cc) {
                System.out.println("Object is not of node type");
            }
        }catch(Exception e) {
            System.out.println("Exception  " + e);
        }
    }
    public void removeNodeFromTopoDB(TopoAPI topo) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("REMOVE-NODE-FROM-TOPODB");
            System.out.print("GiveNodeIP-->>");					
            String param = br.readLine();
            System.out.println("remove node : " + topo.removeNodeFromTopoDB(param));
        }catch(Exception e) {
            System.out.println("Exception  " + e);
        }
    }
    public void removeNodesFromTopoDB(TopoAPI topo) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("REMOVE-NODE-FROM-TOPODB");
            int chr;
            System.out.print("Give Number Of Nodes-->>");
            String param = br.readLine();
            chr = Integer.parseInt(param);
            System.out.println(chr);
            String[] s24 = new String[chr];
            for(int i=0; i<chr; i++) {
                BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Give Ip Address " + (i + 1) + "th Node-->>");					
                s24[i] = br2.readLine();
            }
            System.out.println(topo.removeNodesFromTopoDB(s24));
        }catch(Exception e){
            System.out.println("Exception  " + e);
        }
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
    public void setDiscoveryParametersold(TopoAPI topo) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("SET-DISCOVERY-PARAMETERS");
            SeedParser parser = new SeedParser("d:/sync/webnms/conf/seed1.file");
            System.out.print(" give node name-->>");
            String name = br.readLine();
            System.out.print(" give subnodename -- >>");
            String subName = br.readLine();
            Properties p = new Properties();
            if ((name.equals("DISCOVERY")) || (name.equals("NATIVE_PING"))) {
                Properties myProp = parser.getPropertiesOfNode(name);
                System.out.println(" myProp"+ myProp);
                XMLNode setNode = parser.getXMLOfNode(name, "", myProp);
                // System.out.println(" setting proper " + topo.setDiscoveryParameters(setNode));
                return;
            }
            p = parser.getPropertiesOfNode(name);
            if ((p != null) && (p.size() > 0)) {
                Properties pv = new Properties();
                pv.put(name, p);
                System.out.println(" set prop" + pv);
                System.out.println(" setting prop "+topo.setDiscoveryParameters(pv));
                return;
            }
            p = parser.getPropertiesOfNode(name, subName, p);
            System.out.println(" get properies of " + name + " is " + p);
            for (Enumeration enumm = p.keys(); enumm.hasMoreElements();) {
                String key = (String)enumm.nextElement();
                System.out.println(" key " + key);
                Vector child = (Vector)p.get(key);
                System.out.println(" child value " + child);
                if (child == null) {
                    continue;
                }
                for (int i=0; i<child.size(); i++) {
                    Properties propy = (Properties)child.elementAt(i);
                    System.out.println(" propy value " + propy);
                    XMLNode childNode = parser.getXMLOfNode(name, subName, propy);
                    System.out.println(" childNOde value " + childNode.getNodeName());
                    // System.out.println(" set props using XML " + topo.setDiscoveryParameters(childNode));
                }
            }
        }catch(Exception e) {
            System.out.println("Exception  " + e);
        }
    }
    public void setDiscoveryParameters(TopoAPI topo) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("SET-DISCOVERY-PARAMETERS");
            Properties p = new Properties();
            System.out.print("Give property key-->>");
            String param = br.readLine();
            System.out.print("Give property value-->>");
            String param1 = br.readLine();
            p.put(param, param1);
            System.out.println(" setDiscoveryParameters " + topo.setDiscoveryParameters(p));
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
            setmanage = Boolean.valueOf(br.readLine()).booleanValue();
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
            setmanage = Boolean.valueOf(br.readLine()).booleanValue();
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
            Network n =(Network)topo.checkOut(param,4);
            System.out.print("Want to manage Network(say true/false)-->>");					
            setmanage = Boolean.valueOf(br.readLine()).booleanValue();			
            topo.setManagedAll(n,setmanage);
            topo.unlock(n);
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
            /*RmiExample rmiup = new RmiExample();
              rmiup.getByName(topo);*/
            ManagedObject mobj = topo.getByName(param);
            if(mobj != null) {
                System.out.print("Give the property key you want to change-->>");
                param = br.readLine();
                System.out.print(" Give property value -->>" );
                String param1 = br.readLine();
                
                Properties prop = new Properties();
                prop.put(param, param1);
                //                mobj.setDisplayName(param);
                mobj.setProperties(prop);
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

    ///////////GENERAL_PURPOSE
    public void setAutoCommit(TopoAPI topo) {
        try {
            boolean b = true;
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("SET-AUTO-COMMIT");
            System.out.print("Give value for Auto Commit (true/false)-->>");
            b = Boolean.valueOf(br.readLine()).booleanValue();
            System.out.println(topo.setAutoCommit(b));
        }catch(Exception e) {
            System.out.println("Exception " + e);
        }
    }

    public void changeTester(TopoAPI topo) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Give Object name-->> ");
            String param = br.readLine();
            ManagedObject mobj = topo.getByName(param);
            System.out.println("tester of the object " + param + " is ---- " + mobj.getTester());
            System.out.print("Give your tester value-->> ");
            param = br.readLine();
            mobj.setTester(param);
            System.out.println("updating tester value " + topo.updateObject(mobj));
            System.out.println("tester of the object after update  " + param + " is ---- " + mobj.getTester());
        }catch(Exception e) {
            System.out.println("Exception  " + e);
        }
    }

    public void shutdown() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            Runtime r = Runtime.getRuntime();
            System.out.print("Give host name-->>");
            String str = br.readLine();
            System.out.print("Give key-->>");
            String passw = br.readLine();
            System.out.print("Give password-->>");
            String key = br.readLine();
            Process p = r.exec("java com.adventnet.nms.util.StandAloneShutDown " + str + " 9090 " + key + " " + passw);
        }catch(Exception e) {
            e.printStackTrace();
        }		
    }

    public void startServer() {
        try {
            Runtime r = Runtime.getRuntime();
            //Process p = r.exec("copy d:\sync\webnms\apache\conf\backup\httpd.conf d:\sync\Webnms\apache\conf");
            Process p = r.exec("copy RmiExample.class ./../");
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void register() {
        System.out.println("REGISTER");
        try {
            //TopoObserver
            //            RegisterImpl topoObs = new RegisterImpl();
            //            UnicastRemoteObject.exportObject (topoObs);
            RmiExample rm = new RmiExample();
            UnicastRemoteObject.exportObject (rm);
            System.out.println(topo.register(rm));
            System.out.println(topo.register(rm));
            System.out.println(topo.register(rm));
            
        }catch(Exception e) {
            System.out.println("Exception while registering as TopoObserver " + e);
            System.out.println("Exception  " + e);
        }
    }
    ////////////////////////
    
    public static void main(String args[]) {
        String choice = null;
        try {
            System.out.println("jagan");
            while (true) {
                try {
                    BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
                    System.out.print("Give System you want to connect in-->>");
                    String str = buf.readLine();
                    topo = (TopoAPI)Naming.lookup("rmi://" + java.net.InetAddress.getByName(str.trim()).getHostName() + "/TopoAPI");
                    System.out.println("Handle Received");
                    RmiExample rmi = new RmiExample();
                    while(true) {
                        System.out.println();
                        System.out.println();
                        System.out.print("Give method name you want to execute-->>");
                        choice = buf.readLine();
                        /*if (choice.equalsIgnoreCase("cls")){
                          Runtime r = Runtime.getRuntime();
                          Process p = r.exec("cls");
                          continue;
                          }*/
                        if (choice.equalsIgnoreCase("shutdown")) {
                            rmi.shutdown();
                            System.out.println("Successfully shutdown the server");
                            continue;
                        }
                        if (choice.equalsIgnoreCase("start")) {
                            rmi.startServer();
                        }
                        if (choice.equalsIgnoreCase("changeuser") || choice.equalsIgnoreCase("cu")) {
                            break;
                        }
                        if(choice.equalsIgnoreCase("addObject")) {
                            rmi.addObject(topo);
                        }else if (choice.equalsIgnoreCase("doStatusPoll")) {
                            //  rmi.doStatusPoll(topo);			        
                        }
                        else if (choice.equalsIgnoreCase("setStatusPollenabled")) {
                              rmi.setStatusPollEnabled(topo);			        
                        }
                        else if (choice.equalsIgnoreCase("addObjectWithProps")) {
                            rmi.addObjectWithProp(topo);
                        }else if (choice.equalsIgnoreCase("addObjectWithGroup")) {
                            rmi.addObjectWithGroupProperties(topo);
                        }else if(choice.equalsIgnoreCase("addobjloop")) {
                            rmi.addObjectLoop(topo);
                        }else if(choice.equalsIgnoreCase("changeTester")) {
                            rmi.changeTester(topo);
                        }else if(choice.equalsIgnoreCase("addObjectWithTwo")) {
                            rmi.addObjectWithTwo(topo);
                        }else if(choice.equalsIgnoreCase("addNode")) {					
                            rmi.addNode(topo);
                        }else if(choice.equalsIgnoreCase("addNodeWithThree")) {
                            rmi.addNodeWithThree(topo);
                        }else if (choice.equalsIgnoreCase("addNodeWithFour")) {
                            rmi.addNodeWithFour(topo);
                        }else if(choice.equalsIgnoreCase("addNodetotopodb")) {
                            rmi.addNodeToTopoDB(topo);
                        }else if(choice.equalsIgnoreCase("addNodeToTopoDBWithFour")){
                            rmi.addNodeToTopoDBWithFour(topo);
                        }else if(choice.equalsIgnoreCase("addNodesToTopoDBWithStringArray")) {
                            rmi.addNodesToTopoDBWithStringArray(topo);
                        }else if(choice.equalsIgnoreCase("addNodesToTopoDBwithstartendIP")) {
                            rmi.addNodesToTopoDBWithStartEndIP(topo);
                        }else if(choice.equalsIgnoreCase("addNodeLoop")) {
                            rmi.addNodeLoop(topo);
                        }else if(choice.equalsIgnoreCase("addnetloop")){
                            rmi.addNetLoop(topo);
                        }else if(choice.equalsIgnoreCase("deleteLoop")) {
                            rmi.deleteObjectLoop(topo);
                        }else if(choice.equalsIgnoreCase("updateStatusLoop")) {
                            rmi.updateStatusLoop(topo);
                        }else if(choice.equalsIgnoreCase("updateObjectLoop")) {
                            rmi.updateObjectLoop(topo);
                        }else if(choice.equalsIgnoreCase("refreshObjectLoop")) {
                            rmi.refreshObjectLoop(topo);
                            /*}else if(choice.equalsIgnoreCase("SetGroup")){
                              BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
                              Group ob = new Group();
                              ob.setTopo(topo);
                              System.out.print("Give name of Group-->>");
                              String nameOfGroup = buff.readLine();
                              ob.regist(nameOfGroup);*/
                        }else if(choice.equalsIgnoreCase("getGroupNames")) {
                            rmi.getGroupNames(topo);
                        }else if(choice.equalsIgnoreCase("getGroupNamesOfMO")) {
                            rmi.getGroupNamesOfMO(topo);
                        }else if(choice.equalsIgnoreCase("getMembersOfGroup")) {
                            rmi.getMembersOfGroup(topo);
                        }else if(choice.equalsIgnoreCase("removeMOFromGroup")) {
                            rmi.removeMOFromGroup(topo);
                        }else if(choice.equalsIgnoreCase("setAutoCommit")) {
                            rmi.setAutoCommit(topo);
                        }else if(choice.equalsIgnoreCase("setGroupsForMO")){
                            rmi.setGroupsForMO(topo);
                            /*}else if(choice.equalsIgnoreCase("setContainer")) {
                              BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
                              Container ob = new Container();
                              ob.setTopo(topo);
                              System.out.print("Give name of Container-->>");
                              String nameOfContainer = buff.readLine();
                              ob.regist(nameOfContainer);*/
                        }else if(choice.equalsIgnoreCase("getparentkey")) {
                            rmi.getParentKey(topo);
                        }else if(choice.equalsIgnoreCase("setparentkey")) {
                            rmi.setParentKey(topo);
                        }else if(choice.equalsIgnoreCase("getChildrenKeys")) {
                            rmi.getChildrenKeys(topo);
                        }else if(choice.equalsIgnoreCase("setChildrenKeys")) {
                            rmi.setChildrenKeys(topo);
                        }else if(choice.equalsIgnoreCase("addNetwork")) {
                            rmi.addNetwork(topo);
                        }else if (choice.equalsIgnoreCase("changepollinterval")) {
                            rmi.changePollInterval(topo);
                        }else if(choice.equalsIgnoreCase("changediscinterval")) {
                            rmi.changeDiscInterval(topo);
                        }else if(choice.equalsIgnoreCase("changepingretries")) {
                            rmi.changePingRetries(topo);
                        }else if(choice.equalsIgnoreCase("changesnmpretries")) {
                            rmi.changeSnmpRetries(topo);
                        }else if(choice.equalsIgnoreCase("deleteobject")) {
                            rmi.deleteObject(topo);
                        }else if(choice.equalsIgnoreCase("deleteObjectwithtwo")) {		
                            rmi.deleteObjectWithTwo(topo);
                           } else if(choice.equalsIgnoreCase("deleteObjectwiththree")) {	
                            rmi.deleteObjectWithThree(topo);
                        }else if(choice.equalsIgnoreCase("getByName")) {
                            rmi.getByName(topo);
                        }else if(choice.equalsIgnoreCase("getCompleteList") || choice.equalsIgnoreCase("1")) {
                            rmi.getCompleteList(topo);
                        }else if(choice.equalsIgnoreCase("getDiscoveryParameters")) {		
                            rmi.getDiscoveryParameters(topo);
                        }else if(choice.equalsIgnoreCase("getInterface")) {
                            rmi.getInterface(topo);
                        }else if(choice.equalsIgnoreCase("getInterfaceObjectKey")) {
                            rmi.getInterfaceObjectKey(topo);
                        }else if(choice.equalsIgnoreCase("getInterfaces")) {
                            rmi.getInterfaces(topo);
                        }else if(choice.equalsIgnoreCase("getInterfacesOfNetwork")) {		
                            rmi.getInterfacesOfNetwork(topo);
                        }else if(choice.equalsIgnoreCase("getInterfacesOfNode")) {		
                            rmi.getInterfacesOfNode(topo);
                        }else if(choice.equalsIgnoreCase("getnumnodes")) {		
                            System.out.println("NUM-NODES");
                            System.out.println(topo.getNumNodes());
                        }else if(choice.equalsIgnoreCase("getnumnetworks")) {		
                            System.out.println("NUM-NETWORKS");
                            System.out.println(topo.getNumNetworks());
                        }else if(choice.equalsIgnoreCase("getnuminterfaces")) {		
                            System.out.println("NUM-INTERFACES");
                            System.out.println(topo.getNumInterfaces());
                        }else if(choice.equalsIgnoreCase("getnumObjects")) {
                            System.out.println("NUM-OBJECTS");
                            System.out.println(topo.getNumObjects());
                        }else if(choice.equalsIgnoreCase("getNode")) {
                            rmi.getNode(topo);
                        }else if(choice.equalsIgnoreCase("getNodeProperties")) {		
                            rmi.getNodeProperties(topo);
                        }else if(choice.equalsIgnoreCase("getNodesOfNetwork")) {		
                            rmi.getNodesOfNetwork(topo);
                        }else if(choice.equalsIgnoreCase("getNodes")) {
                            rmi.getNodes(topo);
                        }else if(choice.equalsIgnoreCase("getNet")) {
                            rmi.getNet(topo);
                        }else if(choice.equalsIgnoreCase("getNetworks")) {
                            rmi.getNetworks(topo);
                        }else if(choice.equalsIgnoreCase("getObjects")) {
                            rmi.getObjects(topo);
                        }else if(choice.equalsIgnoreCase("getObjectProp")) {
                            rmi.getObjectProp(topo);
                        }else if(choice.equalsIgnoreCase("getObjectNamesWithProps")) {		
                            rmi.getObjectNamesWithProps(topo);
                        }else if(choice.equalsIgnoreCase("getObjectNamesWithPropswithor")) {   	
                            rmi.getObjectNamesWithPropsWithOR(topo);
                        }else if(choice.equalsIgnoreCase("getPropertiesOfObject")) {		
                            rmi.getPropertiesOfObject(topo);
                        }else if(choice.equalsIgnoreCase("refreshObject")) {
                            rmi.refreshObject(topo);
                        }else if(choice.equalsIgnoreCase("register")) {
                            rmi.register();
                        }else if(choice.equalsIgnoreCase("registerTopo")) {
                            System.out.println("REGISTER-TOPO-SUBSCRIBER");
                            try {
                                TopoSubscriber topoSubsc = new RmiExample();
                                UnicastRemoteObject.exportObject(topoSubsc);
                                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                                System.out.print("Give container Object name-->>");
                                String containerName = br.readLine();
                                System.out.println(topo.registerTopoSubscriber(topoSubsc, containerName));
                            }catch(Exception e) {
                                System.out.println("Exception while registering for TopoSubscriber " + e);
                            }
                        }else if(choice.equalsIgnoreCase("removeNodeFromTopoDB")) {
                            rmi.removeNodeFromTopoDB(topo);
                        }else if(choice.equalsIgnoreCase("removeNodesFromTopoDB")) {
                            rmi.removeNodesFromTopoDB(topo);
                        }else if(choice.equalsIgnoreCase("removeAddressRangetoDiscover")) {		
                            rmi.removeAddressRangeToDiscover(topo);
                        }else if(choice.equalsIgnoreCase("setAddressRangetoDiscover")) {		
                            rmi.setAddressRangeToDiscover(topo);
                        }else if(choice.equalsIgnoreCase("setDiscover")) {
                            rmi.setDiscover(topo);
                        }else if(choice.equalsIgnoreCase("stopDiscover")) {
                            rmi.stopDiscover(topo);
                        }else if(choice.equalsIgnoreCase("setDiscoveryParameters")) {
                            rmi.setDiscoveryParameters(topo);
                        }else if (choice.equalsIgnoreCase("old")) {
                            rmi.setDiscoveryParametersold(topo);
                        }else if(choice.equalsIgnoreCase("setManaged")) {
                            rmi.setManaged(topo);
                        }else if(choice.equalsIgnoreCase("setManagedAllNode")) {
                            rmi.setManagedAllNode(topo);
                        }else if(choice.equalsIgnoreCase("setManagedAllNetwork")) {
                            rmi.setManagedAllNetwork(topo);
                        }else if(choice.equalsIgnoreCase("updateObject")) {
                            rmi.updateObject(topo);
                        }else if(choice.equalsIgnoreCase("updateStatus")) {
                            rmi.updateStatus(topo);
                            ////////////////////////////
                        }else if(choice.equalsIgnoreCase("unlock")){
                            rmi.unlock(topo);
                        }else if(choice.equalsIgnoreCase("checkOut")) {
                            rmi.checkOut(topo);
                        }else if(choice.equalsIgnoreCase("checkoutifavailable")) {
                            rmi.checkOutIfAvailable(topo);
                        }else if(choice.equalsIgnoreCase("lock")) {
                            rmi.lock(topo);
                        }else if(choice.equalsIgnoreCase("ismanagedobjectpresent")) {
                            rmi.isManagedObjectPresent(topo);
                        }else if(choice.equalsIgnoreCase("setdistopologgingmode")) {
                            rmi.setDisTopoLoggingMode(topo);
                        }else if (choice.equalsIgnoreCase("getCurrentLockType")) {
                            rmi.getCurrentLockType(topo);
                        }else if(choice.equalsIgnoreCase("getmoclasshierarchy")){
                            rmi.getMOClassHierarchy(topo);
                        }else if (choice.equalsIgnoreCase("clearlockforobject")){
                            rmi.clearLockForObject(topo);
                        }
                        else if (choice.equalsIgnoreCase("checkWritePermission")) {
                            rmi.checkWritePermission(topo);
                            ///////////////////////////
                        }
                        


                        else if (choice.equalsIgnoreCase("deleteobjectcsd")) {
                            rmi.deleteObjectcsd(topo);
                        }
                        else if (choice.equalsIgnoreCase("deleteobjects")) {
                            rmi.deleteObjects(topo);
                        }

                          else if (choice.equalsIgnoreCase("deleteobjectandsubelements")) {
                            rmi.deleteObjectAndSubElements(topo);
                        }




                        else {
                            System.out.println("wrong choice");
                        }
                    }
                }catch (Exception e) {
                    System.out.println("Exception " + e);
                }
            }
        }catch(Exception e) {
            System.out.println("Exception " + e);
        }
    }
}
