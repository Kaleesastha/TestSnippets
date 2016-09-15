import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.*;
import java.net.*;  
import com.adventnet.management.policydb.*;
import com.adventnet.nms.util.*;

public class SFPLCYIOP extends HttpServlet
{
    HttpServletRequest req = null;
	PrintWriter out=null;  
	NmsUtil nmsutil = null; 
	NmsPolicyAPI api = null;

	public void doPost(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException
    {
        doGet(req, res);
    }
 
    public synchronized void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        out = res.getWriter();
		
		
		try {
			 api = (NmsPolicyAPI)NmsUtil.getAPI("NmsPolicyAPI");
	
			testCase001();
			testCase002();
			testCase011();

		}catch(Exception e) { e.printStackTrace(); }
	}

	private void testCase001()
	{
		try {
			boolean b = api.addPolicy(null);
			if(b)
				out.println("Result for TestCaseID SF-PLCY-IOP-001 :FAIL");
			else
				out.println("Result for TestCaseID SF-PLCY-IOP-001 :PASS");
			}catch(Exception e) {out.println("Result for TestCaseID SF-PLCY-IOP-001 :FAIL"); e.printStackTrace(); }
	}


	private void testCase002()
	{
		try {
			boolean flag = api.stopPolicy(null);
			if(flag)
				out.println("Result for TestCaseID SF-PLCY-IOP-002 :FAIL");
			else
				out.println("Result for TestCaseID SF-PLCY-IOP-002 :PASS");
			}catch(Exception e) {out.println("Result for TestCaseID SF-PLCY-IOP-002 :FAIL"); e.printStackTrace(); }
	}


	private void testCase011()
	{
		out.println("inside 11");
		try {
			boolean pol = api.addPolicyActionAndCondition(null,null,null);
			if(pol)
				out.println("Result for TestCaseID SF-PLCY-IOP-011 :FAIL");
			else
				out.println("Result for TestCaseID SF-PLCY-IOP-011 :PASS");
			}catch(Exception e) {out.println("Result for TestCaseID SF-PLCY-IOP-011 :FAIL"); e.printStackTrace(); }
	}
} // SFPLCYIOP
