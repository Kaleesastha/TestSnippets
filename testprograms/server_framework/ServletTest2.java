import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.adventnet.nms.startnms.*;
import com.adventnet.management.log.SystemUtil;
import com.adventnet.nms.store.relational.*;
import com.adventnet.management.transaction.ConnectionPool;
import com.adventnet.snmp.beans.SnmpTarget;
import com.adventnet.management.transaction.*;
import java.sql.*;
import com.adventnet.nms.util.*;
import com.adventnet.nms.store.relational.RelationalUtil; 
import com.adventnet.nms.topodb.*;
import com.adventnet.nms.store.*;
import com.adventnet.management.log.*;



public class ServletTest2 extends HttpServlet 
{
    
    
    
    // Usage   http://localhost:9090/servlets/ServletTest2
    
    public String getServletInfo() 
    {
        return "This servlet returns an applet tag for poll data graphs";
    }
    public void doPost(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException 
    {
        doGet(req, res);
    }
    
    public synchronized void doGet(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException 
    { 
        


        PrintWriter out=res.getWriter(); 
        out.println("*8888");
        try
        {
            
            TopoAPI topoApi = (TopoAPI)NmsUtil.getAPI("TopoAPI"); 
            TransactionAPI tranapi=NmsUtil.relapi.getTransactionAPI(); 
            ConnectionPool conpl=NmsUtil.relapi.getConnectionPool();
            TransactionAPI cotr=conpl.getTransactionAPI();
            
            TransactionAPI tapi=new TransactionAPI();
            tapi.setConnectionPool(conpl);
            
            out.println("TIME OUT VALUE TRAPI FOR TEST "+tapi.getTransactionTimeOut());
            out.println("TIME OUT VALUE TRAPI FROM RELAPI "+tranapi.getTransactionTimeOut());
            out.println("TIME OUT VALUE TRAPI FROM CONNECTION POOL  "+cotr.getTransactionTimeOut());
            //  out.println("TIME OUT VALUE FROM CPOOL "+conpl.getTransactionTimeOut());
            
         

            tapi.begin();
            //tranapi.setTransactionTimeout(40000);
            //Thread.sleep(10001);
            ManagedObject mobj=new ManagedObject();
            mobj.setName("RAJ371");
            out.println(topoApi.addObject(mobj));
            tapi.commit();
            

            cotr.begin();
            //tranapi.setTransactionTimeout(40000);
            ManagedObject mob=new ManagedObject();
            mob.setName("RAJ271");
            //Thread.sleep(10001);
            out.println(topoApi.addObject(mob));
            cotr.commit();

            tranapi.begin();
            //tranapi.setTransactionTimeout(40000);
            ManagedObject mo=new ManagedObject();
            mo.setName("RAJ171");
            //Thread.sleep(10001);
            out.println(topoApi.addObject(mo));
            tranapi.commit();
            
        }
        
        catch(Exception e)
            {
                e.printStackTrace(out);
                out.println("exception "+e);
            }
        
    }	
    
    void errorPage(String s, HttpServletRequest req, HttpServletResponse res) throws IOException
    {
        PrintWriter out = new PrintWriter(res.getOutputStream());
        out.println(s);
    }    
}





























