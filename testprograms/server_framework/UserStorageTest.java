import java.rmi.*;
import com.adventnet.nms.store.UserStorageAPI;
import com.adventnet.nms.store.*; 
import java.util.*;
import java.io.*;
import test.*;


/* Before using this test program do the following things
   compile the TestObject under example directory
   or check out from examples programs

   Either generate Rletionalclasses,Schema,Aliases,and Relational class entry
          and compile Relational classes and append all the three conf file
   Or
       Compile RelationalTestObject.java from example directory
       and append the following things:
      
       In DatabaseAliases.conf file under conf directory

       In Table TestObject
       name NAME
       value  VALUE
       type  TYPE
       
       In DatabaseSchema.conf file 

       BeginCreateSchema
       create table TestObject (
       "NAME" VARCHAR (100) NOT NULL ,
       "VALUE" VARCHAR (100) ,
       "TYPE" VARCHAR (100) ,
       PRIMARY KEY ("NAME"))
       EndCreateSchema
       
       BeginDropSchema
       drop table TestObject
       EndDropSchema
       
       BeginIndex
       CREATE INDEX TestObject0_ndx ON TestObject ("NAME")
       EndIndex
       
       In relationalclasses.conf file 

       test.test.TestObject	userstoragedb	RelationalTestObject	TestObject


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
        TestObject  object= new TestObject();
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
        String type=null;
        String value=null;
        if(bool!=4)
        {
            System.out.print("\n NAME:");
            name=buf.readLine();
            if((bool==1)||bool==3)
            {
                System.out.print("\n TYPE :");
                type=buf.readLine();
                System.out.print("\n VALUE :");
                value=buf.readLine();
            }
        }
        
        
      
        
        boolean success=false;
        object.setName(name);
        object.setType(type);
        object.setValue(value);
        try{
            if(bool==1)
            {
                success= userdb.addObject(object,name);
                System.out.println("Result of addobject  : "+success);
            }
            else if(bool==2)
            {
               
                success= userdb.deleteObject(object,name);
                System.out.println("Result of deleteobject  : "+success);
            }
            else if(bool==3)
            {
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
                System.out.println("\nEnter classname:  ");
                classname=buf.readLine();
                String name1=null;
                String value1=null;
                String type1=null;
                Object testobj=userdb.getObject(name,classname);
                if(testobj!=null)
                {
                    TestObject test= (TestObject)testobj;
                    name1=test.getName();
                    value1=test.getValue();
                    type1=test.getType();
                }
                if(name==null)System.out.println("Object Doesn't exists");
                else
                    System.out.println("Get object's Results :   "+name1+"  "+type1 +" "+ value1);
            }
        }
        catch(NmsStorageException e)
        {
            System.out.println("Exception Name : NmsStorageException");
            System.out.println("Message : "+e.getMessage());
        }
        catch(RemoteException e1)
        {
            e1.printStackTrace();
        }
    }
}














