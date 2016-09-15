/**
 * TestCorba.java
 */

import com.adventnet.nms.startnms.NmsCorbaMain;

public class TestCorba
{
	public static void main( String[] args )
	{
		Object obj = NmsCorbaMain.getInstance().getOrb();
		if( obj instanceof org.omg.CORBA.ORB )
		{
			System.out.println( "ORB instance " + obj.toString() );
		}
		else
		{
			System.out.println( "NOT AN ORB instance.. " + obj );
		}
	}
}