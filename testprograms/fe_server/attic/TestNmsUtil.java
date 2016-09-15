package test;

import java.io.*;
import java.util.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;
import com.adventnet.nms.util.*;
import com.adventnet.management.log.Log;
import com.adventnet.nms.topodb.*;
import com.adventnet.nms.store.*;

public class TestNmsUtil extends HttpServlet
{
    /*
     *
     *	 Usage   http://localhost:9090/servlets/test.RegisterBEFailOverListenerServlet
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
		try{
		   PrintWriter out=res.getWriter();
		   out.println(" NmsUtil.getAPI(\"xyx\") : "+NmsUtil.getAPI("xyz"));
		   out.println(" NmsUtil.getAPI(\"TopoAPI\") : "+NmsUtil.getAPI("TopoAPI"));

		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}


}
