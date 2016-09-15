
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

import com.adventnet.nms.util.*;
import com.adventnet.nms.store.relational.*;
import com.adventnet.nms.store.*;
import com.adventnet.management.transaction.TransactionAPI;
import test.UserObject2;

//INSTRUCTIONS::: FOR DB-OBJ-REL-27 TO DB-OBJ-REL-34
/* Before using this test program do the following things
   compile the UserObject2.java(Use the UserObject.java and change the name)

   Either generate Rletionalclasses,Schema,Aliases,and Relational class entry
          and compile Relational classes and append all the three conf file
   Or
       Compile RelationalUserObject2.java from example directory
       and append the following things:
      
       In DatabaseAliases.conf file under conf directory

       In Table UserObject2
       name NAME
       value  VALUE
       type  TYPE
       
       In DatabaseSchema.conf file 

       BeginCreateSchema
       create table UserObject2 (
       "NAME" VARCHAR (100) NOT NULL ,
       "VALUE" VARCHAR (100) ,
       "TYPE" VARCHAR (100) ,
       PRIMARY KEY ("NAME"))
       EndCreateSchema
       
       BeginDropSchema
       drop table UserObject2
       EndDropSchema
       
       BeginIndex
       CREATE INDEX UserObject0_ndx ON UserObject2 ("NAME")
       EndIndex
       
       In relationalclasses.conf file 

       test.UserObject2	userstoragedb	RelationalUserObject2	UserObject2


*/    

public class TestUserStorageServlet extends HttpServlet 
{
	
    
   
    
    // Usage   http://localhost:9090/servlets/TestStorageServlet

    //before using this servlet compile UserObjectO.java, RelationalUserObjectO.java
    //And  append DatabaseSchema,relationalclasses and databasealises conf file

    public String getServletInfo() 
    {
        return "This servlet returns an applet tag for poll data graphs";
    }
    /**
     * Handle POST the same as GET.
     * This method is simply a call to doGet().
     *
     * @param req encapsulates the request to the servlet
     * @param resp encapsulates the response from the servlet
     * @see javax.servlet.http.HttpServletRequest#getPathTranslated
     * @exception ServletException will be passed on from included servlets
     * @see #doGet
     */
    
    public void doPost(HttpServletRequest req, HttpServletResponse res)
        throws ServletException,IOException 
    {
        doGet(req, res);
    }
    
    public synchronized void doGet(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException 
    {   
        
        PrintWriter out=res.getWriter();
        UserObject2 object= new UserObject2();
        UserObject2 object1=new UserObject2();
        UserObject2 object2=new UserObject2();
        object.setSource("machine1");
        object.setId(3);
        object.setTimeValue(1921683123);
        object1.setSource("machine2");
        object1.setId(4);
        object1.setTimeValue(1921683124);
        object2.setSource("machine3");
        object2.setId(5);
        object2.setTimeValue(192163125);
        UserStorageAPIImpl userdb=DBUserStorageServer.userstoragedb;
        TransactionAPI transactionApi = userdb.getTransactionAPI();
        if(transactionApi!=null)
        {
            out.println("Gllllllot the handle of TransacationAPI==>DB-OBJ-REL-26 - PASSED");
        }
        try
	{
	    transactionApi.begin(10000);
            out.println("succesfully transaction begined");
	}
	catch(Exception begExc)
	{
            out.println("Exception while begin transaction :"+begExc);
        }
        try
        {
            userdb.addObject(object,"machine1");
            userdb.addObject(object1,"machine2");
            userdb.addObject(object2,"machine3");
            try
            { 
                Thread.sleep(12000);
            }
            catch(Exception e){ }
            transactionApi.commit();
            out.println("Transaction  succesfully commited");
        }
        catch(Exception e1)
        {
            out.println("Exception while performing database operations"+e1);
            e1.printStackTrace();
            try
            {
                transactionApi.rollback("Database Exception occured while Updating "+ e1.getMessage());
               out.println("Transaction  Rolled back");
            }
            catch(Exception e)
            {
                out.println("Exception while Rollback of Transaction :"+e);
            }
            
            
        }
    }	
    void errorPage(String s, HttpServletRequest req, HttpServletResponse res) throws IOException
    {
        PrintWriter out = new PrintWriter(res.getOutputStream());
        out.println(s);
    }    
}







































