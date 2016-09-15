/*
$Id: AddPollServlet.java,v 1.1.1.1 2001/01/10 15:20:13 nms Exp $
*/
/**
 * FetchPollData.java
 */

package com.adventnet.nms.servlets;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;
import com.adventnet.nms.util.*;
import com.adventnet.management.log.Log;
import com.adventnet.nms.topodb.*;
import com.adventnet.nms.poll.PolledData;
import com.adventnet.nms.poll.PollAPI;

public class AddPollServlet extends HttpServlet 
{
	
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
			PollAPI obj;
			for(Enumeration parameterNames = req.getParameterNames();
					parameterNames.hasMoreElements();)
			{
				String param = (String)parameterNames.nextElement();
				String value = (String)req.getParameter(param);

				if (value == null) value = "-";

				ht.put(param,value);
			}
			try {
				obj = GenericUtility.getPollAPI();
			}
			catch (Exception e) 
			{
				NmsLogMgr.MISCUSER.log("Remote exception: " + e.getMessage(),Log.SUMMARY);
				NmsLogMgr.MISCERR.fail("",e);
				errorPage("Error getting Poll access.", req,res);
				return;
			}
			String sindex = (String)ht.get("INDEX");
			int index = Integer.parseInt(sindex);  
			sindex = (String)ht.get("START");
			int start = Integer.parseInt(sindex);  
			sindex = (String)ht.get("END");
			int end = Integer.parseInt(sindex);  
			String period = (String)ht.get("PERIOD");
			sindex = (String)ht.get("RANGE");
			int range = 0;
			if(sindex != null)
				range = Integer.parseInt(sindex);  
			System.out.println(index + "  " + start + "  " + end + "  "+ range + "  " + period);
			if(index == 1)
			{
				for(int i=start;i<=end;i++)
				{
					Properties p = new Properties();
					p.put("name", "Interface"+i);
					//p.put("community", "public"); 
					p.put("agent","srini"+i );
					//p.put("dnsName", "srini"+i);
					p.put("oid","ifInOctets" ); 
					p.put("period",period);
					//p.put("period", String.valueOf(100));
					PolledData pd = new PolledData();
					pd.setProperties(p);
					obj.addPoll(pd);
					try {
					Thread.sleep(100);
					}catch(Exception e) {}
				}
			}
			if(index == 2)
			{
				for(int i=start;i<=end;i++)
				{
					for(int j=1;j<=range;j++)
					{
						Properties p = new Properties();
						p.put("name", "Interface"+j);
						//p.put("community", "public"); 
						p.put("agent","srini"+i );
						p.put("dnsName", "srini"+i);
						p.put("oid","ifInOctets" ); 
						p.put("period",period);
						//p.put("period", String.valueOf(100));
						PolledData pd = new PolledData();
						pd.setProperties(p);
						obj.addPoll(pd);
						try {
							Thread.sleep(300);
						}catch(Exception ex)
						{}
					}
				}
			}
			if(index == 3)
			{
				for(int i=start;i<=end;i++)
				{
					for(int j=1;j<=range;j++)
					{
						Properties p = new Properties();
						p.put("name", "Interface"+j);
						//p.put("community", "public"); 
						p.put("agent","srini"+i );
						//p.put("dnsName", "srini"+i);
						p.put("oid","ifInOctets" ); 
						p.put("period",period);
						p.put("isMultiplePolledData","true");
						//p.put("period", String.valueOf(100));
						PolledData pd = new PolledData();
						pd.setProperties(p);
						obj.addPoll(pd);
					}
				}
			}
		}	
	void errorPage(String s, HttpServletRequest req, HttpServletResponse res) throws IOException
    {
        PrintWriter out = new PrintWriter(res.getOutputStream());
        out.println(s);
    }    
}
