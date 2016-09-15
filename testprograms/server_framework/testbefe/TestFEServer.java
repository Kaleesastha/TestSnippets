/**
 * $Id: TestFEServer.java,v 1.1.1.1 2001/01/10 15:20:13 nms Exp $
 */

package testbefe;

import com.adventnet.nms.startnms.SocketServerConnectionFE;
import com.adventnet.nms.util.PureServerUtilsFE;
import com.adventnet.nms.startnms.MainSocketSessionFE;

public class TestFEServer implements SocketServerConnectionFE
{
	public TestFEServer()
	{
		System.out.println( "!!!###Inside TestFEServer - Constructor..." );
		PureServerUtilsFE.serverSocketFE.registerForResponses(this);
	}
	
	public void init( MainSocketSessionFE mss )
	{
		System.out.println( "!!!###Inside TestFEServer - init() method..." );
		TestFESession sess = new TestFESession(mss);
	}
}