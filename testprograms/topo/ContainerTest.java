import test.SysTypeObject;
import java.rmi.Naming;
import java.util.*;
import com.adventnet.nms.topodb.*;
import java.io.*;

public class ContainerTest{

    public void instantiate(TopoAPI topo){
        try{
            System.out.print("GiveNameOf ContainerObject-->>");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String param = br.readLine();            
            SysTypeObject co = new SysTypeObject(); 
            String[] str = {"sunitha","krishnamoorthir"};
            String[] grp= {"testgrp1","testgrp2"};
            //            go.setGroupNames(grp);
            //go.setGroupMembers(str);
            co.setName(param);
            
            System.out.print("Give ownerName ( if no ownerName give null)-->>");
            br = new BufferedReader(new InputStreamReader(System.in));
            param = br.readLine();
            if ( param.equalsIgnoreCase("null"))
            {
                co.setOwnerName(null);
            }
            else co.setOwnerName(param);
            //  else co.setOwnerName(param);
            
            
            System.out.println(" ContainerObject added "+topo.addObject(co));
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

    public void setChildrenKeys(TopoAPI topo){
        try{
        System.out.println(" GiveNameOf ContainerObject-->>");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String param = br.readLine();
        ManagedObject mo=topo.getByName(param);
        System.out.println(" Give the number of Childrens-->>");
         br = new BufferedReader(new InputStreamReader(System.in));
         param = br.readLine();
         String[] gpMem = null;
         int num = Integer.parseInt(param);
         if ( num !=0 )
             gpMem = new String[num];
         for(int i=0;i<num;i++){
             System.out.println(" give the chindren "+(i+1));
             br = new BufferedReader(new InputStreamReader(System.in));
             String name=br.readLine();
             System.out.println(name);
             if(name.equalsIgnoreCase("null"))
             {
                 gpMem[i] =null;
             }
             
             else   gpMem[i]=name;
     }
        
         //System.out.println(gpMem);
         mo.setChildrenKeys(gpMem);
         System.out.println(" object updated "+topo.updateObject(mo));
        }
        catch(Exception e){
            e.printStackTrace();
        }            
    }

    public void addChildrenKeys(TopoAPI topo){
        try{
        System.out.println(" GiveNameOf ContainerObject-->>");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String param = br.readLine();
        ManagedObject mo=topo.getByName(param);
        System.out.println(" Give the number of Childrens to be added-->>");
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
             
         mo.addChildrenKeys(gpMem);
         System.out.println(" object updated "+topo.updateObject(mo));
        }
        catch(Exception e){
            e.printStackTrace();
        }            
    }

    public void removeChildrenKeys(TopoAPI topo){
        try{
        System.out.println(" GiveNameOf ContainerObject-->>");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String param = br.readLine();
        ManagedObject mo=topo.getByName(param);
        System.out.println(" Give the number of childrens to be removed-->>");
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
             
         mo.removeChildrenKeys(gpMem);
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
    public void setParentKey(TopoAPI topo)
    {
        try{
            System.out.println(" GiveNameOf Object-->>");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String param = br.readLine();
            ManagedObject mo=topo.getByName(param);
            System.out.println("Give the parent name---->");
            /* System.out.println(" Give the number of parentkeys to be set-->>");
               br = new BufferedReader(new InputStreamReader(System.in));
               param = br.readLine();
               String[] gpMem = null;
               int num = Integer.parseInt(param);
               if ( num != 0)
               gpMem = new String[num];
               for(int i=0;i<num;i++){
               System.out.println(" give the parentname "+(i+1));*/
            
            br = new BufferedReader(new InputStreamReader(System.in));
            String pname = br.readLine();
            // gpMem[i]=null;
            
            
            mo.setParentKey(pname);
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
            System.out.println(" Give the number of groupNames to add-->>");
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
        
        String choice = null;
        if ( args==null){
            System.out.println(" Give the method name -->>");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            choice = br.readLine();
        }
        else
            choice = args[0];

        ContainerTest gt = new ContainerTest();
        
        if (choice.equalsIgnoreCase("instantiate"))
            gt.instantiate(topo);
        else if (choice.equalsIgnoreCase("getproperties"))
            gt.getProperties(topo);
        else if (choice.equalsIgnoreCase("getgroupmembers"))
            gt.getGroupMembers(topo);
        else if (choice.equalsIgnoreCase("setchildrenkeys"))
            gt.setChildrenKeys(topo);
        else if (choice.equalsIgnoreCase("addchildrenkeys"))
            gt.addChildrenKeys(topo);
        else if (choice.equalsIgnoreCase("removechildrenkeys"))
            gt.removeChildrenKeys(topo);
        else if (choice.equalsIgnoreCase("setparentkey"))
            gt.setParentKey(topo);
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













