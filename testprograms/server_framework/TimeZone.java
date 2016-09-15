package test;

import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import java.util.*; 
import com.adventnet.nms.commonbe.*;
import com.adventnet.nms.util.PureServerUtilsFE;

public class TimeZone extends HttpServlet
{
    /*
     *
     http://localhost:9095/servlets/test.TimeZone
     *
     */
 
        public void doPost(HttpServletRequest req, HttpServletResponse res)
                throws ServletException, IOException
        {
                doGet(req, res);
        }
 
 
        public synchronized void doGet(HttpServletRequest req, HttpServletResponse res)
                throws ServletException, IOException
        {
           PrintWriter out=res.getWriter();
        try{
            BEServerContext beSvrContxt=PureServerUtilsFE.getBEServerContext(); 
            out.println(" the be server context is - "+beSvrContxt);
            
            String timeZoneID=beSvrContxt.getProperty("TimeZoneID"); 
            out.println("TimeZoneID is" + timeZoneID); 
            
            out.println(" getProperty() method -"+beSvrContxt.getProperty("18"));
            
            out.println(" getProperties() method-"+beSvrContxt.getProperties());
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
