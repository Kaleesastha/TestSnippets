
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

import com.adventnet.nms.util.*;
import com.adventnet.nms.store.relational.*;
import com.adventnet.nms.store.*;
import com.adventnet.management.transaction.TransactionAPI;
import test.*;

public class TestStorageServlet extends HttpServlet 
{
	
    


    // Usage   http://localhost:9090/servlets/TestStorageServlet

    //before using this servlet compile TestObject.java, RelationalTestObject.java
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
        
        TestObject object= new TestObject();
        TestObject object1=new TestObject();
        TestObject object2=new TestObject();
        object.setName("machine1");
        object.setType("router");
        object.setValue("192.168.3.123");
        object1.setName("machine2");
        object1.setType("switch");
        object1.setValue("192.168.3.124");
        object2.setName("machine3");
        object2.setType("snmpnode");
        object2.setValue("192.168.3.125");
        UserStorageAPIImpl userdb=DBUserStorageServer.userstoragedb;
        TransactionAPI transactionApi = userdb.getTransactionAPI();
        try
	{
	    transactionApi.begin(10000);
            SystemUtil.cout.println("succesfully transaction begined");
	}
	catch(Exception begExc)
	{
            com.adventnet.nms.util.SystemUtil.cout.println("Exception while begin transaction :"+begExc);
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
            SystemUtil.cout.println("Transaction  succesfully commited");
        }
        catch(Exception e1)
        {
            SystemUtil.cout.println("Exception while performing database operations"+e1);
            try
            {
                transactionApi.rollback("Database Exception occured while Updating "+ e1.getMessage());
                SystemUtil.cout.println("Transaction  Rolled back");
            }
            catch(Exception e)
            {
                SystemUtil.cout.println("Exception while Rollback of Transaction :"+e);
            }
            
            
        }
    }	
    void errorPage(String s, HttpServletRequest req, HttpServletResponse res) throws IOException
    {
        PrintWriter out = new PrintWriter(res.getOutputStream());
        out.println(s);
    }    
}







































