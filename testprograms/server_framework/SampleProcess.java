/**
 * SampleProcess.java
 */

import com.adventnet.nms.util.RunProcessInterface;

public class SampleProcess implements RunProcessInterface
{
	String str = null;
	
	public void callMain( String args[] )
	{
		System.out.println( str.length() );
	}
	
	public boolean isInitialized()
	{
		return true;
	}
	
	public void shutDown()
	{
		System.out.println( "SampleProcess: Inside shutdown!!!!!" );
		System.out.println( str.length() );
	}
	
	public static void main( String [] args )
	{
		System.out.println( "Sample..." );
	}
}
