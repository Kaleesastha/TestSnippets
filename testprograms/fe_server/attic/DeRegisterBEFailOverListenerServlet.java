package test;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;
import com.adventnet.nms.util.*;
import com.adventnet.management.log.Log;
import com.adventnet.nms.topodb.*;

public class DeRegisterBEFailOverListenerServlet extends HttpServlet
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
	   PrintWriter out=res.getWriter();
           TestBEFailOverListener test=new TestBEFailOverListener();
           PureServerUtilsFE.clientSocketFE.registerBEFailOverListener(test); 
           out.println(" Successfully registered. Check it in stdout.txt logs.");
           PureServerUtilsFE.clientSocketFE.deRegisterBEFailOverListener(test); 

	}


}
