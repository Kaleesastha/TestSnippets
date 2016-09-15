/* $Id: GroupTest.java,v 1.2 2003/06/19 12:31:15 priya Exp $
 *
 * File Name      : GroupTest.java
 * Description    : To do operation in Group Objects
 * Other Info     : Complie the IPService.java file under <WEB_NMS home>/examples/group-interface and place it webnms classpath.
                    Then to run the IPService execute the install.sh/install.exe under <WEB_NMS_HOME>/examples/group-interface
 *                  directory.Reinitiallise and restart the server.
 *
 * USAGE          : java GroupTest
 * Parameter Desc : Give the method name                
 *
 * Owner Name     : Priya.M
 * Change History(Author Date(dd-mm-yyy) and Description of methods added/modifed/deleted):
 */
import test.IPService;
import java.rmi.Naming;
import java.util.*;
import com.adventnet.nms.topodb.*;
import java.io.*;
public class GroupTest{
    public void instantiate(TopoAPI topo){
        try{
            System.out.print("GiveNameOf GroupObject-->>");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String param = br.readLine();            
            IPService go = new IPService(param); 
            System.out.print("Give ownerName ( if no ownerName give null)-->>");
             br = new BufferedReader(new InputStreamReader(System.in));
             param = br.readLine();
             if ( param.equals(""))
             {
                 System.out.println("The owner name is null");
                 go.setOwnerName(null);
             }
           else  go.setOwnerName(param);
            
             
            System.out.println(" GroupObject added "+topo.addObject(go));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void getProperties(TopoAPI topo){
        try{ 
            System.out.print("GiveNameOf GroupObject-->>");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String param = br.readLine();
            ManagedObject mo=topo.getByName(param);
            System.out.println(" Properties of Object: "+mo.getProperties());
        }
        catch(Exception e){
            e.printStackTrace();
        }            
    }

    public void getGroupMembers(TopoAPI topo){
        try{
            System.out.println(" GiveNameOf GroupObject-->>");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String param = br.readLine();
            ManagedObject mo=topo.getByName(param);
            String[] sarr=mo.getGroupMembers();
            for(int i=0;i<sarr.length;i++)
            {
                System.out.println(sarr[i]);
            }
                //System.out.println(" The groupMembers are: "+mo.getGroupMembers());
        }
        catch(Exception e){
            e.printStackTrace();
        }            
    }

    public void setGroupMembers(TopoAPI topo){
        try{
        System.out.println(" GiveNameOf GroupObject-->>");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String param = br.readLine();
        ManagedObject mo=topo.getByName(param);
        System.out.println(" Give the number of groupMembers-->>");
         br = new BufferedReader(new InputStreamReader(System.in));
         param = br.readLine();
         String[] gpMem = null;
         int num = Integer.parseInt(param);
         if ( num !=0 )
             gpMem = new String[num];
         for(int i=0;i<num;i++){
             System.out.println(" give the member "+(i+1));
             br = new BufferedReader(new InputStreamReader(System.in));
             String name=br.readLine();
             System.out.println(name);
             if(name.equalsIgnoreCase("null"))
             {
                   System.out.println(" kkkkkkk");
                 gpMem[i] =null;
             }
             
             else   gpMem[i]=name;
     }
        
         //System.out.println(gpMem);
         mo.setGroupMembers(gpMem);
         System.out.println(" object updated "+topo.updateObject(mo));
        }
        catch(Exception e){
            e.printStackTrace();
        }            
    }

    public void addGroupMembers(TopoAPI topo){
        try{
        System.out.println(" GiveNameOf GroupObject-->>");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String param = br.readLine();
        ManagedObject mo=topo.getByName(param);
	System.out.println("The object name is " + mo);
        System.out.println(" Give the number of groupMembers to be added-->>");
         br = new BufferedReader(new InputStreamReader(System.in));
         param = br.readLine();
         String[] gpMem = null;
         int num = Integer.parseInt(param);
         if ( num != 0)
             gpMem = new String[num];
         for(int i=0;i<num;i++){
             System.out.println(" give the member "+(i+1));
             br = new BufferedReader(new InputStreamReader(System.in));
             gpMem[i] = br.readLine();
             //gpMem[i]=null;
         }
             
         mo.addGroupMembers(gpMem);
         System.out.println(" object updated "+topo.updateObject(mo));
        }
        catch(Exception e){
            e.printStackTrace();
        }            
    }

    public void removeGroupMembers(TopoAPI topo){
        try{
        System.out.println(" GiveNameOf GroupObject-->>");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String param = br.readLine();
        ManagedObject mo=topo.getByName(param);
        System.out.println(" Give the number of groupMembers to be removed-->>");
         br = new BufferedReader(new InputStreamReader(System.in));
         param = br.readLine();
         String[] gpMem = null;
         int num = Integer.parseInt(param);
         if ( num != 0)
             gpMem = new String[num];
         for(int i=0;i<num;i++){
             System.out.println(" give the member "+(i+1));
             br = new BufferedReader(new InputStreamReader(System.in));
             gpMem[i] = br.readLine();
             //gpMem[i]=null;
         }
             
         mo.removeGroupMembers(gpMem);
         System.out.println(" object updated "+topo.updateObject(mo));
        }
        catch(Exception e){
            e.printStackTrace();
        }            
    }

    public void getGroupNames(TopoAPI topo)
    {
        try
        {
            System.out.println("Give the name of the Object--->");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String param = br.readLine();
            ManagedObject mo=topo.getByName(param);
            String[] sarr=mo.getGroupNames();
            for(int i=0;i<sarr.length;i++)
            {
                System.out.println(sarr[i]);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void setGroupNames(TopoAPI topo)
    {
        try{
            System.out.println(" GiveNameOf Object-->>");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String param = br.readLine();
            ManagedObject mo=topo.getByName(param);
            System.out.println(" Give the number of groupNames to be set-->>");
            br = new BufferedReader(new InputStreamReader(System.in));
            param = br.readLine();
            String[] gpMem = null;
            int num = Integer.parseInt(param);
            if ( num != 0)
                gpMem = new String[num];
            for(int i=0;i<num;i++){
                System.out.println(" give the groupname "+(i+1));
                br = new BufferedReader(new InputStreamReader(System.in));
                gpMem[i] = br.readLine();
                // gpMem[i]=null;
            }
            
            mo.setGroupNames(gpMem);
            System.out.println(" object updated "+topo.updateObject(mo));
        }
        catch(Exception e){
            e.printStackTrace();
        }            
        
    }

 public void addGroupNames(TopoAPI topo)
    {
        try{
            System.out.println(" GiveNameOf Object-->>");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String param = br.readLine();
            ManagedObject mo=topo.getByName(param);
	    System.out.println("The name of the object is   " + mo);
            System.out.println(" Give the number of groupNames to add-->>");
            br = new BufferedReader(new InputStreamReader(System.in));
            param = br.readLine();
            String[] gpMem = null;
            int num = Integer.parseInt(param);
            int i;
	    System.out.println("the given no of group is   " + num);
            if ( num != 0)
                gpMem = new String[num];
            for(i=0;i<num;i++){
                System.out.println(" give the groupname "+(i+1));
                br = new BufferedReader(new InputStreamReader(System.in));
                gpMem[i] = br.readLine();
		System.out.println("group name is " + gpMem[i]);
                //gpMem[i]=null;
            }
	    /*	    for (int k=0;k<i;k++)
		{
		    System.out.println("the groupnames are    " + gpMem[k]);
		    System.out.println("the managed object is  "   + mo);
		    mo.addGroupNames(gpMem);
		} 
	    */
	    mo.addGroupNames(gpMem);
            System.out.println(" object updated "+topo.updateObject(mo));
        }
        catch(Exception e){
            e.printStackTrace();
        }            
        
    }
 public void removeGroupNames(TopoAPI topo)
    {
        try{
            System.out.println(" GiveNameOf Object-->>");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String param = br.readLine();
            ManagedObject mo=topo.getByName(param);
            System.out.println(" Give the number of groupNames to remove-->>");
            br = new BufferedReader(new InputStreamReader(System.in));
            param = br.readLine();
            String[] gpMem = null;
            int num = Integer.parseInt(param);
            if ( num != 0)
                gpMem = new String[num];
            for(int i=0;i<num;i++){
                System.out.println(" give the groupname "+(i+1));
                br = new BufferedReader(new InputStreamReader(System.in));
                gpMem[i] = br.readLine();
                //gpMem[i]=null;
            }
         
            mo.removeGroupNames(gpMem);
            System.out.println(" object updated "+topo.updateObject(mo));
        }
        catch(Exception e){
            e.printStackTrace();
        }            
        
    }

    public static void main(String args[]){
        boolean flag=false;
        try 
        {
 	TopoAPI topo = (TopoAPI)Naming.lookup("//localhost/TopoAPI");
	System.out.println ( "Successfully got the handle for TopoAPI");
        System.out.println("Available Methods are");
	System.out.println("1 . instantiate  ");
	System.out.println("2 . getProperties ");
	System.out.println("3 . getGroupmembers ");
	System.out.println("4 . setgroupMembers ");
	System.out.println("5 . removeGroupMembers  ");
	System.out.println("6 . setGroupNames ");
	System.out.println("7 . getGroupNames ");
	System.out.println("8 . addGroupNames ");
	System.out.println("9 . removeGroupNames ");
        String choice = null;
	//        if ( args==null){
            System.out.println(" Give the method name -->>");
             BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             choice = br.readLine();
	     // }
	     // else
	
	     //  choice = args[0];

        GroupTest gt = new GroupTest();
        
        if (choice.equalsIgnoreCase("instantiate"))
            gt.instantiate(topo);
        else if (choice.equalsIgnoreCase("getproperties"))
            gt.getProperties(topo);
        else if (choice.equalsIgnoreCase("getgroupmembers"))
            gt.getGroupMembers(topo);
        else if (choice.equalsIgnoreCase("setgroupmembers"))
            gt.setGroupMembers(topo);
        else if (choice.equalsIgnoreCase("addgroupmembers"))
            gt.addGroupMembers(topo);
        else if (choice.equalsIgnoreCase("removegroupmembers"))
            gt.removeGroupMembers(topo);
        else if (choice.equalsIgnoreCase("setgroupnames"))
            gt.setGroupNames(topo);
        else if (choice.equalsIgnoreCase("getGroupNames"))
            gt.getGroupNames(topo);
        else if (choice.equalsIgnoreCase("addgroupnames"))
            gt.addGroupNames(topo);
        else if (choice.equalsIgnoreCase("removegroupnames"))
            gt.removeGroupNames(topo);
       }
        catch (Exception re) 
        { 
	System.out.println ( "Error in getting the handle for TopoAPI ");
        re.printStackTrace();
        }
    }
}













