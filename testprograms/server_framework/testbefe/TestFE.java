/**
 * $Id: TestFE.java,v 1.1.1.1 2001/01/10 15:20:13 nms Exp $
 */

package testbefe;

import com.adventnet.nms.util.RunProcessInterface;

public class TestFE implements RunProcessInterface
{
	public void callMain( String[] args )
	{
		System.out.println( "!!!###Inside TestFE - callMain() method..." );
		TestFEServer server = new TestFEServer();
	}
	
	public boolean isInitialized()
	{
		return true;
	}
	
	public void shutDown()
	{
		System.out.println( "!!!###Inside TestFE - Shutdown() method..." );
	}
}