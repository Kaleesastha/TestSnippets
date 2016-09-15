/**
 * $Id: TestBEServer.java,v 1.1.1.1 2001/01/10 15:20:13 nms Exp $
 */

package testbefe;

import com.adventnet.nms.startnms.SocketServerConnectionBE;
import com.adventnet.nms.util.PureServerUtilsBE;
import com.adventnet.nms.startnms.MainSocketSessionBE;

public class TestBEServer implements SocketServerConnectionBE
{
	public TestBEServer()
	{
		System.out.println( "###Inside TestBEServer - Constructor..." );
		PureServerUtilsBE.serverSocketBE.registerForResponses(this);
	}
	
	public void init( MainSocketSessionBE mss )
	{
		System.out.println( "###Inside TestBEServer - init() method..." );
		TestBESession sess = new TestBESession(mss);
	}
}