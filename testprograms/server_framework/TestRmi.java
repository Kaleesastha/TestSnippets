/**
 * $Id: TestRmi.java,v 1.1.1.1 2001/01/10 15:20:13 nms Exp $
 */

import java.rmi.*;

public class TestRmi
{
	
	public TestRmi() 
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
		
		String[] list = Naming.list("rmi://" + host +portOption);
		System.out.println( "REGISTERED MODULES" );
		System.out.println( "******************" );
		for( int i=0; i < list.length; i++ )
		{
			System.out.println( list[i] );
		}
		System.out.println( "\n\n" );
		System.out.println( "UNREGISTERED MODULES" );
		System.out.println( "********************" );
		for( int i=0; i < fullList.length; i++ )
		{
			if( !isRegistered( list, fullList[i].trim() ) )
		   {
				System.out.println( fullList[i] );
			}
		}
	}
	
	static boolean isRegistered( String[] reglist, String module )
	{
		for( int i=0; i < reglist.length; i++ )
		{
			if( reglist[i].endsWith(module) )
			{
				return true;
			}
		}
		return false;
	}
}
