/**
 * $Id: TestBESession.java,v 1.1.1.1 2001/01/10 15:20:13 nms Exp $
 */

package testbefe;

import com.adventnet.nms.startnms.SocketSessionConnectionBE;
import com.adventnet.nms.startnms.MainSocketSessionBE;
import java.io.*;

public class TestBESession implements SocketSessionConnectionBE
{
	static final int READY = 11;
	static final int REFRESH = 12;
	static final String ID = "TESTBEFE_ID";
	MainSocketSessionBE mss;
	String uniqueID;
	
	public TestBESession( MainSocketSessionBE mss )
	{
		System.out.println( "###Inside TestBESession - Constructor..." );
		this.mss = mss;
		mss.registerForResponses(this, ID);
	}
	
	public void receive( String uid, byte[] data )
	{
		uniqueID = uid;
		
		try
		{
			ObjectInputStream dis = new ObjectInputStream(new ByteArrayInputStream(data));
			int read = dis.readInt();
			if( read == READY )
			{
				String name = dis.readUTF();
				System.out.println( "###Received in TestBESession..." + name );
		//	}
		//	else if( read == REFRESH )
		//	{
				try
				{
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					ObjectOutputStream oos = new ObjectOutputStream(baos);
					oos.writeInt(REFRESH);
					String tosend = "@@@Test Message from TestBESession@@@";
					oos.writeUTF(tosend);
					oos.flush();
					System.out.println( "###Send from TestBESession..." + tosend );
					mss.send( ID, uniqueID, baos.toByteArray() );
				}
				catch( Exception ex )
				{
					System.err.println( "###Exception while sending message in TestBESession..." + ex );
					ex.printStackTrace();
				}
			}
		}
		catch( Exception e )
		{
			System.err.println( "###Exception in receive method in TestBESession..." + e );
			e.printStackTrace();
		}
	}
	
	public void close()
	{
		System.out.println( "###Inside TestBESession - close() method..." );
	}
}