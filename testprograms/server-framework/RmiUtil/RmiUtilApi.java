
import com.adventnet.nms.rmi.RMIUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.*;
import java.net.*;  

public class RmiUtilApi extends HttpServlet
{
    HttpServletRequest req = null;
 PrintWriter out=null;     
    public void doPost(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException
    {
        doGet(req, res);
    }
 
    public synchronized void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
        {
        out = res.getWriter();        
	out.println("startedddddddddd------");
        
	try{
	remoteServer rs = new remoteServer();
	
	RMIUtil.bindObjectInRegistry(rs,"testp",true);

        Thread.sleep(2000);

        out.println("*_*_*_MAP is *_*_*_"+RMIUtil.getRMIBindStatus("MapAPI"));
	out.println("*_*_*MINE is *_*_*_"+RMIUtil.getRMIBindStatus("testp"));
        RMIUtil.unbindObjectFromRegistry("testp");
        out.println("*_*_*_The remote object is *_*_*_"+RMIUtil.getRMIBindStatus("testp"));
        }
	catch(Exception e)
	{
	out.println(e);
	}
    }
}

