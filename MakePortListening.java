/**
 * MakePortListening.java
 */

import java.net.*;

public class MakePortListening
{
	DatagramSocket udpSocket = null;
	DatagramPacket packet = null;
	ServerSocket tcpSocket = null;
	
	String mode = "TCP";
	int port = -1;
	public MakePortListening( String[] args )
	{
		if( args == null || args.length == 0 )
		{
			usage();
		}
		for(int i = 0; i < args.length;i++)
		{
			if(args[i].equals("-m"))
			{
				mode = args[i+1];
				i++;
			}
			else if(args[i].equals("-p"))
			{
				try
				{
					port = Integer.parseInt(args[i+1]);
					i++;
				}
				catch( Exception e )
				{
					e.printStackTrace();
				}
			}
		}
		portInUse( mode, port );
	}
	
	private void usage()
	{
		System.out.println( "Usage : java MakePortListening [-m mode] -p port" );
		System.exit(1);
	}
	
	private void portInUse( String mode, int port )
	{
		String str = new String("test");
		byte [] bytearr = str.getBytes();
		try
		{
		if( mode.equals("UDP") )
		{
			udpSocket = new DatagramSocket(port);
			packet = new DatagramPacket(bytearr,0);
                        while(true)
                        {
                            udpSocket.receive(packet);
                        }
		}
		else if( mode.equals("TCP") )
		{
			tcpSocket = new ServerSocket(port);
                        while(true)
                        {
                            tcpSocket.accept();
                        }
		}
		}
		catch( Exception ex )
		{
			ex.printStackTrace();
		}
	}
	
	public static void main( String[] args )
	{
		MakePortListening tp = new MakePortListening(args);
	}
}

