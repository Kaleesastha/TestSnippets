import java.rmi.*;
import com.adventnet.nms.store.UserStorageAPI;
import com.adventnet.nms.store.*; 
import java.util.*;
import java.io.*;
import test.UserObject;

// INSTRUCTIONS ::::::::::(DB-OBJ-REL-19 TO DB-OBJ-REL-25) && (DB-OBJ-REL-35 TO DB-OBJ-REL-40)
// (DB-OBJ-REL-46 To DB-OBJ-REL-49)
/* Before using this test program do the following things
   compile the UserObject.java

   Either generate Rletionalclasses,Schema,Aliases,and Relational class entry
          and compile Relational classes and append all the three conf file
   Or
       Compile RelationalTestObject.java from example directory
       and append the following things:
      
       In DatabaseAliases.conf file under conf directory

       In Table UserObject
       name NAME
       value  VALUE
       type  TYPE
       
       In DatabaseSchema.conf file 

       BeginCreateSchema
       create table UserObject (
       "NAME" VARCHAR (100) NOT NULL ,
       "VALUE" VARCHAR (100) ,
       "TYPE" VARCHAR (100) ,
       PRIMARY KEY ("NAME"))
       EndCreateSchema
       
       BeginDropSchema
       drop table UserObject
       EndDropSchema
       
       BeginIndex
       CREATE INDEX UserObject0_ndx ON UserObject ("NAME")
       EndIndex
       
       In relationalclasses.conf file 

       test.UserObject	userstoragedb	RelationalUserObject	UserObject


*/    

class UserStorageTest
{
   
    public static void main(String args[]) throws Exception
    {
        
        Properties prop= null;
        UserStorageAPI userdb=null;    
        try 
        {
            
            userdb = (UserStorageAPI) Naming.lookup ("//localhost/UserStorageAPI");
            System.out.println ( "Successfully got the handle for UserstorageAPI");
        }
        catch (Exception remoteException) 
        { 
            System.out.println ( "Error in getting the handle for UserstorageAPI"+remoteException); 
            System.exit(-1);
        }

        forOtherCases();//DB-OBJ-REL-46 To 49

        //For DB-OBJ-19 to 25 and 35 to 40
        UserObject  object= new UserObject();
        BufferedReader buf =new BufferedReader(new InputStreamReader(new BufferedInputStream(System.in)));
        System.out.println("Option \nADD-1 \nDELETE-2 \nUPDATE-3 \nIsInitilaized-4 \nGET-other values");   
        System.out.print("\nEnter Option :");
        String option=buf.readLine();
        int bool=1;
        while(true)
        {
            try{
                 bool=Integer.parseInt(option);
                 break;
            }
            catch(NumberFormatException e)
            {
                System.out.print("\nEnter valid number Option:");
                option=buf.readLine();
                continue;
            }
        }
        String name=null;
        String id=null;
        String timeValue=null;
        if(bool!=4)
        {
            System.out.print("\n NAME:");
            name=buf.readLine();
            if((bool==1)||bool==3)
            {
                System.out.print("\n ID :");
                id=buf.readLine();
                System.out.print("\n TIMEVALUE :");
                timeValue=buf.readLine();
            }
        }
                
        boolean success=false;
        
        try{
            if(bool==1)
            {
                object.setSource(name);
                object.setId((new Integer(id)).intValue());
                object.setTimeValue((new Long(timeValue)).longValue());
                success= userdb.addObject(object,name);
                System.out.println("Result of addobject  : "+success);
            }
            else if(bool==2)
            {
                String classname=null;
                System.out.println("\nEnter classname:  ");
                classname=buf.readLine();
                Object testObj=userdb.getObject(name,classname);
                success= userdb.deleteObject(testObj,name);
                System.out.println("Result of deleteobject  : "+success);
            }
            else if(bool==3)
            {
                object.setSource(name);
                object.setId((new Integer(id)).intValue());
                object.setTimeValue((new Long(timeValue)).longValue());
                success=userdb.updateObject(object,name);
                System.out.println("Result of updateobject  : "+success);
            }
            else if(bool==4)
            {                
                success=userdb.isInitialized();
                System.out.println("Result of isInialized object  : "+success);
            }
            else 
            {
                String classname=null;
                System.out.println("\nEnter classname:(DO NOT USE PACKAGE STRUCTURE) ");
                classname=buf.readLine();
                String name1=null;
                long timeValue1= 0;
                int id1 = 0;
                Object testobj=userdb.getObject(name,classname);
                if(testobj!=null)
                {
                    UserObject test= (UserObject)testobj;
                    name1=test.getSource();
                    timeValue1=test.getTimeValue();
                    id1=test.getId();
                }
                if(name==null)System.out.println("Object Doesn't exists");
                else
                    System.out.println("Get object's Results :   "+name1+"  "+timeValue1 +" "+ id1);
            }
        }
        catch(NmsStorageException e)
        {
            System.out.println("Exception Name : NmsStorageException");
            System.out.println("Message : "+e.getMessage());
            e.printStackTrace();
        }
        catch(RemoteException e1)
        {
            e1.printStackTrace();
        }

    }

