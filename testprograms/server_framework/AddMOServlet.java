
package com.adventnet.nms.servlets;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;
import com.adventnet.nms.util.*;
import com.adventnet.management.log.Log;
import com.adventnet.nms.topodb.*;

public class AddMOServlet extends HttpServlet 
{
	
	
	// Usage   http://subramani:9090/servlets/com.adventnet.nms.servlets.AddMOServlet?&START=1&END=10000&POLLINTERVAL=1000
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
			TopoAPI obj;
			for(Enumeration parameterNames = req.getParameterNames();
					parameterNames.hasMoreElements();)
			{
				String param = (String)parameterNames.nextElement();
				String value = (String)req.getParameter(param);

				if (value == null) value = "-";

				ht.put(param,value);
			}
			try {
				obj = GenericUtility.getTopoAPI();
			}
			catch (Exception e) 
			{
				NmsLogMgr.MISCUSER.log("Remote exception: " + e.getMessage(),Log.SUMMARY);
				NmsLogMgr.MISCERR.fail("",e);
				errorPage("Error getting Topo access.", req,res);
				return;
			}
			String sindex = "";
			sindex = (String)ht.get("START");
			int start = Integer.parseInt(sindex);  
			sindex = (String)ht.get("END");
			int end = Integer.parseInt(sindex);  
			String period = (String)ht.get("POLLINTERVAL");
			
/*			ManagedObject mo1 = new ManagedObject();
			mo1.setName("10.10.10.0");
			mo1.setType("Network");
			obj.addObject(mo1);			
			
*/			System.out.println(" Started to add MO from "+start+ " to "+end);

			for(int i=start;i<=end;i++)
			{
				Properties p = new Properties();
				ManagedObject mo = new ManagedObject();
				mo.setName("ManagedObject"+i);
				mo.setParentKey("192.168.1.0");
				obj.addObject(mo);
				try
				{
					Thread.sleep(10);
				}catch (Exception e)
				{
					e.printStackTrace();
				}
			}
			System.out.println("Finished adding MO from AddMOServlet");

		}	
	void errorPage(String s, HttpServletRequest req, HttpServletResponse res) throws IOException
    {
        PrintWriter out = new PrintWriter(res.getOutputStream());
        out.println(s);
    }    
}
