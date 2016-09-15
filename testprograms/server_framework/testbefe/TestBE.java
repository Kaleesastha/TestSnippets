/**
 * $Id: TestBE.java,v 1.1.1.1 2001/01/10 15:20:13 nms Exp $
 */

package testbefe;

import com.adventnet.nms.util.RunProcessInterface;

public class TestBE implements RunProcessInterface
{
	public void callMain( String[] args )
	{
		System.out.println( "###Inside TestBE - callMain() method..." );
		TestBEServer server = new TestBEServer();
	}
	
	public boolean isInitialized()
	{
		return true;
	}
	
	public void shutDown()
	{
		System.out.println( "###Inside TestBE - Shutdown() method..." );
	}
}