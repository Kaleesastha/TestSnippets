public class OSPingTest
{
    public static void main(String[] s)
    {
	    String network = s[0];
	    for(int i=0; i<=254; i++){
		    try
		    {
			    String host = network +"."+Integer.toString(i);
			    Runtime.getRuntime().exec("ping -c 2 "+host);
		    }
		    catch(Exception e)
		    {
			    e.printStackTrace();
		    }
	    }
    }
}
