

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;
import com.adventnet.nms.util.*;
import com.adventnet.management.log.Log;
import com.adventnet.nms.topodb.*;

public class Logging extends HttpServlet 
{
    /*
     *
     *	 Usage   http://localhost:9090/servlets/Logging?&MODE=1&NO=1000
     *
     */
	

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
		throws ServletException, IOException 
	{
		doGet(req, res);
	}
	
	public synchronized void doGet(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException 
	{   
		Hashtable ht = new Hashtable();
		for(Enumeration parameterNames = req.getParameterNames();
			parameterNames.hasMoreElements();)
		{
			String param = (String)parameterNames.nextElement();
			String value = (String)req.getParameter(param);

			if (value == null) value = "-";

			ht.put(param,value);
		}
		int mode = Integer.parseInt((String)(ht.get("MODE")));
		int number = Integer.parseInt((String)(ht.get("NO")));
		switch (mode)
		{
		case 1:
			
			for(int i= 0;i<= number;i++)
			{
				System.out.println(i);
			}
			break;
			
		case 2:
			
			for(int i= 0;i<= number;i++)
			{
				System.err.println(i);
				Thread.dumpStack();
			}
			break;
		case 3:
			
			for(int i= 0;i<= number;i++)
			{
				NmsLogMgr.POLICYUSER.log(""+i , Log.SUMMARY);
			}
			break;
		case 4:
			
			for(int i= 0;i<= number;i++)
			{
				NmsLogMgr.POLICYERR.log(""+i , Log.SUMMARY);
			}
			break;
		case 5:
			
			for(int i= 0;i<= number;i++)
			{
				NmsLogMgr.ALERT_AUDITUSER.log(""+i , Log.SUMMARY);
			}
			break;
		case 6:
			
			for(int i= 0;i<= number;i++)
			{
				NmsLogMgr.DISCOVERYUSER.log(""+i , Log.SUMMARY);
			}
			break;
		}
	}	
	void errorPage(String s, HttpServletRequest req, HttpServletResponse res) throws IOException
	{
		PrintWriter out = new PrintWriter(res.getOutputStream());
		out.println(s);
	}    
}
