/**
 * $Id: TestRmiStub.java,v 1.2 2003/06/03 06:56:40 asivakumar Exp $
 */

import java.rmi.*;

public class TestRmiStub
{
	
	public TestRmiStub() 
	{}
	
	public static void main( String[] args ) throws Exception 
	{
		String [] fullList = { "TftpAPI", "MainSocketAPI", "NmsPolicyAPI",
							   "UserConfigAPI", "MapAPI", "TrapAPI",
							   "SeverityAPI", "EventParserAPI",
							   "EventAPI", "EventFilterAPI", "ServerEventFactoryAPI", 
							   "AlertAPI", "AlertFilterAPI", "ServerAlertFactoryAPI",
							   "TopoAPI", "ServerTopoFactoryAPI", 
							   "PollAPI", "ServerPollFactoryAPI" };
		String host = "localhost";
		String portOption = ":1099";
		boolean npflag = false;
		for(int i = 0; i < args.length;i++)
		{
			if(args[i].equals("-h"))
			{
				host = args[i+1];
				i++;
			}
			else if(args[i].equals("-p"))
			{
				portOption = ":" + args[i+1];
				i++;
			}
			else if(args[i].equals("-np"))
			{
				portOption = "";
			}
		}
		String[] list=Naming.list("//localhost:1099");
		for(int i=0;i<list.length;i++)
		    {
			System.out.println(list[i]);
		    }
		for( int i=0; i < fullList.length; i++ )
		{
		  Remote obj = null;
		  try
		  {
		      obj = Naming.lookup("rmi://" + host +portOption + "/"+fullList[i]);
		      
		  }
		  catch(Exception ex)
		  {
		      System.err.println("***** ERROR: " + ex + "\n" + "FOR"+fullList[i]+"\n");
		  }
		}

		//Object res = ((com.adventnet.nms.topodb.TopoAPI)obj).getCompleteList();
		//boolean res = ((com.adventnet.nms.topodb.TopoAPI)obj).checkWritePermission(new com.adventnet.nms.topodb.ManagedObject());
		//System.out.println("result = " + res);

	}
	
}






