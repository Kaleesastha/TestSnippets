/* $Id: TestStorageAPI.java,v 1.3 2010/10/29 13:46:04 swaminathap Exp $ */
package test;
import java.rmi.Naming;
import java.io.BufferedReader;
import com.adventnet.nms.persistence.StorageAPI;
import java.sql.Date;
import java.util.Vector;
import java.util.Properties;
import com.adventnet.nms.util.*;
public class TestStorageAPI
 {

     static StorageAPI storageapi =null ;
     static String operation ="";
     static BufferedReader consoleInput  = null;
     public static void main(String args[]) throws Exception
     {
         try 
         {
             if(args.length !=3)
             {
                 System.out.println("Usage : java test.TestStorageAPI <host-name> <rmiregistry-port> <operation-name(add || update || delete || get || getobjects)>"); //No I18N

                 return;
             }
             String url="//"+args[0] +":"+  args[1] + "/StorageAPI";
             try
             {
                 storageapi = (StorageAPI) Naming.lookup(url);
                 System.out.println(NmsUtil.GetString("RMI Handle of StorageAPI is obtained"));//No I18N
             }
             catch(Exception e)
             {
                 System.out.println ("Error in getting the handle StorageAPI"+e);  //No I18N
                 return;
             }
             operation  = args[2];
             if(operation.equals("add") )
             {
                 addObject();
             }
             else if(operation.equals("get") )
             {
                 getObject();
             }
             else if(operation.equals("update") )
             {
                 updateObject();
             }
             else if(operation.equals("delete") )
             {
                 deleteObject();
             }
             else if(operation.equals("getobjects") )
             {
                 getObjects();
             }
             else
             {
                 System.out.println(NmsUtil.GetString("Invalid operation name "));//No I18N 
                 System.out.println(NmsUtil.GetString("Operations: add, update, get, delete , getobjects"));//No I18N
             }
         }
         catch (Exception e) 
         { 

             e.printStackTrace();
             System.exit(-1);
         }

     }
     private static void addObject()throws Exception
     {
         UserObject object = new UserObject();
         object.setSource("localhost");//No I18N
         object.setTimeValue(1000);
         object.setDayOfEvent(new Date(System.currentTimeMillis()));
         storageapi.addObject("test.UserObject",object); //No I18N
         System.out.println(NmsUtil.GetString("Object added into the Database")); //No I18N
    }
     private static void getObject()throws Exception
     {
         UserObject object = null;
	 long c=1;
  	 object=(UserObject)storageapi.getObject(c,"test.UserObject"); //No I18N
  	 System.out.println(NmsUtil.GetString("Object fetched from Database"));//No I18N
         System.out.println("Source  = "+object.getSource());//No I18N
         System.out.println("ID  = "+object.getId());//No I18N
         System.out.println("TimeValue  = "+object.getTimeValue());//No I18N
         System.out.println("Dayofevent  = "+object.getDayOfEvent());//No I18N
     }
     private static void updateObject()throws Exception
     {
			
         UserObject object = null;
    	     long c=1;
  	 object=(UserObject)storageapi.getObject(c,"test.UserObject"); //No I18N
      	     // changing the value of dayof event
         object.setDayOfEvent(new Date(System.currentTimeMillis()));
         storageapi.updateObject(object,object.getId());
         System.out.println(NmsUtil.GetString("Object Updated "));//No I18N
    }
     private static void deleteObject()throws Exception
    {
 	
         UserObject object = null;
	 long c=1;
  	 object=(UserObject)storageapi.getObject(c,"test.UserObject"); //No I18N
    	    // changing the value of dayof event
         object.setDayOfEvent(new Date(System.currentTimeMillis()));
         storageapi.deleteObject(object,object.getId());
         System.out.println(NmsUtil.GetString("Object deleted "));//No I18N
    }
     private static void getObjects()throws Exception
     {
        UserObject object = new UserObject();
        object.setSource("remotemachine");//No I18N
        object.setTimeValue(1000);
        object.setDayOfEvent(new Date(System.currentTimeMillis()));
        storageapi.addObject("test.UserObject",object); //No I18N
        System.out.println(NmsUtil.GetString("Object added into the Database"));//No I18N
 	Properties p=new Properties();
 	p.put("source","remotemachine");	//No I18N
        Vector objects = storageapi.getObjects("test.UserObject",p); //No I18n
 	       
    }
    
}











