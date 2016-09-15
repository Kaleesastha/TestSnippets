
import com.adventnet.nms.rmi.RMIUtil;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.*;
import java.net.*;  
import java.rmi.Naming;
import java.lang.reflect.Method;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.rmi.server.UnicastRemoteObject;
import java.lang.Class;


public class RmiUtilApi6 extends HttpServlet
{
    HttpServletRequest req = null;
	PrintWriter out=null;  
	boolean flag= false;
	String host = "localhost";
	int port = 1099;
	remoteServer rs = null;
    
	public void doPost(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException
    {
        doGet(req, res);
    }
 
    public synchronized void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        out = res.getWriter();        
		out.println("TestCases SF-RMI-API-*** Started");
        
		

	try{
		rs = new remoteServer();

		/*testCase001();
		testCase002();
		testCase003();
		testCase004();
		testCase005();
		testCase006();
		testCase007(); */
		testCase008();

	  }catch(Exception e){out.println(e);	}
	}

	private void testCase001()
	{
		
		try{
			RMIUtil.bindObjectInRegistry(rs,"case1",true);
			String rm[] = Naming.list("rmi://"+host+":"+port+"/");
		
			for(int i=0;i<rm.length;i++)
			{
				if (rm[i].equals("rmi://localhost:1099/case1"))
				flag = true;
			}
			if (flag) out.println("Result for TestCaseID SF-RMI-API-001:PASS");
			else out.println("Result for TestCaseID SF-RMI-API-001:Fail");

		}catch(Exception e){out.println(e);	}
		try {
			RMIUtil.unbindObjectFromRegistry("case1");
		}catch(Exception e){out.println(e);	}

	}


	private void testCase002()
	{
		try{
			RMIUtil.bindObjectInRegistry(rs,"case2",false);
			String rm1[] = Naming.list("rmi://"+host+":"+port+"/");
		
			for(int i=0;i<rm1.length;i++)
			{
				if (rm1[i].equals("rmi://localhost:1099/case2"))
					flag = true;
			}
			if (flag) out.println("Result for TestCaseID SF-RMI-API-002:PASS");
			else out.println("Result for TestCaseID SF-RMI-API-002:Fail");
        }catch(Exception e){out.println(e);	}
		try {
			RMIUtil.unbindObjectFromRegistry("case2");
		}catch(Exception e){out.println(e);	}
	
    }

	private void testCase003()
	{
		try{
			RMIUtil.bindObjectInRegistry(rs,"case3",false);
			RMIUtil.bindObjectInRegistry(rs,"case3",false);
				
			String rm[] = Naming.list("rmi://"+host+":"+port+"/");
		
			for(int i=0;i<rm.length;i++)
			{
				if (rm[i].equals("rmi://localhost:1099/case1"))
				flag = true;
			}
			if (flag) out.println("Result for TestCaseID SF-RMI-API-003:FAIL");

		}catch(Exception e){out.println("Result for TestCaseID SF-RMI-API-003:PASS"); e.printStackTrace();	}

		try {
			RMIUtil.unbindObjectFromRegistry("case3");
		}catch(Exception e){out.println(e);	}
	}
	
	private void testCase004()
	{
		if (RMIUtil.getRMIBindStatus("MapAPI") == 1)
		out.println("Result for TestCaseID SF-RMI-API-004:PASS");
		else
		out.println("Result for TestCaseID SF-RMI-API-004:FAIL");
	}

	private void testCase005()
	{
		if (RMIUtil.getRMIBindStatus("afasdfasdf") == 2)
		out.println("Result for TestCaseID SF-RMI-API-005:PASS");
		else
		out.println("Result for TestCaseID SF-RMI-API-005:FAIL");
	}
	private void testCase006()
	{
		try {
		RMIUtil.bindObjectInRegistry(rs,"case6",false);
		//As bindobject is executed in a seperate thread. Giving thread.sleep is necessary. 
		Thread.sleep(2000);
	
		if (RMIUtil.getRMIBindStatus("case6") == 1)
		out.println("Result for TestCaseID SF-RMI-API-006:PASS");
		else
		out.println("Result for TestCaseID SF-RMI-API-006:FAIL");
		}catch(Exception exc) {exc.printStackTrace(); } 
		
	}

	private void testCase007()
	{
		boolean b = false;
		try {
			RMIUtil.bindObjectInRegistry(rs,"case7",false);
			Thread.sleep(20000);
			RMIUtil.unbindObjectFromRegistry("case7");
			
			String rm7[] = Naming.list("rmi://"+host+":"+port+"/");
			
			for(int i=0;i<rm7.length;i++)
			{
				if (rm7[i].equals("rmi://localhost:1099/case7"))
				b = true;
			}
			
			if (b) out.println("Result for TestCaseID SF-RMI-API-007:FAIL");
			else out.println("Result for TestCaseID SF-RMI-API-007:PASS");
		}catch(Exception exc) { } 

	}

	private void testCase008()
	{
		try {
		RMIUtil.bindObjectInRegistry(rs,"case10",false);
		Thread.sleep(2000);
		RMIUtil.unbindObjectFromRegistry("case10");
		Thread.sleep(2000);
		out.println(RMIUtil.getRMIBindStatus("case10"));
		if (RMIUtil.getRMIBindStatus("case10") == 2)
		out.println("Result for TestCaseID SF-RMI-API-008:PASS");
		else
		out.println("Result for TestCaseID SF-RMI-API-008:FAIL");
		}catch(Exception exc) { } 
		
	}
	
}


