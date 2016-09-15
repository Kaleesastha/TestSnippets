/**
 * $Id: TestClient.java,v 1.1.1.1 2001/01/10 15:20:13 nms Exp $
 */

package testbefe;

import com.adventnet.nms.startclient.SocketConnection;
import com.adventnet.nms.util.PureClientUtils;
import java.io.*;

public class TestClient implements SocketConnection
{
	static final int READY = 11;
	static final int REFRESH = 12;
	static final String ID = "TESTBEFE_ID";
	
	public TestClient()
	{
		System.out.println( "###Inside TestClient - Constructor..." );
		PureClientUtils.commonSocket.registerForResponses( this, ID );
	}
	
	public void receive( byte[] data )
	{
		ByteArrayInputStream bais = new ByteArrayInputStream(data);
		try
		{
			ObjectInputStream dis = new ObjectInputStream(bais);
			int code = dis.readInt();
			if( code == REFRESH )
			{
				System.out.println( "###Received in TestClient.." + dis.readUTF() );
			}
		}
		catch( Exception ex )
		{
			System.out.println( "###Exception in receive method in TestClient.. " + ex );
			ex.printStackTrace();
		}
	}
	
	public void sendData()
	{
		String tosend = "@@@Test Message from Client@@@";
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try
		{
			ObjectOutputStream dos = new ObjectOutputStream(baos);
			dos.writeInt(READY);
			dos.writeUTF(tosend);
			dos.flush();
		}
		catch( Exception ee )
		{
			System.out.println( "###Exception while sending data from TestClient.." + ee );
			ee.printStackTrace();
		}
		System.out.println( "###Send from TestClient..." + tosend );
		PureClientUtils.commonSocket.send( ID, baos.toByteArray() );
	}
	
	public void close()
	{
		System.out.println( "###Inside TestClient - close() method..." );
	}
	
/*	public static void main( String[] args )
	{
		TestClient client = new TestClient();
		client.sendData();
	}*/
}