import com.adventnet.nms.util.InternalUtil;
public class ReplaceAll
{
    
    public static void main(String[] s)
    {
	    try
	    {
		    //System.err.println("The machine address is:"+java.net.InetAddress.getLocalHost().getHostAddress());
		    //String ipAddress = s[0];
		    //System.err.println(ipAddress.split("\\Q"+"."+"\\E", -1).length - 1);  
		    //System.err.println("encoding is::"+System.getProperty("file.encoding"));
			  /*  System.err.println("InetAddress.getLocalHost().getHostName()==>"+InetAddress.getLocalHost().getHostName());
			    System.err.println("InetAddress.getLocalHost().getHostAddress()==>"+InetAddress.getLocalHost().getHostAddress());
			    byte[] data = s[0].getBytes();
			    System.err.println("new String is!!"+new String(data,"UTF-8"));*/
		    try{
			    String st = "urs\\s";
			    System.err.println("replaceAll string is"+InternalUtil.replaceAll(st, "\\", "/"));
			    //System.err.println("replaceAll string is"+st.replaceAll( "\\", "/"));
		    }
		    catch(Exception ex)
		    {
			    ex.printStackTrace();
			    System.err.println("new String is!!"+s[0]);
		    }
	    }
	    catch(Exception e)
	    {
		    e.printStackTrace();
	    }
    }
}
