/**
 * $Id: TestFESession.java,v 1.1.1.1 2001/01/10 15:20:13 nms Exp $
 */

package testbefe;

import com.adventnet.nms.startnms.SocketSessionConnectionFE;
import com.adventnet.nms.startnms.MainSocketSessionFE;
import com.adventnet.nms.util.PureServerUtils;
import java.io.*;

public class TestFESession implements SocketSessionConnectionFE
{
	static final int READY = 11;
	static final int REFRESH = 12;
	static final String ID = "TESTBEFE_ID";
	MainSocketSessionFE mss;
	String uniqueID;
	
	public TestFESession( MainSocketSessionFE mss )
	{
		System.out.println( "!!!###Inside TestFESession - Constructor..." );
		this.mss = mss;
		mss.registerForResponses(this, ID);
	}
	
	private String getBEMode()
	{
		if( PureServerUtils.useJDBC )
		{
			return "JDBC";
		}
		else
		{
			return "SERIALIZE";
		}
	}
	
	public boolean receive( String uid, byte[] data )
	{
		uniqueID = uid;
		
		if( getBEMode().equals("JDBC") )
		{
			try
			{
				ObjectInputStream dis = new ObjectInputStream(new ByteArrayInputStream(data));
				int read = dis.readInt();
				if( read == READY )
				{
					String name = dis.readUTF();
					System.out.println( "!!!###Received in TestFESession..." + name );
					//	}
					//	else if( read == REFRESH )
					//	{
					try
					{
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						ObjectOutputStream oos = new ObjectOutputStream(baos);
						oos.writeInt(REFRESH);
						String tosend = "!!!@@@Test Message from TestFESession@@@";
						oos.writeUTF(tosend);
						oos.flush();
						System.out.println( "!!!###Send from TestFESession..." + tosend );
						mss.send( ID, uniqueID, baos.toByteArray() );
					}
					catch( Exception ex )
					{
						System.err.println( "!!!###Exception while sending message in TestFESession..." + ex );
						ex.printStackTrace();
					}
				}
			}
			catch( Exception e )
			{
				System.err.println( "!!!###Exception in receive method in TestFESession..." + e );
				e.printStackTrace();
			}
		}
		else
		{
			// By returning false, the request is Handed over to the corresponding BE Session,
			// if present in the BE Server.
			System.out.println("!!!###Sending to the BE session for handling the data");
			return false;
		}
		return true;
	}
	
	public void close()
	{
		System.out.println( "!!!###Inside TestFESession - close() method..." );
	}
}