    public static void forOtherCases()//DB-OBJ-REL46 To 49
    {
        UserStorageAPI userdb=null;    
        try 
        {
            
            userdb = (UserStorageAPI) Naming.lookup ("//localhost/UserStorageAPI");
            System.out.println ( "Successfully got the handle for UserstorageAPI");
        }
        catch (Exception remoteException) 
        { 
            System.out.println ( "Error in getting the handle for UserstorageAPI"+remoteException); 
            System.exit(-1);
        }
           Vector objects=null;
        try
        {
            UserObject object= new UserObject();
            UserObject object1=new UserObject();
            UserObject object2=new UserObject();
            object.setSource("machine1");
            object.setId(3);
            object.setTimeValue(1921683123);
            object1.setSource("machine2");
            object1.setId(4);
            object1.setTimeValue(1921683124);
            object2.setSource("machine3");
            object2.setId(5);
            object2.setTimeValue(192163125);
            
            userdb.addObject(object,"machine1");
            userdb.addObject(object1,"machine2");
            userdb.addObject(object2,"machine3");
            
            Properties criteria = new Properties();
            criteria.setProperty("source","machine1");
         
            
            objects=userdb.getObjects("UserObject",criteria);
        
        System.out.println("DB-OBJ-REL-46:::::::::::");
        for(Enumeration en=objects.elements();en.hasMoreElements();)
        {
            UserObject obj=(UserObject)(en.nextElement());
            System.out.println("Value is" + obj.getSource());
            System.out.println("DB-OBJ-REL-46 :::PASSED - if value is --->machine1");
        }
        
        Properties criteria1=new Properties();
        objects=userdb.getObjects("UserObject",criteria1);
        System.out.println("DB-OBJ-REL-47:::    ");
        for(Enumeration en=objects.elements();en.hasMoreElements();)
        {
            UserObject obj=(UserObject)(en.nextElement());
            System.out.println("All Values::  " + obj.getSource());
           
        }
        System.out.println("DB-OBJ-REL-48::::   ");
        Properties criteria2=new Properties();
        objects=userdb.getObjects("WrongObject2",criteria2);
       
        System.out.println("DB-OBJ-REL-49::::   ");//Not Working ??
        UserObject object49=new UserObject();
        object49.setSource("One");

        object49.setId(1);
        userdb.addObject(object49,"MAC1");

        object49.setId(2);
        userdb.addObject(object49,"MAC2");

        System.out.println("DB-OBJ-REL- 052::: ");
        UserObject object52 = new UserObject();
        object52.setSource("MAC52");
        object52.setId(5);
        boolean success=userdb.addObject(object52,"MAC52");
        if(success)
            {
                System.out.println("MAC52-->Object is Added ");
                System.out.println("Delete this object now Directly......Within 120 seconds");
            }
        else System.out.println("MAC52--> Not Added --> Failed");
        
        Thread.sleep(120000);
        UserObject obj52=(UserObject)userdb.getObject("MAC52","UserObject");
        if(obj52!=null)System.out.println("DB-OBJ-REL -052---> PASSED");
        
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
    }
    

}














